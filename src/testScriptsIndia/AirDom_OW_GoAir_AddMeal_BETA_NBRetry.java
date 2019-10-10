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

	public class AirDom_OW_GoAir_AddMeal_BETA_NBRetry extends AirCommonMethod {

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
		public void Air_OW_GoAir_AddMeal_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

				
			boolean bookingPassed = false;
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			boolean retryConfirm = false;
			int attempt = 0;

					
			do {
				driver.get(baseUrl);
				if (!checkIfSignedIn(driver)) {
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
				
				airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
						flightPreference,attempt);
				Reporter.log("Search URL is : " + driver.getCurrentUrl());
				flightCountFailure = checkFlightsCount1(driver);
				//System.out.println("flightCountFailure"+ flightCountFailure);
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
				
				boolean addmealButtonFound =addMealButton(driver);
				if (!addmealButtonFound) {
					Reporter.log("Add Meal Button Not Found. Making another attempt");
					attempt++;
					continue;
				}
				//Add meals code below
				//assertTrue("Add Meals Button Not Displayed", isElementPresent(driver,getObject("AirCom_BookStep_AddMealBtn_Bundle")));
				
				if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"), 1)){
					//safeClick(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"));
					safeClick(driver,By.xpath("//button[@class='action selectAddonButton']"));
					Thread.sleep(10000);
					driver.switchTo().frame("modal_window");
					}else if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn"), 1)){
						safeClick(driver, getObject("AirCom_BookStep_AddMealBtn"));
						Thread.sleep(10000);
						driver.switchTo().frame("modal_window");
					}
				
				assertTrue("Meals window not opened", driver.findElement(By.cssSelector("h2")).getText().contains("Meals for"));
				Reporter.log("Melas Added");
				
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
				
				retryConfirm = prodNBRetry(driver, "NB");

				//System.out.println("NB Payment Retry Successfull");
				Reporter.log("NB Payment Retry Successfull");
				
				String PaymentRetryUrl = driver.getCurrentUrl();
				Reporter.log("PaymentRetryUrl" + PaymentRetryUrl);
				//System.out.println("PaymentRetryUrl" + PaymentRetryUrl);
				

			} while (!retryConfirm && attempt < 4);
			//System.out.println(!retryConfirm);
			assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (retryConfirm)));
	
		}
		
		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = { "blr","bom","del"};
			String[] destination = {"goi","goi","goi"};
			return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "GoAir", "Direct", "1", "0", "0",
					"credit card", false, "Full Refund" } };
		}
		
		@AfterClass(alwaysRun = true)
		public void closeSelenium() throws Exception {
			//driver.close();
			//driver.quit();
		}

		@AfterMethod(alwaysRun = true)
		public void takeScreenshot(ITestResult _result) throws Exception {
			screenshot(_result, driver);
			//System.out.println("Test Case:" + _result.getMethod().getMethodName());
		}



	}

