package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.veeva.automation.framework.utils.ExtentReportManager;
import com.veeva.automation.framework.utils.WebDriverUtils;

public class CoreProduct {

    private WebDriver driver;
    private final WebDriverUtils webDriverUtils;
    
    public CoreProduct(WebDriver driver,WebDriverUtils webDriverUtils) {
        this.driver = driver;
        this.webDriverUtils = webDriverUtils;
    }

    public void navigateToHomePage(String url) {
    	System.out.println("driver details = "+driver);
        driver.get(url);
        webDriverUtils.acceptCookiesIfDisplayed(cookieButton);
        webDriverUtils.closeAlerts();
    }
    
    @FindBy(xpath = "(//span[text()='Shop'])[1]") // Replace with actual selector
    private WebElement shopMenu;
    
    @FindBy(xpath = "//li[@role='menuitem']//li[@role='menuitem']//a[@title=\"Men's\"]") // Replace with actual selector
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
    
    

    // Other WebElements and methods...

    public void navigateToMensJackets() {
        // Click shop menu, then men's jackets
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
    	ExtentReportManager.logToReport("Total Videos Count is "+webDriverUtils.webElementsSize(videosCount));
    }

    public void countVideoFeedsWithDuration(int days) {
    	ExtentReportManager.logToReport("Total Videos Count that are 3 days old are "+webDriverUtils.countElementsOlderThanDays(videosCountDaysCount,days));
    }
}
