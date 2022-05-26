package test.java.tripServices;

import static org.testng.Assert.assertTrue;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_FetchUserID_on_txnidandtripid extends TripserviceCommon{
	@Test(groups={"Regression"})
	public void checkpnr() throws Exception{
	Reporter.log("http://172.17.51.86:9031/trips/txn/userId/46812466?txnType=1");
	Response resp=RestAssured.get("http://172.17.51.86:9031/trips/txn/userId/46812466?txnType=1");
	System.out.println(resp.asString());
	if(resp.statusCode()==200){
		ResponseBody body= resp.getBody();
		String bodyAsString = body.asString();
		Assert.assertNotNull(resp);
		 Assert.assertEquals("Response consists of userId field",bodyAsString.contentEquals("userId"), bodyAsString.contentEquals("userId"));	
		Reporter.log(bodyAsString);
	}else{
		Reporter.log("Status code : " + resp.statusCode());
		assertTrue(false);
	}
}
 }
