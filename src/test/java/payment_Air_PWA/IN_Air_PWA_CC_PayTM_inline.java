package test.java.payment_Air_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Air;

public class IN_Air_PWA_CC_PayTM_inline extends PaymentsUI_Common_PWA_Air {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getMobileDriver(driver);
    }

    @Test
    public void Air_PWA_CC_Paytm() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(air_SRPUrl("IN",origin,destination, date+1));
        air_SRPPage(driver,"", "");
        air_ItnPage(driver, "", "","","");
        air_AddOnPage(driver, "", "");
        air_TravellerPage(driver, "",phoneNo,emailID,"");
        bento_Paymentpage_PWA(driver,"CC","","","","Paytm","");
        bento_Air_ConfirmationPage_PWA(driver,"", "", "CC PayTM :");
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
