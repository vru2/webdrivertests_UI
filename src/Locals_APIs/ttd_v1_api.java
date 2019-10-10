package Locals_APIs;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;


public class ttd_v1_api extends AirCommonMethod{
	
	String cityName = "bangalore";
	String cityId = "32550";
	String collection_ID = null;
	String VariantID = null;
	
	@Test
	public void ttd_CitySearch_v1_api() {
	    try
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+"/local/api/v1/ttd/search?city="+cityName;
			//System.out.println(searchUrl);
			HttpGet GetResults = new HttpGet(searchUrl);
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
	        Assert.assertTrue(JsonResponse.contains(cityName)&&JsonResponse.contains(cityId), "Searched City Name and City ID sis not match");
	        
	        JSONObject jsonObject = new JSONObject(JsonResponse);
            //System.out.println(jsonObject);
            JSONArray collections = jsonObject.getJSONArray("collections");
            JSONObject firstcollection = collections.getJSONObject(0);
            collection_ID = String.valueOf(firstcollection.getInt("id"));
            //collection_ID = firstcollection.getInt("id").toString();
            //System.out.println("collection_ID :" + collection_ID);
	    	
        }catch (Exception e)
        {
            //System.out.println(e);
        }
		
	}
	
	@Test (dependsOnMethods="ttd_CitySearch_v1_api")
	public void ttd_City_Collection_Listings_v1_api() {
	    try
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+ "/local/api/v1/ttd/city/"+cityId+"/collection/"+collection_ID;
			//System.out.println(searchUrl);
			HttpGet GetResults = new HttpGet(searchUrl);
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
	        Assert.assertTrue(JsonResponse.contains("Bangalore"), "Searched City Name and City ID sis not match");
	    	
	        JSONObject jsonObject = new JSONObject(JsonResponse);
            //System.out.println(jsonObject);
            
            if (jsonObject.has("details")){
            	
            	//System.out.println("Found Details object in the response");
            
            JSONObject details = jsonObject.getJSONObject("details");
            VariantID= String.valueOf(details.getInt("variation_id"));
            
            }else{
            	
            	//System.out.println("Details object Not Present In Response, Hence pulled ID from result");
            	
            	JSONArray detailsID = jsonObject.getJSONArray("result");
            	JSONObject firstResult = detailsID.getJSONObject(0);
            	VariantID = String.valueOf(firstResult.getInt("id"));
            	
            }
	        
        }catch (Exception e)
        {
            //System.out.println(e);
        }
		
	}
	
	@Test (dependsOnMethods="ttd_City_Collection_Listings_v1_api")
	public void ttd_City_Collection_Activity_Details_v1_api() {
	    try
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+"/local/api/v1/ttd/city/"+cityId+"/variant/"+VariantID; 
			 
			//System.out.println(searchUrl);
			HttpGet GetResults = new HttpGet(searchUrl);
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
	        Assert.assertTrue(JsonResponse.contains("Bangalore"), "Searched City Name and City ID sis not match");
	        
	        
	    	
        }catch (Exception e)
        {
            //System.out.println(e);
        }
        
	    	
	}

	
}


