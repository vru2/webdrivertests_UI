package test.java.tripServices_Readapi;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_TripcountbyPNR extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void getTripcount() throws IOException, InterruptedException{
		String url=tsendpoint+tripcountpnr;
		Reporter.log(url);
		Response resp=RestAssured.get(url);
		if(resp.statusCode()==200){
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertNotNull(resp);
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
		
	}

}
