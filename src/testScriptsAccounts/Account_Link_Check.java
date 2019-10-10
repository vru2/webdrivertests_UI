package testScriptsAccounts;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class Account_Link_Check extends Accounts {
	public RemoteWebDriver driver = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@Test
	public void queuesSanity() throws Exception {
	    	        // TODO Auto-generated method stub
		
		driver.get(baseUrl);
		driver.manage().deleteAllCookies(); 
		Accounts_HomepageSignIn(driver);
		driver.get(baseUrl+"/account");
	        String url = "";
	        String homePage = "cleartrip.com";
	        HttpURLConnection huc = null;
	        int respCode = 200;
	        
	        
	        List<WebElement> links = driver.findElements(By.tagName("a"));
	        
	        Iterator<WebElement> it = links.iterator();
	        
	        while(it.hasNext()){
	            
	            url = it.next().getAttribute("href");
	            
	            Reporter.log(url);
	        
	            if(url == null || url.isEmpty()){
	            	Reporter.log("URL is either not configured for anchor tag or it is empty");
	                continue;
	            }
	            
	            if(!url.contains(homePage)){
	            	Reporter.log("URL belongs to another domain, skipping it.");
	                continue;
	            }
	            
	            try {
	                huc = (HttpURLConnection)(new URL(url).openConnection());
	                
	                huc.setRequestMethod("HEAD");
	                
	                huc.connect();
	                
	                respCode = huc.getResponseCode();
	                
	                if(respCode != 400){
	                	Reporter.log(url+" is a broken link");
	                	//Assert.assertTrue(false);
	                }
	                else{
	                	Reporter.log(url+" is a valid link");
	                }
	                    
	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
       }

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}
