package runner;

import org.testng.annotations.DataProvider;

import com.veeva.automation.framework.utils.BaseTestRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // path to feature files
        glue = {"stepDef"},// path to step definitions
        plugin = {"pretty", "html:target/cucumber-reports",
        		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, // report plugins
        monochrome = true
)
public class TestRunner extends BaseTestRunner {

	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
    
}

