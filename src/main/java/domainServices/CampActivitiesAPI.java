package domainServices;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

public class CampActivitiesAPI {
	protected static List<Cookie> espCookies = null;
	protected static List<Cookie> samCookies = null;
	protected static List<Cookie> scmCookies = null;
	protected static List<Cookie> mmCookies = null;
	protected static List<Cookie> saCookies = null;
	CookieStore espCookieStore=null;
	CookieStore samCookieStore=null;
	CookieStore scmCookieStore=null;
	CookieStore mmCookieStore=null;
	CookieStore saCookieStore=null;
	

	public CookieStore espLogin() throws ClientProtocolException, IOException, JSONException {
		DefaultHttpClient Client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost("https://qa2.cleartrip.com/camp/accounts/sign_in");
		postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		postRequest.setHeader("Accept", "application/json");
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.setHeader("Host", "qa2.cleartrip.com");
		StringEntity input = new StringEntity("{\"email\":\"hilal27deen@gmail.com\",\"password\":\"cleartrip\"}");
		postRequest.setEntity(input);
		//Client.setRedirectStrategy(new LaxRedirectStrategy());
		HttpResponse response = Client.execute(postRequest);

		espCookieStore = new BasicCookieStore();
		espCookies = Client.getCookieStore().getCookies();
		int CTcookieSSize=espCookies.size();
		for(int i=0;i<=CTcookieSSize-1;i++){
			//System.out.println(espCookies.get(i));
			espCookieStore.addCookie(espCookies.get(i));
		}
		BasicClientCookie cookie;
		cookie = new BasicClientCookie("mob", "0");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		espCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+path", "%2F");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		espCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+expires", "Mon%2C+06-Feb-2017+10%3A55%3A55+GMT");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		espCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+domain", "accounts.cltp.com");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		espCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-ct-auth", "KNBNE2ODf5wY7pNH1FD5LBvE%2BE%2FgWEuCk97opRHqv7BwpC%2FzDSXLpmuf8DXMMhVx4QNefYeF5hn9x%2BrR1tpWd0WCG8fTxo8zMK%2ByZZo9KDTFdsZQvYzWb39HnPFCN378ZwofTuxS4aLNa5r7XWsCss%2FSsj6XB7cJufmRrrD264Y%3D");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		espCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-usermisc", "SIGNED_IN%7C");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		espCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-userid", "hilal27deen%40gmail.com%7C%7CM%7C%7C14028008");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		espCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-ct-auth-preferences", "%7C%7C");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		espCookieStore.addCookie(cookie);   

		cookie = new BasicClientCookie("_session_id", "BAh7CEkiD3Nlc3Npb25faWQGOgZFVEkiJTQxYTlhNjlhMzVlODU3YjM3MjMxMmQ4MTI1ZTM1NmRhBjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMVFQZ1FJSlRoR21hYU1BNDVCUmtvYXVoeTBQeFczellzY29lRGoxYnpJL0k9BjsARkkiCXN0ZXAGOwBGaQc%3D--927896db753f31c03df36486025935c2220c61a6");
		cookie.setDomain("qa2.cleartrip.com");
		cookie.setPath("/");
		espCookieStore.addCookie(cookie);

		/*if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String output;
		StringBuffer totalOutput = new StringBuffer();
		//System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			////System.out.println(output);
			totalOutput.append(output);
		}
		//System.out.println(totalOutput);*/
		EntityUtils.consumeQuietly(response.getEntity());
		postRequest.releaseConnection();
		return espCookieStore;
	}
	
	public CookieStore samLogin() throws ClientProtocolException, IOException, JSONException {
		DefaultHttpClient Client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost("https://qa2.cleartrip.com/camp/accounts/sign_in");
		postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		postRequest.setHeader("Accept", "application/json");
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.setHeader("Host", "qa2.cleartrip.com");
		StringEntity input = new StringEntity("{\"email\":\"sam1@cleartrip.com\",\"password\":\"demo1234\"}");
		postRequest.setEntity(input);
		//Client.setRedirectStrategy(new LaxRedirectStrategy());
		HttpResponse response = Client.execute(postRequest);

		samCookieStore = new BasicCookieStore();
		samCookies = Client.getCookieStore().getCookies();
		int CTcookieSSize=samCookies.size();
		for(int i=0;i<=CTcookieSSize-1;i++){
			//System.out.println(samCookies.get(i));
			samCookieStore.addCookie(samCookies.get(i));
		}
		BasicClientCookie cookie;
		cookie = new BasicClientCookie("mob", "0");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		samCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+path", "%2F");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		samCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+expires", "Mon%2C+06-Feb-2017+10%3A55%3A55+GMT");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		samCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+domain", "accounts.cltp.com");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		samCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-ct-auth", "sVCFfRAtCIV4qnljP2zEnsM%2BlalXsrizf37PbWxoBYyhYtR9BAvhhlkmX0yAozYfyn0S%2FnC2Upk3V1l9W96wqkDUn%2BaXlkeb5N0WcfXJqwzSd3yMYFefwaPq0ZUG%2BjLn6yx2uu6F%2BH7xIQ1JCluUGZusyeTsguNivc73HKKlGqcXctZo8O3fj7xCnHee3zEO");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		samCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-usermisc", "SIGNED_IN%7C");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		samCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-userid", "sam1%40cleartrip.com%7C%7CM%7C%7C13965458");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		samCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-ct-auth-preferences", "%7C%7C");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		samCookieStore.addCookie(cookie);   

		cookie = new BasicClientCookie("_session_id", "BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiJWQ2MzVjOTdmOWQ1MjA0YTE3NTlhZTMzNGI5ZWFkYzE0BjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMVd1VU5iT2lrY0w0VGNTT1hRRG1jOFo3ZXFBV1E0VHdrQTdYNjlBbmdTdmM9BjsARg%3D%3D--d8a0b852b5b60eff597222cb770f2bb85a0be5e5");
		cookie.setDomain("qa2.cleartrip.com");
		cookie.setPath("/");
		samCookieStore.addCookie(cookie);

		/*if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String output;
		StringBuffer totalOutput = new StringBuffer();
		//System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			////System.out.println(output);
			totalOutput.append(output);
		}
		//System.out.println(totalOutput);*/
		EntityUtils.consumeQuietly(response.getEntity());
		postRequest.releaseConnection();
		return samCookieStore;
	}
	
	public CookieStore scmLogin() throws ClientProtocolException, IOException, JSONException {
		DefaultHttpClient Client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost("https://qa2.cleartrip.com/camp/accounts/sign_in");
		postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		postRequest.setHeader("Accept", "application/json");
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.setHeader("Host", "qa2.cleartrip.com");
		StringEntity input = new StringEntity("{\"email\":\"scm1@cleartrip.com\",\"password\":\"demo1234\"}");
		postRequest.setEntity(input);
		//Client.setRedirectStrategy(new LaxRedirectStrategy());
		HttpResponse response = Client.execute(postRequest);

		scmCookieStore = new BasicCookieStore();
		scmCookies = Client.getCookieStore().getCookies();
		int CTcookieSSize=scmCookies.size();
		for(int i=0;i<=CTcookieSSize-1;i++){
			//System.out.println(scmCookies.get(i));
			scmCookieStore.addCookie(scmCookies.get(i));
		}
		BasicClientCookie cookie;
		cookie = new BasicClientCookie("mob", "0");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		scmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+path", "%2F");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		scmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+expires", "Mon%2C+06-Feb-2017+10%3A55%3A55+GMT");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		scmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+domain", "accounts.cltp.com");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		scmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-ct-auth", "5yMNvK1uVt%2F49vfgw5beN0jREESFDU%2F0KgeurFKzeQEO2wv%2FhkoT%2BMfcp8VsJTIzOPjrj2cqHgKO4L3TYRL9VYPaxrnmf%2BG4eXta7e36cu6J9eU%2BUiIqr7H%2BOXo9e6U5mV1rynaCbTMoDFLSzkPyGUozw5IFZz05%2B0vvN3eNF9VmNHMKYhgMYvCWC0NDSDjX6PcHZA%2Fu3ymBGcQiUOkwMg%3D%3D");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		scmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-usermisc", "SIGNED_IN%7C");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		scmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-userid", "scm1%40cleartrip.com%7CSuper+Content+Manager%7CM%7C%7C13965460");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		scmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-ct-auth-preferences", "%7C%7C");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		scmCookieStore.addCookie(cookie);   

		cookie = new BasicClientCookie("_session_id", "BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiJWEwOGVhOGU1NGY0ZmFlYmQ0ZmQ2YmExY2RlNTAyMDAxBjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMWdFQkxocnliSXQ4emRFNWQvQ1dXN0lFTGhqUTBFK3FFbjR6TkFwYmx6Qlk9BjsARg%3D%3D--26c542585763b1cf99b2c838800bf3c03fca1973");
		cookie.setDomain("qa2.cleartrip.com");
		cookie.setPath("/");
		scmCookieStore.addCookie(cookie);

		/*if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String output;
		StringBuffer totalOutput = new StringBuffer();
		//System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			////System.out.println(output);
			totalOutput.append(output);
		}
		//System.out.println(totalOutput);*/
		EntityUtils.consumeQuietly(response.getEntity());
		postRequest.releaseConnection();
		return scmCookieStore;
	}
	
	public CookieStore mmLogin() throws ClientProtocolException, IOException, JSONException {
		DefaultHttpClient Client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost("https://qa2.cleartrip.com/camp/accounts/sign_in");
		postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		postRequest.setHeader("Accept", "application/json");
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.setHeader("X-CSRF-Token", "QPgQIJThGmaaMA45BRkoauhy0PxW3zYscoeDj1bzI/I=");
		postRequest.setHeader("Host", "qa2.cleartrip.com");
		StringEntity input = new StringEntity("{\"email\":\"mm1@cleartrip.com\",\"password\":\"demo1234\"}");
		postRequest.setEntity(input);
		//Client.setRedirectStrategy(new LaxRedirectStrategy());
		HttpResponse response = Client.execute(postRequest);

		mmCookieStore = new BasicCookieStore();
		mmCookies = Client.getCookieStore().getCookies();
		int CTcookieSSize=mmCookies.size();
		for(int i=0;i<=CTcookieSSize-1;i++){
			//System.out.println(mmCookies.get(i));
			mmCookieStore.addCookie(mmCookies.get(i));
		}
		BasicClientCookie cookie;
		cookie = new BasicClientCookie("mob", "0");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		mmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+path", "%2F");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		mmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+expires", "Mon%2C+06-Feb-2017+10%3A55%3A55+GMT");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		mmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+domain", "accounts.cltp.com");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		mmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-ct-auth", "YKk8uhIAigb2UpEce%2Bdr41%2Bm6ci3zho9RxlQjGwK%2FJkvW%2B23nUcsj8oI3yqNGZGF9mQqzRmGAaD7cvSVen%2BvGKFfieSNhPv4qwoWw49HKc7HP9YBEe%2BF6CS6xUjtZjdyqN8QGH4Jwad%2FrrgXOFPhd9R4S44WPT0MpBTTsczMgKKm0CEu7nhbWsHQr7c1W10U");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		mmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-usermisc", "SIGNED_IN%7C");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		mmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-userid", "mm1%40cleartrip.com%7C%7CM%7C%7C13965464");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		mmCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-ct-auth-preferences", "%7C%7C");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		mmCookieStore.addCookie(cookie);   

		cookie = new BasicClientCookie("_session_id", "BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiJTE1MmY0MDJjZGQxMzEwZGZlNjNmOWVjMzVjYTVlNjljBjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMUZ6d3k0WFhJaWo3aXIycWVYU2xHQ2RqUWxmUEFNcDB4S2l3bmhybTdrODA9BjsARg%3D%3D--d115a8b5eb477e8e5d0c663ba667646149a804dd");
		cookie.setDomain("qa2.cleartrip.com");
		cookie.setPath("/");
		mmCookieStore.addCookie(cookie);

		/*if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String output;
		StringBuffer totalOutput = new StringBuffer();
		//System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			////System.out.println(output);
			totalOutput.append(output);
		}
		//System.out.println(totalOutput);*/
		EntityUtils.consumeQuietly(response.getEntity());
		postRequest.releaseConnection();
		return mmCookieStore;
	}
	
	public CookieStore saLogin() throws ClientProtocolException, IOException, JSONException {
		DefaultHttpClient Client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost("https://qa2.cleartrip.com/camp/accounts/sign_in");
		postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		postRequest.setHeader("Accept", "application/json");
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.setHeader("X-CSRF-Token", "QPgQIJThGmaaMA45BRkoauhy0PxW3zYscoeDj1bzI/I=");
		postRequest.setHeader("Host", "qa2.cleartrip.com");
		StringEntity input = new StringEntity("{\"email\":\"sa1@cleartrip.com\",\"password\":\"demo1234\"}");
		postRequest.setEntity(input);
		//Client.setRedirectStrategy(new LaxRedirectStrategy());
		HttpResponse response = Client.execute(postRequest);

		saCookieStore = new BasicCookieStore();
		saCookies = Client.getCookieStore().getCookies();
		int CTcookieSSize=saCookies.size();
		for(int i=0;i<=CTcookieSSize-1;i++){
			////System.out.println(saCookies.get(i));
			saCookieStore.addCookie(saCookies.get(i));
		}
		BasicClientCookie cookie;
		cookie = new BasicClientCookie("mob", "0");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		saCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+path", "%2F");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		saCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+expires", "Mon%2C+06-Feb-2017+10%3A55%3A55+GMT");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		saCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("+domain", "accounts.cltp.com");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		saCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-ct-auth", "naZA3R%2FVAAQhh0qTY52GRKbW624mcTmJEXZLgCYmUZ9nVNi8uv4b1DVoOrzVc%2B1vu6QWzjRN9zH3dyQk2BpkpKFfieSNhPv4qwoWw49HKc43nWzkSsAOPIeWkLbFxkPpEKw6S9089oLiJyQE%2Bjqo3MuQAQu8O61oPaL2d4f5fTBt0O3Uy8H9Nm7EGfWNjtzg"
				+ "");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		saCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-usermisc", "SIGNED_IN%7C");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		saCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-userid", "hilaludeen.m%40cleartrip.com%7C%7CM%7C%7C14035166");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		saCookieStore.addCookie(cookie);

		cookie = new BasicClientCookie("camp-ct-auth-preferences", "%7C%7C");
		cookie.setDomain(".cleartrip.com");
		cookie.setPath("/");
		saCookieStore.addCookie(cookie);   

		cookie = new BasicClientCookie("_session_id", "BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiJThiYThhZDJmNGI1NDkwOWI3N2IwY2UyOTEwOTVjMTdkBjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMWpyNmgrdlZsMTVkM0dJS0NJbkdwTFp5TmIzL1o5N1ZXTFpuN05YUlVlTlU9BjsARg%3D%3D--3062f80d45c9dfadbca3aa6f0e2ae6072fc64afd");
		cookie.setDomain("qa2.cleartrip.com");
		cookie.setPath("/");
		saCookieStore.addCookie(cookie);

		/*if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

		String output;
		StringBuffer totalOutput = new StringBuffer();
		//System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			////System.out.println(output);
			totalOutput.append(output);
		}
		//System.out.println(totalOutput);*/
		EntityUtils.consumeQuietly(response.getEntity());
		postRequest.releaseConnection();
		return saCookieStore;
	}

}
