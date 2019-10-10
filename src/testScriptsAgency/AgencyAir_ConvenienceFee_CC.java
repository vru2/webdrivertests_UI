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

		public class AgencyAir_ConvenienceFee_CC extends Agency{
		public RemoteWebDriver driver;

		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom", groups="Regression")  
		public void AgencyComConvenFeeCC_279(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", "", 40, 41);
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
		  safeClick(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"));
		  int pricePayment = priceCapture(driver, By.id("formTotal"), 5);
		  int priceConFee = priceCapture(driver, By.id("processingFeeAmount"), 5);
		  Reporter.log("Price in traveller page : "+priceTraveller);
		  Reporter.log("Price in payment page : "+pricePayment);
		  Reporter.log("Convience fee : "+priceConFee);
		  if(!(priceTraveller+priceConFee == pricePayment)) {
			  Reporter.log("Price - Traveller page + ConvienenceFee & Payment page doesnt match");
			  Assert.assertTrue(false);
		  }
		  agencyAir_Paymentpage(driver, "CREDITCARD", "", Trip_Logger_Msg, "Air Agency Convenience fee cc TripID : ");
		textPresent(driver, "Total customer paid", 5);
		  for(int j=1; j<=4;j++) {
				String textXpath = "//div[2]/div/dl/dt["+j+"]";
				String text = getText(driver, By.xpath(textXpath));
					if(text.contains("Total")) {
						String totalPriceXpath = "//div[2]/div/dl/dd["+j+"]";
						int totalPrice_Conf_Int = priceCapture(driver, By.xpath(totalPriceXpath), 20);
						Reporter.log("Total Price in Confirmation Page : "+totalPrice_Conf_Int);			
							if(!(totalPrice_Conf_Int==pricePayment)) {
								Reporter.log("Price in confirmation page doesnt match with price in payment page");
								Assert.assertTrue(false);
							}
					}
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