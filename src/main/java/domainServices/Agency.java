// Framework - Cleartrip Automation
// Version - Web Driver 2.0
// Creation Date - Nov, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All right reserved.
package domainServices;

import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import org.testng.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

	public class Agency extends AirCommonMethod {
	private static String Agency_Url, CTPhone_Url= null;	
	private String baseUrl = "http://"+common.value("host")+common.value("url")+"com";
	private String baseUrl_AE = "http://"+common.value("host")+common.value("url")+"ae";
	private String NBSite  = "BOI";
	public String HotelProcessingFee  = null;
	public String cookievalue_QA2 ="VWRf9RUFCNW5gImJG4L5aVCDIf%2B2bU0gRGrMHaQg2X9hPGTGegGvgBFdOL6%2BFYEBL5w%2BfbtxsaW%2BbtdHrc%2BWsZ7RHXUqbdAak%2FSwCCKL63K8tTGbJDCLrTfl%2FfoF3Isdrb%2F3FRjoJIwUdPY70S08TmYxK3iqZTkRXc8ytNa%2BBa00o14%2B8Nin2VlI5DMi37zE9drcaFIWZ%2F586SUc85K7iPTTHJc6BIHyKJvxRooyMW88099gslytqRg%2FXsizXTKmIRHbeIKgwl1ZlXn2iFdlBRiTo76vBBFonndgkyoCm6UE3xhVMMZfA9oKKybYz8LEF80gYlQCZJ6ura%2FpU0ZYrx5k0yzSPCbioK28bO4oHJVQppTn9WU3zuP%2BTJAZ8cW%2BWN8gWgz9m3w8Gpm974L1BUgW1RidIQROdO6571p8BMI3qUA93b94TJ6nIbslDsDESboOy5WSYNrTa%2BiHKpbRXpPDMcPuCeVHaG0lMH4Xe9Kz41Aod1M95v0DdTANIUDKbW3AuGrXRu12HruX0A3Wvof2JsJpc9GgOPLBMema7QJmtMpijW6xQDDnpIedIcrln4ctDAfwc3VlyRlLzSKXBdcQ1N7goRAwstR5Ze3FEbiZoqNmRCfuq2buuLOYqP7MCbGEUInKQKPVmtAYnCQ5zA%3D%3D";
	
	public String cookievalue_WWW = "fypgo3s3SmD4INfcy6l6bJ13dBBJnkcfxnIIJuSLr1uIyr13wis%2FziJeAqSJGFjnL5w%2BfbtxsaW%2BbtdHrc%2BWsScAb9aOVSPgea3fYMxLqnUIw26hiU0lUSBfq09MXavVPaEF%2FF0yiN4dX4A8gGUVh7%2FAT66hqEU7Laf6KSsiB9CTTsXexOBkLm7cPPqKUMuf";
	public String cookievalue_BETA = "fypgo3s3SmD4INfcy6l6bJ13dBBJnkcfxnIIJuSLr1uIyr13wis%2FziJeAqSJGFjnL5w%2BfbtxsaW%2BbtdHrc%2BWsScAb9aOVSPgea3fYMxLqnUIw26hiU0lUSBfq09MXavVPaEF%2FF0yiN4dX4A8gGUVh7%2FAT66hqEU7Laf6KSsiB9CTTsXexOBkLm7cPPqKUMuf";
	
	
	public String Agency_Url() throws Exception {
		if (common.value("host").contains("qa2") ) {
			Agency_Url = dataFile.value("Agency_QA2");
			
		} else if(common.value("host").contains("hf")) {
			Agency_Url = dataFile.value("Agency_HF");			
		} 
		else if(common.value("host").contains("www")) {
			Agency_Url = dataFile.value("Agency_Prod");			
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
			Reporter.log("Not a Script Issue an error occurred while processing this directive : Error is displayed");
			Assert.assertTrue(false);
			}
		
		
		if (!textPresent(driver, "Sign-in to your Cleartrip Account", 5)){
		/*if(!elementVisible(driver, getObject("Agency_SignIN_EmailID"), 50)) {
			Reporter.log("Agency SignIn Page is not displayed");
		*/
		if (common.value("host").contains("qa2")  ) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("Agency_QA2_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("Agency_QA2_Password"));
		}else if (common.value("host").contains("hf")  ) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("Agency_HF_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("Agency_HF_Password"));
		}
		
		else if(common.value("host").contains("www")) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("Agency_Prod_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("Agency_Prod_Password"));
		} else if(common.value("host").contains("beta")) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("Agency_Beta_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("Agency_Beta_Password"));
		} else if(common.value("host").contains("stg1")) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("Agency_Stg1_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("Agency_Stg1_Password"));
		}
		safeClick(driver, getObject("Agency_SignIN_Button"));
		
		}//}
		
		else if(elementVisible(driver, getObject("Agency_NewSignIN_EmailID"), 50)) {
			Reporter.log("New Agency SignIn Page is not displayed",true); 
			if(common.value("host").contains("www")) {
				safeType(driver, getObject("Agency_NewSignIN_EmailID"), dataFile.value("Agency_Prod_Username"));
				safeType(driver, getObject("Agency_NewSignIN_Password"), dataFile.value("Agency_Prod_Password"));
			}
			
			safeClick(driver, getObject("Agency_NewSignIN_Button"));
		}
		
	}
	
	
	public void agency_Plat_SignIn(RemoteWebDriver driver) throws Exception{
		driver.manage().deleteAllCookies();
		//driver.navigate().refresh();
		if(textPresent(driver, "an error occurred while processing this directive", 1)) {
			Reporter.log("Not a Script Issue an error occurred while processing this directive : Error is displayed");
			Assert.assertTrue(false);
			}
		
		
		if (!textPresent(driver, "Sign-in to your Cleartrip Account", 5)){
		/*if(!elementVisible(driver, getObject("Agency_SignIN_EmailID"), 50)) {
			Reporter.log("Agency SignIn Page is not displayed");
		*/
		if (common.value("host").contains("qa2")  ) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("Agency_QA2_Username"));
			safeClick(driver,getObject("Agency_SignIN_Password"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("Agency_QA2_Password"));
		}else if (common.value("host").contains("hf")  ) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("Agency_HF_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("Agency_HF_Password"));
		}
		
		else if(common.value("host").contains("www")) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("Agency_Prod_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("Agency_Prod_Password"));
		} else if(common.value("host").contains("beta")) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("Agency_Beta_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("Agency_Beta_Password"));
		} else if(common.value("host").contains("stg1")) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("Agency_Stg1_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("Agency_Stg1_Password"));
		}
		safeClick(driver, getObject("Agency_SignIN_Button"));
		
		}//}
		
		else if(elementVisible(driver, getObject("Agency_NewSignIN_EmailID"), 50)) {
			Reporter.log("New Agency SignIn Page is not displayed",true); 
			if(common.value("host").contains("www")) {
				safeType(driver, getObject("Agency_NewSignIN_EmailID"), dataFile.value("Agency_Prod_Username"));
				safeType(driver, getObject("Agency_NewSignIN_Password"), dataFile.value("Agency_Prod_Password"));
			}
			
			safeClick(driver, getObject("Agency_NewSignIN_Button"));
		}
		
	}
	

	public void CTPhone_SignIn(RemoteWebDriver driver) throws Exception{
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		if(!elementVisible(driver, getObject("Agency_SignIN_EmailID"), 50)) {
			Reporter.log("Not a Script Issue CTphone SignIn Page is not displayed");
		}
		if (common.value("host").contains("qa2")||common.value("host").contains("hf") ) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("CTPhone_QA2_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("CTPhone_QA2_Password"));
		} else if(common.value("host").contains("www")) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("CTPhone_Prod_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("CTPhone_Prod_Password"));
		} else if(common.value("host").contains("beta")) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("CTPhone_Beta_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("CTPhone_Beta_Password"));
		} else if(common.value("host").contains("stg1")) {
			safeType(driver, getObject("Agency_SignIN_EmailID"), dataFile.value("CTPhone_Stg1_Username"));
			safeType(driver, getObject("Agency_SignIN_Password"), dataFile.value("CTPhone_Stg1_Password"));
		}
		safeClick(driver, getObject("Agency_SignIN_Button"));
	}
	
	

	public String agencyAir_SrpUrl(RemoteWebDriver driver, String FromCity, String ToCity, String Adults, String Childrens, String Infants, String SearchType, String PrefAirline, int FromDate, int ToDate) throws Exception {
		driver.get(Agency_Url());
		agency_SignIn(driver);		
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Agency_Url();
		String URL_2 = null;
		if(SearchType.equalsIgnoreCase("DomOW")) {
			URL_2 = "/flights/results?from="+FromCity+"&to="+ToCity+"&depart_date="+Check_In_Date+"&adults="+Adults+"&childs="+Childrens+"&infants="+Infants+"&class=Economy&airline=&carrier="+PrefAirline+"&intl=n";
			}
		else if(SearchType.equalsIgnoreCase("IntlOW")) {
			URL_2 = "/flights/results?from="+FromCity+"&to="+ToCity+"&depart_date="+Check_In_Date+"&adults="+Adults+"&childs="+Childrens+"&infants="+Infants+"&class=Economy&airline=&carrier="+PrefAirline+"&intl=y";
			
		}		
		else if(SearchType.equalsIgnoreCase("DomRT")) {
			URL_2 = "/flights/results?from="+FromCity+"&to="+ToCity+"&depart_date="+Check_In_Date+"&return_date="+Check_Out_Date+"&adults="+Adults+"&childs="+Childrens+"&infants="+Infants+"&class=Economy&airline=&carrier="+PrefAirline+"&intl=n";
			}
		else if(SearchType.equalsIgnoreCase("IntlRT")) {
			URL_2 = "/flights/results?from="+FromCity+"&to="+ToCity+"&depart_date="+Check_In_Date+"&return_date="+Check_Out_Date+"&adults="+Adults+"&childs="+Childrens+"&infants="+Infants+"&class=Economy&airline=&carrier="+PrefAirline+"&intl=y";		
		}
		else {
			Reporter.log("Not a Script Issue Provide vaild Search type in SRP_URL method");
			Assert.assertTrue(false);
		}
		SRP_URL = domainURL+URL_2;
		Reporter.log("Srp URL : "+SRP_URL,true);
		return SRP_URL;		
	}

	public String ctPhoneAir_SrpUrl(RemoteWebDriver driver, String FromCity, String ToCity, String Adults, String Childrens, String Infants, String SearchType, String PrefAirline, int FromDate, int ToDate) throws Exception {
		driver.get(CTPhone_Url());
		CTPhone_SignIn(driver);		
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = CTPhone_Url();
		String URL_2 = null;
		if(SearchType.equalsIgnoreCase("DomOW")) {
			URL_2 = "/flights/results?from="+FromCity+"&to="+ToCity+"&depart_date="+Check_In_Date+"&adults="+Adults+"&childs="+Childrens+"&infants="+Infants+"&class=Economy&airline=&carrier="+PrefAirline+"&intl=n";
			}
		else if(SearchType.equalsIgnoreCase("IntlOW")) {
			URL_2 = "/flights/results?from="+FromCity+"&to="+ToCity+"&depart_date="+Check_In_Date+"&adults="+Adults+"&childs="+Childrens+"&infants="+Infants+"&class=Economy&airline=&carrier="+PrefAirline+"&intl=y";
			
		}		
		else if(SearchType.equalsIgnoreCase("DomRT")) {
			URL_2 = "/flights/results?from="+FromCity+"&to="+ToCity+"&depart_date="+Check_In_Date+"&return_date="+Check_Out_Date+"&adults="+Adults+"&childs="+Childrens+"&infants="+Infants+"&class=Economy&airline=&carrier="+PrefAirline+"&intl=n";
			}
		else if(SearchType.equalsIgnoreCase("IntlRT")) {
			URL_2 = "/flights/results?from="+FromCity+"&to="+ToCity+"&depart_date="+Check_In_Date+"&return_date="+Check_Out_Date+"&adults="+Adults+"&childs="+Childrens+"&infants="+Infants+"&class=Economy&airline=&carrier="+PrefAirline+"&intl=y";		
		}
		else {
			Reporter.log("Provide vaild Search type in SRP_URL method");
			Assert.assertTrue(false);
		}
		SRP_URL = domainURL+URL_2;
		Reporter.log("Srp URL : "+SRP_URL);
		return SRP_URL;		
	}


	public String agencyHotel_SrpUrl(RemoteWebDriver driver, String City, String State, String Country, String Adults, int FromDate, int ToDate) throws Exception {
		driver.get(Agency_Url());
		agency_SignIn(driver);		
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Agency_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		SRP_URL = domainURL+URL_2;
		driver.get(SRP_URL);
		Reporter.log("Srp URL : "+SRP_URL);
		return SRP_URL;		
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

	public void agencyAir_Oneway_Search(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date, String Adults,
		String Childrens, String Infants, String pref_airline) throws Exception{
		
		From_Date = getDate( "dd");
		From_Date = From_Date.substring(1);
		From_Date = "1"+From_Date;
		
		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), getObject("AirCom_HomePage_From_Ajax"), FromCity);
		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"), ToCity);
		selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")), getObject("AirCom_HomePage_Departure_NextMonth"), 2, From_Date);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);
		if (pref_airline != "") {
			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {
				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));
			}
			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"), getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);
		}
		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));
	}

	public ArrayList<String> agencyCom_DataVal_Dom_SRP(RemoteWebDriver driver, String FlightType,	ArrayList<String> SRP_Data) throws Exception, InterruptedException {
		if(!elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 60)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 10);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
		 }
					for(int i=0;i<=10; i++){
				if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), 1)){
					break;
				}
				else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
					break;
				}
				Thread.sleep(1000);
			}
		if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
					agencyNoResultsFound(driver);
		}
		
				logURL(driver);
				filterFlightsByLCCOrGDS1(driver, FlightType, 0);
				smartClick(driver, By.id("1_1_S2"));
				safeClick(driver, By.linkText("Flight itinerary"));
				elementVisible(driver, By.xpath("//li/div[2]"), 10, "Flight details : ");
				String flightDetails = getText(driver, By.xpath("//li/div[2]"));
				String timeDep = getText(driver, By.xpath("//li[2]/time/span[2]"));
				String timeArv = getText(driver, By.xpath("//li[4]/time/span[2]"));	
				String[] flightDetailsSplit = flightDetails.split("\n");
				String [] timeDepSplit = timeDep.split(" ");
				String timeDep1 = timeDepSplit[0];
				String timeDep2 = timeDepSplit[1];	
				String [] timeArvSplit = timeArv.split(" ");
				String timeArv1 = timeArvSplit[0];
				String timeArv2 = timeArvSplit[1];
						
				SRP_Data.add(flightDetailsSplit[0]); 												// Flight Name
				SRP_Data.add(flightDetailsSplit[1]); 												// Flight No
				SRP_Data.add(flightDetailsSplit[2]); 												// SeatType							
				SRP_Data.add(getText(driver, By.xpath("//th[8]"))); 						//Price
				SRP_Data.add(timeDepSplit[2]);														// Dep Year												
				SRP_Data.add(timeDep1+" "+timeDep2);											// Dep Date
				SRP_Data.add(timeArvSplit[2]);														// Arv Year												
				SRP_Data.add(timeArv1+" "+timeArv2);											// Arv Date

				Reporter.log("------------SRP Details -----------");
				Reporter.log("Flight Name : "+SRP_Data.get(0));
				Reporter.log("Flight No : "+SRP_Data.get(1));
				Reporter.log("Seat Type : "+SRP_Data.get(2));
				Reporter.log("Price : "+SRP_Data.get(3));
				Reporter.log("Arv time : "+timeArv);
				Reporter.log("Dep time : "+timeDep);
			
				safeClick(driver, getObject("Air_Agency_SRP_Oneway_BookButton"));
				return SRP_Data;
	}
	
	public ArrayList<String> agencyCom_DataVal_Intl_SRP(RemoteWebDriver driver, String FlightType,	ArrayList<String> SRP_Data) throws Exception, InterruptedException {
		if(!elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 60)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 10);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
		 }
					for(int i=0;i<=10; i++){
				if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Int_Oneway_BookButton"), 1)){
					break;
				}
				else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
					break;
				}
				Thread.sleep(1000);
			}
		if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
					agencyNoResultsFound(driver);
		}
		
				logURL(driver);
				safeClick(driver, By.linkText("Flight itinerary"));
				elementVisible(driver, By.xpath("//li/div[2]"), 10, "Flight details : ");
				String flightDetails = getText(driver, By.xpath("//li/div[2]"));
				String timeDep = getText(driver, By.xpath("//li[2]/time/span[2]"));
				String timeArv = getText(driver, By.xpath("//li[4]/time/span[2]"));	
				String[] flightDetailsSplit = flightDetails.split("\n");
				String [] timeDepSplit = timeDep.split(" ");
				String timeDep1 = timeDepSplit[0];
				String timeDep2 = timeDepSplit[1];	
				String [] timeArvSplit = timeArv.split(" ");
				String timeArv1 = timeArvSplit[0];
				String timeArv2 = timeArvSplit[1];
						
				SRP_Data.add(flightDetailsSplit[0]); 												// Flight Name
				SRP_Data.add(flightDetailsSplit[1]); 												// Flight No
				SRP_Data.add(flightDetailsSplit[2]); 												// SeatType							
				SRP_Data.add(getText(driver, By.xpath("//th[8]"))); 						//Price
				SRP_Data.add(timeDepSplit[2]);														// Dep Year												
				SRP_Data.add(timeDep1+" "+timeDep2);											// Dep Date
				SRP_Data.add(timeArvSplit[2]);														// Arv Year												
				SRP_Data.add(timeArv1+" "+timeArv2);											// Arv Date

				Reporter.log("------------SRP Details -----------");
				Reporter.log("Flight Name : "+SRP_Data.get(0));
				Reporter.log("Flight No : "+SRP_Data.get(1));
				Reporter.log("Seat Type : "+SRP_Data.get(2));
				Reporter.log("Price : "+SRP_Data.get(3));
				Reporter.log("Arv time : "+timeArv);
				Reporter.log("Dep time : "+timeDep);
				Reporter.log("DepYear srp: "+SRP_Data.get(4));
				Reporter.log("DepDate srp: "+SRP_Data.get(5));
				Reporter.log("ArvYear srp: "+SRP_Data.get(6));
				Reporter.log("ArvDate srp: "+SRP_Data.get(7));
						
				String Hold_Book_Button = getText(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
				if(Hold_Book_Button.contains("Hold")) {
					safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button[2]"));
				}else	safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
			
				return SRP_Data;
	}


	
	public ArrayList<String>  agencyCom_DataVal_TravellerPage(RemoteWebDriver driver, String Adults,String Childrens, String Infants, ArrayList<String> TravellerPage_Data)	throws Exception {
		elementVisible(driver, getObject("Air_Agency_TravellerPage_ContinueButton"), 20, "Traveller Page : ");
		agencyPax(driver, Adults, Childrens, Infants);
		String travellerFName = driver.findElement(By.id("adult1'sFirstName")).getAttribute("value");
		String travellerLName = driver.findElement(By.id("adult1'sLastName")).getAttribute("value");
		TravellerPage_Data.add(travellerFName+" "+travellerLName);
		
		Reporter.log("------------Traveller Page Details -----------");
		Reporter.log("Traveller Name : "+TravellerPage_Data.get(0));
		
		safeClick(driver, getObject("Air_Agency_TravellerPage_ContinueButton"));
		return TravellerPage_Data;
	}
	
	public ArrayList<String>  agencyCom_DataVal_TravellerPage_Intl(RemoteWebDriver driver, String Adults,String Childrens, String Infants, ArrayList<String> TravellerPage_Data)	throws Exception {
		elementVisible(driver, getObject("Air_Agency_TravellerPage_ContinueButton"), 20, "Traveller Page : ");
		agencyPax(driver, Adults, Childrens, Infants);
		String travellerFName = driver.findElement(By.id("adult1'sFirstName")).getAttribute("value");
		String travellerLName = driver.findElement(By.id("adult1'sLastName")).getAttribute("value");
		TravellerPage_Data.add(travellerFName+" "+travellerLName);
		
		Reporter.log("------------Traveller Page Details -----------");
		Reporter.log("Traveller Name : "+TravellerPage_Data.get(0));
		
		if(elementVisible(driver, getObject("AirCorpCom_TravellerPage_Ajax"), 1)) {
			safeClick(driver, getObject("AirCorpCom_TravellerPage_Ajax"));
		}
		safeClick(driver, getObject("Air_Agency_TravellerPage_Int_ContinueButton"));
		
		return TravellerPage_Data;
	}
	

	public ArrayList<String> agencyCom_DataVal_PaymentPage(RemoteWebDriver driver, ArrayList<String> SRP_Data, ArrayList<String> TravellerPage_Data,  ArrayList<String> PaymentPage_Data) throws Exception, 	InterruptedException {
		if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 30)){
					Reporter.log("Not a Script Issue PaymentPage is not displayed");
				}
				textPresent(driver, "How would you like to pay?", 20);					
				PaymentPage_Data.add(getText(driver, By.xpath("//div[2]/dl/dd[1]")));
				PaymentPage_Data.add(getText(driver, By.xpath("//div[2]/dl/dd[2]")));
				String Price = getText(driver, By.id("formTotal"));
				PaymentPage_Data.add(Price.replace("Rs. ", ""));
				PaymentPage_Data.add(getText(driver, By.xpath("//div[2]/dl/dd[3]")));
				PaymentPage_Data.add(getText(driver, By.xpath("//div[2]/dl/dd[4]")));
				PaymentPage_Data.add(getText(driver, By.xpath("//div[2]/div/dl/dd")));
				Reporter.log("------------Payment Page Details -----------");
				Reporter.log("Flight Name : "+PaymentPage_Data.get(0));
				Reporter.log("Flight No : "+PaymentPage_Data.get(1));
				Reporter.log("Price : "+PaymentPage_Data.get(2));
				Reporter.log("Dep Time : "+PaymentPage_Data.get(3));
				Reporter.log("Arv Time : "+PaymentPage_Data.get(4));
				Reporter.log("Traveller Payment : "+PaymentPage_Data.get(5));
				
				if(!PaymentPage_Data.get(1).contains(SRP_Data.get(1))) {
				Reporter.log("Flight No in SRP & Payment page don't match 'Flight No SRP -' "+SRP_Data.get(1)+" 'Flight No Payment Step -' "+PaymentPage_Data.get(1));
					Assert.assertTrue(false);
				}
				if(!SRP_Data.get(3).contains(PaymentPage_Data.get(2))) {
					Reporter.log("Price in SRP & Payment page don't match 'Price in SRP -' "+SRP_Data.get(3)+" 'Price in Payment Step -' "+PaymentPage_Data.get(2));
					//Assert.assertTrue(false);
				}
				if(!TravellerPage_Data.get(0).contains(PaymentPage_Data.get(5))) {
					Reporter.log("Traveller Name in Traveller Page & Payment page don't match 'Traveller Name in Traveller -' "+TravellerPage_Data.get(0)+" ' Payment Step -' "+PaymentPage_Data.get(5));
					Assert.assertTrue(false);
				}
				if(!SRP_Data.get(0).contains(PaymentPage_Data.get(0))) {
					Reporter.log("Flight Name in SRP & Payment page don't match 'Flight Name in Traveller -' "+SRP_Data.get(0)+" ' Payment Step -' "+PaymentPage_Data.get(0));
					Assert.assertTrue(false);
				}
				if(!(PaymentPage_Data.get(3).contains(SRP_Data.get(4)) && PaymentPage_Data.get(3).contains(SRP_Data.get(5)))) {
					Reporter.log("Flight Dep Date in SRP & Payment page don't match 'Flight Dep in SRP -' "+SRP_Data.get(4)+" ' Payment Step -' "+PaymentPage_Data.get(4));
					Assert.assertTrue(false);
				}
				if(!(PaymentPage_Data.get(4).contains(SRP_Data.get(6)) && PaymentPage_Data.get(4).contains(SRP_Data.get(7)))) {
					Reporter.log("Flight Arv Date in SRP & Payment page don't match 'Flight Arv in SRP -' "+SRP_Data.get(6)+" ' Payment Step -' "+PaymentPage_Data.get(4));
					Assert.assertTrue(false);
				}
				return PaymentPage_Data;
	}

	
	public ArrayList<String> agencyCom_DataVal_ConfirmationPage(RemoteWebDriver driver, ArrayList<String> SRP_Data,	ArrayList<String> PaymentPage_Data, ArrayList<String> ConfirmationPage_Data, String TripID) throws Exception {
		if(MakePaymentOnlyInQA2){
		elementVisible(driver, By.xpath("//dd"), 50);

		String Price = getText(driver, By.cssSelector("dd.price_total"));
		ConfirmationPage_Data.add(getText(driver, By.xpath("//dd")));
		ConfirmationPage_Data.add(getText(driver, By.xpath("//dd[2]")));
		ConfirmationPage_Data.add(Price.replace("Rs. ", ""));
		ConfirmationPage_Data.add(getText(driver, By.xpath("//div[5]/dl/dd[3]")));
		ConfirmationPage_Data.add(getText(driver, By.xpath("//div[2]/div[3]/dl/dd")));
		ConfirmationPage_Data.add(getText(driver, By.xpath("//div[4]/dl/dd[1]")));
		ConfirmationPage_Data.add(getText(driver, By.xpath("//div[4]/dl/dd[2]")));
	

		Reporter.log("------------Confirmation Page Details -----------");
		Reporter.log("Flight No : "+ConfirmationPage_Data.get(0));
		Reporter.log("Flight Name : "+ConfirmationPage_Data.get(1));
		Reporter.log("Price Confirmation Page : "+ConfirmationPage_Data.get(2));
		Reporter.log("SeatType : "+ConfirmationPage_Data.get(3));
		Reporter.log("Traveller Name : "+ConfirmationPage_Data.get(4));
		Reporter.log("Dep Time : "+ConfirmationPage_Data.get(5));
		Reporter.log("Arv Time : "+ConfirmationPage_Data.get(6));		

		if(!ConfirmationPage_Data.get(0).contains(SRP_Data.get(1))) {
			Reporter.log("Flight No in Confirmation & SRP page don't match 'Flight No in SRP -' "+SRP_Data.get(1)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(0));
		//	Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(1).contains(SRP_Data.get(0))) {
			Reporter.log("Flight Name in Confirmation & SRP page don't match 'Flight Name in SRP -' "+SRP_Data.get(0)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(1));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(3).contains(SRP_Data.get(2))) {
			Reporter.log("Flight SeatType in Confirmation & SRP page don't match 'Flight SeatType in SRP -' "+SRP_Data.get(0)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(3));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(4).contains(PaymentPage_Data.get(4))) {
			Reporter.log("Traveller Name in Confirmation & SRP page don't match 'Traveller Name in payment page -' "+PaymentPage_Data.get(5)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(4));
			//Assert.assertTrue(false);
		}
		
		if(!(ConfirmationPage_Data.get(5).contains(SRP_Data.get(4)) && ConfirmationPage_Data.get(5).contains(SRP_Data.get(5)))) {
			Reporter.log("Flight Dep in Confirmation & SRP page don't match 'Flight Dep in SRP page -' "+SRP_Data.get(5)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(5));
			Assert.assertTrue(false);
		}
		
		if(!(ConfirmationPage_Data.get(6).contains(SRP_Data.get(6)) && ConfirmationPage_Data.get(6).contains(SRP_Data.get(7)))){
			Reporter.log("Flight Arv in Confirmation & SRP page don't match 'Flight Arv in SRP page -' "+SRP_Data.get(6)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(7));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(2).contains(PaymentPage_Data.get(2))){
			Reporter.log("Price in Confirmation & SRP page don't match 'Price in Payment page -' "+PaymentPage_Data.get(2)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(2));
			Assert.assertTrue(false);
		}
		
		safeClick(driver, getObject("Air_Agency_Confirmation_Trips_Link"));
		elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Header"), 60);
		elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), 60);
		safeType(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), TripID);
		safeClick(driver, getObject("Air_Agency_Trips_Page_Search_TripID_Submit"));
		safeClick(driver, getObject("Air_Agency_Trips_Page_Trip_Link"));
		textPresent(driver, TripID, 5);
		elementVisible(driver, By.linkText("Pricing & payment details"), 30);
		safeClick(driver, By.linkText("Pricing & payment details"));
		
		for(int i=1; i<10; i++) {
			String costNameXpath = "//dt["+i+"]";
			String costName = getText(driver, By.xpath(costNameXpath));
			if(costName.contains("Cost to agency")) {

				String costPriceXpath = "//dd["+i+"]";

				String costToAgency = getText(driver, By.xpath(costPriceXpath));
				ConfirmationPage_Data.add(costToAgency);
				break;
			}
		}
		Reporter.log("Cost to Agecny : "+ConfirmationPage_Data.get(7));
		
	}
		return ConfirmationPage_Data;
	}

	
	public ArrayList<String> agencyCom_DataVal_HQ(RemoteWebDriver driver, ArrayList<String> SRP_Data,	ArrayList<String> PaymentPage_Data, ArrayList<String> ConfirmationPage_Data, ArrayList<String> HQ_Data, String TripID)
			throws Exception, InterruptedException {
		if(MakePaymentOnlyInQA2){
		driver.manage().deleteAllCookies();
		if(common.value("host").equals("qa2")){
			driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);
			refreshPage(driver);
		} else if(common.value("host").equals("hf")){
			driver.get("https://hf.cleartrip.com/hq/trips/"+TripID);
			refreshPage(driver);
		}
			
		
		
		//hotelCom_Open_TripID_HQ(driver, "Q1610210012");
		safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
		safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
		safeClick(driver, By.id("signInButton"));
		Thread.sleep(5000);

		elementVisible(driver, By.cssSelector("dd.total > strong"), 20);
		if(common.value("host").equals("qa2")){
			driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);
			refreshPage(driver);
		} else if(common.value("host").equals("hf")){
			driver.get("https://hf.cleartrip.com/hq/trips/"+TripID);
			refreshPage(driver);
		}
		elementVisible(driver, By.cssSelector("dd.total > strong"), 20);
		String Price = getText(driver, By.cssSelector("dd.total > strong"));
		HQ_Data.add(Price.replace("Rs. ", ""));
		HQ_Data.add(getText(driver, By.xpath("//tr[4]/td")));
		HQ_Data.add(getText(driver, By.xpath("//td[2]")));
		HQ_Data.add(getText(driver, By.xpath("//td[4]")));
		HQ_Data.add(getText(driver, By.xpath("//tr[3]/td[3]")));
		HQ_Data.add(getText(driver, By.xpath("//tr[3]/td[8]")));
		HQ_Data.add(getText(driver, By.xpath("//td[3]")));
		HQ_Data.add(getText(driver, By.xpath("//td[7]")));
		safeClick(driver, By.id("tab_2"));
		HQ_Data.add(getText(driver, By.xpath("//form/div/div/table/tbody/tr/th/strong")));
		
		Reporter.log("------------HQ Page Details -----------");
		Reporter.log("Price : "+HQ_Data.get(0));
		Reporter.log("Flight Arv : "+HQ_Data.get(1));
		Reporter.log("Flight Dep : "+HQ_Data.get(2));
		Reporter.log("Flight No : "+HQ_Data.get(3));
		Reporter.log("Flight Name : "+HQ_Data.get(6));
		Reporter.log("SeatType : "+HQ_Data.get(7));
		Reporter.log("PNR : "+HQ_Data.get(4));
		Reporter.log("Ticket No : "+HQ_Data.get(5));
		Reporter.log("Traveller Name : "+HQ_Data.get(8));
					
	
		if(!ConfirmationPage_Data.get(7).contains(HQ_Data.get(0))) {
			Reporter.log("Price in Confirmation & HQ page don't match 'Price in Confirmation page -' "+ConfirmationPage_Data.get(7)+" ' HQ Page -' "+HQ_Data.get(0));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(3).contains(HQ_Data.get(7))) {
			Reporter.log("SeatType in Confirmation & HQ page don't match 'SeatType Confirmation page -' "+ConfirmationPage_Data.get(3)+" ' HQ Page -' "+HQ_Data.get(7));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(0).contains(HQ_Data.get(3))) {
			Reporter.log("Flight No in Confirmation & HQ page don't match 'Flight No Confirmation page -' "+ConfirmationPage_Data.get(0)+" ' HQ Page -' "+HQ_Data.get(3));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(1).contains(HQ_Data.get(6))) {
			Reporter.log("Flight Name in Confirmation & HQ page don't match 'Flight Name Confirmation page -' "+ConfirmationPage_Data.get(1)+" ' HQ Page -' "+HQ_Data.get(6));
			Assert.assertTrue(false);
		}			

		if(!HQ_Data.get(8).contains(PaymentPage_Data.get(5))) {
			Reporter.log("Traveller Name in Payment page & HQ page don't match 'Traveller Name Payment page -' "+PaymentPage_Data.get(5)+" ' HQ Page -' "+HQ_Data.get(8));
		//	Assert.assertTrue(false);
		}
		
		if(!(HQ_Data.get(1).contains(SRP_Data.get(5)))) {
			Reporter.log("Flight Arv in SRP & HQ page don't match 'Flight Arv SRP page -' "+SRP_Data.get(5)+" ' HQ Page -' "+HQ_Data.get(1));
		//	Assert.assertTrue(false);
		}
		
		if(!(HQ_Data.get(2).contains(SRP_Data.get(7)))) {
			Reporter.log("Flight Dep in SRP & HQ page don't match 'Flight Dep SRP page -' "+SRP_Data.get(7)+" ' HQ Page -' "+HQ_Data.get(2));
			//Assert.assertTrue(false);
		}
		}
		return HQ_Data;
	}

	public ArrayList<String> agencyCom_DataVal_HQ_Intl(RemoteWebDriver driver, ArrayList<String> SRP_Data,	ArrayList<String> PaymentPage_Data, ArrayList<String> ConfirmationPage_Data, ArrayList<String> HQ_Data, String TripID)
			throws Exception, InterruptedException {
		driver.manage().deleteAllCookies();
		if(common.value("host").equals("qa2")){
			driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);
			refreshPage(driver);
		} else if(common.value("host").equals("hf")){
			driver.get("https://hf.cleartrip.com/hq/trips/"+TripID);
			refreshPage(driver);
		}
		safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
		safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
		safeClick(driver, By.id("signInButton"));
		Thread.sleep(5000);
		if(common.value("host").equals("qa2")){
			driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);
			refreshPage(driver);
		} else if(common.value("host").equals("hf")){
			driver.get("https://hf.cleartrip.com/hq/trips/"+TripID);
			refreshPage(driver);
		}
		elementVisible(driver, By.cssSelector("dd.total > strong"), 20);
		String Price = getText(driver, By.cssSelector("dd.total > strong"));
		HQ_Data.add(Price.replace("Rs. ", ""));
		HQ_Data.add(getText(driver, By.xpath("//td[2]")));
		HQ_Data.add(getText(driver, By.xpath("//td")));
		HQ_Data.add(getText(driver, By.xpath("//td[4]")));
		HQ_Data.add(getText(driver, By.xpath("//table[2]/tbody/tr[3]/td[7]")));
		HQ_Data.add(getText(driver, By.xpath("//table[2]/tbody/tr[3]/td[8]")));
		HQ_Data.add(getText(driver, By.xpath("//td[3]")));
		String SeatType = getText(driver, By.xpath("//th[7]"));
		String SeatTypeXpath2 = null;
		if(SeatType.contains("Class")) {
			SeatTypeXpath2 = "//td[7]";
		} else SeatTypeXpath2 = "//td[8]";

		HQ_Data.add(getText(driver, By.xpath(SeatTypeXpath2)));
		//HQ_Data.add(getText(driver, By.xpath("//td[8]")));
		safeClick(driver, By.id("tab_2"));
		HQ_Data.add(getText(driver, By.xpath("//form/div/div/table/tbody/tr/th/strong")));
		
		Reporter.log("------------HQ Page Details -----------");
		Reporter.log("Price : "+HQ_Data.get(0));
		Reporter.log("Flight Arv : "+HQ_Data.get(1));
		Reporter.log("Flight Dep : "+HQ_Data.get(2));
		Reporter.log("Flight No : "+HQ_Data.get(3));
		Reporter.log("Flight Name : "+HQ_Data.get(6));
		Reporter.log("SeatType : "+HQ_Data.get(7));
	
		Reporter.log("PNR : "+HQ_Data.get(4));
		Reporter.log("Ticket No : "+HQ_Data.get(5));
		Reporter.log("Traveller Name : "+HQ_Data.get(8));
	
		if(!ConfirmationPage_Data.get(7).contains(HQ_Data.get(0))) {
			Reporter.log("Price in Confirmation & HQ page don't match 'Price in Confirmation page -' "+ConfirmationPage_Data.get(7)+" ' HQ Page -' "+HQ_Data.get(0));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(3).contains(HQ_Data.get(7))) {
			Reporter.log("SeatType in Confirmation & HQ page don't match 'SeatType Confirmation page -' "+ConfirmationPage_Data.get(3)+" ' HQ Page -' "+HQ_Data.get(7));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(0).contains(HQ_Data.get(3))) {
			Reporter.log("Flight No in Confirmation & HQ page don't match 'Flight No Confirmation page -' "+ConfirmationPage_Data.get(0)+" ' HQ Page -' "+HQ_Data.get(3));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(1).contains(HQ_Data.get(6))) {
			Reporter.log("Flight Name in Confirmation & HQ page don't match 'Flight Name Confirmation page -' "+ConfirmationPage_Data.get(1)+" ' HQ Page -' "+HQ_Data.get(6));
			Assert.assertTrue(false);
		}			

		if(!HQ_Data.get(8).contains(PaymentPage_Data.get(5))) {
			Reporter.log("Traveller Name in Payment page & HQ page don't match 'Traveller Name Payment page -' "+PaymentPage_Data.get(5)+" ' HQ Page -' "+HQ_Data.get(8));
			Assert.assertTrue(false);
		}
		
		if(!(HQ_Data.get(1).contains(SRP_Data.get(5)))) {
			Reporter.log("Flight Arv in SRP & HQ page don't match 'Flight Arv SRP page -' "+SRP_Data.get(5)+" ' HQ Page -' "+HQ_Data.get(1));
			//Assert.assertTrue(false);
		}
		
		if(!(HQ_Data.get(2).contains(SRP_Data.get(7)))) {
			Reporter.log("Flight Dep in SRP & HQ page don't match 'Flight Dep SRP page -' "+SRP_Data.get(7)+" ' HQ Page -' "+HQ_Data.get(2));
//			Assert.assertTrue(false);
		}
		return HQ_Data;
	}

	
	public void agencyAir_RT_Search(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date, String To_Date, String Adults,
		String Childrens, String Infants, String pref_airline) throws Exception{
		From_Date = getDate( "dd");
		From_Date = From_Date.substring(1);
		From_Date = "1"+From_Date;
		int DateInt = Integer.parseInt(From_Date);
		DateInt = DateInt+1;
		To_Date = Integer.toString(DateInt);
		safeClick(driver, getObject("AirCom_HomePage_Roundtrip_RadioButton"));
		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), getObject("AirCom_HomePage_From_Ajax"), FromCity);
		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"), ToCity);
		selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")), getObject("AirCom_HomePage_Departure_NextMonth"), 2, From_Date);
		safeClick(driver, getObject("AirCom_HomePage_Roundtrip_RadioButton"));
		selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Return_Cal")), getObject("AirCom_HomePage_Departure_NextMonth"), 0, To_Date);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);
		if (pref_airline != "") {
			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {
				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));
			}
			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"), getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);
		}
		logURL(driver);
		safeClick(driver,By.id("SearchBtn"));
		//safeClick(driver, getObject("AirCom_HomePage_Search_Button"));		
	}

	public void agencyAir_MC_Search(RemoteWebDriver driver, int numberOfSectors, String[] FromCity, String[] ToCity, String[] dateSet, String Adults, String Childrens, String Infants, String pref_airline) throws Exception {
		safeClick(driver, getObject("AirCom_HomePage_MultiCity_RadioButton"));
		if (numberOfSectors == 2) {
			safeClick(driver, getObject("AirCom_HomePage_MultiCity_Third_Segment_Delete_Button"));
		}
		for (int i = 1; i <= numberOfSectors; i++) {
			safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox") + i),
					getObject("AirCom_HomePage_Multicity_From_Ajax" + i), FromCity[i]);
			safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox") + i),
					getObject("AirCom_HomePage_Multicity_To_Ajax" + i), ToCity[i]);
			if (i == 1)
				selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal") + i),
						getObject("AirCom_HomePage_Departure_NextMonth"), 3, dateSet[i]);
			else
				selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal") + i),
						getObject("AirCom_HomePage_Departure_NextMonth"), 0, dateSet[i]);
		}
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);
		if (pref_airline != "") {
			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {
				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));
			}
			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),
					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);
		}
		logURL(driver);
		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));
	}

	public void agencyNoResultsFound(RemoteWebDriver driver) throws Exception {
		if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 20)){
				for(int i=0; i<=5; i++){
					if(elementVisible(driver, getObject("Air_Agency_SRP_Flights_Research"), 10)){
						refreshPage(driver);
						elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
					} else break;
				}
			}
	}

	public void agencyAir_SRP_Reload(RemoteWebDriver driver, By by) throws Exception{
		for(int i=0;i<=10;i++) {
			if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Not a Script Issue Displaying Sorry, our system is acting up : message is displayed ion SRP");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 2)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1);
			}
			else if(elementVisible(driver, by, 1)) {
				break;
			}
		}
		
		//elementPresent_Time(driver, by, 30);
		for(int i=0;i<=20; i++){
			if(elementPresent_Time(driver, by, 1)){
				break;
			}
			else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
				break;
			}
			Thread.sleep(1000);
		}
			/*if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
				agencyNoResultsFound(driver);
				}*/
	
			logURL(driver);
			if(elementPresent_Time(driver, by, 5)){
				safeClick(driver, by);
		} 
		else {
			Reporter.log("Not a Script Issue SRP Book Button is not displayed");
			Assert.assertTrue(false);
			}
	}
	public boolean filterFlightsOnSrpByListOfAirlines(RemoteWebDriver driver, ArrayList<String> filterByAirlines) throws Exception {
		
		Reporter.log("Filtering SRP for airlines : " + filterByAirlines, true);
		List<WebElement> flights = null;

		if(filterByAirlines.isEmpty())
		{
			Reporter.log("Airlines to be filtered not specified! Hence not filtering", true);
			return false;
		}

		if (waitElement(driver,By.xpath("//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"), 1)) 
		{
			flights = driver.findElements(By.xpath("//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"));
		}
		else 
		{
			Reporter.log("Not able to locate list of available flight names.", true);
			assertTrue("Failure! Not able to locate list of available flight names.", false);
		}

		for(int i=0;i<flights.size();i++)
		{
			//Reporter.log(flights.get(i).getText(), true);
			if(flights.get(i).getText().equalsIgnoreCase("show multi-airline itineraries"))
			{
				Reporter.log("Clicking on Multi Airline itineraries", false);
				flights.get(i).click();
				continue;
			}
			else
			{
				if(filterByAirlines.contains(flights.get(i).getText()))
				{
					continue;
				}
				else
				{
					try{
						int j= i + 1;
					scrollToElement(driver, By.xpath("(//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["+j+"]/../input"));
					safeClick(driver, By.xpath("(//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["+j+"]/../input"));
					}
					catch(Exception e)
					{
						Reporter.log("Element not found / clickable. Continuing to next element", true);
					}
				}
			}
		}

		if (checkWarning(driver))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void agencyAir_SRP_Reload_IntRT(RemoteWebDriver driver) throws Exception{
		ArrayList<String> filterByAirlines=new ArrayList<String>();
		filterByAirlines.add("SpiceJet");
		filterByAirlines.add("GoAir");
		filterByAirlines.add("Vistara");
		filterByAirlines.add("IndiGo");
		filterByAirlines.add("Jet Airways");
		filterByAirlines.add("Indigo");
		filterByAirlines.add("Air India");
		filterByAirlines.add("Air Asia");
		for(int i=0;i<=10;i++) {
			if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Sorry, our system is acting up : message is displayed ion SRP");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 2)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1);
			}
			else if(elementVisible(driver, By.xpath("//td[3]/button"), 1)) {
				break;
			}
		}
		
		//elementPresent_Time(driver, by, 30);
		for(int i=0;i<=20; i++){
			if(elementPresent_Time(driver, By.xpath("//td[3]/button"), 1)){
				break;
			}
			else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
				break;
			}
			Thread.sleep(1000);
		}
			/*if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
				agencyNoResultsFound(driver);
				}*/
	
			logURL(driver);
			//filterFlightsOnSrpByListOfAirlines(driver,filterByAirlines);
			if(elementVisible(driver, By.xpath("//button[2]"), 5)){
				safeClick(driver, By.xpath("//button[2]"));
			}
			else safeClick(driver, By.xpath("//td[3]/button"));
			/*String button1 = getText(driver, By.xpath("//td[3]/button"));
			System.out.println("buuton name : "+button1);
			for(int i = 0 ; i<=2; i++){
			if(button1.contains("Book")){
				//safeClick(driver, By.xpath("//td[3]/button"));
				System.out.println("Click Book no Hold button");
			}
			else {
				//safeClick(driver, By.xpath("//button[2]"));
				System.out.println("Click Book Hold button is Availbale");
			}
			
			}*/
			
			/*if(elementPresent_Time(driver, by, 5)){
				safeClick(driver, by);
		} 
		else {
			Reporter.log("SRP Book Button is not displayed");
			Assert.assertTrue(false);
			}*/
	}
	
	
	public void agencyAir_SRP_Reload(RemoteWebDriver driver, By by, String Flight_Type) throws Exception{
		for(int i=0;i<=10;i++) {
			if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Not a Script Issue displaying Sorry, our system is acting up : message is displayed ion SRP");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 2)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1);
			}
			else if(elementVisible(driver, by, 1)) {
				break;
			}
		}
		//elementPresent_Time(driver, by, 30);
		for(int i=0;i<=20; i++){
			if(elementPresent_Time(driver, by, 1)){
				break;
			}
			else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
				break;
			}
			Thread.sleep(1000);
		}
			if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
				agencyNoResultsFound(driver);
				}
			filterFlightsByLCCOrGDS1(driver, Flight_Type, 0);
			logURL(driver);
			if(elementPresent_Time(driver, by, 5)){
				safeClick(driver, by);
		} 
		else {
			Reporter.log("Not a Script Issue SRP Book Button is not displayed");
			Assert.assertTrue(false);
			}
	}
	
	
	
	public void agencyAir_SRP_Domestic_Oneway(RemoteWebDriver driver) throws Exception {
		Thread.sleep(2000);
		agencyAir_SRP_Reload(driver, getObject("Air_Agency_SRP_Oneway_BookButton"));
		
	}
	

	public void agencyAir_SRP_Domestic_Oneway(RemoteWebDriver driver, String Flight_Type) throws Exception {
		Thread.sleep(2000);
		agencyAir_SRP_Reload(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), Flight_Type);
		
	}
	
	public void agencyAir_SRP_Int_Oneway(RemoteWebDriver driver) throws Exception {
		Thread.sleep(10000);
		refreshPage(driver);
		agencyAir_SRP_Reload(driver, getObject("Air_Agency_SRP_Int_Oneway_BookButton"));
	
	}

	public void agencyAir_SRP_Domestic_RT(RemoteWebDriver driver) throws Exception {
		Thread.sleep(5000);
		//refreshPage(driver);
		agencyAir_SRP_Reload(driver, getObject("Air_Agency_SRP_RT_BookButton"));
	
	}
	
	public void agencyAir_SRP_Intl_RT(RemoteWebDriver driver) throws Exception {
		Thread.sleep(15000);
		//refreshPage(driver);
		//agencyAir_SRP_Reload(driver, getObject("Air_Agency_SRP_Int_RT_BookButton"));
		agencyAir_SRP_Reload_IntRT(driver);
		
		/*if(!elementVisible(driver, getObject("Air_Agency_SRP_Int_RT_BookButton"), 50)){
		 * 
		agencyNoResultsFound(driver);
		}
		elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 20);
		if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Int_RT_BookButton"), 50)){
		safeClick(driver, getObject("Air_Agency_SRP_Int_RT_BookButton")); 
		}
		else {
			refreshPage(driver);
			Reporter.log("Book button is not displayed");
			agencyNoResultsFound(driver);
			if(elementVisible(driver, getObject("Air_Agency_SRP_Int_RT_BookButton"), 20)){
				safeClick(driver, getObject("Air_Agency_SRP_Int_RT_BookButton"));
			}  else Assert.assertTrue(false);
		}*/
	}
	
	public void agencyAir_SRP_Domestic_SPL_RT(RemoteWebDriver driver) throws Exception {
		refreshPage(driver);
		if(!elementVisible(driver, getObject("Air_Agency_SRP_RT_BookButton"), 50)){
		agencyNoResultsFound(driver);
		}
		elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 20);
		elementPresent_Time(driver, getObject("Air_Agency_SRP_RT_BookButton"), 50);
		agencyAirUncheck_Jetconnect_Air(driver);
		if(elementVisible(driver, By.xpath("//table/td[2]/a"), 20)){
			for(int i=2; i<=5; i++){
				String Spl_RT_XPath = "//td["+i+"]/a/span/img" ;
					if(elementPresent_Time(driver, By.xpath(Spl_RT_XPath), 1)){
					//String Attribute = driver.findElement(By.xpath(Spl_RT_XPath)).getAttribute("title").toString();
					String Attribute = getAttribute(driver, By.xpath(Spl_RT_XPath), "title");
					//System.out.println(Attribute);
						if(Attribute.contains("IndiGo")){
							safeClick(driver, By.xpath(Spl_RT_XPath));
							break ;
						} 
					}
			}
		} 
		String Onward_Flight_Name = getText(driver, By.xpath("//div[2]/div/div/strong"));
		String Return_Flight_Name = getText(driver, By.xpath("//div[2]/div[2]/div/strong"));
		if(!Onward_Flight_Name.equals(Return_Flight_Name)) {
			safeClick(driver, By.xpath("//td[2]/a/strong"));
		}
		logURL(driver);
		safeClick(driver, getObject("Air_Agency_SRP_RT_BookButton"));
	}
	
	public void agencyAirUncheck_Jetconnect_Air(RemoteWebDriver driver)
			throws Exception {
		if(elementPresent_Time(driver,  By.id("1_1_9W"), 1)){
			UnChecking_Checkbox(driver, By.id("1_1_9W"));
		}
		if(elementPresent_Time(driver,  By.id("1_1_S2"), 1)){
			UnChecking_Checkbox(driver, By.id("1_1_S2"));
		}
	}
	
	public void agencyAir_SRP_Int_Hold_Oneway(RemoteWebDriver driver) throws InterruptedException, Exception {
		Thread.sleep(15000);	
		refreshPage(driver);

		if(textPresent(driver, "Sorry, our system is acting up", 10)) {
			Reporter.log("Not a Script Issue Displaying  Sorry, our system is acting up : message is displayed ion SRP");
			Assert.assertTrue(false);
		}
			if(!elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 50)){
			agencyNoResultsFound(driver);
			}
			elementVisible(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), 50);
			for(int i=1; i<=16; i++){
				Thread.sleep(1000);
				String Hold_Xpath = "//li["+i+"]/table/tbody/tr[2]/td[3]/button";
			if(elementVisible(driver, By.xpath(Hold_Xpath), 1)){
				String Hold_Button = getText(driver, By.xpath(Hold_Xpath));
				if(Hold_Button.contains("Hold")){
					safeClick(driver, By.xpath(Hold_Xpath));
					break ; 
				} 
				
			} else if(i==17){
				Reporter.log("Not a Script Issue Hold button is not displayed");
			}
			} 
	}
	
	public void agencyAir_SRP_Domestic_MC(RemoteWebDriver driver) throws Exception {
		Thread.sleep(15000);
		//refreshPage(driver);
		for(int i=0;i<=10;i++) {
			if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Not a Script Issue  Displaying Sorry, our system is acting up : message is displayed ion SRP");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 1)){
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1);
			}
			else if(elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1)){
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1);
			}
			else if(elementVisible(driver, getObject("Air_Agency_SRP_RT_BookButton"), 1)) {
				break;
			}
		}
		
		//elementPresent_Time(driver, by, 30);
		for(int i=0;i<=10; i++){
			if(elementPresent_Time(driver, getObject("Air_Agency_SRP_RT_BookButton"), 1)){
				break;
			}
			else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
				break;
			}
			Thread.sleep(1000);
		}
			if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
				agencyNoResultsFound(driver);
				}
	
			logURL(driver);
			smartClick(driver, By.xpath("//li[2]/div"));
			smartClick(driver, By.xpath("//div[2]/nav/ul/li[2]/div"));
			if(elementPresent_Time(driver, getObject("Air_Agency_SRP_RT_BookButton"), 5)){
				safeClick(driver, getObject("Air_Agency_SRP_RT_BookButton"));
		} 
		else {
			Reporter.log("Not a Script Issue SRP Book Button is not displayed");
			Assert.assertTrue(false);
			}
		
	}	
	
	public void agencyAir_SRP_Intl_MC(RemoteWebDriver driver) throws Exception {
		Thread.sleep(15000);
		refreshPage(driver);

		//agencyAir_SRP_Reload(driver, getObject("Air_Agency_SRP_Int_MC_BookButton"));
		
		for(int i=0;i<=10;i++) {
			if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Not a Script Issue Displaying Sorry, our system is acting up : message is displayed ion SRP");
				Assert.assertTrue(false);
				}
			if(!elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 2)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 1);
			}
			else if(elementVisible(driver, getObject("Air_Agency_SRP_Int_MC_HoldButton"), 1)) {
				break;
			}
			else if(elementVisible(driver, getObject("Air_Agency_SRP_Int_MC_BookButton"), 1)) {
				break;
			}
		}
		if(getText(driver, getObject("Air_Agency_SRP_Int_MC_BookButton")).contains("Book")){
			safeClick(driver, getObject("Air_Agency_SRP_Int_MC_BookButton"));
			}
		if(elementVisible(driver, getObject("Air_Agency_SRP_Int_MC_HoldButton"), 1, "")){
		if(getText(driver, getObject("Air_Agency_SRP_Int_MC_HoldButton")).contains("Book")){
			safeClick(driver, getObject("Air_Agency_SRP_Int_MC_HoldButton"));
			}
		}
		
	}
	
	public void agencyAir_ItineraryPage(RemoteWebDriver driver) throws Exception {
		if(elementPresent(driver,By.xpath("//*[text()='Proxy Error']"),10)) {
			driver.navigate().refresh();
			Thread.sleep(1000);
					}
		for(int i=0; i<=15;i++){
			if(elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 1)){
				break;
			}
			else if(textPresent(driver, "Sorry, our system is acting up.", 1)){
				Reporter.log("Not a Script Issue Displaying Sorry, our system is acting up. : Error message is displayed");
				Assert.assertTrue(false);
			}
		}
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 10)){
			Reporter.log("Not a Script Issue Displaying Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
			Assert.assertTrue(false);
		}
		if(elementPresent_Time(driver, getObject("air_step1_insurance_chkbox"), 2)){
		safeUncheck(driver, getObject("air_step1_insurance_chkbox"));
		}
		Thread.sleep(500);
		String Iti=driver.getPageSource();

		Reporter.log("Itinerary ID="+Iti.split("var itineraryId =")[1].split(";")[0],true);
		safeClick(driver, getObject("Air_Agency_Itinerary_ContinueButton"));
	}
	
	public String agencyAir_ItineraryPage_Insurance(RemoteWebDriver driver) throws Exception {
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 30)){
			Reporter.log("Not a Script Issue Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
			Assert.assertTrue(false);
		}
		Thread.sleep(500);
		String insuranceAmt = getText(driver, By.xpath("//dl[@id='insuranceSummary']/dd"));
		safeClick(driver, getObject("Air_Agency_Itinerary_ContinueButton"));
		return insuranceAmt;
	}
	
	public void agencyAir_ItineraryPage_Add_Markup(RemoteWebDriver driver) throws Exception {
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 20)){
			Reporter.log("Not a Script Issue Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
			Assert.assertTrue(false);
		}
		if(elementPresent_Time(driver, getObject("air_step1_insurance_chkbox"), 2)){
			safeUncheck(driver, getObject("air_step1_insurance_chkbox"));
		}
		agency_Air_Add_OntheGo_Markup(driver);	
		safeClick(driver, getObject("Air_Agency_Itinerary_ContinueButton"));
	}
	
	public void agencyAir_ItineraryPage_OntheGo_Discount(RemoteWebDriver driver) throws Exception {
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 30)){
			Reporter.log("Not a Script Issue Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
			Assert.assertTrue(false);
		}
		if(elementPresent_Time(driver, getObject("air_step1_insurance_chkbox"), 2)){
			safeUncheck(driver, getObject("air_step1_insurance_chkbox"));
		}
		agency_Air_Add_OntheGo_Discount(driver);	
			safeClick(driver, getObject("Air_Agency_Itinerary_ContinueButton"));
	}
	
	public void agency_Hotel_Add_OntheGo_Markup(RemoteWebDriver driver) throws Exception, InterruptedException, NumberFormatException, IOException {
		String TotalPrice = getText(driver, getObject("AgencyHotels_Itinerarypage_TotalPrice"));
		Thread.sleep(2000);
		safeType(driver, getObject("AgencyHotels_Itinerarypage_AgencyService_Fee"), "500");
		Thread.sleep(2000);
		String TotalPrice_After_Markup = getText(driver, getObject("AgencyHotels_Itinerarypage_TotalPrice"));
		TotalPrice = TotalPrice.replace("Rs. ", "");
		TotalPrice_After_Markup = TotalPrice_After_Markup.replace("Rs. ", "");
		if(TotalPrice.contains(",")){
			TotalPrice = TotalPrice.replace(",", "");
		}
		if(TotalPrice_After_Markup.contains(",")){
			TotalPrice_After_Markup = TotalPrice_After_Markup.replace(",", "");
		}
		
		int TotalPrice_int = Integer.parseInt(TotalPrice);
		int TotalPrice_After_Markup_int = Integer.parseInt(TotalPrice_After_Markup);
		if(!(TotalPrice_After_Markup_int==(TotalPrice_int+500))){
			assertCommon(driver, "AgencyMarkup", 1, "Markup Amount is not adding up with total price");
		}
	}
	
	public void CTPhone_Hotel_OntheGo_Discount(RemoteWebDriver driver) throws Exception, InterruptedException, NumberFormatException, IOException {
		String TotalPrice = getText(driver, getObject("AgencyHotels_Itinerarypage_TotalPrice"));
		Thread.sleep(2000);
		safeType(driver, getObject("CTPhoneAgencyHotels_Itinerarypage_CT_Discount"), "1");
		Thread.sleep(2000);
		String TotalPrice_After_Discount = getText(driver, getObject("AgencyHotels_Itinerarypage_TotalPrice"));
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
		safeSelect(driver, getObject("CTPhoneAgencyHotels_Itinerarypage_CT_Discount_Reason_DropDown"), "Manual discount");
	}
	
	public void agency_Air_Add_OntheGo_Discount(RemoteWebDriver driver) throws Exception, InterruptedException, NumberFormatException, IOException {
		String TotalPrice = getText(driver, getObject("AgencyHotels_Itinerarypage_TotalPrice"));
		Thread.sleep(2000);
		safeType(driver, getObject("CTPhoneAgencyHotels_Itinerarypage_CT_Discount"), "50");
		Thread.sleep(2000);
		String TotalPrice_After_Markup = getText(driver, getObject("AgencyHotels_Itinerarypage_TotalPrice"));
		TotalPrice = TotalPrice.replace("Rs. ", "");
		TotalPrice_After_Markup = TotalPrice_After_Markup.replace("Rs. ", "");
		if(TotalPrice.contains(",")){
		TotalPrice = TotalPrice.replace(",", "");  
		}
		if(TotalPrice_After_Markup.contains(",")){
		TotalPrice_After_Markup = TotalPrice_After_Markup.replace(",", "");
		}	
		int TotalPrice_int = Integer.parseInt(TotalPrice);
		int TotalPrice_After_Markup_int = Integer.parseInt(TotalPrice_After_Markup);
		if(!(TotalPrice_After_Markup_int==(TotalPrice_int-50))){
		assertCommon(driver, "CTPhone_Discount", 1, "Discount is not applied with total price");
		}
		safeSelect(driver, getObject("CTPhoneAgencyHotels_Itinerarypage_CT_Discount_Reason_DropDown"), "Manual discount");
	}
	
	public void agency_Air_Add_OntheGo_Markup(RemoteWebDriver driver) throws Exception, InterruptedException, NumberFormatException, IOException {
		String TotalPrice = getText(driver, getObject("AgencyHotels_Itinerarypage_TotalPrice"));
		Thread.sleep(2000);
		safeType(driver, getObject("Air_Agency_Itinerarypage_AgencyService_Fee"), "500");
		Thread.sleep(2000);
		String TotalPrice_After_Markup = getText(driver, getObject("AgencyHotels_Itinerarypage_TotalPrice"));
		TotalPrice = TotalPrice.replace("Rs. ", "");
		TotalPrice_After_Markup = TotalPrice_After_Markup.replace("Rs. ", "");
		if(TotalPrice.contains(",")){
		TotalPrice = TotalPrice.replace(",", "");
		}
		if(TotalPrice_After_Markup.contains(",")){
		TotalPrice_After_Markup = TotalPrice_After_Markup.replace(",", "");
		}	
		int TotalPrice_int = Integer.parseInt(TotalPrice);
		int TotalPrice_After_Markup_int = Integer.parseInt(TotalPrice_After_Markup);
		if(!(TotalPrice_After_Markup_int==(TotalPrice_int+500))){
		assertCommon(driver, "AgencyMarkup", 1, "Markup Amount is not adding up with total price");
		}
	}
	public void gstdetails (RemoteWebDriver driver,String useGST,String useNonDefaultState )throws Exception {
		
		if(useGST.equalsIgnoreCase("true"))
		{
			//Assert.assertFalse("Use GST check box is clicked by default in travellers details book step!", driver.findElement(getObject("AirCom_BookStep3_GST_CheckBox")).isSelected()); 
			if (driver.findElement(By.id("gstin")).isSelected()){
				System.out.println("GSTN enabled");
			}else {
			safeClick(driver,getObject("AgencyCorp_BookStep_EnableGSTcheckbox"));
			}
			
			if(getText(driver, getObject("AgencyCorp_BookStep_AirlingName")).equalsIgnoreCase("SpiceJet"))
			{
			safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Spicejet"));
			}
			else if(getText(driver, getObject("AgencyCorp_BookStep_AirlingName")).equalsIgnoreCase("Indigo"))
			{
			safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Indigo"));
			}
			else if(getText(driver, getObject("AgencyCorp_BookStep_AirlingName")).equalsIgnoreCase("GoAir"))
			{
			safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Goair"));
			}
			else if(getText(driver, getObject("AgencyCorp_BookStep_AirlingName")).equalsIgnoreCase("Air_Asia") ||getText(driver, getObject("AgencyCorp_BookStep_AirlingName")).equalsIgnoreCase("Air Asia"))
			{
			safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Airasia"));
			}
			else
			{
			Reporter.log("Not a Script Issue Airline Specific test GST Code not available! Using SpiceJet", true);
			safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Spicejet"));
			}

			//safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number"));
			safeType(driver, getObject("AgencyCorp_BookStep_gstHolderName"), dataFile.value("GST_Holder_Name"));
			safeType(driver, getObject("AgencyCorp_BookStep_gstHolderAddress"), dataFile.value("GST_Holder_Address"));
			}
			else if(!useGST.equalsIgnoreCase("true") && useNonDefaultState.equalsIgnoreCase("true"))
			{
				if (driver.findElement(By.id("gstin")).isSelected()){
					System.out.println("GSTN enabled");
					safeClick(driver,getObject("AgencyCorp_BookStep_EnableGSTcheckbox"));
				}
			safeSelect(driver,By.id("gstStateContainer"), "Karnataka");
			}
			else
			{
				if (driver.findElement(By.id("gstin")).isSelected()){
					System.out.println("GSTN enabled");
					safeClick(driver,getObject("AgencyCorp_BookStep_EnableGSTcheckbox"));
				}
			}

	}
	public void  agencyAir_TravellerPage(RemoteWebDriver driver,String Adults, String Childrens, String Infants, String useGST, String useNonDefaultState) throws Exception {
		//travellerDetails(driver, Adults, Childrens, Infants, false);
		agencyPax(driver, Adults, Childrens, Infants);
		if(elementVisible(driver, getObject("AirCorpCom_TravellerPage_Ajax"), 1)) {
			safeClick(driver, getObject("AirCorpCom_TravellerPage_Ajax"));
		}
		
		gstdetails(driver,useGST,useNonDefaultState);
				
		safeClick(driver, getObject("Air_Agency_TravellerPage_ContinueButton"));
		}
	
	public void  CTPhoneAir_TravellerPage(RemoteWebDriver driver,String Adults, String Childrens, String Infants) throws Exception {
		//travellerDetails(driver, Adults, Childrens, Infants, false);
		CTPhonePax(driver, Adults, Childrens, Infants);
		safeClick(driver, getObject("Air_Agency_TravellerPage_ContinueButton"));
		}
	
	public void  agencyAir_TravellerPage_Int(RemoteWebDriver driver,String Adults, String Childrens, String Infants, String useGST, String useNonDefaultState) throws Exception {
		//travellerDetails(driver, Adults, Childrens, Infants, false);
		agencyPax(driver, Adults, Childrens, Infants);
		if(elementVisible(driver, getObject("AirCorpCom_TravellerPage_Ajax"), 1)) {
			safeClick(driver, getObject("AirCorpCom_TravellerPage_Ajax"));
		}
		
		gstdetails(driver,useGST,useNonDefaultState);
		
		safeClick(driver, getObject("Air_Agency_TravellerPage_Int_ContinueButton"));
		}
	
	public String agencyAir_Paymentpage(RemoteWebDriver driver,  String PaymentType, String Coupon, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		for(int i=0;i<=15;i++) {
		if(textPresent(driver, "There is a problem with your submission", 1)){
			Reporter.log("Not a Script Issue Displaying There is a problem with your submission : message is displayed in Traveler Page");
			break;
		}
		if(elementVisible(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 120)) {
				break;
		} else if(elementVisible(driver, By.xpath("(//a[contains(text(),'Modify search')])[2]"), 1)) {
				Reporter.log("Not a Script Issue Page has redirected back to SRP after clicking on continue in Traveller page");
				Assert.assertTrue(false);
			}
		}
		textPresent(driver, "How would you like to pay?", 150);
		if(elementPresent(driver,By.xpath("//*[text()='GSTIN cannot be updated once booking is completed']"),1)) {
			safeClick(driver,By.xpath("//*[text()='Continue']"));
			textPresent(driver, "How would you like to pay?", 120);
		}
		String TripID= null;
		if(Coupon.equalsIgnoreCase("COUPON"))
		{	
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), 30);
			safeType(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2BAIR_Coupon"));
			safeClick(driver, getObject("AgencyHotels_PaymentPage_Coupon_Button"));
			elementVisible(driver, By.cssSelector("div.savingsDetails.conditional > ul.inline.clearfix"), 30);
			textPresent(driver, "final cost", 20);
			Reporter.log("Coupon Applied ");
		}
		return TripID = agencyPayment(driver, PaymentType, Trip_Logger_Msg, Booking_Confirmation_Message);
	}
	
	public String CTPhoneAir_Paymentpage(RemoteWebDriver driver,  String PaymentType, String Coupon, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 10)){
			Reporter.log("Not a Script Issue PaymentPage is not displayed");
		}if(Coupon.equalsIgnoreCase("COUPON"))
		{	
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), 30);
			safeType(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2BAIR_Coupon"));
			safeClick(driver, getObject("AgencyHotels_PaymentPage_Coupon_Button"));
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 30);
			textAssert(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), "Great, you are eligible to a cashback");
			Reporter.log("Coupon Applied ");
		}
		String TripID= null;
		textPresent(driver, "How would you like to pay?", 5);
		return TripID = agencyPayment(driver, PaymentType, Trip_Logger_Msg, Booking_Confirmation_Message);
	}
		
	public void agencyTravellerDetails(RemoteWebDriver driver, String Adults, String Childrens, String Infants, boolean international) throws Exception {
			agencyPax(driver, Adults, Childrens, Infants);
	}
		
	public void agencyPax(RemoteWebDriver driver, String adult, String children, String infant) throws Exception {
			    int num_adults = Integer.parseInt(adult);
		        int num_children = Integer.parseInt(children);	        
		        int num_infants = Integer.parseInt(infant);
		        String month = getMonthTime(0, "MMM");
		        String Dob_month = month;
		        boolean B2B_GDS = b2bGDSAirlines(driver);
		        String Adult_Title = "'sTitle";
		        String Adult_Fname = "'sFirstName";
		        String Adult_Lname = "'sLastName";
		        String Adult_Dobday = "AdultDobDay";
		        String Adult_Dobmonth = "AdultDobMonth";
		        String Adult_Dobyear = "AdultDobYear";     	        
		        String Adult_Visa = "adultVisaType";
		        String Adult_Passport_No = "AdultPassport";
		        String Adult_Passport_Issuing_Country = "AdultPPIssuingCountry";
		        String Adult_Passport_Exp_Day = "AdultPPDay";
		        String Adult_Passport_Exp_Month = "AdultPPMonth";
		        String Adult_Passport_Exp_Year = "AdultPPYear";      	        
		        String Child_Title = "'sTitle";
		        String Child_Fname = "'sFirstName";
		        String Child_Lname = "'sLastName";
		        String Child_Dobday = "ChildDobDay";
		        String Child_Dobmonth = "ChildDobMonth";
		        String Child_Dobyear = "ChildDobYear";	   
		        String Child_Visa = "childVisaType";     
		        String Child_Passport_No = "ChildPassport";
		        String Child_Passport_Issuing_Country = "ChildPPIssuingCountry";
		        String Child_Passport_Exp_Day = "childPPDay";
		        String Child_Passport_Exp_Month = "ChildPPMonth";
		        String Child_Passport_Exp_Year = "ChildPPYear";
		        String Infant_Title = "'sTitle";
		        String Infant_Fname = "'sFirstName";
		        String Infant_Lname = "'sLastName";
		        String Infant_Dobday = "InfantDobDay";
		        String Infant_Dobmonth = "InfantDobMonth";
		        String Infant_Dobyear = "InfantDobYear";
		        String Infant_Visa = "infantVisaType";
		        String Infant_Passport_No = "InfantPassport";
		        String Infant_Passport_Issuing_Country = "InfantPPIssuingCountry";
		        String Infant_Passport_Exp_Day = "infantPPDay";
		        String Infant_Passport_Exp_Month = "InfantPPMonth";
		        String Infant_Passport_Exp_Year = "InfantPPYear";
		        
		        // --------------------------------------------GDS PAX------------------------------------------//
		        if ( B2B_GDS ) {
		            Reporter.log("Pax entry for GDS Flight Combination", true);
		            if ( num_adults > 0 ) {
		            	Thread.sleep(1000);
		                for ( int i = 1; i <= num_adults; i++) {
		                	String Adult_Title_ID = "adult"+i+Adult_Title;
		                	String Adult_Dobday_ID = Adult_Dobday+i;
		                	String Adult_Dobmonth_ID = Adult_Dobmonth+i;
		                	String Adult_Dobyear_ID = Adult_Dobyear+i;
		                	String Adult_Fname_ID = "adult"+i+Adult_Fname;
		                	String Adult_Lname_ID = "adult"+i+Adult_Lname;
		                	String Adult_Visa_ID = Adult_Visa+i;	                	
		                	String Adult_Passport_No_ID = Adult_Passport_No+i;
		        	        String Adult_Passport_Issuing_Country_ID = Adult_Passport_Issuing_Country+i;
		        	        String Adult_Passport_Exp_Day_ID = Adult_Passport_Exp_Day+i;
		        	        String Adult_Passport_Exp_Month_ID = Adult_Passport_Exp_Month+i;
		        	        String Adult_Passport_Exp_Year_ID = Adult_Passport_Exp_Year+i;      	   
		                	
		                	boolean adult_name_details = elementPresent_Time(driver, By.id(Adult_Title_ID), 1);
		                    if ( adult_name_details ) {
		                    	safeSelect(driver, By.id(Adult_Title_ID), "Mr");
		                    	safeType(driver, By.id(Adult_Fname_ID), dataFile.value("afname" + i));
		                    	safeAutocomplete(driver, By.id(Adult_Lname_ID), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("alname" + i));
		                        smartSelect(driver, By.name(Adult_Visa_ID), "Business");
		                    } else {
		                        Reporter.log("Adult " + i + " is not displayed");
		                    }
		                    if(elementVisible(driver,  By.id(Adult_Dobday_ID), 1)){
				                smartSelect(driver, By.id(Adult_Dobday_ID), dataFile.value("cdobday"));
		                    	smartSelect(driver, By.id(Adult_Dobmonth_ID), Dob_month);
		                    	smartSelect(driver, By.id(Adult_Dobyear_ID), putYear(-20));
		                        smartSelect(driver, By.name(Adult_Visa_ID), "Business");
		                    }

		                    	smartType(driver, By.id(Adult_Passport_No_ID), dataFile.value("apassport"));
			                    if(elementVisible(driver, By.id(Adult_Passport_Issuing_Country_ID), 1)){
		                        	safeAutocomplete(driver, By.id(Adult_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
		                        }
			                    if(elementVisible(driver,  By.id(Adult_Passport_Exp_Day_ID), 1)){
			                    smartSelect(driver, By.id(Adult_Passport_Exp_Day_ID), "5");
			                    smartSelect(driver, By.id(Adult_Passport_Exp_Month_ID), Dob_month);
			                    smartSelect(driver, By.id(Adult_Passport_Exp_Year_ID), putYear(10));
			                    }
		                }
		            }
		            if(num_children > 0) {
		            	Thread.sleep(1000);
		                for ( int i = 1; i <= num_children; i++) {
		                	String Child_Title_ID = "child"+i+Child_Title;
		                	String Child_Fname_ID = "child"+i+Child_Fname;
		                	String Child_Lname_ID = "child"+i+Child_Lname;
		                	String Child_Dobday_ID = Child_Dobday+i;
		                	String Child_Dobmonth_ID = Child_Dobmonth+i;
		                	String Child_Dobyear_ID = Child_Dobyear+i;
		                	String Child_Visa_ID = Child_Visa+i;	                	
		                	String Child_Passport_No_ID = Child_Passport_No+i;
		        	        String Child_Passport_Issuing_Country_ID = Child_Passport_Issuing_Country+i;
		        	        String Child_Passport_Exp_Day_ID = Child_Passport_Exp_Day+i;
		        	        String Child_Passport_Exp_Month_ID = Child_Passport_Exp_Month+i;
		        	        String Child_Passport_Exp_Year_ID = Child_Passport_Exp_Year+i;      
		                	boolean child_name_details = elementPresent_Time(driver, By.id(Child_Title_ID), 1);
		                	if ( child_name_details ) {
		                    	safeSelect(driver, By.id(Child_Title_ID), dataFile.value("ctitle"));
		                    	safeType(driver, By.id(Child_Fname_ID),dataFile.value("cfname" + i));
		                    	safeAutocomplete(driver, By.id(Child_Lname_ID), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("clname" + i));
		                    	safeSelect(driver, By.id(Child_Dobday_ID), dataFile.value("cdobday"));
		                    	safeSelect(driver, By.id(Child_Dobmonth_ID), Dob_month);
		                    	safeSelect(driver, By.id(Child_Dobyear_ID), putYear(-3));
		                    } else {
		                        Reporter.log("Child " + i + " is not displayed");
		                    } 
		                	//boolean child_Passport = elementPresent_Time(driver, By.name(Child_Visa_ID), 1);
		                       smartSelect(driver, By.name(Child_Visa_ID), "Student");
		                       smartType(driver, By.id(Child_Passport_No_ID), dataFile.value("cpassport"));
		                       if(elementPresent_Time(driver, By.id(Child_Passport_Issuing_Country_ID), 1)){
		                        	safeAutocomplete(driver, By.id(Child_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
		                        }
	
			                   if(elementPresent_Time(driver,   By.name(Child_Passport_Exp_Day_ID), 1)){
			                   smartSelect(driver, By.name(Child_Passport_Exp_Day_ID), "5");
			                   smartSelect(driver, By.id(Child_Passport_Exp_Month_ID), Dob_month);
			                   smartSelect(driver, By.id(Child_Passport_Exp_Year_ID), putYear(10));
			                   }
		                }
		            }
		            if ( num_infants > 0 ) {
		            	Thread.sleep(1000);
		                for ( int i = 1; i <= num_infants; i++) {
		                	String Infant_Title_ID = "infant"+i+Infant_Title;
		                	String Infant_Fname_ID = "infant"+i+Infant_Fname;
		                	String Infant_Lname_ID = "infant"+i+Infant_Lname;
		                	String Infant_Dobday_ID = Infant_Dobday+i;
		                	String Infant_Dobmonth_ID = Infant_Dobmonth+i;
		                	String Infant_Dobyear_ID = Infant_Dobyear+i;
		                	String Infant_Visa_ID = Infant_Visa+i;
		                	String Infant_Passport_No_ID = Infant_Passport_No+i;
		        	        String Infant_Passport_Issuing_Country_ID = Infant_Passport_Issuing_Country+i;
		        	        String Infant_Passport_Exp_Day_ID = Infant_Passport_Exp_Day+i;
		        	        String Infant_Passport_Exp_Month_ID = Infant_Passport_Exp_Month+i;
		        	        String Infant_Passport_Exp_Year_ID = Infant_Passport_Exp_Year+i;      
		                	boolean infant_name_details = elementPresent_Time(driver, By.id(Infant_Title_ID), 1);
		                    if ( infant_name_details ) {
		                    	safeSelect(driver, By.id(Infant_Title_ID), dataFile.value("ititle"));
		                    	safeType(driver, By.id(Infant_Fname_ID), dataFile.value("ifname" + i));
		                    	safeAutocomplete(driver, By.id(Infant_Lname_ID), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("ilname" + i));
		                    	safeSelect(driver, By.id(Infant_Dobday_ID), dataFile.value("idobday"));
		                    	safeSelect(driver, By.id(Infant_Dobmonth_ID), Dob_month);
		                    	safeSelect(driver, By.id(Infant_Dobyear_ID), putYear(0));
		                    } else {
		                        Reporter.log("Infant " + i + " is not displayed");
		                    }
		                    
		                    	smartSelect(driver, By.name(Infant_Visa_ID), "Dependent");
		                        smartType(driver, By.id(Infant_Passport_No_ID), dataFile.value("cpassport"));
		                        if(elementVisible(driver, By.id(Infant_Passport_Issuing_Country_ID), 1)){
		                        	safeAutocomplete(driver, By.id(Infant_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
		                        }
		                        if(elementVisible(driver,  By.name(Infant_Passport_Exp_Day_ID), 1)){	
			                    smartSelect(driver, By.name(Infant_Passport_Exp_Day_ID), "5");
			                    smartSelect(driver, By.id(Infant_Passport_Exp_Month_ID), Dob_month);
			                    smartSelect(driver, By.id(Infant_Passport_Exp_Year_ID), putYear(10));
		                        }
		                }
		            }
		        }
		        // --------------------------------------------LCC PAX------------------------------------------//
		        else {
		            Reporter.log("Pax entry for LCC Flight Combination");
		            if ( num_adults > 0 ) {
		            	Thread.sleep(1000);
		                for ( int i = 1; i <= num_adults; i++) {
		                	String Adult_Title_ID = "adult"+i+Adult_Title;
		                	String Adult_Dobday_ID = Adult_Dobday+i;
		                	String Adult_Dobmonth_ID = Adult_Dobmonth+i;
		                	String Adult_Dobyear_ID = Adult_Dobyear+i;
		                	String Adult_Fname_ID = "adult"+i+Adult_Fname;
		                	String Adult_Lname_ID = "adult"+i+Adult_Lname;
		                	String Adult_Visa_ID = Adult_Visa+i;	                	
		                	String Adult_Passport_No_ID = Adult_Passport_No+i;
		        	        String Adult_Passport_Issuing_Country_ID = Adult_Passport_Issuing_Country+i;
		        	        String Adult_Passport_Exp_Day_ID = Adult_Passport_Exp_Day+i;
		        	        String Adult_Passport_Exp_Month_ID = Adult_Passport_Exp_Month+i;
		        	        String Adult_Passport_Exp_Year_ID = Adult_Passport_Exp_Year+i;      	   
		                	
		                	boolean adult_name_details = elementPresent_Time(driver, By.id(Adult_Title_ID), 1);
		                    if ( adult_name_details ) {
		                    	safeSelect(driver, By.id(Adult_Title_ID), "Mr");
		                    	safeType(driver, By.id(Adult_Fname_ID), "Test");
		                    	safeAutocomplete(driver, By.id(Adult_Lname_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "Test");
		                        smartSelect(driver, By.name(Adult_Visa_ID), "Business");
		                    } else {
		                        Reporter.log("Adult " + i + " is not displayed");
		                    }
		                  //  boolean adultdate = elementVisible(driver,  By.id(Adult_Dobday_ID), 1);
		                    if(elementPresent_Time(driver,  By.id(Adult_Dobday_ID), 1)){
		                       	smartSelect(driver, By.id(Adult_Dobday_ID), dataFile.value("cdobday"));
		                    	smartSelect(driver, By.id(Adult_Dobmonth_ID), Dob_month);
		                    	smartSelect(driver, By.id(Adult_Dobyear_ID), putYear(-20));
		                        smartSelect(driver, By.name(Adult_Visa_ID), "Business");
		                    }
		                    smartType(driver, By.id(Adult_Passport_No_ID), dataFile.value("apassport"));
			                  
			                    if(elementPresent_Time(driver, By.id(Adult_Passport_Exp_Day_ID), 1)){
			                    smartSelect(driver, By.id(Adult_Passport_Exp_Day_ID), "5");
			                    smartSelect(driver, By.id(Adult_Passport_Exp_Month_ID), Dob_month);
			                    smartSelect(driver, By.id(Adult_Passport_Exp_Year_ID), putYear(10));
			                    }
			                    if(elementPresent_Time(driver, By.id(Adult_Passport_Issuing_Country_ID), 1)){
		                        	safeAutocomplete(driver, By.id(Adult_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
		                        }
		                }
		            }
		            if(num_children > 0) {
		            	Thread.sleep(1000);
		                for ( int i = 1; i <= num_children; i++) {
		                	String Child_Title_ID = "child"+i+Child_Title;
		                	String Child_Fname_ID = "child"+i+Child_Fname;
		                	String Child_Lname_ID = "child"+i+Child_Lname;
		                	String Child_Dobday_ID = Child_Dobday+i;
		                	String Child_Dobmonth_ID = Child_Dobmonth+i;
		                	String Child_Dobyear_ID = Child_Dobyear+i;
		                	String Child_Visa_ID = Child_Visa+i;	                	
		                	String Child_Passport_No_ID = Child_Passport_No+i;
		        	        String Child_Passport_Issuing_Country_ID = Child_Passport_Issuing_Country+i;
		        	        String Child_Passport_Exp_Day_ID = Child_Passport_Exp_Day+i;
		        	        String Child_Passport_Exp_Month_ID = Child_Passport_Exp_Month+i;
		        	        String Child_Passport_Exp_Year_ID = Child_Passport_Exp_Year+i;      
		                	boolean child_name_details = elementPresent_Time(driver, By.id(Child_Title_ID), 1);
		                	if ( child_name_details ) {
		                    	safeSelect(driver, By.id(Child_Title_ID), dataFile.value("ctitle"));
		                    	safeType(driver, By.id(Child_Fname_ID), "Test");
		                    	safeAutocomplete(driver, By.id(Child_Lname_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "Test");
		                    	safeSelect(driver, By.id(Child_Dobday_ID), dataFile.value("cdobday"));
		                    	safeSelect(driver, By.id(Child_Dobmonth_ID), Dob_month);
		                    	safeSelect(driver, By.id(Child_Dobyear_ID), putYear(-3));
		                    } else {
		                        Reporter.log("Child " + i + " is not displayed");
		                    } 
		                      smartSelect(driver, By.name(Child_Visa_ID), "Student");
		                       smartType(driver, By.id(Child_Passport_No_ID), dataFile.value("cpassport"));
			                if(elementPresent_Time(driver, By.id(Child_Passport_Issuing_Country_ID), 1)){
		                        	safeAutocomplete(driver, By.id(Child_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
		                        }		         
			                if(elementVisible(driver, By.name(Child_Passport_Exp_Day_ID), 1)){
			                   smartSelect(driver, By.name(Child_Passport_Exp_Day_ID), "5");
			                   smartSelect(driver, By.id(Child_Passport_Exp_Month_ID), Dob_month);
			                   smartSelect(driver, By.id(Child_Passport_Exp_Year_ID), putYear(10));
			                }
		                }
		            }
		            if ( num_infants > 0 ) {
		            	Thread.sleep(1000);
		                for ( int i = 1; i <= num_infants; i++) {
		                	String Infant_Title_ID = "infant"+i+Infant_Title;
		                	String Infant_Fname_ID = "infant"+i+Infant_Fname;
		                	String Infant_Lname_ID = "infant"+i+Infant_Lname;
		                	String Infant_Dobday_ID = Infant_Dobday+i;
		                	String Infant_Dobmonth_ID = Infant_Dobmonth+i;
		                	String Infant_Dobyear_ID = Infant_Dobyear+i;
		                	String Infant_Visa_ID = Infant_Visa+i;
		                	String Infant_Passport_No_ID = Infant_Passport_No+i;
		        	        String Infant_Passport_Issuing_Country_ID = Infant_Passport_Issuing_Country+i;
		        	        String Infant_Passport_Exp_Day_ID = Infant_Passport_Exp_Day+i;
		        	        String Infant_Passport_Exp_Month_ID = Infant_Passport_Exp_Month+i;
		        	        String Infant_Passport_Exp_Year_ID = Infant_Passport_Exp_Year+i;      
		                	boolean infant_name_details = elementPresent_Time(driver, By.id(Infant_Title_ID), 1);
		                    if ( infant_name_details ) {
		                    	safeSelect(driver, By.id(Infant_Title_ID), dataFile.value("ititle"));
		                    	safeType(driver, By.id(Infant_Fname_ID), "Test");
		                    	safeAutocomplete(driver, By.id(Infant_Lname_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "Test");
		                    	safeSelect(driver, By.id(Infant_Dobday_ID), dataFile.value("idobday"));
		                    	safeSelect(driver, By.id(Infant_Dobmonth_ID), Dob_month);
		                    	safeSelect(driver, By.id(Infant_Dobyear_ID), putYear(0));
		                    } else {
		                        Reporter.log("Infant " + i + " is not displayed");
		                    }	                    
		                    	smartSelect(driver, By.name(Infant_Visa_ID), "Dependent");
		                        smartType(driver, By.id(Infant_Passport_No_ID), dataFile.value("cpassport"));
		                        if(elementPresent_Time(driver, By.id(Infant_Passport_Issuing_Country_ID), 1)){
		                        	safeAutocomplete(driver, By.id(Infant_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
		                        }
		                        if(elementVisible(driver, By.name(Infant_Passport_Exp_Day_ID), 1)){
			                    smartSelect(driver, By.name(Infant_Passport_Exp_Day_ID), "5");
			                    smartSelect(driver, By.id(Infant_Passport_Exp_Month_ID), Dob_month);
			                    smartSelect(driver, By.id(Infant_Passport_Exp_Year_ID), putYear(10));
		                        }
		                }
		            }
		        }
		    }
	
	public void CTPhonePax(RemoteWebDriver driver, String adult, String children, String infant) throws Exception {
	    int num_adults = Integer.parseInt(adult);
	    int num_children = Integer.parseInt(children);	        
	    int num_infants = Integer.parseInt(infant);
	    String month = getMonthTime(0, "MMM");
	    String Dob_month = month;
	    boolean B2B_GDS = b2bGDSAirlines(driver);
	    String Adult_Title = "'sTitle";
	    String Adult_Fname = "'sFirstName";
	    String Adult_Lname = "'sLastName";
	    String Adult_Dobday = "AdultDobDay";
	    String Adult_Dobmonth = "AdultDobMonth";
	    String Adult_Dobyear = "AdultDobYear";     	        
	    String Adult_Visa = "adultVisaType";
	    String Adult_Passport_No = "AdultPassport";
	    String Adult_Passport_Issuing_Country = "AdultPPIssuingCountry";
	    String Adult_Passport_Exp_Day = "AdultPPDay";
	    String Adult_Passport_Exp_Month = "AdultPPMonth";
	    String Adult_Passport_Exp_Year = "AdultPPYear";      	        
	    String Child_Title = "'sTitle";
	    String Child_Fname = "'sFirstName";
	    String Child_Lname = "'sLastName";
	    String Child_Dobday = "ChildDobDay";
	    String Child_Dobmonth = "ChildDobMonth";
	    String Child_Dobyear = "ChildDobYear";	   
	    String Child_Visa = "childVisaType";     
	    String Child_Passport_No = "ChildPassport";
	    String Child_Passport_Issuing_Country = "ChildPPIssuingCountry";
	    String Child_Passport_Exp_Day = "childPPDay";
	    String Child_Passport_Exp_Month = "ChildPPMonth";
	    String Child_Passport_Exp_Year = "ChildPPYear";
	    String Infant_Title = "'sTitle";
	    String Infant_Fname = "'sFirstName";
	    String Infant_Lname = "'sLastName";
	    String Infant_Dobday = "InfantDobDay";
	    String Infant_Dobmonth = "InfantDobMonth";
	    String Infant_Dobyear = "InfantDobYear";
	    String Infant_Visa = "infantVisaType";
	    String Infant_Passport_No = "InfantPassport";
	    String Infant_Passport_Issuing_Country = "InfantPPIssuingCountry";
	    String Infant_Passport_Exp_Day = "infantPPDay";
	    String Infant_Passport_Exp_Month = "InfantPPMonth";
	    String Infant_Passport_Exp_Year = "InfantPPYear";
	    
	    // --------------------------------------------GDS PAX------------------------------------------//
	    if ( B2B_GDS ) {
	        Reporter.log("Pax entry for GDS Flight Combination");
	        if ( num_adults > 0 ) {
	        	Thread.sleep(1000);
	            for ( int i = 1; i <= num_adults; i++) {
	            	String Adult_Title_ID = "adult"+i+Adult_Title;
	            	String Adult_Dobday_ID = Adult_Dobday+i;
	            	String Adult_Dobmonth_ID = Adult_Dobmonth+i;
	            	String Adult_Dobyear_ID = Adult_Dobyear+i;
	            	String Adult_Fname_ID = "adult"+i+Adult_Fname;
	            	String Adult_Lname_ID = "adult"+i+Adult_Lname;
	            	String Adult_Visa_ID = Adult_Visa+i;	                	
	            	String Adult_Passport_No_ID = Adult_Passport_No+i;
	    	        String Adult_Passport_Issuing_Country_ID = Adult_Passport_Issuing_Country+i;
	    	        String Adult_Passport_Exp_Day_ID = Adult_Passport_Exp_Day+i;
	    	        String Adult_Passport_Exp_Month_ID = Adult_Passport_Exp_Month+i;
	    	        String Adult_Passport_Exp_Year_ID = Adult_Passport_Exp_Year+i;      	   
	            	
	            	boolean adult_name_details = elementPresent_Time(driver, By.id(Adult_Title_ID), 1);
	                if ( adult_name_details ) {
	                	safeSelect(driver, By.id(Adult_Title_ID), "Mr");
	                	safeType(driver, By.id(Adult_Fname_ID), dataFile.value("afname" + i));
	                	safeType(driver, By.id(Adult_Lname_ID), dataFile.value("alname" + i));
	                	} else {
	                    Reporter.log("Adult " + i + " is not displayed");
	                }
	                if(elementVisible(driver,  By.id(Adult_Dobday_ID), 1)){
		                smartSelect(driver, By.id(Adult_Dobday_ID), dataFile.value("cdobday"));
	                	smartSelect(driver, By.id(Adult_Dobmonth_ID), Dob_month);
	                	smartSelect(driver, By.id(Adult_Dobyear_ID), putYear(-20));
	                }
	                    smartSelect(driver, By.name(Adult_Visa_ID), "Business");
	                    smartType(driver, By.id(Adult_Passport_No_ID), dataFile.value("apassport"));
	                    if(elementPresent_Time(driver, By.id(Adult_Passport_Issuing_Country_ID), 1)){
	                    	safeAutocomplete(driver, By.id(Adult_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
	                    }
	                    if(elementVisible(driver,  By.id(Adult_Passport_Exp_Day_ID), 1)){
	                    smartSelect(driver, By.id(Adult_Passport_Exp_Day_ID), "5");
	                    smartSelect(driver, By.id(Adult_Passport_Exp_Month_ID), Dob_month);
	                    smartSelect(driver, By.id(Adult_Passport_Exp_Year_ID), putYear(10));
	                    }
	            }
	        }
	        if(num_children > 0) {
	        	Thread.sleep(1000);
	            for ( int i = 1; i <= num_children; i++) {
	            	String Child_Title_ID = "child"+i+Child_Title;
	            	String Child_Fname_ID = "child"+i+Child_Fname;
	            	String Child_Lname_ID = "child"+i+Child_Lname;
	            	String Child_Dobday_ID = Child_Dobday+i;
	            	String Child_Dobmonth_ID = Child_Dobmonth+i;
	            	String Child_Dobyear_ID = Child_Dobyear+i;
	            	String Child_Visa_ID = Child_Visa+i;	                	
	            	String Child_Passport_No_ID = Child_Passport_No+i;
	    	        String Child_Passport_Issuing_Country_ID = Child_Passport_Issuing_Country+i;
	    	        String Child_Passport_Exp_Day_ID = Child_Passport_Exp_Day+i;
	    	        String Child_Passport_Exp_Month_ID = Child_Passport_Exp_Month+i;
	    	        String Child_Passport_Exp_Year_ID = Child_Passport_Exp_Year+i;      
	            	boolean child_name_details = elementPresent_Time(driver, By.id(Child_Title_ID), 1);
	            	if ( child_name_details ) {
	                	safeSelect(driver, By.id(Child_Title_ID), dataFile.value("ctitle"));
	                	safeType(driver, By.id(Child_Fname_ID),dataFile.value("cfname" + i));
	                	safeType(driver, By.id(Child_Lname_ID), dataFile.value("clname" + i));
	                	safeSelect(driver, By.id(Child_Dobday_ID), dataFile.value("cdobday"));
	                	safeSelect(driver, By.id(Child_Dobmonth_ID), Dob_month);
	                	safeSelect(driver, By.id(Child_Dobyear_ID), putYear(-3));
	                } else {
	                    Reporter.log("Child " + i + " is not displayed");
	                } 
	            	   smartSelect(driver, By.name(Child_Visa_ID), "Student");
	                   smartType(driver, By.id(Child_Passport_No_ID), dataFile.value("cpassport"));
	                   if(elementPresent_Time(driver, By.id(Child_Passport_Issuing_Country_ID), 1)){
	                    	safeAutocomplete(driver, By.id(Child_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
	                    }
	
	                   if(elementPresent_Time(driver,   By.name(Child_Passport_Exp_Day_ID), 1)){
	                   smartSelect(driver, By.name(Child_Passport_Exp_Day_ID), "5");
	                   smartSelect(driver, By.id(Child_Passport_Exp_Month_ID), Dob_month);
	                   smartSelect(driver, By.id(Child_Passport_Exp_Year_ID), putYear(10));
	                   }
	            }
	        }
	        if ( num_infants > 0 ) {
	        	Thread.sleep(1000);
	            for ( int i = 1; i <= num_infants; i++) {
	            	String Infant_Title_ID = "infant"+i+Infant_Title;
	            	String Infant_Fname_ID = "infant"+i+Infant_Fname;
	            	String Infant_Lname_ID = "infant"+i+Infant_Lname;
	            	String Infant_Dobday_ID = Infant_Dobday+i;
	            	String Infant_Dobmonth_ID = Infant_Dobmonth+i;
	            	String Infant_Dobyear_ID = Infant_Dobyear+i;
	            	String Infant_Visa_ID = Infant_Visa+i;
	            	String Infant_Passport_No_ID = Infant_Passport_No+i;
	    	        String Infant_Passport_Issuing_Country_ID = Infant_Passport_Issuing_Country+i;
	    	        String Infant_Passport_Exp_Day_ID = Infant_Passport_Exp_Day+i;
	    	        String Infant_Passport_Exp_Month_ID = Infant_Passport_Exp_Month+i;
	    	        String Infant_Passport_Exp_Year_ID = Infant_Passport_Exp_Year+i;      
	            	boolean infant_name_details = elementPresent_Time(driver, By.id(Infant_Title_ID), 1);
	                if ( infant_name_details ) {
	                	safeSelect(driver, By.id(Infant_Title_ID), dataFile.value("ititle"));
	                	safeType(driver, By.id(Infant_Fname_ID), dataFile.value("ifname" + i));
	                	safeType(driver, By.id(Infant_Lname_ID), dataFile.value("ilname" + i));
	                	safeSelect(driver, By.id(Infant_Dobday_ID), dataFile.value("idobday"));
	                	safeSelect(driver, By.id(Infant_Dobmonth_ID), Dob_month);
	                	safeSelect(driver, By.id(Infant_Dobyear_ID), putYear(0));
	                } else {
	                    Reporter.log("Infant " + i + " is not displayed");
	                }
	                
	                	smartSelect(driver, By.name(Infant_Visa_ID), "Dependent");
	                    smartType(driver, By.id(Infant_Passport_No_ID), dataFile.value("cpassport"));
	                    if(elementPresent_Time(driver, By.id(Infant_Passport_Issuing_Country_ID), 1)){
	                    	safeAutocomplete(driver, By.id(Infant_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
	                    }
	                    if(elementPresent_Time(driver,  By.name(Infant_Passport_Exp_Day_ID), 1)){	
	                    smartSelect(driver, By.name(Infant_Passport_Exp_Day_ID), "5");
	                    smartSelect(driver, By.id(Infant_Passport_Exp_Month_ID), Dob_month);
	                    smartSelect(driver, By.id(Infant_Passport_Exp_Year_ID), putYear(10));
	                    }
	            }
	        }
	    }
	    // --------------------------------------------LCC PAX------------------------------------------//
	    else {
	        Reporter.log("Pax entry for LCC Flight Combination");
	        if ( num_adults > 0 ) {
	        	Thread.sleep(1000);
	            for ( int i = 1; i <= num_adults; i++) {
	            	String Adult_Title_ID = "adult"+i+Adult_Title;
	            	String Adult_Dobday_ID = Adult_Dobday+i;
	            	String Adult_Dobmonth_ID = Adult_Dobmonth+i;
	            	String Adult_Dobyear_ID = Adult_Dobyear+i;
	            	String Adult_Fname_ID = "adult"+i+Adult_Fname;
	            	String Adult_Lname_ID = "adult"+i+Adult_Lname;
	            	String Adult_Visa_ID = Adult_Visa+i;	                	
	            	String Adult_Passport_No_ID = Adult_Passport_No+i;
	    	        String Adult_Passport_Issuing_Country_ID = Adult_Passport_Issuing_Country+i;
	    	        String Adult_Passport_Exp_Day_ID = Adult_Passport_Exp_Day+i;
	    	        String Adult_Passport_Exp_Month_ID = Adult_Passport_Exp_Month+i;
	    	        String Adult_Passport_Exp_Year_ID = Adult_Passport_Exp_Year+i;      	   
	            	
	            	boolean adult_name_details = elementPresent_Time(driver, By.id(Adult_Title_ID), 1);
	                if ( adult_name_details ) {
	                	safeSelect(driver, By.id(Adult_Title_ID), "Mr");
	                	safeType(driver, By.id(Adult_Fname_ID), "Test");
	                	safeType(driver, By.id(Adult_Lname_ID), "Test");
	                } else {
	                    Reporter.log("Adult " + i + " is not displayed");
	                }
	                if(elementPresent_Time(driver,  By.id(Adult_Dobday_ID), 1)){
	                   	smartSelect(driver, By.id(Adult_Dobday_ID), dataFile.value("cdobday"));
	                	smartSelect(driver, By.id(Adult_Dobmonth_ID), Dob_month);
	                	smartSelect(driver, By.id(Adult_Dobyear_ID), putYear(-20));
	                }
	                    smartSelect(driver, By.name(Adult_Visa_ID), "Business");
	                    smartType(driver, By.id(Adult_Passport_No_ID), dataFile.value("apassport"));
	                    if(elementPresent_Time(driver, By.id(Adult_Passport_Issuing_Country_ID), 1)){
	                    	safeAutocomplete(driver, By.id(Adult_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
	                    }
	                    if(elementPresent_Time(driver, By.id(Adult_Passport_Exp_Day_ID), 1)){
	                    smartSelect(driver, By.id(Adult_Passport_Exp_Day_ID), "5");
	                    smartSelect(driver, By.id(Adult_Passport_Exp_Month_ID), Dob_month);
	                    smartSelect(driver, By.id(Adult_Passport_Exp_Year_ID), putYear(10));
	                    }
	            }
	        }
	        if(num_children > 0) {
	        	Thread.sleep(1000);
	            for ( int i = 1; i <= num_children; i++) {
	            	String Child_Title_ID = "child"+i+Child_Title;
	            	String Child_Fname_ID = "child"+i+Child_Fname;
	            	String Child_Lname_ID = "child"+i+Child_Lname;
	            	String Child_Dobday_ID = Child_Dobday+i;
	            	String Child_Dobmonth_ID = Child_Dobmonth+i;
	            	String Child_Dobyear_ID = Child_Dobyear+i;
	            	String Child_Visa_ID = Child_Visa+i;	                	
	            	String Child_Passport_No_ID = Child_Passport_No+i;
	    	        String Child_Passport_Issuing_Country_ID = Child_Passport_Issuing_Country+i;
	    	        String Child_Passport_Exp_Day_ID = Child_Passport_Exp_Day+i;
	    	        String Child_Passport_Exp_Month_ID = Child_Passport_Exp_Month+i;
	    	        String Child_Passport_Exp_Year_ID = Child_Passport_Exp_Year+i;      
	            	boolean child_name_details = elementPresent_Time(driver, By.id(Child_Title_ID), 1);
	            	if ( child_name_details ) {
	                	safeSelect(driver, By.id(Child_Title_ID), dataFile.value("ctitle"));
	                	safeType(driver, By.id(Child_Fname_ID), "Test");
	                	safeType(driver, By.id(Child_Lname_ID), "Test");
	                	safeSelect(driver, By.id(Child_Dobday_ID), dataFile.value("cdobday"));
	                	safeSelect(driver, By.id(Child_Dobmonth_ID), Dob_month);
	                	safeSelect(driver, By.id(Child_Dobyear_ID), putYear(-3));
	                } else {
	                    Reporter.log("Child " + i + " is not displayed");
	                } 
	            	   smartSelect(driver, By.name(Child_Visa_ID), "Student");
	                   smartType(driver, By.id(Child_Passport_No_ID), dataFile.value("cpassport"));
	                if(elementPresent_Time(driver, By.id(Child_Passport_Issuing_Country_ID), 1)){
	                    	safeAutocomplete(driver, By.id(Child_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
	                    }		         
	                if(elementPresent_Time(driver, By.name(Child_Passport_Exp_Day_ID), 1)){
	                   smartSelect(driver, By.name(Child_Passport_Exp_Day_ID), "5");
	                   smartSelect(driver, By.id(Child_Passport_Exp_Month_ID), Dob_month);
	                   smartSelect(driver, By.id(Child_Passport_Exp_Year_ID), putYear(10));
	                }
	            }
	        }
	        if ( num_infants > 0 ) {
	        	Thread.sleep(1000);
	            for ( int i = 1; i <= num_infants; i++) {
	            	String Infant_Title_ID = "infant"+i+Infant_Title;
	            	String Infant_Fname_ID = "infant"+i+Infant_Fname;
	            	String Infant_Lname_ID = "infant"+i+Infant_Lname;
	            	String Infant_Dobday_ID = Infant_Dobday+i;
	            	String Infant_Dobmonth_ID = Infant_Dobmonth+i;
	            	String Infant_Dobyear_ID = Infant_Dobyear+i;
	            	String Infant_Visa_ID = Infant_Visa+i;
	            	String Infant_Passport_No_ID = Infant_Passport_No+i;
	    	        String Infant_Passport_Issuing_Country_ID = Infant_Passport_Issuing_Country+i;
	    	        String Infant_Passport_Exp_Day_ID = Infant_Passport_Exp_Day+i;
	    	        String Infant_Passport_Exp_Month_ID = Infant_Passport_Exp_Month+i;
	    	        String Infant_Passport_Exp_Year_ID = Infant_Passport_Exp_Year+i;      
	            	boolean infant_name_details = elementPresent_Time(driver, By.id(Infant_Title_ID), 1);
	                if ( infant_name_details ) {
	                	safeSelect(driver, By.id(Infant_Title_ID), dataFile.value("ititle"));
	                	safeType(driver, By.id(Infant_Fname_ID), "Test");
	                	safeType(driver, By.id(Infant_Lname_ID), "Test");
	                	safeSelect(driver, By.id(Infant_Dobday_ID), dataFile.value("idobday"));
	                	safeSelect(driver, By.id(Infant_Dobmonth_ID), Dob_month);
	                	safeSelect(driver, By.id(Infant_Dobyear_ID), putYear(0));
	                } else {
	                    Reporter.log("Infant " + i + " is not displayed");
	                }	                    
	                	smartSelect(driver, By.name(Infant_Visa_ID), "Dependent");
	                    smartType(driver, By.id(Infant_Passport_No_ID), dataFile.value("cpassport"));
	                    if(elementPresent_Time(driver, By.id(Infant_Passport_Issuing_Country_ID), 1)){
	                    	safeAutocomplete(driver, By.id(Infant_Passport_Issuing_Country_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "India");
	                    }
	                    if(elementPresent_Time(driver, By.name(Infant_Passport_Exp_Day_ID), 1)){
	                    smartSelect(driver, By.name(Infant_Passport_Exp_Day_ID), "5");
	                    smartSelect(driver, By.id(Infant_Passport_Exp_Month_ID), Dob_month);
	                    smartSelect(driver, By.id(Infant_Passport_Exp_Year_ID), putYear(10));
	                    }
	            }
	        }
	    }
	    safeType(driver, By.id("contactPhoneNumber1"), "9986508905");
	}

	public void agencyAir_Account_Cancellation(RemoteWebDriver driver, String TripID) throws Exception,InterruptedException {
		safeClick(driver, getObject("Air_Agency_Confirmation_Trips_Link"));
		  if(!elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Header"), 30)){
			  refreshPage(driver);
		  }
		elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Header"), 30);
		elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), 30);
		safeType(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), TripID);
		safeClick(driver, getObject("Air_Agency_Trips_Page_Search_TripID_Submit"));
		safeClick(driver, getObject("Air_Agency_Trips_Page_Trip_Link"));
		
		for(int i=0; i<=2;i++) {
		if(elementPresent_Time(driver, getObject("Air_Agency_Trips_Cancel_One"), 5)) {
			break;
		}
		else if(textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 1)){
			Reporter.log("Oops! Cleartrip’s system is behaving badly :  message is displayed");
			Assert.assertTrue(false);
		}
		else refreshPage(driver);;
		}
		safeClick(driver, getObject("Air_Agency_Trips_Cancel_One"));
		safeClick(driver, By.xpath("//td/input"));
		safeClick(driver, getObject("Air_Agency_Trips_Cancel_Two"));
		elementPresent_Time(driver, By.xpath("//div[2]/div/h1"), 30);
		textAssert(driver, By.xpath("//div[2]/div/h1"), "Review cancellation & refund amount");
		safeClick(driver, By.id("cancel_button"));
		elementPresent_Time(driver, By.xpath("//div[3]/div/h1"), 10);
		Thread.sleep(2000);
		textAssert(driver, By.xpath("//div[3]/div/h1"), "Cancellation confirmation");
	}
	
	
	public void agencyAir_HQ_Cancellation(RemoteWebDriver driver, String TripID) throws Exception,InterruptedException {
		if (MakePaymentOnlyInQA2){
			hotelCom_Open_TripID_HQ(driver, TripID);
			safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
			safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
			safeClick(driver, By.id("signInButton"));
			Thread.sleep(5000);
			hotelCom_Open_TripID_HQ(driver, TripID);
			textPresent(driver, "Itinerary", 50);	
			
			for(int i =0; i<=2; i++) {
			if(!elementVisible(driver, By.cssSelector("a.float_right.control"), 30)) {
				refreshPage(driver);
			}
			}
		elementVisible(driver, By.cssSelector("a.float_right.control"), 20);
		safeClick(driver, By.cssSelector("a.float_right.control"));
		elementVisible(driver, By.xpath("//th/input"), 20);
		safeClick(driver, By.xpath("//th/input"));
		safeType(driver, By.id("add_note"), "test cancellation");
		
		safeClick(driver, By.id("cancel"));
		textPresent(driver, "Itinerary", 50);
		elementVisible(driver, By.xpath("//tr[3]/td[2]"), 20);
		String Trip_Status = getText(driver, By.xpath("//tr[3]/td[2]"));
		if(!Trip_Status.equals("Cancelled")) {
			Reporter.log("Trip is not cancelled, Status is displayed is : "+Trip_Status);
			Assert.assertTrue(false);
		}
		}
	}
	
	public Map<String, String> agencyHotel_SRP(RemoteWebDriver driver, String Hotel_Name, String Booking_Type) throws Exception {    
		Map<String, String> AgencySRPData = new HashMap<String , String>();	
	for(int i =0; i <= 10; i++){
	if(textPresent(driver, "Sorry, our system is acting up", 1)){
		Reporter.log("Not a Script Issue Displaying Sorry, our system is acting up : error is displayed in SRP");
		Assert.assertTrue(false);
	}
	else if(textPresent(driver, "Sorry, we couldn't find any hotels", 1)){
		Reporter.log("Not a Script Issue Sorry, we couldn't find any hotels : error is displayed in SRP");
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
	if (!Hotel_Name.isEmpty()) {
		elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 30);
		safeAutocomplete(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), getObjectHotels("HotelCom_SRP_HotelName_Ajax"), Hotel_Name);
	}
	if (Booking_Type.isEmpty()) {
	elementPresent(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
	Thread.sleep(2000);
	safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 
	safeClick(driver, getObject("AgencyHotels_SRP_SelectRoom_Button"));
	Thread.sleep(1000);
	if (!elementPresent_Time(driver, getObject("AgencyHotels_SRP_BookRoom_Button"), 10)) {
		safeClick(driver, getObject("AgencyHotels_SRP_SelectRoom_Button"));
	}
	safeClick(driver, getObject("AgencyHotels_SRP_BookRoom_Button"));
	}
	else if (Booking_Type.equalsIgnoreCase("SPECIALRATE")) {
		elementPresent(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"));
		for (int i = 1; i <= 15; i++) {
			String SpecialRateHotel_XPATH = "//li[" + i + "]/section/div[5]/div/span";
			String SpecialRate_SelectRoom_Xpath = "//li[" + i + "]/section/div[5]/div/button";
			if (elementPresent_Time(driver, By.xpath(SpecialRateHotel_XPATH), 1)) {
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
		Thread.sleep(10000);
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
		elementPresent_Time(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 20);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 
		safeClick(driver, getObject("AgencyHotels_SRP_SelectRoom_Button"));		
		String SRPPrice = getText(driver, By.xpath("//h2[2]/div/strong"));
		SRPPrice = SRPPrice.replace("Rs.", "");
		AgencySRPData.put("Name", getText(driver, By.xpath("//li/h2/a")));
		AgencySRPData.put("Price", SRPPrice);
		AgencySRPData.put("RoomType", getText(driver, By.xpath("//td/a")));
		safeClick(driver, getObject("AgencyHotels_SRP_BookRoom_Button"));		
		}
	return AgencySRPData;
}

	public void agencyHotel_SRP_RateType(RemoteWebDriver driver, String Hotel_Name, int Room_Type) throws Exception {
		if(textPresent(driver, "Sorry, we couldn't find any hotels", 5)){
			Reporter.log("Not a Script Issue Displaying Sorry, we couldn't find any hotels is displayed in SRP");
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
			String SelectRoom_XPath = "//li["+i+"]/section/div[5]/div/button";			
			safeClick(driver, By.xpath(SelectRoom_XPath));
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
		Reporter.log("Not a Script Issue Login Failed/ Homepage not Loaded");
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

	public void agencyHotel_Itinerarypage(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementPresent_Time(driver, getObject("AgencyHotels_Itinerarypage_PolicyLink"), 10);
		if(textPresent(driver, "Sorry, our system is acting up.", 1)){
			Reporter.log("Sorry, our system is acting up. : Error message is displayed in Itinerary page");
			logURL(driver);
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, getObject("AgencyHotels_Itinerarypage_ContinueButton"), "Itinerary Continue button", 10);
		
	/*	if(elementPresent_Time(driver, getObject("AgencyHotels_Itinerarypage_Hotel_Details"), 1)) {
			String HotelDetails = getText(driver, getObject("AgencyHotels_Itinerarypage_Hotel_Details"));
			String HotelName = getText(driver, getObject("AgencyHotels_Itinerarypage_Hotel_Name"));
			Reporter.log("Hotel Name : +HotelName");
			Reporter.log("Hotel Details : "+HotelDetails);	
		}*/
		safeClick(driver, getObject("AgencyHotels_Itinerarypage_ContinueButton"));
	}
	
	public void agencyHotel_Itinerarypage_Add_OntheGo_Markup(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementPresent(driver, getObject("AgencyHotels_Itinerarypage_PolicyLink"));
		Thread.sleep(2000);
		agency_Hotel_Add_OntheGo_Markup(driver);
		Thread.sleep(2000);
		safeClick(driver, getObject("AgencyHotels_Itinerarypage_ContinueButton"));
	}
	
	public void CTPhoneHotel_Itinerarypage_OntheGo_Discount(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementPresent(driver, getObject("AgencyHotels_Itinerarypage_PolicyLink"));
		Thread.sleep(2000);
		CTPhone_Hotel_OntheGo_Discount(driver);
		Thread.sleep(2000);
		safeClick(driver, getObject("AgencyHotels_Itinerarypage_ContinueButton"));
	}

	public void agencyHotel_Travellerpage(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementVisible(driver, getObject("AgencyHotels_Travellerpage_Title"), 30);
		safeSelect(driver, getObject("AgencyHotels_Travellerpage_Title"), dataFile.value("Title"));
		safeType(driver, getObject("AgencyHotels_Travellerpage_FirstName"), dataFile.value("First_Name_A1"));
		//safeType(driver, getObject("AgencyHotels_Travellerpage_LastName"), dataFile.value("Last_Name_A1"));
		safeType(driver, getObject("AgencyHotels_Travellerpage_LastName"), "Booking");
		//safeType(driver, getObject("AgencyHotels_Travellerpage_LastName"), "Spl Request - Continental Meals");
		if(elementVisible(driver, By.cssSelector("li.highlight"), 2)) {
			smartClick(driver, By.cssSelector("li.highlight"));
		}
		String URL = logURL(driver);
		smartClick(driver, getObject("AgencyHotels_Travellerpage_GST_CheckBox"));
		if(URL.contains("ctphone")) {
			safeType(driver, getObject("AgencyHotels_Travellerpage_PhoneNumber"), "9986696785");
		} else 
			safeType(driver, getObject("AgencyHotels_Travellerpage_PhoneNumber"), "1212121212");
		safeType(driver, getObject("AgencyHotels_Travellerpage_EmailID"), "automation@cleartrip.com");
		safeClick(driver, getObject("AgencyHotels_Travellerpage_ContinueButton"));
	}	
	
	public void agencyHotel_Travellerpage_GST(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementVisible(driver, getObject("AgencyHotels_Travellerpage_Title"), 30);
		safeSelect(driver, getObject("AgencyHotels_Travellerpage_Title"), dataFile.value("Title"));
		safeType(driver, getObject("AgencyHotels_Travellerpage_FirstName"), dataFile.value("First_Name_A1"));
		safeType(driver, getObject("AgencyHotels_Travellerpage_LastName"), "Booking");
		if(elementVisible(driver, By.cssSelector("li.highlight"), 2)) {
			smartClick(driver, By.cssSelector("li.highlight"));
		}
		elementPresent_log(driver, getObject("AgencyHotels_Travellerpage_GST_CheckBox"), "GST CheckBox", 10);
		safeType(driver, getObject("AgencyHotels_Travellerpage_GST_HolderName"), dataFile.value("HotelGSTHolderNumber"));
		safeType(driver, getObject("AgencyHotels_Travellerpage_GST_ID"), dataFile.value("HotelGSTNumber"));
		safeType(driver, getObject("AgencyHotels_Travellerpage_GST_HolderAddress"), "Cleartrip JP Nagar");
		//textAssert(driver, getObject(""), "");
		String URL = logURL(driver);
		if(URL.contains("ctphone")) {
			safeType(driver, getObject("AgencyHotels_Travellerpage_PhoneNumber"), "9986696785");
		} else 
		safeType(driver, getObject("AgencyHotels_Travellerpage_PhoneNumber"), "1212121212");
		safeType(driver, getObject("AgencyHotels_Travellerpage_EmailID"), "automation@cleartrip.com");
		
		
		safeClick(driver, getObject("AgencyHotels_Travellerpage_ContinueButton"));
	}	

	public void agencyHotel_Travellerpage_GST_State(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementVisible(driver, getObject("AgencyHotels_Travellerpage_Title"), 30);
		safeSelect(driver, getObject("AgencyHotels_Travellerpage_Title"), dataFile.value("Title"));
		safeType(driver, getObject("AgencyHotels_Travellerpage_FirstName"), dataFile.value("First_Name_A1"));
		safeType(driver, getObject("AgencyHotels_Travellerpage_LastName"), "Booking");
		if(elementVisible(driver, By.cssSelector("li.highlight"), 2)) {
			smartClick(driver, By.cssSelector("li.highlight"));
		}
		elementPresent_log(driver, getObject(""), "GST State Dropdown", 10);
		safeSelect(driver, getObject(""), (""));
		String URL = logURL(driver);
		if(URL.contains("ctphone")) {
			safeType(driver, getObject("AgencyHotels_Travellerpage_PhoneNumber"), "9986696785");
		} else 
		safeType(driver, getObject("AgencyHotels_Travellerpage_PhoneNumber"), "1212121212");
		safeType(driver, getObject("AgencyHotels_Travellerpage_EmailID"), "automation@cleartrip.com");
		
		
		safeClick(driver, getObject("AgencyHotels_Travellerpage_ContinueButton"));
	}	
	
	public String agencyHotel_Paymentpage(RemoteWebDriver driver, String PaymentType, String Coupon, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception, InterruptedException {
		String TripID = null;
		if(Coupon.equalsIgnoreCase("COUPON"))
		{
			safeType(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2B_Coupon"));
			safeClick(driver, getObject("AgencyHotels_PaymentPage_Coupon_Button"));
			Thread.sleep(2000);
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 30);
			textAssert(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), "Great, you are eligible to a cashback");
		}
		TripID = agencyPayment(driver, PaymentType, Trip_Logger_Msg, Booking_Confirmation_Message);
		if(!(PaymentType.equalsIgnoreCase("NETBANKING") ||PaymentType.equalsIgnoreCase("CTPAY") )){
		safeClick(driver, getObject("AgencyHotels_ConfirmationPage_SignOut_Link"));
		elementVisible(driver, getObject("Agency_SignIN_EmailID"), 30);
		}
		return TripID;
	}

	public String agencyHotel_Paymentpage_NoSignOut(RemoteWebDriver driver, String PaymentType, String Coupon, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception, InterruptedException {
		String TripID = null;
		if(Coupon.equalsIgnoreCase("COUPON"))
		{
			safeType(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelCom_Coupon"));
			safeClick(driver, getObject("AgencyHotels_PaymentPage_Coupon_Button"));
			Thread.sleep(2000);
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 30);
			textAssert(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), "Great, you are eligible to a cashback");
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
			safeClick(driver, getObject("AgencyHotels_PaymentPage_DepositAccount_Tab"));
		}
		else if(PaymentType.equalsIgnoreCase("CREDITCARD")){
			safeClick(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Number"),  dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Exp_Month"), "5");
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));			
			safeType(driver, getObject("AgencyHotels_PaymentPage_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Name"), dataFile.value("MasterCard_HolderName"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_FirstName"), "test");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_LastName"), "test");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_Address"), "JP Nagar");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_City"), "Bangalore");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_State"), "Karnataka");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_Pin"), "560076");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_Country"), "India");
			Thread.sleep(2000);
			}
		else if(PaymentType.equalsIgnoreCase("DEBITCARD")){
			safeClick(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Tab"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Number"),  dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Exp_Month"), "5");
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_BillName"), dataFile.value("MasterCard_HolderName"));
			Thread.sleep(2000);				
		}
		else if(PaymentType.equalsIgnoreCase("NETBANKING")){
			safeClick(driver, getObject("AgencyHotels_PaymentPage_NetBanking_Tab"));
			if(NBSite.equals("BOI")) {
				safeSelect(driver, getObject("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Bank of India");	
			}
			else if(NBSite.equals("SBI")) {
				safeSelect(driver, getObject("AgencyHotels_PaymentPage_NetBank_Dropdown"), "State Bank of India");
			}
			Thread.sleep(2000);
			
		}
		else if(PaymentType.equalsIgnoreCase("HOLD")){
			elementVisible(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 30);
			elementPresent(driver, By.xpath("//div[6]/div[4]/div[3]"));			
		}
		else if(PaymentType.equalsIgnoreCase("IVR")){
			safeClick(driver, getObject("AgencyHotels_PaymentPage_IVR_Tab"));
			String Total_Price = getText(driver, getObject("AgencyHotels_PaymentPage_Total_Price"));
			Total_Price = Total_Price.replace("Rs. ", "");
			if(Total_Price.contains(",")){
				Total_Price = Total_Price.replace(",", "");
			}
		//	int TotalPrice_Int = Integer.parseInt(Total_Price);
			safeType(driver, getObject("AgencyHotels_PaymentPage_IVR_Trx_RefNumber"), "707070");
			safeType(driver, getObject("AgencyHotels_PaymentPage_IVR_Amount"), Total_Price);
			safeType(driver, getObject("AgencyHotels_PaymentPage_IVR_Card_Number"), "5123456");
			safeType(driver, getObject("AgencyHotels_PaymentPage_IVR_GW_Trx_ID"), "505050");
			safeType(driver, getObject("AgencyHotels_PaymentPage_IVR_GW_Text"), "Cleartrip GW");
			safeType(driver, getObject("AgencyHotels_PaymentPage_IVR_Credential"), "Cleartrip Cred");
			safeType(driver, getObject("AgencyHotels_PaymentPage_IVR_Response_Msg"), "Cleartrip Msg");
			Thread.sleep(2000);
		}
		else if(PaymentType.equalsIgnoreCase("TECHPROCESS")){
			safeClick(driver, getObject("AgencyHotels_PaymentPage_TechProcess_Tab"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_TechProcess_Trx_Number"), "5123456");
			Thread.sleep(2000);
				
		}
		else if(PaymentType.equalsIgnoreCase("ITZ")){
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_CashCard_Tab"), 20);
			safeClick(driver, getObject("AgencyHotels_PaymentPage_CashCard_Tab"));			
		}
		else if(PaymentType.equalsIgnoreCase("CTPAY")){
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_CTPay_Tab"), 5);
			safeClick(driver, getObject("AgencyHotels_PaymentPage_CTPay_Tab"));
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_CTPay_CustomerID"), 30);
			safeType(driver, getObject("AgencyHotels_PaymentPage_CTPay_CustomerID"), "");
			Reporter.log("End of case - CTPAY cannot be verified with TripID");
			
		}
		else if(PaymentType.equalsIgnoreCase("NETBANKINGPROD")){
			safeClick(driver, getObject("AgencyHotels_PaymentPage_NetBanking_Tab"));
			if(NBSite.equals("BOI")) {
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Bank of India");
			}
			else if(NBSite.equals("SBI")) {
				safeSelect(driver, getObject("AgencyHotels_PaymentPage_NetBank_Dropdown"), "State Bank of India");
			}
			Thread.sleep(2000);	
			safeClick(driver, getObject("AgencyHotels_PaymentPage_BookButton"));
			if(NBSite.equals("BOI")) {
				if(elementPresent_Time(driver, By.linkText("Return to Biller Site"), 100)){
					Thread.sleep(2000);
					elementPresent(driver, By.linkText("Return to Biller Site"));				
					Reporter.log("BOI Netbanking Site is displayed");
					} 
				else {
					Reporter.log("Not a Script Issue Page has not redirected to BOI Netbanking Site");
					//Assert.assertTrue(false);
				}
						
				safeClick(driver, By.linkText("Return to Biller Site")); //BOI
		
				if(elementVisible(driver, By.xpath("//*[text()='Cancel']"),5)) {
					safeClick(driver,By.xpath("//*[text()='Cancel']"));
				}
				Thread.sleep(2000);
				
				if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
					Reporter.log("Sorry, our system is acting up. message is displayed");
					Assert.assertTrue(false);
				}
				if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
					Reporter.log("Not a Script Issue Page has not redirected to back to Cleartrip from Netbanking Site");
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
				Reporter.log("Not a Script Issue Page has not redirected to SBI Netbanking Site");
				Assert.assertTrue(false);
			}
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"));
			}
			if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 570)){
				Reporter.log("Page has not redirected to back to Cleartrip from SBI Netbanking Site");
				Assert.assertTrue(false);
			}
		
		}
		
		else if(PaymentType.equalsIgnoreCase("CTPAY")){
			safeClick(driver, getObject("AgencyHotels_PaymentPage_CTPay_Tab"));
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_CTPay_CustomerID"), 30);
			safeType(driver, getObject("AgencyHotels_PaymentPage_CTPay_CustomerID"), "getkirank@gmail.com");
		}
			
		if(elementPresent_Time(driver, getObject("AirCorpCom_TravellerPage_ItineraryBlock"), 1)) {
			String Itinerary = getText(driver, getObject("AirCorpCom_TravellerPage_ItineraryBlock"));
			Reporter.log("Itinerary : "+Itinerary);
		}	
		
//================================================Make Payment=========================================//	
	if(MakePaymentOnlyInQA2){
		if(PaymentType.equalsIgnoreCase("NETBANKING")) {
			Reporter.log("Payment Button is Clicked");
			safeClick(driver, getObject("AgencyHotels_PaymentPage_BookButton"));
			if(NBSite.equals("BOI")) {
			if(elementPresent_Time(driver, By.linkText("Return to Biller Site"), 100)){
				Thread.sleep(2000);
				elementPresent(driver, By.linkText("Return to Biller Site"));				
				Reporter.log("BOI Netbanking Site is displayed");
				} 
			else {
				Reporter.log("Not a Script Issue Page has not redirected to BOI Netbanking Site");
				Assert.assertTrue(false);
			}
			
			safeClick(driver, By.linkText("Return to Biller Site")); //BOI
			if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Not a Script Issue Displayed Sorry, our system is acting up. message is displayed in NBRetry Page");
				Assert.assertTrue(false);
			}
			if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 30)){
				Reporter.log("Not a Script Issue Page has not redirected back to Cleartrip from Netbanking Site");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "Oops, your payment didn’t work", 2)) {
				Reporter.log("Not a Script Issue Displayed Oops, your payment didn’t work : error is not displayed");
				//Assert.assertTrue(false);
			}
			
			
			}
			else if(NBSite.equals("SBI")) {
				if(elementPresent_Time(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"), 100)){
						Reporter.log("SBI Netbanking Site is displayed");
					} 
				else {
					Reporter.log("Not a Script Issue Page has not redirected to SBI Netbanking Site");
					Assert.assertTrue(false);
				}
				
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"));

				}
			if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Not a Script Issue Displayed Sorry, our system is acting up. message is displayed in NBRetry Page");
				Assert.assertTrue(false);
			}
			if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 30)){
				Reporter.log("Not a Script Issue Displayed Page has not redirected back to Cleartrip from Netbanking Site");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "Oops, your payment didn’t work", 2)) {
				Reporter.log("Not a Script Issue Displayed Oops, your payment didn’t work : error is not displayed");
				Assert.assertTrue(false);
			}
		}
		
		else if(PaymentType.equalsIgnoreCase("CTPAY")){
			Reporter.log("Payment Button is Clicked");
			safeClick(driver, getObject("AgencyHotels_PaymentPage_BookButton"));	 
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_CTPay_TripID"), 30);
			elementPresent(driver, getObject("AgencyHotels_PaymentPage_CTPay_TripID"));
			TripID = getText(driver, getObject("AgencyHotels_PaymentPage_CTPay_TripID"));
			}
		else if(PaymentType.equalsIgnoreCase("ITZ")){
			Reporter.log("Payment Button is Clicked");
			safeClick(driver, getObject("AgencyHotels_PaymentPage_BookButton"));	 

			elementVisible(driver,  By.xpath("//img"), 60);
			textPresent(driver, "Please select your mode of payment", 20);
			textAssert(driver, "Please select your mode of payment", 2);
			// Code to be added
			}
		
		
		else if(!(PaymentType.equalsIgnoreCase("NETBANKING") || PaymentType.equalsIgnoreCase("NETBANKINGPROD"))) {
			safeClick(driver, getObject("AgencyHotels_PaymentPage_BookButton"));	
			
			if(elementVisible(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 90))
			{
				elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);
				safeClick(driver, getObject("air_amex_payment_button"));
			}
			/*//elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);
			if(!elementPresent(driver,By.xpath("//*[contains(text(),'Great')]"),60)) {
			safeClick(driver, getObject("air_amex_payment_button"));
			}*/
			for (int i = 0; i < 20; i++) {
				if(elementVisible(driver, getObject("AgencyHotels_ConfirmationPage_TripID"), 2)){
					TripID = getText(driver, getObject("AgencyHotels_ConfirmationPage_TripID"));					
					break;
				}
				else if(textPresent(driver, "Proxy Error", 1)){
					Reporter.log("Not a Script Issue Proxy Error displayed, TripID not Genrated", true);
					Assert.assertTrue(false);
				}
				else if(textPresent(driver, "Forbidden", 1)){
					Reporter.log("Not a Script Issue Forbidden message is displayed, TripID not Genrated", true);
					Assert.assertTrue(false);
				}				
				else if(textPresent(driver, "Oops, your booking didn’t go through", 1)){
					Reporter.log("Not a Script Issue Displaying -Oops, your booking didn’t go through :  message is displayed, TripID not Genrated",true);
					Assert.assertTrue(false);
				}
				else if(textPresent(driver, "Sorry, our system is acting up", 1)){
					Reporter.log("Not a Script Issue Displaying Sorry, our system is acting up :  message is displayed, TripID not Genrated",true);
					Assert.assertTrue(false);
				}
				
				else if(textPresent(driver, "Oops! AgentBox system is behaving badly", 1)){
					Reporter.log("Not a Script Issue Displaying -Oops! AgentBox system is behaving badly :  message is displayed, TripID not Genrated",true);
					Assert.assertTrue(false);
				}
				else if(elementVisible(driver, By.xpath("(//a[contains(text(),'Modify search')])[2]"), 1)){
					Reporter.log("Page has Redirected to SRP",true);
					Assert.assertTrue(false);
				}
				
				
/*				else if(elementVisible(driver, getObject("AirCom_HomePage_From_TextBox"), 1)){
					Reporter.log("Page has Redirected to Homepage");
					Assert.assertTrue(false);
				}
				*/
			}
			Reporter.log(Trip_Logger_Msg + TripID,true );
			logger.info(Trip_Logger_Msg + TripID );
			logURL(driver);

			if(TripID.equals(null)){
				Reporter.log("TripID is null", true);
				Assert.assertTrue(false);
			}
			/*if(!elementVisible(driver, getObject("AgencyHotels_ConfirmationPage_TripID"), 10)){
				Reporter.log("Confirmation page is not displayed");
				Assert.assertTrue(false);
			}
			TripID = getText(driver, getObject("AgencyHotels_ConfirmationPage_TripID"));
			Reporter.log(Trip_Logger_Msg + TripID );
			logger.info(Trip_Logger_Msg + TripID );*/
			}		
	}
	
	return TripID;
	}
	
	public void agencyHotel_Acc_Cancellation(RemoteWebDriver driver, String TripID, Map<String, String> agencySRPData) throws Exception {
		  elementPresent_Time(driver, getObject("Air_Agency_Confirmation_Trips_Link"), 10);
		  safeClick(driver, getObject("Air_Agency_Confirmation_Trips_Link"));
		  refreshPage(driver);
		  for(int i = 0 ; i<2; i++){
			  if(!elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Header"), 20)){
				  refreshPage(driver);
			  }
			  if(!elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), 5)){
			  refreshPage(driver);
		  	 } else break;
		  }
		  elementPresent_Time(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), 30);
		  safeType(driver, getObject("Air_Agency_Trips_Page_Search_TripID"), TripID);
		  safeClick(driver, getObject("Air_Agency_Trips_Page_Search_TripID_Submit"));
		  safeClick(driver, getObject("Air_Agency_Trips_Page_Trip_Link"));
		  elementVisible(driver, By.linkText("Pricing & payment details"), 20);
		  safeClick(driver, By.linkText("Pricing & payment details"));
		  if(!elementVisible(driver, By.cssSelector("dd.total"), 10)){
			  safeClick(driver, By.linkText("Pricing & payment details"));
		  }
		  Map<String, String> agencyAccTripData = new HashMap<String, String >();
		  agencyAccTripData.put("Name", getText(driver, By.xpath("//td/div/h2")));
		  agencyAccTripData.put("RoomType", getText(driver, By.xpath("//table[3]/tbody/tr[2]/td")));
		  agencyAccTripData.put("Price", getText(driver, By.cssSelector("dd.total")));
		  
		  String SRPPrice = agencySRPData.get("Price");
		  int SRPPriceInt = Integer.parseInt(SRPPrice);
		  String AcctPrice = getText(driver, By.cssSelector("dd.total"));
		  AcctPrice = AcctPrice.replace("Rs. ", "");
		  int AcctPriceInt = Integer.parseInt(AcctPrice);
		  int HotelProcessingFeeInt = Integer.parseInt(HotelProcessingFee);
		  int TotalPrice = SRPPriceInt+HotelProcessingFeeInt;
		  /*System.out.println("SRPPriceInt : "+SRPPriceInt);
		  System.out.println("HotelProcessingFeeInt : "+HotelProcessingFeeInt);
		  System.out.println("AcctPriceInt : "+AcctPriceInt);*/
		  if(!(TotalPrice==AcctPriceInt)){
			  Reporter.log("Hotel Name doesnt Match 'SRP Price + Processing fee ' : "+TotalPrice+" & 'Accnt Trip Page Price ' : "+AcctPriceInt);
			 // Assert.assertTrue(false);
		  }
		  if(!agencySRPData.get("Name").equals(agencyAccTripData.get("Name"))){
			  Reporter.log("Hotel Name doesnt Match 'SRP Name ' : "+agencySRPData.get("Name")+" & 'Accnt Trip Page Name ' : "+agencyAccTripData.get("Name")  );
			  //Assert.assertTrue(false);
		  }
		  if(!agencySRPData.get("RoomType").equals(agencyAccTripData.get("RoomType"))){
			  Reporter.log("Hotel Name doesnt Match 'SRP RoomType ' : "+agencySRPData.get("RoomType")+" & 'Accnt Trip Page RoomType ' : "+agencyAccTripData.get("RoomType")  );
			  //Assert.assertTrue(false);
		  }
		
		  safeClick(driver, getObject("Air_Agency_Trips_Cancel_One"));
		  safeClick(driver, getObject("Air_Agency_Trips_Cancel_Hotel_Two"));
		  elementPresent_Time(driver, By.xpath("//div[2]/div/h1"), 30);
		  textAssert(driver, By.xpath("//div[2]/div/h1"), "Cancellation confirmation");
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
			
			  String SRPPrice = agencySRPData.get("Price");
			  int SRPPriceInt = Integer.parseInt(SRPPrice);
			  String AcctPrice = getText(driver, By.cssSelector("dd.total > strong"));
			  AcctPrice = AcctPrice.replace("Rs. ", "");
			  int AcctPriceInt = Integer.parseInt(AcctPrice);
			  int HotelProcessingFeeInt = Integer.parseInt(HotelProcessingFee);
			  int TotalPrice = SRPPriceInt+HotelProcessingFeeInt;

		/*	  if(!(TotalPrice==AcctPriceInt)){
				  Reporter.log("Hotel Name doesnt Match 'SRP Price + Processing fee ' : "+TotalPrice+" & 'HQ Trip Page Price ' : "+AcctPriceInt);
				  Assert.assertTrue(false);
			  }*/
			  if(!agencyHQTripData.get("Name").contains(agencySRPData.get("Name"))){
				  Reporter.log("Hotel Name doesn't Match 'SRP Name ' : "+agencySRPData.get("Name")+" & 'HQ Trip Page Name ' : "+agencyHQTripData.get("Name")  );
				  System.out.println("Hotel Name doesn't Match 'SRP Name ' : "+agencySRPData.get("Name")+" & 'HQ Trip Page Name ' : "+agencyHQTripData.get("Name")  );
					  Assert.assertTrue(false);
			  }
			  if(!agencySRPData.get("RoomType").equals(agencyHQTripData.get("RoomType"))){
				  Reporter.log("Hotel Name doesn't Match 'SRP RoomType ' : "+agencySRPData.get("RoomType")+" & 'HQ Trip Page RoomType ' : "+agencyHQTripData.get("RoomType")  );
				  Assert.assertTrue(false);
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
	
	public String agencyPayment_Hold(RemoteWebDriver driver) throws Exception, InterruptedException {
		String TripID = null;
		textPresent(driver, "Duration", 60);
		elementVisible(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 30);
		//elementPresent(driver, By.xpath("//div[6]/div[4]/div[3]"));
		elementPresent(driver, By.id("holdBookingFlash"));
		if(MakePaymentOnlyInQA2){
			safeClick(driver, getObject("AgencyHotels_PaymentPage_BookButton"));
		for(int i =0; i<=20; i++){
				if(textPresent(driver, "Your booking is on hold.", 1)){
					break;
				}
				else if (textPresent(driver, "Oops, your booking didn’t go through", 2)) {
						Reporter.log("Not a Script Issue Displaying --Oops, your booking didn’t go through : error is displayed");
						Assert.assertTrue(false);
				}
			}			
			elementPresent_Time(driver, getObject("AgencyHotels_ConfirmationPage_TripID"), 60);
			TripID = getText(driver, getObject("AgencyHotels_ConfirmationPage_TripID"));
			Reporter.log("Hold Trip ID : " + TripID );			
			Thread.sleep(2000);
		  	safeClick(driver, By.linkText("Trips"));
			elementPresent_Time(driver, By.xpath("//h1"), 30);
			if(textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 1)){
				Reporter.log("Oops! Cleartrip’s system is behaving badly :  message is displayed");
				Assert.assertTrue(false);
			}
			safeClick(driver, By.linkText("On Hold"));
			elementPresent_Time(driver, By.id("primary_search_trip_id"), 50);
			safeType(driver, By.id("primary_search_trip_id"), TripID);
			safeClick(driver, By.id("submit"));
			safeClick(driver, By.xpath("//p/a"));
			safeClick(driver, By.xpath("//div[6]/div/a/img"));			
		}
		return TripID;		
	}
	
	public String agencyPayment_Hold_Release(RemoteWebDriver driver) throws Exception, InterruptedException {
		String TripID = null;
		textPresent(driver, "Duration", 30);
		elementVisible(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 30);
		//elementPresent(driver, By.xpath("//div[6]/div[4]/div[3]"));
		elementPresent(driver, By.id("holdBookingFlash"));
		if(MakePaymentOnlyInQA2){
			safeClick(driver, getObject("AgencyHotels_PaymentPage_BookButton"));
			textPresent(driver, "Your booking is on hold.", 60);
			elementPresent_Time(driver, getObject("AgencyHotels_ConfirmationPage_TripID"), 120);
			TripID = getText(driver, getObject("AgencyHotels_ConfirmationPage_TripID"));
			Reporter.log("Hold Trip ID : " + TripID );			
			Thread.sleep(2000);
		  	safeClick(driver, By.linkText("Trips"));
			elementPresent_Time(driver, By.xpath("//h1"), 60);
			safeClick(driver, By.linkText("On Hold"));
			elementPresent_Time(driver, By.id("primary_search_trip_id"), 50);
			safeType(driver, By.id("primary_search_trip_id"), TripID);
			safeClick(driver, By.id("submit"));
			safeClick(driver, By.xpath("//p/a"));
			safeClick(driver, By.id("release_booking"));
			elementPresent_Time(driver, By.linkText("On Hold"), 60);
			elementPresent(driver, By.linkText("On Hold"));
			/*elementPresent_Time(driver, By.id("primary_search_trip_id"), 60);
			elementPresent(driver, By.id("primary_search_trip_id"));*/
			Reporter.log("Hold booking released TripID : "+TripID);
			logger.info("Hold booking released TripID : "+TripID);		
		}
		return TripID;
	}
	
	public void agencyContactDetails(RemoteWebDriver driver) throws Exception {
		safeSelect(driver, getObject("AgencyHotels_Travellerpage_Contact_Title"), "Mr.");
		safeType(driver, getObject("AgencyHotels_Travellerpage_Contact_FirstName"), "Automation");
		safeType(driver, getObject("AgencyHotels_Travellerpage_Contact_LastName"), "Automation");
		safeType(driver, getObject("AgencyHotels_Travellerpage_Contact_EmailID"), "automation@cleartrip.com");
		safeType(driver, getObject("AgencyHotels_Travellerpage_Contact_Address"), "JP Nagar");
		safeType(driver, getObject("AgencyHotels_Travellerpage_Contact_City"), "Bangalore");
		safeType(driver, getObject("AgencyHotels_Travellerpage_Contact_State"), "Karnataka");
		safeType(driver, getObject("AgencyHotels_Travellerpage_Contact_Pin"), "560076");
		safeType(driver, getObject("AgencyHotels_Travellerpage_Contact_Contry"), "India");
		safeType(driver, getObject("AgencyHotels_Travellerpage_Contact_Phone"), "1212121212");
	}
	
	public void agency_AddTask(RemoteWebDriver driver) throws Exception{
		textPresent(driver, "Search flights", 10);
		safeClick(driver, getObject("Agency_Task_Button"));
		textPresent(driver, "Show tasks assigned to:", 20);
		safeType(driver, getObject("Agency_Task_Description"), dataFile.value("Agency_Task_Description_Text"));
		safeClick(driver, getObject("Agency_Add_Task"));
		assertCommon(driver, "Show tasks assigned to:", 2, "Tasks not Added");
	}
	
	public void Agency_Message(RemoteWebDriver driver) throws Exception{
		textPresent(driver, "Search flights", 10);
		safeClick(driver, getObject("Agency_Task_Button"));
		textPresent(driver, "Show tasks assigned to:", 20);
		safeClick(driver, getObject("Agency_Message_Button"));
		assertCommon(driver, "Messages", 2, "Message Page not displayed");	
	}
	
	public void Agency_AddTraveller(RemoteWebDriver driver) throws Exception{
		textPresent(driver, "Search flights", 10);
		safeClick(driver, getObject("Agency_Add_Traveller"));
		textPresent(driver, "Mobile Number :", 20);
		safeClick(driver, getObject("Agency_New_Traveller"));
		textPresent(driver, "Add a new traveller", 20);
		safeType(driver, getObject("Agency_Add_First_Name_Traveller"), dataFile.value("Traveller_First_Name1"));
		safeType(driver, getObject("Agency_Add_Last_Name_Traveller"), dataFile.value("Traveller_Last_Name1"));
		safeType(driver, getObject("Agency_Traveller_Email"), dataFile.value("Traveller_Email1"));
		safeType(driver, getObject("Agency_Traveller_Phone"), dataFile.value("Traveller_Phone"));
		safeClick(driver, getObject("Agency_Create_Traveller"));
		
		driver.findElement(By.xpath("//a[contains(text(),'Travellers')]")).click();
		
		driver.findElement(By.id("SearchEmail")).sendKeys("priyanka.pukale@cleartrip.com");
		driver.findElement(By.id("submit")).click();
		textPresent(driver, "Priyanka Pukale:", 20);
		assertCommon(driver, "Mobile Number :", 2, "Traveller not Added");
		safeClick(driver,By.xpath("//a[contains(@href, '/signout')]"));
		Thread.sleep(2000);
		textPresent_Log(driver,"Sign in to your account",20);

	}
	
	public void agency_AddUser(RemoteWebDriver driver) throws Exception{
		textPresent(driver, "Search flights", 10);
		/*safeClick(driver, getObject("Agency_Task_Button"));
		textPresent(driver, "Show tasks assigned to:", 10);*/
		driver.findElement(By.id("userAccountLink")).click();
		driver.findElement(By.id("userAccountMenu")).click();
		safeClick(driver, getObject("Agency_Users"));
		textPresent(driver, "Users", 10);
		safeClick(driver, getObject("Agency_Invite_New_User"));
		textPresent(driver, "Add a user", 10);
		safeSelect(driver, getObject("Agency_New_User_Title"), "Mr");
		safeType(driver, getObject("Agency_Add_First_Name_User"), dataFile.value("User_First_Name"));
		safeType(driver, getObject("Agency_Add_Last_Name_User"), dataFile.value("User_Last_Name")+System.currentTimeMillis());
		safeType(driver, getObject("Agency_Set_User_Name"), dataFile.value("User_Name")+System.currentTimeMillis());
		safeType(driver, getObject("Agency_User_Password"), dataFile.value("User_Password"));
		safeType(driver, getObject("Agency_User_Password_Repeat"), dataFile.value("User_Password_Repeat"));
		safeClick(driver, getObject("Agency_User_Permission"));
		safeClick(driver, getObject("Agency_Add_User_Button"));
		assertCommon(driver, "Account owner", 60, "User not Added");
	}
	
	public void Agency_Currency(RemoteWebDriver driver) throws Exception{
		textPresent(driver, "Search flights", 10);
		safeClick(driver, getObject("Agency_Currency_DropDown"));
		textPresent(driver, "Pound Sterling", 10);
		assertCommon(driver, "Rand", 2, "Currency Drop Down not displayed");	
	}
	
	public void Agency_More(RemoteWebDriver driver) throws Exception{
		textPresent(driver, "Search flights", 10);
		WebDriverWait wait = new WebDriverWait(driver,30);
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("moreProductsLink")));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.id("moreProductsLink")));
		
		//safeClick(driver, getObject("Agency_More_Link"));
		textPresent(driver, "Air fare calendar", 50);
		safeClick(driver, getObject("Agency_AirFare_calendar"));
		assertCommon(driver, "Search best airfares", 60, "Air Fare Calendar not displayed");
		safeClick(driver, getObject("Agency_Flights"));
		textPresent(driver, "Search flights", 20);
		Thread.sleep(2000);
		
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("moreProductsLink")));
		//JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.id("moreProductsLink")));
		//safeClick(driver, getObject("Agency_More_Link"));
		textPresent(driver, "Air fare calendar", 20);
		safeClick(driver, getObject("Agency_ImportPNR"));
		assertCommon(driver, "Queue your Galileo PNR to our PCC", 600, "Import PNR page not displayed");
		safeClick(driver, getObject("Agency_Flights"));
		textPresent(driver, "Search flights", 20);
		//JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.id("moreProductsLink")));
		//safeClick(driver, getObject("Agency_More_Link"));
		textPresent(driver, "Air fare calendar", 20);
		safeClick(driver, getObject("Agency_ContactUs"));
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		assertCommon(driver, "Schedule a demo or get in touch for questions", 20, "Import PNR page not displayed");
		// Perform the actions on new window
		// Close the new window, if that window no more required
		driver.close();
		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		}
	
	public void Agency_MyProfile(RemoteWebDriver driver) throws Exception{
		textPresent(driver, "Search flights", 10);
		safeClick(driver, getObject("Agency_Task_Button"));
		textPresent(driver, "Show tasks assigned to:", 20);
		safeClick(driver, getObject("Agency_My_Profile"));
		textPresent(driver, "Contact info", 20);
		//elementPresent_Time(driver, getObject("Agency_User_Account_Permissions"), 20);
		assertCommon(driver, "Edit", 20, "Edit text not displayed");
		safeClick(driver, getObject("Agency_User_Account_Permissions"));
		textPresent(driver, "User Name", 20);
		assertCommon(driver, "Change password", 2, "Change password text not displayed");
		assertCommon(driver, "Repeat password", 2, "Repeat password text not displayed");
		safeClick(driver, getObject("Agency_Task_Reminders"));
		textPresent(driver, "Task reminders via email", 20);
		assertCommon(driver, "Cancel", 2, "Cancel Link not displayed");
	}

	public void Agency_Settings(RemoteWebDriver driver) throws Exception{
		textPresent(driver, "Search flights", 10);
		safeClick(driver, getObject("Agency_Task_Button"));
		textPresent(driver, "Show tasks assigned to:", 20);
		safeClick(driver, getObject("Agency_Setting"));
		textPresent(driver, "Account details & settings", 20);
		assertCommon(driver, "Your agency’s name", 2, "Your agency’s name text not displayed");
		assertCommon(driver, "Your agency’s website address", 2, "Your agency’s website address");
		safeClick(driver, getObject("Agency_DA_Settings"));
		textPresent(driver, "Deposit account activity", 20);
		assertCommon(driver, "Transaction ID", 2, "Transaction ID not displayed");
		safeClick(driver, getObject("Agency_Markup_Settings"));
		textPresent(driver, "Domestic flights markup", 20);
		assertCommon(driver, "For each airline", 2, "For each airline text not displayed");
		safeClick(driver, getObject("Agency_Profile_Settings"));
		textPresent(driver, "If you want to change your PAN number, please contact your account manager.", 20);
		assertCommon(driver, "Agency ID", 2, "Agency ID not displayed");
		safeClick(driver, getObject("Agency_DA_Split_Settings"));
		textPresent(driver, "Account owner", 20);
		assertCommon(driver, "Account owner", 2, "Account owner not displayed");
	
	}
	public void agencyHotel_HQ_TripTools(RemoteWebDriver driver) throws Exception{
		textPresent(driver, "Itinerary", 50);	
		elementPresent_log(driver, By.xpath("//*[@id='booking-source']/h2"), "Booking Source: Agentbox", 1);
		if(!textPresent(driver, "Agency Booking", 50)){
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, By.xpath("//*[@id='layer_1']/table[2]/tbody/tr[3]/td[5]"), "Trip Status Verified", 1);
		elementPresent_log(driver, By.id("tab_2"), "Note Tab Verify", 1);
		safeClick(driver, By.id("tab_2"));
		if(!textPresent(driver, "Add a note to this trip", 50))	{
		Assert.assertTrue(false);
		}
		elementPresent_log(driver, By.id("tab_3"), "Audit Trail Tab Verify", 1);
		safeClick(driver, By.id("tab_3"));
		if(!textPresent(driver, "Audit Trail", 50)){	
		Assert.assertTrue(false);
		}	
		elementPresent_log(driver,By.id("tab_4"), "Payment Details Tab Verify", 1);
		safeClick(driver, By.id("tab_4"));
		if(!textPresent(driver, "Send ct-pay email to the customer", 50)){	
		Assert.assertTrue(false);
		}
		elementPresent_log(driver,By.id("tab_1"), "Payment Details Tab Verify", 1);
		safeClick(driver, By.id("tab_1"));
			
		safeClick(driver, By.xpath("//a[contains(@bubbleblock,'pax_pricing_breakup')][1]"));
		safeClick(driver, By.xpath("//*[@id='ct_bubbleNode']/a"));
		
		safeClick(driver, By.xpath("//a[@title='Email trip details']"));
		safeType(driver, By.id("email"), dataFile.value("HotelCom_SendMail"));
		safeClick(driver, By.id("SendTicketButton"));
		if(!textPresent(driver, " We've sent the itinerary details in an email to", 50)){	
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//a[@title='SMS trip details']"));
		safeType(driver, By.id("mobile_number"), dataFile.value("HotelCom_SendSms"));
		safeClick(driver, By.id("SendSmsButton"));
		if(!textPresent(driver, "We've sent a SMS to", 50)){	
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//a[@title='Print tickets']"));
		driver.navigate().back();
		safeClick(driver, By.xpath("//*[@id='toolbox']/ul[1]/li[4]/a"));
		
		safeClick(driver, By.xpath("//a[@title='Email Sale Invoice']"));
		safeType(driver, By.id("email_sale_invoice"), dataFile.value("HotelCom_SendMail"));
		safeClick(driver, By.xpath("//div[@id='EmailSaleInvoice']/form/input[@id='SendTicketButton']"));
		if(!textPresent(driver, " We've sent the invoice details in an email to", 50)){	
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//*[@id='toolbox']/ul[1]/li[6]/a"));
		safeClick(driver, By.xpath("//*[@id='toolbox']/ul[1]/li[7]/a"));
	}
	

	public void hotelCom_AddCookie(RemoteWebDriver driver){
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
	
	
}