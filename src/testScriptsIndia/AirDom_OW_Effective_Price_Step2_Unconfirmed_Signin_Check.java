package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import domainServices.AirCommonMethod;

public class AirDom_OW_Effective_Price_Step2_Unconfirmed_Signin_Check extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception 
	{
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() 
	{
		String[] origin = { "blr", "blr" };
		String[] destination = { "del", "maa" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "Direct", "1", "0", "0",
				"ctwallet", false } };
	}

	@BeforeMethod
	public void beforeMethod() 
	{
		timer();
	}
	
	@Test(groups = "Regression", dataProvider = "B2CAirOWLCC")
	public void airDom_OW_Effective_Price_Step2_Unconfirmed_Signin_Check_36454(String[] fromSet, String[] toSet, String app, String tripType,
			String flight_type, String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception 
	{

		Reporter.log("Test case " + this.getClass() + " started",true);
		boolean flightCountFailure = true;
		String walletMessage = "";
		String signInText = "";
		SoftAssert s = new SoftAssert();


		int attempt = 0;

		do 
		{
			driver.get(baseUrl);
			
			moneyToPratialWallet("PROMOTION", "CREDIT", "100");
			
			// Searching
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(),true);
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
				attempt++;
				moneyToPratialWallet("REDEEM", "DEBIT", "250");
				continue;
			}
			
			//Clicking on Book Button
			safeClick(driver, getObject("AirCom_SRP_Oneway_BookButton"));
			
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) 
			{
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt",true);
				//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				moneyToPratialWallet("REDEEM", "DEBIT", "250");
				continue;
			}
			
			Reporter.log(driver.getCurrentUrl(),true);
			
			//Adding Insurance details
			insuranceBlock(driver, insuranceRequired);
			
			//Unconfirmed Signin
			Thread.sleep(3000);
			
			safeType(driver, getObject("AirCom_BookStep2_EmailID_UserName"), "partialwallet@cleartrip.com");
			safeClick(driver, getObject("AirCom_BookStep2_Continue_Button"));
			Thread.sleep(5000);
			
			//Navigating back to Homepage
			driver.navigate().to(baseUrl);
			
			// Searching
			Thread.sleep(9000);
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(),true);
			
						
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
				attempt++;
				moneyToPratialWallet("REDEEM", "DEBIT", "250");
				continue;
			}
			
			moneyToPratialWallet("REDEEM", "DEBIT", "250");
			
			//Verifying Wallet Message
			Reporter.log("Verifying Wallet Message", true);
			if(elementPresent(driver, getObject("AirCom_SRP_Wallet_Bal_Message"), 10) && elementVisible(driver, getObject("AirCom_SRP_Wallet_Bal_Message"), 10))
			{
				walletMessage = getText(driver, getObject("AirCom_SRP_Wallet_Bal_Message"));
				s.assertTrue(walletMessage.equals("Looks like you have some money in your wallet. Sign in with ibrahim.shameem@cleartrip.com to use wallet"), "Wallet Message Mismatch!");
			}
			else
			{
				s.fail("Wallet Message not Visible!");
				s.assertAll();
			}
			
			//Verifying Sign in link
			Reporter.log("Verifying Sign in link", true);
			if(elementPresent(driver, getObject("AirCom_SRP_Wallet_Bal_Message_KnowMore_Link"), 10) && elementVisible(driver, getObject("AirCom_SRP_Wallet_Bal_Message_KnowMore_Link"), 10))
			{
				signInText = getText(driver, getObject("AirCom_SRP_Wallet_Bal_Message_KnowMore_Link"));
				s.assertTrue(signInText.equals("Sign in"), "Sign In Link text Mismatch!");
			}
			else
			{
				s.fail("Sign In text not Visible!");
			}
			
			//Verifying Close button
			Reporter.log("Verifying close button", true);
			if(!elementPresent(driver, getObject("AirCom_SRP_Wallet_Bal_Message_Close_Button"), 1) && !elementVisible(driver, getObject("AirCom_SRP_Wallet_Bal_Message_Close_Button"), 1))
			{
				s.fail("CloseButton not Available!");
			}
			
			s.assertAll();

			
			attempt++;

		} 
		while (!flightCountFailure && attempt < 2);
		assertTrue("Search failed after 2 attempts", ((attempt < 3) && (flightCountFailure)));
	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception
	{
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception 
	{
		afterMethod(driver, _result);
	}
}
