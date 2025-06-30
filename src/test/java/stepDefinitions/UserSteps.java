package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import utilities.*;


public class UserSteps {
	
	private String endpoint;
	private Response response;
	
	@Given("login endpoint is {string}")
	public void loginEndpoint(String endpointUrl) {
		endpoint = endpointUrl;
	}
	
	@When("a GET request is sent")
	public void getRequestIsSent() {
		response = when().get(endpoint);
	}
	
	@Then("the response status code should be {int}")
	public void responseStatusCodeShouldBe(int responseCode) {
		response.then().statusCode(responseCode);
	}
	
	@Then("reponse body is stored in Response.json file")
	public void reponseBodyIsStoredInResponseJsonFile() {
		String responseBody = response.getBody().asPrettyString();
		ResponseHelper.writeResponseToJsonFile(responseBody, "Response");
	}
	
	@Then("the response body should contain user with id {int}")
	public void responseBodyShouldContainUserWithId(int id) {
		response.then().body("data.id", equalTo(id));
	}
	
	@Then("the response body should contain the following:")
	public void responseBodyShouldContainTheFollowing(DataTable table) {
		Map<String, String> data = table.asMaps().get(0);
		for(Map.Entry<String, String> entry: data.entrySet()) {
			response.then().body("data." + entry.getKey(), equalTo(entry.getValue()));
		}
	}
}
