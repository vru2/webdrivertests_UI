	// Framework - Cleartrip Automation
// Author - Kiran Kumar


package testScriptsTrains;

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
import junit.framework.Assert;

public class Modify_Search extends Trains{
	
	public RemoteWebDriver driver;
	private String baseUrl;
	
	@DataProvider
	  public static Object [ ][ ] B2cTrains_Pax_1Adult() {
	      return new Object [ ] [ ] { { "SBC","MAS","Sleeper (SL)","20","General","Same","Sanghamitra Exp","1","0","0","0","CREDIT CARD","B2C Trains HomePage SignIn General Booking with Adult=1","Your Booking is confirmed","Cancel"}};
	     
	  };
	  
	@Test (dataProvider="B2cTrains_Pax_1Adult")  
	public void TrainsModifySearch(String DStation, String AStaiton, String Class, String TDate,String Quota,String BoardStation,String TrainsName,String Adult1,String Child1,String SMen,String SWomen,String Payment_Type, 
			String Logger_Msg, String Booking_Confirmation_Message , String IRCTC_Action) throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		B2cTrains_HomepageSignIn(driver);
		B2cTrains_HomepageSearchwithSignin(driver,DStation,AStaiton,Class,TDate,Adult1,Child1,SMen,SWomen);
		logURL(driver);
		elementPresent(driver, By.xpath("//strong"), 20);
		String homePageSearch = getText(driver, By.xpath("//strong"));
		safeClick(driver, By.id("rail_search_link"));
		safeAutocomplete_CHMM(driver, getObject("B2cTrains_HomePage_FromStation"), getObject("B2cTrains_HomePage_SearchAjax"), DStation);
		safeAutocomplete_CHMM(driver, getObject("B2cTrains_HomePage_ToStation"), getObject("B2cTrains_HomePage_SearchAjax"), "DLI");
		safeSelect(driver,getObject("B2cTrains_Class"),"First Class (FC)");
		selectCalendarDate(driver,getObject("B2cTrains_MonthClick"),getObject("B2cTrains_MonthNext"),1,TDate);
		B2cTrains_PaxEntry(driver, Adult1,Child1,SMen,SWomen);
		safeClick(driver, getObject("B2cTrains_SearchButton"));
		elementNotVisible(driver, getObject("B2cTrains_SearchButton"), 25);
		//Thread.sleep(15000);
		elementPresent(driver, By.xpath("//strong"), 20);
		logURL(driver);
		if(!logURL(driver).contains("class=FC")) {
			Reporter.log("URL doesnt contain class = FC");
			Assert.assertTrue(false);
		}
		String modifiedTrip = getText(driver, By.xpath("//strong"));
		Reporter.log("Home page Search is "+homePageSearch);
		Reporter.log("Modify text is : "+modifiedTrip);
		if(!(modifiedTrip.contains("Bangalore Cy Junction") && modifiedTrip.contains("Delhi"))) {
			Reporter.log("Modify text is : "+modifiedTrip);
			Assert.assertTrue(false);
		}
		String TodayDate = getText(driver, By.cssSelector("small.truncate.span.span24"));
		safeClick(driver, By.xpath("//nav[2]/ul/li[2]/a"));
		Thread.sleep(2000);
		String TomorrowDate = getText(driver, By.cssSelector("small.truncate.span.span24"));
		safeClick(driver, By.xpath("//nav[2]/ul/li[1]/a"));
		Thread.sleep(5000);
		String YesterdayDate = getText(driver, By.cssSelector("small.truncate.span.span24"));
		String[] TodaysDate = TodayDate.split(" ");
		TodayDate = TodaysDate[1];
		String[] TomorrowsDate = TomorrowDate.split(" ");
		TomorrowDate = TomorrowsDate[1];
		String[] YesterdaysDate = YesterdayDate.split(" ");
		YesterdayDate = YesterdaysDate[1];
		if(TodayDate.equals(TomorrowDate)) {
			Reporter.log("NextDay button not working");
			Assert.assertTrue(false);
		}if(!TodayDate.equals(YesterdayDate)) {
			Reporter.log("PrevDay button not working");
			Assert.assertTrue(false);
		}
		Reporter.log("NextPage & Previous Page link works ");
	}
	
	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
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