package com.automationexercise.listeners;

import com.automationexercise.FileUtils;
import com.automationexercise.drivers.UITest;
import com.automationexercise.drivers.WebDriverProvider;
import com.automationexercise.Media.ScreenShotManager;
import com.automationexercise.utils.DataReader.PropertyReader;
import com.automationexercise.utils.Logs.LogsManager;
import com.automationexercise.utils.reports.AllureAttachmentManager;
import com.automationexercise.utils.reports.AllureConstants;
import com.automationexercise.utils.reports.AllureEnvironmentManager;
import com.automationexercise.utils.reports.AllureReportGenerator;
import com.automationexercise.Validations.Validation;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

public class TestNGListener implements ISuiteListener, IExecutionListener, IInvokedMethodListener, ITestListener {
    public void onStart(ISuite suite) {
        suite.getXmlSuite().setName("Automation Exercise");
    }
    public void onExecutionStart() {
        LogsManager.info("Test Execution started");
        cleanTestOutputDirectories();
        LogsManager.info("Directories cleaned");
        createTestOutputDirectories();
        LogsManager.info("Directories created");
        PropertyReader.loadProperties();
        LogsManager.info("Properties loaded");
        AllureEnvironmentManager.setEnvironmentVariables();
        LogsManager.info("Allure environment set");
    }

    public void onExecutionFinish() {
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.generateReports(true);
        AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
        LogsManager.info("Test Execution Finished");
    }


    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            if (testResult.getInstance() instanceof UITest)
            {
                LogsManager.info("Test Case " + testResult.getName() + " started");
            }
            LogsManager.info("Test Case " + testResult.getName() + " started");
        }
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = null;

        if (method.isTestMethod()) {
            if (testResult.getInstance().getClass().isAnnotationPresent(UITest.class)) {
                if (testResult.getInstance() instanceof WebDriverProvider provider)
                    driver = provider.getWebDriver();

                // Take screenshot (already attaches to Allure)
                switch (testResult.getStatus()) {
                    case ITestResult.SUCCESS ->
                            ScreenShotManager.takeFullPageScreenshot(driver, "passed-" + testResult.getName());
                    case ITestResult.FAILURE ->
                            ScreenShotManager.takeFullPageScreenshot(driver, "failed-" + testResult.getName());
                    case ITestResult.SKIP ->
                            ScreenShotManager.takeFullPageScreenshot(driver, "skipped-" + testResult.getName());
                }
            }

            // Assert all soft validations
            Validation.assertAll(testResult);

            // Attach logs
            AllureAttachmentManager.attachLogs();
        }
    }



    public void onTestSuccess(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " passed");
    }

    public void onTestFailure(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " failed");
    }

    public void onTestSkipped(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " skipped");
    }


    // cleaning and creating dirs (logs, screenshots, recordings,allure-results)
    private void cleanTestOutputDirectories() {
        // Implement logic to clean test output directories
        FileUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());
        FileUtils.cleanDirectory(new File(ScreenShotManager.SCREENSHOTS_PATH));
       // FileUtils.cleanDirectory(new File(ScreenRecordManager.RECORDINGS_PATH));
        FileUtils.cleanDirectory(new File("src/test/resources/downloads/"));
        FileUtils.forceDelete(new File(LogsManager.LOGS_PATH +"logs.log"));
    }

    private void createTestOutputDirectories() {
        // Implement logic to create test output directories
        FileUtils.createDirectory(ScreenShotManager.SCREENSHOTS_PATH);
        //FileUtils.createDirectory(ScreenShotManager.RECORDINGS_PATH);
        FileUtils.createDirectory("src/test/resources/downloads/");

    }
}