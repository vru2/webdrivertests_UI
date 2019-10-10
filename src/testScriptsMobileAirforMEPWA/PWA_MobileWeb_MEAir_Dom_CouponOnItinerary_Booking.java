
	

	package testScriptsMobileAirforMEPWA;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.ITestResult;
	import org.testng.Reporter;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import dataServices.MobileDataProvider;
	import domainServices.Mobile;
	public class PWA_MobileWeb_MEAir_Dom_CouponOnItinerary_Booking extends Mobile {
			public RemoteWebDriver driver;
			private String baseUrl;
			String srpprice="";
			String paymentpage;
			boolean pgcheck=false;
		String pgfees;
		  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Dom_OW_CP", groups={ "Smoke Test"})
		  public void pwa_MobileWeb_Air_Dom_SignIn_OW_40213(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3,String couponcode) throws Exception {
			 System.out.println(baseUrl);
			 driver.get(common.value("pwaairurlAE"));
			 if(elementVisible(driver,By.xpath("//body/div/div/div/h3"),4)){
				  driver.findElement(By.id("english_select")).click();
				  
			  }
			 
			 Reporter.log("PWA_MobileWeb_MEAir_Dom_CouponOnItinerary_Booking",true);
			 
		//	 mobileCom_SignIn(driver);
			 driver.get(baseUrl);
			 pwa_Air_Homepage(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants,"");
			 pwa_MEAir_SRP(driver,"","all");
			 pwa_MEAir_ReviewItineraryCoupon(driver,couponcode);
			 System.out.println("Coupon applied on Review Page");
			 pwa_MEAir_Unsigned_Bookstep2(driver,Adults,Childrens,Infants,"");
			 System.out.println("Travelers detailsadded here ");
			
			 pwa_MEAir_MakePayment(driver,"CREDITCARD","","");
			  }

		  @BeforeClass
		  public void setUp() throws Exception {
			driver=getMobileDriver(driver);
			baseUrl = common.value("pwaairurlAE");
		  }

		  @AfterMethod(alwaysRun = true)
		  public void afterMethod(ITestResult _result) throws Exception {
		  	afterMethod(driver, _result);
		  }
		  @AfterClass(alwaysRun = true)
		  public void tearDown() throws Exception {
			 // driver.quit();    
		  }






		}











