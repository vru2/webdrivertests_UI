package com.cleartrip.local.camp.others;
//import webdrivertests.testScriptsCampActivities;

import org.testng.annotations.Test;

import domainServices.CampActivities;

public class CampLogins extends CampActivities {

	@Test
	public void campLogins_39743() throws Exception {
		campActivities_SignIN(driver, "SA");
		System.out.println("SA signed in successfully");
		campActivities_SignOut(driver);

		campActivities_SignIN(driver, "SAM");
		System.out.println("SAM signed in successfully");
		campActivities_SignOut(driver);

		campActivities_SignIN(driver, "MM");
		System.out.println("MM signed in successfully");
		campActivities_SignOut(driver);

		campActivities_SignIN(driver, "IMD");
		System.out.println("IMD signed in successfully");
		campActivities_SignOut(driver);

		campActivities_SignIN(driver, "ESP");
		System.out.println("ESP signed in successfully");
		campActivities_SignOut(driver);

		campActivities_SignIN(driver, "SCM");
		System.out.println("SCM signed in successfully");
		campActivities_SignOut(driver);

	}

}
