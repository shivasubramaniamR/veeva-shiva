package stepDef;


import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;

import com.veeva.automation.framework.utils.DependencyInjectionConfiguration;
import com.veeva.automation.framework.utils.JsonDataLoader;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DerivedProduct1;

public class DerivedProduct1_Steps {
    private DerivedProduct1 derivedProduct1;
    private JsonNode testData;
    

    public DerivedProduct1_Steps(DependencyInjectionConfiguration diConfig) {
        this.derivedProduct1 = diConfig.getPage(DerivedProduct1.class);
        try {
            // Load the test data as a JsonNode
            this.testData = JsonDataLoader.loadTestData(System.getProperty("user.dir")+"/src/test/resources/testData/TestCase3_DP1.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    @Given("I am on the DP1 home page")
    public void iAmOnTheDP1HomePage() {
    	derivedProduct1.navigateToDP1HomePage(this.testData.get("url").asText());
    }

    @When("I count the number of slides below the Tickets Menu")
    public void iCountTheNumberOfSlidesBelowTheTicketsMenu() {
    	derivedProduct1.countSlides();
    }

    @Then("I should see expected number slides")
    public void iShouldSeeExpectedNumberOfSlides() {
    	derivedProduct1.verifyNumberOfSlides(this.testData.get("slides").asInt());
    }

    @When("I retrieve the title of each slide")
    public void iRetrieveTheTitleOfEachSlide() {
    	derivedProduct1.getSlideTitles();
    }

    @Then("the slide titles should match the expected test data")
    public void theSlideTitlesShouldMatchTheExpectedTestData() {
    	derivedProduct1.verifySlideTitles(); 
    }

    @When("I measure the duration of each slide")
    public void iMeasureTheDurationOfEachSlide() {
    	derivedProduct1.measureSlideDurations();
    }

    @Then("the slide durations should match the expected durations")
    public void theSlideDurationsShouldMatchTheExpectedDurations() {
    	derivedProduct1.verifySlideDurations();
    }

   

    

    
}

