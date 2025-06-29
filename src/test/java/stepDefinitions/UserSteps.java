package stepDefinitions;

import static io.restassured.RestAssured.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utilities.ConfigReader;
import static org.hamcrest.Matchers.*;
import java.util.Map;

public class UserSteps {
	
	private String endpoint;
	private Response response;
	
	@Given("login endpoint is {string}")
	public void loginEndpoint(String endpointUrl) {
		endpoint = endpointUrl;
	}
	
	@When("a GET request is sent")
	public void getRequestIsSent() {
		String baseUrl = ConfigReader.getProperty("base_url");
		response = when().get(baseUrl + endpoint);
	}
	
	@Then("the response status code should be {int}")
	public void responseStatusCodeShouldBe(int responseCode) {
		response.then().statusCode(responseCode);
	}
	
	@Then("the response body should contain user with id {int}")
	public void responseBodyShouldContainUserWithId(int id) {
		response.then().body("id", equalTo(id));
	}
	
	@Then("the response body should contain the following:")
	public void responseBodyShouldContainTheFollowing(DataTable table) {
		Map<String, String> data = table.asMap();
		for(Map.Entry<String, String> entry: data.entrySet()) {
			response.then().body(entry.getKey(), equalTo(entry.getValue()));
		}
	}
}
