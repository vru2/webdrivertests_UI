package payments_PWA_UI;

import static org.testng.Assert.assertEquals;
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
		waitForElementVisibility(driver,getObjectPlatform("PWA_Menu_button"),5);
		if (elementPresent(driver, getObjectPlatform("PWA_Menu_button"), 10)){

			safeClick(driver,  getObjectPlatform("PWA_Menu_button"));

		}
		elementVisible(driver,getObjectPlatform("PWA_Flights"),5);
		safeClick(driver,  getObjectPlatform("PWA_Flights"));							
		System.out.println("Clicked on flights");
		waitForElementVisibility(driver,getObjectPlatform("PWA_srp_From_button"),5);
		if (elementPresent(driver, getObjectPlatform("PWA_srp_From_button"), 10)){
			driver.findElement(By.xpath("//*[text()='From']/parent::*/*[2]")).click();
		}	
		waitForElementVisibility(driver,getObjectPlatform("PWA_srp_from_input"),5);
		safeClick(driver,  getObjectPlatform("PWA_srp_from_input"));
		safeType(driver, getObjectPlatform("PWA_srp_from_input"), platform.value("PWA_srp_from_input_city"));
		waitForElementVisibility(driver,getObjectPlatform("PWA_srp_from_input_select"),5);		
		safeClick(driver,  getObjectPlatform("PWA_srp_from_input_select"));
		waitForElementVisibility(driver,getObjectPlatform("pwa_to_click_button"),5);
		if (elementPresent(driver, getObjectPlatform("PWA_srp_To_button"), 10)) {	
			driver.findElement(By.xpath("//*[text()='To']/parent::*/*[2]")).click();
		}
		waitForElementVisibility(driver,getObjectPlatform("PWA_srp_to_input"),5);
		safeClick(driver,  getObjectPlatform("PWA_srp_to_input"));
		safeType(driver, getObjectPlatform("PWA_srp_to_input"), platform.value("PWA_srp_to_input_city"));
		safeClick(driver,  getObjectPlatform("PWA_srp_from_input_select"));

		waitForElementVisibility(driver,getObjectPlatform("pwa_srp_select_date"),10);
		if (elementPresent(driver, By.xpath("//*[text()='Depart']/parent::*/*[2]"), 10)){

			driver.findElement(By.xpath("//*[text()='Depart']/parent::*/*[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='react-calendar__month-view'][4]/div/div/div/div[4]")).click();
			Thread.sleep(2000);
		}
		waitForElementVisibility(driver,getObjectPlatform("PWA_travellers_select"),10);
		safeClick(driver,getObjectPlatform("PWA_travellers_select"));	
		elementVisible(driver,getObjectPlatform("PWA_addtravellers"),10);
		safeClick(driver,getObjectPlatform("PWA_addtravellers"));
		elementVisible(driver,getObjectPlatform("PWA_addtravellers_select"),10);
		safeClick(driver,getObjectPlatform("PWA_addtravellers_select"));
		waitForElementVisibility(driver,getObjectPlatform("PWA_travellers_continue"),10);
		safeClick(driver,getObjectPlatform("PWA_travellers_continue"));
		driver.switchTo().activeElement();
		waitForElementVisibility(driver,getObjectPlatform("PWA_homepage_search_button"),10);		
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
		safeClick(driver,getObjectPlatform("PWA_homepage_search_button"));
		Reporter.log("Clicked on Search button");
		System.out.println("Clicked on Search button.");

		if (textPresent(driver, "Flights not available", 2)) {
			Reporter.log("Flights not available text is displayed");
			assertTrue(false);
		}
		assertTrue(true);
	}

	public void pwa_srpflights_select(RemoteWebDriver driver) throws Exception {

		Thread thread = new Thread();
		thread.start();
		WebDriverWait wait = new WebDriverWait(driver, 40);		 
		waitForElementVisibility(driver,By.xpath("//p[contains(text(),'IndiGo')]"),20);		
		if(elementPresent(driver, By.xpath("//p[contains(text(),'IndiGo')]"), 10))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'IndiGo')]")));
			List<WebElement> li = driver.findElements(By.xpath("//p[contains(text(),'IndiGo')]"));;
			Thread.sleep(1000);
			li.get(1).click();	
			Reporter.log("Selected IndiGO Flight");

		}
		else 
		{
			if (elementPresent(driver, By.xpath("//div/ul//div/li[@role='menuitem'][4]"), 4)) {
				driver.findElement(By.xpath("//div/ul//div/li[@role='menuitem'][2]")).click();
			}
			waitForElement(driver,20,By.xpath("//*[text()='Total']//..//../*[3]/*[2]"));
			Reporter.log("Flights selected");
			assertTrue(true);
		}
		waitForElementVisibility(driver,getObjectPlatform("PWA_reviewtotal_price"),10);
		assertTrue(true);
	}
	
	
	public void pwa_review_itinerarypage(RemoteWebDriver driver) throws Exception {	
		
		WebDriverWait wait = new WebDriverWait(driver, 40);		
		waitForElementVisibility(driver,getObjectPlatform("PWA_Standard_fare"),10);
		textPresent_Log(driver, "Standard fare", 5);
		driver.findElement(By.xpath("//*[text()='Standard fare']")).click();
		//safeClick(driver,getObjectPlatform("PWA_Standard_fare"));
		Reporter.log("Selected Standard_fare");
		System.out.println("selected standed fare");
		scrollSmooth(driver, 400);
		waitForElementVisibility(driver,getObjectPlatform("PWA_reviewtotal_price"),10);		
		String totalfare=getText(driver,  getObjectPlatform("PWA_ITIPage_totalfare"));
		System.out.println(totalfare);
		
		
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));	
		js.executeScript("arguments[0].scrollIntoView();", Element);*/
		scrollSmooth(driver, 600);
		scrollToElement(driver, By.xpath("//button[contains(text(),'Continue')]"));
		elementVisible(driver, getObjectPlatform("PWA_review_itinerary_continue_button"), 10);
		waitForElementVisibility(driver,getObjectPlatform("PWA_review_itinerary_continue_button"),10);
		safeClick(driver,getObjectPlatform("PWA_review_itinerary_continue_button"));
		waitForElementVisibility(driver,getObjectPlatform("PWA_itinerary_addons"),10);
		
		if (elementPresent(driver, getObjectPlatform("PWA_itinerary_addons"),40)) {
			textPresent_Log(driver, "Next", 5);
			safeClick(driver,By.xpath("//button[contains(text(),'Next')]"));
			textPresent_Log(driver, "Next", 5);
			safeClick(driver,By.xpath("//button[contains(text(),'Next')]"));
			textPresent_Log(driver, "Done", 5);	
			
			safeClick(driver,By.xpath("//button[contains(text(),'Done')]"));
			

		}
		else {

			waitForElementVisibility(driver,getObjectPlatform("PWA_review_itinerary_reviewTravellers"),10);
			assertTrue(true);
			
		}

	}
	
	public void pwa_review_travellers(RemoteWebDriver driver) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 60);

		waitForElementVisibility(driver,getObjectPlatform("PWA_review_itinerary_reviewTravellers"),20);
		Select title = new Select(driver.findElement(By.name("title")));
		title.selectByVisibleText("Mr");

		waitForElementVisibility(driver,getObjectPlatform("PWA_traveller_firstname"),5);
		safeClick(driver,  getObjectPlatform("PWA_traveller_firstname"));
		safeType(driver, getObjectPlatform("PWA_traveller_firstname"), platform.value("PWA_traveller_firstname"));

		waitForElementVisibility(driver,getObjectPlatform("PWA_traveller_lastname"),5);
		safeClick(driver,  getObjectPlatform("PWA_traveller_lastname"));
		safeType(driver, getObjectPlatform("PWA_traveller_lastname"), platform.value("PWA_traveller_lastname"));
		waitForElementVisibility(driver,getObjectPlatform("PWA_traveller_Nationality"),10);
		if (elementPresent(driver, getObjectPlatform("PWA_traveller_Nationality"),5)) {
			driver.findElement(By.xpath("//label[contains(text(),'Nationality')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class='Sticky Sticky--top']/div[2]/div[2]/input")).sendKeys("India");
			driver.findElement(By.xpath("//li/p[1]")).click();
			Thread.sleep(1000);
		}
		else 
		{
			waitForElementVisibility(driver,getObjectPlatform("PWA_traveller_phonenumber"),5);
			assertTrue(true);
			Reporter.log("Travellers phone number is given");
		}

		if (elementPresent(driver, getObjectPlatform("PWA_traveller_phonenumber"), 10)) {
			safeType(driver, getObjectPlatform("PWA_traveller_phonenumber"), platform.value("PWA_traveller_phonenumber"));
		}
		if ((driver.findElement(By.xpath("//input[@name='email']")).getAttribute("disabled")) == null) {
			safeType(driver, getObjectPlatform("PWA_traveller_emailid"), platform.value("PWA_traveller_emailid"));
		}
		waitForElementVisibility(driver,getObjectPlatform("PWA_traveller_continuebutton"),5);
		safeClick(driver,  getObjectPlatform("PWA_traveller_continuebutton"));		
		if (textPresent(driver, "Flights not available", 5)) {
			Reporter.log("Flights not available text is displayed");
			assertTrue(false);
		}
		assertTrue(true);
	}
	public void pwa_select_Payment_option(RemoteWebDriver driver, String payType, String payType1, String cardType) throws Exception {

		if (cardType.equalsIgnoreCase("MASTER")||cardType.equalsIgnoreCase("")) {
			cardType = "MASTER";
		}else if (cardType.equalsIgnoreCase("AMEX")) {
			cardType = "AMEX";
		}
		String Country = null;
		if (!payType1.equals("")) {
			Country = payType1.substring(0, 2);
		}

		if (payType.equalsIgnoreCase("NB")) {

			waitForElementVisibility(driver,getObjectPlatform("PWA_Paymentpage"),5);
			if 	(elementPresent(driver, getObjectPlatform("PWA_Paymentpage"), 10))				
			{
				waitForElementVisibility(driver, getObjectPlatform("PWA_PaymentPage_NB_Tab"), 5);				
				driver.findElement(By.xpath("//p[contains(text(),'Net banking')]")).click();				
				if (!textPresentInElement(driver,getObjectPlatform("PWA_PaymentPage_SelectNBPage"),"Choose another bank", 2)) {
					Reporter.log("Choose another bank text is not displayed");
					assertTrue(false);
				}
			} 

			waitForElementVisibility(driver, getObjectPlatform("PWA_PaymentPage_SelectNBPage"), 10);
			safeClick(driver,  getObjectPlatform("PWA_PaymentPage_SelectNBPage"));			
			waitForElementVisibility(driver, getObjectPlatform("PWA_PaymentPage_NB_Name"), 10);			
			driver.switchTo().activeElement();
			Thread.sleep(2000);
			safeClick(driver,  getObjectPlatform("PWA_PaymentPage_NB_Name"));			
			Thread.sleep(1000);
			waitForElementVisibility(driver, getObjectPlatform("PWA_PaymentPage_NB_bankName"), 10);
			safeType(driver, getObjectPlatform("PWA_PaymentPage_NB_bankName"), platform.value("PWA_PaymentPage_NB_bankName"));			
			Thread.sleep(2000);
			waitForElementVisibility(driver, getObjectPlatform("PWA_paymentPage_selectBank"), 10);
			safeClick(driver,  getObjectPlatform("PWA_paymentPage_selectBank"));		
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
			waitForElementVisibility(driver, getObjectPlatform("PWA_payment_pay_page"), 10);
			safeClick(driver,  getObjectPlatform("PWA_payment_pay_page"));
			//driver.findElement(By.xpath("//button[contains(text(),'Success')]")).click();
			waitForElementVisibility(driver, getObjectPlatform("PWA_payment_confirmation_page"), 10);

			//	waitForElement(driver, 50, By.xpath("//h5[contains(text(),'Booking confirmed')]"));
			Thread.sleep(5000);
			String url = driver.getCurrentUrl();
			System.out.println("---------" + url.split("itinerary")[1]);
		}
		assertTrue(true);
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
			System.out.println("Trip ID of your booking is : "+Tripref);
			Reporter.log("Payment successful your Booking is Confirmed.");
			assertTrue(true);
		}

		else
		{

			System.out.println("payment failed.");
		}
	}


}
