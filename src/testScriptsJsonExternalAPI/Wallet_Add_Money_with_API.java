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
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import testScriptsExternalAPICommon.CommonUtils;



public class Wallet_Add_Money_with_API extends CommonUtils{

	DefaultHttpClient clientSearch = null;
	// maximum RS.50,000 limit
	
	@Test
	public void amountForWAllet() throws Exception{
		clientSearch = new DefaultHttpClient();
		String hash=calculateHash("SHA-256","13957750|Bangalore-chennai|Q1701240048|PROMOTION|CREDIT|1000|03-DEC-2019|2480|Rahulk0&");
		
		System.out.println("--"+hash);
		
		String postString="{   \"trip-details\": \"Bangalore-chennai\",   \"trip-ref\": \"Q1701240048\",   \"event\": \"PROMOTION\",   \"txn-type\": \"CREDIT\",   \"amount\": 1000,   \"expiry-date\": \"03-DEC-2019\",   \"caller-id\": \"2480\" }";
		HttpPost WalletCall = new HttpPost(new URI("http://10.10.21.146:9080/payment/service/ctwallets/13957750/transactions"));
		System.out.println(postString);
		StringEntity params = new StringEntity(postString);
		WalletCall.setEntity(params);

		WalletCall.setHeader("Content-Type","application/json");
		WalletCall.setHeader("checksum",hash);
		//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
		HttpResponse itinenaryResponse = clientSearch.execute(WalletCall);
		HttpEntity entityIti = itinenaryResponse.getEntity();
		
		Document docIti =null;
		BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
		String str12 ="";
		StringBuffer sb12 =new StringBuffer();
		while((str12=br12.readLine())!=null){
			sb12.append(str12);
			
		}
		System.out.println(sb12);
		JSONObject WalletDetails = new JSONObject(sb12.toString());

		System.out.println("WalletDetails="+WalletDetails);
					//System.out.println(itinenaryId.getString("itinerary_id"));
		
		
		
	}
	

}
