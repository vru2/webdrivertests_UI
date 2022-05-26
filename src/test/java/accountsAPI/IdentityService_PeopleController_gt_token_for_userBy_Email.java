package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class IdentityService_PeopleController_gt_token_for_userBy_Email extends AccountsCommon_API
{
	@Test
	public void IdentityService_PeopleController_gt_token_for_userBy_Email() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("IdentityService_PeopleController_gt_token_for_userBy_Email", "");
		validation( resp, "IdentityService_PeopleController_gt_token_for_userBy_Email", "");

		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());


	}
}
