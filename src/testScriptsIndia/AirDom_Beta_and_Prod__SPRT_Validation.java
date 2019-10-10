package testScriptsIndia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
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

import domainServices.AirCommonMethod;

public class AirDom_Beta_and_Prod__SPRT_Validation extends AirCommonMethod {
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

	@org.testng.annotations.Test
	public void specialRoundTripRateChecker() throws Exception {

		int attempt = 0;
		int x=0;
		boolean checkDiscount=false;
		boolean ratesMatch = false;
		boolean flightCountFailure = true;
		String[] fromSet = { "del", "bom", "blr" };
		String[] toSet = { "bom", "blr", "goi" };
		//String[] fromSet = { "del"};
		//String[] toSet = { "bom"};
HashMap<String,String> hp=new HashMap<String,String>();
		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		do {
			int a=0;
			int b=0;
			x=0;
			srptCheck(driver,"beta",hp,fromSet[attempt],toSet[attempt],attempt);
			srptCheck(driver,"www",hp,fromSet[attempt],toSet[attempt],attempt);
//
			System.out.println(hp.get("prodtabssize"));
			Assert.assertEquals(hp.get("prodtabssize"),hp.get("betatabssize"),"tabs mismatch in Beta and Production");
			List<WebElement> we2=driver.findElements(By.xpath("//*[contains(@class,'comboItem')]"));
			for(int j1=1;j1<we2.size();j1++){
				b=j1+1;
				System.out.println("------------------------------------");
				System.out.println("prod tab highlighted amount--"+j1+"="+hp.get("prodtabhighlightedamount"+j1)+"  betatabhighlightedamount"+j1+"="+hp.get("betatabhighlightedamount"+j1));
				System.out.println(hp.get("prodtabhighlightedamount"+j1)+"  "+ hp.get("betatabhighlightedamount"+j1));
				System.out.println("prod onward flight number--"+hp.get("prodOnwardFlightNumber"+j1)+"  beta onward flight number--"+hp.get("betaOnwardFlightNumber"+j1));
				System.out.println("prod return flight number--"+hp.get("prodReturnFlightNumber"+j1)+"   beta return flight number--"+hp.get("betaReturnFlightNumber"+j1));
				Assert.assertEquals(hp.get("prodtabhighlightedamount"+j1),hp.get("betatabhighlightedamount"+j1),"mismatch in amount");
				Assert.assertEquals(hp.get("prodOnwardFlightNumber"+j1),hp.get("betaOnwardFlightNumber"+j1),"mismatch in onward flight number");
				Assert.assertEquals(hp.get("prodReturnFlightNumber"+j1),hp.get("betaReturnFlightNumber"+j1),"mismatch in return flight number");
				
				
			}
					
			hp.clear();
			attempt++;
		    } while (attempt < 3);
			}
			@AfterClass
			public void closeSelenium() throws Exception {
				// writeTripToFile(tripID);
			
				driver.close();
				driver.quit();
			}

			@AfterMethod(alwaysRun = true)
			public void takeScreenshot(ITestResult _result) throws Exception {
				screenshot(_result, driver);
				System.out.println("Test Case:" + _result.getMethod().getMethodName());
			}

}
