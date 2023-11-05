package com.veeva.automation.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
            
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
    
    /**
     * 
     * @param testName - Name of the Test
     */
    public static synchronized void startTest(String testName) {
        ExtentTest extentTest = getReporter().createTest(testName);
        test.set(extentTest);
    }

    /**
     * 
     * @param message - Custom message to log
     */
    public static void logToReport(String message) {
        System.out.println("Logging details - " + message);
        getTest().log(Status.INFO, message);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static synchronized void endTest() {
        if (getReporter() != null && getTest() != null) {
            getReporter().flush();
        }
        test.remove();
    }
}
