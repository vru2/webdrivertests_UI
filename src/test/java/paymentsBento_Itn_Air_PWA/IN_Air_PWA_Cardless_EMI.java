package test.java.paymentsBento_Itn_Air_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IN_Air_PWA_Cardless_EMI extends PaymentsBento_Itn_Air_Common_PWA {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver =  getMobileDriver(driver);
    }

    @Test
    public void Air_PWA_CardlessEMI() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(air_SRPUrl("IN",origin,destination, date));
        air_SRPPage(driver,"", "");
        air_ItnPage(driver, "", "","","");
        air_AddOnPage(driver, "", "");
        air_TravellerPage(driver, "","8220276214",emailID,"");
        bento_Paymentpage_PWA(driver,"CARDLESSEMI","","","","", "");
        bento_Air_ConfirmationPage_PWA(driver,"", "", "Cardless EMI AXIO :");
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
