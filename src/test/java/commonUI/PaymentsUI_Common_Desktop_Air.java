package test.java.commonUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class PaymentsUI_Common_Desktop_Air extends PaymentsUI_Common_Desktop {

    public void air_SRPPage_Desktop(RemoteWebDriver driver, String FlightName, String FlightNo) throws Exception {
        if (!elementVisible(driver, getObjectPayment("Desktop_Air_SRP_Book_Btn"), 30)) {
            Reporter.log("SRP page not loaded");
            Assert.assertTrue(false);
        }
        textPresent(driver, "Non-stop", 5);
        safeClick(driver, getObjectPayment("Desktop_Air_SRP_Book_Btn"));
    }

    public void air_ItnPage_Desktop(RemoteWebDriver driver, String FlightName, String FlightNo, String CouponGV, String COUPONCODE) throws Exception {
        elementPresent(driver, getObjectPayment("Desktop_Air_Itn_Step1_Coupon_Txt_Box"), 20);
        textPresent(driver, "Review your itinerary", 1);
        String parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        Iterator<String> I1 = s.iterator();
        String Child_URL = "";
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
            }
        }
        Child_URL = driver.getCurrentUrl();
        driver.close(); // Closing Child window
        driver.switchTo().window(parent);
        driver.get(Child_URL);

        if (!elementVisible(driver, getObjectPayment("Desktop_Air_Itn_Step1_Btn"), 10)) {
            Reporter.log("Itn page not loaded");
         //   Assert.assertTrue(false);
        }
        elementVisible(driver, getObjectPayment("Desktop_Air_Itn_Step1_Btn"), 20);
        textPresent_Log(driver, "Review your itinerary", 5);
        refreshPage(driver);

        elementVisible(driver, getObjectPayment("Desktop_Air_Itn_Step1_Btn"), 20);

        if(CouponGV.equalsIgnoreCase("Coupon")){
            applyCoupon(driver, COUPONCODE);
        } else if(CouponGV.equalsIgnoreCase("FullGV")){

        } else if(CouponGV.equalsIgnoreCase("MultiGV")){

        } else if(CouponGV.equalsIgnoreCase("CLPGV")){

        } else if(CouponGV.equalsIgnoreCase("SCLPGV")){

        }
        elementVisible(driver, getObjectPayment("Desktop_Air_Itn_Step1_Btn"), 5);
        textPresent_Log(driver, "Review your itinerary", 1);

        if(elementVisible(driver, By.xpath("//div/h3"), 5)){
            scrollToElement(driver, By.xpath("//div/h3"));
            Thread.sleep(2000);
            smartClick(driver, By.xpath("//div/h3"));

        }
        scrollToElement(driver, getObjectPayment("Desktop_Air_Itn_Step1_Btn"));
        Thread.sleep(2000);

        safeClick(driver, getObjectPayment("Desktop_Air_Itn_Step1_Btn"));
    }

    public void applyCoupon(RemoteWebDriver driver, String CouponCode) throws Exception {
        safeType(driver, getObjectPayment("Desktop_Air_Itn_Step1_Coupon_Txt_Box"), CouponCode);
        safeClick(driver, getObjectPayment("Desktop_Air_Itn_Step1_Coupon_Apply_Btn"));
        textPresent_Log(driver, "Great! You just saved", 5);
    }


        public void air_AddOnPage_Desktop(RemoteWebDriver driver, String FlightName, String FlightNo, String new1, String new2) throws Exception {
        textPresent_Log(driver, "Choose add-ons", 10);
        elementVisible(driver, getObjectPayment("Desktop_Air_Itn_Addon_Btn"),5);
        Thread.sleep(2000);
      //  pageScroll(driver, 0, 500);
        scrollToElement(driver, getObjectPayment("Desktop_Air_Itn_Addon_Btn"));
       /* JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");*/
        Thread.sleep(2000);
        safeClick(driver, getObjectPayment("Desktop_Air_Itn_Addon_Btn"));
    }

    public void air_LoginPage_Desktop(RemoteWebDriver driver, String LoginPage, String EmailID, String PhoneNo) throws Exception {
        elementVisible(driver, getObjectPayment("Desktop_Air_LoginPage_PhoneNo"),10);
        textPresent_Log(driver, "Add contact details", 1);
        safeType(driver, getObjectPayment("Desktop_Air_LoginPage_PhoneNo"), PhoneNo);
        safeClick(driver, getObjectPayment("Desktop_Air_LoginPage_EmailID"));
        safeType(driver, getObjectPayment("Desktop_Air_LoginPage_EmailID"), EmailID);
        safeClick(driver, getObjectPayment("Desktop_Air_LoginPage_Btn"));
    }


    public void air_TravellerPage_Desktop(RemoteWebDriver driver, String Traveller) throws Exception {
        elementVisible(driver, getObjectPayment("Desktop_Air_TravellerPage_FirstName"),10);
        textPresent_Log(driver,"Add traveller details", 1);
        textPresent(driver,"Adult 1", 1);
        safeType(driver, getObjectPayment("Desktop_Air_TravellerPage_FirstName"),"Kiran");
        safeType(driver, getObjectPayment("Desktop_Air_TravellerPage_LastName"),"Kumar");
        //safeSelectByIndex(driver, getObjectPayment("Desktop_Air_TravellerPage_Salutation"),1 );
        safeClick(driver, getObjectPayment("Desktop_Air_TravellerPage_Salutation"));
        safeClick(driver, getObjectPayment("Desktop_Air_TravellerPage_Salutation_Mr"));
        if(elementVisible(driver, getObjectPayment("Desktop_Air_TravellerPage_DOB_Date"),1)){
            safeSelect(driver, getObjectPayment("Desktop_Air_TravellerPage_DOB_Date"), "05");
            safeSelect(driver, getObjectPayment("Desktop_Air_TravellerPage_DOB_Month"), "Jan");
            safeSelect(driver, getObjectPayment("Desktop_Air_TravellerPage_DOB_Year"), "2000");
        }
       if(elementVisible(driver, getObjectPayment("Desktop_Air_TravellerPage_Nationality_Dropdown"),1)){
            safeClick(driver, getObjectPayment("Desktop_Air_TravellerPage_Nationality_Dropdown"));
            Thread.sleep(1000);
            safeClick(driver, getObjectPayment("Desktop_Air_TravellerPage_Nationality_Country"));
        }
        scrollToElement(driver, getObjectPayment("Desktop_Air_TravellerPage_Btn"));
        Thread.sleep(1000);
        safeClick(driver, getObjectPayment("Desktop_Air_TravellerPage_Btn"));
    }

    public void air_PaymentPage_Desktop(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName, String TestDetails) throws Exception {
        elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 30);
        refreshPage(driver);
        if (elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 30)) {
            bento_paymentpage(driver, PaymentType, CardNumber, domain, PayType, BankName);
            if (!(CardNumber == "ADCB" || PaymentType == "Phonepe" || PaymentType == "UPIScan" || PayType == "Googlecaptcha"|| PayType == "Failure")) {
                confirmation_Page_Air(driver, PaymentType, CardNumber, TestDetails);
            }
        } else if (textPresent(driver, "Sorry, our servers are stumped with your request", 1) || textPresent(driver, "Flight not available", 1)) {
            Reporter.log("Booking failed due to itn page issue");
            assertTrue(false);
        } else {
            Reporter.log("Booking failed due to itn page issue");
            assertTrue(false);
        }
    }

    public void confirmation_Page_Air(RemoteWebDriver driver, String PaymentType, String CardNumber, String TestDetails) throws Exception {
        elementPresent_log(driver, getObjectPayment("Desktop_Air_ConfirmationPage_TripID"), "TripID", 30);
        textPresent_Log(driver, "Your booking is done", 30);
        String tripId = getText(driver, getObjectPayment("Desktop_Air_ConfirmationPage_TripID"));
        logURL(driver);
        Reporter.log(TestDetails + tripId);
        System.out.println(TestDetails + tripId);
    }

    }