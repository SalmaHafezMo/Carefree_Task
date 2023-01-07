import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class VerifyAPI {
	
	
	//Validate that can Verify with Valid OTP code 
	@Test(priority=1)
	public void test01 () {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("mobile","966556677888");
		json.put("verification_code", "9531");
		request.body(json.toJSONString());
		//API URL
		Response response =  request.post("api/provider/v1/auth/verify");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Success verify."),true);
		
	}
	
	//Validate that cannot Verify with InValid OTP Code 
	@Test(priority=2)
	public void test02 () {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("mobile","966556677888");
		json.put("verification_code", "123");
		request.body(json.toJSONString());
		//API URL
		Response response =  request.post("api/provider/v1/auth/verify");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 422);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("err_auth_otp_does_not_exist"),true);
		
	}
	
	//Validate that  API Method must be Post 
	@Test(priority=3)
	public void test03 () {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("mobile","966556677888");
		json.put("verification_code", "9531");
		request.body(json.toJSONString());
		//API URL & Method
		Response response =  request.get("api/provider/v1/auth/verify");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 403);
	}
	// Validate that cannot access API without header 
	
	@Test(priority=4)
	public void test04 () {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("mobile","966556677888");
		json.put("verification_code", "9531");
		request.body(json.toJSONString());
		//API URL
		Response response =  request.post("api/provider/v1/auth/verify");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 403);
		
	}

}
