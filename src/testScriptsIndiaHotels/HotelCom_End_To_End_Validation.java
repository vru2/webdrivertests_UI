// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - June, 2017
// Author - Kiran Kumar
// Copyright © 2017 cleartrip Travel. All rights reserved.

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

	public class HotelCom_End_To_End_Validation extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	public String HotelSRP[] = {"", "", "", "",""}; 
	public String HotelItinerary[] = {"", "", "", ""}; 
	public String HotelPayment[] = {"", "", "", ""}; 
	public String HotelConfirmation[] = {"", "", "", ""};
	public String HotelAccount[] = {"", "", "", ""};
	public String HotelHQ[] = {"", "", "", ""};
	
	public String TripID = null;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComBangalore")
  public void HotelComENDToEND_501(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {

	  driver.manage().deleteAllCookies();
      String SRP_Url = hotelSrpUrl(driver, City, State, Country); 
	  driver.get(SRP_Url);
	   
	   //--------------------------------------------------------------------------------SRP Validation----------------------------------------------------------------------------------//
		logMessagePageNotLoaded(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 50, "Search Results Page has not loaded  :( :( :( :( :( :(");
		if(elementVisible(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 25)) {
			Reporter.log("Hotel SRP is displayed");
		} else {
			Reporter.log("Hotel SRP is not displayed");
		}
		
		safeClick(driver, By.xpath("(//a[contains(text(),'Total')])[2]"));
		elementVisible(driver, getObject("HotelCom_SRP_Price_Filter"), 10);
		safeClick(driver, getObject("HotelCom_SRP_Price_Filter")); 	
		Thread.sleep(5000);
		HotelSRP[0] = getText(driver, By.xpath("//li[1]/section/nav/ul/li[1]/h2"));				// Name
		HotelSRP[1] = getText(driver, By.xpath("//h2/strong"));
		HotelSRP[1] = HotelSRP[1].substring(3);
		if(HotelSRP[1].contains("Rs.")){			
			String HotelSRP1[] = HotelSRP[1].split("Rs.");
			HotelSRP[1] = HotelSRP1[1];
		}
		
		Reporter.log("SRP Name : "+HotelSRP[0]);
		Reporter.log("SRP Price : "+HotelSRP[1]);
		elementVisible(driver, getObject("HotelCom_SRP_Price_Filter"), 10);
		safeClick(driver, getObject("HotelCom_SRP_Price_Filter"));
  	   hotelCom_SRP(driver, Hotel_Name,"");
 
	
	  //------------------------------------------------------------------------------Itinerary Validation----------------------------------------------------------------------------------// 	   
	  	 if(!elementVisible(driver, getObject("HotelCom_BookStep1_Continue_Button"), 30)) {
				Reporter.log("Hotel Book Step1 / Itinerary Page is not displayed");
		}
	 	 smartClick(driver, getObjectHotels("HotelCom_ItineraryPage_PayNow_RadioButton"));
	  	 HotelItinerary[0] = getText(driver, By.xpath("//div[@id='itinBlock']/div/h1")); 				// Name
	  	 HotelItinerary[1] = getText(driver, By.xpath("//dd/strong/span[2]"));  							// Price
	  	 HotelItinerary[2] = getText(driver, By.xpath("//div[@id='itinBlock']/div/div[3]/div/p"));// Room Type
	  	 HotelItinerary[2] = HotelItinerary[2].replace("Inclusions", "");
	   	 
	  	 	Reporter.log("Itinerary Name : "+HotelItinerary[0]);
	  	 	Reporter.log("Itinerary Price : "+HotelItinerary[1]);
	  	 	Reporter.log("Itinerary Room Type : "+HotelItinerary[2]);
			
	  	if(!HotelItinerary[0].contains(HotelSRP[0])) {
	  		Reporter.log("SRP & Itinerary page Hotel name doesn't match");
	  		Reporter.log("SRP Name : "+HotelSRP[0]+" Itinerary Name : "+HotelItinerary[0]);
	  		Assert.assertTrue(false);
	  	}
	  	
	  	String Parentwindow = driver.getWindowHandle( );  
	  	safeClick(driver, By.linkText("(rate details)"));
	  	Thread.sleep(5000);
	  	driver.switchTo().frame("modal_window");
	  	Thread.sleep(5000);
	  	textPresent(driver, "Rate details", 5);
	  	String Itineray_Price_Without_tax = getText(driver, By.id("rtTotalAmount"));
	  	Thread.sleep(5000);
	  	driver.switchTo( ).window(Parentwindow); 
		safeClick(driver, By.id("close"));
		if(!Itineray_Price_Without_tax.contains(HotelSRP[1])) {
	  		Reporter.log("SRP & Itinerary Page Price doesn't match");
	  		Reporter.log("SRP Price : "+HotelSRP[1]+" Itinerary Price without Tax : "+Itineray_Price_Without_tax);
	  		System.out.println("SRP Price : "+HotelSRP[1]+" Itinerary Price without Tax : "+Itineray_Price_Without_tax);
	  		Assert.assertTrue(false);
		}  
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_LoginPage(driver, "SignIN","");
		   hotelCom_TravelerPage(driver);
  
	   //--------------------------------------------------------------------------------Payment Step Price Validation----------------------------------------------------------------------------------//
		   
	   if(elementVisible(driver, getObject("HotelCom_BookStep4_MakePayment_Button"), 5)) {
		} else {
			Reporter.log("Hotel Book Step 4 / Payment Step is not displayed");
		}
	   
	   if(elementVisible(driver, getObject("HotelCom_BookStep4_ProccessingFee"), 5)) {
		   String ProcessingFee = getText(driver, getObject("HotelCom_BookStep4_ProccessingFee"));
		   if(ProcessingFee.contains("Rs.")){
			   ProcessingFee  = ProcessingFee.replace("Rs.", "");
		   }
		   int HotelItinerary_int = Integer.parseInt(HotelItinerary[1]); 
		   int ProcessingFee_int = Integer.parseInt(ProcessingFee); 
		   HotelItinerary_int = ProcessingFee_int+HotelItinerary_int;
		   HotelItinerary[1] = Integer.toString(HotelItinerary_int);
	   } 
	   elementVisible(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"), 20);
	   HotelPayment[1] = getText(driver, By.xpath("//p[@id='payBlockTotal']/label/strong")); //Price
	   Reporter.log("Payment Price "+HotelPayment[1]);

	  if(!HotelPayment[1].contains(HotelItinerary[1])) {
		   Reporter.log("Price in Payment page & Itinerary+Processing fee doesnt match");
	  		Reporter.log("Payment Price : "+HotelPayment[1]+" Itinerary+Processing fee Price : "+HotelItinerary[1]);
		   Assert.assertTrue(false);
	   }	

	   //--------------------------------------------------------------------------------Confirmation page Validation----------------------------------------------------------------------------------//
		
	  TripID = hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
	  HotelConfirmation[0] = getText(driver, By.xpath("//h1")); //Name
	  
	  List<WebElement> PriceName = driver.findElements(By.xpath("//dt"));
		List<WebElement> PriceValue = driver.findElements(By.xpath("//dd"));
		Map<String, String> PriceDetails = new HashMap<String, String>();
		
		for (int i = 1; i < PriceName.size(); i++) {
			String value = PriceValue.get(i).getText();
			value = value.replaceAll("RS. ", "");
		//	int valueInt = Integer.parseInt(value);
			PriceDetails.put(PriceName.get(i).getText(), value);
			Reporter.log(PriceName.get(i).getText()+" : "+ value);
		}
		String Conf_Price = PriceDetails.get("Total:");	  
	  //Conf_Price = Conf_Price.replace("RS. ", "");
	  HotelConfirmation[1] = Conf_Price; //Price
	  HotelConfirmation[2] = getText(driver, By.xpath("//div[2]/div/p")); //Room Type
	  HotelConfirmation[3] = getText(driver, By.xpath("//strong")); //TripID
	
	  	String[] RoomType  = HotelConfirmation[2].split("Includes");
	  	HotelConfirmation[2] = RoomType[0] ;
	  	Reporter.log("Confirmation Name "+HotelConfirmation[0]);
	  	Reporter.log("Confirmation Price : "+HotelConfirmation[1]);
	  	Reporter.log("Confirmation Room Type: "+HotelConfirmation[2]);
	  	
	    int Con_Price = Integer.parseInt(HotelConfirmation[1]);
	    int Con_PricePlus1 = Con_Price+1;
	    int Con_PriceMinus1 = Con_Price-1;
	    String Price = Integer.toString(Con_Price); 
	    String PricePlus1 = Integer.toString(Con_PricePlus1);
	    String PriceMinus1 = Integer.toString(Con_PriceMinus1);
		if(!HotelConfirmation[0].contains(HotelSRP[0])) {
	  		Reporter.log("SRP & Confirmation page Hotel name doesn't match");
	  		Reporter.log("SRP Name : "+HotelSRP[0]+" Confirmation Name : "+HotelConfirmation[0]);
	  		Assert.assertTrue(false);
	  	}
	  /*	if(!HotelConfirmation[1].contains(HotelSRP[1])) {
	  		Reporter.log("SRP & Itinerary Page Price doesn't match");
	  		Reporter.log("SRP Price : "+HotelSRP[1]+" Confirmation Price : "+HotelConfirmation[1]);
	  		Assert.assertTrue(false);
	  	}*/
		if(!(Price.contains(HotelItinerary[1]) ||PricePlus1.contains(HotelItinerary[1]) || PriceMinus1.contains(HotelItinerary[1])  )){
			Reporter.log("SRP & Itinerary Page Price doesn't match");
	  		Reporter.log("Itinerary+Processing fee Price : "+HotelItinerary[1]+" Confirmation Price : "+HotelConfirmation[1]);
	  		Assert.assertTrue(false);
		}
		
		/*if(!HotelConfirmation[1].contains(HotelItinerary[1])) {
	  		Reporter.log("SRP & Itinerary Page Price doesn't match");
	  		System.out.println("Itinerary+Processing fee Price : "+HotelItinerary[1]+" Confirmation Price : "+HotelConfirmation[1]);
	  		Reporter.log("Itinerary+Processing fee Price : "+HotelItinerary[1]+" Confirmation Price : "+HotelConfirmation[1]);
	  		Assert.assertTrue(false);
	  	}*/
	  	if(!HotelItinerary[2].contains(HotelConfirmation[2])) {
	  		Reporter.log("Confirmation & Itinerary Page Room Type doesn't match");
	  		Reporter.log("Confirmation Room Type : "+HotelConfirmation[2]+" Itinerary Room Type : "+HotelItinerary[2]);
	  		Assert.assertTrue(false);
	  	}
	   //--------------------------------------------------------------------------------Account Page Validation----------------------------------------------------------------------------------//
		
	  		  hotelCom_Account_Invoice_Validation(driver, TripID);
	  	
		   	  driver.get(baseUrl+"/account/trips/"+TripID);
		   	  elementVisible(driver, By.xpath("//h2/a"), 50);
		   	  HotelAccount[0] = getText(driver, By.xpath("//h2/a")); //Name
			  HotelAccount[1] = getText(driver, By.cssSelector("dd.total")); //Price
			  HotelAccount[2] = getText(driver, By.xpath("//tbody/tr/td")); //Room Type
			  HotelAccount[3] = getText(driver, By.xpath("//div[2]/div[2]/p")); //TripID
			  HotelAccount[2] = HotelAccount[2].replace("1 ×", "");
			  HotelAccount[3] = HotelAccount[3].replace("Trip ID : ", "");
		  	Reporter.log("Account Name : "+HotelAccount[0]);
			Reporter.log("Account Price : "+HotelAccount[1]);
			Reporter.log("Account Room Type : "+HotelAccount[2]);
				  
			if(!HotelAccount[0].contains(HotelSRP[0])) {
		  		Reporter.log("SRP & Account page Hotel name doesn't match");
		  		Reporter.log("SRP Name : "+HotelSRP[0]+" Account Name : "+HotelConfirmation[0]);
		  		Assert.assertTrue(false);
		  	}
		  /*	if(!HotelAccount[1].contains(HotelSRP[1])) {
		  		Reporter.log("SRP & Account Page Price doesn't match");
		  		Reporter.log("SRP Price : "+HotelSRP[1]+" Account Price : "+HotelConfirmation[1]);
		  		Assert.assertTrue(false);
		  	}	*/
			
			if(!HotelAccount[1].contains(HotelConfirmation[1])) {
		  		Reporter.log("Confirmation page & Account Page Price doesn't match");
		  		Reporter.log("Confirmation page Price : "+HotelConfirmation[1]+" Account Price : "+HotelConfirmation[1]);
		  		Assert.assertTrue(false);
		  	}	
		  	if(!HotelItinerary[2].contains(HotelAccount[2])) {
		  		Reporter.log("Account & Itinerary Page Room Type doesn't match");
		  		Reporter.log("Account Room Type : "+HotelAccount[2]+" Itinerary Room Type : "+HotelItinerary[2]);
		  	}
 	   //-------------------------------------------------------------------------------HQ Validation----------------------------------------------------------------------------------//
		  	hotelCom_HQ_Invoice_Validation(driver, TripID);
		    hotelCom_HQ_Voucher_Validation(driver, TripID);
		driver.get(baseUrl+"/hq/trips/"+TripID);
		HotelHQ[0] = getText(driver, By.xpath("//h1")); //Name
		HotelHQ[1] = getText(driver, By.cssSelector("dd.total")); //Price
		HotelHQ[2] = getText(driver, By.xpath("//div[@id='layer_1']/table/tbody/tr[4]/td[4]")); //Room Type
		HotelHQ[3] = getText(driver, By.xpath("//h1")); //TripID
		HotelHQ[2] = HotelHQ[2].replace("1 ×", "");
	
		Reporter.log("HQ Name : "+HotelHQ[0]);
	 	Reporter.log("HQ Price : "+HotelHQ[1]);

 	if(!HotelHQ[0].contains(HotelSRP[0])) {
		Reporter.log("SRP & HQ page Hotel name doesn't match");
		Reporter.log("SRP Name : "+HotelSRP[0]+" HQ Name : "+HotelHQ[0]);
		Assert.assertTrue(false);
	}
	if(!HotelHQ[1].contains(HotelConfirmation[1])) {
		Reporter.log("Confiramtion & HQ Page Price doesn't match");
		Reporter.log("Confirmation Price : "+HotelConfirmation[1]+" HQ Price : "+HotelHQ[1]);
		Assert.assertTrue(false);
	}		  
	if(!HotelAccount[2].contains(HotelHQ[2])) {
		Reporter.log("HQ & Account Page Room Type doesn't match");
		Reporter.log("HQ Room Type : "+HotelHQ[2]+" Account Room Type : "+HotelAccount[2]);
		Assert.assertTrue(false);
	}
	if(!HotelHQ[3].contains(HotelAccount[3])) {
		Reporter.log("HQ & Account Page Room Type doesn't match");
		Reporter.log("HQ TripID : "+HotelHQ[3]+" Account TripID : "+HotelAccount[3]);
		Assert.assertTrue(false);
	}	
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