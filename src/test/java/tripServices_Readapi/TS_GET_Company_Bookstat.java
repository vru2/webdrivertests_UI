package test.java.  tripServices_Readapi;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_Company_Bookstat extends TripserviceCommon {

	@Test(groups={"Regression"})
	public void getCompanyBookstat() throws IOException{
		String url=tsendpoint+companybookstat;
		Response resp=RestAssured.get(url);
		if(resp.statusCode()==200){
			Reporter.log(resp.body().asString());
			Reporter.log("Status code is : " + resp.statusCode());
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Reporter.log("Response Body: " +bodyAsString);
			JsonPath jsonPath=new JsonPath(bodyAsString);
			String companybookstats=jsonPath.getString("company_book_stats");
			Assert.assertEquals(companybookstats.contains("trip_ref"), true ,"Response boday contains  trip_ref");
		    Assert.assertEquals(companybookstats.contains("user_id"),true,"Response body contains user_id");
			}else{
			Reporter.log("Status code " + resp.statusCode());
			assertTrue(false);
		}
	}
}
