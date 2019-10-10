// Framework - Cleartrip Automation
// Author - Kiran Kumar

package domainServices;

import static org.junit.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

@SuppressWarnings("deprecation")
public class Accounts extends AirCommonMethod {

	public void Accounts_HomepageSignIn(RemoteWebDriver driver) throws Exception {
		String Host = common.value("host");
		String mainWindow = driver.getWindowHandle();
		Thread.sleep(2000);
		if(!elementVisible(driver, getObject("Acc_Menu_dropdown"), 10)) {
			refreshPage(driver);
		}
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 10);
		elementPresent_log(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), "Signin not shown", 1);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"));

		elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 20);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
		Thread.sleep(1000);
		driver.switchTo().frame("modal_window");	
		Thread.sleep(1000);
		elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
		if(Host.equalsIgnoreCase("qa2")) {
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("AccountsUserName"));
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("AccountsPassword"));	
			Thread.sleep(4000);
			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			Thread.sleep(2000);

			if(textPresent(driver, "The username or password you entered is incorrect", 2)) {
				Reporter.log("The username or password you entered is incorrect");
				Assert.assertTrue(false);
			}
			Thread.sleep(5000);
		}
		if(Host.equalsIgnoreCase("www")) {
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("AccountsUserName_Trips"));
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("AccountsPassword_Trips"));
			safeClick(driver, getObject("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		}
		//elementNotVisible(driver, By.id("wait_spinner"), 20);
		//Thread.sleep(2000);
	}

	public void Accounts_HomepageSignIn_Prod(RemoteWebDriver driver) throws Exception {
		Thread.sleep(2000);
		safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("AccountsUserName"));
		safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("AccountsPassword"));
		Thread.sleep(2000);
		safeClick(driver, getObject("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		//elementNotVisible(driver, By.id("wait_spinner"), 20);
		//Thread.sleep(2000);
	}

	public void Accounts_HomepageSignInProd(RemoteWebDriver driver) throws Exception {
		String Host = common.value("host");
		String mainWindow = driver.getWindowHandle();
		Thread.sleep(2000);
		if(!elementVisible(driver, getObject("Acc_Menu_dropdown"), 10)) {
			refreshPage(driver);
		}
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 10);
		elementPresent_log(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), "Signin not shown", 1);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"));

		elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 20);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
		Thread.sleep(1000);
		driver.switchTo().frame("modal_window");	
		Thread.sleep(2000);
		elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
		if(Host.equalsIgnoreCase("www")) {
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("AccountsUserName_Trips"));
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("AccountsPassword_Trips"));
			safeClick(driver, getObject("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			if(textPresent(driver, "The username or password you entered is incorrect", 2)) {
				Reporter.log("The username or password you entered is incorrect");
				Assert.assertTrue(false);
			}
			Thread.sleep(5000);
		}
		//elementNotVisible(driver, By.id("wait_spinner"), 20);
		//Thread.sleep(2000);
	}

	public void AccountsFacebookLogin(RemoteWebDriver driver) throws Exception {


		elementPresent(driver, By.id("userAccountLink"));

		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

		safeClick(driver, getObject("AirCom_HomePage_SignIN"));
		Reporter.log("Sign in to Cleartrip page is opened");

		assertFalse("Sign in page not opened", textPresent(driver, "Sign in to Cleartrip", 10));
         Thread.sleep(4000);
		driver.switchTo().frame("modal_window");
		
		Thread.sleep(6000);

	}

	public void DeleteAndReisterSignin(RemoteWebDriver driver) throws Exception {
		String Host = common.value("host");
		String mainWindow = driver.getWindowHandle();
		Thread.sleep(2000);
		if(!elementVisible(driver, getObject("Acc_Menu_dropdown"), 10)) {
			refreshPage(driver);
		}
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 10);
		elementPresent_log(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), "Signin not shown", 1);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"));

		elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 20);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
		Thread.sleep(1000);
		driver.switchTo().frame("modal_window");	
		Thread.sleep(1000);
		elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
		if(Host.equalsIgnoreCase("qa2")) {
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"),"testcltp6@gmail.com");
			Thread.sleep(1000);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"),"cleartrip");	
			Thread.sleep(8000);
			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			}
			
		if(Host.equalsIgnoreCase("www")) {
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"),"varalakshmi.venkateshaiah@cleartrip.com");
			Thread.sleep(2000);
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"),"cleartrip");
             Thread.sleep(3000);
			safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));
			Thread.sleep(3000);
			if(!elementVisible(driver, getObject("HotelCom_SignIn_ModalWindow_Email"),10)){
				Reporter.log("sigin successfull");
			} else{
				Thread.sleep(2000);
				safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"),"varalakshmi.venkateshaiah@cleartrip.com");
				Thread.sleep(2000);
				safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"),"cleartrip");
	             Thread.sleep(3000);
				safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));
			}
		}

		/*safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("AccountsUserName1"));
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("AccountsPassword1"));			
			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			Thread.sleep(5000);*/

		/*safeType(driver, getObject("B2cTrains_SignIn_Acc_Email"), dataFile.value("AccountDeleteAndRegisterEmailID"));
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"), dataFile.value("AccountDeleteAndRegisterPassword"));
			safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));
		 */
	}

	public boolean getAccountsPage(RemoteWebDriver driver) throws Exception {
		elementVisible(driver,getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 50);
		safeClick(driver,getObjectHotels("HotelCom_HomePage_AccountSignin_Header"));
		safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));

		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));
		safeClick(driver, getObject("Acc_Dashboard_Link_Dropdown_Menu"));
		if (elementPresent(driver, getObject("Acc_Trips_Tab"), 40)) {
			return true;
		}
		return false;

	}


	public void resetPassword(RemoteWebDriver driver) throws Exception {
		Thread.sleep(2000);
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_YourTrips"), 20);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 20);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
		Thread.sleep(1000);
		driver.switchTo().frame("modal_window");	
		elementPresent_log(driver, By.linkText("I forgot my password"), "I forgot my password", 30);
		safeClick(driver, By.linkText("I forgot my password"));
		textAssert(driver, "Forgot your password", 30);
		safeType(driver, By.id("email"), "cleartriptester@gmail.com");
		safeClick(driver, By.id("reset_submit"));

		textAssert(driver, "Check your email to reset your password", 30);
		textAssert(driver, "We’ve sent an email to this address", 1);

	}


	public void testEditProfileChanges(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObject("Acc_Profile_EditButton"));
		Thread.sleep(100);
		elementVisible(driver, By.xpath("//form[@id='ProfileForm']/div[2]/h3"), 20);
		textAssert(driver, "Contact details", 1);
		textAssert(driver, "General information", 1);
		textAssert(driver, "Travel preferences", 1);
		textAssert(driver, By.xpath("//form[@id='ProfileForm']/div[2]/h3"), "Contact details");
		textAssert(driver, By.xpath("//form[@id='ProfileForm']/div[4]/h3"), "General information");
		textAssert(driver, By.xpath("//div[@id='profile_TravelPrefs']/h3"), "Travel preferences");		

		String name;
		if (driver.findElement(getObject("Acc_Profile_FName"))
				.getAttribute("value").equals("Test")) {
			name = "TestEdit";
		} else {
			name = "Test";
		}
		safeType(driver, getObject("Acc_Profile_FName"), name);
		safeType(driver, getObject("Acc_Profile_LName"), name);
		driver.findElement(By.id("country_code")).sendKeys("India +91");
		String s=RandomStringUtils.randomNumeric(4);
	    safeType(driver,getObject("AccountProfileMobilenumber"),"949804"+s);
		safeType(driver,getObject("AccountProfileAddress"),"BTM");
		safeType(driver,getObject("AccountProfileCity"),"Bangalore");
		safeType(driver,getObject("AccountProfileState"),"Karnataka");
		safeType(driver,getObject("AccountProfilePin"),"560076");

		//safeType(driver,getObject("AccountProfileCountry"),"India");
		safeAutocomplete(driver,getObject("AccountProfileCountry"),getObject("AccountProfileCountryselect"),"India");
		//safeClick(driver,getObject("AccountProfilePassportCountrydropdown"));

		scrollToElement(driver,getObject("AccountProfilePassportEDOBDay"));
		safeClick(driver,getObject("AccountProfilePassportEDOBDay"));
		safeSelectint(driver,getObject("AccountProfilePassportEDOBDay"),22);
		safeClick(driver,getObject("AccountProfilePassportEDOBMonth"));
		safeSelectByText(driver,getObject("AccountProfilePassportEDOBMonth"),"Aug");
		safeClick(driver,getObject("AccountProfilePassportEDOBYear"));
		safeSelectByValue(driver,getObject("AccountProfilePassportEDOBYear"),"1987");
		safeType(driver,getObject("AccountProfilePassport"),"R65567687");
		safeAutocomplete(driver,getObject("AccountProfilePassportCountry"),getObject("AccountProfilePassportCountrydropdown"),"India");


		safeType(driver,getObject("AccountProfileBirthPlace"),"Kolar");
		safeAutocomplete(driver,getObject("AccountProfileNationality"),getObject("AccountProfileNationalitySelect"),"India");
		safeClick(driver,getObject("AccountProfilePassportIssueDay"));
		safeSelectint(driver,getObject("AccountProfilePassportIssueDay"),12);
		safeClick(driver,getObject("AccountProfilePassportIssueMonth"));
		safeSelectByText(driver,getObject("AccountProfilePassportIssueMonth"),"Jan");
		safeClick(driver,getObject("AccountProfilePassportIssueYear"));
		safeSelectByValue(driver,getObject("AccountProfilePassportIssueYear"),"2017");
		safeClick(driver,getObject("AccountProfilePassportExpiryDay"));
		safeSelectint(driver,getObject("AccountProfilePassportExpiryDay"),12);
		safeClick(driver,getObject("AccountProfilePassportExpiryMonth"));
		safeSelectByText(driver,getObject("AccountProfilePassportExpiryMonth"),"Feb");
		safeClick(driver,getObject("AccountProfilePassportExpiryYear"));
		safeSelectByValue(driver,getObject("AccountProfilePassportExpiryYear"),"2027");
		Thread.sleep(2000);
		driver.findElement(By.id("requestOtp")).sendKeys("123456");
		safeClick(driver, getObject("Acc_Profile_SaveChanges"));

		elementVisible(driver, getObject("Acc_Profile_OTPcheck"), 20);
		
		textPresent_Log(driver, "The OTP provided by you is incorrect.", 10);


		if (waitElement(driver, getObject("Acc_Profile_OTPcheck"), 1)) {
			assertTrue("Required OTP to save profile", driver.findElement(getObject("Acc_Profile_OTPvalidation"))
					.getText()
					.contains("The OTP provided by you is incorrect."));
		} else {
			assertTrue("Profile changes not reflecting. Failure!", driver
					.findElement(getObject("Acc_Profile_Name")).getText()
					.equals("Mr. TestEdit TestEdit"));
		}
		Reporter.log("Required OTP to save profile message is displayed");


		//		elementVisible(driver, getObject("Acc_Profile_FormSuccessMessage"), 20);
		//		textPresent_Log(driver, "Great, your changes have been saved.", 10);			
		//		if (waitElement(driver, getObject("Acc_Profile_FormSuccessMessage"), 1)) {
		//			assertTrue("Profile saved successfully", driver.findElement(getObject("Acc_Profile_FormSuccessMessage"))
		//					.getText()
		//					.contains("Great, your changes have been saved."));
		//		} else {
		//			assertTrue("Profile changes not reflecting. Failure!", driver
		//					.findElement(getObject("Acc_Profile_Name")).getText()
		//					.equals("Mr. TestEdit TestEdit"));
		//		}
		//		Reporter.log("Great, your changes have been saved. text is displayed");
	}


	public void addCardInExpressway(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObject("Acc_Expressway_Button"));
		if (waitElement(driver, getObject("Acc_Expressway_AddCard"), 3)) {
			safeType(driver, getObject("Acc_Expressway_CardNo"),dataFile.value("CCNumber"));
			safeType(driver, getObject("Acc_Expressway_NameOnCard"),dataFile.value("CCName"));
			safeSelect(driver, getObject("Acc_Expressway_CardExpirationMonth"),"5");
			safeSelect(driver, getObject("Acc_Expressway_CardExpirationYear"),dataFile.value("CCYear"));
			safeClick(driver, getObject("Acc_Expressway_AddCard"));
			if(textPresent(driver,"Card already exists", 2)){
				safeClick(driver, getObject("Acc_Expressway_AddCard"));
			}
		} else {
			Reporter.log("Card cant be added. Error!");
			assertTrue(false);
		}

		if (elementVisible(driver, getObject("Acc_Expressway_Final"), 3)) {
			safeSelect(driver, getObject("Acc_Expressway_Title"), "Mr");
			safeType(driver, getObject("Acc_Expressway_FName"), "Test");
			safeType(driver, getObject("Acc_Expressway_LName"), "Test");
			safeType(driver, getObject("Acc_Expressway_PhoneNo"), "1234567890");
			safeClick(driver, getObject("Acc_Expressway_Final"));
		}
	}

	public void AccountsFacebookReturntoCleartrip(RemoteWebDriver driver) throws Exception {
		Thread.sleep(1000);
		safeClick(driver, getObject("B2C_Accounts_FacebookLogin"));
		elementPresent_log(driver, By.xpath("//a/i"), "FB Page ", 20);

		safeType(driver, getObject("B2C_Accounts_FacebookEmail"),
				dataFile.value("UserIdForFacebooklogin"));
		safeType(driver, getObject("B2C_Accounts_Facebookpassword"),
				dataFile.value("UserpasswordForFacebooklogin"));

		safeClick(driver, getObject("B2C_Accounts_Facebooksubmit"));
		Thread.sleep(2000);

		if (!textPresent(driver, "Fly anywhere. Fly everywhere.", 20)) {
			Reporter.log("Home page not loaded after FB login");
			Assert.assertEquals(true, false);
		}
		/*assertFalse("Facebook redirection not happend to cltp home page!",
				!elementPresent(driver, By.id("SearchForm"), 1));
		assertFalse("Facebook redirection not happend to cltp homepage!",
				!elementPresent(driver, By.id("RoundTrip"), 1));
		assertFalse("Facebook redirection not happend to cltp homepage!",
				!elementPresent(driver, By.id("OneWay"), 1));*/
		textPresent_Log(driver, "Fly anywhere. Fly everywhere." , 30);
	}

	public void AccountsManageTripsUnsignedtripflow(RemoteWebDriver driver)
			throws Exception {
		if (waitElement(driver, getObject("AirCom_HomePage_ManageTrips"), 3)) {
			driver.findElement(getObject("AirCom_HomePage_ManageTrips")).click();
		} else {
			Reporter.log("Home page not loading. Error!");
			assertTrue(false);
		}

		textPresent(driver, "Your account", 5);
		Thread.sleep(2000);
		driver.switchTo().frame("modal_window");

	}

	public void AccountsManageTripsUnsignedtripflowOtpVerify(
			RemoteWebDriver driver) throws Exception

	{
		elementPresent_Time(driver, getObject("AirCom_EmailAddress"), 10);
		// waitElement(driver, getObject("AirCom_EmailAddress"), 1);

		safeType(driver, getObject("AirCom_EmailAddress"),dataFile.value("AccountsUserName_Trips"));

		String TripID_Prod= "190401816972";
		String TripID_QA= "Q191008526154";



		if(ProductionUrl) {
			safeType(driver, By.id("tripidSecond"), TripID_Prod);

			driver.findElement(getObject("AirCom_RegisterButton")).click();
			waitForElementVisibility(driver, By.id("otp"),20);


			if(textPresent(driver,"OTP verification", 10)){
				Reporter.log("OTP verification page is displaed");
				driver.findElement(By.id("otp")).sendKeys("123456");
				driver.findElement(By.id("otpVerifyBtn")).click();
				textPresent_Log(driver, "OTP you have entered is invalid",10);
			}

			else {
				Reporter.log("OTP verification page is not displaed");
				assertTrue(false);
			}
		}
		else
		{


			//safeType(driver, By.id("tripidSecond"), TripID_Prod);

			safeType(driver, By.id("tripidSecond"), TripID_QA);
			driver.findElement(getObject("AirCom_RegisterButton")).click();
			waitForElementVisibility(driver, By.id("otp"),20);

			if(textPresent(driver,"OTP verification", 10)){
				Reporter.log("OTP verification page is displaed");
				driver.findElement(By.id("otp")).sendKeys("123456");
				driver.findElement(By.id("otpVerifyBtn")).click();
				Thread.sleep(2000);
				textPresent_Log(driver, "OTP you have entered is invalid",10);

			}

			else {
				Reporter.log("OTP verification page is not displaed");
				assertTrue(false);
			}

		}
		/*textPresent_Log(driver, "OTP verification", 5);		

			}
			else 
			{
				assertTrue("Trip details via manage trips login is not working",
						elementPresent_Time(driver, By.id("otpVerifyBtn"), 10));

			elementPresent_log(driver, By.id("otpVerifyBtn"), " otpVerifyBtn ", 10);
			}*/
	}

	public void ConfirmationPage_npsFeature(RemoteWebDriver driver) throws Exception {
		safeClick(driver, By.xpath("//*[@class='npsScale__cell'][9]"));
		if(!elementPresent_log(driver, By.xpath("//textarea"), "NPS is not working", 10)) {
			Reporter.log("NPS is not working ");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//textarea"));
		safeType(driver, By.xpath("//textarea"), "testing");
		safeClick(driver, By.xpath("//input[@type='submit']"));
		waitForElement(driver, 10,
				By.xpath("//*[contains(text(),'We appreciate')]"));
		String x = getText(driver,
				By.xpath("//*[contains(text(),'We appreciate')]"));
		Assert.assertEquals(
				" We appreciate you taking the time to help us do better. "
				.trim(), x.trim());
	}


	public boolean waitForElement(RemoteWebDriver driver, int time, By by) throws Exception {



		int i = 0;

		boolean elementActiveFlag = false;

		long timerNow = new Date().getTime();

		for (i = 0; (new Date().getTime() - timerNow) / 1000 <= time; i++) {

			if (elementPresent(driver, by, 1)) {

				elementActiveFlag = true;

				break;

			}


			Thread.sleep(1000);

		}


		return elementActiveFlag;

	}

	public boolean checkIfSignedIn(RemoteWebDriver driver) throws Exception {

		if (waitElement(driver, By.xpath("//a[@id='userAccountLink']/span[2]"), 10)) {
			if (driver.findElement(By.xpath("//a[@id='userAccountLink']/span[2]")).getText().equals("Your trips")) {
				return false;
			}
			return true;

		} else {
			addLog("Home page not loaded", true);
			printInfo("Home page not loaded");
		}
		return true;
	}


	public String getRandomString(int length, Mode mode) throws Exception

	{

		StringBuilder buffer = new StringBuilder();

		String characters = "";

		switch(mode)

		{

		case ALPHA:

			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

			break;

		case ALPHANUMERIC:

			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

			break;

		case NUMERIC:

			characters = "123456789";

			break;

		}

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) 

		{

			double index = Math.random() * charactersLength;

			buffer.append(characters.charAt((int) index));

		}

		return buffer.toString();

	}


	public void airCom_HomepageRegisterWithHQRareId(RemoteWebDriver driver, String user, String pass) throws Exception {

		safeClick(driver, getObject("Acc_User_Dropdown_Menu"));

		safeClick(driver, getObject("AirCom_HomePage_Register"));

		Thread.sleep(1000);

		driver.switchTo().frame("modal_window");



		if (waitElement(driver, By.id("username1"), 13)) {

			safeType(driver, By.id("username1"), user);

			safeClick(driver, By.id("registerButton"));

			Thread.sleep(5000);



			if (waitElement(driver, By.id("password"), 13)) {

				safeType(driver, By.id("password"), pass);

				safeSelect(driver, By.id("profile_title"), "Mr");



				safeType(driver, By.xpath("//*[@id='newRegForm']/dl/dd[5]/input[1]"), "Test");

				safeType(driver, By.xpath("//*[@id='newRegForm']/dl/dd[5]/input[2]"), "Test");

				//safeType(driver, By.id("mobile_number"), "9123456789");

				safeType(driver, By.id("mobile_number"), getRandomString(10, Mode.NUMERIC));

				safeClick(driver, By.id("signUpButton"));

				Thread.sleep(2000);
				driver.navigate().refresh();

				if (!waitElement(driver, By.xpath("//*[@id='trips']/div/h1"), 15)) {

					System.out.println("Account page not loading. Error!");

					addLog("Account page not loading. Error!");

					assertTrue("Error!", false);

				} else {

					assertTrue("Account page not loading. Error!", driver.findElementByXPath("//*[@id='trips']/div/h1")

							.getText().contains("Trips you've booked"));

				}

			} else {

				System.out.println("Signup popup not loading. Error!");

				addLog("Signup popup not loading. Error!");

				assertTrue("Error!", false);

			}

		} else {

			System.out.println("Registration popup not loading. Error!");

			addLog("Registration popup not loading. Error!");

			assertTrue("Error!", false);

		}

	}

	public void Accounts_HomepageSignin(RemoteWebDriver driver) throws Exception {
		String Host = common.value("host");
		String mainWindow = driver.getWindowHandle();
		Thread.sleep(4000);
		if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 5)) {
			refreshPage(driver);
		}
		if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 5)){
			Reporter.log("Account Signin Header not displayed in Homepage");
			Assert.assertTrue(false);
		}		
		safeClick(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"));
		if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 5)){
			safeClick(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"));
		}
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 10);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
		Thread.sleep(2000);
		driver.switchTo().frame("modal_window");	
		Thread.sleep(2000);
		elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 30);
		/*
			safeClick(driver, getObject("B2cTrains_HomePage_SignIn"));
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Email"), dataFile.value("AccountsUserName"));
			safeType(driver, getObject("B2cTrains_SignIn_Acc_Password"), dataFile.value("AccountsPassword"));
			safeClick(driver, getObject("B2cTrains_SignIn_Acc_SignIN_Button"));*/
		if(Host.equalsIgnoreCase("qa")) {
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("mwemail"));
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("mwpassword"));			
		}
		if(Host.equalsIgnoreCase("www")) {
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("AccountsUserName_Trips"));
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("AccountsPassword_Trips"));	
		}
		safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		Thread.sleep(5000);
		//elementNotVisible(driver, By.id("wait_spinner"), 20);
		//Thread.sleep(2000);
		driver.switchTo().window(mainWindow);
	}

	public void Accounts_HomepageSignIn_User(RemoteWebDriver driver, String UserType) throws Exception {
		String Host = common.value("host");
		String mainWindow = driver.getWindowHandle();
		Thread.sleep(2000);
		if(!elementVisible(driver, getObject("Acc_Menu_dropdown"), 10)) {
			refreshPage(driver);
		}
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), 10);
		elementPresent_log(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"), "Signin not shown", 1);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"));
		//			if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 5)){
		//				safeClick(driver, getObjectHotels("HotelCom_HomePage_AccountSignin_Header"));
		//			}
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 20);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
		Thread.sleep(1000);
		driver.switchTo().frame("modal_window");	
		Thread.sleep(1000);
		elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
		if(Host.equalsIgnoreCase("qa2")) {
			if(UserType.equals("QA")) {
				safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("AccountsUserName"));
				safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("AccountsPassword"));			
			}

		}
		if(Host.equalsIgnoreCase("www")) {
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("AccountsUserName"));
			safeType(driver, getObject("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("AccountsPassword"));

		}
		safeClick(driver, getObject("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		//elementNotVisible(driver, By.id("wait_spinner"), 20);
		//Thread.sleep(2000);
	}


	public ArrayList<String> db_GetTripUserID(String TripType) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";
			String query =  "select TRIP_REF from trips where USER_ID = 41651522 AND "+TripType+"=1 AND BOOKING_STATUS='P'  and TRIP_REF LIKE 'Q%'"+" ORDER BY CREATED_AT desc";
			Connection myCon = DriverManager.getConnection(url, user, password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					for (int x = 1; x <= 1; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}


	public ArrayList<String> db_removeTrip(String TripID) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";
			String query =  "update  TRIPS set user_id=41586388 where TRIP_REF='"+TripID+"'";
			String commit =  "commit";
			Connection myCon = DriverManager.getConnection(url, user, password);
			if (myCon != null) {
				myCon.createStatement().executeQuery(query);
				myCon.createStatement().executeQuery(commit);
				myCon.close();
			} else
				Reporter.log("Connection not established");

			return data;
		}
	}
}