package testScriptsCorpIndia;

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

public class AirCorp_Admin_EditUpdateProfile extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	
	 @DataProvider(name = "traveller_details")
	    public Object [ ][ ] traveller_details() {
	        return new Object [ ] [ ] { { "Mr", "Frank", "La", "frank.la@cleartrip.com" } };
	    }

	    @Test(dataProvider = "traveller_details")
	    public void Corp_EditUpdate_MyProfile_262(String label, String fname, String lname, String emailID) throws Exception {
	    	driver.get(Corp_Url());
			corp_SignIn(driver);
			textPresent(driver, "Fly anywhere. Fly everywhere.", 10);
			safeClick(driver, getObject("AirCorpCom_HomePage_Travellers_Tab"));
			elementPresent_Time(driver, getObject("AirCorpCom_Traveller_Add_Traveller_Button"), 20);
			String PresentUser = getText(driver, By.xpath("//ul[@id='global']/li/strong")).split("@")[0];
			cancelEditUser(driver,PresentUser);
			String Edituser = "Test"+System.currentTimeMillis();
			editUser(driver,PresentUser, Edituser);			
			safeClick(driver, By.linkText("My profile"));
			elementPresent_Time(driver, By.linkText("Edit person"), 20);
		    safeClick(driver, By.linkText("Edit person"));
	        elementPresent_Time(driver, By.id("add_button"), 20);
	        safeType(driver, By.id("family-name"), "Corp");
	        safeClick(driver, By.id("add_button"));
	        elementPresent_Time(driver, By.xpath("//img[@alt='Add a new traveller']"), 20);
	        safeClick(driver, By.linkText("My profile"));
	        textPresent(driver, PresentUser, 10);
	        if (elementPresent_Time(driver, By.linkText("Edit person"), 20)){
		    	safeClick(driver, By.linkText("Edit person"));
		    	elementPresent_Time(driver, By.id("add_button"), 20);	
	        }
		    String familyName = driver.findElement(By.id("family-name")).getAttribute("value");
		    assertTrue(familyName.matches("Corp"));	    	
	    }
	    	    
	    @AfterMethod(alwaysRun = true)	
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
	    
	    @BeforeClass
    	public void setUp() throws Exception {
    		driver=(RemoteWebDriver) getDriver(driver);
    		}
    		    	
    	@AfterClass(alwaysRun = true)
    	public void tearDown() throws Exception {
    		browserClose(driver);
    	}   	
}