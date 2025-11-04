package com.automationexercise.tests.UI;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.drivers.UITest;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.pages.components.ProductsPage;
import com.automationexercise.tests.BaseTest;
import com.automationexercise.utils.DataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("cart Managment")
@Feature("UI Cart Details")
@Story("Cart Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("Hemmat")
@UITest
public class CartTest extends BaseTest {

    //Tests

    @Test
    public void verifyProductDetailsOnCart()
    {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart()
                .verifyProductDetailsOnCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total")
                );
    }

    //Configuration

    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        // ✅ Initialize your JSON test data here
        testData = new JsonReader("Cart-data");
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
