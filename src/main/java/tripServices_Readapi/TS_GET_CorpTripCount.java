package tripServices_Readapi;

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
		String url=tsendpoint+corptripcount;
		Reporter.log(url);
		Response resp=RestAssured.get(url);
		System.out.println(resp.asString());
		if(resp.statusCode()==200){
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertNotNull(bodyAsString);
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
		String url1=tsendpoint+corptripcount1;
		Reporter.log(url1);
		Response resp1=RestAssured.get(url1);
		System.out.println(resp1.asString());
		if(resp1.statusCode()==200){
			ResponseBody body= resp1.getBody();
			String bodyAsString = body.asString();
			Assert.assertNotNull(bodyAsString);
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp1.statusCode());
			assertTrue(false);
		}
		String url2=tsendpoint+corptripcount2;
		Reporter.log(url2);
		Response resp2=RestAssured.get(url2);
		System.out.println(resp2.asString());
		if(resp2.statusCode()==200){
			ResponseBody body= resp2.getBody();
			String bodyAsString = body.asString();
			Assert.assertNotNull(bodyAsString);
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp2.statusCode());
			assertTrue(false);
		}
		String url3=tsendpoint+corptripcount3;
		Reporter.log(url3);
		Response resp3=RestAssured.get(url3);
		System.out.println(resp3.asString());
		if(resp3.statusCode()==200){
			ResponseBody body= resp3.getBody();
			String bodyAsString = body.asString();
			Assert.assertNotNull(bodyAsString);
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp3.statusCode());
			assertTrue(false);
		}
		Reporter.log("http://172.17.51.86:9031/api/trips?domain=automationqa2pta.cleartripforbusiness.com&booker-id=4172698");
		Response resp4=RestAssured.get("http://172.17.51.86:9031/api/trips?domain=automationqa2pta.cleartripforbusiness.com&booker-id=4172698");
		System.out.println(resp4.asString());
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