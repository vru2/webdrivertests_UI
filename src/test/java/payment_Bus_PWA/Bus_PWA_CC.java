package test.java.payment_Bus_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Bus;


public class Bus_PWA_CC extends PaymentsUI_Common_PWA_Bus {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver =  getMobileDriver(driver);
    }

    @Test
    public void Bus_PWA_CC() throws Exception {
        driver.get(bus_SRPUrl("IN", "", "", 6));
        bus_SRP(driver, "", "");
        bus_SeatSelection(driver, "", "");
        bus_SelectPickup(driver, "", "");
        bus_TravellerPage(driver, "", phoneNo, emailID);
        bus_ReviewPage(driver, "", "", "");
        bento_Paymentpage_PWA(driver,"CC","","","","Amex","");
        bus_ConfirmationPage(driver, "","", "Bus CC ");
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
