package test.java.accountsPWAUI;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.common.WrapperMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Accounts_PWA_UI_Common extends WrapperMethod {

    RemoteWebDriver driver;

    @Test
    public void openBrowser() throws Exception {
        //this.driver = getDriver(driver);
       // baseUrl = getBaseUrl("com");
        System.setProperty("webdriver.chrome.silentOutput","true");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//exe//chromedriver_mac");
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", "Samsung Galaxy S20 Ultra");

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(412,915));
        Object webDriver = driver;
        ((JavascriptExecutor) webDriver).executeScript("window.focus();");
         driver.navigate().to("https://qa2new.cleartrip.com");
        System.out.println("We are currently on the following URL" +driver.getCurrentUrl());
        waitForElementToBeClickable(driver,By.xpath("//div[2]/div[1]/div[1]/div[4]"));
        safeClick(driver,By.xpath("//div[2]/div[1]/div[1]/div[4]"));
        elementPresent(driver,By.xpath("/div[1]/button[1]"));
        safeClick(driver, By.xpath("//div[1]/button[1]"));
        Thread.sleep(10000);
        textPresent_Log(driver,"Get OTP",2);
        safeClick(driver,By.xpath("//div[2]/div[1]/input[1]"));
        Thread.sleep(1000);
        safeType(driver,By.xpath("//div[2]/div[1]/input[1]"),"3456789021");
        safeClick(driver,By.xpath("//div[contains(text(),'Get OTP')]"));
        driver.manage().window().setSize(new Dimension(412,915));
        driver.manage().deleteAllCookies();
        driver.quit();

    }


}
