package test.java.  smsserviceprod;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class InternationsUserSMS extends SMSCommon{
	@Test(groups={"Regression"})
	public void smsservice() throws IOException{
		Response resp ;
		String url ="https://communication.cleartrip.com/sms";	
        resp=paramsForInternationalSMS(headersForsms(),params1, url);
    		validation(resp);
		
		
	}
		

}
