package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirDom_SRP_PriceCheck_LCC extends AirCommonMethod{
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
	String basefare;
	String taxesandfees;
	String total;
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void Dom_LCC_Airline_91(String[] origin, String[] destin, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

			
		boolean flightCountFailure = true;
		int attempt = 0;

		Reporter.log(flightPreference + ":" + this.getClass() + " started");
		System.out.println(flightPreference + ":" + this.getClass() + " started");
		
		
		do {
			driver.get(baseUrl);
			
			airCom_HomepageSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,1);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			
			
			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");   
				System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			List<WebElement> we1=driver.findElements(By.xpath("//span[@class='INR']"));
			List<WebElement> we=driver.findElements(By.xpath("//a[@linkidentifier='fare']"));
			we.get(0).click();
			
			List<WebElement> we3=driver.findElements(By.xpath("//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/div/ul/li[2]/div/div[2]/div/div/dl/dd"));
			String totaldisplayed=driver.findElement(By.xpath(".//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/table/tbody/tr[1]/th[6]/span")).getAttribute("data-pr");
			Reporter.log("Total displayed="+driver.findElement(By.xpath(".//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/table/tbody/tr[1]/th[6]/span")).getAttribute("data-pr"));
			System.out.println("Total displayed="+driver.findElement(By.xpath(".//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/table/tbody/tr[1]/th[6]/span")).getAttribute("data-pr"));
			//System.out.println("total displayed="+we1.get(0).getAttribute("data-pr"));
			try{
				 basefare=we3.get(0).getText().split("\\.")[1].replace(",","");
						}
			catch(Exception e){
			 basefare=we3.get(0).getText();
			}
			try{
				taxesandfees=we3.get(1).getText().split("\\.")[1].replace(",","");
							}
			catch(Exception e){
				 taxesandfees=we3.get(1).getText().split("\\.")[1];
				}
try{
	 total=we3.get(we3.size()-1).getText().split("\\.")[1].replace(",","");			
			}
			catch(Exception e){
				 total=we3.get(we3.size()-1).getText().split("\\.")[1];	
			}
Reporter.log("Basefare="+basefare+"  Taxes and Fees="+taxesandfees+"  Total in fare details="+total+"  total displayed="+totaldisplayed);
System.out.println("Basefare="+basefare+"  Taxes and Fees="+taxesandfees+"  Total in fare details="+total+"  total displayed="+totaldisplayed);
				int basefareandtaxes=Integer.parseInt(basefare)+Integer.parseInt(taxesandfees);
				String basefareandtax=Integer.toString(basefareandtaxes);
				Assert.assertEquals(basefareandtax,total,"basefare and taxes not equals to total");
				Assert.assertEquals(total,totaldisplayed,"total displayed in fare rules ="+total+"total displayed at Book button="+totaldisplayed+"   basefare="+basefare+"  Taxes and fees="+taxesandfees);
				//	Assert.assertEquals(actual, expected);
			assertTrue("DOM OW Flight Results are not displayed",elementPresent(driver, getObject("AirCom_SRP_Modify_Search_Button")));
			
		} while (!flightCountFailure && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));
	
		
	}


	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "del","MAA"};
		String[] destination = {"BOM","BLR"};
		return new Object[][] { { origin, destination, "Flights", "", "lcc", "", "Direct", "1", "0", "0",
				"credit card", false} };
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
