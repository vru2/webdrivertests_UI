// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - April, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsMobileMiddleEast;


//import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

	public class MobileAE_Air_Dom_OW extends Mobile{
	public RemoteWebDriver driver;
	private String baseUrl;


  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileAE_Dom_OW")
  public void MobileAE_OW_Dom_431(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
	  driver.get(baseUrl);
	  /*Select language for AE site
		Author Yashmin*/
	  if(elementVisible(driver,By.xpath("//body/div/div/div/h3"),4)){
		  driver.findElement(By.id("arabic_select")).click();
		  
	  }
	     //to select arabic language
	//  driver.findElement(By.id("arabic_select")).click();
	  mobileCom_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
	  mobileCom_Air_SRP_Oneway(driver, Carrier1, Carrier3, Carrier3);
	  mobileCom_Air_ReviewItineraryPage(driver, "");
	  mobileCom_Air_TravelerPage(driver);
	  if ((common.value("makePayment").equals("true"))) {
	  mobileCom_Air_MakePaymentPage(driver, "" ,"" , Trip_Logger_Msg); 
	  }
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver(driver);
	baseUrl = common.value("urlae");
  }

  @AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	afterMethod(driver, _result);
	 }
  
  @AfterClass
  public void tearDown() throws Exception {
	  //driver.quit();    
  }

}