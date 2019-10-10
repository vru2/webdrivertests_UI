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

public class MEAir_SubDomain_SA_Generic_UI_Check extends AirCommonMethod {
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	int attempt = 0;
	String domain="sa";
	
	@Test(dataProvider = "AirOneWayDomestic")
	public void MEAir_SASite_Generic_Check_465(String[] fromSet,
			String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType,
			String resultClickRow, String adults, String children,
			String infants,// TODO:resultClickRow not used, user name pass to be
							// fixed
			String paymentMethod, boolean insuranceRequired, String refundMethod)
			throws Exception {

		//driver.get(baseUrl);
		if ((common.value("host").equals("www")))
		{
			driver.get("https://www.cleartrip.sa");
		}
		else
		{
			driver.get("https://qa2.cleartrip.sa");
		}
		if(elementPresent_Time(driver, getObject("SAAirCom_Homepage_Language_Popup_English_Button"), 10))
			safeClick(driver, getObject("SAAirCom_Homepage_Language_Popup_English_Button"));
	
		Thread.sleep(10000);
		if(driver.getCurrentUrl().equals("https://www.cleartrip.sa/ar/"))
		{
			safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu"));
			safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu_English_Option"));
		}
		Thread.sleep(10000);
		
		textPresent(driver, "Search flights", 600);
	    assertEquals("Currency  SAR", driver.findElement(By.linkText("Currency  SAR")).getText());
		Reporter.log("Currency  SAR - Verifed", true);
		//System.out.println("Currency  SAR - Verifed");
	   
		assertTrue(isElementPresent(driver, By.cssSelector("i.flagIcon.sa")));
	    Reporter.log("SA Domain Flag Icon - Verifed", true);
		//System.out.println("SA Domain Flag Icon - Verifed");
	    
	    assertEquals("Search flights", driver.findElement(By.cssSelector("h1")).getText());
	    
	    airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightFilterType,attempt);// TODO:add more parameters
	    elementVisible(driver, getObject("AirCom_SRP_Oneway_BookButton"), 100);
	    
	    //OnSRP
	    boolean curCheck = false; 
	    System.out.println("sar currency code="+ driver.findElement(By.cssSelector("th.price > span.SAR.currencyCode")).getText());
	    if(driver.findElement(By.cssSelector("th.price > span.SAR.currencyCode")).getText().equals("SAR") || driver.findElement(By.cssSelector("th.price > span.SAR.currencyCode")).getText().equals("SAR"))
	    	curCheck = true;
	    assertTrue(curCheck);
	    Reporter.log("SAR Currency on SRP - Verifed",true);
		//System.out.println("SAR Currency on SRP - Verifed");
	   
		assertTrue("SA Specific Customer Care Number Not Displayed",driver.getPageSource().contains("+966 112246311")); //
		    
	    Reporter.log("SA Specific CC Number - Verifed", true);
		//System.out.println("SA Specific CC Number - Verifed");
	    
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
		baseUrl = dataFile.value("urlsa");
		baseUrl ="http://qa2.cleartrip.sa";
		System.out.println("base url="+dataFile.value("urlsa"));
		
				
	}

	@AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	afterMethod(driver, _result);
	 }

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
	driver.quit();
	
	}


}
