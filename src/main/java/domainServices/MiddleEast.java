// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - MAR, 2015
// Author - Mohamed Faisal
// Copyright © 2012 cleartrip Travel. All right reserved.
package domainServices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import commonServices.WrapperMethod;

public class MiddleEast extends WrapperMethod {
	public RemoteWebDriver driver;
	public void refmetsample(WebDriver driver) {
		driver.findElement(By.linkText("People")).click();
	     driver.findElement(By.linkText("People")).click();
	     driver.findElement(By.linkText("People")).click();
	     driver.findElement(By.linkText("People")).click();
	     driver.findElement(By.linkText("People")).click();
	}

}
