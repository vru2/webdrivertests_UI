package test.java.payment_Air_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Air;

public class Air_PWA_Paylater_FK_EMI extends PaymentsUI_Common_PWA_Air {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getMobileDriver(driver);
    }

    @Test
    public void Air_PWA_PaylaterFK_EMI() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(air_SRPUrl2("IN",origin1,destination1, date));
        air_SRPPage(driver,"", "");
        air_ItnPage(driver, "COUPON", "PAYFKEMI","","");
        air_AddOnPage(driver, "", "");
        air_TravellerPage(driver, "","7780597594",emailID,"");
        bento_Paymentpage_PWA(driver,"PAYLATERFK","","","PaylaterEMI","COUPON", "");
        bento_Air_ConfirmationPage_PWA(driver,"", "", "Paylater EMI FK ");
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
