package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
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

public class AirIntl_SRP_PriceCheck_GDS extends AirCommonMethod {


	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
	boolean warningFound = false;

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@Test(dataProvider = "B2CAirOWGDS")
	public void airIntl_SRP_PriceCheck_GDS_199(String[] origin, String[] destin, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {
		int z=0;
			HashMap<String,String> hp=new HashMap<String,String>();
			String total=null;
			boolean flightCountFailure = true;
			int attempt = 0;

			Reporter.log(flightPreference + ":" + this.getClass() + " started", true);
		
		do {
			driver.get(baseUrl);
			
			//airCom_HomepageSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,common.value("gdsairline"),1);
			driver.get("https://www.cleartrip.com/flights/international/results?from=DEL&to=DXB&depart_date=10/09/2018&adults=1&childs=0&infants=0&class=Economy&airline=Air+India+(AI)&carrier=AI&intl=y&sd=1535437401541");
			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
			
			//flightCountFailure = checkFlightsCount1(driver);
			flightCountFailure =waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");   
				System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			warningFound = filterFlightsByLCCOrGDS2(driver, flight_type, 0);
			if (warningFound) {
				attempt++;
				continue;
			}
			List<WebElement> we1=driver.findElements(By.xpath("//span[@class='INR']"));
			List<WebElement> we=driver.findElements(By.xpath("//a[@linkidentifier='fare']"));
			we.get(0).click();
		
		List<WebElement> we3=driver.findElements(By.xpath("//*[@class='fareBreakup']/*[1]/dt"));
		for(int i=0;i<we3.size();i++) {
			System.out.println(i+1);
			int x=i+1;
			hp.put(we3.get(i).getText(),we3.get(i).findElement(By.xpath("../dd["+x+"]")).getText().trim());
			
		//String we3.get(i).findElement(By.xpath("./dt")).getText()=we3.get(i).findElement(By.xpath("./dd")).getText();
			}
		System.out.println("keys="+hp.keySet().toString());
		System.out.println("values="+hp.values().toString());
		String[] y=hp.keySet().toString().split(",");
		//System.out.println("length="+y.length);
				for(int a=0;a<y.length-1;a++) {
					if(hp.keySet().toString().split(",")[a].replace("[","").replace("]","").trim().equalsIgnoreCase("discount")) {
						System.out.println("---"+hp.get(hp.keySet().toString().split(",")[a].replace("[","").replace("]","").trim()));
						z=z-Integer.parseInt(hp.get(hp.keySet().toString().split(",")[a].replace("[","").replace("]","").trim()).substring(2).replace(",",""));
						 System.out.println("z="+z);	
					}
					else if(!hp.keySet().toString().split(",")[a].replace("[","").replace("]","").trim().equalsIgnoreCase("Total")){
						System.out.println(hp.keySet().toString().split(",")[a].replace("[","").replace("]",""));
					 z=z+Integer.parseInt(hp.get(hp.keySet().toString().split(",")[a].replace("[","").replace("]","").trim()).substring(1).replace(",",""));
					// System.out.println("z="+z);
					}
					
				}
				//System.out.println(hp.get("Total"));
				//System.out.println("======="+hp.keySet().toString().split(",")[y.length-1].replace("[","").replace("]","").substring(1).replace(",",""));
				//System.out.println(hp.get(hp.keySet().toString().split(",")[y.length-1].replace("[","").replace("]","").substring(1).replace(",","")));
				Assert.assertEquals(z,Integer.parseInt(hp.get("Total").substring(1).replace(",","")));
				assertTrue("DOM OW Flight Results are not displayed",elementPresent(driver, getObject("AirCom_SRP_Modify_Search_Button")));
		
		} while (!flightCountFailure && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (flightCountFailure)));

		
	}


	@DataProvider(name = "B2CAirOWGDS")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "del","MAA","blr"};
		String[] destination = {"DXB","Dxb","dxb"};
		return new Object[][] { { origin, destination, "Flights", "", "gds", "", "Direct", "1", "0", "0",
				"credit card", false} };
	}


@AfterClass(alwaysRun = true)
public void closeSelenium() throws Exception {
	//driver.close();
	//driver.quit();
}

@AfterMethod(alwaysRun = true)
public void afterMethod(ITestResult _result) throws Exception {
	afterMethod(driver, _result);
}


}