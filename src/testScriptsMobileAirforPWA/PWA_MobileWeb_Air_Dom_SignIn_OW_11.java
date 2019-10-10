package testScriptsMobileAirforPWA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class PWA_MobileWeb_Air_Dom_SignIn_OW_11 extends Mobile{
	//EBL-6783
	public RemoteWebDriver driver;
	private String baseUrl;
	String srpprice="";
	String paymentpage;
	boolean pgcheck=false;
String pgfees;
  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Dom_OW", groups={ "Smoke Test"})
  public void pwa_MobileWeb_Air_Dom_SignIn_OW_40213(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
	 System.out.println(baseUrl);
	 driver.get(common.value("pwaairurl"));
	 //driver.get("http://192.168.45.202:3000/m/v2/flights/");
	 //driver.get("https://devpwa.cleartrip.com/m/v2/flights");
	 Reporter.log("PWA_MobileWeb_Air_Dom_SignIn_OW",true);
	 //pwa_signIn(driver);
	 mobileCom_SignIn(driver);
	 driver.get(baseUrl);
	 pwa_Air_Homepage(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants,"");
	 pwa_Air_SRP(driver,"","all");
	 pwa_Air_ReviewItineraryPage(driver);
	 pwa_Air_Bookstep2(driver,Adults,Childrens,Infants,"");
	// waitForElement(driver,20,By.xpath("//*[text()='Traveller Info']"));
	 /*WebElement element1 = (new WebDriverWait(driver,50))
			   .until(ExpectedConditions.elementToBeClickable(getObject("MobileWeb_Trains_In_Travellers_Continue_ButtonR")));
	 JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(getObject("MobileWeb_Trains_In_Travellers_Continue_ButtonR"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		safeClick(driver,getObject("MobileWeb_Trains_In_Travellers_Continue_ButtonR"));*/
	// pwa_Air_Bookstep2(driver,Adults,Childrens,Infants,"");
	 //pwa_Air_MakePayment(driver,"Net Banking","","");
	 pwa_Air_MakePayment(driver,"CREDITCARD","","");
	  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver3(driver);
	baseUrl = common.value("pwaairurl");
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod(ITestResult _result) throws Exception {
  	afterMethod(driver, _result);
  }
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	  driver.quit();    
  }






}
