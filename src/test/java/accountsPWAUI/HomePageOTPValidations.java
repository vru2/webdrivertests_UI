package test.java.accountsPWAUI;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageOTPValidations  extends AccountsPWA_Login{
    @BeforeMethod
    public void setupPWA() throws Exception {
        this.driver= getMobileDriver(driver);
        driver.navigate().to(baseUrl);
    }

    @Test
    public void HomePageOTPValidations() throws Exception {

        CookieButtonCLick();
        OtpErrorValidation("Homepage");
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
