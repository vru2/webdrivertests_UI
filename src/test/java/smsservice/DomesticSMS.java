package smsservice;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DomesticSMS extends SMSCommon{
	@Test(groups={"Regression"})
	public void smsservice() throws IOException{
		Response resp ;
		String url = "http://172.17.51.86:8222/sms";	
        resp=paramsForSMSservice(headersForsms(),params, url);
    		validation(resp);
		
		
	}
		

}
