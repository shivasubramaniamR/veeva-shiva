package stepDef;


import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;

import com.veeva.automation.framework.utils.DependencyInjectionConfiguration;
import com.veeva.automation.framework.utils.JsonDataLoader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CoreProduct;

public class CoreProduct_Steps {
	
    private CoreProduct coreProduct;
    private JsonNode testData;
    

    public CoreProduct_Steps(DependencyInjectionConfiguration diConfig) {
        this.coreProduct = diConfig.getPage(CoreProduct.class);
        try {
            // Load the test data as a JsonNode
            this.testData = JsonDataLoader.loadTestData(System.getProperty("user.dir")+"/src/test/resources/testData/TestCase2_CP.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    //below are the steps for the TestCase1
    @Given("I am on the NBA Warriors homepage")
    public void iAmOnTheNBAWarriorsHomepage() {
    	coreProduct.navigateToHomePage(this.testData.get("url").asText());
    }

    @When("I navigate to the Men Jackets section")
    public void iNavigateToTheMensJacketsSection() {
    	coreProduct.navigateToMensJackets();
    }

    @Then("I collect all Jacket details from all pages")
    public void iCollectAllJacketsDetailsFromAllPages() {
    	coreProduct.collectJacketsDetails();
    }

    @And("I store the details in a text file")
    public void iStoreTheDetailsInATextFile() {
    	coreProduct.storeDetailsInFile();
    }

    @And("I attach the text file to the report")
    public void iAttachTheTextFileToTheReport() {
    	coreProduct.attachFileToReport();
    }
    
    
  //below are the steps for the TestCase2
    @When("I hover over the menu item {string}")
    public void iHoverOverTheMenuItem(String menuItem) {
    	coreProduct.hoverOnMenuItem(menuItem);
    }

    @And("I click on {string}")
    public void iClickOn(String menuItem) {
    	coreProduct.clickOnMenuItem(menuItem);
    }

    @Then("I count the total number of Video Feeds")
    public void iCountTheTotalNumberOfVideoFeeds() {
    	coreProduct.countVideoFeeds();
    }

    @And("I count the Video Feeds with a duration of {int} days or more")
    public void iCountTheVideoFeedsWithADurationOfDaysOrMore(int days) {
    	coreProduct.countVideoFeedsWithDuration(days);
    }

    

    
}

