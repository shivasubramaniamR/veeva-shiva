package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // path to feature files
        glue = {"stepDef", "hooks"},// path to step definitions
        plugin = {"pretty", "html:target/cucumber-reports"}, // report plugins
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

     
     @Override
     @DataProvider(parallel = true)
     public Object[][] scenarios() {
         return super.scenarios();
     }
}

