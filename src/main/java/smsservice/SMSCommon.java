package smsservice;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;

import domains.PlatformCommonUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class SMSCommon extends PlatformCommonUtil {
	Response resp;	

	public HashMap<String, Object> headersForsms(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("x-ct-api-key", "5e7f24997504ef33561908c0f8ba70b6");
		return headers;
	}
	
    public Response paramsForSMSservice(HashMap<String, Object> headers, String params, String url){
   	 resp = RestAssured.given().
				when().
				log().all().
				body(params).
				headers(headers).
				post(url);
		        return resp;
   }
	
    public Response paramsForInternationalSMS(HashMap<String, Object> headers, String params1, String url){
      	 resp = RestAssured.given().
   				when().
   				log().all().
   				body(params1).
   				headers(headers).
   				post(url);
   		        return resp;
      }
	
	public Response validation(Response resp){
		JsonPath jsonPathEvaluator = resp.jsonPath();
        String SmsId=jsonPathEvaluator.get("smsId");
		if(resp.statusCode()==201){
			assertTrue(true);
			Reporter.log("Test case passed");
			Reporter.log(resp.asString());
			Reporter.log("Status code == "+ resp.statusCode());
			Reporter.log(" smsId == "+ SmsId);
            ResponseBody body= resp.getBody();
            String bodyAsString = body.asString();
            Assert.assertTrue(bodyAsString.contains("smsId"));
 }
		else
		{
			
			Reporter.log(resp.asString());
			assertTrue(false);
		}
		return resp;
	
	}
	
	String params="{" + "\"mobileNumber\" : \"9663949690\"," + "\"content\" : \"HI this is cleartrip sms service message from automation script\"," + "\"domain\" : \"cleartrip.com\"," + "\"id\" : \"Q1234567890\" " + "}";
    String params1="{ "+ "\"mobileNumber\" : \"971555874124\","+ "\"content\" : \"Your booking has been confirmed\","+"\"type\" : \"accountsAppOtpRequest\","+"\"id\" : \"Q1708379897\","+"\"domain\" : \"cleartrip.com\","+"\"ccId\" : \"1234\","+"\"isUnicode\": \"true\","+"\"messageContext\": { "+"\"product\":\"air\","+"\"domain\":\"kw\","+"\"channel\":\"mobile\","+"\"category\":\"na\" "+"}"+"}";
}
	
