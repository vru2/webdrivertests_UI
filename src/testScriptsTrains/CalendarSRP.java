package testScriptsTrains;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Trains;
//import junit.framework.Assert;
//import junit.framework.Assert;

public class CalendarSRP extends Trains {

	public RemoteWebDriver driver;
	private String baseUrl;

	// private String baseUrl;
	/*
	 * @DataProvider public static Object [ ][ ] Trains_Calendar() { return new
	 * Object [ ] [ ] { { "New Delhi","MAS","Sleeper (SL)"}};
	 * 
	 * };
	 */

	@DataProvider
	public static Object[][] B2cTrains_Pax_2Adults2Children() {
		return new Object[][] { { "NDLS", "MAS", "Sleeper (SL)", "15", "General", "Same", "G T Express", "2", "2", "0",
				"0", "CREDIT CARD", "B2C Trains HomePage SignIn General Booking with Adult=1",
				"Your Booking is confirmed", "Cancel" } };

	};

	@Test(dataProvider = "B2cTrains_Pax_2Adults2Children",alwaysRun = true)
	public void B2cTrains_CalendarAvailonSRP(String DStation, String AStaiton, String Class, String TDate,
			String Quota, String BoardStation, String TrainsName, String Adult1, String Child1, String SMen,
			String SWomen, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message,
			String IRCTC_Action) throws Exception {
		/*
		 * 
		 * @Test (dataProvider="Trains_Calendar") public void
		 * Trains_Calendar(String deptStation,String ArrivalStation, String
		 * Class) throws Exception{
		 */ 
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		B2cTrains_HomepageSignIn(driver);
		safeClick(driver,getObject("B2C_Home_TriansTab"));
		B2cTrains_HomepageSearchwithSignin(driver, DStation, AStaiton, Class, TDate, Adult1, Child1, SMen, SWomen);
        safeClick(driver,getObject("B2C_CalendarLink"));
        waitForElementVisibility(driver,getObject("B2C_Calendar"),20);
        elementPresent_log(driver,getObject("B2C_Calendar"),"Trains availability for",30);
        Reporter.log("Train avaialability calendar is dispalyed for selected stations");
        
//        if(elementPresent(driver,getObject("B2C_Calendar"))){
//        	System.out.println("Calendar is dispalyed");
//        } else{
//        	System.out.println("Calendar is not dispalyed");
//        	Assert.assertTrue(false);
//        }
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
