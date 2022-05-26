package test.java.paymentsUI;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.domains.PaymentNodeJS;

public class Payment_NodeJS_CC_Default extends PaymentNodeJS {
		public RemoteWebDriver driver;
		private String baseUrl;

		@Test
		public void paymentNodeJS_CC() throws Exception {
			driver.manage().deleteAllCookies();
			driver.get(baseUrl);
			paymentNodeJS_Select_Payment(driver, "CC", "","MASTER");
			paymentNodeJS_Make_Payment(driver, "CC","MASTER");
			String TripRef = paymentNodeJS_ConfirmationPage(driver, "CC", "", "CC ");
			/*
			 * String[] Columns = { "STATUS", "PAYMENT_TYPE","DESCRIPTION","CURRENCY" };
			 * String[] Values = { "S", "CC", "Payment successful", "INR" };
			 * dataBase_Validation("T8719845509", "payments",Columns, Values, "");
			 * dataBase_Validation("T8719845509", "payments","PAYMENT_TYPE","CC", "");
			 * dataBase_Validation("T8719845509", "payments","PAYMENT_TYPE","CC", "");
			 * getCardGateWayAccess("");
			 */

		}

		@BeforeClass
		public void setUp() throws Exception {
			driver = (RemoteWebDriver) getDriver(driver);
			baseUrl = getPaymentNodeUrl;
		}

		@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}

		@AfterClass
		public void tearDown() throws Exception {
			browserClose(driver);
		}

	}