package email;



import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;

import domains.PlatformCommonUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class EmailCommon extends PlatformCommonUtil {
	Response resp;
	public HashMap<String, Object> headersForEmailpostcall(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("x-ct-api-key", "5e7f24997504ef33561908c0f8ba70b6");
		return headers;

	}
	
	public HashMap<String, Object> headersForEmailgeneratePKPasscall(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Cache-Control"," no-cache");
		return headers;

	}
	
	public Response EmailPostAPI(String params, HashMap<String, Object> headers, String url){
		
		resp = RestAssured.given().
				when().
				log().all().
				body(params).
				headers(headers).
				post(url);
		return resp;
	}
	
public Response EmailAttachment(String params1, HashMap<String, Object> headers, String url){
		
		resp = RestAssured.given().
				when().
				log().all().
				body(params1).
				headers(headers).
				post(url);
		return resp;
	}

public Response EmailTemplateTrue(String params2, HashMap<String, Object> headers, String url){
	
	resp = RestAssured.given().
			when().
			log().all().
			body(params2).
			headers(headers).
			post(url);
	return resp;
}

public Response Emailspamscore(String params, HashMap<String, Object> headers, String url){
	
	resp = RestAssured.given().
			when().
			log().all().
			body(params).
			headers(headers).
			post(url);
	return resp;
}

public Response EmailgeneratePKPass(String params3, HashMap<String, Object> headers, String url){
	
	resp = RestAssured.given().
			when().
			log().all().
			body(params3).
			headers(headers).
			post(url);
	return resp;
}

public Response EmailgeneratePDF(String params4, HashMap<String, Object> headers, String url){
	
	resp = RestAssured.given().
			when().
			log().all().
			body(params4).
			headers(headers).
			post(url);
	return resp;
}
	
	public Response validation(Response resp){
		JsonPath jsonPathEvaluator = resp.jsonPath();
        String Status=jsonPathEvaluator.get("Status");
        String Id=jsonPathEvaluator.get("id");
        if(resp.statusCode()==202){
		assertTrue(true);
		Reporter.log("Test case passed");
		Reporter.log(resp.asString());
		Reporter.log("Status code == " + resp.statusCode());
		ResponseBody body=resp.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("id"), true ,"Response boday contains  id");

	}
	else
	{
		
		assertTrue(false);
		Reporter.log(resp.asString());
	}

     return resp;
}
	public Response validationSpamScore(Response resp){
		if(resp.statusCode()==200)
		{
			assertTrue(true);
			Reporter.log("Test case passed");
			Reporter.log(resp.asString());
			Reporter.log(resp.asString());
			Reporter.log("Status code = " + resp.statusCode());
			Reporter.log("Status code = " + resp.statusCode());
//			ResponseBody body= resp.getBody();
//			String bodyAsString = body.asString();
			//Assert.assertEquals(bodyAsString.contains(resp.statusCode()), true ,"Response boday contains status");
			//Reporter.log(resp.getBody());
		} else {
			assertTrue(false);
			Reporter.log("Test script got failed!!");
		}
		return resp;
		
	}
	
	public Response validationPKPass(Response resp){
		JsonPath jsonPathEvaluator = resp.jsonPath();
        String Status=jsonPathEvaluator.get("status");
        String Id=jsonPathEvaluator.get("id");
        String pkPassFileName=jsonPathEvaluator.get("pkPassFileName");
        if(resp.statusCode()==201){
		assertTrue(true);
		Reporter.log("Test case passed");
		Reporter.log(resp.asString());
		Reporter.log("Status code == " + resp.statusCode());
		ResponseBody body=resp.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("status"), true ,"Response boday contains  status");
		Assert.assertEquals(bodyAsString.contains("id"), true ,"Response boday contains  id");
		Assert.assertEquals(bodyAsString.contains("pkPassFileName"), true ,"Response boday contains  pkPassFileName");
	    Reporter.log(Id);
	    Reporter.log(Status);
	    Reporter.log(pkPassFileName);
        }
	else
	{
		
		assertTrue(false);
		System.err.println("Script failed");
	}

     return resp;
}
	

	public Response validationPDF(Response resp){
		JsonPath jsonPathEvaluator = resp.jsonPath();
        String Status=jsonPathEvaluator.get("status");
        String Id=jsonPathEvaluator.get("id");
        String pdfFileName=jsonPathEvaluator.get("pdfFileName");
        if(resp.statusCode()==200){
		assertTrue(true);
		Reporter.log("Test case passed");
		Reporter.log(resp.asString());
		Reporter.log("Status code == " + resp.statusCode());
		ResponseBody body=resp.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("status"), true ,"Response boday contains  status");
		Assert.assertEquals(bodyAsString.contains("id"), true ,"Response boday contains  id");
		Assert.assertEquals(bodyAsString.contains("pdfFileName"), true ,"Response boday contains  PDFFileName");
	    Reporter.log(Id);
	    Reporter.log(Status);
	    Reporter.log(pdfFileName);
        }
	else
	{
		
		assertTrue(false);
		System.err.println("Script failed");
	}

     return resp;
}

	
String params  = "{" + "\"from\": \"varalakshmi.venkateshaiah@cleartrip.com\"," + "\"to\": [\"varalakshmi.venkateshaiah@cleartrip.com\"]," + "\"cc\": [\"varalakshmi.venkateshaiah@cleartrip.com\"]," +"\"bcc\": [\"varalakshmi.venkateshaiah@cleartrip.com\"]," +"\"subject\":\"Test Subject\"," +	"\"mailContent\": \"Test automation message from script\"," +"\"useTemplate\" : \"false\"," + "\"category\" : \"test 2\"," +"\"userName\" : \"lik\"" +"}";

String params1 ="{\"from\": \"varalakshmi.venkateshaiah@cleartrip.com\",\"to\": [\"varalakshmi.venkateshaiah@cleartrip.com\"],\"cc\": [\"varalakshmi.venkateshaiah@cleartrip.com\"],\"bcc\": [\"varalakshmi.venkateshaiah@cleartrip.com\"],\"subject\": \"new test\",\"mailContent\": \"Test Email message in QA 79\",\"useTemplate\": \"false\",\"category\": \"tester template\",\"userName\": \"lik\", \"attachment\": [\"platform-communication-for-test//usr/local/storage/platform_email/pdf-attachments//08102020/c881ab51-1671-43bb-b92b-f39526d9fbeb/farealertpdftestingqa1.pdf\"]}";

String params2  = "{" + "\"from\": \"varalakshmi.venkateshaiah@cleartrip.com\"," + "\"to\": [\"varalakshmi.venkateshaiah@cleartrip.com\"]," + "\"cc\": [\"varalakshmi.venkateshaiah@cleartrip.com\"]," +"\"bcc\": [\"varalakshmi.venkateshaiah@cleartrip.com\"]," +"\"subject\":\"Test Subject\"," +	"\"mailContent\": \"Test automation message from script\"," +"\"useTemplate\" : \"true\"," + "\"category\" : \"test\"," +"\"userName\" : \"lik\"" +"}";

String params3  = "{\"name\":\"final-test-2\",\"pass\":\"{\\\"formatVersion\\\":1,\\\"serialNumber\\\":\\\"42248626\\\",\\\"passTypeIdentifier\\\":\\\"pass.cleartrip.ticket\\\",\\\"description\\\":\\\"Hotel Voucher\\\",\\\"teamIdentifier\\\":\\\"466YHJ5Q96\\\",\\\"organizationName\\\":\\\"Cleartrip\\\",\\\"logoText\\\":\\\" \\\",\\\"foregroundColor\\\":\\\"rgb(0,0,0)\\\",\\\"backgroundColor\\\":\\\"rgb(255,204,0)\\\",\\\"locations\\\":[{\\\"latitude\\\":8.390117,\\\"longitude\\\":76.97607,\\\"relevantText\\\":\\\"Looks like your hotel is near by\\\"}],\\\"generic\\\":{\\\"headerFields\\\":[{\\\"key\\\":\\\"cityName\\\",\\\"label\\\":\\\"Stay in Kovalam\\\",\\\"value\\\":\\\"28 Jan 2016\\\"}],\\\"primaryFields\\\":[{\\\"key\\\":\\\"name\\\",\\\"label\\\":\\\"2 Rooms 2 Nights\\\",\\\"value\\\":\\\"Turtle on The Beach\\\",\\\"textAlignment\\\":\\\"PKTextAlignmentNatural\\\"}],\\\"secondaryFields\\\":[{\\\"key\\\":\\\"checkin\\\",\\\"label\\\":\\\"Check-in\\\",\\\"value\\\":\\\"Thu, 28 Jan 2016\\\"},{\\\"key\\\":\\\"checkout\\\",\\\"label\\\":\\\"Check-out\\\",\\\"value\\\":\\\"Sat, 30 Jan 2016\\\",\\\"textAlignment\\\":\\\"PKTextAlignmentRight\\\"}],\\\"auxiliaryFields\\\":[{\\\"key\\\":\\\"guest\\\",\\\"label\\\":\\\"Guest\\\",\\\"value\\\":\\\"Chethan Purushotham Rao\\\"},{\\\"key\\\":\\\"voucher\\\",\\\"label\\\":\\\"Voucher #\\\",\\\"value\\\":\\\"CHMM-7252418\\\",\\\"textAlignment\\\":\\\"PKTextAlignmentRight\\\"}],\\\"backFields\\\":[{\\\"key\\\":\\\"address\\\",\\\"label\\\":\\\"Address\\\",\\\"value\\\":\\\"VPI\\/439,Kovalam Beach, Kovalam, 695 527\\\"},{\\\"key\\\":\\\"contact_number\\\",\\\"label\\\":\\\"Hotel contact\\\",\\\"value\\\":\\\"914712561014\\\"},{\\\"key\\\":\\\"map\\\",\\\"label\\\":\\\"Maps and directions\\\",\\\"value\\\":\\\"http:\\/\\/maps.google.com\\/maps?f=q&hl=en&q=Turtle+on+The+Beach&sll=8.390117%2C76.97607\\\"},{\\\"key\\\":\\\"trip_id\\\",\\\"label\\\":\\\"Trip ID\\\",\\\"value\\\":\\\"16012127484\\\"},{\\\"key\\\":\\\"ct_note\\\",\\\"label\\\":\\\"Thank you for booking with Cleartrip\\\",\\\"value\\\":\\\"Support: +91 9595333333 (Std\\/Local charges apply)\\\"}]},\\\"associatedStoreIdentifiers\\\":[531324961],\\\"relevantDate\\\":\\\"2016-01-28T02:30:00Z\\\",\\\"suppressStripShine\\\":true,\\\"sharingProhibited\\\":false,\\\"abc\\\":false}\",\"ccid\":\"Q1233\",\"thumbnail\":false,\"thumbnailUrls\":{\"urlThumbnail\":\"https://qa2.cltpstatic.com/camp/images/ai/000/811/542/811542/published/tn/canoe.jpg\",\"urlThumbnail2x\":\"https://qa2.cltpstatic.com/camp/images/ai/000/811/542/811542/published/tn/canoe.jpg\"},\"bucketName\": \"platform-communication-for-test\"}";

String params4 ="{\"templateName\" : \"fare_alert\",\"pdfName\" : \"farealertpdftestingqa1\",\"templateContent\" : \"{\\\"from\\\":\\\"BLR\\\",\\\"to\\\":\\\"CHD\\\",\\\"depart_date\\\":\\\"28\\/06\\/2020\\\",\\\"domain\\\":\\\"www.cleartrip.com\\\",\\\"subscriptionId\\\":\\\"5b058621d6802c3459163d00\\\"}\",\"bucketName\": \"platform-communication-for-test\"}";
}

