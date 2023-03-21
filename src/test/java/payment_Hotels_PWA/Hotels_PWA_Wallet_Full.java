package test.java.payment_Hotels_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Hotels;

public class Hotels_PWA_Wallet_Full extends PaymentsUI_Common_PWA_Hotels {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver =  getMobileDriver(driver);
    }

    @Test
    public void Hotel_PWA_WalletFull() throws Exception {
        driver.get(hotelsUrl);
        hotels_HomePage(driver, HotelName, CheckIN, CheckOut);
        hotels_SRP(driver, "", "");
        hotels_DetailsPage(driver, "", "");
        driver.manage().addCookie(fullwallet);
        hotels_ItineraryPage(driver, "", "", "");
        bento_Paymentpage_PWA(driver,"WALLET","","","","", "");
        hotels_ConfirmationPage(driver, "","", "Hotel PWA Wallet Full");
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