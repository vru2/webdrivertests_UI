package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import domainServices.AirCommonMethod;

/*
 * Automated By: prashanth.k@cleartrip.com
 * 
 * Domain: www.cleartrip.com, www.cleartrip.ae 
 * Steps:
 * Search for BOM - DXE - nearby airports PNQ, AUH are displayed 
 * Book for PNQ - AUH 
 * Click on continue button in book-step1
 * Expected Result:
 * if depart or arrival or both the airports are different then "near by airport" warning message & pop-up should be displayed in book-step.
 * Warning: 
 * "NOTE: You are leaving from <Airport name> and arriving at <Airport name> airport." & Airport Mismatch pop-up message should be displayed
 */

public class AirIntl_NearbyAirportWarning_Check extends AirCommonMethod
{
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
	
	@BeforeClass
	public void startSelenium() throws Exception 
	{
		Reporter.log("Opening Browser.", true);
		this.driver = getDriver(driver);
		if (driver == null) 
		{
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() 
	{
		String[] origin = { "bom","DXB"};
		String[] destination = {"dxb","BOM"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "", "", "1", "0", "0",
				"credit card", false, "Full Refund" } };
	}
	
	@Test(dataProvider = "B2CAirOWLCC")
	void airIntl_NearbyAirportWarning_Check_36468(String[] origin, String[] destination, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod)
	{
		int attempt = 0;
		boolean noFlightCountFailure = false;
		boolean altFlightFound = false;
		String expectedWarningMessage = "NOTE: You are leaving from Lohegaon and arriving at Abu Dhabi International Airport.";
		String expectedOriginTooltip = "You searched for Chatrapati Shivaji Airport";
		String expectedDestinationTooltip = "Your searched for Dubai International Airport";
		String expectedPopupTitle = "Airport Mismatch";
		String expectedPopupMessage = "You have picked a different airport from what you had searched. Do you want to continue?";
		String expectedButton1Text = "Pick a different flight";
		String expectedButton2Text = "Okay, continue";
		
		do 
		{
			Reporter.log("Opening URL: " + baseUrl, true);
			driver.get(baseUrl);
			
			try 
			{
//				Do we need to be signed in?
	  			if (!checkIfSignedIn(driver)) 
				{
					Reporter.log("Signing in.", true);
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
				
//	  			Searching
				Reporter.log("Attempting search.", true);
				airCom_HomepageSearch_Oneway(driver, origin[attempt], destination[attempt], "10", adults, children, infants, flightPreference,attempt);
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
			
				Reporter.log("Checking for errors in search.", true);
				noFlightCountFailure = checkFlightsCount1(driver);
				if (!noFlightCountFailure) 
				{
					Reporter.log("No results found for this search. Making another attempt.", true); 
					attempt++;
					continue;
				}
				else
				{
					Reporter.log("Results are found for this search.", true); 
				}
				
//				Booking alternate flight
				Reporter.log("Trying to book a flight with alternate airports.", true);
				altFlightFound = pickFlightByCitiCode(driver, "pnq", "auh");
				if(!altFlightFound)
				{
					Reporter.log("No matching flights found. Making another attempt.", true); 
					attempt++;
					continue;
				}
				else
				{
					Reporter.log("Matching flight found. Proceeding to book.", true); 
				}
				
//              Identifying and verifying the warning message
				Reporter.log("Verifing warning message.", true);
				new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='lineMessage icon info']")));
				String actualWarningMessage = driver.findElement(By.xpath("//div[@class='lineMessage icon info']")).getText();
				String actualOriginTooltip = driver.findElement(By.xpath("//div[@class='lineMessage icon info']/b[1]")).getAttribute("original-title");
				String actualDestinationTooltip = driver.findElement(By.xpath("//div[@class='lineMessage icon info']/b[2]")).getAttribute("original-title");
				
				SoftAssert s = new SoftAssert();
				s.assertTrue(expectedWarningMessage.equals(actualWarningMessage), "Failed: Mismatch in the Warning Note");
				s.assertTrue(expectedOriginTooltip.equals(actualOriginTooltip), "Failed: Mismatch in the Origin Tooltip");
				s.assertTrue(expectedDestinationTooltip.equals(actualDestinationTooltip), "Failed: Mismatch in the Destination Tooltip");
				
//				Unchecking insurance and clicking on continue booking button
				Reporter.log("Unchecking insurance and Clicking on continue booking button in bookstep 1.", true);
				insuranceBlock(driver, insuranceRequired);
				safeClick(driver, getObject("AirCom_BookStep1_ContinueBooking_Button"));
				
//				Verifying the warning Popup and Popup Message, dismissing the Popup, Continue error message, re-appearance of the Popup, acceptance of Popup.
				// Verify Popup appearance
				Reporter.log("Verifing Popup Appearance.", true);
				s.assertTrue(elementVisible(driver,By.xpath("//div[@id='nearByAirPortBookingWarnBox']"),20), "Failed: Pop - up warning has not appeared.");
				
				//Verify Popup Title
				Reporter.log("Verifing Popup Title.", true);
				String actualPopupTitle = driver.findElement(By.xpath("//div[@id='nearByAirPortBookingWarnBox']//span[@id='nbHeadMsg']")).getText();
				s.assertTrue(expectedPopupTitle.equals(actualPopupTitle), "Failed: Pop up message Title mismatch.");
				
				//Verify Popup Message
				Reporter.log("Verifing Popup Message.", true);
				String actualPopupMessage = driver.findElement(By.xpath("//div[@id='nearByAirPortBookingWarnBox']//p[@class='msgSS']")).getText();
				s.assertTrue(expectedPopupMessage.equals(actualPopupMessage), "Failed: Pop up message content mismatch.");
				
				//Verify Popup Message Buttons
				Reporter.log("Verifing Popup Buttons.", true);
				s.assertTrue(elementVisible(driver,By.xpath("//div/p/input[@class='default button nearByAirportWarningBtn']"),20), "Failed: Pop - up warning pick a different Flight button has not appeared.");
				String actualButton1Text = driver.findElement(By.xpath("//div/p/input[@class='default button nearByAirportWarningBtn']")).getAttribute("value");
				s.assertTrue(expectedButton1Text.equals(actualButton1Text), "Failed: Button Text mismatch");
				
				//Dismiss Popup and verify warning
				Reporter.log("Dismissing Popup.", true);
				safeClick(driver, By.xpath("//div[@id='nearByAirPortBookingWarnBox']//a"));
				
				//Click Continue Booking button again and accept popup warning this time
				safeUncheck(driver, getObject("AirCom_BookStep1_Insurance_CheckBox"));
				safeClick(driver, getObject("AirCom_BookStep1_ContinueBooking_Button"));
				Reporter.log("Click Continue Booking button again and accept popup warning this time.", true);
				s.assertTrue(elementVisible(driver,By.xpath("//div/p/input[@class='primary nearByAirportWarningBtn']"),20), "Failed: Pop - up warning has not appeared.");
				String actualButton2text = driver.findElement(By.xpath("//div/p/input[@class='primary nearByAirportWarningBtn']")).getAttribute("value");
				s.assertTrue(expectedButton2Text.equals(actualButton2text), "Failed: Button Text mismatch");
				safeClick(driver, By.xpath("//div/p/input[@class='primary nearByAirportWarningBtn']"));
				
				s.assertAll();
				Reporter.log("Test Complete.",true);
				break;
			} 
			
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		} while (attempt < 2);
		
		assertTrue("Failed after 2 attempts.", (attempt < 3));
	}
	

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception 
	{
		afterMethod(driver, _result);
	}
	
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception 
	{
		driver.close();
		driver.quit();
	}
	
}
