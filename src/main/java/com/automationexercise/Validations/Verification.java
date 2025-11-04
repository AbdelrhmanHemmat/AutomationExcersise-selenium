package com.automationexercise.Validations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Hard assertion implementation using TestNG Assert.
 * If any assertion fails, the test stops immediately.
 */
public class Verification extends BaseAssertion {

    public Verification(WebDriver driver) {
        super(driver);
    }
    public Verification(){
        super();
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Override
    protected void assertEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
}
