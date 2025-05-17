package com.rahul.testcases.TestSuite_99031;

import com.aventstack.extentreports.ExtentTest;
import com.rahul.actiondriver.Action;
import com.rahul.base.BaseClass;
import com.rahul.dataprovider.dataprovider_99031.DataProvider_99031;
import com.rahul.pageobjects.TestSuite_99031.LandingPage;
import com.rahul.pageobjects.TestSuite_99031.LoginSignUpPage;
import com.rahul.pageobjects.TestSuite_99031.RegistrationPage;
import com.rahul.pageobjects.TestSuite_99031.UserHomePage;
import com.rahul.utility.ExtentTestManager;
import com.rahul.utility.LoggerManager;
import com.rahul.utility.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Map;

public class TC01_UserRegistrationTest extends BaseClass {

    @Test(priority = 1, dataProvider = "SignUpDataProvider", dataProviderClass= DataProvider_99031.class)
    public void UserRegistrationTestMethod(Map<String ,String> testData) throws InterruptedException {

        ExtentTest test = ExtentTestManager.get();
        Logger logger = LoggerManager.getLogger();
        Action action = new Action();

        action.launchUrl(WebDriverManager.getDriver(),properties.getProperty("applicationUrl"));
        test.info("Application url launched successfully.");
        logger.info("Application url launched successfully.");

        LandingPage landingPage = new LandingPage(WebDriverManager.getDriver());
        SoftAssert softAssert = new SoftAssert();

        //Verifying Home page visibility
        softAssert.assertTrue(landingPage.isLandingPageVisible());
        test.info("Logo is visible on landing page of application.");
        logger.info("Logo is visible on landing page of application.");

        LoginSignUpPage loginSignUpPage = landingPage.clickOnLoginSignUp();
        test.info("Clicked on loginSignUp button.");
        logger.info("Clicked on loginSignUp button.");

        //Application navigated to LoginSignUp Page
        softAssert.assertTrue(loginSignUpPage.isNewUserSinnUpVisible());
        test.info("Navigated to LoginSignUp Page.");
        logger.info("Navigated to LoginSignUp Page.");

        loginSignUpPage.enterUserName(testData.get("UserName"));
        test.info("Username entered successfully.");
        logger.info("Username entered successfully.");
        loginSignUpPage.enterEmail(testData.get("Email"));
        test.info("Email entered successfully.");
        logger.info("Email entered successfully.");
        RegistrationPage registrationPage = loginSignUpPage.clickOnSignUpButton();
        test.info("SignUp Button Clicked successfully.");
        logger.info("SignUp Button Clicked successfully.");
        Thread.sleep(Duration.ofSeconds(2));

        //Application navigated to Registration Page
        softAssert.assertTrue(registrationPage.isEnterAccountInformationTextDisplayed());
        test.info("Navigated to Registration Page.");
        logger.info("Navigated to Registration Page.");
        registrationPage.selectTitle(testData.get("Title"));
        registrationPage.enterPassword(testData.get("Password"));
        registrationPage.selectDOB(testData.get("day"),testData.get("month"),testData.get("year"));
        registrationPage.selectNewLetterCheckBox(testData.get("Sign up for our newsletter!"));
        registrationPage.selectReceive_special_offers_CheckBox(testData.get("Receive special offers from our partners!"));
        registrationPage.enterFirstName(testData.get("FirstName"));
        registrationPage.enterLastName(testData.get("LastName"));
        registrationPage.enterAddresslineone(testData.get("Address"));
        registrationPage.enterState(testData.get("State"));
        registrationPage.enterCityName(testData.get("City"));
        registrationPage.enterZipCode(testData.get("ZipCode"));
        registrationPage.enterMobileNumber(testData.get("MobileNumber"));
        test.info("Form is filled for Registration Successfully.");
        logger.info("Form is filled for Registration Successfully.");
        registrationPage.clickOnCreateAccount();
        test.info("CreateAccount Button Clicked Successfully.");
        logger.info("CreateAccount Button Clicked Successfully.");
        Thread.sleep(Duration.ofSeconds(2));


        softAssert.assertTrue(registrationPage.isAccountCreatedVisible());
        test.info("Account Created Text is present on page.");
        logger.info("Account Created Text is present on page.");
        UserHomePage userHomePage=registrationPage.clickonContinueButton();
        Thread.sleep(Duration.ofSeconds(2));
        String text=userHomePage.getLoggedInAsText();
        String ExpectedMsg="Logged in as "+testData.get("UserName");
        softAssert.assertEquals(text,ExpectedMsg);
        test.info("Text "+ExpectedMsg+" is present on user home page.");
        logger.info("Text "+ExpectedMsg+" is present on user home page.");
        userHomePage.clickOnDeleteAccount();
        test.info("Clicked on DeleteAccount successfully.");
        logger.info("Clicked on DeleteAccount successfully.");
        landingPage= userHomePage.clickOnContinueButton();
        test.info("Clicked on Continue button successfully.");
        logger.info("Clicked on Continue button successfully.");

        Thread.sleep(Duration.ofSeconds(2));
        softAssert.assertTrue(landingPage.isLandingPageVisible());
        test.info("After user deletion navigated back to landing page of application.");
        logger.info("After user deletion navigated back to landing page of application.");

        softAssert.assertAll();
        test.info("All Validation Passes Successfully.");
        logger.info("All Validation Passes Successfully.");
        logger.info("Test Case Finished.");

    }

}
