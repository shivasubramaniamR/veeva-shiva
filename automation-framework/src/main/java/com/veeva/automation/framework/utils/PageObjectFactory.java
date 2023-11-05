package com.veeva.automation.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.picocontainer.PicoContainer;

public class PageObjectFactory {

    private final WebDriver driver;
    private final PicoContainer container;

    /**
     * 
     * @param driver - browser driver
     * @param container - dependency injection
     */
    public PageObjectFactory(WebDriver driver, PicoContainer container) {
        this.driver = driver;
        this.container = container;
    }

    /**
     * 
     * @param <T> - Generic Type to handle all types and for type checks safety
     * @param pageClass - Page Class details
     * @return - returns a generic
     */
    public <T> T create(Class<T> pageClass) {
        T pageInstance;
        try {
            //page class has a constructor that takes a WebDriver and WebDriverUtils as arguments
            pageInstance = pageClass.getConstructor(WebDriver.class, WebDriverUtils.class)
                                    .newInstance(driver, container.getComponent(WebDriverUtils.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        // Initialize the fields annotated with @FindBy
        PageFactory.initElements(driver, pageInstance);
        return pageInstance;
    }
}
