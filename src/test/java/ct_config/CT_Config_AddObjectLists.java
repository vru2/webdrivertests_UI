package test.java.ct_config;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CT_Config_AddObjectLists extends CT_CONFIG_COMMON {
	public RemoteWebDriver driver = null;
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}
	
	 @Test
		public void ct_config() throws Exception {
		 Login(driver);
		 choose_verticle(driver);

		// Add, Update and Delete Object IntList
		   add_property(driver,"objproperty8","objintlist","","","823","247","object","list","intlist","");
		   update_property(driver,"objproperty8","8272","object","list","");
		   delete_property(driver,"objproperty8","object","list","");
		   
		// Add, Update and Delete Object FloatList
		   add_property(driver,"objproperty9","objfloatlist","","","927723.266226","2826.25525","object","list","floatlist","");
		   update_property(driver,"objproperty9","1525.166161","object","list","");
		   delete_property(driver,"objproperty9","object","list","");
			   
		// Add, Update and Delete Object StringList
		   add_property(driver,"objproperty10","objstringlist","","","test","testing","object","list","stringlist","");
		   update_property(driver,"objproperty10","tester","object","list","");
		   delete_property(driver,"objproperty10","object","list","");
		   
		// Add, Update and Delete Object BooelanList
		   add_property(driver,"objproperty11","objboollist","","","true","true","object","list","booleanlist","");
		   update_property(driver,"objproperty11","","object","list","boolean");
		   delete_property(driver,"objproperty11","object","list","");
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

