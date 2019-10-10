// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.CorporateHotels;

public class HotelCorp_Connector_Prod_Trust extends CorporateHotels {
	public RemoteWebDriver driver;

	@Test(dataProviderClass = CorporateDataProvider.class, dataProvider = "HotelCorp")
	public void CorpHotel_Trust_Connector_NB(String City, String CheckIn_Date, String CheckOut_Date, String Rooms,
			String Adult1, String Adult2, String Adult3, String Adult4, String Child1, String Child2, String ChildAge1,
			String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg,
			String Booking_Confirmation_Message) throws Exception {
		String HotelB2B_Trust = null;
		if (ProductionUrl) {
			HotelB2B_Trust = dataFile.value("HotelB2B_Trust_Prod");
		} else {
			HotelB2B_Trust = dataFile.value("HotelB2B_Trust");
		}
		driver.get(Corp_Url());
		corp_SignIn(driver);
		corpHotel_HomePageSearch(driver, "Mumbai", CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4,
				Child1, Child2, ChildAge1, ChildAge2);
		corpHotel_SRP(driver, HotelB2B_Trust, "");
		corpHotel_ItineraryPage(driver);
		corpHotel_TravelerPage(driver);
		corpHotel_PaymentPage(driver, "NETBANKINGCITI", "", "Corp.com trust Connector NB  : ", Booking_Confirmation_Message);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
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