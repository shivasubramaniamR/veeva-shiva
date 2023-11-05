package com.veeva.automation.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;

public class PageObjectFactory {

    private final WebDriver driver;
    private final PicoContainer container;

    public PageObjectFactory(WebDriver driver, PicoContainer container) {
        this.driver = driver;
        this.container = container;
    }

    public <T> T create(Class<T> pageClass) {
        // Instantiate the page object with the required constructor
        T pageInstance;
        try {
            // Assumes the page class has a constructor that takes a WebDriver and WebDriverUtils as arguments
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
