import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RegisterationAPI {
	
	//Validate Sign up with Valid data 
	@Test(priority = 1)
	public void Test01(){
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","111@Test");
		json.put("password_confirmation", "111@Test");
		json.put("responsible_person", "Testing_API02");
		json.put("is_accept_terms_and_conditions","true");
		json.put("name", "Testing name02");
		json.put("mobile","966555000888");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/register");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Success Register."),true);
		
		
	}
	// Validate that cannot register with already existing data 
	@Test(priority = 2)
	public void Test02 () {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","123456789");
		json.put("password_confirmation", "123456789");
		json.put("responsible_person", "Testing_API01");
		json.put("is_accept_terms_and_conditions","true");
		json.put("name", "Testing name01");
		json.put("mobile","966556677888");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/register");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 422);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("err_user_already_exists_as_provide"),true);	
		}
		
	// Validate that cannot register with not matched password 
	@Test (priority = 3)
	public void test03() {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","123456789");
		json.put("password_confirmation", "12345678");
		json.put("responsible_person", "Testing_API01");
		json.put("is_accept_terms_and_conditions","true");
		json.put("name", "Testing name01");
		json.put("mobile","966556677888");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/register");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 422);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("err_auth_register_password_confirmation_mismatch"),true);
	}
	
	// Validate that cannot register without check condition & Terms 
	@Test (priority = 4)
	public void test04() {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","123456789");
		json.put("password_confirmation", "12345678");
		json.put("responsible_person", "Testing_API01");
		json.put("name", "Testing name01");
		json.put("mobile","966556677855");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/register");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 422);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("err_auth_register_is_accept_terms_and_conditions_mandatory"),true);
	}
	// Validate that cannot register with missing name 
	@Test (priority = 5)
	public void test05() {
		RestAssured.baseURI= "https://provider.test.carefer.co/";
		RequestSpecification request = RestAssured.given();
		//Headers 
		request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
		
		//Json Body 
		JSONObject  json = new JSONObject();
		json.put("password","123456789");
		json.put("password_confirmation", "12345678");
		json.put("responsible_person", "Testing_API01");
		json.put("is_accept_terms_and_conditions","true");
		json.put("mobile","966556677888");
		
		request.body(json.toJSONString());
		Response response =  request.post("api/provider/v1/auth/register");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 422);
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("err_auth_register_name_mandatory"),true);
	}
	// Validate that cannot register with missing responsible name  
		@Test (priority = 6)
		public void test06() {
			RestAssured.baseURI= "https://provider.test.carefer.co/";
			RequestSpecification request = RestAssured.given();
			//Headers 
			request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
			
			//Json Body 
			JSONObject  json = new JSONObject();
			json.put("password","123456789");
			json.put("password_confirmation", "12345678");
			json.put("is_accept_terms_and_conditions","true");
			json.put("name", "Testing name01");
			json.put("mobile","966556677888");
			
			request.body(json.toJSONString());
			Response response =  request.post("api/provider/v1/auth/register");
			int code = response.getStatusCode();
			Assert.assertEquals(code, 422);
			// Retrieve the body of the Response
			ResponseBody body = response.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("err_auth_register_responsible_person_mandatory"),true);
		}
		
		//Validate that Method is post 
		@Test(priority = 7)
		public void Test07(){
			RestAssured.baseURI= "https://provider.test.carefer.co/";
			RequestSpecification request = RestAssured.given();
			//Headers 
			request.header("Key", "Value").and().header("Platform","careferProviderApplication2Ej!%").and().header("Content-Type","application/json");
			
			//Json Body 
			JSONObject  json = new JSONObject();
			json.put("password","123456789");
			json.put("password_confirmation", "123456789");
			json.put("responsible_person", "Testing_API01");
			json.put("is_accept_terms_and_conditions","true");
			json.put("name", "Testing name01");
			json.put("mobile","966522233389");
			
			request.body(json.toJSONString());
			Response response =  request.get("api/provider/v1/auth/register");
			int code = response.getStatusCode();
			Assert.assertEquals(code, 403);
		}
		
		//Validate that cannot access API without header 
		@Test(priority = 8)
		public void Test08(){
			RestAssured.baseURI= "https://provider.test.carefer.co/";
			RequestSpecification request = RestAssured.given();
			//Json Body 
			JSONObject  json = new JSONObject();
			json.put("password","123456789");
			json.put("password_confirmation", "123456789");
			json.put("responsible_person", "Testing_API01");
			json.put("is_accept_terms_and_conditions","true");
			json.put("name", "Testing name01");
			json.put("mobile","966522233389");
			
			request.body(json.toJSONString());
			Response response =  request.post("api/provider/v1/auth/register");
			int code = response.getStatusCode();
			Assert.assertEquals(code, 403);
		}
		
	}
	

