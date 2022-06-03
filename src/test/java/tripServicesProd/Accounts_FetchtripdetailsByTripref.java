package test.java.  tripServicesProd;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Accounts_FetchtripdetailsByTripref extends TripserviceCommon{

	@Test(groups={"Regression"})
	public void Accounts_FetchByTripref() throws Exception{
		Response resp;
		String url_prod="http://trip-service.cltp.com:9001/api/trips/fetch-trip-details?tripRef=190401816972&size=1";
			 System.out.println(url_prod);
			    resp=RestAssured.get(url_prod);
			    if(resp.statusCode()==200){
			    	System.out.println(resp.asString());
			    	Reporter.log(resp.asString());
				    Reporter.log("Status code " + resp.statusCode());
					ResponseBody body= resp.getBody();
					String bodyAsString = body.asString();
					Assert.assertEquals(bodyAsString.contains("trip_id"), true ,"Response boday contains trip_id");
					Assert.assertEquals(bodyAsString.contains("trip_ref"), true ,"Response boday contains trip_ref");
		         Assert.assertNotNull("train_bookings");
			    }else{
					Reporter.log("Status code " + resp.statusCode());
					assertTrue(false);
				}
		}
	}