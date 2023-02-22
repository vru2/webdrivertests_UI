package test.java.commonUI;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

public class PaymentsUI_Common_PWA extends PaymentsUI_Common {


        public void bento_Paymentpage_PWA(RemoteWebDriver driver, String paymentType, String cardNumber, String domain, String payType, String bankName, String successFail) throws Exception {
        elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Button"), 20);
        textPresent(driver, "Per traveller convenience fee of", 10);
        System.out.println(driver.getCurrentUrl());
        Reporter.log(driver.getCurrentUrl());
        if (paymentType.equalsIgnoreCase("WALLET") || payType.equalsIgnoreCase("PartialWallet")) {
            safeClick(driver, getObjectPayment("PWA_PaymentPage_Wallet_Toggle"));
            Reporter.log("Selected wallet");
            Thread.sleep(2000);
        }
        //payUI_Select_PaymentType_PWA( driver, paymentType);
        switch (paymentType) {
            case "CC":
                bento_pay_CC_PWA(driver, paymentType, cardNumber, domain, payType, bankName, successFail);
                break;
            case "DC":
                bento_pay_CC_PWA(driver, paymentType, cardNumber, domain, payType, bankName, successFail);
                break;
            case "NB":
                bento_pay_NB_PWA(driver, paymentType, cardNumber, domain, payType, bankName, successFail);
                break;
            case "SAVEDPAYMENT":
                bento_pay_SavedPayment_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "EMI":
                bento_pay_EMI_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "TW":
                bento_pay_TW_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "PAYLATER":
                bento_pay_PAYLATER_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "CARDLESSEMI":
                bento_pay_CARDLESSEMI_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "WALLET":
                bento_pay_Wallet_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "Phonepe":
                bento_pay_PhonePe_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "UPI":
                bento_pay_UPI_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "GV":
                bento_pay_GV_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "GV_Partial":
                bento_pay_GV_Partial_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "partial_wallet":
                bento_pay_PartialWallet_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
            case "VALIDCOUPON":
                bento_pay_Coupon_PWA(driver, paymentType, cardNumber, domain, payType, bankName, successFail);
                break;
            case "INVALIDCOUPON":
                bento_pay_Coupon_PWA(driver, paymentType, cardNumber, domain, payType, bankName, successFail);
                break;
            case "OTH":
                bento_pay_Others_PWA(driver, paymentType, cardNumber, domain, payType, bankName);
                break;
        }
        bento_pay_GW_Page_PWA(driver, paymentType, cardNumber, domain, payType, bankName, successFail);
    }

        public void bento_Air_ConfirmationPage_PWA(RemoteWebDriver driver, String paymentType, String payType, String bookingType) throws Exception {
        elementVisible(driver, getObjectPayment("PWA_Air_Confirmation_Page_TripID"), 30);
        if (textPresent(driver, "Something went wrong", 1)) {
            Reporter.log("Something went wrong text is present in confirmation page");
            Assert.assertTrue(false);
        }
        textPresent_Log(driver, "Booking confirmed", 5);
        String tripID = getText(driver, getObjectPayment("PWA_Air_Confirmation_Page_TripID"));
        Reporter.log(bookingType +": "+ tripID);
        System.out.println(bookingType +": "+ tripID);
    }

        public void bento_pay_SavedPayment_PWA(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType_PWA(driver, "SC");
        //textPresent_Log(driver, "PREFERRED PAYMENT MODES", 5);
        if (PayType.equalsIgnoreCase("SavedCard")) {
/*
            elementPresent_log(driver, By.id("Shape"), "bank Logo", 1);
            elementPresent_log(driver, By.cssSelector("rect"), "Card logo", 1);*/
 /*           String CardName = getText(driver, By.xpath("//div[2]/div/p[2]"));
            if (!CardName.equalsIgnoreCase("Paytm Debit Card")) {
                Reporter.log("Card name " + CardName);
                Assert.assertTrue(false);
            }*/
            safeClick(driver, By.xpath("//div[9]/label/div[2]/span"));
            safeClick(driver, By.xpath("//div/div/input"));
            safeType(driver, By.xpath("//div/div/input"), "123");

        }


        else if(PayType.equalsIgnoreCase("SavedUPI")) {
            safeClick(driver, By.xpath("//div[3]/label/div/span"));
            String UPI_txt = getText(driver, By.xpath("//div[2]/span"));
            if (!UPI_txt.equalsIgnoreCase("3212467@okhdfcbank")) {
                Reporter.log("UPI_txt " + UPI_txt);
                Assert.assertTrue(false);
            }
        }

        safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"));
    }

        public void bento_pay_StoredCard_UPI_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType_PWA( driver, PaymentType);

    }

        public void bento_pay_EMI_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType_PWA( driver, PaymentType);
        if(PaymentType.equalsIgnoreCase("EMI")){
            safeClick(driver, By.xpath("//div[2]/input"));
            safeClick(driver, By.xpath("//button"));
            elementVisible(driver, By.xpath("//div/header/div/p"), 5);
            elementVisible(driver, By.xpath("//button"), 5);
        }
    }

        public void bento_pay_NB_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName, String SuccessFail) throws Exception {
        payUI_Select_PaymentType_PWA( driver, PaymentType);
        Reporter.log("Clicked on NB");
        Thread.sleep(1000);
        safeClick(driver, getObjectPayment("PWA_PaymentPage_NB_ICICI"));
        Reporter.log("Selected ICICI Bank");
        safeClick(driver, getObjectPayment("PWA_PaymentPage_Pay_Button"));
		/*textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 20);
		safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"));
		Reporter.log("Payment done successfully");*/
        bento_pay_GW_Page_PWA(driver, "","","","","",SuccessFail);
    }

        public void bento_pay_Coupon_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName, String SuccessFail) throws Exception {
        payUI_Select_PaymentType_PWA( driver, "NB");
        Reporter.log("Clicked on NB");
        bento_pay_validate_Price_Popup(driver, "Coupon code", "");
		/*Thread.sleep(1000);
		safeClick(driver, By.cssSelector("svg.mx-2"));
		textPresent_Log(driver, "Coupon code", 5);
		Thread.sleep(1000);
		mouseHover(driver, By.cssSelector("svg.c-pointer"));
		safeClick(driver, By.cssSelector("svg.c-pointer"));
		Thread.sleep(1000);*/
        bento_pay_NB_PWA(driver, "NB", "", domain, "", "", SuccessFail);
        //bento_pay_CC_PWA(driver, "NB", "", domain, "", "", SuccessFail);
        if(PaymentType.equalsIgnoreCase("VALIDCOUPON")){
            textPresent_Log(driver, "Coupon not applicable", 5);
            textPresent_Log(driver, "Coupon code PAYCC is not applicable on the selected payment mode. Please select another payment mode to get an instant discount of", 5);
            safeClick(driver, By.xpath("//div/div[3]/button")); // Change Payment mode

            bento_pay_validate_Price_Popup(driver, "Coupon code", "");
			/*Thread.sleep(1000);
			mouseHover(driver, By.cssSelector("svg.mx-2"));
			safeClick(driver, By.cssSelector("svg.mx-2"));
			Thread.sleep(1000);
			smartClick(driver, By.cssSelector("svg.mx-2"));
			Thread.sleep(1000);
			textPresent_Log(driver, "Coupon code", 5);
			Thread.sleep(1000);
			mouseHover(driver, By.cssSelector("svg.c-pointer"));
			safeClick(driver, By.cssSelector("svg.c-pointer"));*/
            bento_pay_CC_PWA(driver, "CC", "", domain, "", BankName, "");
        }
        else if(PaymentType.equalsIgnoreCase("INVALIDCOUPON")){
            textPresent_Log(driver, "Coupon not applicable", 5);
            textPresent_Log(driver, "Coupon code PAYCC is not applicable on the selected payment mode. Please select another payment mode to get an instant discount of", 5);
            safeClick(driver, By.xpath("//button[2]")); // Continue witout coupon mode
            Thread.sleep(1000);
        }
        else if(PaymentType.equalsIgnoreCase("COUPON")){

        }
    }

        public void bento_pay_validate_Price_Popup(RemoteWebDriver driver, String PriceType, String PriceType2) throws Exception {


        Thread.sleep(1000);
        mouseHover(driver, By.cssSelector("svg.mx-2"));
        safeClick(driver, By.cssSelector("svg.mx-2"));
        Thread.sleep(1000);
        smartClick(driver, By.cssSelector("svg.mx-2"));
        textPresent_Log(driver, PriceType, 5);
        textPresent_Log(driver, PriceType2, 1);
        Thread.sleep(1000);
        mouseHover(driver, By.cssSelector("svg.c-pointer"));
        safeClick(driver, By.cssSelector("svg.c-pointer"));
    }
        public void bento_pay_TW_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType_PWA(driver, PaymentType);
    }


        public void bento_pay_PAYLATER_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType_PWA(driver, PaymentType);
        elementVisible(driver, By.xpath("//div[2]/div/input"), 5);
        String ICICIBank_text = getText(driver, By.xpath("//div/div[2]/div/div[2]"));
        if(!ICICIBank_text.equalsIgnoreCase("ICICI Bank PayLater")){
            Reporter.log(" Icici bank text "+ICICIBank_text);
            Assert.assertTrue(false);
        }
        safeClick(driver, By.xpath("//div[2]/div/input"));
        safeClick(driver, getObjectPayment("PWA_PaymentPage_Pay_Button"));
        elementVisible(driver, By.id("submit-action"), 20);
        safeType(driver, By.xpath("//input"), "123456");
        safeClick(driver, By.id("submit-action"));
    }

        public void bento_pay_CARDLESSEMI_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType_PWA(driver, PaymentType);
        elementVisible(driver, By.xpath("//div/div[2]/div[2]/div"), 10);
        String Axio_text = getText(driver, By.xpath("//div/div[2]/div[2]/div"));
        if(!Axio_text.equalsIgnoreCase("AXIO")){
            Reporter.log(" Axio text "+Axio_text);
            Assert.assertTrue(false);
        }
        safeClick(driver, By.xpath("//div[2]/div/input"));

        safeClick(driver, getObjectPayment("PWA_PaymentPage_Pay_Button"));
        elementVisible(driver, By.cssSelector("button.success"), 20);
        safeClick(driver, By.cssSelector("button.success"));
    }

        public void bento_pay_Wallet_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        //payUI_Select_PaymentType_PWA( driver, PaymentType);
        textPresent_Log(driver, "CLEARTRIP WALLET", 5);
        textPresent_Log(driver, "from wallet", 1);
        Thread.sleep(2000);
        bento_pay_validate_Price_Popup(driver, "Cleartrip Wallet", "");
        safeClick(driver, getObjectPayment("PWA_PaymentPage_Pay_Button"));
    }

        public void bento_pay_PhonePe_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType_PWA( driver, PaymentType);

    }

        public void bento_pay_UPI_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        scrollSmooth(driver, 200);
        payUI_Select_PaymentType_PWA( driver, PaymentType);
        if(PaymentType.equalsIgnoreCase("Inline")){
            textPresent_Log(driver, "Add new UPI address", 10);
            safeType(driver, getObjectPayment("PWA_PaymentPage_UPI_TextBox"),"kiran@ybl");
        }
        else if(PaymentType.equalsIgnoreCase("SavedUPI")) {
            textPresent_Log(driver, "Add New UPI", 10);
            elementPresent_log(driver, By.cssSelector("rect"), "UPI bank image", 5);
            String savedUPI_Txt = getText(driver, By.xpath("//div[2]/span"));
            if(!savedUPI_Txt.equalsIgnoreCase("3212467@okhdfcbank")){
                Reporter.log("Saved UPI 3212467@okhdfcbank: "+savedUPI_Txt);
                Assert.assertTrue(false);
            }
        }

        smartType(driver, getObjectPayment("PWA_PaymentPage_UPI_TextBox"),"kiran@ybl");
        Thread.sleep(5000);
        safeClick(driver, getObjectPayment("PWA_PaymentPage_Pay_Button"));
    }

        public void bento_pay_GV_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        String Total_Price = getText(driver, By.xpath("//li/div/div/p"));
        if(!Total_Price.equalsIgnoreCase("₹0")){
            Reporter.log("Total Price :"+Total_Price);
            Assert.assertTrue(false);
        }
        safeClick(driver, getObjectPayment("PWA_PaymentPage_Pay_Button"));
    }

        public void bento_pay_GV_Partial_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType_PWA( driver, PaymentType);
        if(PayType.equalsIgnoreCase("PartialWallet")){
            bento_pay_validate_Price_Popup(driver, "Cleartrip Wallet", "Gift card");
        }
        else bento_pay_validate_Price_Popup(driver, "", "Gift card");
        bento_pay_CC_PWA(driver, "CC", CardNumber, domain, PayType, BankName,"");
    }

        public void bento_pay_PartialWallet_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
        payUI_Select_PaymentType_PWA( driver, PaymentType);

    }

        public void bento_pay_CC_PWA(RemoteWebDriver driver, String paymentType,String cardNumber,String domain,String payType, String bankName, String successFail) throws Exception {

        payUI_Select_PaymentType_PWA( driver, paymentType);
        if(bankName=="Paytm") {
            Enter_CC_Details_PWA(driver, "6080322940807777", "1224", "123");
        }
        if(bankName=="MASTER") {
            Enter_CC_Details_PWA(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_EXP_PWA"), platform.value("MasterCard_CVV"));
        }
        if(payType=="GV") {
            bento_pay_validate_Price_Popup(driver, "Gift card", "");
        }
        safeClick(driver, getObjectPayment("PWA_PaymentPage_Pay_Button"));
    }

        public void bento_pay_GW_Page_PWA(RemoteWebDriver driver, String paymentType,String cardNumber,String domain,String payType, String bankName, String SuccessFail) throws Exception {
        if(bankName.equalsIgnoreCase("Paytm")){
            elementVisible(driver, getObjectPayment("PWA_Payments_GW_PayTM_Success"), 20);
            textPresent(driver, "Bank Demo", 10);
            safeClick(driver, getObjectPayment("PWA_Payments_GW_PayTM_Success"));
        }
        else if(bankName.equalsIgnoreCase("RazorpayNB")){
            if(SuccessFail.equalsIgnoreCase("Failure")){

            }
            else if(SuccessFail.equalsIgnoreCase("Success")){
                textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 20);
                safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"));
                Reporter.log("Payment done successfully");
            }
        }
    }

        public void bento_pay_SC_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

    }

        public void bento_pay_Others_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

    }

        public void payUI_Enter_PaymentDetails_PWA(RemoteWebDriver driver, String PayType, String BookingType, String BankName) throws Exception {
        switch (PayType) {
            case "CC":
                payUI_Select_CC_PWA(driver, BankName, BookingType);
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
        }
    }

        public void payUI_Select_CC_PWA(RemoteWebDriver driver,  String BookingType, String BankName) throws Exception {
        elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 5);
        textPresent(driver, "Enter card details", 1);
        switch (BankName) {
            case "MASTER":
                Enter_CC_Details_PWA(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_CVV"));
                break;
            case "RAZORPAYDC":
                Enter_CC_Details_PWA(driver, platform.value("PayTMCard_Number"), platform.value("PayTMCard_MWeb_Exp_Date"), platform.value("PayTMCard_CVV"));
                break;
        }
    }

        public void Enter_CC_Details_PWA(RemoteWebDriver driver, String CCNumber, String CCExp, String CVV) throws Exception {
        Reporter.log("Card Details +\n"+ CCNumber +"\n " + CCExp +" " + CVV);
        elementVisible(driver, getObjectPayment("PWA_Payments_CC_Number"), 5);
        safeType(driver, getObjectPayment("PWA_Payments_CC_Number"), CCNumber);
        safeType(driver, getObjectPayment("PWA_Payments_CC_Name"), "test");
        safeClick(driver, getObjectPayment("PWA_Payments_CC_Exp"));
        safeType(driver, getObjectPayment("PWA_Payments_CC_Exp"), CCExp);
        safeType(driver, getObjectPayment("PWA_Payments_CC_CVV"), CVV);
    }

        public void payUI_Select_PaymentType_PWA(RemoteWebDriver driver, String PayType) throws Exception {
        for (int i = 0; i < 10; i++) {
            if(textPresent(driver, "System error", 1)) {
                Reporter.log("There's something wrong with our system");
                Assert.assertTrue(false);
            } else if(textPresent(driver, "Oops, Something went wrong", 1)) {
                Reporter.log("Oops something wrong with our system");
                Assert.assertTrue(false);
            }
            if(elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs_PWA"), 1)) {
                break;
            }
        }
        if(!elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs_PWA"), 10)) {
            Reporter.log("PayUI Page is not displayed");
            Assert.assertTrue(false);
        }
        switch (PayType) {
            case "CC":
                PayType = "DEBIT/CREDIT CARDS";
                break;
            case "DC":
                PayType = "ADD NEW CARD";
                break;
            case "NB":
                PayType = "NET BANKING";
                break;
            case "EMI":
                PayType = "EMI";
                break;
            case "TW":
                PayType = "WALLETS";
                break;
            case "UPI":
                PayType = "UPI";
                break;
            case "SC":
                PayType = "PREFERRED PAYMENT MODES";
                break;
            case "PAYLATER":
                PayType = "PAY LATER";
                break;
            case "CARDLESSEMI":
                PayType = "CARDLESS EMI";
                break;
            default:
                PayType = "DEBIT/CREDIT CARDS";
                break;
        }
        if(PayType.equalsIgnoreCase("UPI")){
            scrollSmooth(driver, 200);
        }
        safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs_PWA"), PayType);
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