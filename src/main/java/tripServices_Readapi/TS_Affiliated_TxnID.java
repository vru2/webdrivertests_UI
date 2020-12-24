package tripServices_Readapi;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_Affiliated_TxnID extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void getTripService(){
		String url=tsendpoint+affiliatedtxnid;
		Reporter.log(url);
		Response resp=RestAssured.get(url);
		if(resp.statusCode()==200){
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("trip_ref"), true ,"Response boday contains tripId");
			Reporter.log(bodyAsString);
			System.out.println(resp.asString());
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
	}
}
