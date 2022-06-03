// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.domains.PaymentNodeJS;

	public class GV_Creation2 extends PaymentNodeJS{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test 
  public void paymentNodeJS_Acty_B2C_CCAE() throws Exception {
       driver.manage().deleteAllCookies(); 
	   driver.get("http://www.google.com");
	   elementPresent(driver, By.name("q"), 10);
	   for (int i = 0; i < 10; i++) {
		   driver.findElement(By.name("q")).sendKeys("5123"+"456"+"789");
		   Thread.sleep(4000);
	   }
	   }
  

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
  }
  
  @AfterMethod (alwaysRun = true)
  public void afterMethod(ITestResult _result) throws Exception {
	 afterMethod(driver, _result);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}