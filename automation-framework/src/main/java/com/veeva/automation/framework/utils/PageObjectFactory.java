package com.veeva.automation.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectFactory {

    private final WebDriver driver;

    public PageObjectFactory(WebDriver driver) {
        this.driver = driver;
    }

    public <T> T create(Class<T> pageClass) {
        T pageInstance = PageFactory.initElements(driver, pageClass);
        return pageInstance;
    }
}
