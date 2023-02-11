package test.java.paymentsBento_Itn_Air_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IN_Air_PWA_Coupon_Invalid extends PaymentsBento_Itn_Air_Common_PWA {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getMobileDriver(driver);
    }

    @Test
    public void Air_PWA_InvalidCoupon() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(air_SRPUrl("IN",origin,destination, date+2));
        air_SRPPage(driver,"", "");
        air_ItnPage(driver, "COUPON", "PAYCC","","");
        air_AddOnPage(driver, "", "");
        air_TravellerPage(driver, "",phoneNo,emailID,"");
        bento_Paymentpage_PWA(driver,"INVALIDCOUPON","","","RazorpayNB","RazorpayNB", "Success");
        bento_Air_ConfirmationPage_PWA(driver,"", "", "Invalid coupon - NB :");
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
