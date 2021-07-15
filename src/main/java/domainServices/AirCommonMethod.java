/*// Framework - cleartrip Automation

// Version -Web Driver 2.0

// Creation Date - MAR, 2015

// Author - Mohamed Faisal

// Copyright © 2015 cleartrip Travel. All right reserved.

 */
package domainServices;



import static org.junit.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;








import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.text.DecimalFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;








import javax.json.Json;
import javax.json.JsonObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;



import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
//import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;

import org.xml.sax.InputSource;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;






import commonServices.APIUtils;



//import com.sun.xml.internal.ws.api.ha.StickyFeature;



import commonServices.WrapperMethod;
import org.apache.commons.io.IOUtils;
//import javafx.scene.web.WebView;



@SuppressWarnings("deprecation")

public class AirCommonMethod extends WrapperMethod {

	int b = 0;

	boolean p = false;

	boolean refreshflag = true;



	public static String connectorSearchUrl = new String();

	

	RemoteWebDriver driver;

	Map<String, String> insuranceDetails = new HashMap<String, String>();

	List<Integer> cardUsed = new ArrayList<Integer>();

	public String gds_airlines[] = new String[] { "Bangkok Airways", "Air India", "Kingfisher", "Vistara", "Air France",

			"Air Arabia", "British Airways", "Cathay Pacific", "Egypt Air", "Emirates", "Ethiopian Air",

			"Etihad Airways", "Gulf Air", "Kenya Airways", "Kuwait Airways", "Lufthansa", "Malaysia Airlines",

			"Multiple Carriers", "Myanmar Intl", "Oman Av", "Qantas Airways", "Qatar Airways", "Royal Jordanian", "SAS",

			"SilkAir", "Saudi Arabian Air", "Singapore Air", "South African", "SriLankan Airlines", "Swiss Intl Air",

			"Thai Airways", "Turkish Airlines", "Virgin Atlantic", "Air Asia", "All Nippon" };

	public String lcc_airlines[] = new String[] { "aerarann", "aerlingus", "air arabia", "air asia",

			"Air India Express", "air berlin", "air costa", "air italy", "Airasia_india", "air mediterranee", "air one",

			"air southwest", "bahrain air", "belle air", "belle air europe", "blue air", "blue islands", "blue1",

			"bluexpress", "bmibaby", "calm air", "cebu pacific", "city jet", "condor", "corendon", "direkt flyg",

			"eagles airlines", "eastern airways", "ezy", "finn comm", "firefly", "fly baboo", "fly dubai", "flydubai",

			"fly thomas cook", "flybe", "frontier airlines", "german wings", "goair", "helvetic", "iceland express",

			"indigo", "intersky", "Jazeera Airways", "jet2", "jetstar", "Jubba Airways", "kam air", "kulula",

			"Pakistan Intl Air", "malmo aviation", "mango", "manx2", "meridiana", "monarch airlines", "nas air",

			"nordkalottflyg", "norwegian", "ocean air", "onetime", "regional express", "ryan air", "scoot", "skippers",

			"sky west", "skyways", "SpiceJet", "strategic", "thomson fly", "transavia", "trawel fly", "virgin blue",

			"volawindjet", "vueling", "webjet", "west jet", "wizz air" };



	public int number_gds_Airlines = gds_airlines.length;

	public boolean GDS_Flight, B2B_GDS_Flight = false;



	public void alertCaptureAndFail(RemoteWebDriver driver) {

		try {

			org.openqa.selenium.Alert alert = driver.switchTo().alert();

			addLog("Alert detected: " + alert.getText());

			Thread.sleep(2000);

			alert.accept();

			assertTrue("Failure!", false);

		} catch (Exception e) {

		}

		// driver.navigate().refresh();

	}



	public void hotelCom_PaxEntry(RemoteWebDriver driver, String Rooms, String Adult1, String Adult2, String Adult3,

			String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2) throws Exception {

		safeSelect(driver, getObject("HotelCom_HomePage_Room_DropDown"), Rooms);

		String Adults[] = new String[] { Adult1, Adult2, Adult3, Adult4 };

		String Children[] = new String[] { Child1, Child2 };

		String ChildAge[] = new String[] { ChildAge1, ChildAge2 };

		int Hotel_Rooms = Integer.parseInt(Rooms);

		for (int i = 1; i <= Hotel_Rooms; i++) {

			String AdultID = "Adult_" + i;

			if (elementVisible(driver, By.id(AdultID), 1)) {

				safeSelect(driver, By.id(AdultID), Adults[i - 1]);

			}

		}

		Boolean No_Children = Child1.contains("0") || Child2.contains("0");

		if (!No_Children) {

			for (int i = 1; i <= Children.length; i++) {

				String ChildID = "Childs_" + i, Child_AgeID = "ca" + i;

				if (elementVisible(driver, By.id(ChildID), 1)) {

					safeSelect(driver, By.id(ChildID), Children[i - 1]);

					safeSelect(driver, By.name(Child_AgeID), ChildAge[i - 1]);

				}

			}

		}

	}



	public String calculateHash(String hashType, String input) {

		System.out.println("-----" + input);

		byte[] hashseq = input.getBytes();

		StringBuffer hexString = new StringBuffer();

		try {

			MessageDigest algorithm = MessageDigest.getInstance(hashType);

			algorithm.reset();

			algorithm.update(hashseq);

			byte[] messageDigest = algorithm.digest();



			for (int i = 0; i < messageDigest.length; i++) {

				String hex = Integer.toHexString(0xFF & messageDigest[i]);

				if (hex.length() == 1) {

					hexString.append("0");

				}

				hexString.append(hex);

			}



		} catch (NoSuchAlgorithmException e) {

			// logger.error(e);

		}



		return hexString.toString();

	}



	public boolean checkIfSignedIn(RemoteWebDriver driver) throws Exception {

		// if (waitElement(driver,

		// getObject("Acc_Dashboard_Link_Dropdown_Menu_User_Text"), 5)) {

		// if

		// (driver.findElement(getObject("Acc_Dashboard_Link_Dropdown_Menu_User_Text")).getText().equals("Your

		// trips"))

		// {

		if (waitElement(driver, By.xpath("//a[@id='userAccountLink']/span[2]"), 10)) {

			if (driver.findElement(By.xpath("//a[@id='userAccountLink']/span[2]")).getText().equals("Your trips")) {

				return false;

			}

			return true;

		} else {

			addLog("Home page not loaded", true);

			printInfo("Home page not loaded");

			//assertTrue("Failure! Home page not loaded", false);

		}

		return true;

	}



	public void deleteFile() throws IOException {

		String home = System.getProperty("user.home");

		File file = new File(home + "/Downloads");

		String[] myFiles;

		if (file.isDirectory()) {

			myFiles = file.list();

			for (int i = 0; i < myFiles.length; i++) {

				File myFile = new File(file, myFiles[i]);

				// System.out.println(myFile);

				if (myFile.getCanonicalPath().contains("air_booking")) {

					myFile.delete();

				}



			}

		}

	}



	public void misDownload(RemoteWebDriver driver, String option) throws Exception {

		HQ hq = new HQ();

		String domain = ".com";

		driver.get(baseUrl);

		waitForElement(driver, 7, getObject("Acc_User_Dropdown_Menu"));

		driver.get(getBaseUrl(domain) + "/hq");

		hq.signInHQ(driver);

		waitForElement(driver, 7, getObject("Acc_User_Dropdown_Menu"));

		driver.get("https://qa2.cleartrip.com/hq");

		addLog("Landed on HQ Dashboard", true);

		safeClick(driver, By.xpath("//ul[@class='dashboard'][3]/li[1]/a"));

		addLog("Clicked on 'Air MIS Reports'", true);

		if (option.equalsIgnoreCase("cancelled")) {

			Select options = new Select(driver.findElement(By.xpath(".//*[@id='filterType']")));

			options.selectByVisibleText("have been cancelled");

		}

		safeClick(driver, By.xpath("//input[@id='date_from']//../a"));

		// Anil - Fixing to generate report for last 1 day

		String sysDate = getCurrentDay();

		int foo = Integer.parseInt(sysDate);

		int foo1 = foo - 1;

		String misReportFromDate = String.valueOf(foo1);

		int foo2 = Integer.parseInt(sysDate);

		String misReportTillDate = String.valueOf(foo2);

		safeClick(driver, By.xpath("(.//*[@id='datePickerWrapper']//a[text()='" + misReportFromDate + "'])[1]"));

		safeClick(driver, By.xpath("//input[@id='date_to']//../a"));

		safeClick(driver, By.xpath("(.//*[@id='datePickerWrapper']//a[text()='" + misReportTillDate + "'])[1]"));

		safeClick(driver, By.xpath("//input[@type='submit']"));



		// Capture the processing ID

		String ProcessID = driver.findElement(By.xpath(".//*[@id='ContentFrame']//a[contains(text(),'Processing')]"))

				.getText();

		String PID = ProcessID.replaceAll("[^0-9]", "");

		addLog("Processing File ID : " + PID, true);



		safeClick(driver, By.xpath(".//*[@id='tips_tools']/ul/li[5]/a"));

		addLog("Clicked on 'Download custom MIS reports' link", true);

		waitForElement(driver, 7, By.xpath(".//td[text()='" + PID + "']"));



		boolean fileDownload = false;

		for (int i = 0; i < 36; i++) {

			if (driver.findElement(By.xpath("//tr/td[text()='" + PID + "']/following-sibling::td[6]")).getText()

					.contains("Processing")) {

				Thread.sleep(5000);

				driver.navigate().refresh();



			} else if (elementPresent_Time(driver, By.xpath("//tr/td[text()='" + PID + "']/following-sibling::td/a"),

					1)) {

				driver.findElement(By.xpath("//tr/td[text()='" + PID + "']/following-sibling::td/a")).click();

				fileDownload = true;

				break;

			}

		}

		Assert.assertTrue(fileDownload, "File still processing even after waiting for 3 min");



	}



	public void checkSizeofFile() throws IOException, InterruptedException {

		boolean check = false;

		Thread.sleep(2000);

		String home = System.getProperty("user.home");

		File file1 = new File(home + "/Downloads");

		String[] myFiles1;

		if (file1.isDirectory()) {

			myFiles1 = file1.list();

			for (int j = 0; j < myFiles1.length; j++) {

				File myFile1 = new File(file1, myFiles1[j]);

				System.out.println(myFile1);

				if (myFile1.getCanonicalPath().contains("air_booking")) {

					java.io.File file12 = new java.io.File(myFile1.getCanonicalPath());

					addLog("File size of report in bytes=" + file12.length(), true);

					if (file12.length() > 2886) {

						check = true;

					}

					Assert.assertEquals(check, true, "contents in file not found");

					break;

				}

			}

		}

	}



	public boolean checkIfAESignedIn(RemoteWebDriver driver) throws Exception {

		if (waitElement(driver, getObject("MEAirCom_HomePage_Dashboard_Link_YouTripsText"), 5)) {

			safeClick(driver, getObject("MEAirCom_HomePage_Dashboard_Link_YouTripsText"));

			if (elementPresent(driver, getObject("AirCom_HomePage_SignIN"), 5)) {

				safeClick(driver, getObject("MEAirCom_HomePage_Dashboard_Link_YouTripsText"));

				return false;

			}

			return true;

		} else {

			addLog("Home page not loaded", true);

			printInfo("Home page not loaded");

			assertTrue("Failure! Home page not loaded", false);

		}

		return true;

	}



	public boolean checkIfAESubDomainSignedIn(RemoteWebDriver driver) throws Exception {

		if (waitElement(driver, getObject("MEAirCom_HomePage_User_Link_YouTripsText_SubDomain"), 10)) {

			if (driver.findElement(getObject("MEAirCom_HomePage_User_Link_YouTripsText_SubDomain")).getText()

					.equals("Your trips")) {

				return false;

			}

			return true;

		} else {

			addLog("Home page not loaded", true);

			printInfo("Home page not loaded");

			assertTrue("Failure! Home page not loaded", false);

		}

		return true;

	}



	public boolean Accounts_checkIfSignedIn(RemoteWebDriver driver) throws Exception {

		if (driver.findElement(getObject("Account_Page_Dashboard_YourTripsTxt")).getText().equals("Your trips")) {

			return false;

		}

		return true;

	}



	public void airCom_HomepageSignIn(RemoteWebDriver driver, String domain) throws Exception {

		homepageSignIn(driver, domain, dataFile.value("AirUserId"), dataFile.value("AirPassword"));

	}



	public void airCom_HomepageSignInForHQScripts(RemoteWebDriver driver, String domain) throws Exception {

		homepageSignIn(driver, domain, dataFile.value("AirUserIdForHQScripts"),

				dataFile.value("AirPasswordForHQScripts"));

	}



	public void sffLogin(RemoteWebDriver driver, String domain) throws Exception {

		homepageSignIn(driver, domain, dataFile.value("SffLoginUsername"), dataFile.value("SffPassword"));
	}



	public void airCom_HomepageSignInForFreqFlyer(RemoteWebDriver driver, String domain) throws Exception {

		homepageSignIn(driver, domain, dataFile.value("AirUserIdForFreqFlyer"),

				dataFile.value("AirPasswordForFreqFlyer"));

	}



	public void airCom_HomepageSignInForHQScriptsRare(RemoteWebDriver driver, String domain) throws Exception {

		homepageSignIn(driver, domain, dataFile.value("AirUserIdForHQScriptsRareUse"),

				dataFile.value("AirPasswordForHQScriptsRareUse"));

	}



	public void airCom_HomepageSignInForHQScriptsRarer(RemoteWebDriver driver, String domain) throws Exception {

		homepageSignIn(driver, domain, dataFile.value("AirUserIdForHQScriptsRarerUse"),

				dataFile.value("AirPasswordForHQScriptsRarerUse"));

	}
	public void B2C_AccountsDeleteAndRegister(RemoteWebDriver driver, String domain) throws Exception {
		/*homepageSignIn(driver, domain, dataFile.value("AccountDeleteAndRegisterEmail"),

				dataFile.value("AccountDeleteAndRegisterPassword"));*/
		String Host = common.value("host");
		String mainWindow = driver.getWindowHandle();
		Thread.sleep(2000);
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 20);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"));
		if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 2)){
			safeClick(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"));
		}
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 20);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
		Thread.sleep(1000);
		driver.switchTo().frame("modal_window");	
		elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);

	safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("AccountDeleteAndRegisterEmail2"));
	safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("AccountDeleteAndRegisterPassword2"));			
	safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
	Thread.sleep(5000);
	driver.switchTo().window(mainWindow);
	//elementVisible(driver, getObjectHotels("B2C_Home_FlightsText"), 20);
	}
	
	
	public void Accounts_DeleteAndRegister(RemoteWebDriver driver, String domain) throws Exception {

		homepageSignIn(driver, domain, dataFile.value("AccountDeleteAndRegisterEmail2"),

				dataFile.value("AccountDeleteAndRegisterPassword2"));

	}

	

	

	public void airCom_HomepageSignInForWallet(RemoteWebDriver driver, String domain) throws Exception {

		homepageSignIn(driver, domain, dataFile.value("AirUserIdForWallet"), dataFile.value("AirPasswordForWallet"));

	}



	public void homepageSignIn(RemoteWebDriver driver, String domain, String username, String password)

			throws Exception {
      String browser = driver.getCapabilities().getBrowserName();

		//if (!checkIfSignedIn(driver)) {

			/*safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

			safeClick(driver, getObject("AirCom_HomePage_SignIN"));

			Thread.sleep(1000);

			driver.switchTo().frame("modal_window");

			// ID:email
*/
			if (!elementVisible(driver, getObject("AirCom_SignIn_ModalWindow_Email"), 5)) {

				driver.navigate().refresh();

				Thread.sleep(3000);

				safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

				safeClick(driver, getObject("AirCom_HomePage_SignIN"));

				driver.switchTo().frame("modal_window");

			}

			safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"), username);

			safeType(driver, getObject("AirCom_SignIn_ModalWindow_Password"), password);

			safeClick(driver, getObject("AirCom_SignIn_ModalWindow_SignIN_Button"));

			Thread.sleep(3000);

			driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

			if (isElementPresent(driver, By.id("close"))) {

//				driver.findElementById("close").click();

				driver.navigate().refresh();

			}

		 else if (driver.getCapabilities().getBrowserName().contains("internet")

				&& driver.getCapabilities().getVersion().equals("10")

				|| driver.getCapabilities().getVersion().equals("9")) {

			baseUrl = getBaseUrl(domain);

			driver.get(baseUrl + "/signin");

			safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"), username);

			safeType(driver, getObject("AirCom_SignIn_ModalWindow_Password"), password);

			safeClick(driver, getObject("AirCom_SignIn_ModalWindow_SignIN_Button"));

			Thread.sleep(5000);

			driver.get(baseUrl);

		} else if (driver.getCapabilities().getBrowserName().contains("internet")

				&& driver.getCapabilities().getVersion().equals("11")) {

			safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

			safeClick(driver, getObject("AirCom_HomePage_SignIN"));

			driver.switchTo().frame("modal_window");



			safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"), username);

			safeType(driver, getObject("AirCom_SignIn_ModalWindow_Password"), password);

			safeClick(driver, getObject("AirCom_SignIn_ModalWindow_SignIN_Button"));

			Thread.sleep(5000);

			driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

			if (isElementPresent(driver, By.id("close")))

				driver.findElementById("close").click();



		}

	}



	public void multiItineraryorctrouted(RemoteWebDriver driver) throws Exception {

		boolean warningFound = false;

		if (elementPresent(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"), 1)) {

			driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a")).click();

			driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a")).click();

		}

		warningFound = selectingStop("", driver, 0);



		// multiItinerary(driver);

		List<WebElement> we2 = driver.findElements(By.xpath("//li[contains(@class,'listItem')]"));

		List<WebElement> we = driver.findElements(By.xpath("//a[@linkidentifier='itinerary']"));

		test: for (int i = 0; i < we.size(); i++) {

			HashMap<String, String> hp = new HashMap<String, String>();



			System.out.println(

					"-----" + we2.get(i).findElement(By.xpath("./table/tbody[2]/tr[1]/th[1]/small[1]")).getText());

			if (we2.get(i).findElement(By.xpath("./table/tbody[2]/tr[1]/th[1]/small[1]")).getText()

					.equalsIgnoreCase("Multiple carriers")) {

				boolean lcc = false;

				boolean gds = false;

				boolean sj = false;

				boolean ig = false;

				we.get(i).click();



				// List<WebElement>

				// we1=driver.findElements(By.xpath("//span[@class='name']"));

				List<WebElement> we1 = driver.findElements(By.xpath("//*[@class='itinerary']/ul/li/div[2]/span"));

				// *[@class='itinerary']/ul/li/div[2]/span

				// System.out.println(we1.size());

				if (we1.size() >= 2) {

					for (int j = 0; j < we1.size(); j++) {



						Thread.sleep(1000);



						// else{

						if (we1.get(j).getText().equalsIgnoreCase("SpiceJet")) {

							sj = true;

						}

						if (we1.get(j).getText().equalsIgnoreCase("IndiGo")) {

							ig = true;

						}



						/*

						 * if(isLCCFlight(driver,we1.get(j).getText())){ lcc=true; }

						 * if(!isLCCFlight(driver,we1.get(j).getText())){ gds=true; }

						 */

						// if(ig&&sj){

						// if(lcc&&gds || ig&&sj){

						if (ig && sj) {

							we2.get(i).findElement(By.xpath("./table/tbody[2]/tr[2]/td[3]/button")).click();

							break test;

						}

						// }



					}



					we.get(i).click();

					Thread.sleep(2000);

				}

			}



		}

	}



	public void refundCalculationRequestQueueVerification(RemoteWebDriver driver, String tripId) throws Exception {

		driver.get("https://qa2.cleartrip.com/account/trips/" + tripId);

		safeClick(driver, By.xpath("//*[text()='Calculate Refund']"));

		/*

		 * List<WebElement> we=driver.findElements(By.xpath("//*[@type='checkbox']"));

		 * for(int i=0;i<we.size();i++) { we.get(i).click(); }

		 */

		safeClick(driver, By.xpath("//*[@type='checkbox']"));

		safeClick(driver, By.xpath("//*[text()='See Refund Amount']"));



		if (elementPresent(driver, By.xpath("//*[text()='Unknown']"), 5)) {

			driver.get("https://qa2.cleartrip.com/hq");

			safeClick(driver, By.xpath("//*[text()='Refund Calculation Request']"));



			safeType(driver, By.id("search_text"), tripId);



			safeClick(driver, By.xpath("//*[@value='Filter']"));

			Thread.sleep(2000);

			driver.navigate().refresh();

			WebElement element = (new WebDriverWait(driver, 30))

					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='compute refunds']")));

			// Thread.sleep(4000);

			safeClick(driver, By.xpath("//*[text()='compute refunds']"));

			System.out.println("------" + driver.findElement(By.xpath("//*[@id='grand_total']/strong")).getText());

			safeClick(driver, By.xpath("//*[@title='Click to open calendar']"));

			safeClick(driver, By.xpath("//td/a[text()='25']"));

			safeClick(driver, By.id("save"));

			driver.switchTo().alert().accept();

			driver.get("https://qa2.cleartrip.com/account/trips/" + tripId);

			driver.navigate().refresh();

			safeClick(driver, By.xpath("//*[text()='Calculate Refund']"));

			/*

			 * List<WebElement> we=driver.findElements(By.xpath("//*[@type='checkbox']"));

			 * for(int i=0;i<we.size();i++) { we.get(i).click(); }

			 */

			safeClick(driver, By.xpath("//*[@type='checkbox']"));

			safeClick(driver, By.xpath("//*[text()='See Refund Amount']"));

			WebElement element1 = (new WebDriverWait(driver, 30))

					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='refundValueAmount']")));

			List<WebElement> we = driver.findElements(By.xpath("//*[@class='refundValueAmount']"));

			System.out.println("refund amount=" + we.get(we.size() - 1).getText());

		}

	}



	@SuppressWarnings("unused")

	public void newVia(RemoteWebDriver driver) throws Exception {



		boolean warningFound = false;

		boolean routed = false;



		if (elementPresent(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a"))) {

			driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a")).click();

		}

		warningFound = selectingStop1("", driver, 1);



		Thread.sleep(2000);



		List<WebElement> we2 = driver.findElements(By.xpath("//li[contains(@class,'listItem')]"));

		int we2_size=we2.size();



		test: for (int i = 1; i <=we2_size ; i++) {



			driver.findElement(By.xpath("//li[contains(@class,'listItem')]["+i+"]")).click();



			List<WebElement> we1 = driver.findElements(By.xpath("//li[contains(@class,'listItem')]["+i+"]/div[contains(@class,'quickLook')]/ul/li/div[@class='itinerary']/ul/li/div[2]/small[1]"));

			if (we1.size() > 1) {

					Thread.sleep(2000);

					

					addLog("first flight number = " + we1.get(0).getAttribute("innerText"), true);

					addLog("Second flight number = " + we1.get(1).getAttribute("innerText"), true);



					if ((we1.get(0).getAttribute("innerText")).equalsIgnoreCase(we1.get(1).getAttribute("innerText"))) {

						driver.findElement(By.xpath("//li[contains(@class,'listItem')]["+i+"]/table/tbody[contains(@class,'segment')]/tr[2]/td[contains(@class,'price')]/button[contains(@class,'booking')]")).click();

						break test;

					}else {

						driver.findElement(By.xpath("//li[contains(@class,'listItem')]["+i+"]/table/tbody/tr/td[contains(@class,'tabs')]/ul/li/a[@linkidentifier='itinerary']")).click();

						Thread.sleep(2000);

					}

			}

		}
		for (int i = 0; i < 10; i++) {
			String curl=driver.getCurrentUrl();
			if (curl.toLowerCase().contains("itinerary")) {
				break;
				
			} else {
				Thread.sleep(3000);
			}
		}

	}



	@SuppressWarnings("unused")

	public void connectingFlight(RemoteWebDriver driver) throws Exception {

		boolean warningFound = false;

		boolean routed = false;

		smartClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a"));
		warningFound = selectingStop1("", driver, 1);

		Thread.sleep(2000);

		List<WebElement> we2 = driver.findElements(By.xpath("//li[contains(@class,'listItem')]"));

		int we2_size=we2.size();



		test: for (int i = 1; i <=we2_size ; i++) {



			driver.findElement(By.xpath("//li[contains(@class,'listItem')]["+i+"]")).click();



			List<WebElement> we1 = driver.findElements(By.xpath("//li[contains(@class,'listItem')]["+i+"]/div[contains(@class,'quickLook')]/ul/li/div[@class='itinerary']/ul/li/div[2]/small[1]"));

			if (we1.size() > 1) {

					Thread.sleep(2000);

					

					addLog("first flight number = " + we1.get(0).getAttribute("innerText"), true);

					addLog("Second flight number = " + we1.get(1).getAttribute("innerText"), true);



					if (!((we1.get(0).getAttribute("innerText")).equalsIgnoreCase(we1.get(1).getAttribute("innerText")))) {

//						safeClick(driver, new ByAll(By.xpath(

//								"//li/table/tbody/tr/th[contains(@class,'count2')]/ancestor::tr/following-sibling::tr/td[3]/button"),

//								By.xpath(

//										"//li/table/tbody/tr/th[contains(@class,'count')]/ancestor::tr/following-sibling::tr/td[3]/button")));

						driver.findElement(By.xpath("//li[contains(@class,'listItem')]["+i+"]/table/tbody[contains(@class,'segment')]/tr[2]/td[contains(@class,'price')]/button[contains(@class,'booking')]")).click();

						break test;

					}else {

						driver.findElement(By.xpath("//li[contains(@class,'listItem')]["+i+"]/table/tbody/tr/td[contains(@class,'tabs')]/ul/li/a[@linkidentifier='itinerary']")).click();

						Thread.sleep(2000);

					}

			}

		}
		
		for (int i = 0; i < 10; i++) {
			String curl=driver.getCurrentUrl();
			if (curl.toLowerCase().contains("itinerary")) {
				break;
				
			} else {
				Thread.sleep(3000);
			}
		}

	}



	public boolean multiItineraryorctrouted1(RemoteWebDriver driver) throws Exception {

		boolean warningFound = false;

		boolean routed = false;

		if (elementPresent(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"), 1)) {

			driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li")).click();

			driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a")).click();

		}

		warningFound = selectingStop1("", driver, 1);



		// multiItinerary(driver);

		List<WebElement> we2 = driver.findElements(By.xpath("//li[contains(@class,'listItem')]"));

		List<WebElement> we = driver.findElements(By.xpath("//a[@linkidentifier='itinerary']"));



		test: for (int i = 0; i < we.size(); i++) {

			HashMap<String, String> hp = new HashMap<String, String>();

			System.out.println("-----" + we2.get(i).findElement(By.xpath("./table/tbody/tr/th[1]/small[1]")).getText());

			if (we2.get(i).findElement(By.xpath("./table/tbody/tr/th[1]/small[1]")).getText()

					.equalsIgnoreCase("Multiple carriers")) {

				boolean lcc = false;

				boolean gds = false;

				boolean sj = false;

				boolean ig = false;

				we.get(i).click();



				// List<WebElement>

				// we1=driver.findElements(By.xpath("//span[@class='name']"));

				List<WebElement> we1 = we2.get(i)

						.findElements(By.xpath("./div//*[@class='itinerary']/ul/li/div[2]/span"));

				// *[@class='itinerary']/ul/li/div[2]/span

				// System.out.println(we1.size());

				if (we1.size() >= 2) {

					for (int j = 0; j < we1.size(); j++) {

						Thread.sleep(1000);



						if (we1.get(j).getText().equalsIgnoreCase("SpiceJet")) {

							sj = true;

						}

						if (we1.get(j).getText().equalsIgnoreCase("IndiGo")) {

							ig = true;

						}



						if (isLCCFlight(driver, we1.get(j).getText())) {

							lcc = true;

						}

						if (!isLCCFlight(driver, we1.get(j).getText())) {

							gds = true;

						}



						if ((ig && sj) || (lcc && gds)) {

							try {

								we2.get(i).findElement(By.xpath("./table/tbody[2]/tr[2]/td[3]/button")).click();

								routed = true;

							} catch (Exception e) {

								try {

									we2.get(i).findElement(By.xpath("./table/tbody/tr[2]/td[3]/button")).click();

								} catch (Exception E) {

									we2.get(i).findElement(By.xpath("./table/tbody/tr/td/button")).click();

								}

								routed = true;

							}

							break test;

						}

					}



					we.get(i).click();

					Thread.sleep(2000);

				}

			}



		}

		return routed;

	}



	public void payUsignIn(RemoteWebDriver driver, String username, String password) throws Exception {

		if (elementPresent(driver, getObject("PayU_LogOut_Button"))) {

			addLog("User Already Signed In PayU Site");

			printInfo("User Already Signed In PayU Site");

		} else if (!elementPresent(driver, getObject("PayU_LogOut_Button"))) {

			assertTrue("SignIn for PayU Not Displayed", elementPresent(driver, getObject("PayU_SignIn_EmailTxtFld")));

			safeType(driver, getObject("PayU_SignIn_EmailTxtFld"), "");

			safeType(driver, getObject("PayU_SignIn_EmailTxtFld"), username);

			safeType(driver, getObject("PayU_SignIn_PasswordTxtFld"), "");

			safeType(driver, getObject("PayU_SignIn_PasswordTxtFld"), password);

			safeClick(driver, getObject("PayU_SignIn_SubmitBtn"));

			waitElement(driver, getObject("PayU_LogOut_Button"), 10);

			assertTrue("SignInto PayU is Not Successful", elementPresent(driver, getObject("PayU_LogOut_Button")));

		}



	}



	public void b2cSigninExpressWay(RemoteWebDriver driver) throws Exception {

		// String mainWindow = driver.getWindowHandle();

		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

		safeClick(driver, getObject("AirCom_HomePage_SignIN"));

		driver.switchTo().frame("modal_window");

		safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"), "automationexpresswaystored@gmail.com");

		safeType(driver, getObject("AirCom_SignIn_ModalWindow_Password"), "cltppassword");

		safeClick(driver, getObject("AirCom_SignIn_ModalWindow_SignIN_Button"));

		Thread.sleep(5000);



		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

		driver.get(baseUrl);

		checkIfSignedIn(driver);



	}



	public void airCom_HomepageRegisterWithHQRareId(RemoteWebDriver driver, String user, String pass) throws Exception {

		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

		safeClick(driver, getObject("AirCom_HomePage_Register"));

		Thread.sleep(1000);

		driver.switchTo().frame("modal_window");



		if (waitElement(driver, By.id("username1"), 13)) {

			safeType(driver, By.id("username1"), user);

			safeClick(driver, By.id("registerButton"));

			Thread.sleep(5000);



			if (waitElement(driver, By.id("password"), 13)) {

				safeType(driver, By.id("password"), pass);

				safeSelect(driver, By.id("profile_title"), "Mr");



				safeType(driver, By.xpath("//*[@id='newRegForm']/dl/dd[5]/input[1]"), "Test");

				safeType(driver, By.xpath("//*[@id='newRegForm']/dl/dd[5]/input[2]"), "Test");

				//safeType(driver, By.id("mobile_number"), "9123456789");

				safeType(driver, By.id("mobile_number"), getRandomString(10, Mode.NUMERIC));

				safeClick(driver, By.id("signUpButton"));

				Thread.sleep(2000);
				driver.navigate().refresh();

				if (!waitElement(driver, By.xpath("//*[@id='trips']/div/h1"), 15)) {

					System.out.println("Account page not loading. Error!");

					addLog("Account page not loading. Error!");

					assertTrue("Error!", false);

				} else {

					assertTrue("Account page not loading. Error!", driver.findElementByXPath("//*[@id='trips']/div/h1")

							.getText().contains("Trips you've booked"));

				}

			} else {

				System.out.println("Signup popup not loading. Error!");

				addLog("Signup popup not loading. Error!");

				assertTrue("Error!", false);

			}

		} else {

			System.out.println("Registration popup not loading. Error!");

			addLog("Registration popup not loading. Error!");

			assertTrue("Error!", false);

		}

	}



	public void b2cStoredCard(RemoteWebDriver driver) throws Exception {

		// String mainWindow = driver.getWindowHandle();

		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

		safeClick(driver, getObject("AirCom_HomePage_SignIN"));

		driver.switchTo().frame("modal_window");

		safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"), "storedcards@gmail.com");

		safeType(driver, getObject("AirCom_SignIn_ModalWindow_Password"), "cltp1234");

		safeClick(driver, getObject("AirCom_SignIn_ModalWindow_SignIN_Button"));

		Thread.sleep(5000);



		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

		driver.get(baseUrl);

		checkIfSignedIn(driver);



	}



	public void MigsStoredCard(RemoteWebDriver driver) throws Exception {

		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

		safeClick(driver, getObject("AirCom_HomePage_SignIN"));

		driver.switchTo().frame("modal_window");

		safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"), "migs_stored@gmail.com");

		safeType(driver, getObject("AirCom_SignIn_ModalWindow_Password"), "cltp1234");



		safeClick(driver, getObject("AirCom_SignIn_ModalWindow_SignIN_Button"));

		Thread.sleep(5000);



		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

		driver.get(baseUrl);

	}



	public void CTWallet(RemoteWebDriver driver) throws Exception {

		// String mainWindow = driver.getWindowHandle();

		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

		safeClick(driver, getObject("AirCom_HomePage_SignIN"));

		driver.switchTo().frame("modal_window");

		safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"), "full_wallet@cleartrip.com");

		safeType(driver, getObject("AirCom_SignIn_ModalWindow_Password"), "itsme123");

		safeClick(driver, getObject("AirCom_SignIn_ModalWindow_SignIN_Button"));

		Thread.sleep(5000);



		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

		driver.get(baseUrl);

		checkIfSignedIn(driver);



	}



	public void CTWallet1(RemoteWebDriver driver, String wallettype) throws Exception {

		// String mainWindow = driver.getWindowHandle();

		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

		safeClick(driver, getObject("AirCom_HomePage_SignIN"));

		driver.switchTo().frame("modal_window");

		if (wallettype.equalsIgnoreCase("partial")) {

			safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"), "partialwallet@cleartrip.com");

		} else if (wallettype.equalsIgnoreCase("wallet")) {

			safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"), "full_wallet@cleartrip.com");

		} else if (wallettype.equalsIgnoreCase("flexi")) {

			safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"), "flexiwallet@gmail.com");

		}

		safeType(driver, getObject("AirCom_SignIn_ModalWindow_Password"), "itsme123");

		safeClick(driver, getObject("AirCom_SignIn_ModalWindow_SignIN_Button"));

		Thread.sleep(5000);



		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

		driver.get(baseUrl);

		checkIfSignedIn(driver);



	}



	public void moneyToPratialWallet(String event, String transactionType, String amount) throws Exception {

		DefaultHttpClient clientSearch = null;

		addLog(event + " " + transactionType + " " + amount + " Money to wallet.", true);

		clientSearch = new DefaultHttpClient();

		// String

		// hash=calculateHash("SHA-256","5152532|Bangalore-chennai|Q1612160133|PROMOTION|CREDIT|10|03-DEC-2019|2483|cl3@rTri9");

		String hash = calculateHash("SHA-256", "5152632|Bangalore-chennai|Q1612160133|" + event + "|" + transactionType

				+ "|" + amount + "|03-DEC-2019|2483|cl3@rTri9");

		System.out.println("--" + hash);



		String postString = "{   \"trip-details\": \"Bangalore-chennai\",   \"trip-ref\": \"Q1612160133\",   \"event\": \""

				+ event + "\",   \"txn-type\": \"" + transactionType + "\",   \"amount\": " + amount

				+ ",   \"expiry-date\": \"03-DEC-2019\",   \"caller-id\": \"2483\" }";

		HttpPost WalletCall = new HttpPost(

				new URI("http://172.17.12.145:9080/payment/service/ctwallets/5152632/transactions"));

		System.out.println(postString);

		StringEntity params = new StringEntity(postString);

		WalletCall.setEntity(params);



		WalletCall.setHeader("Content-Type", "application/json");

		WalletCall.setHeader("checksum", hash);

		// itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");

		HttpResponse itinenaryResponse = clientSearch.execute(WalletCall);

		HttpEntity entityIti = itinenaryResponse.getEntity();



		Document docIti = null;

		BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));

		String str12 = "";

		StringBuffer sb12 = new StringBuffer();

		while ((str12 = br12.readLine()) != null) {

			sb12.append(str12);



		}

	}



	public JSONObject giftVoucherCreation(int amount) throws Exception {

		DefaultHttpClient clientSearch = null;

		clientSearch = new DefaultHttpClient();

		String hash = calculateHash("SHA-256", "INR|" + amount + "|ravikumar.valmiki@cleartrip.com|1|cleartrip");



		System.out.println("--" + hash);



		String postString = "{ \"currency\":\"INR\", \"amount\":" + amount

				+ ", \"userEmail\":\"ravikumar.valmiki@cleartrip.com\", \"paymentId\":1 }";

		HttpPost itinenaryCall = new HttpPost(new URI("http://172.17.13.109:9080/payment/gv/create"));

		System.out.println(postString);

		StringEntity params = new StringEntity(postString);

		itinenaryCall.setEntity(params);



		itinenaryCall.setHeader("Content-Type", "application/json");

		itinenaryCall.setHeader("checksum", hash);

		/*

		 * itinenaryCall.setHeader("ct-auth",

		 * "5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8"

		 * );

		 */

		HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);

		HttpEntity entityIti = itinenaryResponse.getEntity();



		BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));

		String str12 = "";

		StringBuffer sb12 = new StringBuffer();

		while ((str12 = br12.readLine()) != null) {

			sb12.append(str12);

		}



		JSONObject gv = new JSONObject(sb12.toString());

		// System.out.println("testtttt" + EntityUtils.toString(entityIti,

		// "UTF-8"));



		/*

		 * String gvnumber=gv.get("gvPin").toString(); String

		 * gvnumber1=gv.get("gvNumber").toString();

		 */



		clientSearch.close();

		return gv;

	}



	//Yashmin
	
	public void airCom_HomepageSearch_Oneway1(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,
			String Adults, String Childrens, String Infants, String pref_airline, String airlineCode , int attempt) throws Exception {
		Thread.sleep(5000);

		elementClickable(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), 10);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),
				getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),
				ToCity);
		if (attempt == 0) {
			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),
					getObject("AirCom_HomePage_Departure_NextMonth"), 1, From_Date);
		} else {
			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),
					getObject("AirCom_HomePage_Departure_NextMonth"), 4, From_Date);
		}

		Thread.sleep(2000);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);
		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);
		if (pref_airline != "" || pref_airline == null) {
			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {
				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));
			}
			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),

					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);
		}
		scrollToElement(driver, getObject("AirCom_HomePage_Search_Button"));
		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));
	}

	

	
	
	public void SRPCurrencyCheck(RemoteWebDriver driver) throws Exception
	{ 
	List<WebElement> list=	driver.findElements(By.xpath("//th[@class='price']/span[@class='PHP currencyCode']"));
	if(list.size() > 0){
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());
			break;
		}
		Reporter.log("Book Prices are in PHP currency.", true);
		}else{
			Reporter.log("Book Prices are NOT in PHP currency.", true);
		}
	if(elementPresent(driver,By.xpath("//p[@class='displayFilterValue clearFix']/span/span[@class='PHP currencyCode']")))
	{System.out.println(driver.findElement(By.xpath("//p[@class='displayFilterValue clearFix']/span/span[@class='PHP currencyCode']")).getText());
		Reporter.log("Upto Ranges  Prices are in PHP currency.", true);
	}else{
		Reporter.log("Upto Ranges Prices are NOT in PHP currency.", true);
	}
	
	if(elementPresent(driver,By.xpath("//p[@class='displayStaticRange clearFix']/span[@class='fLeft']")))
	{ System.out.println(driver.findElement(By.xpath("//p[@class='displayStaticRange clearFix']/span[@class='fLeft']")).getText());
		Reporter.log("Left    Prices ranges  are in PHP currency.", true);
	}
	else{
		Reporter.log("Upto Ranges Prices are NOT in PHP currency.", true);
	}
	if(elementPresent(driver,By.xpath("//p[@class='displayStaticRange clearFix']/span[@class='fRight']")))
	{
		System.out.println(driver.findElement(By.xpath("//p[@class='displayStaticRange clearFix']/span[@class='fRight']")).getText());
		Reporter.log("Right    Prices ranges  are in PHP currency.", true);
	}
	else
	{
		Reporter.log("Right    Prices ranges  are Not in PHP currency.", true);
	}
	List<WebElement> list1=driver.findElements(By.xpath("//small[contains(@class,'fRight')]/span[@class='PHP currencyCode']"));
	if(list1.size()>0)
	{
		for (int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i).getText());
			break;
		}
		Reporter.log("Airlines prices are displayed in PHP",true);
	}
	else
	{
		Reporter.log("Airlines prices are NOt displayed in PHP",true);
	}
	
	/*if(getAttribute(driver, (By.xpath("//td[@class='bestPrice searchedOn']/a")), "original-title").contains("PHP currencyCode")){
		System.out.println("Calendar price in PHP : "  + getAttribute(driver, (By.xpath("//td[@class='bestPrice searchedOn']/a")), "original-title"));
		
	}*/
	}
		
	/*Yashmin*/
	public void ItineraryPriceCheck(RemoteWebDriver driver) throws Exception
	{
		/*String Totalfare=driver.findElement(By.xpath("RemoteWebDriver driver")).getText();
		if(elementPresent(driver ,By.xpath("//strong[@id='totalFare']/span[1]")))
		{System.out.println("Total fare in PHP " + Totalfare);}
		else {System.out.println("Total fare Not  in PHP " + Totalfare);}
		driver.findElement(By.xpath("//small/a[@class='weak fareDetailsLink']")).click();*/
		String totalfare =driver.findElement(By.xpath("//dd[@class='row clearFix']")).getText();
		//assertEquals("PHP||totalfare",driver.findElement(By.xpath("//dd[@class='row clearFix']")).getText());
		System.out.println("Total Fare is in PHP " + totalfare.contains("PHP"));
		Reporter.log("PHP Currency on ITINERAY - Verifed", true);
		
	}
	
	public void airCom_HomepageSearch_Oneway(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline, int attempt) throws Exception {

		Thread.sleep(5000);



		elementClickable(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), 10);



		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);



		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);

		if (attempt == 0) {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 1, From_Date);

		} else {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 4, From_Date);

		}



		Thread.sleep(2000);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "" || pref_airline == null) {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),



					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}

		scrollToElement(driver, getObject("AirCom_HomePage_Search_Button"));

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airCom_HomepageSearch_Oneway_With_ConnectorSearch(RemoteWebDriver driver, String FromCity,

			String ToCity, String From_Date, String Adults, String Childrens, String Infants, String pref_airline,

			int attempt) throws Exception {

		airCom_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, Adults, Childrens, Infants, pref_airline,

				attempt);

		String searchUrl = driver.getCurrentUrl();

		connectorSearch(driver, searchUrl);

	}



	public void airCom_HomepageSearch_Oneway_Pricelock(RemoteWebDriver driver, String FromCity, String ToCity,

			String Adults, String Childrens, String Infants, String pref_airline, int attempt) throws Exception {

		// Thread.sleep(5000);



		elementClickable(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), 10);



		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);



		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);

		/*

		 * if (attempt == 0) { selectCalendarDate(driver,

		 * By.id(getValue("AirCom_HomePage_Departure_Cal")),

		 * getObject("AirCom_HomePage_Departure_NextMonth"),1, From_Date); } else {

		 * selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

		 * getObject("AirCom_HomePage_Departure_NextMonth"), 0, From_Date); }

		 */

		driver.findElement(By.id("DepartDate")).click();

		driver.findElement(By.xpath("(//td[@data-handler='selectDay'])[8]")).click();



		Thread.sleep(2000);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "" || pref_airline == null) {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),



					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airCom_HomepageSearch_RoundTrip_Pricelock(RemoteWebDriver driver, String FromCity, String ToCity,

			String Adults, String Childrens, String Infants, String pref_airline, int attempt) throws Exception {

		Thread.sleep(5000);

		safeClick(driver, getObject("AirCom_HomePage_Roundtrip_RadioButton"));

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);



		driver.findElement(By.id("DepartDate")).click();

		driver.findElement(By.xpath("(//td[@data-handler='selectDay'])[8]")).click();

		driver.findElement(By.id("ReturnDate")).click();

		driver.findElement(By.xpath("(//td[@data-handler='selectDay'])[3]")).click();



		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "") {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),

					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airCom_HomepageCitiesSearch_Oneway(RemoteWebDriver driver, String FromCity, String ToCity,

			String From_Date, String Adults, String Childrens, String Infants, String pref_airline, int attempt)

			throws Exception {

		boolean citiesfound = false;

		// Thread.sleep(5000);



		elementClickable(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), 10);



		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);



		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);

		String textfetched = driver.findElement(By.id("ToTag")).getAttribute("value");

		if (textfetched.contains("All airports")) {

			citiesfound = true;

		}

		System.out.println(citiesfound);

		Assert.assertEquals(true, citiesfound, "All airports not found");

		// System.out.println("-----"+driver.findElement(By.id("ToTag")).getAttribute("value"));

		if (attempt == 0) {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 1, From_Date);

		} else {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 0, From_Date);

		}



		Thread.sleep(2000);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "" || pref_airline == null) {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),



					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airCom_HomepageSearch_Oneway2(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline, int attempt) throws Exception {

		// Thread.sleep(5000);

		int month;

		int onwardmonth;

		boolean ep = false;



		if (elementClickable(driver, By.id(getValue("AirCom_HomePage_From_TextBox")), 10)) {

			safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

					getObject("AirCom_HomePage_From_Ajax"), FromCity);

			safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")),

					getObject("AirCom_HomePage_To_Ajax"), ToCity);

		}



		String onwarddate = getModifiedDate(driver, From_Date);

		// System.out.println(getModifiedDate(driver,From_Date));

		// System.out.println(getModifiedDate(driver,common.value("Days_to_add_for_CurrentDate")).split("-")[0]);

		int month1 = Integer.parseInt(onwarddate.split("-")[1]);



		month = month1 - 1;



		System.out.println(month);

		int day1 = Integer.parseInt(onwarddate.split("-")[2]);

		// System.out.println("---"+day1);

		int day = day1;

		do {

			try {

				driver.findElement(By.xpath("//*[contains(@data-year,'" + onwarddate.split("-")[0]

						+ "') and contains(@data-month,'" + month + "')]"));

				ep = true;

			} catch (Exception e) {



				ep = false;

			}

			if (!ep) {

				safeClick(driver, By.xpath("//div[@id='ui-datepicker-div']/div[2]/div/a"));

			}

		} while (!ep);

		// System.out.println("++"+driver.findElement(By.xpath("//*[contains(@data-year,'"+onwarddate.split("-")[0]+"')

		// and contains(@data-month,'"+month+"')]")).);

		/*

		 * if(driver.findElement(By.xpath("//*[contains(@data-year,'"+onwarddate. split

		 * ("-")[0]+"') and contains(@data-month,'"+month+"')]")).isDisplayed ()){

		 *

		 * }

		 */

		// System.out.println("//*[contains(@data-year,'"+onwarddate.split("-")[0]+"')

		// and contains(@data-month,'"+month+"')]");

		List<WebElement> we = driver.findElements(By.xpath("//*[contains(@data-year,'" + onwarddate.split("-")[0]

				+ "') and contains(@data-month,'" + month + "')]"));

		test: for (int i = 0; i < we.size(); i++) {

			// System.out.println(we.get(i).findElement(By.xpath("./a")).getText());

			// System.out.println(onwarddate.split("-")[2]);

			int x = Integer.parseInt(we.get(i).findElement(By.xpath("./a")).getText());

			int y = Integer.parseInt(onwarddate.split("-")[2]);

			if (x == y) {

				we.get(i).click();

				break test;

			}

			/*

			 * if(we.get(i).findElement(By.xpath("./a")).getText().equalsIgnoreCase

			 * (onwarddate.split("-")[2])){ we.get(i).click(); break test; }

			 */



		}



		/*

		 * selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

		 * getObject("AirCom_HomePage_Departure_NextMonth"),1, From_Date);

		 */



		Thread.sleep(2000);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "" || pref_airline == null) {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),



					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public String calenderSearch(RemoteWebDriver driver, String date, String url1) throws Exception {

		Calendar c = Calendar.getInstance();

		// System.out.println("input date="+date1);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");



		// Adding 6 days to past search

		int date1 = Integer.parseInt(date) + 6;

		// System.out.println(date1);

		c.add(Calendar.DATE, date1);



		String convertedDate = dateFormat.format(c.getTime());

		// System.out.println(convertedDate);



		List<WebElement> we = driver.findElements(By.xpath("//tbody[@class='calRender']/tr"));

		test: for (int i = 0; i < we.size(); i++) {

			// System.out.println("row id="+we.get(i).getAttribute("id"));



			// System.out.println(""+convertedDate.split("-")[1]);

			// System.out.println(url1.split("/")[1]);

			if (!convertedDate.split("-")[1].equalsIgnoreCase(url1.split("/")[1])) {



				safeClick(driver, By.xpath("//nav[@class='fRight']/ul/li[2]"));

				Thread.sleep(1200);

				List<WebElement> we2 = driver.findElements(By.xpath("//tbody[@class='calRender']/tr"));

				test1: for (int k = 0; k < we2.size(); k++) {

					List<WebElement> we1 = we2.get(k).findElements(By.xpath("./td"));

					for (int j = 0; j < we1.size(); j++) {

						// System.out.println("++++++++++"+we1.get(j).getAttribute("data-searchdate").split("-")[0]);

						// System.out.println("-------"+convertedDate.replace("-","/"));

						if (we1.get(j).getAttribute("data-searchdate").split("-")[0]

								.equalsIgnoreCase(convertedDate.replace("-", "/"))) {

							we1.get(j).click();

							break test;

						}

					}

				}

			}



			else {

				List<WebElement> we1 = we.get(i).findElements(By.xpath("./td"));

				for (int j = 0; j < we1.size(); j++) {

					// System.out.println("**************"+we1.get(j).getAttribute("data-searchdate"));

					/*

					 * if(we1.get(j).getAttribute("data-searchdate")!=null){ System

					 * .out.println(we1.get(j).getAttribute("data-searchdate" ).split("-")[0]);

					 * System.out.println(convertedDate.replace("-","/"));

					 */

					// System.out.println(we1.get(j).getAttribute("data-searchdate").split("-")[0]);

					// System.out.println(convertedDate.replace("-","/"));

					if (we1.get(j).getAttribute("data-searchdate").split("-")[0]

							.equalsIgnoreCase(convertedDate.replace("-", "/"))) {

						we1.get(j).click();

						break test;

					}

				}



			}

			System.out.println(driver.getCurrentUrl());

		}

		return convertedDate;

	}



	public String air_SrpUrl(RemoteWebDriver driver, String Domain, String FromCity, String ToCity, String Adults,

			String Childrens, String Infants, String SearchType, String PrefAirline, int FromDate, int ToDate)

			throws Exception {

		baseUrl = getBaseUrl(Domain);

		// driver.get(baseUrl);

		String SRP_URL = null;

		String Check_In_Date = putDate1(FromDate);

		String Check_Out_Date = putDate1(ToDate);

		String domainURL = baseUrl;

		String URL_2 = null;

		if (SearchType.equalsIgnoreCase("DomOW")) {

			URL_2 = "/flights/results?from=" + FromCity + "&to=" + ToCity + "&depart_date=" + Check_In_Date + "&adults="

					+ Adults + "&childs=" + Childrens + "&infants=" + Infants + "&class=Economy&airline=&carrier="

					+ PrefAirline + "&intl=n";

		} else if (SearchType.equalsIgnoreCase("IntlOW")) {

			URL_2 = "/flights/results?from=" + FromCity + "&to=" + ToCity + "&depart_date=" + Check_In_Date + "&adults="

					+ Adults + "&childs=" + Childrens + "&infants=" + Infants + "&class=Economy&airline=&carrier="

					+ PrefAirline + "&intl=y";



		} else if (SearchType.equalsIgnoreCase("DomRT")) {

			URL_2 = "/flights/results?from=" + FromCity + "&to=" + ToCity + "&depart_date=" + Check_In_Date

					+ "&return_date=" + Check_Out_Date + "&adults=" + Adults + "&childs=" + Childrens + "&infants="

					+ Infants + "&class=Economy&airline=&carrier=" + PrefAirline + "&intl=n";

		} else if (SearchType.equalsIgnoreCase("IntlRT")) {

			URL_2 = "/flights/results?from=" + FromCity + "&to=" + ToCity + "&depart_date=" + Check_In_Date

					+ "&return_date=" + Check_Out_Date + "&adults=" + Adults + "&childs=" + Childrens + "&infants="

					+ Infants + "&class=Economy&airline=&carrier=" + PrefAirline + "&intl=y";

		} else {

			addLog("Provide vaild Search type in SRP_URL method");

			Assert.assertTrue(false);

		}

		SRP_URL = domainURL + URL_2;

		driver.get(SRP_URL);

		return SRP_URL;

	}



	public void airDom_OW_BusinessClass_Search(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline, String pref_class, int attempt)

			throws Exception {

		Thread.sleep(10000);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);

		if (attempt == 0) {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 1, From_Date);

		} else {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 0, From_Date);

		}



		Thread.sleep(500);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "" || pref_airline == null) {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),



					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}



		if (pref_class != "" || pref_class == null) {

			if (!elementVisible(driver, getObject("AirCom_HomePage_BookingClass"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}



			safeSelect(driver, getObject("AirCom_HomePage_BookingClass"), pref_class);



		}



		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airCom_HomepageSearch_Oneway1(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline, int attempt) throws Exception {

		Thread.sleep(10000);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);

		if (attempt == 0) {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 1, From_Date);

		} else {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 0, From_Date);

		}

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "" || pref_airline == null) {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),



					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airCom_Homepage_SameDay_Search_Oneway(RemoteWebDriver driver, String FromCity, String ToCity,

			String Adults, String Childrens, String Infants, String pref_airline, int attempt) throws Exception {

		Thread.sleep(5000);



		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);



		safeClick(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")));

		Thread.sleep(500);

		List<WebElement> allDates = driver

				.findElements(By.xpath("//*[@id='ui-datepicker-div']/div/table/tbody/tr/td/a"));

		for (WebElement date : allDates) {

			if (date.getAttribute("class").contains("ui-state-active")) {

				date.click();



			}

		}

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "" || pref_airline == null) {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),

					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airCom_Homepage_Sync_Search_Oneway(RemoteWebDriver driver, String FromCity, String ToCity,

			String Adults, String Childrens, String Infants, String pref_airline, int attempt) throws Exception {

		Thread.sleep(5000);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);



		safeClick(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")));

		Thread.sleep(500);

		List<WebElement> allDates = driver

				.findElements(By.xpath("//*[@id='ui-datepicker-div']/div/table/tbody/tr/td/a"));

		allDates.get(1).click();



		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "" || pref_airline == null) {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),

					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void modifySearch_Oneway_SRP(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline) throws Exception {

		waitForElement(driver, 90, getObject("AirCom_SRP_Oneway_BookButton"));



		safeClick(driver, By.xpath("//div[@id='ResultContainer_1_1']/section/div/div/nav/ul/li/a"));

		Thread.sleep(2000);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);

		/*

		 * selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

		 * getObject("AirCom_HomePage_Departure_NextMonth"), 1, From_Date);

		 */safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "" || pref_airline == null) {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),

					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void ExpediaOnewaySearch(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline) throws Exception {

		// Thread.sleep(5000);



		elementClickable(driver, getObject("Expedia_HomePage_From_TextBox"), 60);

		safeAutocomplete(driver, getObject("Expedia_HomePage_From_TextBox"), getObject("Expedia_HomePage_From_Ajax"),

				FromCity);



		safeAutocomplete(driver, getObject("Expedia_HomePage_To_TextBox"), getObject("Expedia_HomePage_From_Ajax"),

				ToCity);



		selectCalendarDate(driver, getObject("Expedia_HomePage_Depature_Calender"),

				getObject("Expedia_HomePage_Departure_NextMonth"), 3, From_Date);



		Thread.sleep(5000);

		printInfo(driver.getCapabilities().getBrowserName());

		if (driver.getCapabilities().getBrowserName().contains("internet")) {

			safeClick(driver, getObject("Expedia_HomePage_Search_Btn"));

			Thread.sleep(20000);

			boolean a = driver.findElementById("button_flight_search").isDisplayed();// isElementPresent(driver,

			// getObject("Expedia_HomePage_Search_Btn"));

			System.out.println(a);

			if (a) {

				safeClick(driver, getObject("Expedia_HomePage_Search_Btn"));



			}

		} else if (driver.getCapabilities().getBrowserName().contains("firefox")

				|| driver.getCapabilities().getBrowserName().contains("chrome")) {

			safeClick(driver, getObject("Expedia_HomePage_Search_Btn"));

		}



	}



	/*

	 * Added by: prashanth.k@cleartrip.com Flight XP OW Search Production

	 */

	public void flightXP_OW_Search_Prod(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline) throws Exception {

		safeClick(driver, getObject("FlightXP_OW_Btn_Prod"));



		elementClickable(driver, getObject("FlightXP_HomePage_From_TextBox_Prod"), 60);



		List<WebElement> we = driver.findElements(By.xpath("//form/input[@placeholder='From']"));

		// System.out.println("size="+we.size());

		we.get(1).sendKeys("Delhi");

		Thread.sleep(2000);

		we.get(1).sendKeys(Keys.ENTER);



		List<WebElement> we1 = driver.findElements(By.xpath("//form/input[@placeholder='To']"));

		// System.out.println("size="+we.size());

		we1.get(1).sendKeys("Bombay");

		Thread.sleep(2000);

		we1.get(1).sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[@title='Next']")).click();



		Thread.sleep(3000);

		driver.findElement(By.xpath("//td/a[text()='" + From_Date + "']")).click();



		Thread.sleep(3000);

		safeClick(driver, getObject("FlightXP_HomePage_Search_Btn_Prod"));

	}



	public void ExpediaOnewaySearch_Prod(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline) throws Exception {

		// Thread.sleep(5000);



		safeClick(driver, getObject("Expedia_OW_Btn_Prod"));



		elementClickable(driver, getObject("Expedia_HomePage_From_TextBox_Prod"), 60);

		safeAutocomplete(driver, getObject("Expedia_HomePage_From_TextBox_Prod"),

				getObject("Expedia_HomePage_From_Ajax_Prod"), FromCity);



		safeAutocomplete(driver, getObject("Expedia_HomePage_To_TextBox_Prod"),

				getObject("Expedia_HomePage_From_Ajax_Prod"), ToCity);



		selectCalendarDate(driver, getObject("Expedia_HomePage_Depature_Calender_Prod"),

				getObject("Expedia_HomePage_Departure_NextMonth_Prod"), 3, From_Date);



		Thread.sleep(3000);

		printInfo(driver.getCapabilities().getBrowserName());

		if (driver.getCapabilities().getBrowserName().contains("internet")) {

			safeClick(driver, getObject("Expedia_HomePage_Search_Btn_Prod"));

			Thread.sleep(20000);

			boolean a = driver.findElementById("button_flight_search").isDisplayed();// isElementPresent(driver,

			// getObject("Expedia_HomePage_Search_Btn"));

			System.out.println(a);

			if (a) {

				safeClick(driver, getObject("Expedia_HomePage_Search_Btn_Prod"));



			}

		} else if (driver.getCapabilities().getBrowserName().contains("firefox")

				|| driver.getCapabilities().getBrowserName().contains("chrome")) {

			safeClick(driver, getObject("Expedia_HomePage_Search_Btn_Prod"));

		}



	}



	public void AmexOnewaySearch(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline) throws Exception {

		// Thread.sleep(5000);



		elementClickable(driver, getObject("Amex_HomePage_From_TextBox"), 60);

		safeAutocomplete(driver, getObject("Amex_HomePage_From_TextBox"), getObject("Amex_HomePage_From_Ajax"),

				FromCity);



		safeAutocomplete(driver, getObject("Amex_HomePage_To_TextBox"), getObject("Amex_HomePage_To_Ajax"), ToCity);



		selectCalendarDate(driver, getObject("Amex_HomePage_Depature_Calender"),

				getObject("Amex_HomePage_Departure_NextMonth"), 2, From_Date);



		Thread.sleep(5000);

		printInfo(driver.getCapabilities().getBrowserName());

		// if (driver.getCapabilities().getBrowserName().contains("internet")) {

		safeClick(driver, getObject("Amex_HomePage_Search_Btn"));

		Thread.sleep(20000);

		// }

	}



	public void ExpediaRoundTripSearch(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline) throws Exception {

		// Thread.sleep(5000);

		safeClick(driver, By.xpath(".//*[@id='rnd_trip']"));

		elementClickable(driver, getObject("Expedia_HomePage_From_TextBox"), 60);

		safeAutocomplete(driver, getObject("Expedia_HomePage_From_TextBox"), getObject("Expedia_HomePage_From_Ajax"),

				FromCity);



		safeAutocomplete(driver, getObject("Expedia_HomePage_To_TextBox"), getObject("Expedia_HomePage_From_Ajax"),

				ToCity);



		selectCalendarDate(driver, getObject("Expedia_HomePage_Depature_Calender"),

				getObject("Expedia_HomePage_Departure_NextMonth"), 3, From_Date);

		safeClick(driver, By.xpath("//span/input[@id='rtn_date']//../a"));

		List<WebElement> we = driver.findElements(By.xpath("//td/a"));

		we.get(3).click();



		Thread.sleep(5000);

		printInfo(driver.getCapabilities().getBrowserName());

		if (driver.getCapabilities().getBrowserName().contains("internet")) {

			safeClick(driver, getObject("Expedia_HomePage_Search_Btn"));

			Thread.sleep(20000);

			boolean a = driver.findElementById("button_flight_search").isDisplayed();// isElementPresent(driver,

			// getObject("Expedia_HomePage_Search_Btn"));

			System.out.println(a);

			if (a) {

				safeClick(driver, getObject("Expedia_HomePage_Search_Btn"));



			}

		} else if (driver.getCapabilities().getBrowserName().contains("firefox")

				|| driver.getCapabilities().getBrowserName().contains("chrome")) {

			safeClick(driver, getObject("Expedia_HomePage_Search_Btn"));

		}



	}



	/*

	 * Amex_HomePage_From_TextBox=XPATH://*[@id='FromTag']

	 * Amex_HomePage_To_TextBox=XPATH:.//*[@id='ToTag']

	 * Amex_HomePage_Search_Btn=XPATH:.//*[@id='SearchBtn']

	 * Amex_HomePage_Depature_Calender=XPATH:.//*[@id='DepartDate']

	 * Amex_HomePage_From_Ajax=XPATH:.//*[@id='FromTag']

	 * Amex_HomePage_To_Ajax=XPATH:.//*[@id='ToTag']

	 * Amex_HomePage_Departure_NextMonth

	 * =XPATH:.//*[@id='ui-datepicker-div']/div[2]/div/a Amex_SRP_Dom_OW_BookButton

	 * =XPATH:.//*[@id='signin_register']/fieldset/dl/dd[2]/button

	 * Amex_air_step2_continue_btn

	 * =XPATH:.//*[@id='signin_register']/fieldset/dl/dd[2]/button

	 */public void amexRoundTripSearch(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String Adults, String Childrens, String Infants, String pref_airline) throws Exception {

		// Thread.sleep(5000);

		safeClick(driver, By.xpath(".//*[@id='RoundTrip']"));

		elementClickable(driver, getObject("Amex_HomePage_From_TextBox"), 60);

		safeAutocomplete(driver, getObject("Amex_HomePage_From_TextBox"), getObject("Amex_HomePage_From_Ajax"),

				FromCity);



		safeAutocomplete(driver, getObject("Amex_HomePage_To_TextBox"), getObject("Amex_HomePage_To_Ajax"), ToCity);



		selectCalendarDate(driver, getObject("Amex_HomePage_Depature_Calender"),

				getObject("Amex_HomePage_Departure_NextMonth"), 2, From_Date);

		safeClick(driver, By.linkText("13"));



		Thread.sleep(5000);

		printInfo(driver.getCapabilities().getBrowserName());

		// if (driver.getCapabilities().getBrowserName().contains("internet")) {

		safeClick(driver, getObject("Amex_HomePage_Search_Btn"));

		Thread.sleep(20000);



	}



	public void expediaTravellerDetailsDom(RemoteWebDriver driver, String adult, String children, String infant,

			boolean GSTbooking) throws Exception {



		elementClickable(driver, By.xpath("//div[@id='itinerary']/dl/dd"), 180);

		// elementPresent_Time(driver, By.xpath("//div[@id='itinerary']/dl/dd"),

		// 100);

		Thread.sleep(5000);



		String LCC1 = "IndiGo";

		String LCC2 = "SpiceJet";

		String LCC3 = "GoAir";



		String Airline = driver.findElementByXPath("//div[@id='itinerary']/dl/dd").getText();

		addLog("Airline Present" + Airline, true);

		String name = "Test";



		if (Airline.contains(LCC2) || Airline.contains(LCC1) || Airline.contains(LCC3)) {

			Thread.sleep(5000);

			// driver.findele

			new Select(driver.findElement(By.id("AdultTitle1"))).selectByVisibleText("Mr");

			safeType(driver, getObject("Expedia_air_traveller_Fusername"), name);

			safeType(driver, getObject("Expedia_air_traveller_Lusername"), name);



		} else {



			new Select(driver.findElement(By.id("AdultTitle1"))).selectByVisibleText("Mr");

			safeType(driver, getObject("Expedia_air_traveller_Fusername"), "Jivan");

			safeType(driver, getObject("Expedia_air_traveller_Lusername"), "Kotian");



		}



		if (GSTbooking) {

			if (!driver.findElement(getObject("Expedia_Air_TravellerPage_GST_CheckBox")).isSelected()) {

				safeClick(driver, getObject("Expedia_Air_TravellerPage_GST_CheckBox"));

			}

			safeType(driver, getObject("Expedia_Air_TravellerPage_GST_Number"), "27AAAAA0000A1Z1");

			safeType(driver, getObject("Expedia_Air_TravellerPage_GST_Company"), "Test User");

			safeType(driver, getObject("Expedia_Air_TravellerPage_GST_Company_Address"),

					"123,Test Street,Test Area,India");

		}



		if (textPresent(driver, "Contact details", 20)) {



			new Select(driver.findElement(By.id("contactSalutation"))).selectByVisibleText("Mr");



			safeType(driver, getObject("Expedia_air_traveller_userContactFname"), name);

			safeType(driver, getObject("Expedia_air_traveller_userContactLname"), name);

			safeType(driver, getObject("Expedia_air_traveller_userContactAddress"), name);

			safeType(driver, getObject("Expedia_air_traveller_userContactCity"), name);

			safeType(driver, getObject("Expedia_air_traveller_userContactState"), name);

			safeType(driver, getObject("Expedia_air_traveller_userContactPin"), "560072");

			safeType(driver, getObject("Expedia_air_traveller_userContactPhoneNumber"), "9986508905");

			safeType(driver, getObject("Expedia_air_traveller_userContactCountry"), "India");

			/*

			 * safeAutocomplete(driver,

			 * getObject("Expedia_air_traveller_userContactCountry"),

			 * getObject("Expedia_air_traveller_userContactCountryAutoComplete" ), "India");

			 */

		}



		safeClick(driver, getObject("Expedia_air_step3_continue_New"));



		if (elementVisible(driver, getObject("Expediaair_step4_creditCard"), 180)) {

			addLog("Payment page successfully loaded.", true);

		} else {

			addLog("Payment page or credidcard link not loaded in 180 secondos!", true);

			Assert.fail("Timed out!!");

		}



	}



	public void flightXPStepTwoTravellerDom(RemoteWebDriver driver, String adult, String children, String infant)

			throws Exception {



		elementClickable(driver, By.xpath("//div[@id='itinerary']/dl/dd"), 180);

		// elementPresent_Time(driver, By.xpath("//div[@id='itinerary']/dl/dd"),

		// 100);

		Thread.sleep(5000);



		String LCC1 = "IndiGo";

		String LCC2 = "SpiceJet";

		String LCC3 = "GoAir";



		String Airline = driver.findElementByXPath("//div[@id='itinerary']/dl/dd").getText();

		addLog("Airline Present" + Airline, true);

		String name = "Test";



		if (Airline.contains(LCC2) || Airline.contains(LCC1) || Airline.contains(LCC3)) {



			// driver.find

			safeClick(driver, By.id("AdultTitle1_chosen"));

			safeClick(driver, By.xpath("//li[@data-option-array-index='1']"));

			safeType(driver, getObject("Expedia_air_traveller_Fusername"), name);

			safeType(driver, getObject("Expedia_air_traveller_Lusername"), name);



		} else {



			new Select(driver.findElement(By.id("AdultTitle1_chosen"))).selectByVisibleText("Mr");

			safeType(driver, getObject("Expedia_air_traveller_Fusername"), "Jivan");

			safeType(driver, getObject("Expedia_air_traveller_Lusername"), "Kotian");



		}

		// }

		if (textPresent(driver, "Contact details", 20)) {



			// new

			// Select(driver.findElement(By.id("contactSalutation"))).selectByVisibleText("Mr");

			safeClick(driver, By.xpath("//div[@id='contactSalutation_chosen']/a"));

			safeClick(driver,

					By.xpath("//div[@id='contactSalutation_chosen']/div/ul/li[@data-option-array-index='1']"));

			safeType(driver, getObject("Expedia_air_traveller_userContactFname"), name);

			safeType(driver, getObject("Expedia_air_traveller_userContactLname"), name);

			safeType(driver, getObject("Expedia_air_traveller_userContactPhoneNumber"), "9686333115");



		}



		safeClick(driver, getObject("Expedia_air_step3_continue_New")); //

		elementVisible(driver, getObject("Expediaair_step4_creditCard"), 300);

		assertEquals("Pay by Credit Card", driver.findElement(By.linkText("Pay by Credit Card")).getText());



	}



	public void b2cSplRoundTripDomSRP(RemoteWebDriver driver) throws Exception {

		elementVisible(driver, getObject("AirCom_HomePage_MoreOption_Link"), 5);



		if (isTextPresent(driver, "Sorry, our system is acting up")) {

			driver.navigate().refresh();

			elementVisible(driver, getObject("AirCom_HomePage_MoreOption_Link"), 5);

		}

		assertCommon(driver, "Duration", time_long, "SRP Loaded");

		UnSelectCarrier(driver, "9W");

		UnSelectCarrier(driver, "S2");

		logURL(driver);



		if (elementVisible(driver, getObject("Aircom_SRP_SplRt_Tab"), 2)) {

			safeClick(driver, getObject("Aircom_SRP_SplRt_Tab"));

			printInfo("First solution from splrt selected");

		} else {

			addLog("Special Round trip Flight not diplayed");

			AssertJUnit.assertTrue("Special Round trip Flight not diplayed", false);

		}

		Thread.sleep(10000);

		String flighttext = driver.findElement(getObject("Aircom_SRP_SplRt_splrtflightname")).getText();

		if (flighttext.contains("JetKonnect") || flighttext.contains("Jet Airways")) {

			if (isTextPresent(driver, "This combination is not available. Please select another flight")) {

				addLog("This combination is not available. Please select another flight");

				AssertJUnit.assertTrue("This combination is not available. Please select another flight", false);

			}



			safeClick(driver, getObject("tuxedo_SRP_air_dom_SPLRT_book_button"));



			safeClick(driver, getObject("Aircom_SRP_SplRt_Tab_link"));

			Thread.sleep(5000);

			printInfo("Second solution from splrt selected");

		}



		if (isTextPresent(driver, "This combination is not available. Please select another flight")) {

			addLog("This combination is not available. Please select another flight");

			AssertJUnit.assertTrue("This combination is not available. Please select another flight", false);

		}

		safeClick(driver, getObject("tuxedo_SRP_air_dom_SPLRT_book_button"));

	}



	@Override

	public String getModifiedDate(RemoteWebDriver driver, String date1) {

		String date = "";

		Calendar c = Calendar.getInstance();

		// System.out.println("input date="+date1);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		c.add(Calendar.DATE, Integer.parseInt(date1));



		String convertedDate = dateFormat.format(c.getTime());

		// System.out.println("Date increase by 15 days="+convertedDate);



		return convertedDate;

	}



	public String getModifiedDate1(RemoteWebDriver driver, String date1) {

		String date = "";

		Calendar c = Calendar.getInstance();

		// System.out.println("input date="+date1);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		c.add(Calendar.DATE, Integer.parseInt(date1));



		String convertedDate = dateFormat.format(c.getTime());

		// System.out.println("Date increase by 15 days="+convertedDate);



		return convertedDate;

	}



	public int getDay(String Date) {

		int day = 0;

		int splitDD = Integer.parseInt(Date.split("-")[2]);

		int splitD = splitDD - 1;

		return splitD;

	}



	public int getMonth(String Date) {

		int day = 0;

		int splitMM = Integer.parseInt(Date.split("-")[1]);

		int splitM = splitMM - 1;

		return splitM;

	}



	public int getYear(String Date) {

		System.out.println(Date);

		int day = 0;

		int splitMM = Integer.parseInt(Date.split("-")[0]);

		int splitM = splitMM;

		System.out.println(splitM);

		return splitM;

	}



	// pass "" as pref_airline in case there is no preffered airline

	public void airCom_HomepageSearch_RoundTrip(RemoteWebDriver driver, String FromCity, String ToCity,

			String From_Date, String To_Date, String Adults, String Childrens, String Infants, String pref_airline,

			int attempt) throws Exception {

		int month;

		int onwardmonth;

		int onwardMonth1 = 0;

		Thread.sleep(5000);

		safeClick(driver, getObject("AirCom_HomePage_Roundtrip_RadioButton"));

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);



		driver.findElement(By.xpath("//dd/div/a/i")).click();

		safeClick(driver, By.xpath("(//td[@data-handler='selectDay'])[8]"));

		safeClick(driver, By.xpath("(//td[@data-handler='selectDay'])[5]"));



		/*

		 * String onwarddate=getModifiedDate(driver,common.value(

		 * "Days_to_add_for_CurrentDate")); //

		 * System.out.println(getModifiedDate(driver,"15").split("-")[0]); int

		 * month1=Integer.parseInt(onwarddate.split("-")[1]);

		 *

		 * month=month1-1;

		 *

		 * //System.out.println(month); int

		 * day1=Integer.parseInt(onwarddate.split("-")[2]); int day=day1-1; //System

		 * .out.println("//*[contains(@data-year,'"+onwarddate.split("-")

		 * [0]+"') and contains(@data-month,'"+month+"')]"); //System.out.println("hi");

		 *

		 * List<WebElement> we=driver.findElements(By.xpath("//*[contains(@data-year,'"

		 * +onwarddate .split("-")[0]+"') and contains(@data-month,'"+month+"')]"));

		 * if(we.size()==0){ driver.findElement(By.className("nextMonth ")).click();

		 * Thread.sleep(5000);

		 * we=driver.findElements(By.xpath("//*[contains(@data-year,'" +onwarddate

		 * .split("-")[0]+"') and contains(@data-month,'"+month+"')]")); }

		 * //System.out.println("day="+day+"  size="+we.size());

		 * //driver.findElement(By.xpath(

		 * "//div[@id='ui-datepicker-div']/div[2]/table/tbody/tr[2]/td[2]/a" )).click();

		 * Test:for(int i=0;i<we.size();i++){ //

		 * System.out.println(we.get(i).getText()+"--"+Integer.toString(day));

		 * if(we.get(i).getText().equalsIgnoreCase(Integer.toString(day))){

		 * we.get(i+1).click(); Thread.sleep(5000); break Test; }

		 *

		 *

		 * } // we.get(day).click(); // } //if(attempt==0){ try{

		 * safeClick(driver,By.xpath(".//*[@id='ReturnDate']")); } catch(Exception e){

		 * System.out.println(e); } //getModifiedDate(driver,"7"); String

		 * date=getModifiedDate(driver,common

		 * .value("Days_to_add_for_CurrentDate_to_return"));

		 *

		 * // System.out.println(getModifiedDate(driver,"17").split("-")[0]); //int

		 * month1=Integer.parseInt(date.split("-")[1]);

		 *

		 * onwardmonth=month1-1;

		 *

		 * //System.out.println(onwardmonth); //int

		 * day1=Integer.parseInt(date.split("-")[2]); //int day=day1-1;

		 * //System.out.println(); System.out.println(day); int day11=day+3;

		 * System.out.println(day11); //System.out.println("//*[contains(@data-year,'"

		 * +date.split("-")[0]+"') and contains(@data-month,'" +onwardmonth+"')]");

		 * Thread.sleep(2000); //driver.findElement(By.xpath

		 * ("//div[@id='ui-datepicker-div']/div[2]/table/tbody/tr[2]/td[5]/a"

		 * )).click(); driver.findElement(By.linkText(String.valueOf(day11))).click();

		 * System.out.println("//*[contains(@data-year,'"+date.split("-")[0]+

		 * "') and contains(@data-month,'"+onwardmonth+"')]");

		 * //System.out.println("//*[contains(@data-year,'"+date.split("-")[0]+

		 * "') and contains(@data-month,'"+onwardmonth+"')]");

		 *

		 * List<WebElement> we1=driver.findElements(By.xpath("//*[contains(@data-year,'"

		 * +date.split ("-")[0]+"') and contains(@data-month,'"+onwardmonth+"')]"));

		 * if(we1.size()==0){ driver.findElement(By.className("nextMonth ")).click();

		 * Thread.sleep(5000);

		 * we1=driver.findElements(By.xpath("//*[contains(@data-year,'" +date.split

		 * ("-")[0]+"') and contains(@data-month,'"+onwardmonth+"')]")); }

		 *

		 * Thread.sleep(5000); if(we1.size()==0){

		 * driver.findElement(By.className("nextMonth ")).click(); Thread.sleep(5000); }

		 * //System.out.println("--------------"+we1.size()); for(int

		 * i=0;i<we1.size();i++){

		 *

		 * if(i==5){ we1.get(i).click(); } }

		 */



		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		if (pref_airline != "") {

			if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

				safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

			}

			safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),

					getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	/*

	 * Modified By: prashanth.k@cleartrip.com Fixed an issue where the City

	 * selection was failing.

	 */

	public void airCom_HomepageSearch_MultiCity(RemoteWebDriver driver, int numberOfSectors, String[] FromCity,

			String[] ToCity, String[] dateSet, String Adults, String Childrens, String Infants, String pref_airline,

			int attempt) throws Exception {

		Thread.sleep(10000);

		safeClick(driver, getObject("AirCom_HomePage_MultiCity_RadioButton"));

		if (numberOfSectors == 2) {

			safeClick(driver, getObject("AirCom_HomePage_MultiCity_Third_Segment_Delete_Button"));

		}

		/*

		 * for (int i = 1; i <= numberOfSectors; i++) { safeAutocomplete(driver,

		 * By.id(getValue("AirCom_HomePage_From_TextBox") + i),

		 * getObject("AirCom_HomePage_Multicity_From_Ajax" + i), FromCity[i]);

		 * safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox") + i),

		 * getObject("AirCom_HomePage_Multicity_To_Ajax" + i), ToCity[i]); if (i == 1 &&

		 * attempt == 0) selectCalendarDate(driver,

		 * By.id(getValue("AirCom_HomePage_Departure_Cal") + i),

		 * getObject("AirCom_HomePage_Departure_NextMonth"), 3, dateSet[i]); else

		 * selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal") +

		 * i), getObject("AirCom_HomePage_Departure_NextMonth"), 0, dateSet[i]); }

		 */

		for (int i = 1; i <= numberOfSectors; i++) {

			int j = i - 1;

			safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox") + i),

					getObject("AirCom_HomePage_Multicity_From_Ajax" + i), FromCity[j]);

			safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox") + i),

					getObject("AirCom_HomePage_Multicity_To_Ajax" + i), ToCity[j]);

			if (i == 1 && attempt == 0)

				selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal") + i),

						getObject("AirCom_HomePage_Departure_NextMonth"), 3, dateSet[i]);

			else

				selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal") + i),

						getObject("AirCom_HomePage_Departure_NextMonth"), 0, dateSet[i]);

		}

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		try {

			if (pref_airline != "") {

				if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

					safeClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

				}

				safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),

						getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

			}

		} catch (Exception e) {



		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airCom_HomepageSearch_MultiCity1(RemoteWebDriver driver, int numberOfSectors, String[] FromCity,

			String[] ToCity, String[] dateSet, String Adults, String Childrens, String Infants, String pref_airline,

			int attempt) throws Exception {

		int attempt1 = 0;



		safeClick(driver, getObject("AirCom_HomePage_MultiCity_RadioButton"));

		if (numberOfSectors == 2) {

			safeClick(driver, getObject("AirCom_HomePage_MultiCity_Third_Segment_Delete_Button"));

		}

		for (int i = 1; i <= numberOfSectors; i++) {

			int j = i - 1 + attempt;

			int month;

			int onwardmonth;

			safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox") + i),

					getObject("AirCom_HomePage_Multicity_From_Ajax" + i), FromCity[j]);

			safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox") + i),

					getObject("AirCom_HomePage_Multicity_To_Ajax" + i), ToCity[j]);



			if (attempt1 == 0) {

				safeClick(driver, By.xpath(".//*[@id='DepartDate1']"));

				String onwarddate = getModifiedDate(driver, "25");

				// System.out.println(getModifiedDate(driver,"25").split("-")[0]);

				int month1 = Integer.parseInt(onwarddate.split("-")[1]);



				month = month1 - 1;



				// System.out.println(month);

				int day1 = Integer.parseInt(onwarddate.split("-")[2]);

				// System.out.println("---"+day1);

				int day = day1;

				// System.out.println("//*[contains(@data-year,'"+onwarddate.split("-")[0]+"')

				// and contains(@data-month,'"+month+"')]");

				List<WebElement> we = driver.findElements(By.xpath("//*[contains(@data-year,'"

						+ onwarddate.split("-")[0] + "') and contains(@data-month,'" + month + "')]"));

				we.get(5).click();

			}

			if (attempt1 == 1) {

				safeClick(driver, By.xpath(".//*[@id='DepartDate2']"));

				// getModifiedDate(driver,"7");

				String date = getModifiedDate(driver, "25");

				// System.out.println(getModifiedDate(driver,"25").split("-")[0]);

				int month1 = Integer.parseInt(date.split("-")[1]);



				onwardmonth = month1 - 1;



				// System.out.println(onwardmonth);

				int day1 = Integer.parseInt(date.split("-")[2]);

				int day = day1 - 1;

				// System.out.println(day);

				// System.out.println("//*[contains(@data-year,'"+date.split("-")[0]+"') and

				// contains(@data-month,'"+onwardmonth+"')]");

				List<WebElement> we1 = driver.findElements(By.xpath("//*[contains(@data-year,'" + date.split("-")[0]

						+ "') and contains(@data-month,'" + onwardmonth + "')]"));

				// System.out.println(we1.size());

				we1.get(6).click();

			}

			/*

			 * if (i == 1 && attempt == 0) selectCalendarDate(driver,

			 * By.id(getValue("AirCom_HomePage_Departure_Cal") + i),

			 * getObject("AirCom_HomePage_Departure_NextMonth"), 3, dateSet[i]); else

			 * selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal") +

			 * i), getObject("AirCom_HomePage_Departure_NextMonth"), 0, dateSet[i]);

			 */

			attempt1++;

		}

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Adults"), Adults);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Childrens"), Childrens);

		safeSelect(driver, getObject("AirCom_HomePage_DropDown_Infants"), Infants);

		/*

		 * if(elementPresent(driver,By.id("MoreOptionsLink"),2)){

		 * safeClick(driver,By.id("MoreOptionsLink")); safeAutocomplete(driver,

		 * getObject("AirCom_HomePage_Prefered_Airline"),

		 * getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline); }

		 */

		try {

			// System.out.println("-------------------------------------"+driver.findElement(By.id("MoreOptionsLink")).isDisplayed());

			if (pref_airline != "") {

				if (!elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1)) {

					smartClick(driver, getObject("AirCom_HomePage_MoreOption_Link"));

				}

				if (elementVisible(driver, getObject("AirCom_HomePage_Prefered_Airline"), 1))

					safeAutocomplete(driver, getObject("AirCom_HomePage_Prefered_Airline"),

							getObject("AirCom_HomePage_Prefered_Airline_Ajax"), pref_airline);

			}

		} catch (Exception e) {



		}

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airCom_SRP_Oneway(RemoteWebDriver driver) throws Exception {

		// System.out.println(driver.getCurrentUrl());



		safeClick(driver, getObject("AirCom_SRP_Oneway_BookButton"));

		if (elementVisible(driver, By.id("priceChangeDownBtn"), 2)) {

			System.out.println("iNSIDE PRICE CHANGE BLOCK");

			safeClick(driver, By.id("priceChangeDownBtn"));

		}
		for (int i = 0; i < 10; i++) {
			String curl=driver.getCurrentUrl();
			if (curl.toLowerCase().contains("itinerary")) {
				break;
				
			} else {
				Thread.sleep(3000);
			}
		}

	}



	/*

	 * Added By: prashanth.kr@cleartrip.com This method un-checks the multi -

	 * itinerary check box based on input and de-selects the specified airline frmon

	 * the filter.

	 */

	public void deselectAirline(RemoteWebDriver driver, String airlineName, boolean multiItineraryRequired)

			throws Exception {

		if (!multiItineraryRequired) {

			addLog("Deselecting Multi-Itinerary!", true);

			JavascriptExecutor js1 = driver;

			WebElement element1 = driver

					.findElement(By.xpath("//*[@original-title='Show multi-airline itineraries']/../*[1]"));

			js1.executeScript("arguments[0].scrollIntoView(true);", element1);

			safeClick(driver, By.xpath("//*[@original-title='Show multi-airline itineraries']/../*[1]"));

		}



		if (elementPresent(driver, By.xpath("//*[@original-title='" + airlineName + "']/../*[2]"), 1)) {

			safeClick(driver, By.xpath("//*[@original-title='" + airlineName + "']/../*[2]"));

		} else {

			addLog("Airline: " + airlineName + " to de-select not found!", true);

		}

	}



	public void airCom_SRP_RoundTrip(RemoteWebDriver driver) throws Exception {

		for (int i = 0; i <= 10; i++) {

			if (elementVisible(driver, getObject("AirCom_SRP_Roundtrip_Discount"), 9)) {

				elementVisible(driver, getObject("AirCom_SRP_Roundtrip_Discount"), 1);

				safeClick(driver, getObject("AirCom_SRP_RoundTrip_BookButton"));

				break;

			} else

				driver.navigate().refresh();

		}

	}



	// Sector no 1,2,3 etc for various sectors in multicity, or else pass any

	// random value

	public boolean filterFlightsByLCCOrGDS(RemoteWebDriver driver, String flight_type, int sectorNo) throws Exception {

		List<WebElement> flights = null;

		if (waitElement(driver, getObject("AirCom_SRP_Flights_List_Prefix"), 5)) {

			flights = driver.findElements(getObject("AirCom_SRP_Flights_List_Prefix"));

		} else if (waitElement(driver, By.xpath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)

				+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix")), 5)) {

			flights = driver.findElementsByXPath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)

					+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix"));

		} else {

			printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.");

			assertTrue("Failure!", false);

		}

		return filterFlightsByLCCOrGDSProcess(driver, flights, flight_type);

	}



	public boolean filterFlightsByLCCOrGDSIntlReturn(RemoteWebDriver driver, String flight_type, int sectorNo)

			throws Exception {

		List<WebElement> flights = null;

		if (waitElement(driver, getObject("AirCom_SRP_Flights_List_Prefix_IntlReturn"), 5)) {

			flights = driver.findElements(getObject("AirCom_SRP_Flights_List_Prefix_IntlReturn"));

		} else {

			printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.");

			assertTrue("Failure!", false);

		}

		return filterFlightsByLCCOrGDSProcess(driver, flights, flight_type);

	}



	public boolean filterFlightsByLCCOrGDS1(RemoteWebDriver driver, String flight_type, int sectorNo) throws Exception {

		int a = 0;

		// System.out.println("flight type="+flight_type);

		boolean lccFlight = false;

		boolean sector1 = false;

		/*

		 * if(refreshflag){ driver.navigate().refresh(); Thread.sleep(15000); }

		 */



		List<WebElement> flights = null;

		List<WebElement> flights1 = null;

		/*

		 * if (waitElement(driver, getObject("AirCom_SRP_Flights_List_Prefix"), 5)) {

		 * flights = driver.findElements(By.xpath(

		 * "//label/span[@class='span span18 truncate']"));

		 *

		 * } else if (waitElement( driver,

		 * By.xpath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo) +

		 * getValue("AirCom_SRP_Flights_List_Multicity_Suffix")), 5)) { flights =

		 * driver.findElements(By.xpath(

		 * "//label/span[@class='span span18 truncate']"));

		 *

		 *

		 * }

		 */

		if (waitElement(driver, By.xpath("//label[contains(@for,'1_1_')]/span"), 1)) {

			flights = driver.findElements(By.xpath("//label[contains(@for,'1_1_')]/span"));



		}



		else {

			// printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.");

			assertTrue("Failure!", false);

		}



		for (int i = 1; i < flights.size(); i++) {

			// System.out.println(flights.size());

			boolean numberpresent = false;

			/*

			 * System.out.println("i value="+i);

			 * System.out.println(flights.get(i).getText());

			 */

			if (flights.get(i).getText().equalsIgnoreCase("show multi-airline itineraries")) {

				try {

					flights.get(i).click();

				} catch (Exception e) {

					continue;

				}

			}

			lccFlight = isLCCFlight(driver, flights.get(i).getText());

			Test: for (char c : flights.get(i).getText().toCharArray()) {

				// System.out.println("flight text=="+flights.get(i).getText());

				if (Character.isDigit(c)) {

					// System.out.println("enters in condition check");

					numberpresent = true;

					break Test;

				}

			}

			if ((!lccFlight && flight_type.equalsIgnoreCase("lcc") && (!numberpresent))

					|| (lccFlight && flight_type.equalsIgnoreCase("gds") && (!numberpresent))) {



				JavascriptExecutor jse = driver;

				jse.executeScript("window.scrollBy(0,250)", "");



				try {

					flights.get(i).click();

				} catch (Exception e) {

					continue;

				}

			}

			if (flights.get(i).getText().equalsIgnoreCase("Jet Airways") && flight_type.equalsIgnoreCase("gds")

					&& (!numberpresent)) {

				// dragAndDrop1(driver,flights.get(i),1,1);

				JavascriptExecutor jse = driver;

				jse.executeScript("window.scrollBy(0,250)", "");

				// System.out.println("flight name="+flights.get(i).getText());

				try {

					flights.get(i).click();

				} catch (Exception e) {

					continue;

				}

			}

		}

		// }



		refreshflag = false;

		if (checkWarning(driver)) {

			return true;

		} else {



			return false;

		}

	}



	/*

	 * Author: prashanth.k@cleartrip.com This methods selects filters to the

	 * available LCC or GDS flights It un-checks multi - airline flights.

	 */

	/*

	 * public boolean filterFlightsByPreferedAirline1(RemoteWebDriver driver, String

	 * flight_preference, int sectorNo) throws Exception { boolean

	 * prefferedFlightFound = false; List<WebElement> flights = null; String fp =

	 * flight_preference;

	 *

	 * if(flight_preference == null) {

	 * addLog("Preferred Airline not specified! Hence not filtering", true);

	 * return false; }

	 *

	 * if(flight_preference.equalsIgnoreCase("Airasia_india")) { fp = "Air Asia"; }

	 *

	 * if (waitElement(driver,By.xpath(

	 * "//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"

	 * ), 1)) { flights = driver.findElements(By.xpath(

	 * "//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"

	 * )); flights = driver.findElements(By.xpath(

	 * "//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label[contains(@for,'1_1_')]/span"

	 * ));

	 *

	 * }

	 *

	 *

	 * for(int i=0;i<flights.size();i++) { int j=i+1;

	 * //System.out.println(flights.get(i).getText());

	 * if((flight_preference.equalsIgnoreCase(flights.get(i).getText())) ||

	 * fp.equalsIgnoreCase(flights.get(i).getText())) { prefferedFlightFound =true;

	 * String path =

	 * "//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span[contains(text(),'"

	 * + flight_preference + "')]" ; mouseHover(driver, By.xpath(path)); path =

	 * "//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span[contains(text(),'"

	 * + flight_preference + "')]/../a"; mouseHoverClick(driver, By.xpath(path));

	 * addLog("Preffered flight: " + flight_preference + " found!",true);

	 * break;

	 *

	 * } }

	 *

	 * JavascriptExecutor jse = (JavascriptExecutor)driver;

	 * jse.executeScript("window.scrollBy(0,-600)", ""); Thread.sleep(1000);

	 *

	 * if (!prefferedFlightFound) { addLog("Preferred flight: " +

	 * flight_preference + " not found.",true); return true; } if

	 * (checkWarning(driver)) return true; else return false; }

	 */



	public boolean filterFlightsByLCCOrGDS2(RemoteWebDriver driver, String flight_type, int sectorNo) throws Exception {

		addLog("Flight type required = " + flight_type, true);



		boolean lccFlight = false;

		List<WebElement> flights = null;



		if (flight_type == "") {

			addLog("LCC or GDS not specified! Hence not filtering", true);

			return false;

		}



		if (waitElement(driver, By.xpath(

				"//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"),

				1)) {

			flights = driver.findElements(By.xpath(

					"//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"));

		} else {

			addLog("Not able to locate list of available flight names.", true);

			assertTrue("Failure! Not able to locate list of available flight names.", false);

		}

		addLog("Clicking on Multi Airline itineraries", false);

		safeClick(driver, By.xpath("//*[contains(text(),'Show multi-airline itineraries')]//../*[1]"));

		for (int i = 0; i < flights.size(); i++) {

			boolean numberpresent = false;



			// addLog(flights.get(i).getText(), true);

			// if(flights.get(i).getText().equalsIgnoreCase("show multi-airline

			// itineraries"))

			// {



			// }

			// else

			// {

			// addLog("Checking if it is LCC",true);

			lccFlight = isLCCFlight(driver, flights.get(i).getText());



			test: for (char c : flights.get(i).getText().toCharArray()) {

				if (Character.isDigit(c)) {

					numberpresent = true;

					break test;

				}

			}

			// }



			// addLog(lccFlight + " " + flight_type + " " + numberpresent

			// + " " + flights.get(i).getText(), false);

			if (!(lccFlight && flight_type.equalsIgnoreCase("lcc") && (!numberpresent))

					&& !(!lccFlight && flight_type.equalsIgnoreCase("gds") && (!numberpresent))) {

				try {

					int j = i + 1;

					// addLog("i="+i+" j="+j,true);

					// JavascriptExecutor jse = (JavascriptExecutor)driver;

					// jse.executeScript("window.scrollBy(0,600)", "");

					// Thread.sleep(1000);

					// Actions a = new Actions(driver);

					// WebElement we1 =

					// driver.findElement(By.xpath("(//div[contains(@class, 'filterContent clearFix

					// hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class,

					// 'list')]/li/label/span)["+j+"]"));

					// a.moveToElement(we1).moveToElement(driver.findElement(By.xpath("(//nav[contains(@class,

					// 'airlines')]/ul[contains(@class,

					// 'list')]/li/label/span)["+j+"]/../a"))).click(driver.findElement(By.xpath("(//nav[contains(@class,

					// 'airlines')]/ul[contains(@class,

					// 'list')]/li/label/span)["+j+"]/../a"))).perform();

					// break;

					scrollToElement(driver, By.xpath(

							"(//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["

									+ j + "]/../input"));

					safeClick(driver, By.xpath(

							"(//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["

									+ j + "]/../input"));

				} catch (Exception e) {

					addLog("Element not found / clickable. Continuing to next element", true);

				}

			}

		}



		if (checkWarning(driver)) {

			return true;

		} else {



			return false;

		}

	}



	/*

	 * public boolean filterFlightsByLCCOrGDS2(RemoteWebDriver driver, String

	 * flight_type, int sectorNo) throws Exception {

	 *

	 * System.out.println("flight type="+flight_type); boolean lccFlight = false;

	 * List<WebElement> flights = null; if (waitElement(driver,By.xpath(

	 * ".//*[@id='ResultContainer_1_1']/section[2]/section/aside[1]/div/div[1]/form/div/div[5]/div/nav/ul/li/label/span"

	 * ), 5)) { flights = driver.findElements(By.xpath(

	 * ".//*[@id='ResultContainer_1_1']/section[2]/section/aside[1]/div/div[1]/form/div/div[5]/div/nav/ul/li/label/span"

	 * )); //solutionSectors.get(0).click(); } else {

	 * printInfo("Not able to locate list of available flight names.");

	 * addLog("Not able to locate list of available flight names.");

	 * assertTrue("Failure!", false); } System.out.println("size="+flights.size());

	 *

	 * Test1:for(b=0;b<flights.size();b++){

	 * //System.out.println("size="+flights.size());

	 *

	 * //System.out.println("i value="+b); lccFlight = isLCCFlight(driver,

	 * flights.get(b).getText()); if ((!lccFlight &&

	 * flight_type.equalsIgnoreCase("lcc")) || (lccFlight &&

	 * flight_type.equalsIgnoreCase("gds"))) {

	 *

	 *

	 * JavascriptExecutor jse = (JavascriptExecutor)driver;

	 * jse.executeScript("window.scrollBy(0,250)", ""); Thread.sleep(1000);

	 * //System.out.println("flight name="+flights.get(b).getText());

	 * flights.get(b).click(); } if

	 * (flights.get(b).getText().equalsIgnoreCase("Jet Airways") &&

	 * flight_type.equalsIgnoreCase("gds")) {

	 * //dragAndDrop1(driver,flights.get(i),1,1); JavascriptExecutor jse =

	 * (JavascriptExecutor)driver; jse.executeScript("window.scrollBy(0,250)", "");

	 * //System.out.println("flight name="+flights.get(b).getText());

	 * flights.get(b).click(); } if(b>10){ break Test1; }

	 *

	 * }

	 *

	 * refreshflag=false; if (checkWarning(driver)){ return true; } else{

	 *

	 * return false; } }

	 */



	public void ctRoutedFlight(RemoteWebDriver driver) throws Exception {

		int a = 0;

		// System.out.println("flight type="+flight_type);

		boolean lccFlight = false;

		boolean sector1 = false;

		/*

		 * if(refreshflag){ driver.navigate().refresh(); Thread.sleep(15000); }

		 */



		List<WebElement> flights = null;

		List<WebElement> flights1 = null;

		/*

		 * if (waitElement(driver, getObject("AirCom_SRP_Flights_List_Prefix"), 5)) {

		 * flights = driver.findElements(By.xpath(

		 * "//label/span[@class='span span18 truncate']"));

		 *

		 * } else if (waitElement( driver,

		 * By.xpath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo) +

		 * getValue("AirCom_SRP_Flights_List_Multicity_Suffix")), 5)) { flights =

		 * driver.findElements(By.xpath(

		 * "//label/span[@class='span span18 truncate']"));

		 *

		 *

		 * }

		 */

		if (waitElement(driver, By.xpath("//label[contains(@for,'1_1_')]/span"), 1)) {

			flights = driver.findElements(By.xpath("//label[contains(@for,'1_1_')]/span"));



		}



		else {

			printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.");

			assertTrue("Failure!", false);

		}



		for (int i = 1; i < flights.size(); i++) {

			// System.out.println(flights.size());

			boolean numberpresent = false;

			/*

			 * System.out.println("i value="+i);

			 * System.out.println(flights.get(i).getText());

			 */

			if (flights.get(i).getText().equalsIgnoreCase("show multi-airline itineraries")) {

				flights.get(i).click();

			}

			lccFlight = isLCCFlight(driver, flights.get(i).getText());



		}

	}



	/*

	 * Modified by: Prashanth.k@cleartrip.com Was failing as xpaths text comparisons

	 * had changed. Now fixed.

	 */



	public void transitVisaSearch(RemoteWebDriver driver) throws Exception {

		// selectingStop("Non Direct", driver,1);



		List<WebElement> we = driver.findElements(By.xpath("//li[contains(@class,'listItem')]"));

		List<WebElement> we1 = driver.findElements(By.xpath("//a[@linkidentifier='itinerary']"));

		// System.out.println(""+we.size()+" "+we1.size());



		test: for (int i = 0; i < we.size(); i++) {

			try {

				we1.get(i).click();

				Thread.sleep(2000);

			} catch (Exception e) {

				addLog("Advrt Block.", true);

				continue;

			}

			// System.out.println("i value="+i);



			// System.out.println("---"+we.get(i).findElement(By.xpath("./div/div/span[2]")).getText());

			// System.out.println("---"+we.get(i).findElement(By.xpath("./div/div/span[2]")).getAttribute("style"));

			// System.out.println("+++++"+we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).getText());

			// System.out.println(we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).isDisplayed());

			try {

				if (we.get(i).findElement(By.xpath(".//span[@class='transitVisaText visaTransitText']"))

						.isDisplayed()) {

					if (we.get(i).findElement(By.xpath(".//span[@class='transitVisaText visaTransitText']")).getText()

							.contains("Transit Visa")) {

						addLog("Change Airport Warning is not Displayed!", true);



						addLog("Transit Visa Warning: " + we.get(i)

								.findElement(By.xpath(".//span[@class='transitVisaText visaTransitText']")).getText(),

								true);

						we.get(i).findElement(By.xpath(".//td/button")).click();

						break test;

					}

				}



				/*

				 * if(we.get(i).findElement(By.xpath("./div/div/span[2]")). isDisplayed()) {

				 * if(we.get(i).findElement(By.xpath("./div/div/span[2]"

				 * )).getText().contains("Transit Visa")) { we.get(i).findElement

				 * (By.xpath("./table/tbody[2]/tr[1]/td[1]/button")).click(); break test; } }

				 */

			} catch (Exception e) {

				e.printStackTrace();

			}

			// System.out.println("---"+we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).getAttribute("class"));



			we1.get(i).click();

			Thread.sleep(2000);

		}

	}



	public void searchingFlight(RemoteWebDriver driver) throws Exception {

		// selectingStop("Non Direct", driver,1);



		List<WebElement> we = driver.findElements(By.xpath("//li[contains(@class,'listItem')]"));

		List<WebElement> we1 = driver.findElements(By.xpath("//a[@linkidentifier='itinerary']"));

		// System.out.println(""+we.size()+" "+we1.size());



		test: for (int i = 0; i < we.size(); i++) {

			we1.get(i).click();

			Thread.sleep(2000);

			// System.out.println("i value="+i);



			// System.out.println("---"+we.get(i).findElement(By.xpath("./div/div/span[2]")).getText());

			// System.out.println("---"+we.get(i).findElement(By.xpath("./div/div/span[2]")).getAttribute("style"));

			// System.out.println("+++++"+we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).getText());

			// System.out.println(we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).isDisplayed());

			try {

				if (we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).isDisplayed()) {

					if (we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).getAttribute("class")

							.contains("changeAirport")) {

						we.get(i).findElement(By.xpath("./table/tbody[2]/tr[2]/td[3]/button")).click();

						break test;

					} else if (we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).getText().contains("transit")) {

						we.get(i).findElement(By.xpath("./table/tbody[2]/tr[2]/td[3]/button")).click();

						break test;

					}

				}

			} catch (Exception e) {



			}

			// System.out.println("---"+we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).getAttribute("class"));

			if (we.get(i).findElement(By.xpath("./div/div/span[2]")).getText().contains("transit Visa")) {

				we.get(i).findElement(By.xpath("./table/tbody[2]/tr[2]/td[3]/button")).click();

				break test;

			}

		}

	}



	public boolean filterFlightsByLCCOrGDSProcess(RemoteWebDriver driver, List<WebElement> flights, String flight_type)

			throws Exception {

		boolean lccFlight = false;

		// SearchResult.count = 0;

		// remove first element, i.e. "show all airlines"

		flights.remove(0);

		JavascriptExecutor jse = driver;

		jse.executeScript("window.scrollBy(0,150)", "");

		for (WebElement flight : flights) {

			String flight_name = flight.findElement(getObject("AirCom_SRP_Flights_List_Suffix")).getText();

			// if(flight_name.equalsIgnoreCase("tiger airways") ||

			// flight_name.equalsIgnoreCase("spicejet")) {

			// flight.findElement(By.xpath(param.get("flight_checkbox"))).click();

			// continue;

			// }

			// jse.executeScript("window.scrollBy(0,150)", "");

			lccFlight = isLCCFlight(driver, flight_name);

			if ((!lccFlight && flight_type.equalsIgnoreCase("lcc"))

					|| (lccFlight && flight_type.equalsIgnoreCase("gds"))) {

				try {

					flight.findElement(getObject("AirCom_SRP_Flights_Checkbox")).click();

				} catch (Exception e) {

					continue;

				}

			}

			if (flight_name.equalsIgnoreCase("Jet Airways") && flight_type.equalsIgnoreCase("gds")) {

				try {

					flight.findElement(getObject("AirCom_SRP_Flights_Checkbox")).click();

				} catch (Exception e) {

					continue;

				}

			}

		}

		// removeAds(driver);

		if (checkWarning(driver))

			return true;

		else

			return false;

	}



	public void selectingStopOver(RemoteWebDriver driver) throws InterruptedException {

		List<WebElement> we = driver.findElements(By.xpath("//li[contains(@class,'listItem')]"));

		List<WebElement> we1 = driver.findElements(By.xpath("//a[@linkidentifier='itinerary']"));

		// System.out.println(""+we.size()+" "+we1.size());



		test: for (int i = 0; i < we.size(); i++) {

			we1.get(i).click();

			Thread.sleep(2000);

			// System.out.println("i value="+i);



			// System.out.println("---"+we.get(i).findElement(By.xpath("./div/div/span[2]")).getText());

			// System.out.println("---"+we.get(i).findElement(By.xpath("./div/div/span[2]")).getAttribute("style"));

			// System.out.println("+++++"+we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).getText());

			// System.out.println(we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).isDisplayed());

			try {

				String flight = we.get(i).findElement(By.xpath("./div/ul/li[1]/div/ul[1]/li[1]/div[2]/small[1]"))

						.getText();

				String flight1 = we.get(i).findElement(By.xpath("./div/ul/li[1]/div/ul[2]/li[1]/div[2]/small[1]"))

						.getText();

				addLog("flight=" + flight + "   flight1=" + flight1, true);

				if (flight.equalsIgnoreCase(flight1)) {

					we.get(i).findElement(By.xpath("./table/tbody/tr[2]/td[3]/button")).click();

					break test;

				}

				// System.out.println(we.get(i).findElement(By.xpath("./div/ul/li[1]/div/ul[1]/li[1]/div[2]/small[1]")).getText());

				// System.out.println(we.get(i).findElement(By.xpath("./div/ul/li[1]/div/ul[2]/li[1]/div[2]/small[1]")).getText());



				/*

				 * if(we.get(i).findElement(By.xpath("./div/div[2]/span[2]")). isDisplayed()){

				 * if(we.get(i).findElement(By.xpath("./div/div[2]/span[2]"

				 * )).getAttribute("class").contains("changeAirport")){ we.get(i)

				 * .findElement(By.xpath("./table/tbody[2]/tr[2]/td[3]/button" )).click(); break

				 * test; } else if(we.get(i).findElement(By.xpath

				 * ("./div/div[2]/span[2]")).getText().contains("transit")){ we.get

				 * (i).findElement(By.xpath("./table/tbody[2]/tr[2]/td[3]/button" )).click();

				 * break test; } }

				 */} catch (Exception e) {



			}

			// System.out.println("---"+we.get(i).findElement(By.xpath("./div/div[2]/span[2]")).getAttribute("class"));

			/*

			 * if(we.get(i).findElement(By.xpath("./div/div/span[2]")).getText().

			 * contains("transit Visa")){

			 * we.get(i).findElement(By.xpath("./table/tbody[2]/tr[2]/td[3]/button"

			 * )).click(); break test; }

			 */

		}



	}



	public boolean filterFlightsByLCCOrGDSOW(RemoteWebDriver driver, String flight_type) throws Exception {



		System.out.println("flight type=" + flight_type);

		boolean lccFlight = false;

		List<WebElement> flights = null;

		if (waitElement(driver, By.xpath(

				".//*[@id='ResultContainer_1_1']/section[2]/section/aside[1]/div/div[1]/form/div/div[5]/div/nav/ul/li/label/span"),

				5)) {

			flights = driver.findElements(By.xpath(

					".//*[@id='ResultContainer_1_1']/section[2]/section/aside[1]/div/div[1]/form/div/div[5]/div/nav/ul/li/label/span"));

			// solutionSectors.get(0).click();

		} else {

			printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.");

			assertTrue("Failure!", false);

		}

		System.out.println("size=" + flights.size());



		Test1: for (b = 0; b < flights.size(); b++) {

			// System.out.println("size="+flights.size());



			// System.out.println("i value="+b);

			lccFlight = isLCCFlight(driver, flights.get(b).getText());

			if ((!lccFlight && flight_type.equalsIgnoreCase("lcc"))

					|| (lccFlight && flight_type.equalsIgnoreCase("gds"))) {



				JavascriptExecutor jse = driver;

				jse.executeScript("window.scrollBy(0,250)", "");

				Thread.sleep(1000);

				// System.out.println("flight name="+flights.get(b).getText());

				flights.get(b).click();

			}

			if (flights.get(b).getText().equalsIgnoreCase("Jet Airways") && flight_type.equalsIgnoreCase("gds")) {

				// dragAndDrop1(driver,flights.get(i),1,1);

				JavascriptExecutor jse = driver;

				jse.executeScript("window.scrollBy(0,250)", "");

				// System.out.println("flight name="+flights.get(b).getText());

				flights.get(b).click();

			}

			/*

			 * if(b>10){ break Test1; }

			 */



		}



		refreshflag = false;

		if (checkWarning(driver)) {

			return true;

		} else {



			return false;

		}

	}



	public boolean filterFlightsByLCCOrGDSRT(RemoteWebDriver driver, String flight_type, String source, String dest,

			String source1, String dest1) throws Exception {



		System.out.println("flight type=" + flight_type);

		boolean lccFlight = false;



		List<WebElement> solutionSectors = driver.findElements(getObject("AirCom_SRP_Multicity_Sectors"));



		List<WebElement> flights = null;



		System.out.println(

				"//div[@data-sector='" + source + "_" + dest + "']/div[4]/div/nav/ul/li[@class='airlines']/label/span");

		System.out.println("source=" + source);

		System.out.println("source1=" + source1);

		System.out.println("dest=" + dest);

		System.out.println("dest1=" + dest1);

		if (waitElement(driver, By.xpath(

				"//div[@data-sector='" + source + "_" + dest + "']/div[4]/div/nav/ul/li[@class='airlines']/label/span"),

				5)) {

			flights = driver.findElements(By.xpath("//div[@data-sector='" + source + "_" + dest

					+ "']/div[4]/div/nav/ul/li[@class='airlines']/label/span"));

			solutionSectors.get(0).click();

		} else if (waitElement(driver, By.xpath(

				"//div[@data-sector='" + source + "_" + dest + "']/div[3]/div/nav/ul/li[@class='airlines']/label/span"),

				5)) {

			flights = driver.findElements(By.xpath("//div[@data-sector='" + source + "_" + dest

					+ "']/div[3]/div/nav/ul/li[@class='airlines']/label/span"));

			solutionSectors.get(0).click();

		}



		else {

			printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.");

			assertTrue("Failure!", false);

		}

		// System.out.println("size="+flights.size());



		Test1: for (b = 0; b < flights.size(); b++) {

			// System.out.println("size="+flights.size());

			boolean numberpresent = false;

			// System.out.println("i value="+b);

			lccFlight = isLCCFlight(driver, flights.get(b).getText());

			if ((!lccFlight && flight_type.equalsIgnoreCase("lcc"))

					|| (lccFlight && flight_type.equalsIgnoreCase("gds"))) {



				JavascriptExecutor jse = driver;

				jse.executeScript("window.scrollBy(0,250)", "");



				// System.out.println("flight name="+flights.get(b).getText());

				flights.get(b).click();

			}

			if (!flight_type.equalsIgnoreCase("gds")) {

				// dragAndDrop1(driver,flights.get(i),1,1);

				JavascriptExecutor jse = driver;

				jse.executeScript("window.scrollBy(0,250)", "");

				System.out.println("flight name=" + flights.get(b).getText());

				flights.get(b).click();

			}

			if (b > 10) {

				break Test1;

			}



		}



		if (waitElement(driver, By.xpath("//div[@data-sector='" + source1 + "_" + dest1

				+ "']/div[4]/div/nav/ul/li[@class='airlines']/label/span"), 5)) {

			flights = driver.findElements(By.xpath("//div[@data-sector='" + source1 + "_" + dest1

					+ "']/div[4]/div/nav/ul/li[@class='airlines']/label/span"));

			solutionSectors.get(1).click();

		} else if (waitElement(driver, By.xpath("//div[@data-sector='" + source1 + "_" + dest1

				+ "']/div[3]/div/nav/ul/li[@class='airlines']/label/span"), 5)) {

			flights = driver.findElements(By.xpath("//div[@data-sector='" + source1 + "_" + dest1

					+ "']/div[3]/div/nav/ul/li[@class='airlines']/label/span"));

			solutionSectors.get(1).click();

		}



		else {

			printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.");

			assertTrue("Failure!", false);

		}

		// System.out.println("size="+flights.size());



		Test1: for (b = 0; b < flights.size(); b++) {

			// System.out.println("size="+flights.size());

			// boolean numberpresent=false;

			// System.out.println("i value="+b);

			lccFlight = isLCCFlight(driver, flights.get(b).getText());

			if ((!lccFlight && flight_type.equalsIgnoreCase("lcc"))

					|| (lccFlight && flight_type.equalsIgnoreCase("gds"))) {



				JavascriptExecutor jse = driver;

				jse.executeScript("window.scrollBy(0,250)", "");



				// System.out.println("flight name="+flights.get(b).getText());

				flights.get(b).click();

			}

			if (flights.get(b).getText().equalsIgnoreCase("Jet Airways") && flight_type.equalsIgnoreCase("gds")) {

				// dragAndDrop1(driver,flights.get(i),1,1);

				JavascriptExecutor jse = driver;

				jse.executeScript("window.scrollBy(0,250)", "");

				System.out.println("flight name=" + flights.get(b).getText());

				flights.get(b).click();

			}

			if (b > 10) {

				break Test1;

			}



		}

		refreshflag = false;

		if (checkWarning(driver)) {

			return true;

		} else {



			return false;

		}

	}



	/*

	 * Author: prashanth.k@cleartrip.com Filters by GDS or LCC. Rewritten as Xpaths

	 * had changed

	 */

	public boolean filterFlightsByLCCOrGDSRT2(RemoteWebDriver driver, String flight_type, String source, String dest,

			String source1, String dest1) throws Exception {

		addLog("Required flight type = " + flight_type, true);

		boolean lccFlight = false;



		List<WebElement> solutionSectors = driver.findElements(getObject("AirCom_SRP_Multicity_Sectors"));

		List<WebElement> flights = null;



		System.out.println(

				"//div[@data-sector='" + source + "_" + dest + "']/div[4]/div/nav/ul/li[@class='airlines']/label/span");

		System.out.println("source=" + source);

		System.out.println("source1=" + source1);

		System.out.println("dest=" + dest);

		System.out.println("dest1=" + dest1);



		if (waitElement(driver, By.xpath(

				"//div[@data-sector='" + source + "_" + dest + "']/div[4]/div/nav/ul/li[@class='airlines']/label/span"),

				5)) {

			flights = driver.findElements(By.xpath("//div[@data-sector='" + source + "_" + dest

					+ "']/div[4]/div/nav/ul/li[@class='airlines']/label/span"));

			solutionSectors.get(0).click();

		} else if (waitElement(driver, By.xpath(

				"//div[@data-sector='" + source + "_" + dest + "']/div[3]/div/nav/ul/li[@class='airlines']/label/span"),

				5)) {

			flights = driver.findElements(By.xpath("//div[@data-sector='" + source + "_" + dest

					+ "']/div[3]/div/nav/ul/li[@class='airlines']/label/span"));

			solutionSectors.get(0).click();

		}



		else {

			// printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.", true);

			assertTrue("Failure!", false);

		}



		for (b = 0; b < flights.size(); b++) {

			boolean numberpresent = false;

			addLog(flights.get(b).getText(), false);

			if (flights.get(b).getText().equalsIgnoreCase("show multi-airline itineraries")) {

				addLog("Clicking on Multi Airline itineraries", false);

				flights.get(b).click();

				continue;

			} else {

				// addLog("Checking if it is LCC",true);

				lccFlight = isLCCFlight(driver, flights.get(b).getText());



				test: for (char c : flights.get(b).getText().toCharArray()) {

					if (Character.isDigit(c)) {

						numberpresent = true;

						break test;

					}

				}

			}



			// addLog(lccFlight + " " + flight_type + " " + numberpresent,

			// false);

			if (!(lccFlight && flight_type.equalsIgnoreCase("lcc") && (!numberpresent))

					&& !(!lccFlight && flight_type.equalsIgnoreCase("gds") && (!numberpresent))) {

				try {

					int j = b + 2;

					// addLog("i="+i+" j="+j,true);

					JavascriptExecutor jse = driver;

					jse.executeScript("window.scrollBy(0,600)", "");

					Thread.sleep(1000);

					Actions a = new Actions(driver);

					WebElement we1 = driver.findElement(

							By.xpath("(//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["

									+ j + "]"));

					// a.moveToElement(we1).moveToElement(driver.findElement(By.xpath("(//nav[contains(@class,

					// 'airlines')]/ul[contains(@class,

					// 'list')]/li/label/span)["+j+"]/../a"))).click(driver.findElement(By.xpath("(//nav[contains(@class,

					// 'airlines')]/ul[contains(@class,

					// 'list')]/li/label/span)["+j+"]/../a"))).perform();

					// break;

					safeClick(driver,

							By.xpath("(//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["

									+ j + "]/../input"));

				} catch (Exception e) {

					addLog("Element not found / clickable. Continuing to next element", false);

				}

			}

		}



		if (waitElement(driver, By.xpath("//div[@data-sector='" + source1 + "_" + dest1

				+ "']/div[4]/div/nav/ul/li[@class='airlines']/label/span"), 5)) {

			flights = driver.findElements(By.xpath("//div[@data-sector='" + source1 + "_" + dest1

					+ "']/div[4]/div/nav/ul/li[@class='airlines']/label/span"));

			solutionSectors.get(1).click();

		} else if (waitElement(driver, By.xpath("//div[@data-sector='" + source1 + "_" + dest1

				+ "']/div[3]/div/nav/ul/li[@class='airlines']/label/span"), 5)) {

			flights = driver.findElements(By.xpath("//div[@data-sector='" + source1 + "_" + dest1

					+ "']/div[3]/div/nav/ul/li[@class='airlines']/label/span"));

			solutionSectors.get(1).click();

		} else {

			// printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.", true);

			assertTrue("Failure!", false);

		}



		for (b = 0; b < flights.size(); b++) {

			boolean numberpresent = false;

			addLog(flights.get(b).getText(), true);

			if (flights.get(b).getText().equalsIgnoreCase("show multi-airline itineraries")) {

				addLog("Clicking on Multi Airline itineraries", false);

				flights.get(b).click();

				continue;

			} else {

				// addLog("Checking if it is LCC", false);

				lccFlight = isLCCFlight(driver, flights.get(b).getText());



				test: for (char c : flights.get(b).getText().toCharArray()) {

					if (Character.isDigit(c)) {

						numberpresent = true;

						break test;

					}

				}

			}



			// addLog(lccFlight + " " + flight_type + " " + numberpresent,

			// false);

			if (!(lccFlight && flight_type.equalsIgnoreCase("lcc") && (!numberpresent))

					&& !(!lccFlight && flight_type.equalsIgnoreCase("gds") && (!numberpresent))) {

				try {

					int j = b + 2;

					// addLog("i="+i+" j="+j,true);

					JavascriptExecutor jse = driver;

					jse.executeScript("window.scrollBy(0,600)", "");

					Thread.sleep(1000);

					Actions a = new Actions(driver);

					WebElement we1 = driver.findElement(

							By.xpath("(//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["

									+ j + "]"));

					// a.moveToElement(we1).moveToElement(driver.findElement(By.xpath("(//nav[contains(@class,

					// 'airlines')]/ul[contains(@class,

					// 'list')]/li/label/span)["+j+"]/../a"))).click(driver.findElement(By.xpath("(//nav[contains(@class,

					// 'airlines')]/ul[contains(@class,

					// 'list')]/li/label/span)["+j+"]/../a"))).perform();

					// break;

					// safeClick(driver,

					// By.xpath("(//nav[contains(@class, 'airlines')]/ul[contains(@class,

					// 'list')]/li/label/span)["+j+"]/../input"));

					flights.get(b).click();

				} catch (Exception e) {

					addLog("Element not found / clickable. Continuing to next element", true);

				}

			}

		}



		refreshflag = false;



		if (checkWarning(driver)) {

			return true;

		} else {

			return false;

		}

	}



	/*

	 * public boolean filterFlightsByLCCOrGDSRT2(RemoteWebDriver driver, String

	 * flight_type,String source,String dest,String source1,String dest1) throws

	 * Exception {

	 *

	 *

	 * addLog("flight type="+flight_type);

	 * System.out.println("flight type="+flight_type); boolean lccFlight = false;

	 *

	 * List<WebElement> solutionSectors =

	 * driver.findElements(getObject("AirCom_SRP_Multicity_Sectors"));

	 *

	 *

	 * List<WebElement> flights = null;

	 *

	 *

	 * System.out.println("//div[@data-sector='"+source+"_"+dest+

	 * "']/div[4]/div/nav/ul/li[@class='airlines']/label/span");

	 * System.out.println("source="+source); System.out.println("source1="+source1);

	 * System.out.println("dest="+dest); System.out.println("dest1="+dest1); if

	 * (waitElement(driver,By.xpath("//div[@data-sector='"

	 * +source+"_"+dest+"']/div[4]/div/nav/ul/li[@class='airlines']/label/span" ),

	 * 5)) { flights = driver.findElements(By.xpath("//div[@data-sector='"+source

	 * +"_"+dest+"']/div[4]/div/nav/ul/li[@class='airlines']/label/span"));

	 * solutionSectors.get(0).click(); } else if

	 * (waitElement(driver,By.xpath("//div[@data-sector='"

	 * +source+"_"+dest+"']/div[3]/div/nav/ul/li[@class='airlines']/label/span" ),

	 * 5)) { flights = driver.findElements(By.xpath("//div[@data-sector='"+source

	 * +"_"+dest+"']/div[3]/div/nav/ul/li[@class='airlines']/label/span"));

	 * solutionSectors.get(0).click(); }

	 *

	 * else { printInfo("Not able to locate list of available flight names.");

	 * addLog("Not able to locate list of available flight names.");

	 * assertTrue("Failure!", false); } //

	 * System.out.println("size="+flights.size());

	 *

	 * Test1:for(b=0;b<flights.size();b++){

	 * //System.out.println("size="+flights.size()); boolean numberpresent=false;

	 * //System.out.println("i value="+b); lccFlight = isLCCFlight(driver,

	 * flights.get(b).getText()); if ((!lccFlight &&

	 * flight_type.equalsIgnoreCase("lcc")) || (lccFlight &&

	 * flight_type.equalsIgnoreCase("gds"))) {

	 *

	 *

	 * JavascriptExecutor jse = (JavascriptExecutor)driver;

	 * jse.executeScript("window.scrollBy(0,250)", "");

	 *

	 * //System.out.println("flight name="+flights.get(b).getText());

	 * flights.get(b).click(); } if ( !flight_type.equalsIgnoreCase("gds")) {

	 * //dragAndDrop1(driver,flights.get(i),1,1); JavascriptExecutor jse =

	 * (JavascriptExecutor)driver; jse.executeScript("window.scrollBy(0,250)", "");

	 * System.out.println("flight name="+flights.get(b).getText());

	 * flights.get(b).click(); } if(b>10){ break Test1; }

	 *

	 * }

	 *

	 * if (waitElement(driver,By.xpath("//div[@data-sector='"+source1+"_"+dest1+

	 * "']/div[4]/div/nav/ul/li[@class='airlines']/label/span"), 5)) { flights =

	 * driver.findElements(By.xpath("//div[@data-sector='"+source1+"_"+dest1+

	 * "']/div[4]/div/nav/ul/li[@class='airlines']/label/span"));

	 * solutionSectors.get(1).click(); } else if

	 * (waitElement(driver,By.xpath("//div[@data-sector='"

	 * +source1+"_"+dest1+"']/div[3]/div/nav/ul/li[@class='airlines']/label/span" ),

	 * 5)) { flights = driver.findElements(By.xpath("//div[@data-sector='"+source1

	 * +"_"+dest1+"']/div[3]/div/nav/ul/li[@class='airlines']/label/span"));

	 * solutionSectors.get(1).click(); }

	 *

	 * else { printInfo("Not able to locate list of available flight names.");

	 * addLog("Not able to locate list of available flight names.");

	 * assertTrue("Failure!", false); } //

	 * System.out.println("size="+flights.size());

	 *

	 * Test1:for(b=0;b<flights.size();b++){

	 * //System.out.println("size="+flights.size()); //boolean numberpresent=false;

	 * //System.out.println("i value="+b); lccFlight = isLCCFlight(driver,

	 * flights.get(b).getText()); if ((!lccFlight &&

	 * flight_type.equalsIgnoreCase("lcc")) || (lccFlight &&

	 * flight_type.equalsIgnoreCase("gds") )) {

	 *

	 *

	 * JavascriptExecutor jse = (JavascriptExecutor)driver;

	 * jse.executeScript("window.scrollBy(0,250)", "");

	 *

	 * //System.out.println("flight name="+flights.get(b).getText());

	 * flights.get(b).click(); } if

	 * (flights.get(b).getText().equalsIgnoreCase("Jet Airways") &&

	 * flight_type.equalsIgnoreCase("gds")) {

	 * //dragAndDrop1(driver,flights.get(i),1,1); JavascriptExecutor jse =

	 * (JavascriptExecutor)driver; jse.executeScript("window.scrollBy(0,250)", "");

	 * System.out.println("flight name="+flights.get(b).getText());

	 * flights.get(b).click(); } if(b>10){ break Test1; }

	 *

	 * } refreshflag=false; if (checkWarning(driver)){ return true; } else{

	 *

	 * return false; } }

	 */



	public boolean ExpediaFlightsFiletrByLCCOrGDS(RemoteWebDriver driver, String flight_type, int sectorNo)

			throws Exception {

		boolean lccFlight = false;

		List<WebElement> flights = null;

		if (waitElement(driver, getObject("Expedia_SRP_Flights_List_Prefix"), 5)) {

			flights = driver.findElements(getObject("Expedia_SRP_Flights_List_Prefix"));

		} else if (waitElement(driver, By.xpath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)

				+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix")), 5)) {

			flights = driver.findElementsByXPath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)

					+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix"));

		} else if (waitElement(driver, By.xpath("//label/span[@class='span span18 truncate']"), 5)) {

			flights = driver.findElements(By.xpath("//label/span[@class='span span18 truncate']"));

		} else {

			printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.");

			assertTrue("Failure!", false);

		}

		// SearchResult.count = 0;

		// remove first element, i.e. "show all airlines"



		for (WebElement flight : flights) {

			String flight_name = flight.getText();

			// if(flight_name.equalsIgnoreCase("tiger airways") ||

			// flight_name.equalsIgnoreCase("spicejet")) {

			// flight.findElement(By.xpath(param.get("flight_checkbox"))).click();

			// continue;

			// }

			lccFlight = isLCCFlight(driver, flight_name);

			if ((!lccFlight && flight_type.equalsIgnoreCase("lcc"))

					|| (lccFlight && flight_type.equalsIgnoreCase("gds")))

				flight.findElement(getObject("Expedia_SRP_Flights_Checkbox")).click();

			if (flight_name.equalsIgnoreCase("Jet Airways") && flight_type.equalsIgnoreCase("gds"))

				flight.findElement(getObject("Expedia_SRP_Flights_Checkbox")).click();

		}

		// removeAds(driver);

		if (checkWarning(driver))

			return true;

		else

			return false;

	}



	public boolean FlightXpFlightsFiletrByLCCOrGDS(RemoteWebDriver driver, String flight_type, int sectorNo)

			throws Exception {

		boolean lccFlight = false;

		List<WebElement> flights = null;

		if (waitElement(driver, getObject("Expedia_SRP_Flights_List_Prefix"), 5)) {

			flights = driver.findElements(getObject("Expedia_SRP_Flights_List_Prefix"));

		} else if (waitElement(driver, By.xpath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)

				+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix")), 5)) {

			flights = driver.findElementsByXPath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)

					+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix"));

		} else if (waitElement(driver, By.xpath("//label/span[@class='span span18 truncate']"), 5)) {

			flights = driver.findElements(By.xpath("//label/span[@class='span span18 truncate']"));

		} else {

			printInfo("Not able to locate list of available flight names.");

			addLog("Not able to locate list of available flight names.");

			assertTrue("Failure!", false);

		}

		// SearchResult.count = 0;

		// remove first element, i.e. "show all airlines"



		for (WebElement flight : flights) {

			String flight_name = flight.getText();

			// if(flight_name.equalsIgnoreCase("tiger airways") ||

			// flight_name.equalsIgnoreCase("spicejet")) {

			// flight.findElement(By.xpath(param.get("flight_checkbox"))).click();

			// continue;

			// }

			lccFlight = isLCCFlight(driver, flight_name);

			if ((!lccFlight && flight_type.equalsIgnoreCase("lcc"))

					|| (lccFlight && flight_type.equalsIgnoreCase("gds")))

				flight.findElement(getObject("Expedia_SRP_Flights_Checkbox")).click();

			if (flight_name.equalsIgnoreCase("Jet Airways") && flight_type.equalsIgnoreCase("gds"))

				flight.findElement(getObject("Expedia_SRP_Flights_Checkbox")).click();

		}

		// removeAds(driver);

		if (checkWarning(driver))

			return true;

		else

			return false;

	}



	public void ClickOnOnly(RemoteWebDriver driver, String airline) {

		System.out.println(airline);

		Actions action = new Actions(driver);

		WebElement airline1 = driver.findElement(By.xpath("//div[@class='stickyFilters']//*[contains(@value,'AI')]"));

		// div[@class='stickyFilters']//*[contains(@for,'AI')]

		JavascriptExecutor jse = driver;

		jse.executeScript("window.scrollBy(0,250)", "");

		// action.moveToElement(airline1).build().perform();

		// Above 3 statements do the mouse hove action so now mouse has been

		// hovered over the airline;

		// next is wait until is 'Only' is visible and then click

		WebDriverWait wait1 = new WebDriverWait(driver, 30);

		WebElement onlyElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(

				By.xpath("//div[@class='stickyFilters']//*[contains(@value,'AI')]/..//a[contains(@text,'Only')]")));

		onlyElement.click();



	}



	public Map<String, List<String>> NearbyAirport(RemoteWebDriver driver, ArrayList<String> origin_arl,

			ArrayList<String> dest_arl) throws Exception {

		List<WebElement> origin = null;

		List<WebElement> dest = null;

		origin = driver.findElements(getObject("AirCom_Search_NearByAirport_Origin"));

		dest = driver.findElements(getObject("AirCom_Search_NearByAirport_Destination"));



		for (int i = 0; i < origin.size(); i++) {

			int j = 0;

			j = i + 1;

			origin_arl.add(driver

					.findElement(By.xpath("//div[@data-block-type='nearbyairportsOrigin']//li[" + j + "]/label/span"))

					.getText());

		}

		for (int i = 0; i < dest.size(); i++) {

			int j = 0;

			j = i + 1;

			dest_arl.add(driver

					.findElement(By.xpath("//div[@data-block-type='nearbyairportsDest']//li[" + j + "]/label/span"))

					.getText());

		}

		Map<String, List<String>> airportMap = new HashMap<String, List<String>>();

		airportMap.put("Origin", origin_arl);

		airportMap.put("Destination", dest_arl);

		return airportMap;

	}



	/*

	 * Modified by: anil.patil@cleartrip.com Changed the javascript scrolling to

	 * scroll earlier to prevent element not clickable exception. Modified xpaths to

	 * the airline names as they had changed.

	 */

	
/*Yashmin	*/
public boolean filterFlightsByPreferedAirline11(RemoteWebDriver driver, String flight_preference, int sectorNo,String airlineCode)
			throws Exception {
		boolean prefferedFlightFound = false;
		List<WebElement> flights = null;
		String fp = flight_preference;

		if (flight_preference == null) {
			addLog("Preferred Airline not specified! Hence not filtering", true);
			return false;
		}

		if (flight_preference.equalsIgnoreCase("Airasia_india")) {
			fp = "Air Asia";
		}

		if (waitElement(driver, By.xpath(
				"//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"),
				1)) {
			flights = driver.findElements(By.xpath(
					"//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"));
		}

		for (int i = 0; i < flights.size(); i++) {
			int j = i + 1;
			// System.out.println(flights.get(i).getText());
			if ((flight_preference.equalsIgnoreCase(flights.get(i).getText()))
					|| fp.equalsIgnoreCase(flights.get(i).getText())) {
				prefferedFlightFound = true;
				/*String path = "//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span[contains(text(),'"
						+ flight_preference + "')]";*/
			//	mouseHover(driver, By.xpath(path));
//				path = "//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span[contains(text(),'"
//						+ flight_preference + "')]/../a";
				
			String	path="//nav[@class='airlines']/ul/li/label[contains(@for,'"+airlineCode.toUpperCase().trim()+"')]/a";
				mouseHoverClick(driver, By.xpath(path));
				addLog("Preffered flight: " + flight_preference + " found and filtered!", true);
				break;

			}
		}

		JavascriptExecutor jse = driver;
		jse.executeScript("window.scrollBy(0,-600)", "");
		Thread.sleep(1000);

		if (!prefferedFlightFound) {
			addLog("Preferred flight: " + flight_preference + " not found.", true);
			return true;
		}
		if (checkWarning(driver))
			return true;
		else
			return false;
	}

	

	
	public boolean filterFlightsByPreferedAirline1(RemoteWebDriver driver, String flight_preference, int sectorNo)

			throws Exception {

		boolean prefferedFlightFound = false;

		List<WebElement> flights = null;

		String fp = flight_preference;



		if (flight_preference == null) {

			addLog("Preferred Airline not specified! Hence not filtering", true);

			return false;

		}



		if (flight_preference.equalsIgnoreCase("Airasia_india")) {

			fp = "Air Asia";

		}



		if (waitElement(driver, By.xpath(

				"//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"),

				1)) {

			flights = driver.findElements(By.xpath(

					"//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"));

		}



		for (int i = 0; i < flights.size(); i++) {

			int j = i + 1;

			// System.out.println(flights.get(i).getText());

			if ((flight_preference.equalsIgnoreCase(flights.get(i).getText()))

					|| fp.equalsIgnoreCase(flights.get(i).getText())) {

				prefferedFlightFound = true;

				String path = "//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span[contains(text(),'"

						+ flight_preference + "')]";

				mouseHover(driver, By.xpath(path));

				path = "//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span[contains(text(),'"

						+ flight_preference + "')]/../a";

				mouseHoverClick(driver, By.xpath(path));

				addLog("Preffered flight: " + flight_preference + " found and filtered!", true);

				break;



			}

		}



		JavascriptExecutor jse = driver;

		jse.executeScript("window.scrollBy(0,-600)", "");

		Thread.sleep(1000);



		if (!prefferedFlightFound) {

			addLog("Preferred flight: " + flight_preference + " not found.", true);

			return true;

		}

		if (checkWarning(driver))

			return true;

		else

			return false;

	}



	public void metaFareRule(RemoteWebDriver driver) throws Exception {

		String fareprice = "";

		String fareprice1 = "";

		try {

			if (elementPresent(driver, getObject("AirCom_SRP_Meta_close"))) {

				safeClick(driver, getObject("AirCom_SRP_Meta_close"));

				// driver.findElement(By.id("close")).click();

			}



		} catch (Exception e) {

			refreshPage(driver);

			// driver.navigate().refresh();

			Thread.sleep(7000);

		}

		elementPresent(driver, getObject("AirCom_SRP_FareRule_Link"), 9);

		// Thread.sleep(5000);

		// driver.findElement(By.linkText("Fare rules")).click();

		safeClick(driver, getObject("AirCom_SRP_FareRule_Link"));

		// driver.findElement(By.xpath("(//span[text()='Book via

		// Riya']/preceding-sibling::div/button[1])[1]")).click();

		elementPresent(driver, getObject("AirCom_SRP_Meta_Bookvia"));

		// driver.findElement(By.xpath("(//span[contains(text(),'Book via')])[1]"));

		elementPresent(driver, getObject("AirCom_SRP_Meta_FareTabs"), 9);

		// Thread.sleep(6000);

		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='metaFareTabs clearfix']/ul/li"));

		Thread.sleep(5000);

		System.out.println(ele.size());

		for (int i = 1; i < ele.size() + 1; i++) {

			driver.findElement(By.xpath("//div[@class='metaFareTabs clearfix']/ul/li[" + i + "]")).click();

			Thread.sleep(5000);

			fareprice = driver.findElement(By.xpath("//div[@class='metaFareTabs clearfix']/ul/li[" + i + "]/a/p/span"))

					.getAttribute("data-pr");

			addLog("FarePrice : " + fareprice);

			// fareprice1=driver.findElement(By.xpath("//strong[@class='fareBreakUpTotal']/span")).getAttribute("data-pr");

			fareprice1 = getAttribute(driver, getObject("AirCom_SRP_Meta_FareBreakup"), "data-pr");

			addLog("FarePrice1 : " + fareprice1);

			Assert.assertEquals(fareprice, fareprice1, "price mismathch in farerule");

			Thread.sleep(5000);

			if (driver.findElement(By.cssSelector("div.itineraryHeader span.status")).isDisplayed()) {

				addLog("Fare rule present");

			} else {

				addLog("error");

			}



		}

	}



	public void metaFareRule1(RemoteWebDriver driver) throws Exception {

		String fareprice = "";

		String fareprice1 = "";

		try {

			if (elementPresent(driver, By.id("close"))) {



				driver.findElement(By.id("close")).click();

			}



		} catch (Exception e) {

			driver.navigate().refresh();

			Thread.sleep(7000);

		}

		elementPresent(driver, By.linkText("Fare rules"), 9);

		// Thread.sleep(5000);

		driver.findElement(By.linkText("Fare rules")).click();

		// driver.findElement(By.xpath("(//span[text()='Book via

		// Riya']/preceding-sibling::div/button[1])[1]")).click();

		driver.findElement(By.xpath("(//span[contains(text(),'Book via')])[1]"));

		elementPresent(driver, By.xpath("//div[@class='metaFareTabs clearfix']/ul/li"), 9);

		// Thread.sleep(6000);

		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='metaFareTabs clearfix']/ul/li"));

		Thread.sleep(5000);

		System.out.println(ele.size());

		for (int i = 1; i < ele.size() + 1; i++) {

			driver.findElement(By.xpath("//div[@class='metaFareTabs clearfix']/ul/li[" + i + "]")).click();

			Thread.sleep(5000);

			fareprice = driver.findElement(By.xpath("//div[@class='metaFareTabs clearfix']/ul/li[" + i + "]/a/p/span"))

					.getAttribute("data-pr");

			System.out.println("-----------" + fareprice);

			fareprice1 = driver.findElement(By.xpath("//strong[@class='fareBreakUpTotal']/span"))

					.getAttribute("data-pr");

			System.out.println("+++++++++++++++++++++++++" + fareprice1);

			Assert.assertEquals(fareprice, fareprice1, "price mismathch in farerule");

			Thread.sleep(5000);

			if (driver.findElement(By.cssSelector("div.itineraryHeader span.status")).isDisplayed()) {

				System.out.println("Fare rule present");

			} else {

				System.out.println("error");

			}



		}

	}



	public void no_Meta_suppliers(RemoteWebDriver driver) throws Exception {

		boolean supplierfound = false;

		try {

			safeClick(driver, getObject("AirCom_SRP_Meta_close"));

			// driver.findElement(By.id("close")).click();

		} catch (Exception e) {

			refreshPage(driver);

			// driver.navigate().refresh();

			Thread.sleep(7000);

		}

		Set<String> set1 = new HashSet<String>();

		List<WebElement> sup = driver.findElements(By.xpath("(//span[contains(text(),'Book via')])"));

		if (sup.size() > 0) {

			supplierfound = true;

		}

		Assert.assertEquals(supplierfound, true, "supplier not found");

		for (int i = 1; i < sup.size(); i++) {

			String txt = sup.get(i).getText();

			set1.add(txt);

		}

		Iterator<String> i1 = set1.iterator();

		while (i1.hasNext() == true) {

			Object obj1 = i1.next();



		}

		int no = 0;

		no = set1.size();

		addLog("No of Supplier called are:" + no);

		// System.out.println("No of Supplier called are:"+no);



	}



	/*

	 * Modified by: prashanthk@cleartrip.com Changed the xpath to correctly identify

	 * the Airline name

	 */

	public boolean customAirline(RemoteWebDriver driver, String flight_preference, int sectorNo) throws Exception {

		boolean sector1 = false;

		boolean prefferedFlightFound = false;

		List<WebElement> stop = driver.findElements(By.xpath("//ul[@class='inline clearFix sectorTabs']/li"));

		test: for (int i = 0; i < stop.size(); i++) {

			// stop.get(i).getText();

			System.out.println("stop name=" + stop.get(i).getText());

			if (stop.get(i).getText().equalsIgnoreCase(sectorNo + " stop")) {

				// boolean stops_available=true;

				System.out.println("stop name=" + stop.get(i).getText());

				stop.get(i).click();



				break test;



			}

		}



		// List<WebElement>

		// we=driver.findElements(By.xpath("//div[contains(@class,'filterContent

		// clearFix')]/nav/ul/li"));

		// Fixed with new xpath below

		List<WebElement> we = driver.findElements(By.xpath("//nav[@class='airlines']/ul/li"));

		Test: for (int i = 1; i < we.size() + 1; i++) {

			// String

			// flightname=driver.findElement(By.xpath("//nav[@class='airlines']/ul/li["+i+"]/label/input/../span")).getText();

			// Fixed with new xpath below

			String flightname = driver.findElement(By.xpath("//nav[@class='airlines']/ul/li[" + i + "]/label/span"))

					.getText();

			addLog(flightname, true);

			if (flightname.equalsIgnoreCase(flight_preference)) {

				// System.out.println("enters in if loop");

				JavascriptExecutor jse = driver;

				jse.executeScript("window.scrollBy(0,600)", "");

				Thread.sleep(3000);

				WebElement moveonmenu = driver

						.findElement(By.xpath("//nav[@class='airlines']/ul/li[" + i + "]/label/span"));

				Actions action = new Actions(driver);

				// driver.manage().timeouts().implicitlyWait(5,

				// TimeUnit.SECONDS);

				action.moveToElement(moveonmenu)

						.moveToElement(

								driver.findElement(By.xpath("//nav[@class='airlines']/ul/li[" + i + "]/label/a")))

						.click().perform();



				prefferedFlightFound = true;

				break Test;

			}

		}

		/*

		 * WebElement moveonmenu = driver.findElement(By.xpath(

		 * "//div[@class='commonFilters']/div[5]/div/nav/ul/li[2]/label")); // actions

		 * .moveToElement(moveonmenu).moveToElement(driver.findElement(By.

		 * xpath("//div[@id='menu1choices']/a"))).click().perform();

		 *

		 * Actions action = new Actions(driver); //WebElement airline =

		 * driver.findElement

		 * (By.xpath("//div[@class='commonFilters']/div[5]/div/nav/ul/li[2]/label/a" ));

		 * action.moveToElement(moveonmenu).moveToElement(driver.findElement( By.xpath

		 * ("//div[@class='commonFilters']/div[5]/div/nav/ul/li[2]/label/a"

		 * ))).click().perform();

		 */

		// Above 3 statements do the mouse hove action so now mouse has been

		// hovered over the airline;

		// next is wait until is 'Only' is visible and then click

		/*

		 * WebDriverWait wait1 = new WebDriverWait(driver,30); WebElement onlyElement =

		 * wait1.until(ExpectedConditions.visibilityOfElementLocated

		 * (By.xpath("//div[@class='stickyFilters']//*[contains(text(),' "

		 * +flight_preference+" ')]/..//a[contains(text(),'Only')]")));

		 * onlyElement.click();

		 */



		/*

		 * test:for(int i=0;i<flights.size();i++){ boolean numberpresent=false;

		 * ((JavascriptExecutor)

		 * driver).executeScript("arguments[0].scrollIntoView(true);", flights.get(i));

		 * try{ System.out.println("flights text========="+flights

		 * .get(i).findElement(By.xpath("./span")).getText());

		 * flights.get(i).findElement(By.xpath("./span")).click(); Thread.sleep(10000);

		 * } catch(Exception e){

		 *

		 * } try{ if(flights.get(i).findElement(By.xpath("./span")).getText().

		 * equalsIgnoreCase("flydubai")){

		 *

		 * Actions action=new Actions(driver);

		 *

		 *

		 * try{ action.moveToElement(flights.get(i)).moveToElement(driver.findElement (

		 * By.xpath("//a[contains(text(),'Only')]"))).click().build().perform();

		 *

		 *

		 * System.out.println("completes the action"); } catch(Exception e){

		 * System.out.println("enters in catch"); }

		 *

		 * Thread.sleep(20000); } System.out.println("checking link========="+flights

		 * .get(i).findElement(By.tagName("a")).getAttribute("class")); }

		 * catch(Exception e){

		 *

		 * } }

		 */



		if (!prefferedFlightFound) {

			addLog("Preffered flight not found.", true);

			return true;

		}

		if (checkWarning(driver))

			return true;

		else

			return false;

	}



	@Override

	public boolean b2bGDSAirlines(RemoteWebDriver driver) throws Exception {

		String Air_Itinerary = getText(driver, By.xpath("//div[@id='itinerary']"));

		for (int i = 0; i < number_gds_Airlines; i++) {

			if (Air_Itinerary.contains(gds_airlines[i])) {

				B2B_GDS_Flight = Air_Itinerary.contains(gds_airlines[i]);

				i = number_gds_Airlines + 1;

			} else {

				B2B_GDS_Flight = Air_Itinerary.contains(gds_airlines[i]);

			}

		}

		return B2B_GDS_Flight;

	}



	public boolean filterFlightsByPreferedAirline(RemoteWebDriver driver, String flight_preference, int sectorNo)

			throws Exception {

		List<WebElement> flights = null;

		if (waitElement(driver, getObject("AirCom_SRP_Flights_List_Prefix"), 5)) {

			flights = driver.findElements(getObject("AirCom_SRP_Flights_List_Prefix"));

		} else if (waitElement(driver, By.xpath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)

				+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix")), 5)) {

			flights = driver.findElementsByXPath(getValue("AirCom_SRP_Flights_List_Multicity_Prefix") + (sectorNo)

					+ getValue("AirCom_SRP_Flights_List_Multicity_Suffix"));

		}



		return filterFlightsByPreferedAirlineProcess(driver, flights, flight_preference);

	}



	public boolean filterFlightsByPreferedAirlineReturn(RemoteWebDriver driver, String flight_preference, int sectorNo)

			throws Exception {

		List<WebElement> flights = null;

		Thread.sleep(1000);

		if (waitElement(driver, getObject("AirCom_SRP_Flights_List_Prefix_IntlReturn"), 10)) {

			flights = driver.findElements(getObject("AirCom_SRP_Flights_List_Prefix_IntlReturn"));

		}



		return filterFlightsByPreferedAirlineProcess(driver, flights, flight_preference);

	}



	public boolean filterFlightsByPreferedAirlineProcess(RemoteWebDriver driver, List<WebElement> flights,

			String flight_preference) throws Exception {

		boolean prefferedFlightFound = false;

		// remove first element, i.e. "show all airlines"

		flights.remove(0);

		for (WebElement flight : flights) {

			String flight_name = flight.findElement(getObject("AirCom_SRP_Flights_List_Suffix")).getText();

			System.out.println("flight name=" + flight_name);

			if ((flight_preference.equalsIgnoreCase(flight_name))) {

				JavascriptExecutor jse = driver;

				jse.executeScript("window.scrollBy(0,150)", "");

				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", flight);

				Thread.sleep(200);

				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-90)", "");

				Thread.sleep(200);

				Actions builder = new Actions(driver);

				builder.moveToElement(flight);

				builder.perform();

				Thread.sleep(200);



				// System.out.println("text to check

				// link"+driver.findElement(By.linkText("Only")).getText());



				driver.findElement(By.linkText("Only")).click();

				// flight.findElement(By.xpath("/a")).click();

				prefferedFlightFound = true;

				break;

			}

		}

		// removeAds(driver);

		if (!prefferedFlightFound) {

			System.out.println("Preffered flight not found.");

			addLog("Preffered flight not found.");

			return true;

		}

		if (checkWarning(driver))

			return true;

		else

			return false;

	}



	public boolean isLCCFlight(RemoteWebDriver driver, String flight) {

		boolean result = false;

		for (String flight_name : lcc_airlines) {

			if (flight.equalsIgnoreCase(flight_name))

				result = true;

		}

		return result;

	}



	public void removeAds(RemoteWebDriver driver) throws Exception {

		if (waitElement(driver, By.className("dismissUpsellButton"), 3))

			safeClick(driver, By.className("dismissUpsellButton"));

	}



	public void airCom_BS1_ReviewItinerary(RemoteWebDriver driver) throws Exception {

		elementVisible(driver, getObject("AirCom_BookStep1_Itinerary"), 9);

		safeClick(driver, getObject("AirCom_BookStep1_Insurance_CheckBox"));

		safeClick(driver, getObject("AirCom_BookStep1_ContinueBooking_Button"));

	}



	public void airCom_BookStep2(RemoteWebDriver driver, String text) throws Exception {

		safeType(driver, getObject("AirCom_BookStep2_EmailID_UserName"), dataFile.value("HotelEmailID"));// EmailID

		if (text.equalsIgnoreCase("SignIN")) {

			safeClick(driver, getObject("AirCom_BookStep2_SignIn_CheckBox"));

			safeType(driver, getObject("AirCom_BookStep2_EmailID_Password"), dataFile.value("HotelPassword"));// Password

			addLog("SignIN @ BookStep2");

		} else {

			addLog("SignIN not done @ BookStep2");

			printInfo("SignIN not done @ BookStep2");

		}

		safeClick(driver, getObject("AirCom_BookStep2_Continue_Button"));

	}



	public void airCom_BookStep21(RemoteWebDriver driver, String text) throws Exception {



		// safeType(driver, getObject("AirCom_SignIn_ModalWindow_Email"),

		// "kiran.kumar@cleartrip.com");

		// safeType(driver, getObject("AirCom_SignIn_ModalWindow_Password"),

		// "cleartrip");

		safeType(driver, getObject("AirCom_BookStep2_EmailID_UserName"), "kiran.kumar@cleartrip.com");// EmailID

		if (text.equalsIgnoreCase("SignIN")) {

			safeClick(driver, getObject("AirCom_BookStep2_SignIn_CheckBox"));

			safeType(driver, getObject("AirCom_BookStep2_EmailID_Password"), "cleartrip");// Password

			addLog("SignIN @ BookStep2");

		} else {

			addLog("SignIN not done @ BookStep2");

			printInfo("SignIN not done @ BookStep2");

		}

		safeClick(driver, getObject("AirCom_BookStep2_Continue_Button"));

	}



	/*

	 * public void airCom_BS_AddTravellers(RemoteWebDriver driver, String adult,

	 * String children, String infant) throws Exception { String adults = adult;

	 * num_adults = Integer.parseInt(adults); String childrens = children;

	 * num_children = Integer.parseInt(childrens); String infants = infant;

	 * num_infants = Integer.parseInt(infants); int num_of_adults = num_adults; int

	 * num_of_children = num_children; int num_of_infants = num_infants;

	 * elementNotVisible(driver, getObject("AirCom_BookStep1_Itinerary"), 20);

	 * //waitForElementToBeSelected(driver,

	 * getObject("AirCom_BookStep3_Traveller_AdultTitle")); Thread.sleep(10000);

	 * Select dropdown = new

	 * Select(driver.findElement(getObject("AirCom_BookStep3_Traveller_AdultTitle"

	 * ))); dropdown.selectByVisibleText("Mr");

	 * driver.findElement(getObject("AirCom_BookStep3_Traveller_FirstName"

	 * )).clear();

	 * driver.findElement(getObject("AirCom_BookStep3_Traveller_FirstName"

	 * )).sendKeys(dataFile.value("First_Name"));

	 * driver.findElement(getObject("AirCom_BookStep3_Traveller_LastName"

	 * )).clear();

	 * driver.findElement(getObject("AirCom_BookStep3_Traveller_LastName"

	 * )).sendKeys(dataFile.value("Last_Name"));

	 * driver.findElement(By.id("mobileNumber")).clear();

	 * driver.findElement(getObject

	 * ("AirCom_BookStep3_Traveller_MobileNo")).sendKeys

	 * (dataFile.value("Mobile_Number"));

	 * driver.findElement(getObject("AirCom_BookStep3_Continue_Button" )).click();

	 *

	 * safeSelect(driver, getObject("AirCom_BookStep3_Traveller_AdultTitle"),

	 * dataFile.value("Title")); safeType(driver,

	 * getObject("AirCom_BookStep3_Traveller_FirstName"), dataFile.value(""));

	 * safeType(driver, getObject("AirCom_BookStep3_Traveller_FirstName"),

	 * dataFile.value("First_Name")); safeType(driver,

	 * getObject("AirCom_BookStep3_Traveller_LastName"), dataFile.value(""));

	 * safeType(driver, getObject("AirCom_BookStep3_Traveller_LastName"),

	 * dataFile.value("Last_Name")); safeType(driver,

	 * getObject("AirCom_BookStep3_Traveller_MobileNo"),

	 * dataFile.value("Mobile_Number")); safeClick(driver,

	 * getObject("AirCom_BookStep3_Continue_Button")); if (!elementVisible(driver,

	 * getObject("AirCom_BookStep4_CreditCard_Tab"), 1)) { safeClick(driver,

	 * getObject("AirCom_BookStep3_Continue_Button")); } }

	 */

	@Override

	public boolean b2cGDSAirlines(RemoteWebDriver driver) throws Exception {

		String Air_Itinerary = getText(driver, getObject("AirCom_BookStep3_Traveller_itiBlock"));

		for (int i = 0; i < number_gds_Airlines; i++) {

			if (Air_Itinerary.contains(gds_airlines[i])) {

				GDS_Flight = Air_Itinerary.contains(gds_airlines[i]);

				i = number_gds_Airlines + 1;

			} else {

				GDS_Flight = Air_Itinerary.contains(gds_airlines[i]);

			}

		}

		return GDS_Flight;

	}



	public void airCom_BS_Payment(RemoteWebDriver driver, String Trip_Logger_Msg) throws Exception {

		if (MakePaymentTrue && !ProductionUrl) {

			elementVisible(driver, getObject("AirCom_BookStep4_CreditCard_Tab"), 2);

			safeClick(driver, getObject("AirCom_BookStep4_CreditCard_Tab"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"),

					dataFile.value("MasterCard_Exp_Month"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"),

					dataFile.value("MasterCard_Exp_Year"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"),

					dataFile.value("MasterCard_HolderName"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			if (textPresent(driver, "Your booking is done", 90)) {

				String SuccessMessage = getText(driver, getObject("AirCom_Confirmation_BookingDone_TextMsg"));

				AssertJUnit.assertEquals("Your booking is done", SuccessMessage);

				String TripID = getText(driver, getObject("AirCom_Confirmation_TripID"));

				addLog("TripID is " + TripID);

				printInfo("TripID is " + TripID);

				logger.info(Trip_Logger_Msg + TripID);

			} else {

				textPresent(driver, "Your booking is done", 100);

				logger.info(Trip_Logger_Msg + " Booking Confirmation page is not displayed");

				AssertJUnit.assertEquals(true, false);

			}

		}

		/*

		 * else if (MakePaymentTrue && ProductionUrl){ safeClick(driver,

		 * getObject("HotelCom_BookStep4_NetBanking_Tab")); elementVisible(driver,

		 * getObject("HotelCom_BookStep4_Netbank_DropDown"), 10); safeClick(driver,

		 * getObject("HotelCom_BookStep4_SBI_Bank_RadioButton")); Thread.sleep(5000);

		 * safeClick(driver, getObject("HotelCom_BookStep4_MakePayment_Button"));

		 * elementVisible(driver, By.xpath("//strong[2]"), 9); String TripID =

		 * getText(driver, By.xpath("//strong[2]"));

		 * logger.info("Netbanking payment retry : "+TripID); elementVisible(driver,

		 * getObject("HotelCom_BookStep4_SBI_Bank_CancelLink"), 9); Thread.sleep(5000);

		 * safeClick(driver, getObject("HotelCom_BookStep4_SBI_Bank_CancelLink"));

		 * addLog("Netbanking payment retry : "+TripID);

		 * logger.info(LoggerMsg+TripID); }

		 */

		else {

			elementVisible(driver, getObject("AirCom_BookStep4_CreditCard_Tab"), 2);

			safeClick(driver, getObject("AirCom_BookStep4_CreditCard_Tab"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"),

					dataFile.value("MasterCard_Exp_Month"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"),

					dataFile.value("MasterCard_Exp_Year"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"),

					dataFile.value("MasterCard_HolderName"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));

		}



	}



	public boolean selectingStop(String mode, RemoteWebDriver driver, int sectorNo) throws Exception {

		boolean stopsStatus = false;

		List<WebElement> stop = driver.findElements(By.xpath("//nav/ul/li"));

		test: for (int i = 0; i < stop.size(); i++) {

			// System.out.println(stop.get(i).getText());



			if (stop.get(i).getText().equalsIgnoreCase(sectorNo + " stop")) {

				// boolean stops_available=true;

				System.out.println("stop name=" + stop.get(i).getText());

				stop.get(i).click();



				break test;



			}



		}

		if (checkWarning(driver)) {

			stopsStatus = true;

		} else {

			stopsStatus = false;

		}

		return stopsStatus;

	}



	/*

	 * Author: prashanth.k@cleartrip.com Takes in sector no, constructs label and

	 * clicks on the required no of stops filter.

	 */

	public boolean selectingStop1(String mode, RemoteWebDriver driver, int sectorNo) throws Exception {

		boolean stopsStatus = false;



//		String xpath = "//label[@for = '1_1_" + sectorNo + "']";

//

//		if (elementPresent(driver, By.xpath(xpath), 25)) {

//			safeClick(driver, By.xpath(xpath));

//		}



		if (checkWarning(driver)) {

			stopsStatus = true;

		} else {

			stopsStatus = false;

		}

		return stopsStatus;

	}



	// sectorNo used for multicity, for others send any int value doesnt affect.

	public boolean flightTypeFilter(String mode, RemoteWebDriver driver, int sectorNo) throws Exception {

		By by;

		String idCheck;

		List<WebElement> stop_options;

		if (mode.equalsIgnoreCase("multicity")) {

			by = By.xpath(".//div[" + sectorNo + "]/div[2]/div/nav/ul/li");

			idCheck = "1_1_0_";

		} else {

			// by = By.name(objectRepos.value("results_stops"));

			by = getObject("results_stops");

			idCheck = "1_1_0";

		}



		boolean stops_available = elementVisible(driver, by, 10);

		String checked;

		// boolean stops_available =false;

		if (stops_available) {

			if (mode.equalsIgnoreCase("multicity")) {

				stop_options = driver.findElementsByXPath(".//div[" + sectorNo + "]/div[2]/div/nav/ul/li");

			} else {

				stop_options = driver.findElements(by);

			}

			if ((mode.equalsIgnoreCase("direct") || mode.equalsIgnoreCase("multicity"))) {

				/*

				 * if (stop_options.get(0).getAttribute("id").contains(idCheck)) {

				 * stop_options.remove(0); stop_options = Lists.reverse(stop_options); for

				 * (WebElement we : stop_options) {

				 * System.out.print(stop_options.get(0).getText()); if (we.isSelected())

				 * we.click(); } } else { addLog(

				 * "Direct flight was required but could not find any direct flight for this search. Trying with new search"

				 * ); System.out .println(

				 * "Direct flight was required but could not find any direct flight for this search. Trying with new search"

				 * ); return true; }

				 */

				checked = stop_options.get(0).findElement(By.xpath("./input")).getAttribute("checked");

				if (checked == null) {

					stop_options.get(0).findElement(By.xpath("./label")).click();

				}



				stop_options.remove(0);

				for (WebElement stop_option : stop_options) {

					checked = stop_option.findElement(By.xpath("./input")).getAttribute("checked");

					if (checked != null && checked.equals("true")) {

						stop_option.findElement(By.xpath("./label")).click();

					}

				}

			} else {

				/*

				 * if (stop_options.get(0).getAttribute("id").contains(idCheck))

				 * stop_options.get(0).click();

				 */

				/*

				 * stop_options.remove(0); for(WebElement stop_option : stop_options) {

				 * System.out.println(stop_option.getAttribute("checked"));

				 * stop_option.findElement(By.xpath("./label")).click(); }

				 */

				checked = stop_options.get(0).findElement(By.xpath("./input")).getAttribute("checked");

				if (checked != null && checked.equals("true")) {

					stop_options.get(0).findElement(By.xpath("./label")).click();

				}



				stop_options.remove(0);

				for (WebElement stop_option : stop_options) {

					checked = stop_option.findElement(By.xpath("./input")).getAttribute("checked");

					if (checked == null) {

						stop_option.findElement(By.xpath("./label")).click();

					}

				}

			}

		} else {

			addLog("No stops option were loaded. Error. Exiting now!", true);

			AssertJUnit.assertTrue(false);



		}

		if (checkWarning(driver))

			return true;

		else

			return false;

	}



	public boolean checkWarning(RemoteWebDriver driver) {

		By warning = By.className("warningMessage");

		boolean warningFound = false;

		try {

			if (waitElement(driver, warning, 2)) {

				// being too picky. Undo last filter applied

				// do {

				addLog("No results found for the specified criteria. Trying with new sectors");

				printInfo("No results found for the specified criteria. Trying with new sectors");

				warningFound = true;

				// safeClick(driver, By.xpath(param.get("warning_button")));

				// } while(waitElement(driver, warning, 2));

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return warningFound;

	}



	public WebElement pickFirstFlight(RemoteWebDriver driver) throws Exception {

		WebElement flight_picked = null;

		if (waitElement(driver, By.xpath(objectRepos.value("oneway_solution_prefix") + "[1]"), 10)) {

			// System.out.println("text="+driver.findElement(By.xpath(objectRepos.value("oneway_solution_prefix")

			// + "[1]")).getText());

			flight_picked = driver.findElement(By.xpath(objectRepos.value("oneway_solution_prefix") + "[1]"));

			return flight_picked;

		} else if (checkWarning(driver))

			return null;

		return flight_picked;

	}



	public WebElement pickFlight(RemoteWebDriver driver, int i) throws Exception {

		WebElement flight_picked = null;

		if (waitElement(driver, By.xpath(objectRepos.value("oneway_solution_prefix") + "[" + i + "]"), 10)) {

			// System.out.println("text="+driver.findElement(By.xpath(objectRepos.value("oneway_solution_prefix")

			// + "[1]")).getText());

			flight_picked = driver.findElement(By.xpath(objectRepos.value("oneway_solution_prefix") + "[" + i + "]"));

			return flight_picked;

		} else if (checkWarning(driver))

			return null;

		return flight_picked;

	}



	public WebElement pickSecondFlight(RemoteWebDriver driver) throws Exception {

		WebElement flight_picked = null;

		if (waitElement(driver, By.xpath(objectRepos.value("oneway_solution_prefix") + "[2]"), 10)) {

			// System.out.println("text="+driver.findElement(By.xpath(objectRepos.value("oneway_solution_prefix")

			// + "[1]")).getText());

			flight_picked = driver.findElement(By.xpath(objectRepos.value("oneway_solution_prefix") + "[1]"));

			return flight_picked;

		} else if (checkWarning(driver))

			return null;

		return flight_picked;

	}



	public WebElement pickFirstFlight1(RemoteWebDriver driver, int flightnumber) throws Exception {

		WebElement flight_picked = null;

		if (waitElement(driver, By.xpath(objectRepos.value("oneway_solution_prefix") + "[1]"), 10)) {

			System.out.println("text="

					+ driver.findElement(By.xpath(objectRepos.value("oneway_solution_prefix") + "[1]")).getText());

			flight_picked = driver.findElement(By.xpath(objectRepos.value("oneway_solution_prefix") + "[1]"));

			return flight_picked;

		} else if (checkWarning(driver))

			return null;

		return flight_picked;

	}



	public boolean pickFlightByCitiCode(RemoteWebDriver driver, String requiredOriginCode,

			String requiredDestinationCode) {

		/*

		 * Author: prashanth.k@cleartrip.com Takes in 2 airport codes and clicks the

		 * book button for the first available direct flight between the two cities on

		 * Search Results Page.

		 */



		boolean flightBooked = false;

		addLog("Attempting to bokk fligt between " + requiredOriginCode + " and " + requiredDestinationCode,

				true);



		List<WebElement> bookButtons = driver

				.findElements(By.xpath("//li[contains(@class,'listItem')]/table/tbody/tr[2]/td[3]/button"));

		List<WebElement> originCodes = driver

				.findElements(By.xpath("//li[contains(@class,'listItem')]/table/tbody/tr[2]/td[1]/abbr[1]"));

		List<WebElement> destinationCodes = driver

				.findElements(By.xpath("//li[contains(@class,'listItem')]/table/tbody/tr[2]/td[1]/abbr[2]"));



		ListIterator<WebElement> oit = originCodes.listIterator();

		ListIterator<WebElement> dit = destinationCodes.listIterator();

		ListIterator<WebElement> bit = bookButtons.listIterator();



		addLog("Number of Flights to be checked = " + originCodes.size() + destinationCodes.size()

				+ bookButtons.size(), true);



		while (oit.hasNext() && dit.hasNext() && bit.hasNext()) {

			String o = oit.next().getText();

			String d = dit.next().getText();

			addLog(o + " to " + d, true);

			if ((o.equalsIgnoreCase(requiredOriginCode)) && (d.equalsIgnoreCase(requiredDestinationCode))) {

				bit.next().click();

				flightBooked = true;

				addLog("Flight found and selected.", true);

				break;

			}

			bit.next();

		}

		return flightBooked;

	}



	public boolean expresswayPage(RemoteWebDriver driver) throws Exception {



		if (elementPresent_Time(driver, By.xpath("//img[@alt='Expressway']"), 30)) {

			safeType(driver, By.id("cvvCodeEc"), "123");

			safeClick(driver, By.id("paymentSubmit"));

			if (elementVisible(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 90)) {

				elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);

				safeClick(driver, getObject("air_amex_payment_button"));

			}

			return true;

		} else {

			addLog("ExpressWay Page Not Displayed", true);

		}

		return false;



	}



	public void BookIntlMC(RemoteWebDriver driver) {

		List<WebElement> we = driver.findElements(By.xpath("//button[@class='booking']"));

		we.get(1).click();

	}



	public void bookButtonDom(WebElement flight_picked) throws Exception {

		flight_picked.findElement(getObject("oneway_solution_suffix_dom")).click();

		// flight_picked.findElement(By.xpath("//button")).click();

	}



	public void bookButtonDom1(RemoteWebDriver driver, WebElement flight_picked, int flightcountnumber)

			throws Exception {

		// flight_picked.findElement(getObject("oneway_solution_suffix_dom")).click();

		List<WebElement> we = driver.findElements(By.xpath("//li/table/tbody/tr[2]/td[3]/button"));

		/*

		 * for(int i=0;i<we.size();i++){

		 * System.out.println("texts==========="+we.get(i).getText()); }

		 */

		try {

			we.get(flightcountnumber - 1).click();

		} catch (Exception e) {



		}

	}



	public void bookButtonIntl(WebElement flight_picked) throws Exception {

		try {

			flight_picked.findElement(getObject("oneway_solution_suffix_dom_intl")).click();

		} catch (NoSuchElementException e) {

			flight_picked.findElement(getObject("oneway_solution_suffix_dom_intl_meta")).click();

		}

	}



	public boolean checkFlightsCount(RemoteWebDriver driver) throws Exception {

		boolean flightsCountFlag = false;

		boolean flightsError = false;

		int counter = 0;

		// refreshing page to make sure indigo results are coming.

		// Thread.sleep(10000);

		// waitForElement(driver, 5, getObject("SRP_air_flightcount"));

		waitForElementVisibility(driver, getObject("SRP_air_flightcount"), 60);



		/*

		 * Actions action = new Actions(driver);

		 * action.sendKeys(Keys.ESCAPE).build().perform(); Thread.sleep(1000);

		 * driver.navigate().refresh();

		 */



		/* waitForElement(driver, 20, getObject("SRP_air_flightcount")); */



		try {

			while (!flightsCountFlag && !flightsError && counter < 3) {

				try {

					counter++;

					if (elementVisible(driver, getObject("SRP_air_flightcount"), 1)) {

						flightsCountFlag = true; // driver.findElement(getObject("SRP_air_flightcount")).getText().contains("of");

					}

					flightsError = elementVisible(driver, getObject("system_acting_error"), 1);

					if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {

						printInfo("System Acting up error.");

						addLog("System Acting up error.");

						assertTrue("Failure!", false);

					}

				} catch (Exception e) {

					if (elementVisible(driver, By.id("FromTag"), 1))

						flightsError = true;

					continue;

				}



			}

			if (!flightsCountFlag && !flightsError) {

				addLog("Error! Search not happening. Stuck at getting flight details!");

				printInfo("Error! Search not happening. Stuck at getting flight details!");

				return false;

			}

		} catch (Exception e) {

			return false;

		}

		if (flightsError) {

			String errorTest = getText(driver, getObject("system_acting_error"));

			if (errorTest.contains("Sorry, our system is acting up")) {

				addLog("System is acting up error has occurred. Exiting with screenshot");

				printInfo("System is acting up error has occurred. Exiting with screenshot");

				// driver.navigate().refresh();

				// checkFlightsCount();

			} else if (errorTest.contains("Sorry, there aren't any flights available for your search")) {

				addLog("No flights available for the search performed. Please try after some time.");

				printInfo("No flights available for the search performed. Please try after some time.");

			}

			return false;

		} else if (!flightsCountFlag)

			return false;

		return true;

	}



	/*

	 * Modified By: Prashanth.k@cleartrip.com This method check if filters are

	 * loaded and sees if results are displayed on the SRP.

	 */

	public boolean checkFlightsCount1(RemoteWebDriver driver) throws Exception {

		boolean flightsCountFlag = false;

		boolean flightsError = false;

		int counter = 0;



		driver.navigate().refresh();



		/*

		 * if(elementPresent(driver,By.xpath(

		 * "//div[@class='stickyFilters']//p[@class='hCount']/strong[1]"),150))

		 * System.out.println("Visible1"); if(elementVisible(driver,By.xpath(

		 * "//div[@class='stickyFilters']//p[@class='hCount']/strong[1]"),20))

		 * System.out.println("Visible2");

		 */



		if (waitForElementVisibility(driver, By.xpath(

				"//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span[contains(@class, 'span')]"),

				120))

			System.out.println("Visible");



		try {

			while (!flightsCountFlag && !flightsError && counter < 1) {

				try {

					counter++;

					if (elementVisible(driver, By.xpath(

							"//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span[contains(@class, 'span')]"),

							1)) {

						flightsCountFlag = elementPresent(driver, By.xpath(

								"//nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span[contains(@class, 'span')]"));

					}



					flightsError = elementVisible(driver, getObject("system_acting_error"), 1);



					if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {

						addLog("System Acting up error. SRP", true);

						assertTrue("Failure!", false);

					}

					Thread.sleep(1000);

				} catch (Exception e) {

					if (elementVisible(driver, By.id("FromTag"), 1))

						flightsError = true;

					continue;

				}

			}

			if (!flightsCountFlag && !flightsError) {

				addLog("Error! Search not happening. Stuck at getting flight details!", true);

				return false;

			}

		} catch (Exception e) {

			return false;

		}



		if (flightsError) {

			String errorTest = getText(driver, getObject("system_acting_error"));

			if (errorTest.contains("Sorry, our system is acting up")) {

				addLog("System is acting up error has occurred. Exiting with screenshot. SRP", true);

				safeClick(driver, By.linkText("Try searching again"));

				// driver.navigate().refresh();

				// checkFlightsCount();

			} else if (errorTest.contains("Sorry, there aren't any flights available for your search")) {

				addLog("No flights available for the search performed. Please try after some time. SRP", true);

				// clicking on try searching again takes to home page.

				// Should do research.

				safeClick(driver, By.linkText("Try searching again"));

			}

			return false;

		} else if (!flightsCountFlag)

			return false;

		return true;

	}



	public void selectBundleFare(RemoteWebDriver driver, int i) throws Exception {



		safeClick(driver, getObject("AirCom_SRP_Refundable_Flights_Checkbox"));

		List<WebElement> we = driver.findElements(By.xpath("//label[@class='bundledValue relative posCenter']"));

		we.get(i - 1).click();

		safeClick(driver, getObject("AirCom_SRP_FareRule_Link"));

		assertTrue("Fare Rules-Free Cancellation Txt Missing", textPresent(driver, "Free cancellation", 3));



		addLog("Bundle fare Selected");



	}



	public boolean ExpediaFlightsCount(RemoteWebDriver driver) {

		boolean flightsCountFlag = false;

		boolean flightsError = false;

		// refreshing page to make sure indigo results are coming.

		driver.navigate().refresh();

		try {

			while (!flightsCountFlag && !flightsError) {

				try {

					if (elementVisible(driver, getObject("Expedia_SRP_air_flightcount"), 1)) {

						flightsCountFlag = driver.findElement(getObject("Expedia_SRP_air_flightcount")).getText()

								.contains("of");

					}

					flightsError = elementVisible(driver, getObject("system_acting_error"), 1);

				} catch (Exception e) {

					if (elementVisible(driver, By.id("FromTag"), 1))

						flightsError = true;

					continue;

				}



			}

			addLog("Search URL for attempt is :" + driver.getCurrentUrl());

			printInfo("Search URL for attempt is :" + driver.getCurrentUrl());

		} catch (Exception e) {

			return false;

		}

		if (flightsError) {

			try {

				String errorTest = getText(driver, getObject("system_acting_error"));

				if (errorTest.contains("Sorry, our system is acting up")) {

					addLog("System is acting up error has occurred. Exiting with screenshot");

					printInfo("System is acting up error has occurred. Exiting with screenshot");

					safeClick(driver, By.linkText("Try searching again"));

					// driver.navigate().refresh();

					// checkFlightsCount();

				} else if (errorTest.contains("Sorry, there aren't any flights available for your search")) {

					addLog("No flights available for the search performed. Please try after some time.");

					printInfo("No flights available for the search performed. Please try after some time.");

					// clicking on try searching again takes to home page.

					// Should do research.

					safeClick(driver, By.linkText("Try searching again"));

				}

				return false;

			} catch (Exception e) {

			}

		} else if (!flightsCountFlag)

			return false;

		return true;

	}



	public void cheaperRateBlock(RemoteWebDriver driver) throws Exception {

		if (waitForElementVisibility(driver, getObject("air_price_cheaper_div"), 10)) {

			try {

				addLog("Cheaper fare Found reported! Message: "

						+ getText(driver, By.xpath("//div[@id='priceChangeDown']//p[@class='msgSS']")), true);

				safeClick(driver, getObject("air_price_cheaper_btn"));



			} catch (Exception e) {

				e.printStackTrace();

			}

		} else if (waitForElementVisibility(driver, getObject("air_price_changeup_div"), 10)) {

			try {

				addLog("Fare Change reported! Message: "

						+ getText(driver, By.xpath("//div[@id='priceChangeUp']//p[@class='msgSS']")), true);

				safeClick(driver, getObject("air_price_changeup_btn"));



			} catch (Exception e) {

				e.printStackTrace();

			}

		}

	}



	public boolean checkIfFailAfterBookButton(RemoteWebDriver driver) throws Exception {

		int tryAgain = 0;

		boolean result = false;

		if (elementPresent_Time(driver, getObject("air_review_itinerary_continue"), 15)) {

			result = false;

		} else {

			while (tryAgain < 2) {

				if (waitElement(driver, getObject("AirCom_SRP_Modify_Search_Button"), 1)) {

					result = true;

					break;

				} else if (elementVisible(driver, getObject("air_review_itinerary_continue"), 1)) {

					result = false;

					break;

				} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {

					printInfo("System Acting up error.");

					addLog("System Acting up error.");

					result = true;

					break;

				} else if (waitElement(driver, By.xpath("//div/section/div/div/h1"), 1)) {

					if (driver.findElement(By.xpath("//div/section/div/div/h1")).getText()

							.equalsIgnoreCase("Whatever you're looking for, isn't here")) {

						safeClick(driver, By.xpath("//span[@id='flightStat']/button"));

						result = true;

					}

				}

				tryAgain++;

			}

		}

		return false;

	}



	public void metaSupplier(RemoteWebDriver driver) throws Exception {

		boolean x = false;

		try {

			driver.findElement(By.id("close")).click();

		} catch (Exception e) {

			refreshPage(driver);

			// driver.navigate().refresh();

			waitForElement(driver, 90, getObject("AirCom_SRP_Oneway_BookButton"));

		}

		List<WebElement> book1 = driver.findElements(

				By.xpath("//li[contains(@class,'listItem')]/table/tbody[2]/tr[2]/td[3]/div/button[2]/span"));

		System.out.println("Entered the loop");

		for (int i = 0; i < book1.size(); i++) {

			if ((book1.get(i).findElement(By.xpath("./../../button[1]/../../span")).getText().contains("Riya"))) {

				System.out.println("------------------"

						+ book1.get(i).findElement(By.xpath("./../../button[1]/../../span")).getText());

				book1.get(i).click();

				System.out.println("Book Suppler selected is:"

						+ book1.get(i).findElement(By.xpath("./../../button[1]/../../span")).getText());

				List<WebElement> we = driver.findElements(By

						.xpath("//li[contains(@class,'listItem')]/table/tbody[2]/tr[2]/td[3]/div/div/div/li/a/div[1]"));

				for (int j = 0; j < we.size(); j++) {

					if ((we.get(j).getText().contains("Riya")) || (we.get(j).getText().contains("Ezeego"))) {

						we.get(j).click();

						book1.get(i).findElement(By.xpath("./../../button[1]")).click();

						// System.out.println("Suppler selected is:"+we.get(j).getText());

						x = true;

						break;

					}

					// System.out.println(we.get(j).getText());

				}

				if (x)

					break;

			}

		}



	}



	public boolean addMealButtonOW(RemoteWebDriver driver) throws Exception {

		if (elementNotVisible(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"), 5)) {

			printInfo("Onward Flight With No Meal Found");

			return false;

		}

		return true;

	}



	public boolean addMealButton(RemoteWebDriver driver) throws Exception {

		if (elementVisible(driver, getObject("AirCom_BookStep_AddMealBtn"), 2)) { // check here add meal button avai

			printInfo("Onward Flight With Meal Found");

			return true;

		} else if (elementVisible(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"), 1)) {

			printInfo("Onward Flight With Meal Found");

			return true;

		}

		return false;

	}



	public LinkedList<HashMap<String, String>> captureItineraryData(RemoteWebDriver driver) throws Exception {

		LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();

		List<WebElement> all_flights = driver.findElementsByXPath(getValue("all_connecting_flights"));

		for (WebElement each_flight : all_flights) {

			// iterate over each and push data to HashMap and add HashMap to

			// linked list

			HashMap<String, String> flightMap = new HashMap<String, String>();

			// push flight number

			String flight_number = each_flight.findElement(getObject("flight_number_suffix")).getText();

			flightMap.put("flight_number", flight_number);

			// push source, destination

			String source = each_flight.findElement(getObject("itin_source")).getText().split(",")[1].trim();

			flightMap.put("source", source);

			String destination = each_flight.findElement(getObject("itin_dest")).getText().split(",")[1].trim();

			flightMap.put("destination", destination);

			// push source and destination code

			String source_code = each_flight.findElement(getObject("itin_source_code")).getText().split(",")[0].trim();

			flightMap.put("source_code", source_code);

			String destination_code = each_flight.findElement(getObject("itin_dest_code")).getText().split(",")[0]

					.trim();

			flightMap.put("destination_code", destination_code);

			// push airport names

			String source_airport_name = each_flight.findElement(getObject("itin_source")).getText().split(",")[0]

					.trim();

			flightMap.put("source_airport_name", source_airport_name);

			String destination_airport_name = each_flight.findElement(getObject("itin_dest")).getText().split(",")[0]

					.trim();

			flightMap.put("destination_airport_name", destination_airport_name);

			// push duration and arr/dep date

			String duration = each_flight.findElement(getObject("itin_duration")).getText().trim();

			flightMap.put("duration", duration);

			String dep_date = each_flight.findElement(getObject("itin_dep_date")).getText().trim();

			flightMap.put("dep_date", dep_date);

			String arr_date = each_flight.findElement(getObject("itin_arr_date")).getText().trim();

			flightMap.put("arr_date", arr_date);

			// push arr/dep timings

			String dep_time = each_flight.findElement(getObject("itin_dep_time")).getText().trim();

			flightMap.put("dep_time", dep_time);

			String arr_time = each_flight.findElement(getObject("itin_arr_time")).getText().trim();

			flightMap.put("arr_time", arr_time);

			// push HashMap to Link list

			assertionLinkedList.add(flightMap);

		}

		String[] reviewItineraryURL = driver.getCurrentUrl().split("/");

		addLog("Itinerary Id : " + reviewItineraryURL[reviewItineraryURL.length - 2]);

		// Get the Itinerary id from the Review Itinerary Page



		return assertionLinkedList;

	}



	/*

	 * public void insuranceBlock(RemoteWebDriver driver, boolean insuranceRequired)

	 * throws Exception { boolean bundleFair = false;

	 * waitForElement(driver,20,getObject("air_review_itinerary_continue"));

	 * addLog("itinerary ID="+driver.getCurrentUrl());

	 * elementClickable(driver, By.id("coupon"), 30);

	 * addLog("itinerary ID="+driver.getCurrentUrl()); if

	 * (elementPresent_Time(driver,

	 * getObject("air_review_itinerary_insurance_provider_bundleFair"), 1)) {

	 * bundleFair = true; } else if (elementPresent_Time(driver,

	 * getObject("air_review_itinerary_no_terms"), 1)) { bundleFair = false; } else

	 * { assertTrue("Insurance not found. Error! Failure!", false); }

	 *

	 * if (!bundleFair) { if (insuranceRequired) { boolean insurance_present =

	 * elementVisible(driver, getObject("air_review_itinerary_insurance_provider"),

	 * 1); if (insurance_present) { if

	 * (!driver.findElement(getObject("air_review_itinerary_no_terms"

	 * )).isSelected()) { safeClick(driver,

	 * getObject("air_review_itinerary_no_terms")); } safeClick(driver,

	 * getObject("air_review_itinerary_terms"));

	 * System.out.println("Insurance selected.");

	 * addLog("Insurance selected."); } } else { if (isElementPresent(driver,

	 * getObject("air_review_itinerary_no_terms"))) { if

	 * (driver.findElement(getObject("air_review_itinerary_no_terms")).isSelected

	 * ()) { safeClick(driver, getObject("air_review_itinerary_no_terms"));

	 * Thread.sleep(500); } } } } else { if (insuranceRequired) { safeClick(driver,

	 * getObject("air_review_itinerary_insurance_provider_bundleFair"));

	 * Thread.sleep(1000); // safeClick(driver, By.id("modalInsuranceSelect"));

	 * safeClick(driver, By.id("modalInsuranceConfirm")); safeClick(driver,

	 * By.id("addInsuranceToTrip")); System.out.println("Insurance selected.");

	 * addLog("Insurance selected."); } else { // do nothing, already

	 * unselected. } } printInfo("Review itinerary loaded");

	 *

	 * safeClick(driver, getObject("air_review_itinerary_continue")); }

	 */



	public void selectingAllAddons(RemoteWebDriver driver) throws InterruptedException, Exception {

		try {



			if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"), 1)) {

				List<WebElement> we = driver.findElements(By.xpath("//button[@class='action selectAddonButton']"));

				Test: for (int i = 0; i < we.size(); i++) {

					// System.out.println(we.get(i).getText());

					if (we.get(i).getText().endsWith("Meals")) {

						we.get(i).click();

						break Test;

					}

				}

				// safeClick(driver,

				// getObject("AirCom_BookStep_AddMealBtn_Bundle"));

				Thread.sleep(3000);

				driver.switchTo().frame("modal_window");

			} else if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn"), 1)) {

				safeClick(driver, getObject("AirCom_BookStep_AddMealBtn"));

				Thread.sleep(10000);

				driver.switchTo().frame("modal_window");

			}



			assertTrue("Meals window not opened",

					driver.findElement(By.cssSelector("h2")).getText().contains("Meals for"));



			// select baggage

			Actions action = new Actions(driver);



			WebElement we1 = driver.findElementByCssSelector("a.row.selectAddonListItem");

			action.moveToElement(we1).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem"))

					.click().build().perform();



			Thread.sleep(2000);

			assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

			driver.findElementByXPath("//input[@value='Done']").click();

			Thread.sleep(1000);

			addLog("Meals Added");

			// System.out.println("Meals Added");



			driver.switchTo().defaultContent();

		} catch (Exception e) {

			addLog("Meals  not found", true);

		}

		try {

			waitElement(driver, getObject("AirCom_BookStep_AddBaggageBtn1"), 60);

			safeClick(driver, getObject("AirCom_BookStep_AddBaggageBtn"));

			Thread.sleep(2000);

			driver.switchTo().frame("modal_window");



			assertTrue("baggage window not opened",

					driver.findElement(By.cssSelector("h2")).getText().contains("Baggage for"));



			// select baggage

			Actions action = new Actions(driver);



			WebElement we = driver.findElementByCssSelector("a.row.selectAddonListItem");

			action.moveToElement(we).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click()

					.build().perform();



			Thread.sleep(2000);

			assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

			driver.findElementByXPath("//input[@value='Done']").click();

			Thread.sleep(3000);

			addLog("Baggage Added");

		} catch (Exception e) {

			addLog("Unable to add baggage, trying again");



		}



		SeatSelect_OW(driver);



	}



	public void insuranceBlock(RemoteWebDriver driver, boolean insuranceRequired) throws Exception {

		if (elementVisible(driver, By.id("priceChangeDownBtn"), 2)) {

			safeClick(driver, By.id("priceChangeDownBtn"));

		}

		if (elementVisible(driver, By.id("priceChangeUpBtn"), 2)) {

			safeClick(driver, By.id("priceChangeUpBtn"));

		}

		// Scroll Manually

		JavascriptExecutor js1 = driver;

		js1.executeScript("window.scrollBy(0,100)", "");



		insuranceBlockProcess(driver, insuranceRequired);

		scrollToElement(driver, getObject("air_review_itinerary_continue"));

		safeClick(driver, getObject("air_review_itinerary_continue"));

	}



	public void insuranceBlockProcess(RemoteWebDriver driver, boolean insuranceRequired) throws Exception {

		if (elementVisible(driver, By.id("priceChangeDownBtn"), 2)) {

			safeClick(driver, By.id("priceChangeDownBtn"));

		}



		boolean IsInsuranceApplicable = true;

		boolean bundleFair = false;

		waitForElement(driver, 20, getObject("air_review_itinerary_continue"));

		elementClickable(driver, By.id("coupon"), 30);



		if (elementPresent_Time(driver, getObject("air_review_itinerary_insurance_provider_bundleFair"), 1)) {

			bundleFair = true;

		} else if (elementPresent_Time(driver, getObject("air_review_itinerary_no_terms"), 1)) {

			bundleFair = false;

		} else {

			if (insuranceRequired) {

				assertTrue("Insurance not found. Error! Failure!", false);

			} else {

				IsInsuranceApplicable = false;

			}

		}



		if (IsInsuranceApplicable) {

			if (!bundleFair) {

				if (insuranceRequired) {

					boolean insurance_present = elementVisible(driver,

							getObject("air_review_itinerary_insurance_provider"), 1);

					if (insurance_present) {

						if (!driver.findElement(getObject("air_review_itinerary_no_terms")).isSelected()) {

							safeClick(driver, getObject("air_review_itinerary_no_terms"));

						}

						safeClick(driver, getObject("air_review_itinerary_terms"));

						addLog("Insurance selected.", true);

					} else {

						addLog("Insurance is not Present!", true);

						String curURL = driver.getCurrentUrl();

						Assert.assertTrue(curURL.contains(".ae") || curURL.contains(".sa") || curURL.contains("kw.")

								|| curURL.contains("bh.") || curURL.contains("om.") || curURL.contains("me.")

								|| curURL.contains("qa."), "Insurance not found!");

					}

				} else {

					boolean insurance_present = elementVisible(driver,

							getObject("air_review_itinerary_insurance_provider"), 1);

					if (insurance_present) {

						if (driver.findElement(getObject("air_review_itinerary_no_terms")).isSelected()) {

							safeClick(driver, getObject("air_review_itinerary_no_terms"));

						}

						addLog("Insurance is not selected.", true);

					} else {

						addLog("Insurance is not Present!", true);

						String curURL = driver.getCurrentUrl();

						Assert.assertTrue(curURL.contains(".ae") || curURL.contains(".sa") || curURL.contains("kw.")

								|| curURL.contains("bh.") || curURL.contains("om.") || curURL.contains("me.")

								|| curURL.contains("qa."), "Insurance not found!");

					}

				}

			} else {

				if (insuranceRequired) {

					if (elementPresent(driver, getObject("air_review_itinerary_insurance_provider_bundleFair"), 2)) {

						safeClick(driver, getObject("air_review_itinerary_insurance_provider_bundleFair"));

						Thread.sleep(1000);



						// safeClick(driver, By.id("modalInsuranceSelect"));

						safeClick(driver, By.id("modalInsuranceConfirm"));

						safeClick(driver, By.id("addInsuranceToTrip"));

						System.out.println("Insurance selected.");

						addLog("Insurance selected.");

					}

				} else {

					safeClick(driver, By.id("modalInsuranceConfirm"));

					// do nothing, already unselected.

				}

			}

			addLog("Review itinerary loaded", true);



		} else {

			addLog("Insurance not applicable for this sector", true);

		}

	}



	public HashMap<String, Double> priceLockAmountAssertionInBookStep(RemoteWebDriver driver,

			HashMap<String, Double> hp) throws Exception {



		Thread.sleep(2000);

		String selectedLockFeeString = getAttribute(driver, getObject("AirCom_BookStep_PriceLock_rBtn"), "value");

		String selectedBalanceString = getAttribute(driver, getObject("AirCom_BookStep_PriceLock_rBtn"),

				"data-balance-amount");

		// converting string to double type

		Double selectedLockFee = Double.parseDouble(selectedLockFeeString);

		Double selectedBalance = Double.parseDouble(selectedBalanceString);



		// getting values of "Total" Block shown in BookStep-1

		String lockfeeString = getText(driver, getObject("AirCom_BookStep_PriceLock_lockfee"));

		String balanceString = getText(driver, getObject("AirCom_BookStep_PriceLock_balance"));

		String totalPayString = getText(driver, getObject("AirCom_BookStep_PriceLock_totalPay"));



		lockfeeString = lockfeeString.replaceAll(",", "");

		balanceString = balanceString.replaceAll(",", "");

		totalPayString = totalPayString.replaceAll(",", "");



		Double lockfee = Double.parseDouble(lockfeeString);

		Double balance = Double.parseDouble(balanceString);

		Double totalPay = Double.parseDouble(totalPayString);



		/*

		 * System.out.println("selectedLockFee - "+selectedLockFee);

		 * System.out.println("selectedBalance - "+selectedBalance);

		 * System.out.println("lockfee - "+lockfee);

		 * System.out.println("balance - "+balance);

		 * System.out.println("totalPay - "+totalPay);

		 */



		hp.put("lockfee", lockfee);

		hp.put("balance", balance);



		Assert.assertEquals(selectedLockFee, lockfee,

				"Mismatch in Lock Fee Amounts in 'Booking options' and 'Total Block' in BookStep-1");

		addLog("Verified Lock Fee Amounts in 'Booking options' and 'Total Block' in BookStep-1");



		Assert.assertEquals(selectedBalance, balance,

				"Mismatch in Balance Amount in 'Booking options' and 'Total Block' in BookStep-1");

		addLog("Verified Balance Amounts in 'Booking options' and 'Total Block' in BookStep-1");



		Assert.assertEquals((lockfee + balance), totalPay,

				"Sum of Lock Fee and Balance not adding up to 'Total' in BookStep-1");

		addLog("lockfee + balance not adding up with Total shown in BookStep-1");



		return hp;

	}



	public void expresswayInsurance(RemoteWebDriver driver, boolean insuranceRequired) throws Exception {

		if (insuranceRequired) {

			safeClick(driver, getObject("air_review_itinerary_insurance_provider_bundleFair"));

			Thread.sleep(1000);

			// safeClick(driver, By.id("modalInsuranceSelect"));

			safeClick(driver, By.id("modalInsuranceConfirm"));

			safeClick(driver, By.id("addInsuranceToTrip"));

			System.out.println("Insurance selected.");

			addLog("Insurance selected.");

		} else {

			if (isElementPresent(driver, getObject("air_review_itinerary_no_terms"))) {

				if (driver.findElement(getObject("air_review_itinerary_no_terms")).isSelected()) {

					safeClick(driver, getObject("air_review_itinerary_no_terms"));

					Thread.sleep(500);

				}

			}

		}

	}



	public void selectAddons(RemoteWebDriver driver) throws Exception {

		if (elementPresent_Time(driver, By.xpath("//span[@class='insurance beforeInsurance']"), 1)) {

			safeClick(driver, By.xpath("//span[@class='insurance beforeInsurance']"));

			// driver.switchTo().frame("modal_window");

			safeClick(driver, By.id("modalInsuranceConfirm"));

			safeClick(driver, By.id("addInsuranceToTrip"));



			assertTrue("BundleFare Insurance Text Missing", textPresent(driver, "free cancellation", 1));

			System.out.println("Insurance selected.");

			addLog("Insurance selected.");



		}



		if (elementVisible(driver, By.xpath("//span[@id='beforeMeals']"), 1)) {

			safeClick(driver, By.id("beforeMeals"));

			Thread.sleep(5000);

			driver.switchTo().frame("modal_window");

			if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn"), 1)) {

				safeClick(driver, getObject("AirCom_BookStep_AddMealBtn"));

				Thread.sleep(10000);

				driver.switchTo().frame("modal_window");

			}



			assertTrue("Meals window not opened",

					driver.findElement(By.cssSelector("h2")).getText().contains("Meals for"));



			// select meal

			Actions action = new Actions(driver);



			WebElement we1 = driver.findElementByCssSelector("a.row.selectAddonListItem");

			action.moveToElement(we1).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem"))

					.click().build().perform();



			Thread.sleep(2000);

			assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

			driver.findElementByXPath("//input[@value='Done']").click();

			Thread.sleep(1000);

			addLog("Meals Added");

			System.out.println("Meals Added");



			driver.switchTo().defaultContent();



		}

		if (elementVisible(driver, By.xpath("//span[@id='beforeBaggage']"), 1)) {

			safeClick(driver, By.id("beforeBaggage"));

			Thread.sleep(5000);

			driver.switchTo().frame("modal_window");



			assertTrue("baggage window not opened",

					driver.findElement(By.cssSelector("h2")).getText().contains("Baggage for"));



			Actions action = new Actions(driver);



			WebElement we = driver.findElementByCssSelector("a.row.selectAddonListItem");

			action.moveToElement(we).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click()

					.build().perform();



			Thread.sleep(2000);

			assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

			driver.findElementByXPath("//input[@value='Done']").click();

			Thread.sleep(3000);

			addLog("Baggage Added");

			System.out.println("Baggage Added");



			Thread.sleep(2000);

			driver.switchTo().parentFrame();



		}

		if (elementPresent_Time(driver, By.xpath("//span[@id='beforeSeats']"), 1)) {

			// safeClick(driver,By.id("beforeSeats"));



		}

		printInfo("Review itinerary loaded");

		safeClick(driver, getObject("air_review_itinerary_continue"));



	}



	public void couponData(RemoteWebDriver driver, int x) throws Exception {

		driver.findElement(By.cssSelector("#step1TotalFare > small > a.weak")).click();

		// driver.findElement(By.cssSelector("small > a.weak")).click();

		driver.switchTo().frame("modal_window");

		Thread.sleep(15000);

		if (x == 1) {

			// System.out.println("+++++++"+driver.findElement(By.id("rtTotalAmount")).getText());

			// System.out.println("1=="+driver.findElement(By.id("rtTotalAmount")).getText().split("

			// ")[1]);

			// System.out.println("2==="+driver.findElement(By.id("rtTotalAmount")).getText().split("

			// ")[2]);

			insuranceDetails.put("totalwithoutcoupon",

					driver.findElement(By.id("rtTotalAmount")).getText().split(" ")[1].replace(",", ""));

			driver.navigate().refresh();

			Thread.sleep(15000);

		} else {

			// System.out.println("+++++++"+driver.findElement(By.id("rtTotalAmount")).getText());

			// System.out.println("1=="+driver.findElement(By.id("rtTotalAmount")).getText().split("

			// ")[1]);

			// System.out.println("2==="+driver.findElement(By.id("rtTotalAmount")).getText().split("

			// ")[2]);

			insuranceDetails.put("totalwithcoupon",

					driver.findElement(By.id("rtTotalAmount")).getText().split(" ")[1].replace(",", ""));

			// System.out.println("++++++++++++"+driver.findElement(By.xpath(".//*[@id='coupondfee']")).getText());

			// System.out.println("------------------"+driver.findElement(By.xpath(".//*[@id='coupondfee']")).getText().replace("-",""));

			insuranceDetails.put("coupondiscount", driver.findElement(By.xpath(".//*[@id='coupondfee']")).getText());



			driver.navigate().refresh();

			Thread.sleep(15000);

			// System.out.println(insuranceDetails.get("coupondiscount"));

			// System.out.println(Integer.parseInt(insuranceDetails.get("totalwithoutcoupon")));

			// System.out.println(Integer.parseInt(insuranceDetails.get("coupondiscount")));

			int y = Integer.parseInt(insuranceDetails.get("totalwithoutcoupon"));

			int z = Integer.parseInt(insuranceDetails.get("coupondiscount"));

			int totaladdingcoupon = y - z;

			int itinerarytotal = Integer.parseInt(insuranceDetails.get("totalwithcoupon"));

			int coupon = Integer.parseInt(insuranceDetails.get("coupondiscount"));

			System.out.println("total of without coupon=" + y + "  Itinerary total=" + itinerarytotal

					+ "  coupon amount =" + coupon);

			addLog("totai with out coupon=" + y + "   Itinerary total=" + itinerarytotal + "  coupon amount ="

					+ coupon);

			Assert.assertEquals(totaladdingcoupon, itinerarytotal,

					"coupon amount =" + coupon + "     failed due to mismatch");

		}

		/*

		 * try{ System.out.println("text in try block=="+driver.findElement(By.

		 * cssSelector("#saveMsg > strong")).getText()); } catch(Exception e){ System

		 * .out.println(""+driver.findElement(By.id("coupondfee")).getText());

		 * System.out.println("in catch"); } List<WebElement>

		 * we=driver.findElements(By.xpath("//p/strong")); for(int i=0;i<we.size();i++){

		 * System.out.println("i value="+i);

		 * System.out.println("tesxt="+we.get(i).getText()); }

		 *

		 * String mess=getText(driver, By.xpath("//p[@id='saveMsg']/strong"));

		 * System.out.println("coupon message="+mess);

		 */

		/*

		 * System.out.println(insuranceDetails.get("coupondiscount")); System.out

		 * .println(Integer.parseInt(insuranceDetails.get("totalwithoutcoupon" )));

		 * System.out.println(Integer.parseInt(insuranceDetails.get("coupondiscount"

		 * ))); int y=Integer.parseInt(insuranceDetails.get("totalwithoutcoupon")); int

		 * z=Integer.parseInt(insuranceDetails.get("coupondiscount")); int

		 * totaladdingcoupon=y-z; int itinerarytotal=Integer.parseInt(insuranceDetails

		 * .get("totalwithcoupon")); int

		 * coupon=Integer.parseInt(insuranceDetails.get("coupondiscount")); Assert

		 * .assertEquals(totaladdingcoupon,itinerarytotal,"coupon amount ="

		 * +coupon+"     failed due to mismatch");

		 */

	}



	public void ItineraryData(RemoteWebDriver driver) throws Exception {

		Thread.sleep(15000);

		// Map<String, String> insuranceDetails = new HashMap<String, String>();

		// insuranceDetails.put("insuranceamount",driver.findElement(By.id("insuranceAmount")).getText().replace(",",""));

		System.out.println("------"

				+ driver.findElement(By.xpath("//*[@id='insurancePAD']/div/dl/dd/small")).getText().split(" ")[1]);

		insuranceDetails.put("insuranceamount",

				driver.findElement(By.xpath("//*[@id='insurancePAD']/div/dl/dd/small")).getText().split(" ")[1]

						.replace(",", ""));

		safeClick(driver, By.id("insurance_box"));

		Thread.sleep(15000);

		driver.findElement(By.cssSelector("#step1TotalFare > small > a.weak")).click();

		// driver.findElement(By.cssSelector("small > a.weak")).click();

		driver.switchTo().frame("modal_window");

		Thread.sleep(15000);

		System.out.println("+++++++" + driver.findElement(By.id("rtTotalAmount")).getText());

		System.out.println("1==" + driver.findElement(By.id("rtTotalAmount")).getText().split(" ")[1]);

		// System.out.println("2==="+driver.findElement(By.id("rtTotalAmount")).getText().split("

		// ")[2]);

		insuranceDetails.put("totalwithoutinsurance",

				driver.findElement(By.id("rtTotalAmount")).getText().split(" ")[1].replace(",", ""));

		driver.navigate().refresh();

		Thread.sleep(15000);

		if (!driver.findElement(By.id("insurance_box")).getAttribute("checked").equals("true")) {

			safeClick(driver, By.id("insurance_box"));

		}

		// Thread.sleep(15000);

		driver.findElement(By.cssSelector("#step1TotalFare > small > a.weak")).click();

		// driver.findElement(By.cssSelector("small > a.weak")).click();

		Thread.sleep(15000);

		driver.switchTo().frame("modal_window");

		System.out.println("with insurance=" + driver.findElement(By.id("rtTotalAmount")).getText());

		insuranceDetails.put("totalatitinerary",

				driver.findElement(By.id("rtTotalAmount")).getText().split(" ")[1].replace(",", ""));

		driver.navigate().refresh();



		driver.switchTo().parentFrame();

		String insuranceamount2 = insuranceDetails.get("insuranceamount").split("\\.")[0];

		System.out.println(insuranceamount2);

		// int

		// insuranceamount1=Integer.parseInt((insuranceDetails.get("insuranceamount")));

		int insuranceamount1 = Integer.parseInt(insuranceamount2);

		System.out.println(

				"Total with out insurance=Rs." + Integer.parseInt(insuranceDetails.get("totalwithoutinsurance")));

		System.out.println("Total with insurance=Rs." + Integer.parseInt(insuranceDetails.get("totalatitinerary")));

		int totalwithoutinsurance1 = Integer.parseInt(insuranceDetails.get("totalwithoutinsurance")) + insuranceamount1;

		// System.out.println("total=="+totalwithoutinsurance1);

		int totalwithinsurance1 = Integer.parseInt(insuranceDetails.get("totalatitinerary"));

		System.out.println("INSURANCE AMOUNT==Rs." + Integer.parseInt(insuranceamount2));

		addLog("INSURANCE AMOUNT==Rs." + Integer.parseInt((insuranceamount2)));



		/*

		 * System.out.println("INSURANCE AMOUNT==Rs."+Integer.parseInt((

		 * insuranceDetails.get("insuranceamount"))));

		 * addLog("INSURANCE AMOUNT==Rs."

		 * +Integer.parseInt((insuranceDetails.get("insuranceamount"))));

		 */

		// System.out.println(totalwithoutinsurance1+" "+insuranceamount1+"

		// "+totalwithinsurance1);



		Assert.assertEquals(totalwithoutinsurance1, totalwithinsurance1,

				"insurance amount =" + insuranceamount1 + "     failed due to mismatch");

		// return insuranceDetails;

	}



	public void ItineraryData1(RemoteWebDriver driver, boolean insuranceRequired) throws Exception {

		Thread.sleep(10000);

		elementClickable(driver, By.id("coupon"), 30);

		if (!insuranceRequired) {

			if (elementPresent(driver, By.id("insurance_box"), 1)) {

				safeClick(driver, By.id("insurance_box"));

			}

		}

		// driver.findElement(By.cssSelector("small > a.weak")).click();

		driver.findElement(By.cssSelector("#step1TotalFare > small > a.weak")).click();



		Thread.sleep(10000);

		try {

			driver.switchTo().frame("modal_window");

		} catch (Exception E) {

			driver.findElement(By.xpath("//a[contains(@class, 'weak fareDetailsLink')]")).click();

			Thread.sleep(5000);

			driver.switchTo().frame("modal_window");

		}



		// driver.switchTo().frame("modal_window");

		Thread.sleep(3000);

		addLog("itinerary amount=" + driver.findElement(By.id("rtTotalAmount")).getText(), true);



		try {

			insuranceDetails.put("totalatitinerary",

					driver.findElement(By.id("rtTotalAmount")).getText().split("\\.")[1].replaceAll(",", ""));



			/*

			 * insuranceDetails.put( "totalatitinerary",

			 * driver.findElement(By.id("rtTotalAmount")).getText()

			 * .split("\\.")[1].replace(",", ""));

			 */} catch (Exception e) {

			insuranceDetails.put("totalatitinerary",

					driver.findElement(By.id("rtTotalAmount")).getText().split(" ")[1]);

		}

		System.out.println("--------------------" + insuranceDetails.get("totalatitinerary"));

		// driver.findElement(By.xpath("//a[@id='close']")).click();

		driver.navigate().refresh();

		driver.switchTo().parentFrame();

		// safeClick(driver,By.id("insurance_box"));

	}



	public String flexidata(RemoteWebDriver driver, boolean insuranceRequired) throws Exception {

		elementClickable(driver, By.id("coupon"), 30);

		if (!insuranceRequired) {

			safeClick(driver, By.id("insurance_box"));

		}

		// driver.findElement(By.cssSelector("small > a.weak")).click();

		driver.findElement(By.cssSelector("#step1TotalFare > small > a.weak")).click();



		Thread.sleep(15000);

		driver.switchTo().frame("modal_window");



		addLog("itinerary amount=" + driver.findElement(By.id("rtTotalAmount")).getText());

		System.out.println("itinerary amount=" + driver.findElement(By.id("rtTotalAmount")).getText());

		String itineraryamount = driver.findElement(By.id("rtTotalAmount")).getText();

		insuranceDetails.put("totalatitinerary",

				driver.findElement(By.id("rtTotalAmount")).getText().split(" ")[1].replace(",", ""));

		// driver.findElement(By.xpath("//a[@id='close']")).click();

		driver.navigate().refresh();

		driver.switchTo().parentFrame();

		return itineraryamount;

		// safeClick(driver,By.id("insurance_box"));

	}



	public void srpcheck(RemoteWebDriver driver, String srpamount) {

		String itinerarytotal;

		String srp;

		System.out.println("****" + srpamount);

		if (srpamount.contains("Rs")) {

			srp = srpamount.split("\\.")[1].replace(",", "");

			// System.out.println("+++++++++"+srp.replace(",",""));

		} else {

			srp = srpamount;



		}



		if (insuranceDetails.get("totalatitinerary").contains("Rs")) {

			itinerarytotal = insuranceDetails.get("totalatitinerary").split("\\.")[1];

			System.out.println("----" + itinerarytotal.replace("\\,", ""));

		} else {

			itinerarytotal = insuranceDetails.get("totalatitinerary");

		}

		System.out.println(itinerarytotal);

		System.out.println("srp amount=" + srp);

		addLog("itinerary=" + itinerarytotal);

		addLog("---" + "srp amount=" + srp);

		System.out.println("final iti" + itinerarytotal);

		Assert.assertEquals(Integer.parseInt(srp), Integer.parseInt(itinerarytotal));



	}



	public HashMap<String, String> durationCheck(RemoteWebDriver driver, String URI, HashMap<String, String> hp,

			String from, String to, int attempt) throws Exception {

		boolean flightCountFailure = false;

		driver.get("https://" + URI + ".cleartrip.com/");

		airCom_HomepageSearch_Oneway(driver, from, to, "10", "1", "0", "0", "Indigo", attempt);

		addLog("Search URL for attempt is :" + driver.getCurrentUrl());

		System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());

		flightCountFailure = checkFlightsCount(driver);

		List<WebElement> we = driver.findElements(By.xpath("//a[@linkidentifier='itinerary']"));

		we.get(0).click();

		Thread.sleep(1000);

		String duration = driver

				.findElement(By.xpath(

						"//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/div/ul/li[1]/div/ul/li[3]/abbr"))

				.getText();



		String flightDetails = driver.findElement(By.xpath(

				".//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/div/ul/li[1]/div/ul/li[1]/div[2]/small[1]"))

				.getText();

		if (URI.equalsIgnoreCase("beta")) {

			Thread.sleep(1000);

			hp.put("betaduration", duration);

			Thread.sleep(1000);

			hp.put("betaflightdetails", flightDetails);

			// hp.put("betatabssize",Integer.toString(we3.size()));

		} else {

			Thread.sleep(1000);

			hp.put("prodduration", duration);

			Thread.sleep(1000);

			hp.put("prodflightdetails", flightDetails);

			// hp.put("prodtabssize",Integer.toString(we3.size()));

		}

		return hp;

	}



	public HashMap<String, String> srptCheck(RemoteWebDriver driver, String URI, HashMap<String, String> hp,

			String from, String to, int attempt) throws Exception {

		boolean flightCountFailure = false;

		driver.get("https://" + URI + ".cleartrip.com/");

		airCom_HomepageSearch_RoundTrip(driver, from, to, "10", "12", "1", "0", "0", "", attempt);

		addLog("Search URL for attempt is :" + driver.getCurrentUrl());

		System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());

		flightCountFailure = checkFlightsCount(driver);

		List<WebElement> we3 = driver.findElements(By.xpath("//*[contains(@class,'comboItem')]"));

		if (URI.equalsIgnoreCase("beta")) {

			hp.put("betatabssize", Integer.toString(we3.size()));

		} else {

			hp.put("prodtabssize", Integer.toString(we3.size()));

		}



		System.out.println("size==" + we3.size());

		for (int j1 = 0; j1 < we3.size(); j1++) {

			// System.out.println(we3.get(j1).getAttribute("data-actual-price"));

			// b=j1+1;

			we3.get(j1).click();

			// System.out.println("prodtabhighlightedamount"+j1);

			if (URI.equalsIgnoreCase("beta")) {

				hp.put("betatabhighlightedamount" + j1, we3.get(j1).getAttribute("data-actual-price"));

			} else {

				hp.put("prodtabhighlightedamount" + j1, we3.get(j1).getAttribute("data-actual-price"));

			}



			String src = from.toString().toUpperCase();

			String dest = to.toString().toUpperCase();



			String OnwardFlightNumber = driver

					.findElement(By.xpath(".//*[@id='flightForm']/section[2]/div[3]/div[2]/div[1]/div/small[1]"))

					.getText();

			// System.out.println("OnwardFlightNumber=="+OnwardFlightNumber);

			addLog("OnwardFlightNumber==" + OnwardFlightNumber);

			Thread.sleep(1000);

			if (URI.equalsIgnoreCase("beta")) {

				hp.put("betaOnwardFlightNumber" + j1, OnwardFlightNumber);

			} else {

				hp.put("prodOnwardFlightNumber" + j1, OnwardFlightNumber);

			}

			// System.out.println(hp.get("prodOnwardFlightNumber"+j1));

			String ReturnFlightNumber = driver

					.findElement(By.xpath(".//*[@id='flightForm']/section[2]/div[3]/div[2]/div[2]/div/small[1]"))

					.getText();

			// System.out.println("Return FlightNumber=="+ReturnFlightNumber);

			addLog("Return FlightNumber==" + ReturnFlightNumber);

			Thread.sleep(1000);

			if (URI.equalsIgnoreCase("beta")) {

				hp.put("betaReturnFlightNumber" + j1, ReturnFlightNumber);

			} else {

				hp.put("prodReturnFlightNumber" + j1, ReturnFlightNumber);

			}

			// System.out.println(hp.get("prodReturnFlightNumber"+j1));



		}



		return hp;

	}



	public void convinienceData(RemoteWebDriver driver) throws Exception {

		Thread.sleep(9000);

		// System.out.println("Search URL is : " + driver.getCurrentUrl());

		System.out.println("---" + driver.findElement(By.cssSelector("#payBlockTotal > label.optINR > #totalFare > #counter")).getText());

		insuranceDetails.put("finaltotalamount",

				driver.findElement(By.cssSelector("#payBlockTotal > label.optINR > #totalFare > #counter")).getText()

						.replace(",", ""));



		// driver.findElement(By.cssSelector("#fareBreakupDetails > small")).click();



		// System.out.println("-----"+driver.findElement(By.xpath("//dd[@id='rtTotalAmount'

		// and @class='total']")).getText());

		System.out.println("processing fees=" + driver.findElement(By.xpath(".//*[@id='ProcessingFee']")).getText());

		System.out.println(

				"processing fees=" + driver.findElement(By.xpath(".//*[@id='ProcessingFee']")).getText().split(" ")[6]);

		try {

			insuranceDetails.put("processingfees",

					driver.findElement(By.xpath(".//*[@id='ProcessingFee']")).getText().split(" ")[6]);

		} catch (Exception e) {



		}



		// System.out.println("Total at

		// itinerary=Rs."+Integer.parseInt(insuranceDetails.get("totalatitinerary")));

		double itinerarytotal = Double.parseDouble(insuranceDetails.get("totalatitinerary"));



		double processingfees = Double.parseDouble(insuranceDetails.get("processingfees"));

		double paymenttotal = Double.parseDouble(insuranceDetails.get("finaltotalamount"));

		double total = itinerarytotal + processingfees;

		addLog("itinerarytotal=" + itinerarytotal + ",processingfees=" + processingfees + ",paymenttotal="

				+ paymenttotal + ",itinerary+processing fees=" + total);

		// System.out.println("itinerarytotal="+itinerarytotal+",processingfees="+processingfees+",paymenttotal="+paymenttotal+",itinerary+processing

		// fees="+total);

		// safeClick(driver,By.xpath("//a[@id='close']"));

		Assert.assertEquals(total, paymenttotal, "processing fees amount =" + processingfees + "total in paymentpage="

				+ paymenttotal + "  itinerary total and processingfees=" + total);



	}



	public void checkFreeCancellation(RemoteWebDriver driver) {

		if (driver.findElement(By.xpath("//div[@class='paymentSummary freeCancellation']/nav/ul/li[3]/dl/dt[3]"))

				.getText().equalsIgnoreCase("Free cancellation option")) {

			System.out.println("Free cancellation option checked");

			addLog("Free cancellation option checked");

		}

	}



	public void checkRefundable(RemoteWebDriver driver, HashMap<String, String> hp) throws InterruptedException {

		List<WebElement> we = driver.findElements(By.xpath("//a[@linkidentifier='fare']"));

		we.get(0).click();

		Thread.sleep(1000);

		String refund = driver.findElement(By.xpath("//div[@class='itinerary clearFix']/h1/span")).getText();

		hp.put("refundable", refund);



	}



	public void SelectInsurance(RemoteWebDriver driver) throws Exception {



		if (elementPresent_Time(driver, By.xpath("//li[@id='insuranceBG']/a"), 1)) {



			printInfo("Review itinerary loaded");

			safeClick(driver, getObject("air_review_itinerary_continue"));

		} // else if (elementPresent_Time(driver,

			// getObject("air_review_itinerary_no_terms"), 10)){



		else if (!driver.findElement(getObject("air_review_itinerary_no_terms")).isSelected()) {

			safeClick(driver, getObject("air_review_itinerary_no_terms"));

		}

		safeClick(driver, getObject("air_review_itinerary_terms"));



		printInfo("Review itinerary loaded");

		safeClick(driver, getObject("air_review_itinerary_continue"));



	}



	public void IntlInsurance(RemoteWebDriver driver) throws Exception {



		elementPresent_Time(driver, getObject("air_review_itinerary_no_terms"), 10);



		if (driver.findElement(getObject("air_review_itinerary_no_terms")).isSelected()) {

			safeClick(driver, getObject("air_review_itinerary_no_terms"));

		}



		printInfo("Review itinerary loaded");

		safeClick(driver, getObject("air_review_itinerary_continue"));

	}



	public String Apply_CouponCode(RemoteWebDriver driver, String coupon) throws Exception {



		if (elementVisible(driver, By.id("priceChangeDownBtn"), 20)) {

			safeClick(driver, By.id("priceChangeDownBtn"));

		}

		// Scroll Manually

		JavascriptExecutor js1 = driver;

		js1.executeScript("window.scrollBy(0,200)", "");



		// Scroll to the element

		scrollToElement(driver, By.id("coupon"));

		elementPresent_Time(driver, By.id("coupon"), 5);



		if (common.value("host").equalsIgnoreCase("qa2")) {



			/*

			 * if (coupon.equalsIgnoreCase("domow")) { safeType(driver, By.id("coupon"),

			 * "SHACHI");

			 */

			if (coupon.equalsIgnoreCase("domow")) {

				safeType(driver, By.id("coupon"), "DOMOW"); // FLYSAVE

			} else if (coupon.equalsIgnoreCase("flysave")) {

				safeType(driver, By.id("coupon"), "FLYSAVE");

			} else if (coupon.equalsIgnoreCase("domrt")) {

				safeType(driver, By.id("coupon"), "MOBILE321");

			} else if (coupon.equalsIgnoreCase("intlow")) {

				safeType(driver, By.id("coupon"), "INTLOW");

			} else if (coupon.equalsIgnoreCase("intlrt")) {

				safeType(driver, By.id("coupon"), "INTLRT");

			} else if (coupon.equalsIgnoreCase("wallet3")) {

				safeType(driver, By.id("coupon"), "WALLET3");

			} else if (coupon.equalsIgnoreCase("DOMOWCB")) {

				safeType(driver, By.id("coupon"), "DOMOWCB");

			} else if (coupon.equalsIgnoreCase("MOBILE321")) {

				safeType(driver, By.id("coupon"), "MOBILE321");



			}
			else if (coupon.equalsIgnoreCase("AUTORT")) {
				
				safeType(driver, By.id("coupon"), "AUTORT");
			}

			System.out.println("coupon entered");

			safeClick(driver, By.id("check_saving"));

			System.out.println("After entering coupon");

			if (coupon.equalsIgnoreCase("wallet3")) {

				assertTrue("Coupon Not Applied",

						getText(driver, By.xpath("//p[@id='saveMsg']/strong")).contains("Cleartrip Wallet "));

			} else if (coupon.equalsIgnoreCase("DOMOWCB")) {

				assertTrue("Coupon Not Applied!", getText(driver, By.xpath("//p[@id='saveMsg']/strong"))

						.contains(" will be credited to your Cleartrip Wallet post booking."));

				assertTrue("Coupon Message not Dispalyed!",

						getText(driver, By.xpath("//p[@id='saveMsg']/strong/../../p[2]"))

								.contains("For details on when the money will be credited, please see"));

				assertTrue("CashBack Schedule Link not Displayed!",

						getText(driver, By.xpath("//p[@id='saveMsg']/strong/../../p[3]/a"))

								.contains("Cashback schedule"));



				String totalCashBack = getText(driver, By.xpath("//p[@id='saveMsg']/strong")).split("Rs.")[1]

						.split(" will")[0].replace(",", "");

				addLog("Total Cash Back from the Message = " + totalCashBack, true);



				safeClick(driver, By.xpath("//p[@id='saveMsg']/strong/../../p[3]/a"));



				List<WebElement> we = driver.findElements(By.xpath("//section/ul/li"));

				String todayCashBack = "";

				int total = 0;

				for (int i = 0; i < we.size(); i++) {

					String schedule = we.get(i).getText();

					addLog(schedule, true);



					String cashback = schedule.split("Rs.")[1].replace(",", "");

					if (cashback.equalsIgnoreCase("")) {

						Assert.fail("Cash Back amount is not displayed!");

					} else if (Integer.parseInt(cashback) == 0) {

						Assert.fail("Cash Back amount is 0!");

					}



					if (schedule.contains("Today"))

						todayCashBack = cashback;



					total = total + Integer.parseInt(cashback);

				}



				addLog("Total from schedule = " + total, true);

				total = total - 1;

				assertTrue("Cash Back displayed is not equal to total cashback from the schedule!",

						total == Integer.parseInt(totalCashBack));



				safeClick(driver, By.xpath("//a[@title='Close']"));



				return todayCashBack;

			} else {

				elementPresent_Time(driver, By.xpath("//p[@id='saveMsg']/strong"), 10);

				assertTrue("Coupon Not Applied",

						getText(driver, By.xpath("//p[@id='saveMsg']/strong")).contains("You just saved"));

				System.out.println("In else blovk");

			}

			System.out.println("Coupon Code Applied Successfully");

			addLog("Coupon Code Applied Successfully");



		} else if (common.value("host").equalsIgnoreCase("beta")) {

			safeType(driver, By.id("coupon"), "CTEMP");

			safeClick(driver, By.id("check_saving"));

			elementPresent_Time(driver, By.xpath("//p[@id='saveMsg']/strong"), 10);

			assertTrue("Coupon Not Applied",

					getText(driver, By.xpath("//p[@id='saveMsg']/strong")).contains("Cleartrip Wallet "));

			// System.out.println("Coupon Code Applied Successfully");

			addLog("Coupon Code Applied Successfully");



		}

		return null;



	}



	public void Apply_CouponCode1(RemoteWebDriver driver, String coupon, HashMap<String, String> hp) throws Exception {

		elementPresent_Time(driver, By.id("coupon"), 5);

		if (common.value("host").equalsIgnoreCase("qa2")) {

			if (coupon.equalsIgnoreCase("wallet3")) {

				safeType(driver, By.id("coupon"), "WALLET3");

				safeClick(driver, By.id("check_saving"));

				elementPresent_Time(driver, By.xpath("//p[@id='saveMsg']/strong"), 10);

				String wallet = getText(driver, By.xpath("//p[@id='saveMsg']/strong"));

				hp.put("wallet", getText(driver, By.xpath("//p[@id='saveMsg']/strong")));

				// assertTrue("Coupon Not Applied", getText(driver,

				// By.xpath("//p[@id='saveMsg']/strong")).contains("You just saved"));

			}



		}

	}



	public void getWalletInformation(RemoteWebDriver driver) throws InterruptedException {

		driver.findElement(By.xpath("//a[@id='userAccountLink']/span[2]")).click();

		Thread.sleep(2000);

		List<WebElement> we = driver.findElements(By.xpath("//li[@class='userAccountCol accountTools']/ul/li"));

		test: for (int i = 0; i < we.size(); i++) {

			int a = i + 1;

			try {

				System.out.println("----" + we.get(i)

						.findElement(By.xpath("//li[@class='userAccountCol accountTools']/ul/li[" + a + "]/a"))

						.getAttribute("href"));

				if (we.get(i).findElement(By.xpath("//li[@class='userAccountCol accountTools']/ul/li[" + a + "]/a"))

						.getAttribute("href").contains("wallet")) {

					we.get(i).click();

					break test;

				}

			} catch (Exception e) {



			}



		}



		for (int i = 0; i < 30; i++) {

			Thread.sleep(2000);

			driver.navigate().refresh();

			Thread.sleep(2000);

			System.out

					.println("wallet balance=" + driver.findElement(By.xpath("//p[@class='walletBalance']")).getText());

		}

	}



	public void Apply_AECouponCode(RemoteWebDriver driver, String coupon) throws Exception {

		elementPresent_Time(driver, By.id("coupon"), 5);

		if (common.value("host").equalsIgnoreCase("qa2")) {



			/*

			 * if (coupon.equalsIgnoreCase("domow")) { safeType(driver, By.id("coupon"),

			 * "SHACHI");

			 */

			if (coupon.equalsIgnoreCase("domow")) {

				safeType(driver, By.id("coupon"), "DOMOW");

			} else if (coupon.equalsIgnoreCase("domrt")) {

				safeType(driver, By.id("coupon"), "MOBILE321");

			} else if (coupon.equalsIgnoreCase("intlow")) {

				safeType(driver, By.id("coupon"), "INTLOW");

			} else if (coupon.equalsIgnoreCase("intlrt")) {

				safeType(driver, By.id("coupon"), "INTLRT");

			}



			safeClick(driver, By.id("check_saving"));

			elementPresent_Time(driver, By.xpath("//p[@id='saveMsg']/strong"), 10);

			assertTrue("Coupon Not Applied",

					getText(driver, By.xpath("//p[@id='saveMsg']/strong")).contains("You just saved"));

			System.out.println("Coupon Code Applied Successfully");

			addLog("Coupon Code Applied Successfully");



		} else if (common.value("host").equalsIgnoreCase("beta")) {

			safeType(driver, By.id("coupon"), "CTEMP");

			safeClick(driver, By.id("check_saving"));

			elementPresent_Time(driver, By.xpath("//p[@id='saveMsg']/strong"), 10);

			assertTrue("Coupon Not Applied",

					getText(driver, By.xpath("//p[@id='saveMsg']/strong")).contains("You just saved"));

			System.out.println("Coupon Code Applied Successfully");

			addLog("Coupon Code Applied Successfully");

		}

	}



	/*

	 * public void applyGiftVoucher(RemoteWebDriver driver) throws Exception {

	 *

	 * printInfo("Review itinerary loaded");

	 *

	 * driver.findElement(By.id("coupon")).clear();

	 * driver.findElement(By.id("coupon")).sendKeys("9900096795528844");

	 * Thread.sleep(2000); // waitElement(driver,

	 * getObject("AirCom_bookstep_GV_CardField"), 30);

	 *

	 * driver.findElement(getObject("AirCom_bookstep_GV_CardField")).clear(); driver

	 * .findElement(getObject("AirCom_bookstep_GV_CardField")).sendKeys("822589" );

	 * driver.findElement(By.id("check_saving")).click(); Thread.sleep(2000);

	 * waitElement(driver, getObject("AirCom_bookstep_GV_SuccessMsg"), 50); String

	 * SuccessMsg =

	 * driver.findElementByXPath("//p[@id='saveMsg']/strong").getText();

	 * assertTrue("Gift Voucher Not Applied",

	 * SuccessMsg.contains("has been redeemed"));

	 *

	 * safeClick(driver, getObject("air_review_itinerary_continue")); }

	 */

	// Yashmin



	public void applyShukran(RemoteWebDriver driver, String gvnumber) throws Exception {



		printInfo("Review itinerary loaded");



// driver.findElement(By.id("coupon")).clear();

		driver.findElement(By.xpath("//input[@name='coupon']")).clear();

		driver.findElement(By.xpath("//input[@name='coupon']")).sendKeys("1800220910201010");



		driver.findElement(By.xpath("//a[@id='check_saving']")).click();

		Thread.sleep(200);

		driver.findElement(By.xpath("//p[@id='saveMsg']")).getText();

		System.out.println("Shukran message" + driver.findElement(By.xpath("//p[@id='saveMsg']")).getText());

	}



	public void applyGiftVoucher(RemoteWebDriver driver, String gvnumber, String gvpin) throws Exception {



		printInfo("Review itinerary loaded");



		// driver.findElement(By.id("coupon")).clear();

		driver.findElement(By.id("coupon")).sendKeys(gvnumber);

		Thread.sleep(200);

		// waitElement(driver, getObject("AirCom_bookstep_GV_CardField"), 30);



		// driver.findElement(getObject("AirCom_bookstep_GV_CardField")).clear();

		driver.findElement(getObject("AirCom_bookstep_GV_CardField")).sendKeys(gvpin);

		driver.findElement(By.id("check_saving")).click();

		Thread.sleep(200);

		waitElement(driver, getObject("AirCom_bookstep_GV_SuccessMsg"), 5);

		String SuccessMsg = driver.findElementByXPath("//p[@id='saveMsg']/strong").getText();

		assertTrue("Gift Voucher Not Applied", SuccessMsg.contains("has been redeemed"));



		// safeClick(driver, getObject("air_review_itinerary_continue"));

	}



	public void walletmoney(int amount) throws URISyntaxException, IOException, JSONException {

		DefaultHttpClient clientSearch = null;

		clientSearch = new DefaultHttpClient();

		String hash = calculateHash("SHA-256",

				"5152532|Bangalore-chennai|Q1612160133|PROMOTION|CREDIT|" + amount + "|03-DEC-2019|2483|cl3@rTri9");



		System.out.println("--" + hash);



		String postString = "{   \"trip-details\": \"Bangalore-chennai\",   \"trip-ref\": \"Q1612160133\",   \"event\": \"PROMOTION\",   \"txn-type\": \"CREDIT\",   \"amount\":"

				+ amount + ",   \"expiry-date\": \"03-DEC-2019\",   \"caller-id\": \"2483\" }";

		HttpPost WalletCall = new HttpPost(

				new URI("http://172.17.12.145:9080/payment/service/ctwallets/5152532/transactions"));

		System.out.println(postString);

		StringEntity params = new StringEntity(postString);

		WalletCall.setEntity(params);



		WalletCall.setHeader("Content-Type", "application/json");

		WalletCall.setHeader("checksum", hash);

		// itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");

		HttpResponse itinenaryResponse = clientSearch.execute(WalletCall);

		HttpEntity entityIti = itinenaryResponse.getEntity();



		Document docIti = null;

		BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));

		String str12 = "";

		StringBuffer sb12 = new StringBuffer();

		while ((str12 = br12.readLine()) != null) {

			sb12.append(str12);



		}

		System.out.println(sb12);

		JSONObject WalletDetails = new JSONObject(sb12.toString());



		addLog("WalletDetails = " + WalletDetails, true);

		// System.out.println(itinenaryId.getString("itinerary_id"));

	}



	public void travellerDetails(RemoteWebDriver driver, String adult, String children, String infant,

			boolean international, boolean useGST, boolean useNonDefaultState) throws Exception {

		if (elementVisible(driver, By.id("priceChangeDownBtn"), 2)) {

			safeClick(driver, By.id("priceChangeDownBtn"));

		}



		addLog("Adding traveller Details", true);

		String adults = adult;

		num_adults = Integer.parseInt(adults);

		String childrens = children;

		num_children = Integer.parseInt(childrens);

		String infants = infant;

		num_infants = Integer.parseInt(infants);

		int num_of_adults = num_adults;

		int num_of_children = num_children;

		int num_of_infants = num_infants;



		System.out.println(driver.getCurrentUrl().contains("login")

				|| waitForElement(driver, 10, getObject("air_traveller_details_continue")));

		Thread.sleep(2000);

		smartClick(driver, By.xpath("//*[@id='itineraryBtn']"));

		smartClick(driver, By.xpath("//input[@class='primary nearByAirportWarningBtn']"));



		boolean loginSection = driver.getCurrentUrl().contains("login");

		if (loginSection) {

			printInfo("Login Required");

			System.out.println(dataFile.value("AirUserIdForHQScripts"));

			safeType(driver, getObject("step2_email_address_username"), dataFile.value("AirUserIdForHQScripts"));

			// safeClick(driver, getObject("step2_password_checkbox"));

			Thread.sleep(500);

			if (elementVisible(driver, getObject("clickcheckbox"), 1)) {

				safeClick(driver, getObject("clickcheckbox"));

			}

			System.out.println(dataFile.value("AirPasswordForHQScripts"));

			safeType(driver, getObject("step2_email_password"), dataFile.value("AirPasswordForHQScripts"));

			safeClick(driver, getObject("step2_login_button"));

		}



		Thread.sleep(3000);



		fillTravellersDetails(driver, num_of_adults, num_of_children, num_of_infants, international);

		fillTravellersContact(driver);



		fillGstDetails(driver, useGST, useNonDefaultState);



		safeClick(driver, getObject("air_traveller_details_continue"));

		Thread.sleep(5000);

		if (elementVisible(driver, By.xpath("//input[@value='Review new timings']"), 1)) {

			safeClick(driver, By.xpath("//input[@value='Review new timings']"));

		}

		if (elementVisible(driver, By.id("priceChangeUpBtn"), 1)) {

			safeClick(driver, By.id("priceChangeUpBtn"));

		}



	}



	public void travellerDetailsAR(RemoteWebDriver driver, String adult, String children, String infant,

			boolean international) throws Exception {



		String adults = adult;

		num_adults = Integer.parseInt(adults);

		String childrens = children;

		num_children = Integer.parseInt(childrens);

		String infants = infant;

		num_infants = Integer.parseInt(infants);

		int num_of_adults = num_adults;

		int num_of_children = num_children;

		int num_of_infants = num_infants;

		System.out.println(driver.getCurrentUrl().contains("login")

				|| waitForElement(driver, 10, getObject("air_traveller_details_continue")));



		boolean loginSection = driver.getCurrentUrl().contains("login");

		if (loginSection) {

			printInfo("Login Required");

			System.out.println(dataFile.value("AirUserIdForHQScripts"));

			safeType(driver, getObject("step2_email_address_username"), dataFile.value("AirUserIdForHQScripts"));

			// safeClick(driver, getObject("step2_password_checkbox"));

			Thread.sleep(500);

			if (elementVisible(driver, getObject("clickcheckbox"), 1)) {

				safeClick(driver, getObject("clickcheckbox"));

			}

			System.out.println(dataFile.value("AirPasswordForHQScripts"));

			safeType(driver, getObject("step2_email_password"), dataFile.value("AirPasswordForHQScripts"));

			safeClick(driver, getObject("step2_login_button"));

		}



		Thread.sleep(3000);



		fillTravellersDetailsAR(driver, num_of_adults, num_of_children, num_of_infants, international);

		fillTravellersContact(driver);

		safeClick(driver, getObject("air_traveller_details_continue"));

		Thread.sleep(5000);

	}



	/*

	 * Modified By: prashanth.k@cleartrip.com Meal selection was failing. now

	 * updated.

	 */

	public void getMeals(RemoteWebDriver driver) throws InterruptedException, Exception {

		// Thread.sleep(5000);

		boolean addMealBtnPresent = false;

		boolean addMealBtn_BundlePresent = false;



		for (int i = 0; i < 3; i++) {

			if (waitForElementVisibility(driver, getObject("AirCom_BookStep_AddMealBtn"), 30))

				addMealBtnPresent = true;

			else if (waitForElementVisibility(driver, By.xpath("//div[@id='beforeMeals']/div[2]/button)"), 30))

				addMealBtn_BundlePresent = true;



			addLog("" + addMealBtn_BundlePresent + " " + addMealBtnPresent, true);

			if (addMealBtn_BundlePresent || addMealBtnPresent) {

				addLog("Add Meal Present", true);

				break;

			}

			addLog("Add Meal not Present. Rechecking page.", true);

		}



		if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn"), 30)) {

			safeClick(driver, getObject("AirCom_BookStep_AddMealBtn"));

			Thread.sleep(10000);

			driver.switchTo().frame("modal_window");

		} else if (elementPresent_Time(driver, By.xpath("//div[@id='beforeMeals']/div[2]/button)"), 1)) {

			safeClick(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"));

			if (elementPresent_Time(driver, By.xpath("//button[@class='action selectAddonButton']"), 1)) {

				safeClick(driver, By.xpath("//button[@class='action selectAddonButton']"));

			}

			Thread.sleep(10000);

			driver.switchTo().frame("modal_window");

		}



		assertTrue("Meals window not opened", driver.findElement(By.cssSelector("h2")).getText().contains("Meals for"));



		// select meal

		Actions action = new Actions(driver);



		WebElement we1 = driver.findElementByCssSelector("a.row.selectAddonListItem");

		action.moveToElement(we1).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click()

				.build().perform();



		Thread.sleep(2000);

		assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

		driver.findElementByXPath("//input[@value='Done']").click();

		Thread.sleep(1000);

		addLog("Meals Added", true);

		// System.out.println("Meals Added");



		driver.switchTo().defaultContent();

	}



	public boolean waitForElement(RemoteWebDriver driver, int time, By by) throws Exception {



		int i = 0;

		boolean elementActiveFlag = false;

		long timerNow = new Date().getTime();

		for (i = 0; (new Date().getTime() - timerNow) / 1000 <= time; i++) {

			if (elementPresent(driver, by, 1)) {

				elementActiveFlag = true;

				break;

			}

			/*

			 * else if(elementPresent(driver,By.xpath(

			 * "//android.widget.ImageView[contains(@resource-id,'moeCloseButton')]" ),1)){

			 * safeClick(driver,By.xpath(

			 * "//android.widget.ImageView[contains(@resource-id,'moeCloseButton')]" )); }

			 */

			Thread.sleep(1000);

		}

		// System.out.println((new Date().getTime() - timerNow) / 1000 +

		// " seconds taken for " + by + " to load");

		if (!elementActiveFlag) {

			// System.out.println("Element By "+by+ " Not Loaded in"+ time

			// +"Seconds");

		}

		return elementActiveFlag;

	}



	@Override

	public boolean elementPresent(RemoteWebDriver driver, By by, int time) {



		boolean elementPresentFlag = false;

		for (int i = 0; i < time; i++) {



			try {

				Thread.sleep(1000);

				WebElement we = null;

				if ((we = driver.findElement(by)) != null) {

					// System.out.println("Element Present" + by);

					elementPresentFlag = true;

					break;

				}



			} catch (Exception e) {

				// System.out.println("Element : " + by + " not present");

			}



		}

		return elementPresentFlag;

	}



	/*

	 * Modified By: prashanth.k@cleartrip.com Baggage selection was failing. now

	 * updated.

	 */

	public void getBaggage(RemoteWebDriver driver) throws Exception {



		for (int i = 0; i < 3; i++) {

			boolean baggageBtnFound = waitForElement(driver, 60, getObject("AirCom_BookStep_AddBaggageBtn"));

			if (baggageBtnFound) {

				addLog("Add Baggage Button found.", true);

				break;

			}

			// driver.navigate().refresh();

			// Thread.sleep(3000);

			addLog("Add Baggage not Button found. Rechecking", true);

		}



		safeClick(driver, getObject("AirCom_BookStep_AddBaggageBtn"));

		Thread.sleep(4000);

		driver.switchTo().frame("modal_window");



		assertTrue("baggage window not opened",

				driver.findElement(By.cssSelector("h2")).getText().contains("Baggage for"));



		// select baggage

		Actions action1 = new Actions(driver);



		WebElement we = driver.findElementByCssSelector("a.row.selectAddonListItem");

		action1.moveToElement(we).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click()

				.build().perform();



		Thread.sleep(2000);

		assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

		driver.findElementByXPath("//input[@value='Done']").click();

		Thread.sleep(3000);

		addLog("Baggage Added", true);

	}



	public void getBaggageLinkInfo(RemoteWebDriver driver) throws Exception {

		waitForElement(driver, 60, By.xpath("//li[@class='baggageInfo']/a"));

		safeClick(driver, By.xpath("//li[@class='baggageInfo']/a"));

		addLog("Link Name=" + getText(driver, By.xpath("//li[@class='baggageInfo']/a")));

		System.out.println("Link Name=" + getText(driver, By.xpath("//li[@class='baggageInfo']/a")));

		String link = getText(driver, By.xpath("//li[@class='baggageInfo']/a"));

		Assert.assertEquals(link, "more info", "Baggage info not present");

		driver.switchTo().frame("modal_window");

		List<WebElement> we = driver.findElements(By.xpath("//td[@class='keyValue']/p"));

		for (int i = 0; i < we.size(); i++) {

			addLog(we.get(i).getText());

			System.out.println(we.get(i).getText());

		}

	}



	public void nearByAirport(RemoteWebDriver driver, String city) {

		List<WebElement> we2 = driver

				.findElements(By.xpath("//li[contains(@class,'listItem')]/table/tbody/tr[2]/td[3]/button"));

		List<WebElement> we = driver.findElements(By.xpath("//li[contains(@class,'listItem')]/table/tbody/tr[2]"));



		System.out.println("size=" + we.size());

		test: for (int i = 0; i < we.size(); i++) {

			// System.out.println("i value="+i);

			// System.out.println("attribute value="+we.get(i).getAttribute("class"));

			// System.out.println("-------"+we.get(i).findElement(By.xpath("./td[1]/abbr")).getAttribute("title"));

			if (we.get(i).findElement(By.xpath("./td[1]/abbr")).getAttribute("title").contains(city)) {

				we.get(i).findElement(By.xpath("./td[3]/button")).click();

				break test;

			}

		}

	}



	public boolean airconditionWatcher(RemoteWebDriver driver) throws Exception {

		boolean reached = false;

		Boolean Schedule_Change = false;

		Boolean Price_ChangeUp_Btn = false;

		Boolean Price_ChangeDown_Btn = false;

		Boolean SRPLoaded = false;

		for (int i = 0; i < 2; i++) {

			Schedule_Change = elementVisible(driver, getObject("AirCom_BookStep4_Schedule_Change_button"), 1);

			Price_ChangeUp_Btn = elementVisible(driver, getObject("AirCom_BookStep4_Price_ChangeUp_button"), 1);

			Price_ChangeDown_Btn = elementVisible(driver, getObject("AirCom_BookStep4_Price_ChangeDown_button"), 1);

			SRPLoaded = elementVisible(driver, getObject("AirCom_SRP_Modify_Search_Button"), 1);



			if (Schedule_Change || Price_ChangeUp_Btn || Price_ChangeDown_Btn || SRPLoaded)

				break;

		}



		if (Schedule_Change || Price_ChangeUp_Btn || Price_ChangeDown_Btn || SRPLoaded) {

			if (Schedule_Change) {

				airlineRescheduled(driver);

				reached = true;

			} else if (Price_ChangeUp_Btn) {

				airlinePricechanged(driver);

				reached = true;

			} else if (Price_ChangeDown_Btn) {

				safeClick(driver, By.id("priceChangeDownBtn"));

				addLog("airline price came down message is displayed ", true);

				Thread.sleep(3000);

				reached = true;

			} else if (elementVisible(driver, By.id("paymentContinue"), 2)) {

				addLog("\"OOPS! Something broke. Try again.\" error came. Retrying with new sector.", true);

			} else if (SRPLoaded) {

				addLog("SRP loaded after some failure. Retrying with new sector.", true);

			}

		} else if (waitElement(driver, By.xpath(".//*[@id='ResultContainer_1_1']"), 1)) {

			addLog(

					"''Sorry, there aren't any flights available for your search.'' message displayed on redirecting to SRP. Retrying with new sector.",

					true);

		} else if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {

			addLog("System Acting up error.", true);

			assertTrue("Failure!", false);

		} else if (waitElement(driver, getObject("AirCom_BookStep4_CreditCard_Tab"), 1)) {
System.out.println("inside cc");
			reached = true;

		} else if (waitElement(driver, getObject("AirCom_BookStep4_CreditCard_Tab_Alternate"), 1)) {
			System.out.println("inside cc alt");
			reached = true;

		}



		return reached;

	}



	public void airlineRescheduled(RemoteWebDriver driver) throws Exception {

		Thread.sleep(2000);

		safeClick(driver, getObject("AirCom_BookStep4_Schedule_Change_button"));

		Thread.sleep(4000);

		smartClick(driver, getObject("air_review_itinerary_continue"));

		Thread.sleep(2000);

		if (elementVisible(driver, getObject("air_traveller_details_continue"), 25)) {

			scrollToElement(driver, getObject("air_traveller_details_continue"));

			Thread.sleep(2000);

			safeClick(driver, getObject("air_traveller_details_continue"));

			addLog("airline rescheduled message is displayed ", true);

		}

		Thread.sleep(2000);

	}



	public void airlinePricechanged(RemoteWebDriver driver) throws Exception {

		addLog("Message displayed for price change.", true);

		safeClick(driver, getObject("AirCom_BookStep4_Price_ChangeUp_button"));

		Thread.sleep(2000);

	}



	public void fillTravellersDetails(RemoteWebDriver driver, int num_of_adults, int num_of_children,

			int num_of_infants, boolean international) throws Exception {

		String month = "Jan";

		String Dob_month = month;

		boolean GDS = b2cGDSAirlines(driver, getObject("AirCom_BookStep3__GDS_flight_check"));

		boolean traveller_detail_reached = elementPresent(driver,

				By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + "1"), 10);

		addLog("Traveller details Step reached", traveller_detail_reached);

		// --------------------------------------------GDS

		// PAX------------------------------------------//

		if (GDS) {

			addLog("Pax entry for GDS Flight Combination", true);

			if (num_of_adults > 0) {

				for (int i = 1; i <= num_of_adults; i++) {

					boolean adult_name_details = elementVisible(driver,

							By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i), 5);

					if (adult_name_details) {

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i),

									dataFile.value("atitle"));

						} catch (Exception e) {

							safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i),

									dataFile.value("atitle"));

						}

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultFirstName") + i),

								"AdtF" + generateRandomWord(4));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultLastName") + i),

								"AdtL" + generateRandomWord(4));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

								1)) {

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

										dataFile.value("adobday"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

										dataFile.value("adobday"));

							}

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBMonth") + i + ""),

										dataFile.value("adobmonth"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBMonth") + i + ""),

										dataFile.value("1"));

							}

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBYear") + i + ""),

										dataFile.value("adobyear"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBYear") + i + ""),

										dataFile.value("adobyear"));

							}

						}

						if (elementPresent_Time(driver, By.xpath("//div[" + i + "]/div/div/dl[2]/dl/dd/input"), 1)) {



							safeAutocomplete(driver, getObject("AirCom_bookstep_travelles_nationality"),

									getObject("AirCom_BookStep4_Amex_Billing_Autocomplete"), "india");

						}

						if (elementVisible(driver, By.xpath("//input[@data-idfield='adultNationality" + i + "']"), 1)) {

							sendKeysByKeyboard(driver, By.xpath("//input[@data-idfield='adultNationality" + i + "']"),

									"India");

							pressEnterKey(driver);

						}



						// international sector from here

						if (international) {

							boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

									1);

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i), 1))

								safeType(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i),

										"F435262"+getRandomNo(4));

							if (waitElement(driver, By.id("adultPPIssuingCountry" + i), 1))

								safeType(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i),

										dataFile.value("apassport"));

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Country") + i), 1))

								autoCompletePassportIssuing(driver, dataFile.value("appissuingcountry"),

										By.id(getValue("AirCom_BookStep3_Adult_Passport_Country") + i), "2");

							if (elementPresent(driver, By.id("adultPPIssuingCountry" + i), 1)) {

								safeType(driver, By.id("adultPPIssuingCountry" + i), "Indi");

								safeClick(driver, By.xpath("//a[contains(text(),'India')]"));



							}

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

									1)) {

								try {

									safeSelect(driver,

											By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

											dataFile.value("appday"));

								} catch (Exception e1) {

									safeSelectByValue(driver,

											By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

											dataFile.value("appday"));

								}

								try {

									safeSelect(driver,

											By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_month") + i),

											dataFile.value("appmonth"));

								} catch (Exception e) {

									safeSelectByValue(driver,

											By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_month") + i),

											dataFile.value("8"));

								}

								try {

									safeSelect(driver,

											By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_year") + i),

											dataFile.value("appyear"));

								} catch (Exception e) {

									safeSelectByValue(driver,

											By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_year") + i),

											dataFile.value("appyear"));

								}

							}

							if (visa)

								try {

									smartSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e) {

									try {

										safeSelectByValue(driver,

												By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

												dataFile.value("visatype"));

									} catch (Exception e1) {

										addLog("IMP. NOTE: Passport drop down is not Visible!!!", true);

									}

								}

						}

					} else {

						addLog("Infant " + i + " is not displayed");

					}

				}

			}

			if (num_of_children > 0) {



				for (int i = 1; i <= num_of_children; i++) {

					boolean child_name_details = elementVisible(driver,

							By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i), 1);



					if (child_name_details) {

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i),

									dataFile.value("ctitle"));

						} catch (Exception e) {

							safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i),

									dataFile.value("ctitle"));

						}

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildFirstName") + i),

								"CHDF" + generateRandomWord(4));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildLastName") + i),

								"CHDL" + generateRandomWord(4));



						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBDay") + i),

									dataFile.value("cdobday"));

						} catch (Exception e) {

							safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBDay") + i),

									dataFile.value("cdobday"));

						}

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBMonth") + i), month);

						} catch (Exception e) {

							safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBMonth") + i),

									"1");

						}

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBYear") + i),

									putYear(-3));

						} catch (Exception e) {

							safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBYear") + i),

									putYear(-3));

						}



						if (elementVisible(driver, By.xpath("//input[@data-idfield='childNationality" + i + "']"), 2)) {

							safeType(driver, By.xpath("//input[@data-idfield='childNationality" + i + "']"), "India");

							safeClick(driver, By.xpath("//a[contains(text(),'India')]"));

						}

						// international sector here

						if (international) {

							boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

									1);

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i), 2))

								safeType(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i),

										"F435262"+getRandomNo(4));

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Country") + i), 2))

								autoCompletePassportIssuing(driver, dataFile.value("cppissuingcountry"),

										By.id(getValue("AirCom_BookStep3_Child_Passport_Country")), "2");

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

									1)) {

								try {

									safeSelect(driver,

											By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

											dataFile.value("cppday"));

								} catch (Exception e) {

									safeSelectByValue(driver,

											By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

											dataFile.value("cppday"));

								}

								try {

									safeSelect(driver,

											By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_month") + i),

											dataFile.value("cppmonth"));

								} catch (Exception e) {

									safeSelectByValue(driver,

											By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_month") + i),

											dataFile.value("8"));

								}

								try {

									safeSelect(driver,

											By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_year") + i),

											dataFile.value("cppyear"));

								} catch (Exception e) {

									safeSelectByValue(driver,

											By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_year") + i),

											dataFile.value("cppyear"));

								}

							}

							if (visa)

								try {

									smartSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e) {

									try {

										safeSelectByValue(driver,

												By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

												dataFile.value("visatype"));

									} catch (Exception e1) {

										addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

									}

								}

						}

					} else {

						addLog("Child " + i + " is not displayed");

					}

				}

			}



			if (num_of_infants > 0) {



				for (int i = 1; i <= num_of_infants; i++) {

					boolean infant_name_details = elementVisible(driver,

							By.id(getValue("AirCom_BookStep3_Traveller_InfantTitle") + i), 1);



					if (infant_name_details) {

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantTitle") + i),

									dataFile.value("ititle"));

						} catch (Exception e) {

							safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantTitle") + i),

									dataFile.value("ititle"));

						}

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantFname") + i),

								"INFF" + generateRandomWord(4));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantLname") + i),

								"INFL" + generateRandomWord(4));

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobDay") + i),

									dataFile.value("idobday"));

						} catch (Exception e) {

							safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobDay") + i),

									dataFile.value("idobday"));

						}

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobMonth") + i),

									Dob_month);

						} catch (Exception e) {

							safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobMonth") + i),

									"1");

						}

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobYear") + i),

									putYear(0));

						} catch (Exception e) {

							safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobYear") + i),

									putYear(0));

						}



						// international sector here

						if (international) {

							if (elementVisible(driver, By.id("infantPPIssuingCountry" + i), 2)) {

								safeType(driver, By.id("infantPPIssuingCountry" + i), "Indi");

								elementVisible(driver, By.xpath("//a[contains(text(),'India')]"), 5);

								safeClick(driver, By.xpath("//a[contains(text(),'India')]"));

							}



							if (elementVisible(driver, By.xpath("//input[@data-idfield='infantNationality" + i + "']"),

									2)) {

								safeType(driver, By.xpath("//input[@data-idfield='infantNationality" + i + "']"),

										"India");

								safeClick(driver, By.xpath("//a[contains(text(),'India')]"));

							}



							boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Visa_type") + i),

									1);

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport") + i), 2))

								safeType(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport") + i),

										"F435262"+getRandomNo(4));

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Country") + i), 1))

								autoCompletePassportIssuing(driver, dataFile.value("ippissuingcountry"),

										By.id(getValue("AirCom_BookStep3_Infant_Passport_Country") + i), "2");



							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_day") + i),

									1)) {

								try {

									safeSelect(driver,

											By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_day") + i),

											dataFile.value("ippday"));

								} catch (Exception e) {

									safeSelectByValue(driver,

											By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_day") + i),

											dataFile.value("ippday"));

								}

								try {

									safeSelect(driver,

											By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_month") + i),

											dataFile.value("8"));

								} catch (Exception e) {

									safeSelectByValue(driver,

											By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_month") + i),

											dataFile.value("ippmonth"));

								}

								try {

									safeSelect(driver,

											By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_year") + i),

											dataFile.value("ippyear"));

								} catch (Exception e) {

									safeSelectByValue(driver,

											By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_year") + i),

											dataFile.value("ippyear"));

								}

							}

							if (visa)

								try {

									smartSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e) {

									try {

										safeSelectByValue(driver,

												By.id(getValue("AirCom_BookStep3_Infant_Visa_type") + i),

												dataFile.value("visatype"));

									} catch (Exception e1) {

										addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

									}



								}

						}

					} else {

						addLog("Infant " + i + " is not displayed");

					}

				}

			}

		} else {

			addLog("Pax entry for LCC Flight Combination", true);

			for (int i = 1; i <= num_of_adults; i++) {

				boolean adult_name_details = elementVisible(driver,

						By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i), 5);

				if (adult_name_details) {

					try {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i),

								dataFile.value("atitle"));

					} catch (Exception e) {

						safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i),

								dataFile.value("atitle"));

					}

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultFirstName") + i),

							"ADTF" + generateRandomWord(4));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultLastName") + i),

							"ADTL" + generateRandomWord(4));

					if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""), 1)) {

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

									dataFile.value("adobday"));

						} catch (Exception e) {

							safeSelectByValue(driver,

									By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

									dataFile.value("adobday"));

						}

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBMonth") + i + ""),

									"1");

						} catch (Exception e) {

							safeSelectByValue(driver,

									By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBMonth") + i + ""), "1");

						}

						try {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBYear") + i + ""),

									dataFile.value("adobyear"));

						} catch (Exception e) {

							safeSelectByValue(driver,

									By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBYear") + i + ""),

									dataFile.value("adobyear"));

						}

					}



					if (elementVisible(driver, By.xpath("//input[@data-idfield='adultNationality" + i + "']"), 1)) {

						sendKeysByKeyboard(driver, By.xpath("//input[@data-idfield='adultNationality" + i + "']"),

								"Indi");

						pressEnterKey(driver);

					}

					if (elementPresent(driver, By.xpath("//*[text()='Birth country']"), 1)) {

						safeType(driver, By.xpath("//*[text()='Birth country']//..//../*[2]/span/input"), "Indi");

						List<WebElement> we = driver.findElements(By.xpath("//*[@class='list']/a"));

						safeClick(driver, By.xpath("//*[@class='list']/a"));



					}

					// international sector from here



					if (international) {

						if (elementVisible(driver, By.id("adultPPIssuingCountry" + i), 2)) {

							sendKeysByKeyboard(driver, By.id("adultPPIssuingCountry" + i), "India");

							pressEnterKey(driver);

						}

						boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i), 1);

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i), 1))

							safeType(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i),

									"F435262"+getRandomNo(4));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i), 1)) {

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

										dataFile.value("appday"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

										dataFile.value("appday"));

							}

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_month") + i),

										dataFile.value("appmonth"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_month") + i), "7");

							}

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_year") + i),

										dataFile.value("appyear"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_year") + i),

										dataFile.value("appyear"));

							}

						}

						if (visa) {

							try {

								smartSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

										dataFile.value("visatype"));

							} catch (Exception e) {

								try {

									safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e1) {

									addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

								}



							}

						}



					}

				} else {

					addLog("Adult " + i + " is not displayed");

				}

			}

			for (int i = 1; i <= num_of_children; i++) {

				boolean child_name_details = elementVisible(driver,

						By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i), 1);



				if (child_name_details) {

					try {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i),

								dataFile.value("ctitle"));

					} catch (Exception e) {

						safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i),

								dataFile.value("ctitle"));

					}

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildFirstName") + i),

							dataFile.value("First_Name_C" + i) + generateRandomWord(4));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildLastName") + i),

							dataFile.value("Last_Name_C" + i) + generateRandomWord(4));



					try {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBDay") + i),

								dataFile.value("cdobday"));

					} catch (Exception e) {

						safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBDay") + i),

								dataFile.value("cdobday"));

					}

					try {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBMonth") + i), month);

					} catch (Exception e) {

						safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBMonth") + i), "1");

					}

					try {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBYear") + i), putYear(-3));

					} catch (Exception e) {

						safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBYear") + i),

								putYear(-3));

					}


					if (elementVisible(driver, By.xpath("//input[@data-idfield='childNationality" + i + "']"), 1)) {

						sendKeysByKeyboard(driver, By.xpath("//input[@data-idfield='childNationality" + i + "']"),

								"Indi");

						pressEnterKey(driver);

					}


					// international sector here

					if (international) {

						boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i), 1);

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i), 2))

							safeType(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i),

									"F435262"+getRandomNo(4));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Country") + i), 1)) {

							sendKeysByKeyboard(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Country") + i),

									"India");

							pressEnterKey(driver);

						}



						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i), 1)) {

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

										dataFile.value("cppday"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

										dataFile.value("cppday"));

							}

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_month") + i),

										dataFile.value("cppmonth"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_month") + i),

										dataFile.value("8"));

							}

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_year") + i),

										dataFile.value("cppyear"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_year") + i),

										dataFile.value("cppyear"));

							}



							if (elementVisible(driver, By.xpath("//input[@data-idfield='childNationality" + i + "']"),

									1)) {

								sendKeysByKeyboard(driver,

										By.xpath("//input[@data-idfield='childNationality" + i + "']"), "India");

								pressEnterKey(driver);

							}

						}

						if (visa)

							try {

								smartSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

										dataFile.value("visatype"));

							} catch (Exception e) {

								try {

									safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e1) {

									addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

								}

							}

					}

				} else {

					addLog("Child " + i + " is not displayed");

				}

			}

			for (int i = 1; i <= num_of_infants; i++) {

				boolean infant_name_details = elementVisible(driver,

						By.id(getValue("AirCom_BookStep3_Traveller_InfantTitle") + i), 1);



				if (infant_name_details) {

					try {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantTitle") + i),

								dataFile.value("ititle"));

					} catch (Exception e) {

						safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantTitle") + i),

								dataFile.value("ititle"));

					}

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantFname") + i),

							dataFile.value("First_Name_I" + i) + generateRandomWord(2));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantLname") + i),

							dataFile.value("Last_Name_I" + i) + generateRandomWord(2));

					try {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobDay") + i),

								dataFile.value("idobday"));

					} catch (Exception e) {

						safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobDay") + i),

								dataFile.value("idobday"));

					}

					try {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobMonth") + i), Dob_month);

					} catch (Exception e) {

						safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobMonth") + i),

								"1");

					}

					try {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobYear") + i), putYear(0));

					} catch (Exception e) {

						safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobYear") + i),

								putYear(0));

					}



					// international sector here

					if (international) {

						boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Visa_type") + i), 1);

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport") + i), 1))

							safeType(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport") + i),

									"F435262"+getRandomNo(4));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Country") + i), 1)) {

							sendKeysByKeyboard(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Country") + i),

									"India");

							pressEnterKey(driver);

						}



						if (elementPresent(driver, By.xpath("//*[text()='Birth country']"), 1)) {

							System.out.println("able to add ");

							safeType(driver, By.xpath("//*[text()='Birth country']//..//../*[2]/span/input"), "Indi");

							safeClick(driver, By.xpath("//*[contains(text()='India']"));



						}



						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_day") + i),

								1)) {

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_day") + i),

										dataFile.value("ippday"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_day") + i),

										dataFile.value("ippday"));

							}

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_month") + i),

										dataFile.value("ippmonth"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_month") + i),

										dataFile.value("8"));

							}

							try {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_year") + i),

										dataFile.value("ippyear"));

							} catch (Exception e) {

								safeSelectByValue(driver,

										By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_year") + i),

										dataFile.value("ippyear"));

							}



							if (elementVisible(driver, By.xpath("//input[@data-idfield='infantNationality" + i + "']"),

									1)) {

								sendKeysByKeyboard(driver,

										By.xpath("//input[@data-idfield='infantNationality" + i + "']"), "Indi");

								pressEnterKey(driver);

							}
							

						}

						if (visa)

							try {

								smartSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Visa_type") + i),

										dataFile.value("visatype"));

							} catch (Exception e) {

								try {

									safeSelectByValue(driver, By.id(getValue("AirCom_BookStep3_Infant_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e1) {

									addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

								}

							}

					}

				} else {

					addLog("Infant " + i + " is not displayed");

				}

			}

		}

	}



	public void fillTravellersDetailsAR(RemoteWebDriver driver, int num_of_adults, int num_of_children,

			int num_of_infants, boolean international) throws Exception {

		String month = "Jan";

		String Dob_month = month;

		boolean GDS = b2cGDSAirlines(driver, getObject("AirCom_BookStep3__GDS_flight_check"));

		// elementVisible(driver,

		// By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + "1"), 30);

		elementPresent(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + "1"), 10);

		printInfo("Traveller details Step reached");

		// --------------------------------------------GDS

		// PAX------------------------------------------//

		if (GDS) {

			// System.out.println("Pax entry for GDS Flight Combination");

			addLog("Pax entry for GDS Flight Combination");

			if (num_of_adults > 0) {

				for (int i = 1; i <= num_of_adults; i++) {

					boolean adult_name_details = elementVisible(driver,

							By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i), 5);

					if (adult_name_details) {

						// System.out.println(dataFile.value("afname" +(char)65+

						// i));

						safeSelectar(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i),

								dataFile.value("atitle"));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultFirstName") + i),

								dataFile.value("afname" + i));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultLastName") + i),

								dataFile.value("alname" + i));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

								1)) {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

									dataFile.value("adobday"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBMonth") + i + ""),

									dataFile.value("adobmonth"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBYear") + i + ""),

									dataFile.value("adobyear"));

						}

						if (elementPresent_Time(driver, By.xpath("//div[" + i + "]/div/div/dl[2]/dl/dd/input"), 1)) {



							safeAutocomplete(driver, getObject("AirCom_bookstep_travelles_nationality"),

									getObject("AirCom_BookStep4_Amex_Billing_Autocomplete"), "india");

						}

						if (elementVisible(driver, By.xpath("//input[@placeholder='Nationality']"), 1)) {

							safeType(driver, By.xpath("//input[@placeholder='Nationality']"), "Indi");

							safeClick(driver, By.xpath("//a[contains(text(),'India')]"));

						}



						// international sector from here

						if (international) {

							boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

									1);

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i), 1))

								safeType(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i),

										dataFile.value("apassport"));

							if (waitElement(driver, By.id("adultPPIssuingCountry" + i), 1))

								safeType(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i),

										dataFile.value("apassport"));

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Country") + i), 1))

								autoCompletePassportIssuing(driver, dataFile.value("appissuingcountry"),

										By.id(getValue("AirCom_BookStep3_Adult_Passport_Country") + i), "2");

							if (elementPresent(driver, By.id("adultPPIssuingCountry" + i), 1)) {

								safeType(driver, By.id("adultPPIssuingCountry" + i), "Indi");

								safeClick(driver, By.xpath("//a[contains(text(),'India')]"));



							}

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

									1)) {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

										dataFile.value("appday"));

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_month") + i),

										dataFile.value("appmonth"));

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_year") + i),

										dataFile.value("appyear"));

							}

							if (visa)

								try {

									smartSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e1) {

									addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

								}

						}

					} else {

						addLog("Infant " + i + " is not displayed");

					}

				}

			}

			if (num_of_children > 0) {



				for (int i = 1; i <= num_of_children; i++) {

					boolean child_name_details = elementVisible(driver,

							By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i), 1);



					if (child_name_details) {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i),

								dataFile.value("ctitle"));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildFirstName") + i),

								dataFile.value("cfname" + i));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildLastName") + i),

								dataFile.value("clname" + i));



						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBDay") + i),

								dataFile.value("cdobday"));

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBMonth") + i), month);

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBYear") + i), putYear(-3));

						;

						if (elementVisible(driver, By.xpath("//input[@data-idfield='childNationality1']"), 2)) {

							safeType(driver, By.xpath("//input[@data-idfield='childNationality1']"), "India");

							safeClick(driver, By.xpath("//a[contains(text(),'India')]"));

						}

						// international sector here

						if (international) {

							boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

									1);

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i), 2))

								safeType(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i),

										dataFile.value("cpassport"));

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Country") + i), 2))

								autoCompletePassportIssuing(driver, dataFile.value("cppissuingcountry"),

										By.id(getValue("AirCom_BookStep3_Child_Passport_Country") + i), "3");

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

									1)) {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

										dataFile.value("cppday"));

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_month") + i),

										dataFile.value("cppmonth"));

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_year") + i),

										dataFile.value("cppyear"));

							}

							if (visa)

								try {

									smartSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e1) {

									addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

								}

						}

					} else {

						addLog("Child " + i + " is not displayed");

					}

				}

			}



			if (num_of_infants > 0) {



				for (int i = 1; i <= num_of_infants; i++) {

					boolean infant_name_details = elementVisible(driver,

							By.id(getValue("AirCom_BookStep3_Traveller_InfantTitle") + i), 1);



					if (infant_name_details) {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantTitle") + i),

								dataFile.value("ititle"));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantFname") + i),

								dataFile.value("ifname" + i));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantLname") + i),

								dataFile.value("ilname" + i));

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobDay") + i),

								dataFile.value("idobday"));

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobMonth") + i), Dob_month);

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobYear") + i), putYear(0));



						// international sector here

						if (international) {

							if (elementVisible(driver, By.id("adultPPIssuingCountry" + i), 2)) {

								safeType(driver, By.id("adultPPIssuingCountry" + i), "Indi");

								elementVisible(driver, By.xpath("//a[contains(text(),'India')]"), 5);

								safeClick(driver, By.xpath("//a[contains(text(),'India')]"));

							}

							boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Visa_type") + i),

									1);

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport") + i), 2))

								safeType(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport") + i),

										dataFile.value("ipassport"));

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Country") + i), 1))

								autoCompletePassportIssuing(driver, dataFile.value("ippissuingcountry"),

										By.id(getValue("AirCom_BookStep3_Infant_Passport_Country") + i), "4");



							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_day") + i),

									1)) {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_day") + i),

										dataFile.value("ippday"));

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_month") + i),

										dataFile.value("ippmonth"));

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_year") + i),

										dataFile.value("ippyear"));

							}

							if (visa)

								try {

									smartSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e1) {

									addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

								}

						}

					} else {

						addLog("Infant " + i + " is not displayed");

					}

				}

			}

		} else {

			System.out.println("Pax entry for LCC Flight Combination");

			addLog("Pax entry for LCC Flight Combination");

			for (int i = 1; i <= num_of_adults; i++) {

				boolean adult_name_details = elementVisible(driver,

						By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i), 5);

				if (adult_name_details) {

					// System.out.println(dataFile.value("First_Name_A"+i));

					safeSelectar(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i),

							dataFile.value("atitle"));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultFirstName") + i),

							dataFile.value("First_Name_A" + i));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultLastName") + i),

							dataFile.value("Last_Name_A" + i));

					if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""), 1)) {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

								dataFile.value("adobday"));

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBMonth") + i + ""),

								dataFile.value("adobmonth"));

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBYear") + i + ""),

								dataFile.value("adobyear"));

					}

					if (elementVisible(driver, By.xpath("//input[@placeholder='Nationality']"), 1)) {

						safeType(driver, By.xpath("//input[@placeholder='Nationality']"), "Indi");

						safeClick(driver, By.xpath("//a[contains(text(),'India')]"));

					}



					// international sector from here

					if (international) {

						if (elementVisible(driver, By.id("adultPPIssuingCountry" + i), 2)) {

							safeType(driver, By.id("adultPPIssuingCountry" + i), "Indi");

							elementVisible(driver, By.xpath("//a[contains(text(),'India')]"), 5);

							safeClick(driver, By.xpath("//a[contains(text(),'India')]"));

						}



						boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i), 1);

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i), 1))

							safeType(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i),

									dataFile.value("apassport"));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Country") + i), 1))

							autoCompletePassportIssuing(driver, dataFile.value("appissuingcountry"),

									By.id(getValue("AirCom_BookStep3_Adult_Passport_Country") + i), "2");

						if (elementPresent(driver, By.id("adultPPIssuingCountry" + i), 1)) {

							safeType(driver, By.id("adultPPIssuingCountry" + i), "Indi");

							safeClick(driver, By.xpath("//a[contains(text(),'India')]"));



						}

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i), 1)) {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

									dataFile.value("appday"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_month") + i),

									dataFile.value("appmonth"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_year") + i),

									dataFile.value("appyear"));

						}

						if (visa)

							try {

								smartSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

										dataFile.value("visatype"));

							} catch (Exception e1) {

								addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

							}

					}

				} else {

					addLog("Adult " + i + " is not displayed");

				}

			}

			for (int i = 1; i <= num_of_children; i++) {

				boolean child_name_details = elementVisible(driver,

						By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i), 1);



				if (child_name_details) {

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i),

							dataFile.value("ctitle"));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildFirstName") + i),

							dataFile.value("First_Name_C" + i));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildLastName") + i),

							dataFile.value("Last_Name_C" + i));



					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBDay") + i),

							dataFile.value("cdobday"));

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBMonth") + i), month);

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBYear") + i), putYear(-3));

					;



					// international sector here

					if (international) {

						boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i), 1);

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i), 2))

							safeType(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i),

									dataFile.value("cpassport"));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Country") + i), 1))

							autoCompletePassportIssuing(driver, dataFile.value("cppissuingcountry"),

									By.xpath("//a[contains(text(),'India')]"), "3");

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i), 1)) {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

									dataFile.value("cppday"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_month") + i),

									dataFile.value("cppmonth"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_year") + i),

									dataFile.value("cppyear"));

						}

						if (visa)

							try {

								smartSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

										dataFile.value("visatype"));

							} catch (Exception e1) {

								addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

							}

					}

					if (elementVisible(driver, By.xpath("//input[@data-idfield='childNationality1']"), 2)) {

						safeType(driver, By.xpath("//input[@data-idfield='childNationality1']"), "India");

						safeClick(driver, By.xpath("//a[contains(text(),'India')]"));

					}

				} else {

					addLog("Child " + i + " is not displayed");

				}

			}

			for (int i = 1; i <= num_of_infants; i++) {

				boolean infant_name_details = elementVisible(driver,

						By.id(getValue("AirCom_BookStep3_Traveller_InfantTitle") + i), 1);



				if (infant_name_details) {

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantTitle") + i),

							dataFile.value("ititle"));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantFname") + i),

							dataFile.value("First_Name_I" + i));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantLname") + i),

							dataFile.value("Last_Name_I" + i));

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobDay") + i),

							dataFile.value("idobday"));

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobMonth") + i), Dob_month);

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_InfantDobYear") + i), putYear(0));



					// international sector here

					if (international) {

						boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Visa_type") + i), 1);

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport") + i), 1))

							safeType(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport") + i),

									dataFile.value("ipassport"));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Country") + i), 1))

							autoCompletePassportIssuing(driver, dataFile.value("ippissuingcountry"),

									By.id(getValue("AirCom_BookStep3_Infant_Passport_Country") + i), "4");

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_day") + i),

								1)) {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_day") + i),

									dataFile.value("ippday"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_month") + i),

									dataFile.value("ippmonth"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Passport_Expiry_year") + i),

									dataFile.value("ippyear"));

						}

						if (visa)

							try {

								smartSelect(driver, By.id(getValue("AirCom_BookStep3_Infant_Visa_type") + i),

										dataFile.value("visatype"));

							} catch (Exception e1) {

								addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

							}

					}

				} else {

					addLog("Infant " + i + " is not displayed");

				}

			}

		}

	}



	public boolean b2cGDSAirlines(RemoteWebDriver driver, By by) throws Exception {

		String airItinerary = getText(driver, by);

		boolean GDS_Flight = false;

		for (int i = 0; i < number_gds_Airlines; i++) {

			if (airItinerary.toLowerCase().contains(gds_airlines[i].toLowerCase())) {

				GDS_Flight = true;

				break;

			}

		}

		return GDS_Flight;

	}



	public void fillTravellersContact(RemoteWebDriver driver) throws Exception {

		driver.findElement(getObject("air_traveller_details_contactPhoneNumber")).clear();

		safeType(driver, getObject("air_traveller_details_contactPhoneNumber"), dataFile.value("MobileNo"));

	}



	public String capatilizeFirstLetter(String str) {

		char ch[] = str.toCharArray();

		for (int i = 0; i < str.length(); i++) {

			if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {

				if (ch[i] >= 'a' && ch[i] <= 'z') {

					ch[i] = (char) (ch[i] - 'a' + 'A');

				}

			} else if (ch[i] >= 'A' && ch[i] <= 'Z')

				ch[i] = (char) (ch[i] + 'a' - 'A');

		}

		String st = new String(ch);

		return st;

	}



	public void autoCompletePassportIssuing(RemoteWebDriver driver, String text, By by, String autoComplete)

			throws Exception {

		int nextAutoComplete = Integer.parseInt(autoComplete) + 3;

		int nextAutoComplete2 = Integer.parseInt(autoComplete) + 4;

		safeClick(driver, by);

		safeType(driver, by, text);

//		String countryName=capatilizeFirstLetter(text);

//		safeClick(driver, By.xpath("//li[@class='list']/a[contains(text(),'"+countryName+"')]"));

		boolean auto = elementVisible(driver, By.xpath("//div[" + autoComplete + "]/nav/ul/li"), 2);

		if (auto) {

			safeClick(driver, By.xpath("//div[" + autoComplete + "]/nav/ul/li"));

		} else if (elementVisible(driver, By.xpath("//div[" + nextAutoComplete + "]/nav/ul/li"), 2))

			safeClick(driver, By.xpath("//div[" + nextAutoComplete + "]/nav/ul/li"));

		else if (elementVisible(driver, By.xpath("//div[" + nextAutoComplete2 + "]/nav/ul/li"), 2))

			safeClick(driver, By.xpath("//div[" + nextAutoComplete2 + "]/nav/ul/li"));

		// if(elementVisible(driver,By.xpath("//ul[@class='autoComplete']"),1)){

		List<WebElement> we = driver.findElements(By.xpath("//ul[@class='autoComplete']"));

		System.out.println("size=" + we.size());

		for (int i = 0; i < we.size(); i++) {

			System.out.println(i);

			System.out.println(we.get(i).getText());

			String ctext = we.get(i).getText();

			if (!ctext.equals("")) {

				we.get(i).click();

			}



		}

//		we.get(1).click();

	}



	public boolean CashPayment(RemoteWebDriver driver, String payment_method) throws Exception {



		elementPresent_Time(driver, By.linkText("Cash"), 20);

		safeClick(driver, By.linkText("Cash"));

		assertTrue("Cash Payment Mode", elementPresent_Time(driver, By.xpath("//div[@id='payByCashUAE']/h6"), 10));

		Thread.sleep(5000);

		printInfo("Payment details tab loaded");



		if (payment_method.equalsIgnoreCase("uaexchange")) {

			safeClick(driver, By.id("uae_exchange"));

			addLog("Cash Payment Mode - uae_exchange");

			printInfo("Cash Payment Mode - uae_exchange");

			Thread.sleep(1000);

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));



		} else if (payment_method.equalsIgnoreCase("alansari")) {

			safeClick(driver, By.id("alansari_exchange"));

			addLog("Cash Payment Mode - alansari_exchange");

			printInfo("Cash Payment Mode - alansari_exchange");

			Thread.sleep(1000);

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));



		}

		return true;

	}



	public void walletOptOut(RemoteWebDriver driver) throws Exception {

		Thread.sleep(1000);



		if (waitElement(driver, getObject("AirCom_BookStep4_CreditCard_Tab"), 2)) {

			if (driver.findElement(By.id("walletOptOut")).isSelected())

				driver.findElement(By.id("walletOptOut")).click();

		}

	}



	public void walletOptIn(RemoteWebDriver driver) throws Exception {

		Thread.sleep(1000);



		if (waitElement(driver, getObject("AirCom_BookStep4_CreditCard_Tab"), 2)) {

			assertTrue("No money in wallet. Error!", waitElement(driver, By.id("walletOptOut"), 10));

			if (!driver.findElement(By.id("walletOptOut")).isSelected())

				driver.findElement(By.id("walletOptOut")).click();

		}

	}



	// cardNo 1-> migs 2-> amex2d 3-> amex 3d

	public boolean b2cPayment(RemoteWebDriver driver, String payment_method, int cardNo) throws Exception {

		// Thread.sleep(1000);

		if (elementVisible(driver, By.id("priceChangeDownBtn"), 2)) {

			addLog("Accepting price change down button.",true);

			safeClick(driver, By.id("priceChangeDownBtn"));

		}

		if (!(waitElement(driver, getObject("AirCom_BookStep4_CreditCard_Tab"), 20)))

			return false;

		addLog("Payment details tab loaded", true);

		// walletOptOut(driver);

		if (payment_method.equalsIgnoreCase("credit card")) {

			safeClick(driver, getObject("AirCom_BookStep4_CreditCard_Tab"));

			addLog("Credit card selected as payment mode", true);


Thread.sleep(1000);
			fillCCdetails(driver, cardNo);

			check_consent(driver, getObject("AirCom_BookStep4_Consent"));

		} else if (payment_method.equalsIgnoreCase("S2S")) {

			safeClick(driver, getObject("AirCom_BookStep4_CreditCard_Tab"));

			addLog("Credit card selected as payment mode", true);

			printInfo("Credit card selected as payment mode");



			fillCCdetails(driver, cardNo);

			check_consent(driver, getObject("AirCom_BookStep4_Consent"));

		} else if (payment_method.equalsIgnoreCase("debit card")) {

			safeClick(driver, getObject("AirCom_BookStep4_DebitCard_Tab"));

			addLog("Debit card selected as payment mode", true);

			printInfo("Debit card selected as payment mode");



			fillCCdetails(driver, cardNo);

			check_consent(driver, getObject("AirCom_BookStep4_Consent"));

		} else if (payment_method.equalsIgnoreCase("storedcardamex")) {

			safeClick(driver, By.linkText("Stored cards"));

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "1234");

			addLog("Storedcard Payment Mode", true);

			printInfo("Storedcard Payment Mode");



		} else if (payment_method.equalsIgnoreCase("EMI")) {

			safeClick(driver, By.id("EMITab"));

			safeClick(driver, By.id("bank_list"));

			// safeClick(driver,By.xpath("//*[@id='bank_list' and @label='HDFC']"));

			List<WebElement> we = driver.findElements(By.tagName("option"));

			for (int i = 0; i < we.size(); i++) {

				// System.out.println("-----"+we.get(i).getText());

				if (we.get(i).getText().contains("HDFC")) {

					we.get(i).click();

				}

				// we.get(2).click();

			}



			// safeClick(driver,By.xpath(".//*[@id='bank_list']/option[4]"));

			// safeClick(driver, getObject("AirCom_BookStep4_CreditCard_Tab"));

			addLog("Credit card selected as payment mode", true);



			fillCCdetails(driver, cardNo);

			check_consent(driver, getObject("AirCom_BookStep4_Consent"));

		}



		else if (payment_method.equalsIgnoreCase("storedcardmigs")) {

			safeClick(driver, By.linkText("Stored cards"));

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "123");

			addLog("Storedcard Payment Mode", true);

			printInfo("Storedcard Payment Mode");



		} else if (payment_method.equalsIgnoreCase("giftvoucher")) {

			textPresent(driver, "Your Gift Card", 50);

			addLog("Gift card Payment Mode", true);

			printInfo("Gift card Payment Mode");



		} else if (payment_method.equalsIgnoreCase("wallet")) {

			assertTrue("Wallet as Payment Option is Not Present",

					elementPresent(driver, getObject("AirCom_Payment_Type_WalletLink")));

			safeClick(driver, getObject("AirCom_Payment_Type_WalletLink"));

			/*

			 * assertTrue("PayU Wallet Option Not Available", elementPresent(driver,

			 * getObject("AirCom_Payment_Type_WalletRadioBtn")));

			 */

			// List<WebElement> we=driver.findElements(By.xpath("//li/label"));

			// System.out.println("size="+we.size());

			/*

			 * for(int i=0;i<we.size();i++){

			 * //System.out.println("radiobuttons==="+we.get(i).getText());

			 * if(we.get(i).getText().equalsIgnoreCase("PayU")){ we.get(i).click(); } }

			 */

			// safeClick(driver,

			// getObject("AirCom_Payment_Type_WalletRadioBtn"));



			safeClick(driver, By.xpath("//li[@data-paymentsubtype='PAYU_WALLET']/label/input"));



			addLog("PayU Wallet Option Selected", true);



		} else if (payment_method.equalsIgnoreCase("ctwallet")) {

		

			  safeClick(driver, getObject("AirCom_Payment_Type_WalletLink"));

			  safeClick(driver, By.id("cleartrip_wallet"));



			// walletOptIn(driver);

			addLog("CTWallet Option Selected", true);



		} else if (payment_method.equalsIgnoreCase("partialctwallet")) {

			assertTrue("Wallet as Payment Option is Not Present",

					elementPresent(driver, getObject("AirCom_Payment_Type_WalletLink")));

			safeClick(driver, getObject("AirCom_Payment_Type_WalletLink"));

			assertTrue("CTWallet Option Not Available", elementPresent(driver, By.id("cleartrip_wallet")));

			safeClick(driver, By.id("cleartrip_wallet"));



			addLog("CTWallet Option Selected", true);

			printInfo("CTWallet Option Selected");

			fillCCdetails(driver, cardNo);

			check_consent(driver, getObject("AirCom_BookStep4_Consent"));



		}



		if (MakePaymentTrue && payment_method.equalsIgnoreCase("giftvoucher")) {

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

		} else if (MakePaymentTrue && payment_method.equalsIgnoreCase("ctwallet")) {

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));



		} else if (MakePaymentTrue && payment_method.equalsIgnoreCase("wallet")) {



			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			// waitElement(driver, getObject("AirCom_PayUWallet_Email"), 60);

			Thread.sleep(5000);

			if (waitElement(driver, getObject("AirCom_PayUWallet_Email"), 60)) {

				addLog("Arrtived on PayU Page", true);

				printInfo("Arrtived on PayU Page");

			} else {

				assertTrue("Pay U page not loaded. Error!", false);

			}

			// payUsignIn(driver,dataFile.value("PayUUserName"),

			// dataFile.value("PayUPassword"));



			// elementPresent_Time(driver,

			// getObject("PayU_SignIn_ForgotPasword_link"), 30);

			// Thread.sleep(5000);

			if (elementPresent(driver, getObject("PayU_SignIn_ForgotPasword_link"))) {

				safeType(driver, getObject("PayU_SignIn_EmailTxtFld"), "");

				safeType(driver, getObject("PayU_SignIn_EmailTxtFld"), dataFile.value("PayUUserName"));

				safeType(driver, getObject("PayU_SignIn_PasswordTxtFld"), "");

				safeType(driver, getObject("PayU_SignIn_PasswordTxtFld"), dataFile.value("PayUPassword"));

				safeClick(driver, getObject("PayU_SignIn_SubmitBtn"));

				Thread.sleep(2000);

			}

			waitElement(driver, By.xpath("//*[@id='cardDiv']/div/ul/li[2]/a"), 10);

			driver.findElementByXPath("//*[@id='cardDiv']/div/ul/li[2]/a").isEnabled();

			Thread.sleep(5000);

			driver.findElementByXPath("//*[@id='cardDiv']/div/ul/li[2]/a").click();

			Thread.sleep(1000);



			safeType(driver, getObject("PayU_CC_Input_TxtFld"), dataFile.value("CCNumber"));

			safeSelect(driver, getObject("PayU_CC_Expiry_DropDown"), "05");

			Thread.sleep(1000);

			new Select(driver.findElement(getObject("PayU_CC_Expiry_Year_DropDown"))).selectByVisibleText("2017");

			Thread.sleep(1000);

			safeSelect(driver, getObject("PayU_CC_Expiry_Year_DropDown"), dataFile.value("CCYear"));

			Thread.sleep(1000);

			safeType(driver, getObject("PayU_CC_CVV"), dataFile.value("CVV"));

			// safeType(driver, getObject("PayU_CC_UserName"),

			// dataFile.value("CCName"));

			/*

			 * List<WebElement> we=driver.findElements(By.xpath(

			 * "//button[contains(@class,'main-btn left')]")); we.get(0).click();

			 */

			Thread.sleep(1000);



			safeSelect(driver, getObject("PayU_CC_Expiry_Year_DropDown"), dataFile.value("CCYear"));

			driver.findElement(getObject("PayU_CC_MakePayment_Btn")).click();

			/*

			 * for(int i=0;i<we.size();i++){ System.out.println("size="+we.size());

			 * System.out.println("searching buttons="+we.get(i).getText()); }

			 */

			// safeClick(driver, getObject("PayU_CC_MakePayment_Btn"));

			Thread.sleep(5000);



		} else if (MakePaymentTrue && payment_method.equalsIgnoreCase("storedcardamex")) {



			scrollToElement(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			// elementPresent_Time(driver,

			// getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 30);

			addLog("Make Payment Button clicked.", true);



			if (cardNo == 3) {

				if (elementVisible(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 90)) {

					elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);

					safeClick(driver, getObject("air_amex_payment_button"));

				}

			}



		} else if (MakePaymentTrue && payment_method.equalsIgnoreCase("storedcardmigs")) {



			scrollToElement(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			// elementPresent_Time(driver,

			// getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 30);

			addLog("Make Payment Button clicked.", true);



			if (cardNo == 1 || cardNo == 4) {

				if (elementVisible(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 90)) {

					elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);

					safeClick(driver, getObject("air_amex_payment_button"));

				}



			}



		} else if (MakePaymentTrue) {

			scrollToElement(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			if (elementVisible(driver, By.id("priceChangeUpBtn"), 3)) {

				addLog("Accepting price change up button after clicking on make payment button",true);

				safeClick(driver, By.id("duplicateBookingContinueBtn"));

			}



			smartClick(driver, By.xpath("//*[@id='couponError']"));



			// duplicateBookingContinueBtn

			if (elementVisible(driver, By.id("priceChangeUpBtn"), 3)) {

				addLog("Accepting price change up button after clicking on make payment button",true);

				safeClick(driver, By.id("priceChangeUpBtn"));

			}

			// elementPresent_Time(driver,

			// getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 30);

			addLog("Make Payment Button clicked.", true);

			addLog("Payment inprogress...", true);

			if (cardNo == 1 || cardNo == 4) {

				if (elementVisible(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 90)) {

					addLog("Payment inprogress...", true);

					elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);

					safeClick(driver, getObject("air_amex_payment_button"));

				}

				addLog("Payment inprogress...", true);

			}

			

			if (cardNo == 3) {

				if (elementVisible(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 90)) {

					elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);

					safeClick(driver, getObject("air_amex_payment_button"));

				}

			}



			if (cardNo == 8) {

				if (elementVisible(driver, getObject("AirCom_Interstitial_Payfort_3Dsecure_Confirm_Button"), 90)) {

					addLog("Navigated to Payfort 3D secure page", true);

					safeType(driver, getObject("AirCom_Interstitial_Payfort_3Dsecure_OTP"), "12345");

					safeClick(driver, getObject("AirCom_Interstitial_Payfort_3Dsecure_Confirm_Button"));

				}

				addLog("Payment inprogress...", true);

			}

			if (cardNo == 9) {

				textPresent(driver, "Send PARes to TermUrl", 20);

				if (elementVisible(driver, getObject("AirCom_Interstitial_PayUS2S_3Dsecure_Confirm_Button"), 50)) {

					addLog("Navigated to Payfort 3D secure page", true);

					safeClick(driver, getObject("AirCom_Interstitial_PayUS2S_3Dsecure_Confirm_Button"));

				}

				addLog("Payment inprogress...", true);

			}



		} else {

			addLog("Payment skipped as payment flag is not set.", true);

			return false;

		}

		smartClick(driver, By.xpath("//*[@id='couponError']"));

		

		for (int i = 0; i < 20; i++) {

			if (!driver.getCurrentUrl().contains("confirmation")) {

				Thread.sleep(5000);

			} else {

				elementPresent(driver, By.id("print_tickets"), 20);

				break;

			}

		}

		

		return true;

	}



	public void npsFeature(RemoteWebDriver driver) throws Exception {

		safeClick(driver, By.xpath("//*[@class='npsScale__cell'][9]"));

		waitForElement(driver, 10, By.xpath("//textarea"));

		safeClick(driver, By.xpath("//textarea"));

		safeType(driver, By.xpath("//textarea"), "testing");

		safeClick(driver, By.xpath("//input[@type='submit']"));

		waitForElement(driver, 10, By.xpath("//*[contains(text(),'We appreciate')]"));

		String x = getText(driver, By.xpath("//*[contains(text(),'We appreciate')]"));

		Assert.assertEquals(" We appreciate you taking the time to help us do better. ".trim(), x.trim());

	}



	public void betab2cPayment(RemoteWebDriver driver, int cardNo) throws Exception {



		safeClick(driver, getObject("AirCom_BookStep4_CreditCard_Tab"));

		addLog("Credit card selected as payment mode", true);

		printInfo("Credit card selected as payment mode");

		safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), "5123456789012349");

		safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("CCMonth"));

		safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("CCYear"));

		safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"), dataFile.value("CCName"));

		safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("CVV"));

		// fillCCdetails(driver, cardNo);

		check_consent(driver, getObject("AirCom_BookStep4_Consent"));



	}



	public void check_consent(RemoteWebDriver driver, By by) throws Exception {

		WebElement confirm = driver.findElement(by);

		if (!confirm.isSelected()) {

			scrollToElement(driver, by);

			safeClick(driver, by);

		}

	}



	public void setCardNumber(int i) {

		this.cardUsed.add(i);

	}



	public List<Integer> getCardNumber() {

		return this.cardUsed;

	}



	public void fillCCdetails(RemoteWebDriver driver, int i) throws Exception {



		switch (i) {



		case 1:

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("CCNumber"));
			Thread.sleep(1000);
Thread.sleep(1000);
		safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("CCMonth"));
///driver.findElement(new ByAll(By.xpath("AirCom_BookStep4_CreditCard_Exp_Month"),By.id("CcExpirationMonth"))).sendKeys("5");
			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("CCYear"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"), dataFile.value("CCName"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("CVV"));

			if (elementVisible(driver, getObject("AirCom_BookStep4_Amex_Billing_Address"), 2)) {

				Thread.sleep(5000);

				safeClick(driver, getObject("AirCom_BookStep4_Amex_Billing_Address"));

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_Address"), "cleartrip");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_City"), "bangalore");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_State"), "karnataka");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_Pin"), "560078");

				safeAutocomplete(driver, getObject("AirCom_BookStep4_Amex_Billing_Country"),

						getObject("AirCom_BookStep4_Amex_Billing_Autocomplete"), "india");

			}

			break;



		case 2:

			addLog("Using Amex 2D Card", true);

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("AmexNumber_2D"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("AmexMonth_2D"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("AmexYear_2D"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"), dataFile.value("AmexName_2D"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("AmexCVV_2D"));

			if (elementVisible(driver, getObject("AirCom_BookStep4_Amex_Billing_Address"), 5)) {

				Thread.sleep(5000);

				safeClick(driver, getObject("AirCom_BookStep4_Amex_Billing_Address"));

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_Address"), "cleartrip");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_City"), "bangalore");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_State"), "karnataka");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_Pin"), "560078");

				safeAutocomplete(driver, getObject("AirCom_BookStep4_Amex_Billing_Country"),

						getObject("AirCom_BookStep4_Amex_Billing_Autocomplete"), "india");

			}

			if (waitElement(driver, By.id("native_currency"), 1)) {

				List<WebElement> we1 = driver.findElements(By.id("native_currency"));

				System.out.println("size=" + we1.size());

				if (we1.size() > 1) {

					we1.get(0).click();

				}

			}

			break;



		case 3:

			addLog("Using Amex 3D Card", true);

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("AmexNumber_3D"));

			// safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"),

			// dataFile.value("AmexNumber_3D"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("AmexMonth_3D"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("AmexYear_3D"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"), dataFile.value("AmexName_3D"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("AmexCVV_3D"));



			Thread.sleep(2000);

			if (elementVisible(driver, getObject("AirCom_BookStep4_Amex_Billing_Address"), 5)) {

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_Address"), "");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_Address"), "cleartrip");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_City"), "");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_City"), "bangalore");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_State"), "");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_State"), "karnataka");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_Pin"), "");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_Pin"), "560078");

				safeType(driver, getObject("AirCom_BookStep4_Amex_Billing_Country"), "");

				safeAutocomplete(driver, getObject("AirCom_BookStep4_Amex_Billing_Country"),

						getObject("AirCom_BookStep4_Amex_Billing_Autocomplete"), "india");

			}

			if (waitElement(driver, By.id("native_currency"), 1)) {

				List<WebElement> we1 = driver.findElements(By.id("native_currency"));

				System.out.println("size=" + we1.size());

				if (we1.size() > 1) {

					we1.get(0).click();

				}

			}

			break;



		case 4:

			addLog("Using MC Debit Card", true);

			safeType(driver, getObject("AirCom_BookStep4_DebitCard_No"), dataFile.value("CCNumber"));

			safeSelect(driver, getObject("AirCom_BookStep4_DebitCard_Exp_Month"), dataFile.value("CCMonth"));

			safeSelect(driver, getObject("AirCom_BookStep4_DebitCard_Exp_Year"), dataFile.value("CCYear"));

			safeType(driver, getObject("AirCom_BookStep4_DebitCard_HolderNamer"), dataFile.value("CCName"));

			safeType(driver, getObject("AirCom_BookStep4_DebitCard_CVV"), dataFile.value("CVV"));

			break;



		case 5:

			addLog("Using Intl MC Card", true);

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("IntlCCNumber"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("CCMonth"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("CCYear"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"), dataFile.value("CCName"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("CVV"));

			break;



		case 6:

			addLog("Using MC Incorrect Expiry Card", true);

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("CCNumber"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("CCMonth"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"),

					dataFile.value("IncorrectCCYearOfExpiry"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"), dataFile.value("CCName"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("CVV"));

			break;



		case 7:

			addLog("Using Intl Debit MC Card", true);

			safeType(driver, getObject("AirCom_BookStep4_DebitCard_No"), dataFile.value("IntlCCNumber"));

			safeSelect(driver, getObject("AirCom_BookStep4_DebitCard_Exp_Month"), dataFile.value("CCMonth"));

			safeSelect(driver, getObject("AirCom_BookStep4_DebitCard_Exp_Year"), dataFile.value("CCYear"));

			safeType(driver, getObject("AirCom_BookStep4_DebitCard_HolderNamer"), dataFile.value("CCName"));

			safeType(driver, getObject("AirCom_BookStep4_DebitCard_CVV"), dataFile.value("CVV"));

			break;



		case 8:

			addLog("Using Visa 3-D Card on PayFort gateway", true);

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("3DVisaCard_PayFort"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("CCMonth"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("CCYear"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"), dataFile.value("CCName"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("CVV"));

			break;



		case 9:

			addLog("Using S2S Card gateway", true);



			safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("PayUS2S_Card"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("CCMonth"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("CCYear"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"), dataFile.value("CCName"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("CVV"));

			break;



		} // TODO add details to accept password for3D card usage break;



	}



	public boolean fillwrongccdetails(RemoteWebDriver driver) throws Exception {

		boolean check = false;

		safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), "512345678102");

		safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("CCMonth"));

		safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("CCYear"));

		safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"), dataFile.value("CCName"));

		safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), "123");

		safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

		waitForElement(driver, 50, By.id("paymentBlockError"));



		if (driver.findElement(By.xpath("//*[text()='Credit Card']")).getText().contains("Credit")) {

			check = true;

		}

		// System.out.println("---------"+driver.findElement(By.id("paymentBlockError")).getText());

		return check;

	}



	public boolean recheckAirlinePriceProcess(RemoteWebDriver driver) throws Exception {

		Boolean priceChanged = false;

		Boolean SRPLoaded = false;



		for (int i = 1; (i <= 5) && elementVisible(driver, By.id("paymentLoaderMess"), 1); i++) {

			/*

			 * if (waitElement(driver, getObject("AirCorpCom_InterstitialPage_TripID"), 1))

			 * { return false; }

			 */

			if (elementVisible(driver, By.id("priceChangeUpBtn"), 1)) {

				priceChanged = true;

				break;

			}

		}

		if (priceChanged) {

			String price_change_text1 = getText(driver, By.xpath("//div[@id='priceChangeUp']/div/div/p"));

			String price_change_text2 = getText(driver, By.xpath("//div[@id='priceChangeUp']/div/div/h3"));

			addLog(

					"Price Change captured from amount : " + price_change_text1 + " to amount : " + price_change_text2);

			safeClick(driver, By.id("priceChangeUpBtn"));

		}



		for (int i = 0; i < 5; i++) {

			/*

			 * if (waitElement(driver, getObject("AirCorpCom_InterstitialPage_TripID"), 1))

			 * { return false; }

			 */

			SRPLoaded = elementVisible(driver, getObject("AirCom_SRP_Modify_Search_Button"), 1);

			if (SRPLoaded)

				break;

		}

		if (SRPLoaded) {

			addLog("SRP loaded after some failure. Retrying with new sector.");

			printInfo("SRP loaded after some failure. Retrying with new sector.");

			return true;

		} else if (elementVisible(driver, By.id("paymentContinue"), 3)) {

			addLog("\"OOPS! Something broke. Try again.\" error came. Retrying with new sector.");

			printInfo("\"OOPS! Something broke. Try again.\" error came. Retrying with new sector.");

			return true;

		} else if (waitElement(driver, By.xpath(".//*[@id='ResultContainer_1_1']"), 3)) {

			addLog(

					"''Sorry, there aren't any flights available for your search.'' message displayed on redirecting to SRP. Retrying with new sector.");

			System.out.println(

					"''Sorry, there aren't any flights available for your search.'' message displayed on redirecting to SRP. Retrying with new sector.");

			return true;

		}

		return false;

	}



	public boolean recheckAirlinePrice(RemoteWebDriver driver, String flag) throws Exception {

		boolean failure = recheckAirlinePriceProcess(driver);

		if (failure) {

			return true;

		} else if (elementVisible(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 1)) {

			Thread.sleep(20000);

			if (elementVisible(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 1)) {

				return true;

			}

		}

		return false;

	}



	public void recheckAirlinePrice(RemoteWebDriver driver) throws Exception {

		boolean failure = recheckAirlinePriceProcess(driver);

		if (failure) {

			assertTrue("Payment failure. Error!", false);

		} else if (elementVisible(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 1)) {

			Thread.sleep(20000);

			if (elementVisible(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 1)) {

				assertTrue("Taking too much time to rediect to payment gateway. Error!", false);

			}

		}

	}



	public String checkBookingStatus1(RemoteWebDriver driver) throws Exception {

		String checkStatus = null;

		// String TripID =null;



		// Thread.sleep(30000);//changes made on 15th evening ...

		if (waitForElement(driver, 100, getObject("AirCom_Confirmation_BookingDone_TextMsg"))) {

			String confirmationMessage = getText(driver, getObject("AirCom_Confirmation_BookingDone_TextMsg"));

			if (confirmationMessage.equals("Your booking is done")

					|| confirmationMessage.equals("Booking on hold with Pricelock")

					|| confirmationMessage.contains("Well, almost done")

					|| confirmationMessage.contains("Your trip has changed")) {



				// checkStatus = waitElement(driver,

				// getObject("AirCom_Confirmation_TripID"), 1);

				checkStatus = getText(driver, getObject("AirCom_Confirmation_TripID"));

				addLog("Trip Id booked is " + checkStatus, true);

				logger.info(confirmationMessage + checkStatus);

				addLog("Confirmation URL: " + driver.getCurrentUrl(), true);

			} else {

				printInfo("Confirmation message is: " + confirmationMessage);

				printInfo("Confirmation URL: " + driver.getCurrentUrl());

				addLog("Confirmation message is: " + confirmationMessage, true);

				addLog("Confirmation URL: " + driver.getCurrentUrl(), true);

			}

		}

		return checkStatus;

	}



	public boolean checkTripID(RemoteWebDriver driver) throws Exception {

		addLog("Waiting to load booking confirmation page.", true);

		boolean checkStatus = false;

		WebDriverWait webDriverWait = new WebDriverWait(driver, 45);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("getTicket")));

		for (int i = 0; i < 15; i++) {

			if (!driver.getCurrentUrl().contains("confirmation")) {

				Thread.sleep(5000);

			} else {

				break;

			}

		}

		addLog("Retrieving trip id from booking confirmation page.", true);

		if (elementPresent(driver, By.id("getTicket"), 5)) {

			List<WebElement> trip = driver.findElements(By.tagName("p"));

			test: for (int i = 0; i < trip.size(); i++) {

				if (trip.get(i).getText().contains("Q18") || trip.get(i).getText().contains("F18")) {

					addLog("TripId booked: " + getText(driver, getObject("AirCom_Confirmation_TripID")), true);

					addLog("Confirmation URL: " + driver.getCurrentUrl(), true);

					checkStatus = true;

				}

				// System.out.println("getting texts="+trip.get(i).getText());

			}

		}

		if (checkStatus) {

			return true;

		} else {

			addLog("Timed out (90 seconds) while waiting for confirmation page.", true);

			addLog("Current URL: " + driver.getCurrentUrl(), true);



			return false;

		}

	}



	public boolean checkBookingStatus(RemoteWebDriver driver) throws Exception {

		boolean checkStatus = false;



		/*

		 * try { Thread.sleep(20000); driver.manage().timeouts().pageLoadTimeout(10000,

		 * TimeUnit.MILLISECONDS); driver.navigate().refresh(); } catch

		 * (org.openqa.selenium.TimeoutException te) { ((JavascriptExecutor)

		 * driver).executeScript("window.stop();"); }

		 * driver.manage().timeouts().pageLoadTimeout(-1, TimeUnit.MILLISECONDS);

		 * Thread.sleep(5000);

		 */



		Thread.sleep(1000);



		WebDriverWait wait = new WebDriverWait(driver, 20);

		addLog("URL : " + driver.getCurrentUrl());

		for (int i = 0; i < 15; i++) {

			if (!driver.getCurrentUrl().contains("confirmation")) {

				Thread.sleep(5000);

			} else {

				break;

			}

		}

		wait.until(ExpectedConditions.urlContains("confirmation"));

		if (waitForElementVisibility(driver, getObject("AirCom_Confirmation_BookingDone_TextMsg"), 10000)) {

			String confirmationMessage = getText(driver, getObject("AirCom_Confirmation_BookingDone_TextMsg"));



			System.out.println("TEST MESSAGE" + confirmationMessage);



			if (confirmationMessage.contains("Your booking is done")

					|| confirmationMessage.contains("Booking on hold with Pricelock")

					|| confirmationMessage.contains("Well, almost done")

					|| confirmationMessage.contains("Your trip has changed")) {



				checkStatus = waitForElementVisibility(driver, getObject("AirCom_Confirmation_TripID"), 1);



			} else {

				addLog("Confirmation message is: " + confirmationMessage, true);

				addLog("Confirmation URL: " + driver.getCurrentUrl(), true);

			}



		}



		if (checkStatus) {

			printInfo(getText(driver, getObject("AirCom_Confirmation_TripID")));

			addLog("TripId booked: " + getText(driver, getObject("AirCom_Confirmation_TripID")), true);

			logger.info("TripId booked: " + getText(driver, getObject("AirCom_Confirmation_TripID")));

			addLog("Confirmation URL: " + driver.getCurrentUrl(), true);

			return true;

		} /*

			 * else if (waitElement(driver,

			 * getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {

			 * printInfo("System Acting up error.");

			 * addLog("System Acting up error."); return false; } else if

			 * (waitElement(driver, getObject("Oops_your_booking_didn't_go_through"), 1))

			 * {// For intl if (

			 * driver.findElement(getObject("Oops_your_booking_didn't_go_through")).

			 * getText() .equals("Oops, your booking didnt go through")) {

			 * printInfo("Oops, your booking didnt go through error.");

			 * addLog("Oops, your booking didnt go through error."); return false; }

			 * return false; } else if (waitElement(driver,

			 * getObject("Sorry_we_couldn't_confirm_your_booking"), 1)) { if (driver.

			 * findElement(getObject("Sorry_we_couldn't_confirm_your_booking" )).getText()

			 * .equals("Sorry, we couldn't confirm your booking")) {

			 * printInfo("Sorry, we couldn't confirm your booking error.");

			 * addLog("Sorry, we couldn't confirm your booking error."); return false;

			 * } return false; } else if (elementVisible(driver,

			 * getObject("AirCom_SRP_Modify_Search_Button"), 1)) {

			 * addLog("SRP loaded after some failure. Retrying with new sector." );

			 * printInfo("SRP loaded after some failure. Retrying with new sector." );

			 * return true; }

			 */else {

			addLog("Booking failed for some reason during payment, Making another attempt!", true);

			addLog("Confirmation URL: " + driver.getCurrentUrl(), true);

			return false;

		}

	}



	public String getTripId(RemoteWebDriver driver, By by) throws Exception {

		String tripId = null;

		tripId = getText(driver, by);

		addLog("Trip ID confirmed for this trip is : " + tripId, true);

		printInfo("Trip ID confirmed for this trip is : " + tripId);

		// checkFreeCancellation(driver);

		return tripId;

	}



	public boolean getAccountsPage(RemoteWebDriver driver) throws Exception {



		try {



			safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

			safeClick(driver, getObject("Acc_Dashboard_Link_Dropdown_Menu"));

			if (waitElement(driver, getObject("Acc_Trips_Tab"), 10)) {

				return true;

			}

			return false;

		} catch (Exception e) {

			addLog("User Account tab did not appear. Force entering account page");

			driver.get(common.value("url") + "/account/");

			if (waitElement(driver, getObject("Acc_Trips_Tab"), 10)) {

				return true;

			}

			return false;

		}

	}



	public void sendSMS(RemoteWebDriver driver) throws Exception {

		Thread.sleep(2000);

		boolean sms_message = false;

		if (driver.findElement(By.linkText("SMS itinerary")).isDisplayed()) {

			driver.findElementByLinkText("SMS itinerary").click();



			safeType(driver, getObject("AirCom_Confirmation_SMS_Mobile_No"), dataFile.value("Mobile_Number"));

			safeClick(driver, getObject("AirCom_Confirmation_SMS_Send_Button"));

			Thread.sleep(2000);

			for (int i = 0; i < 3; i++) {

				sms_message = waitElement(driver, getObject("AirCom_Confirmation_SMS_Confirm"), 1);

				if (sms_message)

					break;

			}

			if (sms_message) {

				sms_message = textPresentInElement(driver, getObject("AirCom_Confirmation_SMS_Confirm"),

						"Itinerary sent", 1);

				if (sms_message) {

					addLog("SMS sent successfully from confirmation page.");

					printInfo("SMS sent successfully from confirmation page.");

				}

			} else {

				addLog("Warning! Error in sending sms from confirmation page.");

				printInfo("Warning! Error in sending sms from confirmation page.");

			}

		}

	}



	public void loadTrip(RemoteWebDriver driver, String tripId, String domain) throws Exception {

		int count = 0;

		Thread.sleep(10000);// TODO:check if value correct

		while (count < 9) {

			addLog("Loading Account trip details page trial - " + (count + 1), true);

			driver.get(getBaseUrl(domain) + "/account/trips/");

			driver.get(getBaseUrl(domain) + "/account/trips/" + tripId);

			if (waitForElementVisibility(driver, getObject("Acc_Trip_Details_Cancel_Link"), 20)) {

				break;

			} else if (waitForElementVisibility(driver, getObject("Acc_Trip_Not_Loaded_Status"), 5)) {

				String tripMessage = getText(driver, getObject("Acc_Trip_Not_Loaded_Status"));

				if (tripMessage != null) {

					if (tripMessage.trim().equalsIgnoreCase("Sorry, unable to get your details right now.")) {

						addLog("Sorry, unable to get your details right now.");

						count++;

						continue;

					} else if (tripMessage.trim().equalsIgnoreCase("Hang on, fetching your trips details...")) {

						addLog("Hang on, fetching your trips details...", true);

						count++;

						continue;

					}

				}

				count++;

			}

			count++;

		}

	}



	public boolean checkStatus(RemoteWebDriver driver, By by) throws Exception {

		String status = null;

		boolean confirmed = false;

		boolean confirmFlag = false;

		int count = 0;

		List<WebElement> listElements = new ArrayList<WebElement>();

		while (!confirmFlag && count < 3) {

			confirmFlag = waitElement(driver, by, 2);

			count++;

		}

		listElements = driver.findElements(by);

		for (WebElement webElement : listElements) {

			status = webElement.getText().trim();

			if (status.equalsIgnoreCase("CONFIRMED")) {

				confirmed = true;

			} else {

				confirmed = false;

				addLog("Some sector/pax of this booking is not confirmed. Hence exiting with error!");

				printInfo("Some sector/pax of this booking is not confirmed. Hence exiting with error!");

				AssertJUnit.assertTrue(false);

			}

		}

		return confirmed;

	}



	public void cancelTrip(RemoteWebDriver driver, By by, String refundTo) throws Exception {

		safeClick(driver, getObject("Acc_Trip_Details_Cancel_Link"));

		Thread.sleep(2000);



		// Enable all the check boxes to to a full cancellation if the By has it

		List<WebElement> we = null;

		if (waitElement(driver, by, 5)) {

			we = driver.findElements(by);

			if (!we.isEmpty()) {

				for (WebElement webElement : we) {

					// TODO:check if element contains 'selectall' as id then

					// select// check if possible to select directly

					if (webElement.getAttribute("id").contains("selectall"))

						webElement.click();

				}

			} else {

				addLog("Not able to select the pax for cancelling. Please check if any change in xpaths");

				printInfo("Not able to select the pax for cancelling. Please check if any change in xpaths");

				AssertJUnit.assertTrue(false);

			}

		} else if (waitElement(driver, By.id("tripCancelError"), 2)) {

			addLog("Not able to cancel booking from accounts. Trip cannot be cancelled online.");

			printInfo("Not able to cancel booking from accounts. Trip cannot be cancelled online.");

			AssertJUnit.assertTrue(false);

		}



		clickCancellationButton(driver, refundTo);

	}



	public void cancelSpecialRoundTrip(RemoteWebDriver driver, By by, String refundTo) throws Exception {

		safeClick(driver, getObject("Acc_Trip_Details_Cancel_Link"));



		// Enable all the check boxes to to a full cancellation if the By has it

		List<WebElement> we = null;

		if (waitElement(driver, by, 5)) {

			we = driver.findElements(by);

			if (!we.isEmpty()) {

				for (WebElement webElement : we) {

					// TODO:check if element contains 'selectall' as id then

					// select// check if possible to select directly

					if (webElement.getAttribute("id").contains("selectall"))

						webElement.click();

					break;

				}

			} else {

				addLog("Not able to select the pax for cancelling. Please check if any change in xpaths");

				printInfo("Not able to select the pax for cancelling. Please check if any change in xpaths");

				AssertJUnit.assertTrue(false);

			}

		} else if (waitElement(driver, By.id("tripCancelError"), 2)) {

			addLog("Not able to cancel booking from accounts. Trip cannot be cancelled online.");

			printInfo("Not able to cancel booking from accounts. Trip cannot be cancelled online.");

			AssertJUnit.assertTrue(false);

		}



		clickCancellationButton(driver, refundTo);

	}



	public void cancelTripOnewayOnePax(RemoteWebDriver driver, By by, String refundTo) throws Exception {

		safeClick(driver, getObject("Acc_Trip_Details_Cancel_Link"));



		if (waitElement(driver, getObject("Acc_Air_Review_Cancel_Button"), 5)) {

			// Enable all the check boxes to to a full cancellation if the By

			// has it

			List<WebElement> we = null;

			if (waitElement(driver, by, 5)) {

				we = driver.findElements(by);

				if (!we.isEmpty()) {

					for (WebElement webElement : we) {

						// TODO:check if element contains 'selectall' as id then

						// select// check if possible to select directly

						if (webElement.getAttribute("id").contains("selectall"))

							webElement.click();

					}

				} else {

					addLog("Not able to select the pax for cancelling. Please check if any change in xpaths");

					printInfo("Not able to select the pax for cancelling. Please check if any change in xpaths");

					AssertJUnit.assertTrue(false);

				}

			} else if (waitElement(driver, By.id("tripCancelError"), 2)) {

				addLog("Not able to cancel booking from accounts. Trip cannot be cancelled online.");

				printInfo("Not able to cancel booking from accounts. Trip cannot be cancelled online.");

				AssertJUnit.assertTrue(false);

			}



			clickCancellationButton(driver, refundTo);

			return;

		}



		if (waitElement(driver, By.id("tripCancelError"), 2)) {

			addLog("Not able to cancel booking from accounts. Trip cannot be cancelled online.");

			printInfo("Not able to cancel booking from accounts. Trip cannot be cancelled online.");

			AssertJUnit.assertTrue(false);

		}



		if (refundTo.equals("card")) {

			safeClick(driver, getObject("Acc_Air_Refund_To_Card"));

		} else {

			safeClick(driver, getObject("Acc_Air_Refund_To_Wallet"));

		}

		safeClick(driver, getObject("Acc_Air_Cancel_Button"));

	}



	private void clickCancellationButton(RemoteWebDriver driver, String refundTo) {

		try {

			JavascriptExecutor js1 = driver;

			List<WebElement> element1 = driver.findElements(By.xpath("//*[text()='See Refund Amount']"));

			// js1.executeScript("arguments[0].scrollIntoView(true);",

			// element1.get(0));

			safeClick(driver, By.xpath("//*[text()='See Refund Amount']"));

			// safeClick(driver, getObject("Acc_Air_Review_Cancel_Button"));

			Thread.sleep(3000);

			if (waitElement(driver, getObject("Acc_Air_Refund_To_Card"), 5)) {

				if (refundTo.equals("card")) {

					safeClick(driver, getObject("Acc_Air_Refund_To_Card"));

				} else {

					safeClick(driver, getObject("Acc_Air_Refund_To_Wallet"));

				}

			}

			safeClick(driver, getObject("Acc_Air_Cancel_Button"));

		} catch (Exception e) {

		}

		// tripCancelled = true;

	}



	public boolean confirmCancellation(RemoteWebDriver driver) throws Exception {

		By by = getObject("Acc_Air_Cancel_Confirmation_Text");

		boolean flagCancelSuccess = false;

		waitElement(driver, by, 30);

		try {

			String ticketCancel = getText(driver, by);

			if (ticketCancel.equalsIgnoreCase("Your tickets were cancelled successfully")) {

				flagCancelSuccess = true;

				printInfo(ticketCancel);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return flagCancelSuccess;

	}



	// Sometimes the link does not work so it calls for the URL directly

	public void signOut(RemoteWebDriver driver, String domain) throws Exception {

		try {

			driver.get(baseUrl);

			Thread.sleep(5000);

			safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

			safeClick(driver, getObject("Acc_Air_User_Global_Logout"));

			int count = 0;

			while (checkIfSignedIn(driver) && count < 2) {

				driver.get(baseUrl + "/signout");

				count++;

			}

		} catch (Exception e) {

			addLog("Sign out link not available! ERROR! Performing direct signout.");

			printInfo("Sign out link not available! ERROR! Performing direct signout.");

			driver.get(getBaseUrl(domain) + "/signout");

			e.printStackTrace();

		}

		if (checkIfSignedIn(driver)) {

			addLog("Manual signout using url also not happening! ERROR!");

			printInfo("Manual signout using url also not happening! ERROR!");

			assertTrue("Failure!", false);

		}

	}



	public void getRefundableFlightsOnly(RemoteWebDriver driver) throws Exception {



		if (waitElement(driver, getObject("AirCom_SRP_Refundable_Flights_Checkbox"), 2))

			if (!driver.findElement(getObject("AirCom_SRP_Refundable_Flights_Checkbox")).isSelected())

				safeClick(driver, getObject("AirCom_SRP_Refundable_Flights_Checkbox"));

	}



	public WebElement selectOneWayFlightDom(RemoteWebDriver driver, String flight_mode) throws Exception {

		// TODO understand this method

		int count = 0;

		List<WebElement> solutions = driver.findElementsByXPath(getValue("AirCom_SRP_OW_Flights_List_Prefix"));

		if (!(solutions.size() > 0))

			if (checkWarning(driver))

				return null;

		for (WebElement soln : solutions) {

			count++;

			if (!(soln.isDisplayed()))

				continue;

			boolean similar_flights = soln.getAttribute("class").equalsIgnoreCase("auxiliary"); // check if solution or

																								// link

			// to

			// similar solutions

			if (similar_flights) {

				int i = 0;

				By similar_flights_link = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]/a");

				safeClick(driver, similar_flights_link);

				By similar_flights_by = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]/"

						+ getValue("AirCom_SRP_OW_Similar_Flights_Suffix"));

				List<WebElement> similar_flights_elem = driver.findElements(similar_flights_by);

				ListIterator<WebElement> li = similar_flights_elem.listIterator();

				while (li.hasNext()) {

					WebElement c = li.next();

					if (c.getText().length() > 0)

						li.remove();

				}

				for (WebElement nested_soln : similar_flights_elem) {

					i++;

					By details_by = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]"

							+ getValue("AirCom_SRP_OW_Similar_Flights_Suffix") + "[" + i + "]"

							+ getValue("AirCom_SRP_OW_Flights_List_Details"));

					safeClick(driver, details_by);

					By connecting_flights_by = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count

							+ "]" + getValue("AirCom_SRP_OW_Similar_Flights_Suffix") + "[" + i + "]"

							+ getValue("AirCom_SRP_OW_Flights_No_Prefix"));

					By connecting_flights_by_2 = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count

							+ "]" + getValue("AirCom_SRP_OW_Similar_Flights_Suffix") + "[" + i + "]"

							+ getValue("AirCom_SRP_OW_Flights_No_Prefix2"));

					List<WebElement> connecting_flights = new ArrayList<WebElement>();

					if (waitElement(driver, connecting_flights_by, 2)) {

						connecting_flights = driver.findElements(connecting_flights_by);

					} else if (waitElement(driver, connecting_flights_by_2, 2)) {

						connecting_flights = driver.findElements(connecting_flights_by_2);

					}

					ArrayList<String> flight_number = new ArrayList<String>();

					for (WebElement each_flight : connecting_flights)

						flight_number.add(each_flight.findElement(By.xpath(getValue("AirCom_SRP_OW_Flights_No_Suffix")))

								.getText());

					String test_flight = flight_number.get(0);

					boolean solution_via = true;

					for (String each : flight_number)

						if (!each.equalsIgnoreCase(test_flight)) {

							solution_via = false;

							break;

						}

					if (((flight_mode.equalsIgnoreCase("via") && solution_via)

							|| (flight_mode.equalsIgnoreCase("stopover") && (!solution_via)))) {

						return nested_soln;

					}

				}

				continue;

			}

			// By details_by =

			// By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix")+"["+count+"]"+getValue("AirCom_SRP_OW_Flights_List_Details"));

			// By details_by =

			// By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" +

			// count + "]");

			safeClick(driver, By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]/table/tbody"));

			Thread.sleep(100);

			By connecting_flights_by = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]"

					+ getValue("AirCom_SRP_OW_Flights_No_Prefix"));

			By connecting_flights_by_2 = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]"

					+ getValue("AirCom_SRP_OW_Flights_No_Prefix2"));

			By connecting_flights_by_3 = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]"

					+ getValue("AirCom_SRP_OW_Flights_No_Prefix3"));

			List<WebElement> connecting_flights = new ArrayList<WebElement>();

			if (waitElement(driver, connecting_flights_by, 1)) {

				connecting_flights.add(driver.findElement(connecting_flights_by));

			}

			if (waitElement(driver, connecting_flights_by_2, 1)) {

				connecting_flights.add(driver.findElement(connecting_flights_by_2));

			}

			if (waitElement(driver, connecting_flights_by_3, 1)) {

				connecting_flights.add(driver.findElement(connecting_flights_by_3));

			}

			ArrayList<String> flight_number = new ArrayList<String>();

			for (WebElement each_flight : connecting_flights)

				flight_number.add(each_flight.getText());

			String test_flight = flight_number.get(0);

			boolean solution_via = false;

			for (String each : flight_number)

				if (!each.equalsIgnoreCase(test_flight)) {

					solution_via = true;

					break;

				}

			if (((flight_mode.equalsIgnoreCase("via") && solution_via)

					|| (flight_mode.equalsIgnoreCase("stopover") && (!solution_via)))) {

				return soln;

			}

		}

		return null;

	}



	/*

	 * Added by: prashanth.k@cleartrip.com Reason: The Xpath search on the returned

	 * web element from the above method selectOneWayFlightDom was failing. Hence

	 * the method selectOneWayFlightDom has now been modified to click and return

	 * clicked status as a boolean. and named as selectOneWayFlightDom1 available in

	 * India.

	 */



	public boolean selectOneWayFlightDom1(RemoteWebDriver driver, String flight_mode) throws Exception {

		// TODO understand this method

		int count = 0;

		List<WebElement> solutions = driver.findElementsByXPath(getValue("AirCom_SRP_OW_Flights_List_Prefix"));

		if (!(solutions.size() > 0))

			if (checkWarning(driver))

				return false;

		for (WebElement soln : solutions) {

			count++;

			if (!(soln.isDisplayed()))

				continue;

			boolean similar_flights = soln.getAttribute("class").equalsIgnoreCase("auxiliary"); // check if solution or

																								// link

			// to

			// similar solutions

			if (similar_flights) {

				int i = 0;

				By similar_flights_link = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]/a");

				safeClick(driver, similar_flights_link);

				By similar_flights_by = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]/"

						+ getValue("AirCom_SRP_OW_Similar_Flights_Suffix"));

				List<WebElement> similar_flights_elem = driver.findElements(similar_flights_by);

				ListIterator<WebElement> li = similar_flights_elem.listIterator();

				while (li.hasNext()) {

					WebElement c = li.next();

					if (c.getText().length() > 0)

						li.remove();

				}

				for (WebElement nested_soln : similar_flights_elem) {

					i++;

					By details_by = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]"

							+ getValue("AirCom_SRP_OW_Similar_Flights_Suffix") + "[" + i + "]"

							+ getValue("AirCom_SRP_OW_Flights_List_Details"));

					safeClick(driver, details_by);

					By connecting_flights_by = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count

							+ "]" + getValue("AirCom_SRP_OW_Similar_Flights_Suffix") + "[" + i + "]"

							+ getValue("AirCom_SRP_OW_Flights_No_Prefix"));

					By connecting_flights_by_2 = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count

							+ "]" + getValue("AirCom_SRP_OW_Similar_Flights_Suffix") + "[" + i + "]"

							+ getValue("AirCom_SRP_OW_Flights_No_Prefix2"));

					List<WebElement> connecting_flights = new ArrayList<WebElement>();

					if (waitElement(driver, connecting_flights_by, 2)) {

						connecting_flights = driver.findElements(connecting_flights_by);

					} else if (waitElement(driver, connecting_flights_by_2, 2)) {

						connecting_flights = driver.findElements(connecting_flights_by_2);

					}

					ArrayList<String> flight_number = new ArrayList<String>();

					for (WebElement each_flight : connecting_flights)

						flight_number.add(each_flight.findElement(By.xpath(getValue("AirCom_SRP_OW_Flights_No_Suffix")))

								.getText());

					String test_flight = flight_number.get(0);

					boolean solution_via = true;

					for (String each : flight_number)

						if (!each.equalsIgnoreCase(test_flight)) {

							solution_via = false;

							break;

						}

					if (((flight_mode.equalsIgnoreCase("via") && solution_via)

							|| (flight_mode.equalsIgnoreCase("stopover") && (!solution_via)))) {

						try {

							nested_soln.findElement(By.xpath("//button[@class='booking']")).click();

							return true;

						} catch (Exception e) {

							e.printStackTrace();

							return false;

						}

					}

				}

				continue;

			}

			// By details_by =

			// By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix")+"["+count+"]"+getValue("AirCom_SRP_OW_Flights_List_Details"));

			// By details_by =

			// By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" +

			// count + "]");

			// safeClick(driver,

			// By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" +

			// count + "]/table/tbody"));

			// Thread.sleep(100);

			try {

				driver.findElement(

						By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]/table/tbody"))

						.click();

			} catch (Exception e) {

				addLog(

						"Search Results row is empty / null. Hence below exception. This is probably due to Rewards advert. Please check",

						true);

				e.printStackTrace();

				continue;

			}

			By connecting_flights_by = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]"

					+ getValue("AirCom_SRP_OW_Flights_No_Prefix"));

			By connecting_flights_by_2 = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]"

					+ getValue("AirCom_SRP_OW_Flights_No_Prefix2"));

			By connecting_flights_by_3 = By.xpath(getValue("AirCom_SRP_OW_Flights_List_Prefix") + "[" + count + "]"

					+ getValue("AirCom_SRP_OW_Flights_No_Prefix3"));

			List<WebElement> connecting_flights = new ArrayList<WebElement>();

			if (waitElement(driver, connecting_flights_by, 1)) {

				connecting_flights.add(driver.findElement(connecting_flights_by));

			}

			if (waitElement(driver, connecting_flights_by_2, 1)) {

				connecting_flights.add(driver.findElement(connecting_flights_by_2));

			}

			if (waitElement(driver, connecting_flights_by_3, 1)) {

				connecting_flights.add(driver.findElement(connecting_flights_by_3));

			}

			ArrayList<String> flight_number = new ArrayList<String>();

			for (WebElement each_flight : connecting_flights)

				flight_number.add(each_flight.getText());

			String test_flight = flight_number.get(0);

			boolean solution_via = false;

			for (String each : flight_number)

				if (!each.equalsIgnoreCase(test_flight)) {

					solution_via = true;

					break;

				}

			if (((flight_mode.equalsIgnoreCase("via") && solution_via)

					|| (flight_mode.equalsIgnoreCase("stopover") && (!solution_via)))) {

				try {

					addLog("" + count, true);

					driver.findElement(By.xpath("//form[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[" + count

							+ "]/table/tbody/tr[2]/td[3]/button")).click();

					return true;

				} catch (Exception e) {

					e.printStackTrace();

					return false;

				}

			}

		}

		return false;

	}



	/*

	 * public boolean selectOneWayFlight(RemoteWebDriver driver, String

	 * flightChannel) throws Exception {// TODO: look into this methods working

	 * String xpath = null; boolean flightFoundFlag = false; int i = 0;

	 * List<WebElement> vendorList = null; By by = null; String xpathPrefixVendor =

	 * null; String xpathSuffixVendor = null; String xpathPrefixRB = null; String

	 * xpathSuffixRB = null; int countFlightsSegmentWise = 0;

	 *

	 * // TODO move the xpath to the OR file xpathPrefixVendor =

	 * "//section[2]/div[4]/div[1]/nav[2]/ul/li["; xpathSuffixVendor =

	 * "]/div/label/table/tbody/tr[2]/td[1]/span"; xpathPrefixRB =

	 * "//section[2]/div[4]/div[1]/nav[2]/ul/li["; xpathSuffixRB =

	 * "]/div/label/table/tbody/tr[1]/th[1]/input"; countFlightsSegmentWise =

	 * driver.findElements(By.xpath(

	 * ".//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li")).size(); outerloop:

	 * for (i = 1; i <= countFlightsSegmentWise; i++) {// For for each radio button

	 * options to select in a segment vendorList = new ArrayList<WebElement>();

	 * xpath = xpathPrefixVendor + i + xpathSuffixVendor; by = By.xpath(xpath);

	 * vendorList.addAll(driver.findElements(by));

	 *

	 * for (int x = 0; x < vendorList.size() - 1; x++) {// For all the flights in a

	 * set of selected radio button for (int y = x + 1; y < vendorList.size(); y++)

	 * { if (flightChannel.equalsIgnoreCase("Stop Over")) { if

	 * (vendorList.get(x).getText ().equalsIgnoreCase(vendorList.get(y).getText()))

	 * { flightFoundFlag = true; xpath = xpathPrefixRB + i + xpathSuffixRB; by =

	 * By.xpath(xpath); safeClick(driver, by); continue outerloop; } else {

	 * flightFoundFlag = false; } } else if (flightChannel.equalsIgnoreCase("Via"))

	 * { if (!vendorList

	 * .get(x).getText().equalsIgnoreCase(vendorList.get(y).getText())) {

	 * flightFoundFlag = true; xpath = xpathPrefixRB + i + xpathSuffixRB; by =

	 * By.xpath(xpath); safeClick(driver, by); continue outerloop; } else {

	 * flightFoundFlag = false; } } else if

	 * (flightChannel.equalsIgnoreCase("Multi")) { String str1 =

	 * vendorList.get(x).getText().trim(); String vendor1 = str1.substring(0,

	 * str1.lastIndexOf(" ")).trim(); str1 = vendorList.get(y).getText().trim();

	 * String vendor2 = str1.substring(0, str1.lastIndexOf(" ")).trim(); if

	 * (!vendor1.equalsIgnoreCase(vendor2)) { flightFoundFlag = true; xpath =

	 * xpathPrefixRB + i + xpathSuffixRB; by = By.xpath(xpath); safeClick(driver,

	 * by); break outerloop; } else { flightFoundFlag = false; } } } } } By warning

	 * = By.className("warningMessage"); if (waitElement(driver, warning, 2)) { //

	 * being too picky return false; } return flightFoundFlag; }

	 */



	public boolean selectRoundTripFlightDom(RemoteWebDriver driver, String flightChannel) throws Exception {// TODO:

																											// look into

		// this

		// methods working

		String xpath = null;

		boolean flightFoundFlag = false;

		int i = 0;

		int d = 0;

		List<WebElement> vendorList = null;

		By by = null;

		String xpathPrefixVendor = null;

		String xpathSuffixVendor = null;

		String xpathPrefixRB = null;

		String xpathSuffixRB = null;

		int countFlightsSegmentWise = 0;



		outerloop: for (d = 1; d <= 2; d++) {// For onward and return flight

			// segments

			By warning = By.className("warningMessage");

			if (waitElement(driver, warning, 2)) {

				// being too picky

				return false;

			}

			// TODO move the xpath to the OR file

			xpathPrefixVendor = "//section[2]/div[4]/div[" + d + "]/nav[1]/ul/li[";

			xpathSuffixVendor = "]/div/label/table/tbody/tr[2]/td[1]/span";

			xpathPrefixRB = "//section[2]/div[4]/div[" + d + "]/nav[1]/ul/li[";

			xpathSuffixRB = "]/div/label/table/tbody/tr[1]/th[1]/input";

			countFlightsSegmentWise = driver.findElements(By.xpath("//div[@data-leg='" + d + "']/nav[1]/ul/li")).size();

			for (i = 1; i <= countFlightsSegmentWise; i++) {// For for each

				// radio button

				// options to select

				// in a segment

				vendorList = new ArrayList<WebElement>();

				xpath = xpathPrefixVendor + i + xpathSuffixVendor;

				by = By.xpath(xpath);

				vendorList.addAll(driver.findElements(by));



				for (int x = 0; x < vendorList.size() - 1; x++) {// For all the

					// flights

					// in a set

					// of

					// selected

					// radio

					// button

					for (int y = x + 1; y < vendorList.size(); y++) {

						if (flightChannel.equalsIgnoreCase("stopover")) {

							if (vendorList.get(x).getText().equalsIgnoreCase(vendorList.get(y).getText())) {

								flightFoundFlag = true;

								xpath = xpathPrefixRB + i + xpathSuffixRB;

								by = By.xpath(xpath);

								safeClick(driver, by);

								continue outerloop;

							} else {

								flightFoundFlag = false;

							}

						} else if (flightChannel.equalsIgnoreCase("Via")) {

							if (!vendorList.get(x).getText().equalsIgnoreCase(vendorList.get(y).getText())) {

								flightFoundFlag = true;

								xpath = xpathPrefixRB + i + xpathSuffixRB;

								by = By.xpath(xpath);

								safeClick(driver, by);

								continue outerloop;

							} else {

								flightFoundFlag = false;

							}

						} else if (flightChannel.equalsIgnoreCase("MultiCarrier")) {

							String str1 = vendorList.get(x).getText().trim();

							String vendor1 = str1.substring(0, str1.lastIndexOf(" ")).trim();

							str1 = vendorList.get(y).getText().trim();

							String vendor2 = str1.substring(0, str1.lastIndexOf(" ")).trim();

							if (!vendor1.equalsIgnoreCase(vendor2)) {

								flightFoundFlag = true;

								xpath = xpathPrefixRB + i + xpathSuffixRB;

								by = By.xpath(xpath);

								safeClick(driver, by);

								break outerloop;

							} else {

								flightFoundFlag = false;

							}

						}

					}

				}

			}

		}

		return flightFoundFlag;

	}



	public boolean selectSplRoundTripFlightDomGDS1(RemoteWebDriver driver) throws Exception {

		List<WebElement> splRndTabs = driver.findElements(By.xpath("//td[@class='comboItem']"));

		System.out.println("tabs size=" + splRndTabs.size());

		if (splRndTabs.size() == 0) {

			safeClick(driver, By.xpath("//*[@class='comboTabs']/td[2]"));

			// return true;



		} else if (splRndTabs != null) {

			splRndTabs.remove(0);// removing all airlines

			int size = splRndTabs.size();

			System.out.println("size==" + size);

			for (int i = 0; i <= size + 1; i++) {

				System.out.println("flight name=" + driver

						.findElement(By.xpath("//td[@class='comboItem'][" + i + "]/a/span/img")).getAttribute("title"));

			}

			for (int i = 2; i <= size + 1; i++) {

				String title = driver.findElement(By.xpath("//td[@class='comboItem'][" + i + "]/a/span/img"))

						.getAttribute("title");

				if (title.equalsIgnoreCase("Air India")) {

					if (isElementPresent(driver, By.xpath("//td[@class='comboItem'][" + i + "]/a/small"))) {

						String discount = driver.findElementByXPath("//td[@class='comboItem'][" + i + "]/a/small")

								.getText();

						printInfo(discount);

						// if()//Save Rs.1

						// String[] x = discount.contains("Save");

						// int d = Integer.parseInt(x[1]);

						// int discountAmnt =

						// Integer.parseInt(discount.split(".")[1]);

						if (discount.contains("Save")) {

							safeClick(driver, By.xpath("td[@class='comboItem'][" + i + "]/a/span/img"));

							if (checkIfSplRndTrip(driver)) {

								addLog("Special round trip flight selected.");

								printInfo("Special round trip flight selected.");

								return true;

							} else {

								addLog("Clicked combo tab, still special round trip flights not selected.");

								printInfo("Clicked combo tab, still special round trip flights not selected.");

								assertTrue("Failure!", false);

							}

						} else

							return false;

					} else

						return false;

				}

			}

		}

		return true;

	}



	public boolean selectSplRoundTripFlightDomGDS(RemoteWebDriver driver) throws Exception {

		List<WebElement> splRndTabs = driver

				.findElements(By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td"));

		System.out.println("tabs size=" + splRndTabs.size());

		if (splRndTabs != null && !splRndTabs.isEmpty()) {

			splRndTabs.remove(0);// removing all airlines tab

			int size = splRndTabs.size();

			for (int i = 1; i <= size + 1; i++) {

				int x = i + 1;

				String title = driver

						.findElement(

								By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td[" + x + "]/a/span/img"))

						.getAttribute("title");

				if (title.equalsIgnoreCase("JetKonnect")) {

					if (isElementPresent(driver,

							By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a/small"))) {

						String discount = driver

								.findElementByXPath(

										".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a/small")

								.getText();

						printInfo(discount);

						// if()//Save Rs.1

						// String[] x = discount.contains("Save");

						// int d = Integer.parseInt(x[1]);

						// int discountAmnt =

						// Integer.parseInt(discount.split(".")[1]);

						if (discount.contains("Save")) {

							safeClick(driver,

									By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a"));

							if (checkIfSplRndTrip(driver)) {

								addLog("Special round trip flight selected.");

								printInfo("Special round trip flight selected.");

								return true;

							} else {

								addLog("Clicked combo tab, still special round trip flights not selected.");

								printInfo("Clicked combo tab, still special round trip flights not selected.");

								assertTrue("Failure!", false);

							}

						} else

							return false;

					} else

						return false;

				}

			}

		}

		return false;

	}



	public boolean selectSplRoundTripFlightDomGDS2(RemoteWebDriver driver) throws Exception {

		boolean srpt = false;

		List<WebElement> splRndTabs = driver

				.findElements(By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td"));

		for (int i = 1; i < splRndTabs.size(); i++) {

			int x = i + 1;

			splRndTabs.get(i).click();

			addLog("tab " + i + " selected", true);

			Thread.sleep(7000);

			System.out.println(

					driver.findElement(By.xpath("//*[@id='flightForm']/section[2]/div[2]/table/td[" + x + "]/a/small"))

							.getText());

			System.out.println("//*[@id='flightForm']/section[2]/div[2]/table/td[" + x + "]/a/small");

			if (driver.findElement(By.xpath("//*[@id='flightForm']/section[2]/div[2]/table/td[" + x + "]/a/small"))

					.getText().contains("Save")) {

				// clickRoundTripBookButton(driver);

				srpt = true;

				break;

			} else {

				srpt = false;

			}

			// System.out.println(driver.findElement(By.xpath("//*[@id='flightForm']/section[2]/div[2]/table/td["+x+"]/a/small")).getAttribute("data-pr"));

			// System.out.println(driver.findElement(By.xpath("//div[@class='row

			// roundTripHead']/div[1]/h2")).getText());



		}

		return srpt;

	}



	public boolean meSelectSplRoundTripFlightIntl(RemoteWebDriver driver, String flight_type, String req_airline)

			throws Exception {

		boolean splrt = false;

		List<WebElement> splRndTabs = driver.findElements(getObject("AirCom_SRP_Intl_SplRT_comboTab"));

		for (int i = 1; i < splRndTabs.size(); i++) {

			int x = i + 1;

			splRndTabs.get(i).click();

			addLog("checking if comboTab " + i + " is a Special RT", true);

			Thread.sleep(7000);

			if (elementPresent_Time(driver,

					By.xpath("//*[@id='flightForm']/section[2]/div[2]/table/td[" + x + "]/a/small"), 3)) {

				if (driver.findElement(By.xpath("//*[@id='flightForm']/section[2]/div[2]/table/td[" + x + "]/a/small"))

						.getText().contains("Save")) {

					String airline_name = driver

							.findElement(

									By.xpath("//*[@id='flightForm']/section[2]/div[2]/table/td[" + x + "]/a/span/img"))

							.getAttribute("title");

					if (isLCCFlight(driver, airline_name) && flight_type.equalsIgnoreCase("lcc")) {

						if (req_airline.equals("")) {

							clickRoundTripBookButton(driver);

							addLog("Selected Flight: " + airline_name, true);

							splrt = true;

							break;

						} else if (req_airline.equalsIgnoreCase(airline_name)) {

							clickRoundTripBookButton(driver);

							addLog("Selected Flight: " + airline_name, true);

							splrt = true;

							break;

						} else {

							continue;

						}

					} else if (flight_type.equalsIgnoreCase("gds")) {

						clickRoundTripBookButton(driver);

						addLog("Selected Flight: " + airline_name, true);

						splrt = true;

						break;

					}

				} else {

					splrt = false;

					continue;

				}

			}

		}

		return splrt;

	}



	public boolean checkIfSplRndTrip(RemoteWebDriver driver) throws Exception {

		if (waitElement(driver, getObject("AirCom_SRP_SplRt_Discounted"), 2)) {

			if (elementVisible(driver, By.xpath("//small[@class='strikeOut']/span"), 1))

				return true;

		}

		return false;

	}



	public boolean checkSpecialRTComboTabForInternational(RemoteWebDriver driver) throws Exception {

		if (waitElement(driver, getObject("AirCom_SRP_Intl_SplRT_comboTab"), 30)) {

			return true;

		} else {

			addLog("Doing a connector search", true);

			connectorSearch(driver, driver.getCurrentUrl());

			/*

			 * addLog("Refreshing page...", true); driver.navigate().refresh();

			 */

			if (waitElement(driver, getObject("AirCom_SRP_Intl_SplRT_comboTab"), 45)) {

				return true;

			} else

				return false;

		}

	}



	public boolean selectSplRoundTripFlightDomLCC(RemoteWebDriver driver) throws Exception {

		List<WebElement> splRndTabs = driver

				.findElements(By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td"));

		if (splRndTabs != null || splRndTabs.size() != 1) {

			splRndTabs.remove(0);// removing all airlines

			int size = splRndTabs.size();

			for (int i = 2; i <= size + 1; i++) {

				String title = driver

						.findElement(

								By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a/span/img"))

						.getAttribute("title");

				addLog(title, true);

				if (title.equalsIgnoreCase("SpiceJet") || title.equalsIgnoreCase("IndiGo")) {

					if (isElementPresent(driver,

							By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a/small"))) {

						String discount = driver

								.findElementByXPath(

										".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a/small")

								.getText();

						printInfo(discount);

						// if()//Save Rs.1

						// String[] x = discount.contains("Save");

						// int d = Integer.parseInt(x[1]);

						// int discountAmnt =

						// Integer.parseInt(discount.split(".")[1]);

						if (discount.contains("Save")) {

							safeClick(driver,

									By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td[" + i + "]/a"));

							if (checkIfSplRndTrip(driver)) {

								return true;

							} else {

								addLog("Clicked combo tab, still special round trip flights not selected.");

								printInfo("Clicked combo tab, still special round trip flights not selected.");

								return false; // TODO: check this with ui

								// assertTrue("Failure!", false);

							}

						} else

							continue;

					} else

						continue;

				}

			}

		}

		return false;

	}



	public void b2cExpresswayPayment(RemoteWebDriver driver, String payment_method, String coupon) throws Exception {

		// assertCommon(driver, "Book with Expressway", time_long,

		// "Expressway Booking page");



		elementPresent_Time(driver, getObject("air_accounts_expressway_logo"), 20);

		// isElementPresent(driver, getObject("air_accounts_expressway_logo"));



		b2cLogItinerary(driver);

		expresswayInsurance(driver, false);

		// insuranceBlock(driver, false);

		/*

		 * boolean insurance_present = textPresent(driver,

		 * "Yes, I accept the terms and conditions of the policy", 50); if

		 * (insurance_present) { WebElement insurance_chkBox =

		 * driver.findElement(getObject("air_step1_insurance_chkbox"));

		 *

		 * if (insurance_chkBox.isSelected()) { safeClick(driver,

		 * getObject("air_step1_insurance_chkbox")); } else

		 * addLog("insurance_provider not displayed"); }

		 */



		if (!coupon.equals("")) {



			if (coupon.equalsIgnoreCase("domow")) {

				safeType(driver, By.id("coupon"), "SHACHI");

				/*

				 * if (coupon.equalsIgnoreCase("domow")) { safeType(driver, By.id("coupon"),

				 * "FLYSAVE");

				 */

			} else if (coupon.equalsIgnoreCase("domrt")) {

				safeType(driver, By.id("coupon"), "MOBILE321");

			} else if (coupon.equalsIgnoreCase("intlow")) {

				safeType(driver, By.id("coupon"), "INTLOW");

			} else if (coupon.equalsIgnoreCase("intlrt")) {

				safeType(driver, By.id("coupon"), "INTLRT");

			} else if (coupon.equalsIgnoreCase("validatecoupon")) {

				/* safeType(driver, By.id("coupon"), "SHACHI"); */

				safeType(driver, By.id("coupon"), "DOMOW");

				safeClick(driver, By.id("check_saving"));

				Thread.sleep(2000);

				String CouponApplied = getText(driver, By.id("counter"));

				System.out.println("CouponApplied " + CouponApplied);



				for (int i = 1; i <= 3; i++) {

					safeClick(driver, By.id("check_saving"));

					Thread.sleep(500);

				}

				Thread.sleep(1000);

				String CouponAppliedAgian = getText(driver, By.id("counter"));

				System.out.println("CouponAppliedAgian " + CouponAppliedAgian);

				assertTrue("On Applying Coupon Agian,Amount Mismatched", CouponApplied.matches(CouponAppliedAgian));

			}

			if (coupon.equalsIgnoreCase("domow") || coupon.equalsIgnoreCase("domrt")

					|| coupon.equalsIgnoreCase("intlow") || coupon.equalsIgnoreCase("intlrt")) {

				safeClick(driver, By.id("check_saving"));

				elementPresent_Time(driver, By.xpath("//p[@id='saveMsg']/strong"), 10);

				assertTrue("ExpressWay - Coupon Not Applied",

						getText(driver, By.xpath("//p[@id='saveMsg']/strong")).contains("You just saved"));

				System.out.println("ExpressWay - Coupon Code Applied Successfully");

				addLog("ExpressWay - Coupon Code Applied Successfully", true);

			}

		}

		if (elementPresent(driver, By.xpath(".//*[@id='contactPhoneNumber1']"), 1)) {

			safeType(driver, By.xpath(".//*[@id='contactPhoneNumber1']"), "1234567890");

		}



		String card = getText(driver, getObject("air_step1_expressway_card"));



		if (payment_method.equalsIgnoreCase("migs") && card.contains("5123")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "123");



		} else if (payment_method.equalsIgnoreCase("amex") && card.contains("007")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "1234");

		} else if (payment_method.equalsIgnoreCase("amex") && card.contains("3714")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "1234");

		} else if (payment_method.equalsIgnoreCase("amex") && card.contains("009")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "0773");

		}

		if (MakePaymentTrue && payment_method.equalsIgnoreCase("migs")) {

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			Thread.sleep(15000);

			if (elementPresent_Time(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 60)) {

				elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);

				safeClick(driver, getObject("air_amex_payment_button"));

			}

			// checkBookingStatus(driver);

		} else if (MakePaymentTrue && payment_method.equalsIgnoreCase("amex")) {

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			Thread.sleep(15000);

			if (elementPresent_Time(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 60)) {

				elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);

				safeClick(driver, getObject("air_amex_payment_button"));

			}

			// checkBookingStatus(driver);

		}



	}



	public void b2cExpresswaySSR(RemoteWebDriver driver, String payment_method, String adults, String children,

			String infants, boolean isInternational) throws Exception {

		if (elementPresent(driver, By.xpath(".//*[@id='contactPhoneNumber1']"), 1)) {

			safeType(driver, By.xpath(".//*[@id='contactPhoneNumber1']"), "1234567890");

		}

		int adult_count = Integer.parseInt(adults);

		int children_count = Integer.parseInt(children);

		int infants_count = Integer.parseInt(infants);



		int total_traveller_count = adult_count + children_count + infants_count;

		if (!(total_traveller_count > 1)) {

			String card = getText(driver, getObject("air_step1_expressway_card"));

			enterExpressWayCardDetails(driver, payment_method, card);

		} else {

			safeClick(driver, getObject("LocalCom_ItineraryPage_Button"));

			travellerDetails(driver, adults, children, infants, isInternational, false, false);

			String card = getText(driver, getObject("air_step2_expressway_card"));

			enterExpressWayCardDetails(driver, payment_method, card);

		}

		safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

	}



	public void enterExpressWayCardDetails(RemoteWebDriver driver, String payment_method, String card)

			throws Exception {

		if (payment_method.equalsIgnoreCase("migs") && card.contains("5123")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "123");



		} else if (payment_method.equalsIgnoreCase("amex") && card.contains("007")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "1234");

		} else if (payment_method.equalsIgnoreCase("amex") && card.contains("3714")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "1234");

		} else if (payment_method.equalsIgnoreCase("amex") && card.contains("009")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "0773");

		} else if (payment_method.equalsIgnoreCase("credit card") && card.contains("5123")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "123");

		}



		if (MakePaymentTrue && payment_method.equalsIgnoreCase("migs")) {

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			Thread.sleep(15000);

			if (elementPresent_Time(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 60)) {

				elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);

				safeClick(driver, getObject("air_amex_payment_button"));

			}

			// checkBookingStatus(driver);

		} else if (MakePaymentTrue && payment_method.equalsIgnoreCase("amex")) {

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			Thread.sleep(15000);

			if (elementPresent_Time(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 60)) {

				elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);

				safeClick(driver, getObject("air_amex_payment_button"));

			}

		}

	}



	public void b2cExpresswayPayment1(RemoteWebDriver driver, String payment_method, String coupon) throws Exception {

		boolean paymentDone = false;

		boolean insurance_present = textPresent(driver, "Yes, I accept the terms and conditions of the policy", 50);

		if (insurance_present) {

			WebElement insurance_chkBox = driver.findElement(getObject("air_step1_insurance_chkbox"));



			if (insurance_chkBox.isSelected()) {

				safeClick(driver, getObject("air_step1_insurance_chkbox"));

			} else

				addLog("insurance_provider not displayed");

		}

		safeClick(driver, By.xpath("//span[2]/button"));

		Thread.sleep(2000);

		driver.switchTo().frame("modal_window");



		assertTrue("baggage window not opened",

				driver.findElement(By.cssSelector("h2")).getText().contains("Baggage for"));



		// select baggage

		Actions action = new Actions(driver);



		WebElement we = driver.findElementByCssSelector("a.row.selectAddonListItem");

		action.moveToElement(we).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click()

				.build().perform();



		Thread.sleep(2000);

		assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

		driver.findElementByXPath("//input[@value='Done']").click();

		Thread.sleep(3000);

		addLog("Baggage Added");



		Thread.sleep(2000);

		// safeClick(driver,By.id("pay_by_card_link"));

		// safeClick(driver,By.xpath(".//*[@id='CCTab']/a"));

		String card = "credit card";



		/*

		 * if (coupon.equalsIgnoreCase("domow")) { safeType(driver, By.id("coupon"),

		 * "SHACHI");

		 */

		if (coupon.equalsIgnoreCase("domow")) {

			safeType(driver, By.id("coupon"), "DOMOW");

		} else if (coupon.equalsIgnoreCase("domrt")) {

			safeType(driver, By.id("coupon"), "MOBILE321");

		} else if (coupon.equalsIgnoreCase("intlow")) {

			safeType(driver, By.id("coupon"), "INTLOW");

		} else if (coupon.equalsIgnoreCase("intlrt")) {

			safeType(driver, By.id("coupon"), "INTLRT");

		} else if (coupon.equalsIgnoreCase("validatecoupon")) {

			/* safeType(driver, By.id("coupon"), "SHACHI"); */

			safeType(driver, By.id("coupon"), "DOMOW");

			safeClick(driver, By.id("check_saving"));

			Thread.sleep(2000);

			String CouponApplied = getText(driver, By.id("counter"));

			System.out.println("CouponApplied " + CouponApplied);



			for (int i = 1; i <= 3; i++) {

				safeClick(driver, By.id("check_saving"));

				Thread.sleep(500);

			}

			Thread.sleep(1000);

			String CouponAppliedAgian = getText(driver, By.id("counter"));

			System.out.println("CouponAppliedAgian " + CouponAppliedAgian);

			assertTrue("On Applying Coupon Agian,Amount Mismatched", CouponApplied.matches(CouponAppliedAgian));

		}

		if (coupon.equalsIgnoreCase("domow") || coupon.equalsIgnoreCase("domrt") || coupon.equalsIgnoreCase("intlow")

				|| coupon.equalsIgnoreCase("intlrt")) {

			safeClick(driver, By.id("check_saving"));

			elementPresent_Time(driver, By.xpath("//p[@id='saveMsg']/strong"), 10);

			assertTrue("ExpressWay - Coupon Not Applied",

					getText(driver, By.xpath("//p[@id='saveMsg']/strong")).contains("You just saved"));

			System.out.println("ExpressWay - Coupon Code Applied Successfully");

			addLog("ExpressWay - Coupon Code Applied Successfully");

		}

		// b2cExpresswayPayment(driver,paymentMethod,"validatecoupon");

		// if (MakePaymentTrue && payment_method.equalsIgnoreCase("migs")) {

		Thread.sleep(2000);

		safeClick(driver, By.id("pay_by_card_link"));

		safeClick(driver, By.xpath(".//*[@id='CCTab']/a"));



		paymentDone = b2cPayment(driver, card, 1);



	}



	public void couponvalidation(RemoteWebDriver driver, String coupon) throws Exception {

		if (coupon.equalsIgnoreCase("domowvalidate")) {

			/* safeType(driver, By.id("coupon"), "SHACHI"); */

			safeType(driver, By.id("coupon"), "DOMOW");

			safeClick(driver, By.id("check_saving"));

			Thread.sleep(2000);

			assertTrue("Error - Coupon not applied",

					getText(driver, By.xpath("//p[@id='saveMsg']/strong")).contains("Great"));

			String CouponApplied = getText(driver, By.id("counter"));

			addLog("CouponApplied " + CouponApplied, true);



			for (int i = 1; i <= 3; i++) {

				safeClick(driver, By.id("check_saving"));

				Thread.sleep(500);

			}

			Thread.sleep(1000);

			String CouponAppliedAgian = getText(driver, By.id("counter"));

			addLog("CouponAppliedAgian " + CouponAppliedAgian, true);

			assertTrue("On Applying Coupon Agian,Amount Mismatched", CouponApplied.matches(CouponAppliedAgian));

		} else if (coupon.equalsIgnoreCase("domrtvalidate")) {

			safeType(driver, By.id("coupon"), "MOBILE321");

			safeClick(driver, By.id("check_saving"));

			Thread.sleep(2000);

			assertTrue("Error - Coupon not applied",

					getText(driver, By.xpath("//p[@id='saveMsg']/strong")).contains("Great"));

			String CouponApplied = getText(driver, By.id("counter"));

			addLog("CouponApplied " + CouponApplied, true);



			for (int i = 1; i <= 3; i++) {

				safeClick(driver, By.id("check_saving"));

				Thread.sleep(500);

			}

			Thread.sleep(1000);

			String CouponAppliedAgian = getText(driver, By.id("counter"));

			addLog("CouponAppliedAgian " + CouponAppliedAgian, true);

			assertTrue("On Applying Coupon Agian,Amount Mismatched", CouponApplied.matches(CouponAppliedAgian));

		}

	}



	public void clickOneWayBookButton(RemoteWebDriver driver) {

		try {

			if (waitElement(driver, getObject("AirCom_SRP_Oneway_BookButton"), 1)) {

				JavascriptExecutor js1 = driver;

				List<WebElement> element1 = driver.findElements(getObject("AirCom_SRP_Oneway_BookButton"));

				// js1.executeScript("arguments[0].scrollIntoView(true);",

				// element1.get(0));

				js1.executeScript("window.scrollBy(0,-600)", "");



				safeClick(driver, getObject("AirCom_SRP_Oneway_BookButton"));

			} else {

				JavascriptExecutor js1 = driver;

				List<WebElement> element1 = driver.findElements(getObject("AirCom_SRP_Oneway_BookButton"));

				// js1.executeScript("arguments[0].scrollIntoView(true);",

				// element1.get(0));

				js1.executeScript("window.scrollBy(0,-600)", "");

				safeClick(driver, getObject("AirCom_SRP_Oneway_BookButton_Meta"));

			}

			return;

		} catch (Exception e) {

		}

	}



	public void clickRoundTripBookButton(RemoteWebDriver driver) throws Exception {

		

		WebElement we=driver.findElement(new ByAll(getObject("AirCom_SRP_RoundTrip_BookButton"),getObject("AirCorp_SRP_Intl_RT_book_button"),

				By.xpath("//td/button[contains(@class,'booking')]"), By.xpath("//button[@class='booking fRight']"),

				By.xpath("//*[@id='flightForm']/section[2]/div[3]/div[3]/button")));

		

		try {

			we.click();

		}catch(Exception e) {

			safeClick(driver, getObject("AirCorp_SRP_Intl_RT_book_button"));

		}
		
		for (int i = 0; i < 10; i++) {
			String curl=driver.getCurrentUrl();
			if (curl.toLowerCase().contains("itinerary")) {
				break;
				
			} else {
				Thread.sleep(3000);
			}
		}

	}



	public void clickMCBookButton(RemoteWebDriver driver) {

		try {

			safeClick(driver, getObject("tuxedo_SRP_air_int_book_button"));

			return;

		} catch (Exception e) {

		}

	}



	public void clickIntlRTBookButton(RemoteWebDriver driver) {

		try {

			safeClick(driver, getObject("AirCorp_SRP_Intl_RT_book_button1"));

			return;

		} catch (Exception e) {

		}

	}



	public void MEXpressWayChangeToMigs(RemoteWebDriver driver, String card) throws Exception, InterruptedException {



		String domainUrl = driver.getCurrentUrl();



		if (MakePaymentTrue && card.contains("5123")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "123");

			// safeClick(driver, getObject("air_step4_Submit"));

			Thread.sleep(15000);

		} else if (MakePaymentTrue && card.contains("007")) {

			safeType(driver, getObject("air_step1_expressway_cvvCode"), "1234");

			// safeClick(driver, getObject("air_step4_Submit"));

			Thread.sleep(15000);

		}

		/*

		 * if (card.contains("Amex") && domainUrl.contains("cleartrip.ae")) {

		 * safeClick(driver, getObject("air_step1_expressway_paybycard_link"));

		 * textPresent(driver, "Make payment with your preferred mode", time_short);

		 * safeClick(driver, getObject("air_step1_expressway_storedCard"));

		 * safeType(driver, getObject("air_step1_expressway_cvvCode"), "123"); } else {

		 * String cardDetails = getText(driver, getObject("air_step1_expressway_card"));

		 * addLog("Migs displayed as default :" + cardDetails); safeType(driver,

		 * getObject("air_step1_expressway_cvvCode"), dataFile.value("CVV") + "4"); }

		 */

	}



	public void b2cConfirmation(RemoteWebDriver driver) throws IOException, InterruptedException, Exception {

		if (MakePaymentTrue) {

			if (textPresent(driver, "Oops! We weren't able to process your payment", 30)

					|| textPresent(driver, "Oops, your payment didn", 30)

					|| textPresent(driver, "Oops! Something snapped", 30)) {

				AssertJUnit.assertTrue("Oops! We weren't able to process your payment", false);

				addLog("Payment did go through due to flights not available or payment did not go through");

			} else {

				logURL(driver);

				isElementPresent(driver, getObject("air_sucess_tripid_New"));

				String TripID = getText(driver, getObject("air_sucess_tripid_New"));

				addLog(this.getClass() + " = " + TripID);

				printInfo(this.getClass() + " = " + TripID);

				// logger.info(this.getClass() + " = " + TripID);

			}

		}

	}



	protected void unsignedUser(RemoteWebDriver driver) throws Exception {

		waitElement(driver, getObject("AirCom_BookStep2_EmailID_UserName"), 50);

		Thread.sleep(5000);

		elementClickable(driver, getObject("AirCom_BookStep2_EmailID_UserName"), 50);

		// isElementPresent(driver, getObject("AirCom_BookStep2_EmailID_UserName"));

		driver.findElement(By.id("username")).clear();

		safeType(driver, getObject("AirCom_BookStep2_EmailID_UserName"), "satish.mandewalkar@cleartrip.com");

		safeClick(driver, getObject("AirCom_BookStep2_Continue_Button"));

		waitForElement(driver, 180, By.id("travellerBtn"));

		addLog("Unsigned User Login Successful." + true);

		// elementPresent(driver,By.id("travellerBtn"),1800);

	}



	public boolean NBRetry(RemoteWebDriver driver, String paymentType) throws Exception {

		boolean result = false;



		elementPresent_Time(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 60);

		System.out.println("NB Tab present");

		addLog("NB Tab present");



		if (paymentType.equalsIgnoreCase("NB")) {

			safeClick(driver, getObject("AirCom_BookStep4_NB_Tab"));

			Thread.sleep(2000);

			isTextPresent(driver, "Popular banks");

			safeSelect(driver, getObject("AirCom_BookStep4_Netbank_DropDown"), "Bank of India");

			Thread.sleep(2000);

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));



			Thread.sleep(15000);

			// waitElement(driver, getObject("AirCom_Payment_BankSite"), 50);

			waitForElement(driver, 50, getObject("AirCom_Payment_BankSite"));

			addLog(driver.getCurrentUrl());

			System.out.println(driver.getCurrentUrl());

			System.out.println(driver.getTitle());

			AssertJUnit.assertEquals("Bank of India Online e Payment", driver.getTitle());

			addLog("Net Banking Site Reached");

			printInfo("Net Banking Site Reached");

			driver.findElement(By.linkText("Return to Biller Site")).click();

			Thread.sleep(2000);

			if (elementVisible(driver, By.xpath("//*[contains(text(),'Transaction')]"), 10)) {

				safeClick(driver, By.xpath("//*[text()='Cancel']"));



			}



			elementPresent_Time(driver, By.cssSelector("h1"), 100);

			textPresent(driver, "Oops, your payment didnt work", 10);



			AssertJUnit.assertEquals("Cleartrip | Book your flight securely in simple steps", driver.getTitle());

			AssertJUnit.assertEquals("Oops, your payment didnt work",

					driver.findElement(By.cssSelector("h1")).getText());

			addLog("Successfully arrived on Payment retry page from NB");

			printInfo("Successfully arrived on Payment retry page from NB");

			Thread.sleep(5000);

			result = true;

		}

		return result;

	}



	protected boolean MigsRetry(RemoteWebDriver driver, String paymentType) throws Exception, InterruptedException {



		if (paymentType.equalsIgnoreCase("migsretry")) {

			safeClick(driver, getObject("AirCom_BookStep4_CreditCard_Tab"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"),

					dataFile.value("RTMasterCard_Exp_Month"));

			safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"),

					dataFile.value("RTMasterCard_Exp_Year"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"),

					dataFile.value("MasterCard_HolderName"));

			safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("RTMasterCard_CVV"));

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			elementPresent_Time(driver, By.xpath("//strong[2]"), 20);

			// System.out.println(getText(driver, By.xpath("//strong[2]")));

			// addLog(getText(driver, By.xpath("//strong[2]")));

			textPresent(driver, "Oops, your payment didnt work", 30);

			addLog("Migs retry - Successfully arrived on Payment retry page");

			printInfo("Migs retry - Successfully arrived on Payment retry page from NB");

			Thread.sleep(5000);

			// assertTrue("Payment Retry Page Did Not Appear",

			// textPresent(driver, "your payment didnt work", 1));

			return false;

		}

		return true;

	}



	protected void PaymentRetry(RemoteWebDriver driver, String paymentType) throws Exception {



		elementPresent_Time(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 60);

		/* System.out.println("NB Tab present"); */

		addLog("NB Tab present", true);

		safeClick(driver, getObject("AirCom_BookStep4_NB_Tab"));



		if (paymentType.equalsIgnoreCase("NB")) {

			if (!isTextPresent(driver, "Popular banks")) {

				safeClick(driver, getObject("AirCom_BookStep4_NB_Tab"));

			} else {

				isTextPresent(driver, "Popular banks");

				Thread.sleep(1000);

				safeSelect(driver, getObject("AirCom_BookStep4_Netbank_DropDown"), "Bank of India");

				Thread.sleep(1000);

				safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

				/*

				 * Thread.sleep(20000); driver.navigate().refresh();

				 */

				// waitElement(driver, getObject("AirCom_Payment_BankSite"), 60);

				elementVisible(driver, getObject("AirCom_Payment_BankSite"), 60);

				AssertJUnit.assertEquals("Bank of India Online e Payment", driver.getTitle());

				addLog("Net Banking Site Reached");

				printInfo("Net Banking Site Reached");

				driver.findElement(By.linkText("Return to Biller Site")).click();



				Thread.sleep(5000);

				if (!(common.value("host").equals("www"))) {

					if (driver.getCurrentUrl().contains("www")) {

						String url = driver.getCurrentUrl().replace("www", "qa2");

						driver.get(url);

					}

				}

				textPresent(driver, "Cleartrip Travel Services Pvt Ltd", 20);

				elementVisible(driver, By.linkText("Cancel"), 10);

				smartClick(driver, By.linkText("Cancel"));



				textAssert(driver, "Oops, your payment didn", 30);



				/*

				 * elementPresent_Time(driver, By.cssSelector("h1"), 50);

				 * 

				 * String textpresent = getText(driver, By.cssSelector("h1"));

				 * 

				 * assertTrue("Payment Retry Page not displayed",

				 * textpresent.contains("Oops, your payment"));

				 */



				// AssertJUnit.assertEquals("Cleartrip | Book your flight securely in simple

				// steps",

				// driver.getTitle());

				// AssertJUnit.assertEquals("Oops, your payment didnt work",

				// driver.findElement(By.cssSelector("h1")).getText());

				addLog("Successfully arrived on Payment retry page from NB");

			}

		}

		if (paymentType.equalsIgnoreCase("NB1")) {

			System.out.println(driver.getCurrentUrl());

			if (!isTextPresent(driver, "Popular banks")) {

				safeClick(driver, getObject("AirCom_BookStep4_NB_Tab"));

			} else {

				isTextPresent(driver, "Popular banks");

				Thread.sleep(1000);

				safeSelect(driver, getObject("AirCom_BookStep4_Netbank_DropDown"), "State Bank of India");

				Thread.sleep(1000);

				safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

				/*

				 * Thread.sleep(20000); driver.navigate().refresh();

				 */

				Thread.sleep(10000);

				waitElement(driver, getObject("AirCom_Payment_BankSite"), 50);

				driver.navigate().refresh();

				Thread.sleep(5000);

				// System.out.println("title="+driver.getTitle());

				AssertJUnit.assertEquals("State Bank of India - Internet Banking", driver.getTitle());

				addLog("Net Banking Site Reached");

				printInfo("Net Banking Site Reached");

				safeClick(driver, By.linkText("Click here"));



				// driver.findElement(By.className("ul.bottom_notes > li > a")).click();

				Thread.sleep(5000);

				elementPresent_Time(driver, By.cssSelector("h1"), 100);



				String textpresent = getText(driver, By.cssSelector("h1"));



				assertTrue("Payment Retry Page not displayed", textpresent.contains("Oops, your payment"));



				// textPresent(driver, "Oops, your payment didnt work", 10);



				AssertJUnit.assertEquals("Cleartrip | Book your flight securely in simple steps", driver.getTitle());

				// AssertJUnit.assertEquals("Oops, your payment didnt work",

				// driver.findElement(By.cssSelector("h1")).getText());

				addLog("Successfully arrived on Payment retry page from NB");

				printInfo("Successfully arrived on Payment retry page from NB");

				Thread.sleep(5000);

			}

		}



		else if (paymentType.equalsIgnoreCase("NBAE")) {

			safeClick(driver, getObject("AirCom_BookStep4_NB_Tab"));

			Thread.sleep(500);

			safeClick(driver, By.id("adcb_bank"));

			Thread.sleep(500);

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			Thread.sleep(10000);

			waitElement(driver, By.xpath("//div[@id='Message']/input"), 50);

			System.out.println("ADCB Interstitial Page Displayed");

			addLog("ADCB Interstitial Page Displayed");

			safeClick(driver, By.xpath("//div[@id='Message']/input"));

			Thread.sleep(10000);

			String ADCB = driver.getTitle();

			System.out.println(ADCB + "PAGE REACHED");

			addLog(ADCB);

			driver.navigate().back();

			driver.navigate().back();



		} else if (paymentType.equalsIgnoreCase("wallet")) {

			assertTrue("Wallet as Payment Option is Not Present",

					elementPresent(driver, getObject("AirCom_Payment_Type_WalletLink")));

			safeClick(driver, getObject("AirCom_Payment_Type_WalletLink"));

			// safeClick(driver,

			// getObject("AirCom_Payment_Type_WalletRadioBtn"));

			Thread.sleep(500);

			safeClick(driver, By.xpath("//li[@data-paymentsubtype='PAYU_WALLET']/label/input"));

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			Thread.sleep(5000);

			waitElement(driver, getObject("AirCom_PayUWallet_BackToCustomerSiteBtn"), 90);

			safeClick(driver, getObject("AirCom_PayUWallet_BackToCustomerSiteBtn"));



			Thread.sleep(2000);

			textPresent(driver, "Oops, your payment didnt work", 100);



			AssertJUnit.assertEquals("Cleartrip | Book your flight securely in simple steps", driver.getTitle());

			Assert.assertEquals("Oops, your payment didnt work", driver.findElement(By.cssSelector("h1")).getText());

			addLog("Successfully arrived on Payment retry page from PayU Wallet Page");

			printInfo("Successfully arrived on Payment retry page from PayU Wallet Page");



		} else if (paymentType.equalsIgnoreCase("mpwallet")) {

			assertTrue("Wallet as Payment Option is Not Present",

					elementPresent(driver, getObject("AirCom_Payment_Type_WalletLink")));



			safeClick(driver, getObject("AirCom_Payment_Type_WalletLink"));

			Thread.sleep(500);

			safeClick(driver, By.xpath("//li[@data-paymentsubtype='MASTERPASS_WALLET']/label/input"));

			// safeClick(driver,

			// getObject("AirCom_Payment_Type_mpWalletRadioBtn"));



			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));



			// elementPresent_Time(driver, By.id("find-wallet"), 15);

			Thread.sleep(5000);

			waitElement(driver, By.id("MasterPass_container"), 180);

			Thread.sleep(10000);

			driver.switchTo().frame("MasterPass_frame");

			safeClick(driver, getObject("AirCom_mpWalletPage_Close"));

			Thread.sleep(5000);

			waitElement(driver, By.xpath("//button[@type='submit']"), 30);

			driver.findElement(By.xpath("//button[@type='submit']")).click();



			addLog("MasterPass Wallet Page Displayed");

			printInfo("MasterPass Wallet Page Displayed");

			Thread.sleep(5000);



			driver.switchTo().defaultContent();



			try {

				if (waitElement(driver, By.id("CancelForm"), 10)) {

					// driver.switchTo().activeElement();

					safeClick(driver, By.id("CancelForm"));

				}

			} catch (Exception e) {

				driver.navigate().back();

				driver.navigate().back();

				driver.navigate().back();

				safeClick(driver, By.xpath("//button[@id='MasterPass_close']"));

				elementPresent_Time(driver, By.xpath("//button[@type='submit']"), 1);

				driver.findElement(By.xpath("//button[@type='submit']")).click();

			}



			/*

			 * driver.switchTo().defaultContent(); waitElement(driver,

			 * getObject("AirCom_mpWalletPage_ReturnToCustomerSite"), 5); safeClick(driver,

			 * getObject("AirCom_mpWalletPage_ReturnToCustomerSite"));

			 */

			Thread.sleep(15000);

			driver.switchTo().defaultContent();

			if (waitElement(driver, By.xpath("//div/div/div/h1"), 20)) {

				// textPresent(driver, "Oops, your payment didnt work", 50);



				// AssertJUnit.assertEquals("Cleartrip | Book your flight securely in simple

				// steps",

				// driver.getTitle());

				if (getText(driver, By.xpath("//div/div/div/h1")).equalsIgnoreCase("Oops, your payment didnt work")) {

					addLog("Successfully arrived on Payment retry page from MasterPass Wallet Page");

					printInfo("Successfully arrived on Payment retry page from MasterPass Wallet Page");

				} else {

					addLog("Payment retry page not loaded. Error!");

					assertTrue(false);

				}

			} else {

				addLog("Payment retry page not loaded. Error!");

				assertTrue(false);

			}

		}



		else if (paymentType.equalsIgnoreCase("CCRetry")) {

			safeClick(driver, getObject("AirCom_BookStep4_CreditCard_Tab"));

			addLog("Attempting CC Payment with wrong CC details", true);

			fillCCdetails(driver, 6);

			check_consent(driver, getObject("AirCom_BookStep4_Consent"));



			elementPresent_Time(driver, By.cssSelector("h1"), 100);

			String textpresent = getText(driver, By.cssSelector("h1"));

			if (textpresent.contains("Oops! We weren't able to process your payment")) {

				safeClick(driver, getObject("AirCom_Payment_Failed_Page_With_Change_Your_Payment_Method"));

			}

			assertTrue("Payment Retry Page not displayed", textpresent.contains("Oops, your payment"));

			addLog("Payment retry page not loaded");

		}



		else {

			addLog("Payment method not found", true);

		}



	}



	protected void PaymentFlexiRetry(RemoteWebDriver driver) throws Exception {

		if (!isTextPresent(driver, "Popular banks")) {

			safeClick(driver, getObject("AirCom_BookStep4_NB_Tab"));

		} else {

			isTextPresent(driver, "Popular banks");



			safeSelect(driver, getObject("AirCom_BookStep4_Netbank_DropDown"), "Bank of India");

			Thread.sleep(1000);

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));



			Thread.sleep(10000);

			waitElement(driver, getObject("AirCom_Payment_BankSite"), 50);



			AssertJUnit.assertEquals("Bank of India Online e Payment", driver.getTitle());

			addLog("Net Banking Site Reached");

			printInfo("Net Banking Site Reached");

			driver.findElement(By.linkText("Return to Biller Site")).click();

			Thread.sleep(2000);

			textPresent(driver, "Pay to confirm", 100);



			textAssert(driver, getObject("AirCom_Payment_FlexiPay_PaymentPage"), "Pay to confirm");

			addLog("Successfully arrived on Payment retry page from NB");

			printInfo("Successfully arrived on Payment retry page from NB");

			Thread.sleep(5000);

		}

	}



	public void SelectAddBaggageFlight(RemoteWebDriver driver) throws Exception, InterruptedException {

		for (int i = 1; i <= 50; i++) {

			//

			if (i % 6 == 0) {

				continue;

			}

			if (elementVisible(driver, By.xpath("//ul/li[" + i + "]/table/tbody[2]/tr[2]/td[3]/button[@type='submit']"),

					1)) {

				driver.findElement(By.xpath("//ul/li[" + i + "]/table/tbody[2]/tr[2]/td[3]/button[@type='submit']"))

						.click();

				printInfo("Baggage flight found");



			}

			addLog("No flights without baggage found");

			// assertTrue("No flights without baggage found", false);



		}



	}



	public void SeatSelect_OW(RemoteWebDriver driver) throws Exception, InterruptedException {

		if (elementVisible(driver, By.id("priceChangeDownBtn"), 20)) {

			safeClick(driver, By.id("priceChangeDownBtn"));

		}



		if (elementVisible(driver, By.xpath("//*[@id='seatmap_tryagain']"), 10)) {

			driver.findElement(By.xpath("//*[@id='seatmap_tryagain']")).click();

			Thread.sleep(5000);

		}

		/*

		 * List<WebElement>

		 * we1=driver.findElements(By.xpath("//*[@class='action selectAddonButton']"));

		 * for(int i=0;i<we1.size();i++) { if(we1.get(i).getText().contains("Seats")) {

		 * we1.get(i).click(); break; } }

		 */

		safeClick(driver, By.xpath("//*[contains(text(),'Travel in comfort')]//..//../../*[1]/*[2]/button"));



		/*

		 * if (elementPresent(driver, By.xpath("//*[contains(text(),'Select Seats')]"),

		 * 10)) {

		 * 

		 * assertTrue( "Seat Selection Button is Not displayed",

		 * isElementPresent(driver, By.xpath("//*[contains(text(),'Select Seats')]")));

		 * safeClick(driver,By.xpath("//*[contains(text(),'Select Seats')]"));

		 * Thread.sleep(5000);

		 * 

		 * } else if (elementPresent(driver, By.id("beforeSeats"),5)) {

		 * safeClick(driver, By.id("beforeSeats")); Thread.sleep(5000); }

		 */



		for (String winHandle : driver.getWindowHandles()) {

			System.out.println("New window displayed" + winHandle);

			driver.switchTo().window(winHandle);

			System.out.println("new window selected");

		}



		boolean seatsLoaded = true;

		safeClick(driver, By.xpath("//*[contains(@class,'available free')]"));

		/*

		 * for (int x = 0; x < 10; x++) { if (elementVisible(driver,

		 * By.xpath("//*[@id='seatmap_tryagain']"), 1)) {

		 * driver.findElement(By.xpath("//*[@id='seatmap_tryagain']")) .click();

		 * seatsLoaded = false; Thread.sleep(30000); continue; } }

		 */

		Assert.assertTrue(seatsLoaded, "Seats not loaded after multiple retries!");

		try {

			if (elementVisible(driver, By.xpath("//*[text()='Continue']"), 1)) {

				do {

					List<WebElement> we = driver.findElements(By.xpath("//*[contains(@class,'available free')]"));

					for (int i = 0; i < we.size(); i++) {

						if (we.get(i).isDisplayed()) {

							we.get(i).click();

							break;

						}

					}

					safeClick(driver, By.xpath("//*[text()='Continue']"));

				} while (elementVisible(driver, By.xpath("//*[text()='Continue']"), 1));

				// safeClick(driver,By.xpath("//*[text()='Continue']"));

			}

			if (elementVisible(driver, By.xpath("//*[contains(text(),'Confirm your')]"), 1)) {



				List<WebElement> we = driver.findElements(By.xpath("//*[contains(@class,'available free')]"));

				for (int i = 0; i < we.size(); i++) {

					if (we.get(i).isDisplayed()) {

						we.get(i).click();

						break;

					}

				}



				// JavascriptExecutor js1 = (JavascriptExecutor) driver;

				// js1.executeScript("arguments[0].click();",By.xpath("//*[contains(@class,'available

				// free')]"));

				// safeClick(driver,By.xpath("//td[17]/a"));

				safeClick(driver, By.xpath("//*[contains(text(),'Confirm your')]"));

			}



		} catch (Exception e) {

			safeClick(driver, By.id("next"));

		}



	}



	public void addbaggageOW(RemoteWebDriver driver) {

		try {

			if (elementVisible(driver, By.id("priceChangeDownBtn"), 2)) {

				safeClick(driver, By.id("priceChangeDownBtn"));

			}

			elementPresent(driver, By.xpath("//*[@id='beforeBaggae']/span/button"), 60);

			/*

			 * List<WebElement>

			 * we1=driver.findElements(By.xpath("//*[@class='action selectAddonButton']"));

			 * for(int i=0;i<we1.size();i++) { if(we1.get(i).getText().contains("Baggage"))

			 * { we1.get(i).click(); break; } }

			 */

			safeClick(driver, By.xpath("//*[@id='beforeBaggae']/span/button"));

			WebDriverWait wait = new WebDriverWait(driver, 5);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("modal_window"));

			Thread.sleep(6000);

			// wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("h2"),"Baggage

			// for"));

			assertTrue("baggage window not opened",

					driver.findElement(By.cssSelector("h2")).getText().contains("Baggage for"));



			// select baggage

			Actions action = new Actions(driver);



			WebElement we = driver.findElementByCssSelector("a.row.selectAddonListItem");

			action.moveToElement(we).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click()

					.build().perform();

			Thread.sleep(2000);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Done']")));

			assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

			driver.findElementByXPath("//input[@value='Done']").click();

			Thread.sleep(3000);

			addLog("Baggage Added");

		} catch (Exception e) {

			addLog("Unable to add baggage, trying again");

			// attempt++;

			// continue;

		}

	}



	public void addMealOW(RemoteWebDriver driver) throws Exception {

		if (elementVisible(driver, By.id("priceChangeDownBtn"), 2)) {

			safeClick(driver, By.id("priceChangeDownBtn"));

		}

		boolean addmealButtonFound = addMealButton(driver);

		if (!addmealButtonFound) {

			addLog("Add Meal Button Not Found.");

		}



		if (elementPresent(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"), 3)) {

			safeClick(driver, getObject("AirCom_BookStep_AddMealBtn_Bundle"));



			if (elementPresent(driver, By.xpath("//button[@class='action selectAddonButton']"), 3)) {

				safeClick(driver, By.xpath("//button[@class='action selectAddonButton']"));

			}

			WebDriverWait wait = new WebDriverWait(driver, 5);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("modal_window"));

			// Thread.sleep(6000);



			// driver.switchTo().frame("modal_window");

		} else if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn"), 5)) {

			safeClick(driver, getObject("AirCom_BookStep_AddMealBtn"));

			WebDriverWait wait = new WebDriverWait(driver, 5);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("modal_window"));

			// driver.switchTo().frame("modal_window");

		}

		Thread.sleep(6000);

		/*

		 * WebDriverWait wait = new WebDriverWait(driver,10);

		 * wait.until(ExpectedConditions.textToBePresentInElement(By.xpath(

		 * "//*[contains(text(),'Veg')]"),"Vegetarian Meal"));

		 */

		assertTrue("Meals window not opened", driver.findElement(By.cssSelector("h2")).getText().contains("Meals for"));



		// select meal

		Actions action = new Actions(driver);



		WebElement we1 = driver.findElementByCssSelector("a.row.selectAddonListItem");

		action.moveToElement(we1).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem")).click()

				.build().perform();



		Thread.sleep(2000);

		assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

		driver.findElementByXPath("//input[@value='Done']").click();

		Thread.sleep(1000);

		addLog("Meals Added");

		// System.out.println("Meals Added");



		driver.switchTo().defaultContent();

	}



	public void seatSelectReturn(RemoteWebDriver driver, String from, String to) throws Exception {



		System.out.println("element status===" + elementVisible(driver, By.id("next"), 5));

		safeClick(driver, getObject("AirCom_BookStep_SeatSelect_Btn"));

		Thread.sleep(10000);



		for (String winHandle : driver.getWindowHandles()) {

			System.out.println("New window displayed" + winHandle);

			driver.switchTo().window(winHandle);

			System.out.println("new window selected");

		}



		if (elementPresent_Time(driver, By.id("next"), 600)) {

			safeClick(driver, By.id("next"));

		}



		Thread.sleep(2000);

		System.out.println("from value=" + from);

		System.out.println("to value=" + to);

		System.out.println("//tbody[@id='" + to + "-" + from + "']//*[@class='available economy']");

		if (elementPresent_Time(driver,

				By.xpath("//tbody[@id='" + to + "-" + from + "']//*[@class='available economy']"), 2)) {

			driver.findElement(By.xpath("//tbody[@id='" + to + "-" + from + "']//*[@class='available economy']"))

					.click();

		}

		safeClick(driver, getObject("AirCom_BookStep_SeatSelect_ConfirmSeat_Btn"));

		System.out.println("Seat Selected To Return Trip Alone");

		addLog("Seat Selected To Return Trip Alone");

		/*

		 * if (elementVisible(driver,

		 * By.xpath("//tbody[2]/tr/td[3]/a[starts-with(@class,'available')]"

		 * ),3)){////tbody[2]/tr/td[3]/a[2] System.out.println("Seat Found");

		 * ////tbody[2]/tr/td[3]/a[not(contains(@class, 'occupied')) and

		 * starts-with(@class, 'available free')] safeClick(driver,By.xpath(

		 * "//tbody[2]/tr/td[3]/a[starts-with(@class, 'available')]")); }else{

		 * System.out.println("Seat Selection Model Window Does Not Loaed");

		 * addLog("Seat Selection Model Window Does Not Loaed"); }

		 * safeClick(driver, getObject("AirCom_BookStep_SeatSelect_ConfirmSeat_Btn"));

		 * System.out.println("Seat Selected To Return Trip Alone");

		 * addLog("Seat Selected To Return Trip Alone");

		 */



	}



	public void SeatSelect_RT(RemoteWebDriver driver, String OnwardSeatSelection, String ReturnOnwardSeatSelection)

			throws Exception, InterruptedException {



		elementPresent_Time(driver, getObject("AirCom_BookStep_SeatSelect_Btn"), 90);

		// Thread.sleep(30000);

		assertTrue("Seat Selection Button is Not displayed",

				isElementPresent(driver, getObject("AirCom_BookStep_SeatSelect_Btn")));



		safeClick(driver, getObject("AirCom_BookStep_SeatSelect_Btn"));

		Thread.sleep(30000);



		for (String winHandle : driver.getWindowHandles()) {

			addLog("New window displayed" + winHandle, true);

			driver.switchTo().window(winHandle);

			addLog("new window selected", true);

		}



		int v = 0;

		boolean seatsLoaded = true;



		while (!waitForElementVisibility(driver, By.id("next"), 15) && v < 10) {

			if (waitForElementVisibility(driver, By.xpath("//*[@id='seatmap_tryagain']"), 15)) {

				driver.findElement(By.xpath("//*[@id='seatmap_tryagain']")).click();

				seatsLoaded = false;

				Thread.sleep(15000);

			}

			v++;

		}



		Assert.assertTrue(seatsLoaded, "Seats not loaded after multiple retries!");



		// if (OnwardSeatSelection.equalsIgnoreCase("SeatSel2OW")) {

		// System.out.println("enters in seat map if");

		/*

		 * if(elementPresent(driver,By.id("seatmap_tryagain"))){

		 * driver.findElement(By.xpath(

		 * "//div[@class='Modal clearFix travellers seatSelection']/div/p/a" )).click();

		 *

		 * //driver.findElement(By.id("seatmap_tryagain")).click(); }

		 */

		if (elementPresent(driver, By.cssSelector("a.available.free"))) { // css=a.available.free

			// xpath("//td[2]/a[starts-with(@class, 'available')]")

			// //*[not(contains(@class,

			// 'occupied'))

			// and

			// starts-with(@class,

			// 'available

			// free')]

			addLog("Seat Found", true);

			safeClick(driver, By.cssSelector("a.available.free")); // xpath("//td[2]/a[starts-with(@class,

																	// 'available')]")

		} else {

			// System.out.println("Seat Selection Model Window Does Not Loaed");

			addLog("Seat Selection Model Window Does Not Loaed", true);

		}

		if (elementVisible(driver, By.id("next"), 5)) {

			safeClick(driver, By.id("next"));

		}

		Thread.sleep(1000);

		// safeClick(driver,

		// getObject("AirCom_BookStep_SeatSelect_ConfirmSeat_Btn"));

		// System.out.println("Seat Selected To Onward Trip Alone");

		addLog("Seat Selected To Onward Trip Alone", true);



		// }



		if (ReturnOnwardSeatSelection.equalsIgnoreCase("SeatSel2RT")) {

			addLog("element status===" + elementVisible(driver, By.id("next"), 5), true);

			Thread.sleep(2000);

			if (elementVisible(driver, By.xpath("//tbody[2]/tr/td[3]/a[starts-with(@class,'available')]"), 3)) {// //tbody[2]/tr/td[3]/a[2]

				addLog("Seat Found", true);

				// //tbody[2]/tr/td[3]/a[not(contains(@class, 'occupied')) and

				// starts-with(@class, 'available free')]

				safeClick(driver, By.xpath("//tbody[2]/tr/td[3]/a[starts-with(@class, 'available')]"));



			} else if (elementPresent(driver, By.cssSelector("a.available.free"), 3)) {

				safeClick(driver, By.cssSelector("a.available.free"));

			} else {



				// System.out.println("Seat Selection Model Window Does Not Loaed");

				addLog("Seat Selection Model Window Does Not Loaed", true);

			}

			safeClick(driver, getObject("AirCom_BookStep_SeatSelect_ConfirmSeat_Btn"));

			// System.out.println("Seat Selected To Return Trip Alone");

			addLog("Seat Selected To Return Trip Alone", true);

		}

		v = 0;

		if (OnwardSeatSelection.equalsIgnoreCase("") && ReturnOnwardSeatSelection.equalsIgnoreCase("")) {

			while (v < 10) {

				if (waitForElementVisibility(driver, By.id("confirmSeat"), 15)) {

					break;

				}

				if (waitForElementVisibility(driver, By.id("next"), 15)) {

					break;

				}

				if (waitForElementVisibility(driver, By.xpath("//*[@id='seatmap_tryagain']"), 1)) {

					driver.findElement(By.xpath("//*[@id='seatmap_tryagain']")).click();

					seatsLoaded = false;

					Thread.sleep(15000);

				}

				v++;

			}

			Assert.assertTrue(seatsLoaded, "Seats not loaded after multiple retries!");



			if (elementVisible(driver, By.cssSelector("a.available.free"), 4)) {

				addLog("Seat Found", true); // //*[not(contains(@class,

				// 'occupied')) and

				// starts-with(@class,

				// 'available free')]

				safeClick(driver, By.cssSelector("a.available.free"));

			}

			if (elementVisible(driver, By.id("next"), 5)) {

				safeClick(driver, By.id("next"));

			}

			// System.out.println("Seat Selected To Onward Trip");

			addLog("Seat Selected To Onward Trip", true);

			Thread.sleep(2000);

			if (elementVisible(driver, By.cssSelector("a.available.free"), 4)) {

				addLog("Seat Found", true); // //*[not(contains(@class,

				// 'occupied')) and

				// starts-with(@class,

				// 'available free')]

				safeClick(driver, By.cssSelector("a.available.free"));

			} else if (elementPresent(driver, By.xpath("//tbody"), 2)) {

				String sector = "";

				List<WebElement> we = driver.findElements(By.xpath("//tbody"));

				for (int i = 0; i < we.size(); i++) {

					sector = we.get(1).getAttribute("id");

					addLog(we.get(i).getAttribute("id"), true);

				}

				System.out.println(".//*[@id='" + sector + "']/tr/td");

				List<WebElement> we1 = driver.findElements(By.xpath(".//*[@id='" + sector + "']/tr/td"));

				test: for (int j = 0; j < we1.size(); j++) {

					List<WebElement> we2 = we1.get(j).findElements(By.xpath("./a"));

					addLog("" + we2.size(), true);

					for (int a = 0; a < we2.size(); a++) {



						addLog(we2.get(a).getAttribute("class"), true);

						if (we2.get(a).getAttribute("class").contains("available")) {

							we2.get(a).click();

							break test;

						}

					}

					// System.out.println(we.get(j).getAttribute("class"));

				}



			}

			/*

			 * List<WebElement> we=driver.findElements(By.xpath(

			 * "//*[@id='flightSeatMap']/tbody/tr/td/a")); Test:for(int

			 * i=1;i<we.size();i++){ for(int j=2;j<we.size();j++){ //driver.findElement

			 * (By.xpath("//*[@id='DEL-BOM']/tr/td[4]/a[2]")).click();

			 * System.out.println("in for loop==="+driver.findElement(By.xpath(

			 * "//*[@id='flightSeatMap']/tbody/tr/td["

			 * +j+"]/a["+i+"]")).getAttribute("class")); if(driver.findElement(By

			 * .xpath("//*[@id='flightSeatMap']/tbody/tr/td[" +j+"]/a["+i+"]")).getAttribute

			 * ("class").contains("available free")){ //finalTap(driver,By.xpath(

			 * "//*[@id='flightSeatMap']/tbody/tr/td["+j+"]/a["+i+"]")); driver.findElement

			 * (By.xpath("//*[@id='flightSeatMap']/tbody/tr/td[" +j+"]/a["+i+"]")).click();

			 * break Test; }

			 *

			 * } }

			 */



			/*

			 * for(int i=0;i<we.size();i++){ System.out.println("checking seat==="

			 * +we.get(i).getAttribute("class"));

			 *

			 * if(we.get(i).getAttribute("class").equalsIgnoreCase("available free" )){

			 * //finalTap(we.get(i)); System.out.println("checking seat111111==="

			 * +we.get(i).getAttribute("class")); we.get(i).click(); }

			 *

			 * } System.out.println("before click"); System.out.println("size="+we.size());

			 * System.out.println("seat name====" +we.get(0).getAttribute("seatname"));

			 * we.get(0).click(); System.out.println("after click"); // test:for(int

			 * i=0;i<we.size();i++){ if(we.get(i).getAttribute("class").equalsIgnoreCase

			 * ("available free")){ System.out.println("attribute class="+we.get(

			 * i).getAttribute("class")); we.get(i).click();

			 *

			 * break test; }

			 */

			// /System.out.println("attribute class="+we.get(i).getAttribute("class"));

			// }

			/*

			 * if (elementPresent(driver,

			 * By.xpath("//tbody[2]/tr/td[3]/a[starts-with(@class, 'available')]" ))){ //

			 * //tbody[2]/tr/td[3]/a[not(contains(@class, 'occupied')) and

			 * starts-with(@class, 'available free')] System.out.println("Seat Found");

			 * safeClick(driver,By.xpath(

			 * "//tbody[2]/tr/td[3]/a[starts-with(@class, 'available')]")); }

			 */

			safeClick(driver, getObject("AirCom_BookStep_SeatSelect_ConfirmSeat_Btn"));

			// System.out.println("Seat Selected To Return Trip Alone");

			addLog("Seat Selected To Return Trip Alone", true);



		}



	}



	public void SelectBundleFareAddOn(RemoteWebDriver driver, String insurance, String baggage, String meals,

			String seat) throws Exception, InterruptedException {



		if (insurance.equalsIgnoreCase("insurance")) {



			if (elementPresent_Time(driver, By.xpath("//span[@class='insurance beforeInsurance']"), 1)) {

				safeClick(driver, By.xpath("//span[@class='insurance beforeInsurance']"));

				// driver.switchTo().frame("modal_window");

				safeClick(driver, By.id("modalInsuranceConfirm"));

				safeClick(driver, By.id("addInsuranceToTrip"));



				assertTrue("BundleFare Insurance Text Missing", textPresent(driver, "free cancellation", 1));

				System.out.println("Insurance selected.");

				addLog("Insurance selected.");



			}



		}



		if (baggage.equalsIgnoreCase("baggage")) {

			if (elementVisible(driver, By.xpath("//span[@id='beforeBaggage']"), 10)) {



				assertTrue("Baggage not found", elementPresent(driver, By.id("beforeBaggage")));

				safeClick(driver, By.id("beforeBaggage"));

				Thread.sleep(5000);

				driver.switchTo().frame("modal_window");



				assertTrue("baggage window not opened",

						driver.findElement(By.cssSelector("h2")).getText().contains("Baggage for"));



				Actions action = new Actions(driver);



				WebElement we = driver.findElementByCssSelector("a.row.selectAddonListItem");

				action.moveToElement(we).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem"))

						.click().build().perform();



				Thread.sleep(2000);

				assertTrue("baggage not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

				driver.findElementByXPath("//input[@value='Done']").click();

				Thread.sleep(3000);

				addLog("Baggage Added");

				System.out.println("Baggage Added");



				Thread.sleep(2000);

				driver.switchTo().parentFrame();



			}



		}



		if (meals.equalsIgnoreCase("meals")) {



			if (elementVisible(driver, By.xpath("//span[@id='beforeMeals']"), 10)) {

				safeClick(driver, By.id("beforeMeals"));

				Thread.sleep(5000);

				driver.switchTo().frame("modal_window");

				if (elementPresent_Time(driver, getObject("AirCom_BookStep_AddMealBtn"), 1)) {

					safeClick(driver, getObject("AirCom_BookStep_AddMealBtn"));

					Thread.sleep(10000);

					driver.switchTo().frame("modal_window");

				}



				assertTrue("Meals window not opened",

						driver.findElement(By.cssSelector("h2")).getText().contains("Meals for"));



				// select meal

				Actions action = new Actions(driver);



				WebElement we1 = driver.findElementByCssSelector("a.row.selectAddonListItem");

				action.moveToElement(we1).moveToElement(driver.findElementByCssSelector("a.row.selectAddonListItem"))

						.click().build().perform();



				Thread.sleep(2000);

				assertTrue("Meals not added", driver.findElementByXPath("//input[@value='Done']").isEnabled());

				driver.findElementByXPath("//input[@value='Done']").click();

				Thread.sleep(1000);

				addLog("Meals Added");

				System.out.println("Meals Added");



				driver.switchTo().defaultContent();



			}



		}



		if (seat.equalsIgnoreCase("seat")) {

			if (elementPresent_Time(driver, getObject("AirCom_BookStep_SeatSelect_Btn"), 10)) {

				Thread.sleep(10000);

				assertTrue("Seat Selection Button is Not displayed",

						isElementPresent(driver, getObject("AirCom_BookStep_SeatSelect_Btn")));

				safeClick(driver, getObject("AirCom_BookStep_SeatSelect_Btn"));

				Thread.sleep(60000);



			} else if (elementPresent(driver, By.id("beforeSeats"))) {

				safeClick(driver, By.id("beforeSeats"));

				Thread.sleep(60000);

			}



			for (String winHandle : driver.getWindowHandles()) {

				System.out.println("New window displayed" + winHandle);

				driver.switchTo().window(winHandle);

				System.out.println("new window selected");

			}



			elementPresent_Time(driver, getObject("AirCom_BookStep_SeatSelect_ConfirmSeat_Btn"), 600);



			if (elementPresent(driver,

					By.xpath("//*[not(contains(@class, 'occupied')) and starts-with(@class, 'available')]"))) {

				System.out.println("Seat Found");

				safeClick(driver,

						By.xpath("//*[not(contains(@class, 'occupied')) and starts-with(@class, 'available')]"));

			} else {

				System.out.println("Seat Selection Model Window Does Not Loaed");

				addLog("Seat Selection Model Window Does Not Loaed");

			}



			safeClick(driver, getObject("AirCom_BookStep_SeatSelect_ConfirmSeat_Btn"));



		}



		printInfo("Review itinerary loaded");

		safeClick(driver, getObject("air_review_itinerary_continue"));



	}



	/*

	 * public void SelectAddBaggageForRoundTripFlight(RemoteWebDriver driver) throws

	 * Exception, InterruptedException { boolean foundFirst = false; boolean

	 * foundSecond = false;

	 *

	 * for (int i = 1; i <= 50; i++) {

	 *

	 * if (elementNotVisible( driver, By.xpath("//div[1]/nav[2]/ul/li[" + i +

	 * "]/div/label/table/tbody/tr/th/span[@class='flightSprite vIndicator noBaggage']"

	 * ), 1)) { driver.findElementByXPath("//li[" + i +

	 * "]/div/label/table/tbody/tr/th/input").click();

	 * printInfo("Onward Flight With No Baggage Found"); foundFirst = true; break; }

	 * }

	 *

	 * for (int j = 1; j <= 50; j++) { if (elementNotVisible( driver,

	 * By.xpath("//div[2]/nav[2]/ul/li[" + j +

	 * "]/div/label/table/tbody/tr/th[6]/span[@class='flightSprite vIndicator noBaggage']"

	 * ), 1)) { driver.findElementByXPath("//div[2]/nav[2]/ul/li[" + j +

	 * "]/div/label/table/tbody/tr/th/input").click();

	 * printInfo("Return Flight With No Baggage Found"); foundSecond = true; break;

	 * }

	 *

	 * } if (!foundFirst || !foundSecond) {

	 * addLog("No flights without baggage found");

	 * assertTrue("No flights without baggage found", false); } }

	 */



	public boolean SelectAddBaggageFlightOnwardWithBGReturnWithoutBG(RemoteWebDriver driver, String OnwardbaggageType,

			String ReturnbaggageType) throws Exception, InterruptedException {

		boolean foundFirst = false;

		boolean foundSecond = false;



		for (int i = 1; i <= 50; i++) {



			if (OnwardbaggageType.equalsIgnoreCase("OnwardWithBG")) {

				if (!elementNotVisible(driver,

						By.xpath("//div[1]/nav/ul/li[" + i

								+ "]/div/label/table/tbody/tr/th/span[@class='flightSprite vIndicator noBaggage']"),

						1)) {

					driver.findElement(By.xpath("//li[" + i + "]/div/label/table/tbody/tr/th/input")).click();

					// driver.findElementByXPath("//li[" + i +

					// "]/div/label/table/tbody/tr/th/input").click();

					printInfo("Onward Flight With No Baggage Found");

					foundFirst = true;

					break;

				}

			} else {

				if (!elementNotVisible(driver,

						By.xpath("//div[1]/nav/ul/li[" + i

								+ "]/div/label/table/tbody/tr/th/span[@class='flightSprite vIndicator noBaggage']"),

						1)) {

					driver.findElement(By.xpath("//div[1]/nav/ul/li[" + i

							+ "]/div/label/table/tbody/tr/th/span[@class='flightSprite vIndicator noBaggage']"))

							.click();

					// driver.findElementByXPath("//li[" + i +

					// "]/div/label/table/tbody/tr/th/input").click();

					printInfo("Onward Flight With No Baggage Found");

					foundFirst = true;

					break;

				}



			}



		}



		for (int j = 1; j <= 50; j++) {

			// System.out.println("enters in return");

			if (ReturnbaggageType.equalsIgnoreCase("ReturnWithBG")) {

				System.out.println("status of element" + elementNotVisible(driver,

						By.xpath("//div[2]/nav/ul/li[" + j

								+ "]/div/label/table/tbody/tr/th[6]/span[@class='flightSprite vIndicator noBaggage']"),

						1));

				// System.out.println("class

				// name="+driver.findElement(By.xpath("//div[2]/nav[2]/ul/li["

				// + j

				// +

				// "]/div/label/table/tbody/tr/th[5][@class='duration']")).getText());

				System.out.println("value of j=" + j);

				if (elementNotVisible(driver,

						By.xpath("//div[2]/nav/ul/li[" + j

								+ "]/div/label/table/tbody/tr/th[6]/span[@class='flightSprite vIndicator noBaggage']"),

						1)) {

					List<WebElement> lay1Flights = driver.findElement(By.xpath("//div[@data-leg='2']/nav/ul"))

							.findElements(By.tagName("li"));



					System.out.println(lay1Flights.get(j - 1).getText());

					lay1Flights.get(j - 1).click();

					printInfo("Return Flight With No Baggage Found");

					foundSecond = true;

					break;

				}

			} else {

				if (!elementNotVisible(driver,

						By.xpath("//div[2]/nav/ul/li[" + j

								+ "]/div/label/table/tbody/tr/th[6]/span[@class='flightSprite vIndicator noBaggage']"),

						1)) {

					System.out.println("enters in element present");

					// driver.findElementByXPath("//div[2]/nav[2]/ul/li[" + j +

					// "]/div/label/table/tbody/tr/th/input").click();

					driver.findElement(By.xpath("//div[2]/nav/ul/li[" + j

							+ "]/div/label/table/tbody/tr/th[6]/span[@class='flightSprite vIndicator noBaggage']"))

							.click();

					printInfo("Return Flight With No Baggage Found");

					foundSecond = true;

					break;



				}

			}



		}

		if (!foundFirst || !foundSecond) {

			addLog("No flights without baggage found");

			// assertTrue("No flights without baggage found", false);

			return false;

		}

		return true;

	}



	public void ExpAir_ItineraryPage(RemoteWebDriver driver) throws InterruptedException, Exception {



		if (!elementVisible(driver, getObject("Expedia_air_Itinerary_ContinueButton"), 50)) {

			addLog("Itinerary Page has not loaded");

		}

		WebDriverWait wait = new WebDriverWait(driver, 15);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));



		Thread.sleep(5000);

		assertTrue("Not present continue btn on travellers page",

				elementPresent(driver, By.xpath("//div[@id='step_1']/form/div/button")));

		// safeClick(driver, getObject("Expedia_air_Itinerary_ContinueButton"));

		safeClick(driver, By.xpath("//div[@id='step_1']/form/div/button"));

		System.out.println("Review your selection");

		addLog("Review your selection");

	}



	public void FlightXpAir_ItineraryPage(RemoteWebDriver driver) throws InterruptedException, Exception {



		if (!elementVisible(driver, getObject("FlightXp_air_Itinerary_ContinueButton"), 50)) {

			addLog("Itinerary Page has not loaded");

		}

		WebDriverWait wait = new WebDriverWait(driver, 15);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));



		Thread.sleep(5000);

		assertTrue("Not present continue btn on travellers page",

				elementPresent(driver, By.xpath("//div[@id='step_1']/form/div/button")));

		// safeClick(driver,

		// getObject("FlightXp_air_Itinerary_ContinueButton"));

		safeClick(driver, By.xpath("//div[@id='step_1']/form/div/button"));

		System.out.println("Review your selection");

		addLog("Review your selection");

	}



	public void mobilweb_FlightXpAir_ItineraryPage(RemoteWebDriver driver) throws InterruptedException, Exception {



		// if(!elementVisible(driver,

		// getObject("FlightXp_air_Itinerary_ContinueButton"), 50)){

		if (!elementVisible(driver, By.xpath("//*[contains(text(),'Continue')]"), 50)) {

			addLog("Itinerary Page has not loaded");

		}

		WebDriverWait wait = new WebDriverWait(driver, 15);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Continue')]")));



		Thread.sleep(5000);

		// assertTrue("Not present continue btn on travellers page",

		// elementPresent(driver,

		// By.xpath("//div[@id='step_1']/form/div/button")));

		// safeClick(driver,

		// getObject("FlightXp_air_Itinerary_ContinueButton"));

		safeClick(driver, By.xpath("//*[contains(text(),'Continue')]"));

		System.out.println("Review your selection");

		addLog("Review your selection");

	}



	public void ExpAir_TravellersEmail(RemoteWebDriver driver) throws InterruptedException, Exception {

		Thread.sleep(10000);

		elementPresent_Time(driver, getObject("Expedia_air_traveller_name"), 100);

		if (!elementVisible(driver, getObject("Expedia_air_traveller_name"), 10)) {

			addLog("Itinerary Page has not loaded");

		}

		Thread.sleep(5000);

		safeType(driver, getObject("Expedia_air_traveller_name"), "anil.patil@cleartrip.com");

		// safeClick(driver, By.id("SEND_MARKETING_EMAIL_FLAG"));

		safeClick(driver, getObject("Expedia_air_step2_continue_btn"));

		System.out.println("Travellers Email ID Entered");

		addLog("Travellers Email ID Entered");



	}



	public void FlightXpAir_TravellerStep(RemoteWebDriver driver) throws InterruptedException, Exception {

		Thread.sleep(10000);

		elementPresent_Time(driver, getObject("FlightXp_air_traveller_name"), 30);

		if (!elementVisible(driver, getObject("FlightXp_air_traveller_name"), 10)) {

			addLog("Itinerary Page has not loaded");

		}

		Thread.sleep(5000);

		// safeType(driver, getObject("FlightXp_air_traveller_name"),

		// "prashanth.s@cleartrip.com");

		safeType(driver, getObject("Expedia_air_traveller_name"), dataFile.value("AirWLTravvelerEmailID"));

		safeClick(driver, getObject("FlightXp_air_step2_continue_btn"));

		System.out.println("Travellers Email ID Entered");

		addLog("Travellers Email ID Entered");



	}



	public void mobileWeb_FlightXpAir_TravellerStep(RemoteWebDriver driver) throws InterruptedException, Exception {

		Thread.sleep(10000);

		elementPresent_Time(driver, getObject("FlightXp_air_traveller_name"), 30);

		if (!elementVisible(driver, getObject("FlightXp_air_traveller_name"), 10)) {

			addLog("Itinerary Page has not loaded");

		}

		Thread.sleep(5000);

		Select options = new Select(driver.findElement(By.className("Title")));

		options.selectByVisibleText("Mr");

		safeType(driver, By.name("AdultFname1"), "test");

		safeType(driver, By.name("AdultLname1"), "test");

		safeType(driver, By.id("Phone"), "1234567890");

		safeType(driver, By.id("Email"), "test@gmail.com");

		safeClick(driver, By.xpath("//*[contains(text(),'Continue')]"));

		// safeType(driver, getObject("FlightXp_air_traveller_name"),

		// "prashanth.s@cleartrip.com");

		/*

		 * safeType(driver, getObject("Expedia_air_traveller_name"),

		 * dataFile.value("AirWLTravvelerEmailID")); safeClick(driver,

		 * getObject("FlightXp_air_step2_continue_btn"));

		 * System.out.println("Travellers Email ID Entered");

		 * addLog("Travellers Email ID Entered");

		 */



	}



	public void AmexAir_TravellersEmail(RemoteWebDriver driver) throws InterruptedException, Exception {

		Thread.sleep(10000);

		elementPresent_Time(driver, getObject("Expedia_air_traveller_name"), 100);

		if (!elementVisible(driver, getObject("Expedia_air_traveller_name"), 10)) {

			addLog("Itinerary Page has not loaded");

		}

		Thread.sleep(5000);

		// safeType(driver, getObject("Expedia_air_traveller_name"),

		// "prashanth.s@cleartrip.com");

		safeType(driver, getObject("Expedia_air_traveller_name"), dataFile.value("AirWLTravvelerEmailID"));

		// safeClick(driver, By.id("SEND_MARKETING_EMAIL_FLAG"));

		safeClick(driver, getObject("Amex_air_step2_continue_btn"));

		// safeClick(driver,By.xpath("//button[@type='submit']"));

		System.out.println("Travellers Email ID Entered");

		addLog("Travellers Email ID Entered");



	}



	public void applyCoupon(RemoteWebDriver driver, String coupon) throws Exception {

		safeClick(driver, By.xpath(".//*[@id='coupon']"));

		// safeType(driver,By.xpath(".//*[@id='coupon']"),"TOTAL");



		if (coupon.equalsIgnoreCase("domow")) {

			/* safeType(driver, By.id("coupon"), "SHACHI"); */

			safeType(driver, By.id("coupon"), "DOMOW");

		} else if (coupon.equalsIgnoreCase("domrt")) {

			safeType(driver, By.id("coupon"), "MOBILE321");

		} else if (coupon.equalsIgnoreCase("owfull")) {

			safeType(driver, By.xpath(".//*[@id='coupon']"), "TOTALFARE");

		} else if (coupon.equalsIgnoreCase("rtfull")) {

			safeType(driver, By.xpath(".//*[@id='coupon']"), "AUTORT");

		}

		safeClick(driver, By.id("apply_coupon"));

		elementPresent_Time(driver, By.xpath("//span[@id='finalCost']"), 10);

		String text = getText(driver, By.xpath("//span[@id='finalCost']"));

		String text1 = getText(driver, By.xpath(".//*[@id='formTotal']"));

		Assert.assertEquals(text, text1, "Mismatch in Total Price and price showned after applying coupon");

		addLog(text, true);



	}



	public boolean ExpAir_Paymentpage(RemoteWebDriver driver, String paymentMethod)

			throws InterruptedException, Exception {



		if (elementPresent_Time(driver, By.id("paymentSubmit"), 100)) {

			textPresent(driver, "Use a coupon code", 5);

			if (paymentMethod.equals("CC") || paymentMethod.equals("AMEX")) {

				safeClick(driver, By.linkText("Pay by Credit Card"));

				Thread.sleep(3000);

				if (MakePaymentTrue && !ProductionUrl) {

					if (elementPresent_Time(driver, By.id("creditCardNumber"), 5)) {

						safeType(driver, By.id("creditCardNumber"), "");

						if (paymentMethod.equals("CC")) {

							safeType(driver, By.id("creditCardNumber"), dataFile.value("CCNumber"));

							safeSelect(driver, By.id("CcExpirationMonth"), "5");

							safeSelect(driver, By.id("CcExpirationYear"), dataFile.value("CCYear"));

							safeType(driver, By.id("cvvCode"), "123");

						} else if (paymentMethod.equals("AMEX")) {

							safeType(driver, By.id("creditCardNumber"), dataFile.value("AmexCard_Number"));

							safeType(driver, By.id("creditCardNumber"), dataFile.value("AmexCard_Number"));

							safeSelect(driver, By.xpath("//select[@id='CcExpirationMonth']"),

									dataFile.value("AmexCard_Exp_Month"));

							safeSelect(driver, By.id("CcExpirationYear"), dataFile.value("AmexCard_Exp_Year"));

							safeType(driver, By.id("cvvCode"), dataFile.value("AmexCVV"));

						}

						safeType(driver, By.id("BillName"), "test");



						safeType(driver, By.id("billFirstName"), "test");

						safeType(driver, By.id("billLastName"), "test");

						safeType(driver, By.id("billLastName"), "test");

						safeType(driver, By.id("billAddress"), "test");

						safeType(driver, By.id("billCity"), "test");

						safeType(driver, By.id("billPin"), "test");

						safeAutocomplete(driver, By.id("billCountry"),

								getObject("ExpediaAir_PaymentPage_Country_auto_container_list_New"), "India");



						/*

						 * safeAutocomplete(driver, getObject("AirCorpCom_PaymentPage_Bill_Country"),

						 * By.xpath ("//*[@id='autocompleteOptionsContainer']/li[2]"),

						 * dataFile.value("Corp_Country"));

						 */

						if (MakePaymentTrue && !ProductionUrl) {

							safeClick(driver, By.id("paymentSubmit"));

							addLog("Make Payment button on payment page clicked.", true);



							Thread.sleep(15000);

							if (elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong)) {

								safeClick(driver, getObject("air_amex_payment_button"));

								addLog("Interstital Page.", true);

								Thread.sleep(10000);



							}

							return true;

						}



					}

				}

			} else if (paymentMethod.equalsIgnoreCase("NB")) {

				safeClick(driver, By.linkText("Pay with Net Banking"));

				Thread.sleep(2000);



				isTextPresent(driver, "Popular banks");

				safeSelect(driver, getObject("AirCom_BookStep4_Netbank_DropDown"), "Bank of India");

				Thread.sleep(1000);

				safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

				addLog("Make Payment button on payment page clicked.", true);



				Thread.sleep(10000);

				waitElement(driver, getObject("AirCom_Payment_BankSite"), 50);



				if (getURL(driver).contains("bankofindia")) {

					AssertJUnit.assertEquals("Bank of India Online e Payment", driver.getTitle());

					addLog("Net Banking Site Reached", true);

				} else {

					refreshPage(driver);

					waitElement(driver, getObject("AirCom_Payment_BankSite"), 20);

					AssertJUnit.assertEquals("Bank of India Online e Payment", driver.getTitle());

					addLog("Net Banking Site Reached", true);

				}



				try {

					driver.findElement(By.linkText("Return to Biller Site")).click();

				} catch (Exception e) {

					driver.findElement(By.xpath("//input[@value='Click to RETURN']")).click();

				}

				Thread.sleep(2000);

				elementPresent_Time(driver, By.cssSelector("h1"), 60);

				textPresent(driver, "Oops, your payment failed", 10);



				assertTrue("Expedia NB retry did not work",

						driver.findElement(By.cssSelector("h1")).getText().contains("Oops, your payment failed"));

				addLog("Successfully arrived on Payment retry page from NB", true);



				return true;



			} else {



				addLog("Wrong payment method passed.", true);

				assertTrue("Failure!", false);

			}

		}

		return false;

	}



	public boolean AmexAir_Paymentpage(RemoteWebDriver driver, String paymentMethod)

			throws InterruptedException, Exception {



		if (elementPresent_Time(driver, By.id("paymentSubmit"), 100)) {

			textPresent(driver, "Use a coupon code", 5);

			if (paymentMethod.equals("CC")) {

				safeClick(driver, By.linkText("Pay by Credit Card"));

				Thread.sleep(3000);

				if (MakePaymentTrue && !ProductionUrl) {

					if (elementPresent_Time(driver, By.id("creditCardNumber"), 5)) {

						safeType(driver, By.id("creditCardNumber"), "");

						safeType(driver, By.id("creditCardNumber"), dataFile.value("CCNumber"));

						safeSelect(driver, By.id("CcExpirationMonth"), "5");

						safeSelect(driver, By.id("CcExpirationYear"), dataFile.value("CCYear"));

						safeType(driver, By.id("cvvCode"), "123");

						safeType(driver, By.id("BillName"), "test");



						safeType(driver, By.id("billFirstName"), "test");

						safeType(driver, By.id("billLastName"), "test");

						safeType(driver, By.id("billLastName"), "test");

						safeType(driver, By.id("billAddress"), "test");

						safeType(driver, By.id("billCity"), "test");

						safeType(driver, By.id("billPin"), "test");

						safeAutocomplete(driver, By.id("billCountry"),

								getObject("ExpediaAir_PaymentPage_Country_auto_container_list_New"), "India");



						/*

						 * safeAutocomplete(driver, getObject("AirCorpCom_PaymentPage_Bill_Country"),

						 * By.xpath ("//*[@id='autocompleteOptionsContainer']/li[2]"),

						 * dataFile.value("Corp_Country"));

						 */

						if (MakePaymentTrue && !ProductionUrl) {

							safeClick(driver, By.id("paymentSubmit"));

							addLog("Make Payment button on payment page clicked.", true);

							return true;

						}



					}

				}

			} else if (paymentMethod.equalsIgnoreCase("NB")) {

				safeClick(driver, By.linkText("Pay with Net Banking"));

				Thread.sleep(2000);



				isTextPresent(driver, "Popular banks");

				safeSelect(driver, getObject("AirCom_BookStep4_Netbank_DropDown"), "Bank of India");

				Thread.sleep(1000);

				safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));



				Thread.sleep(10000);

				waitElement(driver, getObject("AirCom_Payment_BankSite"), 50);



				if (getURL(driver).contains("bankofindia")) {

					AssertJUnit.assertEquals("Bank of India Online e Payment", driver.getTitle());

					addLog("Net Banking Site Reached", true);

				} else {

					refreshPage(driver);

					waitElement(driver, getObject("AirCom_Payment_BankSite"), 20);

					AssertJUnit.assertEquals("Bank of India Online e Payment", driver.getTitle());

					addLog("Net Banking Site Reached", true);

				}



				driver.findElement(By.linkText("Return to Biller Site")).click();

				Thread.sleep(2000);

				elementPresent_Time(driver, By.cssSelector("h1"), 60);

				textPresent(driver, "Oops, your payment failed", 10);



				assertTrue("Expedia NB retry did not work",

						driver.findElement(By.cssSelector("h1")).getText().contains("Oops, your payment failed"));

				addLog("Successfully arrived on Payment retry page from NB", true);



				return true;



			} else {



				addLog("Wrong payment method passed.", true);

				assertTrue("Failure!", false);

			}

		}

		return false;

	}



	public boolean ExpAir_Confirmation(RemoteWebDriver driver) throws InterruptedException, Exception {

		if (MakePaymentTrue && !ProductionUrl) {

			Thread.sleep(20000);

			textPresent(driver, "Great, your booking went through successfully", 100);

			String confirmationMessage = driver.findElement(getObject("ExpediaAir_ConfirmationPage_Success")).getText();

			if (confirmationMessage.contains("Great, your booking went through successfully.")) {

				addLog("Booking passed successfully.", true);

				String tripId = getText(driver, By.cssSelector("strong"));

				addLog("Trip Id booked is " + tripId, true);

				Thread.sleep(5000);

				return true;

			} else if (textPresent(driver, "Proxy Error", 1)) {

				addLog("Proxy Error displayed, hence no TripID collected.", true);

				return true;



			} else {

				addLog("Booking not confirmed.", true);

				assertTrue("Failure!", false);

			}

		}

		return false;

	}



	public boolean FltXpAir_Confirmation(RemoteWebDriver driver) throws InterruptedException, Exception {



		Thread.sleep(15000);

		{

			textPresent(driver, "Great, your booking went through successfully", 100);

			String confirmationMessage = driver.findElement(getObject("ExpediaAir_ConfirmationPage_Success")).getText();

			if (confirmationMessage.contains("Great, your booking went through successfully.")) {

				addLog("Booking passed successfully.");

				printInfo("Booking passed successfully.");

				String tripId = getText(driver, By.cssSelector("strong"));

				addLog("Trip Id booked is " + tripId);

				System.out.println("Trip Id booked is " + tripId);

				;

				printInfo("Trip Id booked is " + tripId);

				Thread.sleep(5000);

				return true;

			} else if (textPresent(driver, "Proxy Error", 1)) {

				addLog("Proxy Error displayed, hence no TripID collected.");

				printInfo("Proxy Error displayed, hence no TripID collected.");

				return true;



			} else {

				addLog("Booking not confirmed.");

				printInfo("Booking not confirmed.");

				assertTrue("Failure!", false);

			}

		}

		return false;

	}



	public boolean mobileweb_FltXpAir_Confirmation(RemoteWebDriver driver) throws InterruptedException, Exception {



		Thread.sleep(15000);

		{

			// textPresent(driver,

			// "Great, your booking went through successfully", 100);

			String confirmationMessage = driver.findElement(By.xpath("//*[contains(text(),'Congratulations')]"))

					.getText();

			System.out.println("----------------" + confirmationMessage);

			if (confirmationMessage.contains("Congratulations, your booking is confirmed")) {

				addLog("Booking passed successfully.");

				printInfo("Booking passed successfully.");

				JavascriptExecutor js1 = driver;

				WebElement element1 = driver.findElement(By.xpath("//*[text()='Trip ID: ']"));

				js1.executeScript("arguments[0].scrollIntoView(true);", element1);

				String tripId = getText(driver, By.xpath("//*[text()='Trip ID: ']/strong"));

				addLog("Trip Id booked is " + tripId, true);

				System.out.println("Trip Id booked is " + tripId);

				;

				printInfo("Trip Id booked is " + tripId);

				Thread.sleep(5000);

				return true;

			} else if (textPresent(driver, "Proxy Error", 1)) {

				addLog("Proxy Error displayed, hence no TripID collected.");

				printInfo("Proxy Error displayed, hence no TripID collected.");

				return true;



			} else {

				addLog("Booking not confirmed.");

				printInfo("Booking not confirmed.");

				assertTrue("Failure!", false);

			}

		}

		return false;

	}



	public boolean amexAir_Confirmation(RemoteWebDriver driver) throws InterruptedException, Exception {

		if (MakePaymentTrue && !ProductionUrl) {

			Thread.sleep(15000);

			textPresent(driver, "Great, your booking went through successfully", 100);

			String confirmationMessage = driver.findElement(getObject("AmexAir_ConfirmationPage_Success")).getText();

			if (confirmationMessage.contains("Great, your booking went through successfully.")) {

				addLog("Booking passed successfully.");

				printInfo("Booking passed successfully.");

				String tripId = getText(driver, By.cssSelector("strong"));

				addLog("Trip Id booked is " + tripId);

				System.out.println("Trip Id booked is " + tripId);

				;

				printInfo("Trip Id booked is " + tripId);

				Thread.sleep(5000);

				return true;

			} else if (textPresent(driver, "Proxy Error", 1)) {

				addLog("Proxy Error displayed, hence no TripID collected.");

				printInfo("Proxy Error displayed, hence no TripID collected.");

				return true;



			} else {

				addLog("Booking not confirmed.");

				printInfo("Booking not confirmed.");

				assertTrue("Failure!", false);

			}

		}

		return false;

	}



	public void onlineAmendmentProcessStartAccRND(RemoteWebDriver driver) throws Exception {

		safeClick(driver, getObject("Acc_Trip_Details_OnlineAmendment_Link"));

		if (waitElement(driver, getObject("Acc_Air_Online_amend_GetStarted_Button"), 8)) {

			safeClick(driver, getObject("Acc_Air_Online_amend_GetStarted_Button"));

			if (waitElement(driver, getObject("Acc_Air_Online_amend_Onward_Calendar_CheckBox"), 8)) {

				safeClick(driver, getObject("Acc_Air_Online_amend_Onward_Calendar_CheckBox"));

				System.out.println(driver.getCurrentUrl());

				safeClick(driver, By.xpath("(//td[@data-handler='selectDay'])[9]"));

				safeClick(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SearchAlternaateFlights"));

			} else {

				addLog("Amendement travel dates page not loaded!");

				System.out.println("Amendement travel dates page not loaded!");

				assertTrue("Failure!", false);

			}

		} else if (waitElement(driver, By.id("tripCancelError"), 1)) {

			addLog("Error on clicking Online Amendment: "

					+ getText(driver, By.xpath("//*[@id='tripCancelError']/h3")));

			System.out.println("Error on clicking Online Amendment: "

					+ getText(driver, By.xpath("//*[@id='tripCancelError']/h3")));

			assertTrue("Failure!", false);

		} else {

			addLog("Amendement intro page not loaded!");

			System.out.println("Amendement intro page not loaded!");

			assertTrue("Failure!", false);

		}

	}



	public void onlineAmendmentSRPAccSplRND(RemoteWebDriver driver, String flightName) throws Exception {

		Thread.sleep(5000);



		driver.switchTo().frame("ctResults");

		if (waitElement(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SRP_FlightList"), 15)) {

			List<WebElement> flights = driver.findElements(getObject("AirCom_HQ_Air_OnlineAmendment_SRP_FlightList"));

			if (flights.size() == 1) {

				System.out.println("Flight option contains only - " + flights.get(0).getText());

				addLog("Flight option contains only - " + flights.get(0).getText());

				/*

				 * if (flights.get(0).getText().equals(flightName)) {

				 * flights.get(0).findElement(By.xpath("./input")).click(); }

				 */

			} else {

				addLog("Flight option contains more than one option for special round trip partial amendment!");

				System.out.println(

						"Flight option contains more than one option for special round trip partial amendment!");

				assertTrue("Failure!", false);

			}

			checkWarning(driver);

			if (waitElement(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SRP_BookButtonOW"), 5)) {

				safeClick(driver, getObject("AirCom_HQ_Air_OnlineAmendment_SRP_BookButtonOW"));

			} else {

				addLog("Book button not available. Error!");

				System.out.println("Book button not available. Error!");

				assertTrue("Failure!", false);

			}



		} else {

			addLog("Flight options not loading. Error!");

			System.out.println("Flight options not loading. Error!");

			assertTrue("Failure!", false);

		}

	}



	public void onlineAmendmentPaymentProcess(RemoteWebDriver driver) throws Exception {

		if (waitElement(driver, getObject("AirCom_HQ_Air_OnlineAmendment_PayTPNo"), 10)) {

			safeType(driver, getObject("AirCom_HQ_Air_OnlineAmendment_PayTPNo"), "11111");

		} else if (waitElement(driver, getObject("AirCom_HQ_Air_OnlineAmendment_PayTPTab"), 1)) {

			safeClick(driver, getObject("AirCom_HQ_Air_OnlineAmendment_PayTPTab"));

			safeType(driver, getObject("AirCom_HQ_Air_OnlineAmendment_PayTPNo"), "11111");

		} else {

			addLog("Online Amendment Payment page not loaded.");

			System.out.println("Online Amendment Payment page not loaded.");

			assertTrue("Failure!", false);

		}

		safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

	}



	public boolean selectStopOverSolution(RemoteWebDriver driver) throws Exception {



		int solnCount = driver.findElements(By.xpath("//form[@id='flightForm']/section[2]/div[4]/div/nav/ul/li"))

				.size();

		System.out.println("solnSize" + solnCount);



		for (int i = 1; i <= solnCount; i++) {

			if (i == 6) {

				continue;

			}

			// Air SRP Solutions Tabs

			safeClick(driver, By.xpath("//form[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[" + i + "]"));



			// check flight details displayed

			if (elementVisible(driver, By.xpath("//form[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[" + i

					+ "]/div/ul/li/div/ul/li/div[2]/small"), 20)) {



				// Capture flight numbers

				String f1 = getText(driver, By.xpath("//form[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[" + i

						+ "]/div/ul/li/div/ul/li/div[2]/small"));

				String f2 = getText(driver, By.xpath("//form[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[" + i

						+ "]/div/ul/li/div/ul[2]/li/div[2]/small"));



				System.out.println(f1 + "....." + f2);

				if (f1.equalsIgnoreCase(f2)) {

					safeClick(driver, By.xpath("//ul/li[" + i + "]/table/tbody/tr[2]/td[3]/button"));

					return true;



				}



			} else {

				addLog("No flights");

				assertTrue("No flights", false);

			}



		}

		return false;



	}



	public void addCardInExpressway(RemoteWebDriver driver) throws Exception {

		safeClick(driver, getObject("Acc_Expressway_Button"));

		if (waitElement(driver, getObject("Acc_Expressway_AddCard"), 3)) {

			safeType(driver, getObject("Acc_Expressway_CardNo"), dataFile.value("CCNumber"));

			safeType(driver, getObject("Acc_Expressway_NameOnCard"), dataFile.value("CCName"));

			safeSelect(driver, getObject("Acc_Expressway_CardExpirationMonth"), "5");

			safeSelect(driver, getObject("Acc_Expressway_CardExpirationYear"), dataFile.value("CCYear"));

			safeClick(driver, getObject("Acc_Expressway_AddCard"));

		} else {

			System.out.println("Card cant be added. Error!");

			addLog("Card cant be added. Error!");

			assertTrue(false);

		}



		if (waitElement(driver, getObject("Acc_Expressway_Final"), 3)) {

			safeSelect(driver, getObject("Acc_Expressway_Title"), "Mr");

			safeType(driver, getObject("Acc_Expressway_FName"), "Test");

			safeType(driver, getObject("Acc_Expressway_LName"), "Test");

			safeType(driver, getObject("Acc_Expressway_PhoneNo"), "1234567890");

			safeClick(driver, getObject("Acc_Expressway_Final"));

		}

	}



	public void testEditProfileChanges(RemoteWebDriver driver) throws Exception {

		safeClick(driver, getObject("Acc_Profile_EditButton"));

		Thread.sleep(100);



		String name;

		if (driver.findElement(getObject("Acc_Profile_FName")).getAttribute("value").equals("Test")) {

			name = "TestEdit";

		} else {

			name = "Test";

		}



		safeType(driver, getObject("Acc_Profile_FName"), name);

		safeType(driver, getObject("Acc_Profile_LName"), name);

		driver.findElement(By.id("country_code")).sendKeys("India +91");

		Thread.sleep(2000);

		safeClick(driver, getObject("Acc_Profile_SaveChanges"));

		Thread.sleep(5000);



		if (waitElement(driver, getObject("Acc_Profile_FormSuccessMessage"), 10)) {

			assertTrue("Profile saved successfully", driver.findElement(getObject("Acc_Profile_FormSuccessMessage"))

					.getText().contains("Great, your changes have been saved."));

		} else {

			assertTrue("Profile changes not reflecting. Failure!",

					driver.findElement(getObject("Acc_Profile_Name")).getText().equals("Mr. TestEdit TestEdit"));

		}

	}



	public void testDiscaardProfileChanges(RemoteWebDriver driver) throws Exception {

		safeClick(driver, getObject("Acc_Profile_EditButton"));

		Thread.sleep(100);



		safeType(driver, getObject("Acc_Profile_FName"), "Test");

		safeType(driver, getObject("Acc_Profile_LName"), "Test");

		safeClick(driver, getObject("Acc_Profile_DiscardButton"));

		Thread.sleep(5000);



		waitElement(driver, getObject("Acc_Profile_EditButton"), 3);

		assertTrue("Profile changes not reflecting. Failure!",

				driver.findElement(getObject("Acc_Profile_Name")).getText().equals("Mr. TestEdit TestEdit"));

	}



	public void revertProfilechanges(RemoteWebDriver driver) throws Exception {

		safeClick(driver, getObject("Acc_Profile_EditButton"));

		Thread.sleep(100);



		safeType(driver, getObject("Acc_Profile_FName"), "Test");

		safeType(driver, getObject("Acc_Profile_LName"), "Test");

		Thread.sleep(1000);

		safeClick(driver, getObject("Acc_Profile_SaveChanges"));

		Thread.sleep(5000);



		waitElement(driver, getObject("Acc_Profile_FormSuccessMessage"), 3);

		assertTrue("Profile save success msg wrong. Failure!",

				driver.findElement(getObject("Acc_Profile_FormSuccessMessage")).getText()

						.equals("Great, your changes have been saved.\n×"));

		assertTrue("Profile changes not reflecting. Failure!",

				driver.findElement(getObject("Acc_Profile_Name")).getText().equals("Mr. Test Test"));

	}



	public void b2cBusSearch(RemoteWebDriver driver, String from, String to) throws Exception {

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), from);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				to);

		selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

				getObject("AirCom_HomePage_Departure_NextMonth"), 0, "28");

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airComHomepageSearchPackage(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,

			String To_Date, String rooms, String Adults, String Childrens, int attempt) throws Exception {

		Thread.sleep(2000);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);

		if (attempt == 0) {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 3, From_Date);

		} else {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 0, From_Date);

		}

		selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Return_Cal")),

				By.id(getValue("AirCom_HomePage_Departure_NextMonth")), 0, To_Date);

		safeSelect(driver, getObject("HomepagePackageRooms"), rooms);

		safeSelect(driver, getObject("HomepagePackageAdults"), Adults);

		safeSelect(driver, getObject("HomepagePackageChildren"), Childrens);

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void airComHomepageSearchPackageFlexiDates(RemoteWebDriver driver, String FromCity, String ToCity,

			String From_Date, String To_Date, String rooms, String Adults, String Childrens, int attempt)

			throws Exception {

		Thread.sleep(2000);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_From_TextBox")),

				getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id(getValue("AirCom_HomePage_To_TextBox")), getObject("AirCom_HomePage_To_Ajax"),

				ToCity);

		if (attempt == 0) {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 3, From_Date);

		} else {

			selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Departure_Cal")),

					getObject("AirCom_HomePage_Departure_NextMonth"), 0, From_Date);

		}

		selectCalendarDate(driver, By.id(getValue("AirCom_HomePage_Return_Cal")),

				By.id(getValue("AirCom_HomePage_Departure_NextMonth")), 0, To_Date);

		safeClick(driver, By.id("package_flexi_dates"));

		Thread.sleep(1000);

		// selectCalendarDate(driver,

		// By.id(getValue("HomePagePackageCheckinCal")),

		// getObject("AirCom_HomePage_Departure_NextMonth"), 0, "10");

		selectCalendarDate(driver, By.xpath("//input[@id='Checkingate']"),

				getObject("AirCom_HomePage_Departure_NextMonth"), 0, "10");

		selectCalendarDate(driver, By.xpath("//input[@id='CheckOutDate']"),

				getObject("AirCom_HomePage_Departure_NextMonth"), 0, "11");

		safeSelect(driver, getObject("HomepagePackageRooms"), rooms);

		safeSelect(driver, getObject("HomepagePackageAdults"), Adults);

		safeSelect(driver, getObject("HomepagePackageChildren"), Childrens);

		safeClick(driver, getObject("AirCom_HomePage_Search_Button"));

	}



	public void packageTravellerDetails(RemoteWebDriver driver, String adult, String children, boolean international)

			throws Exception {



		String adults = adult;

		num_adults = Integer.parseInt(adults);

		String childrens = children;

		num_children = Integer.parseInt(childrens);

		int num_of_adults = num_adults;

		int num_of_children = num_children;

		boolean loginSection = driver.getCurrentUrl().contains("login");

		if (loginSection) {

			printInfo("Login Required");

			safeType(driver, getObject("step2_email_address_username"), dataFile.value("AirUserIdForHQScripts"));

			safeClick(driver, getObject("step2_password_checkbox"));

			Thread.sleep(500);

			safeType(driver, getObject("step2_email_password"), dataFile.value("AirPasswordForHQScripts"));

			safeClick(driver, getObject("step2_login_button"));

			Thread.sleep(5000);

		}



		Thread.sleep(5000);

		boolean GDS = b2cGDSAirlines(driver,

				By.xpath("//div[@id='itineraryDone']/div/div/nav/ul/li[3]/nav/ul[1]/li[2]/small"));

		elementVisible(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + "1"), 10);

		printInfo("Traveller details Step reached");

		addTravellerDetailsWithoutInfants(driver, GDS, num_of_adults, num_of_children, international);

		fillTravellersContact(driver);

		safeClick(driver, getObject("air_traveller_details_continue"));

	}



	public void addTravellerDetailsWithoutInfants(RemoteWebDriver driver, boolean GDS, int num_of_adults,

			int num_of_children, boolean international) throws Exception {

		String month = "Jan";

		if (GDS) {

			addLog("Pax entry for GDS Flight Combination");

			if (num_of_adults > 0) {

				for (int i = 1; i <= num_of_adults; i++) {

					boolean adult_name_details = elementVisible(driver,

							By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i), 5);

					if (adult_name_details) {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i),

								dataFile.value("atitle"));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultFirstName") + i),

								dataFile.value("afname" + i));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultLastName") + i),

								dataFile.value("alname" + i));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

								1)) {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

									dataFile.value("adobday"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBMonth") + i + ""),

									dataFile.value("adobmonth"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBYear") + i + ""),

									dataFile.value("adobyear"));

						}

						if (elementPresent_Time(driver, By.xpath("//div[" + i + "]/div/div/dl[2]/dl/dd/input[1]"), 1)) {

							safeAutocomplete(driver, getObject("AirCom_bookstep_travelles_nationality"),

									getObject("AirCom_BookStep4_Amex_Billing_Autocomplete"), "india");

						}



						// international sector from here

						if (international) {

							boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

									1);

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i), 1))

								safeType(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i),

										dataFile.value("apassport"));

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Country") + i), 1))

								autoCompletePassportIssuing(driver, dataFile.value("appissuingcountry"),

										By.id(getValue("AirCom_BookStep3_Adult_Passport_Country") + i), "2");

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

									1)) {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

										dataFile.value("appday"));

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_month") + i),

										dataFile.value("appmonth"));

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_year") + i),

										dataFile.value("appyear"));

							}

							if (visa)

								try {

									smartSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e1) {

									addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

								}

						}

					} else {

						addLog("Infant " + i + " is not displayed");

					}

				}

			}

			if (num_of_children > 0) {



				for (int i = 1; i <= num_of_children; i++) {

					boolean child_name_details = elementVisible(driver,

							By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i), 1);



					if (child_name_details) {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i),

								dataFile.value("ctitle"));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildFirstName") + i),

								dataFile.value("cfname" + i));

						safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildLastName") + i),

								dataFile.value("clname" + i));



						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBDay") + i),

								dataFile.value("cdobday"));

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBMonth") + i), month);

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBYear") + i), putYear(-3));

						;



						// international sector here

						if (international) {

							boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

									1);

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i), 2))

								safeType(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i),

										dataFile.value("cpassport"));

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Country") + i), 2))

								autoCompletePassportIssuing(driver, dataFile.value("cppissuingcountry"),

										By.id(getValue("AirCom_BookStep3_Child_Passport_Country") + i), "3");

							if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

									1)) {

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

										dataFile.value("cppday"));

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_month") + i),

										dataFile.value("cppmonth"));

								safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_year") + i),

										dataFile.value("cppyear"));

							}

							if (visa)

								try {

									smartSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

											dataFile.value("visatype"));

								} catch (Exception e1) {

									addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

								}

						}

					} else {

						addLog("Child " + i + " is not displayed");

					}

				}

			}

		} else {



			addLog("Pax entry for LCC Flight Combination");

			for (int i = 1; i <= num_of_adults; i++) {

				boolean adult_name_details = elementVisible(driver,

						By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i), 5);

				if (adult_name_details) {

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultTitle") + i),

							dataFile.value("atitle"));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultFirstName") + i),

							dataFile.value("First_Name_A" + i));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultLastName") + i),

							dataFile.value("Last_Name_A" + i));

					if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""), 1)) {

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBDay") + i + ""),

								dataFile.value("adobday"));

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBMonth") + i + ""),

								dataFile.value("adobmonth"));

						safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_AdultDOBYear") + i + ""),

								dataFile.value("adobyear"));

					}



					// international sector from here

					if (international) {

						boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i), 1);

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i), 1))

							safeType(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport") + i),

									dataFile.value("apassport"));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Country") + i), 1))

							autoCompletePassportIssuing(driver, dataFile.value("appissuingcountry"),

									By.id(getValue("AirCom_BookStep3_Adult_Passport_Country") + i), "2");

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i), 1)) {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_day") + i),

									dataFile.value("appday"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_month") + i),

									dataFile.value("appmonth"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Passport_Expiry_year") + i),

									dataFile.value("appyear"));

						}

						if (visa)

							try {

								smartSelect(driver, By.id(getValue("AirCom_BookStep3_Adult_Visa_type") + i),

										dataFile.value("visatype"));

							} catch (Exception e1) {

								addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

							}

					}

				} else {

					addLog("Infant " + i + " is not displayed");

				}

			}

			for (int i = 1; i <= num_of_children; i++) {

				boolean child_name_details = elementVisible(driver,

						By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i), 1);



				if (child_name_details) {

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildTitle") + i),

							dataFile.value("ctitle"));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildFirstName") + i),

							dataFile.value("First_Name_C" + i));

					safeType(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildLastName") + i),

							dataFile.value("Last_Name_C" + i));



					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBDay") + i),

							dataFile.value("cdobday"));

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBMonth") + i), month);

					safeSelect(driver, By.id(getValue("AirCom_BookStep3_Traveller_ChildDOBYear") + i), putYear(-3));

					;



					// international sector here

					if (international) {

						boolean visa = waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i), 1);

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i), 2))

							safeType(driver, By.id(getValue("AirCom_BookStep3_Child_Passport") + i),

									dataFile.value("cpassport"));

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Country") + i), 1))

							autoCompletePassportIssuing(driver, dataFile.value("cppissuingcountry"),

									By.id(getValue("AirCom_BookStep3_Child_Passport_Country") + i), "3");

						if (waitElement(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i), 1)) {

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_day") + i),

									dataFile.value("cppday"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_month") + i),

									dataFile.value("cppmonth"));

							safeSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Passport_Expiry_year") + i),

									dataFile.value("cppyear"));

						}

						if (visa)

							try {

								smartSelect(driver, By.id(getValue("AirCom_BookStep3_Child_Visa_type") + i),

										dataFile.value("visatype"));

							} catch (Exception e1) {

								addLog("IMP NOTE: Visa Type Drop Down NOT Available!!!", true);

							}

					}

				} else {

					addLog("Infant " + i + " is not displayed");

				}

			}



		}

	}



	/*

	 * public String api_url() { if ( common.value("host").contains("qa1") ) {

	 * String url = "http://apiqa.cleartrip.com"; return url; } else if (

	 * common.value("host").contains("stg1") ) { String url =

	 * "http://api.staging.cleartrip.com"; return url; } else if (

	 * common.value("host").contains("qa2") ) { String url =

	 * "http://apiqa.cleartrip.com"; return url; } else

	 *

	 * if ( common.value("host").contains("beta") ) { String url =

	 * "http://api.cleartrip.com"; return url; } if (

	 * common.value("host").contains("debug") ) { String url =

	 * "http://apistaging.cleartrip.com"; return url; }else if (

	 * common.value("host").contains("www")||common.value("host").contains("dr") ) {

	 * String url = "http://api.cleartrip.com"; return url; } return null; }

	 */



	public void way2GoSearch(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date)

			throws Exception {

		Thread.sleep(5000);

		safeAutocomplete(driver, By.id("FromTags"), getObject("AirCom_HomePage_From_Ajax"), FromCity);

		safeAutocomplete(driver, By.id("ToTags"), getObject("AirCom_HomePage_To_Ajax"), ToCity);

		selectCalendarDate(driver, getObject("Way2GoDatePicker"), getObject("AirCom_HomePage_Departure_NextMonth"), 0,

				From_Date);

		safeClick(driver, By.id("SearchHotelsButton"));

	}



	public String api_url() {



		if (common.value("host").contains("qa2")) {

			String url = "https://qa2.cleartrip.com";

			return url;

		} else



		if (common.value("host").contains("beta")) {

			String url = "http://beta.cleartrip.com";

			return url;

		}

		if (common.value("host").contains("debug")) {

			String url = "http://apistaging.cleartrip.com";

			return url;

		} else if (common.value("host").contains("www") || common.value("host").contains("dr")) {

			String url = "http://www.cleartrip.com";

			return url;

		}

		return null;

	}



	public void finalTap(RemoteWebDriver driver, By by) {

		WebElement Image = driver.findElement(by);

		Point point = Image.getLocation();

		int xcord = point.getX();

		int ycord = point.getY();

		System.out.println("x coordinate=" + xcord + "y coordinate=" + ycord);

		Actions builder = new Actions(driver);

		builder.moveToElement(driver.findElement(by), xcord, ycord).click().build().perform();

	}



	public boolean checkBookingStatus2(RemoteWebDriver driver) throws Exception {

		boolean checkStatus = false;



		Thread.sleep(30000);

		if (elementPresent_Time(driver, By.xpath("//h1"), 30)) {

			String confirmationMessage = getText(driver, By.xpath("//h1"));

			System.out.println("confirmationMessage" + confirmationMessage);



			assertTrue("Payment retry did not appear", confirmationMessage.equals("Oops, your payment didnt work"));

			checkStatus = true;

		}



		if (checkStatus) {

			return true;

		} else {

			printInfo("Booking failed for some reason during payment, Making another attempt!");

			addLog("Booking failed for some reason during payment, Making another attempt!");

			return false;

		}

	}



	public void connectorResponse_jsonV3(RemoteWebDriver driver, String JsonResponse, String AirLine,

			String connectortype) throws JSONException {



		JSONObject jsonObject = new JSONObject(JsonResponse);

		JSONObject json = jsonObject.getJSONObject("content");

		JSONObject content = json.getJSONObject("1");



		String fk = content.getJSONObject(String.valueOf(1)).getString("f");



		System.out.println("Fare Key Present : " + fk);



		Assert.assertTrue(fk.contains(AirLine), "Results for searched connector has not displayed");



		addLog(AirLine + "connector results are returned");

		System.out.println(AirLine + "connector results are returned");

	}



	public boolean paymentWalletPageLoadCheck(RemoteWebDriver driver, String wallet) throws Exception {

		boolean reached = false;

		Thread.sleep(1000);



		if (!(waitElement(driver, getObject("AirCom_BookStep4_CreditCard_Tab"), 20)))

			return false;

		addLog("Payment details loaded", true);



		assertTrue("Wallet as Payment Option is Not Present",

				elementPresent(driver, getObject("AirCom_Payment_Type_WalletLink")));

		safeClick(driver, getObject("AirCom_Payment_Type_WalletLink"));



		if (wallet.equals("payu")) {

			safeClick(driver, By.xpath("//li[@data-paymentsubtype='PAYU_WALLET']/label/input"));



			addLog("PayU Wallet Option Selected", true);

			// printInfo("PayU Wallet Option Selected");



			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			addLog("Make Payment Button Clicked", true);



			if (waitForElementVisibility(driver, getObject("AirCom_PayUWallet_Login_Button"), 180)) {

				addLog("Arrived on PayU Page", true);

				safeClick(driver, getObject("AirCom_PayUWallet_Login_Button"));

				reached = true;

			} else {

				addLog("Pay U page not loaded", true);

				assertTrue("Pay U page not loaded. Error!", false);

			}



			/*

			 * addLog("Trying to login to Pay U by cliking on Login button" , true);

			 *

			 * if (waitForElementVisibility(driver, getObject("PayU_SignIn_EmailTxtFld"),

			 * 10)) { safeType(driver, getObject("PayU_SignIn_EmailTxtFld"), "");

			 * safeType(driver, getObject("PayU_SignIn_EmailTxtFld"),

			 * dataFile.value("PayUUserName"));

			 *

			 * safeType(driver, getObject("PayU_SignIn_PasswordTxtFld"), "");

			 * safeType(driver, getObject("PayU_SignIn_PasswordTxtFld"),

			 * dataFile.value("PayUPassword")); safeClick(driver,

			 * getObject("AirCom_PayUWallet_Login_Button")); } else {

			 * addLog("Pay U Login Page not Loaded", true);

			 * Assert.fail("Pay U Login Page not Loaded"); } if

			 * (waitForElementVisibility(driver,

			 * By.xpath("//h5[contains(text(),'Payment')]"), 20)) { addLog(

			 * "Successfully logged in and reached Payment options Page.", true); reached =

			 * true; }

			 */

			/*

			 * driver.findElementByXPath("//*[@id='cardDiv']/div/ul/li[2]/a"). isEnabled();

			 * Thread.sleep(5000);

			 */

		} else if (wallet.equals("paytm")) {

			safeClick(driver, By.xpath("//li[@data-paymentsubtype='PAYTM_WALLET']/label/input"));



			addLog("PayTM Wallet Option Selected", true);

			// printInfo("PayTM Wallet Option Selected");



			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			Thread.sleep(5000);

			if (waitElement(driver, By.id("login-btn"), 180)) {

				safeClick(driver, By.id("login-btn"));

				Thread.sleep(2000);

				driver.switchTo().frame("login-iframe");

				if (waitElement(driver, By.xpath("//*[@id='loginForm']/div/div[2]/input"), 10)) {

					addLog("Arrived on PayTM Page", true);

					// printInfo("Arrived on PayTM Page");

					reached = true;

				} else {

					assertTrue("PayTM signIn page not loaded. Error!", false);

				}

				/*

				 * if (elementPresent(driver, By.xpath("//*[@name='username']"))) { //

				 * safeType(driver, By.xpath("//*[@name='username']"), ""); safeType(driver,

				 * By.xpath("//*[@name='username']"), dataFile.value("PayTMUserName")); //

				 * safeType(driver, By.xpath("//*[@name='password']"), ""); safeType(driver,

				 * By.xpath("//*[@name='password']"), dataFile.value("PayTMPassword"));

				 * safeClick(driver, By.xpath("//*[@id='loginForm']/div/div[8]/button[1]"));

				 * Thread.sleep(2000); } if (waitElement(driver, By.xpath(""), 20)) { reached =

				 * true; }

				 */

			} else {

				assertTrue("PayTM page not loaded. Error!", false);

			}

		} else if (wallet.equals("masterpass")) {

			safeClick(driver, By.xpath("//li[@data-paymentsubtype='MASTERPASS_WALLET']/label/input"));



			addLog("Masterpass Wallet Option Selected", true);

			// printInfo("Masterpass Wallet Option Selected");



			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			addLog("Make Payment Button Clicked", true);



			Thread.sleep(5000);

			if (waitForElementVisibility(driver, By.id("MasterPass_container"), 180)) {

				driver.switchTo().frame("MasterPass_frame");

				if (waitForElementVisibility(driver, getObject("AirCom_mpWalletPage_UserName"), 10)) {

					addLog("Arrived on Masterpass Page", true);



					reached = true;

				} else {

					assertTrue("Masterpass page not loaded. Error!", false);

				}

			} else {

				assertTrue("Masterpass page not loaded. Error!", false);

			}

		} else {

			addLog("Wallet option wrong. Error!", true);

			assertTrue("Failure!", false);

		}

		return reached;

	}



	public boolean paymentUpiPageLoadCheck(RemoteWebDriver driver, String upi) throws Exception {

		boolean reached = false;

		Thread.sleep(1000);



		if (!(waitForElementVisibility(driver, getObject("AirCom_BookStep4_CreditCard_Tab"), 20)))

			return false;

		addLog("Payment details tab loaded", true);



		assertTrue("UPI as Payment Option is Not Present",

				waitForElementVisibility(driver, getObject("AirCom_Payment_Type_UpiLink"), 3));

		safeClick(driver, getObject("AirCom_Payment_Type_UpiLink"));

		addLog("Selected UPI as payment method", true);



		if (upi.equals("phonepe")) {

			safeClick(driver, getObject("AirCom_Payment_Type_PhonePe"));



			addLog("PhonePe Payment Option Selected", true);



			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));

			addLog("Make Payment button clicked", true);



			if (waitForElementVisibility(driver, getObject("AirCom_Payment_PhonePay_OTP_Button"), 180)) {

				addLog("Arrived on PhonePe Login Page", true);

				reached = true;

			} else {

				assertTrue("PhonePe page not loaded. Error!", false);

			}

		} else {

			addLog("UPI option wrong. Error!", true);

			assertTrue("Failure!", false);

		}

		return reached;

	}



	/*

	 * Author: prashanth.k@cleartrip.com Check the fexiSearch block OW booking

	 */

	public SoftAssert verifyFlexiSearch_OW(RemoteWebDriver driver, SoftAssert s) throws Exception {

		int userAlerts = 0;

		int systemAlerts = 0;



		LinkedList<String> userSearchedDates = new LinkedList<String>();

		LinkedList<String> systemSuggestedDates = new LinkedList<String>();

		LinkedList<String> userSearchedPrices = new LinkedList<String>();

		LinkedList<String> systemSuggestedPrices = new LinkedList<String>();



		String searchURL = driver.getCurrentUrl();

		String searchDate = "";

		String searchLowestPrice = "";



		// Getting the lowest priced air fare for search date

		if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 60)) {

			searchDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[0];

			searchLowestPrice = StringUtils.substringBetween(

					getText(driver, By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]")),

					"Rs.", ",")

					+ getText(driver, By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]"))

							.split(",")[1];

			addLog("Search Date: " + searchDate + " Search Lowest Price: " + searchLowestPrice, true);

		}



		// Checking for presence of Flexi fare block

		addLog("Verifying presence of Fare Comparison window", true);

		if (!elementPresent_Time(driver, By.xpath("//section[@id='flexiBlock']/h1/span"), 60)) {

			s.fail("Flexi block is not available on the SRP");

		}



		addLog("Verifing Title of fare comparison window.", true);

		String titlePresent = driver

				.findElement(By.xpath(

						"//div[@id='ResultContainer_1_1']/section/section/div/section[@id='flexiBlock']/h1/span"))

				.getText();

		String titleExpected = "Compare fares across these dates. Tip: You can even apply time and airline filters.";

		addLog("Expected: " + titleExpected + " Actual: " + titlePresent, true);

		s.assertTrue(titleExpected.equals(titlePresent), "Flexi Fare Block title mismatch!");



		// Getting System suggestion details

		addLog("Verifing presence of System Suggested Fares", true);

		if (!elementPresent_Time(driver, By.xpath("//li/a/h3/span[@rel='b']"), 15)) {

			s.fail("System search suggestions are not available on flexi-search");

		}



		systemAlerts = driver.findElements(By.xpath("//li/a/h3/span[@rel='b']")).size();

		addLog("Number of System Alerts: " + systemAlerts, true);



		// Getting System suggestion dates

		addLog("System suggested Dates:", true);

		if (systemAlerts > 0) {

			List<WebElement> we2 = driver.findElements(By.xpath("//li/a/h3/span[@rel='b']/../span[2]"));

			for (WebElement e : we2) {

				systemSuggestedDates.add(e.getAttribute("data-departure"));

				addLog(e.getText() + " " + e.getAttribute("data-departure"), true);

			}



			Thread.sleep(10000);



			// Getting System suggestion prices

			addLog("System suggested Prices:", true);

			List<WebElement> we22 = driver.findElements(By.xpath("//li/a/h3/span[@rel='b']/../../h4"));

			for (WebElement e : we22) {

				systemSuggestedPrices.add(e.getText());

				addLog(e.getText(), true);

			}



			// Extracting Prices

			addLog("Extracting Prices:", true);

			for (int i = 0; i < systemSuggestedPrices.size(); i++) {

				// systemSuggestedPrices.set(i,

				// StringUtils.substringBetween(systemSuggestedPrices.get(i),

				// " ", " . "));

				String temp = "";

				// userSearchedPrices.set(i,

				// StringUtils.substringBetween(userSearchedPrices.get(i), " ",

				// " ? "));

				char[] c = systemSuggestedPrices.get(i).toCharArray();

				for (int j = 0; j < c.length; j++) {

					if (Character.isDigit(c[j])) {

						temp = temp + c[j];

					}

				}

				systemSuggestedPrices.set(i, temp);

				addLog(systemSuggestedPrices.get(i), true);

			}



			// Verification that System suggested price <= Current search price

			addLog("Verification that System suggested prices <= Current search price.", true);

			for (String str : systemSuggestedPrices) {

				if (Integer.parseInt(str) > Integer.parseInt(searchLowestPrice)) {

					addLog("System Suggested price: " + Integer.parseInt(str) + " > Current search price: "

							+ Integer.parseInt(searchLowestPrice), true);

					s.fail();

				}

			}

			addLog("Verified that System suggested prices <= Current search price.", true);



			// This approach will not work as not all calendar dates are

			// populated

			// Comparing suggested prices with calendar prices for each date

			/*

			 * addLog(

			 * "Verifying Date and Prices against Date and Price Displayed in Calendar" ,

			 * true); for(int i=0; i<userSearchedDates.size(); i++) { String xpath =

			 * "//tbody[@class='calRender']/tr/td[contains(@data-searchdate,'" +

			 * systemSuggestedDates.get(i) + "')]/a/strong[@class='flightFare']";

			 *

			 * String systemPrice = systemSuggestedPrices.get(i); String calenderPrice =

			 * driver.findElement(By.xpath(xpath)).getText();

			 *

			 * addLog("Comparison " + i + "for date " + systemSuggestedDates.get(i) +

			 * ":" + "SystemPrice: " + systemPrice + "CalenderPrice: " + calenderPrice,

			 * true); s.assertTrue(systemPrice.contains(calenderPrice),

			 * "Price MisMatch for the above dates"); }

			 */



			// Verifying current Date and Prices against Date and Price

			// suggested by the system

			addLog("Verifying current Date and Prices against Date and Price suggested by the system", true);

			for (int i = 0; i < systemSuggestedDates.size(); i++) {

				String newDate = "";

				String newPrice = "";

				String newURL = searchURL.replace(searchDate, systemSuggestedDates.get(i));

				addLog("Getting lowest price for System suggested date: " + systemSuggestedDates.get(i), true);

				addLog("New search URL: " + newURL, true);



				driver.navigate().to(newURL);



				if (!checkFlightsCount1(driver)) {

					addLog("No results found for this search.", true);

					s.fail("No results found for the system suggested search date.");

					continue;

				}



				addLog("Serarch completed for the System Suggested date: " + systemSuggestedDates.get(i), true);

				if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 60)) {

					newDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

							.split("-")[0];

					newPrice = StringUtils.substringBetween(

							getText(driver,

									By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]")),

							"Rs.", ",")

							+ getText(driver,

									By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]"))

											.split(",")[1];

					addLog("Search Date: " + newDate + " Search Lowest Price: " + newPrice, true);

					if (newDate.equals(systemSuggestedDates.get(i)) && newPrice.equals(systemSuggestedPrices.get(i))) {

						addLog("Price and date Matched!", true);

					} else {

						s.fail("Price and date mis-Matched for System suggested date displayed:"

								+ systemSuggestedDates.get(i) + " / " + systemSuggestedPrices.get(i)

								+ " Vs actual search price: " + newPrice + " for that date!");

					}

				}

			}

		}



		// Getting User searched suggestion details

		addLog("Verifying presence of User Searched Fare", true);

		if (!elementPresent_Time(driver, By.xpath("//li/a/h3/span[@rel='bTooltip']"), 15)) {

			s.fail("User search suggestions are not available on flexi-search");

		}



		userAlerts = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']")).size();

		addLog("Number of User Alerts: " + userAlerts, true);



		// Getting User searched suggestion dates

		addLog("User Search Dates:", true);

		if (userAlerts > 0) {

			List<WebElement> we1 = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']/../span[2]"));

			for (WebElement e : we1) {

				userSearchedDates.add(e.getAttribute("data-departure"));

				addLog(e.getText() + " " + e.getAttribute("data-departure"), true);

			}



			Thread.sleep(10000);



			// Getting User searched suggestion prices

			addLog("User Search Prices:", true);

			List<WebElement> we11 = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']/../../h4"));

			for (WebElement e : we11) {

				userSearchedPrices.add(e.getText());

				addLog(e.getText(), true);

			}



			// Extracting Prices

			addLog("Extracting Prices:", true);

			for (int i = 0; i < userSearchedPrices.size(); i++) {

				String temp = "";

				// userSearchedPrices.set(i,

				// StringUtils.substringBetween(userSearchedPrices.get(i), " ",

				// " ? "));

				char[] c = userSearchedPrices.get(i).toCharArray();

				for (int j = 0; j < c.length; j++) {

					if (Character.isDigit(c[j])) {

						temp = temp + c[j];

					}

				}

				userSearchedPrices.set(i, temp);

				addLog(userSearchedPrices.get(i), true);

			}



			// This approach will not work as not all calendar dates are

			// populated

			// Comparing suggested prices with calendar prices for each date

			/*

			 * addLog(

			 * "Verifying Date and Prices against Date and Price Displayed in Calendar" ,

			 * true); for(int i=0; i<userSearchedDates.size(); i++) { String xpath =

			 * "//tbody[@class='calRender']/tr/td[contains(@data-searchdate,'" +

			 * userSearchedDates.get(i) + "')]/a/strong[@class='flightFare']";

			 *

			 * String userPrice = userSearchedPrices.get(i); String calenderPrice =

			 * driver.findElement(By.xpath(xpath)).getText();

			 *

			 * addLog("Comparison " + i + "for date " + userSearchedDates.get(i) + ":"

			 * + "UserPrice: " + userPrice + "CalenderPrice: " + calenderPrice, true);

			 * s.assertTrue(userPrice.contains(calenderPrice),

			 * "Price MisMatch for the above dates"); }

			 */



			// Verifying Prices of previous user searches displayed against

			// actual search results

			addLog("Verifying Prices of previous user searches displayed against actual search results", true);

			for (int i = 0; i < userSearchedDates.size(); i++) {

				String newDate = "";

				String newPrice = "";

				String newURL = searchURL.replace(searchDate, userSearchedDates.get(i));

				addLog("Getting lowest price for User searched date: " + userSearchedDates.get(i), true);

				addLog("New search URL: " + newURL, true);



				driver.navigate().to(newURL);



				if (!checkFlightsCount1(driver)) {

					addLog("No results found for this search.", true);

					s.fail("No results found for the user search date.");

					continue;

				}



				addLog("Search completed for the User searched date: " + userSearchedDates.get(i), true);

				if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 60)) {

					newDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

							.split("-")[0];

					newPrice = StringUtils.substringBetween(

							getText(driver,

									By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]")),

							"Rs.", ",")

							+ getText(driver,

									By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]"))

											.split(",")[1];

					addLog("Search Date: " + newDate + " Search Lowest Price: " + newPrice, true);

					if (newDate.equals(userSearchedDates.get(i)) && newPrice.equals(userSearchedPrices.get(i))) {

						addLog("Price and date Matched!", true);

					} else {

						s.fail("Price and date misMatch for User search date displayed: " + userSearchedDates.get(i)

								+ " / " + userSearchedPrices.get(i) + " Vs actual search for that date: " + newPrice);

					}

				}

			}

		}

		return s;

	}



	/*

	 * Author: prashanth.k@cleartrip.com Check the fexiSearch block Return booking

	 */

	public void verifyFlexiSearch_RT(RemoteWebDriver driver) throws Exception {

		SoftAssert s = new SoftAssert();

		int userAlerts = 0;

		int systemAlerts = 0;



		List<String> userSearchedOnwardDates = new LinkedList<String>();

		List<String> userSearchedReturnDates = new LinkedList<String>();

		List<String> systemSuggestedOnwardDates = new LinkedList<String>();

		List<String> systemSuggestedReturnDates = new LinkedList<String>();



		List<String> userSearchedPrices = new LinkedList<String>();

		List<String> systemSuggestedPrices = new LinkedList<String>();



		String searchDateOnwards = "";

		String searchDateReturn = "";

		String searchLowestPrice = "";



		String searchURL = driver.getCurrentUrl();



		// Getting the lowest priced air fare for search date

		if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 120)) {

			searchDateOnwards = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[0];

			searchDateReturn = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[1];

			searchLowestPrice = getAttribute(driver, By.xpath("//div[@class='row roundTripHead']/div/h2/span"),

					"data-pr");

			addLog("Search Date Onwards: " + searchDateOnwards + " Search Date Return: " + searchDateReturn

					+ " Search Lowest Price: " + searchLowestPrice, true);

		}



		// Checking for presence of Flexi fare block

		addLog("Verifying presence of Fare Comparison window", true);

		if (!elementPresent_Time(driver, By.xpath("//section[@id='flexiBlock']/h1/span"), 60)) {

			s.fail("Flexi block is not available on the SRP");

			s.assertAll();

		}



		String titlePresent = driver

				.findElement(By.xpath(

						"//div[@id='ResultContainer_1_1']/section/section/div/section[@id='flexiBlock']/h1/span"))

				.getText();

		String titleExpected = "Compare fares across these dates. Tip: You can even apply time and airline filters.";

		addLog("Expected: " + titleExpected + " Actual: " + titlePresent, true);

		s.assertTrue(titleExpected.equals(titlePresent), "Flexi Fare Block title mismatch!");



		// Getting System suggestion details

		addLog("Verifing presence of System suggested Fare", true);

		if (!elementPresent_Time(driver, By.xpath("//li/a/h3/span[@rel='b']"), 20)) {

			s.fail("System suggestions are not avalable on flexi-search");

		}



		systemAlerts = driver.findElements(By.xpath("//li/a/h3/span[@rel='b']")).size();

		addLog("Number of System suggestions: " + systemAlerts, true);



		addLog("System suggestion Dates:", true);

		if (systemAlerts > 0) {

			List<WebElement> we2 = driver.findElements(By.xpath("//li/a/h3/span[@rel='b']/../span[2]"));

			for (WebElement e : we2) {

				systemSuggestedOnwardDates.add(e.getAttribute("data-departure"));

				systemSuggestedReturnDates.add(e.getAttribute("data-arrival"));

				addLog(e.getText() + " Onward: " + e.getAttribute("data-departure") + " Return: "

						+ e.getAttribute("data-arrival"), true);

			}

		}



		// Getting System suggested Prices

		addLog("System suggested Prices:", true);

		List<WebElement> we22 = driver.findElements(By.xpath("//li/a/h3/span[@rel='b']/../../h4"));

		for (WebElement e : we22) {

			systemSuggestedPrices.add(e.getText());

			addLog(e.getText(), true);

		}



		// Extracting Prices

		addLog("Extracting Prices:", true);

		for (int i = 0; i < systemSuggestedPrices.size(); i++) {

			String temp = "";

			// userSearchedPrices.set(i,

			// StringUtils.substringBetween(userSearchedPrices.get(i), " ",

			// " ? "));

			char[] c = systemSuggestedPrices.get(i).toCharArray();

			for (int j = 0; j < c.length; j++) {

				if (Character.isDigit(c[j])) {

					temp = temp + c[j];

				}

			}

			systemSuggestedPrices.set(i, temp);

			addLog(systemSuggestedPrices.get(i), true);

		}



		// Verification that System suggested price <= Current search price

		addLog("Verification that System suggested prices <= Current search price.", true);

		for (String str : systemSuggestedPrices) {

			if (Integer.parseInt(str) > Integer.parseInt(searchLowestPrice)) {

				addLog("System Suggested price: " + Integer.parseInt(str) + " > Current search price: "

						+ Integer.parseInt(searchLowestPrice), true);

				s.fail();

				s.assertAll();

			}

		}

		addLog("Verified that System suggested prices <= Current search price.", true);



		// Verifying Prices of system suggestions displayed against actual

		// search results

		addLog("Verifying Prices of system suggestions displayed against actual search results", true);

		for (int i = 0; i < systemSuggestedOnwardDates.size(); i++) {

			String newOnwardDate = "";

			String newReturnDate = "";

			String newPrice = "";

			String newURL = searchURL.replace(searchDateOnwards, systemSuggestedOnwardDates.get(i))

					.replace(searchDateReturn, systemSuggestedReturnDates.get(i));

			addLog("Getting lowest price for system suggested dates: " + systemSuggestedOnwardDates.get(i) + " - "

					+ systemSuggestedReturnDates.get(i), true);

			addLog("New search URL: " + newURL, true);



			driver.navigate().to(newURL);



			if (!checkFlightsCount1(driver)) {

				addLog("No results found for this search.", true);

				s.fail("No results found for the system suggested search date.");

				continue;

			}



			addLog("Search completed for the System duggested dates: " + systemSuggestedOnwardDates.get(i) + " - "

					+ systemSuggestedReturnDates.get(i), true);

			if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 60)) {

				newOnwardDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

						.split("-")[0];

				newReturnDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

						.split("-")[1];

				newPrice = getAttribute(driver, By.xpath("//div[@class='row roundTripHead']/div/h2/span"), "data-pr");

				addLog("Search dates: " + systemSuggestedOnwardDates.get(i) + " - "

						+ systemSuggestedReturnDates.get(i) + " Search Lowest Price: " + newPrice, true);

				if (newOnwardDate.equals(systemSuggestedOnwardDates.get(i))

						&& newReturnDate.equals(systemSuggestedReturnDates.get(i))

						&& newPrice.equals(systemSuggestedPrices.get(i))) {

					addLog("Price and date Matched!", true);

				} else {

					s.fail("Price and date mismatch for system suggested date displayed: "

							+ systemSuggestedOnwardDates.get(i) + " / " + systemSuggestedReturnDates.get(i) + " / "

							+ systemSuggestedPrices.get(i) + " Vs actual search for that date: " + newPrice);

				}

			}

		}



		// Getting User searched suggestion details

		addLog("Verifing presence of User Searched Fare", true);

		if (!elementPresent_Time(driver, By.xpath("//li/a/h3/span[@rel='bTooltip']"), 15)) {

			s.fail("User search suggestions are not avalable on flexi-search");

		}



		userAlerts = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']")).size();

		addLog("Number of User Alerts: " + userAlerts, true);



		addLog("User Search Dates:", true);

		if (userAlerts > 0) {

			List<WebElement> we1 = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']/../span[2]"));

			for (WebElement e : we1) {

				userSearchedOnwardDates.add(e.getAttribute("data-departure"));

				userSearchedReturnDates.add(e.getAttribute("data-arrival"));

				addLog(e.getText() + " Onward: " + e.getAttribute("data-departure") + " Return: "

						+ e.getAttribute("data-arrival"), true);

			}

		}



		// Getting User Searched Prices

		addLog("User Search Prices:", true);

		List<WebElement> we11 = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']/../../h4"));

		for (WebElement e : we11) {

			userSearchedPrices.add(e.getText());

			addLog(e.getText(), true);

		}



		// Extracting Prices

		addLog("Extracting Prices:", true);

		for (int i = 0; i < userSearchedPrices.size(); i++) {

			String temp = "";

			// userSearchedPrices.set(i,

			// StringUtils.substringBetween(userSearchedPrices.get(i), " ",

			// " ? "));

			char[] c = userSearchedPrices.get(i).toCharArray();

			for (int j = 0; j < c.length; j++) {

				if (Character.isDigit(c[j])) {

					temp = temp + c[j];

				}

			}

			userSearchedPrices.set(i, temp);

			addLog(userSearchedPrices.get(i), true);

		}



		// Verifying Prices of previous user searches displayed against actual

		// search results

		addLog("Verifying Prices of previous user searches displayed against actual search results", true);

		for (int i = 0; i < userSearchedOnwardDates.size(); i++) {

			String newOnwardDate = "";

			String newReturnDate = "";

			String newPrice = "";

			String newURL = searchURL.replace(searchDateOnwards, userSearchedOnwardDates.get(i))

					.replaceFirst(searchDateReturn, userSearchedReturnDates.get(i));

			addLog("Getting lowest price for User searched dates: " + userSearchedOnwardDates.get(i) + " - "

					+ userSearchedReturnDates.get(i), true);

			addLog("New search URL: " + newURL, true);



			driver.navigate().to(newURL);



			if (!checkFlightsCount1(driver)) {

				addLog("No results found for this search.", true);

				s.fail("No results found for this user search date.");

				continue;

			}



			addLog("Search completed for the User searched dates: " + userSearchedOnwardDates.get(i) + " - "

					+ userSearchedReturnDates.get(i), true);

			if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 60)) {

				newOnwardDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

						.split("-")[0];

				newReturnDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

						.split("-")[1];

				newPrice = getAttribute(driver, By.xpath("//div[@class='row roundTripHead']/div/h2/span"), "data-pr");

				addLog("Search dates: " + userSearchedOnwardDates.get(i) + " - " + userSearchedReturnDates.get(i)

						+ " Search Lowest Price: " + newPrice, true);

				if (newOnwardDate.equals(userSearchedOnwardDates.get(i))

						&& newReturnDate.equals(userSearchedReturnDates.get(i))

						&& newPrice.equals(userSearchedPrices.get(i))) {

					addLog("Price and date Matched!", true);

				} else {

					s.fail("Price and date mismatch for system suggested date displayed: "

							+ userSearchedOnwardDates.get(i) + " / " + userSearchedReturnDates.get(i) + " / "

							+ userSearchedPrices.get(i) + " Vs actual search for that date: " + newPrice);

				}

			}

		}



		s.assertAll();

	}



	/*

	 * Author: prashanth.k@cleartrip.com Book OW ticket with first user search

	 * result in flexi search block

	 */

	public void selectFexiSearchUserDate_OW(RemoteWebDriver driver) throws Exception {

		SoftAssert s = new SoftAssert();

		int userAlerts = 0;



		LinkedList<String> userSearchedDates = new LinkedList<String>();

		LinkedList<String> userSearchedPrices = new LinkedList<String>();



		String searchDate = "";

		String searchLowestPrice = "";



		// Getting the lowest priced air fare for search date

		if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 60)) {

			searchDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[0];

			searchLowestPrice = StringUtils.substringBetween(

					getText(driver, By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]")),

					"Rs.", ",")

					+ getText(driver, By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]"))

							.split(",")[1];

			addLog("Search Date: " + searchDate + " Search Lowest Price: " + searchLowestPrice, true);

		}



		// Checking for presence of Flexi fare block

		addLog("Verifying presence of Fare Comparison window", true);

		if (!elementPresent_Time(driver, By.xpath("//section[@id='flexiBlock']/h1/span"), 60)) {

			s.fail("Flexi block is not available on the SRP");

			s.assertAll();

		}



		addLog("Verifing Title of fare comparison window.", true);

		String titlePresent = driver

				.findElement(By.xpath(

						"//div[@id='ResultContainer_1_1']/section/section/div/section[@id='flexiBlock']/h1/span"))

				.getText();

		String titleExpected = "Compare fares across these dates. Tip: You can even apply time and airline filters.";

		addLog("Expected: " + titleExpected + " Actual: " + titlePresent, true);

		s.assertTrue(titleExpected.equals(titlePresent), "Flexi Fare Block title mismatch!");



		// Getting User searched suggestion details

		addLog("Verifying presence of User Searched Fare", true);

		if (!elementPresent_Time(driver, By.xpath("//li/a/h3/span[@rel='bTooltip']"), 15)) {

			s.fail("User search suggestions are not available on flexi-search");

			s.assertAll();

		}



		userAlerts = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']")).size();

		addLog("Number of User Alerts: " + userAlerts, true);



		// Getting User searched suggestion dates

		addLog("User Search Dates:", true);

		if (userAlerts > 0) {

			List<WebElement> we1 = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']/../span[2]"));

			for (WebElement e : we1) {

				userSearchedDates.add(e.getAttribute("data-departure"));

				addLog(e.getText() + " " + e.getAttribute("data-departure"), true);

			}

		}



		// Getting User searched suggestion prices

		addLog("User Search Prices:", true);

		List<WebElement> we11 = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']/../../h4"));

		for (WebElement e : we11) {

			userSearchedPrices.add(e.getText());

			addLog(e.getText(), true);

		}



		// Extracting Prices

		addLog("Extracting Prices:", true);

		for (int i = 0; i < userSearchedPrices.size(); i++) {

			String temp = "";

			// userSearchedPrices.set(i,

			// StringUtils.substringBetween(userSearchedPrices.get(i), " ",

			// " ? "));

			char[] c = userSearchedPrices.get(i).toCharArray();

			for (int j = 0; j < c.length; j++) {

				if (Character.isDigit(c[j])) {

					temp = temp + c[j];

				}

			}

			userSearchedPrices.set(i, temp);

			addLog(userSearchedPrices.get(i), true);

		}



		// Clicking on the first previous User search result

		addLog("Clicking on the first previous User search result", true);

		if (elementPresent(driver, By.xpath("(//li/a/h3/span[@rel='bTooltip']/../../../a)[1]")))

			if (elementClickable(driver, By.xpath("(//li/a/h3/span[@rel='bTooltip']/../../../a)[1]"), 1))

				driver.findElement(By.xpath("(//li/a/h3/span[@rel='bTooltip']/../../../a)[1]")).click();

			else {

				s.fail("User Previous search suggestion not clickable!");

				s.assertAll();

			}

		else {

			s.fail("User Previous search suggestion not Found!");

			s.assertAll();

		}



		String newDate = "";

		String newPrice = "";



		addLog("Getting lowest price for User searched date: " + userSearchedDates.get(0), true);

		addLog("New search URL: " + driver.getCurrentUrl(), true);



		if (!checkFlightsCount1(driver)) {

			addLog("No results found for this search.", true);

			s.fail("No results found for the system suggested search date.");

			s.assertAll();

		}



		addLog("Search completed for the User searched date: " + userSearchedDates.get(0), true);

		if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 60)) {

			newDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[0];

			newPrice = StringUtils.substringBetween(

					getText(driver, By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]")),

					"Rs.", ",")

					+ getText(driver, By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]"))

							.split(",")[1];

			addLog("Search Date: " + newDate + " Search Lowest Price: " + newPrice, true);

			if (newDate.equals(userSearchedDates.get(0)) && newPrice.equals(userSearchedPrices.get(0))) {

				addLog("Price and date Matched!", true);

			} else {

				s.fail("Price and date Matched for User search date displayed Vs actual search for that date!");

				s.assertAll();

			}

		}

	}



	/*

	 * Author: prashanth.k@cleartrip.com Book OW ticket with first system suggested

	 * result in flexi search block

	 */

	public void selectFexiSearchSystemDate_OW(RemoteWebDriver driver) throws Exception {

		SoftAssert s = new SoftAssert();

		int systemAlerts = 0;



		LinkedList<String> systemSearchedDates = new LinkedList<String>();

		LinkedList<String> systemSearchedPrices = new LinkedList<String>();



		String searchDate = "";

		String searchLowestPrice = "";



		// Getting the lowest priced air fare for search date

		if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 60)) {

			searchDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[0];

			searchLowestPrice = StringUtils.substringBetween(

					getText(driver, By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]")),

					"Rs.", ",")

					+ getText(driver, By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]"))

							.split(",")[1];

			addLog("Search Date: " + searchDate + " Search Lowest Price: " + searchLowestPrice, true);

		}



		// Checking for presence of Flexi fare block

		addLog("Verifying presence of Fare Comparison window", true);

		if (!elementPresent_Time(driver, By.xpath("//section[@id='flexiBlock']/h1/span"), 60)) {

			s.fail("Flexi block is not available on the SRP");

			s.assertAll();

		}



		addLog("Verifing Title of fare comparison window.", true);

		String titlePresent = driver

				.findElement(By.xpath(

						"//div[@id='ResultContainer_1_1']/section/section/div/section[@id='flexiBlock']/h1/span"))

				.getText();

		String titleExpected = "Compare fares across these dates. Tip: You can even apply time and airline filters.";

		addLog("Expected: " + titleExpected + " Actual: " + titlePresent, true);

		s.assertTrue(titleExpected.equals(titlePresent), "Flexi Fare Block title mismatch!");



		// Getting System suggestion details

		addLog("Verifying presence of System suggested Fares", true);

		if (!elementPresent_Time(driver, By.xpath("//li/a/h3/span[@rel='b']"), 15)) {

			s.fail("System suggestions are not available on flexi-search");

			s.assertAll();

		}



		systemAlerts = driver.findElements(By.xpath("//li/a/h3/span[@rel='b']")).size();

		addLog("Number of System Suggestions: " + systemAlerts, true);



		// Getting system suggestion dates

		addLog("System Suggestion Dates:", true);

		if (systemAlerts > 0) {

			List<WebElement> we1 = driver.findElements(By.xpath("//li/a/h3/span[@rel='b']/../span[2]"));

			for (WebElement e : we1) {

				systemSearchedDates.add(e.getAttribute("data-departure"));

				addLog(e.getText() + " " + e.getAttribute("data-departure"), true);

			}

		}



		// Getting system suggestion prices

		addLog("System Suggestion Prices:", true);

		List<WebElement> we11 = driver.findElements(By.xpath("//li/a/h3/span[@rel='b']/../../h4"));

		for (WebElement e : we11) {

			systemSearchedPrices.add(e.getText());

			addLog(e.getText(), true);

		}



		// Extracting Prices

		addLog("Extracting Prices:", true);

		for (int i = 0; i < systemSearchedPrices.size(); i++) {

			String temp = "";

			// userSearchedPrices.set(i,

			// StringUtils.substringBetween(userSearchedPrices.get(i), " ",

			// " ? "));

			char[] c = systemSearchedPrices.get(i).toCharArray();

			for (int j = 0; j < c.length; j++) {

				if (Character.isDigit(c[j])) {

					temp = temp + c[j];

				}

			}

			systemSearchedPrices.set(i, temp);

			addLog(systemSearchedPrices.get(i), true);

		}



		// Verification that System suggested price <= Current search price

		addLog("Verification that System suggested prices <= Current search price.", true);

		for (String str : systemSearchedPrices) {

			if (Integer.parseInt(str) > Integer.parseInt(searchLowestPrice)) {

				addLog("System Suggested price: " + Integer.parseInt(str) + " > Current search price: "

						+ Integer.parseInt(searchLowestPrice), true);

				s.fail();

				s.assertAll();

			}

		}

		addLog("Verified that System suggested prices <= Current search price complete.", true);



		// Clicking on the first previous User search result

		addLog("Clicking on the first System suggestion result", true);

		if (elementPresent(driver, By.xpath("(//li/a/h3/span[@rel='b]/../../../a)[1]")))

			if (elementClickable(driver, By.xpath("(//li/a/h3/span[@rel='b']/../../../a)[1]"), 1))

				driver.findElement(By.xpath("(//li/a/h3/span[@rel='b']/../../../a)[1]")).click();

			else {

				s.fail("System suggestion not clickable!");

				s.assertAll();

			}

		else {

			s.fail("System suggestion not Found!");

			s.assertAll();

		}



		String newDate = "";

		String newPrice = "";



		addLog("Getting lowest price for System suggested date: " + systemSearchedDates.get(0), true);

		addLog("New search URL: " + driver.getCurrentUrl(), true);



		if (!checkFlightsCount1(driver)) {

			addLog("No results found for this search.", true);

			s.fail("No results found for the system suggested search date.");

			s.assertAll();

		}



		addLog("Search completed for the System suggested date: " + systemSearchedDates.get(0), true);

		if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 60)) {

			newDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[0];

			newPrice = StringUtils.substringBetween(

					getText(driver, By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]")),

					"Rs.", ",")

					+ getText(driver, By.xpath("//ul[@class='listView flights']/li[1]//th[contains(@class,'price')]"))

							.split(",")[1];

			addLog("Search Date: " + newDate + " Search Lowest Price: " + newPrice, true);

			if (newDate.equals(systemSearchedDates.get(0)) && newPrice.equals(systemSearchedPrices.get(0))) {

				addLog("Price and date Matched!", true);

			} else {

				s.fail("Price and date Matched for User search date displayed Vs actual search for that date!");

				s.assertAll();

			}

		}

	}



	/*

	 * Author: prashanth.k@cleartrip.com Books RT ticket with first user search

	 * result in flexi search block

	 */

	public void selectFexiSearchUserDate_RT(RemoteWebDriver driver) throws Exception {

		SoftAssert s = new SoftAssert();

		int userAlerts = 0;



		List<String> userSearchedOnwardDates = new LinkedList<String>();

		List<String> userSearchedReturnDates = new LinkedList<String>();

		List<String> userSearchedPrices = new LinkedList<String>();



		String searchDateOnwards = "";

		String searchDateReturn = "";

		String searchLowestPrice = "";



		// Getting the lowest priced air fare for search date

		if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 120)) {

			searchDateOnwards = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[0];

			searchDateReturn = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[1];

			searchLowestPrice = getAttribute(driver, By.xpath("//div[@class='row roundTripHead']/div/h2/span"),

					"data-pr");

			addLog("Search Date Onwards: " + searchDateOnwards + " Search Date Return: " + searchDateReturn

					+ " Search Lowest Price: " + searchLowestPrice, true);

		}



		// Checking for presence of Flexi fare block

		addLog("Verifying presence of Fare Comparison window", true);

		if (!elementPresent_Time(driver, By.xpath("//section[@id='flexiBlock']/h1/span"), 60)) {

			s.fail("Flexi block is not available on the SRP");

			s.assertAll();

		}



		String titlePresent = driver

				.findElement(By.xpath(

						"//div[@id='ResultContainer_1_1']/section/section/div/section[@id='flexiBlock']/h1/span"))

				.getText();

		String titleExpected = "Compare fares across these dates. Tip: You can even apply time and airline filters.";

		addLog("Expected: " + titleExpected + " Actual: " + titlePresent, true);

		s.assertTrue(titleExpected.equals(titlePresent), "Flexi Fare Block title mismatch!");



		// Getting User searched suggestion details

		addLog("Verifing presence of User Searched Fare", true);

		if (!elementPresent_Time(driver, By.xpath("//li/a/h3/span[@rel='bTooltip']"), 15)) {

			s.fail("User search suggestions are not avalable on flexi-search");

			s.assertAll();

		}



		userAlerts = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']")).size();

		addLog("Number of User Alerts: " + userAlerts, true);



		addLog("User Search Dates:", true);

		if (userAlerts > 0) {

			List<WebElement> we1 = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']/../span[2]"));

			for (WebElement e : we1) {

				userSearchedOnwardDates.add(e.getAttribute("data-departure"));

				userSearchedReturnDates.add(e.getAttribute("data-arrival"));

				addLog(e.getText() + " Onward: " + e.getAttribute("data-departure") + " Return: "

						+ e.getAttribute("data-arrival"), true);

			}

		}



		// Getting User Searched Prices

		addLog("User Search Prices:", true);

		List<WebElement> we11 = driver.findElements(By.xpath("//li/a/h3/span[@rel='bTooltip']/../../h4"));

		for (WebElement e : we11) {

			userSearchedPrices.add(e.getText());

			addLog(e.getText(), true);

		}



		// Extracting Prices

		addLog("Extracting Prices:", true);

		for (int i = 0; i < userSearchedPrices.size(); i++) {

			String temp = "";

			// userSearchedPrices.set(i,

			// StringUtils.substringBetween(userSearchedPrices.get(i), " ",

			// " ? "));

			char[] c = userSearchedPrices.get(i).toCharArray();

			for (int j = 0; j < c.length; j++) {

				if (Character.isDigit(c[j])) {

					temp = temp + c[j];

				}

			}

			userSearchedPrices.set(i, temp);

			addLog(userSearchedPrices.get(i), true);

		}



		// Clicking on the First previous user searched date displayed

		addLog("Clicking on the First previous user searched date displayed", true);

		addLog("Clicking on the first previous User search result", true);

		if (elementPresent(driver, By.xpath("(//li/a/h3/span[@rel='bTooltip']/../../../a)[1]")))

			if (elementClickable(driver, By.xpath("(//li/a/h3/span[@rel='bTooltip']/../../../a)[1]"), 1))

				driver.findElement(By.xpath("(//li/a/h3/span[@rel='bTooltip']/../../../a)[1]")).click();

			else {

				s.fail("User Previous search suggestion not clickable!");

				s.assertAll();

			}

		else {

			s.fail("User Previous search suggestion not Found!");

			s.assertAll();

		}



		String newOnwardDate = "";

		String newReturnDate = "";

		String newPrice = "";



		addLog("Getting lowest price for User searched dates: " + userSearchedOnwardDates.get(0) + " - "

				+ userSearchedReturnDates.get(0), true);

		addLog("New search URL: " + driver.getCurrentUrl(), true);



		if (!checkFlightsCount1(driver)) {

			addLog("No results found for this search.", true);

			s.fail("No results found for this user search date.");

			s.assertAll();

		}



		addLog("Search completed for the User searched dates: " + userSearchedOnwardDates.get(0) + " - "

				+ userSearchedReturnDates.get(0), true);

		if (elementPresent_Time(driver, By.xpath("//td[contains(@class,'searchedOn')]"), 60)) {

			newOnwardDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[0];

			newReturnDate = getAttribute(driver, By.xpath("//td[contains(@class,'searchedOn')]"), "data-searchdate")

					.split("-")[1];

			newPrice = getAttribute(driver, By.xpath("//div[@class='row roundTripHead']/div/h2/span"), "data-pr");



			addLog("Search dates: " + userSearchedOnwardDates.get(0) + " - " + userSearchedReturnDates.get(0)

					+ " Search Lowest Price: " + newPrice, true);

			if (newOnwardDate.equals(userSearchedOnwardDates.get(0))

					&& newReturnDate.equals(userSearchedReturnDates.get(0))

					&& newPrice.equals(userSearchedPrices.get(0))) {

				addLog("Price and date Matched!", true);

			} else {

				s.fail("Price and date Matched for User search date displayed Vs actual search for that date!");

				s.assertAll();

			}

		}

	}



	/*

	 * Author: prashanth.k@cleartrip.com Filters flights by Departure time on the

	 * SRP

	 */

	public boolean filterByDepartureTime(RemoteWebDriver driver, String depTime) throws Exception {

		addLog("Filtering By departure Time: " + depTime, true);



		if (depTime.equalsIgnoreCase("Early Morning") && elementPresent(driver,

				By.xpath("//input[contains(@id,'departureTime')]/../label[contains(text(),'Early Morning')]"))) {

			safeClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"));

			safeClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a"));

			safeClick(driver,

					By.xpath("//input[contains(@id,'departureTime')]/../label[contains(text(),'Early Morning')]"));

			return true;

		} else if (depTime.equalsIgnoreCase("Morning") && elementPresent(driver,

				By.xpath("//input[contains(@id,'departureTime')]/../label/span[contains(text(),'8am - 12pm')]/.."))) {

			safeClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"));

			safeClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a"));

			safeClick(driver,

					By.xpath("//input[contains(@id,'departureTime')]/../label/span[contains(text(),'8am - 12pm')]/.."));

			return true;

		} else if (depTime.equalsIgnoreCase("Mid-Day") && elementPresent(driver,

				By.xpath("//input[contains(@id,'departureTime')]/../label[contains(text(),'Mid-Day')]"))) {

			safeClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"));

			safeClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a"));

			safeClick(driver, By.xpath("//input[contains(@id,'departureTime')]/../label[contains(text(),'Mid-Day')]"));

			return true;

		} else if (depTime.equalsIgnoreCase("Evening") && elementPresent(driver,

				By.xpath("//input[contains(@id,'departureTime')]/../label[contains(text(),'Evening')]"))) {

			safeClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"));

			safeClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a"));

			safeClick(driver, By.xpath("//input[contains(@id,'departureTime')]/../label[contains(text(),'Evening')]"));

			return true;

		} else if (depTime.equalsIgnoreCase("Night") && elementPresent(driver,

				By.xpath("//input[contains(@id,'departureTime')]/../label[contains(text(),'Night')]"))) {

			safeClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"));

			safeClick(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a"));

			safeClick(driver, By.xpath("//input[contains(@id,'departureTime')]/../label[contains(text(),'Night')]"));

			return true;

		} else {

			addLog("Departure Time: " + depTime + " not found!", true);

			return false;

		}

	}



	/*

	 * Author: prashanth.k@cleartrip.com Verifies Effective price details on the SRP

	 * for OW and RT results.

	 */

	public void verifyEffectivePrice_SRP(RemoteWebDriver driver, Boolean walletPresent, String tripType)

			throws Exception {

		addLog("Verifying Effective Prices displayed on SRP Page", true);



		SoftAssert s = new SoftAssert();



		// Verifying Wallet Balance Message

		addLog("Verifying Wallet Balance Message", true);

		if (elementPresent(driver, getObject("AirCom_SRP_Wallet_Bal_Message"), 1)

				&& elementVisible(driver, getObject("AirCom_SRP_Wallet_Bal_Message"), 1)) {

			String wallet_Message = getText(driver, getObject("AirCom_SRP_Wallet_Bal_Message"));

			if (walletPresent) {

				addLog(wallet_Message, true);

				s.assertTrue(wallet_Message.equals(

						"Your wallet balance can be applied for this booking. Pay only additional amount as shown below. Know more."),

						"Wallet Balance Message Mismatch!");

			} else {

				s.fail("Wallet Message present when wallet is not available for this login. Message Found: "

						+ wallet_Message);

			}

		} else {

			if (walletPresent) {

				s.fail("Wallet Message not present when wallet is available for this login.");

			}

		}



		// Verifying the Know more link

		addLog("Verifying the Know more link", true);

		if (elementPresent(driver, getObject("AirCom_SRP_Wallet_Bal_Message_KnowMore_Link"), 1)

				&& elementVisible(driver, getObject("AirCom_SRP_Wallet_Bal_Message_KnowMore_Link"), 1)) {

			if (walletPresent) {

				addLog((getAttribute(driver, getObject("AirCom_SRP_Wallet_Bal_Message_KnowMore_Link"), "href")),

						true);

				s.assertTrue(getAttribute(driver, getObject("AirCom_SRP_Wallet_Bal_Message_KnowMore_Link"), "href")

						.equals("https://qa2.cleartrip.com/account/wallet"), "Know more Link mismatch!");

			} else {

				s.fail("Know more link should not be available as wallet is not available for this login.");

			}

		} else {

			if (walletPresent) {

				s.fail("Know more link not present when wallet is available for this login.");

			}

		}



		// Verifying the close button

		addLog("Verifying close button", true);

		if (elementPresent(driver, getObject("AirCom_SRP_Wallet_Bal_Message_Close_Button"), 1)

				&& elementVisible(driver, getObject("AirCom_SRP_Wallet_Bal_Message_Close_Button"), 1)) {

			if (walletPresent) {

			} else {

				s.fail("Close button should not be available as wallet is not available for this login.");

			}

		} else {

			if (walletPresent) {

				s.fail("Close button not present when wallet is available for this login.");

			}

		}



		if (tripType.equals("OneWay")) {

			// Getting the number of Results displayed on the SRP

			List<WebElement> flightPrices = driver.findElements(getObject("AirCom_SRP_Flight_Prices_OW"));

			int numOfResults = flightPrices.size();

			addLog("No of results in SRP page: " + numOfResults, true);



			for (int i = 0; i < numOfResults; i++) {

				if (flightPrices.get(i).isDisplayed()

						&& driver.findElements(getObject("AirCom_SRP_Effective_Prices_OW")).get(i).isDisplayed()) {



					String ticketPriceText = flightPrices.get(i).getText();

					String ticketPrice = ticketPriceText.replaceAll("[^0-9]", "");



					String walletBalance_SRP = driver.findElements(getObject("AirCom_SRP_Effective_Prices_OW")).get(i)

							.getAttribute("original-title").split("data-pr='")[1].split("'>?")[0].replaceAll(",", "");

					String effectivePrice_SRPText = driver.findElements(getObject("AirCom_SRP_Effective_Prices_OW"))

							.get(i).getText();

					String effectivePrice_SRP = effectivePrice_SRPText.replaceAll("[^0-9]", "");

					// String effectivePrice_SRP =

					// driver.findElements(getObject("AirCom_SRP_Effective_Prices_OW")).get(i).getText().replace("?",

					// "").replaceAll(",", "");



					if (walletPresent) {

						// Verifying Displayed Effective Price calculation

						addLog("Checking if Effective Price Displayed: " + effectivePrice_SRP

								+ " = Ticket Price: " + ticketPrice + " - Wallet Balance: " + walletBalance_SRP, true);

						s.assertTrue(

								Integer.parseInt(effectivePrice_SRP) == (Integer.parseInt(ticketPrice)

										- Integer.parseInt(walletBalance_SRP)),

								"Effective Price Diaplayed On SRP Mismatch!");

					} else {

						s.fail("Effective Prce is displayed. It should not be available as wallet is not available for this login.");

					}



				} else {

					if (walletPresent) {

						s.fail("Effective price is not displayed when wallet is available for this login.");

					}

				}

			}

		} else if (tripType.equals("RoundTrip")) {

			if (elementPresent(driver, By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td"), 3)) {

				List<WebElement> splRndTabs = driver

						.findElements(By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td"));

				for (int i = 0; i < splRndTabs.size(); i++) {

					splRndTabs.get(i).click();

					if (elementPresent(driver, getObject("AirCom_SRP_Roundtrip_TotalAmount"), 10)

							&& elementPresent(driver, getObject("AirCom_SRP_Effective_Prices_RT"), 10)) {

						String effectivePrice_SRP = getText(driver, getObject("AirCom_SRP_Roundtrip_TotalAmount"))

								.split("Rs.")[1].replaceAll(",", "");

						String walletBalance_SRP = driver.findElement(getObject("AirCom_SRP_Roundtrip_TotalAmount"))

								.getAttribute("original-title").split("data-pr='")[1].split("'>Rs")[0].replaceAll(",",

										"");

						String ticketPrice = driver.findElement(getObject("AirCom_SRP_Effective_Prices_RT")).getText()

								.replace("Original Fare Rs.", "").replaceAll(",", "");



						if (walletPresent) {

							// Verifying Displayed Effective Price calculation

							addLog("Checking if Effective Price Displayed: " + effectivePrice_SRP

									+ " = Ticket Price: " + ticketPrice + " - Wallet Balance: " + walletBalance_SRP,

									true);

							s.assertTrue(

									Integer.parseInt(effectivePrice_SRP) == (Integer.parseInt(ticketPrice)

											- Integer.parseInt(walletBalance_SRP)),

									"Effective Price Diaplayed On SRP Mismatch!");

						} else {

							s.fail("Effective Prce is displayed. It should not be available as wallet is not available for this login.");

						}

					} else {

						if (walletPresent) {

							s.fail("Effective price is not displayed when wallet is available for this login.");

						}

					}

				}

			} else {

				if (elementPresent(driver, getObject("AirCom_SRP_Roundtrip_TotalAmount"), 10)

						&& elementPresent(driver, getObject("AirCom_SRP_Effective_Prices_RT"), 10)) {

					String effectivePrice_SRP = getText(driver, getObject("AirCom_SRP_Roundtrip_TotalAmount"))

							.split("Rs.")[1].replaceAll(",", "");

					String walletBalance_SRP = driver.findElement(getObject("AirCom_SRP_Roundtrip_TotalAmount"))

							.getAttribute("original-title").split("data-pr='")[1].split("'>Rs")[0].replaceAll(",", "");

					String ticketPrice = driver.findElement(getObject("AirCom_SRP_Effective_Prices_RT")).getText()

							.replace("Original Fare Rs.", "").replaceAll(",", "");



					if (walletPresent) {

						// Verifying Displayed Effective Price calculation

						addLog("Checking if Effective Price Displayed: " + effectivePrice_SRP

								+ " = Ticket Price: " + ticketPrice + " - Wallet Balance: " + walletBalance_SRP, true);

						s.assertTrue(

								Integer.parseInt(effectivePrice_SRP) == (Integer.parseInt(ticketPrice)

										- Integer.parseInt(walletBalance_SRP)),

								"Effective Price Diaplayed On SRP Mismatch!");

					} else {

						s.fail("Effective Prce is displayed. It should not be available as wallet is not available for this login.");

					}

				} else {

					if (walletPresent) {

						s.fail("Effective price is not displayed when wallet is available for this login.");

					}

				}

			}

		}

		s.assertAll();

	}



	/*

	 * Author: prashanth.k@cleartrip.com This method handles SRP Results with

	 * Baggage Bundled and No Baggage Bundled Price Radio Buttons as well as non

	 * bundled SRP results and clicks the book button. It stores the baggage values

	 * and the fare details in a hash map which can be used for verification in the

	 * subsequent steps.

	 */

	public HashMap<String, String> airCom_SRP_Oneway_CheckBaggage(RemoteWebDriver driver, Boolean bundledBaggage,

			Boolean withOutBaggageFare, HashMap<String, String> priceAndBaggageDetails) throws Exception {

		Boolean bookButtonClicked = false;

		Boolean bundledFareFound = false;

		Boolean nonBundledFareFound = false;



		SoftAssert s = new SoftAssert();



		if (bundledBaggage && withOutBaggageFare) {

			int noOfSoln = driver.findElements(By.xpath(

					"//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withoutCabinBaggage']/span[2]"))

					.size();

			for (int i = 0; i < noOfSoln; i++) {

				int j = i + 1;

				String labelXPath1 = "(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withoutCabinBaggage']/span[2])["

						+ j + "]";

				if (elementPresent_Time(driver, By.xpath(labelXPath1), 1)) {

					bundledFareFound = true;

					addLog("Bundled Fare Without Baggage Available.", true);

					priceAndBaggageDetails.put("Fare", getText(driver, By.xpath(

							"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withoutCabinBaggage']/span[1])["

									+ j + "]")).replace(getText(driver, By.xpath(

											"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[1]/span)["

													+ j + "]")),

											"").replaceAll(",", "").trim());

					addLog(priceAndBaggageDetails.get("fare"), true);



					addLog("Checking the Dispayed Label: " + getText(driver, By.xpath(labelXPath1)), true);

					s.assertTrue(getText(driver, By.xpath(labelXPath1)).equals("No baggage"),

							"Displayed Fare Label Mismatch!");



					addLog("Checking the Baggage type Label Tool Tip", true);

					s.assertTrue(

							getAttribute(driver, By.xpath(labelXPath1), "original-title").contains("Check in")

									&& getAttribute(driver, By.xpath(labelXPath1), "original-title").contains("0 KG")

									&& getAttribute(driver, By.xpath(labelXPath1), "original-title").contains("Cabin")

									&& getAttribute(driver, By.xpath(labelXPath1), "original-title")

											.contains("Buy additional baggage during booking"),

							"No Baggage Tooltip Mismatch!");



					String toolTipCheckIn = getAttribute(driver, By.xpath(labelXPath1), "original-title")

							.split("Check in ")[1].toUpperCase().split(" KG")[0] + " KG";

					String toolTipCabin = getAttribute(driver, By.xpath(labelXPath1), "original-title")

							.split("Cabin ")[1].toUpperCase().split(" KG")[0] + " KG";

					;

					addLog("Check-In Baggage from ToolTip : " + toolTipCheckIn, true);

					addLog("Cabin Baggage from ToolTip : " + toolTipCabin, true);



					addLog("Clicking on the No baggage Radio Button", true);

					if (elementPresent(driver, By.xpath(

							"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withoutCabinBaggage']/input)["

									+ j + "]"),

							1)

							&& elementVisible(driver, By.xpath(

									"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withoutCabinBaggage']/input)["

											+ j + "]"),

									1)) {

						safeClick(driver, By.xpath(

								"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withoutCabinBaggage']/input)["

										+ j + "]"));



						int stops = 0;

						if (getText(driver, By.xpath(

								"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withoutCabinBaggage']/span[1]/../../../../../tr[2]/td[@class='duration'])["

										+ j + "]")).equalsIgnoreCase("non-stop")) {

							stops = 0;

						} else {

							stops = Integer.parseInt(getText(driver, By.xpath(

									"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withoutCabinBaggage']/span[1]/../../../../../tr[2]/td[@class='duration'])["

											+ j + "]")).split(" stop")[0]);

						}

						addLog("Number of Stops: " + stops, true);

						priceAndBaggageDetails.put("stops", "" + stops);



						addLog("Checking and Clicking on the Baggage Information Link", true);

						mouseHover(driver, By.xpath(

								"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withoutCabinBaggage']/span[1]/../../../../..)["

										+ j + "]"));

						safeClick(driver, By.linkText("Baggage Information"));



						for (int k = 1; k <= (stops + 1); k++) {

							elementPresent_Time(driver, By.cssSelector("span.baggageValue"), 10);

							String checkIn = getText(driver, By.xpath("(//ul/li/span[1]/strong)[" + k + "]"));

							String cabin = getText(driver, By.xpath("(//ul/li/span[2]/strong)[" + k + "]"));



							addLog("Check-In Baggage : " + checkIn, true);

							addLog("Cabin Baggage : " + cabin, true);

							s.assertTrue(checkIn.contains("KG/person") || checkIn.contains("kg/person"),

									"Check In Baggage details are not displayed");

							s.assertTrue(cabin.contains("KG/person") || cabin.contains("kg/person"),

									"Cabin Baggage Details are not Displayed");



							s.assertTrue(toolTipCabin.equalsIgnoreCase(cabin.split("/person")[0]),

									"Cabin Baggage Value Mismatch between Tooltip and Baggage information.");

							s.assertTrue(toolTipCheckIn.equalsIgnoreCase(checkIn.split("/person")[0]),

									"Check-In Baggage Value Mismatch between Tooltip and Baggage information.");



							priceAndBaggageDetails.put("Cabin" + k, toolTipCabin);

							priceAndBaggageDetails.put("CheckIn" + k, toolTipCheckIn);



						}



						safeClick(driver, By.xpath(

								"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withoutCabinBaggage']/span[2]/../../../../td/button)["

										+ j + "]"));

						bookButtonClicked = true;

						break;

					} else {

						s.fail("No Baggage Selection Combo Button Not Available!");

						s.assertAll();

					}

				}

			}

			if (noOfSoln == 0) {

				addLog("Bundled Fare Not Found!", true);

				s.fail("Bundled Fare Not Found!");

				s.assertAll();

			}

		} else if (bundledBaggage && !withOutBaggageFare) {

			int noOfSoln = driver.findElements(By.xpath(

					"//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[2]"))

					.size();

			for (int i = 0; i < noOfSoln; i++) {

				int j = i + 1;

				String labelXPath2 = "(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[2])["

						+ j + "]";

				if (elementPresent_Time(driver, By.xpath(labelXPath2), 1)) {

					bundledFareFound = true;

					addLog("Bundled Fare With Baggage Available.", true);

					priceAndBaggageDetails.put("fare", getText(driver, By.xpath(

							"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[1])["

									+ j + "]")).replace(getText(driver, By.xpath(

											"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[1]/span)["

													+ j + "]")),

											"").replaceAll(",", "").trim());

					addLog(priceAndBaggageDetails.get("fare"), true);



					addLog("Checking the Dispayed Label: " + getText(driver, By.xpath(labelXPath2)), true);

					s.assertTrue(getText(driver, By.xpath(labelXPath2)).equals("Baggage & more"),

							"Displayed Fare Label Mismatch!");



					addLog("Checking the Baggage type Label Tool Tip: "

							+ getAttribute(driver, By.xpath(labelXPath2), "original-title"), true);

					s.assertTrue(

							getAttribute(driver, By.xpath(labelXPath2), "original-title").contains("Check in ")

									&& getAttribute(driver, By.xpath(labelXPath2), "original-title").contains("Cabin "),

							"With Baggage Tooltip Mismatch!");



					String toolTipCheckIn = getAttribute(driver, By.xpath(labelXPath2), "original-title")

							.split("Check in ")[1].toUpperCase().split(" KG")[0] + " KG";

					String toolTipCabin = getAttribute(driver, By.xpath(labelXPath2), "original-title")

							.split("Cabin ")[1].toUpperCase().split(" KG")[0] + " KG";

					addLog("Check-In Baggage from ToolTip : " + toolTipCheckIn, true);

					addLog("Cabin Baggage from ToolTip : " + toolTipCabin, true);



					addLog("Clicking on the with baggage Radio Button", true);

					if (elementPresent(driver, By.xpath(

							"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/input)["

									+ j + "]"),

							1)

							&& elementVisible(driver, By.xpath(

									"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/input)["

											+ j + "]"),

									1)) {

						safeClick(driver, By.xpath(

								"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/input)["

										+ j + "]"));



						int stops = 0;

						if (getText(driver, By.xpath(

								"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[1]/../../../../../tr[2]/td[@class='duration'])["

										+ j + "]")).equalsIgnoreCase("non-stop")) {

							stops = 0;

						} else {

							stops = Integer.parseInt(getText(driver, By.xpath(

									"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[1]/../../../../../tr[2]/td[@class='duration'])["

											+ j + "]")).split(" stop")[0]);

						}

						addLog("Number of Stops: " + stops, true);

						priceAndBaggageDetails.put("stops", "" + stops);



						addLog("Checking the Baggage Information Link", true);

						mouseHover(driver, By.xpath(

								"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[1]/../../../../..)["

										+ j + "]"));

						safeClick(driver, By.linkText("Baggage Information"));



						for (int k = 1; k <= stops + 1; k++) {

							elementPresent_Time(driver, By.cssSelector("span.baggageValue"), 10);

							String checkIn = getText(driver, By.xpath("(//ul/li/span[1]/strong)[" + k + "]"));

							String cabin = getText(driver, By.xpath("(//ul/li/span[2]/strong)[" + k + "]"));



							addLog("Check-In Baggage : " + checkIn, true);

							addLog("Cabin Baggage : " + cabin, true);

							s.assertTrue(checkIn.contains("KG/person") || checkIn.contains("kg/person"),

									"Check In Baggage details are not displayed");

							s.assertTrue(cabin.contains("KG/person") || cabin.contains("kg/person"),

									"Cabin Baggage Details are not Displayed");



							s.assertTrue(toolTipCabin.equalsIgnoreCase(cabin.split("/person")[0]),

									"Cabin Baggage Value Mismatch between Tooltip and Baggage information.");

							s.assertTrue(toolTipCheckIn.equalsIgnoreCase(checkIn.split("/person")[0]),

									"Check-In Baggage Value Mismatch between Tooltip and Baggage information.");



							priceAndBaggageDetails.put("Cabin" + k, toolTipCabin);

							priceAndBaggageDetails.put("CheckIn" + k, toolTipCheckIn);

						}



						safeClick(driver, By.xpath(

								"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[2]/../../../../td/button)["

										+ j + "]"));

						bookButtonClicked = true;

						break;

					} else {

						s.fail("With Baggage Selection Combo Button Not Available!");

						s.assertAll();

					}

				}

			}

			if (noOfSoln == 0) {

				addLog("Bundled Fare Not Found!", true);

				s.fail("Bundled Fare Not Found!");

				s.assertAll();

			}

		} else if (!bundledBaggage) {

			boolean isInternational = driver.getCurrentUrl().contains("international");



			int noOfSoln = driver.findElements(By.xpath("//th[@id='BaggageBundlingTemplate']")).size();

			for (int i = 0; i < noOfSoln; i++) {

				int j = i + 1;



				String labelXPath3 = "(//th[@id='BaggageBundlingTemplate'])[" + j + "]";

				if (elementPresent_Time(driver, By.xpath(labelXPath3), 1)) {

					nonBundledFareFound = true;

					addLog("Fare Available.", true);

					priceAndBaggageDetails.put("Fare",

							getText(driver, By.xpath("(//th[@id='BaggageBundlingTemplate'])[" + j + "]")).replace(

									getText(driver, By.xpath("(//th[@id='BaggageBundlingTemplate']/span)[" + j + "]")),

									"").replaceAll(",", "").trim());

					addLog(priceAndBaggageDetails.get("fare"), true);



					int stops = 0;

					if (getText(driver, By.xpath(

							"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[1]/../../../../../tr[2]/td[@class='duration'])["

									+ j + "]")).equalsIgnoreCase("non-stop")) {

						stops = 0;

					} else {

						stops = Integer.parseInt(getText(driver, By.xpath(

								"(//td/button/../../th[@id='BaggageBundlingTemplate']/div/label[@id='withCabinBaggage']/span[1]/../../../../../tr[2]/td[@class='duration'])["

										+ j + "]")).split(" stop")[0]);

					}

					addLog("Number of Stops: " + stops, true);

					priceAndBaggageDetails.put("stops", "" + stops);



					addLog("Checking the Baggage Information Link", true);

					mouseHover(driver, By.xpath("(//td/button/../../..)[" + j + "]"));

					safeClick(driver, By.linkText("Baggage Information"));



					if (!isInternational) {

						for (int k = 1; k <= stops + 1; k++) {

							elementPresent_Time(driver, By.cssSelector("span.baggageValue"), 10);

							String checkIn = getText(driver, By.xpath("(//ul/li/span[1]/strong)[" + k + "]"));

							String cabin = getText(driver, By.xpath("(//ul/li/span[2]/strong)[" + k + "]"));



							addLog("Check-In Baggage : " + checkIn, true);

							addLog("Cabin Baggage : " + cabin, true);

							s.assertTrue(checkIn.contains("KG/person") || checkIn.contains("kg/person"),

									"Check In Baggage details are not displayed");

							s.assertTrue(cabin.contains("KG/person") || cabin.contains("kg/person"),

									"Cabin Baggage Details are not Displayed");



							priceAndBaggageDetails.put("Cabin" + k, cabin);

							priceAndBaggageDetails.put("CheckIn" + k, checkIn);

						}

					} else {

						String checkIn = "";

						String cabin = "";



						for (int k = 1; k <= stops + 1; k++) {

							elementPresent_Time(driver, By.cssSelector("span.baggageValue"), 10);

							String airline = getText(driver, By.xpath("(//td/button/../../..)[" + j

									+ "]/../../div/ul/li/div[@class='baggage']/ul/li/div/span"));

							if (airline.equalsIgnoreCase("Spicejet") || airline.equalsIgnoreCase("Indigo")

									|| airline.equalsIgnoreCase("air asia")

									|| airline.equalsIgnoreCase("airasia_india")) {

								checkIn = getText(driver, By.xpath("((//td/button/../../..)[" + j

										+ "]/../../div/ul/li/div[@class='baggage']/ul/li/ul)[" + k + "]"));

								cabin = getText(driver, By.xpath("((//td/button/../../..)[" + j

										+ "]/../../div/ul/li/div[@class='baggage']/ul/li/ul)[" + k + "]"));

								s.assertTrue(checkIn.contains("Free check-in") || checkIn.contains("Free cabin"),

										"Check In Baggage details are not displayed");

								s.assertTrue(cabin.contains("Free check-in") || cabin.contains("Free cabin"),

										"Cabin Baggage Details are not Displayed");

							} else {

								checkIn = getText(driver, By.xpath("(//ul/li/span[1]/strong)[" + k + "]"));

								cabin = getText(driver, By.xpath("(//ul/li/span[2]/strong)[" + k + "]"));

								s.assertTrue(checkIn.contains("KG/person") || checkIn.contains("kg/person"),

										"Check In Baggage details are not displayed");

								s.assertTrue(cabin.contains("KG/person") || cabin.contains("kg/person"),

										"Cabin Baggage Details are not Displayed");

							}



							addLog("Check-In Baggage : " + checkIn, true);

							addLog("Cabin Baggage : " + cabin, true);



							priceAndBaggageDetails.put("Cabin" + k, cabin);

							priceAndBaggageDetails.put("CheckIn" + k, checkIn);

						}

					}



					safeClick(driver, By.xpath("(//th[@id='BaggageBundlingTemplate']/../../tr/td/button)[" + j + "]"));

					bookButtonClicked = true;

					break;

				}

			}

		}



		if (bundledBaggage) {

			s.assertTrue(bundledFareFound, "Bundled Fare Solution Not Found!");

		} else {

			s.assertTrue(nonBundledFareFound, "Solution Not Found!");

		}



		s.assertTrue(bookButtonClicked, "Book Button Not Clicked!");

		s.assertAll();



		return priceAndBaggageDetails;

	}



	/*

	 * Author: prashanthk.cleartrip.com This method takes in the baggage details and

	 * fare details from SRP as a hash map and then checks the baggage details

	 * presented in Book step1, clicks on More Info Link and then verifies the

	 * details on the pop up.

	 */

	public void verifyItenaryBaggageInfo_OW(RemoteWebDriver driver, HashMap<String, String> priceAndBaggageDetails)

			throws Exception {

		SoftAssert s = new SoftAssert();

		int stops = Integer.parseInt(priceAndBaggageDetails.get("stops"));



		for (int i = 0; i < stops + 1; i++) {

			int j = i + 1;



			boolean isInternational = driver.getCurrentUrl().contains("international");

			String airline = getText(driver, By.xpath("(//div[contains(@class, 'airlineName')]/span)[" + j + "]"));

			addLog("Airline for leg " + j + " is: " + airline, true);



			waitForElement(driver, 60, By.xpath("(//li[@class='baggageInfo']/small[2])[" + j + "]"));

			String bookStepCheckIn = getText(driver, By.xpath("(//li[@class='baggageInfo']/small[2])[" + j + "]"));

			if (bookStepCheckIn.contains("None"))

				bookStepCheckIn = "0 KG / PERSON";



			addLog("Checking Check-IN Baggage Info for leg " + j + ": " + bookStepCheckIn, true);

			if (isInternational && (airline.equalsIgnoreCase("Spicejet") || airline.equalsIgnoreCase("Indigo")

					|| airline.equalsIgnoreCase("air asia") || airline.equalsIgnoreCase("airasia_india"))) {

				s.assertTrue(

						bookStepCheckIn.toUpperCase().contains(("Free cabin").toUpperCase())

								|| bookStepCheckIn.toUpperCase().contains(("Free check-in").toUpperCase()),

						"Check In Baggage info not present for leg " + j);

				s.assertTrue(priceAndBaggageDetails.get("CheckIn" + j).contains(bookStepCheckIn),

						"Book Step Checkin Baggage Mismatch for leg " + j + "Expected: "

								+ priceAndBaggageDetails.get("CheckIn" + j) + "Actual" + bookStepCheckIn);

			} else {

				s.assertTrue(bookStepCheckIn.toUpperCase().contains("KG / PERSON"),

						"Check In Baggage info not present for leg " + j);

				int srpCheckIn = Integer

						.parseInt(priceAndBaggageDetails.get("CheckIn" + j).toUpperCase().split(" ")[0].trim());

				int bkStepCheckIn = Integer

						.parseInt(bookStepCheckIn.toUpperCase().split(": ")[1].replace("KG / PERSON", "").trim());

				s.assertTrue(srpCheckIn == bkStepCheckIn, "Book Step Checkin Baggage Mismatch for leg " + j

						+ "Expected: " + srpCheckIn + "Actual" + bkStepCheckIn);

			}



			waitForElement(driver, 60, By.xpath("(//li[@class='baggageInfo']/small[3])[" + j + "]"));

			String bookStepCabin = getText(driver, By.xpath("(//li[@class='baggageInfo']/small[3])[" + j + "]"));



			addLog("Checking Cabin Baggage Info for leg " + j + ": " + bookStepCabin, true);

			if (isInternational && (airline.equalsIgnoreCase("Spicejet") || airline.equalsIgnoreCase("Indigo")

					|| airline.equalsIgnoreCase("air asia") || airline.equalsIgnoreCase("airasia_india"))) {

				s.assertTrue(

						bookStepCabin.toUpperCase().contains(("Free cabin").toUpperCase())

								|| bookStepCabin.toUpperCase().contains(("Free check-in").toUpperCase()),

						"Cabin Baggage info not present for leg " + j);

				s.assertTrue(priceAndBaggageDetails.get("Cabin" + j).contains(bookStepCabin),

						"Book Step Cabin Baggage Mismatch for leg " + j + "Expected: "

								+ priceAndBaggageDetails.get("Cabin" + j) + "Actual" + bookStepCabin);



			} else {

				s.assertTrue(bookStepCabin.toUpperCase().contains("KG / PERSON"),

						"Cabin Baggage info not present for leg " + j);

				int srpCabin = Integer

						.parseInt(priceAndBaggageDetails.get("Cabin" + j).toUpperCase().split(" ")[0].trim());

				int bkStepCabin = Integer

						.parseInt(bookStepCabin.toUpperCase().split(": ")[1].replace("KG / PERSON", "").trim());

				s.assertTrue(srpCabin == bkStepCabin, "Book Step Cabin Baggage Mismatch for leg " + j + "Expected: "

						+ srpCabin + "Actual" + bkStepCabin);

				s.assertTrue(

						priceAndBaggageDetails.get("Cabin" + j).toUpperCase().split(" ")[0].trim()

								.contains(bookStepCabin.toUpperCase().split(": ")[1].replace("KG / PERSON", "").trim()),

						"Book Step Cabin Baggage Mismatch for leg " + j);

			}



			waitForElement(driver, 60, By.xpath("(///li[@class='baggageInfo']/a)[" + j + "]"));

			addLog("Checking Baggage Info Link. Link Name = "

					+ getText(driver, By.xpath("(//li[@class='baggageInfo']/a)[" + j + "]")), true);

			Assert.assertTrue(

					getText(driver, By.xpath("(//li[@class='baggageInfo']/a)[" + j + "]")).equals("more info"),

					"Baggage info not present for leg " + j);



			String currentWindow = driver.getWindowHandle();

			addLog("Checking Baggage Info in Popup", true);

			safeClick(driver, By.xpath("(//li[@class='baggageInfo']/a)[" + j + "]"));



			Thread.sleep(10000);



			/*

			 * Done

			 */



			driver.switchTo().frame("modal_window");

			List<WebElement> we = driver.findElements(By.xpath("//td[@class='keyValue']/p"));

			addLog("Popup Info" + j + ": " + we.get(i).getText(), true);



			boolean checkInFound = false;

			boolean cabinFound = false;



			if (isInternational && (airline.equalsIgnoreCase("Spicejet") || airline.equalsIgnoreCase("Indigo")

					|| airline.equalsIgnoreCase("air asia") || airline.equalsIgnoreCase("airasia_india"))) {

				if (we.get(i).getText().contains("Free")) {

					checkInFound = true;

					s.assertEquals(we.get(i).getText().toUpperCase().split(" KG")[0],

							priceAndBaggageDetails.get("CheckIn" + j).toUpperCase().split(" ")[0],

							"Popup Window Checkin Baggage details mismatch!" + "Expected: "

									+ priceAndBaggageDetails.get("CheckIn" + j).toUpperCase().split(" ")[0] + "Actual: "

									+ we.get(i).getText().toUpperCase().split(" KG")[0]);

				}

				if (we.get(i).getText().contains("KG cabin baggage")) {

					cabinFound = true;

					// s.assertEquals(we.get(i).getText().toUpperCase().split(" KG")[0],

					// priceAndBaggageDetails.get("Cabin"+j).toUpperCase().split(" ")[0],

					// "Popup Window Cabin Baggage details mismatch!" +

					// "Expected: " + + "Actual: " + );

				}

			} else {

				if (we.get(i).getText().contains("KG check-in baggage")) {

					checkInFound = true;

					// s.assertEquals(we.get(i).getText().toUpperCase().split(" KG")[0],

					// priceAndBaggageDetails.get("CheckIn"+j).toUpperCase().split(" ")[0],

					// "Popup Window Checkin Baggage details mismatch!" +

					// "Expected: " + + "Actual: " + );

				}

				if (we.get(i).getText().contains("KG cabin baggage")) {

					cabinFound = true;

					// s.assertEquals(we.get(i).getText().toUpperCase().split(" KG")[0],

					// priceAndBaggageDetails.get("Cabin"+j).toUpperCase().split(" ")[0],

					// "Popup Window Cabin Baggage details mismatch!" +

					// "Expected: " + + "Actual: " + );

				}

			}



			s.assertTrue(checkInFound, "Popup Window Checkin Baggage details not Found! for leg " + j);

			s.assertTrue(cabinFound, "Popup Window Cabin Baggage details mismatch! for leg " + j);



			driver.switchTo().window(currentWindow);

			safeClick(driver, getObject("AirCom_BookStep_Baggage_Info_Popup_Close"));

		}



		s.assertAll();

	}



	/*

	 * Added By: Prashanth.k@cleartrip.com This method fills GST details in the

	 * travelers details book step

	 */

	public void fillGstDetails(RemoteWebDriver driver, boolean useGST, boolean useNonDefaultState) throws Exception {

		String curUrl = driver.getCurrentUrl();

		if (curUrl.contains(".sa") || curUrl.contains(".ae") || curUrl.contains("me.") || curUrl.contains("qa.")

				|| curUrl.contains("bh.") || curUrl.contains("kw.") || curUrl.contains("om.")) {

			if (elementPresent_Time(driver, getObject("AirCom_BookStep3_GST_CheckBox"), 3)) {

				Assert.fail("GST details check box present! Should not be present here!");

			}

			if (elementPresent_Time(driver, getObject("AirCom_BookStep3_GST_State_Select"), 3)) {

				Assert.fail("State Selection drop down present! Should not be present here!");

			}

			return;

		}



		if (useGST) {

			// Assert.assertFalse(driver.findElement(getObject("AirCom_BookStep3_GST_CheckBox")).isSelected(),

			// "Use GST check box is clicked by default in travellers details book step!");

			if (driver.findElement(getObject("AirCom_BookStep3_GST_CheckBox")).isSelected())

				safeClick(driver, getObject("AirCom_BookStep3_GST_CheckBox"));



			safeClick(driver, getObject("AirCom_BookStep3_GST_CheckBox"));



			if (getText(driver, getObject("AirCom_Bookstep1_Airline_Name")).equalsIgnoreCase("SpiceJet")) {

				safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Spicejet"));

			} else if (getText(driver, getObject("AirCom_Bookstep1_Airline_Name")).equalsIgnoreCase("Indigo")) {

				safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Indigo"));

			} else if (getText(driver, getObject("AirCom_Bookstep1_Airline_Name")).equalsIgnoreCase("GoAir")) {

				safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Goair"));

			} else if (getText(driver, getObject("AirCom_Bookstep1_Airline_Name")).equalsIgnoreCase("Airasia_india")

					|| getText(driver, getObject("AirCom_Bookstep1_Airline_Name")).equalsIgnoreCase("Air Asia")) {

				safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Airasia"));

			} else if (getText(driver, getObject("AirCom_Bookstep1_Airline_Name")).equalsIgnoreCase("Air India")) {

				safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Airindia"));

			} else if (getText(driver, getObject("AirCom_Bookstep1_Airline_Name")).equalsIgnoreCase("Jet")) {

				safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Jet"));

			} else if (getText(driver, getObject("AirCom_Bookstep1_Airline_Name")).equalsIgnoreCase("Vistara")) {

				safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Vistara"));

			} else {

				addLog("Airline Specific test GST Code not available! Using SpiceJet", true);

				safeType(driver, getObject("AirCom_BookStep3_GST_Number"), dataFile.value("GST_Number_Spicejet"));

			}



			safeType(driver, getObject("AirCom_BookStep3_GST_Holder_Name"), dataFile.value("GST_Holder_Name"));

			// safeType(driver,

			// getObject("AirCom_BookStep3_GST_Holder_Address"),

			// dataFile.value("GST_Holder_Address"));

		}



		/*

		 * else { if

		 * (driver.findElement(getObject("AirCom_BookStep3_GST_CheckBox")).isSelected())

		 * safeClick(driver, getObject("AirCom_BookStep3_GST_CheckBox")); }

		 */

	}



	/*

	 * Added By: prashanth.k@cleartrip.com This method collects the fare breakup

	 * from the first OW/RT SRP search result

	 */

	public HashMap<String, String> getSrpFareDetails(RemoteWebDriver driver, HashMap<String, String> fareDetails,

			String tripType) throws Exception {

		if (tripType.equalsIgnoreCase("OneWay")) {

			String srpPrice = driver.findElements(getObject("AirCom_SRP_Flight_Prices_OW")).get(0).getText().trim()

					.split("Rs.")[1].replaceAll(",", "");

			fareDetails.put("srpPrice", srpPrice);

			// addLog("SRP price: " + srpPrice, true);



			addLog("Clicking on Fare Details link and Tax Breakup Link", true);

			WebElement firstResult = driver.findElements(getObject("AirCom_SRP_Flight_Prices_OW")).get(0);

			new Actions(driver).moveToElement(firstResult).build().perform();

			safeClick(driver, getObject("AirCom_SRP_OW_FareRulesLink"));

			safeClick(driver, getObject("AirCom_SRP_FareRule_TaxesLink"));



			addLog("Collecting Fare Breakup", true);

			List<WebElement> keys = driver.findElements(getObject("AirCom_SRP_FareBreakup_Keys"));

			List<WebElement> values = driver.findElements(getObject("AirCom_SRP_FareBreakup_Values"));



			if (keys.size() != values.size())

				addLog("Fare Breakup Keys and value Size mismatch!", true);



			for (int i = 0; i < keys.size(); i++) {

				String key = keys.get(i).getText().trim();

				if (key.equalsIgnoreCase("Taxes & fees"))

					continue;

				String value = values.get(i).getText().trim().split("Rs.")[1].replaceAll(",", "");

				fareDetails.put("srp" + key, value);

				// addLog("srp"+key + " : " + value, true);

			}

		} else if (tripType.equalsIgnoreCase("RoundTrip")) {

			String srpPrice = driver.findElement(getObject("AirCom_SRP_Roundtrip_TotalAmount")).getText().trim()

					.split("Rs.")[1].replaceAll(",", "");

			fareDetails.put("srpPrice", srpPrice);

			// addLog("SRP price: " + srpPrice, true);



			addLog("Clicking on Fare Details link and Tax Breakup Link", true);

			safeClick(driver, getObject("Aircom_SRP_RT_FareRulesLink"));

			safeClick(driver, getObject("AirCom_SRP_FareRule_TaxesLink"));



			addLog("Collecting Fare Breakup", true);

			List<WebElement> keys = driver.findElements(getObject("AirCom_SRP_FareBreakup_Keys"));

			List<WebElement> values = driver.findElements(getObject("AirCom_SRP_FareBreakup_Values"));



			if (keys.size() != values.size())

				addLog("Fare Breakup Keys and value Size mismatch!", true);



			for (int i = 0; i < keys.size(); i++) {

				String key = keys.get(i).getText().trim();

				if (key.equalsIgnoreCase("Taxes & fees"))

					continue;

				String value = values.get(i).getText().trim().split("Rs.")[1].replaceAll(",", "");

				fareDetails.put("srp" + key, value);

				// addLog("srp"+key + " : " + value, true);

			}

		}

		return fareDetails;

	}



	/*

	 * Added By: prashanth.k@cleartrip.com This method collects the fare breakup

	 * from Book Step1

	 */

	public HashMap<String, String> getConfirmationPageFareDetails(RemoteWebDriver driver,

			HashMap<String, String> fareDetails, String tripType) throws Exception {



		addLog("Collecting Fare Breakup", true);

		List<WebElement> keys = driver.findElements(getObject("AirCom_ConfirmationPage_FareBreakup_Keys"));

		List<WebElement> values = driver.findElements(getObject("AirCom_ConfirmationPage_FareBreakup_Values"));

		for (int i = 0; i < keys.size(); i++) {

			String key = keys.get(i).getText().trim();

			if (key.equalsIgnoreCase("Taxes & fees"))

				continue;

			String value = values.get(i).getText().trim().split("RS.")[1].replaceAll(",", "").trim();

			fareDetails.put("cp" + key, value);

			// addLog("cp"+key + " : " + value, true);

		}



		safeClick(driver, getObject("AirCom_Confirmation_TaxBreakupLink"));



		addLog("Collecting Tax Breakup", true);

		List<WebElement> keys1 = driver.findElements(getObject("AirCom_ConfirmationPage_TaxBreakup_Keys"));

		List<WebElement> values1 = driver.findElements(getObject("AirCom_ConfirmationPage_TaxBreakup_Values"));

		for (int i = 0; i < keys1.size(); i++) {

			String key = keys1.get(i).getText().trim();

			if (key.equalsIgnoreCase("Taxes & fees"))

				continue;

			String value = values1.get(i).getText().trim().split("RS.")[1].replaceAll(",", "").trim();

			fareDetails.put("cp" + key, value);

			// addLog("cp"+key + " : " + value, true);

		}



		return fareDetails;

	}



	/*

	 * Added By: prashanth.k@cleartrip.com This method populates the hash map with

	 * the required parameters picked from the trip.xml Note: This might work only

	 * on QA2 env. If you have multiple instances of the parameter then use the

	 * unique parent parameter and then filter on that later.

	 */

	public HashMap<String, String> getTripXmlPrams(String tripID, HashMap<String, String> xmlDetails) throws Exception {

		DefaultHttpClient client = new DefaultHttpClient();

		HttpGet get = new HttpGet(new URI("http://172.17.12.231:9080/trips/" + tripID));

		HttpResponse response = client.execute(get);

		// client.close();



		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer sb = new StringBuffer();

		String str = "";

		while ((str = br.readLine()) != null) {

			sb.append(str);

		}



		// System.out.println("--------"+sb);

		Set<String> keys = xmlDetails.keySet();

		for (String key : keys) {

			String sb1 = sb.toString();

			sb1 = sb1.split("<" + key + ">")[1].split("</" + key + ">")[0];



			addLog(sb1, true);

			xmlDetails.put(key, sb1);

		}



		return xmlDetails;

	}



	/*

	 * Added By: prashanth.k@cleartrip.com This method populates the hash map with

	 * the required parameters picked from the trip.xml. You will need to supply the

	 * opening and closing xml tags and the hash map key to tore the result under.

	 * You can pass only one set of opening tag and closing tab at a time. Note:

	 * This might work only on QA2 env. If you have multiple instances of the

	 * parameter then use the unique parent parameter and then filter on that later.

	 */

	public HashMap<String, String> getTripXmlPrams2(String tripID, HashMap<String, String> xmlDetails,

			String openingTag, String closingTag, String key) throws Exception {

		DefaultHttpClient client = new DefaultHttpClient();

		HttpGet get = new HttpGet(new URI("http://172.17.12.187:9080/trips/" + tripID));

		HttpResponse response = client.execute(get);

		// client.close();



		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer sb = new StringBuffer();

		String str = "";

		while ((str = br.readLine()) != null) {

			sb.append(str);

		}



		String sb1 = sb.toString();

		sb1 = sb1.split("<" + openingTag + ">")[1].split("</" + closingTag + ">")[0];

		addLog(sb1, true);

		xmlDetails.put(key, sb1);



		return xmlDetails;

	}



	/*

	 * Added By: prashanth.k@cleartrip.com This method returns a Hash Map with the

	 * required GST data extracted from tripXML

	 */

	public HashMap<String, String> getGstDataFromTripXML(RemoteWebDriver driver, String tripID, boolean gstDefault)

			throws Exception {

		addLog("Fetching GST Details from Trip XML for trip ID: " + tripID, true);

		HashMap<String, String> xmlDetails = new HashMap<String, String>();

		HashMap<String, String> gstDetails = new HashMap<String, String>();



		String tmp;

		String id;

		String tripId;

		String gstNumber;

		String gstHolderName;

		String gstHolderAddress;

		String gstHolderStateName;

		String gstHolderStateCode;

		String createdAt;

		String updatedAt;



		/*

		 * String tripID = getText(driver, getObject("AirCom_Confirmation_TripID"));

		 * addLog("Getting GST details from trip XML for TripId: " + tripID,

		 * true);

		 */



		if (!gstDefault) {

			// GST details entered in UI

			xmlDetails.put("gst-detail", "");

			xmlDetails = getTripXmlPrams(tripID, xmlDetails);

			tmp = xmlDetails.get("gst-detail");

			id = tmp.split("<id>")[1].split("</trip-id>")[0];

			tripId = tmp.split("</trip-id>")[1].split("</trip-id>")[0];

			gstNumber = tmp.split("<gst-number>")[1].split("</gst-number>")[0];

			gstHolderName = tmp.split("<gst-holder-name>")[1].split("</gst-holder-name>")[0];

			gstHolderAddress = tmp.split("<gst-holder-address>")[1].split("</gst-holder-address>")[0];

			System.out.println(gstHolderAddress);

			gstHolderStateName = tmp.split("<gst-holder-statename>")[1].split("</gst-holder-statename>")[0];

			gstHolderStateCode = tmp.split("<gst-holder-statecode>")[1].split("</gst-holder-statecode>")[0];

			createdAt = tmp.split("<created-at>")[1].split("</created-at>")[0];

			updatedAt = tmp.split("<updated-at>")[1].split("</updated-at>")[0];

		} else {

			// GST details entered in UI

			xmlDetails.put("gst-detail", "");

			xmlDetails = getTripXmlPrams(tripID, xmlDetails);

			tmp = xmlDetails.get("gst-detail");

			id = tmp.split("<id>")[1].split("</trip-id>")[0];

			tripId = tmp.split("</trip-id>")[1].split("</trip-id>")[0];

			gstNumber = tmp.split("<gst-number nil=")[1].split("></gst-number>")[0].replaceAll("\"", "");

			gstHolderName = tmp.split("<gst-holder-name nil=")[1].split("></gst-holder-name>")[0].replaceAll("\"", "");

			gstHolderAddress = tmp.split("<gst-holder-address nil=")[1].split("></gst-holder-address>")[0]

					.replaceAll("\"", "");

			gstHolderStateName = tmp.split("<gst-holder-statename>")[1].split("</gst-holder-statename>")[0];

			gstHolderStateCode = tmp.split("<gst-holder-statecode>")[1].split("</gst-holder-statecode>")[0];

			createdAt = tmp.split("<created-at>")[1].split("</created-at>")[0];

			updatedAt = tmp.split("<updated-at>")[1].split("</updated-at>")[0];

		}



		gstDetails.put("id", id);

		gstDetails.put("tripId", tripId);

		gstDetails.put("gstNumber", gstNumber);

		gstDetails.put("gstHolderName", gstHolderName);

		gstDetails.put("gstHolderAddress", gstHolderAddress);

		gstDetails.put("gstHolderStateName", gstHolderStateName);

		gstDetails.put("gstHolderStateCode", gstHolderStateCode);

		gstDetails.put("createdAt", createdAt);

		gstDetails.put("updatedAt", updatedAt);



		return gstDetails;

	}



	/*

	 * Added By: prashanth.k@cleartrip.com This method returns a Hash Map with the

	 * required GST data extracted from tripXML

	 */

	public HashMap<String, String> getGSTFareFromTripXML(RemoteWebDriver driver, String tripID) throws Exception {

		addLog("Fetching GST payment from Trip XML for trip ID: " + tripID, true);

		HashMap<String, String> xmlDetails1 = new HashMap<String, String>();

		HashMap<String, String> xmlDetails2 = new HashMap<String, String>();

		HashMap<String, String> amountDetails = new HashMap<String, String>();

		List<String> pricingInfoBlocks = new LinkedList<String>();



		xmlDetails1.put("trip-ref", "");

		xmlDetails1.put("booking-status", "");

		xmlDetails1.put("gst-charged-by-cleartrip", "");

		xmlDetails1.put("gst-charged-by-supplier", "");

		xmlDetails2.put("pricing-info-list type=\"array\"", "");



		xmlDetails1 = getTripXmlPrams(tripID, xmlDetails1);

		xmlDetails2 = getTripXmlPrams2(tripID, xmlDetails2, "pricing-info-list type=\"array\"", "pricing-info-list",

				"pricing-info-list type=\"array\"");



		String tmp1 = xmlDetails1.get("trip-ref");

		String tmp2 = xmlDetails1.get("booking-status");

		String tmp3 = xmlDetails1.get("gst-charged-by-cleartrip");

		String tmp4 = xmlDetails1.get("gst-charged-by-supplier");

		String tmp5 = xmlDetails2.get("pricing-info-list type=\"array\"");



		addLog("Extractring Pricing info Blocks. Length = " + tmp5.split("<pricing-info>").length, true);

		int j = 0;

		for (int i = 1; i < tmp5.split("<pricing-info>").length; i++) {

			pricingInfoBlocks.add(tmp5.split("<pricing-info>")[i].split("</pricing-info>")[0]);

			addLog("Block " + i + ": " + pricingInfoBlocks.get(j++), true);

		}



		for (int i = 0; i < pricingInfoBlocks.size(); i++) {

			amountDetails.put("seq-no", pricingInfoBlocks.get(i).split("<seq-no>")[1].split("</seq-no>")[0].trim());

			String seqno = amountDetails.get("seq-no");

			amountDetails.put("total-tax-cgst" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-tax-cgst>")[1].split("</total-tax-cgst>")[0].trim());

			amountDetails.put("total-tax-sgst" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-tax-sgst>")[1].split("</total-tax-sgst>")[0].trim());

			amountDetails.put("total-tax-igst" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-tax-igst>")[1].split("</total-tax-igst>")[0].trim());

			amountDetails.put("total-tax-utgst" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-tax-utgst>")[1].split("</total-tax-utgst>")[0].trim());

			amountDetails.put("total-tax-ct-cgst" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-tax-ct-cgst>")[1].split("</total-tax-ct-cgst>")[0].trim());

			amountDetails.put("total-tax-ct-sgst" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-tax-ct-sgst>")[1].split("</total-tax-ct-sgst>")[0].trim());

			amountDetails.put("total-tax-ct-igst" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-tax-ct-igst>")[1].split("</total-tax-ct-igst>")[0].trim());

			amountDetails.put("total-tax-ct-utgst" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-tax-ct-utgst>")[1].split("</total-tax-ct-utgst>")[0].trim());



			amountDetails.put("total-fare" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-fare>")[1].split("</total-fare>")[0].trim());

			amountDetails.put("total-fee-gw" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-fee-gw>")[1].split("</total-fee-gw>")[0].trim());

			amountDetails.put("total-markup" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-markup>")[1].split("</total-markup>")[0].trim());

			amountDetails.put("total-discount" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<total-discount>")[1].split("</total-discount>")[0].trim());

			amountDetails.put("net-taxable-value" + ":" + seqno,

					pricingInfoBlocks.get(i).split("<net-taxable-value>")[1].split("</net-taxable-value>")[0].trim());

		}



		addLog("trip-ref: " + tmp1, true);

		addLog("booking-status: " + tmp2, true);

		addLog("gst-charged-by-cleartrip: " + tmp3, true);

		addLog("gst-charged-by-supplier: " + tmp4, true);

		addLog("pricing-info-list type=\"array\": " + tmp5, true);



		double totalCgst = 0.0;

		double totalSgst = 0.0;

		double totalIgst = 0.0;

		double totalUTgst = 0.0;

		double totalCtSgst = 0.0;

		double totalCtCgst = 0.0;

		double totalCtIgst = 0.0;

		double totalCtUtgst = 0.0;

		double totalFare = 0.0;

		double totalFeeGw = 0.0;

		double totalMarkup = 0.0;

		double totalDiscount = 0.0;

		double totalNetTaxableValue = 0.0;



		addLog("Seq: " + amountDetails.get("seq-no"), true);

		for (int i = 1; i <= Integer.parseInt(amountDetails.get("seq-no")); i++) {

			addLog("total-tax-cgst: " + amountDetails.get("total-tax-cgst:" + i), true);

			addLog("total-tax-sgst: " + amountDetails.get("total-tax-sgst:" + i), true);

			addLog("total-tax-igst: " + amountDetails.get("total-tax-igst:" + i), true);

			addLog("total-tax-utgst: " + amountDetails.get("total-tax-utgst:" + i), true);

			addLog("total-tax-ct-cgst: " + amountDetails.get("total-tax-ct-cgst:" + i), true);

			addLog("total-tax-ct-sgst: " + amountDetails.get("total-tax-ct-sgst:" + i), true);

			addLog("total-tax-ct-igst: " + amountDetails.get("total-tax-ct-igst:" + i), true);

			addLog("total-tax-ct-utgst: " + amountDetails.get("total-tax-ct-utgst:" + i), true);



			addLog("total-fare: " + amountDetails.get("total-fare:" + i), true);

			addLog("total-fee-gw: " + amountDetails.get("total-fee-gw:" + i), true);

			addLog("total-markup: " + amountDetails.get("total-markup:" + i), true);

			addLog("total-discount: " + amountDetails.get("total-discount:" + i), true);

			addLog("net-taxable-value: " + amountDetails.get("net-taxable-value:" + i), true);



			totalCgst += Double.parseDouble(amountDetails.get("total-tax-cgst:" + i));

			totalSgst += Double.parseDouble(amountDetails.get("total-tax-sgst:" + i));

			totalIgst += Double.parseDouble(amountDetails.get("total-tax-igst:" + i));

			totalUTgst += Double.parseDouble(amountDetails.get("total-tax-utgst:" + i));

			totalCtSgst += Double.parseDouble(amountDetails.get("total-tax-ct-cgst:" + i));

			totalCtCgst += Double.parseDouble(amountDetails.get("total-tax-ct-sgst:" + i));

			totalCtIgst += Double.parseDouble(amountDetails.get("total-tax-ct-igst:" + i));

			totalCtUtgst += Double.parseDouble(amountDetails.get("total-tax-ct-utgst:" + i));



			totalFare += Double.parseDouble(amountDetails.get("total-fare:" + i));

			totalFeeGw += Double.parseDouble(amountDetails.get("total-fee-gw:" + i));

			totalMarkup += Double.parseDouble(amountDetails.get("total-markup:" + i));

			totalDiscount += Double.parseDouble(amountDetails.get("total-discount:" + i));

			totalNetTaxableValue += Double.parseDouble(amountDetails.get("net-taxable-value:" + i));



		}



		amountDetails.put("totalCgst", String.valueOf(totalCgst));

		amountDetails.put("totalSgst", String.valueOf(totalSgst));

		amountDetails.put("totalIgst", String.valueOf(totalIgst));

		amountDetails.put("totalUTgst", String.valueOf(totalUTgst));

		amountDetails.put("totalCtSgst", String.valueOf(totalCtSgst));

		amountDetails.put("totalCtCgst", String.valueOf(totalCtCgst));

		amountDetails.put("totalCtIgst", String.valueOf(totalCtIgst));

		amountDetails.put("totalCtUtgst", String.valueOf(totalCtUtgst));



		amountDetails.put("totalFare", String.valueOf(totalFare));

		amountDetails.put("totalFeeGw", String.valueOf(totalFeeGw));

		amountDetails.put("totalMarkup", String.valueOf(totalMarkup));

		amountDetails.put("totalDiscount", String.valueOf(totalDiscount));

		amountDetails.put("totalNetTaxableValue", String.valueOf(totalNetTaxableValue));



		/*

		 * addLog("totalCgst: " + amountDetails.get("totalCgst"), true);

		 * addLog("totalSgst: " + amountDetails.get("totalSgst"), true);

		 * addLog("totalIgst: " + amountDetails.get("totalIgst"), true);

		 * addLog("totalUTgst: " + amountDetails.get("totalUTgst"), true);

		 * addLog("totalCtCgst: " + amountDetails.get("totalCtCgst"), true);

		 * addLog("totalCtSgst: " + amountDetails.get("totalCtSgst"), true);

		 * addLog("totalCtIgst: " + amountDetails.get("totalCtIgst"), true);

		 * addLog("totalCtUtgst: " + amountDetails.get("totalCtUtgst"), true);

		 *

		 * addLog("totalFare: " + amountDetails.get("totalFare"), true);

		 * addLog("totalFeeGw: " + amountDetails.get("totalFeeGw"), true);

		 * addLog("totalMarkup: " + amountDetails.get("totalMarkup"), true);

		 * addLog("totalDiscount: " + amountDetails.get("totalDiscount"), true);

		 * addLog("totalNetTaxableValue: " +

		 * amountDetails.get("totalNetTaxableValue"), true);

		 */



		return amountDetails;



	}



	/* Method to get Itinerary id from page source for B2B domains - Anil */

	public void getB2BItineraryId1(RemoteWebDriver driver) {

		String createItineararyPageSource = driver.getPageSource();

		String iti = createItineararyPageSource.split("var itineraryId =")[1].split(";")[0];

		addLog("Itinearary id = " + iti.substring(2, iti.length() - 1), true);



	}



	/*

	 * Added By: anil.patil@cleartrip.com This method returns Itinerary id from page

	 * source for B2B domains

	 */

	public void getB2BItineraryId(RemoteWebDriver driver) {



		Parser parser = new Parser();

		// <input type="hidden" name="itineraryId"

		// value="685c84560c-3c01-4352-906b-2897fcba2b17" disabled="" />

		HasAttributeFilter filter = new HasAttributeFilter("name", "itineraryId");

		try {

			parser.setResource(driver.getCurrentUrl());

			NodeList list = parser.parse(filter);

			Node node = (Node) list.elementAt(0);

			if (node instanceof InputTag) {

				InputTag input = (InputTag) node;

				String ItineararyId = input.getAttribute("value");

				addLog("Itinearary id = " + ItineararyId, true);

			}



		} catch (ParserException e) {

			e.printStackTrace();

		}

	}



	/*

	 * Added By: prashanth.k@cleartrip.com This method does a connector search for

	 * the submitted URL

	 */

	public void connectorSearch(WebDriver driver, String searchUrl) throws Exception {

		String part1;

		String connetorSearchURL;



		if (searchUrl.contains("intl=y")) {

			part1 = searchUrl.split("results")[1].split("intl=y")[0];

			connetorSearchURL = "http://172.17.12.35:9080/airservice/search" + part1 + "intl=y&"

					+ "src=connector&scr=SAR&sct=SA";

		} else {

			part1 = searchUrl.split("results")[1].split("intl=n")[0];

			connetorSearchURL = "http://172.17.12.35:9080/airservice/search" + part1 + "intl=n&"

					+ "src=connector&scr=SAR&sct=SA";

		}

		addLog(connetorSearchURL);

		driver.get(connetorSearchURL);

		Thread.sleep(5000);

		driver.navigate().refresh();

		Thread.sleep(5000);



		driver.get(searchUrl);

		driver.navigate().refresh();

		Thread.sleep(5000);

	}



	/*

	 * Added By: anil.patil@cleartrip.com This method returns Url for Amex

	 * Whitelable based on host name

	 */

	public String getAmexWlUrl() {

		if (common.value("host").contains("qa2")) {

			String url = "https://www.amexindiatravel.com/";

			return url;

		} else if (common.value("host").contains("www")) {

			String url = "http://www.amexindiatravel.com/";

			return url;

		} else if (common.value("host").contains("beta")) {

			/* Will have to update below url once we get beta domain for Amex */

			String url = "http://www.amexindiatravel.com/";

			return url;

		}

		return null;

	}



	/*

	 * Added By: anil.patil@cleartrip.com The method does Web Check-in from user

	 * trip details page

	 */

	public void initiateWebCheckIn(RemoteWebDriver driver, String tripId, String domain,

			HashMap<String, String> xmlDetails, String journey) throws Exception {

		int attempt = 0;

		int attempt1 = 0;

		String isWebcheckinEligible;

		xmlDetails.put("booking-status", "");

		xmlDetails.put("webcheckin-eligible", "");

		xmlDetails = getTripXmlPrams(tripId, xmlDetails);



		String isBookingPassed = xmlDetails.get("booking-status");

		addLog("Trip XML: <booking-status> " + isBookingPassed, true);

		if (!isBookingPassed.equalsIgnoreCase("P")) {

			Assert.fail("Booking status is not P");

		}



		/*

		 * below do-while condition to handle chronicle check eligiblity calls timeout

		 * to webcheckin app.

		 */

		do {

			isWebcheckinEligible = xmlDetails.get("webcheckin-eligible");

			if (isWebcheckinEligible.equalsIgnoreCase("true")) {

				addLog("Trip XML: <webcheckin-eligible> " + isWebcheckinEligible, true);

				break;

			}

			attempt++;

			Thread.sleep(15000);

		} while (attempt < 3);



		if (!isWebcheckinEligible.equalsIgnoreCase("true")) {

			Assert.fail("Parsed TripXML http://172.17.12.231:9080/trips/" + tripId

					+ ", FLIGHT BOOKED IS NOT ELIGIBLE FOR WEB CHECK-IN.");

		}



		do {

			loadTrip(driver, tripId, domain); // loading trip details page



			if (!elementPresent(driver, getObject("AirCom_Trip_Details_WebCheckIn_Button"), 15)) {

				Assert.fail("Web Check-in button not found in trip details page");

			}

			waitForElementToBeClickable(driver, getObject("AirCom_Trip_Details_WebCheckIn_Button"));

			safeClick(driver, getObject("AirCom_Trip_Details_WebCheckIn_Button"));

			addLog("Web Check-in button clicked.", true);



			if (elementPresent(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 15)) {

				Assert.fail("Sorry, our system is acting up.Error!");

			}

			if (!elementPresent(driver, getObject("AirCom_WebCheckInPage_ExpressWayCheckin_Button"), 15)) {

				Assert.fail("Web ExpressWayCheckin option not found");

			}

			safeClick(driver, getObject("AirCom_WebCheckInPage_ExpressWayCheckin_Button"));

			addLog("ExpressWay Web Checkin option clicked.", true);



			if (!elementPresent(driver, getObject("AirCom_WebCheckInPage_ExpressWay_ConfirmCheckIn_Button"), 15)) {

				Assert.fail("Confirm CheckIn Button not found.");

			}

			safeClick(driver, getObject("AirCom_WebCheckInPage_ExpressWay_ConfirmCheckIn_Button"));

			addLog("ExpressWay Web Checkin Confirm button clicked.", true);



			if (!elementPresent(driver, getObject("AirCom_WebCheckInPage_RequestReceivedPage_Button"), 15)) {

				Assert.fail("Web check-in request received page not loaded.");

			}

			safeClick(driver, getObject("AirCom_WebCheckInPage_RequestReceivedPage_Button"));

			addLog("Clicked OKAY on WebCheckInPage Request Received Page.", true);

			Thread.sleep(10000);

			if (!elementPresent(driver, getObject("AirCom_Acc_TripDetailsPage_BackToTrips_Button"), 15)) {

				Assert.fail("Trips page not loaded on clicking OKAY on WebCheckInPage Request Received Page.");

			}

			if (elementPresent(driver, getObject("AirCom_Acc_TripDetailsPage_WebCheckIn_InProgress"), 15)) {

				addLog("Trip details-Web Check-in status: Web Check-in is in progress.", true);

			} else {

				Assert.fail("Web Check-in status not updaing in trip details page.");

			}

			if (journey.equalsIgnoreCase("oneway")) {

				break;

			}

			Thread.sleep(10000);

			attempt1++;

		} while (attempt1 < 1);



	}



	/*

	 * Added By: anil.patil@cleartrip.com The method check if Baggage bundling fares

	 * exist in srp

	 */

	public boolean checkBundlefareInSrp(RemoteWebDriver driver) {

		JavascriptExecutor executor = driver;

		return (Boolean) executor.executeScript(

				"return document.evaluate(\".//th/div/label[@id='withoutCabinBaggage'] and .//th/div/label[@id='withCabinBaggage']\", document, null, XPathResult.BOOLEAN_TYPE, null).booleanValue");

	}



	public boolean isBaggageBundleFareOptionAvailable(RemoteWebDriver driver) throws Exception {

		if (elementVisible(driver, getObject("AirCom_Srp_Filter_FareOptions"), 60)

				&& elementVisible(driver, getObject("AirCom_Srp_Filter_FareOptions_With_Baggage_Checkbox"), 5)

				&& elementVisible(driver, getObject("AirCom_Srp_Filter_FareOptions_Without_Baggage_Checkbox"), 5)

				&& elementVisible(driver, getObject("AirCom_Srp_WithoutCabinBaggage_RadioButton"), 5)

				&& elementVisible(driver, getObject("AirCom_Srp_WithCabinBaggage_RadioButton"), 5)) {

			addLog("Fare Filter Option: With and without baggage Found \n"

					+ "SRP Fare Option: With and Without Baggage Found", true);

			return true;

		} else

			return false;

	}



	/*

	 * Added By: anil.patil@cleartrip.com Method returns check-in and cabin baggage

	 * details for Baggage bundling fares.

	 */

	public HashMap<String, Integer> getBaggageInfoIntlSrp(RemoteWebDriver driver) throws Exception {

		HashMap<String, Integer> Baggageinfo = new HashMap<String, Integer>();

		Thread.sleep(1000);

		String checkintext = getText(driver, getObject("AirCom_Srp_BaggageBundledFare_BaggageInfo_CheckIn"));

		String cabintext = getText(driver, getObject("AirCom_Srp_BaggageBundledFare_BaggageInfo_Cabin"));



		String i = checkintext.replaceAll("[^0-9]", "");

		int checkinintValue = Integer.valueOf(i);

		String j = cabintext.replaceAll("[^0-9]", "");

		int cabinintValue = Integer.valueOf(j);



		Baggageinfo.put("Checkin", checkinintValue);

		Baggageinfo.put("Cabin", cabinintValue);

		return Baggageinfo;

	}



	public HashMap<String, Integer> getBaggageInfoFromPopup(RemoteWebDriver driver) throws Exception {

		HashMap<String, Integer> BaggageinfoPopup = new HashMap<String, Integer>();

		safeClick(driver, getObject("AirCom_BookStep_BaggageInfo_Link"));

		Thread.sleep(20000);

		addLog("switching to baggage modal window", true);

		driver.switchTo().frame("modal_window");

		if (waitElement(driver, By.xpath("//td[@class='keyValue']/p"), 30)) {

			List<WebElement> we = driver.findElements(By.xpath("//td[@class='keyValue']/p"));

			for (int i = 0; i < we.size(); i++) {

				if (we.get(i).getText().contains("check-in")) {

					String checkintext = we.get(i).getText();

					String a = checkintext.replaceAll("[^0-9]", "");

					int checkinintValue = Integer.valueOf(a);

					BaggageinfoPopup.put("check-in_" + i, checkinintValue);

				}

				if (we.get(i).getText().contains("cabin")) {

					String cabintext = we.get(i).getText();

					String b = cabintext.replaceAll("[^0-9]", "");

					int cabinintValue = Integer.valueOf(b);

					BaggageinfoPopup.put("cabin_" + i, cabinintValue);

				}

			}

		} else {

			Assert.fail("Baggage pop up window in book step-1 did not load even after 30 seconds");

		}

		driver.switchTo().defaultContent();

		// Actions action = new Actions(driver);

		// action.sendKeys(Keys.ESCAPE);

		safeClick(driver, getObject("AirCom_BookStep_Baggage_Info_Popup_Close"));

		return BaggageinfoPopup;

	}



	public int[] getBaggageInfoFromBookStep(RemoteWebDriver driver) throws Exception {

		int checkin = 0;

		int cabin = 0;

		waitElement(driver, getObject("AirCom_BookStep_CheckIn_Baggage_Info"), 30);

		String checkIn_BookStep = getText(driver, getObject("AirCom_BookStep_CheckIn_Baggage_Info"));

		if (!checkIn_BookStep.trim().contains("None")) {

			String i = checkIn_BookStep.replaceAll("[^0-9]", "");

			checkin = Integer.valueOf(i);

		}

		String cabin_BookStep = getText(driver, getObject("AirCom_BookStep_Cabin_Baggage_Info"));

		if (!cabin_BookStep.trim().contains("None")) {

			String j = cabin_BookStep.replaceAll("[^0-9]", "");

			cabin = Integer.valueOf(j);

		}

		int[] BaggageInfoBookStep = { checkin, cabin };

		return BaggageInfoBookStep;

	}



	/*

	 * Added by: anil.patil@cleartrip.com Method to apply Gift Voucher for Amex WL

	 * Air Booking

	 */

	public void applyGiftVoucherAmexWL(RemoteWebDriver driver, String gvnumber, String gvpin) throws Exception {

		List<WebElement> gvMessages = null;

		String gvpart1 = gvnumber.substring(0, 10);

		String gvpart2 = gvnumber.substring(10, 16);

		System.out.println(gvpart1 + " and " + gvpart2);

		addLog("Applying GiftVoucher:" + gvnumber + "  Pin:" + gvpin, true); // 4000335011247224

		WebElement applyCouponField = driver.findElement(getObject("Air_AmexWL_PaymentPage_ApplyCoupon"));

		applyCouponField.clear();

		applyCouponField.sendKeys(gvpart1);

		// The id of the filed changes from 'coupon' to 'gift-card-number' as we

		// enter the gv.

		driver.findElement(By.id("gift-card-number")).sendKeys(gvpart2);

		if (elementVisible(driver, getObject("Air_AmexWL_PaymentPage_GV_Pin"), 10)) {

			driver.findElement(getObject("Air_AmexWL_PaymentPage_GV_Pin")).sendKeys(gvpin);

		}

		safeClick(driver, getObject("Air_AmexWL_PaymentPage_ApplyGV_Button"));

		addLog("Apply GV button clicked", true);

		waitElement(driver, getObject("Air_AmexWL_PaymentPage_GV_SuccessMgs"), 5);

		if (waitElement(driver, getObject("Air_AmexWL_PaymentPage_GV_SuccessMgs"), 5)) {

			gvMessages = driver.findElements(getObject("Air_AmexWL_PaymentPage_GV_SuccessMgs"));

			for (WebElement we : gvMessages) {

				addLog(we.getText(), true);

			}

		} else {

			Assert.fail("GV successful message not shown");

		}

	}



	/*

	 * Added By: anil.patil@cleartrip.com Method to filter airlines in SRP based on

	 * the list of airlines passed

	 */

	public boolean filterFlightsOnSrpByListOfAirlines(RemoteWebDriver driver, ArrayList<String> filterByAirlines)

			throws Exception {



		addLog("Filtering SRP for airlines : " + filterByAirlines, true);

		List<WebElement> flights = null;



		if (filterByAirlines.isEmpty()) {

			addLog("Airlines to be filtered not specified! Hence not filtering", true);

			return false;

		}



		if (waitElement(driver, By.xpath(

				"//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"),

				1)) {

			flights = driver.findElements(By.xpath(

					"//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span"));

		} else {

			addLog("Not able to locate list of available flight names.", true);

			assertTrue("Failure! Not able to locate list of available flight names.", false);

		}



		for (int i = 0; i < flights.size(); i++) {

			// addLog(flights.get(i).getText(), true);

			if (flights.get(i).getText().equalsIgnoreCase("show multi-airline itineraries")) {

				addLog("Clicking on Multi Airline itineraries", false);

				flights.get(i).click();

				continue;

			} else {

				if (filterByAirlines.contains(flights.get(i).getText())) {

					continue;

				} else {

					try {

						int j = i + 1;

						scrollToElement(driver, By.xpath(

								"(//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["

										+ j + "]/../input"));

						safeClick(driver, By.xpath(

								"(//div[contains(@class, 'filterContent clearFix  hasList')]/nav[contains(@class, 'airlines')]/ul[contains(@class, 'list')]/li/label/span)["

										+ j + "]/../input"));

					} catch (Exception e) {

						addLog("Element not found / clickable. Continuing to next element", true);

					}

				}

			}

		}



		if (checkWarning(driver)) {

			return true;

		} else {

			return false;

		}

	}



	/*

	 * Added By: anil.patil@cleartrip.com Method to click on baggage info link for

	 * the first flight in SRP

	 */

	public void baggageInfolinkInSRP(RemoteWebDriver driver) throws Exception {

		mouseHover(driver, getObject("AirCom_Srp_First_result"));

		safeClick(driver, getObject("AirCom_Srp_First_result_Airline"));

		if (waitElement(driver, By.xpath(".//div[@class='airlineName']/span[@class='name']"), 30))

			;

		{

			Thread.sleep(5000);

			String airlineName = driver.findElement(By.xpath(".//div[@class='airlineName']/span[@class='name']"))

					.getText();

			addLog("Checking baggage info for BOOKING AIRLINE : " + airlineName, true);

		}

		safeClick(driver, getObject("AirCom_Srp_First_result_BaggageInfo"));

	}



	public void AccountsFacebookLogin(RemoteWebDriver driver) throws Exception {



		driver.get(baseUrl);

		// driver.navigate().to("https://qa2.cleartrip.com");

		Thread.sleep(2000);

		explicitWait(driver, By.id("userAccountLink"), 20);

		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

		safeClick(driver, getObject("AirCom_HomePage_SignIN"));

		assertFalse("Sign in page not opened", textPresent(driver, "Sign in to Cleartrip", 10));

		driver.switchTo().frame("modal_window");



	}



	public void AccountsFacebookRetruntoCleartrip(RemoteWebDriver driver) throws Exception {

		safeClick(driver, getObject("B2C_Accounts_FacebookLogin"));

		// driver.findElement(By.xpath("//a[contains(text(),'Log in with

		// Facebook')]")).click();

		safeType(driver, getObject("B2C_Accounts_FacebookEmail"), dataFile.value("UserIdForFacebooklogin"));

		safeType(driver, getObject("B2C_Accounts_Facebookpassword"), dataFile.value("UserpasswordForFacebooklogin"));



		safeClick(driver, getObject("B2C_Accounts_Facebooksubmit"));

		// driver.findElement(By.id("email")).sendKeys("cleartriptestforfbautomation@gmail.com");

		// driver.findElement(By.id("pass")).sendKeys("cleartrip@123");

		// driver.findElement(By.id("loginbutton")).click();

		Thread.sleep(2000);

		assertFalse("Facebook redirection not happend to cltp account!", !elementPresent(driver, By.id("trips"), 1));

		assertFalse("Facebook redirection not happend to cltp account!",

				!elementPresent(driver, By.id("travellers_tab"), 1));

		assertFalse("Facebook redirection not happend to cltp account!",

				!elementPresent(driver, By.id("profile_tab"), 1));



	}



	public void AccountsManageTripsUnsignedtripflow(RemoteWebDriver driver) throws Exception {

		if (waitElement(driver, getObject("AirCom_HomePage_ManageTrips"), 3)) {

			driver.findElement(getObject("AirCom_HomePage_ManageTrips")).click();

		} else {

			addLog("Home page not loading. Error!");

			// System.out.println("Home page not loading. Error!");

			assertTrue(false);

		}



		textPresent(driver, "Your account", 5);

		Thread.sleep(2000);

		driver.switchTo().frame("modal_window");



	}



	public void AccountsManageTripsUnsignedtripflowOtpVerify(RemoteWebDriver driver) throws Exception



	{

		elementPresent_Time(driver, getObject("AirCom_EmailAddress"), 10);

		// waitElement(driver, getObject("AirCom_EmailAddress"), 1);



		safeType(driver, getObject("AirCom_EmailAddress"), dataFile.value("AirUserIdForHQScripts"));

		safeType(driver, By.id("tripidSecond"), "Q1606090202");

		driver.findElement(getObject("AirCom_RegisterButton")).click();

		Thread.sleep(1000);



		textPresent(driver, "OTP verification", 10);

		assertTrue("Trip details via manage trips login is not working",

				elementPresent_Time(driver, By.id("otpVerifyBtn"), 10));

		System.out.println("OTP verify button has  displayed==" + elementPresent(driver, By.id("otpVerifyBtn"), 1));



	}



	public String getAPITripId(String domain, String tripType, String userMail, String userId)

			throws ClientProtocolException, IOException {

		String tripId;

		StopWatch watch = new StopWatch();

		// HttpClient httpclient = HttpClients.createDefault();

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(10000)

				.setConnectionRequestTimeout(40000).build();

		DefaultHttpClient http = new DefaultHttpClient();

		System.out.println(getBaseUrl(domain));

		HttpPost httppostBook = new HttpPost(getBaseUrl(domain) + "/automation/book");

		// HttpPost httppostBook = new

		// HttpPost("https://qa2.cleartrip.com/automation/book");

		httppostBook.setConfig(requestConfig);



		JsonObject jsonObject = Json.createObjectBuilder().add("email_id", rubyAPITrips.value(userMail))

				.add("user_id", rubyAPITrips.value(userId)).add("trip_ref", rubyAPITrips.value(tripType))

				.add("host", common.value("host")).build();



		String jsonString = jsonObject.toString();

		addLog("Call made to ruby API is : " + jsonString);

		System.out.println("Call made to ruby API is : " + jsonString);

		StringEntity se = new StringEntity(jsonString);

		httppostBook.setEntity(se);



		watch.start();

		HttpResponse response = http.execute(httppostBook);

		watch.stop();

		System.out.println("Time taken by trip booking API : " + watch.toString());

		addLog("Time taken by trip booking API : " + watch.toString());

		HttpEntity entity = response.getEntity();

		System.out.println("Response code : " + response.getStatusLine().getStatusCode());

		addLog("Response code : " + response.getStatusLine().getStatusCode());

		tripId = EntityUtils.toString(entity, "UTF-8");

		addLog("TripId booked by API is : " + tripId);

		System.out.println("TripId booked by API is : " + tripId);

		assertTrue("Proper tripId not generated. Error!", tripId.startsWith("Q", 0));



		return tripId;

	}



	public void signInHQ(RemoteWebDriver driver) throws Exception {

		String username_HQ = null;

		String password_HQ = null;

		if (!checkIfSignedInHQ(driver)) {

			if (common.value("host").equals("qa2")) {

				username_HQ = "Username_HQ_qa2";

				password_HQ = "Password_HQ_qa2";

			} else if (common.value("host").equals("beta")) {

				username_HQ = "Username_HQ_beta";

				password_HQ = "Password_HQ_beta";

			} else if (common.value("host").equals("www")) {

				username_HQ = "Username_HQ_www";

				password_HQ = "Password_HQ_www";

			} else if (common.value("host").equals("hf")) {

				username_HQ = "Username_HQ_qa2";

				password_HQ = "Password_HQ_qa2";

			}

			typeUsername(driver, dataFile.value(username_HQ));

			typePassword(driver, dataFile.value(password_HQ));

			signInButton(driver);

		}

	}



	public boolean checkIfSignedInHQ(RemoteWebDriver driver) throws Exception {

		if (waitElement(driver, getObject("AirCom_SignIn_HQ_Email"), 3)) {

			return false;

		} else if (waitElement(driver, getObject("Air_HQ_Signout"), 3)) {

			return true;

		} else {

			// System.out.println("HQ page not loading.");

			addLog("HQ page not loading.");

			assertTrue("Failure!", false);

		}

		return false;

	}



	public void typeUsername(RemoteWebDriver driver, String username) throws Exception {

		By by = getObject("AirCom_SignIn_HQ_Email");

		safeType(driver, by, username);

	}



	public void typePassword(RemoteWebDriver driver, String passwd) throws Exception {

		By by = getObject("AirCom_SignIn_HQ_Password");

		safeType(driver, by, passwd);

	}



	public void signInButton(RemoteWebDriver driver) throws Exception {

		By by = getObject("AirCom_HQ_SignIN_Button");

		safeClick(driver, by);

		// waitForPageLoaded(driver);

		// if (elementVisible(driver, By.id(param.get("signin_error")), 2)) {//

		// TODO: check why calling recursively

		// signIn("Username_HQ_qa2", "Password_HQ_qa2");

		// }

		// System.out.println("Signed in to HQ");

	}



	public void confirmTripLoad(RemoteWebDriver driver, String tripId, String domain) throws Exception {

		int count = 0;

		Thread.sleep(6000);

		driver.get(getBaseUrl(domain) + "/hq/trips/");

		while (count < 2) {

			driver.get(getBaseUrl(domain) + "/hq/trips/" + tripId);

			Thread.sleep(10000);

			if (waitElement(driver, getObject("AirCom_Sorry_System_Acting_Up_Error"), 1)) {

				// System.out.println("System Acting up error.");

				addLog("System Acting up error.", true);

				assertTrue("Failure!", false);

			}

			if (driver.getPageSource().contains(tripId)) {

				break;

			} else {

				addLog("Sorry, our system is acting up.", true);

			}



			/*

			 * * String tripMessage = getText(driver,

			 * getObject("AirCom_HQ_System_Acting_Up"));

			 *

			 * if (tripMessage == null) { break; } else if

			 * (tripMessage.trim().equalsIgnoreCase ("Sorry, our system is acting up.")) {

			 * count++; }

			 */

			count++;

		}

		if (elementNotVisible(driver, getObject("AirCom_HQ_Trips_Tab"), 1)) {

			assertTrue("Trip details page not loading!", false);

		}



	}



	public String getBookingStatus(RemoteWebDriver driver) throws Exception {

		return getText(driver, getObject("AirCom_HQ_Booking_Status"));

	}



	public void mobileweb_FlightXP_Payment(RemoteWebDriver driver) throws Exception {

		safeType(driver, By.id("CardNumber"), dataFile.value("Master_CC_Number"));

		/*

		 * safeClick(driver, getObject("FlightXp_exp_month_select")); safeClick(driver,

		 * getObject("FlightXp_exp_month")); safeClick(driver,

		 * getObject("FlightXp_exp_year_select")); safeClick(driver,

		 * getObject("FlightXp_exp_year")); safeType(driver, getObject("FlightXp_cvv"),

		 * dataFile.value("MasterCard_CVV"));

		 */

		safeType(driver, By.id("CardName"), dataFile.value("MasterCard_HolderName"));

		Select options = new Select(driver.findElement(By.id("ExpirationMonth")));

		options.selectByVisibleText("5");

		Select options1 = new Select(driver.findElement(By.id("ExpirationYear")));

		options1.selectByVisibleText("2020");

		safeType(driver, By.id("CVV"), "123");

		safeType(driver, By.id("coupon"), "DOMOW");

		safeClick(driver, By.id("coupon-code-button"));

		Thread.sleep(2000);

		safeClick(driver, By.id("ExpressCheckout"));

	}



	public boolean prodNBRetry(RemoteWebDriver driver, String paymentType) throws Exception {

		boolean result = false;



		elementPresent_Time(driver, getObject("AirCom_BookStep4_MakePayment_Button"), 60);

		System.out.println("NB Tab present");

		addLog("NB Tab present");



		if (paymentType.equalsIgnoreCase("NB")) {

			safeClick(driver, getObject("AirCom_BookStep4_NB_Tab"));

			Thread.sleep(2000);

			isTextPresent(driver, "Popular banks");

			safeSelect(driver, getObject("AirCom_BookStep4_Netbank_DropDown"), "Citibank");

			Thread.sleep(2000);

			safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));



			Thread.sleep(15000);

			// waitElement(driver, getObject("AirCom_Payment_BankSite"), 50);

			waitForElement(driver, 50, getObject("AirCom_Payment_BankSite"));

			addLog(driver.getCurrentUrl());

			// System.out.println(driver.getCurrentUrl());

			System.out.println("title=" + driver.getTitle());
			String title=driver.getTitle();
			//AssertJUnit.assertEquals(":: Citibank Payment Gateway ::", driver.getTitle());
			
			AssertJUnit.assertTrue("The title is not expected. Expected: "+"Citibank Payment Gateway|Payment Gateway, Actual: "+title, title.matches(":: Citibank Payment Gateway ::|:: Payment Gateway ::"));
			
			addLog("Net Banking Site Reached");

			printInfo("Net Banking Site Reached");

			try {

				driver.findElement(By.linkText("www.cleartrip.com")).click();

			} catch (Exception e) {

				driver.navigate().back();

			}

			Thread.sleep(2000);

			/*

			 * if(elementVisible(driver,By.xpath(

			 * "//*[contains(text(),'Transaction')]"),10)) {

			 * safeClick(driver,By.xpath("//*[text()='Cancel']"));

			 *

			 * }

			 */

			elementPresent_Time(driver, By.cssSelector("h1"), 100);

			textPresent(driver, "Oops, your payment didnt work", 10);

Thread.sleep(1000);

			AssertJUnit.assertEquals("Cleartrip | Book your flight securely in simple steps", driver.getTitle());

			System.out.println("-------" + driver.findElement(By.cssSelector("h1")).getText());

			AssertJUnit.assertEquals("Oops, your payment didn�t work",

					driver.findElement(By.xpath("//div/h1[contains(text(),'Oops')]")).getText());

			addLog("Successfully arrived on Payment retry page from NB");

			printInfo("Successfully arrived on Payment retry page from NB");

			Thread.sleep(5000);

			result = true;

		}

		return result;



	}



	public String getModifiedDate(String date) {

		Calendar c = Calendar.getInstance();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(date));

		String convertedDate = dateFormat.format(c.getTime());

		return convertedDate;

	}



	public boolean checkUrlContains(RemoteWebDriver remoteWebDriver, String expected, Integer timeOut) {

		WebDriverWait webDriverWait = new WebDriverWait(remoteWebDriver, timeOut);

		webDriverWait.until(ExpectedConditions.urlContains(expected));

		return remoteWebDriver.getCurrentUrl().contains(expected);

	}



	public void clickBook(RemoteWebDriver driver, int i) throws Exception {

		// System.out.println(driver.getCurrentUrl());

		safeClick(driver, By.xpath("//td/button[" + i + "]"));



	}



	@SuppressWarnings("unused")

	public void newViaBook(RemoteWebDriver driver) throws Exception {



		boolean warningFound = false;

		boolean routed = false;

//		if (elementPresent(driver, By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"), 1)) {

//			driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li")).click();

		driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a")).click();

//		}



		warningFound = selectingStop1("", driver, 1);

		Thread.sleep(3000);



		// multiItinerary(driver);

		List<WebElement> we2 = driver.findElements(By.xpath("//li[contains(@class,'listItem')]"));

		List<WebElement> we = driver.findElements(By.xpath("//a[@linkidentifier='itinerary']"));



		test: for (int i = 0; i < we.size(); i++) {

			// HashMap<String, String> hp = new HashMap<String, String>();

			we.get(i).click();



			List<WebElement> we1 = we2.get(i)

					.findElements(By.xpath("./div//*[@class='itinerary']/ul/li/div[2]/small[1]"));

			// *[@class='itinerary']/ul/li/div[2]/span

			// System.out.println(we1.size());

			if (we1.size() == 2) {

				for (int j = 0; j < we1.size(); j++) {



					Thread.sleep(1000);

					String airline1 = driver

							.findElementByXPath("//div[contains(@class,'itinerary')]//ul[1]//li[1]//div[2]//small[1]")

							.getText();

					String airline2 = driver

							.findElementByXPath("//div[contains(@class,'itinerary')]//ul[2]//li[1]//div[2]//small[1]")

							.getText();

					addLog("first flight=" + we1.get(0).getText(), true);

					addLog("Second flight=" + we1.get(1).getText(), true);

					if (we1.get(0).getText().equalsIgnoreCase(we1.get(1).getText())) {

						System.out.println("WE 1 " + airline1);

						System.out.println("WE 1 " + airline2);

						System.out.println("a: " + we2.get(i));

						// we2.get(i).findElement(By.xpath("//button[contains(@class,'booking')]")).click();

						safeClick(driver, By.xpath(

								"//li/table/tbody/tr/th[contains(@class,'count1')]/ancestor::tr/following-sibling::tr/td[3]/button"));

						// driver.findElementByXPath("//li[@data-id=" + i + 1 +

						// "]//tr/td[3]/button").click();

						// driver.findElementByXPath("//li[@data-id=" + i + 1 +

						// "]//tr/td[3]/button").click();

						break test;

					} else {

						addLog("Via flight not found", true);

					}

				}

			}

		}

	}



	public void verifyStats(RemoteWebDriver driver, String tripId,String paxConfig,String ssrConfig) throws Exception {

		SoftAssert soft_assert = new SoftAssert();



		addLog("Waiting to get the booking status from trip details.",true);

		Thread.sleep(35000);

		String bookingStatus ="";

		String responseString ="";

		for (int i = 0; i <=20; i++) {

			

			// Get trips details response to verify completion and pnr status

			HttpResponse response = APIUtils.getApi(common.value("airapitripdetalsurl") + ":" + common.value("airapitripdetalsport") + "/trips/" + tripId,

					null, 200);



			// Convert response to string val

			responseString = APIUtils.returnResponseAsString(response);

			

			bookingStatus = APIUtils.getNodeListFromXMLResponseString(responseString, "//trip/booking-status")

					.item(0).getTextContent();

			if(bookingStatus.trim().equalsIgnoreCase("") || bookingStatus == null) {

				Thread.sleep(5000);

			}else {

				break;

			}

		}

		addLog("Verifying the booking status",true);

		addLog("Booking status of trip id - " + tripId + " is : " + bookingStatus,true);

		

		soft_assert.assertEquals(bookingStatus, "P",

				"Booking is not completed. Observed booking status is " + bookingStatus + ", expected is - P.");

		

		if(bookingStatus.equalsIgnoreCase("p")) {
			soft_assert.assertFalse(responseString.toLowerCase().contains("<booking-status>h</booking-status>"),
					"Booking is not completed. Observed that some of the <booking-status> tag has value as: " + bookingStatus + ", expected is - P.");
			soft_assert.assertFalse(responseString.contains("<booking-status>z</booking-status>"),
					"Booking is not completed. Observed that some of the <booking-status> tag has value as: " + bookingStatus + ", expected is - P.");
		}



		// Verify PNR is generated is not NULL

		String pnr = APIUtils

				.getNodeListFromXMLResponseString(responseString,

						"//trip/air-bookings/air-booking/booking-info-list/booking-info/airline-pnr")

				.item(0).getTextContent();

		

		addLog("Verifying the PNR value as not null",true);

		addLog("PNR of trip id - " + tripId + " is - \"" + pnr+"\"",true);

		soft_assert.assertTrue((pnr != null && !pnr.equals("")), "PNR is not generated. Observed booking PNR is \"" + pnr + "\" for trip id " + tripId);



		// Verify SMS flow check by identifying log content form stats window

		// Hits to stats server using trip id

		driver.get("http://" + dataFile.value("SffLoginUsername") + ":" + dataFile.value("SffPassword") + "@"

				+ common.value("airapistatsurl") + "/bookreports.php?tripId=" + tripId);



		// Verify log contains SMS pattern

		

		String pageSource=driver.getPageSource();

		addLog("Verifying that the booking flow went through SMS flow",true);

		soft_assert.assertEquals(pageSource.contains("SMS response JSON"), true,

				"The booking for trip id " + tripId + " is not is SMS flow");

		

		addLog("Verifying that there is no exception in SMS flow",true);

		soft_assert.assertEquals(pageSource.contains("Exception"), false,

				"The booking for trip id " + tripId + " has exception keyword in SMS flow");

		

		addLog("Verifying that in summary there is no response code status other than 0 and 200 in SMS flow",true);

		isResponseNotProper(tripId,soft_assert);

		

		addLog("Verifying ssr and pax fee in SMS flow from HQ",true);

		Map<String, Double> priceInfo=getMealBaggageAndPaxPrice(tripId);

		

		String[] inp = paxConfig.split("-");

		int adults = Integer.parseInt(inp[0]);

		int children = Integer.parseInt(inp[1]);

		int infants = Integer.parseInt(inp[2]);

		

		String[] ssr = ssrConfig.split("-");

		boolean meal = ((Integer.parseInt(ssr[0]) > 0) ? true : false);

		boolean seat = ((Integer.parseInt(ssr[1]) > 0) ? true : false);

		boolean baggage = ((Integer.parseInt(ssr[2]) > 0) ? true : false);

		

		double seatFee=priceInfo.get("totalSeatFee");

		double baggageFee=priceInfo.get("totalBaggageFee");

		double mealFee=priceInfo.get("totalMealFee");

		double adultBaseFare=priceInfo.get("totalAdultBaseFare");

		double childBaseFare=priceInfo.get("totalChildBaseFare");

		double infantBaseFare=priceInfo.get("totalInfantBaseFare");

		

		if(adults > 0) {

			addLog("Verifying that the fee should be > 0 for "+adults+ " adults, fee= "+adultBaseFare,true);

			soft_assert.assertTrue(adultBaseFare > 0.0,

					"For the trip id " + tripId + ", eventhough adult count is "+adults+", the fare for the same in HQ is less than 1 i.e "+adultBaseFare+" in SMS flow.");

		}else {

			addLog("Verifying that the fee should be 0.0 for "+adults+ " adults, fee= "+adultBaseFare,true);

			soft_assert.assertTrue((adultBaseFare == 0.0),

					"For the trip id " + tripId + ", eventhough adult count is "+adults+", the fare for the same in HQ is greater than 0.0 i.e "+adultBaseFare+" in SMS flow.");

		}

		

		if(children > 0) {

			addLog("Verifying that the fee should be > 0 for "+children+" children, fee= "+childBaseFare,true);

			soft_assert.assertTrue(childBaseFare > 0.0,

					"For the trip id " + tripId + ", eventhough children count is "+children+", the fare for the same in HQ is less than 1 i.e "+childBaseFare+" in SMS flow.");

		}else {

			addLog("Verifying that the fee should be 0.0 for "+children+" children, fee= "+childBaseFare,true);

			soft_assert.assertTrue((childBaseFare == 0.0),

					"For the trip id " + tripId + ", eventhough children count is "+children+", the fare for the same in HQ is greater than 0.0 i.e "+childBaseFare+" in SMS flow.");

		}

		

		if(infants > 0) {

			addLog("Verifying that the fee should be > 0 for "+infants+" infants, fee= "+infantBaseFare,true);

			soft_assert.assertTrue(infantBaseFare > 0.0,

					"For the trip id " + tripId + ", eventhough infants count is "+infants+", the fare for the same in HQ is less than 1 i.e "+infantBaseFare+" in SMS flow.");

		}else {

			addLog("Verifying that the fee should be 0.0 for "+infants+" infant, fee= "+infantBaseFare,true);

			soft_assert.assertTrue((infantBaseFare == 0.0),

					"For the trip id " + tripId + ", eventhough infants count is "+infants+", the fare for the same in HQ is greater than 0.0 i.e "+infantBaseFare+" in SMS flow.");

		}

		

		if(meal) {

			addLog("Verifying that the ordered meal fee should be > 0 , fee= "+mealFee,true);

			soft_assert.assertTrue(mealFee > 0.0,

					"For the trip id " + tripId + ", eventhough meal is ordered, the fare for the same in HQ is less than 1 i.e "+mealFee+" in SMS flow.");

		}else {

			addLog("Verifying that if meal is not ordered then the fee should be 0.0 , fee= "+mealFee,true);

			soft_assert.assertTrue((mealFee == 0.0),

					"For the trip id " + tripId + ", eventhough meal is not ordered, the fare for the same in HQ is greater than 0.0 i.e "+mealFee+" in SMS flow.");

		}

		

		if(baggage) {

			addLog("Verifying that the extra added baggage fee should be > 0 , fee= "+baggageFee,true);

			soft_assert.assertTrue(baggageFee > 0.0,

					"For the trip id " + tripId + ", eventhough extra baggage is added, the fare for the same in HQ is less than 1 i.e "+baggageFee+" in SMS flow.");

		}else {

			addLog("Verifying that if the extra baggage is not added fee should be 0.0 , fee= "+baggageFee,true);

			soft_assert.assertTrue((baggageFee == 0.0),

					"For the trip id " + tripId + ", eventhough extra baggage is not added, the fare for the same in HQ is greater than 0.0 i.e "+baggageFee+" in SMS flow.");

		}

		

		if(seat) {

			addLog("Verifying that the fee for selected seat should be > 0 , fee= "+seatFee,true);

			soft_assert.assertTrue(seatFee > 0.0,

					"For the trip id " + tripId + ", eventhough seat is selected, the fare for the same in HQ is less than 1 i.e "+seatFee+" in SMS flow.");

		}else {

			addLog("Verifying that if seat is not selected, the fee for seat should 0.0 , fee= "+seatFee,true);

			soft_assert.assertTrue((seatFee == 0.0),

					"For the trip id " + tripId + ", eventhough seat is not selected, the fare for the same in HQ is greater than 0.0 i.e "+seatFee+" in SMS flow.");

		}

		

		soft_assert.assertAll();

	}



	public void verifySMSByItinerary(RemoteWebDriver driver, String itneraryId) throws Exception {

		System.out.println("inside verifySMSByItinerary");

		driver.get("http://" + dataFile.value("SffLoginUsername") + ":" + dataFile.value("SffPassword") + "@"

				+ common.value("airapistatsurl") + "/bookreports.php?itineraryId=" + itneraryId);



		// Verify log contains SMS pattern

		Assert.assertEquals(driver.getPageSource().contains("SMS response JSON"), true,

				"The itinerary id " + itneraryId + " is not in SMS flow");

	}



	public void verifySearchResultsByConnectorSearchAndLoadSRP(RemoteWebDriver driver, String baseUrl,

			boolean is_international, String origin, String destination, String destination1, String onwarddate,

			String returndate, String paxConfig, String airline, String suppliers, String tripType,String paymentMethod) throws Exception {

		

		

		String sct="";

		if(!common.value("domain").trim().equalsIgnoreCase("com")) {

			sct=common.value("domain").toLowerCase().trim();

		}

		String solutionXpath="";
		String intl = "n";

		String endPoint = "/flights/results";

		if (is_international) {

			intl = "y";

			endPoint = "/flights/international/results";
			if(tripType.toLowerCase().matches("ow|Connected|via|mc")) {
				solutionXpath="//air-search-result/solutions";
			}else if(tripType.toLowerCase().matches("rt|splrt")) {
				solutionXpath="//air-search-result/solutions";
			}
		}else {
			if(tripType.toLowerCase().matches("ow|Connected|via|mc")) {
				solutionXpath="//air-search-result/onward-solutions";
			}else if(tripType.toLowerCase().matches("rt|splrt")) {
				solutionXpath="//air-search-result/onward-solutions";
			}
		}

		addLog("Getting the pax info.", true);

		String[] inp = paxConfig.split("-");

		String adults = inp[0];

		String children = inp[1];

		String infants = inp[2];



		addLog("Getting onward date", true);

		String odate = getModifiedDate(onwarddate);



		boolean flightCountFailure = false;

		String searchIp = dataFile.value("ConnectorSearchUrl");

		//String connectorSearchUrl = "";

		String searchUrl = "";

		By by = null;



		// todo: equal ignorecase

		if (tripType.toLowerCase().trim().equals("ow")) {



			connectorSearchUrl = searchIp + "from=" + origin + "&to=" + destination + "&depart_date=" + odate

					+ "&adults=" + adults + "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines="

					+ airline + "&carrier=" + airline + "&intl=" + intl + "&suppliers=" + suppliers

					+ "&airlines=" + airline + "&search_ver=&sct="+sct+"&src=connector";



			searchUrl = baseUrl + endPoint + "?from=" + origin + "&to=" + destination + "&depart_date=" + odate

					+ "&adults=" + adults + "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines="

					+ airline + "&carrier=" + airline + "&intl=" + intl + "&suppliers=" + suppliers + "&airlines="

					+ airline + "&stops=0";



			by = getObject("AirCom_SRP_Oneway_BookButton");



		} else if (tripType.toLowerCase().trim().equals("rt") || tripType.toLowerCase().trim().equals("splrt")) {



			addLog("Getting return date", true);

			String rdate = getModifiedDate(returndate);



		connectorSearchUrl = searchIp + "from=" + origin + "&to=" + destination + "&depart_date=" + odate

					+ "&return_date=" + rdate + "&adults=" + adults + "&childs=" + children + "&infants=" + infants

					+ "&class=Economy&airlines=" + airline + "&carrier=" + airline + "&intl=" + intl

					+ "&suppliers=" + suppliers + "&airlines=" + airline + "&search_ver=&sct="+sct+"&src=connector";

			
			/*connectorSearchUrl = searchIp + "from=" + origin + "&to=" + destination + "&depart_date=" + odate

					+ "&return_date=" + rdate + "&adults=" + adults + "&childs=" + children + "&infants=" + infants

					+ "&class=Economy&airlines=" + airline + "&carrier=" + airline + "&intl=" + intl

					+ "&suppliers=" + suppliers + "&airlines=" + airline + "&search_ver=&sct="+sct;*/


			searchUrl = baseUrl + endPoint + "?from=" + origin + "&to=" + destination + "&depart_date=" + odate

					+ "&return_date=" + rdate + "&adults=" + adults + "&childs=" + children + "&infants=" + infants

					+ "&class=Economy&airlines=" + airline + "&carrier=" + airline + "&intl=" + intl + "&suppliers="

					+ suppliers + "&airlines=" + airline + "&stops=0";



			by = By.xpath("//*[text()='Book']");



		} else if (tripType.toLowerCase().trim().equals("mc")) {



			addLog("Getting 2nd onward date for MC", true);

			String rdate = getModifiedDate(odate + 5);

			addLog("Getting 3rd onward date for MC", true);

			String rdate2 = getModifiedDate(onwarddate + 8);



			connectorSearchUrl = searchIp + "from1=" + origin + "&to1=" + destination + "&depart_date_1=" + odate

					+ "&multicity=true&from2=" + destination + "&to2=" + destination1 + "&depart_date_2=" + rdate

					+ "&from3=" + destination1 + "&to3=" + origin + "&depart_date_3=" + rdate2 + "&adults=" + adults

					+ "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines=" + airline + "&carrier="

					+ airline + "&intl=" + intl + "&suppliers=" + suppliers + "&airlines=" + airline+"&search_ver=&sct="+sct+"&src=connector";



			searchUrl = baseUrl + endPoint + "?from1=" + origin + "&to1=" + destination + "&depart_date_1=" + odate

					+ "&multicity=true&from2=" + destination + "&to2=" + destination1 + "&depart_date_2=" + rdate

					+ "&from3=" + destination1 + "&to3=" + origin + "&depart_date_3=" + rdate2 + "&adults=" + adults

					+ "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines=" + airline + "&carrier="

					+ airline + "&intl=" + intl + "&suppliers=" + suppliers + "&airlines=" + airline;



		} else if (tripType.toLowerCase().trim().equals("connected")) {



			connectorSearchUrl = searchIp + "from=" + origin + "&to=" + destination + "&depart_date=" + odate

					+ "&adults=" + adults + "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines="

					+ airline + "&carrier=" + airline + "&intl=" + intl + "&suppliers=" + suppliers

					+ "&airlines=" + airline + "&search_ver=&sct="+sct+"&src=connector";



			searchUrl = baseUrl + endPoint + "?from=" + origin + "&to=" + destination + "&depart_date=" + odate

					+ "&adults=" + adults + "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines="

					+ airline + "&carrier=" + airline + "&intl=" + intl + "&suppliers=" + suppliers + "&airlines="

					+ airline + "&stops=1";



			by = getObject("AirCom_SRP_Oneway_BookButton");



		} else if (tripType.toLowerCase().trim().equals("via")) {

			connectorSearchUrl = searchIp + "from=" + origin + "&to=" + destination + "&depart_date=" + odate

					+ "&adults=" + adults + "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines="

					+ airline + "&carrier=" + airline + "&intl=" + intl + "&suppliers=" + suppliers

					+ "&airlines=" + airline + "&search_ver=&sct="+sct+"&src=connector";



			searchUrl = baseUrl + endPoint + "?from=" + origin + "&to=" + destination + "&depart_date=" + odate

					+ "&adults=" + adults + "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines="

					+ airline + "&carrier=" + airline + "&intl=" + intl + "&suppliers=" + suppliers + "&airlines="

					+ airline + "&stops=1";



			by = getObject("AirCom_SRP_Oneway_BookButton");

		}



		if (common.value("host").equals("qa2")) {

			addLog("Making connector search for " + tripType, true);

			driver.get(connectorSearchUrl);


			// Get connector response

			HttpResponse response = APIUtils.getApi(connectorSearchUrl, null, 200);
		     addLog("Connector Response while search "+ response );

			// Convert response to string val

			String responseString = APIUtils.returnResponseAsString(response);
			String searchResultStatus="";

			try {

				searchResultStatus = APIUtils

						.getNodeListFromXMLResponseString(responseString, "//air-search-result/solutions").item(0)

						.getTextContent();
			}catch(Exception e) {
				searchResultStatus = APIUtils
				.getNodeListFromXMLResponseString(responseString, "//air-search-result/onward-solutions").item(0)
				.getTextContent();
			}


			addLog("Verifying the solutions in connector search", true);

			

			if(searchResultStatus.equalsIgnoreCase(null) || searchResultStatus.trim().equalsIgnoreCase("")) {

				addLog("Making connector search from cache for " + tripType, true);

				String connectorSearchUrl_cache = connectorSearchUrl.substring(0,connectorSearchUrl.indexOf("&src"));

				driver.get(connectorSearchUrl_cache);

				response = APIUtils.getApi(connectorSearchUrl_cache, null, 200);

				responseString = APIUtils.returnResponseAsString(response);
				try {

					searchResultStatus = APIUtils
							.getNodeListFromXMLResponseString(responseString, "//air-search-result/solutions").item(0).getTextContent();
				}catch(Exception e) {
					searchResultStatus = APIUtils
							.getNodeListFromXMLResponseString(responseString, "//air-search-result/onward-solutions").item(0)
							.getTextContent();
				}
			}
			/*Assert.assertTrue(
					!(searchResultStatus.equalsIgnoreCase(null) || searchResultStatus.trim().equalsIgnoreCase("")),
					"There is no solutions for the given sectors, date, pax and airline."); */
		}

		driver.get(baseUrl);

		if (!checkIfSignedIn(driver)) {

			sffLogin(driver, common.value("domain"));

			

			//If sign is not done then signup with random user. 

		//	String username = "CTSUPQA" + getCurrentDateAndTime()+"@gmail.com";

			/*String password = "CTSUPQA@1234";

			addLog("Registration process is happening...",true);

			addLog("email: "+username,true);

			addLog("password: "+password,true);

			

			airCom_HomepageRegisterWithHQRareId(driver, username, password);*/	

			

			if (!common.value("host").trim().equalsIgnoreCase("www") && paymentMethod.toLowerCase().contains("wallet")) {

			//	openNewTabAddCtWalletMoneyAndSwitchBackToMainPage(driver,username);

			}

		}

		

		addLog("Loading the Search result page", true);



		driver.get(searchUrl);



		waitToLoadSRP(driver, 5);



		addLog("Search URL is : " + driver.getCurrentUrl());



		flightCountFailure = waitForElement(driver, 120, by);

		if (!flightCountFailure) {

			addLog("No results found for this search. Making another attempt with different sectors.");

			driver.navigate().refresh();

			waitToLoadSRP(driver, 5);

			flightCountFailure = waitForElement(driver, 120, By.xpath("//*[text()='Book']"));

		}

	}

	

	public void waitToLoadSRP(RemoteWebDriver driver, int pulling_repetition) throws InterruptedException {

		for (int i = 0; i < pulling_repetition; i++) {



			String searchUrl = driver.getCurrentUrl();

			if (searchUrl.contains("page=loaded")) {

				break;

			} else {

				Thread.sleep(5000);

			}

		}

	}



	public void process_SSR(RemoteWebDriver driver, String ssrConfig,String tripType) throws Exception {



		// Get SSR Required - meal-seat-baggage-insurance-coupon

		String[] ssr = ssrConfig.split("-");

		boolean meal = ((Integer.parseInt(ssr[0]) > 0) ? true : false);

		boolean seat = ((Integer.parseInt(ssr[1]) > 0) ? true : false);

		boolean baggage = ((Integer.parseInt(ssr[2]) > 0) ? true : false);

		boolean insuranceRequired = ((Integer.parseInt(ssr[3]) > 0) ? true : false);

		boolean coupon = ((Integer.parseInt(ssr[4]) > 0) ? true : false);



		if (meal) {

			addMealOW(driver);

		}

		if (baggage) {

			addbaggageOW(driver);

		}

		if (seat) {

			SeatSelect_OW(driver);

		}

		if (coupon) {

			if(tripType.equalsIgnoreCase("OW") || tripType.equalsIgnoreCase("MC") || tripType.equalsIgnoreCase("connected") || tripType.equalsIgnoreCase("via")) {

				Apply_CouponCode(driver, "DOMOW");

			}else {

				Apply_CouponCode(driver, "autort");

			}

		}

		insuranceBlock(driver, insuranceRequired);

	}



	public String getItineraryId(RemoteWebDriver driver) {

		String itneraryId = "";

		try {

			addLog("Get itnerary id for SMS flow check in case of payment failure.",true);

			String currentURL = driver.getCurrentUrl();

			addLog("Current URL for itnerary id check - " + currentURL,true);

			itneraryId = currentURL.substring(currentURL.indexOf("itinerary/") + 10,

					currentURL.indexOf("itinerary/") + 48);

			addLog("The itinerary id is : " + itneraryId, true);

		} catch (NullPointerException e) {

			addLog(e.getMessage(), true);

		}

		return itneraryId;

	}



	public void verify_SMS_Flow(RemoteWebDriver driver, boolean bookingPassed, String itineraryId,String paxConfig,String ssrConfigs) throws Exception {

		if (bookingPassed) {

			String tripId = getText(driver, getObject("AirCom_Confirmation_TripID"));

			addLog("trip id to search stat is " + tripId,true); // Verify stats for generated tripID

			verifyStats(driver, tripId,paxConfig,ssrConfigs);

		} else if (!itineraryId.equals("")) {

			verifySMSByItinerary(driver, itineraryId);

		} else {

			addLog("Neither itinerary id or trip id generated.", true);

		}

	}



	public void processTravellerDetails(RemoteWebDriver driver, String paxConfig, boolean is_international,

			boolean useGST, boolean useNonDefaultState) throws Exception {



		if (elementVisible(driver, By.id("priceChangeDownBtn"), 2)) {

			safeClick(driver, By.id("priceChangeDownBtn"));

		}



		addLog("Getting the pax info.", true);

		String[] inp = paxConfig.split("-");

		String adults = inp[0];

		String children = inp[1];

		String infants = inp[2];



		addLog("Adding traveller Details", true);

		int num_of_adults = num_adults = Integer.parseInt(adults);

		int num_of_children = num_children = Integer.parseInt(children);

		int num_of_infants = num_infants = Integer.parseInt(infants);



		Thread.sleep(2000);

		smartClick(driver, By.xpath("//*[@id='itineraryBtn']"));

		smartClick(driver, By.xpath("//input[@class='primary nearByAirportWarningBtn']"));



		boolean loginSection = driver.getCurrentUrl().contains("login");

		if (loginSection) {

			printInfo("Login Required");

			System.out.println(dataFile.value("SffLoginUsername"));

			safeType(driver, getObject("step2_email_address_username"), dataFile.value("SffLoginUsername"));

			// safeClick(driver, getObject("step2_password_checkbox"));

			Thread.sleep(500);

			if (elementVisible(driver, getObject("clickcheckbox"), 1)) {

				safeClick(driver, getObject("clickcheckbox"));

			}

			System.out.println(dataFile.value("SffPassword"));

			safeType(driver, getObject("step2_email_password"), dataFile.value("SffPassword"));

			safeClick(driver, getObject("step2_login_button"));
			
		}

		for (int i = 0; i < 7; i++) {
			String curl=driver.getCurrentUrl();
			if(curl.toLowerCase().contains("traveller")) {
				break;
			}else {
				Thread.sleep(3000);
			}
			
		}

		Thread.sleep(3000);



		fillTravellersDetails(driver, num_of_adults, num_of_children, num_of_infants, is_international);

		fillTravellersContact(driver);



		fillGstDetails(driver, useGST, useNonDefaultState);



		safeClick(driver, getObject("air_traveller_details_continue"));
		
		Thread.sleep(5000);
		
		/*safeClick(driver, getObject("air_traveller_details_continue"));
		Thread.sleep(5000);
*/
		if(elementVisible(driver, getObject("AirCom_BookStep4_Schedule_Change_button"), 1)) {

			airlineRescheduled(driver);

		}

		if (elementVisible(driver, By.xpath("//input[@value='Review new timings']"), 1)) {

			safeClick(driver, By.xpath("//input[@value='Review new timings']"));

		}

		if (elementVisible(driver, By.id("priceChangeUpBtn"), 1)) {

			safeClick(driver, By.id("priceChangeUpBtn"));

		}
		
		for (int i = 0; i < 20; i++) {
			String curl=driver.getCurrentUrl();
			if(curl.toLowerCase().contains("pay")) {
				break;
			}else {
				Thread.sleep(3000);
			}
			
		}

	}



	public boolean processPaymentFlow(RemoteWebDriver driver,String paymentMethod, boolean bookingPassed) throws Exception {

		addLog("Processing payment flow...", true);

		if ((common.value("makePayment").toLowerCase().trim().equals("true"))) {

			addLog("Make payment set as true, So Processing payment flow.", true);

			boolean paymentDone = b2cPayment(driver, paymentMethod, 1);

			if (paymentDone == false) {

				addLog("Flight full error popped up. Redirecting to Search result page.", true);

			}else {

				bookingPassed = checkTripID(driver);

			}



		}else {

			addLog("Make payment set as false, So not Processing payment flow.", true);

		}

		return bookingPassed;

	}



	public String clickOnBookButton(RemoteWebDriver driver, String tripType,String airline) throws Exception {

		tripType = tripType.toLowerCase().trim();

		if (tripType.equals("ow")) {

			airCom_SRP_Oneway(driver);

		} else if (tripType.equals("rt") || tripType.equals("mc")) {

			clickRoundTripBookButton(driver);

		} else if (tripType.equals("connected")) {

			connectingFlight(driver);

		} else if (tripType.equals("via")) {

			newVia(driver);

		} else if (tripType.equals("splrt")) {

			boolean flightFound = checkPresenceOfSplRtAndSelectTheSame(driver,airline);

			Assert.assertTrue(flightFound, "The splrt flight for airline "+airline+" is not found.");

			clickRoundTripBookButton(driver);

		}

		String itineraryId = getItineraryId(driver);

		Thread.sleep(5000);

		return itineraryId;

	}

	

	public void selectGivenAirliesForGivenSector_MC(RemoteWebDriver driver,String airline,String origin,String destination,String destination1){

		

		List<WebElement> list1_we=driver.findElements(By.xpath("//div[@data-fromto='DEL_BOM']/nav/ul/li"));

		for (int i = 0; i < list1_we.size(); i++) {

			List<WebElement> list1_we_tbody=list1_we.get(i).findElements(By.xpath("//div/label/table/tbody[contains(@class,'segment')]"));

			if(list1_we_tbody.size() == 1) {

				WebElement we_airline=list1_we_tbody.get(0).findElement(By.xpath("//tr[2]/td/span/span"));

				String airline_name=we_airline.getText();

				if(airline_name.toLowerCase().contains(airline.toLowerCase())) {

					we_airline.click();

					break;

				}

			}else if(list1_we_tbody.size() > 1) {

				for (int j = 0; j < list1_we_tbody.size(); j++) {

					WebElement we_airline=list1_we_tbody.get(j).findElement(By.xpath("//tr[2]/td/span/span"));

					String airline_name=we_airline.getText();

					if(airline_name.toLowerCase().contains(airline.toLowerCase())) {

						

					}

				}

			}

		}

	}

	

	

	

	//SFF

	

	public void isSFFEnabled(String tripId) {

	    boolean isSFF = false;

	    try {



	        HttpResponse statsResponse = APIUtils.getApi("http://"+

					common.value("airapistatsurl") + ":" + common.value("airapitripdetalsport") + "/summary/bookStats?tripId=" + tripId,

					null, 200);

	        System.out.println(" statsResponse : " + statsResponse);


	        
	        
	        String statsResponseStr = IOUtils.toString(statsResponse.getEntity().getContent());

	        isSFF = statsResponseStr.contains("profitOnCtc");

	        System.out.println(" isSFF : " + isSFF);

	        Assert.assertTrue(isSFF,"The trip id " + tripId + " is not in SFF flow");

	        

	    } catch (Exception e) {

	        e.printStackTrace();

	        Assert.fail("Exception occured while checking isSFFEnabled,hence not proceeding furthur.");

	    }

	}

		

		public void verify_SFF_Flow(RemoteWebDriver driver, boolean bookingPassed, String itineraryId, int no_of_pax, boolean IsFliteTypeInternational,String tripType) throws Exception {

			if (bookingPassed) {

				

				String tripId = getText(driver, getObject("AirCom_Confirmation_TripID"));

				//String tripId= "Q1812051002";
				System.out.println("trip id to search stat " + tripId); // Verify stats for generated tripID

				

				//verify sff enabled

				isSFFEnabled(tripId);

				

				//Get prefered from stats (get preferedFare,cf,ctc,pf,pctc)

			    Map<String, Double> data = getPreferredResult(tripId,IsFliteTypeInternational,tripType);

			    

			    //Read ct config property for sff cal

			    Map<String, Object> resultMap  = readProperties();

			    

			    

			    //Calculation based on RT-int,RT-dom,OW-int,OW-dom

			    

			    Map<String, Map<String, String>> onWardTrip ;

			    Map<String, Map<String, String>> returnTrip ;

			    

			    //Get flight number from confirmation page , in case of OW size of list is 1 otherwise its 2

			   List<WebElement> flightNumbers = driver.findElements(By.xpath("//small[@class='flightNumber']"));
			   //String flight_no = "127";
		         

			    if (tripType.equalsIgnoreCase("OW")) 

			    {

			    	 String onwardAir =flightNumbers.get(0).getText();
			    
			    
			         onwardAir = onwardAir.replace(' ', '-');

			       System.out.println("onwardAir - "+onwardAir);



			         String onwardTime =   driver.findElement(By.xpath("//nav[@class='compactItinerary']/ul/li[3]/span[@class='truncate span span24']")).getText();

			         System.out.println("onwardTime - "+onwardTime);

			         String start_end_time[] = onwardTime.split(" � ");

					 String startTime = start_end_time[0];

					 String endTime = start_end_time[1];

					 System.out.println("onward => start time - "+startTime+" and endtime - "+endTime);

			         

			    	onWardTrip = getConnectorResponseForOnward(IsFliteTypeInternational);

			    	

			    	Set<String> keys = onWardTrip.keySet();

					Iterator<String> keyIter = keys.iterator();

					ArrayList<String> onwardAirKey = new ArrayList<>();

					while (keyIter.hasNext()) 

					{

						String key =  keyIter.next();

						if (key.contains(startTime)) 

						{

							if (key.contains(endTime)) {

								

								if (key.contains(onwardAir)) {

									onwardAirKey.add(key);

								}

							}

						}

						

						

					}

					

					System.out.println(onwardAirKey);
					addLog("Our Key " + onwardAirKey);
					addLog(("Total fare "+ onWardTrip.get(onwardAirKey.get(0))));
					addLog(("Markup "+ onWardTrip.get(onwardAirKey.get(0)).get("markup")));

					boolean sffCal = sffCalculation(resultMap,onWardTrip.get(onwardAirKey.get(0)).get("total-fare"),onWardTrip.get(onwardAirKey.get(0)).get("markup"),data,no_of_pax,IsFliteTypeInternational,tripType);
					Assert.assertTrue(sffCal);
					

				}

			    else

			    {

				         

				     String onwardAir =flightNumbers.get(0).getText();

				     onwardAir = onwardAir.replace(' ', '-');

			         System.out.println("onwardAir - "+onwardAir);

				         

				     String returnAir =flightNumbers.get(1).getText();

				     returnAir = returnAir.replace(' ', '-');

			         System.out.println("returnAir - "+returnAir);

			         

				     String onwardTime =  driver.findElement(By.xpath("//nav[@class='compactItinerary']/ul/li[3]/span[@class='truncate span span24']")).getText();

				     System.out.println("onwardTime - "+onwardTime);

				         

				     String start_end_time_onward[] = onwardTime.split(" � ");

					 String startTimeOnward = start_end_time_onward[0];

					 String endTimeOnward = start_end_time_onward[1];

					 

				     String returnTime =  driver.findElement(By.xpath("//nav[@class='compactItinerary']/ul[2]/li[3]")).getText().split("\n")[0];

				     System.out.println("returnTime - "+returnTime);

				         

				     String start_end_time_return[] = returnTime.split(" � ");

					 String startTimeReturn = start_end_time_return[0];

					 String endTimeOnReturn= start_end_time_return[1];

					 

			    	onWardTrip = getConnectorResponseForOnward(IsFliteTypeInternational);

			    	returnTrip = getConnectorResponseForReturn(IsFliteTypeInternational);

			    	

			    	

			    	boolean onwardAirFlag = false;

			    	boolean returnAirFlag = false;

			    	

			    	//Verify for onward

			    	Set<String> keys = onWardTrip.keySet();

					Iterator<String> keyIter = keys.iterator();

					ArrayList<String> onwardAirKey = new ArrayList<>();

					while (keyIter.hasNext()) {

						String key =  keyIter.next();

						if (key.contains(startTimeOnward)) 

						{

							if (key.contains(endTimeOnward)) {

								

								if (key.contains(onwardAir)) {

									onwardAirKey.add(key);

									onwardAirFlag =  true;

								}

							}

						}

						

						

					}

					

					//Verify for return

					keys = returnTrip.keySet();

					keyIter = keys.iterator();

					ArrayList<String> returnAirKey = new ArrayList<>();

					while (keyIter.hasNext()) {

						String key =  keyIter.next();

						if (key.contains(startTimeReturn)) 

						{

							if (key.contains(endTimeOnReturn)) {

								

								if (key.contains(returnAir)) {

									returnAirKey.add(key);

									returnAirFlag =  true;

								}

							}

						}

						

						

					}

					

					if (onwardAirFlag && returnAirFlag) {

						System.out.println("onwardAirKey - " +onwardAirKey);

						System.out.println("returnAirKey - " +returnAirKey);

						
						addLog("Our onward Key " + onwardAirKey);
						addLog("Our return key "+ returnAirKey);
						addLog(("Total fare "+ onWardTrip.get(onwardAirKey.get(0))));
						//addLog(("Markup "+ onWardTrip.get(onwardAirKey.get(0)).get("markup")));

						boolean sffCalForOnward = sffCalculationOnward(resultMap,onWardTrip.get(onwardAirKey.get(0)).get("total-fare"),onWardTrip.get(onwardAirKey.get(0)).get("markup"),data,no_of_pax,IsFliteTypeInternational,tripType);

						boolean sffCalForReturn = sffCalculationReturn(resultMap,returnTrip.get(returnAirKey.get(0)).get("total-fare"),returnTrip.get(returnAirKey.get(0)).get("markup"),data,no_of_pax,IsFliteTypeInternational,tripType);

						if (!(sffCalForOnward && sffCalForReturn)) {

							Assert.fail("");

						}

						

					}

					else {

						System.out.println("airline not found as per connector response.");

						Assert.fail("airline not found as per connector response.");

					}

					

			    }

				

			}  else {

				addLog("trip id not generated.", true);

			}

		}

		

		private boolean sffCalculation(Map<String, Object> resultMap, String total_fare_retail, String markup, Map<String, Double> data, int no_of_pax, boolean IsFliteTypeInternational,String tripType) {

			// TODO Auto-generated method stub

			
			//String ssr = null;
			
			
			double plbPercentage_retail = 0;
			//double plbPercentage_tbf = 0;
			double plbPercentage_tbo = 0;

			//double commission = 0;
			double commission_retail = 0;
			//double commission_tbf = 0;
			double commission_tbo = 0;

			double gdsIncentive_retail =0;
			//double gdsIncentive_tbf =0;
			double gdsIncentive_tbo =0;
			
			
			double depositIncentive_retail =0;
			//double depositIncentive_tbf =0;
			double depositIncentive_tbo =0;


			

		  	double earning_tbo,ctc_retail,ctc_tbo,  profitOnFare,profitOnCTC;

			double fare_tbo,fare_retail,earning_retail;

			

			if (resultMap!=null) 

			{

				

				//System.out.println(configSetting);

				

				 if (resultMap.get("ct.air.sff.plb.percentage")!=null) 

		 	     {
					 plbPercentage_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 plbPercentage_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("plb r "+(String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("plb tbo "+(String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 //plbPercentage = Double.valueOf(configSetting.get("ct.air.sff.plb.percentage").get(dataFile.value(dataFile.value("airline"))));
					
					// plbPercentage_tbf   = Double.valueOf(configSetting.get("ct.air.sff.plb.percentage").get(dataFile.value(dataFile.value("airline_tbf"))));
					 

		 	     }

				 

				 

				 if (resultMap.get("ct.air.sff.commission.percentage")!=null) 

		 	     {
					 
					 commission_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 commission_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("commission retail "+(String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("comm tbo "+(String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 /*commission_retail = Double.valueOf(configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_retail"))));
					 commission_tbf = Double.valueOf(configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_tbf"))));
					 commission_tbo = Double.valueOf(configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_tbo"))));

					addLog("com r "+configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_retail"))));
					addLog("com tbo "+configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_tbo"))));*/

		 	     }

				 

				 if (resultMap.get("ct.air.sff.gds.incentive")!=null) 

		 	     {
					 
					 gdsIncentive_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 gdsIncentive_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("gds  r "+(String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("gds tbo "+(String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));

					/* gdsIncentive_retail = Double.valueOf(configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 gdsIncentive_tbf = Double.valueOf(configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_tbf"))));
					 gdsIncentive_tbo = Double.valueOf(configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));
					 addLog("gds r "+configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 addLog("gds tbo "+configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));*/

		 	     }

				 

				 if (resultMap.get("ct.air.sff.deposit.incentive")!=null) 

		 	     {
					 
					 depositIncentive_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 depositIncentive_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("deposit r "+(String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("deposit tbo "+(String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));

					/* depositIncentive_retail = Double.valueOf(configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 depositIncentive_tbf = Double.valueOf(configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_tbf"))));
					 depositIncentive_tbo = Double.valueOf(configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));
					 

					 addLog("deposit r "+configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 addLog("deposit tbo "+configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));*/
		 	     }

				 

				 System.out.println(resultMap);

	        

				

			   	
			   	if (tripType.equalsIgnoreCase("ow"))
			   		
			   	{
			   		//fare_tbo = (Double.valueOf(total_fare_retail) - (Double.valueOf("ct.air."+dataFile.value("airline")+".bf.decrease.for.coupon")));
			   		//fare_tbo = (Double.valueOf(total_fare_retail)) - (Double.valueOf(configSetting.get("ct.air."+dataFile.value("airline")+".bf.decrease.for.coupon")));
				   
			   		
			   		
			   		fare_retail=(Double.valueOf(total_fare_retail))-(Double.valueOf(markup));
			   		//fare_retail=Double.valueOf(df.format(fare_retail));
			   		
			   		fare_tbo = (Double.valueOf(fare_retail)) - ((((Long)resultMap.get(dataFile.value("i5_decrease_coupon"))))*no_of_pax);
			   		//fare_tbo = Double.valueOf(df.format(fare_tbo));
			   		addLog("fare_retail calculated "+fare_retail);
			   		addLog("fare_tbo calculated"+fare_tbo);
				   	

				   	earning_retail = ((plbPercentage_retail)*fare_retail*0.01)+((commission_retail)*fare_retail*0.01)+(gdsIncentive_retail*no_of_pax)+((depositIncentive_retail)*fare_retail*0.01);
				   	addLog("earning retail "+ earning_retail);
				   	
				   	earning_tbo =    ((plbPercentage_tbo)*fare_tbo*0.01)+((commission_tbo)*fare_tbo*0.01)+(gdsIncentive_tbo*no_of_pax)+((depositIncentive_tbo)*fare_tbo*0.01);
				   	addLog("earning tbo "+earning_tbo);
				   	
				   	
				   	ctc_retail  = (fare_retail-earning_retail);
				   	//ctc_retail =  Double.valueOf(df.format(ctc_retail));
				   	addLog("ctc_retail calculated" + ctc_retail);
				   	
				   	ctc_tbo  = (fare_tbo-earning_tbo);
				   	//ctc_tbo = Double.valueOf(df.format(ctc_tbo));
				   	addLog("ctc tbo calculate "+ ctc_tbo);
				   	
				   	
				   	profitOnFare = ((fare_retail - fare_tbo)/fare_retail);
				   	//profitOnFare =  Double.valueOf(df.format(profitOnFare));
				   	addLog("profitOnFare calculated "+ profitOnFare);
				   	
				   	profitOnCTC = ((ctc_retail - ctc_tbo)/ctc_retail);
				   	//profitOnCTC = Double.valueOf(df.format(profitOnCTC));
				   	addLog("profitOnCTC calculated"+ profitOnCTC);

				   	

				   	SoftAssert softassert = new SoftAssert();
				   	
				   	//Compare actual and expected values - PF, CF, CTC, profitOnFare and profitOnCTC

						double preferedFare = Double.valueOf(data.get("preferedFare"));
						

						double cf= Double.valueOf(data.get("cf"));
						//cf = Double.valueOf(df.format(cf));

						double ctc = Double.valueOf(data.get("ctc"));

						double profitOnFare_stats = Double.valueOf(data.get("profitOnFare"));
						//profitOnFare_stats = Double.valueOf(df.format(profitOnFare_stats));

						double profitOnCTC_stats = Double.valueOf(data.get("profitOnCTC"));
						//profitOnCTC_stats = Double.valueOf(df.format(profitOnCTC_stats));
						
						
						
						DecimalFormat df = new DecimalFormat("###.###");
						profitOnFare_stats = Double.valueOf(df.format(profitOnFare_stats));
						profitOnCTC_stats = Double.valueOf(df.format(profitOnCTC_stats));
						ctc = Double.valueOf(df.format(ctc));
						preferedFare =  Double.valueOf(df.format(preferedFare));
						cf = Double.valueOf(df.format(cf));
						
						profitOnCTC = Double.valueOf(df.format(profitOnCTC));
						profitOnFare = Double.valueOf(df.format(profitOnFare));
						ctc_tbo = Double.valueOf(df.format(ctc_tbo));
						fare_tbo = Double.valueOf(df.format(fare_tbo));
						fare_retail=Double.valueOf(df.format(fare_retail));
						
						
						
						if (Double.compare(fare_retail,preferedFare)==0)
						{

							addLog("Matching PF");

							if (fare_tbo==cf) 

							{

								addLog("Matching CF");

								if (Double.compare(ctc_tbo,ctc)==0) 

								{

									addLog("Matching CTC");

									if (Double.compare(profitOnCTC,profitOnCTC_stats)==0) 

									{

										

										addLog("Matching profitOnCTC");

										if (Double.compare(profitOnFare,profitOnFare_stats)==0)

										{
											addLog("Matching profitOnFare");
											return true;

										}

										else

										{

											return false;

										}

									}

									else

									{

										return false;

									}

									

								}

								else

								{

									return false;

								}

								

							}

							else

							{

								return false;

							}

						}


						

					else

					{
						
						return false;

					}

			   	
			   	}
			   	
			   	else if (tripType.equalsIgnoreCase("rt")) {
			   		//fare_tbo = (Double.valueOf(total_fare_retail) - (Double.valueOf("ct.air."+dataFile.value("airline")+".bf.decrease.for.coupon")));
			   		//fare_tbo = (Double.valueOf(total_fare_retail)) - (Double.valueOf(configSetting.get("ct.air."+dataFile.value("airline")+".bf.decrease.for.coupon")));
				   
			   		
			   		
			   		fare_retail=(Double.valueOf(total_fare_retail))-(Double.valueOf(markup));
			   		//fare_retail=Double.valueOf(df.format(fare_retail));
			   		
			   		fare_tbo = (Double.valueOf(fare_retail)) - ((((Long)resultMap.get(dataFile.value("i5_decrease_coupon"))))*no_of_pax);
			   		//fare_tbo = Double.valueOf(df.format(fare_tbo));
			   		addLog("fare_retail calculated "+fare_retail);
			   		addLog("fare_tbo calculated"+fare_tbo);
				   	

				   	earning_retail = ((plbPercentage_retail)*fare_retail*0.01)+((commission_retail)*fare_retail*0.01)+(gdsIncentive_retail*no_of_pax)+((depositIncentive_retail)*fare_retail*0.01);
				   	addLog("earning retail "+ earning_retail);
				   	
				   	earning_tbo =    ((plbPercentage_tbo)*fare_tbo*0.01)+((commission_tbo)*fare_tbo*0.01)+(gdsIncentive_tbo*no_of_pax)+((depositIncentive_tbo)*fare_tbo*0.01);
				   	addLog("earning tbo "+earning_tbo);
				   	
				   	
				   	ctc_retail  = (fare_retail-earning_retail);
				   	//ctc_retail =  Double.valueOf(df.format(ctc_retail));
				   	addLog("ctc_retail calculated" + ctc_retail);
				   	
				   	ctc_tbo  = (fare_tbo-earning_tbo);
				   	//ctc_tbo = Double.valueOf(df.format(ctc_tbo));
				   	addLog("ctc tbo calculate "+ ctc_tbo);
				   	
				   	
				   	profitOnFare = ((fare_retail - fare_tbo)/fare_retail);
				   	//profitOnFare =  Double.valueOf(df.format(profitOnFare));
				   	addLog("profitOnFare calculated "+ profitOnFare);
				   	
				   	profitOnCTC = ((ctc_retail - ctc_tbo)/ctc_retail);
				   	//profitOnCTC = Double.valueOf(df.format(profitOnCTC));
				   	addLog("profitOnCTC calculated"+ profitOnCTC);

				   	

				   	SoftAssert softassert = new SoftAssert();
				   	
				   	//Compare actual and expected values - PF, CF, CTC, profitOnFare and profitOnCTC

						double preferedFare_OW = Double.valueOf(data.get("preferedFare_OW"));
						double preferedFare_RT = Double.valueOf(data.get("preferedFare_RT"));

						double cf_OW= Double.valueOf(data.get("cf_OW"));
						double cf_RT= Double.valueOf(data.get("cf_RT"));
						//cf = Double.valueOf(df.format(cf));

						double ctc_OW = Double.valueOf(data.get("ctc_OW"));
						double ctc_RT = Double.valueOf(data.get("ctc_RT"));
						
						double profitOnFare_stats_OW = Double.valueOf(data.get("profitOnFare_OW"));
						double profitOnFare_stats_RT = Double.valueOf(data.get("profitOnFare_RT"));

						double profitOnCTC_stats_OW = Double.valueOf(data.get("profitOnCTC_OW"));
						double profitOnCTC_stats_RT = Double.valueOf(data.get("profitOnCTC_RT"));
						
						
						
						DecimalFormat df = new DecimalFormat("###.###");
						profitOnFare_stats_OW = Double.valueOf(df.format(profitOnFare_stats_OW));
						profitOnCTC_stats_OW = Double.valueOf(df.format(profitOnCTC_stats_OW));
						ctc_OW = Double.valueOf(df.format(ctc_OW));
						preferedFare_OW =  Double.valueOf(df.format(preferedFare_OW));
						cf_OW = Double.valueOf(df.format(cf_OW));
						
						
						profitOnFare_stats_RT = Double.valueOf(df.format(profitOnFare_stats_RT));
						profitOnCTC_stats_RT = Double.valueOf(df.format(profitOnCTC_stats_RT));
						ctc_RT = Double.valueOf(df.format(ctc_RT));
						preferedFare_RT =  Double.valueOf(df.format(preferedFare_RT));
						cf_RT = Double.valueOf(df.format(cf_RT));
						
						
						profitOnCTC = Double.valueOf(df.format(profitOnCTC));
						profitOnFare = Double.valueOf(df.format(profitOnFare));
						ctc_tbo = Double.valueOf(df.format(ctc_tbo));
						fare_tbo = Double.valueOf(df.format(fare_tbo));
						fare_retail=Double.valueOf(df.format(fare_retail));
						
						
						
						if (Double.compare(fare_retail,preferedFare_OW)==0)
						{

							addLog("Matching PF");

							if (fare_tbo==cf_OW) 

							{

								addLog("Matching CF");

								if (Double.compare(ctc_tbo,ctc_OW)==0) 

								{

									addLog("Matching CTC");

									if (Double.compare(profitOnCTC,profitOnCTC_stats_OW)==0) 

									{

										

										addLog("Matching profitOnCTC");

										if (Double.compare(profitOnFare,profitOnFare_stats_OW)==0)

										{
											addLog("Matching profitOnFare");
											return true;

										}

										else

										{

											return false;

										}

									}

									else

									{

										return false;

									}

									

								}

								else

								{

									return false;

								}

								

							}

							else

							{

								return false;

							}

						}


						

					else

					{
						
						return false;

					}

			   	
			   	}
			   	
			   	else {
			   		return false;
			   	}
			   	
			   	
			  

	
			

			}

			else

			{

		       	System.out.println("calculation is not possible as CT config values are not available.");

		       	return false;

		    }
			

			   	

	}

		
		
		private boolean sffCalculationOnward(Map<String, Object> resultMap, String total_fare_retail, String markup, Map<String, Double> data, int no_of_pax, boolean IsFliteTypeInternational,String tripType) {

			// TODO Auto-generated method stub

			
			//String ssr = null;
			
			
			double plbPercentage_retail = 0;
			//double plbPercentage_tbf = 0;
			double plbPercentage_tbo = 0;

			//double commission = 0;
			double commission_retail = 0;
			//double commission_tbf = 0;
			double commission_tbo = 0;

			double gdsIncentive_retail =0;
			//double gdsIncentive_tbf =0;
			double gdsIncentive_tbo =0;
			
			
			double depositIncentive_retail =0;
			//double depositIncentive_tbf =0;
			double depositIncentive_tbo =0;


			

		  	double earning_tbo,ctc_retail,ctc_tbo,  profitOnFare,profitOnCTC;

			double fare_tbo,fare_retail,earning_retail;

			

			if (resultMap!=null) 

			{

				

				//System.out.println(configSetting);

				

				 if (resultMap.get("ct.air.sff.plb.percentage")!=null) 

		 	     {
					 plbPercentage_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 plbPercentage_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("plb r "+(String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("plb tbo "+(String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 //plbPercentage = Double.valueOf(configSetting.get("ct.air.sff.plb.percentage").get(dataFile.value(dataFile.value("airline"))));
					
					// plbPercentage_tbf   = Double.valueOf(configSetting.get("ct.air.sff.plb.percentage").get(dataFile.value(dataFile.value("airline_tbf"))));
					 

		 	     }

				 

				 

				 if (resultMap.get("ct.air.sff.commission.percentage")!=null) 

		 	     {
					 
					 commission_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 commission_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("commission retail "+(String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("comm tbo "+(String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 /*commission_retail = Double.valueOf(configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_retail"))));
					 commission_tbf = Double.valueOf(configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_tbf"))));
					 commission_tbo = Double.valueOf(configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_tbo"))));

					addLog("com r "+configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_retail"))));
					addLog("com tbo "+configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_tbo"))));*/

		 	     }

				 

				 if (resultMap.get("ct.air.sff.gds.incentive")!=null) 

		 	     {
					 
					 gdsIncentive_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 gdsIncentive_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("gds  r "+(String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("gds tbo "+(String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));

					/* gdsIncentive_retail = Double.valueOf(configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 gdsIncentive_tbf = Double.valueOf(configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_tbf"))));
					 gdsIncentive_tbo = Double.valueOf(configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));
					 addLog("gds r "+configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 addLog("gds tbo "+configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));*/

		 	     }

				 

				 if (resultMap.get("ct.air.sff.deposit.incentive")!=null) 

		 	     {
					 
					 depositIncentive_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 depositIncentive_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("deposit r "+(String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("deposit tbo "+(String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));

					/* depositIncentive_retail = Double.valueOf(configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 depositIncentive_tbf = Double.valueOf(configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_tbf"))));
					 depositIncentive_tbo = Double.valueOf(configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));
					 

					 addLog("deposit r "+configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 addLog("deposit tbo "+configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));*/
		 	     }

				 

				 System.out.println(resultMap);

	        

				

			  if (tripType.equalsIgnoreCase("rt")) {
			   		//fare_tbo = (Double.valueOf(total_fare_retail) - (Double.valueOf("ct.air."+dataFile.value("airline")+".bf.decrease.for.coupon")));
			   		//fare_tbo = (Double.valueOf(total_fare_retail)) - (Double.valueOf(configSetting.get("ct.air."+dataFile.value("airline")+".bf.decrease.for.coupon")));
				   
			   		
			   		
			   		fare_retail=(Double.valueOf(total_fare_retail))-(Double.valueOf(markup));
			   		//fare_retail=Double.valueOf(df.format(fare_retail));
			   		
			   		fare_tbo = (Double.valueOf(fare_retail)) - ((((Long)resultMap.get(dataFile.value("i5_decrease_coupon"))))*no_of_pax);
			   		//fare_tbo = Double.valueOf(df.format(fare_tbo));
			   		addLog("fare_retail calculated "+fare_retail);
			   		addLog("fare_tbo calculated"+fare_tbo);
				   	

				   	earning_retail = ((plbPercentage_retail)*fare_retail*0.01)+((commission_retail)*fare_retail*0.01)+(gdsIncentive_retail*no_of_pax)+((depositIncentive_retail)*fare_retail*0.01);
				   	addLog("earning retail "+ earning_retail);
				   	
				   	earning_tbo =    ((plbPercentage_tbo)*fare_tbo*0.01)+((commission_tbo)*fare_tbo*0.01)+(gdsIncentive_tbo*no_of_pax)+((depositIncentive_tbo)*fare_tbo*0.01);
				   	addLog("earning tbo "+earning_tbo);
				   	
				   	
				   	ctc_retail  = (fare_retail-earning_retail);
				   	//ctc_retail =  Double.valueOf(df.format(ctc_retail));
				   	addLog("ctc_retail calculated" + ctc_retail);
				   	
				   	ctc_tbo  = (fare_tbo-earning_tbo);
				   	//ctc_tbo = Double.valueOf(df.format(ctc_tbo));
				   	addLog("ctc tbo calculate "+ ctc_tbo);
				   	
				   	
				   	profitOnFare = ((fare_retail - fare_tbo)/fare_retail);
				   	//profitOnFare =  Double.valueOf(df.format(profitOnFare));
				   	addLog("profitOnFare calculated "+ profitOnFare);
				   	
				   	profitOnCTC = ((ctc_retail - ctc_tbo)/ctc_retail);
				   	//profitOnCTC = Double.valueOf(df.format(profitOnCTC));
				   	addLog("profitOnCTC calculated"+ profitOnCTC);

				   	

				   	SoftAssert softassert = new SoftAssert();
				   	
				   	//Compare actual and expected values - PF, CF, CTC, profitOnFare and profitOnCTC

						double preferedFare_OW = Double.valueOf(data.get("preferedFare_OW"));
						//double preferedFare_RT = Double.valueOf(data.get("preferedFare_RT"));

						double cf_OW= Double.valueOf(data.get("cf_OW"));
						//double cf_RT= Double.valueOf(data.get("cf_RT"));
						//cf = Double.valueOf(df.format(cf));

						double ctc_OW = Double.valueOf(data.get("ctc_OW"));
						//double ctc_RT = Double.valueOf(data.get("ctc_RT"));
					
						double profitOnFare_stats_OW = Double.valueOf(data.get("profitonFare_OW"));
						//double profitOnFare_stats_RT = Double.valueOf(data.get("profitonFare_RT"));

						double profitOnCTC_stats_OW = Double.valueOf(data.get("profitonCTC_OW"));
						//double profitOnCTC_stats_RT = Double.valueOf(data.get("profitonCTC_RT"));
						
						
						
						DecimalFormat df = new DecimalFormat("###.###");
						profitOnFare_stats_OW = Double.valueOf(df.format(profitOnFare_stats_OW));
						profitOnCTC_stats_OW = Double.valueOf(df.format(profitOnCTC_stats_OW));
						ctc_OW = Double.valueOf(df.format(ctc_OW));
						preferedFare_OW =  Double.valueOf(df.format(preferedFare_OW));
						cf_OW = Double.valueOf(df.format(cf_OW));
						
						
						
						
						profitOnCTC = Double.valueOf(df.format(profitOnCTC));
						profitOnFare = Double.valueOf(df.format(profitOnFare));
						ctc_tbo = Double.valueOf(df.format(ctc_tbo));
						fare_tbo = Double.valueOf(df.format(fare_tbo));
						fare_retail=Double.valueOf(df.format(fare_retail));
						
						
						
						if (Double.compare(fare_retail,preferedFare_OW)==0)
						{

							addLog("Matching PF");

							if (fare_tbo==cf_OW) 

							{

								addLog("Matching CF");

								if (Double.compare(ctc_tbo,ctc_OW)==0) 

								{

									addLog("Matching CTC");

									if (Double.compare(profitOnCTC,profitOnCTC_stats_OW)==0) 

									{

										

										addLog("Matching profitOnCTC");

										if (Double.compare(profitOnFare,profitOnFare_stats_OW)==0)

										{
											addLog("Matching profitOnFare");
											return true;

										}

										else

										{

											return false;

										}

									}

									else

									{

										return false;

									}

									

								}

								else

								{

									return false;

								}

								

							}

							else

							{

								return false;

							}

						}


						

					else

					{
						
						return false;

					}

			   	
			   	}
			   	
			   	else {
			   		return false;
			   	}
			   	
			   	
			  

	
			

			}

			else

			{

		       	System.out.println("calculation is not possible as CT config values are not available.");

		       	return false;

		    }
			

			   	

	}

		
		private boolean sffCalculationReturn(Map<String, Object> resultMap, String total_fare_retail, String markup, Map<String, Double> data, int no_of_pax, boolean IsFliteTypeInternational,String tripType) {

			// TODO Auto-generated method stub

			
			//String ssr = null;
			
			
			double plbPercentage_retail = 0;
			//double plbPercentage_tbf = 0;
			double plbPercentage_tbo = 0;

			//double commission = 0;
			double commission_retail = 0;
			//double commission_tbf = 0;
			double commission_tbo = 0;

			double gdsIncentive_retail =0;
			//double gdsIncentive_tbf =0;
			double gdsIncentive_tbo =0;
			
			
			double depositIncentive_retail =0;
			//double depositIncentive_tbf =0;
			double depositIncentive_tbo =0;


			

		  	double earning_tbo,ctc_retail,ctc_tbo,  profitOnFare,profitOnCTC;

			double fare_tbo,fare_retail,earning_retail;

			

			if (resultMap!=null) 

			{

				

				//System.out.println(configSetting);

				

				 if (resultMap.get("ct.air.sff.plb.percentage")!=null) 

		 	     {
					 plbPercentage_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 plbPercentage_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("plb r "+(String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("plb tbo "+(String)(((Map)resultMap.get("ct.air.sff.plb.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 //plbPercentage = Double.valueOf(configSetting.get("ct.air.sff.plb.percentage").get(dataFile.value(dataFile.value("airline"))));
					
					// plbPercentage_tbf   = Double.valueOf(configSetting.get("ct.air.sff.plb.percentage").get(dataFile.value(dataFile.value("airline_tbf"))));
					 

		 	     }

				 

				 

				 if (resultMap.get("ct.air.sff.commission.percentage")!=null) 

		 	     {
					 
					 commission_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 commission_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("commission retail "+(String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("comm tbo "+(String)(((Map)resultMap.get("ct.air.sff.commission.percentage")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 /*commission_retail = Double.valueOf(configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_retail"))));
					 commission_tbf = Double.valueOf(configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_tbf"))));
					 commission_tbo = Double.valueOf(configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_tbo"))));

					addLog("com r "+configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_retail"))));
					addLog("com tbo "+configSetting.get("ct.air.sff.commission.percentage").get(dataFile.value(dataFile.value("airline_tbo"))));*/

		 	     }

				 

				 if (resultMap.get("ct.air.sff.gds.incentive")!=null) 

		 	     {
					 
					 gdsIncentive_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 gdsIncentive_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("gds  r "+(String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("gds tbo "+(String)(((Map)resultMap.get("ct.air.sff.gds.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));

					/* gdsIncentive_retail = Double.valueOf(configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 gdsIncentive_tbf = Double.valueOf(configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_tbf"))));
					 gdsIncentive_tbo = Double.valueOf(configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));
					 addLog("gds r "+configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 addLog("gds tbo "+configSetting.get("ct.air.sff.gds.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));*/

		 	     }

				 

				 if (resultMap.get("ct.air.sff.deposit.incentive")!=null) 

		 	     {
					 
					 depositIncentive_retail = Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
					 depositIncentive_tbo   =  Double.valueOf((String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));
					 
						addLog("deposit r "+(String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_retail"))))));
						addLog("deposit tbo "+(String)(((Map)resultMap.get("ct.air.sff.deposit.incentive")).get((dataFile.value(dataFile.value("airline_tbo"))))));

					/* depositIncentive_retail = Double.valueOf(configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 depositIncentive_tbf = Double.valueOf(configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_tbf"))));
					 depositIncentive_tbo = Double.valueOf(configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));
					 

					 addLog("deposit r "+configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_retail"))));
					 addLog("deposit tbo "+configSetting.get("ct.air.sff.deposit.incentive").get(dataFile.value(dataFile.value("airline_tbo"))));*/
		 	     }

				 

				 System.out.println(resultMap);

	        

				

			  if (tripType.equalsIgnoreCase("rt")) {
			   		//fare_tbo = (Double.valueOf(total_fare_retail) - (Double.valueOf("ct.air."+dataFile.value("airline")+".bf.decrease.for.coupon")));
			   		//fare_tbo = (Double.valueOf(total_fare_retail)) - (Double.valueOf(configSetting.get("ct.air."+dataFile.value("airline")+".bf.decrease.for.coupon")));
				   
			   		
			   		
			   		fare_retail=(Double.valueOf(total_fare_retail))-(Double.valueOf(markup));
			   		//fare_retail=Double.valueOf(df.format(fare_retail));
			   		
			   		fare_tbo = (Double.valueOf(fare_retail)) - ((((Long)resultMap.get(dataFile.value("i5_decrease_coupon"))))*no_of_pax);
			   		//fare_tbo = Double.valueOf(df.format(fare_tbo));
			   		addLog("fare_retail calculated "+fare_retail);
			   		addLog("fare_tbo calculated"+fare_tbo);
				   	

				   	earning_retail = ((plbPercentage_retail)*fare_retail*0.01)+((commission_retail)*fare_retail*0.01)+(gdsIncentive_retail*no_of_pax)+((depositIncentive_retail)*fare_retail*0.01);
				   	addLog("earning retail "+ earning_retail);
				   	
				   	earning_tbo =    ((plbPercentage_tbo)*fare_tbo*0.01)+((commission_tbo)*fare_tbo*0.01)+(gdsIncentive_tbo*no_of_pax)+((depositIncentive_tbo)*fare_tbo*0.01);
				   	addLog("earning tbo "+earning_tbo);
				   	
				   	
				   	ctc_retail  = (fare_retail-earning_retail);
				   	//ctc_retail =  Double.valueOf(df.format(ctc_retail));
				   	addLog("ctc_retail calculated" + ctc_retail);
				   	
				   	ctc_tbo  = (fare_tbo-earning_tbo);
				   	//ctc_tbo = Double.valueOf(df.format(ctc_tbo));
				   	addLog("ctc tbo calculate "+ ctc_tbo);
				   	
				   	
				   	profitOnFare = ((fare_retail - fare_tbo)/fare_retail);
				   	//profitOnFare =  Double.valueOf(df.format(profitOnFare));
				   	addLog("profitOnFare calculated "+ profitOnFare);
				   	
				   	profitOnCTC = ((ctc_retail - ctc_tbo)/ctc_retail);
				   	//profitOnCTC = Double.valueOf(df.format(profitOnCTC));
				   	addLog("profitOnCTC calculated"+ profitOnCTC);

				   	

				   	SoftAssert softassert = new SoftAssert();
				   	
				   	//Compare actual and expected values - PF, CF, CTC, profitOnFare and profitOnCTC

						//double preferedFare_OW = Double.valueOf(data.get("preferedFare_OW"));
						double preferedFare_RT = Double.valueOf(data.get("preferedFare_RT"));

						//double cf_OW= Double.valueOf(data.get("cf_OW"));
						double cf_RT= Double.valueOf(data.get("cf_RT"));
						//cf = Double.valueOf(df.format(cf));

						//double ctc_OW = Double.valueOf(data.get("ctc_OW"));
						double ctc_RT = Double.valueOf(data.get("ctc_RT"));
						
						//double profitOnFare_stats_OW = Double.valueOf(data.get("profitonFare_OW"));
						double profitOnFare_stats_RT = Double.valueOf(data.get("profitonFare_RT"));

						//double profitOnCTC_stats_OW = Double.valueOf(data.get("profitonCTC_OW"));
						double profitOnCTC_stats_RT = Double.valueOf(data.get("profitonCTC_RT"));
						
						
						
						DecimalFormat df = new DecimalFormat("###.###");

						profitOnFare_stats_RT = Double.valueOf(df.format(profitOnFare_stats_RT));
						profitOnCTC_stats_RT = Double.valueOf(df.format(profitOnCTC_stats_RT));
						ctc_RT = Double.valueOf(df.format(ctc_RT));
						preferedFare_RT =  Double.valueOf(df.format(preferedFare_RT));
						cf_RT = Double.valueOf(df.format(cf_RT));
						
						
						
						
						profitOnCTC = Double.valueOf(df.format(profitOnCTC));
						profitOnFare = Double.valueOf(df.format(profitOnFare));
						ctc_tbo = Double.valueOf(df.format(ctc_tbo));
						fare_tbo = Double.valueOf(df.format(fare_tbo));
						fare_retail=Double.valueOf(df.format(fare_retail));
						
						
						
						if (Double.compare(fare_retail,preferedFare_RT)==0)
						{

							addLog("Matching PF");

							if (fare_tbo==cf_RT) 

							{

								addLog("Matching CF");

								if (Double.compare(ctc_tbo,ctc_RT)==0) 

								{

									addLog("Matching CTC");

									if (Double.compare(profitOnCTC,profitOnCTC_stats_RT)==0) 

									{

										

										addLog("Matching profitOnCTC");

										if (Double.compare(profitOnFare,profitOnFare_stats_RT)==0)

										{
											addLog("Matching profitOnFare");
											return true;

										}

										else

										{

											return false;

										}

									}

									else

									{

										return false;

									}

									

								}

								else

								{

									return false;

								}

								

							}

							else

							{

								return false;

							}

						}


						

					else

					{
						
						return false;

					}

			   	
			   	}
			   	
			   	else {
			   		return false;
			   	}
			   	
			   	
			  

	
			

			}

			else

			{

		       	System.out.println("calculation is not possible as CT config values are not available.");

		       	return false;

		    }
			

			   	

	}



		public Map<String,Map<String, String>> getConnectorResponseForOnward(boolean IsFliteTypeInternational) {

		    Map<String, Map<String, String>> result = new HashMap<>();



		    try {

		    	

		    	HttpResponse connectorResponse = APIUtils.getApi(connectorSearchUrl,null, 200);

		    	

		        System.out.println("" + connectorResponse);
		        addLog("Connector Response "+ connectorResponse );


		        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();



		        DocumentBuilder builder = builderFactory.newDocumentBuilder();

		        Document xmlDocument = builder.parse(connectorResponse.getEntity().getContent());

		        XPath xPath = XPathFactory.newInstance().newXPath();



		        String expression = new String();

		        if (IsFliteTypeInternational) 

		        {

		        	expression = "//solutions/solution";

				}

		        else

		        {

		        	expression = "//onward-solutions/solution";

		        }

		        

		        org.w3c.dom.NodeList nodeList = (org.w3c.dom.NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);



		        for (int index = 0; index < nodeList.getLength(); index++) {

		            Map<String, String> data = new HashMap<>();



		            String bookingStatus = ((Element) ((Node) XPathFactory.newInstance().newXPath().compile("./pricing-summary/base-fare").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();



		            String markup = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                    .compile("./pricing-summary/markup").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();



		            String taxes = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                    .compile("./pricing-summary/taxes").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();



		            String totalFare = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                    .compile("./pricing-summary/total-fare").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();





		            data.put("total-fare", totalFare);

		            data.put("taxes", taxes);

		            data.put("markup", markup);

		            data.put("bookingStatus", bookingStatus);



		            org.apache.xml.dtm.ref.DTMNodeList flightList = (org.apache.xml.dtm.ref.DTMNodeList) xPath.compile("./flights/flight").evaluate(nodeList.item(index), XPathConstants.NODESET);



		            for (int index1 = 0; index1 < flightList.getLength(); index1++) {

		                org.apache.xml.dtm.ref.DTMNodeList segments = (org.apache.xml.dtm.ref.DTMNodeList) xPath.compile("./segments/segment").evaluate(flightList.item(index1), XPathConstants.NODESET);

		                StringBuilder sb = new StringBuilder();



		                for (int index2 = 0; index2 < segments.getLength(); index2++) {





		                    String arrivalDateTime = ((Element) ((Node) XPathFactory.newInstance().newXPath()



		                            .compile("./arrival-date-time").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();



		                    String flightNumber = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                            .compile("./flight-number").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String airlines = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                            .compile("./airline").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String departureDateTime = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                            .compile("./departure-date-time").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String departureAirport = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                            .compile("./departure-airport").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String arrivalAirport = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                            .compile("./arrival-airport").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();



		                    sb.append(arrivalAirport);

		                    sb.append("-");

		                    sb.append(departureAirport);

		                    sb.append("-");

		                    sb.append(departureDateTime);

		                    sb.append("-");

		                    sb.append(arrivalDateTime);

		                    sb.append("-");

		                    sb.append(airlines);

		                    sb.append("-");

		                    sb.append(flightNumber);



		                }



		                result.put(sb.toString(), data);





		            }





		        }

		    }catch (Exception e) {

		        e.printStackTrace();

		    }

		    

		    System.out.println("result " + result);
		    addLog("Result " + result);
		    return result;

		}

		

		

		public Map<String,Map<String, String>> getConnectorResponseForReturn(boolean IsFliteTypeInternational) {

		    Map<String, Map<String, String>> result1 = new HashMap<>();



		    try {

		    	

		    	HttpResponse connectorResponse = APIUtils.getApi(connectorSearchUrl,null, 200);

		    	

		        System.out.println("" + connectorResponse);



		        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();



		        DocumentBuilder builder = builderFactory.newDocumentBuilder();

		        Document xmlDocument = builder.parse(connectorResponse.getEntity().getContent());

		        XPath xPath = XPathFactory.newInstance().newXPath();



		        String expression = new String();

		        if (IsFliteTypeInternational) 

		        {

		        	expression = "//solutions/solution";

				}

		        else

		        {

		        	expression = "//return-solutions/solution";

		        }

		        

		        org.w3c.dom.NodeList nodeList = (org.w3c.dom.NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);



		        for (int index = 0; index < nodeList.getLength(); index++) {

		            Map<String, String> data = new HashMap<>();



		            String baseFare = ((Element) ((Node) XPathFactory.newInstance().newXPath().compile("./pricing-summary/base-fare").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();



		            String markup = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                    .compile("./pricing-summary/markup").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();



		            String taxes = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                    .compile("./pricing-summary/taxes").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();



		            String totalFare = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                    .compile("./pricing-summary/total-fare").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();





		            data.put("total-fare", totalFare);

		            data.put("taxes", taxes);

		            data.put("markup", markup);

		            data.put("baseFare", baseFare);



		            org.apache.xml.dtm.ref.DTMNodeList flightList = (org.apache.xml.dtm.ref.DTMNodeList) xPath.compile("./flights/flight").evaluate(nodeList.item(index), XPathConstants.NODESET);



		            for (int index1 = 0; index1 < flightList.getLength(); index1++) {

		                org.apache.xml.dtm.ref.DTMNodeList segments = (org.apache.xml.dtm.ref.DTMNodeList) xPath.compile("./segments/segment").evaluate(flightList.item(index1), XPathConstants.NODESET);

		                StringBuilder sb = new StringBuilder();



		                for (int index2 = 0; index2 < segments.getLength(); index2++) {





		                    String arrivalDateTime = ((Element) ((Node) XPathFactory.newInstance().newXPath()



		                            .compile("./arrival-date-time").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();



		                    String flightNumber = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                            .compile("./flight-number").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String airlines = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                            .compile("./airline").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String departureDateTime = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                            .compile("./departure-date-time").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String departureAirport = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                            .compile("./departure-airport").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String arrivalAirport = ((Element) ((Node) XPathFactory.newInstance().newXPath()

		                            .compile("./arrival-airport").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();



		                    sb.append(arrivalAirport);

		                    sb.append("-");

		                    sb.append(departureAirport);

		                    sb.append("-");

		                    sb.append(departureDateTime);

		                    sb.append("-");

		                    sb.append(arrivalDateTime);

		                    sb.append("-");

		                    sb.append(airlines);

		                    sb.append("-");

		                    sb.append(flightNumber);



		                }



		               // result.put(sb.toString(), data);
		                result1.put(sb.toString(), data);




		            }





		        }

		    }catch (Exception e) {

		        e.printStackTrace();

		    }

		    

		    System.out.println("result 1" + result1);
		    addLog("Result1 " + result1);
		    return result1;

		}



		
		public Map<String, Object> readProperties() throws Exception {

			

			 //HashMap<String,HashMap<String,String>> configSetting = new HashMap<>();

		    Map<String, String> map = new HashMap<>();
		   
		    Map<String, Object> resultMap = new HashMap<>();
		    
		    String listOfProperties = dataFile.value("properties");
		    String prop = dataFile.value("creds");
		    try {

		    	

		    	HttpResponse connectorResponse = APIUtils.getApi(

						common.value("airapitripdetalsurl") + ":" + common.value("airapitripdetalsport") + "/common/inspect_resource?beanName=commonCachedProperties&json=false",

						null, 200);

		    	

		        System.out.println("" + connectorResponse);

		        final Properties properties = new Properties();

		        properties.load(new StringReader(IOUtils.toString(connectorResponse.getEntity().getContent())));

		        map = new HashMap<String, String>((Map) properties);

		       // Iterator<Map.Entry<String, String>> data = map.entrySet().iterator();

		        for (String key : listOfProperties.split(",")) {
		            String result = map.get(key);
		            Map<String, String> res = new HashMap<>();
		            Object jsonObject = (Object) new JSONParser().parse(new StringReader(result));
		            if (jsonObject instanceof Map) {
		                for (String k : prop.split(",")) {
		                    res.put(k, (String) ((Map) ((Map)jsonObject).get(k)).values().iterator().next());
		                    resultMap.put(key, res);
		                }
		            } else {
		                resultMap.put(key, jsonObject);
		            }

		        }

		      /* while (data.hasNext()) {

		            Map.Entry<String, String> d = data.next();

		            System.out.println(d.getKey() + "    " + d.getValue());



		        }
*/
		       

		        //Process for calculation when config contains below setting

		     if (resultMap.containsKey("ct.air.sff.plb.percentage") && resultMap.containsKey("ct.air.sff.commission.percentage") && resultMap.containsKey("ct.air.sff.gds.incentive") && resultMap.containsKey("ct.air.sff.deposit.incentive")) {
		        	
		    	 
		    	 
		    	 System.out.println("ct.air.sff.commission.percentage - prod_staging_IN_book_newskies_retail = " + ((Map)resultMap.get("ct.air.sff.commission.percentage")).get("prod_staging_IN_book_newskies_retail"));
		    	 System.out.println("ct.air.sff.commission.percentage - production_staging_IN_book_goair_newskies_tbf = " + ((Map)resultMap.get("ct.air.sff.commission.percentage")).get("production_staging_IN_book_goair_newskies_tbf"));
		    	 System.out.println("ct.air.sff.plb.percentage - production_staging_IN_book_goair_newskies_tbf = " + ((Map)resultMap.get("ct.air.sff.plb.percentage")).get("prod_staging_IN_book_newskies_retail"));
		    	 System.out.println("ct.air.sff.plb.percentage - prod_staging_IN_book_newskies_retail = " + ((Map)resultMap.get("ct.air.sff.plb.percentage")).get("production_staging_IN_book_goair_newskies_tbf"));
		    	 System.out.println("ct.air.g8.bf.decrease.for.coupon = " + resultMap.get("ct.air.g8.bf.decrease.for.coupon"));
		        /*
		        	//For ct.air.sff.plb.percentage
		        	HashMap<String, String> keyValue_retail = parseTheValue(map.get("ct.air.sff.plb.percentage"),dataFile.value("airline_retail"));
		        	HashMap<String, String> keyValue_tbo = parseTheValue(map.get("ct.air.sff.plb.percentage"),dataFile.value("airline_tbo"));
		        	
		 	    

		        	//HashMap<String, String> keyValue = new Gson().fromJson(map.get("ct.air.sff.plb.percentage"), new TypeToken<HashMap<String, String>>() {}.getType());

		        	//JSONObject jsonObject = (JSONObject) new JSONParser().parse(new StringReader(map.get("ct.air.sff.plb.percentage")));
		        	//HashMap<String,String> keyValue = (HashMap<String, String>) new JSONParser().parse(new StringReader(map.get("ct.air.sff.plb.percentage")));
		 	        configSetting.put("ct.air.sff.plb.percentage", keyValue_retail);
		 	        configSetting.put("ct.air.sff.plb.percentage", keyValue_tbo);

		 	        

		 	        //For "ct.air.sff.commission.percentage"
		 	        keyValue_retail = parseTheValue(map.get("ct.air.sff.commission.percentage"),dataFile.value("airline_retail"));
		        	keyValue_tbo = parseTheValue(map.get("ct.air.sff.commission.percentage"),dataFile.value("airline_tbo"));
		        	
		 	       //keyValue = (HashMap<String, String>) new JSONParser().parse(new StringReader(map.get("ct.air.sff.commission.percentage")));
		 	        configSetting.put("ct.air.sff.commission.percentage", keyValue_retail);
		 	        configSetting.put("ct.air.sff.plb.percentage", keyValue_tbo);
		 	        

		 	        //For "ct.air.sff.gds.incentive"
		 	       keyValue_retail = parseTheValue(map.get("ct.air.sff.gds.incentive"),dataFile.value("airline_retail"));
		 	       keyValue_tbo = parseTheValue(map.get("ct.air.sff.gds.incentive"),dataFile.value("airline_tbo"));
		        	
		 	        
		 	       //keyValue = (HashMap<String, String>) new JSONParser().parse(new StringReader(map.get("ct.air.sff.gds.incentive")));
		 	        configSetting.put("ct.air.sff.gds.incentive", keyValue_retail);
		 	        configSetting.put("ct.air.sff.plb.percentage", keyValue_tbo);
		 	        

		 	        //For "ct.air.sff.deposit.incentive"
		 	       keyValue_retail = parseTheValue(map.get("ct.air.sff.deposit.incentive"),dataFile.value("airline_retail"));
		 	       keyValue_tbo = parseTheValue(map.get("ct.air.sff.deposit.incentive"),dataFile.value("airline_tbo"));
		 	        
		 	       //keyValue =(HashMap<String, String>) new JSONParser().parse(new StringReader(map.get("ct.air.sff.deposit.incentive")));

		 	        configSetting.put("ct.air.sff.deposit.incentive", keyValue_retail);
		 	        configSetting.put("ct.air.sff.plb.percentage", keyValue_tbo);
		 	        

		 	        //Print all key=value pair

		 	        //System.out.println(configSetting);
		 	        addLog("Config Setting "+ configSetting);*/
	
		 	        

				}

		        else {

		        	System.out.println("calculation is not possible as CT config values are not available.");

		        	Assert.fail("calculation is not possible as CT config values are not available.");

		        }

		       

		        

		    } catch (Exception e) {

		        e.printStackTrace();

		    }

			return resultMap;

		}

		

	/*	private HashMap<String, String> parseTheValue(String responseVal,String airlineCode) throws ParseException, JSONException 

		{

			 



			 JSONParser parser = new JSONParser();

			 

			 String valRespectiveToAirline = dataFile.value(airlineCode);



			 Object obj = parser.parse(responseVal);

			 org.json.simple.JSONObject jsonObject =  (org.json.simple.JSONObject) obj;

			 org.json.simple.JSONObject keyvalObj = (org.json.simple.JSONObject) jsonObject.get(valRespectiveToAirline);

			 HashMap<String, String> keyval = new HashMap<>();

			 

			 //If its empty json object then return null object 

			 if (keyvalObj!=null) 

			 {

				 String value  = (String) keyvalObj.get("*");

				 keyval.put(valRespectiveToAirline, value);

				 System.out.println(keyval);

			}

			 return keyval;

			         

		}

*/

		public Map<String, Double> getPreferredResult(String tripId,boolean IsFliteTypeInternational,String tripType) throws Exception {

		    Map<String, Double> data = new HashMap<>();



		    try {

		    	

		    	

		    	HttpResponse statsResponse = APIUtils.getApi("http://"+

						common.value("airapistatsurl") + ":" + common.value("airapitripdetalsport") + "/summary/bookStats?tripId=" + tripId,

						null, 200);

		        System.out.println(" statsResponse : " + statsResponse);



		        String statsResponseStr = IOUtils.toString(statsResponse.getEntity().getContent());

		        if ((tripType.equalsIgnoreCase("OW") && !IsFliteTypeInternational) || (tripType.equalsIgnoreCase("OW") && IsFliteTypeInternational) || (tripType.equalsIgnoreCase("RT") && IsFliteTypeInternational)) 

		        {

		        	 //ProfitOnCTC

		            String profitonCTC = statsResponseStr.substring(statsResponseStr.lastIndexOf("profitOnCtc"), statsResponseStr.lastIndexOf("profitOnCtc") + 100);

		            profitonCTC = profitonCTC.substring(16, profitonCTC.lastIndexOf(">"));
		            
		            addLog("ProfitonCTC stats: "+ profitonCTC);

		            data.put("profitOnCTC", Double.valueOf(profitonCTC));

		            

		            //ProfitOnFare

		            String profitonFare = statsResponseStr.substring(statsResponseStr.lastIndexOf("profitOnFare"), statsResponseStr.lastIndexOf("profitOnFare") + 100);

		            profitonFare = profitonFare.substring(17, profitonFare.lastIndexOf(">"));

		            addLog("ProfitonFare stats: "+ profitonFare);

		            data.put("profitOnFare", Double.valueOf(profitonFare));

		            

		            //PF,CF,CTC

			        String findCheapest = statsResponseStr.substring(statsResponseStr.lastIndexOf("cheapest Solution selected"), statsResponseStr.lastIndexOf("cheapest Solution selected") + 100);

			        findCheapest = findCheapest.replaceAll("cheapest Solution selected.", "");

			        findCheapest = findCheapest.substring(0, findCheapest.lastIndexOf(">"));

			        System.out.println(" findCheapest : " + findCheapest);

			        String preferedFare = findCheapest.split("and")[0].replace("PF:", "");

			        String cf = findCheapest.split("and")[1].split(".CTC")[0].replace("CF: ", "");

			        String ctc = findCheapest.split("and")[1].split(".CTC:")[1];

			        addLog(" preferedFare stats : " + preferedFare);

			        addLog(" cf stats: " + cf);

			        addLog(" ctc stats:  " + ctc);

			        data.put("preferedFare", Double.valueOf(preferedFare));

			        data.put("cf", Double.valueOf(cf));

			        data.put("ctc", Double.valueOf(ctc));

			        return data;

				}

		        else

		        {

		        	//Only for DOM-RT

		        	

		        	 //ProfitOnCTC

		        	String profitonCTC_OW = statsResponseStr.substring(statsResponseStr.indexOf("profitOnCtc"), statsResponseStr.indexOf("profitOnCtc") + 100);

		        	profitonCTC_OW = profitonCTC_OW.substring(16, profitonCTC_OW.lastIndexOf(">"));

		            System.out.println("profitonCTC_OW : "+ profitonCTC_OW);

		            data.put("profitonCTC_OW", Double.valueOf(profitonCTC_OW));

		            

		            String profitonCTC_RT = statsResponseStr.substring(statsResponseStr.lastIndexOf("profitOnCtc"), statsResponseStr.lastIndexOf("profitOnCtc") + 100);

		            profitonCTC_RT = profitonCTC_RT.substring(16, profitonCTC_RT.lastIndexOf(">"));

		            System.out.println("profitonCTC_RT : "+ profitonCTC_RT);

		            data.put("profitonCTC_RT", Double.valueOf(profitonCTC_RT));

		            

		            //ProfitOnFare
		          
		            String profitonFare_OW = statsResponseStr.substring(statsResponseStr.indexOf("profitOnFare"), statsResponseStr.indexOf("profitOnFare") + 100);

		            profitonFare_OW = profitonFare_OW.substring(17, profitonFare_OW.lastIndexOf(">"));

		            System.out.println("profitonFare_OW : "+ profitonFare_OW);

		            data.put("profitonFare_OW", Double.valueOf(profitonFare_OW));

		            

		            String profitonFare_RT = statsResponseStr.substring(statsResponseStr.lastIndexOf("profitOnFare"), statsResponseStr.lastIndexOf("profitOnFare") + 100);

		            profitonFare_RT = profitonFare_RT.substring(17, profitonFare_RT.lastIndexOf(">"));

		            System.out.println("profitonFare_RT : "+ profitonFare_RT);

		            data.put("profitonFare_RT", Double.valueOf(profitonFare_RT));

		            

		            //PF,CF,CTC

		            

		            String findCheapest_OW = statsResponseStr.substring(statsResponseStr.indexOf("cheapest Solution selected"), statsResponseStr.indexOf("cheapest Solution selected") + 100);

		            findCheapest_OW = findCheapest_OW.replaceAll("cheapest Solution selected.", "");

		            findCheapest_OW = findCheapest_OW.substring(0, findCheapest_OW.lastIndexOf(">"));

			        System.out.println(" findCheapest_OW : " + findCheapest_OW);

			        

			        String preferedFare_OW = findCheapest_OW.split("and")[0].replace("PF:", "");

			        String cf_OW = findCheapest_OW.split("and")[1].split(".CTC")[0].replace("CF: ", "");

			        String ctc_OW = findCheapest_OW.split("and")[1].split(".CTC:")[1];

			        System.out.println(" preferedFare_OW : " + preferedFare_OW);

			        System.out.println(" cf_OW : " + cf_OW);

			        System.out.println(" ctc_OW:  " + ctc_OW);

			        

			        data.put("preferedFare_OW", Double.valueOf(preferedFare_OW));

			        data.put("cf_OW", Double.valueOf(cf_OW));

			        data.put("ctc_OW", Double.valueOf(ctc_OW));

			        

			        

			        String findCheapest_RT = statsResponseStr.substring(statsResponseStr.lastIndexOf("cheapest Solution selected"), statsResponseStr.lastIndexOf("cheapest Solution selected") + 100);

			        findCheapest_RT = findCheapest_RT.replaceAll("cheapest Solution selected.", "");

			        findCheapest_RT = findCheapest_RT.substring(0, findCheapest_RT.lastIndexOf(">"));

			        System.out.println(" findCheapest_RT : " + findCheapest_RT);

			        

			        String preferedFare_RT = findCheapest_RT.split("and")[0].replace("PF:", "");

			        String cf_RT = findCheapest_RT.split("and")[1].split(".CTC")[0].replace("CF: ", "");

			        String ctc_RT = findCheapest_RT.split("and")[1].split(".CTC:")[1];

			        System.out.println(" preferedFare_RT : " + preferedFare_RT);

			        System.out.println(" cf_RT : " + cf_RT);

			        System.out.println(" ctc_RT:  " + ctc_RT);

			        

			        data.put("preferedFare_RT", Double.valueOf(preferedFare_RT));

			        data.put("cf_RT", Double.valueOf(cf_RT));

			        data.put("ctc_RT", Double.valueOf(ctc_RT));

			        return data;

		        }

		       

		    } catch (Exception e) {

		        e.printStackTrace();

		    }

		    return data;

		}

		

	

		public void verifySFFByItinerary(RemoteWebDriver driver, String itneraryId) throws Exception {

			System.out.println("inside verifySMSByItinerary");

			driver.get("http://" + dataFile.value("SffLoginUsername") + ":" + dataFile.value("SffPassword") + "@"

					+ common.value("airapistatsurl") + "/bookreports.php?itineraryId=" + itneraryId);

			// Verify log contains SMS pattern



			Assert.assertEquals(driver.getPageSource().contains("Trip going into sff flow"), true,

					"The itinerary id " + itneraryId + " is not in SFF flow");

		}



		

		public void isResponseNotProper(String tripId,SoftAssert soft_assert) {

		    boolean isError = false;

		    try {



		        String statsURL = "http://172.17.12.171:9080/summary/bookStats?tripId=" + tripId;



		        addLog("Stats Summary URL : " + statsURL,true);



		        HttpResponse statsResponse = APIUtils.getApiWithAuth(statsURL, null, 200);

		        addLog("Stats Response : " + statsResponse,true);



		        String statsResponseStr = IOUtils.toString(statsResponse.getEntity().getContent());

		        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) new JSONParser().parse(new StringReader(statsResponseStr));



		        JSONArray jsonValues = (JSONArray) jsonObject.get("air_api_calls");

		        boolean p=true;

		        for (int index = 0; index < jsonValues.size(); index++) {

		            Long httpCode = (Long) ((org.json.simple.JSONObject) jsonValues.get(index)).get("http_code");

		            String tid = (String) ((org.json.simple.JSONObject) jsonValues.get(index)).get("tid");



		            String event = (String) ((org.json.simple.JSONObject) jsonValues.get(index)).get("event");



		            String id = (String) ((org.json.simple.JSONObject) jsonValues.get(index)).get("id");

		            String res = (String) ((org.json.simple.JSONObject) jsonValues.get(index)).get("res");



		            if (!(httpCode == 0 || httpCode == 200)) {

		                isError = true;

		                addLog("isError :: " + isError + " , httpCode : " + httpCode,true);

		                soft_assert.assertEquals(isError, false,

		        				"The booking for trip id " + tripId + " has response code "+httpCode+" which is other than 0 and 200 in SMS flow");

		            }



		            if (res != null) {



		                String statsInnerUrl = "http://172.17.12.171/showbookresult.php?itineraryId=" + id +

		                        "&event=" + event + "&column=api_res" + "&tid=" + tid;

		                

		                HttpResponse statsInnerResponse = APIUtils.getApiWithAuth(statsInnerUrl, null, 200);



		                String statsInnerResponseStr = IOUtils.toString(statsInnerResponse.getEntity().getContent());



		                if (statsInnerResponseStr.toLowerCase().matches("timeout|time out|exception")) {
		                	int len=statsInnerResponseStr.length();
		                	if(len > 1500) {
		                		len=1500;
		                	}

		                    addLog("Stats Inner Error Response : " + statsInnerResponseStr.substring(0, len)+"...",true);

		                    isError = true;

		                    if(p) {

		                    	soft_assert.assertEquals(isError, false,

		                    			"The stats summary's inner call responses for trip id " + tripId + " are not in xml format in SMS flow. To view the call, look into the reporter output or console.");

		                    	p=false;

		                    }

		                }

		            }

		        }

		        addLog("isError :::::::: " + isError,true);



		    } catch (Exception e) {

		        e.printStackTrace();

		    }

		}

		

		

		public Map<String, Double> getMealBaggageAndPaxPrice(String tripId) throws Exception {

		    Map<String, Double> priceInfo = new HashMap<>();



		    try {

		        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();





		        DocumentBuilder builder = builderFactory.newDocumentBuilder();

		        XPath xPath = XPathFactory.newInstance().newXPath();



		        String hqURL = "http://172.17.13.35:9080/trips/" + tripId;

		        HttpResponse hqResponse = APIUtils.getApi(hqURL, null, 200);

		        String hqResponseStr = IOUtils.toString(hqResponse.getEntity().getContent());

		        Document xmlDocument = builder.parse(new InputSource(new StringReader(hqResponseStr)));



		        String expression = "//air-bookings";

		        org.w3c.dom.NodeList nodeList = (org.w3c.dom.NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

		        double totalSeatFee = 0;

		        double totalBaggageFee = 0;

		        double totalMealFee = 0;

		        double totalAdultBaseFare = 0;

		        double totalChildBaseFare = 0;

		        double totalInfantBaseFare = 0;



		        Map<Integer, Double> seqMapping = new HashMap<>();



		        for (int index = 0; index < nodeList.getLength(); index++) {



		        	org.w3c.dom.NodeList priceList = (org.w3c.dom.NodeList) xPath.compile("./air-booking/pricing-info-list/pricing-info").evaluate(nodeList.item(index), XPathConstants.NODESET);



		            for (int index1 = 0; index1 < priceList.getLength(); index1++) {



		                String seatFee = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                        .compile("./total-fee-seat").evaluate(priceList.item(index1), XPathConstants.NODE))).getTextContent();

		                String baggageFee = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                        .compile("./total-fee-baggage").evaluate(priceList.item(index1), XPathConstants.NODE))).getTextContent();

		                String mealFee = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                        .compile("./total-fee-meal").evaluate(priceList.item(index1), XPathConstants.NODE))).getTextContent();

		                String baseFare = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                        .compile("./total-base-fare").evaluate(priceList.item(index1), XPathConstants.NODE))).getTextContent();

		                String seqNo = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                        .compile("./seq-no").evaluate(priceList.item(index1), XPathConstants.NODE))).getTextContent();

		                seqMapping.put(Integer.parseInt(seqNo), Double.valueOf(baseFare));





		                totalBaggageFee += Double.valueOf(baggageFee);

		                totalSeatFee += Double.valueOf(seatFee);



		                totalMealFee += Double.valueOf(mealFee);

		            }





		        }

		        Map<Integer, String> paxSeq = new HashMap<>();



		        for (int index = 0; index < nodeList.getLength(); index++) {



		        	org.w3c.dom.NodeList priceList = (org.w3c.dom.NodeList) xPath.compile("./air-booking/pax-list/pax-info").evaluate(nodeList.item(0), XPathConstants.NODESET);



		            for (int index1 = 0; index1 < priceList.getLength(); index1++) {





		                String seqNo = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                        .compile("./seq-no").evaluate(priceList.item(index1), XPathConstants.NODE))).getTextContent();

		                String paxType = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                        .compile("./pax-type-code").evaluate(priceList.item(index1), XPathConstants.NODE))).getTextContent();



		                paxSeq.put(Integer.parseInt(seqNo), paxType);



		            }

		        }



		        Iterator<Map.Entry<Integer, Double>> seqInterator = seqMapping.entrySet().iterator();



		        while (seqInterator.hasNext()) {

		            Map.Entry<Integer, Double> data = seqInterator.next();

		            Integer key = data.getKey() > paxSeq.size() ? data.getKey() - paxSeq.size() : data.getKey();



		            if (paxSeq.get(key).equalsIgnoreCase("ADT")) {

		                totalAdultBaseFare += data.getValue();



		            } else if (paxSeq.get(key).equalsIgnoreCase("CHD")) {

		                totalChildBaseFare += data.getValue();

		            } else {

		                totalInfantBaseFare += data.getValue();

		            }

		        }



		        priceInfo.put("totalSeatFee", totalSeatFee);

		        priceInfo.put("totalBaggageFee", totalBaggageFee);

		        priceInfo.put("totalMealFee", totalMealFee);

		        priceInfo.put("totalAdultBaseFare", totalAdultBaseFare);

		        priceInfo.put("totalChildBaseFare", totalChildBaseFare);

		        priceInfo.put("totalInfantBaseFare", totalInfantBaseFare);

		    } catch (Exception e) {

		        e.printStackTrace();

		    }

		    return priceInfo;



		}

		

		public String getConnectorsSearchUrl(boolean is_international, String origin, String destination, String destination1, String onwarddate,

				String returndate, String paxConfig, String airline, String suppliers, String tripType) throws Exception {

			

			String sct="in";

			if(!common.value("domain").trim().equalsIgnoreCase("com")) {

				sct=common.value("domain").toLowerCase().trim();

			}

			

			String intl = "n";

			String endPoint = "/flights/results";

			if (is_international) {

				intl = "y";

				endPoint = "/flights/international/results";

			}



			addLog("Getting the pax info.", true);

			String[] inp = paxConfig.split("-");

			String adults = inp[0];

			String children = inp[1];

			String infants = inp[2];



			addLog("Getting onward date", true);

			String odate = getModifiedDate(onwarddate);



			String searchIp = dataFile.value("ConnectorSearchUrl");

			String connectorSearchUrl = "";



			if (tripType.toLowerCase().trim().equals("ow")) {



				connectorSearchUrl = searchIp + "from=" + origin + "&to=" + destination + "&depart_date=" + odate

						+ "&adults=" + adults + "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines="

						+ airline + "&carrier=" + airline + "&intl=" + intl + "&suppliers=" + suppliers

						+ "&airlines=" + airline + "&stops=0&sct="+sct+"&src=connector";



			} else if (tripType.toLowerCase().trim().equals("rt")) {



				addLog("Getting return date", true);

				String rdate = getModifiedDate(returndate);



				connectorSearchUrl = searchIp + "from=" + origin + "&to=" + destination + "&depart_date=" + odate

						+ "&return_date=" + rdate + "&adults=" + adults + "&childs=" + children + "&infants=" + infants

						+ "&class=Economy&airlines=" + airline + "&carrier=" + airline + "&intl=" + intl

						+ "&suppliers=" + suppliers + "&airlines=" + airline + "&stops=0&sct="+sct+"&src=connector";



			} else if (tripType.toLowerCase().trim().equals("mc")) {



				addLog("Getting 2nd onward date for MC", true);

				String rdate = getModifiedDate(odate + 5);

				addLog("Getting 3rd onward date for MC", true);

				String rdate2 = getModifiedDate(onwarddate + 8);



				connectorSearchUrl = searchIp + "from1=" + origin + "&to1=" + destination + "&depart_date_1=" + odate

						+ "&multicity=true&from2=" + destination + "&to2=" + destination1 + "&depart_date_2=" + rdate

						+ "&from3=" + destination1 + "&to3=" + origin + "&depart_date_3=" + rdate2 + "&adults=" + adults

						+ "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines=" + airline + "&carrier="

						+ airline + "&intl=" + intl + "&suppliers=" + suppliers + "&airlines=" + airline+"&sct="+sct+"&src=connector";



			} else if (tripType.toLowerCase().trim().equals("connected")) {



				connectorSearchUrl = searchIp + "from=" + origin + "&to=" + destination + "&depart_date=" + odate

						+ "&adults=" + adults + "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines="

						+ airline + "&carrier=" + airline + "&intl=" + intl + "&suppliers=" + suppliers

						+ "&airlines=" + airline + "&stops=1&sct="+sct+"&src=connector";



			} else if (tripType.toLowerCase().trim().equals("via")) {

				connectorSearchUrl = searchIp + "from=" + origin + "&to=" + destination + "&depart_date=" + odate

						+ "&adults=" + adults + "&childs=" + children + "&infants=" + infants + "&class=Economy&airlines="

						+ airline + "&carrier=" + airline + "&intl=" + intl + "&suppliers=" + suppliers

						+ "&airlines=" + airline + "&stops=1&sct="+sct+"&src=connector";



			}



		return connectorSearchUrl;

			

		}

		

		

		public static Map<String,Map<String, String>> getConnectorResponse(String connectorURL) {

		    Map<String, Map<String, String>> result = new HashMap<>();



		    try {

		       // String connectorURL = "http://172.17.12.25:9080/airservice/search?from=BLR&to=HYD&depart_date=28/01/2019&adults=1&childs=0&infants=0&class=Economy&airline=&carrier=&intl=n&src=connector";





		        HttpResponse connectorResponse = APIUtils.getApi(connectorURL, null, 200);

		        System.out.println("connectorResponse " + connectorResponse);





		        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();



		        DocumentBuilder builder = builderFactory.newDocumentBuilder();

		        Document xmlDocument = builder.parse(connectorResponse.getEntity().getContent());

		        XPath xPath = XPathFactory.newInstance().newXPath();



		        String expression = "//onward-solutions/solution";

		        org.w3c.dom.NodeList nodeList = (org.w3c.dom.NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

		        

		        if(nodeList.getLength() < 1) {

		        	expression = "//air-search-result/solutions/solution";

			        nodeList = (org.w3c.dom.NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

		        }



		        System.out.println("a: "+nodeList.getLength());

		        for (int index = 0; index < nodeList.getLength(); index++) {

		            Map<String, String> data = new HashMap<>();



		            String baseFare = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                    .compile("./pricing-summary/base-fare").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();

		            String markup ="";

		            try {

		            	markup = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

			                    .compile("./pricing-summary/markup").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();



		            }catch(NullPointerException npe) {

		            	markup="0";

		            }

		            

		            String taxes = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                    .compile("./pricing-summary/taxes").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();



		            String totalFare = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                    .compile("./pricing-summary/total-fare").evaluate(nodeList.item(index), XPathConstants.NODE))).getTextContent();





		            data.put("total-fare", totalFare);

		            data.put("taxes", taxes);

		            data.put("markup", markup);

		            data.put("baseFare", baseFare);

		            data.put("totalSolutions", String.valueOf(nodeList.getLength()));



		            org.apache.xml.dtm.ref.DTMNodeList flightList = (org.apache.xml.dtm.ref.DTMNodeList) xPath.compile("./flights/flight").evaluate(nodeList.item(index), XPathConstants.NODESET);



		            for (int index1 = 0; index1 < flightList.getLength(); index1++) {

		                org.apache.xml.dtm.ref.DTMNodeList segments = (org.apache.xml.dtm.ref.DTMNodeList) xPath.compile("./segments/segment").evaluate(flightList.item(index1), XPathConstants.NODESET);

		                StringBuilder sb = new StringBuilder();



		                for (int index2 = 0; index2 < segments.getLength(); index2++) {





		                    String arrivalDateTime = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()



		                            .compile("./arrival-date-time").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();



		                    String flightNumber = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                            .compile("./flight-number").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String airlines = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                            .compile("./airline").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String departureDateTime = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                            .compile("./departure-date-time").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String departureAirport = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                            .compile("./departure-airport").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();





		                    String arrivalAirport = ((Element) ((org.w3c.dom.Node) XPathFactory.newInstance().newXPath()

		                            .compile("./arrival-airport").evaluate(segments.item(index2), XPathConstants.NODE))).getTextContent();



		                    sb.append(arrivalAirport);

		                    sb.append("-");

		                    sb.append(departureAirport);

		                    sb.append("-");

		                    sb.append(departureDateTime);

		                    sb.append("-");

		                    sb.append(arrivalDateTime);

		                    sb.append("-");

		                    sb.append(airlines);

		                    sb.append("-");

		                    sb.append(flightNumber);

		                }

		                result.put(sb.toString(), data);

		            }

		        }

		    }catch (Exception e) {

		        e.printStackTrace();

		    }

		    System.out.println("result " + result);

		    return result;

		}

		

		

		/**

		 * @author manjunath

		 * This method generates random string of the specified length in the specified mode. <br/>

		 * 

		 * @param length <br/>

		 * 

		 * @param mode <br/>

		 * 

		 * @return Random string

		 */

		

		public String getRandomString(int length, Mode mode) throws Exception

		{

			StringBuilder buffer = new StringBuilder();

			String characters = "";

			switch(mode)

			{

			case ALPHA:

				characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

				break;

			case ALPHANUMERIC:

				characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

				break;

			case NUMERIC:

				characters = "123456789";

				break;

			}

			int charactersLength = characters.length();

			for (int i = 0; i < length; i++) 

			{

				double index = Math.random() * charactersLength;

				buffer.append(characters.charAt((int) index));

			}

			return buffer.toString();

		}



		

		/**

		 * @author manjunath

		 * This method returns current date and time in ddMMyyyyHHmmssS format.

		 * 

		 * @return dateAndTime

		 * @throws Exception

		 */

		

	public String getCurrentDateAndTime() throws Exception

		{

//			String pattern = "ddMMyyyyHHmmssS";

			String pattern = "ddmmssS";

		    SimpleDateFormat format = new SimpleDateFormat(pattern);

		    String dateAndTime = format.format(new Date());

		    return dateAndTime ;

		}

	

	public boolean checkPresenceOfSplRtAndSelectTheSame(RemoteWebDriver driver,String airline) throws Exception {

		boolean splrtPresent=false;

		airline=airline.toUpperCase();

		List<WebElement> splRndTabs = driver.findElements(By.xpath("//*[@class='comboTabs']/td"));

		int numberOfTabs=splRndTabs.size();

		if (numberOfTabs == 0) {

			addLog("The special RT is not available for the given airlines/suppliers and sectors.",true);

			 return splrtPresent;

		} else if (splRndTabs != null && numberOfTabs > 0) {

			

			for (int i = 1; i < numberOfTabs; i++) {

				if(isElementVisible(driver, By.xpath("//*[@class='comboTabs']/td[@data-airline='"+airline+"']/a"))){

					safeClick(driver, By.xpath("//*[@class='comboTabs']/td[@data-airline='"+airline+"']/a"));

					String firstFlightNameAndNumber=getAttribute(driver, By.xpath("//div[contains(@class,'flightCSummary')][1]/div/small"), "title");

					String secondFlightNameAndNumber=getAttribute(driver, By.xpath("//div[contains(@class,'flightCSummary')][2]/div/small"), "title");

					addLog("First flight name and number: "+firstFlightNameAndNumber,true);

					addLog("Second flight name and number: "+secondFlightNameAndNumber,true);

					splrtPresent=true;

					break;

				}else if(i == numberOfTabs-1) {

					if(isElementVisible(driver, By.xpath("//*[@class='comboTabs']/td[@id='BackToRtSpT']/a"))) {

						safeClick(driver, By.xpath("//*[@class='comboTabs']/td[@id='BackToRtSpT']/a"));

						String firstFlightNameAndNumber=getAttribute(driver, By.xpath("//div[contains(@class,'flightCSummary')][1]/div/small"), "title");

						String secondFlightNameAndNumber=getAttribute(driver, By.xpath("//div[contains(@class,'flightCSummary')][2]/div/small"), "title");

						addLog("First flight name and number: "+firstFlightNameAndNumber,true);

						addLog("Second flight name and number: "+secondFlightNameAndNumber,true);

						

						String firstAirline=firstFlightNameAndNumber.split("-")[0];

						String secondAirline=secondFlightNameAndNumber.split("-")[0];

						if(firstAirline.equalsIgnoreCase(airline) || secondAirline.equalsIgnoreCase(airline)) {

							if(firstAirline.equalsIgnoreCase(secondAirline)) {

								addLog("Both the airlines are same for this special rt booking which is "+ firstAirline,true);

							}else {

								addLog("Both the airlines are not same for this special rt booking. First Airline is: "+ firstAirline+ " and Second Airline is: "+secondAirline,true);

							}

							splrtPresent=true;

						}else {

							addLog("None of the splrt airlines matches with given splrt airline.",true);

							splrtPresent=false;

						}

					}

				}

			}

		}

		return splrtPresent;

	}

	

}