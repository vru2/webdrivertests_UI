package testScriptsIndia;


import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;
import domainServices.AirCommonMethod;
/*
Test Case Description:	Add Meals to DOM OW RT GoAir flight. 
*/


public class AirDom_RT_GoAir_AddMeal_Booking extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	
	@DataProvider(name = "B2CAirRT")
	public static Object[][] B2CAirRTGDSDomViaParPaxAutoRefund() {
		String[] origin = { "blr","bom","blr"};
		String[] destination = {"maa","del","goi"};
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "Goair", "Non Direct", "1", "0", "0",
				"credit card", "Via", false, "Auto Refund", false, "lcc" } };
	}
	

	
	@Test(dataProvider= "B2CAirRT")
	public void Air_Dom_RT_GoAir_AddMeal_Booking_96(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, String flightSegments, boolean insuranceRequired, String refundMethod, boolean sector,
			String flight_type) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			// safeClick(driver, getObject("AirSideAppButtonXPath"));
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants,
					flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			
			Thread.sleep(5000);
			clickRoundTripBookButton(driver);
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			assertionLinkedList = captureItineraryData(driver);
			
			boolean addmealButtonFound =addMealButton(driver);
			if (!addmealButtonFound) {
				Reporter.log("Add Meal Button Not Found. Making another attempt");
				attempt++;
				continue;
			}
			//Add meals code below
			//assertTrue("Add Meals Button Not Displayed", isElementPresent(driver,getObject("AirCom_BookStep_AddMealBtn")));
			
			/*if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"), 1)){
				safeClick(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"));
				Thread.sleep(10000);
				driver.switchTo().frame("modal_window");
			}else */if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn"), 1)){
					safeClick(driver, getObject("AirCom_BookStep_AddMealBtn"));
					Thread.sleep(10000);
					driver.switchTo().frame("modal_window");
				}
			
			assertTrue("Meals window not opened", driver.findElement(By.cssSelector("h2")).getText().contains("Meals for"));
			
			//Onward Meal
			Actions Onward = new Actions(driver);
			WebElement we1 = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"));
			Onward.moveToElement(we1).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"))).click().build().perform();

			Thread.sleep(1000);
			//Return Meal
			WebElement we2 = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[3]/ul/li[2]/a"));
			Onward.moveToElement(we2).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[3]/ul/li[2]/a"))).click().build().perform();
			 		 
			
			Thread.sleep(1000);
			assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
			driver.findElementByXPath("//input[@value='Done']").click();
			Thread.sleep(3000);
			Reporter.log("Meals Added");
			//System.out.println("Meals Added");
						
			driver.switchTo().parentFrame();
			insuranceBlock(driver, insuranceRequired);
			
			travellerDetails(driver, adults, children, infants, false, false, false);
			
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
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
				} else {
					Reporter.log("Make Payment is set to false. Not attemptign Payment", true);
					bookingPassed = true;
					break;
				}
			} else {
				
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt, true);
				continue;
			}
			attempt++;
			bookingPassed = checkTripID(driver);
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
		
		if ((common.value("makePayment").equals("true"))) 
		{
			String  OnwardMeal = driver.findElementByXPath("//nav[@id='travellerBooked']/ul[2]/li[2]").getText();
			String ReturnMeal = driver.findElementByXPath("//nav[@id='travellerBooked']/ul[3]/li[2]").getText();
			
			assertTrue("Meal Not Added Either to Onward or To Return Trip",OnwardMeal.contains("Meal")&&ReturnMeal.contains("Meal") );
		
			Reporter.log("Meal Added for Both Onward and Return Flights", true);
		}
		//System.out.println("Meal Added for Both Onward and Return Flights");
		
		}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		// writeTripToFile(tripID);
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}


	
}
