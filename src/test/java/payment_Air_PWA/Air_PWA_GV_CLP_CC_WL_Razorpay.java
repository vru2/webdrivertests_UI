package test.java.payment_Air_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Air;

public class Air_PWA_GV_CLP_CC_WL_Razorpay extends PaymentsUI_Common_PWA_Air {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getMobileDriver(driver);
    }

    @Test
    public void Air_PWA_GV_WL_CC() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(air_SRPUrl("IN",origin,destination, date+9));
        air_SRPPage(driver,"", "");
        driver.manage().addCookie(ctauth_partial_wallet);//65243938
        addwalletamount_UserID(10, "65243938");
        air_ItnPage(driver, "GV", "","","");
        air_AddOnPage(driver, "", "");
        air_TravellerPage(driver, "","5252525252",emailID,"");
        bento_Paymentpage_PWA(driver,"GV_Partial","","","PartialWallet","RazorpayCC","");
        bento_Air_ConfirmationPage_PWA(driver,"", "", "GV wallet CC :");
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