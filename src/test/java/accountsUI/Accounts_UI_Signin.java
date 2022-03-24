package accountsUI;

import org.testng.annotations.Test;

public class Accounts_UI_Signin extends Accounts_UI_Common{
	
	@Test
	public void getotp()
	{
		RedisHandler(driver);
		System.out.println(otp);
	}

}
