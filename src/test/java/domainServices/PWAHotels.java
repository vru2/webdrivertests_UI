// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.domainServices;

import static org.testng.Assert.assertTrue;

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
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;


public class PWAHotels extends IndiaHotels {
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
	public String NetBankingQA = "Bank of India"; 
	public String NetBankingProd = "Citibank"; 

	public String getDate(RemoteWebDriver driver,String date) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		c.add(Calendar.DATE, Integer.parseInt(date));
		String convertedDate = dateFormat.format(c.getTime());
		return convertedDate;	
	}

	public String URL(RemoteWebDriver driver, String Domain) throws Exception {
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

	public String SRP_URL(RemoteWebDriver driver, String Domain, String City, String State ) throws Exception {
		baseUrl = URL(driver, Domain);		
		String Date = putDate1(20);
		String Date1 = putDate1(21);
		String SRP_URL = baseUrl+"/m/hotels/results?adults=2&adults1=2&ca1=&children1=0&childs=0&chk_in="+Date+"&chk_out="+Date1+"&city="+City+"&country=IN&dest_code=&num_rooms=1&sort_order=asc&state="+State;
		driver.get(SRP_URL);
		logURL(driver);
		Thread.sleep(2000);
		return SRP_URL;	
	}

	public String URL_Intl(RemoteWebDriver driver, String Domain, String City, String State, String Country ) throws Exception {
		baseUrl = URL(driver, Domain);		
		String Date = putDate1(20);
		String Date1 = putDate1(21);
		String SRP_URL = baseUrl+"/m/hotels/results?city="+City+"&state="+State+"&country="+Country+"&ckIn="+Date+"&ckOut="+Date1+"&noOfRooms=1&ad=2&cd=1&ca1=5";
		driver.get(SRP_URL);
		logURL(driver);
		Thread.sleep(2000);
		return SRP_URL;	
	}

	public String URL_Date(RemoteWebDriver driver, String Domain, String City, String State , int date, int date1) throws Exception {
		baseUrl = URL(driver, Domain);		
		String Date = putDate1(date);
		String Date1 = putDate1(date1);
		String SRP_URL = baseUrl+"/m/hotels/results?city="+City+"&state="+State+"&country=IN&ckIn="+Date+"&ckOut="+Date1+"&noOfRooms=1&ad=2&cd=1&ca1=5";
		driver.get(SRP_URL);
		logURL(driver);
		Thread.sleep(2000);
		return SRP_URL;		
	}

	
	public void homePage_SignIn(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		Thread.sleep(2000);
	if(elementPresent(driver,By.id("menuBtn"),1)){
		safeClick(driver,By.id("menuBtn"));
	}
		safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_HomePage_Trip_Link"));
		safeClick(driver,getObjectHotelsPWA("MobileCom_Hotel_HomePage_signin"));
		elementVisible(driver,getObjectHotelsPWA("MobileCom_Hotel_HomePage_UserName"), 10);
		safeType(driver,getObjectHotelsPWA("MobileCom_Hotel_HomePage_UserName"),dataFile.value("HotelEmailIDPWA"));
		Thread.sleep(2000);
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys(dataFile.value("HotelPasswordPWA"));
		safeType(driver,getObjectHotelsPWA("MobileCom_Hotel_HomePage_Pwd"),dataFile.value("HotelPasswordPWA"));
//		if(ProductionUrl) {
//			safeType(driver,getObjectHotelsPWA("MobileCom_Hotel_HomePage_Pwd"),"");
//		}
		Thread.sleep(2000);
		safeClick(driver,getObjectHotelsPWA("MobileCom_Hotel_HomePage_SignIN_Button"));
	}
	
	public void signIn1(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		Thread.sleep(2000);
	if(elementPresent(driver,By.id("menuBtn"),1)){
		safeClick(driver,By.id("menuBtn"));
	}
		safeClick(driver,getObjectHotelsPWA("MobileCom_Hotel_HomePage_signin"));
		Thread.sleep(2000);
		safeType(driver,getObjectHotelsPWA("MobileCom_Hotel_HomePage_UserName"),dataFile.value("Mobilehotel_EmailID"));
		Thread.sleep(2000);
		safeType(driver,getObjectHotelsPWA("MobileCom_Hotel_HomePage_Pwd"),dataFile.value("Mobilehotel_Password"));
		Thread.sleep(2000);
		safeClick(driver,getObjectHotelsPWA("MobileCom_Hotel_HomePage_SignIN_Button"));
		Thread.sleep(5000);
		
	}
	
	public String getURL()
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
	
	public String getURL_ME(String domain)
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
	
	public void searchPage(RemoteWebDriver driver, String City,  String Adults, String Childrens, String Rooms, String CheckIN, String CheckOut, String HotelName, String SearchType) throws Exception {
		if(elementVisible(driver, By.linkText("English"), 10)){
			safeClick(driver, By.linkText("English"));
		}
		if(elementVisible(driver, By.id("english_site_pref"), 10)){
			smartClick(driver, By.id("english_site_pref"));
		}
		if(elementVisible(driver, By.id("english_select"), 10)){
			smartClick(driver, By.id("english_select"));
		}
		elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_Hotel_Tab"), 10);
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_Hotel_Tab"));
		elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_CityName_Select"), 10);
		if(!textPresent(driver, "Find and book hotels around the world", 2)) {
			Reporter.log("Find and book hotels around the world text is not displayed ");
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_CityName_Select"));
		elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_CitySelect_Text"), 5);
		String citySelectText = getText(driver, getObjectHotelsPWA("PWA_HomePage_CitySelect_Text"));
		if(!citySelectText.contains("Pick your destination")) {
			Reporter.log("Pick your destination text not found");
			Assert.assertTrue(false);
		}
		driver.findElement(By.xpath("//input[starts-with(@placeholder,'Enter a city, area, landmark or hotel')]")).sendKeys(City);
		//safeType(driver, getObjectHotelsPWA("PWA_HomePage_CityName_TextBox"), City);
		elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_CityName_DropDown"), 5);
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_CityName_DropDown"));
		waitForElementVisibility(driver, getObjectHotelsPWA("PWA_HomePage_CheckIN_Date"), 10);
		String checkIN=getDate(driver, CheckIN);
		String checkOut=getDate(driver, CheckOut);
		String checkInXpath="//*[contains(@datetime,'"+checkIN+"')]";
		Thread.sleep(5000);
		elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_CheckIN_Date"), 5);
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_CheckIN_Date"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		waitForElementVisibility(driver, By.xpath(checkInXpath), 50);     //By.xpath("//*[contains(@datetime,'"+checkIN+"')]")
	//	js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@datetime,'"+checkIN+"')]")));
	//	js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[contains(@datetime,'"+checkIN+"')]")));
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(checkInXpath)));
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath(checkInXpath)));
		Thread.sleep(5000);
		String checkOutXpath="//*[contains(@datetime,'"+checkOut+"')]";
		waitForElementVisibility(driver, By.xpath(checkOutXpath), 50);
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath(checkOutXpath)));
		travellerSelection(driver, Adults, Childrens, Rooms);		
		if(SearchType.equals("HotelSearch")) {
			String SelectedHotelName = getText(driver, getObjectHotelsPWA("PWA_HomePage_CityName_Select"));
			if(!SelectedHotelName.contains(City)) {
				Reporter.log("Entered Hotel is : "+City+" but Selected Hotel is : "+SelectedHotelName);
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_CheckAvailability_Button"), 5)) {
				Reporter.log("Check hotel Availability button is not displayed");
				Assert.assertTrue(false);
			}
			safeClick(driver, getObjectHotelsPWA("PWA_HomePage_CheckAvailability_Button"));
		}
		else {
			elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_ShowHotel_Button"), 5);
			safeClick(driver, getObjectHotelsPWA("PWA_HomePage_ShowHotel_Button"));
		}
	}
	
	
	public void searchPage_MultiDays1(RemoteWebDriver driver, String City,  String Adults, String Childrens, String Rooms, String HotelName, String SearchType) throws Exception {
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_Hotel_Tab"));
		elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_CityName_Select"), 10);
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_CityName_Select"));
		elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_CitySelect_Text"), 5);
		String citySelectText = getText(driver, getObjectHotelsPWA("PWA_HomePage_CitySelect_Text"));
		if(!citySelectText.contains("Pick your destination")) {
			Reporter.log("Pick your destination text not found");
			Assert.assertTrue(false);
		}		
		safeType(driver, getObjectHotelsPWA("PWA_HomePage_CityName_TextBox"), City);
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_CityName_DropDown"));
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_CheckIN_Date"));
		Thread.sleep(5000);
		String checkIN=getDate(driver,"50");
		String checkOut=getDate(driver,"53");
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		waitForElementVisibility(driver, By.xpath("//*[contains(@datetime,'"+checkIN+"')]"), 20);
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@datetime,'"+checkIN+"')]")));
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[contains(@datetime,'"+checkIN+"')]")));
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[contains(@datetime,'"+checkOut+"')]")));
		Thread.sleep(2000);
		travellerSelection(driver, Adults, Childrens, Rooms);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_ShowHotel_Button"));
	}
	
	
	
	public void travellerSelection(RemoteWebDriver driver, String Adults, String Childrens, String Rooms) throws Exception {
		elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_Travellers_Option"), 5);
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_Travellers_Option"));
		int Adult = Integer.parseInt(Adults);
		int Child = Integer.parseInt(Childrens);
		int Room = Integer.parseInt(Rooms);
		elementVisible(driver, getObjectHotelsPWA("PWA_HomePage_Travellers_Adults_Minus"), 5);
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_Travellers_Adults_Minus"));
		//Room1
		if(Adult>1) {
			for (int i = 1; i <Adult; i++) {
				safeClick(driver, getObjectHotelsPWA("PWA_HomePage_Travellers_Adults_Plus"));
			}
		}
		
		if(Child!=0) {
			for (int i = 0; i <Child; i++) {
				safeClick(driver, getObjectHotelsPWA("PWA_HomePage_Travellers_Child_Plus"));
			}
			int ll =1;
			for(int j=0; j<Child; j++) {
				safeSelectByValue(driver, By.xpath("//div[1]/ul[2]/*["+ll+"]/*[2]"), getRandomNosMin(10, 1));				
				ll++;
			}		
		}
		if(Room>1) {
			for (int i = 1; i < Room; i++) {
				safeClick(driver, getObjectHotelsPWA("PWA_HomePage_Travellers_Add_Room"));
			
			for(int j=1; j<Adult; j++) {
				int k= i+1;
				safeClick(driver, By.xpath("//div["+k+"]/ul[1]/../*[3]/*[1]/../*[1]/*[2]/*[3]"));	
			}
			for(int j=0; j<Child; j++) {
				int k= i+1;
				safeClick(driver, By.xpath("//div["+k+"]/ul[1]/../*[3]/*[1]/../*[2]/*[2]/*[3]"));				
			}
			int l =1;
			for(int j=0; j<Child; j++) {
				int k= i+1;
					safeSelectByValue(driver, By.xpath("//div["+k+"]/ul[2]/*["+l+"]/*[2]"), getRandomNosMin(10, 1));				
				l++;
			}		
			}
		}
		safeClick(driver, getObjectHotelsPWA("PWA_HomePage_Travellers_Selection_Done"));
	}
	
	public void resultsPage(RemoteWebDriver driver, String HotelName) throws Exception {
		elementVisible(driver, getObjectHotelsPWA("PWA_SRP_SelectHotel"),10);
	/*	elementVisible(driver, getObjectHotelsPWA("PWA_SRP_PriceFilter"), 10);
		safeClick(driver, getObjectHotelsPWA("PWA_SRP_PriceFilter"));	*/    
		
		elementVisible(driver, getObjectHotelsPWA("PWA_SRP_ByLocation"), 10);
		safeClick(driver, getObjectHotelsPWA("PWA_SRP_ByLocation"));
		elementVisible(driver, getObjectHotelsPWA("PWA_SRP_SortBy"), 10);
		elementVisible(driver, getObjectHotelsPWA("PWA_SRP_PriceLowToHigh"), 10);
		safeClick(driver, getObjectHotelsPWA("PWA_SRP_PriceLowToHigh"));
		
		elementVisible(driver, getObjectHotelsPWA("PWA_SRP_SelectHotel"), 10);
		elementVisible(driver, getObjectHotelsPWA("PWA_SRP_Price"), 10);
		if(!HotelName.isEmpty()) {
			safeClick(driver, getObjectHotelsPWA("PWA_SRP_SearchHotel"));
			elementVisible(driver, getObjectHotelsPWA("PWA_SRP_SearchHotel_Page_SearchBox_Text"), 5);
			if(!getText(driver, getObjectHotelsPWA("PWA_SRP_SearchHotel_Page_SearchBox_Text")).contains("Where do you wish to stay")) {
				Reporter.log("Where do you wish to stay text is not displayed");
			}
			elementVisible(driver, getObjectHotelsPWA("PWA_SRP_SearchHotel_Page_SearchBox"), 5);
			safeType(driver, getObjectHotelsPWA("PWA_SRP_SearchHotel_Page_SearchBox"), HotelName);
			Thread.sleep(5000);
			elementVisible(driver, getObjectHotelsPWA("PWA_SRP_SearchHotel_Page_Ajax"), 5);
			elementVisible(driver, getObjectHotelsPWA("PWA_SRP_SearchHotel_Page_HotelName"), 5);
			safeClick(driver, getObjectHotelsPWA("PWA_SRP_SearchHotel_Page_HotelName"));
			elementVisible(driver, getObjectHotelsPWA("PWA_SRP_SelectHotel"),10);
			elementVisible(driver, getObjectHotelsPWA("PWA_SRP_HotelName"),10);
			Thread.sleep(5000);
			String HotelSRPName = getText(driver, getObjectHotelsPWA("PWA_SRP_HotelName"));
			System.out.println("Hotel Selected is : "+HotelName+" Hotel Name displayed is : "+HotelSRPName);
			
			if(!HotelSRPName.contains(HotelName)) {
				Reporter.log("Hotel Selected is : "+HotelName+" Hotel Name displayed is : "+HotelSRPName);
				Assert.assertTrue(false);
			}
		} 
		logURL(driver);
		safeClick(driver, getObjectHotelsPWA("PWA_SRP_SelectHotel"));	
	}
	
	public void detailsPage(RemoteWebDriver driver, String detailsType) throws  Exception{
		if(!elementVisible(driver, getObjectHotelsPWA("PWA_DetailsPage_Book_Button"),2)) {
			refreshPage(driver);
		}
		elementVisible(driver, getObjectHotelsPWA("PWA_DetailsPage_Book_Button"),30);
		Thread.sleep(3000);
		safeClick(driver, getObjectHotelsPWA("PWA_DetailsPage_Book_Button"));
		Thread.sleep(3000);
	}
	
    public void roomTypePage(RemoteWebDriver driver, String roomType) throws  Exception{
    	elementVisible(driver, getObjectHotelsPWA("PWA_RoomRate_Book_Button"), 20);
    	if(roomType.equalsIgnoreCase("Modify")) {
    		if(!elementVisible(driver, getObjectHotelsPWA("PWA_RoomRate_CheckIn"), 10)) {
    			Reporter.log("Modify Date is not displayed");
    			Assert.assertTrue(false);
    		}
    		safeClick(driver, getObjectHotelsPWA("PWA_RoomRate_CheckIn"));
    		Thread.sleep(2000);
    		String checkIN=getDate(driver, "30");
    		String checkOut=getDate(driver, "31");
    		JavascriptExecutor js = (JavascriptExecutor) driver;
    		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@datetime,'"+checkIN+"')]")));
    		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[contains(@datetime,'"+checkIN+"')]")));
    		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[contains(@datetime,'"+checkOut+"')]")));
    		Thread.sleep(1000);
    	}
    	Thread.sleep(5000);
    	elementVisible(driver, getObjectHotelsPWA("PWA_RoomRate_Book_Button"), 5);
		safeClick(driver, getObjectHotelsPWA("PWA_RoomRate_Book_Button"));
	
		
    }
    
	public void itineraryStep(RemoteWebDriver driver, String Booking_Type) throws Exception {
		if(elementPresent(driver,By.id("paynow"),2)){
			safeClick(driver,By.id("paynow"));
		}
		if(Booking_Type.equalsIgnoreCase("COUPON")){
				if(elementPresent(driver,getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_Coupon_Box"),1)){
				safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_Coupon_Box"), dataFile.value("HotelB2B_Coupon"));
				safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_Coupon_Check_Button"));
				String Coupon_Sucess_Msg = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_Coupon_Success_Msg"));
				if(!Coupon_Sucess_Msg.contains("Great! You just saved")){
					Assert.assertEquals(true, false);
				}
				}
		}
		else if(Booking_Type.equalsIgnoreCase("PAHCC")){
			boolean pahRadioBtn = elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"), 5);
			if (pahRadioBtn){
			   	safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"));
			    }
			else{
				Reporter.log("No PAH option available for selected hotel");
				Assert.assertTrue(false);
		   }
		}
		if(Booking_Type.equalsIgnoreCase("PARTPAY")){
			elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_Partpay_Button") , "Partpay radio button", 10);
			safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_Partpay_Button"));
			elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_Partpay_Text") , "Partpay Text message", 10);
			//safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_Partpay_Button"));
			Reporter.log("Part pay button selected");
			if(!getText(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_Partpay_Text")).contains("Pay a token advance of")) {
				Reporter.log("Pay a token advance of : text is not displayed");
				Assert.assertTrue(false);
			}
		}
		safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_PayNowFlow_Continue_Button"));	
     }
	
	public void itineraryPage_PAH(RemoteWebDriver driver) throws Exception {
		boolean pahRadioBtn = elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"), 5);
		if (pahRadioBtn){
		   	safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"));
		    }
		else{
			printInfo("No PAH option available for selected hotel");		
	   }
     }
	 
	public void signInBookFlow(RemoteWebDriver driver) throws Exception{
		elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_Text"), 10);
		String  Step2Text = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_Text"));  
		 Assert.assertEquals("Your email address", Step2Text);
		  safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_Email"), dataFile.value("Mobile_EmailID"));
		  safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_SignIn_CheckBox"));
		  safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_Password"), dataFile.value("Mobile_Password"));
		  safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_SignIn_Button"));
	}
	
	public void loginStep(RemoteWebDriver driver, String SignIN) throws Exception{
		elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_Text"), 10);
		/*String  Step2Text = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_Text"));  
		Assert.assertEquals("Your email address", Step2Text);*/
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("QA2")||Host.equalsIgnoreCase("hf")) {
		safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_Email"), dataFile.value("HotelEmailIDPWA"));
		}
		else if(Host.equalsIgnoreCase("beta")||Host.equalsIgnoreCase("www")) {			
		safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_Email"), dataFile.value("HotelGmailEmailID"));
		}
		if (SignIN.equalsIgnoreCase("SIGNIN")) {
		   if(elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_SignIn_CheckBox"),1)){
				safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_SignIn_CheckBox"));
				Thread.sleep(2000);
				if(Host.equalsIgnoreCase("QA2")||Host.equalsIgnoreCase("hf")) {
					safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_Password"), dataFile.value("HotelPasswordPWA"));
					}
					else if(Host.equalsIgnoreCase("beta")||Host.equalsIgnoreCase("www")) {
						safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_Password"), dataFile.value("HotelGmailPassword"));
					}
				
		   }
			}
		  safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_Step2SignIn_SignIn_Button"));
	}
		
	public void travellerStep(RemoteWebDriver driver, String travellerType) throws Exception {
		  String  TravellerText = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_Text"));  
		  Assert.assertEquals("Name of guest", TravellerText);		
		  safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_Title"), dataFile.value("Title"));
		  safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_FirstName"), dataFile.value("First_Name"));
		  safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_LastName"), dataFile.value("Last_Name"));
		  try{
		  safeType(driver,By.id("payAtHotelMobileNumber"), dataFile.value("Mobile_Number"));
		  }
		  catch(Exception e){
			  safeType(driver,By.id("mobileNumber"),dataFile.value("Mobile_Number"));
		  }
		  safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_SpecialRequest"), dataFile.value("Hotel_Special_Request"));  
		  safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_Continue_Button"));		  
		  Reporter.log("Traveller Page continue button is clicked");
	}
	
	public void travellerPage_GST(RemoteWebDriver driver, String GSTType) throws Exception {
		  String  TravellerText = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_Text"));  
		  Assert.assertEquals("Name of guest", TravellerText);		
		  safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_Title"), dataFile.value("Title"));
		  safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_FirstName"), dataFile.value("First_Name"));
		  safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_LastName"), dataFile.value("Last_Name"));
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
		  
		  safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_SpecialRequest"), dataFile.value("Hotel_Special_Request"));  
		  safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_Continue_Button"));		  
	}
	
	public String paymentStep(RemoteWebDriver driver, String BookingType, String Trip_Logger_Msg) throws Exception {
		if(!elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"), 20)) {
			if(elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_Continue_Button"), 1)) {
			smartClick(driver, getObjectHotelsPWA("MobileCom_Hotel_TravellerPage_Continue_Button"));
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
			elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PartPay_Block"), "PartPay block in payment Page", 2);
			smartClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Tab"));
			elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Text"), 10);
			if(!getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Text")).contains("Enter your credit card details")){
				Reporter.log("Enter your credit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));		
		}
		else if(BookingType.equals("PAHOTP")){
			String OTP = hotelComPAHSendOTP(driver);
			Reporter.log("OTP : "+OTP);
			if(!textPresent(driver, "We'll send a verification code to your mobile number", 10)){
				Reporter.log("We'll send a verification code to your mobile number. text not displayed");
				Assert.assertTrue(false);
			}
			elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_Text"), "", 5);
			elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), "", 1);
			elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"), "", 1);
			Thread.sleep(2000);
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), OTP);
			safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"));		
			elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_SuccessMsg"), "", 1);
			if(!textPresent(driver, "Great, verified successfully!", 10)){
				Reporter.log("Great, verified successfully! : message is not displayed");
				Assert.assertTrue(false);
			}		
		}
		else if(BookingType.equalsIgnoreCase("NBRetryProd")){
				safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_Tab"));
				safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
				safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));
				elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
				elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"),5);
				safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
				elementVisible(driver, By.linkText("Cancel"), 10);
				textPresent(driver, "Cleartrip Travel Services Pvt Ltd", 20);
				safeClick(driver, By.linkText("Cancel"));
				textPresent(driver, "Please double check the information before trying again or try using a different payment method", 20);
				String PaymentRetryText = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
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
		
		else if(BookingType.equalsIgnoreCase("CC")||BookingType.equals("")){
			smartClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Tab"));
			elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Text"), 10);
			if(!getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Text")).contains("Enter your credit card details")){
				Reporter.log("Enter your credit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		}
		else if(BookingType.equalsIgnoreCase("CCCYear")){
			smartClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Tab"));
			elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Text"), 10);
			if(!getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Text")).contains("Enter your credit card details")){
				Reporter.log("Enter your credit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), "2025");
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));

			safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));		
			PaymentPage_Authentication(driver, "");			
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
			smartClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Tab"));
			elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Text"), 10);
			if(!getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Text")).contains("Enter your credit card details")){
				Reporter.log("Enter your credit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		
}
		
		else if(BookingType.equalsIgnoreCase("DC")){
			smartClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_DC_Tab"));
			elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_DC_Text"), 10);
			if(!getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_DC_Text")).contains("Enter your debit card details")){
				Reporter.log("Enter your debit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_DC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_DC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_DC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_DC_CardName"), dataFile.value("Master_CC_Name"));
			//driver.findElement(By.id("BillName")).sendKeys("test");
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_DC_CVV"), dataFile.value("Master_CC_CVV"));
		}
		else if(BookingType.equalsIgnoreCase("PAHCC")){
			safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Tab"));
			textPresent(driver, "Enter your credit card details", 60);
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		}
		else if(BookingType.equalsIgnoreCase("PAHOTP2")){
			  elementPresent_log(driver, By.id("paymentOpen"), "Credit Card Verification", 5);
			 if(!getText(driver, By.id("paymentOpen")).contains("Credit Card Verification")){
					 Reporter.log("Credit Card Verification : message is not displayed");
					 Assert.assertTrue(false);
			 }
		    textPresent(driver, "Enter your credit card details", 60);
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		}		
		else if(BookingType.equalsIgnoreCase("PAHCC2")){
			  elementPresent_log(driver, By.id("paymentOpen"), "Credit Card Verification", 5);
			 if(!getText(driver, By.id("paymentOpen")).contains("Credit Card Verification")){
					 Reporter.log("Credit Card Verification : message is not displayed");
					 Assert.assertTrue(false);
			 }
		    textPresent(driver, "Enter your credit card details", 30);
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		}		
		
		
		
				
		
		
		//===========================================================================================================================================================//
		
		
		
		
		
		
		if(MakePaymentOnlyInQA2){
		if(BookingType.equalsIgnoreCase("NBRetry")){
			safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_Tab"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
			safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));
			elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
			elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"),5);
			safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
			textPresent(driver, "Oops, your payment didn’t work", 20);
			String PaymentRetryText = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
		    Assert.assertEquals("Oops, your payment didn’t work",PaymentRetryText );
		    //safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_SC_Tab"));
		    //safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_SC_CVV"), dataFile.value("MasterCard_CVV"));
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
			safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));		
		}
		}
		return TripID;
	}
	
	public String PaymentPage_Second(RemoteWebDriver driver, String BookingType, String Trip_Logger_Msg, String TripID) throws Exception {
		
		if(MakePaymentOnlyInQA2){
			if(BookingType.contains("PAHOTP2")){
			  driver.get(baseUrl+"/account/trips/"+TripID+"/pahcc/review");
			  paymentStep(driver, "PAHOTP2" , "PAHOTP2");
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
	
	public String makePaymentPage1(RemoteWebDriver driver, String Trip_Logger_Msg) throws Exception {
		String TripID =null;
		safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Tab"));
		textPresent(driver, "Enter your credit card details", 60);
		safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
		safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
		safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
		safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
		safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		if(MakePaymentOnlyInQA2){
		safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));
		textPresent(driver, "Your booking is done", 20);
		elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"), 30);
		TripID = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"));
		//System.out.println(TripID+" for "+Trip_Logger_Msg);
		logger.info(TripID+" for "+Trip_Logger_Msg);
		Reporter.log(TripID+" for "+Trip_Logger_Msg,true);
		logURL(driver);
		}
		return TripID;
	}
	
	public String confirmationPage(RemoteWebDriver driver, String BookingType , String Trip_Logger_Msg, String Confirmation_Msg) throws Exception{
		String TripID =null;
		if(MakePaymentOnlyInQA2){
			if(!BookingType.equals("PAHOTP")) {
			PaymentPage_Authentication(driver, "");
			}if(!elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"), 30)){
			Reporter.log("Confirmation page not displayed");
			Assert.assertTrue(false);
		}
		TripID = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"));
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
	
	public void makePaymentPage_NBRetry_SC(RemoteWebDriver driver, String Trip_Logger_Msg ) throws Exception{
		Thread.sleep(2000);
		safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_Tab"));
		safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
		if(MakePaymentTrue &&!ProductionUrl){
			safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));
			/*String SbiText =getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_SBIText"));
		    Assert.assertEquals("Password *",SbiText );
		    safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_SBI_ClickAbort"));
		    */
			elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
			elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"),5);
			safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
			textPresent(driver, "Oops, your payment didn’t work", 20);
			String PaymentRetryText = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
		    Assert.assertEquals("Oops, your payment didn’t work",PaymentRetryText );
		    safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_SC_Tab"));
		    safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_SC_CVV"), dataFile.value("MasterCard_CVV"));
		    safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));
		    textPresent(driver, "Your booking is done", 50);
			elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"), 50);
			String TripID = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"));
			logger.info(TripID+" for "+Trip_Logger_Msg);
			Reporter.log(TripID+" for "+Trip_Logger_Msg);
		}else{
			printInfo("Either the URL is pointing to production or make payment is false");
		}
	}
	
	public void makePaymentPage_NBRetry_CC(RemoteWebDriver driver, String Trip_Logger_Msg ,boolean international) throws Exception{
		Thread.sleep(5000);
		String PaymentRetryText = null;
		safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_Tab"));
		//safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_DropDown"), "State Bank of India");
		safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
		if(MakePaymentTrue &&!ProductionUrl){
			safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));
			/*String SbiText =getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_SBIText"));
		    Assert.assertEquals("Password *",SbiText );
		    safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_SBI_ClickAbort"));
		    */
			elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
			elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"),5);
			safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
			textPresent(driver, "Oops, your payment didn’t work", 40);
			/////System.out.println(getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text")));
			if(international){
				PaymentRetryText = getText(driver,By.xpath("//div[@class='system_messages']/div/div/h1"));
				////System.out.println(PaymentRetryText);	
			}
			else{
				 PaymentRetryText = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
			}
			 
		    Assert.assertEquals("Oops, your payment didn’t work",PaymentRetryText );
		    safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Tab"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
			safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		    safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));
		    textPresent(driver, "Your booking is done", 50);
			elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"), 50);
			String TripID = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"));
			//System.out.println(TripID+" for "+Trip_Logger_Msg);
			logger.info(TripID+" for "+Trip_Logger_Msg);
			Reporter.log(TripID+" for "+Trip_Logger_Msg);
		}else{
			printInfo("Either the URL is pointing to production or make payment is false");
		}
	}

	public void makePaymentPage_CancellationFlow(RemoteWebDriver driver, String Trip_Logger_Msg) throws Exception {
		Thread.sleep(2000);
		safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Tab"));
		textPresent(driver, "Enter your credit card details", 60);
		safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
		safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"), dataFile.value("Mobile_MasterCard_Exp_Month"));
		safeSelect(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), dataFile.value("Master_CC_Expiry_Year"));
		safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
		safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		if(MakePaymentTrue &&!ProductionUrl){
		safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));
		textPresent(driver, "Your booking is done", 50);
		elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"), 50);
		String TripID = getText(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"));
		logger.info(TripID+" for "+Trip_Logger_Msg);
		Reporter.log(TripID+" for "+Trip_Logger_Msg);
		cancellationAcct(driver,TripID);
		}
	}
	
	/*public void cancellationflow(RemoteWebDriver driver, String TripID) throws Exception{		 
		cancellationAcct(driver,TripID);
	}*/
	
	public void cancellationAcct(RemoteWebDriver driver, String TripID) throws Exception{
		if(MakePaymentOnlyInQA2) {
	     driver.get("https://qa2.cleartrip.com/account/trips/"+TripID+"/cancel");
	     Thread.sleep(2000);
	     elementVisible(driver, getObjectHotelsPWA("MobileCom_HomePage_Trips_CancellationPage1_Text"), 20);
	     String CancellationText= getText(driver, getObjectHotelsPWA("MobileCom_HomePage_Trips_CancellationPage1_Text"));
	     Assert.assertEquals("Cancellations", CancellationText);
	     safeClick(driver, getObjectHotelsPWA("MobileCom_homePage_Trips_cancellationPage1_RefundToPaymentOption_RadioButton"));
	     safeClick(driver, By.className("primary"));
	     if (waitElement(driver, getObjectHotelsPWA("MobileCom_HomePage_Trips_CancellationHotel_TicketsCancelled_Message"), 500)&&isElementPresent(driver, getObjectHotelsPWA("MobileCom_HomePage_Trips_CancellationHotel_TicketsCancelled_Message"))){
	 	     }else{
	    	 Reporter.log("cancellation failed");
	     }
	}	
	}
	
	public void confirmationpage_PriceValidation(RemoteWebDriver driver, int Price_Int) throws Exception {
		PaymentPage_Authentication(driver, "");
		elementVisible(driver, getObjectHotelsPWA("MobileCom_Hotel_ConfirmationPage_TripID"), 30);
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

	public void makePaymentPage_PAHOTP(RemoteWebDriver driver, String Trip_Logger_Msg ) throws Exception{
		String OTP = hotelComPAHSendOTP(driver);
		if(!textPresent(driver, "We've sent you a verification code to your mobile. Please enter the code to verify.", 10)){
			Reporter.log("We've sent you a verification code to your mobile. Please enter the code to verify. : message is not displayed");
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_Text"), "", 5);
		elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), "", 5);
		elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"), "", 5);
		safeType(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), OTP);
		safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"));		
		elementPresent_log(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_PAHOTP_SuccessMsg"), "", 5);
		if(!textPresent(driver, "Great, verified successfully!", 10)){
			Reporter.log("Great, verified successfully! : message is not displayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectHotelsPWA("MobileCom_Hotel_PaymentPage_Payment_Button"));
		
	}	

	public void itineraryPage_PriceValidation(RemoteWebDriver driver, int Price_Int) throws Exception {
		String Price_Itn=getText(driver,getObjectHotelsPWA("MobileCom_Hotel_ItineraryPage_Price")).replace(",", "");
	  int Price_Itn_int = Integer.parseInt(Price_Itn);
	  printInfo("Price in Itinerary Page : "+Price_Itn_int);
	  Reporter.log("Price in Itinerary Page : "+Price_Itn_int);
	  if(!(Price_Int== Price_Itn_int)){
		  printInfo("Price in SRP ' "+Price_Int+" ' does not match in itenary page ' "+Price_Itn_int+" '");
		  Reporter.log("Price in SRP ' "+Price_Int+" ' does not match in itenary page ' "+Price_Itn_int+" '");
	  }
}

	public int detailspage_PriceValidation(RemoteWebDriver driver) throws Exception {
	  int Price_Int=hotelCom_Price_To_Int(driver, getObjectHotelsPWA("MobileCom_Hotel_RoomType_Price"));
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
		/*String tripID = getText(driver, getObjectHotelsPWA("AirCom_Confirmation_TripID"));
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
			//System.out.println("length==="+tmp1.split("<pricing-element>").length);
			try{
			for(int i=0;i<tmp1.split("<pricing-element>").length;i++){
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
					if(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].contains("true")){
					amountDetails.put(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].replace("nil=\"true\">","no code")+j,tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
					j++;
				}
				else{
					amountDetails.put(tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0].replace(">",""),tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
				}
				}
					
			}
						
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
			}
			catch(Exception e){
				gstNumber = tmp.split("<gst-number>")[1].split("</gst-number>")[0].replaceAll("\"", "");
				gstHolderName = tmp.split("<gst-holder-name>")[1].split("</gst-holder-name>")[0].replaceAll("\"", "");
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
	
	public void addCookie(RemoteWebDriver driver){
		   String domain = common.value("host");
		   if(domain.equalsIgnoreCase("qa2")){
			Cookie cookieName = new Cookie("ct-auth", cookievalue_QA2);
			driver.manage().addCookie(cookieName);
		   }
		   else if(domain.equalsIgnoreCase("www")){
			   Cookie cookieName = new Cookie("ct-auth", cookievalue_WWW);
			   driver.manage().addCookie(cookieName);
		   }
		   else if(domain.equalsIgnoreCase("beta")){
			   Cookie cookieName = new Cookie("ct-auth", cookievalue_BETA);
			   driver.manage().addCookie(cookieName);
		   } 
		   refreshPage(driver);
	}
	
	
	public void itineraryStepPWA(RemoteWebDriver driver, String Booking_Type) throws Exception {
		textPresent(driver, "Check-in", 10);
		elementVisible(driver, getObjectHotelsPWA("PWA_Step1_Header"), 20);
		elementPresent_log(driver, getObjectHotelsPWA("PWA_Step1_Header"), "Itinerary page is not displayed", 1);
		String CheckINText = getText(driver, getObjectHotelsPWA("PWA_Step1_CheckIN_Date"));
		if(!CheckINText.contains("Check-in")) {
			Reporter.log("CheckIN text not displayed");
			//Assert.assertTrue(false);
		}
		if(Booking_Type.equals("Policy")) {
			if(!elementVisible(driver, getObjectHotelsPWA("PWA_Step1_View_Policy"), 2)) {
				Reporter.log("View policy link is not displayed in Itinerary page");
				Assert.assertTrue(false);
			}
			safeClick(driver, getObjectHotelsPWA("PWA_Step1_View_Policy"));
			elementVisible(driver, getObjectHotelsPWA("PWA_Step1_PolicyText"), 10);
			Thread.sleep(5000);
			if(!getText(driver, getObjectHotelsPWA("PWA_Step1_PolicyText")).contains("Policy Benefits")) {
				Reporter.log("Policy Benefits text is not displayed in policy popup");
				Assert.assertTrue(false);
			}
			safeClick(driver, getObjectHotelsPWA("PWA_Step1_Policy_Close"));
			
		}
		safeClick(driver, getObjectHotelsPWA("PWA_Step1_Continue_Button"));
	}
	
	public void travellerStepPWA(RemoteWebDriver driver, String SignIN, String BookingType) throws Exception {
		elementVisible(driver, getObjectHotelsPWA("PWA_Step2_HotelSummary_Text"), 20);
		if(textPresent(driver, "Something went wrong. Please try again after sometime.", 2)) {
			Reporter.log("Something went wrong. Please try again after sometime. error is displayed");
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, getObjectHotelsPWA("PWA_Step2_HotelSummary_Text"), "Traveller details page not displayed", 1);
		String CheckINText = getText(driver, getObjectHotelsPWA("PWA_Step2_HotelSummary_Text"));
		if(!CheckINText.contains("Hotel Summary")) {
			Reporter.log("Hotel Summary text not displayed");
		//	Assert.assertTrue(false);
		}
		safeSelectByIndex(driver, getObjectHotelsPWA("PWA_Step2_SelectTitle"), 1);
		safeType(driver, getObjectHotelsPWA("PWA_Step2_FirstName"), "Test");
		safeType(driver, getObjectHotelsPWA("PWA_Step2_LastName"), "Booking");
		safeType(driver, getObjectHotelsPWA("PWA_Step2_Mobile"), "123456123456");
		if(SignIN.equals("SignIN")) {
			safeType(driver, getObjectHotelsPWA("PWA_Step2_Email"), dataFile.value("HotelEmailIDPWA"));
			safeClick(driver, getObjectHotelsPWA("PWA_Step2_HaveCleartripAccount_CheckBox"));
			elementVisible(driver, getObjectHotelsPWA("PWA_Step2_Password"), 10);
			safeType(driver, getObjectHotelsPWA("PWA_Step2_Password"), dataFile.value("HotelPasswordPWA"));
			if(ProductionUrl) {
				safeType(driver, getObjectHotelsPWA("PWA_Step2_Email"), dataFile.value("HotelEmailIDPWA"));
				safeType(driver, getObjectHotelsPWA("PWA_Step2_Password"), dataFile.value("HotelPasswordPWA"));
			}
			
		}
		if(SignIN.equals("StoredCard")) {
			safeType(driver, getObjectHotelsPWA("PWA_Step2_Email"), "cleartriptester@gmail.com");
			safeClick(driver, getObjectHotelsPWA("PWA_Step2_HaveCleartripAccount_CheckBox"));
			safeType(driver, getObjectHotelsPWA("PWA_Step2_Password"), "cleartrip");
		}
		else if(SignIN.equals("UnSigned")) {
			safeType(driver, getObjectHotelsPWA("PWA_Step2_Email"), dataFile.value("HotelEmailIDPWA"));
		}
		if(BookingType.equals("GST")) {
			elementVisible(driver, getObjectHotelsPWA("PWA_Step2_GST_CheckBox"), 5);
			safeClick(driver, getObjectHotelsPWA("PWA_Step2_GST_CheckBox"));
			Thread.sleep(1000);
			safeType(driver, getObjectHotelsPWA("PWA_Step2_GST_Number"), "29AAAAA0000A1Z1");
			Thread.sleep(1000);
			safeType(driver, getObjectHotelsPWA("PWA_Step2_GST_HolderName"), dataFile.value("HotelGSTHolderName"));
			safeType(driver, getObjectHotelsPWA("PWA_Step2_GST_HolderAddress"), dataFile.value("HotelGSTHolderAddress"));
		}
		safeClick(driver, getObjectHotelsPWA("PWA_Step2_Continue_Button"));
	}
	
	public String paymentStepPWA(RemoteWebDriver driver, String BookingType, String PaymentType, String Trip_Logger_Msg) throws Exception {
		if(textPresent(driver, "Something went wrong. Please try again after sometime.", 2)) {
			Reporter.log("Something went wrong. Please try again after sometime. error is displayed");
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, getObjectHotelsPWA("PWA_Step3_CreditCard_Tab"), "Payment details page not displayed", 30);
		if(!elementVisible(driver, getObjectHotelsPWA("PWA_Step3_CreditCard_Tab"), 20)) {
			if(elementVisible(driver, getObjectHotelsPWA("PWA_Step3_CreditCard_Tab"), 1)) {
			smartClick(driver, getObjectHotelsPWA("PWA_Step2_Continue_Button"));			
			}
		}
		paymentPagePWASelectBookingType(driver, "Pay full amount");
		if(BookingType.contains("PAHCC")){
			paymentPagePWASelectBookingType(driver, "Pay at hotel");
			if(!textPresent(driver, "You wont be charged for this booking. This hotel requires a credit card to guarantee this booking", 2)){
				Reporter.log("PAH Creadit Card text message is not displayed");
				Assert.assertTrue(false);
			}			
		}	else if(BookingType.contains("PARTPAY")){
			paymentPagePWASelectBookingType(driver, "PartPay");
		}	else if(BookingType.equals("PAHOTP")){
			paymentPagePWASelectBookingType(driver, "Pay at hotel");
			String OTPGenerated = hotelComPAHSendOTP(driver);
			if(!elementNotVisible(driver, getObjectHotelsPWA("PWA_Step3_MakePayment"), 1)) {
				Reporter.log("Confirm Booking button should not be displayed before OTP is verified");
				Assert.assertTrue(false);
			}
			//paymentPagePWASelectBookingType(driver, "Pay at hotel");
			elementPresent_log(driver, getObjectHotelsPWA("PWA_Step3_PAH_OTP_Text"), "To make a booking, We need to verify your mobile text ", 10);
			String PAHOTPText = getText(driver, getObjectHotelsPWA("PWA_Step3_PAH_OTP_Text"));
			if(!PAHOTPText.contains("To make a booking, We need to verify your mobile")) {
				Reporter.log("To make a booking, We need to verify your mobile text message is not displayed");
				Assert.assertTrue(false);
			}
			elementPresent_log(driver, getObjectHotelsPWA("PWA_Step3_PAH_OTP_TapToVerify"), "TAP TO VERIFY", 10);
			safeClick(driver, getObjectHotelsPWA("PWA_Step3_PAH_OTP_TapToVerify"));
			elementVisible(driver, getObjectHotelsPWA("PWA_Step3_PAH_OTP_TextBox"), 10);
			safeType(driver, getObjectHotelsPWA("PWA_Step3_PAH_OTP_TextBox"), OTPGenerated);
			
			safeClick(driver, getObjectHotelsPWA("PWA_Step3_PAH_OTP_Verify_Button"));
			
			String PAHOTPSuccessText = getText(driver, getObjectHotelsPWA("PWA_Step3_PAH_OTP_Text"));
			if(!PAHOTPSuccessText.contains("Awesome! Book now")) {
				Reporter.log("Awesome! Book now - text message is not displayed");
				Assert.assertTrue(false);
			}
		}  else if(BookingType.equals("PAH")){
			paymentPagePWASelectBookingType(driver, "Pay at hotel");
		}		
		if(PaymentType.equals("NB")) {
			elementVisible(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_Tab"), 10);
			safeClick(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_Tab"));
			safeClick(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_DropDown"));
			elementVisible(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_TextBox"), 10); 
			if(!ProductionUrl) {
			safeType(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_TextBox"), NetBankingQA);
			} else safeType(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_TextBox"), NetBankingProd);
			safeClick(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_SelectBank"));
			elementVisible(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_DropDown"), 10);
			String NBSelected = getText(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_DropDown"));
			if(!ProductionUrl) {
			if(!NBSelected.equals(NetBankingQA)) {
				Reporter.log("NB select is CitiBank displayed is : "+NBSelected);
				Assert.assertTrue(false);
			}
			} else if(!NBSelected.equals(NetBankingProd)) {
				Reporter.log("NB select is CitiBank displayed is : "+NBSelected);
				Assert.assertTrue(false);
			}
			safeClick(driver, getObjectHotelsPWA("PWA_Step3_MakePayment"));
		}
		if(PaymentType.equals("NBRetry")) {
			elementVisible(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_Tab"), 10);
			safeClick(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_Tab"));
			safeClick(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_DropDown"));
			if(!ProductionUrl) {
				safeType(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_TextBox"), NetBankingQA);
				} else safeType(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_TextBox"), NetBankingProd);
				safeClick(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_SelectBank"));
				elementVisible(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_DropDown"), 10);
				String NBSelected = getText(driver, getObjectHotelsPWA("PWA_Step3_Netbanking_DropDown"));
				if(!ProductionUrl) {
				if(!NBSelected.equals(NetBankingQA)) {
					Reporter.log("NB select is CitiBank displayed is : "+NBSelected);
					Assert.assertTrue(false);
				}
				}  else if(!NBSelected.equals(NetBankingProd)) {
					Reporter.log("NB select is CitiBank displayed is : "+NBSelected);
					Assert.assertTrue(false);
				}			safeClick(driver, getObjectHotelsPWA("PWA_Step3_MakePayment"));
				if(NBSelected.equals("Citibank")) {
					textPresent(driver, "Or, Go Back to www.cleartrip.com", 10);
					elementPresent_log(driver, getObjectHotelsPWA("PWA_NB_CityBank_PayNow_Button"), "CityBank NB page ", 20);
					elementPresent_log(driver, getObjectHotelsPWA("PWA_NB_CityBank_Back_Button"), "CityBank NB Back Button ", 20);
					safeClick(driver, getObjectHotelsPWA("PWA_NB_CityBank_Back_Button"));			
					elementPresent_log(driver, getObjectHotelsPWA("PWA_PaymentRetry_PopUp_Retry_Button"), "", 10);
					if(!textPresent(driver, "Oops, Payment failed. You can retry or go back to home", 5)) {
						Reporter.log("Oops, Payment failed. You can retry or go back to home : message is not displayed");
						Assert.assertTrue(false);
					}
					}	else if(NBSelected.equals("Bank of India")) {
						elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
						elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"),5);
						safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
						elementVisible(driver, By.linkText("Cancel"), 5);
						smartClick(driver, By.linkText("Cancel"));
						elementPresent_log(driver, getObjectHotelsPWA("PWA_PaymentRetry_PopUp_Retry_Button"), "", 10);
						if(!textPresent(driver, "Oops, Payment failed. You can retry or go back to home", 5)) {
							Reporter.log("Oops, Payment failed. You can retry or go back to home : message is not displayed");
							Assert.assertTrue(false);
						}
						}
			smartClick(driver, getObjectHotelsPWA("PWA_PaymentRetry_PopUp_Retry_Button"));	
			if(!elementVisible(driver, getObjectHotelsPWA("PWA_Step3_CreditCard_Tab"), 10)) {
				Reporter.log("Credit Card Tab is not displayed");
				Assert.assertTrue(false);
			}
		}
		
		if(MakePaymentOnlyInQA2) {
			if(PaymentType.equals("CC")||PaymentType.equals("PAHCC")||PaymentType.equals("")) {
				elementVisible(driver, getObjectHotelsPWA("PWA_Step3_CreditCard_Tab"), 10);
				safeClick(driver, getObjectHotelsPWA("PWA_Step3_CreditCard_Tab"));
				safeType(driver, getObjectHotelsPWA("PWA_Step3_CreditCard_Number"), dataFile.value("MasterCard_Number"));
				safeType(driver, getObjectHotelsPWA("PWA_Step3_CreditCard_Date"), dataFile.value("MasterCard_MWeb_Exp_Date"));
				safeType(driver, getObjectHotelsPWA("PWA_Step3_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
			}
			else if(PaymentType.equals("StoredCard")) {
				elementVisible(driver, getObjectHotelsPWA("PWA_Step3_StoredCard"), 10);
				safeClick(driver, getObjectHotelsPWA("PWA_Step3_StoredCard"));
				safeType(driver, getObjectHotelsPWA("PWA_Step3_StoredCard_CVV"), dataFile.value("MasterCard_CVV"));
			}
			if(BookingType.equals("COUPON")){
				elementVisible(driver, getObjectHotelsPWA("PWA_Step3_Coupon"), 5);
				safeType(driver, getObjectHotelsPWA("PWA_Step3_Coupon"), "hotel123");
				safeClick(driver, getObjectHotelsPWA("PWA_Step3_Coupon_Apply"));
				Thread.sleep(5000);
				textPresent(driver, "Great! You just saved", 20);
				elementVisible(driver, getObjectHotelsPWA("PWA_Step3_Coupon_Success_Text"), 20);				
				String CouponSuccess = getText(driver, getObjectHotelsPWA("PWA_Step3_Coupon_Success_Text"));
				if(!CouponSuccess.contains("Great! You just saved")) {
					Reporter.log("Great! You just saved text is not displayed");
					Assert.assertTrue(false);
				}
				
			}
		safeClick(driver, getObjectHotelsPWA("PWA_Step3_MakePayment"));
		}
		String TripID =null ;
		return TripID;
	}
	
	public void paymentStepPWA_Second(RemoteWebDriver driver, String BookingType, String PaymentType, String Trip_Logger_Msg) {
		
	}
	
	public String confirmationPagePWA(RemoteWebDriver driver, String BookingType , String Trip_Logger_Msg, String Confirmation_Msg) throws Exception{
		String TripID =null;
		logURL(driver);
		if(MakePaymentOnlyInQA2) {
		elementVisible(driver, getObjectHotelsPWA("PWA_Confirmation_CTGuarantee_Img"), 50);
		elementPresent(driver, getObjectHotelsPWA("PWA_Confirmation_CTGuarantee_Img"));
		elementPresent_log(driver, getObjectHotelsPWA("PWA_Confirmation_TripID"), "TripID", 20);
		elementPresent_log(driver, getObjectHotelsPWA("PWA_Confirmation_Booking_Confirmed_Text"), "Confirmation Text", 1);
		elementPresent_log(driver, getObjectHotelsPWA("PWA_Confirmation_BackToHome_Button"), "Back to Home Button", 1);
		TripID = getText(driver, getObjectHotelsPWA("PWA_Confirmation_TripID"));
		Reporter.log(Trip_Logger_Msg+" - "+TripID);
		logger.info(Trip_Logger_Msg +" - "+ TripID);
		if(BookingType.equals("PAHCC")) {
			elementPresent(driver, getObjectHotelsPWA("PWA_Confirmation_PAHCC_Text"), 10);
			String PAHCCText = getText(driver, getObjectHotelsPWA("PWA_Confirmation_PAHCC_Text"));
			if(!PAHCCText.contains("To be paid at the hotel at the time of check in")) {
				Reporter.log("To be paid at the hotel at the time of check in text message is not displayed");
				Assert.assertTrue(false);
			}
		}
		}
		return TripID;		
	}
	
	public void paymentPagePWASelectBookingType(RemoteWebDriver driver, String SelectBookingType) throws Exception {
		elementVisible(driver, By.xpath("//div[2]/div/div[1]/ul/li"), 20);
		List<WebElement> BookingType = driver.findElements(By.xpath("//div[2]/div/div[1]/ul/li"));
		for (int i = 0; i < BookingType.size(); i++) {
			String BookingTypeText = BookingType.get(i).getText();
			Reporter.log("Booktype: "+BookingTypeText);
			if(BookingTypeText.contains(SelectBookingType)){
				BookingType.get(i).click();
				break;
			}
			else if(i==BookingType.size()) {
				Reporter.log("Select Booking type option is not displayed : "+SelectBookingType);
				Assert.assertTrue(false);
			}
			}
	}		
	
	public void PaymentPage_Authentication(RemoteWebDriver driver, String Values) throws Exception {
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