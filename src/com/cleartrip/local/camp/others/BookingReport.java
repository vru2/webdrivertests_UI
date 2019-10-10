package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class BookingReport extends CampActivities {

	String fileName = "bookings.csv";

	@Parameters({ "user", "domain" })
	@Test
	public void vBookingReport(String user, String domain) {
		try {
			campActivities_SignIN(driver, user);
			driver.get(baseUrl + "/bookings");
			bookingReportVerification(fileName, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void vDirectBookingReport() {
		try {
			driver.get(baseUrl + "/bookings");
			bookingReportVerification(fileName, "direct");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void vWLBookingReport() {
		try {
			driver.get(baseUrl + "/bookings");
			bookingReportVerification(fileName, "wl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void vWLDirectBookingReport() {
		try {
			driver.get(baseUrl + "/bookings");
			bookingReportVerification(fileName, "both");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
