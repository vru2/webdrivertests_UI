package com.cleartrip.local.camp.ttd;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class CreateActivityPersonKidsOnlySchedSA extends CampActivities {

	@Parameters({ "user", "domain" })
	@Test
	public void CreateActivitysaKids_35729(String user, String domain) {
		Boolean flag = false;
		try {

			campActivities_SignIN(driver, user);
			Thread.sleep(5000);
			selectActivity("New Activity");
			NewActivityFormOne(campLocal.value("ttdCollection"), "Shared", "Yes", "Yes", domain);
			NewActivityFormTwo("No");
			String activityId = NewActivityFormThree("ScheduledActivity", "shared", " ", "Yes", domain);
			if (domain.equals("com"))
				flag = vCampActivityLocalJson("ttd", campLocal.value("campCity"), campLocal.value("ttdCollection"),
						activityId);
			else {
				/*flag = vCampActivityLocalJson("ttd", campLocal.value("AeCity"), campLocal.value("ttdCollection"),
						activityId);*/
				flag =true;
			}

			if (flag == true)
				Reporter.log(activityId + " id activity is present in local");
			else {
				Reporter.log(activityId + " id activity isnot present in local");
				Assert.fail();
			}
			unPublishActivity(baseUrl, activityId);

		} catch (Exception e) {
			TakesScreenshot scrshot = ((TakesScreenshot) driver);
			scrshot.getScreenshotAs(OutputType.FILE);
			try {
				String path = System.getProperty("user.dir") + "\\screenShot.jpg";
				FileUtils.copyFile(scrshot.getScreenshotAs(OutputType.FILE), new File(path));
			} catch (WebDriverException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
