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

public class CHMMCom_API_Extranet_Smoke extends IndiaHotels  {

	String debug = common.value("debug_mode");
    boolean debug_mode = Boolean.parseBoolean(debug);

    @DataProvider
    public Object [ ][ ] hotel() {
        //return new Object [ ] [ ] { { "319438","2699085","96602","14a49b19d6f4e5ce8aa92f3e01ffeae9" } };
    	return new Object [ ] [ ] { { "41031","2676204","32086","g9s45bsammqggtczpz3kj3qk" } };
    	//return new Object [ ] [ ] { { "41031","2676204","32086","8a1731a74e8016392fb9938492cf8407" } };
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

}
