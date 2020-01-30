package tripServices;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_MF_MFilter extends TripserviceCommon {
	Response resp;
	@Test(groups={"Regression"})
	public void mFilterCall() throws Exception{
		String url =  Service_Url("TRIPSERVICE_MF_MFILTER");
		String s=RandomStringUtils.randomAlphanumeric(3);
		String r=RandomStringUtils.randomAlphanumeric(3);
		String m=RandomStringUtils.randomNumeric(2);
		String n=RandomStringUtils.randomAlphanumeric(3);
		String Params1="[{\"IF_BlacklistedRiskScore\": 0, \"INC_HighCRRiskScore\": 0, \"Dis_BrowserRiskScore\": 0, \"Dis_DeviceRiskScore\": 0, \"package_name\": \"com.Blahblahblah.android\", \"fraud_category\": \"NOT_FRAUD\", \"locale_info\": \"en_US\", \"timestamp\": \"2019-06-05 04:46:26\", \"device_imei\": \"Default\", \"vendor_id\": \"Default\", \"campaign_id\": \"Test Campaign\", \"DF_APKRiskScore\": 0, \"IF_VPNProxyRiskScore\": 0, \"Dis_IPRiskScore\": 0, \"Dis_OSRiskScore\": 0, \"device_carrier\": \"Airtel\", \"conversion_make\": \"Default\", \"publisher_name\": \"admobs\", \"Dis_ISPRiskScore\": 0, \"device_id\": \"FD2B68B3-618F-48AB-A36A-29D3BF91E"+s+"\" ";
		String Params2=",\"INC_clickUnderRiskScore\": 0, \"IF_DuplicateIPRiskScore\": 0, \"app_version\": \"18.3.0\", \"user_id\": \"Default\", \"sub_publisher_id\": \"25572_28132_24002\", \"DF_DuplicateUserRiskScore\": 0, \"click_id\": \"103899_842b34a321a674cdbacf0b6b32ea45471520733947-20181103073547-1457-20042_23182_27552\", \"ip_address\": \"Default\", \"CF_ClickSpamRiskSCore\": 0, \"DF_IncorrectTelcoRiskScore\": 0, \"DF_FakeDeviceRiskScore\": 0, \"CF_FakeClickRiskScore\": 0, \"os_version\": \"4.4.2\", \"CF_FakeAttributionRiskScore\": 0, \"conversion_model\": \"Default\", \"device_gaid\": \"036CD1EA-9DD7-43D4-BECF-436769CC7"+r+"\" ";
		String Params3=",\"record_id\": 4336183"+m;
		String Params4=",\"country\": \"PK\", \"publisher_id\": \"Default\", \"DF_IncorrectRegionRiskScore\": 0, \"transaction_id\": \"bbf46ae767767d41bd4474dfbae6d1060068a"+n+"\"}]";
		String Params=Params1+Params2+Params3+Params4;
		Reporter.log(Params);
		resp = RestAssured.given().
				when().
				log().all().
				body(Params).
				headers(headersForMFilter()).
				post(url);
		ResponseBody body= resp.getBody();
		String bodyAsString = body.asString();
		   if(resp.statusCode()==200){
		    Reporter.log("Status code " + resp.statusCode());
			Assert.assertEquals(bodyAsString.contains("OK"), true ,"Response boday contains OK");
			
		}else{
			Reporter.log("Status code " + resp.statusCode());
			assertTrue(false);
		}
	
	}
	public HashMap<String, Object> headersForMFilter(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Basic Mjk1MGQzNTYwMWNiOGRiNDVhYjg5NjQ0MzUxNmQ1ZDY6YTA1ODZhNDMwZTU5NGUxNjJjOGUwMzJiNmU5YzQ2OGY=");
		return headers;
	}
	

}

