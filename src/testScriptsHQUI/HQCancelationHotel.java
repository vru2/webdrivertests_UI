// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsHQUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.HQ;

public class HQCancelationHotel extends HQ {
	public RemoteWebDriver driver = null;
	String tripId = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void HQ_HotelCancel() throws Exception {
		String HotelTripID = db_HotelTrip().get(0);
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		driver.get(getBaseUrl(domain) + "/hq/trips/"+HotelTripID);
		logURL(driver);
		elementPresent_log(driver, By.linkText("Cancel trip"), "Cancel Link ", 10);
		textPresent_Log(driver, "Itinerary", 5);
		safeClick(driver, By.linkText("Cancel trip"));
		elementPresent_log(driver, By.id("add_note"), "add_note ", 10);
		safeType(driver, By.id("add_note"), "Cancel text");
		safeClick(driver, By.xpath("//input[@value='Make cancellations']"));
		Thread.sleep(2000);
		if(elementVisible(driver, By.linkText("Cancel trip"), 5)) {
			Reporter.log("Cancelation link is displayed after cancelation");
			Assert.assertTrue(false);
		}
		
	}
	
	public ArrayList<String> db_HotelTrip() throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";
			String query =  "select TRIP_REF from trips where Hotel=1 and BOOKING_STATUS='P' AND TRIP_REF LIKE 'Q%' AND DOMAIN = 'cleartrip.com' AND CREATED_AT IS NOT NULL order by created_at desc";
			Connection myCon = DriverManager.getConnection(url, user, password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
				ResultSetMetaData result = myRes.getMetaData();
					for (int x = 1; x <= 1; x++) {
							String colName = result.getColumnName(x);
							String colValue = myRes.getString(x);
							Name.add(colName);
							data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}