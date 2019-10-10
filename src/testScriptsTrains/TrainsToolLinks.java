package testScriptsTrains;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Trains;

public class TrainsToolLinks extends Trains{
	
	public RemoteWebDriver driver;
	private String baseUrl;
	@DataProvider
	public static Object[][] B2cTrains_Pax_2Adults2Children() {
		return new Object[][] { { "NDLS", "MAS", "Sleeper (SL)", "15", "General", "Same", "G T Express", "2", "2", "0",
				"0", "CREDIT CARD", "B2C Trains HomePage SignIn General Booking with Adult=1",
				"Your Booking is confirmed", "Cancel" } };
	};
	
	@Test(dataProvider = "B2cTrains_Pax_2Adults2Children",alwaysRun = true)
	public void B2cTrains_TrainsToolLinks(String DStation, String AStaiton, String Class, String TDate,
			String Quota, String BoardStation, String TrainsName, String Adult1, String Child1, String SMen,
			String SWomen, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message,
			String IRCTC_Action) throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		B2cTrains_HomepageSignIn(driver);
		safeClick(driver,getObject("B2C_Home_TriansTab"));
		Thread.sleep(2000);
		//Links on IRCTC Search Page
		safeClick(driver,getObjectPlatform("B2C_Trains_CheckPNR"));
		Thread.sleep(2000);
		modalWindow(driver);
		Thread.sleep(4000);
		textPresent_Log(driver,"Indian Railways PNR status",20);
		elementPresent_log(driver,By.id("get_status_button"),"Get Status Button",10);
		driver.navigate().to(baseUrl+"/trains");
		safeClick(driver, getObject("B2C_CalendarAvail"));
		modalWindow(driver);
		Thread.sleep(4000);
		textPresent_Log(driver,"IRCTC Indian Railways seat availability calendar",20);
		elementPresent_log(driver,getObject("B2C_CalendarAvail_Get"),"Get Availability Button",10);
		driver.navigate().to(baseUrl+"/trains");
		safeClick(driver,getObjectPlatform("B2C_Trains_CancelTrain"));
		modalWindow(driver);
		Thread.sleep(4000);
		textPresent_Log(driver,"How To Cancel Trains Online",10);
		elementPresent_log(driver,By.xpath("//a[contains(text(),'Trains')]"),"Trains Link",10);
		driver.navigate().to(baseUrl+"/trains");
		safeClick(driver,getObjectPlatform("B2C_Trains_RailTourPackages"));
		modalWindow(driver);
		Thread.sleep(4000);
		if(ProductionUrl){
			elementPresent_log(driver,By.xpath(".//*[@id='IconNavList']/li[3]/a"),"Hotels Links",10);
			textPresent_Log(driver,"Package Details",10);
		}else{
		elementPresent_log(driver,By.xpath("//span[@id='packageStat']/button"),"Search Packages Button",20);
		}
		driver.navigate().to(baseUrl+"/trains");
		B2cTrains_HomepageSearchwithSignin(driver, DStation, AStaiton, Class, TDate, Adult1, Child1, SMen, SWomen);
		B2cTrains_CheckAvail(driver,Quota,BoardStation,TrainsName);
		// Links on BookStep
		safeClick(driver,By.xpath("(//a[contains(text(),'Booking Policy')])[2]"));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    Thread.sleep(2000);
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(2000);
		textPresent_Log(driver,"Cleartrip train booking policy",20);
		textPresent_Log(driver,"Boarding the train",5);
		driver.close();
	    driver.switchTo().window(tabs2.get(0));
	    safeClick(driver,By.xpath("//a[contains(text(),'Cleartrip‘s terms & conditions')]"));
	    modalWindow(driver);
		textPresent_Log(driver,"Cleartrip Terms of Service", 10);
		elementPresent_log(driver,By.cssSelector("span.productIcon.trains"),"Trains Link",10);
	}
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}
