// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - April, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsMobileMiddleEast;


import static org.testng.Assert.assertTrue;

//import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

	public class MobileAE_Air_Dom_OW_Conv_Fee_Check extends Mobile{
	public RemoteWebDriver driver;
	private String baseUrl;


  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileAE_Dom_OW")
  public void MobileAE_OW_Dom_431(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
	  driver.get(baseUrl);
	 
	  if(elementVisible(driver,By.xpath("//body/div/div/div/h3"),4)){
		  driver.findElement(By.id("arabic_select")).click();
		  
	  }
	     //to select arabic language
	//  driver.findElement(By.id("arabic_select")).click();
	 
	  mobileCom_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
	  mobileCom_Air_SRP_Oneway(driver, Carrier1, Carrier3, Carrier3);
	  //driver.get("https://www.cleartrip.ae/m/flights/results?carrier=&infants=0&airline_codes=UK&rnd_one=O&adults=1&dep_time=0&mobile=true&from=DEL&to=BOM&depart_date=17/09/2018&childs=0&class=Economy&");
	  //mobileCom_Air_ReviewItineraryPage(driver, "");
	  //safeClick(driver, By.xpath("//li[@class='item'][1]"));
	  String totalitinerary=mobileCom_Air_ReviewItineraryPageTotal(driver,"");
	  System.out.println("totalitinerary="+totalitinerary);
	  mobileCom_Air_TravelerPage(driver);
	  WebElement element1 = (new WebDriverWait(driver, 50))
			    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='makePayment']/p[1]")));
	  String pgdetails=driver.findElement(By.xpath("//*[@class='makePayment']/p[1]")).getText();
	  String paymenttotal=driver.findElement(By.xpath("//*[@id='totalAmont']")).getText();
	  int ititotal=Integer.parseInt(totalitinerary.split(" ")[1]);
	  int pgfees=Integer.parseInt(pgdetails.split(" ")[1]);
	  int paymentTotal=Integer.parseInt(paymenttotal.split(" ")[1]);
	  System.out.println("pgfees="+pgfees);
	  System.out.println("paymnettotal="+paymentTotal);
	  assertTrue((ititotal+pgfees)==paymentTotal);
	  
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
	  
  
	  