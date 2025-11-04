package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.utils.DataReader.PropertyReader;
import com.automationexercise.utils.Logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {

    private GUIDriver driver;
    public NavigationBarComponent(GUIDriver driver){
        this.driver=driver;
    }


    //Locators

    private final By homeButton = By.xpath("//a[normalize-space()='Home']");
    private final By productsButton = By.cssSelector("a[href='/products']");
    private final By cartButton = By.cssSelector("a[href='/view_cart']");
    private final By SignupLoginButton = By.cssSelector("a[href='/login']");
    private final By TestCasesButton = By.cssSelector("a[href='/test_cases']");
    private final By APITestingButton = By.cssSelector("a[href='/api_list']");
    private  final By VideoTutorialsButtons = By.cssSelector("a[href='https://www.youtube.com/c/AutomationExercise']");
    private final By ContactUsButton = By.cssSelector("a[href='/contact_us']");
    private final By HomePageLabel = By.cssSelector("div[class='item active'] span:nth-child(1)");
    private final By deleteAccountButton = By.cssSelector("a[href='/delete_account']");
    private final By LogOutButton = By.cssSelector("a[href='/logout']");
    private final By UserLabel = By.cssSelector("ul[class='nav navbar-nav'] li a b");
    //Actions

    @Step("Navigate to Home Page")
    public NavigationBarComponent navigate(){
        driver.browser().NavigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return  this;
    }

    @Step("Click on Home Button")
    public  NavigationBarComponent ClickHomeButton(){
        driver.element().click(homeButton);
        return  this;
    }

    @Step("Click on Products button")
    public ProductsPage ClickOnProductButton(){
        driver.element().click(productsButton);
        return new ProductsPage(driver);
    }

    @Step("Click on CartButton")
    public CartPage ClickonCartButton(){
        driver.element().click(cartButton);
        return new CartPage(driver);
    }

    @Step("Click on SignupLoginButton")
    public SignUpPage ClickOnSignupButton(){
        driver.element().click(SignupLoginButton);
        return new SignUpPage(driver);
    }

    @Step("Click on TestCaseButton")
    public TestCasePage TestCaseButton(){
        driver.element().click(TestCasesButton);
        return new TestCasePage(driver);
    }

    @Step("Click on APITestingButton")
    public  APITestingPage ClickOnAPITestingButton(){
        driver.element().click(APITestingButton);
        return new APITestingPage(driver);
    }

    @Step("Click on VideoTutorialsButtons")
    public VideoTutorialsPage ClickVideoTutorialsButton(){
        driver.element().click(VideoTutorialsButtons);
        return new VideoTutorialsPage(driver);
    }

    @Step("Click on ContactUsButton")
    public ContactUsPage ClickOnContactUsButton(){
        driver.element().click(ContactUsButton);
        return  new ContactUsPage(driver);
    }

    @Step("Click on DeleteAccountButton")
    public DeleteAccountPage ClickOnDeleteAccountButton(){
        driver.element().click(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }
    @Step("Click on LogoutButton")
    public LogOutPage clickOnLogOutPage(){
        driver.element().click(LogOutButton);
        return new LogOutPage(driver);
    }
    //Validations

    @Step("Verify Home Page Label")
    public NavigationBarComponent verifyHomePageLabel(){
        driver.verification().isElementVisible(HomePageLabel);
        return this;
    }

    @Step("Verify User Label")
    public NavigationBarComponent verifyUserLAbel(String ExpectedName){
       String ActualName= driver.element().getText(UserLabel);
        LogsManager.info("Verifying user Label : "+ActualName);
        driver.verification().Equals(ActualName,ExpectedName,"User Label Does not match, Expected : "+ExpectedName +",Actual: "+ActualName);
        return this;
    }
}
