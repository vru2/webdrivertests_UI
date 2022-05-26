package test.java.ct_config;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CT_Config_AddObjofObj extends CT_CONFIG_COMMON {
	public RemoteWebDriver driver = null;
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}
	
	 @Test
		public void ct_config() throws Exception {
	     String jsonvalue="{\"street_address\":\"Suraj Ganga Soft Park\",\"city\":\"Bengaluru\",\"state\":\"Karnataka\"}";
         String jsonvalueschema="{ \"type\": \"object\", \"properties\": { \"street_address\": { \"type\": \"string\" }, \"city\": { \"type\": \"string\" }, \"state\": { \"type\": \"string\" }},\"required\": [\"street_address\", \"city\", \"state\"]} ";
		 Login(driver);
		 choose_verticle1(driver);

		// Add, Update and Delete Object Object String
		   add_property(driver,"objproperty12","obj","objprop","test","","","object","object","","");
		   update_property(driver,"objproperty12","tester","object","object","");
		   add_property_to_existingproperty(driver,"objproperty12","testadd","testjsonn","test",jsonvalue,"object","JSON","object",jsonvalueschema);
		   delete_property(driver,"objproperty12","object","object","");
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

