package testScriptsIndia;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertTrue;
import domainServices.AirCommonMethod;
/*
TestCase Description: To check mini rules for all Airlines in Oneway search page.
Added by: anil.patil@cleartrip.com
*/
public class AirDom_OW_SRP_MiniRules_Verification extends AirCommonMethod { 
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
    
	@BeforeClass
	public void startSelenium() throws Exception 
	{
		this.driver = getDriver(driver);
		if (driver == null) 
		{
			Reporter.log("Error in initial setup. Exiting without screenshot", true);
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWL() 
	{
		String[] origin = {"blr", "blr","bom"};
		String[] destination = {"del","maa","del"};
		return new Object[][] {
					{ origin, destination, "Flights", "", "", "", "Direct", "1", "0", "0", "credit card", false} 
				};
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void AIR_DOM_OW_SRP_MiniRules_Verification(String[] origin, String[] destin, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception 
	{
		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		ArrayList<String> arrlist = new ArrayList<String>();
		boolean flightCountFailure = true;
		int attempt = 0;
		Reporter.log(flightPreference + ":" + this.getClass() + " started");
		
		do 
		{
			//driver.get(baseUrl);
			driver.get(common.value("protocol")+"://"+common.value("host")+".cleartrip.com/flights/results?from="+origin[attempt]+"&to="+destin[attempt]+"&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=n");
			// hardcode of url to be resolved soon
			/*airCom_HomepageSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,0);*/
			Reporter.log("Search URL is : " + driver.getCurrentUrl());

			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			
			List<WebElement> flights1=null;
			if (waitElement(driver,By.xpath("//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"), 1)) 
			{
				flights1 = driver.findElements(By.xpath("//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"));
			}

			test:for(int i=0;i<flights1.size();i++)
			{
				for(char c :flights1.get(i).getText().toCharArray())
				{
					if(Character.isDigit(c))
					{
						break test;
					}
				}

				arrlist.add(flights1.get(i).getText());
			}
			
			//Starting for loop of minirule//
			for (int i=1; i<arrlist.size(); i++){
				Reporter.log("VERIFYING MINIRULES FOR ==> "+arrlist.get(i), true);
				filterFlightsByPreferedAirline1(driver, arrlist.get(i), 0);
				
				//verifying Flight itinerary link
				safeClick(driver, By.linkText("Flight itinerary"));
				if(!elementVisible(driver, By.xpath("//div/ul/li/div/ul/li"), 20)) {
					Reporter.log("Flight Details are not displayed",true);
					Assert.assertTrue(false);
				}
				String flightDetails = getText(driver, By.xpath("//div/ul/li/div/ul/li"));
				Reporter.log("Flight Details : "+flightDetails,true);
				if(!elementVisible(driver, By.cssSelector("li.start"), 2)) {
					Reporter.log("Departure Details are not displayed",true);
					Assert.assertTrue(false);
				}
				String departureDetails = getText(driver, By.cssSelector("li.start"));
				Reporter.log("Departure Details : "+departureDetails,true);
				if(!elementVisible(driver, By.cssSelector("li.end"), 2)) {
					Reporter.log("Arrival Details are not displayed",true);
					Assert.assertTrue(false);
				}
				String arrivalDetails = getText(driver, By.cssSelector("li.end"));
				Reporter.log("Arrival Details : "+arrivalDetails,true);
				
				//verifying Fare Rules link
				safeClick(driver, By.linkText("Fare rules"));
				if(!elementVisible(driver, By.xpath("//div[2]/div/h1"), 30)) {
					Reporter.log("Fare rules are not displayed",true);
					Assert.assertTrue(false);
				}
				Reporter.log("Farebreakup Details",true);
				List<WebElement> pricingName=driver.findElements(By.xpath("//div[2]/div/div/dl/dt"));				
				List<WebElement> pricingValue=driver.findElements(By.xpath("//div[2]/div/div/dl/dd"));
				
				for(int k=0;k<=pricingName.size()-1;k++)
				{
					
					String pricingNames = pricingName.get(k).getText();
					String pricingValues = pricingValue.get(k).getText();
					if(!pricingValues.isEmpty()) {
						Reporter.log(pricingNames+" : "+pricingValues,true);
					}
					if(pricingNames.contains("Total")) {
						break;
					} else if(k==pricingName.size()-1) {
						Reporter.log("Total text is not displayed in fare Rule",true);
						Assert.assertTrue(false);
					}
				}
				
				safeClick(driver, By.linkText("Taxes & fees"));
				for(int j=3;j<14;j++) {
				String taxesNameXpath = "//dt["+j+"]";
				String taxesValueXpath =  "//dd["+j+"]";
				if(elementVisible(driver, By.xpath(taxesNameXpath), 1)) {
						String taxName = getText(driver, By.xpath(taxesNameXpath));
						String taxValue = getText(driver, By.xpath(taxesValueXpath));
						Reporter.log(taxName+" : "+taxValue,true);
					}else break;
				}
				
				//verifying Baggage Info link
				safeClick(driver, By.linkText("Baggage Information"));
				if(!elementVisible(driver, By.xpath("//li/span"), 20)) {
					Reporter.log("Baggage info link not displayed",true);
					Assert.assertTrue(false);
				}
				String CheckIn_Baggage = getText(driver, By.xpath("//li/span"));
				String Cabin_Baggage = getText(driver, By.xpath("//li/span[2]"));
				if(!CheckIn_Baggage.contains("Check-in")) {
					Reporter.log(" CheckIN Baggage doesnt contain : ''Check-in",true);
					Assert.assertTrue(false);
				}
				if(!Cabin_Baggage.contains("Cabin")) {
					Reporter.log(" CheckIN Baggage doesnt contain : ''Cabin",true);
					Assert.assertTrue(false);
				}
				
				Reporter.log("MINIRULES VERIFIED FOR ==> "+arrlist.get(i), true);
				
				flightCountFailure=true;
			}	//closing for loop of minirule//
			
		} while (!flightCountFailure && attempt < 3 );
		assertTrue("flights not found after 3 attempts", ((attempt < 4) && (flightCountFailure)));
	}


	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception 
	{
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception 
	{
		afterMethod(driver, _result);
	}
}




