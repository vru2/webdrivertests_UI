package testScriptsJsonExternalAPI;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
//import org.apache.james.mime4j.field.datetime.DateTime;
import org.json.JSONException;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import testScriptsExternalAPICommon.CommonUtils;

public class SignInAPI extends CommonUtils {
	@Test
	public void test() throws URISyntaxException, IllegalStateException, IOException, JSONException, InterruptedException {
		String ct_auth = null;
		String tkn = null;
		String usermisc = null;
		String userid = null;
		HttpPost post = new HttpPost(new URI("https://qa2.cleartrip.com/externalapi/signin"));
		HttpClient client = new DefaultHttpClient();
		// post.setHeader("X-CT-API-KEY","g9s45bsammqggtczpz3kj3qk");
		post.setHeader("Accept", "text/json");
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("email", "cleartripautomation@gmail.com"));
		urlParameters.add(new BasicNameValuePair("password", "cleartripbangalore"));
		urlParameters.add(new BasicNameValuePair("persistent_login", "t"));
		urlParameters.add(new BasicNameValuePair("caller", "homepage"));
		urlParameters.add(new BasicNameValuePair("source", "ui"));
		// urlParameters.add(new BasicNameValuePair("Accept", "text/json"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(post);
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			if (header.getValue().startsWith("ct-auth=")) {
				ct_auth = header.getValue().substring(8).split(";")[0];
			}
			if (header.getValue().startsWith("tkn=")) {
				tkn = header.getValue().substring(4).split(";")[0];
			}
			if (header.getValue().startsWith("usermisc=")) {
				usermisc = header.getValue().substring(9).split(";")[0];
			}
			if (header.getValue().startsWith("userid=")) {
				userid = header.getValue().substring(7).split(";")[0];
			}
			System.out.println("Key : " + header.getName() + " ,Value : " + header.getValue());
		}

		RemoteWebDriver driver = null;
		driver = getDriver(driver);
		driver.get(getBaseUrl("com"));

		Date date = new Date(127, 1, 1, 1, 1);
		Cookie name = new Cookie("ct-auth", ct_auth, "qa2.cleartrip.com", "/", date);
		driver.manage().addCookie(name);

		if (tkn != null) {
			name = new Cookie("tkn", tkn, "qa2.cleartrip.com", "/", date);
			driver.manage().addCookie(name);
		}

		name = new Cookie("usermisc", usermisc, "qa2.cleartrip.com", "/", date);
		driver.manage().addCookie(name);

		name = new Cookie("userid", userid, "qa2.cleartrip.com", "/", date);
		driver.manage().addCookie(name);

		// After adding the cookie we will check that by displaying all the cookies.
		/*
		 * Set<Cookie> cookiesList = driver.manage().getCookies(); for(Cookie getcookies :cookiesList) {
		 * System.out.println(getcookies ); }
		 */

		driver.get(getBaseUrl("com"));
	}
}