package com.rahul.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

public class WebDriverManager {

    public static final ThreadLocal<WebDriver> tdriver=new ThreadLocal<>();
    public static final ThreadLocal<String> tbrowserName=new ThreadLocal<>();
    private static WebDriver driver;

    public static WebDriver getDriver(){
        return tdriver.get();
    }

    public static void setBrowser(String browserName) {
        tbrowserName.set(browserName);
    }

    public static String getBrowser() {
        return tbrowserName.get();
    }

    public static void removeThreadDriver() {
         tdriver.remove();
    }

    public static void unload() {
        tbrowserName.remove();
    }

    public static void setupDriver(String browserName){
        setBrowser(browserName);
        if(browserName.equalsIgnoreCase("chrome")){
            ChromeOptions options=new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("start-maximized");
            options.addExtensions(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\CRX_file\\uBlockCRX.crx"));
            driver=new ChromeDriver(options);
            tdriver.set(driver);
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options=new EdgeOptions();
            options.addArguments("headless");
            options.addArguments("start-maximized");
//            options.addExtensions(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\CRX_file\\uBlockCRX.crx"));
            driver=new EdgeDriver(options);
            tdriver.set(driver);
        }else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options=new FirefoxOptions();
            options.addArguments("headless");
            options.addArguments("start-maximized");
            driver=new FirefoxDriver(options);
            tdriver.set(driver);
        }
        else {
            System.out.println("Please provide valid browser Name:");
        }
    }

}
