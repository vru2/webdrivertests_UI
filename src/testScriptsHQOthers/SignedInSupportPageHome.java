package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import domainServices.HQ;
import domainServices.AirCommonMethod;

public class SignedInSupportPageHome extends AirCommonMethod {
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

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}

	@Test(groups = "Regression")
	public void signedInSupportPageHome_121() throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		driver.get(baseUrl);
		Thread.sleep(10000);
		airCom_HomepageSignInForHQScripts(driver, domain);

		Thread.sleep(2000);
		driver.get(getBaseUrl(domain) + "/support");
		Thread.sleep(4000);

		//if (waitElement(driver, By.xpath("//*[@id='supportHomePageTrip']/div/div/p/a"), 5)) {
		if (waitElement(driver, By.xpath("//div[@class='container supportCenter']/div/h1"), 5)) {
			Reporter.log("Support page loaded.");
			// System.out.println("Support page loaded.");
			/*
			 * assertTrue("Check cancellation charges button not shown. Error!",
			 * driver.findElementByXPath("//*[@id='supportHomePageTrip']/div/div/nav/ul[2]/li[1]/a/strong").getText()
			 * .contains("Check cancellation charges")); assertTrue("Cancel Flight button not shown. Error!",
			 * driver.findElementByXPath("//*[@id='supportHomePageTrip']/div/div/nav/ul[2]/li[2]/a/strong").getText()
			 * .contains("Cancel Flight")); assertTrue("Change of plans button not shown. Error!",
			 * driver.findElementByXPath("//*[@id='supportHomePageTrip']/div/div/nav/ul[2]/li[3]/a/strong").getText()
			 * .contains("Change of plans?")); assertTrue("Print e-ticket button not shown. Error!",
			 * driver.findElementByXPath("//*[@id='supportHomePageTrip']/div/div/nav/ul[2]/li[4]/a/strong").getText()
			 * .contains("Print e-ticket"));
			 */
			
			System.out.println(driver.findElement(By.xpath("//span[@class='status caution']")).getText());
			if(!(driver.findElement(By.xpath("//span[@class='status caution']")).getText().equalsIgnoreCase("on hold"))){
			assertTrue("Print invoice button not shown. Error!",
					driver.findElementByXPath("//*[@id='secondaryTriptools']/nav/ul/li[2]/a").getText().contains("Print invoice"));
			driver.findElementByXPath("//*[@id='secondaryTriptools']/nav/ul/li[2]/a").click();
			String mainwindow = driver.getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			if (waitElement(driver, By.xpath(".//div/table/tbody/tr/td/h1"), 5)) {

				Reporter.log("Invoice displayed properly. Trip Id - "
						+ driver.findElementByXPath(".//div/table/tbody/tr/td/h1").getText());
			} else {
				// System.out.println("Invoice not displayed properly. Error!");
				Reporter.log("Invoice not displayed properly. Error!");
				assertTrue("Failure!", false);
			}
			driver.close();
			driver.switchTo().window(mainwindow);
			assertTrue("Email itinerary button not shown. Error!",
					driver.findElementById("email_itinerary").getText().contains("Email itinerary"));
			driver.findElementById("email_itinerary").click();
			Thread.sleep(200);
			driver.findElementById("sendEmail").click();
			Thread.sleep(5000);
			assertTrue("Itenerary sent message not displayed. Error!", driver.findElementById("secTriptoolsErrBlock").getText()
					.equals("Itinerary sent"));
			assertTrue("Email Pass button not shown. Error!",
					driver.findElementById("email_pass").getText().contains("Email Pass"));
			driver.findElementById("email_pass").click();
			Thread.sleep(200);
			driver.findElementById("sendPass").click();
			Thread.sleep(5000);
			assertTrue("Pass sent message not displayed. Error!", driver.findElementById("secTriptoolsErrBlock").getText()
					.equals("Itinerary sent"));
			assertTrue("SMS trip details button not shown. Error!",
					driver.findElementById("sms_itinerary").getText().contains("SMS trip details"));
			driver.findElementById("sms_itinerary").click();
			Thread.sleep(200);
			driver.findElementById("sendSms").click();
			Thread.sleep(5000);
			assertTrue("Sms sent message not displayed. Error!",
					driver.findElementById("secTriptoolsErrBlock").getText().equals("Itinerary sent"));
		}
		else{
			Reporter.log("Trip is not confirmed");
			System.out.println("Trip is not confirmed");
		} 
			
		}else {
			Reporter.log("signedin support page not loaded! Error!");
			// System.out.println("signedin support page not loaded! Error!");
			assertTrue("Failure!", false);
		}

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		// writeTripToFile(tripID);
		driver.close();
		driver.quit();
	}

	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}*/
}
