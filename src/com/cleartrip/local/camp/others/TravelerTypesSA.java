package com.cleartrip.local.camp.others;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class TravelerTypesSA extends CampActivities {

	@Parameters({"user"})
	@Test
	public void manageDataMasterTravelerTypeSA_36444(String user) throws Exception {
		String inputTravelerUnit = "Auto_Qa2_Traveller";
		campActivities_SignIN(driver,user);
		driver.get(baseUrl+"/manage_data/master_list");
		addMasterData("Traveller Types",inputTravelerUnit);
		vTravelerType(inputTravelerUnit);
	}

	private void vTravelerType(String tags) {
		try {
			driver.get(baseUrl + "/activities");
			safeClickList(driver, getObjectLocals("Camp_productType"), "Things to do");
			Thread.sleep(1000);
			safeClick(driver, getObjectLocals("Camp_submitBtn"));
			safeClick(driver, getObjectLocals("Camp_firstActivity"));
			safeClick(driver, getObject("Camp_Activities_Edit_Button"));
			safeClick(driver, getObjectLocals("Camp_OthrInfo"));
			Assert.assertTrue(
					driver.findElement(By.xpath(getXpathByReplace(objectReposLocals.value("Camp_travelType"), tags)))
							.isEnabled() == true,
					"TTD Interest tag :" + tags + " didn't display");
			Reporter.log("TTD Interest tag :" + tags + " displayed");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
