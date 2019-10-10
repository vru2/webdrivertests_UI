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

public class MEAir_SubDomain_KW_Generic_UI_Check  extends AirCommonMethod {
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	int attempt = 0;
	String domain="kw";

	
	@Test(dataProvider = "AirOneWayDomestic")
	public void MEAir_KWSite_Generic_Check_462(String[] fromSet,
			String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType,
			String resultClickRow, String adults, String children,
			String infants,// TODO:resultClickRow not used, user name pass to be
							// fixed
			String paymentMethod, boolean insuranceRequired, String refundMethod)
			throws Exception {
		
		driver.get(baseUrl);
		driver.navigate().refresh();
		driver.findElement(By.id("english_site_pref")).click();
		//Thread.sleep(1000);
		textPresent(driver, "Search flights", 60000);
	    assertEquals("Currency  KWD", driver.findElement(By.linkText("Currency  KWD")).getText());
		
		Reporter.log("Currency  KWD - Verifed");
		//System.out.println("Currency  KWD - Verifed");
	   
		assertTrue(isElementPresent(driver, By.cssSelector("i.flagIcon.kw")));
	    Reporter.log("KW Domain Flag Icon - Verifed");
		//System.out.println("KW Domain Flag Icon - Verifed");
	    
	    assertEquals("Search flights", driver.findElement(By.cssSelector("h1")).getText());
	    
	    airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightFilterType,attempt);// TODO:add more parameters
	    elementVisible(driver, getObject("AirCom_SRP_Oneway_BookButton"), 30000);
	    //OnSRP
	    assertEquals("KWD", driver.findElement(By.cssSelector("th.price > span.KWD.currencyCode")).getText());
	    Reporter.log("KWD Currency on SRP - Verifed");
		//System.out.println("KWD Currency on SRP - Verifed");
	   
		assertTrue("KWD Specific Customer Care Number Not Displayed",driver.getPageSource().contains("+965 22069185")); //
		    
	    Reporter.log("KW Specific CC Number - Verifed");
		//System.out.println("KW Specific CC Number - Verifed");
	    

	}

	
	@DataProvider(name = "AirOneWayDomestic")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "del", "bom", "kochin" };
		String[] destination = { "goa", "maa", "mangalore" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "Direct", "1", "0", "0", "credit card",
				false, "Full Refund" } };
	}
	
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = dataFile.value("url_kw_qa2");
		
				
	}

	@AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	afterMethod(driver, _result);
	 }

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
	driver.quit();
	}


}
