package testScriptsCorpIndia;


	import org.openqa.selenium.By;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.testng.Assert;
	import org.testng.ITestResult;
	import org.testng.Reporter;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	import domainServices.Corporate;

	public class AirCorp_Dom_OW_With_SameGstandState extends Corporate {
		public RemoteWebDriver driver;
		boolean flightCountFailure = true;
		String domain = "com";

		
		@DataProvider
		public static Object[][] AirCorp() {
			return new Object[][] { { "BOM", "DEL", "1", "0", "0","CC" } };
		}

		@Test(dataProvider = "AirCorp")
		public void airCorpDom_with_SameGstandState(String FromCity, String ToCity, String Adults, String Childrens, String Infants, String Payment_Type) throws Exception {

			driver.manage().deleteAllCookies();
			String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", "" , 40, 41);
			driver.get(SRP_URL);
			corpAir_SRP(driver, "DOMOW", "");
			if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 30)){
				Reporter.log("Itinerary Page has not loaded");
			}
			elementVisible(driver, By.id("rtTotalAmount"), 20, "Price is not displayed in Itinerary");
			safeUncheck(driver, getObject("air_step1_insurance_chkbox"));
			int totalPrice_Int  = priceCapture(driver, By.id("rtTotalAmount"), 20);
			Reporter.log("Price in Itinerary page : "+totalPrice_Int);
			corpAir_ItineraryPage(driver);
			
			corpAir_TravellerPage(driver, Adults, Childrens, Infants,"true","true");
			
			
			for(int i=0;i<=10;i++) {
				if(elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), 1)) {
					break;
				} else if(elementVisible(driver, By.xpath("(//a[contains(text(),'Modify search')])[2]"), 1)) {
					Reporter.log("Page has redirected back to SRP after clicking on continue in Traveller page");
					Assert.assertTrue(false);
				}
			}
			safeClick(driver, getObject("AirCorp_PaymentPage_CreditCard_Tab"));
			elementVisible(driver, By.id("processingFeeAmount"), 20, "Price is not displayed in PaymentPage");
			int price_Con_Int = priceCapture(driver, By.id("processingFeeAmount"), 20);
			int totalPrice_Con_Int = priceCapture(driver, By.id("formTotal"), 20);
			Reporter.log("PG Fee in Payment Step : "+price_Con_Int);
			Reporter.log("PG Fee + Price in Payment Step : "+totalPrice_Con_Int);
			if(!(totalPrice_Int+price_Con_Int == totalPrice_Con_Int)) {
				Reporter.log("Price + PG fee is not matching in Payment Step");
				Assert.assertTrue(false);
			} else Reporter.log("Price + PG fee is matching in Payment Step");
			AirCorp_Paymentpage(driver, Payment_Type, "", "Corp Dom OW PG fee : ");
			
			/*// css=dd.price_total
				for(int j=1; j<=4;j++) {
					String textXpath = "//div[2]/div/dl/dt["+j+"]";
					String text = getText(driver, By.xpath(textXpath));
						if(text.contains("Total")) {
							String totalPriceXpath = "//div[2]/div/dl/dd["+j+"]";
							int totalPrice_Conf_Int = priceCapture(driver, By.xpath(totalPriceXpath), 20);
							Reporter.log("Total Price in Confirmation Page : "+totalPrice_Conf_Int);						
								if(!(totalPrice_Conf_Int==totalPrice_Con_Int)) {
									Reporter.log("Price in confirmation page doesnt match with price in payment page");
									Assert.assertTrue(false);
								}
						}
				}*/
		}

		@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
		
		

		@BeforeClass
		public void setUp() throws Exception {
			driver = (RemoteWebDriver) getDriver(driver);
			baseUrl = getBaseUrl(domain);
		}
		
		@AfterClass
		public void tearDown() throws Exception {
			browserClose(driver);
		}
	}

