package whatsappServiceProd;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WhatsappDeliveryFeedback extends WhatsappCommon {
	@Test(groups={"Regression"})
	public void message() throws IOException, InterruptedException {
		Response resp;
		String url = Service_Url("WHATSAPP_MESSAGE");
        resp=paramsForwhatsappmessage(headersForWhatsappUserMessage(),params1,url);
        Thread.sleep(9000);
        validateMessage(resp);
        JsonPath jsonPathEvaluator = resp.jsonPath();
        String messageId=jsonPathEvaluator.get("messageId");
        Response resp1;
    		String url4feedback =("http://whatsappapi.cltp.com:9001/whatsapp/feedback?id="+messageId);
    		// Service_Url("WHATSAPP_DELIVERY_FEEDBACK"+messageId); ("http://whatsappapi.cltp.com:9001/r3/whatsapp/feedback?id="+messageId);
    	    resp1=paramsForwhatsappdeliveryfeedback(headersForWhatsappUserMessage(),url4feedback);
    	    validateDeliveryFeedback(resp1);
    	}
	}
