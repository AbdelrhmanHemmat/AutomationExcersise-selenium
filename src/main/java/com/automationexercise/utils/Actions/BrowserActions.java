package com.automationexercise.utils.Actions;

import com.automationexercise.utils.Logs.LogsManager;
import com.automationexercise.utils.WaitManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class BrowserActions {

    private final WebDriver driver;
    private WaitManager waitManager;

    public BrowserActions(WebDriver driver){
        this.driver = driver;
        this.waitManager = new WaitManager(driver); // ✅ Fix
    }

    public void maxmizeBrowser(){
        driver.manage().window().maximize();
    }

    public String getCurrentUrl(){
        String url = driver.getCurrentUrl();
        LogsManager.info("Current URL: " + url);
        return url;
    }

    public void NavigateTo(String URL){
        driver.get(URL);
        LogsManager.info("Navigated to URL : " + URL);
    }

    public void refreshCurrentPage(){
        driver.navigate().refresh();
    }

    public void closeCurrentWindow(){
        driver.close();
    }

    public void openNewWindow(){
        driver.switchTo().newWindow(WindowType.WINDOW);
    }

    public void closeExtensionTab() {
        String currentWindowHandle = driver.getWindowHandle();

        try {
            Thread.sleep(2000); // Give extension time to open
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitManager.fluentWait().until(d -> driver.getWindowHandles().size() > 1);

        if (driver.getWindowHandles().size() > 1) {
            Object[] handles = driver.getWindowHandles().toArray();
            driver.switchTo().window(handles[1].toString()).close();
            driver.switchTo().window(currentWindowHandle);
            LogsManager.info("Extension tab closed");
        } else {
            LogsManager.warn("No extension tab found to close");
        }
    }

}
