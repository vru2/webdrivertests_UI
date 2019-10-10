package com.cleartrip.local.camp.others;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class GstInvoiceReport extends CampActivities {

	String[] headers = { "Commission", "PG Fees", "IGST", "Total GST", "Commission Invoice", "Total Commission", "CGST",
			"SGST" };
	HashMap<String, Float> invoiceVal = new HashMap<>();
	HashMap<String, Float> pdfReportVal = new HashMap<>();

	@Parameters({ "user", "domain" })
	@Test
	public void vGstInvoiceReport(String user, String domain) {
		float in = 0;

		try {
			for (String inp : headers) {
				invoiceVal.put(inp, in);
				pdfReportVal.put(inp, in);
			}

			campActivities_SignIN(driver, user);
			driver.get(baseUrl + "/gst");
			vGstReport(invoiceVal);
			readPdfValues(pdfReportVal);
			vGstReportInvoice(invoiceVal, pdfReportVal);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
