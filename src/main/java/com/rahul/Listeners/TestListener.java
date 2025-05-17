package com.rahul.Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.rahul.base.BaseClass;
import com.rahul.utility.ExtentTestManager;
import com.rahul.utility.LoggerManager;
import com.rahul.utility.WebDriverManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseClass implements ITestListener {

    @Override
    public void onTestSkipped(ITestResult result) {
        try{
            System.out.println("Test Case Skipped.");
            //creating ExtentTest Object
            ExtentTest extentTest = extentReports.createTest(result.getTestContext().getName()+"__"+result.getName());

            WebDriverManager.setBrowser(result.getTestContext().getCurrentXmlTest().getParameter("browserName"));
            extentTest.assignAuthor(WebDriverManager.getBrowser());

            extentTest.assignCategory(result.getTestContext().getCurrentXmlTest().getParameter("category"));
            extentTest.assignAuthor((result.getTestContext().getCurrentXmlTest().getParameter("authorName")));

            ExtentTestManager.set(extentTest);
            //Creating logger object
            LoggerManager.createLogger(result.getTestContext().getName()+"_"+result.getName());
            ExtentTestManager.get().log(Status.SKIP,"------------------------------"+result.getName()+" Test is Skipped.-----------------------------------");
            LoggerManager.getLogger().info("------------------------------{} Test is Skipped.-----------------------------------", result.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
