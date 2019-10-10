package testScriptsIndia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirDom_Connector_Search extends AirCommonMethod {
	public RemoteWebDriver driver = null;
	@Test(dataProvider="dp")
	public void connectorSearch_94(String from,String to,String carrier,String aCount,String cCount) throws ClientProtocolException, IOException, URISyntaxException{
		ArrayList<String> arrlist = new ArrayList<String>();
		String onwarddate=getModifiedDate(driver,common.value("Days_to_add_for_CurrentDate"));
		String returndate=getModifiedDate(driver,common.value("Days_to_add_for_CurrentDate_to_return"));
	DefaultHttpClient client = new DefaultHttpClient();
	//System.out.println(common.value("protocol1")+"//search?from="+from+"&to="+to+"&depart_date=15/12/2016&&adults=1&childs=0&infants=0&dep_time=0&ret_time=0&class=Economy&airline=&carrier=&x=57&y=16&flexi_search=no&tb=n&src=connector&debug=1");
	HttpGet get = new HttpGet(new URI("http://172.17.12.168:9080/airservice//search?from=DEL&to=BOM&depart_date=15/12/2018&&adults=1&childs=0&infants=0&dep_time=0&ret_time=0&class=Economy&airline=&carrier=&x=57&y=16&flexi_search=no&tb=n&src=connector&debug=1"));
	//HttpGet get = new HttpGet(new URI("http://10.10.21.107:9080/trips/Q1611070426"));
	 
	HttpResponse response = client.execute(get);
	BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	StringBuffer sb = new StringBuffer();
	String str="";
	while((str=br.readLine())!=null){
		//System.out.println("----"+str);
		sb.append(str);
	}
	String bookDetails=sb.toString();
	//System.out.println("-----"+bookDetails);
	int size=bookDetails.split("<airline>").length;
	//System.out.println(size);
    for(int i=1;i<size;i++){
    	String [ ] getbookingStatus = bookDetails.split("<airline>");
        String [ ] bookingStatus = getbookingStatus[i].split("</airline>");
       //System.out.println(bookingStatus[0]);
       //System.out.println("Air Lines= " + bookingStatus[0].toString().split("<code>")[1].split("</code>")[0]);
       try{
       arrlist.add(bookingStatus[0].toString().split("<code>")[1].split("</code>")[0]);
       }
       catch(Exception e){
       arrlist.add(bookingStatus[0]);
       }
       /* Reporter.log("bookingStatus= " + bookingStatus[0]);
    	//System.out.println("bookingStatus= "+i  + getbookingStatus[i]);
    	String [ ] bookingStatus = getbookingStatus[i].split("</airline>");
    	System.out.println("airlines= " + getbookingStatus[i].split("</airline>")[1]);*/
    	
    }
    Assert.assertEquals(true,arrlist.contains("6E"),"6E Airline Not Found");
    Assert.assertEquals(true,arrlist.contains("SG"),"SG Airline Not Found");
    Assert.assertEquals(true,arrlist.contains("AI"),"AI Airline Not Found");
    Reporter.log("6E="+arrlist.contains("6E"),true);
    Reporter.log("SG="+arrlist.contains("SG"),true);
    Reporter.log("AI="+arrlist.contains("AI"),true);
   // System.out.println(arrlist.contains("6E"));
   // System.out.println(arrlist.contains("AI"));
   // System.out.println(arrlist.contains("SG"));
  // System.out.println("bookingStatus= " + getbookingStatus[0]);
    //Reporter.log("bookingStatus= " + getbookingStatus[0]);
	}
	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"DEL","BOM","","1","0"}
				              
		 
		 };
		}
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}

