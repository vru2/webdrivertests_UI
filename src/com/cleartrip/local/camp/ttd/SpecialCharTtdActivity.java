package com.cleartrip.local.camp.ttd;

import java.util.ArrayList;

import org.apache.http.HttpStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;

import domainServices.CampActivities;

public class SpecialCharTtdActivity extends CampActivities {
	
	@Parameters({"user"})
	@BeforeClass
	public void campLogin(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
	}

	@Parameters({"domain"})
	@Test
	public void verifyComma(String domain) {
		try {
			String actName = "Auto, activity", actDisplayName = "specialCharCheck";
			driver.get("https://partners.cleartrip." + domain + "/camp/activities/819544");
			vSpecialCharInActivyName(actName,actDisplayName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Parameters({"domain"})
	@Test
	public void verifyCommaSpecialChar(String domain) {
		try {
			String actName = "Auto$, $activity", actDisplayName = "special@CharacterCheck";
			driver.get("https://partners.cleartrip." + domain + "/camp/activities/819544");
			vSpecialCharInActivyName(actName,actDisplayName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Parameters({"domain"})
	@Test
	public void verifyMultiSpecialChar(String domain) {
		try {
			String actName = "@!, Auto$,$activity", actDisplayName = "special$ DollerCheck";
			driver.get("https://partners.cleartrip." + domain + "/camp/activities/819544");
			vSpecialCharInActivyName(actName,actDisplayName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Parameters({"domain"})
	@Test
	public void verifyFirstSpecialChar(String domain) {
		try {
			String actName = "$activity,", actDisplayName = "#specialFirstCheck";
			driver.get("https://partners.cleartrip." + domain + "/camp/activities/819544");
			vSpecialCharInActivyName(actName,actDisplayName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Parameters({"domain"})
	@Test
	public void verifyEndSpecialChar(String domain) {
		try {
			String actName = "activity, ", actDisplayName = "specialEndCheck";
			driver.get("https://partners.cleartrip." + domain + "/camp/activities/819544");
			vSpecialCharInActivyName(actName,actDisplayName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void vSpecialCharInActivyName(String actName, String actDisplayName) throws Exception {
		String actNameLoc = "//div[@data-activityname='nameLbl']/a/img",imgUrl;
		safeClick(driver, By.xpath("//h3/a[1]"));
		safeType_NonClear(driver, By.name("activity_name"), actName);
		safeType_NonClear(driver, By.name("activity_display_name"), actDisplayName);

		safeClick(driver, By.linkText("Save"));
		Thread.sleep(10000);
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		scrollToElement(driver, By.linkText("Publish"));
		safeClick(driver, By.linkText("Publish"));
		Thread.sleep(20000);
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
		waitElement(driver, By.xpath("//div[@ng-hide='closeNotice']/div"), 5);
		String SuccessMsg = getText(driver, By.xpath("//div[@ng-hide='closeNotice']/div"));
		Assert.assertTrue(SuccessMsg.contains("Activity Published"), SuccessMsg);
		Reporter.log(SuccessMsg, true);

		wait = new WebDriverWait(driver, 50000);
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://qa2.cleartrip.com/local/gadag/boating-in-gadag-collection-454-1");
		Assert.assertTrue(isElementPresent(driver, By.xpath(getXpathByReplace(actNameLoc, actDisplayName))),
				actDisplayName + " activity isn't displaying");
		Reporter.log("Activity is displayed",true);
		imgUrl = driver.findElement(By.xpath(getXpathByReplace(actNameLoc, actDisplayName))).getAttribute("src");
		Reporter.log(imgUrl,true);
		
		//get(imgUrl).then().assertThat().statusCode(HttpStatus.SC_OK);
		Reporter.log("Images aren't breaking",true);
		
		driver.close();
		driver.switchTo().window(tabs.get(0));
		
		
	}
}