package tripServices;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Accounts_FetchtripdetailsByBookedUserid extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Accounts_FetchByBookedUserid() throws Exception{
		Response resp;
		Response resp1;
		String url_qa="http://172.17.26.11:9031/api/trips/fetch-trip-details?bookedUserId=41656838&size=1";
		String url1_qa="http://172.17.26.11:9031/api/trips/fetch-trip-details?bookedUserId=41697596&size=10";
			System.out.println(url_qa);
			Reporter.log(url_qa);
	    resp=RestAssured.get(url_qa);
	    if(resp.statusCode()==200){
	    	System.out.println(resp.asString());
	    	Reporter.log(resp.asString());
		    Reporter.log("Status code " + resp.statusCode());
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("trip_id"), true ,"Response boday contains trip_id");
			Assert.assertEquals(bodyAsString.contains("trip_ref"), true ,"Response boday contains trip_ref");
			Assert.assertEquals(bodyAsString.contains("totalElements"), true ,"Response boday contains totalElements");
			Assert.assertEquals(bodyAsString.contains("numberOfElements"), true ,"Response boday contains numberOfElements");
            Assert.assertNotNull("air_bookings");
	    }else{
			Reporter.log("Status code " + resp.statusCode());
			assertTrue(false);
		}
	    Thread.sleep(2000);
	    System.out.println(url1_qa);
	    Reporter.log(url1_qa);
	    resp1=RestAssured.get(url1_qa);
	    if(resp1.statusCode()==200){
	    	System.out.println(resp1.asString());
	    	Reporter.log(resp1.asString());
		    Reporter.log("Status code " + resp1.statusCode());
			ResponseBody body= resp1.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("trip_id"), true ,"Response boday contains trip_id");
			Assert.assertEquals(bodyAsString.contains("trip_ref"), true ,"Response boday contains trip_ref");
			Assert.assertEquals(bodyAsString.contains("totalElements"), true ,"Response boday contains totalElements");
			Assert.assertEquals(bodyAsString.contains("numberOfElements"), true ,"Response boday contains numberOfElements");
            Assert.assertNotNull("air_bookings");
	    }else{
			Reporter.log("Status code " + resp1.statusCode());
			assertTrue(false);
		}
		
		}
	}
