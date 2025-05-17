package com.rahul.pageobjects.TestSuite_99031;

import com.rahul.actiondriver.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPage {
    private WebDriver driver = null;
    private Action action = null;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        action = new Action();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href='/login']")
    WebElement loginSignUp;

    @FindBy(css = "img[alt='Website for automation practice']")
    WebElement logo;


    public LoginSignUpPage clickOnLoginSignUp() {
        action.explicitWait(driver, loginSignUp, 5);
        action.click(driver, loginSignUp);
        return new LoginSignUpPage(driver);
    }

    public boolean isLandingPageVisible() {
        action.explicitWait(driver,logo,5);
        return action.isDisplayed(driver,logo);
    }
}
