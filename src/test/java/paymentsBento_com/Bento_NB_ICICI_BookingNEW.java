package test.java.  paymentsBento_com;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_NB_ICICI_BookingNEW extends PaymentUI_Common_Bento {
	String payURL ="";

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void bento_nb() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		driver.manage().deleteAllCookies();
		driver.navigate().to(searchurl);
		Reporter.log(searchurl);
		Searchpagebook(driver,"");
	    book_itnnew(driver,"");
	    if(elementVisible(driver,getObjectPayment("Bento_Payment_PayText"),30)) {
			payURL = driver.getCurrentUrl();
	   }
	   else if(textPresent(driver,"Sorry, our servers are stumped with your request",30)||textPresent(driver,"Flight not available",30))
	    {
	    	Reporter.log("Booking failed due to itn page issue");
	    	assertTrue(false);
	    }
		 browserClose(driver);
	    
	}

	@Test (dependsOnMethods="bento_nb")
	public void bento_nb_PWA() throws Exception {
		driver=(RemoteWebDriver) getMobileDriver(driver);
		driver.manage().deleteAllCookies();
		driver.navigate().to(payURL);
		Reporter.log(payURL);
		payUI_Select_PaymentType_PWA(driver, "NET BANKING");
		payUI_Enter_PaymentDetails_PWA(driver, "NET BANKING", "ICICI Bank");
		safeClick(driver, By.xpath("//button"));
		elementVisible(driver, By.cssSelector("button.success"), 10);
		textPresent(driver, "Razorpay Bank Welcome to Razorpay Software Private Ltd Bank", 5);
		safeClick(driver, By.cssSelector("button.success"));
		elementVisible(driver, By.xpath("//div[@id='root']/div/div/div/ul/li/div/h5"), 30);
		String tripID= getText(driver, By.xpath("//div[@id='root']/div/div/div/ul/li[3]/p[2]"));
		System.out.println("tripID : "+tripID);
		browserClose(driver);	    
	}
	
		@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}

}
