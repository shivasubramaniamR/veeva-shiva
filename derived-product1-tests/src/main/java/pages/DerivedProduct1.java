package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.veeva.automation.framework.utils.JsonDataLoader;
import com.veeva.automation.framework.utils.StepLogger;
import com.veeva.automation.framework.utils.WebDriverUtils;

public class DerivedProduct1 {

	private WebDriver driver;
	private final WebDriverUtils webDriverUtils;
	private static int slidesCount;
	private static List<String> actualValues;
	ArrayNode expectedTitles;
	private static List<Long> slideDurations;
	private static List<Long> expectedDurations;

	/**
	 * 
	 * @param driver         - Reference for the browser
	 * @param webDriverUtils - Reference for the driver utilities
	 */

	public DerivedProduct1(WebDriver driver, WebDriverUtils webDriverUtils) {
		this.driver = driver;
		this.webDriverUtils = webDriverUtils;
	}

	// Below are the Locators related to the DP1
	@FindBy(xpath = "//button[text()='I Accept']")
	private WebElement cookieButton;

	@FindBy(xpath = "//div[@id='__next']/main[1]/div[3]/div[1]/div[2]/div[2]/button")
	private List<WebElement> slides;

	/***
	 * 
	 * @param url - This is the URL for the Derived Product 2
	 */
	public void navigateToDP1HomePage(String url) {
		driver.get(url);
		webDriverUtils.acceptCookiesIfDisplayed(cookieButton);
		webDriverUtils.closeAlerts();
	}

	public void countSlides() {
		slidesCount = webDriverUtils.webElementsSize(slides);
		StepLogger.setStepMessage("Number of slides found: " + slides.size());
	}

	/**
	 * 
	 * @param expectedCount - This is the expected count for the slides
	 */
	public void verifyNumberOfSlides(int expectedCount) {

		if (slidesCount != expectedCount) {
			throw new AssertionError("Expected " + expectedCount + " slides, but found " + slidesCount);
		}
		StepLogger.setStepMessage(
				"Expected " + expectedCount + " slides, and found " + slidesCount + " Test Step Passed");
	}

	public void getSlideTitles() {
		actualValues = new ArrayList<>();
		for (WebElement element : slides) {
			actualValues.add(element.getText().trim());
		}

		StepLogger.setStepMessage("Slides Found: " + actualValues);

	}

	public void verifySlideTitles() {

		try {
			expectedTitles = JsonDataLoader.readJsonArrayFromFile(
					System.getProperty("user.dir") + "/src/test/resources/testData/TestCase3_DP1.json",
					"expectedTitles");
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean isMatch = JsonDataLoader.compareWebElementsWithJson(actualValues, expectedTitles);

		if (!isMatch) {
			throw new AssertionError("Expected " + expectedTitles + " slides, but found " + actualValues);
		}

		StepLogger.setStepMessage(
				"Expected " + expectedTitles + " slides, matches with " + actualValues + " Test Step Passed");

	}

	public void measureSlideDurations() {
		slideDurations = webDriverUtils.calculateSlideDurations(slides);
		StepLogger.setStepMessage("Slides Duration: " + slideDurations);
	}

	public void verifySlideDurations() {

		try {
			expectedDurations = JsonDataLoader.readExpectedDurations(
					System.getProperty("user.dir") + "/src/test/resources/testData/TestCase3_DP1.json");
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean isMatch = JsonDataLoader.compareDurations(slideDurations, expectedDurations);

		if (!isMatch) {
			throw new AssertionError("Expected " + expectedDurations + " of slides, but found " + slideDurations);
		}

		StepLogger.setStepMessage(
				"Expected " + expectedDurations + " of slides, matches with " + slideDurations + " Test Step Passed");

	}
}
