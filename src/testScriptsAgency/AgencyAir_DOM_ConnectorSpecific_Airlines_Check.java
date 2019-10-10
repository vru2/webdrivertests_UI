
	package testScriptsAgency;

	import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

	import dataServices.AgencyDataProvider;
import domainServices.Agency;

	public class AgencyAir_DOM_ConnectorSpecific_Airlines_Check extends Agency{

		public RemoteWebDriver driver;
		int tosign=0;

		@Test (dataProvider = "B2CAirOWLCC")  
		public void AgencyComCoupon(String[] origin, String[] destin, String app, String tripType, String airlines,String adults, String children, String infants,String flightPreference) throws Exception {
			String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
			System.out.println("onward date="+onwarddate);
			
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			int attempt = 0;
			//driver.manage().deleteAllCookies();
			  driver.get(Agency_Url());
			  if(tosign==0){
			  agency_SignIn(driver);
			  }
			 
			  
//			 agencyAir_Oneway_Search(driver, FromCity, ToCity, From_Date, Adults, Childrens, Infants, Pref_Airline);
			 Thread.sleep(1000);
			 driver.get("https://manit.agentbox.com/flights/international/results?from="+origin[attempt]+"&to="+destin[attempt]+"&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline=SpiceJet+(SG)&carrier=SG&intl=y&sd=1535011788144&nearbyairportsOrigin=JAI-DEL&nearbyairportsDest=AUH-XNB-ZVJ-DXB&fareoptions=WITHBAG-WITHOUTBAG&airlines="+airlines+"&tripDuration=3-33&layoverDuration=0-19");
			 
			  tosign++;			
		  	}

		@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		}

		@AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}

		@AfterClass
		public void tearDown() throws Exception {
		//browserClose(driver);
		}
		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			
			String origin6E [] = {"DEL", "BOM", "CCU"};
			String destination6E [] = {"BLR", "GOA", "BLR"};
			
			String originSG [] = {"DEL", "MAA", "CCU"};
			String destinationSG [] = {"BOM", "BLR", "MAA"};
			
			return new Object[][] { 
					
					{ origin6E, destination6E, "Flights", "OneWay", "6E", "1", "0", "0","IndiGo"},
					{ originSG, destinationSG, "Flights", "OneWay", "SG", "1", "0", "0","SpiceJet"},
					{ originSG, destinationSG, "Flights", "OneWay", "G8", "1", "0", "0","GoAir"},
					{ originSG, destinationSG, "Flights", "OneWay", "AI", "1", "0", "0","Air India"},
					{ originSG, destinationSG, "Flights", "OneWay", "9w", "1", "0", "0","JetAirways"},
					{ originSG, destinationSG, "Flights", "OneWay", "FZ", "1", "0", "0","Vistra"},
					/*{ originSG, destinationSG, "Flights", "OneWay", "G9", "1", "0", "0","Air Arabia"},
					{ originSG, destinationSG, "Flights", "OneWay", "EK", "1", "0", "0","Emirates"},
					{ originSG, destinationSG, "Flights", "OneWay", "G9", "1", "0", "0","Air IndiaExpress"},
					{ originSG, destinationSG, "Flights", "OneWay", "EK", "1", "0", "0","GulfAir"}*/
					
				
					
					
			};
		}
		

	}


