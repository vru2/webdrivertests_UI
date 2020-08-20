// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_Validation extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test (priority=1)
	public void PWA_Validate_pay_error() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		payUI_Select_PaymentType_PWA(driver, "DEBIT/CREDIT CARDS");
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Number"),  platform.value("MasterCard_Number"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please enter valid card details");	
		refreshPage(driver);
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Number"),  platform.value("MasterCard_Number"));
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Expiry"), platform.value("MasterCard_EXP_PWA"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please enter valid card details");
		refreshPage(driver);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "NET BANKING");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn2"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please select your bank");	
		refreshPage(driver);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "WALLETS");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please select any wallet");	
		refreshPage(driver);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "UPI");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please select any UPI payment method");	
	}

	@Test (priority=2)
	public void PWA_Validate_PriceValidation() throws Exception {
		
	}
	
	@Test (priority=3)
	public void PWA_Validate_ItineraryValidation() throws Exception {
		
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
