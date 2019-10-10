package testScriptsExternalAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testScriptsExternalAPICommon.CommonUtils;


@SuppressWarnings("deprecation")
public class API_Connector_Search_SG extends CommonUtils{

	
	
  @Test      	  
 public void connector() throws Exception {
    	DefaultHttpClient Client = new DefaultHttpClient();
    	String onwarddate = getConnectorModifiedDate(common.value("Days_to_add_for_CurrentDate"));
    	
    	System.out.println("http://172.17.12.71:9080/airservice/search?from=BOM&to=DEL&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline=SG&carrier=SG&intl=n&src=connector&debug=1&responseType=jsonV3&source=API");
		String searchUrl="http://172.17.12.71:9080/airservice/search?from=BOM&to=DEL&depart_date="+onwarddate+"&adults=1&childs=0&infants=0&class=Economy&airline=SG&carrier=SG&intl=n&src=connector&debug=1&responseType=jsonV3&source=API";
		/*Reporter.log( "R3"+ connectortype + " searchUrl :" + searchUrl);
		System.out.println("envtbeta is on -"+ common.value("envtbeta") + connectortype + " searchUrl :" + searchUrl);*/
		
		
		HttpGet GetResults = new HttpGet(searchUrl);
		
		HttpResponse response = Client.execute(GetResults); 
		
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "Response code");
		
		HttpEntity entity = response.getEntity();
		Document doc =null;
		StringBuffer sb1 = new StringBuffer();
		String str1="";
		BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str1=br1.readLine())!=null){
		   sb1.append(str1);
		}
		  
		//System.out.println(sb1);
		
		JSONObject jsonObject = new JSONObject(sb1.toString());
		System.out.println(jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("content");
		     
		String fk = airSearchResult.getJSONObject(String.valueOf(1)).getString("fk");
		     
		System.out.println("Fare Key Present : " + fk);
		Assert.assertTrue(fk.contains("SG"), "fare key not present");
		
 }
        
    
}




