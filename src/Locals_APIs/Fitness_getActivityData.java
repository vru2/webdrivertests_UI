package Locals_APIs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Fitness_getActivityData {
	
	
	private static final String HASH_ALGORITHM = "HmacSHA256";
    private static final String HASH_ALGO = "SHA-256";


    private String hashMac(String text, String secretKey) throws Exception {

        Key sk = new SecretKeySpec(secretKey.getBytes("UTF-8"), HASH_ALGORITHM);
        Mac mac = Mac.getInstance(sk.getAlgorithm());
        mac.init(sk);
        byte[] hmac = mac.doFinal(text.getBytes());
        return new String(Hex.encodeHex(hmac));

    }

    private String calculateHASH(String data, String algo) throws Exception {

        MessageDigest md = MessageDigest.getInstance(algo);
        md.update(data.getBytes());
        byte[] mb = md.digest();
        String out = new String(Hex.encodeHex(mb));
        return out;

    }

	@Test
    public void getOperatingCities() {
        try
        {
        	  String nonce = "abcdef";
              String apiKey = "He_6_5lBkhA58wkDW_b_1rjM0nu0s5dIiF_DVcvbUAY";
              String secretKey = "1deffaff9941921039f309035bf6ffd6b8ccd27bb4b39748885fc355cbdb5006";
              Date date = new Date();
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              StringBuffer content = new StringBuffer(nonce);
              content.append("GET");
              content.append(sdf.format(date));
              String contentSha256 = calculateHASH(content.toString(), HASH_ALGO);
              String hmac = hashMac(contentSha256, secretKey);

 
    	DefaultHttpClient Client = new DefaultHttpClient();
		String searchUrl="http://fitfeeds.cloudapp.net:8080/B2BAPIPlatform/GetActivityData/v1?activityId=764:27";
		//System.out.println(searchUrl);
		HttpGet GetResults = new HttpGet(searchUrl);
		GetResults.setHeader("nonce","abcdef");
		GetResults.setHeader("APIkey",apiKey);		
		GetResults.setHeader("content-SHA256",contentSha256);
		GetResults.setHeader("Hmac",hmac);
		//GetResults.setHeader("content-type", "application/json");
		HttpResponse searchResponse1 = Client.execute(GetResults);
		//System.out.println(searchResponse1);
		Assert.assertEquals(searchResponse1.getStatusLine().getStatusCode(), 200, "Response code");
		
		BufferedReader br1 = new BufferedReader(new InputStreamReader((searchResponse1.getEntity().getContent())));
		String output1;
		StringBuffer totalOutput1 = new StringBuffer();
		while ((output1 = br1.readLine()) != null) {
			totalOutput1.append(output1);
		}
		String JsonResponse=totalOutput1.toString();
        //System.out.println(JsonResponse);
        
        }catch (Exception e)
        {
            //System.out.println(e);
        }
    }
}