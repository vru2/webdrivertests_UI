package testScriptsHQOthers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory; // This is included in poi-ooxml-3.6-20091214.jar


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.HQDataProvider;
import domainServices.HQ;
import domainServices.AirCommonMethod;

public class Air_Booking_MIS_Report_Check extends AirCommonMethod{
	String domain = "com";
	public RemoteWebDriver driver = null;
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	@Test(groups = "Regression", dataProviderClass = HQDataProvider.class, dataProvider = "B2CAirOWGDSDomTicketingFailure")
		public void autoTicketingFailure(String[] fromSet, String[] toSet, String app, String tripType, String flightPreference,
				String flightFilterType, String adults, String children, String infants, String paymentMethod,boolean insuranceRequired,
			String refundMethod) throws Exception {
		deleteFile();
		misDownload(driver, "");
		checkSizeofFile();
	}
	
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}
	
}

