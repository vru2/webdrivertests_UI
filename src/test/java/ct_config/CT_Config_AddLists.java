package test.java.  ct_config;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CT_Config_AddLists extends CT_CONFIG_COMMON {
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

		// Add, Update and Delete IntList
		   add_property(driver,"intlistproperty","","","","12","14","list","intlist","","");
		   update_property(driver,"intlistproperty","92772","list","","");
		   add_property_to_existingproperty(driver,"intlistproperty","","","726","","list","","","");
		   delete_property(driver,"intlistproperty","list","",""); 
		   
		// Add, Update and Delete FloatList
		   add_property(driver,"floatlistproperty","","","","1772.2662","155228.172772","list","floatlist","","");
		   update_property(driver,"floatlistproperty","25522.26262","list","","");
		   add_property_to_existingproperty(driver,"floatlistproperty","","","252552.27727","","list","","","");
		   delete_property(driver,"floatlistproperty","list","","");
		   
		// Add, Update and Delete StringList
		   add_property(driver,"stringlistproperty","","","","test","testing","list","stringlist","","");
		   update_property(driver,"stringlistproperty","tester","list","","");
		   add_property_to_existingproperty(driver,"stringlistproperty","","","test","","list","","","");
		   delete_property(driver,"stringlistproperty","list","","");
		   
		// Add, Update and Delete BooleanList
		   add_property(driver,"boollistproperty","","","","true","true","list","booleanlist","","");
		   update_property(driver,"boollistproperty","","list","boolean","");
		   add_property_to_existingproperty(driver,"boollistproperty","","","","","list","","boolean","");
		   delete_property(driver,"boollistproperty","list","","");
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
