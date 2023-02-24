package test.java.commonUI;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PaymentsUI_Common_PWA_Hotels extends PaymentsUI_Common_PWA{

    public void hotels_HomePage(RemoteWebDriver driver, String HotelName, int CheckIN, int CheckOut) throws Exception {
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


    }