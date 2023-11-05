package pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.veeva.automation.framework.utils.CSVUtility;
import com.veeva.automation.framework.utils.StepLogger;
import com.veeva.automation.framework.utils.WebDriverUtils;

public class DerivedProduct2 {

	private WebDriver driver;
	private final WebDriverUtils webDriverUtils;
	private List<String> hyperlinks;

	/**
	 * 
	 * @param driver  - Reference for the browser
	 * @param webDriverUtils - Reference for the driver utilities
	 */
	public DerivedProduct2(WebDriver driver, WebDriverUtils webDriverUtils) {
		this.driver = driver;
		this.webDriverUtils = webDriverUtils;
	}

	// Below are the Locators related to the DP2
	@FindBy(xpath = "//button[text()='I Accept']")
	private WebElement cookieButton;

	@FindBy(xpath = "//footer[@data-testid='footer']//a")
	private List<WebElement> footerLinks;
	
	/***
	 * 
	 *  @param url - This is the URL for the Derived Product 2
	 */
	public void navigateToDP2HomePage(String url) {
		driver.get(url);
		webDriverUtils.acceptCookiesIfDisplayed(cookieButton);
		webDriverUtils.closeAlerts();
	}

	public void scrollToFooter() {
		webDriverUtils.scrollToBottom();
	}

	public void findAllHyperlinks() {
		hyperlinks=webDriverUtils.findAllHyperlinks(footerLinks);
		StepLogger.setStepMessage("HyperLinks found: " + hyperlinks);
	}
	
	/**
	 * 
	 * @param filePath - Path of the File where the CSV file should be written
	 */

	public void writeLinksToCSV(String filePath) {
		CSVUtility.writeLinksToCSV(hyperlinks, filePath);
		StepLogger.setStepMessage("HyperLinks written to CSV File");
		
	}

	public void reportDuplicateHyperlinks() {
		Set<String> duplicateLinks = webDriverUtils.reportDuplicateHyperlinks(hyperlinks);
		if(!duplicateLinks.isEmpty())
			throw new AssertionError("Expected No Duplicate Links but Found Duplicate links"  + duplicateLinks);
		StepLogger.setStepMessage("No Duplicate HyperLinks Found");	
		
	}

	

	
}
