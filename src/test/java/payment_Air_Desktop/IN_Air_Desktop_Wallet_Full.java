package test.java.payment_Air_Desktop;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_Desktop_Air;

public class IN_Air_Desktop_Wallet_Full extends PaymentsUI_Common_Desktop_Air {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getDriver(driver);
    }

    @Test
    public void Air_Desktop_Walletfull() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(air_SRPUrl("IN",origin,destination, date));
        air_SRPPage_Desktop(driver,"","");
        air_ItnPage_Desktop(driver, "", "","","");
        air_AddOnPage_Desktop(driver,"","","","");
        air_LoginPage_Desktop(driver, "", emailID, phoneNo);
        air_TravellerPage_Desktop(driver,"");
        driver.manage().addCookie(fullwallet);
        air_PaymentPage_Desktop(driver, "wallet", "", "", "", "", "Full Wallet : ");
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