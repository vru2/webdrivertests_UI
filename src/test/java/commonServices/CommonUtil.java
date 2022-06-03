// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Sep, 2016
// Author - Mohamed Faisal
// Copyright 2016 Cleartrip Travel. All rights reserved.

package test.java.  commonServices;

import static test.java.commonServices.CachedPropertiesTemp.cacheUrlInstance;
import static test.java.commonServices.CachedPropertiesTemp.campLocalInstance;
import static test.java.commonServices.CachedPropertiesTemp.ctsuite;
import static test.java.commonServices.CachedPropertiesTemp.dataInstance;
import static test.java.commonServices.CachedPropertiesTemp.dbInstance;
import static test.java.commonServices.CachedPropertiesTemp.getTrip;
import static test.java.commonServices.CachedPropertiesTemp.getapiPayload;
import static test.java.commonServices.CachedPropertiesTemp.instance;
import static test.java.commonServices.CachedPropertiesTemp.objectReportInstance;
import static test.java.commonServices.CachedPropertiesTemp.objectReportInstanceAir;
import static test.java.commonServices.CachedPropertiesTemp.objectReportInstanceHotels;
import static test.java.commonServices.CachedPropertiesTemp.objectReportInstanceHotelsPWA;
import static test.java.commonServices.CachedPropertiesTemp.objectReportInstanceLocals;
import static test.java.commonServices.CachedPropertiesTemp.objectReportInstancePayment;
import static test.java.commonServices.CachedPropertiesTemp.objectReportInstanceTrains;
import static test.java.commonServices.CachedPropertiesTemp.objectReposCtSuite;
import static test.java.commonServices.CachedPropertiesTemp.platformInstance;
import static test.java.commonServices.CachedPropertiesTemp.objectReportInstancePlatform;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import  test.java.testrailpoc.testrail_utils;

public class CommonUtil extends oracle.jdbc.driver.OracleDriver{

	public CachedPropertiesTemp common = instance();
	public CachedPropertiesTemp dataFile = dataInstance();
	public CachedPropertiesTemp db = dbInstance();
	public CachedPropertiesTemp caheUrl = cacheUrlInstance();
	public CachedPropertiesTemp objectRepos = objectReportInstance();
	public CachedPropertiesTemp objectReposAir = objectReportInstanceAir();
	public CachedPropertiesTemp objectReposHotels = objectReportInstanceHotels();
	public CachedPropertiesTemp objectReposHotelsPWA = objectReportInstanceHotelsPWA();
	public CachedPropertiesTemp objectReposPlatform = objectReportInstancePlatform();
	public CachedPropertiesTemp objectReposLocals = objectReportInstanceLocals();
	public CachedPropertiesTemp objectReposTrains = objectReportInstanceTrains();
	public CachedPropertiesTemp objectReposPayment = objectReportInstancePayment();
	public CachedPropertiesTemp rubyAPITrips = getTrip();
	public CachedPropertiesTemp apiPayload = getapiPayload();
	public CachedPropertiesTemp platform = platformInstance();
	public CachedPropertiesTemp ctsuite = ctsuite();
	public CachedPropertiesTemp objectReposCtSuite = objectReposCtSuite();
	public CachedPropertiesTemp campLocal = campLocalInstance();

	public String debug = common.value("debug");
	public boolean debugger = Boolean.parseBoolean(debug);
	StopWatch watch;

	public static enum Mode 
	{
		ALPHA, ALPHANUMERIC, NUMERIC 
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
	public By getObjectAir(String objectKey) throws Exception {
		String[] curObject = objectReposAir.value(objectKey).split(":");
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

	public By getObjectHotels(String objectKey) throws Exception {
		String[] curObject = objectReposHotels.value(objectKey).split(":");
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

	public By getObjectHotelsPWA(String objectKey) throws Exception {
		String[] curObject = objectReposHotelsPWA.value(objectKey).split(":");
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


	public By getObjectLocals(String objectKey) throws Exception {
		String[] curObject = objectReposLocals.value(objectKey).split(":");
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

	public By getObjectTrains(String objectKey) throws Exception {
		String[] curObject = objectReposTrains.value(objectKey).split(":");
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

	public By getObjectCtSuite(String objectKey) throws Exception {
		String[] curObject = objectReposCtSuite.value(objectKey).split(":", 2);
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

	public String getValue(String objectKey) throws Exception {
		String[] curObject = objectRepos.value(objectKey).split(":");
		String objectValue = curObject[0];
		return objectValue;

	}

	public String getMonthTime(int month, String format) throws Exception {
		Calendar calender = new GregorianCalendar();
		calender.add(Calendar.MONTH, +month);
		Date date = calender.getTime();
		String dateTime = new SimpleDateFormat(format).format(date);
		return dateTime;
	}

	public String getDate(String format) throws Exception {
		Calendar calender = new GregorianCalendar();
		Date date = calender.getTime();
		String dateTime = new SimpleDateFormat(format).format(date);
		return dateTime;
	}

	public String putYear(int year) throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.YEAR, +year);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("yyyy").format(s);
		return dateString;
	}

	public String getBit() {
		String architecture = "os.arch";
		String bit = System.getProperty(architecture);
		// System.out.println("bit : " + bit);
		addLog(bit);

		return bit;
	}

	public void printInfo(String Data) {
		if (debugger) {
//			System.out.println(Data);
		}
	}

	public String putLogDate() throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +0);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("_EEE_ddMMMyyyy_hhmmss").format(s);
		return dateString;
	}

	public String putDate(int days) throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +days);
		Date s = c.getTime();
		String dateString[] = new SimpleDateFormat("dd/MM/yyyy").format(s).split("/");
		// //System.out.println(dateString[0]);

		return dateString[0];
	}

	public String putDate1(int days) throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +days);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("dd/MM/yyyy").format(s);
		return dateString;
	}

	public String putDateNoSplChar(int days) throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +days);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("ddMMyy").format(s);
		return dateString;
	}

	public String putDateNoSplCharMonth(int months) throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.MONTH, +months);
		Date s = c.getTime();
		String dateString = new SimpleDateFormat("MMyy").format(s);
		return dateString;
	}

	public String getMonth() throws Exception {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String Month = new SimpleDateFormat("MMMMMMMM").format(date);
		return Month;
	}

	public String getDateTime(int day, String format) throws Exception {
		Calendar calender = new GregorianCalendar();
		calender.add(Calendar.DATE, +day);
		Date date = calender.getTime();
		String dateTime = new SimpleDateFormat(format).format(date);
		return dateTime;
	}

	public String generateRandomWord(int wordLength) {
		java.util.Random r= new java.util.Random();
		StringBuilder sb = new StringBuilder(wordLength);
		for(int i = 0; i < wordLength; i++) { // For each letter in the word
			char tmp = (char) ('a' + r.nextInt('z' - 'a')); // Generate a letter between a and z
			sb.append(tmp); // Add it to the String
		}
		return sb.toString();
	}




	public String getRandomNo(int number) throws Exception {
		int randomInventory = ThreadLocalRandom.current().nextInt(number);
		randomInventory = (int) (Math.random() * 10);
		randomInventory = randomInventory + 500;
		String Str_randomInventory = Integer.toString(randomInventory);
		return Str_randomInventory;
	}

	public String getRandomNos(int number) throws Exception {
		int randomInventory = ThreadLocalRandom.current().nextInt(number);
		randomInventory = (int) (Math.random() * number);
		String Str_randomInventory = Integer.toString(randomInventory);
		return Str_randomInventory;
	}

	public String getRandomNosMin(int number, int min) throws Exception {
		int randomInventory = ThreadLocalRandom.current().nextInt(number);
		randomInventory = (int) (Math.random() * number);
		randomInventory = randomInventory + min;
		String Str_randomInventory = Integer.toString(randomInventory);
		return Str_randomInventory;
	}

	/*
	 * public String putDateApi(int days) throws Exception { Calendar c = new GregorianCalendar(); c.add(Calendar.DATE, +days);
	 * Date s = c.getTime(); String dateString = new SimpleDateFormat("yyyy-MM-dd").format(s); return dateString; }
	 *
	 * public boolean api_payment_flag() { if ( common.value("host").contains("stg1") || common.value("host").contains("qa2") ||
	 * common.value("host").contains("stg2")|| common.value("host").contains("debug") ) {
	 *
	 * boolean flag = true; return flag; } else { boolean flag = false; return flag; } }
	 */

	public boolean checkBookingStatusTripXML(String tripId, String expectedStatus) throws Exception {
		boolean failure = false;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(new URI("http://172.17.12.231:9080/trips/" + tripId));
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String xml = EntityUtils.toString(entity, "UTF-8");
		// System.out.println(xml);
		String statusXml = xml.split("<booking-status>")[1].split("</booking-status>")[0];
		if (!statusXml.equals("C")) {
			addLog("Booking status in trip xml should have been C but was : " + statusXml);
			failure = true;
		}

		/*
		 * DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); DocumentBuilder builder =
		 * factory.newDocumentBuilder();
		 *
		 * StringBuilder xmlStringBuilder = new StringBuilder();
		 * //xmlStringBuilder.append("<?xml version="1.0"?> <class> </class>"); ByteArrayInputStream input = new
		 * ByteArrayInputStream( xmlStringBuilder.toString().getBytes("UTF-8")); //Document doc = builder.parse(input);
		 *
		 * XPath xPath = XPathFactory.newInstance().newXPath();
		 *
		 * String expression = "/class/student"; NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc,
		 * XPathConstants.NODESET);
		 *
		 * for (int i = 0; i < nodeList.getLength(); i++) { Node nNode = nodeList.item(i); ... }
		 */

		/*
		 * DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); DocumentBuilder dBuilder;
		 *
		 * dBuilder = dbFactory.newDocumentBuilder();
		 *
		 * org.w3c.dom.Document doc = dBuilder.parse(new InputSource( new StringReader(xml)));
		 * doc.getDocumentElement().normalize(); XPath xPath = XPathFactory.newInstance().newXPath();
		 *
		 * String expression = "booking-status"; NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc,
		 * XPathConstants.NODESET); for (int i = 0; i < nodeList.getLength(); i++) { Node nNode = nodeList.item(i);
		 * System.out.println(nNode.toString()); System.out.println(nNode.getTextContent()); }
		 */
		return failure;
	}

	public void signInAPI(RemoteWebDriver driver, String url, String email, String password) throws URISyntaxException,
			ClientProtocolException, IOException {
		String ct_auth = null;
		String tkn = null;
		String usermisc = null;
		String userid = null;
		String domain = url.split("//")[1];

		driver.get(url);

		StopWatch watch = new StopWatch();
		watch.start();

		HttpResponse response = getSignInCookie(driver, url, email, password);

		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			if (header.getValue().startsWith("ct-auth=")) {
				ct_auth = header.getValue().substring(8).split(";")[0];
			} else if (header.getValue().startsWith("tkn=")) {
				tkn = header.getValue().substring(4).split(";")[0];
			} else if (header.getValue().startsWith("usermisc=")) {
				usermisc = header.getValue().substring(9).split(";")[0];
			} else if (header.getValue().startsWith("userid=")) {
				userid = header.getValue().substring(7).split(";")[0];
			}
			// System.out.println("Key : " + header.getName() + " ,Value : " + header.getValue());
		}

		Date date = new Date(127, 1, 1, 1, 1);
		Cookie name = new Cookie("ct-auth", ct_auth, domain, "/", date);
		driver.manage().addCookie(name);

		if (tkn != null) {
			name = new Cookie("tkn", tkn, domain, "/", date);
			driver.manage().addCookie(name);
		}

		name = new Cookie("usermisc", usermisc, domain, "/", date);
		driver.manage().addCookie(name);

		name = new Cookie("userid", userid, domain, "/", date);
		driver.manage().addCookie(name);

		// After adding the cookie we will check that by displaying all the cookies in the browser.
		/*
		 * Set<Cookie> cookiesList = driver.manage().getCookies(); for(Cookie getcookies :cookiesList) {
		 * System.out.println(getcookies ); }
		 */

		driver.get(url);
		watch.stop();

		addLog("Time taken to signIn from API: " + watch.toString());
		System.out.println("Time taken to signIn from API: " + watch.toString());
	}

	public HttpResponse getSignInCookie(RemoteWebDriver driver, String url, String email, String password)
			throws URISyntaxException, ClientProtocolException, IOException {
		HttpPost post = new HttpPost(new URI(url + "/externalapi/signin"));
		HttpClient client = new DefaultHttpClient();
		// post.setHeader("X-CT-API-KEY","g9s45bsammqggtczpz3kj3qk");
		post.setHeader("Accept", "text/json");
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("email", email));
		urlParameters.add(new BasicNameValuePair("password", password));
		urlParameters.add(new BasicNameValuePair("persistent_login", "t"));
		urlParameters.add(new BasicNameValuePair("caller", "homepage"));
		urlParameters.add(new BasicNameValuePair("source", "ui"));
		// urlParameters.add(new BasicNameValuePair("Accept", "text/json"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		return client.execute(post);
	}

	public void timer() {
		watch = new StopWatch();
		watch.start();

	}

	public void afterMethod(RemoteWebDriver driver, ITestResult _result) throws Exception {
		screenshot(_result, driver);
		afterMethodProcess(_result);
	}

	public void afterMethod_Local(RemoteWebDriver driver, ITestResult _result) throws Exception {
		screenshot(_result, driver);
		afterMethodProcessLocal(_result);
	}

	public void afterMethodNoScreenshot(ITestResult _result) throws Exception {
		afterMethodProcess(_result);
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

	public void afterMethodProcessLocal(ITestResult _result) throws Exception {
		if (watch != null) {
			watch.stop();
			addLog("Time taken by script : " + watch.toString());
		}
		if (common.value("testrailupdate").equalsIgnoreCase("true")) {
			testrail_utils util = new testrail_utils();
			util.UpdateTestRail(_result, common.value("localprojectid"));
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

			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File screenshotFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(filepath));
			addLog("<a href'" + filepath + "'>screenshot</a>");
			addLog("Screenshot Name :" + filename);
			Reporter.log("Screenshot Path :" + filename);
			addLog(driver.getCurrentUrl());
			System.out.println("Script Failed : "+testName);
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

	public String getCurrentDay(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String date1= dateFormat.format(date);
		String[] date2 = date1.split("/");
		String currentDay = date2[0];
		//int currentDay = Integer.parseInt(date2[0]);
		return currentDay;
	}

	public String getCurrentHour(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String currentTime= dateFormat.format(date);
		String[] hour = currentTime.split(":");
		String currentHour=hour[0];
		//int currentHour = Integer.parseInt(hour[0]);
		return currentHour;
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
	
	public static void addStaticLog(String logMessage,boolean printOnConsole) {
		if(printOnConsole) {
			Reporter.log("<p>"+logMessage+"</p>",false);
			System.out.println(logMessage+"\n");
		}else {
			Reporter.log("<p>"+logMessage+"</p>",false);
		}
	}
}