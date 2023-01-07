import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ValidScenario {
	
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
			json.put("mobile","966555000889");
			request.body(json.toJSONString());
			Response Signupesponse =  request.post("api/provider/v1/auth/register");
			//Verification
			json.put("mobile","966556677889");
			json.put("verification_code", "9531");
			request.body(json.toJSONString());
			//API URL
			Response Verifyresponse =  request.post("api/provider/v1/auth/verify");
			//Login 
			json.put("password","123456");
			json.put("mobile","966556677889");
			request.body(json.toJSONString());
			Response Loginreponse =  request.post("api/provider/v1/auth/login");
			int code = Loginreponse.getStatusCode();
			Assert.assertEquals(code, 200);
			// Retrieve the body of the Response
			ResponseBody body = Loginreponse.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("Success Login."),true);
			
		}

}
