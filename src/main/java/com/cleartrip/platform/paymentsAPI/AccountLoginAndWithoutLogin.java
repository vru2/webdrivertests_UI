package com.cleartrip.platform.paymentsAPI;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import domainServices.PaymentNodeJS;
import io.restassured.response.Response;

public class AccountLoginAndWithoutLogin extends PaymentNodeJS{

	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;

	@Test
	public void testingWithAValidAccountLogin() throws Exception{

		try{
			resp = payUIget("BookApp/GetPayWithCookie","");
			qaUrl = qaurl;
			Url = qaUrl+ fetchPaymentURL(resp);
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies();
			driver.get(Url);
			Thread.sleep(10000);
		}

		catch(Exception e){
			System.out.println(e);
			Reporter.log("Exception is"+e);
		}

	}

}
