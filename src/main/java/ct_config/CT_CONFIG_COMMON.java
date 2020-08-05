package ct_config;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import common.WrapperMethod;
import domains.HQ;

public class CT_CONFIG_COMMON extends HQ{

	public RemoteWebDriver driver = null;
	static String url="/hq/ct-admin/recordList";
	String username="varalakshmi.venkateshaiah@cleartrip.com";
	String pwd="Cleartrip@123";
	String s;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	
	
	public void HQSignIn(RemoteWebDriver driver) throws Exception {
	    safeType(driver, By.id("email"), username);
		safeType(driver,By.id("password"),pwd);//option[@value='20']
		safeClick(driver,By.id("signInButton"));
		elementVisible(driver, By.linkText("Trips"), 20);
		
	}
	
	public void Login(RemoteWebDriver driver) throws Exception{
		driver.get(baseUrl+"/hq");
		System.out.println("hq loaded");
		HQSignIn(driver);
	    System.out.println("Signed into hq");
	    logURL(driver);	
	    driver.get(baseUrl+"/hq/ct-config");
	    System.out.println("ct-config url launched");
	}
	public void choose_verticle(RemoteWebDriver driver) throws Exception{
		textPresent_Log(driver,"select vertical",40);
		elementPresent_log(driver,By.xpath("//div[2]"),"submit Button",20);
		safeClick(driver,By.xpath("//div/div/div/div/div/div/div"));
		safeClick(driver,By.xpath("//div[3]/ul/li[4]"));
		Thread.sleep(3000);
		safeClick(driver,By.xpath("//div[2]/div"));
		safeType(driver,By.xpath("//input[@type='text']"),"air-webhook");
		Thread.sleep(1000);
		safeClick(driver,By.xpath("//div[2]/div[3]/ul/li[2]"));
		Thread.sleep(3000);
		safeClick(driver,By.xpath("//div[3]/div"));
		safeClick(driver,By.xpath("//div[3]/div[3]/ul/li"));
		Thread.sleep(2000);
		safeClick(driver,By.xpath("//button"));
		textPresent_Log(driver,"Fetching details for the selected options",40);
		textPresent_Log(driver,"No changes as of yet",60);
		elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
		elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
		System.out.println("Vertical selected");
	}
	public void choose_verticle1(RemoteWebDriver driver) throws Exception{
		textPresent_Log(driver,"select vertical",40);
		elementPresent_log(driver,By.xpath("//div[2]"),"submit Button",20);
		safeClick(driver,By.xpath("//div/div/div/div/div/div/div"));
		safeClick(driver,By.xpath("//div[3]/ul/li[4]"));
		Thread.sleep(3000);
		safeClick(driver,By.xpath("//div[2]/div"));
		safeType(driver,By.xpath("//input[@type='text']"),"air-webhook");
		Thread.sleep(1000);
		safeClick(driver,By.xpath("//div[2]/div[3]/ul/li[2]"));
		Thread.sleep(3000);
		safeClick(driver,By.xpath("//div[3]/div"));
		safeClick(driver,By.xpath("//div[3]/div[3]/ul/li[2]"));
		Thread.sleep(2000);
		safeClick(driver,By.xpath("//button"));
		textPresent_Log(driver,"Fetching details for the selected options",40);
		textPresent_Log(driver,"No changes as of yet",60);
		elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
		elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
		System.out.println("Vertical selected");
	}
	public void add_property(RemoteWebDriver driver,String propertykey,String propertykey1,String propertykey2,String value,String value1,String value2,String datatype,String subtype,String subtype1,String jsonschema) throws Exception{
		safeClick(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"));
		if(datatype.equalsIgnoreCase("number")){
			safeClick(driver,By.xpath("//div/section/div[2]/div/div/div"));
			safeClick(driver,By.xpath("//div[3]/ul/li"));
			if(subtype.equalsIgnoreCase("integer"))
			{
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li"));
				System.out.println("integer selected");
				safeType(driver,By.id("controlName"),propertykey);
				System.out.println("property ket entered");
				safeType(driver,By.id("propertyValue"),value);
				System.out.println("property value entered");
			} else if(subtype.equalsIgnoreCase("floatprop")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[2]"));
				safeType(driver,By.id("controlName"),propertykey);
				safeType(driver,By.id("propertyValue"),value);
			}		
		}
		if(datatype.equalsIgnoreCase("string")){
			safeClick(driver,By.xpath("//div/section/div[2]/div/div/div"));
			safeClick(driver,By.xpath("//div[3]/ul/li[2]"));
			if(subtype.equalsIgnoreCase("url")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li"));
				    safeType(driver,By.id("controlName"),propertykey);
				    safeType(driver,By.id("propertyValue"),value);
			} else if(subtype.equalsIgnoreCase("ip")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[2]"));
				    safeType(driver,By.id("controlName"),propertykey);
				    safeType(driver,By.id("propertyValue"),value);
			} else if(subtype.equalsIgnoreCase("relativePath")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[3]"));
				    safeType(driver,By.id("controlName"),propertykey);
				    safeType(driver,By.id("propertyValue"),value);
			} else if(subtype.equalsIgnoreCase("normalString")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[4]"));
				    safeType(driver,By.id("controlName"),propertykey);
				    safeType(driver,By.id("propertyValue"),value);
			}
		}
		if(datatype.equalsIgnoreCase("boolean")){
			safeClick(driver,By.xpath("//div/span"));
			safeClick(driver,By.xpath("//div[3]/ul/li[5]"));
			safeType(driver,By.id("controlName"),propertykey);
			safeClick(driver,By.xpath("//div[2]/label"));
		}
		
		if(datatype.equalsIgnoreCase("JSON")){
			safeClick(driver,By.xpath("//div/section/div[2]/div/div/div"));
			safeClick(driver,By.xpath("//li[6]"));
			safeType(driver,By.id("controlName"),propertykey);
			safeType(driver,By.id("jsonSchema"),jsonschema);
			safeType(driver,By.id("propertyValue"),value);		
		}
		if(datatype.equalsIgnoreCase("list")){
			safeClick(driver,By.xpath("//div/section/div[2]/div/div/div"));
			safeClick(driver,By.xpath("//div[3]/ul/li[3]"));
			if(subtype.equalsIgnoreCase("intlist")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li"));
				safeType(driver,By.id("controlName"),propertykey);
			    safeType(driver,By.id("propertyValue"),value1+","+value2);		
			}else if(subtype.equalsIgnoreCase("floatlist")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[2]"));
				safeType(driver,By.id("controlName"),propertykey);
			    safeType(driver,By.id("propertyValue"),value1+","+value2);		
			}else if(subtype.equalsIgnoreCase("stringlist")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[3]"));
				safeType(driver,By.id("controlName"),propertykey);
			    safeType(driver,By.id("propertyValue"),value1+","+value2);		
			}else if(subtype.equalsIgnoreCase("booleanlist")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[4]"));
				safeType(driver,By.id("controlName"),propertykey);
			    safeType(driver,By.id("propertyValue"),value1+","+value2);		
			}
		}
		if(datatype.equalsIgnoreCase("object")){
			safeClick(driver,By.xpath("//div[2]/div/div/div"));
			safeClick(driver,By.xpath("//div[3]/ul/li[4]"));
			safeType(driver,By.id("controlName"),propertykey);
			safeClick(driver,By.xpath("//form/div/button"));
			if(subtype.equalsIgnoreCase("number")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li"));
				if(subtype1.equalsIgnoreCase("integer")){
					safeClick(driver,By.xpath("//div[3]/div/div"));
					safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li"));
					safeType(driver,By.id("controlName"),propertykey1);
					safeType(driver,By.id("propertyValue"),value);
					
				}else if(subtype1.equalsIgnoreCase("float")){
					safeClick(driver,By.xpath("//div[3]/div/div"));
					safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li[2]"));
					safeType(driver,By.id("controlName"),propertykey1);
					safeType(driver,By.id("propertyValue"),value);	
				}	
			} else if(subtype.equalsIgnoreCase("string")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[2]"));
				if(subtype1.equalsIgnoreCase("url")){
					safeClick(driver,By.xpath("//div[3]/div/div"));
					safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li"));
					safeType(driver,By.id("controlName"),propertykey1);
					safeType(driver,By.id("propertyValue"),value);	
				}else if(subtype1.equalsIgnoreCase("ip")){
					safeClick(driver,By.xpath("//div[3]/div/div"));
					safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li[2]"));
					safeType(driver,By.id("controlName"),propertykey1);
					safeType(driver,By.id("propertyValue"),value);	
				}else if(subtype1.equalsIgnoreCase("relativePath")){
					safeClick(driver,By.xpath("//div[3]/div/div"));
					safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li[3]"));
					safeType(driver,By.id("controlName"),propertykey1);
					safeType(driver,By.id("propertyValue"),value);	
				}else if(subtype1.equalsIgnoreCase("normalString")){
					safeClick(driver,By.xpath("//div[3]/div/div"));
					safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li[4]"));
					safeType(driver,By.id("controlName"),propertykey1);
					safeType(driver,By.id("propertyValue"),value);	
				}
			} else if(subtype.equalsIgnoreCase("boolean")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[5]"));
				safeType(driver,By.id("controlName"),propertykey1);
				safeClick(driver,By.xpath("//label[2]"));
			} else if(subtype.equalsIgnoreCase("JSON")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[6]"));
				safeType(driver,By.id("controlName"),propertykey1);
				safeType(driver,By.id("jsonSchema"),jsonschema);
				safeType(driver,By.id("propertyValue"),value);
			} else if(subtype.equalsIgnoreCase("list")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[3]"));
				if(subtype1.equalsIgnoreCase("intlist")){
					safeClick(driver,By.xpath("//div[3]/div/div/span"));
					safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li"));
					safeType(driver,By.id("controlName"),propertykey1);
				    safeType(driver,By.id("propertyValue"),value1+","+value2);
				}else if(subtype1.equalsIgnoreCase("floatlist")){
					safeClick(driver,By.xpath("//div[3]/div/div/span"));
					safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li[2]"));
					safeType(driver,By.id("controlName"),propertykey1);
				    safeType(driver,By.id("propertyValue"),value1+","+value2);
				}else if(subtype1.equalsIgnoreCase("stringlist")){
					safeClick(driver,By.xpath("//div[3]/div/div/span"));
					safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li[3]"));
					safeType(driver,By.id("controlName"),propertykey1);
				    safeType(driver,By.id("propertyValue"),value1+","+value2);
				}else if(subtype1.equalsIgnoreCase("booleanlist")){
					safeClick(driver,By.xpath("//div[3]/div/div/span"));
					safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li[4]"));
					safeType(driver,By.id("controlName"),propertykey1);
				    safeType(driver,By.id("propertyValue"),value1+","+value2);
				}
			} else if(subtype.equalsIgnoreCase("object")){
				safeClick(driver,By.xpath("//div[2]/div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[4]"));
				safeType(driver,By.id("controlName"),propertykey1);
				safeClick(driver,By.xpath("//form/div/button"));
				safeClick(driver,By.xpath("//div[2]/div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[2]"));
				safeClick(driver,By.xpath("//div[3]/div/div"));
				safeClick(driver,By.xpath("//div[3]/div/div[3]/ul/li[4]"));
				safeType(driver,By.id("controlName"),propertykey2);
			    safeType(driver,By.id("propertyValue"),value);
			}
			
		}
		if((datatype.equalsIgnoreCase("JSON"))||(datatype.equalsIgnoreCase("object")&&subtype.equals("JSON"))){
			safeClick(driver,By.xpath("//form/div/button"));
			
		}else{
	           safeClick(driver,By.xpath("//form/button"));
	           System.out.println("Clicked on submit");
		     }
	    elementPresent_log(driver,By.xpath("//div[3]/button"),"View changes",5);
	    elementPresent_log(driver,By.xpath("//div/div[2]/button"),"confirm changes",5);
	    elementPresent_log(driver,By.xpath("//div[3]/button[2]"),"discard",5);
	    safeClick(driver,By.xpath("//div[3]/button"));
	    System.out.println("Clicked on view changes");
	    elementPresent_log(driver,By.xpath("//ul/p"),"All changes",10);
	    safeClick(driver,By.cssSelector("svg.sc-jKVCRD.dflrgH > path"));
	    System.out.println("View changes closed");
	    elementPresent_log(driver,By.xpath("//div[3]/button"),"View changes",10);
	    elementPresent_log(driver,By.xpath("//div/div[2]/button"),"confirm changes",5);
	    safeClick(driver,By.xpath("//div/div[2]/button"));
	    System.out.println("Clicked on confirm changes");
	    elementPresent_log(driver,By.xpath("//form/div"),"publish",5);
	    safeType(driver,By.id("commitMessage"),"Add Property");
	    System.out.println("Entered commit message");
	    safeClick(driver,By.xpath("//form/div/button"));
	    System.out.println("Clicked on publish");
	    textPresent_Log(driver,"publishing changes",20);
	    textPresent_Log(driver,"changes published successfully",100);
	    Thread.sleep(5000);
	    textPresent_Log(driver,"No changes as of yet",40);
	    System.out.println("Changes published");
		elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
		elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
		if(datatype.equalsIgnoreCase("list")||(datatype.equalsIgnoreCase("object")&&((subtype.equalsIgnoreCase("number"))||(subtype.equalsIgnoreCase("string"))||(subtype.equalsIgnoreCase("boolean"))||(subtype.equalsIgnoreCase("JSON"))))){
			 safeType(driver,By.xpath("//input[@value='']"),propertykey);
			 safeClick(driver,By.xpath("//div[2]/ul/li"));
			 Thread.sleep(2000);
			 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO"));
			 textPresent_Log(driver,value1,2);
			 textPresent_Log(driver,value2,2);
			    safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
			    Thread.sleep(2000);
			    textPresent_Log(driver,"No changes as of yet",40);
				elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
				elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
			    System.out.println("Datatype "+datatype+" with "+"subtype "+subtype+" Property Added Successfully");
			
		}else if((datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("object"))||(datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("list"))){
			 safeType(driver,By.xpath("//input[@value='']"),propertykey);
			 safeClick(driver,By.xpath("//div[2]/ul/li"));
			 Thread.sleep(2000);
			 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO > path"));
			 Thread.sleep(1000);
			 safeClick(driver,By.cssSelector("ul.PropertyEditorRowNode__open__SbmpR > li.PropertyEditorRowNode__rowItem__29voR > div.PropertyEditorRowNode__leftBlock__2Cxz7 > div.PropertyEditorRowNode__objectOpener__bFjdw > svg.sc-jPPmml.jwDqO"));
			if(datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("object")){
			 textPresent_Log(driver,value,2);
			}else{
			 textPresent_Log(driver,value1,2);
			 textPresent_Log(driver,value2,2);
			}
			    safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
			    Thread.sleep(2000);
			    textPresent_Log(driver,"No changes as of yet",40);
				elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
				elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
			    System.out.println("Datatype "+datatype+" with "+"subtype "+subtype+" Property Added Successfully");
			
		}
		else{
	    safeType(driver,By.xpath("//input[@value='']"),propertykey);
	    safeClick(driver,By.xpath("//div[2]/ul/li"));
	    Thread.sleep(2000);
	    textPresent_Log(driver,value,2);
	    safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
	    Thread.sleep(2000);
	    textPresent_Log(driver,"No changes as of yet",40);
		elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
		elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
	    System.out.println("Datatype "+datatype+" with "+"subtype "+subtype+" Property Added Successfully");
		}
	}
	
	public void update_property(RemoteWebDriver driver,String propertykey,String value,String datatype,String subtype,String subtype1) throws Exception{
		 safeType(driver,By.xpath("//input[@value='']"),propertykey);
		 safeClick(driver,By.xpath("//div[2]/ul/li"));
		 Thread.sleep(2000);
		if(datatype.equalsIgnoreCase("list")||(datatype.equalsIgnoreCase("object")&&((subtype.equalsIgnoreCase("number"))||(subtype.equalsIgnoreCase("string"))||(subtype.equalsIgnoreCase("boolean"))||(subtype.equalsIgnoreCase("JSON"))))){
			 Thread.sleep(2000);
			 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO"));
			 Thread.sleep(1000);
			 safeClick(driver,By.xpath("//div[2]/ul/li"));
			    safeClick(driver,By.xpath("//button[3]"));
			    elementPresent_log(driver,By.xpath("//form"),"update button",20);
			    if(datatype.equalsIgnoreCase("list")){
			    	textPresent_Log(driver,"Data type of Items in list",5);
			    } else{
			    	textPresent_Log(driver,"Data type of the property is saved as",5);
			    }
			    
			    if((datatype.equalsIgnoreCase("list")&&subtype.equalsIgnoreCase("boolean"))){
			    	safeClick(driver,By.xpath("//label[2]"));
			    }else if((datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("boolean"))){
			    	safeClick(driver,By.xpath("//div[2]/label"));
			    }else{
			    	safeType(driver,By.id("propertyValue"),value);
			    }
			    
			}else if((datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("object"))||(datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("list"))){
			 Thread.sleep(2000);
			 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO > path"));
			 Thread.sleep(1000);
			 safeClick(driver,By.cssSelector("ul.PropertyEditorRowNode__open__SbmpR > li.PropertyEditorRowNode__rowItem__29voR > div.PropertyEditorRowNode__leftBlock__2Cxz7 > div.PropertyEditorRowNode__objectOpener__bFjdw > svg.sc-jPPmml.jwDqO"));
             Thread.sleep(1000);
             safeClick(driver,By.xpath("//ul/li/div/div[2]/ul/li/div/div"));
			    safeClick(driver,By.xpath("//button[3]"));
			    elementPresent_log(driver,By.xpath("//form"),"update button",20);
			    if(datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("list")){
			    	textPresent_Log(driver,"Data type of Items in list",20);
			    } else{
			    	textPresent_Log(driver,"Data type of the property is saved as",20);
			    }
			    if(datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("list")&&subtype1.equalsIgnoreCase("boolean")){
			    	safeClick(driver,By.xpath("//label[2]"));
			    }else{
			    safeType(driver,By.id("propertyValue"),value);
			    }
		} else{
			 safeClick(driver,By.xpath("//li/li"));
			 safeClick(driver,By.xpath("//button[3]"));
			 elementPresent_log(driver,By.xpath("//form"),"update button",5);
			 textPresent_Log(driver,"Data type of the property is saved as",5);
			 if(datatype.equalsIgnoreCase("boolean")){
				 safeClick(driver,By.xpath("//label[2]"));
			 } else{
			 safeType(driver,By.id("propertyValue"),value);
			 }
		 }   
		if((datatype.equalsIgnoreCase("JSON"))||(datatype.equalsIgnoreCase("object")&&subtype.equals("JSON"))){
			safeClick(driver,By.xpath("//form/div/button"));
			
		} else{
	           safeClick(driver,By.xpath("//form/button"));
		     }
		    elementPresent_log(driver,By.xpath("//div[3]/button"),"View changes",5);
		    elementPresent_log(driver,By.xpath("//div/div[2]/button"),"confirm changes",5);
		    elementPresent_log(driver,By.xpath("//div[3]/button[2]"),"discard",5);
		    safeClick(driver,By.xpath("//div[3]/button"));
		    elementPresent_log(driver,By.xpath("//ul/p"),"All changes",10);
		    safeClick(driver,By.cssSelector("svg.sc-jKVCRD.dflrgH > path"));
		    elementPresent_log(driver,By.xpath("//div[3]/button"),"View changes",10);
		    elementPresent_log(driver,By.xpath("//div/div[2]/button"),"confirm changes",5);
		    safeClick(driver,By.xpath("//div/div[2]/button"));
		    elementPresent_log(driver,By.xpath("//form/div"),"publish",5);
		    safeType(driver,By.id("commitMessage"),"Update Property");
		    safeClick(driver,By.xpath("//form/div/button"));
		    textPresent_Log(driver,"publishing changes",30);
		    textPresent_Log(driver,"changes published successfully",90);
		    Thread.sleep(6000);
		    textPresent_Log(driver,"No changes as of yet",60);
		    safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
			elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
			elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
			if(datatype.equalsIgnoreCase("list")||(datatype.equalsIgnoreCase("object")&&((subtype.equalsIgnoreCase("number"))||(subtype.equalsIgnoreCase("string"))||(subtype.equalsIgnoreCase("boolean"))||(subtype.equalsIgnoreCase("JSON"))))){
				 safeType(driver,By.xpath("//input[@value='']"),propertykey);
				 safeClick(driver,By.xpath("//div[2]/ul/li"));
				 Thread.sleep(2000);
				 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO"));
				 textPresent_Log(driver,value,2);
			}else if((datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("object"))||(datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("list"))){
				 safeType(driver,By.xpath("//input[@value='']"),propertykey);
				 safeClick(driver,By.xpath("//div[2]/ul/li"));
				 Thread.sleep(2000);
				 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO > path"));
				 Thread.sleep(1000);
				 safeClick(driver,By.cssSelector("ul.PropertyEditorRowNode__open__SbmpR > li.PropertyEditorRowNode__rowItem__29voR > div.PropertyEditorRowNode__leftBlock__2Cxz7 > div.PropertyEditorRowNode__objectOpener__bFjdw > svg.sc-jPPmml.jwDqO"));
				 Thread.sleep(1000);
				 textPresent_Log(driver,value,2);
			}
			else{
		    safeType(driver,By.xpath("//input[@value='']"),propertykey);
		    safeClick(driver,By.xpath("//div[2]/ul/li"));
		    Thread.sleep(2000);
		    textPresent_Log(driver,value,2);
			}
			if((datatype.equalsIgnoreCase("list"))||(datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("list"))){
				safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
			    Thread.sleep(2000);
			    textPresent_Log(driver,"No changes as of yet",40);
				elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
				elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
				System.out.println(propertykey + "property updated successfully");
			} 
			else if((datatype.equalsIgnoreCase("object")&&((subtype.equalsIgnoreCase("number"))||(subtype.equalsIgnoreCase("string"))||(subtype.equalsIgnoreCase("boolean"))||(subtype.equalsIgnoreCase("JSON"))))||(datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("object"))){		
				if(datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("object")){
                  safeClick(driver,By.xpath("//ul/li/div/div[2]/ul/li/div/div"));
				}else{
				  safeClick(driver,By.xpath("//div[2]/ul/li"));
				}
				safeClick(driver,By.xpath("//div[2]/button"));
			    elementPresent_log(driver,By.xpath("//td[8]/button"),"Add",5);
			    textPresent_Log(driver,"Click on view button to view the complete info of the changes",5);
			    safeClick(driver,By.xpath("//td[8]/button"));
			    elementPresent_log(driver,By.xpath("//section/div/button"),"Commit Difference",2);
			    elementPresent_log(driver,By.xpath("//button[2]"),"Complete detail",2);
			    safeClick(driver,By.xpath("//section/div/button"));
			    safeClick(driver,By.xpath("//button[2]"));
			    textPresent_Log(driver,"Committed By",2);
			    textPresent_Log(driver,"Comments",2);
			    textPresent_Log(driver,"Diff Block",2);
			    safeClick(driver,By.xpath("//button"));
			    Thread.sleep(1000);
			    safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
			    Thread.sleep(2000);
			    textPresent_Log(driver,"No changes as of yet",40);
				elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
				elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
				System.out.println(propertykey + "property updated successfully");
			} 
			else{
		    safeClick(driver,By.xpath("//li/li"));
		    safeClick(driver,By.xpath("//button"));
		    elementPresent_log(driver,By.xpath("//td[8]/button"),"Add",5);
		    textPresent_Log(driver,"Click on view button to view the complete info of the changes",5);
		    safeClick(driver,By.xpath("//td[8]/button"));
		    elementPresent_log(driver,By.xpath("//section/div/button"),"Commit Difference",2);
		    elementPresent_log(driver,By.xpath("//button[2]"),"Complete detail",2);
		    safeClick(driver,By.xpath("//section/div/button"));
		    safeClick(driver,By.xpath("//button[2]"));
		    textPresent_Log(driver,"Committed By",2);
		    textPresent_Log(driver,"Comments",2);
		    textPresent_Log(driver,"Diff Block",2);
		    safeClick(driver,By.xpath("//button"));
		    Thread.sleep(1000);
		    safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
		    Thread.sleep(2000);
		    textPresent_Log(driver,"No changes as of yet",40);
			elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
			elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
			System.out.println(propertykey  + " property updated successfully");
			}
	}
	
	public void add_property_to_existingproperty(RemoteWebDriver driver,String propertykey,String propertykey1,String propertykey2,String value,String value1,String datatype,String datatype1,String subtype,String jsonschema) throws Exception{
		 safeType(driver,By.xpath("//input[@value='']"),propertykey);
		 safeClick(driver,By.xpath("//div[2]/ul/li"));
		 Thread.sleep(2000);
		 if(datatype.equalsIgnoreCase("list")){
		 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO"));
		 safeClick(driver,By.xpath("//button"));
		 elementPresent_log(driver,By.xpath("//form"),"update button",20);
		 textPresent_Log(driver,"Data type of Items in list",5);
		 if(subtype.equalsIgnoreCase("boolean")){
			 safeClick(driver,By.xpath("//div[2]/label")); 
			 safeClick(driver,By.xpath("//form/button"));
		 }else{
		  safeType(driver,By.id("propertyValue"),value);
		  safeClick(driver,By.xpath("//form/button"));
		 }
		 } else if(datatype.equalsIgnoreCase("object")){
			safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO"));
			 safeClick(driver,By.xpath("//button"));
			 Thread.sleep(1000);
			 safeClick(driver,By.xpath("//div[2]/div/div/div"));
				safeClick(driver,By.xpath("//div[3]/ul/li[2]"));
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[4]"));
				safeType(driver,By.id("controlName"),propertykey1);
				safeType(driver,By.id("propertyValue"),value);
				 safeClick(driver,By.xpath("//form/button"));
		 } else if(datatype.equalsIgnoreCase("object")||subtype.equalsIgnoreCase("object")){
			 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO > path"));
			 safeClick(driver,By.cssSelector("ul.PropertyEditorRowNode__open__SbmpR > li.PropertyEditorRowNode__rowItem__29voR > div.PropertyEditorRowNode__leftBlock__2Cxz7 > div.PropertyEditorRowNode__objectOpener__bFjdw > svg.sc-jPPmml.jwDqO"));
			 safeClick(driver,By.xpath("//button"));
			 Thread.sleep(1000);
			 safeClick(driver,By.xpath("//div[2]/div/div/div"));
				safeClick(driver,By.xpath("//div[3]/ul/li[2]"));
				safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
				safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[4]"));
				safeType(driver,By.id("controlName"),propertykey1);
				safeType(driver,By.id("propertyValue"),value);
				 safeClick(driver,By.xpath("//form/button"));
				 safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
				    Thread.sleep(2000);
				    safeType(driver,By.xpath("//input[@value='']"),propertykey);
					 safeClick(driver,By.xpath("//div[2]/ul/li"));
					 Thread.sleep(2000);  
					 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO > path"));
					 safeClick(driver,By.cssSelector("ul.PropertyEditorRowNode__open__SbmpR > li.PropertyEditorRowNode__rowItem__29voR > div.PropertyEditorRowNode__leftBlock__2Cxz7 > div.PropertyEditorRowNode__objectOpener__bFjdw > svg.sc-jPPmml.jwDqO"));
					 safeClick(driver,By.xpath("//button"));
					 Thread.sleep(1000);
					 safeClick(driver,By.xpath("//ul/li/div/div[2]/ul/button"));
					 safeClick(driver,By.xpath("//div[2]/div/div/div"));
						safeClick(driver,By.xpath("//li[6]"));
						safeType(driver,By.id("controlName"),propertykey2);
						safeType(driver,By.id("jsonSchema"),jsonschema);
						safeType(driver,By.id("propertyValue"),value1);	
						safeClick(driver,By.xpath("//form/div/button"));
		 }
		 elementPresent_log(driver,By.xpath("//div[3]/button"),"View changes",5);
		    elementPresent_log(driver,By.xpath("//div/div[2]/button"),"confirm changes",5);
		    elementPresent_log(driver,By.xpath("//div[3]/button[2]"),"discard",5);
		    safeClick(driver,By.xpath("//div[3]/button"));
		    elementPresent_log(driver,By.xpath("//ul/p"),"All changes",10);
		    safeClick(driver,By.cssSelector("svg.sc-jKVCRD.dflrgH > path"));
		    elementPresent_log(driver,By.xpath("//div[3]/button"),"View changes",10);
		    elementPresent_log(driver,By.xpath("//div/div[2]/button"),"confirm changes",5);
		    safeClick(driver,By.xpath("//div/div[2]/button"));
		    elementPresent_log(driver,By.xpath("//form/div"),"publish",5);
		    safeType(driver,By.id("commitMessage"),"Add Property");
		    safeClick(driver,By.xpath("//form/div/button"));
		    textPresent_Log(driver,"publishing changes",30);
		    textPresent_Log(driver,"changes published successfully",90);
		    Thread.sleep(6000);
		    textPresent_Log(driver,"No changes as of yet",60);
		    safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
		    Thread.sleep(2000);
		    elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
			elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
		    safeType(driver,By.xpath("//input[@value='']"),propertykey);
		    safeClick(driver,By.xpath("//div[2]/ul/li"));
			Thread.sleep(2000);
			if(datatype.equalsIgnoreCase("list")||(datatype.equalsIgnoreCase("object"))){
				 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO"));
				 textPresent_Log(driver,value,2);
			}else if((datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("object"))){
				 safeClick(driver,By.cssSelector("svg.sc-jPPmml.jwDqO > path"));
				 Thread.sleep(1000);
				 safeClick(driver,By.cssSelector("ul.PropertyEditorRowNode__open__SbmpR > li.PropertyEditorRowNode__rowItem__29voR > div.PropertyEditorRowNode__leftBlock__2Cxz7 > div.PropertyEditorRowNode__objectOpener__bFjdw > svg.sc-jPPmml.jwDqO"));
				 Thread.sleep(1000);
				 textPresent_Log(driver,value,2);
			}
			safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
		    Thread.sleep(2000);
		    textPresent_Log(driver,"No changes as of yet",40);
			elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
			elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
			System.out.println("Property add successfully");
	}
	
	public void delete_property(RemoteWebDriver driver,String propertykey,String datatype,String subtype,String subtype1) throws Exception{
		 safeType(driver,By.xpath("//input[@value='']"),propertykey);
		 safeClick(driver,By.xpath("//div[2]/ul/li"));
		 Thread.sleep(2000);
		if(datatype.equalsIgnoreCase("list")||(datatype.equalsIgnoreCase("object")&&((subtype.equalsIgnoreCase("number"))||(subtype.equalsIgnoreCase("string"))||(subtype.equalsIgnoreCase("boolean"))||(subtype.equalsIgnoreCase("JSON"))))){
			 Thread.sleep(2000);
			 if(datatype.equalsIgnoreCase("list")){
				 safeClick(driver,By.xpath("//li/div/div[2]"));
			 }else{
	                safeClick(driver,By.xpath("//li/li"));
			 }
	       safeClick(driver,By.xpath("//button[2]"));
		}else if((datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("object"))||(datatype.equalsIgnoreCase("object")&&subtype.equalsIgnoreCase("list"))){
			 Thread.sleep(2000);
            	safeClick(driver,By.xpath("//li/div/div[2]"));     
            safeClick(driver,By.xpath("//button[2]"));
		} else{
			 safeClick(driver,By.xpath("//li/li"));
			 safeClick(driver,By.xpath("//button[2]"));
		}
	    elementPresent_log(driver,By.xpath("//div[3]/button"),"View changes",5);
	    elementPresent_log(driver,By.xpath("//div/div[2]/button"),"confirm changes",5);
	    elementPresent_log(driver,By.xpath("//div[3]/button[2]"),"discard",5);
	    safeClick(driver,By.xpath("//div[3]/button"));
	    elementPresent_log(driver,By.xpath("//ul/p"),"All changes",10);
	    safeClick(driver,By.cssSelector("svg.sc-jKVCRD.dflrgH > path"));
	    elementPresent_log(driver,By.xpath("//div[3]/button"),"View changes",10);
	    elementPresent_log(driver,By.xpath("//div/div[2]/button"),"confirm changes",5);
	    safeClick(driver,By.xpath("//div/div[2]/button"));
	    elementPresent_log(driver,By.xpath("//form/div"),"publish",5);
	    safeType(driver,By.id("commitMessage"),"Delete Property");
	    safeClick(driver,By.xpath("//form/div/button"));
	    textPresent_Log(driver,"publishing changes",10);
	    textPresent_Log(driver,"changes published successfully",100);
	    Thread.sleep(5000);
	    textPresent_Log(driver,"No changes as of yet",90);
	    safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
		elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
		elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
	    safeType(driver,By.xpath("//input[@value='']"),propertykey);
	    if(elementPresent(driver,By.xpath("//div[2]/p[2]")))
	    {
	    	System.out.println(propertykey + " property deleted successfully");
	    	safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
		    Thread.sleep(2000);
		    textPresent_Log(driver,"No changes as of yet",40);
			elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
			elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
	    }
	     else {
			    	System.out.println(propertykey + " Property is not deleted");
			    }
	}
	
	public void property_revert(RemoteWebDriver driver,String propertykey,String value,String value1,String action) throws Exception{
		if(action.equalsIgnoreCase("add"))
		{
		safeClick(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"));
		safeClick(driver,By.xpath("//div/section/div[2]/div/div/div"));
		safeClick(driver,By.xpath("//div[3]/ul/li[2]"));
		safeClick(driver,By.xpath("//div[2]/div[2]/div/div"));
		safeClick(driver,By.xpath("//div[2]/div/div[3]/ul/li[4]"));
		    safeType(driver,By.id("controlName"),propertykey);
		    safeType(driver,By.id("propertyValue"),value);
		
		}else if(action.equalsIgnoreCase("update")){
			 safeType(driver,By.xpath("//input[@value='']"),propertykey);
			    safeClick(driver,By.xpath("//div[2]/ul/li"));
			    Thread.sleep(2000);
			    safeClick(driver,By.xpath("//li/li"));
				 safeClick(driver,By.xpath("//button[3]"));
				 elementPresent_log(driver,By.xpath("//form"),"update button",5);
				 textPresent_Log(driver,"Data type of the property is saved as",5);
				 safeType(driver,By.id("propertyValue"),value1);	    
		}else if(action.equalsIgnoreCase("revert")){
			safeClick(driver,By.linkText("Audit Properties"));
			safeClick(driver,By.xpath("//input[@value='Revert']"));
			safeClick(driver,By.xpath("//button"));
			safeType(driver,By.xpath("//input[@type='text']"),"Revert");
			safeClick(driver,By.xpath("//div[2]/button"));
			textPresent_Log(driver,"Reverting",20);
			textPresent_Log(driver,"Reverted Successfully",50);
			safeClick(driver,By.xpath("//div[2]/div/div[2]/div/button"));
			textPresent_Log(driver,"Revert",10);
			safeClick(driver,By.linkText("Properties"));
			Thread.sleep(2000);
			choose_verticle(driver);
			textPresent_Log(driver,"No changes as of yet",40);
			elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
			elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
			  safeType(driver,By.xpath("//input[@value='']"),propertykey);
			    safeClick(driver,By.xpath("//div[2]/ul/li"));
			    Thread.sleep(2000);
			    textPresent_Log(driver,value,2);
			    safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
			    Thread.sleep(2000);
			    textPresent_Log(driver,"No changes as of yet",40);
				elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
				elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);	
		} else if(action.equalsIgnoreCase("delete")){
			safeType(driver,By.xpath("//input[@value='']"),propertykey);
		    safeClick(driver,By.xpath("//div[2]/ul/li"));
		    Thread.sleep(2000);
		    safeClick(driver,By.xpath("//li/li"));
			 safeClick(driver,By.xpath("//button[2]"));
		}
		if(action.equalsIgnoreCase("add")||action.equalsIgnoreCase("update")){
		safeClick(driver,By.xpath("//form/button"));
		}
		if(action.equalsIgnoreCase("add")||action.equalsIgnoreCase("update")||action.equalsIgnoreCase("delete")){
				
		elementPresent_log(driver,By.xpath("//div[3]/button"),"View changes",5);
		elementPresent_log(driver,By.xpath("//div/div[2]/button"),"confirm changes",5);
		elementPresent_log(driver,By.xpath("//div[3]/button[2]"),"discard",5);
		    safeClick(driver,By.xpath("//div[3]/button"));
		    elementPresent_log(driver,By.xpath("//ul/p"),"All changes",10);
		    safeClick(driver,By.cssSelector("svg.sc-jKVCRD.dflrgH > path"));
		    elementPresent_log(driver,By.xpath("//div[3]/button"),"View changes",10);
		    elementPresent_log(driver,By.xpath("//div/div[2]/button"),"confirm changes",5);
		    safeClick(driver,By.xpath("//div/div[2]/button"));
		    elementPresent_log(driver,By.xpath("//form/div"),"publish",5);
		    safeType(driver,By.id("commitMessage"),action + " Property");
		    safeClick(driver,By.xpath("//form/div/button"));
		    textPresent_Log(driver,"publishing changes",10);
		    textPresent_Log(driver,"changes published successfully",90);
		    Thread.sleep(5000);
		    textPresent_Log(driver,"No changes as of yet",40);
		    if(action.equalsIgnoreCase("update")||action.equalsIgnoreCase("delete")){
		    	safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
			    Thread.sleep(2000);
		    } 
		    textPresent_Log(driver,"No changes as of yet",40);
			elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
			elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
		    safeType(driver,By.xpath("//input[@value='']"),propertykey);
		    if(action.equalsIgnoreCase("add")){
		    	safeClick(driver,By.xpath("//div[2]/ul/li"));
			    Thread.sleep(2000);
		    	textPresent_Log(driver,value,2);
		    }else if(action.equalsIgnoreCase("update")){
		    	safeClick(driver,By.xpath("//div[2]/ul/li"));
			    Thread.sleep(2000);
		    	textPresent_Log(driver,value1,2);
		    } else if(action.equalsIgnoreCase("delete")){
		    	   if(elementPresent(driver,By.xpath("//div[2]/p[2]")))
		  	    {
		  	    	System.out.println(propertykey + " property deleted successfully");
		  	    }
		  	     else {
		  			    	System.out.println(propertykey + " Property is not deleted");
		  			
		  	     }
		    }
		    safeClick(driver,By.cssSelector("svg.sc-jtggT.buLuLa"));
		    Thread.sleep(2000);
		    textPresent_Log(driver,"No changes as of yet",40);
			elementPresent_log(driver,By.cssSelector("svg.sc-gZMcBi.giNnHm"),"Add Button",10);
			elementPresent_log(driver,By.xpath("//input[@value='']"),"search property",10);
			System.out.println(action + " property for revert function");
		}  
	}
		
}
	


