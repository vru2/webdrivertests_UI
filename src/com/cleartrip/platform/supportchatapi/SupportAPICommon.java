package com.cleartrip.platform.supportchatapi;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;

import domainServices.PlatformCommonUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.path.json.JsonPath;


public class SupportAPICommon extends PlatformCommonUtil {
    Response resp;

	public HashMap<String, Object> headersForchat(){
	HashMap<String, Object> headers = new HashMap<>();
	headers.put("Content-Type", "application/json");
	headers.put("Cookie",platform.value("CT_Auth"));
	return headers;
    
    }
    
    public Response createChat(HashMap<String, Object> headers, String params, String url){
    	 resp = RestAssured.given().
				when().
				log().all().
				body(params).
				headers(headers).
				post(url);
		        return resp;
    }
    
    public Response gatewaycreateChat(HashMap<String, Object> headers, String params, String url){
   	 resp = RestAssured.given().
				when().
				log().all().
				body(params).
				headers(headers).
				post(url);
		        return resp;
   }
    
    public Response validation(Response resp){
    	JsonPath jsonPathEvaluator = resp.jsonPath();
        String Status=jsonPathEvaluator.get("Status");
          String ChatKey=jsonPathEvaluator.get("ChatKey");
    	if(resp.statusCode()==202){
			Reporter.log("Test case passed");
			System.out.println(resp.asString());
			Reporter.log("Status code is:: " + resp.statusCode());
			
			Reporter.log("ChatKey == "+ ChatKey);
            ResponseBody body= resp.getBody();
            String bodyAsString = body.asString();
          
            Assert.assertTrue(bodyAsString.contains("ChatKey"));
            Assert.assertTrue(Status.equalsIgnoreCase("success"));
            Assert.assertNotNull(ChatKey);
            assertTrue(true);
 }
		else
		{
			
			Reporter.log(resp.asString());
			assertTrue(false);
		}
    
//    	if(!(status=="Success"){
//    		Reporter.log("Chat created successfully");
//    	}else{
//    		Reporter.log("Status Failed");
//    		Assert.assertTrue(false);
//    	}
//    	
//    	if(!(ChatKey==null)){
//    		Reporter.log("Failed to create chatkey");
//    		Assert.assertTrue(false);
//    	}
		return resp;
    	
    }
   
String params = "{\"skipPreChat\":\"false\",\"data\":{\"name\":\"Testing from cleartrip please close the chat\",\"first_name\":\"test first name\",\"last_name\":\"Test\",\"email\":\"varalakshmi.venkateshaiah@cleartrip.com\",\"information\":\"test info\",\"custom_trip_id\":\"Q1812200190\",\"custom_platform\":\"test custom platform\",\"custom_cause\":\"test custom cause\",\"custom_sub_cause\":\"test custom sub cause\",\"custom_information\":\"test custom information\"}}";

}
