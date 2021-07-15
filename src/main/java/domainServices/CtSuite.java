package domainServices;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;

import commonServices.WrapperMethod;

public class CtSuite extends WrapperMethod {

	protected WebDriverWait wait;
	public final String agency = "Agency", corprate = "Corporate", b2c = "b2c", whiteLabel = "whiteLabel",
			mobile = "mobile", exclude = "Exclude", maxAdult = "Max adults", maxChild = "Max Children",
			maxOccupancy = "Max Occupancy", minRoom = "Min Rooms", nonRefund = "non-refundable", refund = "refundable",
			upto = "Upto";
	public String[] applicability = { "", "Extra Bed Rate", "Child No Bed Rate", "Child Extra Bed Rate" };
	
	public RemoteWebDriver getDriver(RemoteWebDriver driver,String browser) throws IOException {

		DesiredCapabilities capability = null;

		switch (browser) {
		case "chrome":
			if (System.getProperty("os.name").contains("Windows")) {
				String filepath = "C:\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", filepath);
			} else {
				String filepath = "C:\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", filepath);
			}
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
			chromePrefs.put("download.prompt_for_download", false);
			//chromePrefs.put("download.default_directory", DOWNLOADPATH);
			chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			options.addArguments("--disable-extensions");

			// setting the capabilities with the above preference
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(cap);
			break;

		case "firefox":
			String filepath = "C:\\geckodriver.exe";
			if (System.getProperty("os.name").contains("Windows")) {
				
				System.setProperty("webdriver.gecko.driver", filepath);
			} 
			FirefoxProfile profile = null;
			ProfilesIni pfIntail = new ProfilesIni();
			try {
				profile = pfIntail.getProfile("autoprofile");
				if (profile.toString() == null) {
					profile = new FirefoxProfile();
				}
			} catch (NullPointerException npe) {
				profile = new FirefoxProfile();
			}
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"application/pdf,application/force-download,application/x-download,text/csv,text/plain,image/jpeg,application/vnd.ms-excel,application/zip,text/pdf");
			profile.setPreference("browser.helperApps.neverAsk.openFile",
					"application/pdf,application/force-download,application/x-download,text/csv,text/plain,image/jpeg,application/vnd.ms-excel,application/zip,"
							+ "text/pdf,application/download");
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("pdfjs.disabled", true);
			capability = DesiredCapabilities.firefox();
			capability.setCapability("unexpectedAlertBehaviour", "ignore");
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new FirefoxDriver(capability);
			break;
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	
	
	
	

	public void ctSuiteLogin(RemoteWebDriver driver, String userName, String password) throws Exception {
		wait = new WebDriverWait(driver, 50000);
		if (ctsuite.value("host").equals("qa2")) {
			driver.get("https://" + ctsuite.value("host") + ctsuite.value("suiteUrl"));
		} else {
			driver.get("https://" + ctsuite.value("suiteUrl"));
		}

		if ((waitElement(driver, getObjectCtSuite("login"), 10)) == false) {
			Reporter.log("Unable to load CtSuite page", true);
			AssertJUnit.fail();
		}

		safeType(driver, getObjectCtSuite("login"), userName);
		safeType(driver, getObjectCtSuite("password"), password);
		safeClick(driver, getObjectCtSuite("loginBtn"));
		waitForElementVisibility(driver, getObjectCtSuite("logoutBtn"), 10);
		AssertJUnit.assertTrue("Unable to find logout button", isElementPresent(driver, getObjectCtSuite("logoutBtn")));
		Reporter.log("User login successfully", true);

	}

	public void selectHotel(RemoteWebDriver driver, String type, String name) throws Exception {
		wait = new WebDriverWait(driver, 50000);
		safeClick(driver, getObjectCtSuite("selectTypeClick"));
		driver.findElement(By.xpath(getXpathByReplace(objectReposCtSuite.value("hotelSelectType"), type))).click();
		safeType(driver, getObjectCtSuite("hotelNameBox"), name);
		driver.findElement(getObjectCtSuite("hotelNameBox")).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(500);
		safeClick(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("hotelNameSelect"), name)));
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(driver, getObjectCtSuite("hotelNameHeader")), "Hotel selection is failed");
		Reporter.log("hotel selected " + name, true);
	}

	public void updateInventoryForSingleDate(RemoteWebDriver driver, String date) throws Exception {
		Select select = new Select(driver.findElement(getObjectCtSuite("calendarRoomType")));
		List<WebElement> ls = select.getOptions();
		for (WebElement e : ls) {
			select.selectByVisibleText(e.getText());
			safeType_NonClear(driver, getObjectCtSuite("calendarStartDate"), date);
			driver.findElement(getObjectCtSuite("calendarStartDate")).sendKeys(Keys.TAB);
		}

	}

	public void vRateDashBoard(RemoteWebDriver driver, String roomType, String rateName) throws Exception {
		safeClick(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("suiteTabs"), "rates")));
		Thread.sleep(1000);
		vListItem(driver, getObjectCtSuite("rateRoomType"), roomType);
		selectItemFromList(driver, getObjectCtSuite("rateRoomType"), roomType);
		rateActions(driver, rateName, "Active");
		Thread.sleep(1000);
		safeClick(driver, getObjectCtSuite("createRateBtn"));

	}

	public void rateCreationBasic(RemoteWebDriver driver, HashMap<String, String> parms) throws Exception {
		int tmp;

		safeClick(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("rateMealType"), parms.get("mealType"))));
		if (parms.get("commission") != null) {
			safeType(driver, getObjectCtSuite("rateCommissionInput"), parms.get("commission"));
			Assert.assertTrue(getAttribute(driver, getObjectCtSuite("rateCommissionInput"), "value")
					.equals(parms.get("commission")), "Unable to set commission");
			Reporter.log("Commission set to " + parms.get("commission"), true);
		}
		if (parms.get("applicabilityBolean") != null) {
			int cnt = Integer.parseInt(parms.get("applicability"));

			if (parms.get("applicabilityBolean").equals("yes")) {
				while (cnt > 0) {
					tmp = cnt % 10;
					if (tmp >= 0) {
						safeClick(driver, By.xpath(
								getXpathByReplace(objectReposCtSuite.value("rateApplicability"), applicability[tmp])));
						cnt = cnt / 10;
					}
				}
			}
		}
		if (parms.get("ChannelSellMobileBolean") != null && parms.get("ChannelSellMobileBolean").equals("no")) {
			safeClick(driver, By.xpath((getXpathByReplace(objectReposCtSuite.value("rateChannelType"), mobile))));
			Reporter.log("Sell channel mobile is disable", true);
		}
		if (parms.get("ChannelSellWhitLabelBolean") != null && parms.get("ChannelSellWhitLabelBolean").equals("no")) {
			safeClick(driver, By.xpath((getXpathByReplace(objectReposCtSuite.value("rateChannelType"), whiteLabel))));
			Reporter.log("Sell channel whiteLabel is disable", true);
		}
		if (parms.get("ChannelSellB2cBolean") != null && parms.get("ChannelSellB2cBolean").equals("no")) {
			safeClick(driver, By.xpath((getXpathByReplace(objectReposCtSuite.value("rateChannelType"), b2c))));
			Reporter.log("Sell channel b2c is disable", true);
		}
		if (parms.get("ChannelSellAgencyBolean") != null && parms.get("ChannelSellAgencyBolean").equals("no")) {
			safeClick(driver, By.xpath((getXpathByReplace(objectReposCtSuite.value("rateChannelType"), agency))));
			Reporter.log("Sell channel agency is disable", true);
		}
		if (parms.get("ChannelSellCorpBolean") != null && parms.get("ChannelSellCorpBolean").equals("no")) {
			safeClick(driver, By.xpath((getXpathByReplace(objectReposCtSuite.value("rateChannelType"), corprate))));
			Reporter.log("Sell channel Corp is disable", true);
		}
		if (parms.get("ChannelSellAgencyBolean") != null && parms.get("ChannelSellAgencyBolean").equals("yes")) {
			safeType(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("agencyCorpInputBox"), agency)),
					ctsuite.value("agencyName"));
			Thread.sleep(500);
			driver.findElement(By.xpath(getXpathByReplace(objectReposCtSuite.value("agencyCorpInputBox"), agency)))
					.sendKeys(Keys.TAB);
			;
			safeClick(driver,
					By.xpath(getEleByMultiReplace(objectReposCtSuite.value("rateAgencyCorpSelection"), agency, "all")));
			Reporter.log("Sell channel Agency is enable for " + ctsuite.value("agencyName"), true);
		}
		if (parms.get("ChannelSellCorpBolean") != null && parms.get("ChannelSellCorpBolean").equals("yes")) {
			safeType(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("agencyCorpInputBox"), corprate)),
					ctsuite.value("corpName"));
			Thread.sleep(500);
			driver.findElement(By.xpath(getXpathByReplace(objectReposCtSuite.value("agencyCorpInputBox"), corprate)))
					.sendKeys(Keys.TAB);
			;
			safeClick(driver, By
					.xpath(getEleByMultiReplace(objectReposCtSuite.value("rateAgencyCorpSelection"), corprate, "all")));
			Reporter.log("Sell channel Corp is enable for " + ctsuite.value("corpName"), true);
		}
		if (parms.get("ChannelSellApiExcludeBolean") != null
				&& parms.get("ChannelSellApiExcludeBolean").equals("all")) {
			safeClick(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("apiCheckBox"), exclude)));
			safeClick(driver, getObjectCtSuite("apiExcludeAddBtn"));
			driver.findElement(getObjectCtSuite("apiUserInputBox")).sendKeys(ctsuite.value("apiPartnerId"));
			// safeType(driver,getObjectCtSuite("apiUserInputBox"),ctsuite.value("apiPartnerId"));
			safeClick(driver, By.xpath(
					getXpathByReplace(objectReposCtSuite.value("apiAutosearchUser"), ctsuite.value("apiPartnerId"))));
			Reporter.log("Sell channel API is enable for " + ctsuite.value("apiPartnerId"), true);
		}
		safeClick(driver, getObjectCtSuite("rateNextBtn"));
		ratePageError(driver);
		Reporter.log("Basic details for rate is completed", true);
	}

	public void rateCreationPriceing(RemoteWebDriver driver, HashMap<String, String> inputs) throws Exception {
		int tableRowSize = driver.findElements(getObjectCtSuite("rateTable")).size();
		String val;
		int cnt = 0;
		for (int i = 1; i <= tableRowSize; i++) {
			for (int j = 2; j <= 3; j++) {
				val = objectReposCtSuite.value("rateTableVal") + "[" + i + "]/td[" + j + "]/input";
				if (i == 1) {
					driver.findElement(By.xpath(val)).sendKeys(inputs.get("singleRate"));
					cnt++;
					if (cnt == 2) {
						Reporter.log("Single rate set to " + inputs.get("singleRate"), true);
						cnt = 0;
					}
				}
				if (i == 2) {
					driver.findElement(By.xpath(val)).sendKeys(inputs.get("doubleRate"));
					cnt++;
					if (cnt == 2) {
						Reporter.log("Single rate set to " + inputs.get("doubleRate"), true);
						cnt = 0;
					}
				}
				if (i == 3) {
					driver.findElement(By.xpath(val)).sendKeys(inputs.get("ebr"));
					cnt++;
					if (cnt == 2) {
						Reporter.log("Single rate set to " + inputs.get("ebr"), true);
						cnt = 0;
					}
				}
				if (i == 4) {
					driver.findElement(By.xpath(val)).sendKeys(inputs.get("cxb"));
					cnt++;
					if (cnt == 2) {
						Reporter.log("Single rate set to " + inputs.get("cxb"), true);
						cnt = 0;
					}
				}
				if (i == 5) {
					driver.findElement(By.xpath(val)).sendKeys(inputs.get("cnb"));
					cnt++;
					if (cnt == 2) {
						Reporter.log("Single rate set to " + inputs.get("cnb"), true);
						cnt = 0;
					}
				}
			}
		}

		if (inputs.get("childFree") != null) {
			safeClick(driver,
					By.xpath(getXpathByReplace(objectReposCtSuite.value("childFreeUpto"), inputs.get("childFree"))));
			Reporter.log("child free age set to " + inputs.get("childFree"), true);
		}
		if (inputs.get("childNoBed") != null) {
			safeClick(driver, By
					.xpath(getXpathByReplace(objectReposCtSuite.value("childBedAgeRange"), inputs.get("childNoBed"))));
			Reporter.log("child Bed age range set to " + inputs.get("childNoBed"), true);
		}
		if (inputs.get("childExtraBed") != null) {
			safeClick(driver, By.xpath(
					getXpathByReplace(objectReposCtSuite.value("childExtraAgeRange"), inputs.get("childExtraBed"))));
			Reporter.log("child extra Bed  range set to " + inputs.get("childExtraBed"), true);
		}

		if (inputs.get("maxAdultVal") != null) {
			safeClick(driver, By.xpath(getEleByMultiReplace(objectReposCtSuite.value("rateOccupancyRange"), maxAdult,
					inputs.get("maxAdultVal"))));
			Reporter.log("Max adult range set to " + inputs.get("maxAdultVal"), true);
		}
		if (inputs.get("maxChildVal") != null) {
			safeClick(driver, By.xpath(getEleByMultiReplace(objectReposCtSuite.value("rateOccupancyRange"), maxChild,
					inputs.get("maxChildVal"))));
			Reporter.log("Max child range set to " + inputs.get("maxChildVal"), true);
		}
		if (inputs.get("maxOccupancyVal") != null) {
			safeClick(driver, By.xpath(getEleByMultiReplace(objectReposCtSuite.value("rateOccupancyRange"),
					maxOccupancy, inputs.get("maxOccupancyVal"))));
			Reporter.log("Max occupancy range set to " + inputs.get("maxOccupancyVal"), true);
		}
		if (inputs.get("minRoomVal") != null) {
			safeClick(driver, By.xpath(getEleByMultiReplace(objectReposCtSuite.value("rateOccupancyRange"), minRoom,
					inputs.get("minRoomVal"))));
			Reporter.log("Min room range set to " + inputs.get("minRoomVal"), true);
		}

		safeClick(driver, getObjectCtSuite("rateNextBtn"));
		ratePageError(driver);
		Reporter.log("Pricing and restrictions for rate is completed", true);
	}

	public void rateInclusions(RemoteWebDriver driver, ArrayList<String> inputs) throws Exception {
		for (String str : inputs) {
			javaExecutorClick(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("inclusionCheckBox"), str)));
			Reporter.log("Inclusion " + str + " is selected", true);
		}
		safeClick(driver, getObjectCtSuite("rateNextBtn"));
		Reporter.log("Inclusions for rate is completed", true);
	}

	public void rateActions(RemoteWebDriver driver, String rateName, String status) throws Exception {
		List<WebElement> list = driver
				.findElements(By.xpath(getXpathByReplace(objectReposCtSuite.value("activeRateName"), rateName)));
		int size = list.size();
		if (size == 0) {
			Reporter.log("No active rates are available with this name " + rateName, true);
			return;
		} else {
			if (status.equals("Active")) {
				for (WebElement we : list) {
					safeClick(driver,
							By.xpath(getXpathByReplace(objectReposCtSuite.value("rateswitchToggle"), rateName)));
					safeClick(driver, getObjectCtSuite("rateStatusChangeBtn"));
					Reporter.log("Rate status changed to disable", true);
				}
			} else if (status.equals("All")) {
				safeClick(driver, getObjectCtSuite("allPlanTab"));
				for (WebElement we : list) {
					safeClick(driver,
							By.xpath(getXpathByReplace(objectReposCtSuite.value("rateswitchToggle"), rateName)));
					safeClick(driver, getObjectCtSuite("rateStatusChangeBtn"));
					Reporter.log("Rate status changed to enable", true);
				}

			}
		}

	}

	public void rateCreateConfirm(RemoteWebDriver driver, String rateName) throws Exception {
		Boolean flag = false;
		safeClick(driver, getObjectCtSuite("rateSaveBtn"));
		Thread.sleep(10000);
		safeClick(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("suiteTabs"), "rates")));
		List<WebElement> list = driver
				.findElements(By.xpath(getXpathByReplace(objectReposCtSuite.value("activeRateName"), rateName)));
		for (WebElement we : list) {
			if (we.getText().equals(rateName)) {
				Reporter.log(rateName + " displayed on rate Page", true);
				flag = true;
			}

		}
		if (flag = false) {
			Reporter.log("Unable to find rate at rate Page", true);
			Assert.fail();
		}
	}

	public void rateCancelPolicy(RemoteWebDriver driver, HashMap<String, String> inputs) throws Exception {

		if (inputs.get("cancelPolicyType") != null && inputs.get("cancelPolicyType").equals(nonRefund)) {
			selectItemFromList(driver, getObjectCtSuite("bookingPolicy"), nonRefund);
			Reporter.log("Cancel policy set" + nonRefund, true);
		} else if (inputs.get("cancelPolicyType") != null && inputs.get("cancelPolicyType").equals(refund)) {
			if (inputs.get("cancelTime") != null && inputs.get("cancelTime").equals(upto)) {
				int cnt = Integer.parseInt(inputs.get("defaultCancelPolicyCnt"));
				int val = cnt;
				for (int i = 1; i <= cnt; i++) {
					selectItemFromList(driver,
							By.xpath(getXpathByReplace(objectReposCtSuite.value("defaultCancelPolicyLevel1"),
									String.valueOf(i))),
							inputs.get("cancelTime"));
					Reporter.log("Cancel policy set" + refund, true);
					selectItemFromList(driver,
							By.xpath(getXpathByReplace(objectReposCtSuite.value("defaultCancelPolicyLevel2"),
									String.valueOf(i))),
							inputs.get("timeRange"));
					Reporter.log("time range set to " + inputs.get("timeRange"), true);
					selectItemFromList(driver,
							By.xpath(getXpathByReplace(objectReposCtSuite.value("defaultCancelPolicyLevel3"),
									String.valueOf(i))),
							inputs.get("timeType"));
					Reporter.log("time type set to " + inputs.get("timeType"), true);

					if (inputs.get("chargeType") != null && inputs.get("chargeValue") != null) {
						selectItemFromList(driver,
								By.xpath(getXpathByReplace(objectReposCtSuite.value("defaultCancelPolicyLevel4"),
										String.valueOf(i))),
								inputs.get("chargeType"));
						Reporter.log("charge type set to " + inputs.get("chargeType"), true);
						safeType(driver,
								By.xpath(getXpathByReplace(objectReposCtSuite.value("defaultCancelAmtInputBox"),
										String.valueOf(i))),
								inputs.get("chargeValue"));
						Reporter.log("charge value set to " + inputs.get("chargeValue"), true);
					}

					if (val > 1) {
						safeClick(driver,
								By.xpath(getXpathByReplace(objectReposCtSuite.value("defaultCancelpolicyAddBtn"),
										String.valueOf(i))));
						val--;
					}
				}
			}
		}
	}

	public void vAthotelPlatform(RemoteWebDriver driver, HashMap<String, String> inputs, String roomType)
			throws Exception {
		String str;
		ArrayList<String> tabs;
		LocalDate currentDate = LocalDate.now();
		IndiaHotels hotelMethod = new IndiaHotels();
		((JavascriptExecutor) driver).executeScript("window.open()");
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(baseUrl+"/hotels");
		//safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
		safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"),
				getObjectHotels("HotelCom_HomePage_SearchAjax"), inputs.get("city"));
		selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"),
				getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 1, String.valueOf(currentDate.getDayOfMonth()));
		selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"),
				getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0,
				String.valueOf(currentDate.getDayOfMonth() + Integer.parseInt(inputs.get("dayGap"))));
		safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
		hotelMethod.hotelCom_SRP_Select_HotelName(driver, ctsuite.value("hotelName"));
		String Booking_Hotel = getText(driver, getObjectHotels("HotelCom_SRP_HotelName"));
		Reporter.log("The Hotel Being Booked is " + Booking_Hotel, true);
		safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button"));
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		str = getXpathByReplace(objectReposCtSuite.value("hotelMoreInclusionBtn"), roomType);
		if(isElementPresent(driver, By.xpath(str))) {
			safeClick(driver, By.xpath(str));
		}
		
		List<WebElement> inclusionList = driver
				.findElements(By.xpath(getXpathByReplace(objectReposCtSuite.value("hotelRoomInclusions"), roomType)));
		Reporter.log("Inclusions list at platform Side", true);
		for (WebElement we : inclusionList) {
			Reporter.log(we.getText(), true);
		}

		Reporter.log("Hotel cancel Policy", true);
		Reporter.log(getAttribute(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("hotelPolicy"), roomType)),"original-title"),
				true);
		Reporter.log("Hotel Price", true);
		Reporter.log(getText(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("hotelRatePrice"), roomType))),
				true);
		safeClick(driver, By.xpath(getXpathByReplace(objectReposCtSuite.value("hotelRatebookBtn"), roomType)));
		Reporter.log("Clicked on book for this hotel " + roomType, true);
		driver.close();
		driver.switchTo().window(tabs.get(1));
		driver.close();
		driver.switchTo().window(tabs.get(0));

	}

	public void selectItemFromList(RemoteWebDriver driver, By loc, String text) {
		Select select = new Select(driver.findElement(loc));
		List<WebElement> list = select.getOptions();
		for (WebElement e : list) {
			if (e.getText().contains(text)) {
				select.selectByVisibleText(e.getText());
				return;
			}
		}

	}

	public void vListItem(RemoteWebDriver driver, By loc, String text) {
		boolean eleAvailable = false;
		Select select = new Select(driver.findElement(loc));
		List<WebElement> list = select.getOptions();

		for (WebElement e : list) {
			if (e.getText().contains(text)) {
				Reporter.log(text + " found into list", true);
				eleAvailable = true;
				return;
			}
		}

		if (eleAvailable == false) {
			Reporter.log(text + " isn't found into list", true);
			AssertJUnit.assertTrue(false);
		}

	}

	public String getEleByMultiReplace(String loc, String replacedVal, String replaced2ndVal) {
		String xpath;
		xpath = loc.replaceAll("(?i)" + "nameLbl", replacedVal);
		xpath = xpath.replaceAll("(?i)" + "valueLbl", replaced2ndVal);
		return xpath;
	}

	public void ratePageError(RemoteWebDriver driver) throws Exception {
		if (isElementPresent(driver, getObjectCtSuite("rateErrorMessage"))) {
			Reporter.log("There is error into rate creation", true);
			Reporter.log("Error:" + getText(driver, getObjectCtSuite("rateNextBtn")), true);
			Assert.fail();
		}

	}
}
