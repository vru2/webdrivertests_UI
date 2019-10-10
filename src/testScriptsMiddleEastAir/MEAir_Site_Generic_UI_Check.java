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
import org.testng.annotations.Test;

import dataServices.HQDataProvider;
import dataServices.IndiaDataProvider;
import domainServices.AirCommonMethod;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import dataServices.IndiaDataProvider;

public class MEAir_Site_Generic_UI_Check extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	int attempt = 0;
	String domain = "ae";
	
	
	@Test(dataProviderClass = IndiaDataProvider.class, dataProvider = "AirOneWayDomestic")
	public void MEAir_AESite_Generic_Check(String[] fromSet,
			String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType,
			String resultClickRow, String adults, String children,
			String infants,// TODO:resultClickRow not used, user name pass to be
							// fixed
			String paymentMethod, boolean insuranceRequired, String refundMethod)
			throws Exception {

		driver.get(baseUrl);
		if (!checkIfAESignedIn(driver)) {
			airCom_HomepageSignInForHQScripts(driver, domain);
		}
		
		textPresent(driver, "Search flights", 60000);
		assertEquals("Currency  AED", driver.findElement(By.linkText("Currency  AED")).getText());
		Reporter.log("Currency  AED - Verifed");
		//System.out.println("Currency  AED - Verifed");
	   
		assertTrue(isElementPresent(driver, By.cssSelector("i.flagIcon.ae")));
	    Reporter.log("AE Domain Flag Icon - Verifed");
		//System.out.println("AE Domain Flag Icon - Verifed");
	    
	    assertEquals("Search flights", driver.findElement(By.cssSelector("h1")).getText());
	    
	    airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightFilterType,attempt);// TODO:add more parameters
	    elementVisible(driver, getObject("AirCom_SRP_Oneway_BookButton"), 30000);
	    //OnSRP
	    assertEquals("AED", driver.findElement(By.cssSelector("th.price > span.AED.currencyCode")).getText());
	    Reporter.log("AED Currency on SRP - Verifed");
		//System.out.println("AED Currency on SRP - Verifed");
	   
		assertTrue("AE Specific Customer Care Number Not Displayed",driver.getPageSource().contains("8000184598")); //
		    
	    Reporter.log("AE Specific CC Number - Verifed");
		//System.out.println("AE Specific CC Number - Verifed");
	    
	}

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = common.value("meairurl");
		
				
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
	}

}