package com.employeeapi.testCases;

import static io.restassured.RestAssured.baseURI;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Post_SingleData extends TestBase{
	
	@Test
	public static void post_001() {
		RestAssured.baseURI = "https://reqres.in/";


	RequestSpecification httpRequest = RestAssured.given();
	
	//Here we created data which we send along with the post request
	JSONObject requestParams=new JSONObject();
	requestParams.put("name", "Nick");
	requestParams.put("job", "driver");
	
	//Add a header stating the request body is a json
	httpRequest.header("Content-Type","application/json");
	
	//Add the Json to the body of the request
	httpRequest.body(requestParams.toJSONString());
	
	// Response object
	Response response = httpRequest.request(Method.POST, "api/users");
	
	//Capture response body
	String responseBody=response.getBody().asString();
	 System.out.println("Response Body is:" +responseBody);
	
	//verify response
	 Assert.assertEquals(responseBody.contains("Nick"), true);	
	
	
	
	
	
	
	
	
	}
}
