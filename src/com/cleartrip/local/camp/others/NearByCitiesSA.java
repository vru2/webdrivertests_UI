package com.cleartrip.local.camp.others;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class NearByCitiesSA extends CampActivities {

	@Parameters({ "user", "domain" })
	@Test
	public void manageDataNearByCitiesSA_37804(String user, String domain) throws Exception {

		HashMap<String, String> inputs = new HashMap<>();
		if (domain.equals("com")) {
			inputs.put("city", "Bangalor");
			inputs.put("nearCity", "Mysor");
		} else {
			inputs.put("city", "Duba");
			inputs.put("nearCity", "Ajma");
		}
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/nearby_cities");
		manageNearByCity(driver, inputs);
	}

}
