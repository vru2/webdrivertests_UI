package test.java.payment_Air_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Air;

public class IN_Air_PWA_Cardless_EMI extends PaymentsUI_Common_PWA_Air {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver =  getMobileDriver(driver);
    }

    @Test
    public void Air_PWA_CardlessEMI() throws Exception {
        driver.manage().deleteAllCookies();
       /* driver.get(air_SRPUrl("IN",origin,destination, date));*/
        driver.get("https://qa2new.cleartrip.com/flights/results?adults=1&childs=0&infants=0&class=Economy&depart_date=20/03/2023&from=MAA&to=DEL&intl=n");
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
