package com.automationexercise.utils.Logs;

public class TimeManager {

    //screenshot -Logs-reports
    public static String getTimeStamp(){
        return new java.text.SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new java.util.Date());
    }

    //unique timestamp for each data
    public static String getSimpleTimeStamp(){
       //return a timestamp in numbers only
        return String.valueOf(System.currentTimeMillis());
    }

}
