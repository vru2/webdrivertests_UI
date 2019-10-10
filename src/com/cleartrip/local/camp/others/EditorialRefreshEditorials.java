package com.cleartrip.local.camp.others;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialRefreshEditorials extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void editorialRefreshEditorials_39748(String user) throws Exception {
		campActivities_SignIN(driver, user);
		driver.get(baseUrl+"/editorial/");
		safeClick(driver, getObjectLocals("Camp_Refresh_Editorials"));
		safeAutocomplete(driver, By.name("city_name"), By.linkText(campLocal.value("campCity")),
				campLocal.value("campCityPartial"));
		safeClick(driver, getObjectLocals("Camp_RefreshEditorial_Click"));

	}

}
