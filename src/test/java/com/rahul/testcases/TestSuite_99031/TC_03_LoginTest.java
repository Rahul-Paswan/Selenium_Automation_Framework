package com.rahul.testcases.TestSuite_99031;

import com.aventstack.extentreports.ExtentTest;
import com.rahul.actiondriver.Action;
import com.rahul.base.BaseClass;
import com.rahul.dataprovider.dataprovider_99031.DataProvider_99031;
import com.rahul.pageobjects.TestSuite_99031.LandingPage;
import com.rahul.pageobjects.TestSuite_99031.LoginSignUpPage;
import com.rahul.pageobjects.TestSuite_99031.UserHomePage;
import com.rahul.utility.ExtentTestManager;
import com.rahul.utility.LoggerManager;
import com.rahul.utility.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class TC_03_LoginTest extends BaseClass {

    @Test(priority = 1, dataProvider = "InCorrectLoginDataProvider",dataProviderClass = DataProvider_99031.class)
    public void loginTestMethod_IncorrectUsridPassword(Map<String ,String> testData){

        Logger logger = LoggerManager.getLogger();
        Action action = new Action();
        ExtentTest test = ExtentTestManager.get();
        SoftAssert softAssert = new SoftAssert();
        action.launchUrl(WebDriverManager.getDriver(),properties.getProperty("applicationUrl"));
        test.info("Url Launched Successfully");
        logger.info("Url Launched Successfully");

        LandingPage landingPage=new LandingPage(WebDriverManager.getDriver());
        softAssert.assertTrue(landingPage.isLandingPageVisible());
        softAssert.assertAll();
        test.info("Application Landing Page Open Successfully");
        logger.info("Application Landing Page Open Successfully");

        LoginSignUpPage loginSignUpPage=landingPage.clickOnLoginSignUp();
        test.info("Clicked On loginSign .");
        logger.info("Clicked On loginSign .");

//      login to application
        loginSignUpPage.enterLoginEmail(testData.get("Email"));
        test.info("UserName Entered Successfully.");
        logger.info("UserName Entered Successfully.");
        loginSignUpPage.enterLoginPassword(testData.get("Password"));
        test.info("Password Entered Successfully.");
        logger.info("Password Entered Successfully.");
        UserHomePage userHomePage=loginSignUpPage.clickLoginButton(WebDriverManager.getDriver());
        test.info("Clicked on login button.");
        logger.info("Clicked on login button.");

        softAssert.assertTrue(loginSignUpPage.isErrorLabelDisplayed());
        softAssert.assertAll();
        String text=loginSignUpPage.getErrorMessageText();
        softAssert.assertEquals(text,"Your email or password is incorrect!");
        softAssert.assertAll();
        test.info("\"Your email or password is incorrect!\" is present on page.");
        logger.info("\"Your email or password is incorrect!\" is present on page.");


    }
}
