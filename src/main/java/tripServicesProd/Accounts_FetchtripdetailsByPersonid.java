package tripServicesProd;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Accounts_FetchtripdetailsByPersonid extends TripserviceCommon{
	@Test(groups={"Regression"})
	public void Accounts_FetchByPersonid() throws Exception{
		Response resp;
		Response resp1;
		String url_prod="http://trip-service.cltp.com:9001/api/trips/fetch-trip-details?personId=86970492&size=1";
		String url1_prod="http://trip-service.cltp.com:9001/api/trips/fetch-trip-details?personId=86970492&size=10";
			System.out.println(url_prod);
		    resp=RestAssured.get(url_prod);
		    if(resp.statusCode()==200){
		    	System.out.println(resp.asString());
		    	Reporter.log(resp.asString());
			    Reporter.log("Status code " + resp.statusCode());
				ResponseBody<?> body= resp.getBody();
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
		    System.out.println(url1_prod);
		    resp1=RestAssured.get(url1_prod);
		    if(resp1.statusCode()==200){
		    	System.out.println(resp1.asString());
		    	Reporter.log(resp1.asString());
			    Reporter.log("Status code " + resp1.statusCode());
				ResponseBody<?> body= resp1.getBody();
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
