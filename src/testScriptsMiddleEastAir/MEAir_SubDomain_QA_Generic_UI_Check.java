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

public class MEAir_SubDomain_QA_Generic_UI_Check extends AirCommonMethod{

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	int attempt = 0;
	String domain="qa";
	
	
	
	@Test(dataProvider = "AirOneWayDomestic")
	public void MEAir_QASite_Generic_Check_464(String[] fromSet,
			String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType,
			String resultClickRow, String adults, String children,
			String infants,// TODO:resultClickRow not used, user name pass to be
							// fixed
			String paymentMethod, boolean insuranceRequired, String refundMethod)
			throws Exception {

		driver.get(baseUrl);
		textPresent(driver, "Search flights", 600);
	    assertEquals("Currency  QAR", driver.findElement(By.linkText("Currency  QAR")).getText());
		Reporter.log("Currency  QAR - Verifed");
		//System.out.println("Currency  QAR - Verifed");
	   
		assertTrue(isElementPresent(driver, By.cssSelector("i.flagIcon.qa")));
	    Reporter.log("QA Domain Flag Icon - Verifed");
		//System.out.println("QA Domain Flag Icon - Verifed");
	    
	    assertEquals("Search flights", driver.findElement(By.cssSelector("h1")).getText());
	    
	    airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightFilterType,attempt);// TODO:add more parameters
	    elementVisible(driver, getObject("AirCom_SRP_Oneway_BookButton"), 100);
	    
	    //OnSRP
	    /*assertEquals("QAR", driver.findElement(By.cssSelector("th.price > span.QAR.currencyCode")).getText());
	    Reporter.log("QAR Currency on SRP - Verifed");*/
		//System.out.println("QAR Currency on SRP - Verifed");
	   
		assertTrue("QA Specific Customer Care Number Not Displayed",driver.getPageSource().contains("+800-100-929")); //
		    
	    Reporter.log("QA Specific CC Number - Verifed");
		//System.out.println("QA Specific CC Number - Verifed");
	    
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
		baseUrl = dataFile.value("url_qa_qa2");
		
				
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
