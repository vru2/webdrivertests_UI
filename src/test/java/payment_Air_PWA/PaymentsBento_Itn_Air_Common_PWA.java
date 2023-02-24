package test.java.payment_Air_PWA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import test.java.commonUI.PaymentsUI_Common_PWA;

public class PaymentsBento_Itn_Air_Common_PWA extends PaymentsUI_Common_PWA {
    String inurl = "https://qa2new.cleartrip.com";
    String origin = "BLR";
    String destination = "MAA";
    int date = 85;
    String phoneNo="1212121212";
    String emailID="kiran.kumar@cleartrip.com";

    public String air_SRPUrl(String Domain, String origin, String destination, int date) throws Exception {
        String Air_URL = "/flights/results?adults=1&childs=0&infants=0&class=Economy&depart_date=" + getDateTime(date, "dd/MM/yyyy") + "&from=" + origin + "&to=" + destination + "&intl=n";
        String SearchUrl = "";
        if (Domain == "IN") {
            SearchUrl = inurl + Air_URL;
        }
        Reporter.log(SearchUrl);
        System.out.println(SearchUrl);
        return SearchUrl;
    }

    public void air_SRPPage(RemoteWebDriver driver, String FlightName, String FlightNo) throws Exception {
        if (!elementVisible(driver, By.xpath("//ul/div/div/div/div[2]/div[2]"), 30)) {
            Reporter.log("SRP page not loaded");
            Assert.assertTrue(false);
        }
        textPresent(driver, "Sort", 5);
        safeClick(driver, By.xpath("//ul/div/div/div/div[2]/div[2]"));
    }

    public void air_ItnPage(RemoteWebDriver driver, String CouponGV, String CouponCode, String param1, String param2) throws Exception {
        textPresent(driver, "Review Itinerary", 10);
        if (!elementVisible(driver, By.xpath("//button"), 30)) {
            Reporter.log("Itn page not loaded");
            Assert.assertTrue(false);
        }
        if(elementVisible(driver, By.xpath("//div[@id='onetrust-close-btn-container']/button"),5)) {
            mouseHover(driver, By.xpath("//div[@id='onetrust-close-btn-container']/button"));
            safeClick(driver, By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        }
        if(CouponGV.equalsIgnoreCase("COUPON")){
            safeType(driver, By.name("coupon"),CouponCode);
            safeClick(driver, By.xpath("//div[2]/ul/div/li/p"));
            textPresent_Log(driver, "Great! You just saved", 5);
        }
        else if(CouponGV.equalsIgnoreCase("GV")){
            String[] GV = getGV(10);
            safeType(driver, By.xpath("//input"),GV[0]);
            safeType(driver, By.xpath("//li[2]/input"),GV[1]);
            safeClick(driver, By.xpath("//li[2]/p"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
        }
        else if(CouponGV.equalsIgnoreCase("GVFULL")){
            String[] GV = getGV(10000);
            safeType(driver, By.xpath("//input"),GV[0]);
            safeType(driver, By.xpath("//li[2]/input"),GV[1]);
            safeClick(driver, By.xpath("//li[2]/p"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
        }
        else if(CouponGV.equalsIgnoreCase("GVMulti")){
            String[] GV = getGV(10);
            safeType(driver, By.xpath("//input"),GV[0]);
            safeType(driver, By.xpath("//li[2]/input"),GV[1]);
            safeClick(driver, By.xpath("//li[2]/p"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
            GV = getGV(10);
            safeClick(driver, By.xpath("//div[2]/p"));
            safeType(driver, By.xpath("//div[2]/li/input"),GV[0]);
            safeType(driver, By.xpath("//li[2]/input"),GV[1]);
            safeClick(driver, By.xpath("//li[2]/p"));
            textPresent_Log(driver, "has been redeemed for this booking", 5);
        }
        textPresent_Log(driver, "Review Itinerary", 10);
        elementVisible(driver, By.xpath("//button"), 20);
        mouseHover(driver, By.xpath("//button"));
        safeClick(driver, By.xpath("//button"));
    }

    public void air_AddOnPage(RemoteWebDriver driver, String param1, String param2) throws Exception {
        elementVisible(driver, By.xpath("//div[2]/div[1]/div[3]/p"), 5);

        if(!textPresent(driver, "Add ons", 5)) {
            if (textPresent(driver, "Review Itinerary", 2)) {
                mouseHover(driver, By.xpath("//button"));
                safeClick(driver, By.xpath("//button"));
            }
        }
        if(!textPresent(driver, "Review Travellers", 2)) {
          if (textPresent(driver, "Skip", 5)) {
                mouseHover(driver, By.xpath("//div[2]/div[1]/div[3]/p"));
                safeClick(driver, By.xpath("//div[2]/div[1]/div[3]/p"));
                elementVisible(driver, By.linkText("Exit without saving"), 5);
                safeClick(driver, By.linkText("Exit without saving"));
            }
        }
        if(textPresent(driver, "Review Itinerary", 2)){
            mouseHover(driver, By.xpath("//button"));
            safeClick(driver, By.xpath("//button"));
        }
    }

    public void air_TravellerPage(RemoteWebDriver driver, String Login, String phoneNo, String emailID, String Param) throws Exception {
        textPresent_Log(driver, "Review Travellers", 20);
        safeSelect(driver, By.name("title"),"Mr");
        safeType(driver, By.name("firstName"),"Kiran");
        safeType(driver, By.name("lastName"),"Kumar");
        if(elementVisible(driver, By.xpath("//div[5]/li"),1)){
            String Nationality_DOB = getText(driver, By.xpath("//div[5]/li"));
            if(Nationality_DOB.contains("Natinality")){

                safeClick(driver, By.xpath("//div[5]/li"));
                textPresent(driver, "Select Country", 5);
                safeType(driver, By.xpath("//div[2]/div[2]/div[2]/input"), "India");
                Thread.sleep(2000);
                mouseHover(driver, By.xpath("//div[2]/div/ul/li"));
                safeClick(driver, By.xpath("//div[2]/div/ul/li"));
            }
           else if(Nationality_DOB.contains("Date of birth")){
               mouseHover(driver, By.name("dob"));
                Actions actions = new Actions(driver);
                WebElement elementLocator = driver.findElement(By.name("dob"));
                actions.doubleClick(elementLocator).perform();
               mouseHover(driver, By.name("dob"));
               safeClick(driver, By.name("dob"));
                actions.moveToElement(elementLocator);
                actions.clickAndHold(elementLocator);
               // Thread.sleep(1000);
                actions.sendKeys("04").perform();
                //Thread.sleep(1000);
                actions.sendKeys("12").perform();
                //Thread.sleep(1000);
                actions.sendKeys("2000").perform();
              //  actions.sendKeys("00").perform();
            }
        }
        safeType(driver, By.name("phone"),phoneNo);
        safeType(driver, By.name("email"),emailID);
        scrollSmooth(driver, 200);
        elementVisible(driver, By.xpath("//button"),2);
        mouseHover(driver, By.xpath("//button"));
        safeClick(driver, By.xpath("//button"));
    }

}