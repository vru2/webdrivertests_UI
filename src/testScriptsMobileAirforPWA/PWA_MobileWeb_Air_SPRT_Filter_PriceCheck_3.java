package testScriptsMobileAirforPWA;

import java.sql.Driver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class PWA_MobileWeb_Air_SPRT_Filter_PriceCheck_3 extends Mobile{

	public RemoteWebDriver Driver;
	private String  baseUrl;
	String srpprice="";
	String paymentpage;
	boolean pgcheck=false;
	String pgfees;
	
	@Test (dataProviderClass = MobileDataProvider.class, dataProvider = "MobileCom_Dom_RT", groups= {" Smoke Test"})
	public void  pwa_MobileWeb_Air_SPRT_PriceCheck_40218(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
		
		System.out.println(baseUrl);
	driver.get(common.value("pwaairurl"));
	Reporter.log("PWA_MobileWeb_Air_Dom_OW",true);
	pwa_Air_Homepage(driver, FromCity, ToCity, From_Date, To_Date,  Adults, Childrens,Infants,"rt");
	checkSPRTPrice(driver);
	
	}
		
	
  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver3(driver);
	baseUrl = common.value("pwaairurl");
  }

 @AfterMethod(alwaysRun = true)
 public void afterMethod(ITestResult _Result) throws Exception{
	 afterMethod(driver, _Result);
 }
 
@AfterClass(alwaysRun = true)
public void tearDown() throws Exception{
	driver.quit();
}
	
	
	

}
