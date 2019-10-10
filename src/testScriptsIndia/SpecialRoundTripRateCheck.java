package testScriptsIndia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class SpecialRoundTripRateCheck extends AirCommonMethod{
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
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

	@Test
	public void specialRoundTripRateChecker_167() throws Exception {

		int attempt = 0;
		int x=0;
		boolean checkDiscount=false;
		boolean ratesMatch = false;
		boolean flightCountFailure = true;
		String[] fromSet = { "bom", "bom", "blr" };
		String[] toSet = { "del", "blr", "goi" };
HashMap<String,String> hp=new HashMap<String,String>();
		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		do {
			int a=0;
			int b=0;
			x=0;
			driver.get(baseUrl);
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", "1", "0", "0", "", attempt);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl());
			System.out.println("Search URL for attempt is :" + driver.getCurrentUrl());
			flightCountFailure = waitForElement(driver,90,By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			
			List<WebElement> we2=driver.findElements(By.xpath("//*[contains(@class,'comboItem')]"));
			System.out.println("size=="+we2.size());
			for(int j=0;j<we2.size();j++){
				System.out.println(we2.get(j).getAttribute("data-actual-price"));
				b=j+1;
				System.out.println("tabhighlightedamount"+b);
				hp.put("tabhighlightedamount"+b,we2.get(j).getAttribute("data-actual-price"));
			}
			List<WebElement> splRndTabs = driver.findElements(By.xpath(".//*[@id='flightForm']/section[2]/div[2]/table/td"));
			for(int i=1;i<splRndTabs.size();i++){
				splRndTabs.get(i).click();
				Reporter.log("tab "+i+" selected");
				System.out.println("tab "+i+" selected");
				Thread.sleep(7000);
				hp.put("HighlghtedtotalAmount"+i,driver.findElement(By.xpath("//div[@class='row roundTripHead']/div[1]/h2")).getText());
				
				Reporter.log("highlighted amount="+hp.get("HighlghtedtotalAmount"+i));
				Reporter.log("tab highlighted amount=Rs."+hp.get("tabhighlightedamount"+i));
				System.out.println("highlighted amount="+hp.get("HighlghtedtotalAmount"+i));
				System.out.println("tab highlighted amount=Rs."+hp.get("tabhighlightedamount"+i));
				String highlight=hp.get("HighlghtedtotalAmount"+i).replaceAll("\\D+","");
			
				System.out.println(highlight);
				Assert.assertEquals(highlight,hp.get("tabhighlightedamount"+i).replaceAll("\\D+",""),"Assertion fails");
				
				
				
				
				
				
				//assertTrue();
				
				String totalAmount=driver.findElement(By.xpath("//div[@class='row roundTripHead']/div[1]/h2")).getText();
/*				
				if(isElementPresent(driver,By.xpath(".//*[@id='flightForm']/section[2]/div[3]/div[1]/p/small/span"))){
					
					String src=fromSet[attempt].toString().toUpperCase();
					String dest=toSet[attempt].toString().toUpperCase();
				//	System.out.println("//div[@data-fromto='"+src+"_"+dest+"']/nav/ul/li/div/label/table/tbody/tr[1]/th[1]/input");
				List<WebElement> we=driver.findElements(By.xpath("//div[@data-fromto='"+src+"_"+dest+"']/nav/ul/li/div/label/table/tbody/tr[1]/th[1]/input"));
				Test:for(int y=0;y<we.size();y++){
					String radiobuttonstatus=we.get(y).getAttribute("checked");
					//System.out.println(radiobuttonstatus);
					if(radiobuttonstatus.equalsIgnoreCase("true")){
						int z=y+1;
						//System.out.println("//div[@data-fromto='DEL_BLR']/nav/ul/li["+z+"]/div/label/table/tbody/tr[2]/td/span/span");
						String OnwardFlightNumber=driver.findElement(By.xpath("//div[@data-fromto='"+src+"_"+dest+"']/nav/ul/li["+z+"]/div/label/table/tbody/tr[2]/td/span/span")).getText();
						System.out.println("OnwardFlightNumber=="+OnwardFlightNumber);
						Reporter.log("OnwardFlightNumber=="+OnwardFlightNumber);
						break Test;
					}
					
				}
				List<WebElement> we1=driver.findElements(By.xpath("//div[@data-fromto='"+dest+"_"+src+"']/nav/ul/li/div/label/table/tbody/tr[1]/th[1]/input"));
				Test1:for(int y=0;y<we1.size();y++){
					String radiobuttonstatus=we1.get(y).getAttribute("checked");
					//System.out.println(radiobuttonstatus);
					if(radiobuttonstatus.equalsIgnoreCase("true")){
						int z=y+1;
					//	System.out.println("//div[@data-fromto='BLR_DEL']/nav/ul/li["+z+"]/div/label/table/tbody/tr[2]/td/span/span");
						String ReturnFlightNumber=driver.findElement(By.xpath("//div[@data-fromto='"+dest+"_"+src+"']/nav/ul/li["+z+"]/div/label/table/tbody/tr[2]/td/span/span")).getText();
						System.out.println("Return FlightNumber=="+ReturnFlightNumber);
						Reporter.log("Return FlightNumber=="+ReturnFlightNumber);;
						break Test1;
					}
					
				}
					
					
					checkDiscount=true;
					x++;
				}*/
		/*		
			//assertTrue(
						"Special round trip amount on selecting from combo tab doesn't match with all airlines tab. Error! \nCombo fare = "
								+  "\nAll airline fare = " + totalAmount
								+ ". For the airline - ", checkDiscount);*/
			}
			hp.clear();
			System.out.println("total tabs are="+splRndTabs.size()+"     discount tabs are="+x+"      for"+ driver.getCurrentUrl());
			Reporter.log("total tabs are="+splRndTabs.size()+"     discount tabs are="+x+"      for"+ driver.getCurrentUrl());
			
				attempt++;
			} while (attempt < 3);
		Assert.assertEquals(true,flightCountFailure);
			}
			@AfterClass
			public void closeSelenium() throws Exception {
				// writeTripToFile(tripID);
			
				driver.close();
				driver.quit();
			}

			@AfterMethod(alwaysRun = true)
			public void afterMethod(ITestResult _result) throws Exception {
				afterMethod(driver, _result);
			}

}
