package headLessBooking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class SearchAPI {
	protected  List<Cookie> cookiesB2C = null;
	protected  List<Cookie> cookiesHQ = null;
	CookieStore Cook;
	String host = "qa2.cleartrip.com";


	@SuppressWarnings("resource")
	public  String  airSearch(String Triptype, String From, String To, String depart_date,String return_date, String Adults, String Child, String Infant, String Airline) throws ClientProtocolException, IOException, JSONException, InterruptedException {
		DefaultHttpClient Client = new DefaultHttpClient();
		String searchUrl="http://"+host+"/flights/results/airjson?trip_type="+Triptype+"&from="+From+"&to="+To+"&depart_date="+depart_date+"&adults="+Adults+"&childs="+Child+"&infants="+Infant+"&class=Economy&airline="+Airline+"&carrier=&ver=V2&type=json&intl=n&page=&search_ver=V2&cc=1&rhc=1";
		//System.out.println(searchUrl);
		HttpGet GetResults = new HttpGet(searchUrl);
		HttpResponse searchResponse1 = Client.execute(GetResults);
		BufferedReader br1 = new BufferedReader(new InputStreamReader((searchResponse1.getEntity().getContent())));
		String output1;
		StringBuffer totalOutput1 = new StringBuffer();
		while ((output1 = br1.readLine()) != null) {
			totalOutput1.append(output1);
		}
		String JsonResponse=totalOutput1.toString();
		JSONObject Data = new JSONObject(JsonResponse);
		String[] suppiers = getExpiredSuppiers(JsonResponse);
		/*boolean expiredSupplierFlag = (boolean) Data.get("expiredSupplier");
		////System.out.println(expiredSupplierFlag);
		int count=0;
		if(expiredSupplierFlag){
			do {
				JsonResponse =SearchExpiry(JsonResponse,suppiers,Triptype,From,To,depart_date,return_date,Adults,Child,Infant,Airline);
				suppiers = getExpiredSuppiers(JsonResponse);
				count++;
			} while (suppiers[1].length()>3 && count>6);
		}
		////System.out.println(JsonResponse);
		EntityUtils.consumeQuietly(searchResponse1.getEntity());
		GetResults.releaseConnection();*/
		return JsonResponse;

	}


	private  String[] getExpiredSuppiers(String JsonResponse) throws JSONException, InterruptedException {
		String[] pickedString = new String[2];
		JSONObject Data = new JSONObject(JsonResponse);
		pickedString[0] = Data.getString("sid");
	/*	boolean expiredSupplierFlag = (boolean) Data.get("expiredSupplier");
		////System.out.println(expiredSupplierFlag);
		if(expiredSupplierFlag){
			JSONObject jsons = Data.getJSONObject("jsons");
			pickedString[1]  = jsons.getJSONObject("expiredSuppliers").toString();
			////System.out.println(pickedString[1] );
			//Thread.sleep(1000);	
		}else{
			pickedString[1]  = "";
		}*/
		return pickedString;
	}


	public  String SearchExpiry(String JsonResponse,String[] eSuppiersWithSid,String Triptype, String From, String To, String depart_date,String return_date, String Adults, String Child, String Infant, String Airline) throws ClientProtocolException, IOException {
		@SuppressWarnings("resource")
		DefaultHttpClient Client = new DefaultHttpClient();
		////System.out.println(eSuppiersWithSid[1]);
		String eSuppiers =URLEncoder.encode(eSuppiersWithSid[1],"UTF-8");
		////System.out.println(eSuppiers);
		String sid =eSuppiersWithSid[0];
		String searchExpiry="http://"+host+"/flights/results/airjson?trip_type="+Triptype+"&from="+From+"&to="+To+"&depart_date="+depart_date+"&adults="+Adults+"&childs="+Child+"&infants="+Infant+"&class=Economy&airline="+Airline+"&carrier=&stops=0&intl=n&sd=1457356991459&page=loaded&search_ver=V2&cc=5&type=json&ver=V2&isIntl=false&isRt=false&isMetaSearch=false&numLegs=1&trip_type=OneWay&fromTo1=BOM_DEL&expiry="+eSuppiers+"&psid="+sid+"&fci=1457356581218&nbs=N";
		//System.out.println(searchExpiry);
		HttpGet GetResults = new HttpGet(searchExpiry);
		HttpResponse searchResponse = Client.execute(GetResults);
		BufferedReader br1 = new BufferedReader(new InputStreamReader((searchResponse.getEntity().getContent())));
		String output1;
		StringBuffer totalOutput1 = new StringBuffer();
		while ((output1 = br1.readLine()) != null) {
			totalOutput1.append(output1);
		}
		String JsonResponse1=totalOutput1.toString();
		if(JsonResponse1.startsWith("{\"fare")){
			JsonResponse=JsonResponse1;	
		}
		////System.out.println(JsonResponse);
		EntityUtils.consumeQuietly(searchResponse.getEntity());
		GetResults.releaseConnection();
		return JsonResponse;

	}

	public  HashMap<String, String> getOWPostData(String dynamicSearchData, int index) throws JSONException {
		HashMap<String, String> pickedData = new HashMap<String, String>();
		try {
			JSONObject Data = new JSONObject(dynamicSearchData);
			JSONObject mapping = Data.getJSONObject("mapping");
			
			JSONArray onward = mapping.getJSONArray("onward");
			////System.out.println("Total  OneWay Solution : "+onward.length());
			pickedData.put("Solution", String.valueOf(onward.length()));
			JSONObject SolutionIndex = onward.getJSONObject(index);
			////System.out.println(SolutionIndex);
			String contentIndex = SolutionIndex.getJSONArray("c").getString(0);
			pickedData.put("contentIndex", contentIndex);
			String fareIndex = SolutionIndex.getString("f");
			pickedData.put("fareIndex", fareIndex);
			////System.out.println(contentIndex);
			////System.out.println(fareIndex);
			JSONObject content = Data.getJSONObject("content");
			JSONObject contentData = content.getJSONObject(contentIndex);
			String From =contentData.getString("fr").trim();
			String to =contentData.getString("to").trim();
			String departDate =contentData.getString("ad").trim();
			String arrivalTime =contentData.getString("a").trim();
			String[] FareKey =contentData.getString("fk").split("_");
			String flightNo =FareKey[1].trim();
			String departTime =FareKey[3].trim();
			String Class =FareKey[4].trim();

			JSONObject fare = Data.getJSONObject("fare");
			JSONObject fareData = fare.getJSONObject(fareIndex);
			String fareType=fareData.getString("dfd");
			JSONObject fareDataRegular = fareData.getJSONObject(fareType);

			String baseFare =fareDataRegular.getString("bp").replaceAll("\\.0", "").trim();
			String fareBaseKey =fareDataRegular.getString("fk").trim();
			String fareBase =fareDataRegular.getString("fb").trim();
			String taxes =fareDataRegular.getString("t").replaceAll("\\.0", "").trim();
			String price =fareDataRegular.getString("pr").replaceAll("\\.0", "").trim();
			String discount =fareDataRegular.getString("ds").replaceAll("\\.0", "").trim();
			String adultBF =fareDataRegular.getString("abp").replaceAll("\\.0", "").trim();

			JSONObject jsons = Data.getJSONObject("jsons");
			String topLevelRateRules =jsons.getString("topLevelRateRules").trim();;
			String newGdsSplrtFare =jsons.getString("newGdsSplrtFare").trim();;

			pickedData.put("from", From);
			pickedData.put("to", to);
			pickedData.put("departDate", departDate);
			pickedData.put("arrivalTime", arrivalTime);
			pickedData.put("flightNo", flightNo);
			pickedData.put("departTime", departTime);
			pickedData.put("Class", Class);
			pickedData.put("baseFare", baseFare);
			pickedData.put("fareBaseKey", fareBaseKey);
			pickedData.put("fareBase", fareBase);
			pickedData.put("baseFare", taxes);
			pickedData.put("price", price);
			pickedData.put("discount", discount);
			pickedData.put("adultBF", adultBF);
			pickedData.put("topLevelRateRules", topLevelRateRules);
			pickedData.put("newGdsSplrtFare", newGdsSplrtFare);
			return pickedData;
		} catch (Exception e) {
			return null;
		} 


		////System.out.println(pickedData);
	
	}

	@SuppressWarnings({ "resource" })
	public  String createItinerary(String Adults, String Child, String Infant,
			String Airline, HashMap<String, String> initiateBooking)
					throws URISyntaxException, UnsupportedEncodingException,
					IOException, ClientProtocolException {
		String itineraryID="";
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			URI url = new URI("http://"+host+"/flights/initiate-booking");
			//System.out.println(url);
			HttpPost initiateBookingPostCall = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("from", initiateBooking.get("from")));
			params.add(new BasicNameValuePair("to", initiateBooking.get("to")));
			params.add(new BasicNameValuePair("depart_date", initiateBooking.get("departDate")));
			params.add(new BasicNameValuePair("adults", Adults));
			params.add(new BasicNameValuePair("childs", Child));
			params.add(new BasicNameValuePair("infants", Infant));
			params.add(new BasicNameValuePair("airline", Airline));
			params.add(new BasicNameValuePair("carrier", ""));
			params.add(new BasicNameValuePair("cc", "1"));
			params.add(new BasicNameValuePair("rhc", "1"));
			params.add(new BasicNameValuePair("type", "json"));
			params.add(new BasicNameValuePair("out_price",initiateBooking.get("price") ));
			params.add(new BasicNameValuePair("out_fare_key", initiateBooking.get("fareBaseKey")));
			params.add(new BasicNameValuePair("out_leg_fare_basis_1", initiateBooking.get("fareBase")));	
			params.add(new BasicNameValuePair("out_base_price", initiateBooking.get("baseFare")));
			params.add(new BasicNameValuePair("out_taxes", initiateBooking.get("baseFare")));
			params.add(new BasicNameValuePair("out_disc", initiateBooking.get("discount")));
			params.add(new BasicNameValuePair("out_adult_base", initiateBooking.get("adultBF")));
			params.add(new BasicNameValuePair("out_per_adult_base_price", initiateBooking.get("adultBF")));
			params.add(new BasicNameValuePair("out_leg_from_1", initiateBooking.get("from")));
			params.add(new BasicNameValuePair("out_leg_to_1", initiateBooking.get("to")));
			params.add(new BasicNameValuePair("out_leg_arrives_date_1", initiateBooking.get("departDate")));
			params.add(new BasicNameValuePair("out_leg_airline_1", ""));
			params.add(new BasicNameValuePair("out_leg_arrives_1", initiateBooking.get("arrivalTime")));
			params.add(new BasicNameValuePair("out_leg_aircode_1", initiateBooking.get("flightNo")));
			params.add(new BasicNameValuePair("out_leg_departs_date_1", initiateBooking.get("departDate")));
			params.add(new BasicNameValuePair("out_leg_departs_1", initiateBooking.get("departTime")));
			params.add(new BasicNameValuePair("out_leg_cabin_type_1", initiateBooking.get("Class")));
			params.add(new BasicNameValuePair("out_no_legs", "1"));
			params.add(new BasicNameValuePair("topLevelRateRules", initiateBooking.get("topLevelRateRules")));
			params.add(new BasicNameValuePair("newGdsSplrtFare", initiateBooking.get("newGdsSplrtFare")));
			initiateBookingPostCall.setEntity(new UrlEncodedFormEntity(params));
			initiateBookingPostCall.setHeader("content-type", "application/x-www-form-urlencoded");
			initiateBookingPostCall.setHeader("user-agent", "User-Agent Mozilla/5.0 (Windows NT 6.1; rv:32.0) Gecko/20100101 Firefox/32.0");
			client.setRedirectStrategy(new LaxRedirectStrategy());
			HttpResponse itineraryCreate = client.execute(initiateBookingPostCall);	
			Cook = new BasicCookieStore();
			cookiesB2C = client.getCookieStore().getCookies();
			int cookieSSize=cookiesB2C.size();
			for(int i=0;i<=cookieSSize-1;i++){
				////System.out.println(cookiesMob.get(i));
				Cook.addCookie(cookiesB2C.get(i));
			}
			BasicClientCookie cookie;
			cookie = new BasicClientCookie("_session_id", "BAh7BkkiD3Nlc3Npb25faWQGOgZFRiIlZmZhNDFkYWJjMWViYTNmNTE3ZTkzOGUwOGJlN2RlMzI%3D--0b0fd64213dff0a5ba15ddc986f74f9c7adf844d");
			cookie.setDomain(".cleartrip.com");
			cookie.setPath("/");
			Cook.addCookie(cookie);

			cookie = new BasicClientCookie("tkn", "1428409034");
			cookie.setDomain(".cleartrip.com");
			cookie.setPath("/");
			Cook.addCookie(cookie);

			cookie = new BasicClientCookie("usermisc", "SIGNED_IN%7C");
			cookie.setDomain(".cleartrip.com");
			cookie.setPath("/");
			Cook.addCookie(cookie);

			cookie = new BasicClientCookie("userid", "automation%40cleartrip.com");
			cookie.setDomain(".cleartrip.com");
			cookie.setPath("/");
			Cook.addCookie(cookie);

			cookie = new BasicClientCookie("ct-auth", "l9lx4ikgX9ynZqguXElowgjFfIGQAkNUBdoFS%2F3XYIFIpUB8%2FTL1kB75eMwx9bIYiqbzNRhJMu8vl6qhfb4T4bnSVLhXO56msrvEZ%2BeEkmi4CMJDuZMqCqXOFgqBXseomIxxk%2BIXnNdm3IA5zsr4Euaeb9AM8k0T%2BAAM5pjFUVrpiCIisaXwkpqzf2vb57X%2FbeNX%2FRMf%2FF%2BjdPPyyT8v1dW6jIO5AzstkJ%2FFipYTLH36jrEYH3sD5oB3ZOA%2FODe0n0%2FPjL8sr31DynLRmGtyBQfeXOaG4VF23I%2BwKUpiQlrNI%2B32fBku3VXU2%2F8OuO34rw4JunSdqM0eOkX%2Br3XQZw%3D%3D");
			cookie.setDomain(".cleartrip.com");
			cookie.setPath("/");
			Cook.addCookie(cookie);

			BufferedReader detailJsonDatabr = new BufferedReader(new InputStreamReader((itineraryCreate.getEntity().getContent())));
			String detailJsonDataResult;
			StringBuilder detailJsonDataResultbuilder = new StringBuilder();
			while ((detailJsonDataResult = detailJsonDatabr.readLine()) != null) {
				detailJsonDataResultbuilder.append(detailJsonDataResult);
			}
			////System.out.println(detailJsonDataResultbuilder.toString());
			itineraryID= detailJsonDataResultbuilder.toString().split("var iti=\"")[1].split("\"")[0];
			//System.out.println(itineraryID);

			EntityUtils.consumeQuietly(itineraryCreate.getEntity());
			initiateBookingPostCall.releaseConnection();
		} catch (Exception e) {
			itineraryID= "";
		}
		return itineraryID;
	}




	@SuppressWarnings({ "resource" })
	public  String login(String Adults, String Child, String Infant,String itineraryID ) throws URISyntaxException, IOException, JSONException {
		DefaultHttpClient client = new DefaultHttpClient();
		URI url = new URI("https://"+host+"/flights/itinerary/"+itineraryID+"/user");
		//System.out.println(url);
		HttpPost loginPostCall = new HttpPost(url);	
		String activityData="caller=bookstep2&subscribe_action=Y&auth_action=Y&username=automation%40cleartrip.com&persistent_login=t&rnum=16024606&adults=1&children=0&isRegistered=yes&infants=0&password=dontask";
		StringEntity input1 = new StringEntity(activityData);
		loginPostCall.setEntity(input1);	
		loginPostCall.setHeader("content-type", "application/x-www-form-urlencoded");
		loginPostCall.setHeader("X-Requested-With", "XMLHttpRequest");
		loginPostCall.setHeader("Accept", "text/json");
		loginPostCall.setHeader("user-agent", "User-Agent Mozilla/5.0 (Windows NT 6.1; rv:32.0) Gecko/20100101 Firefox/32.0");
		client.setRedirectStrategy(new LaxRedirectStrategy());
		HttpResponse itineraryCreate = client.execute(loginPostCall);	
		Cook = new BasicCookieStore();
		cookiesB2C = client.getCookieStore().getCookies();
		int cookieSSize=cookiesB2C.size();
		for(int i=0;i<=cookieSSize-1;i++){
			Cook.addCookie(cookiesB2C.get(i));
		}
		BufferedReader detailJsonDatabr = new BufferedReader(new InputStreamReader((itineraryCreate.getEntity().getContent())));
		String detailJsonDataResult;
		StringBuilder detailJsonDataResultbuilder = new StringBuilder();
		while ((detailJsonDataResult = detailJsonDatabr.readLine()) != null) {
			detailJsonDataResultbuilder.append(detailJsonDataResult);
		}
		String Response = detailJsonDataResultbuilder.toString();
		JSONObject Data = new JSONObject(Response);
		JSONObject user_details = Data.getJSONObject("user_details");
		String userid = user_details.getString("userid");
		EntityUtils.consumeQuietly(itineraryCreate.getEntity());
		loginPostCall.releaseConnection();
		return userid;

	}

	@SuppressWarnings("resource")
	public String wallet(String itineraryID ) throws IllegalStateException, IOException, JSONException {
		DefaultHttpClient client = new DefaultHttpClient();
		String walletUrl="https://"+host+"/book/fetchWalletBalance?itinerary_id="+itineraryID+"&user_id=7892488";
		//System.out.println(walletUrl);
		HttpGet get = new HttpGet(walletUrl);
		client.setRedirectStrategy(new LaxRedirectStrategy());
		client.setCookieStore(getCookies());
		HttpResponse response = client.execute(get);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		String Response = result.toString();
		//System.out.println(Response);
		JSONObject Data = new JSONObject(Response);
		String balance = Data.getString("ct_user_wallet_balance");
		EntityUtils.consumeQuietly(response.getEntity());
		get.releaseConnection();
		return balance;
	}


	@SuppressWarnings({ "resource" })
	public  boolean updateTravellers(String itineraryID ) throws URISyntaxException, IOException, JSONException {
		DefaultHttpClient client = new DefaultHttpClient();
		URI url = new URI("https://"+host+"/flights/itinerary/"+itineraryID+"/seat-sell-2");
		//System.out.println(url);
		HttpPost travellerPostCall = new HttpPost(url);	
		String activityData="first_name=Test&title=Mrs&mobileNum=9620317319&last_name=Test4";
		StringEntity input1 = new StringEntity(activityData);
		travellerPostCall.setEntity(input1);
		travellerPostCall.setHeader("content-type", "application/x-www-form-urlencoded");
		travellerPostCall.setHeader("X-Requested-With", "XMLHttpRequest");
		travellerPostCall.setHeader("Accept", "*/*");
		travellerPostCall.setHeader("user-agent", "User-Agent Mozilla/5.0 (Windows NT 6.1; rv:32.0) Gecko/20100101 Firefox/32.0");
		client.setRedirectStrategy(new LaxRedirectStrategy());
		client.setCookieStore(getCookies());
		HttpResponse itineraryCreate = client.execute(travellerPostCall);	
		BufferedReader detailJsonDatabr = new BufferedReader(new InputStreamReader((itineraryCreate.getEntity().getContent())));
		String detailJsonDataResult;
		StringBuilder detailJsonDataResultbuilder = new StringBuilder();
		while ((detailJsonDataResult = detailJsonDatabr.readLine()) != null) {
			detailJsonDataResultbuilder.append(detailJsonDataResult);
		}
		String Response = detailJsonDataResultbuilder.toString();
		boolean status =false;
		if(Response.contains("{}")){
			status=true;
		}else{
			status=false; 
		}
		EntityUtils.consumeQuietly(itineraryCreate.getEntity());
		travellerPostCall.releaseConnection();
		return status;
	}


	@SuppressWarnings({ "resource" })
	public  String payment(String itineraryID ) throws URISyntaxException, IOException, JSONException {
		String status="false";
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			URI url = new URI("https://"+host+"/flights/itinerary/"+itineraryID+"/advanced-prepayment");
			//System.out.println(url);
			HttpPost paymentPostCall = new HttpPost(url);		
			String activityData="product=AIR&insurance=false&store_card=false&ts=1612745&ct_user_wallet_enabled=true&ct_user_wallet_currency=INR&&travellerUpdate=true&booking_id=&AdultTitle1=Mrs&AdultFname1=Test&AdultLname1=Test&AdultId1=14013894&AdultNname1=&contact1=9620317319&contactType1=mobile×tamp=&username=automation%40cleartrip.com&userid=7892488&ShipAddress1=&ShipCity=&ShipState=&ShipCountry=&ShipPinCode=&callPrepayment=Y";
			StringEntity input1 = new StringEntity(activityData);
			paymentPostCall.setEntity(input1);
			paymentPostCall.setHeader("content-type", "application/x-www-form-urlencoded");
			paymentPostCall.setHeader("X-Requested-With", "XMLHttpRequest");
			paymentPostCall.setHeader("Accept", "*/*");
			paymentPostCall.setHeader("user-agent", "User-Agent Mozilla/5.0 (Windows NT 6.1; rv:32.0) Gecko/20100101 Firefox/32.0");
			client.setRedirectStrategy(new LaxRedirectStrategy());
			client.setCookieStore(getCookies());
			HttpResponse itineraryCreate = client.execute(paymentPostCall);	
			BufferedReader detailJsonDatabr = new BufferedReader(new InputStreamReader((itineraryCreate.getEntity().getContent())));
			String detailJsonDataResult;
			StringBuilder detailJsonDataResultbuilder = new StringBuilder();
			while ((detailJsonDataResult = detailJsonDatabr.readLine()) != null) {
				detailJsonDataResultbuilder.append(detailJsonDataResult);
			}
			String Response = detailJsonDataResultbuilder.toString();
			JSONObject Data = new JSONObject(Response);
			 status = Data.getString("status");
			EntityUtils.consumeQuietly(itineraryCreate.getEntity());
			paymentPostCall.releaseConnection();
			return status;
		} catch (IllegalStateException e) {
			return status;
		}
	}
	
	
	@SuppressWarnings({ "resource" })
	public  String paymentRetry(String itineraryID ) throws URISyntaxException, IOException, JSONException {
		String status="false";
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			URI url = new URI("https://"+host+"/flights/itinerary/"+itineraryID+"/advanced-prepayment");
			//System.out.println(url);
			HttpPost paymentPostCall = new HttpPost(url);		
			String activityData="product=AIR&store_card=false&ts=123248832&ct_user_wallet_enabled=true&ct_user_wallet_currency=INR&travellerUpdate=false&callPrepayment=Y";
			StringEntity input1 = new StringEntity(activityData);
			paymentPostCall.setEntity(input1);
			paymentPostCall.setHeader("content-type", "application/x-www-form-urlencoded");
			paymentPostCall.setHeader("X-Requested-With", "XMLHttpRequest");
			paymentPostCall.setHeader("Accept", "*/*");
			paymentPostCall.setHeader("user-agent", "User-Agent Mozilla/5.0 (Windows NT 6.1; rv:32.0) Gecko/20100101 Firefox/32.0");
			client.setRedirectStrategy(new LaxRedirectStrategy());
			client.setCookieStore(getCookies());
			HttpResponse itineraryCreate = client.execute(paymentPostCall);	
			BufferedReader detailJsonDatabr = new BufferedReader(new InputStreamReader((itineraryCreate.getEntity().getContent())));
			String detailJsonDataResult;
			StringBuilder detailJsonDataResultbuilder = new StringBuilder();
			while ((detailJsonDataResult = detailJsonDatabr.readLine()) != null) {
				detailJsonDataResultbuilder.append(detailJsonDataResult);
			}
			String Response = detailJsonDataResultbuilder.toString();
			JSONObject Data = new JSONObject(Response);
			 status = Data.getString("status");
			EntityUtils.consumeQuietly(itineraryCreate.getEntity());
			paymentPostCall.releaseConnection();
			return status;
		} catch (IllegalStateException e) {
			return status;
		}
	}


	@SuppressWarnings("resource")
	public String confirmation(String itineraryID ) throws IllegalStateException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		String Confirmation="https://"+host+"/flights/itinerary/"+itineraryID+"/book";
		//System.out.println(Confirmation);
		HttpPost getConfirmation = new HttpPost(Confirmation);
		getConfirmation.setHeader("content-type", "application/x-www-form-urlencoded");
		getConfirmation.setHeader("Upgrade-Insecure-Requests", "1");
		getConfirmation.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		getConfirmation.setHeader("user-agent", "User-Agent Mozilla/5.0 (Windows NT 6.1; rv:32.0) Gecko/20100101 Firefox/32.0");
		client.setRedirectStrategy(new LaxRedirectStrategy());
		client.setCookieStore(getCookies());
		HttpResponse response = client.execute(getConfirmation);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		String tripId;
		try {
			tripId = result.toString().split("confirmation_number=")[1].split("&last_name")[0];
			//System.out.println(tripId);
		} catch (Exception e) {
			tripId="Z";
		}
		EntityUtils.consumeQuietly(response.getEntity());
		getConfirmation.releaseConnection();
		return tripId;
	}

	public void setCookies(CookieStore Cook) {
		this.Cook=Cook;
	}

	public CookieStore getCookies() {
		return this.Cook;
	}


	@SuppressWarnings({ "resource" })
	public  CookieStore hqLogin() throws URISyntaxException, IOException, JSONException {
		DefaultHttpClient client = new DefaultHttpClient();
		URI url = new URI("https://"+host+"/externalapi/signin");
		//System.out.println(url);
		HttpPost loginPostCall = new HttpPost(url);	
		String activityData="email=automation%40cleartrip.com&password=dontask&persistent_login=t&service=%2F&caller=homepage&source=ui&action_type=&trip_ref=";
		StringEntity input1 = new StringEntity(activityData);
		loginPostCall.setEntity(input1);	
		loginPostCall.setHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		loginPostCall.setHeader("X-Requested-With", "XMLHttpRequest");
		loginPostCall.setHeader("Accept", "text/json");
		loginPostCall.setHeader("X-Prototype-Version", "1.6.0_rc0");
		loginPostCall.setHeader("user-agent", "User-Agent Mozilla/5.0 (Windows NT 6.1; rv:32.0) Gecko/20100101 Firefox/32.0");
		client.setRedirectStrategy(new LaxRedirectStrategy());
		HttpResponse itineraryCreate = client.execute(loginPostCall);	
		CookieStore cookieStoreHQ = new BasicCookieStore();
		cookiesHQ = client.getCookieStore().getCookies();
		int cookieSSize=cookiesHQ.size();
		for(int i=0;i<=cookieSSize-1;i++){
			////System.out.println(cookiesHQ.get(i));
			cookieStoreHQ.addCookie(cookiesHQ.get(i));
		}
	/*	BufferedReader detailJsonDatabr = new BufferedReader(new InputStreamReader((itineraryCreate.getEntity().getContent())));
		String detailJsonDataResult;
		StringBuilder detailJsonDataResultbuilder = new StringBuilder();
		while ((detailJsonDataResult = detailJsonDatabr.readLine()) != null) {
			detailJsonDataResultbuilder.append(detailJsonDataResult);
		}
		String Response = detailJsonDataResultbuilder.toString();*/
		EntityUtils.consumeQuietly(itineraryCreate.getEntity());
		loginPostCall.releaseConnection();
		return cookieStoreHQ;

	}

	@SuppressWarnings("resource")
	public String addWalletMoney(CookieStore cookieStoreHQ) throws IllegalStateException, IOException, JSONException {
		DefaultHttpClient client = new DefaultHttpClient();
		String walletUrl="https://"+host+"/hq/people/wallet_txn?walletId=3662&event=MANUAL_REFUND&amount=10000&tripId=Q1509290140&expiryDate=dd%2Fmm%2Fyyyy&transaction_type=CREDIT&personId=7892488&currency=INR";
		//System.out.println(walletUrl);
		HttpGet get = new HttpGet(walletUrl);
		client.setRedirectStrategy(new LaxRedirectStrategy());
		client.setCookieStore(cookieStoreHQ);
		HttpResponse response = client.execute(get);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		String Response = result.toString();
		JSONObject Data = new JSONObject(Response);
		String status = Data.getString("status");
		EntityUtils.consumeQuietly(response.getEntity());
		get.releaseConnection();
		return status;
	}



}
