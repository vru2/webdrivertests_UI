package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EventInterestTagSA extends CampActivities {

	@Parameters({ "user" })
	@Test
	public void manageDataMasterListEventInterestTagSA_36442(String user) throws Exception {
		String eventInterstTag = "Auto_qa2_EventInterest_tag";
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/master_list");
		addMastertagsData("Event Interest Tag", eventInterstTag);
	}

}
