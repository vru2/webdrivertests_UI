package testScriptsTrains;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Trains;

public class IRCTC_Username_Validationn extends Trains{
	public RemoteWebDriver driver;
	private String baseUrl;

	@DataProvider
	public static Object[][] B2cTrains_Pax_2Adults2Children() {
		return new Object[][] { { "NDLS", "MAS", "Sleeper (SL)", "15", "General", "Same", "G T Express", "2", "2", "0",
				"0", "CREDIT CARD", "B2C Trains HomePage SignIn General Booking with Adult=1",
				"Your Booking is confirmed", "Cancel" } };

	};

	@Test(dataProvider = "B2cTrains_Pax_2Adults2Children",alwaysRun = true)
	public void B2cTrains_IRCTC_Username_Validation(String DStation, String AStaiton, String Class, String TDate,
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
		B2cTrains_UnsignedSignIn(driver);
		safeClick(driver,getObject("B2C_Home_TriansTab"));
		B2cTrains_HomepageSearchwithIRCTCValidation(driver, DStation, AStaiton, Class, TDate, Adult1, Child1, SMen, SWomen);
		elementPresent_Time(driver,getObject("B2cTrains_SRP_totalTrains"),20);
		
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
