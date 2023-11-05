package com.veeva.automation.framework.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	
	private final WebDriver driver;
    private final WebDriverWait wait;

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // You can adjust the timeout value as needed.
    }

    public void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            System.out.println("Unable to click on the element: " + e.getMessage());
        }
    }


    public void enterText(WebElement element, String text) {
        try {
            WebElement elementToType = wait.until(ExpectedConditions.visibilityOf(element));
            elementToType.clear(); // Clear the field before typing if needed
            elementToType.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Unable to enter text in the element: " + e.getMessage());
        }
    }


    public void hoverOverElement(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            System.out.println("Unable to hover over the element: " + e.getMessage());
        }
    }

    public void acceptCookiesIfDisplayed(WebElement cookieAcceptButton) {
        try {
            if (cookieAcceptButton.isDisplayed()) {
                WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
                acceptCookiesButton.click();
                System.out.println("Cookies accepted.");
            }
        } catch (Exception e) {
            System.out.println("Cookie acceptance button either not found or not clickable: " + e.getMessage());
        }
    }


    
    public void closeAlerts() {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            alertWait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("No alerts to close.");
        }
    }
    
    
    public int webElementsSize(List<WebElement> list) {
        int size = 0;
        try {
            
            if (list != null) {
                size = list.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return size;
    }
    
    public int countElementsOlderThanDays(List<WebElement> elements, int daysCount) {
        int count = 0;
        
        for (WebElement element : elements) {
            String text = element.getText().trim();
            // Check if the text ends with 'd' to signify days.
            if (text.endsWith("d")) {
                // Extract the number of days.
                int days = Integer.parseInt(text.substring(0, text.length() - 1));
                // Increment count if days are greater than or equal to 3.
                if (days >= daysCount) {
                    count++;
                }
            }
        }
        
        return count;
    }




}
