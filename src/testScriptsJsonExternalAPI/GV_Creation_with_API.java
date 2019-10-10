package testScriptsJsonExternalAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import testScriptsExternalAPICommon.CommonUtils;

import com.google.common.collect.ArrayListMultimap;

public class GV_Creation_with_API extends CommonUtils{
	DefaultHttpClient clientSearch = null;
	/*http://jira.cleartrip.com/browse/PRM-20016
		http://jira.cleartrip.com/browse/DRI-15064
*/	/*  
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	HashMap<String, String> hp=new HashMap<String,String>();
	HashMap<String, String> hp1=new HashMap<String,String>();
	String payLoadFileName = "Payloadroundtrip.txt";
    boolean bookingSuccess = false;
  String tripId = null;
    String airlinepnr=null;
	String ticketnumber=null;

	String bookingstatus=null;
	String basefare=null;
	String discount=null;
	String taxes=null;
	String cashback=null;
	*/
	@Test(invocationCount=2)
	public void gvCreation() throws Exception{
		clientSearch = new DefaultHttpClient();
		String hash=calculateHash("SHA-256","INR|4000|anil.patil@cleartrip.com|1|cleartrip");
		
		//System.out.println("--"+hash);
		
		String postString="{ \"currency\":\"INR\", \"amount\":4000, \"userEmail\":\"anil.patil@cleartrip.com\", \"paymentId\":1 }";
		HttpPost itinenaryCall = new HttpPost(new URI("http://172.17.13.109:9080/payment/gv/create"));
		//System.out.println(postString);
		StringEntity params = new StringEntity(postString);
		itinenaryCall.setEntity(params);

		itinenaryCall.setHeader("Content-Type","application/json");
		itinenaryCall.setHeader("checksum",hash);
		//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
		HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
		HttpEntity entityIti = itinenaryResponse.getEntity();
		//System.out.println(entityIti);
		Document docIti =null;
		BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
		String str12 ="";
		StringBuffer sb12 =new StringBuffer();
		while((str12=br12.readLine())!=null){
			sb12.append(str12);
		}
		//System.out.println("+++++++++"+sb12);
		JSONObject itinenaryId = new JSONObject(sb12.toString());

		Reporter.log("GiftVoucher: "+itinenaryId, true);
					//System.out.println(itinenaryId.getString("itinerary_id"));*/
		
		
		
	}
	
}
