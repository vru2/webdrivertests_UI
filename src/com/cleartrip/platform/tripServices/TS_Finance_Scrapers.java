package com.cleartrip.platform.tripServices;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_Finance_Scrapers extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void airPostCall() throws Exception{
		Response resp;
		String url =  Service_Url("TRIPSERVICE_FINANCE_SCRAPERS");
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
			resp=TripserviceFinanceScrapers(parmas_financescrapers,headersForTripservicepostcall(),url);
			validationforfinancescrapers(resp);	
		} else if(Host.equalsIgnoreCase("dev")) {
			resp=TripserviceFinanceScrapers(parmas_financescrapers,headersForTripservicepostcall(),url);
			validationforfinancescrapers(resp);	
		} else if (Host.equalsIgnoreCase("www")) {
			resp=TripserviceFinanceScrapers(parmas_financescrapersprod,headersForTripservicepostcall(),url);
			validationforfinancescrapers(resp);
		}
			
 }
}