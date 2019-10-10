package testScriptsCorpAE;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Admin_AddDelete_Travellers extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@BeforeClass
	public void setUp() throws Exception {

		//driver = (RemoteWebDriver) getDriverFF(driver);
		driver=(RemoteWebDriver) getDriver(driver);
		 
		baseUrl = getBaseUrl(domain);
	}
	
	@DataProvider(name = "traveller_details")
    public Object [ ][ ] traveller_details() {
        return new Object [ ] [ ] { { "Mr", "Frank", "La", "frank.la@cleartrip.com" } };
    }

    @Test(dataProvider = "traveller_details")
    public void Corp_Adding_Traveller_471(String label, String fname, String lname, String emailID) throws Exception {
		driver.get(Corp_AE_Url());
		corp_SignIn(driver);
		textPresent(driver, "Fly anywhere. Fly everywhere.", 10);
		
		safeClick(driver, getObject("AirCorpCom_HomePage_Travellers_Tab"));
		elementVisible(driver, getObject("AirCorpCom_Traveller_Add_Traveller_Button"), 20);
			
		safeClick(driver, getObject("AirCorpCom_Traveller_Add_Traveller_Button"));
		textPresent(driver, "Add a new traveller", 10);
		safeSelect(driver, getObject("AirCorp_Traveller_Add_Traveller_Title_Dropdown"), label);
		
	    safeType(driver, getObject("AirCorp_Traveller_First_Name"), fname);
	    safeType(driver, getObject("AirCorp_Traveller_Last_Name"), lname);
	    safeType(driver, getObject("AirCorp_Traveller_Emailid"), emailID);
	    
	    safeClick(driver, getObject("AirCorp_Traveller_Save_Traveller_Button"));

	    elementVisible(driver, getObject("AirCorp_Traveller_EditPerson"), 10);
	    textPresent(driver, "You should fill out", 5);
	    
	    assertTrue("Not able to add traveller", elementPresent_Time(driver, getObject("AirCorp_Traveller_EditPerson"), 20));
        Thread.sleep(2000);      
    	
    }
    
    @Test(alwaysRun = true, dependsOnMethods = { "Corp_Adding_Traveller_471" },dataProvider = "traveller_details")
    public void Corp_Deleting_Traveller(String label, String fname, String lname, String emailID) throws Exception {
    	
    	safeClick(driver, getObject("AirCorpCom_HomePage_Travellers_Tab"));
    	//elementPresent_Time(driver, getObject("AirCorpCom_Traveller_Add_Traveller_Button"), 10);

    	elementVisible(driver, getObject("AirCorpCom_Traveller_Add_Traveller_Button"), 10);
    	Thread.sleep(2000);
    	//safeType(driver, getObject("AirCorp_Traveller_FirstName"), fname);
    	
    	safeAutocomplete(driver, getObject("AirCorp_Traveller_FirstName"), By.xpath("//body/ul/li"), fname);
    	Thread.sleep(2000);
    	
        safeClick(driver, By.linkText("Remove"));
     //   safeClick(driver, getObject("AirCorp_Traveller_Remove"));
        Thread.sleep(2000);
    	driver.switchTo().alert().accept();
    	Thread.sleep(1000);
    	   }
    	 
	@AfterMethod(alwaysRun = true)	
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}