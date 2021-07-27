package Payments_PWA_UI;
import domainServices.Mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class Mobile_Air_Dom_Expressway_GST_Signedin_Newcard_4 extends Mobile{



	public RemoteWebDriver driver;
	private String baseUrl;

	@Test(dataProviderClass=MobileDataProvider.class,dataProvider="MobileCom_Dom_OW")

	public void mobileCom_Air_Dom_Expressway(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String GoAir, String SpiceJet, String Indigo) throws Exception {
		Thread.sleep(5000);
		
		driver.get(baseUrl);
		driver.manage().window().maximize();
		pwa_Air_Homepage(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants,"");
		Thread.sleep(2000);
		mobileCom_Air_SRP_Oneway1(driver,"SpiceJet");
		pwa_Air_ReviewItineraryPage(driver);
		Thread.sleep(2000);
		mobileCom_Air_Expressway_Itinerary_GST(driver,"SpiceJet",true);
		pwa_Air_MakePayment(driver,"Net Banking","","");
		

	}
	@BeforeClass
	public void setUp() throws Exception {
		driver=getMobileDriver3(driver);
		baseUrl = common.value("pwaairurl");
	}

	
	@AfterClass
	public void tearDown() throws Exception {
		 driver.quit();
	}





}
