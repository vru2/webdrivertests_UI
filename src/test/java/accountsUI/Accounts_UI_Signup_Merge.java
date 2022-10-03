package test.java.accountsUI;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Accounts_UI_Signup_Merge extends Accounts_UI_Common{

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getDriver(driver);
        baseUrl = getBaseUrl("com");
    }
    @Test
    public void signup_merge() throws Exception {
        driver.manage().deleteAllCookies();
        driver.navigate().to(baseUrl);
        Signup("SIGNUP_MERGE","Signup_merge");
    }
    @AfterClass
    public void closeSelenium() throws Exception {
        browserClose(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult _result) throws Exception {
        afterMethod(driver,_result);
    }

}