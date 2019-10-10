package testScriptsCHMM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.IndiaHotels;

public class CHMMCom_API_Extranet_Prod extends IndiaHotels  {

	String debug = common.value("debug_mode");
    boolean debug_mode = Boolean.parseBoolean(debug);
   
    
    @DataProvider
    public Object [ ][ ] hotel() {
          	return new Object [ ] [ ] { { "2430330","29947661440","6462396","06b578176ecf49f0858ffa13c9113a14" } };
    }

    //HotelID = 41403 , 160158 , 171525
    //RoomTypeID = (41403) 2677433, 313520 , 130197 , 130196, 130198
    //RateID = (2677433), 80348, 80347, 80349,  80329 
    
    
    @Test (dataProvider="hotel")
    public void getRoomTypes(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
    	

        String api_url = api_url();
        Reporter.log("URL :"+api_url);  
    	DefaultHttpClient httpclient = new DefaultHttpClient();
		    try {
				
				String requestXML = "<?xml version=\"1.0\" ?><hotel-room-types type=\"get\" xmlns=\"http://www.cleartrip.com/extranet/hotel-room-types\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><hotel-id>"+hotelId+"</hotel-id></hotel-room-types>";
				HttpPost getroomtypes = new HttpPost(api_url() + "/chmm/service/get-room-types");
				getroomtypes.addHeader("X-CT-API-KEY", apikey);
				getroomtypes.addHeader("X-CT-SOURCE-TYPE", "API");
				Reporter.log(requestXML,true);
				StringEntity input = new StringEntity(requestXML);
				getroomtypes.setEntity(input);
				HttpResponse httpResponse = httpclient.execute(getroomtypes);
				BufferedReader br = new BufferedReader(new InputStreamReader(	(httpResponse.getEntity().getContent())));
				String output;
				StringBuilder builder = new StringBuilder();
				while ((output = br.readLine()) != null) {
					builder.append(output);
				}
				String rOutput = builder.toString();
				Reporter.log(rOutput);
			    Assert.assertTrue(rOutput.contains("Request Executed Successfully"));
	    
		} catch (IOException e) {
			e.printStackTrace();
		}
}

    @Test (dataProvider="hotel")
    public void getRatePlan(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
    	DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			String requestXML ="<?xml version=\"1.0\" ?><hotel-rate-plan type=\"get\" xmlns=\"http://www.cleartrip.com/extranet/hotel-rate-plan\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.cleartrip.com/extranet/hotel-rate-plan hotel-rate-plan.xsd\"><hotel-id>"+hotelId+"</hotel-id><room-id>"+roomtypeId+"</room-id></hotel-rate-plan>";
			Reporter.log(requestXML,true);
		
			HttpPost getrateplan =new HttpPost(api_url() + "/chmm/service/get-rate-plan");
			getrateplan.addHeader("X-CT-API-KEY", apikey);
			getrateplan.addHeader("X-CT-SOURCE-TYPE", "API");
			StringEntity input = new StringEntity(requestXML);
			getrateplan.setEntity(input);
			HttpResponse httpResponse = httpclient.execute(getrateplan);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(httpResponse.getEntity().getContent())));
			String output;
			StringBuilder builder = new StringBuilder();
			while ((output = br.readLine()) != null) {
				builder.append(output);
			}
			String rOutput = builder.toString();
			Reporter.log(rOutput);
			Assert.assertTrue( rOutput.contains("Request Executed Successfully"));
	
		} catch (IOException e) {
			e.printStackTrace();
	}
} 
    
    @Test (dataProvider="hotel")
    public void getRateInfo(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
    	DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			String requestXML= "<?xml version=\"1.0\" ?><hotel-rate-info type=\"get\" xmlns=\"http://www.cleartrip.com/extranet/hotel-rate-info\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.cleartrip.com/extranet/hotel-rate-info hotel-rate-info.xsd\"><hotel-id>"+hotelId+"</hotel-id><room-type-id>"+roomtypeId+"</room-type-id><rate-id>"+rateId+"</rate-id><from-date>"+putDate1(1)+"</from-date><to-date>"+putDate1(30)+"</to-date></hotel-rate-info>";
			Reporter.log(requestXML,true);
			
			HttpPost getrateinfo =new HttpPost(api_url() + "/chmm/service/get-rate-info");
			getrateinfo.addHeader("X-CT-API-KEY", apikey);
			getrateinfo.addHeader("X-CT-SOURCE-TYPE", "API");
			StringEntity input = new StringEntity(requestXML);
			getrateinfo.setEntity(input);
			HttpResponse httpResponse = httpclient.execute(getrateinfo);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(httpResponse.getEntity().getContent())));
			String output;
			StringBuilder builder = new StringBuilder();
			while ((output = br.readLine()) != null) {
				builder.append(output);
			}
			String rOutput = builder.toString();
			Reporter.log(rOutput);
			Assert.assertTrue(rOutput.contains("Request Executed Successfully"));
	
		} catch (IOException e) {
			e.printStackTrace();
	}
}

    /*
    @Test (dataProvider="hotel")
    public void get_inventory(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
    	DefaultHttpClient httpclient = new DefaultHttpClient();
		    try {
				String requestXML = "<?xml version=\"1.0\" ?><hotel-get-inventory type=\"get\" xmlns=\"http://www.cleartrip.com/extranet/hotel-get-inventory\"><hotel-id>41031</hotel-id><room-type-id>"+roomtypeId+"</room-type-id><room-inventory><from-date>"+putDate1(1)+"</from-date><to-date>"+putDate1(30)+"</to-date></room-inventory></hotel-get-inventory>";
				Reporter.log(requestXML);
				HttpPost getinventory = new HttpPost(api_url() + "/chmm/service/get-inventory");
				System.out.println(getinventory);
				getinventory.addHeader("X-CT-API-KEY", apikey);
				getinventory.addHeader("X-CT-SOURCE-TYPE", "API");
				StringEntity input = new StringEntity(requestXML);
				getinventory.setEntity(input);
				HttpResponse httpResponse = httpclient.execute(getinventory);
		
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(httpResponse.getEntity().getContent())));
				String output;
				StringBuilder builder = new StringBuilder();
				while ((output = br.readLine()) != null) {
					builder.append(output);
				}
				String rOutput = builder.toString();
				System.out.println(rOutput);		
				Reporter.log(rOutput);
				Assert.assertTrue(rOutput.contains("Request Executed Successfully"));
		
			} catch (IOException e) {
				e.printStackTrace();
	}
}*/

    
}
