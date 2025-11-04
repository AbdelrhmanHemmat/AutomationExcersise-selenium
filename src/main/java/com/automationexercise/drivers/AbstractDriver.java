package com.automationexercise.drivers;

import com.automationexercise.utils.DataReader.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.io.File;

public abstract  class AbstractDriver {

    protected  final String remoteHost = PropertyReader.getProperty("remoteHost");
    protected final  String remotePort = PropertyReader.getProperty("remotePort");
    //protected File haramBlurextensions = new File("extensions");
    public abstract WebDriver createDriver();

}
