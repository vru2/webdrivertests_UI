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

public class Pax_4A_Sleeper_AmazonPay extends Trains {
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider
	  public static Object [ ][ ] B2cTrains_Pax_1Adult() {
	      return new Object [ ] [ ] { { "SBC","MAS","Sleeper (SL)","10","General","Same","Kaveri Express","4","0","0","0","WALLETS","B2C Trains HomePage SignIn General Booking with Adult=1","Your Booking is confirmed","Cancel"}};
	     
	  };
	 
	  @Test (dataProvider="B2cTrains_Pax_1Adult",alwaysRun = true)	  
		public void B2cTrains_Pax_4A_Sleeper(String DStation, String AStaiton, String Class, String TDate,String Quota,String BoardStation,String TrainsName,String Adult1,String Child1,String SMen,String SWomen,String Payment_Type, 
				String Logger_Msg, String Booking_Confirmation_Message , String IRCTC_Action) throws Exception {
		  driver.get(baseUrl);
		  B2cTrains_HomepageSignIn(driver);
		  B2cTrains_HomepageSearchwithSignin(driver,DStation,AStaiton,Class,TDate,Adult1,Child1,SMen,SWomen);
		  B2cTrains_CheckAvail(driver,Quota,BoardStation,TrainsName);
		  B2cTrains_Bookstep1(driver,BoardStation);
		  B2cTrains_Bookstep2(driver,Adult1,Child1,SMen,SWomen);
			Reporter.log(driver.getCurrentUrl(),true);
			 if (MakePaymentOnlyInQA2) {
			B2cTrains_Payment(driver,Payment_Type,Logger_Msg,Booking_Confirmation_Message);
			B2CTrains_paymentIntersitialPage(driver);
			B2C_TrainsWallet(driver,Payment_Type);
			Thread.sleep(2000);
			B2cTrains_Bookstep1(driver,BoardStation);
			  B2cTrains_Bookstep2(driver,Adult1,Child1,SMen,SWomen);
			  Reporter.log(driver.getCurrentUrl(),true);
			  textPresent_Log(driver,"Transfer funds from",5);
			  elementPresent_log(driver,getObject("Payment_Submit"),"Payment Submit Button",5);
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

