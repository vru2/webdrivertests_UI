// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan, 2017
// Author - Kiran Kumar
// Copyright ï¿½ 2017 Cleartrip Travel. All right reserved

package domainServices;

import java.io.File;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

















//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import commonServices.WrapperMethod;

public class Locals extends WrapperMethod {
	int login = 0;
	// String Date = "7";
	public RemoteWebDriver driver;
	protected String baseUrl = "https://" + campLocal.value("host")
			+ common.value("url") + "com";
	public String locals_City_URL = baseUrl + "/local/"
			+ dataFile.value("Change_City") + "/things-to-do-in-"
			+ dataFile.value("Change_City");
	public String locals_City_SEO_URL = baseUrl + "/local/"
			+ dataFile.value("Change_City_SEO") + "/things-to-do-in-"
			+ dataFile.value("Change_City_SEO");
	public String locals_Klook_City_URL = baseUrl + "/local/"
			+ dataFile.value("Change_Klook_City") + "/things-to-do-in-"
			+ dataFile.value("Change_Klook_City");
	public String locals_Klook_City_URL_AdditionalInfo_WithList = baseUrl
			+ "/local/"
			+ dataFile.value("Change_Klook_City_AdditionalInfo_WithList")
			+ "/things-to-do-in-"
			+ dataFile.value("Change_Klook_City_AdditionalInfo_WithList");
	public String locals_Klook_City_URL_AdditionalInfo_WithoutList = baseUrl
			+ "/local/"
			+ dataFile.value("Change_Klook_City_AdditionalInfo_WithoutList")
			+ "/things-to-do-in-"
			+ dataFile.value("Change_Klook_City_AdditionalInfo_WithoutList");
	public String locals_Musement_City_URL = baseUrl + "/local/"
			+ dataFile.value("Change_Musement_City") + "/things-to-do-in-"
			+ dataFile.value("Change_Musement_City");
	public String locals_Burj_City_URL = baseUrl + "/local/"
			+ dataFile.value("Change_Burj_City") + "/things-to-do-in-"
			+ dataFile.value("Change_Burj_City");
	public String locals_City_URL_AbuDhabi = getBaseUrl("com")
			+ "/local/abu dhabi/things-to-do-in-abu dhabi";
	public String locals_City_Events_URL = getBaseUrl("com") + "/local/"
			+ dataFile.value("Change_City_Events") + "/things-to-do-in-"
			+ dataFile.value("Change_City_Events");
	public String locals_City_Feature_URL = getBaseUrl("com") + "/local/"
			+ dataFile.value("Change_City_Events") + "/featured-activities-in-"
			+ dataFile.value("Change_City_Events");
	public String localsAE_City_URL = getBaseUrl("ae") + "/local/"
			+ dataFile.value("Change_City") + "/things-to-do-in-"
			+ dataFile.value("Change_City");
	public String localsAE_City_URL_Abu = getBaseUrl("ae") + "/local/"
			+ dataFile.value("Change_City_AE") + "/things-to-do-in-"
			+ dataFile.value("Change_City_AE");
	public String localsWL_City_URL = localsWL_URL() + "/local/"
			+ dataFile.value("Change_City") + "/things-to-do-in-"
			+ dataFile.value("Change_City");
	public String localsWL_KlookCityWithoutList_URL = localsWL_URL()
			+ "/local/"
			+ dataFile.value("Change_Klook_City_AdditionalInfo_WithoutList")
			+ "/things-to-do-in-"
			+ dataFile.value("Change_Klook_City_AdditionalInfo_WithoutList");
	public String localsWL_KlookCityWithList_URL = localsWL_URL() + "/local/"
			+ dataFile.value("Change_Klook_City_AdditionalInfo_WithList")
			+ "/things-to-do-in-"
			+ dataFile.value("Change_Klook_City_AdditionalInfo_WithList");
	public String localsWL_KlookCityURL = localsWL_URL() + "/local/"
			+ dataFile.value("Change_Klook_City") + "/things-to-do-in-"
			+ dataFile.value("Change_Klook_City");
	public String localsWL_MusementCityURL = localsWL_URL() + "/local/"
			+ dataFile.value("Change_Musement_City") + "/things-to-do-in-"
			+ dataFile.value("Change_Musement_City");
	public String localsWL_City_URL_prod = localsWL_URL();
	public String localsWL_City_URL_AbuDhabi = localsWL_URL()
			+ "/local/abu dhabi/things-to-do-in-abu dhabi";
	public String locals_CampCity_URL = baseUrl + "/local/"
			+ campLocal.value("campCity") + "/things-to-do-in-"
			+ campLocal.value("campCity");
	public String locals_WLCampCity_URL = localsWL_URL() + "/local/"
			+ campLocal.value("campCity") + "/things-to-do-in-"
			+ campLocal.value("campCity");

	protected String baseUrl_AE = "https://" + common.value("host")
			+ common.value("url") + "ae";
	LocalDate currentDate = LocalDate.now();
	public String amntVal = "10", rateId;
	public final String CTINSTANT = "QACT" + currentDate.getMonthValue()
			+ currentDate.getDayOfMonth(), CTINSTANT2ND = "QACT2nd"
			+ currentDate.getMonthValue() + currentDate.getDayOfMonth();
	WebDriverWait wait = null;

	public String localsWL_URL() {
		String localsWL_URL = null;
		if (common.value("host").equals("qa2")) {
			localsWL_URL = "https://automationqa2.travelbox99.com";
		} else if (common.value("host").equals("hf")) {
			localsWL_URL = "https://automationqa2.travelbox99.com";
		} else if (common.value("host").equals("beta")) {
			localsWL_URL = "https://betalocal.triplocal.com";
		} else if (common.value("host").equals("www")) {

			localsWL_URL = "https://suresh.halli@cleartrip.com:test@betalocal.triplocal.com";
		}
		return localsWL_URL;
	}

	public void locals_SignIN(RemoteWebDriver driver, String User)
			throws Exception {
		// login=1;
		String mainWindow = driver.getWindowHandle();
		safeClick(driver, getObjectLocals("LocalCom_HomePage_YourTrips"));
		elementVisible(driver, getObjectLocals("LocalCom_HomePage_SignIN"), 20);
		safeClick(driver, getObjectLocals("LocalCom_HomePage_SignIN"));
		driver.switchTo().frame("modal_window");
		elementPresent_Time(driver,
				getObjectLocals("LocalCom_SignIn_ModalWindow_Email"), 50);
		if (User.isEmpty()) {
			safeType(driver,
					getObjectLocals("LocalCom_SignIn_ModalWindow_Email"),
					dataFile.value("LocalEmailID"));
			safeType(driver,
					getObjectLocals("LocalCom_SignIn_ModalWindow_Password"),
					dataFile.value("LocalPassword"));
		} else if (User.equalsIgnoreCase("Wallet")) {
			safeType(driver,
					getObjectLocals("LocalCom_SignIn_ModalWindow_Email"),
					dataFile.value("LocalWalletEmailID"));
			safeType(driver,
					getObjectLocals("LocalCom_SignIn_ModalWindow_Password"),
					dataFile.value("LocalWalletPwd"));
		}
		safeClick(driver,
				getObjectLocals("LocalCom_SignIn_ModalWindow_SignIN_Button"));
		Thread.sleep(7000);
		driver.switchTo().window(mainWindow);

	}

	public void locals_WL_SignIN(RemoteWebDriver driver, String User)
			throws Exception {
		String WL_URL = dataFile.value("Locals_WL_QA2");
		driver.get(WL_URL + "/local/signin");
		if (textPresent(driver, "Sorry, our system is acting up.", 1)) {
			Reporter.log(
					"Sorry, our system is acting up. : Error is displayed",
					true);
			Assert.assertTrue(false);
		}
		if (elementVisible(driver,
				getObjectLocals("LocalWL_SignINPage_EmailID"), 10)) {
			Reporter.log("White Lable Signin Page is not displayed", true);
		}
		safeType(driver, getObjectLocals("LocalWL_SignINPage_EmailID"),
				dataFile.value("Locals_WL_EmailID"));
		safeType(driver, getObjectLocals("LocalWL_SignINPage_Password"),
				dataFile.value("Locals_WL_Password"));

		if (User.equalsIgnoreCase("DA")) {
			safeType(driver,
					getObjectLocals("LocalCom_SignIn_ModalWindow_Email"),
					dataFile.value("HotelWalletsEmailID"));
			safeType(driver,
					getObjectLocals("LocalCom_SignIn_ModalWindow_Password"),
					dataFile.value("HotelWalletsPassword"));
		}

		safeClick(driver, getObjectLocals("LocalWL_SignINPage_Button"));
		if (elementVisible(driver,
				getObjectLocals("LocalWL_SignINPage_SignOutLink"), 10)) {
			Reporter.log("White Label SignIN not working", true);
		}
	}

	public void locals_Change_Location_MobileWeb(RemoteWebDriver driver)
			throws Exception {
		logMessagePageNotLoaded(driver,
				getObjectLocals("LocalMobileWeb_ChangeCity_LinkNEW"), 10,
				"Change City link is not displayed");
		safeClick(driver, getObjectLocals("LocalMobileWeb_ChangeCity_LinkNEW"));
		driver.switchTo().frame("modal_window");
		Thread.sleep(1000);
		safeAutocomplete(
				driver,
				getObjectLocals("LocalMobileWeb_ChangeCity_Localities_TextBox"),
				getObjectLocals("LocalMobileWeb_ChangeCity_Localities_Ajax"),
				dataFile.value("Change_City"));
		// safeClick(driver, By.xpath("//button[2]"));

	}

	public void locals_Change_Location_WL(RemoteWebDriver driver)
			throws Exception {
		logMessagePageNotLoaded(driver, By.xpath("//span[2]"), 10,
				"Change City link is not displayed");
		safeClick(driver, By.xpath("//span[2]"));
		driver.switchTo().frame("modal_window");
		Thread.sleep(2000);
		safeAutocomplete(
				driver,
				getObjectLocals("LocalMobileWeb_ChangeCity_Localities_TextBox"),
				getObjectLocals("LocalMobileWeb_ChangeCity_Localities_Ajax"),
				dataFile.value("Change_City"));
	}

	public void locals_Change_Location_MobileWeb_WL(RemoteWebDriver driver)
			throws Exception {
		logMessagePageNotLoaded(driver, By.xpath("//div/h1"), 10,
				"Change City link is not displayed");
		safeClick(driver, By.xpath("//div/h1"));
		driver.switchTo().frame("modal_window");
		Thread.sleep(3000);
		safeAutocomplete(
				driver,
				getObjectLocals("LocalMobileWeb_ChangeCity_Localities_TextBox"),
				getObjectLocals("LocalMobileWeb_ChangeCity_Localities_Ajax"),
				dataFile.value("Change_City"));
		Reporter.log("City changed to " + dataFile.value("Change_City"), true);
	}

	public void locals_Change_Location_MobileWeb_WL_V2(RemoteWebDriver driver,
			String cityNameKey) throws Exception {
		logMessagePageNotLoaded(driver, By.xpath("//div/h1"), 10,
				"Change City link is not displayed");
		safeClick(driver, By.xpath("//div/h1"));
		driver.switchTo().frame("modal_window");
		Thread.sleep(3000);
		safeAutocomplete(
				driver,
				getObjectLocals("LocalMobileWeb_ChangeCity_Localities_TextBox"),
				getObjectLocals("LocalMobileWeb_ChangeCity_Localities_Ajax"),
				dataFile.value(cityNameKey));
		Reporter.log("City changed to " + dataFile.value(cityNameKey), true);
	}

	public void locals_Change_Location(RemoteWebDriver driver) throws Exception {
		if (!elementVisible(driver,
				getObjectLocals("LocalCom_HomePage_Location_LinkNEW"), 5)) {
			Reporter.log("Change city Location Link is not displayed", true);
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectLocals("LocalCom_HomePage_Location_LinkNEW"));
		driver.switchTo().frame("modal_window");
		Thread.sleep(2000);
		safeType(driver, getObjectLocals("LocalEnterCity"),
				dataFile.value("Change_City"));
		safeClick(driver, getObjectLocals("LocalCom_TTD_Selectcity"));

	}

	public void locals_Select_LocalTab(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, By.xpath("//aside/nav/ul/li/a"), 20);
		safeClickList(driver, By.xpath("//aside/nav/ul/li/a"), "Local");
	}

	public void locals_SelectDate_enabled(RemoteWebDriver driver, String Date)
			throws Exception {
		String s;
		int flag = 0;
        Thread.sleep(3000);
		safeClick(driver, getObjectLocals("LocalCom_BookPage_Calender_Icon"));
		//driver.findElement(By.xpath("//a[@class='nextMonth ']")).click();
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 7; j++) {
				FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						         .withTimeout(30,TimeUnit.SECONDS)
						         .pollingEvery(3,TimeUnit.SECONDS)
						         .ignoring(NoSuchElementException.class);
			try {
					if (driver
							.findElement(
									By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
											+ i + "]/td[" + j + "]"))
							.getAttribute("data-event").isEmpty()) {
						i++;
						j++;
					} else if (driver
							.findElement(
									By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
											+ i + "]/td[" + j + "]"))
							.getAttribute("data-event").contains("click")
							|| driver
									.findElement(
											By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
													+ i + "]/td[" + j + "]"))
									.getAttribute("data-event")
									.contains("data-month")) {
						s = driver
								.findElement(
										By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
												+ i + "]/td[" + j + "]/a"))
								.getText();
						safeClick(driver, By.linkText(s));
						}
					} 
				catch (NullPointerException e) {
				}

			}
		}

			
		

	}

	public void locals_SelectDate(RemoteWebDriver driver, String Date)
			throws Exception {
		// String Date = putDate(7);
		safeClick(driver, getObjectLocals("LocalCom_BookPage_Calender_Icon"));
		elementVisible(driver,
				getObjectLocals("LocalCom_BookPage_Calender_Popup"), 5);
		if (elementNotVisible(driver, By.linkText(Date), 5)) {
			int incrementdate = Integer.parseInt(Date);
			incrementdate = incrementdate + 1;
			if (incrementdate > 30) {
				incrementdate = incrementdate - 30;
			}
			Date = String.valueOf(incrementdate);
			safeClick(driver, By.linkText(Date));
			if (!elementNotVisible(driver, By.linkText(Date), 5)) {
				safeClick(driver,
						getObjectLocals("LocalCom_BookPage_Calender_Icon"));
				safeClick(driver,
						getObjectLocals("LocalCom_BookPage_Calender_NextMonth"));

			}

		}
		safeClick(driver, getObjectLocals("LocalCom_BookPage_Calender_Icon"));
		safeClick(driver, By.linkText(Date));
	}

	public void locals_SelectDate_Events(RemoteWebDriver driver, int Date)
			throws Exception {
		List<WebElement> Date_Cont = driver.findElements(By
				.xpath("//label/span[2]"));
		Date_Cont.get(Date - 1).click();
		/*
		 * int size = Date_Cont.size(); System.out.println("Size : "+size);
		 * Date_Cont.get(size-1).click();
		 */
	}

	public void locals_MobileWeb_SelectDate_Events(RemoteWebDriver driver,
			int Date) throws Exception {
		List<WebElement> Date_Cont = driver.findElements(By.xpath("//label"));
		Date_Cont.get(Date - 1).click();
	}

	public void locals_MobileWeb_SelectDate(RemoteWebDriver driver, String Date)
			throws Exception {
		// String Date = putDate(7);
		safeClick(driver,
				getObjectLocals("LocalCom_BookPage_Calender_Icon_Mobile"));
		elementVisible(driver,
				getObjectLocals("LocalCom_BookPage_Calender_Popup"), 5);
		if (elementNotVisible(driver, By.linkText(Date), 5)) {

			int incrementdate = Integer.parseInt(Date);
			incrementdate = incrementdate + 1;
			if (incrementdate > 30) {
				incrementdate = incrementdate - 30;
			}
			Date = String.valueOf(incrementdate);
			if (elementNotVisible(driver, By.linkText(Date), 5)) {
				Calendar c = new GregorianCalendar();
				c.add(Calendar.DATE, +0);
				Date s = c.getTime();
				String dateString[] = new SimpleDateFormat("dd/MM/yyyy")
						.format(s).split("/");
				// //System.out.println(dateString[0]);

				String tdate = dateString[0];
				int tdates = Integer.parseInt(tdate);
				tdates = tdates + 10;
				tdate = String.valueOf(tdates);

				safeClick(driver, By.linkText(String.valueOf(23)));

			}
			if (!elementNotVisible(driver, By.linkText(Date), 5)) {
				safeClick(driver,
						getObjectLocals("LocalCom_BookPage_Calender_Icon"));
				safeClick(driver,
						getObjectLocals("LocalCom_BookPage_Calender_NextMonth"));

			}
			// safeClick(driver,
			// getObjectLocals("LocalCom_BookPage_Calender_NextMonth"));
		}
		// safeClick(driver,By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td["+Date+"]/span"));
		if (elementNotVisible(driver, By.linkText(Date), 5)) {
			safeClick(driver,
					getObjectLocals("LocalCom_BookPage_Calender_Icon"));
			safeClick(driver, By.linkText(Date));
		}

	}
/*-----------------------------------new method activity-----------------------------------
	public static void activitySelectDate(RemoteWebDriver driver,String plusCount) {
		String curDate = String.valueOf(currentDate.getDayOfMonth() + Integer.parseInt(plusCount));
		//seleniumClick(activityLoc.detailPageCalender);
		safeClick(driver, getObjectLocals("LocalCom_BookPage_Calender_Icon_Mobile"));		

if(Integer.parseInt(curDate)>31||Integer.parseInt(curDate)>30) {
			driver.findElement(By.xpath("//a[@class='nextMonth ']")).click();
			curDate=String.valueOf(Integer.parseInt(curDate)-30);
		}
		String date="//div[@id='ui-datepicker-div']//td[a/text()='nameLbl']";
		String available = getAttributeValue(driver, date, curDate, "title");
		if (Integer.parseInt(available) >= 1) {
			seleniumClick(driver,date, curDate);
		}
		Reporter.log("Date Selected for activity booking :"+curDate,true);
		
	}



	public static String getAttributeValue(RemoteWebDriver driver, String loc, String replaceVal, String attVal) {
		if (!replaceVal.isEmpty()) {
			String val = getXpathByReplace(loc, replaceVal);
			return driver.findElement(By.xpath(val)).getAttribute(attVal);
		} /*else {
			scrollToViewElement(driver, getObject(loc));
			return driver.findElement(getObject(loc)).getAttribute(attVal);
		}
		return null;
	}


	public static String getXpathByReplace(String loc, String replacedVal) {
		return loc.replaceAll("(?i)" + "nameLbl", replacedVal);
	}	
	
	
	

	
	
	
	*/
//-------------------------------------------------------------------------
	
	
	
	
	
	
	
//New method for Mobile Web//
	public void locals_MobileWeb_SelectDate_enabled(RemoteWebDriver driver, String Date)
			throws Exception {
		String s;
		int flag = 0;
		//Thread.sleep(3000);
		safeClick(driver, getObjectLocals("LocalCom_BookPage_Calender_Icon_Mobile"));
		//driver.findElement(By.xpath("//a[@class='nextMonth ']")).click();
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 7; j++) {
				/*System.out
						.println(driver
								.findElement(
										By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
												+ i + "]/td[" + j + "]"))
								.getAttribute("class"));
*/
		//		Thread.sleep(5000);
				String We = driver.findElement(
						By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
								+ i + "]/td[" + j + "]")).getAttribute("class");
	/*			System.out.print(driver.findElement(
						By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
								+ i + "]/td[" + j + "]")).getAttribute(
						"data-event"));*/
				try {
					if (driver
							.findElement(
									By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
											+ i + "]/td[" + j + "]"))
							.getAttribute("data-event").isEmpty()) {
						i++;
						j++;
					} else if (driver
							.findElement(
									By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
											+ i + "]/td[" + j + "]"))
							.getAttribute("data-event").contains("click")
							|| driver
									.findElement(
											By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
													+ i + "]/td[" + j + "]"))
									.getAttribute("data-event")
									.contains("data-month")) {
						s = driver
								.findElement(
										By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr["
												+ i + "]/td[" + j + "]/a"))
								.getText();

						Calendar c = new GregorianCalendar();
						c.add(Calendar.DATE, +0);
						Date s1 = c.getTime();
						String dateString[] = new SimpleDateFormat("dd/MM/yyyy")
								.format(s1).split("/");
						// //System.out.println(dateString[0]);

						String c1 = dateString[0];
						int newdate = Integer.parseInt(c1);
						newdate = newdate +1;

						int avalaiblecurrentdate = Integer.parseInt(s);

						if (avalaiblecurrentdate > newdate) {
							safeClick(driver, By.linkText(s));
							flag = 1;
							break;
						}
					}
				} catch (NullPointerException e) {
				}

				

			}
			if (flag == 1)
				break;
		}
	}
	
	//
	
	
	
	
	public void locals_NameSearch_Events(RemoteWebDriver driver,
			String EventType, String EventName) throws Exception {
		elementVisible(driver,
				getObjectLocals("LocalCom_HomePage_Activity_TabNEW"), 10);
		safeClickList(driver,
				getObjectLocals("LocalCom_HomePage_Activity_TabNEW"), "Events");
		logURL(driver);
		if (EventType.equalsIgnoreCase("Caraousal")) {
			if (!elementVisible(
					driver,
					getObjectLocals("LocalMobileWeb_Events_HomePage_Caraousal"),
					20)) {
				Reporter.log("Caraousal is not displayed", true);
				Assert.assertTrue(false);
			}
			Thread.sleep(3000);
			safeClick(driver,
					getObjectLocals("LocalMobileWeb_Events_HomePage_Caraousal"));

			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			Thread.sleep(3000);
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"),
					EventName);

		} else if (EventType.equalsIgnoreCase("ProductEditorial")) {
			if (!elementVisible(
					driver,
					getObjectLocals("LocalMobileWeb_Events_HomePage_ProductEditorial"),
					20)) {
				Reporter.log("Prod Editorial is not displayed", true);
				Assert.assertTrue(false);
			}
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,150)", "");
			Thread.sleep(2000);
			safeClick(
					driver,
					getObjectLocals("LocalMobileWeb_Events_HomePage_ProductEditorial"));
			Thread.sleep(2000);
			jse.executeScript("window.scrollBy(0,200)", "");
			Thread.sleep(3000);
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"),
					EventName);
		}

		else {
			if (!elementVisible(driver,
					By.xpath("//h2[@class='metaName truncate']"), 20)) {
				Reporter.log("Data is not displayed in Locals Homepage", true);
				Assert.assertTrue(false);
			}
			// safeClickList(driver,
			// getObjectLocals("LocalMobileWeb_Group_LinkList2"),
			// EventName);
			scrollToElement(driver,
					getObjectLocals("LocalMobileWeb_Activity_LinkList"));
			safeClick(driver, By.xpath((getXpathByReplace(
					objectReposLocals.value("LocalMobileWeb_Event_Selction"),
					EventName))));
			// safeClickList(driver,
			// getObjectLocals("LocalMobileWeb_Activity_LinkList"),
			// EventName);
		}
	}

	public void locals_NameSearch_TTD(RemoteWebDriver driver, String TTDType,
			String TTDName) throws Exception {
		if (TTDType.equalsIgnoreCase("Caraousal")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalMobileWeb_TTD_HomePage_Caraousal"),
					20)) {
				Reporter.log("Caraousal is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver,
					getObjectLocals("LocalMobileWeb_TTD_HomePage_Caraousal"));
		} else if (TTDType.equalsIgnoreCase("ProductEditorial")) {
			if (!elementVisible(driver,
					By.xpath("//div/div[1]/nav/ul/li/a/div/div/h2"), 20)) {
				Reporter.log("Prod Editorial is not displayed", true);
				Assert.assertTrue(false);
			} else {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,100)", "");
				System.out.println("clicking prod editorial");
				Thread.sleep(3000);
				safeClick(driver,
						By.xpath("//div/div[1]/nav/ul/li/a/div/div/h2"));
				// driver.findElement(By.xpath("((//a/div[@class='imageContainer'])[1]")).click();
			}
		}
		/*
		 * else if(elementVisible(driver,
		 * By.xpath("//a[@class='card typeCollection']/div/div/h2"), 10)) {
		 * List<WebElement> we=driver.findElements(By.
		 * xpath("//a[@class='card typeCollection']/div/div/div")); for(int
		 * i=0;i<we.size();i++){
		 * if(we.get(i).getText().equalsIgnoreCase("boating")) {
		 * we.get(i).click(); }
		 * 
		 * }
		 * 
		 * }
		 */
		else {
			// if(!elementVisible(driver,
			// By.xpath("//div[5]/nav/ul/li/a/div/div/h2"), 20)){
			if (!elementVisible(
					driver,
					By.xpath("//div/nav/ul[@class='row clearfix imageGrid']/li/a"),
					20)) {
				// ul[@class='row clearfix imageGrid']/li/a
				Reporter.log("Data is not displayed in Locals Homepage", true);
				Assert.assertTrue(false);
			}
			// scrollToElement(driver,
			// getObjectLocals("LocalMobileWeb_TTD_HomePage_LinkList"));
			safestClickLocal(driver,
					getObjectLocals("LocalMobileWeb_TTD_HomePage_LinkList"),
					TTDType);
		}
		Thread.sleep(2000);
		driver.navigate().refresh();
		wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By
				.xpath("//p[contains(text(),'Fetching details')]"))));
		// System.out.println("entered listing page");
		if (elementVisible(driver,
				getObjectLocals("LocalMobileWeb_Group_LinkList2"), 5)) {
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"), TTDName);
		}
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By
				.xpath("//p[contains(text(),'Fetching details')]"))));
	}

	public void locals_NameSearch_FNB(RemoteWebDriver driver, String FNBType,
			String FNBName) throws Exception {
		elementVisible(driver,
				getObjectLocals("LocalCom_HomePage_Activity_TabNEW"), 10);
		safeClickList(driver,
				getObjectLocals("LocalCom_HomePage_Activity_TabNEW"), "Eat Out");

		if (FNBType.equalsIgnoreCase("Carousal")) {
			if (!elementVisible(
					driver,
					getObjectLocals("LocalMobileWeb_FNB_HomePage_CaraousalNEW"),
					20)) {
				Reporter.log("Caraousal is not displayed", true);
				Assert.assertTrue(false);
			}
			Thread.sleep(2000);
			safeClick(driver,
					getObjectLocals("LocalMobileWeb_FNB_HomePage_CaraousalNEW"));
		} else if (FNBType.equalsIgnoreCase("ProductEditorial")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalMobileWeb_ProductEditorial_Link"), 20)) {
				Reporter.log("Prod Editorial is not displayed", true);
				Assert.assertTrue(false);
			}
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,100)", "");
			safeClick(driver, By.xpath("//div/div[1]/nav/ul/li/a/div/div/h2"));
		}

		else

		{

			Thread.sleep(5000);
			if (!elementVisible(driver,
					getObjectLocals("LocalMobileWeb_FNB_LinkList"), 20)) {
				Reporter.log("Data is not displayed in Locals Homepage", true);
				Assert.assertTrue(false);
			}
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,200)", "");
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_FNB_LinkList"), FNBType);
		}
		wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By
				.xpath("//p[contains(text(),'Fetching details')]"))));

		if (elementVisible(driver,
				getObjectLocals("LocalMobileWeb_Group_LinkList2"), 5)) {
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"), FNBName);
		}
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By
				.xpath("//p[contains(text(),'Fetching details')]"))));
		// safeClick(driver,
		// getObjectLocals("LocalCom_Activity_BookPage_Bookbutton"));
	}

	public void locals_BookPopUP(RemoteWebDriver driver, String ActivityType,
			String BookingType, int count) throws Exception {
		String Date = putDate(7);
		if (Date.startsWith("0")) {
			Date = Date.substring(1);
		}
		Thread.sleep(2000);
		logURL(driver);
		elementVisible(driver, By.cssSelector("h1.booking-form__title"), 5);
		String BookMsg = getText(driver,
				By.cssSelector("h1.booking-form__title"));
		if (!BookMsg.contains("Reserve your spot now")) {
			Reporter.log("Reserve your spot now : Message is not displayed",
					true);
			Assert.assertTrue(false);
		}
		if (textPresent(driver,
				"There was an error trying to communicate with the server", 1)) {
			Reporter.log(
					"There was an error trying to communicate with the server : Message is not displayed",
					true);
			Assert.assertTrue(false);
		}
		if (ActivityType.equalsIgnoreCase("FNB")) {
			logMessagePageNotLoaded(driver,
					getObjectLocals("LocalCom_BookPage_Calender_Icon"), 10,
					"Book PopUp not displayed");
			logURL(driver);
			locals_SelectDate_enabled(driver, Date);
			if (BookingType.equalsIgnoreCase("Adult")) {
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
			}

			else if (BookingType.equalsIgnoreCase("AdultChild")) {
				// safeSelectByIndex(driver, By.id("options"), 1);
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
						"2");
			} else if (BookingType.equalsIgnoreCase("AdultTime")) {
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_TimeSlot"),
						"2");
				Thread.sleep(2000);
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"2");

			} else if (BookingType.equalsIgnoreCase("Couple")) {
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
			} else if (BookingType.equalsIgnoreCase("Group")) {
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"2");
			} else if (BookingType.equalsIgnoreCase("Caraousal")) {

			}
			// Thread.sleep(5000);
			safeClick(driver,
					getObjectLocals("LocalCom_Activity_BookPage_Bookbutton"));
			if (textPresent(driver,
					"There was an error trying to communicate with the server",
					2)) {
				Reporter.log(
						"There was an error trying to communicate with the server : Message is displayed",
						true);
				Assert.assertTrue(false);
			}
			// safeClick(driver,
			// getObjectLocals("LocalCom_FNB_BookPage_Bookbutton"));
		}

		else if (ActivityType.equalsIgnoreCase("TTD")) {
			logMessagePageNotLoaded(driver,
					getObjectLocals("LocalCom_BookPage_Calender_Icon"), 10,
					"Book PopUp not displayed");
			logURL(driver);
			int date = Integer.parseInt(Date);

			date = date + 9;

			if (date > 30) {
				date = date - 30;
			}
			Date = String.valueOf(date);
			locals_SelectDate_enabled(driver, Date);

			/*
			 * if(!elementClickable(driver,By.linkText(Date),2)) {
			 * locals_SelectDate(driver,String.valueOf(date+1)); } else {
			 * locals_SelectDate(driver,String.valueOf(date-1)); }
			 */

			if (BookingType.equalsIgnoreCase("Adult")) {
				// safeSelect(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				// "5");
				for (int i = 1; i < count; i++) {
					safeClick(driver, By.xpath("(//input[@value='+'])[1]"));
				}
			}

			else if (BookingType.equalsIgnoreCase("AdultChild")) {
				/*
				 * safeSelect(driver,
				 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				 * "1"); safeSelect(driver,
				 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
				 * "1");
				 */
				for (int i = 1; i < count; i++) {
					safeClick(driver, By.xpath("(//input[@value='+'])[1]"));
					safeClick(driver, By.xpath("(//input[@value='+'])[2]"));
				}
			}

			else if (BookingType.equalsIgnoreCase("Kids")) {
				for (int i = 1; i < count; i++) {
					safeClick(driver, By.xpath("(//input[@value='+'])[1]"));
				}
				// safeSelect(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
				// "2");
			} else if (BookingType.equalsIgnoreCase("MultiRate")) {
				safeSelectByIndex(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_TimeSlot"),
						2);
				// safeSelect(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				// "1");

			} else if (BookingType.equalsIgnoreCase("Group")) {
				// safeSelect(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				// "2");
			} else if (BookingType.equalsIgnoreCase("AdultTime")) {
				Thread.sleep(2000);
				// safeSelectByIndex(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_TimeSlot"),
				// 2);
				for (int i = 1; i < count; i++) {
					safeClick(driver, By.xpath("//input[@value='+']"));
				}
				// safeClickListContains(driver,
				// By.xpath("//input[@id='adult']/option"),"10");
				// safeSelect(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				// "5");
			} else if (BookingType.equalsIgnoreCase("Caraousal")) {

			}
			// safeClick(driver, By.xpath("//button[text()='Book now']"));
			safeClick(driver,
					getObjectLocals("LocalCom_TTD_BookPage_Bookbutton"));
			if (textPresent(driver,
					"There was an error trying to communicate with the server",
					2)) {
				Reporter.log(
						"There was an error trying to communicate with the server : Message is displayed",
						true);
				Assert.assertTrue(false);
			}

		} else if (ActivityType.equalsIgnoreCase("Events")) {
			logMessagePageNotLoaded(driver,
					getObjectLocals("LocalCom_Events_BookPage_Bookbutton"), 10,
					"Book PopUp not displayed");
			logURL(driver);
			locals_SelectDate_Events(driver, 1);

			if (BookingType.isEmpty()) {

			} else if (BookingType.equalsIgnoreCase("MeraFree")) {
				locals_Event_PaymentType(driver, "free");
			}

			else if (BookingType.equalsIgnoreCase("MeraPaid")) {
				locals_Event_PaymentType(driver, "paid");
			} else if (BookingType.equalsIgnoreCase("InsiderFree")) {
				locals_Event_PaymentType(driver, "free");
			}

			else if (BookingType.equalsIgnoreCase("InsiderPaid")) {
				locals_Event_PaymentType(driver, "paid");
			} else if (BookingType.equalsIgnoreCase("Carousal")) {
				elementVisible(driver,
						getObjectLocals("LocalCom_Events_PopUp_Add_Tickets"),
						20);
				safeClick(driver,
						getObjectLocals("LocalCom_Events_PopUp_Add_Tickets"));
			}

			safeClick(driver,
					getObjectLocals("LocalCom_Events_BookPage_Bookbutton"));
			if (elementVisible(driver,
					getObjectLocals("LocalCom_Events_Error"), 5)) {
				String Error = getText(driver,
						getObjectLocals("LocalCom_Events_Error"));
				Reporter.log(Error + " : Error message is displayed", true);
				Assert.assertTrue(false);
			}
		}
	}

	public String locals_ItineraryPage(RemoteWebDriver driver,
			String ActivityType) throws Exception {
		String[] parts = null;
		logURL(driver);
		String cashBack = null;
		WebDriverWait wait = new WebDriverWait(driver, 50);
		/*
		 * for (int i = 0; i <= 20; i++) { if (elementVisible(driver,
		 * getObjectLocals("LocalCom_ItineraryPage_Button"), 1)) { break; } else
		 * if (textPresent(driver, "Whatever you're looking for, isn't here",
		 * 1)) { Reporter. log(
		 * "Whatever you're looking for, isn't here : error message in Itinerary Page"
		 * ); Assert.assertTrue(false); } else if (textPresent(driver,
		 * "Sorry, our system is acting up.", 1)) { Reporter.
		 * log("Sorry, our system is acting up :  error message in Itinerary Page"
		 * ); Assert.assertTrue(false); }
		 * 
		 * }
		 */
		
Thread.sleep(5000);
	try
	{if(driver.findElement(By.xpath("//*[@id='itineraryBtn1']")).isDisplayed())
		{
			driver.findElement(By.xpath("//*[@id='itineraryBtn1']")).click();
		}
	}catch(Exception e)
	{
		
	}
		if (!elementVisible(driver,
				getObjectLocals("LocalCom_ItineraryPage_Title"), 10)) {
			Reporter.log("Itinerary Page is not displayed", true);
			Assert.assertTrue(false);
		}
		safeSelect(driver, getObjectLocals("LocalCom_ItineraryPage_Title"),
				dataFile.value("Title"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_FirstName"),
				dataFile.value("First_Name_A1"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_LastName"),
				dataFile.value("Last_Name_A1"));
		String emailText = driver.findElement(By.id("userEmail")).getAttribute(
				"value");
		if (emailText.isEmpty()) {
			safeType(driver, getObjectLocals("LocalCom_ItineraryPage_EmailID"),
					campLocal.value("bookingEmailId"));
		}

		/*
		 * if (!ActivityType.contains("HomePageSignIN")) { safeType(driver,
		 * getObjectLocals("LocalCom_ItineraryPage_EmailID"),
		 * campLocal.value("bookingEmailId")); }
		 */

		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_PhoneNo"),
				dataFile.value("LocalsPhoneNO"));
	try{
		if(driver.findElement(By.xpath("//*[@id='use_vat']")).isSelected()==true)
		{
			driver.findElement(By.xpath("//*[@id='use_vat']")).click();
		}
	}catch(Exception e)
	{
		
	}
		
		
		
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		if (ActivityType.equalsIgnoreCase("Coupon")) {
			safeType(driver, getObjectLocals("LocalCoupon"),
					dataFile.value("Localinstcoupon"));
			safeClick(driver, getObjectLocals("Localapply"));
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg"), 20)) {
				Reporter.log("Coupon is not wrking", true);
				Assert.assertTrue(false);
			}
			String CouponMsg = getText(driver,
					getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg"));
			if (CouponMsg.contains("Great! You just saved ")) {
				parts = CouponMsg.split("[a-zA-Z]+");
				cashBack = parts[5];
				Reporter.log("Coupon Message displayed : " + CouponMsg, true);
				// Assert.assertTrue(false);

			} else if (CouponMsg
					.contains("Sorry, the coupon code entered is invalid")) {
				Reporter.log("Coupon Message displayed : " + CouponMsg, true);
				Assert.assertTrue(false);
			}
			jse.executeScript("window.scrollBy(0,100)", "");
		} else if (ActivityType.equalsIgnoreCase("FullWalletCoupon")) {
			safeType(driver, getObjectLocals("LocalCoupon"),
					dataFile.value("LocalFullwallet"));
			safeClick(driver, getObjectLocals("Localapply"));
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg"), 20)) {
				Reporter.log("Coupon is not wrking", true);
				Assert.assertTrue(false);
			}
			String CouponMsg = getText(driver,
					getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg"));
			if (CouponMsg.contains("Great! You just saved ")) {
				parts = CouponMsg.split("[a-zA-Z]+");
				cashBack = parts[5];
				Reporter.log("Coupon Message displayed : " + CouponMsg, true);
				// Assert.assertTrue(false);

			} else if (CouponMsg
					.contains("Sorry, the coupon code entered is invalid")) {
				Reporter.log("Coupon Message displayed : " + CouponMsg, true);
				Assert.assertTrue(false);
			}
			jse.executeScript("window.scrollBy(0,100)", "");
		} else if (ActivityType.equalsIgnoreCase("GV")) {
			safeType(driver, getObjectLocals("LocalCoupon"),
					dataFile.value("LocalGV"));
			safeType(driver, getObjectLocals("Local_GV_PIN"),
					dataFile.value("LocalGVPin"));
			safeClick(driver, getObjectLocals("Localapply"));
			Thread.sleep(2000);
			if (elementVisible(driver,
					getObjectLocals("LocalComItineraryGVMsg"), 20)) {
				Reporter.log("GV is working", true);

			} else {
				System.out.println("GV not working");
				Assert.assertTrue(false);
			}
			/*
			 * String CouponMsg = getText(driver,
			 * getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg")); if
			 * (CouponMsg.contains("Great! You just saved ")) { parts =
			 * CouponMsg.split("[a-zA-Z]+"); cashBack = parts[5];
			 * Reporter.log("Coupon Message displayed : " + CouponMsg); //
			 * Assert.assertTrue(false);
			 * 
			 * } else if
			 * (CouponMsg.contains("Sorry, the coupon code entered is invalid"))
			 * { Reporter.log("Coupon Message displayed : " + CouponMsg);
			 * Assert.assertTrue(false); }
			 */
			jse.executeScript("window.scrollBy(0,100)", "");
		} else if (ActivityType.equalsIgnoreCase("GVCC")) {
			safeType(driver, getObjectLocals("LocalCoupon"),
					dataFile.value("LocalGVCC"));
			safeType(driver, getObjectLocals("Local_GV_PIN"),
					dataFile.value("LocalGVCCPin"));
			safeClick(driver, getObjectLocals("Localapply"));
			Thread.sleep(2000);
			if (elementVisible(driver,
					getObjectLocals("LocalComItineraryGVMsg"), 20)) {
				Reporter.log("GV is working", true);

			} else {
				System.out.println("GV not working");
				Assert.assertTrue(false);
			}
			/*
			 * String CouponMsg = getText(driver,
			 * getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg")); if
			 * (CouponMsg.contains("Great! You just saved ")) { parts =
			 * CouponMsg.split("[a-zA-Z]+"); cashBack = parts[5];
			 * Reporter.log("Coupon Message displayed : " + CouponMsg); //
			 * Assert.assertTrue(false);
			 * 
			 * } else if
			 * (CouponMsg.contains("Sorry, the coupon code entered is invalid"))
			 * { Reporter.log("Coupon Message displayed : " + CouponMsg);
			 * Assert.assertTrue(false); }
			 */
			jse.executeScript("window.scrollBy(0,100)", "");
		} else if (ActivityType.equalsIgnoreCase("Wallet")) {
			safeType(driver, getObjectLocals("LocalCoupon"),
					dataFile.value("Localwalcoupon"));
			safeClick(driver, getObjectLocals("Localapply"));

			if (!elementVisible(driver,
					getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg"), 10)) {
				Reporter.log("Coupon is not wrking", true);
				Assert.assertTrue(false);
			}
			String CouponMsg = getText(driver,
					getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg"));
			if (CouponMsg
					.contains("will be credited to your Cleartrip Wallet post booking")) {
				parts = CouponMsg.split("[a-zA-Z]+");
				cashBack = parts[1];
				Reporter.log("Coupon Message displayed : " + CouponMsg, true);
				// Assert.assertTrue(false);
			}
			// JavascriptExecutor jse = (JavascriptExecutor) driver;
			// jse.executeScript("window.scrollBy(0,100)", "");
			scrollToElement(driver,
					getObjectLocals("LocalCom_ItineraryPage_Button"));
		} else if (ActivityType.equalsIgnoreCase("WalletPlus")) {
			safeType(driver, getObjectLocals("LocalCoupon"),
					dataFile.value("Localwalpluscoupon"));
			safeClick(driver, getObjectLocals("Localapply"));
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg"), 20)) {
				Reporter.log("Coupon is not wrking", true);
				Assert.assertTrue(false);
			}
			String CouponMsg = getText(driver, getObjectLocals("coupon_Msg"));
			parts = CouponMsg.split("[a-zA-Z]+");
			int a = Integer.parseInt(parts[5].trim());
			int b = Integer.parseInt(parts[13].trim());
			cashBack = Integer.toString(a + b);

			if (!CouponMsg.contains("Great! You just saved ")) {
				Reporter.log("Coupon Message displayed : " + CouponMsg, true);
				Assert.assertTrue(false);
			} else if (CouponMsg
					.contains("Sorry, the coupon code entered is invalid")) {
				Reporter.log("Coupon Message displayed : " + CouponMsg, true);
				Assert.assertTrue(false);
			}
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_ItineraryPage_DualCashBack_Msg"),
					20)) {
				Reporter.log("CLTP Wallet msg is not displayed", true);
				Assert.assertTrue(false);
			}
			String CashBackMsg = getText(driver,
					getObjectLocals("LocalCom_ItineraryPage_DualCashBack_Msg"));
			if (!CashBackMsg.contains("in your Cleartrip wallet")) {
				Reporter.log("Coupon Message displayed : " + CashBackMsg, true);
				Assert.assertTrue(false);
			} else if (CashBackMsg
					.contains("Sorry, the coupon code entered is invalid")) {
				Reporter.log("Coupon Message displayed : " + CashBackMsg, true);
				Assert.assertTrue(false);
			}
			// JavascriptExecutor jse = (JavascriptExecutor) driver;
			// jse.executeScript("window.scrollBy(0,100)", "");
			scrollToElement(driver,
					getObjectLocals("LocalCom_ItineraryPage_Button"));
		} else if (!ActivityType.contains("HomePageSignIN")) {

			safeType(driver, getObjectLocals("LocalCom_ItineraryPage_EmailID"),
					campLocal.value("bookingEmailId"));

		}
		scrollToElement(driver,
				getObjectLocals("LocalCom_ItineraryPage_Button"));
		safeClick(driver, getObjectLocals("LocalCom_ItineraryPage_Button"));
		Thread.sleep(7000);
		if (wait.until(ExpectedConditions.invisibilityOf(driver
				.findElement(getObjectLocals("LocalCom_ItineraryPage_Button")))) == false) {
			Reporter.log("Unable to proceed Make Payment", true);
			Assert.fail();
		}
		// isElementVisible(driver,getObjectLocals("LocalCom_ItineraryPage_Button"));
		/*
		 * if(ActivityType.contains("Free")){ TripID =
		 * locals_Payment_ConfirmationPage(driver, "", "Free"); }
		 */

		return cashBack;
	}

	public void locals_PaymentPage(RemoteWebDriver driver, String PaymentType)
			throws Exception {
		if (!elementVisible(driver,
				getObjectLocals("LocalCom_PaymentPage_Button"), 10)) {
			Reporter.log("Payment Page is not displayed", true);
			Assert.assertTrue(false);
		}
		if (PaymentType.isEmpty() || PaymentType.equalsIgnoreCase("CC")) {
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_CC_Tab"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_Number"),
					dataFile.value("MasterCard_Number"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Exp_Month"),
					dataFile.value("MasterCard_Exp_Month"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Exp_Year"),
					dataFile.value("MasterCard_Exp_Year"));
			safeType(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_BillName"),
					dataFile.value("MasterCard_HolderName"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_CVV"),
					dataFile.value("MasterCard_CVV"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,300)", "");
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
			IndiaHotels indhotels = new IndiaHotels();
			indhotels.hotelCom_PaymentPage_Authentication(driver, "");
			// safeClick(driver, getObject("Amexwebsubmit"));
		} else if (PaymentType.equalsIgnoreCase("DC")) {
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_DC_Tab"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_DC_Number"),
					dataFile.value("MasterCard_Number"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_DC_Exp_Month"),
					dataFile.value("MasterCard_Exp_Month"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_DC_Exp_Year"),
					dataFile.value("MasterCard_Exp_Year"));
			safeType(driver,
					getObjectLocals("LocalCom_PaymentPage_DC_BillName"),
					dataFile.value("MasterCard_HolderName"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_DC_CVV"),
					dataFile.value("MasterCard_CVV"));
		} else if (PaymentType.equalsIgnoreCase("AmexCC")) {
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_CC_Tab"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_Number"),
					dataFile.value("AmexCard_Number"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Exp_Month"),
					dataFile.value("AmexCard_Exp_Month_Hotels"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Exp_Year"),
					dataFile.value("AmexCard_Exp_Year"));
			safeType(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_BillName"),
					dataFile.value("AmexCard_HolderName"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_CVV"),
					dataFile.value("AmexCard_CVV"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,300)", "");
			if (elementVisible(driver,
					getObjectHotels("HotelCom_BookStep4_Amex_Billing_Address"),
					10)) {
				safeType(
						driver,
						getObjectHotels("HotelCom_BookStep4_Amex_Billing_Address"),
						"JP Nagar");
				safeType(
						driver,
						getObjectHotels("HotelCom_BookStep4_Amex_Billing_City"),
						"Bangalore");
				safeType(
						driver,
						getObjectHotels("HotelCom_BookStep4_Amex_Billing_State"),
						"Karnataka");
				safeType(driver,
						getObjectHotels("HotelCom_BookStep4_Amex_Billing_Pin"),
						"560076");
				safeAutocomplete(
						driver,
						getObjectHotels("HotelCom_BookStep4_Amex_Billing_Country"),
						getObjectHotels("HotelCom_BookStep4_Amex_Billing_Country_Ajax"),
						"India");
			}
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
			Thread.sleep(20000);
			safeClick(driver, getObject("Amexwebsubmit"));
		}

		else if (PaymentType.equalsIgnoreCase("SC")) {
			if (elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_StoredCard_Tab"), 5)) {
				Reporter.log("Stored Card tab is not didplayed", true);
				Assert.assertTrue(false);
			}

			safeClick(driver,
					getObjectLocals("LocalCom_PaymentPage_StoredCard_Tab"));
			safeType(driver, By.id(""), "123");
		} else if (PaymentType.equalsIgnoreCase("DA")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_DA_Tab"), 5)) {
				Reporter.log("Deposit account tab is not didplayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_DA_Tab"));
			elementAssert(driver,
					getObjectLocals("LocalCom_PaymentPage_DA_BalanceText"), 5);
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
		}

		else if (PaymentType.equalsIgnoreCase("Wallet")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_Tab"), 5)) {
				Reporter.log("Wallet tab is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_Tab"));
			elementAssert(driver,
					getObjectLocals("LocalCom_PaymentPage_CTWallet_Amount"), 5);
			if (!getText(driver,
					getObjectLocals("LocalCom_PaymentPage_CTWallet_Amount"))
					.contains("Balance")) {
				Reporter.log("CT wallet Balance amount is not displayed", true);
				Assert.assertTrue(false);
			}
		}

		// suresh
		else if (PaymentType.equalsIgnoreCase("FullWallet")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_Tab"), 5)) {
				Reporter.log("Wallet tab is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_Tab"));
			// elementAssert(driver,
			// getObjectLocals("LocalCom_PaymentPage_CTWallet_Amount"), 5);
			/*
			 * if(!getText(driver,
			 * getObjectLocals("LocalCom_PaymentPage_CTWallet_Amount")).contains
			 * ("Balance")) {
			 * Reporter.log("CT wallet Balance amount is not displayed");
			 * Assert.assertTrue(false); }
			 */
			safeClick(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_CTWallet"));
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));

		}

		// suresh

		else if (PaymentType.equalsIgnoreCase("Paytm")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_Tab"), 5)) {
				Reporter.log("Wallet tab is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_Tab"));
			safeClick(driver, getObjectLocals("LocalCom_Paymentpage_Paytm"));
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
			Thread.sleep(3000);
			safeClick(driver, getObjectLocals("LocalCom_Webview_Paytm_cancel"));
			String actText = getText(driver,
					getObjectLocals("LocalCom_Payment_Retry_msg"));
			String expText = "Oops, your payment didn’t work";
			if (actText.equalsIgnoreCase(expText)) {
				System.out.println("Test case passed");
			}

			/*
			 * elementAssert(driver,
			 * getObjectLocals("LocalCom_PaymentPage_CTWallet_Amount"), 5);
			 * if(!getText(driver,
			 * getObjectLocals("LocalCom_PaymentPage_CTWallet_Amount")).contains
			 * ("Balance")) {
			 * Reporter.log("CT wallet Balance amount is not displayed");
			 * Assert.assertTrue(false); }
			 */
		}

		else if (PaymentType.equalsIgnoreCase("Jio")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_Tab"), 5)) {
				Reporter.log("Wallet tab is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_Tab"));
			safeClick(driver, getObjectLocals("LocalCom_Paymentpage_Jio"));
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
			Thread.sleep(5000);
			safeClick(driver, getObjectLocals("LocalCom_Webview_Jio_cancel"));

			Thread.sleep(2000);
			safeClick(driver,
					getObjectLocals("LocalCom_Webview_Jio_cancel_confirm"));
			String actText = getText(driver,
					getObjectLocals("LocalCom_Payment_Retry_msg"));
			String expText = "Oops, your payment didn’t work";
			if (actText.equalsIgnoreCase(expText)) {
				System.out.println("Test case passed");
			}

		}
		// suresh
		else if (PaymentType.equalsIgnoreCase("Freecharge")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_Tab"), 5)) {
				Reporter.log("Wallet tab is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver,
					getObjectLocals("LocalCom_PaymentPage_Wallets_Tab"));
			safeClick(driver,
					getObjectLocals("LocalCom_Paymentpage_Freecharge"));
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
			elementVisible(driver, By.id("fcLogo"), 15);
			String title = driver.getTitle();
			Assert.assertEquals(title, "Pay with Freecharge");
			/*
			 * safeClick(driver,
			 * getObjectLocals("LocalCom_Webview_Freecharge_cancel"));
			 * Thread.sleep(2000); String
			 * actText=getText(driver,getObjectLocals(
			 * "LocalCom_Payment_Retry_msg")); String
			 * expText="Oops, your payment didn’t work";
			 */
			/*
			 * if(actText.equalsIgnoreCase(expText)) {
			 * System.out.println("Test case passed"); }
			 */

		}

		else if (PaymentType.equalsIgnoreCase("NBRetry")) {
			elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Button"), 10);
			if (elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_NB_Tab"), 1)) {
				Reporter.log("NB option is not displayed", true);
				// Assert.assertTrue(false);
			}
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_NB_Tab"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_NB_DropDown"),
					"Bank of India");
			if (MakePaymentOnlyInQA2) {
				safeClick(driver,
						getObjectLocals("LocalCom_PaymentPage_Button"));
				for (int i = 0; i < 5; i++) {
					if (textPresent(driver, "Sorry, our system is acting up.",
							1)) {
						Reporter.log(
								"Sorry, our system is acting up. : message is not displayed in NB",
								true);
						Assert.assertTrue(false);
					} else if (textPresent(
							driver,
							"Sorry, we can not process your payment at this time.",
							1)) {
						Reporter.log(
								"Sorry, we can not process your payment at this time. : message is not displayed",
								true);
						Assert.assertTrue(false);
					} else if (elementVisible(
							driver,
							getObjectLocals("LocalCom_PaymentPage_BOI_Back_Button"),
							1, "NB page is not displayed")) {
						break;
					}
				}
				elementVisible(
						driver,
						getObjectLocals("LocalCom_PaymentPage_BOI_Back_Button"),
						30, "NB page is not displayed");
				safeClick(driver,
						getObjectLocals("LocalCom_PaymentPage_BOI_Back_Button"));
				if (!textPresent(driver, "Oops, your payment didn’t work", 10)) {
					Reporter.log(
							"Oops, your payment didn’t work : message is not displayed",
							true);
					Assert.assertTrue(false);
				}
				elementVisible(driver,
						getObjectLocals("LocalCom_PaymentPage_Button"), 20);
			}
		}

		else if (PaymentType.equalsIgnoreCase("NBRetryAE")) {
			elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Button"), 10);
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_NB_Tab"));
			safeClick(driver, By.id("adcb_bank"));
			// safeSelect(driver,
			// getObjectLocals("LocalCom_PaymentPage_NB_DropDown"), "ADCB
			// Bank");
			if (MakePaymentOnlyInQA2) {
				safeClick(driver,
						getObjectLocals("LocalCom_PaymentPage_Button"));
				for (int i = 0; i < 5; i++) {
					if (textPresent(driver, "Sorry, our system is acting up.",
							1)) {
						Reporter.log(
								"Sorry, our system is acting up. : message is not displayed",
								true);
						Assert.assertTrue(false);
					} else if (textPresent(
							driver,
							"Sorry, we can not process your payment at this time.",
							1)) {
						Reporter.log(
								"Sorry, we can not process your payment at this time. : message is not displayed",
								true);
						Assert.assertTrue(false);
					}

					else if (textPresent(driver,
							"Oops, your payment didn’t work", 1)) {
						Reporter.log(
								"Oops, your payment didn’t work : message is not displayed",
								true);
						Assert.assertTrue(false);
					}
					if (elementVisible(driver, By.id("Merchant_Logo"), 1)) {
						break;
					}
				}
				driver.manage().timeouts()
						.pageLoadTimeout(30, TimeUnit.SECONDS);
				if (elementVisible(driver, By.id("Merchant_Logo"), 60)) {
					elementVisible(driver,
							By.xpath("//div[@id='Message']/input"), 20);
				} else {
					Reporter.log("ADCB bank site page is not displayed", true);
					Assert.assertTrue(false);
				}
				textPresent(driver, "Cleartrip", 20);
				Thread.sleep(5000);
				safeClick(driver, By.xpath("//input[@value='Pay']"));
				textPresent(driver, "ADCB Personal Internet Banking", 20);
				driver.switchTo().frame("bottom_frame");
				elementVisible(driver, By.id("cidnumber"), 30);
				Reporter.log("ADCB Bank page is displayed", true);
			}
		} else if (MakePaymentOnlyInQA2
				&& !PaymentType.equalsIgnoreCase("NBRetry")
				&& !PaymentType.equalsIgnoreCase("NBRetryAE")) {
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
			if (elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Error"), 5)) {
				Reporter.log(
						"Sorry, we can not process your payment at this time. Please try again after some time.",
						true);
				Assert.assertTrue(false);
			}
			if (PaymentType.equalsIgnoreCase("AmexCC")) {
				if (!elementVisible(
						driver,
						getObjectLocals("LocalCom_PaymentPage_AmexCard_Authentication"),
						60, "")) {
					Reporter.log("Amex Autentication is not displayed", true);
					Assert.assertTrue(false);
				}
				safeClick(
						driver,
						getObjectLocals("LocalCom_PaymentPage_AmexCard_WebPage_Submit"));
			}
		}
	}

	public String locals_Payment_ConfirmationPage_MobileWeb(
			RemoteWebDriver driver, String Trip_Logger_Msg, String PaymentType)
			throws Exception, InterruptedException {
		String TripID = null;
		if (MakePaymentOnlyInQA2) {
			for (int i = 0; i <= 30; i++) {
				if (textPresent(driver, "Your booking is done", 1)) {
					break;
				} else if (textPresent(driver,
						"Sorry, our system is acting up", 1)) {
					Reporter.log(
							"Sorry, our system is acting up. : error message is displayed in Paymenty Confirmation Page",
							true);
					Assert.assertTrue(false);
				}
			}
			if (!elementVisible(
					driver,
					getObjectLocals("LocalCom_MobileWeb_ConfirmationPage_Button"),
					10)) {
				Reporter.log("TripID is not displayed", true);
				Assert.assertTrue(false);
			}
			TripID = getText(
					driver,
					getObjectLocals("LocalCom_MobileWeb_ConfirmationPage_Button"));
			textAssert(driver, "Your booking is done", 5);
			logURL(driver);
			Reporter.log(Trip_Logger_Msg + TripID, true);
			// logger.info(Trip_Logger_Msg + TripID);
		}
		return TripID;
	}

	public String locals_Payment_ConfirmationPage(RemoteWebDriver driver,
			String Trip_Logger_Msg, String PaymentType) throws Exception,
			InterruptedException {
		String TripID = null, PaxDetails = "Mr. Test Test", EmailId = campLocal
				.value("bookingEmailId"), MobNo = dataFile
				.value("LocalsPhoneNO");
		String confPax, confMob, confEmail;
		if (MakePaymentOnlyInQA2) {
			for (int i = 0; i <= 30; i++) {
				if (textPresent(driver, "Your booking is done", 1)) {
					break;
				} else if (textPresent(driver,
						"Sorry, our system is acting up", 1)) {
					Reporter.log(
							"Sorry, our system is acting up. : error message is displayed in Payment Confirmation page",
							true);
					Assert.assertTrue(false);
				}
			}
			if (!elementVisible(driver,
					getObjectLocals("LocalCom_ConfirmationPage_Button"), 5)) {
				Reporter.log("TripID is not displayed", true);
				Assert.assertTrue(false);
			}
			TripID = getText(driver,
					getObjectLocals("LocalCom_ConfirmationPage_Button"));
			textAssert(driver, "Your booking is done", 5);
			logURL(driver);
			Reporter.log("Confirmation Page is displayed", true);
			Reporter.log(Trip_Logger_Msg + TripID);
			logger.info(Trip_Logger_Msg + TripID);

			confPax = getText(driver,
					getObjectLocals("LocalConfirmationPaxName"));
			confMob = getText(driver,
					getObjectLocals("LocalConfirmationMobNumber"));
			confEmail = getText(driver,
					getObjectLocals("LocalConfirmationEmailId"));
			Assert.assertTrue(PaxDetails.equalsIgnoreCase(confPax),
					"Contact details are not matching");
			Reporter.log("Contact details are matching", true);
			Assert.assertTrue(EmailId.equalsIgnoreCase(confEmail),
					"Email id not matching");
			Reporter.log("Email id matching", true);
			Assert.assertTrue(MobNo.equalsIgnoreCase(confMob),
					"Mob number not matching");
			Reporter.log("Mob number matching", true);
		}
		return TripID;
	}

	public void locals_NameSearch_MobileWeb_Events(RemoteWebDriver driver,
			String EventType, String EventName) throws Exception {
		/*
		 * elementVisible(driver, By.xpath("//div[2]/a/b"), 20);
		 * safeClickList(driver, By.xpath("//div[2]/a/b"), EventType);
		 */
		// locals_MobileWeb_Change_Location(driver);
		logMessagePageNotLoaded(driver,
				getObjectLocals("LocalMobileWeb_MenuBtn"), 10,
				"Menu Btn not displayed");
		safeClick(driver, getObjectLocals("LocalMobileWeb_MenuBtn"));

		safeClickList(driver, getObjectLocals("LocalMobileWeb_ActivityList"),
				"Events");
		/*
		 * safeClickList(driver, getObjectLocals("LocalMobileWeb_ActivityList"),
		 * "Events"); safeClickList(driver,
		 * getObjectLocals("LocalMobileWeb_Activity_HomePage_ActivityList"),
		 * EventName);
		 */
		if (EventType.equalsIgnoreCase("Caraousal")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalMobileWeb_Caraousal"), 20)) {
				Reporter.log("Caraousal is not displayed", true);
				Assert.assertTrue(false);
			}
			Thread.sleep(2000);
			safeClick(driver, getObjectLocals("LocalMobileWeb_Caraousal"));
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_ProductEditorial_List"),
					EventName);

		} else if (EventType.equalsIgnoreCase("ProductEditorial")) {
			if (!elementVisible(
					driver,
					getObjectLocals("LocalMobileWeb_Events_HomePage_ProductEditorial"),
					20)) {
				Reporter.log("Prod Editorial is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(
					driver,
					getObjectLocals("LocalMobileWeb_Events_HomePage_ProductEditorial"));
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_ProductEditorial_List"),
					EventName);
		} else {
			if (!elementVisible(driver,
					By.xpath("//h2[@class='metaName truncate']"), 20)) {
				Reporter.log("List1 is not displayed", true);
				Assert.assertTrue(false);
			}
			// safeClickList(driver,
			// getObjectLocals("LocalMobileWeb_Group_LinkList2"),
			// EventName);
			if (EventName == dataFile.value("Locals_Data_Events_MeraFree")) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,300)", "");
			}
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Activity_LinkList"),
					EventName);
		}

	}

	public void locals_NameSearch_TTD_MobileWeb(RemoteWebDriver driver,
			String TTDType, String TTDName) throws Exception {

		if (TTDType.equalsIgnoreCase("Caraousal")) {
			logMessagePageNotLoaded(driver,
					getObjectLocals("LocalMobileWeb_Caraousal"), 10,
					"Caraousal is not displayed");
			safeClick(driver, getObjectLocals("LocalMobileWeb_Caraousal"));
		} else if (TTDType.equalsIgnoreCase("ProductEditorial")) {
			logMessagePageNotLoaded(driver,
					getObjectLocals("LocalMobileWeb_ProductEditorial_Link"),
					10, "Prod Editorial Link is not displayed");
			safeClick(driver,
					getObjectLocals("LocalMobileWeb_ProductEditorial_Link"));
		} else {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,200)", "");
			safeClick(
					driver,
					By.xpath((getXpathByReplace(objectReposLocals
							.value("LocalMobileWeb_Activity_LinkList"), TTDType))));
			// safeClickList(driver,
			// getObjectLocals("LocalMobileWeb_Activity_LinkList"),
			// TTDType);
		}
		Thread.sleep(3000);
		// driver.navigate().refresh();
		wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By
				.xpath("//span[@id='contentLoading']"))));
		if (elementVisible(driver,
				getObjectLocals("LocalMobileWeb_Group_LinkList2"), 5)) {
			Reporter.log("Activity Name " + TTDName, true);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,300)", "");
			// if(TTDName==Test without timeslot unscheduled)
			if (TTDName == dataFile
					.value("Locals_Data_Activity_Adult_TimeSlot_Unavailable")) {
				// JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,450)", "");
				// scrollToElement(driver, By.xpath("//a[text()='Test without
				// timeslot
				// unscheduled']"));
			}
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"), TTDName);
			Thread.sleep(3000);
			// driver.navigate().refresh();
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By
					.xpath("//span[@id='contentLoading']"))));
		}
	}

	public void locals_NameSearch_FNB_MobileWeb(RemoteWebDriver driver,
			String EventType, String EventName) throws Exception {
		logMessagePageNotLoaded(driver,
				getObjectLocals("LocalMobileWeb_MenuBtn"), 10,
				"Menu btn is not displayed");
		safeClick(driver, getObjectLocals("LocalMobileWeb_MenuBtn"));
		safeClickList(driver, getObjectLocals("LocalMobileWeb_ActivityList"),
				"Eat Out");
		logURL(driver);
		if (EventType.equalsIgnoreCase("Carousal")) {
			logMessagePageNotLoaded(driver, By.xpath("//div/div/div/div/h1"),
					10, "Caraousal is not displayed");

			safeClick(driver, By.xpath("//div/div/div/div/h1"));
		} else if (EventType.equalsIgnoreCase("ProductEditorial")) {
			logMessagePageNotLoaded(driver, By.xpath("//a/div"), 10,
					"Prod Editorial is not displayed");

			safeClick(driver, By.xpath("//a/div"));
		} else {
			scrollToElement(driver, By.xpath("//li/a/div/div/h2"));
			elementVisible(driver, By.xpath("//li[1]/a/div/div/h2"), 10);
			if (EventType == dataFile.value("Locals_Data_FNB_AdultChild_Group")) {
				safeClick(driver, By.xpath("(//li[1]/a/div/div/h2)[2]"));
			} else {
				safeClickList(driver, By.xpath("//li/a/div/div/h2"), EventType);
			}

		}
		wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By
				.xpath("//span[@id='contentLoading']"))));
		if (elementVisible(driver,
				getObjectLocals("LocalMobileWeb_Group_LinkList2"), 5)) {

			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"),
					EventName);
		}
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By
				.xpath("//span[@id='contentLoading']"))));
	}

	public void locals_BookFlow_MobileWeb(RemoteWebDriver driver,
			String ActivityType, String BookType) throws Exception {
		String adultCount = null;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String Date = putDate(7);
		if (Date.startsWith("0")) {
			Date = Date.substring(1);
		}
		logMessagePageNotLoaded(
				driver,
				getObjectLocals("LocalMobileWeb_Activity_DetailsPage_BookBtnNEW"),
				10, "Details page Book Btn is not displayed");
		scrollToElement(
				driver,
				getObjectLocals("LocalMobileWeb_Activity_DetailsPage_BookBtnNEW"));
		safeClick(
				driver,
				getObjectLocals("LocalMobileWeb_Activity_DetailsPage_BookBtnNEW"));

		if (ActivityType.contains("TTD")) {
			scrollToElement(
					driver,
					getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButtonNEW"));
			logMessagePageNotLoaded(
					driver,
					getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButtonNEW"),
					20, "Book page popup Btn is not displayed");
			logURL(driver);
			// safeClick(driver,By.id("options"));
			// Select packagedropdown = new Select(driver.findElement(By.id("options")));
			// Select(driver.findElement(By.id("options")));
			// packagedropdown.selectByVisibleText("5:30 Promotion Sail");
			safeClick(driver,
					getObjectLocals("Local_MobileWeb_BookPage_Calendar_Image1"));
			/*
			 * if(elementNotVisible(driver, By.linkText(Date), 5)){
			 * safeClick(driver, By.cssSelector("a.nextMonth.")); }
			 * safeClick(driver, By.linkText(Date));
			 */
			locals_MobileWeb_SelectDate_enabled(driver, Date);
			if (BookType.equalsIgnoreCase("Adult")) {
				driver.manage().window().fullscreen();
				elementVisible(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						10);
				String aC = driver.findElement(By.id("adult")).getAttribute(
						"value");
				int adtCount = Integer.parseInt(aC);
				if (adtCount == 0) {
					driver.findElement(By.name("quantity")).click();
				}
				wait.until(ExpectedConditions.elementToBeClickable(driver
						.findElement(By
								.xpath("//button[contains(@class,'ttdBook')]"))));
				// safeSelect(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				// "1");
			} else if (BookType.equalsIgnoreCase("AdultChild")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				/*
				 * safeSelect(driver,
				 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				 * "1"); safeSelect(driver,
				 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
				 * "1");
				 */
			} else if (BookType.equalsIgnoreCase("AdultTime")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				// safeSelect(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				// "2");
				safeSelectByIndex(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_TimeSlot"),
						2);
			}

			else if (BookType.contains("Kids")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
						20, "Child Dropdown is not displayed");
				// safeSelect(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
				// "2");
				for (int i = 0; i < 3; i++) {
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].click();",
							driver.findElement(By.xpath("//a[@value='+']")));
					// safeClick(driver, By.xpath("//a[@value='+']"));
				}
			}

			else if (BookType.contains("MultiRate")) {
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Options"),
						"boating2");
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				for (int i = 1; i < 2; i++) {
					/*
					 * ((JavascriptExecutor)
					 * driver).executeScript("arguments[0].click();",
					 * driver.findElement(By.xpath("//a[@value='+']")));
					 */
					safeClick(driver, By.xpath("(//a[@value='+'])[1]"));
				}
				// safeSelectByIndex(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				// 1);
				// safeSelectByIndex(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				// 2);
			} else if (BookType.contains("Caraousal")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				// safeSelectByIndex(driver,
				// getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
				// 1);
				for (int i = 0; i <= 5; i++) {
					safeClick(driver, By.xpath("(//input[@value='+'])[1]"));
				}
			}

			driver.findElement(By.xpath("//button[contains(@class,'ttdBook')]"))
					.click();
			/*
			 * smartClick(driver,
			 * By.xpath("//button[contains(@class,'ttdBook')]"));
			 * ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].click();",
			 * driver.findElement(By.xpath(
			 * "//button[contains(@class,'ttdBook')]")));
			 */
		}

		else if (ActivityType.contains("FNB")) {
			logMessagePageNotLoaded(driver,
					getObjectLocals("LocalCom_FNB_BookPage_Bookbutton"), 20,
					"Book page popup Btn is not displayed");
			logURL(driver);
			safeClick(driver,
					getObjectLocals("Local_MobileWeb_BookPage_Calendar_Image"));
			locals_MobileWeb_SelectDate_enabled(driver, Date);
			if (BookType.equalsIgnoreCase("Adult")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
			} else if (BookType.equalsIgnoreCase("AdultTime")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
				safeSelectByIndex(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_TimeSlot"),
						2);
			} else if (BookType.equalsIgnoreCase("AdultChild")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
						"1");
			} else if (BookType.equalsIgnoreCase("Couple")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
			} else if (BookType.equalsIgnoreCase("Group")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"2");
			}
			// safeClick(driver,
			// getObjectLocals("LocalCom_FNB_BookPage_Bookbutton"));
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].click();",
					driver.findElement(By.xpath("//div[2]/input")));
		}

		else if (ActivityType.equalsIgnoreCase("Events")) {
			logMessagePageNotLoaded(driver,
					getObjectLocals("LocalCom_Events_BookPage_Bookbutton"), 10,
					"Book PopUp not displayed");
			// locals_MobileWeb_SelectDate_Events(driver, 1);
			logURL(driver);
			if (BookType.isEmpty()) {

			} else if (BookType.equalsIgnoreCase("MeraFree")) {
				locals_MobileWeb_Event_PaymentType(driver, "free");
			}

			else if (BookType.equalsIgnoreCase("MeraPaid")) {
				locals_MobileWeb_Event_PaymentType(driver, "paid");
			} else if (BookType.equalsIgnoreCase("InsiderFree")) {
				locals_MobileWeb_Event_PaymentType(driver, "free");
			}

			else if (BookType.equalsIgnoreCase("InsiderPaid")) {
				locals_MobileWeb_Event_PaymentType(driver, "paid");
			} else if (BookType.equalsIgnoreCase("Carousal")) {
				elementVisible(driver,
						getObjectLocals("LocalCom_Events_PopUp_Add_Tickets"),
						20);
				safeClick(driver,
						getObjectLocals("LocalCom_Events_PopUp_Add_Tickets"));
			}
			safeClick(
					driver,
					getObjectLocals("LocalMobileWeb_Events_BookPage_Bookbutton"));
			if (elementVisible(driver,
					getObjectLocals("LocalCom_Events_Error"), 5)) {
				String Error = getText(driver,
						getObjectLocals("LocalCom_Events_Error"));
				Reporter.log(Error + " : Error message is displayed", true);
				Assert.assertTrue(false);
			}
		}
	}

	public void locals_MobileWeb_Event_PaymentType(RemoteWebDriver driver,
			String EventPayment) throws Exception {

		String Booking_Type = getText(driver, By.xpath("//dt/label"));
		if (EventPayment.equalsIgnoreCase("Free")) {
			if (!Booking_Type.contains("Free")) {
				Reporter.log("Free Booking is displayed as " + Booking_Type,
						true);
				Assert.assertTrue(false);
			}
		} else if (EventPayment.equalsIgnoreCase("paid")) {
			if (Booking_Type.contains("Free ticket")) {
				Reporter.log("Free Booking is displayed as " + Booking_Type,
						true);
				Assert.assertTrue(false);
			}
		}

	}

	// ==============================Unused============================================//

	public void locals_itineraryPage(RemoteWebDriver driver, String coupon)
			throws Exception {

		elementVisible(driver,
				getObjectLocals("LocalCom_ItineraryPage_Button"), 50);
		safeSelect(driver, getObjectLocals("LocalCom_ItineraryPage_Title"),
				dataFile.value("Title"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_FirstName"),
				dataFile.value("First_Name_A1"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_LastName"),
				dataFile.value("Last_Name_A1"));

		if (login == 0) {
			safeType(driver, getObjectLocals("LocalCom_ItineraryPage_EmailID"),
					dataFile.value("LocalsEmailID"));
		}

		// safeType(driver, getObjectLocals("LocalCom_ItineraryPage_EmailID"),
		// dataFile.value("LocalsEmailID"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_PhoneNo"),
				dataFile.value("LocalsPhoneNO"));
		if (coupon.equalsIgnoreCase("instant")) {
			safeType(driver, getObjectLocals("LocalCoupon"),
					dataFile.value("Localinstcoupon"));
			safeClick(driver, getObjectLocals("Localapply"));
			elementVisible(driver, getObjectLocals("LocalPromotiontext"), 10);
		} else if (coupon.equalsIgnoreCase("wallet")) {
			safeType(driver, getObjectLocals("LocalCoupon"),
					dataFile.value("Localwalcoupon"));
			safeClick(driver, getObjectLocals("Localapply"));
			elementVisible(driver, getObjectLocals("LocalPromotionwal"), 10);
		} else if (coupon.equalsIgnoreCase("walletplus")) {
			safeType(driver, getObjectLocals("LocalCoupon"),
					dataFile.value("Localwalpluscoupon"));
			safeClick(driver, getObjectLocals("Localapply"));
			elementVisible(driver, getObjectLocals("LocalPromotiontext"), 10);
		}
		// elementVisible(driver, getObjectLocals("LocalPromotionwal"),10);
		safeClick(driver, getObjectLocals("LocalCom_ItineraryPage_Button"));
	}

	// suresh CC amex payment
	public String localsPaymentCcAmex(RemoteWebDriver driver) throws Exception {

		elementVisible(driver, getObjectLocals("LocalCom_PaymentPage_Button"),
				10);

		safeClick(driver, getObjectLocals("LocalCom_PaymentPage_CC_Tab"));
		safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_Number"),
				dataFile.value("AmexCard_Numberr"));
		safeSelect(driver,
				getObjectLocals("LocalCom_PaymentPage_CC_Exp_Month"),
				dataFile.value("AmexCard_Exp_Month"));
		safeSelect(driver, getObjectLocals("LocalCom_PaymentPage_CC_Exp_Year"),
				dataFile.value("AmexCard_Exp_Year"));
		/*
		 * safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_BillName"),
		 * dataFile.value("AmexCard_HolderName")); safeType(driver,
		 * getObjectLocals("LocalCom_PaymentPage_CC_CVV"),
		 * dataFile.value("AmexCVV")); safeType(driver,
		 * getObject("Amexbilladdress"), dataFile.value("billaddress"));
		 * safeType(driver, getObject("Amexbillcity"),
		 * dataFile.value("billcity")); safeType(driver,
		 * getObject("Amexbillstate"), dataFile.value("billstate"));
		 * safeType(driver, getObject("Amexpin"), dataFile.value("billpin"));
		 * safeType(driver, getObject("Amexbillcountry"),
		 * dataFile.value("billcountry")); safeClick(driver,
		 * getObject("AmexbillCountryclick"));
		 */
		safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
		Thread.sleep(20000);
		safeClick(driver, getObject("Amexwebsubmit"));
		String TripID = locals_Payment_ConfirmationPage(driver, "", "CC");
		return TripID;
	}

	public String localsPaymentNbRetryCcMaster(RemoteWebDriver driver)
			throws Exception {

		elementVisible(driver, getObjectLocals("LocalCom_PaymentPage_Button"),
				10);
		safeClick(driver, getObjectLocals("LocalsComNB"));
		safeClick(driver, getObjectLocals("LocalsComSBI"));
		safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
		Thread.sleep(5000);
		safeClick(driver, getObjectLocals("LocalNBback"));

		safeClick(driver, getObjectLocals("LocalCom_PaymentPage_CC_Tab"));
		safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_Number"),
				dataFile.value("MasterCard_Number"));
		safeSelect(driver,
				getObjectLocals("LocalCom_PaymentPage_CC_Exp_Month"),
				dataFile.value("MasterCard_Exp_Month"));
		safeSelect(driver, getObjectLocals("LocalCom_PaymentPage_CC_Exp_Year"),
				dataFile.value("MasterCard_Exp_Year"));
		safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_BillName"),
				dataFile.value("MasterCard_HolderName"));
		safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_CVV"),
				dataFile.value("MasterCard_CVV"));
		safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
		elementVisible(driver, getObject("AcsEmulator"), 50);
		safeClick(driver, getObject("Amexwebsubmit"));

		String TripID = locals_Payment_ConfirmationPage(driver, "", "CC");
		return TripID;
	}

	public String localsPaymentCcMaster(RemoteWebDriver driver)
			throws Exception {
		safeClick(driver, getObjectLocals("LocalCom_PaymentPage_CC_Tab"));
		safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_Number"),
				dataFile.value("MasterCard_Number"));
		safeSelect(driver,
				getObjectLocals("LocalCom_PaymentPage_CC_Exp_Month"),
				dataFile.value("MasterCard_Exp_Month"));
		safeSelect(driver, getObjectLocals("LocalCom_PaymentPage_CC_Exp_Year"),
				dataFile.value("MasterCard_Exp_Year"));
		safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_BillName"),
				dataFile.value("MasterCard_HolderName"));
		safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_CVV"),
				dataFile.value("MasterCard_CVV"));
		safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
		Thread.sleep(20000);
		String TripID = locals_Payment_ConfirmationPage(driver, "", "CC");
		return TripID;
	}

	public void locals_NameSearch_FNBOLD(RemoteWebDriver driver,
			String FNBType, String FNBName) throws Exception {
		safeClickList(driver,
				getObjectLocals("LocalCom_HomePage_Activity_Tab"), "Eat Out");
		if (FNBType.equalsIgnoreCase("Carousal")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalMobileWeb_FNB_HomePage_Caraousal"),
					20)) {
				Reporter.log("Caraousal is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver,
					getObjectLocals("LocalMobileWeb_FNB_HomePage_Caraousal"));
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"), FNBName);
		} else if (FNBType.equalsIgnoreCase("ProductEditorial")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalMobileWeb_ProductEditorial_Link"), 20)) {
				Reporter.log("Prod Editorial is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver,
					getObjectLocals("LocalMobileWeb_ProductEditorial_Link"));
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"), FNBName);
		} else {
			if (!elementVisible(driver, By.xpath("//li[1]/a/div/div/h2"), 20)) {
				Reporter.log("List1 is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_FNB_LinkList"), FNBType);
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"), FNBName);
		}
		safeClick(driver,
				getObjectLocals("LocalCom_Activity_BookPage_Bookbutton"));
	}

	public void locals_TTD_NameSearchOLD(RemoteWebDriver driver,
			String TTDType, String TTDName) throws Exception {
		if (TTDType.equalsIgnoreCase("Caraousal")) {
			if (!elementVisible(driver,
					getObjectLocals("LocalMobileWeb_TTD_HomePage_Caraousal"),
					20)) {
				Reporter.log("Caraousal is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver,
					getObjectLocals("LocalMobileWeb_TTD_HomePage_Caraousal"));
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"), TTDName);
		} else if (TTDType.equalsIgnoreCase("ProductEditorial")) {
			if (!elementVisible(driver, By.xpath("//li/a/div/div/h2"), 20)) {
				Reporter.log("Prod Editorial is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClick(driver, By.xpath("//li/a/div/div/h2"));
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"), TTDName);
		} else {
			if (!elementVisible(driver,
					By.xpath("//div[5]/nav/ul/li/a/div/div/h2"), 20)) {
				Reporter.log("List1 is not displayed", true);
				Assert.assertTrue(false);
			}
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_TTD_HomePage_LinkList"),
					TTDType);
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"), TTDName);
		}
		safeClick(driver,
				getObjectLocals("LocalCom_Activity_BookPage_Bookbutton"));
	}

	/*
	 * public void locals_BookPopUPOLD(RemoteWebDriver driver, String
	 * ActivityType, String BookingType) throws Exception { String Date =
	 * putDate(7); if (Date.startsWith("0")) { Date = Date.substring(1); }
	 * Thread.sleep(5000); logURL(driver); if
	 * (ActivityType.equalsIgnoreCase("Events")) {
	 * logMessagePageNotLoaded(driver,
	 * getObjectLocals("LocalCom_Events_PopUp_Button"), 10,
	 * "Book PopUp not displayed"); if (BookingType.isEmpty()) {
	 * 
	 * } else if (BookingType.equalsIgnoreCase("MeraFree")) {
	 * 
	 * } else if (BookingType.equalsIgnoreCase("MeraPaid")) {
	 * 
	 * } else if (BookingType.equalsIgnoreCase("InsiderFree")) {
	 * List<WebElement> BookType = driver.findElements(By.xpath("//dt")); for
	 * (int i = 0; i <= BookType.size() - 1; i++) { String BookTypes =
	 * BookType.get(i).getText(); if (!BookTypes.isEmpty()) {
	 * Reporter.log("BookTypes : " + BookTypes,true); } if
	 * (BookTypes.contains("Free")) { int j = i + 1; String PlusXpath =
	 * "//section[" + j + "]/div/dl/dd/input[3]"; safeClick(driver,
	 * By.xpath(PlusXpath)); break; } else if (i == BookType.size() - 1) {
	 * Reporter.log("Free text is not displayed in list",true);
	 * Assert.assertTrue(false); } } }
	 * 
	 * else if (BookingType.equalsIgnoreCase("InsiderPaid")) {
	 * 
	 * List<WebElement> BookType = driver.findElements(By.xpath("//dt")); for
	 * (int i = 0; i <= BookType.size() - 1; i++) { String BookTypes =
	 * BookType.get(i).getText(); if (!BookTypes.isEmpty()) {
	 * Reporter.log("BookTypes : " + BookTypes,true); } if
	 * (!BookTypes.contains("Free")) { int j = i + 1; String PlusXpath =
	 * "//section[" + j + "]/div/dl/dd/input[3]"; safeClick(driver,
	 * By.xpath(PlusXpath)); break; } else if (i == BookType.size() - 1) {
	 * Reporter.log("Free text is not displayed in list",true);
	 * Assert.assertTrue(false); } } } else if
	 * (BookingType.equalsIgnoreCase("Carousal")) { elementVisible(driver,
	 * getObjectLocals("LocalCom_Events_PopUp_Add_Tickets"), 20);
	 * safeClick(driver, getObjectLocals("LocalCom_Events_PopUp_Add_Tickets"));
	 * } safeClick(driver, getObjectLocals("LocalCom_Events_PopUp_Button")); if
	 * (elementVisible(driver, getObjectLocals("LocalCom_Events_Error"), 5)) {
	 * String Error = getText(driver, getObjectLocals("LocalCom_Events_Error"));
	 * Reporter.log("Error message is displayed : " + Error,true);
	 * Assert.assertTrue(false); } } else if
	 * (ActivityType.equalsIgnoreCase("FNB")) { logMessagePageNotLoaded(driver,
	 * getObjectLocals("LocalCom_FNB_BookPage_Bookbutton"), 10,
	 * "Book PopUp not displayed"); safeClick(driver, By.xpath("//dd/div/a/i"));
	 * safeClick(driver, By.linkText(Date)); if
	 * (BookingType.equalsIgnoreCase("Adult")) { safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"), "1"); } else
	 * if (BookingType.equalsIgnoreCase("AdultNew")) { safeClick(driver,
	 * By.xpath("//span/a/i")); safeClick(driver, By.linkText(Date));
	 * safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"), "1");
	 * safeClick(driver, By.xpath("//button")); } else if
	 * (BookingType.equalsIgnoreCase("AdultChild")) { safeSelectByIndex(driver,
	 * By.id("options"), 1); safeClick(driver, By.xpath("//dd/div/a/i"));
	 * safeClick(driver, By.linkText(Date)); safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"), "1");
	 * safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"), "1"); } else
	 * if (BookingType.equalsIgnoreCase("AdultTime")) { safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_TimeSlot"), "2");
	 * safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"), "2"); } else
	 * if (BookingType.equalsIgnoreCase("Couple")) { safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"), "1"); } else
	 * if (BookingType.equalsIgnoreCase("Group")) { safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"), "2"); } else
	 * if (BookingType.equalsIgnoreCase("Caraousal")) { } safeClick(driver,
	 * getObjectLocals("LocalCom_FNB_BookPage_Bookbutton")); }
	 * 
	 * else if (ActivityType.equalsIgnoreCase("TTD")) {
	 * 
	 * logMessagePageNotLoaded(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButton"), 10,
	 * "Book PopUp not displayed");
	 * 
	 * safeClick(driver, By.xpath("//dd/div/a/i")); safeClick(driver,
	 * By.linkText(Date)); if (BookingType.equalsIgnoreCase("Adult")) {
	 * safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"), "1"); }
	 * 
	 * else if (BookingType.equalsIgnoreCase("AdultChild")) { safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"), "1");
	 * safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"), "1"); }
	 * 
	 * else if (BookingType.equalsIgnoreCase("Kids")) { safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"), "1"); } else
	 * if (BookingType.equalsIgnoreCase("MultiRate")) {
	 * safeSelectByIndex(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_TimeSlot"), 2);
	 * safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"), "1"); } else
	 * if (BookingType.equalsIgnoreCase("Group")) { safeSelect(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"), "2"); } else
	 * if (BookingType.equalsIgnoreCase("Caraousal")) {
	 * 
	 * } safeClick(driver,
	 * getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButton"));
	 * 
	 * } }
	 */

	public void locals_MobileWeb_FNB_NameSearchNEW(RemoteWebDriver driver,
			String EventType, String EventName) throws Exception {
		/*
		 * elementVisible(driver, By.xpath("//div[2]/a/b"), 20);
		 * safeClickList(driver, By.xpath("//div[2]/a/b"), "Local");
		 */
		// locals_MobileWeb_Change_Location(driver);
		logMessagePageNotLoaded(driver,
				getObjectLocals("LocalMobileWeb_MenuBtn"), 10,
				"Menu btn is not displayed");
		safeClick(driver, getObjectLocals("LocalMobileWeb_MenuBtn"));
		safeClickList(driver, getObjectLocals("LocalMobileWeb_ActivityList"),
				"Eat Out");
		if (EventType.equalsIgnoreCase("Carousal")) {
			logMessagePageNotLoaded(driver, By.xpath("//div/div/div/div/h1"),
					10, "Caraousal is not displayed");

			safeClick(driver, By.xpath("//div/div/div/div/h1"));

		} else if (EventType.equalsIgnoreCase("ProductEditorial")) {
			logMessagePageNotLoaded(driver, By.xpath("//a/div"), 10,
					"Prod Editorial is not displayed");

			safeClick(driver, By.xpath("//a/div"));
		} else {
			elementVisible(driver, By.xpath("//li[1]/a/div/div/h2"), 10);
			safeClickList(driver, By.xpath("//li/a/div/div/h2"), EventType);
		}
		if (elementVisible(driver,
				getObjectLocals("LocalMobileWeb_Group_LinkList2"), 5)) {
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_Group_LinkList2"),
					EventName);
		}
	}

	public void locals_Change_Location_MobileWebOLD(RemoteWebDriver driver)
			throws Exception {
		logMessagePageNotLoaded(driver,
				getObjectLocals("LocalMobileWeb_ChangeCity_Link"), 10,
				"Change City link is not displayed");
		safeClick(driver, getObjectLocals("LocalMobileWeb_ChangeCity_Link"));
		driver.switchTo().frame("modal_window");
		Thread.sleep(1000);
		safeAutocomplete(
				driver,
				getObjectLocals("LocalMobileWeb_ChangeCity_Localities_TextBox"),
				getObjectLocals("LocalMobileWeb_ChangeCity_Localities_Ajax"),
				dataFile.value("Change_City"));
		// safeClick(driver, By.xpath("//button[2]"));

	}

	public void locals_BookFlow_MobileWebOLD(RemoteWebDriver driver,
			String ActivityType, String BookType) throws Exception {
		String Date = putDate(7);
		if (Date.startsWith("0")) {
			Date = Date.substring(1);
		}

		logMessagePageNotLoaded(driver,
				getObjectLocals("LocalMobileWeb_Activity_DetailsPage_BookBtn"),
				20, "Details page Book Btnis not displayed");
		safeClick(driver,
				getObjectLocals("LocalMobileWeb_Activity_DetailsPage_BookBtn"));

		if (ActivityType.contains("TTD")) {
			logMessagePageNotLoaded(
					driver,
					getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButton"),
					20, "Book page popup Btn is not displayed");
			Thread.sleep(5000);
			safeClick(driver,
					getObjectLocals("Local_MobileWeb_BookPage_Calendar_Image"));
			// safeClick(driver, By.xpath("//a/img"));
			safeClick(driver, By.linkText(Date));

			if (BookType.equalsIgnoreCase("Adult")) {
				/*
				 * mouseHoverClick(driver, By.xpath("//img[@alt='Calendar']"));
				 * safeClick(driver, By.id("bookingDate")); safeClick(driver,
				 * By.xpath("//img[@alt='Calendar']"));
				 */

				elementVisible(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						10);
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
			} else if (BookType.equalsIgnoreCase("AdultChild")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");

				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
						"1");
			}

			else if (BookType.equalsIgnoreCase("AdultTime")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"2");
				safeSelectByIndex(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_TimeSlot"),
						2);
			}

			else if (BookType.contains("Kids")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
						20, "Child Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
						"1");
			}

			else if (BookType.contains("MultiRate")) {
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Options"),
						"boating1");
				safeClick(driver, By.linkText("30"));
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"2");
			} else if (BookType.contains("Caraousal")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
			}
			safeClick(
					driver,
					getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButton"));
		}

		else if (ActivityType.contains("FNB")) {
			elementVisible(driver,
					getObjectLocals("LocalCom_FNB_BookPage_Bookbutton"), 10);
			safeClick(driver,
					getObjectLocals("Local_MobileWeb_BookPage_Calendar_Image"));
			safeClick(driver, By.linkText(Date));
			if (BookType.equalsIgnoreCase("Adult")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
			} else if (BookType.equalsIgnoreCase("AdultTime")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
				safeSelectByIndex(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_TimeSlot"),
						2);
			} else if (BookType.equalsIgnoreCase("AdultChild")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Child"),
						"1");
			} else if (BookType.equalsIgnoreCase("Couple")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"1");
			} else if (BookType.equalsIgnoreCase("Group")) {
				logMessagePageNotLoaded(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						20, "Adult Dropdown is not displayed");
				safeSelect(
						driver,
						getObjectLocals("LocalCom_Activity_BookPage_PopUP_Adult"),
						"2");
			}
			safeClick(driver,
					getObjectLocals("LocalCom_FNB_BookPage_Bookbutton"));
		}

		else if (ActivityType.contains("Events")) {
			elementVisible(driver,
					By.xpath("//dd[@class='fRight controlContainer']/input"),
					20);
			List<WebElement> we = driver.findElements(By
					.xpath("//dd[@class='fRight controlContainer']/input"));
			test: for (int i = 0; i < we.size(); i++) {
				try {
					if (we.get(i).getAttribute("data-ticket-type")
							.equalsIgnoreCase(BookType)) {
						we.get(i + 1).click();
						break test;
					}
				} catch (Exception e) {

				}
				if (i == we.size()) {
					Reporter.log(BookType + " : is not displayed", true);
					Assert.assertTrue(false);
				}
			}
			safeClick(driver, By.id("eventBook"));
		}
	}

	public void locals_Change_LocationOLD(RemoteWebDriver driver)
			throws Exception {
		if (!elementVisible(driver,
				getObjectLocals("LocalCom_HomePage_Location_Link"), 5)) {
			Reporter.log("Change city Location Link is not displayed", true);
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectLocals("LocalCom_HomePage_Location_Link"));
		// safeClick(driver, By.xpath("//p/a"));
		driver.switchTo().frame("modal_window");
		Thread.sleep(2000);
		safeType(driver, getObjectLocals("LocalEnterCity"),
				dataFile.value("Change_City"));
		safeClick(driver, getObjectLocals("LocalCom_TTD_Selectcity"));

	}

	// suresh search
	public void locals_TTD_Search(RemoteWebDriver driver, String collection)
			throws Exception {
		elementVisible(driver,
				getObjectLocals("LocalCom_HomePage_Activity_Tab"), 20);
		if (!elementVisible(driver,
				getObjectLocals("LocalCom_HomePage_Activity_Tab"), 20)) {

			Reporter.log("Activity Tab is not displayed", true);
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectLocals("LocalCom_HomePage_Activity_Tab"));
		if (collection.equalsIgnoreCase("kids")) {
			safeClick(driver, getObjectLocals("LocalCom_TTD_Kids"));
			safeClick(driver, getObjectLocals("LocalCom_TTD_kids_list"));

		} else if (collection.equalsIgnoreCase("Groups")) {
			safeClick(driver, getObjectLocals("LocalCom_TTD_groups"));
			safeClick(driver, getObjectLocals("LocalCom_TTD_groups_list"));

		} else if (collection.equalsIgnoreCase("Adult")) {
			safeClick(driver, getObjectLocals("LocalCom_TTD_Adult"));
			safeClick(driver, getObjectLocals("LocalCom_TTD_Adult_list"));
		} else if (collection.equalsIgnoreCase("AdultChild")) {
			safeClick(driver, getObjectLocals("LocalCom_TTD_Adult"));
			safeClick(driver, getObjectLocals("LocalCom_TTD_AdultChild_list"));
		} else if (collection.equalsIgnoreCase("Carousal")) {
			safeClick(driver, getObjectLocals("Localcom_TTD_Carousal"));
		} else if (collection.equalsIgnoreCase("prodedit")) {

			safeClick(driver, getObjectLocals("Localcom_TTD_prodEditorial"));
			safeClick(driver, getObjectLocals("LocalCom_TTD_groups_list"));
		}
		// safeClick(driver,
		// getObjectLocals("LocalCom_ActivityPage_ActivityImage"));
		Thread.sleep(5000);
		elementNotVisible(driver, getObjectLocals("LocalCom_Loading"), 10);
		// elementVisible(driver,
		// getObjectLocals("LocalCom_Activity_DetailsPage_Name"),
		// 10);
		// safeClick(driver,
		// getObjectLocals("LocalCom_Activity_DetailsPage_ActivityImage"));
		elementNotVisible(driver, getObjectLocals("LocalCom_Loading"), 10);
		elementVisible(driver,
				getObjectLocals("LocalCom_Activity_BookPage_Bookbutton"), 10);
		safeClick(driver,
				getObjectLocals("LocalCom_Activity_BookPage_Bookbutton"));
	}

	// suresh book form
	public void locals_TTD_bookpopup(RemoteWebDriver driver, String pax)
			throws Exception {
		Thread.sleep(5000);
		elementVisible(driver,
				getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButton"),
				10);
		safeClick(
				driver,
				getObjectLocals("LocalCom_Activity_BookPage_PopUP_CalendarIcon"));
		safeClick(driver, By.linkText("28"));

		elementVisible(driver,
				getObjectLocals("LocalCom_Activity_BookPage_PopUP_TimeSlot"),
				10);
		if (pax.equalsIgnoreCase("children")) {
			safeClick(driver, getObjectLocals("LocalCom_child_pax"));
			Select sct = new Select(driver.findElement(By.id("children")));
			sct.selectByIndex(2);
			safeClick(
					driver,
					getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButton"));

		} else if (pax.equalsIgnoreCase("groups")) {
			safeClick(driver, getObjectLocals("LocalCom_TTD_pop_option"));
			Select sct1 = new Select(driver.findElement(By.id("options")));
			sct1.selectByIndex(1);
			safeClick(
					driver,
					getObjectLocals("LocalCom_Activity_BookPage_PopUP_CalendarIcon"));
			safeClick(driver, By.linkText("28"));
			safeClick(
					driver,
					getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButton"));
		} else if (pax.equalsIgnoreCase("Adult")) {
			safeClick(
					driver,
					getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButton"));
		} else if (pax.equalsIgnoreCase("AdultChild")) {
			safeClick(driver, getObjectLocals("LocalCom_child_pax"));
			Select sct = new Select(driver.findElement(By.id("children")));
			sct.selectByIndex(2);
			safeClick(
					driver,
					getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButton"));
		}
		// safeClick(driver,
		// getObjectLocals("LocalCom_Activity_BookPage_PopUP_BookButton"));
		Thread.sleep(5000);

	}

	public void locals_Events_Data_Refresh(RemoteWebDriver driver,
			String EventType) throws InterruptedException {
		if (EventType.equals("Insider")) {
			driver.get("http://10.10.21.150:9080/local-suppliers/insiderData?task=2");
			Thread.sleep(5000);
		} else {
			driver.get("http://10.10.21.150:9080/local-suppliers/meraData?task=2");
			Thread.sleep(5000);
		}
	}

	public void locals_Event_PaymentType(RemoteWebDriver driver,
			String EventPayment) throws Exception {
		/*
		 * String eventType = getAttribute(driver,
		 * getObjectLocals("LocalMobileWeb_Events_BookDetails_EventType"),
		 * "data-ticket-type"); if(EventPayment.equalsIgnoreCase("free")){
		 * if(!eventType.equalsIgnoreCase("Free Tickets")){
		 * Reporter.log("Free Booking is displayed as "+eventType);
		 * Assert.assertTrue(false); } else
		 * if(!eventType.equalsIgnoreCase("paid")){
		 * if(!eventType.equalsIgnoreCase("Tickets")){
		 * Reporter.log("Paid Payment Type is displayed as "+eventType);
		 * Assert.assertTrue(false); } } }
		 */
		String Booking_Type = getText(driver,
				By.cssSelector("p.ticket-units__price-digit"));
		if (EventPayment.equalsIgnoreCase("Free")) {
			if (!Booking_Type.contains("Free")) {
				Reporter.log("Free Booking is displayed as " + Booking_Type,
						true);
				Assert.assertTrue(false);
			}
		} else if (EventPayment.equalsIgnoreCase("paid")) {
			if (Booking_Type.contains("Free")) {
				Reporter.log("Paid Booking is displayed as " + Booking_Type,
						true);
				Assert.assertTrue(false);
			}
		}

	}

	public void hqTripDetail() {

	}

	public String localCom_HQ_Cancellation(RemoteWebDriver driver, String TripID)
			throws Exception {
		if (MakePaymentOnlyInQA2) {
			LocalCom_Open_TripID_HQ(driver, TripID);
			Thread.sleep(2000);
			// driver.findElement(By.xpath("//a[text()='Cancel
			// trip']")).click();
			safeClick(driver, getObjectLocals("LocalCom_HQ_Trips_Cancel_Link"));
			elementVisible(driver,
					getObjectLocals("LocalCom_HQ_Trips_Cancel_AddNotes"), 30);
			safeType(driver,
					getObjectLocals("LocalCom_HQ_Trips_Cancel_AddNotes"),
					"Test Booking");
			safeClick(driver,
					getObjectLocals("LocalCom_HQ_Trips_Cancel_Button"));
			elementNotPresent_Time(driver,
					getObjectLocals("LocalCom_HQ_Trips_Cancel_AddNotes"), 20);
			elementVisible(driver, getObjectLocals("LocalCom_HQ_Trips_Status"),
					60);
			String Cancel_Status = getText(driver,
					getObjectLocals("LocalCom_HQ_Trips_Status"));
			if (!Cancel_Status.contains("Cancelled")) {
				Reporter.log("Trip Status after cancellation is : "
						+ Cancel_Status, true);
				Assert.assertEquals(true, false);
			}
		}
		return TripID;
	}

	public void LocalCom_Open_TripID_HQ(RemoteWebDriver driver, String TripID)
			throws Exception {
		String URL = logURL(driver);
		if (URL.contains("com")) {
			driver.get(baseUrl + "/hq/trips/" + TripID);

		} else if (URL.contains("ae")) {
			driver.get(baseUrl_AE + "/hq/trips/" + TripID);
		}

		logURL(driver);
		if (!elementVisible(driver,
				getObjectLocals("LocalCom_HQ_Trips_Cancel_Link"), 60)) {
			// System.out.println("method LocalCom_Open_TripID_HQ completed");
			refreshPage(driver);
		} else if (textPresent(driver, "Sorry, our system is acting up.", 2)) {
			Reporter.log("Sorry, our system is acting up. : error is displayed");
			Assert.assertTrue(false);
		}
		// System.out.println("method LocalCom_Open_TripID_HQ completed");
	}

	// gst-suresh
	public void gstDetails(RemoteWebDriver driver, boolean isLogin)
			throws Exception {

		safeSelect(driver, getObjectLocals("LocalCom_ItineraryPage_Title"),
				dataFile.value("Title"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_FirstName"),
				dataFile.value("First_Name_A1"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_LastName"),
				dataFile.value("Last_Name_A1"));
		if (!isLogin) {
			safeType(driver, getObjectLocals("LocalCom_ItineraryPage_EmailID"),
					campLocal.value("bookingEmailId"));
			safeType(driver, getObjectLocals("LocalCom_ItineraryPage_PhoneNo"),
					dataFile.value("LocalsPhoneNO"));
			safeClick(driver, getObjectLocals("LocalGstCheckbox"));
			safeType(driver, getObjectLocals("LocalGstNumber"),
					dataFile.value("Local_Gst_Number"));
			safeType(driver, getObjectLocals("LocalGstHolderName"),
					dataFile.value("Local_Gst_HolderName"));
			// safeType(driver, getObjectLocals("LocalGstHolderAddress"),
			// dataFile.value("Local_Gst_Address"));
		} else {
			safeType(driver, getObjectLocals("LocalCom_ItineraryPage_PhoneNo"),
					dataFile.value("LocalsPhoneNO"));
			safeClick(driver, getObjectLocals("LocalGstCheckbox"));
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", "");

		safeClick(driver, getObjectLocals("LocalCom_ItineraryPage_Button"));

	}

	/**
	 * activity verification in Feature Page
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void featureActivityList(RemoteWebDriver driver) throws Exception {

		List<WebElement> activityList = driver.findElements(By
				.xpath(objectReposLocals.value("Local_Feature_Activity")));
		Reporter.log(
				"Total number activity Feature Page have "
						+ activityList.size(), true);
		if (activityList.size() == 0) {
			Reporter.log(
					"Activity in Feature Page count is Zero,Failing Test case here only",
					true);
			Assert.fail();
		}
		for (int itr = 1; itr <= activityList.size(); itr++) {
			if (itr == 1) {
				((JavascriptExecutor) driver).executeScript(
						"window.scrollBy(0,200)", "");
			}
			String inp = objectReposLocals.value("Local_Feature_Activity")
					+ "[" + itr + "]";
			Reporter.log(
					"Activity name : "
							+ driver.findElement(By.xpath(inp)).getAttribute(
									"data-activity-name"), true);
			// Thread.sleep(200);
			safeClick(driver, By.xpath(inp));
			// Thread.sleep(2000);
			elementAssert(driver,
					getObjectLocals("Local_Feature_Activity_header"), 5);
			Reporter.log(
					"Activity header:"
							+ getText(
									driver,
									getObjectLocals("Local_Feature_Activity_header")),
					true);
			Assert.assertTrue(
					(driver.findElement(
							getObjectLocals("Local_Feature_Activity_bookForm"))
							.getText().contains("Reserve your spot now")),
					"'Activity is sold out' is coming in feature page");
			Reporter.log(
					"Feature Activity: "
							+ driver.findElement(
									getObjectLocals("Local_Feature_Activity_header"))
									.getText() + "Reverse you spot", true);
			elementAssert(driver,
					getObjectLocals("Local_Feature_Activity_bookForm"), 5);
			elementAssert(driver,
					getObjectLocals("Local_Feature_Activity_bookLink"), 5);
			// Thread.sleep(200);
			driver.navigate().back();
		}

	}

	/**
	 * Verification of collection cards
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void featureCollectionCardsList(RemoteWebDriver driver)
			throws Exception {

		String collectionLoctor, collectionName, activityName, activityLoctor;
		List<WebElement> collectionList = driver.findElements(By
				.xpath(objectReposLocals.value("Local_Feature_Collection")));
		Reporter.log("Total number collections Feature Page have "
				+ collectionList.size(), true);
		if (collectionList.size() == 0) {
			Reporter.log(
					"collections in Feature Page count is Zero,Failing Test case here only",
					true);
			Assert.fail();
		}
		for (int itr = 1; itr <= collectionList.size(); itr++) {
			if (itr == 1) {
				((JavascriptExecutor) driver).executeScript(
						"window.scrollBy(0,2200)", "");
			} else if (itr == 5) {
				safeClick(driver, getObjectLocals("Local_Collection_nxtBtn"));
			}
			Thread.sleep(2000);
			collectionLoctor = objectReposLocals
					.value("Local_Feature_Collection") + "[" + itr + "]";
			collectionName = driver.findElement(By.xpath(collectionLoctor))
					.getAttribute("data-collection-name");
			Reporter.log("Collection name : " + collectionName, true);
			safeClick(driver, By.xpath(collectionLoctor));
			Thread.sleep(2000);
			List<WebElement> collectionactivityLst = driver
					.findElements(By.xpath(objectReposLocals
							.value("Local_Collection_Activity")));
			Reporter.log(
					"Total activities count " + collectionactivityLst.size()
							+ "under this collections " + collectionName, true);

			for (int cnt = 1; cnt <= collectionactivityLst.size(); cnt++) {
				if (cnt == 1 || cnt == 5) {
					((JavascriptExecutor) driver).executeScript(
							"window.scrollBy(0,200)", "");
				}
				Thread.sleep(1000);

				if (cnt < 5) {
					activityLoctor = objectReposLocals
							.value("Local_Collection_Activity")
							+ "["
							+ cnt
							+ "]";
				} else {
					activityLoctor = "("
							+ objectReposLocals
									.value("Local_Collection_Activity") + "["
							+ (cnt - 4) + "])[" + 2 + "]";
				}

				activityName = driver.findElement(By.xpath(activityLoctor))
						.getAttribute("data-activityname");
				Reporter.log(
						"Activity Name under collection : " + activityName,
						true);
				safeClick(driver, By.xpath(activityLoctor));
				Thread.sleep(2000);
				if (isElementPresent(driver,
						getObjectLocals("Local_Feature_Error"))) {
					Assert.assertFalse(false, "This activity: " + activityName
							+ "have some problem");
				}
				elementAssert(driver,
						getObjectLocals("Local_Feature_Activity_header"), 5);
				Reporter.log(
						"Activity header:"
								+ getText(
										driver,
										getObjectLocals("Local_Feature_Activity_header")),
						true);

				/*
				 * Assert.assertTrue( (driver.findElement(getObjectLocals(
				 * "Local_Feature_Activity_bookForm")). getText()
				 * .contains("Reserve your spot now")),
				 * "'Activity is sold out' is coming in feature page");
				 * 
				 * Reporter.log("Feature Activity: " +
				 * driver.findElement(getObjectLocals(
				 * "Local_Feature_Activity_header")).getText( ) +
				 * "Reverse you spot");
				 */
				elementAssert(driver,
						getObjectLocals("Local_Feature_Activity_bookForm"), 5);
				// elementAssert(driver,
				// getObjectLocals("Local_Feature_Activity_bookLink"), 5);
				Thread.sleep(200);
				driver.navigate().back();

			}
			driver.navigate().back();
		}

	}

	/**
	 * Verification of Carousal in Feature page
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void featureCarousalList(RemoteWebDriver driver) throws Exception {

		String collectionLoctor, collectionName, activityName, activityLoctor;
		List<WebElement> collectionList = driver.findElements(By
				.xpath(objectReposLocals.value("Local_carosal")));
		Reporter.log("Total number Carousal Feature Page have "
				+ collectionList.size(), true);
		if (collectionList.size() == 0) {
			Reporter.log(
					"Carousal in Feature Page count is Zero,Failing Test case here only",
					true);
			Assert.fail();
		}

		collectionLoctor = objectReposLocals.value("Local_carosal") + "[" + 1
				+ "]/div";
		collectionName = driver.findElement(By.xpath(collectionLoctor))
				.getAttribute("data-name");
		Reporter.log("Carousal name : " + collectionName, true);
		Thread.sleep(1000);
		safeClick(driver, By.xpath(collectionLoctor));
		List<WebElement> collectionactivityLst = driver.findElements(By
				.xpath(objectReposLocals.value("Local_Collection_Activity")));
		Reporter.log("Total activities count " + collectionactivityLst.size()
				+ "under this collections " + collectionName, true);

		for (int cnt = 1; cnt <= collectionactivityLst.size(); cnt++) {
			if (cnt == 1 && cnt == 5 && cnt == 9) {
				((JavascriptExecutor) driver).executeScript(
						"window.scrollBy(0,200)", "");
			}
			activityLoctor = objectReposLocals
					.value("Local_Collection_Activity") + "[" + cnt + "]";
			activityName = driver.findElement(By.xpath(activityLoctor))
					.getAttribute("data-activityname");
			Reporter.log("Activity Name under collection : " + activityName,
					true);
			safeClick(driver, By.xpath(activityLoctor));
			Thread.sleep(2000);
			if (isElementPresent(driver, getObjectLocals("Local_Feature_Error"))) {
				Assert.assertFalse(false, "This activity: " + activityName
						+ "have some problem");
			}
			elementAssert(driver,
					getObjectLocals("Local_Feature_Activity_header"), 5);
			Reporter.log(
					"Activity header:"
							+ getText(
									driver,
									getObjectLocals("Local_Feature_Activity_header")),
					true);
			Assert.assertTrue(
					(driver.findElement(
							getObjectLocals("Local_Feature_Activity_bookForm"))
							.getText().contains("Reserve your spot now")),
					"'Activity is sold out' is coming in feature page");
			Reporter.log(
					"Feature Activity: "
							+ driver.findElement(
									getObjectLocals("Local_Feature_Activity_header"))
									.getText() + "Reverse you spot", true);
			elementAssert(driver,
					getObjectLocals("Local_Feature_Activity_bookForm"), 5);
			elementAssert(driver,
					getObjectLocals("Local_Feature_Activity_bookLink"), 5);
			Thread.sleep(200);
			driver.navigate().back();

		}
		driver.navigate().back();
	}

	/**
	 * Verification of collection cards
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void mobileFeatureCollectionCardsList(RemoteWebDriver driver)
			throws Exception {

		String collectionLoctor, collectionName, activityName, activityLoctor;
		List<WebElement> collectionList = driver.findElements(By
				.xpath(objectReposLocals.value("Local_MobileWeb_Activity")));
		Reporter.log("Total number collections Feature Page have "
				+ collectionList.size(), true);
		for (int itr = 1; itr <= collectionList.size(); itr++) {
			if (itr == 1) {
				((JavascriptExecutor) driver).executeScript(
						"window.scrollBy(0,2000)", "");
			} else if (itr == 5) {
				safeClick(driver, getObjectLocals("Local_Collection_nxtBtn"));
			}
			Thread.sleep(2000);
			collectionLoctor = objectReposLocals
					.value("Local_MobileWeb_Activity") + "[" + itr + "]";
			collectionName = driver.findElement(By.xpath(collectionLoctor))
					.getAttribute("data-collection-name");
			Reporter.log("Collection name : " + collectionName, true);
			safeClick(driver, By.xpath(collectionLoctor));
			Thread.sleep(2000);
			List<WebElement> collectionactivityLst = driver
					.findElements(By.xpath(objectReposLocals
							.value("Local_Collection_Activity")));
			Reporter.log(
					"Total activities count " + collectionactivityLst.size()
							+ "under this collections " + collectionName, true);

			for (int cnt = 1; cnt <= collectionactivityLst.size(); cnt++) {
				((JavascriptExecutor) driver).executeScript(
						"window.scrollBy(0,270)", "");
				Thread.sleep(1000);
				if (cnt < 5) {
					activityLoctor = objectReposLocals
							.value("Local_Collection_Activity")
							+ "["
							+ cnt
							+ "]";
				} else {
					activityLoctor = "("
							+ objectReposLocals
									.value("Local_Collection_Activity") + "["
							+ (cnt - 4) + "])[" + 2 + "]";
				}

				activityName = driver.findElement(By.xpath(activityLoctor))
						.getAttribute("data-activityname");
				Reporter.log(
						"Activity Name under collection : " + activityName,
						true);
				safeClick(driver, By.xpath(activityLoctor));
				Thread.sleep(2000);
				if (isElementPresent(driver,
						getObjectLocals("Local_Feature_Error"))) {
					Assert.assertFalse(false, "This activity: " + activityName
							+ "have some problem");
				}
				elementAssert(driver,
						getObjectLocals("Local_Feature_Activity_header"), 5);
				Reporter.log(
						"Activity header:"
								+ getText(
										driver,
										getObjectLocals("Local_Feature_Activity_header")),
						true);
				elementAssert(driver,
						getObjectLocals("Local_Feature_Activity_bookLink"), 5);
				Thread.sleep(200);
				driver.navigate().back();

			}
			driver.navigate().back();
		}

	}

	/**
	 * activity verification in Feature Page
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void mobileFeatureActivityList(RemoteWebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)",
				"");
		String inp = objectReposLocals.value("Local_Feature_Activity") + "["
				+ 1 + "]";
		Reporter.log(
				driver.findElement(By.xpath(inp)).getAttribute(
						"data-activity-name"), true);
		Reporter.log(
				"Activity name : "
						+ driver.findElement(By.xpath(inp)).getAttribute(
								"data-activity-name"), true);
		try {
			safeClick(driver, By.xpath(inp));
			elementAssert(driver,
					getObjectLocals("Local_Feature_Activity_header"), 5);
			Reporter.log(
					"Activity header:"
							+ getText(
									driver,
									getObjectLocals("Local_Feature_Activity_header")),
					true);
			elementAssert(driver,
					getObjectLocals("Local_Feature_Activity_bookLink"), 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Verification of Carousal in Feature page
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void mobileFeatureCarousalList(RemoteWebDriver driver)
			throws Exception {

		String activityName, activityLoctor;
		WebElement we = driver
				.findElement(getObjectLocals("Local_MobileWeb_Carousal"));
		((JavascriptExecutor) driver)
				.executeScript("arguments[0].click();", we);
		List<WebElement> collectionactivityLst = driver.findElements(By
				.xpath(objectReposLocals.value("Local_Collection_Activity")));
		Reporter.log("Total activities count " + collectionactivityLst.size()
				+ "under this collections ", true);

		for (int cnt = 1; cnt <= collectionactivityLst.size(); cnt++) {
			((JavascriptExecutor) driver).executeScript(
					"window.scrollBy(0,240)", "");
			activityLoctor = objectReposLocals
					.value("Local_Collection_Activity") + "[" + cnt + "]";
			activityName = driver.findElement(By.xpath(activityLoctor))
					.getAttribute("data-activityname");
			Reporter.log("Activity Name under collection : " + activityName,
					true);
			safeClick(driver, By.xpath(activityLoctor));
			Thread.sleep(2000);
			if (isElementPresent(driver, getObjectLocals("Local_Feature_Error"))) {
				Assert.assertFalse(false, "This activity: " + activityName
						+ "have some problem");
			}
			elementAssert(driver,
					getObjectLocals("Local_Feature_Activity_header"), 5);
			elementAssert(driver,
					getObjectLocals("Local_Feature_Activity_bookLink"), 5);
			Thread.sleep(200);
			driver.navigate().back();
		}
	}

	public void rateRuleVerification(RemoteWebDriver driver, String promoCode)
			throws Exception {
		String couponMsg = null, tripId = null;
		driver.get(locals_City_URL);
		Thread.sleep(2000);
		safeClick(driver, getObjectLocals("sign_user"));
		if (elementVisible(driver,
				getObjectLocals("LocalCom_HomePage_SignOut"), 2)) {
			safeClick(driver, getObjectLocals("LocalCom_HomePage_SignOut"));
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			driver.get(locals_City_URL);

		}
		locals_NameSearch_TTD(driver,
				dataFile.value("Locals_Data_Activity_Adult_Group"),
				dataFile.value("Locals_Data_Activity_Adult_Name"));
		locals_BookPopUP(driver, "TTD", "AdultTime", 1);
		rateRuleItinerary(driver);
		safeType(driver, getObjectLocals("LocalCoupon"), promoCode);
		safeClick(driver, getObjectLocals("Localapply"));
		if (!elementVisible(driver,
				getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg"), 20)) {
			Reporter.log("Coupon is not wrking", true);
			Assert.assertTrue(false);
		}
		couponMsg = getText(driver, getObjectLocals("coupon_Msg"));
		if (couponMsg.contains("saved")) {
			Reporter.log("Instant Coupon is applied : " + couponMsg, true);
		} else if (couponMsg.contains("Cleartrip Wallet")) {
			Reporter.log("Cleartrip Wallet Coupon is applied : " + couponMsg,
					true);
		} else if (couponMsg.contains("Cleartrip Wallet")
				&& couponMsg.contains("saved")) {
			Reporter.log("Cleartrip Wallet & Instant Coupon is applied : "
					+ couponMsg, true);
		} else if (couponMsg
				.contains("Sorry, the coupon code entered is invalid")) {
			Reporter.log("Coupon Message displayed : " + couponMsg, true);
			Assert.assertTrue(false);
		}

		safeClick(driver, getObjectLocals("LocalCom_ItineraryPage_Button"));
		locals_PaymentPage(driver, "CC");
		tripId = locals_Payment_ConfirmationPage(driver, "WL TTD Adult : ", "");
		if (couponMsg.contains("saved")) {
			Assert.assertTrue(
					getText(driver, By.xpath("//dt[3]")).contains("Coupon"),
					"Coupon didn't applied");
			Reporter.log("Coupon is applied", true);
			Assert.assertTrue(
					getText(driver, By.xpath("//dd[3]")).contains("10"),
					"Coupon amount isn't correct");
			Reporter.log("Instant cashback applied", true);
		} else if (couponMsg.contains("Cleartrip Wallet")) {
			driver.get(baseUrl);
			signIN(driver, campLocal.value("bookingEmailId"));
			driver.get(baseUrl + "/account/wallet");
			Assert.assertTrue(
					getText(driver, getObjectLocals("wallet_cashBack"))
							.contains(tripId), tripId
							+ " caskback didn't credit into wallet");
			Reporter.log(tripId + " caskback is credit into wallet", true);
			Assert.assertTrue(
					getText(driver, getObjectLocals("wallet_cashBack"))
							.contains("10"),
					" 10 Rupee caskback didn'tcredited into wallet");
			Reporter.log("10 Rupee caskback is credited into wallet", true);
			safeClick(driver, getObjectLocals("sign_user"));
			if (elementVisible(driver,
					getObjectLocals("LocalCom_HomePage_SignOut"), 2)) {
				safeClick(driver, getObjectLocals("LocalCom_HomePage_SignOut"));
				Thread.sleep(2000);
				driver.manage().deleteAllCookies();

			}
		}
	}

	public void rateRuleItinerary(RemoteWebDriver driver) throws Exception {
		for (int i = 0; i <= 20; i++) {
			if (elementVisible(driver,
					getObjectLocals("LocalCom_ItineraryPage_Button"), 1)) {
				break;
			} else if (textPresent(driver,
					"Whatever you're looking for, isn't here", 1)) {
				Reporter.log(
						"Whatever you're looking for, isn't here : error message in Itinerary Page",
						true);
				Assert.assertTrue(false);
			} else if (textPresent(driver, "Sorry, our system is acting up.", 1)) {
				Reporter.log(
						"Sorry, our system is acting up :  error message in Itinerary Page",
						true);
				Assert.assertTrue(false);
			}

		}
		if (!elementVisible(driver,
				getObjectLocals("LocalCom_ItineraryPage_Title"), 10)) {
			Reporter.log("Itinerary Page is not displayed", true);
			Assert.assertTrue(false);
		}
		safeSelect(driver, getObjectLocals("LocalCom_ItineraryPage_Title"),
				dataFile.value("Title"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_FirstName"),
				dataFile.value("First_Name_A1"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_LastName"),
				dataFile.value("Last_Name_A1"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_EmailID"),
				campLocal.value("bookingEmailId"));
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_PhoneNo"),
				dataFile.value("LocalsPhoneNO"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", "");

	}

	public void uploadRateRules(RemoteWebDriver driver, String coupon,
			String promoCode, String rateId) throws Exception {
		File fo = new File(System.getProperty("user.dir")
				+ "/uploadRateRule.csv");
		PrintWriter pw = new PrintWriter(fo);
		StringBuilder sb = new StringBuilder();
		sb.append(coupon);
		sb.append(',');
		sb.append(promoCode);
		sb.append(',');
		sb.append(rateId);

		pw.write(sb.toString());
		pw.close();

		if (coupon.contains("delete") || promoCode.contains("QAINSWALL")) {
			driver.get(baseUrl + "/hq/");
			vURLStatus(driver, "/hq");
			if (!(isElementPresent(driver,
					getObjectLocals("LocalCom_HomePage_SignOut")) && (getText(
					driver, getObjectLocals("rule_userName")).contains("NOC")))) {
				safeClick(driver, getObjectLocals("LocalCom_HomePage_SignOut"));
				Thread.sleep(2000);
				safeClick(driver, getObjectLocals("sign_user"));
				if (elementVisible(driver,
						getObjectLocals("LocalCom_HomePage_SignOut"), 2)) {
					safeClick(driver,
							getObjectLocals("LocalCom_HomePage_SignOut"));
					Thread.sleep(2000);
					driver.manage().deleteAllCookies();
					driver.get(baseUrl + "/hq");
					safeType(driver, getObjectLocals("rateRule_login"),
							"suresh.halli@cleartrip.com");
					safeType(driver, getObjectLocals("rateRule_passwrd"),
							dataFile.value("Password"));
					safeClick(driver, getObjectLocals("rateRule_signIn"));
					waitElement(driver, getObjectLocals("rate_homePage"), 10);
					driver.get(baseUrl + "/hq/local_raterules");
					vURLStatus(driver, "/hq/local_raterules");
				}
			}
			if (coupon.contains("CT")) {
				driver.get(baseUrl + "/hq/local_raterules/edit?id=" + rateId);
				safeClick(driver, getObjectLocals("rule_activeId"));
				safeClick(driver, getObjectLocals("rateRule_saveRule"));
			}
		}
		safeClick(driver, By.linkText("Dashboard"));
		safeClick(driver, By.linkText("Others"));
		safeClick(driver, By.linkText("Upload"));
		driver.findElement(By.id("file")).sendKeys(fo.toString());
		safeClick(driver, getObjectLocals("rateRule_ProcessFile"));
		Assert.assertTrue(isElementPresent(driver, By.xpath("//h1")),
				"Batch transaction file didn't upload");
		Reporter.log("Batch transaction file uploaded", true);
		// safeClick(driver, getObjectLocals("LocalCom_HomePage_SignOut"));
		driver.get(baseUrl + "/hq/air/services");
		safeClick(driver, getObjectLocals("rateRule_Generate"));
		isElementPresent(driver, getObjectLocals("rateRule_Ok"));
		fo.delete();
	}

	public String createRateRules(RemoteWebDriver driver, String header,
			String wallet, String code, String discount) throws Exception {
		driver.get(baseUrl + "/hq");
		vURLStatus(driver, "/hq");
		if (isElementPresent(driver,
				getObjectLocals("LocalCom_HomePage_SignOut"))) {
			safeClick(driver, getObjectLocals("LocalCom_HomePage_SignOut"));
			Thread.sleep(2000);
			safeClick(driver, getObjectLocals("sign_user"));
			if (elementVisible(driver,
					getObjectLocals("LocalCom_HomePage_SignOut"), 2)) {
				safeClick(driver, getObjectLocals("LocalCom_HomePage_SignOut"));
				Thread.sleep(2000);
				driver.manage().deleteAllCookies();
				driver.get(baseUrl + "/hq");
			}

		}
		safeType(driver, getObjectLocals("rateRule_login"), "noc@cleartrip.com");
		safeType(driver, getObjectLocals("rateRule_passwrd"),
				dataFile.value("Password"));
		safeClick(driver, getObjectLocals("rateRule_signIn"));
		waitElement(driver, getObjectLocals("rate_homePage"), 10);
		driver.get(baseUrl + "/hq/local_raterules");
		vURLStatus(driver, "/hq/local_raterules");
		if (isElementPresent(driver, By.xpath(getXpathByReplace(
				objectReposLocals.value("Booking_date_selection"), code)))) {
			Reporter.log("Coupon Code " + code + " is already exist", true);
		} else {
			safeClick(driver, getObjectLocals("rateRule_NewRule"));
			safeType(driver, getObjectLocals("rateRule_promoCode"), code);
			safeType(driver, getObjectLocals("rateRule_promoDesc"), header);
			safeClick(driver, getObjectLocals("rateRule_mobWLChk"));
			safeClick(driver, getObjectLocals("rateRule_WLChk"));
			safeClick(driver, getObjectLocals("rateRule_websiteChk"));
			safeClick(driver, getObjectLocals("rateRule_mobileWeb"));
			safeClick(driver, getObjectLocals("rateRule_DIndia"));
			safeClick(driver, getObjectLocals("rateRule_DAe"));
			if (wallet.equals("wallet")) {
				safeClick(driver, getObjectLocals("rateRule_walletPlus"));
			} else if (wallet.equals("CT")) {
				safeClickList(driver, getObjectLocals("rule_type"),
						"Cleartrip Funded Discount");
				safeClick(driver, getObjectLocals("rateRule_ActTtd"));
				waitElement(driver, getObjectLocals("rule_ttd_btn"), 5);
				safeClick(driver, getObjectLocals("rule_ttd_btn"));
				safeType(driver, getObjectLocals("rule_ttdInputBox"),
						campLocal.value("ctFund_activity"));
				Thread.sleep(1000);
				safeClick(driver, getObjectLocals("rule_ttd_addBtn"));
				isElementPresent(driver, By.linkText("Delete"));
			}
			safeClick(driver, getObjectLocals("rateRule_discFlat"));
			safeType(driver, getObjectLocals("rateRule_AmtVal"), discount);
			safeClick(driver, getObjectLocals("rateRule_totalFare"));
			safeClick(driver, getObjectLocals("rateRule_saveRule"));
		}
		rateId = driver.findElement(
				By.xpath(getXpathByReplace(
						objectReposLocals.value("rateRule_id"), code)))
				.getAttribute("href");
		StringBuffer sb = new StringBuffer(rateId);
		rateId = sb.substring(53, rateId.length());

		return rateId;
	}

	public void vURLStatus(RemoteWebDriver driver, String url) throws Exception {
		if (isElementPresent(driver, getObjectLocals("Local_notWork_Err")) == true) {
			Reporter.log(url + " URL is Down", true);
			Assert.fail();
		}
	}

	public void signIN(RemoteWebDriver driver, String userName) {

		try {
			String mainWindow = driver.getWindowHandle();
			safeClick(driver, getObjectLocals("LocalCom_HomePage_YourTrips"));
			elementVisible(driver, getObjectLocals("LocalCom_HomePage_SignIN"),
					20);
			safeClick(driver, getObjectLocals("LocalCom_HomePage_SignIN"));
			driver.switchTo().frame("modal_window");
			elementPresent_Time(driver,
					getObjectLocals("LocalCom_SignIn_ModalWindow_Email"), 50);
			safeType(driver,
					getObjectLocals("LocalCom_SignIn_ModalWindow_Email"),
					userName);
			safeType(driver,
					getObjectLocals("LocalCom_SignIn_ModalWindow_Password"),
					campLocal.value("bookingPassword"));
			safeClick(
					driver,
					getObjectLocals("LocalCom_SignIn_ModalWindow_SignIN_Button"));
			Thread.sleep(7000);
			driver.switchTo().window(mainWindow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print Invoice Details verification
	 * 
	 * @param driver
	 * @param id
	 * @param cashBack
	 */
	public void printInvoiceVerification(RemoteWebDriver driver, String id,
			String cashBack, String couponType) {
		ArrayList<String> ids = new ArrayList<>();
		int cashBck = 0;
		float convFee, sGst, cGst, calculateGst;

		try {
			DecimalFormat df = new DecimalFormat("###.##");
			Thread.sleep(3000);
			driver.get(locals_City_URL);
			signIN(driver, campLocal.value("bookingEmailId"));
			driver.get(baseUrl + "/account/trips/" + id);
			safeClick(driver, getObjectLocals("Local_Invoice"));
			Set<String> windowId = driver.getWindowHandles();
			Iterator<String> itr = windowId.iterator();
			while (itr.hasNext()) {
				ids.add(itr.next());
			}

			driver.switchTo().window(ids.get(1));

			Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText()
					.contains(id), id + " isnot present");
			Reporter.log(id + " is present in print invoice", true);
			if (cashBack.equals(null)) {
				cashBck = 0;
			} else
				cashBck = Integer.parseInt(cashBack.trim());

			convFee = Float.parseFloat(getText(driver, By
					.xpath(getXpathByReplace(
							objectReposLocals.value("Local_printInvoice"),
							"Convenience"))));
			sGst = Float.parseFloat(getText(driver, By.xpath(getXpathByReplace(
					objectReposLocals.value("Local_printInvoice"), "SGST"))));
			cGst = Float.parseFloat(getText(driver, By.xpath(getXpathByReplace(
					objectReposLocals.value("Local_printInvoice"), "CGST"))));

			if ((convFee - cashBck <= 0)) {
				if (couponType.toLowerCase().contains("wallet")) {
					Reporter.log(
							"Convenience Fee minus cashBack is less then Zero",
							true);
					Assert.assertTrue(sGst > 0, "SGST should not be zero");

					Reporter.log("State GST is : " + sGst, true);
					Assert.assertTrue(cGst > 0, "CGST should not be zero");
					Reporter.log("State GST is :" + cGst, true);
				} else {
					Reporter.log(
							"Convenience Fee minus cashBack is less then Zero",
							true);
					Assert.assertTrue(sGst == 0, "SGST should be 0");
					Reporter.log("State GST is 0", true);
					Assert.assertTrue(cGst == 0, "CGST should be 0");
					Reporter.log("State GST is 0", true);
				}

			} else {

				calculateGst = (float) ((((convFee - cashBck) / 1.18) * .18) / 2);
				Assert.assertTrue(
						Float.parseFloat(df.format(calculateGst)) == sGst,
						"SGST isn't matching with calcualted GST");
				Reporter.log(sGst + " :SGST is matching with calcualted GST",
						true);
				Assert.assertTrue(
						Float.parseFloat(df.format(calculateGst)) == cGst,
						"CGST isn't matching with calcualted GST");
				Reporter.log(cGst + " :CGST is matching with calcualted GST",
						true);

			}

			driver.close();
			driver.switchTo().window(ids.get(0));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void vCampDashBoardBooking(RemoteWebDriver driver, String url,
			String tripId) {

		try {
			Thread.sleep(3000);
			driver.get(url + "/camp/accounts/sign_in");
			CampActivities camp = new CampActivities();
			camp.campActivities_SignIN(driver, "SA");
			driver.get(url + "/camp/bookings");
			safeType(driver, getObjectLocals("Camp_Booking_trip"), tripId);
			safeClick(driver, getObjectLocals("Camp_TripFetch_btn"));
			Thread.sleep(3000);
			Assert.assertTrue(
					getText(driver, getObjectLocals("Camp_tripFetch_detail"))
							.contains(tripId),
					"Unable to fetch Details of This trip ID :" + tripId);
			Reporter.log("Able to fetch Details of This trip ID :" + tripId,
					true);
			Thread.sleep(2000);
			safeClick(driver, By.xpath("//i[@class='fa fa-arrow-down']"));
			if (isElementPresent(driver,
					By.xpath("//button[contains(.,'Confirm')]")) == true) {
				safeClick(driver, By.xpath("//button[contains(.,'Confirm')]"));
				Thread.sleep(5000);
			}
			Assert.assertTrue(
					camp.dbSchemaGetValue("RESERVATION_STATUS",
							"ACTIVITY_LEDGER_INFO", "TRIP_REF", tripId).equals(
							"reconfirmed"),
					"reconfirmed status is not confirmed from DB");
			Reporter.log(tripId + " have reconfirmed status in DB", true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void ttdYouMightAlsoLike(RemoteWebDriver driver, String seo)
			throws Exception {
		scrollDownToElementAndClick(driver,
				getObjectLocals("LocalsHomePageProdEditorial"));
		// safeClick(driver, getObjectLocals("LocalsHomePageProdEditorial"));
		// safeClick(driver,
		// getObjectLocals("LocalMobileWeb_ProductEditorial_Link"));

		if (seo.equalsIgnoreCase("AlsoLike")) {
			safeClick(driver, getObjectLocals("LocalListingUMightLikeColl"));
			safeClick(driver, getObjectLocals("LocalListingUMightLikelist"));

		} else if (seo.equalsIgnoreCase("NearYou")) {
			safeClick(driver, getObjectLocals("LocalListingNearYou"));
			scrollDownToElementAndClick(driver,
					getObjectLocals("LocalListingNearYou"));
		}

		if (elementVisible(driver,
				getObjectLocals("LocalCom_Activity_BookPage_Bookbutton"), 5)) {
			Reporter.log("Actvity details page viewed", true);
		} else {
			Reporter.log("Actvity details page isnot viewed", true);
			Assert.fail();
		}

	}

	public void scrollDownToElementAndClick(RemoteWebDriver driver, By by)
			throws Exception {
		boolean isElementMentionedPresent = false;
		WebElement we = driver.findElement(by);
		while (isElementMentionedPresent == false) {
			if (we.isDisplayed()) {
				isElementMentionedPresent = true;
				we.click();

			} else {
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].scrollIntoView();", we);
			}
		}
	}

	public void fnbYouMightAlsoLike(RemoteWebDriver driver, String seo)
			throws Exception {
		safeClick(driver, getObjectLocals("LocalsHomePageWl"));
		Thread.sleep(2500);
		if (seo.equalsIgnoreCase("AlsoLike")) {
			((JavascriptExecutor) driver)
					.executeScript(
							"arguments[0].click();",
							driver.findElement(getObjectLocals("LocalListingUMightLikeWlColl")));
			Thread.sleep(1000);
			((JavascriptExecutor) driver)
					.executeScript(
							"arguments[0].click();",
							driver.findElement(getObjectLocals("LocalListingUMightLikelist")));
		} else if (seo.equalsIgnoreCase("NearYou")) {

			scrollToElement(driver, getObjectLocals("LocalListingNearYou"));
			safeClick(driver, getObjectLocals("LocalListingNearYou"));

		}
		if (elementVisible(driver,
				getObjectLocals("LocalCom_Activity_BookPage_Bookbutton"), 5)) {
			Reporter.log("Actvity details page viewed", true);
		} else {
			Reporter.log("Actvity details page isnot viewed", true);
			Assert.fail();
		}

	}

	public void ttdWlYouMightAlsoLike(RemoteWebDriver driver, String seo)
			throws Exception {
		scrollToElement(driver, getObjectLocals("LocalsHomePageWl"));
		safeClick(driver, getObjectLocals("LocalsHomePageWl"));
		Thread.sleep(2000);
		if (seo.equalsIgnoreCase("AlsoLike")) {
			((JavascriptExecutor) driver)
					.executeScript(
							"arguments[0].click();",
							driver.findElement(getObjectLocals("LocalListingUMightLikeWlColl")));
			Thread.sleep(1000);
			((JavascriptExecutor) driver)
					.executeScript(
							"arguments[0].click();",
							driver.findElement(getObjectLocals("LocalListingUMightLikelist")));
		} else if (seo.equalsIgnoreCase("NearYou")) {
			// Thread.sleep(1000);
			// scrollToElement(driver, getObjectLocals("LocalListingNearYou"));
			safeClick(driver, getObjectLocals("LocalListingNearYou"));
		}

		if (elementVisible(driver,
				getObjectLocals("LocalCom_Activity_BookPage_Bookbutton"), 5)) {
			Reporter.log("Actvity details page viewed", true);
		} else {
			Reporter.log("Actvity details page isnot viewed", true);
			Assert.fail();
		}

	}

	public void fnbWlYouMightAlsoLike(RemoteWebDriver driver, String seo)
			throws Exception {
		safeClick(driver, getObjectLocals("LocalsHomePageProdEditorial"));
		Thread.sleep(1000);
		if (seo.equalsIgnoreCase("AlsoLike")) {
			((JavascriptExecutor) driver)
					.executeScript(
							"arguments[0].click();",
							driver.findElement(getObjectLocals("LocalListingUMightLikeWlColl")));
			Thread.sleep(1000);
			((JavascriptExecutor) driver)
					.executeScript(
							"arguments[0].click();",
							driver.findElement(getObjectLocals("LocalListingUMightLikelist")));
		} else if (seo.equalsIgnoreCase("NearYou")) {
			Thread.sleep(1000);
			scrollToElement(driver, getObjectLocals("LocalListingNearYou"));
			safeClick(driver, getObjectLocals("LocalListingNearYou"));

		}
		if (elementVisible(driver,
				getObjectLocals("LocalCom_Activity_BookPage_Bookbutton"), 5)) {

			Reporter.log("Actvity details page viewed", true);
		} else {
			Reporter.log("Actvity details page isnot viewed", true);
			Assert.fail();
		}

	}

	public void deleteHQCoupon(String user, String paswrd, String dbUrl,
			String query) throws SQLException {
		java.sql.Connection conn = null;
		java.sql.Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Reporter.log("Connecting to a selected database...");
			conn = DriverManager.getConnection(dbUrl, user, paswrd);
			Reporter.log("Connected database successfully...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			conn.commit();
			Reporter.log("Query Exeution sucessfully: " + query, true);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				conn.close();
		}
	}

	public void bookingWithCTRateRule(RemoteWebDriver driver,
			String collection, String activityName, String discount) {
		String actPrice, CTPrice, locals_City_URL = baseUrl + "/local/"
				+ campLocal.value("campCity") + "/things-to-do-in-"
				+ campLocal.value("campCity");
		int priceActual, priceCtFund;

		try {
			driver.get(locals_City_URL);
			safeClick(driver, getObjectLocals("sign_user"));
			if (elementVisible(driver,
					getObjectLocals("LocalCom_HomePage_SignOut"), 2)) {
				safeClick(driver, getObjectLocals("LocalCom_HomePage_SignOut"));
				Thread.sleep(2000);
				driver.manage().deleteAllCookies();
				driver.get(locals_City_URL);

			}
			safeClick(driver,
					By.xpath(getXpathByReplace(
							objectReposLocals.value("Local_collectionName"),
							collection)));

			isElementPresent(
					driver,
					By.xpath(getXpathByReplace(activityName,
							campLocal.value("ctFund_activity"))));
			actPrice = getText(driver, By.xpath(getXpathByReplace(
					objectReposLocals.value("Local_activityPrice"),
					campLocal.value("ctFund_activity"))));
			CTPrice = getText(driver, By.xpath(getXpathByReplace(
					objectReposLocals.value("Local_activityCTPrice"),
					campLocal.value("ctFund_activity"))));
			safeClick(driver,
					By.xpath(getXpathByReplace(
							objectReposLocals.value("Local_activityName"),
							activityName)));

			priceActual = Integer.parseInt(actPrice.substring(1,
					actPrice.length()));
			priceCtFund = Integer.parseInt(CTPrice.substring(1,
					CTPrice.length()));

			Assert.assertTrue(
					(priceActual - priceCtFund) == Integer.parseInt(discount),
					discount
							+ " rupee doesn't fit with Activity discount price "
							+ priceCtFund + " rupee Actual Price of Activity "
							+ priceActual + " rupee");
			Reporter.log(discount
					+ " rupee fit with Activity discount price rupee "
					+ priceCtFund + " rupee Actual Price of Activity "
					+ priceActual + " rupee", true);

			locals_BookPopUP(driver, "TTD", "AdultChild", 1);
			if (isElementPresent(driver, getObjectLocals("local_booking_err"))) {
				Reporter.log("Error found ,after clicking Book now", true);
				Assert.fail();
			}
			rateRuleItinerary(driver);
			safeClick(driver, getObjectLocals("LocalCom_ItineraryPage_Button"));
			locals_PaymentPage(driver, "CC");
			printInvoiceVerification(
					driver,
					locals_Payment_ConfirmationPage(driver, "WL TTD Adult : ",
							""), discount, "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String createCtFundRateRule(RemoteWebDriver driver, String ctCode,
			String cTDiscount) {
		String instntRuleId = null;
		try {
			instntRuleId = createRateRules(driver, "CT Funded rateRule", "CT",
					ctCode, cTDiscount);
			uploadRateRules(driver, "add-coupon", ctCode, instntRuleId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return instntRuleId;
	}

	public void vCtRuleCodeBeforeCreation(RemoteWebDriver driver, String code)
			throws Exception {

		final String DB_URL = "jdbc:oracle:thin:@//172.17.4.101:1521/cleardb", USER = "hq", PASS = "hq123clear";
		driver.get(baseUrl + "/hq/local_raterules");
		if (isElementPresent(
				driver,
				By.xpath(getXpathByReplace(
						objectReposLocals.value("rateRule_id"), code)))) {

			rateId = driver.findElement(
					By.xpath(getXpathByReplace(
							objectReposLocals.value("rateRule_id"), code)))
					.getAttribute("href");
			StringBuffer sb = new StringBuffer(rateId);
			rateId = sb.substring(53, rateId.length());
			uploadRateRules(driver, "delete-coupon", code, rateId);
			deleteHQCoupon(USER, PASS, DB_URL,
					"DELETE from EXPRESSIONS where ID='" + rateId + "'");
			deleteHQCoupon(USER, PASS, DB_URL,
					"DELETE from RULE_MASTER where ID='" + rateId + "'");

		}

	}

	public void ctRuleCleanup(RemoteWebDriver driver,
			ArrayList<String> ctRateIds) {

		final String DB_URL = "jdbc:oracle:thin:@10.10.12.16:1521:cleardb", USER = "hq", PASS = "hq123clear";
		try {
			uploadRateRules(driver, "delete-coupon", CTINSTANT,
					ctRateIds.get(0));
			uploadRateRules(driver, "delete-coupon", CTINSTANT2ND,
					ctRateIds.get(1));

			for (String itr : ctRateIds) {
				deleteHQCoupon(USER, PASS, DB_URL,
						"DELETE from EXPRESSIONS where ID='" + itr + "'");
				deleteHQCoupon(USER, PASS, DB_URL,
						"DELETE from RULE_MASTER where ID='" + itr + "'");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void detailYouMightAlsoLike(RemoteWebDriver driver, String product)
			throws Exception {

		if (product.equalsIgnoreCase("ttd")) {
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_TTD_HomePage_LinkList"),
					campLocal.value("LocalCollectionName"));
			Thread.sleep(2000);
			if (elementVisible(driver,
					getObjectLocals("LocalListingUMightLikelist"), 10)) {

				((JavascriptExecutor) driver)
						.executeScript(
								"arguments[0].click();",
								driver.findElement(getObjectLocals("LocalListingUMightLikelist")));
			}
		} else if (product.equalsIgnoreCase("fnb")) {
			safeClickList(driver,
					getObjectLocals("LocalMobileWeb_TTD_HomePage_LinkList"),
					campLocal.value("LocalFnbCollectionName"));
			Thread.sleep(1000);
			((JavascriptExecutor) driver)
					.executeScript(
							"arguments[0].click();",
							driver.findElement(getObjectLocals("LocalListingUMightLikelist")));

		}
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,800)", "");
		Thread.sleep(1000);
		if (elementVisible(driver, getObjectLocals("LocalDetailPageSEO"), 10)) {

			Reporter.log("Actvity details page SEO present", true);
		} else {
			Reporter.log("Actvity details page SEO not present", true);
			Assert.fail();
		}

	}

	public void localsPaymentProd(RemoteWebDriver driver, boolean MakePayment)
			throws Exception {
		if (!elementVisible(driver,
				getObjectLocals("LocalCom_PaymentPage_Button"), 10)) {
			Reporter.log("Payment Page is not displayed", true);
			Assert.assertTrue(false);
		}
		if (MakePayment == false) {
			elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Button"), 10);
			if (elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_NB_Tab"), 1)) {
				Reporter.log("NB option displayed", true);
				// Assert.assertTrue(false);
			}
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_NB_Tab"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_NB_DropDown"),
					"Bank of India");
			Thread.sleep(3000);
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));

			elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_BOI_Back_Button"),
					30, "NB page displayed");
		    Thread.sleep(3000);
			safeClick(driver,
					getObjectLocals("LocalCom_PaymentPage_BOI_Back_Button"));
			Thread.sleep(3000);
			// Adding Extra elemet to click on cancel page
			safeClick(
					driver,
					getObjectLocals("LocalCom_CCavenue_PaymentPage_Cancel_Link"));

			if (!textPresent(driver, "Oops, your payment didn’t work", 10)) {
				Reporter.log(
						"Oops, your payment didn’t work : message is not displayed",
						true);
				Assert.assertTrue(false);
			}
			elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Button"), 20);

		} else if (MakePayment == true) {
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_CC_Tab"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_Number"),
					campLocal.value("CC_MasterCard_Number"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Exp_Month"),
					campLocal.value("CC_MasterCard_Exp_Month"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Exp_Year"),
					campLocal.value("CC_MasterCard_Exp_Year"));
			safeType(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_BillName"),
					campLocal.value("MasterCard_HolderName"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_CVV"),
					campLocal.value("CC_MasterCard_CVV"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,300)", "");
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
			waitElement(driver, By.xpath("//input[@value='payerAuth']"), 30);
			safeClick(driver, By.xpath("//input[@value='payerAuth']"));
			safeClick(driver, By.id("submitBtn"));
			safeType(driver, By.id("txtPassword"), "ctHDFCpl!");
			safeClick(driver, By.id("cmdSubmit"));
			locals_Payment_ConfirmationPage(driver, "", "CC");
		}
	}

	public void localsPaymentProdAe(RemoteWebDriver driver, boolean MakePayment)
			throws Exception {
		if (!elementVisible(driver,
				getObjectLocals("LocalCom_PaymentPage_Button"), 10)) {
			Reporter.log("Payment Page is not displayed", true);
			Assert.assertTrue(false);
		}
		if (MakePayment == false) {
			elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Button"), 10);
			if (elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Tab"), 1)) {
				Reporter.log("Credit card option displayed", true);
				// Assert.assertTrue(false);
			}
			
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_CC_Tab"));
		
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_Number"),
				             	campLocal.value("CC_MasterCard_Number_Prod"));
		
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_Number"),
	             	campLocal.value("CC_MasterCard_Number_Prod"));
/*		if(driver.findElement(By.xpath("//*[@id='creditCardNumberDisp']")).getText()!="5329610000004480")
			{
				driver.findElement(By.xpath("//*[@id='creditCardNumberDisp']")).sendKeys("5329610000004480");
				
			}*/
		
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Exp_Month"),
					campLocal.value("CC_MasterCard_Exp_Month_Prod"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Exp_Year"),
					campLocal.value("CC_MasterCard_Exp_Year_Prod"));
			safeType(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_BillName"),
					campLocal.value("MasterCard_HolderName_Prod"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_CVV"),
					campLocal.value("CC_MasterCard_CVV_Prod"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,300)", "");
			Thread.sleep(3000);
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));

			safeClick(driver,
					getObjectLocals("LocalCom_PaymentPage_Cancel_Link"));
			driver.switchTo().alert().accept();
			// Text message was order
			if (!textPresent(driver, "Oops, your payment didn’t work", 10)) {
				Reporter.log(
						"Oops, your payment didn’t work: message is not displayed",
						true);
				Assert.assertTrue(false);
			}
			elementVisible(driver,
					getObjectLocals("LocalCom_PaymentPage_Button"), 20);

		} else if (MakePayment == true) {
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_CC_Tab"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_Number"),
					campLocal.value("CC_MasterCard_Number"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Exp_Month"),
					campLocal.value("CC_MasterCard_Exp_Month"));
			safeSelect(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_Exp_Year"),
					campLocal.value("CC_MasterCard_Exp_Year"));
			safeType(driver,
					getObjectLocals("LocalCom_PaymentPage_CC_BillName"),
					campLocal.value("MasterCard_HolderName"));
			safeType(driver, getObjectLocals("LocalCom_PaymentPage_CC_CVV"),
					campLocal.value("CC_MasterCard_CVV"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,300)", "");
			safeClick(driver, getObjectLocals("LocalCom_PaymentPage_Button"));
			waitElement(driver, By.xpath("//input[@value='payerAuth']"), 30);
			safeClick(driver, By.xpath("//input[@value='payerAuth']"));
			safeClick(driver, By.id("submitBtn"));
			safeType(driver, By.id("txtPassword"), "ctHDFCpl!");
			safeClick(driver, By.id("cmdSubmit"));
			locals_Payment_ConfirmationPage(driver, "", "CC");
		}
	}

	public void vInvoiceVerificationAE(RemoteWebDriver driver,
			HashMap<String, String> hm) {
		ArrayList<String> ids = new ArrayList<>();
		float convFee, price, vat, activityPrice, cashBck = 0, conFeeAftrCashBck;

		try {
			DecimalFormat df = new DecimalFormat("###.##");
			Thread.sleep(3000);
			driver.manage().deleteAllCookies();
			driver.get(baseUrl_AE);
			signIN(driver, campLocal.value("bookingEmailId"));
			driver.get(baseUrl_AE + "/account/trips/" + hm.get("id"));
			safeClick(driver, getObjectLocals("Local_Invoice"));
			Set<String> windowId = driver.getWindowHandles();
			Iterator<String> itr = windowId.iterator();
			while (itr.hasNext()) {
				ids.add(itr.next());
			}

			driver.switchTo().window(ids.get(1));
			Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText()
					.contains(hm.get("id")), hm.get("id") + " isnot present");
			Reporter.log(hm.get("id") + " is present in print invoice", true);
			convFee = Float.parseFloat(getText(driver, By
					.xpath(getXpathByReplace(
							objectReposLocals.value("Local_printInvoice"),
							"Convenience"))));
			vat = Float.parseFloat(getText(driver,
					By.xpath(getXpathByReplace(
							objectReposLocals.value("Local_printInvoice"),
							"VAT @ 5 %"))));
			price = Float.parseFloat(getText(
					driver,
					By.xpath(getXpathByReplace(
							objectReposLocals.value("Local_printInvoice"),
							hm.get("ActivityName")))));

			// Convenience fee verification
			if (isElementPresent(driver, By.xpath(getXpathByReplace(
					objectReposLocals.value("Local_printInvoice"), "Cashback")))) {

				cashBck = Float.parseFloat(getText(driver, By
						.xpath(getXpathByReplace(
								objectReposLocals.value("Local_printInvoice"),
								"Cashback"))));

				conFeeAftrCashBck = (convFee - cashBck);

				if (conFeeAftrCashBck < 0) {
					Assert.assertTrue(
							convFee == Float.parseFloat(hm.get("Convenience")),
							"Convenience fees is not verified ,Actual convenience fee :"
									+ convFee);
					Reporter.log("Convenience fees is verified", true);
				}
			}
			// System.out.println((convFee-cashBck));
			// System.out.println(Float.parseFloat(df.format(Float.parseFloat(hm.get("Convenience"))/1.05)));
			Assert.assertTrue((convFee - cashBck) == Float.parseFloat(df
					.format(Float.parseFloat(hm.get("Convenience")) / 1.05)),
					"Convenience fees is not verified ,Actual convenience fee :"
							+ convFee);
			Reporter.log("Convenience fees is verified", true);

			// activity price verification
			activityPrice = Float.parseFloat(df.format(((Float.parseFloat(hm
					.get("ActivityPrice")) * (Float.parseFloat(hm
					.get("headCount")))))));
			if (hm.get("ActivityName").contains("Inclusive")) {
				Assert.assertTrue(
						Float.parseFloat(df.format((activityPrice / 1.05))) == price,
						"Activity price isn't verified,current price is showing "
								+ activityPrice);
				Reporter.log("Activity price is verified", true);

				// Vat verification
				float diff = (Float
						.parseFloat(df.format(activityPrice - price)))
						+ (Float.parseFloat(hm.get("Convenience")) - convFee);
				Assert.assertTrue(diff == vat,
						"VAT isn't verified,current VAT is showing "
								+ activityPrice);
				Reporter.log("VAT is verified", true);

			} else {
				Assert.assertTrue((activityPrice / 1.05) == price,
						"Activity price isn't verified,current price is showing "
								+ activityPrice);
				Reporter.log("Activity price is verified", true);

				// Vat verification
				float diff = Float.parseFloat(df.format((price * .05)))
						+ Float.parseFloat(df.format((Float.parseFloat(hm
								.get("Convenience")) - convFee)));
				Assert.assertTrue(diff == vat,
						"VAT isn't verified,current VAT is showing "
								+ activityPrice);
				Reporter.log("VAT is verified", true);
			}
			driver.close();
			driver.switchTo().window(ids.get(0));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void vNdUpdateActivityPrice(RemoteWebDriver driver,
			String inclusiveActName, String mrpPrice, String marketPrice)
			throws Exception {
		CampActivities camp = new CampActivities();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		driver.get(campLocal.value("campQaAeUrl") + "/accounts/sign_in");
		camp.campActivities_SignIN(driver, "SA");
		driver.get(campLocal.value("campQaAeUrl") + "/activities");
		safeAutocomplete(driver, getObject("Camp_ManageData_activtyNameLoc"),
				getObject("AirCom_HomePage_From_Ajax"), inclusiveActName);
		safeClick(driver, getObjectLocals("camp_VariantPriceTab"));
		safeClick(driver, getObjectLocals("camp_editActivity_btn"));
		safeType(driver, getObject("Camp_FNB_adultMrp"), mrpPrice);
		safeType(
				driver,
				getObject("Camp_Activities_NewActivity_FormThree_PricingPerPerson_AdultMarketPrice"),
				marketPrice);
		safeClick(driver, By.xpath("//a[text()='Save']"));
		Thread.sleep(5000);
		waitElement(driver, getObjectLocals("Camp_save_header"), 6);
		Assert.assertTrue(
				getText(driver, getObjectLocals("Camp_save_header")).contains(
						"Activity Updated Successfully"),
				"Unable to save Activity Error:"
						+ getText(driver, getObjectLocals("Camp_save_header")));
		jse.executeScript("window.scrollBy(0,3000)", "");
		scrollToElement(driver, By.linkText("Publish"));
		safeClick(driver, By.linkText("Publish"));
		Thread.sleep(5000);
		waitElement(driver, By.xpath("//div[@ng-hide='closeNotice']/div"), 5);
		String SuccessMsg = getText(driver,
				By.xpath("//div[@ng-hide='closeNotice']/div"));
		Reporter.log(SuccessMsg, true);
		Assert.assertTrue(SuccessMsg.contains("Activity Published"), SuccessMsg);
	}

	public void vVatImplementation(RemoteWebDriver driver, String urlAeCity,
			String activityName, String marketPrice, String inclusiveActName)
			throws Exception {
		String actLocalPrice, tripId, convFeeCal;
		driver.get(urlAeCity);
		if (isElementPresent(driver, By.xpath(getXpathByReplace(
				objectReposLocals.value("local_AeVatActivity"), activityName))) == false) {
			Reporter.log("Vat Activity isnot present", true);
			Assert.fail();
		}
		Reporter.log(activityName + " activity is present", true);
		actLocalPrice = getText(driver, By.xpath(getXpathByReplace(
				objectReposLocals.value("local_AeVatPricing"), activityName)));
		StringBuffer sb = new StringBuffer(actLocalPrice);
		Assert.assertTrue(
				sb.substring(4, actLocalPrice.length()).equals(marketPrice),
				"In Local Ae pricing is showing " + actLocalPrice
						+ " But Actual Price of activity is " + marketPrice);
		Reporter.log("In Local Ae pricing is showing " + actLocalPrice
				+ " and Actual Price of activity in camp is " + marketPrice,
				true);
		safeClick(driver, By.xpath(getXpathByReplace(
				objectReposLocals.value("local_AeVatActivity"), activityName)));
		locals_BookPopUP(driver, "TTD", "AdultTime",
				Integer.parseInt(campLocal.value("HeadCount")));
		rateRuleItinerary(driver);
		safeClick(driver, getObjectLocals("local_fareBreakUp"));
		convFeeCal = String.valueOf(Integer.parseInt(campLocal
				.value("HeadCount"))
				* Integer.parseInt((campLocal.value("convenienceFeeAE"))));
		Assert.assertTrue(getText(driver, getObjectLocals("local_fBreakCfee"))
				.contains(convFeeCal), "Convenience fee isn't showing AED"
				+ convFeeCal);
		Reporter.log("Convenience fee is showing AED" + convFeeCal, true);
		safeClick(driver, getObjectLocals("local_closeBtn"));
		safeClick(driver, getObjectLocals("LocalCom_ItineraryPage_Button"));
		Thread.sleep(7000);
		locals_PaymentPage(driver, "CC");
		tripId = locals_Payment_ConfirmationPage(driver,
				"TTD Adult Timeslot Booking : ", "");
		HashMap<String, String> hm = new HashMap<>();
		hm.put("id", tripId);
		hm.put("ActivityName", inclusiveActName);
		hm.put("ActivityPrice", marketPrice);
		hm.put("Convenience", convFeeCal);
		hm.put("headCount", campLocal.value("HeadCount"));
		vInvoiceVerificationAE(driver, hm);
	}

	public void additionalInfoItinerary(RemoteWebDriver driver,
			String couponType) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id("check_saving")));

		String[] parts = null;
		logURL(driver);
		String cashBack = null;

		driver.findElement(
				By.xpath("//*[@id='additonalInfoDiv']/form/dl/dd/input"))
				.sendKeys("Test");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", "");

		if (couponType != null || !couponType.isEmpty()) {
			if (couponType.equalsIgnoreCase("Coupon")) {
				safeType(driver, getObjectLocals("LocalCoupon"),
						dataFile.value("Localinstcoupon"));
				safeClick(driver, getObjectLocals("Localapply"));
				if (!elementVisible(driver,
						getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg"),
						20)) {
					Reporter.log("Coupon is not wrking", true);
					Assert.assertTrue(false);
				}
				String CouponMsg = getText(driver,
						getObjectLocals("LocalCom_ItineraryPage_Coupon_Msg"));
				if (CouponMsg.contains("Great! You just saved ")) {
					parts = CouponMsg.split("[a-zA-Z]+");
					cashBack = parts[5];
					Reporter.log("Coupon Message displayed : " + CouponMsg,
							true);

				} else if (CouponMsg
						.contains("Sorry, the coupon code entered is invalid")) {
					Reporter.log("Coupon Message displayed : " + CouponMsg,
							true);
					Assert.assertTrue(false);
				}

			}
		}
		jse.executeScript("window.scrollBy(0,100)", "");

		driver.findElement(By.id("itineraryBtn1")).click();
	}

	public void additonalInfoTravelerStep(RemoteWebDriver driver)
			throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		WebElement additionalInfoList = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.id("nameTitle"))));
		if (additionalInfoList != null
				|| !additionalInfoList.toString().isEmpty()) {
			driver.findElement(By.id("travPassport Name1")).sendKeys(
					"Indian Passport");
			driver.findElement(By.id("travPassport Number1")).sendKeys(
					"asdf1234");
			WebElement dateOfBirth = driver.findElement(By
					.id("travDate of Birth1"));
			dateOfBirth.sendKeys("28/04/1986");
		}
		safeSelect(driver, getObjectLocals("LocalCom_ItineraryPage_Title"),
				dataFile.value("Title"));
		safeType(driver, getObjectLocals("FirstNameAdditionalInfo"),
				dataFile.value("First_Name_A1"));
		safeType(driver, getObjectLocals("LastNameAdditionalInfo"),
				dataFile.value("Last_Name_A1"));
		String emailText = driver.findElement(By.id("emailAdd")).getAttribute(
				"value");
		if (emailText.isEmpty()) {
			safeType(
					driver,
					getObjectLocals("LocalCom_ItineraryPage_AdditionalInfo_EmailId"),
					campLocal.value("bookingEmailId"));

		}
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_PhoneNo"),
				dataFile.value("LocalsPhoneNO"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", "");

		driver.findElement(By.id("travelersBtn")).click();

	}

	public void additonalInfoTravelerStepWithoutList(RemoteWebDriver driver)
			throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		WebElement additionalInfoList = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.id("nameTitle"))));

		safeSelect(driver, getObjectLocals("LocalCom_ItineraryPage_Title"),
				dataFile.value("Title"));
		safeType(driver, getObjectLocals("FirstNameAdditionalInfo"),
				dataFile.value("First_Name_A1"));
		safeType(driver, getObjectLocals("LastNameAdditionalInfo"),
				dataFile.value("Last_Name_A1"));
		String emailText = driver.findElement(By.id("emailAdd")).getAttribute(
				"value");
		if (emailText.isEmpty()) {
			safeType(
					driver,
					getObjectLocals("LocalCom_ItineraryPage_AdditionalInfo_EmailId"),
					campLocal.value("bookingEmailId"));

		}
		safeType(driver, getObjectLocals("LocalCom_ItineraryPage_PhoneNo"),
				dataFile.value("LocalsPhoneNO"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", "");

		driver.findElement(By.id("travelersBtn")).click();

	}

	public void agency_Ctauth_SignIn(RemoteWebDriver driver) throws Exception {
		driver.manage().deleteAllCookies();
		if (textPresent(driver,
				"an error occurred while processing this directive", 1)) {
			Reporter.log("an error occurred while processing this directive : Error is displayed");
			Assert.assertTrue(false);
		}
		if (elementVisible(driver,
				getObjectHotels("Agency_New_SignIN_EmailID"), 50)) {
			Reporter.log("Agency SignIn Page is not displayed");
		}
		if (common.value("host").contains("qa2")) {
			safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"),
					dataFile.value("Agency_Hotels_QA2_Username"));
			safeType(driver, getObjectHotels("Agency_New_SignIN_Password"),
					dataFile.value("Agency_Hotels_QA2_Password"));
		} else if (common.value("host").contains("hf")) {
			safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"),
					dataFile.value("Agency_Hotels_HF_Username"));
			safeType(driver, getObjectHotels("Agency_New_SignIN_Password"),
					dataFile.value("Agency_Hotels_HF_Password"));
		} else if (common.value("host").contains("www")) {
			safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"),
					dataFile.value("Agency_Hotels_Prod_Username"));
			safeType(driver, getObjectHotels("Agency_New_SignIN_Password"),
					dataFile.value("Agency_Hotels_Prod_Password"));
		} else if (common.value("host").contains("beta")) {
			safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"),
					dataFile.value("Agency_Beta_Username"));
			safeType(driver, getObjectHotels("Agency_New_SignIN_Password"),
					dataFile.value("Agency_Beta_Password"));
		} else if (common.value("host").contains("stg1")) {
			safeType(driver, getObjectHotels("Agency_New_SignIN_EmailID"),
					dataFile.value("Agency_Stg1_Username"));
			safeType(driver, getObjectHotels("Agency_New_SignIN_Password"),
					dataFile.value("Agency_Stg1_Password"));
		}
		safeClick(driver, getObjectHotels("Agency_New_SignIN_Button"));
	}




   public int collectioncount(RemoteWebDriver driver,String xpath)
   {
	   
	   List<WebElement> list=driver.findElements(By.xpath(xpath));
	   return list.size();
   }
   
   
   public ArrayList<String> FailedActivity=new ArrayList<String>();
   
   public void collectionclick( RemoteWebDriver driver,int TotalActivity) throws InterruptedException
   {
	   String collectionname =null;
	   
	   for(int i=1;i<=TotalActivity;i++)
	   {
		 WebDriverWait w = new WebDriverWait(driver,50);
		 String Actualcollectionname= driver.findElement(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']//ul/li["+i+"]/a")).getAttribute("data-collection-name");
		 System.out.println(Actualcollectionname); 
		 
	    	 if(i==1)
	    	 { JavascriptExecutor jse2 = (JavascriptExecutor) driver;
	    	 	jse2.executeScript("window.scrollBy(0,300)", "");
				  w.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']//ul/li["+i+"]")))));
				  
					 w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']/nav/ul/li["+i+"]")));
					 w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']/nav/ul/li["+i+"]")));
	    	 	Actions A = new Actions(driver);
	    	 	A.moveToElement(driver.findElement(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']//ul/li["+i+"]"))).build().perform();
	    	 //	Thread.sleep(10000);
	    	 }	//driver.findElement(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']//ul/li["+i+"]")).click();
	       	
		 
		 
			  w.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']//ul/li["+i+"]")))));
		  
			 w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']/nav/ul/li["+i+"]")));
			 w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']/nav/ul/li["+i+"]")));
			 w.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']/nav/ul/li["+i+"]"))))).click();
		  
		 
		// Thread.sleep(5000);
		// driver.findElement(By.xpath("//div[@class='container fourCols collectionList darkRow noTitle']//ul/li["+i+"]")).click(); 
			  if (i==8)
			  {   JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			    jse1.executeScript("window.scrollBy(0,300)", "");
			  } 
		 try{
			 
			 w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='localCollectionBanner__meta']/h2")));
			 w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='localCollectionBanner__meta']/h2")));
			 w.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//div[@class='localCollectionBanner__meta']/h2")))));
			
			 
			 collectionname=driver.findElement(By.xpath("//div[@class='localCollectionBanner__meta']/h2")).getText();
		 
		  Assert.assertEquals(Actualcollectionname,collectionname);
		  System.out.println("PASSED:- "+collectionname);
		 }
		 catch(AssertionError | ElementNotFoundException |ElementNotInteractableException a )
		 {
			 FailedActivity.add(collectionname);
			 System.out.println("Failed:-\t"+"Activty page not loaed"+collectionname);
		 }
		 
		 System.out.println("URL:-"+ driver.getCurrentUrl());
		 driver.navigate().back();
		 
		  if (i>=17)
		  {   JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		    jse1.executeScript("window.scrollBy(0,300)", "");
		  } 
		   
	   }
   }
   

}
