package test.java.tripServices;

import static org.testng.Assert.assertTrue;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_CheckPNR extends TripserviceCommon{
	@Test(groups={"Regression"})
	public void checkpnr() throws Exception{
	Reporter.log("http://172.17.51.86:9031/trips/4640338393/pnr-check");
	Response resp=RestAssured.get("http://172.17.51.86:9031/trips/4640338393/pnr-check");
	System.out.println(resp.asString());
	if(resp.statusCode()==200){
		ResponseBody body= resp.getBody();
		String bodyAsString = body.asString();
		Assert.assertNotNull(resp);
		 Assert.assertEquals("nget is true",bodyAsString.contentEquals("true"), bodyAsString.contentEquals("true"));	
		 Assert.assertEquals("Response consists nget field",bodyAsString.contentEquals("nget"), bodyAsString.contentEquals("nget"));	
		Reporter.log(bodyAsString);
	}else{
		Reporter.log("Status code : " + resp.statusCode());
		assertTrue(false);
	}
}
 }
