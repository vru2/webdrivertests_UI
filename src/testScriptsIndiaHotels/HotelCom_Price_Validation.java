// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import java.util.ArrayList;
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

import domainServices.IndiaHotels;

	public class HotelCom_Price_Validation extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	public Map<String, String> SRP = new HashMap<String, String>();
	public Map<String, String> Details = new HashMap<String, String>();
	public Map<String, String> Itinerary = new HashMap<String, String>();
	public Map<String, String> Payment = new HashMap<String, String>();
	public Map<String, String> Confirmation = new HashMap<String, String>();
	public Map<String, String> Account = new HashMap<String, String>();
	public Map<String, String> HQ = new HashMap<String, String>();

	public String TripID = null;
	
  @Test	
  public void HotelComPriceValidation() throws Exception {

	  driver.manage().deleteAllCookies();
      String SRP_Url = hotelSrpUrl(driver, "Pune", "Maharashtra", "IN"); 
	  driver.get(SRP_Url);
	   
	   //--------------------------------------------------------------------------------SRP Validation----------------------------------------------------------------------------------//
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 50, "Search Results Page has not loaded  :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 25)) {
			Reporter.log("Hotel SRP is displayed");
		} else {
			Reporter.log("Hotel SRP is not displayed");
		}
		
		safeClick(driver, By.xpath("(//a[contains(text(),'Total')])[2]"));
		elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
		safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
		Thread.sleep(5000);
		String HotelSRPPrice =null;
		if(elementVisible(driver, By.cssSelector("small.strikeOut"), 5)) {
			String Price = getText(driver, By.xpath("//h2/div/strong"));
			String[] SRPPrices = Price.split("Rs.");
			HotelSRPPrice = SRPPrices[2];			
		} else {	
			HotelSRPPrice = getText(driver, By.xpath("//h2/div/strong"));
		}
		String HotelSRPName = getText(driver, By.xpath("//li[1]/section/nav/ul/li[1]/h2"));				// Name
	
		HotelSRPPrice = HotelSRPPrice.replaceAll("[^0-9]", "");
		SRP.put("Name", HotelSRPName);
		SRP.put("Price", HotelSRPPrice);
		Reporter.log("SRP Name : "+SRP.get("Name"));
		Reporter.log("SRP Price : "+SRP.get("Price"));
		safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button"));
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		loop: for(int i=0; i<=20;i++) {
			if(elementPresent_Time(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)) {
				break loop;
			} 
			if(elementPresent_Time(driver, By.cssSelector("p.minPriceByline.weak"), 1)) {
				break loop;
			} 
			
			if(elementPresent_Time(driver, By.xpath("//div[@id='hotelDetailsHeader']/div/div/div[2]/div[2]/div[2]/strong"), 1)) {
				break loop;
			} 
			Thread.sleep(100);
		}
		
		String Modal_URL = driver.getCurrentUrl();
		driver.switchTo().window(tabs.get(0));
		driver.get(Modal_URL);
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));
		if(!(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Min_Rate"), 2) )) {
			refreshPage(driver);
		}
		 for(int i=0; i<=10;i++){
			if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Min_Rate"), 1)){
				 		break;
				 }
			else if(textPresent(driver, "Sorry, our system is acting up.", 1)){
				  Reporter.log("Sorry, our system is acting up. : message is displayed");
				  Assert.assertTrue(false);
			  }
			  
			  else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)){
				  Reporter.log("Sorry, this hotel is unavailable. : message is displayed");
				  Assert.assertTrue(false);
			  }
			  else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)){
				  Reporter.log("Whatever you're looking for, isn't here : message is displayed");
				  Assert.assertTrue(false);
			  }
			  else if(!elementVisible(driver, By.cssSelector("div.roomRate > div.row > div.perRoomPrDisp > span.loader.dotDotDot"), 1)){
				 break;
			 }
			 
		 }
			   if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
				   Reporter.log("Hotel Rate is displayed in Details page");
			   }
			   else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)) {
					String HotelName = getText(driver, By.xpath("//div[2]/div/div/h1"));
					Reporter.log("Sorry, this hotel is unavailable - No rates/ inventory for hotel :"+HotelName);
					Assert.assertTrue(false);
			   } 
			   if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Min_Rate"), 1)) {
			    String HotelDetailsPrice = getText(driver, getObjectHotels("HotelCom_DetailsPageNew_Min_Rate")).replaceAll("[^0-9]", "");
			    Details.put("Price", HotelDetailsPrice);
			   } else if(elementVisible(driver, By.cssSelector("p.minPriceByline.weak"), 1)) {
				   String HotelDetailsPrice = getText(driver,  By.cssSelector("p.minPriceByline.weak")).replaceAll("[^0-9]", "");			
				   Details.put("Price", HotelDetailsPrice);
			   } else Reporter.log("Itenarary Price not displayed");
				   
			   Reporter.log("Details Page Price : "+Details.get("Price"));
			   if(!Details.get("Price").equals(SRP.get("Price"))) {
			  		Reporter.log("SRP & Details page Hotel Price doesn't match");
			  		Reporter.log("SRP Price : "+SRP.get("Price")+" Details Price : "+Details.get("Price"));
			  		Assert.assertTrue(false);
			   }
			  /* smartClick(driver, By.linkText("Select Room"));
			   
			   smartClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"));	*/
			   safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
				Thread.sleep(500);
				safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));	
			   
	
	  //------------------------------------------------------------------------------Itinerary Validation----------------------------------------------------------------------------------// 	   
	  	 if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"), 30)) {
				Reporter.log("Hotel Book Step1 / Itinerary Page is not displayed");
		}
	 	 smartClick(driver, getObjectHotels("HotelCom_ItineraryPage_PayNow_RadioButton"));
	  	 String HotelItineraryName = getText(driver, By.xpath("//div[@id='itinBlock']/div/h1")); 				// Name
	  	String HotelItineraryPrice = getText(driver, getObjectHotels("HotelCom_BookStep1_Price"));	// Price
	  	String HotelItineraryRoomType = getText(driver, By.xpath("//div[@id='itinBlock']/div/div[3]/div/p"));// Room Type
	  	HotelItineraryName = HotelItineraryName.replace("Inclusions", "");
	  	HotelItineraryPrice = HotelItineraryPrice.replaceAll("[^0-9]", "");
	  	Itinerary.put("Name", HotelItineraryName);
	  	Itinerary.put("Price", HotelItineraryPrice);
	  	Itinerary.put("RoomType", HotelItineraryRoomType);
		   if(!Itinerary.get("Price").equals(SRP.get("Price"))) {
		  		Reporter.log("SRP & Itinerary page Hotel Price doesn't match");
		  		Reporter.log("SRP Price : "+SRP.get("Price")+" Itinerary Price : "+Itinerary.get("Price"));
		  		Assert.assertTrue(false);
		   }
	    	
	  	String Parentwindow = driver.getWindowHandle( );  
	  	safeClick(driver, By.linkText("(rate details)"));
	  	Thread.sleep(5000);
	  	driver.switchTo().frame("modal_window");
	  	Thread.sleep(5000);
	  	textPresent(driver, "Rate details", 10);
	  	String Itineray_Price_Without_tax = getText(driver, By.id("rtTotalAmount"));

	  	Itineray_Price_Without_tax = Itineray_Price_Without_tax.replaceAll("[^0-9]", "");
	  	Itinerary.put("PriceRateDetails", Itineray_Price_Without_tax);
		Reporter.log("Itinerary Name : "+Itinerary.get("Name"));
		Reporter.log("Itinerary Price : "+Itinerary.get("Price"));
		Reporter.log("Itinerary Room Type : "+Itinerary.get("RoomType"));
		Reporter.log("PriceRateDetails popup : "+Itinerary.get("PriceRateDetails")); 	 	  	
	  	
	  	driver.switchTo( ).window(Parentwindow); 
		safeClick(driver, By.id("close"));
		Reporter.log("Rate Details Popup :"+Itineray_Price_Without_tax);
			hotelCom_ItineraryPage(driver, "");			   
			hotelCom_LoginPage(driver, "SignIN", "");
			hotelCom_TravelerPage(driver);
  
	   //--------------------------------------------------------------------------------Payment Step Price Validation----------------------------------------------------------------------------------//
		   
	   if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 5)) {
		} else {
			Reporter.log("Hotel Book Step 4 / Payment Step is not displayed");
		}
	   String ProcessingFee = null;
	   if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_ProccessingFee"), 5)) {
		   ProcessingFee = getText(driver, getObjectHotels("HotelCom_BookStep4_ProccessingFee"));
		   ProcessingFee  = ProcessingFee.replaceAll("[^0-9]", "");
		   Payment.put("ProcessingFee", ProcessingFee);
		   int HotelItinerary_int = Integer.parseInt(Itinerary.get("Price")); 
		   int ProcessingFee_int = Integer.parseInt(ProcessingFee); 
		   HotelItinerary_int = ProcessingFee_int+HotelItinerary_int;
		   String ItineraryPriceProc = Integer.toString(HotelItinerary_int);
		   Itinerary.put("PriceProc", ItineraryPriceProc); // Int Price + Processing fee
	   }
	   elementVisible(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"), 20);
	   String PaymentPrice= getText(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"));
	   PaymentPrice = PaymentPrice.replaceAll("[^0-9]", "");
	   Payment.put("Price", PaymentPrice);
	   
	  if(!Payment.get("Price").contains(Itinerary.get("PriceProc"))) {
		   Reporter.log("Price in Payment page & Itinerary+Processing fee doesnt match");
		   Reporter.log("Payment Price : "+Payment.get("Price")+" Itinerary+Processing fee Price : "+Itinerary.get("PriceProc"));
		   Reporter.log("Payment Processengfee :"+Payment.get("ProcessingFee"));
	  		Assert.assertTrue(false);
	   }	

	   //--------------------------------------------------------------------------------Confirmation page Validation----------------------------------------------------------------------------------//
		
	  TripID = hotelCom_PaymentPage(driver, "CREDITCARD", "Price Valication ", "");
	if(MakePaymentOnlyInQA2){
	  String AccName = getText(driver, By.xpath("//h1")); //Name
	  Confirmation.put("Name", AccName);
	  List<WebElement> PriceName = driver.findElements(By.xpath("//dt"));
		List<WebElement> PriceValue = driver.findElements(By.xpath("//dd"));
		Map<String, String> PriceDetails = new HashMap<String, String>();
		
		for (int i = 1; i < PriceName.size(); i++) {
			String value = PriceValue.get(i).getText();
			value = value.replaceAll("[^0-9]", "");
			PriceDetails.put(PriceName.get(i).getText(), value);
			Reporter.log(PriceName.get(i).getText()+" : "+ value);
		}
		String Conf_Price = PriceDetails.get("Total:");	  
		String AccRoomType = getText(driver, By.xpath("//div[2]/div/p")); 
		String[] RoomType  = AccRoomType.split("Includes");
		String AccTripID =  getText(driver, By.xpath("//strong"));
		Confirmation.put("Price", Conf_Price); //Price
		Confirmation.put("RoomType", RoomType[0]); 
		Confirmation.put("TripID", AccTripID);
	  	Reporter.log("Confirmation Name "+Confirmation.get("Name"));
	  	Reporter.log("Confirmation Price : "+Confirmation.get("Price"));
	  	Reporter.log("Confirmation Room Type: "+Confirmation.get("RoomType"));
	  	Reporter.log("TripID: "+Confirmation.get("TripID"));  	
	  	
	    int Con_Price = Integer.parseInt(Confirmation.get("Price"));
	    int Con_PricePlus1 = Con_Price+1;
	    int Con_PriceMinus1 = Con_Price-1;
	    String Price = Integer.toString(Con_Price); 
	    String PricePlus1 = Integer.toString(Con_PricePlus1);
	    String PriceMinus1 = Integer.toString(Con_PriceMinus1);
		if(!Confirmation.get("Name").contains(SRP.get("Name"))) {
	  		Reporter.log("SRP & Confirmation page Hotel name doesn't match");
	  		Reporter.log("SRP Name : "+SRP.get("Name")+" Confirmation Name : "+Confirmation.get("Name"));
	  		Assert.assertTrue(false);
	  	}
	 
	  	if(!Itinerary.get("RoomType").contains(Confirmation.get("RoomType"))) {
	  		Reporter.log("Confirmation & Itinerary Page Room Type doesn't match");
	  		Reporter.log("Confirmation Room Type : "+Confirmation.get("RoomType")+" Itinerary Room Type : "+Itinerary.get("RoomType"));
	  		Assert.assertTrue(false);
	  	}
	  	
	 	if(!Itinerary.get("PriceProc").equals(Confirmation.get("Price"))) {
	  		Reporter.log("Confirmation & Itinerary Page Room Type doesn't match");
	  		Reporter.log("Confirmation Price : "+Confirmation.get("Price")+" Itinerary Price+Proc fee : "+Itinerary.get("PriceProc"));
	  		Assert.assertTrue(false);
	  	}
	   //--------------------------------------------------------------------------------Account Page Validation----------------------------------------------------------------------------------//
		
	    	  driver.get(baseUrl+"/account/trips/"+TripID);
		   	  elementVisible(driver, By.xpath("//h2/a"), 50);
		   	String AcctName = getText(driver, By.xpath("//h2/a")); //Name
		   	String AcctPrice = getText(driver, By.cssSelector("dd.total")); //Price
		   	String[] AcctPrice1 = AcctPrice.split("Rs. ");
		   	AcctPrice1[1] = AcctPrice1[1].replaceAll("[^0-9] ", ""); 
		    String AcctRoomType= getText(driver, By.xpath("//tbody/tr/td")); //Room Type
		   	String AcctTripID = getText(driver, By.xpath("//div[2]/div[2]/p")); //TripID
			AcctRoomType = AcctRoomType.replace("1 × ", "");
			Account.put("Name", AcctName);
			Account.put("Price", AcctPrice1[1]);
			Account.put("RoomType", AcctRoomType);
			Account.put("TripID", AcctTripID);
			Reporter.log("Account Name : "+Account.get("Name"));
			Reporter.log("Account Price : "+Account.get("Price"));
			Reporter.log("Account Room Type : "+Account.get("RoomType"));
			if(!Account.get("Name").contains(SRP.get("Name"))) {
		  		Reporter.log("SRP & Account page Hotel name doesn't match");
		  		Reporter.log("SRP Name : "+SRP.get("Name")+" Account Name : "+Account.get("Name"));
		  		Assert.assertTrue(false);
		  	}
			
			if(!Account.get("Price").contains(Payment.get("Price"))) {
				Reporter.log("Payment & Account page Hotel price doesn't match");
		  		Reporter.log("Payment Price : "+Payment.get("Price")+" Account Price : "+Account.get("Price"));
		  		Assert.assertTrue(false);
		  	}
		
 	   //-------------------------------------------------------------------------------HQ Validation----------------------------------------------------------------------------------//
		  
		driver.get(baseUrl+"/hq/trips/"+TripID);
		String HQName= getText(driver, By.xpath("//h1")); //Name
		String HQPrice = getText(driver, By.cssSelector("dd.total")); //Price		
	   	String[] HQPrice1 = HQPrice.split("Rs. ");
	   	HQPrice1[1] = HQPrice1[1].replaceAll("[^0-9] ", ""); 
		
		
	   	String HQRoomType = getText(driver, By.xpath("//div[@id='layer_1']/table/tbody/tr[4]/td[4]")); //Room Type
	   	HQRoomType = HQRoomType.replace("1 × ", "");

	   	HQ.put("Name", HQName );
	   	HQ.put("Price", HQPrice1[1] );
	   	HQ.put("RoomType", HQRoomType );

	   	Reporter.log("HQ Name : "+HQ.get("Name"));
	 	Reporter.log("HQ Price : "+HQ.get("Price"));
	 	Reporter.log("HQ RoomType : "+HQ.get("RoomType"));
	 	
	 	
	 	if(!HQ.get("Name").contains(SRP.get("Name"))) {
			Reporter.log("SRP & HQ page Hotel name doesn't match");
			Reporter.log("SRP Name : "+SRP.get("Name")+" HQ Name : "+HQ.get("Name"));
			Assert.assertTrue(false);
		}
	 	if(!HQ.get("Price").equals(Confirmation.get("Price"))) {
			Reporter.log("Confiramtion & HQ Page Price doesn't match");
			Reporter.log("Confirmation Price : "+Confirmation.get("Price")+" HQ Price : "+HQ.get("Price"));
			Assert.assertTrue(false);
		}		
	 	if(!Account.get("RoomType").contains(HQ.get("RoomType"))) {
			Reporter.log("HQ & Account Page Room Type doesn't match");
			Reporter.log("HQ Room Type : "+HQ.get("RoomType")+" Account Room Type : "+Account.get("RoomType"));
			Assert.assertTrue(false);
		}

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