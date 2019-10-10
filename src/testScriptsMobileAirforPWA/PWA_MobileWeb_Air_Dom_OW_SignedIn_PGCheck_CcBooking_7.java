package testScriptsMobileAirforPWA;

import static org.testng.Assert.assertTrue;

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

public class PWA_MobileWeb_Air_Dom_OW_SignedIn_PGCheck_CcBooking_7 extends Mobile{
	//EBL-6783
    public RemoteWebDriver driver;
	private String baseUrl;
	String srpprice="";
	String paymentpage;
	boolean pgcheck=false;
String pgfees;
  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Dom_OW", groups={ "Smoke Test"})
  public void pwa_MobileWeb_Air_Dom_OW(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
	 System.out.println(baseUrl);
	 driver.get(common.value("pwaairurl"));
	 //driver.get("http://192.168.45.202:3000/m/v2/flights/");
	//driver.get("https://devpwa.cleartrip.com/m/v2/flights");
	 Reporter.log("PWA_MobileWeb_Air_Dom_OW",true);
	 pwa_Air_Homepage(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants,"");
	 pwa_Air_SRP(driver,"","all");
	 
	 String totalinitinerary=pwa_Air_ReviewItineraryPage(driver).trim();
	 pwa_Air_Bookstep2(driver,Adults,Childrens,Infants,"");
	 String pgdetails=checkPGFee(driver);
	 String paymenttot=getText(driver,By.xpath("//*[text()='Total booking amount']//../*[2]")).substring(1).replace(",","").trim();
	 int paymenttotal=Integer.parseInt(paymenttot);
	 int ittotal=Integer.parseInt(totalinitinerary);
	 int pgfees=Integer.parseInt(pgdetails);
	System.out.println("paymenttotal="+paymenttotal);
	 System.out.println("ittotal="+ittotal);
	 System.out.println("pgfees="+pgfees);
	  assertTrue((ittotal+pgfees)==paymenttotal);
	 pwa_Air_MakePayment(driver,"CREDITCARD","","");
	  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver3(driver);
	baseUrl = common.value("pwaairurl");
  }

 /* @AfterMethod(alwaysRun = true)
  public void afterMethod(ITestResult _result) throws Exception {
  	afterMethod(driver, _result);
  }*/
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	  driver.quit();    
  }





}
