package DataDrivenTestCases;

import static io.restassured.RestAssured.baseURI;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Post_DataDriven_DataProvider extends TestBase {
	
	@Test(dataProvider="userDataProvider")
	public static void post_001(String ename, String ejob) {
		RestAssured.baseURI = "https://reqres.in/";


	RequestSpecification httpRequest = RestAssured.given();
	
	//Here we created data which we send along with the post request
	JSONObject requestParams=new JSONObject();
	requestParams.put("name", ename);
	requestParams.put("job", ejob);
	
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
	 Assert.assertEquals(responseBody.contains(ename), true);	
			
	}
	
	@DataProvider(name="userDataProvider")
	String [][]getData()
	{
		String userdata[][]= {{"Mahbub","doctor"},{"Bikash","Palayer"},{"Rayan","Scientist"}};
		return userdata;
		
		
	}
	
}
