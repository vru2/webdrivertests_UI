package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class RewardReportSanity extends HQ {
	public RemoteWebDriver driver = null;
	String domain = "com";

	
	@Test
	public void rewardReportSanity_117() throws Exception {

		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		Thread.sleep(6000);
		driver.get(getBaseUrl(domain) + "/hq/reward_program/reports");
		safeSelect(driver, getObject("Reward_Program_Report_Type"), "Enrollment");
		safeSelect(driver, getObject("Reward_Program_Report_Domain"), ".com");
		//safeSelectByValue(driver, getObject("Reward_Program_Report_Domain"), "cleartrip.com");
		selectCalendarDate(driver, getObject("Reward_Program_Report_StartDate_Calendar"),
				getObject("Reward_Program_Report_StartDate_Calendar_PreviousMonth"), 1, "1");
		selectCalendarDate(driver, getObject("Reward_Program_Report_EndDate_Calendar"),
				getObject("Reward_Program_Report_StartDate_Calendar_PreviousMonth"), 1, "28");
		driver.findElement(getObject("Reward_Program_Report_GetReport")).click();
		Thread.sleep(3000);

		safeSelect(driver, getObject("Reward_Program_Report_Type"), "Coupon Issued");
		safeSelectByValue(driver, getObject("Reward_Program_Report_Domain"), "cleartrip.com");
		selectCalendarDate(driver, getObject("Reward_Program_Report_StartDate_Calendar"),
				getObject("Reward_Program_Report_StartDate_Calendar_PreviousMonth"), 1, "1");
		selectCalendarDate(driver, getObject("Reward_Program_Report_EndDate_Calendar"),
				getObject("Reward_Program_Report_StartDate_Calendar_PreviousMonth"), 1, "28");
		driver.findElement(getObject("Reward_Program_Report_GetReport")).click();
		Thread.sleep(3000);

		safeSelect(driver, getObject("Reward_Program_Report_Type"), "Coupon redeemed");
		safeSelectByValue(driver, getObject("Reward_Program_Report_Domain"), "cleartrip.com");
		selectCalendarDate(driver, getObject("Reward_Program_Report_StartDate_Calendar"),
				getObject("Reward_Program_Report_StartDate_Calendar_PreviousMonth"), 1, "1");
		selectCalendarDate(driver, getObject("Reward_Program_Report_EndDate_Calendar"),
				getObject("Reward_Program_Report_StartDate_Calendar_PreviousMonth"), 1, "28");
		driver.findElement(getObject("Reward_Program_Report_GetReport")).click();
		Thread.sleep(3000);

		safeSelect(driver, getObject("Reward_Program_Report_Type"), "Coupon Expired");
		safeSelectByValue(driver, getObject("Reward_Program_Report_Domain"), "cleartrip.com");
		selectCalendarDate(driver, getObject("Reward_Program_Report_StartDate_Calendar"),
				getObject("Reward_Program_Report_StartDate_Calendar_PreviousMonth"), 1, "1");
		selectCalendarDate(driver, getObject("Reward_Program_Report_EndDate_Calendar"),
				getObject("Reward_Program_Report_StartDate_Calendar_PreviousMonth"), 1, "28");
		driver.findElement(getObject("Reward_Program_Report_GetReport")).click();
		Thread.sleep(10000);

		// generating one more report as refreshing page not possible
		safeSelect(driver, getObject("Reward_Program_Report_Type"), "Enrollment");
		safeSelectByValue(driver, getObject("Reward_Program_Report_Domain"), "cleartrip.com");
		selectCalendarDate(driver, getObject("Reward_Program_Report_StartDate_Calendar"),
				getObject("Reward_Program_Report_StartDate_Calendar_PreviousMonth"), 1, "1");
		selectCalendarDate(driver, getObject("Reward_Program_Report_EndDate_Calendar"),
				getObject("Reward_Program_Report_StartDate_Calendar_PreviousMonth"), 1, "28");
		driver.findElement(getObject("Reward_Program_Report_GetReport")).click();

		Thread.sleep(5000);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		refreshPage(driver);
		for (int i = 5; i <= 6; i++) {
			assertTrue("Download link absent. Error!",
					driver.findElement(By.xpath("//*[@id='ContentFrame']/table/tbody/tr[" + i + "]/td[3]/a")).getText()
							.equals("Download"));
			System.out.println(driver.findElement(By.xpath("//*[@id='ContentFrame']/table/tbody/tr[" + i + "]/td[1]"))
					.getText().subSequence(0, 10));
			Reporter.log((String) driver.findElement(By.xpath("//*[@id='ContentFrame']/table/tbody/tr[" + i + "]/td[1]"))
					.getText().subSequence(0, 10));
			assertTrue("Date is not todays! Failure!",
					driver.findElement(By.xpath("//*[@id='ContentFrame']/table/tbody/tr[" + i + "]/td[1]")).getText()
							.subSequence(0, 10).equals(todaysDate));
		}

	}

	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
	  }
	  @AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
	  @AfterClass
	  public void tearDown() throws Exception {
		  browserClose(driver);    
	  }
}
