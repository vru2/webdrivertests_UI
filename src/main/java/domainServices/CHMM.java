// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Sep, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package domainServices;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

	public class CHMM extends IndiaHotels {
	private String  baseUrl_Com;
	public String CHMMHotelCity = dataFile.value("HotelCHMMCity");
	public String CHMMHotelName = dataFile.value("HotelCHMMName");
	public String CHMMHotelRoomType = dataFile.value("HotelCHMMRoomType");
	public String CHMMHotelRateTypeSell = dataFile.value("HotelCHMMRateTypeSell");
	public String CHMMHotelRateTypeNet = dataFile.value("HotelCHMMRateTypeNet");
	

	public String CHMMHotelCitySellNet = dataFile.value("HotelCHMMCitySellNet");
	public String CHMMHotelNameNet = dataFile.value("HotelCHMMNameNetRate");
	public String CHMMHotelRoomTypeNet = dataFile.value("HotelCHMMRoomTypeNetRate");
	public String CHMMHotelNameSell = dataFile.value("HotelCHMMNameSellRate");
	public String CHMMHotelRoomTypeSell = dataFile.value("HotelCHMMRoomTypeSellRate");

	
	public void getCHMM_URL(RemoteWebDriver driver, String Domain) {
		String CHMM_URL = CHMM_URL(driver, Domain);
		driver.get(CHMM_URL);	
	}
	
	public String CHMM_URL (RemoteWebDriver driver, String Domain) {
		//driver.manage().deleteAllCookies();
		String CHMM_URL = null;
		if(common.value("host").equals("qa2")){
		if(Domain.equalsIgnoreCase("com")) {
			CHMM_URL = dataFile.value("CHMM_Com_QA2");
			}
		else if(Domain.equalsIgnoreCase("ae")){
			CHMM_URL = dataFile.value("CHMM_AE_QA2");
			}
		}else if(common.value("host").equalsIgnoreCase("hf")){
			if(Domain.equalsIgnoreCase("com")) {
				CHMM_URL = dataFile.value("CHMM_Com_HF");
				}
			else if(Domain.equalsIgnoreCase("ae")){
				CHMM_URL = dataFile.value("CHMM_AE_HF");
				}
		}else if(common.value("host").equalsIgnoreCase("poc")){
			if(Domain.equalsIgnoreCase("com")) {
				CHMM_URL = dataFile.value("CHMM_Com_POC");
				}
			else if(Domain.equalsIgnoreCase("ae")){
				CHMM_URL = dataFile.value("CHMM_AE_POC");
				}
		}
		return CHMM_URL;
		}
	
	public void CHMM_SignIN (RemoteWebDriver driver, String LoginType) throws Exception {
		driver.manage().deleteAllCookies();
		refreshPage(driver);
		if(LoginType.isEmpty()){
			/*elementVisible(driver, getObjectHotels("CHMM_SignIN_UserName"), 20);
		 	safeType(driver, getObjectHotels("CHMM_SignIN_UserName"),dataFile.value("HotelEmailID") );
		 	safeType(driver, getObjectHotels("CHMM_SignIN_Password"), dataFile.value("HotelPassword"));
		 	safeClick(driver, getObjectHotels("CHMM_SignIN_Button"));
		 	if(!elementVisible(driver, getObjectHotels("CHMM_HotelSearch"), 30)){
		 		Reporter.log("Signin not wrking");
		 		Assert.assertTrue(false);
		 	}*/
			hotelCom_AddCookie(driver);
			Thread.sleep(5000);
			driver.get(CHMM_URL(driver, "com"));
		}
		else if(LoginType.equalsIgnoreCase("Hotelier")) {
			elementVisible(driver, getObjectHotels("CHMM_SignIN_UserName"), 20);
		 	safeType(driver, getObjectHotels("CHMM_SignIN_UserName"), "getkirank@gmail.com" );
		 	safeType(driver, getObjectHotels("CHMM_SignIN_Password"), "cleartrip");
		 	safeClick(driver, getObjectHotels("CHMM_SignIN_Button"));
			if(!elementVisible(driver, getObjectHotels("CHMM_HotelSearch"), 30)){
		 		Reporter.log("Signin not wrking");
		 		Assert.assertTrue(false);
		 	}
			}
	}
	
	public void CHMM_AddHotel (RemoteWebDriver driver) throws Exception {		
		elementVisible(driver, getObjectHotels("CHMM_Add_New_Hotel_Button"), 20);
	 	safeClick(driver, getObjectHotels("CHMM_Add_New_Hotel_Button"));
	 	textPresent(driver, "Add a hotel to your account", 10);
	 	safeType(driver, getObjectHotels("CHMM_HotelSearch"), "New Hotel");		 	
	 	safeType(driver, getObjectHotels("CHMM_Add_Hotel_City"), "Bangalore");
	 	safeType(driver, getObjectHotels("CHMM_Add_Hotel_Accnt_Mngr_Name"), "Rajeev");
	 	safeType(driver, getObjectHotels("CHMM_Add_Hotel_Contact_Person"), "Kiran");
	 	safeType(driver, getObjectHotels("CHMM_Add_Hotel_Contact_Phone"), "9986696785");
	 	safeType(driver, getObjectHotels("CHMM_Add_Hotel_Person_Signing_Contract"), "Rajeev");
	 	safeSelect(driver, getObjectHotels("CHMM_Add_Hotel_Payment_Method"), "Net Banking");
	 	safeType(driver, getObjectHotels("CHMM_Add_Hotel_Bank_Name"), "Hdfc");
	 	safeType(driver, getObjectHotels("CHMM_Add_Hotel_Branch"), "JP Nagar");
	 	safeType(driver, getObjectHotels("CHMM_Add_Hotel_Accnt_Holder"), "Rajeev");
	 	safeType(driver, getObjectHotels("CHMM_Add_Hotel_Accnt_No"), "1234");
	 	//safeClick(driver, getObjectHotels("CHMM_Add_Hotel_Button"));
	}
	
	public void CHMM_Search_Hotel(RemoteWebDriver driver) throws Exception {		
		elementVisible(driver, getObjectHotels("CHMM_Add_New_Hotel_Button"), 60);
	}
	
	public void CHMM_Search_Merchant(RemoteWebDriver driver) throws Exception {		
		elementVisible(driver, getObjectHotels("CHMM_Add_New_Hotel_Button"), 60);
	}
	
	public void CHMM_Add_Rate(RemoteWebDriver driver, String HotelName, String RoomType, String RateType) throws Exception {
		elementVisible(driver, getObjectHotels("CHMM_Rates_Tab_Link"), 30);
		Thread.sleep(5000);
		safeClick(driver, getObjectHotels("CHMM_Rates_Tab_Link"));
		safeClick(driver, getObjectHotels("CHMM_Rates_New_Room_Rate_Button"));
		textPresent(driver, "Add a room rate", 5);
		//String Datevalue = getDateTime(1, "ddmmHH:mm:ss");
		String Datevalue = getDateTime(1, "ddmmss");
		safeType(driver, getObjectHotels("CHMM_Rates_Name"), "Automation Test "+Datevalue);
		safeSelect(driver, getObjectHotels("CHMM_Rates_AllowMarkup"), RateType+" Rate");
		safeType(driver, getObjectHotels("CHMM_Rates_Margin_Percent"), "1");
		elementVisible(driver, getObjectHotels("CHMM_HotelSearch"), 60);
		safeAutocomplete_CHMM(driver, getObjectHotels("CHMM_HotelSearch"), getObjectHotels("CHMM_Rates_Hotel_Search_Ajax"), HotelName);;
		safeSelect(driver, getObjectHotels("CHMM_Rates_Room_Type"), RoomType);
		 safeClick(driver, getObjectHotels("CHMM_Rates_Validity_Start_Calendar"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Validity_Start_Calendar_Date"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Validity_Ending_Calendar"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Validity_Ending_Calendar_Date"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Update_Inventory_Button"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Update_Inventory_Start_Calendar"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Update_Inventory_Start_Calendar_Date"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Update_Inventory_Ending_Calendar"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Update_Inventory_Ending_Calendar_Date"));
		safeType(driver, getObjectHotels("CHMM_Rates_Room_Inventory"), "10");		
		safeClick(driver, getObjectHotels("CHMM_Rates_Save_Room_Rate"));
		elementVisible(driver, getObjectHotels("CHMM_Rates_Tax_Type_All_Occupancies"),60);
	//	safeSelect(driver, By.id("hotel_cur_dropdown"), "AED AED - UAE Dirham");
		safeType(driver, getObjectHotels("CHMM_Rates_Tax_Type_All_Occupancies"), "0.1");
		if(RateType.equals("Sell")){
		safeType(driver, getObjectHotels("CHMM_Rates_Single_Sell_Rate"), "100");
		} else {
			safeType(driver, getObjectHotels("CHMM_Rates_Single_Net_Rate"), "100");
		}			
		safeClick(driver, By.linkText("Cancellation charges"));
		safeClick(driver, By.id("nonRefundCheck"));
		Thread.sleep(5000);
		safeClick(driver, getObjectHotels("CHMM_Rates_Save_Room_Rate"));
		Thread.sleep(10000);
		if(!elementPresent_Time(driver, getObjectHotels("CHMM_Rates_Added_Without_Error"), 30)) {
			Reporter.log("Chmm Rates added confirmed message is not displayed");
			Assert.assertTrue(false);
		}
		String RateAdded = getText(driver, getObjectHotels("CHMM_Rates_Added_Without_Error"));
		Boolean RateisAdded = RateAdded.contains("rate has been added for ");
		if(!RateisAdded){
			Reporter.log("Rate is not added");
			Assert.assertTrue(false);
		}
	}
	
	public void CHMM_RackRate_AddRate(RemoteWebDriver driver,String HotelName,String RoomType,String RateType,String HotelCountry,int gstPercent) throws Exception
	{
		
		elementVisible(driver, getObjectHotels("CHMM_Rates_Tab_Link"), 30);
		Thread.sleep(5000);
		safeClick(driver, getObjectHotels("CHMM_Rates_Tab_Link"));
		safeClick(driver, getObjectHotels("CHMM_Rates_New_Room_Rate_Button"));
		textPresent(driver, "Add a room rate", 5);
		String Datevalue = getDateTime(1, "ddmmss");
		safeType(driver, getObjectHotels("CHMM_Rates_Name"), "Automation Test "+Datevalue);
		//safeSelect(driver, getObjectHotels("CHMM_Rates_AllowMarkup"), RateType+" Rate");
		safeType(driver, getObjectHotels("CHMM_Rates_Margin_Percent"), "10");
		elementVisible(driver, getObjectHotels("CHMM_HotelSearch"), 60);
		safeAutocomplete_CHMM(driver, getObjectHotels("CHMM_HotelSearch"), getObjectHotels("CHMM_Rates_Hotel_Search_Ajax"), HotelName);;
		elementVisible(driver, getObjectHotels("CHMM_HotelSearch"), 60);
		safeAutocomplete_CHMM(driver, getObjectHotels("CHMM_HotelSearch"), getObjectHotels("CHMM_Rates_Hotel_Search_Ajax"), HotelName);;
		safeSelect(driver, getObjectHotels("CHMM_Rates_Room_Type"), RoomType);
		safeClick(driver, getObjectHotels("CHMM_Rates_Validity_Start_Calendar"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Validity_Start_Calendar_Date"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Validity_Ending_Calendar"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Validity_Ending_Calendar_Date"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Update_Inventory_Button"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Update_Inventory_Start_Calendar"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Update_Inventory_Start_Calendar_Date"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Update_Inventory_Ending_Calendar"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Update_Inventory_Ending_Calendar_Date"));
		safeType(driver, getObjectHotels("CHMM_Rates_Room_Inventory"), "10");		
		safeClick(driver, getObjectHotels("CHMM_Goto_PricingBlock"));
		Thread.sleep(5000);
		elementVisible(driver,getObjectHotels("CHMM_HotelCurrencyDropDown"), 15);
		safeSelect(driver, By.id("hotel_cur_dropdown"), "INR Rs. - Indian Rupee");
		elementVisible(driver,getObjectHotels("CHMM_GstTextOnCHMM_AddPage"), 5);
		Thread.sleep(10000);
		if(HotelCountry.contentEquals("Domestic"))
		{
			//Adding Rack Rate cases can be done here
			elementVisible(driver, getObjectHotels("CHMM_CalcuGSTonSRButton"), 15);
			elementVisible(driver, getObjectHotels("CHMM_CalcuGSTonRRButton"), 15);
				if(RateType.contentEquals("SRSR"))
				{
					elementVisible(driver, getObjectHotels("CHMM_GstApplyOnSRButton"), 15);
					//safeSelect(driver, getObject("CHMM_HotelCurrencyDropDown"), "INR Rs. - Indian Rupee");
					safeClick(driver, getObjectHotels("CHMM_CalcuGSTonSRButton"));
					safeClick(driver,getObjectHotels("CHMM_GstApplyOnSRButton"));
					if(enterPriceCalculateGst(driver, "SRSR",gstPercent)==true)
					{
						Reporter.log("SRSR Calculation PASSED");
						Assert.assertTrue(true);
						}
					else{
						Reporter.log("SRSR Calcuation FAILED , Please check manually");
						Assert.assertTrue(false);
						}
				}
				else if (RateType.contentEquals("RRSR"))
				{
					elementVisible(driver, getObjectHotels("CHMM_CalcuGSTonRRButton"), 15);
					elementVisible(driver, getObjectHotels("CHMM_GstApplyOnSRButton"), 15);
					//safeSelect(driver, getObject("CHMM_HotelCurrencyDropDown"), "INR Rs. - Indian Rupee");
					safeClick(driver,getObjectHotels("CHMM_CalcuGSTonRRButton"));
					safeClick(driver,getObjectHotels("CHMM_GstApplyOnSRButton"));
					if(enterPriceCalculateGst(driver, "RRSR",gstPercent)==true)
					{
						Reporter.log("RRSR Calculation PASSED");
						Assert.assertTrue(true);
						}
					else{
						Reporter.log("RRSR Calcuation FAILED , Please check manually");
						Assert.assertTrue(false);
						}
				}
				else if (RateType.contentEquals("RRRR"))
				{
					elementVisible(driver, getObjectHotels("CHMM_CalcuGSTonRRButton"), 15);
					elementVisible(driver, getObjectHotels("CHMM_GstApplyOnRRButton"), 15);
					//safeSelect(driver, getObject("CHMM_HotelCurrencyDropDown"), "INR Rs. - Indian Rupee");
					safeClick(driver,getObjectHotels("CHMM_CalcuGSTonRRButton"));
					safeClick(driver,getObjectHotels("CHMM_GstApplyOnRRButton"));
					if(enterPriceCalculateGst(driver, "RRRR",gstPercent)==true)
					{
						Reporter.log("RRRR Calculation PASSED");
						Assert.assertTrue(true);
						}
					else{
						Reporter.log("RRRR Calcuation FAILED , Please check manually");
						Assert.assertTrue(false);
						}
				}
					else{Assert.assertTrue(false);}
			safeClick(driver, By.linkText("Cancellation charges"));
			safeClick(driver, By.id("nonRefundCheck"));
			elementVisible(driver, getObjectHotels("CHMM_Rates_Save_Room_Rate"),60);
			Thread.sleep(3000);
			safeClick(driver,By.xpath("//*[@id='cancellation_collection_date']/fieldset/p/a/img"));
			Thread.sleep(5000);
			safeClick(driver, getObjectHotels("CHMM_Rates_Save_Room_Rate"));
			Thread.sleep(10000);
			if(!elementPresent_Time(driver, getObjectHotels("CHMM_Rates_Added_Without_Error"), 30)) {
				Reporter.log("Chmm Rates added confirmed message is not displayed");
				Assert.assertTrue(false);
			}
			String RateAdded = getText(driver, getObjectHotels("CHMM_Rates_Added_Without_Error"));
			Boolean RateisAdded = RateAdded.contains("rate has been added for ");
			if(!RateisAdded){
				Reporter.log("Rate is not added");
				Assert.assertTrue(false);
			}				
							
		}
		else if (HotelCountry.contentEquals("International"))
		{
			//This condition is written for CHMM Rates , Older format (Eg, CHMM International hotels)
			Assert.assertTrue(true);
		}
		else 
		{ 
			Reporter.log("Wrong input DATA given:Country; Domestic/International");
			Assert.assertTrue(false); 
		}
				
	}
	public boolean enterPriceCalculateGst(RemoteWebDriver driver,String rateType,int gstPercent) throws Exception
	{
		if(rateType.contentEquals("SRSR"))
		{
			String gstPercentString=Integer.toString(gstPercent);
			String sellPriceSingleRate=Double.toString(gstCal(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"),gstPercent));
			String sellPriceDoubleRate=Double.toString(gstCal(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"),gstPercent));
			//Verifying Singlebed and Doublebed rate
			safeType(driver, getObjectHotels("CHMM_SR_SingleRate"),dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"));			
			elementVisible(driver,getObjectHotels("CHMM_GstAppliedPercentSingleRate"),10);
			safeType(driver,getObjectHotels("CHMM_SR_DoubleRate"),dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"));
			elementVisible(driver,getObjectHotels("CHMM_GstAppliedPercentDoubleRate"), 10);
			safeClick(driver, getObjectHotels("CHMM_TotalPriceSingleRate"));
			String amountDisplayedSingleRate=getAttribute(driver,getObjectHotels("CHMM_TotalPriceSingleRate"),"value");
			String amountDisplayedDoubleRate=getAttribute(driver,getObjectHotels("CHMM_TotalPriceDoubleRate"),"value");
			if(amountDisplayedSingleRate.contains(sellPriceSingleRate) && amountDisplayedDoubleRate.contains(sellPriceDoubleRate))
			{
			Reporter.log("Sell Price - Single: "+(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"))+" GST Percent:"+gstPercentString+" TotalAmount shown in CHMM: "+amountDisplayedSingleRate);
			Reporter.log("Sell Price - Double: "+(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"))+" GST Percent:"+gstPercentString+" TotalAmount shown in CHMM: "+amountDisplayedDoubleRate);
			Assert.assertTrue(true);
			}
			else {Assert.assertTrue(false);}
			//ExtraBed/ChildExtraBed/ChildNoBed
			safeType(driver,getObjectHotels("CHMM_SR_ExtraBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ExtraBed"));
			safeType(driver,getObjectHotels("CHMM_SR_ChildExtraBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ChildExtraBed"));
			safeType(driver,getObjectHotels("CHMM_SR_ChildNoBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ChildNoBed"));
			return true;
		}
		else if (rateType.contentEquals("RRSR"))
		{
			String gstPercentString=Integer.toString(gstPercent);
			String sellPriceSingleRate=Double.toString(gstCal(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"),gstPercent));
			String sellPriceDoubleRate=Double.toString(gstCal(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"),gstPercent));
			//Verifying Singlebed and Doublebed rate
			safeType(driver, getObjectHotels("CHMM_SR_SingleRate"),dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"));
			safeType(driver, getObjectHotels("CHMM_RR_SingleRate"),dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"));
			elementVisible(driver,getObjectHotels("CHMM_GstAppliedPercentSingleRate"),10);
			safeType(driver,getObjectHotels("CHMM_SR_DoubleRate"),dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"));
			safeType(driver, getObjectHotels("CHMM_RR_DoubleRate"),dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"));
			elementVisible(driver,getObjectHotels("CHMM_GstAppliedPercentDoubleRate"), 10);
			safeClick(driver, getObjectHotels("CHMM_TotalPriceSingleRate"));
			String amountDisplayedSingleRate=getAttribute(driver,getObjectHotels("CHMM_TotalPriceSingleRate"),"value");
			String amountDisplayedDoubleRate=getAttribute(driver,getObjectHotels("CHMM_TotalPriceDoubleRate"),"value");
			if(amountDisplayedSingleRate.contains(sellPriceSingleRate) && amountDisplayedDoubleRate.contains(sellPriceDoubleRate))
			{
			Reporter.log("Sell Price - Single: "+(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"))+" GST Percent:"+gstPercentString+" TotalAmount shown in CHMM: "+amountDisplayedSingleRate);
			Reporter.log("Sell Price - Double: "+(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"))+" GST Percent:"+gstPercentString+" TotalAmount shown in CHMM: "+amountDisplayedDoubleRate);
			Assert.assertTrue(true);
			}else {Assert.assertTrue(false);}
			
			//Extrabed or ChildExtraBed or ChildNoBed
			safeType(driver,getObjectHotels("CHMM_SR_ExtraBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ExtraBed"));
			safeType(driver,getObjectHotels("CHMM_RR_ExtraBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ExtraBed"));
			safeType(driver,getObjectHotels("CHMM_SR_ChildExtraBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ChildExtraBed"));
			safeType(driver,getObjectHotels("CHMM_RR_ChildExtraBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ChildExtraBed"));
			safeType(driver,getObjectHotels("CHMM_SR_ChildNoBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ChildNoBed"));
			safeType(driver,getObjectHotels("CHMM_RR_ChildNoBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ChildNoBed"));
			return true;
		}
		else if (rateType.contentEquals("RRRR"))
		{
			String gstPercentString=Integer.toString(gstPercent);
			String sellPriceSingleRate=Double.toString(gstCal(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"),gstPercent));
			String sellPriceDoubleRate=Double.toString(gstCal(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"),gstPercent));
			//Verifying Singlebed and Doublebed rate
			safeType(driver, getObjectHotels("CHMM_SR_SingleRate"),dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"));
			safeType(driver, getObjectHotels("CHMM_RR_SingleRate"),dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"));
			elementVisible(driver,getObjectHotels("CHMM_GstAppliedPercentSingleRate"),10);
			safeType(driver,getObjectHotels("CHMM_SR_DoubleRate"),dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"));
			safeType(driver, getObjectHotels("CHMM_RR_DoubleRate"),dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"));
			elementVisible(driver,getObjectHotels("CHMM_GstAppliedPercentDoubleRate"), 10);
			safeType(driver,getObjectHotels("CHMM_SR_ExtraBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ExtraBed"));
			safeType(driver,getObjectHotels("CHMM_RR_ExtraBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ExtraBed"));
			safeType(driver,getObjectHotels("CHMM_SR_ChildExtraBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ChildExtraBed"));
			safeType(driver,getObjectHotels("CHMM_RR_ChildExtraBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ChildExtraBed"));
			safeType(driver,getObjectHotels("CHMM_SR_ChildNoBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ChildNoBed"));
			safeType(driver,getObjectHotels("CHMM_RR_ChildNoBedRate"),dataFile.value("CHMM_"+gstPercent+"to"+gstPercent+"%_ChildNoBed"));
			String amountDisplayedSingleRate=getAttribute(driver,getObjectHotels("CHMM_TotalPriceSingleRate"),"value");
			String amountDisplayedDoubleRate=getAttribute(driver,getObjectHotels("CHMM_TotalPriceDoubleRate"),"value");
			if(amountDisplayedSingleRate.contains(sellPriceSingleRate) && amountDisplayedDoubleRate.contains(sellPriceDoubleRate))
			{
			Reporter.log("Sell Price - Single: "+(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Single"))+" GST Percent:"+gstPercentString+" TotalAmount shown in CHMM: "+amountDisplayedSingleRate);
			Reporter.log("Sell Price - Double: "+(dataFile.value("CHMM_"+gstPercent+"%_SellRate_Double"))+" GST Percent:"+gstPercentString+" TotalAmount shown in CHMM: "+amountDisplayedDoubleRate);
			Assert.assertTrue(true);
			}else 
			{Assert.assertTrue(false);}
			return true;
		}
		else
		{
			return false;	
		}		
		
	}
	public double gstCal(String sellRate,int gstPercent)
	{
		double rate=Integer.valueOf(sellRate);
		double pertageinDec=(gstPercent*0.01);
		double total=(rate*pertageinDec)+rate;
		return total;
		
	}
	
	public void CHMM_Select_Rate(RemoteWebDriver driver, String HotelName, String RoomType, String RateType) throws Exception{
		safeClick(driver, getObjectHotels("CHMM_Rates_Tab_Link"));
		elementVisible(driver, getObjectHotels("CHMM_Rates_New_Room_Rate_Button"), 30);
		textPresent(driver, "Find & update room rates", 2);
		Thread.sleep(2000);
		safeAutocomplete_CHMM(driver, getObjectHotels("CHMM_HotelSearch"), getObjectHotels("CHMM_Rates_Hotel_Search_Ajax"), HotelName);
		safeSelect(driver, getObjectHotels("CHMM_Rates_Room_Type"), RoomType);
		safeSelect(driver, getObjectHotels("CHMM_Rates_Rate_Type"), RateType);
		safeClick(driver, getObjectHotels("CHMM_Rates_Submit_Button"));
		elementVisible(driver, getObjectHotels("CHMM_Rates_Calendar_Page_Right_HandSide_Content"), 60);
	}	
	
	public void CHMM_Inventory_Bulk_Update(RemoteWebDriver driver, String Edit_Month, String Start_Date, String End_Date, String Inventory, String Rate_Type) throws Exception{ // Rate Month = Current Month + Interger
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_View_Link"));
		elementVisible(driver, getObjectHotels("CHMM_Rates_Submit_Button"), 30);
	//	safeClick(driver, getObjectHotels("CHMM_Rates_Submit_Button"));
		elementVisible(driver, getObjectHotels("CHMM_Rates_Bulk_Update_From_Date_Calendar"), 30);
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_From_Date_Calendar"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, By.linkText(Start_Date));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_To_Date_Calendar"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, By.linkText(End_Date));
		safeClick(driver, getObjectHotels("CHMM_Rates_Submit_Button"));
		elementVisible(driver, getObjectHotels("CHMM_Rates_Bulk_Update_View_Page"), 60);
		
		int Start = Integer.parseInt(Start_Date);
		 int End = Integer.parseInt(End_Date);
		 int No_Of_Days = End-Start;
		 for(int i=0; i<=No_Of_Days; i++){
			 String Inventory_Xpath = "inv_"+i;
			 safeType(driver, By.id(Inventory_Xpath), Inventory);
		 }

		 safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Submit_Button"));
		 //Thread.sleep(25000);
		 //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 String Updated_Inventory= getText(driver, By.id("inv_span_0"));
		 Boolean Bol_Updated_Inventory = Updated_Inventory.equals(Inventory);
		 if(!Bol_Updated_Inventory){
			 Reporter.log("Rates are not Updated");
			 Assert.assertTrue(false);
			 }
	 }
	
	public void CHMM_Inventory_Update(RemoteWebDriver driver, String EditMonth, String StartDate, String EndDate, String Inventory, String  HotelName, String RoomType) throws Exception{ // Rate Month = Current Month + Interger
		elementVisible(driver, getObjectHotels("CHMM_Inventory_Link"), 30);
	 	safeClick(driver, getObjectHotels("CHMM_Inventory_Link"));
	 	elementVisible(driver, getObjectHotels("CHMM_Rates_Submit_Button"), 30);
        textPresent(driver, "Find & update room inventory", 5);
        safeAutocomplete_CHMM(driver, getObjectHotels("CHMM_HotelSearch"), getObjectHotels("CHMM_Rates_Hotel_Search_Ajax"), HotelName);
    	safeSelect(driver, getObjectHotels("CHMM_Rates_Room_Type"), RoomType);
		safeClick(driver, getObjectHotels("CHMM_Rates_Submit_Button"));
		elementVisible(driver, By.linkText("edit"), 60);
		safeClick(driver, getObjectHotels("CHMM_Inventory_Update_Button"));
		elementVisible(driver, getObjectHotels("CHMM_Rates_Bulk_Update_From_Date_Calendar"), 60);
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_From_Date_Calendar"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, By.linkText(StartDate));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_To_Date_Calendar"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		safeClick(driver, By.linkText(EndDate));
		safeType(driver, getObjectHotels("CHMM_Rates_Room_Inventory"), Inventory);
		safeClick(driver, getObjectHotels("CHMM_Inventory_Bulk_Update_Button"));
		elementNotVisible(driver, getObjectHotels("CHMM_Rates_Bulk_Update_From_Date_Calendar"), 60);
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		safeClick(driver, getObjectHotels("CHMM_Inventory_Next_Month_Link"));
		 //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 elementVisible(driver, getObjectHotels("CHMM_Inventory_Next_Month_Link"), 60);
		 safeClick(driver, getObjectHotels("CHMM_Inventory_Next_Month_Link"));
		 String Chmm_Date = getChmm_RateID(2);
		 String ChmmUpdated_Start_date = StartDate+Chmm_Date;
		 Thread.sleep(10000);
		 String Edited_Inventory_Start_Date = getText(driver, By.id(ChmmUpdated_Start_date));
		 int Inventory_Count=Integer.parseInt(Edited_Inventory_Start_Date);
		 int Inventory_Updated = Integer.parseInt(Inventory);

		 if(!(Inventory_Count==Inventory_Updated)) {
			 Reporter.log("Inventory added is not Updated");
			 Assert.assertTrue(false);
		 }
	 }
	
	public int CHMM_Inventory_Check(RemoteWebDriver driver, String HotelName, String RoomType, String StartDate) throws Exception {
	 	elementVisible(driver, getObjectHotels("CHMM_Inventory_Link"), 30);
	 	safeClick(driver, getObjectHotels("CHMM_Inventory_Link"));
	 	elementVisible(driver, getObjectHotels("CHMM_Rates_Submit_Button"), 60);
        textPresent(driver, "Find & update room inventory", 5);
        safeAutocomplete_CHMM(driver, getObjectHotels("CHMM_HotelSearch"), getObjectHotels("CHMM_Rates_Hotel_Search_Ajax"), HotelName);
    	safeSelect(driver, getObjectHotels("CHMM_Rates_Room_Type"), RoomType);
		safeClick(driver, getObjectHotels("CHMM_Rates_Submit_Button"));
		elementVisible(driver, By.linkText("edit"), 60);
		 safeClick(driver, getObjectHotels("CHMM_Inventory_Next_Month_Link"));
		 //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 elementVisible(driver, getObjectHotels("CHMM_Inventory_Next_Month_Link"), 30);
		 safeClick(driver, getObjectHotels("CHMM_Inventory_Next_Month_Link"));
		 Thread.sleep(5000);
		 String Chmm_Date = getChmm_RateID(2);
		 String ChmmUpdated_Start_date = StartDate+Chmm_Date;
		 String Edited_Inventory_Start_Date = getText(driver, By.id(ChmmUpdated_Start_date));
		 int Inventory_Count=Integer.parseInt(Edited_Inventory_Start_Date);
		 Reporter.log("Now the Inventory Count is = "+Inventory_Count);
		 return Inventory_Count;		
	}	
	
	public String getChmm_RateID(int month) throws Exception {
        Calendar calender = new GregorianCalendar();
        calender.add(Calendar.MONTH, +month);
        Date date = calender.getTime();
        String dateID = new SimpleDateFormat("MMyyyy").format(date);
        String firstLetter = String.valueOf(dateID.charAt(0));
        String Chmm_RateID = dateID;
        if(firstLetter.contains("0")){        	
        	Chmm_RateID= dateID.replaceFirst("0", "");
        }
        return Chmm_RateID;
        
    }

	public void CHMM_HomepageSearch_Web(RemoteWebDriver driver, String Domain, String City, String CheckIn_Date, String CheckOut_Date, int Months, String Rooms, String Adult1,
			String Adult2, String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2) throws Exception {	
		driver.manage().deleteAllCookies();
		baseUrl_Com = getBaseUrl( Domain);
		driver.get(baseUrl_Com);
		logMessagePageNotLoaded(driver, getObject("HotelCom_HomePage_HotelTab"), 2, "Home Page has not loaded");
		safeClick(driver, getObject("HotelCom_HomePage_HotelTab"));
		if(!elementVisible(driver, getObject("HotelCom_HomePage_SearchBox"), 1)){
			safeClick(driver, getObject("HotelCom_HomePage_HotelTab"));	
		}
		elementVisible(driver, getObject("HotelCom_HomePage_Room_DropDown"), 1);
		safeAutocomplete(driver, getObject("HotelCom_HomePage_SearchBox"), getObject("HotelCom_HomePage_SearchAjax"), City);
		selectCalendarDate(driver, getObject("HotelCom_HomePage_CheckIn_Cal"), getObject("HotelCom_HomePage_CheckIn_NextMonth"), Months, CheckIn_Date);
		selectCalendarDate(driver, getObject("HotelCom_HomePage_CheckOut_Cal"), getObject("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
		safeSelect(driver, getObject("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
		hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
		String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
		Reporter.log("Hotels Searched for " + City + " City for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
		safeClick(driver, getObject("HotelCom_HomePage_Search_Button"));
	}

	public void CHMM_BookingTab_Trip_Validation (RemoteWebDriver driver, String HotelName, String TripID) throws Exception {
		if(MakePaymentOnlyInQA2){
			elementVisible(driver, getObjectHotels("CHMM_Booking_Tab"), 60);
			safeClick(driver, getObjectHotels("CHMM_Booking_Tab"));
			elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Go_Button"), 60);
			textPresent(driver, "Booking activity at your hotels", 5);	
	        safeAutocomplete_CHMM(driver, getObjectHotels("CHMM_HotelSearch"), getObjectHotels("CHMM_Rates_Hotel_Search_Ajax"), HotelName);
	
	        elementPresent_Time(driver, By.linkText(TripID), 30);
	        elementPresent(driver, By.linkText(TripID));
			}
		}
	
	public void CHMM_Edit_Rate_Bulk_Update(RemoteWebDriver driver, String StartDate, String EndDate, String Single_RoomRate, String Double_RoomRate, String RateType) throws Exception{ // Rate Month = Current Month + Interger
		elementVisible(driver, getObjectHotels("CHMM_Rates_Bulk_Update_View_Link"), 50);
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_View_Link"));
		elementVisible(driver, getObjectHotels("CHMM_Rates_Bulk_Update_From_Date_Calendar"), 50);
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_From_Date_Calendar"));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		Thread.sleep(2000);
		safeClick(driver, By.linkText(StartDate));
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_To_Date_Calendar"));
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Show_Next_Month"));
		Thread.sleep(2000);
		safeClick(driver, By.linkText(EndDate));
		 safeClick(driver, getObjectHotels("CHMM_Rates_Submit_Button"));
		 elementVisible(driver, getObjectHotels("CHMM_Rates_Bulk_Update_View_Page"), 50);
		 int Start = Integer.parseInt(StartDate);
		 int End = Integer.parseInt(EndDate);
		 int No_Of_Days = End-Start;	 
		 Boolean Rate_Type_Sell = RateType.contains("Sell");
		 Boolean Rate_Type_Net = RateType.contains("Net"); 
		 elementVisible(driver, By.id("sgsr_0"), 30);
		 elementVisible(driver, By.id("sgnr_0"), 30);
		 for(int i=0; i<=No_Of_Days; i++){
			 
			 if(Rate_Type_Sell){
					safeType(driver, By.id("sgsr_"+i), Single_RoomRate);
					safeType(driver, By.id("dbsr_"+i), Double_RoomRate);
				}
				else if(Rate_Type_Net){
					safeType(driver, By.id("sgnr_"+i), Single_RoomRate);
					safeType(driver, By.id("dbnr_"+i), Double_RoomRate);
				}
		 }
		 safeClick(driver, getObjectHotels("CHMM_Rates_Bulk_Update_Submit_Button"));
		 if(Rate_Type_Sell){
			 String Updated_Sell_Rate= getText(driver, By.id("dsgsr_0"));
			 Boolean Sell_Rate_Update = Updated_Sell_Rate.equals(Single_RoomRate);
			 Reporter.log("Updated_Sell_Rate = "+Updated_Sell_Rate);
			 if(!Sell_Rate_Update){
				 Reporter.log("Rates are not Updated");
				 Assert.assertTrue(false);
			 }
		 }
		 else if(!Rate_Type_Net){
			 String Updated_Net_Rate= getText(driver, By.id("dsgnr_0"));
			 Boolean Net_Rate_Update = Updated_Net_Rate.equals(Single_RoomRate);
			 if(Net_Rate_Update){
				 Reporter.log("Rates are not Updated");
				 Assert.assertTrue(false);
			 }
		 }
	 }

	public void CHMM_Edit_CalendarRate(RemoteWebDriver driver, String EditMonth, String EditDate, String Single_RoomRate, String Double_RoomRate, String RateType) throws Exception{ // Rate Month = Current Month + Interger
		
		 int Month = Integer.parseInt(EditMonth);
		// int Date = Integer.parseInt(EditDate);
		 for (int i=1; i<=Month; i++){
	        elementVisible(driver, getObjectHotels("CHMM_Inventory_Next_Month_Link"), 2);
			safeClick(driver, getObjectHotels("CHMM_Inventory_Next_Month_Link"));
			Thread.sleep(5000);
		}
		 	Thread.sleep(2000);
		 	safeClick(driver, By.xpath("(//a[contains(text(),'edit')])["+EditDate+"]"));
		 	Thread.sleep(5000);
			//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 	driver.switchTo().frame("modal_window");
			Boolean Rate_Type_Sell = RateType.contains("Sell");
			Boolean Rate_Type_Net = RateType.contains("Net");
			if(Rate_Type_Sell){
				safeType(driver, By.id("sgsr_0"), Single_RoomRate);
				safeType(driver, By.id("dbsr_0"), Double_RoomRate);
			}
			else if(Rate_Type_Net){
				safeType(driver, By.id("sgnr_0"), Single_RoomRate);
				safeType(driver, By.id("dbnr_0"), Double_RoomRate);
			}
			Thread.sleep(5000);
			safeClick(driver, getObjectHotels("CHMM_Calendar_Edit_Save_Button"));
			Thread.sleep(5000);
			//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			String mainWindow = driver.getWindowHandle();
	//		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//			driver.switchTo().window(tabs.get(0));
			driver.switchTo().window(mainWindow);
			Thread.sleep(7000);
			String Chmm_RateID = getChmm_RateID(Month);
	    	String Chmm_RateID_Day = EditDate+Chmm_RateID;
	    	elementVisible(driver, By.xpath("//dl[@id='"+Chmm_RateID_Day+"']/dd"), 10);
	    	String Rate_Value =getText(driver, By.xpath("//dl[@id='"+Chmm_RateID_Day+"']/dd"));
	    	//elementVisible(driver, By.cssSelector("#"+Chmm_RateID_Day+">dd"), 10);
	    	//String Rate_Value =getText(driver, By.cssSelector("#"+Chmm_RateID_Day+">dd"));
	    	if(Rate_Value.contains(".")) {
	    		Rate_Value = Rate_Value.substring(0, Rate_Value.length()-2);
	    	}	    	
	   		if(!(Rate_Value.contains(Single_RoomRate))) {
	   			 Reporter.log("Room rate after edit : "+Rate_Value+" : Room rate edited : "+Single_RoomRate);
	   			 Assert.assertTrue(false);
	   		 }
	 }

	public void CHMM_SRP_Web(RemoteWebDriver driver, String HotelName, String Single_RoomRate, String Double_RoomRate, String RateType) throws InterruptedException, IOException, Exception {
			logMessagePageNotLoaded(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 60, "Search Results Page has not loaded");
			elementVisible(driver, getObject("HotelCorp_SRP_HotelName_TextBox"), 60);
			elementPresent(driver, getObject("HotelCom_SRP_HotelName_TextBox"));
			safeAutocomplete(driver, getObject("HotelCom_SRP_HotelName_TextBox"), getObject("HotelCom_SRP_HotelName_Ajax"), HotelName);
			safeClick(driver, getObject("HotelCom_SRP_SelectRoom_Button"));
			String URL = logURL(driver);
			if(URL.contains(".com")){
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				Thread.sleep(1000);
				driver.manage().window().maximize();
				}
			String Roomrate_Active =null;
			if(RateType.contains("Net")){
				Roomrate_Active = "Room only rate";
			} else Roomrate_Active = "Free Wi-Fi";
			//	String Roomrate_Active = RateType.substring(0, RateType.length()-9);
			/*	loop : for (int i=1; i<=2;i++){
						for(int j=2; j<=3;j++){
							String Rooms_Xpath = "//div[@id='roomsContainer']/div["+i+"]/div[2]/div["+j+"]/h5";
							elementVisible(driver, By.xpath(Rooms_Xpath), 10);
							Boolean Room_Type =getText(driver, By.xpath(Rooms_Xpath)).contains(Roomrate_Active);
								if(Room_Type){
									String RoomRate_Xpath = "//div["+i+"]/div[2]/div["+j+"]/div[3]/div/strong";
									String Room_Rate_Web = getText(driver, By.xpath(RoomRate_Xpath));									
									if(Single_RoomRate.isEmpty()){
									Boolean Rate_Match = Room_Rate_Web.equals(Single_RoomRate);
										if (!Rate_Match){
											Reporter.log("Chmm & Web Search : Rates does'nt Match");			
										}	else Reporter.log("Chmm & Web Search : Rates Match ");
									}								
									safeClick(driver, By.xpath("//div["+i+"]/div[2]/div["+j+"]/div[4]/a")); 			
									break loop;
								}
								else {
										Reporter.log(Roomrate_Active+" : Rate is not displayed - Update Room Inventory");
										Assert.assertTrue(false);
						}				
					}		
				}*/
			for(int i=1; i<=2;i++) {
				String RateType_Xpath = "//div[2]/div/div/nav/ul/li["+i+"]";
				String RateType_Web = getText(driver, By.xpath(RateType_Xpath));
				if(RateType_Web.contains(Roomrate_Active)) {
					String SelectRate_CheckBox_Xpath = "//li["+i+"]/div/div";
					String Rate_Xpath = "//li["+i+"]/div/div/span";
					String Rate_Price = getText(driver, By.xpath(Rate_Xpath));
					Rate_Price = Rate_Price.replace("Rs.", "");
					if(!(Rate_Price.equals(Single_RoomRate))){
						Reporter.log("Price in SRP : "+Rate_Price+" Price provided in CHMM : "+Single_RoomRate);

						//System.out.println("Price in SRP : "+Rate_Price+" Price provided in CHMM : "+Single_RoomRate);
						Assert.assertTrue(false);						
					}
					safeClick(driver, By.xpath(SelectRate_CheckBox_Xpath));
				}
			}
			Thread.sleep(2000);
			safeClick(driver, By.xpath("//div/div/div[2]/a"));
		}
	
	
	public void CHMM_DetailsPageValidation(RemoteWebDriver driver, String Chmm_RateType, String Single_RoomRate) throws Exception {
		  String Roomrate_Active =null;
			if(Chmm_RateType.contains("Net")){
				Roomrate_Active = "Free Wi-Fi";
			} else Roomrate_Active = "Room only rate";
		
			for(int i=1; i<=2;i++) {
				String RateType_Xpath = "//div[2]/div/div/nav/ul/li["+i+"]";
				String RateType_Web = getText(driver, By.xpath(RateType_Xpath));
				if(RateType_Web.contains(Roomrate_Active)) {
					String Rate_Xpath = "//li["+i+"]/div/div/span";
					String Rate_Price = getText(driver, By.xpath(Rate_Xpath));
					Rate_Price = Rate_Price.replace("Rs.", "");
					if(!(Rate_Price.equals(Single_RoomRate))){
						Reporter.log("Price in SRP : "+Rate_Price+" Price provided in CHMM : "+Single_RoomRate);
						Assert.assertTrue(false);						
					}
				}
			}
		}	
	
	public void CHMM_Ledger_Booking_DateSearch(RemoteWebDriver driver, String Date, String ReservationStatus) throws Exception {
		   elementVisible(driver, getObjectHotels("CHMM_Ledger_Link"), 60);  
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Link"));
		   elementVisible(driver, getObjectHotels("CHMM_Ledger_BookingDate"), 60);
		   //safeClick(driver, getObjectHotels("CHMM_Ledger_BookingDate"));
		   safeClick(driver, By.id("dt-CHECK_IN_DATE"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_From_Calendar"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Next_Calendar_Link"));
		   safeClick(driver, By.linkText(Date));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_To_Calendar"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Next_Calendar_Link"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Next_Calendar_Link"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Next_Calendar_Link"));
		   safeClick(driver, By.linkText(Date));
		   safeSelect(driver, getObjectHotels("CHMM_Ledger_Supplier_Dropdown"), "All");
		   safeSelect(driver, getObjectHotels("CHMM_Ledger_BookingStatus_Dropdown"), "All");
		   safeSelect(driver, getObjectHotels("CHMM_Ledger_ReservationStatus_Dropdown"), ReservationStatus);
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Search_button"));
		   elementVisible(driver, getObjectHotels("CHMM_Ledger_Search_HotelName"), 60) ;
		 }
	
	public void CHMM_Ledger_Trip_Search_Status_Verification(RemoteWebDriver driver,String TripID, String Status) throws Exception{
			elementVisible(driver, getObjectHotels("CHMM_Ledger_Link"), 30);
			safeClick(driver, getObjectHotels("CHMM_Ledger_Link"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_BookingDate"));
		   safeSelect(driver, getObjectHotels("CHMM_Ledger_BookingStatus_Dropdown"), "All");
		   safeType(driver, getObjectHotels("CHMM_Ledger_TripID"), TripID);
		  safeClick(driver, getObjectHotels("CHMM_Ledger_Search_button"));
		  elementVisible(driver, getObjectHotels("CHMM_Ledger_Search_Hotel_Reservation_Status"), 30);
		  String Reservation_Status = getText(driver, getObjectHotels("CHMM_Ledger_Search_Hotel_Reservation_Status"));
		  Reporter.log("The reservation status is "+Reservation_Status);
		  Assert.assertEquals("Status is matched", Status, Reservation_Status);
	}
	
	public void CHMM_PAH_Cnfrm_ReCnfrm_Change(RemoteWebDriver driver, String Confrm_status) throws Exception{
				if(Confrm_status.equalsIgnoreCase("Reconfirm"))
				{
					
					driver.get("http://qa2.cleartrip.com/chmm/listhotel?hotelId="+dataFile.value("HotelID_PAHConfirm"));
					elementVisible(driver, getObjectHotels("CHMM_HotelSearch_Settings"), 5);
					safeClick(driver, getObjectHotels("CHMM_HotelSearch_Settings"));
					WebElement we = driver.findElement(getObjectHotels("CHMM_HotelSearch_Settings_Reconfrm_Checkbox"));
					Boolean isChecked = we.isSelected();
						if(isChecked)
							{
								safeClick(driver, getObjectHotels("CHMM_HotelSearch_Settings_Save_Button"));
							}
						else
							{
								we.click();
								safeClick(driver, getObjectHotels("CHMM_HotelSearch_Settings_Save_Button"));
							}
				}
			
			else if(Confrm_status.equalsIgnoreCase("Confirm")){
				driver.get("http://qa2.cleartrip.com/chmm/listhotel?hotelId=51344");
				elementVisible(driver, getObjectHotels("CHMM_HotelSearch_Settings"), 5);
				safeClick(driver, getObjectHotels("CHMM_HotelSearch_Settings"));
				WebElement we = driver.findElement(getObjectHotels("CHMM_HotelSearch_Settings_Confrm_Checkbox"));
				Boolean isChecked = we.isSelected();
					if(isChecked)
						{
							safeClick(driver, getObjectHotels("CHMM_HotelSearch_Settings_Save_Button"));
						}
				else
						{
							we.click();
							safeClick(driver, getObjectHotels("CHMM_HotelSearch_Settings_Save_Button"));
						}
					}
		}
	
	public void CHMM_Ledger_Status_Change(RemoteWebDriver driver, String ReservationStatusChanged, String UpdatedStatus) throws Exception {
				String HotelName = null, TripID = null;
				HotelName = getText(driver, getObjectHotels("CHMM_Ledger_Search_HotelName"));
				TripID = getText(driver, getObjectHotels("CHMM_Ledger_Search_Hotel_TripID"));				
				safeClick(driver, getObjectHotels("CHMM_Ledger_Search_Hotel_Reservation_Status"));
				textPresent(driver, HotelName, 50);
				elementVisible(driver, getObjectHotels("CHMM_Ledger_Change_Status_Edit"), 100);
				safeClick(driver, getObjectHotels("CHMM_Ledger_Change_Status_Edit"));
				textPresent(driver, "Reservation Status", 50);
				safeSelect(driver, getObjectHotels("CHMM_Ledger_Change_Reservation"), ReservationStatusChanged);
				Thread.sleep(5000);
				safeClick(driver, getObjectHotels("CHMM_Ledger_Change_Reservation_Submit_Button"));
				textPresent(driver, "Record updated successfully.", 50);
				 elementVisible(driver, getObjectHotels("CHMM_Ledger_Link"), 60);  
				 safeClick(driver, getObjectHotels("CHMM_Ledger_Link"));
				 Thread.sleep(2000);
				elementVisible(driver, getObjectHotels("CHMM_Ledger_TripID"), 60);
				 Thread.sleep(2000);
				safeType(driver, getObjectHotels("CHMM_Ledger_TripID"), TripID);
				Thread.sleep(2000);
				safeSelect(driver, getObjectHotels("CHMM_Ledger_ReservationStatus_Dropdown"), "All");
				Thread.sleep(2000);
				Reporter.log("TripID & Hotel Name = "+TripID+"  "+HotelName);
				elementVisible(driver, getObjectHotels("CHMM_Ledger_Search_button"), 50);
				safeClick(driver, getObjectHotels("CHMM_Ledger_Search_button"));
				Thread.sleep(10000);
				elementVisible(driver, getObjectHotels("CHMM_Ledger_Search_Hotel_Reservation_Status"), 50);
				String UpdatedStatusNew = getText(driver, getObjectHotels("CHMM_Ledger_Search_Hotel_Reservation_Status"));
				Boolean Status_Updated = UpdatedStatusNew.contains(UpdatedStatus);
				if(!Status_Updated){
				Assert.assertTrue(false);
			}
		}
	
	public void CHMM_Ledger_Booking_VccSearch(RemoteWebDriver driver, String Date, String TripID) throws Exception {
		   elementVisible(driver, getObjectHotels("CHMM_Ledger_Link"), 60);
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Link")); 
		   elementVisible(driver, getObjectHotels("CHMM_Ledger_BookingDate"), 60);
		   safeClick(driver, getObjectHotels("CHMM_Ledger_BookingDate"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_From_Calendar"));		   
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Month_Calendar_Link"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Month_Calendar_Link"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Month_Calendar_Link"));
		   safeClick(driver, By.linkText(Date));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_To_Calendar"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Next_Calendar_Link"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Next_Calendar_Link"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Next_Calendar_Link"));
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Previous_Next_Calendar_Link"));
		   safeClick(driver, By.linkText(Date));
		   safeSelect(driver, getObjectHotels("CHMM_Ledger_Supplier_Dropdown"), "All");
		   safeSelect(driver, getObjectHotels("CHMM_Ledger_BookingStatus_Dropdown"), "All");
		   safeType(driver, getObjectHotels("CHMM_Ledger_TripID"), TripID);
		   Thread.sleep(2000);
		 //  safeSelect(driver, getObjectHotels("CHMM_Ledger_PaymentStatus_Dropdown"), "vcc issued");
		   Thread.sleep(2000);
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Search_button"));
		   Thread.sleep(10000);
		   safeClick(driver, getObjectHotels("CHMM_Ledger_Search_Hotel_Reservation_Status"));
		   elementVisible(driver, By.xpath("//tr[26]/td[2]"), 60);
		   String Remarks = getText(driver, By.xpath("//tr[26]/td[2]"));
			   if(!Remarks.equals("vcc issued")) {
				   Reporter.log("Payment Status is not displaying as 'VCC Issued' ");
				// Assert.assertTrue(false);
			   } else Reporter.log("Payment Status is  displayed as 'VCC Issued' ");
	}
	
	public void CHMM_Booking_Status(RemoteWebDriver driver, String Status, String Status_Displayed) throws Exception {
		Thread.sleep(7000);	
		elementVisible(driver, getObjectHotels("CHMM_Booking_Tab"), 60);
			safeClick(driver, getObjectHotels("CHMM_Booking_Tab"));
			elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Activity_DropDown"), 60);
			safeSelect(driver, getObjectHotels("CHMM_Booking_Tab_Activity_DropDown"), Status_Displayed);
			safeSelect(driver, getObjectHotels("CHMM_Booking_Tab_Booked_From_Month_Dropdown"), "Feb");
			safeSelect(driver, getObjectHotels("CHMM_Booking_Tab_Booked_From_Year_Dropdown"), "2016");
			safeClick(driver, getObjectHotels("CHMM_Booking_Tab_Search_Go_Button"));
			elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Search_PopUp"), 10);
			elementNotVisible(driver, getObjectHotels("CHMM_Booking_Tab_Search_PopUp"), 120);
			String Status_Text = null;
			if(Status.contains("Booking")) {				
				elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Booking_Status_Confirmed"), 60);
				Status_Text = getText(driver, getObjectHotels("CHMM_Booking_Tab_Booking_Status_Confirmed"));
				if(!Status_Text.contains("Booking")) {
					Reporter.log("Status selected is not same as the results displayed");
					Assert.assertTrue(false);
				}
			} else {
				elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Booking_Status_Cancelled"), 60);
				Status_Text = getText(driver, getObjectHotels("CHMM_Booking_Tab_Booking_Status_Cancelled"));
				if(!Status_Text.contains("CANCEL")) {
					Reporter.log("Status selected is not same as the results displayed");
					Assert.assertTrue(false);
				}
			}			
		}	
	
	public void CHMM_Booking_Payment_Status(RemoteWebDriver driver, String Status) throws Exception {
		elementVisible(driver, getObjectHotels("CHMM_Booking_Tab"), 60);
		safeClick(driver, getObjectHotels("CHMM_Booking_Tab"));
		elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Payment_DropDown"), 60);
		safeSelect(driver, getObjectHotels("CHMM_Booking_Tab_Payment_DropDown"), Status);
		safeSelect(driver, getObjectHotels("CHMM_Booking_Tab_Booked_From_Month_Dropdown"), "Mar");
		safeSelect(driver, getObjectHotels("CHMM_Booking_Tab_Booked_From_Year_Dropdown"), "2017");
		safeClick(driver, getObjectHotels("CHMM_Booking_Tab_Search_Go_Button"));
		elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Search_PopUp"), 10);
		elementNotVisible(driver, getObjectHotels("CHMM_Booking_Tab_Search_PopUp"), 100);
		String Status_Text = null;
		if(Status.contains("Booking")) {
			elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Payment_Status"), 60);
			Status_Text = getText(driver, getObjectHotels("CHMM_Booking_Tab_Payment_Status"));
			if(!Status_Text.contains(Status)) {
				Reporter.log("Status selected is not same as the results displayed");
				Assert.assertTrue(false);
			}
		} 			
	}	
	
	public void CHMM_Booking_Reservation_Status(RemoteWebDriver driver, String Status) throws Exception {
		elementVisible(driver, getObjectHotels("CHMM_Booking_Tab"), 60);
		safeClick(driver, getObjectHotels("CHMM_Booking_Tab"));
		elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Reservation_DropDown"), 60);
		safeSelect(driver, getObjectHotels("CHMM_Booking_Tab_Reservation_DropDown"), Status);
		safeSelect(driver, getObjectHotels("CHMM_Booking_Tab_Booked_From_Month_Dropdown"), "Aug");
		safeSelect(driver, getObjectHotels("CHMM_Booking_Tab_Booked_From_Year_Dropdown"), "2017");
		safeClick(driver, getObjectHotels("CHMM_Booking_Tab_Search_Go_Button"));
		elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Search_PopUp"), 10);
		elementNotVisible(driver, getObjectHotels("CHMM_Booking_Tab_Search_PopUp"), 100);
		String Status_Text = null;
		if(Status.contains("Booking")) {
			elementVisible(driver, getObjectHotels("CHMM_Booking_Tab_Reservation_Status"), 60);
			Status_Text = getText(driver, getObjectHotels("CHMM_Booking_Tab_Reservation_Status"));
			if(!Status_Text.contains(Status)) {
				Reporter.log("Status selected is not same as the results displayed");
				Assert.assertTrue(false);
			}
		} 			
	}	
	

	public String hotelCom_Chmm_VerifyDebitNotes(RemoteWebDriver driver, String TripID) throws Exception{
		driver.get("http://qa2.cleartrip.com/chmm");
		   CHMM_Ledger_Trip_Search_Status_Verification(driver, TripID, "Cancelled");
		   safeClick(driver, By.linkText("Cancelled"));
		   elementVisible(driver, By.cssSelector("h1"), 30);
		   if(!elementVisible(driver, By.linkText("View Debit Notes"), 10)){
			   Reporter.log("view Debit Notes link is not displayed");
			   Assert.assertTrue(false);
		   }
		   if(!getText(driver, By.xpath("//tbody[@id='list_body']/tr/td/span")).contains("Pay at hotel")){
			   Reporter.log("P@H text is not displayed in page");
			 //  System.out.println("Text " +getText(driver, By.xpath("//tbody[@id='list_body']/tr/td/span")));
			  // Assert.assertTrue(false);
		   }
		   safeClick(driver, By.linkText("View Debit Notes"));
		   Thread.sleep(5000);
		   ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(1000);
			elementVisible(driver, By.cssSelector("h1"), 30);
			if(!getText(driver, By.cssSelector("h1")).contains("Invoice cum Debit Note")){
				   Reporter.log("Invoice cum Debit Note text is not displayed");
				   Assert.assertTrue(false);
			   }
			if(!getText(driver, By.xpath("//table[4]/tbody/tr[2]/td")).contains("Retention charges for cancellation of Booking ID")){
				   Reporter.log("Retention charges for cancellation of Booking ID text is not displayed");
				   Assert.assertTrue(false);
			   }
			if(!elementVisible(driver, By.xpath("//td[2]/strong"), 5)){
				   Reporter.log("Cacellation Price is not displayed");
				   Assert.assertTrue(false);
			   }
			String CancellationAmt  = getText(driver, By.xpath("//td[2]/strong"));
			Reporter.log("CancellationAmt in Debit Notes : "+CancellationAmt);
			
		return CancellationAmt;
		
	}

	}	
	