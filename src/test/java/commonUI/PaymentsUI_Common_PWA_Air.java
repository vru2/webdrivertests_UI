package test.java.commonUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class PaymentsUI_Common_PWA_Air extends PaymentsUI_Common_PWA{


    public void air_SRPPage(RemoteWebDriver driver, String FlightName, String FlightNo) throws Exception {
        if (!elementVisible(driver, By.xpath("//ul/div/div/div/div[2]/div[2]"), 10)) {
            Reporter.log("SRP page not loaded");
            Assert.assertTrue(false);
        }
        textPresent(driver, "Sort", 5);
        smartClick(driver, By.xpath("//button/p"));
        safeClick(driver, By.xpath("//ul/div/div/div/div[2]/div[2]"));
    }

    public void air_ItnPage(RemoteWebDriver driver, String CouponGV, String CouponCode, String param1, String param2) throws Exception {
        elementVisible(driver, By.name("coupon"), 10);
        textPresent(driver, "Review Itinerary", 1);
        if (!elementVisible(driver, By.xpath("//button"), 10)) {
            Reporter.log("Itn page not loaded");
            Assert.assertTrue(false);
        }
        if(elementVisible(driver, By.xpath("//div[@id='onetrust-close-btn-container']/button"),1)) {
            mouseHover(driver, By.xpath("//div[@id='onetrust-close-btn-container']/button"));
            safeClick(driver, By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        }
        if(CouponGV.equalsIgnoreCase("COUPON")){
            safeType(driver, By.name("coupon"),CouponCode);
            safeClick(driver, By.xpath("//div[2]/ul/div/li/p"));
            textPresent_Log(driver, "Great! You just saved", 5);
        }
        else if(CouponGV.equalsIgnoreCase("GV")){
            String[] GV = getGV(10);
            safeType(driver, By.xpath("//input"),GV[0]);
            safeType(driver, By.xpath("//li[2]/input"),GV[1]);
            safeClick(driver, By.xpath("//li[2]/p"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
        }
        else if(CouponGV.equalsIgnoreCase("GVFULL")){
            String[] GV = getGV(10000);
            safeType(driver, By.xpath("//input"),GV[0]);
            safeType(driver, By.xpath("//li[2]/input"),GV[1]);
            safeClick(driver, By.xpath("//li[2]/p"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
        }
        else if(CouponGV.equalsIgnoreCase("GVMulti")){
            String[] GV = getGV(10);
            safeType(driver, By.xpath("//input"),GV[0]);
            safeType(driver, By.xpath("//li[2]/input"),GV[1]);
            safeClick(driver, By.xpath("//li[2]/p"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
            GV = getGV(10);
            safeClick(driver, By.xpath("//div[2]/p"));
            safeType(driver, By.xpath("//div[2]/li/input"),GV[0]);
            safeType(driver, By.xpath("//li[2]/input"),GV[1]);
            safeClick(driver, By.xpath("//li[2]/p"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
        }
        //textPresent_Log(driver, "Review Itinerary", 10);

        elementVisible(driver, By.xpath("//button"), 20);
        mouseHover(driver, By.xpath("//button"));
        safeClick(driver, By.xpath("//button"));
        if(elementVisible(driver, By.cssSelector("div.Overlay"), 1)){
            smartClick(driver, By.linkText("Continue"));
        }
    }

    public void air_AddOnPage(RemoteWebDriver driver, String param1, String param2) throws Exception {
        elementVisible(driver, By.xpath("//div[2]/div[1]/div[3]/p"), 5);

        if(!textPresent(driver, "Add ons", 5)) {
            if (textPresent(driver, "Review Itinerary", 2)) {
                mouseHover(driver, By.xpath("//button"));
                safeClick(driver, By.xpath("//button"));
            }
        }
        if(!textPresent(driver, "Review Travellers", 2)) {
            if (textPresent(driver, "Skip", 5)) {
                mouseHover(driver, By.xpath("//div[2]/div[1]/div[3]/p"));
                safeClick(driver, By.xpath("//div[2]/div[1]/div[3]/p"));
                elementVisible(driver, By.linkText("Exit without saving"), 5);
                safeClick(driver, By.linkText("Exit without saving"));
            }
        }
        if(textPresent(driver, "Review Itinerary", 2)){
            mouseHover(driver, By.xpath("//button"));
            safeClick(driver, By.xpath("//button"));
        }
    }

    public void air_TravellerPage(RemoteWebDriver driver, String Login, String phoneNo, String emailID, String Param) throws Exception {
        textPresent_Log(driver, "Review Travellers", 20);
        safeSelect(driver, By.name("title"),"Mr");
        safeType(driver, By.name("firstName"),"Kiran");
        safeType(driver, By.name("lastName"),"Kumar");
        if(elementVisible(driver, By.xpath("//div[5]/li"),1)){
            String Nationality_DOB = getText(driver, By.xpath("//div[5]/li"));
            if(Nationality_DOB.contains("Nationality")){
                safeClick(driver, By.xpath("//div[5]/li"));
                textPresent(driver, "Select Country", 5);
                safeType(driver, By.xpath("//div[2]/div[2]/div[2]/input"), "India");
                Thread.sleep(2000);
                mouseHover(driver, By.xpath("//div[2]/div/ul/li"));
                safeClick(driver, By.xpath("//div[2]/div/ul/li"));
            }
            else if(Nationality_DOB.contains("Date of birth")){
                mouseHover(driver, By.name("dob"));
                Actions actions = new Actions(driver);
                WebElement elementLocator = driver.findElement(By.name("dob"));
                actions.doubleClick(elementLocator).perform();
                mouseHover(driver, By.name("dob"));
                safeClick(driver, By.name("dob"));
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
        safeType(driver, By.name("phone"),phoneNo);
        safeType(driver, By.name("email"),emailID);
        scrollSmooth(driver, 200);
        elementVisible(driver, By.xpath("//button"),2);
        mouseHover(driver, By.xpath("//button"));
        safeClick(driver, By.xpath("//button"));
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