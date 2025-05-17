package com.rahul.pageobjects.TestSuite_99031;

import com.rahul.actiondriver.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class UserHomePage {

    private WebDriver driver;
    private Action action=null;

    public UserHomePage(WebDriver driver){
        this.driver=driver;
        action=new Action();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a")
    WebElement LoggedInAs;

    @FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a")
    WebElement deleteAccount;

    @FindBy(css = "h2[data-qa='account-deleted']")
    WebElement accountDeletedLabel;

    @FindBy(css = "a[data-qa='continue-button']")
    WebElement continueBtn;

    public String getLoggedInAsText(){
        action.explicitWait(driver,LoggedInAs,5);
        return LoggedInAs.getText();
    }

    public void clickOnDeleteAccount(){
        action.explicitWait(driver,deleteAccount,5);
        action.click(driver,deleteAccount);
    }

    public boolean isccountDeletedTextMessagevisible(){
        action.explicitWait(driver,accountDeletedLabel,5);
        return action.isDisplayed(driver,accountDeletedLabel);
    }

    public LandingPage clickOnContinueButton(){
        action.click(driver,continueBtn);
        return  new LandingPage(driver);
    }
}
