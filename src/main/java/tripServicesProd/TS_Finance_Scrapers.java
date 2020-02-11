package tripServicesProd;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_Finance_Scrapers extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void airPostCall() throws Exception{
		Response resp;
		String url ="http://172.21.48.21:8281/trips/finance/scrapers";
			resp=TripserviceFinanceScrapers(parmas_financescrapersprod,headersForTripservicepostcall(),url);
			validationforfinancescrapers(resp);
		}
			
 }