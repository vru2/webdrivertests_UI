package test.java.accountsPWAUI;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SupportPageLoginOnly extends AccountsPWA_Login {

    @BeforeClass
    public void LaunchApplication() throws Exception {
        this.driver= getMobileDriver(driver);
        driver.navigate().to(baseUrl);
    }

    @Test
    public void SupportPageLoginOnly() throws Exception
    {
        supportPageLogin();
        Login("SIGNIN","SupportPage_SignUp");
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
