package com.automationexercise.tests.UI;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.drivers.UITest;
import com.automationexercise.pages.components.ProductsPage;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.tests.BaseTest;
import com.automationexercise.utils.DataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Products Management")
@Feature("UI Product Details")
@Story("Product Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("Hemmat")
@UITest
public class ProductDetailsTest extends BaseTest {

    @Test
    public void verifyProductDetailsTCWithoutLogin()
    {
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .verifyProductDetails(testData.getJsonData("product.name"),testData.getJsonData("product.price"));
    }

    @Test
    public void verifyReviewMessageTCWithoutLogin()
    {
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .addReview(testData.getJsonData("review.name"),testData.getJsonData("review.email"),testData.getJsonData("review.review"))
                .verifyReviewMsg(testData.getJsonData("messages.review"));
    }


    //Configurations
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("products-Details_data");
    }
    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
//        driver.browser().closeExtensionTab();
    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }

}