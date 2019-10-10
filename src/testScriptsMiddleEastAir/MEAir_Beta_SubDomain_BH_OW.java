package testScriptsMiddleEastAir;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import static org.testng.AssertJUnit.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

import dataServices.HQDataProvider;
import dataServices.IndiaDataProvider;
import domainServices.AirCommonMethod;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import dataServices.IndiaDataProvider;

public class MEAir_Beta_SubDomain_BH_OW extends AirCommonMethod  
{
	
	public RemoteWebDriver driver = null;
	String tripId = null;
	String baseUrl = null;
	int attempt = 0;
	String domain="bh";
	
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
		String origin[] = {"BAH", "DEL","DEL" };
		String destination[] = { "DEL","BAH","BOM" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "1", "0", "0", "credit card", false} };
	}

	@Test(dataProvider = "B2CAirOW")
	public void test_MEAir_Beta_SubDomain_BH_OW(String[] fromSet,
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
			driver.get(baseUrl);
			
			if (!checkIfAESubDomainSignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			assertEquals("Currency  BHD", driver.findElement(By.xpath("//*[@class='listMenuLink currencyLink']")).getText());
			Reporter.log("Currency  BHD - Verifed", true);
			   
			assertTrue(isElementPresent(driver, By.linkText("Baharain")));
			Reporter.log("BH Domain Flag Icon - Verifed", true);
			    
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightFilterType, attempt);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl(), true);
			
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				attempt++;
				continue;
			}
			driver.navigate().refresh();
			Thread.sleep(4000);
			driver.navigate().refresh();
			Thread.sleep(4000);
			assertEquals("Currency  BHD", driver.findElement(By.xpath("//*[@class='listMenuLink currencyLink']")).getText());
			Reporter.log("BHD Currency on SRP - Verifed", true);
			
			assertTrue("BH Specific Customer Care Number Not Displayed",driver.getPageSource().contains("+973 1 6196845")); //
			Reporter.log("BH Specific CC Number - Verifed", true);
			
			airCom_SRP_Oneway(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) 
			{
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt", true);
				attempt++;
				continue;
			}
			
			insuranceBlock(driver, false);
			
			//safeClick(driver, getObject("air_review_itinerary_continue"));
			
			travellerDetails(driver, adults, children, infants, true, false, false);
			
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
