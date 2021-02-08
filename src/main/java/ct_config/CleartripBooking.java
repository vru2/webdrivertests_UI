package ct_config;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CleartripBooking extends CT_CONFIG_COMMON {
	public RemoteWebDriver driver = null;

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}

	@Test
	public void ct_config() throws Exception {
		driver.get(baseUrl);
		elementPresent_log(driver, By.xpath("//a[@id='userAccountLink']/span[2]"), "Your trip", 40);
		driver.findElementByXPath("//a[@id='userAccountLink']/span[2]").click();
		driver.findElementById("SignIn").click();
		Thread.sleep(2000);
		driver.switchTo().frame("modal_window");
		safeType(driver, By.id("email"), username);
		safeType(driver, By.id("password"), pwd);
		safeClick(driver, By.id("signInButton"));
		Thread.sleep(2000);
		driver.get("https://qa2.cleartrip.com/flights/results?adults=1&childs=0&infants=0&depart_date=15/01/2022&return_date=&intl=n&from=MAA&to=HYD&class=Economy&airline=&carrier=&sd=1611035082602&page=&sellingCountry=IN&ssfi=&flexi_search=&ssfc=&origin=MAA%20-%20Chennai,%20IN%20&destination=HYD%20-%20Hyderabad,%20IN");
		Thread.sleep(6000);
		elementPresent_log(driver,By.xpath("//div[4]/button"),"Book",40);
		Cookie bentoitn=new Cookie("forcedBentoItn","true");
		driver.manage().addCookie(bentoitn);
		driver.findElementByXPath("//div[4]/button").click();
		Thread.sleep(6000);
		String parent=driver.getWindowHandle();
		Set<String>s1=driver.getWindowHandles();
		Iterator<String> I1= s1.iterator();
		while(I1.hasNext())
		{
		  String child_window=I1.next();
		  if(!parent.equals(child_window))
		  {
		    driver.switchTo().window(child_window);
		    System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
		    safeClick(driver,By.xpath("//div[24]/div/button"));
		    if(elementPresent_log(driver,By.xpath("//div[2]/div/input"),"Mobile Number",10))
		    {
		     safeClick(driver,By.xpath("//div[2]/div/input"));
		     safeType(driver,By.xpath("//div[2]/div/input"),"9663949690");
		     safeClick(driver,By.xpath("//div[5]/button"));
		    }
		    else
		    {
		     elementPresent_log(driver,By.xpath("//div[3]/div/div/div/div/input"),"FirstName",10);
		    }
		    safeClick(driver,By.xpath("//div[3]/div/div/div/div/input"));
		    safeType(driver,By.xpath("//div[3]/div/div/div/div/input"),"Testert");
		    safeClick(driver,By.xpath("//div[3]/div[2]/div/input"));
		    safeType(driver,By.xpath("//div[3]/div[2]/div/input"),"test");
		    safeClick(driver,By.xpath("//div[3]/div/div/button"));
		    safeClick(driver,By.xpath("//div[3]/div/div/div/ul/li[2]"));
		    safeClick(driver,By.xpath("//div[7]/div/button"));
		    elementPresent_log(driver,By.id("CVV_4129"),"CVV",30);
		    Cookie bentopayment=new Cookie("isBentoPayment","true");
		    driver.manage().addCookie(bentopayment);
		    driver.navigate().refresh();
		    textPresent_Log(driver,"Pay to complete your booking",30);
		    safeClick(driver,By.id("CVV_4129"));
		    safeType(driver,By.id("CVV_4129"),"1234");
		    safeClick(driver,By.xpath("//button"));
		    textPresent_Log(driver,"Please wait...",10);
		    textPresent_Log(driver,"American",20);
		    textPresent_Log(driver,"ACS Emulator",20);
		    safeClick(driver,By.xpath("//input[@value='Submit']"));
		    textPresent_Log(driver,"Your booking is done",50);
		    String tripid=driver.findElement(By.xpath("//b")).getText();
		    System.out.println(tripid);
		    driver.close();
		  }
		}
		driver.quit();

	}

}