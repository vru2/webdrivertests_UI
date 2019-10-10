package com.cleartrip.local.camp.beta;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class TTDActivityFlow extends CampActivities {

	@Parameters({"domain"})
	@Test
	public void activtyFlow(String domain) {
		try {
			
			campActivities_SignIN(driver, "ESP");
			selectActivity("New Activity");
			NewActivityFormOneESP(campLocal.value("prodCollection"), "Shared", "Yes", "Yes", "prodAutomation",domain);
			NewActivityFormTwo("Yes");
			String actId = NewActivityFormThreeESP("ScheduledActivity", "shared", "Pricing Per Person", "");
			campActivities_SignOut(driver);
			//String actId="948054";
			campActivities_SignIN(driver, "SAM");
			selectAllActivities(driver);
			activitySAMApprove(driver, actId, "Approve");
			campActivities_SignOut(driver);
			prodActivityApproveSCM(driver, actId,campLocal.value("prodInterestTag"),domain);
			prodActivityApproveSA(driver, actId,domain);
			vProdActivityLocal(driver,domain,"things-to-do");
			scrollToElement(driver,getObject("Camp_FNB_Unpublish"));
			safeClick(driver, getObject("Camp_FNB_Unpublish"));
			Thread.sleep(4500);
			//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='block-ui-message ng-binding']"))));
			Assert.assertTrue(driver.findElement(By.xpath("html/body/div[1]/div[1]/div"))
					.getText().contains("Activity Un-Published"),"Activity is not unpublished");
			Reporter.log("Activity unpublished",true);

		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
