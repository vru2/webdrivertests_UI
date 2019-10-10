package com.cleartrip.local.visa;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Visa;

public class IndiaVisaCoupon extends Visa {
	
	@Test
	public void visa() throws Exception
	{
		
/*		driver.manage().deleteAllCookies();
		driver.get("https://qa2.cleartrip.ae");
		SigIn(driver);
		VisaToUAE(driver,"//nav[@class='hasProductIcons']//li[4]");*/
		driver.get("https://qa2.cleartrip.ae/visa/dubai");
		
		VisaTravelDetailsIndia(driver,"//div[@class='col-16']/div/div[3]/div/div");
		
		IndiabookTravellers( driver);
	    Applycoupon(driver);
	    visapayment(driver);
		Visadocumentupload( driver);
		Visadetails(driver);
	}
	
	
	@BeforeClass
	public void setUp() throws Exception {
			driver = (RemoteWebDriver) getDriver(driver);
			}

   @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }
}
