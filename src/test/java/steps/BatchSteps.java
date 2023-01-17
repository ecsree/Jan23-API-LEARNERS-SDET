package steps;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BatchSteps {

	RequestSpecification httprequest;
	public static Response response;
	public static Response response1;
	int actualresponseCode;
	public static int statuscode;
	public static List<Integer> batchIdList = new ArrayList<>();
	public static List<Response> batchResponseList = new ArrayList<>();
	public static List<Response> batchDeletemsgList = new ArrayList<>();
		
	@Given("A valid API url {string}")
	public void api_url(String url) {
		RestAssured.baseURI = url;
	}

                          
	@When("^User sent payload with batch_name as(.*) batch_description as(.*) and batch status as(.*) and send  request with (.*)$")
	public void user_sent_payload_with_batch_name_as_batch_name_batch_description_as_desc_and_batch_status_as_status_and_send_request_with_endpoint(String batchName, String desc, String status, String endpoint) {

		String payload  ="{\r\n"

								+ "\"batchName\": \""+batchName+"\",\r\n"
								+ "\"batchDescription\": \""+desc+"\",\r\n"
								+ "\"batchStatus\": \""+status+"\",\r\n"
								+ "\"batchNoOfClasses\": 12,\r\n"
								+ "\"programId\": 1939\r\n"
								+ "}\r\n"
								+ "}";

		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		response = httpRequest.body(payload).post(endpoint);

		System.out.println("Response Body is =>  " + response.asString());
		// getting batchID
		Object res = JSONValue.parse(response.asString());
		JSONObject requestProgram = (JSONObject) res;
		batchIdList.add(Integer.parseInt(requestProgram.get("batchId").toString()));
		System.out.println("batchIdList = " + batchIdList);
	}

	@Then("^The new batch is Created (.*)$")
	public void the_new_batch_is_created(int statusCode) {
		actualresponseCode = response.then().extract().statusCode();
		System.out.println("Response status code is:" + statusCode);
		Assert.assertEquals(statusCode, actualresponseCode);
		Assert.assertNotNull(batchIdList);
		System.out.println("Response Body is =>  " + response.asString());

	}
	
	@When("^User send payload with batch_name as (.*) batch_description as(.*) and batch status as(.*) and send  request with (.*)$")
	public void user_send_payload_with_batch_name_as_jan23_api_learners_sdet_sdet411_batch_description_as_rest_api_batch009_and_batch_status_as_active_and_send_request_with_batches(String name, String desc, String status, String endpoint) { {
		String payload ="{\r\n"
				+ "\"batchName\": \""+name+"\",\r\n"
				+ "\"batchDescription\": \""+desc+"\",\r\n"
				+ "\"batchStatus\": \""+status+"\",\r\n"
				+ "\"batchNoOfClasses\": 12,\r\n"
				+ "\"programId\": 1939\r\n"
				+ "}\r\n"
				+ "}";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		response = httpRequest.body(payload).put(endpoint + batchIdList.get(0));
		System.out.println("Response Body is => " + response.asString());
	}
	}
	@Then("^batch is Updated (.*)$")
	public void batch_is_updated(int code) {
		Assert.assertEquals(statuscode, actualresponseCode);
	}

	@When("User send a request with end point")
	public void user_send_a_request_with_end_point() {

		for(int i=0;i<batchIdList.size();i++) {
			RequestSpecification rs = RestAssured.given();
			response = rs.when().get("/batches/batchId/"+batchIdList.get(i));
			System.out.println(response.asString());
			batchResponseList.add(response);
		}
	}

	@Then("user should get valid response")
	public void user_should_get_valid_response() {
		for(int i=0;i<batchResponseList.size();i++) {
			response = batchResponseList.get(i);
			int actualresponseCode = response.then().extract().statusCode();
			Assert.assertEquals(200, actualresponseCode);
			System.out.println("Response Body is =>  " + response.asString());
		}
	}

	@When("User send a request with end point to delete a batch")
	public void user_send_a_request_with_end_point_to_delete_a_batch() {
		for(int i=0;i<batchIdList.size();i++) {
			RequestSpecification rs = RestAssured.given();
			response = rs.when().delete("/batches/"+ batchIdList.get(i));
			System.out.println(response.asString());
			batchDeletemsgList.add(response);
		}
	}
	@Then("the batch should get deleted")
	public void the_batch_should_get_deleted() {
		for(int i=0;i<batchDeletemsgList.size();i++) {
			response = batchDeletemsgList.get(i);
			int actualresponseCode = response.then().extract().statusCode();
			Assert.assertEquals(200, actualresponseCode);
			System.out.println("Response Body is =>  " + response.asString());
			ResponseBody body = response.getBody();
			String bodyStringValue = body.asString();
			Assert.assertTrue(bodyStringValue.contains("deleted Successfully!"));
		}
	}
}
