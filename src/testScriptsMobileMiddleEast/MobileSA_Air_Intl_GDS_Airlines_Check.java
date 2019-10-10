package testScriptsMobileMiddleEast;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Mobile;

public class MobileSA_Air_Intl_GDS_Airlines_Check extends Mobile{




	public RemoteWebDriver driver;
	private String baseUrl;
	int attempt=0;


	@Test(dataProvider = "MobilewebOWGDS")
  public void mobileSA_Air_Intl_GDS_Airlines_Check(String[] origin, String[] destin, String app, String tripType, String airlines,String adults, String children, String infants,String flightPreference) throws Exception {
	  driver.get(baseUrl);
	  String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		System.out.println("onward date="+onwarddate);
	  if(elementVisible(driver,By.xpath("//body/div/div/div/h3"),4)){
		  driver.findElement(By.id("english_select")).click();
		  
	  }	
	 // driver.get(driver.getCurrentUrl()+"/flights/international/results?rnd_one=O&from="+origin[attempt]+"&to="+destin[attempt]+"&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&mobile=true&class=Economy&carrier=AI&dep_time=0&airline_codes=");
	  driver.get(driver.getCurrentUrl()+"/flights/international/results?childs=0&from="+origin[attempt]+"&infants=0&airline_codes=&rnd_one=O&dep_time=0&adults=1&class=Economy&mobile=true&depart_date="+onwarddate+"&carrier="+airlines+"&to="+destin[attempt]);
	 /* JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.xpath("//strong[text()='"+flightPreference+"']"));
		js1.executeScript("arguments[0].scrollIntoView(true);", element1);*/
	  
	 System.out.println("//strong[text()='"+flightPreference+"']");
	  Assert.assertEquals(true,elementPresent(driver,By.xpath("//strong[text()='"+flightPreference+"']"),40));
	   }
	@DataProvider(name = "MobilewebOWGDS")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		
		String originAI[] = {"DEL"};
		String destinationAI [] = {"DXB"};
		
		String origin9W[] = {"DEL"};
		String destination9W [] = {"DXB"};
		
		String originEY [] = {"DEL"};
		String destinationEY [] = {"DXB"};
		
		
		
	return new Object[][] { 
		{ originAI, destinationAI, "Flights", "OneWay", "AI", "1", "0", "0","Air India"},
		{ originEY, destinationEY, "Flights", "OneWay","EY", "1", "0", "0","Etihad Airways"},
		{ origin9W, destination9W, "Flights", "OneWay", "9W", "1", "0", "0","Jet Airways"}
				
		};
	}
  @BeforeClass
  public void setUp() throws Exception {
	  driver=getMobileDriver(driver);
	baseUrl = common.value("urlsa");
  }

  @AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	afterMethod(driver, _result);
	 }
  
  @AfterClass
  public void tearDown() throws Exception {
	  driver.quit();    
  }





}
