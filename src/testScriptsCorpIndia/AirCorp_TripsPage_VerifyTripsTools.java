package testScriptsCorpIndia;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_TripsPage_VerifyTripsTools extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "Bangalore", "Chennai", "19", "20", "1", "0", "0", "","lcc","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void Trips(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline,String flight_type,String PaymentType) throws Exception {

		driver.get(Corp_Url());//Q1708221034 Q1610190300
		corp_SignIn(driver);
		driver.get(Corp_Url()+"/trips/Q1703080908");// , Q1703280502, Q1610190238, Q1509100656 , Q1610190300, Q1611243156
		Thread.sleep(5000);
		
		assertTrue("Trips Page Has Not Loaded", elementPresent_Time(driver, By.linkText("Cancellations"), 15));
		}

	@Test(alwaysRun = true, dependsOnMethods = { "Trips" })
    public void Verify_PrintETicket() throws Exception {
    	
		
    	safeClick(driver, By.linkText("Print e-tickets"));
    	waitElement(driver, By.cssSelector("h1.first"), 10);
    	assertTrue("eTickets not opened", elementPresent(driver, By.linkText("Print this ticket")));
    	
    	driver.navigate().back();
    	elementPresent_Time(driver, By.linkText("Cancellations"), 15);
    	Reporter.log("Print eTickets Verified");
      	
    }
	
	@Test(alwaysRun = true, dependsOnMethods = { "Verify_PrintETicket" })
    public void Verify_SMSTripDetails() throws Exception {
    	
		elementPresent_Time(driver, By.id("smsTrip"), 5);
		 
		    driver.findElement(By.id("smsTrip")).click();
		    driver.findElement(By.id("mobile_num")).clear();
		    driver.findElement(By.id("mobile_num")).sendKeys("9986508905");
		    assertTrue(elementPresent(driver,By.cssSelector("#smsTripForm > input[type=\"submit\"]")));
		    driver.findElement(By.cssSelector("#smsTripForm > input[type=\"submit\"]")).click();
		    Thread.sleep(3000);
		    	Reporter.log("SMSTripDetails Verified");
  }
	
	@Test(alwaysRun = true, dependsOnMethods = { "Verify_SMSTripDetails" })
    public void Verify_EmailTripDetails() throws Exception {
		
			elementPresent_Time(driver, By.id("emailTrip"), 5);
		    
		    driver.findElement(By.id("emailTrip")).click();
		    driver.findElement(By.cssSelector("#emailTripForm > input[name=\"emailAddress\"]")).clear();
		    driver.findElement(By.cssSelector("#emailTripForm > input[name=\"emailAddress\"]")).sendKeys("satish.mandewalkar@cleartrip.com");
		    assertTrue(elementPresent(driver,By.cssSelector("#emailTripForm > input[type=\"submit\"]")));
		    driver.findElement(By.cssSelector("#emailTripForm > input[type=\"submit\"]")).click();
		    Thread.sleep(3000);
		    	Reporter.log("EmailTripDetails Verified");    	
	}
	
	@Test(alwaysRun = true, dependsOnMethods = { "Verify_EmailTripDetails" })
    public void Verify_EmailInvoice() throws Exception {
		elementPresent_Time(driver, By.id("emailInvoice"), 5);
		
	    driver.findElement(By.id("emailInvoice")).click();
	    driver.findElement(By.xpath("//form[@id='emailInvoiceForm']/input")).clear();
	    driver.findElement(By.xpath("//form[@id='emailInvoiceForm']/input")).sendKeys("smandewalkar@cleartrip.com");
	    assertTrue(elementPresent(driver,By.xpath("//form/input[@type='submit']")));
	    driver.findElement(By.xpath("//li[8]/div/form/input[@type='submit']")).click();
	    Thread.sleep(3000);
	    	Reporter.log("EmailInvoice Verified");    	
	}
	
	@Test(alwaysRun = true, dependsOnMethods = { "Verify_EmailInvoice" })
    public void Verify_EmailETicket() throws Exception {
		elementPresent_Time(driver, By.linkText("emailETicket"), 5);
		safeClick(driver, By.id("emailETicket"));
		elementNotVisible(driver, By.xpath("//form[@id='emailETicketForm']/input"), 5);
		safeType(driver, By.xpath("//form[@id='emailETicketForm']/input"), "satish.mandewalkar@cleartrip.com");
		assertTrue(elementPresent(driver,By.xpath("//input[@value='Submit']")));
		safeClick(driver, By.xpath("//input[@value='Submit']"));
		
		Thread.sleep(3000);
	    	Reporter.log("Email eTickets Verified");
	    	    	
	}	
	
	@Test(alwaysRun = true, dependsOnMethods = { "Verify_EmailETicket" })
	public void Verify_PrintInvoice_224() throws Exception {
		
		elementPresent_Time(driver, By.linkText("Print Invoice"), 5);
		String winHandleBefore = driver.getWindowHandle();
		
		safeClick(driver, By.linkText("Print Invoice"));
		Thread.sleep(5000);
		
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		driver.manage().window().maximize();
		Thread.sleep(3000);
		//System.out.println(getText(driver, By.cssSelector("h1")));
		assertTrue("PrintInvoice Failed", getText(driver, By.cssSelector("h1")).contains("Invoice No"));
		Reporter.log("PrintInvoice Verified");    	
    	driver.close();
    	Thread.sleep(3000);
    	driver.switchTo().window(winHandleBefore);

	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	
	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		//afterMethod(driver, _result);
	}*/

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		//driver.close();
		//driver.quit();
	}
}