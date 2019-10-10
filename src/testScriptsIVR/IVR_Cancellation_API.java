package testScriptsIVR;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonServices.APIUtils;
import domainServices.APIServices;

public class IVR_Cancellation_API extends APIServices

{
	@Test
	public void ivrCheck() throws Exception, IOException
	{
	Reporter.log("Test case " + this.getClass() + " started");
	System.out.println("Test case " + this.getClass() + " started");

	HashMap<String, String> headers1 = APIUtils.postIVRAPIHeaders();
	
	HttpResponse response1 = APIUtils.postApi(getIVRBaseUrl() + "/hq/trips/v2/Q1710110682/ivr_cancellation?", headers1,"",200);
	int hitStatus = response1.getStatusLine().getStatusCode();
	
	Reporter.log("Http Staus for Get Request"+hitStatus,true);
	System.out.println(response1);
	String responseString1 = APIUtils.returnResponseAsString(response1);
	Reporter.log(responseString1, true);
	
	Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);

		}	

}
