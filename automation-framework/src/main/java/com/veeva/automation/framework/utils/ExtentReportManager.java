package com.veeva.automation.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
    
    public static void setTest(ExtentTest testInstance) {
        test = testInstance;
    }

    public static void logToReport(String message) {
        if (test != null) {
            test.log(Status.INFO, message);
        }
    }
}

