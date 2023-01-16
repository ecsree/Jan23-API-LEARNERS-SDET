package steps;

import java.time.LocalDateTime;
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
import io.restassured.specification.RequestSpecification;

public class StepDefinitions {
	RequestSpecification request;
	public static Response respon;
	int actualresponseCode;
	int statuscode;
	public static List<Integer> programIdList = new ArrayList<>();;

	@Given("API url {string}")
	public void api_url(String url) {
		RestAssured.baseURI = url;
	}

	@When("^User set body with program_name as(.*) program_description as(.*) and program status as(.*) and send  request with (.*)$")
	public void user_set_body_with_program_name_as_programname_program_description_as_desc_and_program_status_as_status_and_send_request_with_endpoint(
			String programname, String desc, String status, String endpoint) {

		
		String payload = "{\r\n" + "\"programName\": \"" + programname + "\",\r\n" + "\"programDescription\": \"" + desc
				+ "\",\r\n" + "\"programStatus\": \"" + status + "\",\r\n" + "\"creationTime\": \""
				+ LocalDateTime.now() + "\",\r\n" + "\"lastModTime\": \"" + LocalDateTime.now() + "\"\r\n" + "}";
		RequestSpecification httprequest = RestAssured.given();
		httprequest.header("Content-Type", "application/json");
		Response respon = httprequest.body(payload).post(endpoint);
		statuscode = respon.getStatusCode();
		System.out.println("Responce status code is:" + statuscode);
		System.out.println("Response Body is =>  " + respon.asString());
		// getting programID
		Object res = JSONValue.parse(respon.asString());
		JSONObject requestProgram = (JSONObject) res;
		programIdList.add(Integer.parseInt(requestProgram.get("programId").toString()));
		System.out.println("programIdList = " + programIdList);
	}
	
	@Then("^The new Program is Created  (.*)$")
	public void the_new_program_is_created_statuscode(int code) {
		Assert.assertEquals(code, statuscode);

	}
}
