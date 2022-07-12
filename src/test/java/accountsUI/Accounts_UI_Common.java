package test.java.  accountsUI;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.Reporter;
import test.java.common.WrapperMethod;
import redis.clients.jedis.Jedis;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Accounts_UI_Common extends WrapperMethod
{
  public String otp;
  public RemoteWebDriver driver;
  public String mobile="1231231424";
  public String email="testcltp39@gmail.com";
  public String Type;
  public String value;

  public void Signup(String Type, String Signuptype) throws Exception {
	  if (Signuptype == "Signup_mobile_only") {
		  if(elementVisible(driver, By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Log in or Sign up'])[1]/following::*[name()='svg'][1]"),2)) {
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
				  safeClick(driver, By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Log in or Sign up'])[1]/following::*[name()='svg'][1]"));
			  }
		  }
		  elementPresent(driver, getObjectPlatform("Accounts_nav_bar"), 2);
		  safeClick(driver, getObjectPlatform("Accounts_nav_bar"));
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
			  safeType(driver, getObjectPlatform("Accounts_login_mobilenumber"), mobile);
			  safeClick(driver, getObjectPlatform("Accounts_login_getotp"));
			  textPresent(driver,"Enter OTP sent to the number",2);
			  enterotp(Type,"+91"+mobile);
			  safeClick(driver,getObjectPlatform("Accounts_login_validateotp"));
			  if(elementVisible(driver,getObjectPlatform("Account_login_none"),2))
			  {
				  safeClick(driver,getObjectPlatform("Account_login_none"));
			  }
			  safeClick(driver,getObjectPlatform("Account_login_personaldetails_skip"));
		      elementPresent(driver, getObjectPlatform("Accounts_nav_bar"), 2);
		      safeClick(driver, getObjectPlatform("Accounts_nav_bar"));
			  textPresent(driver,mobile,2);
			  System.out.println("Signup successful with mobilenumber only");
			  ArrayList<String> id=db_accounts_people_MySQL(mobile);
			  String people_id= id.get(0);
			  System.out.println(people_id);
			  ArrayList<String> delete_id= db_accounts_deletepeopleid_MySQL(mobile);
			  if(delete_id.isEmpty())
			  {
				  System.out.println("peoplerecord deleted successfully");
			  }
	  }
	  if (Signuptype == "Signup_merge") {
		  if(elementVisible(driver, By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Log in or Sign up'])[1]/following::*[name()='svg'][1]"),2)) {
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
				  safeClick(driver, By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Log in or Sign up'])[1]/following::*[name()='svg'][1]"));
			  }
		  }
		  elementPresent(driver, getObjectPlatform("Accounts_nav_bar"), 2);
		  safeClick(driver, getObjectPlatform("Accounts_nav_bar"));
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
		  safeType(driver, getObjectPlatform("Accounts_login_mobilenumber"), mobile);
		  safeClick(driver, getObjectPlatform("Accounts_login_getotp"));
		  textPresent(driver,"Enter OTP sent to the number",2);
		  enterotp("SIGNIN","+91"+mobile);
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
		  enterotp("SIGNUP_MERGE",email);
		  textPresent(driver,"Email Verified",2);
		  safeClick(driver,getObjectPlatform("Account_login_personaldetails_update"));
		  elementPresent(driver, getObjectPlatform("Accounts_nav_bar"), 2);
		  safeClick(driver, getObjectPlatform("Accounts_nav_bar"));
		  textPresent(driver,mobile,2);
		  System.out.println("Signup merge successful with mobile and email");
		  ArrayList<String> id=db_accounts_people_MySQL(mobile);
		  String people_id= id.get(0);
		  System.out.println(people_id);
		  ArrayList<String> delete_id= db_accounts_deletepeopleid_MySQL(mobile);
		  if(delete_id.isEmpty())
		  {
			  System.out.println("peoplerecord deleted successfully");
		  }
	  }
  }
		  public void RedisHandler (RemoteWebDriver driver,String Type,String value){
			  Jedis jedis = new Jedis("http://172.17.64.102:6379");
			  System.out.println("Connection Successful");
			  System.out.println("The server is running" + jedis.ping());
			  otp = jedis.get("ACCOUNTS_SERVICE_MOBILE_LOGIN_KEY_" + value + Type);
			  System.out.println("OTP is "+otp);
			  jedis.close();
		  }

		  public void getotp (String Type,String value)
		  {
			  RedisHandler(driver,Type,value);
			  System.out.println(otp);
		  }

		  public void enterotp(String Type,String value) throws Exception {
			  getotp(Type,value);
			  String otp1= String.valueOf(otp.charAt(0));
			  System.out.println(otp1);
			  String otp2= String.valueOf(otp.charAt(1));
			  System.out.println(otp2);
			  String otp3= String.valueOf(otp.charAt(2));
			  System.out.println(otp3);
			  String otp4= String.valueOf(otp.charAt(3));
			  System.out.println(otp4);
			  String otp5= String.valueOf(otp.charAt(4));
			  System.out.println(otp5);
			  String otp6= String.valueOf(otp.charAt(5));
			  System.out.println(otp6);
			  safeClick(driver,getObjectPlatform("Accounts_login_otp1"));
			  safeType(driver,getObjectPlatform("Accounts_login_otp1"), otp1);
			  safeClick(driver,getObjectPlatform("Accounts_login_otp2"));
			  safeType(driver,getObjectPlatform("Accounts_login_otp2"), otp2);
			  safeClick(driver,getObjectPlatform("Accounts_login_otp3"));
			  safeType(driver,getObjectPlatform("Accounts_login_otp3"), otp3);
			  safeClick(driver,getObjectPlatform("Accounts_login_otp4"));
			  safeType(driver,getObjectPlatform("Accounts_login_otp4"), otp4);
			  safeClick(driver,getObjectPlatform("Accounts_login_otp5"));
			  safeType(driver,getObjectPlatform("Accounts_login_otp5"), otp5);
			  safeClick(driver,getObjectPlatform("Accounts_login_otp6"));
			  safeType(driver,getObjectPlatform("Accounts_login_otp6"), otp6);
		  }

		  public ArrayList<String> db_accounts_people_MySQL (String RefundType) throws
		  SQLException, ClassNotFoundException {
			  ArrayList<String> data = new ArrayList<String>();
			  ArrayList<String> Name = new ArrayList<String>();
			  {
				  String MySQL_URL = "jdbc:mysql://172.17.64.165:3306/accounts";
				  String MySQL_User = "cleartrip";
				  String MySQL_Password = "1nterl3av3";
				  String query = "select ID from people where mobile=" + mobile;
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

		  public ArrayList<String> db_accounts_deletepeopleid_MySQL (String RefundType) throws
		  SQLException, ClassNotFoundException {
			  ArrayList<String> data = new ArrayList<String>();
			  ArrayList<String> Name = new ArrayList<String>();
			  {
				  String MySQL_URL = "jdbc:mysql://172.17.64.165:3306/accounts";
				  String MySQL_User = "cleartrip";
				  String MySQL_Password = "1nterl3av3";
				  String query = "DELETE from people where mobile=" + mobile;
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

		  public ArrayList<String> db_promotionalservice_referraldetails_MySQL (String RefundType) throws
		  SQLException, ClassNotFoundException {
			  ArrayList<String> data = new ArrayList<String>();
			  ArrayList<String> Name = new ArrayList<String>();
			  {
				  String MySQL_URL = "jdbc:mysql://172.17.32.150:3306/promotional_schema";
				  String MySQL_User = "promo_user";
				  String MySQL_Password = "Pr0mote426";
				  String query = "select ID from people where mobile=" + mobile;
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
