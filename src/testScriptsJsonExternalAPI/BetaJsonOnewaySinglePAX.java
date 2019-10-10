package testScriptsJsonExternalAPI;

import java.util.HashMap;
//EBL-6556
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ArrayListMultimap;

import testScriptsExternalAPICommon.CommonUtils;

public class BetaJsonOnewaySinglePAX extends CommonUtils{

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
	public void jsonOnewaySinglePAX_336(String from,String to,String[] carrier,String aCount,String cCount) throws Exception{
		int i=1;
		boolean flightUnavailable;
		do {
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		System.out.println(onwarddate);
		System.out.println(carrier);
		betagetFareKeyandFlightDetails(clientSearch1,fareKeys,from,to,carrier[1],aCount,cCount,onwarddate,"");
		
		
		
		//Test:do{/*

			flightUnavailable= false;
			String key1=fareKeys.get(String.valueOf(i)).toArray()[0].toString();
			//System.out.println(key1);
				String flightno = fareKeys.get(String.valueOf(i)).toArray()[0].toString().split(carrier[1].trim())[1].split("_")[1].trim();
				String amount=fareKeys.get(String.valueOf(i)).toArray()[1].toString().split("\\.")[0];
			JSONObject itinenaryId=betacreateItinerary(amount,key1,from,to,flightno,carrier[1],onwarddate,"");
			// if ((common.value("makePayment").equals("true"))) {  
			JSONObject jsonObject1 = betajsonPriceCheck(itinenaryId,clientSearch,"");
			//System.out.println(jsonObject1);
			Reporter.log(jsonObject1.toString());
		try{
			if(jsonObject1.get("succes_message").toString().equalsIgnoreCase("Price Check is success")) {
				bookingSuccess=true;
				flightUnavailable=false;
			}
			//System.out.println("----"+jsonObject1.get("succes_message"));
					
				
				
							 
				
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
			// }
			i++;

		}while(i<2 && flightUnavailable);
		
			}

	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		String[] carrier= {"SG","6E","UK"};
		
		return new Object[][]{
				{"DEL","BOM",carrier,"1","0"}


		};
	}
	//PayloadIntlSinglePAX.txt
	/*@AfterMethod (alwaysRun = true)
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	   //System.out.println("Test Case:" + _result.getMethod().getMethodName());
	  }*/
	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}*/

}
