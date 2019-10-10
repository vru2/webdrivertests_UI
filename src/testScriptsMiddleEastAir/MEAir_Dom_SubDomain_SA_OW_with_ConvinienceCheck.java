package testScriptsMiddleEastAir;

import static org.testng.AssertJUnit.assertEquals;
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

public class MEAir_Dom_SubDomain_SA_OW_with_ConvinienceCheck extends AirCommonMethod{

	
	public RemoteWebDriver driver = null;
	String tripId = null;
	String baseUrl = null;
	int attempt = 0;
	String domain="sa";
	
	@BeforeClass
	public void startSelenium() throws Exception 
	{
		this.driver = getDriver(driver);
		if (driver == null) 
		{
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	
	@DataProvider(name = "B2CAirOW")
	public static Object[][] B2CAirOWLCC() {
		String origin[] = {"BLR", "DEL","DEL" };
		String destination[] = { "DEL","CCU","BOM" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "1", "0", "0", "credit card", false} };
	}

	
	@Test(dataProvider = "B2CAirOW")
	public void test_MEAir_Beta_SubDomain_SA_OW(String[] fromSet,
			String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType,
			String adults, String children,
			String infants,
			String paymentMethod, boolean insuranceRequired)
			throws Exception 
	{
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		
		int attempt = 0;
		
		do 
		{
	
			//driver.get(baseUrl);
			if ((common.value("host").equals("www")))
			{
				driver.get("https://www.cleartrip.sa");
			}
			else
			{
				driver.get("https://qa2.cleartrip.sa");
			}
			
			if(elementPresent_Time(driver, getObject("SAAirCom_Homepage_Language_Popup_English_Button"), 10))
				safeClick(driver, getObject("SAAirCom_Homepage_Language_Popup_English_Button"));
		
			Thread.sleep(10000);
			
			if (!checkIfAESignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			
			if(driver.getCurrentUrl().equals("https://www.cleartrip.sa/ar/"))
			{
				safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu"));
				safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu_English_Option"));
			}
			Thread.sleep(10000);
			
			assertTrue(isElementPresent(driver, By.cssSelector("i.flagIcon.sa")));
		    Reporter.log("SA Domain Flag Icon - Verifed", true);
			
		    airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightFilterType,attempt);
		    
		    flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				attempt++;
				continue;
			}
			//assertEquals("SAR", driver.findElement(By.cssSelector("#BaggageBundlingTemplate > span.SAR.currencyCode")).getText());
			//System.out.println("------------"+driver.findElement(By.xpath("//*[@class='listMenuLink currencyLink']")).getText().split("  ")[0]);
			//System.out.println(driver.findElement(By.xpath("//*[@class='listMenuLink currencyLink']")).getText().split("  ")[1].trim());
			

			airCom_SRP_Oneway(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) 
			{
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt", true);
				attempt++;
				continue;
			}
			ItineraryData1(driver,insuranceRequired);
			safeClick(driver, getObject("air_review_itinerary_continue"));
			//insuranceBlock(driver, false);
			
			travellerDetails(driver, adults, children, infants, true, false, false);
			convinienceData(driver);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			
			if (reachedPaymentStep) 
			{
				if ((common.value("makePayment").equals("true"))) 
				{
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
					if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");//workaround
					else if (paymentDone == false) {
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt,true);
						continue;
					}
					if (error) {
						attempt++;
						continue;
					}
				} else 
				{
					Reporter.log("Make Payment is set to false. Not attemptign Payment", true);
					bookingPassed = true;
					break;
				}
			}
			else 
			{
				
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt, true);
				continue;
			}
			
			attempt++;
			bookingPassed = checkBookingStatus(driver);
	
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
	    
	}

	@AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception 
	{
	 	afterMethod(driver, _result);
	}

	@AfterClass
	public void tearDown() throws Exception 
	{
		driver.close();
		driver.quit();
		
	}



}
