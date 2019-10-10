package testScriptsFinance;



import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

public class HQ_Losstracker_Add_Entry extends HQ
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

		driver.get(getBaseUrl(domain) + "/hq" +"/trips" + "/Q1902270130");
		Thread.sleep(4000);


		if (waitElement(driver, By.className("control"), 3)) {
			List<WebElement> trips = driver.findElements(By.className("control"));
			assertTrue("Trip detail page not opened... Error!", trips.size() > 0);
			Reporter.log("Trip detail page opened sucessfully");
			Thread.sleep(2000);
			driver.findElementByLinkText("Loss Tracker For trip").click();
			Reporter.log("Clicked on Losstracker LInk!!");
			driver=(RemoteWebDriver) getDriver(driver);
			logURL(driver);
			Thread.sleep(2000);
			if (!driver.getCurrentUrl().equals("https://qa2.cleartrip.com/hq/trips/Q1902270130/loss_tracker"))
			{
				String currentURL = driver.getCurrentUrl();
				
				assertTrue("Loss tracker dashboard not opened.!!", false);


			}
			else
			{
				boolean Addentrypresent=driver.findElement(By.linkText("Add Loss Entry")).isDisplayed();
				System.out.println(Addentrypresent);    
				Reporter.log("Loss tracker dashboard opened!!");

				driver.findElement(By.linkText("Add Loss Entry")).click();
				signInHQFinance(driver);
				driver.findElement(By.id("loss_amt")).click();
				driver.findElement(By.id("loss_amt")).sendKeys("1");
				driver.findElement(By.xpath("//textarea[contains(@class, 'loss_reason required')]")).click();
				driver.findElement(By.xpath("//textarea[contains(@class, 'loss_reason required')]")).sendKeys("Test in QA");
				Select  drpdepartment = new Select(driver.findElement(By.id("department")));
				drpdepartment.selectByVisibleText("Hotel Fulfillment");

				Select  drplosstype = new Select(driver.findElement(By.id("loss_type")));
				drplosstype.selectByVisibleText("Booking Issues");

				Select  drpapprover = new Select(driver.findElement(By.id("person_id")));
				drpapprover.selectByVisibleText("amith.sheshachala@cleartrip.com");

				
				driver.findElement(By.id("submit_button")).click();
				Thread.sleep(20000);

				if (waitElement(driver, By.xpath("//table[@id='listings']"), 10))
				{

					if (driver.findElement(By.xpath("//caption[text()='Recorded Loss']")).getText().equals("Recorded Loss")) {

						System.out.println("Loss created and record seen!!");		

					}				

				}

				else {

					addLog("Loss not created!!", true);

					Reporter.log("Loss record not created!!");



				}
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