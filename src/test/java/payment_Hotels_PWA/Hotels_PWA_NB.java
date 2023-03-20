package test.java.payment_Hotels_PWA;

import org.testng.ITestResult;
import org.testng.annotations.*;
import test.java.commonUI.PaymentsUI_Common_PWA_Hotels;

public class IN_Hotels_PWA_NB extends PaymentsUI_Common_PWA_Hotels {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver =  getMobileDriver(driver);
    }

    @Test
    public void Hotel_PWA_NB() throws Exception {
        driver.get(hotelsUrl);
        hotels_HomePage(driver, HotelName, CheckIN, CheckOut);
        hotels_SRP(driver, "", "");
        hotels_DetailsPage(driver, "", "");
        hotels_ItineraryPage(driver, "", "", "");
        bento_Paymentpage_PWA(driver,"NB","","","","RazorpayNB", "Success");
        hotels_ConfirmationPage(driver, "","", "Hotel NB");
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