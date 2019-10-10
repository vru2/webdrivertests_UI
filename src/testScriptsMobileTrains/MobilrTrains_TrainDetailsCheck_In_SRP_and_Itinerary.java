package testScriptsMobileTrains;

import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Trains;
import junit.framework.Assert;

public class MobilrTrains_TrainDetailsCheck_In_SRP_and_Itinerary extends Trains{
HashMap<String,String> hp=new HashMap<String,String>();
HashMap<String,String> hp1=new HashMap<String,String>();

	//EBL-6734


	
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider
	  public static Object [ ][ ] MobileTrains_Pax_MultiPax() {
	      return new Object [ ] [ ] { { "NDLS","MAS","Sleeper (SL)","10","General","Same","G T Express","1","0","0","0","CREDITCARD","B2C Trains HomePage SignIn General Booking with Adult=1","Your Booking is confirmed","Cancel"}};
	     
	  };
	  
	@Test (dataProvider="MobileTrains_Pax_MultiPax")	  
	public void b2cTrains_Pax_2Adults_288(String DStation, String AStaiton, String Class, String TDate,String Quota,String BoardStation,String TrainsName,String Adult1,String Child1,String SMen,String SWomen,String Payment_Type, 
			String Logger_Msg, String Booking_Confirmation_Message , String IRCTC_Action) throws Exception {
		
		driver.get(baseUrl);
		//mobileTrains_HomepageSignIn(driver);
		//driver.get("https://qa2.cleartrip.com/m/v2/trains/search");
		mobileTrains_HomepageSignIn(driver);
		mobileTrains_HomepageSearch(driver,DStation,AStaiton,Class,TDate,Adult1,Child1,SMen,SWomen,"general",0);
		mobileTrains_CheckSRPDetails(driver,Quota,BoardStation,TrainsName,hp);
		mobileTrains_Bookstep1Details(driver,BoardStation,hp1);
		/*CheckSRPSorting(driver,Quota,BoardStation,TrainsName,hp);
		
		System.out.println(hp);*/
		checkDetails(hp,hp1,Adult1,Child1,SMen,SWomen);
		Reporter.log(driver.getCurrentUrl());
		//mobileTrains_Bookstep1Details(driver,BoardStation,hp1);
		
        /*String day=hp.get("dayclasstraveller").split("\\|")[0].replace("[","").replace("]","").trim();
		String traveller=hp.get("dayclasstraveller").split("\\|")[2].split(" ")[1];
		//System.out.println("--"+traveller);
		String departuretime=hp.get("timings").split("\\-")[0].replace("[","").replace("]","").trim();
		String arrivaltime=hp.get("timings").split("-")[1].split("\\|")[0].replace("[","").replace("]","").trim();
		String TrainNameinSRP=hp.get("trainname").replace("[","").replace("]","").trim().toUpperCase();
		String classnameinSRP=hp.get("class");
		String dayinItinerary=hp1.get("daydetails").replace("[","").replace("]","").trim();
		String departuretimeinItinerary=hp1.get("departuretime").replace("[","").replace("]","").trim();
		String ArrivaltimeinItinerary=hp1.get("arrivaltime").replace("[","").replace("]","").trim();
		String TrainNameinItinerary=hp1.get("trainname").replace("[","").replace("]","").trim();
		String classinItinerary=hp1.get("class").split(" ")[0];
		String durationinSRP=hp.get("timings").split("\\|")[1].replace("[","").replace("]","").trim();
		String durationinItinerary=hp1.get("duration").replace("[","").replace("]","").trim();
		//System.out.println("duration in Itinerary="+hp1.get("duration"));
		//System.out.println("day in Itinerary="+hp1.get("daydetails").replace("[","").replace("]",""));
		//System.out.println("day in SRP="+day.replace("[","").replace("]",""));
		Assert.assertEquals(day,dayinItinerary);
		Assert.assertEquals(departuretime,departuretimeinItinerary);
		Assert.assertEquals(arrivaltime,ArrivaltimeinItinerary);
		Assert.assertEquals(TrainNameinSRP,TrainNameinItinerary);
		Assert.assertEquals(classnameinSRP,classinItinerary);
		Assert.assertEquals(Adult1,traveller);
		Assert.assertEquals(durationinSRP,durationinItinerary);*/
		
		/*System.out.println("day in SRP="+day);
		System.out.println("departuretime in SRP="+departuretime);
		System.out.println("Arrival time in SRP="+arrivaltime);
		System.out.println("Train Name in SRP="+hp.get("trainname"));
		System.out.println("class in SRP="+hp.get("class"));
		System.out.println("day in Itinerary="+hp1.get("daydetails"));
		System.out.println("departuretime in Itinerary="+hp1.get("departuretime"));
		System.out.println("Arrival time in Itinerary="+hp1.get("arrivaltime"));
		System.out.println("Train Name in Itinerary="+hp1.get("trainname"));
		System.out.println("class in Itinerary="+hp1.get("class").split(" ")[0]);
		System.out.println(hp1);*/
		
	/*	mobileTrains_Bookstep2(driver,Adult1,Child1,SMen,SWomen,"");
		Reporter.log(driver.getCurrentUrl(),true);
	    
		//mobileTrains_Payment(driver,"CREDITCARD",Logger_Msg,Booking_Confirmation_Message);
		 if ((common.value("makePayment").equals("true"))) {
			 mobileTrains_PaymentR(driver,Payment_Type,Logger_Msg,Booking_Confirmation_Message);
		IRCTC_LoginPageR(driver,IRCTC_Action);
		 }
		Thread.sleep(5000);*/
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
	 /* @AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}*/
	  @AfterClass
	  public void tearDown() throws Exception {
		  driver.quit();    
	  }





}
