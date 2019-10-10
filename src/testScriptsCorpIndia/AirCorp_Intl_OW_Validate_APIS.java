package testScriptsCorpIndia;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class AirCorp_Intl_OW_Validate_APIS extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	
	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "JFK", "LON", "20", "24", "1", "0", "0", "British Airways","","" } };
	}

	@Test(dataProvider = "AirCorp")
	public void airCorpIntl_Validate_APIS_244(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String Payment_Type) throws Exception {
		driver.get(Corp_Url());
		corp_SignIn(driver);
		corpAir_HomePage_Search(driver, "ONEWAY", FromCity, ToCity, Adults, Childrens, Infants, Pref_Airline, 0);
		elementVisible(driver, getObject("SRP_air_flightcount"), 50);
		/*for(int i=2; i<=10; i++) {
			String flightNameXpath = "//div[5]/div/nav/ul/li["+i+"]/label/span";
			String flightNameOnlyXpath = "//li["+i+"]/label/a";
			String flightName = getText(driver, By.xpath(flightNameXpath));
			if(flightName.contains("Air India")) {
				mouseHover(driver, By.xpath(flightNameXpath));
				safeClick(driver, By.xpath(flightNameOnlyXpath));
				break;
			}
		}*/
		
		
		List<WebElement> flightName = driver.findElements(By.xpath("//div[6]/div/nav/ul/li[2]/label/span"));
		List<WebElement> flightOnly = driver.findElements(By.xpath("//label/a"));
		for (int i = 0; i < flightName.size(); i++) {
			String text = flightName.get(i).getText();
			int j = i+2;
			if(text.contains("British Airways")){
				mouseHover(driver, By.xpath("//div[6]/div/nav/ul/li["+j+"]/label/span"));
				Thread.sleep(2000);
				flightOnly.get(i).click();
				break;
			}
		}
		
		corpAir_SRP_Intl_Oneway(driver);
		corpAir_ItineraryPage(driver);
		corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);		
		String tripID=AirCorp_Paymentpage(driver, "CC", "", "Corp Intl OW APIS : ");	
		System.out.println(tripID);
		driver.get("https://demo.cleartripforbusiness.com/trips/"+tripID);
		elementPresent(driver,By.linkText("Fill APIS information"));
	 	corpAir_Confirmation_APIS(driver);

	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}