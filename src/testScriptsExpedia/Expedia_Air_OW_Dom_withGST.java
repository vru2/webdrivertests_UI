package testScriptsExpedia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import static org.testng.AssertJUnit.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.HQDataProvider;
import dataServices.IndiaDataProvider;
import domainServices.AirCommonMethod;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

//import com.thoughtworks.selenium.Wait.WaitTimedOutException;

import dataServices.IndiaDataProvider;

/*EBL-6618 : Expedia_Air_OW_Dom_withGST */
public class Expedia_Air_OW_Dom_withGST extends AirCommonMethod{
	
	public RemoteWebDriver driver = null;
	boolean flightCountFailure = true;
	int attempt = 0;
	boolean bookingPassed = false;
	boolean ExpPayment = false;
	boolean GSTbooking = true;
	HashMap<String, String> gstDetails = new HashMap<String, String>();
	
	
	@DataProvider(name = "AirOneWayDomestic")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"DEL","MAA"};
		String[] destination = {"BOM","BLR"};
		return new Object[][] { { origin,destination,"","1","0","0", "CC",false,"lcc"} };
	}

	
	@Test(dataProvider = "AirOneWayDomestic")
	public void test_Expedia_air_dom_OW_GST_32993(String[] origin, String[] destin,String flightPreference,String adults, String children,
			String infants,String Payment_Options, boolean insuranceRequired,String flight_type)
			throws Exception {
		
		
		do {
			
		driver.get(baseUrl);
		System.out.println(baseUrl);
		
		elementPresent_Time(driver, By.cssSelector("h1"), 10);
		if (elementClickable(driver, getObject("Expedia_HomePage_From_TextBox"), 20)){
			Reporter.log("Homepage loaded", true);
			
		}else {
			refreshPage(driver);
			elementClickable(driver, getObject("Expedia_HomePage_From_TextBox"), 20);
		}
		//System.out.println("expedia home page displayed");
		ExpediaOnewaySearch(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference);// TODO:add more parameters
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
		ExpAir_TravellersEmail(driver);
		Thread.sleep(15000);
		elementPresent_Time(driver, By.xpath("//div[@id='itinerary']/dl/dd"), 120);
		
		expediaTravellerDetailsDom(driver,adults, children, infants, GSTbooking);
		
		safeClick(driver,getObject("Expediaair_step4_creditCard"));
        elementVisible(driver, getObject("ExpediaAir_step4_ccmaster"), 300);


        ExpPayment = ExpAir_Paymentpage(driver, "CC");
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
			
			String tripId = getText(driver, By.cssSelector("strong"));
			gstDetails = getGstDataFromTripXML(driver, tripId, false);
			
			Assert.assertEquals(gstDetails.get("gstNumber"), "27AAAAA0000A1Z1", "Expected GST number not found!");
			Assert.assertEquals(gstDetails.get("gstHolderName"), "Test User", "Expected GST holder name not found!");
			Assert.assertEquals(gstDetails.get("gstHolderAddress"), "123,Test Street,Test Area,India", "Expected no GST holder address, but found!");
			Assert.assertEquals(gstDetails.get("gstHolderStateName"), "Maharashtra", "Expected GST state as Maharashtra, but found: " + gstDetails.get("gstHolderStateName"));
			Assert.assertEquals(gstDetails.get("gstHolderStateCode"), "27", "Expected GST state code as 27, but found: " + gstDetails.get("gstHolderStateCode"));
			
			Reporter.log("Airline: " + flightPreference + " Attempt: " + attempt + " TEST COMPLETED!", true);
	}
	
	
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = common.value("air_expedia_url");
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
