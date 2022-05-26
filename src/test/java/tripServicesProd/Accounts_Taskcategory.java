package test.java.tripServicesProd;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Accounts_Taskcategory extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Accounts_Taskcategory() throws Exception{
		Response resp;
		Response resp1;
		String url_prod="http://trip-service.cltp.com:9001/api/trips/task-category?id=361";
		String url1_prod="http://trip-service.cltp.com:9001/api/trips/task-category?companyId=95761";
			System.out.println(url_prod);
		    resp=RestAssured.get(url_prod);
		    if(resp.statusCode()==200){
		    	System.out.println(resp.asString());
		    	Reporter.log(resp.asString());
			    Reporter.log("Status code " + resp.statusCode());
			    ResponseBody body= resp.getBody();
		    	 String bodyAsString = body.asString();
				    Reporter.log("Status code " + resp.statusCode());
					Assert.assertNotNull("id");
					Assert.assertNotNull("company_id");
					Assert.assertNotNull("category");
					Assert.assertEquals(bodyAsString.contains("old"), true ,"Response boday contains old");
					
		} else{
			Reporter.log("Status code " + resp.statusCode());
			assertTrue(false);
		}
		    
		    System.out.println(url1_prod);
		    resp1=RestAssured.get(url1_prod);
		    if(resp1.statusCode()==200){
		    	System.out.println(resp1.asString());
		    	Reporter.log(resp1.asString());
			    Reporter.log("Status code " + resp1.statusCode());
			    ResponseBody body= resp1.getBody();
		    	 String bodyAsString = body.asString();
				    Reporter.log("Status code " + resp1.statusCode());
					Assert.assertNotNull("id");
					Assert.assertNotNull("company_id");
					Assert.assertNotNull("category");
					Assert.assertNotNull("person_id");
					Assert.assertEquals(bodyAsString.contains("Payment Follow Up"), true ,"Response boday contains Payment Follow Up");
					Assert.assertEquals(bodyAsString.contains("Special Request"), true ,"Response boday contains Special Request");
					Assert.assertEquals(bodyAsString.contains("ticketinng"), true ,"Response boday contains ticketinng");
					Assert.assertEquals(bodyAsString.contains("Collection"), true ,"Response boday contains Collection");
					
					
		} else{
			Reporter.log("Status code " + resp1.statusCode());
			assertTrue(false);
		}
 }	
}
