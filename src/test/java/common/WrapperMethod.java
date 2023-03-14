// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.common;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

public class WrapperMethod extends CommonUtil {
	// WebElement we=null;
	private static WebDriver browserDriver;
	//	/public static Logger logger = Logger.getLogger(WrapperMethod.class);
	public static Logger logger = Logger.getLogger("");
	protected String baseUrl;

	public Boolean MakePaymentTrue = common.value("makePayment").equals("true"),
			NetBanking = common.value("makePayment").contains("true"), ProductionUrl =
			common.value("host").contains("www"), BetaURL =
			common.value("host").contains("beta"), MakePaymentOnlyInQA2 = MakePaymentTrue
			&& !(ProductionUrl || BetaURL), MakePaymentOnlyInProd = MakePaymentTrue && ProductionUrl;


	public boolean GDS_Flight, B2B_GDS_Flight = false;

	public String getBaseUrl(String domain) {
		// addLog("Domain: " + domain + " Host: " + common.value("host"),true);
		if (domain.equals("com") || domain.equals("ae") || domain.equals("sa")) {
			baseUrl = "https://" + common.value("host") + common.value("url") + domain;
		} else if (domain.equals("qa") || domain.equals("bh") || domain.equals("om") || domain.equals("kw")
				|| domain.equals("me")) {
			if (common.value("host").equals("qa2") || common.value("host").equals("hf")
					|| common.value("host").equals("www")) {
				baseUrl = "https://" + domain + common.value("url") + "com";
			} else if (common.value("host").equals("beta")) {
				baseUrl = "https://" + "beta" + domain + common.value("url") + "com";
			}
		}
		// addLog("baseURL: " + baseUrl, true);
		return baseUrl;
	}


	public void browserClose(RemoteWebDriver driver) {
		driver.close();
		//driver.quit();
	}

	/*public RemoteWebDriver getMobileDriver(RemoteWebDriver driver) throws IOException {

		if (common.value("mobilebrowser").equalsIgnoreCase("chrome")) {
			Map<String, Object> deviceMetrics = new HashMap<>();
			Map<String, Object> mobileEmulation = new HashMap<>();

			mobileEmulation.put("deviceName", "Samsung Galaxy S20 Ultra");
			if (System.getProperty("os.name").contains("Linux")) {
				File file = new File(".");
				String filepath = file.getCanonicalPath() + "/exe/chromedriver_linux_110";
				System.setProperty("webdriver.chrome.driver", filepath);
			} else if (System.getProperty("os.name").contains("Mac")) {
				File file = new File(".");
				String filepath = file.getCanonicalPath() + "//exe//chromedriver1";
				System.setProperty("webdriver.chrome.driver", filepath);
			}
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; Microsoft; Lumia 640 XL LTE) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Mobile Safari/537.36 Edge/12.10166");
			chromeOptions.addArguments("--allowed-ips");

			if (common.value("headlessbrowser").equalsIgnoreCase("false")) {
				driver = new ChromeDriver(chromeOptions);
			} else driver = new ChromeDriver(this.createHeadlessChromeMobile());
		} else if (common.value("mobilebrowser").equalsIgnoreCase("FIREFOX")) {

			File file = new File(".");
			String filepath = file.getCanonicalPath() + "//exe//geckodriver_mac";
			System.setProperty("webdriver.gecko.driver", filepath);
			Map<String, Object> mobileEmulation = new HashMap<>();
			String user_agent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("general.useragent.override", user_agent);
			driver = new FirefoxDriver(options);

		}
		Dimension dimension = new Dimension(300, 900);
		driver.manage().window().setSize(dimension);
		return driver;

	}*/

	/*public RemoteWebDriver getDriver(RemoteWebDriver driver) throws IOException, InterruptedException {

		if(this.common.value("browser").equalsIgnoreCase("firefox")) {
			if (this.common.value("headlessbrowser").equalsIgnoreCase("true") && this.common.value("OS").equalsIgnoreCase("linux")) {

				File file = new File(".");
				System.setProperty("webdriver.gecko.driver", file.getCanonicalPath() + "//exe//geckodriver");
				FirefoxOptions options = new FirefoxOptions()
						//	.addPreference("--headless", 1)
						.addPreference("browser.startup.page", 1)
						.setHeadless(true);
				//.options.setHeadless(true);

				driver = new FirefoxDriver(options);
				driver.manage().window().maximize();
			} else {

				File file = new File(".");
				System.setProperty("webdriver.gecko.driver", file.getCanonicalPath() + "//exe//geckodriver_mac");
				FirefoxOptions options = new FirefoxOptions()
						//	.addPreference("--headless", 1)
						.addPreference("browser.startup.page", 1);
				driver = new FirefoxDriver(options);
			}
		}
		else if(this.common.value("browser").equalsIgnoreCase("chrome")){
			if (this.common.value("headlessbrowser").equalsIgnoreCase("true") && this.common.value("OS").equalsIgnoreCase("linux")) {

				System.setProperty("webdriver.chrome.driver", "exe/chromedriver_linux_110");
				Map<String, String> mobileEmulation = new HashMap<>();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver(chromeOptions);
			} else {

				System.setProperty("webdriver.chrome.driver", "exe/chromedriver_mac_110");
				Map<String, String> mobileEmulation = new HashMap<>();
				ChromeOptions chromeOptions = new ChromeOptions();
				// ChromeOptions options = new ChromeOptions();
				driver = new ChromeDriver(chromeOptions);
				driver.manage().window().maximize();
			}
		}
		return driver;
	}

	public String get_BrowserName(RemoteWebDriver driver) {
		String BrowserName = null;
		if (driver instanceof FirefoxDriver) {
			BrowserName = "FF";
			// System.out.println("FF DRIVER");
		} else if (driver instanceof InternetExplorerDriver) {
			BrowserName = "IE";
			// System.out.println("IE DRIVER");
		} else if (driver instanceof ChromeDriver) {
			BrowserName = "Chrome";
			// System.out.println("Chrome DRIVER");
		}
		return BrowserName;
	}*/


	public RemoteWebDriver getDriver(RemoteWebDriver driver) throws IOException {

		WebDriverManager.chromedriver().setup();
		if (common.value("headlessbrowser").equalsIgnoreCase("false")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("disable-gpu");
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
			chromeOptions.addArguments("disable-infobars"); // disabling infobars
			chromeOptions.addArguments("--disable-extensions"); // disabling extensions
			driver = new ChromeDriver(chromeOptions);

		}


		else if (common.value("headlessbrowser").equalsIgnoreCase("true")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("disable-gpu");
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
			chromeOptions.addArguments("disable-infobars"); // disabling infobars
			chromeOptions.addArguments("--disable-extensions"); // disabling extensions
			driver = new ChromeDriver(chromeOptions);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);
		return driver;

	}

	public RemoteWebDriver getMobileDriver(RemoteWebDriver driver) throws IOException {


		WebDriverManager.chromedriver().setup();
		if (common.value("headlessbrowser").equalsIgnoreCase("false")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("disable-gpu");
			chromeOptions.addArguments("--allowed-ips");
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; Microsoft; Lumia 640 XL LTE) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Mobile Safari/537.36 Edge/12.10166");
			chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
			chromeOptions.addArguments("disable-infobars"); // disabling infobars
			chromeOptions.addArguments("--disable-extensions"); // disabling extensions
			chromeOptions.addArguments("--disable-web-security");
			//chromeOptions.addArguments("--user-data-dir=/tmp/chrome_dev_test");
			chromeOptions.addArguments("--allow-cross-origin-auth-prompt");
			driver = new ChromeDriver(chromeOptions);

		}


		else if (common.value("headlessbrowser").equalsIgnoreCase("true")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--allowed-ips");
			chromeOptions.addArguments("disable-gpu");
			chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; Microsoft; Lumia 640 XL LTE) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Mobile Safari/537.36 Edge/12.10166");
			chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
			chromeOptions.addArguments("disable-infobars"); // disabling infobars
			chromeOptions.addArguments("--disable-extensions"); // disabling extensions
			driver = new ChromeDriver(chromeOptions);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);
		//Dimension dimension = new Dimension(360, 800);
		Dimension dimension = new Dimension(330, 950);
		driver.manage().window().setSize(dimension);
		driver.manage().deleteAllCookies();
		return driver;
	}

	public static boolean isElementPresent(RemoteWebDriver driver, By by) throws Exception {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static <we> boolean isElementPresent1(RemoteWebDriver driver, WebElement we) throws Exception {
		try {
			we.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean isElementVisible(RemoteWebDriver driver, By by) throws Exception {
		try {
			driver.findElement(by).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean waitElement(RemoteWebDriver driver, By by, int Time) throws Exception {
		boolean element = false;
		for (int second = 0; second < Time; second++) {
			if (isElementPresent(driver, by)) {
				element = true;
				break;
			}
			Thread.sleep(1000);
		}
		return element;
	}

	public boolean textPresentInElement(RemoteWebDriver driver, By by, String text, int Time)
			throws InterruptedException {
		boolean textpresent = false;
		int second;

		WebElement we = driver.findElement(by);
		for (second = 0; second < Time; second++) {
			try {
				if ((text.equals(we.getText()))) {
					textpresent = true;
					break;
				}
			} catch (Exception ignore) {
			}
			Thread.sleep(1000);
		}
		return textpresent;
	}

	public boolean textPresentInElementAssert(RemoteWebDriver driver, By by, String text, int Time)
			throws InterruptedException {
		boolean textpresent = false;
		int second;

		WebElement we = driver.findElement(by);
		for (second = 0; second < Time; second++) {
			try {
				if ((text.equals(we.getText()))) {
					textpresent = true;
					break;
				}
			} catch (Exception ignore) {
			}
			Thread.sleep(1000);
		}
		if(!textpresent) {
			Assert.assertTrue(false);
		}
		return textpresent;
	}

	public WebElement explicitWait(RemoteWebDriver driver, By by, int Time) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		return element;
	}

	public boolean textPresent(RemoteWebDriver driver, String text, int Time) throws InterruptedException {
		boolean textprest = false;
		int second = 0;
		for (second = 0; second < Time; second++) {
			try {
				if ((driver.getPageSource().contains(text))) {
					textprest = true;
					break;
				}
			} catch (Exception ignore) {
			}
			Thread.sleep(1000);
		}
		return textprest;
	}

	public boolean textPresent_Log(RemoteWebDriver driver, String text, int Time) throws InterruptedException {
		boolean textprest = false;
		int second = 0;
		for (second = 0; second < Time; second++) {
			try {
				if ((driver.getPageSource().contains(text))) {
					textprest = true;
					break;
				}
			} catch (Exception ignore) {
			}
			Thread.sleep(1000);
		}
		if (!textprest) {
			addLog(text + ": text is not Present");
			Assert.assertTrue(false);
		}
		return textprest;
	}

	public boolean textNotPresent_Log(RemoteWebDriver driver, String text, int Time) throws InterruptedException {
		boolean textNotPrest = true;
		if(textPresent(driver, text, Time)) {
			textNotPrest = false;
			addLog(text + ": text is Present");
			Assert.assertTrue(false);
		}
		return textNotPrest;
	}


	public void textAssert(RemoteWebDriver driver, String text, int Time) throws InterruptedException {
		int i = 0;
		for (i = 0; i <= Time; i++) {
			if (!textPresent(driver, text, 1)) {
			} else
				break;
		}
		if (i == Time) {
			addLog(text + " : text is not displayed");
			Assert.assertTrue(false);
		}
	}

	public void pageSlide(RemoteWebDriver driver, int Horizontal, int Verticle) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(Horizontal, Verticle)", "");
	}

	public boolean elementVisible(RemoteWebDriver driver, By by, int Time) throws Exception {
		boolean visible = false;
		boolean elementPresent;
		int second = 0;
		try {
			for (second = 0; second <= Time; second++) {
				elementPresent = isElementPresent(driver, by);
				if (elementPresent) {
					if (driver.findElement(by).isDisplayed()) {
						visible = true;
						break;
					}
				}
				Thread.sleep(1000);
			}
			if (!visible) {
				// addLog(by + " is not displayed in the page", true);
			}
			return visible;
		} catch (StaleElementReferenceException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean elementVisible(RemoteWebDriver driver, By by, int Time, String Log) throws Exception {
		boolean visible = false;
		boolean elementPresent;
		int second = 0;
		try {
			for (second = 0; second <= Time; second++) {
				elementPresent = isElementPresent(driver, by);
				if (elementPresent) {
					if (driver.findElement(by).isDisplayed()) {
						visible = true;
						break;
					}
				} else if (second == Time) {
					addLog(Log + " : Element is not visible");
				}
				Thread.sleep(1000);
			}
			if (!visible) {
				// addLog(by + " is not displayed in the page");
			}
			return visible;
		} catch (StaleElementReferenceException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean elementPresent(RemoteWebDriver driver, By by) throws Exception {
		int Time = 30;
		boolean visible = false;
		for (int second = 0; second < Time; second++) {
			try {
				driver.findElement(by);
				visible = true;
				// return visible;
				break;
			} catch (NoSuchElementException e) {
			}
			Thread.sleep(1000);
		}
		if (!visible) {
			// addLog(by + " is not displayed in the page");
			assertTrue("Failure! Element not Visible!", false);
		}
		return visible;
	}

	public boolean elementPresent_log(RemoteWebDriver driver, By by, String Text, int Time) throws Exception {
		boolean visible = false;
		for (int second = 0; second < Time; second++) {
			try {
				driver.findElement(by).isDisplayed();
				visible = true;
				// return visible;
				break;
			} catch (NoSuchElementException e) {
			}
			Thread.sleep(1000);
		}
		if (!visible) {
			addLog(Text + " is not displayed in the page");
			assertTrue("Failure!", false);
		}
		addLog(Text + " is displayed in page");
		return visible;
	}

	public boolean elementPresent(RemoteWebDriver driver, By by, int time) {

		boolean elementPresentFlag = false;
		// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for (int i = 0; i < time; i++) {
			try {
				WebElement we = null;
				if ((we = driver.findElement(by)) != null) {
					// System.out.println("Element Present" + by);
					elementPresentFlag = true;
					break;
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				// System.out.println("Element : " + by + " not present");
			}
			List<WebElement> eles = null;

		}
		return elementPresentFlag;
	}

	public boolean elementClickable(RemoteWebDriver driver, By by, int Time) throws Exception {
		boolean enabled = false;
		for (int second = 0; second < Time; second++) {
			try {
				driver.findElement(by).isEnabled();
				enabled = true;
				// return visible;
				break;
			} catch (NoSuchElementException e) {

			}
			Thread.sleep(1000);
		}

		return enabled;
	}

	public boolean elementAssert(RemoteWebDriver driver, By by, int Time) throws Exception {
		boolean visible = false;
		for (int second = 0; second < Time; second++) {
			try {
				driver.findElement(by);
				visible = true;
				// return visible;
				break;
			} catch (NoSuchElementException e) {

			}
			Thread.sleep(1000);
		}
		if (!visible) {
			addLog(by + " is not displayed in the page");
			assertTrue("Failure!", false);
		}
		return visible;
	}

	public boolean elementPresent_Time(RemoteWebDriver driver, By by, int Time) throws Exception {
		boolean visible = false;
		for (int second = 0; second < Time; second++) {
			try {
				driver.findElement(by);
				visible = true;
				// return visible;
				break;

			} catch (Exception e) {

				Thread.sleep(1000);
			}
		}
		return visible;
	}

	public boolean elementNotPresent_Time(RemoteWebDriver driver, By by, int Time) throws Exception {
		boolean NotVisible = false;
		boolean elementPresent;
		int second = 0;
		for (second = 0; second < Time; second++) {
			elementPresent = isElementPresent(driver, by);
			if (elementPresent) {
				if (driver.findElement(by).isDisplayed()) {
					NotVisible = false;
				} else {
					NotVisible = true;
					break;
				}
			}
			Thread.sleep(1000);
		}
		return NotVisible;
	}

	public boolean elementNotVisible(RemoteWebDriver driver, By by, int Time) throws Exception {
		// return (!elementVisible(driver, by, Time));

		boolean NotVisible = true;
		boolean elementPresent;
		int second = 0;
		for (second = 0; second < Time; second++) {
			elementPresent = isElementPresent(driver, by);
			if (elementPresent) {
				if (driver.findElement(by).isDisplayed()) {
					NotVisible = false;
				} else {
					NotVisible = true;
					break;
				}
			}
			Thread.sleep(1000);
		}
		return NotVisible;
	}

	public boolean assert_elementNotPresent(RemoteWebDriver driver, By by) throws Exception {
		boolean Notpresent;
		try {
			driver.findElement(by);
			Notpresent = false;
		} catch (NoSuchElementException e) {
			Notpresent = true;
		}
		if (!Notpresent) {
			addLog(by + " is not displayed in the page");
			assertTrue("Failure!", false);
		}
		return Notpresent;
	}

	public void assertCommon(RemoteWebDriver driver, String text, int waitTime, String message)
			throws IOException, InterruptedException {
		if (log_textPresent(driver, text, waitTime, message)) {
			assertTrue(message, textPresent(driver, text, waitTime));
		} else {
			addLog(text + " is not displayed in the page failed with error: " + message);
			assertTrue(message, textPresent(driver, text, waitTime));
		}
	}

	public boolean log_textPresent(RemoteWebDriver driver, String text, int Time, String message)
			throws InterruptedException {
		boolean textprest = false;
		long start = System.currentTimeMillis();
		int second = 0;
		for (second = 0; second < Time; second++) {
			try {
				if ((driver.getPageSource().contains(text))) {
					textprest = true;
					break;
				}
			} catch (Exception ignore) {
			}
			Thread.sleep(100);
		}
		long end = System.currentTimeMillis();
		long time = end - start;
		addLog("Time taken to load " + message + " :" + time + " Millisecs");
		return textprest;
	}

	public void BrowserOpen(RemoteWebDriver driver, String URL) throws InterruptedException {
		long start = System.currentTimeMillis();
		driver.get(URL);
		long end = System.currentTimeMillis();
		long time = end - start;
		addLog("Time taken to load HomePage :" + time + " Millisecs");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	protected boolean isTextPresent(RemoteWebDriver driver, String text) {
		try {
			boolean b = driver.getPageSource().contains(text);
			return b;
		} catch (Exception e) {
			return false;
		}
	}

	public void safeClick_JS(RemoteWebDriver driver, By by) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement we=driver.findElement(by);
		Actions a = new Actions(driver);
		a.moveToElement(we);
		a.perform();
		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		we = wait.until(ExpectedConditions.elementToBeClickable(by));
		we = wait.until(ExpectedConditions.visibilityOf(we));
/*
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
		System.out.println("First element JS");*/
		Thread.sleep(2000);

		driver.executeScript("return arguments[0].scrollIntoView();", we);
		Thread.sleep(2000);
		we.click();
	}

	public void pageScroll(RemoteWebDriver driver, int x, int y) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(x,y)");
	}

	public void safeClick(RemoteWebDriver driver, By by) throws Exception {

		elementVisible(driver, by, 20);
		elementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		we = wait.until(ExpectedConditions.elementToBeClickable(by));
		we = wait.until(ExpectedConditions.visibilityOf(we));
		if (isElementPresent(driver, by)) {
			Actions actions = new Actions(driver);
			actions.moveToElement(we).click().build().perform();
		} /*else {
			 addLog("Element " + by + "is not displayed in " +
			 driver.getCurrentUrl());
		}*/
	}

	public void safeClickLog(RemoteWebDriver driver, By by, String text, int time) throws Exception {
		elementPresent_log(driver, by,  text, time);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		we = wait.until(ExpectedConditions.elementToBeClickable(by));
		we = wait.until(ExpectedConditions.visibilityOf(we));
		if (isElementPresent(driver, by)) {
			Actions actions = new Actions(driver);
			actions.moveToElement(we).click().perform();
		} else {
			// addLog("Element " + by + "is not displayed in " +
			// driver.getCurrentUrl());
		}
	}

	public void pressEnterKey(RemoteWebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.ENTER).build().perform();
	}

	public void sendKeysByKeyboard(RemoteWebDriver driver,By by, String text) throws InterruptedException {
		WebElement el = driver.findElement(by);
		el.clear();
		String val = text;
		for (int i = 0; i < val.length(); i++) {
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			el.sendKeys(s);
			Thread.sleep(200);
		}
	}

	public void smartClick(RemoteWebDriver driver, By by) throws Exception {
		if (elementVisible(driver, by, 2)) {
			elementPresent(driver, by);
			WebElement we = driver.findElement(by);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			we = wait.until(ExpectedConditions.elementToBeClickable(by));
			we = wait.until(ExpectedConditions.visibilityOf(we));
			if (isElementPresent(driver, by)) {
				Actions actions = new Actions(driver);
				actions.moveToElement(we).click().perform();
			}
		}
	}

	public void safeClickList1(RemoteWebDriver driver, By by, String Text) throws Exception {
		elementVisible(driver, by, 10);
		List<WebElement> we = driver.findElements(by);
		for (int i = 0; i <= we.size() - 1; i++) {
			String elementText = we.get(i).getText();
			if (elementText.equalsIgnoreCase(Text)) {
				if (!we.get(i).isDisplayed()) {
					we.get(i).wait(5000);
				}
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0, 250)", "");
				Thread.sleep(5000);
				we.get(i).click();
				break;
			} else if (i == we.size() - 1) {
				addLog(Text + " : is not displayed in the List");
				Assert.assertTrue(false);
			}
		}
	}

	public void safeClickList(RemoteWebDriver driver, By by, String Text) throws Exception {
		elementVisible(driver, by, 5);
		boolean elementAvailable = false;
		List<WebElement> we = driver.findElements(by);
		for (WebElement WebEle : we) {
			String elementText = WebEle.getText();
			if (elementText.contains(Text)) {
				if (!WebEle.isDisplayed()) {
					Thread.sleep(1000);
				}

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0, 150)", "");
				elementAvailable = true;
				Thread.sleep(1000);
				WebEle.click();
				break;
			}
		}
		if (!elementAvailable) {
			System.out.println(Text + " : is not displayed in the List");
			addLog(Text + " : is not displayed in the List");
			Assert.assertTrue(false);
		}

	}

	public void textNotPresent_List(RemoteWebDriver driver, By by, String Text) throws Exception {
		elementVisible(driver, by, 5);
		boolean elementAvailable = false;
		List<WebElement> we = driver.findElements(by);
		for (WebElement WebEle : we) {
			String elementText = WebEle.getText();
			if (elementText.equalsIgnoreCase(Text)) {
				if (!WebEle.isDisplayed()) {
					Thread.sleep(1000);
				}

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0, 150)", "");
				elementAvailable = true;
				Thread.sleep(1000);
				addLog(Text + " : is not displayed in the List");
				Assert.assertTrue(false);
				break;
			}
		}
		if (!elementAvailable) {
			addLog(Text + " : is not displayed in the List");
			//Assert.assertTrue(false);
		}

	}

	public void scrollToPageTop(RemoteWebDriver driver) throws Exception {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-1000)", "");
	}

	public void safeClickListContains(RemoteWebDriver driver, By by, String Text) throws Exception {
		elementVisible(driver, by, 10);
		boolean elementAvailable = false;
		List<WebElement> we = driver.findElements(by);
		for (WebElement WebEle : we) {
			String elementText = WebEle.getText();
			if (elementText.contains(Text)) {
				if (!WebEle.isDisplayed()) {
					Thread.sleep(2000);
				}
				elementAvailable = true;
				Thread.sleep(2000);
				WebEle.click();
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0, 250)", "");
				break;
			}
		}

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0, 750)", "");
		if (!elementAvailable) {
			addLog(Text + " : is not displayed in the List");
			Assert.assertTrue(false);
		}

	}

	public void modalWindow_BackTo_MainWindow(RemoteWebDriver driver, By by, int time)
			throws InterruptedException, Exception {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		// driver.manage().window().maximize();
		elementVisible(driver, by, time);
		String Modal_URL = driver.getCurrentUrl();
		driver.switchTo().window(tabs.get(0));
		driver.get(Modal_URL);
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));
		elementVisible(driver, by, time);
	}

	public void safeClick_CheckBox(RemoteWebDriver driver, By by) throws Exception {
		elementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		we = wait.until(ExpectedConditions.elementToBeClickable(by));
		we = wait.until(ExpectedConditions.visibilityOf(we));
		if (!we.isSelected()) {
			we.click();
		}
	}

	public void safeUncheck(RemoteWebDriver driver, By by) throws Exception {
		elementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		we = wait.until(ExpectedConditions.elementToBeClickable(by));
		we = wait.until(ExpectedConditions.visibilityOf(we));
		if (we.isSelected()) {
			we.click();
		}
	}

	public void mouseHover(RemoteWebDriver driver, By by) throws Exception {
		elementPresent_Time(driver, by, 10);
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(by);
		action.moveToElement(we).build().perform();
	}

	public void mouseHoverClick(RemoteWebDriver driver, By by) throws Exception {
		elementPresent_Time(driver, by, 10);
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(by);
		action.moveToElement(we).click().build().perform();
		// action.click();
	}

	public static void textAssert(RemoteWebDriver driver, By by, String text) throws Exception {
		String text1 = driver.findElement(by).getText();
		if (!text1.contains(text)) {
			Reporter.log("<p>"+text1 + " is displayed instead of : " + text+"</p>");
		}
		Assert.assertTrue(text, text1.contains(text));
	}

	public void UnChecking_Checkbox(RemoteWebDriver driver, By by) throws Exception {
		elementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		if (driver.findElement(by).getAttribute("checked") != null) {
			we.click();
		}
	}

	public void Checking_Checkbox(RemoteWebDriver driver, By by) throws Exception {
		elementPresent(driver, by);
		WebElement we = driver.findElement(by);

		if (driver.findElement(by).getAttribute("checked") == null) {
			we.click();
		}
	}

	public static void waitForElementToBeSelected(RemoteWebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeSelected(by));
	}

	public void waitForElementToBeClickable(RemoteWebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void logMessagePageNotLoaded(RemoteWebDriver driver, By by, int Time, String Log_Message) throws Exception {
		if (!elementVisible(driver, by, Time)) {
			addLog(Log_Message);
		}
	}

	public void safeType(RemoteWebDriver driver, By by, String text) throws Exception {
		elementPresent(driver, by);
		boolean element = isElementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		we.clear();
		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		if (element) {
			if (isElementPresent(driver, by)) {
				we.isDisplayed();
				Actions actions = new Actions(driver);
				actions.moveToElement(we).click().perform();
				we.clear();
				we.sendKeys(text);
			}
		} else {
			addLog("Element " + by + " is not displayed in " + driver.getCurrentUrl());
		}
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void safeType_NonClear(RemoteWebDriver driver, By by, String text) throws Exception {
		elementPresent(driver, by);
		boolean element = isElementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// we.clear();
		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		if (element) {
			if (isElementPresent(driver, by)) {
				we.isDisplayed();
				Actions actions = new Actions(driver);
				actions.moveToElement(we).click().perform();
				we.clear();
				we.sendKeys(text);
			}
		} else {
			addLog("Element " + by + " is not displayed in " + driver.getCurrentUrl());
		}
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void smartType(RemoteWebDriver driver, By by, String text) throws Exception {
		if (elementPresent_Time(driver, by, 1)) {
			boolean element = isElementPresent(driver, by);
			WebElement we = driver.findElement(by);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			we.clear();
			we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			if (element) {
				if (isElementPresent(driver, by)) {
					we.isDisplayed();
					Actions actions = new Actions(driver);
					actions.moveToElement(we).click().perform();
					we.clear();
					we.sendKeys(text);
				}
			} else {
				addLog("Element " + by + " is not displayed in " + driver.getCurrentUrl());
			}
			// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		}
	}

	public void safeSelect(RemoteWebDriver driver, By by, String text) throws Exception {
		elementPresent(driver, by);
		boolean element = isElementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		if (element) {
			new Select(we).selectByVisibleText(text);
		} else {
			addLog("Element " + by + " or Text " + text + " is not displayed in " + driver.getCurrentUrl());
		}
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void safeSelectint(RemoteWebDriver driver, By by, int i) throws Exception {
		elementPresent(driver, by);
		boolean element = isElementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		if (element) {
			new Select(we).selectByIndex(i);
		} else {
			addLog("Element " + by + " or Text " + i + " is not displayed in " + driver.getCurrentUrl());
		}
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void safeSelectar(RemoteWebDriver driver, By by, String text) throws Exception {
		elementPresent(driver, by);
		boolean element = isElementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		if (element) {
			new Select(we).selectByValue("Mr");
		} else {
			addLog("Element " + by + " or Text " + text + " is not displayed in " + driver.getCurrentUrl());
		}
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void safeSelectByValue(RemoteWebDriver driver, By by, String text) throws Exception {
		elementPresent(driver, by);
		boolean element = isElementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		if (element) {
			new Select(we).selectByValue(text);
		} else {
			addLog("Element " + by + " or Text " + text + " is not displayed in " + driver.getCurrentUrl());
		}
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void safeSelectByText(RemoteWebDriver driver, By by, String text) throws Exception {
		elementPresent(driver, by);
		boolean element = isElementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		if (element) {
			new Select(we).selectByVisibleText(text);
		} else {
			addLog("Element " + by + " or Text " + text + " is not displayed in " + driver.getCurrentUrl());
		}
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}



	public void safeSelectByIndex(RemoteWebDriver driver, By by, int index) throws Exception {
		elementPresent(driver, by);
		boolean element = isElementPresent(driver, by);
		WebElement we = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		if (element) {
			if (isElementPresent(driver, by))
				new Select(we).selectByIndex(index);
		} else {
			addLog("Element " + by + " having index " + index + " is not displayed in " + driver.getCurrentUrl());
		}
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void smartSelect(RemoteWebDriver driver, By by, String text) throws Exception {
		if (elementPresent_Time(driver, by, 1)) {
			boolean element = isElementPresent(driver, by);
			WebElement we = driver.findElement(by);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			if (element) {
				if (isElementPresent(driver, by))
					new Select(we).selectByVisibleText(text);
			} else {
				addLog("Element " + by + " or Text " + text + " is not displayed in " + driver.getCurrentUrl(),
						true);
			}
			// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		}
	}

	public String getURL(RemoteWebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getAttribute(RemoteWebDriver driver, By element, String Attribute) throws Exception {
		return driver.findElement(element).getAttribute(Attribute);

	}

	public void dragAndDrop(RemoteWebDriver driver, By by, int X, int Y) throws Exception {
		WebElement element = driver.findElement(by);
		new Actions(driver).dragAndDropBy(element, X, Y).perform();
	}

	public void dragAndDrop1(RemoteWebDriver driver, WebElement element, int X, int Y) throws Exception {
		// WebElement element = driver.findElement(by);
		new Actions(driver).dragAndDropBy(element, X, Y).perform();
	}

	public void selectCalendarDate(RemoteWebDriver driver, By calendarName, By nextMonth, int noOfMonths, String Date)
			throws Exception {
		try {
			safeClick(driver, calendarName);
		} catch (Exception e) {

		}
		for (int i = 1; i <= noOfMonths; i++) {
			Thread.sleep(500);
			safeClick(driver, nextMonth);
		}
		Thread.sleep(500);

		try {
			driver.findElement(By.linkText(Date)).click();
		} catch (Exception e) {
			driver.findElement(
					By.xpath("//button[contains(@class, 'datepicker-cal-date') and @data-day='" + Date + "']")).click();
			;
		}
	}

	public void selectCalendarDate1(RemoteWebDriver driver, By calendarName, By nextMonth, int noOfMonths, String Date)
			throws Exception {
		try {
			safeClick(driver, calendarName);
		} catch (Exception e) {

		}
		for (int i = 1; i <= noOfMonths; i++) {
			Thread.sleep(500);
			safeClick(driver, nextMonth);
		}
		Thread.sleep(500);
		List<WebElement> we = driver.findElements(By.linkText(Date));
		for (int i = 0; i < we.size(); i++) {
			// System.out.println("i value="+i);
			we.get(i).click();
			// welist.click();
			break;
		}
		safeClick(driver, By.linkText(Date));
	}

	public void selectCalendarThisMonth(RemoteWebDriver driver, By calendarName, String Date) throws Exception {
		safeClick(driver, calendarName);
		Thread.sleep(500);
		safeClick(driver, By.xpath("//td[@class='selected current']/a"));
		// safeClick(driver, By.linkText(Date));
	}

	public void selectCalendarThisMonth2(RemoteWebDriver driver, By calendarName, String Date) throws Exception {
		safeClick(driver, calendarName);
		Thread.sleep(500);
		String date = getModifiedDate(driver, "5");
		// System.out.println("modified date="+date);
		safeClick(driver, By.linkText(Date));
	}

	public String getModifiedDate(RemoteWebDriver driver, String date1) {
		Calendar c = Calendar.getInstance();
		// System.out.println("input date="+date1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		c.add(Calendar.DATE, Integer.parseInt(date1));

		String convertedDate = dateFormat.format(c.getTime());
		System.out.println("Date increased=" + convertedDate);

		return convertedDate;
	}

	public void safeAutocomplete(RemoteWebDriver driver, By textBox, By ajax, String text) throws Exception {
		safeType(driver, textBox, text);
		Thread.sleep(1500);
		for (int i = 0; i <= 3; i++) {
			if (!isElementPresent(driver, ajax)) {
				safeType(driver, textBox, text);
				Thread.sleep(3000);
			}
			if (isElementPresent(driver, ajax)) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(ajax));
				element2.click();
				break;
			}
		}
	}

	public void safeAutocompleteMouseHover(RemoteWebDriver driver, By textBox, By ajax, String text) throws Exception {
		safeAutocomplete(driver, textBox, ajax, text);
		safeType(driver, textBox, text);
		Thread.sleep(1000);
		for (int i = 0; i <= 10; i++) {
			if (!isElementPresent(driver, ajax)) {
				safeType(driver, textBox, text);
				Thread.sleep(1000);
			}
			if (isElementPresent(driver, ajax)) {
				WebElement we = driver.findElement(ajax);
				Actions builder = new Actions(driver);
				builder.moveToElement(we, 10, 10).click().build().perform();
				break;
			}
		}
	}

	public void safeAutocomplete_CHMM(RemoteWebDriver driver, By textBox, By ajax, String text) throws Exception {
		safeType(driver, textBox, text);
		Thread.sleep(1000);
		for (int i = 0; i <= 3; i++) {
			if (!isElementPresent(driver, ajax)) {
				safeType(driver, textBox, text);
				Thread.sleep(5000);
			}
			if (isElementVisible(driver, ajax)) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(ajax));
				element2.click();
				break;
			}
		}
	}

	public String getText(RemoteWebDriver driver, By by) throws Exception {
		elementVisible(driver, by, 5);
		elementPresent(driver, by);
		boolean element = isElementPresent(driver, by);
		if (element) {

			if(isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
			}
			WebElement we = driver.findElement(by);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			String text = null;
			text = we.getText();
			return text;
		} else {
			addLog("Element " + by + " is not displayed in " + driver.getCurrentUrl());
			return null;
		}

	}

	public String getValue(RemoteWebDriver driver, By by) throws Exception {
		elementVisible(driver, by, 5);
		elementPresent(driver, by);
		boolean element = isElementPresent(driver, by);
		if (element) {

			if(isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
			}
			WebElement we = driver.findElement(by);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			String text = null;
			text = we.getAttribute("value");
			return text;
		} else {
			addLog("Element " + by + " is not displayed in " + driver.getCurrentUrl());
			return null;
		}

	}

	public String getText1(RemoteWebDriver driver, By by) throws Exception {
		elementVisible(driver, by, 1);
		boolean element = isElementPresent(driver, by);
		if (element) {

			if(isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
			}
			WebElement we = driver.findElement(by);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			we = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			String text = null;
			text = we.getText();
			return text;
		} else {
			addLog("Element " + by + " is not displayed in " + driver.getCurrentUrl());
			return null;
		}

	}

	public String logURL(RemoteWebDriver driver) throws Exception {
		String Url = driver.getCurrentUrl();
		addLog(Url);
		return Url;
	}

	public String logSysoURL(RemoteWebDriver driver) throws Exception {
		String Url = driver.getCurrentUrl();
		addLog(Url, true);
		return Url;
	}


	public void refreshPage(RemoteWebDriver driver) {
		driver.navigate().refresh();
	}

	public void SelectCarrier(RemoteWebDriver driver, String Carrier) throws Exception {
		String SelCarrier = "//input[contains(@id,'" + Carrier + "')]";
		// System.out.println(SelCarrier);
		org.testng.Assert.assertTrue(elementVisible(driver, By.xpath(SelCarrier), 10),
				"SelCarrier element not present");
		if (elementVisible(driver, By.xpath(SelCarrier), 20)) {
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(SelCarrier));
			action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Only')]")))
					.click();
			Thread.sleep(3000);
		} else {
			addLog(Carrier + " not displayed in the SRP");
		}
	}

	public static String getBrowserAndVersion() {

		String browser_version = null;
		Capabilities cap = ((RemoteWebDriver) browserDriver).getCapabilities();
		String browsername = cap.getBrowserName();
		// This block to find out IE Version number
		if ("internet explorer".equalsIgnoreCase(browsername)) {
			String uAgent = (String) ((JavascriptExecutor) browserDriver).executeScript("return navigator.userAgent;");
			// System.out.println(uAgent);
			// uAgent return as "MSIE 8.0 Windows" for IE8
			if (uAgent.contains("MSIE") && uAgent.contains("Windows")) {
				browser_version = uAgent.substring(uAgent.indexOf("MSIE") + 5, uAgent.indexOf("Windows") - 2);
			} else if (uAgent.contains("Trident/7.0")) {
				browser_version = "11.0";
			} else {
				browser_version = "0.0";
			}
		} else {
			// Browser version for Firefox and Chrome
			browser_version = "123";// .split(".")[0];
		}
		String browserversion = browser_version.substring(0, browser_version.indexOf("."));
		return browsername + " " + browserversion;
	}

	public void dragAndDrop(RemoteWebDriver driver, String Str_sourceElement, String Str_destinationElement) {
		WebElement sourceElement = driver.findElement(By.xpath(Str_sourceElement));
		WebElement destinationElement = driver.findElement(By.xpath(Str_destinationElement));
		try {
			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build().perform();
			} else {
				addLog("Element is not displayed to drag");
			}
		} catch (StaleElementReferenceException e) {
			addLog("Element with " + sourceElement + "or" + destinationElement
					+ "is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			addLog("Element " + sourceElement + "or" + destinationElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			addLog("Error occurred while performing drag and drop operation " + e.getStackTrace());
		}
	}

	public boolean isAlertPresent(RemoteWebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public static void switchFromTab2toTab1(RemoteWebDriver driver) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String Modal_URL1 = driver.getCurrentUrl();
		driver.switchTo().window(tabs.get(0));
		// driver.get(Modal_URL1);
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));
		driver.get(Modal_URL1);
	}

	public String getXpathByReplace(String loc, String replacedVal) {
		return loc.replaceAll("(?i)" + "nameLbl", replacedVal);
	}


	public void scrollToElement(RemoteWebDriver driver, By by) throws Exception {
		WebElement we = driver.findElement(by);
		if (isElementPresent(driver, by)) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", we);
		} else {
			addLog("Element " + by + "is not displayed in " + driver.getCurrentUrl(), true);
		}
	}

	public static boolean waitForElementVisibility(RemoteWebDriver driver, By by, int Time) throws Exception {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(Time)).until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			Reporter.log("<p>Element not visible after waitng for" + Time + "seconds.</p>");
			return false;
		}

	}
	public static boolean isElementSelected(RemoteWebDriver driver, By by) {
		return driver.findElement(by).isSelected();
	}




	public void javaExecutorClick(RemoteWebDriver driver, By by) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(by));
	}

	public void scrollSmooth(RemoteWebDriver driver,final int y) {
		for (int i = 0; i < y; i++) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,4)", "");
		}
	}

	public void pwascrollSmooth(RemoteWebDriver driver,final int y) {
		for (int i = 0; i < y; i++) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)", "");
		}
	}

	public void safestClickLocal(RemoteWebDriver driver, By by, String activityName) throws Exception {
		elementVisible(driver, by, 10);
		boolean elementAvailable = false;
		List<WebElement> we =driver.findElements(by);
		for(WebElement  WebEle : we ){
			String elementText = WebEle.getText();
			if(elementText.equalsIgnoreCase(activityName)){
				WebEle.click();
				elementAvailable = true;
				break;
			}else {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0, 50)", "");

			}
		}

		if(!elementAvailable){
			addLog(activityName + " : is not displayed in the List");
			Assert.assertTrue(false);
		}
	}

	public void moveToGivenElementLocatorByActionClassAndClickByJS(RemoteWebDriver driver,WebElement element) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(element).click();
		action.perform();
	}

	@SuppressWarnings("unused")
	public void openNewTabAddCtWalletMoneyAndSwitchBackToMainPage(RemoteWebDriver driver, String email) throws InterruptedException {
		addLog("Opening new chrome tab...",true);
		Thread.sleep(2000);

		Object ob=(JavascriptExecutor) driver.executeScript("window.open()");

		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		addLog("Switched to new chrome tab...",true);
		driver.switchTo().window(tabs.get(1)); //switches to new tab

		//  String urlToAddCTWallentMoney=dataFile.value("UrlToAddWalletMoney").replace("cleartripautomationsff@gmail.com", email);
		String urlToAddCTWallentMoney=dataFile.value("UrlToAddWalletMoney");
		//  addLog("Adding money into ctwallet for user : "+email,true);
		driver.get(urlToAddCTWallentMoney);
		addLog("Switching back to main chrome window...",true);
		driver.switchTo().window(tabs.get(0)); // switch back to main screen
		Thread.sleep(3000);
	}

	// Author Varalakshmi
	public void modalWindow(RemoteWebDriver driver)
			throws InterruptedException, Exception {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		//driver.manage().window().maximize();
		String Modal_URL = driver.getCurrentUrl();
		driver.switchTo().window(tabs.get(0));
		driver.get(Modal_URL);
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));
		String  newUrl =Modal_URL;
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
			newUrl = Modal_URL.replace("https://www.", "https://qa2.");
		}
		driver.get(newUrl);
	}

}

