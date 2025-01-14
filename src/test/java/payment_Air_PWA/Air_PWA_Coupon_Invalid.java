package test.java.payment_Air_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Air;

public class Air_PWA_Coupon_Invalid extends PaymentsUI_Common_PWA_Air {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getMobileDriver(driver);
    }

    @Test
    public void Air_PWA_InvalidCoupon() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(air_SRPUrl("IN",origin,destination, date+2));
        //driver.manage().addCookie(fullwallet);
        air_SRPPage(driver,"", "");
        air_ItnPage(driver, "COUPON", "PAYCC","","");
        air_AddOnPage(driver, "", "");
        air_TravellerPage(driver, "","1212121214",emailID,"");
        bento_Paymentpage_PWA(driver,"NB","","","Coupon","RazorpayNB", "Success");
        bento_Air_ConfirmationPage_PWA(driver,"", "", "PWA Invalid coupon air - NB ");
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
