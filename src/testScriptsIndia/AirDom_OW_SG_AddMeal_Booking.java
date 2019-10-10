package testScriptsIndia;


import static org.testng.AssertJUnit.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

import static org.testng.AssertJUnit.assertTrue;
import dataServices.HQDataProvider;
import domainServices.HQ;
import domainServices.AirCommonMethod;
/*
TestCase Description: Add a meal to GoAir OW booking with 1 pax 
*/



public class AirDom_OW_SG_AddMeal_Booking extends AirCommonMethod {
	
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "blr","maa","maa"};
		String[] destination = {"maa","bom","hyd"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0",
				"credit card", false, "Full Refund" } };
	}



	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
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

	@Test(dataProvider = "B2CAirOWLCC")
	public void AirDom_OW_SG_AddMeal_Booking_99(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

			
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

				
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
		
			airCom_SRP_Oneway(driver);
			
			/*WebElement we = pickFirstFlight(driver);
			if (we != null) {
				bookButtonDom(we);
			} else {
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.");
				attempt++;
				continue;
			}*/
		
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			assertionLinkedList = captureItineraryData(driver);
			
			Thread.sleep(8000);
			driver.navigate().refresh();
			Thread.sleep(8000);
			
			boolean addmealButtonFound =addMealButton(driver);
			if (!addmealButtonFound) {
				Reporter.log("Add Meal Button Not Found. Making another attempt");
				attempt++;
				continue;
			}
			//Add meals code below
			//assertTrue("Add Meals Button Not Displayed", isElementPresent(driver,getObject("AirCom_BookStep_AddMealBtn_Bundle")));
			
			if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"), 1)){
				List<WebElement> we=driver.findElements(By.xpath("//button[@class='action selectAddonButton']"));
				Test:for(int i=0;i<we.size();i++){
				//System.out.println(we.get(i).getText());
				if(we.get(i).getText().endsWith("Meals")){
					we.get(i).click();
					break Test;
				}
				}
				//safeClick(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"));
				Thread.sleep(10000);
				driver.switchTo().frame("modal_window");
				}else if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn"), 1)){
					safeClick(driver, getObject("AirCom_BookStep_AddMealBtn"));
					Thread.sleep(10000);
					driver.switchTo().frame("modal_window");
				}
			
			assertTrue("Meals window not opened", driver.findElement(By.cssSelector("h2")).getText().contains("Meals for"));
			
			//select baggage		
			Actions action = new Actions(driver);
			
			WebElement we1 = driver.findElementByCssSelector("a.row.selectAddonListItem");
			action.moveToElement(we1).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click().build().perform();
			 
			

			Thread.sleep(2000);
			assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
			driver.findElementByXPath("//input[@value='Done']").click();
			Thread.sleep(1000);
			Reporter.log("Meals Added");
			//System.out.println("Meals Added");
			
			driver.switchTo().defaultContent();		
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
			bookingPassed = checkBookingStatus(driver);
			
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
		if ((common.value("makePayment").equals("true"))) 
		{
			assertTrue("Meal is not added", getText(driver, getObject("AirCom_ConfirmationPage_AddMealTxt")).contains("Meal"));
			Reporter.log("Meal Added",true);
		}
		//System.out.println("Meal Added");
		
	}
	
	
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}



}
