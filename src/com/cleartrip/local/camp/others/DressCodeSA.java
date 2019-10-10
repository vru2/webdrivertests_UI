package com.cleartrip.local.camp.others;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class DressCodeSA extends CampActivities {

	@Parameters({"user"})
	@Test
	public void manageDataMasterAddDressCodeSA_36441(String user) throws Exception {
		String inputDressCodeUnit = "Auto_Qa2_Dress";
		campActivities_SignIN(driver,user);
		driver.get(baseUrl + "/manage_data/master_list");
		addMasterData("Dress Codes", inputDressCodeUnit);
		//vDressCode();
		
	}

	private void vDressCode() throws Exception {
		
		driver.get(baseUrl+"/activities");
		safeClickList(driver, getObjectLocals("Camp_productType"),"Things to do");
		safeClick(driver, getObjectLocals("Camp_submitBtn"));
		safeClick(driver, getObjectLocals("Camp_firstActivity"));
		safeClick(driver, getObject("Camp_Activities_Edit_Button"));
		safeClick(driver, getObjectLocals("Camp_OthrInfo"));
		//List<WebElement> list =driver.findElements(getObjectLocals("Camp_dressInfo"));
		List<WebElement> list =driver.findElements(By.xpath("//form[@id='stepTwoForm']//div[11]//li"));
		
		
		for(WebElement we : list) {
			System.out.println(we.getText());
		}
		
		
		
		
		
	}

}
