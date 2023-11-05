package stepDef;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;

import com.veeva.automation.framework.utils.DependencyInjectionConfiguration;
import com.veeva.automation.framework.utils.JsonDataLoader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DerivedProduct2;

public class DerivedProduct2_Steps {

	private DerivedProduct2 derivedProduct2;
	private JsonNode testData;

	public DerivedProduct2_Steps(DependencyInjectionConfiguration diConfig) {
		this.derivedProduct2 = diConfig.getPage(DerivedProduct2.class);
		try {
			// Load the test data as a JsonNode
			this.testData = JsonDataLoader
					.loadTestData(System.getProperty("user.dir") + "/src/test/resources/testData/TestCase4_DP2.json");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Given("I am on the DP2 home page")
	public void i_am_on_dp2_home_page() {
		derivedProduct2.navigateToDP2HomePage(this.testData.get("url").asText());
	}

	@When("I scroll down to the footer")
	public void i_scroll_down_to_the_footer() {
		derivedProduct2.scrollToFooter();
	}

	@Then("I find all hyperlinks in the footer")
	public void i_find_all_hyperlinks_in_the_footer() {
		derivedProduct2.findAllHyperlinks();
	}

	@And("I write the hyperlinks into a CSV file")
	public void i_write_hyperlinks_into_csv_file() {
		derivedProduct2
				.writeLinksToCSV(System.getProperty("user.dir") + "/src/test/resources/testData/footer_links.csv");
	}

	@And("I report if any duplicate hyperlinks are present")
	public void i_report_duplicate_hyperlinks() {
		derivedProduct2.reportDuplicateHyperlinks();
	}

}
