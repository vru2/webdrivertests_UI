package common;

import static common.CachedProperties.cacheUrlInstance;
import static common.CachedProperties.dataInstance;
import static common.CachedProperties.dbInstance;
import static common.CachedProperties.instance;
import static common.CachedProperties.objectReportInstance;
import static common.CachedProperties.platformInstance;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static common.CachedProperties.getTrip;
import static common.CachedProperties.objectReportInstancePlatform;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import static common.CachedProperties.objectReportInstancePayment;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import testrailpoc.testrail_utils;

public class CommonUtil {
	public CachedProperties common = instance();
	public CachedProperties dataFile = dataInstance();
	public CachedProperties db = dbInstance();
	public CachedProperties caheUrl = cacheUrlInstance();
	public CachedProperties objectRepos = objectReportInstance();
	public CachedProperties platform = platformInstance();
	public CachedProperties objectReposPlatform = objectReportInstancePlatform();
	public CachedProperties objectReposPayment = objectReportInstancePayment();
	public CachedProperties rubyAPITrips = getTrip();
	
	public String debug = common.value("debug");
	public boolean debugger = Boolean.parseBoolean(debug);
	StopWatch watch;
	
	public void printInfo(String Data) {
		if (debugger) {
//			System.out.println(Data);
		}
	}

	public String getBit() {
		String architecture = "os.arch";
		String bit = System.getProperty(architecture);
		// System.out.println("bit : " + bit);
		addLog(bit);

		return bit;
	}
	
	public String getValue(String objectKey) throws Exception {
		String[] curObject = objectRepos.value(objectKey).split(":");
		String objectValue = curObject[0];
		return objectValue;

	}
	
	public By getObject(String objectKey) throws Exception {
		String[] curObject = objectRepos.value(objectKey).split(":", 2);
		String objectType = curObject[0];
		String objectValue = curObject[1];
		// find by id
		if (objectType.equalsIgnoreCase("ID")) {
			return By.id(objectValue);
		}
		// find by XPATH
		else if (objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(objectValue);
		}
		// find by name
		else if (objectType.equalsIgnoreCase("NAME")) {
			return By.name(objectValue);
		}
		// Find by css
		else if (objectType.equalsIgnoreCase("CSS")) {
			return By.cssSelector(objectValue);
		}
		// find by link
		else if (objectType.equalsIgnoreCase("LINK")) {
			return By.linkText(objectValue);
		}
		// find by class
		else if (objectType.equalsIgnoreCase("CLASSNAME")) {
			return By.className(objectValue);
		}
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) {
			return By.partialLinkText(objectValue);

		} else {
			throw new Exception("Wrong object type:" + objectKey);
		}
	}
	
	public By getObjectPlatform(String objectKey) throws Exception {
		String[] curObject = objectReposPlatform.value(objectKey).split(":");
		String objectType = curObject[0];
		String objectValue = curObject[1];
		// find by id
		if (objectType.equalsIgnoreCase("ID")) {
			return By.id(objectValue);
		}
		// find by XPATH
		else if (objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(objectValue);
		}
		// find by name
		else if (objectType.equalsIgnoreCase("NAME")) {
			return By.name(objectValue);
		}
		// Find by css
		else if (objectType.equalsIgnoreCase("CSS")) {
			return By.cssSelector(objectValue);
		}
		// find by link
		else if (objectType.equalsIgnoreCase("LINK")) {
			return By.linkText(objectValue);
		}
		// find by class
		else if (objectType.equalsIgnoreCase("CLASSNAME")) {
			return By.className(objectValue);
		}
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) {
			return By.partialLinkText(objectValue);

		} else {
			throw new Exception("Wrong object type:" + objectKey);
		}
	}
	
	public void afterMethod(RemoteWebDriver driver, ITestResult _result) throws Exception {
		screenshot(_result, driver);
		afterMethodProcess(_result);
	}
	
	public String putLogDate() throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +0);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("_EEE_ddMMMyyyy_hhmmss").format(s);
		return dateString;
	}
	
	public void afterMethodProcess(ITestResult _result) throws Exception {
		if (watch != null) {
			watch.stop();
			addLog("Time taken by script : " + watch.toString());
		}
		if (common.value("testrailupdate").equalsIgnoreCase("true")) {
			testrail_utils util = new testrail_utils();
			util.UpdateTestRail(_result, common.value("travelprojectid"));
		}
	}
	public void screenshot(ITestResult result, RemoteWebDriver driver) throws Exception {

		String OS = System.getProperty("os.name");

		String filepath = "";
		String testName = result.getName();
		int Status = result.getStatus();


		if (Status == 2) {
			File file = new File(".");
			String filename = testName + putLogDate() + ".png";
			if ( OS.contains("Windows")){
				filepath = file.getCanonicalPath() + "\\ScreenShots\\" + filename;
			} else {
				filepath = file.getCanonicalPath() + "/ScreenShots/" + filename;
			}

			/*WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshotFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(filepath));
			addLog("<a href'" + filepath + "'>screenshot</a>");
			addLog("Screenshot Name :" + filename);
			Reporter.log("Screenshot Path :" + filename);
			addLog(driver.getCurrentUrl());
			System.out.println("Script Failed : "+testName);*/
		}
		/*else if (OS.contains("Linux")){

			if (Status == 2) {
				File file = new File(".");
				String filename = testName + putLogDate() + ".png";
				filepath = file.getCanonicalPath() + "/ScreenShots/" + filename;
				System.out.println("linux path " + filepath);
				WebDriver augmentedDriver = new Augmenter().augment(driver);
				File screenshotFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			 Files.copy(screenshotFile, new File(filepath));
				//FileUtils(screenshotFile, new File(filepath));
				addLog("<a href'" + filepath + "'>screenshot</a>");
				addLog("Screenshot Name :" + filename);
				System.out.println("screen sho linux captured");
			}


		}
*/	}
	

	public By getObjectPayment(String objectKey) throws Exception {
		String[] curObject = objectReposPayment.value(objectKey).split(":");
		String objectType = curObject[0];
		String objectValue = curObject[1];
		// find by id
		if (objectType.equalsIgnoreCase("ID")) {
			return By.id(objectValue);
		}
		// find by XPATH
		else if (objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(objectValue);
		}
		// find by name
		else if (objectType.equalsIgnoreCase("NAME")) {
			return By.name(objectValue);
		}
		// Find by css
		else if (objectType.equalsIgnoreCase("CSS")) {
			return By.cssSelector(objectValue);
		}
		// find by link
		else if (objectType.equalsIgnoreCase("LINK")) {
			return By.linkText(objectValue);
		}
		// find by class
		else if (objectType.equalsIgnoreCase("CLASSNAME")) {
			return By.className(objectValue);
		}
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) {
			return By.partialLinkText(objectValue);

		} else {
			throw new Exception("Wrong object type:" + objectKey);
		}
	}
	
	public void addLog(String logMessage) {
		Reporter.log("<p>"+logMessage+"</p>",false);
	}
	
	public void addLog(String logMessage,boolean printOnConsole) {
		if(printOnConsole) {
			Reporter.log("<p>"+logMessage+"</p>",false);
			System.out.println(logMessage+"\n");
		}else {
			Reporter.log("<p>"+logMessage+"</p>",false);
		}
	}
	
}
