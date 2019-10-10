package com.cleartrip.local.camp.others;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class TTDInterestTagSA extends CampActivities {

	@Parameters({ "user" })
	@Test
	public void manageDataMasterTTDInterestTagSA_36445(String user) throws Exception {
		String ttdInterestTag = "Auto_qa2_ttd_tag";
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/master_list");
		addMastertagsData("TTD Interest Tags", ttdInterestTag);
		vTtdInterestTag(ttdInterestTag);
	}

	private void vTtdInterestTag(String tags) {
		try {
			driver.get(baseUrl + "/activities/new?code=things_to_do");
			/*safeClickList(driver, getObjectLocals("Camp_productType"), "Things to do");
			Thread.sleep(1000);
			safeClick(driver, getObjectLocals("Camp_submitBtn"));
			safeClick(driver, getObjectLocals("Camp_firstActivity"));
			safeClick(driver, getObject("Camp_Activities_Edit_Button"));
			*/Assert.assertTrue(
					driver.findElement(By.xpath(getXpathByReplace(objectReposLocals.value("Camp_ttdTag"), tags)))
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
