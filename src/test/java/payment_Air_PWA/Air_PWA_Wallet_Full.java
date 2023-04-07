package test.java.payment_Air_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Air;

import java.util.logging.Logger;

public class Air_PWA_Wallet_Full extends PaymentsUI_Common_PWA_Air {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getMobileDriver(driver);
    }

    @Test
    public void Air_PWA_Walletfull() throws Exception {
        driver.manage().deleteAllCookies();
        Logger log = Logger.getLogger("devpinoyLogger");
        driver.get(air_SRPUrl("IN",origin,destination, date+10));
        air_SRPPage(driver,"", "");
        air_ItnPage(driver, "", "","","");
        air_AddOnPage(driver, "", "");
        driver.manage().addCookie(fullwallet);
        air_TravellerPage(driver, "","1234123456",emailID,"");
        bento_Paymentpage_PWA(driver,"WALLET","","","","", "");
        bento_Air_ConfirmationPage_PWA(driver,"", "", "Wallet Full :");
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
