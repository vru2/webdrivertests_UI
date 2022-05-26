package test.java.ct_config;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CT_Config_AddJSON extends CT_CONFIG_COMMON {
	public RemoteWebDriver driver = null;
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}
	
	 @Test
		public void ct_config() throws Exception {
		  String jsonvalue="{\"street_address\":\"Suraj Ganga Soft Park\",\"city\":\"Bengaluru\",\"state\":\"Karnataka\"}";
		     String jsonvalue1="{\"street_address\":\"Suraj Ganga Park\",\"city\":\"Bengaluru\",\"state\":\"Karnataka\"}";
		     String jsonvalueschema="{ \"type\": \"object\", \"properties\": { \"street_address\": { \"type\": \"string\" }, \"city\": { \"type\": \"string\" }, \"state\": { \"type\": \"string\" }},\"required\": [\"street_address\", \"city\", \"state\"]} ";
       
		 Login(driver);
		 choose_verticle1(driver);

		// Add, Update and Delete JSON
		   add_property(driver,"jsonproperty","","",jsonvalue,"","","JSON","","",jsonvalueschema);
		   update_property(driver,"jsonproperty",jsonvalue1,"JSON","","");
		   delete_property(driver,"jsonproperty","","","");		 
	 }
	 @AfterClass
		public void closeSelenium() throws Exception {
		 browserClose(driver);
		}

		@AfterMethod(alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}

}
