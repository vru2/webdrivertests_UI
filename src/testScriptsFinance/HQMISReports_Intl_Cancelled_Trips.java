package testScriptsFinance;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import domainServices.HQ;

public class HQMISReports_Intl_Cancelled_Trips extends HQ

{
	
	


	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test(groups = "Regression")
	public void MISReport() throws Exception {

		driver.get(getBaseUrl(domain) + "/hq");
		signInHQFinance(driver);

		driver.get(getBaseUrl(domain) + "/hq");
		elementVisible(driver, By.linkText("Dashboard"), 5);
		safeClick(driver, By.linkText("Dashboard"));
		Thread.sleep(3000);
		elementVisible(driver, By.linkText("Air MIS Reports"), 5);
		safeClick(driver, By.linkText("Air MIS Reports"));


		logURL(driver);
		if (!driver.getCurrentUrl().equals("https://qa2.cleartrip.com/hq/reports/mis/air")) {

			Reporter.log("Air MIS Report Not opened !!!");

		} else {

			Reporter.log("Air MIS Report opened !!!");

			Select  drpbooktype = new Select(driver.findElement(By.id("filterType")));
			drpbooktype.selectByVisibleText("have been cancelled");
			
			Select  drpbookingtype = new Select(driver.findElement(By.id("bookingType")));
			drpbookingtype.selectByVisibleText("International");

	

			WebElement selectfromDate = driver.findElement(By.id("date_from"));
			selectfromDate.click();


			WebElement previousLink = driver.findElement(By.id("cal_showPreviousMonth"));
			previousLink.click();


			WebElement dateselect = driver.findElement(By.xpath("//div[3]/table/tbody/tr[3]/td[2]/a"));
			dateselect.click();	

			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

			Thread.sleep(4000);
			logURL(driver);

			if(textPresent(driver,"MIS Reports Progress" ,10))
			{

				List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "MIS generation is a tough job" + "')]"));
				Assert.assertTrue("Text not found!", list.size() > 0);

				Reporter.log("MIS report generation is in progree!!");
				System.out.println("MIS report generation is in progress!!");

				String ProcessID = driver.findElement(By.xpath(".//*[@id='ContentFrame']//a[contains(text(),'Processing')]")).getText();

				String PID = ProcessID.replaceAll("[^0-9]", "");

				addLog("Processing File ID : " + PID, true);

				safeClick(driver, By.xpath(".//*[@id='tips_tools']/ul/li[5]/a"));

				addLog("Clicked on 'Download custom MIS reports' link", true);

				waitForElement(driver, 7, By.xpath(".//td[text()='" + PID + "']"));



				boolean fileDownload = false;

				for (int i = 0; i < 36; i++) {

					if (driver.findElement(By.xpath("//tr/td[text()='" + PID + "']/following-sibling::td[6]")).getText()

							.contains("Processing")) {

						Thread.sleep(5000);

						driver.navigate().refresh();



					} else if (elementPresent_Time(driver, By.xpath("//tr/td[text()='" + PID + "']/following-sibling::td/a"),

							1)) {

						driver.findElement(By.xpath("//tr/td[text()='" + PID + "']/following-sibling::td/a")).click();

						fileDownload = true;

						break;

					}

				}

				Reporter.log("File still processing even after waiting for 3 min!!");
				checkSizeofFile();
			}


		}

	}
	@AfterClass
	public void closeSelenium() throws Exception {
		browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	


}
