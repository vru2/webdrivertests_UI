package test.java.commonUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class PaymentsUI_Common_Desktop_Hotels extends PaymentsUI_Common_Desktop {

    public void hotelsDetailsPage(RemoteWebDriver driver, String HotelName, String Price) throws Exception {
        if(textPresent(driver, "Sorry our servers are stumped with your request", 1)) {
            refreshPage(driver);
        }
        elementPresent_log(driver, getObjectPayment("Hotel_Details_Modify_Btn"), "Modify Button", 5);
        safeClick(driver, getObjectPayment("Hotel_Details_SelectRoom_Btn"));
        By selectRoomButton= By.xpath("");
        if(elementVisible(driver, getObjectPayment("Hotel_Details_Book_Btn"), 1)) {
            Thread.sleep(1000);
            selectRoomButton=getObjectPayment("Hotel_Details_Book_Btn");

        }
        else selectRoomButton=getObjectPayment("Hotel_Details_Book_Btn1");
        if(elementVisible(driver, selectRoomButton, 2)) {
            safeClick(driver, selectRoomButton);
        }
        String parent=driver.getWindowHandle();
        Set<String> s=driver.getWindowHandles();
        Iterator<String> I1= s.iterator();
        String Child_URL="";
        while(I1.hasNext())
        {
            String child_window=I1.next();
            if(!parent.equals(child_window))
            {
                driver.switchTo().window(child_window);}
        }
        elementVisible(driver, By.xpath("//div[2]/div/div[2]/div/div/div[2]/div/input"), 10);
        textPresent(driver, "Review your itinerary", 1);
        Child_URL = driver.getCurrentUrl();
        driver.close(); // Closing Child window
        driver.switchTo().window(parent);
        driver.get(Child_URL);
        //elementVisible(driver, getObjectPayment("Hotel_ContactPage_Continue_Btn_New"), 10);
        elementVisible(driver, By.xpath("//div[2]/div/div[2]/div/div/div[2]/div/input"), 10);
        textPresent(driver, "Review your itinerary", 1);
    }

    public void hotelsItnPage(RemoteWebDriver driver, String CouponGV, String PayType, String SignIN, String Contact) throws Exception {
        Thread.sleep(2000);
        refreshPage(driver);
        elementVisible(driver, By.xpath("//div[3]/div/div[2]/div/input"), 20);
        textPresent(driver, "Review your itinerary", 1);
        Thread.sleep(1000);
        if(textPresent(driver, "We are seeing overwhelming traffic. Please try again in sometime",1)){
            Reporter.log("We are seeing overwhelming traffic. Please try again in sometime. error in itinerary");
            Assert.assertTrue(false);
        }
        elementPresent_log(driver, By.xpath("//div[5]/div/div/input"),"", 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        textPresent(driver, "Add contact details", 1);
        Thread.sleep(1000);
        elementVisible(driver, By.xpath("//input"), 5);
        safeType(driver, By.xpath("//input"), "1212121212");
        safeType(driver, By.xpath("//div[5]/div/div/input"), "ctpayment@gmail.com");
        if(CouponGV.contains("GV")) {
            elementVisible(driver, By.xpath("//div[3]/div/div[2]/div/input"), 5);
            textPresent(driver, "Apply coupon or gift card",1);
            mouseHover(driver, By.xpath("//div[3]/div/div[2]/div/input"));
            safeClick(driver, By.xpath("//div[3]/div/div[2]/div/input"));
            if(CouponGV.equalsIgnoreCase("FullGV")) {
                String[] GV = getGVSCLP(10000);
                hotel_Apply_GV(driver, GV[0],GV[1]);
                //	hotel_Apply_GV(driver, GV_number,GV_pin);
            }
            else if(CouponGV.equalsIgnoreCase("PartialGV")){
                String[] GV = getGV(10);
                hotel_Apply_GV(driver, GV[0],GV[1]);
                GV = getGV(10);
                hotel_Apply_GV(driver, GV[0],GV[1]);
            }
            else if(CouponGV.equalsIgnoreCase("PartialGV_SCLP")) {
                String[] GV = getGVSCLP(10);
                hotel_Apply_GV(driver, GV[0],GV[1]);
            }
            else if(CouponGV.equalsIgnoreCase("PartialGV_SCLP_PopUp")) {
                hotel_Apply_GV(driver, PayType,SignIN);
            }

            else if(CouponGV.equalsIgnoreCase("PartialGV_SCLP_CLP")) {
                String[] GV = getGV(10);
                hotel_Apply_GV(driver, GV[0],GV[1]);
                GV = getGVSCLP(10);
                hotel_Apply_GV(driver, GV[0],GV[1]);
            }

        }
        else if(CouponGV.equalsIgnoreCase("COUPONCC")) {
            mouseHover(driver, By.xpath("//div[3]/div/div[2]/div/input"));
            safeClick(driver, By.xpath("//div[3]/div/div[2]/div/input"));
            safeType(driver, By.xpath("//div[3]/div/div[2]/div/input"), "CCHOTEL");
            WebElement element = driver.findElement(By.xpath("//div[2]/button"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            mouseHover(driver, By.xpath("//div[2]/div[3]/div/div/button"));
            safeClick(driver, By.xpath("//div[2]/div[3]/div/div/button"));
            textPresent_Log(driver,"Great! You just saved",5);
        }
        elementVisible(driver,getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"),5);
        js.executeScript("window.scrollBy(0,100)");
        Thread.sleep(500);
        elementVisible(driver, getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"), 2);
        mouseHover(driver, getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"));
        safeClick(driver, getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"));
        Thread.sleep(500);
        safeClick(driver, getObjectPayment("Hotel_ContactPage_Salutation_Mr"));
      //  safeSelectByIndex(driver, getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"), 1);
        safeType(driver, getObjectPayment("Hotel_ContactPage_FirstName_TextBox"), "Kiran");
        safeType(driver, getObjectPayment("Hotel_ContactPage_LastName_TextBox"), "Kumar");
        js.executeScript("window.scrollBy(0,600)");
        Thread.sleep(500);
        safeClick(driver, getObjectPayment("Hotel_ContactPage_Continue_Btn_New"));
    }

    public void hotel_Apply_GV(RemoteWebDriver driver, String GV_Number, String GV_Pin) throws Exception {
        safeType(driver, By.xpath("//div[3]/div/div[2]/div/input"), GV_Number);
        elementVisible(driver, By.xpath("//div[2]/input"), 5);
        mouseHover(driver, By.xpath("//div[2]/input"));
        safeClick(driver, By.xpath("//div[2]/input"));
        safeType(driver, By.xpath("//div[2]/input"), GV_Pin);
        safeClick(driver, By.xpath("//div[2]/div[3]/button"));
        textPresent_Log(driver, "redeemed from your gift card", 10);
    }


    public void bento_Validation_Text(RemoteWebDriver driver, String PaymentType, String Domain) throws Exception {
        if(PaymentType.equalsIgnoreCase("CC")) {
            if(Domain.equals("FLYIN")) {
                elementPresent_log(driver, getObjectPayment("FlyIN_Logo"), "FlyIN_Logo"	, 5);
                String title = driver.getTitle();
                if(!title.contains("Flyin | Pay securely")) {
                    Reporter.log("Page title "+title);
                    org.junit.Assert.assertTrue(false);
                }
            }
            else if(Domain.contains("FLYINAR")) {
                elementPresent_log(driver, getObjectPayment("FlyIN_Logo"), "FlyIN_Logo"	, 5);
                String title = driver.getTitle();
                if(title.contains("Flyin | Pay securely")) {
                    Reporter.log("Page title "+title);
                    org.junit.Assert.assertTrue(false);
                }
            }
            else {
                String title = driver.getTitle();
                if(!title.contains("Cleartrip | Pay securely")) {
                    Reporter.log("Page title "+title);
                    org.junit.Assert.assertTrue(false);
                }
            }
            if(Domain.equals("FLYINAR")) {
               // bento_Select_PaymentType_AR(driver, "CC");
                textPresent_Log(driver, "ادفع لإتمام الحجز", 1);
                textPresent_Log(driver, "ستبدال نقاط قطاف لهذا الحجز", 1);
                textPresent_Log(driver, "بطاقة مدى / البطاقة", 1);
                textPresent_Log(driver, "الدفع بالبطاقة", 1);
                textPresent_Log(driver, "رقم البطاق", 1);
                textPresent_Log(driver, "تاريخ الانتهاء", 1);
                textPresent_Log(driver, "إسم صاحب البطاقة", 1);
                textPresent_Log(driver, "رمز التحقق من البطاقة", 1);
                textPresent_Log(driver, "السعر الإجمالي، شامل جميع الضرائب", 1);
                textPresent_Log(driver, "ر.س.", 1);
                textPresent_Log(driver, "المبلغ المطلوب", 1);
                textPresent_Log(driver, "ملخص الحجز", 1);
                textPresent_Log(driver, "استبدال نقاط قطاف لهذا الحجز", 1);


                textPresent_Log(driver, "استبدل الآن", 1);

                //  price BREAKUP
                textPresent_Log(driver, "المبلغ المطلوب", 1);
                textPresent_Log(driver, "السعر الأساسي (1 مسافر", 1);
                textPresent_Log(driver, "الضرائب والرسوم", 1);
                textPresent_Log(driver, "رسوم أخرى", 1);

                //  booking Summary

                textPresent_Log(driver, "ملخص الحجز", 1);
                textPresent_Log(driver, "1 مسافر", 1);

                //  Terms & conditions
                textPresent_Log(driver, "لسعر الإجمالي، شامل جميع", 1);
                textPresent_Log(driver, "الضرائب", 1);
                textPresent_Log(driver, "© 2006–2021 Saudi Ebreez Company", 1);





                textNotPresent_Log(driver, "You pay", 1);
                textNotPresent_Log(driver, "Base fare (1 traveller)", 1);
                textNotPresent_Log(driver, "Taxes and fees", 1);
                textNotPresent_Log(driver, "Flexifly", 1);
                textNotPresent_Log(driver, "Travel Insurance", 1);

                textNotPresent_Log(driver, "Pay to complete your booking", 1);
                textNotPresent_Log(driver, "Enter card details", 1);
                textNotPresent_Log(driver, "Card number", 1);
                textNotPresent_Log(driver, "Expiry date", 1);
                textNotPresent_Log(driver, "Card holder Name", 1);
                textNotPresent_Log(driver, "Pay to complete your booking", 1);
                //textNotPresent_Log(driver, "CVV", 1);

                //paypal


                safeClick(driver, By.xpath("//nav/div[3]"));
                textPresent_Log(driver, "الدفع بواسطة باي بال", 1);

            }
            else {
                bento_Select_PaymentType(driver, "CC");
                textPresent_Log(driver, "Pay to complete your booking", 5);
                if(!Domain.equals("FLYIN")) {
                    textPresent_Log(driver, "Enter card details", 1);
                }
                else textPresent_Log(driver, "Card Payment", 1);
                textPresent_Log(driver, "Card number", 1);
                textPresent_Log(driver, "Expiry date", 1);
                textPresent_Log(driver, "Card holder Name", 1);
                textPresent_Log(driver, "Pay to complete your booking", 1);
                textPresent_Log(driver, "CVV", 1);
                String CardPlaceHolder = driver.findElement(getObjectPayment("Bento_Pay_CreditCard_Number")).getAttribute("placeholder");
                String NamePlaceHolder = driver.findElement(getObjectPayment("Bento_Pay_CreditCard_Name")).getAttribute("placeholder");
                String CVVPlaceHolder = driver.findElement(getObjectPayment("Bento_Pay_CreditCard_CVV")).getAttribute("placeholder");
                if(!(CardPlaceHolder.equals("Enter card number")&&NamePlaceHolder.equals("Name as on card")&&CVVPlaceHolder.equals("CVV"))) {
                    Reporter.log("PlaceHoder is not correct "+CardPlaceHolder+" "+NamePlaceHolder+" "+CVVPlaceHolder);
                    org.junit.Assert.assertTrue(false);
                }
                textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
                //	textPresent_Log(driver, "and the terms and conditions of Cleartrip", 1);
                textPresent_Log(driver, "Total, inclusive of all taxes", 1);
                textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Pay now", 2);
            }
        }
        else if (PaymentType.equalsIgnoreCase("EMI"))
        {
            Thread.sleep(3000);
            textPresent_Log(driver, "Pay to complete your booking", 20);
          //  driver.manage().addCookie(cookie_Bento_Payment_EMI);
            refreshPage(driver);
			/*if(elementVisible(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"),5))
			{
			 safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
			 Thread.sleep(5000);
			}*/
            bento_Select_PaymentType(driver, "EMI");
            textPresent_Log(driver, "Pay to complete your booking", 1);
            textPresent_Log(driver, "Choose an EMI option", 1);
            textPresent_Log(driver, "Cleartrip does not levy any charges for availing EMI. Charges, if any, are levied by the bank. Please check with your bank for charges related to interest, processing fees, refund or pre-closure", 1);
            textPresent_Log(driver, "Show banks with No Cost EMI", 1);
            textPresent_Log(driver, "Popular banks", 1);
            textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
            textPresent_Log(driver, "Total, inclusive of all taxes", 1);
            //textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Enter card details", 2);
            textPresentInElementAssert(driver, getObjectPayment("PaymentPage_EMI_Pay_Btn"), "Continue", 1);
            //elementPresent_log(driver, getObjectPayment("PaymentPage_EMI_ICICIBank_Radio_Btn"), "EMI radio btn", 1);

        }
        else if (PaymentType.equalsIgnoreCase("QITAF"))
        {
            elementPresent_log(driver, getObjectPayment("PaymentPage_QITAF_Redeem_Link"), "Redeem link", 10);
            elementPresent_log(driver, getObjectPayment("PaymentPage_QITAF_Logo"), "Qitaf logo", 10);
            textPresent_Log(driver, "Redeem Qitaf reward points for this booking.", 1);
            safeClick(driver, getObjectPayment("PaymentPage_QITAF_Redeem_Link"));
            elementPresent_log(driver, getObjectPayment("PaymentPage_QITAF_Mobile_Number_Text"), "Redeem link", 10);
            safeClick(driver, getObjectPayment("PaymentPage_QITAF_Redeem_Link"));  // Clicking on cancel link
            elementNotPresent_Time(driver, getObjectPayment("PaymentPage_QITAF_Mobile_Number_Text"), 5);
            safeClick(driver, getObjectPayment("PaymentPage_QITAF_Redeem_Link"));// Clicking on redeem link
            safeType(driver, getObjectPayment("PaymentPage_QITAF_Mobile_Number_Text"), "9986696785");
            Thread.sleep(2000);
            safeClick(driver, getObjectPayment("PaymentPage_QITAF_Request_Passcode_Btn"));
            elementPresent_log(driver, getObjectPayment("PaymentPage_QITAF_Invalid_Mobile_Error"), "error mesage", 5);
            textPresent_Log(driver, "Powered by STC.", 1);
            textPresent_Log(driver, "Invalid mobile number", 1);
            textPresent_Log(driver, "Registered Qitaf number", 1);
            Thread.sleep(2000);
            safeClick(driver, getObjectPayment("PaymentPage_QITAF_KnowMore_Link"));
            Thread.sleep(5000);
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
            String KnowMoreURL = getURL(driver);
            Reporter.log("Booking Policy URL : "+KnowMoreURL);
            if(Domain.equals("FLYIN")) {
                if(!KnowMoreURL.contains("https://www.flyin.com/qitaf")) {
                    Reporter.log("Know more URL : "+KnowMoreURL);
                    org.junit.Assert.assertTrue(false);
                }
                textPresent_Log(driver, "Redeem your Qitaf points", 5);
            }


        }

        else if(PaymentType.equalsIgnoreCase("NB")) {
            bento_Select_PaymentType(driver, "NB");
            textPresent_Log(driver, "Pay to complete your booking", 1);
            textPresent_Log(driver, "Select a bank", 1);
            textPresent_Log(driver, "Popular banks", 1);
            textPresent_Log(driver, "All other banks", 1);
            textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
            textPresent_Log(driver, "Total, inclusive of all taxes", 1);
            textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Pay now", 2);
        }
        else if(PaymentType.equalsIgnoreCase("TW")) {
            bento_Select_PaymentType(driver, "TW");
            textPresent_Log(driver, "Pay to complete your booking", 1);
            textPresent_Log(driver, "Select a wallet", 1);
            textPresent_Log(driver, "Available wallets", 1);
            textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
            textPresent_Log(driver, "Total, inclusive of all taxes", 1);
            textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Pay now", 2);
        }
        else if(PaymentType.equalsIgnoreCase("UPI")) {
            bento_Select_PaymentType(driver, "UPI");
            textPresent_Log(driver, "Pay to complete your booking", 1);
            textPresent_Log(driver, "Enter UPI ID", 1);
            textPresent_Log(driver, "UPI ID", 1);
            textPresent_Log(driver, "Payment request will be sent to the phone no. linked to your UPI ID", 1);
            textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
            textPresent_Log(driver, "Total, inclusive of all taxes", 1);
            textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Show QR Code", 2);
            String UPIIDPlaceholder = driver.findElement(getObjectPayment("Bento_Pay_UPI")).getAttribute("placeholder");
            if(!(UPIIDPlaceholder.equals("Enter your UPI ID"))) {
                Reporter.log("PlaceHoder is not correct "+UPIIDPlaceholder);
                org.junit.Assert.assertTrue(false);
            }
        }
        else if(PaymentType.equalsIgnoreCase("SC")) {
            driver.manage().addCookie(cookie_Parl_Wallet);
            refreshPage(driver);
            Thread.sleep(5000);
            smartClick(driver, By.cssSelector("span.checkbox__mark.bs-border.bc-neutral-500.bw-1.ba"));
            bento_Select_PaymentType(driver, "SC");
            textPresent_Log(driver, "Pay to complete your booking", 1);
            textPresent_Log(driver, "Select a saved card", 1);
            textPresent_Log(driver, "Saved cards", 1);
            textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
            textPresent_Log(driver, "Total, inclusive of all taxes", 1);
            textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Pay now", 2);
        }
        else if(PaymentType.equalsIgnoreCase("PayPal")) {
            bento_Select_PaymentType(driver, "PayPal");
            textPresent_Log(driver, "Pay to complete your booking" ,1);
            textPresent_Log(driver, "Pay using PayPal" ,1);
            elementPresent_log(driver, getObjectPayment("Bento_Pay_PayPal_RadioBtn"), "paypal radio button image", 1);
        }
        else if(PaymentType.equalsIgnoreCase("ADCB")) {
            bento_Select_PaymentType(driver, "ADCB");
            textPresent_Log(driver, "Pay to complete your booking" ,1);
            textPresent_Log(driver, "Enter ADCB card details" ,1);
            textPresent_Log(driver, "ADCB card number" ,1);
            textPresent_Log(driver, "Expiry date" ,1);
            textPresent_Log(driver, "Card holder Name" ,1);
            textPresent_Log(driver, "CVV" ,1);
        }
        else if(PaymentType.equalsIgnoreCase("PRICE_BREAKUP")) {
            textPresent_Log(driver, "You pay", 1);
            textPresent_Log(driver, "Base fare (1 traveller)", 1);
            textPresent_Log(driver, "Taxes and fees", 1);
            if(Domain.equals("IN")) {
			/*textPresent_Log(driver, "Flexifly", 1);
			textPresent_Log(driver, "Travel Insurance", 1);*/
            }
            if(Domain.equals("FLYIN")) {

                textPresent_Log(driver, "Other fee", 1);
            }
            else textPresent_Log(driver, "Convenience fee", 1);
            //textPresent_Log(driver, "Cleartrip wallet", 1);
            WebElement ele= driver.findElement(getObjectPayment("Bento_Pay_PriceBreakup_ConvFee_Image"));
            Actions action = new Actions(driver);
            action.moveToElement(ele).perform();
            if(Domain.equals("IN")) {
                textPresent_Log(driver, "Includes a non-refundable convenience fee of ₹ 100 per traveller", 1);
                Reporter.log("Includes a non-refundable convenience fee of ₹ 30 per traveller -popup is displayed");
            }
            if(Domain.equals("AE")) {
                textPresent_Log(driver, "Includes a non-refundable convenience fee of AED", 1);
                textPresent_Log(driver, "10 per traveller", 1);

            }
            else if(Domain.equals("FLYIN")) {
                textPresent_Log(driver, "Includes a non-refundable other fee of SAR", 1);
                textPresent_Log(driver, "11 per traveller", 1);

            }


        }

        else if(PaymentType.equalsIgnoreCase("Booking_SUMMARY")) {
            textPresent_Log(driver, "Booking summary" ,1);
            textPresent_Log(driver, "Bangalore", 1);
            textPresent_Log(driver, "(BLR)", 1);
            textPresent_Log(driver, "(HYD)", 1);
            if(Domain.equals("IN")) {
                //textPresent_Log(driver, "09:20, Sat 24 Oct - 11:05, Sat 24 Oct", 1);
                textPresent_Log(driver, "1 traveller", 1);
                textPresent_Log(driver, "(F)", 1);
		/*	textPresent_Log(driver, "Tester Test", 1);
			textPresent_Log(driver, "(F)", 1);*/
            }
            if(Domain.equals("AE")||Domain.equals("KW")||Domain.equals("QA")||Domain.equals("SA")||Domain.equals("OM")||Domain.equals("ME")||Domain.equals("BH")) {
                //textPresent_Log(driver, "13:50, Sat 24 Oct - 15:35, Sat 24 Oct", 1);
                textPresent_Log(driver, "1 traveller", 1);
                textPresent_Log(driver, "(F)", 1);
                //textPresent_Log(driver, "test test (M)", 1);
            }
            if(Domain.endsWith("FLYIN")) {
                textPresent_Log(driver, "© 2006–2022 Saudi Ebreez Company", 1);

            }else textPresent_Log(driver, "© 2006–2022 Cleartrip Pvt. Ltd.", 1);
            textPresent_Log(driver, "Completely safe and secure transactions", 1);
        }
        else if(PaymentType.equalsIgnoreCase("KNET")) {
            bento_Select_PaymentType(driver, "KNET");
            textPresent_Log(driver, "Select a bank", 1);
            elementPresent_log(driver, getObjectPayment("Bento_KNET_Radio_Button"), "Knet Radio button", 1);

        }
        else if(PaymentType.equalsIgnoreCase("Failure_Banner")) {
            textPresent_Log(driver, "UPI payments are having high failure rate, try other payment mode", 1);
            elementPresent_log(driver, By.xpath("//div[@id='root']/div/main/div/div[2]/div/div/div[2]"), "Note", 1);
            elementPresent_log(driver, By.xpath("//div[@id='root']/div/main/div/div[2]/div/div/div/div"), "Text G/W failure", 1);
        }

    }


    public void hotelsPayment_Page_Validation(RemoteWebDriver driver, String PayType, String Domain) throws Exception {
        if(textPresent(driver, "Oops, Something went wrong.", 1)) {
            refreshPage(driver);
        }
        if(textPresent(driver, "Oops, Something went wrong.", 1)) {
            Assert.assertTrue(false);
        }
        if(!(PayType.contains("GV")||PayType.contains("WALLET"))) {
            hotelPrice_PaymentPage=  getText(driver, By.xpath("//p[2]/span")).replace("₹ ", "").replace(",", "");
            int payment_Price=Integer.parseInt(hotelPrice_PaymentPage);
            int itinerary_Price=Integer.parseInt(hotelPrice_Itinerary);
            int itinerary_Price_Plus_ConvFee=itinerary_Price+170; // added conv fee
            System.out.println("Payment page price  :"+payment_Price+" itinerary_Price_Plus_ConvFee : "+itinerary_Price_Plus_ConvFee);
            if(payment_Price!=itinerary_Price_Plus_ConvFee) {
                Reporter.log("Payment page price  :"+payment_Price+" itinerary_Price_Plus_ConvFee : "+itinerary_Price_Plus_ConvFee);
                //System.out.println("Payment page price  :"+payment_Price+" itinerary_Price_Plus_ConvFee : "+itinerary_Price_Plus_ConvFee);
                //Assert.assertTrue(false);
            }
        }
    }

    public void hotelsPaymentPage(RemoteWebDriver driver, String PaymentType, String CardNumber, String Domain, String PayType, String BankName, String TestDetails) throws Exception {
        //hotelsPayment_Page_Validation(driver, PayType, Domain);
        paymentPageHotels(driver, PaymentType, CardNumber, Domain, PayType, BankName, TestDetails);
    }

    public void paymentPageHotels(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName, String TestDetails) throws Exception {
        elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 30);
        refreshPage(driver);
        if (elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 30)) {
            bento_paymentpage(driver, PaymentType, CardNumber, domain, PayType, BankName);
            if (!(CardNumber == "ADCB" || PaymentType == "Phonepe" || PaymentType == "UPIScan" || PayType == "Googlecaptcha"|| PayType == "Failure")) {
                confirmation_page_hotel(driver, PaymentType, CardNumber, TestDetails);
            }
        } else if (textPresent(driver, "Sorry, our servers are stumped with your request", 1) || textPresent(driver, "Flight not available", 1)) {
            Reporter.log("Booking failed due to itn page issue");
            assertTrue(false);
        } else {
            Reporter.log("Booking failed due to itn page issue");
            assertTrue(false);
        }
    }

    public void confirmation_page_hotel(RemoteWebDriver driver, String PaymentType, String CardNumber, String TestDetails) throws Exception {
        if(textPresent(driver, "Oops, your booking didn’t go through", 2)){
            Reporter.log("Oops, your booking didn’t go through ");
            Assert.assertTrue(false);
        }
        elementPresent_log(driver, By.xpath("//div[3]/p"), "TripID", 30);
        textPresent_Log(driver, "Booking successful", 10);
        elementVisible(driver, By.xpath("//div[3]/p"), 10);
        String tripid = getText(driver, By.xpath("//div[3]/p"));
        logURL(driver);
        Reporter.log(TestDetails + tripid);
        System.out.println(TestDetails + tripid);
    }

}