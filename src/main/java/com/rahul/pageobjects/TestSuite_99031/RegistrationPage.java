package com.rahul.pageobjects.TestSuite_99031;

import com.rahul.actiondriver.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegistrationPage {
    private WebDriver driver=null;
    private Action action=null;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        action=new Action();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//b[normalize-space()='Enter Account Information'])[1]")
    private WebElement EnterAccountInformation;

    @FindBy(css = "input[type='radio'][value='Mr']")
    private WebElement MrTitle;

    @FindBy(css = "input[type='radio'][value='Mrs']")
    private WebElement MrsTitle;

    @FindBy(css = "#password")
    private WebElement password;

    @FindBy(id = "days")
    private WebElement Day;

    @FindBy(id = "months")
    private WebElement Month;

    @FindBy(id = "years")
    private WebElement Year;

    @FindBy(css = "#newsletter")
    private WebElement newsletterCheckBox;

    @FindBy(css = "#optin")
    private WebElement Receive_special_offers_CheckBox;

    @FindBy(css = "#first_name")
    private WebElement firstName;

    @FindBy(css = "#last_name")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id='address1']")
    private WebElement addressLineOne;

    @FindBy(xpath = "//input[@id='state']")
    private WebElement state;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement city;

    @FindBy(xpath = "//input[@id='zipcode']")
    private WebElement zipCode;

    @FindBy(xpath = "//input[@id='mobile_number']")
    private WebElement mobileNumber;

    @FindBy(css = "button[data-qa='create-account']")
    private WebElement CreateAccountBtn;

    @FindBy(css = "h2[data-qa='account-created']")
    WebElement accountedCreatedLabel;

    @FindBy(css = "a[data-qa='continue-button']")
    WebElement continueBtn;


    //Action Methods
    public boolean isEnterAccountInformationTextDisplayed() {
        return action.isDisplayed(driver,EnterAccountInformation);
    }

    public void selectTitle(String titleName) {
        if (titleName.equalsIgnoreCase("Mr")) {
            action.click(driver,MrTitle);
        } else if (titleName.equalsIgnoreCase("Mrs")) {
            action.click(driver,MrsTitle);
        } else {
            System.out.println("please provide valid Gender name for title like Male and Female");
        }
    }

    public void enterPassword(String pwd) {
        action.type(password,pwd);
    }

    public void selectDOB(String day, String month, String year) {
        action.explicitWait(driver,Day,5);
        //Selecting Day
        action.selectByVisibleText (day,Day);
        action.explicitWait(driver,Day,5);

        //Selecting Month like 'March'
        action.selectByVisibleText(month,Month);
        action.explicitWait(driver,Day,5);

        //Selecting Year like '1998'
        action.selectByVisibleText(year,Year);
    }

    public void selectNewLetterCheckBox(String val) {
        if(val.equalsIgnoreCase("yes")){
            action.click(driver,newsletterCheckBox);
        }
        else {
            return;
        }

    }

    public void selectReceive_special_offers_CheckBox(String val) {
        if(val.equalsIgnoreCase("yes")){
            action.click(driver,Receive_special_offers_CheckBox);
        }
        else {
            return;
        }

    }

    public void enterFirstName(String firstName) {
        action.type(this.firstName,firstName);
    }

    public void enterLastName(String lastName) {
        action.type(this.lastName,lastName);
    }

    public void enterAddresslineone(String addline1) {
        action.type(addressLineOne,addline1);
    }

    public void enterState(String stateName) {
        action.type(state,stateName);
    }

    public void enterCityName(String cityName) {
        action.type(city,cityName);
    }

    public void enterZipCode(String zipCode) {
        action.type(this.zipCode,zipCode);
    }

    public void enterMobileNumber(String mobileNumber) {
        action.type(this.mobileNumber,mobileNumber);
    }

    public void clickOnCreateAccount() {
        action.click(driver,CreateAccountBtn);
    }

    public boolean isAccountCreatedVisible() {
        action.explicitWait(driver,accountedCreatedLabel,5);
        return action.isDisplayed(driver,accountedCreatedLabel);
    }

    public UserHomePage clickonContinueButton() {
        action.click(driver,continueBtn);
        return new UserHomePage(driver);
    }

}
