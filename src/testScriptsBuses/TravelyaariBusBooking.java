package testScriptsBuses;

import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class TravelyaariBusBooking extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	String domain = "com";
	String tripId = null;

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void travelyaariBusBooking() throws Exception {

		String[] fromSet = { "Ahmedabad", "Delhi" };
		String[] toSet = { "Mumbai", "Ahmedabad" };
		boolean bookingPassed = false;
		int attempt = 0;
		boolean travelyaariFound = false;

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}

			driver.findElement(getObject("HomepageBusTab")).click();
			Thread.sleep(1000);
			if (waitElement(driver, getObject("AirCom_HomePage_Search_Button"), 5)) {
				b2cBusSearch(driver, fromSet[attempt], toSet[attempt]);
			} else {
				//System.out.println("Bus home page not loading. Error!");
				Reporter.log("Bus home page not loading. Error!");
				assertTrue("Failure!", false);
			}
			Thread.sleep(2000);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
			//System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());

			List<WebElement> tabs = driver.findElements(getObject("BusSRPTabs"));
			tabs.remove(0);
			for (WebElement tab : tabs) {
				if (tab.getText().equalsIgnoreCase("Travelyaari")) {
					tab.click();
					travelyaariFound = true;
				}
			}
			if (!travelyaariFound) {
				Reporter.log("Cant find travelyari results. will attempt with different sector.");
				//System.out.println("Cant find travelyari results. will attempt with different sector.");
				attempt++;
				continue;
			}

			if (waitElement(driver, getObject("BusSRPNoResultsMsg"), 2)) {
				Reporter.log("Cant find results. will attempt with different sector.");
				//System.out.println("Cant find results. will attempt with different sector.");
				attempt++;
				continue;
			}

			safeClick(driver, getObject("BusSRPBookButton"));

			Thread.sleep(5000);
			driver.switchTo().frame("modal_window");
			if (waitElement(driver, By.xpath("//*[not(contains(@class, 'occupied')) and starts-with(@class, 'seat sleeper_h')]"),
					3)) {
				//System.out.println("inside sleeper");
				safeClick(driver, By.xpath("//*[not(contains(@class, 'occupied')) and starts-with(@class, 'seat sleeper_h')]"));
			} else if (waitElement(driver,
					By.xpath("//*[not(contains(@class, 'occupied')) and starts-with(@class, 'seat seater')]"), 1)) { // //paramBus.get("bus_seat_sleeper")
				//System.out.println("inside seater");
				safeClick(driver, By.xpath("//*[not(contains(@class, 'occupied')) and starts-with(@class, 'seat seater')]"));
			}
			//safeSelectByIndex(driver, By.xpath("//div[1]/div[2]/div[2]/dl[3]/select"), 0); // paramBus.get("boarding_point")
			/*String dep = driver.findElement(By.xpath("//div[1]/div[2]/div[2]/dl[3]/select" + "/option[1]")).getText();
			//System.out.println(dep);
			//System.out.println("*********************************");*/
			// busDepTime = dep.split("\\(")[1].replaceAll("\\)", "");

			safeClick(driver, By.cssSelector("button.booking")); // paramBus.get("book")
			Thread.sleep(5000);

			driver.switchTo().window("");

			safeClick(driver, By.xpath("//*[@id='itineraryBtn']")); // . paramBus.get("bus_itinerary")

			boolean loginSection = driver.getCurrentUrl().contains("login");
			if (loginSection) {
				printInfo("Login Required");
				safeType(driver, getObject("step2_email_address_username"), dataFile.value("AirUserIdForHQScripts"));
				safeClick(driver, getObject("step2_password_checkbox"));
				Thread.sleep(500);
				safeType(driver, getObject("step2_email_password"), dataFile.value("AirPasswordForHQScripts"));
				safeClick(driver, getObject("step2_login_button"));
				Thread.sleep(8000);
			}

			Thread.sleep(2000);
			fillTravellersDetails(driver, 1, 0, 0, false);
			fillTravellersContact(driver);
			driver.findElement(By.id("AdultAge1")).sendKeys("24");
			//safeClick(driver, getObject("air_traveller_details_continue"));

			if (waitElement(driver, By.xpath("//*[@name='IDCardtype']"), 5)) {
				safeSelectByIndex(driver, By.xpath("//*[@name='IDCardtype']"), 1);
				safeType(driver, By.id("IDCardNum"), "123456789");

			}
			safeClick(driver, By.xpath("//*[@id='travellerBtn']"));
			Thread.sleep(5000);
			break;
		} while (attempt < 2);

		if (waitElement(driver, By.id("checkoutFormSubmit"), 10)) {
			if (driver.getCurrentUrl().contains("cleartrip")) {
				Reporter.log("Test case " + this.getClass() + " passed successfully");
				//System.out.println("Test case " + this.getClass() + " passed successfully");
			} else {
				Reporter.log("Redirected url does not contain cleartrip in the url. Error!");
				//System.out.println("Redirected url does not contain cleartrip in the url. Error!");
				assertTrue("Failure!", false);
			}
		} else {
			Reporter.log("Not redirecting to the merchant site. Error!");
			//System.out.println("Not redirecting to the merchant site. Error!");
			assertTrue("Failure!", false);
		}

	}

	@AfterClass
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		driver.manage().deleteAllCookies();
	}
}
