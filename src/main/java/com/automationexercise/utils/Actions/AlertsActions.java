package com.automationexercise.utils.Actions;

import com.automationexercise.utils.Logs.LogsManager;
import com.automationexercise.utils.WaitManager;
import org.openqa.selenium.WebDriver;

public class AlertsActions {


    private final WebDriver driver;
    private final WaitManager waitManager;
    
    //initializing
    public AlertsActions(WebDriver driver){
        this.driver=driver;
        this.waitManager=new WaitManager(driver);
        }

        //Accept Allerts
        public void acceptAlert(){
            waitManager.fluentWait().until(d->{
                try{
                    d.switchTo().alert().accept();
                    return true;
                }catch (Exception e){
                    LogsManager.error("Failed to accept alert" , e.getMessage());
                    return false;
                }
            });
        }

        //Dismiss allerts
    public void DismisAlert(){
        waitManager.fluentWait().until(d->
        {
            try{
                d.switchTo().alert().dismiss();
                return true;
            }catch(Exception e){
                LogsManager.error("Failed to dismiss alert",e.getMessage());
                return false;
        }
        });
    }

    //get alert text
    public String getAlertText(){
        return waitManager.fluentWait().until(d->{
            try{
                String text = d.switchTo().alert().getText();
                return !text.isEmpty()?text:null;
            }catch (Exception e){
                LogsManager.error("Failed to get alert text",e.getMessage());
                return null;
            }
        });
    }

    //send text
    public void setAlertText(String text){

        waitManager.fluentWait().until(d->
        {
            try {
                d.switchTo().alert().sendKeys(text);
                return true;
            }catch (Exception e){
                LogsManager.error("Failed to set alert text",e.getMessage());
                return false;
            }
        });
    }

    }

