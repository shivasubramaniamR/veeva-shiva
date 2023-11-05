package com.veeva.automation.framework.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	
	private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * 
     * @param driver - browser driver
     */
    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * 
     * @param element - WebElement needs to be passed
     */
    public void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            System.out.println("Unable to click on the element: " + e.getMessage());
        }
    }


    /**
     * 
     * @param element  - WebElement needs to be passed
     * @param text - data that needs to be entered
     */
    public void enterText(WebElement element, String text) {
        try {
            WebElement elementToType = wait.until(ExpectedConditions.visibilityOf(element));
            elementToType.clear(); // Clear the field before typing 
            elementToType.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Unable to enter text in the element: " + e.getMessage());
        }
    }

/**
 * 
 * @param element - WebElement needs to be passed
 */
    public void hoverOverElement(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            System.out.println("Unable to hover over the element: " + e.getMessage());
        }
    }

    /**
     * 
     * @param cookieAcceptButton - WebElement needs to be passed
     */
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
    
    /**
     * 
     * @param list - A list of WebElements to be passed 
     * @return - returns an integer
     */
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
    
    /**
     * 
     * @param elements - A List of WebElements
     * @param daysCount - number of days to validate
     * @return - returns an integer
     */
    public int countElementsOlderThanDays(List<WebElement> elements, int daysCount) {
        int count = 0;
        
        try {
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
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
        
        return count;
    }
    
    /**
     * 
     * @param slides - WebElement needs to be passed
     * @return - returns a list of Long Data Type Values
     */
    public List<Long> calculateSlideDurations(List<WebElement> slides) {
        List<Long> slideDurations = new ArrayList<>();
       
        for (int i = 0; i < slides.size(); i++) {
            WebElement currentSlide = slides.get(i);
            
            // Wait for the current slide to become active.
            wait.until(ExpectedConditions.attributeToBe(currentSlide, "aria-selected", "true"));
            long startTime = System.currentTimeMillis();

            // If it's the last slide, we need to wait for the first slide to become active again
            int nextSlideIndex = (i + 1) % slides.size();
            WebElement nextSlide = slides.get(nextSlideIndex);

            // Wait for the next slide to become active
            wait.until(ExpectedConditions.attributeToBe(nextSlide, "aria-selected", "true"));
            long endTime = System.currentTimeMillis();

            // Calculate the duration and add it to the list.
            long duration = endTime - startTime;
            long durationInSeconds = Math.round(duration / 1000.0);
            slideDurations.add(durationInSeconds);

            // After the next slide becomes active, wait for the current slide to not be active anymore before continuing
            if (i < slides.size() - 1) {
                wait.until(ExpectedConditions.attributeToBe(currentSlide, "aria-selected", "false"));
            }
        }

        return slideDurations;
    }
    
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    /**
     * 
     * @param linkElements - A list of WebElements
     * @return - returns a list of String
     */
    public List<String> findAllHyperlinks(List<WebElement> linkElements) {
    	List<String> hyperlinks = new ArrayList<>();
        for (WebElement linkElement : linkElements) {
            String href = linkElement.getAttribute("href");
            hyperlinks.add(href);
        }
        
        return hyperlinks;
    }
    
    /**
     * 
     * @param hyperlinks - A list of Strings
     * @return - returns a Set of Strings
     */
    public Set<String> reportDuplicateHyperlinks(List<String> hyperlinks) {
        Set<String> uniqueLinks = new HashSet<>();
        Set<String> duplicateLinks = new HashSet<>();
        
        for (String link : hyperlinks) {
            if (!uniqueLinks.add(link)) {
                duplicateLinks.add(link);
            }
        }
        
        return duplicateLinks;
    }

    
    



}
