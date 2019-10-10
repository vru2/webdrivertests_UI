package testScriptsIndiaHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class HotelCom_Shortlist_Unsigned extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;

	@Test
	public void HotelCom_Shortlist_Unsign_Select() throws Exception {
		String mainWindow = driver.getWindowHandle();
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		driver.get(hotelSrpUrl(driver, "Pune", "Maharashtra", "IN"));
		for (int i = 0; i <= 10; i++) {
			if (elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 1)) {
				break;
			} else {
				if (textPresent(driver, "Sorry, our system is acting up", 1)) {
					Reporter.log("Sorry, our system is acting up message is displayed in SRP");
					Assert.assertTrue(false);
				}
			}
		}
		String selectedCount = getText(driver,getObjectHotels("HotelCom_SRP_Your_Shortlist_Link_Count"));
		int count = Integer.parseInt(selectedCount);
		safeClick(driver, getObjectHotels("HotelCom_SRP_Total_Link"));
		Thread.sleep(2000);
	 
		if (elementVisible(driver, getObjectHotels("HotelCom_SRP_ToBe_Shortlisted_Link"), 5)) {
			safeClick(driver, getObjectHotels("HotelCom_SRP_ToBe_Shortlisted_Link"));
		}
		else {
			Reporter.log("shortlist option not shown for hotels");
			Assert.assertTrue(false);
		}
		selectedCount = getText(driver,getObjectHotels("HotelCom_SRP_Your_Shortlist_Link_Count"));
		count = Integer.parseInt(selectedCount);
		Thread.sleep(200);
		if (count == 0) {
			Reporter.log("Hotel is not added to shortlisted list");
			Assert.assertTrue(false);
		}
		else{
		safeClick(driver, getObjectHotels("HotelCom_SRP_Your_Shortlist_Link_Count"));
		hotelCom_Switchto_NextTab(driver);
		}
		// Checking if new login option is present in shortlist page for unsigned in user
		if (!elementVisible(driver, getObjectHotels("HotelCom_ShortlistPage_Modify_Button"), 20)) {
			Reporter.log("Shortlist page not loading for unsigned user");
			Assert.assertTrue(false);
		} 
		else {
			Reporter.log("Shortlist page loaded for unsigned user");
			safeClick(driver, getObjectHotels("HotelCom_SRP_Your_ShortlistPage_AccountLogin"));
		}
		//Signin at shortlist page
		Thread.sleep(1000);
		driver.switchTo().frame("modal_window");
		elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), "sonal.a@cleartrip.com");
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), "cleartrip");
		safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		Thread.sleep(7000);
		driver.switchTo().window(mainWindow);
		// Verifing Shortlist page Share
		safeClick(driver, getObjectHotels("HotelCom_ShortlistPage_Share_Button"));
		Thread.sleep(5000);
		driver.navigate().refresh();
		safeClick(driver, getObjectHotels("HotelCom_ShortlistPage_Share_Button"));
		safeClick(driver, getObjectHotels("HotelCom_ShortlistPage_Share_Copy_Button"));
		String shareUrl = getAttribute(driver, getObjectHotels("HotelCom_ShortlistPage_Share_Link"), "value");
		safeClick(driver, getObjectHotels("HotelCom_ShortlistPage_Share_Close"));
		Reporter.log("Share url: "+shareUrl);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl("com");
	}
	@AfterMethod (alwaysRun = true)
	   public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
	} 
	@AfterClass public void tearDown() throws Exception {
	browserClose(driver); 
	}
	 
}
