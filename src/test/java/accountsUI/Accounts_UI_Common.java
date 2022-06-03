package test.java.  accountsUI;

import org.openqa.selenium.remote.RemoteWebDriver;

import test.java.common.WrapperMethod;
import redis.clients.jedis.Jedis;

public class Accounts_UI_Common extends WrapperMethod
{
  public String otp;
  public RemoteWebDriver driver;
  public void RedisHandler(RemoteWebDriver driver) {
	    Jedis jedis= new Jedis("http://172.17.64.102:6379");
	    System.out.println("Connection Successful");
	    System.out.println("The server is running"+jedis.ping());
	    otp= jedis.get("ACCOUNTS_SERVICE_MOBILE_LOGIN_KEY_+919663949690SIGNIN");
	    jedis.close();
	    	    }
}
