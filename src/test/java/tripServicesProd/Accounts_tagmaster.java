package test.java.  tripServicesProd;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Accounts_tagmaster extends TripserviceCommon{
	@Test(groups={"Regression"})
	public void Accounts_ExistingUsers() throws Exception{
		Response resp;
     	String url_prod="http://trip-service.cltp.com:9001/api/trips/tag-master?tagMasterId=3401";
			System.out.println(url_prod);
		    resp=RestAssured.get(url_prod);
		    if(resp.statusCode()==200){
		    	System.out.println(resp.asString());
		    	Reporter.log(resp.asString());
			    Reporter.log("Status code " + resp.statusCode());
			    ResponseBody body= resp.getBody();
				String bodyAsString = body.asString();
				Assert.assertEquals(bodyAsString.contains("64460-vijay"), true ,"Response boday contains 64460-vijay");
				Assert.assertEquals(bodyAsString.contains("CORP"), true ,"Response boday contains CORP");
				Assert.assertEquals(bodyAsString.contains("Company"), true ,"Response boday contains Company");
				Assert.assertNotNull("tag_name");
				Assert.assertNotNull("source_type");
				Assert.assertNotNull("id");
				Assert.assertNotNull("status");	
				Assert.assertNotNull("tag_links");
				Assert.assertNotNull("tag_id");
				Assert.assertNotNull("tag_master_id");
				Assert.assertNotNull("tag_type");
				
		    }else{
				Reporter.log("Status code " + resp.statusCode());
				assertTrue(false);
			}
		}

}
