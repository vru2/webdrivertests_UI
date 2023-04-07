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
    public void Air_PWA_PaylaterFK() throws Exception {
        driver.manage().deleteAllCookies();
     /*   driver.get(air_SRPUrl_2("IN",origin1,destination1, date+5));
        air_SRPPage(driver,"", "");
        air_ItnPage(driver, "", "","","");
        air_AddOnPage(driver, "", "");
        air_TravellerPage(driver, "","9986696785",emailID,"");*/
        driver.get("https://qa2new.cleartrip.com/pay/air/8fa9e9928bfd6d1bcb07bc86ed2ee39a?lang=en");
        bento_Paymentpage_PWA(driver,"PAYLATERFK","","","PaylaterEMI","", "");
        bento_Air_ConfirmationPage_PWA(driver,"", "", "PaylaterEMI FK :");
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
