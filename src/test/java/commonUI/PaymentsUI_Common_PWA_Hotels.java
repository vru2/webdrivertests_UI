package test.java.commonUI;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class PaymentsUI_Common_PWA_Hotels extends PaymentsUI_Common_PWA{



    public void hotels_HomePage(RemoteWebDriver driver, String HotelName, int CheckIN, int CheckOut) throws Exception {
        System.out.println(CheckIN+" "+CheckOut);
        elementVisible(driver, getObjectPayment("PWA_Hotels_Homepage_City_TextBox"), 10);
        safeClick(driver, getObjectPayment("PWA_Hotels_Homepage_City_TextBox"));
        elementVisible(driver, getObjectPayment("PWA_Hotels_Homepage_City_Search_Popup"), 10);
        Thread.sleep(1000);
        safeAutocomplete(driver, getObjectPayment("PWA_Hotels_Homepage_City_Search_Popup"), getObjectPayment("PWA_Hotels_Homepage_City_Search_Ajax"), HotelName);
        elementVisible(driver, getObjectPayment("PWA_Hotels_Homepage_Calendar"), 10);
        safeClick(driver, getObjectPayment("PWA_Hotels_Homepage_Calendar"));
        String Calendar_Xpath = "//div[2]/div/div/div/div[";
        elementVisible(driver, By.xpath(Calendar_Xpath + CheckIN + "]"), 10);
        Thread.sleep(1000);
        safeClick(driver, By.xpath(Calendar_Xpath + CheckIN + "]"));
        safeClick(driver, By.xpath(Calendar_Xpath + CheckOut + "]"));
        safeClick(driver, getObjectPayment("PWA_Hotels_Homepage_Search_Button"));
    }

    public void hotels_SRP(RemoteWebDriver driver, String HotelName, String HotelName1) throws Exception {
        elementVisible(driver, getObjectPayment("PWA_Hotels_SRP_Select_Hotel"), 10);
        safeClick(driver, getObjectPayment("PWA_Hotels_SRP_Select_Hotel"));
    }

    public void hotels_DetailsPage(RemoteWebDriver driver, String HotelsName, String HotelName1) throws Exception {
        elementVisible(driver, getObjectPayment("PWA_Hotels_DetailsPage_Book_Button"), 10);
        Thread.sleep(2000);
        safeClick(driver, getObjectPayment("PWA_Hotels_DetailsPage_Book_Button"));
    }

    public void hotels_ItineraryPage(RemoteWebDriver driver, String Coupon, String CouponCode, String Login) throws Exception {
        elementVisible(driver, getObjectPayment("PWA_Hotels_ItineraryPage_PhoneNo"), 10);
        safeType(driver, getObjectPayment("PWA_Hotels_ItineraryPage_PhoneNo"), phoneNo);
        safeType(driver, getObjectPayment("PWA_Hotels_ItineraryPage_EmailID"), emailID);
        //safeSelectByValue(driver, getObjectPayment("PWA_Hotels_ItineraryPage_Title"), "Mr.");
        safeClick(driver, getObjectPayment("PWA_Hotels_ItineraryPage_Title"));
        safeSelectByIndex(driver, getObjectPayment("PWA_Hotels_ItineraryPage_Title"), 1);
        safeType(driver, getObjectPayment("PWA_Hotels_ItineraryPage_FirstName"), "Kiran");
        safeType(driver, getObjectPayment("PWA_Hotels_ItineraryPage_LastName"), "Kumar");
        if(Coupon.equalsIgnoreCase("Coupon")){
           // mouseHover(driver, getObjectPayment("PWA_Hotels_ItineraryPage_Coupon_TextBox"));
            scrollToElement(driver, getObjectPayment("PWA_Hotels_ItineraryPage_Coupon_TextBox"));
            safeClick(driver, getObjectPayment("PWA_Hotels_ItineraryPage_Coupon_TextBox"));
            safeType(driver, getObjectPayment("PWA_Hotels_ItineraryPage_Coupon_TextBox"), CouponCode);
            safeClick(driver, getObjectPayment("PWA_Hotels_ItineraryPage_Coupon_Apply_Btn"));
            textPresent_Log(driver, "Great! You just saved", 5);
        }
        // Hotels GV not implemented by Hotels team
        safeClick(driver, getObjectPayment("PWA_Hotels_ItineraryPage_Continue_Btn"));
    }

    public void hotels_ConfirmationPage(RemoteWebDriver driver, String paymentType, String payType, String bookingType) throws Exception {
        if (textPresent(driver, "Something went wrong", 1)||textPresent(driver, "Oops, your booking didn’t go through", 1)) {
            Reporter.log("Something went wrong text is present in confirmation page");
            Assert.assertTrue(false);
        }
        elementVisible(driver, getObjectPayment("PWA_Hotels_ConfirmationPage_TripID"), 30);
        textPresent_Log(driver, "Booking confirmed", 1);
        String tripID = getText(driver, getObjectPayment("PWA_Hotels_ConfirmationPage_TripID"));
        Reporter.log(bookingType +" : "+ tripID);
        System.out.println(bookingType +" : "+ tripID);
    }

    }