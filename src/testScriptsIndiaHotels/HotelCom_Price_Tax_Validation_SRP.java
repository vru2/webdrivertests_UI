// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.IndiaHotels;

	public class HotelCom_Price_Tax_Validation_SRP extends IndiaHotels{
	public RemoteWebDriver driver;	
	
	@Test 
	  public void HotelCom_Tax0() throws Exception {
	 	   taxValidation(driver, "com", "Pune", "Maharashtra", "",  "1", "0", "Hotel Celebrations Inn");	
	  }
	  
	  @Test 
	  public void HotelCom_Tax12() throws Exception {
	 	   taxValidation(driver, "com", "Pune", "Maharashtra", "",  "1", "1", "Hotel Kamlesh");	
	  }
	  
	  @Test 
	  public void HotelCom_Tax18() throws Exception {
	 	   taxValidation(driver, "com", "Pune", "Maharashtra", "",  "1", "0", "Hotel Basera");	
	  }
  
	  @Test 
	  public void HotelCom_Tax28() throws Exception {
	 	   taxValidation(driver, "com", "Pune", "Maharashtra", "",  "1", "1", "Hotel Basera");	
	  }
  
  
  public void taxValidation (RemoteWebDriver driver, String Domain, String City, String State, String Country, String Adults, String Children, String HotelName) throws Exception {
	  SRP_URL_HotelName_Pax(driver, Domain, City, State, Country,  Adults, Children, HotelName);
 	  for(int i=0; i<=10; i++){
			if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 1)) {
				System.out.println("Hotel results are displayed");
				break;
		} else {
				if(textPresent(driver, "Sorry, our system is acting up", 1)){
					System.out.println("Sorry, our system is acting up message is displayed in SRP");
					Assert.assertTrue(false);
				}
		}
	}
 	 int TaxPer = 0; 	
 	String RoomPrice = getText(driver, By.xpath("//h2[2]/div/strong"));
 	RoomPrice = RoomPrice.replaceAll("\\D+","");
 	int RoomPriceInt = Integer.parseInt(RoomPrice);
 	TaxPer=  TaxSlab(RoomPriceInt);
 	System.out.println("Per Room Price : "+RoomPriceInt+" Tax % : "+TaxPer);	
 	if(TaxPer!=0) { 	
 	int TaxCalculated = (RoomPriceInt*TaxPer)/100;
 	int PriceCalculated = TaxCalculated+RoomPriceInt;
 	System.out.println("Tax amt calculated : "+TaxCalculated);
 	System.out.println("Price calculated ( RoomPrice + Tax) "+PriceCalculated);
 	String TaxDisplayed = getText(driver, By.cssSelector("small.additionalTaxes"));
 	TaxDisplayed = TaxDisplayed.replaceAll("\\D+","");
 	int TaxDisplayedInt = Integer.parseInt(TaxDisplayed); 	
 	System.out.println("Tax Displayed : "+TaxDisplayedInt+" Tax Calculated : "+TaxCalculated);
 	if(TaxCalculated!=TaxDisplayedInt) {
 		Assert.assertTrue(false);
 	}
 	safeClick(driver, By.xpath("(//a[contains(text(),'Total')])[2]"));
 	Thread.sleep(2000);
 	String TotalPriceDisp = getText(driver, By.cssSelector("#totPriceDisp > div > strong"));
 	TotalPriceDisp = TotalPriceDisp.replaceAll("\\D+", "");
 	int TotalPriceDispInt = Integer.parseInt(TotalPriceDisp);
 	System.out.println("Total Displayed : "+TotalPriceDispInt+" Total Calculated : "+PriceCalculated); 	
 	Assert.assertEquals(TotalPriceDispInt, PriceCalculated, "Price not Matching");
 	if(TotalPriceDispInt!=PriceCalculated) {
 		Assert.assertTrue(false);
 	}
 	}
 	
 	else {
 		safeClick(driver, By.xpath("(//a[contains(text(),'Total')])[2]"));
 		Thread.sleep(5210);
 	 	String TotalPriceDisp = getText(driver, By.cssSelector("#totPriceDisp > div > strong"));
 	 	TotalPriceDisp = TotalPriceDisp.replaceAll("\\D+", "");
 	 	int TotalPriceInt = Integer.parseInt(TotalPriceDisp);
 	 	if(TotalPriceInt!=RoomPriceInt) {
 	 		System.out.println("Total Calculated : "+TotalPriceInt+" Total Displayed : "+RoomPriceInt);
 	 		Assert.assertTrue(false);
 	 	}
 	}
  }
  
  public int TaxSlab(int Price) {
	  int Tax =0;
	  if(Price<1000) {
		  Tax = 0;
	 	} else if(Price>=1000&&Price<2500) {
	 		Tax = 12;
	 	} else if(Price>=2500&&Price<7500) {
	 		Tax = 18;
	 	} else if(Price>=7500) {
	 		Tax = 28;
	 	}
	return Tax;	  
  }

  @BeforeClass
  public void setUp() throws Exception {
	  driver=(RemoteWebDriver) getDriver(driver);
  }

  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);	  
  }

}