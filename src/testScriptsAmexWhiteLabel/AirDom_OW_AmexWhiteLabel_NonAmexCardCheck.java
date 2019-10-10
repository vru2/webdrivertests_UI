package testScriptsAmexWhiteLabel;

import static org.testng.AssertJUnit.assertTrue;

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

import domainServices.AirCommonMethod;

public class AirDom_OW_AmexWhiteLabel_NonAmexCardCheck extends AirCommonMethod
{
	
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
		//baseUrl = common.value("Air_WhiteLabel_Amex_url");
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
	public void atest_AirDom_OW_AmexWhiteLabel_NonAmexCardCheck_36473(String[] origin, String[] destin,String flightPreference,String adults, String children, String infants,String Payment_Options, boolean insuranceRequired,String flight_type) throws Exception 
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
				elementClickable(driver, getObject("Amex_HomePage_From_TextBox"), 20);
			}
			
			AmexOnewaySearch(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference);// TODO:add more parameters
			Reporter.log("Search URL: " + driver.getCurrentUrl(), true);
			
			if (elementPresent_Time(driver,getObject("Amex_SRP_Dom_OW_BookButton") , 20))
			{
				Reporter.log("Search Results Displayed", true);			
			}
			else
			{
				driver.navigate().refresh();
				elementPresent_Time(driver,getObject("Expedia_SRP_Dom_OW_BookButton") , 20);
				Reporter.log("Results Page Refreshed");
			}
		
			flightCountFailure = ExpediaFlightsCount(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors", true);
				attempt++;
				continue;
			}
		
			safeClick(driver, getObject("Expedia_SRP_Dom_OW_BookButton"));
	
			ExpAir_ItineraryPage(driver);
			
			AmexAir_TravellersEmail(driver);
			
			Thread.sleep(15000);
			
			//elementPresent_Time(driver, By.xpath("//div[@id='itinerary']/dl/dd"), 120);
			
			expediaTravellerDetailsDom(driver,adults, children, infants, GSTbooking);
		
			safeClick(driver,getObject("Expediaair_step4_creditCard"));
			// elementVisible(driver, getObject("ExpediaAir_step4_ccmaster"), 300);

			//applyCoupon(driver);
			
			AmxPayment = ExpAir_Paymentpage(driver, "CC");
			
			Reporter.log("Error Message: " + getText(driver, getObject("Amex_WrongCC_Message")), true);
			Assert.assertTrue(getText(driver, getObject("Amex_WrongCC_Message")).trim().equals("Sorry, we do not accept the card you have provided, please try again with a different card. Click here to see a list of accepted payment options"), "Expected Error Message not displayed!");
			
			safeClick(driver, getObject("Amex_WrongCC_Message_Payment_Options_Link"));
			
			Reporter.log("Accepted Cards Message: " + getText(driver, getObject("Amex_WrongCC_Message_Payment_Options_Link_Message")), true);
			Assert.assertTrue(getText(driver, getObject("Amex_WrongCC_Message_Payment_Options_Link_Message")).trim().equals("We accept the following cards. Amex"), "Expected accepted cards Message not displayed!");
			
			Reporter.log("Test case " + this.getClass() + " passed successfully", true);
			break;
		}
		while (attempt < 3);
	}
	
	@AfterMethod(alwaysRun = true)
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
