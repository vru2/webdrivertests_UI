/*
 * Created by Prashanth S.
 * Modified and updated by prashanthk@cleartrip.com
 * Reason: Fix issues and add improved logging.
 */


package testScriptsIndia;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

	public class Air_TopDeals_Calendar_Flow extends AirCommonMethod
	{
		public RemoteWebDriver driver;
		private String baseUrl;
		public String domain = "com";
		
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
	
		@Test
		public void test_Air_TopDeals_Calendar_Flow_36446() throws Exception 
		{
			driver.get(baseUrl);
	       
			Reporter.log("Clicking on the the Flight Deals link on Homepage", true);
			elementVisible(driver, By.linkText("Flight Deals"), 10);
			safeClick(driver, By.linkText("Flight Deals"));
			switchFromTab2toTab1(driver);
			Thread.sleep(5000);
			elementVisible(driver, getObject("TopDeal_Flight"), 30);
			Thread.sleep(10000);
			Reporter.log("Clicking on the the Flight Deals link on Homepage", true);
			
			   String Flights = getText(driver, getObject("TopDeal_Flight"));
			   Reporter.log("Flight Name is "+Flights, true);
			   String[] words=Flights.split("\\s");
			   
			   for(String w:words)
			   {  
				   Reporter.log(w,true);  
			   }  
			   
			   String searchdate = words[0] + " " + words[1] + " " + words[2];
			   Reporter.log("Search Date is " +searchdate,true); 
			  
			   String searchedairline = words[4];
			   Reporter.log("searchedairline in calendar page is " +searchedairline,true);
			   
			   /*String searchdate=Flights.split("|")[1].replaceAll("\\s","");*/
			   
			   safeClick(driver, getObject("TopDeal_Cal"));
			   Thread.sleep(5000);
			   switchFromTab2toTab1(driver);
			   String CalendarPage = getText(driver, getObject("TopDeal_Cal_Flight"));
			   Reporter.log("flight details in calendar Cell "+CalendarPage,true);
				
			   String[] words1=CalendarPage.split("\\s");
			   for(String w1:words1)
			   {  
				   Reporter.log(w1);  
			   }
			
			   String calendardate = words[0] + " " + words[1] + " " + words[2];
			   Reporter.log("Calendar Date is "+calendardate,true);
			   
			   if (calendardate.equals(searchdate)) 
			   {
				   Reporter.log("Search date and calendar date are matching to:" +calendardate,true);
			   }
			   else 
			   {
				   Reporter.log("Search date and calendar date are not matching :" +calendardate,true);
				   Assert.assertEquals(calendardate, searchdate);
			   }
	
			   String searchURL = getAttribute(driver, getObject("TopDeal_Best_Fare"), "href");
			   
			   driver.navigate().to(searchURL);
			   String AirlineName = getText(driver, getObject("TopDeal_SRP_Airline_Name"));
		
			   if (AirlineName.contains(searchedairline))
			   {
				   Reporter.log("Top Deals airline matches searched airline:" +AirlineName,true);
			   }
			   else 
			   {
				   Reporter.log("Top Deals airline doesnt matches searched airline:" +AirlineName,true);
				   Assert.assertEquals(AirlineName, searchedairline);
			   }
		}
  
  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
	  screenshot(_result, driver);
	  //afterMethod(driver, _result);
	}
  
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}