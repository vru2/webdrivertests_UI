// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsAgency;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class AgencyAir_ConvenienceFee_NB extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")  
		public void AgencyComConveFeeNB_281(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			  driver.manage().deleteAllCookies();
			  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", "", 42, 43);
			  driver.get(SRP_URL);
			  Thread.sleep(1000);
			  driver.navigate().refresh();
			  agencyAir_SRP_Domestic_Oneway(driver);
			  agencyAir_ItineraryPage(driver);
			  elementVisible(driver, getObject("Air_Agency_TravellerPage_ContinueButton"), 20, "Traveller page not loaded : ");
			  int priceTraveller = priceCapture(driver, By.id("rtTotalAmount"), 5);
			  agencyAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
			  if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 30)){
					Reporter.log("PaymentPage is not displayed");
				}
				safeClick(driver, getObject("AgencyHotels_PaymentPage_NetBanking_Tab"));
				safeSelect(driver, getObject("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Bank of India");	
					  int pricePayment = priceCapture(driver, By.id("formTotal"), 10);
					  int priceConFee = priceCapture(driver, By.id("processingFeeAmount"), 10);
					  Reporter.log("Price in traveller page : "+priceTraveller);
					  Reporter.log("Price in payment page : "+pricePayment);
					  Reporter.log("Convience fee : "+priceConFee);
					  if(!(priceTraveller+priceConFee == pricePayment)) {
						  Reporter.log("Price - Traveller page + ConvienenceFee & Payment page doesnt match");
						  Assert.assertTrue(true);
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
  
		@AfterClass(alwaysRun = true)
		public void tearDown() throws Exception {
		browserClose(driver);
		}  
}