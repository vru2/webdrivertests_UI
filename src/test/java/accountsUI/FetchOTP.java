package test.java.accountsUI;

import org.testng.annotations.Test;

public class FetchOTP extends Accounts_UI_Common{
    @Test
    public void signup_mobile_oly() throws Exception {
        // SIGNIN OTP
        RedisHandler(driver,"SIGNIN","+919380489646","","");
        // SIGNUP_MERGE OTP
        //RedisHandler(driver,"SIGNUP_MERGE","","@gmail.com","");
        //  DELETE_ACCOUNT OTP
        //RedisHandler(driver,"MOBILE_EMAIL","+912345679877","","DELETE_ACCOUNT");
        // UPDATE_MOBILE
        // RedisHandler(driver,"UPDATE_MOBILE","+914356373737","","");
    }
}