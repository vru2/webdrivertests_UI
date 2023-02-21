package test.java.paymentBento_Bus_PWA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_PWA_Air;

public class IN_Bus_PWA_NB extends PaymentsUI_Common_PWA_Air {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver =  getMobileDriver(driver);
    }

    @Test
    public void Bus_PWA_NB() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get("https://qa2new.cleartrip.com/bus/results?fromCity=4292&toCity=4562&journeyDate=2023-02-24&fromCityName=Bengaluru&toCityName=Chennai");
        Thread.sleep(5000);
        safeClick(driver, By.xpath("//div[5]/div/div/div/div[2]"));
        Thread.sleep(5000);
        WebElement we = driver.findElement(By.cssSelector("canvas"));
        Actions builder = new Actions(driver);
        Boolean selectedSeat = elementVisible(driver, By.xpath("//button"), 1);

        builder.moveToElement(we, 5, 5).click().build().perform();

        if(!elementVisible(driver, By.xpath("//button"), 1)){
            builder.moveToElement(we, 20, 30).click().build().perform();
        }
        if(!elementVisible(driver, By.xpath("//button"), 1)){
            builder.moveToElement(we, 20, 50).click().build().perform();
        }
        if(!elementVisible(driver, By.xpath("//button"), 1)){
            builder.moveToElement(we, 100, 150).click().build().perform();
        }
        if(!elementVisible(driver, By.xpath("//button"), 1)){
            builder.moveToElement(we, 20, 40).click().build().perform();
        }
        safeClick(driver, By.xpath("//button"));
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
