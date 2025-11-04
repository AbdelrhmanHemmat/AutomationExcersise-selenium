package com.automationexercise.drivers;

import com.automationexercise.utils.Actions.*;
import com.automationexercise.utils.Logs.LogsManager;
import com.automationexercise.Validations.Validation;
import com.automationexercise.Validations.Verification;
import com.automationexercise.utils.DataReader.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIDriver {
    private final String browser;
    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GUIDriver() {
        browser = PropertyReader.getProperty("browserType"); // guaranteed non-null
        LogsManager.info("Initializing GUIDriver with browser: " + browser);

        Browser browserType;
        try {
            browserType = Browser.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid browserType in properties: " + browser, e);
        }

        LogsManager.info("Starting driver for browser: " + browserType);
        AbstractDriver abstractDriver = browserType.getDriverFactory();
        WebDriver driver = ThreadGuard.protect(abstractDriver.createDriver());
        driverThreadLocal.set(driver);
    }

    public ElementAction element() {
        return new ElementAction(get());
    }

    public BrowserActions browser() {
        return new BrowserActions(get());
    }

    public FrameActions frame() {
        return new FrameActions(get());
    }

    public AlertsActions alert() {
        return new AlertsActions(get());
    }

    public Validation validation() {
        return new Validation(get());
    }

    public Verification verification() {
        return new Verification(get());
    }

    public WebDriver get() {
        return driverThreadLocal.get();
    }

    public void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
