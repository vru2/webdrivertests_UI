package testScriptsPriceComparision;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

public class B2CPriceComparisionIntlOWAE extends AirCommonMethod {
	public RemoteWebDriver driver1 = null;
	public RemoteWebDriver driver2 = null;
	boolean flag=false;
	int attempt1=0;
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

	@DataProvider(name = "priceComparision")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"del"};
		String[] destination = {"dxb"};
		String[] origin1 = {"bom"};
		String[] destination1 = {"mct"};
		String[] origin2 = {"bom"};
		String[] destination2 = {"dxb"};
		return new Object[][] { { origin, destination},{ origin1, destination1},{ origin2, destination2} };
		/*String[] origin = { "DEL", "BOM", "BOM" };
		String[] destination ={ "DXB", "MCT", "DXB" };
		return new Object[][] { { origin, destination} };*/
	}

	@Test(dataProvider = "priceComparision")
	public void priceComparisionIntlOW(String[] origin,String[] destination) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		//System.out.println("Test case " + this.getClass() + " started");

		int attempt = 0;

		HashMap<String, String> betaInfo = null;
		HashMap<String, String> prodInfo = null;

	
			flag=false;
			
			attempt=0;
			Reporter.log("retrying with different segments origin-"+origin[attempt1]+"  ,Destination-"+destination[attempt1]);
    		//System.out.println("retrying with different segments origin-"+origin[attempt1]+"  ,Destination-"+destination[attempt1]);
			GetSRPInfo betaClass = new GetSRPInfo("http://beta.cleartrip.ae", driver1, origin[attempt1],destination[attempt1]);
			Thread beta = new Thread(betaClass);
			beta.start();
			GetSRPInfo prodClass = new GetSRPInfo("http://www.cleartrip.ae", driver2,origin[attempt1],destination[attempt1]);
			Thread prod = new Thread(prodClass);
			prod.start();

			beta.join();
			if (betaClass.failureFlag) {
				//System.out.println("error in getting info in Beta, will try one more time.");
				Reporter.log("error in getting info in Beta, will try one more time.");
				
				betaClass = new GetSRPInfo("http://beta.cleartrip.ae", driver1, origin[attempt1],destination[attempt1]);
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
					//System.out.println("due to count mismatch refreshing for 10 seconds and fetching flight count");
					Reporter.log("due to count mismatch refreshing for 10 seconds and fetching flight count");
					
					driver1.navigate().refresh();
					driver2.navigate().refresh();
					Thread.sleep(10000);
				}
			prodInfo = prodClass.info;
			Set<String> infoList = betaInfo.keySet();
			test:for (String key : infoList) {
               //System.out.println("Beta: " + betaInfo.get(key) + " - Prod: " + prodInfo.get(key));
				
				if(betaInfo.get(key).equals(prodInfo.get(key))){
					flag=true;
					}
				else{
					
					flag=false;
			//System.out.println("attempt==="+attempt);
             if(attempt>=1){
            	 Reporter.log("prod val =" +(prodInfo.get(key)+"   beta value="+betaInfo.get(key)));
            	 assertTrue("Beta and prod values dont match for : " + key, betaInfo.get(key).equals(prodInfo.get(key)));
             }
             break test;
            	/* try{
            		   Assert.assertEquals(betaInfo.get(key),prodInfo.get(key));
            		} catch (AssertionError ae){
            		   //System.out.println("flights in prod "+ae.getMessage()+"in beta");
            		   Reporter.log("flights count  in prod "+ae.getMessage()+"in beta");
            		}
	            
					}
					break test;*/
				}
				//assertTrue("Beta and prod values dont match for : " + key, betaInfo.get(key).equals(prodInfo.get(key)));
			}
			////System.out.println("Attempt no " + attempt + " passed.");
			//System.out.println("flag status"+flag);
			attempt++;
			} while (!flag && attempt<2);
			
		Reporter.log("Test case " + this.getClass() + " passed successfully");
		//System.out.println("Test case " + this.getClass() + " passed successfully");
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
		public GetSRPInfo(String url, RemoteWebDriver driver, String origin,String destination) {
			baseUrl = url;
			this.driver = driver;
			this.attempt = attempt;
			this.origin=origin;
			this.destination=destination;
			info = new HashMap<String, String>();
		}

		public void run() {
			try {
				driver.get(baseUrl);
				// safeClick(driver, getObject("AirSideAppButtonXPath"));
				airCom_HomepageSearch_Oneway(driver,origin,destination, "10", "1", "1", "1", "", attempt);
				Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
				//System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
				driver.navigate().refresh();
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
			if(waitElement(driver,By.xpath("//li[@class='addOnItem']/label/input"), 5)){
				List<WebElement> we1=driver.findElements(By.xpath("//tr/th/small"));
				//test:for(int i=0;i<we1.size();i++){
				////System.out.println("text name="+we1.get(i).getText());
					////System.out.println("text name="+we1.get(0).getText());
				info.put("flightName", we1.get(0).getText());
			
				List<WebElement> we2=driver.findElements(By.xpath("//tr/th"));
				test1:	for(int i=0;i<we2.size();i++){
						////System.out.println("text name111="+we2.get(i).getText());
						if(we2.get(i).getText().contains("AED")){
							info.put("price", we2.get(i).getText().trim());
							break test1;
						}
					}
				List<WebElement> we3=driver.findElements(By.xpath("//tr/td/ul/li"));
				test:for(int i=0;i<we3.size();i++){
					////System.out.println("text name="+we3.get(i).getText());
					if(we3.get(i).getText().equalsIgnoreCase("Flight itinerary")){
						we3.get(i).click();
						break test;
					}
				
				}
				
			Thread.sleep(1000);
			List<WebElement> we4=driver.findElements(By.xpath("//div/small"));
			//test2:	for(int i=0;i<we3.size();i++){
				////System.out.println("text for flight no="+we3.get(1).getText());
				info.put("flightNo",we4.get(1).getText()
						.trim());
			
			List<WebElement> we=driver.findElements(By.xpath("//p/strong"));
			
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
