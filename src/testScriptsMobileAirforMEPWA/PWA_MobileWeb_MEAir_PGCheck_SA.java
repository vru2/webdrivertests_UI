package testScriptsMobileAirforMEPWA;

import static org.testng.Assert.assertTrue;

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

	public class PWA_MobileWeb_MEAir_PGCheck_SA extends Mobile{
		//EBL-6783
	    public RemoteWebDriver driver;
		private String baseUrl;
		String srpprice="";
		String paymentpage;
		boolean pgcheck=false;
	String pgfees;
	  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Dom_OW", groups={ "Smoke Test"})
	  public void pwa_MobileWeb_Air_Dom_OWPGchek_SA(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
		 
		 System.out.println(baseUrl);
		 driver.get(common.value("pwaairurlSA"));
		// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		 if(elementVisible(driver,By.xpath("//div/h1[1][@class='Modal__heading']"),1)){
			
			 System.out.println("popup found");
			  driver.findElement(By.xpath("//div/a[@class='Modal__action']")).click();
			  System.out.println("slected english lang");
			  
		  }
		 
		 Reporter.log("PWA_MobileWeb_MEAir_PGCheck_SA",true);
		 pwa_Air_Homepage(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants,"");
		 pwa_Air_SRP(driver,"","all");
		 
		 String totalinitinerary=pwa_MEAir_ReviewItineraryPage(driver).trim();
		 pwa_Air_Bookstep2(driver,Adults,Childrens,Infants,"");
		 String pgdetails=checkPGFee(driver);
		 String paymenttot=getText(driver,By.xpath("//*[text()='Total booking amount']//../*[2]")).substring(1).replace(",","").trim();

		 System.out.println("pg details "+pgdetails);
		 System.out.println("paymenttot details "+ paymenttot);
		
		  pwa_MEAir_MakePayment(driver,"CREDITCARD","","");
		  String ConfirmationUrl=driver.getCurrentUrl();
		System.out.println("Confirmation url"+ConfirmationUrl);
		
		String s1="qa2.cleartrip.sa";
		String replaceString=ConfirmationUrl.replace("qa2","www");
		
		String tripID = driver.findElement(By.xpath("//p[text()='Trip ID']/parent::*/*[2]")).getText();
		Reporter.log("Trip ID=" + tripID, true);
		  }
	 

	  @BeforeClass
	  public void setUp() throws Exception {
		driver=getMobileDriver(driver);
		baseUrl = common.value("pwaairurlSA");
	  }

	 /* @AfterMethod(alwaysRun = true)
	  public void afterMethod(ITestResult _result) throws Exception {
	  	afterMethod(driver, _result);
	  }*/
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
		//  driver.quit();    
	  }





	}



