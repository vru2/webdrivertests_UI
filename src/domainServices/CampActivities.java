	// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - MAR, 2015
// Author - Mohamed Faisal
// Copyright © 2015 cleartrip Travel. All right reserved.
package domainServices;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
//import org.apache.tools.ant.types.Assertions.EnabledAssertion;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import commonServices.WrapperMethod;

public class CampActivities extends WrapperMethod {

	public RemoteWebDriver driver;
	public String baseUrl;
	final public String DOWNLOADPATH = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;
	public String restroName, locator, chainName;
	protected WebDriverWait wait;
	public JavascriptExecutor jse;
	

	@Parameters({ "env", "domain", "browser" })
	@BeforeClass
	public void setUp(String env, String domain, String browser) throws Exception {
		if (env.equalsIgnoreCase("qa")) {
			if (domain.equalsIgnoreCase("com")) {
				baseUrl = campLocal.value("campQaComUrl");
			} else if (domain.equalsIgnoreCase("ae")) {
				baseUrl = campLocal.value("campQaAeUrl");
			}
		} else if (env.equalsIgnoreCase("prod")) {
			if (domain.equalsIgnoreCase("com")) {
				baseUrl = campLocal.value("campUrlComProd");
			} else if (domain.equalsIgnoreCase("ae")) {
				baseUrl = campLocal.value("campUrlAeProd");
			}
		} else if (env.equalsIgnoreCase("hf")) {
			if (domain.equalsIgnoreCase("com")) {
				baseUrl = campLocal.value("campUrlComHf");
			}
		}
		getDriver(browser);
		wait = new WebDriverWait(driver,50000);
		jse = (JavascriptExecutor) driver;
		driver.get(baseUrl + "/accounts/sign_in");
	}

	//@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result){
		try {
			afterMethod_Local(driver, _result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
		driver = null;
	}

	public void getDriver(String browser) throws IOException {

		DesiredCapabilities capability = null;

		switch (browser) {
		case "chrome":
			if (System.getProperty("os.name").contains("Windows")) {
				File file = new File(".");
				String filepath = "C:\\chromedriver.exe";
				/*String filepath = file.getCanonicalPath() + file.separator + "exe" + file.separator
						+ "chromedriver.exe";*/
				System.setProperty("webdriver.chrome.driver", filepath);
			} else {
				File file = new File(".");
				String filepath = "C:\\chromedriver.exe";
				//String filepath = file.getCanonicalPath() + "//exe//chromedriver";
				System.setProperty("webdriver.chrome.driver", filepath);
			}
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
			chromePrefs.put("download.prompt_for_download", false);
			chromePrefs.put("download.default_directory", DOWNLOADPATH);
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
			if (System.getProperty("os.name").contains("Windows")) {
				File file = new File(".");
				String filepath = file.getCanonicalPath() + "//exe//geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", filepath);
			} else {
				File file = new File(".");
				String filepath = file.getCanonicalPath() + "//exe//geckodriver";
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
	}

	public void apiHost(RemoteWebDriver driver) throws Exception {
		if (common.value("host").contains("qa2")) {
			String url = "http://qa2.cleartrip.com";

		} else if (common.value("host").contains("dev")) {
			String url = "http://airpartners.cleartrip.com";
		}

	}

	public void campActivities_SignIN(RemoteWebDriver driver, String Login_Type) throws Exception {
		wait = new WebDriverWait(driver,50000);
		if((waitElement(driver, getObject("Camp_Activities_SignIn_UserName"), 10))==false){
			Reporter.log("Unable to load camp page",true);
			Assert.fail();
		}
		if(isElementPresent(driver, By.xpath("//div[@class='error_msg']"))) {
			Reporter.log("Unable to login page,thorwing error",true);
			org.testng.Assert.fail();
			
		}
		//elementClickable(driver, getObject("Camp_Activities_SignIn_UserName"), 30);
		// elementPresent(driver, getObject("Camp_Activities_SignIn_UserName"));

		if (campLocal.value("host").contains("qa2")) {
			if (Login_Type.equalsIgnoreCase("CE")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_CE"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_CE"));
			} else if (Login_Type.equalsIgnoreCase("CM")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_CM"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_CM"));
			} else if (Login_Type.equalsIgnoreCase("COP")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_COP"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_COP"));
			} else if (Login_Type.equalsIgnoreCase("ESP")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_ESP"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_ESP"));
			} else if (Login_Type.equalsIgnoreCase("IMD")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_IMD"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_IMD"));
			} else if (Login_Type.equalsIgnoreCase("SA")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_SA"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_SA"));
			} else if (Login_Type.equalsIgnoreCase("SAM")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_SAM"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_SAM"));
			} else if (Login_Type.equalsIgnoreCase("SCM")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_SCM"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_SCM"));
			} else if (Login_Type.equalsIgnoreCase("MM")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_MM"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_MM"));
			} else if (Login_Type.equalsIgnoreCase("SAProduction")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_SAProd"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_SAProd"));
			}
			safeClick(driver, getObject("Camp_Activities_SignIn_Button"));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
			Thread.sleep(1000);
			if(isElementPresent(driver, By.xpath("//div[text()='Invalid Username/Password']")) == true) {
				Reporter.log("Login failed",true);
				org.testng.Assert.fail();
			}
			// elementVisible(driver, getObject("Camp_Activities_DashboardTab"), 50,
			// "Homepage after SignIN is not displayed");
			if(elementClickable(driver, getObject("Camp_Activities_DashboardTab"), 50)==false) {
				Reporter.log("Unable to load dashboard",true);
			};
		}

		else if (campLocal.value("host").contains("dev")) {
			if (Login_Type.equalsIgnoreCase("CE")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_CE"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_CE"));
			} else if (Login_Type.equalsIgnoreCase("CM")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_CM"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_CM"));
			} else if (Login_Type.equalsIgnoreCase("COP")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_COP"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_COP"));
			} else if (Login_Type.equalsIgnoreCase("ESP")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),campLocal.value("prodESPUser"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),campLocal.value("prodESPPwd"));
			} else if (Login_Type.equalsIgnoreCase("IMD")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						dataFile.value("CampActivities_UserName_IMD"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						dataFile.value("CampActivities_Password_IMD"));
			} else if (Login_Type.equalsIgnoreCase("SA")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						campLocal.value("prodSAUser"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						campLocal.value("prodSAPwd"));
			} else if (Login_Type.equalsIgnoreCase("SAM")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),campLocal.value("prodSAMUser"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),campLocal.value("prodSAMPwd"));
			} else if (Login_Type.equalsIgnoreCase("SCM")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						campLocal.value("prodSCMUser"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						campLocal.value("prodSCMPwd"));
			} else if (Login_Type.equalsIgnoreCase("MM")) {
				safeType(driver, getObject("Camp_Activities_SignIn_UserName"),
						campLocal.value("prodMMUser"));
				safeType(driver, getObject("Camp_Activities_SignIn_Password"),
						campLocal.value("prodMMPwd"));
			}

			safeClick(driver, getObject("Camp_Activities_SignIn_Button"));
			Thread.sleep(5000);
			if(isElementPresent(driver, By.xpath("//div[text()='Invalid Username/Password']")) == true) {
				Reporter.log("Login failed",true);
				org.testng.Assert.fail();
			}

		}
		
		if(isElementVisible(driver, By.linkText("Activity"))==true) {
			Reporter.log("Login sucessfully", true);
		}
		/*if (!elementVisible(driver, By.linkText("Activity"), 2, "Unable to signin")) {
			campActivities_SignIN(driver, Login_Type);
		}*/
	}

	public void campActivities_SignOut(RemoteWebDriver driver) throws Exception {
		elementClickable(driver, getObject("Camp_Activities_Account_Tab"), 30);
		Thread.sleep(3000);
		safeClick(driver, getObject("Camp_Activities_Account_Tab"));
		
		Thread.sleep(2000);
		safeClick(driver, By.linkText("Logout"));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		if(elementClickable(driver, getObject("Camp_Activities_SignIn_UserName"), 30)==false) {
			Reporter.log("Sign out didn't happen properly",true);
		};
	}

	public void campActivities_LoadDashBoard(RemoteWebDriver driver) throws Exception {
		if (elementClickable(driver, getObject("Camp_Activities_Dashboard_Publishedactivity"), 20)) {
			Reporter.log("Dashboard Loaded");
		} else {
			assertTrue(false);
		}
	}

	public void camp_HomePage_Select_ActivityType(RemoteWebDriver driver, String ActivityType, String Text)
			throws Exception, InterruptedException {
		elementVisible(driver, getObject("Camp_Activities_Dashboard_Activity"), 20, "home page not loaded");
		mouseHover(driver, getObject("Camp_Activities_Dashboard_Activity"));
		Thread.sleep(1000);
		// safeClick(driver, getObject("Camp_Activities_Dashboard_Activity"));
		safeClick(driver, By.linkText(ActivityType));
		if (!textPresent(driver, Text, 2)) {
			elementVisible(driver, getObject("Camp_Activities_Dashboard_Activity"), 20, "");
			mouseHover(driver, getObject("Camp_Activities_Dashboard_Activity"));
			// safeClick(driver, getObject("Camp_Activities_Dashboard_Activity"));
			Thread.sleep(1000);
			safeClick(driver, By.linkText(ActivityType));
		}
	}

	public void camp_HomePage_Select_ManageData(RemoteWebDriver driver, String ManageType, String Text)
			throws Exception, InterruptedException {
		elementVisible(driver, getObject("Camp_Activities_Dashboard_ManageData"), 20, "home page not loaded");
		safeClick(driver, getObject("Camp_Activities_Dashboard_ManageData"));
		safeClick(driver, By.linkText(ManageType));
		if (!textPresent(driver, Text, 2)) {
			elementVisible(driver, getObject("Camp_Activities_Dashboard_ManageData"), 20, "");
			safeClick(driver, getObject("Camp_Activities_Dashboard_ManageData"));
			Thread.sleep(1000);
			safeClick(driver, By.linkText(ManageType));
		}
	}

	public void SendActivity_For_Approval(RemoteWebDriver driver) throws Exception {
		elementPresent_Time(driver, By.linkText("Submit For Approval"), 1);
		safeClick(driver, By.linkText("Submit For Approval"));
		assertTrue("Activity Not Created", textPresent(driver, "Activity Updated Successfully", 10));
		// System.out.println("Activity Updated Successfully for Approval");
		Reporter.log("Activity Updated Successfully for Approval");
		Thread.sleep(5000);
	}

	public void RejectActivity(RemoteWebDriver driver) throws Exception {
		elementClickable(driver, By.linkText("Reject"), 30);
		// elementPresent_Time(driver, By.linkText("Reject"), 10);
		safeClick(driver, By.linkText("Reject"));
		Thread.sleep(5000);
		/*
		 * textPresent(driver, "Activity Rejected", 10);
		 * assertTrue("Activity Not Rejected", getText(driver,
		 * By.cssSelector("div.notice")).contains("Activity Rejected"));
		 */

	}

	public void ApproveActivity(RemoteWebDriver driver, String ActivityID) throws Exception {
		elementPresent_Time(driver, By.linkText("Approve Accuracy"), 20);
		Thread.sleep(2000);
		safeClick(driver, By.linkText("Edit"));
		safeType(driver, getObject("Camp_Activities_Form_Support_Email"), "kiran.naidu@cleartrip.com");
		safeClick(driver, By.linkText("Save"));
		textPresent(driver, "Activity Updated Successfully", 10);
		safeClick(driver, getObject("Camp_Activities_Form_Approve_Accuracy_Btn"));
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		textPresent(driver, "Accuracy approved Successfully", 10);
		assertTrue("Activity Not Approved",
				getText(driver, By.cssSelector("div.success")).contains("Accuracy approved Successfully"));
		// System.out.println("Accuracy approved Successfully");
		Thread.sleep(5000);
	}

	public void ApproveContent(RemoteWebDriver driver, String ActivityID) throws Exception {
		elementPresent_Time(driver, By.linkText("Approve Content"), 20);
		Thread.sleep(2000);
		safeClick(driver, By.linkText("Edit"));
		safeClick(driver, By.xpath("//form[@id='stepOneForm']/div[2]/div[6]/div[2]/div/div/button"));
		safeClick(driver, By.xpath("//li[3]/label/input"));
		safeClick(driver, By.xpath("//div[7]"));
		safeClick(driver, By.linkText("Approve Content"));
		textPresent(driver, "Activity Updated Successfully", 10);

		// assertTrue("Activity Not Approved", getText(driver,
		// By.cssSelector("div.success")).contains("Activity Updated Successfully"));
		// System.out.println("Activity Content Approved Successfully");
		Reporter.log("Activity Content Approved Successfully");
		Thread.sleep(5000);
	}

	public void ApprovePricing(RemoteWebDriver driver, String ActivityID) throws Exception {
		Thread.sleep(5000);
		elementPresent_Time(driver, By.linkText("Approve Pricing"), 15);
		safeClick(driver, By.linkText("Approve Pricing"));
		textPresent(driver, "Activity Updated Successfully", 10);
		// assertTrue("Approve Pricing Not Updated", getText(driver,
		// By.cssSelector("div.success")).contains("Activity Updated Successfully"));
		// System.out.println("Activity Pricing Approved Successfully");
		Reporter.log("Activity Pricing Approved Successfully");
		Thread.sleep(5000);
	}

	public void PublishActivity(RemoteWebDriver driver, String ActivityID) throws Exception {
		Thread.sleep(5000);
		elementPresent_Time(driver, By.linkText("Publish"), 15);
		safeClick(driver, By.linkText("Publish"));
		textPresent(driver, "Activity Published", 10);
		// assertTrue("Approve Pricing Not Updated", getText(driver,
		// By.cssSelector("div.notice")).contains("Activity Published"));
		Thread.sleep(5000);
		refreshPage(driver);
		driver.get(baseUrl + "/activities/" + ActivityID + "/edit");
		textPresent(driver, "Activity Actions", 10);
		assertTrue("Activity Not Published", elementPresent_Time(driver, By.linkText("Unpublish"), 10));
		// System.out.println("Activity Published Successfully");
		Reporter.log("Activity Published  Successfully");
		Thread.sleep(5000);
	}

	public void campActivities_ActiveTabs(RemoteWebDriver driver, String[] Active_Tabs)
			throws Exception, InterruptedException {
		int Active_Tab_Length = Active_Tabs.length;
		for (int j = 0; j <= Active_Tab_Length - 1; j++) {
			elementPresent(driver, By.linkText(Active_Tabs[j]));
			Thread.sleep(200);
		}

	}

	public void campActivities_InActiveTabs(RemoteWebDriver driver, String[] InActive_Tabs)
			throws Exception, InterruptedException {
		int InActive_Tab_Length = InActive_Tabs.length;
		for (int j = 0; j <= InActive_Tab_Length - 1; j++) {
			assert_elementNotPresent(driver, By.linkText(InActive_Tabs[j]));
			Thread.sleep(200);
		}
	}

	public void selectActivity(String ActivityType) throws Exception {
		waitElement(driver, By.linkText("Activity"), 20);
		Thread.sleep(1000);
		if (elementClickable(driver, By.xpath("//a[contains(text(),'Activity')]"), 10)) {
			mouseHover(driver, By.xpath("//a[contains(text(),'Activity')]"));
			safeClick(driver, By.xpath("//a[text()='New Activity (Things to do)']"));
		}
	}

	public void selectAccount(RemoteWebDriver driver, String Role) throws Exception {
		waitElement(driver, By.linkText("Account"), 15);
		// Thread.sleep(5000);
		if (elementClickable(driver, By.xpath("//a[contains(text(),'Account')]"), 10)) {

			// if (elementPresent_Time(driver, By.xpath("//a[contains(text(),'Activity')]"),
			// 5)){
			// elementClickable(driver, By.xpath("//a[contains(text(),'Activity')]"), 1);
			mouseHover(driver, By.xpath("//a[contains(text(),'Account')]"));
			safeClick(driver, By.linkText("Accounts List"));

		}

	}

	public void NewActivityFormOne(String Category, String ActivityType, String MeetingPoint, String PickUpDrop, String domain) throws Exception {
		
		Thread.sleep(5000);
			safeClick(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier"));
			if (domain.equals("com")) {
				safeAutocomplete(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier_InputFld"),
						By.xpath("//li[@class='ui-menu-item']"), "activitiescleartrip13@gmail.com");
			} else {
				safeAutocomplete(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier_InputFld"),
						By.xpath("//li[@class='ui-menu-item']"), "w4bu22os.cfu@20mail.eu");
			}

		Thread.sleep(2000);
		safeType(driver, getObject("Camp_Activities_GSTIN"), dataFile.value("CampActivities_gstInNum"));
		// safeClick(driver, getObject("Camp_Activities_GSTIN"));
		Thread.sleep(2000);
		safeSelect(driver, getObject("Camp_Activities_NewActivity_SelectCategory"), Category);
		safeClick(driver, getObject("Camp_Activities_InterestTag"));

		Thread.sleep(2000);
		List<WebElement> it = driver.findElements(By.xpath("//input[@name='selectItemselectthemes']"));
		it.get(0).click();

		safeType(driver, getObject("Camp_Activities_NewActivity_ActivityName"), "");

		String ActivityName = "Auto" + getDateTime(1, "ddmm");
		safeType(driver, getObject("Camp_Activities_NewActivity_ActivityName"), ActivityName);
		safeType(driver, getObject("Camp_Activities_NewActivity_DisplayName"), ActivityName);

		Thread.sleep(1000);
		safeType(driver, getObject("Camp_Activities_NewActivity_OnelineDesc"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_OnelineDesc"), "Test");

		safeType(driver, getObject("Camp_Activities_NewActivity_DetailDesc"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_DetailDesc"), "Test Automation");

		safeType(driver, getObject("Camp_Activities_NewActivity_Address"), "");

		if (domain.equals("com")) {
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_Address"),
					By.id("1287e6a38da136a3cc480a0edafb387b3b205c66"), campLocal.value("campCity"));
			Thread.sleep(2000);

			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_City"),By.xpath("//li/a[contains(.,'Bagalkot')]"), campLocal.value("campCityPartial"));
			Thread.sleep(2000);
			jse.executeScript("window.scrollBy(0,250)", "");
			safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "");
			safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "jpnagar");
			safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "");
			safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "560072");

			safeType(driver, getObject("Camp_Activities_NewActivity_Landmark"), "");
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_Landmark"),
					By.cssSelector("span.pac-item-query.ng-binding"), campLocal.value("campCity"));
		} else {

			Thread.sleep(500);
			safeAutocomplete_CHMM(driver, getObject("Camp_FNB_AddressLine"), By.xpath("//div[6]/div/div"),
					campLocal.value("AeCity"));
			safeAutocompleteMouseHover(driver, getObject("Camp_Activities_NewActivity_City"),
					getObject("AirCom_HomePage_From_Ajax"), campLocal.value("campAecityPartial"));
			Thread.sleep(2000);

			Thread.sleep(2000);
			jse.executeScript("window.scrollBy(0,250)", "");
			safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "");
			safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "Dibba");
			safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "");
			safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "160072");

			safeType(driver, getObject("Camp_Activities_NewActivity_Landmark"), "");
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_Landmark"),
					By.cssSelector("span.pac-item-query.ng-binding"), campLocal.value("campAecityPartial"));

		}
		if (MeetingPoint.equalsIgnoreCase("Yes")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_MeetingPoint_Yes"));
			safeType(driver, By.name("primary_support_number"), "9986508905");
			jse.executeScript("window.scrollBy(0,850)", "");
		} else if (MeetingPoint.equalsIgnoreCase("No")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_MeetingPoint_No"));
			Thread.sleep(1000);
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_MeetingAddress"),
					By.cssSelector("span.ng-binding.ng-scope"), "Test");
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_MeetingCity"),
					By.xpath("//body/ul[2]/li/a"), "Bang");
			safeType(driver, getObject("Camp_Activities_NewActivity_MeetingLocation"), "jpnagar");
			safeType(driver, getObject("Camp_Activities_NewActivity_MeetingPincode"), "560072");
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_MeetingLandmark"),
					By.cssSelector("span.pac-item-query.ng-binding"), "JP Nagar");
			safeType(driver, getObject("Camp_Activities_NewActivity_Meeting_SupportNumber"), "9986508905");
		}
		if (PickUpDrop.equalsIgnoreCase("Yes")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_PickUp_Yes"));
			safeType(driver, getObject("Camp_Activities_NewActivity_PickUp_Place"), "JP Nagar");
			safeClick(driver, getObject("Camp_Activities_NewActivity_PickUp_Add_Btn"));
			elementPresent_Time(driver, By.cssSelector("li.ng-binding.ng-scope"), 5);
			assertTrue("PickUp Or Drop point not added",
					elementPresent(driver, By.cssSelector("li.ng-binding.ng-scope")));
			jse.executeScript("window.scrollBy(0,250)", "");
			// System.out.println("Pick Up Place Added");
		} else if (PickUpDrop.equalsIgnoreCase("No")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_PickUp_No"));
		}

		if (elementPresent_Time(driver, getObject("Camp_Activities_NewActivity_ShowFullFillNo"), 2)) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_ShowFullFillNo"));
		}

		safeClick(driver, getObject("Camp_Activities_NewActivity_FormOne_SaveBtnn"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(9000);
		elementPresent_Time(driver, By.cssSelector("span.Drag-Drop"), 10);
		/*assertTrue("Create Activity Form One Is Not Successful",
				elementPresent(driver, getObject("Camp_Activities_NewActivity_FormTwo_SaveBtn")));*/
		Reporter.log("Create Activity Form One Is Successful",true);
		Thread.sleep(2000);

	}

/*	public void NewAEActivityFormOne(RemoteWebDriver driver, String Category, String ActivityType, String MeetingPoint,
			String PickUpDrop, String ActivityFor) throws Exception {
		Thread.sleep(5000);
		if (getText(driver, getObject("Camp_Activities_Header_LoggedInUser")).contains("IMD")
				|| getText(driver, getObject("Camp_Activities_Header_LoggedInUser")).contains("COP")
				|| getText(driver, getObject("Camp_Activities_Header_LoggedInUser")).contains("Sam")
				|| getText(driver, getObject("Camp_Activities_Header_LoggedInUser")).contains("Mm")
				|| getText(driver, getObject("Camp_Activities_Header_LoggedInUser")).contains("Super Content")
				|| getText(driver, getObject("Camp_Activities_Header_LoggedInUser")).contains("Sa")) {

			if (ActivityFor.equalsIgnoreCase("myself")) {
				safeClick(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Myself"));
			} else if (ActivityFor.equalsIgnoreCase("supplier")) {
				safeClick(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier"));
				safeAutocomplete(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier_InputFld"),
						By.xpath("//body/ul[2]/li/a"), "activitycleartrip@gmail.com");
			}
		}

		safeSelect(driver, getObject("Camp_Activities_NewActivity_SelectCategory"), Category);
		safeType(driver, getObject("Camp_Activities_NewActivity_ActivityName"), "");

		String ActivityName = "Auto" + System.currentTimeMillis();
		safeType(driver, getObject("Camp_Activities_NewActivity_ActivityName"), ActivityName);

		Thread.sleep(1000);
		// SA User specific Tags field.
		if (getText(driver, getObject("Camp_Activities_Header_LoggedInUser")).contains("Sam")) {
			// System.out.println("User Logged In Is SAM");
		} else if (getText(driver, getObject("Camp_Activities_Header_LoggedInUser")).contains("Sa")) {
			safeClick(driver, By.xpath("//form[@id='stepOneForm']/div[2]/div[6]/div[2]/div/div/button"));
			safeClick(driver, By.xpath("//li[3]/label/input"));
			safeClick(driver, By.xpath("//div[7]"));

			safeType(driver, By.name("activity_display_name"), "Test");
		}

		if (ActivityType.equalsIgnoreCase("Private")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_ActivityPrivate"));
		} else if (ActivityType.equalsIgnoreCase("Shared")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_ActivityShared"));
		}
		safeType(driver, getObject("Camp_Activities_NewActivity_OnelineDesc"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_OnelineDesc"), "Test");

		safeType(driver, getObject("Camp_Activities_NewActivity_DetailDesc"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_DetailDesc"), "Test Automation");

		safeType(driver, getObject("Camp_Activities_NewActivity_Address"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_Address"),
				"2nd Phase, J P Nagar, Bengaluru, Karnataka, India");

		safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_City"), By.xpath("//body/ul/li/a"), "Dubai");

		safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "jpnagar");
		safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "560072");
		safeType(driver, getObject("Camp_Activities_NewActivity_Landmark"), "");
		safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_Landmark"),
				By.cssSelector("span.pac-item-query.ng-binding"), "J P Na");

		if (MeetingPoint.equalsIgnoreCase("Yes")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_MeetingPoint_Yes"));
			safeType(driver, By.name("primary_support_number"), "9986508905");
		} else if (MeetingPoint.equalsIgnoreCase("No")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_MeetingPoint_No"));
			Thread.sleep(1000);
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_MeetingAddress"),
					By.cssSelector("span.ng-binding.ng-scope"), "Test");
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_MeetingCity"),
					By.xpath("//body/ul[2]/li/a"), "Bang");
			safeType(driver, getObject("Camp_Activities_NewActivity_MeetingLocation"), "jpnagar");
			safeType(driver, getObject("Camp_Activities_NewActivity_MeetingPincode"), "560072");
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_MeetingLandmark"),
					By.cssSelector("span.pac-item-query.ng-binding"), "JP Nagar");
			safeType(driver, getObject("Camp_Activities_NewActivity_Meeting_SupportNumber"), "9986508905");
		}
		if (PickUpDrop.equalsIgnoreCase("Yes")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_PickUp_Yes"));
			safeType(driver, getObject("Camp_Activities_NewActivity_PickUp_Place"), "JP Nagar");
			safeClick(driver, getObject("Camp_Activities_NewActivity_PickUp_Add_Btn"));
			elementPresent_Time(driver, By.cssSelector("li.ng-binding.ng-scope"), 5);
			assertTrue("PickUp Or Drop point not added",
					elementPresent(driver, By.cssSelector("li.ng-binding.ng-scope")));
			// System.out.println("Pick Up Place Added");
		} else if (PickUpDrop.equalsIgnoreCase("No")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_PickUp_No"));
		}

		if (elementPresent_Time(driver, getObject("Camp_Activities_NewActivity_ShowFullFillNo"), 2)) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_ShowFullFillNo"));
		}

		if (getText(driver, getObject("Camp_Activities_Header_LoggedInUser")).contains("Sa")) {
			safeClick(driver, By.xpath("//dd/div/button"));
		} else {

			safeClick(driver, getObject("Camp_Activities_NewActivity_FormOne_SaveBtn"));
			elementPresent_Time(driver, By.cssSelector("span.Drag-Drop"), 10);
			assertTrue("Create Activity Form One Is Not Successful",
					elementPresent(driver, getObject("Camp_Activities_NewActivity_FormTwo_SaveBtn")));
		}
		// System.out.println("Create Activity Form One Is Successful");
		Reporter.log("Create Activity Form One Is Successful");
		Thread.sleep(5000);

	}*/

	public void NewActivityFormTwo(String RiskInvolved) throws Exception {

		elementClickable(driver,getObject("Camp_Activities_NewActivity_FormTwo_Inclusions"),10);
		safeType(driver, getObject("Camp_Activities_NewActivity_FormTwo_Inclusions"), "Actinclusion");
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormTwo_Inclusions_AddLink"));
		Thread.sleep(1000);
		safeClick(driver, By.linkText("Select Files"));
		//Robot R = new Robot(); R.keyPress(KeyEvent.VK_ESCAPE);

		 Thread.sleep(1000);
		WebElement element = driver.findElement(By.name("image"));
		Thread.sleep(1000);
		File f = new File("exe\\itsme.png");
		element.sendKeys(f.getAbsolutePath());

/*		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();*/
		// System.out.println("image added");
		  Robot R = new Robot(); R.keyPress(KeyEvent.VK_ESCAPE);
		  R.keyPress(KeyEvent.VK_ESCAPE);
		  
		if (RiskInvolved.equalsIgnoreCase("Yes")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormTwo_RiskInvolved_Yes"));
			safeType(driver, getObject("Camp_Activities_NewActivity_FormTwo_RiskInvolved_InputFld"), "Test Automation");
		} else if (RiskInvolved.equalsIgnoreCase("No")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormTwo_RiskInvolved_No"));
		}
		jse.executeScript("window.scrollBy(0,650)", "");
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormTwo_BestSuited_InputFld"));
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormTwo_BestSuited_List"));
		safeClick(driver, By.xpath("//body/div"));
		Thread.sleep(2000);
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormTwo_SaveBtn"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		/*for (int i = 0; i <5; i++) {
			
			if(isElementVisible(driver,By.xpath("//div[@class='block-ui-message ng-binding']"))== true){
				Thread.sleep(10000);
			}else
				break;
		}*/
		
		//Thread.sleep(10000);
		Reporter.log("Create Activity Form TWO Is Successful",true);
		URL url = new URL(driver.getCurrentUrl());
		Reporter.log(url.getPath(),true);

	}

	public String NewActivityFormThree(String ActivityType, String privateShared, String Pricing, String KidsOnly,
			String domain) throws Exception {

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(5000);
		if (ActivityType.equalsIgnoreCase("ScheduledActivity")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_ScheduledActivity"));
			// safeSelect(driver, By.name("startingTime"), "01:00");
		} else if (ActivityType.equalsIgnoreCase("NotScheduledActivity")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_NotAScheduledActivity"));
			safeSelect(driver, By.name("weekday_from"), "06:00");
			safeSelect(driver, By.name("weekday_from_type"), "AM");
			safeSelect(driver, By.name("weekday_to"), "11:00");
			safeSelect(driver, By.name("weekday_to_type"), "PM");
			safeSelect(driver, By.name("weekend_from"), "06:00");
			safeSelect(driver, By.name("weekend_from_type"), "AM");
			safeSelect(driver, By.name("weekend_to"), "11:00");
			safeSelect(driver, By.name("weekend_to_type"), "PM");

		}

		if (privateShared.equalsIgnoreCase("shared")) {
			safeClick(driver, By.id("ActivityTypeShared"));
			// elementPresent_Time(driver, By.xpath("//a[contains(., 'Add Variant')]"), 5);
			elementClickable(driver, By.linkText("Add Variant"), 5);
			// safeClick(driver, By.xpath("//a[contains(., 'Add Variant')]"));
			safeClick(driver, By.linkText("Add Variant"));
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			// System.out.println("Model window selected");
			safeType(driver, By.xpath("//input[@id='rate_name']"), "test automation");
			safeType(driver, By.xpath("//div[@ng-model='html']"), "test automation");
			Thread.sleep(500);
			safeSelect(driver, By.name("activity_duration_days"), "0");
			Thread.sleep(500);
			safeSelect(driver, By.xpath("(//select[@name='activity_duration_hours'])[2]"), "01");
			Thread.sleep(500);
			safeSelect(driver, By.name("activity_duration_mins"), "01");
			Thread.sleep(500);
			// add inclusion
			safeType(driver, By.xpath("//div[3]/div/input[@name='activity_addons']"), "Water");
			safeClick(driver, By.xpath("//div[3]/div[2]/a"));
			safeClick(driver, By.xpath("//button[@id='rateModalSave']"));
			driver.switchTo().defaultContent();

		} else if (privateShared.equalsIgnoreCase("private")) {
			safeClick(driver, By.id("ActivityTypePrivate"));
			// safeSelect(driver,
			// By.xpath("//form[@id='stepThreeForm']/div/dd[2]/div/div[2]/select"),"0");
			elementPresent_Time(driver, By.xpath("//a[contains(., 'Add Variant')]"), 5);
			safeClick(driver, By.xpath("//a[contains(., 'Add Variant')]"));
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			// System.out.println("Model window selected");
			safeType(driver, By.xpath("//input[@id='rate_name']"), "test automation");
			safeSelect(driver, By.name("activity_duration_days"), "0");
			Thread.sleep(500);
			safeSelect(driver, By.xpath("//ng-form/div/div[2]/div[4]/div[2]/div[2]/select"), "01");
			Thread.sleep(500);
			safeSelect(driver, By.name("activity_duration_mins"), "01");
			Thread.sleep(500);
			// add inclusion
			safeType(driver, By.xpath("//div[3]/div/input[@name='activity_addons']"), "Water");
			safeClick(driver, By.xpath("//div[3]/div[2]/a"));
			safeClick(driver, By.xpath("//button[@id='rateModalSave']"));
			driver.switchTo().defaultContent();

		}

		// activity operational hours
		// safeSelectByValue(driver, by, "01:00");
		
		jse.executeScript("window.scrollBy(0,300)", "");
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_Pricing_ValidityFrom"));
		Thread.sleep(500);
		safeClick(driver, By.cssSelector("span.ui-icon.ui-icon-circle-triangle-e"));
		Thread.sleep(500);
		safeClick(driver, By.linkText("3"));
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_Pricing_ValidityTo"));
		Thread.sleep(500);
		safeClick(driver, By.cssSelector("span.ui-icon.ui-icon-circle-triangle-e"));
		Thread.sleep(500);
		safeClick(driver, By.linkText("9"));

		Thread.sleep(1000);

		//safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Days"));
		Thread.sleep(500);
		safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Days"), "1");

		//safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Hours"));
		Thread.sleep(500);
		safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Hours"), "01");

		//safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Mins"));
		Thread.sleep(500);
		safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Mins"), "15");
		//scrollToElement(driver,By.name("startingTime"));
		Thread.sleep(1000);
		if (ActivityType.equalsIgnoreCase("ScheduledActivity")) {
			safeSelectByValue(driver, By.name("startingTime"), "01:00");
		}

		if (KidsOnly.equalsIgnoreCase("Yes")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_KidsOnly"));
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_ChildMarketPrice"), "400");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_ChildCommission"), "50");
			if (elementPresent_Time(driver,
					getObject("Camp_Activities_NewActivity_FormThree_PPU_Max_capacity_InputFld"), 1)) {
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PPU_Max_capacity_InputFld"), "10");
				jse.executeScript("window.scrollBy(0,200)", "");
			}
			jse.executeScript("window.scrollBy(0,600)", "");
		} else if (KidsOnly.equalsIgnoreCase("")) {
			// System.out.println("Kids Not Selected");

		}

		if (Pricing.equalsIgnoreCase("Pricing Per Person")) {
			// System.out.println("Pricing per person info");
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson"));
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultMarketPrice"),
					"500");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultCommision"), "5");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_ChildMarketPrice"),
					"300");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_ChildCommission"), "5");
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_MinPersonRequired_InputFld"));
			safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_MinPersonRequired_InputFld"), "1");
			if (elementPresent_Time(driver, getObject("Camp_Activities_NewActivity_FormThree_Max_capacity_InputFld"),
					1)) {
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_Max_capacity_InputFld"), "10");
			}
			jse.executeScript("window.scrollBy(0,600)", "");
		}
		// Pricing Per Unit
		else if (Pricing.equalsIgnoreCase("pricingTypeSlot")) {

			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerUnit"));
			safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_unitDropdown"), "bike");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_UnitMarketPrice"),
					"500");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_UnitCommision"), "5");
			if (elementPresent_Time(driver,
					getObject("Camp_Activities_NewActivity_FormThree_PPU_Max_capacity_InputFld"), 1)) {
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PPU_Max_capacity_InputFld"), "20");
			}
			if (elementPresent_Time(driver,
					getObject("Camp_Activities_NewActivity_FormThree_PPU_MaxPersonRequired_InputFld"), 1)) {
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PPU_MaxPersonRequired_InputFld"),
						"5");
			}
			jse.executeScript("window.scrollBy(0,600)", "");
		}

		safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_SaveBtn"));
		waitElement(driver,getObjectLocals("Camp_save_header"), 6);
		assertTrue("Unable to save Activity Error:" + getText(driver, getObjectLocals("Camp_save_header")),
				getText(driver, getObjectLocals("Camp_save_header")).contains("Review your Activity and Submit for Approval"));
		scrollToElement(driver,By.linkText("Save"));
		safeClick(driver, By.linkText("Save"));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(5000);
		scrollToElement(driver,By.linkText("Approve Content"));
		safeClick(driver, By.linkText("Approve Content"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		assertTrue("Unable to save Activity Error:" + getText(driver, getObjectLocals("Camp_save_header")),
				getText(driver, getObjectLocals("Camp_save_header")).contains("Activity Updated Successfully"));
		
		Reporter.log("Activity approved", true);
		if(isElementPresent(driver,By.linkText("Approve Content"))==true) {
			scrollToElement(driver,By.linkText("Approve Content"));
			safeClick(driver, By.linkText("Approve Content"));
			Reporter.log("Activity approved", true);
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
			/*for(int i=0;i<=5;i++) {
				if(isElementPresent(driver,By.xpath("//div[@class='block-ui-message ng-binding']")) == true) {
					Thread.sleep(30000);
				}
			}*/
			
		}
		Thread.sleep(20000);
		scrollToElement(driver,By.linkText("Publish"));
		safeClick(driver, By.linkText("Publish"));
		Thread.sleep(20000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		waitElement(driver, By.xpath("//div[@ng-hide='closeNotice']/div"), 5);
		String SuccessMsg = getText(driver, By.xpath("//div[@ng-hide='closeNotice']/div"));
		Reporter.log(SuccessMsg, true);
		Assert.assertTrue(SuccessMsg, SuccessMsg.contains("Activity Published"));
		URL url = new URL(driver.getCurrentUrl());
		//System.out.println(url.getPath());
		StringBuilder sb = new StringBuilder(url.getPath());
		Reporter.log("Activity Id: "+ sb.substring(17,sb.length()-5),true);
		if (domain.equals("com")) {
			return sb.substring(17,sb.length()-5);
		} else
			return sb.substring(17,sb.length()-5);
	}

	public boolean checkActivityID(RemoteWebDriver driver) throws Exception {
		boolean checkStatus = false;
		if (checkStatus) {
			printInfo(driver.getCurrentUrl());
			Reporter.log(driver.getCurrentUrl());
			logger.info("ActvityUrl: " + driver.getCurrentUrl());
			// System.out.println("ActvityUrl: " + driver.getCurrentUrl());
			String URL = driver.getCurrentUrl();
			String ActivityID = URL.substring(0, URL.length() - 5);
			ActivityID = ActivityID.substring(ActivityID.lastIndexOf('/'));
			return true;
		} else {
			return false;
		}

	}

	public void sa_Select_ManageData(RemoteWebDriver driver, String DataType) throws Exception {
		elementNotVisible(driver, By.cssSelector("div.block-ui-message.ng-binding"), 20);
		Thread.sleep(2000);
		elementVisible(driver, By.xpath("//header/div/div/div/ul/li[7]"), 20);
		elementClickable(driver, By.xpath("//header/div/div/div/ul/li[7]"), 10);
		safeClick(driver, By.xpath("//header/div/div/div/ul/li[7]"));
		Thread.sleep(2000);
		safeClick(driver, By.linkText(DataType));
	}

	public void sa_ManageData_Verify_Add_Delete(RemoteWebDriver driver, String Xpath1, String Xpath2, String Xpath3,
			String Xpath4, String DataType) throws Exception {
		int i, j = 1;
		loopAdd: for (i = 1; i <= 100; i++) {
			String Categories_Xpath = Xpath1 + i + Xpath2;
			if (getText(driver, By.xpath(Categories_Xpath)).contains(DataType)) {
				Reporter.log("Added category is displayed in the list : " + DataType);
				break loopAdd;
			}
			if (i == 100) {
				Reporter.log("Added category is not displayed in the list");
				Assert.assertTrue(false);
			}
		}

		loopDelete: for (j = 1; j <= 100; j++) {
			String Categories_Xpath = Xpath1 + j + Xpath2;
			if (getText(driver, By.xpath(Categories_Xpath)).contains(DataType)) {
				safeClick(driver, By.xpath(Xpath1 + j + Xpath3));
				Reporter.log("Added category is deleted from the list");
				break loopDelete;
			}
			if (j == 100) {
				Reporter.log("Added category is not deleted from the list");
				Assert.assertTrue(false);
			}
		}
	}

	/**
	 * F&B Filling page two With price per person with kids condition
	 * 
	 * @param pricing
	 * @param KidsPrice
	 * @return
	 * @throws Exception
	 */

	public String fnbCreateFormTwo(String pricing, String KidsPrice, String domain) throws Exception {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		textAssert(driver, getObject("Camp_Activites_Choose_variant"), "Choose Variant");
		Thread.sleep(1500);
		safeClick(driver, getObject("Camp_Activites_Choose_variant"));
		if(campLocal.value("host").equals("qa2")) {
		if (domain.equals("com"))
			safeClickListContains(driver, getObject("Camp_FNB_variant"), campLocal.value("fnbVariant"));
		else
			safeClickListContains(driver, getObject("Camp_FNB_variant"), campLocal.value("AefnbVariant"));
		}else
			safeClickListContains(driver, getObject("Camp_FNB_variant"),"Non Veg Thali");
		// safeSelectByValue(driver,getObject("Camp_FNB_variant"), "YumFood");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		safeClick(driver, getObject("Camp_FNB_VariantModel"));
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)", "");
		Thread.sleep(1000);
		safeClick(driver, By.xpath("//input[@name='validityFrom']"));
		safeClick(driver, By.xpath("//a[2]/span"));
		safeClick(driver, By.linkText("1"));
		safeClick(driver, By.xpath("//input[@name='validityTo']"));
		safeClick(driver, By.xpath("//a[2]/span"));
		safeClick(driver, By.linkText("2"));

		if (pricing.equalsIgnoreCase("Pricing Per Person")) {

			if (KidsPrice.equalsIgnoreCase("Kids")) {
				safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_KidsOnly"));
				safeType(driver, getObject("Camp_FNB_childMrp"), "20");
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_ChildMarketPrice"), "10");
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_ChildCommission"), "1");

			} else {
				safeType(driver, getObject("Camp_FNB_adultMrp"), "5");
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultMarketPrice"),
						"2");
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultCommision"),
						"1");
				safeType(driver, getObject("Camp_FNB_childMrp"), "20");
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_ChildMarketPrice"), "1");
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_ChildCommission"), "1");

			}
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_MinPersonRequired_InputFld"));
			safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_MinPersonRequired_InputFld"), "1");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_Max_capacity_InputFld"), "5");

		} else if (pricing.equalsIgnoreCase("Pricing Per Unit")) {

			safeClick(driver, getObject("Camp_FNB_PricePerUnit"));
			safeType(driver, getObject("Camp_FNB_adultMrp"), "5");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultMarketPrice"),
					"2");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultCommision"), "1");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PPU_Max_capacity_InputFld"), "2");
		}
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)", "");
		safeClick(driver, By.xpath("//dd/div/button"));
		Thread.sleep(4000);
		textAssert(driver, "Review your Activity and Submit for Approval", 10);
		Thread.sleep(4000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,3000)", "");
		safeClick(driver, By.linkText("Approve Content"));
		Thread.sleep(4000);
		textAssert(driver, "Activity Updated Successfully", 10);
		URL url = new URL(driver.getCurrentUrl());
		StringBuilder sb = new StringBuilder(url.getPath());
		Reporter.log("Activity id: "+sb.substring(17,sb.length()-5),true);
		scrollToElement(driver,getObject("Camp_FNB_Publish"));
		safeClick(driver, getObject("Camp_FNB_Publish"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(35000);
		Reporter.log(getText(driver,By.xpath("html/body/div[1]/div[1]/div")),true);
		Assert.assertTrue("Activity Is not published",
				driver.findElement(By.xpath("html/body/div[1]/div[1]/div")).getText().equals("Activity Published"));

		if (domain.equals("com")) {
			Reporter.log("Fnb activity is publish with activity Id: " + sb.substring(17,sb.length()-5),true);
			return sb.substring(17,sb.length()-5);
		} else {
			Reporter.log("Fnb activity is publish with activity Id: " + sb.substring(17,sb.length()-5),true);
			return sb.substring(17,sb.length()-5);
		}
	}

	public void fnbCreateFormOne(String domain) throws Exception {
		String SysDate = getDateTime(1, "ddmm");
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(1000);
		safeClick(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier"));
		if (domain.equals("com")) {
			safeAutocomplete(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier_InputFld"),
					By.xpath("//li[@class='ui-menu-item']"), "activitycleartrip@gmail.com");
		} else {
			safeAutocomplete(driver, getObject("Camp_Activities_IMD_FormOne_CreateFor_Supplier_InputFld"),
					By.xpath("//li[@class='ui-menu-item']"), "w4bu22os.cfu@20mail.eu");
		}
		//safeType(driver, getObject("Camp_Activities_GSTIN"), dataFile.value("CampActivities_gstInNum"));
		safeType(driver, getObject("Camp_FNB_activityName"), "Restaurant" + SysDate);
		safeType(driver, getObject("Camp_FNB_activityDisplayName"), "Outlet" + SysDate);

		if (domain.equals("com")) {
			safeAutocomplete_CHMM(driver, getObject("Camp_FNB_AddressLine"), By.xpath("//div[6]/div/div"),
					campLocal.value("campCity"));
			safeAutocompleteMouseHover(driver, getObject("Camp_Activities_NewActivity_City"),
					getObject("AirCom_HomePage_From_Ajax"), campLocal.value("campCity"));
			Reporter.log("City Selected : " + campLocal.value("campCity"),true);
			safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "587101");
			safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "mg Road");
			safeAutocomplete_CHMM(driver, getObject("Camp_Activities_NewActivity_Landmark"),
					By.xpath("//div[7]/div/div"), campLocal.value("campCity"));
		} else {
			safeAutocomplete_CHMM(driver, getObject("Camp_FNB_AddressLine"), By.xpath("//div[6]/div/div"),
					campLocal.value("AeCity"));
			safeAutocompleteMouseHover(driver, getObject("Camp_Activities_NewActivity_City"),
					getObject("AirCom_HomePage_From_Ajax"), campLocal.value("AeCity"));
			Reporter.log("City Selected : " + campLocal.value("AeCity"),true);
			safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "587101");
			safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "Dubai Road");
			safeAutocomplete_CHMM(driver, getObject("Camp_Activities_NewActivity_Landmark"),
					By.xpath("//div[7]/div/div"), campLocal.value("AeCity"));

		}
		safeType(driver, getObject("Camp_Activities_NewActivity_Meeting_SupportNumber"), "1212121121");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)", "");
		safeClick(driver, By.xpath("//button[contains(.,'Save')]"));
		Thread.sleep(6000);
		Reporter.log("Restaurant" + SysDate + " one form is filled sucessfully",true);
	}

	public void selectManageEditorial(RemoteWebDriver driver) throws Exception {
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		waitElement(driver, By.linkText("Manage Data"), 10);
		if (elementClickable(driver, By.xpath("//a[contains(text(),'Manage Data')]"), 10)) {
			mouseHover(driver, By.xpath("//a[contains(text(),'Manage Data')]"));
			driver.findElement(By.linkText("Manage Editorials")).click();
			Thread.sleep(2000);
		}

	}

	public void EditorialDetails(RemoteWebDriver driver, String type) throws Exception {
		safeClick(driver, By.linkText("Create Editorials"));
		Thread.sleep(2000);
		safeClick(driver, getObject("Camp_CreateEditorialActiveYes"));

		// safeType(driver,
		// getObject("Camp_CreateEditorial_Title"),common.value("releasenumber")+"editorial
		// title");
		if (type == "CarousalActivity") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"),
					common.value("releasenumber") + campLocal.value("CampCarousalActivity"));
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + campLocal.value("CampCarousalActivity"));
		} else if (type == "CarousalCollection") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"),
					common.value("releasenumber") + campLocal.value("CampCarousalCollection"));
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + campLocal.value("CampCarousalCollection"));
		} else if (type == "CarousalOfferActivity") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"),
					common.value("releasenumber") + campLocal.value("CampCarousalOfferActivity"));
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + campLocal.value("CampCarousalOfferActivity"));
		} else if (type == "CarousalOfferCollection") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"),
					common.value("releasenumber") + campLocal.value("CampCarousalOfferCollection"));
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + campLocal.value("CampCarousalOfferCollection"));
		} else if (type == "CarousalOfferAll") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"),
					common.value("releasenumber") + campLocal.value("CampCarousalOfferAll"));
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + campLocal.value("CampCarousalOfferAll"));
		} else if (type == "ProductEditorial") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"),
					common.value("releasenumber") + campLocal.value("CampProductEditorial"));
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + campLocal.value("CampProductEditorial"));
		}
		safeType(driver, getObject("Camp_CreateEditorial_SubTitle"),
				common.value("releasenumber") + "editorial subtitle");
		safeType(driver, getObject("Camp_CreateEditorial_Description"),
				common.value("releasenumber") + "editorial description");
		safeClick(driver, getObject("Camp_CreateEditorial_StartDate"));
		safeClick(driver, By.xpath("//a[text()='1']"));
		// safeClick(driver, By.xpath("//a[2]/span"));
		// safeClick(driver, By.linkText("1"));
		Thread.sleep(1000);
		safeClick(driver, getObject("Camp_CreateEditorial_EndDate"));
		safeClick(driver, By.xpath("//a[2]/span"));
		safeClick(driver, By.linkText("2"));
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,280)", "");
		// safeClick(driver, By.xpath("(//input[@value='all'])"));
		safeClick(driver, By.xpath("(//input[@name='days_of_week'])[8]"));
	}

	public void EditorialType(RemoteWebDriver driver, String type, String product) throws Exception {
		if (type == "Carousal") {
			safeClick(driver, By.xpath("(//input[@name='editorial_type'])[1]"));
			safeSelect(driver, By.name("editorial_tags"), "Deal");
		}

		if (product == "TTD") {
			safeAutocomplete(driver, By.name("carousel_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"), "Bagalkot");
			Thread.sleep(2000);
			safeSelect(driver, getObject("Camp_CreateEditorial_SelectProduct"), "Things to do");

		} else if (product == "FNB") {
			safeAutocomplete(driver, By.name("carousel_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"), "Bagalkot");
			Thread.sleep(2000);
			safeSelect(driver, getObject("Camp_CreateEditorial_SelectProduct"), "Food and Beverages");

		} else if (product == "Events") {
			safeAutocomplete(driver, By.name("carousel_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"), "Bagalkot");
			Thread.sleep(2000);
			safeSelect(driver, getObject("Camp_CreateEditorial_SelectProduct"), "Events");

		}
		Thread.sleep(3000);
		safeClick(driver, By.linkText("Select File"));
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.name("image"));
		File f = new File("exe\\itsme.png");
		Thread.sleep(5000);
		element.sendKeys(f.getAbsolutePath());
		/*Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();*/
		 Robot R = new Robot(); R.keyPress(KeyEvent.VK_ESCAPE);
		  R.keyPress(KeyEvent.VK_ESCAPE);
		jse.executeScript("window.scrollBy(0,250)", "");
	}

	public void contentType(RemoteWebDriver driver, String ctype, String cproduct) throws Exception {
		if (ctype == "Activities") {
			safeClick(driver, By.xpath("//input[@value='activities']"));
			if (cproduct == "TTD") {
				safeAutocomplete(driver, getObject("Camp_CreateEditorial_PickaActivities"),
						By.linkText("test carousal activity"), "test carousal");
				
				jse.executeScript("window.scrollBy(0,100)", "");
			} else if (cproduct == "FNB") {
				safeAutocomplete(driver, getObject("Camp_CreateEditorial_PickaActivities"),
						By.linkText("YumFood (Testchain2007170700)"), "yumfood");
				
				jse.executeScript("window.scrollBy(0,100)", "");
			} else if (cproduct == "Events") {
				safeAutocomplete(driver, getObject("Camp_CreateEditorial_PickaActivities"),
						By.linkText("Testing of events"), "Testing of events");
				driver.findElement(getObjectLocals("Camp_Event_Editorial")).clear();
				
				
				jse.executeScript("window.scrollBy(0,100)", "");
			}

		} else if (ctype == "Collection") {
			safeClick(driver, getObject("Camp_CreateEditorial_PickCollectionRadio"));
			Thread.sleep(3000);
			jse.executeScript("window.scrollBy(0,150)", "");
			safeClick(driver, By.xpath("//*[@id='editorialtype_carousel']/div[4]/div/div[2]/div/div/a"));
			Thread.sleep(2000);
			if (cproduct == "TTD") {
				safeAutocomplete(driver,
						By.xpath(".//*[@id='editorialtype_carousel']/div[4]/div/div[2]/div/div/div/div/input"),
						By.xpath("//ul/li/em"), "Boatin");
			} else if (cproduct == "FNB") {
				safeAutocomplete(driver,
						By.xpath(".//*[@id='editorialtype_carousel']/div[4]/div/div[2]/div/div/div/div/input"),
						By.xpath("//ul/li/em"), "Auto FNB");
			} else if (cproduct == "Events") {
				safeAutocomplete(driver,
						By.xpath(".//*[@id='editorialtype_carousel']/div[4]/div/div[2]/div/div/div/div/input"),
						By.xpath("//ul/li/em"), "Music");
			}
		} else if (ctype == "OfferActivity") {
			safeClick(driver, getObject("Camp_CreateEditorial_PickOfferRadio"));
			Thread.sleep(2000);
			safeType(driver, By.xpath("//div[@ng-model='html']"), "test automation offer");
			jse.executeScript("window.scrollBy(0,250)", "");
			if (cproduct == "TTD") {
				safeClick(driver, By.xpath("(//input[@name='carousel_editorial_mapto'])[1]"));
				safeAutocomplete(driver, getObject("Camp_CreateEditorial_PickaActivities"),
						By.linkText("test carousal activity"), "test carousal");
				Thread.sleep(3000);
			} else if (cproduct == "FNB") {
				safeClick(driver, By.xpath("(//input[@name='carousel_editorial_mapto'])[1]"));
				safeAutocomplete(driver, getObject("Camp_CreateEditorial_PickaActivities"),
						By.linkText("YumFood (Testchain2007170700)"), "yumfood");

			}

		} else if (ctype == "OfferCollection") {
			safeClick(driver, getObject("Camp_CreateEditorial_PickOfferRadio"));
			Thread.sleep(2000);
			safeType(driver, By.xpath("//div[@ng-model='html']"), "test automation offer");
			jse.executeScript("window.scrollBy(0,250)", "");
			if (cproduct == "TTD") {
				safeClick(driver, By.xpath("(//input[@name='carousel_editorial_mapto'])[2]"));
				Thread.sleep(2000);
				safeClick(driver, By.xpath(
						".//*[@id='editorialtype_carousel']/div[4]/div/div[2]/div[2]/div/div[2]/div/div/a/div/b"));
				safeAutocomplete(driver, By.xpath(
						".//*[@id='editorialtype_carousel']/div[4]/div/div[2]/div[2]/div/div[2]/div/div/div/div/input"),
						By.xpath("//ul/li/em"), "Boatin");
				Thread.sleep(2000);
			} else if (cproduct == "FNB") {
				safeClick(driver, By.xpath("(//input[@name='carousel_editorial_mapto'])[2]"));
				Thread.sleep(2000);
				safeClick(driver, By.xpath(
						".//*[@id='editorialtype_carousel']/div[4]/div/div[2]/div[2]/div/div[2]/div/div/a/div/b"));
				safeAutocomplete(driver, By.xpath(
						".//*[@id='editorialtype_carousel']/div[4]/div/div[2]/div[2]/div/div[2]/div/div/div/div/input"),
						By.xpath("//ul/li/em"), "Auto FNB");
				Thread.sleep(2000);
			} else if (cproduct == "Events") {
				safeClick(driver, By.xpath("(//input[@name='carousel_editorial_mapto'])[2]"));
				Thread.sleep(2000);
				safeClick(driver, By.xpath(
						".//*[@id='editorialtype_carousel']/div[4]/div/div[2]/div[2]/div/div[2]/div/div/a/div/b"));
				safeAutocomplete(driver, By.xpath(
						".//*[@id='editorialtype_carousel']/div[4]/div/div[2]/div[2]/div/div[2]/div/div/div/div/input"),
						By.xpath("//ul/li/em"), "Music");
				Thread.sleep(2000);
			}
			// safeClick(driver, getObject("Camp_CreateEditorial_OfferDontShowOnHomePage"));
		} else if (ctype == "OfferAll") {
			safeClick(driver, getObject("Camp_CreateEditorial_PickOfferRadio"));
			Thread.sleep(2000);
			jse.executeScript("window.scrollBy(0,250)", "");
			safeType(driver, By.xpath("//div[@ng-model='html']"), "test automation offer");

			if (cproduct == "TTD") {
				safeClick(driver, By.xpath("(//input[@name='carousel_editorial_mapto'])[3]"));

			} else if (cproduct == "FNB") {
				safeClick(driver, By.xpath("(//input[@name='carousel_editorial_mapto'])[3]"));

			} else if (cproduct == "Events") {
				safeClick(driver, By.xpath("(//input[@name='carousel_editorial_mapto'])[3]"));

			}

			// safeClick(driver, getObject("Camp_CreateEditorial_OfferDontShowOnHomePage"));
		}

		jse.executeScript("window.scrollBy(0,250)", "");
	}

	public void mapCarousal(RemoteWebDriver driver, String mproduct, boolean offer) throws Exception {
		// boolean offer=true;
		safeAutocomplete(driver, By.xpath("//input[@ng-model='newTag.text']"), By.xpath("//span/em[text()='Bagalkot']"),
				"bagalkot");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		if (mproduct == "TTD") {
			safeClick(driver, By.name("activities"));
		} else if (mproduct == "FNB") {
			safeClick(driver, By.name("dine_out"));
		} else if (mproduct == "Events") {
			safeClick(driver, By.name("events"));
		}
		if (offer == true) {
			safeClick(driver, getObject("Camp_CreateEditorial_OfferDontShowOnHomePage"));
		}
		scrollToElement(driver, By.xpath("//input[@value='Save']"));
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("window.scrollBy(0,450)", "");
		safeClick(driver, By.xpath("//input[@value='Save']"));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		//Thread.sleep(10000);
		boolean elementVisible = isElementPresent(driver, By.xpath("//input[@value='Save']"));
		Reporter.log("Save button elementVisible status : " + elementVisible,true);
		Assert.assertEquals(false, elementVisible);
	}

	public void mapCarousalEvent(RemoteWebDriver driver, boolean offer) throws Exception {
		safeAutocomplete(driver, By.xpath("//input[@ng-model='newTag.text']"),
				By.xpath("//span/em[text()='Bangalore']"), "bangalore");
		Thread.sleep(1000);
		if (offer == true) {
			safeClick(driver, getObject("Camp_CreateEditorial_OfferDontShowOnHomePage"));
		}
		jse.executeScript("window.scrollBy(0,450)", "");
		safeClick(driver, By.name("events"));
		safeClick(driver, By.xpath("//input[@value='Save']"));
		Thread.sleep(7000);
		boolean elementVisible = elementVisible(driver, By.xpath("//input[@value='Save']"), 10);
		Reporter.log("Save button elementVisible status : " + elementVisible,true);
		Assert.assertEquals(false, elementVisible);
	}

	public void prodEditorial(RemoteWebDriver driver, String product) throws Exception {
		safeClick(driver, By.xpath("(//input[@name='editorial_type'])[2]"));
		safeSelect(driver, By.name("editorial_tags"), "Featured");

		if (product == "TTD") {
			safeAutocomplete(driver, By.name("product_editorial_cities"), By.xpath("//li/a[contains(.,'Bagalkot')]"), "Bagalkot");
			Thread.sleep(2000);
			safeSelect(driver, getObject("Camp_CreateProdEditorial_SelectProduct"), "Things to do");
			safeClick(driver, By.xpath("//input[@value='Pick 3-5 collections']"));
			safeAutocomplete(driver, By.xpath("//input[@value='Pick 3-5 collections']"),
					By.xpath(".//*[@id='editorialtype_product']/div[3]/div/div/div/div/ul/li"), "boati");
		} else if (product == "FNB") {
			safeAutocomplete(driver, By.name("product_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"), "Bagalkot");
			Thread.sleep(2000);
			safeSelect(driver, getObject("Camp_CreateProdEditorial_SelectProduct"), "Food and Beverages");
			safeClick(driver, By.xpath("//input[@value='Pick 3-5 collections']"));
			safeAutocomplete(driver, By.xpath("//input[@value='Pick 3-5 collections']"),
					By.xpath(".//*[@id='editorialtype_product']/div[3]/div/div/div/div/ul/li"), "Auto FNB");
		}
		if (product == "Events") {
			safeAutocomplete(driver, By.name("product_editorial_cities"), By.xpath("//li/a[contains(.,'Bagalkot')]"), "Bangal");
			Thread.sleep(2000);
			safeSelect(driver, getObject("Camp_CreateProdEditorial_SelectProduct"), "Events");
			safeClick(driver, By.xpath("//input[@value='Pick 3-5 collections']"));
			safeAutocomplete(driver, By.xpath("//input[@value='Pick 3-5 collections']"),
					By.xpath(".//*[@id='editorialtype_product']/div[3]/div/div/div/div/ul/li"), "Music");
		}
	}

	public void mapProdEditorial(RemoteWebDriver driver, String product) throws Exception {
		safeAutocomplete(driver, By.xpath("//input[@ng-model='newTag.text']"), By.xpath("//span/em[text()='Bagalkot']"),
				"bagalkot");
		if (product == "Events") {
			safeAutocomplete(driver, By.xpath("//input[@ng-model='newTag.text']"),
					By.xpath("//span/em[text()='Bangalore']"), "bangalore");
		}
		safeClick(driver, By.xpath("//input[@value='Save']"));
		Thread.sleep(7000);
		boolean elementVisible = elementVisible(driver, By.xpath("//input[@value='Save']"), 6);
		Reporter.log("Save button elementVisible status : " + elementVisible,true);
		Assert.assertEquals(false, elementVisible);
	}

	/**
	 * FNB EDIT variant Pricing
	 * 
	 * @throws Exception
	 */
	public void fnbVariantPricing() throws Exception {
		safeSelect(driver, getObject("Camp_Activities_Search_ProductID"), "F & B");
		Thread.sleep(1000);
		safeClick(driver, getObject("Camp_Submit_Button"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		String FNB_Name = getText(driver, By.xpath("(//td[2])[1]"));
		safeClick(driver, By.xpath("(//td/a)[1]"));
		Thread.sleep(5000);
		elementVisible(driver, By.xpath("//h3"), 5, "Fnb Activity name not displayed in edit page");
		String Category_Name_Edit = getText(driver, By.xpath("//h3"));
		if (!Category_Name_Edit.contains(FNB_Name)) {
			Reporter.log("Selected FNB Name doesnt match : " + FNB_Name,true);
			Assert.assertTrue(false);
		}
		safeClick(driver, By.linkText("Edit"));
		safeClick(driver, By.linkText("Variants & Pricing"));
		textPresent(driver, "Step 1: Select a Variant ", 10);
		safeSelectByIndex(driver, By.id("variantType"), 0);
		/*selectCalendarDate(driver, getObject("Camp_Activities_FromDate"), getObject("Camp_Activities_Date_NextMonth"),
				1, "1");
		selectCalendarDate(driver, getObject("Camp_Activities_ToDate"), getObject("Camp_Activities_Date_NextMonth"), 1,
				"5");*/
		safeType(driver, getObject("Camp_FNB_adultMrp"), "500");
		safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultMarketPrice"), "240");
		safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultCommision"), "1");
		safeType(driver, getObject("Camp_FNB_childMrp"), "200");
		safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_ChildMarketPrice"), "120");
		safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_ChildCommission"), "1");
		safeClick(driver, By.xpath("//div//a[contains(.,'Save')]"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		if (!elementVisible(driver, By.cssSelector("div.success"), 20, "Sucess message is not displayed")) {
			Assert.assertTrue(false);
		}
		Reporter.log("Pricing Editing done sucessfully",true);
	}

	/**
	 * FNB Edit Inventory
	 * 
	 * @throws Exception
	 */
	public void inventoryEditFnb() throws Exception {
		String FNB_Name, Category_Name_Edit;
		safeSelect(driver, getObject("Camp_Activities_Search_ProductID"), "F & B");
		safeSelect(driver, By.xpath("//select"), "Published");
		Thread.sleep(1000);
		safeClick(driver, getObject("Camp_Submit_Button"));
		FNB_Name = getText(driver, By.xpath("(//td[2])[1]"));
		safeClick(driver, By.xpath("(//td/a)[1]"));
		Category_Name_Edit = getText(driver, By.xpath("//h3"));
		/*
		 * if (!Category_Name_Edit.contains(FNB_Name)) {
		 * Reporter.log("Selected FNB Name doesnt match : " + FNB_Name);
		 * Assert.assertTrue(false); }
		 */
		textPresent(driver, "Activity Title ", 5);
		safeClick(driver, By.linkText("Click here"));
		Thread.sleep(5000);
		modalWindow_BackTo_MainWindow(driver, By.xpath("//label"), 20);
		safeClick(driver, By.id("dateFrom"));
		safeClick(driver, By.xpath("//a[2]/span"));
		String MonthSelected = getText(driver, By.xpath("//div[6]/div/div"));
		safeClick(driver, By.linkText("1"));
		safeClick(driver, By.id("dateTo"));
		safeClick(driver, By.linkText("15"));
		safeType(driver, By.name("bulkInventory"), "10");
		safeClick(driver, By.xpath("//button"));
		Thread.sleep(5000);
		elementVisible(driver, By.cssSelector("span.targetSuccess.ng-binding"), 20, "Inventory Updated");
		elementNotPresent_Time(driver, By.xpath("//div[5]/div[2]/div"), 5);
		elementVisible(driver, By.xpath("//div[3]/div/div"), 20);
		for (int i = 0; i <= 3; i++) {
			String Cal_Month = getText(driver, By.xpath("//div[3]/div/div"));
			if (MonthSelected.contains(Cal_Month)) {
				break;

			} else
				safeClick(driver, By.xpath("//button[2]"));
		}
		String InventoryUpdated = getText(driver, By.xpath("//div[2]/div[2]/table/tbody/tr/td/a/div/span[2]"));
		Thread.sleep(5000);
		if (!InventoryUpdated.contains("10")) {
			Reporter.log("Inventory Updated : " + InventoryUpdated,true);
			Assert.assertTrue(false);
		}
		Reporter.log("Inventory Updated is : " + InventoryUpdated,true);
	}

	/**
	 * FNB Edit Activity
	 * 
	 * @throws Exception
	 */
	public void fnbEditActivity() throws Exception {
		String FNB_Name, Category_Name_Edit;
		String SysDate = getDateTime(1, "ddmmHHmmss");
		safeSelect(driver, getObject("Camp_Activities_Search_ProductID"), "F & B");
		Thread.sleep(1000);
		safeClick(driver, getObject("Camp_Submit_Button"));
		FNB_Name = getText(driver, By.xpath("(//td[2])[1]"));
		safeClick(driver, By.xpath("(//td/a)[1]"));
		Category_Name_Edit = getText(driver, By.xpath("//h3"));
		if (!Category_Name_Edit.contains(FNB_Name)) {
			Reporter.log("Selected FNB Name doesnt match : " + FNB_Name,true);
			Assert.assertTrue(false);
		}
		Thread.sleep(1000);
		safeClick(driver, getObject("Camp_Activities_Edit_Button"));
		safeType(driver, getObject("Camp_FNB_activityName"), "Restaurant" + SysDate);
		safeType(driver, getObject("Camp_FNB_activityDisplayName"), "Outlet" + SysDate);
		safeClick(driver, getObject("Camp_Activities_SaveButton"));
		Reporter.log("FNB Activity Sucessfully Edited",true);
	}

	/**
	 * FNB back Date Function
	 * 
	 * @throws Exception
	 */
	public void fndAddBackDate() throws Exception {
		safeSelect(driver, getObject("Camp_Activities_Search_ProductID"), "F & B");
		safeSelect(driver, getObject("Camp_Activities_Search_Select_Type"), "Published");
		safeClick(driver, getObject("Camp_Submit_Button"));
		elementVisible(driver, By.xpath("//td[3]"), 5, "Fnb Activity name not displayed");
		safeClick(driver, By.xpath("(//td/a)[1]"));
		Thread.sleep(1000);
		safeClick(driver, getObject("Camp_Activities_Edit_Button"));
		safeClick(driver, getObject("Camp_Activites_VNPricing"));
		textPresent(driver, "Step 1: Select a Variant ", 10);
		selectCalendarDate(driver, getObject("Camp_Activities_FromDate"), getObject("Camp_Activities_Date_NextMonth"),
				0, "1");
		selectCalendarDate(driver, getObject("Camp_Activities_ToDate"), getObject("Camp_Activities_Date_NextMonth"), 2,
				"20");

		smartClick(driver, By.xpath("//div[@id='pricingLayer']/div[1]//span/a[contains(.,'click here')]"));
		textPresent(driver, "Blackout Days", 2);
		selectCalendarDate(driver, getObject("Camp_Activities_FromDate1"), getObject("Camp_Activities_Date_NextMonth"),
				2, "2");
		selectCalendarDate(driver, getObject("Camp_Activities_ToDate1"), getObject("Camp_Activities_Date_NextMonth"), 2,
				"5");

		safeClick(driver, getObject("Camp_Activities_SaveButton"));
		elementNotVisible(driver, getObject("Camp_Loader_PopUp"), 5);
		if (!elementVisible(driver, getObject("Camp_Activities_Success_Message"), 50)) {
			Reporter.log("Activity Updated Successfully : message is not displayed",true);
			Assert.assertTrue(false);
		}

		Reporter.log("Activity Updated Successfully : message is displayed",true);
	}

	public void fnbCreateTag() throws Exception {
		String SysDate = getDateTime(1, "ddmm"),
				delActivityIcnLoc = "//section[@id='manageActivity']//li[contains(.,'" + SysDate + "')]/i";
		restroName = "Restaurant" + SysDate;
		smartSelect(driver, By.xpath("//section[@id='manageActivity']//select"), "Fnb Interest Tag");
		Thread.sleep(1000);
		safeClick(driver, By.xpath("//section[@id='manageActivity']//span/a[contains(.,'Add')]"));
		Thread.sleep(1000);
		safeType(driver, By.xpath("//div[@id='ngdialog1']//input[@type='text']"), restroName);
		safeClick(driver, By.xpath("//div[@id='ngdialog1']//input[@type='checkbox']"));
		safeClick(driver, By.xpath("//div[@id='ngdialog1']//a[contains(.,'Add')]"));
		locator = "//section[@id='manageActivity']//span[contains(.,'" + restroName + "')]";
		elementPresent(driver, By.xpath(locator));
		Reporter.log("FNB " + SysDate + " is added from master list",true);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(3000);
		safeClick(driver, By.xpath(delActivityIcnLoc));
		safeClick(driver, By.xpath(delActivityIcnLoc));
		Reporter.log("FNB " + SysDate + " is deleted from master list",true);
		
	}

	public void fnbAddChainActivity() throws Exception {
		String SysDate = getDateTime(1, "ddmm");
		camp_HomePage_Select_ManageData(driver, "Manage Chains and Variants", "Manage Chains and Variants");
		safeClick(driver, getObject("Camp_FNB_CreateChain"));
		textPresent(driver, "Chain Panel", 5);
		restroName = "testChain" + SysDate;
		safeType(driver, getObject("Camp_FNB_BransName"), restroName);
		safeAutocomplete_CHMM(driver, By.xpath("//div[5]/div/input"), By.xpath("//body/ul[2]/li/a"), "Bagalkot");
		safeClick(driver, getObject("Camp_FNB_NotApplicable"));
		safeClick(driver, By.xpath("(//div/div/i)[2]"));
		Thread.sleep(2000);
		safeClick(driver, By.linkText("Select File"));
		WebElement element = driver.findElement(By.name("image"));
		File f = new File("exe\\itsme.png");
		element.sendKeys(f.getAbsolutePath());
		safeType(driver, By.xpath("(//div/textarea)[1]"), "new restaurants");
		safeClick(driver, By.xpath("(//div/div/i)[2]"));
		safeType(driver, By.xpath("//input[contains(@placeholder,'Person')]"), "Automation");
		safeType(driver, By.name("brand_contact_number"), "9664540175");
		safeClick(driver, By.xpath("//div//a[contains(.,'Save')]"));
		Thread.sleep(2500);
		Reporter.log("Chain is added", true);

		// textAssert(driver, By.xpath("//h3"), restroName);
	}

	public void newAddVariant() throws Exception {
		String SysDate = getDateTime(1, "ddmm");
		String RestroName = "YumFood" + SysDate;
		safeAutocomplete_CHMM(driver,
				By.xpath("//section[@id='manageActivity']//input[contains(@placeholder,'Search for a Brand')]"),
				By.xpath("//body/ul/li/a"), "Testchain1903160317");
		safeClick(driver, By.linkText("Fetch"));
		Thread.sleep(3000);
		safeClick(driver, By.xpath("//section[@id='manageActivity']//a[contains(.,'Add New Variant')]"));
		safeType(driver, getObject("Camp_FNB_Namevariant"), RestroName);
		smartSelect(driver, By.xpath("//div[@id='ngdialog4']//dd/select"), "All Day dining");
		safeType(driver, By.name("variant_short_description"), "Nice Variant");
		safeType(driver, By.name("variant_highlights"), "Nice Food");
		safeClick(driver, By.linkText("ADD"));
		Thread.sleep(2000);
		safeClick(driver, By.linkText("Select File"));
		WebElement element = driver.findElement(By.name("image"));
		File f = new File("exe\\itsme.png");
		element.sendKeys(f.getAbsolutePath());
		safeClick(driver, By.xpath("(//div[@class='ngdialog-content']//button[@type='button'])[1]"));
		safeClick(driver, By.xpath("//div[@class='ngdialog-content']//a[contains(.,'Check All')]"));
		safeClick(driver, By.xpath("(//div[@class='ngdialog-content']//button[@type='button'])[1]"));
		safeType(driver, By.name("variant_inclusions"), "Biriyani");
		safeClick(driver, By.xpath("(//div[@class='ngdialog-content']//a[contains(.,'ADD')])[2]"));
		safeClick(driver, By.id("breakFast"));
		safeClick(driver, By.xpath("(//div[@class='ngdialog-content']//button[@type='button'])[2]"));
		locator = "//div[@class='ngdialog-content']//label[contains(.,'" + "Dinner" + "')]/input";
		safeClick(driver, By.xpath(locator));
		safeClick(driver, By.xpath("(//div[@class='ngdialog-content']//button[@type='button'])[2]"));
		Thread.sleep(1000);
		safeSelectByIndex(driver, By.name("duration_hours"), 23);
		safeSelectByIndex(driver, By.name("duration_mins"), 2);
		safeSelectByIndex(driver, By.name("weekday_from"), 2);
		safeSelectByIndex(driver, By.name("weekday_from_type"), 1);
		safeSelectByIndex(driver, By.name("weekday_to"), 22);
		safeSelectByIndex(driver, By.name("weekday_to_type"), 1);
		safeSelectByIndex(driver, By.name("weekend_from"), 2);
		safeSelectByIndex(driver, By.name("weekend_from_type"), 1);
		safeSelectByIndex(driver, By.name("weekend_to"), 22);
		safeSelectByIndex(driver, By.name("weekend_to_type"), 1);
		safeClick(driver, By.xpath("//div//a[contains(.,'Save')]"));
		Thread.sleep(9000);
		chainName = driver.findElement(By.xpath("//h3")).getText();
	}

	public void espAssign() throws Exception {
		Thread.sleep(2000);
		safeAutocomplete_CHMM(driver, By.xpath("//div[@id='supplier-dashboard']//input[@placeholder='Search ESP']"),
				By.xpath("//body/ul/li/a"), dataFile.value("CampActivities_UserName_ESP"));
		Thread.sleep(1000);
		safeClick(driver, By.xpath("(html//a[contains(.,'Edit')])[3]"));
		safeAutocomplete_CHMM(driver, By.xpath("//div[@id='tap']//input[contains(@placeholder,'Search for a Brand')]"),
				By.xpath("//body/ul/li/a"), "Testchain1903160317");
		safeClick(driver, By.xpath("//div[@id='tap']//button[contains(.,'Assign Role')]"));
	}

	/**
	 * Unpublish TTD and FNB activity
	 * 
	 * @param url
	 * @param id
	 * @throws Exception
	 */
	public void unPublishActivity(String url, String id) throws Exception {
		String unpublishUrl = url + "/activities/" + id + "/edit";
		driver.get(unpublishUrl);
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,2700)", "");
		safeClick(driver, getObject("Camp_FNB_Unpublish"));
		Thread.sleep(9000);
		Assert.assertTrue("Activity is not unpublished", driver.findElement(By.xpath("html/body/div[1]/div[1]/div"))
				.getText().contains("Activity Un-Published"));
		Reporter.log("Activity unpublished",true);
	}

	/**
	 * Checking for Camp Active link for different User
	 * 
	 * @param driver
	 * @param data
	 * @throws Exception
	 */
	public void campActiveLinks(RemoteWebDriver driver, ArrayList<String> data) throws Exception {

		Thread.sleep(3000);
		if (elementPresent(driver, By.linkText(data.get(0))) == true) {

			mouseHover(driver, By.linkText(data.get(0)));
			if (elementPresent(driver, By.linkText(data.get(1)))) {
				safeClick(driver, By.linkText(data.get(1)));
				Thread.sleep(3000);
				Assert.assertTrue(data.get(2) + "option is not present",
						getText(driver, By.xpath(data.get(3))).contains(data.get(2)));
			}
		}

	}

	/**
	 * Add Activity Units,Dress Code,Cuisnies
	 * 
	 * @param driver2
	 * @param inputActivityUnit
	 * @throws Exception
	 */
	public void addMasterData(String OperationFor, String inputActivityUnit) throws Exception {
		String addActivityloc = "//section[@id='manageActivity']//span[text()='" + inputActivityUnit + "']",
				delActivityIcnLoc = "//section[@id='manageActivity']//li[contains(.,'" + inputActivityUnit + "')]/i";

		smartSelect(driver, By.xpath("//section[@id='manageActivity']//select"), OperationFor);
		if (isElementPresent(driver, By.xpath(addActivityloc)) == true) {
			Thread.sleep(2000);
			safeClick(driver, By.xpath(delActivityIcnLoc));
		}
		safeType(driver, By.xpath("//section[@id='manageActivity']//input"), inputActivityUnit);
		safeClick(driver, By.linkText("Add"));
		Thread.sleep(3000);
		textAssert(driver, By.xpath(addActivityloc), inputActivityUnit);
		Reporter.log(OperationFor + " " + inputActivityUnit + " is added into master list",true);
		Thread.sleep(2000);
		
		  safeClick(driver, By.xpath(delActivityIcnLoc)); if (isElementPresent(driver,
		  By.xpath(delActivityIcnLoc)) == false) { Reporter.log(OperationFor + " " +
		  inputActivityUnit + " is deleted from master list"); }
	}

	/**
	 * 
	 * @param driver
	 * @param OperationFor
	 * @param dataTag
	 * @throws Exception
	 */
	public void addMastertagsData(String OperationFor, String dataTag) throws Exception {
		String addActivityloc = "(//section[@id='manageActivity']//span[text()='" + dataTag + "'])[2]",
				delActivityIcnLoc = "//section[@id='manageActivity']//li[contains(.,'" + dataTag + "')]/i";
		smartSelect(driver, By.xpath("//section[@id='manageActivity']//select"), OperationFor);
		Thread.sleep(1000);
		if (isElementPresent(driver, By.xpath(addActivityloc)) == true) {
			Thread.sleep(2000);
			safeClick(driver, By.xpath(delActivityIcnLoc));
		}
		Thread.sleep(1000);
		safeClick(driver, By.xpath("//section[@id='manageActivity']//span/a[contains(.,'Add')]"));
		Thread.sleep(1000);
		safeType(driver, By.xpath("//div[@id='ngdialog1']//input[@type='text']"), dataTag);
		safeClick(driver, By.xpath("//div[@id='ngdialog1']//input[@type='checkbox']"));
		safeClick(driver, By.xpath("//div[@id='ngdialog1']//a[contains(.,'Add')]"));
		Thread.sleep(2000);
		//textAssert(driver, By.xpath(addActivityloc), dataTag);
		Reporter.log(OperationFor + " " + dataTag + " is added into master list",true);
		Thread.sleep(2000);
		safeClick(driver, By.xpath("//section[@id='manageActivity']//label[contains(.,'Operation For*')]"));
		Thread.sleep(2000);
		/*
		 * safeClick(driver, By.xpath(delActivityIcnLoc)); if (isElementPresent(driver,
		 * By.xpath(delActivityIcnLoc)) == false) { Reporter.log(OperationFor + " " +
		 * dataTag + " is deleted from master list"); }
		 */

	}

	/**
	 * Add Category and Collection under Master Data
	 * 
	 * @param driver
	 * @param inputTypes
	 * @throws Exception
	 */
	public void addMasterDataCategoryCollection(ArrayList<String> inputTypes) throws Exception {
		String addActivityloc = "(//section[@id='manageActivity']//span[text()='" + inputTypes.get(2) + "'])[2]",
				delActivityIcnLoc = "//section[@id='manageActivity']//li[contains(.,'" + inputTypes.get(2) + "')]/i",
				clickOpertionFrLoc = "//section[@id='manageActivity']//select[contains(.,'" + inputTypes.get(0) + "')]",
				productTypeLoc = "//section[@id='manageActivity']//select[contains(.,'" + inputTypes.get(1) + "')]";

		smartSelect(driver, By.xpath(clickOpertionFrLoc), inputTypes.get(0));
		smartSelect(driver, By.xpath(productTypeLoc), inputTypes.get(1));
		Thread.sleep(1000);
		if (isElementPresent(driver, By.xpath(addActivityloc)) == true) {
			Thread.sleep(2000);
			safeClick(driver, By.xpath(delActivityIcnLoc));
		}
		Thread.sleep(1000);
		safeClick(driver, By.xpath("//section[@id='manageActivity']//span/a[contains(.,'Add')]"));
		Thread.sleep(1000);
		safeType(driver, By.xpath("(//input[@ng-model='new_data.name' and @type='text'])[2]"), inputTypes.get(2));
		safeType(driver, By.xpath("//textarea[@ng-model='new_data.description']"), inputTypes.get(3));
		/*
		 * if (inputTypes.get(0).equals("Collections")) { safeClick(driver,
		 * By.xpath("//input[@ng-model='new_data.is_custom']")); }
		 */
		safeClick(driver, By.xpath("//a[@ng-click='add_data()']"));
		Thread.sleep(2000);
		safeClick(driver, By.xpath("//section[@id='manageActivity']//label[contains(.,'Operation For*')]"));
		Thread.sleep(2000);
		textAssert(driver, By.xpath(addActivityloc), inputTypes.get(2));
		Reporter.log(inputTypes.get(0) + " " + inputTypes.get(2) + " is added into master list",true);
		Thread.sleep(2000);
		if (inputTypes.get(1).equals("Things to do")) {
			driver.get(baseUrl + "/activities/new?code=things_to_do");
		} else if (inputTypes.get(1).contains("Food")) {
			driver.get(baseUrl + "/activities/new?code=fnb");
		}
		List<WebElement> list = driver.findElements(getObjectLocals("CampFormone_CollectionList"));
		for (int itr = 0; itr < list.size(); itr++) {
			// System.out.println(list.get(itr).getText());
			if (list.get(itr).getText().equals(inputTypes.get(2))) {
				Reporter.log(inputTypes.get(3) + " collection is present in " + inputTypes.get(1),true);
				break;
			} else if (itr == list.size()) {
				Reporter.log(inputTypes.get(3) + " collection isn't present in " + inputTypes.get(1),true);
				Assert.fail();
			}

		}
		/*
		 * safeClick(driver, By.xpath(delActivityIcnLoc)); if (isElementPresent(driver,
		 * By.xpath(delActivityIcnLoc)) == false) { Reporter.log(inputTypes.get(0) + " "
		 * + inputTypes.get(2) + " is deleted from master list"); }
		 */

	}

	/**
	 * Manage Reorder for TTD,Events and FNB
	 * 
	 * @param driver
	 * @param inputs
	 * @throws Exception
	 */
	public void manageReorder(HashMap<String, String> inputs) throws Exception {
		Thread.sleep(2000);
		safeType(driver, getObject("Camp_ManageData_CityBox"), inputs.get("city"));
		Thread.sleep(1000);
		safeClick(driver, getObject("AirCom_HomePage_From_Ajax"));
		Reporter.log("City Selected : " + inputs.get("city"),true);
		Thread.sleep(2000);
		safeClickListContains(driver, getObject("Camp_ManageData_ProductTypeLoc"), inputs.get("productType"));
		Reporter.log("Product Selected : " + inputs.get("productType"),true);
		Thread.sleep(2000);
		if (isElementPresent(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_delActivityloc"),
				inputs.get("collection")))) == true) {
			safeClick(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_DelActivityIcnLoc"),
					inputs.get("collection"))));
		}
		safeClick(driver, getObject("Camp_ManageData_CollectionClick"));
		safeType(driver, getObject("Camp_ManageInput_input"), inputs.get("collection"));
		safeClick(driver, By.xpath(
				getXpathByReplace(objectRepos.value("Camp_ManageData_CollectionLoc"), inputs.get("collection"))));
		Reporter.log("Collection Selected : " + inputs.get("collection"),true);
		Thread.sleep(3000);
		textAssert(driver, By.xpath(
				getXpathByReplace(objectRepos.value("Camp_ManageData_AddActivityloc"), inputs.get("collection"))),
				inputs.get("collection"));
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormOne_SaveBtn"));
		Thread.sleep(2000);
		textAssert(driver, By.xpath("//h3"), "Order Updated Successfully");
		Reporter.log("Reorder for " + inputs.get("productType") + " Updated SUccessfully",true);
	}

	/**
	 * Manage Sort Listing for TTD,Events and FNB
	 * 
	 * @param driver
	 * @param inputTypes
	 * @throws Exception
	 */
	public void manageSortListings(HashMap<String, String> inputTypes) throws Exception {
		Thread.sleep(2000);
		safeType(driver, getObject("Camp_ManageData_CityBox"), inputTypes.get("city"));
		Thread.sleep(1000);
		safeClick(driver, getObject("AirCom_HomePage_From_Ajax"));
		Reporter.log("City Selected : " + inputTypes.get("city"),true);
		Thread.sleep(2000);
		safeClickListContains(driver, getObject("Camp_ManageData_ProductTypeLoc"), inputTypes.get("productType"));
		Reporter.log("Product Selected : " + inputTypes.get("productType"),true);

		Thread.sleep(3000);
		safeClick(driver, getObject("Camp_ManageData_CollectionClick"));
		Thread.sleep(2000);
		safeType(driver, By.xpath("//section[@id='manageActivity']//div[3]/dd//input"), inputTypes.get("collection"));
		safeClick(driver, By.xpath(
				getXpathByReplace(objectRepos.value("Camp_ManageData_CollectionLoc"), inputTypes.get("collection"))));
		Reporter.log("Collection Selected : " + inputTypes.get("collection"),true);
		Thread.sleep(2000);

		if (!inputTypes.get("productType").equals("Events")) {
			if (isElementPresent(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_delActivityloc"),
					inputTypes.get("list")))) == true) {
				safeClick(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_DelActivityIcnLoc"),
						inputTypes.get("list"))));
			}

			Thread.sleep(2000);
			safeClick(driver, getObject("Camp_ManageData_listClk"));
			safeType(driver, By.xpath("//section[@id='manageActivity']//div[4]/dd//input"), inputTypes.get("list"));
			safeClick(driver, By.xpath(
					getXpathByReplace(objectRepos.value("Camp_ManageData_CollectionLoc"), inputTypes.get("list"))));
			Thread.sleep(2000);

			textAssert(driver, By.xpath(
					getXpathByReplace(objectRepos.value("Camp_ManageData_AddActivityloc"), inputTypes.get("list"))),
					inputTypes.get("list"));
		}
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormOne_SaveBtn"));
		Thread.sleep(2000);
		//textAssert(driver, By.xpath("//h3"), "Listings Updated");
		Reporter.log(inputTypes.get("productType") + " Sorted listing Updated",true);
	}

	/**
	 * Manage assign collection for ttd,fnb and events activity
	 * 
	 * @param driver
	 * @param inputTypes
	 * @throws Exception
	 */
	public void manageAssignCollection(HashMap<String, String> inputTypes) throws Exception {
		safeClickListContains(driver, getObject("Camp_ManageData_ProductTypeLoc"), inputTypes.get("productType"));
		Reporter.log("Product Selected : " + inputTypes.get("productType"),true);
		Thread.sleep(3000);
		safeClickListContains(driver, getObject("Camp_ManageData_ThemsListLoc"), inputTypes.get("category"));
		Thread.sleep(3000);
		if (isElementPresent(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_AddedCollectionLoc"),
				inputTypes.get("collection")))) == true) {
			safeClick(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_DelActivityIcnLoc"),
					inputTypes.get("collection"))));
		}
		Thread.sleep(3000);
		safeClick(driver, getObject("Camp_ManageData_CollectionListClkLoc"));
		safeClick(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_CollectionselectLoc"),
				inputTypes.get("collection"))));
		Reporter.log("collection Selected : " + inputTypes.get("collection"),true);
		safeClick(driver, By.xpath("//button[contains(.,'Update')]"));
		Thread.sleep(3000);
		textAssert(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_AddedCollectionLoc"),
				inputTypes.get("collection"))), inputTypes.get("collection"));
		Reporter.log(inputTypes.get("category") + " is added under " + inputTypes.get("collection"),true);
	}

	/**
	 * Manage Order collection for ttd,fnd and events activity
	 * 
	 * @param driver
	 * @param inputTypes
	 * @throws Exception
	 */
	public void manageOrderCollection(HashMap<String, String> inputTypes) throws Exception {
		Thread.sleep(3000);
		safeClick(driver, getObject("Camp_ManageData_orderCategoryLoc"));
		safeType(driver, getObject("Camp_ManageData_CityBox"), inputTypes.get("city"));
		Thread.sleep(1000);
		safeClick(driver, getObject("AirCom_HomePage_From_Ajax"));
		Reporter.log("City Selected : " + inputTypes.get("city"),true);
		Thread.sleep(1000);
		safeClickListContains(driver, getObject("Camp_ManageData_productType1Loc"), inputTypes.get("productType"));
		Reporter.log("Product Selected : " + inputTypes.get("productType"),true);
		Thread.sleep(3000);
		if (isElementPresent(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_CollectionLoc"),
				inputTypes.get("category")))) == true) {
			safeClick(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_DelActivityIcnLoc"),
					inputTypes.get("category"))));

		}
		safeClickListContains(driver, getObject("Camp_ManageData_categotiesLoc"), inputTypes.get("category"));
		Reporter.log("Category Selected : " + inputTypes.get("category"),true);
		Thread.sleep(3000);
		textAssert(driver, By.xpath(
				getXpathByReplace(objectRepos.value("Camp_ManageData_delActivityloc"), inputTypes.get("category"))),
				inputTypes.get("category"));
		safeClick(driver, By.xpath("//button"));
		Reporter.log("Manage Category for " + inputTypes.get("productType") + " updated sucessfully",true);
	}

	/**
	 * manage custom collection for ttd ,fnb and event activity
	 * 
	 * @param driver
	 * @param inputTypes
	 * @throws Exception
	 */
	public void manageCustomCollection(HashMap<String, String> inputTypes) throws Exception {
		Thread.sleep(1000);
		safeType(driver, getObject("Camp_ManageData_locationLoc"), inputTypes.get("city"));
		Thread.sleep(1000);
		safeClick(driver, getObject("AirCom_HomePage_From_Ajax"));
		Thread.sleep(1000);
		safeClickListContains(driver, getObject("Camp_ManageData_productType2Loc"), inputTypes.get("productType"));
		Thread.sleep(1000);
		safeClick(driver, getObject("Camp_ManageData_collectionLinkLoc"));

		safeClick(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_masterCollectionLoc"),
				inputTypes.get("collection"))));
		Thread.sleep(2000);

		if (isElementPresent(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_activityPresentloc"),
				inputTypes.get("activity")))) == true) {

			safeClick(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_activityRemoveLoc"),
					inputTypes.get("activity"))));
		}
		safeType(driver, getObject("Camp_ManageData_activtyNameLoc"), inputTypes.get("activity"));
		Thread.sleep(2000);
		dragAndDrop(driver, "//div[3]/ul/li", "//div[4]");
		Reporter.log(inputTypes.get("activity") + " drag and drop under this collection: " + inputTypes.get("collection"),true);
		Thread.sleep(2000);
		safeClick(driver, getObject("LocalCom_Activity_BookPage_Bookbutton"));
		Thread.sleep(2000);
		textAssert(driver, getObject("Camp_ManageData_statusMsg"), "Success");
		Reporter.log(inputTypes.get("productType") + " custom collection is updated",true);
	}

	/**
	 * Manage Item Recommendation for ttd,FNB and event activity
	 * 
	 * @param driver
	 * @param inputs
	 * @throws Exception
	 */
	public void manageItemRecommendation(HashMap<String, String> inputs) throws Exception {
		Thread.sleep(2000);
		safeType(driver, getObject("Camp_ManageData_CityBox"), inputs.get("city"));
		Thread.sleep(1000);
		safeClick(driver, getObject("AirCom_HomePage_From_Ajax"));
		Reporter.log("City Selected : " + inputs.get("city"),true);
		Thread.sleep(2000);
		safeClickListContains(driver, getObject("Camp_ManageData_ProductTypeLoc"), inputs.get("productType"));
		Reporter.log("Product Selected : " + inputs.get("productType"),true);
		Thread.sleep(2000);
		if (isElementPresent(driver, By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_itemRecommand"),
				inputs.get("activity")))) == true) {
			safeClick(driver, By.xpath(
					getXpathByReplace(objectRepos.value("Camp_ManageData_DelActivityIcnLoc"), inputs.get("activity"))));
		}
		safeType(driver, getObject("Camp_ManageInput_input"), inputs.get("activity"));
		smartClick(driver, By.xpath("(//body/ul/li/a)[2]"));
		Reporter.log("activity Selected : " + inputs.get("activity"),true);
		Thread.sleep(3000);
		textAssert(driver,
				By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_itemRecommand"), inputs.get("activity"))),
				inputs.get("activity"));
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormOne_SaveBtn"));
		Thread.sleep(3000);
		if (inputs.get("productType").equalsIgnoreCase("Events"))
			textAssert(driver, By.xpath("//h3"), "Indexed Successfully");
		Reporter.log("Reorder for " + inputs.get("productType") + " Indexed Successfully",true);
	}

	public void homeEditorialCarousal(RemoteWebDriver driver, String product) throws Exception {
		safeClick(driver, By.xpath("(//input[@name='editorial_type'])[3]"));
		safeSelect(driver, getObject("Camp_Editorial_HomePage_Layout"), "Carousel");
		if (product == "TTD") {
			safeAutocomplete(driver, By.name("hp_editorial_cities"), By.xpath("//li/a[contains(.,'Bagalkot')]"), "Bagal");
			safeClick(driver, By.xpath("(//input[@name='product'])[1]"));
			scrollToElement(driver, getObject("Camp_Editorial_HomePage_Pickactivities"));
			safeAutocomplete(driver, getObject("Camp_Editorial_HomePage_Pickactivities"),
					By.linkText("test carousal activity -- Activity"), "test carousal");

		} else if (product == "FNB") {
			safeAutocomplete(driver, By.name("hp_editorial_cities"), By.xpath("//li/a[contains(.,'Bagalkot')]"), "Bagal");
			safeClick(driver, By.xpath("(//input[@name='product'])[2]"));
			safeAutocomplete(driver, getObject("Camp_Editorial_HomePage_Pickactivities"),
					By.linkText("SureshVar -- Variant"), "SureshVar");
			// upload image as well
			safeSelect(driver, By.name("activity_tags"), "Deal");
			safeClick(driver, By.xpath("(//input[@name='image_type-0'])[2]"));
			selectFile(driver);

		} else if (product == "Events") {
			safeAutocomplete(driver, By.name("hp_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"), "Bangal");
			safeClick(driver, By.xpath("(//input[@name='product'])[3]"));
			safeAutocomplete(driver, getObject("Camp_Editorial_HomePage_Pickactivities"),
					By.linkText("Event automation paid -- Event"), "Event automation paid");
			// text overlay hide
			scrollToElement(driver, By.xpath("//input[@ng-model='activity.text_overlay_hide']"));
			safeClick(driver, By.xpath("//input[@ng-model='activity.text_overlay_hide']"));
		}

	}

	public void mapHomePage(RemoteWebDriver driver, String product) throws Exception {

		if (product == "Events") {
			safeAutocomplete(driver, By.xpath("//input[@ng-model='newTag.text']"),
					By.xpath("//span/em[text()='Bangalore']"), campLocal.value("campCityEvent"));
		} else {
			safeAutocomplete(driver, By.xpath("//input[@ng-model='newTag.text']"),
					By.xpath("//span/em[text()='Bagalkot']"), campLocal.value("campCity"));
		}
		scrollToElement(driver, By.xpath("//input[@value='Save']"));
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("window.scrollBy(0,450)", "");
		safeClick(driver, By.xpath("//input[@value='Save']"));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		if(isElementPresent(driver,By.xpath("//div[@class='block-ui-message ng-binding']"))) {
			Thread.sleep(30000);
		}
		
		//Thread.sleep(7000);
		boolean elementVisible = isElementPresent(driver, By.xpath("//input[@value='Save']"));
		Reporter.log("Save button elementVisible status : " + elementVisible,true);
		Assert.assertEquals(false, elementVisible);

	}

	public void homeEditorialCollection(RemoteWebDriver driver, String product) throws Exception {
		safeClick(driver, By.xpath("(//input[@name='editorial_type'])[3]"));
		safeSelect(driver, getObject("Camp_Editorial_HomePage_Layout"), "Collection Cards");
		if (product == "TTD") {
			safeAutocomplete(driver, By.name("hp_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
			safeClick(driver, By.xpath("(//input[@name='product'])[1]"));
			scrollToElement(driver, getObject("Camp_Editorial_HomePage_Pickactivities"));
			safeAutocomplete(driver, getObject("Camp_Editorial_HomePage_Pickactivities"),
					By.linkText("Boating -- Collection"), "Boating");
			safeAutocomplete(driver, getObject("Camp_Editorial_HomePage_Pickactivities"),
					By.linkText("Camping -- Collection"), "Camping");

		} else if (product == "FNB") {
			safeAutocomplete(driver, By.name("hp_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
			safeClick(driver, By.xpath("(//input[@name='product'])[2]"));
			safeAutocomplete(driver, getObject("Camp_Editorial_HomePage_Pickactivities"),
					By.linkText("Auto FNB -- Collection"), "Auto FNB");

		} else if (product == "Events") {
			safeAutocomplete(driver, By.name("hp_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityEventPartial"));
			safeClick(driver, By.xpath("(//input[@name='product'])[3]"));
			safeAutocomplete(driver, getObject("Camp_Editorial_HomePage_Pickactivities"),
					By.linkText("Music / Concerts / Live Performance -- Collection"), "Music");

		}

	}

	public void homeEditorialactivityList(RemoteWebDriver driver, String product) throws Exception {

		safeClick(driver, By.xpath("(//input[@name='editorial_type'])[3]"));
		safeSelect(driver, getObject("Camp_Editorial_HomePage_Layout"), "Activities List");
		if (product == "TTD") {
			safeAutocomplete(driver, By.name("hp_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
			safeClick(driver, By.xpath("(//input[@name='product'])[1]"));
			scrollToElement(driver, getObject("Camp_Editorial_HomePage_Pickactivities"));
			safeAutocomplete(driver, getObject("Camp_Editorial_HomePage_Pickactivities"),
					By.linkText("Camping -- Collection"), "Camping");
			Thread.sleep(2000);
			dragAndDrop(driver, "(//li[@ng-drag-data='obj'])[1]", "//ul[@class='cat-list cities list-unstyled']");

		} else if (product == "FNB") {
			safeAutocomplete(driver, By.name("hp_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
			safeClick(driver, By.xpath("(//input[@name='product'])[2]"));
			safeAutocomplete(driver, getObject("Camp_Editorial_HomePage_Pickactivities"),
					By.linkText("Auto FNB -- Collection"), "Auto FNB");
			Thread.sleep(5000);
			scrollToElement(driver, By.xpath("(//li[@ng-drag-data='obj'])[1]"));
			dragAndDrop(driver, "(//li[@ng-drag-data='obj'])[1]", "//ul[@class='cat-list cities list-unstyled']");
			safeSelect(driver, By.name("activity_tags"), "Deal");
			safeClick(driver, By.xpath("(//input[@name='image_type-0'])[2]"));
			scrollToElement(driver, By.xpath("//input[@ng-model='activity.text_overlay_hide']"));
			safeClick(driver, By.xpath("//input[@ng-model='activity.text_overlay_hide']"));
			selectFile(driver);

		} else if (product == "Events") {
			safeAutocomplete(driver, By.name("hp_editorial_cities"), By.linkText(campLocal.value("campCityEvent")),
					campLocal.value("campCityEventPartial"));
			safeClick(driver, By.xpath("(//input[@name='product'])[3]"));
			scrollToElement(driver, getObject("Camp_Editorial_HomePage_Pickactivities"));
			safeAutocomplete(driver, getObject("Camp_Editorial_HomePage_Pickactivities"),
					By.linkText("Music / Concerts / Live Performance -- Collection"), "Music");
			Thread.sleep(2000);
			dragAndDrop(driver, "(//li[@ng-drag-data='obj'])[1]", "//ul[@class='cat-list cities list-unstyled']");
		}
	}

	public void selectFile(RemoteWebDriver driver) throws Exception {
		safeClick(driver, By.linkText("Select File"));

		Thread.sleep(3000);
		WebElement element = driver.findElement(By.name("image"));
		File f = new File("exe\\itsme.png");
		Thread.sleep(5000);
		element.sendKeys(f.getAbsolutePath());
		/*Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();*/
		Robot R = new Robot(); R.keyPress(KeyEvent.VK_ESCAPE);
		R.keyPress(KeyEvent.VK_ESCAPE);
	}

	/**
	 * Manage Data of nearby cities
	 * 
	 * @param driver
	 * @param inputs
	 * @throws Exception
	 */
	public void manageNearByCity(RemoteWebDriver driver, HashMap<String, String> inputs) throws Exception {
		Thread.sleep(2000);
		safeType(driver, getObject("Camp_ManageData_nearcity"), inputs.get("city"));
		Thread.sleep(1000);
		safeClick(driver, getObject("AirCom_HomePage_From_Ajax"));
		Reporter.log("City Selected : " + inputs.get("city"),true);
		Thread.sleep(2000);
		if (isElementPresent(driver, By.xpath(
				getXpathByReplace(objectRepos.value("Camp_ManageData_CollectionLoc"), inputs.get("nearCity"))))) {
			Thread.sleep(1000);
			safeClick(driver, By.xpath(
					getXpathByReplace(objectRepos.value("Camp_ManageData_DelActivityIcnLoc"), inputs.get("nearCity"))));
		}
		Thread.sleep(9000);
		safeType(driver, getObject("Camp_ManageData_nearestcity"), inputs.get("nearCity"));
		Thread.sleep(1000);
		smartClick(driver, By.xpath("(//body/ul/li/a)[2]"));
		Reporter.log("Nearest city Selected : " + inputs.get("nearCity"),true);
		Thread.sleep(5000);
		textAssert(driver,
				By.xpath(getXpathByReplace(objectRepos.value("Camp_ManageData_CollectionLoc"), inputs.get("nearCity"))),
				inputs.get("nearCity"));
	}

	/**
	 * Account PLB Configured
	 * 
	 * @param driver
	 * @param inputs
	 * @throws Exception
	 */
	public void accountPLBConfigure(RemoteWebDriver driver, HashMap<String, String> inputs) throws Exception {
		safeType(driver, getObject("Camp_Account_SearchESP"), inputs.get("espName"));
		safeClick(driver, getObject("Camp_Account_fetch"));
		safeClick(driver, getObject("Camp_Account_savBtn"));
	}

	/**
	 * PLB Reports
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void plbReports(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObjectLocals("Camp_Account_Reports"));
		safeClick(driver, getObjectLocals("Camp_ReportType"));
		safeClick(driver, getObjectLocals("Camp_Start_Date"));
		safeClick(driver, By.xpath("//a[2]/span"));
		safeClick(driver, By.linkText("1"));
		safeClick(driver, getObjectLocals("Camp_Till_Date"));
		safeClick(driver, By.xpath("//a[2]/span"));
		safeClick(driver, By.linkText("2"));
		safeClick(driver, getObjectLocals("Camp_Report_Download"));
		Reporter.log("Status Report download sucessfully",true);
		safeClick(driver, getObjectLocals("Camp_AccuralReport"));
		safeClick(driver, getObjectLocals("Camp_Report_Download"));
		Reporter.log("Accural Redemption report download sucessfully",true);
	}

	public void waitForPageLoading(int waitTime) {
		try {
			switch (waitTime) {
			case 1:
				Thread.sleep(1000);
				break;
			case 2:
				Thread.sleep(2000);
				break;
			case 3:
				Thread.sleep(3000);
				break;
			case 4:
				Thread.sleep(4000);
				break;
			case 5:
				Thread.sleep(5000);
				break;
			case 6:
				Thread.sleep(6000);
				break;
			case 7:
				Thread.sleep(7000);
				break;
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	public void campAccountApprove(RemoteWebDriver driver) throws Exception {
		selectAccount(driver, "");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='supplier-dashboard']//td[2]/a")).click();
		safeSelect(driver, getObject("Camp_Activities_Account_Channel"), "Organic Activation");
		// Thread.sleep(2000);
		safeClick(driver, By.id("is_wl_partner"));

		safeAutocompleteMouseHover(driver, getObject("Camp_Activities_Account_AssignIMD"),
				getObject("camp_Activities_Account_IMD_dropdown"), "activitiescleartrip13");
		scrollToElement(driver, getObject("Camp_Activities_Account_FnbCheckbox"));
		safeClick(driver, getObject("Camp_Activities_Account_FnbCheckbox"));
		// driver.findElement(By.xpath("//input[@placeholder='Search for a Brand / Chain
		// Eg : Nandhana']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search for a Brand / Chain Eg : Nandhana']"))
				.sendKeys("Dhanaraj chain");
		Thread.sleep(2000);
		// safeAutocompleteMouseHover(driver,getObject("Camp_Activities_Account_SelecChain"),getObject("Camp_Activities_Account_ChainDropdown"),"Dhanaraj");
		safeClick(driver, getObject("Camp_Activities_Account_ChainDropdown"));
		safeClick(driver, getObject("Camp_Activities_Account_Approve"));

	}

	public void wlAccountPage(RemoteWebDriver driver) throws Exception {
		driver.get(baseUrl+"/wl/listings");
		//safeClick(driver, By.linkText("WL Accounts"));
		waitForPageLoading(2000);
		safeClick(driver, By.xpath("(//td/a)[1]"));
	}

	public void wlAccountConfig(RemoteWebDriver driver) throws Exception {
		waitForPageLoading(2);
		safeClick(driver, By.xpath("(//a[contains(text(),'Edit')])[2]"));
		scrollToElement(driver, By.xpath("//span[text()='Add User']"));
		safeClick(driver, By.xpath("//span[text()='Add User']"));
		safeType(driver, By.name("user_first_name"), "fname");
		safeType(driver, By.name("user_last_name"), "lname");
		safeType(driver, By.name("user_email"), "test@camp.com");
		waitForPageLoading(2000);
		//safeType(driver, By.name("revenue_share"), "30");
		//safeClick(driver, By.xpath("(//input[@name='countryFiltertype'])[1]"));
		//safeClick(driver, By.id("isAgencyEnable"));
		safeClick(driver, By.xpath("//button[text()='Submit']"));
		// waitForPageLoading(10000);
		// Thread.sleep(30000);

	}

	public boolean orderCarousal(RemoteWebDriver driver, String product, String city, String CarousalType)
			throws Exception {
		String input = null;
		safeClick(driver, By.linkText("Order Editorials"));
		if (product == "TTD") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
			safeSelect(driver, getObject("Camp_OrderEditorial_SelectProduct"), "Things to do");
		} else if (product == "FNB") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"), By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
			safeSelect(driver, getObject("Camp_OrderEditorial_SelectProduct"), "Food and Beverages");
		} else if (product == "Events") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"),
					By.linkText(campLocal.value("campCityEvent")), campLocal.value("campCityEventPartial"));
			safeSelect(driver, getObject("Camp_OrderEditorial_SelectProduct"), "Events");
		}

		Thread.sleep(15000);
		safeClick(driver, By.xpath("//form[@id='editorial_configuration_form']//input[@value='carousel']"));
		Thread.sleep(2000);
		// safeType(driver,
		// By.name("editorialConfiguration_cityFilter"),common.value("releasenumber")+"editorial
		// name");
		if (CarousalType == "CarousalActivity") {
			safeType(driver, By.name("editorialConfiguration_cityFilter"),
					common.value("releasenumber") + campLocal.value("CampCarousalActivity"));
			input = common.value("releasenumber") + campLocal.value("CampCarousalActivity");
		} else if (CarousalType == "CarousalCollection") {
			safeType(driver, By.name("editorialConfiguration_cityFilter"),
					common.value("releasenumber") + campLocal.value("CampCarousalCollection"));
			input = common.value("releasenumber") + campLocal.value("CampCarousalCollection");
		} else if (CarousalType == "CarousalOfferActivity") {
			safeType(driver, By.name("editorialConfiguration_cityFilter"),
					common.value("releasenumber") + campLocal.value("CampCarousalOfferActivity"));
			input = common.value("releasenumber") + campLocal.value("CampCarousalOfferActivity");
		} else if (CarousalType == "CarousalOfferCollection") {
			safeType(driver, By.name("editorialConfiguration_cityFilter"),
					common.value("releasenumber") + campLocal.value("CampCarousalOfferCollection"));
			input = common.value("releasenumber") + campLocal.value("CampCarousalOfferCollection");
		} else if (CarousalType == "CarousalOfferAll") {

			safeType(driver, By.name("editorialConfiguration_cityFilter"),
					common.value("releasenumber") + campLocal.value("CampCarousalOfferAll"));
			input = common.value("releasenumber") + campLocal.value("CampCarousalOfferAll");
		}
		waitForPageLoading(1000);
		dragAndDrop(driver, "//li[@ng-drag-data='obj']", "//ul[contains(@class,'editorial-cat-list border')]");
		waitForPageLoading(1000);
		safeClick(driver, By.xpath("//input[@value='Save']"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		// String
		// input=common.value("releasenumber")+campLocal.value("CampCarousalCollection");
		
		return editorialSearchAPI(input, product.toLowerCase(), city);

	}

	public void wlSiteConfig(RemoteWebDriver driver) throws Exception {
		waitForPageLoading(1);
		elementNotVisible(driver, By.xpath("//div[contains(text(),'Loading')]"), 200);
		scrollToElement(driver, getObject("Camp_WL_SiteConfig"));
		safeClick(driver, getObject("Camp_WL_SiteConfig"));
		safeType(driver, getObject("Camp_WL_UrlSlug"), common.value("releasenumber") + "regression");
		selectFile(driver);
		Thread.sleep(2000);
		safeType(driver, By.name("header_color_picker"), "#6900ff");
		safeType(driver, By.name("font_color_picker"), "#00d9ff");
		scrollToElement(driver, By.name("isCTCobranded"));

		safeClick(driver, getObject("Camp_WL_HeaderLink"));
		safeType(driver, By.name("header_title"), "About us");
		safeType(driver, By.name("header_url"), "https://qa2.cleartrip.com/about/");
		safeClick(driver, getObject("Camp_WL_FooterLink"));
		safeType(driver, By.name("footer_title"), "Cleartrip business");
		safeType(driver, By.name("footer_url"), "https://www.cleartripforbusiness.com/");
		safeClick(driver, By.name("isCTCobranded"));
		scrollToElement(driver, By.xpath("//button[text()='Submit']"));
		safeClick(driver, By.xpath("//button[text()='Submit']"));

	}

	public void communicationConfig(RemoteWebDriver driver) throws Exception {
		waitForPageLoading(1);
		elementNotVisible(driver, By.xpath("//div[contains(text(),'Loading')]"), 300);
		scrollToElement(driver, getObject("Camp_WL_CommunicationConfig"));
		safeClick(driver, getObject("Camp_WL_CommunicationConfig"));
		safeType(driver, By.name("sender_email"), "automation@camp.com");
		safeType(driver, By.name("communication_email"), "test@camp.com");
		safeClick(driver, By.xpath("//button[text()='Submit']"));
	}

	public void contentConfig(RemoteWebDriver driver) throws Exception {
		waitForPageLoading(1);
		elementNotVisible(driver, By.xpath("//div[contains(text(),'Loading')]"), 300);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(getObject("Camp_WL_ContentConfig")));
		safeClick(driver, getObject("Camp_WL_ContentConfig"));
		safeAutocomplete(driver, By.name("city"), By.linkText("Bangalore"), "Bangal");
		safeClick(driver, By.name("allCities"));
		safeClick(driver, By.id("ttd"));
		safeClick(driver, By.id("fnb"));
		safeClick(driver, By.id("editorial"));
		scrollToElement(driver, By.id("is_book_flow_enabled"));
		safeClick(driver, By.id("is_book_flow_enabled"));
		safeClick(driver, By.xpath("//button[text()='Submit']"));
		waitForPageLoading(1);
		elementNotVisible(driver, By.xpath("//div[contains(text(),'Loading')]"), 300);
		safeClick(driver, By.xpath("//button[text()='Go Live']"));
		System.out.println("Go live clicked");

	}

	public void campSignup(RemoteWebDriver driver, String email,String name,String domain, String env) throws Exception {

		wait = new WebDriverWait(driver,500000);
		//LocalDate date = LocalDate.now();
		driver.get("http://"+env+".cleartrip."+domain+"/camp/accounts/sign_up");
		safeType(driver, By.name("first_name"), "fname");
		safeType(driver, By.name("last_name"), "lname");
		safeType(driver, By.name("email"),email);
		safeType(driver, By.name("mobile_number"), "1234567890");
		safeAutocomplete(driver, By.name("resident_city"), By.linkText("Dubai"), "Duba");
		safeType(driver, By.name("company_name"),name);
		scrollToElement(driver, By.xpath("//button[@type='submit']"));
		safeClick(driver, By.xpath("//button[@type='submit']"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
	}

	public boolean vCampActivityLocalJson(String productType, String city, String collection, String actId)
			throws SQLException {
		String cityUri, cityIdSearch, cityId, collectionId, actSearch, url;
		if (city.equals("Dubai")) {
			url = campLocal.value("murlLocalsAE");
		} else 
			url = campLocal.value("murlLocals");
		Boolean flag = false;
		cityUri = url + "/api/v1/" + productType + "/search?city=" + city;
		cityIdSearch = get(cityUri).asString();
		cityId = from(cityIdSearch).get("city.id");
		collectionId = dbSchemaGetValue("ID","ACTIVITY_COLLECTIONS","name",collection);
		actSearch = url + "/api/v1/" + productType + "/city/" + cityId + "/collection/" + collectionId;
		Response input = get(actSearch);
		//System.out.println(input.asString());
		final JsonPath jsonPath = new JsonPath(input.asString());
		if (productType.equals("ttd")) {
			List<HashMap<String, Integer>> resultList = null;
			resultList = jsonPath.get("result");
			for (int itr = 0; itr <= resultList.size(); itr++) {
				//System.out.println(resultList.get(itr).get("activity_id"));
				if (resultList.get(itr).get("activity_id") == Integer.parseInt(actId)) {
					Reporter.log("Activity id: " + actId + " found",true);
					flag = true;
					break;
				} else if (itr == resultList.size()) {
					Reporter.log("Activity id: " + actId + " didn't found in collection",true);
				}
			}
		} else if (productType.equals("fnb")) {
			List<List<HashMap<String, Integer>>> fnbResultList = null;
			List<HashMap<String, Integer>> fnbLocationList = null;
			fnbResultList = jsonPath.get("result.locations");
			
			 // System.out.println(fnbResultList.size()); System.out.println(fnbResultList);
			  for (int i = 0; i < fnbResultList.size(); i++) {

				fnbLocationList = jsonPath.get("result.locations[" + i + "].address");
				
				 // System.out.println(fnbLocationList);
				 // System.out.println(fnbLocationList.size());
				 
				for (int j = 0; j < fnbLocationList.size(); j++) {
					// System.out.println(fnbLocationList.get(j).get("activity_id"));
					if (fnbLocationList.get(j).get("activity_id") == Integer.parseInt(actId)) {
						Reporter.log("Activity id: " + actId + " found",true);
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	public String dbSchemaGetValue(String val,String tableName,String colName,String colVal) throws SQLException {

		final String DB_URL = "jdbc:oracle:thin:@//172.17.4.101:1521/cleardb";
		final String USER = "activities";
		final String PASS = "activities";
		String id = null;
		java.sql.Connection conn = null;
		java.sql.Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Reporter.log("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Reporter.log("Connected database successfully...");
			stmt = conn.createStatement();
			String sql = "select "+val+" from "+ tableName +" where "+ colName +"='" + colVal + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getString(val);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				conn.close();
		}
		return id;
	}

	public void orderProductEditorial(RemoteWebDriver driver, String product) throws Exception {
		if (product == "TTD") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
			safeSelect(driver, getObject("Camp_OrderEditorial_SelectProduct"), "Things to do");
		}
		if (product == "FNB") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
			safeSelect(driver, getObject("Camp_OrderEditorial_SelectProduct"), "Food and Beverages");
		}
		if (product == "Events") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"),
					By.linkText(campLocal.value("campCityEvent")), campLocal.value("campCityEventPartial"));
			safeSelect(driver, getObject("Camp_OrderEditorial_SelectProduct"), "Events");
		}
		// waitForElementToBeClickable(driver,
		// By.xpath("(//input[@name='editorialConfiguration_editorial_type'])[1]"));
		Thread.sleep(10000);
		safeClick(driver, By.xpath("//form[@id='editorial_configuration_form']//input[@value='product_editorial']"));
		waitForPageLoading(5);
		// scrollToElement(driver, By.xpath("//ul[contains(@class,'editorial-cat-list
		// border')]"));
		// safeType(driver,
		// By.name("editorialConfiguration_cityFilter"),common.value("releasenumber")+"editorial
		// name");
		String productEdName = common.value("releasenumber") + "ProductEditorial";

		dragAndDrop(driver,
				getXpathByReplace(objectReposLocals.value("Camp_OrderProductEditoiral_drag"), productEdName),
				"//ul[contains(@class,'editorial-cat-list border')]");
		safeClick(driver, By.xpath("//input[@value='Save']"));

	}

	public void homePageExplore(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObjectLocals("Camp_homePageExplore"));
		safeAutocomplete(driver, By.name("hp_editorial_cities"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
				campLocal.value("campCityPartial"));
		selectPhoto(driver);
		safeType(driver, getObjectLocals("Camp_textDisplay"), "homePageexplore text");
		safeType(driver, getObjectLocals("Camp_HomePageExploreSearchKeyword"), "search keyword Auto");
		scrollToElement(driver, getObjectLocals("Camp_HomePageExploreLayout"));
		safeSelect(driver, getObjectLocals("Camp_HomePageExploreLayout"), "Explore Grid");

	}

	public void selectPhoto(RemoteWebDriver driver) throws Exception {
		safeClick(driver, By.xpath("(//input[@name='Photos'])[1]"));

		Thread.sleep(3000);
		WebElement element = driver.findElement(By.name("Photos"));
		File f = new File("exe\\itsme.png");
		Thread.sleep(5000);
		element.sendKeys(f.getAbsolutePath());
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void homePagegeneralWebview(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObjectLocals("Camp_homePageWebView"));
		safeSelect(driver, getObjectLocals("Camp_HomePageWebviewLayout"), "General Webview");
		selectFile(driver);
		safeType(driver, getObjectLocals("Camp_HomePageWebviewUrl"), "https://qa2.cleartrip.com/local");

	}

	public void homePageDeal(RemoteWebDriver driver, String deal) throws Exception {
		safeClick(driver, getObjectLocals("Camp_homePageWebView"));
		if (deal == "DealV2") {
			safeSelect(driver, getObjectLocals("Camp_HomePageWebviewLayout"), "Deal V2");
			selectFile(driver);
		} else if (deal == "DealV1") {
			safeSelect(driver, getObjectLocals("Camp_HomePageWebviewLayout"), "Deal V1");
		}
		safeType(driver, getObjectLocals("Camp_HomePageWebviewUrl"), "https://qa2.cleartrip.com/local");
		safeType(driver, getObjectLocals("Camp_HomePageWebviewCoupon"), "DealCoupon");

	}

	public void homePageHeroContent(RemoteWebDriver driver, String product) throws Exception {
		safeClick(driver, getObjectLocals("Camp_homePageWebView"));
		safeSelect(driver, getObjectLocals("Camp_HomePageWebviewLayout"), "Hero Content");
		safeType(driver, getObjectLocals("Camp_HomePageWebviewUrl"), "https://qa2.cleartrip.com/local");
		if (product == "TTD") {
			safeAutocomplete(driver, By.name("hp_editorial_pick_activities"),
					By.linkText("test carousal activity -- Activity"), "test carousal");
		} else if (product == "Events") {
			safeAutocomplete(driver, By.name("hp_editorial_pick_activities"), By.linkText("Post Payment Fix -- Event"),
					"Post Payment Fix");
		}
		selectFile(driver);

	}

	public void homeEditorialDetails(RemoteWebDriver driver, String htype) throws Exception {
		safeClick(driver, By.linkText("Create Editorials"));
		Thread.sleep(2000);
		safeClick(driver, getObject("Camp_CreateEditorialActiveYes"));
		if (htype == "heroTTD") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "heroTTD");
			safeType(driver, getObject("Camp_CreateEditorial_Title"), common.value("releasenumber") + "heroTTD");
		} else if (htype == "heroEvent") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "heroEvent");
			safeType(driver, getObject("Camp_CreateEditorial_Title"), common.value("releasenumber") + "heroEvent");
		} else if (htype == "TTDActivityList") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "TTDActivityList");
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + "TTDActivityList");
		} else if (htype == "FNBActivityList") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "FNBActivityList");
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + "FNBActivityList");
		} else if (htype == "EventActivityList") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"),
					common.value("releasenumber") + "EventActivityList");
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + "EventActivityList");
		} else if (htype == "TTDCarousal") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "TTDCarousal");
			safeType(driver, getObject("Camp_CreateEditorial_Title"), common.value("releasenumber") + "TTDCarousal");
		} else if (htype == "FNBCarousal") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "FNBCarousal");
			safeType(driver, getObject("Camp_CreateEditorial_Title"), common.value("releasenumber") + "FNBCarousal");
		} else if (htype == "EventCarousal") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "EventCarousal");
			safeType(driver, getObject("Camp_CreateEditorial_Title"), common.value("releasenumber") + "EventCarousal");
		} else if (htype == "TTDCollectionCard") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"),
					common.value("releasenumber") + "TTDCollectionCard");
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + "TTDCollectionCard");
		} else if (htype == "FNBCollectionCard") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"),
					common.value("releasenumber") + "FNBCollectionCard");
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + "FNBCollectionCard");
		} else if (htype == "EventCollectionCard") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"),
					common.value("releasenumber") + "EventCollectionCard");
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + "EventCollectionCard");
		} else if (htype == "DealV1") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "DealV1");
			safeType(driver, getObject("Camp_CreateEditorial_Title"), common.value("releasenumber") + "DealV1");
		} else if (htype == "DealV2") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "DealV2");
			safeType(driver, getObject("Camp_CreateEditorial_Title"), common.value("releasenumber") + "DealV2");
		} else if (htype == "homePageExplore") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "homePageExplore");
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + "homePageExplore");
		} else if (htype == "GeneralWebview") {
			safeType(driver, getObject("Camp_CreateEditorial_Name"), common.value("releasenumber") + "GeneralWebview");
			safeType(driver, getObject("Camp_CreateEditorial_Title"),
					common.value("releasenumber") + "GeneralWebview ");
		}
		// safeType(driver, getObject("Camp_CreateEditorial_Title"),
		// common.value("releasenumber") + "editorial title");
		safeType(driver, getObject("Camp_CreateEditorial_SubTitle"),
				common.value("releasenumber") + "editorial subtitle");
		safeType(driver, getObject("Camp_CreateEditorial_Description"),
				common.value("releasenumber") + "editorial description");
		safeClick(driver, getObject("Camp_CreateEditorial_StartDate"));
		safeClick(driver, By.xpath("//a[text()='1']"));
		// safeClick(driver, By.xpath("//a[2]/span"));
		// safeClick(driver, By.linkText("1"));
		Thread.sleep(1000);
		safeClick(driver, getObject("Camp_CreateEditorial_EndDate"));
		safeClick(driver, By.xpath("//a[2]/span"));
		safeClick(driver, By.linkText("20"));
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,280)", "");
		// safeClick(driver, By.xpath("(//input[@value='all'])"));
		safeClick(driver, By.xpath("(//input[@name='days_of_week'])[8]"));
	}

	public Boolean homePageConfig(RemoteWebDriver driver, String htype) throws Exception {
		Boolean flag = false;
		safeClick(driver, getObjectLocals("Camp_HomePageConfig"));
		safeAutocomplete(driver, By.name("editorialConfiguration_city"), By.xpath("//li/a[contains(.,'Bagalkot')]"),
				campLocal.value("campCityPartial"));
		safeClick(driver, getObjectLocals("Camp_HomePageConfigCity_Fetch"));
		vproductCardLocation("Product Cards");
		scrollToElement(driver, getObjectLocals("Camp_HomePageConfig_Filter"));

		if (htype == "heroTTD") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"), common.value("releasenumber") + "heroTTD");
		} else if (htype == "heroEvent") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "heroEvent");
		} else if (htype == "TTDActivityList") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "TTDActivityList");
		} else if (htype == "FNBActivityList") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "FNBActivityList");
		} else if (htype == "EventActivityList") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "EventActivityList");
		} else if (htype == "TTDCarousal") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "TTDCarousal");
		} else if (htype == "FNBCarousal") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "FNBCarousal");
		} else if (htype == "EventCarousal") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "EventCarousal");
		} else if (htype == "TTDCollectionCard") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "TTDCollectionCard");
		} else if (htype == "FNBCollectionCard") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "FNBCollectionCard");
		} else if (htype == "EventCollectionCard") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "EventCollectionCard");
		} else if (htype == "DealV1") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"), common.value("releasenumber") + "DealV1");
		} else if (htype == "DealV2") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"), common.value("releasenumber") + "DealV2");
		} else if (htype == "homePageExplore") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "homePageExplore");
		} else if (htype == "GeneralWebview") {
			safeType(driver, getObjectLocals("Camp_HomePageConfig_Filter"),
					common.value("releasenumber") + "GeneralWebview");
		}
		waitForPageLoading(4);
		dragAndDrop(driver, getXpathByReplace("(//li[contains(.,'nameLbl')])[1]", common.value("releasenumber")), "//ul[contains(@class,'editorial-cat-list border')]");
		dragAndDrop(driver, (getXpathByReplace(objectReposLocals.value("Camp_availableBlock"), "Product Cards")),
				"//ul[contains(@class,'editorial-cat-list border')]");

		safeClick(driver, By.xpath("//input[@value='Save']"));
		waitForPageLoading(1);
		safeAutocomplete(driver, By.name("editorialConfiguration_city"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
				campLocal.value("campCityPartial"));
		safeClick(driver, getObjectLocals("Camp_HomePageConfigCity_Fetch"));
		scrollToElement(driver, getObjectLocals("Camp_HomePageConfig_Publish"));
		safeClick(driver, getObjectLocals("Camp_HomePageConfig_Publish"));
		Thread.sleep(8000);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(getObjectLocals("Camp_HomePageConfig_PopUpPublish"))); 				
		//safeClick(driver, getObjectLocals("Camp_HomePageConfig_PopUpPublish"));
		// elementNotVisible(driver, By.xpath("//div[text()='Loading ...']"), 25);
		Thread.sleep(8000);
		String input = common.value("releasenumber") + htype;
		// return esSearch(input);
		return homePageSearchAPI(input, campLocal.value("campCity"));

	}

	public Boolean esSearch(String input) {

		Boolean flag = false;
		List<HashMap<String, String>> resultList = null;

		Response response = given().auth().basic("es_admin", "es_admin")
				.get("http://10.10.12.112:9200/local/homepage/_search?size=10000");
		final JsonPath jsonPath = new JsonPath(response.asString());
		resultList = jsonPath.get("hits.hits._source");

		for (int itr = 0; itr < resultList.size(); itr++) {
			// System.out.println(resultList.get(itr).get("name"));
			if (resultList.get(itr).get("name").equals(input)) {
				Reporter.log("Home page editorial name" +input+ " found",true);
				flag = true;
				break;
			} else if (itr == resultList.size()) {
				Reporter.log("Home page editorial name" +input+ "Not found",true);
			}
		}
		return flag;

	}

	public boolean editorialSearchAPI(String input, String productType, String cityName) {
		String requrl;
		boolean flag = false;

		List<HashMap<String, String>> resultList = null;

		requrl = common.value("murlLocals") + "/api/v1/" + productType + "/search?city=" + cityName;
		Response response = given().params("editorial", "true").get(requrl);
		System.out.println(response.asString());
		JsonPath jsonPath = new JsonPath(response.asString());
		resultList = jsonPath.get("editorial.carousel");
		// resultList = response.then().extract().path("editorial.carousel");
		// System.out.println(resultList.toString());
		for (int itr = 0; itr < resultList.size(); itr++) {
			// System.out.println("Carousal titles : " + resultList.get(itr).get("title"));
			if (resultList.get(itr).get("title").equals(input)) {
				Reporter.log("editorial name " +input+ " is found",true);
				flag = true;
				break;
			} else if (itr == resultList.size()) {
				Reporter.log("editorial name " +input+ " didn't found",true);
				org.testng.Assert.fail();
				
			}

		}
		return flag;
	}

	public void removeCarousal(RemoteWebDriver driver, String product) throws Exception {

		safeClick(driver, By.linkText("Order Editorials"));
		if (product == "TTD") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
			safeSelect(driver, getObject("Camp_OrderEditorial_SelectProduct"), "Things to do");
		} else if (product == "FNB") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"), By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
			safeSelect(driver, getObject("Camp_OrderEditorial_SelectProduct"), "Food and Beverages");
		} else if (product == "Events") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"),
					By.linkText(campLocal.value("campCityEvent")), campLocal.value("campCityEventPartial"));
			safeSelect(driver, getObject("Camp_OrderEditorial_SelectProduct"), "Events");
		}

		waitForPageLoading(7);
		safeClick(driver, By.xpath("(//input[@name='editorialConfiguration_editorial_type'])[1]"));
		waitForPageLoading(3);

		// scrollToElement(driver, By.name("editorialConfiguration_cityFilter"));
		List<WebElement> li = driver
				.findElements(By.xpath("//i[@ng-click='removeFromEnabledEditorial($index,obj,$event)']"));
		for (int i = 0; i < li.size(); i++) {
			li.get(i).click();
			waitForPageLoading(1);
		}
		scrollToElement(driver, By.xpath("//input[@value='Save']"));
		waitForPageLoading(2);
		safeClick(driver, By.xpath("//input[@value='Save']"));
		waitForPageLoading(2);
	}

	public void NewActivityFormOneESP(String Category, String ActivityType, String MeetingPoint,
			String PickUpDrop,String activityName, String domain) throws Exception {
		/*elementVisible(driver, getObject("Camp_Activities_GSTIN"), 10);
		Thread.sleep(1000);
		safeType(driver, getObject("Camp_Activities_GSTIN"), dataFile.value("CampActivities_gstInNum"));
*/		Thread.sleep(2000);
		safeSelect(driver, getObject("Camp_Activities_NewActivity_SelectCategory"), Category);
		//safeType(driver, getObject("Camp_Activities_NewActivity_ActivityName"), "");

		String ActivityName =  activityName +campLocal.value("releaseVersion");
		safeType(driver, getObject("Camp_Activities_NewActivity_ActivityName"), ActivityName);
		safeType(driver, getObject("Camp_Activities_NewActivity_OnelineDesc"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_OnelineDesc"), "Test");
		safeType(driver, getObject("Camp_Activities_NewActivity_DetailDesc"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_DetailDesc"), "Test Automation");

		if(domain.equals("com")) {
		safeType(driver, getObject("Camp_Activities_NewActivity_Address"), "");
		safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_Address"),
				By.id("1287e6a38da136a3cc480a0edafb387b3b205c66"), campLocal.value("campCity"));
		Thread.sleep(2000);

		safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_City"),
				By.xpath("//li/a[contains(.,'Bagalkot')]"), campLocal.value("campCityPartial"));
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,250)", "");
		safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "jpnagar");
		safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "");
		safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "560072");

		safeType(driver, getObject("Camp_Activities_NewActivity_Landmark"), "");
		safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_Landmark"),
				By.cssSelector("span.pac-item-query.ng-binding"), "Bagalkot, Karnataka, India");

		}else if (domain.equals("ae")) {
			safeType(driver, getObject("Camp_Activities_NewActivity_Address"), "");
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_Address"),
					By.xpath("//div/span[2]/span"),campLocal.value("AeCity"));
			Thread.sleep(2000);

			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_City"),
					By.xpath("//li/a[contains(.,'Dibba, United Arab Emirates')]"), campLocal.value("campAecityPartial"));
			Thread.sleep(2000);
			jse.executeScript("window.scrollBy(0,250)", "");
			safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "");
			safeType(driver, getObject("Camp_Activities_NewActivity_Locality"), "MG road");
			safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "");
			safeType(driver, getObject("Camp_Activities_NewActivity_Pincode"), "720056");

			safeType(driver, getObject("Camp_Activities_NewActivity_Landmark"), "");
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_Landmark"),
					By.cssSelector("span.pac-item-query.ng-binding"),campLocal.value("AeCity"));
		}
		if (MeetingPoint.equalsIgnoreCase("Yes")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_MeetingPoint_Yes"));
			safeType(driver, By.name("primary_support_number"), "9986508905");
			jse.executeScript("window.scrollBy(0,850)", "");
		} else if (MeetingPoint.equalsIgnoreCase("No")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_MeetingPoint_No"));
			Thread.sleep(1000);
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_MeetingAddress"),
					By.cssSelector("span.ng-binding.ng-scope"), "Test");
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_MeetingCity"),
					By.xpath("//body/ul[2]/li/a"), "Bang");
			safeType(driver, getObject("Camp_Activities_NewActivity_MeetingLocation"), "jpnagar");
			safeType(driver, getObject("Camp_Activities_NewActivity_MeetingPincode"), "560072");
			safeAutocomplete(driver, getObject("Camp_Activities_NewActivity_MeetingLandmark"),
					By.cssSelector("span.pac-item-query.ng-binding"), "JP Nagar");
			safeType(driver, getObject("Camp_Activities_NewActivity_Meeting_SupportNumber"), "9986508905");
		}
		if (PickUpDrop.equalsIgnoreCase("Yes")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_PickUp_Yes"));
			safeType(driver, getObject("Camp_Activities_NewActivity_PickUp_Place"), "JP Nagar");
			safeClick(driver, getObject("Camp_Activities_NewActivity_PickUp_Add_Btn"));
			elementPresent_Time(driver, By.cssSelector("li.ng-binding.ng-scope"), 5);
			assertTrue("PickUp Or Drop point not added",
					elementPresent(driver, By.cssSelector("li.ng-binding.ng-scope")));
			jse.executeScript("window.scrollBy(0,250)", "");
			// System.out.println("Pick Up Place Added");
		} else if (PickUpDrop.equalsIgnoreCase("No")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_PickUp_No"));
		}

		if (elementPresent_Time(driver, getObject("Camp_Activities_NewActivity_ShowFullFillNo"), 2)) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_ShowFullFillNo"));
		}

		safeClick(driver, getObject("Camp_Activities_NewActivity_FormOne_SaveBtnn"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		elementPresent_Time(driver, By.cssSelector("span.Drag-Drop"), 10);
		assertTrue("Create Activity Form One Is Not Successful",
				elementPresent(driver, getObject("Camp_Activities_NewActivity_FormTwo_SaveBtn")));
		Reporter.log("Create Activity Form One Is Successful",true);
		Thread.sleep(5000);

	}

	public String NewActivityFormThreeESP(String ActivityType, String privateShared,
			String Pricing, String KidsOnly) throws Exception {
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(10000);
		if (ActivityType.equalsIgnoreCase("ScheduledActivity")) {
			Thread.sleep(2000);
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_ScheduledActivity"));
			// safeSelect(driver, By.name("startingTime"), "01:00");
		} else if (ActivityType.equalsIgnoreCase("NotScheduledActivity")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_NotAScheduledActivity"));
			safeSelect(driver, By.name("weekday_from"), "06:00");
			safeSelect(driver, By.name("weekday_from_type"), "AM");
			safeSelect(driver, By.name("weekday_to"), "11:00");
			safeSelect(driver, By.name("weekday_to_type"), "PM");
			safeSelect(driver, By.name("weekend_from"), "06:00");
			safeSelect(driver, By.name("weekend_from_type"), "AM");
			safeSelect(driver, By.name("weekend_to"), "11:00");
			safeSelect(driver, By.name("weekend_to_type"), "PM");

		}

		if (privateShared.equalsIgnoreCase("shared")) {
			Thread.sleep(3000);
			safeClick(driver, By.id("ActivityTypeShared"));
			// elementPresent_Time(driver, By.xpath("//a[contains(., 'Add Variant')]"), 5);
			elementClickable(driver, By.linkText("Add Variant"), 5);
			// safeClick(driver, By.xpath("//a[contains(., 'Add Variant')]"));
			safeClick(driver, By.linkText("Add Variant"));
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			// System.out.println("Model window selected");
			safeType(driver, By.xpath("//input[@id='rate_name']"), "test automation");
			safeType(driver, By.xpath("//div[@ng-model='html']"), "test automation");
			Thread.sleep(500);
			safeSelect(driver, By.name("activity_duration_days"), "0");
			Thread.sleep(500);
			safeSelect(driver, By.xpath("(//select[@name='activity_duration_hours'])[2]"), "01");
			Thread.sleep(500);
			safeSelect(driver, By.name("activity_duration_mins"), "01");
			Thread.sleep(500);
			// add inclusion
			safeType(driver, By.xpath("//div[3]/div/input[@name='activity_addons']"), "Water");
			safeClick(driver, By.xpath("//div[3]/div[2]/a"));
			safeClick(driver, By.xpath("//button[@id='rateModalSave']"));
			driver.switchTo().defaultContent();

		} else if (privateShared.equalsIgnoreCase("private")) {
			safeClick(driver, By.id("ActivityTypePrivate"));
			// safeSelect(driver,
			// By.xpath("//form[@id='stepThreeForm']/div/dd[2]/div/div[2]/select"),"0");
			elementPresent_Time(driver, By.xpath("//a[contains(., 'Add Variant')]"), 5);
			safeClick(driver, By.xpath("//a[contains(., 'Add Variant')]"));
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			// System.out.println("Model window selected");
			safeType(driver, By.xpath("//input[@id='rate_name']"), "test automation");
			safeSelect(driver, By.name("activity_duration_days"), "0");
			Thread.sleep(500);
			safeSelect(driver, By.xpath("//ng-form/div/div[2]/div[4]/div[2]/div[2]/select"), "01");
			Thread.sleep(500);
			safeSelect(driver, By.name("activity_duration_mins"), "01");
			Thread.sleep(500);
			// add inclusion
			safeType(driver, By.xpath("//div[3]/div/input[@name='activity_addons']"), "Water");
			safeClick(driver, By.xpath("//div[3]/div[2]/a"));
			safeClick(driver, By.xpath("//button[@id='rateModalSave']"));
			driver.switchTo().defaultContent();

		}

		// activity operational hours
		// safeSelectByValue(driver, by, "01:00");
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,300)", "");
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_Pricing_ValidityFrom"));
		Thread.sleep(500);
		safeClick(driver, By.cssSelector("span.ui-icon.ui-icon-circle-triangle-e"));
		Thread.sleep(500);
		safeClick(driver, By.linkText("3"));
		safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_Pricing_ValidityTo"));
		Thread.sleep(500);
		safeClick(driver, By.cssSelector("span.ui-icon.ui-icon-circle-triangle-e"));
		Thread.sleep(500);
		safeClick(driver, By.linkText("9"));

		Thread.sleep(1000);

		//safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Days"));
		Thread.sleep(500);
		safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Days"), "1");

		//safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Hours"));
		Thread.sleep(500);
		safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Hours"), "01");

		//safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Mins"));
		Thread.sleep(500);
		safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_Booking_CutOffTime_Mins"), "15");
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(1000);
		if (ActivityType.equalsIgnoreCase("ScheduledActivity")) {
			safeSelect(driver, By.name("startingTime"), "01:00");
		}

		if (KidsOnly.equalsIgnoreCase("Yes")) {
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_KidsOnly"));
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_ChildMarketPrice"), "1");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_ChildCommission"), "5");
			if (elementPresent_Time(driver,
					getObject("Camp_Activities_NewActivity_FormThree_PPU_Max_capacity_InputFld"), 1)) {
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PPU_Max_capacity_InputFld"), "2");
				jse.executeScript("window.scrollBy(0,200)", "");
			}
			jse.executeScript("window.scrollBy(0,600)", "");
		} else if (KidsOnly.equalsIgnoreCase("")) {
			// System.out.println("Kids Not Selected");

		}

		if (Pricing.equalsIgnoreCase("Pricing Per Person")) {
			// System.out.println("Pricing per person info");
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson"));
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultMarketPrice"), "4");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultCommision"), "20");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_ChildMarketPrice"), "4");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_ChildCommission"), "20");
			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_MinPersonRequired_InputFld"));
			safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_MinPersonRequired_InputFld"), "1");
			if (elementPresent_Time(driver, getObject("Camp_Activities_NewActivity_FormThree_Max_capacity_InputFld"),
					1)) {
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_Max_capacity_InputFld"), "2");
			}
			jse.executeScript("window.scrollBy(0,600)", "");
		}
		// Pricing Per Unit
		else if (Pricing.equalsIgnoreCase("pricingTypeSlot")) {

			safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerUnit"));
			safeSelect(driver, getObject("Camp_Activities_NewActivity_FormThree_unitDropdown"), "bike");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_UnitMarketPrice"), "10");
			safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_UnitCommision"), "10");
			if (elementPresent_Time(driver,
					getObject("Camp_Activities_NewActivity_FormThree_PPU_Max_capacity_InputFld"), 1)) {
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PPU_Max_capacity_InputFld"), "2");
			}
			if (elementPresent_Time(driver,
					getObject("Camp_Activities_NewActivity_FormThree_PPU_MaxPersonRequired_InputFld"), 1)) {
				safeType(driver, getObject("Camp_Activities_NewActivity_FormThree_PPU_MaxPersonRequired_InputFld"),
						"5");
			}
			jse.executeScript("window.scrollBy(0,600)", "");
		}

		safeClick(driver, getObject("Camp_Activities_NewActivity_FormThree_SaveBtn"));
		waitElement(driver, By.cssSelector("div.success"), 6);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		assertTrue("Activity Not Created", elementPresent(driver, By.cssSelector("div.success")));
		Thread.sleep(10000);
		jse.executeScript("window.scrollBy(0,1500)", "");
		safeClick(driver, By.linkText("Save"));
		waitForPageLoading(2);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,3000)", "");
		waitForPageLoading(1);
		elementVisible(driver, getObjectLocals("CampSumbitforApproval"), 10);
		safeClick(driver, getObjectLocals("CampSumbitforApproval"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		waitForPageLoading(2);
		URL url = new URL(driver.getCurrentUrl());
		//System.out.println(url.getPath());
		StringBuilder sb = new StringBuilder(url.getPath());
		Reporter.log("Activity Id: "+ sb.substring(17,sb.length()-5),true);
		return sb.substring(17,sb.length()-5);
	}

	public void activitySAMApprove(RemoteWebDriver driver, String actId, String action) throws Exception {
		safeSelect(driver, getObjectLocals("CampStatusFilter"), "Pending Approval");
		Thread.sleep(2000);
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		Thread.sleep(3000);
		// List<WebElement> li=driver.findElements(By.xpath("//td/a"));
		safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		if (action == "Reject") {
			safeClick(driver, getObjectLocals("CampSAMReject"));
		} else if (action == "Approve") {
			safeClick(driver, getObjectLocals("CampSAMAccuracy"));
			driver.switchTo().alert().accept();
		}
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(6000);
	}

	public void selectAllActivities(RemoteWebDriver driver) throws Exception {
		waitElement(driver, By.linkText("Activity"), 15);
		if (elementClickable(driver, By.xpath("//a[contains(text(),'Activity')]"), 10)) {
			mouseHover(driver, By.xpath("//a[contains(text(),'Activity')]"));
			safeClick(driver, By.xpath("//a[text()='All Activities']"));
		}
	}

	public void reSubmitActivityESP(RemoteWebDriver driver, String actId) throws Exception {
		//safeSelect(driver, getObjectLocals("CampStatusFilter"), "Rejected");
		Thread.sleep(2000);
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		// List<WebElement> li=driver.findElements(By.xpath("//td/a"));
		waitForPageLoading(5);
		safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		waitForPageLoading(2);
		jse.executeScript("window.scrollBy(0,2000)", "");
		elementVisible(driver, getObjectLocals("CampSumbitforApproval"), 10);
		safeClick(driver, getObjectLocals("CampSumbitforApproval"));
		waitForPageLoading(2);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
	}

	public void activityMMApprove(RemoteWebDriver driver, String actId) throws Exception {
		waitForPageLoading(2);
		safeSelect(driver, getObjectLocals("CampStatusFilter"), "Accuracy Approved by SAM");
		waitForPageLoading(2);
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		waitForPageLoading(5);
		safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		waitForPageLoading(1);
		scrollToElement(driver,getObjectLocals("CampMMApproval"));
		safeClick(driver, getObjectLocals("CampMMApproval"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Reporter.log("Pricing approved by MM successfully",true);

	}

	public void allocateActivityToCM(RemoteWebDriver driver, String actId) throws Exception {
		safeSelect(driver, getObjectLocals("CampStatusFilter"), "Pending Allocation");
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		waitForPageLoading(3);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampScmAllocationCheckbox"), actId)));
		waitForPageLoading(1);
		scrollToElement(driver, getObjectLocals("CampScmAllocationDropdown"));
		safeSelect(driver, getObjectLocals("CampScmAllocationDropdown"),
				"Test CM Test CM(activitiescleartrip4@gmail.com)");
		safeClick(driver, getObjectLocals("CampScmAllocateButton"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Reporter.log("Activity allocated to CM from SCM Successfully",true);
	}

	public void allocateActivityToCE(RemoteWebDriver driver, String actId) throws Exception {
		safeSelect(driver, getObjectLocals("CampStatusFilter"), "Pending Allocation");
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		waitForPageLoading(5);
		if (isElementPresent(driver,
				By.xpath(getXpathByReplace(objectReposLocals.value("CampScmAllocationCheckbox"), actId))) == true) {
			safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampScmAllocationCheckbox"), actId)));
		} else {
			safeClick(driver, By.xpath("//*[@id='supplier-dashboard']//li[2]/a"));
			safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampScmAllocationCheckbox"), actId)));
		}
		waitForPageLoading(1);
		scrollToElement(driver, getObjectLocals("CampScmAllocationDropdown"));
		safeSelect(driver, getObjectLocals("CampScmAllocationDropdown"),
				"Test CE Test CE(activitiescleartrip1@gmail.com)");
		safeClick(driver, getObjectLocals("CampScmAllocateButton"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Reporter.log("Activity allocated to CE from CM Successfully",true);
	}

	public void contentApprovalfromCE(RemoteWebDriver driver, String actId) throws Exception {
		safeSelect(driver, getObjectLocals("CampStatusFilter"), "Pending Curation");
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		waitForPageLoading(1);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		if (isElementPresent(driver,
				By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId))) == true) {
			safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		} else {
			safeClick(driver, By.xpath("//*[@id='supplier-dashboard']//li[2]/a"));
			safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		}
		waitForPageLoading(1);
		safeClick(driver, By.xpath("//a[contains(text(),'Edit')]"));
		// scrollToElement(driver, getObject("Camp_Activities_InterestTag"));
		safeClick(driver, getObject("Camp_Activities_InterestTag"));
		waitForPageLoading(2);
		List<WebElement> it = driver.findElements(By.xpath("//input[@name='selectItemselectthemes']"));
		it.get(0).click();
		scrollToElement(driver, getObjectLocals("CampCEContentApproval"));
		safeClick(driver, getObjectLocals("CampCEContentApproval"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Reporter.log("Content approved from CE successfully",true);

	}

	public void contentApprovalfromCM(RemoteWebDriver driver, String actId) throws Exception {
		safeSelect(driver, getObjectLocals("CampStatusFilter"), "Curated By CE");
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		waitForPageLoading(1);
		safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		waitForPageLoading(1);
		scrollToElement(driver, getObjectLocals("CampApproveContent"));
		safeClick(driver, getObjectLocals("CampApproveContent"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Reporter.log("Content approved from CM successfully",true);
	}

	public void contentApprovalfromSCM(RemoteWebDriver driver, String actId) throws Exception {
		safeSelect(driver, getObjectLocals("CampStatusFilter"), "Content Approved by CM");
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		waitForPageLoading(1);
		safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		waitForPageLoading(1);
		scrollToElement(driver, getObjectLocals("CampApproveContent"));
		safeClick(driver, getObjectLocals("CampApproveContent"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		waitForPageLoading(2);
		scrollToElement(driver, getObjectLocals("CampPublish"));
		safeClick(driver, getObjectLocals("CampPublish"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Reporter.log("Content approved from SCM successfully",true);
	}

	public void unPublishfromSA(RemoteWebDriver driver, String actId) throws Exception {
		safeSelect(driver, getObjectLocals("CampStatusFilter"), "Published");
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		// List<WebElement> li=driver.findElements(By.xpath("//td/a"));
		waitForPageLoading(1);
		safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		waitForPageLoading(2);
		scrollToElement(driver, getObjectLocals("CampUnPublish"));
		safeClick(driver, getObjectLocals("CampUnPublish"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Reporter.log("Unpublished from SA successfully",true);
		waitForPageLoading(2);
	}

	public void publishfromSA(RemoteWebDriver driver, String actId) throws Exception {
		safeSelect(driver, getObjectLocals("CampStatusFilter"), "Un Published");
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		// List<WebElement> li=driver.findElements(By.xpath("//td/a"));
		waitForPageLoading(1);
		safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		waitForPageLoading(2);
		scrollToElement(driver, getObjectLocals("CampApproveContent"));
		safeClick(driver, getObjectLocals("CampApproveContent"));
		waitForPageLoading(2);
		scrollToElement(driver, getObjectLocals("CampPublish"));
		safeClick(driver, getObjectLocals("CampPublish"));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
	}

	public boolean homePageSearchAPI(String input, String cityName) {
		String requrl;
		boolean flag = false;

		List<HashMap<String, String>> resultList = null;

		requrl = common.value("murlLocals") + "/api/v1/" + "homePage?city=" + cityName;
		Response response = given().get(requrl);
		// System.out.println(response.toString());
	//	resultList = response.then().extract().path("templates");
		// System.out.println(resultList.toString());
		for (int itr = 0; itr < resultList.size(); itr++) {
			// System.out.println(resultList.get(itr).get("title"));
			if (resultList.get(itr).get("title").equals(input)) {
				Reporter.log("home page item" +input+ " found",true);
				flag = true;
				break;
			} else if (itr == resultList.size()) {
				Reporter.log("home page item" +input+ " not found",true);
			}

		}
		return flag;
	}

	public void removeHomePageContent(RemoteWebDriver driver, String product) throws Exception {

		safeClick(driver, By.linkText("Home Page Configuration"));
		if (product == "TTD") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
		} else if (product == "FNB") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"),By.xpath("//li/a[contains(.,'Bagalkot')]"),
					campLocal.value("campCityPartial"));
		} else if (product == "Events") {
			safeAutocomplete(driver, By.name("editorialConfiguration_city"),
					By.linkText(campLocal.value("campCityEvent")), campLocal.value("campCityEventPartial"));
		}

		waitForPageLoading(2);
		safeClick(driver, getObjectLocals("Camp_HomePageConfigCity_Fetch"));
		waitForPageLoading(3);
		List<WebElement> li = driver
				.findElements(By.xpath("//i[@ng-click='removeFromHomePageBlocks($index,obj,$event)']"));
		// System.out.println(li.size());
		for (int i = li.size(); i >= 1; i--) {
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].click();",li.get(i));
			safeClick(driver, By.xpath("//section[@id='editorialConfiguration_carousel']//li[" + i + "]//i"));
			// li.get(i).click();
			waitForPageLoading(1);
		}
		scrollToElement(driver, By.xpath("//section[@id='editorialConfiguration_carousel']//input[@value='Publish']"));
		waitForPageLoading(2);
		safeClick(driver, By.xpath("//section[@id='editorialConfiguration_carousel']//input[@value='Publish']"));
		safeClick(driver, getObjectLocals("Camp_HomePageConfig_PopUpPublish"));
		waitForPageLoading(2);
	}

	public void readPdfValues(HashMap<String, Float> pdfReportVal) throws Exception {
		ArrayList<String> reportVal = new ArrayList<>();
		String[] reportData;
		deleteFile("Invoice.pdf");
		safeClick(driver, getObjectLocals("Camp_Invoice"));
		Thread.sleep(3000);
		try (PDDocument document = PDDocument.load(new File(DOWNLOADPATH + "//Invoice.pdf"))) {

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				PDFTextStripper tStripper = new PDFTextStripper();
				String pdfFileInText = tStripper.getText(document);
				String lines[] = pdfFileInText.split("\\r?\\n");
				for (String line : lines) {
					// System.out.println(line);
					if (line.contains("Commision") || line.contains("PG Fees") || line.contains("CGST")
							|| line.contains("SGST") || line.contains("IGST") || line.contains("Exclusive of GST")
							|| line.contains("Inclusive of GST") || line.contains("Total GST/taxes")) {
						reportVal.add(line);
					}
				}

				for (String inp : reportVal) {
					if (inp.contains("Commision")) {
						reportData = inp.split("[a-zA-Z]+");
						pdfReportVal.put("Commission",
								pdfReportVal.get("Commission") + Float.parseFloat(reportData[1]));
					} else if (inp.contains("PG Fees")) {
						reportData = inp.split("[a-zA-Z]+");
						pdfReportVal.put("PG Fees", pdfReportVal.get("PG Fees") + Float.parseFloat(reportData[2]));
					} else if (inp.contains("CGST")) {

						pdfReportVal.put("CGST",
								pdfReportVal.get("CGST") + Float.parseFloat(inp.substring(10, inp.length())));
					} else if (inp.contains("SGST")) {
						pdfReportVal.put("SGST",
								pdfReportVal.get("SGST") + Float.parseFloat(inp.substring(10, inp.length())));
					} else if (inp.contains("IGST")) {
						pdfReportVal.put("IGST",
								pdfReportVal.get("IGST") + Float.parseFloat(inp.substring(11, inp.length())));
					} else if (inp.contains("Total GST/taxes")) {
						pdfReportVal.put("Total GST",
								pdfReportVal.get("Total GST") + Float.parseFloat(inp.substring(16, inp.length())));
					}

				}

			}

			pdfReportVal.put("Total Commission", pdfReportVal.get("Commission") + pdfReportVal.get("PG Fees"));
			pdfReportVal.put("Commission Invoice",
					pdfReportVal.get("Total Commission") + pdfReportVal.get("Total GST"));
			//System.out.println(pdfReportVal);
		} finally {
			deleteFile("Invoice.pdf");
		}
	}

	public void vGstReport(HashMap<String, Float> invoiceVal) {
		float in = 0;
		String input = null;
		try {
			deleteFile("Invoice.csv");
			safeType(driver, getObjectLocals("Camp_selectEsp"), campLocal.value("GstReport_EspName"));
			Thread.sleep(1000);
			safeClick(driver, getObject("CHMM_Rates_Hotel_Search_Ajax"));
			safeClick(driver, getObjectLocals("Camp_selectDate"));
			safeClick(driver, getObjectLocals("Camp_gstCurrentDate"));
			safeClick(driver, getObjectLocals("Camp_Report"));
			Thread.sleep(3000);

			for (Map.Entry<String, Float> value : invoiceVal.entrySet()) {

				Reader reader = Files.newBufferedReader(Paths.get(DOWNLOADPATH + File.separator + "Invoice.csv"));
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

				for (CSVRecord csvRecord : csvParser.getRecords()) {
					
					if((csvRecord.get("Booking Status").equals("Confirmed"))) {
						
					input = csvRecord.get(value.getKey());
					in += Float.parseFloat((input));
					}else {
					
						input = csvRecord.get(value.getKey());
						in -= Float.parseFloat((input));
					}
				}
				invoiceVal.put(value.getKey(), in);
				in = 0;
			}

			// System.out.println(invoiceVal);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			deleteFile("Invoice.csv");
		}

	}

	public void deleteFile(String path) {
		File file = new File(DOWNLOADPATH + File.separator + path);
		
		if (file.exists()) {
			file.delete();
		}
	}

	public void vGstReportInvoice(HashMap<String, Float> invoiceVal, HashMap<String, Float> pdfReportVal) {
		DecimalFormat df = new DecimalFormat("###.##");
		String digit;

		for (Map.Entry<String, Float> val : invoiceVal.entrySet()) {

			digit = df.format(val.getValue());
			
			/*  System.out.println(Float.parseFloat(digit));
			  System.out.println(df.format(pdfReportVal.get(val.getKey())));
			*/ 
			Assert.assertTrue(
					val.getKey() + " doesn't match with GST Report val " + Float.parseFloat(digit)
							+ " to GST Invoice val " + df.format(pdfReportVal.get(val.getKey())),
					Float.parseFloat(digit) == Float.parseFloat(df.format(pdfReportVal.get(val.getKey()))));
			Reporter.log(val.getKey() + " match with GST Report val " + Float.parseFloat(digit) + " to GST Invoice val "
					+ df.format(pdfReportVal.get(val.getKey())), true);

		}

	}

	public void vproductCardLocation(String type) throws Exception {
		int cnt = 0;
		Thread.sleep(3000);
		List<WebElement> ls = driver.findElements(getObjectLocals("Camp_homePageView"));
		if (ls.size() == 0) {
			Reporter.log("Home page view is blank", true);
		}

		for (WebElement we : ls) {
			cnt++;
			if (we.getText().contains(type)) {
				Reporter.log(type + " is found home page view,moving to available block", true);
				safeClick(driver, By.xpath("//li[" + cnt + "]/div/i"));
				Assert.assertTrue(type + " isnot present in available block", isElementPresent(driver,
						By.xpath(getXpathByReplace(objectReposLocals.value("Camp_availableBlock"), "Product Cards"))));
				Reporter.log(type + " moved to available block", true);
				break;
			}
		}

		

	}

	public void bookingReportVerification(String fileName, String chkBox) {
		try {
			int cnt, screenCnt;
			deleteFile(fileName);
			safeClick(driver, getObjectLocals("Booking_date_btn"));

			safeClick(driver,
					By.xpath(getXpathByReplace(objectReposLocals.value("Booking_date_selection"), "Last 7 Days")));

			if (chkBox.equals("direct")) {
				safeClick(driver, getObjectLocals("Camp_directBooking_chkbox"));
			} else if (chkBox.equals("wl")) {
				safeClick(driver, getObjectLocals("Camp_wlBooking_chkbox"));
			} else if (chkBox.equals("both")) {
				safeClick(driver, getObjectLocals("Camp_directBooking_chkbox"));
				safeClick(driver, getObjectLocals("Camp_wlBooking_chkbox"));
			}

			safeClick(driver,
					By.xpath(getXpathByReplace(objectReposLocals.value("Booking_date_selection"), " Submit ")));
			
			if(isElementPresent(driver,By.xpath("//h1"))) {
			
				driver.findElement(By.xpath("//h1")).getText().contains("our system is acting up");
				Reporter.log("System is not working,showing message system is acting up ",true);
				Assert.fail();
			}
			
			safeClick(driver,
					By.xpath(getXpathByReplace(objectReposLocals.value("Booking_date_selection"), " Download CSV ")));

			Thread.sleep(8000);
			Reader reader = Files.newBufferedReader(Paths.get(DOWNLOADPATH + fileName));
			CSVParser csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			String reportData = driver.findElement(By.xpath("//h4/span")).getText();

			cnt = csvParser.getRecords().size();
			screenCnt = Integer.parseInt(reportData.substring(16, reportData.length()));
			reader.close();
			Assert.assertTrue("In Camp Result of booking is found " + screenCnt + " and Csv record found are " + cnt,
					cnt == screenCnt);
			Reporter.log("In Camp Result of booking is found " + screenCnt + " and Csv record found are " + cnt, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void uploadCsvMarkUp(RemoteWebDriver driver, String agentId, String agentName, String subId,String env) throws Exception {

		//agentId = "2230";
		driver.get("https://"+env+".cleartrip.ae/camp/wl/config_b2b_share");
		File fo = new File(System.getProperty("user.dir") + "/downloads/ctActivityMarkUp.csv");
		PrintWriter pw = new PrintWriter(fo);
		StringBuilder sb = new StringBuilder();
		if (agentName.contains("SubAgent")) {
			sb.append("agent_id");
			sb.append(',');
			sb.append("subagent_id");
			sb.append(',');
			sb.append("activity_id");
			sb.append(',');
			sb.append("markup");
			sb.append("\n");
			sb.append(agentId);
			sb.append(',');
			sb.append(subId);
			sb.append(',');
			sb.append(campLocal.value("agentActivityId"));
			sb.append(',');
			sb.append(campLocal.value("agentMarkUp"));
			pw.write(sb.toString());
			pw.close();
			safeClick(driver,By.xpath("(//input[@id='config_b2b_share'])[2]"));
			WebElement element = driver.findElement(By.name("csv"));
			File f = new File("/downloads/ctActivityMarkUp.csv");
			Thread.sleep(5000);
			element.sendKeys(f.getAbsolutePath());
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			safeClick(driver, By.xpath("(//a[contains(.,'Upload')])[2]"));
		} else {
			sb.append("agent_id");
			sb.append(',');
			sb.append("activity_id");
			sb.append(',');
			sb.append("markup");
			sb.append("\n");
			sb.append(agentId);
			sb.append(',');
			sb.append(campLocal.value("agentActivityId"));
			sb.append(',');
			sb.append(campLocal.value("agentMarkUp"));
			pw.write(sb.toString());
			pw.close();
			
			driver.findElement(By.xpath("(//input[@id='config_b2b_share'])[1]")).sendKeys(fo.toString());
			safeClick(driver, By.xpath("(//a[contains(.,'Upload')])[1]"));
		}
			Assert.assertTrue("B2B Config didn't update",getText(driver, By.xpath("//div[@class='notice']")).contains("B2B Config Updated Successfully"));
			Reporter.log("B2B Config is updated succesfully", true);
		
		fo.delete();
	}

	public String wlAccountActivity(RemoteWebDriver driver, String name, String domain, String env) throws Exception {

		wait = new WebDriverWait(driver,500000);
		driver.get("http://"+env+".cleartrip." + domain + "/camp/accounts/sign_in");
		campActivities_SignIN(driver, "SA");
		driver.get("https://"+env+".cleartrip." + domain + "/camp/wl/listings");
		Thread.sleep(2000);
		Assert.assertTrue(name + " isn't present in WL account listing",isElementPresent(driver, By.xpath(getXpathByReplace("//a[.='nameLbl']", name))));
		Reporter.log(name + " is present in WL account listing", true);
		safeClick(driver, By.xpath(getXpathByReplace("//a[.='nameLbl']", name)));
		Thread.sleep(3000);
		safeClick(driver, By.xpath("//span/a[contains(text(),'Edit')]"));
		safeClick(driver,
				By.xpath(getXpathByReplace(objectReposLocals.value("camp_WlAccount_tabs"), "WL Site Configuration")));
		safeType(driver, By.id("url_slug"), name);
		selectFile(driver);
		Thread.sleep(2000);
		safeType(driver, By.name("header_color_picker"), "#6900ff");
		safeType(driver, By.name("font_color_picker"), "#00d9ff");
		scrollToElement(driver, By.name("isCTCobranded"));
		safeClick(driver,By.xpath("//input[@id='isCTCobranded']"));
		safeClick(driver, By.xpath("//button[text()='Submit']"));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(10000);
		scrollToElement(driver,By.xpath(
				getXpathByReplace(objectReposLocals.value("camp_WlAccount_tabs"), "Communication Configuration")));
		safeClick(driver, By.xpath(
				getXpathByReplace(objectReposLocals.value("camp_WlAccount_tabs"), "Communication Configuration")));
		safeType(driver, By.name("sender_email"), "test@cleartrip.com");
		safeType(driver, By.name("communication_email"), campLocal.value("bookingEmailId"));
		safeClick(driver, By.xpath("//button[text()='Submit']"));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(10000);
		safeClick(driver,
				By.xpath(getXpathByReplace(objectReposLocals.value("camp_WlAccount_tabs"), "Content Configuration")));
		safeType(driver, By.xpath("//input[contains(@ng-model,'city_name')]"), "Duba");
		Thread.sleep(500);
		safeClick(driver, getObject("CHMM_Rates_Hotel_Search_Ajax"));
		//org.testng.Assert.assertTrue(getText(driver, By.xpath("//ul[@class='cities list-unstyled']/li")).contains("Dubai"),"Unable to select Dubai city ");
		Reporter.log("City is selected to Dubai",true);
		safeClick(driver, By.id("allCities"));
		safeClick(driver, By.id("ttd"));
		safeClick(driver, By.id("fnb"));
		safeClick(driver, By.id("editorial"));
		safeClick(driver, By.xpath("//input[@id='is_book_flow_enabled']"));
		
		safeClick(driver, By.xpath("//button[text()='Submit']"));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(15000);
		safeClick(driver, By.xpath("//button[text()='Go Live']"));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(10000);
		URL url = new URL(driver.getCurrentUrl());
		StringBuilder sb = new StringBuilder(url.getQuery());
		//System.out.println(sb.substring(2,sb.length()));

		return sb.substring(3,sb.length());

	}

	public void approveAgent(RemoteWebDriver driver, String domain, String agentName, String agent,String env) throws Exception {
		wait = new WebDriverWait(driver,500000);
		driver.get("http://"+env+".cleartrip." + domain + "/camp/accounts/sign_in");
		campActivities_SignIN(driver, "SA");
		driver.get("http://"+env+".cleartrip." + domain + "/camp/accounts");
		Assert.assertTrue("fname record isn't present",isElementPresent(driver, By.xpath("//a[contains(.,'fname')]")));
		Reporter.log("fname record is present", true);
		Thread.sleep(1000);
		safeClick(driver, By.xpath("//a[contains(.,'fname')]"));
		safeClickList(driver, By.xpath("//select[@name='channel']/option"), "Organic Activation");
		safeClick(driver, By.id("is_wl_partner"));
		Assert.assertTrue("White label type drop down isn't present",isElementPresent(driver, By.xpath("//select[@ng-model='user.wl_type']")));
		Reporter.log("White label type drop down is present", true);
		if (agent.equals("")) {
			safeClickList(driver, By.xpath("//select[@ng-model='user.wl_type']/option"), "Agent");
		} else {
			safeClickList(driver, By.xpath("//select[@ng-model='user.wl_type']/option"), "Subagent");
			safeType(driver, By.xpath("//input[@ng-model='parent_agent_id']"), agent);
		}
		safeClick(driver, By.name("depositAccountConfigTypeNew"));
		safeType(driver, By.xpath("//input[@placeholder='Choose COP']"), "activitiescleartrip3@gmail.com");
		safeClick(driver, getObject("CHMM_Rates_Hotel_Search_Ajax"));
		safeClick(driver, By.xpath("//button[text()='Approve']"));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		Thread.sleep(10000);
		Assert.assertTrue("Account didn't approved",getText(driver, By.xpath("html/body/div[1]/div[1]/div")).contains("Account Approved"));
		Reporter.log("Account approved", true);
		campActivities_SignOut(driver);

	}

	public String createMail(RemoteWebDriver driver) throws Exception {

		driver.get("http://www.20minutemail.com/");
		safeClick(driver, By.xpath("//input[@value='Create mail']"));
		Thread.sleep(5000);
		return getText(driver, By.id("userTempMail"));
	}
	
	public void prodActivityApproveSA(RemoteWebDriver driver, String actId,String domain) throws Exception {

		campActivities_SignIN(driver, "SA");
		selectAllActivities(driver);
		if(domain.equals("com")) {
			safeAutocomplete(driver, By.xpath("//input[@placeholder='Location']"), By.xpath("//li[@class='ui-menu-item']"),
					campLocal.value("campCityPartial"));
			}else if (domain.equals("ae")) {
				safeAutocomplete(driver, By.xpath("//input[@placeholder='Location']"), By.xpath("//li[@class='ui-menu-item']"),
						campLocal.value("campAecityPartial"));
			}
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		scrollToElement(driver, By.linkText("Approve Content"));
		safeClick(driver, By.linkText("Approve Content"));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		assertTrue("Unable to save Activity Error:" + getText(driver, getObjectLocals("Camp_save_header")),
				getText(driver, getObjectLocals("Camp_save_header")).contains("Activity Updated Successfully"));

		Reporter.log("Activity approved", true);
		if (isElementPresent(driver, By.linkText("Approve Content")) == true) {
			scrollToElement(driver, By.linkText("Approve Content"));
			safeClick(driver, By.linkText("Approve Content"));
			Reporter.log("Activity approved", true);
			wait.until(ExpectedConditions
					.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		}
		Thread.sleep(15000);
		scrollToElement(driver, By.linkText("Publish"));
		safeClick(driver, By.linkText("Publish"));
		Thread.sleep(20000);
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		waitElement(driver, By.xpath("//div[@ng-hide='closeNotice']/div"), 5);
		String SuccessMsg = getText(driver, By.xpath("//div[@ng-hide='closeNotice']/div"));
		Reporter.log(SuccessMsg, true);
		Assert.assertTrue(SuccessMsg, SuccessMsg.contains("Activity Published"));
		URL url = new URL(driver.getCurrentUrl());
		// System.out.println(url.getPath());
		StringBuilder sb = new StringBuilder(url.getPath());
		Reporter.log("Activity Id: " + sb.substring(17, sb.length() - 5), true);
	}

	public void prodActivityApproveSCM(RemoteWebDriver driver, String actId,String interestTag,String domain) throws Exception {
		campActivities_SignIN(driver, "SCM");
		if(domain.equals("com")) {
		safeAutocomplete(driver, By.xpath("//input[@placeholder='Location']"), By.xpath("//li[@class='ui-menu-item']"),
				campLocal.value("campCityPartial"));
		}else if (domain.equals("ae")) {
			safeAutocomplete(driver, By.xpath("//input[@placeholder='Location']"), By.xpath("//li[@class='ui-menu-item']"),
					campLocal.value("campAecityPartial"));
		}
		safeClick(driver, getObjectLocals("CampSumbmitFilter"));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		safeClick(driver, By.xpath(getXpathByReplace(objectReposLocals.value("CampActivityId"), actId)));
		safeClick(driver, getObject("Camp_Activities_Edit_Button"));
		WebElement element = driver.findElement(By.xpath(getXpathByReplace(objectReposLocals.value("camp_Interest_tag"), interestTag)));
		jse.executeScript("arguments[0].click();",element);
		
		/*List<WebElement> it = driver.findElements(By.xpath("//input[@name='selectItemselectthemes']"));
		it.get(0).click();*/
		scrollToElement(driver, By.linkText("Approve Content"));
		safeClick(driver, By.linkText("Approve Content"));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		assertTrue("Unable to save Activity Error:" + getText(driver, getObjectLocals("Camp_save_header")),
				getText(driver, getObjectLocals("Camp_save_header")).contains("Activity Updated Successfully"));

		Reporter.log("Activity approved from SCM", true);
		campActivities_SignOut(driver);

	}
	
	public void vProdActivityLocal(RemoteWebDriver driver,String domain,String category) throws Exception {
		wait = new WebDriverWait(driver,50000);
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		if(domain.equals("com")) {
		System.out.println("https://www.cleartrip."+domain+"/local/"+campLocal.value("campCity").toLowerCase()+"/"+category+"-in-"+campLocal.value("campCity").toLowerCase());
		driver.get("https://www.cleartrip."+domain+"/local/"+campLocal.value("campCity").toLowerCase()+"/"+category+"-in-"+campLocal.value("campCity").toLowerCase());
		}else if (domain.equals("ae")) {
			System.out.println("https://www.cleartrip."+domain+"/local/"+campLocal.value("campAecityPartial").toLowerCase()+"/"+category+"-in-"+campLocal.value("campAecityPartial").toLowerCase());
			driver.get("https://www.cleartrip."+domain+"/local/"+campLocal.value("campAecityPartial").toLowerCase()+"/"+category+"-in-"+campLocal.value("campAecityPartial").toLowerCase());
		}
		if(category.contains("things")) {
		Assert.assertTrue("Collection: "+campLocal.value("prodCollection")+ "isn't showing in local",getText(driver,By.xpath("//div[@id='content']//h2")).contains(campLocal.value("prodCollection")));
		Reporter.log("Collection: "+campLocal.value("prodCollection")+ " is showing in local",true);
		}
		Thread.sleep(9000);
		safeClick(driver, By.xpath("//div[@id='content']//h2"));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//p[contains(text(),'Fetching details')]"))));
		Thread.sleep(9000);
		Assert.assertTrue("Activity isn't present",getText(driver, By.xpath("(//div[@id='content']//div[1]/a)[2]")).contains("prodAutomation"));
		Reporter.log("Activity is present in local");
		
		driver.close();
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(5000);
	}
	
}