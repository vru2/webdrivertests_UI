// Framework - Cleartrip Automation
// Author - Kiran Kumar

package domainServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;

public class CorporateHotels extends IndiaHotels {
	/*
	 * private String baseUrl =
	 * "http://"+common.value("host")+common.value("url")+"com"; private String
	 * baseUrl_AE = "http://"+common.value("host")+common.value("url")+"ae";
	 */
	public String DomFromSectors[] = new String[] { "BLR", "MAA", "DEL", "BOM", "HYD", "BLR", "MAA", "DEL", "BOM" };
	public String DomToSectors[] = new String[] { "MAA", "DEL", "BOM", "HYD", "BLR", "DEL", "BOM", "HYD", "BLR" };

	public String HotelProcessingFee;

	private static String Corp_Url, Corp_AE_Url, Corp_SA_Url = null;

	public String Corp_Url() throws Exception {
		if (common.value("host").contains("qa2")) {
			Corp_Url = dataFile.value("Corp_Hotels_QA2");

		} else if (common.value("host").equalsIgnoreCase("hf")) {
			Corp_Url = dataFile.value("Corp_Hotels_HF");

		} else if (common.value("host").contains("www")) {
			Corp_Url = dataFile.value("Corp_Hotels_Prod");
		} else if (common.value("host").contains("beta")) {
			Corp_Url = dataFile.value("Corp_Beta");
		} else if (common.value("host").contains("stg1")) {
			Corp_Url = dataFile.value("Corp_Stg1");
		}
		Reporter.log(Corp_Url);
		return Corp_Url;
	}

	public String Corp_PTA_Url() throws Exception {
		if (common.value("host").contains("qa2")) {
			Corp_Url = dataFile.value("Corp_Hotels_PTA_QA2");

		} else if (common.value("host").equalsIgnoreCase("hf")) {
			Corp_Url = dataFile.value("Corp_Hotels_PTA_HF");

		}
		Reporter.log(Corp_Url);
		return Corp_Url;
	}

	public String Corp_AE_Url() throws Exception {
		if (common.value("host").equalsIgnoreCase("qa2")) {
			Corp_AE_Url = dataFile.value("CorpAE_Hotels_QA2");

		}
		if (common.value("host").equalsIgnoreCase("hf")) {
			Corp_AE_Url = dataFile.value("CorpAE_Hotels_HF");

		} else if (common.value("host").equalsIgnoreCase("www")) {
			Corp_AE_Url = dataFile.value("CorpAE_Hotels_Prod");
		} else if (common.value("host").equalsIgnoreCase("beta")) {
			Corp_AE_Url = dataFile.value("CorpAE_Hotels_Beta");
		} else if (common.value("host").equalsIgnoreCase("stg1")) {
			Corp_AE_Url = dataFile.value("CorpAE_Hotels_Stg1");
		}
		Reporter.log(Corp_AE_Url);
		return Corp_AE_Url;
	}

	public String Corp_SA_Url() throws Exception {
		if (common.value("host").contains("qa2")) {
			Corp_SA_Url = dataFile.value("CorpSA_Hotels_QA2");

		} else if (common.value("host").equalsIgnoreCase("hf")) {
			Corp_SA_Url = dataFile.value("CorpSA_Hotels_HF");

		} else if (common.value("host").contains("www")) {
			Corp_SA_Url = dataFile.value("CorpSA_Hotels_Prod");
		} else if (common.value("host").contains("beta")) {

		} else if (common.value("host").contains("stg1")) {

		}
		Reporter.log(Corp_SA_Url);
		return Corp_SA_Url;
	}

	public void corp_SignIn(RemoteWebDriver driver) throws Exception {
		if (textPresent(driver, "Sorry, our system is acting up.", 1)) {
			Reporter.log("Sorry, our system is acting up. : message is displayed SignIN page");
			Assert.assertTrue(false);
		} else if (textPresent(driver, "Whatever you're looking for, isn't here", 1)) {
			Reporter.log("Whatever you're looking for, isn't here : message is displayed SignIN page");
			Assert.assertTrue(false);
		}
		if (!checkIfSignedCorp(driver)) {
			elementVisible(driver, getObjectHotels("CorpCom_SignIN_EmailID"), 10);
			if (common.value("host").contains("qa2")) {
				safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Hotels_QA2_Username"));
				safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
						dataFile.value("Corp_Hotels_QA2_Password"));
			} else if (common.value("host").contains("hf")) {
				safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Hotels_HF_Username"));
				safeType(driver, getObjectHotels("CorpCom_SignIN_Password"), dataFile.value("Corp_Hotels_HF_Password"));
			} else if (common.value("host").contains("www")) {
				safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"),
						dataFile.value("Corp_Hotels_Prod_Username1"));
				safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
						dataFile.value("Corp_Hotels_Prod_Password1"));
			} else if (common.value("host").contains("beta")) {
				safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Beta_Username"));
				safeType(driver, getObjectHotels("CorpCom_SignIN_Password"), dataFile.value("Corp_Beta_Password"));
			} else if (common.value("host").contains("stg1")) {
				safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Stg1_Username"));
				safeType(driver, getObjectHotels("CorpCom_SignIN_Password"), dataFile.value("Corp_Stg1_Password"));
			}
			safeClick(driver, getObjectHotels("CorpCom_SignIN_SignIN_Button"));
			// if (elementVisible(driver, By.id("userAccountLink"), 5)) {
		}
		if (textPresent(driver, "Read timed out", 2)
				|| elementVisible(driver, getObjectHotels("CorpCom_SignIN_EmailID"), 1)) {
			if (!checkIfSignedCorp(driver)) {
				elementVisible(driver, getObjectHotels("CorpCom_SignIN_EmailID"), 10);
				if (common.value("host").contains("qa2") || common.value("host").equalsIgnoreCase("hf")) {
					safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"),
							dataFile.value("Corp_Hotels_QA2_Username"));
					safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
							dataFile.value("Corp_Hotels_QA2_Password"));
				} else if (common.value("host").contains("www")) {
					safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"),
							dataFile.value("Corp_Hotels_Prod_Username"));
					safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
							dataFile.value("Corp_Hotels_Prod_Password"));
				} else if (common.value("host").contains("beta")) {
					safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Beta_Username"));
					safeType(driver, getObjectHotels("CorpCom_SignIN_Password"), dataFile.value("Corp_Beta_Password"));
				} else if (common.value("host").contains("stg1")) {
					safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Stg1_Username"));
					safeType(driver, getObjectHotels("CorpCom_SignIN_Password"), dataFile.value("Corp_Stg1_Password"));
				}
				safeClick(driver, getObjectHotels("CorpCom_SignIN_SignIN_Button"));

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

	public String corpHotel_SrpUrl(RemoteWebDriver driver, String City, String State, String Country, String Adults,
			int FromDate, int ToDate) throws Exception {
		driver.get(Corp_Url());
		corp_SignIn(driver);
		String SRP_URL = null;
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Corp_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city=" + City + "&state=" + State + "&country=" + Country
				+ "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + Check_In_Date + "&chk_out=" + Check_Out_Date
				+ "&adults1=2&children1=1&ca1=5&num_rooms=1";
		SRP_URL = domainURL + URL_2;
		logURL(driver);
		driver.get(SRP_URL);
		return SRP_URL;
	}
	
	public String corpHotel_SrpUrl_No_SignIN(RemoteWebDriver driver, String City, String State, String Country) throws Exception {
		String Check_In_Date = putDate1(50);
		String Check_Out_Date = putDate1(52);
		String domainURL = Corp_Url();
		String URL_2 = "/hotels/results?city=" + City + "&state=" + State + "&country=" + Country
				+ "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + Check_In_Date + "&chk_out=" + Check_Out_Date
				+ "&adults1=2&children1=1&ca1=5&num_rooms=1";
		String SRP_URL = domainURL + URL_2;
		logURL(driver);
		driver.get(SRP_URL);
		return SRP_URL;
	}

	public String corpHotel_DetailsPageUrl(RemoteWebDriver driver,  String HotelID, int Date) throws Exception {
		driver.get(Corp_Url());
		corp_SignIn(driver);
		String CheckIn = putDateNoSplChar(Date);
		String CheckOut = putDateNoSplChar(Date+1);
		String domainURL = Corp_Url();
		String detailsPageUrlLink = domainURL+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0";
		return detailsPageUrlLink;				
	}
	
	public String corpHotel_DetailsPageUrl_NoSignIN(RemoteWebDriver driver,  String HotelID, int Date) throws Exception {
		String CheckIn = putDateNoSplChar(Date);
		String CheckOut = putDateNoSplChar(Date+1);
		String domainURL = Corp_Url();
		String detailsPageUrlLink = domainURL+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0";
		return detailsPageUrlLink;				
	}
	
	public void corpHotel_DetailsPage(RemoteWebDriver driver,  String BookingType) throws Exception {

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
				 textAssert(driver, "Free Cancellations until", 10);
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
		
	
	
	public String corpAEHotel_SrpUrl(RemoteWebDriver driver, String City, String State, String Country, String Adults,
			int FromDate, int ToDate) throws Exception {
		driver.get(Corp_AE_Url());
		corp_AE_SignIn(driver);
		String SRP_URL = null;
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Corp_AE_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city=" + City + "&state=" + State + "&country=" + Country
				+ "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + Check_In_Date + "&chk_out=" + Check_Out_Date
				+ "&adults1=" + Adults + "&children1=1&ca1=5&num_rooms=1";
		SRP_URL = domainURL + URL_2;
		logURL(driver);
		driver.get(SRP_URL);
		return SRP_URL;
	}

	public String corpAEHotel_DetailsPageUrl(RemoteWebDriver driver,  String HotelID, int Date) throws Exception {
		driver.get(Corp_AE_Url());
		corp_AE_SignIn(driver);
		String CheckIn = putDateNoSplChar(Date);
		String CheckOut = putDateNoSplChar(Date+1);
		String domainURL = Corp_AE_Url();
		String detailsPageUrlLink = domainURL+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0";
		return detailsPageUrlLink;				
	}
	
	public void corpHotel_DetailsPage_BackButton(RemoteWebDriver driver) throws Exception {
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
	
	public String corpPTA_Hotel_SrpUrl(RemoteWebDriver driver, String BookingType, String City, String State,
			String Country, String Adults, int FromDate, int ToDate) throws Exception {
		driver.get(Corp_PTA_Url());
		corp_SignIn_User(driver, BookingType);
		String SRP_URL = null;
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Corp_PTA_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city=" + City + "&state=" + State + "&country=" + Country
				+ "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + Check_In_Date + "&chk_out=" + Check_Out_Date
				+ "&adults1=2&children1=1&ca1=5&num_rooms=1";
		SRP_URL = domainURL + URL_2;
		logURL(driver);
		driver.get(SRP_URL);
		return SRP_URL;
	}

	public String corpSAHotel_SrpUrl(RemoteWebDriver driver, String City, String State, String Country, String Adults,
			int FromDate, int ToDate) throws Exception {
		driver.get(Corp_SA_Url());
		corp_SignIn_User(driver, "CorpSA");
		String SRP_URL = null;
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		String domainURL = Corp_SA_Url();
		String URL_2 = null;
		URL_2 = "/hotels/results?city=" + City + "&state=" + State + "&country=" + Country
				+ "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + Check_In_Date + "&chk_out=" + Check_Out_Date
				+ "&adults1=2&children1=1&ca1=5&num_rooms=1";
		SRP_URL = domainURL + URL_2;
		logURL(driver);
		driver.get(SRP_URL);
		return SRP_URL;
	}

	public void corp_SignIn_User(RemoteWebDriver driver, String UserType) throws Exception {
		if (textPresent(driver, "Sorry, our system is acting up.", 1)) {
			Reporter.log("Sorry, our system is acting up. : message is displayed");
			Assert.assertTrue(false);
		} else if (textPresent(driver, "Whatever you're looking for, isn't here", 1)) {
			Reporter.log("Whatever you're looking for, isn't here : message is displayed SignIN page");
			Assert.assertTrue(false);
		}
		if (!checkIfSignedCorp(driver)) {
			elementVisible(driver, getObjectHotels("CorpCom_SignIN_EmailID"), 10);
			if (UserType.contains("BookOnly")) {
				if (common.value("host").contains("qa2")) {
					safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"),
							dataFile.value("Corp_QA2_Username_BookOnly"));
					safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
							dataFile.value("Corp_QA2_Password_BookOnly"));
				} else if (common.value("host").contains("hf")) {
					safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"),
							dataFile.value("CorpHF_Hotels_Username_BookOnly"));
					safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
							dataFile.value("CorpHF_Hotels_Password_BookOnly"));
				}
			} else if (UserType.contains("PTABOOK")) {
				safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"),
						dataFile.value("CorpQA2_Hotels_Appr_Username_PTA"));
				safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
						dataFile.value("CorpQA2_Hotels_Appr_Password_PTA"));
			} else if (UserType.contains("PTAAPPROVER")) {
				safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"),
						dataFile.value("CorpQA2_Hotels_Username_PTA"));
				safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
						dataFile.value("CorpQA2_Hotels_Password_PTA"));
			}

			else if (UserType.contains("CorpSA")) {
				if (common.value("host").contains("qa2")) {
					safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"),
							dataFile.value("CorpSA_Hotels_QA2_Username"));
					safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
							dataFile.value("CorpSA_Hotels_QA2_Password"));
				} else if (common.value("host").contains("hf")) {
					safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"),
							dataFile.value("CorpSA_Hotels_HF_Username"));
					safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
							dataFile.value("CorpSA_Hotels_HF_Password"));
				} else if (common.value("host").contains("www")) {
					safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"),
							dataFile.value("CorpSA_Hotels_Prod_Username"));
					safeType(driver, getObjectHotels("CorpCom_SignIN_Password"),
							dataFile.value("CorpSA_Hotels_Prod_Password"));
				}
			}
		} else if (common.value("host").contains("www")) {
		} else if (common.value("host").contains("beta")) {
		} else if (common.value("host").contains("stg1")) {
		}
		safeClick(driver, getObjectHotels("CorpCom_SignIN_SignIN_Button"));
		if (elementVisible(driver, By.xpath("//*[@id='userAccountLink']/ul/li"), 10)) {
			Reporter.log("login successfull");
		} else {
			Reporter.log("login failed");
			AssertJUnit.assertTrue("Failure!", false);
		}
	}

	public boolean checkIfSignedCorp(RemoteWebDriver driver) throws Exception {
		if (waitElement(driver, getObjectHotels("CorpCom_SignIN_EmailID"), 3)) {
			return false;
		} else if (waitElement(driver, getObjectHotels("HotelCom_HomePage_Search_Button"), 3)) {
			return true;
		} else {
			Reporter.log("Corp page not loading.");
			Assert.assertTrue(false);
		}
		return false;
	}

	public void corp_AE_SignIn(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObjectHotels("CorpCom_SignIN_EmailID"), 60);
		if (common.value("host").contains("qa2")) {
			safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("CorpAE_QA2_Username"));
			safeType(driver, getObjectHotels("CorpCom_SignIN_Password"), dataFile.value("CorpAE_QA2_Password"));
		} else if (common.value("host").equalsIgnoreCase("hf")) {
			safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("CorpAE_HF_Username"));
			safeType(driver, getObjectHotels("CorpCom_SignIN_Password"), dataFile.value("CorpAE_HF_Password"));
		} else if (common.value("host").contains("www")) {
			safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("CorpAE_Hotel_Prod_Username"));
			safeType(driver, getObjectHotels("CorpCom_SignIN_Password"), dataFile.value("CorpAE_Hotel_Prod_Password"));
		} else if (common.value("host").contains("beta")) {
			safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("CorpAE_Beta_Username"));
			safeType(driver, getObjectHotels("CorpCom_SignIN_Password"), dataFile.value("CorpAE_Beta_Password"));
		} else if (common.value("host").contains("stg1")) {
			safeType(driver, getObjectHotels("CorpCom_SignIN_EmailID"), dataFile.value("CorpAE_Stg1_Username"));
			safeType(driver, getObjectHotels("CorpCom_SignIN_Password"), dataFile.value("CorpAE_Stg1_Password"));
		}
		safeClick(driver, getObjectHotels("CorpCom_SignIN_SignIN_Button"));
	}

	public void corpAir_Signout(RemoteWebDriver driver) throws Exception {
		driver.findElement(getObjectHotels("AirCorpCom_Signout")).click();
		Reporter.log("Signed out.");
	}

	public void corpHotel_HomePageSearch(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date,
			String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, String Child1, String Child2,
			String ChildAge1, String ChildAge2) throws Exception {
		CheckIn_Date = getDate("dd");
		CheckIn_Date = CheckIn_Date.substring(1);
		CheckIn_Date = "1" + CheckIn_Date;
		int DateInt = Integer.parseInt(CheckIn_Date);
		DateInt = DateInt + 1;
		CheckOut_Date = Integer.toString(DateInt);
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCorp_HomePage_HotelTab"), 50,
				"Corp HomePage not loaded/ Old UI / SignIN Error");
		safeClick(driver, getObjectHotels("HotelCorp_HomePage_HotelTab"));
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), 20);
		safeAutocomplete(driver, getObjectHotels("HotelCorp_HomePage_SearchBox"),
				getObjectHotels("HotelCorp_HomePage_SearchAjax"), City);
		selectCalendarDate(driver, getObject("HotelCorp_HomePage_CheckIn_Cal"),
				getObject("HotelCorp_HomePage_CheckIn_NextMonth"), 2, CheckIn_Date);
		selectCalendarDate(driver, getObject("HotelCorp_HomePage_CheckOut_Cal"),
				getObject("HotelCorp_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
		corpHotel_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value");
		String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
		Reporter.log("Hotels Searched for " + City + "CheckIn Date : " + FromDate + "CheckOut Date : " + TODate);
		safeClick(driver, getObjectHotels("HotelCorp_HomePage_Search_Button"));
		driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");

	}
	
	public void corpHotel_HomePageSearch1(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date,
			String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, String Child1, String Child2,
			String ChildAge1, String ChildAge2) throws Exception {
		CheckIn_Date = getDate("dd");
		CheckIn_Date = CheckIn_Date.substring(1);
		CheckIn_Date = "2" + CheckIn_Date;
		int DateInt = Integer.parseInt(CheckIn_Date);
		DateInt = DateInt + 1;
		CheckOut_Date = Integer.toString(DateInt);
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCorp_HomePage_HotelTab"), 50,
				"Corp HomePage not loaded/ Old UI / SignIN Error");
		safeClick(driver, getObjectHotels("HotelCorp_HomePage_HotelTab"));
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), 20);
		safeAutocomplete(driver, getObjectHotels("HotelCorp_HomePage_SearchBox"),
				getObjectHotels("HotelCorp_HomePage_SearchAjax"), City);
		selectCalendarDate(driver, getObject("HotelCorp_HomePage_CheckIn_Cal"),
				getObject("HotelCorp_HomePage_CheckIn_NextMonth"), 0, CheckIn_Date);
		selectCalendarDate(driver, getObject("HotelCorp_HomePage_CheckOut_Cal"),
				getObject("HotelCorp_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
		corpHotel_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value");
		String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
		Reporter.log("Hotels Searched for " + City + "CheckIn Date : " + FromDate + "CheckOut Date : " + TODate);
		safeClick(driver, getObjectHotels("HotelCorp_HomePage_Search_Button"));
		driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");

	}

	public void corpHotel_HomePageSearchMultiNightRoom(RemoteWebDriver driver, String City, String CheckIn_Date,
			String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4,
			String Child1, String Child2, String ChildAge1, String ChildAge2) throws Exception {
		CheckIn_Date = getDate("dd");
		CheckIn_Date = CheckIn_Date.substring(1);
		CheckIn_Date = "1" + CheckIn_Date;
		int DateInt = Integer.parseInt(CheckIn_Date);
		DateInt = DateInt + 2;
		CheckOut_Date = Integer.toString(DateInt);
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCorp_HomePage_HotelTab"), 50,
				"Corp HomePage not loaded/ Old UI / SignIN Error");
		safeClick(driver, getObjectHotels("HotelCorp_HomePage_HotelTab"));
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), 20);
		safeAutocomplete(driver, getObjectHotels("HotelCorp_HomePage_SearchBox"),
				getObjectHotels("HotelCorp_HomePage_SearchAjax"), City);
		selectCalendarDate(driver, getObjectHotels("HotelCorp_HomePage_CheckIn_Cal"),
				getObjectHotels("HotelCorp_HomePage_CheckIn_NextMonth"), 2, CheckIn_Date);
		selectCalendarDate(driver, getObjectHotels("HotelCorp_HomePage_CheckOut_Cal"),
				getObjectHotels("HotelCorp_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
		corpHotel_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value");
		String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
		Reporter.log("Hotels Searched for " + City + "CheckIn Date : " + FromDate + "CheckOut Date : " + TODate);
		safeClick(driver, getObjectHotels("HotelCorp_HomePage_Search_Button"));
		driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");

	}

	public void corpHotel_PaxEntry(RemoteWebDriver driver, String Rooms, String Adult1, String Adult2, String Adult3,
			String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2) throws Exception {

		safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
		safeSelect(driver, getObjectHotels("HotelCorp_HomePage_Room_DropDown"), Rooms);
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

	public Map<String, String> corpHotel_SRP(RemoteWebDriver driver, String Hotel_Name, String Booking_Type)
			throws Exception {
		Map<String, String> CorpSRPData = new HashMap<String, String>();
		if (textPresent(driver, "Sorry, our system is acting up", 1)) {
			Reporter.log("Sorry, our system is acting up : error is displayed in SRP - Refreshing the page");
			refreshPage(driver);
		}
		for (int i = 0; i <= 20; i++) {			
			if (textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Sorry, our system is acting up : error is displayed in SRP");
				Assert.assertTrue(false);
			} else if (textPresent(driver, "Sorry, we couldn't find any hotels", 1)) {
				Reporter.log("Sorry, we couldn't find any hotels : error is displayed in SRP");
				Assert.assertTrue(false);
			} else if (!elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 5)) {
				if(i>15) {
				Reporter.log("Results are not displayed in SRP");
				}
			} else
				break;
		}
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 10,
				"Search Results Page has not loaded  :( :( :( :( :( :(");
		elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 10);
		/*
		 * if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Rooms_Unavailable"),
		 * 2)) { refreshPage(driver); elementVisible(driver,
		 * getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 30);
		 * if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Rooms_Unavailable"),
		 * 2)) { Reporter.log("Room Unavailable in SRP"); Assert.assertTrue(false); } }
		 */
		logURL(driver);
		elementPresent(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
		safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
		Thread.sleep(2000);
		if (!Hotel_Name.isEmpty()) {
			Reporter.log("Hotel Selected is "+Hotel_Name);
			safeAutocomplete(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"),
					getObjectHotels("HotelCorp_SRP_HotelName_Ajax"), Hotel_Name);
			//elementPresent_log(driver, By.linkText(Hotel_Name), "HotelName doesnt match with the Selected", 5);
		}
		if (Booking_Type.isEmpty()) {
			//safeClick(driver, getObjectHotels("HotelCorp_SRP_SelectRoom_Button"));
			Thread.sleep(2000);
			/*
			 * String SRP_Price = getText(driver,
			 * getObjectHotels("HotelCorp_SRP_Price_Text")); String RateType =
			 * getText(driver, getObjectHotels("HotelCorp_SRP_Hotel_RateType")); String
			 * HotelName = getText(driver, getObjectHotels("HotelCorp_SRP_HotelName"));
			 * Reporter.log("SRP: Booking hotel '"+HotelName+"' with Room Type '"
			 * +RateType+"' with Price "+SRP_Price);
			 */
			if (!common.value("B2BNEWWINDOW").equalsIgnoreCase("TRUE")) {
				if (!elementVisible(driver, getObjectHotels("HotelCorp_SRP_BookRoom_Button"), 20)) {
					safeClick(driver, getObjectHotels("HotelCorp_SRP_SelectRoom_Button"));
				} else {
					/*if (!elementVisible(driver, getObjectHotels("HotelCorp_SRP_BookRoom_Button"), 20)) {
						safeClick(driver, getObjectHotels("HotelCorp_SRP_SelectRoom_Button"));
					}*/
					elementVisible(driver, getObjectHotels("HotelCorp_SRP_Hotel_RateType"), 50);
					safeClick(driver, getObjectHotels("HotelCorp_SRP_BookRoom_Button"));
				}
			}
		} else if (common.value("B2BNEWWINDOW").equalsIgnoreCase("False")) {
			if (Booking_Type.equalsIgnoreCase("SPECIALRATECORP")) {
				elementPresent(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"));
				for (int i = 1; i <= 15; i++) {
					String SpecialRateHotel_XPATH = "//li[" + i + "]/section/div[5]/div/span";
					String SpecialRate_SelectRoom_Xpath = "//li[" + i + "]/section/div[5]/div/button";
					if (elementPresent_Time(driver, By.xpath(SpecialRateHotel_XPATH), 1)) {
						safeClick(driver, By.xpath(SpecialRate_SelectRoom_Xpath));
						for (int j = 1; j <= 3; j++) {
							String SpecialRate_XPATH = "//li[" + i + "]/div/div[2]/table/tbody/tr[" + j
									+ "]/td/small/span";
							if (elementPresent(driver, By.xpath(SpecialRate_XPATH))) {
								String SpecialRate_Book = "//li[" + i + "]/div/div[2]/table/tbody/tr[" + j
										+ "]/td[6]/input";
								safeClick(driver, By.xpath(SpecialRate_Book));
								break;
							}
						}
						break;
					}
				}
			}
		}
		if (common.value("B2BNEWWINDOW").equalsIgnoreCase("True")) {
			if (Booking_Type.equalsIgnoreCase("SPECIALRATECORP")) {
				for (int i = 1; i <= 15; i++) {
					String SpecialRateHotel_XPATH = "//li[" + i + "]/section/div[5]/div/span";
					String SpecialRate_SelectRoom_Xpath = "//li[" + i + "]/section/div[5]/div/button";
					if (elementVisible(driver, By.xpath(SpecialRateHotel_XPATH), 1)) {
						if (getText(driver, By.xpath(SpecialRateHotel_XPATH)).contains("SPECIAL RATE")) {
							safeClick(driver, By.xpath(SpecialRate_SelectRoom_Xpath));
							break;
						}
					}
				}
			}
		}
		if (common.value("B2BNEWWINDOW").equalsIgnoreCase("False")) {
			if (Booking_Type.equalsIgnoreCase("PACKAGERATECORP")) {
				Thread.sleep(5000);
				elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 15);
				for (int i = 1; i <= 15; i++) {
					String PackageRateHotel_XPATH = "//li[" + i + "]/section/div[5]/div/button";
					safeClick(driver, By.xpath(PackageRateHotel_XPATH));
					for (int j = 1; j <= 3; j++) {
						String PackageRoomRate_XPATH = "//li[" + i + "]/div/div[2]/table/tbody/tr[" + j + "]/td/a";
						if (elementPresent_Time(driver, By.xpath(PackageRoomRate_XPATH), 1)) {
							String SpecialRate = getText(driver, By.xpath(PackageRoomRate_XPATH));
							if (SpecialRate.contains("Package")) {
								String PackageRoomRateButton = "//li[" + i + "]/div/div[2]/table/tbody/tr[" + j
										+ "]/td[6]/input";
								safeClick(driver, By.xpath(PackageRoomRateButton));
								i = 16;
								break;
							}
						}
					}
				}
			}
		}
		if (common.value("B2BNEWWINDOW").equalsIgnoreCase("True")) {
			if (Booking_Type.equalsIgnoreCase("PACKAGERATECORP")) {
				for (int i = 1; i <= 15; i++) {
					String SpecialRateHotel_XPATH = "//li[" + i + "]/section/div[5]/div/span";
					String SpecialRate_SelectRoom_Xpath = "//li[" + i + "]/section/div[5]/div/button";
					if (elementPresent_Time(driver, By.xpath(SpecialRateHotel_XPATH), 1)) {
						if (getText(driver, By.xpath(SpecialRateHotel_XPATH)).contains("SPECIAL RATE")) {
							safeClick(driver, By.xpath(SpecialRate_SelectRoom_Xpath));
							break;
						}
					}
				}
			}
		} 
		if (Booking_Type.equalsIgnoreCase("PAH")) {

			safeClick(driver, getObjectHotels("HotelCorp_SRP_PAH_Checkbox"));

//			safeClick(driver, getObjectHotels("HotelCorp_SRP_PAH_Checkbox"));
			Thread.sleep(1000);
			elementPresent_Time(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 10);
			/*
			 * loop :for (int i = 1; i <= 15; i++) { String PackageRateHotel_XPATH =
			 * "//li["+i+"]/section/div[5]/div/button"; safeClick(driver,
			 * By.xpath(PackageRateHotel_XPATH)); for (int j = 1; j <= 3; j++) { String
			 * PackageRoomRate_XPATH = "//li["+i+"]/div/div[2]/table/tbody/tr["+j+"]/td/a";
			 * if(elementPresent_Time(driver, By.xpath(PackageRoomRate_XPATH), 1)){ String
			 * SpecialRate = getText(driver, By.xpath(PackageRoomRate_XPATH));
			 * if(!(SpecialRate.contains("Package")||(SpecialRate.contains("B2B"))||(
			 * SpecialRate.contains("Special")))){ String PackageRoomRateButton =
			 * "//li["+i+"]/div/div[2]/table/tbody/tr["+j+"]/td[6]/input"; safeClick(driver,
			 * By.xpath(PackageRoomRateButton)); break loop; } } } }
			 */
			Thread.sleep(1000);
			
			//safeClick(driver, getObjectHotels("HotelCorp_SRP_SelectRoom_Button"));
			Thread.sleep(2000);
			if (common.value("B2BNEWWINDOW").equalsIgnoreCase("false")) {
			if (!elementVisible(driver, getObjectHotels("HotelCorp_SRP_BookRoom_Button"), 20)) {
				safeClick(driver, getObjectHotels("HotelCorp_SRP_SelectRoom_Button"));
			}
			elementVisible(driver, getObjectHotels("HotelCorp_SRP_Hotel_RateType"), 30);
			safeClick(driver, getObjectHotels("HotelCorp_SRP_BookRoom_Button"));
			}
			else {
				
			}
		} else if (Booking_Type.equalsIgnoreCase("DATAVALIDATION")) {
			elementPresent_Time(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 20);
			Thread.sleep(2000);
			//safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
			safeClick(driver, getObjectHotels("AgencyHotels_SRP_SelectRoom_Button"));
			String SRPPrice = getText(driver, By.xpath("//h2[@id='perRoomPrDisp']/strong"));
			System.out.println(SRPPrice);
			SRPPrice = SRPPrice.substring(3);
			if (SRPPrice.contains("Rs.")) {
				String[] prices = SRPPrice.split("Rs.");
				SRPPrice = prices[1];
			}
			// System.out.println("SRPPrice : "+SRPPrice);
			// SRPPrice = SRPPrice.replace("Rs.", "");
			CorpSRPData.put("Name", getText(driver, By.xpath("//li/h2/a")));
			CorpSRPData.put("Price", SRPPrice);
			//CorpSRPData.put("RoomType", getText(driver, By.xpath("//td/a"))); -- room options removed in corp
			safeClick(driver, getObjectHotels("HotelCorp_SRP_SelectRoom_Button"));
		}
		if (common.value("B2BNEWWINDOW").equalsIgnoreCase("TRUE")) {
			if(!((Booking_Type.equalsIgnoreCase("PACKAGERATECORP"))||(Booking_Type.equalsIgnoreCase("SPECIALRATECORP")))) {
			safeClick(driver, getObjectHotels("HotelCorp_SRP_SelectRoom_Button"));
			}
			hotelCom_SRP_ModalWindow_B2B(driver, Booking_Type);
		
		}
		return CorpSRPData;
	}

	public void corpHotel_Intl(RemoteWebDriver driver, String Hotel_Name, String Booking_Type) throws Exception {

		Map<String, String> CorpSRPData = new HashMap<String, String>();
		for (int i = 0; i <= 20; i++) {
			if (textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Sorry, our system is acting up : error is displayed in SRP");
				Assert.assertTrue(false);
			} else if (textPresent(driver, "Sorry, we couldn't find any hotels", 1)) {
				Reporter.log("Sorry, we couldn't find any hotels : error is displayed in SRP");
				Assert.assertTrue(false);
			} else if (!elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 5)) {
				Reporter.log("Results are not displayed in SRP");
			} else
				break;
		}
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 10,
				"Search Results Page has not loaded  :( :( :( :( :( :(");
		elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 10);

		logURL(driver);
		elementPresent(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
		//safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
		Thread.sleep(2000);
		if (!Hotel_Name.isEmpty()) {
			safeAutocomplete(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"),
			getObjectHotels("HotelCorp_SRP_HotelName_Ajax"), Hotel_Name);
			elementPresent_log(driver, By.linkText(Hotel_Name), "HotelName doesnt match with the Selected", 5);
		}
		if (Booking_Type.isEmpty()) {
			safeClick(driver, getObjectHotels("HotelCorp_SRP_SelectRoom_Button"));
			Thread.sleep(2000);
		}
		/*if(!(common.value("B2BNEWWINDOW").equalsIgnoreCase("TRUE")&&common.value("host").equalsIgnoreCase("www"))) {
		safeClick(driver, getObjectHotels("HotelCorp_SRP_BookRoom_Button"));
		}*/ 
		
		hotelCom_SRP_ModalWindow_B2B(driver, Booking_Type);
		
	}

	public void corpHotel_SRP_RateType(RemoteWebDriver driver, String Hotel_Name, int Room_Type) throws Exception {
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 60,
				"Search Results Page has not loaded  :( :( :( :( :( :(");
		elementVisible(driver, getObjectHotels("HotelCorp_SRP_HotelName_TextBox"), 20);
		logURL(driver);

		// ------------------------------------------------------ Click Price Fiter
		// ----------------------------------------------------------//

		elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
		safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
		// ------------------------------------------- Select the RoomType
		// --------------------------------------------------------//
		loop: for (int i = 1; i < 5; i++) {
			String SelectRoom_XPath = "//li[" + i + "]/section/div[5]/div/button";
			safeClick(driver, By.xpath(SelectRoom_XPath));
			String BookButton_XPath = "//li[" + i + "]/div/div[2]/table/tbody/tr[" + Room_Type + "]/td[6]/input";
			Thread.sleep(2000);
			elementNotVisible(driver, By.cssSelector("span.loader.dotDotDot"), 2);
			if (elementPresent_Time(driver, By.xpath(BookButton_XPath), 2)) {
				safeClick(driver, By.xpath(BookButton_XPath));
				break loop;
			}
		}

	}

	public void corpHotel_ItineraryPage(RemoteWebDriver driver) throws Exception {
		for (int i = 0; i < 5; i++) {
			if (elementVisible(driver, getObjectHotels("HotelCorp_ReviewPage_Book_Button"), 5)) {
				break;
			} else if (textPresent(driver, "Hotel no longer available", 1)) {
				Reporter.log("Hotel no longer available : Message is displayed in Itinerary page");
				Assert.assertTrue(false);
			} else if (textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 1)) {
				Reporter.log("Oops! Cleartrip’s system is behaving badly : Message is displayed in Itinerary page");
				Assert.assertTrue(false);
			}
			if (!elementVisible(driver, getObjectHotels("HotelCorp_ReviewPage_Book_Button"), 5)) {
				Reporter.log("Itinerary page is not displayed");
				Assert.assertTrue(false);
			}
			String Iti = driver.getPageSource();

			// Reporter.log("Itinerary ID="+Iti.split("var itineraryId
			// =")[1].split(";")[0]);

		}
		if (elementVisible(driver, By.id("booking_violation_reasons"), 2)) {
			smartSelect(driver, By.id("booking_violation_reasons"), "Other");
		}
		safeClick(driver, getObjectHotels("HotelCorp_ReviewPage_Book_Button"));
	}

	public void corpHotel_ItineraryPagePAH(RemoteWebDriver driver) throws Exception {
		 elementVisible(driver, getObject("HotelCorp_ReviewPage_Book_Button"), 60);
		  if(!elementVisible(driver, getObject("HotelCorp_ReviewPage_PAH"), 10)) {
				Reporter.log("Pay @ Hotel link is not displayed");
				Assert.assertTrue(false);
		} 
		  else Reporter.log("Pay @ Hotel link is displayed");
		  safeClick(driver, getObject("HotelCorp_ReviewPage_PAH"));
			if(elementVisible(driver, By.id("booking_violation_reasons"), 2)){
				smartSelect(driver, By.id("booking_violation_reasons"), "Other");
			}
		  safeClick(driver, getObject("HotelCorp_ReviewPage_Book_Button"));
	}
	

	public void corpHotel_TravelerPage(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObjectHotels("HotelCorp_TravellerPage_Button"), 10);
		safeAutocomplete(driver, getObjectHotels("HotelCorp_TravellerPage_Name"),
				getObjectHotels("HotelCorp_TravellerPage_NameAjax"), "test booking");
		Thread.sleep(2000);
		safeType(driver, getObjectHotels("HotelCorp_TravellerPage_EmailID"), "automation@cleartrip.com");
		safeType(driver, getObjectHotels("HotelCorp_TravellerPage_PhoneNumber"), "1212121212");		
		safeType(driver, getObjectHotels("HotelCorp_TravellerPage_SplRequest"), dataFile.value("Hotel_Special_Request"));
				if(MakePaymentOnlyInProd) {
					safeType(driver, getObjectHotels("HotelCorp_TravellerPage_EmailID"), "automation@cleartrip.com");
					safeType(driver, getObjectHotels("HotelCorp_TravellerPage_PhoneNumber"), "1212121212");		
					safeType(driver, getObjectHotels("HotelCorp_TravellerPage_SplRequest"),"");			
				}
		safeClick(driver, getObjectHotels("HotelCorp_TravellerPage_Button"));
	}

	public void corpHotel_TravelerPage_GST(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObjectHotels("HotelCorp_TravellerPage_Button"), 10);
		safeAutocomplete(driver, getObjectHotels("HotelCorp_TravellerPage_Name"),
				getObjectHotels("HotelCorp_TravellerPage_NameAjax"), "test booking");
		elementPresent_log(driver, getObjectHotels("HotelCorp_TravellerPage_GSTNumber"), "GST DropDown", 10);
		safeSelectByIndex(driver, getObjectHotels("HotelCorp_TravellerPage_GSTNumber"), 1);

		safeType(driver, getObjectHotels("HotelCorp_TravellerPage_EmailID"), "automation@cleartrip.com");
		safeType(driver, getObjectHotels("HotelCorp_TravellerPage_PhoneNumber"), "1212121212");
		safeType(driver, getObjectHotels("HotelCorp_TravellerPage_SplRequest"),
				dataFile.value("Hotel_Special_Request"));
		safeClick(driver, getObjectHotels("HotelCorp_TravellerPage_Button"));
	}

	public void corpHotel_TravelerPage_GSTState(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObjectHotels("HotelCorp_TravellerPage_Button"), 10);
		safeAutocomplete(driver, getObjectHotels("HotelCorp_TravellerPage_Name"),
				getObjectHotels("HotelCorp_TravellerPage_NameAjax"), "test booking");
		elementPresent_log(driver, getObjectHotels("HotelCorp_TravellerPage_GSTState"), "GST DropDown", 10);
		safeSelect(driver, getObjectHotels("HotelCorp_TravellerPage_GSTState"), "Karnataka");
		safeType(driver, getObjectHotels("HotelCorp_TravellerPage_EmailID"), "automation@cleartrip.com");
		safeType(driver, getObjectHotels("HotelCorp_TravellerPage_PhoneNumber"), "1212121212");
		safeType(driver, getObjectHotels("HotelCorp_TravellerPage_SplRequest"),
				dataFile.value("Hotel_Special_Request"));
		safeClick(driver, getObjectHotels("HotelCorp_TravellerPage_Button"));
	}

	public String corpHotel_PaymentPage(RemoteWebDriver driver, String PaymentType, String Coupon,
			String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		String TripID = null;
		elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"), 50);
		if (elementVisible(driver, By.id("processingFeeAmount"), 5)) {
			HotelProcessingFee = getText(driver, By.id("processingFeeAmount"));
			HotelProcessingFee = HotelProcessingFee.replace("Rs. ", "");
			// System.out.println("HotelProcessingFee : "+HotelProcessingFee);
		}
		if (Coupon.equalsIgnoreCase("COUPON")) {
			if(!ProductionUrl) {
			// safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"),
			// dataFile.value("HotelB2B_Coupon"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"),
					dataFile.value("HotelB2B_Coupon"));
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_Button"));
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 60);
			Thread.sleep(5000);
			textAssert(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"),
					"Great, you are eligible to a cashback");
			}
		}
		if (Coupon.equalsIgnoreCase("COUPONAE")) {
			// safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"),
			// dataFile.value("HotelB2B_Coupon"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"),
					dataFile.value("HotelB2B_CouponAE"));
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_Button"));
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 60);
			Thread.sleep(5000);
			textAssert(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"),
					"Great, you are eligible to a cashback");
		}
		if (PaymentType.equalsIgnoreCase("DEPOSITACCOUNT") || PaymentType.equals("")) {
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_DepositAccount_Tab"));
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

		else if (PaymentType.equalsIgnoreCase("CREDITCARD")) {
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"));
			if(!ProductionUrl) {
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Number"),
					dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Exp_Month"), "5");
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Exp_Year"),
					dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_CVV"),
					dataFile.value("MasterCard_CVV"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Name"),
					dataFile.value("MasterCard_HolderName"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_FirstName"), "test");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_LastName"), "test");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Address"), "JP Nagar");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_City"), "JP Nagar");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_State"), "Karnataka");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Pin"), "560076");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Country"), "India");
			Thread.sleep(2000);
			}
		}

		else if (PaymentType.equalsIgnoreCase("DEBITCARD")) {			
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Tab"));
			if(!ProductionUrl) {
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Number"),
					dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Exp_Month"), "5");
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Exp_Year"),
					dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_CVV"),
					dataFile.value("MasterCard_CVV"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_BillName"),
					dataFile.value("MasterCard_HolderName"));
			Thread.sleep(2000);
			}
		}

		else if (PaymentType.equalsIgnoreCase("NETBANKING")) {
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBanking_Tab"));
			// safeSelect(driver,
			// getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"), "State Bank of
			// India");
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Bank of India");
			Thread.sleep(2000);
		}
		
		else if (PaymentType.equalsIgnoreCase("NETBANKINGCITI")) {
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBanking_Tab"));
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Citibank");
			Thread.sleep(2000);

			Reporter.log("Payment Button is Clicked");
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_BookButton"));
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CityBank_Site_Logo"), 30)){
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

		else if (PaymentType.equalsIgnoreCase("NETBANKINGPROD")) {

			safeClick(driver, getObjectHotels("HotelCorp_PaymentPage_NetBanking_Tab"));
			safeSelect(driver, getObjectHotels("HotelCorp_PaymentPage_NetBank_Dropdown"), "State Bank of India");
			Thread.sleep(2000);
			safeClick(driver, getObjectHotels("HotelCorp_PaymentPage_BookButton"));

			if (!elementPresent_Time(driver, getObjectHotels("HotelCorp_BookStep4_SBI_Bank_CancelLink"), 5)) {
				refreshPage(driver);
			}
			for (int i = 0; i <= 60; i++) {
				if (elementPresent_Time(driver, getObjectHotels("HotelCorp_BookStep4_SBI_Bank_CancelLink"), 1)) {
					break;
				}
				if (textPresent(driver, "Oops, your payment didn’t work", 5)) {
					Reporter.log("Oops, your payment didn’t work ");
					Assert.assertTrue(false);
				}
			}
			if (elementPresent_Time(driver, getObjectHotels("HotelCorp_BookStep4_SBI_Bank_CancelLink"), 1)) {
				safeClick(driver, getObjectHotels("HotelCorp_BookStep4_SBI_Bank_CancelLink"));
			} else {
				Reporter.log("Page has not redirected to Netbanking Site");
				AssertJUnit.assertEquals(true, false);
			}

			if (textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Sorry, our system is acting up. message is displayed");
				Assert.assertTrue(false);
			}
			if (!elementPresent_Time(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)) {
				Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
				AssertJUnit.assertEquals(true, false);
			} else
				Reporter.log("Page has redirected to back to Cleartrip from Netbanking Site");

			/*
			 * safeClick(driver,
			 * getObjectHotels("AgencyHotels_PaymentPage_NetBanking_Tab"));
			 * //safeSelect(driver,
			 * getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"),
			 * "State Bank of India"); safeSelect(driver,
			 * getObjectHotels("AgencyHotels_PaymentPage_NetBank_Dropdown"),
			 * "Bank of India"); Thread.sleep(2000); safeClick(driver,
			 * getObjectHotels("AgencyHotels_PaymentPage_BookButton"));
			 * if(elementPresent_Time(driver,
			 * getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"), 100)){
			 * Thread.sleep(2000); elementPresent(driver,
			 * getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"));
			 * Reporter.log("Netbanking Site is displayed"); }
			 * if(elementPresent_Time(driver, By.linkText("Return to Biller Site"), 100)){
			 * Thread.sleep(2000); elementPresent(driver,
			 * By.linkText("Return to Biller Site"));
			 * Reporter.log("Netbanking Site is displayed"); } else {
			 * Reporter.log("Page has not redirected to Netbanking Site");
			 * Assert.assertEquals(true, false); } safeClick(driver,
			 * By.linkText("Return to Biller Site"));
			 */
		}

		else if (PaymentType.equalsIgnoreCase("NBBOI")) {

			waitElement(driver, getObjectHotels("HotelCorp_PaymentPage_NetBanking_Tab"), 10);
			safeClick(driver, getObjectHotels("HotelCorp_PaymentPage_NetBanking_Tab"));

			safeSelect(driver, getObjectHotels("HotelCorp_PaymentPage_NetBank_Dropdown"), "Bank of India");
			safeClick(driver, getObjectHotels("HotelCorp_PaymentPage_BookButton"));

			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

			if (textPresent(driver, "This site can’t be reached", 20)) {
				Reporter.log("This site can’t be reached message is displayed refreshing the page");
				refreshPage(driver);
			} else if (elementVisible(driver, getObjectHotels("HotelCom_BookStep4_BOI_Bank_CancelLink"), 10)) {
				Thread.sleep(2000);
				Reporter.log("NB site page is displayed");
				elementPresent(driver, getObjectHotels("HotelCom_BookStep4_BOI_Bank_CancelLink"));
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_BOI_Bank_CancelLink"));
				if(textPresent(driver, "Cleartrip Travel Services Pvt Ltd", 1))
				{
					elementVisible(driver, By.linkText("Cancel"), 1);
					safeClick(driver, By.linkText("Cancel"));
				}
				else
				{
					Reporter.log("Page has redirected to back to Cleartrip from Netbanking Site");
				}
				//safeClick(driver, By.xpath("//a[contains(text(),'Cancel')]"));
			}
			else if (textPresent(driver, "Sorry, our system is acting up.", 1)) {
				Reporter.log("Sorry, our system is acting up. message is displayed");
				Assert.assertTrue(false);
			} else if (!elementPresent_Time(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"), 5)) {
				Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
				Assert.assertTrue(false);
			} else if (textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 1)) {
				Reporter.log("Oops! Cleartrip’s system is behaving badly");
				Assert.assertTrue(false);
			} else if(!textPresent(driver, "Oops, your payment didn’t work", 1)) {
				Reporter.log("Oops! Cleartrip’s system is behaving badly textis not displayed");
				Assert.assertTrue(false);
		}
			else Reporter.log("Page has redirected to back to Cleartrip from Netbanking Site");
		}
		if (textPresent(driver, "This site can’t be reached", 5)) {
			Reporter.log("This site can’t be reached message is displayed");
			Assert.assertTrue(false);
		}
		/*
		 * else { Reporter.log("Netbanking Site is not displayed");
		 * Assert.assertTrue(false); }
		 */

		// ------------------------------------------------------------- MAke Payment in
		// QA2 Only-------------------------------------------------//

		if (MakePaymentOnlyInQA2) {
			if (!(PaymentType.equalsIgnoreCase("NETBANKINGPROD") || PaymentType.equalsIgnoreCase("NETBANKING")
					 || PaymentType.equalsIgnoreCase("NETBANKINGCITI")|| (PaymentType.equalsIgnoreCase("NBBOI")))) {
				Reporter.log("Payment Button is Clicked");
				safeClick(driver, getObjectHotels("HotelCorp_PaymentPage_Payment_Button"));
				hotelCom_PaymentPage_Authentication(driver, "");
				for (int i = 0; i <= 30; i++) {
					if (elementVisible(driver, getObjectHotels("HotelCorp_ConfirmationPage_TripID"), 5)) {
						break;
					}
					if (elementVisible(driver, By.cssSelector("h1.Failure"), 1)) {
						Reporter.log("Oops! Cleartrip’s system is behaving badly");
						Assert.assertTrue(false);
					}
					if (textPresent(driver, "Oops, your booking didn’t go through", 1)) {
						Reporter.log("Oops, your booking didn’t go through");
						Assert.assertTrue(false);
					}

				}
				TripID = getText(driver, getObjectHotels("HotelCorp_ConfirmationPage_TripID"));
				// String SuccessMessage = getText(driver,
				// getObjectHotels("HotelCorp_ConfirmationPage_ConfirmationText"));
				Reporter.log(Trip_Logger_Msg + TripID);
				logger.info(Trip_Logger_Msg + TripID);
				// Assert.assertEquals(Booking_Confirmation_Message, SuccessMessage);

				/*
				 * safeClick(driver,
				 * getObjectHotels("HotelCorp_ConfirmationPage_SignOut_Link"));
				 * elementVisible(driver, getObjectHotels("CorpCom_SignIN_EmailID"), 10);
				 */
			}
			if (PaymentType.equalsIgnoreCase("NETBANKING")) {
				Reporter.log("Payment Button is Clicked");
				safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_BookButton"));
				/*
				 * if(elementPresent_Time(driver,
				 * getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"), 100)){
				 * Thread.sleep(2000); safeClick(driver,
				 * getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink")); } else {
				 * Reporter.log("Page has not redirected to Netbanking Site");
				 * Assert.assertEquals(true, false); }
				 */
				if (elementPresent_Time(driver, By.linkText("Return to Biller Site"), 100)) {
					Thread.sleep(2000);
					elementPresent(driver, By.linkText("Return to Biller Site"));
					Reporter.log("Netbanking Site is displayed");
				} else {
					Reporter.log("Page has not redirected to Netbanking Site");
					AssertJUnit.assertEquals(true, false);
				}
				safeClick(driver, By.linkText("Return to Biller Site"));
				// safeClick(driver, getObjectHotels("HotelCom_BookStep4_SBI_Bank_CancelLink"));
				if (!elementPresent_Time(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)) {
					Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
					AssertJUnit.assertEquals(true, false);
				}

			}
			else if (PaymentType.equalsIgnoreCase("NETBANKINGCITI")) {
				
			}
			

		}
		
		else if(MakePaymentOnlyInProd) {

			System.out.println("=============LIVE bookings are done ===============");
			Reporter.log("Payment Button is Clicked");
			safeClick(driver, getObjectHotels("HotelCorp_PaymentPage_Payment_Button"));
			hotelCom_PaymentPage_Production_Card_Authentication(driver, "Agency");	
			for (int i = 0; i <= 30; i++) {
				if (elementVisible(driver, getObjectHotels("HotelCorp_ConfirmationPage_TripID"), 5)) {
					break;
				}
				if (elementVisible(driver, By.cssSelector("h1.Failure"), 1)) {
					Reporter.log("Oops! Cleartrip’s system is behaving badly");
					Assert.assertTrue(false);
				}
				if (textPresent(driver, "Oops, your booking didn’t go through", 1)) {
					Reporter.log("Oops, your booking didn’t go through");
					Assert.assertTrue(false);
				}

			}
			TripID = getText(driver, getObjectHotels("HotelCorp_ConfirmationPage_TripID"));
			Reporter.log(Trip_Logger_Msg + TripID);
			logger.info(Trip_Logger_Msg + TripID);
		}
		return TripID;
	}

	public String corpHotel_PaymentPage_PAH(RemoteWebDriver driver) throws Exception	{
		  if(!elementVisible(driver, getObject("HotelCorp_PaymentPage_PAH_OTP_Text"), 30)) {
			  Reporter.log("PAH Verification OTP textbox not displayed");
			  Assert.assertTrue(false);
		  }
		  if(!elementVisible(driver, By.id("payCustMobile"), 30)) {
			  Reporter.log("Customer mobile text box is not displayed");
			  Assert.assertTrue(false);
		  }
		  if(!elementVisible(driver, By.id("payAtHotelEmail"), 30)) {
			  Reporter.log("Customer Email text box is not displayed");
			  Assert.assertTrue(false);
		  }
		  safeType(driver, By.id("payAtHotelEmail"), "automation@cleartrip.com");
		  safeType(driver, By.id("payCustMobile"), "1234567890");
		  safeClick(driver, By.id("payCustMobileBtn"));
		  elementVisible(driver, By.id("payCustMobSucc"), 5);
		  if(!elementVisible(driver, By.id("payCustMobSucc"), 5)){
			  Reporter.log("OTP sent message for PAH OTP validation is not displayed");
			  Assert.assertTrue(false);
		  }
		  safeType(driver, By.id("payHotelOTP"), "1212");
		  safeClick(driver, By.id("payHotelOTPBtn"));
		  
		  if(!elementVisible(driver, By.id("payHotelOTPErr"), 5)){
			  Reporter.log("Error message is not displayed for PAH OTP validation");
			  Assert.assertTrue(false);
		  }
		  if(!textPresent(driver, "Please enter that verification code below to confirm your booking.", 5)){
			  Reporter.log("Please enter that verification code below to confirm your booking. message is not displayed for PAH OTP validation");
			  Assert.assertTrue(false);
		  }
		  String Itinerary=driver.getPageSource();

			Reporter.log("Itinerary ID="+Itinerary.split("var itineraryId = ")[1].split(";")[0]);
			String ItinearyRaw = Itinerary.split("var itineraryId = ")[1].split(";")[0];

			ItinearyRaw = ItinearyRaw.substring(1);
			ItinearyRaw = ItinearyRaw.replace(ItinearyRaw.substring(ItinearyRaw.length()-1), "");
			String OTP = hotelComPAHSendOTP_Corp(driver, ItinearyRaw);       
			safeType(driver, By.id("payHotelOTP"), OTP);
			safeClick(driver, By.id("payHotelOTPBtn"));
		  
		  if(!textPresent(driver, "Verification code has been sent to the mobile number and email address", 5)){
			 Reporter.log("Verification code has been sent to the mobile number and email address : message not displayed"); 
			 Assert.assertTrue(false);
		  }
		  if(!textPresent(driver, "Verification code has been sent to the mobile number and email address", 5)){
				 Reporter.log("Verification code has been sent to the mobile number and email address : message not displayed"); 
				 Assert.assertTrue(false);
			  }
		  if(!textPresent(driver, "Great, verified successfully", 5)){
				 Reporter.log("Great, verified successfully : message not displayed"); 
				 Assert.assertTrue(false);
			  }
		  Thread.sleep(2000);
		return OTP;
	}
	
	public String corpHotel_PaymentPage_NoSignOut(RemoteWebDriver driver, String PaymentType, String Coupon,
			String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		String TripID = null;
		if (Coupon.equalsIgnoreCase("COUPON")) {
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_TextBox"),
					dataFile.value("HotelCom_Coupon"));
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_Button"));
			elementVisible(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"), 60);
			textAssert(driver, getObjectHotels("AgencyHotels_PaymentPage_Coupon_ELigibleText"),
					"Great, you are eligible to a cashback");
		}
		if (PaymentType.equalsIgnoreCase("DEPOSITACCOUNT")) {
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_DepositAccount_Tab"));
		} else if (PaymentType.equalsIgnoreCase("CREDITCARD")) {
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Tab"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Number"),
					dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Exp_Month"), "5");
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Exp_Year"),
					dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_CVV"),
					dataFile.value("MasterCard_CVV"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_CreditCard_Name"),
					dataFile.value("MasterCard_HolderName"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_FirstName"), "test");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_LastName"), "test");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Address"), "JP Nagar");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_City"), "JP Nagar");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_State"), "Karnataka");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Pin"), "560076");
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_Bill_Country"), "India");
			Thread.sleep(2000);
		} else if (PaymentType.equalsIgnoreCase("DEBITCARD")) {
			safeClick(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Tab"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Number"),
					dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Exp_Month"), "5");
			safeSelect(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_Exp_Year"),
					dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_CVV"),
					dataFile.value("MasterCard_CVV"));
			safeType(driver, getObjectHotels("AgencyHotels_PaymentPage_DebitCard_BillName"),
					dataFile.value("MasterCard_HolderName"));
			Thread.sleep(2000);
		}

		if (MakePaymentOnlyInQA2) {
			safeClick(driver, getObjectHotels("HotelCorp_PaymentPage_Payment_Button"));
			elementVisible(driver, getObjectHotels("HotelCorp_ConfirmationPage_TripID"), 90);
			TripID = getText(driver, getObjectHotels("HotelCorp_ConfirmationPage_TripID"));
			String SuccessMessage = getText(driver, getObjectHotels("HotelCorp_ConfirmationPage_ConfirmationText"));
			Reporter.log(Trip_Logger_Msg + TripID);
			logger.info(Trip_Logger_Msg + TripID);
			// Assert.assertEquals(Booking_Confirmation_Message, SuccessMessage);
		}
		return TripID;
	}

	//public void corpHotel_Account_Cancellation(RemoteWebDriver driver, String TripID, Map<String, String> corpSRPData)
	public void corpHotel_Account_Cancellation(RemoteWebDriver driver, String TripID)
			throws Exception {
		if (MakePaymentOnlyInQA2) {
			safeClick(driver, By.linkText("Trips"));
			elementPresent_Time(driver, By.xpath("//h1"), 30);
			elementPresent_Time(driver, getObjectHotels("HotelCorp_TripsPage_SearchTrips"), 5);
			safeType(driver, getObjectHotels("HotelCorp_TripsPage_SearchTrips"), TripID);
			safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
			Thread.sleep(3000);
			if (elementVisible(driver, By.id("listView_a"), 2)) {
				smartClick(driver, By.id("listView_a"));
				smartClick(driver, By.xpath("//p/a"));
			}
			elementPresent_Time(driver, By.linkText("Cancellations"), 50);
			for (int i = 0; i <= 10; i++) {
				if (elementVisible(driver, By.linkText("Cancellations"), 2)) {
					Reporter.log("Cancel Link is displayed");
					break;
				} else if (textPresent(driver, "We will send you the e-ticket shortly", 2)) {
					Reporter.log("We will send you the e-ticket shortly : message is displayed");
					refreshPage(driver);
				}
			}

			/*
			 * elementVisible(driver, By.linkText("Pricing & payment details"), 20);
			 * safeClick(driver, By.linkText("Pricing & payment details"));
			 * if(!elementVisible(driver, By.cssSelector("dd.total"), 10)){
			 * safeClick(driver, By.linkText("Pricing & payment details")); }
			 */
			elementVisible(driver, By.xpath("//h1"), 20);
			/*Map<String, String> corpAccTripData = new HashMap<String, String>();
			corpAccTripData.put("Name", getText(driver, By.xpath("//h1")));
			corpAccTripData.put("RoomType", getText(driver, By.xpath("//td[5]")));
			corpAccTripData.put("Price", getText(driver, By.cssSelector("div.total > dd")));
			int AcctPriceInt = hotelCom_ConvertPrice_To_Int(driver, By.cssSelector("div.total > dd"));
			String SRPPrice = corpSRPData.get("Price");
			if (SRPPrice.contains(",")) {
				SRPPrice = SRPPrice.replaceAll(",", "");
			}
			int SRPPriceInt = Integer.parseInt(SRPPrice);
			String AcctPrice = corpAccTripData.get("Price");
			AcctPrice = AcctPrice.replace("Rs. ", "");
			// int AcctPriceInt = Integer.parseInt(AcctPrice);
			int TotalPrice;
			if (HotelProcessingFee != null) {
				int HotelProcessingFeeInt = Integer.parseInt(HotelProcessingFee);
				TotalPrice = SRPPriceInt + HotelProcessingFeeInt;
			} else
				TotalPrice = SRPPriceInt;

			if (!corpAccTripData.get("Name").contains(corpSRPData.get("Name"))) {
				Reporter.log("Hotel Name doesnt Match 'SRP Name ' : " + corpSRPData.get("Name")
						+ " & 'Accnt Trip Page Name ' : " + corpAccTripData.get("Name"));
				// System.out.println("Hotel Name doesnt Match 'SRP Name ' :
				// "+corpSRPData.get("Name")+" & 'Accnt Trip Page Name ' :
				// "+corpAccTripData.get("Name") );
				Assert.assertTrue(false);
			}
			if (!corpSRPData.get("RoomType").equals(corpAccTripData.get("RoomType"))) {
				Reporter.log("Hotel Name doesnt Match 'SRP RoomType ' : " + corpSRPData.get("RoomType")
						+ " & 'Accnt Trip Page RoomType ' : " + corpAccTripData.get("RoomType"));
				// System.out.println("Hotel Name doesnt Match 'SRP RoomType ' :
				// "+corpSRPData.get("RoomType")+" & 'Accnt Trip Page RoomType ' :
				// "+corpAccTripData.get("RoomType") );
				Assert.assertTrue(false);
			}
			if (!(TotalPrice == AcctPriceInt)) {
				Reporter.log("Hotel Name doesnt Match 'SRP Price + Processing fee ' : " + TotalPrice
						+ " & 'Accnt Trip Page Price ' : " + AcctPriceInt);
				// System.out.println("Hotel Name doesnt Match 'SRP Price + Processing fee ' :
				// "+TotalPrice+" & 'Accnt Trip Page Price ' : "+AcctPriceInt);
				Assert.assertTrue(false);
			}*/

			safeClick(driver, By.linkText("Cancellations"));
			/*
			 * elementPresent_Time(driver,
			 * By.xpath("//input[starts-with(@id, 'group_box_ce_')]"), 10);
			 * assertTrue("Trip Cancellation Page Has Not Displayed", elementPresent(driver,
			 * By.xpath("//input[starts-with(@id, 'group_box_ce_')]"))); safeClick(driver,
			 * By.xpath("//input[starts-with(@id, 'group_box_ce_')]")); safeClick(driver,
			 * By.xpath("//*[@id='cancel']"));
			 */
			elementVisible(driver, By.xpath("//input[4]"), 10);
			if (!textPresent(driver, "Cancel your stay at", 10)) {
				Reporter.log("Cancel your stay at : message is not displayed after cancel 1 buton is clicked");
				Assert.assertTrue(false);
			}
			safeClick(driver, By.xpath("//input[4]"));
			elementVisible(driver, By.id("Flash"), 20);
			if (!textPresent(driver, "Cancellation confirmation", 30)) {
				Reporter.log("We have successfully cancelled this trip : message is not displayed");
				Assert.assertTrue(false);
			}

		}

	}
	
	public void corpHotel_Account_Cancellation_Production(RemoteWebDriver driver, String TripID) throws Exception {

		if (MakePaymentOnlyInProd) {
			safeClick(driver, By.linkText("Trips"));
			elementPresent_Time(driver, By.xpath("//h1"), 30);
			elementPresent_Time(driver, getObjectHotels("HotelCorp_TripsPage_SearchTrips"), 5);
			safeType(driver, getObjectHotels("HotelCorp_TripsPage_SearchTrips"), TripID);
			safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
			Thread.sleep(3000);
			if (elementVisible(driver, By.id("listView_a"), 2)) {
				smartClick(driver, By.id("listView_a"));
				smartClick(driver, By.xpath("//p/a"));
			}
			elementPresent_Time(driver, By.linkText("Cancellations"), 50);
			for (int i = 0; i <= 10; i++) {
				if (elementVisible(driver, By.linkText("Cancellations"), 2)) {
					Reporter.log("Cancel Link is displayed");
					break;
				} else if (textPresent(driver, "We will send you the e-ticket shortly", 2)) {
					Reporter.log("We will send you the e-ticket shortly : message is displayed");
					refreshPage(driver);
				}
			}

			
			elementVisible(driver, By.xpath("//h1"), 20);
			
			safeClick(driver, By.linkText("Cancellations"));
		
			elementVisible(driver, By.xpath("//input[4]"), 10);
			if (!textPresent(driver, "Cancel your stay at", 10)) {
				Reporter.log("Cancel your stay at : message is not displayed after cancel 1 buton is clicked");
				Assert.assertTrue(false);
			}
			safeClick(driver, By.xpath("//input[4]"));
			elementVisible(driver, By.id("Flash"), 20);
			if (!textPresent(driver, "We have successfully cancelled this trip", 30)) {
				Reporter.log("We have successfully cancelled this trip : message is not displayed");
				Assert.assertTrue(false);
			}
		}
	
	}

	public void corpHotel_HQ_Cancellation(RemoteWebDriver driver, String TripID, Map<String, String> corpSRPData)
			throws Exception {
		if (MakePaymentOnlyInQA2) {
			driver.manage().deleteAllCookies();
			hotelCom_Open_TripID_HQ(driver, TripID);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			if (!elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Link"), 60)) {
				refreshPage(driver);
			}
			Map<String, String> corpHQTripData = new HashMap<String, String>();
			corpHQTripData.put("Name", getText(driver, By.xpath("//h1")));
			corpHQTripData.put("RoomType", getText(driver, By.xpath("//tr[3]/td")));
			corpHQTripData.put("Price", getText(driver, By.cssSelector("dd.total")));

			String SRPPrice = corpSRPData.get("Price");
			SRPPrice = SRPPrice.replaceAll("\\D+", "");
			int SRPPriceInt = Integer.parseInt(SRPPrice);
			String AcctPrice = getText(driver, By.cssSelector("dd.total"));
			AcctPrice = AcctPrice.replace("Rs. ", "");
			int AcctPriceInt = Integer.parseInt(AcctPrice);
			int HotelProcessingFeeInt = Integer.parseInt(HotelProcessingFee);
			int TotalPrice = SRPPriceInt + HotelProcessingFeeInt;
			if (!(TotalPrice == AcctPriceInt)) {
				Reporter.log("Hotel Price doesnt Match 'SRP Price + Processing fee ' : " + TotalPrice
						+ " & 'Accnt Trip Page Price ' : " + AcctPriceInt);
				// Assert.assertTrue(false);
			}
			if (!corpHQTripData.get("Name").contains(corpSRPData.get("Name"))) {
				Reporter.log("Hotel Name doesn't Match 'SRP Name ' : " + corpSRPData.get("Name")
						+ " & 'HQ Trip Page Name ' : " + corpHQTripData.get("Name"));
				// System.out.println("Hotel Name doesn't Match 'SRP Name ' :
				// "+corpSRPData.get("Name")+" & 'HQ Trip Page Name ' :
				// "+corpHQTripData.get("Name") );
				Assert.assertTrue(false);
			}
		/* - commented it as we are not capturing room type in srp
			if (!corpSRPData.get("RoomType").equals(corpHQTripData.get("RoomType"))) {
				Reporter.log("Hotel Name doesn't Match 'SRP RoomType ' : " + corpSRPData.get("RoomType")
						+ " & 'HQ Trip Page RoomType ' : " + corpHQTripData.get("RoomType"));
				// Assert.assertTrue(false);
			}
			*/
			String Confirmation_Status = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Status"));
			if (!Confirmation_Status.contains("Confirmed")) {
				Reporter.log("Trip Status after cancellation is : " + Confirmation_Status);
				Assert.assertTrue(false);
			}
			safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Link"));
			safeType(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), "Test Booking");
			safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Button"));
			elementNotPresent_Time(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), 20);
			elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Status"), 60);
			String Cancel_Status = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Status"));
			if (!Cancel_Status.contains("Cancelled")) {
				Reporter.log("Trip Status after cancellation is : " + Cancel_Status);
				AssertJUnit.assertEquals(true, false);
			}
		}
	}

	public void hotelCom_Open_TripID_HQ(RemoteWebDriver driver, String TripID) throws Exception {
		String URL = logURL(driver);
		if (URL.contains("com")) {
			driver.get("http://" + common.value("host") + common.value("url") + "com" + "/hq/trips/" + TripID);
		} else if (URL.contains("ae")) {
			driver.get("http://" + common.value("host") + common.value("url") + "ae" + "/hq/trips/" + TripID);
		}
	}

	public void corpHotel_ConfirmationPage(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObjectHotels("HotelCorp_ConfirmationPage_TripID"), 90);
		String TripID = getText(driver, getObjectHotels("HotelCorp_ConfirmationPage_TripID"));
		Thread.sleep(2000);
		Reporter.log("Trip ID : " + TripID);
	}

	public void corpHotel_HQ_TripTools(RemoteWebDriver driver) throws Exception {
		textPresent(driver, "Itinerary", 50);
		elementPresent_log(driver, By.xpath("//*[@id='booking-source']/h2"), "Booking Source: Corp", 1);
		if (!textPresent(driver, "Corporate Booking", 50)) {
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, By.xpath("//*[@id='layer_1']/table[2]/tbody/tr[3]/td[5]"), "Trip Status Verified",
				1);
		elementPresent_log(driver, By.id("tab_2"), "Note Tab Verify", 1);
		safeClick(driver, By.id("tab_2"));
		if (!textPresent(driver, "Add a note to this trip", 50)) {
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, By.id("tab_3"), "Audit Trail Tab Verify", 1);
		safeClick(driver, By.id("tab_3"));
		if (!textPresent(driver, "Audit Trail", 50)) {
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, By.id("tab_4"), "Payment Details Tab Verify", 1);
		safeClick(driver, By.id("tab_4"));
		if (!textPresent(driver, "Send ct-pay email to the customer", 50)) {
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, By.id("tab_1"), "Payment Details Tab Verify", 1);
		safeClick(driver, By.id("tab_1"));
		safeClick(driver, By.xpath("//a[contains(@bubbleblock,'pax_pricing_breakup')][1]"));
		safeClick(driver, By.xpath("//*[@id='ct_bubbleNode']/a"));
		safeClick(driver, By.xpath("//a[@title='Email trip details']"));
		safeType(driver, By.id("email"), dataFile.value("Corp_Hotels_Trips_SendMail")); // changed from HotelCom_SendMail to Corp_Hotels_Trips_SendMail
		safeClick(driver, By.id("SendTicketButton"));
		if (!textPresent(driver, " We've sent the itinerary details in an email to", 50)) {
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//a[@title='SMS trip details']"));
		safeType(driver, By.id("mobile_number"), dataFile.value("HotelCom_SendSms"));
		safeClick(driver, By.id("SendSmsButton"));
		if (!textPresent(driver, "We've sent a SMS to", 50)) {
			Assert.assertTrue(false);
		}
		Thread.sleep(1000);		
		safeClick(driver, By.xpath("//*[@id='toolbox']/ul[1]/li[3]"));// old -> //a[@title='Print tickets']
		Thread.sleep(1000);	
		driver.navigate().back();
		safeClick(driver, By.xpath("//*[@id='toolbox']/ul[1]/li[4]/a"));
		safeClick(driver, By.id("EmailSaleInvoice")); // old -> //a[@title='Email Sale Invoice']
		safeType(driver, By.id("email_sale_invoice"), dataFile.value("Corp_Hotels_Trips_SendMail")); // changed from HotelCom_SendMail to Corp_Hotels_Trips_SendMail
		safeClick(driver, By.xpath("//div[@id='EmailSaleInvoice']/form/input[@id='SendTicketButton']"));
		Thread.sleep(1000);
		if (!textPresent(driver, " We've sent the invoice details in an email to", 50)) {
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//*[@id='toolbox']/ul[1]/li[6]/a"));
		safeClick(driver,By.linkText("Print hotel invoice"));
		//old -> safeClick(driver, By.xpath("//*[@id='toolbox']/ul[1]/li[7]/a"));
	}

	public void corpHotel_Accounts_TripTools(RemoteWebDriver driver) throws Exception {
		if (elementVisible(driver, By.id("listView_a"), 2)) {
			smartClick(driver, By.id("listView_a"));
			smartClick(driver, By.xpath("//p/a"));

			
			//--------------------Print Voucher--------------------------------------------//
			safeClick(driver, By.linkText("Print voucher"));
			elementVisible(driver, By.xpath("//div[@id='tips_tools']/h2"), 5);
			elementPresent_log(driver, By.xpath("//div[@id='tips_tools']/h2"), "Print Voucher in acct not opened", 5);
			elementPresent_log(driver, By.linkText("Print this voucher"), "Print this voucher link not displayed in Voucher", 5);
			elementPresent_log(driver, By.linkText("Email voucher"), "Email voucher link not displayed in Voucher", 5);
			safeClick(driver, By.linkText("Email voucher"));
			safeType(driver, By.id("EmailAddress"),  dataFile.value("Corp_Hotels_Trips_SendMail"));
			safeClick(driver, By.xpath("//input[@value='Send Voucher']"));
			driver.navigate().back();
			
			//-------------------------------------Email Eticket--------------------------------//

			safeClick(driver, By.id("emailETicket"));
			safeType(driver, By.xpath("//*[@id='emailETicketForm']/input[1]"),  dataFile.value("Corp_Hotels_Trips_SendMail"));//dataFile.value("HotelCom_SendMail")
			safeClick(driver, By.xpath("//*[@id='emailETicketForm']/input[4]"));
			Thread.sleep(2000);
			driver.navigate().back(); // Since the new window is blank, redirecting back to previous screen - need to cross check in prod  
			//-------------------------------------Tax Invoice----------------------------------//
			
			safeClick(driver, By.linkText("Tax invoice"));
			Thread.sleep(2000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(5000);
			elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[3]/tbody"));
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
			
			//--------------------------Customer payment receipt----------------------------------------//
			Thread.sleep(2000);
			elementVisible(driver, By.linkText("Customer payment receipt"), 10);
			safeClick(driver, By.linkText("Customer payment receipt"));
			Thread.sleep(2000);
			ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs1.get(1));
			Thread.sleep(5000);
			elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[2]/tbody"));
			elementPresent(driver, By.xpath("//div[@id='ContentFrame']/div/table/tbody/tr/td/table[3]/tbody"));
			if(!textPresent(driver, "GST on Hotel Rate", 5)) {
				Reporter.log(" GST on Hotel Rate  - text is not displayed in Customer receipt");  
				Assert.assertTrue(false);
			}
			driver.switchTo().window(tabs1.get(1)).close();
			driver.switchTo().window(tabs1.get(0));
			
			safeClick(driver, By.id("emailTrip"));
			safeType(driver, By.xpath("//*[@id='emailTripForm']/input[1]"), dataFile.value("Corp_Hotels_Trips_SendMail"));
			safeClick(driver, By.xpath("//*[@id='emailTripForm']/input[3]"));
			Thread.sleep(3000);
			driver.navigate().back();// Since the new window is blank, redirecting back to previous screen 

			safeClick(driver, By.id("smsTrip"));
			safeType(driver, By.id("mobile_num"), dataFile.value("HotelCom_SendSms"));
			safeClick(driver, By.xpath("//*[@id='smsTripForm']/input[3]"));
			Thread.sleep(3000);

	/*		safeClick(driver, By.xpath("//*[@title='Tax invoice']"));
			safeClick(driver, By.xpath("//*[@title='Customer payment receipt']"));*/

		} else {
			Reporter.log("Account trip details page not loaded");
			Assert.assertTrue(false);
		}Reporter.log("Corp-Accounts trip links verified successully");

		/*if (elementVisible(driver, By.id("listView_a"), 2)) { // commented it as new one is working
			smartClick(driver, By.id("listView_a"));
			smartClick(driver, By.xpath("//p/a"));

			safeClick(driver, By.xpath("//*[@title='Print voucher']"));
			driver.navigate().back();
			Thread.sleep(3000);

			safeClick(driver, By.id("emailETicket"));
			safeType(driver, By.xpath("//*[@id='emailETicketForm']/input[1]"), dataFile.value("HotelCom_SendMail"));
			safeClick(driver, By.xpath("//*[@id='emailETicketForm']/input[4]"));
			Thread.sleep(3000);

			safeClick(driver, By.id("emailTrip"));
			safeType(driver, By.xpath("//*[@id='emailTripForm']/input[1]"), dataFile.value("HotelCom_SendMail"));
			safeClick(driver, By.xpath("//*[@id='emailTripForm']/input[3]"));
			Thread.sleep(3000);

			safeClick(driver, By.id("smsTrip"));
			safeType(driver, By.id("mobile_num"), dataFile.value("HotelCom_SendSms"));
			safeClick(driver, By.xpath("//*[@id='smsTripForm']/input[3]"));
			Thread.sleep(3000);

			safeClick(driver, By.xpath("//*[@title='Tax invoice']"));
			safeClick(driver, By.xpath("//*[@title='Customer payment receipt']"));
			Thread.sleep(3000);
		} else {
			Reporter.log("Account trip details page not loaded");
			Assert.assertTrue(false);
		}*/
	}

}