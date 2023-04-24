package test.java.accountsPWAUI;

import org.testng.ITestResult;
import org.testng.annotations.*;

public class AccountsPageOTPValidations extends AccountsPWA_Login {

    @BeforeMethod
    public void setupPWA() throws Exception {
        this.driver= getMobileDriver(driver);
        driver.navigate().to(baseUrl);
    }

    @Test
    public void AccountsPageOTPValidations() throws Exception {

        CookieButtonCLick();
        OtpErrorValidation("AccountsPage");
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
