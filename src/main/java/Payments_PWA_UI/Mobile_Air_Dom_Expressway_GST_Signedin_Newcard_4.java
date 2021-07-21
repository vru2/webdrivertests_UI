 package Payments_PWA_UI;
 import domainServices.Mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class Mobile_Air_Dom_Expressway_GST_Signedin_Newcard_4 extends Mobile{


	
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@Test(dataProviderClass=MobileDataProvider.class,dataProvider="MobileCom_Dom_OW")
	
	public void mobileCom_Air_Dom_Expressway(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String GoAir, String SpiceJet, String Indigo) throws Exception {
		 Thread.sleep(5000);
		driver.get(baseUrl);
   // String TripID;
   // Thread.sleep(5000);
  /*  driver.switchTo().frame("branch-banner-iframe");
    if(elementPresent(driver,By.xpath("//div[@id='branch-banner-close1']"),2)){
		  safeClick(driver,By.xpath("//div[@id='branch-banner-close1']"));
	  }*/
		
   // mobileCom_SignIn(driver);
    driver.get(baseUrl);
    pwa_Air_Homepage(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants,"");
   // mobileCom_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
   Thread.sleep(2000);
    mobileCom_Air_SRP_Oneway1(driver,"SpiceJet");
    pwa_Air_ReviewItineraryPage(driver);
    Thread.sleep(2000);
	// pwa_Air_Bookstep2(driver,Adults,Childrens,Infants,"");
	 
    mobileCom_Air_Expressway_Itinerary_GST(driver,"SpiceJet",true);
    pwa_Air_MakePayment(driver,"Net Banking","","");
    /*if ((common.value("makePayment").equals("true"))) {
        mobileCom_Air_MakePaymentPage(driver, "", "EXPRESSWAY", Trip_Logger_Msg);
        TripID = getText(driver, getObject("MobileCom_Air_ConfirmationPage_TripID"));
 	   String TotalPrice=driver.findElement(By.xpath("//*[@class='kvp paymentBreakup']/dd/strong")).getText().split(" ")[1].replace(",","");
 	   System.out.println(TotalPrice);
 //checkDetails(driver,"Q1707100101","SpiceJet",false);
 	checkDetails(driver,TripID.split(":")[1].trim(),"SpiceJet",false);
        //mobileCom_Air_MakePaymentExpwayPage(driver,Trip_Logger_Msg);
     }*/   
    
    //mobileCom_Air_MakePaymentExpwayPage(driver,Trip_Logger_Msg);
   
	}
	 @BeforeClass
	  public void setUp() throws Exception {
		driver=getMobileDriver3(driver);
		/* System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		  driver = new ChromeDriver();*/
		  
		baseUrl = common.value("pwaairurl");
	  }

	/* @AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}*/
	  
	 @AfterClass
	  public void tearDown() throws Exception {
		 // driver.quit();
	  }
	
	
	


}
