package testScriptsIndiaHotels;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.GetTitle;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;
import junit.framework.Assert;

public class HotelCom_SRP_SortOrder_Default extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
	private String firsthotel=null;
	String firstSRPHotel=null;
	
	@Test
	public void HotelSortOrder_Default() throws Exception{
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);	  
	 	hotelCom_AddCookie(driver);
	 	driver.get(baseUrl+"/hq");
	 	String firsthotel=HotelCom_SRP_SortOrder(driver,"default");
	  	driver.get(hotelSrpUrl(driver,"Hyderabad","Telangana","IN"));
	  	waitForElementVisibility(driver, By.id("showNearByBox"), 50);
	  	firstSRPHotel=getAttribute(driver, getObjectHotels("HotelCom_SRP_FirstHotelName"), "title");
	  	System.out.println(firstSRPHotel);
	  	if(firsthotel.contains(firstSRPHotel)){
	  		Reporter.log("Default sort order working fine");
	  	}
	  	else{
	  		Reporter.log("Default sort order not working");
	  		Assert.assertTrue(false);
	  	}
	}
	  	
	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
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
