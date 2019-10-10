package testScriptsIVR;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonServices.APIUtils;
import domainServices.APIServices;

public class IVR_Refund_Status extends APIServices
{
	@Test
	public void ivrCheck() throws Exception, IOException
	{
	Reporter.log("Test case " + this.getClass() + " started");
	System.out.println("Test case " + this.getClass() + " started");

	HashMap<String, String> headers = APIUtils.getIVRAPIHeaders();
	
	HttpResponse response = APIUtils.getApi(getIVRBaseUrl() + "/hq/trips/v2/Q170525006/refund_status", headers, 200);
	int hitStatus = response.getStatusLine().getStatusCode();
	
	Reporter.log("Http Staus for Get Request"+hitStatus,true);
	System.out.println(response);
	String responseString = APIUtils.returnResponseAsString(response);
	Reporter.log(responseString, true);
	
	Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);

	}
	
	
	
	
}
