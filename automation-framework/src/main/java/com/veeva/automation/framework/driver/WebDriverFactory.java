package com.veeva.automation.framework.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

	private Map<String, BrowserDriver> driverMap;
	private String browser;
	Properties prop;
	FileInputStream fis;

	public WebDriverFactory() throws IOException {
		driverMap = new HashMap<>();
		driverMap.put("chrome", new ChromeDriverManager());
		driverMap.put("firefox", new FirefoxDriverManager());
		driverMap.put("edge", new EdgeDriverManager());

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("global.properties");
		Properties properties = new Properties();
		properties.load(inputStream);

		browser = System.getProperty("browser", "chrome");
	}

	public WebDriver getDriver() {

		return driverMap.get(browser).createDriver();
	}
}
