package commonServices;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class APIUtils extends CommonUtil{

	public static HttpResponse getApi(String url, HashMap<String, String> headers, int statuscode)
	
			throws ClientProtocolException, IOException {
		Reporter.log(url);
	//	System.out.println(url);
		final HttpClient client = new DefaultHttpClient();
		addStaticLog("The request url is : "+url,true);
		// HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		// request.setHeader("User-Agent", USER_AGENT);
		if (headers != null) {
			Set<Map.Entry<String, String>> set = headers.entrySet();
			Iterator<Entry<String, String>> i = set.iterator();
			while (i.hasNext()) {
				Map.Entry<String, String> me = (Map.Entry<String, String>) i.next();
	//	System.out.println("key= "+me.getKey().toString()+"                    value="+me.getValue().toString());
				request.addHeader(me.getKey().toString(), me.getValue().toString());
			}
		}
		
	    
		HttpResponse response = client.execute(request);
		
		HttpParams params = client.getParams();
		   HttpConnectionParams.setConnectionTimeout(params,180000);
		    HttpConnectionParams.setSoTimeout(params,180000);
		int hitStatus = response.getStatusLine().getStatusCode();
		
		addStaticLog("Http response Staus of the given get Request url is : "+hitStatus,true);
		//Assert.assertEquals(hitStatus,statuscode,"Response code is="+hitStatus);
		//System.out.println(response);
		return response;

		/*
		 * StringBuffer result = new StringBuffer(); GetStatusCode = response.getStatusLine().getStatusCode();
		 * 
		 * if (GetStatusCode == statuscode) { BufferedReader rd = null;
		 * 
		 * rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		 * 
		 * String line = ""; while ((line = rd.readLine()) != null) { result.append(line);
		 * 
		 * } } else { Reporter.log("No Proper Response: " + response.getStatusLine().getStatusCode() + ", for the url: " + url); }
		 * return result.toString();
		 */
	}
	
	public static HttpResponse getApiWithAuth(String url, HashMap<String, String> headers, int statuscode)

		      throws ClientProtocolException, IOException {

		   CredentialsProvider provider = new BasicCredentialsProvider();
		   UsernamePasswordCredentials credentials
		         = new UsernamePasswordCredentials("UN", "pwd");
		   provider.setCredentials(AuthScope.ANY, credentials);

//		   addStaticLog("The request url is : "+url,true);
		   // System.out.println(url);
		   final HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		   // HttpClient client = HttpClientBuilder.create().build();
		   HttpGet request = new HttpGet(url);
		   // request.setHeader("User-Agent", USER_AGENT);
		   if (headers != null) {
		      Set<Map.Entry<String, String>> set = headers.entrySet();
		      Iterator<Entry<String, String>> i = set.iterator();
		      while (i.hasNext()) {
		         Map.Entry<String, String> me = (Map.Entry<String, String>) i.next();
		         // System.out.println("key= "+me.getKey().toString()+"                    value="+me.getValue().toString());
		         request.addHeader(me.getKey().toString(), me.getValue().toString());
		      }
		   }


		   HttpResponse response = client.execute(request);
		   int hitStatus = response.getStatusLine().getStatusCode();

		   addStaticLog("The request url is : "+url+" and the response status for the same is: "+hitStatus,true);
		   return response;
		}

	public static HttpResponse postApi(String url, HashMap<String, String> headers, String payload, int statuscode)
			throws ClientProtocolException, IOException {
		Reporter.log(url);
		final HttpClient client = new DefaultHttpClient();
		// HttpClient client = HttpClientBuilder.create().build();
		HttpPost postrequest = new HttpPost(url);

		if (headers != null) {
			Set<Map.Entry<String, String>> set = headers.entrySet();
			Iterator<Entry<String, String>> i = set.iterator();
			while (i.hasNext()) {
				Map.Entry<String, String> me = (Map.Entry<String, String>) i.next();
				/*System.out.println("Header:" + me.getKey() + ": " + me.getValue());
				Reporter.log("Header:" + me.getKey() + ": " + me.getValue());*/
				postrequest.addHeader(me.getKey().toString(), me.getValue().toString());
				// headers.put(me.getKey().toString(),me.getValue().toString());
			}
		}

		if (payload != null) {
			StringEntity payLoad = new StringEntity(payload);
			postrequest.setEntity(payLoad);
		}
		//System.out.println(payload);
		HttpResponse response = client.execute(postrequest);
		HttpParams params = client.getParams();
	    HttpConnectionParams.setConnectionTimeout(params,180000);
	    HttpConnectionParams.setSoTimeout(params, 180000);
		System.out.println(response);
		int hitStatus = response.getStatusLine().getStatusCode();
		
		Reporter.log("Http Staus for Itinerary response="+hitStatus);
		//Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
		System.out.println(response);
		return response;
		
		/*
		 * StringBuffer result = new StringBuffer(); PostStatusCode = response.getStatusLine().getStatusCode();
		 * 
		 * if (PostStatusCode == statuscode) {
		 * 
		 * BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		 * 
		 * String line = ""; while ((line = rd.readLine()) != null) { result.append(line); } } else {
		 * Reporter.log("No Proper Response: " + response.getStatusLine().getStatusCode() + ", for the url: " + url); } return
		 * result.toString();
		 */
	}

	public static HttpResponse putApi(String url, HashMap<String, String> headers, String payload, int statuscode)
			throws ClientProtocolException, IOException {
		final HttpClient client = new DefaultHttpClient();
		// HttpClient client = HttpClientBuilder.create().build();
		HttpPut putRequest = new HttpPut(url);

		// postrequest.setHeader("User-Agent", USER_AGENT);

		if (headers != null) {
			Set<Map.Entry<String, String>> set = headers.entrySet();
			Iterator<Entry<String, String>> i = set.iterator();
			while (i.hasNext()) {
				Map.Entry<String, String> me = (Map.Entry<String, String>) i.next();
				Reporter.log(me.getKey() + ": " + me.getValue());
				putRequest.addHeader(me.getKey().toString(), me.getValue().toString());

			}
		}

		StringEntity payLoad = new StringEntity(payload);
		putRequest.setEntity(payLoad);

		HttpResponse response = client.execute(putRequest);
		return response;
		/*
		 * StringBuffer result = new StringBuffer(); PutStatusCode = response.getStatusLine().getStatusCode();
		 * Reporter.log("Response Code : " + PutStatusCode);
		 * 
		 * if (PutStatusCode == statuscode) {
		 * 
		 * BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		 * 
		 * String line = ""; while ((line = rd.readLine()) != null) { result.append(line); } } else {
		 * Reporter.log("No Proper Response: " + response.getStatusLine().getStatusCode() + ", for the url: " + url); } return
		 * result.toString();
		 */
	}

	// send httpResponse as String and xpath of node to search, returns list of Nodes
	public static NodeList getNodeListFromXMLResponseString(String response, String xpath) throws Exception {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new ByteArrayInputStream(response.getBytes("utf-8"))));
		return (NodeList) XPathFactory.newInstance().newXPath().compile(xpath).evaluate(doc, XPathConstants.NODESET);
	}

	// n - node to search in, xpath - xpath of the node to be searched inside node, returns text of found node
	public static String getTextContentOfNodeInNode(Node n, String xpath) throws Exception {
		// Either cast to Node or Element
		return ((Element) searchNodeInNode(n, xpath)).getTextContent();
	}

	// returns the node found by given xpath inside the given node n
	public static Node searchNodeInNode(Node n, String xpath) throws Exception {
		return (Node) XPathFactory.newInstance().newXPath().compile(xpath).evaluate(n, XPathConstants.NODE);
	}

	// returns the node list found by given xpath inside the given node n
	public static NodeList getNodeListInNode(Node n, String xpath) throws Exception {
		return (NodeList) XPathFactory.newInstance().newXPath().compile(xpath).evaluate(n, XPathConstants.NODESET);
	}

	// send HttpResponse object and it returns it in form of String
	public static String returnResponseAsString(HttpResponse response) throws IOException {
		BufferedReader rd;
		StringBuffer result = new StringBuffer();
		rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		//System.out.println("response of string=="+result.toString());
		return result.toString();
	}

	public static HashMap<String, String> getExtAPIHeaders(String apiKey, String source) {
		HashMap<String, String> headers = null;
		headers = new HashMap<String, String>();
		headers.put("X-CT-API-KEY", apiKey);
		headers.put("X-CT-SOURCE-TYPE", source);
		return headers;
	}
	
	public static HashMap<String, String> getIVRAPIHeaders() {
		HashMap<String, String> headers = null;
		headers = new HashMap<String, String>();
		headers.put("ct-gn-auth", "dc7cfd533f661f92ebf6dceaf9930c3dba977bb8");
		return headers;
	}
	
	public static HashMap<String, String> postIVRAPIHeaders() {
		HashMap<String, String> headers = null;
		headers = new HashMap<String, String>();
		headers.put("ct-gn-auth", "dc7cfd533f661f92ebf6dceaf9930c3dba977bb8");
		return headers;
	}
	
	/*public static HashMap<String, String> posttSalesforceHeaders() {
		HashMap<String, String> headers = null;
		headers = new HashMap<String, String>();
		headers.put("client-id", "3MVG9ZL0ppGP5UrDqeAQAd9DFWPxeZn5GHVHs69uM6w4TopIPH_aJxQsBdu7AhsaOBg05fuk1Bg8WkI2Vl3j5");
		headers.put("client-secret", "5196942279531708723");
		return headers;
	}*/
	public static HashMap<String, String> gettSalesforceHeaders() {
		HashMap<String, String> headers = null;
		headers = new HashMap<String, String>();
		headers.put("client-id", "3MVG9ZL0ppGP5UrDqeAQAd9DFWPxeZn5GHVHs69uM6w4TopIPH_aJxQsBdu7AhsaOBg05fuk1Bg8WkI2Vl3j5");
		headers.put("client-secret", "5196942279531708723");
		return headers;
	}
	public static HashMap<String, String> postSalesforceHeaders()
	{
		HashMap<String, String> headers =null;
		headers = new HashMap<String, String>();
		headers.put("client-id", "3MVG9ZL0ppGP5UrDqeAQAd9DFWPxeZn5GHVHs69uM6w4TopIPH_aJxQsBdu7AhsaOBg05fuk1Bg8WkI2Vl3j5");
		headers.put("client-secret", "5196942279531708723");
		headers.put("Src-Type", "png");
		headers.put("File-Name", "profile pick updation singledc");
		
		return headers;
	}
	
	
	
	
	
	

	public static JSONObject returnResponseAsJsonObject(HttpResponse response) throws IOException, JSONException {
		StringBuffer sb1 = new StringBuffer();
		String str1 = "";
		BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while ((str1 = br1.readLine()) != null) {
			sb1.append(str1);                                                                                                     
		}
		JSONObject jsonObject = new JSONObject(sb1.toString());
		return jsonObject;
	}

}
