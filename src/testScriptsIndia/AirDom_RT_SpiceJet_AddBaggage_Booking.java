package testScriptsIndia;



	import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
	Test Case Description:	From RT SRP select onward flight with baggage and roundtrip flight without baggage and in the book step add baggage for both onward and return 
	*/


	public class AirDom_RT_SpiceJet_AddBaggage_Booking extends AirCommonMethod {
		
		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
		String tripId = null;
		String domain = "com";
String[] Baggage=null;
int x=0;

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
			String[] origin = { "bom","blr","maa","bom"};
			String[] destination = {"del","maa","blr","blr"};
			return new Object[][] { { origin, destination, "Flights", "RoundTrip", "SpiceJet", "", "1", "0", "0",
					"credit card", "", false, "Auto Refund", false, "lcc" } };
		}
		
		
		
		@Test(dataProvider= "B2CAirRT")
		public void airDom_RT_SpiceJet_AddBaggage_Booking_190(String[] fromSet, String[] toSet, String app, String tripType,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, String flightSegments, boolean insuranceRequired, String refundMethod, boolean sector,
				String flight_type) throws Exception {

			Reporter.log("Test case " + this.getClass() + " started",true);
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
				airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "15", "19", adults, children, infants,
						flightPreference,attempt);
				Reporter.log("Search URL is : " + driver.getCurrentUrl(),true);
				//System.out.println("Search URL is : " + driver.getCurrentUrl());
				flightCountFailure = checkFlightsCount1(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
				
//				Modified by: prashanth.k@cleartrip.com
//				This test fails as there are now flights available with no check-in baggage allowed.
//				So we identify the first search result without the "no check-in baggage" icon for both onward and return flight and click book
				
//				Select Onward Flight
				int onwardFlightCount = driver.findElements(By.xpath("//*[@id='flightForm']/section[2]/div[4]/div[1]/nav/ul/li/div/label/table/tbody")).size();
				for(int i=1;i<=onwardFlightCount;i++){
					String xpath1 = "//*[@id='flightForm']/section[2]/div[4]/div[1]/nav/ul/li[" + i + "]/div/label/table/tbody/tr/th[6]/span[@class='flightSprite vIndicator noBaggage']";
					String xpath2 = "//*[@id='flightForm']/section[2]/div[4]/div[1]/nav/ul/li[" + i + "]/div/label/table/tbody/tr/th[1]/input";
					try{
						driver.findElement(By.xpath(xpath1));
					}
					catch(Exception e){
						safeClick(driver, By.xpath(xpath2));
						break;
					}
				}
				
//				Select Return Flight
				int returnFlightCount = driver.findElements(By.xpath("//*[@id='flightForm']/section[2]/div[4]/div[2]/nav/ul/li/div/label/table/tbody")).size();
				for(int i=1;i<=returnFlightCount;i++){
					String xpath1 = "//*[@id='flightForm']/section[2]/div[4]/div[2]/nav/ul/li[" + i + "]/div/label/table/tbody/tr/th[6]/span[@class='flightSprite vIndicator noBaggage']";
					String xpath2 = "//*[@id='flightForm']/section[2]/div[4]/div[2]/nav/ul/li[" + i + "]/div/label/table/tbody/tr/th[1]/input";
					try{
						driver.findElement(By.xpath(xpath1));
					}
					catch(Exception e){
						safeClick(driver, By.xpath(xpath2));
						break;
					}
				}
				
//				Continue with the booking process				
				
//				Thread.sleep(5000);
				clickRoundTripBookButton(driver);
				
				boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
				if (failAfterBookButton) {
					Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
					attempt++;
					continue;
				}
				cheaperRateBlock(driver);
				assertionLinkedList = captureItineraryData(driver);
				
				try{
				//Onward - AddBaggage code 
				assertTrue("Add Baggage Button Not Displayed", isElementPresent(driver,getObject("AirCom_BookStep_AddBaggageBtn")));
				
				safeClick(driver, getObject("AirCom_BookStep_AddBaggageBtn"));
				Thread.sleep(5000);
				driver.switchTo().frame("modal_window");
				Actions action = new Actions(driver);
				WebElement we = driver.findElementByCssSelector("a.row.selectAddonListItem");
				action.moveToElement(we).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click().build().perform();
				 
				
				Thread.sleep(2000);
				
				Reporter.log("Onward Baggage Added",true);
				//System.out.println("Baggage Added");
				
				safeClick(driver,getObject("AirDom_Return_AddBaggageBtn"));
				
				Thread.sleep(2000);
				assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
				driver.findElementByXPath("//input[@value='Done']").click();
				Thread.sleep(3000);
				Reporter.log("Baggage Added");
				//System.out.println("Baggage Added");
				Thread.sleep(2000);
				driver.switchTo().parentFrame();
				}
				catch(Exception e)
				{
					Reporter.log("Unable to add baggage, trying again");
					attempt++;
					continue;
				}
				
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

			}

/*		@AfterClass(alwaysRun = true)
		public void closeSelenium() throws Exception {
			// writeTripToFile(tripID);
			driver.close();
			driver.quit();
		}

		@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}*/


	}

