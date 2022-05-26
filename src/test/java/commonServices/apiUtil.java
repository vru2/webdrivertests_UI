package test.java.commonServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

public class apiUtil {
	 DefaultHttpClient httpClient=new DefaultHttpClient();
	 
	 
	 
	 public  String getCall(CookieStore Cookie, String URL)
			throws URISyntaxException, IOException, ClientProtocolException {
	
		HttpGet Get = new HttpGet();
		URI searchF5URL = new URI(URL);
		Get.setURI(searchF5URL);
		httpClient.setRedirectStrategy(new LaxRedirectStrategy());
		httpClient.setCookieStore(Cookie);
		HttpResponse searchResponse = httpClient.execute(Get);
		
		
		BufferedReader detail = new BufferedReader(new InputStreamReader((searchResponse.getEntity().getContent())));
		String response;
		StringBuilder DataResultbuilder = new StringBuilder();
		while ((response = detail.readLine()) != null) {
			DataResultbuilder.append(response);
		}
		String responseData = DataResultbuilder.toString();
		return responseData;
	}

	 
	 
	 public String putDate(int days) throws Exception {
		  Calendar c = new GregorianCalendar();
		  c.add(Calendar.DATE, +days);
		  Date s = c.getTime();
		  String dateString = new SimpleDateFormat("dd/MM/yyyy").format(s);
		  return dateString;
		 }
	 
	 public String postCall(CookieStore Cookie, String URL,String postdata) throws ClientProtocolException, IOException {
		 
		 HttpPost post = new HttpPost(URL);
		 post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		 post.setHeader("Accept", "application/json");
		 post.setHeader("Content-Type", "application/json");
		 post.setHeader("X-CSRF-Token", "jr6h+vVl15d3GIKCInGpLZyNb3/Z97VWLZn7NXRUeNU=");
		 post.setHeader("Host", "qa2.cleartrip.com");
			String activityData=postdata;
			StringEntity input1 = new StringEntity(activityData);
			post.setEntity(input1);
			httpClient.setCookieStore(Cookie);
			HttpResponse searchResponse1 = httpClient.execute(post);
			BufferedReader br1 = new BufferedReader(new InputStreamReader((searchResponse1.getEntity().getContent())));
			String output1;
			StringBuffer totalOutput1 = new StringBuffer();
			while ((output1 = br1.readLine()) != null) {
				totalOutput1.append(output1);
			}
			String response=totalOutput1.toString();
			////System.out.println(response);
			EntityUtils.consumeQuietly(searchResponse1.getEntity());
			post.releaseConnection();
			return response;
		
	}

}
