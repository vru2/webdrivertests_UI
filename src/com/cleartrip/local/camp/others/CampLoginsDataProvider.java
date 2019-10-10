package com.cleartrip.local.camp.others;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class CampLoginsDataProvider extends CampActivities{
	@Test(dataProvider="Logins")
	public void campLogins(String un, String pwd) throws Exception
	{
	driver.get("https://qa2.cleartrip.com/camp/accounts/sign_in");
	waitForPageLoading(2);
	driver.findElement(By.name("username")).sendKeys(un);
	driver.findElement(By.name("password")).sendKeys(pwd);
	driver.findElement(By.xpath("//button[text()='Log in']")).click();
	campActivities_SignOut(driver);
	
	}
	
	@DataProvider(name="Logins")
	public Object[][] loginRoles(){
		return new Object[][]
				{
			{"sa1@cleartrip.com","cleartrip"},
			{"sam1@cleartrip.com","cleartrip"},
			{"scm2@cleartrip.com","demo1234"},
			{"mm1@cleartrip.com","demo1234"},
			{"scm2@cleartrip.com","demo1234"},
			{"activitiescleartrip4@gmail.com","demo1234"},
			{"activitiescleartrip1@gmail.com","demo1234"},
			{"ctprodtest1@gmail.com","demo1234"}
			
			
		};
	}
				
	}


