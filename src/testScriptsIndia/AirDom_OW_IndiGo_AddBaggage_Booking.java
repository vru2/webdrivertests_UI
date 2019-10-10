package testScriptsIndia;



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
	TestCase Description: Add a baggage to a OW DOM LCC flight and do booking
	*/
	public class AirDom_OW_IndiGo_AddBaggage_Booking extends AirCommonMethod { 
		
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
		public void Air_Dom_OW_IndiGo_AddBaggage_Booking_191(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired) throws Exception {

				
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
				flightCountFailure = checkFlightsCount1(driver);
				
				if (!flightCountFailure) {
				
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
				
					attempt++;
					continue;
				}
				if(elementPresent(driver,By.xpath(""),1)){
					driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a")).click();
					//driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a")).click();
					}
				List<WebElement> we4=driver.findElements(By.xpath("//li[contains(@class,'listItem')]/table/tbody/tr[1]/th[5]/div[not(span[contains(@class,'noBaggage')])]"));
				for(int i=0;i<we4.size();i++){
				we4.get(i).findElement(By.xpath("./../../../tr[2]/td[3]/button")).click();
				break;
				}
		//airCom_SRP_Oneway(driver);
				
				Thread.sleep(5000);
				try
				{
				waitElement(driver, getObject("AirCom_BookStep_AddBaggageBtn1"),60);
				safeClick(driver, getObject("AirCom_BookStep_AddBaggageBtn"));
				Thread.sleep(2000);
				driver.switchTo().frame("modal_window");
				
				assertTrue("baggage window not opened", driver.findElement(By.cssSelector("h2")).getText().contains("Baggage for"));
				
				//select baggage		
				Actions action = new Actions(driver);
				
				WebElement we = driver.findElementByCssSelector("a.row.selectAddonListItem");
				action.moveToElement(we).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click().build().perform();
				 
				

				Thread.sleep(2000);
				assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
				driver.findElementByXPath("//input[@value='Done']").click();
				Thread.sleep(3000);
				Reporter.log("Baggage Added");
				}
				catch(Exception e)
				{
					Reporter.log("Unable to add baggage, trying again");
					attempt++;
					continue;
				}
				
		        Thread.sleep(2000);
					
				
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
			if ((common.value("makePayment").equals("true"))) {
				assertTrue("Added Baggage Not Displayed On Confirmation Page",getText(driver, By.cssSelector("li.colZero.col6 > strong")).contains("Kg"));
			}
			
		}
		
		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = {"del","blr","maa","blr"};
			String[] destination = {"bom","bom","blr","pnq"};
			return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "Indigo", "", "1", "0", "0",
					"credit card", false} };
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

