package test.java.accountsUI;

import org.testng.annotations.Test;

public class FetchOTP extends Accounts_UI_Common{
    @Test
    public void signup_mobile_oly() throws Exception {
        // SIGNIN OTP
      RedisHandler(driver,"SIGNIN","+913663636365","","");
        // SIGNUP_MERGE OTP
       RedisHandler(driver,"SIGNUP_MERGE","","merge98@gmail.com","");
        //  DELETE_ACCOUNT OTP
     RedisHandler(driver,"","","merge100@gmail.com","UPDATE_EMAIL");
        // UPDATE_MOBILE
        // RedisHandler(driver,"UPDATE_MOBILE","+914356373737","","");
        //5457676759
    }
}