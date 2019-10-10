package testScriptsAmexWhiteLabel;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class Air_OW_AmexWhiteLabel_MinAmountChargeCheck extends AirCommonMethod{

	
	public RemoteWebDriver driver = null;
	boolean flightCountFailure = true;
	int attempt = 0;
	boolean bookingPassed = false;
	boolean ExpPayment = false;
	boolean GSTbooking = false;

	
	@DataProvider(name = "AirOneWayDomestic")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"DEL","MAA"};
		String[] destination = {"BOM","BLR"};
		return new Object[][] { { origin,destination,"","1","0","0", "CC",false,"lcc"} };
	}

	
	@Test(dataProvider = "AirOneWayDomestic")
	public void test_Air_OW_AmexWhiteLabel_MinAmountChargeCheck_36471(String[] origin, String[] destin,String flightPreference,String adults, String children,
			String infants,String Payment_Options, boolean insuranceRequired,String flight_type)
			throws Exception {
		
		
		do {
			System.out.println(baseUrl);
		driver.get(baseUrl);
		
		
		
		
		
		
		
		
		
		//elementPresent(driver, By.cssSelector("h1"), 10);
		if (elementClickable(driver, getObject("Expedia_HomePage_From_TextBox"), 20)){
			Reporter.log("Homepage loaded", true);
			
		}else {
			refreshPage(driver);
			elementClickable(driver, getObject("Expedia_HomePage_From_TextBox"), 20);
		}
		//System.out.println("expedia home page displayed");
		AmexOnewaySearch(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference);// TODO:add more parameters
		Reporter.log(driver.getCurrentUrl(), true);
		
		if (elementPresent_Time(driver,getObject("Expedia_SRP_Dom_OW_BookButton") , 20)){
			Reporter.log("Results Displayed", true);
			//System.out.println("Results Displayed");
			
		}else{
			driver.navigate().refresh();
			elementPresent_Time(driver,getObject("Expedia_SRP_Dom_OW_BookButton") , 20);
			Reporter.log("Results Page Refreshed", true);
			//System.out.println("Results Page Refreshed");
			
		}
		
		flightCountFailure = ExpediaFlightsCount(driver);
		if (!flightCountFailure) {
			Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
			//System.out.println("No results found for this search. Making another attempt with different sectors.");
			attempt++;
        	continue;
    		
		}
		
		ExpediaFlightsFiletrByLCCOrGDS(driver, flight_type, 0);
		safeClick(driver, getObject("Expedia_SRP_Dom_OW_BookButton"));
	
		
		ExpAir_ItineraryPage(driver);
		AmexAir_TravellersEmail(driver);
		Thread.sleep(15000);
		elementPresent_Time(driver, By.xpath("//div[@id='itinerary']/dl/dd"), 120);
		expediaTravellerDetailsDom(driver,adults, children, infants, GSTbooking);
		
		safeClick(driver,getObject("Expediaair_step4_creditCard"));
       // elementVisible(driver, getObject("ExpediaAir_step4_ccmaster"), 300);

       applyCoupon(driver,"owfull");
        ExpPayment = ExpAir_Paymentpage(driver, "AMEX");
        if (!ExpPayment) {
        	Reporter.log("Expedia Payment Page Not Displayed, attempting next search", true);
			//System.out.println("Expedia Payment Page Not Displayed, attempting next search");
			attempt++;
        	continue;
    		
		}
		
        
		
		bookingPassed = ExpAir_Confirmation(driver);
		Thread.sleep(20000);
		if (bookingPassed) {
			Reporter.log("Test case " + this.getClass() + " passed successfully", true);
		}
		} while (!bookingPassed && attempt < 3);
			assertTrue("Booking failed after 3 attempts", ((attempt < 3) && (bookingPassed)));
		
			
	}
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		//baseUrl = common.value("Air_WhiteLabel_Amex_url");
		baseUrl = getAmexWlUrl();
	}

		
/*	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = Firefox_Config(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = common.value("air_expedia_url");
		
				
	}*/

@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}


}
