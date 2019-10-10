package com.cleartrip.local.camp.accountflow;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AccountFlowVerificationSCM extends CampActivities {
	public String locationPath;
	ArrayList<String> verifyData = new ArrayList<>();
	
	@Parameters({"user"})
	@Test(priority = 1)
	public void campActiveTabAllActivitySCM_36506(String user) throws Exception {
		campActivities_SignIN(driver,user);
		locationPath = "//section[@id='supplier-dashboard']/h4";
		verifyData.add("Activity");
		verifyData.add("All Activities");
		verifyData.add("Activities Listing");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 2)
	public void campActiveTabActivityHistorySCM_36508() throws Exception {
		verifyData.clear();
		locationPath = "//h2[text()='Activity History']";
		verifyData.add("Activity");
		verifyData.add("Activity History");
		verifyData.add("Activity History");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 3)
	public void campActiveTabRatingSCM_36509() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']/h4[contains(text(),'Ratings')]";
		verifyData.add("Activity");
		verifyData.add("Ratings");
		verifyData.add("Ratings");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 4)
	public void campActiveTabImportExportSCM_36510() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']/div/div[1]/span/a";
		verifyData.add("Activity");
		verifyData.add("Import/Export");
		verifyData.add("Download CSV");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 5)
	public void campActiveManageChainSCM_36511() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//h2[text()='Manage Chains and Variants']";
		verifyData.add("Manage Data");
		verifyData.add("Manage Chains and Variants");
		verifyData.add("Manage Chains and Variants");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 6)
	public void campActiveDashBoardESP_36512() throws Exception {
		locationPath = "//div[@id='superadminDashboard']//h4[text()='Dashboard Menu']";
		safeClick(driver, By.linkText("Dashboard"));
		Thread.sleep(3000);
		if (isElementPresent(driver, By.xpath(locationPath)) == false) {
			Reporter.log("DashBoard Menu isn't present for SCM User");
		}
	}

	@Test(priority = 8)
	public void campActiveTabInventortAuditSCM_36507() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']//h4[contains(text(),'Inventory Audit')]";
		verifyData.add("Activity");
		verifyData.add("Inventory Audit");
		verifyData.add("Inventory Audit");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

}
