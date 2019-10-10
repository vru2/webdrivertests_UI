package testScriptsAmexWhiteLabel;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import domainServices.AirCommonMethod;
/*
TestCase Description: Amex OW Full Gift Voucher Booking
Test rail id: C40359
*/
public class AirDom_OW_AmexWhiteLabel_Full_GV_Booking extends AirCommonMethod {
	
	public RemoteWebDriver driver = null;
	boolean flightCountFailure = true;
	int attempt = 0;
	boolean bookingPassed = false;
	boolean AmxPayment = false;
	boolean GSTbooking = false;
	
	@BeforeClass
	public void startSelenium() throws Exception 
	{
		this.driver = getDriver(driver);
		if (driver == null) 
		{
			Reporter.log("Error in initial setup. Exiting without screenshot", true);
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getAmexWlUrl();
	}

	
	@DataProvider(name = "AirOneWayDomestic")
	public static Object[][] B2CAirOWLCCDomFullRefund() 
	{
		String[] origin = {"DEL","MAA","ccu"};
		String[] destination = {"BOM","BLR","Hyd"};
		return new Object[][] { { origin, destination, "", "1", "0", "0", "CC", false, "lcc"} };
	}

	
	@Test(dataProvider = "AirOneWayDomestic")
	public void airDom_OW_AmexWL_Full_GV_Booking(String[] origin, String[] destin,String flightPreference,String adults, String children, String infants,String Payment_Options, boolean insuranceRequired,String flight_type) throws Exception 
	{
		
		do
		{
			Reporter.log("URL: " + baseUrl, true);
			driver.get(baseUrl);
		
			if (elementClickable(driver, getObject("Amex_HomePage_From_TextBox"), 20))
			{
				Reporter.log("Homepage loaded", true);
			}
			else
			{
				refreshPage(driver);
				if(elementClickable(driver, getObject("Amex_HomePage_From_TextBox"), 20)){
					Reporter.log("Homepage loaded after page refresh", true);
				}else{
					Reporter.log("attempt#"+attempt+"	Homepage not loaded, making another attempt", true);
					attempt++;
					continue;
				}
			}
			
			AmexOnewaySearch(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference);
			Reporter.log("Search URL: " + driver.getCurrentUrl(), true);
			
			if (elementPresent_Time(driver,getObject("Expedia_SRP_Dom_OW_BookButton") , 20))
			{
				Reporter.log("Search Results Displayed", true);			
			}
			else
			{
				driver.navigate().refresh();
				if(elementPresent_Time(driver,getObject("Expedia_SRP_Dom_OW_BookButton") , 20)){
				Reporter.log("Search Results Displayed after srp page Refreshed", true);
				}else
				{	
					Reporter.log("attempt#"+attempt+"	Search Results not loaded, making another attempt", true);
					attempt++;
					continue;
				}
			}
		
			flightCountFailure = ExpediaFlightsCount(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors", true);
				attempt++;
				continue;
			}
		
			ExpediaFlightsFiletrByLCCOrGDS(driver, flight_type, 0);
			
			safeClick(driver, getObject("Expedia_SRP_Dom_OW_BookButton"));
	
			ExpAir_ItineraryPage(driver);
			
			AmexAir_TravellersEmail(driver);
			
			Thread.sleep(10000);
			
			expediaTravellerDetailsDom(driver,adults, children, infants, GSTbooking);
		
			safeClick(driver,getObject("Expediaair_step4_creditCard"));
			
			AmxPayment = elementPresent_Time(driver, getObject("Air_AmexWL_PaymentPage_ApplyCoupon"), 30);
			if (!AmxPayment) 
			{
				Reporter.log("Expedia Payment Page Not Displayed, attempting next search");
				attempt++;
				continue;
			}
			
			applyGiftVoucherAmexWL(driver, dataFile.value("AmexCard_GV_Number"),dataFile.value("AmexCard_GV_Pin")); // added new method
			String payable = getText(driver, getObject("Air_AmexWL_PaymentPage_GV_BalancePayable")); //Rs. 0
			Reporter.log("Total payable after applying GV: "+payable, true);
			if (payable.trim().equalsIgnoreCase("Rs. 0"))
			{
				safeClick(driver, getObject("Air_AmexWL_PaymentPage_GV_MakePaymentButton"));
				Reporter.log("Make payment button clicked", true);
			}
			else{
				Assert.fail("Total payable amount after applying GV is not Zero. Check GV balance.");
				}
			
			bookingPassed = ExpAir_Confirmation(driver);
		
			if (bookingPassed) 
			{
				Reporter.log("Test case " + this.getClass() + " passed successfully", true);
			}
		}
		while (!bookingPassed && attempt < 3);
			
		assertTrue("Booking failed after 3 attempts", ((attempt < 3) && (bookingPassed)));
		
			
	}
	
	@AfterMethod(alwaysRun = false)
	public void afterMethod(ITestResult _result) throws Exception
	{
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception 
	{
		driver.close();
		driver.quit();
	}


}
