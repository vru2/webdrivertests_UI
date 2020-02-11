package email;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GeneratePDF extends EmailCommon {

	@Test(groups={"Regression"})		
	public void generatePDF() throws IOException {
		Response resp;
		String url = Service_Url("EMAIL_GENERATEPDF");
		resp=EmailgeneratePDF(params4,headersForEmailgeneratePKPasscall(),url);
		validationPDF(resp);
	}
}
