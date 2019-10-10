package testScriptsPriceComparision;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class B2CPriceComparisionDomOW extends AirCommonMethod {
	public RemoteWebDriver driver1 = null;
	public RemoteWebDriver driver2 = null;
	boolean flag=true;
	int attempt=0;
	int attempt1=0;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String domain = "com";
	String[] fromSet = { "del", "bom", "blr" };
	String[] toSet = { "bom", "blr", "goa" };
	String prodcount="";
	String betacount="";
	String origin1="";
	String destination1="";
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver1 = getDriver(driver1);
		if (driver1 == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		this.driver2 = getDriver(driver2);
		if (driver2 == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
	}
	@DataProvider(name = "priceComparision")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		/*String[] origin = { "del", "bom", "blr" };
		String[] destination ={ "bom", "blr", "goa" };*/
		String[] origin = {"del"};
		String[] destination = {"bom"};
		String[] origin1 = {"bom"};
		String[] destination1 = {"blr"};
		String[] origin2 = {"blr"};
		String[] destination2 = {"goa"};
		return new Object[][] { { origin, destination},{ origin1, destination1},{ origin2, destination2} };
	}
	/*@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDom() {
		String[] origin = { "del", "bom", "blr" };
		String[] destination = { "bom", "blr", "goa" };
		return new Object[][] { { origin, destination} };
									}
*/	@Test(dataProvider = "priceComparision")
	public void priceComparisionDomOW(String[] origin,String[] destination) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		/*//System.out.println(origin);
		//System.out.println(destination);*/

		HashMap<String, String> betaInfo = null;
		HashMap<String, String> prodInfo = null;

		
			 flag=true;
			 attempt=0;
			
			Reporter.log("retrying with different segments origin-"+origin[attempt1]+"  :Destination-"+destination[attempt1]);
    		//System.out.println("retrying with different segments origin-"+origin[attempt1]+"  :Destination-"+destination[attempt1]);
    		
			
			GetSRPInfo betaClass = new GetSRPInfo("http://beta.cleartrip.com", driver1, origin[attempt1],destination[attempt1]);
			Thread beta = new Thread(betaClass);
			beta.start();
			GetSRPInfo prodClass = new GetSRPInfo("http://www.cleartrip.com", driver2, origin[attempt1],destination[attempt1]);
			Thread prod = new Thread(prodClass);
			prod.start();

			beta.join();
			if (betaClass.failureFlag) {
				//System.out.println("error in getting info in Beta, will try one more time.");
				Reporter.log("error in getting info in Beta, will try one more time.");

				betaClass = new GetSRPInfo("http://beta.cleartrip.com", driver1,origin[attempt1],destination[attempt1]);
				beta = new Thread(betaClass);

				beta.start();
				beta.join();

				if (betaClass.failureFlag) {
					//System.out.println("Again error in getting info in Beta, for the second time.");
					Reporter.log("Again error in getting info in Beta, for the second time.");
					assertTrue("Failure!", false);
				}
			}
			betaInfo = betaClass.info;
			prod.join();
			if (prodClass.failureFlag) {
				//System.out.println("error in getting info in Prod, will try one more time.");
				Reporter.log("error in getting info in Prod, will try one more time.");
				
				prodClass = new GetSRPInfo("http://www.cleartrip.com", driver2,origin[attempt1],destination[attempt1]);
				prod = new Thread(prodClass);
				
				prod.start();
				prod.join();
				
				if (prodClass.failureFlag) {
					//System.out.println("Again error in getting info in Prod, for the second time.");
					Reporter.log("Again error in getting info in Prod, for the second time.");
					assertTrue("Failure!", false);
				}
			}
			do{
				if(attempt>0){
					//System.out.println("due to value mismatch refreshing for 10 seconds and fetching flight values");
					Reporter.log("due to count mismatch refreshing for 10 seconds and fetching flight values");
					////System.out.println("enters in refresh-----------------------------------------------------------------------------------------------");
					driver1.navigate().refresh();
					driver2.navigate().refresh();
					Thread.sleep(10000);
				}
			prodInfo = prodClass.info;
			Set<String> infoList = betaInfo.keySet();
			for (String key : infoList) {
				//System.out.println("Beta: " + betaInfo.get(key) + " - Prod: " + prodInfo.get(key));
				if(!betaInfo.get(key).equals(prodInfo.get(key))){
					Reporter.log("prod val =" +(prodInfo.get(key)+"   beta value="+betaInfo.get(key)));
					flag=false;
					
				}
				if(attempt>=1){
					
					assertTrue("Beta and prod values dont match for : " + key, betaInfo.get(key).equals(prodInfo.get(key)));
				}
				//assertTrue("Beta and prod values dont match for : " + key, betaInfo.get(key).equals(prodInfo.get(key)));
			}
			//System.out.println("Attempt no =" + attempt);
		
		Reporter.log("Test case " + this.getClass() + " passed successfully");
		//System.out.println("Test case " + this.getClass() + " passed successfully");
		attempt++;
		
	} while (!flag && attempt<2);
		
 

	}

	public class GetSRPInfo implements Runnable {

		String baseUrl = null;
		HashMap<String, String> info = null;
		public RemoteWebDriver driver = null;
		boolean flightCountFailure = true;
		boolean failureFlag = false;
		int attempt = 0;

		String origin="";
		String destination="";
	public GetSRPInfo(String url, RemoteWebDriver driver,String origin,String destination) {
			baseUrl = url;
			this.driver = driver;
			
			this.origin=origin;
			this.destination=destination;
			//System.out.println(origin);
    		//System.out.println(destination);
			
			info = new HashMap<String, String>();
		}

		public void run() {
			try {
				driver.get(baseUrl);
				//System.out.println("origin="+origin);
				//System.out.println("destination="+destination);
				// safeClick(driver, getObject("AirSideAppButtonXPath"));
				 Actions action = new Actions(driver);
				 action.sendKeys(Keys.ESCAPE);
				airCom_HomepageSearch_Oneway(driver, origin , destination, "10", "1", "1", "1", "", attempt);
				Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
				//System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
				
				flightCountFailure = checkFlightsCount1(driver);
				if (!flightCountFailure) {
					Reporter.log("No results found for this search. Making another attempt with same sector.");
					//System.out.println("No results found for this search. Making another attempt with same sector.");
					failureFlag = true;
					return;
				}
				// //System.out.println("In thread" + Thread.currentThread().getId());
				captureAllValues();
			} catch (Exception e) {
				Reporter.log("Exception thrown. " + e.getMessage() + " Error!");
				//System.out.println("Exception thrown. " + e.getMessage() + " Error!");
				failureFlag = true;
			}
		}

		void captureAllValues() throws Exception {
			driver.navigate().refresh();
			Thread.sleep(5000);
			if(waitElement(driver,By.xpath("//li[@class='allFlights']"),1)){
				safeClick(driver,By.xpath("//li[@class='allFlights']"));
			}
			Reporter.log("price=="+driver.findElement(By.xpath("//p[@class='displayStaticRange clearFix']/span[@class='fLeft']")).getText());
			//System.out.println("price=="+driver.findElement(By.xpath("//p[@class='displayStaticRange clearFix']/span[@class='fLeft']")).getText());
			List<WebElement> we1=driver.findElements(By.xpath("//th/small"));
			//for(int i=0;i<we1.size();i++){
				////System.out.println("text============="+we1.get(0).getText());
				info.put("flightName", we1.get(0).getText().trim());
			//}
				info.put("price", driver.findElement(By.xpath("//p[@class='displayStaticRange clearFix']/span[@class='fLeft']")).getText());
			try{
			List<WebElement> we3=driver.findElements(By.xpath("//tr/td/ul/li"));
			we3.get(0).click();
			}
			catch(Exception e){
				driver.findElement(By.xpath(".//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/table/tbody[3]/tr[3]/td/ul/li[1]/a")).click();
			}
			Thread.sleep(1000);
			List<WebElement> we4=driver.findElements(By.xpath("//div/small"));
			info.put("flightNo",we4.get(1).getText().trim());
		List<WebElement> we=driver.findElements(By.xpath("//p/strong"));
		//System.out.println("size=="+we.size());
			//System.out.println("flight count--"+we.get(2).getText());
			
			//String flightText = driver.findElement(By.xpath("//div[@id='ResultContainer_1_1']/section[2]/section/aside/div/p/strong")).getText();
			String flightText = we.get(2).getText();
			Reporter.log("flight text="+flightText);
			//System.out.println("flight text="+flightText);
			String[] flightText1 = flightText.split(" ");
			Reporter.log("------------"+flightText1[0]);
			//System.out.println("------------"+flightText1[0]);
			
			String flight=flightText1[0];
			info.put("flightsCount", flight);
		}
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		// writeTripToFile(tripID);
		driver1.close();
		driver1.quit();
		driver2.close();
		driver2.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver1);
		screenshot(_result, driver2);
		driver1.manage().deleteAllCookies();
		driver2.manage().deleteAllCookies();
	}
}
