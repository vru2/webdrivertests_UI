package testScriptsJsonExternalAPI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
public class HQAPI {
@Test
public void jsonOnewaySinglePAX() throws URISyntaxException, ClientProtocolException, IOException{
	DefaultHttpClient	clientSearch = new DefaultHttpClient();
	////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
	HttpPost bookCall = new HttpPost(new URI("https://qa2.cleartrip.com/automation/book"));
	String postStringBook="{\"email_id\":\"cleartripautomation@gmail.com\", \"user_id\":13990654, \"trip_ref\":\"Q1706270466\", \"host\":\"qa2\"}";
	StringEntity paramsBook1 = new StringEntity(postStringBook);
    bookCall.setEntity(paramsBook1);
    HttpResponse bookResponse = clientSearch.execute(bookCall);
    HttpEntity entityBook1 = bookResponse.getEntity(); 
    String responseString = EntityUtils.toString(entityBook1, "UTF-8");
    System.out.println("response string=="+responseString);
   // System.out.println(bookResponse);
}
}
