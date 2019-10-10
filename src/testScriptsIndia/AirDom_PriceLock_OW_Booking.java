package testScriptsIndia;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

import domainServices.AirCommonMethod;

public class AirDom_PriceLock_OW_Booking extends AirCommonMethod{

	public RemoteWebDriver driver = null;
	String tripId = null;
	String domain = "com";
	HashMap<String, Double> hp = new HashMap<String, Double>();

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) { 
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}


	@Test(dataProvider = "B2CAirPricelock")
	public void Air_PriceLock_OW_Booking(String[] origin, String[] destination, String tripType, String flight_type,
			String[] flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				if (!checkIfSignedIn(driver)) {
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
			}

			airCom_HomepageSearch_Oneway_Pricelock(driver,origin[attempt],destination[attempt],
					adults, children, infants, flightPreference[attempt], attempt);

			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true); // capture itinerary

			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));

			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				attempt++;
				continue;
			}

			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt", true);
				attempt++;
				continue;
			}


			safeClick(driver, getObject("AirCom_SRP_Oneway_BookButton"));
			Reporter.log("Itinerary URL : " + driver.getCurrentUrl(), true);
			
			insuranceBlockProcess(driver, insuranceRequired); // to uncheck Insurance if pre-selected

			
			// If pricelock option not found refresh url and check 
			boolean gotLock= false;
			int priceLockAttempt = 0;
			while(!gotLock && priceLockAttempt < 3){
				if (elementPresent(driver, getObject("AirCom_BookStep_PriceLock_rBtn"))){
					safeClick(driver, getObject("AirCom_BookStep_PriceLock_rBtn"));
					gotLock=true;
				}
				else{
					Reporter.log("refreshing page to make avail_lock call again...");
					System.out.println("refreshing page to make avail_lock call again...");
					driver.navigate().refresh();
					Thread.sleep(5000);
				}
				priceLockAttempt++;
			}
			if (!gotLock) {
				attempt++;
				continue;
			}

			priceLockAmountAssertionInBookStep(driver, hp); // assert pricelock amounts in bookstep-1

			Double bookStepLockfee = hp.get("lockfee");
			Double bookStepBalance = hp.get("balance");

			safeClick(driver, getObject("air_review_itinerary_continue"));

			travellerDetails(driver, adults, children, infants, false, false, false);

			Boolean reachedPaymentStep = airconditionWatcher(driver);

			if (reachedPaymentStep) {
				Thread.sleep(2000);
				String pgFeeString = getText(driver, getObject("AirCom_BookStep_PriceLock_ProccessingFee"));
				String lockfeeInPayBlockString = getText(driver, getObject("AirCom_BookStep_PriceLock_PaymentBlock_lockfee"));
				String balanceInPayBlockString = getText(driver, getObject("AirCom_BookStep_PriceLock_Balance_WithPGfee"));

				lockfeeInPayBlockString=lockfeeInPayBlockString.replaceAll(",","");
				balanceInPayBlockString=balanceInPayBlockString.replaceAll(",","");
				pgFeeString=pgFeeString.replaceAll("Rs.","");

				Double lockfeeInPayBlock = Double.parseDouble(lockfeeInPayBlockString);
				Double balanceInPayBlock = Double.parseDouble(balanceInPayBlockString);
				Double pgFee = Double.parseDouble(pgFeeString);

				Assert.assertEquals(bookStepLockfee, lockfeeInPayBlock,"Mismatch in Lock Fee Amounts in 'Booking options' and 'Total Block' in Payment Step");
				Assert.assertEquals((bookStepBalance + pgFee), balanceInPayBlock,"Mismatch in Lock Fee Amounts in 'Booking options' and 'Total Block' in Payment Step");
				Reporter.log("Verified Lock Fee and Pay Balance Amounts in 'Payment Page' ");

				paymentDone = b2cPayment(driver, paymentMethod, 1);
				if (paymentDone == true)
					recheckAirlinePrice(driver);
				else if (!(common.value("makePayment").equals("true")))
				{
					Reporter.log("Make Payment Flag is set to True. Hence Ignoring payment and setting booking passed as true.",true);
					bookingPassed = true;
					break;
				}
				else if (paymentDone == false) {
					attempt++;
					Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
					continue;
				}
			} else {
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
				continue;
			}
			attempt++;
			bookingPassed = checkBookingStatus(driver);

			Reporter.log("Pricelock 1st Payment done: "+driver.getCurrentUrl());
			System.out.println("Pricelock 1st Payment done: "+driver.getCurrentUrl());

			assertTrue("You can pay through your 'Cleartrip Account' link Not Present", elementPresent(driver, getObject("AirCom_ConfirmationPage_PriceLock_CTAccount_Link")));
			safeClick(driver, getObject("AirCom_ConfirmationPage_PriceLock_CTAccount_Link"));
			waitElement(driver, getObject("Acc_Air_PriceLock_MakePayment_btn"), 50);
			WebElement bStatus = driver.findElement(By.cssSelector("span.status.caution"));
			String BookStatus = bStatus.getText();
			//System.out.println("checkThis"+ bStatus.getText());
			//String BookStatus = getText(driver, getObject("Acc_Air_PriceLock_Payment_status"));
			System.out.println("Accounts Page URL:"+ driver.getCurrentUrl());
			
			assertTrue("Status of the booking is NOT in HOLD", BookStatus.contains("HOLD"));

			safeClick(driver, getObject("Acc_Air_PriceLock_MakePayment_btn"));
			waitElement(driver, getObject("AirCom_Payment_PriceLock_PaymentPage"), 60);
			textAssert(driver, getObject("AirCom_Payment_PriceLock_PaymentPage"), "Pay to confirm");

			/*			Can be used for payment re-try
			safeClick(driver, getObject("AirCom_BookStep4_NB_Tab"));
			PaymentFlexiRetry(driver); */

			b2cPayment(driver, paymentMethod, 1);

			bookingPassed = checkTripID(driver);
			System.out.println("attempt count="+attempt);

		} while (!bookingPassed && attempt < 3);

		Reporter.log("PriceLock Booking 2nd payment Done " + getText(driver, getObject("AirCom_Confirmation_TripID")));
		//System.out.println("PriceLock Booking is Done " + getText(driver, getObject("AirCom_Confirmation_TripID")));

	}
	
	
	@DataProvider(name = "B2CAirPricelock")
	public static Object[][] B2CAirOWPricelock() {
		String[] origin = { "blr", "bom"};
		String[] destination = {"bom","blr"};
		String[] airline = {"SpiceJet","Vistara"};
		return new Object[][] { { origin, destination, "OneWay", "lcc", airline , "Direct", "1", "0", "0",
			"credit card", false}};
	}


	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		//System.out.println("Test Case:" + _result.getMethod().getMethodName());
	}
}
