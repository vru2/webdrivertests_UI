package paymentsBento_com;

import static common.CachedProperties.instance;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import common.CachedProperties;
import paymentsUI_Air.PaymentUI_Common;

@Test
public class Mobile_PWA_ICICI_NB_Booking2  extends PaymentUI_Common {
	
	public CachedProperties common = instance();
	public RemoteWebDriver driver = null;
	
	@BeforeClass
	public void getMobileDriver() throws IOException, InterruptedException
	{
		driver=(RemoteWebDriver) getMobileDriver(driver);
	}

	@BeforeMethod
	public void openBrowser()
	{
		driver.get("https://qa2.cleartrip.com/");
		System.out.println("We are currently on the following URL" +driver.getCurrentUrl());
	}

	public void mobilePWA_NB_bookflow() throws Exception
	{		
		driver.findElement(By.xpath("//div[@class='Sticky Sticky--top']/div/div[1]")).click();
	}
	
	@AfterClass
	public void afterClass()
	{
		driver.quit();
	}
}