package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class Airdom_CT_Routed extends AirCommonMethod{

	 
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
boolean routed=false;
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot",true);
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void airdom_CT_Routed_180(String[] origin, String[] destin, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

			boolean flightfound=false;
		boolean flightCountFailure = true;
		int attempt = 0;

		Reporter.log(flightPreference + ":" + this.getClass() + " started",true);
		//System.out.println(flightPreference + ":" + this.getClass() + " started");
		
		
		do {
			driver.get(baseUrl);
			driver.navigate().refresh();
		
			airCom_HomepageSearch_Oneway2(driver, origin[attempt], destin[attempt],common.value("Days_to_add_for_CurrentDate"), adults, children, infants,flightPreference,1);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(),true);
			//System.out.println("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = waitForElement(driver,90,By.xpath("//button[@class='booking']"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				flightfound=false;
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				//continue;
			}
			else{
				flightfound=true;
			}
			
			routed=multiItineraryorctrouted1(driver);
			Reporter.log(driver.getCurrentUrl());
			System.out.println(driver.getCurrentUrl());
			/*if(elementPresent(driver,By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a"),1)){
				driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li[3]/a")).click();
				driver.findElement(By.xpath("//div[2]/section/div/div[2]/nav[2]/ul/li/a")).click();
				}
			
		 selectingStop(flightFilterType, driver,1);
			//selectingStop("",driver,1);
		 List<WebElement> we2=driver.findElements(By.xpath("//th/small"));
			//List<WebElement> we2=driver.findElements(By.xpath("//li[contains(@class,'listItem')]"));
			List<WebElement> we=driver.findElements(By.xpath("//a[@linkidentifier='itinerary']"));
			test:for(int i=0;i<we.size();i++){
								System.out.println("-----"+we2.get(i).getText());
				if(we2.get(i).getText().equalsIgnoreCase("Multiple carriers")){
					boolean lcc=false;
					boolean gds=false;

				we.get(i).click();
				
				List<WebElement> we1=driver.findElements(By.xpath("//span[@class='name']"));
				//System.out.println(we1.size());
				if(we1.size()>=2){
				for(int j=0;j<we1.size();j++){
					Thread.sleep(1000);
					System.out.println("flight name="+we1.get(j).getText());
					System.out.println("lcc="+isLCCFlight(driver,we1.get(j).getText()));
					if(isLCCFlight(driver,we1.get(j).getText())){
						lcc=true;
					}
					else{
						gds=true;
					}
					if(!isLCCFlight(driver,we1.get(j).getText())){
						gds=true;
					}
					System.out.println("----"+lcc+"---"+gds);
					if(lcc&&gds){
						flightfound=true;
						we2.get(i).findElement(By.xpath("./table/tbody[2]/tr[2]/td[3]/button")).click();
						break test;
					}
				}
				we.get(i).click();
				Thread.sleep(2000);
				//System.out.println(driver.getCurrentUrl());
				//Reporter.log(driver.getCurrentUrl());
				}
								}
				
			}*/
			
			
			
			
			/*System.out.println(driver.getCurrentUrl());
			String url=driver.getCurrentUrl();
			String url1=url.split("=")[3].split("&")[0];
			//System.out.println("----"+url1);
			List<WebElement> we=driver.findElements(By.xpath("//tbody[@class='calRender']/tr"));
			test:for(int i=0;i<we.size();i++){
				System.out.println("row id="+we.get(i).getAttribute("id"));
				List<WebElement> we1=we.get(i).findElements(By.xpath("./td"));
				for(int j=0;j<we1.size();j++){
					System.out.println("date name=="+we1.get(j).getAttribute("data-searchdate"));
					if(we1.get(j).getAttribute("class").contains("searchedOn")){
						we1.get(j).click();
						break test;
					}
					System.out.println("class name=="+we1.get(j).getAttribute("class"));
				}
			}
			String url2=driver.getCurrentUrl();
			String url3=url2.split("=")[3].split("&")[0];
			//System.out.println("----"+url3);
			//System.out.println(driver.getCurrentUrl());
	      Reporter.log("initial date="+url1+"date by selecting calender="+url3);
			Assert.assertEquals(url1,url3,"calender search failed");
			Thread.sleep(9000);
*/			
			//assertTrue("DOM OW Flight Results are not displayed",elementPresent(driver, getObject("AirCom_SRP_Modify_Search_Button")));
			//
			attempt++;
	} while (!routed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (routed)));
	
		
	}


	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"blr","cjb","cjb"};
		String[] destination = {"amd","bom","del"};
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
