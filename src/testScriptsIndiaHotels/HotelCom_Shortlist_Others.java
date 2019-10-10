// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	public class HotelCom_Shortlist_Others extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	private String City = "Agra";
	private String State = "Uttar+Pradesh";
	
  @Test
  public void HotelCom_Shortlist_HomePage() throws Exception {	  
	  	driver.manage().deleteAllCookies(); 
	  	driver.get(baseUrl);
	  	hotelCom_HomepageSignIn(driver, "");
	  	driver.get(hotelSrpUrl(driver, City, State, "IN"));
	  	for(int i=0; i<=10; i++){
			if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 1)) {
				break;
				} else {
					if(textPresent(driver, "Sorry, our system is acting up", 1)){
						Reporter.log("Sorry, our system is acting up message is displayed in SRP");
						Assert.assertTrue(false);
					}
			}
	   }
	  	 if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Shortlisted_Link"), 2)) {
			   safeClick(driver, getObjectHotels("HotelCom_SRP_Shortlisted_Link"));
		   }
		   Thread.sleep(2000);
		   if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_ToBe_Shortlisted_Link"), 2)) {
			   Reporter.log("Shortlist delete is not working");
			   Assert.assertTrue(false);
		   }
		   //Verifing ADD
		   safeClick(driver, getObjectHotels("HotelCom_SRP_ToBe_Shortlisted_Link"));
		   Thread.sleep(2000);
		   if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Shortlisted_Link"), 2)) {
			   Reporter.log("Shortlist ADD is not working");
			//   Assert.assertTrue(false);
		   }

		  driver.get(baseUrl+"/hotels");
		  if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_Shortlist_Link"), 5)) {
			  Reporter.log("Shortlist link is not displayed in Homepage");
			  Assert.assertTrue(false);
		  }
		  safeClick(driver, getObjectHotels("HotelCom_HomePage_Shortlist_Link"));
		  elementVisible(driver, getObjectHotels("HotelCom_Acccounts_ShortlistPage_Header"), 5);
		  String Shortlist_Acct_Page_Text = getText(driver, getObjectHotels("HotelCom_Acccounts_ShortlistPage_Header"));
			 if(!(Shortlist_Acct_Page_Text.contains("You have a shortlist in")||Shortlist_Acct_Page_Text.contains("You have shortlists in"))) {
				 Reporter.log("You have a shortlist in is not displayed in Acct Shortlist page");
				 Assert.assertTrue(false);
			 }
  }

  @Test(dependsOnMethods= "HotelCom_Shortlist_HomePage")
  public void HotelCom_Shortlist_SRP() throws Exception {	  

			 driver.get(hotelSrpUrl(driver, City, State, "IN"));
			  	for(int i=0; i<=10; i++){
					if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 1)) {
						break;
						} else {
							if(textPresent(driver, "Sorry, our system is acting up", 1)){
								Reporter.log("Sorry, our system is acting up message is displayed in SRP");
								Assert.assertTrue(false);
							}
					}
			   }
		 safeClick(driver, getObjectHotels("HotelCom_SRP_Accounts_Dropdown"));
		 if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_AccountTab_ShortlistLink"), 5)){
			 Reporter.log("Shortlist tab is not displayed in SRP");
			 Assert.assertTrue(false);
		 }
		 
//		 driver.findElement(By.id("")).getAttribute("");
  }
		  
  @Test(dependsOnMethods= "HotelCom_Shortlist_SRP")
  public void HotelCom_Shortlist_AcctPage() throws Exception {	  
		 safeClick(driver, getObjectHotels("HotelCom_SRP_AccountTab_ShortlistLink"));
		 elementVisible(driver, getObjectHotels("HotelCom_Acccounts_ShortlistPage_Header"), 10);
		 String Shortlist_Acct_Page_Text = getText(driver, getObjectHotels("HotelCom_Acccounts_ShortlistPage_Header"));
		 if(!(Shortlist_Acct_Page_Text.contains("You have a shortlist in")||Shortlist_Acct_Page_Text.contains("You have shortlists in"))) {
			 Reporter.log("You have a shortlist in is not displayed in Acct Shortlist page");
			 Assert.assertTrue(false);
		 }
		 	elementVisible(driver, By.xpath("//*[@id='hotelsListView']/ul/li/div/div[2]/h4"), 10);
			List<WebElement> cityList =driver.findElements(By.xpath("//*[@id='hotelsListView']/ul/li"));
			for(int i =0; i<=cityList.size()-1; i++){
				int j=i+1;
				 String elementText = cityList.get(i).getText();
				 if(elementText.contains(City)){
					    	 if(!cityList.get(i).isDisplayed()){
					}				    
				    	 Thread.sleep(2000);
				    	 safeClick(driver, By.xpath("//*[@id='hotelsListView']/ul/li/div/div[2]/h4"));	
			}
			}
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			elementVisible(driver, getObjectHotels("HotelCom_ShortlistPage_Modify_Button"), 20);
			elementPresent(driver, getObjectHotels("HotelCom_ShortlistPage_CheckIN_Details"), 5);
			String TextShortlist = getText(driver, By.cssSelector("h1.shortlist__heading"));
			if(!TextShortlist.contains("Shortlisted hotel")) {
				Reporter.log("Shortlisted hotel in : text is not displayed");
				Assert.assertTrue(false);
			}
			driver.switchTo().window(tabs.get(0));
			elementVisible(driver, By.xpath("//*[@id='hotelsListView']/ul/li/div/div[2]/h4"), 10);
			for(int i =0; i<=cityList.size()-1; i++){
			 int j=i+1;
			 String elementText = "";
			 if(i==0) {
				 elementText = getText(driver, By.cssSelector("h4.citySnippet__name"));
			 }
			 else {
			 elementText = cityList.get(j).getText();
			 }
			 if(elementText.contains(City)){
				    	 if(!cityList.get(j).isDisplayed()){
				}				    
			    	 Thread.sleep(2000);
			    	 mouseHover(driver, By.xpath("//*[@id='hotelsListView']/ul/li["+j+"]/div[2]"));
			    	 Thread.sleep(1000);
			    	 safeClick(driver, By.xpath("//*[@id='hotelsListView']/ul/li["+j+"]/div[2]/a"));
			    	 Thread.sleep(1000);
			    	 driver.switchTo().alert().accept();
			    	 break;
				 }	
			     }
		  }

  @Test(dependsOnMethods= "HotelCom_Shortlist_AcctPage")
  public void HotelCom_Shortlist_DetailPage() throws Exception {	  
		driver.get(detailsPage_URL_Link(driver, "com", "714305", 5));
		elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Price"), 20);
		if(!elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Shortlist_Link"), 3)) {
			
		} else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Shortlisted_Link"), 1)) {
			safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Shortlist_Link"));
			Thread.sleep(2000);
		}
		safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Shortlisted_Link"));
		Thread.sleep(2000);
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