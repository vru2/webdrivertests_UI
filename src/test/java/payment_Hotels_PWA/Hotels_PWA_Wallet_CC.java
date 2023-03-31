package test.java.payment_Hotels_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Hotels;

public class Hotels_PWA_Wallet_CC extends PaymentsUI_Common_PWA_Hotels {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver =  getMobileDriver(driver);
    }

    @Test
    public void Hotel_PWA_WL_CC() throws Exception {
        driver.get(hotelsUrl);
        hotels_HomePage(driver, HotelName, CheckIN, CheckOut);
        hotels_SRP(driver, "", "");
        hotels_DetailsPage(driver, "", "");
        driver.manage().addCookie(ctauth_partial_wallet);//65243938
        addwalletamount_UserID(10, "65243938");
        hotels_ItineraryPage(driver, "", "", "true", "5252525252");
        bento_Paymentpage_PWA(driver,"GV_Partial","","","PartialWalletONLY","Paytm","");
        hotels_ConfirmationPage(driver, "","", "Hotel PWA WL CC PayTM");
    }

    @AfterClass(alwaysRun = true)
    public void closeSelenium() throws Exception {
        browserClose(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult _result) throws Exception {
        afterMethod(driver, _result);
    }
}