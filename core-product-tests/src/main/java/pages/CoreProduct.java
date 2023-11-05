package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.veeva.automation.framework.utils.StepLogger;
import com.veeva.automation.framework.utils.WebDriverUtils;

public class CoreProduct {

    private WebDriver driver;
    private final WebDriverUtils webDriverUtils;
    
    /**
	 * 
	 * @param driver         - Reference for the browser
	 * @param webDriverUtils - Reference for the driver utilities
	 */
    
    public CoreProduct(WebDriver driver,WebDriverUtils webDriverUtils) {
        this.driver = driver;
        this.webDriverUtils = webDriverUtils;
    }

    
    //below are the locators for TestCase 1 and TestCase 2 
    @FindBy(xpath = "(//span[text()='Shop'])[1]") 
    private WebElement shopMenu;
    
    @FindBy(xpath = "//li[@role='menuitem']//li[@role='menuitem']//a[@title=\"Men's\"]") 
    private WebElement shopMenuMen;
    
    @FindBy(xpath = "//button[text()='I Accept']") 
    private WebElement cookieButton;
    
    @FindBy(xpath = "(//span[text()='...'])[1]") 
    private WebElement elipsisButton;
    
    @FindBy(xpath = "(//a[@title='News & Features'])[1]") 
    private WebElement newsFeatures;
    
    @FindBy(xpath = "//div[@class='ColumnsComponents_container__YMzra']/div[div[div[h3[text()='VIDEOS']]]]/div/div/div/ul/li") 
    private List<WebElement> videosCount;
    
    @FindBy(xpath = "//div[@class='ColumnsComponents_container__YMzra']/div[div[div[h3[text()='VIDEOS']]]]/div/div/div/ul/li//time") 
    private List<WebElement> videosCountDaysCount;
    
    /***
	 * 
	 * @param url - This is the URL for the Derived Product 2
	 */

    public void navigateToHomePage(String url) {
        driver.get(url);
        webDriverUtils.acceptCookiesIfDisplayed(cookieButton);
        webDriverUtils.closeAlerts();
    }

    public void navigateToMensJackets() {
    	 webDriverUtils.hoverOverElement(shopMenu);
    	 webDriverUtils.hoverOverElement(shopMenuMen);
    }

    public void collectJacketsDetails() {
        // Logic to collect details of jackets from all paginated pages
    }

    public void storeDetailsInFile() {
        // Logic to store details in a text file
    }

    public void attachFileToReport() {
        // Logic to attach the file to the report
    }
    
    public void hoverOnMenuItem(String menuItem) {
        
    	webDriverUtils.hoverOverElement(elipsisButton);
    }

    public void clickOnMenuItem(String menuItem) {
    	webDriverUtils.clickElement(newsFeatures);
        
    }

    public void countVideoFeeds() {
    	
    	StepLogger.setStepMessage("Total Videos Count is "+webDriverUtils.webElementsSize(videosCount));
      }

    public void countVideoFeedsWithDuration(int days) {
    	StepLogger.setStepMessage("Total Videos Count that are "+days+" days old are "+webDriverUtils.countElementsOlderThanDays(videosCountDaysCount,days));
    }
}
