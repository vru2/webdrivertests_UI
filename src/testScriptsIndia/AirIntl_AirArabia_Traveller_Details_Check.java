package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirIntl_AirArabia_Traveller_Details_Check extends AirCommonMethod{
	 
	
	
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
		public void airIntl_AirArabia_Traveller_Details_Check_198(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
				String flightPreference, String flightFilterType, String adults, String children, String infants,
				String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {
			
			

			boolean complete = false;	
			boolean bookingPassed = false;
			boolean warningFound = false;
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			int attempt = 0;
			boolean DOB=false;
			boolean PNumber=false;
			boolean PExpiry=false;
			boolean PissuingCountry=false;
			boolean visatype=false;
			ArrayList<String> arrlist = new ArrayList<String>();
HashMap<String,String> hp=new HashMap<String,String>();
			
					
			do{
				driver.get(baseUrl);
				if (!checkIfSignedIn(driver)) {
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
				
				airCom_HomepageSearch_Oneway2(driver, fromSet[attempt], toSet[attempt], "2", adults, children, infants,
						flightPreference,attempt);
				Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
				
				//flightCountFailure = checkFlightsCount1(driver);
				flightCountFailure =waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					//System.out.println("No results found for this search. Making another attempt with different sectors.");
					attempt++;
					continue;
					
				}
				
				warningFound = filterFlightsByPreferedAirline1(driver,"Air Arabia", 0);
				if (warningFound) {
					attempt++;
					continue;
				}
				
				airCom_SRP_Oneway(driver);
				
				
			
				boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
				if (failAfterBookButton) {
					Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
					attempt++;
					continue;
				}
				
				Apply_CouponCode(driver, "intlow");
				
				insuranceBlock(driver, insuranceRequired);
				
				/*if(elementPresent_Time(driver, By.xpath("//input[contains(@class, 'primary nearByAirportWarningBtn')]"), 10))
				{
					safeClick(driver, By.xpath("//input[contains(@class, 'primary nearByAirportWarningBtn')]"));
				}*/
				
				if(waitForElement(driver,9,By.xpath("//dl[@class='horizontal travellers child']/dt"))){
					
				}
				List<WebElement> we=driver.findElements(By.xpath("//dl[@class='horizontal travellers child']/dt"));
				for(int i=0;i<we.size();i++){
					arrlist.add(we.get(i).findElement(By.xpath("./span")).getText());
					//hp.put("field"+i,we.get(i).findElement(By.xpath("./span")).getText());
					Reporter.log("-----------"+we.get(i).findElement(By.xpath("./span")).getText(), true);
				}
				complete = true;
				/*if(arrlist.contains("Date of birth")){
					DOB=true;
				}
				if(arrlist.contains("Passport number")){
					PNumber=true;
					
				}
				if(arrlist.contains("Visa type")){
					
				visatype=true;
				}
				if(arrlist.contains("Passport expiry")){
					
					PExpiry=true;
					}
				if(arrlist.contains("Passport Issuing Country")){
					
					PissuingCountry=true;
					}*/
			}
			while(attempt<3 && !complete);
			
				Assert.assertEquals(arrlist.contains("Date of birth"),true,"Date of Birth Not Found");
				Assert.assertEquals(arrlist.contains("Passport number"),true,"Passport Number Not Found");
				//Assert.assertEquals(arrlist.contains("Visa type"),true,"Visa Type Not Found");
				Reporter.log("***IMP NOTE***: VISA TYPE DROPDOWN IS NOT SHOWN!!!!", true);
				
				
		
		}
		
		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = { "BOM","DEL","BLR"};
			String[] destination = {"SHJ","SHJ","SHJ"};
			return new Object[][] { { origin, destination, "Flights", "OneWay", "gds", "Air Arabia", "Direct", "1", "0", "0",
					"credit card", false, "Full Refund" } };
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
