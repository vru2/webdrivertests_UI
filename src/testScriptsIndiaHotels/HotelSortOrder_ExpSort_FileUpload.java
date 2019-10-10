package testScriptsIndiaHotels;

import java.io.File;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;
import junit.framework.Assert;

public class HotelSortOrder_ExpSort_FileUpload extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	String firstSRPHotel=null;
	
	@Test
	public void HotelSortOrder_ExpSort_ABUpload() throws Exception{
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);	  
	 	hotelCom_AddCookie(driver);
	 	//String firsthotel=HotelCom_SRP_SortOrder(driver,"sortexp1");
	 	driver.get("https://qa2.cleartrip.com/hq");
	  	safeClick(driver, getObjectHotels("HotelCom_HQ_HotelTab"));
	  	safeClick(driver, getObjectHotels("HotelCom_HQ_PromotionHotel_Link"));
	  	safeType(driver, getObjectHotels("CHMM_Add_Hotel_City"), "Hyderabad");
	  	safeClick(driver, getObjectHotels("HotelCom_HQ_PromotionHotel_Autosuggest"));
	  	safeClick(driver, getObjectHotels("HotelCom_HQ_PromotionHotel_CityFilter"));
	  	waitForElementVisibility(driver, getObjectHotels("HotelCom_HQ_Default_SortOrder"), 50);
	  	//------------SortExp2------------------------------
	 	safeClick(driver, By.id("Sort2-checkbox"));
	 	if(!elementClickable(driver, By.xpath("//*[@id='div-Sort2']/input[3]"), 10)){
	 		Reporter.log("Not able to select Sort Expression @ check box");
	 		Assert.assertTrue(false);
	 	}
	 	//---------------File Upload------------------------
	 	File f=null;
	 	f=new File("HotelSortUpload.csv");
	 	String path="";
	 	boolean fileExist=f.exists();
	 	if(fileExist==true){
	 		path=f.getAbsolutePath();
	 		Reporter.log("File Path "+path);
	 	}else {
	 		 Reporter.log("File Not found");
	 	 }
	 	driver.findElement(By.name("Sort2")).sendKeys(path);
	 	Alert alert=driver.switchTo().alert();
	 	System.out.println(alert.getText());
	 	String response=alert.getText();
	 	if(response.equalsIgnoreCase("File Not supported!!")){
	 		Reporter.log("File upload failed");
	 		Assert.assertTrue(false);
	 	}
	 	safeClick(driver, getObjectHotels("HotelCom_HQ_Promotion_HotelTable_Update"));
	  	waitForElementVisibility(driver, By.id("sortTypeField"), 50);
	 	//=============chk srp==================================			
	  	String SRPURL = null;	
		String Check_In_Date = putDate1(60);
		String Check_Out_Date = putDate1(62);
		String domainURL = getBaseUrl( "com");
		String URL_2 = "/hotels/results?city="+"Hyderabad"+"&state="+"Telangana"+"&country="+"IN"+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1=2&children1=0&adults2=2&children2=0&adults3=2&children3=0&num_rooms=3";
		SRPURL = domainURL+URL_2;
	  	waitForElementVisibility(driver, By.id("showNearByBox"), 50);
	  	firstSRPHotel=getAttribute(driver, getObjectHotels("HotelCom_SRP_FirstHotelName"), "title");
	  	Reporter.log(firstSRPHotel);
	  	if(firstSRPHotel.contains("ITC Kakatiya Hyderabad")||firstSRPHotel.contains("Trident Hyderabad")){
	  		Reporter.log("AB upload sort order working fine");
	  	}
	  	else{
	  		Reporter.log("AB Upload sort order not working");
	  		Assert.assertTrue(false);
	  	}
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
