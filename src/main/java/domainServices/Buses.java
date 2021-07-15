package domainServices;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;

import commonServices.WrapperMethod;

public class Buses extends WrapperMethod {

	
    public DefaultHttpClient httpclient = new DefaultHttpClient();
    public StringBuilder builder = new StringBuilder();
    public String smallWorldCityID = "";

    public void apiBusGetAliasListForCity(String [ ] aliasCity, int j, String url) throws IOException, ClientProtocolException {
        StringBuilder builder1 = null;
        HttpGet getRequest1 = new HttpGet(common.value("air_url") + url + aliasCity[j]);
        // //////System.out.println(getRequest1);
        HttpResponse httpResponse1 = httpclient.execute(getRequest1);
        BufferedReader br1 = new BufferedReader(new InputStreamReader((httpResponse1.getEntity().getContent())));
        String output1;

        while ((output1 = br1.readLine()) != null) {
            builder1 = new StringBuilder();
            builder1.append(output1);
        }
    }

    public HttpResponse apiBusGetUrl(String City, String url) throws IOException, ClientProtocolException {
        HttpGet getRequest = new HttpGet(common.value("air_url") + url + City);
        ////System.out.println(getRequest);
        HttpResponse httpResponse = httpclient.execute(getRequest);
        BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
        String output;
        while ((output = br.readLine()) != null) {
            builder = new StringBuilder();
            builder.append(output);
        }
        return httpResponse;
    }

    public void httpStatusCheck(HttpResponse httpResponse) {
        int hitStatus = httpResponse.getStatusLine().getStatusCode();
        if ( hitStatus == 200 ) {
            Assert.assertEquals(hitStatus, 200);
        	
            Reporter.log("HTTP Status code :" + hitStatus);
        } else {
            Reporter.log("HTTP Status code :" + hitStatus);
            Assert.assertEquals(hitStatus, 200);
        }
    }

    public HttpResponse busUrl(String Url, String City) throws IOException, ClientProtocolException {
        HttpGet getRequest = new HttpGet(Url + City);
        HttpResponse httpResponse = httpclient.execute(getRequest);
        return httpResponse;
    }

    public String busUrlOutput(HttpResponse httpResponse) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
        String output;
        StringBuilder builder = new StringBuilder();
        while ((output = br.readLine()) != null) {
            builder.append(output);
        }
        String Solutions = builder.toString();
        return Solutions;
    }

    public void busDestParser(String file, String CityCode) throws Exception {
        JSONParser parser = new JSONParser();
//
        Object obj = parser.parse(new FileReader(file));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray routeList = (JSONArray) jsonObject.get(smallWorldCityID);

        List < String > list = new ArrayList < String >();
        for ( int i = 0; i < routeList.size(); i++) {
            if ( routeList.get(i) != null ) {
                list.add(routeList.get(i).toString());
            }
        }
        String [ ] filteredDestination = list.toArray(new String [list.size()]);
        // //////System.out.println(filteredDestination.length);
        HttpResponse httpResponse = busUrl(common.value("air_url") + common.value("destination"), CityCode);
        String Solutions = busUrlOutput(httpResponse);
        // //////System.out.println(Solutions);
        httpStatusCheck(httpResponse);

        for ( int i = 0; i < filteredDestination.length; i++) {
            if ( Solutions.contains(filteredDestination[i].trim()) ) {
                Assert.assertTrue(Solutions.contains(filteredDestination[i].trim()));
            } else {
                // ////System.out.println("Not displayed in Route Json " + filteredDestination[i]);
            	Assert.assertTrue(Solutions.contains(filteredDestination[i].trim()));
            }
        }

    }

    public void busValidateCityCodeRoute(String file, String CityCode) throws Exception {
        JSONParser parser = new JSONParser();
//
        Object obj = parser.parse(new FileReader(file));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray routeList = (JSONArray) jsonObject.get(CityCode);

        List < String > list = new ArrayList < String >();
        for ( int i = 0; i < routeList.size(); i++) {
            if ( routeList.get(i) != null ) {
                list.add(routeList.get(i).toString());
            }
        }
        String [ ] filteredDestination = list.toArray(new String [list.size()]);
        if ( filteredDestination.length >= 1 ) {
            Assert.assertTrue(true);
        } else {
            // ////System.out.println("NO Mapping : " + CityCode);

        }

    }
}
