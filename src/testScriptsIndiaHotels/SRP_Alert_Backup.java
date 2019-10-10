package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

	public class SRP_Alert_Backup extends AirCommonMethod{
	public RemoteWebDriver driver;
	String Alert_Script = "<script>alert('Hello Cleartripper')</script>";
	
	@Test
	public void AlertCom_Bus_SRP() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1= "http://qa2.cleartrip.com/buses/results?from=";
		String URL2= "&to=Chennai&depart_date=14/";
		String URL3= "&fromId=4FCD3FCDE44D78692F22EC95&toId=4FCD3FCDE44D78692F233012";	
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3;
		driver.get(URL);
	 	alertCaptureAndFail(driver);
  }
	
	@Test
	public void AlertCom_Air_Oneway() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1= "http://qa2.cleartrip.com/flights/results?from=";
		String URL2= "&to=MAA&depart_date=10/";
		String URL3= "&adults=1&childs=0&infants=0&class=Economy&class=First&airline=&carrier=&intl=n";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3;
		driver.get(URL);
		driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
  }
	
	@Test
	public void AlertCom_Air_Roundtrip() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1 = "http://qa2.cleartrip.com/flights/results?from=";
		String URL2 = "&to=MAA&depart_date=10/";
		String URL3 = "&return_date=11/";
		String URL4 = "&adults=1&childs=1&infants=1&class=Economy&airline=SpiceJet+%28SG%29&carrier=SG&intl=n";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
	 	driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
  }

	@Test
	public void AlertCom_Air_Intl_Oneway() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1 = "http://qa2.cleartrip.com/flights/international/results?from=";
		String URL2 = "&to=MAA&depart_date=10/";
		String URL3 = "&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=y";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3;
		driver.get(URL);
	 	driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
  }

	@Test	
	public void AlertCom_Air_MultiCity() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1= "http://qa2.cleartrip.com/flights/results?from1=";
		String URL2= "&to1=MAA&depart_date_1=17/";
		String URL3= "&multicity=true&from2=MAA&to2=DEL&depart_date_2=18/";
		String URL4 = "&from3=&to3=&depart_date_3=&adults=1&childs=0&infants=0&class=Economy&intl=n&num_legs=2";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
	 	driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
	 	}

	@Test
	public void AlertCom_Air_Intl_MultiCity() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1= "http://qa2.cleartrip.com/flights/international/results?from1=";
		String URL2= "&to1=CMB&depart_date_1=17/";
		String URL3= "&multicity=true&from2=CMB&to2=DEL&depart_date_2=18/";
		String URL4 = "&from3=&to3=&depart_date_3=&adults=1&childs=0&infants=0&class=Economy&intl=y&num_legs=2";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
	 	alertCaptureAndFail(driver);
	 	}
  
	@Test
	public void AlertCom_Hotel() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1 = "http://qa2.cleartrip.com/hotels/results?city=";
		String URL2 = "&state=Karnataka&country=IN&area=&poi=&hotelId=&hotelName=&dest_code=32550&chk_in=06/";
		String URL3 = "&chk_out=17/";
		String URL4 = "&adults1=2&children1=0&num_rooms=1";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
	 	alertCaptureAndFail(driver);
  }
	@Test
	public void Alert_AE_Air_Oneway() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1= "http://qa2.cleartrip.ae/flights/results?from=";
		String URL2= "&to=MAA&depart_date=10/";
		String URL3= "&adults=1&childs=0&infants=0&class=Economy&class=First&airline=&carrier=&intl=n";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3;
		driver.get(URL);
	 	driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
  }
	
	@Test
	public void Alert_AE_Air_Roundtrip() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1 = "http://qa2.cleartrip.ae/flights/results?from=";
		String URL2 = "&to=MAA&depart_date=10/";
		String URL3 = "&return_date=11/";
		String URL4 = "&adults=1&childs=1&infants=1&class=Economy&airline=SpiceJet+%28SG%29&carrier=SG&intl=n";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
	 	driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
  }

	@Test
	public void Alert_AE_Air_Intl_Oneway() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1 = "http://qa2.cleartrip.ae/flights/international/results?from=";
		String URL2 = "&to=MAA&depart_date=10/";
		String URL3 = "&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=y";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3;
		driver.get(URL);
	 	driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
  }
	
	@Test	
	public void Alert_AE_Air_MultiCity() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1= "http://qa2.cleartrip.ae/flights/results?from1=";
		String URL2= "&to1=MAA&depart_date_1=17/";
		String URL3= "&multicity=true&from2=MAA&to2=DEL&depart_date_2=18/";
		String URL4 = "&from3=&to3=&depart_date_3=&adults=1&childs=0&infants=0&class=Economy&intl=n&num_legs=2";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
	 	driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
	 	}

	@Test
	public void Alert_AE_Air_Intl_MultiCity() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1= "http://qa2.cleartrip.ae/flights/international/results?from1=";
		String URL2= "&to1=CMB&depart_date_1=17/";
		String URL3= "&multicity=true&from2=CMB&to2=DEL&depart_date_2=18/";
		String URL4 = "&from3=&to3=&depart_date_3=&adults=1&childs=0&infants=0&class=Economy&intl=y&num_legs=2";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
	 	driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
	 	}
  
	@Test
	public void Alert_AE_Hotel() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1 = "http://qa2.cleartrip.ae/hotels/results?city=";
		String URL2 = "&state=Karnataka&country=IN&area=&poi=&hotelId=&hotelName=&dest_code=32550&chk_in=06/";
		String URL3 = "&chk_out=17/";
		String URL4 = "&adults1=2&children1=0&num_rooms=1";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
	 	alertCaptureAndFail(driver);
	}
	
	@Test
	public void AlertCom_Trains() throws Exception {
			driver.manage().deleteAllCookies();					
			String URL1 = "http://qa2.cleartrip.com/trains/results?from_city=";
			String URL2 = "&to_city=Mumbai&class=1A&date=24-";
			String URL3 = "&adults=1&children=0&male_seniors=0&female_seniors=0";
			String Month = getMonthTime(2, "MM");
			String Year = putYear(0);
			String Date = Month+"-"+Year;
			String URL = URL1+Alert_Script+URL2+Date+URL3;
			driver.get(URL);
			alertCaptureAndFail(driver);
		}
	
	@Test
	public void AlertCom_Packages() throws Exception {
			driver.manage().deleteAllCookies();					
			String URL1 = "http://qa2.cleartrip.com/packages/results?origin=";
			String URL2 = "%2C+IN+-+Bengaluru+International+Airport+%28BLR%29&from=BLR&city=Chennai&dest_code=33070&state=Tamil+Nadu&country=IN&depart_date=28/";
			String URL3 = "&chk_out=29/";
			String URL4 = "&num_rooms=1&adults1=1&adults=1&children1=0&childs=0&infants=0";
			String Month = getMonthTime(2, "MM");
			String Year = putYear(0);
			String Date = Month+"/"+Year;
			String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
			driver.get(URL);
			alertCaptureAndFail(driver);
		}
	
		@Test
		public void AlertCom_WeekendGetaways() throws Exception {
				driver.manage().deleteAllCookies();					
				String URL1 = "http://qa2.cleartrip.com/getaways/results?from=";
				String URL2 = "&state=Karnataka&country=IN&dest_code=&chk_in=23/";
				String URL3 = "&chk_out=24/";
				String URL4 = "&adults1=2&children1=0&num_rooms=1";
				String Month = getMonthTime(2, "MM");
				String Year = putYear(0);
				String Date = Month+"/"+Year;
				String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
				driver.get(URL);
				alertCaptureAndFail(driver);
			}
	
	@Test
	public void AlertCom_Waytogo() throws Exception {
			driver.manage().deleteAllCookies();					
			String URL1 = ".cleartrip.com/waytogo/results?from=";
			String URL2 = "&to=Mumbai-Maharashtra-India";
			String URL = URL1+Alert_Script+URL2;
			driver.get(URL);
			alertCaptureAndFail(driver);
		}
		
	

	@Test
	public void AlertAgency_Air_Oneway() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://qa2.agentbox.com");
		agency_SignIn(driver);
		String URL1 = "http://qa2.agentbox.com/flights/search?rnd_one=O&origin=";
		String URL2 = "%2C+IN+-+Bengaluru+International+Airport+%28BLR%29&from=BLR&destination=Chennai%2C+IN+-+Chennai+Airport+%28MAA%29&to=MAA&depart_date=18/";
		String URL3 = "&adults=1&children=0&infants=0&class=Economy&airline=&carrier=&dep_time=0";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3;
		driver.get(URL);
		alertCaptureAndFail(driver);
	  }
	  
	/*@Test
	public void AlertAgency_Air_Roundtrip() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://qa2.agentbox.com");
		agency_SignIn(driver);
		String URL1 = "http://qa2.agentbox.com/flights/search?rnd_one=O&origin=";
		String URL2 = "%2C+IN+-+Bengaluru+International+Airport+%28BLR%29&from=BLR&destination=Chennai%2C+IN+-+Chennai+Airport+%28MAA%29&to=MAA&depart_date=19/";
		String URL3 = "&return_date=20/";
		String URL4 = "&adults=1&children=0&infants=0&class=Economy&airline=&carrier=&dep_time=0&ret_time=0";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
		alertCaptureAndFail(driver);
		}*/

	@Test
	public void AlertAgency_Air_Intl_Oneway() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://qa2.agentbox.com");
		agency_SignIn(driver);
		String URL1 = "http://qa2.agentbox.com/flights/search?rnd_one=O&origin=";
		String URL2 = "%2C+IN+-+Bengaluru+International+Airport+%28BLR%29&from=BLR&destination=Colombo%2C+LK+-+Bandaranaike+%28CMB%29&to=CMB&depart_date=25/";
		String URL3 = "&adults=1&children=0&infants=0&class=Economy&airline=&carrier=&dep_time=0";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3;
		driver.get(URL);
		alertCaptureAndFail(driver);
		}

	@Test
	public void AlertAgency_Air_Intl_Roundtrip() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://qa2.agentbox.com");
		agency_SignIn(driver);
		String URL1 = "http://qa2.agentbox.com/flights/search?rnd_one=R&origin=";
		String URL2 = "%2C+LK+-+Bandaranaike+%28CMB%29&from=CMB&destination=Chennai%2C+IN+-A+Chennai+Airport+%28MAA%29&to=MAA&depart_date=19/";
		String URL3 = "&return_date=20/";
		String URL4 = "&adults=1&children=0&infants=0&class=Economy&airline=&carrier=&dep_time=0&ret_time=0";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);		
		alertCaptureAndFail(driver);
	  }
	
	public void corp_SignIn(RemoteWebDriver driver) throws Exception{
		elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 600);
		if (common.value("host").contains("qa2") ) {
			safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_QA2_Username"));
			safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_QA2_Password"));
		} else if(common.value("host").contains("www")) {
			safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Prod_Username"));
			safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Prod_Password"));
		} else if(common.value("host").contains("beta")) {
			safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Beta_Username"));
			safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Beta_Password"));
		} else if(common.value("host").contains("stg1")) {
			safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("Corp_Stg1_Username"));
			safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("Corp_Stg1_Password"));
		}
		safeClick(driver, getObject("CorpCom_SignIN_SignIN_Button"));
		textPresent(driver, "Search flights", 600);
	}	

	public void agency_SignIn(RemoteWebDriver driver) throws Exception{
		elementVisible(driver, getObject("AgencyCom_SignIN_EmailID"), 600);
		if (common.value("host").contains("qa2") ) {
			safeType(driver, getObject("AgencyCom_SignIN_EmailID"), dataFile.value("Agency_QA2_Username"));
			safeType(driver, getObject("AgencyCom_SignIN_Password"), dataFile.value("Agency_QA2_Password"));
		} else if(common.value("host").contains("www")) {
			safeType(driver, getObject("AgencyCom_SignIN_EmailID"), dataFile.value("Agency_Prod_Username"));
			safeType(driver, getObject("AgencyCom_SignIN_Password"), dataFile.value("Agency_Prod_Password"));
		} else if(common.value("host").contains("beta")) {
			safeType(driver, getObject("AgencyCom_SignIN_EmailID"), dataFile.value("Agency_Beta_Username"));
			safeType(driver, getObject("AgencyCom_SignIN_Password"), dataFile.value("Agency_Beta_Password"));
		} else if(common.value("host").contains("stg1")) {
			safeType(driver, getObject("AgencyCom_SignIN_EmailID"), dataFile.value("Agency_Stg1_Username"));
			safeType(driver, getObject("AgencyCom_SignIN_Password"), dataFile.value("Agency_Stg1_Password"));
		}
		safeClick(driver, getObject("AgencyCom_SignIN_Button"));
		textPresent(driver, "Search international and domestic flights", 600);
	}
	
	/*
	@Test
	public void AlertCorp_Air_Oneway() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://automationqa.cleartripforbusiness.com");
		corp_SignIn(driver);
		String URL1 = "https://automationqa.cleartripforbusiness.com/flights/results?from=";
		String URL2 = "&to=MAA&depart_date=27/";
		String URL3 = "&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=n";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3;
		driver.get(URL);
		Thread.sleep(10000);
		alertCaptureAndFail(driver);
  }

	@Test
	public void AlertCorp_Air_Roundtrip() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://automationqa.cleartripforbusiness.com");
		corp_SignIn(driver);
		String URL1 = "https://automationqa.cleartripforbusiness.com/flights/results?from=";
		String URL2 = "&to=MAA&depart_date=27/";
		String URL3 = "&return_date=28/";
		String URL4 = "&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=n";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
		Thread.sleep(10000);
		alertCaptureAndFail(driver);
	}
	
	@Test
	public void AlertCorp_Air_MuitiCity() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://automationqa.cleartripforbusiness.com");
		corp_SignIn(driver);
		String URL1 = "https://automationqa.cleartripforbusiness.com/flights/results?from1=";
		String URL2 = "&to1=MAA&depart_date_1=23/";
		String URL3 = "&multicity=true&from2=MAA&to2=DEL&depart_date_2=24/";
		String URL4 = "&from3=&to3=&depart_date_3=&adults=1&childs=0&infants=0&class=Economy&intl=n&num_legs=2";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
		Thread.sleep(10000);
		alertCaptureAndFail(driver);
		
		}
	
	@Test
	public void AlertCorp_Air_Intl_Oneway() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://automationqa.cleartripforbusiness.com");
		corp_SignIn(driver);
		String URL1 = "http://automationqa.cleartripforbusiness.com/flights/international/results?from=";
		String URL2 = "&to=MAA&depart_date=04/";
		String URL3 = "&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=y";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3;
		driver.get(URL);
		Thread.sleep(10000);
		alertCaptureAndFail(driver);
	}

	@Test
	public void AlertCorp_Air_Intl_Roundtrip() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://automationqa.cleartripforbusiness.com");
		corp_SignIn(driver);
		String URL1 = "http://automationqa.cleartripforbusiness.com/flights/international/results?from=";
		String URL2 = "&to=MAA&depart_date=04/";
		String URL3 = "&return_date=05/";
		String URL4 = "&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=y";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
		Thread.sleep(10000);
		alertCaptureAndFail(driver);
		}
		
		@Test
	public void AlertCorp_Hotel() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://automationqa.cleartripforbusiness.com");
		corp_SignIn(driver);
		String URL1 = "http://automationqa.cleartripforbusiness.com/hotels/results?city=";
		String URL2 = "&state=Karnataka&country=IN&area=&poi=&hotelId=&hotelName=&dest_code=32550&chk_in=20/";
		String URL3 = "&chk_out=21/";
		String URL4 = "&adults1=1&children1=0&num_rooms=1";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
		Thread.sleep(10000);
		alertCaptureAndFail(driver);
		}
	
	@Test
	public void AlertCorp_Air_Intl_MuitiCity() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://automationqa.cleartripforbusiness.com");
		corp_SignIn(driver);
		String URL1 = "http://automationqa.cleartripforbusiness.com/flights/international/results?from1=";
		String URL2 = "&to1=CMB&depart_date_1=10/";
		String URL3 = "&multicity=true&from2=CMB&to2=MEL&depart_date_2=11/";
		String URL4 = "&from3=&to3=&depart_date_3=&adults=1&childs=0&infants=0&class=Economy&intl=y&num_legs=5";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
		Thread.sleep(10000);
		alertCaptureAndFail(driver);
		}
		
	@Test
	public void Alert_AE_Air_Intl_Roundtrip() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1 = "http://qa2.cleartrip.ae/flights/international/results?from=";
		String URL2 = "&to=MAA&depart_date=10/";
		String URL3 = "&return_date=11/";
		String URL4 = "&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=y";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		driver.get(URL);
	 	driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
  } 
  		
	@Test
	public void AlertCom_Air_Intl_Roundtrip() throws Exception {
		driver.manage().deleteAllCookies();
		String URL1 = "http://qa2.cleartrip.com/flights/international/results?from=";
		String URL2 = "&to=MAA&depart_date=10/";
		String URL3 = "&return_date=11/";
		String URL4 = "&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=y";
		String Month = getMonthTime(2, "MM");
		String Year = putYear(0);
		String Date = Month+"/"+Year;
		String URL = URL1+Alert_Script+URL2+Date+URL3+Date+URL4;
		
		driver.get(URL);
	 	driver.navigate().refresh();
	 	alertCaptureAndFail(driver);
  	}*/		
	
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	
  }

 @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}