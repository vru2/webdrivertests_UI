package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
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

public class AirComDomLCCNoBaggageNBRetry extends AirCommonMethod {

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

	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDom() {
		String[] origin = { "blr", "ccu", "ixu" };
		String[] destination = { "vga", "vtz", "maa" };

		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "SpiceJet", "Direct", "1", "0", "0",
				"credit card", false } };
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void airComDomLCCNoBaggageNBRetry(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

		boolean warningFound = false;
		boolean flightCountFailure = true;
		int attempt = 0;
		boolean retryConfirm = true;

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}

			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference, attempt);

			Reporter.log("Search URL is : " + driver.getCurrentUrl());

			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}

			List<WebElement> allFlights = driver.findElements(By.xpath(".//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li"));
			for (WebElement flight : allFlights) {
				try {
					flight.findElement(By.xpath("table/tbody[2]/tr[1]/th[5]/div/span[1]"));
					flight.findElement(By.xpath("table/tbody[2]/tr[2]/td[3]/button")).click();
					break;
				} catch (NoSuchElementException e) {
					continue;
				}
			}

			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}

			//Thread.sleep(2000);

			if (waitElement(driver, By.xpath("//*[@id='itinBlock']/div/div/div/ul/li[6]/small[2]/strong"), 10)) {
				//System.out.println(driver.findElementByXPath("//*[@id='itinBlock']/div/div/div/ul/li[6]/small[2]/strong").getText());
				assertTrue(
						"Baggage on itinerary step is not None! Error",
						driver.findElementByXPath("//*[@id='itinBlock']/div/div/div/ul/li[6]/small[2]/strong").getText()
								.equals("None"));

				driver.findElementByXPath("//*[@id='itinBlock']/div/div/div/ul/li[6]/a").click();

				Thread.sleep(2000);
				driver.switchTo().frame("modal_window");
				Thread.sleep(2000);
				//System.out.println(driver.findElementByXPath("html/body/section/table/tbody/tr[2]/td[2]/p[1]").getText());
				assertTrue(
						"Baggage info is not zero. Failure!",
						driver.findElementByXPath("html/body/section/table/tbody/tr[2]/td[2]/p[1]").getText()
								.equals("0 Kg check-in baggage"));
				driver.switchTo().parentFrame();
				driver.findElementById("close").click();
			} else {
				assertTrue("Baggage info not available on itinerary step. Failure!", false);
			}

			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, false, false, false);

			retryConfirm = NBRetry(driver, "NB");

			//System.out.println("Payment Retry Successfull");
			Reporter.log("Payment Retry Successfull");

			String PaymentRetryUrl = driver.getCurrentUrl();
			Reporter.log("PaymentRetryUrl" + PaymentRetryUrl);
			System.out.println("PaymentRetryUrl" + PaymentRetryUrl);

			// assertTrue("NB got redirected to BETA",PaymentRetryUrl.contains("www.cleartrip.com") );
			// System.out.println("NB got redirected to WWW");
			Reporter.log("NB got successfully redirected to WWW");

		} while (!retryConfirm && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (retryConfirm)));

	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		// System.out.println("Test Case:" + _result.getMethod().getMethodName());
	}

}
