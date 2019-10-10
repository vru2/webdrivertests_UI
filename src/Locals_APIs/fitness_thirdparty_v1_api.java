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

public class fitness_thirdparty_v1_api extends AirCommonMethod { 

	/*city":{"name":"mumbai","id":"33719"}
	city":{"name":"bangalore","id":"32550"}
	city":{"name":"new delhi","id":"35485"}"
	city":{"name":"pune","id":"35943"}
*/
	
	
	String cityName = "delhi";
	String cityId = "35485";
	String gym_id = null;
	String category_id = null;
	String duration=null;
	
	@Test (priority=1)
	public void fitness_homepage_v1_api() {
	    try
	    
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+"/local/api/v1/fitness/search?city="+cityName; 
							
       		//System.out.println(searchUrl);
			HttpGet GetResults = new HttpGet(searchUrl);
			GetResults.setHeader("content-type", "application/json");
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
	        
	        JSONObject jsonObject = new JSONObject(JsonResponse);
            //System.out.println(jsonObject);
            JSONObject fitgym = jsonObject.getJSONObject("fitGym");
            JSONArray list = fitgym.getJSONArray("List");
            JSONObject firstgym = list.getJSONObject(0);
            gym_id = String.valueOf(firstgym.getInt("id"));
            
            JSONObject jsonObject1 = new JSONObject(JsonResponse);
            //System.out.println(jsonObject);
            JSONObject fitCat = jsonObject1.getJSONObject("fitCat");
            JSONArray list1 = fitCat.getJSONArray("List");
            JSONObject fitchCategory = list1.getJSONObject(0);
            category_id = String.valueOf(fitchCategory.getInt("id"));
            
	        Assert.assertTrue(JsonResponse.contains(cityName)&&JsonResponse.contains(cityId), "City Name and City ID did not match in City Search Response");
	        
	        
	        
        }catch (Exception e)
        {
            //System.out.println(e);
        }
	
}

	@Test(priority=2,dependsOnMethods="fitness_homepage_v1_api")
	public void fitness_gym_details_v1_api() {
	    try
	    
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+"/local/api/v1/fitness/details/city/"+cityId+"/gym/"+gym_id; 
									
       		//System.out.println(searchUrl);
			HttpGet GetResults = new HttpGet(searchUrl);
			GetResults.setHeader("content-type", "application/json");
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
	        //System.out.println("Verify assert");
	        Assert.assertTrue(JsonResponse.contains(cityName)&&JsonResponse.contains(cityId), "City Name and City ID did not match in City Search Response");
	
        }catch (Exception e)
        {
            //System.out.println(e);
        }
	
}


	@Test(priority=3,dependsOnMethods="fitness_homepage_v1_api")
	public void fitness_category_details_v1_api() {
	    try
	    
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+"/local/api/v1/fitness/details/city/"+cityId+"/category/"+category_id; 
			    					
       		//System.out.println(searchUrl);
			HttpGet GetResults = new HttpGet(searchUrl);
			GetResults.setHeader("content-type", "application/json");
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
	
        }catch (Exception e)
        {
            //System.out.println(e);
        }
	
}

	@Test(priority=4,dependsOnMethods="fitness_homepage_v1_api")
	public void fitness_category_v1_api() {
	    try
	    
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+"/local/api/v1/fitness/city/"+cityId+"/category/"+category_id; 
			    					
       		//System.out.println(searchUrl);
			HttpGet GetResults = new HttpGet(searchUrl);
			GetResults.setHeader("content-type", "application/json");
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
	        
	        Assert.assertTrue(JsonResponse.contains("result"), "City Name and City ID did not match in City Search Response");
	
        }catch (Exception e)
        {
            //System.out.println(e);
        }
	
}

	
	@Test
	public void fitness_schedule_v1_api() {
	    try
	    
        {
	    	DefaultHttpClient Client = new DefaultHttpClient();
			String searchUrl=api_url()+"/local/api/v1/fitness/schedules"; 
			    
       		//System.out.println(searchUrl);
			HttpGet GetResults = new HttpGet(searchUrl);
			GetResults.setHeader("content-type", "application/json");
			HttpResponse searchResponse1 = Client.execute(GetResults);
			////System.out.println(searchResponse1);
			
			Assert.assertEquals(searchResponse1.getStatusLine().getStatusCode(), 200, "Response code");
			
			BufferedReader br1 = new BufferedReader(new InputStreamReader((searchResponse1.getEntity().getContent())));
			String output1;
			
			StringBuffer totalOutput1 = new StringBuffer();
			while ((output1 = br1.readLine()) != null) {
				totalOutput1.append(output1);
			}
			
			String JsonResponse=totalOutput1.toString();
	       // //System.out.println(JsonResponse);

            JSONArray jsonArray = new JSONArray(JsonResponse);
            //System.out.println(jsonArray);
            JSONObject firstSchedule = jsonArray.getJSONObject(0);
            JSONArray Schedule = firstSchedule.getJSONArray("sch");
            JSONObject ScheduleDetail = Schedule.getJSONObject(0);
            duration =  ScheduleDetail.getString("duration");
            //System.out.println("duration :"+duration);
	
        }catch (Exception e)
        {
            //System.out.println(e);
        }
	
}

	
	
}
