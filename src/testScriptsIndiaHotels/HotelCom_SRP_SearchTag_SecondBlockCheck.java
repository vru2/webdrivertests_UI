package testScriptsIndiaHotels;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class HotelCom_SRP_SearchTag_SecondBlockCheck extends IndiaHotels {
	public RemoteWebDriver driver;
	
	@Test
	public void SearchTag_SecondBlock() throws Exception{
		SRP_URL(driver,"com","Goa","Goa","37264");
		Thread.sleep(1000);
		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_SearchTag_Block1"), 10)){
			if(elementNotVisible(driver, getObjectHotels("HotelCom_SRP_SearchTag_Clear"), 10)){
				for (int i = 0; i < 20; i++ ) {	
					if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_SearchTag_SecondBlock"),1)){
						JavascriptExecutor j = (JavascriptExecutor) driver;
						j.executeScript("window.scrollBy(0, 750)", "");	
					} 
				}	
				Reporter.log("Search-tag displayed after scroll");
				safeClick(driver, By.id("triggerPromoTagBtn"));
				if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelCount"), 10)){	
					Reporter.log("Scrolled back after clicking on Done button");
				}
				else{
					Reporter.log("Second search tag block DONE button not working");
					Assert.assertTrue(false);
				}
			}
			else{
				Reporter.log("Search tag select already selected");
				safeClick(driver, getObjectHotels("HotelCom_SRP_YourSort_ClearAll"));
				driver.navigate().refresh();
				for (int s = 0; s < 20; s++) {
					if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_SearchTag_SecondBlock"),1))
					{
						JavascriptExecutor jse = (JavascriptExecutor) driver;
						jse.executeScript("window.scrollBy(0, 750)","");
					} 
					else{
						Reporter.log("Second serch-tag block appeared after scroll");
						safeClick(driver, By.id("triggerPromoTagBtn"));
					}
				}		
				if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelCount"), 10)){
					Reporter.log("Scrolled back after clicking on Done button");
				}
				else{
					Reporter.log("Second search tag block DONE button not working");
					Assert.assertTrue(false);
				}
			
		 	 	}
			}
		else{
			Reporter.log("Search tags not displayed");
	 	 	Assert.assertTrue(false);
		}
	}
	
	@BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	  }
	  
	 @AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
	  
	  @AfterClass
	  public void tearDown() throws Exception {
		browserClose(driver);
	  }
}
