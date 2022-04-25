package paymentsBento_Itn;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Bento_IN_Coupon_validations extends PaymentsBento_Itn_Common {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}

	@Test
	public void bento_EMI() throws Exception {
	
		

		driver.manage().deleteAllCookies();
		driver.navigate().to("https://qa2.cleartrip.com/pay/air/V1VUdUVARxNEMDIyUQ==?lang=en");
		textPresent_Log(driver, "Pay to complete your booking", 20);
		payUI_Select_PaymentType(driver, "NB");	
		safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
		String Coupon_Value = "No Coupon";
		for(int i=3; i<=14; i++) {
			i = i+1;
			String PriceBreakup_Xpath = "//div["+i+"]/p";
			String CouponText = getText(driver, By.xpath(PriceBreakup_Xpath));
			if(CouponText.contains("Coupon code")) {
				String CouponPrice_Xpath = "//div["+i+"]/p[2]";
				Coupon_Value = getText(driver, By.xpath(CouponPrice_Xpath));
				break;
			}
			}
		Reporter.log("Selected ICIC Bank");
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow")); 
		String InvalidCoupon_Msg = getText(driver, By.xpath("//div[3]/div/p"));   

		String Total_Price = getText(driver, By.xpath("//p[2]/span"));   
		

		String ButtonAnyway_Text = getText(driver, By.xpath("//form/button"));   
		String Coupon_Value_Popup = "";  
		 
	
		if (InvalidCoupon_Msg.length() > 4) 
		{
			Coupon_Value_Popup = InvalidCoupon_Msg.substring(InvalidCoupon_Msg.length() - 4);
		} 
		else
		{
			Coupon_Value_Popup = InvalidCoupon_Msg;
		}
		Coupon_Value_Popup= Coupon_Value_Popup.replace("₹", "").replace(".", "");

		ButtonAnyway_Text= ButtonAnyway_Text.replace("Book anyway at ", "").replace(",", "").replace("₹", "");
		InvalidCoupon_Msg= InvalidCoupon_Msg.replace(".", "");
		Total_Price= Total_Price.replace("₹", "").replace(",", "");
		Coupon_Value=Coupon_Value.replace("₹", "").replace("- ", "");
		
		int Coupon_Value_Breakup = Integer.parseInt(Coupon_Value);
		int Price_Without_Coupon = Integer.parseInt(ButtonAnyway_Text);
		int Coupon_Value_Popup_Int = Integer.parseInt(Coupon_Value_Popup);
		int Total_Price_Int = Integer.parseInt(Total_Price);

		Reporter.log( "Total_Price_Int "+Total_Price_Int);
		Reporter.log( "Coupon_Value_Breakup "+Coupon_Value_Breakup);
		Reporter.log( "Price_Without_Coupon "+Price_Without_Coupon);
		Reporter.log("Coupon_Value_Popup "+Coupon_Value_Popup_Int);
		
		if(Coupon_Value_Breakup!=Coupon_Value_Popup_Int) {
			Reporter.log("Coupon in Price Brekup "+Coupon_Value_Breakup+" & Coupon "+Coupon_Value_Popup_Int+" in Invalid Coupon Popup are not matching");
			Assert.assertTrue(false);
		}
		
		if(Price_Without_Coupon!=(Coupon_Value_Breakup+Total_Price_Int)) {
			Reporter.log("Price Without Coupon  "+Price_Without_Coupon+"is not equal to Sum of Coupon Value in Breakup "+Coupon_Value_Breakup+" Total amount after coupon Discount "+Total_Price_Int);
			Assert.assertTrue(false);
		}
	}
		
	
	}

