package email;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GeneratePDF extends EmailCommon {

	@Test(groups={"Regression"})		
	public void generatePDF() throws IOException {
		Response resp;
		String url = "http://172.17.51.86:8001/email/getpdf";
		resp=EmailgeneratePDF(params4,headersForEmailgeneratePKPasscall(),url);
		System.out.println(resp.asString());
		validationPDF(resp);
		
	}
}
