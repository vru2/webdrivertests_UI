// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Oct, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCorpAE;


import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class AirCorp_Dom_OW_ConvenienceFee_CC extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	
	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "DEL", "MAA", "1", "0", "0","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void airCorpDomConFeeCC_480(String FromCity, String ToCity, String Adults, String Childrens, String Infants, String Payment_Type) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpAE_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", "" , 50, 51);
		driver.get(SRP_URL);
		
		corpAir_SRP(driver, "DOMOW", "");
		
		/*driver.get(Corp_AE_Url());
		corp_SignIn(driver);
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, "", 0);
		corpAir_SRP(driver, "DOMOW", "");*/
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 30)){
			Reporter.log("Itinerary Page has not loaded");
		}
		elementVisible(driver, By.id("rtTotalAmount"), 20, "Price is not displayed in Itinerary");
		int totalPrice_Int  = priceCapture(driver, By.id("rtTotalAmount"), 20);
		Reporter.log("Price in Itinerary page : "+totalPrice_Int);
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		for(int i=0;i<=10;i++) {
			if(elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), 1)) {
				break;
			} else if(elementVisible(driver, By.xpath("(//a[contains(text(),'Modify search')])[2]"), 1)) {
				Reporter.log("Page has redirected back to SRP after clicking on continue in Traveller page");
				Assert.assertTrue(false);
			}
		}
		safeClick(driver, getObject("AirCorp_PaymentPage_CreditCard_Tab"));
		elementVisible(driver, By.id("processingFeeAmount"), 20, "Price is not displayed in PaymentPage");
		int price_Pay_Int = priceCapture(driver, By.id("processingFeeAmount"), 20);
		int totalPrice_Pay_Int = priceCapture(driver, By.id("formTotal"), 20);
		Reporter.log("PG Fee in Payment Step : "+price_Pay_Int);
		Reporter.log("PG Fee + Price in Payment Step : "+totalPrice_Pay_Int);

		System.out.println("PG Fee in Payment Step : "+price_Pay_Int);
		System.out.println("PG Fee +  Price in Payment Step : "+totalPrice_Pay_Int);
		if(!(totalPrice_Int+price_Pay_Int == totalPrice_Pay_Int)) {
			Reporter.log("Price + PG fee is not matching in Payment Step");
			Assert.assertTrue(false);
		} else Reporter.log("Price + PG fee is matching in Payment Step");
		AirCorp_Paymentpage(driver, Payment_Type, "", "Corp Dom OW PG fee : ");
			for(int j=1; j<=4;j++) {
				String textXpath = "//div[2]/div/dl/dt["+j+"]";
				String text = getText(driver, By.xpath(textXpath));
					if(text.contains("Total")) {
						String totalPriceXpath = "//div[2]/div/dl/dd["+j+"]";
						int totalPrice_Conf_Int = priceCapture(driver, By.xpath(totalPriceXpath), 20);
						Reporter.log("Total Price in Confirmation Page : "+totalPrice_Conf_Int);						
							if(!(totalPrice_Conf_Int==totalPrice_Pay_Int)) {
								Reporter.log("Price in confirmation page doesnt match with price in payment page : Payment Price : "+totalPrice_Pay_Int+" : Confirmation page price :  "+totalPrice_Conf_Int);
								Assert.assertTrue(false);
							}
					}
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
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}