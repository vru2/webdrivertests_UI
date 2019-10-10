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

public class MobileAE_Air_Intl_LCC_Airlines_Check extends Mobile{

	public RemoteWebDriver driver;
	private String baseUrl;
	int attempt=0;


	@Test(dataProvider = "MobilewebOWLCC")
  public void mobileAE_Air_Intl_LCC_Airlines_Check(String[] origin, String[] destin, String app, String tripType, String airlines,String adults, String children, String infants,String flightPreference) throws Exception {
	  driver.get(baseUrl);
	  String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		System.out.println("onward date="+onwarddate);
	  if(elementVisible(driver,By.xpath("//body/div/div/div/h3"),4)){
		  driver.findElement(By.id("english_select")).click();
		  
	  }
	  driver.get(driver.getCurrentUrl()+"/flights/international/results?childs=0&from="+origin[attempt]+"&infants=0&airline_codes=&rnd_one=O&dep_time=0&adults=1&class=Economy&mobile=true&depart_date="+onwarddate+"&carrier="+airlines+"&to="+destin[attempt]);
	  Assert.assertEquals(true,elementPresent(driver,By.xpath("//strong[text()='"+flightPreference+"']"),40));
	   }
  @DataProvider(name = "MobilewebOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		
		String origin6E [] = {"DEL"};
		String destination6E [] = {"DXB"};
		
		String originSG [] = {"DEL"};
		String destinationSG [] = {"DXB"};
		
		
		
	return new Object[][] { 
				
				{ origin6E, destination6E, "Flights", "OneWay", "6E", "1", "0", "0","IndiGo"},
				{ originSG, destinationSG, "Flights", "OneWay", "SG", "1", "0", "0","SpiceJet"}
				
		};
	}
  @BeforeClass
  public void setUp() throws Exception {
	  driver=getMobileDriver(driver);
	baseUrl = common.value("urlae");
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
