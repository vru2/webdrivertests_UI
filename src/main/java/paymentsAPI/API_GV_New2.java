// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;
import java.util.Random;

import org.json.JSONException;
import org.testng.annotations.Test;

public class API_GV_New2 extends API_PaymentCommon1
{
	@Test
	public void paymentGV_API() throws IOException, JSONException{
		
		String ran_Char = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder ran_Char1 = new StringBuilder();
        Random rnd = new Random();
        while (ran_Char1.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * ran_Char.length());
            ran_Char1.append(ran_Char.charAt(index));
        }
        String ranStr = ran_Char1.toString();
        System.out.println(ranStr+"Q@1");
		}
}