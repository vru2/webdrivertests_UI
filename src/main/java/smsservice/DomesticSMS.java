package smsservice;

import java.io.IOException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DomesticSMS extends SMSCommon{
	@Test(groups={"Regression"})
	public void smsservice() throws IOException{
		Response resp ;
		String url = Service_Url("SMS");	
        resp=paramsForSMSservice(headersForsms(),params, url);
    		validation(resp);
		
		
	}
		

}
