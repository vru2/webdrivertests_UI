// Framework - 	Cleartrip Automation
// Author - 			Kiran Kumar

package testScriptsIndiaHotels;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_SearchTag_Create extends IndiaHotels{
	public RemoteWebDriver driver;
	public String baseUrl;
	public String HotelID = "313380";
	String TagName =  ranstring();
	   
	@Test
	public void SearchTagCreate() throws Exception {
		   driver.manage().deleteAllCookies();
		   driver.get(baseUrl);
			hotelCom_AddCookie(driver);
		   hotelCom_Open_HQ(driver);
		   safeClick(driver, getObjectHotels("HotelCom_HQ_HotelTab"));
		   elementVisible(driver, getObjectHotels("HotelCom_HQ_Hotel_Tags"), 5);
		   safeClick(driver, getObjectHotels("HotelCom_HQ_Hotel_Tags"));
		   elementVisible(driver, getObjectHotels("HotelCom_HQ_Hotel_Tags_Text"), 5);
		   String CreateTagText = getText(driver, getObjectHotels("HotelCom_HQ_Hotel_Tags_Text")); 
		   if(!CreateTagText.equals("New Tag Creation")) {
			   Reporter.log("New Tag Creation text is not displayed");
			   Assert.assertTrue(false);
		   }
		   Reporter.log("TagName : "+TagName);
		   safeType(driver, getObjectHotels("HotelCom_HQ_Hotel_Tags_TextBox"), TagName);
		   safeClick(driver, getObjectHotels("HotelCom_HQ_Hotel_Tags_Submit"));		   
		   Thread.sleep(2000);
	}

			@Test (dependsOnMethods={"SearchTagCreate"})
			public void SearchTag_Add2Hotel() throws Exception {		   
			   driver.get("https://qa2.cleartrip.com/hq/hotels/"+HotelID);
			   safeClick(driver, By.linkText("Edit hotel"));
			   safeClick(driver, By.linkText("Search Tags"));
			   Thread.sleep(3000);
			   List<WebElement> we = driver.findElements(By.cssSelector("fieldset > div > label"));  
			   List<WebElement> we1 = driver.findElements(By.xpath("//fieldset/div/input"));
			   for(int i=0; i<we1.size(); i++) {
				     if(we.get(i).getText().contains(TagName)) {
			    		we1.get(i).click();
			    		break;
				   }		    	
			   }  
	
			   safeClick(driver, By.cssSelector("input[type=\"submit\"]"));
			   Thread.sleep(5000);
			   if(!textPresent(driver, "is updated.", 10)) {
				   Reporter.log("Hotel is not updated ");
				   Assert.assertTrue(false);
			   }
			  // driver.get("http://hq-settings.cltp.com:9001/r3/hq-settings_r3/rails_cache_load");
			}
			
			@Test (dependsOnMethods={"SearchTag_Add2Hotel"})
			public void SearchTag_A_VerifyDetailsPage() throws Exception {   
			
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
			
			@Test (dependsOnMethods={"SearchTag_Add2Hotel"})
					public void SearchTag_Delete() throws Exception {   
				   hotelCom_Open_HQ(driver);
				   safeClick(driver, getObjectHotels("HotelCom_HQ_HotelTab"));
				   elementVisible(driver, getObjectHotels("HotelCom_HQ_Hotel_Tags"), 5);
				   safeClick(driver, getObjectHotels("HotelCom_HQ_Hotel_Tags"));
				   elementVisible(driver, getObjectHotels("HotelCom_HQ_Hotel_Tags_Text"), 5);
				   List<WebElement> wl = driver.findElements(By.xpath("//tr/td[1]"));
				   List<WebElement> wl2 = driver.findElements(By.xpath("//a[2]"));
				   for(int i=0; i<=wl.size()-1;i++) {
				  String TagNameList = wl.get(i).getText();
				   if(TagNameList.equals(TagName)) {				  
						String DeleteXpath = "//tr["+i+"]/td[2]/a[2]";
						safeClick(driver, By.xpath(DeleteXpath));
						Thread.sleep(2000);   
					 	driver.switchTo().alert().accept();			
					 	break;
				   }
				   }
				   Thread.sleep(5000);	
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