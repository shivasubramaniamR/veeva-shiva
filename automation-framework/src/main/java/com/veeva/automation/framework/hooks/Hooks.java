package com.veeva.automation.framework.hooks;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.veeva.automation.framework.utils.DependencyInjectionConfiguration;
import com.veeva.automation.framework.utils.ExtentReportManager;
import com.veeva.automation.framework.utils.StepLogger;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	private final WebDriver driver;

	/**
	 * 
	 * @param dependencyInjectionConfiguration - Inject the Dependency
	 */
	public Hooks(DependencyInjectionConfiguration dependencyInjectionConfiguration) {
		this.driver = dependencyInjectionConfiguration.getDriver();
	}

	/**
	 * 
	 * @param scenario - Scenario details to log the test start
	 */
	@Before
	public void beforeScenario(Scenario scenario) {

		ExtentReportManager.startTest(scenario.getName());
	}

	/**
	 * 
	 * @param scenario - Scenario details to log the test status
	 */

	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			ExtentReportManager.logToReport("Test Failed");
		} else {
			ExtentReportManager.logToReport("Test Passed");
		}
		// Now end the test which will also flush the results
		ExtentReportManager.endTest();

	}

	@After(order = 2)
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * 
	 * @param scenario - Scenario details to log all the test statistics
	 * @throws IOException
	 */
	@AfterStep
	public void afterEachStep(Scenario scenario) throws IOException {

		String customMessage = StepLogger.getStepMessage();

		if (customMessage != null && !customMessage.isEmpty()) {
			scenario.log(customMessage); 
		}

		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
		scenario.attach(fileContent, "image/png", "image");
		StepLogger.clearStepMessage();
	}

}
