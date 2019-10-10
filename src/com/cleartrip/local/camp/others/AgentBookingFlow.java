package com.cleartrip.local.camp.others;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonServices.WrapperMethod;
import domainServices.CampActivities;
import domainServices.Locals;

public class AgentBookingFlow extends WrapperMethod{

	HashMap<String, String> emailIds = new HashMap<>();
	CampActivities campAct = new CampActivities();
	Locals local = new Locals();
	RemoteWebDriver driver;
	WebDriverWait wait;

	//@Parameters({ "domain" })
	@Test
	public void createAgent(/*String domain*/) throws Exception {
		String domain = "ae",env="partners";
		int mrpPrice=1000,commision=20;
		LocalDate date = LocalDate.now();
		String agentName = "agent" + date.getMonthValue() + date.getDayOfMonth(),
				subAgentName = "subagentqa" + date.getMonthValue() + date.getDayOfMonth();
		
		//agent creation
		emailIds.put("agentEmailID", campAct.createMail(driver));
		signUpApprove(agentName,domain,env);
		String id = campAct.wlAccountActivity(driver,agentName, domain,env);
		campAct.uploadCsvMarkUp(driver,id, "", agentName,env);
		
		addHostEntry("172.17.8.38",agentName);
		
        Assert.assertTrue(campAct.dbSchemaGetValue("AGENT_ID","AGENT_ACTIVITY_CT_MKPS","AGENT_ID",id).equals(id),"Agent id didn't update into DB");
		Reporter.log("Agent id updated into DB", true);
		
		driver.get("https://"+agentName+".travelbox99.com/local/signin");
		safeType(driver, By.id("email"),emailIds.get("agentEmailID"));
		safeType(driver, By.id("password"),"cleartrip");
		safeClick(driver, By.id("signInButton"));
		
		driver.get("https://"+agentName+".travelbox99.com/local/dubai/"+campLocal.value("agentURL"));
		vAgentBooking(driver,mrpPrice,commision);
		
		
		
		
		//sub agent creation
		emailIds.put("subAgentEmailID", campAct.createMail(driver));
		signUpApprove(subAgentName,domain,env);
		campAct.campSignup(driver,emailIds.get("subAgentEmailID"), subAgentName, domain,env);
		campAct.approveAgent(driver,domain, subAgentName, emailIds.get("agentEmailID"),env);
		String subId = campAct.wlAccountActivity(driver,subAgentName, domain,env);
		campAct.uploadCsvMarkUp(driver,id, subId, subAgentName,env);
		Assert.assertTrue(campAct.dbSchemaGetValue("SUB_AGENT_ID","SUBAGENT_ACTIVITY_AGENT_MKPS","SUB_AGENT_ID",subId).equals(subId),"SubAgent id didn't update into DB");
		Reporter.log("SubAgent id updated into DB", true);
	}
	
	
	
	private void signUpApprove(String name, String domain, String env) throws Exception {
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		campAct.campSignup(driver,emailIds.get("agentEmailID"), name, domain,env);
		campAct.approveAgent(driver,domain, name, "",env);
		driver.close();
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(3000);
		//safeClick(driver, By.xpath("//div[@id='divEmailList']/div[1]/div[contains(.,'Welcome to Cleartrip')]"));
		//driver.findElement(By.linkText("Click here")).sendKeys(Keys.CONTROL+"t");
		safeClick(driver, By.linkText("Click here"));
		safeType(driver, By.xpath("//input[@name='password']"),"cleartrip");
		safeType(driver, By.xpath("//input[@name='confirm_password']"),"cleartrip");
		safeClick(driver, By.xpath("//button[@type='submit']"));
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		campAct.campActivities_SignOut(driver);		
	}



	private void vAgentBooking(RemoteWebDriver driver,int mrpPrice,int commission) throws Exception {
		int calc,total=0;
		String Date = putDate(7);
		if (Date.startsWith("0")) {
			Date = Date.substring(1);
		}
		Thread.sleep(2000);
		elementVisible(driver, By.cssSelector("h1.booking-form__title"), 5);
		String BookMsg = getText(driver, By.cssSelector("h1.booking-form__title"));
		if (!BookMsg.contains("Reserve your spot now")) {
			Reporter.log("Reserve your spot now : Message is not displayed", true);
			Assert.assertTrue(false);
		}
		if (textPresent(driver, "There was an error trying to communicate with the server", 1)) {
			Reporter.log("There was an error trying to communicate with the server : Message is not displayed", true);
			Assert.assertTrue(false);
		}
		
		local.locals_SelectDate(driver, Date);
		calc = mrpPrice-(mrpPrice*(commission/100));
		total = calc+calc*((Integer.parseInt(campLocal.value("agentMarkUp")))/100);
		Assert.assertTrue(isElementPresent(driver, By.xpath("//small[@class='weak-hint']")),"net rate and commission isn't showing in book page");
		Reporter.log("net rate and commission is showing in book page :\n"+getText(driver, By.xpath("//small[@class='weak-hint']")), true);
		
		safeClick(driver, getObjectLocals("LocalCom_TTD_BookPage_Bookbutton"));
		local.rateRuleItinerary(driver);
		scrollToElement(driver, getObjectLocals("LocalCom_ItineraryPage_Button"));
		safeClick(driver, getObjectLocals("LocalCom_ItineraryPage_Button"));
		local.locals_PaymentPage(driver, "CC");
		String tripId = local.locals_Payment_ConfirmationPage(driver, "TTD Adult Timeslot Booking : ", "");
		Reporter.log(tripId,true);
	}



	private void addHostEntry(String ip, String name) throws IOException {
		FileWriter writer = new FileWriter("C://Windows//System32//drivers//etc//hosts", true);
        writer.write("\n");
        writer.write(ip);   // write new line
        writer.write("\t"+name+".travelbox99.com");
        writer.close();
	}



	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		driver = (RemoteWebDriver) getDriver(driver);
		campAct.baseUrl = campAct.campLocal.value("campUrlAeProd");
		wait = new WebDriverWait(driver,500000);
	}

}
