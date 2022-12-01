package test.java.  email;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GeneratePDF extends EmailCommon {

	@Test(groups={"Regression"})		
	public void generatePDF() throws IOException {
		Response resp;
		String url = "http://email-api.cltp.com:9001/email/getpdf";
		resp=EmailgeneratePDF(params4,headersForEmailgeneratePKPasscall(),url);
		System.out.println(resp.asString());
		validationPDF(resp);
		
	}
}
