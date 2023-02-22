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
       /* driver.get("https://qa2new.cleartrip.com/bus/results?fromCity=4292&toCity=4562&journeyDate=2023-02-24&fromCityName=Bengaluru&toCityName=Chennai");
        System.out.println(getURL(driver));
       // scrollToElement(driver, By.xpath("//div[6]/div/div[2]"));
        Thread.sleep(5000);
        safeClick(driver, By.xpath("//div[6]/div/div[2]"));
        Thread.sleep(5000);*/
        driver.get("https://qa2new.cleartrip.com/bus/chart?solutionId=4292|4562|2023-02-24|19|0&searchId=4292_4562_2023-02-24_1677058716570&operatorName=GDS%20Demo%20Test&busType=Non%20A/C,%20Seater%20Sleeper,%20Deluxe&deptTime=2023-02-24T23:00:00&pricePerSeat=10689&itineraryId=");
        WebElement we = driver.findElement(By.cssSelector("canvas"));
        Actions builder = new Actions(driver);
        safeClick(driver, By.id("lgnd"));
        Boolean selectedSeat = elementVisible(driver, By.xpath("//div[3]/section/canvas"), 1);
        int[] Bus_X = new int[] { 60, 60, 60,  60, 60, 180 , 180, 180, 180};
        int[] Bus_Y = new int[] { 50, 70 , 140,  -140, -160, 140, 70, 140, -140};
        for (int k=0; k<= Bus_X.length-1; k++){
            System.out.println(Bus_X[k] +"  "+ Bus_Y[k]);
            builder.moveToElement(we, Bus_X[k], Bus_Y[k]).click().build().perform();
            //safeClick(driver, By.xpath("//canvas"));
           // safeClick(driver, By.id("lgnd"));
            Thread.sleep(1000);
        }

        builder.moveToElement(we, 180, 5).click().build().perform();
        //safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 180, 140).click().build().perform();
        // safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 180, -150).click().build().perform();
        // safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 250, 5).click().build().perform();
        //safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 300, 5).click().build().perform();
        //  safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 280, 140).click().build().perform();
        // safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 260, -150).click().build().perform();
        // safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 60, 5).click().build().perform();
        //safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 60, 5).click().build().perform();
      //  safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 60, 140).click().build().perform();
       // safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 60, -150).click().build().perform();
        // safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);

        builder.moveToElement(we, 60, -160).click().build().perform();
        // safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);

        builder.moveToElement(we, 60, -170).click().build().perform();
        // safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);

        builder.moveToElement(we, 5, -190).click().build().perform();
        // safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);

        builder.moveToElement(we, 5, -140).click().build().perform();
        // safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 60, -140).click().build().perform();
        // safeClick(driver, By.id("lgnd"));
        builder.moveToElement(we, 5, -280).click().build().perform();
        safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 60, -140).click().build().perform();
        safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 60, 140).click().build().perform();
        safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 60, 70).click().build().perform();
        safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, 60, -70).click().build().perform();
        safeClick(driver, By.id("lgnd"));
        Thread.sleep(1000);
        builder.moveToElement(we, -80, 20).click().build().perform();
        safeClick(driver, By.id("lgnd"));
        builder.moveToElement(we, -80, 140).click().build().perform();
        safeClick(driver, By.id("lgnd"));
        builder.moveToElement(we, -80, -140).click().build().perform();
        safeClick(driver, By.id("lgnd"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(50,0)", "");
        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            js.executeScript("window.scrollBy(10,10)", "");
            safeClick(driver, By.id("lgnd"));
            builder.moveToElement(we, 5, 10).click().build().perform();
        }
        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            safeClick(driver, By.id("lgnd"));
            js.executeScript("window.scrollBy(20,-50)", "");
            builder.moveToElement(we, 60, 60).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }
        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            safeClick(driver, By.id("lgnd"));
            js.executeScript("window.scrollBy(20,-50)", "");
            builder.moveToElement(we, 5, 100).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }
        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            safeClick(driver, By.id("lgnd"));
            js.executeScript("window.scrollBy(20,-50)", "");
            builder.moveToElement(we, 80, 100).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }
        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            js.executeScript("window.scrollBy(10,-50)", "");
            safeClick(driver, By.id("lgnd"));
            //builder.moveToElement(we, 5, -20).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }
        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            js.executeScript("window.scrollBy(-20,-50)", "");
            safeClick(driver, By.id("lgnd"));
            // builder.moveToElement(we, 5, -40).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }
        if(!elementVisible(driver, By.xpath("//button122"), 1)){// working
            js.executeScript("window.scrollBy(-30,-50)", "");
            safeClick(driver, By.id("lgnd"));
           builder.moveToElement(we, 5, -80).click().build().perform();
        }
        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            js.executeScript("window.scrollBy(-50,-50)", "");
            safeClick(driver, By.id("lgnd"));
          // builder.moveToElement(we, 5, 80).click().build().perform();
        }

        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            safeClick(driver, By.id("lgnd"));
            js.executeScript("window.scrollBy(-50,-50)", "");
            builder.moveToElement(we, 60, 100).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }
        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            safeClick(driver, By.id("lgnd"));
            js.executeScript("window.scrollBy(-50,-50)", "");
//            builder.moveToElement(we, -80, 80).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        } if(!elementVisible(driver, By.xpath("//button122"), 1)){
            safeClick(driver, By.id("lgnd"));
            js.executeScript("window.scrollBy(-50,-50)", "");
          //  builder.moveToElement(we, -80, 5).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }
        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            safeClick(driver, By.id("lgnd"));
            js.executeScript("window.scrollBy(-50,-50)", "");
            builder.moveToElement(we, -80, 20).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }

        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            safeClick(driver, By.id("lgnd"));
            js.executeScript("window.scrollBy(-20,-60)", "");
            builder.moveToElement(we, -80, 100).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }

        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            safeClick(driver, By.id("lgnd"));
            js.executeScript("window.scrollBy(-50,-50)", "");
            builder.moveToElement(we, -80, -80).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }
        if(!elementVisible(driver, By.xpath("//button122"), 1)){
            safeClick(driver, By.id("lgnd"));
            js.executeScript("window.scrollBy(-50,-50)", "");
       //     builder.moveToElement(we, -80, -20).click().build().perform();
            safeClick(driver, By.xpath("//p"));
        }
        Thread.sleep(50000);
        safeClick(driver, By.xpath("//button"));
        Thread.sleep(50000);
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
