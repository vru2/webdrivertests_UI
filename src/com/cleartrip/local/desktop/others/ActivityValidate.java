package com.cleartrip.local.desktop.others;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Locals;

public class ActivityValidate extends Locals {

	public static RemoteWebDriver driver;
	public  List<WebElement> list =new ArrayList<WebElement>();
	
	@Test (groups= "Smoke Tests",priority=0)
	public void Homepage() throws Exception 
				{
					driver.manage().deleteAllCookies();
					driver.get("https://www.cleartrip.com/activities/bangalore");
		           	driver.findElement(By.xpath("//*[@id='localMainNav']/nav/ul/li[2]/a")).click();
		           
		            JavascriptExecutor jse = (JavascriptExecutor) driver;
				    jse.executeScript("window.scrollBy(0,300)", "");
				}
	@Test(dependsOnMethods={"Homepage"},description="Getting count of activities")			
	public void getActivities()
			{
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					list=driver.findElements(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']//ul/li"));
					System.out.println("Count of Activity in Bangalore"+list.size());
			}
	
		@Test(dependsOnMethods={"getActivities"},description="Clicking on the Available Activities")
		public void ClickActivity() throws InterruptedException
			{
					int ToatalActivity=list.size();
				    collectionclick(driver,ToatalActivity);
			}
	
		@Test(dependsOnMethods={"getActivities","ClickActivity","Homepage"},description="Validationg any Failed cases")
		public void failedcollection()
			{
				if(FailedActivity.size()>0)
				    {
				    	Assert.fail();				    	
				    }
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
