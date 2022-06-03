// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI_Air;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class PWA_Coupon extends PaymentUI_Common{
	public RemoteWebDriver driver;
	String Coupon_Invalid_Text = null;
	
	@Test (priority=0)
	public void PWA_CouponInvalid() throws Exception {
		String PayUrl = getPayUI("AirCoupon", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));
		textPresent_Log(driver, "Coupon code (DOMOW)", 5);
		Thread.sleep(1000);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));
		Thread.sleep(2000);
		payUI_Select_PaymentType_PWA(driver, "NET BANKING");
		textPresent(driver, "Choose another bank", 5);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_Select_NB"));
		Thread.sleep(2000);
		safeType(driver, getObjectPayment("PWA_NETBANKING_Page_NB_TextBox"), "Axis Bank");
		Thread.sleep(3000);
		safeClick(driver, getObjectPayment("PWA_NETBANKING_Page_NB_AJAX"));
		Thread.sleep(2000);
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn2"));
		Thread.sleep(6000);		
        if(elementVisible(driver,By.xpath("//div[3]/div/div/div/div"),5))
        {
		Coupon_Invalid_Text = getText(driver, getObjectPayment("PWA_PaymentPage_Invalid_Coupon_Text"));
		System.out.println(Coupon_Invalid_Text);
		Reporter.log(Coupon_Invalid_Text);
		if(!Coupon_Invalid_Text.contains("Coupon code DOMOW is not applicable on the chosen payment method. Change payment mode/card details or book without coupon at ₹ ")) {
			Reporter.log("Coupon code DOMOW is not applicable on the chosen payment method. Change payment mode/card details or book without coupon at ₹ "+ Coupon_Invalid_Text);
			Assert.assertTrue(false);
		}
		if(!Coupon_Invalid_Text.contains("800")) {
			Reporter.log("800 rs is not displayed");
			Assert.assertTrue(false);
		}
       }
        else
        {
		Coupon_Invalid_Text = getText(driver, getObjectPayment("PWA_PaymentPage_Invalid_Coupon_Text1"));
		System.out.println(Coupon_Invalid_Text);
		Reporter.log(Coupon_Invalid_Text);
		if(!Coupon_Invalid_Text.contains("Coupon code DOMOW is not applicable on the selected payment mode. Please select another payment mode to get an instant discount of ")) {
			Reporter.log("Coupon code DOMOW is not applicable on the selected payment mode. Please select another payment mode to get an instant discount of "+ Coupon_Invalid_Text);
			Assert.assertTrue(false);
		}
		if(!Coupon_Invalid_Text.contains("800")) {
			Reporter.log("800 rs is not displayed");
			Assert.assertTrue(false);
		}
       }
        
		Thread.sleep(2000);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_Coupon_Change_Payment_Button"));
		Thread.sleep(2000);
		}
		
		@Test(priority=1)
		public void PWA_CouponValid() throws Exception {
		Thread.sleep(2000);
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn2"));
		Thread.sleep(6000);
		 if(elementVisible(driver,By.xpath("//div[2]/div/div/div/div"),10))
	        {
			Coupon_Invalid_Text = getText(driver, getObjectPayment("PWA_PaymentPage_Invalid_Coupon_Text1"));
			System.out.println(Coupon_Invalid_Text);
			Reporter.log(Coupon_Invalid_Text);
			if(!Coupon_Invalid_Text.contains("Coupon code DOMOW is not applicable on the selected payment mode. Please select another payment mode to get an instant discount of ₹ ")) {
				Reporter.log("Coupon code DOMOW is not applicable on the selected payment mode. Please select another payment mode to get an instant discount of ₹ "+ Coupon_Invalid_Text);
				Assert.assertTrue(false);
			}
			if(!Coupon_Invalid_Text.contains("800")) {
				Reporter.log("800 rs is not displayed");
				Assert.assertTrue(false);
			}
	       }
		 else
	        {
			Coupon_Invalid_Text = getText(driver, getObjectPayment("PWA_PaymentPage_Invalid_Coupon_Text"));
			System.out.println(Coupon_Invalid_Text);
			Reporter.log(Coupon_Invalid_Text);
			if(!Coupon_Invalid_Text.contains("Coupon code DOMOW is not applicable on the chosen payment method. Change payment mode/card details or book without coupon at ₹ ")) {
				Reporter.log("Coupon code DOMOW is not applicable on the chosen payment method. Change payment mode/card details or book without coupon at ₹ "+ Coupon_Invalid_Text);
				Assert.assertTrue(false);
			}
			if(!Coupon_Invalid_Text.contains("800")) {
				Reporter.log("800 rs is not displayed");
				Assert.assertTrue(false);
			}
	       }
		safeClick(driver, getObjectPayment("PWA_PaymentPage_Book_Without_Coupon_Button"));		
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getMobileDriver(driver);
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
