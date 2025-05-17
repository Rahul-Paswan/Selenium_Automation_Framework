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

public class TC_02_LoginTest extends BaseClass {

    private Action action=null;
    private ExtentTest test=null;
    private SoftAssert softAssert=null;
    private Logger logger;

    @Test(priority = 1, dataProvider = "SignUpDataProvider", dataProviderClass= DataProvider_99031.class)
    public void RegistrationTest_Method(Map<String ,String> testData) throws InterruptedException {
        test= ExtentTestManager.get();
        logger= LoggerManager.getLogger();
        action=new Action();
        softAssert=new SoftAssert();

        action.launchUrl(WebDriverManager.getDriver(),properties.getProperty("applicationUrl"));
        test.info("Application url launched successfully.");
        logger.info("Application url launched successfully.");
        LandingPage landingPage = new LandingPage(WebDriverManager.getDriver());
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
        softAssert.assertAll();

    }

    @Test(priority = 2, dependsOnMethods = {"RegistrationTest_Method"} ,dataProvider = "CorrectLoginDataProvider",dataProviderClass = DataProvider_99031.class)
    public void loginTestMethod_CorrectUsridPassword(Map<String ,String> testData){
        action=new Action();
        test= ExtentTestManager.get();
        softAssert=new SoftAssert();
        action.launchUrl(WebDriverManager.getDriver(),properties.getProperty("applicationUrl"));
        test.info("Url Launched Successfully");
        logger.info("Url Launched Successfully");

        LandingPage landingPage=new LandingPage(WebDriverManager.getDriver());
        softAssert.assertTrue(landingPage.isLandingPageVisible());
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

        String text=userHomePage.getLoggedInAsText();
        String ExpectedMsg="Logged in as "+testData.get("UserName");
        softAssert.assertEquals(text,ExpectedMsg);
        test.info("Text "+ExpectedMsg+" is present on user home page.");
        logger.info("Text "+ExpectedMsg+" is present on user home page.");
        softAssert.assertAll();
    }
}
