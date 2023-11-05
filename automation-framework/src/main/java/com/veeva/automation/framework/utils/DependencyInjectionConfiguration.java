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
        //this.pageObjectFactory = new PageObjectFactory(this.driver,container);
        container.addComponent(PageObjectFactory.class, new PageObjectFactory(driver, container));
        //container.addComponent(PageObjectFactory.class, new PageObjectFactory(this.driver));
        container.addComponent(WebDriverUtils.class, new WebDriverUtils(this.driver));
        

         
    }
    
    public WebDriver getDriver() {
       // return this.driver;
    	return container.getComponent(WebDriver.class);
    }
    
    public WebDriverUtils getWebDriverUtils() {
        return container.getComponent(WebDriverUtils.class);
    }


    public <T> T getPage(Class<T> pageClass) {
        PageObjectFactory pageObjectFactory = this.container.getComponent(PageObjectFactory.class);
        return pageObjectFactory.create(pageClass);
    }

    // Other configurations...
}
