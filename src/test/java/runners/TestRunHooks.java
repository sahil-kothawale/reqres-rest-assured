package runners;

import io.cucumber.java.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;
import io.restassured.RestAssured;

public class TestRunHooks {

	@Before
	public void setUpRequest() {
		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setBaseUri(ConfigReader.getProperty("base_url"))
				.addHeader("x-api-key", ConfigReader.getProperty("api_key"))
				.setContentType("application/json")
				.build();
		
		RestAssured.requestSpecification = reqSpec;
		System.out.println("Global request specification set up complete");
	}
}
