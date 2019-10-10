// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_Payment_PAH_SignedIN extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComPAH")
  public void PayAtHotel_525(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   	driver.manage().deleteAllCookies(); 
	   	driver.get(baseUrl); 
	  // 	hotelCom_HomepageSignIn(driver,"");
	   	hotelCom_AddCookie(driver);
	   	hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAH"), 60, "MultipleRoomNights");
		hotelCom_ItineraryPage(driver, "PAH");
	 	hotelCom_TravelerPage_PAH(driver);  
	 	String TripID = hotelCom_PaymentPage_PAH(driver, "PAH", "PAH Signin", Booking_Confirmation_Message );
	 	hotelCom_ConfirmationPageValidation(driver, "PAH", "", "Your booking is done");
	 	driver.get( baseUrl+"/account/trips/"+TripID);
		elementVisible(driver, By.xpath("//td[4]/span"), 50);
		String Trip_Status=getText(driver, By.xpath("//td[4]/span"));
		if(!Trip_Status.equals("CONFIRMED")){
			Reporter.log("Trip status in Acccount is not CONFIRMED, It is dispalyed as "+Trip_Status);
			Assert.assertTrue(false);
		}
		String Trip_Voucher=getText(driver, By.xpath("//td[3]"));
		if(!Trip_Voucher.contains("CHMM")){
			Reporter.log("Trip Voucher doesnt contains CHMM, It is dispalyed as "+Trip_Voucher);
			Assert.assertTrue(false);
		}
		
		driver.get( baseUrl+"/hq/trips/"+TripID);
		if(!textPresent(driver,"Tips, tools & extras", 5)){
			refreshPage(driver);
		}
		if(!textPresent(driver, "This is a Pay At Hotel Booking.", 5)){
			Reporter.log("This is a Pay At Hotel Booking. : message is not displayed");
			Assert.assertTrue(false);
		}
		Trip_Status=getText(driver, By.xpath("//td[5]"));
		if(!Trip_Status.equals("Reserved")){	
			Reporter.log("Trip status is not 'Reserved', It is dispalyed as "+Trip_Status);
		//	Assert.assertTrue(false);
		}	
		Trip_Voucher=getText(driver, By.xpath("//tr[3]/td[3]"));
		if(!Trip_Voucher.contains("CHMM")){
			Reporter.log("Trip Voucher doesnt contain CHMM, It is dispalyed as "+Trip_Voucher);
			Assert.assertTrue(false);
		}
		Map<String, Integer	> HQData =new HashMap<String, Integer>();
		 
		 List<WebElement> RateName = driver.findElements(By.xpath("//div[9]/div/dl/dt"));
		 List<WebElement> RateValue = driver.findElements(By.xpath("//div[9]/div/dl/dd"));
		/* for (int i=0; i<=2;i++) {
			String Name = RateName.get(i).getText();
			String Ratevalue = RateValue.get(i).getText();
			Ratevalue = Ratevalue.replace("Rs. ", "").replace(",", "");
			double d = Double.parseDouble(Ratevalue);
			long FinalValue = Math.round(d);	
			int Value = (int ) (long) FinalValue;
			HQData.put(Name, Value);
		}*/
		 
		 for (int i = 0; i < RateName.size()-1; i++) {
			 	String Name = RateName.get(i).getText();
			 	String Ratevalue = RateValue.get(i).getText();
				Ratevalue = Ratevalue.replace("Rs. ", "").replace(",", "");
				double d = Double.parseDouble(Ratevalue);
				long FinalValue = Math.round(d);	
				int Value = (int ) (long) FinalValue;
				HQData.put(Name, Value);
			 if(Name.equals("Total")){
				 break;
			 }
		}
	  /*  safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Print_Voucher_Link"));
		elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Voucher_TripID"), 30);
		
		if(!elementVisible(driver, By.xpath("//td[2]/div/table/tbody/tr[1]/td[2]"), 5)){
			Reporter.log("Rate details are not displayed in Voucher");
			Assert.assertTrue(false);
		}
		Map<String, Integer	> VoucherData =new HashMap<String, Integer>();
		 
	for(int i=1; i<=3;i++){
		String RateNameVoucher_Xpath = "//td[2]/div/table/tbody/tr["+i+"]/td";
		String RateValueVoucher_Xpath = "//td[2]/div/table/tbody/tr["+i+"]/td[2]";
		String Name = getText(driver, By.xpath(RateNameVoucher_Xpath));
		String RateValue1 = getText(driver, By.xpath(RateValueVoucher_Xpath));
		RateValue1 = RateValue1.replace("Rs. ", "").replace(",", "");
		double d = Double.parseDouble(RateValue1);
		long FinalValue = Math.round(d);	
		int Value = (int ) (long) FinalValue;
		VoucherData.put(Name, Value);
		 if(Name.equals("Total:")){
			 break;
		 }
		}
		
	if(!VoucherData.get("Room Rate").equals(HQData.get("Room Rate"))){
		Reporter.log("Voucher & HQ Room Rate is not equal : Voucher Room Rate is "+VoucherData.get("Room Rate")+" HQ Room Rate is "+HQData.get("Room Rate"));
		Assert.assertTrue(false);
	}
	if(!VoucherData.get("Total:").equals(HQData.get("Total"))){
		Reporter.log("Voucher & HQ Total is not equal : Voucher Total is "+VoucherData.get("Total")+" HQ Total is "+HQData.get("Total"));
		Assert.assertTrue(false);
	}*/
	/*
	if(!VoucherData.get("Hotel Taxes").equals(HQData.get("Hotel Taxes"))){
		Reporter.log("Voucher & HQ Hotel Taxes is not equal : Voucher Hotel Taxes is "+VoucherData.get("Hotel Taxes")+" HQ Hotel Taxes is "+HQData.get("Hotel Taxes"));
		Assert.assertTrue(false);
	}*/
	 	
  }

  @BeforeClass
  public void setUp() throws Exception {	 
	  driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
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