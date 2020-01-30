package ct_config;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domains.HQ;

public class CT_Config extends CT_CONFIG_COMMON {
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
			
			// Add, Update and Delete Integer
			   add_property(driver,"intproperty","","","12","","","number","integer","","");
			   update_property(driver,"intproperty","15","","","");
			   delete_property(driver,"intproperty","","","");
			
		    // Add, Update and Delete Float
			   add_property(driver,"floatproperty","","","1262.16262","","","number","floatprop","","");
			   update_property(driver,"floatproperty","19179.16612","","","");
			   delete_property(driver,"floatproperty","","","");
			  
			  
		    // Add, Update and Delete URL
			   add_property(driver,"urlproperty","","","http://www.cleartrip.com","","","string","url","","");
			   update_property(driver,"urlproperty","http://qa2.cleartrip.com","","","");
			   delete_property(driver,"urlproperty","","","");
			  
		    // Add, Update and Delete IP
			   add_property(driver,"ipproperty","","","172.23.12.26","","","string","ip","","");
			   update_property(driver,"ipproperty","172.23.11.26","","","");
			   delete_property(driver,"ipproperty","","","");
			   
			   
			// Add, Update and Delete RelativePath
			   add_property(driver,"relpathproperty","","","/documents","","","string","relativePath","","");
			   update_property(driver,"relpathproperty","/document","","","");
			   delete_property(driver,"relpathproperty","","","");
		    
		    // Add, Update and Delete noramlString   
			   add_property(driver,"stringproperty","","","test","","","string","normalString","","");
			   update_property(driver,"stringproperty","tester","","","");
			   delete_property(driver,"stringproperty","","","");
			   
			// Add, Update and Delete Boolean
			   add_property(driver,"boolproperty","","","","","","boolean","","","");
			   update_property(driver,"boolproperty","","boolean","","");
			   delete_property(driver,"boolproperty","","","");
			   
			// Add, Update and Delete JSON
			   add_property(driver,"jsonproperty","","",jsonvalue,"","","JSON","","",jsonvalueschema);
			   update_property(driver,"jsonproperty",jsonvalue1,"JSON","","");
			   delete_property(driver,"jsonproperty","","","");
				   
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
				   
			// Add, Update and Delete Object Object String
			   add_property(driver,"objproperty12","obj","objprop","test","","","object","object","","");
			   update_property(driver,"objproperty12","tester","object","object","");
			   add_property_to_existingproperty(driver,"objproperty12","testadd","testjsonn","test",jsonvalue,"object","JSON","object",jsonvalueschema);
			   delete_property(driver,"objproperty12","object","object","");

		    
		    //revert
		       property_revert(driver,"revertprop","test","","add");
		       property_revert(driver,"revertprop","","tester","update");
		       property_revert(driver,"revertprop","","","revert");
		       property_revert(driver,"revertprop","","","delete");
			
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
