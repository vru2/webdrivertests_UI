package testScriptsMobileTrains;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Trains;

public class MobileTrains_with_GST extends Trains{


	//EBL-6734
	
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider
	  public static Object [ ][ ] MobileTrains_with_GST() {
	      return new Object [ ] [ ] { { "SBC","MYS","Sleeper (SL)","10","General","Same","G T Express","1","0","0","0","CREDITCARD","B2C Trains HomePage SignIn General Booking with Adult=1","Your Booking is confirmed","Cancel"}};
	     
	  };
	  
	@Test (dataProvider="MobileTrains_with_GST")	  
	public void mobileTrains_with_GST(String DStation, String AStaiton, String Class, String TDate,String Quota,String BoardStation,String TrainsName,String Adult1,String Child1,String SMen,String SWomen,String Payment_Type, 
			String Logger_Msg, String Booking_Confirmation_Message , String IRCTC_Action) throws Exception {
		
		driver.get(baseUrl);
		//mobileTrains_HomepageSignIn(driver);
		//driver.get("https://qa2.cleartrip.com/m/v2/trains/search");
		mobileTrains_HomepageSignIn(driver);
		mobileTrains_HomepageSearch(driver,DStation,AStaiton,Class,TDate,Adult1,Child1,SMen,SWomen,"general",0);
		mobileTrains_CheckAvail(driver,Quota,BoardStation,TrainsName);
		mobileTrains_Bookstep1(driver,BoardStation);
		mobileTrains_Bookstep2(driver,Adult1,Child1,SMen,SWomen,"gst");
		Reporter.log(driver.getCurrentUrl(),true);
	    
		//mobileTrains_Payment(driver,"CREDITCARD",Logger_Msg,Booking_Confirmation_Message);
		 if ((common.value("makePayment").equals("true"))) {
			 mobileTrains_PaymentR(driver,Payment_Type,Logger_Msg,Booking_Confirmation_Message);
		IRCTC_LoginPageR(driver,IRCTC_Action);
		 }
		Thread.sleep(5000);
		//driver.quit();
		
	}
	
	 @BeforeClass
	  public void setUp() throws Exception {
		  System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 8_4 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8H7 Safari/6533.18.5");
			driver = new ChromeDriver(options);
			
			 driver.manage().deleteAllCookies(); 
		//driver=getMobileDriver(driver);
			 baseUrl = common.value("PWATrainsURL");
	  }
	  /*@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}*/
	 @AfterClass
	  public void tearDown() throws Exception {
		  driver.quit();    
	  }



}
