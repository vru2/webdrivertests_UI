package testScriptsAmexWhiteLabel;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirDom_RT_AmexWhiteLabel_CouponBooking extends AirCommonMethod
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

	@DataProvider(name = "AirRTDomestic")
	public static Object[][] B2CAirOWGDSDomFullRefund() 
	{
		String[] origin = {"DEL", "MAA", "CCU"};
		String[] destination = {"CCU", "BLR", "HYD"};
		return new Object[][] { { origin,destination,"","1","1","0", "CC", false, "GDS"} };
	}

	
	@Test(dataProvider = "AirRTDomestic")
	public void airDom_RT_AmexWhiteLabel_CouponBooking_36470(String[] origin, String[] destin,String flightPreference,String adults, String children,	String infants,String Payment_Options, boolean insuranceRequired,String flight_type) throws Exception
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
			
			amexRoundTripSearch(driver, origin[attempt], destin[attempt], "10", adults, children, infants, flightPreference);// TODO:add more parameters
			Reporter.log("Search URL: " + driver.getCurrentUrl(), true);
		
			flightCountFailure = ExpediaFlightsCount(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
    		}
		
			ExpediaFlightsFiletrByLCCOrGDS(driver, flight_type, 0);
		
			safeClick(driver,getObject("Amex_SRP_Dom_RT_BookButton"));
		
			ExpAir_ItineraryPage(driver);
	
			AmexAir_TravellersEmail(driver);
			Thread.sleep(10000);
		
			//elementPresent_Time(driver, By.xpath("//div[@id='itinerary']/dl/dd"), 120);
			expediaTravellerDetailsDom(driver,adults, children, infants, GSTbooking);
		
			safeClick(driver,getObject("Expediaair_step4_creditCard"));
			// elementVisible(driver, getObject("ExpediaAir_step4_ccmaster"), 300);

			applyCoupon(driver,"domrt");
        
			AmxPayment = ExpAir_Paymentpage(driver, "AMEX");
        	if (!AmxPayment) 
        	{
        		Reporter.log("Amex Payment Page Not Displayed, attempting next search", true);
        		//System.out.println("Expedia Payment Page Not Displayed, attempting next search");
        		attempt++;
        		continue;
    		}
		
        	bookingPassed = ExpAir_Confirmation(driver);
        	Thread.sleep(20000);
        	if (bookingPassed) 
        	{
        		Reporter.log("Test case " + this.getClass() + " passed successfully", true);
        		//System.out.println("Test case " + this.getClass() + " passed successfully");
        	}
		} 
		while (!bookingPassed && attempt < 3);
		
		assertTrue("Booking failed after 3 attempts", ((attempt < 3) && (bookingPassed)));
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
