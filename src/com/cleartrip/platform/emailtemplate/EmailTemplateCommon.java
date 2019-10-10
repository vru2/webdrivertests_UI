package com.cleartrip.platform.emailtemplate;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import commonServices.WrapperMethod;
import domainServices.AirCommonMethod;
import domainServices.HQ;
import junit.framework.Assert;

public class EmailTemplateCommon extends WrapperMethod {
	public RemoteWebDriver driver;
    public static String s;
    public static String p;
	
	public void hqLogin(RemoteWebDriver driver) throws Exception{
		// Username
		safeClick(driver,getObjectPlatform("HQLogin_Username"));
		safeType(driver,getObjectPlatform("HQLogin_Username"),platform.value("HQLogin_EmailId"));
		
		//Password
		safeClick(driver,getObjectPlatform("HQLogin_Password"));
		safeType(driver,getObjectPlatform("HQLogin_Password"),platform.value("HQLogin_Password"));
		
		//Signin
		safeClick(driver,getObjectPlatform("HQLogin_Signin"));
		Reporter.log("Signed in Successfully");
		Assert.assertTrue(true);
		waitForElementVisibility(driver,getObjectPlatform("ClearTrip_LOGO"),100);
		
	}
	
	public void promotionalemailTemplate(RemoteWebDriver driver) throws Exception{
		waitForElementVisibility(driver,By.xpath(".//*[@id='editor']/textarea"),40);
		// Select Create new template
		safeClick(driver,getObjectPlatform("EmailTemplate_Create"));
		
		waitForElementVisibility(driver,getObjectPlatform("EmailTemplate_Name"),20);
		// Enter the template name
		
	    //String s = RandomStringUtils.random(8, alphabet);
		s=RandomStringUtils.randomAlphabetic(5);
		safeType(driver,getObjectPlatform("EmailTemplate_Name"),s);
	    Reporter.log("Promotional Template Name: " +s);
		
		
		// Select the template type
		safeClick(driver,getObjectPlatform("EmailTemplate_Type"));

		
		// Enter the template content
		WebElement textarea=driver.findElement(By.xpath(".//*[@id='editor']/textarea"));
		textarea.sendKeys("This is promotional test template creation");
		
		// Click on save template
		safeClick(driver,getObjectPlatform("EmailTemplate_Save"));
	
	}
	

	public void testTemplate(RemoteWebDriver driver) throws Exception{
		// Click on test template
		safeClick(driver,getObjectPlatform("EmailTemplate_Test"));
		
		// enter the from address
		safeType(driver,getObjectPlatform("EmailTemplate_From"),platform.value("HQLogin_EmailId"));
		
		// enter the to address
		safeType(driver,getObjectPlatform("EmailTemplate_To"),platform.value("HQLogin_EmailId"));
		
		// enter the subject
		safeType(driver,getObjectPlatform("EmailTemplate_Subject"),"Test subject");

		// select use template
		safeClick(driver,getObjectPlatform("EmailTemplate_Usetemplate"));
		Reporter.log("Use template radio button selected");
		
		// enter the mail content
		safeType(driver,getObjectPlatform("EmailTemplate_Mailcontent"),"Test mailcontent");
		
		// Click on preview
		safeClick(driver,getObjectPlatform("EmailTemplate_Preview"));

		// Close the preview window
		safeClick(driver,getObjectPlatform("EmailTemplate_Previewclose"));
		
		// click on spamscore to check the spamscore
		safeClick(driver,getObjectPlatform("EmailTemplate_Spamscore"));
		
		// Click on test button to send the template
		safeClick(driver,getObjectPlatform("EmailTemplate_Testchanges"));
		
		waitForElementVisibility(driver,getObjectPlatform("Success_Text"),20);
		String pagetext= driver.findElement(getObjectPlatform("Success_Text")).getText();
		Assert.assertEquals("Mail accepted!", pagetext);
		
		// Closing the test modal
		safeClick(driver,getObjectPlatform("EmailTemplate_Closetest"));
		
	}
       
    public void testPromoTemplate(RemoteWebDriver driver) throws Exception{
    	waitForElementVisibility(driver,By.xpath(".//*[@id='editor']/textarea"),50);
		//Select template
    	WebElement tempselec=driver.findElement(By.xpath(".//*[@id='templateSelector']"));
        tempselec.click();
       Actions a=new Actions(driver);
        a.moveToElement(tempselec);
        a.sendKeys(s).click().release().perform();
        testTemplate(driver);
    	Reporter.log("Testing successfull for the created promotional template");
		   
    }
	
	public void transactionalemailTemplate(RemoteWebDriver driver) throws Exception{
		waitForElementVisibility(driver,By.xpath(".//*[@id='editor']/textarea"),50);
		// Select Create new template
	      safeClick(driver,getObjectPlatform("EmailTemplate_Create"));
				
		waitForElementVisibility(driver,getObjectPlatform("EmailTemplate_Name"),30);
		// Enter the template name
		p=RandomStringUtils.randomAlphabetic(5);
		safeType(driver,getObjectPlatform("EmailTemplate_Name"),p);
		Reporter.log("Transactional Template Name: " +p);

		// Select the template type
		safeClick(driver,getObjectPlatform("EmailTemplate_Templatetype"));
		safeSelect(driver,getObjectPlatform("EmailTemplate_Templatetype"),"Transactional");
				
		// Enter the template content
		WebElement textarea=driver.findElement(By.xpath(".//*[@id='editor']/textarea"));
		textarea.sendKeys("This is transactional test template creation");
	
		// Click on save template
		safeClick(driver,getObjectPlatform("EmailTemplate_Save"));
		String text=driver.findElement(getObjectPlatform("Error_Message")).getText();
		if(!textPresent(driver,text, 5))
		{
			Assert.assertTrue(false);
			Reporter.log("Error message not displayed");
			Reporter.log("Transactional template created successfully");
			
		}else{
			
			Reporter.log(text);
		}
		
		
	}
	
	
	public void testTransTemplate(RemoteWebDriver driver) throws Exception{
		waitForElementVisibility(driver,By.xpath(".//*[@id='editor']/textarea"),60);
		//Select template
		WebElement tempselec=driver.findElement(By.xpath(".//*[@id='templateSelector']"));
        tempselec.click();
       Actions a=new Actions(driver);
        a.moveToElement(tempselec);
        a.sendKeys(p).click().release().perform();
        testTemplate(driver);
		Reporter.log("Testing successfull for the created transactional template");	
		Reporter.log("TestScript ran successfully for email template creation");
	}
	
	public void templateCreationValidation(RemoteWebDriver driver) throws Exception{
		String text=driver.findElement(getObjectPlatform("Error_Message")).getText();
		//Assert.assertEquals(text.isDisplayed(),false);
		if(!elementPresent_log(driver,getObjectPlatform("Error_Message"),"already exists, please change the name!",5))
		{
			Assert.assertTrue(false);
			Reporter.log("Error message not displayed");
			Reporter.log("New Template created successfully");
			
		} 
		elementPresent_log(driver,getObjectPlatform("Error_Message"),"already exists, please change the name!",5);
		
		if(textPresentInElement(driver,getObjectPlatform("Error_Message"),"already exists, please change the name!",5)){
			Reporter.log(text);
			Assert.assertTrue(false);
		} 
		
	}
	
	
}
