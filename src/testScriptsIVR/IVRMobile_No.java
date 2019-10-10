package testScriptsIVR;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import commonServices.APIUtils;
import dataServices.APIDataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import commonServices.APIUtils;
import dataServices.APIDataProvider;
import domainServices.APIServices;

public class IVRMobile_No extends APIServices

{
	@Test
	public void ivrCheck() throws Exception, IOException
	{
	Reporter.log("Test case " + this.getClass() + " started");
	System.out.println("Test case " + this.getClass() + " started");

	HashMap<String, String> headers = APIUtils.getIVRAPIHeaders();
	
	HttpResponse response = APIUtils.getApi(getIVRBaseUrl() + "/trips/v2?mobile=7799964888", headers, 200);
	int hitStatus = response.getStatusLine().getStatusCode();
	
	Reporter.log("Http Staus for Get Request"+hitStatus,true);
	System.out.println(response);
	String responseString = APIUtils.returnResponseAsString(response);
	Reporter.log(responseString, true);
	
	Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);

	}
}
	
	


