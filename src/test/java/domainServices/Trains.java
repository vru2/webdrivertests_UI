// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - MAR, 2015
// Author - Karthick Krihsna
// Copyright © 2016 cleartrip Travel. All right reserved.
package test.java.  domainServices;

import static org.testng.AssertJUnit.assertTrue;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;

import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.java.commonServices.CommonUtil;

//import com.opera.core.systems.scope.protos.EcmascriptProtos.SetFormElementValueArgOrBuilder;

import test.java.commonServices.WrapperMethod;

public class Trains extends WrapperMethod {

	private static final String AdultCountry1 = null;
	private static final String TripID = null;
	private String baseUrl = "http://"+common.value("host")+common.value("url")+"com";
	public String NetBankingQA = "Axis Bank";  
	//private String baseUrl_AE = "http://"+common.value("host")+common.value("url")+"ae";
	public DefaultHttpClient httpclient = new DefaultHttpClient();

	private static String CorpTrains_Url;

	public String CorpTrains_Url() throws Exception {
		if (common.value("host").contains("qa2")) {
			CorpTrains_Url = dataFile.value("CorpTrains_Qa2");

		} else if (common.value("host").contains("www")) {
			CorpTrains_Url = dataFile.value("Corp_Prod");
		} else if (common.value("host").contains("beta")) {
			CorpTrains_Url = dataFile.value("Corp_Beta");
		} else if (common.value("host").contains("stg1")) {
			CorpTrains_Url = dataFile.value("Corp_Stg1");
		}
		return CorpTrains_Url;
	}
	public boolean checkIfSignedCorp(RemoteWebDriver driver) throws Exception {
		if (waitElement(driver, getObject("CorpCom_SignIN_EmailID"), 3)) {
			return false;
		} else if (waitElement(driver, getObject("AirCorpCom_HomePage_Search_Button"), 3)) {
			return true;
		} else {
			Reporter.log("Corp page not loading.");
			assertTrue("Failure!", false);
		}
		return false;
	}

	public void corpTrains_SignIn(RemoteWebDriver driver) throws Exception {
		if(textPresent(driver, "Sorry, our system is acting up.", 1)) {
			Reporter.log("Sorry, our system is acting up. : message is displayed");
			Assert.assertTrue(false);
		} else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)) {
			Reporter.log("Whatever you're looking for, isn't here : message is displayed");
			Assert.assertTrue(false);
		}  
		if (!checkIfSignedCorp(driver)) {
			elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 10);
			if (common.value("host").contains("qa2")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("TrainsEmailID"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("TrainsPassword"));
			} else if (common.value("host").contains("www")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("TrainsEmailID"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("TrainsPassword"));
			} else if (common.value("host").contains("beta")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("TrainsEmailID"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("TrainsPassword"));
			} else if (common.value("host").contains("stg1")) {
				safeType(driver, getObject("CorpCom_SignIN_EmailID"), dataFile.value("TrainsEmailID"));
				safeType(driver, getObject("CorpCom_SignIN_Password"), dataFile.value("cTrainsPassword"));

			}
			safeClick(driver, getObject("CorpCom_SignIN_SignIN_Button"));
			//if (elementVisible(driver, By.id("userAccountLink"), 5)) {

			if (elementPresent_Time(driver, By.xpath("//a/ul/li"), 5))
			{
				Reporter.log("login successfull");
			} else {
				Reporter.log("login failed");
				assertTrue("Failure!", false);
			}
		}
	}
	public void CorpTrains_HomepageSearch(RemoteWebDriver driver,String DStation, String AStation, String Class, String TDate, String Adult1,String Child1,String SMen,String SWomen) throws Exception 
	{
		Reporter.log("In corp trains search page");
		//driver.findElement(By.xpath("//li[@class='trainsApp']")).click();
		safeClick(driver,getObject("CorpTrains_HomePage_TrainsLink"));	
		Thread.sleep(7000);
		//Reporter.log("-------------"+driver.getTitle());
		//Reporter.log("text present="+driver.findElement(By.xpath("//h1")).getText());
		//stextPresentInElement(driver,getObject("CorpTrain_HomePage_Text"),"Indian Railways IRCTC Train Tickets Reservation",20);
		safeAutocomplete(driver,getObject("CorpTrains_HomePage_FromStation"),getObject("CorpTrainsFromAjax"), DStation);	
		safeAutocomplete(driver,getObject("CorpTrains_HomePage_ToStation"),getObject("CorpTravellersAjax"),AStation);

		safeSelect(driver,getObject("CorpTrains_HomePage_SelectClass"),Class);
		//selectCalendarDate(driver,getObject("CorpTrains_HomePage_SelectDate"),getObject("CorpTrains_HomePage_NextMonth"),1,TDate);
		//selectCalendarThisMonth(driver,getObject("CorpTrains_HomePage_SelectDate"),(TDate));		
		selectCalendarDate(driver,getObject("CorpTrains_HomePage_SelectDate"),getObject("B2cTrains_MonthNext"),1,TDate);			
		CorpTrains_PaxEntry(driver,Adult1,Child1,SMen,SWomen);
		safeClick(driver,getObject("CorpTrains_HomePage_SelectSearch"));
		Reporter.log("Train Searched for " + DStation + " to : " + AStation + " for Date : " + (TDate) +" in corporate domain");
		elementPresent_Time(driver,getObject("CorpTrains_SRP_TotalTrainsNos"),10);
		Thread.sleep(3000);

	}
	private String getWindow(RemoteWebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}
	public void CorpTrains_CheckAvail(RemoteWebDriver driver,String Quota,String BoardStation,String TrainsName) throws Exception {

		String s1 = getText(driver, getObject("CorpTrains_SRP_TotalTrainsNos"));
		String [] s1list = s1.split(" of ");
		int count1 = (s1list.length) - 1;
		int count =s1list.length;
		int trainsCount = Integer.parseInt(s1list[count1]);
		//Reporter.log(trainsCount);
		Reporter.log(s1list[count1]);
		//int count = trainsCount;
		//int count = Integer.parseInt(trainsCount);
		for(int i=1; i <= count; i++)
		{
			String name=driver.findElementByXPath("//*[@id='train-"+i+"']/td[1]/p[1]/a").getText();
			//Reporter.log(name);
			Reporter.log(name);
			if(TrainsName.equals(name)){
				Reporter.log("Selected train "+name+" is matching with "+TrainsName);
				//Reporter.log("Selected train "+name+" is matching with "+TrainsName);
				if(Quota == "General" )
				{
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					Thread.sleep(9000);
					//elementPresent_Time(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label"),10);
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label").click();

				}
				else if(Quota =="Tatkal"){
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					Thread.sleep(9000);				
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();

					/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();	*/			
				}
				else {
					Reporter.log("Selected trains "+ TrainsName +" is not available");
					//Reporter.log("Selected trains "+ TrainsName +" is not available");
					break;
				}
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();
				break;
			}
			else{
				Reporter.log("Selected train "+name+" is not matching with "+TrainsName);
				//Reporter.log("Selected train "+name+" is not matching with "+TrainsName);
			}
		}
		Thread.sleep(7000);
	}
	public void CorpTrains_Bookstep1(RemoteWebDriver driver,String BoardStation ) throws Exception {

		if(BoardStation != "Same")
		{
			String station="2";
			safeSelect(driver,getObject("B2cTrains_BoardingStation"),station);
		}
		safeClick(driver,getObject("CorpTrains_Step1Button"));
		//Reporter.log("Proceeding from bookstep 1");
		Reporter.log("Proceeding from bookstep 1");
	}

	public void CorpTrains_Bookstep2(RemoteWebDriver driver,String Adult1,String Child1,String SMn,String SWomn) throws Exception {
		int Adult = Integer.parseInt(Adult1);
		int Children = Integer.parseInt(Child1);
		int SMen = Integer.parseInt(SMn);
		int SWomen = Integer.parseInt(SWomn);
		Thread.sleep(2000);
		for (int i=1; i <= Adult;i++ )
		{
			elementVisible(driver, By.id("Traveller1"), 10);		
			safeAutocomplete(driver,getObject("CorpAdultFirstName"+i),getObject("Traincorp_TravellerPage_NameAjax"),"Test123");
			Thread.sleep(3000);
			safeSelect(driver,getObject("CorpAdultAge"+i),dataFile.value("AdultAge"+i));		
			safeSelect(driver,By.xpath("//*[@id='adultBerthPreference"+i+"']"),dataFile.value("AdultBerth"+i));
			Thread.sleep(5000);

		}
		for (int i=1; i <= Children;i++ )
		{
			elementVisible(driver, By.id("childauto_1"), 10);	
			safeAutocomplete(driver,getObject("CorpChildFirstName"+i),getObject("CorpTravellerpagesAjax"),"test123");
			Thread.sleep(2000);
			safeSelect(driver,getObject("CorpChildAge"+i),dataFile.value("ChildAge"+i));		
			safeSelect(driver,By.xpath("//*[@id='childBerthPreference"+i+"']"),dataFile.value("ChildBerth"+i));
		}
		for (int i=1; i <= SMen;i++ )
		{

			safeAutocomplete(driver,getObject("CorpSMFirstName"+i),getObject("CorpTravellerpagesAjax"),"test123");
			safeSelect(driver,getObject("CorpSmAge"+i),dataFile.value("SAge"+i));	
			safeSelect(driver,By.xpath("//*[@id='male_seniorBerthPreference"+i+"']"),dataFile.value("SBerth"+i));

		}
		for (int i=1; i <= SWomen;i++ )
		{
			safeAutocomplete(driver,By.xpath("//*[@id='female_seniorauto_"+i+"']"),getObject("HotelCorp_TravellerPage_NameAjax"),dataFile.value("AdultFirstName"+i));
			safeSelect(driver,getObject("CorpSwAge"+i),dataFile.value("SAge"+i));	
			safeSelect(driver,By.xpath("//*[@id='female_seniorBerthPreference"+i+"']"),dataFile.value("SBerth"+i));
		}
		// Berth Preference xpath has same id element so to handle it , added an extra condition

		safeAutocomplete(driver,getObject("CorpContactPerson"),getObject("CorpTravellerpagesAjax"),"test123");
		Thread.sleep(4000);
		safeType(driver,getObject("CorpAddress"),"JP Nagar 5th Phase");
		safeType(driver,getObject("CorpCity"),"Bangalore");
		safeType(driver,getObject("CorpPin"),"560078");
		safeType(driver,getObject("CorpCountry"),"India");
		safeType(driver,getObject("CorpContactNumber"),"9620357979");
		safeClick(driver,getObject("CorpTravellerContinue"));
		Thread.sleep(2000);
	}

	public void CorpTrains_PaxEntry(RemoteWebDriver driver,String Adult1,String Child1,String SMen,String SWomen) throws Exception {
		//elementVisible(driver, getObject("HotelCom_HomePage_Room_DropDown"), 10);
		safeSelect(driver, getObject("CorpTrains_HomePage_SelectAdults"), Adult1);
		safeSelect(driver, getObject("CorpTrains_HomePage_SelectChildren"),Child1);
		safeSelect(driver, getObject("CorpTrains_HomePage_SelectSnMen"),SMen);
		safeSelect(driver, getObject("CorpTrains_HomePage_SelectSnWomen"),SWomen);		
	}

	public void B2cTrains_HomepageSignIn(RemoteWebDriver driver) throws Exception {    
		String Host = common.value("host");
		//String TrainsEmailaddress= "cleartriptest1"+CommonUtil.getRandomNo(1)+"@gmail.com";
		if(Host.equalsIgnoreCase("qa2")) {	
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 10);
			//elementPresent_log(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), "Signin not shown", 1);
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_YourTrips"), 30);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
			if(elementVisible(driver, By.id("global_signout"), 1)) {
				safeClick(driver, By.id("global_signout"));
				elementVisible(driver, getObjectHotels("HotelCom_HomePage_YourTrips"), 5);
				safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
			}
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 20);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
			Thread.sleep(2000);
			driver.switchTo().frame("modal_window");
			elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 60);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("TrainsEmailIDQA"));
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"),  dataFile.value("TrainsPasswordQA"));
			Thread.sleep(2000);
			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		}
		else if(Host.equalsIgnoreCase("www")) {
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 10);
			elementPresent_log(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), "Signin not shown", 1);
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_YourTrips"), 50);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 40);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
			Thread.sleep(3000);
			driver.switchTo().frame("modal_window");
			elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 60);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("TrainsEmailIDProd"));
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"),  dataFile.value("TrainsPasswordProd"));
			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			}
	}
	
	public void B2cTrains_UnsignedSignIn(RemoteWebDriver driver) throws Exception {    
		String Host = common.value("host");
		//String TrainsEmailaddress= "cleartriptest1"+CommonUtil.getRandomNo(1)+"@gmail.com";
		if(Host.equalsIgnoreCase("qa2")) {	
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 10);
			elementPresent_log(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), "Signin not shown", 1);
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_YourTrips"), 30);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
			if(elementVisible(driver, By.id("global_signout"), 1)) {
				safeClick(driver, By.id("global_signout"));
				elementVisible(driver, getObjectHotels("HotelCom_HomePage_YourTrips"), 5);
				safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
			}
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 20);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
			Thread.sleep(1000);
			driver.switchTo().frame("modal_window");
			elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 60);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("TrainsEmailunsigned"));
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"),  dataFile.value("TrainsPasswordunsigned"));
			Thread.sleep(4000);
			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		}
		else if(Host.equalsIgnoreCase("www")) {
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 10);
			elementPresent_log(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), "Signin not shown", 1);
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_YourTrips"), 50);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 40);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
			Thread.sleep(3000);
			driver.switchTo().frame("modal_window");
			elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 60);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("TrainsEmailIDProd"));
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"),  dataFile.value("TrainsPasswordProd"));
			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			}
	}

	
	
	
	
	

	public void B2cTrains_HomepageSignIn_Popup(RemoteWebDriver driver) throws Exception {
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Email"), dataFile.value("TrainsEmailID"));
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"), dataFile.value("TrainsPassword"));
			safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));
			elementVisible(driver, getObject("B2cTrains_trainsHeaderLink"), 10);
			safeClick(driver, getObject("B2cTrains_trainsHeaderLink"));
		}
		else if(Host.equalsIgnoreCase("www")) {
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Email"), dataFile.value("TrainsEmailIDProd"));
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"), "Testing@123");
			safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));
			elementVisible(driver, getObject("B2cTrains_trainsHeaderLink"), 10);
			safeClick(driver, getObject("B2cTrains_trainsHeaderLink"));
		}

	}

	public void B2cTrains_CheckavailSignIn(RemoteWebDriver driver) throws Exception {
		//String mainWindow = driver.getWindowHandle();
		driver.switchTo().frame("modal_window");
		elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("TrainsEmailID"));

		safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"),  dataFile.value("TrainsPassword"));

		safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));


	}


	public void mobileTrains_HomepageSignIn(RemoteWebDriver driver) throws Exception {
		//safeClick(driver,By.xpath("//span[contains(text(),'Search Indian Rlwys. & IRCTC trains')]"));
		Thread.sleep(1000);
		safeClick(driver,By.className("menuBtn"));
		//safeClick(driver,By.xpath("//*[text()='Menu']"));		
		safeClick(driver,By.xpath("//*[text()='Settings']"));
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		/*WebElement element = driver.findElement(By.xpath("//*[contains(text(),'Sign In')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		//Thread.sleep(500); 
		safeClick(driver,getObject("MobileWeb_Trains_SignInR"));*/
		/*safeClick(driver,By.xpath("//ul[@class='list list--inline']/li/a[0]"));
		String mainWindow = driver.getWindowHandle();
		safeClick(driver, getObject("B2cTrains_HomePage_SignIn"));
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Email"),"ns.likhitha@cleartrip.com");
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"),"Ravindra@143");
		safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));
		elementVisible(driver, getObject("B2cTrains_trainsHeaderLink"), 10);
		safeClick(driver, getObject("B2cTrains_trainsHeaderLink"));*/

		safeType(driver,getObject("MobileWeb_Trains_EmailIDR"),dataFile.value("TrainsEmailID"));
		safeType(driver,getObject("MobileWeb_Trains_PasswordR"),"likhitha");

		safeClick(driver, getObject("MobileWeb_Trains_LoginR"));
		driver.get(common.value("PWATrainsURL"));
		Thread.sleep(2000);
		driver.findElement(By.id("trains-search")).click();


	}

	public void B2cTrains_HomepageSignIn_Prod(RemoteWebDriver driver) throws Exception {
/*		String mainWindow = driver.getWindowHandle();
		waitForElementVisibility(driver,getObject("B2cTrains_HomePage_SignIn"),50);
		safeClick(driver, getObject("B2cTrains_HomePage_SignIn"));
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Email"), dataFile.value("TrainsEmailIDQA"));
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"), dataFile.value("TrainsPasswordQA"));
		safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));
		elementVisible(driver, getObject("B2cTrains_trainsHeaderLink"), 10);
		safeClick(driver, getObject("B2cTrains_trainsHeaderLink"));*/

		
		String mainWindow = driver.getWindowHandle();
		Thread.sleep(2000);
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_YourTrips"), 30);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 30);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
		Thread.sleep(1000);
		driver.switchTo().frame("modal_window");
		elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 60);
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("beta")||Host.equalsIgnoreCase("www")) {
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("TrainsEmailIDProd"));
			}
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("TrainsPasswordProd"));
		
		safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		
		Thread.sleep(7000);
		driver.switchTo().window(mainWindow);
	}


	public void B2cTrains_BookstepSignin(RemoteWebDriver driver) throws Exception
	{	
		Thread.sleep(3000);
		safeType(driver, getObject("B2ctrains_bookstepsignin_email"), dataFile.value("TrainsEmailIDQA"));
		safeClick(driver,getObject("HaveACleartripAccAndPassword"));
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"), dataFile.value("TrainsPasswordQA"));
		safeClick(driver,getObject("B2cTrains_bookstep_ContinueBooking"));
		elementPresent_Time(driver,getObject("PageWaitTextBookstep_Signin"),5);
	}
	
	public void B2cTrains_HomepageSearch(RemoteWebDriver driver,String DStation, String AStation, String Class, String TDate, String Adult1,String Child1,String SMen,String SWomen) throws Exception {	
		//logMessagePageNotLoaded(driver, getObject("HotelCom_HomePage_HotelTab"), 2, "Home Page has not loaded");
		safeClick(driver, getObject("B2C_Trains"));
		waitForElementVisibility(driver,By.xpath(".//*[@id='rail_search']/h1"),60);
		if(!textPresent(driver, "Search trains", 1)) {
			Reporter.log("Search trains text is not present");
			assertTrue(false);
		}
		elementPresent_Time(driver, getObject("B2cTrains_HomePage_FromStation"), 40);
		safeAutocomplete(driver, getObject("B2cTrains_HomePage_FromStation"), getObject("B2cTrains_HomePage_SearchAjax"), DStation);
		safeAutocomplete(driver, getObject("B2cTrains_HomePage_ToStation"), getObject("B2cTrains_HomePage_SearchAjax"), AStation);
		safeSelect(driver,getObject("B2cTrains_Class"),Class);
		selectCalendarDate(driver,getObject("B2cTrains_MonthClick"),getObject("B2cTrains_MonthNext"),2,TDate);
		//selectCalendarThisMonth(driver,getObject("B2cTrains_MonthClick"),(TDate));
		//selectCalendarThisMonth(driver,getObject("B2cTrains_Date"),TDate);
		B2cTrains_PaxEntry(driver, Adult1,Child1,SMen,SWomen);
		Reporter.log("Train Searched for " + DStation + " to : " + AStation + " for Date : " + (TDate));
		safeClick(driver, getObject("B2cTrains_SearchButton"));
	
	}

	public void B2cTrains_HomepageSearchwithSignin(RemoteWebDriver driver,String DStation, String AStation, String Class, String TDate, String Adult1,String Child1,String SMen,String SWomen) throws Exception {	
		elementVisible(driver,getObject("B2cTrains_HomePage_TrainTab"),30);
		//logMessagePageNotLoaded(driver, getObject("HotelCom_HomePage_HotelTab"), 2, "Home Page has not loaded");
		safeClick(driver, getObject("B2cTrains_HomePage_TrainTab"));
		textPresent_Log(driver,"Indian Railways IRCTC Train Tickets Reservation",10);
		elementPresent_Time(driver, getObject("B2cTrains_HomePage_FromStation"), 10);
		safeAutocomplete_CHMM(driver, getObject("B2cTrains_HomePage_FromStation"), getObject("B2cTrains_HomePage_SearchAjax"), DStation);
		safeAutocomplete_CHMM(driver, getObject("B2cTrains_HomePage_ToStation"), getObject("B2cTrains_HomePage_SearchAjax"), AStation);
		safeSelect(driver,getObject("B2cTrains_Class"),Class);
		selectCalendarDate(driver,getObject("B2cTrains_MonthClick"),getObject("B2cTrains_MonthNext"),1,TDate);
		//selectCalendarThisMonth(driver,getObject("B2cTrains_MonthClick"),(TDate));
		//selectCalendarThisMonth(driver,getObject("B2cTrains_Date"),TDate);
		B2cTrains_PaxEntry(driver, Adult1,Child1,SMen,SWomen);
		Reporter.log("Train Searched for " + DStation + " to : " + AStation + " for Date : " + (TDate));
		safeClick(driver, getObject("B2cTrains_SearchButton"));
		Thread.sleep(5000);	
		driver.switchTo().frame("modal_window");
		elementPresent_log(driver,getObject("IRCTC_UserName"),"Username", 10);
		textPresent_Log(driver,"To continue with this booking",10);
		safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
		safeClick(driver,getObject("IRCTC_UN_popup"));

	if(elementVisible(driver,By.xpath(".//*[@id='statusCall']"),5)){
		Thread.sleep(2000);
		safeClick(driver,getObject("IRCTC_UN_popup"));
	}
		elementVisible(driver,getObject("B2cTrains_SearchButton"), 10);		
	safeClick(driver, getObject("B2cTrains_SearchButton"));

	}
	
	public void B2cTrains_HomepageSearchNotSignedin(RemoteWebDriver driver,String DStation, String AStation, String Class, String TDate, String Adult1,String Child1,String SMen,String SWomen) throws Exception {	
		//logMessagePageNotLoaded(driver, getObject("HotelCom_HomePage_HotelTab"), 2, "Home Page has not loaded");
		//safeClick(driver, getObject("B2cTrains_HomePage_TrainTab"));

		elementPresent_Time(driver, getObject("B2cTrains_HomePage_FromStation"), 10);
		safeAutocomplete(driver, getObject("B2cTrains_HomePage_FromStation"), getObject("B2cTrains_HomePage_SearchAjax"), DStation);
		safeAutocomplete(driver, getObject("B2cTrains_HomePage_ToStation"), getObject("B2cTrains_HomePage_SearchAjax"), AStation);
		safeSelect(driver,getObject("B2cTrains_Class"),Class);
		selectCalendarDate(driver,getObject("B2cTrains_MonthClick"),getObject("B2cTrains_MonthNext"),1,TDate);
		//selectCalendarThisMonth(driver,getObject("B2cTrains_MonthClick"),(TDate));
		//selectCalendarThisMonth(driver,getObject("B2cTrains_Date"),TDate);
		B2cTrains_PaxEntry(driver, Adult1,Child1,SMen,SWomen);
		Reporter.log("Train Searched for " + DStation + " to : " + AStation + " for Date : " + (TDate));
		safeClick(driver, getObject("B2cTrains_SearchButton"));
		Thread.sleep(5000);	
		driver.switchTo().frame("modal_window");
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Email"), dataFile.value("TrainsEmailIDQA"));
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"), dataFile.value("TrainsPasswordQA"));
		safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));
		Thread.sleep(2000);
		safeClick(driver, getObject("B2cTrains_SearchButton"));
		driver.switchTo().frame("modal_window");
		Thread.sleep(5000);
		safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
		safeClick(driver,getObject("IRCTC_UN_popup"));
		elementVisible(driver,getObject("B2cTrains_SearchButton"), 10);		
		safeClick(driver, getObject("B2cTrains_SearchButton"));

	}
	public void mobileTrains_HomepageSearch(RemoteWebDriver driver,String DStation, String AStation, String Class, String TDate, String Adult1,String Child1,String SMen,String SWomen,String type,int x) throws Exception {	
		//logMessagePageNotLoaded(driver, getObject("HotelCom_HomePage_HotelTab"), 2, "Home Page has not loaded");
		//safeClick(driver, getObject("B2cTrains_HomePage_TrainTab"));

		//safeClick(driver, getObject("MobileWeb_Trains_OriginR"));
		//safeClick(driver,By.xpath("//*[text()='Origin']/parent::*/*[2]"));
		safeClick(driver,By.xpath("//*[contains(@id,'switch')]//..//..//../div[1]"));
		safeType(driver,getObject("MobileWeb_Trains_AutocompleteR"),DStation);
		safeClick(driver,getObject("MobileWeb_Trains_OriginDropdownR"));
		Thread.sleep(3000);
		//safeClick(driver,By.xpath("//*[text()='Destination']/parent::*/*[2]"));
		safeClick(driver,By.xpath("//*[contains(@id,'switch')]//..//..//../div[2]"));
		//safeClick(driver, getObject("MobileWeb_Trains_DestR"));
		Thread.sleep(4000);
		safeType(driver,getObject("MobileWeb_Trains_AutocompleteR"),AStation);
		safeClick(driver,getObject("MobileWeb_Trains_OriginDropdownR"));
		if(x==0) {
			safeClick(driver,By.xpath("//*[text()='Depart']/parent::*/*[2]"));
		}
		if(type.equalsIgnoreCase("rac") || type.equalsIgnoreCase("tatkal") || type.equalsIgnoreCase("wl")) {
			//safeClick(driver,getObject("MobileWeb_Trains_DateR"));
			if(x==0) {
				String d;
				String convertedDate=getModifiedDate(driver,"1");
				/*//Reporter.log(""+convertedDate);
				Calendar c = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				c.add(Calendar.DATE, Integer.parseInt("10"));
				String convertedDate = dateFormat.format(c.getTime());
				String a=Month.of(Integer.parseInt(convertedDate.split("-")[1])).name().toLowerCase();
				String b=String.valueOf(a.charAt(0)).toUpperCase();
				//Reporter.log("b value="+b);
				String z=a.replaceFirst(String.valueOf(a.charAt(0)),b);
				//Reporter.log("--------"+z+" "+convertedDate.split("-")[2]);*/
				safeClick(driver,By.xpath("//*[text()='Depart']/parent::*/*[2]"));
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@datetime,'"+convertedDate+"')]")));
				js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[contains(@datetime,'"+convertedDate+"')]")));
				/*List<WebElement> we=driver.findElements(By.xpath("//*[text()='"+d+"']"));
				if(we.size()>1) {

				}*/



				/*JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[text()='"+d+"']")));
				js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[text()='"+d+"']")));*/
				//	js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td/button[contains(@aria-label,'"+z+" "+convertedDate.split("-")[2].replaceFirst("^0+(?!$)", "")+"')]")));

				//safeClick(driver,getObject("MobileWeb_Trains_DateRACR"));
			}
			//safeClick(driver,getObject("MobileWeb_Trains_DateRACR"));
		}
		else {
			String d;

			String convertedDate=getModifiedDate(driver,"10");
			/*if(Integer.parseInt(convertedDate.split("-")[2])<10) {
				d=convertedDate.split("-")[2].replaceAll("0","");
			}
			else {
				d=convertedDate.split("-")[2];
			}
			//Reporter.log("d value="+d);
			Calendar c = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			c.add(Calendar.DATE, Integer.parseInt("10"));
			String convertedDate = dateFormat.format(c.getTime());
			String a=Month.of(Integer.parseInt(convertedDate.split("-")[1])).name().toLowerCase();
			String b=String.valueOf(a.charAt(0)).toUpperCase();
			//Reporter.log("b value="+b);
			String z=a.replaceFirst(String.valueOf(a.charAt(0)),b);
			//Reporter.log("--------"+z+" "+convertedDate.split("-")[2]);*/
			safeClick(driver,By.xpath("//*[text()='Depart']/parent::*/*[2]"));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@datetime,'"+convertedDate+"')]")));
			js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[contains(@datetime,'"+convertedDate+"')]")));
			//js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td/button[contains(@aria-label,'"+z+" "+convertedDate.split("-")[2].replaceFirst("^0+(?!$)", "")+"')]")));



			//safeClick(driver,getObject("MobileWeb_Trains_DateselectR"));	
		}
		if(Adult1!="1" || SMen!="0" || SWomen!="0" || Child1!="0" ) {
			safeClick(driver,getObject("MobileWeb_Trains_TravellerR"));
		}
		if(Adult1!="1") {

			waitForElement(driver,10,getObject("MobileWeb_Trains_AdultR"));
			Thread.sleep(2000);
			for(int i=1;i<Integer.parseInt(Adult1);i++) {
				safeClick(driver,getObject("MobileWeb_Trains_AdultR"));
			}

		}
		if(Child1!="0") {

			waitForElement(driver,10,getObject("MobileWeb_Trains_ChildR"));
			Thread.sleep(2000);
			for(int i=1;i<Integer.parseInt(Child1)+1;i++) {
				safeClick(driver,getObject("MobileWeb_Trains_ChildR"));
			}

		}
		if(SMen!="0") {
			Thread.sleep(2000);
			waitForElement(driver,10,getObject("MobileWeb_Trains_SMenR"));
			for(int i=1;i<Integer.parseInt(SMen)+1;i++) {
				safeClick(driver,getObject("MobileWeb_Trains_SMenR"));
			}
		}
		if(SWomen!="0") {
			for(int i=1;i<Integer.parseInt(SWomen)+1;i++) {
				safeClick(driver,getObject("MobileWeb_Trains_SwomenR"));
			}
		}
		if(elementPresent(driver,getObject("MobileWeb_Trains_Traveller_Continue"),10)) {
			safeClick(driver,getObject("MobileWeb_Trains_Traveller_Continue"));
		}
		waitForElement(driver,10,getObject("MobileWeb_Trains_Search"));
		Thread.sleep(500);
		/*JavascriptExecutor js1 = (JavascriptExecutor) driver;
		  js1.executeScript("arguments[0].click();",driver.findElement(getObject("MobileWeb_Trains_Search")));*/
		safeClick(driver,getObject("MobileWeb_Trains_Search"));

	}
	public void B2cTrains_HomepageSearch_tatkal(RemoteWebDriver driver,String DStation, String AStation, String Class, String TDate, String Adult1,String Child1,String SMen,String SWomen) throws Exception {	
		textPresent_Log(driver,"Indian Railways IRCTC Train Tickets Reservation",10);
		//logMessagePageNotLoaded(driver, getObject("HotelCom_HomePage_HotelTab"), 2, "Home Page has not loaded");
		//safeClick(driver, getObject("B2cTrains_HomePage_TrainTab"));
		elementPresent_Time(driver, getObject("B2cTrains_HomePage_FromStation"), 10);
		safeAutocomplete(driver, getObject("B2cTrains_HomePage_FromStation"), getObject("B2cTrains_HomePage_SearchAjax"), DStation);
		safeAutocomplete(driver, getObject("B2cTrains_HomePage_ToStation"), getObject("B2cTrains_HomePage_SearchAjax"), AStation);
		safeSelect(driver,getObject("B2cTrains_Class"),Class);
		selectCalendarDate(driver,getObject("B2cTrains_MonthClick"),getObject("B2cTrains_MonthNext"),2,TDate);
		//selectCalendarThisMonth(driver,getObject("B2cTrains_MonthClick"),(TDate));
		//Reporter.log(getObject("B2cTrains_MonthClick"));
		//selectCalendarThisMonth(driver,getObject("B2cTrains_MonthClick"),TDate);
		B2cTrains_PaxEntry(driver, Adult1,Child1,SMen,SWomen);
		Reporter.log("Train Searched for " + DStation + " to : " + AStation + " for Date : " + (TDate));
		safeClick(driver, getObject("B2cTrains_SearchButton"));
		Thread.sleep(5000);	
		driver.switchTo().frame("modal_window");
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Email"), dataFile.value("TrainsEmailIDProd"));
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"), dataFile.value("TrainsPasswordProd"));
		safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));
		safeClick(driver, getObject("B2cTrains_SearchButton"));
		Thread.sleep(2000);
//		driver.switchTo().frame("modal_window");
//		Thread.sleep(3000);
//		irctcPopup_Validation(driver);
//		safeClick(driver, getObject("B2cTrains_SearchButton"));
//		Thread.sleep(2000);
//		driver.switchTo().frame("modal_window");
//		Thread.sleep(4000);
//		safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
//		safeClick(driver,getObject("IRCTC_UN_popup"));
//		Thread.sleep(2000);
//		if(elementVisible(driver,By.xpath(".//*[@id='statusCall']"),20)){
//			Thread.sleep(2000);
//			safeClick(driver,getObject("IRCTC_UN_popup"));
			Thread.sleep(2000);
//		}
		
		//safeClick(driver, getObject("B2cTrains_SearchButton"));
		elementPresent_Time(driver,getObject("B2cTrains_SRP_totalTrains"),10);
	}
	
	public void B2cTrains_HomepageSearch_tatkal1(RemoteWebDriver driver,String DStation, String AStation, String Class, String TDate, String Adult1,String Child1,String SMen,String SWomen) throws Exception {	
		textPresent_Log(driver,"Indian Railways IRCTC Train Tickets Reservation",10);
		//logMessagePageNotLoaded(driver, getObject("HotelCom_HomePage_HotelTab"), 2, "Home Page has not loaded");
		//safeClick(driver, getObject("B2cTrains_HomePage_TrainTab"));
		elementPresent_Time(driver, getObject("B2cTrains_HomePage_FromStation"), 10);
		safeAutocomplete(driver, getObject("B2cTrains_HomePage_FromStation"), getObject("B2cTrains_HomePage_SearchAjax"), DStation);
		safeAutocomplete(driver, getObject("B2cTrains_HomePage_ToStation"), getObject("B2cTrains_HomePage_SearchAjax"), AStation);
		safeSelect(driver,getObject("B2cTrains_Class"),Class);
		//selectCalendarDate(driver,getObject("B2cTrains_MonthClick"),getObject("B2cTrains_MonthNext"),2,TDate);
		selectCalendarThisMonth(driver,getObject("B2cTrains_MonthClick"),(TDate));
		//Reporter.log(getObject("B2cTrains_MonthClick"));
		//selectCalendarThisMonth(driver,getObject("B2cTrains_MonthClick"),TDate);
		B2cTrains_PaxEntry(driver, Adult1,Child1,SMen,SWomen);
		Reporter.log("Train Searched for " + DStation + " to : " + AStation + " for Date : " + (TDate));
		safeClick(driver, getObject("B2cTrains_SearchButton"));
		Thread.sleep(5000);	
		driver.switchTo().frame("modal_window");
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Email"), dataFile.value("TrainsEmailIDProd"));
		safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"), dataFile.value("TrainsPasswordProd"));
		safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));
		safeClick(driver, getObject("B2cTrains_SearchButton"));
//		driver.switchTo().frame("modal_window");
//        Thread.sleep(4000);
//		textPresent_Log(driver,"I forgot my IRCTC username", 30);
//		safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
//		safeClick(driver,getObject("IRCTC_UN_popup"));
//		elementVisible(driver, getObject("B2cTrains_SearchButton"), 20);
//        safeClick(driver, getObject("B2cTrains_SearchButton"));
		elementPresent_Time(driver,getObject("B2cTrains_SRP_totalTrains"),10);
	}


	public void B2cTrains_HomepageSearch_RAC(RemoteWebDriver driver,String DStation, String AStation, String Class, String TDate, String Adult1,String Child1,String SMen,String SWomen) throws Exception {	
		safeClick(driver, getObject("B2cTrains_HomePage_TrainTab"));
		safeAutocomplete(driver, getObject("B2cTrains_HomePage_FromStation"), getObject("B2cTrains_HomePage_SearchAjax"), DStation);
		safeAutocomplete(driver, getObject("B2cTrains_HomePage_ToStation"), getObject("B2cTrains_HomePage_SearchAjax"), AStation);
		safeSelect(driver,getObject("B2cTrains_Class"),Class);
		//Reporter.log(getObject("B2cTrains_MonthClick"));
		selectCalendarThisMonth(driver,getObject("B2cTrains_MonthClick"),TDate);
		B2cTrains_PaxEntry(driver, Adult1,Child1,SMen,SWomen);
		Reporter.log("Train Searched for " + DStation + " to : " + AStation + " for Date : " + (TDate));
		safeClick(driver, getObject("B2cTrains_SearchButton"));
		elementPresent_Time(driver,getObject("B2cTrains_SRP_totalTrains"),10);
	}


public void B2cTrains_CalendraAvailability(RemoteWebDriver driver, String Dstation, String Astation, String Class) throws Exception{
	String Host = common.value("host");   
	driver.get(baseUrl);
       elementPresent_log(driver,getObject("B2C_Home_TriansTab"), "Train tab ", 50);
       safeClick(driver,getObject("B2C_Home_TriansTab"));
       elementPresent_log(driver, getObject("B2C_CalendarAvail"), "Train Calendar ", 50);
	   safeClick(driver, getObject("B2C_CalendarAvail"));
	   //Thread.sleep(5000);
	   // driver.findElement(By.xpath("//a[contains(text(),'Trains availability calendar')]")).click();
	    modalWindow(driver);	 
		//driver.get(baseUrl+"/trains/calendar");
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		safeAutocomplete(driver, getObject("B2C_CalendarAvail_From"), getObject("B2cTrains_HomePage_SearchAjax"), Dstation);
		safeAutocomplete(driver, getObject("B2C_CalendarAvail_To"), getObject("B2cTrains_HomePage_SearchAjax"), Astation);
		//safeType(driver,getObject("B2C_CalendarAvail_From"),Dstation);
		//Thread.sleep(3000);
		//safeType(driver,getObject("B2C_CalendarAvail_To"),Astation);
		//Thread.sleep(2000);
		safeClick(driver,getObject("B2C_CalendarAvail_Class"));
		safeSelect(driver,getObject("B2C_CalendarAvail_Class"),Class);
		safeClick(driver,getObject("B2C_CalendarAvail_Date"));
		safeSelect(driver,getObject("B2C_CalendarAvail_Date"),"Next 15 days");
		safeClick(driver,getObject("B2C_CalendarAvail_Get"));
		//driver.findElement(By.id("to")).sendKeys("Chennai Central (MAS)");
		//driver.findElement(By.id("train_class")).sendKeys("Sleeper (SL)");
		//driver.findElement(By.className("booking")).click();
		 waitForElementVisibility(driver,getObject("B2C_Calendar"),50);
		elementPresent_log(driver,getObject("B2C_Calendar"),"Trains availability for",50);
		Reporter.log("Calendar for the selected stations is displayed");
		 if(Host.equalsIgnoreCase("www")){
				safeClick(driver,By.xpath(".//*[@id='ContentFrame']/div[2]/table/tbody/tr[1]/td[1]/a"));
				driver.switchTo().frame("modal_window");
				Thread.sleep(3000);
				textPresent_Log(driver,"Timing and Distance",40);
				textPresent_Log(driver,"Stations that the Trains Pass Through",10);
				textPresent_Log(driver,"Average Ticket Price",10);
				textPresent_Log(driver,"Important Passenger Information",10);
				elementPresent_log(driver,By.xpath(".//*[@id='submit_search_form']"),"Seach flights button",10);
		 }
	
}



	public void B2cTrains_CheckAvail(RemoteWebDriver driver,String Quota,String BoardStation,String TrainsName) throws Exception {
		if(textPresent(driver, "Train ticketing service is unavailable from", 1)) {
			Reporter.log("Train ticketing service is unavailable from");
			Assert.assertTrue(false);
		}
		logURL(driver);
		String trainsCount = getText(driver, getObject("B2cTrains_trainsCount"));
		Reporter.log(trainsCount);
		int count = Integer.parseInt(trainsCount);
		for(int i=1; i <= count; i++)
		{
			
			String name=driver.findElementByXPath("//*[@id='train-"+i+"']/td[1]/p[1]/a").getText();
			String checkAvailability="//*[@id='train-"+i+"']/td[6]/input";
			String label="//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label";			
			String labelTatkal="//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label";			
			Reporter.log(name);
			if(TrainsName.equals(name)){
				Reporter.log("Selected train "+name+" is matching with "+TrainsName);
				if(Quota == "General" )
				{
					/*driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					//Thread.sleep(9000);
					elementVisible(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label"),10);
				
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label").click();*/
					
					safeClick(driver, By.xpath(checkAvailability));
					if(textPresent(driver,"Could not get current availability",1)){
					
						Reporter.log("IRCTC is down in SRP");
						Assert.assertTrue(false);
					}
					elementVisible(driver, By.xpath(label), 10);
				
					safeClick(driver, By.xpath(label));
					

				}
				else if(Quota =="Tatkal"){
					
					safeClick(driver, By.xpath(checkAvailability));
					if(textPresent(driver,"Could not get current availability",10)){
						Reporter.log("IRCTC is down");
						Assert.assertTrue(false);
					}
					elementVisible(driver, By.xpath(labelTatkal), 10);
					safeClick(driver, By.xpath(labelTatkal));
					
					
					/*driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					Thread.sleep(9000);				
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();*/

					/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();	*/			
				}

				else if(Quota =="RAC"){
					String checkAvail = "//*[@id='train-"+i+"']/td[6]/input";
					String RACAvail = "//*[@id='train-"+i+"']/td[6]/p/a";
					String RACBook = "//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label/input";
					safeClick(driver, By.xpath(checkAvail));
					if(textPresent(driver,"Could not get current availability",10)){
						Reporter.log("IRCTC is down");
						Assert.assertTrue(false);
					}
					safeClick(driver, By.xpath(RACAvail));
					safeClick(driver, By.xpath(RACBook));
					
					/*
					driver.findElementByXPath(checkAvail).click();
					driver.findElementByXPath(RACAvail).click();
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[2]/dd[2]/p[1]/label/input").click();*/


					Thread.sleep(5000);				
					//driver.findElementByXPath("//tr[@id='train-"+i+"']/td[7]/button").click();

					/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();	*/			
				}




				else {
					Reporter.log("Selected trains "+ TrainsName +" is not available");
					////Reporter.log("Selected trains "+ TrainsName +" is not available");
					break;
				}
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();
				
				break;
			}
			else{
				Reporter.log("Selected train "+name+" is not matching with "+TrainsName);
				////Reporter.log("Selected train "+name+" is not matching with "+TrainsName);
			}
		}

	}
	
	public boolean mobileTrains_CheckAvail(RemoteWebDriver driver,String Quota,String BoardStation,String TrainsName) throws Exception {
		boolean found=false;
		Thread.sleep(3000);
		//waitForElement(driver,10,By.xpath("//li[@role='menuitem'][1]/div"));
		//elementPresent(driver,By.xpath("//li[@role='menuitem'][1]/div"),10);
		/*JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//li[@role='menuitem'][1]/div")));*/

		safeClick(driver,By.xpath("//li[@role='menuitem'][1]/div"));
		//WebDriverWait wait = new WebDriverWait(driver,20);

		// if(wait.until(ExpectedConditions.alertIsPresent())!=null) {
		// Alert alert = driver.switchTo().alert();
		// alert.accept();
		if( waitForElement(driver,10,By.xpath("//input[@name='irctcUserName']"))) {
			safeType(driver,By.xpath("//input[@name='irctcUserName']"),"test");
			safeClick(driver,By.xpath("//button[text()='Continue booking']"));
			Thread.sleep(2000);   
		}

		waitForElement(driver,30,getObject("MobileWeb_Trains_Selecting_Seat_Type_GSleeperR"));


		//	String name=driver.findElementByXPath("//*[@id='train-"+i+"']/td[1]/p[1]/a").getText();
		////Reporter.log(name);
		//Reporter.log(name);
		//if(TrainsName.equals(name)){
		//Reporter.log("Selected train "+name+" is matching with "+TrainsName);
		////Reporter.log("Selected train "+name+" is matching with "+TrainsName);
		if(Quota == "General" )
		{
			safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_GSleeperR"));
			Thread.sleep(9000);
			//elementPresent_Time(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label"),10);
			//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label").click();

		}
		else if(Quota =="Tatkal"){
			int y=2;
			int x=0;
			boolean tatkalfound=false;
			if(elementPresent(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_TatkalR"),5)) {
				safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_TatkalR"));
				tatkalfound=true;
				found=true;
				Thread.sleep(2000);
				driver.switchTo().alert().accept();
			}
			else {
				boolean list=true;
				do {

					//safeClick(driver,By.xpath("//*[@id='back-a']"));
					safeClick(driver,By.xpath("//div[contains(@class,'Header')]/parent::*/*[2]/*[1]"));
					Thread.sleep(4000);
					waitForElement(driver,10,By.xpath("//*[@id='root']/div/div[2]/div/div[2]/ul/li/div"));
					List<WebElement> we=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/ul/li/div"));
					for(int i=1;i<we.size()+1;i++) {
						x=we.size();
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/ul/li["+i+"]/div")).click();
						//we.get(i).click();
						if(waitForElement(driver,10,getObject("MobileWeb_Trains_Selecting_Seat_Type_TatkalR"))) {
							safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_TatkalR"));
							Thread.sleep(2000);
							driver.switchTo().alert().accept();
							y=we.size();
							tatkalfound=true;
							found=true;
							break;

						}
						else {
							safeClick(driver,By.xpath("//*[@id='back-a']"));
						}
						y++;
						//Reporter.log("y value="+y);

					}
					//list=false;
					////Reporter.log("x value="+x);
					////Reporter.log("x="+x+",y="+y);
				}while(!tatkalfound);
			}
			Thread.sleep(9000);				
			//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();

			/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();	*/			







			/*safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_TatkalR"));
				driver.switchTo().alert().accept();*/


			//	driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
			//Thread.sleep(9000);				
			//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();

			/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();	*/			

		}
		else if(Quota =="wl") {
			boolean tatkalfound=false;
			if(elementPresent(driver,By.xpath("//*[text()='Waitlist']"),5)) {
				safeClick(driver,By.xpath("//*[text()='Waitlist']"));
				tatkalfound=true;
				found=true;


			}
			else {
				boolean list=true;
				//	do {

				safeClick(driver,By.xpath("//*[@id='back-a']"));
				Thread.sleep(4000);
				waitForElement(driver,10,By.xpath("//*[@id='root']/div/div[2]/div/div[2]/ul/li/div"));
				List<WebElement> we=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/ul/li/div"));
				for(int i=1;i<we.size()+1;i++) {

					driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/ul/li["+i+"]/div")).click();
					//we.get(i).click();
					if(waitForElement(driver,10,By.xpath("//*[text()='Waitlist']"))) {
						safeClick(driver,By.xpath("//*[text()='Waitlist']"));


						tatkalfound=true;
						found=true;
						break;

					}
					else {
						safeClick(driver,By.xpath("//*[@id='back-a']"));
					}



				}
				//list=false;
				////Reporter.log("x value="+x);
				////Reporter.log("x="+x+",y="+y);
				//}while(!tatkalfound || list);
			}
			Thread.sleep(9000);				
			//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();

			/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();	*/			







			/*safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_TatkalR"));
				driver.switchTo().alert().accept();*/


			//	driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
			//Thread.sleep(9000);				
			//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();

			/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();	*/			


		}




		else if(Quota =="RAC"){
			boolean racfound=false;
			if(elementPresent(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_RACR"),5)) {
				safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_RACR"));
			}
			else {
				do {
					safeClick(driver,By.xpath("//*[@id='back-a']"));
					Thread.sleep(4000);
					waitForElement(driver,10,By.xpath("//*[@id='root']/div/div[2]/div/div[2]/ul/li/div"));
					List<WebElement> we=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/ul/li/div"));
					for(int i=2;i<we.size();i++) {
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/ul/li["+i+"]/div")).click();
						//we.get(i).click();
						if(waitForElement(driver,10,getObject("MobileWeb_Trains_Selecting_Seat_Type_RACR"))) {
							safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_RACR"));
							racfound=true;
							break;

						}
					}
				}while(!racfound);
			}
			Thread.sleep(9000);				
			//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();

			/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();	*/			
		}





		//driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();
		//break;
		//}


		return found;
	}
	public void nearByStation(RemoteWebDriver driver) throws Exception {
		waitForElement(driver,10,getObject("MobileWeb_Trains_Selecting_Train_in_SRPR"));
		boolean shownearby=false;
		List<WebElement> we=driver.findElements(By.xpath("//li[@role='menuitem']/*/*[1]/p[2]"));
		int x=we.size();		
		driver.findElement(By.xpath("//*[contains(text(),'Show nearby stations')]")).click();
		Thread.sleep(2000);
		List<WebElement> we1=driver.findElements(By.xpath("//li[@role='menuitem']/*/*[1]/p[2]"));
		int y=we1.size();
		if(x<y) {
			shownearby=true;
		}
		Assert.assertTrue(shownearby);
	}

	public void mobileTrains_CheckAvailClasses(RemoteWebDriver driver,String Quota,String BoardStation,String TrainsName) throws Exception {
		boolean availabilityfound=false;
		//elementPresent(driver,getObject("MobileWeb_Trains_Selecting_Train_in_SRPR"),10);
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Show nearby stations']"))));

		//waitForElement(driver,10,getObject("MobileWeb_Trains_Selecting_Train_in_SRPR"));
		safeClick(driver,getObject("MobileWeb_Trains_Selecting_Train_in_SRPR"));

		waitForElement(driver,10,getObject("MobileWeb_Trains_Selecting_Seat_Type_GSleeperR"));
		List<WebElement> we1=driver.findElements(By.xpath("//div[@class='Tabs__nav']/div"));
		for(int i=0;i<we1.size();i++) {
			we1.get(i).click();
			Thread.sleep(5000);
			List<WebElement> we=driver.findElements(By.xpath("//*[contains(text(),'General')]"));
			if(we.size()>0) {
				//Reporter.log("seats are available");
				availabilityfound=true;
			}
			else {
				availabilityfound=false;
			}
		}
		assertTrue(availabilityfound);
		//	String name=driver.findElementByXPath("//*[@id='train-"+i+"']/td[1]/p[1]/a").getText();
		////Reporter.log(name);
		//Reporter.log(name);
		//if(TrainsName.equals(name)){
		//Reporter.log("Selected train "+name+" is matching with "+TrainsName);
		////Reporter.log("Selected train "+name+" is matching with "+TrainsName);
		/*if(Quota == "General" )
			{
				safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_GSleeperR"));
				Thread.sleep(9000);
				//elementPresent_Time(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label"),10);
				//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label").click();

			}
			else if(Quota =="Tatkal"){
				safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_TatkalR"));
				driver.switchTo().alert().accept();


			//	driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
				//Thread.sleep(9000);				
				//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();

				elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();				
			}

			else if(Quota =="RAC"){
				safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_RACR"));
				Thread.sleep(9000);				
				//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();

				elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();				
			}
		 */




		//driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();
		//break;
		//}



	}
	public void B2cTrains_CheckAvail_Unsigned(RemoteWebDriver driver,String Quota,String BoardStation,String TrainsName) throws Exception {
		//Reporter.log(TrainsName);
		String trainsCount = getText(driver, getObject("B2cTrains_trainsCount"));
		////Reporter.log(trainsCount);
		Reporter.log(trainsCount);
		int count = Integer.parseInt(trainsCount);
		for(int i=1; i <= count; i++)
		{
			String name=driver.findElementByXPath("//*[@id='train-"+i+"']/td[1]/p[1]/a").getText();
			//Reporter.log(name);
			Reporter.log(name);
			if(TrainsName.equals(name)){
				Reporter.log("Selected train "+name+" is matching with "+TrainsName);
				////Reporter.log("Selected train "+name+" is matching with "+TrainsName);
				if(Quota == "General" )
				{
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					Thread.sleep(9000);
					//elementPresent_Time(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label"),10);
					//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label").click();
					//driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();
					Thread.sleep(5000);			
					driver.switchTo().frame("modal_window");
					safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
					safeClick(driver,getObject("IRCTC_UN_popup"));
					Thread.sleep(4000);
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();		
					Thread.sleep(4000);


				}
				else if(Quota =="Tatkal"){
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					driver.switchTo().frame("modal_window");
					safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
					safeClick(driver,getObject("IRCTC_UN_popup"));			
					Thread.sleep(9000);

					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();
					driver.findElement(By.xpath("//a[text()='Kaveri Express']/../../../td[7]/button")).click();
					Thread.sleep(5000);				

				}

				else if(Quota =="RAC"){
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					Thread.sleep(3000);
					driver.switchTo().frame("modal_window");
					safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
					safeClick(driver,getObject("IRCTC_UN_popup"));			
					Thread.sleep(9000);

					driver.findElementByXPath(".//*[@id='train-"+i+"']/td[6]/p/a").click();
					//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[2]/dd[2]/p[1]/label/input").click();				

					Thread.sleep(9000);				
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[2]/dd[4]/p[1]/label/acronym").click();
					driver.findElement(By.xpath("//a[text()='Kaveri Express']/../../../td[7]/button")).click();

					/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();	*/			
				}



				else {
					Reporter.log("Selected trains "+ TrainsName +" is not available");
					////Reporter.log("Selected trains "+ TrainsName +" is not available");
				}
				//driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();
				break;
			}
			else{
				Reporter.log("Selected train "+name+" is not matching with "+TrainsName);
				////Reporter.log("Selected train "+name+" is not matching with "+TrainsName);
			}
		}

	}



	public void B2cTrains_CheckAvailnew_Unsigned(RemoteWebDriver driver,String Quota,String BoardStation,String TrainsName) throws Exception {
		//Reporter.log(TrainsName);
		String trainsCount = getText(driver, getObject("B2cTrains_trainsCount"));
		////Reporter.log(trainsCount);
		Reporter.log(trainsCount);
		int count = Integer.parseInt(trainsCount);
		for(int i=1; i <= count; i++)
		{
			String name=driver.findElementByXPath("//*[@id='train-"+i+"']/td[1]/p[1]/a").getText();
			//Reporter.log(name);
			Reporter.log(name);
			if(TrainsName.equals(name)){
				Reporter.log("Selected train "+name+" is matching with "+TrainsName);
				////Reporter.log("Selected train "+name+" is matching with "+TrainsName);
				if(Quota == "General" )
				{
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					Thread.sleep(4000);
					//elementPresent_Time(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label"),10);
					//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label").click();
					//driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();
					//Thread.sleep(5000);			
					/*driver.switchTo().frame("modal_window");
					safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
					safeClick(driver,getObject("IRCTC_UN_popup"));
					Thread.sleep(4000);
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();		
					Thread.sleep(4000);*/


				}

			}
		}
	}

	public void B2cTrains_CheckAvailtwo_Unsigned(RemoteWebDriver driver,String Quota,String BoardStation,String TrainsName) throws Exception {
		//Reporter.log(TrainsName);
		String trainsCount = getText(driver, getObject("B2cTrains_trainsCount"));
		////Reporter.log(trainsCount);
		Reporter.log(trainsCount);
		int count = Integer.parseInt(trainsCount);
		for(int i=1; i <= count; i++)
		{
			String name=driver.findElementByXPath("//*[@id='train-"+i+"']/td[1]/p[1]/a").getText();
			//Reporter.log(name);
			Reporter.log(name);
			if(TrainsName.equals(name)){
				Reporter.log("Selected train "+name+" is matching with "+TrainsName);
				////Reporter.log("Selected train "+name+" is matching with "+TrainsName);
				if(Quota == "General" )
				{
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					Thread.sleep(9000);
					//elementPresent_Time(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label"),10);
					//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label").click();
					//driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();
					Thread.sleep(5000);			
					/*driver.switchTo().frame("modal_window");
						safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
						safeClick(driver,getObject("IRCTC_UN_popup"));*/
					Thread.sleep(4000);
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();		
					Thread.sleep(4000);


				}
				else if(Quota =="Tatkal"){
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					/*driver.switchTo().frame("modal_window");
					safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
					safeClick(driver,getObject("IRCTC_UN_popup"));	*/		
					Thread.sleep(9000);

					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();
					driver.findElement(By.xpath("//a[text()='Kaveri Express']/../../../td[7]/button")).click();
					Thread.sleep(5000);				

				}

				else if(Quota =="RAC"){
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
					Thread.sleep(3000);
					/*driver.switchTo().frame("modal_window");
						safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
						safeClick(driver,getObject("IRCTC_UN_popup"));			
						Thread.sleep(9000);*/

					driver.findElementByXPath(".//*[@id='train-"+i+"']/td[6]/p/a").click();
					//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[2]/dd[2]/p[1]/label/input").click();				

					Thread.sleep(9000);				
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[2]/dd[4]/p[1]/label/acronym").click();
					driver.findElement(By.xpath("//a[text()='Kaveri Express']/../../../td[7]/button")).click();

					/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
					driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();	*/			
				}



				else {
					Reporter.log("Selected trains "+ TrainsName +" is not available");
					////Reporter.log("Selected trains "+ TrainsName +" is not available");
				}
				//driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();
				break;
			}
			else{
				Reporter.log("Selected train "+name+" is not matching with "+TrainsName);
				////Reporter.log("Selected train "+name+" is not matching with "+TrainsName);
			}
		}

	}

	public void B2cTrains_Bookstep1(RemoteWebDriver driver,String BoardStation ) throws Exception {
		if(textPresent(driver, "Sorry, our system is acting up.", 2)) {
			Reporter.log("Sorry, our system is acting up. in BookStep1");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Internal Server Error", 2)) {
			Reporter.log("Internal Server Error. in BookStep1");
			Assert.assertTrue(false);
		}
		
	if(textPresent(driver,"Oops! Railways� online service is having hiccups",2)){
			Reporter.log("Oops! Railways� online service is having hiccups");
			Assert.assertTrue(false);
	}
		
		if(BoardStation != "Same")
		{
			String station="2";
			safeSelect(driver,getObject("B2cTrains_BoardingStation"),station);
		}

		safeClick(driver,getObject("B2cTrains_Step1Button"));
		////Reporter.log("Proceeding from bookstep 1");
		Reporter.log("Proceeding from bookstep 1");
	}
	
	public void B2cTrains_Bookstep1_WithInsurance(RemoteWebDriver driver,String BoardStation ) throws Exception {
		if(textPresent(driver, "Sorry, our system is acting up.", 2)) {
			Reporter.log("Sorry, our system is acting up.");
			Assert.assertTrue(false);
		}
		
		if(textPresent(driver,"Oops! Railways� online service is having hiccups",2)){
			Reporter.log("Oops! Railways� online service is having hiccups");
			Assert.assertTrue(false);
		}
		if(BoardStation != "Same")
		{
			String station="2";
			safeSelect(driver,getObject("B2cTrains_BoardingStation"),station);
		}
		textPresent_Log(driver, "Do you want to take Travel Insurance", 5);
		safeClick(driver, By.id("irctc_travel_insurance_yes"));
		Thread.sleep(2000);
		safeSelectByIndex(driver, By.id("boardingPointOptions"), 2);
		safeClick(driver,getObject("B2cTrains_Step1Button"));
		Reporter.log("Proceeding from bookstep 1");
	}
	
	public void mobileTrains_Bookstep1(RemoteWebDriver driver,String BoardStation ) throws Exception {
		Thread.sleep(2000);

		waitForElement(driver,10,getObject("MobileWeb_Trains_Continue_Button_ItineraryR"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		safeClick(driver,getObject("MobileWeb_Trains_Continue_Button_ItineraryR"));

		Reporter.log("Proceeding from bookstep 1");
	}
	public void B2cTrains_Bookstep2	(RemoteWebDriver driver,String Adult1,String Child1,String SMn,String SWomn) throws Exception {
		int Adult = Integer.parseInt(Adult1);
		int Children = Integer.parseInt(Child1);
		int SMen = Integer.parseInt(SMn);
		int SWomen = Integer.parseInt(SWomn);
		Thread.sleep(2000);
		for (int i=1; i <= Adult;i++ )
		{
			safeSelect(driver,getObject("AdultTitle"+i+""),dataFile.value("AdultTitle"+i));
			safeType(driver,getObject("AdultFirstName"+i),dataFile.value("AdultFirstName"+i));
			safeType(driver,getObject("AdultSecName"+i),dataFile.value("AdultSecName"+i));
			safeSelect(driver,getObject("AdultAge"+i),dataFile.value("AdultAge"+i));
			safeSelectByIndex(driver,By.xpath("//*[@id='AdultBerthPreference"+i+"']"),2);
			safeSelect(driver,getObject("AdultCountry"+i),dataFile.value("TrainsAdultCountry"));
			if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
				safeClick(driver,getObject("B2C_Train_Infantslink"));
				if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
					safeClick(driver,getObject("B2C_Train_Infantslink"));
				}
			}
			//driver.findElement(By.id("AdultCountry1")).sendKeys("India");			


		}
		for (int i=1; i <= Children;i++ )
		{
			safeSelect(driver,getObject("ChildTitle"+i+""),dataFile.value("ChildTitle"+i));
			safeType(driver,getObject("ChildFirstName"+i),dataFile.value("ChildFirstName"+i));
			safeType(driver,getObject("ChildSecName"+i),dataFile.value("ChildSecName"+i));
			safeSelect(driver,By.xpath("//*[@name='ChildAge"+i+"']"),dataFile.value("ChildAge"+i));
			safeSelectByIndex(driver,By.xpath("//*[@id='ChildBerthPreference"+i+"']"),2);	
			safeSelect(driver,getObject("ChildCountry"+i),dataFile.value("TrainsChildCountry"));
			driver.findElement(By.id("ChildCountry1")).sendKeys("India");	
			if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
				safeClick(driver,getObject("B2C_Train_Infantslink"));
				if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
					safeClick(driver,getObject("B2C_Train_Infantslink"));
				}
			}


		}		
		// Berth Preference xpath has same id element so to handle it , added an extra condition
		if ( SMen >=1 && SWomen >=1){
			List<WebElement> we1=driver.findElements(By.xpath("//select[contains(@id,'SeniorTitle')]"));
			int STraveller = we1.size();
			for (int i=1; i <= STraveller;i++ )
			{
				safeSelect(driver,getObject("STitle"+i+""),dataFile.value("STitle"+i));
				safeType(driver,getObject("SFirstName"+i),dataFile.value("SFirstName"+i));
				safeType(driver,getObject("SSecName"+i),dataFile.value("SSecName"+i));
				safeSelect(driver,getObject("SAge"+i),dataFile.value("SAge"+i));
				safeSelectByIndex(driver,By.xpath("//*[@id='SeniorBerthPreference"+i+"']"),2);
				safeSelect(driver,getObject("SeniorCountry"+i),dataFile.value("TrainsChildCountry"));
				if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
					smartClick(driver,By.cssSelector("#removeInfantLink>img"));
					if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
						smartClick(driver,By.cssSelector("#removeInfantLink>img"));
					}
				}
				//driver.findElement(By.id("SeniorCountry1")).sendKeys("India");	
				
			}
		}
		else if (SMen >=1)
		{
			for (int i=1; i <= SMen;i++ )
			{
				{
					safeSelect(driver,getObject("STitle"+i+""),dataFile.value("STitle"+i));
					safeType(driver,getObject("SFirstName"+i),dataFile.value("SFirstName"+i));
					safeType(driver,getObject("SSecName"+i),dataFile.value("SSecName"+i));
					safeSelect(driver,getObject("SAge"+i),dataFile.value("SAge"+i));
					safeSelectByIndex(driver,By.xpath("//*[@id='SeniorBerthPreference"+i+"']"),2);						
					safeSelect(driver,getObject("SeniorCountry"+i),dataFile.value("TrainsAdultCountry"));
					if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
						smartClick(driver,getObject("B2C_Train_Infantslink"));
						if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
							smartClick(driver,getObject("B2C_Train_Infantslink"));
						}
				}
		}
	}
		}
		else if (SWomen >=1)
		{
			for (int i=1; i <= SWomen;i++ )
			{
				safeSelect(driver,getObject("STitle"+i+""),dataFile.value("STitle"+i));
				safeType(driver,getObject("SFirstName"+i),dataFile.value("SFirstName"+i));
				safeType(driver,getObject("SSecName"+i),dataFile.value("SSecName"+i));
				safeSelect(driver,getObject("SAge"+i),dataFile.value("SAge"+i));
				safeSelectByIndex(driver,By.xpath("//*[@id='SeniorBerthPreference"+i+"']"),2);			
				safeSelect(driver,getObject("SeniorCountry1"+i),dataFile.value("TrainsChildCountry"));
				driver.findElement(By.id("SeniorCountry1")).sendKeys("India");
				if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
					smartClick(driver,getObject("B2C_Train_Infantslink"));
					if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
						smartClick(driver,getObject("B2C_Train_Infantslink"));
					}
				}
			}
		}
		else {
		safeSelect(driver,getObject("ConNameTitle"),dataFile.value("ConNameTitle"));
		safeType(driver,getObject("ConFName"),dataFile.value("ConFName"));		
		safeType(driver,getObject("ConSName"),dataFile.value("ConSName"));


//		safeClick(driver,getObject("TrainsGSTCheckbox"));		
//		safeType(driver,getObject("GSTno"),dataFile.value("GSTno"));
//		safeType(driver,getObject("GSTname"),dataFile.value("GSTname"));
//		safeType(driver,getObject("GSTaddress"),dataFile.value("GSTaddress"));


		safeType(driver,getObject("ConCountry"),dataFile.value("ConCountry"));
		safeType(driver,getObject("ConMobileNo"),dataFile.value("ConMobileNo"));
		safeClick(driver,getObject("ContinueToPay"));	
		if(textPresent(driver, "error message to be added", 2)) {
			safeClick(driver,getObject("ContinueToPay"));				
		}
		
	}
	}
	public void IRCTC_LoginPageR(RemoteWebDriver driver, String IRCTC_action) throws Exception
	{
		String action = IRCTC_action;
		String TripID = null;
		if(action == "Confirm")
		{
			safeType(driver,getObject("IRCTC_Password"),dataFile.value("IRCTC_Password"));
			Reporter.log("Reached IRCTC Login page , Confirming the booking");
			////Reporter.log("Reached IRCTC Login page , Confirming the booking");
		}
		else if(action == "Cancel")
		{
			//boolean irctc=waitForElement(driver,10,By.xpath("//div[contains(text(),'Authentication')]"));
			waitForElement(driver,10,By.xpath("//div[contains(text(),'Authentication')]"));
			safeClick(driver,getObject("IRCTC_Cancel"));
			Reporter.log("Reached IRCTC Login page , Cancelling the booking");
			//Assert.assertEquals(waitForElement(driver,10,By.xpath("//div[contains(text(),'Authentication')]")),true);

		}
		else
		{
			browserClose(driver);
			Reporter.log("Reached IRCTC Login page , Closing the window");
			////Reporter.log("Reached IRCTC Login page , Closing the window");
		}

		elementVisible(driver,getObject("bookingFailurePage"),20);
		//Assert.assertEquals(waitForElement(driver,10,By.xpath("//p[contains(text(),'We are trying to confirm your booking')]")),true);
		String url = driver.getCurrentUrl();
		String newurl = url.replace("https://www.", "https://qa2.");
		driver.get(newurl);
		Assert.assertEquals(waitForElement(driver,10,By.xpath("//p[contains(text(),'We are trying to confirm your booking')]")),true);
		Thread.sleep(2000);
		//driver.get(driver.getCurrentUrl());
		//driver.navigate().toString();
		/* elementVisible(driver, getObject("bookFailureTripID"), 10);
		TripID = getText(driver,getObject("bookFailureTripID"));
		Reporter.log("Payment Success , Booking Cancelled . Trip ID = "+TripID);
		////Reporter.log("Payment Success , Booking Cancelled . Trip ID = "+TripID);
		Thread.sleep(5000);*/

	}
	public void checkDetails(HashMap<String,String> hp,HashMap<String,String> hp1,String adult,String child,String smen,String swomen) {
		String day=hp.get("dayclasstraveller").split("\\|")[0].replace("[","").replace("]","").trim();
		String traveller=hp.get("traveller");
		//String traveller=hp.get("dayclasstraveller").split("\\|")[2].split(" ")[1];
		////Reporter.log("--"+traveller);
		String departuretime=hp.get("timings").split("\\-")[0].replace("[","").replace("]","").trim();
		String arrivaltime=hp.get("timings").split("-")[1].split("\\|")[0].replace("[","").replace("]","").trim();
		String TrainNameinSRP=hp.get("trainname").replace("[","").replace("]","").trim().toUpperCase();
		String classnameinSRP=hp.get("classname");

		String departuredayinItinerary=hp1.get("departureday").replace("[","").replace("]","").trim();
		String departuretimeinItinerary=hp1.get("departuretime").replace("[","").replace("]","").trim();
		String ArrivaltimeinItinerary=hp1.get("arrivaltime").replace("[","").replace("]","").trim();
		String TrainNameinItinerary=hp1.get("trainname").replace("[","").replace("]","").trim();
		String classinItinerary=hp1.get("classname").split(" ")[0];
		String durationinSRP=hp.get("timings").split("\\|")[1].replace("[","").replace("]","").trim();
		String durationinItinerary=hp1.get("duration").replace("[","").replace("]","").trim();
		////Reporter.log("duration in Itinerary="+hp1.get("duration"));
		////Reporter.log("day in Itinerary="+hp1.get("daydetails").replace("[","").replace("]",""));
		////Reporter.log("day in SRP="+day.replace("[","").replace("]",""));
		Assert.assertEquals(day,departuredayinItinerary.replace("[","").replace("]","").trim());
		Assert.assertEquals(departuretime,departuretimeinItinerary);
		Assert.assertEquals(arrivaltime,ArrivaltimeinItinerary);
		Assert.assertEquals(TrainNameinSRP,TrainNameinItinerary);
		Assert.assertEquals(classnameinSRP,classinItinerary);
		Assert.assertEquals(adult,traveller);
		Assert.assertEquals(durationinSRP,durationinItinerary);
	}
	public void getPaymentDetails(RemoteWebDriver driver,HashMap<String,String> hp,HashMap<String,String> hp1) throws Exception {
		waitForElement(driver,10,By.xpath("//p[contains(text(),'Card')]"));
		String trainname=driver.findElement(By.xpath("//h2[text()='Make payment']/parent::*/../div[2]/div/*/*/*[1]/div")).getText();
		String classname=driver.findElement(By.xpath("//h2[text()='Make payment']/parent::*/../div[2]/div/*/*/*/*/li[2]/p[2]")).getText();
		String departuretime=driver.findElement(By.xpath("//h2[text()='Make payment']/parent::*/../div[2]/div/*/*/*/ul/li[1]/*[1]/p[1]/span[2]")).getText();
		String departurecity=driver.findElement(By.xpath("//h2[text()='Make payment']/parent::*/../div[2]/div/*/*/*/ul/li[1]/*[1]/p[1]/span[1]")).getText();
		String departureday=driver.findElement(By.xpath("//h2[text()='Make payment']/parent::*/../div[2]/div/*/*/*/ul/li[1]/*[1]/p[2]")).getText();
		String duration=driver.findElement(By.xpath("//h2[text()='Make payment']/parent::*/../div[2]/div/*/*/*/ul/li[1]/div[2]")).getText();
		String arrivaltime=driver.findElement(By.xpath("//h2[text()='Make payment']/parent::*/../div[2]/div/*/*/*/ul/li[1]/*[3]/p[2]")).getText();
		String arrivalday=driver.findElement(By.xpath("//h2[text()='Make payment']/parent::*/../div[2]/div/*/*/*/ul/li[1]/*[3]/p[2]")).getText();
		String arrivalcity=driver.findElement(By.xpath("//h2[text()='Make payment']/parent::*/../div[2]/div/*/*/*/ul/li[1]/*[3]/p[1]/span[2]")).getText();
		hp.put("trainname",trainname);
		hp.put("classname",classname);
		hp.put("departuretime",departuretime);
		hp.put("departurecity",departurecity);
		hp.put("departureday",departureday);
		hp.put("duration",duration);
		hp.put("arrivaltime",arrivaltime);
		hp.put("arrivalday",arrivalday);
		hp.put("arrivalcity",arrivalcity);
		Assert.assertEquals(hp.get(trainname),hp1.get(trainname));
		Assert.assertEquals(hp.get(classname),hp1.get(classname));
		Assert.assertEquals(hp.get(departuretime),hp1.get(departuretime));
		Assert.assertEquals(hp.get(departurecity),hp1.get(departurecity));
		Assert.assertEquals(hp.get(departureday),hp1.get(departureday));
		Assert.assertEquals(hp.get(arrivaltime),hp1.get(arrivaltime));
		Assert.assertEquals(hp.get(arrivalday),hp1.get(arrivalday));
		Assert.assertEquals(hp.get(arrivalcity),hp1.get(arrivalcity));
		//Reporter.log(hp1);
		//Reporter.log(hp);


	}
	public HashMap<String,String> mobileTrains_Bookstep1Details(RemoteWebDriver driver,String BoardStation,HashMap<String,String> hp ) throws Exception {


		waitForElement(driver,10,getObject("MobileWeb_Trains_Continue_Button_ItineraryR"));
		//hp.put("trainname",driver.findElement(getObject("PWATrainsNameINIti")).getText());
		/*hp.put("class",driver.findElement(getObject("PWATrainsClassinIti")).getText());
		hp.put("daydetails",driver.findElement(getObject("PWATrainsDayDetailsinIti")).getText());
		hp.put("departuretime",driver.findElement(getObject("PWATrainsDepartureTimeIti")).getText());
		hp.put("departurecity",driver.findElement(getObject("PWATrainsDepartureCityinIti")).getText());
		hp.put("arrivaltime",driver.findElement(getObject("PWATrainsArrivalTime")).getText());
		hp.put("arrivalcity",driver.findElement(getObject("PWATrainsArrivalCity")).getText());
		hp.put("duration",driver.findElement(getObject("PWATrainsDuration")).getText());*/
		hp.put("trainname",driver.findElement(By.xpath("//*[text()='Review Itinerary']/parent::*/../div/div[1]/div[1]/div/p")).getText());
		hp.put("classname",driver.findElement(By.xpath("//*[text()='Review Itinerary']/parent::*/../div/div[1]/div[1]/ul/li/p[2]")).getText());
		hp.put("departureday",driver.findElement(By.xpath("//*[text()='Review Itinerary']/parent::*/../div/div[1]/div[1]/ul/li[1]/div[1]/p[2]")).getText());
		hp.put("arrivalday",driver.findElement(By.xpath("//*[text()='Review Itinerary']/parent::*/../div/div[1]/div[1]/ul/li[1]/div[3]/*[2]")).getText());
		hp.put("departuretime",driver.findElement(By.xpath("//*[text()='Review Itinerary']/parent::*/../div/div[1]/div[1]/ul/li[1]/div[1]/p[1]/span[2]")).getText());
		hp.put("departurecity",driver.findElement(By.xpath("//*[text()='Review Itinerary']/parent::*/../div/div[1]/div[1]/ul/li[1]/div[1]/p[3]")).getText());
		hp.put("arrivaltime",driver.findElement(By.xpath("//*[text()='Review Itinerary']/parent::*/../div/div[1]/div[1]/ul/li[1]/div[3]/p[1]/span[1]")).getText());
		hp.put("arrivalcity",driver.findElement(By.xpath("//*[text()='Review Itinerary']/parent::*/../div/div[1]/div[1]/ul/li[1]/div[3]/p[3]")).getText());
		hp.put("duration",driver.findElement(By.xpath("//*[text()='Review Itinerary']/parent::*/../div/div[1]/div[1]/ul/li[1]/div[2]/span")).getText());

		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/div[2]/div/button"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);*/
		safeClick(driver,getObject("MobileWeb_Trains_Continue_Button_ItineraryR"));

		Reporter.log("Proceeding from bookstep 1");
		return hp;
	}
	public  HashMap<String,String> mobileTrains_CheckSRPDetails(RemoteWebDriver driver,String Quota,String BoardStation,String TrainsName,HashMap<String,String> hp) throws Exception {
		waitForElement(driver,10,By.xpath("//*[text()='Show nearby stations']"));
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Show nearby stations']"))));

		List<WebElement> we=driver.findElements(By.xpath("//li[@role='menuitem']/*/*/*"));
		for(int i=0;i<we.size();i++) {

			////Reporter.log("i value="+i+"     ,"+we.get(i).getText());
		}

		hp.put("trainname",we.get(0).getText());
		hp.put("Price",we.get(1).getText());
		hp.put("timings",we.get(2).getText());
		hp.put("classname",we.get(3).getText());
		hp.put("traveller",driver.findElement(By.xpath("//*[contains(text(),'traveller')]")).getText().split("\\|")[2].split(" ")[1]);
		////Reporter.log("-------------------------------------"+hp.get("traveller"));
		//hp.put("dayclasstraveller",driver.findElement(By.xpath("//*[contains(text(),'traveller')]")).getText());
		safeClick(driver,getObject("MobileWeb_Trains_Selecting_Train_in_SRPR"));
		waitForElement(driver,10,getObject("MobileWeb_Trains_Selecting_Seat_Type_GSleeperR"));
		////Reporter.log(we.get(i).getText());

		//	String name=driver.findElementByXPath("//*[@id='train-"+i+"']/td[1]/p[1]/a").getText();
		////Reporter.log(name);
		//Reporter.log(name);
		//if(TrainsName.equals(name)){
		//Reporter.log("Selected train "+name+" is matching with "+TrainsName);
		////Reporter.log("Selected train "+name+" is matching with "+TrainsName);
		if(Quota == "General" )
		{
			safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_GSleeperR"));
			hp.put("dayclasstraveller",driver.findElement(By.xpath("//span[contains(text(),'Available')]//..//../p[1]")).getText());
			////Reporter.log("------------------------------------------"+hp.get("dayclasstraveller"));
			//span[contains(text(),'Available')]//..//../p[1]
			Thread.sleep(9000);
			//elementPresent_Time(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label"),10);
			//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label").click();

		}
		else if(Quota =="Tatkal"){
			safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_TatkalR"));
			driver.switchTo().alert().accept();


			//	driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/input").click();
			//Thread.sleep(9000);				
			//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();
			/*
				elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();		*/		
		}

		else if(Quota =="RAC"){
			safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_RACR"));
			Thread.sleep(9000);				
			//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();

			/*elementPresent(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label"));
				driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[2]/label").click();		*/		
		}





		//driver.findElementByXPath("//*[@id='train-"+i+"']/td[7]/button").click();
		//break;
		//}




		return hp;
	}
	public  HashMap<String,String>  CheckSRPSorting(RemoteWebDriver driver,String Quota,String BoardStation,String TrainsName,HashMap<String,String> hp) throws Exception {
		ArrayList all=new ArrayList();
		ArrayList all1=new ArrayList();
		ArrayList all2=new ArrayList();
		elementPresent(driver,getObject("MobileWeb_Trains_Selecting_Train_in_SRPR"),10);
		//safeClick(driver,getObject("MobileWeb_Trains_Selecting_Train_in_SRPR"));

		waitForElement(driver,10,getObject("MobileWeb_Trains_Selecting_Train_in_SRPR"));
		////Reporter.log();
		List<WebElement> we=driver.findElements(By.xpath("//li[@role='menuitem']/*/*/*"));
		List<WebElement> we1=driver.findElements(By.xpath("//li[@role='menuitem']/*/*[1]/p[2]"));
		//List<WebElement> we2=driver.findElements(By.xpath("//li[@role='menuitem']/*/*[2]/p[1]"));
		int j=0;
		for(int i=0;i<we1.size();i++) {
			////Reporter.log(we1.get(i).getText().substring(1));

			//ArrayList all=new ArrayList();
			all.add(Integer.parseInt(we1.get(i).getText().substring(1)));
		}
		safeClick(driver,By.xpath("//*[contains(text(),'Departure')]"));
		Thread.sleep(2000);
		List<WebElement> we2=driver.findElements(By.xpath("//li[@role='menuitem']/*/*[2]/p[1]"));
		for(int i=0;i<we2.size();i++) {
			String a=we2.get(i).getText().split("-")[0].split(":")[1];
			////Reporter.log(we2.get(i).getText().split("-")[0].split(":")[1]);
			int y=Integer.parseInt(a.trim());
			int x=((Integer.parseInt(we2.get(i).getText().split("-")[0].split(":")[0])*60));
			////Reporter.log(x);
			int z=x+y;
			////Reporter.log("z value="+z);

			all1.add(z);
			////Reporter.log(all1);
		}
		safeClick(driver,By.xpath("//*[contains(text(),'Duration')]"));
		Thread.sleep(3000);
		List<WebElement> we3=driver.findElements(By.xpath("//li[@role='menuitem']/*/*[2]/p[1]"));
		for(int i=0;i<we3.size();i++) {

			////Reporter.log("duration ----"+we3.get(i).getText().split("\\|")[1]);
			int c=we3.get(i).getText().split("\\|")[1].split(" ").length;
			////Reporter.log("c value="+c);
			if(c==4) {
				//int mday=Integer.parseInt(we2.get(i).getText().split("\\|")[1].split(" ")[0]);
				////Reporter.log(mday);
				int mday=Integer.parseInt(we3.get(i).getText().split("\\|")[1].split(" ")[1].replaceAll("[^\\d.]", ""))*60*60;
				////Reporter.log(mday);
				int mhour=Integer.parseInt(we3.get(i).getText().split("\\|")[1].split(" ")[2].replaceAll("[^\\d.]", ""))*60;
				////Reporter.log(mhour);
				int mmin=Integer.parseInt(we3.get(i).getText().split("\\|")[1].split(" ")[3].replaceAll("[^\\d.]", ""));
				int totaldur=mday+mhour+mmin;
				////Reporter.log("------------------------------"+totaldur);
				all2.add(totaldur);
			}
			else if(c==3) {
				int mhour=Integer.parseInt(we3.get(i).getText().split("\\|")[1].split(" ")[1].replaceAll("[^\\d.]", ""))*60;
				////Reporter.log("mhour======"+mhour);
				int mmin=Integer.parseInt(we3.get(i).getText().split("\\|")[1].split(" ")[2].replaceAll("[^\\d.]", ""));
				////Reporter.log("mmnin============="+mmin);
				int totaldur=mhour+mmin;
				all2.add(totaldur);
				////Reporter.log(all2);
			}
			else if(c==2) {
				int mmin=Integer.parseInt(we3.get(i).getText().split("\\|")[1].split(" ")[1].replaceAll("[^\\d.]", ""));
				int totaldur=mmin;
				all2.add(totaldur);
			}
		}
		////Reporter.log(all2);
		List copy = new ArrayList(all);
		List copy1 = new ArrayList(all1);
		List copy2 = new ArrayList(all2);
		Collections.sort(copy);
		Collections.sort(copy1);
		Collections.sort(copy2);
		////Reporter.log("copy1==="+copy1);
		////Reporter.log("copy2===="+copy2);
		//  //Reporter.log(copy2.equals(all2));
		assertTrue(copy.equals(all));
		assertTrue(copy1.equals(all1));
		assertTrue(copy2.equals(all2));
		safeClick(driver,getObject("MobileWeb_Trains_Selecting_Train_in_SRPR"));
		waitForElement(driver,10,getObject("MobileWeb_Trains_Selecting_Seat_Type_GSleeperR"));

		if(Quota == "General" )
		{
			safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_GSleeperR"));
			Thread.sleep(9000);
			//elementPresent_Time(driver,getObject("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label"),10);
			//driver.findElementByXPath("//*[@id='train-"+i+"']/td[6]/dl[1]/dd/p[1]/label").click();

		}
		else if(Quota =="Tatkal"){
			safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_TatkalR"));
			driver.switchTo().alert().accept();


		}

		else if(Quota =="RAC"){
			safeClick(driver,getObject("MobileWeb_Trains_Selecting_Seat_Type_RACR"));
			Thread.sleep(9000);				
		}
		return hp;
	}
	public void mobileTrains_Bookstep2(RemoteWebDriver driver,String Adult1,String Child1,String SMn,String SWomn,String gst) throws Exception {
		int Adult = Integer.parseInt(Adult1);
		int Children = Integer.parseInt(Child1);
		int SMen = Integer.parseInt(SMn);
		int SWomen = Integer.parseInt(SWomn);
		//elementPresent(driver,getObject("MobileWeb_Trains_In_Travellers_Text"),10);
		Thread.sleep(2000);
		waitForElement(driver,10,By.xpath("//*[contains(text(),'Adult')]"));
		for(int i=1;i<Adult+1;i++) {
			Thread.sleep(2000);

			safeClick(driver,By.xpath("//*[text()='Adult "+i+"']//..//..//../*[2]"));
			Thread.sleep(2000);
			Select title = new Select(driver.findElement(By.name("title")));
			title.selectByVisibleText("Mr");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_FirstNameR"),"Test");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_LastNameR"),"Test");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_AgeR"),"29");


			safeClick(driver,getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));

		}
		for(int i=1;i<SMen+1;i++) {
			////Reporter.log("//*[text()='Sr Men \"+i+\"']//..//..//../*[2]");

			safeClick(driver,By.xpath("//*[text()='Sr Men "+i+"']"));
			Select title = new Select(driver.findElement(By.name("title")));
			title.selectByVisibleText("Mr");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_FirstNameR"),"Test");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_LastNameR"),"Test");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_AgeR"),"64");
			safeClick(driver,getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));
		}
		for(int i=1;i<SWomen+1;i++) {
			////Reporter.log("//*[text()='Adult "+i+"')]");

			safeClick(driver,By.xpath("//*[text()='Sr Women "+i+"']//..//..//../*[2]"));
			Select title = new Select(driver.findElement(By.name("title")));
			title.selectByVisibleText("Mrs");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_FirstNameR"),"Test");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_LastNameR"),"Test");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_AgeR"),"60");
			safeClick(driver,getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));
		}
		for(int i=1;i<Children+1;i++) {
			////Reporter.log("//*[text()='Adult "+i+"')]");
			safeClick(driver,By.xpath("//*[text()='Child "+i+"']//..//..//../*[2]"));

			Select title = new Select(driver.findElement(By.name("title")));
			title.selectByVisibleText("Mstr");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_FirstNameR"),"Test");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_LastNameR"),"Test");
			safeType(driver,getObject("MobileWeb_Trains_In_Travellers_AgeR"),"8");
			safeClick(driver,getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));
		}

		elementPresent(driver,getObject("MobileWeb_Trains_In_Travellers_Text"),10);
		if(gst.equalsIgnoreCase("gst")) {
			safeClick(driver,getObject("PWATrainsGSTTab"));
			elementPresent(driver,By.xpath("//p[contains(text(),'GST Number')]"),10);
			safeType(driver,getObject("PWATrainsGSTNumber"),"22AAAAA0000A1Z5");
			safeType(driver,getObject("PWATrainsGSTHolderName"),"cleartrip");
			safeType(driver,getObject("PWATrainsGSTCity"),"Bangalore");
			safeClick(driver,getObject("PWATrainsGSTBookingButton"));
			//safeClick(driver,By.xpath("//p[contains(text(),'Use GSTIN for this booking')]"));
			//elementPresent(driver,By.xpath("//p[contains(text(),'GST Number')]"),10);
			//safeType(driver,By.xpath("//p[contains(text(),'GST Number')]//..//../*[1]/*[2]"),"22AAAAA0000A1Z5");
			//safeType(driver,By.xpath("//p[contains(text(),'Holder Namer')]//..//../*[1]/*[2]"),"cleartrip");
			//safeType(driver,By.xpath("//p[contains(text(),'Holder Address')]//..//../*[1]/*[2]"),"Bangalore");
			//safeClick(driver,By.xpath("//button[contains(text(),'Continue Booking')]"));
		}
		elementPresent(driver,getObject("MobileWeb_Trains_In_Travellers_Text"),10);
		if(elementPresent(driver,getObject("PWATrainsPhone"),10)) {
			safeType(driver,getObject("PWATrainsPhone"),"1234567890");

		}
		////Reporter.log("---------"+driver.findElement(By.xpath("//input[@name='email']")).getAttribute("disabled"));
		if((driver.findElement(getObject("PWATrainsEMail")).getAttribute("disabled"))==null) {
			smartType(driver,getObject("PWATrainsEMail"),"ns.likhitha@cleartrip.com");
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(getObject("MobileWeb_Trains_In_Travellers_Continue_ButtonR"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		safeClick(driver,getObject("MobileWeb_Trains_In_Travellers_Continue_ButtonR"));




	}
	public String corpTrainsPayment (RemoteWebDriver driver, String PaymentType, String Coupon, String Trip_Logger_Msg,
			String Booking_Confirmation_Message) throws Exception
	{


		if (PaymentType.equalsIgnoreCase("DEPOSITACCOUNT")) {
			safeClick(driver, getObject("Trains_PaymentPage_DepositAccount_Tab"));
		}

		else if (PaymentType.equalsIgnoreCase("CREDITCARD")) {
			safeClick(driver, getObject("TrainsCorp_PaymentPage_CreditCard_Tab"));
			safeType(driver, getObject("Trainscorp_PaymentPage_CreditCard_Number"), dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObject("Trainscorp_PaymentPage_CreditCard_Exp_Month"), "5");
			safeSelect(driver, getObject("Trainscorp_PaymentPage_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObject("Trainscorp_PaymentPage_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("Trainscorp_PaymentPage_CreditCard_Name"), dataFile.value("MasterCard_HolderName"));
			Thread.sleep(1000);
		} 

		else if (PaymentType.equalsIgnoreCase("DEBITCARD")) {
			safeClick(driver, getObject("Trainscorp_PaymentPage_DebitCard_Tab"));
			safeType(driver, getObject("Trainscorp_PaymentPage_DebitCard_Number"), dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObject("Trainscorp_PaymentPage_DebitCard_Exp_Month"), "5");
			safeSelect(driver, getObject("Trainscorp_PaymentPage_DebitCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObject("Trainscorp_PaymentPage_DebitCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeType(driver, getObject("Trainscorp_PaymentPage_DebitCard_BillName"), dataFile.value("MasterCard_HolderName"));
			Thread.sleep(2000);
		} 


		else if(PaymentType.equalsIgnoreCase("NETBANKING")){
			safeClick(driver, getObject("CorpTrains_PaymentPage_NetBanking_Tab"));		
			safeSelect(driver, getObject("Trainscorp_PaymentPage_NetBank_Dropdown"), "Bank of India");			
			Thread.sleep(1000);
			// safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));
		}

		else if(PaymentType.equalsIgnoreCase("NETBANKINGPROD")){


			safeClick(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"));
			safeSelect(driver, getObject("AirCorp_PaymentPage_NetBank_Dropdown"), "State Bank of India");
			Thread.sleep(2000);
			safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));

			if(!elementPresent_Time(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"), 5)){
				refreshPage(driver);
			}
			for(int i=0; i<=60;i++) {
				if(elementPresent_Time(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"), 1)) {
					break;
				}
				if(textPresent(driver, "Oops, your payment didn’t work", 5)) {
					Reporter.log("Oops, your payment didn’t work ");
					Assert.assertTrue(false);
				}
			}
			if(elementPresent_Time(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"), 1)){
				safeClick(driver, getObject("AirCorp_BookStep4_SBI_Bank_CancelLink"));
			} else {
				Reporter.log("Page has not redirected to Netbanking Site");
				Assert.assertEquals(true, false);
			}

			if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Sorry, our system is acting up. message is displayed");
				Assert.assertTrue(false);
			}
			if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
				Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
				Assert.assertEquals(true, false);
			}
			else Reporter.log("Page has redirected to back to Cleartrip from Netbanking Site");



		}


		else if(PaymentType.equalsIgnoreCase("NBBOI")){


			waitElement(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"), 10);
			safeClick(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"));

			safeSelect(driver, getObject("AirCorp_PaymentPage_NetBank_Dropdown"), "Bank of India");
			safeClick(driver, getObject("AirCorp_PaymentPage_BookButton"));

			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			if(elementVisible(driver, getObject("HotelCom_BookStep4_BOI_Bank_CancelLink"), 50)){
				Thread.sleep(2000);			
				Reporter.log("NB site page is displayed");
				elementPresent(driver, getObject("HotelCom_BookStep4_BOI_Bank_CancelLink"));
				safeClick(driver, getObject("HotelCom_BookStep4_BOI_Bank_CancelLink"));
			}
			else {
				Reporter.log("Netbanking Site is not displayed");
				Assert.assertTrue(false);
			}



			if(textPresent(driver, "Sorry, our system is acting up.", 5)) {
				Reporter.log("Sorry, our system is acting up. message is displayed");
				Assert.assertTrue(false);
			}
			if(!elementPresent_Time(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"), 50)){
				Reporter.log("Page has not redirected to back to Cleartrip from Netbanking Site");
				Assert.assertEquals(true, false);
			}
			else Reporter.log("Page has redirected to back to Cleartrip from Netbanking Site");

		}

		//------------------------------------------------------------- MAke Payment in QA2 Only-------------------------------------------------//

		if (MakePaymentOnlyInQA2) {
			if(!(PaymentType.equalsIgnoreCase("NETBANKINGPROD")||PaymentType.equalsIgnoreCase("NETBANKING")||(PaymentType.equalsIgnoreCase("NBBOI")))) {
				Reporter.log("Payment Button is Clicked");
				safeClick(driver, getObject("HotelCorp_PaymentPage_Payment_Button"));
				for(int i=0; i<=30; i++) {
					if(elementVisible(driver, getObject("HotelCorp_ConfirmationPage_TripID"), 5)){
						break;
					}
					if(elementVisible(driver, By.cssSelector("h1.Failure"), 1)){
						Reporter.log("Oops! Cleartrip’s system is behaving badly");
						Assert.assertTrue(false);
					}
					if(textPresent(driver, "Oops, your booking didn’t go through", 1)){
						Reporter.log("Oops, your booking didn’t go through");
						Assert.assertTrue(false);
					}
				}

				Reporter.log(Trip_Logger_Msg + TripID);
				logger.info(Trip_Logger_Msg + TripID);
				/*safeClick(driver, getObject("HotelCorp_ConfirmationPage_SignOut_Link"));
			elementVisible(driver, getObject("CorpCom_SignIN_EmailID"), 10);*/
			}
			if(PaymentType.equalsIgnoreCase("NETBANKING")) {
				Reporter.log("Payment Button is Clicked");
				safeClick(driver, getObject("Trainscorp_PaymentPage_BookButton"));
				if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_BOI_Bank_CancelLink"), 20)){
					Reporter.log("NB page not displayed");
					Assert.assertTrue(false);

				}
				else Reporter.log("NB page is displayed");				

			}							
		}

		return TripID; 

	}
	public void B2cTrains_PaxEntry(RemoteWebDriver driver,String Adult1,String Child1,String SMen,String SWomen) throws Exception {
		//elementVisible(driver, getObject("HotelCom_HomePage_Room_DropDown"), 10);
		safeSelect(driver, getObject("B2cTrains_AdultsDropdown"), Adult1);
		safeSelect(driver, getObject("B2cTrains_ChildrenDropdown"),Child1);
		safeSelect(driver, getObject("B2cTrains_SMenDropdown"),SMen);
		safeSelect(driver, getObject("B2cTrains_SWomenDropdown"),SWomen);		
	}

	public String B2cTrains_Payment(RemoteWebDriver driver,String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Text) throws Exception {
		if(common.value("TrainNewPaymentUI").equalsIgnoreCase("NEW")) {
			
			Reporter.log("NEW Payment UI");
			logMessagePageNotLoaded(driver, getObjectPayment("PaymentPage_CreditCard_Tab"), 2, "Payment Step has not loaded");
			//textPresent_Log(driver,"Enter your credit card details",10);
			textPresent_Log(driver,"Trip Details",10);
			String TripID = null;
			logURL(driver);
			waitElement(driver, getObjectPayment("PaymentPage_Payment_Button"), 30);
			if(Payment_Type.equalsIgnoreCase("CREDIT CARD")||Payment_Type == ""){		
					textPresent(driver, "Utilize the balance", 5);
					smartClick(driver, getObjectPayment("PaymentPage_Utilize_WalletBalance_CheckBox"));
					 //Thread.sleep(2000);
				   safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "Credit Card");
				   textPresent_Log(driver, "Enter your credit card details", 2);
				   Thread.sleep(2000);
				   safeType(driver, getObjectPayment("PaymentPage_CreditCard_Number"), "5123456789012346");
				   Thread.sleep(2000);
				   safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"));
				   safeSelectByText(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"), "May (05)");
				   safeSelectByIndex(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"), 1);
				   safeType(driver, getObjectPayment("PaymentPage_CreditCard_Name"), "test");
				   safeType(driver, getObjectPayment("PaymentPage_CreditCard_CVV"), "123");
				   Thread.sleep(2000);
				   safeClick(driver, getObjectPayment("PaymentPage_Payment_Button"));
				   textPresent(driver, "AXIS SIMULATOR", 5);
				   elementVisible(driver, getObjectPayment("PAY"), 30);
				   Thread.sleep(2000);
				   smartType(driver, By.id("password"), "123456");
				   //smartClick(driver, By.id("submitBtn")); 
				   smartClick(driver, getObjectPayment("PAY"));
			  }

			else if(Payment_Type.equalsIgnoreCase("Net Banking")){
				
				smartClick(driver, getObjectPayment("PaymentPage_Utilize_WalletBalance_CheckBox"));
				safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "Net Banking");
				   textPresent_Log(driver, "Popular Banks", 2);
				   safeClick(driver,getObjectPayment("PaymentPage_NB_PopularBanks"));
					//elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), 30);
					Thread.sleep(2000);
					safeClick(driver, getObjectPayment("PaymentPage_Payment_Button"));	
					safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
			}

			else if(Payment_Type == "Debit Card"){
				   //safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "Debit Card");
				   //textPresent_Log(driver, "Popular Banks", 2);	
				//textPresent(driver, "Utilize the balance", 5);
				smartClick(driver, getObjectPayment("PaymentPage_Utilize_WalletBalance_CheckBox"));
			   safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "Debit Card");
			   textPresent_Log(driver, "Enter your debit card details", 2);
			   Thread.sleep(2000);
			   safeType(driver, getObjectPayment("PaymentPage_CreditCard_Number"), "5123456789012346");
			   Thread.sleep(2000);
			   safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"));
			   safeSelectByText(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"), "May (05)");
			   safeSelectByIndex(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"), 1);
			   safeType(driver, getObjectPayment("PaymentPage_CreditCard_Name"), "CardName");
			   safeType(driver, getObjectPayment("PaymentPage_CreditCard_CVV"), "123");
			   Thread.sleep(2000);
			   safeClick(driver, getObjectPayment("PaymentPage_Payment_Button"));
			   textPresent(driver, "AXIS SIMULATOR", 5);
			   elementVisible(driver, getObjectPayment("PAY"), 30);
			   Thread.sleep(2000);
			   smartType(driver, By.id("password"), "123456");
			   //smartClick(driver, By.id("submitBtn")); 
			   smartClick(driver, getObjectPayment("PAY"));
		  
			   	}

			else if(Payment_Type == "UPI"){
				
				smartClick(driver, getObjectPayment("PaymentPage_Utilize_WalletBalance_CheckBox"));
				   safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "UPI");
				   textPresent_Log(driver, "Select UPI partner to make your payment", 2);
				   safeClick(driver, getObjectPayment("PaymentPage_UPI_PhonePe"));
					elementPresent_log(driver, getObjectPayment("MakePayment_PhonePe_Page_Pay_Btn"),"PhonePe Button", 20);
					Thread.sleep(2000);
			     safeClick(driver, getObjectPayment("PaymentPage_Payment_Button"));	
			     Thread.sleep(2000);
			 	textPresent_Log(driver,"Login to PhonePe",20);
			}
			else if(Payment_Type == "WALLETS"){
				smartClick(driver, getObjectPayment("PaymentPage_Utilize_WalletBalance_CheckBox"));
				   safeClickList(driver, getObjectPayment("PaymentPage_PaymentType_Tabs"), "Wallets");
				   textPresent_Log(driver, "Select a wallet to make your payment", 2);
				   safeClick(driver, getObjectPayment("PaymentPage_Wallet_AmazonPay"));
					elementPresent_log(driver, getObjectPayment("MakePayment_Amazon_Page_Signin"),"Amazon Signin", 20);
						   
			}
			else 
			{
				Assert.assertTrue(false);
				Reporter.log("Payment type doesnt match");
			}
	}
			else if(common.value("TrainNewPaymentUI").equalsIgnoreCase("OLD")||common.value("TrainNewPaymentUI").equalsIgnoreCase("")) {
				Reporter.log("OLD Payment UI");
				logMessagePageNotLoaded(driver, getObject("HotelCom_BookStep4_CreditCard_Tab"), 40, "Payment Step has not loaded");
			//textPresent_Log(driver,"you will be redirected to the IRCTC",10);
			//textPresent_Log(driver,"Cleartrip charges a processing fee",10);
			String TripID = null;
			waitElement(driver, getObject("HotelCom_BookStep4_CreditCard_Tab"), 50);
			if(Payment_Type == "CREDITCARD"){
				safeClick(driver,getObject("TrainsCCLink"));
				safeType(driver,getObject("CC_MasterCard_Number"),dataFile.value("CC_MasterCard_Number"));
				safeSelect(driver,getObject("CC_MasterCard_Exp_Month"),dataFile.value("CC_MasterCard_Exp_Month"));
				safeSelect(driver,getObject("CC_MasterCard_Exp_Year"),dataFile.value("CC_MasterCard_Exp_Year"));
				safeType(driver,getObject("CC_MasterCard_HolderName"),dataFile.value("CC_MasterCard_HolderName"));
				safeType(driver,getObject("CC_MasterCard_CVV"),dataFile.value("CC_MasterCard_CVV"));
				Thread.sleep(2000);
				safeClick(driver,getObject("Payment_Submit"));
	            Thread.sleep(6000);
		}
			else {
				Reporter.log("Payment type is incorrect");
				Assert.assertTrue(false);
			}
			safeClick(driver,getObjectPayment("PaymentPage_Payment_Button"));
            Thread.sleep(6000);
		
		
		
		}


		else if(Payment_Type.equalsIgnoreCase("Net Banking")){
			waitElement(driver, getObject("trainsNetBanking_Tab"), 50);
			safeClick(driver, getObject("trainsNetBanking_Tab"));
			elementPresent(driver, getObject("trainsNetbankingRetry"));
			safeSelect(driver, getObject("trainsNetbankingRetry"),NetBankingQA);
			safeClick(driver,getObject("Payment_Submit"));
		}




		else if(Payment_Type == "DEBITCARD"){
			safeClick(driver,getObject("TrainsDCLink"));
			safeType(driver,getObject("DC_MasterCard_Number"),dataFile.value("DC_MasterCard_Number"));
			safeSelect(driver,getObject("DC_MasterCard_Exp_Month"),dataFile.value("DC_MasterCard_Exp_Month"));
			safeSelect(driver,getObject("DC_MasterCard_Exp_Year"),dataFile.value("DC_MasterCard_Exp_Year"));
			safeType(driver,getObject("DC_MasterCard_HolderName"),dataFile.value("DC_MasterCard_HolderName"));
			safeType(driver,getObject("DC_MasterCard_CVV"),dataFile.value("DC_MasterCard_CVV"));
			safeClick(driver,getObject("Payment_Submit"));

		}
		
		else if(Payment_Type == "UPI"){
			safeClick(driver,getObject("Train_UPI"));
			safeClick(driver,getObject("Train_UPI_Phonepe"));
			textPresent_Log(driver,"PhonePe",10);
			safeClick(driver,getObject("Payment_Submit"));
		}
		else if(Payment_Type == "WALLETS"){
		safeClick(driver,getObject("Train_Wallet"));
		textPresent_Log(driver,"Paytm",10);
		safeClick(driver,getObject("Train_Wallet_Amazonpay"));
		safeClick(driver,getObject("Payment_Submit"));
		}
		else {
			safeClick(driver,getObject("TrainsCCLink"));
			safeType(driver,getObject("CC_MasterCard_Number"),dataFile.value("CC_MasterCard_Number"));
			safeSelect(driver,getObject("CC_MasterCard_Exp_Month"),dataFile.value("CC_MasterCard_Exp_Month"));
			safeSelect(driver,getObject("CC_MasterCard_Exp_Year"),dataFile.value("CC_MasterCard_Exp_Year"));
			safeType(driver,getObject("CC_MasterCard_HolderName"),dataFile.value("CC_MasterCard_HolderName"));
			safeType(driver,getObject("CC_MasterCard_CVV"),dataFile.value("CC_MasterCard_CVV"));			
			safeClick(driver,getObject("Payment_Submit"));

		}
		Thread.sleep(5000);
		return TripID;
		}
	public String mobileTrains_PaymentR(RemoteWebDriver driver,String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Text) throws Exception {
		//logMessagePageNotLoaded(driver, getObject("HotelCom_BookStep4_CreditCard_Tab"), 30, "Payment Step has not loaded");
		String TripID = null;
		waitForElement(driver,10,By.xpath("//p[contains(text(),'Card')]"));
		if(Payment_Type == "CREDITCARD"){
			if(elementPresent(driver,By.xpath("//*[text()='Add New Debit / Credit Card']"),10)) {
				safeClick(driver,By.xpath("//*[text()='Add New Debit / Credit Card']/parent::*/*[1]"));
			}
			//safeClick(driver,getObject("TrainsCCLink"));
			safeType(driver,getObject("MobileWeb_Trains_In_Payment_CardNoR"),dataFile.value("CC_MasterCard_Number"));
			safeType(driver,getObject("MobileWeb_Trains_In_Payment_ExpiryDateR"),"0521");

			/*safeSelect(driver,getObject("CC_MasterCard_Exp_Month"),dataFile.value("CC_MasterCard_Exp_Month"));
			safeSelect(driver,getObject("CC_MasterCard_Exp_Year"),dataFile.value("CC_MasterCard_Exp_Year"));
			safeType(driver,getObject("CC_MasterCard_HolderName"),dataFile.value("CC_MasterCard_HolderName"));*/
			safeType(driver,getObject("MobileWeb_Trains_In_Payment_CVVR"),dataFile.value("CC_MasterCard_CVV"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//button[text()='Make payment']"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			safeClick(driver,getObject("MobileWeb_Trains_In_Payment_ButtonR"));

			mobileTrains_paymentIntersitialPage(driver);
			waitForElement(driver,10,getObject("air_amex_payment_button"));
			Thread.sleep(3000);
			safeClick(driver,getObject("air_amex_payment_button"));
		}


		else if(Payment_Type.equalsIgnoreCase("Net Banking")){
			//waitForElement(driver,10,By.xpath("//input[contains(text(),'Search banks')]"));
			//waitElement(driver, getObject("trainsNetBanking_Tab"), 10);

			safeClick(driver,getObject("MobileWeb_Trains_In_Payment_NetBanking_ButtonR"));
			JavascriptExecutor js = (JavascriptExecutor) driver;

			WebElement element = driver.findElement(By.xpath("//p[contains(text(),'Select another bank')]"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			safeClick(driver,getObject("MobileWeb_Trains_In_Payment_SearchOtherBankR"));
			waitForElement(driver,10,getObject("MobileWeb_Trains_AutocompleteR"));
			safeType(driver,getObject("MobileWeb_Trains_AutocompleteR"),"Bank of India");
			safeClick(driver,getObject("MobileWeb_Trains_In_Payment_Selecting_BankOfIndiaR"));
			Thread.sleep(2000);
			/*WebElement element1 = (new WebDriverWait(driver,30))
						   .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Make Payment']")));*/
			safeClick(driver,By.xpath("//button[text()='Make payment']"));
			mobileTrains_paymentIntersitialPage(driver);
			/*waitForElement(driver,10,By.xpath("//p[text()='I remember my IRCTC password']"));
				safeClick(driver,By.xpath("//p[text()='I remember my IRCTC password']"));
				safeClick(driver,By.xpath("//*[contains(text(),'Continue Booking')]"));*/
			safeClick(driver,By.linkText("Return to Biller Site"));

			if(elementPresent(driver,By.xpath("//*[contains(@class,'Modal')]"),20)) {
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("//*[text()='Retry']")).click();
			}

			//	safeClick(driver,getObject("Payment_Submit"));
		}




		else if(Payment_Type == "DEBITCARD"){
			safeClick(driver,getObject("TrainsDCLink"));
			safeType(driver,getObject("DC_MasterCard_Number"),dataFile.value("DC_MasterCard_Number"));
			safeSelect(driver,getObject("DC_MasterCard_Exp_Month"),dataFile.value("DC_MasterCard_Exp_Month"));
			safeSelect(driver,getObject("DC_MasterCard_Exp_Year"),dataFile.value("DC_MasterCard_Exp_Year"));
			safeType(driver,getObject("DC_MasterCard_HolderName"),dataFile.value("DC_MasterCard_HolderName"));
			safeType(driver,getObject("DC_MasterCard_CVV"),dataFile.value("DC_MasterCard_CVV"));
			safeClick(driver,getObject("Payment_Submit"));

		}
		else {
			safeClick(driver,getObject("TrainsCCLink"));
			safeType(driver,getObject("CC_MasterCard_Number"),dataFile.value("CC_MasterCard_Number"));
			safeSelect(driver,getObject("CC_MasterCard_Exp_Month"),dataFile.value("CC_MasterCard_Exp_Month"));
			safeSelect(driver,getObject("CC_MasterCard_Exp_Year"),dataFile.value("CC_MasterCard_Exp_Year"));
			safeType(driver,getObject("CC_MasterCard_HolderName"),dataFile.value("CC_MasterCard_HolderName"));
			safeType(driver,getObject("CC_MasterCard_CVV"),dataFile.value("CC_MasterCard_CVV"));			
			safeClick(driver,getObject("Payment_Submit"));
		}
		Thread.sleep(5000);
		return TripID;
	}
	public void B2CTrains_paymentIntersitialPage(RemoteWebDriver driver) throws Exception
	{
		if(!common.value("TrainNewPaymentUI").equalsIgnoreCase("NEW")) {
			
		
		Thread.sleep(4000);
		driver.switchTo().frame("modal_window");
		Thread.sleep(2000);
		textPresent_Log(driver,"Get your booking confirmed",20);
		textPresent_Log(driver,"Continue to make payment",10);
		safeClick(driver,getObject("Radio_RememberPassword"));
		safeClick(driver,getObject("Continue_Payment"));



		//elementVisible(driver,getObject("IRCTC_errorPopup"),30);
		/*String errMsg = driver.findElement(By.xpath("//*[@id='loginerrorpanelagent_header_content']")).getText();
		if(errMsg.contains("Booking Detail For Client Transaction ID"))
		{
			driver.findElement(By.xpath("//*[@id='loginerrorpanelokagent']")).click();
		}
		 */		
		if(textPresent(driver, "Sorry, our system is acting up", 5)) {
			Reporter.log("Sorry, our system is acting up - msg is displayed");
			logURL(driver);
			Assert.assertTrue(false);
		}
		}
		else {

			
			Thread.sleep(15000);
			//textPresent_Log(driver, dataFile.value("IRCTC_UserName"), 5);
			/*driver.switchTo().frame("modal_window");
			Thread.sleep(2000);
			textPresent_Log(driver,"Get your booking confirmed",20);
			textPresent_Log(driver,"Continue to make payment",10);
			safeClick(driver,getObject("Radio_RememberPassword"));
			safeClick(driver,getObject("Continue_Payment"));
			Thread.sleep(4000);*/
		}
		
		
	}
	public void mobileTrains_paymentIntersitialPage(RemoteWebDriver driver) throws Exception
	{
		waitForElement(driver,10,getObject("Mobile_Trains_In_Payment_InterterestrialpageR"));
		safeClick(driver,getObject("Mobile_Trains_In_Payment_InterterestrialpageR"));
		safeClick(driver,getObject("Mobile_Trains_In_Payment_ContinueBookingButtonR"));

	}
	public void B2CTrains_Netbanking(RemoteWebDriver driver) throws Exception
	{
        if(NetBankingQA.equalsIgnoreCase("Axis Bank"))
        {
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		waitForElementVisibility(driver,getObject("B2C_NBRetry_AxisLink"),40);
		if(elementVisible(driver, getObject("B2C_NBRetry_AxisLink"), 80)){
			driver.navigate().back();
			Reporter.log("NetBanking page is displayed ");
			waitForElementVisibility(driver,By.xpath("//h1"),50);
		}
       }
		
		else if(NetBankingQA.equalsIgnoreCase("Bank of India")){
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			waitForElementVisibility(driver,getObject("B2C_NBRetry_BOILink"),40);
			if(elementVisible(driver, getObject("B2C_NBRetry_BOILink"), 30)){
				safeClick(driver,getObject("B2C_NBRetry_BOILink"));
				Reporter.log("NetBanking page is displayed ");
				
				waitForElementVisibility(driver,By.xpath("//h1"),50);
			}
		}
		else{
			driver.navigate().back();
			Reporter.log("NetBanking page not displayed");
			waitForElementVisibility(driver,By.xpath("//h1"),50);
		}
		/*if(!elementVisible(driver, getObject("HotelCom_BookStep4_CreditCard_Tab"), 80)) {
				Reporter.log("Cleartrip - Payment Retry Page is not displayed");
				Reporter.log("Bank page is not displayed ");
				Assert.assertTrue(false);
			}	 
			if(!elementVisible(driver, getObject("trainsCreditCardTab"), 50)){
				Reporter.log("Page is not redirected to Cleartrip Site from Bank Site after clicking on cancel button");
				Assert.assertTrue(false);
			} */
        if(!elementVisible(driver,By.xpath("//button[@type='submit']"),80)){
        	Reporter.log("Page is not redirected to Cleartrip Site from Bank Site after clicking on cancel button");
			Assert.assertTrue(false);
        }
			Reporter.log("Page has redirected to Cleartrip Site from Bank Site after clicking on cancel button");


		}

	
		
//		if(elementVisible(driver, getObject("trainsNetbankingCancelLink"), 60)){
//			Thread.sleep(2000);   
//			elementPresent(driver, getObject("trainsNetbankingCancelLink"));
//			safeClick(driver, getObject("trainsNetbankingCancelLink"));  
//			//Reporter.log("Bank page is displaed ");
//			if(!elementVisible(driver, getObject("HotelCom_BookStep4_CreditCard_Tab"), 60)) {
//				Reporter.log("Cleartrip - Payment Retry Page is not displayed");
//				//Reporter.log("Bank page is not displaed ");
//				Assert.assertTrue(false);
//			}	 
//			if(!elementVisible(driver, getObject("trainsCreditCardTab"), 30)){
//				Reporter.log("Page is not redirected to Cleartrip Site from Bank Site after clicking on cancel button");
//				Assert.assertTrue(false);
//			} 
//			Reporter.log("Page has redirected to Cleartrip Site from Bank Site after clicking on cancel button");
//
//
//		}

	public void mobileTrains_Netbanking(RemoteWebDriver driver) throws Exception
	{

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		if(elementVisible(driver, getObject("trainsNetbankingCancelLink"), 60)){
			Thread.sleep(2000);   
			elementPresent(driver, getObject("trainsNetbankingCancelLink"));
			safeClick(driver, getObject("trainsNetbankingCancelLink"));  
			//Reporter.log("Bank page is displaed ");
			if(!elementVisible(driver, getObject("HotelCom_BookStep4_CreditCard_Tab"), 60)) {
				Reporter.log("Cleartrip - Payment Retry Page is not displayed");
				//Reporter.log("Bank page is not displaed ");
				Assert.assertTrue(false);
			}	 
			if(!elementVisible(driver, getObject("trainsCreditCardTab"), 30)){
				Reporter.log("Page is not redirected to Cleartrip Site from Bank Site after clicking on cancel button");
				Assert.assertTrue(false);
			} 
			Reporter.log("Page has redirected to Cleartrip Site from Bank Site after clicking on cancel button");


		}

	}
	public void IRCTC_LoginPage(RemoteWebDriver driver, String IRCTC_action) throws Exception
	{
		String action = IRCTC_action;
		String TripID = null;
		if(action == "Confirm")
		{
//			safeType(driver,getObject("IRCTC_Password123"),dataFile.value("IRCTC_Password"));
//			//Reporter.log("Reached IRCTC Login page , Confirming the booking");
//			//Reporter.log("Reached IRCTC Login page , Confirming the booking");
			Reporter.log("Confirmation booking will not be done");
			Assert.assertTrue(false);
		}
		else if(action == "Cancel")
		{
			
			safeClick(driver,getObject("IRCTC_Cancel"));
			//Reporter.log("Reached IRCTC Login page , Cancelling the booking");
			Reporter.log("Reached IRCTC Login page , Canceling the booking");
		}
		else
		{
			browserClose(driver);
			//Reporter.log("Reached IRCTC Login page , Closing the window");
			Reporter.log("Reached IRCTC Login page , Closing the window");
		}

		elementVisible(driver,getObject("bookingFailurePage"), 30);

		String url = driver.getCurrentUrl();
		String newurl = url.replace("https://www.", "https://qa2.");
		//////Reporter.log(newurl);
		driver.get(newurl);
		Thread.sleep(2000);
		//driver.get(driver.getCurrentUrl());
		//driver.navigate().toString();

		textPresent(driver, "Oops, your booking didn�t go through", 50);
		elementVisible(driver, getObject("bookFailureTripID"), 5);
		TripID = getText(driver,getObject("bookFailureTripID"));
		//Reporter.log("Payment Success , Booking Cancelled . Trip ID = "+TripID);
		Reporter.log("Payment Success , Booking Cancelled . Trip ID = "+TripID);

	}





	public void Corp_IRCTC_LoginPage(RemoteWebDriver driver, String IRCTC_action) throws Exception
	{
		String action = IRCTC_action;
		String TripID = null;
		if(action == "Confirm")
		{
			safeType(driver,getObject("IRCTC_Password"),dataFile.value("IRCTC_Password"));
			Reporter.log("Reached IRCTC Login page , Confirming the booking");
			////Reporter.log("Reached IRCTC Login page , Confirming the booking");
		}
		else if(action == "Cancel")
		{
			safeClick(driver,getObject("IRCTC_Cancel"));
			Reporter.log("Reached IRCTC Login page , Cancelling the booking");
			////Reporter.log("Reached IRCTC Login page , Cancelling the booking");
		}
		else
		{
			browserClose(driver);
			Reporter.log("Reached IRCTC Login page , Closing the window");
			////Reporter.log("Reached IRCTC Login page , Closing the window");
		}
		if(!elementVisible(driver, getObject("bookFailureTripID"), 10));		
		Reporter.log("Log-in page not displayed");
		Thread.sleep(5000);		
		//Reporter.log("IRCTC login page is displayed");
		//Reporter.log("Clicked on cancel button from IRCTC login page");

	}


	public void PNR_Check(RemoteWebDriver driver, String PNR_number) throws Exception
	{
		textPresentInElement(driver,getObject("your10digitPNR"),"Your 10 digit PNR:",10);
		elementVisible(driver,getObject("PNR_input"),10);
		////Reporter.log("Trains PNR Form");
		Reporter.log("Trains PNR Form");
		safeType(driver,getObject("PNR_input"),PNR_number);
		safeClick(driver,getObject("getPNRstatus"));
		elementVisible(driver,getObject("trainNamePNRStatus"),15);
		Thread.sleep(3000);
		safeClick(driver,getObject("back2PNRenquiryForm"));	
		textPresentInElement(driver,getObject("your10digitPNR"),"Your 10 digit PNR:",10);
		Thread.sleep(5000);
		if(isElementPresent(driver,getObject("recentlyCheckPNR")))
		{
			Reporter.log("Recently checked PNRs displayed");
			////Reporter.log("Recently checked PNRs displayed");
			safeClick(driver,getObject("recentlyCheckPNR"));
			elementVisible(driver,getObject("trainNamePNRStatus"),15);
			Thread.sleep(3000);
			safeClick(driver,getObject("back2PNRenquiryForm"));	
		}
		Thread.sleep(10000);
	}
	public void TrainsCalendar_Search(RemoteWebDriver driver,String DeptStation,String ArrStation,String Class)
	{
		//	elementVisible(driver,getObject(""));
	}
	public boolean waitForElement(RemoteWebDriver driver, int time,By by) throws Exception{

		//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int i = 0;
		boolean elementActiveFlag = false;
		//TODO change for debug mode
		long timerNow = new Date().getTime();
		for (i = 0; (new Date().getTime() - timerNow) / 1000 <= time; i++) {
			if(elementPresent(driver, by,1)){
				elementActiveFlag=true;
				break;	
			}
			/*else if(elementPresent(driver,By.xpath("//android.widget.ImageView[contains(@resource-id,'moeCloseButton')]"),1)){
				safeClick(driver,By.xpath("//android.widget.ImageView[contains(@resource-id,'moeCloseButton')]"));
			}*/
			Thread.sleep(1000);
		}
		////Reporter.log((new Date().getTime() - timerNow) / 1000 + " seconds taken for " + by + "  to load");
		if (!elementActiveFlag) {
			// //Reporter.log("Element By "+by+ " Not Loaded in"+ time +"Seconds");
		}

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return elementActiveFlag;
	}
	
	public void B2C_bookStep2(RemoteWebDriver driver,String Adult1,String Child1,String SMn,String SWomn) throws Exception{
		int Adult = Integer.parseInt(Adult1);
		int SMen = Integer.parseInt(SMn);
		Thread.sleep(2000);
		for (int i=1; i <= Adult;i++ )
		{
			safeSelect(driver,getObject("AdultTitle"+i+""),dataFile.value("AdultTitle"+i));
			safeType(driver,getObject("AdultFirstName"+i),dataFile.value("AdultFirstName"+i));
			safeType(driver,getObject("AdultSecName"+i),dataFile.value("AdultSecName"+i));
			safeSelect(driver,getObject("AdultAge"+i),dataFile.value("AdultAge"+i));
			safeSelectByIndex(driver,By.xpath("//*[@id='AdultBerthPreference"+i+"']"),2);
			if(elementVisible(driver,By.xpath(".//*[@id='AdultMealPreference"+i+"']"),10)){
				safeSelectByIndex(driver,By.xpath(".//*[@id='AdultMealPreference"+i+"']"),2);
			}
			safeSelect(driver,getObject("AdultCountry"+i),dataFile.value("TrainsAdultCountry"));
			//driver.findElement(By.id("AdultCountry1")).sendKeys("India");			
          }

		if(SMen>=1){
		for (int i=1; i <= SMen;i++ )
		{
			safeSelect(driver,getObject("STitle"+i+""),dataFile.value("STitle"+i));
			safeType(driver,getObject("SFirstName"+i),dataFile.value("SFirstName"+i));
			safeType(driver,getObject("SSecName"+i),dataFile.value("SSecName"+i));
			safeSelect(driver,getObject("SAge"+i),dataFile.value("SAge"+i));
			safeSelectByIndex(driver,By.xpath("//*[@id='SeniorBerthPreference"+i+"']"),2);
			if(elementVisible(driver,By.xpath(".//*[@id='SeniorMealPreference"+i+"']"),10)){
				safeSelectByIndex(driver,By.xpath(".//*[@id='SeniorMealPreference"+i+"']"),2);
			}
			safeSelect(driver,getObject("SeniorCountry"+i),dataFile.value("TrainsAdultCountry"));
			if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
				safeClick(driver,getObject("B2C_Train_Infantslink"));
				if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
					safeClick(driver,getObject("B2C_Train_Infantslink"));
				}
			}
				
			}
			//driver.findElement(By.id("SeniorCountry1")).sendKeys("India");
			//driver.findElement(By.id("SeniorCountry1")).sendKeys(Keys.ENTER);
			
		}
			safeSelect(driver,getObject("ConNameTitle"),dataFile.value("ConNameTitle"));
			safeType(driver,getObject("ConFName"),dataFile.value("ConFName"));		
			safeType(driver,getObject("ConSName"),dataFile.value("ConSName"));


//			safeClick(driver,getObject("TrainsGSTCheckbox"));		
//			safeType(driver,getObject("GSTno"),dataFile.value("GSTno"));
//			safeType(driver,getObject("GSTname"),dataFile.value("GSTname"));
//			safeType(driver,getObject("GSTaddress"),dataFile.value("GSTaddress"));


			safeType(driver,getObject("ConCountry"),dataFile.value("ConCountry"));
			safeType(driver,getObject("ConMobileNo"),dataFile.value("ConMobileNo"));
			safeClick(driver,getObject("ContinueToPay"));	
		}
	
	public void B2C_Book2(RemoteWebDriver driver,String Adult1,String Child1,String SMn,String SWomn) throws Exception {
		
		int Adult = Integer.parseInt(Adult1);
		int Children = Integer.parseInt(Child1);
		int SMen = Integer.parseInt(SMn);
		int SWomen = Integer.parseInt(SWomn);
		Thread.sleep(2000);
		for (int i=1; i <= Adult;i++ )
		{
			safeSelect(driver,getObject("AdultTitle"+i+""),dataFile.value("AdultTitle"+i));
			safeType(driver,getObject("AdultFirstName"+i),dataFile.value("AdultFirstName"+i));
			safeType(driver,getObject("AdultSecName"+i),dataFile.value("AdultSecName"+i));
			safeSelect(driver,getObject("AdultAge"+i),dataFile.value("AdultAge"+i));
			Thread.sleep(2000);
			safeSelectByIndex(driver,By.xpath("//*[@id='AdultBerthPreference"+i+"']"),2);
			safeSelect(driver,getObject("AdultCountry"+i),dataFile.value("TrainsAdultCountry"));
			if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
				safeClick(driver,getObject("B2C_Train_Infantslink"));
				if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
					safeClick(driver,getObject("B2C_Train_Infantslink"));
				}
			}
			//driver.findElement(By.id("AdultCountry1")).sendKeys("India");			


		}
		for (int i=1; i <= Children;i++ )
		{
			safeSelect(driver,getObject("ChildTitle"+i+""),dataFile.value("ChildTitle"+i));
			safeType(driver,getObject("ChildFirstName"+i),dataFile.value("ChildFirstName"+i));
			safeType(driver,getObject("ChildSecName"+i),dataFile.value("ChildSecName"+i));
			safeSelect(driver,By.xpath("//*[@name='ChildAge"+i+"']"),dataFile.value("ChildAge"+i));
			safeSelectByIndex(driver,By.xpath("//*[@id='ChildBerthPreference"+i+"']"),2);	
			safeSelect(driver,getObject("ChildCountry"+i),dataFile.value("TrainsChildCountry"));
			driver.findElement(By.id("ChildCountry1")).sendKeys("India");	
			if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
				safeClick(driver,getObject("B2C_Train_Infantslink"));
				if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
					safeClick(driver,getObject("B2C_Train_Infantslink"));
				}
			}


		}		
		// Berth Preference xpath has same id element so to handle it , added an extra condition
		if ( SMen >=1 && SWomen >=1){
			List<WebElement> we1=driver.findElements(By.xpath("//select[contains(@id,'SeniorTitle')]"));
			int STraveller = we1.size();
			for (int i=1; i <= STraveller;i++ )
			{
				safeSelect(driver,getObject("STitle"+i+""),dataFile.value("STitle"+i));
				safeType(driver,getObject("SFirstName"+i),dataFile.value("SFirstName"+i));
				safeType(driver,getObject("SSecName"+i),dataFile.value("SSecName"+i));
				safeSelect(driver,getObject("SAge"+i),dataFile.value("SAge"+i));
				safeSelectByIndex(driver,By.xpath("//*[@id='SeniorBerthPreference"+i+"']"),2);
				safeSelect(driver,getObject("SeniorCountry"+i),dataFile.value("TrainsChildCountry"));
				if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
					safeClick(driver,By.cssSelector("#removeInfantLink>img"));
					if(driver.findElement(By.cssSelector("#removeInfantLink>img")).isDisplayed()){
						safeClick(driver,By.cssSelector("#removeInfantLink>img"));
					}
				}
				//driver.findElement(By.id("SeniorCountry1")).sendKeys("India");	
				
			}
		}
	
	safeSelect(driver,getObject("ConNameTitle"),dataFile.value("ConNameTitle"));
	safeType(driver,getObject("ConFName"),dataFile.value("ConFName"));		
	safeType(driver,getObject("ConSName"),dataFile.value("ConSName"));


//	safeClick(driver,getObject("TrainsGSTCheckbox"));		
//	safeType(driver,getObject("GSTno"),dataFile.value("GSTno"));
//	safeType(driver,getObject("GSTname"),dataFile.value("GSTname"));
//	safeType(driver,getObject("GSTaddress"),dataFile.value("GSTaddress"));


	safeType(driver,getObject("ConCountry"),dataFile.value("ConCountry"));
	safeType(driver,getObject("ConMobileNo"),dataFile.value("ConMobileNo"));
	safeClick(driver,getObject("ContinueToPay"));	
	
	
	}
	
	public void irctcPopup_Validation(RemoteWebDriver driver) throws Exception{
		elementPresent_log(driver,getObject("IRCTC_UserName"),"Username", 10);
		textPresent_Log(driver,"To continue with this booking",10);
		safeClick(driver,By.xpath(".//*[@id='statusForm']/fieldset/dl/dd[2]/a"));
		textPresent_Log(driver,"Your Password is as you selec",10);
		textPresent_Log(driver,"You will have to use new email address",10);
		driver.switchTo().parentFrame();
		Thread.sleep(2000);
		safeClick(driver,By.xpath("//a[@id='close']"));
	}
	
	public void B2CTrains_paymentIntersitialPagewithforgotpasswordflow(RemoteWebDriver driver) throws Exception
	{
		
		Thread.sleep(6000);
		driver.switchTo().frame("modal_window");
		textPresent_Log(driver,"Get your booking confirmed",20);
		textPresent_Log(driver,"Continue to make payment",10);
		safeClick(driver,By.xpath(".//*[@id='irctc_not_remember']"));
		safeClick(driver,By.xpath(".//*[@id='otp_verify_btn']"));
		String parent=driver.getWindowHandle();
		Set<String>s1=driver.getWindowHandles();
		Iterator<String> I1= s1.iterator();
		while(I1.hasNext())
		{
		 
		   String child_window=I1.next();
		   if(!parent.equals(child_window))
		   {
		   driver.switchTo().window(child_window);
		   driver.manage().window().maximize();
		   Reporter.log(driver.switchTo().window(child_window).getTitle());
		   Thread.sleep(3000);
		   textPresent_Log(driver,"Captcha letters are case sensitive and to be entered",20);
		   textPresent_Log(driver,"Designed and Hosted by CRIS",10);
		   driver.close();
		 }
		}
		driver.switchTo().window(parent);
        Thread.sleep(4000);
		driver.switchTo().frame("modal_window");
		Thread.sleep(2000);
		safeClick(driver,getObject("Radio_RememberPassword"));
		safeClick(driver,getObject("Continue_Payment"));



		//elementVisible(driver,getObject("IRCTC_errorPopup"),30);
		/*String errMsg = driver.findElement(By.xpath("//*[@id='loginerrorpanelagent_header_content']")).getText();
		if(errMsg.contains("Booking Detail For Client Transaction ID"))
		{
			driver.findElement(By.xpath("//*[@id='loginerrorpanelokagent']")).click();
		}
		 */		

	}
	
	
	public void calendarTrainDetails(RemoteWebDriver driver) throws Exception{
		String Host = common.value("host"); 
		if(Host.equalsIgnoreCase("www")){
			Thread.sleep(2000);
			safeClick(driver,By.xpath(".//*[@id='ContentFrame']/div[2]/table/tbody/tr[1]/td[1]/a"));
			driver.switchTo().frame("modal_window");
			Thread.sleep(3000);
			textPresent_Log(driver,"Timing and Distance",40);
			textPresent_Log(driver,"Stations that the Trains Pass Through",10);
			textPresent_Log(driver,"Average Ticket Price",10);
			textPresent_Log(driver,"Important Passenger Information",10);
			elementPresent_log(driver,By.xpath(".//*[@id='submit_search_form']"),"Seach flights button",10);
	 }
	}
	
	public void B2C_TrainsWallet(RemoteWebDriver driver, String Payment_Type) throws InterruptedException{
		if(Payment_Type =="UPI"){
			textPresent_Log(driver,"kira kumar",10);
			textPresent_Log(driver,"VERIFY",10);
			Reporter.log("PhonePe page is displayed");
			driver.navigate().back();
			Reporter.log("Phonepe page was displayed - ending the booking process");
		
		}
		else if(Payment_Type =="WALLETS"){
			textPresent_Log(driver,"Login with your Amazon account",10);
			textPresent_Log(driver,"Create your Amazon account",10);
			Reporter.log("Amazon page is displayed");
			driver.navigate().back();
			Reporter.log("Amazon page was displayed hence ending the booking process");
		}
		
	}
	
	public String B2cTrains_Payment2(RemoteWebDriver driver,String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Text) throws Exception {
		logMessagePageNotLoaded(driver, getObject("HotelCom_BookStep4_CreditCard_Tab"), 40, "Payment Step has not loaded");
		textPresent_Log(driver,"Transfer funds from",10);
		String TripID = null;
		waitElement(driver, getObject("HotelCom_BookStep4_CreditCard_Tab"), 50);
		if(Payment_Type == "CREDITCARD"){
			safeClick(driver,getObject("TrainsCCLink"));
			safeType(driver,getObject("CC_MasterCard_Number"),dataFile.value("CC_MasterCard_Number"));
			safeSelect(driver,getObject("CC_MasterCard_Exp_Month"),dataFile.value("CC_MasterCard_Exp_Month"));
			safeSelect(driver,getObject("CC_MasterCard_Exp_Year"),dataFile.value("CC_MasterCard_Exp_Year"));
			safeType(driver,getObject("CC_MasterCard_HolderName"),dataFile.value("CC_MasterCard_HolderName"));
			safeType(driver,getObject("CC_MasterCard_CVV"),dataFile.value("CC_MasterCard_CVV"));
			Thread.sleep(4000);
			safeClick(driver,getObject("Payment_Submit"));
            Thread.sleep(6000);
	}
		return TripID;
	}
	
	
	public void B2cTrains_HomepageSearchwithIRCTCValidation(RemoteWebDriver driver,String DStation, String AStation, String Class, String TDate, String Adult1,String Child1,String SMen,String SWomen) throws Exception {	
		elementVisible(driver,getObject("B2cTrains_HomePage_TrainTab"),30);
		//logMessagePageNotLoaded(driver, getObject("HotelCom_HomePage_HotelTab"), 2, "Home Page has not loaded");
		safeClick(driver, getObject("B2cTrains_HomePage_TrainTab"));
		textPresent_Log(driver,"Indian Railways IRCTC Train Tickets Reservation",10);
		elementPresent_Time(driver, getObject("B2cTrains_HomePage_FromStation"), 10);
		safeAutocomplete_CHMM(driver, getObject("B2cTrains_HomePage_FromStation"), getObject("B2cTrains_HomePage_SearchAjax"), DStation);
		safeAutocomplete_CHMM(driver, getObject("B2cTrains_HomePage_ToStation"), getObject("B2cTrains_HomePage_SearchAjax"), AStation);
		safeSelect(driver,getObject("B2cTrains_Class"),Class);
		selectCalendarDate(driver,getObject("B2cTrains_MonthClick"),getObject("B2cTrains_MonthNext"),1,TDate);
		//selectCalendarThisMonth(driver,getObject("B2cTrains_MonthClick"),(TDate));
		//selectCalendarThisMonth(driver,getObject("B2cTrains_Date"),TDate);
		B2cTrains_PaxEntry(driver, Adult1,Child1,SMen,SWomen);
		Reporter.log("Train Searched for " + DStation + " to : " + AStation + " for Date : " + (TDate));
		safeClick(driver, getObject("B2cTrains_SearchButton"));
		Thread.sleep(5000);	
		driver.switchTo().frame("modal_window");
		Thread.sleep(5000);
		textPresent_Log(driver,"To continue with this booking",20);
		elementPresent_log(driver,getObject("IRCTC_UserName"),"Username",20);
		safeType(driver,getObject("IRCTC_UserName"),"testcltp@11");
		safeClick(driver,getObject("IRCTC_UN_popup"));
        if(elementVisible(driver,By.xpath(".//*[@id='statusCall']"),5)){
			Thread.sleep(2000);
			String ErrorMessage=driver.findElement(By.xpath(".//*[@id='statusCall']")).getText();
			Reporter.log("Invalid Username Error Message: " +ErrorMessage);
			textPresent_Log(driver,"Your IRCTC username seems invalid. Please enter a valid IRCTC username and proceed",10);
			driver.findElement(getObject("IRCTC_UserName")).clear();
			safeType(driver,getObject("IRCTC_UserName"),dataFile.value("IRCTC_UserName"));
			safeClick(driver,getObject("IRCTC_UN_popup"));
            Thread.sleep(2000);
		}
		elementVisible(driver,getObject("B2cTrains_SearchButton"), 10);		
		safeClick(driver, getObject("B2cTrains_SearchButton"));

	}
	
	public String B2cTrains_TrainsToolLinks(RemoteWebDriver driver,String Link) throws Exception{
		//Links on IRCTC Search Page
				safeClick(driver,getObjectPlatform(Link));
				return Link;
		
	}
	public String B2cTrains_TrainsToolLinks_Validation(RemoteWebDriver driver,String element, String text) throws Exception{
		//Links on IRCTC Search Page
		     modalWindow(driver);
		      Thread.sleep(4000);
				textPresent_Log(driver,text,20);
				elementPresent_log(driver,getObjectPlatform(element),"element is present",10);
				return text;
		
	}	
}
	