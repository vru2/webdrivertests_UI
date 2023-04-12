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
        elementVisible(driver, getObjectPayment("Desktop_Air_Itn_Step1_Review_Text"), 10);
        textPresent(driver, "Review your itinerary", 1);
        Child_URL = driver.getCurrentUrl();
        driver.close(); // Closing Child window
        driver.switchTo().window(parent);
        driver.get(Child_URL);

        elementVisible(driver, getObjectPayment("Desktop_Air_Itn_Step1_Review_Text"), 10);
        textPresent(driver, "Review your itinerary", 2);
        smartClick(driver, getObjectPayment("Desktop_Air_Itn_Step1_Review_VAS"));
        if(CouponGV.equalsIgnoreCase("Coupon")){
            applyCoupon(driver, COUPONCODE);
        } else if(CouponGV.equalsIgnoreCase("GVFull")){
            String[] GV = getGVSCLP(10000);
            applyGV(driver, GV[0],GV[1]);
        } else if(CouponGV.equalsIgnoreCase("MultiGV")){
            String[] GV = getGVSCLP(10);
            applyGV(driver, GV[0],GV[1]);
            GV = getGV(10);
            applyGV(driver, GV[0],GV[1]);
        } else if(CouponGV.equalsIgnoreCase("GVPartial")){
            String[] GV = getGVSCLP(10);
            applyGV(driver, GV[0],GV[1]);
        } else if(CouponGV.equalsIgnoreCase("SCLPGV")){

        }
        else if(CouponGV.equalsIgnoreCase("PartialGV_SCLP_PopUp")){
            String[] GV = getGVSCLP(10);
            applyGV(driver, FlightName,FlightNo); //passing GVnumber, Pin
        }

        elementVisible(driver, getObjectPayment("Desktop_Air_Itn_Step1_Btn"), 5);
        textPresent_Log(driver, "Review your itinerary", 1);

        if(elementVisible(driver, getObjectPayment("Desktop_Air_Itn_Step1_Review_VAS1"), 5)){
            scrollToElement(driver, getObjectPayment("Desktop_Air_Itn_Step1_Review_VAS1"));
            Thread.sleep(1000);
            smartClick(driver, getObjectPayment("Desktop_Air_Itn_Step1_Review_VAS1"));

        }
        scrollToElement(driver, getObjectPayment("Desktop_Air_Itn_Step1_Btn"));
        Thread.sleep(2000);
        safeClick(driver, getObjectPayment("Desktop_Air_Itn_Step1_Btn"));
    }

    public void applyGV(RemoteWebDriver driver, String GVNumber, String GVPin) throws Exception {
        safeType(driver, getObjectPayment("Desktop_Air_Itn_Step1_GVNumber_Txt_Box"), GVNumber);
        safeType(driver, getObjectPayment("Desktop_Air_Itn_Step1_GVPin_Txt_Box"), GVPin);
        safeClick(driver, getObjectPayment("Desktop_Air_Itn_Step1_GV_Apply_Btn"));
        textPresent_Log(driver, "has been redeemed for this booking", 2);
    }

    public void applyCoupon(RemoteWebDriver driver, String CouponCode) throws Exception {
        safeType(driver, getObjectPayment("Desktop_Air_Itn_Step1_Coupon_Txt_Box"), CouponCode);
        safeClick(driver, getObjectPayment("Desktop_Air_Itn_Step1_Coupon_Apply_Btn"));
        textPresent_Log(driver, "Great! You just saved", 5);
    }

    public void air_AddOnPage_Desktop(RemoteWebDriver driver, String FlightName, String FlightNo, String new1, String new2) throws Exception {
        textPresent(driver, "Choose add-ons", 1);
        if(textPresent(driver, "Choose add-ons", 1)) {
            if (elementVisible(driver, getObjectPayment("Desktop_Air_Itn_Addon_Btn"), 1)) {
                Thread.sleep(500);
                scrollToElement(driver, getObjectPayment("Desktop_Air_Itn_Addon_Btn"));
                Thread.sleep(1000);
                safeClick(driver, getObjectPayment("Desktop_Air_Itn_Addon_Btn"));
            }
        }

    }

    public void air_LoginPage_Desktop(RemoteWebDriver driver, String LoginPage, String EmailID, String PhoneNo) throws Exception {
        elementVisible(driver, getObjectPayment("Desktop_Air_LoginPage_PhoneNo"),10);
        textPresent_Log(driver, "Add contact details", 2);
        safeType(driver, getObjectPayment("Desktop_Air_LoginPage_PhoneNo"), PhoneNo);
        smartClick(driver, getObjectPayment("Desktop_Air_LoginPage_EmailID"));
        smartType(driver, getObjectPayment("Desktop_Air_LoginPage_EmailID"), EmailID);
        smartType(driver, getObjectPayment("Desktop_Air_LoginPage_EmailID1"), EmailID);


/*

            smartClick(driver, getObjectPayment("Desktop_Air_LoginPage_EmailID1"));
            smartType(driver, getObjectPayment("Desktop_Air_LoginPage_EmailID1"), EmailID);
*/

        if(elementVisible(driver, getObjectPayment("Desktop_Air_LoginPage_Btn"),1)) {
            safeClick(driver, getObjectPayment("Desktop_Air_LoginPage_Btn"));
        }
         else safeClick(driver, getObjectPayment("Desktop_Air_LoginPage_Btn1"));
    }


    public void air_TravellerPage_Desktop(RemoteWebDriver driver, String Traveller) throws Exception {
        elementVisible(driver, getObjectPayment("Desktop_Air_TravellerPage_FirstName"),10);
        textPresent_Log(driver,"Add traveller details", 1);
        textPresent(driver,"Adult 1", 1);
       // smartType(driver, getObjectPayment("Desktop_Air_TravellerPage_FirstName"),"Kiran");
        smartType(driver, getObjectPayment("Desktop_Air_TravellerPage_FirstName1"),"Kiran");
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
       // elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 1);
       // refreshPage(driver);
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
        textPresent_Log(driver, "Your booking is done", 5);
        String tripId = getText(driver, getObjectPayment("Desktop_Air_ConfirmationPage_TripID"));
        logURL(driver);
        Reporter.log(TestDetails + tripId);
        System.out.println(TestDetails + tripId);
    }

    }