// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Smiles extends API_PaymentCommon1
{
	@Test
	public void SmilesAPI() throws IOException, JSONException{
		Response resp ;		
		resp = rearch_SmilesAuth("","");	

/*PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
authScheme.setUserName("admin");
authScheme.setPassword("admin");
RestAssured.authentication = authScheme;*/
		
		validation("CC", resp);
		}
}