package com.automationexercise.utils.Actions;

import com.automationexercise.utils.Logs.LogsManager;
import com.automationexercise.utils.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrameActions {

    private final WebDriver driver;
    private final WaitManager waitManager;

    //initializing

    public FrameActions(WebDriver driver){
        this.driver = driver;
        this.waitManager= new WaitManager(driver);
    }

    //switch by index
    public  void switchToFrameByIndex(int index){
        waitManager.fluentWait().until(d->{
            try {
                d.switchTo().frame(index);
                LogsManager.info("Switched to frame using index: "+index);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    //switch using name or id
    public void SwitchToFrameByNameOrID(String NameOrID){
        waitManager.fluentWait().until(d->
        {
            try {
             d.switchTo().frame(NameOrID);
             LogsManager.info("Switched to frame using Name orID: "+NameOrID);
             return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    //Switch to frame by webelement
    public  void switchToFrameByWebElement(By FrameLocator){
        waitManager.fluentWait().until(d->{
            try {
                d.switchTo().frame(d.findElement(FrameLocator));
                LogsManager.info("Switched to frame using locator: "+FrameLocator);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    //Swithch back to the default content
    public void SwithToDefaultContent(){
        waitManager.fluentWait().until(d->
        {
            try {
                d.switchTo().defaultContent();
                LogsManager.info("Switched back to default content");
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    //
}