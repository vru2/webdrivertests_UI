package test.java.accountsPWAUI;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginWithMobileNumber extends AccountsPWA_Login{

    @BeforeClass
    public void setupPWA() throws Exception {
        this.driver= getMobileDriver(driver);
        driver.navigate().to(baseUrl);
    }

    @Test
    public void LoginWithMobileNumber() throws Exception
    {
        LoginButtonClickonHomepage();
        LoginWithMobileNumber("SIGININ", "1111111111");
        SignOut();
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
