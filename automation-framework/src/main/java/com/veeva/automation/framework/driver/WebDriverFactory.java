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
        
          
//        fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//global.properties");
//        prop = new Properties();
//		prop.load(fis);
        
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("global.properties");
		Properties properties = new Properties();
		properties.load(inputStream);

		
		
       // browser=properties.getProperty("browser");
        browser = System.getProperty("browser", "chrome");

        System.out.println("browser Name== "+browser);
    }

    public WebDriver getDriver() {
    	
        return driverMap.get(browser).createDriver();
    }
}

