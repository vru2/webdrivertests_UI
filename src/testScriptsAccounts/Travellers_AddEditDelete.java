package testScriptsAccounts;

import static org.testng.Assert.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class Travellers_AddEditDelete extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";

	
	@Test(groups="Regression",alwaysRun = true)
	public void accountTravellersAddEditDelete_79() throws Exception {

		driver.get(baseUrl);
		if (!checkIfSignedIn(driver)) {
			if(ProductionUrl){
				driver.navigate().to(baseUrl+"/signin");
				Accounts_HomepageSignIn_Prod(driver);
				driver.executeScript("location.reload()");
			}else{
			Accounts_HomepageSignIn(driver);
			driver.executeScript("location.reload()");
		}
		}
		

		boolean b2cAccountPage = getAccountsPage(driver);
		if (b2cAccountPage == false) {
			Reporter.log("Error in the flow. Accounts page not loading!");
		}
		safeClick(driver, getObject("Acc_Travellers_Tab"));
		Thread.sleep(3000);
		elementPresent(driver, getObject("Acc_Traveller_AddTravellerButton"), 40);
		if (waitElement(driver, getObject("Acc_Traveller_AddTravellerButton"), 1))
		{

			driver.executeScript("location.reload()");
	Thread.sleep(2000);
			if (driver.findElement(By.xpath("//*[@id='traveller_name_block_label']/h2")).getText().equals("Mstr. Ashith Reddy")) 
			{
				
				safeClick(driver, getObject("Acc_Traveller_DeleteTravellerButton"));
				Thread.sleep(1000);
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				assertEquals("Ashith Reddy has been deleted.×",driver.findElement(getObject("Acc_Traveller_NewTravellersMsg"))
						.getText());
				
			}
			
//Add traveler
			driver.findElement(getObject("Acc_Traveller_AddTravellerButton")).click();
			Thread.sleep(100);
			safeSelect(driver, getObject("Acc_Traveller_TravellerTitle"), "Mstr");
			safeType(driver, getObject("Acc_Traveller_TravellerFName"), "Ashith");
			safeType(driver, getObject("Acc_Traveller_TravellerLName"), "Reddy");
			String s=RandomStringUtils.randomAlphanumeric(2);
			safeType(driver, By.xpath(".//*[@id='traveller_user_nick_name']"), s+"NICK");
			//driver.findElement(By.xpath(".//*[@id='traveller_user_nick_name']")).sendKeys("NICK");
			safeSelect(driver, getObject("Acc_Country_Code"), "India +91");
			driver.findElementById("mobile_number").sendKeys("1234567890");
			safeClick(driver, getObject("Acc_Traveller_SaveTravellerButton"));
			textPresent_Log(driver, "Great, you've added Ashith Reddy", 10);
			
			driver.findElement(By.xpath("//a[contains(text(),'Edit')]")).click();
	//Edit		
			safeSelect(driver, getObject("Acc_Traveller_TravellerTitle"), "Mstr");
			safeType(driver, getObject("Acc_Traveller_TravellerFName"), "Ashith");
			String s1=RandomStringUtils.randomAlphanumeric(2);
			safeType(driver, By.xpath(".//*[@id='traveller_user_nick_name']"), s1+"NICK");			
			safeSelect(driver, getObject("Acc_Country_Code"), "India +91");
			driver.findElementById("mobile_number").clear();
			Thread.sleep(2000);
			driver.findElementById("mobile_number").sendKeys("1234567890");
			
			safeClick(driver, getObject("Acc_Traveller_SaveTravellerButton"));
			textPresent_Log(driver, "Great, your changes have been saved.", 10);
			
		}
		else
		{
			Reporter.log("Edit traveler got failed!");
			Assert.assertTrue(false);
		}
	}

	 @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
	  }
	  
	  @AfterMethod (alwaysRun = true)
	  public void afterMethod(ITestResult _result) throws Exception {
		 afterMethod(driver, _result);
	  }
	  
	  @AfterClass
	  public void tearDown() throws Exception {
		  browserClose(driver);
	  }
}
