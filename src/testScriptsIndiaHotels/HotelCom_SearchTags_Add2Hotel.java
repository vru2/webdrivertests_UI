// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;
import junit.framework.Assert;

	public class HotelCom_SearchTags_Add2Hotel extends IndiaHotels{
	public RemoteWebDriver driver;
	public String baseUrl;
	public String HotelID = "313380";
	
  @Test
  public void SRP_SearchTags() throws Exception {
	  String TagNo = getRandomNos(7);
	  int TagNoInt= Integer.parseInt(TagNo);
	   driver.manage().deleteAllCookies();
	   driver.get(baseUrl);
		hotelCom_AddCookie(driver);
	   driver.get("https://qa2.cleartrip.com/hq/hotels/"+HotelID);
	   safeClick(driver, By.linkText("Edit hotel"));
	   safeClick(driver, By.linkText("Search Tags"));
	   Thread.sleep(5000);
	   List<WebElement> we = driver.findElements(By.cssSelector("fieldset > div > label"));  
	   List<WebElement> we1 = driver.findElements(By.xpath("//fieldset/div/input"));
	    for(int i=0; i<we1.size()-1; i++) {
	    	if(we1.get(i).isSelected()) {
	    		we1.get(i).click();
	    		Thread.sleep(500);
	    	}
	}
		Thread.sleep(2000);
	   String TagName = we.get(TagNoInt).getText();
	   
	   we1.get(TagNoInt).click();
	   safeClick(driver, By.xpath("//input[@value='Save hotel']"));
	   Thread.sleep(2000);
	   if(!textPresent(driver, "is updated.", 10)) {
		   Reporter.log("Hotel is not updated ");
		   Assert.assertTrue(false);
	   }
	   driver.get("https://qa2.cleartrip.com/hotels/details/"+HotelID);
	   elementVisible(driver, By.cssSelector("h5.tagsInlineContainerHeader"), 10);
	   if(elementVisible(driver, By.id("showMoreTags"), 1)) {
		   safeClick(driver, By.id("showMoreTags"));
	   }
	   if(!elementVisible(driver, By.linkText(TagName), 5)) {
		   Reporter.log("Search tag is not displayed in Details Page : Tag Name - "+TagName);
		   Assert.assertTrue(false);
	   }
  } 

	public static String ranstring() {
		String str = "abcdefghijklmnopqrstuvwxyz";
		int len =5;
		Random rndm_method = new Random(); 
        char[] ranString = new char[len];
		for(int i =0; i<len; i++) {
			ranString[i] = str.charAt(rndm_method.nextInt(str.length()));			
		}
		String RanString = String.valueOf(ranString);
		return RanString;
	}
  
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
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