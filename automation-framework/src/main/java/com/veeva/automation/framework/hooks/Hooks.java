package com.veeva.automation.framework.hooks;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.veeva.automation.framework.utils.DependencyInjectionConfiguration;
import com.veeva.automation.framework.utils.ExtentReportManager;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	private ExtentTest test;
	private final WebDriver driver;
	
	public Hooks(DependencyInjectionConfiguration dependencyInjectionConfiguration) {
        this.driver = dependencyInjectionConfiguration.getDriver();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
    	
        test = ExtentReportManager.getReporter().createTest(scenario.getName());
        ExtentReportManager.setTest(test);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot...
            // Add it to the report...
            test.log(Status.FAIL, "Test Failed");
        } else {
            test.log(Status.PASS, "Test Passed");
        }
        ExtentReportManager.getReporter().flush();
        
    }
    
    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @AfterStep
    public void afterEachStep(Scenario scenario) throws IOException {
    	System.out.println("Inside screenshot step");
       
    	File sourcePath= 	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
		scenario.attach(fileContent, "image/png", "image");
    }

}
