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

public class MobileTrains_WaitingList extends Trains{


	//EBL-6734


	
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider
	  public static Object [ ][ ] MobileTrains_Tatkal() {
		String[] origin = {"sbc","sbc","del","BLR"};  
		String[] destination = {"Adoni","Chennai","bom","CCU"};
		
	      return new Object [ ] [ ] { { origin,destination,"Sleeper (SL)","10","wl","Same","G T Express","1","0","0","0","CREDITCARD","B2C Trains HomePage SignIn General Booking with Adult=1","Your Booking is confirmed","Cancel"}};
	     
	  };
	  
	@Test (dataProvider="MobileTrains_Tatkal")	  
	public void mobileTrains_WaitingList(String[] origin, String[] destination, String Class, String TDate,String Quota,String BoardStation,String TrainsName,String Adult1,String Child1,String SMen,String SWomen,String Payment_Type, 
			String Logger_Msg, String Booking_Confirmation_Message , String IRCTC_Action) throws Exception {
		boolean found=false;
		int i=0;
		
		//driver.get("https://qa2.cleartrip.com/m/v2/trains/search");
		//mobileTrains_HomepageSignIn(driver);
		do{
			
		driver.get(baseUrl);
		
		
		//driver.get("https://qa2.cleartrip.com/m/v2/trains/search");
		if(i==0) {
		mobileTrains_HomepageSignIn(driver);
		}
		mobileTrains_HomepageSearch(driver,origin[i],destination[i],Class,TDate,Adult1,Child1,SMen,SWomen,"Tatkal",i);
		found=mobileTrains_CheckAvail(driver,Quota,BoardStation,TrainsName);
		System.out.println("found value="+found);
		if(found) {
		mobileTrains_Bookstep1(driver,BoardStation);
		mobileTrains_Bookstep2(driver,Adult1,Child1,SMen,SWomen,"");
		Reporter.log(driver.getCurrentUrl(),true);
	    
		//mobileTrains_Payment(driver,"CREDITCARD",Logger_Msg,Booking_Confirmation_Message);
		 if ((common.value("makePayment").equals("true"))) {
			 mobileTrains_PaymentR(driver,Payment_Type,Logger_Msg,Booking_Confirmation_Message);
		IRCTC_LoginPageR(driver,IRCTC_Action);
		 }
		Thread.sleep(5000);
		}
		i++;
	}while(!found && i<3);
		
		
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
	 /* @AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}*/
	 @AfterClass
	  public void tearDown() throws Exception {
		  driver.quit();    
	  }





}
