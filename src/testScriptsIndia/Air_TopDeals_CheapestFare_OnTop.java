// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Apr, 2017
// Author - Prashanth S
// Copyright © 2017 cleartrip Travel. All right reserved.
package testScriptsIndia;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

	public class Air_TopDeals_CheapestFare_OnTop extends AirCommonMethod{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test
  public void test_Air_TopDeals_CheapestFare_OnTop_36448() throws Exception {
	  
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   elementVisible(driver, By.linkText("Flight Deals"), 10);
	   safeClick(driver, By.linkText("Flight Deals"));
	   switchFromTab2toTab1(driver);
	  elementVisible(driver, getObject("TopDeal_Price"), 10);
	  Thread.sleep(10000);
	  List<WebElement> priceList = driver.findElements(getObject("TopDeal_Price"));
	  ArrayList<Integer> al = new ArrayList<>();
	  for(WebElement  WebEle : priceList ){
	 String elementText = WebEle.getText();
	 elementText = elementText.replace("Rs. ", "");
	 if(elementText.contains(",")){
	 elementText =   elementText.replace(",", "");
	 }
	 int elementTextInt = Integer.parseInt(elementText);
	 al.add(elementTextInt);
	 }
	  int lowest=10000;
	  for(Integer  integ : al ){
	 int intgers =  integ.intValue();
	 
	 if(lowest > intgers){

	       lowest = intgers;
	      }
	  } 
	  
		  Reporter.log("Lowest Price in 3 months is: "+lowest,true);		
		 }
	      

		
	  	   
  
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
  }
  
  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		//afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}