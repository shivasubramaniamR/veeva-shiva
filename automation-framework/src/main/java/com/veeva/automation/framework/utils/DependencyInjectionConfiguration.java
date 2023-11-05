package com.veeva.automation.framework.utils;

import org.openqa.selenium.WebDriver;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import com.veeva.automation.framework.driver.WebDriverFactory;

public class DependencyInjectionConfiguration {

    private final MutablePicoContainer container;
    private final WebDriver driver;
    
    public DependencyInjectionConfiguration() {
        container = new DefaultPicoContainer();
        
        container.addComponent(WebDriverFactory.class);
        
        WebDriverFactory webDriverFactory = container.getComponent(WebDriverFactory.class);
        this.driver = webDriverFactory.getDriver();
        
        container.addComponent(WebDriver.class, this.driver);
        container.addComponent(PageObjectFactory.class, new PageObjectFactory(driver, container));
        container.addComponent(WebDriverUtils.class, new WebDriverUtils(this.driver));
            
    }
    
    public WebDriver getDriver() {
    	return container.getComponent(WebDriver.class);
    }
    
    public WebDriverUtils getWebDriverUtils() {
        return container.getComponent(WebDriverUtils.class);
    }


    /**
     * 
     * @param <T>  - Generic Type to handle all types and also to ensure Type Checks
     * @param pageClass - Page Class needs to be passed
     * @return
     */
    public <T> T getPage(Class<T> pageClass) {
        PageObjectFactory pageObjectFactory = this.container.getComponent(PageObjectFactory.class);
        return pageObjectFactory.create(pageClass);
    }

   
}
