package paymentsUI_Air;

import org.junit.Assert;
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
		elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 10);
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
	
	public void payUI_Enter_PaymentDetails(RemoteWebDriver driver, String PayType, String PaySubType) throws Exception {
		switch (PayType) {
		case "CC":
			break;
		case "DC":
			
			break;
		case "NB":
			payUI_Enter_NB_Details(driver, PayType, PaySubType);
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
	
	public void payUI_Enter_NB_Details(RemoteWebDriver driver, String PayType, String PaySubType) throws Exception {		
			elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 5);
			safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), PaySubType);
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
			if(PaySubType.equalsIgnoreCase("Citibank")) {
				elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), 30);
				safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
			}else if(PaySubType.equalsIgnoreCase("Hdfc Bank")) {
				elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), 30);
				safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), "test");
				safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Password"), "test");
				safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn"));
				Thread.sleep(5000);
				elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_IntermitentText"), 5);
				safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn2"));
			}else if(PaySubType.equalsIgnoreCase("ICICI Bank")) {
				textPresent(driver, "Welcome to Razorpay Bank", 5);
				elementVisible(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Logo"), 10);
				safeClick(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Submit"));	
			}

	}	
	
	public void payUI_Mock_ConfirmationPage(RemoteWebDriver driver, String PayUrl) throws InterruptedException {
		for (int i = 0; i <=10; i++) {

			String returnUrl  = getURL(driver);

			if(returnUrl.contains("paymentservice/return")) {
				
				driver.get(PayUrl);	
				textPresent_Log(driver, "Payment successful", 10);
				textPresent_Log(driver, "view your booking details and Trip ID", 5);
				break;
			}
			else if(i==10){
				Reporter.log("Payment is not sucessful");
				Assert.assertTrue(false);
			}
			Thread.sleep(1000);
			System.out.println("i "+i);
		}
	}
	
	
	
}
