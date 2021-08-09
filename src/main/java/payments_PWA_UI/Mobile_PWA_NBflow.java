package payments_PWA_UI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Mobile_PWA_NBflow extends Mobile_PWA_Common
{
	public RemoteWebDriver driver = null;
	public RemoteWebDriver arg1 = null;

	@Test
	public void pwa_flow() throws Exception 
	{
		pwahomepageseach(driver);			
		pwa_srpflights_select(driver);
		pwa_review_itinerarypage(driver);
		pwa_review_travellers(driver);
		pwa_select_Payment_option(driver, "NB", "ICICI", "");
		pwa_booking_confirmation_page(driver, "", "","");

	}

	@BeforeMethod
	public void openBrowser()
	{
		driver.get("https://qa2.cleartrip.com/");
		System.out.println("We are currently on the following URL" +driver.getCurrentUrl());
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver,_result);
	}
	@BeforeClass
	public void startSelenium() throws Exception {
		driver=(RemoteWebDriver) getMobileDriver1(driver);
		baseUrl = getBaseUrl("com");
		driver.manage().window().maximize();


	}

}
