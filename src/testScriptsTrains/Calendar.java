package testScriptsTrains;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Trains;


public class Calendar extends Trains{


	public RemoteWebDriver driver;
	public String baseUrl;
	String domain = "com";
	
	
		@DataProvider
		  public static Object [ ][ ] Trains_Calendar() {
		      return new Object [ ] [ ] { { "SBC","MAS","Sleeper (SL)"}};
	
		  };
		  
		
		  
		@Test (dataProvider="Trains_Calendar",alwaysRun = true)	
		public void B2CTrainsCalendarAvail(String Dstation, String Astation, String Class) throws Exception{
			driver.manage().deleteAllCookies();
			B2cTrains_CalendraAvailability(driver,Dstation,Astation,Class);
			
		}

		
		@BeforeClass
		  public void setUp() throws Exception {
			driver=(RemoteWebDriver) getDriver(driver);
			baseUrl = getBaseUrl(domain);
		}
		  @AfterMethod(alwaysRun = true)
			public void afterMethod(ITestResult _result) throws Exception {
				afterMethod(driver, _result);
			}
		  @AfterClass
		  public void tearDown() throws Exception {
			  browserClose(driver);
		  }

}
