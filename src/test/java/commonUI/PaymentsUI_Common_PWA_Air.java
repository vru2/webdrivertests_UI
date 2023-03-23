package test.java.commonUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class PaymentsUI_Common_PWA_Air extends PaymentsUI_Common_PWA{


    public void air_SRPPage(RemoteWebDriver driver, String FlightName, String FlightNo) throws Exception {
        if (!elementVisible(driver, getObjectPayment("PWA_Air_SRP_Book_btn"), 20)) {
            Reporter.log("SRP page not loaded");
            Assert.assertTrue(false);
        }
        textPresent(driver, "Sort", 5);
        smartClick(driver, getObjectPayment("PWA_Air_SRP_Multi_Flights"));
        safeClick(driver, getObjectPayment("PWA_Air_SRP_Book_btn"));
    }

    public void air_ItnPage(RemoteWebDriver driver, String CouponGV, String CouponCode, String param1, String param2) throws Exception {
        elementVisible(driver, getObjectPayment("PWA_Air_Itn_Coupon_Txt"), 10);
        textPresent(driver, "Review Itinerary", 1);
        if (!elementVisible(driver, By.xpath("//button"), 10)) {
            Reporter.log("Itn page not loaded");
            Assert.assertTrue(false);
        }
        smartClick(driver, By.xpath("//div[3]/div/div/div"));

        if(elementVisible(driver, By.xpath("//div[@id='onetrust-close-btn-container']/button"),1)) {
            mouseHover(driver, By.xpath("//div[@id='onetrust-close-btn-container']/button"));
            safeClick(driver, By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        }
        if(CouponGV.equalsIgnoreCase("COUPON")){
            safeType(driver, getObjectPayment("PWA_Air_Itn_Coupon_Txt"),CouponCode);
            safeClick(driver, getObjectPayment("PWA_Air_Itn_Coupon_Apply_Btn"));
            textPresent_Log(driver, "Great! You just saved", 5);
        }
        else if(CouponGV.equalsIgnoreCase("GV")){
            String[] GV = getGV(10);
            safeType(driver, getObjectPayment("PWA_Air_Itn_GV_Number_Txt"),GV[0]);
            safeType(driver, getObjectPayment("PWA_Air_Itn_GV_Pin_Txt"),GV[1]);
            safeClick(driver, getObjectPayment("PWA_Air_Itn_GV_Apply_Btn"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
        }
        else if(CouponGV.equalsIgnoreCase("GVFULL")){
            String[] GV = getGV(10000);
            safeType(driver, getObjectPayment("PWA_Air_Itn_GV_Number_Txt"),GV[0]);
            safeType(driver, getObjectPayment("PWA_Air_Itn_GV_Pin_Txt"),GV[1]);
            safeClick(driver, getObjectPayment("PWA_Air_Itn_GV_Apply_Btn"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
        }
        else if(CouponGV.equalsIgnoreCase("GVMulti")){
            String[] GV = getGV(10);
            safeType(driver, getObjectPayment("PWA_Air_Itn_GV_Number_Txt"),GV[0]);
            safeType(driver, getObjectPayment("PWA_Air_Itn_GV_Pin_Txt"),GV[1]);
            safeClick(driver, getObjectPayment("PWA_Air_Itn_GV_Apply_Btn"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
            GV = getGV(10);
            safeClick(driver, getObjectPayment("PWA_Air_Itn_GV_Add_New_Btn"));

            safeType(driver, getObjectPayment("PWA_Air_Itn_GV_Number_Txt2"),GV[0]);
            safeType(driver, getObjectPayment("PWA_Air_Itn_GV_Pin_Txt2"),GV[1]);
            safeClick(driver, getObjectPayment("PWA_Air_Itn_GV_Apply_Btn2"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
        }
        //textPresent_Log(driver, "Review Itinerary", 10);

        elementVisible(driver, getObjectPayment("PWA_Air_Itn_Button2"), 20);
        mouseHover(driver, getObjectPayment("PWA_Air_Itn_Button2"));
        safeClick(driver, getObjectPayment("PWA_Air_Itn_Button2"));
        if(elementVisible(driver, By.cssSelector("div.Overlay"), 1)){
            smartClick(driver, By.linkText("Continue"));
        }
    }

    public void air_AddOnPage(RemoteWebDriver driver, String param1, String param2) throws Exception {
        if (textPresent(driver, "Skip", 1)) {
            mouseHover(driver, getObjectPayment("PWA_Air_Addons_SkipPage_Skip_Txt"));
            safeClick(driver, getObjectPayment("PWA_Air_Addons_SkipPage_Skip_Txt"));
            elementVisible(driver, getObjectPayment("PWA_Air_Addons_SkipPage_Popup_Txt"), 5);
            safeClick(driver, getObjectPayment("PWA_Air_Addons_SkipPage_Popup_Txt"));
        }
        if( !elementVisible(driver, getObjectPayment("PWA_Air_TravellerPage_Title"), 1)){
        elementVisible(driver, getObjectPayment("PWA_Air_Addons_Txt"), 1);
        if(!textPresent(driver, "Add ons", 1)) {
            if (textPresent(driver, "Review Itinerary", 2)) {
                mouseHover(driver, getObjectPayment("PWA_Air_Itn_Button2"));
                safeClick(driver, getObjectPayment("PWA_Air_Itn_Button2"));
            }
        }
        if(!textPresent(driver, "Review Travellers", 2)) {
            if (textPresent(driver, "Skip", 5)) {
                mouseHover(driver, getObjectPayment("PWA_Air_Addons_SkipPage_Skip_Txt"));
                safeClick(driver, getObjectPayment("PWA_Air_Addons_SkipPage_Skip_Txt"));
                elementVisible(driver, getObjectPayment("PWA_Air_Addons_SkipPage_Popup_Txt"), 5);
                safeClick(driver, getObjectPayment("PWA_Air_Addons_SkipPage_Popup_Txt"));
            }
        }
        if(textPresent(driver, "Review Itinerary", 2)) {
            mouseHover(driver, getObjectPayment("PWA_Air_Itn_Button2"));
            safeClick(driver, getObjectPayment("PWA_Air_Itn_Button2"));
        }
        }
    }

    public void air_TravellerPage(RemoteWebDriver driver, String Login, String phoneNo, String emailID, String Param) throws Exception {
        textPresent_Log(driver, "Review Travellers", 20);
        safeSelect(driver, getObjectPayment("PWA_Air_TravellerPage_Title"),"Mr");
        safeType(driver, getObjectPayment("PWA_Air_TravellerPage_FName"),"Kiran");
        safeType(driver, getObjectPayment("PWA_Air_TravellerPage_LName"),"Kumar");
        if(elementVisible(driver, getObjectPayment("PWA_Air_TravellerPage_Nationality"),1)){
            String Nationality_DOB = getText(driver, getObjectPayment("PWA_Air_TravellerPage_Nationality"));
            if(Nationality_DOB.contains("Nationality")){
                safeClick(driver, getObjectPayment("PWA_Air_TravellerPage_Nationality"));
                textPresent(driver, "Select Country", 5);
                Thread.sleep(1000);
                safeType(driver, getObjectPayment("PWA_Air_TravellerPage_Nationality_Popup_Txt"), "India");
                Thread.sleep(1000);
                mouseHover(driver, getObjectPayment("PWA_Air_TravellerPage_Nationality_Popup_DropDown"));
                Thread.sleep(1000);
                safeClick(driver, getObjectPayment("PWA_Air_TravellerPage_Nationality_Popup_DropDown"));
            }
            else if(Nationality_DOB.contains("Date of birth")){
                mouseHover(driver, getObjectPayment("PWA_Air_TravellerPage_DOB_Txt"));
                Actions actions = new Actions(driver);
                WebElement elementLocator = driver.findElement(getObjectPayment("PWA_Air_TravellerPage_DOB_Txt"));
                actions.doubleClick(elementLocator).perform();
                mouseHover(driver, getObjectPayment("PWA_Air_TravellerPage_DOB_Txt"));
                safeClick(driver, getObjectPayment("PWA_Air_TravellerPage_DOB_Txt"));
                actions.moveToElement(elementLocator);
                actions.clickAndHold(elementLocator);
                // Thread.sleep(1000);
                actions.sendKeys("04").perform();
                //Thread.sleep(1000);
                actions.sendKeys("12").perform();
                //Thread.sleep(1000);
                actions.sendKeys("2000").perform();
                //  actions.sendKeys("00").perform();
            }
        }
        safeType(driver, getObjectPayment("PWA_Air_TravellerPage_Phone"),phoneNo);
        safeType(driver, getObjectPayment("PWA_Air_TravellerPage_Email"),emailID);
        scrollSmooth(driver, 200);
        elementVisible(driver,getObjectPayment("PWA_Air_Itn_Button2"),2);
        mouseHover(driver, getObjectPayment("PWA_Air_Itn_Button2"));
        safeClick(driver, getObjectPayment("PWA_Air_Itn_Button2"));
        if(elementVisible(driver, By.xpath("//div[3]/a"), 5)){
            smartClick(driver, By.xpath("//div[3]/a"));
        }
        if(elementVisible(driver, getObjectPayment("PWA_Air_TravellerPage_PricePopUP"), 1)){
            Reporter.log("Price popup");
            smartClick(driver, getObjectPayment("PWA_Air_TravellerPage_PricePopUP"));
        }
    }
/*
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
    }*/

    public void bento_Air_ConfirmationPage_PWA(RemoteWebDriver driver, String paymentType, String payType, String bookingType) throws Exception {
        elementVisible(driver, getObjectPayment("PWA_Air_Confirmation_Page_TripID"), 30);
        if (textPresent(driver, "Something went wrong", 1)) {
            Reporter.log("Something went wrong text is present in confirmation page");
            org.junit.Assert.assertTrue(false);
        }
        textPresent_Log(driver, "Booking confirmed", 5);
        String tripID = getText(driver, getObjectPayment("PWA_Air_Confirmation_Page_TripID"));
        Reporter.log(bookingType +": "+ tripID);
        System.out.println(bookingType +": "+ tripID);
    }


}