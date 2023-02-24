package test.java.accountsUI;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Accounts_UI_Link_Mobile extends Accounts_UI_Common{
    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver = getDriver(driver);
        baseUrl = getBaseUrl("com");
    }
    @Test
    public void link_mobile() throws Exception {
        driver.manage().deleteAllCookies();
        driver.navigate().to(baseUrl);
        Signup("UPDATE_MOBILE","Link_Phone", mobile1, mobile2);
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