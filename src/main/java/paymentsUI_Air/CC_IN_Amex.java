// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CC_IN_Amex extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void CC_Amex_Pay() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		Thread.sleep(5000);
		//Robot robot = new Robot();

/*		robot.keyPress(KeyEvent.VK_F12);
		robot.keyRelease(KeyEvent.VK_F12);


*/		
		Actions action = new Actions(driver);
	    action.sendKeys(Keys.F12);
		
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_F12);
	    robot.keyRelease(KeyEvent.VK_F12);
		
		driver.findElement(getObjectPayment("PaymentPage_CreditCard_Number")).sendKeys(Keys.F12);
		payUI_Select_PaymentType(driver, "CC");
		payUI_Enter_PaymentDetails(driver, "CC", "AMEX","");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
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
