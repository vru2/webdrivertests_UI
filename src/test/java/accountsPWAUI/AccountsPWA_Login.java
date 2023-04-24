
package test.java.accountsPWAUI;

        import org.apache.commons.lang.RandomStringUtils;
        import org.openqa.selenium.By;
        import org.openqa.selenium.Keys;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.remote.RemoteWebDriver;
        import org.testng.Assert;
        import org.testng.Reporter;
        import redis.clients.jedis.Jedis;
        import test.java.common.WrapperMethod;
        import java.sql.*;
        import java.util.ArrayList;

public class AccountsPWA_Login extends WrapperMethod {
    String baseUrl = "https://qa2new.cleartrip.com/";
    String accountsPageUrl = "https://qa2new.cleartrip.com/my-account";
    public String otp;
    public RemoteWebDriver driver;
    public String mail = "testcltp9";
    public String m = "@gmail.com";
    public String e = RandomStringUtils.randomNumeric(5);
    public String email = mail + e + m;
    public String UpdateEmail = "update31@test.com";
    public String InvalidEmail = "122222";
    public String TestEmail = "iusd@iue.com";
    public String Type;
    public String value;
    public String action;

    public String number = "1111111199";


    public void RedisHandler(RemoteWebDriver driver, String Type, String value, String email, String action) throws InterruptedException {
        Jedis jedis = new Jedis("http://172.29.23.218:6379");
        System.out.println("Connection Successful");
        System.out.println("The server is running" + jedis.ping());
        Thread.sleep(3000);
        otp = jedis.get("ACCOUNTS_SERVICE_MOBILE_LOGIN_KEY_" + value + email + Type + action);
        System.out.println("OTP is " + otp);
        jedis.close();
    }
    public void getotp(String Type, String value, String email, String action) throws InterruptedException {
        RedisHandler(driver, Type, value, email, action);
        System.out.println(otp);
    }
    public void enterOtpForMobileLogin(String Type, String value, String email, String action, String Signuptype) throws Exception {

        if ((Signuptype == "Signup_mobile_only" || Signuptype == "Signup_Merge" || Signuptype == "UPDATE_EMAIL" || Signuptype == "myTripsPageUPDATE_EMAIL")) {
            getotp(Type, value, email, action);
            String otp1 = String.valueOf(otp.charAt(0));
            System.out.println(otp1);
            String otp2 = String.valueOf(otp.charAt(1));
            System.out.println(otp2);
            String otp3 = String.valueOf(otp.charAt(2));
            System.out.println(otp3);
            String otp4 = String.valueOf(otp.charAt(3));
            System.out.println(otp4);
            Thread.sleep(1000);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp1"), otp1);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp2"), otp2);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp3"), otp3);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp4"), otp4);
        }

       else if ((Signuptype == "SupportPage_SignUp")) {
            getotp(Type, value, email, action);
            String otp1 = String.valueOf(otp.charAt(0));
            System.out.println(otp1);
            String otp2 = String.valueOf(otp.charAt(1));
            System.out.println(otp2);
            String otp3 = String.valueOf(otp.charAt(2));
            System.out.println(otp3);
            String otp4 = String.valueOf(otp.charAt(3));
            System.out.println(otp4);
            Thread.sleep(1000);
            safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp1"), otp1);
            safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp2"), otp2);
            safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp3"), otp3);
            safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp4"), otp4);
        }

    }
    public void enterOtpForAddingEmail(String Type, String value, String email, String action, String Signuptype) throws Exception {

        if (Signuptype == "Signup_Merge") {
            getotp(Type, value, email, action);
            textPresent_Log(driver, "OTP has been sent to your email address.", 5);
            System.out.println("OTP has been sent to your email address.");
            String otp1 = String.valueOf(otp.charAt(0));
            System.out.println(otp1);
            String otp2 = String.valueOf(otp.charAt(1));
            System.out.println(otp2);
            String otp3 = String.valueOf(otp.charAt(2));
            System.out.println(otp3);
            String otp4 = String.valueOf(otp.charAt(3));
            System.out.println(otp4);
            String otp5 = String.valueOf(otp.charAt(4));
            System.out.println(otp4);
            String otp6 = String.valueOf(otp.charAt(5));
            System.out.println(otp4);
            Thread.sleep(1000);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp1"), "1");
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp2"), "1");
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp3"), "1");
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp4"), "1");
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp5"), "1");
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp6"), "1");
            textPresent_Log(driver, "Please enter a valid OTP", 5);
            System.out.println("Please enter a valid OTP displayed");
            driver.findElement(By.xpath("(//div[@class = \"flex\"]//div//input)[6]")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            driver.findElement(By.xpath("(//div[@class = \"flex\"]//div//input)[5]")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            driver.findElement(By.xpath("(//div[@class = \"flex\"]//div//input)[4]")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            driver.findElement(By.xpath("(//div[@class = \"flex\"]//div//input)[3]")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            driver.findElement(By.xpath("(//div[@class = \"flex\"]//div//input)[2]")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            driver.findElement(By.xpath("(//div[@class = \"flex\"]//div//input)[1]")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            //valid OTP
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp1"), otp1);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp2"), otp2);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp3"), otp3);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp4"), otp4);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp5"), otp5);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp6"), otp6);
        } else {
            getotp(Type, value, email, action);
            String otp1 = String.valueOf(otp.charAt(0));
            System.out.println(otp1);
            String otp2 = String.valueOf(otp.charAt(1));
            System.out.println(otp2);
            String otp3 = String.valueOf(otp.charAt(2));
            System.out.println(otp3);
            String otp4 = String.valueOf(otp.charAt(3));
            System.out.println(otp3);
            String otp5 = String.valueOf(otp.charAt(4));
            System.out.println(otp4);
            String otp6 = String.valueOf(otp.charAt(5));
            System.out.println(otp5);
            Thread.sleep(1000);
            safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp1"), otp1);
            safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp2"), otp2);
            safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp3"), otp3);
            safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp4"), otp4);
            safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp5"), otp5);
            safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp6"), otp6);
        }


    }
    public ArrayList<String> db_accounts_people_MySQL(String number) throws SQLException, ClassNotFoundException {

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
    public ArrayList<String> db_accounts_deletepeopleid_MySQL(String number) throws Exception {

        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> Name = new ArrayList<String>();
        {
            String MySQL_URL = "jdbc:mysql://172.29.23.229:3306/accounts";
            String MySQL_User = "cleartrip";
            String MySQL_Password = "1nterl3av3";
            String query = "DELETE IGNORE from people where mobile = " + number;
            String query1 = "COMMIT";
            Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
            if (myCon != null) {
                int myRes = myCon.createStatement().executeUpdate(query);
                int myRes1 = myCon.createStatement().executeUpdate(query1);
                System.out.println("Update rows " + myRes);
                System.out.println("Update rows " + myRes1);
                if (myRes == 1 && myRes1 == 0) {
                    System.out.println("Record deleted Successfully");
                   /* ResultSetMetaData result = myRes.getMetaData();
                    for (int x = 1; x <= 1; x++) {
                        String colName = result.getColumnName(x);
                        String colValue = myRes.getString(x);
                        Name.add(colName);
                        data.add(colValue);*/
                }
            } else {
                Assert.assertTrue(false);
                Reporter.log("DB Connection not established");
            }
            myCon.close();
            return data;
        }
    }
    public void reachToAccountsPage() throws Exception {

        if (elementVisible(driver, getObjectPlatform("AccountsPWA_accounts_button"), 5)) {
            safeClick(driver, getObjectPlatform("AccountsPWA_accounts_button"));
            Reporter.log("Accounts button clicked");
        } else {
            elementPresent(driver, getObjectPlatform("AccountsPWA_accounts_button"), 2);
            Reporter.log("Accounts button is not clickable in" + driver.getCurrentUrl());
        }
        textPresent_Log(driver, "Cleartrip for work", 5);
        textPresent_Log(driver, "My trips", 1);
        textPresent_Log(driver, "Wallet", 1);
        textPresent_Log(driver, "Saved Payment Modes", 1);
        textPresent_Log(driver, "Settings", 1);
        textPresent_Log(driver, "Support", 1);
    }
    public void reachToLoginPage() throws Exception {

        reachToAccountsPage();
        if (elementVisible(driver, getObjectPlatform("AccountsPWA_login_button"), 2)) {
            textPresent_Log(driver, "Log in to unlock personalised experience!", 2);
            textPresent_Log(driver, "Log in / Sign up", 2);
            safeClick(driver, getObjectPlatform("AccountsPWA_login_button"));
            safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
            if (elementVisible(driver, getObjectPlatform("AccountsPWA_cancel_btn"), 3)) {
                safeClick(driver, getObjectPlatform("AccountsPWA_cancel_btn"));
                Reporter.log("Cancel button  is clicked");
            } else {
                elementPresent(driver, getObjectPlatform("AccountsPWA_cancel_btn"));
                Reporter.log("Cancel button is not clickable in" + driver.getCurrentUrl());
            }
            safeClick(driver, getObjectPlatform("AccountsPWA_login_button"));
            Thread.sleep(1000);
            textPresent_Log(driver, "privacy policy", 2);
            textPresent_Log(driver, "terms of use", 2);
        } else {
            elementPresent(driver, getObjectPlatform("AccountsPWA_login_button"), 2);
            Reporter.log("Login Button is not clickable " + driver.getCurrentUrl());
        }
    }
    public void phoneNumberErrorValidation(String Page) throws Exception {

        textPresent_Log(driver, "Let’s get started", 5);
        textPresent_Log(driver, "Please enter your mobile number to continue", 1);
        textPresent_Log(driver, "Enter mobile number", 1);
        textPresent_Log(driver, "+91", 1);
        String s = driver.findElement(By.xpath("//div[contains(text(),'We no more support email based login. You can now login via mobile number & link email to access your account')]")).getText();
        if (s.equalsIgnoreCase("We no more support email based login. You can now login via mobile number & link email to access your account.")) {
            System.out.println("Text " + s + " is displaying");
            Reporter.log("Text " + s + " is displaying");
        } else {
            Assert.assertTrue(false);
            System.out.println("Text " + s + " is not displaying");
            Reporter.log("Text " + s + " is not displaying");
        }
        textPresent_Log(driver, "We no more support email based login. You can now login via mobile", 5);
        textPresent_Log(driver, "link email to access your account.", 1);
        textPresent_Log(driver, "By continuing, you agree to Cleartrip's", 1);
        textPresent_Log(driver, "privacy policy", 1);
        textPresent_Log(driver, "terms of use", 1);
        if (Page == "Support") {
            safeClick(driver, getObjectPlatform("AccountsPWA_getOTPSupportPage_btn"));
        } else {
            safeClick(driver, getObjectPlatform("AccountsPWA_getOTP_btn"));
        }
        if (textPresent_Log(driver, "Please enter a phone number", 1)) {
            Reporter.log("Error message : Please enter a phone number : is displaying");
        } else {
            Assert.assertTrue(false);
            Reporter.log("Error message : Please enter a phone number : is not getting displayed");
        }
        safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
        safeType(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), "1123456");
        if (Page == "Support") {
            safeClick(driver, getObjectPlatform("AccountsPWA_getOTPSupportPage_btn"));
        } else {
            safeClick(driver, getObjectPlatform("AccountsPWA_getOTP_btn"));
        }
        if (textPresent_Log(driver, "Please enter a valid phone number", 1)) {
            Reporter.log("Error message : Please enter a valid phone number : is displaying");
        } else {
            Assert.assertTrue(false);
            Reporter.log("Error message : Please enter a valid phone number : is not getting displayed");
        }
    }

    public void Login(String Type, String Signuptype) throws Exception {

        String n = "234";
        String s = RandomStringUtils.randomNumeric(7);
        String mobile1 = n + s;
        String mobile3 = n + s;
        String mobile2 = n + s;
        String mobile4 = n + s;
        if ((Signuptype == "Signup_mobile_only")) {
            Thread.sleep(1000);
            textPresent_Log(driver, "Get OTP", 4);
            safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
            safeType(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), mobile1);
            textPresent_Log(driver, "Enter your OTP send to", 4);
            textPresent_Log(driver, "We have sent you an OTP", 1);
            textPresent_Log(driver, mobile1, 1);
            enterOtpForMobileLogin(Type, "+91" + mobile1, "", "", Signuptype);
            textPresent_Log(driver, mobile1, 2);
            NotNowButtonCLick();
            System.out.println("Signup successful with mobileNumber only");
            //delete people record
            Thread.sleep(1000);
            ArrayList<String> id = db_accounts_people_MySQL(mobile1);
            String people_id = id.get(0);
            System.out.println(people_id);
            ArrayList<String> delete_id = db_accounts_deletepeopleid_MySQL(mobile1);
            if (delete_id.isEmpty()) {
                System.out.println("PeopleRecord deleted successfully");
            }
        }
        if ((Signuptype == "Signup_Merge")) {
            Thread.sleep(2000);
            textPresent_Log(driver, "Get OTP", 4);
            safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
            safeType(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), mobile3);
            enterOtpForMobileLogin(Type, "+91" + mobile3, "", "", Signuptype);
            textPresent_Log(driver, mobile3, 2);
            System.out.println("Signup successful with mobilenumber only");
            //signup Merge
            safeClick(driver, getObjectPlatform("AccountPWA_login_personalDetails_email"));
            safeType(driver, getObjectPlatform("AccountPWA_login_personalDetails_email"), email);
            safeClick(driver, getObjectPlatform("AccountPWA_login_personalDetails_email_verify"));
            Thread.sleep(1000);
            enterOtpForAddingEmail("SIGNUP_MERGE", "", email, "", Signuptype);
            textPresent(driver, "Email linked successfully with this account.", 2);
            System.out.println("Signup merge successful");
            //   textPresent_Log(driver, "Logged in successfully!", 6);
            ArrayList<String> id = db_accounts_people_MySQL(mobile3);
            String people_id = id.get(0);
            System.out.println(people_id);
            ArrayList<String> delete_id1 = db_accounts_deletepeopleid_MySQL(mobile3);
            if (delete_id1.isEmpty()) {
                System.out.println("PeopleRecord deleted successfully");
            }
        }
        if ((Signuptype == "UPDATE_EMAIL")) {
            Thread.sleep(2000);
            textPresent_Log(driver, "Get OTP", 2);
            safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
            safeType(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), mobile4);
            enterOtpForMobileLogin(Type, "+91" + mobile4, "", "", Signuptype);
            textPresent_Log(driver, mobile4, 2);
            NotNowButtonCLick();
            System.out.println("Signup successful with MobileNumber only");
            //textPresent_Log(driver, "Logged in successfully!", 6);
            //Update Email
            profilePage();
            Thread.sleep(2000);
            safeClick(driver, getObjectPlatform("AccountsPWA_AddEmail_btn"));
            textPresent_Log(driver, "Edit email address", 4);
            textPresent_Log(driver, "Confirm and verify", 2);
            safeClick(driver, getObjectPlatform("AccountsPWA_EmailVerify_btn"));
            textPresent_Log(driver, "Please enter an email address.", 2);
            safeClick(driver, getObjectPlatform("AccountsPWA_txtEmailAddress_btn"));
            safeType(driver, getObjectPlatform("AccountsPWA_txtEmailAddress_btn"), InvalidEmail);
            safeClick(driver, getObjectPlatform("AccountsPWA_EmailVerify_btn"));
            textPresent_Log(driver, "Invalid email address", 2);
            safeClick(driver, getObjectPlatform("AccountsPWA_txtEmailAddress_btn"));
            safeType(driver, getObjectPlatform("AccountsPWA_txtEmailAddress_btn"), TestEmail);
            safeClick(driver, getObjectPlatform("AccountsPWA_EmailVerify_btn"));
            EmailInvalidOtpValidation();
            textPresent_Log(driver, "Please enter a valid OTP.", 4);
            textPresent_Log(driver, "Verify your email address", 2);
            textPresent_Log(driver, "Haven’t received OTP?", 2);
            textPresent_Log(driver, "Enter the OTP sent to", 2);
            safeClick(driver, getObjectPlatform("AccountsPWA_ChangeEmail_btn"));
            safeClick(driver, getObjectPlatform("AccountsPWA_txtEmailAddress_btn"));
            safeType(driver, getObjectPlatform("AccountsPWA_txtEmailAddress_btn"), email);
            safeClick(driver, getObjectPlatform("AccountsPWA_EmailVerify_btn"));
            textPresent_Log(driver, email, 2);
            enterOtpForAddingEmail("UPDATE_EMAIL", "", email, "", Signuptype);
            textPresent_Log(driver, "Email address has been updated", 4);
            textPresent_Log(driver, email, 2);
            System.out.println("Update Email is successful");
            // editing and adding new email address
            safeClick(driver, getObjectPlatform("AccountsPWA_EditEmail_btn"));
            safeClick(driver, getObjectPlatform("AccountsPWA_EmailVerify_btn"));
            textPresent_Log(driver, "Email is already verified.", 2);
            safeType(driver, getObjectPlatform("AccountsPWA_txtEmailAddress_btn"), UpdateEmail);
            safeClick(driver, getObjectPlatform("AccountsPWA_EmailVerify_btn"));
            enterOtpForAddingEmail("UPDATE_EMAIL", "", UpdateEmail, "", Signuptype);
            textPresent_Log(driver, "Email address has been updated", 4);
            System.out.println("New email is Updated successfully");
            ArrayList<String> id = db_accounts_people_MySQL(mobile4);
            String people_id = id.get(0);
            System.out.println(people_id);
            ArrayList<String> delete_id1 = db_accounts_deletepeopleid_MySQL(mobile4);
            if (delete_id1.isEmpty()) {
                System.out.println("PeopleRecord deleted successfully");
            }
        }
        if ((Signuptype == "myTripsPageUPDATE_EMAIL")) {
            Thread.sleep(3000);
            textPresent_Log(driver, "Get OTP", 3);
            safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
            safeType(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), mobile2);
            enterOtpForMobileLogin(Type, "+91" + mobile2, "", "", Signuptype);
            textPresent_Log(driver, mobile2, 2);
            NotNowButtonCLick();
            //UpdateEmail via AddEmail button
            System.out.println("Signup successful with MobileNumber only");
          //  textPresent_Log(driver, "Logged in successfully!", 6);
            Thread.sleep(3000);
            if(elementVisible(driver, getObjectPlatform("AccountsPWA_addEmail_btn1"), 3)) {
                safeClick(driver, getObjectPlatform("AccountsPWA_addEmail_btn1"));
                safeClick(driver, getObjectPlatform("AccountsPWA_txtEmailAddress_btn"));
                smartType(driver, getObjectPlatform("AccountsPWA_txtEmailAddress_btn"), email);
                safeClick(driver, getObjectPlatform("AccountsPWA_EmailVerify_btn"));
                enterOtpForAddingEmail("UPDATE_EMAIL", "", email, "", Signuptype);
                System.out.println("successfully updated emailID");
            }
            else{
                Reporter.log("Existing user logged in with Email already linked");
            }
            ArrayList<String> id = db_accounts_people_MySQL(mobile2);
            String people_id = id.get(0);
            System.out.println(people_id);
            ArrayList<String> delete_id1 = db_accounts_deletepeopleid_MySQL(mobile2);
            if (delete_id1.isEmpty()) {
                System.out.println("PeopleRecord deleted successfully");
            }
        }
        if ((Signuptype == "SupportPage_SignUp")) {
            Thread.sleep(2000);
            textPresent_Log(driver, "Get OTP", 2);
            safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
            safeType(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), mobile1);
            textPresent_Log(driver, "Enter OTP sent to the number ", 2);
            textPresent_Log(driver, "Haven't received OTP?", 1);
            textPresent_Log(driver, mobile1, 1);
            textPresent_Log(driver, "Keep me logged in", 1);
            enterOtpForMobileLogin(Type, "+91" + mobile1, "", "", Signuptype);
            textPresent_Log(driver, mobile1, 2);
            System.out.println("Signup successful with mobileNumber only");
            NotNowButtonCLick();
            //textPresent_Log(driver, "Logged in successfully!", 6);
            //delete people record
            Thread.sleep(1000);
            ArrayList<String> id = db_accounts_people_MySQL(mobile1);
            String people_id = id.get(0);
            System.out.println(people_id);
            ArrayList<String> delete_id = db_accounts_deletepeopleid_MySQL(mobile1);
            if (delete_id.isEmpty()) {
                System.out.println("PeopleRecord deleted successfully");
            }
        }

    }
    public void supportPageLogin() throws Exception {
        reachToAccountsPage();
        if (elementVisible(driver, getObjectPlatform("AccountsPWA_Support_btn"), 3)) {
            safeClick(driver, getObjectPlatform("AccountsPWA_Support_btn"));
            Reporter.log("Support button  is clicked");
            System.out.println("Support Button is clicked");
        } else {
            elementPresent(driver, getObjectPlatform("AccountsPWA_Support_btn"));
            Reporter.log("Support button is not clickable in" + driver.getCurrentUrl());
        }
        textPresent_Log(driver, "How do I cancel a flight booking?", 7);
        textPresent_Log(driver, "How do I make changes to flight reservation?", 1);
        textPresent_Log(driver, "Click here for Hotel FAQ's", 1);
        textPresent_Log(driver, "Welcome to Cleartrip Support", 1);
        if (elementVisible(driver, getObjectPlatform("AccountsPWA_SignIn_btn"), 2)) {
            safeClick(driver, getObjectPlatform("AccountsPWA_SignIn_btn"));
            Reporter.log("SignIn Button is clicked");
            System.out.println("SignIn Button is clicked");
        } else {
            elementPresent(driver, getObjectPlatform("AccountsPWA_SignIn_btn"));
            Reporter.log("SignIn button is not clickable in" + driver.getCurrentUrl());
        }
        Thread.sleep(1000);
        safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
        if (elementVisible(driver, getObjectPlatform("AccountsPWA_supportPageCancel_btn"), 3)) {
            safeClick(driver, getObjectPlatform("AccountsPWA_supportPageCancel_btn"));
            Reporter.log("Cancel button  is clicked");
            System.out.println("Cancel button  is clicked");
        } else {
            elementPresent(driver, getObjectPlatform("AccountsPWA_supportPageCancel_btn"));
            Reporter.log("Cancel button is not clickable in" + driver.getCurrentUrl());
        }
        safeClick(driver, getObjectPlatform("AccountsPWA_SignIn_btn"));
        System.out.println("SignIn button is clicked");
        Thread.sleep(1000);
        textPresent_Log(driver, "privacy policy", 2);
        textPresent_Log(driver, "terms of use", 2);
    }
    public void profilePage() throws Exception {

        textPresent(driver, "ViewProfile", 4);
        safeClick(driver, getObjectPlatform("AccountsPWA_ViewProfile_btn"));
        textPresent_Log(driver, "Profile", 2);
        textPresent_Log(driver, "Mobile Number", 2);
        textPresent_Log(driver, "Email Address", 2);
        textPresent_Log(driver, "Full Name", 2);
        textPresent_Log(driver, "Birthday", 2);
        textPresent_Log(driver, "Marital status", 2);
        textPresent_Log(driver, "Login information", 2);
        textPresent_Log(driver, "Personal information", 2);
        textPresent_Log(driver, "GSTIN details", 6);
        safeClick(driver, getObjectPlatform("AccountsPWA_AddEmail_btn"));
        if (elementVisible(driver, getObjectPlatform("AccountsPWA_EmailPopUpCross_btn"), 3)) {
            safeClick(driver, getObjectPlatform("AccountsPWA_EmailPopUpCross_btn"));
            Reporter.log("User is able to close tha Email PopUp " + driver.getCurrentUrl());
            System.out.println("Cross button clicked");
        } else {
            elementPresent(driver, getObjectPlatform("AccountsPWA_EmailPopUpCross_btn"), 3);
            Reporter.log("User is not able to close the Email PopUp" + driver.getCurrentUrl());
            System.out.println("Cross button not clicked");
        }
    }

    public void LoginClickMyTripsPage() throws Exception {

        if (elementVisible(driver, getObjectPlatform("AccountsPWA_myTrips_btn"), 60)) {
            safeClick(driver, getObjectPlatform("AccountsPWA_myTrips_btn"));
            Reporter.log("myTrips button clicked");
        } else {
            Assert.assertTrue(false);
            elementPresent(driver, getObjectPlatform("AccountsPWA_myTrips_btn"));
            Reporter.log("myTrips button is not clickable in" + driver.getCurrentUrl());
        }
        safeClick(driver, getObjectPlatform("AccountsPWA_myTripsLogin_btn"));
    }

    public void ItineraryPageAirLogin() throws Exception {
        driver.navigate().to("https://qa2new.cleartrip.com/flights/itinerary/NI6830126df6-9dfc-43d7-96af-230115185040/info?ancillaryEnabled=true&state=%5Bobject+Object%5D");
        driver.navigate().refresh();
        safeClick(driver, getObjectPlatform("AccountsPWA_itineraryLogin_btn"));
    }

    public void ItineraryPageHotelLogin() throws Exception {
        driver.navigate().to("https://qa2new.cleartrip.com/hotels/itinerary/75f4f46575-a5ec-40dc-80fb-230116095032/info");
        driver.navigate().refresh();
        //   JSClick(getObjectPlatform("AccountsPWA_itineraryHotelLogin_btn"));
        safeClick(driver, getObjectPlatform("AccountsPWA_itineraryHotelLogin_btn"));
    }
    public void OtpErrorValidation(String Page) throws Exception {

        String number = "334676";
        String s = RandomStringUtils.randomNumeric(4);
        String phoneNumber = number + s;
        System.out.println(phoneNumber);
        if (Page == "AccountsPage") {
            Reporter.log("Accounts page Signup banner OTP validations");
            reachToAccountsPage();
            safeClick(driver, getObjectPlatform("AccountsPWA_login_button"));
        }
        if (Page == "TripsPage") {
            Reporter.log("TripsPage page Signup banner OTP validations");
            LoginClickMyTripsPage();
        }
        if (Page == "SupportPage") {
            Reporter.log("SupportPage page Signup banner OTP validations");
            supportPageLogin();
        }
        if (Page == "Homepage") {
            safeClick(driver, getObjectPlatform("AccountsPWA_HomePageLogin_btn"));
            Reporter.log("HomePage Signup banner OTP validations");
        }
        Thread.sleep(2000);
        safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
        safeType(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), phoneNumber);

        for (int i = 0; i <= 3; i++) {
            String otp1 = "1";
            String otp2 = "1";
            String otp3 = "1";
            String otp4 = "1";

            if (Page == "TripsPage" || Page == "AccountsPage" || Page == "Homepage") {
                safeType(driver, getObjectPlatform("AccountsPWA_login_otp1"), otp1);
                safeType(driver, getObjectPlatform("AccountsPWA_login_otp2"), otp2);
                safeType(driver, getObjectPlatform("AccountsPWA_login_otp3"), otp3);
                safeType(driver, getObjectPlatform("AccountsPWA_login_otp4"), otp4);
                if (i < 3) {
                    System.out.println(i);
                    textPresent_Log(driver, "Please enter a valid OTP", 3);
                    textPresent_Log(driver, "Trying to Autodetect", 1);
                    Reporter.log("Please enter a valid OTP is displaying");
                    System.out.println("Please enter a valid OTP is displaying");
                }
            } else {
                safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp1"), otp1);
                safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp2"), otp2);
                safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp3"), otp3);
                safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp4"), otp4);
                if (i < 3) {
                    System.out.println(i);
                    textPresent_Log(driver, "Please enter a valid OTP", 2);
                    Reporter.log("Please enter a valid OTP is displaying");
                }
            }
        }
        Thread.sleep(3000);
        textPresent_Log(driver, "You exceeded the OTP validation limit. Please try again in 10 minutes", 2);
        System.out.println("You exceeded the OTP validation limit. Please try again in 10 minutes error message is displaying");
        Reporter.log("You exceeded the OTP validation limit. Please try again in 10 minutes error message is displaying");
    }

    public void SignOut() throws Exception {

        driver.navigate().to(accountsPageUrl);
        Thread.sleep(2000);
        if (elementVisible(driver, getObjectPlatform("AccountsPWA_signOut_btn"), 3)) {
            safeClick(driver, getObjectPlatform("AccountsPWA_signOut_btn"));
            Reporter.log("SignOut button clicked");
        } else {
            elementPresent_log(driver, getObjectPlatform("AccountsPWA_signOut_btn"), "SignOut button is not displaying in", 5);
        }
        driver.navigate().refresh();
        if (elementVisible(driver, getObjectPlatform("AccountsPWA_login_button"), 5)) {
            System.out.println("User logged out successfully");
            Reporter.log("User logged out successfully");
        } else {
            elementPresent(driver, getObjectPlatform("AccountsPWA_login_button"));
            //Assert.assertTrue(b);
            System.out.println("SignOut button is not clicked");
        }
    }
    public void changeNumberFunctionality(String Page) throws Exception {

        if (Page == "SupportPage") {
            safeClick(driver, getObjectPlatform("AccountsPWA_SupportPageChangeNumber_btn"));
        } else {
            safeClick(driver, getObjectPlatform("AccountsPWA_ChangeNumber_btn"));
        }
        if (elementVisible(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), 3)) {
            System.out.println("Change number button is clicked");
            Reporter.log("Change number button is clicked");
        } else {
            elementPresent(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), 3);
            System.out.println(" Change number button is not clickable");
            Reporter.log("Change number button is not clickable");
        }
    }
    public void editNumberFunctionality() throws Exception {

        safeClick(driver, getObjectPlatform("AccountsPWA_edit_btn"));
        if (elementVisible(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), 3)) {
            System.out.println("Edit button is clicked");
            Reporter.log("Edit button is clicked");
        } else {
            elementPresent(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), 3);
            System.out.println("Edit button is not clickable");
            Reporter.log("Edit button is not clickable");
        }
    }
    public void EmailInvalidOtpValidation() throws Exception {

        String otp1 = "1";
        String otp2 = "1";
        String otp3 = "1";
        String otp4 = "1";
        String otp5 = "1";
        String otp6 = "1";
        Thread.sleep(1000);
        safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp1"), otp1);
        safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp2"), otp2);
        safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp3"), otp3);
        safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp4"), otp4);
        safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp5"), otp5);
        safeType(driver, getObjectPlatform("AccountsPWA_EmailMerge_otp6"), otp6);

    }
    public void LoginButtonClickonHomepage() throws Exception {

        CookieButtonCLick();
        textPresent_Log(driver, "Home", 6);
        textPresent_Log(driver, "myTrips", 2);
        textPresent_Log(driver, "offer", 2);
        textPresent_Log(driver, "myAccount", 2);
        safeClick(driver, getObjectPlatform("AccountsPWA_HomePageLogin_btn"));
    }
    public void CookieButtonCLick() throws Exception {

        if (elementVisible(driver, getObjectPlatform("AccountsPWA_Cookies_btn"), 3)) {
            safeClick(driver, getObjectPlatform("AccountsPWA_Cookies_btn"));
            System.out.println("Cookie popup is closed");
            Reporter.log("Cookie popup is closed");
        } else {
            elementPresent(driver, getObjectPlatform("AccountsPWA_Cookies_btn"));
            System.out.println("Cookie popup is not displayed");
            Reporter.log("Cookie popup is not displayed" + driver.getCurrentUrl());
        }
    }
    public void NotNowButtonCLick() throws Exception {

        if (textPresent(driver, "Almost there", 6)) {
            safeClick(driver, getObjectPlatform("AccountsPWA_notNow_btn"));
            System.out.println("Not Now button is clicked in signup merge banner");
            Reporter.log("Not Now button is clicked in signup merge banner");
        } else {
          //  Assert.assertTrue(false);
            Reporter.log(" signup merge banner is not displayed" + driver.getCurrentUrl());
            System.out.println("Not Now button is not clicked in signup merge banner");
        }
    }
    public void LoginWithMobileNumber(String Type, String PhoneNo) throws Exception {

            textPresent_Log(driver, "Get OTP", 4);
            safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
            safeType(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), PhoneNo);
            textPresent_Log(driver, "Enter your OTP send to", 4);
            textPresent_Log(driver, "We have sent you an OTP", 1);
            textPresent_Log(driver, PhoneNo, 1);
            RedisHandler(driver,"SIGNIN","+91" + PhoneNo,"","");
            String otp1 = String.valueOf(otp.charAt(0));
            System.out.println(otp1);
            String otp2 = String.valueOf(otp.charAt(1));
            System.out.println(otp2);
            String otp3 = String.valueOf(otp.charAt(2));
            System.out.println(otp3);
            String otp4 = String.valueOf(otp.charAt(3));
            System.out.println(otp4);
            Thread.sleep(1000);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp1"), otp1);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp2"), otp2);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp3"), otp3);
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp4"), otp4);

        }
    public void AccountPageLoginClick() throws Exception{

        safeClick(driver, getObjectPlatform("AccountsPWA_accounts_button"));
        safeClick(driver, getObjectPlatform("AccountsPWA_login_button"));
    }
    public void SupportPageLoginClick() throws Exception{

        safeClick(driver, getObjectPlatform("AccountsPWA_accounts_button"));
        safeClick(driver, getObjectPlatform("AccountsPWA_Support_btn"));
        safeClick(driver, getObjectPlatform("AccountsPWA_SignIn_btn"));
    }
    public void EnterInvalidOTP(String Page) throws Exception{

        String number = "534676";
        String s = RandomStringUtils.randomNumeric(4);
        String phoneNumber = number + s;
        System.out.println(phoneNumber);

        if (Page == "AccountsPage") {
            AccountPageLoginClick();
        }
        if (Page == "TripsPage") {
            LoginClickMyTripsPage();
        }
        if (Page == "Homepage") {
            safeClick(driver, getObjectPlatform("AccountsPWA_HomePageLogin_btn"));
        }
        if (Page == "SupportPage") {
            SupportPageLoginClick();
        }
        safeClick(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"));
        safeType(driver, getObjectPlatform("AccountsPWA_mobileNumber_textbox"), phoneNumber);

        if (Page == "TripsPage" || Page == "AccountsPage" || Page == "HomepagePage") {
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp1"), "2");
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp2"), "2");
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp3"), "2");
            safeType(driver, getObjectPlatform("AccountsPWA_login_otp4"), "2");
        }
        else {
            safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp1"), "1");
            safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp2"), "2");
            safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp3"), "3");
            safeType(driver, getObjectPlatform("AccountsPWA_SupportPage_otp4"), "4");
        }
     Thread.sleep(3000);

    }

}
