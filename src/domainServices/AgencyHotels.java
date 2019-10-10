// Framework - Cleartrip Automation
// Author - Kiran Kumar

package domainServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import junit.framework.Assert;

	public class AgencyHotels extends IndiaHotels {
	private static String Agency_Url, CTPhone_Url= null;	
	private String baseUrl = "http://"+common.value("host")+common.value("url")+"com";
	private String baseUrl_AE = "http://"+common.value("host")+common.value("url")+"ae";
	private String NBSite  = "BOI"; // old value CITI
	public String HotelProcessingFee  = null;
	
	public String Agency_Url() throws Exception {
		if (common.value("host").contains("qa2") ) {
			Agency_Url = dataFile.value("Agency_Hotels_QA2");
			
		} else if(common.value("host").contains("hf")) {
			Agency_Url = dataFile.value("Agency_Hotels_HF");			
		} 
		else if(common.value("host").contains("www")) {
			Agency_Url = dataFile.value("Agency_Hotels_Prod");			
		} 
		 else if(common.value("host").contains("beta")) {
				Agency_Url = dataFile.value("Agency_Beta");				
			} 
		 else if(common.value("host").contains("stg1")) {
				Agency_Url = dataFile.value("Agency_Stg1");				
			} 
		return Agency_Url; 
	}

	public String CTPhone_Url() throws Exception {
		if (common.value("host").contains("qa2") || common.value("host").contains("hf")) {
			CTPhone_Url = dataFile.value("CTPhone_QA2");
			
		} /*else if(common.value("host").equalsIgnoreCase("hf")) {
			CTPhone_Url = dataFile.value("CTPhone_Prod");			
		} */
		else if(common.value("host").contains("www")) {
			CTPhone_Url = dataFile.value("CTPhone_Prod");			
		} 
		 else if(common.value("host").contains("beta")) {
			 CTPhone_Url = dataFile.value("CTPhone_Beta");				
			} 
		 else if(common.value("host").contains("stg1")) {
			 CTPhone_Url = dataFile.value("CTPhone_Stg1");				
			} 
		return CTPhone_Url; 
	}
	
	public void agency_SignIn(RemoteWebDriver driver) throws Exception{
		driver.manage().deleteAllCookies();
		//driver.navigate().refresh();
		if(textPresent(driver, "an error occurred while processing this directive", 1)) {
			Reporter.log("an error occurred while processing this directive : Error is displayed");
			Assert.assertTrue(false);
			}
		
		if(elementVisible(driver, getObjectHotels("Agency_SignIN_EmailID"), 50)) {
		//	Reporter.log("Agency SignIn Page is not displayed");
			if (common.value("host").contains("qa2")  ) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("Agency_Hotels_QA2_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("Agency_Hotels_QA2_Password"));
			}else if (common.value("host").contains("hf")  ) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("Agency_Hotels_HF_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("Agency_Hotels_HF_Password"));
			}else if(common.value("host").contains("www")) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("Agency_Hotels_Prod_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("Agency_Hotels_Prod_Password"));
			} else if(common.value("host").contains("beta")) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("Agency_Beta_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("Agency_Beta_Password"));
			} else if(common.value("host").contains("stg1")) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("Agency_Stg1_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("Agency_Stg1_Password"));
			}
			safeClick(driver, getObjectHotels("Agency_SignIN_Button"));
		}
		else if(elementVisible(driver, getObjectHotels("Agency_New_SignIN_EmailID"), 50)){
			//Reporter.log("Agency SignIn Page is not displayed");
			if (common.value("host").contains("qa2")  ) {
				safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"), dataFile.value("Agency_Hotels_QA2_Username"));
				safeType(driver, getObjectHotels("Agency_New_SignIN_Password"), dataFile.value("Agency_Hotels_QA2_Password"));
			}else if (common.value("host").contains("hf")  ) {
				safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"), dataFile.value("Agency_Hotels_HF_Username"));
				safeType(driver, getObjectHotels("Agency_New_SignIN_Password"), dataFile.value("Agency_Hotels_HF_Password"));
			}else if(common.value("host").contains("www")) {
				safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"), dataFile.value("Agency_Hotels_Prod_Username"));
				safeType(driver, getObjectHotels("Agency_New_SignIN_Password"), dataFile.value("Agency_Hotels_Prod_Password"));
			} else if(common.value("host").contains("beta")) {
				safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"), dataFile.value("Agency_Beta_Username"));
				safeType(driver, getObjectHotels("Agency_New_SignIN_Password"), dataFile.value("Agency_Beta_Password"));
			} else if(common.value("host").contains("stg1")) {
				safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"), dataFile.value("Agency_Stg1_Username"));
				safeType(driver, getObjectHotels("Agency_New_SignIN_Password"), dataFile.value("Agency_Stg1_Password"));
			}
			safeClick(driver, getObjectHotels("Agency_New_SignIN_Button"));
		}		
		
		if(textPresent(driver, "Read timed out", 1)) {
			if (common.value("host").contains("qa2")  ) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("Agency_Hotels_QA2_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("Agency_Hotels_QA2_Password"));
			}else if (common.value("host").contains("hf")  ) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("Agency_Hotels_HF_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("Agency_Hotels_HF_Password"));
			}
			
			else if(common.value("host").contains("www")) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("Agency_Hotels_Prod_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("Agency_Hotels_Prod_Password"));
			} else if(common.value("host").contains("beta")) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("Agency_Beta_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("Agency_Beta_Password"));
			} else if(common.value("host").contains("stg1")) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("Agency_Stg1_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("Agency_Stg1_Password"));
			}
			safeClick(driver, getObjectHotels("Agency_SignIN_Button"));
		}
	}
	
	//---------------------------CTauth service signin for agency------------------------------//
	public void agency_Ctauth_SignIn(RemoteWebDriver driver) throws Exception{
		driver.manage().deleteAllCookies();
		if(textPresent(driver, "an error occurred while processing this directive", 1)) {
			Reporter.log("an error occurred while processing this directive : Error is displayed");
			Assert.assertTrue(false);
			}
		if(elementVisible(driver, getObjectHotels("Agency_New_SignIN_EmailID"), 50)){
			Reporter.log("Agency SignIn Page is not displayed");
		}
		if (common.value("host").contains("qa2")  ) {
			safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"), dataFile.value("Agency_Hotels_QA2_Username"));
			safeType(driver, getObjectHotels("Agency_New_SignIN_Password"), dataFile.value("Agency_Hotels_QA2_Password"));
		}else if (common.value("host").contains("hf")  ) {
			safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"), dataFile.value("Agency_Hotels_HF_Username"));
			safeType(driver, getObjectHotels("Agency_New_SignIN_Password"), dataFile.value("Agency_Hotels_HF_Password"));
		}else if(common.value("host").contains("www")) {
			safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"), dataFile.value("Agency_Hotels_Prod_Username"));
			safeType(driver, getObjectHotels("Agency_New_SignIN_Password"), dataFile.value("Agency_Hotels_Prod_Password"));
		} else if(common.value("host").contains("beta")) {
			safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"), dataFile.value("Agency_Beta_Username"));
			safeType(driver, getObjectHotels("Agency_New_SignIN_Password"), dataFile.value("Agency_Beta_Password"));
		} else if(common.value("host").contains("stg1")) {
			safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"), dataFile.value("Agency_Stg1_Username"));
			safeType(driver, getObjectHotels("Agency_New_SignIN_Password"), dataFile.value("Agency_Stg1_Password"));
		}
	safeClick(driver, getObjectHotels("Agency_New_SignIN_Button"));
	}
	
	public void CTPhone_SignIn(RemoteWebDriver driver) throws Exception{
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		if(!elementVisible(driver, getObjectHotels("Agency_SignIN_EmailID"), 50)) {
			Reporter.log("CTphone SignIn Page is not displayed");
		}
		if (common.value("host").contains("qa2")||common.value("host").contains("hf") ) {
			safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("CTPhone_QA2_Username"));
			safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("CTPhone_QA2_Password"));
		} else if(common.value("host").contains("www")) {
			safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("CTPhone_Prod_Username"));
			safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("CTPhone_Prod_Password"));
		} else if(common.value("host").contains("beta")) {
			safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("CTPhone_Beta_Username"));
			safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("CTPhone_Beta_Password"));
		} else if(common.value("host").contains("stg1")) {
			safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("CTPhone_Stg1_Username"));
			safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("CTPhone_Stg1_Password"));
		}
		
		safeClick(driver, getObjectHotels("Agency_SignIN_Button"));
		if(textPresent(driver, "Read timed out", 1)) {
			if (common.value("host").contains("qa2")||common.value("host").contains("hf") ) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("CTPhone_QA2_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("CTPhone_QA2_Password"));
			} else if(common.value("host").contains("www")) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("CTPhone_Prod_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("CTPhone_Prod_Password"));
			} else if(common.value("host").contains("beta")) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("CTPhone_Beta_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("CTPhone_Beta_Password"));
			} else if(common.value("host").contains("stg1")) {
				safeType(driver, getObjectHotels("Agency_SignIN_EmailID"), dataFile.value("CTPhone_Stg1_Username"));
				safeType(driver, getObjectHotels("Agency_SignIN_Password"), dataFile.value("CTPhone_Stg1_Password"));
			}
			safeClick(driver, getObjectHotels("Agency_SignIN_Button"));
		}
	}
	
	public String agencyHotel_SrpUrl(RemoteWebDriver driver, String City, String State, String Country, String Adults, int FromDate, int ToDate) throws Exception {
		driver.get(Agency_Url());
		agency_SignIn(driver);		
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Agency_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=1&ca1=5&num_rooms=1";
		SRP_URL = domainURL+URL_2;
		driver.get(SRP_URL);
		Reporter.log("Srp URL : "+SRP_URL);
		return SRP_URL;		
	}
	
	public String agencyHotel_SrpUrl_No_SignIN(RemoteWebDriver driver, String City, String State, String Country) throws Exception {
		String Check_In_Date = putDate1(14);
		String Check_Out_Date = putDate1(16);
		String domainURL = Agency_Url();
		String URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1=1&children1=1&ca1=5&num_rooms=1";
		String SRP_URL = domainURL+URL_2;
		driver.get(SRP_URL);
		Reporter.log("Srp URL : "+SRP_URL);
		return SRP_URL;		
	}
	
	public String agencyHotel_SrpUrl_HotelName(RemoteWebDriver driver, String City, String State, String Country, String Adults, int FromDate, int ToDate, String HotelName) throws Exception {
		driver.get(Agency_Url());
		agency_SignIn(driver);		
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Agency_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&hotelId=&dest_code=&hotelId=&hotelName="+HotelName+"&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=1&ca1=5&num_rooms=1&";
		SRP_URL = domainURL+URL_2;
		driver.get(SRP_URL);
		Reporter.log("Srp URL : "+SRP_URL);
		return SRP_URL;		
	}
	
	public String agencyHotel_DetailsPageUrl(RemoteWebDriver driver,  String HotelID, int Date) throws Exception {
		driver.get(Agency_Url());
		agency_SignIn(driver);		
		String CheckIn = putDateNoSplChar(Date);
		String CheckOut = putDateNoSplChar(Date+1);
		String domainURL = Agency_Url();
		//URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=1&ca1=5&num_rooms=1";
		String detailsPageUrlLink = domainURL+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0";
		return detailsPageUrlLink;				
	}
	
	public String agencyHotel_DetailsPageUrl_NoSignIN(RemoteWebDriver driver,  String HotelID, int Date) throws Exception {
		String CheckIn = putDateNoSplChar(Date);
		String CheckOut = putDateNoSplChar(Date+1);
		String domainURL = Agency_Url();
		String detailsPageUrlLink = domainURL+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0";
		return detailsPageUrlLink;				
	}
	
	public String ctPhoneHotel_SrpUrl(RemoteWebDriver driver, String City, String State, String Country, String Adults, int FromDate, int ToDate) throws Exception {
		driver.get(CTPhone_Url());
		CTPhone_SignIn(driver);
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = CTPhone_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		SRP_URL = domainURL+URL_2;
		driver.get(SRP_URL);
		Reporter.log("Srp URL : "+SRP_URL);
		return SRP_URL;		
	}

	public String ctPhoneHotel_DetailsPageUrl(RemoteWebDriver driver,  String HotelID, int Date) throws Exception {
		driver.get(CTPhone_Url());
		CTPhone_SignIn(driver);
		String CheckIn = putDateNoSplChar(Date);
		String CheckOut = putDateNoSplChar(Date+1);
		String domainURL = CTPhone_Url();
		//URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=1&ca1=5&num_rooms=1";
		String detailsPageUrlLink = domainURL+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0";
		return detailsPageUrlLink;				
	}
	
	public void agencyHotel_DetailsPage_BackButton(RemoteWebDriver driver) throws Exception {
		if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
			   Reporter.log("Hotel Rate is not displayed in Details page");
		   }
	 	if(!elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Back_Button"), 5)) {
	 		Reporter.log("Back button is not displayed");
	 		Assert.assertTrue(false);
	 	}
	 	String CityName = getText(driver, getObjectHotels("HotelCom_DetailsPage_Back_Button"));
	 	CityName = CityName.replaceAll(" All hotels in", "");
	 	String[] CityName1 = CityName.split(" ");
	 	CityName = CityName1[1];
	 	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Back_Button"));
	 	elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 5);
	 	String CityName_SRP = getText(driver, getObjectHotels("HotelCom_SRP_CityName"));
	 	if(!CityName_SRP.contains(CityName)) {
	 		Reporter.log("City Name is different : Details Page City : "+CityName+" SRP City Name : "+CityName_SRP);
	 		Assert.assertTrue(false);
	 	}
		
	}
	
	public void agency_Hotel_Add_OntheGo_Markup(RemoteWebDriver driver) throws Exception, InterruptedException, NumberFormatException, IOException {
		String TotalPrice = getText(driver, getObjectHotels("AgencyHotels_Itinerarypage_TotalPrice"));
		Thread.sleep(2000);
		String BookingFeeAgency=driver.findElement(By.xpath("//dl[@class='horizontal summary']/dd/input[@type='text']")).getText().trim().replace("Rs. ", "").replace(".00", "");
		//old -> String BookingFeeAgency=driver.findElement(By.xpath("//dl[@class='horizontal summary']/dd[3]")).getText().trim().replace("Rs. ", "").replace(".00", "");
		int BookingFeeHotelAgency = Integer.parseInt(BookingFeeAgency);
		safeType(driver, getObjectHotels("AgencyHotels_Itinerarypage_AgencyService_Fee"), "500");
		Thread.sleep(2000);
		String TotalPrice_After_Markup = getText(driver, getObjectHotels("AgencyHotels_Itinerarypage_TotalPrice"));
		TotalPrice = TotalPrice.replace("Rs. ", "");
		TotalPrice_After_Markup = TotalPrice_After_Markup.replace("Rs. ", "");
		if(TotalPrice.contains(",")){
			TotalPrice = TotalPrice.replace(",", "");
		}
		if(TotalPrice_After_Markup.contains(",")){
			TotalPrice_After_Markup = TotalPrice_After_Markup.replace(",", "");
		}
		
		int TotalPrice_int = Integer.parseInt(TotalPrice)-BookingFeeHotelAgency;
		int TotalPrice_After_Markup_int = Integer.parseInt(TotalPrice_After_Markup);
		if(!(TotalPrice_After_Markup_int==(TotalPrice_int+500))){
			assertCommon(driver, "AgencyMarkup", 1, "Markup Amount is not adding up with total price");
		}
	}
	
	public void CTPhone_Hotel_OntheGo_Discount(RemoteWebDriver driver) throws Exception, InterruptedException, NumberFormatException, IOException {
		String TotalPrice = getText(driver, getObjectHotels("AgencyHotels_Itinerarypage_TotalPrice"));
		Thread.sleep(2000);
		safeType(driver, getObjectHotels("CTPhoneAgencyHotels_Itinerarypage_CT_Discount"), "1");
		Thread.sleep(2000);
		String TotalPrice_After_Discount = getText(driver, getObjectHotels("AgencyHotels_Itinerarypage_TotalPrice"));
		TotalPrice = TotalPrice.replace("Rs. ", "");
		TotalPrice_After_Discount = TotalPrice_After_Discount.replace("Rs. ", "");
		if(TotalPrice.contains(",")){
			TotalPrice = TotalPrice.replace(",", "");
		}
		if(TotalPrice_After_Discount.contains(",")){
			TotalPrice_After_Discount = TotalPrice_After_Discount.replace(",", "");
		}	
		int TotalPrice_int = Integer.parseInt(TotalPrice);
		int TotalPrice_After_Markup_int = Integer.parseInt(TotalPrice_After_Discount);
		if(!(TotalPrice_After_Markup_int==(TotalPrice_int-1))){
			assertCommon(driver, "CTPhone Discount", 1, "Discount is not applied with total price");
		}
		safeSelect(driver, getObjectHotels("CTPhoneAgencyHotels_Itinerarypage_CT_Discount_Reason_DropDown"), "Manual discount");
	}
	
	public String agencyAir_Paymentpage(RemoteWebDriver driver,  String PaymentType, String Coupon, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		for(int i=0;i<=15;i++) {
		if(textPresent(driver, "There is a problem with your submission", 1)){
			Reporter.log("There is a problem with your submission : message is displayed in Traveler Page");
			break;
		}
		if(elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"), 2)) {
				break;
		} else if(elementVisible(driver, By.xpath("(//a[contains(text(),'Modify search')])[2]"), 1)) {
				Reporter.log("Page has redirected back to SRP after clicking on continue in Traveller page");
				Assert.assertTrue(false);
			}
		}
		textPresent(driver, "How would you like to pay?", 30);
		String TripID= null;
		if(Coupon.equalsIgnoreCase("COUPON"))
		{	
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"), 30);
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2BAIR_Coupon"));
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_Button"));
			elementVisible(driver, By.cssSelector("div.savingsDetails.conditional > ul.inline.clearfix"), 30);
			textPresent(driver, "final cost", 20);
			Reporter.log("Coupon Applied ");
		}
		return TripID = agencyPayment(driver, PaymentType, Trip_Logger_Msg, Booking_Confirmation_Message);
	}
	
	public String CTPhoneAir_Paymentpage(RemoteWebDriver driver,  String PaymentType, String Coupon, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		if(!elementPresent_Time(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"), 10)){
			Reporter.log("PaymentPage is not displayed");
		}if(Coupon.equalsIgnoreCase("COUPON"))
		{	
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"), 30);
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2BAIR_Coupon"));
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_Button"));
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 30);
			textAssert(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"), "Great, you are eligible to a cashback");
			Reporter.log("Coupon Applied ");
		}
		String TripID= null;
		textPresent(driver, "How would you like to pay?", 5);
		return TripID = agencyPayment(driver, PaymentType, Trip_Logger_Msg, Booking_Confirmation_Message);
	}
		
	public Map<String, String> agencyHotel_SRP(RemoteWebDriver driver, String Hotel_Name, String Booking_Type) throws Exception {    
		Map<String, String> AgencySRPData = new HashMap<String , String>();	
		if(textPresent(driver, "Sorry, our system is acting up", 1)){
			Reporter.log("Sorry, our system is acting up : error is displayed in SRP");
			refreshPage(driver);
		}
	for(int i =0; i <= 10; i++){
	if(textPresent(driver, "Sorry, our system is acting up", 1)){
		Reporter.log("Sorry, our system is acting up : error is displayed in SRP");
		Assert.assertTrue(false);
	}
	else if(textPresent(driver, "Sorry, we couldn't find any hotels", 1)){
		Reporter.log("Sorry, we couldn't find any hotels : error is displayed in SRP");
		Assert.assertTrue(false);
	}
	else if(!elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 5)) {
		//Reporter.log("Results are not displayed in SRP");	
	}
	else break;
	}
	logURL(driver);
/*	if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Rooms_Unavailable"), 2)) {
		refreshPage(driver);
		elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 30);
		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Rooms_Unavailable"), 2)) {
			Reporter.log("Room Unavailable in SRP");
			Assert.assertTrue(false);
		}
	}*/
	elementPresent(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
	Thread.sleep(2000);
	safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 
	if (!Hotel_Name.isEmpty()) {
		Reporter.log("Hotel Selected is "+Hotel_Name);
		elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 20);
		safeAutocomplete(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), getObjectHotels("HotelCom_SRP_HotelName_Ajax"), Hotel_Name);
	//	elementPresent_log(driver, By.linkText(Hotel_Name), "Hotel selected ", 5);
	}
	if (Booking_Type.isEmpty()) {
	
	safeClick(driver, getObjectHotels("AgencyHotels_SRP_SelectRoom_Button"));
	Thread.sleep(1000);
	if (!elementPresent_Time(driver, getObjectHotels("AgencyHotels_SRP_BookRoom_Button"), 10)) {
		safeClick(driver, getObjectHotels("AgencyHotels_SRP_SelectRoom_Button"));
	}
	safeClick(driver, getObjectHotels("AgencyHotels_SRP_BookRoom_Button"));
	}
	else if (Booking_Type.equalsIgnoreCase("SPECIALRATE")) {
		
		elementPresent(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"));

		safeClick(driver, By.xpath("(//a[contains(text(),'Special rates')])[2]"));
		safeClick(driver, By.xpath("(//a[contains(text(),'Special rates')])[2]"));
		for (int i = 1; i <= 15; i++) {
			String SpecialRateHotel_XPATH = "//li[" + i + "]/section/div[5]/div/span";
			String SpecialRate_SelectRoom_Xpath = "//li[" + i + "]/section/div[5]/div/button";
			if (elementVisible(driver, By.xpath(SpecialRateHotel_XPATH), 1)) {
				safeClick(driver, By.xpath(SpecialRate_SelectRoom_Xpath));
				for (int j = 1; j <= 3; j++) {
					String SpecialRate_XPATH = "//li[" + i + "]/div/div[2]/table/tbody/tr[" + j + "]/td/small/span";
					if (elementPresent(driver, By.xpath(SpecialRate_XPATH))) {
						String SpecialRate_Book = "//li[" + i + "]/div/div[2]/table/tbody/tr[" + j + "]/td[6]/input";
						safeClick(driver, By.xpath(SpecialRate_Book));
						break;
					}
				}
				break;
			}
		}
	}
	else if (Booking_Type.equalsIgnoreCase("PACKAGERATE")) {
		safeClick(driver, By.xpath("(//a[contains(text(),'Special rates')])[2]"));
		safeClick(driver, By.xpath("(//a[contains(text(),'Special rates')])[2]"));
		elementPresent(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"));
		for (int i = 1; i <= 15; i++) {
			if(i==4) {
				i++;
			}
			String PackageRateHotel_XPATH  = "//li["+i+"]/section/div[5]/div/button"; 
			safeClick(driver, By.xpath(PackageRateHotel_XPATH));
			
			for (int j = 1; j <= 3; j++) {
			String PackageRoomRate_XPATH  = "//li["+i+"]/div/div[2]/table/tbody/tr["+j+"]/td/a";
			if(elementPresent_Time(driver, By.xpath(PackageRoomRate_XPATH), 1)){
				String SpecialRate = getText(driver, By.xpath(PackageRoomRate_XPATH));
				if(SpecialRate.contains("Package")){
					String PackageRoomRateButton = "//li["+i+"]/div/div[2]/table/tbody/tr["+j+"]/td[6]/input";
					safeClick(driver, By.xpath(PackageRoomRateButton));
					i = 16;
					break;			
				}
			}
			}
		}
	}
	else if (Booking_Type.equalsIgnoreCase("DATAVALIDATION")) {
		safeClick(driver, getObjectHotels("AgencyHotels_SRP_SelectRoom_Button"));		
		String SRPPrice = getText(driver, By.xpath("//h2[2]/strong")); // old - //h2[2]/div/strong
		SRPPrice = SRPPrice.replace("Rs.", "");
		AgencySRPData.put("Name", getText(driver, By.xpath("//li/h2/a")));
		AgencySRPData.put("Price", SRPPrice);
		AgencySRPData.put("RoomType", getText(driver, By.xpath("//td/a")));
		safeClick(driver, getObjectHotels("AgencyHotels_SRP_BookRoom_Button"));		
		}
	else if (Booking_Type.equalsIgnoreCase("DETAILSPAGEBOOK")) {
		safeClick(driver, getObjectHotels("HotelCorp_SRP_HotelName"));
		Thread.sleep(2000);
		hotelCom_SRP_ModalWindow_B2B(driver, "");
		}
	return AgencySRPData;
}

	public void agencyHotel_SRP_RateType(RemoteWebDriver driver, String Hotel_Name, int Room_Type) throws Exception {
		if(textPresent(driver, "Sorry, we couldn't find any hotels", 5)){
			Reporter.log("Sorry, we couldn't find any hotels is displayed in SRP");
			Assert.assertTrue(false);
			
		}
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 60, "Search Results Page has not loaded  :( :( :( :( :( :(");
		elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 20);
		logURL(driver);
					
				//------------------------------------------------------ Click Price Fiter ----------------------------------------------------------//
				
				elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
				safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 
				
				//------------------------------------------- Select the RoomType --------------------------------------------------------//
loop:		for(int i=1; i<5; i++) {
			//old SelectRoom_XPath -> //li["+i+"]/section/div[5]/div/button		
			String SelectRoom_XPath = "//li["+i+"]//div/button";
			safeClick(driver, By.xpath(SelectRoom_XPath));
			//String BookButton_XPath = "//input[(@type='button') and (@value='Book')]";
			String BookButton_XPath = "//li["+i+"]/div/div[2]/table/tbody/tr["+Room_Type+"]/td[6]/input";
			Thread.sleep(2000);
			elementNotVisible(driver, By.cssSelector("span.loader.dotDotDot"), 2);
			if(elementPresent_Time(driver, By.xpath(BookButton_XPath), 10)) {
				safeClick(driver, By.xpath(BookButton_XPath));
				break loop;
			}
		}
		
	}
	
	public void agencyHotel_HomepageSearch(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1,
	String Adult2, String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2) throws Exception{
		CheckIn_Date = getDate( "dd");
		CheckIn_Date = CheckIn_Date.substring(1);
		CheckIn_Date = "1"+CheckIn_Date;
		int DateInt = Integer.parseInt(CheckIn_Date);
		DateInt = DateInt+1;
		CheckOut_Date = Integer.toString(DateInt);
	if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_HotelTab"), 30)){
		Reporter.log("Login Failed/ Homepage not Loaded");
	}
	safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
	if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), 30)){
		safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));	
	}
	elementVisible(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), 30);
	safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), getObjectHotels("HotelCom_HomePage_SearchAjax"), City);
	selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 2, CheckIn_Date);
	selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
	agencyHotel_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
	String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value");
	String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
	Reporter.log("Hotels Searched for " + City + "CheckIn Date : " + FromDate + "CheckOut Date : " + TODate);
	safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
}
	
	public void agencyHotel_HomepageSearch_MultiRoomNights(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1,
			String Adult2, String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2) throws Exception{
				CheckIn_Date = getDate( "dd");
				CheckIn_Date = CheckIn_Date.substring(1);
				CheckIn_Date = "1"+CheckIn_Date;
				int DateInt = Integer.parseInt(CheckIn_Date);
				DateInt = DateInt+2;
				CheckOut_Date = Integer.toString(DateInt);
			if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_HotelTab"), 30)){
				Reporter.log("Login Failed/ Homepage not Loaded");
			}
			safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
			if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), 30)){
				safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));	
			}
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), 30);
			safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), getObjectHotels("HotelCom_HomePage_SearchAjax"), City);
			selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 2, CheckIn_Date);
			selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
			agencyHotel_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
			String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value");
			String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
			Reporter.log("Hotels Searched for " + City + "CheckIn Date : " + FromDate + "CheckOut Date : " + TODate);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
		}
				
	public void agencyHotel_PaxEntry(RemoteWebDriver driver, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, String Child1,
			String Child2, String ChildAge1, String ChildAge2) throws Exception {
			safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
			safeSelect(driver, getObjectHotels("HotelCom_HomePage_Room_DropDown"), Rooms);
			String Adults[] = new String[] { Adult1, Adult2, Adult3, Adult4 };
			String Children[] = new String[] { Child1, Child2 };
			String ChildAge[] = new String[] { ChildAge1, ChildAge2 };
			int Hotel_Rooms = Integer.parseInt(Rooms);
			for (int i = 1; i <= Hotel_Rooms; i++) {
				String AdultID = "Adult_" + i;
				if (elementVisible(driver, By.id(AdultID), 1)) {
					safeSelect(driver, By.id(AdultID), Adults[i - 1]);
				}
			}
			Boolean No_Children = Child1.contains("0") || Child2.contains("0");
			if (!No_Children) {
				for (int i = 1; i <= Children.length; i++) {
					String ChildID = "Childs_" + i, Child_AgeID = "ca" + i;
					if (elementVisible(driver, By.id(ChildID), 1)) {
						safeSelect(driver, By.id(ChildID), Children[i - 1]);
						safeSelect(driver, By.name(Child_AgeID), ChildAge[i - 1]);
					}
				}
			}
		}


	public void agencyHotel_DetailsPage(RemoteWebDriver driver, String BookingType) throws InterruptedException, Exception {
		if(common.value("NewDetailsPage").equalsIgnoreCase("true")) {
			loop: for(int i=0; i<=10;i++) {
				if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_Price"), 1)) {
					break loop;
				} else if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_LoaderDots"), 1)) {
					Thread.sleep(2000);
				}else if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 1)) {
					Reporter.log("Sorry, this hotel is unavailablePlease try different dates - Error is displayed");
					Assert.assertTrue(false);
				}else if(textPresent(driver, "Sorry, our system is acting up.", 1)){
					  Reporter.log("Sorry, our system is acting up. : message is displayed");
					  Assert.assertTrue(false);
				}else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)){
					  Reporter.log("Sorry, this hotel is unavailable. : message is displayed");
					  Assert.assertTrue(false);
				}else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)){
					  Reporter.log("Whatever you're looking for, isn't here : message is displayed");
					  Assert.assertTrue(false);
				}Thread.sleep(1000);
			}
			textPresent(driver, "", 5);
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));			
		} else {
			if(!(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 10) || elementPresent_Time(driver, By.xpath("//div[2]/strong"), 5))) {
				refreshPage(driver);
			}
			 for(int i=0; i<=10;i++){
				if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
					 		break;
					 }
				else if(textPresent(driver, "Sorry, our system is acting up.", 1)){
					  Reporter.log("Sorry, our system is acting up. : message is displayed");
					  Assert.assertTrue(false);
				  }
				  
				  else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)){
					  Reporter.log("Sorry, this hotel is unavailable. : message is displayed");
					  Assert.assertTrue(false);
				  }
				  else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)){
					  Reporter.log("Whatever you're looking for, isn't here : message is displayed");
					  Assert.assertTrue(false);
				  }
				  else if(!elementVisible(driver, By.cssSelector("div.roomRate > div.row > div.perRoomPrDisp > span.loader.dotDotDot"), 1)){
					 break;
				 }
				 
			 }
			 if(BookingType.equals("ProdpaymentLive")) {
				 textAssert(driver, "Free Cancellation", 10);
			 }
				   if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
					   Reporter.log("Hotel Rate is displayed in Details page");
				   }
				   else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)) {
						String HotelName = getText(driver, By.xpath("//div[2]/div/div/h1"));
						Reporter.log("Sorry, this hotel is unavailable - No rates/ inventory for hotel :"+HotelName);
						Assert.assertTrue(false);
				   } 
				   
				
				   if(elementVisible(driver, By.xpath("//div/div/div[2]/a"), 1)){
					   safeClick(driver, By.xpath("//div/div/div[2]/a"));
					   smartClick(driver, By.linkText("Book"));
				   }	
				   smartClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"));	
			}
		}
	
	public void agencyHotel_Itinerarypage(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementPresent_Time(driver, getObjectHotels("AgencyHotels_Itinerarypage_PolicyLink"), 10);
		if(textPresent(driver, "Sorry, our system is acting up.", 1)){
			Reporter.log("Sorry, our system is acting up. : Error message is displayed in Itinerary page");
			logURL(driver);
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, getObjectHotels("AgencyHotels_Itinerarypage_ContinueButton"), "Itinerary Continue button", 10);
		if (!elementVisible(driver, getObjectHotels("AgencyHotels_Itinerarypage_ContinueButton"), 5)) {
			Reporter.log("Itinerary page is not displayed");
			Assert.assertTrue(false);
		}
		String Iti=driver.getPageSource();

		Reporter.log("Itinerary ID="+Iti.split("var itineraryId =")[1].split(";")[0]);
	
	/*	if(elementPresent_Time(driver, getObjectHotels("AgencyHotels_Itinerarypage_Hotel_Details"), 1)) {
			String HotelDetails = getText(driver, getObjectHotels("AgencyHotels_Itinerarypage_Hotel_Details"));
			String HotelName = getText(driver, getObjectHotels("AgencyHotels_Itinerarypage_Hotel_Name"));
			Reporter.log("Hotel Name : +HotelName");
			Reporter.log("Hotel Details : "+HotelDetails);	
		}*/
		safeClick(driver, getObjectHotels("AgencyHotels_Itinerarypage_ContinueButton"));
	}
	
	public void agencyHotel_Itinerarypage_Add_OntheGo_Markup(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementPresent(driver, getObjectHotels("AgencyHotels_Itinerarypage_PolicyLink"));
		Thread.sleep(2000);
		agency_Hotel_Add_OntheGo_Markup(driver);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("AgencyHotels_Itinerarypage_ContinueButton"));
	}
	
	public void CTPhoneHotel_Itinerarypage_OntheGo_Discount(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementPresent(driver, getObjectHotels("AgencyHotels_Itinerarypage_PolicyLink"));
		Thread.sleep(2000);
		CTPhone_Hotel_OntheGo_Discount(driver);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("AgencyHotels_Itinerarypage_ContinueButton"));
	}

	public void agencyHotel_Travellerpage(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementVisible(driver, getObjectHotels("AgencyHotels_Travellerpage_Title"), 30);
		safeSelect(driver, getObjectHotels("AgencyHotels_Travellerpage_Title"), dataFile.value("Title"));
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_FirstName"), dataFile.value("First_Name_A1"));
		//safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_LastName"), dataFile.value("Last_Name_A1"));
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_LastName"), "Booking");
		//safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_LastName"), "Spl Request - Continental Meals");
		/*if(elementVisible(driver, By.cssSelector("li.highlight"), 2)) {
			smartClick(driver, By.cssSelector("li.highlight"));
		}*/
		Thread.sleep(5000);
		//smartClick(driver, By.cssSelector("li.highlight"));
		smartClick(driver, By.xpath("//ul[@id='autocompleteOptionsContainer']/li"));
		String URL = logURL(driver);
		smartClick(driver, getObjectHotels("AgencyHotels_Travellerpage_GST_CheckBox"));
		if(URL.contains("ctphone")) {
			safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_PhoneNumber"), "9738685480");
		}
		
		else 
			safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_PhoneNumber"), "1212121212");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_EmailID"), "automation@cleartrip.com");
		if(MakePaymentOnlyInProd) {
			safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_PhoneNumber"), "1212121212");
			safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_EmailID"), "automation@cleartrip.com");
		} 
		safeClick(driver, getObjectHotels("AgencyHotels_Travellerpage_ContinueButton"));
	}	
	
	public void agencyHotel_Travellerpage_GST(RemoteWebDriver driver, String GST_Type) throws InterruptedException, Exception {
		elementVisible(driver, getObjectHotels("AgencyHotels_Travellerpage_Title"), 30);
		safeSelect(driver, getObjectHotels("AgencyHotels_Travellerpage_Title"), dataFile.value("Title"));
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_FirstName"), dataFile.value("First_Name_A1"));
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_LastName"), "Booking");
		if(elementVisible(driver, By.cssSelector("li.highlight"), 2)) {
			smartClick(driver, By.cssSelector("li.highlight"));
		}
		Thread.sleep(5000);
		elementPresent_log(driver, getObjectHotels("AgencyHotels_Travellerpage_GST_CheckBox"), "GST CheckBox", 10);
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_GST_HolderName"), dataFile.value("HotelGSTHolderName"));
		//safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_GST_ID"), dataFile.value("HotelGSTNumber")); 29	AAAAA0000A1Z1
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_GST_ID"), "29AAAAA0000A1Z1"); 
		Thread.sleep(5000);
		//safeSelect(driver, getObjectHotels("AgencyHotels_Travellerpage_GST_State"), "Karnataka");
		String URL = logURL(driver);
		if(URL.contains("ctphone")) {
			safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_PhoneNumber"), "9738685480");
		} else 
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_PhoneNumber"), "1212121212");
		Thread.sleep(2000);
		textAssert(driver, getObjectHotels("AgencyHotels_Travellerpage_GST_StateInfo"), "29 - Karnataka");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_EmailID"), "automation@cleartrip.com");
		safeClick(driver, getObjectHotels("AgencyHotels_Travellerpage_ContinueButton"));
	}	

	public void agencyHotel_Travellerpage_GST_State(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementVisible(driver, getObjectHotels("AgencyHotels_Travellerpage_Title"), 30);
		safeSelect(driver, getObjectHotels("AgencyHotels_Travellerpage_Title"), dataFile.value("Title"));
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_FirstName"), dataFile.value("First_Name_A1"));
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_LastName"), "Booking");

		smartClick(driver, By.xpath("//ul[@id='autocompleteOptionsContainer']/li"));
		/*if(elementVisible(driver, By.cssSelector("li.highlight"), 2)) {
			smartClick(driver, By.cssSelector("li.highlight"));
		}*/
		elementPresent_log(driver, getObjectHotels(""), "GST State Dropdown", 10);
		safeSelect(driver, getObjectHotels(""), (""));
		String URL = logURL(driver);
		if(URL.contains("ctphone")) {
			safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_PhoneNumber"), "9738685480");
		} else 
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_PhoneNumber"), "1212121212");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_EmailID"), "automation@cleartrip.com");
		
		safeClick(driver, getObjectHotels("AgencyHotels_Travellerpage_ContinueButton"));
	}	
	

	public String validateXML(String TripID, String StartTag, String EndTag, String TagValue) throws IOException, URISyntaxException{
		if(MakePaymentOnlyInQA2){
			DefaultHttpClient client = new DefaultHttpClient();
			String XML_URL = null;
			if(common.value("host").contains("qa2")){
				
				XML_URL = "http://172.17.13.35:9080/trips/"+TripID; //old -> http://172.17.12.231:9080/trips/
				//XML_URL = "http://10.10.21.107:9080/trips/"+TripID;
			}else if(common.value("host").contains("hf")){
				XML_URL = "http://10.10.25.116:9080/trips/"+TripID;
			}
			
			Reporter.log("XML URL : "+XML_URL);
			
			HttpGet get = new HttpGet(new URI(XML_URL));
			
			HttpResponse response = client.execute(get);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer();
			String str="";
			while((str=br.readLine())!=null){
				sb.append(str);
			}
			String sb1=sb.toString();
			String [ ] getFOP = sb1.split(StartTag);
			String [ ] FOP = getFOP[1].split(EndTag);
			if(FOP[0].contains(TagValue)){
				       Reporter.log("TAG" +StartTag+FOP[0]+EndTag);
			        } else {
			        	Reporter.log("TAG" +StartTag+FOP[0]+EndTag);
			        	Reporter.log("TAG value " +TagValue+"TAG value  in XML "+FOP[0]);
				        Assert.assertTrue(false);
			        	}
			}
		return TagValue;	
	}
	
	public String agencyHotel_Paymentpage(RemoteWebDriver driver, String PaymentType, String Coupon, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception, InterruptedException {
		String TripID = null;
		if(Coupon.equalsIgnoreCase("COUPON"))
		{
			if(!ProductionUrl) {
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2B_Coupon"));
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_Button"));
			Thread.sleep(2000);
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 30);
			textAssert(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"), "Great, you are eligible to a cashback");
			Reporter.log("Great, you are eligible to a cashback message is displayed");
			}
		}
		
		TripID = agencyPayment(driver, PaymentType, Trip_Logger_Msg, Booking_Confirmation_Message);
		if(!(PaymentType.equalsIgnoreCase("NETBANKING") ||PaymentType.equalsIgnoreCase("CTPAY") )){
		safeClick(driver, getObjectHotels("AgencyHotels_ConfirmationPage_SignOut_Link"));
		elementVisible(driver, getObjectHotels("Agency_SignIN_EmailID"), 10);
		}
		return TripID;
	}

	public String agencyHotel_Paymentpage_NoSignOut(RemoteWebDriver driver, String PaymentType, String Coupon, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception, InterruptedException {
		String TripID = null;
		if(Coupon.equalsIgnoreCase("COUPON"))
		{
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelCom_Coupon"));
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_Button"));
			Thread.sleep(2000);
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 30);
			textAssert(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"), "Great, you are eligible to a cashback");
		}
		if(elementVisible(driver, By.id("processingFeeAmount"), 5)){
			HotelProcessingFee = getText(driver, By.id("processingFeeAmount"));
			HotelProcessingFee = HotelProcessingFee.replace("Rs. ", "");			
		}
		TripID = agencyPayment(driver, PaymentType, Trip_Logger_Msg, Booking_Confirmation_Message);
		return TripID;
	}

	public String agencyPayment(RemoteWebDriver driver, String PaymentType, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception, InterruptedException {
		String TripID =null;
		//elementPresent_Time(driver, By.xpath("//div[5]/h2"), 60);
		if(PaymentType.equalsIgnoreCase("DEPOSITACCOUNT")||PaymentType.isEmpty()){
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_DepositAccount_Tab"));
		}
		else if(PaymentType.equalsIgnoreCase("CREDITCARD")){
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"));
			if(!ProductionUrl) {
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Number"),  dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Exp_Month"), "5");
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));			
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Name"), dataFile.value("MasterCard_HolderName"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_FirstName"), "test");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_LastName"), "test");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Address"), "JP Nagar");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_City"), "Bangalore");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_State"), "Karnataka");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Pin"), "560076");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Country"), "India");
			Thread.sleep(2000);
			}
			}
		else if(PaymentType.equals("ProdpaymentLive")){
			System.out.println("=================================LIVE CARD details are Used =================================");
			
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Number"),  common.value("MasterCardNo"));
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Exp_Month"), "10");
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Exp_Year"), "2018");			
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_CVV"), "");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Name"), "Ramesh K");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_FirstName"), "Cleartrip");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_LastName"), "PVt Ltd");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Address"), "JP Nagar");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_City"), "Bangalore");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_State"), "Karnataka");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Pin"), "560076");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Country"), "India");
			Thread.sleep(2000);
			}
		else if(PaymentType.equalsIgnoreCase("DEBITCARD")){
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Tab"));
			if(!ProductionUrl) {
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Number"),  dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Exp_Month"), "5");
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_BillName"), dataFile.value("MasterCard_HolderName"));
			Thread.sleep(2000);				
			}
		}
		else if(PaymentType.equalsIgnoreCase("NETBANKING")){
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBanking_Tab"));
			if(NBSite.equals("BOI")) {
				safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Bank of India");	
			}
			else if(NBSite.equals("SBI")) {
				safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"), "State Bank of India");
			}
			else if(NBSite.equals("CITI")) {
				safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Citibank");
			}
			Thread.sleep(2000);
		}
		else if(PaymentType.equalsIgnoreCase("HOLD")){
			elementVisible(driver, getObjectHotels("AirCom_BookStep4_MakePayment_Button"), 30);
			elementPresent(driver, By.xpath("//div[6]/div[4]/div[3]"));			
		}
		else if(PaymentType.equalsIgnoreCase("IVR")){
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_IVR_Tab"));
			String Total_Price = getText(driver, getObjectHotels("AgencyHotels_PaymentPage_Total_Price"));
			Total_Price = Total_Price.replace("Rs. ", "");
			if(Total_Price.contains(",")){
				Total_Price = Total_Price.replace(",", "");
			}
		//	int TotalPrice_Int = Integer.parseInt(Total_Price);
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_IVR_Trx_RefNumber"), "707070");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_IVR_Amount"), Total_Price);
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_IVR_Card_Number"), "5123456");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_IVR_GW_Trx_ID"), "505050");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_IVR_GW_Text"), "Cleartrip GW");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_IVR_Credential"), "Cleartrip Cred");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_IVR_Response_Msg"), "Cleartrip Msg");
			Thread.sleep(2000);
		}
		else if(PaymentType.equalsIgnoreCase("TECHPROCESS")){
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_TechProcess_Tab"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_TechProcess_Trx_Number"), "5123456");
			Thread.sleep(2000);
				
		}
		else if(PaymentType.equalsIgnoreCase("ITZ")){
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_CashCard_Tab"), 20);
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_CashCard_Tab"));			
		}
		else if(PaymentType.equalsIgnoreCase("CTPAY")){
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_CTPay_Tab"), 5);
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_CTPay_Tab"));
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_CTPay_CustomerID"), 30);
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CTPay_CustomerID"), "cleartriptester@cleartrip.com");
			Reporter.log("End of case - CTPAY cannot be verified with TripID");
			
		}
		else if(PaymentType.equalsIgnoreCase("NETBANKINGPROD")){
			if(!ProductionUrl) {
				NBSite="BOI";	
			}
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBanking_Tab"));
			if(NBSite.equals("BOI")) {
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Bank of India");
			}
			else if(NBSite.equals("SBI")) {
				safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"), "State Bank of India");
			}
			else if(NBSite.equals("CITI")) {
				safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Citibank");
			}
			Thread.sleep(2000);	
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_BookButton"));
			if(NBSite.equals("BOI")) {
				for (int i = 0; i < 15; i++) {
				
				 if(textPresent(driver, "This site can’t be reached", 1)){
						Reporter.log("This site can’t be reached message is displayed refreshing the page");
						refreshPage(driver);
					}
				if(elementPresent_Time(driver, By.linkText("Return to Biller Site"), 1)){
					elementPresent(driver, By.linkText("Return to Biller Site"));				
					Reporter.log("BOI Netbanking Site is displayed");
					break;
					} 		
				else if(textPresent(driver, "This site can’t be reached", 1)){
						Reporter.log("This site can’t be reached message is displayed");
						Assert.assertTrue(false);
					}
				else {
					if( i == 15) {
					Reporter.log("Page has not redirected to BOI Netbanking Site");
					}
				}
				}
						
				safeClick(driver, By.linkText("Return to Biller Site")); //BOI				
				elementVisible(driver, By.linkText("Cancel"), 10);
				textPresent(driver, "Cleartrip Travel Services Pvt Ltd", 1);
				smartClick(driver, By.linkText("Cancel"));

				if(!textPresent(driver, "Oops, your payment didn’t work", 5)) {
					Reporter.log("Oops, your payment didn’t work message is not displayed");
					Assert.assertTrue(false);
				}
				if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
					Reporter.log("Sorry, our system is acting up. message is displayed");
					Assert.assertTrue(false);
				}
				if(!elementPresent_Time(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
					Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
					Assert.assertTrue(false);
				}
				else Reporter.log("Page has redirected to back to Cleartrip from BOI Netbanking Site");
			}
			
			else if(NBSite.equals("SBI")) {
			
			if(elementPresent_Time(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"), 70)){
			Thread.sleep(2000);
			elementPresent(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"));			
			Reporter.log("Netbanking Site is displayed");
			}
			//--------------------------------------Not Working in Prod------------------------------------//
		
		if(elementPresent_Time(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"), 100)){
				Thread.sleep(2000);
				elementPresent(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"));				
				Reporter.log("SBI Netbanking Site is displayed");
				} 
			else {
				Reporter.log("Page has not redirected to SBI Netbanking Site");
				Assert.assertTrue(false);
			}
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"));
				}
			
			else if(NBSite.equals("CITI")) {
				

				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CityBank_Site_Logo"), 50)){
					Thread.sleep(2000);			
					Reporter.log("NB site page is displayed");
				}
					elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CityBank_PaymentText"), 5);
					elementPresent(driver, getObjectHotels("HotelCom_BookStep4_CityBank_Cancel_Link"));
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_CityBank_Cancel_Link"));
				
				
				
				if(!textPresent(driver, "Oops, your payment didn’t work", 20)) {
					Reporter.log("Oops, your payment didn’t work message is not displayed");
					Assert.assertTrue(false);
				}
				if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
					Reporter.log("Sorry, our system is acting up. message is displayed");
					Assert.assertTrue(false);
				}
				if(!elementPresent_Time(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
					Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
					Assert.assertTrue(false);
				}
				else Reporter.log("Page has redirected to back to Cleartrip from CITI Netbanking Site");
			}
			if(!elementPresent_Time(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"), 20)){
				Reporter.log("Page has not redirected to back to Cleartrip from SBI Netbanking Site");
				Assert.assertTrue(false);
			}
		
		}
		
		else if(PaymentType.equalsIgnoreCase("CTPAY")){
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_CTPay_Tab"));
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_CTPay_CustomerID"), 30);
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CTPay_CustomerID"), "automation@cleartrip.com");
		}
			
		if(elementPresent_Time(driver, getObjectHotels("AirCorpCom_TravellerPage_ItineraryBlock"), 1)) {
			String Itinerary = getText(driver, getObjectHotels("AirCorpCom_TravellerPage_ItineraryBlock"));
			Reporter.log("Itinerary : "+Itinerary);
		}	
		
//================================================Make Payment=========================================//	
	if(MakePaymentOnlyInQA2){
		if(PaymentType.equalsIgnoreCase("NETBANKING")) {
			Reporter.log("Payment Button is Clicked");
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_BookButton"));
			if(NBSite.equals("BOI")) {
			if(elementPresent_Time(driver, By.linkText("Return to Biller Site"), 100)){
				Thread.sleep(2000);
				elementPresent(driver, By.linkText("Return to Biller Site"));				
				Reporter.log("BOI Netbanking Site is displayed");
				} 
			else {
				Reporter.log("Page has not redirected to BOI Netbanking Site");
				Assert.assertTrue(false);
			}
			
			safeClick(driver, By.linkText("Return to Biller Site")); //BOI
			if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Sorry, our system is acting up. message is displayed in NBRetry Page");
				Assert.assertTrue(false);
			}
			if(!elementPresent_Time(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"), 30)){
				Reporter.log("Page has not redirected back to Cleartrip from Netbanking Site");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "Oops, your payment didn’t work", 2)) {
				Reporter.log("Oops, your payment didn’t work : error is not displayed");
				Assert.assertTrue(false);
			}
			
			
			}
			else if(NBSite.equals("SBI")) {
				if(elementPresent_Time(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"), 100)){
						Reporter.log("SBI Netbanking Site is displayed");
					} 
				else {
					Reporter.log("Page has not redirected to SBI Netbanking Site");
					Assert.assertTrue(false);
				}
				
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"));

				}
			if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Sorry, our system is acting up. message is displayed in NBRetry Page");
				Assert.assertTrue(false);
			}
			if(!elementPresent_Time(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"), 30)){
				Reporter.log("Page has not redirected back to Cleartrip from Netbanking Site");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "Oops, your payment didn’t work", 2)) {
				Reporter.log("Oops, your payment didn’t work : error is not displayed");
				Assert.assertTrue(false);
			}
		}
		
		else if(PaymentType.equalsIgnoreCase("CTPAY")){
			Reporter.log("Payment Button is Clicked");
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_BookButton"));	 
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_CTPay_TripID"), 30);
			elementPresent(driver, getObjectHotels("AgencyHotels_PaymentPage_CTPay_TripID"));
			TripID = getText(driver, getObjectHotels("AgencyHotels_PaymentPage_CTPay_TripID"));
			}
		else if(PaymentType.equalsIgnoreCase("ITZ")){
			Reporter.log("Payment Button is Clicked");
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_BookButton"));	 

			elementVisible(driver,  By.xpath("//img"), 60);
			textPresent(driver, "Please select your mode of payment", 20);
			textAssert(driver, "Please select your mode of payment", 2);
			// Code to be added
			}
		
		
		else if(!(PaymentType.equalsIgnoreCase("NETBANKING") || PaymentType.equalsIgnoreCase("NETBANKINGPROD"))) {

			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_BookButton"));	 
			hotelCom_PaymentPage_Authentication(driver, "");
			for (int i = 0; i < 10; i++) {
				if(elementVisible(driver, getObjectHotels("AgencyHotels_ConfirmationPage_TripID"), 2)){
					TripID = getText(driver, getObjectHotels("AgencyHotels_ConfirmationPage_TripID"));					
					break;
				}
				else if(textPresent(driver, "Proxy Error", 1)){
					Reporter.log("Proxy Error displayed, TripID not Genrated", true);
					Assert.assertTrue(false);
				}
				else if(textPresent(driver, "Forbidden", 1)){
					Reporter.log("Forbidden message is displayed, TripID not Genrated", true);
					Assert.assertTrue(false);
				}				
				else if(textPresent(driver, "Oops, your booking didn’t go through", 1)){
					Reporter.log("Oops, your booking didn’t go through :  message is displayed, TripID not Genrated",true);
					Assert.assertTrue(false);
				}
				else if(textPresent(driver, "Sorry, our system is acting up", 1)){
					Reporter.log("Sorry, our system is acting up :  message is displayed, TripID not Genrated",true);
					Assert.assertTrue(false);
				}
				
				else if(textPresent(driver, "Oops! AgentBox system is behaving badly", 1)){
					Reporter.log("Oops! AgentBox system is behaving badly :  message is displayed, TripID not Genrated",true);
					Assert.assertTrue(false);
				}
				else if(elementVisible(driver, By.xpath("(//a[contains(text(),'Modify search')])[2]"), 1)){
					Reporter.log("Page has Redirected to SRP",true);
					Assert.assertTrue(false);
				}		
				
				/*else if(elementVisible(driver, getObjectHotels("AirCom_HomePage_From_TextBox"), 1)){
					Reporter.log("Page has Redirected to Homepage");
					Assert.assertTrue(false);
				}*/
				
			}
			Reporter.log(Trip_Logger_Msg + TripID,true );
			logger.info(Trip_Logger_Msg + TripID );
			logURL(driver);

			if(TripID.equals(null)){
				Reporter.log("TripID is null", true);
				Assert.assertTrue(false);
			}
			/*if(!elementVisible(driver, getObjectHotels("AgencyHotels_ConfirmationPage_TripID"), 10)){
				Reporter.log("Confirmation page is not displayed");
				Assert.assertTrue(false);
			}
			TripID = getText(driver, getObjectHotels("AgencyHotels_ConfirmationPage_TripID"));
			Reporter.log(Trip_Logger_Msg + TripID );
			logger.info(Trip_Logger_Msg + TripID );*/
			}		
	}
	else if(MakePaymentOnlyInProd) {
		System.out.println("=============LIVE bookings are done ===============");
		Reporter.log("Payment Button is Clicked");
		safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_BookButton"));
		hotelCom_PaymentPage_Production_Card_Authentication(driver, "Agency");	
		
		for (int i = 0; i < 10; i++) {
			if(elementVisible(driver, getObjectHotels("AgencyHotels_ConfirmationPage_TripID"), 2)){
				TripID = getText(driver, getObjectHotels("AgencyHotels_ConfirmationPage_TripID"));					
				break;
			}
			else if(textPresent(driver, "Proxy Error", 1)){
				Reporter.log("Proxy Error displayed, TripID not Genrated", true);
				Assert.assertTrue(false);
			}
			else if(textPresent(driver, "Forbidden", 1)){
				Reporter.log("Forbidden message is displayed, TripID not Genrated", true);
				Assert.assertTrue(false);
			}				
			else if(textPresent(driver, "Oops, your booking didn’t go through", 1)){
				Reporter.log("Oops, your booking didn’t go through :  message is displayed, TripID not Genrated",true);
				Assert.assertTrue(false);
			}
			else if(textPresent(driver, "Sorry, our system is acting up", 1)){
				Reporter.log("Sorry, our system is acting up :  message is displayed, TripID not Genrated",true);
				Assert.assertTrue(false);
			}
			
			else if(textPresent(driver, "Oops! AgentBox system is behaving badly", 1)){
				Reporter.log("Oops! AgentBox system is behaving badly :  message is displayed, TripID not Genrated",true);
				Assert.assertTrue(false);
			}
			else if(elementVisible(driver, By.xpath("(//a[contains(text(),'Modify search')])[2]"), 1)){
				Reporter.log("Page has Redirected to SRP",true);
				Assert.assertTrue(false);
			}		
		}
		Reporter.log(Trip_Logger_Msg + TripID,true );
		logger.info(Trip_Logger_Msg + TripID );
		logURL(driver);

		if(TripID.equals(null)){
			Reporter.log("TripID is null", true);
			Assert.assertTrue(false);
		}
		
	}
	
	return TripID;
	}
	
	public void agencyHotel_Acc_Cancellation(RemoteWebDriver driver, String TripID, Map<String, String> agencySRPData) throws Exception {
		  elementPresent_Time(driver, getObjectHotels("Air_Agency_Confirmation_Trips_Link"), 10);
		  safeClick(driver, getObjectHotels("Air_Agency_Confirmation_Trips_Link"));
		  refreshPage(driver);
		  for(int i = 0 ; i<2; i++){
			  if(!elementPresent_Time(driver, getObjectHotels("Air_Agency_Trips_Page_Header"), 20)){
				  refreshPage(driver);
			  }
			  if(!elementPresent_Time(driver, getObjectHotels("Air_Agency_Trips_Page_Search_TripID"), 5)){
			  refreshPage(driver);
		  	 } else break;
		  }
		  elementPresent_Time(driver, getObjectHotels("Air_Agency_Trips_Page_Search_TripID"), 30);
		  safeType(driver, getObjectHotels("Air_Agency_Trips_Page_Search_TripID"), TripID);
		  safeClick(driver, getObjectHotels("Air_Agency_Trips_Page_Search_TripID_Submit"));
		  safeClick(driver, getObjectHotels("Air_Agency_Trips_Page_Trip_Link"));
		  elementVisible(driver, By.linkText("Pricing & payment details"), 20);
		  safeClick(driver, By.linkText("Pricing & payment details"));
		  if(!elementVisible(driver, By.cssSelector("dd.total"), 10)){
			  safeClick(driver, By.linkText("Pricing & payment details"));
		  }
		  Map<String, String> agencyAccTripData = new HashMap<String, String >();
		  agencyAccTripData.put("Name", getText(driver, By.xpath("//td/div/h2")));
		  agencyAccTripData.put("RoomType", getText(driver, By.xpath("//table[3]/tbody/tr[2]/td")));
		  agencyAccTripData.put("Price", getText(driver, By.cssSelector("dd.total")));
		  
	/*	  String SRPPrice = agencySRPData.get("Price");
		  int SRPPriceInt = Integer.parseInt(SRPPrice);
		 String AcctPrice = getText(driver, By.cssSelector("dd.total"));
		  AcctPrice = AcctPrice.replace("Rs. ", "");
		  int AcctPriceInt = Integer.parseInt(AcctPrice);*/
		  //int HotelProcessingFeeInt = Integer.parseInt(HotelProcessingFee);
		  
		  /*int HotelProcessingFeeInt = hotelCom_ConvertPrice_To_Int(driver, By.cssSelector("dd.total"));
		  int TotalPrice = SRPPriceInt+HotelProcessingFeeInt;
		  */
		  /*	ut.println("SRPPriceInt : "+SRPPriceInt);
		  System.out.println("HotelProcessingFeeInt : "+HotelProcessingFeeInt);
		  System.out.println("AcctPriceInt : "+AcctPriceInt);*/
		/*  if(!(TotalPrice==AcctPriceInt)){
			  Reporter.log("Hotel Name doesnt Match 'SRP Price + Processing fee ' : "+TotalPrice+" & 'Accnt Trip Page Price ' : "+AcctPriceInt);
			 // Assert.assertTrue(false);
		  }*/
		  if(!agencySRPData.get("Name").equals(agencyAccTripData.get("Name"))){
			  Reporter.log("Hotel Name doesnt Match 'SRP Name ' : "+agencySRPData.get("Name")+" & 'Accnt Trip Page Name ' : "+agencyAccTripData.get("Name")  );
			  //Assert.assertTrue(false);
		  }
		  if(!agencySRPData.get("RoomType").equals(agencyAccTripData.get("RoomType"))){
			  Reporter.log("Hotel Name doesnt Match 'SRP RoomType ' : "+agencySRPData.get("RoomType")+" & 'Accnt Trip Page RoomType ' : "+agencyAccTripData.get("RoomType")  );
			  //Assert.assertTrue(false);
		  }
		
		  safeClick(driver, getObjectHotels("Air_Agency_Trips_Cancel_One"));
		  safeClick(driver, getObjectHotels("Air_Agency_Trips_Cancel_Hotel_Two"));
		  elementPresent_Time(driver, By.xpath("//div[2]/div/h1"), 30);
		  textAssert(driver, By.xpath("//div[2]/div/h1"), "Cancellation confirmation");
	}
	
	public void agencyHotel_Acc_Cancellation_Production(RemoteWebDriver driver, String TripID, String PaymentType) throws Exception {
		if(MakePaymentOnlyInProd) {
			if(PaymentType.equals("ProdpaymentLive")) {
		  elementPresent_Time(driver, getObjectHotels("Air_Agency_Confirmation_Trips_Link"), 10);
		  safeClick(driver, getObjectHotels("Air_Agency_Confirmation_Trips_Link"));
		  refreshPage(driver);
		  for(int i = 0 ; i<2; i++){
			  if(!elementPresent_Time(driver, getObjectHotels("Air_Agency_Trips_Page_Header"), 20)){
				  refreshPage(driver);
			  }
			  if(!elementPresent_Time(driver, getObjectHotels("Air_Agency_Trips_Page_Search_TripID"), 5)){
			  refreshPage(driver);
		  	 } else break;
		  }
		  elementPresent_Time(driver, getObjectHotels("Air_Agency_Trips_Page_Search_TripID"), 30);
		  safeType(driver, getObjectHotels("Air_Agency_Trips_Page_Search_TripID"), TripID);
		  safeClick(driver, getObjectHotels("Air_Agency_Trips_Page_Search_TripID_Submit"));
		  safeClick(driver, getObjectHotels("Air_Agency_Trips_Page_Trip_Link"));
		  elementVisible(driver, By.linkText("Pricing & payment details"), 20);
		  safeClick(driver, By.linkText("Pricing & payment details"));
		  if(!elementVisible(driver, By.cssSelector("dd.total"), 10)){
			  safeClick(driver, By.linkText("Pricing & payment details"));
		  }		
		  safeClick(driver, getObjectHotels("Air_Agency_Trips_Cancel_One"));
		  safeClick(driver, getObjectHotels("Air_Agency_Trips_Cancel_Hotel_Two"));
		  elementPresent_Time(driver, By.xpath("//div[2]/div/h1"), 30);
		  textAssert(driver, By.xpath("//div[2]/div/h1"), "Cancellation confirmation");
		} else {
			Reporter.log("Payment in production flag is not true");
			Assert.assertTrue(false);
		}
		}else {
			Reporter.log("Payment in production flag is not true");
			Assert.assertTrue(false);
		}
	}
	
	public void agencyHotel_HQ_Cancellation(RemoteWebDriver driver, String TripID, Map<String, String> agencySRPData) throws Exception {
		if (MakePaymentOnlyInQA2){
			driver.manage().deleteAllCookies();
			hotelCom_Open_TripID_HQ(driver, TripID);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			if(!elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Link"), 60)) {
				refreshPage(driver);
			}
			elementVisible(driver, By.xpath("//h1"), 10);
			Map<String, String> agencyHQTripData = new HashMap<String, String >();
			agencyHQTripData.put("Name", getText(driver, By.xpath("//h1")));
			agencyHQTripData.put("RoomType", getText(driver, By.xpath("//tr[3]/td")));
			agencyHQTripData.put("Price", getText(driver, By.cssSelector("dd.total > strong")));
			
/*			  String SRPPrice = agencySRPData.get("Price");
			  int SRPPriceInt = Integer.parseInt(SRPPrice);
			  String AcctPrice = getText(driver, By.cssSelector("dd.total > strong"));
			  AcctPrice = AcctPrice.replace("Rs. ", "");
			  int AcctPriceInt = Integer.parseInt(AcctPrice);
			  int HotelProcessingFeeInt = Integer.parseInt(HotelProcessingFee);
			  int TotalPrice = SRPPriceInt+HotelProcessingFeeInt;*/

		/*	  if(!(TotalPrice==AcctPriceInt)){
				  Reporter.log("Hotel Name doesnt Match 'SRP Price + Processing fee ' : "+TotalPrice+" & 'HQ Trip Page Price ' : "+AcctPriceInt);
				  Assert.assertTrue(false);
			  }*/
			  if(!agencyHQTripData.get("Name").contains(agencySRPData.get("Name"))){
				  Reporter.log("Hotel Name doesn't Match 'SRP Name ' : "+agencySRPData.get("Name")+" & 'HQ Trip Page Name ' : "+agencyHQTripData.get("Name")  );
				//  System.out.println("Hotel Name doesn't Match 'SRP Name ' : "+agencySRPData.get("Name")+" & 'HQ Trip Page Name ' : "+agencyHQTripData.get("Name")  );
					//  Assert.assertTrue(false);
			  }
			  if(!agencySRPData.get("RoomType").equals(agencyHQTripData.get("RoomType"))){
				  Reporter.log("Hotel Name doesn't Match 'SRP RoomType ' : "+agencySRPData.get("RoomType")+" & 'HQ Trip Page RoomType ' : "+agencyHQTripData.get("RoomType")  );
			//	  Assert.assertTrue(false);
			  }			
			safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Link"));
			safeType(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), "Test Booking");
			safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Button"));
		    elementNotPresent_Time(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), 20);
			elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Status"), 60);
			String Cancel_Status = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Status"));
			if(!Cancel_Status.contains("Cancelled")){
				Reporter.log("Trip Status after cancellation is : "+Cancel_Status);
				Assert.assertTrue(false);
				}	
		}
	}
	
	public void hotelCom_Open_TripID_HQ(RemoteWebDriver driver, String TripID) throws Exception {
		String URL = logURL(driver);
		
		if(URL.contains("com")){
			driver.get(baseUrl+"/hq/trips/"+TripID);
		}
		else if(URL.contains("ae")){
			driver.get(baseUrl_AE+"/hq/trips/"+TripID);
		}
	}
	
	public void agencyContactDetails(RemoteWebDriver driver) throws Exception {
		safeSelect(driver, getObjectHotels("AgencyHotels_Travellerpage_Contact_Title"), "Mr.");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_Contact_FirstName"), "Automation");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_Contact_LastName"), "Automation");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_Contact_EmailID"), "automation@cleartrip.com");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_Contact_Address"), "JP Nagar");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_Contact_City"), "Bangalore");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_Contact_State"), "Karnataka");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_Contact_Pin"), "560076");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_Contact_Contry"), "India");
		safeType(driver, getObjectHotels("AgencyHotels_Travellerpage_Contact_Phone"), "1212121212");
	}
	public void agencyHotel_Accounts_TripTool(RemoteWebDriver driver) throws Exception{
		if(textPresent(driver, "Itinerary", 20)) {
			  Reporter.log("Hotels Trip details page is loaded");
			  safeClick(driver, By.id("tab_2"));
			  elementPresent(driver, By.xpath("//*[@id='add_note']/fieldset/legend"));
			
			  safeClick(driver, By.xpath("//*[@id='ContentFrame']/div[6]/div/div[1]/ul/li[1]/a"));
			  driver.navigate().back();
			  Thread.sleep(3000);
			
			  safeClick(driver, By.xpath("//*[@id='ContentFrame']/div[6]/div/div[1]/ul/li[2]/a"));
			  safeType(driver, By.id("email"), dataFile.value("HotelCom_SendMail"));
			  safeClick(driver, By.id("SendTicketButton")); 
			  Thread.sleep(3000);
			
			  safeClick(driver, By.xpath("//*[@id='ContentFrame']/div[6]/div/div[1]/ul/li[3]/a"));
			  safeType(driver, By.id("mobile_number"), dataFile.value("HotelCom_SendSms"));
			  safeClick(driver, By.id("SendSmsButton"));
			  Thread.sleep(3000);
			
			  safeClick(driver, By.xpath(".//*[@id='ContentFrame']/div[6]/div/div[1]/ul/li[4]/a"));
			  safeClick(driver, By.xpath("//*[@id='ContentFrame']/div[6]/div/div[1]/ul/li[5]/a"));
			  safeClick(driver, By.xpath("//*[@id='ContentFrame']/div[6]/div/div[1]/ul/li[6]/a"));
			  Thread.sleep(3000);
		  }
		  else{
			  Reporter.log("Hotels Trip details page is not loaded");
			  Assert.assertTrue(false);
		  }	
	}
	
	
}