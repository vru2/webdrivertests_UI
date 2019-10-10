// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.AgencyHotels;

	public class AgencyHotel_Cancellation_HQ extends AgencyHotels{
	public RemoteWebDriver driver;
	String TripID = null;
	
  @Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="HotelAgency", groups="Regression")
  public void agencyHotel_Cancel_HQ_562(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
			String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, 
			String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  	driver.manage().deleteAllCookies();
	  	agencyHotel_SrpUrl(driver, "Mumbai", "Maharashtra", "IN", "1", 24, 25);
		Map<String, String> AgencySRPData = agencyHotel_SRP(driver, Hotel_Name,"DATAVALIDATION");
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		TripID = agencyHotel_Paymentpage_NoSignOut(driver, Payment_Type, "", "Agency HQ Cancel :", Booking_Confirmation_Message);
		//driver.get("https://manit.agentbox.com/trips/"+TripID);
		  elementPresent_Time(driver, getObject("Air_Agency_Confirmation_Trips_Link"), 10);
		  safeClick(driver, getObject("Air_Agency_Confirmation_Trips_Link"));
		  if(!elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Header"), 30)){
			  refreshPage(driver);
		  }
		  elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Header"), 30);
		  elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), 30);
		  safeType(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), TripID);
		  safeClick(driver, getObject("Air_Agency_Trips_Page_Search_TripID_Submit"));
		  safeClick(driver, getObject("Air_Agency_Trips_Page_Trip_Link"));
		  
		  elementVisible(driver, By.linkText("Pricing & payment details"), 10);	
		  safeClick(driver, By.linkText("Pricing & payment details"));
		  if(!elementVisible(driver, By.xpath("//dt"), 10)){
			  safeClick(driver, By.linkText("Pricing & payment details"));
		  }
		  elementVisible(driver, By.xpath("//dt"), 10);
		  List<WebElement> List_Name = driver.findElements(By.xpath("//dt"));
		  List<WebElement> List_Price = driver.findElements(By.xpath("//dd"));
		  Map<String, String> AcctTripsPage = new HashMap<String, String>();
		  for (int i = 0; i < List_Price.size(); i++) {
			 String Name = List_Name.get(i).getText();
			 String Price = List_Price.get(i).getText();		
			 AcctTripsPage.put(Name, Price);
		}  
		String Commission = AcctTripsPage.get("Commission");
		//String TDSonCommission = AcctTripsPage.get("TDS on Commission");
		String AgencyPay = AcctTripsPage.get("Payable by Agency");	
		String CustomerPay = AcctTripsPage.get("Payable by Customer");
		
		Commission = Commission.replace("Rs. ", "").replace(" (-)", "");	
	//	TDSonCommission = TDSonCommission.replace("Rs. ", "");
		double commissionDouble = Double.parseDouble(Commission);
	//	double TDSonCommissionDouble = Double.parseDouble(TDSonCommission);
		int CommissionInt =(int) Math.round(commissionDouble);
	//	int TDSCommissionInt =(int) Math.round(TDSonCommissionDouble);
		AgencyPay = AgencyPay.replace("Rs. ", "");
		//CustomerPay = CustomerPay.replace("Rs. ", "");
		CustomerPay = CustomerPay.replaceAll("\\D+","");
		int AgencyPayInt = Integer.parseInt(AgencyPay);
		int CustomerPayInt = Integer.parseInt(CustomerPay);
		//int AgencyCommissionInt =  CommissionInt-TDSCommissionInt;	
	//	int CustomerPayCalcInt = AgencyPayInt+AgencyCommissionInt;
	/*	if(!(CustomerPayInt ==CustomerPayCalcInt)){
			Reporter.log("Commission : "+CommissionInt);
			Reporter.log("TDS on Commission Round Off : "+TDSCommissionInt);
			Reporter.log("Agency Pay  : "+AgencyPayInt);
			Reporter.log("Coustomer Pay  : "+CustomerPayInt);
			//Assert.assertTrue(false);		
		}*/
		if(MakePaymentOnlyInQA2){
	    	agencyHotel_HQ_Cancellation(driver, TripID, AgencySRPData);
			  }
	}



  @BeforeClass
  public void setUp() throws Exception {
	  driver=(RemoteWebDriver) getDriver(driver);
  }
  
  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
		browserClose(driver);    
  }
}