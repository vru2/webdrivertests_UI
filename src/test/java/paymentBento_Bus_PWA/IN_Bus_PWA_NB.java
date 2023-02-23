package test.java.paymentBento_Bus_PWA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Bus;


public class IN_Bus_PWA_NB extends PaymentsUI_Common_PWA_Bus {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver =  getMobileDriver(driver);
    }

    @Test
    public void Bus_PWA_NB() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(bus_SRPUrl("IN", "", "", 5));
        bus_SRP(driver, "", "");
        bus_SeatSelection(driver, "", "");
        bus_SelectPickup(driver, "", "");
        bus_TravellerPage(driver, "", phoneNo, emailID);
        bus_ReviewPage(driver, "", "", "");
        bento_Paymentpage_PWA(driver,"NB","","","","RazorpayNB", "Success");
        bus_ConfirmationPage(driver, "","", "");
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
