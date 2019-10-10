// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Dec, 2016
// Author - SaiLikhithass
// Copyright © 2016 Cleartrip (P) Ltd. All right reserved.


package testScriptsTrains;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Trains;

public class General_NB_Prod extends Trains{
	
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider
	  public static Object [ ][ ] B2CTrains_NetbankingRetry() {
	      return new Object [ ] [ ] { { "SBC","MAS","Sleeper (SL)","10","General","Same","Sanghamitra Exp","1","0","0","0","Net Banking","B2C Trains HomePage SignIn General Booking with Adult=1","Your Booking is confirmed","Cancel"}};
	     
	 
	  };
	  
	@Test(dataProvider="B2CTrains_NetbankingRetry",alwaysRun = true)
	public void B2CTrains_NetbankingRetry_334(String DStation, String AStaiton, String Class, String TDate,String Quota,String BoardStation,String TrainsName,String Adult1,String Child1,String SMen,String SWomen,String Payment_Type, 
			String Logger_Msg, String Booking_Confirmation_Message , String IRCTC_Action) throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		B2cTrains_HomepageSignIn_Prod(driver);
		B2cTrains_HomepageSearchwithSignin(driver,DStation,AStaiton,Class,TDate,Adult1,Child1,SMen,SWomen);
		B2cTrains_CheckAvail(driver,Quota,BoardStation,TrainsName);
		B2cTrains_Bookstep1(driver,BoardStation);
		B2cTrains_Bookstep2(driver,Adult1,Child1,SMen,SWomen);
		Reporter.log(driver.getCurrentUrl(),true);
		B2cTrains_Payment(driver,Payment_Type,Logger_Msg,Booking_Confirmation_Message);
		B2CTrains_paymentIntersitialPage(driver);		
		B2CTrains_Netbanking(driver);
		
		}
	
	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
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

