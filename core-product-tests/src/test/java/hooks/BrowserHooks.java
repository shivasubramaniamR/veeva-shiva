package hooks;

import org.openqa.selenium.WebDriver;
import org.picocontainer.MutablePicoContainer;

import com.veeva.automation.framework.utils.DependencyInjectionConfiguration;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class BrowserHooks {

    private WebDriver driver;

    public BrowserHooks(DependencyInjectionConfiguration diConfig) {
        this.driver = diConfig.getDriver();
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

