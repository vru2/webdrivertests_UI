package test.java.  accountsUI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.Reporter;
import redis.clients.jedis.Jedis;
import test.java.common.WrapperMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Accounts_UI_Common extends WrapperMethod
{
	public String otp;
	public RemoteWebDriver driver;
	public String mobile1="4535353876";
	public String email="testcltp333@gmail.com";
	public String Type;
	public String value;
	public String action;
	public String email1="testcltp160@gmail.com";
	public String password="Cleartrip@123";
	public String mobile3="6353443437";
	public String mobile2="4424244225";


	public void Signup(String Type, String Signuptype, String Mobile1, String Mobile2) throws Exception {

		if (Signuptype == "Signup_mobile_only") {
			if(elementVisible(driver,getObjectPlatform("Accounts_close_popup"),2)) {
				String parent = driver.getWindowHandle();
				Set<String> s1 = driver.getWindowHandles();
				Iterator<String> I1 = s1.iterator();
				while (I1.hasNext()) {
					String child_window = I1.next();
					if (!parent.equals(child_window)) {
						driver.switchTo().window(child_window);
						driver.navigate().to(driver.getCurrentUrl());
						System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
						Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
					}
					safeClick(driver,getObjectPlatform("Accounts_close_popup"));
				}
			}
		/*  elementPresent(driver, getObjectPlatform("Accounts_nav_bar"), 2);
		  safeClick(driver, getObjectPlatform("Accounts_nav_bar"));*/
			elementPresent(driver, getObjectPlatform("Accounts_login_button"), 2);
			safeClick(driver, getObjectPlatform("Accounts_login_button"));
			String parent = driver.getWindowHandle();
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> I1 = s1.iterator();
			while (I1.hasNext()) {
				String child_window = I1.next();
				if (!parent.equals(child_window)) {
					driver.switchTo().window(child_window);
					driver.navigate().to(driver.getCurrentUrl());
					System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
					Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
				}
			}
			elementVisible(driver, getObjectPlatform("Accounts_login_mobilenumber"), 2);
			safeClick(driver, getObjectPlatform("Accounts_login_mobilenumber"));
			// Validate mobile number field
			safeClick(driver, getObjectPlatform("Accounts_login_getotp"));
			textPresent(driver, "Please enter a phone number", 2);
			safeClick(driver, getObjectPlatform("Accounts_login_mobilenumber"));
			safeType(driver, getObjectPlatform("Accounts_login_mobilenumber"), "1123456");
			safeClick(driver, getObjectPlatform("Accounts_login_getotp"));
			textPresent(driver, "Please enter a valid phone number", 2);

			//Signup
			safeClick(driver, getObjectPlatform("Accounts_login_mobilenumber"));
			safeType(driver, getObjectPlatform("Accounts_login_mobilenumber"), Mobile1);
			safeClick(driver, getObjectPlatform("Accounts_login_getotp"));
			textPresent(driver,"Enter OTP sent to the number",2);
			Thread.sleep(2000);
			enterotp(Type,"+91"+mobile1,"","");
			safeClick(driver,getObjectPlatform("Accounts_login_validateotp"));
			if(elementVisible(driver,getObjectPlatform("Account_login_none"),2))
			{
				safeClick(driver,getObjectPlatform("Account_login_none"));
			}
			Thread.sleep(1000);
			safeClick(driver,getObjectPlatform("Account_login_personaldetails_skip"));
			elementPresent(driver, getObjectPlatform("Accounts_nav_bar"), 2);
			safeClick(driver, getObjectPlatform("Accounts_nav_bar"));
			textPresent(driver,mobile1,2);
			System.out.println("Signup successful with mobilenumber only");
			ArrayList<String> id=db_accounts_people_MySQL(mobile1);
			String people_id= id.get(0);
			System.out.println(people_id);
			ArrayList<String> delete_id= db_accounts_deletepeopleid_MySQL(Mobile2);
			if(delete_id.isEmpty())
			{
				System.out.println("peoplerecord deleted successfully");
			}
		}
		if (Signuptype == "Signup_merge") {
			if(elementVisible(driver, getObjectPlatform("Accounts_close_popup"),5))
			{
				String parent = driver.getWindowHandle();
				Set<String> s1 = driver.getWindowHandles();
				Iterator<String> I1 = s1.iterator();
				while (I1.hasNext()) {
					String child_window = I1.next();
					if (!parent.equals(child_window)) {
						driver.switchTo().window(child_window);
						driver.navigate().to(driver.getCurrentUrl());
						System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
						Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
					}
					safeClick(driver, getObjectPlatform("Accounts_close_popup"));
				}
			}
			//elementPresent(driver, getObjectPlatform("Accounts_nav_bar"), 5);
			//safeClick(driver, getObjectPlatform("Accounts_nav_bar"));
			elementPresent(driver, getObjectPlatform("Accounts_login_button"), 2);
			safeClick(driver, getObjectPlatform("Accounts_login_button"));
			String parent = driver.getWindowHandle();
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> I1 = s1.iterator();
			while (I1.hasNext()) {
				String child_window = I1.next();
				if (!parent.equals(child_window)) {
					driver.switchTo().window(child_window);
					driver.navigate().to(driver.getCurrentUrl());
					System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
					Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
				}
			}
			//Signup
			elementVisible(driver, getObjectPlatform("Accounts_login_mobilenumber"), 2);
			safeClick(driver, getObjectPlatform("Accounts_login_mobilenumber"));
			safeType(driver, getObjectPlatform("Accounts_login_mobilenumber"), mobile2);
			safeClick(driver, getObjectPlatform("Accounts_login_getotp"));
			textPresent(driver,"Enter OTP sent to the number",5);
			Thread.sleep(2000);
			enterotp("SIGNIN","+91"+mobile2,"","");
			safeClick(driver,getObjectPlatform("Accounts_login_validateotp"));
			if(elementVisible(driver,getObjectPlatform("Account_login_none"),2))
			{
				safeClick(driver,getObjectPlatform("Account_login_none"));
			}
			//Signup_Merge
			safeClick(driver,getObjectPlatform("Account_login_personaldetails_email"));
			safeType(driver,getObjectPlatform("Account_login_personaldetails_email"),email);
			safeClick(driver,getObjectPlatform("Account_login_personaldetails_email_verify"));
			Thread.sleep(1000);
			enterotp("SIGNUP_MERGE","",email,"");
			textPresent(driver,"Email Verified",2);
			safeClick(driver,getObjectPlatform("Account_login_personaldetails_update"));
			elementPresent(driver, getObjectPlatform("Accounts_nav_bar"), 2);
			safeClick(driver, getObjectPlatform("Accounts_nav_bar"));
			textPresent(driver,mobile2,2);
			System.out.println("Signup merge successful with mobile and email");
			ArrayList<String> id=db_accounts_people_MySQL(mobile2);
			String people_id= id.get(0);
			System.out.println(people_id);
			ArrayList<String> delete_id= db_accounts_deletepeopleid_MySQL(mobile2);
			if(delete_id.isEmpty())
			{
				System.out.println("peoplerecord deleted successfully");
			}
		}
		if (Signuptype == "Link_Phone") {
			if(elementVisible(driver, getObjectPlatform("Accounts_close_popup"),5))
			{
				String parent = driver.getWindowHandle();
				Set<String> s1 = driver.getWindowHandles();
				Iterator<String> I1 = s1.iterator();
				while (I1.hasNext()) {
					String child_window = I1.next();
					if (!parent.equals(child_window)) {
						driver.switchTo().window(child_window);
						driver.navigate().to(driver.getCurrentUrl());
						System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
						Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
					}
					safeClick(driver, getObjectPlatform("Accounts_close_popup"));
				}
			}
			//elementPresent(driver, getObjectPlatform("Accounts_nav_bar"), 5);
			//safeClick(driver, getObjectPlatform("Accounts_nav_bar"));
			elementPresent(driver, getObjectPlatform("Accounts_login_button"), 5);
			safeClick(driver, getObjectPlatform("Accounts_login_button"));
			String parent = driver.getWindowHandle();
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> I1 = s1.iterator();
			while (I1.hasNext()) {
				String child_window = I1.next();
				if (!parent.equals(child_window)) {
					driver.switchTo().window(child_window);
					driver.navigate().to(driver.getCurrentUrl());
					System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
					Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
				}
			}
			//Signin
			Thread.sleep(4000);
			elementVisible(driver, getObjectPlatform("Account_login_email"), 15);
			safeClick(driver, getObjectPlatform("Account_login_email"));
			elementVisible(driver,getObjectPlatform("Account_login_emailid"),5);
			safeType(driver, getObjectPlatform("Account_login_emailid"), email1);
			safeClick(driver, getObjectPlatform("Account_login_password"));
			safeType(driver,getObjectPlatform("Account_login_password"),password);
			safeClick(driver,getObjectPlatform("Account_login_signin"));
			textPresent(driver,"Verify mobile number",10);
			elementVisible(driver,getObjectPlatform("Account_login_mobile"),5);
			safeClick(driver,getObjectPlatform("Account_login_mobile"));
			safeType(driver,getObjectPlatform("Account_login_mobile"),mobile3);
			safeClick(driver,getObjectPlatform("Account_login_mobileverify"));
			Thread.sleep(1000);
			enterotp("UPDATE_MOBILE","+91"+mobile3,"","");
			safeClick(driver,getObjectPlatform("Accounts_login_validateotp"));
			elementPresent(driver, getObjectPlatform("Accounts_nav_bar"), 2);
			safeClick(driver, getObjectPlatform("Accounts_nav_bar"));
			textPresent(driver,mobile3,2);
			System.out.println("Signup merge successful with mobile and email");
			ArrayList<String> id=db_accounts_peopleMOBILE_MySQL(email1);
			String people_mobile= id.get(0);
			System.out.println(people_mobile);
			ArrayList<String> id2=db_accounts_deletepeoplenudge_MySQL();
			ArrayList<String> delete_id= db_accounts_deletemobileid_MySQL(email1);
			ArrayList<String> id1=db_accounts_peopleMOBILE_MySQL(email1);
			String people_mobile1= id1.get(0);
			System.out.println(people_mobile1);
			System.out.println("Mobile number deleted successfully");

			Response resp ;
			resp= RestAssured.given().
					when().
					log().all().
					header("accept","*/*").
					header("auth_key","7GHT#@D65yhgder4R").
					header("x-ct-caller-app","test").
					delete("http://172.29.23.248:9001/user/v2/cache/65233114");
			System.out.println(resp.asString());
		}

	}
	public void RedisHandler (RemoteWebDriver driver, String Type, String value, String email, String action){
		Jedis jedis = new Jedis("http://172.29.23.218:6379");
		System.out.println("Connection Successful");
		System.out.println("The server is running" + jedis.ping());
		otp = jedis.get("ACCOUNTS_SERVICE_MOBILE_LOGIN_KEY_" + value + email +Type + action);
		System.out.println("OTP is "+otp);
		jedis.close();
	}

	public void getotp (String Type,String value, String email, String action)
	{
		RedisHandler(driver,Type,value,email,action);
		System.out.println(otp);
	}

	public void enterotp(String Type,String mobile, String email, String action) throws Exception {
		getotp(Type,mobile,email,action);
		String otp1= String.valueOf(otp.charAt(0));
		String otp2= String.valueOf(otp.charAt(1));
		String otp3= String.valueOf(otp.charAt(2));
		String otp4= String.valueOf(otp.charAt(3));
		/*	String otp5= String.valueOf(otp.charAt(4));
		System.out.println(otp5);
		String otp6= String.valueOf(otp.charAt(5));
		System.out.println(otp6);*/
		Thread.sleep(1000);
		safeClick(driver,getObjectPlatform("Accounts_login_otp1"));
		safeType(driver,getObjectPlatform("Accounts_login_otp1"), otp1);
		safeClick(driver,getObjectPlatform("Accounts_login_otp2"));
		safeType(driver,getObjectPlatform("Accounts_login_otp2"), otp2);
		safeClick(driver,getObjectPlatform("Accounts_login_otp3"));
		safeType(driver,getObjectPlatform("Accounts_login_otp3"), otp3);
		safeClick(driver,getObjectPlatform("Accounts_login_otp4"));
		safeType(driver,getObjectPlatform("Accounts_login_otp4"), otp4);
/*		safeClick(driver,getObjectPlatform("Accounts_login_otp5"));
		safeType(driver,getObjectPlatform("Accounts_login_otp5"), otp5);
		safeClick(driver,getObjectPlatform("Accounts_login_otp6"));
		safeType(driver,getObjectPlatform("Accounts_login_otp6"), otp6);*/
	}

	public ArrayList<String> db_accounts_people_MySQL (String number) throws
			SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String MySQL_URL = "jdbc:mysql://172.29.23.229:3306/accounts";
			String MySQL_User = "cleartrip";
			String MySQL_Password = "1nterl3av3";
			String query = "select ID from people where mobile=" + number;
			Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
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
	public ArrayList<String> db_accounts_peopleMOBILE_MySQL (String number) throws
			SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String MySQL_URL = "jdbc:mysql://172.29.23.229:3306/accounts";
			String MySQL_User = "cleartrip";
			String MySQL_Password = "1nterl3av3";
			String query = "select MOBILE from people where USERNAME="+"'"+ email1+"'";
			Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
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

	public ArrayList<String> db_accounts_deletepeopleid_MySQL (String number) throws
			SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String MySQL_URL = "jdbc:mysql://172.29.23.229:3306/accounts";
			String MySQL_User = "cleartrip";
			String MySQL_Password = "1nterl3av3";
			String query = "DELETE IGNORE from people where mobile=" + number;
			String query1 = "COMMIT";
			Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				ResultSet myRes1 = myCon.createStatement().executeQuery(query1);
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
	public ArrayList<String> db_accounts_deletemobileid_MySQL (String number) throws
			SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String MySQL_URL = "jdbc:mysql://172.29.23.229:3306/accounts";
			String MySQL_User = "cleartrip";
			String MySQL_Password = "1nterl3av3";
			String query = "UPDATE PEOPLE SET MOBILE=NULL,COUNTRY_CODE=NULL,MOBILE_VERIFIED=NULL,MOBILE_VERIFIED_TIMESTAMP=NULL WHERE USERNAME="+"'" + email1+"'";
			String query1 = "COMMIT";
			Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				ResultSet myRes1 = myCon.createStatement().executeQuery(query1);
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

	public ArrayList<String> db_accounts_deletepeoplenudge_MySQL () throws
			SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String MySQL_URL = "jdbc:mysql://172.29.23.229:3306/accounts";
			String MySQL_User = "cleartrip";
			String MySQL_Password = "1nterl3av3";
			String query = "DELETE FROM PEOPLE_NUDGE WHERE PEOPLE_ID=65233114";
			String query1 = "COMMIT";
			Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				ResultSet myRes1 = myCon.createStatement().executeQuery(query1);
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

	public ArrayList<String> db_promotionalservice_referraldetails_MySQL (String number) throws
			SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String MySQL_URL = "jdbc:mysql://172.29.23.230:3306/promotional_schema";
			String MySQL_User = "promo_user";
			String MySQL_Password = "Pr0mote426";
			String query = "select ID from people where mobile=" + number;
			Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
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
}