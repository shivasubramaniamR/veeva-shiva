//package com.veeva.automation.framework.utils;
//
//import org.openqa.selenium.WebDriver;
//import org.picocontainer.DefaultPicoContainer;
//import org.picocontainer.MutablePicoContainer;
//
//import com.veeva.automation.framework.driver.WebDriverFactory;
//
//public class DependencyInjectionConfiguration2 {
//
//    private final MutablePicoContainer container;
//
//    public DependencyInjectionConfiguration2(String browserType) {
//        container = new DefaultPicoContainer();
//        
//        WebDriverFactory webDriverFactory = container.getComponent(WebDriverFactory.class);
//        WebDriver driver = webDriverFactory.getDriver();
//
//        // Register the WebDriver factory
//        container.addComponent(WebDriverFactory.class,driver);
//
//        // Register the PageObjectFactory
//        container.addComponent(PageObjectFactory.class, new PageObjectFactory(container.getComponent(WebDriver.class)));
//        
//    }
//
//    public <T> T getPage(Class<T> pageClass) {
//        PageObjectFactory pageObjectFactory = container.getComponent(PageObjectFactory.class);
//        return pageObjectFactory.create(pageClass);
//    }
//
//    // Other configurations...
//}
