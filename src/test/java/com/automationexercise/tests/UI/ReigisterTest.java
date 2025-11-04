package com.automationexercise.tests.UI;

import com.automationexercise.apis.UserManagementAPI;
import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.drivers.UITest;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.pages.components.SignUpLoginPage;
import com.automationexercise.pages.components.SignUpPage;
import com.automationexercise.tests.BaseTest;
import com.automationexercise.utils.DataReader.JsonReader;
import com.automationexercise.utils.Logs.TimeManager;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Automation Exercise")
@Feature("UI User Management")
@Story("User Registration")
@Severity(SeverityLevel.CRITICAL)
@Owner("Hemmat")
@UITest
public class ReigisterTest extends BaseTest {

    String timestamp = TimeManager.getSimpleTimeStamp();
    //TESTS
    @Description("Verify user can sign up with valid data")
    @Test
    public  void ValidSignUpTC(){

        new SignUpLoginPage(driver).Navigate().
                enterSignUpName(testData.getJsonData("name"))
                .enterSignUpEmail(testData.getJsonData("email")+timestamp+"@gmail.com")
                .clickSignUpButton();

        new SignUpPage(driver)
                .FillRegistrationForm(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("password"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber")
                        )
                .ClickCreateAccountButton()
                .verifyAccountCreated();
    }

    @Description("Verify user cannot sign up with invalid data")
    @Test
    public void verifyErrorMessageWhenAccountCreatedBefore()
    {
        //precondition > create a user account
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email") + timestamp  + "@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber")
                )
                .verifyUserCreatedSuccessfully();

        new SignUpLoginPage(driver).Navigate()
                .enterSignUpName(testData.getJsonData("name"))
                .enterSignUpEmail(testData.getJsonData("email") + timestamp  + "@gmail.com")
                .clickSignUpButton()
                .verifySignUpErrorMessage(testData.getJsonData("messages.error"));


        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }


    //CONFIGURATION

    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        // ✅ Initialize your JSON test data here
        testData = new JsonReader("register-data");
    }

    @BeforeMethod
    public void setup(){
        driver= new GUIDriver();
        new NavigationBarComponent(driver).navigate();
//        driver.browser().closeExtensionTab();
    }
    @AfterMethod
    public void tearDown(){
        driver.quitDriver();
    }
}
