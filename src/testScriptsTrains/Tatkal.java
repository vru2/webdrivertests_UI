// Framework - cleartrip Automation
// Author - N.SaiLikhitha

package testScriptsTrains;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Trains;

public class Tatkal extends Trains{
public RemoteWebDriver driver;
	private String baseUrl;

		static Date dNow = new Date( );
		static SimpleDateFormat ft = new SimpleDateFormat ("dd");
		static String CurrentDate = ft.format(dNow);
		static int CurDate = Integer.parseInt(CurrentDate)+1;
		static String TomDate = String.valueOf(CurDate);


	@DataProvider
	  public static Object [ ][ ] B2cTrainsTatkal() {
	      return new Object [ ] [ ] { { "SBC","MAS","Sleeper (SL)",CurrentDate,"Tatkal","Same","Kaveri Express","1","0","0","0","CREDIT CARD","B2C Trains HomePage SignIn General Booking with Adult=1","Your Booking is confirmed","Cancel"}};

	  };
	
	
	@Test (dataProvider="B2cTrainsTatkal",alwaysRun = true)	
	public void B2cTrainsTatkal(String DStation, String AStation, String Class, String TDate,String Quota,String BoardStation,String TrainsName,String Adult1,String Child1,String SMen,String SWomen,String Payment_Type, 
			String Logger_Msg, String Booking_Confirmation_Message , String IRCTC_Action) throws Exception{
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		safeClick(driver,getObject("B2C_Trains"));
		//B2cTrains_HomepageSignIn(driver);
		B2cTrains_HomepageSearch_tatkal1(driver,DStation,AStation,Class,TDate,Adult1,Child1,SMen,SWomen);
		B2cTrains_CheckAvail(driver,Quota,BoardStation,TrainsName);
		//B2cTrains_CheckAvail_Unsigned(driver,Quota,BoardStation,TrainsName);
		//B2cTrains_CheckAvailnew_Unsigned(driver,Quota,BoardStation,TrainsName);
		//B2cTrains_CheckavailSignIn(driver);
		//B2cTrains_CheckAvailtwo_Unsigned(driver,Quota,BoardStation,TrainsName);
		B2cTrains_Bookstep1(driver,BoardStation);
		//B2cTrains_BookstepSignin(driver);
		B2cTrains_Bookstep2(driver,Adult1,Child1,SMen,SWomen);
		Reporter.log(driver.getCurrentUrl(),true);
		 if (MakePaymentOnlyInQA2) {
		B2cTrains_Payment(driver,Payment_Type,Logger_Msg,Booking_Confirmation_Message);
		B2CTrains_paymentIntersitialPagewithforgotpasswordflow(driver);
		elementPresent_log(driver,getObject("air_amex_payment_button"),"amex payment button",50);
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
	
