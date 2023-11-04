package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    private WebDriver driver;

    @FindBy(xpath = "(//span[text()='Tickets'])[1]")
    private WebElement warriorsLogo;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage(String url) {
    	System.out.println("driver details = "+driver);
        driver.get(url);
    }
    
    public void clickItem() {
    	warriorsLogo.click();
    }

    public boolean isLogoDisplayed() {
        return warriorsLogo.isDisplayed();
    }

    // Other methods
}
