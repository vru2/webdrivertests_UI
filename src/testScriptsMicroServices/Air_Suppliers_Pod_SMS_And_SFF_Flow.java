package testScriptsMicroServices;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class Air_Suppliers_Pod_SMS_And_SFF_Flow extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	String domain = common.value("domain");
	String itineraryId = "";
	boolean bookingPassed = false;
	boolean paymentDone = false;

	@BeforeClass
	public void startSelenium() throws Exception {
		
		this.driver = getDriver(driver);
		if (driver == null) {
			addLog("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
		addLog("Base URL " + baseUrl,true);
	}

	@Parameters({ "origin", "destination","destination1", "airline", "paxConfig", "ssrConfig", "paymentMethod", "onwardDate",
			"returnDate", "suppliers", "module", "IsFliteTypeInternational", "tripType","splrtAirline" })
	@Test
	public void air_Suppliers_Pod_SMS_And_SFF_Bookings(String origin, String destination,@Optional("") String destination1, @Optional("") String airline,
			String paxConfig, String ssrConfig, String paymentMethod, String onwarddate,@Optional("") String returndate,
			@Optional("") String suppliers, @Optional("") String module, boolean IsFliteTypeInternational,String tripType,@Optional("") String splrtAirline) throws Exception {

			verifySearchResultsByConnectorSearchAndLoadSRP(driver,baseUrl,IsFliteTypeInternational, origin, destination,destination1, onwarddate, returndate, paxConfig,
					airline, suppliers,tripType,paymentMethod);
			
			//selectGivenAirliesForGivenSector(origin.toUpperCase(),destination.toUpperCase(),destination1.toUpperCase());
			itineraryId = clickOnBookButton(driver,tripType,splrtAirline);
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				addLog("Redirected back to SRP after clicking on book button. Making another attempt" );
			}

			process_SSR(driver, ssrConfig,tripType);

			processTravellerDetails(driver, paxConfig, IsFliteTypeInternational, false, false);

			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				addLog("Booking flow reached to payment step",true);
				bookingPassed=processPaymentFlow(driver,paymentMethod,bookingPassed);
			} else {
				addLog("Booking flow did not reach to payment steps",true);
			}
		
		if(common.value("makePayment").toLowerCase().trim().equals("true")) {
			Assert.assertTrue(bookingPassed,"Booking failed");
		}
		if (module.equalsIgnoreCase("sms")) {
			verify_SMS_Flow(driver,bookingPassed,itineraryId,paxConfig,ssrConfig);
		}
		else if (module.equalsIgnoreCase("sff")) {
			
			//checks stats for "Trip going into sff flow" and
			//calculate pax
			String paxArray[] = paxConfig.split("-");
			int finalPaxCount =0;
			for (String pax : paxArray) {
				finalPaxCount += Integer.valueOf(pax);
			}
			verify_SFF_Flow(driver,bookingPassed,itineraryId,finalPaxCount,IsFliteTypeInternational,tripType);
			
			
		}
	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
//		driver.close();
//		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}