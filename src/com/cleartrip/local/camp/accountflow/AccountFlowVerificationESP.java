package com.cleartrip.local.camp.accountflow;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AccountFlowVerificationESP extends CampActivities {
	public String locationPath;
	ArrayList<String> verifyData = new ArrayList<>();

	@Parameters({"user"})
	@Test(priority = 1)
	public void campActiveTabMyAccountESP_36496(String user) throws Exception {
		campActivities_SignIN(driver,user);
		locationPath = "//div[@id='tap-view']/h3";
		verifyData.add("Account");
		verifyData.add("My Profile");
		verifyData.add("Mr.ESP's Profile");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 2)
	public void campActiveTabMyBankAccountESP_36497() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='banking']//h5";
		verifyData.add("Account");
		verifyData.add("My Bank Account");
		verifyData.add("Bank Account Information for Remittances");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 3)
	public void campActiveTabAllActivityESP_36498() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']/h4";
		verifyData.add("Activity");
		verifyData.add("All Activities");
		verifyData.add("Activities Listing");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 4)
	public void campActiveTabTTDESP_36499() throws Exception {
		verifyData.clear();
		locationPath = "//strong[contains(.,'List an Activity')]";
		verifyData.add("Activity");
		verifyData.add("New Activity (Things to do)");
		verifyData.add("List an Activity");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 5)
	public void campActiveTabInventoryESP_36500() throws Exception {
		verifyData.clear();
		locationPath = "//div[@id='superadminDashboard']//div[@class='activity_inventory']/div[contains(.,'Inventory')]";
		verifyData.add("Activity");
		verifyData.add("Inventory");
		verifyData.add("Inventory");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 6)
	public void campActiveTabRatingsESP_36501() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']/h4[contains(text(),'Ratings')]";
		verifyData.add("Activity");
		verifyData.add("Ratings");
		verifyData.add("Ratings");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 7)
	public void campActiveAllBookingESP_36502() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']/h4[contains(text(),'Your Bookings')]";
		verifyData.add("Bookings");
		verifyData.add("All Bookings");
		verifyData.add("Your Bookings");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 8)
	public void campActiveTabRemittancesESP_36503() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']//h4[contains(text(),'Remittances')]";
		verifyData.add("Bookings");
		verifyData.add("Remittances");
		verifyData.add("Remittances");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

/*	@Test(priority = 9)
	public void campActiveTabGSTReportESP_36504() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//label[contains(text(),'GST Invoice and Reports')]";
		verifyData.add("Bookings");
		verifyData.add("GST Reports & Invoices");
		verifyData.add("GST Invoice and Reports");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}*/

	@Test(priority = 10)
	public void campActiveDashBoardESP_36505() throws Exception {
		locationPath = "//div[@id='superadminDashboard']//h4[text()='Dashboard Menu']";
		safeClick(driver, By.linkText("Dashboard"));
		Thread.sleep(3000);
		if (isElementPresent(driver, By.xpath(locationPath)) == false) {
			Reporter.log("DashBoard Menu isn't present for ESP User");
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}
