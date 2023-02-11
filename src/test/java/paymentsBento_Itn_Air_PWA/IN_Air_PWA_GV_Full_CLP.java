package test.java.paymentsBento_Itn_Air_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IN_Air_PWA_GV_Full_CLP extends PaymentsBento_Itn_Air_Common_PWA {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getMobileDriver(driver);
    }

    @Test
    public void Air_PWA_GV_WL_CC() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(air_SRPUrl("IN",origin,destination, date+9));
        air_SRPPage(driver,"", "");
        air_ItnPage(driver, "GVFULL", "","","");
        air_AddOnPage(driver, "", "");
        air_TravellerPage(driver, "",phoneNo,emailID,"");
        bento_Paymentpage_PWA(driver,"GV","","","","","");
        bento_Air_ConfirmationPage_PWA(driver,"", "", "GV Full :");
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
