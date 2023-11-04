package stepDef;


import org.testng.Assert;

import com.veeva.automation.framework.utils.DependencyInjectionConfiguration;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class HomePageSteps {

    private HomePage homePage;

    public HomePageSteps(DependencyInjectionConfiguration diConfig) {
    	        this.homePage = diConfig.getPage(HomePage.class);
    }

    @Given("I am on the website {string}")
    public void i_am_on_the_NBA_Warriors_website(String url) {
        homePage.navigateToHomePage(url);
    }

    @When("I view the homepage")
    public void i_view_the_homepage() {
        // Any actions if needed can be performed here
    }

    @Then("I should see the team logo")
    public void i_should_see_the_Warriors_logo() {
    	homePage.clickItem();
        Assert.assertTrue(homePage.isLogoDisplayed(), "The Warriors logo is not displayed.");
    }

    // Add more step definitions as required
}

