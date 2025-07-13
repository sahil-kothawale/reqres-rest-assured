package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import models.request.*;
import models.response.*;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import utilities.*;


public class UserSteps {
	
	private String endpoint;
	private Response response;
	private CreateUserRequest createUserRequest;
	private CreateUserResponse createUserResponse;
	private UpdateUserResponse updateUserResponse;
	
	@Given("login endpoint is {string}")
	public void loginEndpoint(String endpointUrl) {
		endpoint = endpointUrl;
	}
	
	@When("a GET request is sent")
	public void getRequestIsSent() {
		response = when().get(endpoint);
	}
	
	@When("user loads create user request body from {string}")
	public void loadCreateUserRequestBodyFromJsonFile(String fileName) {
		createUserRequest = JsonHelper.convertJsonFileToObject(fileName, CreateUserRequest.class);
	}
	
	@When("a POST request for creating user is sent")
	public void postRequestForCreatingUserIsSent() {
		response = given()
				.body(createUserRequest)
				.when()
				.post(endpoint);
	}
	
	@When("update the user request body as:")
	public void updateTheUserRequestBodyAs(DataTable table) {
		List<Map<String, String>> rows = table.asMaps();
		Map<String, String> row = rows.get(0);
		createUserRequest.setName(row.get("name"));
		createUserRequest.setJob(row.get("job"));
	}
	
	@When("a PUT request for updating user is sent")
	public void aPutRequestForUpdatingUserIsSent() {
		response = given()
				.body(createUserRequest)
				.when()
				.put(endpoint);
	}
	
	@When("a DELETE request for deleting user is sent")
	public void aDeleteRequestForDeletingUserIsSent() {
		response = given()
				.when()
				.delete(endpoint);
	}
	
	@Then("the response status code should be {int}")
	public void responseStatusCodeShouldBe(int responseCode) {
		response.then().statusCode(responseCode).log().all();
	}
	
	@Then("reponse body is stored in {string}.json file")
	public void reponseBodyIsStoredInResponseJsonFile(String fileName) {
		String responseBody = response.getBody().asPrettyString();
		JsonHelper.writeResponseToJsonFile(responseBody, fileName);
		switch(fileName) {
		case "CreateUserResponse":
			createUserResponse = response.as(CreateUserResponse.class);
			break;
		case "UpdateUserResponse":
			updateUserResponse = response.as(UpdateUserResponse.class);
			break;
		default:
			throw new IllegalArgumentException("Invalid file: " + fileName);
		}
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
	
	@Then("the response body should contain new generated id")
	public void responseBodyShouldContainNewGeneratedId() {
		assertNotNull(createUserResponse.getId());
		assertFalse(createUserResponse.getId().isEmpty());
	}
}
