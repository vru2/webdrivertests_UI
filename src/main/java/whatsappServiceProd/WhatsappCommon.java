package whatsappServiceProd;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;

import domains.PlatformCommonUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class WhatsappCommon extends PlatformCommonUtil {
	Response resp;
	Response response;
	

	public HashMap<String, Object> headersForWhatsAppCheckContacts() {
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("x-ct-api-key", "5e7f24997504ef33561908c0f8ba70b6");
		return headers;

	}
	
	public HashMap<String, Object> headersForWhatsappUserMessage() {
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("x-ct-api-key", "5e7f24997504ef33561908c0f8ba70b6");

		return headers;

	}
	
	public HashMap<String, Object> headersForWhatsappUserMessageEgypt(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("x-ct-auth-email","project@flyin.com");
		return headers;
		
	}
	
	public HashMap<String, Object> headersForWhatsappUserMessageSaudi(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("x-ct-auth-email","marketing@flyin.com");
		return headers;
		
	}
	
	public Response paramsForwhatsappservice(HashMap<String, Object> headers, String params, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(url);
			        return resp;
	   }
	
	public Response paramsForwhatsappmessage(HashMap<String, Object> headers, String params1, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params1).
					headers(headers).
					post(url);
			        return resp;
	   }
	
	public Response paramsForwhatsappservicewithattachment(HashMap<String, Object> headers, String params1, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params2).
					headers(headers).
					post(url);
			        return resp;
	   }
	
	public  Response paramsForwhatsappdeliveryfeedback(HashMap<String, Object> headers, String url){
		resp = RestAssured.given().
				when().
				log().all().
				headers(headers).
				get(url);
		return resp;
	}
	
	public Response paramsForwhatsappOptin(HashMap<String, Object> headers, String params1, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params3).
					headers(headers).
					post(url);
			        return resp;
	   }
	
	public Response paramsForwhatsappOptinStatus(HashMap<String, Object> headers, String params1, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params4).
					headers(headers).
					post(url);
			        return resp;
	   }
	
	public Response paramsForwhatsappwebhook(HashMap<String, Object> headers, String params1, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params5).
					headers(headers).
					post(url);
			        return resp;
	   }
	
	public Response paramsForwhatsappmultilanguage(HashMap<String, Object> headers, String params6, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params6).
					headers(headers).
					post(url);
			        return resp;
	   }
	
	public Response paramsForwhatsappservicewithattachmentEgypt(HashMap<String, Object> headers, String params, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(url);
			        return resp;
	   }
	
	public Response paramsForwhatsappservicewithattachmentSaudi(HashMap<String, Object> headers, String params, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(url);
			        return resp;
	   }
	
	public Response paramsForwhatsappmessageSaudi(HashMap<String, Object> headers, String params, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(url);
			        return resp;
	   }

	public Response paramsForwhatsappmessageEgypt(HashMap<String, Object> headers, String params, String url){
	   	 resp = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(url);
			        return resp;
	   }
	public Response validation(Response resp){
    	if(resp.statusCode()==200){
			Reporter.log("Test case passed");
			Reporter.log(resp.asString());
			Reporter.log("Status code == "+ resp.statusCode());
            ResponseBody body= resp.getBody();
            String bodyAsString = body.asString();
            JsonPath jsonPathEvaluator = resp.jsonPath();
            String Status=jsonPathEvaluator.get("status");
            String Wa_Id=jsonPathEvaluator.get("wa_id");
            Assert.assertTrue(bodyAsString.contains("wa_id"));
            Assert.assertTrue(bodyAsString.contains("status"));
            assertTrue(true);
 }
		else
		{
			
			Reporter.log(resp.asString());
			assertTrue(false);
		}
		return resp;
	
	}
	
	public Response validateMessage(Response resp){
    	
    	if(resp.statusCode()==202){
			Reporter.log("Test case passed");
			Reporter.log(resp.asString());
			JsonPath jsonPathEvaluator = resp.jsonPath();
	        String Status=jsonPathEvaluator.get("status");
	        String MessageId=jsonPathEvaluator.get("messageId");
			Reporter.log("Status code == "+ resp.statusCode());
			Reporter.log("Message Id == "+MessageId);
            ResponseBody body= resp.getBody();
            String bodyAsString = body.asString();
            System.out.println(bodyAsString);
            Assert.assertTrue(bodyAsString.contains("messageId"));
            Assert.assertNotNull(MessageId);
            assertTrue(true);
 }
		else
		{
			
			Reporter.log(resp.asString());
			assertTrue(false);
		}
       
		return resp;
	
	}
	
	public Response validateDeliveryFeedback(Response response){
			if(response.statusCode()==200){
				assertTrue(true);
				Reporter.log("Test case passed");
				System.out.println(response.asString());
				Reporter.log("Status code ==  " + response.statusCode());
			}
			else{
				assertTrue(false);
				System.err.println("Script failed");
			}
		return resp;
		
	}
	
	public Response validateOptinFlow(Response resp){
		JsonPath jsonPathEvaluator = resp.jsonPath();
        String RequestId=jsonPathEvaluator.getString("requestId");
        String IsOptedin=jsonPathEvaluator.getString("isOptedin");
		if(resp.statusCode()==200){
			assertTrue(true);
			Reporter.log("Test case passed");
			Reporter.log(resp.asString());
			Reporter.log("Status code == " + resp.statusCode());
			Reporter.log("requestId == " + RequestId);
			Reporter.log("isOptedin == " + IsOptedin);
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("requestId"), true ,"Response boday contains requestId");

		}else{
			assertTrue(false);
			System.err.println("Script failed");
		}
		Assert.assertNotNull(RequestId);
		Assert.assertNotNull(IsOptedin);
		return resp;
		
		
	}
	
	public Response validateWebhook(Response resp){
		JsonPath jsonPathEvaluator = resp.jsonPath();
        String messageId=jsonPathEvaluator.getString("messageId");
		if(resp.statusCode()==200){
			assertTrue(true);
			Reporter.log("Test case passed");
			Reporter.log(resp.asString());
			Reporter.log("Status code == " + resp.statusCode());
			Reporter.log("messageId == " +messageId);
			
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("messageId"), true ,"Response boday contains messageId");
			
		}else{
			assertTrue(false);
			System.err.println("Script failed");
		}
		Assert.assertNotNull(messageId);
			
		return resp;
		
	}
	
	String params="{ "+"\"mobileNumbers\":["+"\"96639\",\"9663949690\"]"+"}";
	String params1="{\"ccid\":\"Q1807300315\",\"ctUserId\":41644626,\"messageType\":\"hsm\",\"mobileNumber\":\"9663949690\",\"hsmRequestPayload\":{\"firstName\":\"Lakshmi\",\"hsmType\":\"flight\",\"hsmSubType\":\"onward\",\"tripId\":\"Q1807300315\",\"flightDetails\":[{\"fromCity\":\"BBI\",\"toCity\":\"BBI\",\"pnrNum\":\"TDT8GX\",\"dateOfJourney\":\"Thu, 21 FEB 2019\",\"departureTime\":\"10:40\",\"arrivalTime\":\"13:00\",\"flightName\":\"SG\"}],\"tripStart\":\"BBI\",\"tripEnd\":\"BBI\"}}";
	String params2 = " {" + "\"attachments\": [" + " {" +" \"documentName\": \"Ticket\"," +" \"documentPath\": \"/usr/local/storage/trip_attachments/CleartripHotelBookingPolicy.pdf\"" +	"}" +" ]," +" \"ccid\": \"Q12345\"," +" \"ctUserId\": \"12345\"," +	" \"message\": \"Automation whatsapp message with attachments\"," +	" \"messageType\": \"document/pdf\"," + " \"mobileNumber\": \"9663949690\"" +"}";
    String params3="{ "+"\"ctUserId\": \"string\", "+"\"mobileNumber\": \"9663949690\","+"\"whatsappUsername\":\"919663949690\","+"\"isValidWhatsappNumber\": \"true\","+"\"requestType\": \"optin\","+"\"sourcePage\": \"bookingpage\","+"\"sourceType\": \"bookflow\","+"\"Domain\":\"cleartrip.com\","+"\"countryCode\":\"91\""+"}";
    String params4="{ "+"\"mobileNumber\":\"9663949690\","+"\"domain\":\"cleartrip.com\" "+"}";
    String params5= "{" + " \"messages\": [" +"{"+"  " + " " + "  \"from\": \"9663949690\"," +"" +" " + "\"text\":{" + " \"body\": \"string\" " +	" }" +	" " +"  }" +" ]" +"}";
    String params6="{ "+"\"ctUserId\": \"string\","+"\"ccid\":\"111151\","+"\"messageType\": \"hsm\","+"\"mobileNumber\": \"+919663949690\","+"\"hsmRequestPayload\":{ "+"\"language\":\"en_US\", "+"\"hsmTemplateName\":\"booking_confirmation_local\", \"paramMap\":{\"1\":\"Lakshmi\",\"2\":\"BOM\",\"3\":\"BLR\",\"4\":\"2019-04-16\",\"5\":\"Q1903180150\",\"6\":\"Banglore\",\"7\":\"Baggage\",\"8\":\"Indigo\",\"9\":\"Flights\",\"10\":\"Support\"}"+" } "+"}";
    String hsmtemplate="{\"ccid\":\"string\",\"ctUserId\":\"string\",\"messageType\":\"hsm\",\"mobileNumber\":\"+919663949690\",\"hsmRequestPayload\":{\"language\":\"EN_US\",\"hsmTemplateName\":\"flyin_flight_onward_v2\",\"paramMap\":{\"1\":\"Test prod\",\"2\":\"value2 Test\",\"3\":\"value3 Test\",\"4\":\"value4 Test\",\"5\":\"value5 Test\",\"6\":\"value6 Test\",\"7\":\"value7 Test\",\"8\":\"value8 Test\",\"9\":\"value9 Test\",\"10\":\"value10\"}}}";
}