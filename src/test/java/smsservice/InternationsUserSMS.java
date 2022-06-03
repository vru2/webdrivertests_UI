package test.java.  smsservice;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class InternationsUserSMS extends SMSCommon{
	@Test(groups={"Regression"})
	public void smsservice() throws IOException{
		Response resp ;
		String url = Service_Url("SMS");	
        resp=paramsForInternationalSMS(headersForsms(),params1, url);
    		validation(resp);
		
		
	}
		

}
