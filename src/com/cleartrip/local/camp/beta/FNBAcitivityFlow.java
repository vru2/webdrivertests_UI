package com.cleartrip.local.camp.beta;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class FNBAcitivityFlow extends CampActivities {

	@Parameters({ "domain" })
	@Test
	public void fnbActivity(String domain) throws Exception {
		campActivities_SignIN(driver, "SA");
		prodFnbPageOne(domain);
		String activityId = fnbCreateFormTwo("Pricing Per Person", "", domain);
		vProdActivityLocal(driver, domain, "food-and-drinks");
		unPublishActivity(baseUrl, activityId);
	}

	private void prodFnbPageOne(String domain) throws Exception {

		driver.get(baseUrl + "/activities/new?code=fnb");
		Thread.sleep(1000);
		safeClick(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier"));
		if (domain.equals("com")) {
			safeAutocomplete(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier_InputFld"),
					By.xpath("//li[@class='ui-menu-item']"), "suresh.halli@cleartrip.com");
		} else {
			safeAutocomplete(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier_InputFld"),
					By.xpath("//li[@class='ui-menu-item']"), "prabhanjan.manoli@cleartrip.com");
		}
		// safeType(driver, getObject("Camp_Activities_GSTIN"),
		// dataFile.value("CampActivities_gstInNum"));
		safeType(driver, getObject("Camp_FNB_activityName"), "Restaurant" + campLocal.value("releaseVersion"));
		safeType(driver, getObject("Camp_FNB_activityDisplayName"), "Outlet" + campLocal.value("releaseVersion"));

		if (domain.equals("com")) {
			safeAutocomplete_CHMM(driver, getObject("Camp_FNB_AddressLine"), By.xpath("//div[6]/div/div"),
					campLocal.value("campCity"));
			safeAutocompleteMouseHover(driver, getObject("Camp_Activities_NewActivity_City"),
					getObject("AirCom_HomePage_From_Ajax"), campLocal.value("campCity"));
			Reporter.log("City Selected : " + campLocal.value("campCity"), true);
			safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "587101");
			safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "mg Road");
			safeAutocomplete_CHMM(driver, getObject("Camp_Activities_NewActivity_Landmark"),
					By.xpath("//div[7]/div/div"), campLocal.value("campCity"));
		} else {
			safeAutocomplete_CHMM(driver, getObject("Camp_FNB_AddressLine"), By.xpath("//div[6]/div/div"),
					campLocal.value("AeCity"));
			safeAutocompleteMouseHover(driver, getObject("Camp_Activities_NewActivity_City"),
					getObject("AirCom_HomePage_From_Ajax"), campLocal.value("AeCity"));
			Reporter.log("City Selected : " + campLocal.value("AeCity"), true);
			safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "587101");
			safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), campLocal.value("campAecityPartial"));
			safeAutocomplete_CHMM(driver, getObject("Camp_Activities_NewActivity_Landmark"),
					By.xpath("//div[7]/div/div"), campLocal.value("campAecityPartial"));

		}
		safeType(driver, getObject("Camp_Activities_NewActivity_Meeting_SupportNumber"), "1212121121");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)", "");
		safeClick(driver, By.xpath("//button[contains(.,'Save')]"));
		Thread.sleep(6000);
		Reporter.log("Restaurant" + campLocal.value("releaseVersion") + " one form is filled sucessfully", true);

	}
}
