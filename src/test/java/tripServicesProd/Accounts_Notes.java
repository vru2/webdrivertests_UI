package test.java.tripServicesProd;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Accounts_Notes extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Accounts_ExistingUsers() throws Exception{
		Response resp;
		String url_prod="http://trip-service.cltp.com:9001/api/trips/notes?id=150295672";
			System.out.println(url_prod);
		    resp=RestAssured.get(url_prod);
		    if(resp.statusCode()==200){
		    	System.out.println(resp.asString());
		    	Reporter.log(resp.asString());
			    Reporter.log("Status code " + resp.statusCode());
				Assert.assertNotNull("id");
				Assert.assertNotNull("note");
				Assert.assertNotNull("subject");
				Assert.assertNotNull("user_id");
				Assert.assertNotNull("trip_id");
					
		}
}	
}
