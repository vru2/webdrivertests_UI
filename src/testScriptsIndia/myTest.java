package testScriptsIndia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class myTest {
	
	
	WebDriver driver;
	@Test
	@Parameters("browser")
	
	public void crossbrowser (String BrowserName){
		if(BrowserName.equalsIgnoreCase("firefox")){
			driver= new HtmlUnitDriver();
		}
		else if (BrowserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "E:\\Seleniumct\\exe\\chromedriver.exe");
			driver=new ChromeDriver();
			}
		else if (BrowserName.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver","exe\\IEDriverServer64.exe");
			driver=new InternetExplorerDriver();
			}

			driver.manage().window().maximize();
			
			driver.get("www.cleartrip.com");
			//System.out.println(driver.getTitle());
			driver.quit();
		
	}

}
