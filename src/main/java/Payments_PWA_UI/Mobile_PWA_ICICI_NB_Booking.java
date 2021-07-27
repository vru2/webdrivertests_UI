package Payments_PWA_UI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class Mobile_PWA_ICICI_NB_Booking 
{
	WebDriver driver;
	@BeforeClass
	public void getMobileDriver()
	{

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/exe/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-agent=Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; Microsoft; Lumia 640 XL LTE) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Mobile Safari/537.36 Edge/12.10166");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();


	}
	@BeforeMethod
	public void openBrowser()
	{
		driver.get("https://qa2.cleartrip.com/");
		System.out.println("We are currently on the following URL" +driver.getCurrentUrl());
	}

	public void mobilePWA_NB_bookflow() throws InterruptedException
	{
		driver.findElement(By.xpath("//div[@class='Sticky Sticky--top']/div/div[1]")).click();
		driver.findElement(By.xpath("//a/div[2]/h5[contains(text(),'Flights')]")).click();
		System.out.println("Clicked on flights");
		if (elementPresent(driver, By.xpath("//p[text()='From']"), 3)) {
			driver.findElement(By.xpath("//*[text()='From']/parent::*/*[2]")).click();
		}		
		driver.findElement(By.xpath("//div[@class='Sticky Sticky--top']/div[2]/div[2]/input")).sendKeys("Bangalore");
		Thread.sleep(1000);
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
			driver.findElement(By.xpath("//div[@class='react-calendar__month-view'][3]/div/div/div/div[12]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//p[contains(text(),'Travellers')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//p[contains(text(),'Adult')]/..//../div[2]/*[3]/*")).click();
			driver.findElement(By.xpath("//p[contains(text(),'Adult')]/..//../div[2]/*[1]")).click();
			driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();

			if (elementPresent(driver, By.xpath("//button[text()='Book']"),2)) {
				safeClick(driver, By.xpath("//button[text()='Book']"));
			} 
			else 

			{
				if (elementPresent(driver, By.xpath("//div/ul//div/li[@role='menuitem'][4]"), 4)) {
					driver.findElement(By.xpath("//div/ul//div/li[@role='menuitem'][2]")).click();
				}


				waitForElement(driver,20,By.xpath("//*[text()='Total']//..//../*[3]/*[2]"));

			}

			waitForElement(driver,20,By.xpath("//*[text()='Total']//..//../*[3]/*[2]"));
			driver.findElement(By.xpath("//*[text()='Total']//..//../*[3]/*[2]")).getText();
			waitForElement(driver, 5, By.xpath("//*[text()='Standard fare']"));		
			driver.findElement(By.xpath("//*[text()='Standard fare']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
			Thread.sleep(3000);
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
			waitForElement(driver, 5, By.xpath("//p[contains(text(),'Payment')]"));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//p[contains(text(),'Net banking')]")).click();
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
			//	safeType(driver, getObject("MobileWeb_Trains_AutocompleteR"), "ICICI Bank");
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
			waitForElement(driver, 10, By.xpath("//h5[contains(text(),'Booking confirmed')]"));
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

	private boolean waitForElementVisibility(WebDriver driver2, By xpath, int i) {
		// TODO Auto-generated method stub
		return false;
	}
	private void safeClick(WebDriver driver2, By xpath) {
		// TODO Auto-generated method stub

	}
	private void waitForElement(WebDriver driver2, int i, By xpath) {
		// TODO Auto-generated method stub

	}
	public boolean elementPresent(WebDriver driver2, By by, int time) {

		boolean elementPresentFlag = false;

		for (int i = 0; i < time; i++) {
			try {
				WebElement we = null;
				if ((we = driver2.findElement(by)) != null) {

					elementPresentFlag = true;
					break;
				}
				Thread.sleep(1000);
			} catch (Exception e) {

			}
			List<WebElement> eles = null;

		}
		return elementPresentFlag;
	}

	@AfterMethod
	public void postSignUp()
	{
		System.out.println(driver.getCurrentUrl());

	}
	@AfterClass
	public void afterClass()
	{
			driver.quit();
	}

}




