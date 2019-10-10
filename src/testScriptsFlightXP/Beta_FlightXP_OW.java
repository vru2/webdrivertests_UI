package testScriptsFlightXP;

//import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
//import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;
public class Beta_FlightXP_OW extends AirCommonMethod{
	
	public RemoteWebDriver driver = null;
	boolean flightCountFailure = true;
	int attempt = 0;
	boolean bookingPassed = false;
	boolean ExpPayment = false;
	

	
	@DataProvider(name = "AirOneWayDomestic")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"DEL","MAA"};
		String[] destination = {"BOM","BLR"};
		return new Object[][] { { origin,destination,"","1","0","0", "CC",false,"lcc"} };
	}

	
	@Test(dataProvider = "AirOneWayDomestic")
	public void test_FlightXP_OW_36474(String[] origin, String[] destin,String flightPreference,String adults, String children,
			String infants,String Payment_Options, boolean insuranceRequired,String flight_type)
			throws Exception {
		
		/*
		do {
			*/
		driver.get(baseUrl);
		System.out.println(baseUrl);
		
		elementPresent_Time(driver, By.cssSelector("h1"), 10);
		if (elementClickable(driver, getObject("FlightXP_HomePage_From_TextBox_Prod"), 20)){
			Reporter.log("Homepage loaded", true);
			
		}else {
			refreshPage(driver);
			elementClickable(driver, getObject("FlightXP_HomePage_From_TextBox_Prod"), 20);
		}

		flightXP_OW_Search_Prod(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference);
		System.out.println(driver.getCurrentUrl());
		
		if (elementPresent_Time(driver,getObject("FlightXP_SRP_Dom_OW_BookButton") , 20)){
			Reporter.log("Results Displayed");
			//System.out.println("Results Displayed");
			
		}else{
			driver.navigate().refresh();
			elementPresent_Time(driver,getObject("FlightXP_SRP_Dom_OW_BookButton") , 20);
			Reporter.log("Results Page Refreshed");
			//System.out.println("Results Page Refreshed");
			
		}
		
		flightCountFailure = ExpediaFlightsCount(driver);
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.");
			//System.out.println("No results found for this search. Making another attempt with different sectors.");
			attempt++;
        	//continue;
    		
		}
		
		FlightXpFlightsFiletrByLCCOrGDS(driver, flight_type, 0);
		safeClick(driver, getObject("FlightXP_SRP_Dom_OW_BookButton"));
	
		
		FlightXpAir_ItineraryPage(driver);
		
		FlightXpAir_TravellerStep(driver);
		Thread.sleep(15000);
		
		elementPresent_Time(driver, By.xpath("//div[@id='itinerary']/dl/dd"), 120);
		flightXPStepTwoTravellerDom(driver,adults, children, infants);
		
		
		if(elementVisible(driver, getObject("FlightXp_payment"), 300))
		{
			Reporter.log("Reached Payment Page!", true);
			safeType(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Number"),  dataFile.value("Master_CC_Number"));
			safeClick(driver, getObject("FlightXp_exp_month_select"));
			safeClick(driver, getObject("FlightXp_exp_month"));
			safeClick(driver, getObject("FlightXp_exp_year_select"));
			safeClick(driver, getObject("FlightXp_exp_year"));			
			safeType(driver, getObject("FlightXp_cvv"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("FlightXp_cc_name"), dataFile.value("MasterCard_HolderName"));
			Thread.sleep(2000);
			
			if(common.value("makePayment").equals("true"))
			{
				safeClick(driver, getObject("FlightXp_cc_makePay_button"));
				Thread.sleep(15000);
				if (elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong)) 
				{
					safeClick(driver, getObject("air_amex_payment_button"));
					Reporter.log("Interstital Page.",true);
					Thread.sleep(10000);
		
				}
				assertCommon(driver, "Great, your booking went through successfully.", 180, "Confirmation page not displayed");
				bookingPassed = FltXpAir_Confirmation(driver);
			}
			else
			{
				Reporter.log("Make Payment is set to False, hence not attempting payment!", true);
				bookingPassed = true;
			}
		
		}
		
		if (bookingPassed) {
			Reporter.log("Test case " + this.getClass() + " passed successfully", true);
			
		}
       }
	
	
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		if(common.value("host").equalsIgnoreCase("qa2"))
			baseUrl = dataFile.value("FlightXP_QA2");
		else if(common.value("host").equalsIgnoreCase("www"))
			baseUrl = dataFile.value("FlightXP_PROD");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}

}
