package tripServicesProd;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_FinanceAllTrips_Air extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void getTripService() throws IOException{
		String url="http://trip-service.cltp.com:9001/trips/finance/all-trips?date=2019-09-01&productType=air&txnType=1&paymentStatus=SUCCESS&domains=cleartrip.com%24";
		Response resp=RestAssured.get(url);
		System.out.println(resp.asString());
		if(resp.statusCode()==200){
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Reporter.log(bodyAsString);
			Assert.assertEquals(resp.statusCode(),200,"Correct status code dispalayed");
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
	}


}
