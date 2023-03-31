package test.java.payment_Air_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Air;

public class Air_PWA_GV_CLP_CC extends PaymentsUI_Common_PWA_Air {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getMobileDriver(driver);
    }

    @Test
    public void Air_PWA_GV_Multi_CC() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(air_SRPUrl("IN",origin,destination, date+9));
        air_SRPPage(driver,"", "");
        air_ItnPage(driver, "GVMulti", "","","");
        air_AddOnPage(driver, "", "");
        //driver.manage().addCookie(ctauth_partial_wallet);//65243938
        addwalletamount_UserID(10, "65243938");
        air_TravellerPage(driver, "true","5252525252",emailID,"");
        bento_Paymentpage_PWA(driver,"GV_Partial","","","PartialWallet","Amex","");
        bento_Air_ConfirmationPage_PWA(driver,"", "", "GV Multi CC :");
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
