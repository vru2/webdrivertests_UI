

	
	package testScriptsMobileAirforMEPWA;


	import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

	import dataServices.MobileDataProvider;
import domainServices.Mobile;

	public class PWA_MobileWeb_MEAir_DOM_RTBooking_AE extends Mobile{

		public RemoteWebDriver driver;
		private String baseUrl;
		String srpprice="";
		String paymentpage;
		boolean pgcheck=false;
		String pgfees;
		
	  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Dom_RT", groups={ "Smoke Test"})
	  public void pwa_MobileWeb_Air_Dom_RT_40212(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
		 
		 System.out.println(baseUrl);
		// driver.get(common.value("pwaairurlAE"));// properties files
		 driver.get(common.value("pwaairurlAE"));
		 driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		 if(elementVisible(driver,By.xpath("//div/h1[1][@class='Modal__heading']"),1)){
			
			 System.out.println("popup found");
			  driver.findElement(By.xpath("//div/a[@class='Modal__action']")).click();
			  System.out.println("slected english lang");
			  
		  }
		 
		 Reporter.log("PWA_MobileWeb_MEAir_DOM_RTBooking_AE",true); //?
		 pwa_Air_Homepage(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants,"rt");
		 pwa_Air_SRP(driver,"rt","all");
		 pwa_MEAir_ReviewItineraryPage(driver);
		 pwa_Air_Bookstep2(driver,Adults,Childrens,Infants,"");
		 pwa_MEAir_MakePayment(driver,"CREDITCARD","","");
	}

	  @BeforeClass
	  public void setUp() throws Exception {
		driver=getMobileDriver(driver);
		baseUrl = common.value("pwaairurlAE");
	  }

	  @AfterMethod(alwaysRun = true)
	  public void afterMethod(ITestResult _result) throws Exception {
	  	afterMethod(driver, _result);
	  }
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
		 // driver.quit();    
	  }















}
