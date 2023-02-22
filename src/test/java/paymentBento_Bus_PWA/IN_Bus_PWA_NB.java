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
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.JavascriptExecutor;


public class IN_Bus_PWA_NB extends PaymentsUI_Common_PWA_Air {

    @BeforeClass
    public void startSelenium() throws Exception {
        this.driver =  getMobileDriver(driver);
    }

    @Test
    public void Bus_PWA_NB() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get("https://qa2new.cleartrip.com/bus/results?fromCity=4292&toCity=4562&journeyDate=2023-02-24&fromCityName=Bengaluru&toCityName=Chennai");
        System.out.println(getURL(driver));
       // scrollToElement(driver, By.xpath("//div[6]/div/div[2]"));
        Thread.sleep(5000);
        safeClick(driver, By.xpath("//div[6]/div/div[2]"));
        Thread.sleep(5000);
       // driver.get("https://qa2new.cleartrip.com/bus/chart?solutionId=4292|4562|2023-02-24|19|0&searchId=4292_4562_2023-02-24_1677058716570&operatorName=GDS%20Demo%20Test&busType=Non%20A/C,%20Seater%20Sleeper,%20Deluxe&deptTime=2023-02-24T23:00:00&pricePerSeat=10689&itineraryId=");
        WebElement we = driver.findElement(By.cssSelector("canvas"));
        Actions builder = new Actions(driver);
        safeClick(driver, By.id("lgnd"));
        Boolean selectedSeat = elementVisible(driver, By.xpath("//div[3]/section/canvas"), 1);
        int[] Bus_X = new int[] { 60, 60, 60,  60, 60, 60, 180 , 180, 180, 180, 180, 220, 220};
        int[] Bus_Y = new int[] { 10, 70 , 140,  -140, -150, -170, 10, 170, 80, -140, 10, 70, -140};
        for (int k=0; k<= Bus_X.length-1; k++){
            builder.moveToElement(we, Bus_X[k], Bus_Y[k]).click().build().perform();
            Thread.sleep(2000);
            if(elementVisible(driver, By.xpath("//button"), 2)){
                safeClick(driver, By.xpath("//button"));
                break;
            }
        }
        Thread.sleep(5000);
        textPresent(driver, "Choose pick-up point", 10);
        safeClick(driver, By.xpath("//div[2]/div[2]"));
        safeClick(driver, By.xpath("//button"));
        textPresent(driver, "Choose drop-off point", 10);
        safeClick(driver, By.xpath("//div[2]/div[2]"));
        safeClick(driver, By.xpath("//button"));
        Thread.sleep(5000);

        textPresent(driver, "Traveller details", 10);
        safeClick(driver, By.xpath("//div[2]/div/div/div/p"));
        safeType(driver, By.xpath("//input"), "Kiran");
        safeType(driver, By.xpath("//div[4]/div/input"), "Kumar");
        safeType(driver, By.xpath("//div[5]/div/input"), "12");
        safeType(driver, By.id("mobile"), "1212121212");
        safeType(driver, By.id("email"), "kiran.kumar@cleartrip.com");
        safeClick(driver, By.xpath("//button"));
        textPresent(driver, "Review booking", 10);
        safeClick(driver, By.xpath("//button"));

        Thread.sleep(5000);




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
