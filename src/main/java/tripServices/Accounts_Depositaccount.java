package tripServices;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Accounts_Depositaccount extends TripserviceCommon {
	String Host;
	 @Parameters({ "hostvalue"})
	    @BeforeClass
	    public void setUp(String hostvalue)
	    {
	        Host =hostvalue;
	    }
	
	@Test(groups={"Regression"})
	public void Accounts_FetchByPersonid() throws Exception{
		Response resp;
		Response resp1;
		String url_qa="http://172.17.26.11:9031/api/trips/deposit-account-details?id=46207180";
		String url1_qa="http://172.17.26.11:9031/api/trips/deposit-account-details?id=46206142";
		String url_prod="http://trip-service.cltp.com:9001/api/trips/deposit-account-details?id=132459010";
		String url1_prod="http://trip-service.cltp.com:9001/api/trips/deposit-account-details?id=132518836";
		if(Host.equalsIgnoreCase("qa2")) {
			System.out.println(url_qa);
	    resp=RestAssured.get(url_qa);
	    if(resp.statusCode()==200){
	    	System.out.println(resp.asString());
	    	Reporter.log(resp.asString());
		    Reporter.log("Status code " + resp.statusCode());
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("id"), true ,"Response boday contains id");
			Assert.assertNotNull("id");
			Assert.assertEquals(bodyAsString.contains("credit_limit"), true ,"Response boday contains credit_limit");
			Assert.assertNotNull("credit_limit");
			Assert.assertEquals(bodyAsString.contains("credit_allowed"), true ,"Response boday contains credit_allowed");
			Assert.assertNotNull("credit_allowed");
			Assert.assertEquals(bodyAsString.contains("minimum_balance"), true ,"Response boday contains minimum_balance");
            Assert.assertNotNull("minimum_balance");
            Assert.assertEquals(bodyAsString.contains("balance_type"), true ,"Response boday contains balance_type");
            Assert.assertNotNull("balance_type");
            Assert.assertEquals(bodyAsString.contains("account_name"), true ,"Response boday contains account_name");
            Assert.assertNotNull("account_name");
            Assert.assertEquals(bodyAsString.contains("customer_no"), true ,"Response boday contains customer_no");
            Assert.assertNotNull("customer_no");
            Assert.assertEquals(bodyAsString.contains("currency_code"), true ,"Response boday contains currency_code");
            Assert.assertNotNull("currency_code");
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
			Assert.assertEquals(bodyAsString.contains("id"), true ,"Response boday contains id");
			Assert.assertNotNull("id");
			Assert.assertEquals(bodyAsString.contains("balance"), true ,"Response boday contains balance");
			Assert.assertNotNull("balance");
			Assert.assertEquals(bodyAsString.contains("credit_allowed"), true ,"Response boday contains credit_allowed");
			Assert.assertNotNull("credit_allowed");
			Assert.assertEquals(bodyAsString.contains("minimum_balance"), true ,"Response boday contains minimum_balance");
            Assert.assertNotNull("minimum_balance");
            Assert.assertEquals(bodyAsString.contains("balance_type"), true ,"Response boday contains balance_type");
            Assert.assertNotNull("balance_type");
            Assert.assertEquals(bodyAsString.contains("account_name"), true ,"Response boday contains account_name");
            Assert.assertNotNull("account_name");
            Assert.assertEquals(bodyAsString.contains("customer_no"), true ,"Response boday contains customer_no");
            Assert.assertNotNull("customer_no");
            Assert.assertEquals(bodyAsString.contains("currency_code"), true ,"Response boday contains currency_code");
            Assert.assertNotNull("currency_code");
	    }else{
			Reporter.log("Status code " + resp1.statusCode());
			assertTrue(false);
		}
		
		}
		else if(Host.equalsIgnoreCase("www")){
			System.out.println(url_prod);
		    resp=RestAssured.get(url_prod);
		    if(resp.statusCode()==200){
		    	System.out.println(resp.asString());
		    	Reporter.log(resp.asString());
			    Reporter.log("Status code " + resp.statusCode());
				ResponseBody body= resp.getBody();
				String bodyAsString = body.asString();
				Assert.assertEquals(bodyAsString.contains("id"), true ,"Response boday contains id");
				Assert.assertNotNull("id");
				Assert.assertEquals(bodyAsString.contains("credit_allowed"), true ,"Response boday contains credit_allowed");
				Assert.assertNotNull("credit_allowed");
				Assert.assertEquals(bodyAsString.contains("minimum_balance"), true ,"Response boday contains minimum_balance");
	            Assert.assertNotNull("minimum_balance");
	            Assert.assertEquals(bodyAsString.contains("balance_type"), true ,"Response boday contains balance_type");
	            Assert.assertNotNull("balance_type");
	            Assert.assertEquals(bodyAsString.contains("account_name"), true ,"Response boday contains account_name");
	            Assert.assertNotNull("account_name");
	            Assert.assertEquals(bodyAsString.contains("customer_no"), true ,"Response boday contains customer_no");
	            Assert.assertNotNull("customer_no");
	            Assert.assertEquals(bodyAsString.contains("currency_code"), true ,"Response boday contains currency_code");
	            Assert.assertNotNull("currency_code");
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
				Assert.assertEquals(bodyAsString.contains("id"), true ,"Response boday contains id");
				Assert.assertNotNull("id");
				Assert.assertEquals(bodyAsString.contains("credit_allowed"), true ,"Response boday contains credit_allowed");
				Assert.assertNotNull("credit_allowed");
				Assert.assertEquals(bodyAsString.contains("minimum_balance"), true ,"Response boday contains minimum_balance");
	            Assert.assertNotNull("minimum_balance");
	            Assert.assertEquals(bodyAsString.contains("balance_type"), true ,"Response boday contains balance_type");
	            Assert.assertNotNull("balance_type");
	            Assert.assertEquals(bodyAsString.contains("account_name"), true ,"Response boday contains account_name");
	            Assert.assertNotNull("account_name");
	            Assert.assertEquals(bodyAsString.contains("customer_no"), true ,"Response boday contains customer_no");
	            Assert.assertNotNull("customer_no");
	            Assert.assertEquals(bodyAsString.contains("currency_code"), true ,"Response boday contains currency_code");
	            Assert.assertNotNull("currency_code");
		    }else{
				Reporter.log("Status code " + resp1.statusCode());
				assertTrue(false);
			}
		}
		
	}
}
