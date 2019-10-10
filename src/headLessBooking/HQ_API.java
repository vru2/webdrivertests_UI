package headLessBooking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class HQ_API {
	protected  List<Cookie> cookiesHQ = null;
	CookieStore cookHQ;
	String host = "qa2.cleartrip.com";


	

	public void setCookies(CookieStore cookHQ) {
		this.cookHQ=cookHQ;
	}

	public CookieStore getCookies() {
		return this.cookHQ;
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
		cookHQ = new BasicCookieStore();
		cookiesHQ = client.getCookieStore().getCookies();
		int cookieSSize=cookiesHQ.size();
		for(int i=0;i<=cookieSSize-1;i++){
			////System.out.println(cookiesHQ.get(i));
			cookHQ.addCookie(cookiesHQ.get(i));
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
		return cookHQ;

	}

	@SuppressWarnings("resource")
	public String addWalletMoney() throws IllegalStateException, IOException, JSONException {
		DefaultHttpClient client = new DefaultHttpClient();
		String walletUrl="https://"+host+"/hq/people/wallet_txn?walletId=3662&event=MANUAL_REFUND&amount=10000&tripId=Q1509290140&expiryDate=dd%2Fmm%2Fyyyy&transaction_type=CREDIT&personId=7892488&currency=INR";
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
		JSONObject Data = new JSONObject(Response);
		String status = Data.getString("status");
		EntityUtils.consumeQuietly(response.getEntity());
		get.releaseConnection();
		return status;
	}



}
