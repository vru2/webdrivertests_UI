package testScriptsCHMM;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.CHMM;

public class CHMMCom_RackRate_SR_SR_12 extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;
	
	 @DataProvider(name = "CHMM_Hotel")
	    public Object [ ][ ] Hotel_domestic() throws Exception {
	        return new Object [ ] [ ] { { "Stream Valley Cottages", "Premium Cottage - Active"}};
	    }
	    
	
	 @Test(dataProvider = "CHMM_Hotel")
  public void CHMM_RackRate_SR_SR_GST12(String HotelName, String RoomType) throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  CHMM_SignIN(driver, "");
	  //CHMM_Add_Rate(driver, HotelName, RoomType, "Net");
	  CHMM_RackRate_AddRate(driver,HotelName,RoomType,"SRSR","Domestic",12);
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = CHMM_URL(driver, "com");
  }
  
  @AfterMethod (alwaysRun = false)
	public void afterMethod(ITestResult _result) throws Exception {
		//afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}
