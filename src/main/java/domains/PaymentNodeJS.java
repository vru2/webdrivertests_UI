package domains;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.w3c.dom.Document;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import paymentsAPI.API_PaymentCommon1;
import paymentsUI.PaymentUI_CommonUtilities;

public class PaymentNodeJS extends API_PaymentCommon1{
	


	public String getPaymentNodeUrl = "http://172.17.15.176:9080";

	public String getPaymentSummaryUrl = "http://172.17.14.217:9080/ct-logger/paymentDashboard";

	protected String qaurl = "https://qa2.cleartrip.com";

	//String urlgetPay = "http://172.17.26.11:8070";

	String endPointgetPay = "/paymentservice/api/getPaymentURL";

	


	public void paymentNodeJS_HomePage(RemoteWebDriver driver, String payServer, String testServer) throws Exception {
		elementVisible(driver, getObjectPayment("HomePage_Header"), 10);
		elementPresent(driver, getObjectPayment("HomePage_Confirm_Btn"), 5);
		if (common.value("PaymentServer").equalsIgnoreCase("MYSQL")) {
			Thread.sleep(500);
			safeType(driver, getObjectPayment("HomePage_Host_ServerIp_Txt"), "172.17.28.21:8358/paymentservice");
			Reporter.log("Mysql payment 172.17.26.11:8358/payment"); 
		} else if (common.value("PaymentServer").equalsIgnoreCase("DEV")) {
			Thread.sleep(500);
			safeType(driver, getObjectPayment("HomePage_Host_ServerIp_Txt"), "172.17.26.11:8250:/paymentservice");
			Reporter.log("DEV payment 172.17.26.11:8250:9001/paymentservice"); 
		}		
		else {
			Thread.sleep(500);
			safeType(driver, getObjectPayment("HomePage_Host_ServerIp_Txt"), "172.17.26.11:8070/paymentservice");
			Reporter.log("Oracle payment 172.17.26.11:8070/paymentservice"); 
		}
		safeClick(driver, getObjectPayment("HomePage_Confirm_Btn"));
	}

	public void paymentNodeJS_Select_Payment(RemoteWebDriver driver, String payType, String payType1, String cardType)
			throws Exception {
		if (cardType.equalsIgnoreCase("MASTER")||cardType.equalsIgnoreCase("")) {
			cardType = "MASTER";
		}else if (cardType.equalsIgnoreCase("AMEX")) {
			cardType = "AMEX";
		}
		String Country = null;
		if (!payType1.equals("")) {
			Country = payType1.substring(0, 2);
		}
		Reporter.log(common.value("PaymentServer") + " Payment service is called");
		if (!payType1.equalsIgnoreCase("NOHOMEPAGE")) {
			if (textPresent(driver, "Payment Server(Host:Port", 1)) {
				elementVisible(driver, getObjectPayment("HomePage_Header"), 10);
				elementPresent(driver, getObjectPayment("HomePage_Confirm_Btn"), 5);
				if (common.value("PaymentServer").equalsIgnoreCase("MYSQL")) {
					Thread.sleep(500);
					smartType(driver, getObjectPayment("HomePage_Host_ServerIp_Txt"), "172.17.28.21:8358/paymentservice");
				} else {
					Thread.sleep(500);
					//smartType(driver, getObjectPayment("HomePage_Host_ServerIp_Txt"), "172.17.8.218:9001/paymentservice");
				}
				smartClick(driver, getObjectPayment("HomePage_Confirm_Btn"));
			}
		}
		elementVisible(driver, getObjectPayment("SelectPayment_Header"), 5);
		elementPresent(driver, getObjectPayment("SelectPayment_Header"), 5);
		textPresent(driver, "Select payment option", 1);
		if (payType.equalsIgnoreCase("CC")) {
			select_Card(driver);
			// ---------------------------------------------------------------------//
			paymentNodeJS_EnterCard_Details(driver, cardType);
		} else if (payType.equalsIgnoreCase("SC")) {
			select_Card(driver);
		} else if (payType.equalsIgnoreCase("DC")) {
			select_Card(driver);
			safeClick(driver, getObjectPayment("SelectPayment_CC_DC_Dropdown"));
			safeClick(driver, getObjectPayment("SelectPayment_Type_DC"));
			paymentNodeJS_EnterCard_Details(driver, cardType);
		} else if (payType.equalsIgnoreCase("CURRENCY")) {
			safeClick(driver, getObjectPayment("SelectPayment_Currency_DropDown"));
			safeClickList(driver, getObjectPayment("SelectPayment_Currency_Values"), payType1);
			// mouseHoverClick(driver, getObjectPayment("SelectPayment_Country_DropDown"));
			scrollToPageTop(driver);
			safeClick(driver, getObjectPayment("SelectPayment_Country_DropDown"));
			safeClickList(driver, getObjectPayment("SelectPayment_Country_Values"), Country);
			scrollToElement(driver, getObjectPayment("SelectPayment_CTWallet_Amount"));
			select_Card(driver);
			paymentNodeJS_EnterCard_Details(driver, cardType);
		}

		else if (payType.equalsIgnoreCase("GVSC")) {
			add_GV(driver, "");
			select_Card(driver);
		} else if (payType.equalsIgnoreCase("GV")) {
			add_GV(driver, "");
		} else if (payType.equalsIgnoreCase("MULTIGV")) {
			add_GV(driver, "");
			add_GV(driver, "");
			add_GV(driver, "");
		} else if (payType.equalsIgnoreCase("GVCC")) {
			add_GV(driver, "");
			select_Card(driver);
			paymentNodeJS_EnterCard_Details(driver, cardType);
		} else if (payType.equalsIgnoreCase("GVCCWALL")) {
			add_GV(driver, "");
			add_Wallet(driver, "");
			select_Card(driver);
			paymentNodeJS_EnterCard_Details(driver, cardType);
		} else if (payType.equalsIgnoreCase("GVWALL")) {

			add_GV(driver, "");
			safeType(driver, getObjectPayment("SelectPayment_Amount_Txt"), "0");
			add_Wallet(driver, "");
		} else if (payType.equalsIgnoreCase("CTCC")) {
			add_Wallet(driver, "");
			select_Card(driver);
			paymentNodeJS_EnterCard_Details(driver, cardType);
		} else if (payType.equalsIgnoreCase("CTFULL")) {
			safeType(driver, getObjectPayment("SelectPayment_Amount_Txt"), "0");
			add_Wallet(driver, "");
		} else if (payType.equalsIgnoreCase("CTSC")) {
			add_Wallet(driver, "");
			select_Card(driver);
		} else if (payType.equalsIgnoreCase("NB")) {
			safeClickList(driver, getObjectPayment("SelectPayment_Option"), "Net Banking");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Dropdown"));
			safeClickList(driver, getObjectPayment("SelectPayment_NB_Bank"), payType1);
			if(payType1.equalsIgnoreCase("Citibank")) {
				safeClick(driver, getObjectPayment("SelectPayment_NB_Bank_Select_Arrow"));
				//safeSelectByIndex(driver, getObjectPayment("SelectPayment_NB_Bank_Select"), 6);
				safeClick(driver, By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ICICI Bank'])[1]/following::span[2]"));
			}
			else if(payType1.equalsIgnoreCase("HDFC Bank")) {
				safeClick(driver, getObjectPayment("SelectPayment_NB_Bank_Select_Arrow"));
				//safeSelectByIndex(driver, getObjectPayment("SelectPayment_NB_Bank_Select"), 6);

				safeClickList(driver, getObjectPayment("SelectPayment_NB_Bank"), payType1);
			}
		}		
		else if (payType.equalsIgnoreCase("PHONEPE")) {
			safeType(driver, getObjectPayment("SelectPayment_Amount_Txt"), "1");
			safeClickList(driver, getObjectPayment("SelectPayment_Option"), "UPI");
		} else if (payType.equalsIgnoreCase("PHONEPECTWALL")) {
			add_Wallet(driver, "");
			safeType(driver, getObjectPayment("SelectPayment_Amount_Txt"), "1");
			safeClickList(driver, getObjectPayment("SelectPayment_Option"), "UPI");
		} else if (payType.equalsIgnoreCase("RAZORPAY")) {
			safeType(driver, getObjectPayment("SelectPayment_Amount_Txt"), "1");
			safeClickList(driver, getObjectPayment("SelectPayment_Option"), "UPI");
		} else if (payType.equalsIgnoreCase("RAZORPAYCTWALL")) {
			add_Wallet(driver, "");
			safeType(driver, getObjectPayment("SelectPayment_Amount_Txt"), "1");
			safeClickList(driver, getObjectPayment("SelectPayment_Option"), "UPI");
		}

		else if (payType.equalsIgnoreCase("TPW")) {
			safeType(driver, By.id("amount"), "1");
			safeClickList(driver, getObjectPayment("SelectPayment_Option"), "Wallets");
			safeClickList(driver, getObjectPayment("SelectPayment_Wallet_Types"), payType1);

		}	else if (payType.equalsIgnoreCase("CTP")) {
			//safeClick(driver, getObjectPayment("SelectPayment_Type_CtPay"));
		}

	}

	public void paymentNodeJS_Select_Source(RemoteWebDriver driver, String source, String source1) throws Exception {
		elementVisible(driver, getObjectPayment("SelectPayment_Header"), 5);
		elementPresent(driver, getObjectPayment("SelectPayment_Header"), 5);
		if (source.equalsIgnoreCase("MOBILE") || source.equalsIgnoreCase("AGENCY") || source.equalsIgnoreCase("CORP")) {
			scrollToPageTop(driver);
			safeClick(driver, getObjectPayment("SelectPayment_Source_DropDown"));
			safeClickList(driver, getObjectPayment("SelectPayment_Source_Values"), source);
			scrollToPageTop(driver);
		}
	}

	public void paymentNodeJS_Select_Product(RemoteWebDriver driver, String product, String product1) throws Exception {
		elementVisible(driver, getObjectPayment("SelectPayment_Header"), 5);
		elementPresent(driver, getObjectPayment("SelectPayment_Header"), 5);
		if (product.equalsIgnoreCase("HOTEL") || product.equalsIgnoreCase("ACTIVITY")
				|| product.equalsIgnoreCase("INTL-AIR")) {
			scrollToPageTop(driver);
			safeClick(driver, getObjectPayment("SelectPayment_Product_DropDown"));
			safeClickList(driver, getObjectPayment("SelectPayment_Product_Values"), product);
			scrollToPageTop(driver);
		}
	}

	public void paymentNodeJS_EnterCard_Details(RemoteWebDriver driver, String cardType) throws Exception {
		if(cardType.equalsIgnoreCase("MASTER")){
			Reporter.log("Master card selected");
		}
		if (cardType.equalsIgnoreCase("AMEX")) {
			Reporter.log("Card No : " + platform.value("AmexCard_Number"));
			Reporter.log("PG Credentials : " + platform.value("AmexCard_PGCred"));
			safeType_NonClear(driver, getObjectPayment("EnterPayment_Card_Number"), platform.value("AmexCard_Number"));
			safeType(driver, getObjectPayment("EnterPayment_Card_Exp_Month"), platform.value("AmexCard_Month"));
			safeType(driver, getObjectPayment("EnterPayment_Card_Exp_Year"), platform.value("AmexCard_Year"));
			safeType(driver, getObjectPayment("EnterPayment_Card_CVV"), platform.value("AmexCard_CVV"));
			safeClick(driver, getObjectPayment("EnterPayment_Card_PGCredential_Drop_Dwn"));
			safeClickList(driver, getObjectPayment("EnterPayment_Card_PGCredential_Text"),
					platform.value("AmexCard_PGCred"));
		} else if (cardType.equalsIgnoreCase("DC")) {
			Reporter.log("Card No : " + platform.value("DebitCard_Number"));
			Reporter.log("PG Credentials : " + platform.value("DebitCard_PGCred"));
			safeType_NonClear(driver, getObjectPayment("EnterPayment_Card_Number"), platform.value("DebitCard_Number"));
			safeType(driver, getObjectPayment("EnterPayment_Card_Exp_Month"), platform.value("DebitCard_Month"));
			safeType(driver, getObjectPayment("EnterPayment_Card_Exp_Year"), platform.value("DebitCard_Year"));
			safeType(driver, getObjectPayment("EnterPayment_Card_CVV"), platform.value("DebitCard_CVV"));
			//safeClick(driver, getObjectPayment("EnterPayment_Card_PGCredential_Drop_Dwn"));
			//safeClickList(driver, getObjectPayment("EnterPayment_Card_PGCredential_Text"),	platform.value("DebitCard_PGCred"));
		} else if (cardType.equalsIgnoreCase("CHECKOUT")) {
			Reporter.log("Card No : " + platform.value("SACheckOut_Number"));
			Reporter.log("PG Credentials : " + platform.value("SACheckOut_PGCred"));
			safeType_NonClear(driver, getObjectPayment("EnterPayment_Card_Number"),
					platform.value("SACheckOut_Number"));
			safeType(driver, getObjectPayment("EnterPayment_Card_Exp_Month"), platform.value("SACheckOut_Month"));
			safeType(driver, getObjectPayment("EnterPayment_Card_Exp_Year"), platform.value("SACheckOut_Year"));
			safeType(driver, getObjectPayment("EnterPayment_Card_CVV"), platform.value("SACheckOut_CVV"));
			safeClick(driver, getObjectPayment("EnterPayment_Card_PGCredential_Drop_Dwn"));
			safeClickList(driver, getObjectPayment("EnterPayment_Card_PGCredential_Text"),
					platform.value("SACheckOut_PGCred"));
		}else if (cardType.equalsIgnoreCase("NOON")) {
			Reporter.log("Card No : " + platform.value("Noon_Number"));
			Reporter.log("PG Credentials : " + platform.value("Noon_PGCred"));
			safeType_NonClear(driver, getObjectPayment("EnterPayment_Card_Number"),
					platform.value("Noon_Number"));
			safeType(driver, getObjectPayment("EnterPayment_Card_Exp_Month"), platform.value("Noon_Month"));
			safeType(driver, getObjectPayment("EnterPayment_Card_Exp_Year"), platform.value("Noon_Year"));
			safeType(driver, getObjectPayment("EnterPayment_Card_CVV"), platform.value("Noon_CVV"));
			safeClick(driver, getObjectPayment("EnterPayment_Card_PGCredential_Drop_Dwn"));
			safeClickList(driver, getObjectPayment("EnterPayment_Card_PGCredential_Text"),
					platform.value("Noon_PGCred"));
		}  

		else if (cardType.equalsIgnoreCase("S2S")) {
			Reporter.log("Card No : " + platform.value("S2S_Number"));
			Reporter.log("PG Credentials : " + platform.value("S2S_PGCred"));
			safeType_NonClear(driver, getObjectPayment("EnterPayment_Card_Number"), platform.value("S2S_Number"));
			safeType(driver, getObjectPayment("EnterPayment_Card_Exp_Month"), platform.value("S2S_Month"));
			safeType(driver, getObjectPayment("EnterPayment_Card_Exp_Year"), platform.value("S2S_Year"));
			safeType(driver, getObjectPayment("EnterPayment_Card_CVV"), platform.value("S2S_CVV"));
			safeClick(driver, getObjectPayment("EnterPayment_Card_PGCredential_Drop_Dwn"));
			safeClickList(driver, getObjectPayment("EnterPayment_Card_PGCredential_Text"),
					platform.value("S2S_PGCred"));
		} else if (cardType.equalsIgnoreCase("PAYFORT")) {
			// Reporter.log("Card No : "+platform.value("S2S_Number"));
			Reporter.log("PG Credentials : " + platform.value("SAFort_PGCred"));
			safeClick(driver, getObjectPayment("EnterPayment_Card_PGCredential_Drop_Dwn"));
			safeClickList(driver, getObjectPayment("EnterPayment_Card_PGCredential_Text"),
					platform.value("SAFort_PGCred"));
		}
	}

	public String paymentNodeJS_Make_Payment(RemoteWebDriver driver, String payType, String bankType) throws Exception {
		String TripID = null;
		if (payType.equalsIgnoreCase("CC") || payType.equalsIgnoreCase("DC")) {
			Thread.sleep(2000);
			if (!elementVisible(driver, getObjectPayment("MakePayment_Pay_Btn_CC"), 2)) {
				select_Card(driver);
			}
			safeClick(driver, getObjectPayment("MakePayment_Pay_Btn_CC"));
			if (textPresent(driver, "Validation Failed", 2)) {
				Reporter.log("Validation Failed text is displayed");
				assertTrue(false);
			}
			//======================================//
			paymentNodeJS_BankPage(driver, bankType);
		} else if (payType.equalsIgnoreCase("SC")) {
			Thread.sleep(2000);
			if (!elementVisible(driver, getObjectPayment("MakePayment_Pay_Btn_SC"), 5)) {
				select_Card(driver);
			}
			safeClick(driver, getObjectPayment("MakePayment_Pay_Btn_SC"));
		} else if (payType.equalsIgnoreCase("CCCheckout")) {
			safeClick(driver, getObjectPayment("MakePayment_Pay_Btn_CC"));
			elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), 30);
			elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Logo"), 20);
			Thread.sleep(2000);
			safeType(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), "Checkout1!");
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Continue_Btn"));
		} else if (payType.equalsIgnoreCase("CCPayFortSA")) {
			safeClick(driver, getObjectPayment("MakePayment_Pay_Btn_CC"));
			elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), 30);
			elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Logo"), 20);
			Thread.sleep(2000);
			safeType(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), "Checkout1!");
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Continue_Btn"));
		} else if (payType.equalsIgnoreCase("NB")) {
			safeClick(driver, getObjectPayment("MakePayment_Pay_Btn_NB"));
			if (bankType.equalsIgnoreCase("Bank of India")) {
				if(textPresent(driver, "Payment failed", 1)) {
					Reporter.log("Payment failed");
					Assert.assertTrue(false);
				}
				elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_BOI_Return_Lnk"), 30);
				safeClick(driver, getObjectPayment("MakePayment_NB_Bank_BOI_Return_Lnk"));
			} else if (bankType.equalsIgnoreCase("Citibank")) {
				if(textPresent(driver, "Payment failed", 1)) {
					Reporter.log("Payment failed");
					Assert.assertTrue(false);
				}
				elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), 30);
				safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
			}else if (bankType.equalsIgnoreCase("TECHPROCESS")) {
				if(textPresent(driver, "Payment failed", 1)) {
					Reporter.log("Payment failed");
					Assert.assertTrue(false);
				}
				elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), 30);
				safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), "test");
				safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Password"), "test");
				safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn"));
				Thread.sleep(5000);
				elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_IntermitentText"), 5);
				safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn2"));
			}

		} else if (payType.equalsIgnoreCase("GV")) {
			safeClick(driver, getObjectPayment("SelectPayment_GV_Full_Btn"));
		} else if (payType.equalsIgnoreCase("MULTIGV")) {
			safeClick(driver, getObjectPayment("SelectPayment_GV_Full_Btn"));
		} else if (payType.equalsIgnoreCase("GVCC")) {
			Thread.sleep(2000);
			if (!elementVisible(driver, getObjectPayment("MakePayment_Pay_Btn_CC"), 5)) {
				select_Card(driver);
			}
		} else if (payType.equalsIgnoreCase("GVWALL")) {
			safeClick(driver, getObjectPayment("SelectPayment_GV_Wallet_Btn"));
		} else if (payType.equalsIgnoreCase("PHONEPE")) {
			safeClick(driver, getObjectPayment("MakePayment_Pay_Btn_Phonepe"));
			/*elementVisible(driver, getObjectPayment("MakePayment_PhonePe_Page_Pay_Btn"), 10);
			safeClick(driver, getObjectPayment("MakePayment_PhonePe_Page_Pay_Btn"));*/
			elementPresent_log(driver, By.id("mobileNumber"), "", 30);
		} else if (payType.equalsIgnoreCase("RAZORPAY")) {
			safeClick(driver, getObjectPayment("MakePayment_Pay_Btn_RazorPay"));
			elementVisible(driver, getObjectPayment("MakePayment_RazorPay_Page_Logo"), 10);
			textPresent(driver, "ENTER YOUR UPI ID", 1);
			safeType(driver, getObjectPayment("MakePayment_RazorPay_Page_ID_Txt_Box"), platform.value("RazorPay_Cred"));
			safeClick(driver, getObjectPayment("MakePayment_RazorPay_Page_Pay_Btn"));
		}else if (payType.equalsIgnoreCase("CTP")) {
			elementVisible(driver, getObjectPayment("SelectPayment_Type_CtPay"), 10);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500)", "");
			safeClick(driver, getObjectPayment("SelectPayment_Type_CtPay"));			
			elementPresent(driver, getObjectPayment("MakePayment_CtPay_CT_Logo"));
			if(!textPresent(driver, "Enter your credit card details", 20)) {
				Reporter.log("Enter your credit card details text is not displayed");
				Assert.assertTrue(false);
			}
			logURL(driver);

			safeClick(driver, getObjectPayment("MakePayment_CtPay_CC_Tab"));
			safeType(driver, getObjectPayment("MakePayment_CtPay_CC_Number"), "5123456789012346");
			safeSelect(driver, getObjectPayment("MakePayment_CtPay_CC_ExpMonth"), "05");
			safeSelect(driver, getObjectPayment("MakePayment_CtPay_CC_ExpYear"), "2020");			
			safeType(driver, getObjectPayment("MakePayment_CtPay_CC_Name"), "Test");
			safeType(driver, getObjectPayment("MakePayment_CtPay_CVV"), "123");
			safeClick(driver, getObjectPayment("MakePayment_CtPay_Pay_Button"));

			/*			safeClick(driver, getObjectPayment("MakePayment_CtPay_CC_Tab"));
			safeType(driver, getObjectPayment("MakePayment_CtPay_CC_Number"), "340000000000009");
			safeSelect(driver, getObjectPayment("MakePayment_CtPay_CC_ExpMonth"), "05");
			safeSelect(driver, getObjectPayment("MakePayment_CtPay_CC_ExpYear"), "2021");			
			safeType(driver, getObjectPayment("MakePayment_CtPay_CC_Name"), "Test");
			safeType(driver, getObjectPayment("MakePayment_CtPay_CVV"), "1234");
			safeType(driver, By.id("billAddress"), "test");
			safeType(driver, By.id("billCity"), "test");
			safeType(driver, By.id("billState"), "test");
			safeType(driver, By.id("billPin"), "560085");
			safeAutocomplete(driver, By.id("billCountry"), By.id("ui-id-2"), "India");
			safeClick(driver, getObjectPayment("MakePayment_CtPay_Pay_Button"));
			paymentNodeJS_BankPage(driver, "AMEX");*/

		}

		else if (payType.equalsIgnoreCase("CTFULL")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-1000)");
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("SelectPayment_CTWallet_Btn"));
			if (textPresent(driver, "Validation Failed", 2)) {
				Reporter.log("Validation Failed text is displayed");
				assertTrue(false);
			} else if (!textPresent(driver, "Payment successful", 20)) {
				Reporter.log("Clicking pay button for second time");
				safeClick(driver, getObjectPayment("SelectPayment_CTWallet_Btn"));
			}
		} else if (payType.equalsIgnoreCase("TPW")) {
			safeClick(driver, getObjectPayment("MakePayment_Pay_Btn_Wallet"));
			if (bankType.equalsIgnoreCase("AmazonPay")) {

			} else if (bankType.equalsIgnoreCase("Mobikwik")) {
				elementVisible(driver, By.cssSelector("font.flR > img"), 5, "Mobikwik App");
				if (!textPresent(driver, "Enter Mobile Number", 20)) {
					Reporter.log("Enter Mobile Number text is not displayed");
					Assert.assertTrue(false);
				}
			} else if (bankType.equalsIgnoreCase("HDFC PayZapp")) {

			} else if (bankType.equalsIgnoreCase("Paytm")) {
				Thread.sleep(5000);
				waitForElementVisibility(driver, By.xpath("//section/button"),20);
				/*elementVisible(driver, getObjectPayment("TW_PayTM_Proceed_Btn"), 20);				
				elementPresent(driver, getObjectPayment("TW_PayTM_PhoneNo_Txt_Box"));*/
				elementPresent_log(driver, By.xpath("//section/button"), "Paytm Pay Button", 5);
				//textPresent_Log(driver, "CLEARTRIP PRIVATE LIMITED Order", 10);
			} else if (bankType.equalsIgnoreCase("Masterpass")) {

			} else if (bankType.equalsIgnoreCase("PayU")) {
				if (!textPresent(driver, "PayUMoney. All rights reserved", 20)) {
					Reporter.log("PayUMoney. All rights reserved PayU text is not displayed");
					Assert.assertTrue(false);
				}

			} else if (bankType.equalsIgnoreCase("Ola_Money")) {
				if (!textPresent(driver, "Enter phone number registered with Ola Money", 20)) {
					Reporter.log("Login to OlaMoney Wallet text is not displayed");
					Assert.assertTrue(false);
				}
			} else if (bankType.equalsIgnoreCase("jio_money")) {
				if (!textPresent(driver, "Please enter your mobile number to Login", 20)) {
					Reporter.log("Please enter your mobile number to Login text is not displayed");
					Assert.assertTrue(false);
				}

			} else if (bankType.equalsIgnoreCase("freecharge")) {
				if (!elementVisible(driver, getObjectPayment("TW_FreeCharge_Logo"), 20)) {
					Reporter.log("FreeCharge Logo is not displayed");
					Assert.assertTrue(false);
				}
			} else if (bankType.equalsIgnoreCase("airtel_money")) {
				if (!elementVisible(driver, getObjectPayment("TW_AirtelMoney_Logo"), 20)) {
					Reporter.log("Airtel Logo is not displayed");
					Assert.assertTrue(false);
				}
				if (!textPresent(driver, "Create your Airtel Money wallet and enjoy various benefits", 1)) {
					Reporter.log("Create your Airtel Money wallet and enjoy various benefits text is not displayed");
					Assert.assertTrue(false);
				}
			} else if (bankType.equalsIgnoreCase("Paypal")) {
				if (!elementVisible(driver, getObjectPayment("PaymentPage_TW_Paypal_EmailID"), 20)) {
					Reporter.log("Paypal Logo is not displayed");
					Assert.assertTrue(false);
				}

				if(textPresent(driver, "Need help with your password", 5)) {
					safeClick(driver, By.name("Return to PayPal login"));
				}
				safeType(driver, getObjectPayment("PaymentPage_TW_Paypal_EmailID"), platform.value("Paypal_EmailID"));

				elementVisible(driver, By.id("btnNext"), 20);
				safeClick(driver, By.id("btnNext"));
				Thread.sleep(5000);
				safeType(driver, getObjectPayment("PaymentPage_TW_Paypal_Password"), platform.value("Paypal_Password"));
				safeClick(driver, getObjectPayment("PaymentPage_TW_Paypal_Login"));
				elementVisible(driver, getObjectPayment("PaymentPage_TW_Paypal_CC_CVV"), 20);
				textPresent(driver, "Cancel and return to Cleartrip", 5);
				safeClick(driver, getObjectPayment("PaymentPage_TW_Paypal_Pay_Now_Btn"));
				Thread.sleep(5000);
				safeType(driver, getObjectPayment("PaymentPage_TW_Paypal_CC_CVV"), platform.value("Paypal_CVV"));
				//safeClick(driver, getObjectPayment("PaymentPage_TW_Paypal_CVV_Paynow_Btn"));

				elementVisible(driver, getObjectPayment("PaymentPage_TW_Paypal_3D_Page_Header"), 20);

				safeType(driver, getObjectPayment("PaymentPage_TW_Paypal_3D_Page_Password"), platform.value("Paypal_CVV"));
				safeClick(driver, getObjectPayment("PaymentPage_TW_Paypal_3D_Page_Submit"));
				Thread.sleep(5000);
			}

		}
		return TripID;
	}

	public void add_GV(RemoteWebDriver driver, String amount) throws Exception {
		//String GV[] = paymentNodeJS_GV_Creation(driver, "50");
		String GV[] = getGV(1);
		/*System.out.println("GV no " +GV[0]);
		System.out.println("GV Pin " +GV[1]);*/
		Reporter.log(GV[0]+" : "+GV[1]);
		safeType(driver, getObjectPayment("SelectPayment_GV_Card_No"), GV[0]);
		safeType(driver, getObjectPayment("SelectPayment_GV_Card_Pin"), GV[1]);
		safeClick(driver, getObjectPayment("SelectPayment_GV_Add_Btn"));
		Thread.sleep(1000);
	}

	public void add_Wallet(RemoteWebDriver driver, String amount) throws Exception {
		safeType(driver, getObjectPayment("SelectPayment_CTWallet_Amount"), "100");
	}

	public void select_Card(RemoteWebDriver driver) throws Exception {
		safeClickList(driver, getObjectPayment("SelectPayment_Option"), "Card");

	}

	public void paymentNodeJS_BankPage(RemoteWebDriver driver, String bankType) throws Exception {

		if (bankType.equalsIgnoreCase("AMEX")) {
			textPresent(driver, "ACS Emulator", 10);
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"), 20);
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"));
		} else if (bankType.equalsIgnoreCase("CHECKOUT")) {
			if (platform.value("CheckOut_3D").equals("true")) {
				elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), 30);
				elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Logo"), 20);
				Thread.sleep(2000);
				safeType(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), "Checkout1!");
				Thread.sleep(2000);
				safeClick(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Continue_Btn"));
			}
		} else if (bankType.equalsIgnoreCase("S2S")) {
			elementVisible(driver, getObjectPayment("MakePayment_PgCred_S2SNew_Submit_Btn"), 30);
			Thread.sleep(2000);
			safeType(driver, getObjectPayment("MakePayment_PgCred_S2SNew_Page_Txt_Box"), "secone1");
			safeClick(driver, getObjectPayment("MakePayment_PgCred_S2SNew_Submit_Btn"));
		} else if (bankType.equalsIgnoreCase("S2SOLD")) {
			elementVisible(driver, getObjectPayment("MakePayment_PgCred_S2S_Page_Txt"), 30);
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("MakePayment_PgCred_S2S_Submit_Btn"));
		}
		else if (bankType.equalsIgnoreCase("Noon")) {
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Submit_Btn"), 30);
			textPresent(driver, "Please submit your Verified by Visa password", 1);
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Password_TxtBx"), "Noon pay redirection ", 1);
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Password_TxtBx"),"1234");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Submit_Btn"));

			//safeClick(driver, "");
		}		
	}

	public String paymentNodeJS_ConfirmationPage(RemoteWebDriver driver, String payType, String Confirm_Msg, String LoggerMsg) throws Exception {
		for (int i = 0; i < 5; i++) {		
			if (textPresent(driver, "AXIS SIMULATOR", 1)) {
				smartType(driver, By.id("password"), "123456");
				smartClick(driver, By.id("submitBtn"));	
			}
			else if (textPresent(driver,"Payment successful", 1)){
				break;
			}
			else if (textPresent(driver, "This site can’t be reached", 1)) {
				Reporter.log("This site can’t be reached msg is displayed");
				logURL(driver);
				Assert.assertTrue(false);
			} else if (textPresent(driver, "Sorry, our system is acting up.", 1)) {
				Reporter.log("Sorry, our system is acting up. msg is displayed");
				logURL(driver);
				Assert.assertTrue(false);
			} else if (textPresent(driver, "Payment failed", 1)) {
				Reporter.log("Payment failed");
				logURL(driver);
				if(!payType.equalsIgnoreCase("NB")) {
					Assert.assertTrue(false);
				}
				else if(textPresent(driver, "Validation Failed", 1)) {

					Reporter.log("Validation Failed");
					Assert.assertTrue(false);
				}
			}
		}
		// logURL(driver);
		if (!payType.equalsIgnoreCase("CTP")){
			elementPresent_Time(driver, getObjectPayment("PaymentConfirmation_Msg"), 1);

			//	elementPresent_log(driver, getObjectPayment("PaymentConfirmation_Msg"), "CTpay Confirm", 5);
		}

		if (payType.equalsIgnoreCase("CTP")){
			//textPresent(driver, "Your payment is successful", 10);
			textPresent_Log(driver, "Your payment is successful", 10);
			if(!elementPresent(driver, getObjectPayment("PaymentConfirmation_CTPay_ID"))) {
				Reporter.log("CTpay Confirmation ID is not displayed");
				Assert.assertTrue(false);
			}
			String ctpaymessage = getText(driver, getObjectPayment("PaymentConfirmation_CTPay_ID")).split(":")[1].substring(0, 11).trim();
			//			String[] ctpayid = ctpaymessage.split(":");
			//			String ctpayid1 = ctpayid[1].substring(0, 11).trim();
			Reporter.log("CTPay ID " +ctpaymessage);
		}
		else if (payType.equalsIgnoreCase("CC") || payType.equalsIgnoreCase("DC") || payType.equalsIgnoreCase("PHONEPE") || payType.equalsIgnoreCase("NBCiti")|| payType.equalsIgnoreCase("NBtechprocess")) {
			String confirmationMsg = getText(driver, getObjectPayment("PaymentConfirmation_Msg"));			
			if (!confirmationMsg.equals("Payment successful! :)")) {
				assertTrue(false);
			}
		} else if (payType.equalsIgnoreCase("Paypal")) {

			/*String confirmationMsg = getText(driver, getObjectPayment("PaymentConfirmation_Msg"));			
			if (!confirmationMsg.equals("Payment failed... :(")) {
				assertTrue(false);
			}*/
		}	String PaymentID = null;


		if (!payType.equalsIgnoreCase("CTP")){
			String confirmationMsg = getText(driver, getObjectPayment("PaymentConfirmation_Msg"));			
			PaymentID = paymentNodeJS_Get_PaymentID(driver);
			logger.info(LoggerMsg + " : " + PaymentID);
			Reporter.log(LoggerMsg + " : " + PaymentID);
			Reporter.log("ConfirmationMsg = " + confirmationMsg);
		}
		else if (payType.equalsIgnoreCase("NB")) {
			String confirmationMsg = getText(driver, getObjectPayment("PaymentConfirmation_Msg"));
			if (!confirmationMsg.equals("Payment successful! :)")) {
				assertTrue(false);
			}
		}
		return PaymentID;
	}

	public void paymentNodeJS_Summary(RemoteWebDriver driver, String tripID, String paymentType) throws Exception {
		if (tripID != null) {
			String URL = getPaymentSummaryUrl + tripID;
			Reporter.log("URL : "+URL);
			driver.get(URL);			
			elementVisible(driver, getObjectPayment("Summary_PayRequest_Tab"), 5);
			if (paymentType.equalsIgnoreCase("CC")) {
				String TripRef = "appRef1" + "\" : \"" + tripID;
				// System.out.println(TripRef);
				if (!textPresent(driver, TripRef, 5)) {
					Reporter.log("");
					assertTrue(false);
				}
			}

		}

	}

	public String paymentNodeJS_Get_PaymentID(RemoteWebDriver driver) throws Exception {
		String Confirm_Url = logURL(driver);
		String tripRef[] = Confirm_Url.split("tripRef=");
		String paymentID = tripRef[1];
		return paymentID;
	}

	public String[] paymentNodeJS_Enter_GV(RemoteWebDriver driver)
			throws ClientProtocolException, IOException, URISyntaxException, JSONException {
		String GV[] = paymentNodeJS_GV_Creation(driver, "50");

		return GV;
	}

	public String[] paymentNodeJS_GV_Creation(RemoteWebDriver driver, String Amount)
			throws ClientProtocolException, IOException, URISyntaxException, JSONException {
		String[] GV = null;
		DefaultHttpClient clientSearch = null;
		String GV_Details = "INR|" + Amount + "|kiran.kumar@cleartrip.com|1|cleartrip";
		clientSearch = new DefaultHttpClient();
		String hash = calculateHash("SHA-256", GV_Details);
		String postString = "{ \"currency\":\"INR\", \"amount\":" + Amount
				+ ", \"userEmail\":\"kiran.kumar@cleartrip.com\", \"paymentId\":1 }";
		HttpPost itinenaryCall = new HttpPost(new URI("http://172.17.13.109:9080/payment/gv/create"));
		StringEntity params = new StringEntity(postString);
		itinenaryCall.setEntity(params);
		itinenaryCall.setHeader("Content-Type", "application/json");
		itinenaryCall.setHeader("checksum", hash);
		HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
		HttpEntity entityIti = itinenaryResponse.getEntity();
		Document docIti = null;
		BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
		String GVNumber = "";
		StringBuffer GVNumber1 = new StringBuffer();
		while ((GVNumber = br12.readLine()) != null) {
			GVNumber1.append(GVNumber);
		}
		JSONObject GVPin = new JSONObject(GVNumber1.toString());
		String GVString = GVPin.toString();
		String[] GVString1 = GVString.split("\"gvPin\":\"");
		String[] GVString_Pin = GVString.split("\",\"balance");
		String[] GVString_No = GVString.split("gvNumber\":\"");
		GVString_No[1] = GVString_No[1].replaceAll("\"}", "");
		GVString_Pin[0] = GVString_Pin[0].replaceAll("gvPin", "");
		GVString_Pin[0] = GVString_Pin[0].replaceAll("\\{", "").replaceAll(":", "").replaceAll("\"\"\"", "");
		GV = new String[2];
		GV[0] = GVString_No[1];
		GV[1] = GVString_Pin[0];
		Reporter.log("GV No : " + GVString_No[1]);
		Reporter.log("GV Pin : " + GVString_Pin[0]);
		System.out.println("GV No : " + GVString_No[1]);
		System.out.println("GV Pin : " + GVString_Pin[0]);
		return GV;
	}

	public String calculateHash(String hashType, String input) {
		byte[] hashseq = input.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance(hashType);
			algorithm.reset();
			algorithm.update(hashseq);
			byte[] messageDigest = algorithm.digest();

			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append("0");
				}
				hexString.append(hex);
			}

		} catch (NoSuchAlgorithmException e) {
			// logger.error(e);
		}

		return hexString.toString();
	}

	public String getPaymentID(String TripID) throws SQLException, ClassNotFoundException {
		String Voucher = null;
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "Product";
			String password = "product";

			ArrayList<String> TripIDName = new ArrayList<String>();
			ArrayList<String> TripIDValue = new ArrayList<String>();

			String query = "select ID from tm.payments where app_ref1='Q2973188238'";

			Reporter.log("oracle driver is called..");

			Connection myCon = DriverManager.getConnection(url, user, password);
			Reporter.log("Conection to QA2 DB is established..");

			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				// ResultSet myRes = myCon.createStatement().executeUpdate(query);
				Reporter.log("Executing Query..");
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int noOfColumns = result.getColumnCount();

					for (int x = 1; x <= noOfColumns; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						TripIDName.add(colName);
						TripIDValue.add(colValue);
						System.out.println("TripIDValue : " + TripIDValue);
						System.out.println("TripIDName : " + TripIDName);
						if (TripIDName.get(x - 1).contains("ID")) {
							Reporter.log(TripIDName.get(x - 1) + " : " + TripIDValue.get(x - 1));
							Reporter.log(TripIDValue.get(x - 1));
							Voucher = TripIDValue.get(x - 1);
							System.out.println("Voucher " + Voucher);
							break;
						}
						Reporter.log("Voucher " + Voucher);
					}

				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return Voucher;

	}

	public String dataBase_Validation1(String paymentID, String table, String column, String value, String value2)
			throws SQLException, ClassNotFoundException {
		String Voucher = null;
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "Product";
			String password = "product";

			ArrayList<String> TripIDName = new ArrayList<String>();
			ArrayList<String> TripIDValue = new ArrayList<String>();

			String query = "select " + column + " from tm." + table + " where APP_REF1='" + paymentID + "'";
			/*			System.out.println(query);
			Reporter.log("oracle driver is called..");*/

			Connection myCon = DriverManager.getConnection(url, user, password);
			Reporter.log("Conection to QA2 DB is established..");

			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				// ResultSet myRes = myCon.createStatement().executeUpdate(query);
				Reporter.log("Executing Query..");
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int noOfColumns = result.getColumnCount();

					for (int x = 1; x <= noOfColumns; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						TripIDName.add(colName);
						TripIDValue.add(colValue);


					}
					if (!TripIDValue.get(0).equals(value)) {
						Assert.assertTrue(false);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return Voucher;

	}

	public void dataBase_Validation(String paymentID, String table, String[] column, String[] value, String value2)
			throws SQLException, ClassNotFoundException {

		String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
		String user = "Product";
		String password = "product";

		ArrayList<String> TripIDName = new ArrayList<String>();
		ArrayList<String> TripIDValue = new ArrayList<String>();
		Connection myCon = DriverManager.getConnection(url, user, password);
		for (int i = 0; i < column.length; i++) {
			String query = "select " + column[i] + " from tm." + table + " where APP_REF1='" + paymentID + "'";
			/*Reporter.log("oracle driver is called..");
			Reporter.log("Conection to QA2 DB is established..");
			 */
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				//Reporter.log("Executing Query..");
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int noOfColumns = result.getColumnCount();
					for (int x = 1; x <= noOfColumns; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						TripIDName.add(colName);
						TripIDValue.add(colValue);
					}
					if (!TripIDValue.get(i).equals(value[i])) {
						Assert.assertTrue(false);
					}
				}
			} else
				Reporter.log("Connection not established");
		}
		myCon.close();

	}


	public ArrayList<String> db_airTripPAXName(String TripID) throws SQLException, ClassNotFoundException {
		//	String Voucher = null;

		ArrayList<String> data = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";

			ArrayList<String> Name = new ArrayList<String>();

			String query =  "select PAX_INFO.FIRST_NAME, PAX_INFO.LAST_NAME from PAX_INFO INNER JOIN Trips on PAX_INFO.trip_ID = Trips.ID where Trips.TRIP_REF='" + TripID +"'";


			Connection myCon = DriverManager.getConnection(url, user, password);
			//Reporter.log("Conection to QA2 DB is established..");

			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				//Reporter.log("Executing Query..");
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int noOfColumns = result.getColumnCount();

					for (int x = 1; x <= noOfColumns; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
						if (Name.get(x - 1).contains("FIRST_NAME")) {
							//Reporter.log(Name.get(x - 1) + " : " + data.get(x - 1));
							//String	datas = data.get(x - 1);
						}	if (Name.get(x - 1).contains("LAST_NAME")) {
							//Reporter.log(Name.get(x - 1) + " : " + data.get(x - 1));
							//	String Voucher = data.get(x - 1);
							break;
						}

					}

				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}

		return data;

	}

	public ArrayList<String> db_airTripPNR(String TripID) throws SQLException, ClassNotFoundException {
		//	String Voucher = null;

		ArrayList<String> data = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";

			ArrayList<String> Name = new ArrayList<String>();

			String query =  "select AIR_BOOKING_INFO.GDS_PNR, AIR_BOOKING_INFO.AIRLINE_PNR from AIR_BOOKING_INFO  INNER JOIN Trips on AIR_BOOKING_INFO.ID = Trips.ID where Trips.TRIP_REF='" + TripID +"'";


			Connection myCon = DriverManager.getConnection(url, user, password);
			//Reporter.log("Conection to QA2 DB is established..");

			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				//Reporter.log("Executing Query..");
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int noOfColumns = result.getColumnCount();

					for (int x = 1; x <= noOfColumns; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
						if (Name.get(x - 1).contains("GDS_PNR")) {
							//	Reporter.log(Name.get(x - 1) + " : " + data.get(x - 1));

						}	if (Name.get(x - 1).contains("AIRLINE_PNR")) {
							//Reporter.log(Name.get(x - 1) + " : " + data.get(x - 1));
							break;
						}

					}

				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}

		return data;

	}

	public ArrayList<String> db_airTripDetail(String TripID) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";
			String query =  "SELECT PX.TITLE, PX.FIRST_NAME,  PX.LAST_NAME,  t.trip_name,  ab.gds_pnr,  ab.airline_pnr,   s.operating_airline, s.flight_number,   s.departure_airport,  s.arrival_airport,  s.departure_date_time,  s.arrival_date_time FROM  PAX_INFO PX INNER JOIN TRIPS T ON PX.TRIP_ID=T.ID INNER JOIN air_booking_info ab on ab.pax_info_id=px.id INNER JOIN segments s on ab.segment_id=s.id WHERE T.trip_ref='" + TripID +"' ";
			Connection myCon = DriverManager.getConnection(url, user, password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int noOfColumns = result.getColumnCount();
					for (int x = 1; x <= noOfColumns; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}

	public ArrayList<String> db_airTripDetails(String TripID) throws SQLException, ClassNotFoundException {
		//	String Voucher = null;

		ArrayList<String> data = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";

			ArrayList<String> Name = new ArrayList<String>();

			String query =  "select TRIP_Name, START_DATE_TIME, END_DATE_TIME, AMOUNT from trips where Trip_Ref = '"+TripID+"'";


			Connection myCon = DriverManager.getConnection(url, user, password);
			//	Reporter.log("Conection to QA2 DB is established..");

			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				//	Reporter.log("Executing Query..");
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int noOfColumns = result.getColumnCount();

					for (int x = 1; x <= noOfColumns; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);

					}

				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}

		return data;

	}

	public ArrayList<String> db_airFlightDetails(String TripID) throws SQLException, ClassNotFoundException {
		//	String Voucher = null;

		ArrayList<String> data = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";

			ArrayList<String> Name = new ArrayList<String>();

			String query =  "Select OPERATING_AIRLINE, FLIGHT_NUMBER from TM.SEGMENTS sg Join tm.trips tp on tp.id= sg.TRIP_ID where tp.Trip_ref = '"+TripID+"'";


			Connection myCon = DriverManager.getConnection(url, user, password);
			Reporter.log("Conection to QA2 DB is established..");

			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				Reporter.log("Executing Query..");
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int noOfColumns = result.getColumnCount();

					for (int x = 1; x <= noOfColumns; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);

					}

				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}

		return data;

	}

	public ArrayList<String> db_trainPNRDetails(String TripID) throws SQLException, ClassNotFoundException {
		//	String Voucher = null;

		ArrayList<String> data = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";

			ArrayList<String> Name = new ArrayList<String>();

			String query =  "SELECT TBI.PNR_NUMBER FROM TRAIN_BOOKING_INFO TBI INNER JOIN TRAIN_BOOKINGS TB ON TB.ID=tbi.train_booking_id INNER JOIN TRIPS T ON T.ID=tb.trip_id WHERE t.trip_ref= '"+TripID+"'";


			Connection myCon = DriverManager.getConnection(url, user, password);
			//Reporter.log("Conection to QA2 DB is established..");

			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				//Reporter.log("Executing Query..");
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int noOfColumns = result.getColumnCount();

					for (int x = 1; x <= noOfColumns; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);

					}

				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}

		return data;

	}

	public ArrayList<String> gmailTrains(RemoteWebDriver driver, String TripID, ArrayList<String> db_Trains) throws Exception {
		ArrayList<String> email_Trains = new ArrayList<String>();
		elementPresent_log(driver, By.xpath("//div/input"), "", 5);
		safeType(driver, By.xpath("//div/input"), TripID);
		driver.findElement(By.xpath("//div/input")).sendKeys(Keys.RETURN);	    
		Thread.sleep(1000);
		if(!elementVisible(driver, By.xpath("//td[6]/div/div/div[2]/span/span"), 1)) {
			Reporter.log("Email is not present");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//td[6]/div/div/div[2]/span/span"));
		elementVisible(driver, By.xpath("//h2/span"), 5);
		if(!textPresent(driver,db_Trains.get(0), 1)) {
			Reporter.log("Name is not Present "+db_Trains.get(0));	    	
		}	  
		if(!textPresent(driver,db_Trains.get(2), 1)) {
			Reporter.log("Amount is different "+db_Trains.get(2));	    	
		}
		if(!textPresent(driver,db_Trains.get(1), 1)) {
			Reporter.log("Station is not Present "+db_Trains.get(1));	
			Assert.assertTrue(false);
		}

		if(!textPresent(driver,db_Trains.get(3), 1)) {
			Reporter.log("PNR is not Present "+db_Trains.get(3));	
			Assert.assertTrue(false);
		}

		/*
	    String E_TripHeader = getText(driver, By.xpath("//h2/span"));
	    String E_Trip = getText(driver, By.xpath("//strong/span"));
	    if(!(TripID.contains(E_TripHeader)&&TripID.contains(E_Trip))) {
	    	Reporter.log("TripID is not matching");
	    	Assert.assertTrue(false);
	    }
	    String E_Name = getText(driver, By.xpath("//tr[5]/td/table/tbody/tr/td"));
	    String E_Station = getText(driver, By.xpath("//tr[3]/td/table/tbody/tr/td"));
	    String E_Amount = getText(driver, By.xpath("//td[2]/div[2]/table/tbody/tr[4]/td/div[2]"));
	    String E_PNR = getText(driver, By.xpath("//p[4]/a/strong"));

	    String [] name1 = E_Name.split(" ");
	    E_Name = name1[1]+ " "+name1[2];
	    String[] station1  = E_Station.split(" ");
	    E_Station = station1[0]+" "+station1[2];
	    email_Trains.add(E_Name);
	    email_Trains.add(E_Station);
	    email_Trains.add(E_Amount);*/

		return email_Trains;
	}



	public ArrayList<String> gmailAirValidation(RemoteWebDriver driver, String TripID, ArrayList<String> db_airTrip) throws Exception {
		ArrayList<String> email_Trains = new ArrayList<String>();
		elementPresent_log(driver, By.xpath("//div/input"), "", 5);
		safeType(driver, By.xpath("//div/input"), TripID);
		driver.findElement(By.xpath("//div/input")).sendKeys(Keys.RETURN);	    
		Thread.sleep(1000);
		if(!elementVisible(driver, By.xpath("//td[6]/div/div/div[2]/span/span"), 1)) {
			Reporter.log("===============Email is not present===============");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//td[6]/div/div/div[2]/span/span"));
		elementVisible(driver, By.xpath("//h2/span"), 5);
		//String [] station=db_airTrip.get(3).split(" ");
		//All text Present

		if(!textPresent(driver,db_airTrip.get(0), 1)) {
			Reporter.log("===============Title is not Present "+db_airTrip.get(0));	    	
		}
		if(!textPresent(driver,db_airTrip.get(1), 1)) {
			Reporter.log("===============First name is not Present "+db_airTrip.get(1));	    	
		}
		if(!textPresent(driver,db_airTrip.get(2), 1)) {
			Reporter.log("===============Last name is not Present "+db_airTrip.get(2));	    	
		}
		if(!textPresent(driver,db_airTrip.get(3), 1)) {
			Reporter.log("===============Station details not Present "+db_airTrip.get(3));	    	
		}
		/* if(!textPresent(driver,station[2], 1)) {
	    	Reporter.log("===============Arrvial station is not Present "+db_airTrip.get(3));	    	
	    }*/

		if(!((textPresent(driver,db_airTrip.get(4), 1))||(textPresent(driver,db_airTrip.get(5),1)))) {
			Reporter.log(" ===============PNR is not Present "+db_airTrip.get(4) +" "+db_airTrip.get(5));	    	
		}
		if(!textPresent(driver,db_airTrip.get(6), 1)) {
			Reporter.log("===============Airline Name is not Present "+db_airTrip.get(6));	    	
		}
		if(!textPresent(driver,db_airTrip.get(7), 1)) {
			Reporter.log("===============Airline Number is not Present "+db_airTrip.get(7));	    	
		}
		if(!textPresent(driver,db_airTrip.get(8), 1)) {
			Reporter.log("===============Departure station is not Present "+db_airTrip.get(8));	    	
		}
		if(!textPresent(driver,db_airTrip.get(9), 1)) {
			Reporter.log("===============Arrival station is not Present "+db_airTrip.get(9));	    	
		}

		return email_Trains;
	}


	public ArrayList<String> dbTrains(String TripID) throws ClassNotFoundException, SQLException {
		ArrayList<String> db_Trains = new ArrayList<String>();

		ArrayList<String> data = db_airTripPAXName(TripID);		
		ArrayList<String> data1 = db_airTripDetails(TripID);
		ArrayList<String> data2=db_trainPNRDetails(TripID);
		//ArrayList<String> data2 = db_airFlightDetails(TripID);
		String D_Name = data.get(0)+" "+data.get(1);
		String D_Station = data1.get(0);
		String D_PNR=data2.get(0);
		/*		  String [] Station = D_Station.split(" ");
		  D_Station = Station[0]+" "+Station[2];*/
		db_Trains.add(D_Name);
		db_Trains.add(D_Station);
		db_Trains.add(data1.get(3));	
		db_Trains.add(D_PNR);
		return db_Trains;

	}

	public ArrayList<String> dbAir(String TripID) throws ClassNotFoundException, SQLException {
		ArrayList<String> db_Trains = new ArrayList<String>();

		ArrayList<String> data = db_airTripPAXName(TripID);		
		ArrayList<String> data1 = db_airTripDetails(TripID);
		ArrayList<String> data2 = db_airFlightDetails(TripID);
		String D_Name = data.get(0)+" "+data.get(1);
		String D_Station = data1.get(0);
		String [] Station = D_Station.split(" ");
		D_Station = Station[0]+" "+Station[2];

		db_Trains.add(D_Name);
		db_Trains.add(D_Station);
		db_Trains.add(data1.get(3));

		return db_Trains;

	}

	public void getCardGateWayAccess(String TripID) throws SQLException, ClassNotFoundException {
		String Voucher = null;
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "Product";
			String password = "product";

			ArrayList<String> TripIDName = new ArrayList<String>();
			ArrayList<String> TripIDValue = new ArrayList<String>();
			ArrayList<String> TripIDValue1 = new ArrayList<String>();

			String query = "select ID from TM.PAYMENT_CARDS_GATEWAY_ACCESS where PAYMENT_ID=42639304";

			//	Reporter.log("oracle driver is called..");

			Connection myCon = DriverManager.getConnection(url, user, password);
			Connection myCon1 = DriverManager.getConnection(url, user, password);
			Connection myCon2 = DriverManager.getConnection(url, user, password);
			//Reporter.log("Conection to QA2 DB is established..");

			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				//Reporter.log("Executing Query..");
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int noOfColumns = result.getColumnCount();
					int noOfRows = myRes.getRow();
					for (int x = 1; x <= noOfColumns; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						TripIDName.add(colName);
						TripIDValue.add(colValue);
						if (TripIDName.get(x - 1).contains("ID")) {
							Reporter.log(TripIDName.get(x - 1) + " : " + TripIDValue.get(x - 1));
							Reporter.log(TripIDValue.get(x - 1));
							Voucher = TripIDValue.get(x - 1);
							break;
						}
					}

				}
			}
			myCon.close();
			String TRAN_ID = null;
			String VERN_ID = null;
			if (myCon1 != null) {
				for (int i = 0; i <= 2; i++) {

					TRAN_ID = TripIDValue.get(i).toString();

					// System.out.println(i+" = "+TRAN_ID);
					String query1 = "select TRAN_TYPE from TM.PAYMENT_CARDS_GATEWAY_ACCESS where ID=" + TRAN_ID;
					System.out.println(query1);
					ResultSet myRes1 = myCon1.createStatement().executeQuery(query1);
					Reporter.log("Executing Query..");
					while (myRes1.next() == true) {
						ResultSetMetaData result = myRes1.getMetaData();
						String colValue = myRes1.getString(1);
						TripIDValue1.add(colValue);
						if (TripIDValue1.get(i).contains("VERN")) {
							VERN_ID = TRAN_ID;
							//	System.out.println(" Vern : " + TRAN_ID);
							Reporter.log("TripIDValue1 : " + TripIDValue1.get(i));
							//	System.out.println("TripIDValue1 : " + TripIDValue1.get(i));
							break;
						}
					}
				}
			}
			if (myCon2 != null) {
				String TripID1 = "Q1810030752";
				// String query2 = "select GATEWAY_RESPONSE_3 from
				// TM.PAYMENT_CARDS_GATEWAY_ACCESS where ID="+VERN_ID;
				String query2 = "select GATEWAY_RESPONSE_3 from TM.PAYMENT_CARDS_GATEWAY_ACCESS where PAYMENT_ID=(select ID from tm.payments where app_ref1='"
						+ TripID1 + "') AND TRAN_TYPE='VERN'";
				//System.out.println(query2);
				ResultSet myRes1 = myCon1.createStatement().executeQuery(query2);
				//Reporter.log("Executing Query..");
				while (myRes1.next() == true) {
					ResultSetMetaData result = myRes1.getMetaData();
					String colValue = myRes1.getString(1);
					String Gateway = colValue;
					//	System.out.println(" Vern121212 : " + Gateway);
					if (!Gateway.equals("null")) {
						VERN_ID = TRAN_ID;
						//	System.out.println(" Vern121212 : " + Gateway);
						break;

					}
				}
			}
			myCon1.close();
			myCon2.close();
		}

	}

	public String[] GV_Creation(RemoteWebDriver driver, String Amount) throws ClientProtocolException, IOException, URISyntaxException, JSONException{
		String[] GV = null;
		DefaultHttpClient clientSearch = null;
		String GV_Details ="INR|"+Amount+"|kiran.kumar@cleartrip.com|1|cleartrip";
		clientSearch = new DefaultHttpClient();
		String hash=calculateHash("SHA-256",GV_Details);				
		String postString="{ \"currency\":\"INR\", \"amount\":"+Amount+", \"userEmail\":\"kiran.kumar@cleartrip.com\", \"paymentId\":1 }";
		HttpPost itinenaryCall = new HttpPost(new URI("http://172.17.13.109:9080/payment/gv/create"));
		//HttpPost itinenaryCall = new HttpPost(new URI("http://10.10.21.245:9080/payment/gv/create"));
		StringEntity params = new StringEntity(postString);
		itinenaryCall.setEntity(params);
		itinenaryCall.setHeader("Content-Type","application/json");
		itinenaryCall.setHeader("checksum",hash);
		HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
		HttpEntity entityIti = itinenaryResponse.getEntity();
		Document docIti =null;
		BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
		String GVNumber ="";
		StringBuffer GVNumber1 =new StringBuffer();
		System.out.println(GVNumber1);
		while((GVNumber=br12.readLine())!=null){
			GVNumber1.append(GVNumber);
		}
		System.out.println(GVNumber1);
		JSONObject GVPin = new JSONObject(GVNumber1.toString());
		String GVString = GVPin.toString();
		String [] GVString1 = GVString.split("\"gvPin\":\""); 
		String [] GVString_Pin = GVString.split("\",\"balance");
		String [] GVString_No = GVString.split("gvNumber\":\"");
		GVString_No[1] = GVString_No[1].replaceAll("\"}", ""); 
		GVString_Pin[0] = GVString_Pin[0].replaceAll("gvPin", "");		
		GVString_Pin[0] = GVString_Pin[0].replaceAll("\\{", "").replaceAll(":", "").replaceAll("\"\"\"", "");	
		GV = new String[2];
		GV[0] =GVString_No[1];
		GV[1] =GVString_Pin[0];
		Reporter.log("GV No : "+GVString_No[1]);
		Reporter.log("GV Pin : "+GVString_Pin[0]);
		System.out.println(GVString_No[1] + ":"+GVString_Pin[0]);
		//System.out.println("GV Pin : "+GVString_Pin[0]);
		return GV;

	}


	public void gmailLogin (RemoteWebDriver driver, String email, String password) throws Exception {
		textPresent(driver, "Not your computer?",  10);
		elementVisible(driver, By.xpath("//input"), 5);
		safeType(driver, By.xpath("//input"), email);
		safeClick(driver, By.xpath("//div[2]/div/div[2]/div/div/div/div[2]"));
		Thread.sleep(1000);
		elementVisible(driver, By.xpath("//input[@name='password']"), 10);
		textPresent(driver, "password",  10);
		safeType(driver, By.xpath("//input[@name='password']"), password);
		safeClick(driver, By.xpath("//div/div/content/span"));
		Thread.sleep(1000);
		elementVisible(driver, By.xpath("//div/input"), 20);
	}

	public Response validation_PaymentUI(String payType, Response resp){
		boolean isMatching = false;
		String status="";
		String payment_url="";
		Reporter.log("Response body "+payType +" : "+ resp.body().asString());
		int statusCode = resp.getStatusCode();	
		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();

		if (payType.equalsIgnoreCase("BookApp/GetPay")) {				

			status = jsonPathEvaluator.getString("status");
			payment_url = jsonPathEvaluator.getString("payment_url"); 

			if(status.equals("S") && !payment_url.equals("null"))
				isMatching = true;
		}

		Assert.assertTrue(isMatching);
		return resp;
	}

	public void validatePaymentURLLoad(RemoteWebDriver driver,String payment_url){

		try{
			boolean isLoading = false;
			List<WebElement> payment_modes = driver.findElements(By.xpath("//ul[@id='paymentModeTab']"));
			if(payment_modes.size()!=0){
				isLoading = true;
			}

			Assert.assertTrue("Payment page did not load", isLoading);
		}
		catch(Exception e){
			System.out.println(e);

		}

	}

	public String fetchPaymentURL(Response resp){
		String payurl="";
		JsonPath jsonPathEvaluator = resp.jsonPath();
		payurl = jsonPathEvaluator.getString("payment_url");
		Reporter.log("http://qa2.cleartrip.com"+payurl);
		return payurl;

	}

	public void pressTabOnElements(RemoteWebDriver driver,String xpath) throws InterruptedException{
		WebElement element = driver.findElement(By.xpath(xpath));
		element.sendKeys(Keys.TAB);
		Thread.sleep(3000);
	}

	public void validateErrors(RemoteWebDriver driver,String errorText,String xpath){
		boolean isMatching = false;
		try{
			xpath = xpath.replace("errortext", errorText);
			if(driver.findElement(By.xpath(xpath)).isDisplayed()){
				isMatching = true;
			}

		}

		catch(Exception e){
			Reporter.log(errorText);
		}

		finally{
			Assert.assertTrue("Error text is not valid",isMatching);
		}

	}
	
	public static boolean isElementPresent(RemoteWebDriver driver, String xpath) throws Exception {
		try {
			driver.findElement(By.xpath(xpath));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean elementVisible(RemoteWebDriver driver, String xpath, int Time) throws Exception {
		boolean visible = false;
		boolean elementPresent;
		int second = 0;
		try {
			for (second = 0; second <= Time; second++) {
				elementPresent = isElementPresent(driver, xpath);
				if (elementPresent) {
					if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
						visible = true;
						break;
					}
				}
				Thread.sleep(1000);
			}
			if (!visible) {
				// addLog(by + " is not displayed in the page", true);
			}
			return visible;
		} catch (StaleElementReferenceException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void click(RemoteWebDriver driver,String xpath) throws InterruptedException{
		driver.findElement(By.xpath(xpath)).click();
	}


	public void validateFooterLink(RemoteWebDriver driver,String footerName,String xpath){
		boolean isMatching = false;

		try{
			xpath = xpath.replace("footername", footerName);
			if(driver.findElement(By.xpath(xpath)).isDisplayed()){
				isMatching = true;
			}
		}
		catch(Exception e){
			Reporter.log("Footer name is matching as" +footerName);
		}

		finally{
			Assert.assertTrue("Footer Name is either not valid or not present",isMatching);
		}
	}

	public void validatePaymentModes(RemoteWebDriver driver,String paymentModeName,String paymentModeXpath){
		boolean isMatching = false;
		try{
			paymentModeXpath = paymentModeXpath.replace("paymentmode", paymentModeName);
			if(driver.findElement(By.xpath(paymentModeXpath)).isDisplayed()){
				isMatching = true;
			}
		}
		catch(Exception e){
			Reporter.log("Payment Mode Name is matching as" +paymentModeName);
		}

		finally{
			Assert.assertTrue("Payment Mode Name is not present", isMatching);
		}

	}

	public void validateSectionDetails(RemoteWebDriver driver,String sectionName, String xpath){
		boolean isPresent= false;

		try{

			xpath = xpath.replace("sectionname", sectionName);
			if(driver.findElement(By.xpath(xpath)).isDisplayed()){
				isPresent=true;
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}

		finally{
			Assert.assertTrue("Section Name is not valid",isPresent);
		}

	}

	public void validateLicenseAgreementPolicyLinks(RemoteWebDriver driver,String licenseFieldName, String xpath){
		boolean isPresent= false;

		try{
			xpath = xpath.replace("#", licenseFieldName);
			List<WebElement> element = driver.findElements(By.xpath(xpath));
			if(element.size()>0){
				isPresent=true;
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}

		finally{
			Assert.assertTrue("Field name is not present",isPresent);
		}

	}
	
	
	public static void selectItemFromList(RemoteWebDriver driver,String xpath,String text) {
		try {
			Select select = new Select(driver.findElement(By.xpath(xpath)));
			List<WebElement> list = select.getOptions();
			for (WebElement element : list) {
				if (element.getText().contains(text)) {
					select.selectByVisibleText(element.getText());
					return;
				}
			}
		} catch(Exception e){
			Reporter.log("Exception is" +e);
			Assert.fail("No such element found::locator value");
		}
	}
	

	public void validateIfImagesArePresent(RemoteWebDriver driver,String xpath,String imageName){
		boolean isPresent= false;

		try{
			xpath = xpath.replace("imagename", imageName);
			List<WebElement> element = driver.findElements(By.xpath(xpath));
			if(element.size()==1){
				isPresent=true;
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}


		finally{
			Assert.assertTrue("Image name is not present",isPresent);
		}
	}

	public void fillInInvalidCreditCardDetails(RemoteWebDriver driver, String cardNumberxpath,String cardNamexpath, String expiryMonthxpath, String expiryYearxpath, String cvvxpath){
		try{
			driver.findElement(By.xpath(cardNumberxpath)).sendKeys(PaymentUI_CommonUtilities.invalidCardNumber);
			driver.findElement(By.xpath(cardNamexpath)).sendKeys(PaymentUI_CommonUtilities.validCardName);
			driver.findElement(By.xpath(expiryMonthxpath)).sendKeys(PaymentUI_CommonUtilities.validExpiryMonth);
			driver.findElement(By.xpath(expiryYearxpath)).sendKeys(PaymentUI_CommonUtilities.validExpiryYear);
			driver.findElement(By.xpath(cvvxpath)).sendKeys(PaymentUI_CommonUtilities.validCvv);
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}

	public void validatetriptravellersanditineraryDetails(RemoteWebDriver driver,String sectionFieldName,String xpath){
		boolean isPresent=false;

		try{
			xpath = xpath.replace("sectionfieldname", sectionFieldName);
			if(driver.findElement(By.xpath(xpath)).isDisplayed()){
				isPresent=true;

			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}


		finally{
			Assert.assertTrue("Field is not present",isPresent);
		}
	}

	public void validateIfPresent(RemoteWebDriver driver, String xpath){
		boolean isAvailable= false;

		try{
			WebElement element = driver.findElement(By.xpath(xpath));
			if(element.isDisplayed()){
				isAvailable=true;
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}

		finally{
			Assert.assertTrue("Element is not present",isAvailable);
		}
	}

	public void validatepricingSectionDetails(RemoteWebDriver driver,String pricingsectionFieldName,String xpath){
		boolean isPresent=false;

		try{
			xpath = xpath.replace("pricingsectionfieldname", pricingsectionFieldName);
			if(driver.findElement(By.xpath(xpath)).isDisplayed()){
				isPresent=true;

			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}


		finally{
			Assert.assertTrue("Field is not present",isPresent);
		}
	}

	public void validateErrorWhenLicenseCheckboxIsUnchecked(RemoteWebDriver driver, String xpath){
		boolean isPresent=false;
		try{
			List<WebElement> webElement = driver.findElements(By.xpath(xpath));
			WebElement makePaymentButton= driver.findElement(By.xpath(PaymentUI_CommonUtilities.makePaymentbutton));
			String classes= makePaymentButton.getAttribute("class");
			boolean isDisabled = classes.contains("c-not-allowed");
			if(webElement.size()==1 && isDisabled==true){
				String errorText = driver.findElement(By.xpath(PaymentUI_CommonUtilities.licenseAgreementErrorTextXpath)).getText();
				Assert.assertEquals(errorText, PaymentUI_CommonUtilities.licenseAgreementErrorText);
				isPresent=true;
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}

		finally{
			Assert.assertTrue("License Agreement CheckBox is Not Present",isPresent);
		}
	}

	public void storedCardCheckbox(RemoteWebDriver driver,String xpath){
		boolean isDisplayed=false;

		try{
			WebElement element=driver.findElement(By.xpath(xpath));
			if(element.isDisplayed()){
				click(driver,PaymentUI_CommonUtilities.storedCardCheckbox);
				isDisplayed=true;
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}

		finally{
			Assert.assertEquals(true, isDisplayed);
		}
	}
	public void fillValidCreditCardDetails(RemoteWebDriver driver, String cardNumberxpath){
		try{
			Thread.sleep(2000);
			pressTabOnElements(driver,cardNumberxpath);
			driver.findElement(By.xpath(cardNumberxpath)).clear();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			pressTabOnElements(driver,cardNumberxpath);
			driver.findElement(By.xpath(cardNumberxpath)).sendKeys(PaymentUI_CommonUtilities.validCardNumber);
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}

	public void fillValidAmexCreditCardDetails(RemoteWebDriver driver, String cardNumberxpath,String cardNamexpath, String expiryMonthxpath, String expiryYearxpath, String cvvxpath){
		try{
			driver.findElement(By.xpath(cardNumberxpath)).sendKeys(PaymentUI_CommonUtilities.validAmexCardNumber);
			driver.findElement(By.xpath(cardNamexpath)).sendKeys(PaymentUI_CommonUtilities.validCardName);
			driver.findElement(By.xpath(expiryMonthxpath)).sendKeys(PaymentUI_CommonUtilities.validExpiryMonth);
			driver.findElement(By.xpath(expiryYearxpath)).sendKeys(PaymentUI_CommonUtilities.validAmexExpiryYear);
			driver.findElement(By.xpath(cvvxpath)).sendKeys(PaymentUI_CommonUtilities.validAmexCvv);
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}
	
	public void fillCardDetailsForEW(RemoteWebDriver driver, String cardNumberxpath,String cardNamexpath, String expiryMonthxpath, String expiryYearxpath){
		try{
			driver.findElement(By.xpath(cardNumberxpath)).sendKeys(PaymentUI_CommonUtilities.validAmexCardNumber);
			driver.findElement(By.xpath(cardNamexpath)).sendKeys(PaymentUI_CommonUtilities.validCardName);
			driver.findElement(By.xpath(expiryMonthxpath)).sendKeys(PaymentUI_CommonUtilities.ewAddCardValidExpiryMonth);
			driver.findElement(By.xpath(expiryYearxpath)).sendKeys(PaymentUI_CommonUtilities.ewAddCardValidExpiryYear);
			}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}


	public void validateErrorForInvalidCCDetails(RemoteWebDriver driver, String xpath){

		boolean isMatching = false;

		try{
			String errorText = driver.findElement(By.xpath(xpath)).getText();
			if(errorText.equals(PaymentUI_CommonUtilities.errorTextInvalidCC)){
				isMatching = true;
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}

		finally{
			Assert.assertTrue("Error text is not valid", isMatching);
		}


	}

	public void validateNoErrorForValidCCDetails(RemoteWebDriver driver, String xpath){

		boolean isMatching = false;

		try{
			Thread.sleep(10000);
			List<WebElement> webElements = driver.findElements(By.xpath(xpath));
			if(webElements.isEmpty()){
				isMatching=true;
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}

		finally{
			Assert.assertTrue("Error text is not valid", isMatching);
		}


	}

	public void validateRetryCaptcha(RemoteWebDriver driver,String xpath, String captchaerror){

		boolean isMatching=false;

		try{
			xpath= xpath.replace("captchaerrormessage", captchaerror);
			String errorMessageCaptcha = driver.findElement(By.xpath(xpath)).getText();
			if(errorMessageCaptcha.equals(PaymentUI_CommonUtilities.captchaErrorName)){
				isMatching=true;
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
			System.out.println(e);
		}

		finally{
			Assert.assertTrue("Error text is not valid", isMatching);
		}


	}

	public void waitOnAParticularElement(RemoteWebDriver driver,String xpath){
		try{
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}

	public void scrollIntoAParticularElement(RemoteWebDriver driver, String xpath){
		try{
			WebElement element = driver.findElement(By.xpath(xpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500); 
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}

	public void convenienceText(RemoteWebDriver driver, String xpath){

		boolean isExisting= false;

		try{
			xpath=xpath.replace("#", PaymentUI_CommonUtilities.convenienceFeeText);
			List<WebElement> webElement = driver.findElements(By.xpath(xpath));
			if(webElement.size()==1){
				isExisting= true;		
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}

		finally{
			Assert.assertTrue("Convenience Fee Text does not exist",isExisting);
		}
	}

	public void validateCCPageText(RemoteWebDriver driver, String xpath){
		boolean isExisting= false;

		try{
			xpath=xpath.replace("#", PaymentUI_CommonUtilities.creditCardPageText);
			List<WebElement> webElement = driver.findElements(By.xpath(xpath));
			if(webElement.size()==1){
				isExisting= true;		
			}
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}

		finally{
			Assert.assertTrue("Credit Card Page Text Does Not Exist",isExisting);
		}
	}

	public void validateDCPageText(RemoteWebDriver driver, String xpath){
		boolean isExisting= false;

		try{
			xpath=xpath.replace("#", PaymentUI_CommonUtilities.debitCardPageText);
			List<WebElement> webElement = driver.findElements(By.xpath(xpath));
			if(webElement.size()==1){
				isExisting= true;		
			}
		}
		catch(Exception e){
			Reporter.log("Exception is" +e);
		}

		finally{
			Assert.assertTrue("Debit Card Page Text Does Not Exist",isExisting);
		}
	}

	public void navigateBack(RemoteWebDriver driver){
		try{
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}
	
	public void signCleartripHomePage(RemoteWebDriver driver,String email, String password)throws Exception  {
		try {
				
				driver.get(qaurl);		
				Thread.sleep(5000);
				safeClick(driver, getObjectPayment("B2C_HomePage_YourTrips"));
				safeClick(driver, getObjectPayment("B2C_HomePage_SignIn"));
				Thread.sleep(5000);
				driver.switchTo().frame("modal_window");
				safeType(driver, getObjectPayment("B2C_HomePage_Email"), email);
				safeType(driver, getObjectPayment("B2C_HomePage_Password"), password);
				safeClick(driver, getObjectPayment("B2C_HomePage_SignInButton"));
				Thread.sleep(10000);
		
			} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	
	public void selectFromDropdown(RemoteWebDriver driver, String xpath, String dropDownValue){
		try{
			Select options = new Select(driver.findElement(By.xpath(xpath)));
			options.selectByVisibleText(dropDownValue);
		}
		
		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}
	

}