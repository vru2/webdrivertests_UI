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

public class AirCorp_Admin_AddDelete_Department extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	
	 @DataProvider(name = "dept")
	    public Object [ ][ ] traveller_details() {
	        return new Object [ ] [ ] { { "AutoDept","Auto" } };
	    }

	    @Test(dataProvider = "dept")
	    public void Corp_AddEditandDelete_Department_259(String deptname, String peopledept) throws Exception {
	    	driver.get(Corp_Url());
			corp_SignIn(driver);
			textPresent(driver, "Fly anywhere. Fly everywhere.", 10);
			
			safeClick(driver, getObject("AirCorpCom_HomePage_Travellers_Tab"));
			elementPresent_Time(driver, getObject("AirCorpCom_Traveller_Add_Traveller_Button"), 10);
			safeClick(driver, getObject("AirCorpCom_CompanySettings_Link"));
			textPresent(driver, "Account details & settings", 5);
			safeClick(driver, getObject("AirCorpCom_CompanySettings_Depts"));
			elementPresent_Time(driver, getObject("AirCorpCom_CompanySettings_AddDepts"), 10);
			
			safeClick(driver, getObject("AirCorpCom_CompanySettings_AddDepts"));
			textPresent(driver, "Add a department", 10);
			safeType(driver, getObject("AirCorpCom_CompanySettings_Depts_AddDept_Deptname"), deptname);
			safeAutocomplete(driver, getObject("AirCorpCom_CompanySettings_Depts_AddDept_PeopleDept"), getObject("AirCorp_InputTxt_Ajax"), peopledept);
			
			elementPresent_Time(driver, By.linkText("Remove"), 10);
			Thread.sleep(2000);
			safeClick(driver,getObject("AirCorpCom_CompanySettings_Depts_AddDept_SaveDept"));
			elementPresent_Time(driver, By.linkText("Delete"), 10);
			Reporter.log("Department Added Successfully");
			
			safeClick(driver, By.linkText("Delete"));
			Thread.sleep(1000);
			driver.switchTo().alert().accept();
			assertTrue("Dept Not Deleted", textPresent(driver, "Add your departments", 10));
			Reporter.log("Department Deleted Successfully");
			
	    }

		@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		}

		@AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
  
		@AfterClass(alwaysRun = true)
		public void tearDown() throws Exception {
		browserClose(driver);  
		}
}
