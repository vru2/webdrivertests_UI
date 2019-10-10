package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
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

public class SpecialRoundTripRateChecker extends AirCommonMethod {

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

	@Test
	public void specialRoundTripRateChecker() throws Exception {

		int attempt = 0;
		boolean ratesMatch = false;
		boolean flightCountFailure = true;
		String[] fromSet = { "del", "bom", "blr" };
		String[] toSet = { "blr", "blr", "goa" };

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		do {
			driver.get(baseUrl);
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", "1", "0", "0", "", attempt);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
			//System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}

			List<WebElement> splRndTabs = driver.findElements(By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td"));
			WebElement allAirlinesTab;
			String totalAmountSpecial = null;
			String totalAmount = null;
			String onwardSelectedFlightNoSpecial = null;
			String returnSelectedFlightNoSpecial = null;
			String checked;

			if (splRndTabs != null) {
				allAirlinesTab = splRndTabs.get(0);
				int size = splRndTabs.size();
				for (int i = 2; i < size + 1; i++) {
					String title = driver.findElement(
							By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a/span/img")).getAttribute(
							"title");
					/*//System.out.println(driver.findElement(
							By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a/span/img")).getAttribute(
							"title"));
					*/
					if (title.equalsIgnoreCase("SpiceJet"))
						continue;
					if (isElementPresent(driver, By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a/small"))) {
						String discount = driver.findElementByXPath(
								".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a/small").getText();
						printInfo(discount);
						//if(elementPresent_Time(driver,By.xpath("//small[@class='strikeOut']/span"),1)){
						if (discount.contains("Save")) {
							safeClick(driver, By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a"));
							Thread.sleep(2000);
							//System.out.println("checkIfSplRndTrip(driver) status="+checkIfSplRndTrip(driver));
							if (checkIfSplRndTrip(driver)) {
								Reporter.log("Special round trip flight selected. " + title);
								printInfo("Special round trip flight selected. " + title);
								totalAmountSpecial = driver.findElement(getObject("AirCom_SRP_Roundtrip_TotalAmount")).getText();
								//System.out.println("totalAmountSpecial - " + totalAmountSpecial);
								// Thread.sleep(10000);

								List<WebElement> onwardFlights = driver
										.findElements(getObject("AirCom_SRP_Roundtrip_OnwardFlights"));
								for (WebElement onwardFlight : onwardFlights) {
									checked = onwardFlight.findElement(By.xpath("./tr[1]/th[1]/input")).getAttribute("checked");
									if (checked == null)
										continue;
									if (checked.equals("true")) {
										//System.out.println(onwardFlight.findElement(By.xpath("./tr[2]/td/span")).getText());
										onwardSelectedFlightNoSpecial = onwardFlight.findElement(By.xpath("./tr[2]/td/span"))
												.getText();
										break;
									}
								}

								List<WebElement> returnFlights = driver
										.findElements(getObject("AirCom_SRP_Roundtrip_ReturnFlights"));
								for (WebElement returnFlight : returnFlights) {
									checked = returnFlight.findElement(By.xpath("./tr[1]/th[1]/input")).getAttribute("checked");
									if (checked.equals("true")) {
										returnSelectedFlightNoSpecial = returnFlight.findElement(By.xpath("./tr[2]/td/span"))
												.getText();
										break;
									}
								}

								allAirlinesTab.click();
								Thread.sleep(3000);
								
								List<WebElement> stop_options;
								By by = By.name(objectRepos.value("results_stops"));
								boolean stops_available = elementVisible(driver, by, 2);
								if (stops_available) {
									stop_options = driver.findElements(by);
									stop_options.remove(0);
									for (WebElement we : stop_options) {
										System.out.print(stop_options.get(0).getText());
										if (we.isSelected())
											we.click();
									}
								}
								Thread.sleep(2000);
								
								boolean onwardFlightFound = false;
								boolean returnFlightFound = false;

								onwardFlights = driver.findElements(getObject("AirCom_SRP_Roundtrip_OnwardFlights"));
								for (WebElement onwardFlight : onwardFlights) {
									
									if (onwardFlight.findElement(By.xpath("./tr[2]/td/span")).getText()
											.equalsIgnoreCase(onwardSelectedFlightNoSpecial)) {
										onwardFlight.findElement(By.xpath("./tr[1]/th[1]/input")).click();
										onwardFlightFound = true;
										break;
									}
								}

								returnFlights = driver.findElements(getObject("AirCom_SRP_Roundtrip_ReturnFlights"));
								for (WebElement returnFlight : returnFlights) {
									if (returnFlight.findElement(By.xpath("./tr[2]/td/span")).getText()
											.equalsIgnoreCase(returnSelectedFlightNoSpecial)) {
										returnFlight.findElement(By.xpath("./tr[1]/th[1]/input")).click();
										returnFlightFound = true;
										break;
									}
								}
								
								if (!onwardFlightFound || !returnFlightFound) {
									// might not be a direct flight
									continue;
								}

								Thread.sleep(2000);

								totalAmount = driver.findElement(getObject("AirCom_SRP_Roundtrip_TotalAmount")).getText();
								//System.out.println("all airline total amount - " + totalAmount);

								ratesMatch = totalAmountSpecial.equalsIgnoreCase(totalAmount);
								assertTrue(
										"Special round trip amount on selecting from combo tab doesn't match with all airlines tab. Error! \nCombo fare = "
												+ totalAmountSpecial + "\nAll airline fare = " + totalAmount
												+ ". For the airline - " + title, ratesMatch);
								//assertTrue("Failure!", false);
							} else {
								Reporter.log("Clicked combo tab, still special round trip flights not selected.");
								printInfo("Clicked combo tab, still special round trip flights not selected.");
								
							}
						} else {
							continue;
						}
					} else {
						continue;
					}
				}
			}
			attempt++;
		} while (attempt < 3);
		if (!ratesMatch) {
			assertTrue("Rates could not be matched. Error!", false);
		}
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		// writeTripToFile(tripID);
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		//System.out.println("Test Case:" + _result.getMethod().getMethodName());
	}

}
