package test.java.commonUI;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class PaymentsUI_Common_Desktop extends PaymentsUI_Common{

    public void payUI_Select_PaymentType(RemoteWebDriver driver, String PayType) throws Exception {
        for (int i = 0; i < 10; i++) {
            if(elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 1)) {
                break;
            }
            else if(textPresent(driver, "System error", 1)) {
                Reporter.log("There's something wrong with our system");
                Assert.assertTrue(false);
            } else if(textPresent(driver, "Oops, Something went wrong", 1)) {
                Reporter.log("Oops something wrong with our system");
                Assert.assertTrue(false);
            }

        }
        if(!elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 10)) {
            Reporter.log("PayUI Page is not displayed");
            String UI_error = getText(driver, By.xpath("//h1"));
            Reporter.log(UI_error);
            Assert.assertTrue(false);
        }
        switch (PayType) {
            case "CC":
                PayType = "Debit/Credit card";
                break;
            case "DC":
                PayType = "Debit Card";
                break;
            case "NB":
                PayType = "Net banking";
                break;
            case "TW":
                PayType = "Wallets";
                break;
            case "UPI":
                PayType = "UPI";
                break;
            case "ADCB":
                PayType = "ADCB touchPoints";
                break;
            case "SC":
                PayType = "Saved payment modes";
                break;
            case "KNET":
                PayType = "KNET";
                break;
            case "PayPal":
                PayType = "PayPal";
                break;
            case "EMI":
                PayType = "EMI";
                break;
            default:
                PayType = "Credit Card";
                break;
        }
        safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs"), PayType);
    }

    public void bento_pay_StoredCard(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType(driver, "SC");
        textPresent_Log(driver, "Select a saved payment mode", 5);
        if (CardNumber == "7777") {
            if (domain.equals("HOTELS")) {
                safeClick(driver, getObjectPayment("Bento_Payment_SC_Tokenized_CVV_7777"));
                safeType(driver, getObjectPayment("Bento_Payment_SC_Tokenized_CVV_7777"), "123");
            }
        }
        Reporter.log("Entered CVV");
        safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
        Reporter.log("Clicked on paynow");
        if (elementVisible(driver, getObjectPayment("Bento_Payment_Skip_Securecard"), 1)) {
            safeClick(driver, getObjectPayment("Bento_Payment_Skip_Securecard"));
            Thread.sleep(1000);
        }
        bento_pay_GWPage(driver, "RAZORPAYNB", "", "Success");
    }

    public void bento_pay_GWPage(RemoteWebDriver driver, String GWName, String PaymentType, String SuccessFail ) throws Exception {
        if(GWName.equalsIgnoreCase("PAYU")){
            elementVisible(driver, getObjectPayment("Bento_submit"), 20);
            safeClick(driver, getObjectPayment("Bento_card_password"));
            Thread.sleep(1000);
            safeType(driver, getObjectPayment("Bento_card_password"),"123456");
            safeClick(driver, getObjectPayment("Bento_submit"));
        }
        else if(GWName.equalsIgnoreCase("RAZORPAYCC")){
            elementVisible(driver, getObjectPayment("Bento_Payment_Razropay_Submit"),20);
            textPresent(driver, "One Time Password (OTP) successfully sent to the phone number linked to your card ending with 0000.", 1);
            safeClick(driver, getObjectPayment("Bento_Payment_Razropay_Pin"));
            safeType(driver,getObjectPayment("Bento_Payment_Razropay_Pin"),"0000");
            safeClick(driver,getObjectPayment("Bento_Payment_Razropay_Submit"));
            /*textPresent_Log(driver, "Booking successful", 10);
            Reporter.log("Payment done successfully");*/
        }
        else if(GWName.equalsIgnoreCase("RAZORPAYNB")){
            if(SuccessFail.equalsIgnoreCase("Success")){
                Thread.sleep(2000);
                textPresent(driver, "Welcome to Razorpay Software Private Ltd Bank", 5);
                elementPresent_log(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"), "",	10);
                textPresent(driver, "Welcome to Razorpay Software Private Ltd Bank", 10);
                elementVisible(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"), 10);
                safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"));
                Reporter.log("Clicked on Success NB");
            }
            else {
                elementPresent_log(driver, getObjectPayment("Bento_Payment_NB_Payment_Failure"), "",	10);
                safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Failure"));
                Reporter.log("Clicked on failure NB");
            }
        }
        else if(GWName.equalsIgnoreCase("PAYTM")){
            if(elementVisible(driver, By.xpath("//button[@onclick='submitSuccess()']"), 5)) {
                    safeClick(driver, By.xpath("//button[@onclick='submitSuccess()']"));
            }
            else {
                textPresent(driver, "Welcome to Razorpay Software Private Ltd Bank", 1);
                if(elementVisible(driver, getObjectPayment("Bento_Payment_DC_Payment_Success"), 5))
                {
                    safeClick(driver, getObjectPayment("Bento_Payment_DC_Payment_Success"));
                }
                else if(elementVisible(driver, By.xpath("//input[@type='tel']"), 10))
                {
                    driver.findElement(By.xpath("//input[@type='tel']")).click();
                    driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("1111");
                    driver.findElement(By.xpath("//button[@id='submit-action']/span")).click();
                }
            }
        }
        else if(GWName.equalsIgnoreCase("AMEX")){
            if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
            {
                smartClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
            }
            textPresent(driver, "ACS Emulator", 20);
            Thread.sleep(5000);
            smartClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
        }
    }

    public void bento_paymentpage(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName) throws Exception {
        textPresent_Log(driver, "Pay to complete your booking", 20);
        Reporter.log(driver.getCurrentUrl());
        System.out.println(driver.getCurrentUrl());
        if (PaymentType.equalsIgnoreCase("WALLET")) {
            safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
            Reporter.log("Selected wallet");
            Thread.sleep(2000);
        }
        if (PayType.equalsIgnoreCase("WALLET")) {
            textPresent_Log(driver, "Cleartrip wallet", 2);
            safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
            Reporter.log("Selected wallet");
            Thread.sleep(2000);
        }
      //  pay_fares(driver);
        switch (PaymentType) {
            case "storedcard":
                bento_pay_StoredCard(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "GVPriceChange":
                bento_pay_GVPriceChange(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "storedcardUPI":
                bento_pay_StoredCard_UPI(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "CC":
                bento_pay_CC(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "EMI":
                bento_pay_EMI(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "BFL":
                bento_pay_BFL(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "NB":
                bento_pay_NB(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "TW":
                bento_pay_TW(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "wallet":
                bento_pay_Wallet(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "Phonepe":
                bento_pay_PhonePe(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "UPIScan":
                bento_pay_UPIScan(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "UPI":
                bento_pay_UPI(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "GV":
                bento_pay_GV(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "GV_Partial":
                bento_pay_GV_Partial(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "partial_wallet":
                bento_pay_PartialWallet(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "Coupon":
                bento_pay_Coupon(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
            case "sc":
                bento_pay_SC(driver, PaymentType, CardNumber, domain, PayType, BankName);
                break;
        }
    }

    public void bento_pay_BFL(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 10);
        payUI_Select_PaymentType(driver, "EMI");
        safeClick(driver, By.xpath("//div[6]/div/p"));
        Thread.sleep(2000);
        safeClick(driver, By.xpath("//div[3]/div/div[4]/div"));
        Thread.sleep(2000);
        safeClick(driver, By.xpath("//div[5]/div/div[3]/label/div/span"));
        Thread.sleep(2000);
        safeClick(driver, By.xpath("//div[2]/button"));
        Thread.sleep(2000);
        textPresent_Log(driver, "Cleartrip does not levy any charges for availing EMI.", 5);
        textPresent_Log(driver, "BAJAJ Finserv", 5);
        textPresent_Log(driver, "Cleartrip does not levy any charges for availing EMI.", 5);
        safeType(driver, By.id("cardNumber"),"2030400200341834");
        safeType(driver, By.id("name"),"Kiran");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        safeClick(driver, By.xpath("//div[3]/button"));
        bento_pay_GWPage(driver,"RAZORPAYNB","","Success");
    }

    public void bento_pay_EMI(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 10);
        payUI_Select_PaymentType(driver, "EMI");
        //safeClick(driver, getObjectPayment("PaymentPage_EMI_ICICIBank_Radio_Btn"));
        elementVisible(driver, By.xpath("//div[10]/div/div[3]/label/div"), 5);
        if(PayType.equalsIgnoreCase("NoCostEMI")) {
            safeClick(driver, By.cssSelector("label.switch-label"));
            String NoCostEMI_Text = getText(driver, By.xpath("//div[10]/div/div[3]/label/div"));
            if (!NoCostEMI_Text.contains("No Cost")) {
                Reporter.log("No Cost text not displayed");
                //Assert.assertTrue(false);
            }

            safeClick(driver, By.xpath("//div[10]/div/div[3]/label/div"));
        }
        else if(PayType.equalsIgnoreCase("EMI")){
            String NoCostEMI_Text = getText(driver, By.xpath("//div[7]/label/div/span"));
            safeClick(driver, By.xpath("//div[7]/label/div/span"));
            if (NoCostEMI_Text.contains("No Cost")) {
                Reporter.log("No Cost text not displayed");
                Assert.assertTrue(false);
            }
        }
        if(PayType.equalsIgnoreCase("EMI")) {
            textPresent_Log(driver, "Interest charged by bank is non-refundable", 1);
        }
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(1000);

        mouseHover(driver, By.xpath("//div[3]/button"));
        safeClick(driver, By.xpath("//div[3]/button"));
        elementVisible(driver, getObjectPayment("PaymentPage_EMI_EnterCard_Details_btn"), 10);
        scrollToElement(driver, getObjectPayment("PaymentPage_EMI_EnterCard_Details_btn"));
        //	safeClick(driver, getObjectPayment("PaymentPage_EMI_EnterCard_Details_btn"));
        //textPresent_Log(driver, "Selected EMI option", 5);

        textPresent_Log(driver, "Interest (Charged by Bank)", 5);
        if(PayType.equalsIgnoreCase("NoCostEMI")) {
            textPresent_Log(driver, "No Cost EMI discount", 1);
            textPresent_Log(driver, "is given upfront as No Cost EMI discount", 1);
        }
        else if(PayType.equalsIgnoreCase("NoCostEMI")) {
            textPresent_Log(driver, "total cost includes interest of", 1);
        }
        elementVisible(driver, getObjectPayment("PaymentPage_EMI_Change_Plan_Button"), 5);
        textPresent_Log(driver, "Enter credit card details", 5);
        Enter_CC_Details(driver, platform.value("RazorPay_Number"), platform.value("RazorPay_Month_UI"), platform.value("RazorPay_Year"), platform.value("RazorPay_CVV"));
        //safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
        js.executeScript("window.scrollBy(0,500)");

        safeClick(driver, By.xpath("//div/div/div/div[3]/button"));


        Reporter.log("Clicked on paynow");
        if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),1))
        {
            safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
            Thread.sleep(1000);
        }
        bento_pay_GWPage(driver,"RAZORPAYCC","","");
    }

    public void bento_pay_CC(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType(driver, "CC");
        if(CardNumber=="4111") {
            payUI_Enter_PaymentDetails(driver, "CC", "RAZORPAYDC","");
            Thread.sleep(1000);
            if(textPresent(driver, "Your wallet balance is sufficient", 2)) {
                safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
                Reporter.log("Deselected wallet");
                Thread.sleep(2000);
            }
            smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
            //Thread.sleep(1000);
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
            Reporter.log("Clicked on paynow");
            if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),1))
            {
                safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
            }
            bento_pay_GWPage(driver,"PAYTM","","");
        }
        else if(CardNumber=="INVALID") {

            payUI_Enter_PaymentDetails(driver, "CC", "INVALID","");
            smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
            for (int i=0; i<=5; ++i) {
                Thread.sleep(2000);
                safeClick(driver, getObjectPayment("Bento_paynow"));
                textPresent(driver, "Invalid card number. Please re-enter the correct card details", 2);
            }
            safeClick(driver, getObjectPayment("Bento_paynow"));
            safeClick(driver, By.cssSelector("div.br-100.flex.flex-center.flex-middle.c-pointer > svg"));
            safeClick(driver, getObjectPayment("Bento_paynow"));
            textPresent_Log(driver, "Please validate captcha", 10);
            //elementPresent_log(driver, getObjectPayment("PaymentPage_GoogleCaptcha_Widget"), "Google Captcha", 5);
        }
        else if(CardNumber=="5123") {

            payUI_Enter_PaymentDetails(driver, "CC", "MASTER","");
            smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
            safeClick(driver, getObjectPayment("Bento_paynow"));
            //Save Card RBI popup
            if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
            {
                smartClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
            }
            bento_pay_GWPage(driver,"PAYU","","");

        }
        else if(CardNumber=="5241")
        {

            payUI_Enter_PaymentDetails(driver, "CC", "RAZORPAY","");
            smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
            Reporter.log("Clicked on paynow");

            if (textPresent(driver, "Cleartrip wallet", 5)) { safeClick(driver,
                    getObjectPayment("Bento_Payment_Deselect_Wallet"));
                Reporter.log("Deselected wallet"); Thread.sleep(2000); }

            if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
            {
                smartClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
            }
            bento_pay_GWPage(driver, "RAZORPAYCC","","");
        }
        else if(CardNumber=="3456")
        {
            payUI_Enter_PaymentDetails(driver, "CC", "AMEX","");
            smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("window.scrollBy(0,600)");
            Thread.sleep(1000);
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
            Reporter.log("Clicked on paynow");
            if (textPresent(driver, "Cleartrip wallet", 5))
            {
                //smartClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
                Reporter.log("Deselected wallet");
                Thread.sleep(2000);
            }
            bento_pay_GWPage(driver, "AMEX","","");

        }

        else if(CardNumber=="7777")
        {
            payUI_Enter_PaymentDetails(driver, "CC", "PAYTM","");
            smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("window.scrollBy(0,600)");
            Thread.sleep(1000);
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
            Reporter.log("Clicked on paynow");
            if (textPresent(driver, "Cleartrip wallet", 5))
            {
                //smartClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
                Reporter.log("Deselected wallet");
                Thread.sleep(2000);
            }
            bento_pay_GWPage(driver,"PAYTM","","");
        }

        else if(CardNumber=="7777SC")
        {
            payUI_Enter_PaymentDetails(driver, "CC", "PAYTM","");
            textPresent_Log(driver, "Save my card as per the RBI guidelines", 1);
            //safeClick(driver, By.xpath(""));

            smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,600)");
            Thread.sleep(1000);
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
            Reporter.log("Clicked on paynow");
            if (textPresent(driver, "Cleartrip wallet", 5))
            {
                //smartClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
                Reporter.log("Deselected wallet");
                Thread.sleep(2000);
            }
            bento_pay_GWPage(driver,"PAYTM","","");
        }
        else
        {
            safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
            safeClick(driver, getObjectPayment("Bento_Payment_SC_CVV"));
            safeType(driver, getObjectPayment("Bento_Payment_SC_CVV"), "1234");
            Reporter.log("Entered CVV");
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
            Reporter.log("Clicked on paynow");
            if (textPresent(driver, "Cleartrip wallet", 5))
            {
                safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
                Reporter.log("Deselected wallet");
                Thread.sleep(2000);
            }
		/*	if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
			{
				safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
			}
			textPresent_Log(driver, "Please wait...", 2);
			textPresent_Log(driver, "American", 2);
			textPresent_Log(driver, "ACS Emulator", 2);
			safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
			textPresent_Log(driver, "Booking successful", 5);
			Reporter.log("Payment done successfully");*/
        }
        //bento_pay_GWPage(driver, "AMEX","","");
    }

    public void bento_pay_StoredCard_UPI(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType(driver, "SC");
        textPresent_Log(driver, "Select a saved payment mode", 5);
        textPresentInElementAssert(driver,By.xpath("//div[@class='hover:bg-neutral-0'][1]"), "3212467@okhdfcbank",5);
        safeClick(driver,By.xpath("//div[@class='hover:bg-neutral-0'][1]"));
        safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
    }

    public void bento_pay_PartialWallet(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
        elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
        safeClick(driver,getObjectPayment("Bento_select_cardsec"));
        payUI_Enter_PaymentDetails(driver, "CC", "AMEX","");
        safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
        Reporter.log("Clicked on paynow");
        if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
        {
            safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
        }
        textPresent_Log(driver, "Please wait...", 5);
        textPresent_Log(driver, "American", 5);
        textPresent_Log(driver, "ACS Emulator", 10);
        safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
        textPresent_Log(driver, "Booking successful", 5);
        Reporter.log("Payment done successfully");

    }

    public void bento_pay_Coupon(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        if(CardNumber.equalsIgnoreCase("Air")) {
            textPresent_Log(driver, "Coupon code (PAYCC)", 2);
        }
        if(CardNumber.equalsIgnoreCase("Hotel")) {
            textPresent_Log(driver, "Coupon code (CCHOTEL)", 2);
        }
        elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
        smartClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
        Reporter.log("Clicked on SC");
        payUI_Select_PaymentType(driver, "CC");
        payUI_Enter_PaymentDetails(driver, "CC", "RAZORPAY","");
        safeClick(driver, getObjectPayment("Bento_paynow"));
        //Save Card RBI popup
        if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
        {
            safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
        }
        textPresent(driver, "Please wait...", 5);
        textPresent(driver, "One Time Password (OTP) successfully sent to the phone number linked to your card ending with 0000.", 5);
        safeClick(driver, getObjectPayment("Bento_Payment_Razropay_Pin"));
        safeType(driver,getObjectPayment("Bento_Payment_Razropay_Pin"),"0000");
        safeClick(driver,getObjectPayment("Bento_Payment_Razropay_Submit"));
       // textPresent_Log(driver, "Booking successful", 10);
        Reporter.log("Payment done successfully");
    }

    public void bento_pay_GV_Partial(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        textPresent_Log(driver, "Pay to complete your booking", 10);
        textPresent_Log(driver, "Gift card", 1);
        textPresent_Log(driver, "Cleartrip wallet", 1);
        bento_pay_CC(driver, PaymentType, CardNumber, domain, PayType, BankName);
    }

    public void bento_pay_SC(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

		/*	safeClick(driver,getObjectPayment("Bento_select_cardsec"));
		payUI_Enter_PaymentDetails(driver,PayType,BankName,"");
		safeClick(driver, getObjectPayment("Bento_paynow"));
		if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
		{
		safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
		}*/
        if(CardNumber=="5123")
        {
            elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
            safeClick(driver, getObjectPayment("Bento_card_password"));
            Thread.sleep(1000);
            safeType(driver, getObjectPayment("Bento_card_password"),"123456");
            safeClick(driver, getObjectPayment("Bento_submit"));

        }
        else if(CardNumber=="5241")
        {
            if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
            {
                safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
            }

            if(textPresent(driver, "Bank Demo", 20)) {
                if(elementVisible(driver, By.xpath("//button[@onclick='submitSuccess()']"), 5))
                {
                    safeClick(driver, By.xpath("//button[@onclick='submitSuccess()']"));
                }
            }
            else {
                elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
                safeClick(driver, getObjectPayment("Bento_card_password"));
                Thread.sleep(1000);
                safeType(driver, getObjectPayment("Bento_card_password"),"123456");
                safeClick(driver, getObjectPayment("Bento_submit"));
            }

        }

        textPresent_Log(driver, "Booking successful", 10);
        Reporter.log("Payment done successfully");

    }

    public void bento_Select_PaymentType(RemoteWebDriver driver, String PayType) throws Exception {
        for (int i = 0; i < 2; i++) {
            if(textPresent(driver, "System error", 1)) {
                Reporter.log("There's something wrong with our system");
                Assert.assertTrue(false);
            } else if(textPresent(driver, "Oops, Something went wrong", 1)) {
                Reporter.log("Oops something wrong with our system");
                Assert.assertTrue(false);
            }
            if(elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 1)) {
                break;
            }

        }
        if(textPresent(driver, "Your wallet balance is sufficient", 2)) {
            safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
        }
        if(!elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 5)) {
            Reporter.log("PayUI Page is not displayed");
            String UI_error = getText(driver, By.xpath("//nav/div/p"));
            Reporter.log(UI_error);
            Assert.assertTrue(false);
        }
        switch (PayType) {
            case "CC":
                PayType = "Debit/Credit card";
                break;
            case "NB":
                PayType = "Net banking";
                break;
            case "TW":
                PayType = "Wallets";
                break;
            case "UPI":
                PayType = "UPI";
                break;
            case "EMI":
                PayType = "EMI";
                break;
            case "ADCB":
                PayType = "ADCB TouchPoints";
                break;
            case "SC":
                PayType = "Saved cards";
                break;
            case "KNET":
                PayType = "KNET";
                break;
            case "PayPal":
                PayType = "PayPal";
                break;
            default:
                PayType = "Debit/Credit card";
                break;
        }
        safeClickList(driver, getObjectPayment("Bento_Pay_Tabs"), PayType);
    }

    public void bento_pay_NB(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        textPresent(driver, "Pay to complete your booking", 5);
        if(textPresent(driver, "Your wallet balance is sufficient", 2)) {
            safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
            Reporter.log("Deselected wallet");
            Thread.sleep(2000);
        }
        if(PayType.contains("Coupon")) {
            // Invalid Coupon Validation
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
            bento_Select_PaymentType(driver, "NB");
            Reporter.log("Clicked on NB");
            safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));

            elementVisible(driver, By.xpath("//div[5]/div/p"), 10);
            textPresent_Log(driver, "Coupon not applicable", 10);
            elementPresent_log(driver, getObjectPayment("Bento_Pay_Coupon_Popup_Close_Btn"), "invaid coupon Pop Up not displayed",	1);
            safeClick(driver, By.xpath("//button[2]")); // Clicking on Change paymentMode
            Thread.sleep(2000);
            textNotPresent_Log(driver, "Coupon not applicable", 1);
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));

            Thread.sleep(2000);
            elementPresent_log(driver, getObjectPayment("Bento_Pay_Coupon_Popup_Close_Btn"), "",	1);
            safeClick(driver, By.xpath("//div[4]/div/button")); // Clicking on Continue without coupon paymentMode
            Reporter.log("Clicked on paynow in Coupon Popup");
        }
        else if(PayType.contains("Retry")) {
            bento_Select_PaymentType(driver, "NB");
            Reporter.log("Clicked on NB");
            safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
            bento_pay_GWPage(driver, "RAZORPAYNB","","Failure");
            //safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Failure"));
            elementPresent_log(driver, getObjectPayment("Bento_Payment_Paynow"),"Pay button in retry page", 30);
            textPresent_Log(driver, "Oops! Your payment failed. If you were charged, any amount deducted will be reversed", 30);
            payUI_Select_PaymentType(driver, "NB");
            Reporter.log("Clicked on NB");
            Thread.sleep(1000);
            safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
            Reporter.log("Selected ICICI Bank");
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
            textPresent(driver, "Please wait...", 2);
            textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 20);
        }
        else {
            payUI_Select_PaymentType(driver, "NB");
            Reporter.log("Clicked on NB");
            Thread.sleep(1000);
            safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
            Reporter.log("Selected ICICI Bank");
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
        }
        Thread.sleep(5000);
        bento_pay_GWPage(driver, "RAZORPAYNB","","Success");
    }

    public void bento_pay_TW(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        bento_Select_PaymentType(driver, "TW");
        Reporter.log("Clicked on TW");
        if(CardNumber.equals("AmazonPay")) {
            safeClick(driver, getObjectPayment("PaymentPage_Wallet_AmazonPay"));
            safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
            textPresent(driver, "Login with your Amazon account", 50);
            safeType(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Email"), "kiran.kumar@cleartrip.com");
            safeClick(driver, By.xpath("//span/input"));
            elementVisible(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Password"),5);
            safeType(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Password"), "Cleartrip@123");
            safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Login"));
            textPresent_Log(driver, "Select payment method", 20);
            safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_SelectCard"));
            elementVisible(driver, getObjectPayment("MakePayment_Amazon_Page_SelectCard_CVV"), 2);
            Thread.sleep(5000);
            safeType(driver, getObjectPayment("MakePayment_Amazon_Page_SelectCard_CVV"), "123");
            safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_Pay_Button"));
            elementPresent_Time(driver, getObjectPayment("MakePayment_Amazon_Page_Mock_Continue_Button"), 10);
            safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_Mock_Continue_Button"));//input
        }
    }

    public void bento_pay_Wallet(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        //safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
        Thread.sleep(1000);
        if (textPresent(driver, "Cleartrip wallet", 2)) {
            elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
            textPresent_Log(driver, "Your wallet balance is sufficient to pay for this booking. Please uncheck wallet to use other payment mode", 2);
            mouseHover(driver, getObjectPayment("Bento_Payment_Paynow"));
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
            //safeClick(driver, By.xpath("//div/div/div/div[3]/button"));
            Reporter.log("Clicked on paynow");
            if(textPresent(driver,"Cleartrip wallet",2))
            {
                smartClick(driver, getObjectPayment("Bento_Payment_Paynow"));
                Reporter.log("Clicked on paynow");
            }
            textPresent(driver, "Please wait...", 10);
            Reporter.log("Payment done successfully");
            Thread.sleep(3000);
        }
    }

    public void bento_pay_PhonePe(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

        payUI_Select_PaymentType(driver, "TW");
        safeClick(driver, getObjectPayment("Bento_Payment_Wallet"));
        Reporter.log("Clicked on Wallet");
        safeClick(driver, getObjectPayment("Bento_Payment_Wallet_Phonepe"));
        Reporter.log("Selected Phonepe");
        safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
        Reporter.log("Clicked on paynow");
        textPresent_Log(driver, "Please wait...", 5);
        textPresent_Log(driver, "PhonePe", 5);
        //textPresent_Log(driver, "SEND OTP TO LOGIN", 5);
        //textPresent(driver, "Scan&Pay via PhonePe App", 2);
        textPresent(driver, "SHOW QR CODE", 2);
        Reporter.log("PhonePe Page Validated");
        System.out.println("PhonePe Page Validated");

    }

    public void bento_pay_UPIScan(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType(driver, "UPI");
        Reporter.log("Clicked on UPI");
        Thread.sleep(2000);
        String Price = getText(driver, getObjectPayment("Bento_Pay_Total_Value"));
        textPresent_Log(driver, "SCAN QR CODE", 5);
        elementPresent_log(driver, getObjectPayment("Bento_Payment_Paynow"), "Show QR Code", 5);
        elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_QRCode"), "QR Image in Payment Page", 5);
        safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));  //Click Show QR Code Button
        textPresent_Log(driver, "Powered by", 10);
        elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_PoweredBy_Text"), "Powered by", 1);
        elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_RazorPay_Image"), "Razorpay Image", 1);
        elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Price"), "Price", 1);
        String QR_Price = getText(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Price"));
        if(!QR_Price.equals(Price)) {
            Reporter.log("Price in Payment page "+Price+" Price in QR page "+QR_Price);
            System.out.println("Price in Payment page "+Price+" Price in QR page "+QR_Price);
            //assertTrue(false);
        }
        String QRPage_URL = getURL(driver);
        Reporter.log(QRPage_URL);
        elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Do_Not_Refresh_Text"), "Do not refresh text", 1);
        textPresent_Log(driver, "Scan here to pay with any UPI app", 1);
        textPresent_Log(driver, "Do not refresh or click 'Go Back'", 1);
        textPresent_Log(driver, "while we check your payment status", 1);
        elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_QRCODE_Image"), "QR Code image", 1);
        elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Gpay_Image"), "GPay Image", 1);
        elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_PhonePe_Image"), "GPay Image", 1);
        elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_PayTM_Image"), "PayTM Image", 1);
        elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_CleartripLogo"), "Cleartrip logo", 1);
        safeClick(driver, getObjectPayment("Bento_Pay_UPIScan_Page_CancelPay_Link"));
        Thread.sleep(2000);
        if(!elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 30)) {
            Reporter.log("Payment page is not displayed");
            Assert.assertTrue(false);
        }
        //textPresent_Log(driver, "Payment cancelled", 10);
        //textPresent_Log(driver, "If you have already paid, please wait for a few minutes before trying again", 1);

    }

    public void bento_pay_UPI(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType(driver, "UPI");
        if(PayType=="SavedUPI"){
            textPresent_Log(driver,"Add new UPI",5);
            textPresent_Log(driver,"Saved UPI ID",1);
            safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
            textPresent_Log(driver,"Enter UPI ID",5);
            textPresent_Log(driver,"Payment request will be sent to the phone",1);
            safeClick(driver, By.xpath("//div/div/div/div[3]/label/div/span"));

        }
        else if(PayType=="SavedCardTab"){

        }

        else {
            payUI_Select_PaymentType(driver, "CC");
            payUI_Select_PaymentType(driver, "UPI");
            Reporter.log("Clicked on UPI");
            Thread.sleep(2000);
            safeClick(driver, getObjectPayment("Bento_Payment_UPI_ID"));
            if(PayType.equalsIgnoreCase("Failure")) {
                safeType(driver, getObjectPayment("Bento_Payment_UPI_ID"), "failure@razorpay");
                Reporter.log("Entered UPI Details");
            }
            else {
                safeType(driver, getObjectPayment("Bento_Payment_UPI_ID"), "9986696785@ybl");
                Reporter.log("Entered UPI Details");
            }
        }
        safeClick(driver, getObjectPayment("Bento_Payment_Paynow_UPI"));
        Reporter.log("Clicked on paynow");
        textPresent(driver, "Please wait...", 2);
        textPresent(driver, "Please accept the collect request sent to your UPI app", 5);
        Reporter.log("Payment done successfully");
        Thread.sleep(5000);
        if(PayType.equalsIgnoreCase("Failure")) {
            textPresent_Log(driver, "Oops! Your payment failed. If you were charged, any amount deducted will be reversed", 20);
        }
    }

    public void bento_pay_GV(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

        elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
        textPresent_Log(driver, "Gift card", 2);
        textPresent_Log(driver, "Pay to complete your booking", 5);
        textNotPresent_Log(driver, "Enter card details", 1);

        String GVText=getText(driver, By.xpath("//div[10]/p"));
        if(!GVText.contains("Gift card")&&GVText.contains(GV_number)) {
            Reporter.log("GV is not displayed"+GVText);
            Assert.assertTrue(false);
        }
        textPresent_Log(driver, "Pay to complete your booking", 1);
        if(elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 1)) {
            Reporter.log("CC tab is displayed");
            Assert.assertTrue(false);
        }
        Reporter.log("payment tab is not displayed for full GV");
        textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare", 2);
        textPresent_Log(driver, "booking policy", 2);
        textPresent_Log(driver, "privacy policy", 1);
        textPresent_Log(driver, "terms", 1);
        textPresent_Log(driver, "Convenience fee", 1);
        Reporter.log("Includes a convenience fee of text is displayed");
        String YouPay = getText(driver, By.xpath("//p[2]/span"));
        if (!YouPay.contains("0")) {
            Reporter.log("Youpay doesn't contain 0 rs");
            Assert.assertTrue(false);
        }

        Reporter.log("Youpay  contain 0 rs");
        String Total = getText(driver, By.xpath("//div/div/span"));
        System.out.println("total "+Total);
        if (!Total.contains("0")) {
            Reporter.log("Total doesn't contain 0 rs");
            Assert.assertTrue(false);
        }else Reporter.log("Total contain 0 rs");

        Assert.assertEquals("Complete booking", getText(driver, getObjectPayment("Bento_Pay_Button")));
        safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
        Reporter.log("Clicked on paynow");
    }


    public String switchToPopup(RemoteWebDriver driver) {
        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        // Now you are in the popup window, perform necessary actions here
        driver.switchTo().window(subWindowHandler); // switch to popup window
        return parentWindowHandler;
    }


    public void bento_pay_GVPriceChange(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName) throws Exception {
        GVPriceChange_Validation(driver, PayType, BankName);
        String parentWindowHandler = switchToPopup(driver);
        mouseHover(driver,By.xpath("//button[2]") );
        safeClick(driver, By.xpath("//button[2]"));  //change payment mode
        driver.switchTo().window(parentWindowHandler);

        GVPriceChange_Validation(driver, PayType, BankName);
        parentWindowHandler = switchToPopup(driver);
        mouseHover(driver, By.cssSelector("svg.c-neutral-900.c-pointer"));
        safeClick(driver, By.cssSelector("svg.c-neutral-900.c-pointer"));  //close button
        driver.switchTo().window(parentWindowHandler);

        GVPriceChange_Validation(driver, PayType, BankName);
        parentWindowHandler = switchToPopup(driver);
        mouseHover(driver, By.xpath("//div[4]/div/button"));
        safeClick(driver, By.xpath("//div[4]/div/button")); //Book anyway
        driver.switchTo().window(parentWindowHandler);

        Thread.sleep(5000);
        bento_pay_GWPage(driver, "RAZORPAYNB","","Success");
    }

    private void GVReduceAmount(String PayType, String BankName) throws ClassNotFoundException, SQLException {
        rearchGV_UI("VALIDATESCLPGV_UI", PayType, BankName);
        rearchGV_UI("CAPTURESCLPGV_UI", PayType, BankName);
    }


    private void GVPriceChange_Validation(RemoteWebDriver driver, String GVNumber, String GVPin) throws Exception {
        bento_Select_PaymentType(driver, "NB");
        GVReduceAmount(GVNumber, GVPin);  //GV number / Pin
        safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
        safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
        elementPresent_log(driver, By.xpath("//div[4]/div/button"),"GV price change button", 5); // book anyway btn
        elementPresent_log(driver, By.cssSelector("svg.c-neutral-900.c-pointer"),"Close button", 5); // close button
        elementPresent_log(driver, By.xpath("//button[2]"),"Close button", 5); // change payment button
        textPresent_Log(driver, "We noticed your Gift card balance has changed from ", 1);
        textPresent_Log(driver, "Gift card balance changed", 1);
        textPresent_Log(driver, "after using", 1);
        textPresent_Log(driver, "Gift card balance.", 1);
    }

    public void Enter_CC_Details(RemoteWebDriver driver, String CCNumber, String CCExpMonth, String CCExpYear, String CVV) throws Exception {
        Thread.sleep(1000);
        Reporter.log("Card Details +\n"+ CCNumber +"\n " + CCExpMonth  +" " + CCExpYear +" " + CVV);
        safeType(driver, getObjectPayment("PaymentPage_CreditCard_Number"), CCNumber);
        safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"));
        safeSelect(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"), CCExpMonth);
        safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"));
        Thread.sleep(1000);
        safeSelect(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"), CCExpYear);
        safeType(driver, getObjectPayment("PaymentPage_CreditCard_Name"), "test");
        safeType(driver, getObjectPayment("PaymentPage_CreditCard_CVV"), CVV);
        Thread.sleep(1000);
    }

    public void payUI_Enter_PaymentDetails(RemoteWebDriver driver, String PayType, String BankName, String BookingType) throws Exception {
        switch (PayType) {
            case "CC":
                payUI_Select_CC(driver, BankName, BookingType);
                break;
            case "DC":
                payUI_Select_DC(driver, BankName, BookingType);
                break;
            case "NB":
                payUI_Select_NB(driver, BankName, BookingType);
                break;
            case "TW":
                break;
            case "UPI":
                payUI_Select_UPI(driver, BankName, BookingType);
                break;
            case "KNET":
                payUI_Select_KNET(driver, BankName, BookingType);
                break;
            default:
                break;
        }
    }

    public void payUI_Select_CC(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {
        elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 5);
        textPresent(driver, "Enter card details", 1);
        switch (BankName) {
            case "MASTER":
                Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
                break;
            case "AMEX":
                Enter_CC_Details(driver, platform.value("AmexCard_Number"), platform.value("AmexCard_Month_New"), platform.value("AmexCard_Year"), platform.value("AmexCard_CVV"));
                break;
            case "PAYTM":
                Enter_CC_Details(driver, "4761360073426701","Jan (01)","2025","123");
                break;
            case "AMEXTRAIN":
                Enter_CC_Details(driver, platform.value("AmexCard_Number"), platform.value("AmexCard_Month_New"), platform.value("AmexCard_Year"), platform.value("AmexCard_CVV"));
                break;
            case "CAPTCHA":
                Enter_CC_Details(driver, "512345678901234", platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
                break;
            case "CHECKOUT":
                Enter_CC_Details(driver, platform.value("SACheckOut_Number"), platform.value("SACheckOut_Month_UI"), platform.value("SACheckOut_Year"), platform.value("SACheckOut_CVV"));
                break;
            case "PAYFORT":
                Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
                break;
            case "NOON":
                Enter_CC_Details(driver, platform.value("Noon_Number"), platform.value("Noon_Month_UI"), platform.value("Noon_Year"), platform.value("Noon_CVV"));
                break;
            case "RAZORPAY":
                Enter_CC_Details(driver, platform.value("RazorPay_Number"), platform.value("RazorPay_Month_UI"), platform.value("RazorPay_Year"), platform.value("RazorPay_CVV"));
                break;
            case "INVALID":
                Enter_CC_Details(driver, "1212121212121212", platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
                break;
            case "RAZORPAYDC":
                Enter_CC_Details(driver, platform.value("RazorPay_Number_DC"), platform.value("RazorPay_Month_UI_DC"), platform.value("RazorPay_Year_DC"), platform.value("RazorPay_CVV_DC"));
                break;
        }
        if(common.value("Bento_Payment").equalsIgnoreCase("true")||BookingType.contains("TRAINS")) {
            safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));

            Reporter.log("Make Payment button is Clicked");
            if(textPresent(driver, "Internal server error", 5)) {
                Reporter.log("Internal server error is displayed after Clicking Make Payment");
                Assert.assertTrue(false);
            }
            if(textPresent(driver, "Hmm, something's not right", 1)) {
                Reporter.log("Hmm, something's not right. is displayed after Clicking Make Payment");
                Assert.assertTrue(false);
            }

            if(textPresent(driver, "Error in credentials entered. Verify your details and try again", 1)) {
                Reporter.log("Error in credentials entered. Verify your details and try again is displayed after Clicking Make Payment");
                Assert.assertTrue(false);
            }
            if(!BankName.contains("CAPTCHA")) {
                payUI_BankPage(driver, BankName);
            }
        }
    }

    public void payUI_Select_DC(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {
        elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 5);
        textPresent_Log(driver, "Enter your debit card details.", 1);
        switch (BankName) {
            case "MASTER":
                Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
                break;
            case "RAZORPAYDC":
                Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
                break;
        }

        if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
            safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
            Reporter.log("Make Payment button is Clicked");
            if(textPresent(driver, "Internal server error", 5)) {
                Reporter.log("Internal server error is displayed after Clicking Make Payment");
                Assert.assertTrue(false);
            }
            if(!BankName.contains("CAPTCHA")) {
                payUI_BankPage(driver, BankName);
            }
        }
    }

    public void payUI_Select_KNET(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {
        elementVisible(driver, getObjectPayment("PaymentPage_Knet_RadioButton"), 5);
        safeClick(driver, getObjectPayment("PaymentPage_Knet_RadioButton"));
        elementPresent_log(driver, getObjectPayment("PaymentPage_Knet_Image"), "", 1);

        if(common.value("Bento_Payment").equalsIgnoreCase("true")|| BookingType.contains("TRAINS")) {
            safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
            Reporter.log("Make Payment button is Clicked");
        }

    }

    public void payUI_BankPage(RemoteWebDriver driver, String BankName) throws Exception {
        elementNotVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 10);
        if(BankName.equalsIgnoreCase("MASTER")) {
            if (textPresent(driver, "AXIS SIMULATOR", 10)|| textPresent(driver, "CYBER SIMULATOR", 10)) {
                Reporter.log("PayU OTP page is displayed");
                smartType(driver, By.id("password"), "123456");
                smartClick(driver, By.id("submitBtn"));
            }
            else if (!textPresent(driver,"Payment successful", 1)){
                Reporter.log("Payment is not sucessfull");
                Assert.assertTrue(false);
            }
        }else if(BankName.equalsIgnoreCase("AMEX")||BankName.equalsIgnoreCase("AMEXTRAIN")) {
            elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"), "Amex Bank ", 20);
            textPresent(driver, "ACS Emulator", 1);
            Reporter.log("Amex Auth page is displayed");
            safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"));
        }else if(BankName.equalsIgnoreCase("Citibank")||BankName.equalsIgnoreCase("CitibankPopular")) {
            elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), 20);
            elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), "Citi Bank  ", 1);
            Reporter.log("CitiBank Auth page is displayed");
            safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
        }else if(BankName.equalsIgnoreCase("Axis Bank")||BankName.equalsIgnoreCase("AxisBankPopular")) {
            elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), 20);
            elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), "Citi Bank  ", 1);
            Reporter.log("CitiBank Auth page is displayed");
            safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
        }

        else if(BankName.equalsIgnoreCase("Hdfc Bank")) {
            elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), 20);
            elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), "Tech Process Bank ", 1);
            Reporter.log("HDFCBank Auth page is displayed");
            safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), "test");
            safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Password"), "test");
            safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn"));
            Thread.sleep(5000);
            elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_IntermitentText"), 5);
            safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn2"));
        }else if(BankName.equalsIgnoreCase("ICICI Bank")) {
            elementVisible(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Logo"), 20);
            elementPresent_log(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Logo"), "Razorpay Bank ", 1);
            textPresent(driver, "Welcome to Razorpay Bank", 1);
            Reporter.log("RazorPay Auth page is displayed");
            safeClick(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Submit"));
        }
        else if(BankName.equalsIgnoreCase("CAPTCHA")) {
            elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), 20);
            elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), "Citi Bank ", 1);
            Reporter.log("CitiBank Auth page is displayed");
            safeSelect(driver, By.cssSelector("select[name=\"PAID\"]"), "N");
            safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
        }else if(BankName.equalsIgnoreCase("Knet")) {
            textPresent_Log(driver, "Cleartrip Mea Fz Llc", 30);
            elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Knet_DropDown"), "Knet Bank ", 2);
            Reporter.log("Knet Bank page is displayed");
            safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_DropDown"), "Knet Test Card [KNET1]");
            Thread.sleep(5000);
            elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CardNumber"), 5);
            safeType(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CardNumber"), "0000000001");
            safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Month"), "09");
            safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Year"), "2021");
            safeType(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CVV"), "1234");
            safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Proceed"));
            textPresent_Log(driver, "Billing Information", 20);

            safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Confirm"));
            textPresent(driver, "If You Are Not Redirected Automatically In 30 Seconds", 10);
            smartClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_RedirectionPage"));
        }else if(BankName.equalsIgnoreCase("PhonePE")) {
            if(!(textPresent(driver, "9986696785", 10)||elementPresent_log(driver, By.id("mobileNumber"), "PhonePe homepage", 30))) {
                Reporter.log("phonepe page is not displayed");
                Assert.assertTrue(false);
            }
            Reporter.log("PhonePE page is displayed");
        }else if(BankName.equalsIgnoreCase("AmazonPay")) {
			/*elementPresent_log(driver, By.id("mobileNumber"), "PhonePe homepage", 30);
			Reporter.log("PhonePE page is displayed");*/
        }
        else if(BankName.equalsIgnoreCase("Checkout")) {
            elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), 30);
            elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Logo"), 20);
            Thread.sleep(2000);
            safeType(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), "Checkout1!");
            Thread.sleep(2000);
            safeClick(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Continue_Btn"));
        }
        else if(BankName.equalsIgnoreCase("PayFort")) {
            elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), 30);
            elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Logo"), 20);
            Thread.sleep(2000);
            safeType(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), "Checkout1!");
            Thread.sleep(2000);
            safeClick(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Continue_Btn"));
        }
        else if(BankName.equalsIgnoreCase("Noon")) {
            elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Submit_Btn"), 10);
            textPresent(driver, "Please submit your Verified by Visa password", 1);
            elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Password_TxtBx"), "Noon pay redirection ", 1);
            safeType(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Password_TxtBx"),"1234");
            safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Submit_Btn"));
        }
        else if(BankName.equalsIgnoreCase("RazorPay")) {
            safeClick(driver, getObjectPayment("MakePayment_Pay_Btn_CC"));
            textPresent_Log(driver, "Welcome to Razorpay Bank", 30);
            elementVisible(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Logo"), 1);
            safeClick(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Submit"));
        }
    }

    public void payUI_Select_NB(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {
        elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 5);
        textPresent_Log(driver, "Popular banks", 1);
        //textPresent_Log(driver, "", 1);
        if(BankName.contains("CAPTCHA")) {
            safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), "Citibank");
        }
        else if(BankName.contains("CitibankPopular")) {
            safeClick(driver, getObjectPayment("PaymentPage_NB_PopularBanks_Citi"));
        }
        else if(BankName.contains("AxisbankPopular")) {
            safeClick(driver, getObjectPayment("PaymentPage_NB_PopularBanks_Axis"));
        }
        else {
            safeClick(driver, getObjectPayment("PayUI_NB_DropDown"));
            Thread.sleep(5000);
            safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), BankName);

        }

        if(common.value("Bento_Payment").equalsIgnoreCase("true")||BookingType.contains("TRAINS")) {
            safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
            payUI_BankPage(driver, BankName);
        }

    }

    public void payUI_Select_UPI(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {

        textPresent(driver, "Select UPI partner to make your payment", 5);
        elementVisible(driver, getObjectPayment("SelectPayment_UPI_PhonePe"), 5);
        safeClick(driver, getObjectPayment("SelectPayment_UPI_PhonePe"));

        if(common.value("Bento_Payment").equalsIgnoreCase("true")||BookingType.contains("TRAINS")) {
            safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
            payUI_BankPage(driver, BankName);
        }
    }

}