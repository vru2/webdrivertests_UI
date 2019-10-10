package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirIntl_OW_Loading_BaggageInfo extends AirCommonMethod {
	 
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"del","blr", "del","MAA"};
		String[] destination = {"dxb","lon","dxb","cmb"};
		return new Object[][] { { origin, destination, "Flights", "", "gds", "","", "Direct", "1", "1", "1",
				"credit card", false} };
	}
	
	@Test(dataProvider = "B2CAirOWLCC")
	public void airIntl_OW_Loading_BaggageInfo_200(String[] origin, String[] destin, String app, String tripType, String flight_type,
			String flightPreference,String flightclass, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {
		String message ="";
		boolean hbaggage=false;
		String checkIn="";
		int attempt = 0;
		String cabin="";
		Reporter.log(flightPreference + ":" + this.getClass() + " started",true);
		
			driver.get(baseUrl);
			
			airDom_OW_BusinessClass_Search(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,flightclass,1);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(),true);
			
//			System.out.println(driver.getCurrentUrl());
			
			if (elementPresent_Time(driver, getObject("AirCom_SRP_FlightsCount"), 60)){
				Reporter.log("Results avaiable",true);
			}else {
				refreshPage(driver);
				elementPresent_Time(driver, getObject("AirCom_SRP_FlightsCount"), 60);
			}
			waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
		if(elementPresent(driver,By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"),1)){
			driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a")).click();
			//driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a")).click();
			}
			mouseHover(driver, By.xpath("//form/section[2]/div[4]/div/nav/ul/li[1]")); // first result on SRP
			
			safeClick(driver, By.linkText("Baggage Information"));
			
			elementPresent_Time(driver, By.cssSelector("span.baggageValue"), 50);
			Thread.sleep(9000);
			if(elementPresent(driver,By.xpath("//ul/li/span[1]/strong"),1))
			{
				checkIn = getText(driver, By.xpath("//ul/li/span[1]/strong"));
				cabin = getText(driver, By.xpath("//ul/li/span[2]/strong"));
				Reporter.log("Check-In Baggage : " + checkIn,true);
				Reporter.log("Cabin Baggage : " + cabin,true);
				hbaggage = true;
			}
			if(elementPresent(driver,By.xpath("//ul[contains(@class,'baggageSummary')]/li[2]/ul/li"),1))
			{
				message=getText(driver,By.xpath("//ul[contains(@class,'baggageSummary')]/li[2]/ul/li"));
//	 System.out.println(message);
				Reporter.log("baggage message :"+message,true);
			}
			
			if(hbaggage)
			{
				assertTrue("CheckInBaggage Details are not displayed",checkIn.contains("/person"));
				assertTrue("CabinBaggage details are not displayed",cabin.contains("/person"));
			}
			else
			{
				assertTrue("Baggage details are not displayed",message.contains("aggage"));
			}
	
		
	}


	


@AfterClass(alwaysRun = true)
public void closeSelenium() throws Exception {
	driver.close();
	driver.quit();
}

@AfterMethod(alwaysRun = true)
public void afterMethod(ITestResult _result) throws Exception {
	afterMethod(driver, _result);
}



}
