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

public class CHMMCom_API_Extranet extends IndiaHotels  {

	String debug = common.value("debug_mode");
    boolean debug_mode = Boolean.parseBoolean(debug);

    @DataProvider
    public Object [ ][ ] hotel() {
        //return new Object [ ] [ ] { { "319438","2699085","96602","14a49b19d6f4e5ce8aa92f3e01ffeae9" } };
    	return new Object [ ] [ ] { { "41031","2676204","32086","g9s45bsammqggtczpz3kj3qk" } };
    	//return new Object [ ] [ ] { { "41031","2676204","32086","8a1731a74e8016392fb9938492cf8407" } };
    }

@Test (dataProvider="hotel")
public void get_room_types(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
	DefaultHttpClient httpclient = new DefaultHttpClient();
	try {
		
		String requestXML = "<?xml version=\"1.0\" ?><hotel-room-types type=\"get\" xmlns=\"http://www.cleartrip.com/extranet/hotel-room-types\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><hotel-id>"+hotelId+"</hotel-id></hotel-room-types>";
		HttpPost getroomtypes = new HttpPost(api_url() + "/chmm/service/get-room-types");
		getroomtypes.addHeader("X-CT-API-KEY",
				apikey);
		getroomtypes.addHeader("X-CT-SOURCE-TYPE", "API");
	//	Reporter.log(requestXML);
		StringEntity input = new StringEntity(requestXML);
		getroomtypes.setEntity(input);
		HttpResponse httpResponse = httpclient.execute(getroomtypes);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(httpResponse.getEntity().getContent())));
		String output;
		StringBuilder builder = new StringBuilder();
		while ((output = br.readLine()) != null) {
			builder.append(output);
		}
		String rOutput = builder.toString();
		if(debug_mode){
			////System.out.println(rOutput);
		}
		//Reporter.log(rOutput);
	    Assert.assertTrue(rOutput.contains("Request Executed Successfully"));
	    
	} catch (IOException e) {
		e.printStackTrace();
	}

}

@Test (dataProvider="hotel")
public void get_inventory(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
	DefaultHttpClient httpclient = new DefaultHttpClient();
	try {
		String requestXML = "<?xml version=\"1.0\" ?><hotel-get-inventory type=\"get\" xmlns=\"http://www.cleartrip.com/extranet/hotel-get-inventory\"><hotel-id>41031</hotel-id><room-type-id>"+roomtypeId+"</room-type-id><room-inventory><from-date>"+putDate1(1)+"</from-date><to-date>"+putDate1(30)+"</to-date></room-inventory></hotel-get-inventory>";

		//Reporter.log(requestXML);
		
		HttpPost getinventory = new HttpPost(api_url() + "/chmm/service/get-inventory");
		getinventory.addHeader("X-CT-API-KEY",
				apikey);
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
		////System.out.println(rOutput);
		if(debug_mode){
			////System.out.println(rOutput);
		}
		Reporter.log(rOutput);
		////System.out.println(rOutput);
		////System.out.println("---------------------------------------");
		Assert.assertTrue(rOutput.contains("Request Executed Successfully"));

	} catch (IOException e) {
		e.printStackTrace();
	}
}

@Test (dataProvider="hotel")
public void get_rateplan(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
	DefaultHttpClient httpclient = new DefaultHttpClient();
	try {
		String requestXML ="<?xml version=\"1.0\" ?><hotel-rate-plan type=\"get\" xmlns=\"http://www.cleartrip.com/extranet/hotel-rate-plan\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.cleartrip.com/extranet/hotel-rate-plan hotel-rate-plan.xsd\"><hotel-id>"+hotelId+"</hotel-id><room-id>"+roomtypeId+"</room-id></hotel-rate-plan>";

		Reporter.log(requestXML);
		
		HttpPost getrateplan =new HttpPost(api_url() + "/chmm/service/get-rate-plan");
		getrateplan.addHeader("X-CT-API-KEY",
				apikey);
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
		////System.out.println(rOutput);
		if(debug_mode){
			////System.out.println(rOutput);
		}
		Reporter.log(rOutput);
		////System.out.println(rOutput);
		Assert.assertTrue( rOutput.contains("Request Executed Successfully"));

	} catch (IOException e) {
		e.printStackTrace();
	}

} 

@Test (dataProvider="hotel")
public void get_rate_info(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
	DefaultHttpClient httpclient = new DefaultHttpClient();
	try {
		String requestXML= "<?xml version=\"1.0\" ?><hotel-rate-info type=\"get\" xmlns=\"http://www.cleartrip.com/extranet/hotel-rate-info\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.cleartrip.com/extranet/hotel-rate-info hotel-rate-info.xsd\"><hotel-id>"+hotelId+"</hotel-id><room-type-id>"+roomtypeId+"</room-type-id><rate-id>"+rateId+"</rate-id><from-date>"+putDate1(1)+"</from-date><to-date>"+putDate1(30)+"</to-date></hotel-rate-info>";

		Reporter.log(requestXML);
		
		HttpPost getrateinfo =new HttpPost(api_url() + "/chmm/service/get-rate-info");
		getrateinfo.addHeader("X-CT-API-KEY",
				apikey);
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
		////System.out.println(rOutput);
		if(debug_mode){
			////System.out.println(rOutput);
		}
		Reporter.log(rOutput);
		Assert.assertTrue(rOutput.contains("Request Executed Successfully"));

	} catch (IOException e) {
		e.printStackTrace();
	}

}

@Test (dataProvider="hotel")
public void push_Inventory(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
	DefaultHttpClient httpclient = new DefaultHttpClient();
	try {
		String requestXML= "<?xml version=\"1.0\" ?><hotel-inventory type=\"update\" xmlns=\"http://www.cleartrip.com/extranet/hotel-inventory\"><hotel-id>"+hotelId+"</hotel-id><room-type-id>"+roomtypeId+"</room-type-id><room-inventories><room-inventory><from-date>"+putDate1(1)+"</from-date><to-date>"+putDate1(10)+"</to-date><applicable-days>ALL</applicable-days><inventory>12</inventory><release-hours>24</release-hours></room-inventory></room-inventories></hotel-inventory>";

		Reporter.log(requestXML);
		
		HttpPost pushinventory =new HttpPost(api_url() + "/chmm/service/push-inventory");
		pushinventory.addHeader("X-CT-API-KEY",
				apikey);
		pushinventory.addHeader("X-CT-SOURCE-TYPE", "API");
		StringEntity input = new StringEntity(requestXML);
		pushinventory.setEntity(input);
		HttpResponse httpResponse = httpclient.execute(pushinventory);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(httpResponse.getEntity().getContent())));
		String output;
		StringBuilder builder = new StringBuilder();
		while ((output = br.readLine()) != null) {
			builder.append(output);
		}
		String rOutput = builder.toString();
		////System.out.println(rOutput);
		if(debug_mode){
			////System.out.println(rOutput);
		}
		Reporter.log(rOutput);
		Assert.assertTrue(rOutput.contains("XML Updated Successfully"));

	} catch (IOException e) {
		e.printStackTrace();
	}

}

    
@Test (dataProvider="hotel")
public void push_Inventory_specificdays(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
	DefaultHttpClient httpclient = new DefaultHttpClient();
	try {
		String requestXML= "<?xml version=\"1.0\" ?><hotel-inventory type=\"update\" xmlns=\"http://www.cleartrip.com/extranet/hotel-inventory\"><hotel-id>"+hotelId+"</hotel-id><room-type-id>"+roomtypeId+"</room-type-id><room-inventories><room-inventory><from-date>"+putDate1(1)+"</from-date><to-date>"+putDate1(30)+"</to-date><applicable-days>MON,TUE,FRI</applicable-days><inventory>5</inventory><release-hours>24</release-hours></room-inventory></room-inventories></hotel-inventory>";

		Reporter.log(requestXML);
		
		HttpPost pushinventoryspecificdays =new HttpPost(api_url() + "/chmm/service/push-inventory");
		pushinventoryspecificdays.addHeader("X-CT-API-KEY",
				apikey);
		pushinventoryspecificdays.addHeader("X-CT-SOURCE-TYPE", "API");
		StringEntity input = new StringEntity(requestXML);
		pushinventoryspecificdays.setEntity(input);
		HttpResponse httpResponse = httpclient.execute(pushinventoryspecificdays);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(httpResponse.getEntity().getContent())));
		String output;
		StringBuilder builder = new StringBuilder();
		while ((output = br.readLine()) != null) {
			builder.append(output);
		}
		String rOutput = builder.toString();
		////System.out.println(rOutput);
		if(debug_mode){
			////System.out.println(rOutput);
		}
		Reporter.log(rOutput);
		Assert.assertTrue(rOutput.contains("XML Updated Successfully"));

	} catch (IOException e) {
		e.printStackTrace();
	}
}


 /*   @Test (dataProvider="hotel")
public void push_rate(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
	DefaultHttpClient httpclient = new DefaultHttpClient();
	try {
		String requestXML= "<?xml version=\"1.0\" ?><hotel-room-rate type=\"update\" xmlns=\"http://www.cleartrip.com/extranet/hotel-room-rate\"><hotel-id>"+hotelId+"</hotel-id><room-type-id>"+roomtypeId+"</room-type-id><rate-id>"+rateId+"</rate-id><rate-name>Standard AC Room</rate-name><rate-type>SELL</rate-type><room-rates><room-rate><from-date>"+putDate1(1)+"</from-date><to-date>"+putDate1(30)+"</to-date><single-rate>1600</single-rate><double-rate>2200</double-rate><applicable-days>ALL</applicable-days></room-rate></room-rates></hotel-room-rate>";

		Reporter.log(requestXML);
		
		HttpPost pushrate =new HttpPost(api_url() + "/chmm/service/push-rate");
		pushrate.addHeader("X-CT-API-KEY",
				apikey);
       pushrate.addHeader("X-CT-SOURCE-TYPE", "API");
		StringEntity input = new StringEntity(requestXML);
		pushrate.setEntity(input);
		HttpResponse httpResponse = httpclient.execute(pushrate);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(httpResponse.getEntity().getContent())));
		String output;
		StringBuilder builder = new StringBuilder();
		while ((output = br.readLine()) != null) {
			builder.append(output);
		}
		String rOutput = builder.toString();
		////System.out.println(rOutput);
		if(debug_mode){
			////System.out.println(rOutput);
		}
		Reporter.log(rOutput);
		Assert.assertTrue(rOutput.contains("XML Updated Successfully"));

	} catch (IOException e) {
		e.printStackTrace();
	}

}*/

/*@Test (dataProvider="hotel")
public void push_rate_specificdays(String hotelId,String roomtypeId,String rateId,String apikey) throws Exception {
	DefaultHttpClient httpclient = new DefaultHttpClient();
	try {
		String requestXML= "<?xml version=\"1.0\" ?><hotel-room-rate type=\"update\" xmlns=\"http://www.cleartrip.com/extranet/hotel-room-rate\"><hotel-id>"+hotelId+"</hotel-id><room-type-id>"+roomtypeId+"</room-type-id><rate-id>"+rateId+"</rate-id><rate-name>Standard AC Room</rate-name><rate-type>SELL</rate-type><room-rates><room-rate><from-date>"+putDate1(1)+"</from-date><to-date>"+putDate1(30)+"</to-date><single-rate>1600</single-rate><double-rate>2200</double-rate><applicable-days>MON,TUE,FRI</applicable-days></room-rate></room-rates></hotel-room-rate>";

		Reporter.log(requestXML);
		
		HttpPost pushratespecificdays =new HttpPost(api_url() + "/chmm/service/push-rate");
		pushratespecificdays.addHeader("X-CT-API-KEY",
				apikey);
       pushratespecificdays.addHeader("X-CT-SOURCE-TYPE", "API");
		StringEntity input = new StringEntity(requestXML);
		pushratespecificdays.setEntity(input);
		HttpResponse httpResponse = httpclient.execute(pushratespecificdays);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(httpResponse.getEntity().getContent())));
		String output;
		StringBuilder builder = new StringBuilder();
		while ((output = br.readLine()) != null) {
			builder.append(output);
		}
		String rOutput = builder.toString();
		////System.out.println(rOutput);
		if(debug_mode){
			////System.out.println(rOutput);
		}
		Reporter.log(rOutput);
		Assert.assertTrue(rOutput.contains("XML Updated Successfully"));

	} catch (IOException e) {
		e.printStackTrace();
	}
}*/

}
