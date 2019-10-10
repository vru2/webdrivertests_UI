package testScriptsJsonExternalAPI;
import java.io.BufferedReader;
import java.util.HashMap;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ArrayListMultimap;

import testScriptsExternalAPICommon.CommonUtils;
public class JsonOnewaySinglePAX_GoAir extends CommonUtils{
	public RemoteWebDriver driver = null;

    DefaultHttpClient clientSearch = null;
    DefaultHttpClient clientSearch1 = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	HashMap<String,String> hp=new HashMap<String,String>();
	String payLoadFileName = "PayloadSinglePAX.txt";
	boolean bookingSuccess = false;
	String tripId= null;
	int id;
	String airlinepnr=null;
	String ticketnumber=null;
	String bookingstatus=null;
	String totalfare=null;
	String basefare=null;
	String discount=null;
	String taxes=null;
	String cashback=null;
	String responseString;
	String value;
	String ticketNumber;
	String term="";
	@Test(dataProvider="dp", groups={ "Smoke Test"})
	public void jsonOnewaySinglePAX_336(String from,String to,String carrier,String aCount,String cCount) throws Exception{
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		getFareKeyandFlightDetails(clientSearch1,fareKeys,from,to,carrier,aCount,cCount,onwarddate,"");
		
		
		int i=1;
		boolean flightUnavailable;
		Test:do{

			flightUnavailable= false;
			String key1=fareKeys.get(String.valueOf(i)).toArray()[0].toString();
			//System.out.println(key1);
				String flightno = fareKeys.get(String.valueOf(i)).toArray()[0].toString().split(carrier.trim())[1].split("_")[1].trim();
				String amount=fareKeys.get(String.valueOf(i)).toArray()[1].toString().split("\\.")[0];
			JSONObject itinenaryId=createItinerary(amount,key1,from,to,flightno,carrier,onwarddate,"");
			 if ((common.value("makePayment").equals("true"))) {  
			JSONObject jsonObject1 = jsonBooking(itinenaryId,clientSearch,"");
		//	System.out.println(jsonObject1);
			try{
					String tripId = jsonObject1.getString("trip_id");
					JSONArray pxPrcngInfo = jsonObject1.getJSONArray("pax_pricing_info_list");
					for (int loop1 = 0; loop1 <= (pxPrcngInfo.length()-1); loop1++){
						JSONObject paxObject = pxPrcngInfo.getJSONObject(loop1);
						JSONArray bookingInfoArray = paxObject.getJSONArray("booking_info_list");
						for (int loop2 = 0; loop2 <= (bookingInfoArray.length()-1); loop2++){
							JSONObject bookingInfoObject = bookingInfoArray.getJSONObject(loop2);
							airlinepnr = bookingInfoObject.getString("airline_pnr");
							ticketNumber=bookingInfoObject.getString("ticket_number");
							bookingstatus=bookingInfoObject.getString("booking_status");
							
						}
					}
				
				Reporter.log(responseString);
				
				System.out.println("trip-id="+jsonObject1.getString("trip_id"));
				Reporter.log("trip-id="+jsonObject1.getString("trip_id"));
				tripId = jsonObject1.getString("trip_id");
				Reporter.log("airline-pnr="+airlinepnr);
				System.out.println("airline-pnr="+airlinepnr);
				Reporter.log("TicketNumber="+ticketNumber);
				System.out.println("TicketNumber="+ticketNumber);
				
				Reporter.log("booking status="+bookingstatus);
				System.out.println("booking status="+bookingstatus);
				
				
							 
				bookingSuccess=true;
				flightUnavailable=false;
		}
		catch(Exception e){
			System.out.println(jsonObject1.getString("error_message"));
			Reporter.log(jsonObject1.getString("error_message"));
			bookingSuccess=false;
		}
			//}
			/*catch(Exception e){
				////System.out.println("response string=="+responseString);
				//Reporter.log("response string=="+responseString);
				flightUnavailable=true;
			}*/
			 }
			i++;

		}while(i<1 && flightUnavailable);
		if ((common.value("makePayment").equals("true"))) { 
		Assert.assertTrue(bookingSuccess,"Booking Failed");
		}
			}

	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"DEL","BOM","G8","1","0"}


		};
	}
	//PayloadIntlSinglePAX.txt
	@AfterMethod (alwaysRun = true)
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	   //System.out.println("Test Case:" + _result.getMethod().getMethodName());
	  }
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}