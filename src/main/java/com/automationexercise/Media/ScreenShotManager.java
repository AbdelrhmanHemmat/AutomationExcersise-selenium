package com.automationexercise.Media;

import com.automationexercise.utils.Logs.LogsManager;
import com.automationexercise.utils.Logs.TimeManager;
import com.automationexercise.utils.reports.AllureAttachmentManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShotManager {
    public static final String SCREENSHOTS_PATH = "test-output/Screenshots/";

    //take full page screenshot
    public static void takeFullPageScreenshot(WebDriver driver, String screenshotName) {
        //code to take full page screenshot
        try {
            //capture screenshot using takescreenshot
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            //save screen shot to a file to desired location
            File screenshotDest = new File(SCREENSHOTS_PATH + screenshotName + "-" + TimeManager.getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotDest);

            //TODO: attach the screenshot to allure
            AllureAttachmentManager.attachScreenshot(screenshotName,screenshotDest.getAbsolutePath());

            LogsManager.info("Screenshot captured: " + screenshotDest.getAbsolutePath());
        } catch (Exception e) {
            LogsManager.error("Failed to capture ScreenShot " + e.getMessage());
        }
    }

    //Take screenshot of a specific element
    public static void takeElementScreenShot(WebDriver driver, By elementLocator) {
        //Highlight the element if needed (not implemented here)
        //code to take full page screenshot
        try {
            //capture screenshot using takescreenshot
            String ariaName = driver.findElement(elementLocator).getAriaRole();
            File screenshotSrc = driver.findElement(elementLocator).getScreenshotAs(OutputType.FILE);

            //save screen shot to a file to desired location
            File screenshotDest = new File(SCREENSHOTS_PATH + ariaName + "-" + TimeManager.getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotDest);

            //TODO: attach the screenshot to allure
            LogsManager.info("Screenshot captured: " + screenshotDest.getAbsolutePath());

        } catch (Exception e) {
            LogsManager.error("Failed to capture ScreenShot " + e.getMessage());
        }
    }
}
