package test.java.  accountsUI;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Accounts_UI_Signup_Mobile_Oly extends Accounts_UI_Common{

	@BeforeClass
	public void startSelenium() throws Exception {/*
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");*/
	}
	@Test
	public void signup_mobile_oly() throws Exception {
		//driver.manage().deleteAllCookies();
		//driver.navigate().to(baseUrl);
		RedisHandler(driver,"SIGNIN","+919986696785");
		//Signup("SIGNIN","Signup_mobile_only");
	}
	@AfterClass
	public void closeSelenium() throws Exception {
		browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver,_result);
	}

}
