package testScriptsCorpAE;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class AirCorp_Dom_OW_Refundable extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	
	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "DEL", "BOM", "19", "20", "1", "0", "0", "","lcc","DA" } };
	}

	@Test(dataProvider = "AirCorp")
	public void airCorpDom_Refundable(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String Payment_Type) throws Exception {
		driver.get(Corp_AE_Url());
		corp_AE_SignIn(driver);
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		//corpAir_Oneway_Search(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens, Infants, Pref_Airline);	
			if(!elementVisible(driver, getObject("SRP_air_flightcount"), 90)){
				elementVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 10);
				elementNotVisible(driver, getObject("Air_Agency_SRP_Page_Loader"), 50);
			}
			for(int i=0;i<=20; i++){
				if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Oneway_BookButton"), 1)){
					break;
				}
				else if(elementPresent_Time(driver, getObject("Air_Agency_SRP_Flights_Research"), 1)){
					break;
				}
				Thread.sleep(1000);
			}
		if(!elementVisible(driver, By.id("1_1_refundable"), 10)) {
			Reporter.log("Refundable flights are not displayed for the search");
			Assert.assertTrue(false);			
		}
		safeClick(driver, By.id("1_1_refundable"));
		if(textPresent(driver, "We couldn't find flights to match your filters", 5)) {
			Reporter.log("We couldn't find flights to match your filters : Text is displayed");
			Assert.assertTrue(false);
		}
		mouseHover(driver, By.xpath("//li/table/tbody/tr[2]/td"));
	//	mouseHoverClick(driver, By.linkText("Fare rules"));
		safeClick(driver, By.xpath("//a[contains(text(),'Fare rules')]"));
		if(!elementVisible(driver, By.xpath("//h1/span"), 20)) {
			Reporter.log("Refundable text is not displayed in  Itinerary details");
			Assert.assertTrue(false);
		}
		Thread.sleep(2000);
	
		safeClick(driver, getObject("Air_Agency_SRP_Oneway_BookButton"));
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		AirCorp_Paymentpage(driver, Payment_Type, "COUPON", "Corp Dom OW Refundable : ");
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}