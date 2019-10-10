// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAccounts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class NPS_Hotel extends IndiaHotels{
		public RemoteWebDriver driver;
		private String TripID = null;
		
	  @Test
	  public void HotelCom_Cancel_HQ_551() throws Exception {
	      driver.manage().deleteAllCookies(); 
	      hotelCom_DetailsPage(driver, "com", getHotelID(), 40, "");
	      hotelCom_ItineraryPage(driver, "");
	      hotelCom_LoginPage(driver, "Unsigned", "");
		  hotelCom_TravelerPage(driver);
		  hotelCom_PaymentPage(driver, ""	, "", "");
		  JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@class='npsScale__cell'][9]"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
				safeClick(driver,By.xpath("//*[@class='npsScale__cell'][9]"));
				waitElement(driver,By.xpath("//textarea"),10);
				WebElement element1 = driver.findElement(By.xpath("//textarea"));
				js.executeScript("arguments[0].scrollIntoView(true);", element1);
				safeClick(driver,By.xpath("//textarea"));
				safeType(driver,By.xpath("//textarea"),"testing");
				safeClick(driver,By.xpath("//input[@value='Submit']"));
				waitElement(driver,By.xpath("//*[contains(text(),'We appreciate')]"),10);
				String x=getText(driver,By.xpath("//*[contains(text(),'We appreciate')]"));
				Assert.assertEquals(" We appreciate you taking the time to help us do better. ".trim(),x.trim());			
	  }

	  @BeforeClass
	  public void setUp() throws Exception{
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