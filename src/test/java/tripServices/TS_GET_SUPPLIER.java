package test.java.  tripServices;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_SUPPLIER extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void getSupplier() throws IOException, InterruptedException{
		String url=Service_Url("TRIPSERVICE_GET_SUPPLIER");
		Reporter.log(url);
		System.out.println(url);
		Response resp=RestAssured.get(url);
		Thread.sleep(6000);
		Reporter.log(resp.asString());
		System.out.println(resp.asString());
		if(resp.statusCode()==200){
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("BOOKING_NO"), true ,"Response boday contains booking_no");
			Assert.assertEquals(bodyAsString.contains("SUPPLIER"), true ,"Response boday contains supplier");
			Assert.assertEquals(bodyAsString.contains("AGENT_PCC"), true ,"Response boday contains agent_pcc");
			Assert.assertEquals(bodyAsString.contains("JOURNEY_TYPE"), true ,"Response boday contains journey_type");
			Assert.assertEquals(bodyAsString.contains("SOURCE_TYPE"), true ,"Response boday contains source_type");
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
		
	}
}
