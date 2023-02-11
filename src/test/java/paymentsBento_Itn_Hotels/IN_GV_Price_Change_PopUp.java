package test.java.paymentsBento_Itn_Hotels;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IN_GV_Price_Change_PopUp extends PaymentsBento_Itn_Hotels_Common {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
	}

	@Test
	public void Hotel_IN_PriceChange_GV_PopUp() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(hotelDetailsUrl("IN", hotelName_DetailsPage));
		String[] GV = getGVSCLP(10);
		driver.manage().addCookie(hotelLogin);
		hotelsDetailsPage(driver, "", "");
		hotelsItnPage(driver, "PartialGV_SCLP_PopUp", GV[0], GV[1], "");
		hotelsPaymentPage(driver,"GVPriceChange","","Hotels",GV[0], GV[1], "GV Price Change Validation + NB : ");
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
