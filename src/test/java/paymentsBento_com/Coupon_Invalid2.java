// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Coupon_Invalid2 extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);				
	}

	@Test
	public void CouponInvalid() throws Exception {
		driver.get("https://qa2.cleartrip.com/flights/results?adults=1&childs=0&infants=0&depart_date=03/04/2021&return_date=&intl=n&from=BLR&to=HYD&class=Economy&airline=&carrier=&sd=1600754739618&page=&sellingCountry=IN&ssfi=&flexi_search=&ssfc=&origin=BLR%20-%20Bangalore,%20IN%20&destination=HYD%20-%20Hyderabad,%20IN");
		elementVisible(driver, By.xpath("//div[4]/button"), 30);
		driver.get("https://qa2.cleartrip.com/flights/results?adults=1&childs=0&infants=0&depart_date=03/04/2021&return_date=&intl=n&from=BLR&to=HYD&class=Economy&airline=&carrier=&sd=1600754739618&page=&sellingCountry=IN&ssfi=&flexi_search=&ssfc=&origin=BLR%20-%20Bangalore,%20IN%20&destination=HYD%20-%20Hyderabad,%20IN");
		
		Thread.sleep(5000);
		get_Bento_Url(driver, "AirCoupon", "");
		bento_Select_PaymentType(driver, "NB");
		safeClick(driver, By.xpath("//div[3]/div[2]/div/label/div/span"));
		textPresent_Log(driver, "Coupon code (DOMOW)", 5);
		safeClick(driver, getObjectPayment("Bento_Pay_Button"));
		Thread.sleep(5000);
		textPresent_Log(driver, "Coupon not applicable", 5);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Coupon_Popup_Close_Btn"), "",	1);
		safeClick(driver, getObjectPayment("Bento_Pay_Coupon_Popup_Close_Btn"));
		/*elementPresent_log(driver, getObjectPayment("Bento_Pay_Coupon_Popup_Book_Anyway_Btn"), "Book anyway Btn", 5);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Coupon_Popup_Changepayment_Btn"), "Change payment", 5);
		textPresent_Log(driver, "Coupon code DOMOW is not applicable on the selected", 1);
		textPresent_Log(driver, "payment mode. Please select another payment mode to get", 1);
		textPresent_Log(driver, "an instant discount of ₹ 150.", 1);	*/	
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