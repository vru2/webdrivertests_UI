package tripServices;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Accounts_ExistingUser extends TripserviceCommon {

	@Test(groups={"Regression"})
	public void Accounts_ExistingUsers() throws Exception{
		Response resp;
		Response resp1;
		String url_qa="http://172.17.26.11:9031/api/trips/existing-user?userId=41695410";
		String url1_qa="http://172.17.26.11:9031/api/trips/existing-user?emailId=varalakshmivaru29@gmail.com";
		String url_prod="http://trip-service.cltp.com:9001/api/trips/existing-user?userId=86627412";
		String url1_prod="http://trip-service.cltp.com:9001/api/trips/existing-user?emailId=varalakshmi.venkateshaiah@cleartrip.com";
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
			System.out.println(url_qa);
	    resp=RestAssured.get(url_qa);
	    if(resp.statusCode()==200){
	    	System.out.println(resp.asString());
	    	Reporter.log(resp.asString());
		    Reporter.log("Status code " + resp.statusCode());
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("true"), true ,"Response boday contains true");
	    }else{
			Reporter.log("Status code " + resp.statusCode());
			assertTrue(false);
		}
	    Thread.sleep(2000);
	    System.out.println(url1_qa);
	    resp1=RestAssured.get(url1_qa);
	    if(resp1.statusCode()==200){
	    	System.out.println(resp1.asString());
	    	Reporter.log(resp1.asString());
		    Reporter.log("Status code " + resp1.statusCode());
			ResponseBody body= resp1.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("true"), true ,"Response boday contains true");
	    }else{
			Reporter.log("Status code " + resp1.statusCode());
			assertTrue(false);
		}
		} else if(Host.equalsIgnoreCase("www")){
			System.out.println(url_prod);
		    resp=RestAssured.get(url_prod);
		    if(resp.statusCode()==200){
		    	System.out.println(resp.asString());
		    	Reporter.log(resp.asString());
			    Reporter.log("Status code " + resp.statusCode());
				ResponseBody body= resp.getBody();
				String bodyAsString = body.asString();
				Assert.assertEquals(bodyAsString.contains("true"), true ,"Response boday contains true");
		    }else{
				Reporter.log("Status code " + resp.statusCode());
				assertTrue(false);
			}
		    Thread.sleep(2000);
		    System.out.println(url1_prod);
		    resp1=RestAssured.get(url1_prod);
		    if(resp1.statusCode()==200){
		    	System.out.println(resp1.asString());
		    	Reporter.log(resp1.asString());
			    Reporter.log("Status code " + resp1.statusCode());
				ResponseBody body= resp1.getBody();
				String bodyAsString = body.asString();
				Assert.assertEquals(bodyAsString.contains("true"), true ,"Response boday contains true");
		    }else{
				Reporter.log("Status code " + resp1.statusCode());
				assertTrue(false);
			}
		}
		
	}
}
