package com.veeva.automation.framework.utils;


import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = {"com.veeva.automation.framework.hooks"},// path to step definitions
        plugin = {"pretty", "html:target/cucumber-reports",
        		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, // report plugins
        monochrome = true
)
public class BaseTestRunner extends AbstractTestNGCucumberTests {

     
     @Override
     @DataProvider(parallel = true)
     public Object[][] scenarios() {
         return super.scenarios();
     }
}


