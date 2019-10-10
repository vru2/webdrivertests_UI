package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;
import domainServices.HQ;

public class HQScreenshot extends AirCommonMethod {
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String domain = "com";
	private boolean passed = false;
	private int attempt = 0;

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test(groups = "Regression")
	public void screenshot_160() throws Exception {

		HQ hq = new HQ();

		driver.get(getBaseUrl(domain) + "/hq");
		hq.signInHQ(driver);

		do {
			driver.get(getBaseUrl(domain) + "/hq/trips/");
			if (waitElement(driver, getObject("AirCom_HQ_Trips_Search_Box"), 10)) {
			driver.findElement(getObject("AirCom_HQ_Trips_Search_Box")).sendKeys(dataFile.value("AirUserIdForHQScripts"));
			//	driver.findElement(getObject("AirCom_HQ_Trips_Search_Box")).sendKeys("ns.likhitha@cleartrip.com");
				driver.findElement(getObject("AirCom_HQ_Trips_Search_Button")).click();
			} else {
				Reporter.log("Trips tab in HQ not loading!");
				// System.out.println("Trips tab in HQ not loading!");
				assertTrue("Failure!", false);
			}
			
			waitElement(driver, getObject("AirCom_HQ_Trips_Search_Result_Fifth_Trip"), 10);
			String tripId = driver.findElement(By.xpath("//*[@id='left']/div[2]/table/tbody/tr[5]/td[2]/p[1]")).getText();
			// System.out.println(tripId);
			Reporter.log(tripId);
			driver.findElement(getObject("AirCom_HQ_Trips_Search_Result_Fifth_Trip")).click();
			elementVisible(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1);
			if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {
				// System.out.println("System Acting up error.");
				Reporter.log("System Acting up error.");
				assertTrue("Failure!", false);
			}
			if (elementNotVisible(driver, getObject("AirCom_HQ_Trips_Tab"), 5)) {
				assertTrue("Trip details page not loading!", false);
			}
			driver.findElement(getObject("Air_HQ_Trip_Details_Screenshots_Link")).click();
			elementPresent_log(driver,getObject("Air_HQ_Trip_Details_Screenshot_One"), "Screenshot", 20);
			if (waitElement(driver, getObject("Air_HQ_Trip_Details_Screenshot_One"), 5)) {
				if (waitElement(driver, getObject("Air_HQ_Trip_Details_Screenshot_Two"), 1)) {
					// System.out.println("Both screenshots are displayed properly.");
					Reporter.log("Both screenshots are displayed properly.");
					passed = true;
					break;
				} else {
					// System.out.println("Only one screenshot displayed!");
					Reporter.log("Only one screenshot displayed!");
					assertTrue("Failure!", false);
				}
			} else {
				attempt++;
				continue;
			}
		} while (attempt < 5);

	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
	
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}
