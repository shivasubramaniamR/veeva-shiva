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
        System.out.println("Logging details - " + message);
        if (test != null) {
            try {
                test.log(Status.INFO, message);
            } catch (Exception e) {
                System.err.println("Error logging to ExtentReport: " + e.getMessage());
                // You might want to log the stack trace or handle the exception as needed
            }
        } else {
            System.err.println("ExtentTest instance is null. Unable to log to ExtentReports.");
        }
    }

    // This should be called after all the tests have completed
    public static void finalizeReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    
    
}
