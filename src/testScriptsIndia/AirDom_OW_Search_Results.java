package testScriptsIndia;


import java.util.ArrayList;
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

public class AirDom_OW_Search_Results extends AirCommonMethod { 
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
    boolean airasia;
    boolean airindia;
    boolean indigo;
    boolean goair;
    boolean jetairways;
    boolean vistara;
    boolean spicejet;
    
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
	public static Object[][] B2CAirOWLCCDomFullRefund() 
	{
		String[] origin = {"blr", "blr","bom"};
		String[] destination = {"del","goi","del"};
		return new Object[][] 
				{ 
					{ origin, destination, "Flights", "", "lcc", "", "Direct", "1", "0", "0", "credit card", false} 
				};
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void Dom_LCC_Airline_82(String[] origin, String[] destin, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception 
	{
		ArrayList<String> arrlist = new ArrayList<String>();
		boolean flightCountFailure = true;
		int attempt = 0;
		SoftAssert s = new SoftAssert();

		Reporter.log(flightPreference + ":" + this.getClass() + " started");
		
		
		do 
		{
			driver.get(baseUrl);
						
			airCom_HomepageSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,0);
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
				int j=i+1;
				for(char c :flights1.get(i).getText().toCharArray())
				{
					if(Character.isDigit(c))
					{
						break test;
					}
				}

				arrlist.add(flights1.get(i).getText());
			}
			
			Boolean airlineNotFound = false;
		    if(arrlist.contains("Airasia_india") || arrlist.contains("Air Asia") ) 
		    {
		    	airasia=true;
		    }
		    else 
		    {
		    	Reporter.log("Airasia_india not found in this sector: " + origin[attempt] + " - " + destin[attempt] + " Will retry with a new sector!", true);
		    	airlineNotFound = true;
		    }
		    if(arrlist.contains("Air India"))  
		    {
		    	airindia=true;
		    }
		    else 
		    {
		    	Reporter.log("Air India not found in this sector: " + origin[attempt] + " - " + destin[attempt] + " Will retry with a new sector!", true);
		    	airlineNotFound = true;
		    }
		    if(arrlist.contains("GoAir"))
		    {
		    	goair=true;
		    }
		    else 
		    {
		    	Reporter.log("GoAir not found in this sector: " + origin[attempt] + " - " + destin[attempt] + " Will retry with a new sector!", true);
		    	airlineNotFound = true;
		    }
		    if(arrlist.contains("IndiGo")) 
		    {
		    	indigo=true;
		    }
		    else 
		    {
		    	Reporter.log("IndiGo not found in this sector: " + origin[attempt] + " - " + destin[attempt] + " Will retry with a new sector!", true);
		    	airlineNotFound = true;
		    }
		    if(arrlist.contains("Jet Airways")) 
		    {
		    	jetairways=true;
		    }
		    else 
		    {
		    	Reporter.log("Jet Airways not found in this sector: " + origin[attempt] + " - " + destin[attempt] + " Will retry with a new sector!", true);
		    	airlineNotFound = true;
		    }
		    if(arrlist.contains("SpiceJet")) 
		    {
		    	spicejet=true;
		    }
		    else 
		    {
		    	Reporter.log("SpiceJet not found in this sector: " + origin[attempt] + " - " + destin[attempt] + " Will retry with a new sector!", true);
		    	airlineNotFound = true;
		    }
		    if(arrlist.contains("Vistara")) 
		    {
		    	vistara=true;
		    }
		    else 
		    {
		    	Reporter.log("Vistara not found in this sector: " + origin[attempt] + " - " + destin[attempt] + " Will retry with a new sector!", true);
		    	airlineNotFound = true;
		    }
		    
		    
		    if(airlineNotFound)
		    {
		    	attempt++;
		    	continue;
		    }
						
			
		} while (!flightCountFailure || !vistara || !spicejet || !jetairways || !indigo || !airindia || !airasia || !goair && attempt < 3 );
	
		
		s.assertTrue(((attempt < 3) && (flightCountFailure)), "Booking failed after 3 attempts");
		
	    s.assertEquals(true,arrlist.contains("Airasia_india") || arrlist.contains("Air Asia"),"Airasia_india Airline Not Found");
	    s.assertEquals(true,arrlist.contains("Air India"),"AI Airline Not Found");
	    s.assertEquals(true,arrlist.contains("GoAir"),"Go Air Airline Not Found");
	    s.assertEquals(true,arrlist.contains("IndiGo"),"6E Airline Not Found");
	    s.assertEquals(true,arrlist.contains("Jet Airways"),"Jet Airways Airline Not Found");
	    s.assertEquals(true,arrlist.contains("SpiceJet"),"SG Airline Not Found");
	    s.assertEquals(true,arrlist.contains("Vistara"),"Vistara Airline Not Found");
	    
	    
	    Reporter.log("Airasia_india="+ (arrlist.contains("Airasia_india") || arrlist.contains("Air Asia")) ,true);
	    Reporter.log("Air India="+ arrlist.contains("Air India"),true);
	    Reporter.log("GoAir=" +arrlist.contains("GoAir"),true);
	    Reporter.log("IndiGo="+ arrlist.contains("IndiGo"),true);
	    Reporter.log("Jet Airways="+ arrlist.contains("Jet Airways"),true);
	    Reporter.log("SpiceJet="+ arrlist.contains("SpiceJet"),true);
	    Reporter.log("Vistara="+ arrlist.contains("Vistara"),true);
	    
		s.assertAll();
	
		
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




