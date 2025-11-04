package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignUpPage {
    private final GUIDriver driver;
    public SignUpPage(GUIDriver driver) {
        this.driver=driver;
    }

    //Locator
    private final  By continueButton = By.cssSelector(".btn.btn-primary");
    private final  By accountCreatedLabel = By.cssSelector("h2[class='title text-center'] b");
    private  final By name =By.cssSelector("#name");
    private  final By email = By.cssSelector("#email");
    private final By password = By.cssSelector("#password");
    private final By day = By.cssSelector("#days");
    private final By month = By.id("months");
    private final By year =By.cssSelector("#years");
    private final By newsletter = By.id("newsletter");
    private final  By specialOffers =By.cssSelector("#optin");
    private final By firstName = By.cssSelector("#first_name");
    private final By lastName = By.cssSelector("#last_name");
    private final By company = By.cssSelector("#company");
    private final By address1 = By.cssSelector("#address1");
    private  final By address2 = By.cssSelector("#address2");
    private final By country =By.cssSelector("#country");
    private final By state =By.cssSelector("#state");
    private final By city =By.cssSelector("#city");
    private final By zipcode=By.cssSelector("#zipcode");
    private final By mobileNumber=By.cssSelector("#mobile_number");
    private final By createAccountButton = By.cssSelector("button[data-qa='create-account']");

    //actions
    @Step("Choose title {title}")
    private SignUpPage chooseTitle(String title){
        By titleLocator = By.xpath("//input[@value='"+title+"']");
        driver.element().click(titleLocator);
        return this;
    }

    @Step("Fill Registration form")
    public SignUpPage FillRegistrationForm(String title,
                                           String passwordText,
                                           String dayText,
                                           String monthText,
                                           String yearText,
                                           String firstNameText,
                                           String lastNameText,
                                           String companyText,
                                           String address1Text,
                                           String address2Text,
                                           String countryText,
                                           String stateText,
                                           String cityText,
                                           String zipcodeText,
                                           String mobileNumberText){

        chooseTitle(title);
        driver.element().type(password, passwordText);
        driver.element().selectFromDropdown(day, dayText);
        driver.element().selectFromDropdown(month, monthText);
        driver.element().selectFromDropdown(year, yearText);
        driver.element().click(newsletter);
        driver.element().click(specialOffers);
        driver.element().type(firstName, firstNameText);
        driver.element().type(lastName, lastNameText);
        driver.element().type(company, companyText);
        driver.element().type(address1, address1Text);
        driver.element().type(address2, address2Text);
        driver.element().selectFromDropdown(country, countryText);
        driver.element().type(state, stateText);
        driver.element().type(city, cityText);
        driver.element().type(zipcode, zipcodeText);
        driver.element().type(mobileNumber, mobileNumberText);
        return this;
    }

    @Step("Click on Create Account Button ")
    public SignUpPage ClickCreateAccountButton(){
        driver.element().click(createAccountButton);
        return this;
    }

@Step("Click on continue button")
public  NavigationBarComponent ClickonContinueButton(){
        driver.element().click(continueButton);
        return new NavigationBarComponent(driver);
}

    //validation
    @Step("Verify Account created")
    public SignUpPage verifyAccountCreated(){
        driver.verification().isElementVisible(accountCreatedLabel);
        return this;
    }

}
