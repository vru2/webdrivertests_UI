package testScriptsMobile;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.AirCommonMethod;
import domainServices.Mobile;

public class MobileFlightxp extends  AirCommonMethod{

	public RemoteWebDriver driver;
	private String baseUrl;
	boolean flightCountFailure = true;
	int attempt = 0;
	boolean bookingPassed = false;
	boolean ExpPayment = false;
	
	@BeforeClass
	public void setUp() throws Exception 
	{
		driver=getMobileDriver(driver);
		//baseUrl = "https://qabook.flightxp.com/flights";
		baseUrl = "https://qa2.cleartrip.com";
	}
	
	@Test(dataProvider = "AirOneWayDomestic")
	public void mobileCom_OW_Dom(String[] origin, String[] destin,String flightPreference,String adults, String children,
			String infants,String Payment_Options, boolean insuranceRequired,String flight_type) throws Exception 
	{
		Reporter.log("Test Case : " + this.getClass() + " started.", true);
		driver.get(baseUrl);
		driver.get("https://qabook.flightxp.com/m/flights/results?from=DEL&to=BOM&depart_date=31/08/2018&adults=1&childs=0&infants=0&mobile=true&class=Economy");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[@class='item']")).click();
				
	mobilweb_FlightXpAir_ItineraryPage(driver);
		
	mobileWeb_FlightXpAir_TravellerStep(driver);
		Thread.sleep(15000);
		if ((common.value("makePayment").equals("true"))) {
		mobileweb_FlightXP_Payment(driver);
		}
		
		if(elementPresent(driver,By.xpath("//*[text()='Continue to mobile web']"),15)) {
			safeClick(driver,By.xpath("//*[text()='Continue to mobile web']"));
		}
		
		bookingPassed = mobileweb_FltXpAir_Confirmation(driver);
		Thread.sleep(20000);
		if (bookingPassed) {
			Reporter.log("Test case " + this.getClass() + " passed successfully", true);
			
		}
		
		Reporter.log("Test Case : " + this.getClass() + " completed.", true);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception 
	{
		afterMethod(driver, _result);
	}
	  
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception 
	{
		//driver.close();
	//	driver.quit();    	  
	}

	@DataProvider(name = "AirOneWayDomestic")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"DEL","MAA"};
		String[] destination = {"BOM","BLR"};
		return new Object[][] { { origin,destination,"","1","0","0", "CC",false,"lcc"} };
	}

}
