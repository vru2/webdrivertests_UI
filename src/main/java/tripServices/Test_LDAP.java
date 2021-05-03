package tripServices;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import paymentsBento_com.PaymentUI_Common_Bento;

public class Test_LDAP extends PaymentUI_Common_Bento {
	
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}
	
	@Test
	public void ldap() throws InterruptedException
	{
		driver.manage().deleteAllCookies();
		driver.get("https://varalakshmi.venkateshaiah@cleartrip.com:Varu@2902@qa2.cleartrip.com/");
		Thread.sleep(10000);
		/*
		 * textPresent(driver, "Sign in", 20);
		 * driver.switchTo().alert().sendKeys("varalakshmi.venkateshaiah@cleartrip.com")
		 * ; Thread.sleep(20000); driver.switchTo().alert().accept();
		 */
	}
	 @AfterClass
		public void closeSelenium() throws Exception {
		 browserClose(driver);
		}

		@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}

}
