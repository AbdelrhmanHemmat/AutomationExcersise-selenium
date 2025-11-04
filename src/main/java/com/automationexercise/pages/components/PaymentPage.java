package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.security.PublicKey;

public class PaymentPage {

    private final GUIDriver driver;

    public PaymentPage(GUIDriver driver){
    this.driver=driver;    
    }

    //vars
    private String OrderPageEndPoint = "/payment";
    //Locators
    private final By NameOnCard = By.cssSelector("input[name='name_on_card']");
    private final By CardNumber = By.cssSelector("input[name='card_number']");
    private final By CVC = By.cssSelector("input[placeholder='ex. 311']");
    private final By ExpirationMonth = By.cssSelector("input[placeholder='MM']");
    private final By ExpirationYear = By.cssSelector("input[placeholder='YYYY']");
    private final By PayAndConfirmOrder = By.cssSelector("#submit");
    private final By OrderPlacedLabel = By.cssSelector("h2[class='title text-center'] b");
    private final By invoiceDownloadButton = By.cssSelector(".btn.btn-default.check_out");
    private final By ContinueButton = By.cssSelector(".btn.btn-primary");
    //Actions

    @Step("Fill Card Information")
    public   PaymentPage fillCardInfo(String nameOnCard, String cardNumber, String cardCvc, String cardMonthExpiration, String cardYearExpiration){
        driver.element().type(NameOnCard, nameOnCard)
                .type(CardNumber, cardNumber)
                .type(CVC, cardCvc)
                .type(ExpirationMonth, cardMonthExpiration)
                .type(ExpirationYear, cardYearExpiration)
                .click(PayAndConfirmOrder);
        return this;
    }
    @Step("Click on Download Invoice Button")
    public PaymentPage ClickOnDownLoadInvoiceButton(){
        driver.element().click(invoiceDownloadButton);
        return this;
    }

    //validations
    @Step("Verify payment success Message")
    public PaymentPage verifyPaymentSuccessMessage(String expectedMessage){
        driver.verification().Equals(driver.element().getText(OrderPlacedLabel),expectedMessage,"Payment Success Message");
        return this;
    }

    @Step("verify Invoice DownloadedSuccessfully")
    public PaymentPage verifyDownloadedFile(String invoiceName) {
        driver.verification().assertFileExists(invoiceName, "File is not existed");
        return this;
    }
}
