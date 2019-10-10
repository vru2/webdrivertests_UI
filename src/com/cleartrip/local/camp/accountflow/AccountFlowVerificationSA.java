package com.cleartrip.local.camp.accountflow;

import java.util.ArrayList;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AccountFlowVerificationSA extends CampActivities {
	public String locationPath;
	ArrayList<String> verifyData = new ArrayList<>();

	@Parameters({ "user" })
	@Test(priority = 0)
	public void campActiveTabDashboardSA_36513(String user) throws Exception {
		campActivities_SignIN(driver, user);
		locationPath = "//div[@id='superadminDashboard']//h4[text()='Dashboard Menu']";
		verifyData.add("Dashboard");
		verifyData.add("Dashboard");
		verifyData.add("Dashboard Menu");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 1)
	public void campActiveTabFinanceDashSA_36514() throws Exception {
		verifyData.clear();
		locationPath = "//div[@id='dbblock']//h5[text()='Remittance Upload']";
		verifyData.add("Finance Dashboard");
		verifyData.add("Finance Dashboard");
		verifyData.add("Remittance Upload");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 2)
	public void campActiveTabAccountListSA_36515() throws Exception {
		verifyData.clear();
		locationPath = "//div[@id='supplier-dashboard']/h4";
		verifyData.add("Account");
		verifyData.add("Accounts List");
		verifyData.add("Accounts Listing");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 3)
	public void campActiveTabPLBSA_36516() throws Exception {
		verifyData.clear();
		locationPath = "//div[@id='tap-view']/h3[text()='Configure PLB']";
		verifyData.add("Account");
		verifyData.add("PLB");
		verifyData.add("Configure PLB");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 4)
	public void campActiveTabAllActivitySA_36517() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']/h4[contains(text(),'Activities Listing')]";
		verifyData.add("Activity");
		verifyData.add("All Activities");
		verifyData.add("Activities Listing");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 5)
	public void campActiveTabTTDSA_36518() throws Exception {
		verifyData.clear();
		locationPath = "//strong[contains(.,'List an Activity')]";
		verifyData.add("Activity");
		verifyData.add("New Activity (Things to do)");
		verifyData.add("List an Activity");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 6)
	public void campActiveTabFNBSA_36519() throws Exception {
		verifyData.clear();
		locationPath = "//strong[contains(.,'List an F & B offering')]";
		verifyData.add("Activity");
		verifyData.add("New Activity (F & B)");
		verifyData.add("List an F & B offering");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 7)
	public void campActiveTabInventorySA_36520() throws Exception {
		verifyData.clear();
		locationPath = "//div[@id='superadminDashboard']//div[@class='activity_inventory']/div[contains(.,'Inventory')]";
		verifyData.add("Activity");
		verifyData.add("Inventory");
		verifyData.add("Inventory");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 9)
	public void campActiveTabActivityHistorySA_36522() throws Exception {
		verifyData.clear();
		locationPath = "//h2[text()='Activity History']";
		verifyData.add("Activity");
		verifyData.add("Activity History");
		verifyData.add("Activity History");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 10)
	public void campActiveTabRatingsSA_36523() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']/h4[contains(text(),'Ratings')]";
		verifyData.add("Activity");
		verifyData.add("Ratings");
		verifyData.add("Ratings");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 11)
	public void campActiveTabImportExportSA_36524() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']/div/div[1]/span/a";
		verifyData.add("Activity");
		verifyData.add("Import/Export");
		verifyData.add("Download CSV");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 12)
	public void campActiveTabAllBookingSA_36525() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']/h4[contains(text(),'Your Bookings')]";
		verifyData.add("Bookings");
		verifyData.add("All Bookings");
		verifyData.add("Your Bookings");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 13)
	public void campActiveTabRemittancesSA_36526() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']//h4[contains(text(),'Remittances')]";
		verifyData.add("Bookings");
		verifyData.add("Remittances");
		verifyData.add("Remittances");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 14)
	public void campActiveTabGSTReportSA_36527() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//label[contains(text(),'GST Invoice and Reports')]";
		verifyData.add("Bookings");
		verifyData.add("GST Reports & Invoices");
		verifyData.add("GST Invoice and Reports");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 15)
	public void campActiveTabReportNewSA_36528() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']//h4[contains(text(),'Report Center')]";
		verifyData.add("Reports");
		verifyData.add("Reports New");
		verifyData.add("Report Center");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 15)
	public void campActiveTabReportOldSA_36529() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']//h4[contains(text(),'Important Reports')]";
		verifyData.add("Reports");
		verifyData.add("Reports Old");
		verifyData.add("Important Reports");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 16)
	public void campActiveTabMasterListSA_36530() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//option[text()='Select Master List']";
		verifyData.add("Manage Data");
		verifyData.add("Master List");
		verifyData.add("Select Master List");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 17)
	public void campActiveTabReOrderSA_36531() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//label[contains(.,'City')]";
		verifyData.add("Manage Data");
		verifyData.add("Reorder");
		verifyData.add("City");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 16)
	public void campActiveTabSortListingSA_36532() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//label[contains(.,'City')]";
		verifyData.add("Manage Data");
		verifyData.add("Sort Listings");
		verifyData.add("City");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 17)
	public void campActiveTabManageCategoriesSA_36533() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//a[text()='Order Categories']";
		verifyData.add("Manage Data");
		verifyData.add("Manage Categories");
		verifyData.add("Order Categories");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 18)
	public void campActiveTabAccessControlSA_36534() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//label[text()='Manage Access']";
		verifyData.add("Manage Data");
		verifyData.add("Access Control");
		verifyData.add("Manage Access");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 19)
	public void campActiveTabCustomCollectionSA_36535() throws Exception {
		verifyData.clear();
		locationPath = "//h2[contains(.,'Activity - Custom Collections mapping')]";
		verifyData.add("Manage Data");
		verifyData.add("Custom Collections");
		verifyData.add("Activity - Custom Collections mapping");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 20)
	public void campActiveTabNearbyCitiesSA_36536() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//label[contains(.,'Nearest Cities')]";
		verifyData.add("Manage Data");
		verifyData.add("Nearby Cities");
		verifyData.add("Nearest Cities");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 21)
	public void campActiveTabManageMerchandisingSA_36537() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//a[text()='Create Merchandise']";
		verifyData.add("Manage Data");
		verifyData.add("Manage Merchandising");
		verifyData.add("Create Merchandise");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 22)
	public void campActiveTabManageEditorialSA_36538() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='editorial-dashboard']//a[text()='List Editorials']";
		verifyData.add("Manage Data");
		verifyData.add("Manage Editorials");
		verifyData.add("List Editorials");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 23)
	public void campActiveTabItemRecommendationsSA_36539() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//label[contains(.,'City')]";
		verifyData.add("Manage Data");
		verifyData.add("Item Recommendations");
		verifyData.add("City");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 24)
	public void campActiveTabManageChainsVariantsSA_36540() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='manageActivity']//h2[text()='Manage Chains and Variants']";
		verifyData.add("Manage Data");
		verifyData.add("Manage Chains and Variants");
		verifyData.add("Manage Chains and Variants");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 25)
	public void campActiveTabWLAccountsSA_36541() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']//h4[contains(text(),'WL Accounts Listing')]";
		verifyData.add("Accounts");
		verifyData.add("Accounts");
		verifyData.add("WL Accounts Listing");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

	@Test(priority = 26)
	public void campActiveTabInventoryAuditSA_36521() throws Exception {
		verifyData.clear();
		locationPath = "//section[@id='supplier-dashboard']//h4[contains(text(),'Inventory Audit')]";
		verifyData.add("Activity");
		verifyData.add("Inventory Audit");
		verifyData.add("Inventory Audit");
		verifyData.add(locationPath);
		campActiveLinks(driver, verifyData);
	}

}
