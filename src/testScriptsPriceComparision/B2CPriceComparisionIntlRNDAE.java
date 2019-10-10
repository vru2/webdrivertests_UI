package testScriptsPriceComparision;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class B2CPriceComparisionIntlRNDAE extends AirCommonMethod {
	public RemoteWebDriver driver1 = null;
	public RemoteWebDriver driver2 = null;
	 ArrayList<String> betalist= new ArrayList<String>();
	  ArrayList<String> prodlist= new ArrayList<String>(); 
	  boolean flag=true;
	  int attempt = 0;
	  int attempt1 = 0;
String prodcount="";
String betacount="";
	boolean betaconnector=true;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String domain = "com";
	String[] fromSet = { "DEL", "BOM", "BOM" };
	String[] toSet = { "DXB", "MCT", "DXB" };

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
	@DataProvider(name = "PriceComparision")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"del"};
		String[] destination = {"dxb"};
		String[] origin1 = {"bom"};
		String[] destination1 = {"mct"};
		String[] origin2 = {"bom"};
		String[] destination2 = {"dxb"};
		return new Object[][] { { origin, destination},{ origin1, destination1},{ origin2, destination2} };
		/*String[] origin = { "DEL", "BOM", "BOM"};
		String[] destination = { "DXB", "MCT", "DXB"};
		return new Object[][] { { origin, destination} };*/
	}

	@Test(dataProvider = "PriceComparision")
	public void priceComparisionIntlRND(String[] origin,String[] destination) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		

		HashMap<String, String> betaInfo = null;
		HashMap<String, String> prodInfo = null;
		 flag=true;
		attempt=0;
       
        	
    
        	Reporter.log("retrying with different segments origin"+origin[attempt1]+"  :Destination"+destination[attempt1]);
    		//System.out.println("retrying with different segments origin"+origin[attempt1]+"  :Destination"+destination[attempt1]);
			//System.out.println("origin="+origin[attempt1]);
			//System.out.println("destination="+destination[attempt1]);
			GetSRPInfo betaClass = new GetSRPInfo("http://beta.cleartrip.ae", driver1,origin[attempt1],destination[attempt1]);
			Thread beta = new Thread(betaClass);
			beta.start();
			GetSRPInfo prodClass = new GetSRPInfo("http://www.cleartrip.ae", driver2,origin[attempt1],destination[attempt1]);
			Thread prod = new Thread(prodClass);
			prod.start();

			beta.join();
			if (betaClass.failureFlag) {
				//System.out.println("error in getting info in Beta, will try one more time.");
				Reporter.log("error in getting info in Beta, will try one more time.");
				
				betaClass = new GetSRPInfo("http://beta.cleartrip.ae", driver1,origin[attempt1],destination[attempt1]);
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
				
				prodClass = new GetSRPInfo("http://www.cleartrip.ae", driver2, origin[attempt1],destination[attempt1]);
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
					//System.out.println("due to values mismatch refreshing for 10 seconds and fetching flight count");
					Reporter.log("due to values mismatch refreshing for 10 seconds and fetching flight count");
					//System.out.println("enters in refresh-----------------------------------------------------------------------------------------------");
					driver1.navigate().refresh();
					driver2.navigate().refresh();
					Thread.sleep(10000);
				}
			prodInfo = prodClass.info;
			Set<String> infoList = betaInfo.keySet();
			for (String key : infoList) {
				//System.out.println("Beta: " + betaInfo.get(key) + " - Prod: " + prodInfo.get(key));
				betacount=betaInfo.get(key);
				prodcount=prodInfo.get(key);
				
				
				
				if(!betaInfo.get(key).equalsIgnoreCase(prodInfo.get(key))){
					
					flag=false;
					
				}
				if(attempt>=1){
					//System.out.println("prod val =" +(prodInfo.get(key)+"   beta value="+betaInfo.get(key)));
					 Reporter.log("prod val =" +(prodInfo.get(key)+"   beta value="+betaInfo.get(key)));
					assertTrue("Beta and prod values dont match for : " + key, betaInfo.get(key).equals(prodInfo.get(key)));
				}
				}
			//System.out.println("Beta: " + betacount + " - Prod: " + prodcount);
			Reporter.log("Beta: " + betacount + " - Prod: " + prodcount);

			attempt++;
		} while (!flag && attempt<2);
			
		//assertTrue("Beta and prod values dont match for :",betacount.equals(prodcount));
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
			//this.attempt = attempt;
			info = new HashMap<String, String>();
		}

		public void run() {
			try {
				
				driver.get(baseUrl);
				// safeClick(driver, getObject("AirSideAppButtonXPath"));
				airCom_HomepageSearch_RoundTrip(driver,origin,destination, "10", "12", "1", "1", "1", "", attempt);
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
			boolean sector1=false;
			
			driver.navigate().refresh();
			Thread.sleep(5000);
			if(waitElement(driver,By.xpath("//li[@class='addOnItem']/label/input"),10)){
			/*List<WebElement> we3=driver.findElements(By.xpath("//th/span"));
			for(int i=0;i<we3.size();i++){
				//System.out.println("texts in List"+we3.get(1).getAttribute("data-pr"));
				break;
			}*/
			//if(waitElement(driver,By.xpath("//li[@class='addOnItem']/label/input"), 5))
			try{
			//System.out.println("price==="+"AED"+driver.findElement(By.xpath("//div[@class='row legsContainer']/div/nav/ul/li[1]/table/tbody[2]/tr[1]/th[6]/span")).getAttribute("data-pr"));
				//if(we2.get(0).getText().contains("AED")){
					////System.out.println("flight price text============="+we2.get(i).getText());
					info.put("price", "AED"+driver.findElement(By.xpath("//div[@class='row legsContainer']/div/nav/ul/li[1]/table/tbody[2]/tr[1]/th[6]/span")).getAttribute("data-pr"));
			}
			catch(Exception e){
				//System.out.println("price in catch="+driver.findElement(By.xpath("//p[@class='displayFilterValue clearFix']/span[1]")).getText());
				
			}
					//break test1;
				//}
				
				
			//}                                           
Thread.sleep(5000);
	List<WebElement> we2=driver.findElements(By.xpath("//tr/td/ul/li"));
	try{
	//System.out.println("we2 size="+we2.size());
	we2.get(0).click();
	}
	catch(Exception e){
		//System.out.println("enters in catch");
		////System.out.println("link name==="+driver.findElement(By.xpath("//li[@class='listItem showTabs nonBundled ']/table[@class='resultUnit flightDetailsLink']/tbody[3]/tr[3]/td/ul/li[1]")).getText());
		driver.findElement(By.xpath(".//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/table/tbody[3]/tr[3]/td/ul/li[1]/a")).click();
	}
	/*for(int i=0;i<we3.size();i++){
		//System.out.println("checking for link="+we3.get(i).getText());
	}*/
	
	Thread.sleep(1000);
//}
			info.put("onwardFlightName",
					driver.findElement(By.xpath("//li[1]/div/ul/li[1]/div[1]/ul/li[1]/div[2]/span")).getText()
							.trim());// //*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/div/ul/li[1]/div[1]/ul/li[1]/div[2]/span
			info.put("returnFlightName",
					driver.findElement(By.xpath("//li[1]/div/ul/li[1]/div[2]/ul/li[1]/div[2]/span")).getText()
							.trim());////*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/div/ul/li[1]/div[2]/ul/li[1]/div[2]/span
			info.put("onwardFlightNo",
					driver.findElement(By.xpath("//li[1]/div/ul/li[1]/div[1]/ul/li[1]/div[2]/small[1]")).getText()
							.trim());////*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/div/ul/li[1]/div[1]/ul/li[1]/div[2]/small[1]
			info.put("returnFlightNo",
					driver.findElement(By.xpath("//li[1]/div/ul/li[1]/div[2]/ul/li[1]/div[2]/small[1]")).getText()
							.trim());////*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[1]/div/ul/li[1]/div[2]/ul/li[1]/div[2]/small[1]
			/*String flightText = driver.findElement(getObject("SRP_air_flightcount")).getText();
			flightText = flightText.substring(0, flightText.indexOf("o") - 1);*/
			List<WebElement> we=driver.findElements(By.xpath("//p/strong"));
			//System.out.println("we size="+we.size());
				//System.out.println("flight count"+we.get(2).getText());
				
				//String flightText = driver.findElement(By.xpath("//div[@id='ResultContainer_1_1']/section[2]/section/aside/div/p/strong")).getText();
				String flightText = we.get(2).getText();
				//System.out.println("flight text="+flightText);
				String[] flightText1 = flightText.split(" ");
				//System.out.println("------------"+flightText1[0]);
				String flight=flightText1[0];
			info.put("flightsCount", flightText);
			
		}
			
			
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
