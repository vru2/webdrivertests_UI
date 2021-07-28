package payments_PWA_UI;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import paymentsUI.PaymentUI_CommonUtilities;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.IOUtils;
import org.testng.Reporter;

import common.WrapperMethod;
import junit.framework.Assert;

public class Mobile_PWA_Common extends WrapperMethod
{ 

	public RemoteWebDriver driver = null;
	static String url="https://qa2.cleartrip.com/";
	ChromeOptions options = null;


	public void pwahomepageseach(RemoteWebDriver driver) throws Exception {

		waitForElement(driver,20,By.xpath("//div[@class='Sticky Sticky--top']/div/div[1]"));
		if (elementPresent(driver, By.xpath("//*[text()='Depart']/parent::*/*[2]"), 10)){

			driver.findElement(By.xpath("//div[@class='Sticky Sticky--top']/div/div[1]")).click();
		}
		Thread.sleep(2000);					
		driver.findElement(By.xpath("//a/div[2]/h5[contains(text(),'Flights')]")).click();
		System.out.println("Clicked on flights");
		if (elementPresent(driver, By.xpath("//p[text()='From']"), 3)) {
			driver.findElement(By.xpath("//*[text()='From']/parent::*/*[2]")).click();
		}		
		driver.findElement(By.xpath("//div[@class='Sticky Sticky--top']/div[2]/div[2]/input")).sendKeys("Bangalore");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//li/p[1]")).click();
		Thread.sleep(1000);
		if (elementPresent(driver, By.xpath("//p[text()='To']"), 3)) {
			driver.findElement(By.xpath("//*[text()='To']/parent::*/*[2]")).click();
		}
		driver.findElement(By.xpath("//div[@class='Sticky Sticky--top']/div[2]/div[2]/input")).sendKeys("Mumbai");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li/p[1]")).click();
		Thread.sleep(1000);
		if (elementPresent(driver, By.xpath("//*[text()='Depart']/parent::*/*[2]"), 10)){

			driver.findElement(By.xpath("//*[text()='Depart']/parent::*/*[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='react-calendar__month-view'][2]/div/div/div/div[12]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//p[contains(text(),'Travellers')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//p[contains(text(),'Adult')]/..//../div[2]/*[3]/*")).click();
			driver.findElement(By.xpath("//p[contains(text(),'Adult')]/..//../div[2]/*[1]")).click();
			driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();

		}

	}

	public void pwa_srpflight_select(RemoteWebDriver driver) throws Exception {


		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/ul//div/li[@role='menuitem'][4]"))); 
		if 	(elementPresent(driver, By.xpath("//div/ul//div/li[@role='menuitem'][4]"),20))
		{
			driver.findElement(By.xpath("//div/ul//div/li[@role='menuitem'][3]")).click();
			Thread.sleep(2000);
		} 
		else 

		{
			Reporter.log("FlightSRP not loaded "); 
		}


		waitForElement(driver,20,By.xpath("//*[text()='Total']//..//../*[3]/*[2]"));
	}
	public void pwa_review_itinerary(RemoteWebDriver driver) throws Exception {

		waitForElement(driver,20,By.xpath("//*[text()='Total']//..//../*[3]/*[2]"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Total']//..//../*[3]/*[2]")).getText();
		waitForElement(driver, 5, By.xpath("//*[text()='Standard fare']"));		
		driver.findElement(By.xpath("//*[text()='Standard fare']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
		Thread.sleep(3000);
	}
	public void pwa_review_travellers(RemoteWebDriver driver) throws Exception {

		waitForElement(driver, 5, By.xpath("//span[@class='fs-15 fw-500']"));
		Select title = new Select(driver.findElement(By.name("title")));
		title.selectByVisibleText("Mr");
		Thread.sleep(1000);
		driver.findElement(By.name("firstName")).sendKeys("Test");
		Thread.sleep(1000);
		driver.findElement(By.name("lastName")).sendKeys("Test");
		if(elementPresent(driver, By.xpath("//label[contains(text(),'Nationality')]"),2))

		{

			driver.findElement(By.xpath("//label[contains(text(),'Nationality')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class='Sticky Sticky--top']/div[2]/div[2]/input")).sendKeys("India");
			driver.findElement(By.xpath("//li/p[1]")).click();
			Thread.sleep(1000);
		}
		else 
		{
			waitForElement(driver, 5, By.xpath("//input[@name='phone']"));	
		}


		if (elementPresent(driver, By.xpath("//input[@name='phone']"), 10)) {
			driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("1234567890");

		}

		if ((driver.findElement(By.xpath("//input[@name='email']")).getAttribute("disabled")) == null) {

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@gmail.com");
		}
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();

		Thread.sleep(6000);
	}
	public void pwa_select_Payment_option(RemoteWebDriver driver, String payType, String payType1, String cardType) throws Exception {

		if (payType.equalsIgnoreCase("NB")) {

			waitForElement(driver, 5, By.xpath("//p[contains(text(),'Payment')]"));
			Thread.sleep(4000);	
			if 	(elementPresent(driver, By.xpath("//p[contains(text(),'Payment')]"),20))
			{
				driver.findElement(By.xpath("//p[contains(text(),'Net banking')]")).click();
				Thread.sleep(2000);
			} 
			else 
			{
				Reporter.log("Payemnt page not loaded.Redirected to SRP "); 
			}
			driver.findElement(By.xpath("//button[contains(text(),'Choose another bank')]")).click();
			Thread.sleep(1000);
			waitForElement(driver, 10, By.xpath("//div[@class='pwa-bank--search-wrapper bg-white']"));
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='pwa-bank--search-wrapper bg-white']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@class='fs-3 cardFadeIn']")).sendKeys("ICICI Bank");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//p[contains(text(),'ICICI Bank')]")).click();
			List<WebElement> we = driver.findElements(By.xpath("//li/p"));
			for (int i = 1; i < we.size(); i++) {
				System.out.println("-------------------------------" + we.get(i).getText());
				if (we.get(i).getText().equalsIgnoreCase("ICICI Bank")) {

					we.get(i).click();

					break;
				}

				Thread.sleep(2000);
			}
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click();", driver.findElement(By.xpath("//p[contains(text(),'Pay now')]")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(),'Success')]")).click();
			waitForElement(driver, 50, By.xpath("//h5[contains(text(),'Booking confirmed')]"));
			Thread.sleep(5000);
			String url = driver.getCurrentUrl();
			System.out.println("---------" + url.split("itinerary")[1]);
		}
	}		
	private void waitForElement(RemoteWebDriver driver2, int i, By xpath) {
	}
	public void pwa_booking_confirmation_page(RemoteWebDriver driver, String payType, String Confirm_Msg, String LoggerMsg) throws Exception {

		if(driver.findElement(By.xpath("//h5[contains(text(),'Booking confirmed')]")).isDisplayed())
		{
			System.out.println("Payment is Success and booking is confirmed");
			Thread.sleep(4000);
			waitForElement(driver, 10, By.xpath("//ul/li[3][1]/p[2]"));
			WebElement  driver1 = driver.findElement(By.xpath("//ul/li[3][1]/p[2]"));
			String Tripref = driver1.getText();
			System.out.println("The Tripref is : "+Tripref);

		}
		else
		{
			System.out.println("payment failed.");
		}
	}


}
