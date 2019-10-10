package domainServices;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonServices.CommonUtil;
import commonServices.WrapperMethod;

public class Visa extends Locals{

	
	
	public void SigIn(RemoteWebDriver driver) throws Exception {

		 
		safeClick(driver,By.id("userAccountLink"));
		safeClick(driver,By.id("SignIn"));
/*		driver.findElement(By.id("userAccountLink")).click();
		driver.findElement(By.id("SignIn")).click();*/
       // driver.switchTo().frame("modal_window");
/*        WebDriverWait w = new WebDriverWait(driver, 10) ;
        
        w.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("email")))).isDisplayed();
        w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("email")))).isEnabled();*/
        Thread.sleep(3000);
        Wait<WebDriver> w = new FluentWait<WebDriver>(driver)
        						.withTimeout(10, TimeUnit.SECONDS);
        
        Thread.sleep(5000);
       
        safeType(driver,By.id("email"),("manjuraj.nagaraj@cleartrip.com"));
        safeType(driver,By.id("password"),("Itsme@123"));
        driver.findElement(By.id("signInButton")).click();
        Reporter.log("Valid Credentials",true);
        Reporter.log("Signed In Succesfully",true);
	}
	
	public void VisaToUAE(RemoteWebDriver driver,String visa) throws Exception
	{ 
		safeClick(driver,By.xpath(visa));	
		
		Reporter.log("Visa Option Selected",true);
		
	}
	
	public void VisaTravelDetailsIndiaAdultInfant(RemoteWebDriver driver,String days) throws Exception
	{
		Thread.sleep(3000);
		Reporter.log(" Travellers Detail Page ",true);
		WebElement we=driver.findElement(By.xpath("//div[@class='p-relative']/select"));
		if(we.isDisplayed())
		{
			Select dropdown =new Select(we);
		
		Thread.sleep(3000);
		dropdown.selectByIndex(1);
		}
		driver.findElement(By.xpath(days)).click();
/*		Calendar c =new GregorianCalendar();
		c.add(Calendar.DATE,20);
		JavascriptExecutor e = driver;
		
		e.executeScript("window.scrollBy(0,150)","");*/
		   safeClick(driver,By.id("entryDate"));
			List<WebElement> date = driver.findElements(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']"));
			int activeday =date.size();
			Thread.sleep(1000);
			String startdate= driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']")).getText();
			int adddate= Integer.parseInt(startdate);
			adddate =adddate+7;

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			for(int i=1;i<activeday;i++)
				{
				Thread.sleep(1000);
					if( driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']["+i+"]")).getText().contains(String.valueOf(adddate)))
						{
							driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']["+i+"]")).click();
						    break;
						}
			          
				}
			
			Reporter.log(driver.getCurrentUrl());
		 
			safeClick(driver, By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div[2]/div/button"));
		    Reporter.log("Applied for Visa");
		}

	public void VisaTravelDetailsIndia(RemoteWebDriver driver,String days) throws Exception
	{
		Thread.sleep(3000);
		WebElement we=driver.findElement(By.xpath("//div[@class='p-relative']/select"));
		if(we.isDisplayed())
		{
			Select dropdown =new Select(we);
		
		Thread.sleep(3000);
		dropdown.selectByValue("IN");
		}
		driver.findElement(By.xpath(days)).click();
/*		Calendar c =new GregorianCalendar();
		c.add(Calendar.DATE,20);
		JavascriptExecutor e = driver;
		
		e.executeScript("window.scrollBy(0,150)","");*/
		   safeClick(driver,By.id("entryDate"));
		   safeClick(driver,By.xpath("//span[@aria-label='Next Month']"));
			List<WebElement> date = driver.findElements(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']"));
			int activeday =date.size();
			Thread.sleep(1000);
			String startdate= driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']")).getText();
			int adddate= Integer.parseInt(startdate);
			adddate =adddate+7;

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			for(int i=1;i<activeday;i++)
				{
				Thread.sleep(1000);
					if( driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']["+i+"]")).getText().contains(String.valueOf(adddate)))
						{
							driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']["+i+"]")).click();
						    break;
						}
			          
				}
			
			Reporter.log(driver.getCurrentUrl());
		 
			safeClick(driver, By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div[2]/div/button"));
		    Reporter.log("Applied for Visa");
		}

		
		
	
	public void VisaTravelDetailsPhilipines(RemoteWebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		WebElement we=driver.findElement(By.xpath("//*[@id='nationality']"));
		Select dropdown =new Select(we);
		Thread.sleep(3000);
		dropdown.selectByValue("PH");
		
		driver.findElement(By.xpath("//div[@class='col-16']/div/div[3]/div/div")).click();
/*		Calendar c =new GregorianCalendar();
		c.add(Calendar.DATE,20);
		JavascriptExecutor e = driver;
		
		e.executeScript("window.scrollBy(0,150)","");*/
		   safeClick(driver,By.id("entryDate"));
		   safeClick(driver,By.xpath("//span[@aria-label='Next Month']"));
			List<WebElement> date = driver.findElements(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']"));
			int activeday =date.size();
			Thread.sleep(1000);
			String startdate= driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']")).getText();
			int adddate= Integer.parseInt(startdate);
			adddate =adddate+7;

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			for(int i=1;i<activeday;i++)
				{
				Thread.sleep(3000);
					if( driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']["+i+"]")).getText().contains(String.valueOf(adddate)))
						{
							driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']["+i+"]")).click();
						    break;
						}
			          
				}
			
			
			safeClick(driver, By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div[2]/div/button"));
		
	}
	
		
	public void VisaTravelDetailsPhilipinesAdultInfant(RemoteWebDriver driver) throws Exception
	{
		WebElement we=driver.findElement(By.xpath("//div[@class='p-relative']/select"));
		Select dropdown =new Select(we);
		Thread.sleep(3000);
		dropdown.selectByIndex(2);
		
		driver.findElement(By.xpath("//div[@class='col-16']/div/div[3]/div/div")).click();
/*		Calendar c =new GregorianCalendar();
		c.add(Calendar.DATE,20);
		JavascriptExecutor e = driver;
		
		e.executeScript("window.scrollBy(0,150)","");*/
		   safeClick(driver,By.id("entryDate"));
			List<WebElement> date = driver.findElements(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']"));
			int activeday =date.size();
			Thread.sleep(1000);
			String startdate= driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']")).getText();
			int adddate= Integer.parseInt(startdate);
			adddate =adddate+7;

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			for(int i=1;i<activeday;i++)
				{
				Thread.sleep(1000);
					if( driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']["+i+"]")).getText().contains(String.valueOf(adddate)))
						{
							driver.findElement(By.xpath("//div[@class='DayPicker-Day'][@aria-disabled='false']["+i+"]")).click();
						    break;
						}
			          
				}
			
			safeClick(driver,By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div[2]/div/div[6]/div[2]/ul/li[3]/svg"));
			safeClick(driver, By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div[2]/div/button"));
		
	}
	
	
	
	
	
	
	public void IndiabookTravellers(RemoteWebDriver driver) throws Exception
			{
				JavascriptExecutor e =(JavascriptExecutor)driver;
				e.executeScript("window.scrollBy(0,150)");
			    WebDriverWait wait = new WebDriverWait(driver, 5);
			    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button"))).isEnabled();
			    Thread.sleep(3000);

				safeClick(driver, By.xpath("//button"));
				//driver.findElement(By.id("email")).clear();
		        safeType(driver,By.id("email"),"manjuraj.nagaraj@cleartrip.com");
		        Select s = new Select(driver.findElement(By.xpath("//select[@name='countryCode']")));
		        s.selectByIndex(2);;
		        safeType(driver,By.id("phone"),"9738685480");
		        safeClick(driver,By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div[4]/div/div[2]/div[2]/button"));
		        e.executeScript("window.scrollBy(0,150)");
		        s = new Select(driver.findElement(By.xpath("//select[@id='title']")));
		        Thread.sleep(1000);

		        s.selectByValue("Mr");
		        safeType(driver,By.name("firstName"),"Test");
		        safeType(driver,By.name("lastName"),"Test");
		        s = new Select(driver.findElement(By.name("dobDate")));
		        s.selectByValue("20");
		        s = new Select(driver.findElement(By.name("dobMonth")));
		        s.selectByIndex(5);
		        s = new Select(driver.findElement(By.name("dobYear")));
		        s.selectByValue("1992");
		        //safeClick(driver, By.id("passport"));
                safeType(driver,By.id("passport"),"passport");
		        Select s2 = new Select(driver.findElement(By.xpath("//select[@name='passportExpiryDate']")));
		        s2.selectByValue("20");
		        s2 = new Select(driver.findElement(By.xpath("//select[@name='passportExpiryMonth']")));
		        s2.selectByIndex(5);
		        s2 = new Select(driver.findElement(By.xpath("//select[@name='passportExpiryYear']")));
		        s2.selectByIndex(10);;
		        //safeClick(driver, By.id
		        e.executeScript("window.scrollBy(0,150)");
		        Thread.sleep(1000);
		        safeClick(driver,By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div[6]/div/div[2]/div[2]/button"));
		        
			}
	

		public void PhilipinesbookTravellers(RemoteWebDriver driver) throws Exception
		{
			JavascriptExecutor e =(JavascriptExecutor)driver;
			e.executeScript("window.scrollBy(0,150)");
		    WebDriverWait wait = new WebDriverWait(driver, 5);
		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button"))).isEnabled();
		    Thread.sleep(3000);

			safeClick(driver, By.xpath("//button"));
			//driver.findElement(By.id("email")).clear();
	        safeType(driver,By.id("email"),"manjuraj.nagaraj@cleartrip.com");
	        Select s = new Select(driver.findElement(By.xpath("//select[@name='countryCode']")));
	        s.selectByIndex(2);;
	        safeType(driver,By.id("phone"),"9738685480");
	        safeClick(driver,By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div[4]/div/div[2]/div[2]/button"));
	        e.executeScript("window.scrollBy(0,150)");
	        s = new Select(driver.findElement(By.xpath("//select[@id='title']")));
	        Thread.sleep(2000);

	        s.selectByValue("Mr");
	        safeType(driver,By.name("firstName"),"Test");
	        safeType(driver,By.name("lastName"),"Test");
	        s = new Select(driver.findElement(By.name("dobDate")));
	        s.selectByValue("20");
	        s = new Select(driver.findElement(By.name("dobMonth")));
	        s.selectByIndex(5);
	        s = new Select(driver.findElement(By.name("dobYear")));
	        s.selectByValue("1992");
	        //safeClick(driver, By.id("passport"));
            safeType(driver,By.id("passport"),"passport");
	        Select s2 = new Select(driver.findElement(By.xpath("//select[@name='passportExpiryDate']")));
	        s2.selectByValue("20");
	        s2 = new Select(driver.findElement(By.xpath("//select[@name='passportExpiryMonth']")));
	        s2.selectByIndex(5);
	        s2 = new Select(driver.findElement(By.xpath("//select[@name='passportExpiryYear']")));
	        s2.selectByIndex(10);;
	        //safeClick(driver, By.id
	        e.executeScript("window.scrollBy(0,150)");
	        Thread.sleep(2000);
	        safeType(driver, By.xpath("//input[@id='fatherFirstName']"),"Test");
	        safeType(driver, By.xpath("//input[@id='fatherLastName']"),"Test");
	        
	        safeType(driver, By.xpath("//input[@id='motherFirstName']"),"Test");
	        safeType(driver, By.xpath("//input[@id='motherLastName']"),"Test");
	        Thread.sleep(5000);
	        safeClick(driver,By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div[6]/div/div[2]/div[2]/button"));
	        
		}

		public Boolean Applycoupon(RemoteWebDriver driver) throws InterruptedException
		{
			Thread.sleep(5000);
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("window.scrollBy(0,150)");
			driver.findElement(By.id("applyCoupon")).sendKeys("JITU");
			driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div[8]/div/div/div[3]/div[2]/div[1]/div[6]/div/div/button")).click();
			Thread.sleep(5000);
			if((driver.findElement(By.xpath("//p[@style='display: inline;']")).getText().contains("Great you saved")))
		    {
		    	System.out.println("Coupon applied successfully");
		    	return true;
		    }
		    else
		    	{System.out.println("Coupon Error");
		         return false;
		    	}
		} 
	     public void visapayment(RemoteWebDriver driver) throws Exception
	     		{
	    	 		Thread.sleep(5000);
	    	         safeType(driver, By.xpath("//input[@name='cardNumber']"),"5123456789012346");
	    	         Select m = new Select(driver.findElement(By.xpath("//select[@id='expiryMonth']")));
	     		     m.selectByIndex(5);
	     		     m = new Select(driver.findElement(By.xpath("//select[@id='expiryYear']")));
	     		     m.selectByValue("2020");
	     		     safeType(driver, By.id("name"),"Test");
	     		     JavascriptExecutor e =(JavascriptExecutor)driver;
	     		     e.executeScript("window.scrollBy(0,50)");
	     		     safeType(driver,By.name("cvv"),"123");
	     		     safeClick(driver,By.xpath("//span[@class='checkbox__mark']"));
	     		     safeClick(driver,By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div[8]/div/div/div[3]/div[2]/div[1]/button"));
	     		     
	     		
	     		}
	     public  boolean paymentpageValidate(RemoteWebDriver driver)
	     			{
	    	 				driver.findElement(By.xpath("//h1")).getText();
	    	 				Assert.assertEquals("Your payment is done",driver.findElement(By.xpath("//h1")).getText());
	    	 				Assert.assertEquals("Payment receipt", driver.findElement(By.xpath("//div[@class='col-8']/div/p[1]")).getText());
							
	    	 				return true;
					}
	     
	     public void Visadocumentupload(RemoteWebDriver driver) throws Exception
	     		{
	    	 	  JavascriptExecutor documents = (JavascriptExecutor) driver;
	    	 	  documents.executeScript("window.scrollBy(0,150)");
	    	 	  safeClick(driver,By.xpath("//*[@id='root']/div/div[1]/div[2]/div[1]/div[2]/button"));
	    	 	  documents.executeScript("window.scrollBy(0,250)");
	    	 	 // File f = new File("//exe//visa.jpg");
	    	 	  driver.findElement(By.id("passport1")).sendKeys(System.getProperty("user.dir")+"//exe//visa.jpg");
	    	 	  driver.findElement(By.id("passportBack1")).sendKeys(System.getProperty("user.dir")+"//exe//visa.jpg");
	    	 	  driver.findElement(By.id("1")).sendKeys(System.getProperty("user.dir")+"//exe//visa.jpg");
	    	 	  WebDriverWait wait = new WebDriverWait(driver, 10);
	    	 	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[3]/div/div/button"))).isEnabled();
	    	 	  Thread.sleep(5000);
	    	 	  safeClick(driver,By.xpath("//*[@id='root']/div/div[1]/div[3]/div/div/button"));
	    	 	  Thread.sleep(5000);
	    	 	 // driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    	 	  /*  driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[3]/div/div/div[4]/div[2]/label")).sendKeys("D://Automation_Scripts/Automation_CT/Automation_CT/Passport-size-photo-test.jpg");
	    	 	  Actions a = new Actions(driver);
	    	 	  //  driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[3]/div/div/div[4]/div[2]/label")).sendKeys("/Automation_CT/Passport-size-photo-test.jpg");;
	    	 	 // a.click();
	    	 	 // a.sendKeys("D://Automation_Scripts/Automation_CT/Automation_CT/Passport-size-photo-test.jpg");
	    	 	.build().perform();
	    	 	*///  safeClick(driver,By.xpath("//*[@id='root']/div/div[1]/div[3]/div/div/div[4]/div[3]/label"));
	    	 
	     		}
	     public void Visadetails(RemoteWebDriver driver) throws InterruptedException
	       {
	    	 Thread.sleep(5000);
	    	 WebDriverWait wait = new WebDriverWait(driver,10);
	    	// wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//h1")).getText().contains("Document"))));
	    	 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[3]/div/div/button"))));
	    	 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1"))));
	    	 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1"))));
	    	 System.out.println("Message Displayed :-"+driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/div/p")).getText());
	    	 System.out.println(driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/div/p")).getText());
	    	 Thread.sleep(3000);
	       
	       }
}
