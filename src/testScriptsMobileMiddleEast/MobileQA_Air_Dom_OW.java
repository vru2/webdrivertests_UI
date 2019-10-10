

	
package testScriptsMobileMiddleEast;


	

	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import dataServices.MobileDataProvider;
	import domainServices.Mobile;

		public class MobileQA_Air_Dom_OW extends Mobile{
		public RemoteWebDriver driver;
		private String baseUrl;

	  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileQA_Dom_OW")
	  public void MobileQA_Dom_OW_12(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
		  driver.get(baseUrl);
		  mobileCom_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
		  mobileCom_Air_SRP_Oneway(driver, Carrier1, Carrier3, Carrier3);
		  mobileCom_Air_ReviewItineraryPage(driver, "");
		  mobileCom_Air_TravelerPage(driver);
		  if ((common.value("makePayment").equals("true"))) {
		  mobileCom_Air_MakePaymentPage(driver, "" ,"" , Trip_Logger_Msg); 
		  }
	  }

	  @BeforeClass
	  public void setUp() throws Exception {
		driver=getMobileDriver(driver);
		baseUrl = common.value("urlqa");
	  }

	  @AfterMethod(alwaysRun = true)
		 public void afterMethod(ITestResult _result) throws Exception {
		 	afterMethod(driver, _result);
		 }
	  
	  @AfterClass
	  public void tearDown() throws Exception {
		 // driver.quit();    
	  }

	}




