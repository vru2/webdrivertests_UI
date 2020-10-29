package ct_admin;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import common.WrapperMethod;

public class CT_Admin_Common extends WrapperMethod {

	
	public RemoteWebDriver driver = null;
	static String url="/hq/ct-admin/recordList";
	String username="varalakshmi.venkateshaiah@cleartrip.com";
	String pwd="Cleartrip@123";
	String s;
	
	
	
	public void HQSignIn(RemoteWebDriver driver) throws Exception {
	    safeType(driver, By.id("email"), username);
		safeType(driver,By.id("password"),pwd);//option[@value='20']
		safeClick(driver,By.id("signInButton"));
		elementVisible(driver, By.linkText("Trips"), 20);
		
	}
	public void LoginHQ(RemoteWebDriver driver) throws Exception {
		driver.get(baseUrl+"/hq");
		elementPresent_log(driver,By.id("email"),"Email textbox is visible",50);
		System.out.println("hq loaded");
		Reporter.log("hq loaded");
		HQSignIn(driver);
		System.out.println("Signed into hq");
		Reporter.log("Signed into hq");
		 logURL(driver);	
		 driver.get(baseUrl+url);
		 System.out.println("ct-admin url launched");
		Reporter.log("ct-admin url launched");
		elementPresent_log(driver,By.id("jsonfiles"),"Resource drop is visible",20);
	}
	
	public void ct_admin_recordList(RemoteWebDriver driver) throws Exception {
		textPresent_Log(driver,"Recordlist",10);
		System.out.println("ct-admin ui loaded");
		Reporter.log("ct-admin ui loaded");
		safeClick(driver,By.id("jsonfiles"));
		safeSelect(driver,By.xpath("//select[@id='jsonfiles']"),"walletCredentials");
		textPresent_Log(driver,"Primary",20);
		elementPresent_log(driver,By.id("jsonfiles"),"walletCredentials",10);
		Reporter.log("WalletCredentails ui is loaded");
		System.out.println("WalletCredentails ui is loaded");
		safeClick(driver,By.id("jsonfiles"));
		safeSelect(driver,By.xpath("//select[@id='jsonfiles']"),"nbCredentials");
		textPresent_Log(driver,"IN_CITI",20);
		elementPresent_log(driver,By.id("reqId0"),"Deactivate",10);
		Reporter.log("nbCredentials ui loaded");
		System.out.println("nbCredentials ui loaded");
	}
	
	public void ct_admin_recordListUpdate(RemoteWebDriver driver) throws Exception
	{
		safeClick(driver,By.id("jsonfiles"));
		safeSelect(driver,By.xpath("//select[@id='jsonfiles']"),"pgCredentials");
		elementPresent_log(driver,By.linkText("Tests_HDFC"),"Tests_HDFC",5);
		Reporter.log("pgCredentials ui loaded");
		System.out.println("pgCredentials ui loaded");
		safeClick(driver,By.linkText("Tests_HDFC"));
		textPresent_Log(driver,"UPDATE pgCredentials",10);
		elementPresent_log(driver,By.xpath("//input[@value='Update']"),"Update",5);
		Reporter.log("update pgCredentials page ui loaded");
		System.out.println("update pgCredentials page ui loaded");
		safeClick(driver,By.id("txt308"));
		s=RandomStringUtils.randomNumeric(2);
		String r="cleartrip"+s;
		safeType(driver,By.id("txt308"),r);
		Reporter.log("value entered");
		System.out.println("value entered");
		safeClick(driver,By.xpath("//input[@value='Update']"));
		Reporter.log("Clicked on Update");
		System.out.println("Clicked on Update");
		elementPresent_log(driver,By.linkText("Tests_HDFC"),"Tests_HDFC",10);
		safeClick(driver,By.linkText("Tests_HDFC"));
		textPresent_Log(driver,"UPDATE pgCredentials",10);
		//String k=driver.findElement(By.id("txt308")).getAttribute()
		//System.out.println(k.toString());
		
		  if(textPresent(driver,r,5)) 
		  { 
		   Reporter.log("Update was successfull");
		   System.out.println("Update was successfull"); 
		  }else {
		  Reporter.log("Update was not successfull");
		  System.out.println("Update was not successfull"); 
		  Assert.assertTrue(false); 
		}
}
	
	

}
