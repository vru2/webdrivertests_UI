package tripServices;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_BookingStatus extends TripserviceCommon {

			@Test(groups={"Regression"})
			public void Tripserviceairputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
			{
				Response resp;
				String url =  Service_Url("TRIPSERVICE_POST_CALL");
				Reporter.log(url);
				resp=TripservicePostcall(params,headersForTripservicepostcall(),url);
				validationforputcall(resp);	
				Response resp2;
				String url2="http://172.17.51.86:9031/trips/fetch-booking-status?tripRef="+tripref;
				Reporter.log(url2);
				resp2=RestAssured.get(url2);
				if(resp2.statusCode()==200)
				{
					System.out.println(resp2.asString());
					Reporter.log(resp2.asString());
		            Reporter.log("Status code " + resp2.statusCode());
		            ResponseBody body= resp2.getBody();
					String bodyAsString = body.asString();
					Assert.assertNotNull(resp2);
				    Assert.assertEquals("Booking Status is Z",bodyAsString.contentEquals("Z"), bodyAsString.contentEquals("Z"));	
				}
				else{
					Reporter.log("Trip ref not found");
					assertTrue(false);
				}
                Response resp1;
				String url1 = ("http://172.17.51.86:9031/trips/"+tripref+"/air-bookings/update-booking");
				Reporter.log(url1);
				resp1=TripserviceHotelsPutcall(params3,headersForTripserviceputcall(),url1);
				validationforput(resp1);	
				Reporter.log(url2);
				resp2=RestAssured.get(url2);
				if(resp2.statusCode()==200)
				{
					System.out.println(resp2.asString());
					Reporter.log(resp2.asString());
		            Reporter.log("Status code " + resp2.statusCode());
		            ResponseBody body= resp2.getBody();
					String bodyAsString = body.asString();
					Assert.assertNotNull(resp2);
				    Assert.assertEquals("Booking Stauts is P",bodyAsString.contentEquals("P"), bodyAsString.contentEquals("P"));	
				}
				else{
					Reporter.log("Trip ref not found");
					assertTrue(false);
				}
					
		 }
}
