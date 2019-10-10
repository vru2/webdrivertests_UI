// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Dec, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package domainServices;

import org.testng.Assert;
import org.testng.AssertJUnit;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Corporate extends AirCommonMethod {
	/*private String baseUrl = "http://"+common.value("host")+common.value("url")+"com";
	private String baseUrl_AE = "http://"+common.value("host")+common.value("url")+"ae";
*/
	  public String DomFromSectors[] = new String[] {"BLR", "MAA", "DEL", "BOM", "HYD", "BLR", "MAA", "DEL", "BOM"};
	  public String DomToSectors[] = 		new String[] {"MAA", "DEL", "BOM", "HYD", "BLR",  "DEL", "BOM", "HYD", "BLR"};
	
	public String gds_airlines[] = new String[] { "Bangkok Airways", "Air India", "Jet Airways", "Jet Konnect", "Kingfisher",
			"Air India Express", "Air France", "Air Arabia", "British Airways", "Cathay Pacific", "Egypt Air", "Emirates",
			"Ethiopian Air", "Etihad Airways", "Gulf Air", "Kenya Airways", "Kuwait Airways", "Lufthansa", "Malaysia Airlines",
			"Multiple Carriers", "Myanmar Intl", "Oman Av", "Qantas Airways", "Qatar Airways", "Royal Jordanian", "SAS",
			"SilkAir", "Saudi Arabian Air", "Singapore Air", "South African", "SriLankan Airlines", "Swiss Intl Air",
			"Thai Airways", "Turkish Airlines", "Virgin Atlantic", "Air Asia", "All Nippon" };
	public String lcc_airlines[] = new String[] { "aerarann", "aerlingus", "air arabia", "air asia", "air berlin", "air costa",
			"air italy", "air mediterranee", "air one", "air southwest", "bahrain air", "belle air", "belle air europe",
			"blue air", "blue islands", "blue1", "bluexpress", "bmibaby", "calm air", "cebu pacific", "city jet", "condor",
			"corendon", "direkt flyg", "eagles airlines", "eastern airways", "ezy", "finn comm", "firefly", "fly baboo",
			"fly dubai", "flydubai", "fly thomas cook", "flybe", "frontier airlines", "german wings", "goair", "helvetic", "iceland express",
			"Indigo", "intersky", "Jazeera Airways", "jet2", "jetstar", "Jubba Airways", "kam air", "kulula", "malmo aviation",
			"mango", "manx2", "meridiana", "monarch airlines", "nas air", "nordkalottflyg", "norwegian", "ocean air", "onetime",
			"regional express", "ryan air", "SpiceJet","scoot", "skippers", "sky west", "skyways", "SpiceJet", "strategic", "thomson fly",
			"transavia", "trawel fly", "virgin blue", "volawindjet", "vueling", "webjet", "west jet", "wizz air" };

	public int number_gds_Airlines = gds_airlines.length;
	public boolean GDS_Flight, B2B_GDS_Flight = false;
	public String HotelProcessingFee ;

	private static String Corp_Url, Corp_AE_Url, Corp_SA_Url = null;

	public String Corp_Url() throws Exception {
		if (common.value("host").contains("qa2") ) {
			Corp_Url = dataFile.value("Corp_QA2");

		} 
		else if (common.value("host").equalsIgnoreCase("hf") ) {
			Corp_Url = dataFile.value("Corp_HF");

		}
		else if (common.value("host").contains("www")) {
			Corp_Url = dataFile.value("Corp_Prod");
		} else if (common.value("host").contains("beta")) {
			Corp_Url = dataFile.value("Corp_Beta");
		} else if (common.value("host").contains("stg1")) {
			Corp_Url = dataFile.value("Corp_Stg1");
		}
		return Corp_Url;
	}
	
	
	
	public String Corp_Plat_Url() throws Exception {
		if (common.value("host").contains("qa2") ) {
			Corp_Url = dataFile.value("Corp_Plat_QA2");

		} 
		else if (common.value("host").equalsIgnoreCase("hf") ) {
			Corp_Url = dataFile.value("Corp_HF");

		}
		else if (common.value("host").contains("www")) {
			Corp_Url = dataFile.value("Corp_Prod");
		} else if (common.value("host").contains("beta")) {
			Corp_Url = dataFile.value("Corp_Beta");
		} else if (common.value("host").contains("stg1")) {
			Corp_Url = dataFile.value("Corp_Stg1");
		}
		return Corp_Url;
	}
	
	public String Corp_PTA_Url() throws Exception {
		if (common.value("host").contains("qa2")  ) {
			Corp_Url = dataFile.value("Corp_PTA_QA2");

		} 
		else if (common.value("host").equalsIgnoreCase("hf") ) {
			Corp_Url = dataFile.value("Corp_PTA_HF");

		}
		return Corp_Url;
	}

	public String Corp_AE_Url() throws Exception {
		if (common.value("host").equalsIgnoreCase("qa2") ) {
			Corp_AE_Url = dataFile.value("Corp_AE_QA2");

		} 
		if (common.value("host").equalsIgnoreCase("hf") ) {
			Corp_AE_Url = dataFile.value("Corp_AE_HF");

		}
		else if (common.value("host").equalsIgnoreCase("www")) {
			Corp_AE_Url = dataFile.value("Corp_AE_Prod");
		} else if (common.value("host").equalsIgnoreCase("beta")) {
			Corp_AE_Url = dataFile.value("Corp_AE_Beta");
		} else if (common.value("host").equalsIgnoreCase("stg1")) {
			Corp_AE_Url = dataFile.value("Corp_AE_Stg1");
		}
		return Corp_AE_Url;
	}
	
	public String Corp_SA_Url() throws Exception {
		if (common.value("host").contains("qa2")) {
			Corp_SA_Url = dataFile.value("Corp_SA_QA2");

		} 
		else if (common.value("host").equalsIgnoreCase("hf") ) {
			Corp_SA_Url = dataFile.value("Corp_SA_HF");

		} else if (common.value("host").contains("www")) {
			Corp_SA_Url = dataFile.value("Corp_SA_Prod");
		} else if (common.value("host").contains("beta")) {
			
		} else if (common.value("host").contains("stg1")) {
			
		}
		return Corp_SA_Url;
	}
	
	
	
	public void corp_plat_SignIn(RemoteWebDriver driver) throws Exception {
		if(textPresent(driver, "Sorry, our system is acting up.", 1)) {
			Reporter.log("Sorry, our system is acting up. : message is displayed SignIN page");
			Assert.assertTrue(false);
		} else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)) {
			Reporter.log("Whatever you're looking for, isn't here : message is displayed SignIN page");
			Assert.assertTrue(false);
		}  
		if (!checkIfSignedCorp(driver)) {
			elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 10);
			if (common.value("host").contains("qa2")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_QA2_plat_Username"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_QA2_plat_Password"));
			}
			else if (common.value("host").contains("hf") ) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_HF_Username"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_HF_Password"));
			}
			else if (common.value("host").contains("www")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Prod_Username"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Prod_Password"));
			} else if (common.value("host").contains("beta")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Beta_Username"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Beta_Password"));
			} else if (common.value("host").contains("stg1")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Stg1_Username"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Stg1_Password"));
			} 
			safeClick(driver, getObject("CorpCom_SignIN_SignIN_Button"));
			//if (elementVisible(driver, By.id("userAccountLink"), 5)) {
		}
			if(textPresent(driver, "Read timed out", 2)|| elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 1)) {
				if (!checkIfSignedCorp(driver)) {
					elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 10);
					if (common.value("host").contains("qa2") || common.value("host").equalsIgnoreCase("hf") ) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_QA2_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_QA2_Password"));
					} else if (common.value("host").contains("www")) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Prod_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Prod_Password"));
					} else if (common.value("host").contains("beta")) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Beta_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Beta_Password"));
					} else if (common.value("host").contains("stg1")) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Stg1_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Stg1_Password"));
					}
					safeClick(driver, getObject("CorpCom_SignIN_SignIN_Button"));
					
					elementVisible(driver, By.xpath("//img"), 50);
					smartClick(driver, By.xpath("//img"));
				}
			}
			if (elementPresent_Time(driver, By.xpath("//a/ul/li"), 5)) {
					Reporter.log("login successfull");
			} else {
				Reporter.log("login failed");
				AssertJUnit.assertTrue("Failure!", false);
			}
		}
	
	
	
	

	public void corp_SignIn(RemoteWebDriver driver) throws Exception {
		if(textPresent(driver, "Sorry, our system is acting up.", 1)) {
			Reporter.log("Sorry, our system is acting up. : message is displayed SignIN page");
			Assert.assertTrue(false);
		} else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)) {
			Reporter.log("Whatever you're looking for, isn't here : message is displayed SignIN page");
			Assert.assertTrue(false);
		}  
		if (!checkIfSignedCorp(driver)) {
			elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 10);
			if (common.value("host").contains("qa2")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_QA2_Username"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_QA2_Password"));
			}
			else if (common.value("host").contains("hf") ) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_HF_Username"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_HF_Password"));
			}
			else if (common.value("host").contains("www")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Prod_Username"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Prod_Password"));
			} else if (common.value("host").contains("beta")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Beta_Username"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Beta_Password"));
			} else if (common.value("host").contains("stg1")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Stg1_Username"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Stg1_Password"));
			} 
			safeClick(driver, getObject("CorpCom_SignIN_SignIN_Button"));
			//if (elementVisible(driver, By.id("userAccountLink"), 5)) {
		}
			if(textPresent(driver, "Read timed out", 2)|| elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 1)) {
				if (!checkIfSignedCorp(driver)) {
					elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 10);
					if (common.value("host").contains("qa2") || common.value("host").equalsIgnoreCase("hf") ) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_QA2_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_QA2_Password"));
					} else if (common.value("host").contains("www")) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Prod_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Prod_Password"));
					} else if (common.value("host").contains("beta")) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Beta_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Beta_Password"));
					} else if (common.value("host").contains("stg1")) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Stg1_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Stg1_Password"));
					}
					safeClick(driver, getObject("CorpCom_SignIN_SignIN_Button"));
					
					elementVisible(driver, By.xpath("//img"), 50);
					smartClick(driver, By.xpath("//img"));
				}
			}
			if (elementPresent_Time(driver, By.xpath("//a/ul/li"), 5)) {
					Reporter.log("login successfull");
			} else {
				Reporter.log("login failed");
				AssertJUnit.assertTrue("Failure!", false);
			}
		}
	
	public String corpCom_Air_SrpUrl(RemoteWebDriver driver, String FromCity, String ToCity, String Adults, String Childrens, String Infants, String SearchType, String PrefAirline,  int FromDate, int ToDate) throws Exception {
		driver.get(Corp_Url());
		corp_SignIn(driver);
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = 	Corp_Url();
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
			Reporter.log("Provide vaild Search type in SRPURL method");
			Assert.assertTrue(false);
		}
		logURL(driver);
		SRP_URL = domainURL+URL_2;
		return SRP_URL;		
	}
	
	public String corpAE_Air_SrpUrl(RemoteWebDriver driver, String FromCity, String ToCity, String Adults, String Childrens, String Infants, String SearchType, String PrefAirline,  int FromDate, int ToDate) throws Exception {
		driver.get(Corp_AE_Url());
		corp_AE_SignIn(driver);
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = 	Corp_AE_Url();
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
			Reporter.log("Provide vaild Search type in SRPURL method");
			Assert.assertTrue(false);
		}
		logURL(driver);
		SRP_URL = domainURL+URL_2;
		return SRP_URL;		
	}
	
	public String corpSA_Air_SrpUrl(RemoteWebDriver driver, String FromCity, String ToCity, String Adults, String Childrens, String Infants, String SearchType, String PrefAirline,  int FromDate, int ToDate) throws Exception {
		driver.get(Corp_SA_Url());
		corp_SignIn_User(driver, "CorpSA");
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = 	Corp_SA_Url();
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
			Reporter.log("Provide vaild Search type in SRPURL method");
			Assert.assertTrue(false);
		}
		logURL(driver);
		SRP_URL = domainURL+URL_2;
		return SRP_URL;		
	}
	
	public String corpHotel_SrpUrl(RemoteWebDriver driver, String City, String State, String Country, String Adults, int FromDate, int ToDate) throws Exception {
		driver.get(Corp_Url());
		corp_SignIn(driver);	
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Corp_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		SRP_URL = domainURL+URL_2;
		logURL(driver);
		driver.get(SRP_URL);
		return SRP_URL;		
	}
	
	public String corpAEHotel_SrpUrl(RemoteWebDriver driver, String City, String State, String Country, String Adults, int FromDate, int ToDate) throws Exception {
		driver.get(Corp_AE_Url());
		corp_AE_SignIn(driver);
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Corp_AE_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		SRP_URL = domainURL+URL_2;
		logURL(driver);
		driver.get(SRP_URL);
		return SRP_URL;		
	}
	
	public String corpSAHotel_SrpUrl(RemoteWebDriver driver, String City, String State, String Country, String Adults, int FromDate, int ToDate) throws Exception {
		driver.get(Corp_SA_Url());
		corp_SignIn_User(driver, "CorpSA");
		String SRP_URL = null;	
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Corp_SA_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		SRP_URL = domainURL+URL_2;
		logURL(driver);
		driver.get(SRP_URL);
		return SRP_URL;		
	}
	
	public void corp_SignIn_User(RemoteWebDriver driver, String UserType) throws Exception {
		if(textPresent(driver, "Sorry, our system is acting up.", 1)) {
			Reporter.log("Sorry, our system is acting up. : message is displayed");
			Assert.assertTrue(false);
		} else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)) {
			Reporter.log("Whatever you're looking for, isn't here : message is displayed SignIN page");
			Assert.assertTrue(false);
		}  
		if (!checkIfSignedCorp(driver)) {
			elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 10);
			if(UserType.contains("BookOnly")) {
			if (common.value("host").contains("qa2")) {					
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_QA2_Username_BookOnly"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_QA2_Password_BookOnly"));
				}
				else if( common.value("host").contains("hf")){
					safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_HF_Username_BookOnly"));
					safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_HF_Password_BookOnly"));			
					}
				}
			else if(UserType.contains("BookOnlyAE")) {
				if (common.value("host").contains("qa2")) {					
					safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_QA2_Username_BookOnly"));
					safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_QA2_Password_BookOnly"));
					}
					else if( common.value("host").contains("hf")){
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_HF_Username_BookOnly"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_HF_Password_BookOnly"));			
						}
					}
				else if(UserType.contains("SearchOnly")) {
					if (common.value("host").contains("qa2")) {			
					safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_QA2_Username_SearchOnly"));
					safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_QA2_Password_SearchOnly"));
					}
					else if( common.value("host").contains("hf")){
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_HF_Username_SearchOnly"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_HF_Password_SearchOnly"));			
						}
				}
				else if(UserType.contains("PTABOOK")) {
					safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_QA2_Username_BookOnly"));
					safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_QA2_Password_BookOnly"));
					}
				else if(UserType.contains("PTAAPPROVER")) {
					safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_QA2_Username_PTA"));
					safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_QA2_Password_PTA"));
					}
				
				else if(UserType.contains("CorpSA")) {					
					if (common.value("host").contains("qa2")) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("CorpSA_QA2_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("CorpSA_QA2_Password"));
					}
					else if (common.value("host").contains("hf") ) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("CorpSA_HF_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("CorpSA_HF_Password"));
					}	else if (common.value("host").contains("www") ) {
						safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("CorpSA_Prod_Username"));
						safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("CorpSA_Prod_Password"));
					}
					}
			} else if (common.value("host").contains("www")) {
			} else if (common.value("host").contains("beta")) {
			} else if (common.value("host").contains("stg1")) {
			}
			safeClick(driver, getObject("CorpCom_SignIN_SignIN_Button"));
			if (elementVisible(driver, By.xpath("//*[@id='userAccountLink']/ul/li"), 10)) {
				Reporter.log("login successfull");
			} else {
				Reporter.log("login failed");
				AssertJUnit.assertTrue("Failure!", false);
			}
		}

	public boolean checkIfSignedCorp(RemoteWebDriver driver) throws Exception {
		if (waitElement(driver, getObject("CorpCom_SignIN_EmailID"), 3)) {
			return false;
		} else if (waitElement(driver, getObject("AirCorpCom_HomePage_Search_Button"), 3)) {
			return true;
		} else {
			Reporter.log("Corp page not loading.");
			AssertJUnit.assertTrue("Failure!", false);
		}
		return false;
	}

	public void corp_AE_SignIn(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 60);
		if (common.value("host").contains("qa2")  ) {
			safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("CorpAE_QA2_Username"));
			safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("CorpAE_QA2_Password"));
		}else if (common.value("host").equalsIgnoreCase("hf") ) {
			safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("CorpAE_HF_Username"));
			safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("CorpAE_HF_Password"));
		} else if (common.value("host").contains("www")) {
			safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("CorpAE_Prod_Username"));
			safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("CorpAE_Prod_Password"));
		} else if (common.value("host").contains("beta")) {
			safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("CorpAE_Beta_Username"));
			safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("CorpAE_Beta_Password"));
		} else if (common.value("host").contains("stg1")) {
			safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("CorpAE_Stg1_Username"));
			safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("CorpAE_Stg1_Password"));
		}
		safeClick(driver, getObject("CorpCom_SignIN_SignIN_Button"));
	}

	public void corpAir_Oneway_Search1(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date, String To_Date,
			String Adults, String Childrens, String Infants, String Pref_Airline) throws Exception {
		
		From_Date = getDate( "dd");
		From_Date = From_Date.substring(1);
		From_Date = "1"+From_Date;
		int DateInt = Integer.parseInt(From_Date);
		DateInt = DateInt+1;
		To_Date = Integer.toString(DateInt);
		safeAutocomplete(driver, getObject("AirCorpCom_HomePage_From_TextBox"), getObject("AirCorpCom_HomePage_From_Ajax"),
				FromCity);
		safeAutocomplete(driver, getObject("AirCorpCom_HomePage_To_TextBox"), getObject("AirCorpCom_HomePage_To_Ajax"), ToCity);
		selectCalendarDate(driver, getObject("AirCorpCom_HomePage_Departure_Cal"),
				getObject("AirCorpCom_HomePage_Departure_NextMonth"), 1, From_Date);
		safeSelect(driver, getObject("AirCorpCom_HomePage_DropDown_Adults"), Adults);
		safeSelect(driver, getObject("AirCorpCom_HomePage_DropDown_Childrens"), Childrens);
		safeSelect(driver, getObject("AirCorpCom_HomePage_DropDown_Infants"), Infants);
		if (!Pref_Airline.isEmpty()) {
			safeClick(driver, getObject("AirCorpCom_HomePage_MoreOption_Link"));
			safeAutocomplete(driver, getObject("AirCorpCom_HomePage_Prefered_Airline"),
					getObject("AirCorpCom_HomePage_Prefered_Airline_Ajax"), Pref_Airline);
		}
		safeClick(driver, getObject("AirCorpCom_HomePage_Search_Button"));
	}

	public void corpAir_RoundTrip_Search1(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,
			String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline,int attempt) throws Exception {
		Thread.sleep(2000);
		From_Date = getDate( "dd");
		From_Date = From_Date.substring(1);
		From_Date = "1"+From_Date;
		int DateInt = Integer.parseInt(From_Date);
		DateInt = DateInt+1;
		To_Date = Integer.toString(DateInt);
		safeClick(driver, getObject("AirCom_HomePage_Roundtrip_RadioButton"));
		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), getObject("AirCom_HomePage_From_Ajax"),
				FromCity);
		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"), ToCity);
		if (attempt == 0) {
			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),
					getObject("AirCom_HomePage_Departure_NextMonth"), 2, From_Date);
		} else {
			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),
					getObject("AirCom_HomePage_Departure_NextMonth"), 0, From_Date);
		}
		selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Return_Cal")),
				By.id(getValue("AirCom_HomePage_Departure_NextMonth")), 0, To_Date);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);
		if (Pref_Airline != "") {
			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {
				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));
			}
			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),
					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), Pref_Airline);
		}
		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));
	}
	
	public void corpAir_HomePage_Search(RemoteWebDriver driver, String SearchType, String FromCity, String ToCity,
			String Adults, String Childrens, String Infants, String Pref_Airline, int attempt) throws Exception {
		
		String From_Date = getDate( "dd");
		From_Date = From_Date.substring(1);
		From_Date = "1"+From_Date;
		int DateInt = Integer.parseInt(From_Date);
		DateInt = DateInt+1;
		String To_Date = Integer.toString(DateInt);
		
		if(SearchType.equalsIgnoreCase("ONEWAY")) {		
			safeAutocomplete(driver, getObject("AirCorpCom_HomePage_From_TextBox"), getObject("AirCorpCom_HomePage_From_Ajax"),	FromCity);
			safeAutocomplete(driver, getObject("AirCorpCom_HomePage_To_TextBox"), getObject("AirCorpCom_HomePage_To_Ajax"), ToCity);
			selectCalendarDate(driver, getObject("AirCorpCom_HomePage_Departure_Cal"), getObject("AirCorpCom_HomePage_Departure_NextMonth"), 2, From_Date);
			safeSelect(driver, getObject("AirCorpCom_HomePage_DropDown_Adults"), Adults);
			safeSelect(driver, getObject("AirCorpCom_HomePage_DropDown_Childrens"), Childrens);
			safeSelect(driver, getObject("AirCorpCom_HomePage_DropDown_Infants"), Infants);
				if (!Pref_Airline.isEmpty()) {
					safeClick(driver, getObject("AirCorpCom_HomePage_MoreOption_Link"));
					safeAutocomplete(driver, getObject("AirCorpCom_HomePage_Prefered_Airline"),
							getObject("AirCorpCom_HomePage_Prefered_Airline_Ajax"), Pref_Airline);
				}
			}
		
			else if(SearchType.equalsIgnoreCase("ROUNDTRIP")) {
				safeClick(driver, getObject("AirCom_HomePage_Roundtrip_RadioButton"));
				safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), getObject("AirCom_HomePage_From_Ajax"), FromCity);
				safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"), ToCity);
						if (attempt == 0) {
							selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),	getObject("AirCom_HomePage_Departure_NextMonth"), 1, From_Date);
							} 
						else {
							selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")), getObject("AirCom_HomePage_Departure_NextMonth"), 0, From_Date);
							}
				selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Return_Cal")), By.id(getValue("AirCom_HomePage_Departure_NextMonth")), 0, To_Date);
				safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);
				safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);
				safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);
					if (Pref_Airline != "") {
						if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {
							safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));
						}
						safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),getObject("AirCom_HomePage_Prefered_Airline_Ajax"), Pref_Airline);
					}
			}
		safeClick(driver, getObject("AirCorpCom_HomePage_Search_Button"));
	}
	
	public boolean checkFlightsCount(RemoteWebDriver driver) throws Exception {
		boolean flightsCountFlag = false;
		boolean flightsError = false;
		int counter = 0;
		// refreshing page to make sure indigo results are coming.
		Thread.sleep(1000);
		waitElement(driver, getObject("B2B_SRP_air_flightcount"), 2);
		try {
			while (!flightsCountFlag && !flightsError && counter < 5) {
				try {
					counter++;
					if (elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 1)) {
						flightsCountFlag = driver.findElement(getObject("B2B_SRP_air_flightcount")).getText().contains("of");
					}
					flightsError = elementVisible(driver, getObject("system_acting_error"), 1);
					if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
						Reporter.log("System Acting up error.");
						AssertJUnit.assertTrue("Failure!", false);
					}
				/*	else if(textPresent(driver, "Sorry, our system is acting up", 1)) {
						Reporter.log("Sorry, our system is acting up : Message is displayed");
						Assert.assertTrue(false);
					}*/
					Thread.sleep(1000);
				} catch (Exception e) {
					if (elementVisible(driver, By.id("FromTag"), 1))
						flightsError = true;
					continue;
				}

			}
			if (!flightsCountFlag && !flightsError) {
				Reporter.log("Error! Search not happening. Stuck at getting flight details!");
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		if (flightsError) {
			String errorTest = getText(driver, getObject("system_acting_error"));
			if (errorTest.contains("Sorry, our system is acting up")) {
				Reporter.log("System is acting up error has occurred. Exiting with screenshot");
				safeClick(driver, By.linkText("Try searching again"));
			} else if (errorTest.contains("Sorry, there aren't any flights available for your search")) {
				Reporter.log("No flights available for the search performed. Please try after some time.");
				safeClick(driver, By.linkText("Try searching again"));
			}
			return false;
		} else if (!flightsCountFlag)
			return false;
		return true;
	}

	public ArrayList<String> corpCom_DataVal_Dom_SRP(RemoteWebDriver driver, String FlightType,	ArrayList<String> SRP_Data) throws Exception, InterruptedException {
		
		logURL(driver);
		if (!checkFlightsCount(driver)) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
		}		
		elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div/p"), 40);

			corpAir_ConfirmSRPLoadComplete(driver);
			filterFlightsByLCCOrGDS1(driver, FlightType, 0);
			
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
				SRP_Data.add(getText(driver, By.xpath("//tbody/tr/th[7]"))); 		//Price
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
								
				/*System.out.println("flightName : "+SRP_Data.get(0));
				System.out.println("flightNo : "+SRP_Data.get(1) );
				System.out.println("flightBookType : "+SRP_Data.get(2));
				System.out.println("price SRp: "+SRP_Data.get(3));
				System.out.println("depYear SRp: "+SRP_Data.get(4));
				System.out.println("depDate SRp: "+SRP_Data.get(5));
				System.out.println("ArvYear SRp: "+SRP_Data.get(6));
				System.out.println("ArvDate SRp: "+SRP_Data.get(7));
				*/
				
				safeClick(driver, getObject("AirCorpCom_SRP_Book_Button"));
			
			
				return SRP_Data;
	}
	
	public ArrayList<String> corpCom_DataVal_Intl_SRP(RemoteWebDriver driver, String FlightType,	ArrayList<String> SRP_Data) throws Exception, InterruptedException {
		
		logURL(driver);
		if (!checkFlightsCount(driver)) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
		}		
		elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div/p"), 40);

			corpAir_ConfirmSRPLoadComplete(driver);

			elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 20);
			
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
				SRP_Data.add(getText(driver, By.xpath("//tbody[2]/tr/th[7]"))); 						//Price
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
								
				/*System.out.println("flightName : "+SRP_Data.get(0));
				System.out.println("flightNo : "+SRP_Data.get(1) );
				System.out.println("flightBookType : "+SRP_Data.get(2));
				System.out.println("price SRp: "+SRP_Data.get(3));
				System.out.println("depYear SRp: "+SRP_Data.get(4));
				System.out.println("depDate SRp: "+SRP_Data.get(5));
				System.out.println("ArvYear SRp: "+SRP_Data.get(6));
				System.out.println("ArvDate SRp: "+SRP_Data.get(7));*/
				
				/*if(elementVisible(driver, By.xpath("(//button[@type='submit'])[4]"), 2)) {
					safeClick(driver, By.xpath("(//button[@type='submit'])[4]"));
				}else safeClick(driver, By.xpath("(//button[@type='submit'])[3]"));
			*/
				String Hold_Book_Button = getText(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
				if(Hold_Book_Button.contains("Hold")) {
					safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button[2]"));
				}else	safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
			
				return SRP_Data;
	}

	public ArrayList<String>  corpCom_DataVal_TravellerPage(RemoteWebDriver driver, String Adults,String Childrens, String Infants, ArrayList<String> TravellerPage_Data)	throws Exception {
		elementVisible(driver, getObject("Air_Agency_TravellerPage_ContinueButton"), 20, "Traveller Page : ");
		corpAir_Dom_PaxEntry(driver, Adults, Childrens, Infants);
		String travellerName = driver.findElement(By.id("adultTraveller1")).getAttribute("value");
		//String travellerLName = driver.findElement(By.id("adult1'sLastName")).getAttribute("value");
		TravellerPage_Data.add(travellerName);
		//System.out.println("Traveller Name : "+TravellerPage_Data.get(0));
		
		Reporter.log("------------Traveller Page Details -----------");
		Reporter.log("Traveller Name : "+TravellerPage_Data.get(0));
		
		
		safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_ContactPerson_Name"), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("Corp_Adult1_Name"));
		waitElement(driver, getObject("AirCorpCom_TravellerPage_EmailID"), 5);
		Thread.sleep(2000);
		safeType(driver, getObject("AirCorpCom_TravellerPage_EmailID"), dataFile.value("Corp_QA2_Username"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_Address"), dataFile.value("Corp_Address"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_City"), dataFile.value("Corp_City"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_State"), dataFile.value("Corp_State"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_Pin"), dataFile.value("Corp_Pin"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_Country"), dataFile.value("Corp_Country"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_Phone"), dataFile.value("Corp_Traveller_Phone"));
		if (elementPresent_Time(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), 1)){
		safeType(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), dataFile.value("Corp_Spl_Request"));
		}		
		safeClick(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"));
		Thread.sleep(2000);	
		Reporter.log("Continue button on traveller details page clicked.");
		return TravellerPage_Data;
	}
	
	public ArrayList<String>  corpCom_DataVal_TravellerPage_Intl(RemoteWebDriver driver, String Adults,String Childrens, String Infants, ArrayList<String> TravellerPage_Data)	throws Exception {
		elementVisible(driver, getObject("Air_Agency_TravellerPage_ContinueButton"), 20, "Traveller Page : ");
		corpAir_Intl_PaxEntry(driver, Adults, Childrens, Infants);
		
		String travellerName = driver.findElement(By.id("adultTraveller1")).getAttribute("value");
		//String travellerLName = driver.findElement(By.id("adult1'sLastName")).getAttribute("value");
		TravellerPage_Data.add(travellerName);
		//System.out.println("Traveller Name : "+TravellerPage_Data.get(0));
		
		Reporter.log("------------Traveller Page Details -----------");
		Reporter.log("Traveller Name : "+TravellerPage_Data.get(0));
				
		safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_ContactPerson_Name"), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("Corp_Adult1_Name"));
		waitElement(driver, getObject("AirCorpCom_TravellerPage_EmailID"), 5);
		Thread.sleep(2000);
		safeType(driver, getObject("AirCorpCom_TravellerPage_EmailID"), dataFile.value("Corp_QA2_Username"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_Address"), dataFile.value("Corp_Address"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_City"), dataFile.value("Corp_City"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_State"), dataFile.value("Corp_State"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_Pin"), dataFile.value("Corp_Pin"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_Country"), dataFile.value("Corp_Country"));
		safeType(driver, getObject("AirCorpCom_TravellerPage_Phone"), dataFile.value("Corp_Traveller_Phone"));
		if (elementPresent_Time(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), 1)){
		safeType(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), dataFile.value("Corp_Spl_Request"));
		}		
		safeClick(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"));
		Thread.sleep(2000);	
		Reporter.log("Continue button on traveller details page clicked.");
		return TravellerPage_Data;
	}

	public ArrayList<String> corpCom_DataVal_PaymentPage(RemoteWebDriver driver, ArrayList<String> SRP_Data, ArrayList<String> TravellerPage_Data,  ArrayList<String> PaymentPage_Data) throws Exception, 	InterruptedException {
		if(MakePaymentOnlyInQA2){
		if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 10)){
					Reporter.log("PaymentPage is not displayed");
				}
				textPresent(driver, "How would you like to pay?", 20);
				safeClick(driver, getObject("AirCorpCom_PaymentPage_DepositAccount_Tab"));
				elementVisible(driver, By.xpath("//div[2]/dl/dd[1]"), 5);
				/*PaymentPage[0] = getText(driver, By.xpath("//div[2]/dl/dd[1]"));
				PaymentPage[1] = getText(driver, By.xpath("//div[2]/dl/dd[2]"));
				PaymentPage[2]  = getText(driver, By.id("formTotal"));	
				PaymentPage[2]  = PaymentPage[2].replace("Rs. ", "");
				PaymentPage[3]  = getText(driver, By.xpath("//div[2]/dl/dd[3]"));
				PaymentPage[4]  = getText(driver, By.xpath("//div[2]/dl/dd[4]"));
				PaymentPage[5] = getText(driver, By.xpath("//div[2]/div/dl/dd"));*/
				
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
			
/*
				System.out.println("Flight Name : "+PaymentPage_Data.get(0));
				System.out.println("Flight No : "+PaymentPage_Data.get(1));
				System.out.println("price : "+PaymentPage_Data.get(2));
				System.out.println("flightDepPayment : "+PaymentPage_Data.get(3));
				System.out.println("flightArvPayment : "+PaymentPage_Data.get(4));
				System.out.println("namePayment : "+PaymentPage_Data.get(5));*/
				
				if(!PaymentPage_Data.get(1).contains(SRP_Data.get(1))) {
					//System.out.println("Flight no : Failed");
					Reporter.log("Flight No in SRP & Payment page don't match 'Flight No SRP -' "+SRP_Data.get(1)+" 'Flight No Payment Step -' "+PaymentPage_Data.get(1));
					Assert.assertTrue(false);
				}
				if(!SRP_Data.get(3).contains(PaymentPage_Data.get(2))) {
					//System.out.println("price : Failed");
					Reporter.log("Price in SRP & Payment page don't match 'Price in SRP -' "+SRP_Data.get(3)+" 'Price in Payment Step -' "+PaymentPage_Data.get(2));
					//Assert.assertTrue(false);
				}
				if(!TravellerPage_Data.get(0).contains(PaymentPage_Data.get(5))) {
					//System.out.println("TravellerName : Failed");
					Reporter.log("Traveller Name in Traveller Page & Payment page don't match 'Traveller Name in Traveller -' "+TravellerPage_Data.get(0)+" ' Payment Step -' "+PaymentPage_Data.get(5));
					Assert.assertTrue(false);
				}
				if(!SRP_Data.get(0).contains(PaymentPage_Data.get(0))) {
					//System.out.println("Flight name : failed");
					Reporter.log("Flight Name in SRP & Payment page don't match 'Flight Name in Traveller -' "+SRP_Data.get(0)+" ' Payment Step -' "+PaymentPage_Data.get(0));
					Assert.assertTrue(false);
				}
				if(!(PaymentPage_Data.get(3).contains(SRP_Data.get(4)) && PaymentPage_Data.get(3).contains(SRP_Data.get(5)))) {
					//System.out.println("Flight Dep : Failed");
					Reporter.log("Flight Dep Date in SRP & Payment page don't match 'Flight Dep in SRP -' "+SRP_Data.get(4)+" ' Payment Step -' "+PaymentPage_Data.get(4));
					Assert.assertTrue(false);
				}
				if(!(PaymentPage_Data.get(4).contains(SRP_Data.get(6)) && PaymentPage_Data.get(4).contains(SRP_Data.get(7)))) {
					//System.out.println("Flight Arv : Failed");
					Reporter.log("Flight Arv Date in SRP & Payment page don't match 'Flight Arv in SRP -' "+SRP_Data.get(6)+" ' Payment Step -' "+PaymentPage_Data.get(4));
					Assert.assertTrue(false);
				}
		}
	return PaymentPage_Data;
	}

	public ArrayList<String> corpCom_DataVal_PaymentPage_Intl(RemoteWebDriver driver, ArrayList<String> SRP_Data, ArrayList<String> TravellerPage_Data,  ArrayList<String> PaymentPage_Data) throws Exception, 	InterruptedException {
		if(MakePaymentOnlyInQA2){
		if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 20)){
					Reporter.log("PaymentPage is not displayed");
				}
				textPresent(driver, "How would you like to pay?", 20);
				safeClick(driver, getObject("AirCorpCom_PaymentPage_DepositAccount_Tab"));
				elementVisible(driver, By.xpath("//div[2]/dl/dd[1]"), 5);
				/*PaymentPage[0] = getText(driver, By.xpath("//div[2]/dl/dd[1]"));
				PaymentPage[1] = getText(driver, By.xpath("//div[2]/dl/dd[2]"));
				PaymentPage[2]  = getText(driver, By.id("formTotal"));	
				PaymentPage[2]  = PaymentPage[2].replace("Rs. ", "");
				PaymentPage[3]  = getText(driver, By.xpath("//div[2]/dl/dd[3]"));
				PaymentPage[4]  = getText(driver, By.xpath("//div[2]/dl/dd[4]"));
				PaymentPage[5] = getText(driver, By.xpath("//div[2]/div/dl/dd"));*/
				
				PaymentPage_Data.add(getText(driver, By.xpath("//div[2]/dl/dd[1]")));
				PaymentPage_Data.add(getText(driver, By.xpath("//div[2]/dl/dd[2]")));
				String Price = getText(driver, By.id("formTotal"));
				PaymentPage_Data.add(Price.replace("Rs. ", ""));
				PaymentPage_Data.add(getText(driver, By.xpath("//div[2]/dl/dd[3]")));
				PaymentPage_Data.add(getText(driver, By.xpath("//div[2]/dl/dd[4]")));
				PaymentPage_Data.add(getText(driver, By.xpath("//div[3]/div/div[2]/div/dl/dd")));
				

				Reporter.log("------------Payment Page Details -----------");
				Reporter.log("Flight Name : "+PaymentPage_Data.get(0));
				Reporter.log("Flight No : "+PaymentPage_Data.get(1));
				Reporter.log("Price : "+PaymentPage_Data.get(2));
				Reporter.log("Dep Time : "+PaymentPage_Data.get(3));
				Reporter.log("Arv Time : "+PaymentPage_Data.get(4));
				Reporter.log("Traveller Payment : "+PaymentPage_Data.get(5));
				
				/*			//System.out.println("price : "+PaymentPage[2]);
				//System.out.println("flightDepPayment : "+PaymentPage[3]);
				//System.out.println("flightArvPayment : "+PaymentPage[4]);
				//System.out.println("namePayment : "+PaymentPage[5]);
				

				System.out.println("Flight Name : "+PaymentPage_Data.get(0));
				System.out.println("Flight No : "+PaymentPage_Data.get(1));
				System.out.println("price : "+PaymentPage_Data.get(2));
				System.out.println("flightDepPayment : "+PaymentPage_Data.get(3));
				System.out.println("flightArvPayment : "+PaymentPage_Data.get(4));*/
				//System.out.println("namePayment : "+PaymentPage_Data.get(5));
				
				if(!PaymentPage_Data.get(1).contains(SRP_Data.get(1))) {
					//System.out.println("Flight no : Failed");
					Reporter.log("Flight No in SRP & Payment page don't match 'Flight No SRP -' "+SRP_Data.get(1)+" 'Flight No Payment Step -' "+PaymentPage_Data.get(1));
					Assert.assertTrue(false);
				}
				if(!SRP_Data.get(3).contains(PaymentPage_Data.get(2))) {
					//System.out.println("price : Failed");
					Reporter.log("Price in SRP & Payment page don't match 'Price in SRP -' "+SRP_Data.get(3)+" 'Price in Payment Step -' "+PaymentPage_Data.get(2));
					//Assert.assertTrue(false);
				}
				if(!TravellerPage_Data.get(0).contains(PaymentPage_Data.get(5))) {
					//System.out.println("TravellerName : Failed");
					Reporter.log("Traveller Name in Traveller Page & Payment page don't match 'Traveller Name in Traveller -' "+TravellerPage_Data.get(0)+" ' Payment Step -' "+PaymentPage_Data.get(5));
					Assert.assertTrue(false);
				}
				if(!SRP_Data.get(0).contains(PaymentPage_Data.get(0))) {
					//System.out.println("Flight name : failed");
					Reporter.log("Flight Name in SRP & Payment page don't match 'Flight Name in Traveller -' "+SRP_Data.get(0)+" ' Payment Step -' "+PaymentPage_Data.get(0));
					Assert.assertTrue(false);
				}
				if(!(PaymentPage_Data.get(3).contains(SRP_Data.get(4)) && PaymentPage_Data.get(3).contains(SRP_Data.get(5)))) {
					System.out.println("Flight Dep : Failed");
					Reporter.log("Flight Dep Date in SRP & Payment page don't match 'Flight Dep in SRP -' "+SRP_Data.get(4)+" ' Payment Step -' "+PaymentPage_Data.get(4));
					Assert.assertTrue(false);
				}
				if(!(PaymentPage_Data.get(4).contains(SRP_Data.get(6)) && PaymentPage_Data.get(4).contains(SRP_Data.get(7)))) {
					System.out.println("Flight Arv : Failed");
					Reporter.log("Flight Arv Date in SRP & Payment page don't match 'Flight Arv in SRP -' "+SRP_Data.get(6)+" ' Payment Step -' "+PaymentPage_Data.get(4));
					//Assert.assertTrue(false);
				}
		}
				return PaymentPage_Data;
	}

	public ArrayList<String> corpCom_DataVal_ConfirmationPage(RemoteWebDriver driver, ArrayList<String> SRP_Data,	ArrayList<String> PaymentPage_Data, ArrayList<String> ConfirmationPage_Data)	throws Exception {
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
		Reporter.log("Price : "+ConfirmationPage_Data.get(2));
		Reporter.log("SeatType : "+ConfirmationPage_Data.get(3));
		Reporter.log("Traveller Name : "+ConfirmationPage_Data.get(4));
		Reporter.log("Dep Time : "+ConfirmationPage_Data.get(5));
		Reporter.log("Arv Time : "+ConfirmationPage_Data.get(6));
		/*

		System.out.println("flightNoConfirmation : "+ConfirmationPage_Data.get(0));
		System.out.println("flightNameConfirmation : "+ConfirmationPage_Data.get(1));
		System.out.println("priceConfirmation : "+ConfirmationPage_Data.get(2));
		System.out.println("seatTypeConfirmation : "+ConfirmationPage_Data.get(3));
		System.out.println("nameConfirmation : "+ConfirmationPage_Data.get(4));
		System.out.println("flightDepConfirmation : "+ConfirmationPage_Data.get(5));
		System.out.println("flightArvConfirmation : "+ConfirmationPage_Data.get(6));*/
		
		if(!ConfirmationPage_Data.get(0).contains(SRP_Data.get(1))) {
			//System.out.println("Flight No : Failed");
			Reporter.log("Flight No in Confirmation & SRP page don't match 'Flight No in SRP -' "+SRP_Data.get(1)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(0));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(1).contains(SRP_Data.get(0))) {
			//System.out.println("Flight Name : Failed");
			Reporter.log("Flight Name in Confirmation & SRP page don't match 'Flight Name in SRP -' "+SRP_Data.get(0)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(1));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(3).contains(SRP_Data.get(2))) {
			//System.out.println("Flight SeatType : Failed");
			Reporter.log("Flight SeatType in Confirmation & SRP page don't match 'Flight SeatType in SRP -' "+SRP_Data.get(0)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(3));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(4).contains(PaymentPage_Data.get(5))) {
			//System.out.println("Traveller name : Failed");
			Reporter.log("Traveller Name in Confirmation & SRP page don't match 'Traveller Name in payment page -' "+PaymentPage_Data.get(5)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(4));
			//Assert.assertTrue(false);
		}
		
		if(!(ConfirmationPage_Data.get(5).contains(SRP_Data.get(4)) && ConfirmationPage_Data.get(5).contains(SRP_Data.get(5)))) {
			//System.out.println("Flight Dep : Failed");
			Reporter.log("Flight Dep in Confirmation & SRP page don't match 'Flight Dep in SRP page -' "+SRP_Data.get(5)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(5));
			Assert.assertTrue(false);
		}
		
		if(!(ConfirmationPage_Data.get(6).contains(SRP_Data.get(6)) && ConfirmationPage_Data.get(6).contains(SRP_Data.get(7)))){
			//System.out.println("Flight Arv : Failed");
			Reporter.log("Flight Arv in Confirmation & SRP page don't match 'Flight Arv in SRP page -' "+SRP_Data.get(6)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(7));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(2).contains(PaymentPage_Data.get(2))){
			//System.out.println("Price : Failed");
			Reporter.log("Price in Confirmation & SRP page don't match 'Price in SRP page -' "+PaymentPage_Data.get(2)+" ' Confirmation Page -' "+ConfirmationPage_Data.get(2));
			//Assert.assertTrue(false);
		}
		
		
		return ConfirmationPage_Data;
	}



	public ArrayList<String> corpCom_DataVal_HQ(RemoteWebDriver driver, ArrayList<String> SRP_Data,	ArrayList<String> PaymentPage_Data, ArrayList<String> ConfirmationPage_Data, ArrayList<String> HQ_Data, String TripID)
			throws Exception, InterruptedException {
		if(common.value("host").equals("qa2")){
			driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);
		} else if(common.value("host").equals("hf")){
			driver.get("https://hf.cleartrip.com/hq/trips/"+TripID);
		}
		
		//driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);
		//hotelCom_Open_TripID_HQ(driver, "Q1610210012");
		safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
		safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
		safeClick(driver, By.id("signInButton"));
		Thread.sleep(5000);
		//System.out.println(TripID);
		hotelCom_Open_TripID_HQ(driver, TripID);	
		String Price = getText(driver, By.cssSelector("dd.total > strong"));
		//String SeatTypeXpath = "//th[7]";
		String SeatType = getText(driver, By.xpath("//th[7]"));
		String SeatTypeXpath2 = null;
		if(SeatType.contains("Class")) {
			SeatTypeXpath2 = "//td[7]";
		} else SeatTypeXpath2 = "//td[8]";
		
		HQ_Data.add(Price.replace("Rs. ", ""));
		HQ_Data.add(getText(driver, By.xpath("//tr[4]/td")));
		HQ_Data.add(getText(driver, By.xpath("//td[2]")));
		HQ_Data.add(getText(driver, By.xpath("//td[4]")));
		HQ_Data.add(getText(driver, By.xpath("//tr[3]/td[3]")));
		HQ_Data.add(getText(driver, By.xpath("//tr[3]/td[8]")));
		HQ_Data.add(getText(driver, By.xpath("//td[3]")));	
		
		HQ_Data.add(getText(driver, By.xpath(SeatTypeXpath2)));
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
					/*
		System.out.println("Price : "+HQ_Data.get(0));
		System.out.println("Flight Arv : "+HQ_Data.get(1));
		System.out.println("Flight Dep : "+HQ_Data.get(2));
		System.out.println("Flight No : "+HQ_Data.get(3));
		System.out.println("Flight Name : "+HQ_Data.get(6));
		System.out.println("SeatType : "+HQ_Data.get(7));
		System.out.println("PNR : "+HQ_Data.get(4));
		System.out.println("Ticket No : "+HQ_Data.get(5));
		System.out.println("Traveller Name : "+HQ_Data.get(8));*/
		
		if(textPresent(driver, "Some transactions in this booking are open", 5)) {
			Reporter.log("Some transactions in this booking are open. : PNR & Ticket no will not be generated");			
		}
		
		if(!ConfirmationPage_Data.get(2).contains(HQ_Data.get(0))) {
			//System.out.println("Price : Failed");
			Reporter.log("Price in Confirmation & HQ page don't match 'Price in Confirmation page -' "+ConfirmationPage_Data.get(2)+" ' HQ Page -' "+HQ_Data.get(0));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(3).contains(HQ_Data.get(7))) {
			//System.out.println("Seat : Failed");
			Reporter.log("SeatType in Confirmation & HQ page don't match 'SeatType Confirmation page -' "+ConfirmationPage_Data.get(3)+" ' HQ Page -' "+HQ_Data.get(7));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(0).contains(HQ_Data.get(3))) {
			//System.out.println("Flight No : Passed");
			Reporter.log("Flight No in Confirmation & HQ page don't match 'Flight No Confirmation page -' "+ConfirmationPage_Data.get(0)+" ' HQ Page -' "+HQ_Data.get(3));
			Assert.assertTrue(false);
		}
		
		if(!ConfirmationPage_Data.get(1).contains(HQ_Data.get(6))) {
			//System.out.println("Flight Name : Failed");
			Reporter.log("Flight Name in Confirmation & HQ page don't match 'Flight Name Confirmation page -' "+ConfirmationPage_Data.get(1)+" ' HQ Page -' "+HQ_Data.get(6));
			Assert.assertTrue(false);
		}			

		if(!HQ_Data.get(8).contains(PaymentPage_Data.get(5))) {
			//System.out.println("Name : Failed");
			Reporter.log("Traveller Name in Payment page & HQ page don't match 'Traveller Name Payment page -' "+PaymentPage_Data.get(5)+" ' HQ Page -' "+HQ_Data.get(8));
			Assert.assertTrue(false);
		}
		
		if(!(HQ_Data.get(1).contains(SRP_Data.get(5)))) {
			//System.out.println("Arv : Failed");
			Reporter.log("Flight Arv in SRP & HQ page don't match 'Flight Arv SRP page -' "+SRP_Data.get(4)+" ' HQ Page -' "+HQ_Data.get(1));
			System.out.println("Flight Arv in SRP & HQ page don't match 'Flight Arv SRP page -' "+SRP_Data.get(4)+" ' HQ Page -' "+HQ_Data.get(1));
		
		}
		
		if(!(HQ_Data.get(2).contains(SRP_Data.get(7)))) {
			//System.out.println("Dep : Failed");
			Reporter.log("Flight Dep in SRP & HQ page don't match 'Flight Dep SRP page -' "+SRP_Data.get(6)+" ' HQ Page -' "+HQ_Data.get(2));
			Assert.assertTrue(false);
		}
		return HQ_Data;
	}

	
	public void removeJet(RemoteWebDriver driver) throws Exception {
		List<WebElement> flights = null;
		if (waitElement(driver, getObject("AirCom_SRP_Flights_List_Prefix"), 5)) {
			flights = driver.findElements(getObject("AirCom_SRP_Flights_List_Prefix"));
		} else {
			Reporter.log("Not able to locate list of available flight names.");
			AssertJUnit.assertTrue("Failure!", false);
		}

		flights.remove(0);
		for (WebElement flight : flights) {
			String flight_name = flight.findElement(getObject("AirCom_SRP_Flights_List_Suffix")).getText();
			if (flight_name.contains("Jet Airways") || flight_name.equals("JetKonnect")) {
				flight.findElement(getObject("AirCom_SRP_Flights_Checkbox")).click();
			}
		}

		if (checkWarning(driver)) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
			AssertJUnit.assertTrue("Failure!", false);
		}

		Reporter.log("Removed Jet and JetKonnnect flights.");

		if (checkWarning(driver)) {
			AssertJUnit.assertTrue("Failure!", false);
		}
	}

	public boolean checkWarning(RemoteWebDriver driver) {
		By warning = By.className("warningMessage");
		boolean warningFound = false;
		try {
			if (waitElement(driver, warning, 2)) {
				Reporter.log("No results found for the specified criteria.");
				warningFound = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return warningFound;
	}

	public void corpAir_SRP_loadConfirmation(RemoteWebDriver driver) throws Exception {
		for (int i = 0; i <= 2; i++) {
			refreshPage(driver);
			// Thread.sleep(10000);
			if (waitElement(driver, getObject("AirCorpCom_SRP_Book_Button"), 20)) {
				Reporter.log("Book button found with attempt no " + i);
				break;
			} else {
				Reporter.log("Search not happening, didn't find book button! Attempting refresh.");
			}
		}

		if (!checkFlightsCount(driver)) {
			Reporter.log("No results found for this search.");
			Assert.assertTrue(false);
		}
	}

	public void corpNoResultsFound(RemoteWebDriver driver) throws Exception {
		if(!elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 20)){
		for(int i=0; i<=5; i++){
		if(elementPresent_Time(driver, By.id("link=Try searching again"), 1)){
			refreshPage(driver);
		}
		
		else break;
		}
		}
		if(textPresent(driver, "We couldn't find flights to match your filters", 1)) {
			Reporter.log("We couldn't find flights to match your filters : message is displayed ( flights not displayed");
			Assert.assertTrue(false);
		}
	}
	
	public void AirCorp_SRP_Int_Hold_Oneway(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 60);
		corpNoResultsFound(driver);
		elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 50);
		elementPresent_Time(driver, getObject("AirCorp_SRP_Oneway_BookButton"), 50);
OuterLoop :for(int j=0; j<=2;j++) {
		for (int i=3; i<=16;i++) {
			if(elementVisible(driver, By.xpath("(//button[@type='submit'])["+i+"][contains(text(),'Hold')]"), 1)){
				break OuterLoop;
			}
			if(i==16) {
				refreshPage(driver);
			}
		}
		}
		for(int i=1; i<=16; i++){
			Thread.sleep(2000);
			String Hold_Xpath = "//li["+i+"]/table/tbody/tr[2]/td[3]/button";
			if(elementVisible(driver, By.xpath(Hold_Xpath), 1)){
			String Hold_Button = getText(driver, By.xpath(Hold_Xpath));
			if(Hold_Button.contains("Hold")){
				safeClick(driver, By.xpath(Hold_Xpath));
				break ; 
			} 
			}
		} 
}

	public void corpAir_Payment_Hold_Release(RemoteWebDriver driver) throws Exception, InterruptedException {
		String TripID = null;
		textPresent(driver, "Duration", 60);
		elementVisible(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 30);
		//elementPresent(driver, By.xpath("//div[6]/div[4]/div[3]"));
		elementPresent(driver, By.id("holdBookingFlash"));
		if(MakePaymentOnlyInQA2){
			safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));
			textPresent(driver, "Your booking is on hold.", 60);
			elementPresent_Time(driver, getObject("AirCorpCom_ConfirmationPage_TripID"), 90);
			TripID = getText(driver, getObject("AirCorpCom_ConfirmationPage_TripID"));
			Reporter.log("Hold Trip ID : " + TripID );	
			Thread.sleep(20000);
		  	safeClick(driver, By.linkText("Trips"));
		  	elementPresent_Time(driver, By.xpath("//h1"), 120);
				
			elementPresent_Time(driver,getObject("AirCorp_TripsPage_SearchTrips"), 5);
			
			safeType(driver, getObject("AirCorp_TripsPage_SearchTrips"), TripID);
			safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
			Thread.sleep(3000);
			
			if (elementPresent_Time(driver, By.id("listView_a"), 1)){
			safeClick(driver, By.id("listView_a"));
			
			WebDriverWait wait = new WebDriverWait(driver, 5);
		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p/a")));
			
			safeClick(driver, By.xpath("//p/a"));
			}		
			else if(textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 5)){
				Reporter.log("Oops! Cleartrip’s system is behaving badly :  message is displayed");
				Assert.assertTrue(false);
			}
			elementPresent_Time(driver, By.id("release_booking"), 60);
			WebDriverWait waitt = new WebDriverWait(driver, 10);
		
		    waitt.until(ExpectedConditions.elementToBeClickable(By.id("release_booking")));
			
		    elementPresent(driver, By.id("release_booking"));
			safeClick(driver, By.id("release_booking"));
			elementPresent_Time(driver, By.xpath("//h1"), 60);
			/*
			elementPresent_Time(driver,getObject("AirCorp_TripsPage_SearchTrips"), 5);
			Thread.sleep(60000);
			
			safeType(driver, getObject("AirCorp_TripsPage_SearchTrips"), TripID);
			safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
			Thread.sleep(3000);
		
		    assertEquals("No bookings have been made for any user", driver.findElement(By.cssSelector("h2")).getText());
				*/
			Reporter.log("Hold booking released TripID : "+TripID);
			logger.info("Hold booking released TripID : "+TripID);
		
		}
	}
	
	public void corpAir_ConfirmSRPLoadComplete(RemoteWebDriver driver) throws InterruptedException, Exception {
		//refreshPage(driver);
		Thread.sleep(5000);
		//textPresent(driver, "Getting prices and availability", 20);
		int i = 0;
		if (!elementPresent(driver, getObject("AirCorpCom_SRP_Book_Button"), 30)) {
		for (i = 0; i <= 10; i++) {
			if (elementPresent(driver, getObject("AirCorpCom_SRP_Book_Button"), 1)) {
				Reporter.log("Book button found with attempt no " + i);
				break;
			} 
			else if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Sorry, our system is acting up : Message is displayed");
				Assert.assertTrue(false);
			}
			else {
				Reporter.log("Search not happening, didn't find book button! Attempting refresh.");
			}
			if(!(i==0)){
				refreshPage(driver);}
		}
		}
		/*if (!checkFlightsCount(driver)) {
			Reporter.log("No results found for this search.");
			assertTrue("Failure!", false);
		}*/
		
		smartClick(driver, By.xpath("(//a[contains(text(),'All flights')])[2]"));
	}

	public void corpAir_SRP_Domestic_Oneway(RemoteWebDriver driver, String flight_type) throws InterruptedException, Exception {
		corpAir_ConfirmSRPLoadComplete(driver);
		filterFlightsByLCCOrGDS1(driver, flight_type, 0);		
		safeClick(driver, getObject("AirCorpCom_SRP_Book_Button"));
	}
	public boolean filterFlightsOnSrpByListOfAirlinesRT(RemoteWebDriver driver, ArrayList<String> filterByAirlines) throws Exception {
		
		Reporter.log("Filtering SRP for airlines : " + filterByAirlines, true);
		List<WebElement> flights = null;

		if(filterByAirlines.isEmpty())
		{
			Reporter.log("Airlines to be filtered not specified! Hence not filtering", true);
			return false;
		}

		if (waitElement(driver,By.xpath("//li[@class='airlines']/label/span"), 1)) 
		{
			flights = driver.findElements(By.xpath("//li[@class='airlines']/label/span"));
		}
		else 
		{
			Reporter.log("Not able to locate list of available flight names.", true);
			//assertTrue("Failure! Not able to locate list of available flight names.", false);
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
						//li[@class='airlines'][1]/label/span/../input
					scrollToElement(driver, By.xpath("//li[@class='airlines']["+j+"]/label/span/../input"));
					safeClick(driver, By.xpath("//li[@class='airlines']["+j+"]/label/span/../input"));
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
	public void corpAir_SRP(RemoteWebDriver driver, String Booking_Type, String flight_type) throws InterruptedException, Exception {
		boolean flag=true;
		ArrayList<String> filterByAirlines= new ArrayList<String>();
		filterByAirlines.add("SpiceJet");
	
		//filterByAirlines.add("GoAir");
		refreshPage(driver);
		elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div/p"), 30);
		if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Sorry, our system is acting up : Message is displayed in SRP");
				Assert.assertTrue(false);
		}
		logURL(driver);
		if (!checkFlightsCount(driver)) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
		}		
		//------------------------------------ Domestic Oneway---------------------------------------------------//
		if(Booking_Type.equalsIgnoreCase("DOMOW")) {
			
			corpAir_ConfirmSRPLoadComplete(driver);
			//filterFlightsOnSrpByListOfAirlines(driver,filterByAirlines);
			flag=filterFlightsOnSrpByListOfAirlines(driver,filterByAirlines);
			System.out.println("flag="+flag);
			if(flag) {
				refreshPage(driver);
			}
			//filterFlightsByLCCOrGDS1(driver, flight_type, 0);
			safeClick(driver, getObject("AirCorpCom_SRP_Book_Button"));
			}
		//------------------------------------ Domestic Oneway CorpFare---------------------------------------------------//
		else if(Booking_Type.equalsIgnoreCase("DOMOWCORPFARE")) {
			corpAir_ConfirmSRPLoadComplete(driver);
			if(elementPresent(driver,By.xpath("//*[text()=' Book corporate fare at ']//..//../button"),4)) {
			scrollToElement(driver, By.xpath("//*[text()=' Book corporate fare at ']//..//../button"));
			safeClick(driver,By.xpath("//*[text()=' Book corporate fare at ']//..//../button"));
			}
			else {
				if(elementPresent(driver,By.id("BaggageBundlingTemplate"),2)) {
					safeClick(driver,By.xpath("//*[@id='BaggageBundlingTemplate']/./../../tr/td/button"));
				}
			}
			/*safeClick(driver,By.xpath("//*[@class='addOnFilter']/label/input"));
			safeClick(driver, getObject("AirCorpCom_SRP_Book_Button"));*/
		//	Checking_Checkbox(driver, By.id("1_1_corpFare"));
			/*for(int i=1; i<=20; i++) {
				String Details_Xpath = "//section[2]/div[4]/div/nav/ul/li["+i+"]";
				String CorpFare_Xpath = "//li["+i+"]/table/tbody/tr[2]/td[3]/label/input";
				String Book_Xpath = "//ul/li["+i+"]/table/tbody/tr[2]/td[3]/button";
				String FlightDetails = null;
										
				try {
					FlightDetails = driver.findElement(By.xpath(Details_Xpath)).getText();
					if(FlightDetails.contains("Book corporate fare at")) {
						safeClick(driver, By.xpath(CorpFare_Xpath));
						Thread.sleep(2000);
						safeClick(driver, By.xpath(Book_Xpath));
						break;
					}	
				} 
				catch (Exception e) 
				{
					//safeClick(driver,By.xpath("//*[@class='addOnFilter']/label/input"));
					//safeClick(driver, getObject("AirCorpCom_SRP_Book_Button"));
				}
						//getText(driver, By.xpath(Details_Xpath));
				
			
			}	*/		
		}
		
		//------------------------------------ Domestic RT CorpFare---------------------------------------------------//
		else if(Booking_Type.equalsIgnoreCase("DOMCORPFARE")) {
			corpAir_ConfirmSRPLoad(driver);
			elementVisible(driver, getObject("AirCorp_DOM_SRP_RT_CorpFare_Txt"), 10);
			safeClick(driver, getObject("AirCorp_DOM_SRP_RT_Leg0_CorpFare_RadioBtn"));
			safeClick(driver, getObject("AirCorp_DOM_SRP_RT_Leg1_CorpFare_RadioBtn"));

			elementVisible(driver, getObject("AirCorpCom_SRP_RoundTrip_BookButton"), 20);
			safeClick(driver, getObject("AirCorpCom_SRP_RoundTrip_BookButton"));
		}
			
			//------------------------------------ Domestic RoundTrip--------------------------------------------//
			else if(Booking_Type.equalsIgnoreCase("DOMRT")) {
				
				corpAir_ConfirmSRPLoad(driver);		
				//filterFlightsOnSrpByListOfAirlines(driver,filterByAirlines);
				if(elementPresent(driver,By.xpath("//*[text()='Undo last filter']"),3)) {
					safeClick(driver,By.xpath("//*[text()='Undo last filter']"));
				}
				List<WebElement> we=driver.findElements(By.xpath("//*[@name='airlines']"));
				for(int i=0;i<we.size();i++) {
				//	System.out.println("airline=="+we.get(i).findElement(By.xpath("./../span")).getText());
					if(!we.get(i).findElement(By.xpath("./../span")).getText().equals("SpiceJet")) {
						we.get(i).click();
					}
				}
				
				
				
				//filterFlightsByLCCOrGDS1(driver, flight_type, 0);				
				/*if(textPresent(driver, "No flight combination found", 2)) {
					logURL(driver);
					smartClick(driver, By.xpath("//div[1]/nav/ul/li/div/p[2]//*[contains(text(),'Reset')]"));
					smartClick(driver, By.xpath("//div[2]/nav/ul/li/div/p[2]//*[contains(text(),'Reset')]"));
					refreshPage(driver);
					corpAir_ConfirmSRPLoad(driver);
				}*/
				elementVisible(driver, getObject("AirCorpCom_SRP_RoundTrip_BookButton"), 20);
				safeClick(driver, getObject("AirCorpCom_SRP_RoundTrip_BookButton"));
			}
		
		//------------------------------------ Domestic Special RoundTrip--------------------------------------------//
			else if(Booking_Type.equalsIgnoreCase("DOMSPLRT")) {
				corpAir_ConfirmSRPLoad(driver);
			//	filterFlightsByLCCOrGDS1(driver, "", 0);
				elementPresent_Time(driver, getObject("Air_Agency_SRP_RT_BookButton"), 60);
				corpNoResultsFound(driver);
				elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 50);
				elementPresent_Time(driver, getObject("Air_Agency_SRP_RT_BookButton"), 60);
				//corpAirUncheck_Jetconnect_Air(driver);
				/*if(!elementPresent_Time(driver, By.xpath("//td[2]/a/small"), 5)){
				for(int i=2; i<=5; i++){
					String Spl_RT_XPath = "//div[3]/section[2]/section/div/form/section[2]/div[2]/table/td["+i+"]" ;
					if(elementPresent_Time(driver, By.xpath(Spl_RT_XPath), 2)){
					String Attribute = driver.findElement(By.xpath(Spl_RT_XPath)).getAttribute("title").toString();
					if(Attribute.contains("SpiceJet")){
						safeClick(driver, By.xpath(Spl_RT_XPath));
						break ;
					} 
					else safeClick(driver, By.xpath("//div[3]/section[2]/section/div/form/section[2]/div[2]/table/td[1]"));
					}
				}
				} else safeClick(driver, By.xpath("//td[2]/a/small"));*/
	
				
				elementPresent_Time(driver, By.xpath("//table/td[2]/a"), 5);
				 safeClick(driver, By.xpath("//table/td[2]/a"));
				 Thread.sleep(2000);
				safeClick(driver, getObject("Air_Agency_SRP_RT_BookButton"));
			}
		
		//------------------------------------ Domestic MultiCity--------------------------------------------//
			else if(Booking_Type.equalsIgnoreCase("DOMMC")) {
				
				corpAir_ConfirmSRPLoad(driver);
				//Thread.sleep(10000);
				elementPresent(driver, getObject("AirCorp_SRP_Dom_MC_book_button"), 60);
				//refreshPage(driver);
				
				/*flag=filterFlightsOnSrpByListOfAirlines(driver,filterByAirlines);
				System.out.println("flag="+flag);
				if(flag) {
					refreshPage(driver);
				}*/
				/*sa
				corpNoResultsFound(driver);*/
			  elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/aside/div/p/strong"), 20);
			  elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 100);
			  elementPresent_Time(driver, getObject("AirCorp_SRP_Dom_MC_book_button"), 20);
			  UnSelectCarrierMC(driver, "9W", "1");
			  UnSelectCarrierMC(driver, "S2", "1");
				safeClick(driver, getObject("AirCorp_SRP_Dom_MC_book_button"));				
			}
		
		//------------------------------------ International Oneway--------------------------------------------//
			else if(Booking_Type.equalsIgnoreCase("INTLOW")) {
				
				corpAir_ConfirmSRPLoad(driver);	
				corpAir_ConfirmSRPLoadCompleteIntlOneway(driver);
				flag=filterFlightsOnSrpByListOfAirlines(driver,filterByAirlines);
				System.out.println("flag="+flag);
				if(flag) {
					refreshPage(driver);
				}
				elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 20);
				String Hold_Book_Button = getText(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
				if(Hold_Book_Button.contains("Hold")) {
					safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button[2]"));
				}else	safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
			}

		//------------------------------------ International RoundTrip--------------------------------------------//
			else if(Booking_Type.equalsIgnoreCase("INTLRT")) {
				
				corpNoResultsFound(driver);
				//filterFlightsOnSrpByListOfAirlines(driver,filterByAirlines);
				elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 60);		
				filterFlightsByPreferedAirline1(driver,"SpiceJet", 0);
				String Hold_Book_Button = getText(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
				if(Hold_Book_Button.contains("Hold")) {
					safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button[2]"));
				}else	safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
			}
		
		//------------------------------------ International MultiCity--------------------------------------------//
			else if(Booking_Type.equalsIgnoreCase("INTLMC")) {
				
				corpAir_ConfirmSRPLoad(driver);
				//filterFlightsOnSrpByListOfAirlines(driver,filterByAirlines);
				elementPresent_Time(driver, getObject("AirCorp_SRP_Intl_MC_book_button"), 50);	
				corpNoResultsFound(driver);
				  elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div/p"), 30);
				  elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 60);
				  if(!elementPresent_Time(driver, getObject("AirCorp_SRP_Intl_MC_book_button"), 20)){
						driver.navigate().refresh();
						elementVisible(driver, getObject("SRP_air_flightcount"), 60);
				  }
				  //corpAirUncheck_Jetconnect_Air(driver);
				  UnSelectCarrierMC(driver, "9W", "1");
					UnSelectCarrierMC(driver, "S2", "1");
					
				safeClick(driver, getObject("AirCorp_SRP_Intl_MC_book_button"));
			}
		//------------------------------------ International Hold--------------------------------------------//
			else if(Booking_Type.equalsIgnoreCase("INTLHOLD")) {
				corpAir_ConfirmSRPLoad(driver);
				filterFlightsByLCCOrGDS1(driver, flight_type, 0);								
				elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 10);
				corpNoResultsFound(driver);
				elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 20);
				elementPresent_Time(driver, getObject("AirCorp_SRP_Oneway_BookButton"), 10);
				OuterLoop :for(int j=0; j<=2;j++) {
							for (int i=3; i<=7;i++) {
									if(elementVisible(driver, By.xpath("(//button[@type='submit'])["+i+"][contains(text(),'Hold')]"), 1)){
										break OuterLoop;
									}
									if(i==7) {
										refreshPage(driver);
									}
							}
						
				}
				int j = 0;	
				for(int i=1; i<=10; i++){
									Thread.sleep(1000);
									String Hold_Xpath = "//li["+i+"]/table/tbody/tr[2]/td[3]/button";
										if(elementVisible(driver, By.xpath(Hold_Xpath), 1)){
										String Hold_Button = getText(driver, By.xpath(Hold_Xpath));
						
											if(Hold_Button.contains("Hold")){
												j++;
												if(j>1){
												safeClick(driver, By.xpath(Hold_Xpath));
												break ;
												}
												 
											}else if(i<=5) {
												Reporter.log("Hold button is not displayed");
												Assert.assertTrue(false);
											}
										} 
								}
			}
	}

	public void corpAir_SRPAddmeal(RemoteWebDriver driver, String Booking_Type, String flight_type) throws InterruptedException, Exception {
		scrollToElement(driver, By.xpath("//*[text()=' Book corporate fare at ']//..//../button"));
		safeClick(driver,By.xpath("//*[text()=' Book corporate fare at ']//..//../button"));
		
		
	}
	
	public void SelectNoBookVoilationFlight(RemoteWebDriver driver) throws Exception, InterruptedException {
		for (int i = 1; i <= 50; i++) {
			//
			if (i % 6 == 0) {
				continue;
			}
			if (elementNotVisible(driver, By.xpath("//ul/li[" + i + "]/table/tbody/tr[1]/th[6]/div/span"), 1)) {
				driver.findElementByXPath("//ul/li[" + i + "]/table/tbody/tr[2]/td[3]/button").click();
				return;
			}
			
		}
		Reporter.log("No flights without booking voilation are found");
		AssertJUnit.assertTrue("No flights without booking voilation are found", false);
	}
	
	public void corpAir_ConfirmSRPLoad(RemoteWebDriver driver) throws InterruptedException, Exception {
		for(int j = 0; j <= 5; j++) {
		if(elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div/div/div/div/div"), 1)) {
			break;
		}
		else if(elementVisible(driver, By.xpath("//div/div/p/strong"), 1)) {
			break;
		}			
		else if(textPresent(driver, "Sorry, our system is acting up", 1)) {
			refreshPage(driver);
			Thread.sleep(15000);
		}	
		}
	smartClick(driver, By.xpath("(//a[contains(text(),'All flights')])[2]"));
	}
	
	public void corpAir_ConfirmSRPLoad(RemoteWebDriver driver,  String Adults, String Childrens, String Infants, String SearchType) throws InterruptedException, Exception {
		String Sector = getRandomNos(8);
		int intSector = Integer.parseInt(Sector);
		String FromCity = DomFromSectors[intSector];
		String ToCity = DomToSectors[intSector];
		
		for(int j = 0; j <= 5; j++) {
			if(elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div/div/div/div/div"), 1)) {
				break;
			}
			else if(elementVisible(driver, By.xpath("//div/div/p/strong"), 1)) {
				break;
			}			
		
			}
		if(elementVisible(driver, By.cssSelector("div.warningMessage > h2"), 2)){
			String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, SearchType, "" , 41, 42);
			driver.get(SRP_URL);
			
			}
		if(textPresent(driver, "Sorry, our system is acting up", 1)) {
			Assert.assertTrue(false);
		}	
		
		smartClick(driver, By.xpath("(//a[contains(text(),'All flights')])[2]"));
		}
	
	public void corpAir_ConfirmSRPLoadCompleteRoundtrip(RemoteWebDriver driver) throws InterruptedException, Exception {
		Thread.sleep(15000);
		
		for (int i = 0; i <= 5; i++) {
			corpAir_ConfirmSRPLoad(driver);
			// Thread.sleep(10000);
			if (waitElement(driver, getObject("AirCorpCom_SRP_RoundTrip_BookButton"), 60)) {
				Reporter.log("Book button found with attempt no " + i);
				break;
			} else {
				refreshPage(driver);
				Reporter.log("Search not happening, didn't find book button! Attempting refresh.");
			}
			
		}
		if (!checkFlightsCount(driver)) {
			Reporter.log("No results found for this search.");
			AssertJUnit.assertTrue("Failure!", false);
		}
	}

	public void corpAir_SRP_Roundtrip1(RemoteWebDriver driver, String flight_type) throws InterruptedException, Exception {
		corpAir_ConfirmSRPLoad(driver);
		filterFlightsByLCCOrGDS1(driver, flight_type, 0);
		if(textPresent(driver, "No flight combination found", 2)) {
			logURL(driver);
			smartClick(driver, By.xpath("//div[1]/nav/ul/li/div/p[2]//*[contains(text(),'Reset')]"));
			smartClick(driver, By.xpath("//div[2]/nav/ul/li/div/p[2]//*[contains(text(),'Reset')]"));
			refreshPage(driver);
			corpAir_ConfirmSRPLoad(driver);
		}
		elementVisible(driver, getObject("AirCorpCom_SRP_RoundTrip_BookButton"), 20);
		safeClick(driver, getObject("AirCorpCom_SRP_RoundTrip_BookButton"));
	}

	public void corpAir_SRP_Intl_RT(RemoteWebDriver driver) throws Exception {
		//elementVisible(driver, getObject("Air_Agency_SRP_Int_RT_BookButton"), 60);
		corpNoResultsFound(driver);
		elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 100);
		//elementPresent_Time(driver, getObject("Air_Agency_SRP_Int_RT_BookButton"), 60);
		//safeClick(driver, getObject("Air_Agency_SRP_Int_RT_BookButton"));
		
		String Hold_Book_Button = getText(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
		if(Hold_Book_Button.contains("Hold")) {
			safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button[2]"));
		}else	safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
		
		
		//safeClick(driver, By.xpath("//button[2]"));
		//safeClick(driver, By.xpath("//td[3]/button"));
	}
	
	public void corpAir_SRP_Domestic_SPL_RT(RemoteWebDriver driver) throws Exception {
		elementPresent_Time(driver, getObject("Air_Agency_SRP_RT_BookButton"), 60);
		corpNoResultsFound(driver);
		elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 100);
		elementPresent_Time(driver, getObject("Air_Agency_SRP_RT_BookButton"), 60);
		corpAirUncheck_Jetconnect_Air(driver);
	if(!elementPresent_Time(driver, By.xpath("//td[2]/a/small"), 5)){
		for(int i=2; i<=5; i++){
			String Spl_RT_XPath = "//div[3]/section[2]/section/div/form/section[2]/div[2]/table/td["+i+"]" ;
			if(elementPresent_Time(driver, By.xpath(Spl_RT_XPath), 2)){
			String Attribute = driver.findElement(By.xpath(Spl_RT_XPath)).getAttribute("title").toString();
			if(Attribute.contains("SpiceJet")){
				safeClick(driver, By.xpath(Spl_RT_XPath));
				break ;
			} 
			else safeClick(driver, By.xpath("//div[3]/section[2]/section/div/form/section[2]/div[2]/table/td[1]"));
			}
		}
		} else safeClick(driver, By.xpath("//td[2]/a/small"));
		safeClick(driver, getObject("Air_Agency_SRP_RT_BookButton"));
	}
	
	public void corpAir_ConfirmSRPLoadCompleteIntlOneway(RemoteWebDriver driver) throws InterruptedException, Exception {
		corpAir_ConfirmSRPLoad(driver);		
		for (int i = 0; i <= 10; i++) {
			// Thread.sleep(10000);
			if (waitElement(driver, getObject("AirCorpCom_SRP_Intl_Book_Button"), 1)) {
				Reporter.log("Book button found with attempt no " + i);
				break;
			} else if(i==10){
				Reporter.log("Search not happening, didn't find book button! Attempting refresh.");
			}
			refreshPage(driver);
		}
		if (!checkFlightsCount(driver)) {
			Reporter.log("No results found for this search.");
			AssertJUnit.assertTrue("Failure!", false);
		}

		smartClick(driver, By.xpath("(//a[contains(text(),'All flights')])[2]"));
	}

	public void corpAir_SRP_Intl_Oneway(RemoteWebDriver driver) throws InterruptedException, Exception {
		boolean flag=false;
		 ArrayList<String> filterByAirlines=new  ArrayList<String>();
		 filterByAirlines.add("SpiceJet");
		elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 20);
		/*if(elementVisible(driver, By.xpath("(//button[@type='submit'])[4]"), 2)) {
			safeClick(driver, By.xpath("(//button[@type='submit'])[4]"));
		}else safeClick(driver, By.xpath("(//button[@type='submit'])[3]"));
	*/
		/*String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";


((JavascriptExecutor)driver).executeScript(javaScript, webElement);*/
		flag=filterFlightsOnSrpByListOfAirlines(driver,filterByAirlines);
		System.out.println("flag="+flag);
		if(flag) {
			refreshPage(driver);
		}
		String Hold_Book_Button = getText(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
		if(Hold_Book_Button.contains("Hold")) {
			safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button[2]"));
		}else	safeClick(driver, By.xpath("//li[1]/table/tbody/tr[2]/td[3]/button"));
	}

	public boolean checkIfFailAfterBookButton_corp(RemoteWebDriver driver) throws Exception {
		
		boolean result = false;
		if (elementVisible(driver, getObject("air_review_itinerary_continue_corp"), 15)) {
			result = false;
		} 
		
		return result;
	}
	
	
	
	public void corpAirAE_SRP_Intl(RemoteWebDriver driver) throws InterruptedException, Exception {
		if (!elementVisible(driver, getObject("AirCorpAE_SRP_Intl_Price_Count"), 60)) {
			for (int i = 0; i <= 10; i++) {
				refreshPage(driver);
				if (elementVisible(driver, getObject("AirCorpAE_SRP_Intl_Price_Count"), 60)) {
					break;
				}
			}
		}
		safeClick(driver, getObject("AirCorpAE_SRP_Intl_Book_Button"));
	}

	public void corpAir_SRP_Intl_Hold_Oneway(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementVisible(driver, getObject("AirCorpCom_SRP_Price_Drag"), 100);
		if (!elementVisible(driver, getObject("AirCorpCom_SRP_Intl_Hold_Book_Button"), 60)) {
			for (int i = 0; i <= 10; i++) {
				refreshPage(driver);
				if (elementVisible(driver, getObject("AirCorpCom_SRP_Intl_Hold_Book_Button"), 60)) {
					break;
				}
			}
		}
		Thread.sleep(2000);
		for (int i = 1; i < 30; i++) {
			if (elementVisible(driver, By.xpath("//li[" + i + "]/table/tbody[2]/tr[2]/td[3]/button"), 100)) {
				String HoldButton = getText(driver, By.xpath("//li[" + i + "]/table/tbody[2]/tr[2]/td[3]/button"));
				if (HoldButton.contains("Hold")) {
					safeClick(driver, By.xpath("//li[" + i + "]/table/tbody[2]/tr[2]/td[3]/button"));
					break;
				}
			}
		}

	}

	public void corpAir_SRP_Intl_Roundtrip(RemoteWebDriver driver) throws InterruptedException, Exception {
		Thread.sleep(10000);
		if (!elementVisible(driver, getObject("AirCorpCom_SRP_Intl_Roundtrip_Book_Button"), 100)) {
			for (int i = 0; i <= 5; i++) {
				refreshPage(driver);
				if (elementVisible(driver, getObject("AirCorpCom_SRP_Intl_Roundtrip_Book_Button"), 70)) {
					break;
				}
			}
		}
		safeClick(driver, getObject("AirCorpCom_SRP_Intl_Roundtrip_Book_Button"));
	}

	public void corpAir_ItineraryPage(RemoteWebDriver driver) throws InterruptedException, Exception {
		if(!elementPresent(driver,By.xpath("//*[text()='Book your flight in 3 easy steps']"),20)) {
			driver.navigate().refresh();
					}
		if(textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 2)){
			Reporter.log("Oops! Cleartrip’s system is behaving badly : Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
			Assert.assertTrue(false);
		}
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 60)){
			Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
		}
		if(elementPresent_Time(driver, getObject("air_step1_insurance_chkbox"), 1)){
			scrollToElement(driver, getObject("air_step1_insurance_chkbox"));
		safeUncheck(driver, getObject("air_step1_insurance_chkbox"));
		}
		
		if (elementPresent_Time(driver, By.id("booking_violation_reasons"), 1)){
			safeSelect(driver, By.id("booking_violation_reasons"), "Other");
		}
		String Iti=driver.getPageSource();

		Reporter.log("Itinerary ID="+Iti.split("var itineraryId =")[1].split(";")[0],true);
		
		Thread.sleep(5000);
		
		safeClick(driver, getObject("Air_Agency_Itinerary_ContinueButton"));
	}
	
	public void corpAir_AddMeal_ItineraryPage(RemoteWebDriver driver) throws InterruptedException, Exception {
		
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 60)){
			Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
		}
		if(elementPresent_Time(driver, getObject("air_step1_insurance_chkbox"), 1)){
		safeUncheck(driver, getObject("air_step1_insurance_chkbox"));
		}
		if(elementPresent(driver,By.linkText("+ Add in-flight meals"),3)) {
			safeClick(driver,By.linkText("+ Add in-flight meals"));
			driver.switchTo().frame("modal_window");
			
		}
		//elementPresent_Time(driver, getObject("AirCorpCom_ReviewPage_Roundtrip_AddMeal"), 5);
		//safeClick(driver, getObject("AirCorpCom_ReviewPage_Roundtrip_AddMeal"));
		//Thread.sleep(5000);
		//driver.switchTo().frame("modal_window");
		//Onward Meal
/*		if(!elementVisible(driver, By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"), 5)){
			Reporter.log("Add meal option is not displayed");
			Assert.assertTrue(false);
		}
		mouseHover(driver, By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"));
		Actions Onward = new Actions(driver);
		WebElement we1 = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"));
		Onward.moveToElement(we1).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"))).click().build().perform();

		Thread.sleep(1000);
		//Return Meal
		WebElement we2 = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[3]/ul/li[2]/a"));
		Onward.moveToElement(we2).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[3]/ul/li[2]/a"))).click().build().perform();

		Thread.sleep(1000);
		AssertJUnit.assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
		driver.findElementByXPath("//input[@value='Done']").click();
		Thread.sleep(3000);
		Reporter.log("Meals Added");
		Thread.sleep(2000);
		
		driver.switchTo().parentFrame();
	
		if (elementPresent_Time(driver, By.id("booking_violation_reasons"), 2)){
			safeSelect(driver, By.id("booking_violation_reasons"), "Other");
		}*/
		//if (elementPresent_Time(driver, By.id("booking_violation_reasons"), 2)){
			safeSelect(driver, By.id("booking_violation_reasons"), "Other");
		//}
		safeClick(driver, getObject("Air_Agency_Itinerary_ContinueButton"));
	}
	
public void corpAir_AddMeal_OW_ItineraryPage(RemoteWebDriver driver) throws InterruptedException, Exception {
		
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 60)){
			Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
		}
		if(elementPresent_Time(driver, getObject("air_step1_insurance_chkbox"), 1)){
		safeUncheck(driver, getObject("air_step1_insurance_chkbox"));
		}
		
		elementPresent_Time(driver, getObject("AirCorpCom_ReviewPage_Roundtrip_AddMeal"), 5);
		safeClick(driver, getObject("AirCorpCom_ReviewPage_Roundtrip_AddMeal"));
		Thread.sleep(5000);
		driver.switchTo().frame("modal_window");
		//Onward Meal
		mouseHover(driver, By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"));
		Actions Onward = new Actions(driver);
		WebElement we1 = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"));
		Onward.moveToElement(we1).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"))).click().build().perform();

		Thread.sleep(1000);
		/*//Return Meal
		WebElement we2 = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[3]/ul/li[2]/a"));
		Onward.moveToElement(we2).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[3]/ul/li[2]/a"))).click().build().perform();
*/
		Thread.sleep(1000);
		AssertJUnit.assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
		driver.findElementByXPath("//input[@value='Done']").click();
		Thread.sleep(3000);
		Reporter.log("Meals Added");
		Thread.sleep(2000);
		
		driver.switchTo().parentFrame();
	
		if (elementPresent_Time(driver, By.id("booking_violation_reasons"), 2)){
			safeSelect(driver, By.id("booking_violation_reasons"), "Other");
		}
		safeClick(driver, getObject("Air_Agency_Itinerary_ContinueButton"));
	}
	
	
	
public void corpAir_AddMeal_Indigo_Default_ItineraryPage(RemoteWebDriver driver) throws InterruptedException, Exception {
		
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 60)){
			Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
		}
		if(elementPresent_Time(driver, getObject("air_step1_insurance_chkbox"), 1)){
		safeUncheck(driver, getObject("air_step1_insurance_chkbox"));
		}
		
		elementPresent_Time(driver, getObject("AirCorpCom_ReviewPage_Roundtrip_AddMeal"), 5);
		safeClick(driver, getObject("AirCorpCom_ReviewPage_Roundtrip_AddMeal"));
		Thread.sleep(5000);
		

		//String mealSelected = driver.findElement(By.id("")).getAttribute("value");
		driver.switchTo().frame("modal_window");
		//Onward Meal
		mouseHover(driver, By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"));
		Actions Onward = new Actions(driver);
		WebElement we1 = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"));
		Onward.moveToElement(we1).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[2]/ul/li[2]/a"))).click().build().perform();

		Thread.sleep(1000);
		//Return Meal
		WebElement we2 = driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[3]/ul/li[2]/a"));
		Onward.moveToElement(we2).moveToElement(driver.findElement(By.xpath("//section[@id='addonListContainer']/ul/li[3]/ul/li[2]/a"))).click().build().perform();

		Thread.sleep(1000);
		AssertJUnit.assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());
		driver.findElementByXPath("//input[@value='Done']").click();
		Thread.sleep(3000);
		Reporter.log("Meals Added");
		Thread.sleep(2000);
		
		driver.switchTo().parentFrame();
	
		if (elementPresent_Time(driver, By.id("booking_violation_reasons"), 2)){
			safeSelect(driver, By.id("booking_violation_reasons"), "Other");
		}
		//safeClick(driver, getObject("Air_Agency_Itinerary_ContinueButton"));
	}
	
	public void agencyAir_ItineraryPage(RemoteWebDriver driver) throws Exception {
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 120)){
			Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
		}
		if(elementPresent_Time(driver, getObject("air_step1_insurance_chkbox"), 2)){
		safeUncheck(driver, getObject("air_step1_insurance_chkbox"));
		}
		Thread.sleep(2000);
		safeClick(driver, getObject("Air_Agency_Itinerary_ContinueButton"));
	}

	
	public String corpAir_ItineraryPageWithInsurance(RemoteWebDriver driver) throws InterruptedException, Exception {
		if (elementVisible(driver, getObject("AirCorpCom_ReviewPage_Book_Button"), 12)) {
			Reporter.log("Insurance Selected For This Booking");
		} 
			AssertJUnit.assertTrue(elementPresent(driver, getObject("AirCom_BookStep_InsuranceTxt")));
			String insuranceAmt = getText(driver, By.xpath("//dl[@id='insuranceSummary']/dd"));
			if (elementPresent_Time(driver, By.id("booking_violation_reasons"), 2)){
				safeSelect(driver, By.id("booking_violation_reasons"), "Other");
			}

			safeClick(driver, getObject("AirCorpCom_ReviewPage_Book_Button"));
			Reporter.log("Continue button on itinerary page clicked.");
		
		return insuranceAmt;
	}
	
	public void corpAirAE_ItineraryPage(RemoteWebDriver driver) throws InterruptedException, Exception {

		safeClick(driver, getObject("AirCorpCom_ReviewPage_Book_Button"));
	}

	/*
	 * public boolean checkIfFailAfterBookButton(RemoteWebDriver driver) throws Exception { int tryAgain = 0; boolean result =
	 * false; if (elementVisible(driver, getObject("air_review_itinerary_continue"), 8)) { result = false; } else { while
	 * (tryAgain < 8) { if (waitElement(driver, getObject("AirCom_SRP_Modify_Search_Button"), 1)) { result = true; break; } else
	 * if (elementVisible(driver, getObject("air_review_itinerary_continue"), 1)) { result = false; break; } else if
	 * (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) { printInfo("System Acting up error.");
	 * Reporter.log("System Acting up error."); break; } else if (waitElement(driver, By.xpath("//div/section/div/div/h1"), 1)) {
	 * if (driver.findElement(By.xpath("//div/section/div/div/h1")).getText()
	 * .equalsIgnoreCase("Whatever you're looking for, isn't here")) { safeClick(driver,
	 * By.xpath("//span[@id='flightStat']/button")); result = true; } }// TODO: check if right xpath tryAgain++; } } return
	 * result; }
	 */

	public void corpAir_ItineraryPage_Roundtrip(RemoteWebDriver driver) throws InterruptedException, Exception {
		elementVisible(driver, getObject("AirCorpCom_ReviewPage_Roundtrip_Book_Button"), 1200);
		UnChecking_Checkbox(driver, getObject("AirCorpCom_ReviewPage_Insurance_CheckBox"));
		safeClick(driver, getObject("AirCorpCom_ReviewPage_Roundtrip_Book_Button"));
	}

	public void corpAir_TravellerPage(RemoteWebDriver driver, String Adults, String Childrens, String Infants, String useGST, String useNonDefaultState)
			throws InterruptedException, Exception {
		
		
		
			if (elementVisible(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"), 10)) {
				
				
				
				
				// safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_Adult1"), getObject("AirCorpCom_TravellerPage_Ajax"),
				// dataFile.value("Corp_Adult1_Name"));
				corpAir_Dom_PaxEntry(driver, Adults, Childrens, Infants);
				if(driver.getCurrentUrl().contains("https://cltpdomainprod1.cleartripforbusiness.ae/flights/itinerary"))
					safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_ContactPerson_Name"), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("CorpProdAeContact"));
				else
					safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_ContactPerson_Name"), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("Corp_Adult1_Name"));
				waitElement(driver, getObject("AirCorpCom_TravellerPage_EmailID"), 5);
				Thread.sleep(2000);
				safeType(driver, getObject("AirCorpCom_TravellerPage_EmailID"), dataFile.value("Corp_QA2_Username"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Address"), dataFile.value("Corp_Address"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_City"), dataFile.value("Corp_City"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_State"), dataFile.value("Corp_State"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Pin"), dataFile.value("Corp_Pin"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Country"), dataFile.value("Corp_Country"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Phone"), dataFile.value("Corp_Traveller_Phone"));
				/*if (elementPresent_Time(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), 1)){
				safeType(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), dataFile.value("Corp_Spl_Request"));
				}*/
				
				
				gstdetails(driver,useGST,useNonDefaultState);
				
				safeClick(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"));
				Thread.sleep(2000);	
				Reporter.log("Continue button on traveller details page clicked.");
				
			} else {
			Reporter.log("Continue button on traveller details page not visible.");
			AssertJUnit.assertTrue("Failure!", false);
		}
		
	}

	public void gstdetails (RemoteWebDriver driver,String useGST,String useNonDefaultState )throws Exception {
		if(useGST.equalsIgnoreCase("true"))
		{
			 
			safeSelectByIndex(driver, By.id("gst_number"), 1);
			
		}
		
		/*if(useNonDefaultState.equalsIgnoreCase("true"))
			{
			safeSelect(driver,By.id("gstStateContainer"), "Chhattisgarh");
			}*/

	}

	public void corpAir_TravellerPage_FrequentFlier_MealPrefe(RemoteWebDriver driver, String Adults, String Childrens, String Infants)
			throws InterruptedException, Exception {
		
			if (elementVisible(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"), 10)) {
				// safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_Adult1"), getObject("AirCorpCom_TravellerPage_Ajax"),
				// dataFile.value("Corp_Adult1_Name"));
				corpAir_PaxEntry_FrequentFlier_MealPref(driver, Adults, Childrens, Infants);
				safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_ContactPerson_Name"), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("Corp_Adult1_Name"));
				waitElement(driver, getObject("AirCorpCom_TravellerPage_EmailID"), 5);
				Thread.sleep(2000);
				safeType(driver, getObject("AirCorpCom_TravellerPage_EmailID"), dataFile.value("Corp_QA2_Username"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Address"), dataFile.value("Corp_Address"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_City"), dataFile.value("Corp_City"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_State"), dataFile.value("Corp_State"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Pin"), dataFile.value("Corp_Pin"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Country"), dataFile.value("Corp_Country"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Phone"), dataFile.value("Corp_Traveller_Phone"));
				if (elementPresent_Time(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), 1)){
				safeType(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), dataFile.value("Corp_Spl_Request"));
				}
				
				safeClick(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"));
				Thread.sleep(2000);	
				Reporter.log("Continue button on traveller details page clicked.");
				
			} else {
			Reporter.log("Continue button on traveller details page not visible.");
			AssertJUnit.assertTrue("Failure!", false);
		}
		
	}
	
	public void corpAir_TravellerPage_AddMeal(RemoteWebDriver driver, String Adults, String Childrens, String Infants)
			throws InterruptedException, Exception {
		
			if (elementVisible(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"), 10)) {
				
				corpAir_Dom_PaxEntry(driver, Adults, Childrens, Infants);
				safeClick(driver, By.xpath("//fieldset/div/a"));
				if(!elementVisible(driver, By.id("adult1MealPref"), 5)){
					Reporter.log("Meal  preference drop down is not dispplayed");
					Assert.assertTrue(false);
				}
				safeSelect(driver, By.id("adult1MealPref"), "Veg");				
				safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_ContactPerson_Name"), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("Corp_Adult1_Name"));
				waitElement(driver, getObject("AirCorpCom_TravellerPage_EmailID"), 5);
				Thread.sleep(2000);
				safeType(driver, getObject("AirCorpCom_TravellerPage_EmailID"), dataFile.value("Corp_QA2_Username"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Address"), dataFile.value("Corp_Address"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_City"), dataFile.value("Corp_City"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_State"), dataFile.value("Corp_State"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Pin"), dataFile.value("Corp_Pin"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Country"), dataFile.value("Corp_Country"));
				safeType(driver, getObject("AirCorpCom_TravellerPage_Phone"), dataFile.value("Corp_Traveller_Phone"));
				/*if (elementPresent_Time(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), 1)){
				safeType(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), dataFile.value("Corp_Spl_Request"));
				}*/
				
				safeClick(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"));
				Thread.sleep(2000);	
				Reporter.log("Continue button on traveller details page clicked.");
				
			} else {
			Reporter.log("Continue button on traveller details page not visible.");
			AssertJUnit.assertTrue("Failure!", false);
		}
		
	}

	
	public void corpAir_Intl_TravellerPage(RemoteWebDriver driver, String Adults, String Childrens, String Infants)
			throws InterruptedException, Exception {
		if (elementVisible(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"), 20)) {
			// safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_Adult1"), getObject("AirCorpCom_TravellerPage_Ajax"),
			// dataFile.value("Corp_Adult1_Name"));
			corpAir_Intl_PaxEntry(driver, Adults, Childrens, Infants);
			
			if(driver.getCurrentUrl().contains("https://cltpdomainprod1.cleartripforbusiness.ae/flights/itinerary"))
				safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_ContactPerson_Name"), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("CorpProdAeContact"));
			else
				safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_ContactPerson_Name"), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("Corp_Adult1_Name"));
			
			elementVisible(driver, getObject("AirCorpCom_TravellerPage_EmailID"), 20);
			safeType(driver, getObject("AirCorpCom_TravellerPage_EmailID"), dataFile.value("Corp_QA2_Username"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_Address"), dataFile.value("Corp_Address"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_City"), dataFile.value("Corp_City"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_State"), dataFile.value("Corp_State"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_Pin"), dataFile.value("Corp_Pin"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_Country"), dataFile.value("Corp_Country"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_Phone"), dataFile.value("Corp_Traveller_Phone"));
			if (elementPresent_Time(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), 1)){
			safeType(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), dataFile.value("Corp_Spl_Request"));
			}
			Thread.sleep(2000);
			safeClick(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"));
			Reporter.log("Continue button on traveller details page clicked.");
		} else {
			Reporter.log("Continue button on traveller details page not visible.");
			AssertJUnit.assertTrue("Failure!", false);
		}
		Thread.sleep(2000);
	}

	public void corpAir_PTA_TravellerPage(RemoteWebDriver driver)
			throws InterruptedException, Exception {
		
		if (elementVisible(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"), 10)) {
			// safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_Adult1"), getObject("AirCorpCom_TravellerPage_Ajax"),
			// dataFile.value("Corp_Adult1_Name"));
			corpAir_PaxEntry_PTA(driver, "1", "0", "0");
			safeAutocomplete(driver, getObject("AirCorpCom_TravellerPage_ContactPerson_Name"), getObject("AirCorpCom_TravellerPage_Ajax"), "TestPTA Booking");
			waitElement(driver, getObject("AirCorpCom_TravellerPage_EmailID"), 5);
			Thread.sleep(2000);
			safeType(driver, getObject("AirCorpCom_TravellerPage_EmailID"), dataFile.value("Corp_QA2_Username"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_Address"), dataFile.value("Corp_Address"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_City"), dataFile.value("Corp_City"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_State"), dataFile.value("Corp_State"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_Pin"), dataFile.value("Corp_Pin"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_Country"), dataFile.value("Corp_Country"));
			safeType(driver, getObject("AirCorpCom_TravellerPage_Phone"), dataFile.value("Corp_Traveller_Phone"));
			if (elementPresent_Time(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), 1)){
			safeType(driver, getObject("AirCorpCom_TravellerPage_Spl_Request"), dataFile.value("Corp_Spl_Request"));
			}
			
			safeClick(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"));
			Thread.sleep(2000);	
			Reporter.log("Continue button on traveller details page clicked.");
			
		} else {
			Reporter.log("Continue button on traveller details page not visible.");
			AssertJUnit.assertTrue("Failure!", false);
		}
		
	}

	
	public String AirCorp_Paymentpage(RemoteWebDriver driver,  String PaymentType, String Coupon, String Booking_Comfirmation_Message) throws Exception {
		for(int i=0;i<=10;i++) {
			if(elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), 1)) {
				break;
			} else if(elementVisible(driver, By.xpath("(//a[contains(text(),'Modify search')])[2]"), 1)) {
				Reporter.log("Page has redirected back to SRP after clicking on continue in Traveller page");
				Assert.assertTrue(false);
			}
		}
		elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), 20);
		String TripID= null;
		if(Coupon.equalsIgnoreCase("COUPON"))
		{	
			elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), 60);
			if (common.value("host").contains("qa2") || common.value("host").equalsIgnoreCase("hf")){
				safeType(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2BAIR_Coupon"));	
			}else if (common.value("host").contains("beta")||common.value("host").contains("www")){
				//safeType(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), dataFile.value("AirCorp_OWDOM_ProdBetaCoupon"));
			}
			
			safeClick(driver, getObject("AirCorp_PaymentPage_Coupon_Button"));
			if(MakePaymentOnlyInQA2){
			elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_ELigibleText"), 30);
			textAssert(driver, getObject("AirCorp_PaymentPage_Coupon_ELigibleText"), "Great, you are eligible to a cashback");
			Reporter.log("Coupon Applied ");
			}
		}
		textPresent(driver, "How would you like to pay?", 20);

		TripID = corpPayment(driver, PaymentType, Booking_Comfirmation_Message);
		return TripID;
		}

	public void AirCorp_HQ_Cancellation(RemoteWebDriver driver, String TripID) throws Exception,InterruptedException {
		if (MakePaymentOnlyInQA2){
			hotelCom_Open_TripID_HQ(driver, TripID);
			safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
			safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
			safeClick(driver, By.id("signInButton"));
			Thread.sleep(5000);
			hotelCom_Open_TripID_HQ(driver, TripID);
			//driver.get("http://"+common.value("host")+common.value("url")+"com"+"/hq/trips/"+TripID);
			textPresent(driver, "Itinerary", 50);	
			for(int i =0; i<=2; i++) {
				if(!elementVisible(driver, By.cssSelector("a.float_right.control"), 10)) {
					refreshPage(driver);
				}
			}
			if(elementVisible(driver, By.xpath("//tr[3]/td[2]"), 10)){
				String text = getText(driver, By.xpath("//tr[3]/td[2]"));
				if(text.equals("Booking Failed")){
					Reporter.log(" booking has failed : "+text);
					Assert.assertTrue(false);
				}
			}
		elementVisible(driver, By.cssSelector("a.float_right.control"), 30);
		safeClick(driver, By.cssSelector("a.float_right.control"));
		elementVisible(driver, By.xpath("//th/input"), 60);
		safeClick(driver, By.xpath("//th/input"));
		if(textPresentInElement(driver, By.xpath("//tr[3]/td[2]"), "Booking Failed", 1)) {
			Reporter.log("Booking Failed Status message is displayed");
			Assert.assertTrue(false);
		}
		if(!elementVisible(driver, By.id("cancel"), 10)){
			refreshPage(driver);
			Thread.sleep(20000);
		}
		
		safeType(driver, By.id("add_note"), "test cancellation");
		safeClick(driver, By.id("cancel"));
		textPresent(driver, "Itinerary", 50);
		elementVisible(driver, By.xpath("//tr[3]/td[2]"), 30);
		String Trip_Status = getText(driver, By.xpath("//tr[3]/td[2]"));
		if(!Trip_Status.equals("Cancelled")) {
			Reporter.log("Trip is not cancelled, Status is displayed is : "+Trip_Status);
			Assert.assertTrue(false);
		}
		}
	}
	
	public String corpPayment(RemoteWebDriver driver, String PaymentType, String Booking_Comfirmation_Message) throws Exception, InterruptedException {
		String TripID =null;
		
			
		if(MakePaymentOnlyInQA2){		
		elementPresent_Time(driver, getObject("AirCorp_PaymentPage_CreditCard_Tab"), 100);
		textPresent(driver, "How would you like to pay?", 20);
		if(PaymentType.equalsIgnoreCase("DA")||PaymentType.isEmpty()){
			safeClick(driver, getObject("AirCorp_PaymentPage_DepositAccount_Tab"));
		}
		else if(PaymentType.equalsIgnoreCase("CC")){
			Reporter.log("Payment Type is : CC");
			safeClick(driver, getObject("AirCorp_PaymentPage_CreditCard_Tab"));
			safeType(driver, getObject("AirCorp_PaymentPage_CreditCard_Number"),  dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObject("AirCorp_PaymentPage_CreditCard_Exp_Month"), "5");
			safeSelect(driver, getObject("AirCorp_PaymentPage_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));			
			safeType(driver, getObject("AirCorp_PaymentPage_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("AirCorp_PaymentPage_CreditCard_Name"), dataFile.value("MasterCard_HolderName"));
			safeType(driver, getObject("AirCorp_PaymentPage_Bill_FirstName"), "test");
			safeType(driver, getObject("AirCorp_PaymentPage_Bill_LastName"), "test");
			safeType(driver, getObject("AirCorp_PaymentPage_Bill_Address"), "JP Nagar");
			safeType(driver, getObject("AirCorp_PaymentPage_Bill_City"), "Bangalore");
			safeType(driver, getObject("AirCorp_PaymentPage_Bill_State"), "Karnataka");
			safeType(driver, getObject("AirCorp_PaymentPage_Bill_Pin"), "560076");
			//safeType(driver, getObject("AirCorp_PaymentPage_Bill_Country"), "India");
			safeAutocomplete(driver, getObject("AirCorp_PaymentPage_Bill_Country"), By.xpath("//body/ul/li"),  "India");		
			Thread.sleep(2000);
			
		}
		
		else if (PaymentType.equals("DA")) {
			Reporter.log("Payment Type is : DA");
			safeClick(driver, getObject("AirCorpCom_PaymentPage_DepositAccount_Tab"));
			}
		else if(PaymentType.equalsIgnoreCase("DC")){
			Reporter.log("Payment Type is : DC");
			safeClick(driver, getObject("AirCorp_PaymentPage_DebitCard_Tab"));
			safeType(driver, getObject("AirCorp_PaymentPage_DebitCard_Number"),  dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObject("AirCorp_PaymentPage_DebitCard_Exp_Month"), "5");
			safeSelect(driver, getObject("AirCorp_PaymentPage_DebitCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObject("AirCorp_PaymentPage_DebitCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("AirCorp_PaymentPage_DebitCard_BillName"), dataFile.value("MasterCard_HolderName"));
			Thread.sleep(2000);				
		}
		else if(PaymentType.equalsIgnoreCase("NB")){
			Reporter.log("Payment Type is : NB");
			safeClick(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"));
			safeSelect(driver, getObject("AirCorp_PaymentPage_NetBank_Dropdown"), "State Bank of India");
			Thread.sleep(2000);
			safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));
			
			if(!elementPresent_Time(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"), 5)){
				refreshPage(driver);
				Reporter.log("NB site displayed after clicking Back button");
			}
			for(int i=0; i<=60;i++) {
				if(elementPresent_Time(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"), 1)) {
					break;
				}
			if(textPresent(driver, "Oops, your payment didn’t work", 5)) {
				Reporter.log("Oops, your payment didn’t work ");
				Assert.assertTrue(false);
				}
			}
			if(elementPresent_Time(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"), 1)){
			safeClick(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"));
			} else {
				Reporter.log("Page has not redirected to Netbanking Site");
				AssertJUnit.assertEquals(true, false);
			}

			if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Sorry, our system is acting up. message is displayed");
				Assert.assertTrue(false);
			}
			if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
				Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
				AssertJUnit.assertEquals(true, false);
			}
			else Reporter.log("Page has redirected to back to Cleartrip from Netbanking Site");
			
			
		}
		else if(PaymentType.equalsIgnoreCase("NBBOI")){
		
			waitElement(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"), 10);
			safeClick(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"));

			safeSelect(driver, getObject("AirCorp_PaymentPage_NetBank_Dropdown"), "Bank of India");
			safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));
		
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			if(elementVisible(driver, getObject("HotelCom_BookStep4_BOI_Bank_CancelLink"), 50)){
				Thread.sleep(2000);			
				Reporter.log("NB site page is displayed");
				elementPresent(driver, getObject("HotelCom_BookStep4_BOI_Bank_CancelLink"));
				safeClick(driver, getObject("HotelCom_BookStep4_BOI_Bank_CancelLink"));
				}
			else {
					Reporter.log("Netbanking Site is not displayed");
					Assert.assertTrue(false);
					
					}
			if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Sorry, our system is acting up. message is displayed");
				Assert.assertTrue(false);
			}
			else if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
				Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
				AssertJUnit.assertEquals(true, false);
			}
			Reporter.log("Page has redirected to back to Cleartrip from Netbanking Site");
		}
		else if(PaymentType.equalsIgnoreCase("HOLD")){
			elementVisible(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 60);
			elementPresent(driver, By.xpath("//div[6]/div[4]/div[3]"));			
		}
		
		else if(PaymentType.equalsIgnoreCase("NBFailure")) {

			safeClick(driver, getObject("AirCorpCom_PaymentPage_NetBanking_Tab"));
			Thread.sleep(100);
			if (waitElement(driver, getObject("AirCorpCom_PaymentPage_NetBanking_Banks"), 5)) {
				safeSelect(driver, getObject("AirCorpCom_PaymentPage_NetBanking_Banks"), "Bank of India");
				safeClick(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"));
				Reporter.log("Make Payment button on payment page clicked.");
			//}
			Thread.sleep(5000);
			if (waitElement(driver, By.linkText("Return to Biller Site"), 60)) {
				safeClick(driver, By.linkText("Return to Biller Site"));
				Reporter.log("Return to Biller Site is in-progress");
				Thread.sleep(5000);
			} else {
				Reporter.log("Return to Biller Site link not loading.");
				AssertJUnit.assertTrue("Failure!", false);
				}
				
			
			}
		}
	if(MakePaymentOnlyInQA2){
		if(!(PaymentType.equalsIgnoreCase("NB") ||PaymentType.equalsIgnoreCase("NBFailure") ||PaymentType.equalsIgnoreCase("NBBOI")) ) {
			safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));	 
			if(textPresent(driver, "We do not have the billing address country you entered on record", 1)) {
				safeAutocomplete(driver, getObject("AirCorp_PaymentPage_Bill_Country"), By.xpath("//body/ul/li"),  "India");
				Thread.sleep(5000);
				safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));	
			}
			
			Reporter.log("Payment Button is Clicked");
			
			if(elementPresent(driver, getObject("air_amex_payment_selectAuth"), time_verylong)) {
			safeClick(driver, getObject("air_amex_payment_button"));
			}
			
			corpAir_Verify_Confirmation_OR_Failure(driver, "Great, your booking went through successfully", "Oops, your payment didn’t work", "Whatever you're looking for, isn't here", By.xpath("(//a[contains(text(),'Modify search')])[2]"), "Oops, your booking didn’t go through");
			if(!textPresent(driver, "Great, your booking went through successfully", 10)) {
				Reporter.log("Great, your booking went through successfully : text is not displayed");
				Reporter.log("//===============================Booking Failed=================================//");
			}
			logURL(driver);
			if(!elementVisible(driver, getObject("AirCorpCom_Confirmation_Message"), 10)) {
				//elementPresent(driver, getObject("AirCorpCom_Confirmation_Message"));
				Reporter.log("Corp Booking confirmation message is not displayed");
			}
			String confirmationMessage = driver.findElement(getObject("AirCorpCom_Confirmation_Message")).getText();
			if (confirmationMessage.equals("Great, your booking went through successfully.")){
				TripID = getText(driver, getObject("AirCorpCom_ConfirmationPage_TripID"));
				Reporter.log("Trip Id booked is " + TripID,true);
				logger.info(Booking_Comfirmation_Message+TripID);
			}else if (textPresent(driver, "Proxy Error", 1)) {
				Reporter.log("Proxy Error displayed, hence no TripID collected.");				
			}	
		}
		}
	}
		return TripID;
	}
	
	public void UnSelectCarrierMC(RemoteWebDriver driver, String Carrier, String leg) throws Exception {
		String Locators = "id=1_1_" + Carrier + "_" + leg;
		if (elementVisible(driver, By.xpath(Locators), 2)) {
			driver.findElementByXPath(Locators).click();

			Thread.sleep(500);
		} else {
			Reporter.log(Carrier + " not displayed in the SRP");
		}

	}
	
	public void corpAir_Paymentpage(RemoteWebDriver driver, String paymentMethod) throws InterruptedException, Exception {
		Thread.sleep(5000);
		if(!elementVisible(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"), 50)) {
			if(elementNotVisible(driver, By.cssSelector("img.progress_img"), 20)) {
				
			}
			else if(!elementVisible(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"), 20)) {
				smartClick(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"));
				Reporter.log("Traveller page continue button is clicked for second time");
			}
			
		}
		textPresent(driver, "Use a coupon code", 20);
		if (waitElement(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"), 50)) {
			if (paymentMethod.equals("DA")) {
				safeClick(driver, getObject("AirCorpCom_PaymentPage_DepositAccount_Tab"));
				if (elementVisible(driver, By.id("flashNotice"), 1)) {
					Reporter.log("Deposit Acc flash notice msg is- "
							+ driver.findElement(By.xpath("//*[@id='flashNotice']/span")).getText() + "\n will try with CC.");
					corpAir_Paymentpage(driver, "CC");
					return;
				}
				if (MakePaymentOnlyInQA2) {
					safeClick(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"));
					Reporter.log("Make Payment button on payment page clicked.");
				}
			} else if (paymentMethod.equals("NBFailure")) {
				safeClick(driver, getObject("AirCorpCom_PaymentPage_NetBanking_Tab"));
				Thread.sleep(100);
				if (waitElement(driver, getObject("AirCorpCom_PaymentPage_NetBanking_Banks"), 5)) {
					safeSelect(driver, getObject("AirCorpCom_PaymentPage_NetBanking_Banks"), "Bank of India");
					//if (MakePaymentTrue && !ProductionUrl) {
						safeClick(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"));
						Reporter.log("Make Payment button on payment page clicked.");
					//}
					Thread.sleep(5000);
					if (waitElement(driver, By.linkText("Return to Biller Site"), 60)) {
						safeClick(driver, By.linkText("Return to Biller Site"));
						Reporter.log("Return to Biller Site is in-progress");
					} else {
						Reporter.log("Return to Biller Site link not loading.");
						AssertJUnit.assertTrue("Failure!", false);
					}
				} else {
					Reporter.log("Make Payment button on payment page clicked.");
					AssertJUnit.assertTrue("Failure!", false);
				}
			} else if (paymentMethod.equals("CC")) {
				safeClick(driver, getObject("AirCorpCom_PaymentPage_CreditCard_Tab"));
				Thread.sleep(100);
				if (MakePaymentOnlyInQA2) {
					if (waitElement(driver, getObject("AirCorpCom_PaymentPage_CreditCard_Number"), 5)) {
						safeType(driver, getObject("AirCorpCom_PaymentPage_CreditCard_Number"), dataFile.value("CCNumber"));
						safeSelect(driver, getObject("AirCorpCom_PaymentPage_CreditCard_Exp_Month"), "5");
						safeSelect(driver, getObject("AirCorpCom_PaymentPage_CreditCard_Exp_Year"), dataFile.value("CCYear"));
						safeType(driver, getObject("AirCorpCom_PaymentPage_CreditCard_Name"), dataFile.value("CCName"));
						safeType(driver, getObject("AirCorpCom_PaymentPage_CreditCard_CVV"), dataFile.value("CVV"));

						safeType(driver, getObject("AirCorpCom_PaymentPage_Bill_FirstName"), dataFile.value("Corp_Adult1_Name"));
						safeType(driver, getObject("AirCorpCom_PaymentPage_Bill_LastName"), dataFile.value("Corp_Adult1_Name"));
						safeType(driver, getObject("AirCorpCom_PaymentPage_Bill_Address"), dataFile.value("Corp_Address"));
						safeType(driver, getObject("AirCorpCom_PaymentPage_Bill_City"), dataFile.value("Corp_City"));
						safeType(driver, getObject("AirCorpCom_PaymentPage_Bill_Pin"), dataFile.value("Corp_Pin"));
						safeAutocomplete(driver, getObject("AirCorpCom_PaymentPage_Bill_Country"),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li[2]"), dataFile.value("Corp_Country"));

						safeClick(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"));
						Reporter.log("Make Payment button on payment page clicked.");
					}
				}
			}else if (paymentMethod.equals("DC")) {
				safeClick(driver, getObject("AirCorpCom_PaymentPage_CreditCard_Tab"));
				Thread.sleep(100);
				if (MakePaymentOnlyInQA2) {
					safeClick(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Tab"));
					safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Number"),  dataFile.value("Master_CC_Number"));
					safeSelect(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Exp_Month"), "5");
					safeSelect(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
					safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_CVV"), dataFile.value("MasterCard_CVV"));
					safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_BillName"), dataFile.value("MasterCard_HolderName"));
					Thread.sleep(2000);		
					safeClick(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"));
				}
			} 
			else {
				Reporter.log("Wrong payment method passed.");
				AssertJUnit.assertTrue("Failure!", false);
			}
		} else {
			Reporter.log("Make Payment button on payment page not visible.");
			AssertJUnit.assertTrue("Failure!", false);
		}
	}

	public boolean corpAir_ConfirmReturnToPaymentPage(RemoteWebDriver driver) throws Exception {
		Thread.sleep(10000);
		if (waitElement(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"), 30)) {
			Reporter.log("Returned back to Payment page.");
			return true;
		} else {
			Reporter.log("Make Payment button cant be found, Not returned to payment page.");
			AssertJUnit.assertTrue("Failure!", false);
			return false;
		}
	}

	public String AirCorpPayment_Hold(RemoteWebDriver driver) throws Exception, InterruptedException {
		String TripID = null;
		textPresent(driver, "Duration", 60);
		elementVisible(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 60);
		//elementPresent(driver, By.xpath("//div[6]/div[4]/div[3]"));
		elementPresent(driver, By.id("holdBookingFlash"));
		if(MakePaymentOnlyInQA2){
			safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));
			corpAir_Verify_Confirmation_OR_Failure(driver, "Your booking is on hold.", "Oops, your payment didn’t work", "Whatever you're looking for, isn't here", By.xpath("(//a[contains(text(),'Modify search')])[2]"),"Oops, your booking didn’t go through");
			elementPresent_Time(driver, getObject("AirCorpCom_ConfirmationPage_TripID"), 60);
			TripID = getText(driver, getObject("AirCorpCom_ConfirmationPage_TripID"));
			Reporter.log("Successful Hold Trip ID : " + TripID );	

			logger.info("Successful Hold Trip ID : " + TripID );
			Thread.sleep(20000);
		  	safeClick(driver, By.linkText("Trips"));
		  	elementPresent_Time(driver, By.xpath("//h1"), 60);
				
			elementPresent_Time(driver,getObject("AirCorp_TripsPage_SearchTrips"), 5);
			
			safeType(driver, getObject("AirCorp_TripsPage_SearchTrips"), TripID);
			safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
			Thread.sleep(3000);
			if (elementPresent_Time(driver, By.id("listView_a"), 1)){
			safeClick(driver, By.id("listView_a"));
			
			WebDriverWait wait = new WebDriverWait(driver, 5);
		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p/a")));
			safeClick(driver, By.xpath("//p/a"));
			}
			if(textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 5)){
				Reporter.log("Oops! Cleartrip’s system is behaving badly :  message is displayed");
				Assert.assertTrue(false);
			}
			elementPresent_Time(driver, By.xpath("//a[@id='confirm_button']/img"), 20);
			
			WebDriverWait waitt = new WebDriverWait(driver, 5);
		    waitt.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[1]/a/img")));
			elementPresent(driver, By.xpath("//a[@id='confirm_button']/img"));
			safeClick(driver, By.xpath("//a[@id='confirm_button']/img"));		
		}
		return TripID;		
	}

	public void corpAir_Hold_Paymentpage(RemoteWebDriver driver) throws InterruptedException, Exception {
		
		Thread.sleep(10000);
		textPresent(driver, "This is a hold booking", 100);
		//elementVisible(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"), 100);
		if (!textPresent(driver, "This is a hold booking", 1)) {
			Reporter.log("Proxy Error OR Hold Booking message is not displayed.");
			Assert.assertTrue(false);
		}
		if (MakePaymentTrue && !ProductionUrl) {
			safeClick(driver, getObject("AirCorpCom_PaymentPage_Payment_Button"));
			Reporter.log("Hold Payment Clicked");
		}
		Thread.sleep(20000);
		textPresent(driver, "Your booking is on hold", 120);
		if (textPresent(driver, "Your booking is on hold", 1)){
			String tripId = getText(driver, getObject("AirCorpCom_ConfirmationPage_TripID"));
			Reporter.log("Hold Trip Id booked is " + tripId);
			logger.info("Hold TripID : " + tripId );
			driver.get(Corp_Url+"/trips/"+tripId);
			textPresent(driver, "Release this booking", 20);
			if (elementPresent_Time(driver, getObject("CorpCom_SignIN_EmailID"), 1)){
				corp_SignIn(driver);
				textPresent(driver, "Fly anywhere. Fly everywhere.", 20);
				driver.get(Corp_Url+"/trips/"+tripId);
				textPresent(driver, "Release this booking", 20);
			}
			
			elementPresent(driver, getObject("AirCorpCom_Confirm_HoldBooking_btn"));
			safeClick(driver, getObject("AirCorpCom_Confirm_HoldBooking_btn"));
			
		}
	}

	
	public boolean corpAir_Confirmation(RemoteWebDriver driver, String Booking_Confirmation_Message) throws InterruptedException, Exception {
		if (MakePaymentOnlyInQA2) {
			Thread.sleep(5000);		

			corpAir_Verify_Confirmation_OR_Failure(driver, "Great, your booking went through successfully", "Oops, your payment didn’t work", "Whatever you're looking for, isn't here", By.xpath("(//a[contains(text(),'Modify search')])[2]"),"Oops, your booking didn’t go through");
			
			if(!textPresent(driver, "Great, your booking went through successfully", 10)) {
				Reporter.log("Great, your booking went through successfully : text is not displayed");
				Reporter.log("//===============================Booking Failed ========================================//");
			}
			if(!elementVisible(driver, getObject("AirCorpCom_Confirmation_Message"), 10)) {
				//elementPresent(driver, getObject("AirCorpCom_Confirmation_Message"));
				Reporter.log("Corp Booking Confirmation message is not displayed");
				Assert.assertTrue(false);
				}
			String confirmationMessage = driver.findElement(getObject("AirCorpCom_Confirmation_Message")).getText();
			if (confirmationMessage.equals("Great, your booking went through successfully.")){
				Reporter.log("Booking passed successfully.");
				String TripID = getText(driver, getObject("AirCorpCom_ConfirmationPage_TripID"));
				logURL(driver);
				logger.info(Booking_Confirmation_Message + TripID );
				Reporter.log("Trip Id booked is " + TripID);
				return true;
			}else if (textPresent(driver, "Proxy Error", 1)) {
				Reporter.log("Proxy Error displayed, hence no TripID collected.");
				return true;
				
			} else {
				Reporter.log("Booking not confirmed.");
				AssertJUnit.assertTrue("Failure!", false);
			}
		}
		return false;
	}

	public void corpAir_Verify_Confirmation_OR_Failure(RemoteWebDriver driver, String Success_Message, String Oops_Failure, String Whatever_Error, By BackTo_SRP, String Oops_Bookingerror) throws InterruptedException, Exception {
		int i=0;
		for( i=0; i<=15;i++) {
			if(textPresent(driver, Success_Message, 1)) {
					break;	
				}			 
			else if(textPresent(driver, Oops_Failure, 1)) {
				Reporter.log(Oops_Failure+ " : Error message is displayed");
				Assert.assertTrue(false);
			}			
			else if(textPresent(driver, Whatever_Error, 1)) {
				Reporter.log(Whatever_Error+" : error is displayed");
				Assert.assertTrue(false);
			}
			else if(textPresent(driver, "Proxy Error", 1)) {
				Reporter.log("Proxy Error : error is displayed");
				Assert.assertTrue(false);
			}else if(elementVisible(driver, BackTo_SRP, 1)) {
				Reporter.log("Page has redirected to SRP");
				Assert.assertTrue(false);
			}else if(textPresent(driver, Oops_Bookingerror, 1)) {
				Reporter.log("Oops, your booking didn’t go through : error is displayed");
				Assert.assertTrue(false);
			}else if(elementVisible(driver, By.id("OneWay"), 1)) {
				Reporter.log("Page is redirected to Home page");
				Assert.assertTrue(false);
			}else if(textPresent(driver, "page isn’t working", 1)) {
				Reporter.log("Page isn’t working : error is displayed");
				Assert.assertTrue(false);
			}else if(textPresent(driver, "We do not have the billing address country you entered on record", 1)) {
				Reporter.log("Credit Card Country name is not selected from Ajax");
				Assert.assertTrue(false);
			}
			
		}
	}
	
	public void corpAir_Confirmation_APIS(RemoteWebDriver driver) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Fill APIS information")));
	    
	    
	    
	    safeClick(driver, By.linkText("Fill APIS information"));
	    Thread.sleep(5000);
	    
	    driver.switchTo().frame("modal_window");
		
		 
		elementPresent_Time(driver, By.id("dobDay1"), 10); 
		 safeSelect(driver, By.id("dobDay1"), "1");
		 safeSelect(driver, By.id("dobMonth1"), "Nov");
		 safeSelect(driver, By.id("dobYear1"), "1971");
		 safeType(driver, By.id("apis_Nationality1"), "Indi");
		 Thread.sleep(1000);
		 
		 driver.findElement(By.id("apis_Passport1")).sendKeys("123456789");
		 
		 safeSelect(driver, By.id("expyDay1"), "19");
		 safeSelect(driver, By.id("expyMonth1"), "Nov");
		 safeSelect(driver, By.id("expyYear1"), "2033");
		 
		 safeType(driver, By.id("apis_Country1"), "india");
		 
		 
		 driver.findElement(By.id("apisSave1")).click();
		 
		 AssertJUnit.assertTrue("APIS Info NOT Submitted", textPresent(driver, "APIS information submitted", 10));
		
	   Thread.sleep(3000);   
		 Reporter.log("APIS information submitted");
		 
		//corpAir_Signout(driver);
	
	}
	
	
	public void corpAir_Signout(RemoteWebDriver driver) throws Exception {
		driver.findElement(getObject("AirCorpCom_Signout")).click();
		Reporter.log("Signed out.");
	}

	public void corpAir_Dom_PaxEntry(RemoteWebDriver driver, String Adults, String Childrens, String Infants) throws Exception {
		boolean GDS_Flight = b2bGDSAirlines(driver);
		if (GDS_Flight) {
			int Adult_Int = Integer.parseInt(Adults);
			for (int i = 1; i <= Adult_Int; i++) {
				String AdultID = "adultTraveller" + i;
				if (elementVisible(driver, By.id(AdultID), 1)) {
					safeAutocomplete(driver, By.id(AdultID), getObject("AirCorpCom_TravellerPage_Ajax"),
							dataFile.value("afname" + i));
				}
			}

			int Child_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Child_Int; i++) {
				String ChildID = "childauto_" + i;
				String ChildDOB_Day = "child" + i + "'sDobDay";
				String ChildDOB_Month = "child" + i + "'sDobMonth";
				String ChildDOB_Year = "child" + i + "'sDobYear";

				if (elementVisible(driver, By.id(ChildID), 1)) {
					// safeType(driver, By.id(ChildID) ,"test test");
					safeAutocomplete(driver, By.id(ChildID), getObject("AirCorpCom_TravellerPage_Ajax"),
							dataFile.value("cfname" + i));
					WebElement element1 = (new WebDriverWait(driver,30))
							   .until(ExpectedConditions.elementToBeClickable(By.id(ChildDOB_Day)));
					Thread.sleep(3000);
					safeSelect(driver, By.id(ChildDOB_Day), "10");
					Thread.sleep(1000);
					safeSelect(driver, By.id(ChildDOB_Month), "Dec");
					safeSelect(driver, By.id(ChildDOB_Year), putYear(-7));
				}
			}

			int Infant_Int = Integer.parseInt(Infants);
			for (int i = 1; i <= Infant_Int; i++) {
				String Infant = "infantauto_" + i;
				String InfantDOB_Day = "infant" + i + "'sDobDay";
				String InfantDOB_Month = "infant" + i + "'sDobMonth";
				String InfantDOB_Year = "infant" + i + "'sDobYear";
				if (elementVisible(driver, By.id(Infant), 1)) {
					safeAutocomplete(driver, By.id(Infant), getObject("AirCorpCom_TravellerPage_Ajax"),
							dataFile.value("ilname" + i));
					safeSelect(driver, By.id(InfantDOB_Day), "10");
					safeSelect(driver, By.id(InfantDOB_Month), "Dec");
					safeSelect(driver, By.id(InfantDOB_Year), putYear(-1));
				}
			}
		} else {
			int Adult_Int = Integer.parseInt(Adults);
			for (int i = 1; i <= Adult_Int; i++) {
				String AdultID = "adultTraveller" + i;
				if (elementVisible(driver, By.id(AdultID), 1)) {
					safeAutocomplete(driver, By.id(AdultID), getObject("AirCorpCom_TravellerPage_Ajax"), "Prashanth");
				}
			}

			int Child_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Child_Int; i++) {
				String ChildID = "childauto_" + i;
				String ChildDOB_Day = "child" + i + "'sDobDay";
				String ChildDOB_Month = "child" + i + "'sDobMonth";
				String ChildDOB_Year = "child" + i + "'sDobYear";
				if (elementVisible(driver, By.id(ChildID), 1)) {
					safeAutocomplete(driver, By.id(ChildID), getObject("AirCorpCom_TravellerPage_Ajax"), "Test Booking");
					WebElement element1 = (new WebDriverWait(driver,30))
							   .until(ExpectedConditions.elementToBeClickable(By.id(ChildDOB_Day)));
					Thread.sleep(3000);
					safeSelect(driver, By.id(ChildDOB_Day), "10");
					Thread.sleep(1000);
					safeSelect(driver, By.id(ChildDOB_Month), "Dec");
					safeSelect(driver, By.id(ChildDOB_Year), putYear(-7));
				}
			}

			int Infant_Int = Integer.parseInt(Infants);
			for (int i = 1; i <= Infant_Int; i++) {
				String Infant = "infantauto_" + i;
				String InfantDOB_Day = "infant" + i + "'sDobDay";
				String InfantDOB_Month = "infant" + i + "'sDobMonth";
				String InfantDOB_Year = "infant" + i + "'sDobYear";
				if (elementVisible(driver, By.id(Infant), 1)) {
					// safeType(driver, By.id(Infant) ,"test test");
					safeAutocomplete(driver, By.id(Infant), getObject("AirCorpCom_TravellerPage_Ajax"), "Test Booking");
					safeSelect(driver, By.id(InfantDOB_Day), "10");
					safeSelect(driver, By.id(InfantDOB_Month), "Dec");
					safeSelect(driver, By.id(InfantDOB_Year), putYear(-1));
				}
			}
		}
	}

	public void corpAir_Intl_PaxEntry(RemoteWebDriver driver, String Adults, String Childrens, String Infants) throws Exception {
		boolean GDS_Flight = b2bGDSAirlines(driver);
		if (GDS_Flight) {
			int Adult_Int = Integer.parseInt(Adults);
			for (int i = 1; i <= Adult_Int; i++) {
				String AdultID = "adultTraveller" + i;

				String PassportNo = "adult" + i + "'sPassport";
				String IssuingCountry = "AdultPPIssuingCountry" + i;
				String PassportExpiryDay = "AdultPPDay" + i;
				String PassportExpiryMonth = "AdultPPMonth" + i;
				String PassportExpiryYear = "AdultPPYear"+i;
				String VisaType = "adultVisaType" + i;
				String AdultDOB_Day = "adult" + i + "'sDobDay";
				String AdultDOB_Month = "adult" + i + "'sDobMonth";
				String AdultDOB_Year = "adult" + i + "'sDobYear";

				
				if (elementVisible(driver, By.id(AdultID), 1)) {
					safeAutocomplete(driver, By.id(AdultID), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("afname" + i));
					WebElement element = (new WebDriverWait(driver,30))
							   .until(ExpectedConditions.elementToBeClickable(By.name(AdultDOB_Day)));
					Thread.sleep(3000);
					if(elementPresent(driver,By.name(AdultDOB_Day),1)) {
					smartSelect(driver, By.name(AdultDOB_Day), "10");
					Thread.sleep(1000);
					smartSelect(driver, By.name(AdultDOB_Month), "Jan");
					smartSelect(driver, By.name(AdultDOB_Year), putYear(-14));
					}
					smartSelect(driver, By.name(VisaType), "Business");
					/*if (elementVisible(driver, By.name(VisaType), 1)) {
						safeSelect(driver, By.name(VisaType), "Business");
					}*/
					if (elementVisible(driver, By.id(PassportNo), 1)) {
						safeType(driver, By.id(PassportNo), "12345");
						Thread.sleep(5000);
						if(elementVisible(driver, By.id(IssuingCountry), 1)){
						safeAutocomplete(driver, By.id(IssuingCountry), By.xpath("//*[@id='autocompleteOptionsContainer']/li"),	"india");
						}
						smartSelect(driver, By.id(PassportExpiryDay), "10");
						smartSelect(driver, By.id(PassportExpiryMonth), "Oct");
						smartSelect(driver, By.id(PassportExpiryYear), "2020");
					}
				}
			}

			int Child_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Child_Int; i++) {
				String ChildID = "childauto_" + i;
				String ChildDOB_Day = "child" + i + "'sDobDay";
				String ChildDOB_Month = "child" + i + "'sDobMonth";
				String ChildDOB_Year = "child" + i + "'sDobYear";

				String ChildPassportNo = "child" + i + "'sPassport";
				String ChildIssuingCountry = "ChildPPIssuingCountry" + i;
				String ChildPassportExpiryDay = "childPPDay" + i;
				String ChildPassportExpiryMonth = "ChildPPMonth" + i;
				String ChildPassportExpiryYear = "ChildPPYear"+i;
				String ChildVisa = "childVisaType" + i;

				if (elementVisible(driver, By.id(ChildID), 1)) {
					safeAutocomplete(driver, By.id(ChildID), getObject("AirCorpCom_TravellerPage_Ajax"),	dataFile.value("cfname" + i));
					WebElement element = (new WebDriverWait(driver,30))
							   .until(ExpectedConditions.elementToBeClickable(By.id(ChildDOB_Day)));
					safeSelect(driver, By.id(ChildDOB_Day), "10");
					safeSelect(driver, By.id(ChildDOB_Month), "Dec");
					safeSelect(driver, By.id(ChildDOB_Year), putYear(-7));
					if (elementVisible(driver, By.name(ChildVisa), 1)) {
						safeSelect(driver, By.name(ChildVisa), "Business");
					}
					if (elementVisible(driver, By.id(ChildPassportNo), 1)) {
						safeType(driver, By.id(ChildPassportNo), "12345");
						safeAutocomplete(driver, By.id(ChildIssuingCountry),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li"), "india");
						safeSelect(driver, By.name(ChildPassportExpiryDay), "10");
						safeSelect(driver, By.id(ChildPassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(ChildPassportExpiryYear), "2020");
					}
				}
			}

			int Infant_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Infant_Int; i++) {
				String Infant = "infantauto_" + i;
				String InfantDOB_Day = "infant" + i + "'sDobDay";
				String InfantDOB_Month = "infant" + i + "'sDobMonth";
				String InfantDOB_Year = "infant" + i + "'sDobYear";

				String InfantPassportNo = "infant" + i + "'sPassport";
				String InfantIssuingCountry = "InfantPPIssuingCountry" + i;
				String InfantPassportExpiryDay = "infantPPDay" + i;
				String InfantPassportExpiryMonth = "InfantPPMonth" + i;
				String InfantPassportExpiryYear = "InfantPPYear"+i;
				String InfantVisaType = "infantVisaType" + i;

				if (elementVisible(driver, By.id(Infant), 1)) {
					safeAutocomplete(driver, By.id(Infant), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("ilname" + i));
					System.out.println(InfantDOB_Day + "        "+InfantDOB_Month+"        "+InfantDOB_Year);
					WebElement element = (new WebDriverWait(driver,100))
							   .until(ExpectedConditions.elementToBeClickable(By.id(InfantDOB_Day)));
					safeSelect(driver, By.id(InfantDOB_Day), "10");
					safeSelect(driver, By.id(InfantDOB_Month), "Dec");
					safeSelect(driver, By.id(InfantDOB_Year), putYear(-1));
					if (elementVisible(driver, By.name(InfantVisaType), 1)) {
						safeSelect(driver, By.name(InfantVisaType), "Business");
					}
					if (elementVisible(driver, By.id(InfantPassportNo), 1)) {
						safeType(driver, By.id(InfantPassportNo), "12345");
						safeAutocomplete(driver, By.id(InfantIssuingCountry),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li"), "india");
						safeSelect(driver, By.name(InfantPassportExpiryDay), "10");
						safeSelect(driver, By.id(InfantPassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(InfantPassportExpiryYear), "2020");
					}
				}
			}
		}
		
		else {
			int Adult_Int = Integer.parseInt(Adults);
			for (int i = 1; i <= Adult_Int; i++) {
				String AdultID = "adultTraveller" + i;

				String PassportNo = "adult" + i + "'sPassport";
				String IssuingCountry = "AdultPPIssuingCountry" + i;
				String PassportExpiryDay = "AdultPPDay" + i;
				String PassportExpiryMonth = "AdultPPMonth" + i;
				String PassportExpiryYear = "AdultPPYear"+i;
				String VisaType = "adultVisaType" + i;
				String AdultDOB_Day = "adult" + i + "'sDobDay";
				String AdultDOB_Month = "adult" + i + "'sDobMonth";
				String AdultDOB_Year = "adult" + i + "'sDobYear";

				
				if (elementVisible(driver, By.id(AdultID), 1)) {
					safeAutocomplete(driver, By.id(AdultID), getObject("AirCorpCom_TravellerPage_Ajax"), "Test Booking");

					
					smartSelect(driver, By.name(AdultDOB_Day), "10");
					smartSelect(driver, By.name(AdultDOB_Month), "Jan");
					smartSelect(driver, By.name(AdultDOB_Year), putYear(-14));
					smartSelect(driver, By.name(VisaType), "Business");
					/*if (elementVisible(driver, By.name(VisaType), 1)) {
						safeSelect(driver, By.name(VisaType), "Business");
					}*/
					if (elementVisible(driver, By.id(PassportNo), 1)) {
						safeType(driver, By.id(PassportNo), "12345");
						safeAutocomplete(driver, By.id(IssuingCountry), By.xpath("//*[@id='autocompleteOptionsContainer']/li"),
								"india");
						smartSelect(driver, By.id(PassportExpiryDay), "10");
						smartSelect(driver, By.id(PassportExpiryMonth), "Oct");
						smartSelect(driver, By.id(PassportExpiryYear), "2020");
					}
				}
			}

			int Child_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Child_Int; i++) {
				String ChildID = "childauto_" + i;
				String ChildDOB_Day = "child" + i + "'sDobDay";
				String ChildDOB_Month = "child" + i + "'sDobMonth";
				String ChildDOB_Year = "child" + i + "'sDobYear";

				String ChildPassportNo = "child" + i + "'sPassport";
				String ChildIssuingCountry = "ChildPPIssuingCountry" + i;
				String ChildPassportExpiryDay = "childPPDay" + i;
				String ChildPassportExpiryMonth = "ChildPPMonth" + i;
				String ChildPassportExpiryYear = "ChildPPYear"+i;
				String ChildVisa = "childVisaType" + i;

				if (elementVisible(driver, By.id(ChildID), 1)) {
					safeAutocomplete(driver, By.id(ChildID), getObject("AirCorpCom_TravellerPage_Ajax"), "Test Booking");
					Thread.sleep(2000);
					safeSelect(driver, By.id(ChildDOB_Day), "10");
					safeSelect(driver, By.id(ChildDOB_Month), "Dec");
					safeSelect(driver, By.id(ChildDOB_Year), putYear(-7));
					if (elementVisible(driver, By.name(ChildVisa), 1)) {
						safeSelect(driver, By.name(ChildVisa), "Business");
					}
					if (elementVisible(driver, By.id(ChildPassportNo), 1)) {
						safeType(driver, By.id(ChildPassportNo), "12345");
						safeAutocomplete(driver, By.id(ChildIssuingCountry),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li"), "india");
						safeSelect(driver, By.name(ChildPassportExpiryDay), "10");
						safeSelect(driver, By.id(ChildPassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(ChildPassportExpiryYear), "2020");
					}
				}
			}

			int Infant_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Infant_Int; i++) {
				String Infant = "infantauto_" + i;
				String InfantDOB_Day = "infant" + i + "'sDobDay";
				String InfantDOB_Month = "infant" + i + "'sDobMonth";
				String InfantDOB_Year = "infant" + i + "'sDobYear";

				String InfantPassportNo = "infant" + i + "'sPassport";
				String InfantIssuingCountry = "InfantPPIssuingCountry" + i;
				String InfantPassportExpiryDay = "infantPPDay" + i;
				String InfantPassportExpiryMonth = "InfantPPMonth" + i;
				String InfantPassportExpiryYear = "InfantPPYear"+i;
				String InfantVisaType = "infantVisaType" + i;

				if (elementVisible(driver, By.id(Infant), 1)) {
					safeAutocomplete(driver, By.id(Infant), getObject("AirCorpCom_TravellerPage_Ajax"), "Test Booking");
					Thread.sleep(2000);
					safeSelect(driver, By.id(InfantDOB_Day), "10");
					safeSelect(driver, By.id(InfantDOB_Month), "Dec");
					safeSelect(driver, By.id(InfantDOB_Year), putYear(-1));
					if (elementVisible(driver, By.name(InfantVisaType), 1)) {
						safeSelect(driver, By.name(InfantVisaType), "Business");
					}
					if (elementVisible(driver, By.id(InfantPassportNo), 1)) {
						safeType(driver, By.id(InfantPassportNo), "12345");
						safeAutocomplete(driver, By.id(InfantIssuingCountry),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li"), "india");
						safeSelect(driver, By.name(InfantPassportExpiryDay), "10");
						safeSelect(driver, By.id(InfantPassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(InfantPassportExpiryYear), "2020");
					}
				}
			}
		}

	}

	public void corpAir_PaxEntry_PTA(RemoteWebDriver driver, String Adults, String Childrens, String Infants) throws Exception {
		boolean GDS_Flight = b2bGDSAirlines(driver);
		if (GDS_Flight) {
			int Adult_Int = Integer.parseInt(Adults);
			for (int i = 1; i <= Adult_Int; i++) {
				String AdultID = "adultTraveller" + i;
				String AdultDOB_Day = "adult" + i + "'sDobDay";
				String AdultDOB_Month = "adult" + i + "'sDobMonth";
				String AdultDOB_Year = "adult" + i + "'sDobYear";

				String PassportNo = "adult" + i + "'sPassport";
				String IssuingCountry = "AdultPPIssuingCountry" + i;
				String PassportExpiryDay = "AdultPPDay" + i;
				String PassportExpiryMonth = "AdultPPMonth" + i;
				String PassportExpiryYear = "AdultPPYear";
				String VisaType = "adultVisaType" + i;

				if (elementVisible(driver, By.id(AdultID), 1)) {
					safeAutocomplete(driver, By.id(AdultID), getObject("AirCorpCom_TravellerPage_Ajax"), "TestPTA Booking");
						Thread.sleep(5000);
						elementVisible(driver, By.name(AdultDOB_Day), 10);
						smartSelect(driver, By.name(AdultDOB_Day), "10");
						smartSelect(driver, By.name(AdultDOB_Month), "Jan");
						smartSelect(driver, By.name(AdultDOB_Year), putYear(-14));
						smartSelect(driver, By.name(VisaType), "Business");
						
					if (elementVisible(driver, By.id(PassportNo), 1)) {
						safeType(driver, By.id(PassportNo), "12345");
						safeAutocomplete(driver, By.id(IssuingCountry), By.xpath("//*[@id='autocompleteOptionsContainer']/li"),
								"india");
						safeSelect(driver, By.id(PassportExpiryDay), "10");
						safeSelect(driver, By.id(PassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(PassportExpiryYear), "2020");
					}
				}
			}
			

			int Child_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Child_Int; i++) {
				String ChildID = "childauto_" + i;
				String ChildDOB_Day = "child" + i + "'sDobDay";
				String ChildDOB_Month = "child" + i + "'sDobMonth";
				String ChildDOB_Year = "child" + i + "'sDobYear";

				String ChildPassportNo = "adult" + i + "'sPassport";
				String ChildIssuingCountry = "AdultPPIssuingCountry" + i;
				String ChildPassportExpiryDay = "AdultPPDay" + i;
				String ChildPassportExpiryMonth = "AdultPPMonth" + i;
				String ChildPassportExpiryYear = "AdultPPYear";
				String ChildVisa = "adultVisaType" + i;

				if (elementVisible(driver, By.id(ChildID), 1)) {
					safeAutocomplete(driver, By.id(ChildID), getObject("AirCorpCom_TravellerPage_Ajax"),
							dataFile.value("cfname" + i));
					safeSelect(driver, By.id(ChildDOB_Day), "10");
					safeSelect(driver, By.id(ChildDOB_Month), "Dec");
					safeSelect(driver, By.id(ChildDOB_Year), putYear(-7));
					if (elementVisible(driver, By.name(ChildVisa), 1)) {
						safeSelect(driver, By.name(ChildVisa), "Business");
					}
					if (elementVisible(driver, By.id(ChildPassportNo), 1)) {
						safeType(driver, By.id(ChildPassportNo), "12345");
						safeAutocomplete(driver, By.id(ChildIssuingCountry),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li"), "india");
						safeSelect(driver, By.id(ChildPassportExpiryDay), "10");
						safeSelect(driver, By.id(ChildPassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(ChildPassportExpiryYear), "2020");
					}
				}
			}

			int Infant_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Infant_Int; i++) {
				String Infant = "infantauto_" + i;
				String InfantDOB_Day = "infant" + i + "'sDobDay";
				String InfantDOB_Month = "infant" + i + "'sDobMonth";
				String InfantDOB_Year = "infant" + i + "'sDobYear";

				String InfantPassportNo = "adult" + i + "'sPassport";
				String InfantIssuingCountry = "AdultPPIssuingCountry" + i;
				String InfantPassportExpiryDay = "AdultPPDay" + i;
				String InfantPassportExpiryMonth = "AdultPPMonth" + i;
				String InfantPassportExpiryYear = "AdultPPYear";
				String InfantVisaType = "adultVisaType" + i;

				if (elementVisible(driver, By.id(Infant), 1)) {
					// safeType(driver, By.id(Infant) ,"test test");
					safeAutocomplete(driver, By.id(Infant), getObject("AirCorpCom_TravellerPage_Ajax"),
							dataFile.value("ilname" + i));
					safeSelect(driver, By.id(InfantDOB_Day), "10");
					safeSelect(driver, By.id(InfantDOB_Month), "Dec");
					safeSelect(driver, By.id(InfantDOB_Year), putYear(-1));
					if (elementVisible(driver, By.name(InfantVisaType), 1)) {
						safeSelect(driver, By.name(InfantVisaType), "Business");
					}
					if (elementVisible(driver, By.id(InfantPassportNo), 1)) {
						safeType(driver, By.id(InfantPassportNo), "12345");
						safeAutocomplete(driver, By.id(InfantIssuingCountry),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li"), "india");
						safeSelect(driver, By.id(InfantPassportExpiryDay), "10");
						safeSelect(driver, By.id(InfantPassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(InfantPassportExpiryYear), "2020");
					}
				}
			}
		} else {
			int Adult_Int = Integer.parseInt(Adults);
			for (int i = 1; i <= Adult_Int; i++) {
				String AdultID = "adultTraveller" + i;

				String PassportNo = "adult" + i + "'sPassport";
				String IssuingCountry = "AdultPPIssuingCountry" + i;
				String PassportExpiryDay = "AdultPPDay" + i;
				String PassportExpiryMonth = "AdultPPMonth" + i;
				String PassportExpiryYear = "AdultPPYear";
				String VisaType = "adultVisaType" + i;
				String AdultDOB_Day = "adult" + i + "'sDobDay";
				String AdultDOB_Month = "adult" + i + "'sDobMonth";
				String AdultDOB_Year = "adult" + i + "'sDobYear";

				
				if (elementVisible(driver, By.id(AdultID), 1)) {
					safeAutocomplete(driver, By.id(AdultID), getObject("AirCorpCom_TravellerPage_Ajax"), "TestPTA Booking");

					
					smartSelect(driver, By.name(AdultDOB_Day), "10");
					smartSelect(driver, By.name(AdultDOB_Month), "Jan");
					smartSelect(driver, By.name(AdultDOB_Year), putYear(-14));
					smartSelect(driver, By.name(VisaType), "Business");
					/*if (elementVisible(driver, By.name(VisaType), 1)) {
						safeSelect(driver, By.name(VisaType), "Business");
					}*/
					if (elementVisible(driver, By.id(PassportNo), 1)) {
						safeType(driver, By.id(PassportNo), "12345");
						safeAutocomplete(driver, By.id(IssuingCountry), By.xpath("//*[@id='autocompleteOptionsContainer']/li"),
								"india");
						smartSelect(driver, By.id(PassportExpiryDay), "10");
						smartSelect(driver, By.id(PassportExpiryMonth), "Oct");
						smartSelect(driver, By.id(PassportExpiryYear), "2020");
					}
				}
			}

			int Child_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Child_Int; i++) {
				String ChildID = "childauto_" + i;
				String ChildDOB_Day = "child" + i + "'sDobDay";
				String ChildDOB_Month = "child" + i + "'sDobMonth";
				String ChildDOB_Year = "child" + i + "'sDobYear";

				String ChildPassportNo = "adult" + i + "'sPassport";
				String ChildIssuingCountry = "AdultPPIssuingCountry" + i;
				String ChildPassportExpiryDay = "AdultPPDay" + i;
				String ChildPassportExpiryMonth = "AdultPPMonth" + i;
				String ChildPassportExpiryYear = "AdultPPYear";
				String ChildVisa = "adultVisaType" + i;

				if (elementVisible(driver, By.id(ChildID), 1)) {
					safeAutocomplete(driver, By.id(ChildID), getObject("AirCorpCom_TravellerPage_Ajax"), "Test Booking");
					safeSelect(driver, By.id(ChildDOB_Day), "10");
					safeSelect(driver, By.id(ChildDOB_Month), "Dec");
					safeSelect(driver, By.id(ChildDOB_Year), putYear(-7));
					if (elementVisible(driver, By.name(ChildVisa), 1)) {
						safeSelect(driver, By.name(ChildVisa), "Business");
					}
					if (elementVisible(driver, By.id(ChildPassportNo), 1)) {
						safeType(driver, By.id(ChildPassportNo), "12345");
						safeAutocomplete(driver, By.id(ChildIssuingCountry),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li"), "india");
						safeSelect(driver, By.id(ChildPassportExpiryDay), "10");
						safeSelect(driver, By.id(ChildPassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(ChildPassportExpiryYear), "2020");
					}
				}
			}

			int Infant_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Infant_Int; i++) {
				String Infant = "infantauto_" + i;
				String InfantDOB_Day = "infant" + i + "'sDobDay";
				String InfantDOB_Month = "infant" + i + "'sDobMonth";
				String InfantDOB_Year = "infant" + i + "'sDobYear";

				String InfantPassportNo = "adult" + i + "'sPassport";
				String InfantIssuingCountry = "AdultPPIssuingCountry" + i;
				String InfantPassportExpiryDay = "AdultPPDay" + i;
				String InfantPassportExpiryMonth = "AdultPPMonth" + i;
				String InfantPassportExpiryYear = "AdultPPYear";
				String InfantVisaType = "adultVisaType" + i;

				if (elementVisible(driver, By.id(Infant), 1)) {
					safeAutocomplete(driver, By.id(Infant), getObject("AirCorpCom_TravellerPage_Ajax"), "Test Booking");
					safeSelect(driver, By.id(InfantDOB_Day), "10");
					safeSelect(driver, By.id(InfantDOB_Month), "Dec");
					safeSelect(driver, By.id(InfantDOB_Year), putYear(-1));
					if (elementVisible(driver, By.name(InfantVisaType), 1)) {
						safeSelect(driver, By.name(InfantVisaType), "Business");
					}
					if (elementVisible(driver, By.id(InfantPassportNo), 1)) {
						safeType(driver, By.id(InfantPassportNo), "12345");
						safeAutocomplete(driver, By.id(InfantIssuingCountry),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li"), "india");
						safeSelect(driver, By.id(InfantPassportExpiryDay), "10");
						safeSelect(driver, By.id(InfantPassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(InfantPassportExpiryYear), "2020");
					}
				}
			}
		}

	}

	public void corpAir_PaxEntry_FrequentFlier_MealPref(RemoteWebDriver driver, String Adults, String Childrens, String Infants) throws Exception {
			int Adult_Int = Integer.parseInt(Adults);
			for (int i = 1; i <= Adult_Int; i++) {
				String AdultID = "adultTraveller" + i;
				String AdultDOB_Day = "adult" + i + "'sDobDay";
				String AdultDOB_Month = "adult" + i + "'sDobMonth";
				String AdultDOB_Year = "adult" + i + "'sDobYear";

				String PassportNo = "adult" + i + "'sPassport";
				String IssuingCountry = "AdultPPIssuingCountry" + i;
				String PassportExpiryDay = "AdultPPDay" + i;
				String PassportExpiryMonth = "AdultPPMonth" + i;
				String PassportExpiryYear = "AdultPPYear";
				String VisaType = "adultVisaType" + i;

				if (elementVisible(driver, By.id(AdultID), 1)) {
					safeAutocomplete(driver, By.id(AdultID), getObject("AirCorpCom_TravellerPage_Ajax"), "Test MealPref");
						Thread.sleep(5000);
						elementVisible(driver, By.name(AdultDOB_Day), 10);
						smartSelect(driver, By.name(AdultDOB_Day), "10");
						smartSelect(driver, By.name(AdultDOB_Month), "Jan");
						smartSelect(driver, By.name(AdultDOB_Year), putYear(-14));
						smartSelect(driver, By.name(VisaType), "Business");
						
					if (elementVisible(driver, By.id(PassportNo), 1)) {
						safeType(driver, By.id(PassportNo), "12345");
						safeAutocomplete(driver, By.id(IssuingCountry), By.xpath("//*[@id='autocompleteOptionsContainer']/li"),
								"india");
						safeSelect(driver, By.id(PassportExpiryDay), "10");
						safeSelect(driver, By.id(PassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(PassportExpiryYear), "2020");
					}
				}
			}
			

			int Child_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Child_Int; i++) {
				String ChildID = "childauto_" + i;
				String ChildDOB_Day = "child" + i + "'sDobDay";
				String ChildDOB_Month = "child" + i + "'sDobMonth";
				String ChildDOB_Year = "child" + i + "'sDobYear";

				String ChildPassportNo = "adult" + i + "'sPassport";
				String ChildIssuingCountry = "AdultPPIssuingCountry" + i;
				String ChildPassportExpiryDay = "AdultPPDay" + i;
				String ChildPassportExpiryMonth = "AdultPPMonth" + i;
				String ChildPassportExpiryYear = "AdultPPYear";
				String ChildVisa = "adultVisaType" + i;

				if (elementVisible(driver, By.id(ChildID), 1)) {
					safeAutocomplete(driver, By.id(ChildID), getObject("AirCorpCom_TravellerPage_Ajax"),
							dataFile.value("cfname" + i));
					safeSelect(driver, By.id(ChildDOB_Day), "10");
					safeSelect(driver, By.id(ChildDOB_Month), "Dec");
					safeSelect(driver, By.id(ChildDOB_Year), putYear(-7));
					if (elementVisible(driver, By.name(ChildVisa), 1)) {
						safeSelect(driver, By.name(ChildVisa), "Business");
					}
					if (elementVisible(driver, By.id(ChildPassportNo), 1)) {
						safeType(driver, By.id(ChildPassportNo), "12345");
						safeAutocomplete(driver, By.id(ChildIssuingCountry),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li"), "india");
						safeSelect(driver, By.id(ChildPassportExpiryDay), "10");
						safeSelect(driver, By.id(ChildPassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(ChildPassportExpiryYear), "2020");
					}
				}
			}

			int Infant_Int = Integer.parseInt(Childrens);
			for (int i = 1; i <= Infant_Int; i++) {
				String Infant = "infantauto_" + i;
				String InfantDOB_Day = "infant" + i + "'sDobDay";
				String InfantDOB_Month = "infant" + i + "'sDobMonth";
				String InfantDOB_Year = "infant" + i + "'sDobYear";

				String InfantPassportNo = "adult" + i + "'sPassport";
				String InfantIssuingCountry = "AdultPPIssuingCountry" + i;
				String InfantPassportExpiryDay = "AdultPPDay" + i;
				String InfantPassportExpiryMonth = "AdultPPMonth" + i;
				String InfantPassportExpiryYear = "AdultPPYear";
				String InfantVisaType = "adultVisaType" + i;

				if (elementVisible(driver, By.id(Infant), 1)) {
					// safeType(driver, By.id(Infant) ,"test test");
					safeAutocomplete(driver, By.id(Infant), getObject("AirCorpCom_TravellerPage_Ajax"),
							dataFile.value("ilname" + i));
					safeSelect(driver, By.id(InfantDOB_Day), "10");
					safeSelect(driver, By.id(InfantDOB_Month), "Dec");
					safeSelect(driver, By.id(InfantDOB_Year), putYear(-1));
					if (elementVisible(driver, By.name(InfantVisaType), 1)) {
						safeSelect(driver, By.name(InfantVisaType), "Business");
					}
					if (elementVisible(driver, By.id(InfantPassportNo), 1)) {
						safeType(driver, By.id(InfantPassportNo), "12345");
						safeAutocomplete(driver, By.id(InfantIssuingCountry),
								By.xpath("//*[@id='autocompleteOptionsContainer']/li"), "india");
						safeSelect(driver, By.id(InfantPassportExpiryDay), "10");
						safeSelect(driver, By.id(InfantPassportExpiryMonth), "Oct");
						safeSelect(driver, By.id(InfantPassportExpiryYear), "2020");
					}
				}
			}
		

	}

	

	
	
	public void AirCorpPax(RemoteWebDriver driver, String adult, String children, String infant) throws Exception {
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
                    	safeAutocomplete(driver, By.id(Adult_Lname_ID), getObject("AirCorpCom_TravellerPage_Ajax"), dataFile.value("alname" + i));
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
	                    if(elementPresent_Time(driver,  By.id(Adult_Passport_Exp_Day_ID), 1)){
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
                	boolean child_Passport = elementPresent_Time(driver, By.name(Child_Visa_ID), 1);
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
                    	safeAutocomplete(driver, By.id(Adult_Lname_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "Test");
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
                    	safeAutocomplete(driver, By.id(Child_Lname_ID), getObject("AirCorpCom_TravellerPage_Ajax"), "Test");
                    	safeSelect(driver, By.id(Child_Dobday_ID), dataFile.value("cdobday"));
                    	safeSelect(driver, By.id(Child_Dobmonth_ID), Dob_month);
                    	safeSelect(driver, By.id(Child_Dobyear_ID), putYear(-3));
                    } else {
                        Reporter.log("Child " + i + " is not displayed");
                    } 
                	boolean child_Passport = elementPresent_Time(driver, By.name(Child_Visa_ID), 1);
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
                        if(elementPresent_Time(driver, By.name(Infant_Passport_Exp_Day_ID), 1)){
	                    smartSelect(driver, By.name(Infant_Passport_Exp_Day_ID), "5");
	                    smartSelect(driver, By.id(Infant_Passport_Exp_Month_ID), Dob_month);
	                    smartSelect(driver, By.id(Infant_Passport_Exp_Year_ID), putYear(10));
                        }
                }
            }
        }
    }

	
	
	public void corpHotel_HomePageSearch(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date,
			String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, String Child1, String Child2,
			String ChildAge1, String ChildAge2) throws Exception {
		CheckIn_Date = getDate( "dd");
		CheckIn_Date = CheckIn_Date.substring(1);
		CheckIn_Date = "1"+CheckIn_Date;
		int DateInt = Integer.parseInt(CheckIn_Date);
		DateInt = DateInt+1;
		CheckOut_Date = Integer.toString(DateInt);
		logMessagePageNotLoaded(driver, getObject("HotelCorp_HomePage_HotelTab"), 50, "Corp HomePage not loaded/ Old UI / SignIN Error");
		safeClick(driver, getObject("HotelCorp_HomePage_HotelTab"));
		elementVisible(driver, getObject("HotelCom_HomePage_Traveller_DropDown"), 20);
		safeAutocomplete(driver, getObject("HotelCorp_HomePage_SearchBox"), getObject("HotelCorp_HomePage_SearchAjax"), City);
		selectCalendarDate(driver, getObject("HotelCorp_HomePage_CheckIn_Cal"),
		getObject("HotelCorp_HomePage_CheckIn_NextMonth"), 2, CheckIn_Date);
		selectCalendarDate(driver, getObject("HotelCorp_HomePage_CheckOut_Cal"),
		getObject("HotelCorp_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
		corpHotel_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value");
		String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
		Reporter.log("Hotels Searched for " + City + "CheckIn Date : " + FromDate + "CheckOut Date : " + TODate);
		safeClick(driver, getObject("HotelCorp_HomePage_Search_Button"));
		driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");

	}

	
	public void corpHotel_HomePageSearchMultiNightRoom(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date,
			String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, String Child1, String Child2,
			String ChildAge1, String ChildAge2) throws Exception {
		CheckIn_Date = getDate( "dd");
		CheckIn_Date = CheckIn_Date.substring(1);
		CheckIn_Date = "1"+CheckIn_Date;
		int DateInt = Integer.parseInt(CheckIn_Date);
		DateInt = DateInt+2;
		CheckOut_Date = Integer.toString(DateInt);
		logMessagePageNotLoaded(driver, getObject("HotelCorp_HomePage_HotelTab"), 50, "Corp HomePage not loaded/ Old UI / SignIN Error");
		safeClick(driver, getObject("HotelCorp_HomePage_HotelTab"));
		elementVisible(driver, getObject("HotelCom_HomePage_Traveller_DropDown"), 20);
		safeAutocomplete(driver, getObject("HotelCorp_HomePage_SearchBox"), getObject("HotelCorp_HomePage_SearchAjax"), City);
		selectCalendarDate(driver, getObject("HotelCorp_HomePage_CheckIn_Cal"),
		getObject("HotelCorp_HomePage_CheckIn_NextMonth"), 2, CheckIn_Date);
		selectCalendarDate(driver, getObject("HotelCorp_HomePage_CheckOut_Cal"),
		getObject("HotelCorp_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
		corpHotel_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value");
		String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
		Reporter.log("Hotels Searched for " + City + "CheckIn Date : " + FromDate + "CheckOut Date : " + TODate);
		safeClick(driver, getObject("HotelCorp_HomePage_Search_Button"));
		driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");

	}

	public void corpHotel_PaxEntry(RemoteWebDriver driver, String Rooms, String Adult1, String Adult2, String Adult3,
			String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2) throws Exception {

		safeSelect(driver, getObject("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
		safeSelect(driver, getObject("HotelCorp_HomePage_Room_DropDown"), Rooms);
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

	public Map<String, String> corpHotel_SRP(RemoteWebDriver driver, String Hotel_Name, String Booking_Type) throws Exception { 
		Map<String, String> CorpSRPData = new HashMap<String , String>();	
		for(int i =0; i <= 20; i++){
			if(textPresent(driver, "Sorry, our system is acting up", 1)){
				Reporter.log("Sorry, our system is acting up : error is displayed in SRP");
				Assert.assertTrue(false);
			}	else if(textPresent(driver, "Sorry, we couldn't find any hotels", 1)){
				Reporter.log("Sorry, we couldn't find any hotels : error is displayed in SRP");
				Assert.assertTrue(false);
			}
			else if(!elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 5)) {
				Reporter.log("Results are not displayed in SRP");	
			}
			else break;
		}		
		logMessagePageNotLoaded(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 10, "Search Results Page has not loaded  :( :( :( :( :( :(");
		elementVisible(driver, getObject("HotelCorp_SRP_HotelName_TextBox"), 10);
		/*if(elementVisible(driver, getObject("HotelCom_SRP_Rooms_Unavailable"), 2)) {
			refreshPage(driver);
			elementVisible(driver, getObject("HotelCorp_SRP_HotelName_TextBox"), 30);
			if(elementVisible(driver, getObject("HotelCom_SRP_Rooms_Unavailable"), 2)) {
				Reporter.log("Room Unavailable in SRP");
				Assert.assertTrue(false);
			}
		}*/
		logURL(driver);
		if (!Hotel_Name.isEmpty()) {
			safeAutocomplete(driver, getObject("HotelCorp_SRP_HotelName_TextBox"), getObject("HotelCorp_SRP_HotelName_Ajax"),
					Hotel_Name);
		}
		if (Booking_Type.isEmpty()) {
			elementPresent(driver, getObject("HotelCom_SRP_Price_Filter"));
			Thread.sleep(2000);
			safeClick(driver, getObject("HotelCom_SRP_Price_Filter"));
			Thread.sleep(2000);
			safeClick(driver, getObject("HotelCorp_SRP_SelectRoom_Button"));
			Thread.sleep(2000);
			if (!elementVisible(driver, getObject("HotelCorp_SRP_BookRoom_Button"), 20)) {
				safeClick(driver, getObject("HotelCorp_SRP_SelectRoom_Button"));
			}
			elementVisible(driver, getObject("HotelCorp_SRP_Hotel_RateType"), 50);
			/*
			 * String SRP_Price = getText(driver, getObject("HotelCorp_SRP_Price_Text")); String RateType = getText(driver,
			 * getObject("HotelCorp_SRP_Hotel_RateType")); String HotelName = getText(driver,
			 * getObject("HotelCorp_SRP_HotelName"));
			 * Reporter.log("SRP: Booking hotel '"+HotelName+"' with Room Type '"+RateType+"' with Price "+SRP_Price);
			 */
			safeClick(driver, getObject("HotelCorp_SRP_BookRoom_Button"));
		} else if (Booking_Type.equalsIgnoreCase("SPECIALRATE")) {
			elementPresent(driver, getObject("HotelCorp_SRP_HotelName_TextBox"));
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
			Thread.sleep(5000);
			elementVisible(driver, getObject("HotelCorp_SRP_HotelName_TextBox"), 15);
			for (int i = 1; i <= 15; i++) {      
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
		else if (Booking_Type.equalsIgnoreCase("PAH")) {

			safeClick(driver, getObject("HotelCorp_SRP_PAH_Checkbox"));
			Thread.sleep(1000);
			elementPresent(driver, getObject("HotelCorp_SRP_HotelName_TextBox"));
		/*	loop :for (int i = 1; i <= 15; i++) {      
				String PackageRateHotel_XPATH  = "//li["+i+"]/section/div[5]/div/button";
				safeClick(driver, By.xpath(PackageRateHotel_XPATH));
				for (int j = 1; j <= 3; j++) {
				String PackageRoomRate_XPATH  = "//li["+i+"]/div/div[2]/table/tbody/tr["+j+"]/td/a";
				if(elementPresent_Time(driver, By.xpath(PackageRoomRate_XPATH), 1)){
					String SpecialRate = getText(driver, By.xpath(PackageRoomRate_XPATH));
					if(!(SpecialRate.contains("Package")||(SpecialRate.contains("B2B"))||(SpecialRate.contains("Special")))){
						String PackageRoomRateButton = "//li["+i+"]/div/div[2]/table/tbody/tr["+j+"]/td[6]/input";
						safeClick(driver, By.xpath(PackageRoomRateButton));
						break loop;			
					}
				}
				}
			}*/
			Thread.sleep(2000);
			safeClick(driver, getObject("HotelCom_SRP_Price_Filter"));
			Thread.sleep(2000);
			safeClick(driver, getObject("HotelCorp_SRP_SelectRoom_Button"));
			Thread.sleep(2000);
			if (!elementVisible(driver, getObject("HotelCorp_SRP_BookRoom_Button"), 20)) {
				safeClick(driver, getObject("HotelCorp_SRP_SelectRoom_Button"));
			}
			elementVisible(driver, getObject("HotelCorp_SRP_Hotel_RateType"), 30);
			safeClick(driver, getObject("HotelCorp_SRP_BookRoom_Button"));			
		}
		else if (Booking_Type.equalsIgnoreCase("DATAVALIDATION")) {
			elementPresent_Time(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 20);
			Thread.sleep(2000);
			safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 
			safeClick(driver, getObject("AgencyHotels_SRP_SelectRoom_Button"));		
			String SRPPrice = getText(driver, By.xpath("//h2[2]/div/strong"));
			SRPPrice = SRPPrice.substring(3);			  
			if(SRPPrice.contains("Rs.")){
				String[] prices = SRPPrice.split("Rs.");
				SRPPrice = prices[1];
			}
			//System.out.println("SRPPrice : "+SRPPrice);
			//SRPPrice = SRPPrice.replace("Rs.", "");
			CorpSRPData.put("Name", getText(driver, By.xpath("//li/h2/a")));
			CorpSRPData.put("Price", SRPPrice);
			CorpSRPData.put("RoomType", getText(driver, By.xpath("//td/a")));
			safeClick(driver, getObject("AgencyHotels_SRP_BookRoom_Button"));		
		
			}
		return CorpSRPData;
	}

	public void corpHotel_SRP_RateType(RemoteWebDriver driver, String Hotel_Name, int Room_Type) throws Exception {
		logMessagePageNotLoaded(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 60, "Search Results Page has not loaded  :( :( :( :( :( :(");
		elementVisible(driver, getObject("HotelCorp_SRP_HotelName_TextBox"), 20);
		logURL(driver);
					
				//------------------------------------------------------ Click Price Fiter ----------------------------------------------------------//
				
				elementVisible(driver, getObject("HotelCom_SRP_Price_Filter"), 10);
				safeClick(driver, getObject("HotelCom_SRP_Price_Filter")); 	
				//------------------------------------------- Select the RoomType --------------------------------------------------------//
loop:		for(int i=1; i<5; i++) {
			String SelectRoom_XPath = "//li["+i+"]/section/div[5]/div/button";			
			safeClick(driver, By.xpath(SelectRoom_XPath));
			String BookButton_XPath = "//li["+i+"]/div/div[2]/table/tbody/tr["+Room_Type+"]/td[6]/input";
			Thread.sleep(2000);
			elementNotVisible(driver, By.cssSelector("span.loader.dotDotDot"), 2);
			if(elementPresent_Time(driver, By.xpath(BookButton_XPath), 2)) {
				safeClick(driver, By.xpath(BookButton_XPath));
				break loop;
			}
		}
		
	}
	
	
	
	public void corpHotel_ItineraryPage(RemoteWebDriver driver) throws Exception {
		for (int i = 0; i < 5; i++) {
			if(elementVisible(driver, getObject("HotelCorp_ReviewPage_Book_Button"), 5)){
				break;
			}
			else if(textPresent(driver, "Hotel no longer available", 1)){
				Reporter.log("Hotel no longer available : Message is displayed in Itinerary page");
				Assert.assertTrue(false);
			}	
			else if(textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 1)){
				Reporter.log("Oops! Cleartrip’s system is behaving badly : Message is displayed in Itinerary page");
				Assert.assertTrue(false);
		}	
		
	}
	/*	if(elementPresent_Time(driver, getObject("AgencyHotels_Itinerarypage_Hotel_Details"), 1)) {
			String HotelDetails = getText(driver, getObject("AgencyHotels_Itinerarypage_Hotel_Details"));
			String HotelName = getText(driver, getObject("AgencyHotels_Itinerarypage_Hotel_Name"));
			Reporter.log("Hotel Name : ");
			Reporter.log(HotelName);
			Reporter.log("Hotel Details : ");			
			Reporter.log(HotelDetails);			
		}*/
		if(elementVisible(driver, By.id("booking_violation_reasons"), 2)){
			smartSelect(driver, By.id("booking_violation_reasons"), "Other");
		}
		safeClick(driver, getObject("HotelCorp_ReviewPage_Book_Button"));
	}
	
	public void corpHotel_ItineraryPagePAH(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObject("HotelCorp_ReviewPage_Book_Button"), 60);
		Thread.sleep(2000);
		if(elementVisible(driver, getObject("HotelCorp_ReviewPage_PAH"), 10)) {
		safeClick(driver, getObject("HotelCorp_ReviewPage_PAH"));
		} else {
			Reporter.log("PAH button is not displayed in Itinerary page");
			Assert.assertTrue(false);
		}
		Thread.sleep(2000);
		safeClick(driver, getObject("HotelCorp_ReviewPage_Book_Button"));
	}

	public void corpHotel_TravelerPage(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObject("HotelCorp_TravellerPage_Button"), 10);
		safeAutocomplete(driver, getObject("HotelCorp_TravellerPage_Name"), getObject("HotelCorp_TravellerPage_NameAjax"),"test booking");
		safeType(driver, getObject("HotelCorp_TravellerPage_EmailID"), "automation@cleartrip.com");
		safeType(driver, getObject("HotelCorp_TravellerPage_PhoneNumber"), "1212121212");
		safeType(driver, getObject("HotelCorp_TravellerPage_SplRequest"), dataFile.value("Hotel_Special_Request"));
		safeClick(driver, getObject("HotelCorp_TravellerPage_Button"));
	}
	
	public void corpHotel_TravelerPage_GST(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObject("HotelCorp_TravellerPage_Button"), 10);
		safeAutocomplete(driver, getObject("HotelCorp_TravellerPage_Name"), getObject("HotelCorp_TravellerPage_NameAjax"),"test booking");
		elementPresent_log(driver, getObject("HotelCorp_TravellerPage_GSTNumber"), "GST DropDown", 10);
		safeSelectByIndex(driver, getObject("HotelCorp_TravellerPage_GSTNumber"), 1);
		
		safeType(driver, getObject("HotelCorp_TravellerPage_EmailID"), "automation@cleartrip.com");
		safeType(driver, getObject("HotelCorp_TravellerPage_PhoneNumber"), "1212121212");
		safeType(driver, getObject("HotelCorp_TravellerPage_SplRequest"), dataFile.value("Hotel_Special_Request"));
		safeClick(driver, getObject("HotelCorp_TravellerPage_Button"));
	}
	
	public void corpHotel_TravelerPage_GSTState(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObject("HotelCorp_TravellerPage_Button"), 10);
		safeAutocomplete(driver, getObject("HotelCorp_TravellerPage_Name"), getObject("HotelCorp_TravellerPage_NameAjax"),"test booking");
		elementPresent_log(driver, getObject("HotelCorp_TravellerPage_GSTState"), "GST DropDown", 10);
		safeSelect(driver, getObject("HotelCorp_TravellerPage_GSTState"), "Karnataka");
		safeType(driver, getObject("HotelCorp_TravellerPage_EmailID"), "automation@cleartrip.com");
		safeType(driver, getObject("HotelCorp_TravellerPage_PhoneNumber"), "1212121212");
		safeType(driver, getObject("HotelCorp_TravellerPage_SplRequest"), dataFile.value("Hotel_Special_Request"));
		safeClick(driver, getObject("HotelCorp_TravellerPage_Button"));
	}

	public String corpHotel_PaymentPage(RemoteWebDriver driver, String PaymentType, String Coupon, String Trip_Logger_Msg,
			String Booking_Confirmation_Message) throws Exception {
		String TripID =null;
		elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), 50);
		if(elementVisible(driver, By.id("processingFeeAmount"), 5)){
			HotelProcessingFee = getText(driver, By.id("processingFeeAmount"));
			HotelProcessingFee = HotelProcessingFee.replace("Rs. ", "");
			//System.out.println("HotelProcessingFee : "+HotelProcessingFee);
		}
		if (Coupon.equalsIgnoreCase("COUPON")) {
			//safeType(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2B_Coupon"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2B_Coupon"));
			safeClick(driver, getObject("AgencyHotels_PaymentPage_Coupon_Button"));
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 60);
			Thread.sleep(5000);
			textAssert(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), "Great, you are eligible to a cashback");
		}
		if (Coupon.equalsIgnoreCase("COUPONAE")) {
			//safeType(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2B_Coupon"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelB2B_CouponAE"));
			safeClick(driver, getObject("AgencyHotels_PaymentPage_Coupon_Button"));
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 60);
			Thread.sleep(5000);
			textAssert(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), "Great, you are eligible to a cashback");
		}
		if (PaymentType.equalsIgnoreCase("DEPOSITACCOUNT")) {
			safeClick(driver, getObject("AgencyHotels_PaymentPage_DepositAccount_Tab"));
		}
		
		else if (PaymentType.equalsIgnoreCase("CREDITCARD")) {
			safeClick(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Number"), dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Exp_Month"), "5");
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Name"), dataFile.value("MasterCard_HolderName"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_FirstName"), "test");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_LastName"), "test");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_Address"), "JP Nagar");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_City"), "JP Nagar");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_State"), "Karnataka");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_Pin"), "560076");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_Country"), "India");
			Thread.sleep(2000);
		} 
		
		else if (PaymentType.equalsIgnoreCase("DEBITCARD")) {
			safeClick(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Tab"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Number"), dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Exp_Month"), "5");
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_BillName"), dataFile.value("MasterCard_HolderName"));
			Thread.sleep(2000);
		} 
		

		else if(PaymentType.equalsIgnoreCase("NETBANKING")){
			safeClick(driver, getObject("AgencyHotels_PaymentPage_NetBanking_Tab"));
			//safeSelect(driver, getObject("AgencyHotels_PaymentPage_NetBank_Dropdown"), "State Bank of India");
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Bank of India");			
			Thread.sleep(2000);
		}
		
		else if(PaymentType.equalsIgnoreCase("NETBANKINGPROD")){
			

			safeClick(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"));
			safeSelect(driver, getObject("AirCorp_PaymentPage_NetBank_Dropdown"), "State Bank of India");
			Thread.sleep(2000);
			safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));
			
			if(!elementPresent_Time(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"), 5)){
				refreshPage(driver);
			}
			for(int i=0; i<=60;i++) {
				if(elementPresent_Time(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"), 1)) {
					break;
				}
			if(textPresent(driver, "Oops, your payment didn’t work", 5)) {
				Reporter.log("Oops, your payment didn’t work ");
				Assert.assertTrue(false);
				}
			}
			if(elementPresent_Time(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"), 1)){
			safeClick(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"));
			} else {
				Reporter.log("Page has not redirected to Netbanking Site");
				AssertJUnit.assertEquals(true, false);
			}
		
			if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Sorry, our system is acting up. message is displayed");
				Assert.assertTrue(false);
			}
			if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
				Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
				AssertJUnit.assertEquals(true, false);
			}
			else Reporter.log("Page has redirected to back to Cleartrip from Netbanking Site");
			
			
			
			
	/*		safeClick(driver, getObject("AgencyHotels_PaymentPage_NetBanking_Tab"));
			//safeSelect(driver, getObject("AgencyHotels_PaymentPage_NetBank_Dropdown"), "State Bank of India");
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Bank of India");
			Thread.sleep(2000);	
			safeClick(driver, getObject("AgencyHotels_PaymentPage_BookButton"));
			if(elementPresent_Time(driver, getObject("HotelCom_BookStep4_SBI_Bank_CancelLink"), 100)){
			Thread.sleep(2000);
			elementPresent(driver, getObject("HotelCom_BookStep4_SBI_Bank_CancelLink"));			
			Reporter.log("Netbanking Site is displayed");
			}
			if(elementPresent_Time(driver, By.linkText("Return to Biller Site"), 100)){
				Thread.sleep(2000);
				elementPresent(driver, By.linkText("Return to Biller Site"));				
				Reporter.log("Netbanking Site is displayed");
				} 
			else {
				Reporter.log("Page has not redirected to Netbanking Site");
				Assert.assertEquals(true, false);
			}
			safeClick(driver, By.linkText("Return to Biller Site"));
	*/
		}		
		
		else if(PaymentType.equalsIgnoreCase("NBBOI")){

			
			waitElement(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"), 10);
			safeClick(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"));

			safeSelect(driver, getObject("AirCorp_PaymentPage_NetBank_Dropdown"), "Bank of India");
			safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));
		
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			if(elementVisible(driver, getObject("HotelCom_BookStep4_BOI_Bank_CancelLink"), 50)){
				Thread.sleep(2000);			
				Reporter.log("NB site page is displayed");
				elementPresent(driver, getObject("HotelCom_BookStep4_BOI_Bank_CancelLink"));
				safeClick(driver, getObject("HotelCom_BookStep4_BOI_Bank_CancelLink"));
				}
			else {
					Reporter.log("Netbanking Site is not displayed");
					Assert.assertTrue(false);
					}
			if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Sorry, our system is acting up. message is displayed");
				Assert.assertTrue(false);
			}
			if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 20)){
				Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
				Assert.assertTrue(false);
			}
			else Reporter.log("Page has redirected to back to Cleartrip from Netbanking Site");
		}
		
		//------------------------------------------------------------- MAke Payment in QA2 Only-------------------------------------------------//
		
		if (MakePaymentOnlyInQA2) {
			if(!(PaymentType.equalsIgnoreCase("NETBANKINGPROD")||PaymentType.equalsIgnoreCase("NETBANKING")||(PaymentType.equalsIgnoreCase("NBBOI")))) {
				Reporter.log("Payment Button is Clicked");
			safeClick(driver, getObject("HotelCorp_PaymentPage_Payment_Button"));
			for(int i=0; i<=30; i++) {
				if(elementVisible(driver, getObject("HotelCorp_ConfirmationPage_TripID"), 5)){
					break;
				}
				if(elementVisible(driver, By.cssSelector("h1.Failure"), 1)){
					Reporter.log("Oops! Cleartrip’s system is behaving badly");
					Assert.assertTrue(false);
				}
				if(textPresent(driver, "Oops, your booking didn’t go through", 1)){
					Reporter.log("Oops, your booking didn’t go through");
					Assert.assertTrue(false);
				}
				
				
			}
			TripID = getText(driver, getObject("HotelCorp_ConfirmationPage_TripID"));
			//String SuccessMessage = getText(driver, getObject("HotelCorp_ConfirmationPage_ConfirmationText"));
			Reporter.log(Trip_Logger_Msg + TripID);
			logger.info(Trip_Logger_Msg + TripID);
		//	Assert.assertEquals(Booking_Confirmation_Message, SuccessMessage);

			
			/*
			safeClick(driver, getObject("HotelCorp_ConfirmationPage_SignOut_Link"));
			elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 10);*/
		}
			if(PaymentType.equalsIgnoreCase("NETBANKING")) {
				Reporter.log("Payment Button is Clicked");
				safeClick(driver, getObject("AgencyHotels_PaymentPage_BookButton"));
			/*	if(elementPresent_Time(driver, getObject("HotelCom_BookStep4_SBI_Bank_CancelLink"), 100)){
				Thread.sleep(2000);
				safeClick(driver, getObject("HotelCom_BookStep4_SBI_Bank_CancelLink"));
				} else {
					Reporter.log("Page has not redirected to Netbanking Site");
					Assert.assertEquals(true, false);
				}*/
				if(elementPresent_Time(driver, By.linkText("Return to Biller Site"), 100)){
					Thread.sleep(2000);
					elementPresent(driver, By.linkText("Return to Biller Site"));				
					Reporter.log("Netbanking Site is displayed");
					} 
				else {
					Reporter.log("Page has not redirected to Netbanking Site");
					AssertJUnit.assertEquals(true, false);
				}
				safeClick(driver, By.linkText("Return to Biller Site"));
			//	safeClick(driver, getObject("HotelCom_BookStep4_SBI_Bank_CancelLink"));
				if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
					Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
					AssertJUnit.assertEquals(true, false);
				}
			
			}
			
		}
		return TripID;
	}

	public String corpHotel_PaymentPage_NoSignOut(RemoteWebDriver driver, String PaymentType, String Coupon, String Trip_Logger_Msg,
			String Booking_Confirmation_Message) throws Exception {
		String TripID = null;
		if (Coupon.equalsIgnoreCase("COUPON")) {
			safeType(driver, getObject("AgencyHotels_PaymentPage_Coupon_TextBox"), dataFile.value("HotelCom_Coupon"));
			safeClick(driver, getObject("AgencyHotels_PaymentPage_Coupon_Button"));
			elementVisible(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 60);
			textAssert(driver, getObject("AgencyHotels_PaymentPage_Coupon_ELigibleText"), "Great, you are eligible to a cashback");
		}
		if (PaymentType.equalsIgnoreCase("DEPOSITACCOUNT")) {
			safeClick(driver, getObject("AgencyHotels_PaymentPage_DepositAccount_Tab"));
		} else if (PaymentType.equalsIgnoreCase("CREDITCARD")) {
			safeClick(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Number"), dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Exp_Month"), "5");
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Name"), dataFile.value("MasterCard_HolderName"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_FirstName"), "test");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_LastName"), "test");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_Address"), "JP Nagar");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_City"), "JP Nagar");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_State"), "Karnataka");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_Pin"), "560076");
			safeType(driver, getObject("AgencyHotels_PaymentPage_Bill_Country"), "India");
			Thread.sleep(2000);
		} else if (PaymentType.equalsIgnoreCase("DEBITCARD")) {
			safeClick(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Tab"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Number"), dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Exp_Month"), "5");
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_DebitCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("AgencyHotels_PaymentPage_DebitCard_BillName"), dataFile.value("MasterCard_HolderName"));
			Thread.sleep(2000);
		}
		
		
		if (MakePaymentOnlyInQA2) {
			safeClick(driver, getObject("HotelCorp_PaymentPage_Payment_Button"));
			elementVisible(driver, getObject("HotelCorp_ConfirmationPage_TripID"), 90);
			TripID = getText(driver, getObject("HotelCorp_ConfirmationPage_TripID"));
			String SuccessMessage = getText(driver, getObject("HotelCorp_ConfirmationPage_ConfirmationText"));
			Reporter.log(Trip_Logger_Msg + TripID);
			logger.info(Trip_Logger_Msg + TripID);
		//	Assert.assertEquals(Booking_Confirmation_Message, SuccessMessage);
		}
		return TripID;
	}
	
	public void corpHotel_Account_Cancellation(RemoteWebDriver driver, String TripID, Map<String, String> corpSRPData) throws Exception {
		if (MakePaymentOnlyInQA2){
			safeClick(driver, By.linkText("Trips"));
		  	elementPresent_Time(driver, By.xpath("//h1"), 30);				
			elementPresent_Time(driver,getObject("AirCorp_TripsPage_SearchTrips"), 5);			
			safeType(driver, getObject("AirCorp_TripsPage_SearchTrips"), TripID);
			safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
			Thread.sleep(3000);
			if(elementVisible(driver, By.id("listView_a"), 2)){
				smartClick(driver, By.id("listView_a"));			
				smartClick(driver, By.xpath("//p/a"));				
			}
			elementPresent_Time(driver, By.linkText("Cancellations"), 50);
			for(int i=0; i<=10;i++){
				if(elementVisible(driver, By.linkText("Cancellations"), 2)) {
				Reporter.log("Cancel Link is displayed");
				break;
			}
				else if(textPresent(driver, "We will send you the e-ticket shortly", 2)) {
				Reporter.log("We will send you the e-ticket shortly : message is displayed");
				refreshPage(driver);
			}
			}

			
		/*	 elementVisible(driver, By.linkText("Pricing & payment details"), 20);
			  safeClick(driver, By.linkText("Pricing & payment details"));
			  if(!elementVisible(driver, By.cssSelector("dd.total"), 10)){
				  safeClick(driver, By.linkText("Pricing & payment details"));
			  }*/
			  elementVisible(driver, By.xpath("//h1"), 20);
			  Map<String, String> corpAccTripData = new HashMap<String, String >();
			  corpAccTripData.put("Name", getText(driver, By.xpath("//h1")));
			  corpAccTripData.put("RoomType", getText(driver, By.xpath("//td[5]")));
			  corpAccTripData.put("Price", getText(driver, By.cssSelector("div.total > dd")));
			  
			  String SRPPrice = corpSRPData.get("Price");
			  int SRPPriceInt = Integer.parseInt(SRPPrice);
			  String AcctPrice =  corpAccTripData.get("Price");
			  // String AcctPrice = getText(driver, By.cssSelector("div.total > dd"));
			  AcctPrice = AcctPrice.replace("Rs. ", "");
			  int AcctPriceInt = Integer.parseInt(AcctPrice);
			  int TotalPrice;
				 if(HotelProcessingFee !=null){
					  int HotelProcessingFeeInt = Integer.parseInt(HotelProcessingFee);
					  TotalPrice = SRPPriceInt+HotelProcessingFeeInt;
				 } else TotalPrice = SRPPriceInt;
				
			  if(!corpAccTripData.get("Name").contains(corpSRPData.get("Name"))){
				  Reporter.log("Hotel Name doesnt Match 'SRP Name ' : "+corpSRPData.get("Name")+" & 'Accnt Trip Page Name ' : "+corpAccTripData.get("Name")  );
				 //System.out.println("Hotel Name doesnt Match 'SRP Name ' : "+corpSRPData.get("Name")+" & 'Accnt Trip Page Name ' : "+corpAccTripData.get("Name")  );
					  Assert.assertTrue(false);
			  }
			  if(!corpSRPData.get("RoomType").equals(corpAccTripData.get("RoomType"))){
				  Reporter.log("Hotel Name doesnt Match 'SRP RoomType ' : "+corpSRPData.get("RoomType")+" & 'Accnt Trip Page RoomType ' : "+corpAccTripData.get("RoomType")  );
			//	  System.out.println("Hotel Name doesnt Match 'SRP RoomType ' : "+corpSRPData.get("RoomType")+" & 'Accnt Trip Page RoomType ' : "+corpAccTripData.get("RoomType")  );
				    Assert.assertTrue(false);
			  }
			  if(!(TotalPrice==AcctPriceInt)){
				  Reporter.log("Hotel Name doesnt Match 'SRP Price + Processing fee ' : "+TotalPrice+" & 'Accnt Trip Page Price ' : "+AcctPriceInt);
				 // System.out.println("Hotel Name doesnt Match 'SRP Price + Processing fee ' : "+TotalPrice+" & 'Accnt Trip Page Price ' : "+AcctPriceInt);
				  Assert.assertTrue(false);
			  }
			
			safeClick(driver, By.linkText("Cancellations"));
			/*elementPresent_Time(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]"), 10);
			assertTrue("Trip Cancellation Page Has Not Displayed", elementPresent(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]")));
			safeClick(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]"));
			safeClick(driver, By.xpath("//*[@id='cancel']"));
		*/	
			elementVisible(driver, By.xpath("//input[4]"), 10);
			if(!textPresent(driver, "Cancel your stay at", 10)){
				Reporter.log("Cancel your stay at : message is not displayed after cancel 1 buton is clicked");
				Assert.assertTrue(false);
			}
			safeClick(driver, By.xpath("//input[4]" ));
			elementVisible(driver, By.id("Flash"), 20);
			if(!textPresent(driver, "We have successfully cancelled this trip", 30)){
				Reporter.log("We have successfully cancelled this trip : message is not displayed");
				Assert.assertTrue(false);
			}
			
		}

	}
	
	public void corpHotel_HQ_Cancellation(RemoteWebDriver driver, String TripID, Map<String, String> corpSRPData) throws Exception {
		if (MakePaymentOnlyInQA2){
			driver.manage().deleteAllCookies();
			hotelCom_Open_TripID_HQ(driver, TripID);
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
			safeClick(driver, getObject("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			if(!elementVisible(driver, getObject("HotelCom_HQ_Trips_Cancel_Link"), 60)) {
				refreshPage(driver);
			}
			Map<String, String> corpHQTripData = new HashMap<String, String>();
			corpHQTripData.put("Name", getText(driver, By.xpath("//h1")));
			corpHQTripData.put("RoomType", getText(driver, By.xpath("//tr[3]/td")));
			corpHQTripData.put("Price", getText(driver, By.cssSelector("dd.total")));

			  String SRPPrice = corpSRPData.get("Price");
			  SRPPrice = SRPPrice.replaceAll("\\D+","");
			  int SRPPriceInt = Integer.parseInt(SRPPrice);
			  String AcctPrice = getText(driver, By.cssSelector("dd.total"));
			  AcctPrice = AcctPrice.replace("Rs. ", "");
			  int AcctPriceInt = Integer.parseInt(AcctPrice);
			  int HotelProcessingFeeInt = Integer.parseInt(HotelProcessingFee);
			  int TotalPrice = SRPPriceInt+HotelProcessingFeeInt;
			  if(!(TotalPrice==AcctPriceInt)){
				  Reporter.log("Hotel Price doesnt Match 'SRP Price + Processing fee ' : "+TotalPrice+" & 'Accnt Trip Page Price ' : "+AcctPriceInt);
				 // Assert.assertTrue(false);
			  }			
		  if(!corpHQTripData.get("Name").contains(corpSRPData.get("Name"))){
			  Reporter.log("Hotel Name doesn't Match 'SRP Name ' : "+corpSRPData.get("Name")+" & 'HQ Trip Page Name ' : "+corpHQTripData.get("Name")  );
			  //System.out.println("Hotel Name doesn't Match 'SRP Name ' : "+corpSRPData.get("Name")+" & 'HQ Trip Page Name ' : "+corpHQTripData.get("Name")  );
			  Assert.assertTrue(false);
		  }
		  if(!corpSRPData.get("RoomType").equals(corpHQTripData.get("RoomType"))){
			  Reporter.log("Hotel Name doesn't Match 'SRP RoomType ' : "+corpSRPData.get("RoomType")+" & 'HQ Trip Page RoomType ' : "+corpHQTripData.get("RoomType")  );
			  Assert.assertTrue(false);
		  }		
			
			safeClick(driver, getObject("HotelCom_HQ_Trips_Cancel_Link"));
			safeType(driver, getObject("HotelCom_HQ_Trips_Cancel_AddNotes"), "Test Booking");
			safeClick(driver, getObject("HotelCom_HQ_Trips_Cancel_Button"));
		    elementNotPresent_Time(driver, getObject("HotelCom_HQ_Trips_Cancel_AddNotes"), 20);
			elementVisible(driver, getObject("HotelCom_HQ_Trips_Status"), 60);
			String Cancel_Status = getText(driver, getObject("HotelCom_HQ_Trips_Status"));
			if(!Cancel_Status.contains("Cancelled")){
				Reporter.log("Trip Status after cancellation is : "+Cancel_Status);
				AssertJUnit.assertEquals(true, false);
				}	
		}
	}
	
	public void hotelCom_Open_TripID_HQ(RemoteWebDriver driver, String TripID) throws Exception {
	String URL = logURL(driver);
		if(URL.contains("com")){
			driver.get("http://"+common.value("host")+common.value("url")+"com"+"/hq/trips/"+TripID);
		}
		else if(URL.contains("ae")){
			driver.get("http://"+common.value("host")+common.value("url")+"ae"+"/hq/trips/"+TripID);
		}
	}
	
	public void corpHotel_ConfirmationPage(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObject("HotelCorp_ConfirmationPage_TripID"), 900);
		String TripID = getText(driver, getObject("HotelCorp_ConfirmationPage_TripID"));
		Thread.sleep(2000);
		Reporter.log("Trip ID : " + TripID);
	}
	
	public boolean filterFlightsByLCCOrGDS(RemoteWebDriver driver, String flight_type, int sectorNo) throws Exception {
		boolean lccFlight = false;
		List<WebElement> flights = null;
		if (waitElement(driver, getObject("AirCom_SRP_Flights_List_Prefix"), 5)) {
			flights = driver.findElements(getObject("AirCom_SRP_Flights_List_Prefix"));
		} else if (waitElement(driver,	By.xpath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix")), 5)) {
			flights = driver.findElementsByXPath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)	+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix"));
		} else {
			Reporter.log("Not able to locate list of available flight names.");
			AssertJUnit.assertTrue("Failure!", false);
		}
		// SearchResult.count = 0;
		// remove first element, i.e. "show all airlines"
		flights.remove(0);
		for (WebElement flight : flights) {
			String flight_name = flight.findElement(getObject("AirCom_SRP_Flights_List_Suffix")).getText();
			// if(flight_name.equalsIgnoreCase("tiger airways") || flight_name.equalsIgnoreCase("spicejet")) {
			// flight.findElement(By.xpath(param.get("flight_checkbox"))).click();
			// continue;
			// }
			lccFlight = isLCCFlight(driver, flight_name);
			if ((!lccFlight && flight_type.equalsIgnoreCase("lcc")) || (lccFlight && flight_type.equalsIgnoreCase("gds")))
				flight.findElement(getObject("AirCom_SRP_Flights_Checkbox")).click();
			if (flight_name.equalsIgnoreCase("Jet Airways") && flight_type.equalsIgnoreCase("gds"))
				flight.findElement(getObject("AirCom_SRP_Flights_Checkbox")).click();
			if (flight_name.equalsIgnoreCase("JetKonnect") && flight_type.equalsIgnoreCase("gds"))
				flight.findElement(getObject("AirCom_SRP_Flights_Checkbox")).click();
		}
		// removeAds(driver);
		if (checkWarning(driver))
			return true;
		else
			return false;
	}

	
	public boolean filterFlightsByLCCOrGDS1(RemoteWebDriver driver, String flight_type, int sectorNo) throws Exception 
	{
		//Reporter.log("flight type required = " + flight_type, true);

		boolean lccFlight = false;
		List<WebElement> flights = null;


		if (waitElement(driver,By.xpath("//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"), 1)) 
		{
			flights = driver.findElements(By.xpath("//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"));
		}
		else 
		{
			Reporter.log("Not able to locate list of available flight names.", true);
			assertTrue("Failure!", false);
		}

		for(int i=0;i<flights.size();i++)
		{
			boolean numberpresent=false;
			//Reporter.log(flights.get(i).getText(), true);
			if(flights.get(i).getText().equalsIgnoreCase("show multi-airline itineraries"))
			{
				Reporter.log("Clicking on Multi Airline itineraries", true);
				flights.get(i).click();
			}
			else
			{
				//Reporter.log("Checking if it is LCC",true);
				lccFlight = isLCCFlight(driver, flights.get(i).getText());
				
				test:for(char c : flights.get(i).getText().toCharArray())
				{
					if(Character.isDigit(c))
					{
						numberpresent=true;
						break test;
					}
				}
			}
			
		//	Reporter.log(lccFlight + " " + flight_type + " " + numberpresent, true);
			if (!(lccFlight && flight_type.equalsIgnoreCase("lcc") && (!numberpresent)) && !(!lccFlight && flight_type.equalsIgnoreCase("gds") && (!numberpresent) ))
			{
				try
				{
					int j = i+1;
					//Reporter.log("i="+i+" j="+j,true);
					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("window.scrollBy(0,600)", "");
					Thread.sleep(1000);
					/*Actions a = new Actions(driver);
					WebElement we1 = driver.findElement(By.xpath("(//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["+j+"]"));
					a.moveToElement(we1).moveToElement(driver.findElement(By.xpath("(//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["+j+"]/../a"))).click(driver.findElement(By.xpath("(//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["+j+"]/../a"))).perform();*/
					//break;
					safeClick(driver, By.xpath("(//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["+j+"]/../input"));
				}
				catch(Exception e)
				{
					Reporter.log("Element not found / clickable. Continuing to next element", true);
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
	
	public boolean filterFlightsByLCCOrGDS_OLD(RemoteWebDriver driver, String flight_type, int sectorNo) throws Exception {
		boolean lccFlight = false;
		List<WebElement> flights = null;
		if (waitElement(driver, getObject("AirCom_SRP_Flights_List_Prefix"), 5)) {
			flights = driver.findElements(By.xpath("//div[6]/div/nav/ul"));
		}  else {
			Reporter.log("Not able to locate list of available flight names.");
			AssertJUnit.assertTrue("Failure!", false);
		}
		// SearchResult.count = 0;
		// remove first element, i.e. "show all airlines"
		flights.remove(0);
		for (WebElement flight : flights) {
			String flight_name = flight.findElement(getObject("AirCom_SRP_Flights_List_Suffix")).getText();
			// if(flight_name.equalsIgnoreCase("tiger airways") || flight_name.equalsIgnoreCase("spicejet")) {
			// flight.findElement(By.xpath(param.get("flight_checkbox"))).click();
			// continue;
			// }
			lccFlight = isLCCFlight(driver, flight_name);
			if ((!lccFlight && flight_type.equalsIgnoreCase("lcc")) || (lccFlight && flight_type.equalsIgnoreCase("gds")))
				flight.findElement(getObject("AirCom_SRP_Flights_Checkbox")).click();
			if (flight_name.equalsIgnoreCase("Jet Airways") && flight_type.equalsIgnoreCase("gds"))
				flight.findElement(getObject("AirCom_SRP_Flights_Checkbox")).click();
			if (flight_name.equalsIgnoreCase("JetKonnect") && flight_type.equalsIgnoreCase("gds"))
				flight.findElement(getObject("AirCom_SRP_Flights_Checkbox")).click();
		}
		// removeAds(driver);
		if (checkWarning(driver))
			return true;
		else
			return false;
	}

	public boolean isLCCFlight(RemoteWebDriver driver, String flight) {
		boolean result = false; 
		for (String flight_name : lcc_airlines) {
			if (flight.equalsIgnoreCase(flight_name))
				result = true;
		}
		return result;
	}
	
	public void  AirCorp_TravellerPage_Int(RemoteWebDriver driver,String Adults, String Childrens, String Infants) throws Exception {
		//travellerDetails(driver, Adults, Childrens, Infants, false);
		AirCorpPax(driver, Adults, Childrens, Infants);
		
		safeClick(driver, getObject("Air_Agency_TravellerPage_Int_ContinueButton"));
		}

	public void airCorp_MC_Search(RemoteWebDriver driver, int numberOfSectors, String[] FromCity, String[] ToCity, String[] dateSet, String Adults, String Childrens, String Infants, String pref_airline) throws Exception {
		if (elementClickable(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), 10)){
			Thread.sleep(5000);
		safeClick(driver, getObject("AirCom_HomePage_MultiCity_RadioButton"));
		}
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
						getObject("AirCom_HomePage_Departure_NextMonth"), 1, dateSet[i]);
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
		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));
	}

	public void clickDom_MCBookButton(RemoteWebDriver driver) {
		try {
			safeClick(driver, getObject("AirCorp_SRP_Dom_MC_book_button"));
			return;
		} catch (Exception e) {
		}
	}
	
	public void airCorp_SRP_Domestic_MC(RemoteWebDriver driver) throws Exception {
		Thread.sleep(10000);
		elementPresent_Time(driver, getObject("AirCorp_SRP_Dom_MC_book_button"), 60);
		refreshPage(driver);
		corpNoResultsFound(driver);
	  elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/aside/div/p/strong"), 20);
	  elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 100);
	  elementPresent_Time(driver, getObject("AirCorp_SRP_Dom_MC_book_button"), 20);
	  //corpAirUncheck_Jetconnect_Air(driver);
	  UnSelectCarrierMC(driver, "9W", "1");
		UnSelectCarrierMC(driver, "S2", "1");
		Thread.sleep(2000);
		safeClick(driver, getObject("AirCorp_SRP_Dom_MC_book_button"));
}

	public void airCorp_SRP_Intl_MC(RemoteWebDriver driver) throws Exception {
		elementPresent_Time(driver, getObject("AirCorp_SRP_Intl_MC_book_button"), 50);	
		corpNoResultsFound(driver);
		  elementNotVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div/p"), 30);
		  elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 50);
		  if(!elementPresent_Time(driver, getObject("AirCorp_SRP_Intl_MC_book_button"), 20)){
				driver.navigate().refresh();
				elementVisible(driver, getObject("B2B_SRP_air_flightcount"), 50);
		  }
		  //corpAirUncheck_Jetconnect_Air(driver);
		  UnSelectCarrierMC(driver, "9W", "1");
			UnSelectCarrierMC(driver, "S2", "1");
			
		safeClick(driver, getObject("AirCorp_SRP_Intl_MC_book_button"));
	}

	
	public void corpAirUncheck_Jetconnect_Air(RemoteWebDriver driver)
			throws Exception {
		if(elementPresent_Time(driver,  By.id("1_1_9W"), 1)){
			UnChecking_Checkbox(driver, By.id("1_1_9W"));
		}
		if(elementPresent_Time(driver,  By.id("1_1_S2"), 1)){
			UnChecking_Checkbox(driver, By.id("1_1_S2"));
		}
	}
	
	/*public void  agencyAir_TravellerPage(RemoteWebDriver driver,String Adults, String Childrens, String Infants) throws Exception {
		//travellerDetails(driver, Adults, Childrens, Infants, false);
		if (elementVisible(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"), 10)) {
		agencyPax(driver, Adults, Childrens, Infants);
		safeClick(driver, getObject("AirCorpCom_TravellerPage_Continue_Button"));
		}else {
			Reporter.log("Continue button on traveller details page not visible.");
			assertTrue("Failure!", false);
		}
		}
	*/
	public void cancelEditUser(RemoteWebDriver driver,String PresentUser) throws Exception {

		safeClick(driver, By.linkText("My profile"));
		elementPresent_Time(driver, By.linkText("Edit person"), 20);
		safeClick(driver, By.linkText("Edit person"));
		
		elementPresent_Time(driver, By.linkText("Cancel"), 20);
		
		safeType(driver, By.id("family-name"), "Agency");
		safeClick(driver, By.linkText("Cancel"));
		elementPresent_Time(driver, By.linkText("Edit person"), 20);
		
	}

	public void editUser(RemoteWebDriver driver,String PresentUser, String Edituser) throws Exception {
		safeClick(driver, By.linkText("Edit person"));
		elementPresent_Time(driver, By.id("add_button"), 20);
		safeType(driver, By.id("family-name"), Edituser);
		safeClick(driver, By.id("add_button"));
		elementPresent_Time(driver, By.xpath("//img[@alt='Add a new traveller']"), 20);
		safeClick(driver, By.linkText("My profile"));
		
		
		if (elementPresent_Time(driver, By.linkText("Edit person"), 20)){
			safeClick(driver, By.linkText("Edit person"));
			
		}
		textPresent(driver, PresentUser, 10);
		
		String fn = driver.findElement(By.id("family-name")).getAttribute("value");
		//String fn =getText(driver, By.id(""));
		AssertJUnit.assertEquals(fn, Edituser);
	}

	public boolean filterFlightsByLCCOrGDS2(RemoteWebDriver driver, String flight_type, int sectorNo) throws Exception {
		int a=0;
		boolean lccFlight = false;
		boolean sector1=false;
		/*if(refreshflag){
		driver.navigate().refresh();
		Thread.sleep(5000);
		}*/
		List<WebElement> flights = null;
		List<WebElement> flights1=null;
		if (waitElement(driver, getObject("AirCom_SRP_Flights_List_Prefix"), 5)) {
			flights = driver.findElements(By.xpath("//label/span[@class='span span18 truncate']"));
			
		} else if (waitElement(
				driver,
				By.xpath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)
						+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix")), 5)) {
			flights = driver.findElements(By.xpath("//label/span[@class='span span18 truncate']"));
			
			
		}
		else if (waitElement(driver,By.xpath("//label[contains(@for,'1_1_')]/span"), 5)) {
			flights = driver.findElements(By.xpath("//label[contains(@for,'1_1_')]/span"));
			
			
		}
		
		else {
			Reporter.log("Not able to locate list of available flight names.");
			AssertJUnit.assertTrue("Failure!", false);
		}
		
		
		for(int i=0;i<flights.size();i++){
			boolean numberpresent=false;
			
			lccFlight = isLCCFlight(driver, flights.get(i).getText());
			Test:for(char c : flights.get(i).getText().toCharArray()){
				////System.out.println("flight text=="+flights.get(i).getText());
		        if(Character.isDigit(c)){
		        	////System.out.println("enters in condition check");
		            numberpresent=true;
		            break Test;
		        }
			}
			if ((!lccFlight && flight_type.equalsIgnoreCase("lcc") && (!numberpresent)) || (lccFlight && flight_type.equalsIgnoreCase("gds") && (!numberpresent) ))
			{
				
				
				  JavascriptExecutor jse = (JavascriptExecutor)driver;
				   jse.executeScript("window.scrollBy(0,200)", "");
							  
				   
				flights.get(i).click();
			}
			if (flights.get(i).getText().equalsIgnoreCase("Jet Airways") && flight_type.equalsIgnoreCase("gds")&& (!numberpresent))
			{
				//dragAndDrop1(driver,flights.get(i),1,1);
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				   jse.executeScript("window.scrollBy(0,250)", "");
				flights.get(i).click();
			}
		}
		//}
	
		refreshflag=false;
		if (checkWarning(driver)){
			return true;
		}
		else{
			
			return false;
		}
	}

	public void UnSelectCarrier(RemoteWebDriver driver, String Carrier) throws Exception {
		// (String Carrier) throws Exception {
		String Locators = "//input[contains(@id,'" + Carrier + "')]";
		if (elementVisible(driver, By.xpath(Locators), 2)) {
			driver.findElementByXPath(Locators).click();
			// safeClick(driver, By.xpath(Locators));
			Thread.sleep(1000);
		} else {
			Reporter.log(Carrier + " not displayed in the SRP");
		}
	}
	
	public void validateXML(String TripID, String StartTag, String EndTag, String TagValue) throws IOException, URISyntaxException{
		if(MakePaymentOnlyInQA2){
			DefaultHttpClient client = new DefaultHttpClient();
			String XML_URL = null;
			if(common.value("host").contains("qa2")){
				XML_URL = "http://172.17.12.187:9080/trips/"+TripID;
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
			System.out.println(sb1);
			try {
			String [ ] getFOP = sb1.split(StartTag);
			System.out.println(getFOP[1]);
			String [ ] FOP = getFOP[1].split(EndTag);
			System.out.println("------------"+FOP[0]);
			if(FOP[0].contains(TagValue)){
				       Reporter.log("meal-status is displayed as " +StartTag+FOP[0]+"/"+EndTag,true);
			        } else {
			        	Reporter.log("meal-status is displayed as " +StartTag+FOP[0]+"/"+EndTag);
				        Assert.assertTrue(false);
			        	}
			}
			catch(Exception e) {
				
			}
			}	
	}
}