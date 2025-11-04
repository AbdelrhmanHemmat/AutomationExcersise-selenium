package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.utils.DataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignUpLoginPage {
   public NavigationBarComponent navigationBarComponent;

    private GUIDriver driver;
    private final  String SignUpLoginEndPoint  = "/login";

    public SignUpLoginPage(GUIDriver driver) {
        this.driver=driver;
        this.navigationBarComponent=new NavigationBarComponent(driver);
    }

    //Locator
    private final By LoginEmail    = By.cssSelector("input[data-qa='login-email']");
    private final By LoginPassword = By.cssSelector("input[placeholder='Password']");
    private final By SignUpName    = By.cssSelector("input[placeholder='Name']");
    private final By SignupEmail   = By.cssSelector("input[data-qa='signup-email']");
    private final By LoginButton   = By.cssSelector("button[data-qa='login-button']");
    private final By SignUpButton  = By.cssSelector("button[data-qa='signup-button']");
    private final By LoginText     = By.cssSelector("div[class='login-form'] h2");
    private final By LoginError    = By.cssSelector("body > section:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(2) > p:nth-child(4)");
    private final By SignUpError   = By.cssSelector("body > section:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > form:nth-child(2) > p:nth-child(5)");


    //Actions
    @Step("Navigate to Register/Login Page")
    public  SignUpLoginPage Navigate(){
        driver.browser().NavigateTo(PropertyReader.getProperty("baseUrlWeb") + SignUpLoginEndPoint);
        return this;
    }

    @Step("Enter email {email} in Login field")
    public  SignUpLoginPage enterLoginEmail(String email){
        driver.element().type(LoginEmail,email);
        return this;
    }
    @Step("Enter Password {Password} in Login field")
    public  SignUpLoginPage enterLoginPassword(String Password){
        driver.element().type(LoginPassword,Password);
        return this;
    }
    @Step("Click on Login button")
    public SignUpLoginPage ClickonLoginButton(){
        driver.element().click(LoginButton);
        return this;
    }



    @Step("Enter name {name} in SignUp Field")
    public SignUpLoginPage enterSignUpName(String name){
        driver.element().type(SignUpName,name);
        return this;
    }
    @Step("Enter Email {email} in SignUp Field")
    public SignUpLoginPage enterSignUpEmail(String email){
        driver.element().type(SignupEmail,email);
        return this;
    }
    @Step("Click on the Sign up button")
    public SignUpLoginPage clickSignUpButton(){
        driver.element().click(SignUpButton);
        return new SignUpLoginPage(driver);
    }


    //Validation
    @Step("Verify Existance of Login Text and new user Signup Text")
    public SignUpLoginPage ExistOfLoginTextNewUserText(){
        driver.verification().isElementVisible(LoginText);
        return  this;
    }


    @Step("Verify SignUp error message {errorMessage}")
    public SignUpLoginPage verifySignUpErrorMessage(String errorExpected){
        String ActualError =driver.element().getText(SignUpError);
        driver.verification().Equals(ActualError,errorExpected,"Login error message is not as expected");
        return this;
    }

    @Step("Verify Login error message {errorMessage}")
    public SignUpLoginPage verifyLoginErrorMessage(String errorExpected){
        String ActualError =driver.element().getText(LoginError);
        driver.verification().Equals(ActualError,errorExpected,"Login error message is not as expected");
        return this;
    }


}
