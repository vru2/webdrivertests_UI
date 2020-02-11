package emailprod;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GeneratePDF extends EmailCommon {

	@Test(groups={"Regression"})		
	public void generatePDF() throws IOException {
		Response resp;
		String url ="http://emailapi.cltp.com:9001/email/getpdf";
		resp=EmailgeneratePDF(params4,headersForEmailgeneratePKPasscall(),url);
		validationPDF(resp);
	}
}
