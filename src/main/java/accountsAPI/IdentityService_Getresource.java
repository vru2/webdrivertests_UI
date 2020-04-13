package accountsAPI;

import java.io.IOException;
import io.restassured.http.ContentType;


import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import junit.framework.Assert;


import static io.restassured.RestAssured.given;
public class IdentityService_Getresource extends AccountsCommon_API
{
	
	
	@Test
	public void identtiyservice_getresource() throws IOException, JSONException{

		Response resp ;		
		String Type = null;
		String url="https://qa2.cleartrip.com/ctauth/resource";
		resp =postCallIdentity(url);
		
		String ReponseStr = resp.body().asString();
		Reporter.log("Response body "+Type +" : "+ resp.body().asString());

		int statusCode = resp.getStatusCode();
		System.out.println(statusCode);

		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();
		if(statusCode!=401) {
			Assert.assertTrue(false);
		}

		if(!ReponseStr.contains("Other than 401 status is displayed")){
		}
		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		
		String status = jsonPathEvaluator.getString("status");
		
		if(!status.contains("UNAUTHORIZED")) {
			Assert.assertTrue(false);						
		}
		
	}
}


