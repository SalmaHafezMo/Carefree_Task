import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class LoginAPI {
	
	@Test(priority = 1)
	public void test01() {
		//Validate login with already registered credentials 
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","123456");
		json.put("mobile","966556677888");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/login");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Success Login."),true);
	}
  // Validate that cannot login with not verified  data 
	@Test(priority = 2)
	public void test02() {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","123456");
		json.put("mobile","966556677885");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/login");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 401);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("err_auth_login_unverified_user"),true);
	}
	// Validate that cannot Login with invalid data 
	@Test(priority = 3)
	public void test03() {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","123456");
		json.put("mobile","966556677886");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/login");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 401);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Wrong Password ."),true);
	}
	//Validate that cannot Login without Mobile or password 
	@Test(priority = 4)
	public void test04() {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","");
		json.put("mobile","966556677886");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/login");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 422);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("err_auth_login_password_mandatory"),true);
	}
	@Test(priority = 5)
	public void test05() {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","123456");
		json.put("mobile","");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/login");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 422);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("err_auth_login_mobile_mandatory"),true);
	}
	// Validate the API Method is post 
	@Test(priority = 6)
	public void test06() {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","123456");
		json.put("mobile","");
		
		request.body(json.toJSONString());
		Response response =  request.get("api/provider/v1/auth/login");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 403);
	}
	
	// Validate that cannot access the API without Headers 
	@Test(priority = 7)
	public void test07() {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","123456");
		json.put("mobile","966556677888");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/login");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 403);
	}
}
