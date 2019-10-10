package testScriptsIndia;



import static org.testng.AssertJUnit.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//import org.jboss.netty.handler.codec.http.multipart.HttpPostRequestDecoder.NotEnoughDataDecoderException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;
import dataServices.HQDataProvider;
import domainServices.HQ;
import domainServices.AirCommonMethod;
/*
TestCase Description: FlexiPay booking,On second booking payment retry BLR- DEL - 
SG (select show only refundable adn then select flight), OW with 1 pax , 2nd apy pay retry 
*/


public class AirDom_FlexiPay_RT_1stPayRetry_MultiPax_Booking extends AirCommonMethod {
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@Test(dataProvider = "B2CAirRT")
	public void Air_RT_FlexiPay_1stPayRetry_Booking(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, String flightSegments, boolean insuranceRequired, String refundMethod, boolean sector,
			String flight_type) throws Exception {

			
		boolean bookingPassed = false;
		boolean SecondPayment = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		
		
				
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			// safeClick(driver, getObject("AirSideAppButtonXPath"));
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants,
					flightPreference,attempt);
			
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			getRefundableFlightsOnly(driver);
			waitElement(driver, getObject("AirCom_OW_SRP_Flights_ShowAllLink"), 5);
			clickRoundTripBookButton(driver);

			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
				try{		
			safeClick(driver, getObject("AirCom_BookStep_FlexiPay_rBtn"));	
				}
				catch(Exception e){
					attempt++;
					Reporter.log("price Lock is not Available: " + attempt);
					continue;
				}
			String flexiAmount = getText(driver, getObject("AirCom_BookStep_FlexiPay_Amount"));
			//System.out.println("Flexi Pay Amount On Book Step: "+flexiAmount);
			
			
			insuranceBlock(driver, insuranceRequired);
				
				
			travellerDetails(driver, adults, children, infants, false, false, false);
				
				Boolean reachedPaymentStep = airconditionWatcher(driver);
				if (reachedPaymentStep) {
					
					String flexyAmountPaymentPage = getText(driver, getObject("AirCom_BookStep4_FlexyAmount"));
					//System.out.println("Flexi Pay Amount On Payment Step: "+flexyAmountPaymentPage);
					
					assertEquals("Mismatch of Flexi Amount on BS to PP", flexiAmount, flexyAmountPaymentPage);
					//System.out.println("Flexi AMount on BookStep and Payment Are Same");
					
					safeClick(driver, getObject("AirCom_BookStep4_NB_Tab"));
					
					PaymentRetry(driver, "NB");
					Reporter.log("First Payment Retry Done");
					//System.out.println("First Payment Retry Done");
									
					//First Payment
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					if (paymentDone == true)
						recheckAirlinePrice(driver);
					else if (!(common.value("makePayment").equals("true")))
						break;
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
				bookingPassed = checkTripID(driver);
				
				//Flexi Booking
				Reporter.log("Flexi Pay Booking is Inprogress");
				//System.out.println("Flexi Pay Booking is Inprogress");
				
				assertTrue("FlexiPay Cleartrip Account Link is Not Present", elementPresent(driver, getObject("AirCom_ConfirmationPage_FlexiPay_CTAccount_Link")));
				safeClick(driver, getObject("AirCom_ConfirmationPage_FlexiPay_CTAccount_Link"));
				waitElement(driver, getObject("Acc_Air_FlexiPay_MakePayment_btn"), 50);
				String BookStatus = getText(driver, getObject("Acc_Air_FlexiPay_Payment_status"));
				//System.out.println("Accounts Page URL:"+ driver.getCurrentUrl());
				//System.out.println("Flexi Pay Booking Status from Accounts "+ BookStatus);
				assertTrue("Status of the booking is NOT in HOLD", BookStatus.equalsIgnoreCase("ON HOLD"));
																						
				
				safeClick(driver, getObject("Acc_Air_FlexiPay_MakePayment_btn"));
				textPresent(driver, "Pay to confirm", 15);
				textAssert(driver, getObject("AirCom_Payment_FlexiPay_PaymentPage"), "Pay to confirm");
				
				//System.out.println("Making Second Payment");
				//second payment
				Boolean reachedPaymentStep1 = airconditionWatcher(driver);
				if (reachedPaymentStep1) {
					if ((common.value("makePayment").equals("true"))) {
						paymentDone = b2cPayment(driver, paymentMethod, 1);
						boolean error = false;
						if (paymentDone == true)
							error = recheckAirlinePrice(driver, "testFlag");//workaround
						else if (paymentDone == false) {
							attempt++;
							Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
							continue;
						}
						if (error) {
							attempt++;
							continue;
						}
					} else {
						bookingPassed = true;
						break;
					}
				} else {
					
					attempt++;
					Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
					continue;
				}
				attempt++;
				bookingPassed = checkTripID(driver);
				
			} while (!bookingPassed && attempt < 3);
			assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
			
		/*
				b2cPayment(driver, paymentMethod, 1);
				
			
			SecondPayment = checkBookingStatus(driver);
			
			
					
			} while (!SecondPayment && attempt < 3);
		 	assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (SecondPayment)));
			Reporter.log("FlexiPay Booking is Done " + getText(driver, getObject("AirCom_Confirmation_TripID")));
			//System.out.println("FlexiPay Booking is Done " + getText(driver, getObject("AirCom_Confirmation_TripID")));
			
		*/
			
			
			
		}
		
	@DataProvider(name = "B2CAirRT")
	public static Object[][] B2CAirRTGDSDomViaParPaxAutoRefund() {
		String[] origin = {"del","del"};
		String[] destination = {"blr","bom"};
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "SpiceJet", "Non Direct", "1", "1", "0",
				"credit card", "Via", false, "Auto Refund", false, "lcc" } };
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



