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
	
	public class AirDom_OW_Booking_ConfirmAddOns extends AirCommonMethod { 
		
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
		public void airDom_OW_Booking_ConfirmAddOns_179(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
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
				Reporter.log("Search URL is : " + driver.getCurrentUrl(),true);
				flightCountFailure = checkFlightsCount1(driver);
				//System.out.println("flightCountFailure=="+flightCountFailure);
				if (!flightCountFailure) {
					//System.out.println("enetrs in no flight count failure");
					Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
				}
				
				
				/*
				 * Modified by: prashanth.k@cleartrip.com
				 * Need the modification to handle no baggage booking cases.
				 */			
				boolean solFound = false;
				int noOfSolutions = driver.findElements(By.xpath("//li[contains(@class,'listItem')]")).size();
				for(int i = 1; i <= noOfSolutions; i++ )
				{
					if(!elementPresent(driver, By.xpath("//li[contains(@class,'listItem')]["+i+"]/table/tbody/tr[1]/th[5]/div/span[contains(@original-title,'No check-in baggage included')]"),1))
					{
						driver.findElement(By.xpath("//li[contains(@class,'listItem')]["+i+"]/table/tbody/tr[2]/td[3]/button")).click();
						solFound = true;
						break;
					}
					Reporter.log("Sol "+i+" does not allow chekin baggage.", true);
				}
				
				if(!solFound)
				{
					Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
					attempt++;
					continue;
				}
								
				
/*				List<WebElement> we4=driver.findElements(By.xpath("//li[contains(@class,'listItem')]/table/tbody/tr[1]/th[5]/div[not(span)]"));
				for(int i=0;i<we4.size();i++){
				we4.get(i).findElement(By.xpath("./../../../tr[2]/td[3]/button")).click();
				break;
				}*/
	//	airCom_SRP_Oneway(driver);
				
				//Thread.sleep(10000);
				
		Reporter.log("Adding BAGGAGE is in progress",true);
		if(elementPresent(driver,getObject("AirCom_BookStep_AddBaggageBtn"),90)){
				//elementVisible(driver, getObject("AirCom_BookStep_AddBaggageBtn"), 60);
				//waitElement(driver, getObject("AirCom_BookStep_AddBaggageBtn1"),60);
				safeClick(driver, getObject("AirCom_BookStep_AddBaggageBtn"));
				Thread.sleep(5000);
				driver.switchTo().frame("modal_window");
				
				assertTrue("baggage window not opened", driver.findElement(By.cssSelector("h2")).getText().contains("Baggage for"));
				
				//select baggage		
				Actions action = new Actions(driver);
				
				WebElement we = driver.findElementByCssSelector("a.row.selectAddonListItem");
				action.moveToElement(we).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click().build().perform();
				 
				

				Thread.sleep(1000);
				assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
				driver.findElementByXPath("//input[@value='Done']").click();
				Thread.sleep(1000);
				driver.switchTo().defaultContent();	
				Reporter.log("Baggage Added");
			}
		Reporter.log("Adding MEAL is in progress",true);
		
		if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"), 1)){
			safeClick(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"));
			if(elementPresent_Time(driver,By.xpath("//button[@class='action selectAddonButton']"),1)){
			safeClick(driver,By.xpath("//button[@class='action selectAddonButton']"));
			}
			Thread.sleep(10000);
			driver.switchTo().frame("modal_window");
			}else if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn"), 1)){
				safeClick(driver, getObject("AirCom_BookStep_AddMealBtn"));
				Thread.sleep(10000);
				driver.switchTo().frame("modal_window");
			}
			
			assertTrue("Meals window not opened", driver.findElement(By.cssSelector("h2")).getText().contains("Meals for"));
			
			//select meal		
			Actions action1 = new Actions(driver);
			
			WebElement we1 = driver.findElementByCssSelector("a.row.selectAddonListItem");
			action1.moveToElement(we1).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click().build().perform();
			 
			

			Thread.sleep(2000);
			assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
			driver.findElementByXPath("//input[@value='Done']").click();
			Thread.sleep(1000);
			Reporter.log("Meals Added",true);
			//System.out.println("Meals Added");
			
		driver.switchTo().defaultContent();

					
		Reporter.log("Adding SEAT SELECTION is in progress",true);
				SeatSelect_OW(driver);
				
		Reporter.log("Adding COUPON is in progress",true);
				Apply_CouponCode(driver, "domow");
		
		Reporter.log("Adding INSURANCE is in progress",true);
		        insuranceBlock(driver, insuranceRequired);
				
				
				travellerDetails(driver, adults, children, infants, false, false, false);
				
				airconditionWatcher(driver);
				PaymentRetry(driver, "NB");
			
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
				assertTrue("Baggage Not Added After NB Retry", textPresent(driver, "Baggage", 1));
				assertTrue("Baggage Not Added After NB Retry", textPresent(driver, "Meal", 1));
			}	
		}
		
		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = {"maa","ccu","blr"};
			String[] destination = {"bom","blr","pnq"};
			return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "", "1", "0", "0",
					"credit card", true} };
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

