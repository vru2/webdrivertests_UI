package testScriptsExternalAPI;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonServices.APIUtils;
import dataServices.APIDataProvider;
import domainServices.APIServices;

public class APISearch extends APIServices {
    HashMap<String, String> headers = APIUtils.getExtAPIHeaders(common.value("APIKey"), "API");
    String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
    String returndate1 = getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));

    /*
     * @BeforeMethod public void beforeMethod() { timer(); }
     */
    /*

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "AIRLINE_CODE_INVALID")
    public void AIRLINE_CODE_INVALID_362(String from, String to, String depart, String carrier, String aCount, String cCount)
            throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
                + "&cabin-type=Economy", headers, 200);
        Reporter.log(APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
                + "&cabin-type=Economy", headers, 200).toString());
        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 400) {
            assertEquals(hitStatus, 400);
            assertTrue(text.contains(" Invalid carrier code: AIX"));
            Reporter.log("HTTP Status code :" + hitStatus + " Message :  Invalid carrier code: AIX");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }
    */
    //
	/*@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_AIRPORT_CODE_INVALID_D")
	public void Api_AIRPORT_CODE_INVALID_D_363(String from, String to, String depart, String carrier, String aCount, String cCount)
			throws Exception {
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
				+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
				+ "&cabin-type=Economy", headers, 200);

		String text = APIUtils.returnResponseAsString(response);
		int hitStatus = response.getStatusLine().getStatusCode();// deepa needs to get back
		if (hitStatus == 200) {
			assertEquals(hitStatus, 200);
			assertTrue(text.contains("BBB is not a valid IATA destination."));
			Reporter.log("HTTP Status code :" + hitStatus + " Message : BBB is not a valid IATA destination.");
		} else {
			Reporter.log("HTTP Status code :" + hitStatus);
			assertEquals(hitStatus, 400);
		}
	}
*/
	/*@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_AIRPORT_CODE_INVALID_O")
	public void Api_AIRPORT_CODE_INVALID_O_364(String from, String to, String depart, String carrier, String aCount, String cCount)
			throws Exception {
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
				+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
				+ "&cabin-type=Economy", headers, 200);

		String text = APIUtils.returnResponseAsString(response);
		int hitStatus = response.getStatusLine().getStatusCode();// deepa needs to get back
		if (hitStatus == 200) {
			assertEquals(hitStatus, 200);
			assertTrue(text.contains("BBB is not a valid IATA destination."));
			Reporter.log("HTTP Status code :" + hitStatus + " Message : BBB is not a valid IATA Origin.");
		} else {
			Reporter.log("HTTP Status code :" + hitStatus);
			assertEquals(hitStatus, 400);
		}
	}
*/
	/*
    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_CABIN_TYPE_INVALID")
    public void Api_CABIN_TYPE_INVALID_365(String from, String to, String depart, String carrier, String aCount, String cCount)
            throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                        + onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier + "&&cabin-type=X",
                headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 400) {
            assertEquals(hitStatus, 400);
            assertTrue(text.contains("Invalid cabin-type X"));
            Reporter.log("HTTP Status code :" + hitStatus + " Message : Invalid cabin-type X");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }
    */

	/*@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_DEPART_DATE_INVALID_EXPIRED")
	public void Api_DEPART_DATE_INVALID_EXPIRED_366(String from, String to, String depart, String carrier, String aCount,
			String cCount) throws Exception {
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to
				+ "&depart-date=05-07-2016&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
				+ "&&cabin-type=Economy", headers, 200);

		String text = APIUtils.returnResponseAsString(response);
		int hitStatus = response.getStatusLine().getStatusCode();
		if (hitStatus == 400) {
			assertEquals(hitStatus, 400);
			assertTrue(text.contains("Not a valid Date"));
			Reporter.log("HTTP Status code :" + hitStatus + "Not a valid Date");
		} else {
			Reporter.log("HTTP Status code :" + hitStatus);
			assertEquals(hitStatus, 400);
		}
	}*/
    /*
    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_DEPARTDATE_INVALID_300")
    public void Api_DEPARTDATE_INVALID_300_367(String from, String to, String depart, String carrier, String aCount, String cCount)
            throws Exception {
        HttpResponse response = APIUtils.getApi(
                getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date=" + depart + "&adults=" + aCount
                        + "&children=" + cCount + "&infants=0&carrier=" + carrier + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 200) {
            assertEquals(hitStatus, 200);
            assertTrue(text.contains("Depart-date far ahead in future,should be less than 1 year from todays date."));
            Reporter.log("HTTP Status code :" + hitStatus
                    + "Depart-date far ahead in future,should be less than 1 year from todays date.");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_DEPARTURE_AND_ARRIVAL_SAME")
    public void Api_DEPARTURE_AND_ARRIVAL_SAME_368(String from, String to, String depart, String carrier, String aCount,
                                                   String cCount) throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
                + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 400) {
            assertEquals(hitStatus, 400);
            assertTrue(text.contains("Origin and Destination should be different."));
            Reporter.log("HTTP Status code :" + hitStatus + "Origin and Destination should be different.");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_DEPARTURE_DATE_FORMAT_INVALID")
    public void Api_DEPARTURE_DATE_FORMAT_INVALID_369(String from, String to, String depart, String carrier, String aCount,
                                                      String cCount) throws Exception {
        HttpResponse response = APIUtils.getApi(
                getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date=" + depart + "&adults=" + aCount
                        + "&children=" + cCount + "&infants=0&carrier=" + carrier + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 400) {
            assertEquals(hitStatus, 400);
            assertTrue(text.contains("Not a valid Date"));// Invalid depart date format - Please provide all dates in the format
            // YYYY-MM-DD ONLY.
            Reporter.log("HTTP Status code :" + hitStatus
                    + "Invalid depart date format - Please provide all dates in the format YYYY-MM-DD ONLY.");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_DESTIN_Empty")
    public void Api_DESTIN_Empty_370(String from, String to, String depart, String carrier, String aCount, String cCount)
            throws Exception {
        HttpResponse response = APIUtils.getApi(
                getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date=" + depart + "&adults=" + aCount
                        + "&children=" + cCount + "&infants=0&carrier=" + carrier + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 400) {
            assertEquals(hitStatus, 400);
            assertTrue(text.contains("Destination is a required field."));
            Reporter.log("HTTP Status code :" + hitStatus + "Destination is a required field.");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }
    */

	/*@Test(groups = "Regression")
	public void OW_FromToEmpty_9081() throws Exception {
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=&to=&depart-date=" + onwarddate
				+ "&adults=1&children=0&infants=0&carrier=&cabin-type=Economy", headers, 200);

		String text = APIUtils.returnResponseAsString(response);
		int hitStatus = response.getStatusLine().getStatusCode();
		if (hitStatus == 400) {
			assertEquals(hitStatus, 400);
			assertTrue(text.contains("Origin is a required field."));
			Reporter.log("HTTP Status code :" + hitStatus + "Origin is a required field.");
		} else {
			Reporter.log("HTTP Status code :" + hitStatus);
			assertEquals(hitStatus, 400);
		}
	}*/

	/*@Test(groups = "Regression")
	public void OW_FromEmpty_9082() throws Exception {
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=&to=DEL&depart-date=" + onwarddate
				+ "&adults=1&children=0&infants=0&carrier=&cabin-type=Economy", headers, 200);

		String text = APIUtils.returnResponseAsString(response);
		int hitStatus = response.getStatusLine().getStatusCode();
		if (hitStatus == 400) {
			assertEquals(hitStatus, 400);
			assertTrue(text.contains("Origin is a required field."));
			Reporter.log("HTTP Status code :" + hitStatus + "Origin is a required field.");
		} else {
			Reporter.log("HTTP Status code :" + hitStatus);
			assertEquals(hitStatus, 400);
		}
	}*/
	/*

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_INFANT_COUNT_INVALID")
    public void Api_INFANT_COUNT_INVALID_371(String from, String to, String depart, String carrier, String aCount, String cCount,
                                             String iCount) throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=" + iCount + "&carrier=" + carrier
                + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 400) {
            assertEquals(hitStatus, 400);
            assertTrue(text.contains("Infants cannot exceed the adults in a booking."));
            Reporter.log("HTTP Status code :" + hitStatus + "Infants cannot exceed the adults in a booking.");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_Invalid_Auth")
    public void Api_Invalid_Auth_372(String from, String to, String depart, String carrier, String aCount, String cCount,
                                     String iCount) throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-CT-API-KEY", "garbage");
        headers.put("X-CT-SOURCE-TYPE", "API");
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=" + iCount + "&carrier=" + carrier
                + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 400) {
            assertEquals(hitStatus, 400);
            assertTrue(text.contains("Not authorized to access the service"));
            Reporter.log("HTTP Status code :" + hitStatus + "Not authorized to access the service");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_INVALID_PAX")
    public void Api_INVALID_PAX_373(String from, String to, String depart, String carrier, String aCount, String cCount,
                                    String iCount) throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=" + iCount + "&carrier=" + carrier
                + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 400) {
            assertEquals(hitStatus, 400);
            assertTrue(text.contains("Not a valid pax count for adult: 0"));
            Reporter.log("HTTP Status code :" + hitStatus + "Not a valid pax count for adult: 0");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }
    */

	/*@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_NUMBER_OF_PASSENGERS_EXCEEDED")
	public void Api_NUMBER_OF_PASSENGERS_EXCEEDED_374(String from, String to, String depart, String carrier, String aCount,
			String cCount, String iCount) throws Exception {
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
				+ onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=" + iCount + "&carrier=" + carrier
				+ "&&cabin-type=Economy", headers, 200);

		String text = APIUtils.returnResponseAsString(response);
		int hitStatus = response.getStatusLine().getStatusCode();
		if (hitStatus == 400) {
			assertEquals(hitStatus, 400);
			assertTrue(text.contains("Total number of passengers in a booking cannot exceed 9."));
			Reporter.log("HTTP Status code :" + hitStatus + "Total number of passengers in a booking cannot exceed 9.");
		} else {
			Reporter.log("HTTP Status code :" + hitStatus);
			assertEquals(hitStatus, 400);
		}
	}*/

	/*@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "OW_Carrier")
	public void OW_Carrier_375(String carrier) throws Exception {
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=DEL&to=BOM&depart-date=" + onwarddate
				+ "&adults=1&children=0&infants=0&carrier=" + carrier + "&&cabin-type=Economy", headers, 200);

		String text = APIUtils.returnResponseAsString(response);
		int hitStatus = response.getStatusLine().getStatusCode();
		if (hitStatus == 200) {
			assertEquals(hitStatus, 200);
			if (text.contentEquals("Flights not available.")) {
				Reporter.log(carrier + " : Flights not available.");
			} else {
				assertTrue(text.contains("</onward-solutions>"));
				assertFalse(text.contains("</return-solutions>"));
			}
			Reporter.log("HTTP Status code :" + hitStatus + " - " + carrier);
		} else {
			Reporter.log("HTTP Status code :" + hitStatus + " - " + carrier);
			assertEquals(hitStatus, 200);
		}
	}*/
	/*

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_Onway_Adult1_Dom")
    public void Api_Onway_Adult1_Dom_376(String from, String to, String depart, String carrier, String aCount, String cCount,
                                         String iCount) throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=" + iCount + "&carrier=" + carrier
                + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 200) {
            assertEquals(hitStatus, 200);
            if (text.contentEquals("Flights not available.")) {
                Reporter.log(carrier + " : Flights not available.");
                assertTrue(text.contentEquals("Flights not available."));
            } else {
                assertTrue(text.contains("</onward-solutions>"));
                assertFalse(text.contains("</return-solutions>"));
            }
            Reporter.log("HTTP Status code :" + hitStatus + " - " + carrier);
        } else {
            Reporter.log("HTTP Status code :" + hitStatus + " - " + carrier);
            assertEquals(hitStatus, 200);
        }
    }

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_Onway_Adult1_Intl")
    public void Api_Onway_Adult1_Intl_377(String from, String to, String depart, String carrier, String aCount, String cCount,
                                          String iCount) throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=" + iCount + "&carrier=" + carrier
                + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 200) {
            assertEquals(hitStatus, 200);
            if (text.contentEquals("Flights not available.")) {
                Reporter.log(carrier + " : Flights not available.");
                assertTrue(text.contentEquals("Flights not available."));
            } else {
                assertTrue(text.contains("</solutions>"));
                assertFalse(text.contains("</return-solutions>"));
            }
            Reporter.log("HTTP Status code :" + hitStatus + " - " + carrier);
        } else {
            Reporter.log("HTTP Status code :" + hitStatus + " - " + carrier);
            assertEquals(200, hitStatus);
        }
    }

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_ORIGIN_Empty")
    public void Api_ORIGIN_Empty_378(String from, String to, String depart, String carrier, String aCount, String cCount)
            throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier
                + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 400) {
            assertEquals(hitStatus, 400);
            assertTrue(text.contains("Origin is a required field."));
            Reporter.log("HTTP Status code :" + hitStatus + "Origin is a required field.");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_ORIGIN_Empty")
    public void Api_Oneway_Carrier_379(String from, String to, String depart, String carrier, String aCount, String cCount)
            throws Exception {
        HttpResponse response = APIUtils.getApi(
                getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date=" + depart + "&adults=" + aCount
                        + "&children=" + cCount + "&infants=0&carrier=" + carrier + "&&cabin-type=Economy", headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 400) {
            assertEquals(hitStatus, 400);
            assertTrue(text.contains("Origin is a required field."));
            Reporter.log("HTTP Status code :" + hitStatus + "Origin is a required field.");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }
    */

	/*@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_RETURN_DATE_FORMAT_INVALID")
	public void Api_RETURN_DATE_FORMAT_INVALID_380(String from, String to, String depart, String returndate, String carrier,
			String aCount, String cCount) throws Exception {
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
				+ depart + "&return-date=" + returndate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier="
				+ carrier, headers, 200);

		String text = APIUtils.returnResponseAsString(response);
		int hitStatus = response.getStatusLine().getStatusCode();
		if (hitStatus == 400) {
			assertEquals(hitStatus, 400);
			assertTrue(text.contains("Not a valid Date "));
			Reporter.log("HTTP Status code :" + hitStatus + "Not a valid Date ");
		} else {
			Reporter.log("HTTP Status code :" + hitStatus);
			assertEquals(hitStatus, 400);
		}
	}*/
	/*

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_RETURN_DATE_INVALID")
    public void Api_RETURN_DATE_INVALID_381(String from, String to, String depart, String returndate, String carrier,
                                            String aCount, String cCount) throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + depart + "&return-date=" + returndate + "&adults=" + aCount + "&children=" + cCount + "&infants=0&carrier="
                + carrier, headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 200) {
            assertEquals(hitStatus, 200);
            assertTrue(text.contains("Return-date can not be less than depart-date."));
            Reporter.log("HTTP Status code :" + hitStatus + "Return-date can not be less than depart-date.");
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 400);
        }
    }

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_Roundtrip_Adult1_Dom")
    public void Api_Roundtrip_Adult1_Dom_382(String from, String to, String depart, String returndate, String carrier,
                                             String aCount, String cCount, String iCount) throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&return-date=" + returndate1 + "&adults=" + aCount + "&children=" + cCount
                + "&infants=0&carrier=" + carrier, headers, 200);

        String text = APIUtils.returnResponseAsString(response);
        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 200) {
            assertEquals(hitStatus, 200);
            assertTrue(text.contains("<onward-solutions>"));
            assertTrue(text.contains("<return-solutions>"));
            Reporter.log("HTTP Status code :" + hitStatus);
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 200);
        }
    } */

    @Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_Roundtrip_Adult1_Intl")
    public void Api_Roundtrip_Adult1_Intl_383(String from, String to, String depart, String returndate, String carrier,
                                              String aCount, String cCount, String iCount) throws Exception {
        HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
                + onwarddate + "&return-date=" + returndate1 + "&adults=" + aCount + "&children=" + cCount + "&infants=" + iCount
                + "&carrier=" + carrier, headers, 200);

        String text = APIUtils.returnResponseAsString(response);

        int hitStatus = response.getStatusLine().getStatusCode();
        if (hitStatus == 200) {
            assertEquals(hitStatus, 200);
            assertTrue(text.contains("</solution>"));
            Reporter.log("HTTP Status code :" + hitStatus);
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            assertEquals(hitStatus, 200);
        }
    }

	/*@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "Api_Roundtrip_Adult9_Infant9")
	public void Api_Roundtrip_Adult9_Infant9_384(String from, String to, String depart, String returndate, String carrier,
			String aCount, String cCount, String iCount) throws Exception {
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date="
				+ onwarddate + "&return-date=" + returndate1 + "&adults=" + aCount + "&children=" + cCount + "&infants=" + iCount
				+ "&carrier=" + carrier, headers, 200);

		String text = APIUtils.returnResponseAsString(response);
		int hitStatus = response.getStatusLine().getStatusCode();
		if (hitStatus == 200) {
			assertEquals(hitStatus, 200);
			assertTrue(text.contains("</onward-solutions>"));
			assertTrue(text.contains("</return-solutions>"));
			Reporter.log("HTTP Status code :" + hitStatus);
		} else {
			Reporter.log("HTTP Status code :" + hitStatus);
			assertEquals(hitStatus, 200);
		}
	}
*/
	/*@Test(groups = "Regression", dataProviderClass = APIDataProvider.class, dataProvider = "RT_Carrier")
	public void RT_Carrier_385(String carrier) throws Exception {
		HttpResponse response = APIUtils.getApi(getBaseUrl() + "/air/1.0/search?from=DEL&to=BOM&depart-date=" + onwarddate
				+ "&return-date=" + returndate1 + "&adults=1&children=0&infants=0&carrier=" + carrier, headers, 200);

		String text = APIUtils.returnResponseAsString(response);
		int hitStatus = response.getStatusLine().getStatusCode();
		if (hitStatus == 200) {
			assertEquals(hitStatus, 200);
			assertTrue(text.contains("<onward-solutions>"));
			assertTrue(text.contains("<return-solutions>"));
			Reporter.log("HTTP Status code :" + hitStatus);
		} else {
			Reporter.log("HTTP Status code :" + hitStatus);
			assertEquals(hitStatus, 200);
		}
	}*/

	/*@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethodNoScreenshot(_result);
	}*/

}
