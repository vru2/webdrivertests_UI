package test.java.  tripServicesProd;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_Get_ProductType_BookType extends TripserviceCommon {

	@Test(groups={"Regression"})
	public void getProducttypeBookingType() throws IOException{
		String url=Service_Url("TRIPSERVICE_GETPRODUCTTYPE-BOOKTYPE_CALL");
		System.out.println(url);
		Response resp=RestAssured.get(url);
		System.out.println(resp.asString());
		if(resp.statusCode()==200){
			assertTrue(true);
			Reporter.log("Test case passed");
			Reporter.log(resp.body().asString());
			Reporter.log("Status code is :: " + resp.statusCode());
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Reporter.log("Response Body: " +bodyAsString);
			JsonPath jsonPath=new JsonPath(bodyAsString);
			String TotalElements = jsonPath.getString("totalElements");
			String Pagesize=jsonPath.getString("totalPages");
			Reporter.log("TotalElements:" +TotalElements);
			Reporter.log("Pagesize: " +Pagesize);
			Assert.assertEquals(bodyAsString.contains("trip_ref"), true ,"Response boday contains trip_ref");
			assertTrue(true);
		}else{
			Reporter.log("Status code is :: " + resp.statusCode());
			assertTrue(false);
		}
	}
}
