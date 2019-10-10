// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCorpIndia;

import java.util.ArrayList;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class AirCorp_DataValidation_OW_Dom_GDS extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	
	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "DEL", "19", "20", "1", "0", "0", "","gds","DA" } };
	}

	@Test(dataProvider = "AirCorp")
	public void airCorpDom_dataValidationGDS_230(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String Payment_Type) throws Exception {
		ArrayList<String> SRP_Data = new ArrayList<String>();
		ArrayList<String> TravellerPage_Data = new ArrayList<String>();
		ArrayList<String> PaymentPage_Data = new ArrayList<String>();
		ArrayList<String> ConfirmationPage_Data = new ArrayList<String>();
		ArrayList<String> HQ_Data = new ArrayList<String>();
		
		driver.manage().deleteAllCookies();
		driver.get(corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 50, 51));
		SRP_Data = corpCom_DataVal_Dom_SRP(driver, flight_type, SRP_Data);	 	
		corpAir_ItineraryPage(driver);
		TravellerPage_Data = corpCom_DataVal_TravellerPage(driver, Adults, Childrens, Infants, TravellerPage_Data);
		PaymentPage_Data = corpCom_DataVal_PaymentPage(driver, SRP_Data, TravellerPage_Data, PaymentPage_Data);
		if(MakePaymentOnlyInQA2){
		String TripID  = AirCorp_Paymentpage(driver, Payment_Type, "", "Corp Dom OW Data Validation GDS : ");
		ConfirmationPage_Data = corpCom_DataVal_ConfirmationPage(driver, SRP_Data, PaymentPage_Data, ConfirmationPage_Data);
		HQ_Data = corpCom_DataVal_HQ(driver, SRP_Data, PaymentPage_Data, ConfirmationPage_Data, HQ_Data, TripID);
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}