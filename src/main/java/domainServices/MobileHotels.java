// Framework - Cleartrip Automation
// Author - Kiran Kumar

package domainServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

public class MobileHotels extends IndiaHotels {
	public String commonURL;
	public RemoteWebDriver driver; 	
	private String baseUrl = "https://"+common.value("host")+common.value("url")+"com";
	private String baseUrl_AE = "https://"+common.value("host")+common.value("url")+"ae";
	private String baseUrl_OM = "https://om"+common.value("url")+"com";
	private String baseUrl_KW = "https://kw"+common.value("url")+"com";
	private String baseUrl_BH =  "https://bh"+common.value("url")+"com";
	private String baseUrl_QA =  "https://qa"+common.value("url")+"com";
	private String baseUrl_SA =  "https://www"+common.value("url")+"sa";
	private String baseUrl_ME =  "https://me"+common.value("url")+"com";
	public String TripID = null;
	String Airline="";
	
	public void mobileCom_SignIn(RemoteWebDriver driver) throws Exception 
	{
		driver.navigate().refresh();
		Reporter.log("Signing In...", true);

		scrollToElement(driver, getObject("MobileCom_Air_HomePage_signin"));
		safeClick(driver,getObject("MobileCom_Air_HomePage_signin"));
		
		if(!waitForElementVisibility(driver, getObject("MobileCom_Air_HomePage_UserName"), 30))
		{
			Reporter.log("Sign In Page not Loadedin 30 seconds.", true);
			Assert.fail("Sign In page not Loaded!");
		}
		safeType(driver,getObject("MobileCom_Air_HomePage_UserName"),dataFile.value("Mobile_EmailID"));
		safeType(driver,getObject("MobileCom_Air_HomePage_Pwd"),dataFile.value("Mobile_Password"));
		safeClick(driver,getObject("MobileCom_Air_HomePage_SignIN_Button"));
		
		
	}
	
		
	public void mobileCom_Hotel_SignIn(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		//safeClick(driver, getObject("MobileCom_Air_HomePage_Flight_Tab"));
		Thread.sleep(2000);
	if(elementPresent(driver,By.id("menuBtn"),1)){
		safeClick(driver,By.id("menuBtn"));
	}
		safeClick(driver, getObject("MobileCom_Hotel_HomePage_Hotel_Tab"));
		safeClick(driver,getObject("MobileCom_Air_HomePage_signin"));
		elementVisible(driver,getObject("MobileCom_Air_HomePage_UserName"), 10);
		safeType(driver,getObject("MobileCom_Air_HomePage_UserName"),dataFile.value("HotelEmailID"));
		safeType(driver,getObject("MobileCom_Air_HomePage_Pwd"),dataFile.value("HotelPassword"));
		Thread.sleep(2000);
		safeClick(driver,getObject("MobileCom_Air_HomePage_SignIN_Button"));
	}
	
	public void mobileCom_SignIn1(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		//safeClick(driver, getObject("MobileCom_Air_HomePage_Flight_Tab"));
		Thread.sleep(2000);
	if(elementPresent(driver,By.id("menuBtn"),1)){
		safeClick(driver,By.id("menuBtn"));
	}
		safeClick(driver,getObject("MobileCom_Air_HomePage_signin"));
		Thread.sleep(2000);
		safeType(driver,getObject("MobileCom_Air_HomePage_UserName"),dataFile.value("Mobilehotel_EmailID"));
		Thread.sleep(2000);
		safeType(driver,getObject("MobileCom_Air_HomePage_Pwd"),dataFile.value("Mobilehotel_Password"));
		Thread.sleep(2000);
		safeClick(driver,getObject("MobileCom_Air_HomePage_SignIN_Button"));
		Thread.sleep(5000);
		
	}
	
public String getModifiedDate(RemoteWebDriver driver,String date) {
	Calendar c = Calendar.getInstance();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	c.add(Calendar.DATE, Integer.parseInt(date));
	String convertedDate = dateFormat.format(c.getTime());
	return convertedDate;
	
}

	
	public String getSRPPrice(RemoteWebDriver driver) throws Exception{
		String SRP=getText(driver,By.xpath("//dl[@class='kvp']/dd[3]")).split(" ")[1].replace(",","");
		return SRP;
	}
	
	

		
	
	public String getMobile_Web_URL()
	{
		if(common.value("host").equalsIgnoreCase("qa2"))
		{
			Reporter.log("URL: " + common.value("murl"), true);
			return common.value("murl");
		}
		else if(common.value("host").equalsIgnoreCase("www"))
		{
			Reporter.log("URL: " + common.value("murl_prod"), true);
			return common.value("murl_prod");
		}
		else
		{
			Reporter.log("Unable to construct murl for the specified host. Please check host value in common.properties", true);
			Assert.fail("Failed!");
			return null;
		}
	}
	
	/*
	 * Added by: prashanth.k@cleartrip.com
	 * This method returns the mobile URL based on the value that has been set for host in common.properties
	 */
	public String getMobile_Web_URL_ME(String domain)
	{
		if(domain.equalsIgnoreCase("ae"))
		{
			if(common.value("host").equalsIgnoreCase("qa2"))
			{
				Reporter.log("URL: " + common.value("murl_ae"), true);
				return common.value("murl_ae");
			}
			else if(common.value("host").equalsIgnoreCase("www"))
			{
				Reporter.log("URL: " + common.value("murl_prod_ae"), true);
				return common.value("murl_prod_ae");
			}
			else
			{
				Reporter.log("Unable to construct murl for the specified host. Please check host value in common.properties", true);
				Assert.fail("Failed!");
				return null;
			}
		}
		else if(domain.equalsIgnoreCase("sa"))
		{
			if(common.value("host").equalsIgnoreCase("qa2"))
			{
				Reporter.log("URL: " + common.value("murl_sa"), true);
				return common.value("murl_sa");
			}
			else if(common.value("host").equalsIgnoreCase("www"))
			{
				Reporter.log("URL: " + common.value("murl_prod_sa"), true);
				return common.value("murl_prod_sa");
			}
			else
			{
				Reporter.log("Unable to construct murl for the specified host. Please check host value in common.properties", true);
				Assert.fail("Failed!");
				return null;
			}
		}
		return null;
	}
	
	//---------------------------------------------------------Hotel Scripts---------------------------------------------------//
	
	public void mobileCom_Hotel_HomepageSearch(RemoteWebDriver driver, String City, String From_Date, String To_Date, String Adults, String Childrens) throws Exception {
		  driver.navigate().refresh();
		  if(elementVisible(driver,By.id("menuBtn"),1)){
			  safeClick(driver,By.id("menuBtn"));
		  }
		  if(elementVisible(driver, getObject("MobileCom_Hotel_HomePage_Hotel_Tab"),1)){
		  safeClick(driver, getObject("MobileCom_Hotel_HomePage_Hotel_Tab"));	
		  }
		  safeAutocomplete(driver, getObject("MobileCom_Hotel_HomePage_City"), getObject("MobileCom_Hotel_HomePage_Hotel_Ajax"), City);
		  selectCalendarDate(driver, getObject("MobileCom_Hotel_HomePage_CheckIN_Calendar"), getObject("MobileCom_Hotel_HomePage_Calendar_NextMonth"), 1, From_Date);
		  Thread.sleep(1000);
		  selectCalendarDate(driver, getObject("MobileCom_Hotel_HomePage_CheckOut_Calendar"), getObject("MobileCom_Hotel_HomePage_Calendar_NextMonth"), 0, To_Date); 
		  safeSelect(driver, getObject("MobileCom_Hotel_HomePage_Adults_DropDown"), Adults);
		  safeSelect(driver, getObject("MobileCom_Hotel_HomePage_Childrens_DropDown"), Childrens);
		 // try{
		 // safeClick(driver, getObject("MobileCom_Hotel_HomePage_Search_Button_For_Single_Pax"));
		 // }
		  //catch(Exception e){ 
			  safeClick(driver,By.id("submitBtn"));
		  //}
	}
	
	public void mobileCom_Hotel_HomepageMultiPaxRoom(RemoteWebDriver driver, String City, String From_Date, String To_Date, String Rooms, String AdultsInRoom1, String AdultsInRoom2, String ChildInRoom1, String ChildInRoom2, String ChildAgeInRoom1, String ChildAgeInRoom2, String Hotel_Name, String Trip_Logger_Msg) throws Exception{
		driver.navigate().refresh();
		if(elementVisible(driver,By.id("menuBtn"),1)){
			  safeClick(driver,By.id("menuBtn"));
		  }
		safeClick(driver, getObject("MobileCom_Hotel_HomePage_Hotel_Tab"));
		safeAutocomplete(driver, getObject("MobileCom_Hotel_HomePage_City"), getObject("MobileCom_Hotel_HomePage_Hotel_Ajax"), City);
		selectCalendarDate(driver, getObject("MobileCom_Hotel_HomePage_CheckIN_Calendar"), getObject("MobileCom_Hotel_HomePage_Calendar_NextMonth"), 1, From_Date);
		Thread.sleep(1000);
		selectCalendarDate(driver, getObject("MobileCom_Hotel_HomePage_CheckOut_Calendar"), getObject("MobileCom_Hotel_HomePage_Calendar_NextMonth"), 0, To_Date);
		Select RoomsDropDown = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_Rooms_DropDown")));
		RoomsDropDown.selectByVisibleText(Rooms);
		Select AdultDropDown1 = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_AdultsInRoom1")));
		AdultDropDown1.selectByVisibleText(AdultsInRoom1);
		Select AdultDropDown2 = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_AdultsInRoom2")));
		AdultDropDown2.selectByVisibleText(AdultsInRoom2);
		Select ChildDropDown1 = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_ChildInRoom1")));
		ChildDropDown1.selectByVisibleText(ChildInRoom1);
		Select ChildDropDown2 = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_ChildInRoom2")));
		ChildDropDown2.selectByVisibleText(ChildInRoom2);
		Select ChildAgeDropDown1 = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_ChildAgeInRoom1")));
		ChildAgeDropDown1.selectByVisibleText(ChildAgeInRoom1);
		Select ChildAgeDropDown2 = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_ChildAgeInRoom2")));
		ChildAgeDropDown2.selectByVisibleText(ChildAgeInRoom2);
		safeClick(driver, getObject("MobileCom_Hotel_HomePage_Search_Button_MultiPax"));
	}	
	
	public void mobileCom_Hotel_Package_Rates(RemoteWebDriver driver, String Hotel_Name) throws Exception {
		  elementNotVisible(driver, getObject("MobileCom_Hotel_SRP_HoldOn_Search_Message"), 60);
		  String srpText = getText(driver, getObject("MobileCom_Hotel_SRP_Text"));
		  Assert.assertEquals("Price", srpText);
		  safeClick(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"));
		  for(int i=1; i<=10; i++){
			  String Hotel_SRP_XPATH = "//div/ul/li["+i+"]/div";
		  if(elementPresent_Time(driver, By.xpath(Hotel_SRP_XPATH), 5)){
			  safeClick(driver, By.xpath(Hotel_SRP_XPATH));
			  elementVisible(driver, getObject("MobileCom_Hotel_DetailsPage_Select_Room"), 50);
			  safeClick(driver, getObject("MobileCom_Hotel_DetailsPage_Select_Room"));
			  for(int j=2; j<=5; i++){
				  String RoomType_Xpath = "//section/nav/ul["+j+"]/li[1]";
				  if(elementPresent_Time(driver, By.xpath(RoomType_Xpath), 1)){
					  String RoomType_Text = getText(driver, By.xpath(RoomType_Xpath));
					  if(RoomType_Text.contains("Package")){
						  safeClick(driver, By.xpath(RoomType_Xpath));
						  i=11;
						  break;
					  }
				  }
				  
			  } driver.navigate().back();
			  driver.navigate().back();		  
		  }
		  }
	}
	
	public void mobileCom_Hotel_SRP(RemoteWebDriver driver, String Hotel_Name) throws Exception {
		Thread.sleep(2000);
		//logURL(driver);
		if(!elementPresent(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"),5)){/*
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("iframe size="+size);
		Reporter.log("iframe size="+size);
		if(size==1){
			driver.switchTo().frame(0);
		}
		else if(size==2){
			driver.switchTo().frame(1);
		}
		else if(size==3){
			driver.switchTo().frame(2);
		}
		else if(size==4){
			driver.switchTo().frame(3);
		}
		if(elementPresent(driver,By.id("closeBanner"),2)){
		//driver.switchTo().frame(1);
		driver.findElement(By.id("closeBanner")).click();
		}
		*/}
		  /*elementVisible(driver, getObject("MobileCom_Hotel_SRP_HoldOn_Search_Message"),600);
		  String srpText = getText(driver, getObject("MobileCom_Hotel_SRP_Text"));
		  Assert.assertEquals("Price", srpText);*/
		  elementVisible(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"), 10);
		  smartClick(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"));
		  
		  if(logURL(driver).contains("me.cleartrip")) {
			  safeClick(driver,By.xpath("//ul[@id='popSort']/li[4]/a"));
		  }
		  if(!Hotel_Name.isEmpty()){
			  elementPresent_log(driver, getObject("MobileCom_Hotel_SRP_SearchBar"), "Search bar in SRP ", 10);
			  Thread.sleep(1000);
			  safeType(driver, getObject("MobileCom_Hotel_SRP_SearchBar"), Hotel_Name);
			  Thread.sleep(1000);
			  safeClick(driver, getObject("MobileCom_Hotel_SRP_SearchBar"));
			  mouseHoverClick(driver, getObject("MobileCom_Hotel_SRP_HotelSearch_Button"));
			  Thread.sleep(1000);
			 // safeClick(driver, getObject("MobileCom_Hotel_SRP_HotelSearch_Button"));
			  if(!getText(driver, getObject("MobileCom_Hotel_SRP_HotelSearch_Name")).contains(Hotel_Name)){
				  Reporter.log("Hotel name selected is : "+getText(driver, getObject("MobileCom_Hotel_SRP_HotelSearch_Name"))+" and Hotel to be selected is  "+Hotel_Name);
				  Assert.assertTrue(false);
			  } 
			 }
		  safeClick(driver, getObject("MobileCom_Hotel_SRP_Hotel")); 
		}
	
	public void mobileCom_Hotel_SRP_Search_HotelName(RemoteWebDriver driver, String Hotel_Name) throws Exception {
		boolean hotelFound=false;
		if(!elementPresent(driver, getObject("MobileCom_Hotel_SRP_Hotel"),1)){/*
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("iframe size="+size);
		Reporter.log("iframe size="+size);
		if(size==1){
			driver.switchTo().frame(0);
		}
		else if(size==2){
			driver.switchTo().frame(1);
		}
		else if(size==3){
			driver.switchTo().frame(2);
		}
		else if(size==4){
			driver.switchTo().frame(3);
		}
		if(elementPresent(driver,By.id("closeBanner"),2)){
		//driver.switchTo().frame(1);
		driver.findElement(By.id("closeBanner")).click();
		}
		*/}
		  /*elementVisible(driver, getObject("MobileCom_Hotel_SRP_HoldOn_Search_Message"),600);
		  String srpText = getText(driver, getObject("MobileCom_Hotel_SRP_Text"));
		  Assert.assertEquals("Price", srpText);*/
		//safeClick(driver,By.xpath("//span[@class='browseLink']"));
		  //safeClick(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"));
		//  safeType(driver, By.id("hotelsByName"), "Royal Opera");
		//  mouseHover(driver, By.id("hotelsByName"));
		 // safeClick(driver, By.id("hotelsByNameBtn"));
		List<WebElement> we=driver.findElements(By.xpath("//div[@class='hotelInfo']/strong/span"));
		test:for(int i=0;i<we.size();i++){
			
			
			if(we.get(i).getText().equalsIgnoreCase("Ramada Encore Domlur")){
				hotelFound=true;
				break test;
			}
		}
		Assert.assertEquals(hotelFound,true,"hotel didnt found");
		//System.out.println(getText(driver,By.xpath("//span[@class=''truncate]")));
		safeClick(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"));
		  safeClick(driver, getObject("MobileCom_Hotel_SRP_Hotel")); 
		}
	
	public void mobileCom_Hotel_SRP_PAH(RemoteWebDriver driver, String Hotel_Name ) throws Exception, Exception{
		  elementNotVisible(driver, getObject("MobileCom_Hotel_SRP_HoldOn_Search_Message"), 600);
		  Thread.sleep(1000);
		  String srpText = getText(driver, getObject("MobileCom_Hotel_SRP_Text"));
		  Assert.assertEquals("Price", srpText);
		if(elementPresent_Time(driver, getObject("MobileCom_Hotel_SRP_HotelName_SearchBox"), 10)){
			Thread.sleep(5000);
			  safeType(driver, getObject("MobileCom_Hotel_SRP_HotelName_SearchBox"), Hotel_Name);
			  safeClick(driver, getObject("MobileCom_Hotel_SRP_HotelName_SearchButton"));
		  }
	}
	
	public void mobileCom_Hotel_Details(RemoteWebDriver driver) throws  Exception{
		  elementVisible(driver, getObject("MobileCom_Hotel_DetailsPage_Select_Room"), 20);
		  safeClick(driver, getObject("MobileCom_Hotel_DetailsPage_Select_Room"));
	}
	
	public String mobileCom_Hotel_Open_URL(RemoteWebDriver driver, String Domain) throws Exception {
		String URL = null;
		if(Domain.equalsIgnoreCase("com")){
			URL = baseUrl; 
		}
		else if(Domain.equalsIgnoreCase("ae")){
			URL = baseUrl_AE;
		}
		else if(Domain.equalsIgnoreCase("bh")){
			URL = baseUrl_BH;
		}
		else if(Domain.equalsIgnoreCase("om")){
			URL = baseUrl_OM;
		}
		else if(Domain.equalsIgnoreCase("kw")){
			URL = baseUrl_KW;
		}
		else if(Domain.equalsIgnoreCase("sa")){
			URL = baseUrl_SA;
		}
		else if(Domain.equalsIgnoreCase("me")){
			URL = baseUrl_ME;
		}
		else if(Domain.equalsIgnoreCase("qa")){
			URL = baseUrl_QA;
		}
		return URL;
	}
	
	public String mobileCom_Hotel_SRP_URL(RemoteWebDriver driver, String Domain, String City, String State ) throws Exception {
		baseUrl = mobileCom_Hotel_Open_URL(driver, Domain);		
		String Date = putDate1(20);
		String Date1 = putDate1(21);
		String SRP_URL = baseUrl+"/m/hotels/results?city="+City+"&state="+State+"&country=IN&ckIn="+Date+"&ckOut="+Date1+"&noOfRooms=1&ad=2&cd=1&ca1=5";
		driver.get(SRP_URL);
		logURL(driver);
		Thread.sleep(2000);
		return SRP_URL;	
	}
	
	public String mobileCom_Hotel_SRP_URL_Desktop(RemoteWebDriver driver, String Domain, String City, String State ) throws Exception {
		baseUrl = mobileCom_Hotel_Open_URL(driver, Domain);		
		String Date = putDate1(20);
		String Date1 = putDate1(21);
		String SRP_URL = baseUrl+"/hotels/results?city="+City+"&state="+State+"&country=IN&ckIn="+Date+"&ckOut="+Date1+"&noOfRooms=1&ad=2&cd=1&ca1=5";
		driver.get(SRP_URL);
		logURL(driver);
		Thread.sleep(2000);
		return SRP_URL;	
	}
	
	public String mobileCom_Hotel_SRP_URL_Intl(RemoteWebDriver driver, String Domain, String City, String State, String Country ) throws Exception {
		baseUrl = mobileCom_Hotel_Open_URL(driver, Domain);		
		String Date = putDate1(20);
		String Date1 = putDate1(21);
		String SRP_URL = baseUrl+"/m/hotels/results?city="+City+"&state="+State+"&country="+Country+"&ckIn="+Date+"&ckOut="+Date1+"&noOfRooms=1&ad=2&cd=1&ca1=5";
		driver.get(SRP_URL);
		logURL(driver);
		Thread.sleep(2000);
		return SRP_URL;	
	}
	
	public String mobileCom_Hotel_SRP_URL_Date(RemoteWebDriver driver, String Domain, String City, String State , int date, int date1) throws Exception {
		baseUrl = mobileCom_Hotel_Open_URL(driver, Domain);		
		String Date = putDate1(date);
		String Date1 = putDate1(date1);
		String SRP_URL = baseUrl+"/m/hotels/results?city="+City+"&state="+State+"&country=IN&ckIn="+Date+"&ckOut="+Date1+"&noOfRooms=1&ad=2&cd=1&ca1=5";
		driver.get(SRP_URL);
		logURL(driver);
		Thread.sleep(2000);
		return SRP_URL;
		
}
	
	public String mobileCom_Hotel_Details_URL(RemoteWebDriver driver, String Domain, String City,String State, String Country,  int CheckIn, int CheckOut, String HotelID ) throws Exception {
		baseUrl = mobileCom_Hotel_Open_URL(driver, Domain);		
		String Date = putDate1(CheckIn);
		String Date1 = putDate1(CheckOut);
		String Details_URL = baseUrl+"/m/hotels/results?city="+City+"&state="+State+"&country="+Country+"&ckIn="+Date+"&ckOut="+Date1+"&noOfRooms=1&ad=2&cd=1&ca1=5#checkAvailability?hotelId="+HotelID;
		driver.get(Details_URL);
		logURL(driver);
		Thread.sleep(2000);
		return Details_URL;
		
}
	
	public String mobileCom_Hotel_Details_URL_PartPay(RemoteWebDriver driver, String Domain, String City,String State, String Country,  int CheckIn, int CheckOut, String HotelID ) throws Exception {
		baseUrl = mobileCom_Hotel_Open_URL(driver, Domain);		
		String Date = putDate1(CheckIn);
		String Date1 = putDate1(CheckOut);
		String Details_URL = baseUrl+"/m/hotels/results?city="+City+"&country="+Country+"&state="+State+"&ckIn="+Date+"&ckOut="+Date1+"&noOfRooms=1&ad=2&cd=1&ca1=5&pahCCRequired=true#details?hotelId="+HotelID;
		driver.get(Details_URL);
		logURL(driver);
		Thread.sleep(2000);
		return Details_URL;
		
}
	
    public void mobileCom_Hotel_RoomType(RemoteWebDriver driver) throws  Exception{
    		elementVisible(driver, getObject("MobileCom_Hotel_DetailsPage_Text"), 2);
    	  if(elementVisible(driver, getObject("MobileCom_Hotel_RoomType_Pick_Room2"), 2)){
    		  safeClick(driver, getObject("MobileCom_Hotel_RoomType_Pick_Room2"));  
    	  }else{
		  safeClick(driver, getObject("MobileCom_Hotel_RoomType_Pick_Room1"));	
	 }
    }
	
	public void mobileCom_Hotel_ItineraryPage(RemoteWebDriver driver, String Booking_Type) throws Exception {
		if(Booking_Type.equalsIgnoreCase("PAH")){
			boolean pahRadioBtn = elementVisible(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"), 5);
			if (pahRadioBtn){
			   	safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"));
			    }
			else{
				Reporter.log("No PAH option available for selected hotel");
				Assert.assertTrue(false);
		   }
			/*if(elementVisible(driver, By.xpath("//div[@id='couponCodeBlock']/div/label"), 1)){
				Reporter.log("Coupon block is displayed after clicking P@H option");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_Text"), 1)){
				Reporter.log("PAH text block is not displayed after clicking P@H option");
				Assert.assertTrue(false);
			}*/
	/*	if( !getText(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_Text")).contains("We will reserve your room until you confirm your sta")){
			Reporter.log("We will reserve your room until you confirm your stay text is not displayed after clicking P@H option");
			Assert.assertTrue(false);
		}*/
			//dd/div/div/label
			if( !getText(driver, getObject("MobileCom_Hotel_ItineraryPage_PAHCoupon_Text")).contains("Please apply a Pay at Hotel coupon")){
				Reporter.log(" Please apply a Pay at Hotel coupon text is not displayed after clicking P@H option");
				Assert.assertTrue(false);
			}/*	
		if( !getText(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_Text")).contains("Pay@Hotel")){
			Reporter.log("Pay@Hotel text is not displayed after clicking P@H option");
			Assert.assertTrue(false);
		}
			*/
		}
		else if(Booking_Type.equalsIgnoreCase("Meta")) {
			if(elementPresent(driver,getObject("MobileCom_Hotel_ItineraryPage_Coupon_Box"),1)){
				Reporter.log("Coupon Text Box is displayed");
				Assert.assertTrue(false);
		}				
		}
		else if(Booking_Type.equalsIgnoreCase("COUPONPAHCC")) {
			if(elementPresent(driver,getObject("MobileCom_Hotel_ItineraryPage_Coupon_Box"),1)){
				safeType(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Box"), dataFile.value("HotelCom_PAHCoupon"));
				safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Check_Button"));
				String Coupon_Sucess_Msg = getText(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Success_Msg"));
				if(!Coupon_Sucess_Msg.contains("credited to your Cleartrip Wallet post booking")){
					Reporter.log("Coupon Message : "+Coupon_Sucess_Msg);
					Assert.assertEquals(true, false);
				}
				}
		}
		
		else if(Booking_Type.equalsIgnoreCase("COUPONPAH")) {
			if(elementPresent(driver,getObject("MobileCom_Hotel_ItineraryPage_Coupon_Box"),1)){
				safeType(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Box"), dataFile.value("HotelCom_PAHCoupon"));
				safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Check_Button"));
				String Coupon_Sucess_Msg = getText(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Success_Msg"));
				if(!Coupon_Sucess_Msg.contains("credited to your Cleartrip Wallet post booking")){
					Reporter.log("Coupon Message : "+Coupon_Sucess_Msg);
					Assert.assertEquals(true, false);
				}
				}
		}
		
		else if(elementPresent(driver,By.id("paynow"),2)){
			safeClick(driver,By.id("paynow"));
		}
		if(Booking_Type.equalsIgnoreCase("COUPON")){
				if(elementPresent(driver,getObject("MobileCom_Hotel_ItineraryPage_Coupon_Box"),1)){
				safeType(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Box"), dataFile.value("HotelB2B_Coupon"));
				safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Check_Button"));
				String Coupon_Sucess_Msg = getText(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Success_Msg"));
				if(!Coupon_Sucess_Msg.contains("Great! You just saved")){
					Assert.assertEquals(true, false);
				}
				}
		}
		else if(Booking_Type.equalsIgnoreCase("PAHCC")){
			boolean pahRadioBtn = elementVisible(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"), 5);
			if (pahRadioBtn){
			   	safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"));
			    }
			else{
				Reporter.log("No PAH option available for selected hotel");
				Assert.assertTrue(false);
		   }
		}
		
		if(Booking_Type.equalsIgnoreCase("PARTPAY")){
			elementPresent_log(driver, getObject("MobileCom_Hotel_ItineraryPage_Partpay_Button") , "Partpay radio button", 10);
			safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_Partpay_Button"));
			elementPresent_log(driver, getObject("MobileCom_Hotel_ItineraryPage_Partpay_Text") , "Partpay Text message", 10);
			//safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_Partpay_Button"));
			Reporter.log("Part pay button selected");
			if(!getText(driver, getObject("MobileCom_Hotel_ItineraryPage_Partpay_Text")).contains("Pay a token advance of")) {
				Reporter.log("Pay a token advance of : text is not displayed");
				Assert.assertTrue(false);
			}
		}
	/*	if(!Booking_Type.equalsIgnoreCase("PARTPAY")){
			Thread.sleep(2000);
			safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_PayNowFlow_Continue_Button"));	
		}*/
		safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_PayNowFlow_Continue_Button"));	
     }
	
	public void mobileCom_Hotel_ItineraryPage_PAH(RemoteWebDriver driver) throws Exception {
		boolean pahRadioBtn = elementVisible(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"), 5);
		if (pahRadioBtn){
		   	safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"));
		    }
		else{
			printInfo("No PAH option available for selected hotel");		
	   }
     }
	 
	public void MobileCom_SignInBookFlow(RemoteWebDriver driver) throws Exception{
		elementVisible(driver, getObject("MobileCom_Hotel_Step2SignIn_Text"), 10);
		String  Step2Text = getText(driver, getObject("MobileCom_Hotel_Step2SignIn_Text"));  
		 Assert.assertEquals("Your email address", Step2Text);
		  safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Email"), dataFile.value("Mobile_EmailID"));
		  safeClick(driver, getObject("MobileCom_Hotel_Step2SignIn_SignIn_CheckBox"));
		  safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Password"), dataFile.value("Mobile_Password"));
		  safeClick(driver, getObject("MobileCom_Hotel_Step2SignIn_SignIn_Button"));

	}
	
	public void mobileCom_Hotel_Login(RemoteWebDriver driver, String SignIN) throws Exception{
		elementVisible(driver, getObject("MobileCom_Hotel_Step2SignIn_Text"), 10);
		String  Step2Text = getText(driver, getObject("MobileCom_Hotel_Step2SignIn_Text"));  
		Assert.assertEquals("Your email address", Step2Text);
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("QA2")||Host.equalsIgnoreCase("hf")) {
		safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Email"), dataFile.value("HotelEmailID"));
		}
		else if(Host.equalsIgnoreCase("beta")||Host.equalsIgnoreCase("www")) {			
		safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Email"), dataFile.value("HotelGmailEmailID"));
		}
		
		if (SignIN.equalsIgnoreCase("SIGNIN")) {
		   if(elementVisible(driver, getObject("MobileCom_Hotel_Step2SignIn_SignIn_CheckBox"),1)){
				safeClick(driver, getObject("MobileCom_Hotel_Step2SignIn_SignIn_CheckBox"));
				Thread.sleep(2000);
				/*safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Password"), dataFile.value("HotelPassword"));
				Reporter.log("SignIN @ BookStep2");*/
				if(Host.equalsIgnoreCase("QA2")||Host.equalsIgnoreCase("hf")) {
					safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Password"), dataFile.value("HotelPassword"));
					}
					else if(Host.equalsIgnoreCase("beta")||Host.equalsIgnoreCase("www")) {
						safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Password"), dataFile.value("HotelGmailPassword"));
					}
				
		   }
			}
		  safeClick(driver, getObject("MobileCom_Hotel_Step2SignIn_SignIn_Button"));
	}
		
	public void mobileCom_Hotel_TravellerPage(RemoteWebDriver driver) throws Exception {
		  String  TravellerText = getText(driver, getObject("MobileCom_Hotel_TravellerPage_Text"));  
		  Assert.assertEquals("Name of guest", TravellerText);		
		  safeSelect(driver, getObject("MobileCom_Hotel_TravellerPage_Title"), dataFile.value("Title"));
		  safeType(driver, getObject("MobileCom_Hotel_TravellerPage_FirstName"), dataFile.value("First_Name"));
		  safeType(driver, getObject("MobileCom_Hotel_TravellerPage_LastName"), dataFile.value("Last_Name"));
		  if(elementVisible(driver, By.id("use_vat"), 10)){
			  if(isElementSelected(driver, By.id("use_vat"))){
				  	Reporter.log("Vat fields shown, ncheck and continue");
					safeUncheck(driver, By.name("use_vat"));
				}
			}
			else{
				Reporter.log("Vat fields not shown");
			}
		  try{
		  safeType(driver,By.id("payAtHotelMobileNumber"), dataFile.value("Mobile_Number"));
		  }
		  catch(Exception e){
			  safeType(driver,By.id("mobileNumber"),dataFile.value("Mobile_Number"));
		  }
		  if(elementVisible(driver, getObject("MobileCom_Hotel_TravellerPage_SpecialRequest"), 1)){
		  safeType(driver, getObject("MobileCom_Hotel_TravellerPage_SpecialRequest"), dataFile.value("Hotel_Special_Request"));  
		  }
		  safeClick(driver, getObject("MobileCom_Hotel_TravellerPage_Continue_Button"));		  
		  Reporter.log("Traveller Page continue button is clicked");
	}
	
	public void mobileCom_Hotel_TravellerPage_GST(RemoteWebDriver driver, String GSTType) throws Exception {
		  String  TravellerText = getText(driver, getObject("MobileCom_Hotel_TravellerPage_Text"));  
		  Assert.assertEquals("Name of guest", TravellerText);		
		  safeSelect(driver, getObject("MobileCom_Hotel_TravellerPage_Title"), dataFile.value("Title"));
		  safeType(driver, getObject("MobileCom_Hotel_TravellerPage_FirstName"), dataFile.value("First_Name"));
		  safeType(driver, getObject("MobileCom_Hotel_TravellerPage_LastName"), dataFile.value("Last_Name"));
		  try{
		  safeType(driver,By.id("payAtHotelMobileNumber"), dataFile.value("Mobile_Number"));
		  }
		  catch(Exception e){
			  safeType(driver,By.id("mobileNumber"),dataFile.value("Mobile_Number"));
		  }
		  if(GSTType.equalsIgnoreCase("GSTEDIT")){
			  if(!textPresent(driver, "Use GSTIN for this booking (Optional)", 2)){
				  Reporter.log("Use GSTIN for this booking (Optional) Message is not displayed");
				  Assert.assertTrue(false);
			  }
			  safeClick(driver, By.xpath("//dl/dd[@class='gstCheckLabel']/small/a"));
			  if(!elementVisible(driver, By.xpath("//div/dl[@class='horizontal mt10']/dt"), 5)){
				  Reporter.log("GSTIN Text is not displayed");
				  Assert.assertTrue(false);
			  }
			  safeType(driver, By.id("gst_number"), "29 - FASDG1234H - 1Q1");
			  safeType(driver, By.xpath("//div/div/div/div/dl[2]/dd/input"), "Cleartrip");
		  }
		  else if(GSTType.equalsIgnoreCase("GSTUnselect")){
			  if(!textPresent(driver, "Use GSTIN for this booking (Optional)", 2)){
				  Reporter.log("Use GSTIN for this booking (Optional) Message is not displayed");
				  Assert.assertTrue(false);
			  }
			  Reporter.log("Unchecked GST check Box");
			  UnChecking_Checkbox(driver, By.id("use_gst"));
		  }
		  if(elementVisible(driver, getObject("MobileCom_Hotel_TravellerPage_SpecialRequest"), 1)){
		  safeType(driver, getObject("MobileCom_Hotel_TravellerPage_SpecialRequest"), dataFile.value("Hotel_Special_Request"));  
		  }
		  safeClick(driver, getObject("MobileCom_Hotel_TravellerPage_Continue_Button"));		  
	}
	
	public String mobileCom_Hotel_PaymentPage(RemoteWebDriver driver, String BookingType, String Trip_Logger_Msg) throws Exception {
		if(!elementVisible(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"), 20)) {
			if(elementVisible(driver, getObject("MobileCom_Hotel_TravellerPage_Continue_Button"), 1)) {
			smartClick(driver, getObject("MobileCom_Hotel_TravellerPage_Continue_Button"));
			}
		}
		String TripID =null;
		if(BookingType.contains("PAHCC")){
			if(!textPresent(driver, "This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now", 2)){
				Reporter.log("PAH Creadit Card text message is not displayed");
				Assert.assertTrue(false);
			}			
		}	else if(BookingType.contains("PARTPAY")){
			textPresent_Log(driver, "Amount payable", 5);
			elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PartPay_Block"), "PartPay block in payment Page", 2);
		}
		else if(BookingType.equals("PAHOTP")){
			String OTP = hotelComPAHSendOTP(driver);
			if(!textPresent(driver, "We've sent you a verification code to your mobile. Please enter the code to verify.", 10)){
				Reporter.log("We've sent you a verification code to your mobile. Please enter the code to verify. : message is not displayed");
				Assert.assertTrue(false);
			}
			elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_Text"), "", 5);
			elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), "", 1);
			elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"), "", 1);
			Thread.sleep(5000);
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), OTP);
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"));		
			elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SuccessMsg"), "", 1);
			if(!textPresent(driver, "Great, verified successfully!", 10)){
				Reporter.log("Great, verified successfully! : message is not displayed");
				Assert.assertTrue(false);
			}		
		}
		else if(BookingType.equalsIgnoreCase("NBRetryProd")){
				safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_NB_Tab"));
				safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
				safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
				elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
				elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"),5);
				safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
				elementVisible(driver, By.linkText("Cancel"), 10);
				textPresent(driver, "Cleartrip Travel Services Pvt Ltd", 20);
				safeClick(driver, By.linkText("Cancel"));
				textPresent(driver, "Please double check the information before trying again or try using a different payment method", 20);
				String PaymentRetryText = getText(driver, getObject("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
//			    Assert.assertEquals("Oops, your payment didn’t work",PaymentRetryText );
			    //safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_SC_Tab"));
			    //safeType(driver, getObject("MobileCom_Hotel_PaymentPage_SC_CVV"), dataFile.value("MasterCard_CVV"));
			    if(!textPresent(driver, "Oops, your payment didn’t work", 1)){
			    	Reporter.log("Oops, your payment didn’t work : message is not displayed");
			    	//Assert.assertTrue(false);
			    }
			    
			    if(!elementVisible(driver, By.cssSelector("p.subText.important"), 2)){
			    	Reporter.log("Error message is not displayed");
			    	Assert.assertTrue(false);
			    }
			    if(!getText(driver, By.xpath("//h1")).contains("Oops, your payment did")){
			    	Reporter.log("Error message is not displayed");
			    	Assert.assertTrue(false);
			    }
			}
		
		else if(BookingType.equalsIgnoreCase("CC")||BookingType.equals("")||BookingType.equals("PARTPAY")){
			smartClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
			elementVisible(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text"), 10);
			if(!getText(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text")).contains("Enter your credit card details")){
				Reporter.log("Enter your credit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		}
		else if(BookingType.equalsIgnoreCase("CCCYear")){
			smartClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
			elementVisible(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text"), 10);
			if(!getText(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text")).contains("Enter your credit card details")){
				Reporter.log("Enter your credit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), "2025");
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));

			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));		
			//Oops, your payment didn’t work  //div/h1
			//Your payment has not been authorized by your bank. Please check the card number, CVV and expiry date //div[1]/div[1]/div[1]/p
			mobileCom_Hotel_PaymentPage_Authentication(driver, "");			
			textPresent(driver, "able to process your payment", 30);
			safeClick(driver, By.id("retryLink"));
			textPresent(driver, "Oops, your payment ", 30);
			elementVisible(driver, By.xpath("//div/h1"), 10);
			
			if(!getText(driver, By.xpath("//div/h1")).contains("Oops, your payment did")){
				Reporter.log("Oops, your payment didn’t work : message not displayed");
				Assert.assertTrue(false);
			}
			elementVisible(driver, By.xpath("//div[1]/div[1]/div[1]/p"), 10);
			//if(!getText(driver, By.xpath("//div[1]/div[1]/div[1]/p")).contains("Your payment has not been authorized by your bank")){
			if(!textPresent(driver, "Your payment has not been authorized by your bank", 5)){
				Reporter.log("Your payment has not been authorized by your bank. Please check the card number, CVV and expiry date : message not displayed");
				Assert.assertTrue(false);
			}	
			smartClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
			elementVisible(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text"), 10);
			if(!getText(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text")).contains("Enter your credit card details")){
				Reporter.log("Enter your credit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		
}
		
		else if(BookingType.equalsIgnoreCase("DC")){
			smartClick(driver, getObject("MobileCom_Hotel_PaymentPage_DC_Tab"));
			elementVisible(driver, getObject("MobileCom_Hotel_PaymentPage_DC_Text"), 10);
			if(!getText(driver, getObject("MobileCom_Hotel_PaymentPage_DC_Text")).contains("Enter your debit card details")){
				Reporter.log("Enter your debit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_DC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_DC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_DC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_DC_CardName"), dataFile.value("Master_CC_Name"));
			//driver.findElement(By.id("BillName")).sendKeys("test");
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_DC_CVV"), dataFile.value("Master_CC_CVV"));
		}
		else if(BookingType.equalsIgnoreCase("PAHCC")){
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
			textPresent(driver, "Enter your credit card details", 60);
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		}
		else if(BookingType.equalsIgnoreCase("PAHOTP2")){
			  elementPresent_log(driver, By.id("paymentOpen"), "Credit Card Verification", 5);
			 if(!getText(driver, By.id("paymentOpen")).contains("Credit Card Verification")){
					 Reporter.log("Credit Card Verification : message is not displayed");
					 Assert.assertTrue(false);
			 }
		    textPresent(driver, "Enter your credit card details", 60);
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		}		
		else if(BookingType.equalsIgnoreCase("PAHCC2")){
			  elementPresent_log(driver, By.id("paymentOpen"), "Credit Card Verification", 5);
			 if(!getText(driver, By.id("paymentOpen")).contains("Credit Card Verification")){
					 Reporter.log("Credit Card Verification : message is not displayed");
					 Assert.assertTrue(false);
			 }
		    textPresent(driver, "Enter your credit card details", 30);
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		}		
		
		
		
				
		
		
		//===========================================================================================================================================================//
		
		
		
		
		
		
		if(MakePaymentOnlyInQA2){
		if(BookingType.equalsIgnoreCase("NBRetry")){
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_NB_Tab"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
			elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
			elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"),5);
			safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
			textPresent(driver, "Oops, your payment didn’t work", 20);
			String PaymentRetryText = getText(driver, getObject("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
		    Assert.assertEquals("Oops, your payment didn’t work",PaymentRetryText );
		    //safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_SC_Tab"));
		    //safeType(driver, getObject("MobileCom_Hotel_PaymentPage_SC_CVV"), dataFile.value("MasterCard_CVV"));
		    if(!textPresent(driver, "Oops, your payment didn’t work", 5)){
		    	Reporter.log("Oops, your payment didn’t work : message is not displayed");
		    	Assert.assertTrue(false);
		    }
		    if(!elementVisible(driver, By.cssSelector("p.subText.important"), 2)){
		    	Reporter.log("Error message is not displayed");
		    	Assert.assertTrue(false);
		    }
		}
		if(!BookingType.contains("NBRetry")){
			Thread.sleep(2000);
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));		
		}
		}
		return TripID;
	}
	
	public String mobileCom_Hotel_Second_PaymentPage(RemoteWebDriver driver, String BookingType, String Trip_Logger_Msg, String TripID) throws Exception {
		
		if(MakePaymentOnlyInQA2){
			if(BookingType.contains("PAHOTP2")){
			  driver.get(baseUrl+"/account/trips/"+TripID+"/pahcc/review");
			  mobileCom_Hotel_PaymentPage(driver, "PAHOTP2" , "PAHOTP2");
			  hotelCom_PaymentPage_Authentication(driver, "");
			  elementPresent_log(driver, By.cssSelector("i.i16.pahBrand"), "P@H icon ", 60);
			  elementPresent_log(driver, By.cssSelector("span.status.positive"), "Confirmed Status", 10);
			
			  if(!textPresent(driver, "Your stay is confirmed. Please pay at the hotel.", 1)){
				  Reporter.log("Your stay is confirmed. Please pay at the hotel. : message is not displayed");
				  Assert.assertTrue(false);
			  }
			  if(!getText(driver, By.cssSelector("h3.lightHeader")).contains("Pay@hotel")){
				  Reporter.log("Pay@hotel text is not displayed in rate breakup");
				  Assert.assertTrue(false);
			  }
			  if(!getText(driver, By.cssSelector("span.status.positive")).contains("CONFIRMED")){
				  Reporter.log("Confirmed Status is not displayed , status is displayed as "+getText(driver, By.cssSelector("span.status.positive")));
				  Assert.assertTrue(false);
			  }
			  Reporter.log("PAHCC / OTP - second booking / confiramtion TripID: "+TripID);
			}
	/*		else if(BookingType.contains("PAHCC2")){
				  driver.get(baseUrl+"/account/trips/"+TripID+"/pahcc/review");
				  mobileCom_Hotel_PaymentPage(driver, "PAHCC2" , "PAHCC2");	 
				  elementPresent_log(driver, By.cssSelector("i.i16.pahBrand"), "P@H icon ", 60);
				  elementPresent_log(driver, By.cssSelector("span.status.positive"), "Confirmed Status", 10);
				
				  if(!textPresent(driver, "Your stay is confirmed. Please pay at the hotel.", 1)){
					  Reporter.log("Your stay is confirmed. Please pay at the hotel. : message is not displayed");
					  Assert.assertTrue(false);
				  }
				  if(!getText(driver, By.cssSelector("h3.lightHeader")).contains("Pay@hotel")){
					  Reporter.log("Pay@hotel text is not displayed in rate breakup");
					  Assert.assertTrue(false);
				  }
				  if(!getText(driver, By.cssSelector("span.status.positive")).contains("CONFIRMED")){
					  Reporter.log("Confirmed Status is not displayed , status is displayed as "+getText(driver, By.cssSelector("span.status.positive")));
					  System.out.println("Confirmed Status is not displayed , status is displayed as "+getText(driver, By.cssSelector("span.status.positive")));
					  Assert.assertTrue(false);
				  }
				}*/
		}
		
		return TripID;
		
	}
	
	public String mobileCom_Hotel_MakePaymentPage1(RemoteWebDriver driver, String Trip_Logger_Msg) throws Exception {
		String TripID =null;
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
		textPresent(driver, "Enter your credit card details", 60);
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
		safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
		safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		if(MakePaymentOnlyInQA2){
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		textPresent(driver, "Your booking is done", 20);
		elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 30);
		TripID = getText(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"));
		//System.out.println(TripID+" for "+Trip_Logger_Msg);
		logger.info(TripID+" for "+Trip_Logger_Msg);
		Reporter.log(TripID+" for "+Trip_Logger_Msg,true);
		logURL(driver);
		}
		return TripID;
	}
	
	public String mobileCom_Hotel_ConfirmationPage(RemoteWebDriver driver, String BookingType , String Trip_Logger_Msg, String Confirmation_Msg) throws Exception{
		String TripID =null;
		if(MakePaymentOnlyInQA2){
			if(!BookingType.equals("PAHOTP")) {
			mobileCom_Hotel_PaymentPage_Authentication(driver, "");
			}if(!elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 30)){
			Reporter.log("Confirmation page not displayed");
			Assert.assertTrue(false);
		}
		TripID = getText(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"));
		logger.info(Trip_Logger_Msg+ " : "+TripID);
		Reporter.log(Trip_Logger_Msg+ " : "+TripID);
		logURL(driver);
		if(BookingType.contains("PAHOTP")){
			if(!textPresent(driver, Confirmation_Msg, 20)){
				Reporter.log("Confirmation msg is not correct");
				Assert.assertTrue(false);
			}
		//	elementPresent_log(driver, By.xpath("//section/div/div/div/div[2]"), "Confirmation Booking Block", 5);
		//	elementPresent_log(driver, By.xpath("//section/div/div/div/div[2]/p[2]/a"), "Confirmtion Booking Block", 5);
		}
		else if(BookingType.contains("PAHCC")){
			textPresent(driver, Confirmation_Msg, 20);	
			
		}
		else if(BookingType.contains("PARTPAY")){
			if(!textPresent(driver, Confirmation_Msg, 20)){
				Reporter.log("Confirmation msg is not correct");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "Please pay the remaining", 20)){
				Reporter.log("Please pay the remaining : msg is not displayed");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "Call us when you're ready to pay", 20)){
				Reporter.log("Call us when you're ready to pay: msg is not displayed");
				Assert.assertTrue(false);
			}
			elementPresent_log(driver, By.xpath("//section/div/div/p"), "Confirmation Booking Block", 5);
			if(!getText(driver, By.xpath("//section/div/div/p")).contains("Your trip id is")){
				Reporter.log("Your trip id is : msg is not displayed");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "95 95 333 333", 5)){
				Reporter.log("95 95 333 333 : phone no is not displayed");
				Assert.assertTrue(false);
			}		
			
		}
		}
		return TripID;
	}
	
	public void mobileCom_Hotel_MakePaymentPage_NBRetry_SC(RemoteWebDriver driver, String Trip_Logger_Msg ) throws Exception{
		Thread.sleep(2000);
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_NB_Tab"));
		safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
		if(MakePaymentTrue &&!ProductionUrl){
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
			/*String SbiText =getText(driver, getObject("MobileCom_Hotel_PaymentPage_NB_SBIText"));
		    Assert.assertEquals("Password *",SbiText );
		    safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_NB_SBI_ClickAbort"));
		    */
			elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
			elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"),5);
			safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
			textPresent(driver, "Oops, your payment didn’t work", 20);
			String PaymentRetryText = getText(driver, getObject("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
		    Assert.assertEquals("Oops, your payment didn’t work",PaymentRetryText );
		    safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_SC_Tab"));
		    safeType(driver, getObject("MobileCom_Hotel_PaymentPage_SC_CVV"), dataFile.value("MasterCard_CVV"));
		    safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		    textPresent(driver, "Your booking is done", 50);
			elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 50);
			String TripID = getText(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"));
			logger.info(TripID+" for "+Trip_Logger_Msg);
			Reporter.log(TripID+" for "+Trip_Logger_Msg);
		}else{
			printInfo("Either the URL is pointing to production or make payment is false");
		}
	}
	
	public void mobileCom_Hotel_MakePaymentPage_NBRetry_CC(RemoteWebDriver driver, String Trip_Logger_Msg ,boolean international) throws Exception{
		Thread.sleep(5000);
		String PaymentRetryText = null;
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_NB_Tab"));
		//safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_NB_DropDown"), "State Bank of India");
		safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
		if(MakePaymentTrue &&!ProductionUrl){
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
			/*String SbiText =getText(driver, getObject("MobileCom_Hotel_PaymentPage_NB_SBIText"));
		    Assert.assertEquals("Password *",SbiText );
		    safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_NB_SBI_ClickAbort"));
		    */
			elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
			elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"),5);
			safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
			textPresent(driver, "Oops, your payment didn’t work", 40);
			/////System.out.println(getText(driver, getObject("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text")));
			if(international){
				PaymentRetryText = getText(driver,By.xpath("//div[@class='system_messages']/div/div/h1"));
				////System.out.println(PaymentRetryText);	
			}
			else{
				 PaymentRetryText = getText(driver, getObject("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
			}
			 
		    Assert.assertEquals("Oops, your payment didn’t work",PaymentRetryText );
		    safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		    safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		    textPresent(driver, "Your booking is done", 50);
			elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 50);
			String TripID = getText(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"));
			//System.out.println(TripID+" for "+Trip_Logger_Msg);
			logger.info(TripID+" for "+Trip_Logger_Msg);
			Reporter.log(TripID+" for "+Trip_Logger_Msg);
		}else{
			printInfo("Either the URL is pointing to production or make payment is false");
		}
	}

	public void mobileCom_Hotel_MakePaymentPage_CancellaionFlow(RemoteWebDriver driver, String Trip_Logger_Msg) throws Exception {
		Thread.sleep(2000);
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
		textPresent(driver, "Enter your credit card details", 60);
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
		safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
		safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		if(MakePaymentTrue &&!ProductionUrl){
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		textPresent(driver, "Your booking is done", 50);
		elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 50);
		String TripID = getText(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"));
		logger.info(TripID+" for "+Trip_Logger_Msg);
		Reporter.log(TripID+" for "+Trip_Logger_Msg);
		mobileCom_Hotel_Cancellationflow(driver,TripID);
		}
	}
	
	public void mobileCom_Hotel_Cancellationflow(RemoteWebDriver driver, String TripID) throws Exception{
		 /*Thread.sleep(2000);
		  driver.get(common.value("murl"));
		 if(elementVisible(driver,By.id("menuBtn"),1)){
			safeClick(driver,By.id("menuBtn"));
		 }
		 List<WebElement> we=driver.findElements(By.xpath("//b"));
		Test: for(int i=0;i<we.size();i++){
			// we.get(i).getText();
			// //System.out.println( we.get(i).getText());
			 if(we.get(i).getText().equalsIgnoreCase("trips")){
				 we.get(i).click();
			 break Test;
			 }
		 }
		// safeClick(driver, getObject("MobileCom_HomePage_Trips_Tab"));
		 Thread.sleep(5000);
		 boolean TripPage500 = isElementPresent(driver, getObject("MobileCom_TripsPage_500Error"));
		 if (TripPage500){
			 driver.navigate().refresh();
			 mobileCom_Hotel_CancellationSteps(driver,TripID);
		 }else{
			 mobileCom_Hotel_CancellationSteps(driver,TripID);
		 }*/
		mobileCom_Hotel_CancellationSteps(driver,TripID);
	}
	
	public void mobileCom_Hotel_CancellationSteps(RemoteWebDriver driver, String TripID) throws Exception{
	/*	 String TripsText =getText(driver, getObject("MobileCom_HomePage_Trips_PageText"));
	     Assert.assertEquals("Trips", TripsText);
	     printInfo(TripID);*/
	     
	     driver.get("https://qa2.cleartrip.com/account/trips/"+TripID+"/cancel");
	     Thread.sleep(2000);
	     elementVisible(driver, getObject("MobileCom_HomePage_Trips_CancellationPage1_Text"), 20);
	     String CancellationText= getText(driver, getObject("MobileCom_HomePage_Trips_CancellationPage1_Text"));
	     Assert.assertEquals("Cancellations", CancellationText);
	     safeClick(driver, getObject("MobileCom_homePage_Trips_cancellationPage1_RefundToPaymentOption_RadioButton"));
	     safeClick(driver, By.className("primary"));
	     if (waitElement(driver, getObject("MobileCom_HomePage_Trips_CancellationHotel_TicketsCancelled_Message"), 500)&&isElementPresent(driver, getObject("MobileCom_HomePage_Trips_CancellationHotel_TicketsCancelled_Message"))){
	     //String CancellationConfirmationText = getText(driver,getObject("MobileCom_HomePage_Trips_CancellationPage3_TicketsCancelled_Message"));
	     //Assert.assertEquals("Your hotel booking was cancelled successfully", CancellationConfirmationText );
	     /*Thread.sleep(1000);
         safeClick(driver, getObject("MobileCom_HomePage_Trips_CancellationPage3_BackToTrips_Button"));
         Thread.sleep(2000);
         String TripsText2 =getText(driver, getObject("MobileCom_HomePage_Trips_PageText"));
	     Assert.assertEquals("Trips", TripsText2);*/
	     }else{
	    	 Reporter.log("cancellation failed");
	     }
	}	

	public void mobileCom_Hotel_Confirmationpage_PriceValidation(RemoteWebDriver driver, int Price_Int) throws Exception {
		mobileCom_Hotel_PaymentPage_Authentication(driver, "");
		elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 30);
		String Price_Confirmation =null;  
		for(int i=0; i<=6; i++){
			 String XPATH = "//div/div/section/div/div/aside/nav/ul/li[3]/dl/dt["+i+"]";
			 String XPATH_Price = "//div/div/section/div/div/aside/nav/ul/li[3]/dl/dd["+i+"]";
				
			if(elementPresent_Time(driver, By.xpath(XPATH), 1)){
					String Price_Type = getText(driver, By.xpath(XPATH));
					//System.out.println(Price_Type);
					if(Price_Type.contains("Total")){
						Price_Confirmation = getText(driver, By.xpath(XPATH_Price)).replace("Rs. ", "");
						if(Price_Confirmation.contains(",")){
							Price_Confirmation = Price_Confirmation.replace(",", "");
						}
						break;
					}
				}
			}
	  int Price_Confirmation_Int = Integer.parseInt(Price_Confirmation);
	  Reporter.log("Price in Trip Confirmation Page : "+Price_Confirmation);
      if(!(Price_Confirmation_Int == Price_Int)){
		  Reporter.log("Price in SRP ' "+Price_Int+" ' does not match in Confirmation  page ' "+Price_Confirmation_Int+" '");
      }
	}

	public void mobileCom_Hotel_MakePaymentPage_PAHOTP(RemoteWebDriver driver, String Trip_Logger_Msg ) throws Exception{
		String OTP = hotelComPAHSendOTP(driver);
		if(!textPresent(driver, "We've sent you a verification code to your mobile. Please enter the code to verify.", 10)){
			Reporter.log("We've sent you a verification code to your mobile. Please enter the code to verify. : message is not displayed");
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_Text"), "", 5);
		elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), "", 5);
		elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"), "", 5);
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), OTP);
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"));		
		elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SuccessMsg"), "", 5);
		if(!textPresent(driver, "Great, verified successfully!", 10)){
			Reporter.log("Great, verified successfully! : message is not displayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		
	}	
	
	
	public void mobileCom_Hotel_ItineraryPage_PriceValidation(RemoteWebDriver driver, int Price_Int) throws Exception {
		String Price_Itn=getText(driver,getObject("MobileCom_Hotel_ItineraryPage_Price")).replace(",", "");
	  int Price_Itn_int = Integer.parseInt(Price_Itn);
	  printInfo("Price in Itinerary Page : "+Price_Itn_int);
	  Reporter.log("Price in Itinerary Page : "+Price_Itn_int);
	  if(!(Price_Int== Price_Itn_int)){
		  printInfo("Price in SRP ' "+Price_Int+" ' does not match in itenary page ' "+Price_Itn_int+" '");
		  Reporter.log("Price in SRP ' "+Price_Int+" ' does not match in itenary page ' "+Price_Itn_int+" '");
	  }
}

	public int mobileCom_Hotel_Detailspage_PriceValidation(RemoteWebDriver driver) throws Exception {
		//String Price=getText(driver, getObject("MobileCom_Hotel_RoomType_Price")).replace("Rs. ", "").replace(".0", "").replace(",", "");
	
	  //int Price_Int = Integer.parseInt(Price);	  
	  int Price_Int=hotelCom_Price_To_Int(driver, getObject("MobileCom_Hotel_RoomType_Price"));
	  printInfo("Price in Hotel Details Page : "+Price_Int);
	  Reporter.log("Price in Hotel Details Page : "+Price_Int);
	  return Price_Int;
}
	
	
	public HashMap<String, String> getGstDataFromTripXML(RemoteWebDriver driver, String tripID, boolean gstDefault) throws Exception
	{
		Reporter.log("Fetching GST Details from Trip XML for trip ID: " + tripID, true);
		HashMap<String, String> xmlDetails = new HashMap<String, String>();
		HashMap<String, String> gstDetails = new HashMap<String, String>();
		HashMap<String, String> amountDetails = new HashMap<String, String>();
		String tmp1;
		float z = 0;
		String tmp;
		String id;
		String tripId;
		String gstNumber;
		String gstHolderName;
		String gstHolderAddress;
		String gstHolderStateName;
		String gstHolderStateCode;
		String createdAt;
		String updatedAt;
		String tmp2;
		/*String tripID = getText(driver, getObject("AirCom_Confirmation_TripID"));
		Reporter.log("Getting GST details from trip XML for TripId: " + tripID, true);*/
		
		if(!gstDefault)
		{
			//GST details entered in UI
			xmlDetails.put("gst-detail", "");
			xmlDetails.put("pricing-elements","");
			xmlDetails = getTripXmlPrams(tripID, xmlDetails);
			//System.out.println(xmlDetails);
			//System.out.println(xmlDetails.get("pricing-elements"));
			tmp1=xmlDetails.get("pricing-elements");
			//System.out.println("========================="+tmp1);
			tmp = xmlDetails.get("gst-detail");
			//System.out.println("++++++++"+tmp1.split("<pricing-element>").length);
			int j=0;
			for(int i=1;i<tmp1.split("<pricing-element>").length;i++){
				
				//System.out.println("amount="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
				//System.out.println("code="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0]);
			if(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].contains("true")){
				amountDetails.put(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].replace("nil=\"true\">","no code")+j,tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
				j++;
			}
			else{
				amountDetails.put(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].replace(">",""),tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
			}
			
			
			//System.out.println(amountDetails);
				//	xmlDetails.put(, value)
				//.split("<amount>")[1].split("</amount>")[0]
			}
			for(String key:amountDetails.keySet()){
				//System.out.println("z value="+z);
				//System.out.println(amountDetails.get(key));
				z=z+(Float.parseFloat(amountDetails.get(key)));
			}
			System.out.println(amountDetails);
			Reporter.log("cgst="+amountDetails.get("CGST"),true);
			Reporter.log("sgst="+amountDetails.get("SGST"),true);
			
			System.out.println("-----"+z);
			
			//System.out.println(tmp);
			id = tmp.split("<id>")[1].split("</trip-id>")[0];
			tripId = tmp.split("<trip-id>")[1].split("</trip-id>")[0];
			gstNumber = tmp.split("<gst-number>")[1].split("</gst-number>")[0];
			gstHolderName = tmp.split("<gst-holder-name>")[1].split("</gst-holder-name>")[0];
			//gstHolderAddress = tmp.split("<gst-holder-address>")[1].split("</gst-holder-address>")[0];
			gstHolderStateName = tmp.split("<gst-holder-statename>")[1].split("</gst-holder-statename>")[0];
			gstHolderStateCode = tmp.split("<gst-holder-statecode>")[1].split("</gst-holder-statecode>")[0];
			createdAt = tmp.split("<created-at>")[1].split("</created-at>")[0];
			updatedAt = tmp.split("<updated-at>")[1].split("</updated-at>")[0];
		
		}
		else
		{
			//GST details entered in UI
			xmlDetails.put("pricing-elements","");
			xmlDetails.put("gst-detail", "");
			xmlDetails = getTripXmlPrams(tripID, xmlDetails);
			tmp1=xmlDetails.get("pricing-elements");
			tmp = xmlDetails.get("gst-detail");
			int j=0;
			System.out.println("length==="+tmp1.split("<pricing-element>").length);
			try{
			for(int i=0;i<tmp1.split("<pricing-element>").length;i++){
								//System.out.println("amount="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
				//System.out.println("code="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0]);
			if(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].contains("true")){
				amountDetails.put(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].replace("nil=\"true\">","no code")+j,tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
				j++;
			}
			else{
				amountDetails.put(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].replace(">",""),tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
			}
			}
			}
			catch(Exception e){

				for(int i=1;i<tmp1.split("<pricing-element>").length;i++){
									//System.out.println("amount="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
					//System.out.println("code="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0]);
				if(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].contains("true")){
					amountDetails.put(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].replace("nil=\"true\">","no code")+j,tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
					j++;
				}
				else{
					amountDetails.put(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].replace(">",""),tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
				}
				}
					
			}
			
			//System.out.println(amountDetails);
				
			
			for(String key:amountDetails.keySet()){
				z=z+(Float.parseFloat(amountDetails.get(key)));
			}
			System.out.println(amountDetails);
			Reporter.log("cgst="+amountDetails.get("CGST"),true);
			Reporter.log("sgst="+amountDetails.get("SGST"),true);
			
			Assert.assertEquals(Float.parseFloat(amountDetails.get("CGST"))>0,true);
			Assert.assertEquals(Float.parseFloat(amountDetails.get("SGST"))>0,true);
			id = tmp.split("<id>")[1].split("</trip-id>")[0];
			tripId = tmp.split("</trip-id>")[1].split("</trip-id>")[0];
			try{
			gstNumber = tmp.split("<gst-number nil=")[1].split("></gst-number>")[0].replaceAll("\"", "");
			gstHolderName = tmp.split("<gst-holder-name nil=")[1].split("></gst-holder-name>")[0].replaceAll("\"", "");
			//gstHolderAddress = tmp.split("<gst-holder-address nil=")[1].split("></gst-holder-address>")[0].replaceAll("\"", "");
			}
			catch(Exception e){
				gstNumber = tmp.split("<gst-number>")[1].split("</gst-number>")[0].replaceAll("\"", "");
				gstHolderName = tmp.split("<gst-holder-name>")[1].split("</gst-holder-name>")[0].replaceAll("\"", "");
				//gstHolderAddress = tmp.split("<gst-holder-address>")[1].split("</gst-holder-address>")[0].replaceAll("\"", "");
			}
			gstHolderStateName = tmp.split("<gst-holder-statename>")[1].split("</gst-holder-statename>")[0];
			gstHolderStateCode = tmp.split("<gst-holder-statecode>")[1].split("</gst-holder-statecode>")[0];
			createdAt = tmp.split("<created-at>")[1].split("</created-at>")[0];
			updatedAt = tmp.split("<updated-at>")[1].split("</updated-at>")[0];
		}
		
		gstDetails.put("id", id);
		gstDetails.put("tripId", tripId);
		gstDetails.put("gstNumber", gstNumber);
		gstDetails.put("gstHolderName", gstHolderName);
		//gstDetails.put("gstHolderAddress", gstHolderAddress);
		gstDetails.put("gstHolderStateName", gstHolderStateName);
		gstDetails.put("gstHolderStateCode", gstHolderStateCode);
		gstDetails.put("createdAt", createdAt);
		gstDetails.put("updatedAt", updatedAt);

		return gstDetails;
	}
	public HashMap<String, String> getTripXmlPrams(String tripID, HashMap<String, String> xmlDetails) throws Exception
	{
		DefaultHttpClient client = new DefaultHttpClient();
	HttpGet get = new HttpGet(new URI("http://10.10.21.107:9080/trips/"+tripID));
		//HttpGet get = new HttpGet(new URI("http://10.10.21.107:9080/trips/Q1707110060"));
		HttpResponse response = client.execute(get);
		//client.close();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer sb = new StringBuffer();
		String str="";
		while((str=br.readLine())!=null)
		{
			sb.append(str);
		}
		//System.out.println("++++++++++++++++++++++"+sb.toString());
		
		Set<String> keys = xmlDetails.keySet();
		for(String key : keys)
		{
			String sb1=sb.toString();
			sb1 = sb1.split("<" + key + ">")[1].split("</" + key + ">")[0];
			xmlDetails.put(key, sb1);
			Reporter.log(sb1, true);
			
		}
		
		return xmlDetails;
	}
	
	public void mobileCom_Hotel_PaymentPage_Authentication(RemoteWebDriver driver, String Values) throws Exception {
	   	/*
	   	for(int i=0; i<=25; i++) {
	   	textPresent(driver, "ACS Emulator", 1);
	if(elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Header"), 1)) {
	break;
	}
	if(elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"), 1)) {
	break;
	}
	}
	Thread.sleep(1000);
	elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"), 10, "Payment Authentication : ");
	smartClick(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"));
	*/
	    }
}