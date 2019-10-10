package testScriptsAccounts;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.Accounts;

public class MyTrips_B2C extends Accounts {
	public RemoteWebDriver driver = null;
	String domain = "com";
	
	@Test
	public void B2CTripsPage() throws Exception {
	  
		driver.manage().deleteAllCookies(); 
	  driver.get(baseUrl);
	  if(ProductionUrl){
			Accounts_HomepageSignInProd(driver);
			   driver.get(driver.getCurrentUrl());
			  elementVisible(driver,getObjectPlatform("Account_Your_Trips"), 10);
		  	   safeClick(driver,getObjectPlatform("Account_Your_Trips"));
		  	   safeClick(driver,getObjectPlatform("B2C_ACCOUNT_TRIPSLINK"));
		  	   Thread.sleep(2000);
		  	   textPresent_Log(driver,"Trip ID",5);
		  	   Thread.sleep(2000);
		  	   elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader > h3"), 10);
		  	   elementPresent_log(driver,getObject("Acc_ExpressWay_Tab"),"expressway tab",5);
		}else{
			Accounts_HomepageSignIn(driver);
			   driver.get(driver.getCurrentUrl());
	   elementVisible(driver,getObjectPlatform("Account_Your_Trips"), 10);
	   safeClick(driver,getObjectPlatform("Account_Your_Trips"));
	   safeClick(driver,getObjectPlatform("B2C_ACCOUNT_TRIPSLINK"));
	   elementPresent_log(driver,getObject("Acc_Trips_Upcoming_Tab"),"Upcoming trips tab",20);
	   elementPresent_log(driver,getObject("Acc_Trips_AllTrips_Tab"),"All trips tab",5);
	   textPresent_Log(driver,"Trip ID",5);
	   Thread.sleep(2000);
	   elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader > h3"), 10);
	   elementPresent_log(driver,getObject("Acc_ExpressWay_Tab"),"expressway tab",5);
	    // Trips tab
	    safeClick(driver,getObject("Acc_Trips_Upcoming_Tab"));
		}
	    String TripID = null;
	  if(ProductionUrl) {
		TripID="190401816972";
		  driver.get(baseUrl+"/account/trips/"+TripID);
			textPresent_Log(driver,"Trip ID",10);
			textPresent_Log(driver,"Contact Details",5);
			textPresent_Log(driver,"Payment Details",5);
			if(elementPresent(driver,getObjectPlatform("B2C_TripDetails_EmailItinerary"),10)){
				safeClick(driver,getObjectPlatform("B2C_TripDetails_EmailItinerary"));
				safeType(driver,getObjectPlatform("B2C_EmailItinerary_Email"),dataFile.value("AccountsUserName_Trips"));
				safeClick(driver,getObjectPlatform("B2C_EmailItinerary_Send"));
				Thread.sleep(2000);
				elementPresent_log(driver,getObjectPlatform("B2C_EmailItinerary_SuccessMsg"),"Itinerary Sent Successfully",2);
				textPresent_Log(driver,"Itinerary sent successfully.",5);
				Thread.sleep(1000);
			}
			if(elementPresent(driver,getObjectPlatform("B2C_TripDetails_SMSItinerary"),10)){
				safeClick(driver,getObjectPlatform("B2C_TripDetails_SMSItinerary"));
				safeType(driver,getObjectPlatform("B2C_SMSItinerary_Number"),"1234567890");
				safeClick(driver,getObjectPlatform("B2C_SMSItinerary_Send"));
				elementPresent_log(driver,getObjectPlatform("B2C_EmailItinerary_SuccessMsg"),"Trip details sent successfully",10);
				textPresent_Log(driver,"Trip details sent successfully.",5);
				Thread.sleep(1000);
			}
			safeClick(driver,getObjectPlatform("B2C_TripList_Page"));
			//elementPresent_log(driver,getObject("Acc_Trips_Upcoming_Tab"),"Upcoming trips tab",20);
			  //elementPresent_log(driver,getObject("Acc_Trips_AllTrips_Tab"),"All trips tab",5);
			  textPresent_Log(driver,"Trip ID",5);
			  elementPresent_log(driver,getObject("Acc_ExpressWay_Tab"),"expressway tab",5);
			  //safeClick(driver,getObject("Acc_Trips_AllTrips_Tab"));
			  textPresent_Log(driver,"Trip ID",10);
	  }else{
	    TripID = "Q1901240011"; 
		driver.get(baseUrl+"/account/trips/"+TripID);
		textPresent_Log(driver,"Trip ID",10);
		textPresent_Log(driver,"Contact Details",10);
		textPresent_Log(driver,"Payment Details",5);
		if(elementPresent(driver,getObjectPlatform("B2C_TripDetails_PrintVocher"),10)){
			safeClick(driver,getObjectPlatform("B2C_TripDetails_PrintVocher"));
			Thread.sleep(3000);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    Thread.sleep(2000);
			driver.switchTo().window(tabs2.get(1));
		    textPresent_Log(driver,"Voucher Number",10);
		    textPresent_Log(driver,"Cleartrip Trip ID",5);
		    driver.close();
		    driver.switchTo().window(tabs2.get(0));
		}if(elementPresent(driver,getObjectPlatform("B2C_TripDetails_PrintInvoice"),10)){
			safeClick(driver,getObjectPlatform("B2C_TripDetails_PrintInvoice"));
			Thread.sleep(2000);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    Thread.sleep(1000);
			driver.switchTo().window(tabs2.get(1));
			Thread.sleep(3000);
		    textPresent_Log(driver,"Invoice No",20);
		    textPresent_Log(driver,"Cleartrip Charges",5);
		    driver.close();
		    driver.switchTo().window(tabs2.get(0));
		    Thread.sleep(1000);
		}if(elementPresent(driver,getObjectPlatform("B2C_TripDetails_PrintPaymentReceipt"),10)){
			safeClick(driver,getObjectPlatform("B2C_TripDetails_PrintPaymentReceipt"));
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    Thread.sleep(1000);
			driver.switchTo().window(tabs2.get(1));
			Thread.sleep(3000);
		    textPresent_Log(driver,"cleartrip Receipt No",10);
		    textPresent_Log(driver,"Total amount",5);
		    driver.close();
		    driver.switchTo().window(tabs2.get(0));
		    Thread.sleep(1000);
		}
		if(elementPresent(driver,getObjectPlatform("B2C_TripDetails_EmailItinerary"),10)){
			safeClick(driver,getObjectPlatform("B2C_TripDetails_EmailItinerary"));
			safeType(driver,getObjectPlatform("B2C_EmailItinerary_Email"),dataFile.value("AccountsUserName"));
			safeClick(driver,getObjectPlatform("B2C_EmailItinerary_Send"));
			Thread.sleep(3000);
			elementPresent_log(driver,getObjectPlatform("B2C_EmailItinerary_SuccessMsg"),"Itinerary Sent Successfully",5);
			textPresent_Log(driver,"Itinerary sent successfully.",5);
			Thread.sleep(1000);
		}if(elementPresent(driver,getObjectPlatform("B2C_TripDetails_EmailPASS"),10)){
			safeClick(driver,getObjectPlatform("B2C_TripDetails_EmailPASS"));
			safeType(driver,getObjectPlatform("B2C_EmailPass_Email"),dataFile.value("AccountsUserName"));
			safeClick(driver,getObjectPlatform("B2C_EmailPass_Send"));
			elementPresent_log(driver,getObjectPlatform("B2C_EmailItinerary_SuccessMsg"),"Itinerary Sent Successfully",10);
			textPresent_Log(driver,"Itinerary sent successfully.",5);
			Thread.sleep(1000);
		}if(elementPresent(driver,getObjectPlatform("B2C_TripDetails_SMSItinerary"),10)){
			safeClick(driver,getObjectPlatform("B2C_TripDetails_SMSItinerary"));
			safeType(driver,getObjectPlatform("B2C_SMSItinerary_Number"),"1234567890");
			safeClick(driver,getObjectPlatform("B2C_SMSItinerary_Send"));
			elementPresent_log(driver,getObjectPlatform("B2C_EmailItinerary_SuccessMsg"),"Trip details sent successfully",10);
			textPresent_Log(driver,"Trip details sent successfully.",5);
			Thread.sleep(1000);
		}
		
		safeClick(driver,getObjectPlatform("B2C_TripList_Page"));
		elementPresent_log(driver,getObject("Acc_Trips_Upcoming_Tab"),"Upcoming trips tab",20);
		  elementPresent_log(driver,getObject("Acc_Trips_AllTrips_Tab"),"All trips tab",5);
		  textPresent_Log(driver,"Trip ID",5);
		  elementPresent_log(driver,getObject("Acc_ExpressWay_Tab"),"expressway tab",5);
		  safeClick(driver,getObject("Acc_Trips_AllTrips_Tab"));
		  textPresent_Log(driver,"Trip ID",10);
		  if(elementPresent(driver,getObjectPlatform("B2C_TripList_Loadmore"),10)){
			  safeClick(driver,getObjectPlatform("B2C_TripList_Loadmore"));
			  
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