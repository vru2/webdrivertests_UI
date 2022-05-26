package test.java.tripServicesProd;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_CorpTripCount extends TripserviceCommon {
	
	@Test(groups={"Regression"})
	public void getCorpTripCount() throws IOException, InterruptedException{
			Reporter.log("http://trip-service.cltp.com:9001/api/trips?airline-pnr=VGRN8E");
			Response resp=RestAssured.get("http://trip-service.cltp.com:9001/api/trips?airline-pnr=VGRN8E");
			if(resp.statusCode()==200){
				ResponseBody body= resp.getBody();
				String bodyAsString = body.asString();
				Assert.assertNotNull(bodyAsString);
				Reporter.log(bodyAsString);
			}else{
				Reporter.log("Status code : " + resp.statusCode());
				assertTrue(false);
			}
			Reporter.log("http://trip-service.cltp.com:9001/api/trips?domain=leagro.cleartripforbusiness.com");
			Response resp1=RestAssured.get("http://trip-service.cltp.com:9001/api/trips?domain=leagro.cleartripforbusiness.com");
			if(resp1.statusCode()==200){
				ResponseBody body= resp1.getBody();
				String bodyAsString = body.asString();
				Assert.assertNotNull(bodyAsString);
				Reporter.log(bodyAsString);
			}else{
				Reporter.log("Status code : " + resp1.statusCode());
				assertTrue(false);
			}
			Reporter.log("http://trip-service.cltp.com:9001/api/trips?domain=leagro.cleartripforbusiness.com&booking-status=P&status-history=0");
			Response resp2=RestAssured.get("http://trip-service.cltp.com:9001/api/trips?domain=leagro.cleartripforbusiness.com&booking-status=P&status-history=0");
			if(resp2.statusCode()==200){
				ResponseBody body= resp2.getBody();
				String bodyAsString = body.asString();
				Assert.assertNotNull(bodyAsString);
				Reporter.log(bodyAsString);
			}else{
				Reporter.log("Status code : " + resp2.statusCode());
				assertTrue(false);
			}
			Reporter.log("http://trip-service.cltp.com:9001/api/trips?domain=leagro.cleartripforbusiness.com&airline-pnr=VGRN8E&booking-status=P&status-history=0&txn-status=C&booker-id=66585586&pax_id=67370730");
			Response resp3=RestAssured.get("http://trip-service.cltp.com:9001/api/trips?domain=leagro.cleartripforbusiness.com&airline-pnr=VGRN8E&booking-status=P&status-history=0&txn-status=C&booker-id=66585586&pax_id=67370730");
			if(resp3.statusCode()==200){
				ResponseBody body= resp3.getBody();
				String bodyAsString = body.asString();
				Assert.assertNotNull(bodyAsString);
				Reporter.log(bodyAsString);
			}else{
				Reporter.log("Status code : " + resp3.statusCode());
				assertTrue(false);
			}
			Reporter.log("http://trip-service.cltp.com:9001/api/trips?domain=leagro.cleartripforbusiness.com&booker-id=66585586");
			Response resp4=RestAssured.get("http://trip-service.cltp.com:9001/api/trips?domain=leagro.cleartripforbusiness.com&booker-id=66585586");
			if(resp4.statusCode()==200){
				ResponseBody body= resp3.getBody();
				String bodyAsString = body.asString();
				Assert.assertNotNull(bodyAsString);
				Reporter.log(bodyAsString);
			}else{
				Reporter.log("Status code : " + resp4.statusCode());
				assertTrue(false);
			}
	}

}
