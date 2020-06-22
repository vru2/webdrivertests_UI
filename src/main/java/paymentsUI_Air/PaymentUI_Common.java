// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import paymentsAPI.API_PaymentCommon1;


public class PaymentUI_Common extends API_PaymentCommon1{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;

protected String qaurl = "https://qa2.cleartrip.com";
	

	public String fetchPaymentURL(Response resp){
		String payurl="";
		JsonPath jsonPathEvaluator = resp.jsonPath();
		payurl = jsonPathEvaluator.getString("payment_url");
		return payurl;
	}
	
	public String getPayUI(String PayType, String PayType1) throws Exception {
		resp = payUIget(PayType,"");
		Url = qaurl+ fetchPaymentURL(resp);
		Reporter.log("Payment URL : " +Url);
		return Url;
	}
	
	public void payUI_Select_PaymentType(RemoteWebDriver driver, String PayType) throws Exception {
		if(!elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 10)) {
			Reporter.log("PayUI Page is not displayed");
			String UI_error = getText(driver, By.xpath("//h1"));
			Reporter.log(UI_error);			
			Assert.assertTrue(false);
		}
		switch (PayType) {
		case "CC":
			PayType = "Credit Card";
			break;
		case "DC":
			PayType = "Debit Card";
			break;
		case "NB":
			PayType = "Net Banking";
			break;
		case "TW":
			PayType = "Wallets";
			break;
		case "UPI":
			PayType = "UPI";
			break;
		case "ADCB":
			PayType = "";
			break;
		default:
			break;
		}		
		safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs"), PayType);	
	}
	
	public void payUI_Enter_PaymentDetails(RemoteWebDriver driver, String PayType, String BankName) throws Exception {
		switch (PayType) {
		case "CC":
			payUI_Select_CC(driver, BankName);
			break;
		case "DC":
			
			break;
		case "NB":
			payUI_Select_NB(driver, BankName);
			break;
		case "TW":
			break;
		case "UPI":
			break;
		case "ADCB":
			break;
		default:
			break;
		}		
	}
	
	
	public void payUI_Select_CC(RemoteWebDriver driver, String BankName) throws Exception {		
			elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 5);
			switch (BankName) {
				case "MASTER":
				Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
				break;	
				case "AMEX":
				Enter_CC_Details(driver, platform.value("AmexCard_Number"), platform.value("AmexCard_Month_New"), platform.value("AmexCard_Year"), platform.value("AmexCard_CVV"));
				break;
			}			
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
			Reporter.log("Make Payment button is Clicked");
			payUI_BankPage(driver, BankName);			
	}
	
	public void Enter_CC_Details(RemoteWebDriver driver, String CCNumber, String CCExpMonth, String CCExpYear, String CVV) throws Exception {
		Reporter.log("Card Details +\n"+ CCNumber +"\n " + CCExpMonth  +" " + CCExpYear +" " + CVV);
		safeType(driver, getObjectPayment("PaymentPage_CreditCard_Number"), CCNumber);
		safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"));
		safeSelect(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"), CCExpMonth);
		safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"));
		safeSelect(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"), CCExpYear);
		safeType(driver, getObjectPayment("PaymentPage_CreditCard_Name"), "test");
		safeType(driver, getObjectPayment("PaymentPage_CreditCard_CVV"), CVV);
	}
	
	public void payUI_BankPage(RemoteWebDriver driver, String BankName) throws Exception {	
		elementNotVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 10);		
		if(BankName.equalsIgnoreCase("MASTER")) {
			if (textPresent(driver, "AXIS SIMULATOR", 10)) {
				Reporter.log("PayU OTP page is displayed");
				smartType(driver, By.id("password"), "123456");
				smartClick(driver, By.id("submitBtn"));	
			}
			else if (!textPresent(driver,"Payment successful", 1)){
				Reporter.log("Payment is not sucessfull");
				Assert.assertTrue(false);
		}
		}else if(BankName.equalsIgnoreCase("AMEX")) {
			textPresent(driver, "ACS Emulator", 10);
			Reporter.log("Amex Auth page is displayed");
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"), 20);
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"));
		}else if(BankName.equalsIgnoreCase("Citibank")) {
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), 30);
			Reporter.log("CitiBank Auth page is displayed");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
		}else if(BankName.equalsIgnoreCase("Hdfc Bank")) {
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), 30);
			Reporter.log("HDFCBank Auth page is displayed");
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), "test");
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Password"), "test");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn"));
			Thread.sleep(5000);
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_IntermitentText"), 5);
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn2"));
		}else if(BankName.equalsIgnoreCase("ICICI Bank")) {
			textPresent(driver, "Welcome to Razorpay Bank", 5);
			Reporter.log("RazorPay Auth page is displayed");
			elementVisible(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Logo"), 10);
			safeClick(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Submit"));	
		}
	}
		
	
	public void payUI_Select_NB(RemoteWebDriver driver, String BankName) throws Exception {		
			elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 5);
			safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), BankName);
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
			payUI_BankPage(driver, BankName);
	}	
	
	public void payUI_Mock_ConfirmationPage(RemoteWebDriver driver, String PayUrl) throws InterruptedException {
		for (int i = 0; i <=10; i++) {
			String returnUrl  = getURL(driver);
			if(returnUrl.contains("paymentservice/return")) {
				Reporter.log("Refreshing PayUI page to check the Payment Status");
				driver.get(PayUrl);	
				textPresent_Log(driver, "Payment successful", 10);
				textPresent_Log(driver, "view your booking details and Trip ID", 5);
				break;
			}
			else if(i==1) {
				driver.get(PayUrl);	
				textPresent_Log(driver, "Payment successful", 10);
				break;
			}
			else if(i==10){
				Reporter.log("Payment is not sucessful");
				Assert.assertTrue(false);
			}
			Thread.sleep(1000);
			}
	}
	
	
	
}
