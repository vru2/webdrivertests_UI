package testScriptsIndiaHotels;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;
import junit.framework.Assert;

public class HotelCom_SRP_GoogleSearch extends IndiaHotels{
	public RemoteWebDriver driver;
	
	@Test
	public void HotelCom_SRP_GoogleAddressSearch() throws Exception{
		SRP_URL(driver,"com","Pune","Maharashtra","35943");
		hotelCom_AddCookie(driver);
		safeClick(driver, getObjectHotels("HotelCom_SRP_GoogleSearch"));
		safeType(driver, getObjectHotels("HotelCom_SRP_GoogleSearch"), "Hotel Basera");
		safeType_NonClear(driver, By.id("showNearByBox"), "Hotel Basera");
		Thread.sleep(2000);		
		 List<WebElement> elements = driver.findElements(By.className("pac-item"));
		 try{
		 for(WebElement element : elements)
		    {
		    	WebDriverWait wait = new WebDriverWait(driver, 10);
		        Thread.sleep(2000);
		        if(element.getAttribute("innerHTML").contains("Hotel Basera")){
		        	element.click();
		        }
		        else{
		        	Reporter.log("Google address search failed");
		        	Assert.assertTrue(false);
		        }
		    }
		 }catch(Exception e){
			 Reporter.log("StaleElementReferenceException Exception handled");
		 }
		 if(elementVisible(driver, getObjectHotels("HotelCom_SRP_MapPinLocality"), 10)){
			 for (int i = 0; i < 20; i++) {
				 if(elementNotVisible(driver, getObjectHotels("HotelCom_SRP_ShowNearByHotels_BlockButton"), 5)){
					 JavascriptExecutor jse = (JavascriptExecutor) driver;
					 jse.executeScript("window.scrollBy(0, 750)", "");	
				 }
			 else{
				 String msg=getText(driver, getObjectHotels("HotelCom_SRP_ShowNearByHotels_Block"));
				 Reporter.log("Block displayed- "+msg);
				 break;
			 	}
			}
		}
		 else{
			 Reporter.log("Google Address not selected");
		 }
	}
	
@BeforeClass
public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
}  

@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

@AfterClass
public void tearDown() throws Exception {
	  browserClose(driver);
}
}