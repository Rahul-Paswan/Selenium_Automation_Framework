package com.rahul.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.rahul.utility.ExtentTestManager;
import com.rahul.utility.LoggerManager;
import com.rahul.utility.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


public class BaseClass {

    private static File file;
    protected static ExtentReports extentReports;
    protected static Properties properties;
    private static String testName;


// Before Suite initializing shared resources like ExtentReport.
    @BeforeSuite
    public void BeforeSuit(){
//Loading properties file.
        properties=new Properties();
        try {
            FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + "\\Configuration\\config.properties"));
            properties.load(fis);
            System.out.println("Properties file loaded successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        file = new File(System.getProperty("user.dir") + "\\ExtentReportOutput\\Report.html");
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(file);
        try {
            extentSparkReporter.loadJSONConfig(new File(System.getProperty("user.dir")+"\\Configuration\\extent-config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("Operating System Version",System.getProperty("os.version"));
        extentReports.setSystemInfo("Environment",properties.getProperty("Environment"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));

    }

    @Parameters({"browserName"})
    @BeforeMethod
    public void BeforeMethod(@Optional("Chrome") String browserName , Method method, ITestContext context){
        WebDriverManager.setupDriver(browserName);
        //creating Logger
        LoggerManager.createLogger(context.getName()+"_"+method.getName());

        //Creating ExtentTest object
        ExtentTest extentTest = extentReports.createTest(context.getName()+"__"+method.getName());
        extentTest.assignDevice(browserName);
        extentTest.assignCategory(context.getCurrentXmlTest().getParameter("category"));
        extentTest.assignAuthor(context.getCurrentXmlTest().getParameter("authorName"));
        ExtentTestManager.set(extentTest);
        ExtentTestManager.get().info("------------------------------"+method.getName()+" Test is Started-----------------------------------");
        LoggerManager.getLogger().info("------------------------------{} Test is Started-----------------------------------", method.getName());
        testName=context.getName();

    }

    @AfterMethod
    public void AfterMethod(Method method, ITestResult result){
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                ExtentTestManager.get().log(Status.PASS,"------------------------------"+method.getName()+" Test is Passed.-----------------------------------");
                LoggerManager.getLogger().info("------------------------------{} Test is Passed.-----------------------------------", method.getName());
                break;

            case ITestResult.FAILURE:
                ExtentTestManager.get().log(Status.FAIL,"------------------------------"+method.getName()+" Test is Failed.-----------------------------------");
                ExtentTestManager.get().log(Status.FAIL,"Reason of Failure is:"+result.getThrowable());
                LoggerManager.getLogger().fatal("------------------------------{} Test is Failed.-----------------------------------", method.getName());
                LoggerManager.getLogger().fatal("Failure Reason is:{}", String.valueOf(result.getThrowable()));

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss.SSS");
                String timestamp = LocalDateTime.now().format(dtf);

                TakesScreenshot takesScreenshot= (TakesScreenshot)WebDriverManager.getDriver();
                File Destination_file=new File(System.getProperty("user.dir")+"\\Screenshots\\"+testName+"\\"+testName+"__"+method.getName()+"_"+timestamp+".jpg");
                File source_File=takesScreenshot.getScreenshotAs(OutputType.FILE);
                String Base64IMG=takesScreenshot.getScreenshotAs(OutputType.BASE64);

                try {
                    FileUtils.copyFile(source_File,Destination_file);
                    ExtentTestManager.get().addScreenCaptureFromBase64String(Base64IMG,result.getName());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                ExtentTestManager.get().info("------------------------------"+method.getName()+" Unknown Result.-----------------------------------");
                LoggerManager.getLogger().info("------------------------------{} Unknown Result-----------------------------------", method.getName());

        }
        ExtentTestManager.get().info("------------------------------"+method.getName()+" Test is Ended-----------------------------------");
        LoggerManager.getLogger().info("------------------------------{} Test is Ended-----------------------------------", method.getName());

//    removing thread set object like driver , ExtentTest, Browser Name etc from local thread
        LoggerManager.removeLogger();
        WebDriverManager.getDriver().quit();
        WebDriverManager.unload();
        ExtentTestManager.remove();
        WebDriverManager.removeThreadDriver();

    }

    @AfterSuite
    public void AfterSuite(){
        extentReports.flush();
        try {
            URI uri = file.toURI();
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Desktop is not supported on this system.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


