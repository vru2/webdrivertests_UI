package ct_config;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CT_Config_AddObjects extends CT_CONFIG_COMMON {
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
		 choose_verticle(driver);
		// Add, Update and Delete Object Int
		   add_property(driver,"objproperty","objint","","827","","","object","number","integer","");
		   update_property(driver,"objproperty","2662","object","number","");
		   add_property_to_existingproperty(driver,"objproperty","testadd","","test","","object","","","");
		   delete_property(driver,"objproperty","object","number","");
		   
		// Add, Update and Delete Object Float
		   add_property(driver,"objproperty1","objfloat","","1272.16616","","","object","number","float","");
		   update_property(driver,"objproperty1","5353.26622","object","number","");
		   delete_property(driver,"objproperty1","object","number","");
		   
		// Add, Update and Delete Object URL
		   add_property(driver,"objproperty2","objurl","","http://www.cleartrip.com","","","object","string","url","");
		   update_property(driver,"objproperty2","http://qa2.cleartrip.com","object","string","");
		   delete_property(driver,"objproperty2","object","string","");
		   
		// Add, Update and Delete Object IP
		   add_property(driver,"objproperty3","objip","","173.12.16.23","","","object","string","ip","");
		   update_property(driver,"objproperty3","172.22.11.12","object","string","");
		   delete_property(driver,"objproperty3","object","string","");
			   
		// Add, Update and Delete Object RelativePath
		   add_property(driver,"objproperty4","objrelpath","","/","","","object","string","relativePath","");
		   update_property(driver,"objproperty4","/documents","object","string","");
		   delete_property(driver,"objproperty4","object","string","");
			   
		// Add, Update and Delete Object normalString
		   add_property(driver,"objproperty5","objstrng","","test","","","object","string","normalString","");
		   update_property(driver,"objproperty5","tester","object","string","");
		   delete_property(driver,"objproperty5","object","string","");
		   
		// Add, Update and Delete Object Boolean
		   add_property(driver,"objproperty6","objbool","","","","","object","boolean","","");
		   update_property(driver,"objproperty6","","object","boolean","");
		   delete_property(driver,"objproperty6","object","boolean","");
			   
		// Add, Update and Delete Object JSON
		   add_property(driver,"objproperty7","objjson","",jsonvalue,"","","object","JSON","",jsonvalueschema);
		   update_property(driver,"objproperty7",jsonvalue1,"object","JSON","");
		   delete_property(driver,"objproperty7","object","JSON","");
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

