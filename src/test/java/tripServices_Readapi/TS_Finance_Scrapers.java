package test.java.tripServices_Readapi;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_Finance_Scrapers extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void airPostCall() throws Exception{
		Response resp;
		String url =  tsendpoint+financescrapers;
			Reporter.log(url);
			System.out.println(url);
			resp=TripserviceFinanceScrapers(parmas_financescrapers,headersForTripservicepostcall(),url);
			System.out.println(resp.asString());
			validationforfinancescrapers(resp);	
		} 
}