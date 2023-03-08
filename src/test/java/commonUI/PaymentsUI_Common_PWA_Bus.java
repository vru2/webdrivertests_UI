package test.java.commonUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class PaymentsUI_Common_PWA_Bus extends PaymentsUI_Common_PWA{

    public void bus_SRP(RemoteWebDriver driver, String BusName, String BusType) throws Exception {
        elementVisible(driver, getObjectPayment("PWA_Bus_SRP_BusSelect"), 10);
        safeClick(driver, getObjectPayment("PWA_Bus_SRP_BusSelect"));
    }

    public void bus_SeatSelection(RemoteWebDriver driver, String Seat, String SeatNo) throws Exception {
        elementVisible(driver, getObjectPayment("PWA_Bus_SeatSelection"), 10);
        WebElement we = driver.findElement(getObjectPayment("PWA_Bus_SeatSelection1"));
        Actions builder = new Actions(driver);
        Boolean selectedSeat = elementVisible(driver, getObjectPayment("PWA_Bus_SeatSelection"), 1);
        int[] Bus_X = new int[] { 60, 60, 60,  60, 60, 60, 180 , 180, 180, 180, 180, 220, 220, 280, 280, 280, 280,       320, 320, 320,  320, 320 , -70, -70, -70,  -70,};
        int[] Bus_Y = new int[] { 10, 70 , 140,  -140, -150, -170, 10, 170, 80, -140, 10,  70, -140, 10, 70 , 140, -140,  10, 70 , 140,  -140, -200, 10, 70 , 140,  -140 };
        for (int i=0; i<= Bus_X.length-1; i++){
            System.out.println(Bus_X[i]+" : "+Bus_Y[i]);
            builder.moveToElement(we, Bus_X[i], Bus_Y[i]).click().build().perform();
            Thread.sleep(1000);
            if(elementVisible(driver, getObjectPayment("PWA_Bus_SeatSelection_PickupPoint_Btn"), 2)){
                safeClick(driver, getObjectPayment("PWA_Bus_SeatSelection_PickupPoint_Btn"));
                break;
            }
        }
    }

    public void bus_SelectPickup(RemoteWebDriver driver, String PickUp, String DropOff) throws Exception {
        textPresent(driver, "Choose pick-up point", 10);
        safeClick(driver, getObjectPayment("PWA_Bus_PickupPoint1_Btn"));
        safeClick(driver, getObjectPayment("PWA_Bus_DropoffPoint_Btn"));
        textPresent(driver, "Choose drop-off point", 10);
        safeClick(driver, getObjectPayment("PWA_Bus_PickupPoint1_Btn"));
        safeClick(driver, getObjectPayment("PWA_Bus_DropoffPoint_AddTraveller_Btn"));
    }

    public void bus_TravellerPage(RemoteWebDriver driver, String Login, String PhoneNo, String EmailID) throws Exception {
        textPresent(driver, "Traveller details", 10);
        safeClick(driver, getObjectPayment("PWA_Bus_TravellerPage_Salutation"));
        safeType(driver, getObjectPayment("PWA_Bus_TravellerPage_FirstName"), "Kiran");
        safeType(driver, getObjectPayment("PWA_Bus_TravellerPage_LastName"), "Kumar");
        safeType(driver, getObjectPayment("PWA_Bus_TravellerPage_Age"), "12");
        safeType(driver, getObjectPayment("PWA_Bus_TravellerPage_PhoneNo"), phoneNo);
        safeType(driver, getObjectPayment("PWA_Bus_TravellerPage_EmailID"), emailID);
        safeClick(driver, getObjectPayment("PWA_Bus_TravellerPage_Continue_Btn"));
    }

    public void bus_ReviewPage(RemoteWebDriver driver, String Coupon, String CouponCode, String CouponType) throws Exception {
        textPresent(driver, "Review booking", 10);
        safeClick(driver, getObjectPayment("PWA_Bus_ReviewPage_Continue_Btn"));

    }

    public void bus_ConfirmationPage(RemoteWebDriver driver, String paymentType, String payType, String bookingType) throws Exception {
        if (textPresent(driver, "Something went wrong", 1)) {
            Reporter.log("Something went wrong text is present in confirmation page");
            Assert.assertTrue(false);
        }
        textPresent_Log(driver, "Booking confirmed", 20);
        elementVisible(driver, By.xpath("//div[3]"), 1);
        textPresent_Log(driver, "Booking confirmed", 5);
        String tripID = getText(driver, By.xpath("//div[3]"));
        Reporter.log(bookingType +": "+ tripID);
        System.out.println(bookingType +": "+ tripID);
    }



    }