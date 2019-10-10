	// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - June, 2016
// Author - Karthick Krishna
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

public class Pax_1A_ACFirstClass extends Trains{
	
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider
	  public static Object [ ][ ] B2cTrains_Pax_1Adult() {
	      return new Object [ ] [ ] { { "NDLS","MAS","AC First Class (1A)","10","General","Same","G T Express","1","0","0","0","CREDIT CARD","B2C Trains HomePage SignIn General Booking with Adult=1","Your Booking is confirmed","Cancel"}};
	     
	  };
	  
	@Test (dataProvider="B2cTrains_Pax_1Adult",alwaysRun = true)	  
	public void B2cTrains_Pax_2Adults_fc(String DStation, String AStaiton, String Class, String TDate,String Quota,String BoardStation,String TrainsName,String Adult1,String Child1,String SMen,String SWomen,String Payment_Type, 
			String Logger_Msg, String Booking_Confirmation_Message , String IRCTC_Action) throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		//safeClick(driver, getObject("B2cTrains_HomePage_TrainTab"));
		B2cTrains_HomepageSignIn(driver);
		B2cTrains_HomepageSearchwithSignin(driver,DStation,AStaiton,Class,TDate,Adult1,Child1,SMen,SWomen);
		B2cTrains_CheckAvail(driver,Quota,BoardStation,TrainsName);
		B2cTrains_Bookstep1(driver,BoardStation);
		B2cTrains_Bookstep2(driver,Adult1,Child1,SMen,SWomen);
		Reporter.log(driver.getCurrentUrl(),true);
		 if (MakePaymentOnlyInQA2) {
		B2cTrains_Payment(driver,Payment_Type,Logger_Msg,Booking_Confirmation_Message);
		B2CTrains_paymentIntersitialPage(driver);
		waitForElement(driver,50,getObject("air_amex_payment_button"));
		   Thread.sleep(3000);
	// safeClick(driver,getObject("air_amex_payment_button"));
		IRCTC_LoginPage(driver,IRCTC_Action);
		 }
		
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

