


	package testScriptsAgency;

	import static org.testng.Assert.assertTrue;

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

	public class AgencyAir_DOM_GDS_ConnectorSpecific_Airlines_Check extends Agency{

		public RemoteWebDriver driver;
		

		@Test (dataProvider = "B2CAirOWLCC")  
		public void AgencyComCoupon(String[] origin, String[] destin, String app, String tripType, String airlines,String adults, String children, String infants,String flightPreference) throws Exception {
			String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
			System.out.println("onward date="+onwarddate);
			
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			int attempt = 0;
			driver.get(Agency_Url());	 
			 driver.get(driver.getCurrentUrl()+"/flights/international/results?from="+origin[attempt]+"&to="+destin[attempt]+"&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airlines="+airlines);
			 Reporter.log("Search URL is : " + driver.getCurrentUrl());
				
				
				flightCountFailure = checkFlightsCount(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with different sectors.");
					
				}
				
				assertTrue(elementVisible(driver, getObject("AirCom_SRP_Oneway_BookButton"), 1),flightPreference+ ":" +"Airline Not Available");
					
				Reporter.log(flightPreference + ":" + "Availability Check" + " Completed",true);	
			  	
		  	}

		@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		 driver.get(Agency_Url());
		  agency_SignIn(driver);
		 
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
					
					{ originSG, destinationSG, "Flights", "OneWay", "AI", "1", "0", "0","Air India"},
					{ originSG, destinationSG, "Flights", "OneWay", "9w", "1", "0", "0","JetAirways"},
					{ originSG, destinationSG, "Flights", "OneWay", "FZ", "1", "0", "0","Vistra"},
					
				
					
					
			};
		}
		

	}




