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


public class fnb_v1_api extends AirCommonMethod{
	
	String cityName = "mumbai";
	String cityId = "33719";
	String collection_ID = null;
	String VariantID = null;
	
	@Test
	public void fnb_CitySearch_v1_api() {
	    try
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+"/local/api/v1/fnb/search?city="+cityName; 
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
	        Assert.assertTrue(JsonResponse.contains(cityName)&&JsonResponse.contains(cityId), "City Name and City ID did not match in City Search Response");
	        
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
	
	@Test (dependsOnMethods="fnb_CitySearch_v1_api")
	public void fnb_City_Collection_Listings_v1_api() {
	    try
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+ "/local/api/v1/fnb/city/"+cityId+"/collection/"+collection_ID;
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
	        Assert.assertTrue(JsonResponse.contains("Mumbai")&&JsonResponse.contains(cityId), "Searched City Name and City ID is not match in Collection Listings");
	    	
	        JSONObject jsonObject = new JSONObject(JsonResponse);
            //System.out.println(jsonObject);
            JSONObject details = jsonObject.getJSONObject("details");
            VariantID= String.valueOf(details.getInt("variation_id"));
           
	        
        }catch (Exception e)
        {
            //System.out.println(e);
        }
		
	}
	
	@Test (dependsOnMethods="fnb_City_Collection_Listings_v1_api")
	public void fnb_City_Collection_Activity_Details_v1_api() {
	    try
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+"/local/api/v1/fnb/city/"+cityId+"/variant/"+VariantID;  
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
	        Assert.assertTrue(JsonResponse.contains("Mumbai")&&JsonResponse.contains(cityId), "Searched City Name and City ID is not match in Activity details");
	        
	        
	    	
        }catch (Exception e)
        {
            //System.out.println(e);
        }
        
    
		
	}

	
}




