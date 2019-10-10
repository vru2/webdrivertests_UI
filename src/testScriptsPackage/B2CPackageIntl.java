package testScriptsPackage;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.HQDataProvider;
import domainServices.AirCommonMethod;

public class B2CPackageIntl extends AirCommonMethod {

	public RemoteWebDriver driver = null;
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

	/*
	 * @DataProvider(name = "B2CPackageIntl") public static Object[][] B2CPackageIntl() { String[] origin = {"del", "blr",
	 * "kolkata"}; String[] destination = {"bom", "chennai", "cochin"}; return new Object[][] { { origin, destination, "Flights",
	 * "OneWay", "Air India", "Direct", "1", "0", "0", "credit card", false, "Auto Refund"}}; }
	 */

	@Test(dataProviderClass = HQDataProvider.class, dataProvider = "B2CPackageIntl")
	public void packageIntl_441(String[] fromSet, String[] toSet, String app, String tripType, String flightFilterType, String rooms,
			String adults, String children, String paymentMethod, boolean insuranceRequired, String refundMethod)
			throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		int attempt = 0;
		boolean bookingPassed = false;
		boolean paymentDone = false;

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			//driver.findElement(By.id("Packages")).click();
			safeClick(driver, getObject("HomepagePackageTab"));
			if (!waitElement(driver, getObject("HomepagePackageHotelFlexiDatesCheckbox"), 4)) {
				Reporter.log("Package homepage not loading. Exiting.");
				//System.out.println("Package homepage not loading. Exiting.");
				assertTrue("Failure!", false);
			}

			airComHomepageSearchPackage(driver, fromSet[attempt], toSet[attempt], "10", "12", rooms, adults, children, attempt);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
			//System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());

			/*
			 * safeClick(driver, getObject("PackageSRPPrefferedAirline")); try { safeSelect(driver,
			 * getObject("PackageSRPPrefferedAirline"), "Air India"); } catch (Exception e) { try { safeSelect(driver,
			 * getObject("PackageSRPPrefferedAirline"), "SpiceJet"); } catch (Exception ex) {
			 * Reporter.log("Cant find AirIndia and SpiceJet flights. will attempt different sectors.");
			 * //System.out.println("Cant find AirIndia and SpiceJet flights. will attempt different sectors."); attempt++;
			 * continue; } }
			 */

			if (waitElement(driver, getObject("PackageSRPZeroResultMsg"), 2)) {
				Reporter.log("Cant find results. will attempt with different sector.");
				//System.out.println("Cant find results. will attempt with different sector.");
				attempt++;
				continue;
			}

			safeClick(driver, getObject("PackageSRPBookButton"));

			/*
			 * elementPresent(driver, by); boolean element = isElementPresent(driver, by); WebElement we = driver.findElement(by);
			 * WebDriverWait wait = new WebDriverWait(driver, 50);
			 * 
			 * we = wait.until(ExpectedConditions.visibilityOfElementLocated(by)); if (element) { if (isElementPresent(driver,
			 * by)) new Select(we).selectByVisibleText(text); } else { Reporter.log("Element " + by + " or Text " + text +
			 * " is not displayed in " + driver.getCurrentUrl()); } driver.manage().timeouts().implicitlyWait(2,
			 * TimeUnit.SECONDS);
			 */
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}

			cheaperRateBlock(driver);
			printInfo("Review itinerary loaded");
			safeClick(driver, getObject("air_review_itinerary_continue"));
			packageTravellerDetails(driver, adults, children, true);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
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
			bookingPassed = checkBookingStatus(driver);
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		//System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

}
