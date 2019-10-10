package testScriptsExternalAPI;

import com.google.common.collect.ArrayListMultimap;
import commonServices.APIUtils;
import domainServices.APIServices;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.testng.AssertJUnit.assertTrue;

public class API_DomOneway_With_GST_IN_Book extends APIServices {

	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys = ArrayListMultimap.create();
	String payLoadFileName = "PayloadSinglePAX.txt";
	// String debug = cachedProperties.value("debug_mode");
	boolean debug_mode = Boolean.parseBoolean(debug);
	boolean bookingSuccess = false;
	String tripId = null;
	String airlinepnr = null;
	String ticketnumber = null;
	String bookingstatus = null;
	String totalfare = null;
	String basefare = null;
	String discount = null;
	String taxes = null;
	String cashback = null;
	StringBuilder cancelbuilder = new StringBuilder();
	public RemoteWebDriver driver = null;
	String responseString;

	@Test(dataProvider = "dp", groups = { "Smoke Test" })
	public void retailOneAdult_351(String from, String to, String carrier, String aCount, String cCount)
			throws Exception {

		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		ArrayList<String> fareKeys = new ArrayList<String>();
		boolean bookingSuccess = false;
		HashMap<String, String> headers = APIUtils.getExtAPIHeaders(common.value("APIKey"), "API");
		String onwarddate = getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		HttpResponse response = APIUtils.getApi(
				getBaseUrl() + "/air/1.0/search?from=" + from + "&to=" + to + "&depart-date=" + onwarddate + "&adults="
						+ aCount + "&children=" + cCount + "&infants=0&carrier=" + carrier + "&cabin-type=Economy",
				headers, 200);

		String responseString = APIUtils.returnResponseAsString(response);
		// System.out.println("------"+XML.toJSONObject(responseString));
		int noOfFareKeys = 0;
		NodeList nodeList = APIUtils.getNodeListFromXMLResponseString(responseString, "//onward-solutions/solution");
		noOfFareKeys = nodeList.getLength();
		for (int i = 0; i < noOfFareKeys && i < 5; i++) {
			fareKeys.add(APIUtils.getTextContentOfNodeInNode(nodeList.item(i),
					"./pax-pricing-info-list/pax-pricing-info/pricing-info-list/pricing-info/fare-key"));
		}

		int i = 0;
		boolean flightUnavaliable = true;
		do {
			String key = fareKeys.get(i);
			String flightno = key.split("_" + carrier)[1].split("_")[1].trim();
			String post = apiPayload.value("createItinneraryDomOWPayload");
			// System.out.println(post);
			Random r = new Random();
			char c = (char) (r.nextInt(26) + 'a');
			String postString = post.replaceFirst("#key", key).replaceFirst("#from", from).replaceFirst("#to", to)
					.replaceFirst("#flightno", flightno).replaceFirst("#carrier", carrier)
					.replaceFirst("#onwarddate", onwarddate).replaceFirst("Jivan", "Jivann" + c)
					.replaceFirst("Kotian", "Kotiann" + c);
			// System.out.println(postString);
			HttpResponse itinenaryResponse = APIUtils.postApi(getBaseUrl() + "/air/1.0/itinerary/create", headers,
					postString, 200);
			String itinenaryResponseString = APIUtils.returnResponseAsString(itinenaryResponse);

			String itineraryId = null;
			try {
				itineraryId = APIUtils
						.getNodeListFromXMLResponseString(itinenaryResponseString, "//itinerary/itinerary-id").item(0)
						.getTextContent();
				Reporter.log(itineraryId);
			} catch (Exception e) {
				Reporter.log(e.toString());
				Reporter.log("response is : \n" + itinenaryResponseString);
				i++;
				continue;
			}
			/*
			 * HttpResponse PriceResponse = priceCheck(itineraryId, headers); String
			 * PricecheckResponseString = APIUtils.returnResponseAsString(PriceResponse);
			 * System.out.println(PricecheckResponseString);
			 */

			if ((common.value("makePayment").equals("true"))) {
				HttpResponse bookResponse = callBook(itineraryId, headers, true);
				String bookResponseString = APIUtils.returnResponseAsString(bookResponse);
				// System.out.println(bookResponseString);
				bookingSuccess = assertBookXmlResponseOW(bookResponseString);
				try {
					tripId = APIUtils.getNodeListFromXMLResponseString(bookResponseString, "//trip-id").item(0)
							.getTextContent();
					checkDetails(driver, tripId, "SpiceJet", false);
				}catch(Exception e)
				{
					Reporter.log(e.toString());
				}
			}
			if (bookingSuccess) {
				flightUnavaliable = false;
			}
			i++;
		} while (i < 5 && i < noOfFareKeys && flightUnavaliable);
		if ((common.value("makePayment").equals("true"))) {
			assertTrue("Booking Failed. Error!", bookingSuccess);
		}

		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");

		//

		/*
		 * //String common.value("Environment") =
		 * getcommon.value("Environment")("common.value("Environment")"); String
		 * onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		 * String returndate1=getModifiedDate(common.value(
		 * "Days_to_add_for_CurrentDate_to_return"));
		 * ////System.out.println("onward date="+onwarddate+"   returndate="+returndate1
		 * ); clientSearch = new DefaultHttpClient();
		 * ////System.out.println(common.value("protocol")+"://"+common.value(
		 * "Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+
		 * "&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+
		 * "&infants=0&carrier="+carrier+"&cabin-type=Economy"); HttpGet get = new
		 * HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+
		 * ".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+
		 * onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+
		 * carrier+"&cabin-type=Economy"));
		 * ////System.out.println(common.value("protocol")+"://"+common.value(
		 * "Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+
		 * "&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+
		 * "&infants=0&carrier="+carrier+"&cabin-type=Economy"); //
		 * getdepositaccountid(common.value("AccountID"),"Book.txt");
		 * //getdepositaccountid(common.value("AccountID"),"PayloadIntlSinglePAX.txt");
		 * ////System.out.println(common.value("APIKey"));
		 * get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		 * //get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		 * //get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		 * HttpResponse response = clientSearch.execute(get);
		 * ////System.out.println(response); HttpEntity entity = response.getEntity();
		 * Document doc =null; StringBuffer sb1 = new StringBuffer(); String str1="";
		 * BufferedReader br1 = new BufferedReader(new
		 * InputStreamReader(response.getEntity().getContent()));
		 * while((str1=br1.readLine())!=null){ sb1.append(str1.trim()); }
		 * 
		 * JSONObject jsonObject = XML.toJSONObject(sb1.toString());
		 * 
		 * //System.out.println("------"+jsonObject); JSONObject airSearchResult =
		 * jsonObject.getJSONObject("air-search-result"); JSONObject onward =
		 * airSearchResult.getJSONObject("onward-solutions");
		 * ////System.out.println("onward="+onward); JSONArray onwardSolutions =
		 * onward.getJSONArray("solution");
		 * ////System.out.println("onwardSolutions=="+onwardSolutions); for(int
		 * a=0;a<onwardSolutions.length();a++){ //
		 * //System.out.println(onwardSolutions.getJSONObject(a).getJSONObject(
		 * "pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject(
		 * "pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString()
		 * );
		 * fareKeys.put(String.valueOf(a),onwardSolutions.getJSONObject(a).getJSONObject
		 * ("pax-pricing-info-list").getJSONObject("pax-pricing-info").getJSONObject(
		 * "pricing-info-list").getJSONObject("pricing-info").get("fare-key").toString()
		 * ); ////System.out.println("fare key value="+fareKeys.get("a")); } int i=0;
		 * boolean flightUnavailable; Test:do{
		 * 
		 * flightUnavailable= false;
		 * //if(fareKeys.get(String.valueOf(i)).toArray().length==2){ // String key =
		 * fareKeys.get(String.valueOf(i)).toString(); String
		 * key1=fareKeys.get(String.valueOf(i)).toString().replace("[","").replace("]",
		 * ""); // //System.out.println(key1); String flightno =
		 * fareKeys.get(String.valueOf(i)).toString().split(carrier.trim())[1].split("_"
		 * )[1].trim(); ////System.out.println(flightno); // String
		 * flightno1=flightno.replace("[","").replace("]","");
		 * ////System.out.println("flight no 1=================="+flightno1); String key
		 * = fareKeys.get("fare-key").get(i); String flightno =
		 * fareKeys.get("fare-key").get(i).split(carrier.trim())[1].split("_")[1].trim()
		 * ; Random r = new Random(); char c = (char) (r.nextInt(26) + 'a');
		 * 
		 * String
		 * postString="<?xml version=\"1.0\" encoding=\"UTF-8\"?><itinerary>  <cabin-type>E</cabin-type>  <flights>    <flight-spec>      <fare-key>"
		 * +key1+"</fare-key>      <segments>        <segment-spec>          <departure-airport>"
		 * +from+"</departure-airport>          <arrival-airport>"
		 * +to+"</arrival-airport>          <flight-number>"
		 * +flightno+"</flight-number>          <airline>"
		 * +carrier+"</airline>          <departure-date>"
		 * +onwarddate+"</departure-date>        </segment-spec>      </segments>    </flight-spec>  </flights>  <pax-info-list>    <pax-info>      <title>Mr</title>      <first-name>test"
		 * +c+"</first-name>      <last-name>test"
		 * +c+"</last-name>      <type>ADT</type>      <id-number>Grant</id-number>      <date-of-birth>1990-05-05</date-of-birth>    </pax-info>  </pax-info-list>  <contact-detail>    <title>Mr</title>    <first-name>Test</first-name>    <last-name>Booking</last-name>    <email>nfsfrank@hotmail.com</email>    <address>Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)</address>    <mobile>919844000000</mobile>    <landline>02240554000</landline>    <city-name>Mumbai</city-name>    <state-name>Maharashtra</state-name>    <country-name>India</country-name>    <pin-code>400011</pin-code>  </contact-detail></itinerary>"
		 * ; getPayLoad1111(key1, from, to,onwarddate, flightno,
		 * carrier,payLoadFileName); // } FileInputStream fis = new
		 * FileInputStream("src\\testScriptsExternalAPICommon\\"+payLoadFileName);
		 * BufferedReader brIti = new BufferedReader(new InputStreamReader(fis)); String
		 * strIti=""; StringBuffer sbIti = new StringBuffer();
		 * while((strIti=brIti.readLine())!=null){ sbIti.append(strIti);
		 * 
		 * }
		 * 
		 * String postString = sbIti.toString(); System.out.println(postString);
		 * 
		 * //System.out.println(postString);
		 * System.out.println(common.value("protocol")+"://"+common.value("Environment")
		 * +".cleartrip.com/air/1.0/itinerary/create"); HttpPost itinenaryCall = new
		 * HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+
		 * ".cleartrip.com/air/1.0/itinerary/create")); StringEntity params = new
		 * StringEntity(postString); itinenaryCall.setEntity(params);
		 * 
		 * itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey")); HttpResponse
		 * itinenaryResponse = clientSearch.execute(itinenaryCall); HttpEntity entityIti
		 * = itinenaryResponse.getEntity(); Document docIti =null; BufferedReader br12 =
		 * new BufferedReader(new InputStreamReader(entityIti.getContent())); String
		 * str12 =""; StringBuffer sb12 =new StringBuffer();
		 * while((str12=br12.readLine())!=null){ sb12.append(str12); }
		 * 
		 * //System.out.println(sb12); JSONObject itinenaryId=
		 * XML.toJSONObject(sb12.toString());
		 * //System.out.println("itinerary id="+itinenaryId);
		 * //Reporter.log(itinenaryId.toString()); FileInputStream fisBook1 = new
		 * FileInputStream("src\\testScriptsExternalAPICommon\\pricecheck.txt");
		 * BufferedReader brBook1 = new BufferedReader(new InputStreamReader(fisBook1));
		 * String strBook1=""; StringBuffer sbBook1 = new StringBuffer();
		 * while((strBook1=brBook1.readLine())!=null){ sbBook1.append(strBook1);
		 * 
		 * }
		 * 
		 * String postStringBook1 = sbBook1.toString();
		 * 
		 * FileInputStream fisBook = new
		 * FileInputStream("src\\testScriptsExternalAPICommon\\Book.txt");
		 * BufferedReader brBook = new BufferedReader(new InputStreamReader(fisBook));
		 * String strBook=""; StringBuffer sbBook = new StringBuffer();
		 * while((strBook=brBook.readLine())!=null){ sbBook.append(strBook);
		 * 
		 * }
		 * 
		 * String postStringBook = sbBook.toString(); if
		 * ((common.value("makePayment").equals("true"))) { String
		 * postStringBook1="<price-check></price-check>"; String
		 * postStringBook="<?xml version=\"1.0\" encoding=\"UTF-8\"?><booking>  <payment-detail>    <payment-type>DA</payment-type>    <deposit-account-id>"
		 * +common.value("AccountID")
		 * +"</deposit-account-id>  </payment-detail></booking>"; Document docBook =
		 * null; Document docBook1 = null; //try{
		 * System.out.println(common.value("protocol")+"://"+common.value("Environment")
		 * +".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject(
		 * "itinerary").get("itinerary-id")); HttpPost bookCall1 = new HttpPost(new
		 * URI(common.value("protocol")+"://"+common.value("Environment")+
		 * ".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject(
		 * "itinerary").get("itinerary-id"))); StringEntity paramsBook1 = new
		 * StringEntity(postStringBook1); bookCall1.setEntity(paramsBook1);
		 * //System.out.println(common.value("APIKey"));
		 * bookCall1.setHeader("X-CT-API-KEY",common.value("APIKey")); HttpResponse
		 * bookResponse1 = clientSearch.execute(bookCall1);
		 * //System.out.println("book response="+bookResponse1.getEntity()); HttpEntity
		 * entityBook1 = bookResponse1.getEntity(); String responseString =
		 * EntityUtils.toString(entityBook1, "UTF-8");
		 * System.out.println("response string=="+responseString);
		 * 
		 * if(itinenaryResponse!=null){ DocumentBuilderFactory dbf1 =
		 * DocumentBuilderFactory.newInstance(); DocumentBuilder db1 =
		 * dbf1.newDocumentBuilder(); docBook1 = db1.parse(entityBook1.getContent()); }
		 * Reporter.log("sucess message=="+docBook1.getElementsByTagName(
		 * "succes-message").item(0).getTextContent());
		 * //System.out.println("sucess message=="+docBook1.getElementsByTagName(
		 * "succes-message").item(0).getTextContent()); int hitStatus =
		 * bookResponse1.getStatusLine().getStatusCode();
		 * Reporter.log("status="+hitStatus); //System.out.println("status="+hitStatus);
		 * System.out.println(common.value("protocol")+"://"+common.value("Environment")
		 * +".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject(
		 * "itinerary").get("itinerary-id")); HttpPost bookCall = new HttpPost(new
		 * URI(common.value("protocol")+"://"+common.value("Environment")+
		 * ".cleartrip.com/air/1.0/itinerary/book/"+itinenaryId.getJSONObject(
		 * "itinerary").get("itinerary-id"))); StringEntity paramsBook = new
		 * StringEntity(postStringBook); bookCall.setEntity(paramsBook);
		 * bookCall.setHeader("X-CT-API-KEY",common.value("APIKey")); HttpResponse
		 * bookResponse = clientSearch.execute(bookCall);
		 * //System.out.println("book response="+bookResponse.getEntity()); HttpEntity
		 * entityBook = bookResponse.getEntity(); responseString =
		 * EntityUtils.toString(entityBook, "UTF-8");
		 * System.out.println(responseString); StringBuffer sb11 = new StringBuffer();
		 * String str11=""; BufferedReader br11 = new BufferedReader(new
		 * InputStreamReader(bookResponse.getEntity().getContent()));
		 * while((str11=br11.readLine())!=null){ sb11.append(str11); }
		 * 
		 * JSONObject jsonObject1 = XML.toJSONObject(sb11.toString());
		 * //System.out.println(jsonObject1); if(itinenaryResponse!=null){
		 * DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		 * DocumentBuilder db = dbf.newDocumentBuilder(); // docBook =
		 * db.parse(responseString); docBook = db.parse(entityBook.getContent()); }
		 * //System.out.println(responseString); Reporter.log(responseString);
		 * Reporter.log("Tickrt number="+docBook.getElementsByTagName("ticket-number").
		 * item(0).getTextContent());
		 * //System.out.println("Tickrt number="+docBook.getElementsByTagName(
		 * "ticket-number").item(0).getTextContent());
		 * Reporter.log("gds-pnr="+docBook.getElementsByTagName("gds-pnr").item(0).
		 * getTextContent());
		 * //System.out.println("gds-pnr="+docBook.getElementsByTagName("gds-pnr").item(
		 * 0).getTextContent());
		 * Reporter.log("base-fare="+docBook.getElementsByTagName("base-fare").item(0).
		 * getTextContent());
		 * //System.out.println("base-fare="+docBook.getElementsByTagName("base-fare").
		 * item(0).getTextContent()); basefare =
		 * docBook.getElementsByTagName("base-fare").item(0).getTextContent();
		 * Reporter.log("discount="+docBook.getElementsByTagName("discount").item(0).
		 * getTextContent());
		 * //System.out.println("discount="+docBook.getElementsByTagName("discount").
		 * item(0).getTextContent()); discount =
		 * docBook.getElementsByTagName("discount").item(0).getTextContent();
		 * //System.out.println("taxes="+docBook.getElementsByTagName("taxes").item(0).
		 * getTextContent());
		 * Reporter.log("taxes="+docBook.getElementsByTagName("taxes").item(0).
		 * getTextContent()); taxes =
		 * docBook.getElementsByTagName("taxes").item(0).getTextContent();
		 * Reporter.log("cashback="+docBook.getElementsByTagName("cashback").item(0).
		 * getTextContent());
		 * //System.out.println("cashback="+docBook.getElementsByTagName("cashback").
		 * item(0).getTextContent()); cashback =
		 * docBook.getElementsByTagName("cashback").item(0).getTextContent();
		 * System.out.println("trip-id="+docBook.getElementsByTagName("trip-id").item(0)
		 * .getTextContent());
		 * Reporter.log("trip-id="+docBook.getElementsByTagName("trip-id").item(0).
		 * getTextContent()); tripId =
		 * docBook.getElementsByTagName("trip-id").item(0).getTextContent();
		 * Reporter.log("airline-pnr="+docBook.getElementsByTagName("airline-pnr").item(
		 * 0).getTextContent());
		 * //System.out.println("airline-pnr="+docBook.getElementsByTagName(
		 * "airline-pnr").item(0).getTextContent());
		 * airlinepnr=docBook.getElementsByTagName("airline-pnr").item(0).getTextContent
		 * ();
		 * Reporter.log("ticket-number="+docBook.getElementsByTagName("ticket-number").
		 * item(0).getTextContent());
		 * //System.out.println("ticket-number="+docBook.getElementsByTagName(
		 * "ticket-number").item(0).getTextContent());
		 * ticketnumber=docBook.getElementsByTagName("ticket-number").item(0).
		 * getTextContent();
		 * Reporter.log("booking-status="+docBook.getElementsByTagName("booking-status")
		 * .item(0).getTextContent());
		 * //System.out.println("booking-status="+docBook.getElementsByTagName(
		 * "booking-status").item(0).getTextContent());
		 * bookingstatus=docBook.getElementsByTagName("booking-status").item(0).
		 * getTextContent(); bookingSuccess=true; flightUnavailable=false; }
		 * catch(Exception e){
		 * ////System.out.println("response string=="+responseString);
		 * //Reporter.log("response string=="+responseString); flightUnavailable=true; }
		 * 
		 * i++; //break Test; HttpDelete deleteRequest = new
		 * HttpDelete("https://apiqa.cleartrip.com/trips/air/cancel/" + tripId);
		 * deleteRequest.addHeader("X-CT-API-KEY", "g9s45bsammqggtczpz3kj3qk");
		 * HttpResponse cancelRequest = clientSearch.execute(deleteRequest);
		 * BufferedReader cancelbr = new BufferedReader(new
		 * InputStreamReader((cancelRequest.getEntity().getContent())));
		 * System.out.println("-----"+cancelbr);
		 * 
		 * String cancelxml; while ((cancelxml = cancelbr.readLine()) != null) {
		 * cancelbuilder.append(cancelxml); } // #################################
		 * Cancel Details // ############################## // String cancelDetails =
		 * cancelbuilder.toString(); System.out.println("----------"+cancelDetails); if
		 * ( debug_mode ) { //System.out.println("cancelDetails :" + cancelDetails); }
		 * else { Reporter.log("cancelDetails :" + cancelDetails); } String [ ] getTxnId
		 * = cancelDetails.split("<txn-id>"); String [ ] TxnId =
		 * getTxnId[1].split("</txn-id>"); Reporter.log("<txn-id>                = " +
		 * TxnId[0]); String [ ] getStatus =
		 * cancelDetails.split("<supplier-cancellation>"); String [ ] Status =
		 * getStatus[1].split("</supplier-cancellation>");
		 * Reporter.log("<supplier-cancellation> = " + Status[0]); if (
		 * Onwardoperatingairline[0].contains("SG") ) {
		 * assertTrue(Status[0].contains("F")); } else {
		 * assertTrue(Status[0].contains("S")); } //}
		 * //httpclient.getConnectionManager().shutdown();
		 * 
		 * }
		 * 
		 * }while(i<1 && flightUnavailable); if
		 * ((common.value("makePayment").equals("true"))) {
		 * Assert.assertTrue(bookingSuccess,"Booking Failed"); }
		 */}

	@Test(dependsOnMethods = { "retailOneAdult_351" })
	public void cancel() throws IOException, IOException {
		if ((common.value("makePayment").equals("true"))) {
			// System.out.println("https://apiqa.cleartrip.com/trips/air/cancel/"+ tripId);
			DefaultHttpClient clientSearch1 = new DefaultHttpClient();
			;
			HttpDelete deleteRequest = new HttpDelete(getBaseUrl() + "/trips/air/cancel/" + tripId);
			// deleteRequest.addHeader("X-CT-API-KEY", "g9s45bsammqggtczpz3kj3qk");
			deleteRequest.addHeader("X-CT-API-KEY", common.value("APIKey"));
			HttpResponse cancelRequest = clientSearch1.execute(deleteRequest);
			BufferedReader cancelbr = new BufferedReader(
					new InputStreamReader((cancelRequest.getEntity().getContent())));
			// System.out.println("-----"+cancelbr);

			String cancelxml;
			while ((cancelxml = cancelbr.readLine()) != null) {
				cancelbuilder.append(cancelxml);
			}
			// ################################# Cancel Details
			// ############################## //
			String cancelDetails = cancelbuilder.toString();
			System.out.println("----------" + cancelDetails);
			if (debug_mode) {
				// System.out.println("cancelDetails :" + cancelDetails);
			} else {
				Reporter.log("cancelDetails :" + cancelDetails);
			}
		}
	}

	@DataProvider(name = "dp")
	public static Object[][] oneAdultDp() {
		return new Object[][] { { "DEL", "BOM", "6E", "1", "0" }

		};
	}
	// PayloadIntlSinglePAX.txti
	/*
	 * @AfterMethod(alwaysRun = true) public void afterMethod(ITestResult _result)
	 * throws Exception { afterMethodNoScreenshot(_result); }
	 */

}
