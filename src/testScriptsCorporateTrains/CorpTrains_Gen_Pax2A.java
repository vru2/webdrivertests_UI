// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - MARCH, 2017
// Author - SaiLikhitha
// Copyright © 2016 Cleartrip (P) Ltd. All right reserved.

package testScriptsCorporateTrains;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Trains;

public class CorpTrains_Gen_Pax2A extends Trains{

	public RemoteWebDriver driver;
	private String baseUrl;

	@DataProvider
	  public static Object [ ][ ] CorpTrains_Gen_2A() {
	      return new Object [ ] [ ] { { "New Delhi","Chennai Central","Sleeper (SL)","10","General","Same","G T Express","2","0","0","0","NETBANKING","Corp Trains  SignIn General Booking with Adult=2","Your Booking is confirmed","Cancel"}};
	     
	  };
	  
	@Test (dataProvider="CorpTrains_Gen_2A")	
	public void CorpTrains_Gen_2A(String DStation, String AStaiton, String Class, String TDate,String Quota,String BoardStation,String TrainsName,String Adult1,String Child1,String SMen,String SWomen,String Payment_Type, 
			String Logger_Msg, String Booking_Confirmation_Message , String IRCTC_Action) throws Exception{
		
		driver.get(CorpTrains_Url());
		corpTrains_SignIn(driver);
		CorpTrains_HomepageSearch(driver,"New Delhi",AStaiton,Class,TDate,Adult1,Child1,SMen,SWomen);
		CorpTrains_CheckAvail(driver,Quota,BoardStation,TrainsName);
		CorpTrains_Bookstep1(driver,BoardStation);
		CorpTrains_Bookstep2(driver,Adult1,Child1,SMen,SWomen);
		corpTrainsPayment(driver,Payment_Type,"",Logger_Msg,"");
		}
	
	
	  @BeforeClass
	  public void setUp() throws Exception {
			driver=(RemoteWebDriver) getDriver(driver);
	  }

	  @AfterMethod (alwaysRun = true)
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	  }
	  
	  @AfterClass
	  public void tearDown() throws Exception {
		  browserClose(driver);
	  }

}
