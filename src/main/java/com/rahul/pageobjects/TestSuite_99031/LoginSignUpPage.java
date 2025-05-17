package com.rahul.pageobjects.TestSuite_99031;

import com.rahul.actiondriver.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginSignUpPage {
    private WebDriver driver=null;
    private Action action=null;

    public LoginSignUpPage(WebDriver driver){
        this.driver=driver;
        action=new Action();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Name']")
    WebElement userName;

    @FindBy(css = "input[data-qa='signup-email']")
    WebElement signUpEmail;

    @FindBy(css = "button[data-qa='signup-button']")
    WebElement signUpButton;

    @FindBy(css = "div[class='signup-form'] h2")
    WebElement newUserSignUp;

    @FindBy(css = "input[data-qa='login-email']")
    WebElement loginEmail;

    @FindBy(css = "input[placeholder='Password']")
    WebElement loginPassword;

    @FindBy(css = "button[data-qa='login-button']")
    WebElement loginButton;

    @FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']")
    WebElement ErrorLabel;

    public void enterUserName(String Username){
        action.type(userName,Username);
    }

    public void enterEmail(String email){
        action.type(signUpEmail,email);
    }

    public RegistrationPage clickOnSignUpButton(){
        action.click(driver,signUpButton);
        return new RegistrationPage(driver);
    }

    public boolean isNewUserSinnUpVisible(){
        return action.isDisplayed(driver,newUserSignUp);
    }

    public void enterLoginEmail(String email){
        action.type(loginEmail,email);
    }

    public void enterLoginPassword(String password){
        action.type(loginPassword,password);
    }

    public UserHomePage clickLoginButton(WebDriver driver){
        action.click(driver,loginButton);
        return new UserHomePage(driver);
    }

    public boolean isErrorLabelDisplayed(){
        action.explicitWait(driver, ErrorLabel,5);
        return action.isDisplayed(driver,ErrorLabel);
    }

    public String getErrorMessageText(){
        return ErrorLabel.getText();
    }

}
