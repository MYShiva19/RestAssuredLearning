package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIEndPointsEnum;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class StepDefinition extends Utils{
	
	 RequestSpecification req;
	 ResponseSpecification resspec;
	 Response response;
	 RequestSpecification res;
	 TestDataBuild tdb = new TestDataBuild();
	 static String placeId;
	 //Static Payload
//	@Given("Add Place Payload")
//	public void add_place_payload() throws IOException {
//		 
//		 
//		res=given().spec(requestSpecification())
//		.body(tdb.addPlacePayload());
//
//		
//	}
	//Dynamic Payload
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		res=given().spec(requestSpecification())
				.body(tdb.addPlacePayload(name, language, address));
	}
	
//	@When("User calls AddPlaceAPI with POST http request")
//	public void user_calls_add_place_api_with_post_http_request() {
//		
//		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//		response =res.when().post(APIEndPointsEnum.ADD_PLACE_API.getValue()).
//		then().spec(resspec).extract().response();
//
//		
//		
//	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		res=given().spec(requestSpecification())
				.body(tdb.deletePlacePayload(placeId));
		
		
	}
	
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String endPoint, String httpMethod) {
		
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(endPoint.equalsIgnoreCase("AddPlaceAPI") && httpMethod.equalsIgnoreCase("Post")) {
		
		response =res.when().post(APIEndPointsEnum.ADD_PLACE_API.getValue()).
		then().spec(resspec).extract().response();
		}
		
		else if(endPoint.equalsIgnoreCase("GetPlaceAPI") && httpMethod.equalsIgnoreCase("Get")) {
		response =res.when().get(APIEndPointsEnum.GET_PLACE_API.getValue()).
		then().spec(resspec).extract().response();			
		}	
		
		else if(endPoint.equalsIgnoreCase("DeletePlaceAPI") && httpMethod.equalsIgnoreCase("Delete")) {
			response =res.when().delete(APIEndPointsEnum.DELETE_PLACE_API.getValue()).
			then().spec(resspec).extract().response();
		}	
	}
	
	
	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
	   assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {	
		assertEquals(retrieveResponseValue(response.asString(), key), value);
	}
	
	@Then("Verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String endPoint) throws IOException {
		placeId = retrieveResponseValue(response.asString(), "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", placeId);		
		user_calls_with_http_request(endPoint, "get");
	    
	}

}
