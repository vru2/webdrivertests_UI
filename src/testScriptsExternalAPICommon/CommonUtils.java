package testScriptsExternalAPICommon;

import static commonServices.CachedProperties.instance;
import static commonServices.CachedProperties.objectReportInstance;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.common.collect.ArrayListMultimap;

import commonServices.CachedProperties;
import domainServices.AirCommonMethod;



public class CommonUtils extends Commonmethods{
	
	

	public CachedProperties common = instance();
	public String debug = common.value("debug");
	public boolean debugger = Boolean.parseBoolean(debug);
	public CachedProperties objectRepos = objectReportInstance();
	public String getEnvironment(String env) throws Exception{
//		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\testScriptsExternalAPICommon\\Environment.properties"));
		FileInputStream fis = new FileInputStream(new File("src\\testScriptsExternalAPICommon\\Environment.properties"));
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(env);
		
	}
	public String getFarekey(String FareKey) throws Exception {
		String[] curObject = objectRepos.value(FareKey).split(":");
		String FareType = curObject[0];
		return FareType;
	}
	
/*	public By getObject(String header) throws Exception {
		String[] curObject = objectRepos.value(objectKey).split(":");
		String objectType = curObject[0];
		String objectValue = curObject[1];
		// find by id
		if (objectType.equalsIgnoreCase("ID")) {
			return By.id(objectValue);
		}
	}*/
	
	/* public  String calculateHash(String hashType, String input) {
	        byte[] hashseq = input.getBytes();
	        StringBuffer hexString = new StringBuffer();
	        try {
	            MessageDigest algorithm = MessageDigest.getInstance(hashType);
	            algorithm.reset();
	            algorithm.update(hashseq);
	            byte[] messageDigest = algorithm.digest();

	            for (int i = 0; i < messageDigest.length; i++) {
	                String hex = Integer.toHexString(0xFF & messageDigest[i]);
	                if (hex.length() == 1) {
	                    hexString.append("0");
	                }
	                hexString.append(hex);
	            }

	        } catch (NoSuchAlgorithmException e) {
	            //logger.error(e);
	        }

	        return hexString.toString();
	    }*/
	
	public void changeFare(String fare,String filename) throws Exception{
		File inputFile = new File("src\\testScriptsExternalAPICommon\\"+filename);
        DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
       String farekey=doc.getElementsByTagName("fare-key").item(0).getTextContent().toString();
       String[] oldfare=farekey.split("_");
       //System.out.println("old fare="+oldfare[oldfare.length-1]);
       //System.out.println("new fare="+fare);
       String mfarekey=farekey.replaceAll(oldfare[oldfare.length-1],fare);
       //System.out.println(mfarekey);
       doc.getElementsByTagName("fare-key").item(0).setTextContent(mfarekey);
       TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(inputFile);
		transformer.transform(source, result);
		//System.out.println("new fare key="+doc.getElementsByTagName("fare-key").item(0).getTextContent().toString());
       //farekey.substring(beginIndex, endIndex)
      // String[] parsedfarekey=farekey.split("_")
	}
	public TreeSet<Integer> getPrice(JSONObject fare,TreeSet<Integer> al) throws Exception{
		String term ="";
		for(int i=1;i<fare.length();i++){
			
			Iterator keys=fare.getJSONObject(String.valueOf(i)).keys();
			test:  while(keys.hasNext()){
			   String key=(String)keys.next();
			  // System.out.println(key);
			   if(key.equalsIgnoreCase("n")){
				 term=key;
				   break test;
				   }
			   if(key.equalsIgnoreCase("r")){
			 term=key;
				   break test;
				   }
			   if(key.equalsIgnoreCase("hbag")){
			 term=key;
			   break test;
			   }
			   if(key.equalsIgnoreCase("corp")){
				  term=key;
				   break test;
				   }
			   
			  }
			
			if(term.equalsIgnoreCase("r")){
				int x=(int)(Math.round(fare.getJSONObject(String.valueOf(i)).getJSONObject(term).getInt("pr")));
				al.add(x);
				}
			
			else if(term.equalsIgnoreCase("n")){
				//System.out.println(term);
				///System.out.println(fare.getJSONObject(String.valueOf(i)).getJSONObject(term).getInt("pr"));
				int x=(int)(Math.round(fare.getJSONObject(String.valueOf(i)).getJSONObject(term).getInt("pr")));
			
				//System.out.println(x);
				al.add(x);
					}
		
	}
		return al;
	}
	public List<TreeSet> getpriceRoundtrip(JSONObject fare,JSONArray onward,JSONArray retur,TreeSet<Integer> ts,TreeSet<Integer> ts1) throws Exception{
		List al = new ArrayList<HashMap>();
		HashMap<String,String> hp=new HashMap<String,String>();
		HashMap<String,String> hp1=new HashMap<String,String>();
		   for(int i=1;i<fare.length()+1;i++){
	    		//   System.out.println();
	    		   if(fare.getJSONObject(String.valueOf(i)).has("R")){
	    		     		   hp1.put(String.valueOf(i),fare.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
	    		//  System.out.println(fare.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr"));
	    		   hp1.put("amount"+String.valueOf(i),String.valueOf(fare.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr")));
	    		  // System.out.println("i value="+i);
	    	//	 System.out.println( hp1.get("amount"+String.valueOf(i)));
	    		   }
	    		   else if(fare.getJSONObject(String.valueOf(i)).has("N")){
		     		   hp1.put(String.valueOf(i),fare.getJSONObject(String.valueOf(i)).getJSONObject("N").getString("fk"));
		 // System.out.println(fare.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr"));
		   hp1.put("amount"+String.valueOf(i),String.valueOf(fare.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr")));
		   
		   }
	    		   
	    		   
	    		//  System.out.println( "i value="+i+"   and "+airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
	    		  
	    	   }
	    	   
	    	 
	       
	      /* JSONObject airSearchResult = jsonObject.getJSONObject("mapping");
	       
	       JSONArray onward = jsonObject.getJSONObject("mapping").getJSONArray("onward");*/
	       for(int j=1;j<onward.length();j++){
	    	//   System.out.println("---"+onward.getJSONObject(j).get("f"));
	    	   hp.put("onward"+String.valueOf(j),onward.getJSONObject(j).get("f").toString());
	    	 /*System.out.println(hp.get("onward"+String.valueOf(j)));
	    	 System.out.println("amount"+hp.get("onward"+String.valueOf(j)));*/
	    	// System.out.println(hp1.get("amount"+hp.get("onward"+String.valueOf(j))));
	    	 ts.add(Integer.parseInt(hp1.get("amount"+hp.get("onward"+String.valueOf(j)))));
	    	 
	    	 
	    	 //System.out.println(hp1.get(hp.get("onward"+String.valueOf(j))));
	    	 hp.put(hp1.get("amount"+hp.get("onward"+String.valueOf(j))),hp1.get(hp.get("onward"+String.valueOf(j))));
	    	// System.out.println(hp.get(hp1.get("amount"+hp.get("onward"+String.valueOf(j)))));
	    	  
	       }
	       /*JSONArray return1 = jsonObject.getJSONObject("mapping").getJSONArray("return");*/
	       for(int i=0;i<retur.length();i++){
	    	   //System.out.println("---"+return1.getJSONObject(i).get("f"));
	    	  
	    	   hp.put("return"+String.valueOf(i),retur.getJSONObject(i).get("f").toString());
	    	   //System.out.println(hp.get("return"+String.valueOf(i)));
		    	 //System.out.println("amount"+hp.get("return"+String.valueOf(i)));
		    	// System.out.println(hp1.get("amount"+hp.get("return"+String.valueOf(i))));
		    	 ts1.add(Integer.parseInt(hp1.get("amount"+hp.get("return"+String.valueOf(i)))));
		    	 
		    	 
		    	 //System.out.println(hp1.get(hp.get("return"+String.valueOf(i))));
		    	 hp.put(hp1.get("amount"+hp.get("return"+String.valueOf(i))),hp1.get(hp.get("return"+String.valueOf(i))));
		    	 //System.out.println(hp.get(hp1.get("amount"+hp.get("return"+String.valueOf(i)))));
          al.add(ts);
          al.add(ts1);

	   }
	       return al;
	}
	
	public List<HashMap> getRoundtripFlightDetails(HashMap<String, String> hp,HashMap<String, String> hp1,DefaultHttpClient clientSearch,HashMap<String, String> fareKeys ,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,String source) throws Exception{
	clientSearch = new DefaultHttpClient();
	String term="";
	
		List a1 = new ArrayList<HashMap>();
		System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
	//	//System.out.println(get);
		//getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"Payloadroundtrip.txt");
		//String farekey=getFarekey("Farekey").toString().split(":")[1];
	    if(source.equalsIgnoreCase("b2c")){
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			get.setHeader("X-CT-SOURCETYPE","B2C");
		}
		else{
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		}
	    //get.setHeader("X-CT-API-KEY",common.value("APIKey"));
	  //  get.setHeader("X-CT-API-KEY",common.value("APIKey"));
	   // get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		int hitStatus = response.getStatusLine().getStatusCode();
		Reporter.log("Http response Staus for bookResponse="+hitStatus);
		Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
		StringBuffer sb = new StringBuffer();
		String str="";
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str=br.readLine())!=null){
			   sb.append(str.trim());
			  
		}
		
     //  System.out.println("------------"+sb.toString());
       JSONObject jsonObject = new JSONObject(sb.toString());
//System.out.println(jsonObject);
       JSONObject airSearchResult1 = jsonObject.getJSONObject("fare");
      // System.out.println("size="+airSearchResult1.length());
    
    	  // JSONObject airSearchResult11 = jsonObject.getJSONObject("fare");
    	   for(int i=1;i<airSearchResult1.length()+1;i++){
    		   String key="";
    		//   System.out.println();
    		   if(airSearchResult1.getJSONObject(String.valueOf(i)).has("R")){
    		     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
    		  // System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr"));
    		   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr")));
    		  
    		   }
    		   else if(airSearchResult1.getJSONObject(String.valueOf(i)).has("N")){
	     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getString("fk"));
	 //  System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr"));
	   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr")));
	   
	   }
    		   else if(airSearchResult1.getJSONObject(String.valueOf(i)).has("SPLRT")){
    			   Iterator<String> keys = airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("SPLRT").keys();
    			    while(keys.hasNext()){
    			     key = keys.next();
    			     //  System.out.println(key);
    			       break;
    			    }
	     		 hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("SPLRT").getJSONObject(key).getJSONObject("R").getString("fk"));
	 //  System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr"));
	   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("SPLRT").getJSONObject(key).getJSONObject("R").getInt("pr")));
	   
	  
    		   }
    		   
    		//  System.out.println( "i value="+i+"   and "+airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
    		  
    	   }
    	   
    	 
       
       JSONObject airSearchResult = jsonObject.getJSONObject("mapping");
       
       JSONArray onward = jsonObject.getJSONObject("mapping").getJSONArray("onward");
       for(int j=0;j<onward.length();j++){
    	//   System.out.println("---"+onward.getJSONObject(j).get("f"));
    	   fareKeys.put(String.valueOf(j),onward.getJSONObject(j).get("f").toString());
    	  // System.out.println(fareKeys.get(String.valueOf(j)));
    	   
    	  // System.out.println(hp1.get(fareKeys.get("amount"+String.valueOf(j).toString().replace("[","").replace("]",""))));
    	  // System.out.println(hp1.get(fareKeys.get(String.valueOf(j)).toString().replace("[","").replace("]","")));
       }
       JSONArray return1 = jsonObject.getJSONObject("mapping").getJSONArray("return");
       for(int i=0;i<return1.length();i++){
    	   //System.out.println("---"+return1.getJSONObject(i).get("f"));
    	  
    	   hp.put(String.valueOf(i),return1.getJSONObject(i).get("f").toString());
    	  // System.out.println(hp.get(String.valueOf(i)));

   }
   a1.add(hp);
   a1.add(hp1);
   a1.add(fareKeys);
  // System.out.println(a1);
   return a1;
	}

	public List<HashMap> getINTLRoundtripFlightDetails(HashMap<String, String> hp,HashMap<String, String> hp1,DefaultHttpClient clientSearch,HashMap<String, String> fareKeys ,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,String source) throws Exception{
	clientSearch = new DefaultHttpClient();
	String term="";
	
		List a1 = new ArrayList<HashMap>();
		System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
	//	//System.out.println(get);
		//getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"Payloadroundtrip.txt");
		//String farekey=getFarekey("Farekey").toString().split(":")[1];
	    if(source.equalsIgnoreCase("b2c")){
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			get.setHeader("X-CT-SOURCETYPE","B2C");
		}
		else{
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		}
	    //get.setHeader("X-CT-API-KEY",common.value("APIKey"));
	  //  get.setHeader("X-CT-API-KEY",common.value("APIKey"));
	   // get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		StringBuffer sb = new StringBuffer();
		String str="";
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str=br.readLine())!=null){
			   sb.append(str.trim());
			  
		}
		
     //  System.out.println("------------"+sb.toString());
       JSONObject jsonObject = new JSONObject(sb.toString());
// System.out.println(jsonObject);
       JSONObject airSearchResult1 = jsonObject.getJSONObject("fare");
      // System.out.println("size="+airSearchResult1.length());
    
    	  // JSONObject airSearchResult11 = jsonObject.getJSONObject("fare");
    	   for(int i=1;i<airSearchResult1.length()+1;i++){
    		   String key="";
    		//   System.out.println();
    		   if(airSearchResult1.getJSONObject(String.valueOf(i)).has("R")){
    		     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
    		  // System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr"));
    		   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr")));
    		  
    		   }
    		   else if(airSearchResult1.getJSONObject(String.valueOf(i)).has("N")){
	     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getString("fk"));
	 //  System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr"));
	   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr")));
	   
	   }
    		   else if(airSearchResult1.getJSONObject(String.valueOf(i)).has("SPLRT")){
    			   Iterator<String> keys = airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("SPLRT").keys();
    			    while(keys.hasNext()){
    			     key = keys.next();
    			     //  System.out.println(key);
    			       break;
    			    }
	     		 hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("SPLRT").getJSONObject(key).getJSONObject("R").getString("fk"));
	 //  System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr"));
	   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("SPLRT").getJSONObject(key).getJSONObject("R").getInt("pr")));
	   
	  
    		   }
    		   
    		//  System.out.println( "i value="+i+"   and "+airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
    		  
    	   }
    	   
    	 
       
       JSONObject airSearchResult = jsonObject.getJSONObject("mapping");
       
       JSONArray onward = jsonObject.getJSONObject("mapping").getJSONArray("onward");
       for(int j=0;j<onward.length();j++){
    	//   System.out.println("---"+onward.getJSONObject(j).get("f"));
    	   fareKeys.put(String.valueOf(j),onward.getJSONObject(j).get("f").toString());
    	  // System.out.println(fareKeys.get(String.valueOf(j)));
    	   
    	  // System.out.println(hp1.get(fareKeys.get("amount"+String.valueOf(j).toString().replace("[","").replace("]",""))));
    	  // System.out.println(hp1.get(fareKeys.get(String.valueOf(j)).toString().replace("[","").replace("]","")));
       }
       JSONArray return1 = jsonObject.getJSONObject("mapping").getJSONArray("return");
       for(int i=0;i<return1.length();i++){
    	   //System.out.println("---"+return1.getJSONObject(i).get("f"));
    	  
    	   hp.put(String.valueOf(i),return1.getJSONObject(i).get("f").toString());
    	  // System.out.println(hp.get(String.valueOf(i)));

   }
   a1.add(hp);
   a1.add(hp1);
   a1.add(fareKeys);
   return a1;
	}
	public JSONObject getRoundTripItineraryID(DefaultHttpClient	clientSearch,HashMap<String, String> hp,HashMap<String, String> hp1,HashMap<String, String> fareKey,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,int i,String source) throws Exception, IOException{
	
		boolean flightUnavailable=false;
		
		clientSearch=new DefaultHttpClient();
	//	System.out.println(hp);
		//System.out.println(hp1);
		String key=hp1.get(fareKey.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		//System.out.println(key);
		String amount=hp1.get("amount"+fareKey.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
	//System.out.println(hp.get(String.valueOf(i).toString()));
		String key1=hp1.get(hp.get(String.valueOf(i).toString()));
		//System.out.println(key1);
		String amount1=hp1.get("amount"+hp.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		//System.out.println(amount1);
		String flightno =key.split(carrier.trim())[1].split("_")[1].trim();
		String flightno1 =key1.split(carrier.trim())[1].split("_")[1].trim();
		//System.out.println("----"+amount+"++++++++++"+amount1+"    flightno="+flightno+"    flightno1="+flightno1+"         farekey="+key+"         farekey1="+key1);


		flightUnavailable= false;
		//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
		// String key = fareKeys.get(String.valueOf(i)).toString();
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		
		String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\":"+amount+",         \"fare_key\": \"" +key+"\"      },       {         \"amount\":"+amount1+",         \"fare_key\": \"" +key1+"\"      }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\": \""+from+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\": \""+flightno+"\",             \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",              \"departure_date\": \""+onwarddate+"\"           }         }       },       {         \"segments\": {           \"2\": {             \"departure_airport\":  \""+to+"\",            \"arrival_airport\":  \""+from+"\",             \"flight_number\": \""+flightno1+"\",             \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",            \"departure_date\": \""+returndate+"\"             }         }       }     ],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"test\"+c+\"\",         \"last_name\": \"test\"+c+\"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripfortesting@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     },     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\":521     }   } }";
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
		//System.out.println("---"+postString);
		//System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create");
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create"));
	StringEntity params = new StringEntity(postString);
	itinenaryCall.setEntity(params);
	if(source.equalsIgnoreCase("b2c")){
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	}
	else{
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	}
	//itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	//itinenaryCall.setHeader("ct-auth",value);
	//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
	HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
	int hitStatus = itinenaryResponse.getStatusLine().getStatusCode();
	Reporter.log("Http response Staus for itinenaryResponse="+hitStatus,true);
	//Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
	HttpEntity entityIti = itinenaryResponse.getEntity();
	/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
	System.out.println("response string=="+responseString);*/
	Document docIti =null;
	BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
	String str12 ="";
	StringBuffer sb12 =new StringBuffer();
	while((str12=br12.readLine())!=null){
	sb12.append(str12);
	}

	JSONObject itinenaryId = new JSONObject(sb12.toString());

//System.out.println(itinenaryId);
		return itinenaryId;
	}
	public JSONObject getRoundTripItineraryIDWithOutItineraryID(DefaultHttpClient	clientSearch,HashMap<String, String> hp,HashMap<String, String> hp1,HashMap<String, String> fareKey,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,int i,String source) throws Exception, IOException{
		
		boolean flightUnavailable=false;
		
		clientSearch=new DefaultHttpClient();
		//System.out.println(hp);
		//System.out.println(hp1);
		String key=hp1.get(fareKey.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		//System.out.println(key);
		String amount=hp1.get("amount"+fareKey.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
	//System.out.println(hp.get(String.valueOf(i).toString()));
		String key1=hp1.get(hp.get(String.valueOf(i).toString()));
		//System.out.println(key1);
		String amount1=hp1.get("amount"+hp.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		//System.out.println(amount1);
		String flightno =key.split(carrier.trim())[1].split("_")[1].trim();
		String flightno1 =key1.split(carrier.trim())[1].split("_")[1].trim();
		//System.out.println("----"+amount+"++++++++++"+amount1+"    flightno="+flightno+"    flightno1="+flightno1+"         farekey="+key+"         farekey1="+key1);


		flightUnavailable= false;
		//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
		// String key = fareKeys.get(String.valueOf(i)).toString();
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\": "+amount+",         \"fare_key\":  \"" +key+"\"       },       {         \"amount\": "+amount1+",          \"fare_key\": \"" +key1+"\"        }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\": \""+from+"\",              \"arrival_airport\":  \""+to+"\",             \"flight_number\":\""+flightno+"\",             \"airline\":  \""+carrier+"\",             \"operating_airline\":  \""+carrier+"\",            \"departure_date\":  \""+onwarddate+"\"          }         }       },       {         \"segments\": {           \"2\": {             \"departure_airport\":\""+to+"\",             \"arrival_airport\": \""+from+"\",             \"flight_number\": \""+flightno1+"\",            \"airline\": \""+carrier+"\",             \"operating_airline\": \""+carrier+"\",             \"departure_date\":\""+returndate+"\"          }         }       }     ],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"test"+c+"\",         \"last_name\": \"test"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": "+common.value("AccountID")+"    }   } }";
		//String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\":"+amount+",         \"fare_key\": \"" +key+"\"      },       {         \"amount\":"+amount1+",         \"fare_key\": \"" +key1+"\"      }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\": \""+from+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\": \""+flightno+"\",             \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",              \"departure_date\": \""+onwarddate+"\"           }         }       },       {         \"segments\": {           \"2\": {             \"departure_airport\":  \""+to+"\",            \"arrival_airport\":  \""+from+"\",             \"flight_number\": \""+flightno1+"\",             \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",            \"departure_date\": \""+returndate+"\"             }         }       }     ],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"test\",         \"last_name\": \"test\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripfortesting@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     },     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\":521     }   } }";
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
		//System.out.println("---"+postString);
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create"));
	StringEntity params = new StringEntity(postString);
	itinenaryCall.setEntity(params);
	if(source.equalsIgnoreCase("b2c")){
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	}
	else{
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	}
	//itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	//itinenaryCall.setHeader("ct-auth",value);
	//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
	HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
	HttpEntity entityIti = itinenaryResponse.getEntity();
	/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
	System.out.println("response string=="+responseString);*/
	Document docIti =null;
	BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
	String str12 ="";
	StringBuffer sb12 =new StringBuffer();
	while((str12=br12.readLine())!=null){
	sb12.append(str12);
	}

	JSONObject itinenaryId = new JSONObject(sb12.toString());

	//System.out.println(itinenaryId);
		return itinenaryId;
	}
	public List<HashMap> getViaFlightDetails(HashMap<String, String> hp,HashMap<String, String> hp1,DefaultHttpClient clientSearch,ArrayListMultimap<String, String>  contentref,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,String source) throws Exception{
	clientSearch = new DefaultHttpClient();
	String term="";
		List a1 = new ArrayList<HashMap>();
		
		System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
		
	    //System.out.println(get);
		//getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"Payloadroundtrip.txt");
		//String farekey=getFarekey("Farekey").toString().split(":")[1];
	    if(source.equalsIgnoreCase("b2c")){
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			get.setHeader("X-CT-SOURCETYPE","B2C");
		}
		else{
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		}
	    //get.setHeader("X-CT-API-KEY",common.value("APIKey"));
	  //  get.setHeader("X-CT-API-KEY",common.value("APIKey"));
	   // get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		int hitStatus = response.getStatusLine().getStatusCode();
		Reporter.log("Http response Staus for GetResponse="+hitStatus);
		Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
		
		
		StringBuffer sb = new StringBuffer();
		String str="";
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str=br.readLine())!=null){
			   sb.append(str.trim());
			  
		}
		
     //  System.out.println(sb.toString());
       JSONObject jsonObject = new JSONObject(sb.toString());
//System.out.println(jsonObject);
       JSONObject airSearchResult1 = jsonObject.getJSONObject("fare");
      // System.out.println("size="+airSearchResult1.length());
    
    	  // JSONObject airSearchResult11 = jsonObject.getJSONObject("fare");
    	   for(int i=1;i<airSearchResult1.length()+1;i++){
    		   
    		 //System.out.println("i value="+i);
    		   if(airSearchResult1.getJSONObject(String.valueOf(i)).has("R")){
    		     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
    		 //  System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
    		   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr")));
    		  
    		   }
    		   else if(airSearchResult1.getJSONObject(String.valueOf(i)).has("N")){
	     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getString("fk"));
	   //System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getString("fk"));
	   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr")));
	   
	   }
    		   else if(airSearchResult1.getJSONObject(String.valueOf(i)).has("CORP")){
    			   //System.out.println("i value in corp="+i);
					String term1 = null;
					Iterator Ikey=airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("CORP").keys();
					//System.out.println(keys.toString());
				
					test:  while(Ikey.hasNext()){
					   String key=(String)Ikey.next();
					 // System.out.println(key);
					   if(key.equalsIgnoreCase("n")){
						 term1=key;
						   break test;
						   }
					    if(key.equalsIgnoreCase("r")){
					 term1=key;
						   break test;
						   }
					   if(key.equalsIgnoreCase("hbag")){
					 term1=key;
					   break test;
					   }
					  if(key.equalsIgnoreCase("corp")){
						  term1=key;
						   break test;
						   }
					   
					  }
					hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("CORP").getJSONObject(term1).get("fk").toString());
					hp1.put("amount"+String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("CORP").getJSONObject(term1).get("pr").toString());
					//System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("CORP").getJSONObject(term1).get("fk").toString());
					//System.out.println("-----"+hp1.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
    		   
    		   
    		//  System.out.println( "i value="+i+"   and "+airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
    		  
    	   }
    	   
    	 
       
       JSONObject airSearchResult = jsonObject.getJSONObject("mapping");
       
       JSONArray onward = jsonObject.getJSONObject("mapping").getJSONArray("onward");
       JSONObject secdetails = jsonObject.getJSONObject("content");
       for(int l=1;l<secdetails.length()+1;l++){
    	   hp.put("content"+l, secdetails.getJSONObject(String.valueOf(l)).get("fr").toString());
    	   hp.put("arrivaldate"+l,secdetails.getJSONObject(String.valueOf(l)).get("ad").toString());
    	//  System.out.println(secdetails.getJSONObject(String.valueOf(l)).get("to"));
    	 // System.out.println("l="+l+"    "+hp.get("content"+l));
       }
       
       
       for(int j=1;j<onward.length();j++){
    	   
    	   int x=1;
    	   JSONArray contentinmapping = onward.getJSONObject(j).getJSONArray("c");
    	  //System.out.println(contentinmapping);
    	   if(contentinmapping.length()==2){
    		  // System.out.println("x value="+x);
    		   contentref.put(String.valueOf(x),contentinmapping.getString(0).toString());
    		   contentref.put(String.valueOf(x),contentinmapping.getString(1).toString());
    		   //System.out.println(contentref.get(String.valueOf(b)).toArray()[1].toString());
    		//   System.out.println(contentref.get(String.valueOf(b)).toString());
    		   hp.put("contentref"+x,contentref.get(String.valueOf(x)).toString());
    		   hp.put("fareref"+x,onward.getJSONObject(j).get("f").toString());
    		 /*System.out.println(hp1.get(hp.get("fareref"+x)));
    		  System.out.println("amount="+hp1.get("amount"+hp.get("fareref"+x)));
    		   System.out.println(hp.get("contentref"+x).toString().split(",")[1].replace("]",""));
    		  System.out.println("content"+hp.get("contentref"+x).toString().split(",")[1].replace("]","").trim());
    		  System.out.println(hp.get("content"+hp.get("contentref"+x).toString().split(",")[1].replace("]","").trim()));
    		  System.out.println(hp.get("arrivaldate"+hp.get("contentref"+x).toString().split(",")[1].replace("]","").trim()));
    		  System.out.println(hp);
    		  System.out.println("j value="+j);
    		  System.out.println( hp.get("contentref"+x));
    		System.out.println(hp.get("fareref"+x));
    		   System.out.println(hp.get("contentref"+x).toString());*/
    		  //System.out.println(contentref.get(String.valueOf(b)));
    		  
    		   x++;
    	   }
    	   }
    	   a1.add(hp);
   a1.add(hp1);
   a1.add(contentref);
   return a1;
	
	}
	public List<HashMap> B2CInternational(HashMap<String, String> hp,HashMap<String, String> hp1,DefaultHttpClient clientSearch,ArrayListMultimap<String, String>  contentref,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,String source) throws Exception{

		clientSearch = new DefaultHttpClient();
		String term="";
			List a1 = new ArrayList<HashMap>();
			
		//	System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
			
		    HttpGet get = null;
			try {
				get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    //System.out.println(get);
			//getdepositaccountid(common.value("AccountID"),"Book.txt");
			//getdepositaccountid(common.value("AccountID"),"Payloadroundtrip.txt");
			//String farekey=getFarekey("Farekey").toString().split(":")[1];
		    if(source.equalsIgnoreCase("b2c")){
				get.setHeader("X-CT-API-KEY",common.value("APIKey"));
				get.setHeader("X-CT-SOURCETYPE","B2C");
			}
			else{
				get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			}
		    //get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		  //  get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		   // get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
			HttpResponse response = clientSearch.execute(get);
			StringBuffer sb = new StringBuffer();
			String str="";
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while((str=br.readLine())!=null){
				   sb.append(str.trim());
				  
			}
			
	       //System.out.println(sb.toString());
	       JSONObject jsonObject = new JSONObject(sb.toString());
	//System.out.println(jsonObject);
	       JSONObject airSearchResult1 = jsonObject.getJSONObject("fare");
	      // System.out.println("size="+airSearchResult1.length());
	    
	    	  // JSONObject airSearchResult11 = jsonObject.getJSONObject("fare");
	    	   for(int i=1;i<airSearchResult1.length()+1;i++){
	    		//   System.out.println();
	    		   if(airSearchResult1.getJSONObject(String.valueOf(i)).has("R")){
	    		     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
	    		  // System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr"));
	    		   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr")));
	    		  
	    		   }
	    		   else if(airSearchResult1.getJSONObject(String.valueOf(i)).has("N")){
		     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getString("fk"));
		 //  System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr"));
		   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr")));
		   
		   }
	    		   
	    		   
	    		//  System.out.println( "i value="+i+"   and "+airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
	    		  
	    	   }
	    	   
	    	 
	       
	       JSONObject airSearchResult = jsonObject.getJSONObject("mapping");
	       
	       JSONArray onward = jsonObject.getJSONObject("mapping").getJSONArray("onward");
	       JSONObject secdetails = jsonObject.getJSONObject("content");
	       for(int l=1;l<secdetails.length()+1;l++){
	    	   hp.put("content"+l, secdetails.getJSONObject(String.valueOf(l)).get("fr").toString());
	    	   hp.put("arrivaldate"+l,secdetails.getJSONObject(String.valueOf(l)).get("ad").toString());
	    	//  System.out.println(secdetails.getJSONObject(String.valueOf(l)).get("to"));
	    	 // System.out.println("l="+l+"    "+hp.get("content"+l));
	       }
	       
	       
	       for(int j=1;j<onward.length();j++){
	    	   
	    	   int x=1;
	    	   JSONArray contentinmapping = onward.getJSONObject(j).getJSONArray("c");
	    	  
	    	   if(contentinmapping.length()==2){
	    		   contentref.put(String.valueOf(x),contentinmapping.getString(0).toString());
	    		   contentref.put(String.valueOf(x),contentinmapping.getString(1).toString());
	    		   //System.out.println(contentref.get(String.valueOf(b)).toArray()[1].toString());
	    		//   System.out.println(contentref.get(String.valueOf(b)).toString());
	    		   hp.put("contentref"+x,contentref.get(String.valueOf(x)).toString());
	    		   hp.put("fareref"+x,onward.getJSONObject(j).get("f").toString());
	    		 //  System.out.println(hp1.get(hp.get("fareref"+x)));
	    		   //System.out.println("amount="+hp1.get("amount"+hp.get("fareref"+x)));
	    		  // System.out.println(hp.get("contentref"+x).toString().split(",")[1].replace("]",""));
	    		  // System.out.println("content"+hp.get("contentref"+x).toString().split(",")[1].replace("]","").trim());
	    		   //System.out.println(hp.get("content"+hp.get("contentref"+x).toString().split(",")[1].replace("]","").trim()));
	    		   //System.out.println(hp.get("arrivaldate"+hp.get("contentref"+x).toString().split(",")[1].replace("]","").trim()));
	    		 //  System.out.println( hp.get("contentref"+j));
	    		// System.out.println(hp.get("fareref"+j));
	    		  // System.out.println(hp.get("contentref"+j).toString());
	    		  // System.out.println(contentref.get(String.valueOf(b)));
	    		  
	    		   x++;
	    	   }
	    	   }
	    	   a1.add(hp);
	   a1.add(hp1);
	   a1.add(contentref);
	   return a1;
		
		
	}
	
	
	
	
	
	public JSONObject getViaItineraryID(DefaultHttpClient	clientSearch,HashMap<String, String> hp,HashMap<String, String> hp1,ArrayListMultimap<String, String>  contentref,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,int i,String PAX,String source) throws Exception, IOException{
		int j=1;
		boolean flightUnavailable=false;
		String postString ="";
	//	System.out.println(hp);
		clientSearch=new DefaultHttpClient();
		/*System.out.println("hp="+hp);
		System.out.println("hp1="+hp1);
		System.out.println(hp.get("fareref1"));
		System.out.println("fareref"+j);
		System.out.println(hp.get("fareref"+j));*/
		
		
		//System.out.println("amount="+amount);
		//System.out.println(hp.get("fareref"+j));
		//System.out.println("------"+hp1);
		String key=hp1.get(hp.get("fareref"+j));
	   // System.out.println("key="+key);
		String amount=hp1.get("amount"+hp.get("fareref"+j));
		String flightno =key.split(carrier.trim())[1].split("_")[1].trim();
		String flightno1=key.split(carrier.trim())[2].split("_")[1].trim();
		String stopoverfrom=hp.get("content"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim());
		String onwardday=hp.get("arrivaldate"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim()).split("/")[0];
		String onwardmonth=hp.get("arrivaldate"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim()).split("/")[1];
		String onwardyear=hp.get("arrivaldate"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim()).split("/")[2];
		/*String onwardmonth=hp.get("arrivaldate"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim()).replaceAll("/","-").split("-")[1];
		String onwardyear=hp.get("arrivaldate"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim()).replaceAll("/","-").split("-")[2];*/
		String onwarddate1=onwardyear+"-"+onwardmonth+"-"+onwardday;
		//String key1=hp1.get(hp.get(String.valueOf(i).toString().replace("[","").replace("]","")));
		//String amount1=hp1.get("amount"+hp.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		//System.out.println(key.split(carrier.trim()));
		//System.out.println("----"+amount+"++++++++++    flightno="+flightno+"    flightno1="+flightno1+"         farekey="+key+"    onwarddate1="+onwarddate1+"              farekey="+key);


		flightUnavailable= false;
		//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
		// String key = fareKeys.get(String.valueOf(i)).toString();
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		if(PAX.equalsIgnoreCase("single")){
		 postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\": "+amount+",         \"fare_key\":\"" +key+"\"        }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\": \""+from+"\",             \"arrival_airport\": \""+stopoverfrom+"\",            \"flight_number\":\""+flightno+"\",             \"airline\":\""+carrier+"\",             \"operating_airline\": \""+carrier+"\",              \"departure_date\": \""+onwarddate+"\"         },           \"2\": {             \"departure_airport\": \""+stopoverfrom+"\",            \"arrival_airport\":\""+to+"\",              \"flight_number\": \""+flightno1+"\",             \"airline\": \""+carrier+"\",             \"operating_airline\": \""+carrier+"\",              \"departure_date\": \""+onwarddate1+"\"          }         }       }     ],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"test"+c+"\",         \"last_name\": \"teest"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripautomation@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     },     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": 521     }   } }   ";
		}
		else{
			postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\": "+amount+",         \"fare_key\": \"" +key+"\"       }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\": \""+from+"\",             \"arrival_airport\": \""+stopoverfrom+"\",             \"flight_number\": \""+flightno+"\",              \"airline\":\""+carrier+"\",             \"operating_airline\": \""+carrier+"\",            \"departure_date\": \""+onwarddate+"\"           },           \"2\": {             \"departure_airport\": \""+stopoverfrom+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\": \""+flightno1+"\",             \"airline\": \""+carrier+"\",             \"operating_airline\": \""+carrier+"\",             \"departure_date\": \""+onwarddate1+"\"            }         }       }     ],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"tesst"+c+"\",         \"last_name\": \"teesst"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       },       {         \"title\": \"Mstr\",         \"first_name\": \"teesst"+c+"\",         \"last_name\": \"teesst"+c+"\",         \"type\": \"CHD\",         \"date_of_birth\": \"2007-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripautomation@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     },     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": 521     }   } }";
		}
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
		//System.out.println("---"+postString);
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create"));
	StringEntity params = new StringEntity(postString);
	itinenaryCall.setEntity(params);
	if(source.equalsIgnoreCase("b2c")){
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	}
	else{
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	}
	//itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	//itinenaryCall.setHeader("ct-auth",value);
	//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
	HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
	int hitStatus = itinenaryResponse.getStatusLine().getStatusCode();
	Reporter.log("Http response Staus for itinenaryResponse="+hitStatus);
	Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
	
	HttpEntity entityIti = itinenaryResponse.getEntity();
	/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
	System.out.println("response string=="+responseString);*/
	Document docIti =null;
	BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
	String str12 ="";
	StringBuffer sb12 =new StringBuffer();
	while((str12=br12.readLine())!=null){
	sb12.append(str12);
	}

	JSONObject itinenaryId = new JSONObject(sb12.toString());

	//System.out.println(itinenaryId);
		return itinenaryId;
	}
	public JSONObject getStopOverItineraryID(DefaultHttpClient	clientSearch,HashMap<String, String> hp,HashMap<String, String> hp1,ArrayListMultimap<String, String>  contentref,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,int i,String PAX,String source) throws Exception, IOException{
		int j=1;
		JSONObject itinenaryId =null;
		boolean fsame=false;
		/*System.out.println(hp);
		System.out.println("fareref"+j);
		System.out.println("-------------------------------------"+hp.get("fareref"+j));
		System.out.println("flight"+hp.get("contentref"+j).toString().split(",")[0].replace("[","").trim());*/
		String flg=hp.get("flightno"+hp.get("contentref"+j).toString().split(",")[0].replace("[","").trim());
		String flg1=hp.get("flightno"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim());
		//System.out.println("flight 1=="+flg);
		//System.out.println("flight 2==="+flg1);
		do{
			
			 flg=hp.get("flightno"+hp.get("contentref"+j).toString().split(",")[0].replace("[","").trim());
			 flg1=hp.get("flightno"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim());
		if(Integer.parseInt(flg)==Integer.parseInt(flg1)){
			fsame=true;
		boolean flightUnavailable=false;
		String postString ="";
	//	System.out.println(hp);
		clientSearch=new DefaultHttpClient();
		/*System.out.println("hp="+hp);
		System.out.println("hp1="+hp1);
		System.out.println(hp.get("fareref1"));
		System.out.println("fareref"+j);
		System.out.println(hp.get("fareref"+j));
		
		
		System.out.println("amount="+amount);
		System.out.println(hp.get("fareref"+j));*/
		
		String key=hp1.get(hp.get("fareref"+j));
		String amount=hp1.get("amount"+hp.get("fareref"+j));
		String flightno =key.split(carrier.trim())[1].split("_")[1].trim();
		
		//String flightno1=key.split(carrier.trim())[2].split("_")[1].trim();
		String stopoverfrom=hp.get("content"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim());
		String onwardday=hp.get("arrivaldate"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim()).split("/")[0];
		String onwardmonth=hp.get("arrivaldate"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim()).split("/")[1];
		String onwardyear=hp.get("arrivaldate"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim()).split("/")[2];
	
		
		
		
		/*String onwardmonth=hp.get("arrivaldate"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim()).replaceAll("/","-").split("-")[1];
		String onwardyear=hp.get("arrivaldate"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim()).replaceAll("/","-").split("-")[2];*/
		String onwarddate1=onwardyear+"-"+onwardmonth+"-"+onwardday;
		//String key1=hp1.get(hp.get(String.valueOf(i).toString().replace("[","").replace("]","")));
		//String amount1=hp1.get("amount"+hp.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		//System.out.println(key.split(carrier.trim()));
		//System.out.println("----"+amount+"++++++++++    flightno="+flightno+"    flightno1="+flightno1+"         farekey="+key+"    onwarddate1="+onwarddate1+"              farekey="+key);


		flightUnavailable= false;
		//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
		// String key = fareKeys.get(String.valueOf(i)).toString();
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		if(PAX.equalsIgnoreCase("single")){
		 postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\": "+amount+",         \"fare_key\":\"" +key+"\"        }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\": \""+from+"\",             \"arrival_airport\": \""+stopoverfrom+"\",            \"flight_number\":\""+flightno+"\",             \"airline\":\""+carrier+"\",             \"operating_airline\": \""+carrier+"\",              \"departure_date\": \""+onwarddate+"\"         },           \"2\": {             \"departure_airport\": \""+stopoverfrom+"\",            \"arrival_airport\":\""+to+"\",              \"flight_number\": \""+flightno+"\",             \"airline\": \""+carrier+"\",             \"operating_airline\": \""+carrier+"\",              \"departure_date\": \""+onwarddate1+"\"          }         }       }     ],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"test"+c+"\",         \"last_name\": \"test"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripautomation@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     },     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": 521     }   } }   ";
		}
		else{
			postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\": "+amount+",         \"fare_key\": \"" +key+"\"       }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\": \""+from+"\",             \"arrival_airport\": \""+stopoverfrom+"\",             \"flight_number\": \""+flightno+"\",              \"airline\":\""+carrier+"\",             \"operating_airline\": \""+carrier+"\",            \"departure_date\": \""+onwarddate+"\"           },           \"2\": {             \"departure_airport\": \""+stopoverfrom+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\": \""+flightno+"\",             \"airline\": \""+carrier+"\",             \"operating_airline\": \""+carrier+"\",             \"departure_date\": \""+onwarddate1+"\"            }         }       }     ],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"tsest"+c+"\",         \"last_name\": \"tsest"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       },       {         \"title\": \"Mstr\",         \"first_name\": \"tessst"+c+"\",         \"last_name\": \"tessst"+c+"\",         \"type\": \"CHD\",         \"date_of_birth\": \"2007-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripautomation@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     },     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": 521     }   } }";
		}
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
		//System.out.println("---"+postString);
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create"));
	StringEntity params = new StringEntity(postString);
	itinenaryCall.setEntity(params);
	if(source.equalsIgnoreCase("b2c")){
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	}
	else{
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	}
	//itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	//itinenaryCall.setHeader("ct-auth",value);
	//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
	HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
	int hitStatus = itinenaryResponse.getStatusLine().getStatusCode();
	Reporter.log("Http response Staus for itinenaryResponse="+hitStatus);
	Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
	HttpEntity entityIti = itinenaryResponse.getEntity();
/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
	System.out.println("response string=="+responseString);*/
	Document docIti =null;
	BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
	String str12 ="";
	StringBuffer sb12 =new StringBuffer();
	while((str12=br12.readLine())!=null){
	sb12.append(str12);
	}

	 itinenaryId = new JSONObject(sb12.toString());
	// System.out.println(itinenaryId);
	}
		else{
			fsame=false;
			j++;
		}
		
		}
		while(!fsame);
	
		return itinenaryId;
	}
	public List<HashMap> getStopOverFlightDetails(HashMap<String, String> hp,HashMap<String, String> hp1,DefaultHttpClient clientSearch,ArrayListMultimap<String, String>  contentref,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,String source) throws Exception{
	clientSearch = new DefaultHttpClient();
	String term="";
		List a1 = new ArrayList<HashMap>();
		
	//	System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		
	    HttpGet get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
		
	    //System.out.println(get);
		//getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"Payloadroundtrip.txt");
		//String farekey=getFarekey("Farekey").toString().split(":")[1];
	    if(source.equalsIgnoreCase("b2c")){
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			get.setHeader("X-CT-SOURCETYPE","B2C");
		}
		else{
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		}   
		//get.setHeader("X-CT-API-KEY",common.value("APIKey"));
	  //  get.setHeader("X-CT-API-KEY",common.value("APIKey"));
	   // get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		int hitStatus = response.getStatusLine().getStatusCode();
		Reporter.log("Http response Staus for GetResponse="+hitStatus);
		Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
		StringBuffer sb = new StringBuffer();
		String str="";
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str=br.readLine())!=null){
			   sb.append(str.trim());
			  
		}
		
       //System.out.println(sb.toString());
       JSONObject jsonObject = new JSONObject(sb.toString());
System.out.println(jsonObject);
       JSONObject airSearchResult1 = jsonObject.getJSONObject("fare");
      // System.out.println("size="+airSearchResult1.length());
    
    	  // JSONObject airSearchResult11 = jsonObject.getJSONObject("fare");
    	   for(int i=1;i<airSearchResult1.length()+1;i++){
    		//   System.out.println();
    		   if(airSearchResult1.getJSONObject(String.valueOf(i)).has("R")){
    		     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
    		  // System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr"));
    		   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr")));
    		  
    		   }
    		   else if(airSearchResult1.getJSONObject(String.valueOf(i)).has("N")){
	     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getString("fk"));
	 //  System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr"));
	   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr")));
	   
	   }
    		   
    		   
    		//  System.out.println( "i value="+i+"   and "+airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
    		  
    	   }
    	   
    	 
       
       JSONObject airSearchResult = jsonObject.getJSONObject("mapping");
       
       JSONArray onward = jsonObject.getJSONObject("mapping").getJSONArray("onward");
       JSONObject secdetails = jsonObject.getJSONObject("content");
       for(int l=1;l<secdetails.length()+1;l++){
    	   hp.put("content"+l, secdetails.getJSONObject(String.valueOf(l)).get("fr").toString());
    	   hp.put("arrivaldate"+l,secdetails.getJSONObject(String.valueOf(l)).get("ad").toString());
    	   hp.put("flightno"+l,secdetails.getJSONObject(String.valueOf(l)).get("fk").toString().split(carrier.trim())[1].split("_")[0].trim().replace("-",""));
    	//   System.out.println(hp.get("--------------flightno"+l));
    	//  System.out.println(secdetails.getJSONObject(String.valueOf(l)).get("to"));
    	 // System.out.println("l="+l+"    "+hp.get("content"+l));
       }
       
       int x=1;
       for(int j=1;j<onward.length();j++){
    	   
    	  
    	   JSONArray contentinmapping = onward.getJSONObject(j).getJSONArray("c");
    	  
    	   if(contentinmapping.length()==2){
    		   contentref.put(String.valueOf(x),contentinmapping.getString(0).toString());
    		   contentref.put(String.valueOf(x),contentinmapping.getString(1).toString());
    		   //System.out.println(contentref.get(String.valueOf(b)).toArray()[1].toString());
    		//   System.out.println(contentref.get(String.valueOf(b)).toString());
    		   
    		   hp.put("contentref"+x,contentref.get(String.valueOf(x)).toString());
    		   hp.put("fareref"+x,onward.getJSONObject(j).get("f").toString());
    		  
    		   /*String flg=hp.get("flightno"+hp.get("contentref"+j).toString().split(",")[0].replace("[","").trim());
    			String flg1=hp.get("flightno"+hp.get("contentref"+j).toString().split(",")[1].replace("]","").trim());
    			System.out.println("flight 1=="+flg);
    			System.out.println("flight 2==="+flg1);*/
    		 //  System.out.println(hp1.get(hp.get("fareref"+x)));
    		   //System.out.println("amount="+hp1.get("amount"+hp.get("fareref"+x)));
    		  // System.out.println(hp.get("contentref"+x).toString().split(",")[1].replace("]",""));
    		  // System.out.println("content"+hp.get("contentref"+x).toString().split(",")[1].replace("]","").trim());
    		   //System.out.println(hp.get("content"+hp.get("contentref"+x).toString().split(",")[1].replace("]","").trim()));
    		   //System.out.println(hp.get("arrivaldate"+hp.get("contentref"+x).toString().split(",")[1].replace("]","").trim()));
    		 //  System.out.println( hp.get("contentref"+j));
    		// System.out.println(hp.get("fareref"+j));
    		  // System.out.println(hp.get("contentref"+j).toString());
    		  // System.out.println(contentref.get(String.valueOf(b)));
    		  
    		   x++;
    	   }
    	   else {
    		   Reporter.log("StopOver not Found");
    	   }
    	   }
    	   a1.add(hp);
   a1.add(hp1);
   a1.add(contentref);
   return a1;
	
	}
	public JSONObject getRoundTripMultiPAXItineraryID(DefaultHttpClient	clientSearch,HashMap<String, String> hp,HashMap<String, String> hp1,HashMap<String, String> fareKey,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,int i,String source) throws Exception, IOException{
		
		boolean flightUnavailable=false;
		
		clientSearch=new DefaultHttpClient();
		String key=hp1.get(fareKey.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		//System.out.println(key);
		String amount=hp1.get("amount"+fareKey.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		String key1=hp1.get(hp.get(String.valueOf(i).toString().replace("[","").replace("]","")));
		String amount1=hp1.get("amount"+hp.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		String flightno =key.split(carrier.trim())[1].split("_")[1].trim();
		String flightno1 =key1.split(carrier.trim())[1].split("_")[1].trim();
		//System.out.println("----"+amount+"++++++++++"+amount1+"    flightno="+flightno+"    flightno1="+flightno1+"         farekey="+key+"         farekey1="+key1);


		flightUnavailable= false;
		//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
		// String key = fareKeys.get(String.valueOf(i)).toString();

		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\": "+amount+",        \"fare_key\": \"" +key+"\"       },       {         \"amount\": "+amount1+",        \"fare_key\":  \"" +key1+"\"      }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\": \""+from+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\": \""+flightno+"\",            \"airline\":\""+carrier+"\",            \"operating_airline\": \""+carrier+"\",             \"departure_date\": \""+onwarddate+"\"          }         }       },       {         \"segments\": {           \"2\": {             \"departure_airport\":  \""+to+"\",           \"arrival_airport\":  \""+from+"\",            \"flight_number\": \""+flightno1+"\",            \"airline\": \""+carrier+"\",             \"operating_airline\": \""+carrier+"\",              \"departure_date\":\""+returndate+"\"           }         }       }     ],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"teest"+c+"\",         \"last_name\": \"teest"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       },       {         \"title\": \"Mstr\",         \"first_name\": \"teesttt"+c+"\",         \"last_name\": \"teestttt"+c+"\",         \"type\": \"CHD\",          \"date_of_birth\": \"2013-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripfortesting@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     },     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": 521     }   } }";
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
		//System.out.println("---"+postString);
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create"));
	StringEntity params = new StringEntity(postString);
	itinenaryCall.setEntity(params);
	if(source.equalsIgnoreCase("b2c")){
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	}
	else{
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	}
	//itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	//itinenaryCall.setHeader("ct-auth",value);
	//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
	HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
	int hitStatus = itinenaryResponse.getStatusLine().getStatusCode();
	Reporter.log("Http Staus for Itinerary response="+hitStatus);
	HttpEntity entityIti = itinenaryResponse.getEntity();
	
	/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
	System.out.println("response string=="+responseString);*/
	Document docIti =null;
	BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
	String str12 ="";
	StringBuffer sb12 =new StringBuffer();
	while((str12=br12.readLine())!=null){
	sb12.append(str12);
	}

	JSONObject itinenaryId = new JSONObject(sb12.toString());

	
		return itinenaryId;
	}
	public ArrayListMultimap<String, String>  getFareKeyandFlightDetails( DefaultHttpClient clientSearch1,ArrayListMultimap<String, String> fareKeys ,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String source) throws URISyntaxException, ClientProtocolException, IOException, JSONException{
		String term="";
		String term1="";
		String onwarddate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		System.out.println("onward date="+onwarddate1+"   returndate="+returndate1);
	Reporter.log("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0",true);
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
	//System.out.println("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		HttpGet get = new HttpGet(new URI("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
		System.out.println(common.value("APIKey"));
		if(source.equalsIgnoreCase("b2c")){
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			get.setHeader("X-CT-SOURCETYPE","B2C");
			
		}
		else{
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
					}
	
	    clientSearch1 = new DefaultHttpClient();
	   
		HttpResponse response = clientSearch1.execute(get);
		int hitStatus = response.getStatusLine().getStatusCode();
		Reporter.log("Http response Staus for Get Request="+hitStatus);
		Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
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

	//System.out.println(""+jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("fare");
		
		for(int i=1;i<airSearchResult.length();i++){
			
				Iterator keys=airSearchResult.getJSONObject(String.valueOf(i)).keys();
				//System.out.println(keys.toString());
			
				test:  while(keys.hasNext()){
				   String key=(String)keys.next();
				  //System.out.println(key);
				   if(key.equalsIgnoreCase("n")){
					 term=key;
					   break test;
					   }
				    if(key.equalsIgnoreCase("r")){
				 term=key;
					   break test;
					   }
				   if(key.equalsIgnoreCase("hbag")){
				 term=key;
				   break test;
				   }
				  if(key.equalsIgnoreCase("corp")){
					  term=key;
					   break test;
					   }
				   
				  }
				//System.out.println("-----"+airSearchResult.length());
				if(term.equalsIgnoreCase("hbag")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr").toString());
					//System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("r")){
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
			//System.out.println(fareKeys.get(String.valueOf(i)));
			//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
				}
				if(term.equalsIgnoreCase("corp")){
					
					Iterator Ikey=airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).keys();
					//System.out.println(keys.toString());
				
					test:  while(Ikey.hasNext()){
					   String key=(String)Ikey.next();
					  //System.out.println(key);
					   if(key.equalsIgnoreCase("n")){
						 term1=key;
						   break test;
						   }
					    if(key.equalsIgnoreCase("r")){
					 term1=key;
						   break test;
						   }
					   if(key.equalsIgnoreCase("hbag")){
					 term1=key;
					   break test;
					   }
					  if(key.equalsIgnoreCase("corp")){
						  term1=key;
						   break test;
						   }
					   
					  }
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject(term1).get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject(term1).get("pr").toString());
					//System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("n")){
					//System.out.println("i value="+i);
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
				//	System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
						}
			
		}
		return fareKeys;
	}
	
	public ArrayListMultimap<String, String>  getFareKeyandFlightDetailsAE( DefaultHttpClient clientSearch1,ArrayListMultimap<String, String> fareKeys ,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String source) throws URISyntaxException, ClientProtocolException, IOException, JSONException{
		String term="";
		/*String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		System.out.println("onward date="+onwarddate+"   returndate="+returndate1);*/
	Reporter.log("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&country=AE&currency=AED&jsonVersion=1.0",true);
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
	//System.out.println("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		HttpGet get = new HttpGet(new URI("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&country=AE&currency=AED&jsonVersion=1.0"));
		System.out.println(common.value("APIKey"));
		if(source.equalsIgnoreCase("b2c")){
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			get.setHeader("X-CT-SOURCETYPE","B2C");
			
		}
		else{
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
					}
	
	    clientSearch1 = new DefaultHttpClient();
	   
		HttpResponse response = clientSearch1.execute(get);
		int hitStatus = response.getStatusLine().getStatusCode();
		Reporter.log("Http response Staus for Get Request="+hitStatus);
		Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
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

	//System.out.println(""+jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("fare");
		
		for(int i=1;i<airSearchResult.length();i++){
			
				Iterator keys=airSearchResult.getJSONObject(String.valueOf(i)).keys();
				//System.out.println(keys.toString());
			
				test:  while(keys.hasNext()){
				   String key=(String)keys.next();
				  //System.out.println(key);
				   if(key.equalsIgnoreCase("n")){
					 term=key;
					   break test;
					   }
				   if(key.equalsIgnoreCase("r")){
				 term=key;
					   break test;
					   }
				   if(key.equalsIgnoreCase("hbag")){
				 term=key;
				   break test;
				   }
				   /*if(key.equalsIgnoreCase("corp")){
					  term=key;
					   break test;
					   }*/
				   
				  }
				//System.out.println("-----"+airSearchResult.length());
				if(term.equalsIgnoreCase("hbag")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr").toString());
					//System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("r")){
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
			//System.out.println(fareKeys.get(String.valueOf(i)));
			//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
				}
				if(term.equalsIgnoreCase("corp")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr").toString());
					//System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("n")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
				//	System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
						}
			
		}
		return fareKeys;
	}
	public ArrayListMultimap<String, String>  betagetFareKeyandFlightDetails( DefaultHttpClient clientSearch1,ArrayListMultimap<String, String> fareKeys ,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String source) throws URISyntaxException, ClientProtocolException, IOException, JSONException{
		String term="";
		/*String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		System.out.println("onward date="+onwarddate+"   returndate="+returndate1);*/
		System.out.println("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
	Reporter.log("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0",true);
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
	//System.out.println("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		HttpGet get = new HttpGet(new URI("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
		if(source.equalsIgnoreCase("b2c")){
			get.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
			get.setHeader("X-CT-SOURCETYPE","B2C");
			
		}
		else{
			get.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
					}
	
	    clientSearch1 = new DefaultHttpClient();
	   
		HttpResponse response = clientSearch1.execute(get);
		 HttpParams paramss = clientSearch1.getParams();
		    HttpConnectionParams.setConnectionTimeout(paramss,180000);
		    HttpConnectionParams.setSoTimeout(paramss, 180000);
		int hitStatus = response.getStatusLine().getStatusCode();
		Reporter.log("Http response Staus for Get Request="+hitStatus);
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

	//System.out.println("-----------"+jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("fare");
		
		for(int i=1;i<airSearchResult.length();i++){
			
				Iterator keys=airSearchResult.getJSONObject(String.valueOf(i)).keys();
				test:  while(keys.hasNext()){
				   String key=(String)keys.next();
				  // System.out.println(key);
				   if(key.equalsIgnoreCase("n")){
					 term=key;
					   break test;
					   }
				   if(key.equalsIgnoreCase("r")){
				 term=key;
					   break test;
					   }
				   if(key.equalsIgnoreCase("hbag")){
				 term=key;
				   break test;
				   }
				   if(key.equalsIgnoreCase("corp")){
					  term=key;
					   break test;
					   }
				   
				  }
				//System.out.println("-----"+airSearchResult.length());
				if(term.equalsIgnoreCase("hbag")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr").toString());
					//System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("r")){
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
			//System.out.println(fareKeys.get(String.valueOf(i)));
			//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
				}
				if(term.equalsIgnoreCase("corp")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr").toString());
					//System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("n")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
				//	System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
						}
			
		}
		return fareKeys;
	}
	public ArrayList<String>  betaSearchResponse( DefaultHttpClient clientSearch1,ArrayList<String> fareKeys ,String from,String to,String aCount,String cCount,String onwarddate,String source) throws URISyntaxException, ClientProtocolException, IOException, JSONException{
		String term="";
		/*String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		System.out.println("onward date="+onwarddate+"   returndate="+returndate1);*/
	//	System.out.println("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&cabin-type=Economy&jsonVersion=1.0");
	Reporter.log("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&cabin-type=Economy&jsonVersion=1.0",true);
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
	//System.out.println("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		HttpGet get = new HttpGet(new URI("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&cabin-type=Economy&jsonVersion=1.0"));
		if(source.equalsIgnoreCase("b2c")){
			get.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
			get.setHeader("X-CT-SOURCETYPE","B2C");
			
		}
		else{
			get.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
					}
	
	    clientSearch1 = new DefaultHttpClient();
	   
		HttpResponse response = clientSearch1.execute(get);
		int hitStatus = response.getStatusLine().getStatusCode();
		Reporter.log("Http response Staus for Get Request="+hitStatus);
		HttpEntity entity = response.getEntity();
		Document doc =null;
		StringBuffer sb1 = new StringBuffer();
		String str1="";
		BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str1=br1.readLine())!=null){
			sb1.append(str1);
		}


		JSONObject jsonObject = new JSONObject(sb1.toString());

//System.out.println("-----------"+jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("fare");
		
		for(int i=1;i<airSearchResult.length();i++){
			//System.out.println("i value="+i);
			
				Iterator keys=airSearchResult.getJSONObject(String.valueOf(i)).keys();
				test:  while(keys.hasNext()){
				   String key=(String)keys.next();
				  // System.out.println(key);
				   if(key.equalsIgnoreCase("n")){
					 term=key;
					   break test;
					   }
				   if(key.equalsIgnoreCase("r")){
				 term=key;
					   break test;
					   }
				   if(key.equalsIgnoreCase("hbag")){
				 term=key;
				   break test;
				   }
				   if(key.equalsIgnoreCase("corp")){
					  term=key;
					   break test;
					   }
				   
				  }
				//System.out.println("-----"+airSearchResult.length());
				if(term.equalsIgnoreCase("hbag")){
					fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString().split("|")[2].split("_")[2]);
					//fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString().split("|")[2].split("_")[2]);
					//System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("r")){
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString().split("retail")[1].split("_")[1]);
			//fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString().split("retail")[1].split("_")[1]);
			fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString().split("\\|")[2].split("_")[2]);
			//System.out.println(fareKeys.get(String.valueOf(i)));
			//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
				}
				
				if(term.equalsIgnoreCase("corp")){
					
					try {
					fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString().split("\\|")[2].split("_")[2]);
					}
					catch(Exception e) {
						try {
						//fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("N").get("fk").toString().split("retail")[1].split("_")[1]);
						fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("N").get("fk").toString().split("\\|")[2].split("_")[2]);
						}
						catch(Exception e1) {
							//fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString().split("retail")[1].split("_")[1]);
							fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString().split("\\|")[2].split("_")[2]);	
						}
						}
					//System.out.println(fareKeys.get(String.valueOf(i)));
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("n")){
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString().split("\\|")[2].split("_")[2]);
					fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString().split("\\|")[2].split("_")[2]);
					//fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString().split("retail")[1].split("_")[1]);
					//fareKeys.add(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString().split("/|")[2].split("_")[2]);
			
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
						}
			
		}
		return fareKeys;
	}
	
	public ArrayListMultimap<String, String>  getViaFareKeyandFlightDetails( DefaultHttpClient clientSearch1,ArrayListMultimap<String, String> fareKeys ,ArrayListMultimap<String, String> mapping ,HashMap<String,String> content,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String source) throws URISyntaxException, ClientProtocolException, IOException, JSONException{
		String term="";
		/*String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		System.out.println("onward date="+onwarddate+"   returndate="+returndate1);*/
	
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
		//System.out.println("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		HttpGet get = new HttpGet(new URI("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
		if(source.equalsIgnoreCase("b2c")){
		get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			get.setHeader("X-CT-SOURCETYPE","B2C");
		}
		else{
			get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		}
		//get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		
	    clientSearch1 = new DefaultHttpClient();
	   
		HttpResponse response = clientSearch1.execute(get);
		//System.out.println(response);
		HttpEntity entity = response.getEntity();
		Document doc =null;
		StringBuffer sb1 = new StringBuffer();
		String str1="";
		BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str1=br1.readLine())!=null){
			sb1.append(str1);
		}


		JSONObject jsonObject = new JSONObject(sb1.toString());

		//System.out.println(jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("fare");
		
		for(int i=1;i<airSearchResult.length();i++){
			
				Iterator keys=airSearchResult.getJSONObject(String.valueOf(i)).keys();
				test:  while(keys.hasNext()){
				   String key=(String)keys.next();
				  // System.out.println(key);
				   if(key.equalsIgnoreCase("n")){
					 term=key;
					   break test;
					   }
				   if(key.equalsIgnoreCase("r")){
				 term=key;
					   break test;
					   }
				   if(key.equalsIgnoreCase("hbag")){
				 term=key;
				   break test;
				   }
				   if(key.equalsIgnoreCase("corp")){
					  term=key;
					   break test;
					   }
				   
				  }
				//System.out.println("-----"+airSearchResult.length());
				if(term.equalsIgnoreCase("hbag")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr").toString());
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("r")){
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
			//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
				}
				if(term.equalsIgnoreCase("corp")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("pr").toString());
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).getJSONObject("R").get("fk"));
				}
				if(term.equalsIgnoreCase("n")){
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk").toString());
					fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("pr").toString());
					//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject(term).get("fk"));
						}
			
		}
		JSONObject mapping1 = jsonObject.getJSONObject("mapping");
		//System.out.println(mapping1.getJSONArray("onward"));
		for(int i=0;i<mapping1.getJSONArray("onward").length();i++){
			//System.out.println(mapping1.getJSONArray("onward").getJSONArray(String.valueOf(i)).getString("f")));
		}
		
		return fareKeys;
	}
	public JSONObject createItinerary(String amount,String key1,String from,String to,String flightno,String carrier,String onwarddate,String source) throws URISyntaxException, IOException, Exception{
		Random r = new Random();
	//	System.out.println("++++++++++++++++++"+r);
		char c = (char) (r.nextInt(26) + 'a');
		//System.out.println("-------------------------"+c);
		String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\":\""+amount+"\",         \"fare_key\": \"" +key1+"\"      }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\":  \""+from+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\":\""+flightno+"\",             \"airline\": \""+carrier+"\",             \"operating_airline\":\""+carrier+"\",             \"departure_date\": \""+onwarddate+"\"}}}],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"testrr"+c+"\",         \"last_name\":  \"testyrrr"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"ns.likhitha@cleartrip.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"1111122222\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     }   } }";
		//String postString="{\"itinerary\":{\"cabin_type\":\"E\",\"fare_details\":[{\"amount\": \""+amount+"\",\"fare_key\": \"" +key1+"\" }],\"flights\":[{\"segments\":{\"1\":{\"departure_airport\": \""+from+"\",\"arrival_airport\": \""+to+"\", \"flight_number\": \""+flightno+"\", \"airline\": \""+carrier+"\",\"operating_airline\":  \""+carrier+"\",\"departure_date\":\""+onwarddate+"\"}}}],\"pax_info_list\":[{\"title\":\"Mr\",\"first_name\":\"test"+c+"\",\"last_name\":\"test"+c+"\",\"type\":\"ADT\",\"date_of_birth\":\"1988-11-15\",\"pax_nationality\":\"IN\",\"poi_details\":{\"id_card_number\":\"423\",\"id_card_type\":\"GOVT\",\"visa_type\":\"Business\"},\"passport_detail\":{\"passport_number\":\"345678\",\"passport_exp_date\":\"2018-11-15\",\"passport_issuing_country\":\"IN\",\"passport_issue_date\":\"2013-11-15\"},\"frequent_flyer_numbers\":[{\"freq_flier_number\":\"56789\",\"applicable_airline\":\"SG\",\"airline\":\"SG\"},{\"freq_flier_number\":\"56789\",\"applicable_airline\":\"SG\",\"airline\":\"SG\"}]}],\"contact_detail\":{\"title\":\"Mr\",\"first_name\":\"Frank\",\"last_name\":\"Dsouza\",\"email\":\"cleartripautomation@gmail.com\",\"address\":\"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",\"mobile\":\"919844000000\",\"landline\":\"02240554000\",\"city_name\":\"Mumbai\",\"state_name\":\"Maharashtra\",\"country_name\":\"India\",\"pin_code\":\"400011\"},\"payment_detail\":{\"payment_type\":\"DA\",\"deposit_account_id\":521}}}";
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
	//System.out.println("---"+postString);
		 DefaultHttpClient clientSearch1 = new DefaultHttpClient();
	System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create");
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create"));
StringEntity params = new StringEntity(postString);
itinenaryCall.setEntity(params);
if(source.equalsIgnoreCase("b2c")){
	itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
}
else{
	itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			}


//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
//itinenaryCall.setHeader("CT-AUTH",value);
//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
HttpResponse itinenaryResponse = clientSearch1.execute(itinenaryCall);
int hitStatus = itinenaryResponse.getStatusLine().getStatusCode();
Reporter.log("Http response Staus for itinenaryResponse="+hitStatus);
Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
HttpEntity entityIti = itinenaryResponse.getEntity();
/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
System.out.println("response string=="+responseString);*/
Document docIti =null;
BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
String str12 ="";
StringBuffer sb12 =new StringBuffer();
while((str12=br12.readLine())!=null){
sb12.append(str12);
}
//System.out.println("---"+sb12);
JSONObject itinenaryId = new JSONObject(sb12.toString());
//System.out.println("itinerary id="+itinenaryId);
		//System.out.println("itinerary id="+itinenaryId.getString("itinerary_id"));
try {
		Reporter.log("itinerary id="+itinenaryId.getString("itinerary_id"));
}
catch(Exception e) {
	Reporter.log(itinenaryId.toString(),true);
}
		return itinenaryId;
	}
	
	public JSONObject createItineraryAE(String amount,String key1,String from,String to,String flightno,String carrier,String onwarddate,String source) throws URISyntaxException, IOException, Exception{
		Random r = new Random();
	//	System.out.println("++++++++++++++++++"+r);
		char c = (char) (r.nextInt(26) + 'a');
		//System.out.println("-------------------------"+c);
		String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\":\""+amount+"\",         \"fare_key\": \"" +key1+"\"      }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\":  \""+from+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\":\""+flightno+"\",             \"airline\": \""+carrier+"\",             \"operating_airline\":\""+carrier+"\",             \"departure_date\": \""+onwarddate+"\"}}}],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"testa"+c+"\",         \"last_name\":  \"testy"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripautomation@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     }   } }";
		//String postString="{\"itinerary\":{\"cabin_type\":\"E\",\"fare_details\":[{\"amount\": \""+amount+"\",\"fare_key\": \"" +key1+"\" }],\"flights\":[{\"segments\":{\"1\":{\"departure_airport\": \""+from+"\",\"arrival_airport\": \""+to+"\", \"flight_number\": \""+flightno+"\", \"airline\": \""+carrier+"\",\"operating_airline\":  \""+carrier+"\",\"departure_date\":\""+onwarddate+"\"}}}],\"pax_info_list\":[{\"title\":\"Mr\",\"first_name\":\"test"+c+"\",\"last_name\":\"test"+c+"\",\"type\":\"ADT\",\"date_of_birth\":\"1988-11-15\",\"pax_nationality\":\"IN\",\"poi_details\":{\"id_card_number\":\"423\",\"id_card_type\":\"GOVT\",\"visa_type\":\"Business\"},\"passport_detail\":{\"passport_number\":\"345678\",\"passport_exp_date\":\"2018-11-15\",\"passport_issuing_country\":\"IN\",\"passport_issue_date\":\"2013-11-15\"},\"frequent_flyer_numbers\":[{\"freq_flier_number\":\"56789\",\"applicable_airline\":\"SG\",\"airline\":\"SG\"},{\"freq_flier_number\":\"56789\",\"applicable_airline\":\"SG\",\"airline\":\"SG\"}]}],\"contact_detail\":{\"title\":\"Mr\",\"first_name\":\"Frank\",\"last_name\":\"Dsouza\",\"email\":\"cleartripautomation@gmail.com\",\"address\":\"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",\"mobile\":\"919844000000\",\"landline\":\"02240554000\",\"city_name\":\"Mumbai\",\"state_name\":\"Maharashtra\",\"country_name\":\"India\",\"pin_code\":\"400011\"},\"payment_detail\":{\"payment_type\":\"DA\",\"deposit_account_id\":521}}}";
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
	//System.out.println("---"+postString);
		 DefaultHttpClient clientSearch1 = new DefaultHttpClient();
	System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create?country=AE&currency=AED");
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create?country=AE&currency=AED"));
StringEntity params = new StringEntity(postString);
itinenaryCall.setEntity(params);
if(source.equalsIgnoreCase("b2c")){
	itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
}
else{
	itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			}


//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
//itinenaryCall.setHeader("CT-AUTH",value);
//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
HttpResponse itinenaryResponse = clientSearch1.execute(itinenaryCall);
int hitStatus = itinenaryResponse.getStatusLine().getStatusCode();
Reporter.log("Http response Staus for itinenaryResponse="+hitStatus);
Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
HttpEntity entityIti = itinenaryResponse.getEntity();
/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
System.out.println("response string=="+responseString);*/
Document docIti =null;
BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
String str12 ="";
StringBuffer sb12 =new StringBuffer();
while((str12=br12.readLine())!=null){
sb12.append(str12);
}
//System.out.println("---"+sb12);
JSONObject itinenaryId = new JSONObject(sb12.toString());
//System.out.println("itinerary id="+itinenaryId);
		//System.out.println("itinerary id="+itinenaryId.getString("itinerary_id"));
try {
		Reporter.log("itinerary id="+itinenaryId.getString("itinerary_id"));
}
catch(Exception e) {
	Reporter.log(itinenaryId.toString(),true);
}
		return itinenaryId;
	}
	public JSONObject betacreateItinerary(String amount,String key1,String from,String to,String flightno,String carrier,String onwarddate,String source) throws URISyntaxException, IOException, Exception{
		Random r = new Random();
	//	System.out.println("++++++++++++++++++"+r);
		char c = (char) (r.nextInt(26) + 'a');
		//System.out.println("-------------------------"+c);
		String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\":\""+amount+"\",         \"fare_key\": \"" +key1+"\"      }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\":  \""+from+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\":\""+flightno+"\",             \"airline\": \""+carrier+"\",             \"operating_airline\":\""+carrier+"\",             \"departure_date\": \""+onwarddate+"\"}}}],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"testa"+c+"\",         \"last_name\":  \"testy"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripautomation@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     }   } }";
		//String postString="{\"itinerary\":{\"cabin_type\":\"E\",\"fare_details\":[{\"amount\": \""+amount+"\",\"fare_key\": \"" +key1+"\" }],\"flights\":[{\"segments\":{\"1\":{\"departure_airport\": \""+from+"\",\"arrival_airport\": \""+to+"\", \"flight_number\": \""+flightno+"\", \"airline\": \""+carrier+"\",\"operating_airline\":  \""+carrier+"\",\"departure_date\":\""+onwarddate+"\"}}}],\"pax_info_list\":[{\"title\":\"Mr\",\"first_name\":\"test"+c+"\",\"last_name\":\"test"+c+"\",\"type\":\"ADT\",\"date_of_birth\":\"1988-11-15\",\"pax_nationality\":\"IN\",\"poi_details\":{\"id_card_number\":\"423\",\"id_card_type\":\"GOVT\",\"visa_type\":\"Business\"},\"passport_detail\":{\"passport_number\":\"345678\",\"passport_exp_date\":\"2018-11-15\",\"passport_issuing_country\":\"IN\",\"passport_issue_date\":\"2013-11-15\"},\"frequent_flyer_numbers\":[{\"freq_flier_number\":\"56789\",\"applicable_airline\":\"SG\",\"airline\":\"SG\"},{\"freq_flier_number\":\"56789\",\"applicable_airline\":\"SG\",\"airline\":\"SG\"}]}],\"contact_detail\":{\"title\":\"Mr\",\"first_name\":\"Frank\",\"last_name\":\"Dsouza\",\"email\":\"cleartripautomation@gmail.com\",\"address\":\"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",\"mobile\":\"919844000000\",\"landline\":\"02240554000\",\"city_name\":\"Mumbai\",\"state_name\":\"Maharashtra\",\"country_name\":\"India\",\"pin_code\":\"400011\"},\"payment_detail\":{\"payment_type\":\"DA\",\"deposit_account_id\":521}}}";
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
	//System.out.println("---"+postString);
		 DefaultHttpClient clientSearch1 = new DefaultHttpClient();
	//System.out.println("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/json/create");
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/json/create"));
StringEntity params = new StringEntity(postString);
itinenaryCall.setEntity(params);
if(source.equalsIgnoreCase("b2c")){
	itinenaryCall.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
	itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
}
else{
	itinenaryCall.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
			}


//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
//itinenaryCall.setHeader("CT-AUTH",value);
//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
HttpResponse itinenaryResponse = clientSearch1.execute(itinenaryCall);
HttpParams params2 = clientSearch1.getParams();
HttpConnectionParams.setConnectionTimeout(params2,180000);
HttpConnectionParams.setSoTimeout(params2, 180000);



int hitStatus = itinenaryResponse.getStatusLine().getStatusCode();
Reporter.log("Http response Staus for itinenaryResponse="+hitStatus);
HttpEntity entityIti = itinenaryResponse.getEntity();
/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
System.out.println("response string=="+responseString);*/
Document docIti =null;
BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
String str12 ="";
StringBuffer sb12 =new StringBuffer();
while((str12=br12.readLine())!=null){
sb12.append(str12);
}
//System.out.println("---"+sb12);
JSONObject itinenaryId = new JSONObject(sb12.toString());
//System.out.println("itinerary id="+itinenaryId);
		//System.out.println("itinerary id="+itinenaryId.getString("itinerary_id"));
		Reporter.log("itinerary id="+itinenaryId.getString("itinerary_id"));
		return itinenaryId;
	}
	public JSONObject createItinerary1(String amount,String key1,String from,String to,String flightno,String carrier,String onwarddate,String source) throws URISyntaxException, IOException, Exception{
		Random r = new Random();
	//	System.out.println("++++++++++++++++++"+r);
		char c = (char) (r.nextInt(26) + 'a');
		//System.out.println("-------------------------"+c);
		String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\":  \""+amount+"\",         \"fare_key\":  \"" +key1+"\"       }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\":  \""+from+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\":  \""+flightno+"\",             \"airline\": \""+carrier+"\",            \"operating_airline\": \""+carrier+"\",            \"departure_date\": \""+onwarddate+"\"        }         }       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"test"+c+"\",       \"last_name\": \"test"+c+"\",       \"email\": \"cleartripautomation@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     }   } }";
		//String postString="{\"itinerary\":{\"cabin_type\":\"E\",\"fare_details\":[{\"amount\": \""+amount+"\",\"fare_key\": \"" +key1+"\" }],\"flights\":[{\"segments\":{\"1\":{\"departure_airport\": \""+from+"\",\"arrival_airport\": \""+to+"\", \"flight_number\": \""+flightno+"\", \"airline\": \""+carrier+"\",\"operating_airline\":  \""+carrier+"\",\"departure_date\":\""+onwarddate+"\"}}}],\"pax_info_list\":[{\"title\":\"Mr\",\"first_name\":\"test"+c+"\",\"last_name\":\"test"+c+"\",\"type\":\"ADT\",\"date_of_birth\":\"1988-11-15\",\"pax_nationality\":\"IN\",\"poi_details\":{\"id_card_number\":\"423\",\"id_card_type\":\"GOVT\",\"visa_type\":\"Business\"},\"passport_detail\":{\"passport_number\":\"345678\",\"passport_exp_date\":\"2018-11-15\",\"passport_issuing_country\":\"IN\",\"passport_issue_date\":\"2013-11-15\"},\"frequent_flyer_numbers\":[{\"freq_flier_number\":\"56789\",\"applicable_airline\":\"SG\",\"airline\":\"SG\"},{\"freq_flier_number\":\"56789\",\"applicable_airline\":\"SG\",\"airline\":\"SG\"}]}],\"contact_detail\":{\"title\":\"Mr\",\"first_name\":\"Frank\",\"last_name\":\"Dsouza\",\"email\":\"cleartripautomation@gmail.com\",\"address\":\"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",\"mobile\":\"919844000000\",\"landline\":\"02240554000\",\"city_name\":\"Mumbai\",\"state_name\":\"Maharashtra\",\"country_name\":\"India\",\"pin_code\":\"400011\"},\"payment_detail\":{\"payment_type\":\"DA\",\"deposit_account_id\":521}}}";
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
	//System.out.println("---"+postString);
		 DefaultHttpClient clientSearch1 = new DefaultHttpClient();
		//System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create?adults=1");
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create?adults=1"));
StringEntity params = new StringEntity(postString);
itinenaryCall.setEntity(params);
if(source.equalsIgnoreCase("b2c")){
	itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
}
else{
	itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			}


//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
//itinenaryCall.setHeader("CT-AUTH",value);
//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
HttpResponse itinenaryResponse = clientSearch1.execute(itinenaryCall);
HttpEntity entityIti = itinenaryResponse.getEntity();
/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
System.out.println("response string=="+responseString);*/
Document docIti =null;
BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
String str12 ="";
StringBuffer sb12 =new StringBuffer();
while((str12=br12.readLine())!=null){
sb12.append(str12);
}
//System.out.println("---"+sb12);
JSONObject itinenaryId = new JSONObject(sb12.toString());
//System.out.println("itinerary id="+itinenaryId);
	//	System.out.println("itinerary id="+itinenaryId.getString("itinerary_id"));
		return itinenaryId;
	}
	
	
	public JSONObject createItineraryForMultiPAX(String amount,String key1,String from,String to,String flightno,String carrier,String onwarddate,String source) throws URISyntaxException, IOException, Exception{
		Random r = new Random();
		//	System.out.println("++++++++++++++++++"+r);
			char c = (char) (r.nextInt(26) + 'a');
		String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\":  \""+amount+"\",         \"fare_key\":\"" +key1+"\"      }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\":  \""+from+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\":  \""+flightno+"\",             \"airline\": \""+carrier+"\",             \"operating_airline\": \""+carrier+"\",             \"departure_date\":\""+onwarddate+"\" }         }       }     ],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"teest"+c+"\",         \"last_name\": \"teest"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       },       {         \"title\": \"Mstr\",         \"first_name\": \"teesttt\",         \"last_name\": \"teestttt\",         \"type\": \"CHD\",         \"date_of_birth\": \"2009-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripautomation@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     },     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": 521     }   } }";
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
	//System.out.println("---"+postString);
		 DefaultHttpClient clientSearch1 = new DefaultHttpClient();
		HttpPost itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create"));
StringEntity params = new StringEntity(postString);
itinenaryCall.setEntity(params);
if(source.equalsIgnoreCase("b2c")){
	itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
}
else{
	itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
}

//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
//itinenaryCall.setHeader("CT-AUTH",value);
//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
HttpResponse itinenaryResponse = clientSearch1.execute(itinenaryCall);
HttpEntity entityIti = itinenaryResponse.getEntity();
/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
System.out.println("response string=="+responseString);*/
Document docIti =null;
BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
String str12 ="";
StringBuffer sb12 =new StringBuffer();
while((str12=br12.readLine())!=null){
sb12.append(str12);
}
//System.out.println(sb12);
JSONObject itinenaryId = new JSONObject(sb12.toString());

//System.out.println("itinerary id="+itinenaryId);
		//System.out.println("itinerary id="+itinenaryId.getString("itinerary_id"));
		return itinenaryId;
	}
	public String getPaymentLink(JSONObject itinenaryId) throws Exception, JSONException{
		String paymentlink;
		String postStringBook1="<price-check><payment-detail><payment-type>CP</payment-type></payment-detail></price-check>";
		String postStringBook="<booking><payment-detail><payment-type>CP</payment-type></payment-detail></booking>";
		
  	   
		
		Document docBook = null;
		Document docBook1 = null;
		//try{
		DefaultHttpClient	clientSearch1 = new DefaultHttpClient();
			   	    	//System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getString("itinerary_id"));
		        HttpPost bookCall1 = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getString("itinerary_id")));
		        StringEntity paramsBook1 = new StringEntity(postStringBook1);
		        bookCall1.setEntity(paramsBook1);
		        bookCall1.setHeader("X-CT-API-KEY",common.value("APIKey"));
		        bookCall1.setHeader("X-CT-SOURCETYPE","B2C");
		        HttpResponse bookResponse1 = clientSearch1.execute(bookCall1);
		       // //System.out.println("book response="+bookResponse1.getEntity());
		       HttpEntity entityBook1 = bookResponse1.getEntity();
		    /* responseString = EntityUtils.toString(entityBook1, "UTF-8");
		  	   //System.out.println("Response string="+responseString);*/
		  	    
		  	    	 DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
		               DocumentBuilder db1 = dbf1.newDocumentBuilder();
		                docBook1 = db1.parse(entityBook1.getContent());
		    	 
		  	   
paymentlink=docBook1.getElementsByTagName("payment-link").item(0).getTextContent();
	 //System.out.println("payment link="+docBook1.getElementsByTagName("payment-link").item(0).getTextContent());
	return paymentlink;
	}
	public JSONObject jsonPriceCheck(JSONObject itinenaryId,DefaultHttpClient clientSearch,String source) throws Exception, JSONException{
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		String a="jivan"+c;
	String b="kotian"+c;
		//String postStringBook="{   \"price_check\": {     \"gst_details\": {       \"gst_number\": \"22BHURJ3851M1Z5\",       \"gst_holder_name\": \"Test holder name\",       \"gst_holder_address\": \"Test , test apartment, test road, test phase, test nagar, Bangalore 560078\",       \"gst_holder_state_code\": \"22\"     }   } }";
		String postStringBook="{   \"price_check\": {     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"jivan"+c+"\",         \"last_name\":\"kotian"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1985-07-02\",         \"pax_nationality\": \"IN\",         \"passport_detail\": {           \"passport_number\": \"E31970131\",           \"passport_exp_date\": \"2023-10-13\",           \"passport_issuing_country\": \"IN\"         },         \"poi_details\": {           \"visa_type\": \"employee\"         }       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Jivan\",       \"last_name\": \"Kotian\",       \"email\": \"deepa.kerur@cleartrip.com\",       \"address\": \"B-121, Sector 5\",       \"mobile\": \"1234567890\",       \"landline\": \"1234567890\",       \"city_name\": \"Noida\",       \"state_name\": \"Uttar Pradesh\",       \"country_name\": \"India\",       \"pin_code\": \"201301\"     }   } }";
		//String poststringBook1="<price-check></price-check>";
	//System.out.println(postStringBook);
		Document docBook = null;
		Document docBook1 = null;
		
		 clientSearch =  new DefaultHttpClient();
		 Reporter.log("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/priceCheck/"+itinenaryId.getString("itinerary_id"));
		System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/priceCheck/"+itinenaryId.getString("itinerary_id"));
			HttpPost bookCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/priceCheck/"+itinenaryId.getString("itinerary_id")));
			//System.out.println(postStringBook);
			StringEntity paramsBook = new StringEntity(postStringBook);
			bookCall.setEntity(paramsBook);
			if(source.equalsIgnoreCase("b2c")){
				bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
				bookCall.setHeader("X-CT-SOURCETYPE","B2C");
			}
			else{
				bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			}

			HttpResponse bookResponse = clientSearch.execute(bookCall);
			//System.out.println("book response="+bookResponse.getEntity());
			HttpEntity entityBook = bookResponse.getEntity();
		/*String responseString = EntityUtils.toString(entityBook, "UTF-8");
			System.out.println("response string=="+responseString);*/
		
			StringBuffer sb11 = new StringBuffer();
				String str11="";
				BufferedReader br11 = new BufferedReader(new InputStreamReader(bookResponse.getEntity().getContent()));
				while((str11=br11.readLine())!=null){
					   sb11.append(str11);
				}
				//System.out.println(sb11);
				JSONObject jsonObject1 = new JSONObject(sb11.toString());
				
				
				
				return jsonObject1;
				
	}
	public JSONObject betajsonPriceCheck(JSONObject itinenaryId,DefaultHttpClient clientSearch,String source) throws Exception, JSONException{
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		String a="jivan"+c;
	String b="kotian"+c;
		//String postStringBook="{   \"price_check\": {     \"gst_details\": {       \"gst_number\": \"22BHURJ3851M1Z5\",       \"gst_holder_name\": \"Test holder name\",       \"gst_holder_address\": \"Test , test apartment, test road, test phase, test nagar, Bangalore 560078\",       \"gst_holder_state_code\": \"22\"     }   } }";
		String postStringBook="{   \"price_check\": {     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"jivan"+c+"\",         \"last_name\":\"kotian"+c+"\",         \"type\": \"ADT\",         \"date_of_birth\": \"1985-07-02\",         \"pax_nationality\": \"IN\",         \"passport_detail\": {           \"passport_number\": \"E31970131\",           \"passport_exp_date\": \"2023-10-13\",           \"passport_issuing_country\": \"IN\"         },         \"poi_details\": {           \"visa_type\": \"employee\"         }       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Jivan\",       \"last_name\": \"Kotian\",       \"email\": \"deepa.kerur@cleartrip.com\",       \"address\": \"B-121, Sector 5\",       \"mobile\": \"1234567890\",       \"landline\": \"1234567890\",       \"city_name\": \"Noida\",       \"state_name\": \"Uttar Pradesh\",       \"country_name\": \"India\",       \"pin_code\": \"201301\"     }   } }";
		//String poststringBook1="<price-check></price-check>";
	//System.out.println(postStringBook);
		Document docBook = null;
		Document docBook1 = null;
		
		 clientSearch =  new DefaultHttpClient();
		 Reporter.log("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/json/priceCheck/"+itinenaryId.getString("itinerary_id"));
		System.out.println("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/json/priceCheck/"+itinenaryId.getString("itinerary_id"));
			HttpPost bookCall = new HttpPost(new URI("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/json/priceCheck/"+itinenaryId.getString("itinerary_id")));
			//System.out.println(postStringBook);
			StringEntity paramsBook = new StringEntity(postStringBook);
			bookCall.setEntity(paramsBook);
			if(source.equalsIgnoreCase("b2c")){
				bookCall.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
				bookCall.setHeader("X-CT-SOURCETYPE","B2C");
			}
			else{
				bookCall.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
			}

			HttpResponse bookResponse = clientSearch.execute(bookCall);
			 HttpParams params2 = clientSearch.getParams();
			    HttpConnectionParams.setConnectionTimeout(params2,180000);
			    HttpConnectionParams.setSoTimeout(params2, 180000);
			
			//System.out.println("book response="+bookResponse.getEntity());
			HttpEntity entityBook = bookResponse.getEntity();
		/*String responseString = EntityUtils.toString(entityBook, "UTF-8");
			System.out.println("response string=="+responseString);*/
		
			StringBuffer sb11 = new StringBuffer();
				String str11="";
				BufferedReader br11 = new BufferedReader(new InputStreamReader(bookResponse.getEntity().getContent()));
				while((str11=br11.readLine())!=null){
					   sb11.append(str11);
				}
				//System.out.println(sb11);
				JSONObject jsonObject1 = new JSONObject(sb11.toString());
				
				
				
				return jsonObject1;
				
	}
	
	public JSONObject jsonBooking(JSONObject itinenaryId,DefaultHttpClient clientSearch,String source) throws Exception, JSONException{
		String postString ="";
		
	   String postStringBook="{   \"booking\": {     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\":"+common.value("AccountID")+"}}}";
	String postStringBook1="{   \"booking\": {     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\":"+common.value("AccountID")+"     },     \"gst_details\": {       \"gst_number\": \"22BHURJ3851M1Z5\"     }   } }{  \"booking\": {    \"payment_detail\": {      \"payment_type\": \"DA\",      \"deposit_account_id\": 521    },    \"gst_details\": {      \"gst_number\": \"22BHURJ3851M1Z5\",      \"gst_holder_name\": \"Test holder name\",      \"gst_holder_address\": \"Test , test apartment, test road, test phase, test nagar, Bangalore 560078\",      \"gst_holder_state_code\": \"22\"    }  } }";
	//String postStringBook2="{   \"booking\": {     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": "+common.value("AccountID")+"     },     \"gst_details\": {       \"gst_holder_state_code\": \"22\"     }   } }";	
	String postStringBook2="{   \"booking\": {     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": 521     },     \"gst_details\": {           }   } }";
	if(common.value("apigst").equalsIgnoreCase("withoutgst")){
	postString=postStringBook;	
	}
	else if(common.value("apigst").equalsIgnoreCase("gst")){
		postString=postStringBook1;	
	}
	else if(common.value("apigst").equalsIgnoreCase("gststateonly")){
		postString=postStringBook2;	
	}
	//String poststringBook1="<price-check></price-check>";
	//System.out.println(postString);
		Document docBook = null;
		Document docBook1 = null;
		
		 clientSearch =  new DefaultHttpClient();
		 HttpPost bookCall;
		 if(common.value("host").equalsIgnoreCase("www")) {
		 Reporter.log("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id"),true);
		//System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id"));
			 bookCall = new HttpPost(new URI("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id")));
			//System.out.println(postString);
		 }
		 else {
			 Reporter.log("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id"));
				System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id"));
					 bookCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id")));
					//System.out.println(postString); 
		 }
			StringEntity paramsBook = new StringEntity(postString);
			//System.out.println(paramsBook);
			bookCall.setEntity(paramsBook);
			if(source.equalsIgnoreCase("b2c")){
				 if(common.value("host").equalsIgnoreCase("www")) {
				bookCall.setHeader("X-CT-API-KEY",common.value("APIKey1"));
				 }
				 else {
					 bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
				 }
				bookCall.setHeader("X-CT-SOURCETYPE","B2C");
			}
			else{
				if(common.value("host").equalsIgnoreCase("www")) {
					bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
					 }
					 else {
						 bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
					 }
			}

			HttpResponse bookResponse = clientSearch.execute(bookCall);
			int hitStatus = bookResponse.getStatusLine().getStatusCode();
			Reporter.log("Http response Staus for bookResponse="+hitStatus,true);
			Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
			//System.out.println("book response="+bookResponse.getEntity());
			HttpEntity entityBook = bookResponse.getEntity();
		/*String responseString = EntityUtils.toString(entityBook, "UTF-8");
			System.out.println("response string=="+responseString);*/
		
			StringBuffer sb11 = new StringBuffer();
				String str11="";
				BufferedReader br11 = new BufferedReader(new InputStreamReader(bookResponse.getEntity().getContent()));
				while((str11=br11.readLine())!=null){
					   sb11.append(str11);
				}
				//System.out.println(sb11);
				JSONObject jsonObject1 = new JSONObject(sb11.toString());
				
				
				
				return jsonObject1;
				
	}
	public JSONObject jsonBooking_GST(JSONObject itinenaryId,DefaultHttpClient clientSearch,String source,boolean GST) throws Exception, JSONException{
		String postString ="";
		
	   String postStringBook="{   \"booking\": {     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\":"+common.value("AccountID")+"}}}";
	String postStringBook1="{  \"booking\": {    \"payment_detail\": {      \"payment_type\": \"DA\",      \"deposit_account_id\": 521    },    \"gst_details\": {      \"gst_number\": \"22BHURJ3851M1Z5\",      \"gst_holder_name\": \"test\",      \"gst_holder_address\": \"cleartrip\",      \"gst_holder_state_code\": \"22\"    }  } }";
	//String postStringBook2="{   \"booking\": {     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": "+common.value("AccountID")+"     },     \"gst_details\": {       \"gst_holder_state_code\": \"22\"     }   } }";	
	String postStringBook2="{   \"booking\": {     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": 521     },     \"gst_details\": {\"gst_holder_state_code\": \"22\"           }   } }";
	
	if(GST){
		postString=postStringBook1;
	}
	else{
		postString=postStringBook2;	
	}
	//String poststringBook1="<price-check></price-check>";
	
		Document docBook = null;
		Document docBook1 = null;
		
		 clientSearch =  new DefaultHttpClient();
		Reporter.log("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id"));
			HttpPost bookCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id")));
			//System.out.println("--------"+postString);
			StringEntity paramsBook = new StringEntity(postString);
			bookCall.setEntity(paramsBook);
			if(source.equalsIgnoreCase("b2c")){
				bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
				bookCall.setHeader("X-CT-SOURCETYPE","B2C");
			}
			else{
				bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			}

			HttpResponse bookResponse = clientSearch.execute(bookCall);
			//System.out.println("book response="+bookResponse.getEntity());
			HttpEntity entityBook = bookResponse.getEntity();
		/*String responseString = EntityUtils.toString(entityBook, "UTF-8");
			System.out.println("response string=="+responseString);*/
		
			StringBuffer sb11 = new StringBuffer();
				String str11="";
				BufferedReader br11 = new BufferedReader(new InputStreamReader(bookResponse.getEntity().getContent()));
				while((str11=br11.readLine())!=null){
					   sb11.append(str11);
				}
				//System.out.println(sb11);
				JSONObject jsonObject1 = new JSONObject(sb11.toString());
				
				
				
				return jsonObject1;
				
	}
public JSONObject jsonBookingWithContactDetails(JSONObject itinenaryId,DefaultHttpClient clientSearch,String source) throws Exception, JSONException{
		
	Random r = new Random();
	//	System.out.println("++++++++++++++++++"+r);
		char c = (char) (r.nextInt(26) + 'a');
		//System.out.println("-------------------------"+c);
		
		String postStringBook="{   \"booking\": {     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": "+common.value("AccountID")+"    },     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank"+c+"\",       \"last_name\": \"Dsouza"+c+"\",       \"email\": \"cleartripfortesting@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     }   } }";
		//String poststringBook1="<price-check></price-check>";
	//System.out.println(postStringBook);
		Document docBook = null;
		Document docBook1 = null;
	//	System.out.println(itinenaryId.getString("itinerary_id"));
		 clientSearch =  new DefaultHttpClient();
			//System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id"));
			HttpPost bookCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id")));
			//System.out.println(postStringBook);
			StringEntity paramsBook = new StringEntity(postStringBook);
			bookCall.setEntity(paramsBook);
			if(source.equalsIgnoreCase("b2c")){
				bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
				bookCall.setHeader("X-CT-SOURCETYPE","B2C");
			}
			else{
				bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			}
			//bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			HttpResponse bookResponse = clientSearch.execute(bookCall);
			//System.out.println("book response="+bookResponse.getEntity());
			HttpEntity entityBook = bookResponse.getEntity();
			/*String responseString = EntityUtils.toString(entityBook, "UTF-8");
			System.out.println("response string=="+responseString);*/
		
			StringBuffer sb11 = new StringBuffer();
				String str11="";
				BufferedReader br11 = new BufferedReader(new InputStreamReader(bookResponse.getEntity().getContent()));
				while((str11=br11.readLine())!=null){
					   sb11.append(str11);
				}
				//System.out.println(sb11);
				JSONObject jsonObject1 = new JSONObject(sb11.toString());
				
				
				
				return jsonObject1;
				
	}
	public JSONObject jsonMultiPAXBooking(JSONObject itinenaryId,DefaultHttpClient clientSearch,String source) throws Exception, JSONException{
		
		
		String postStringBook="{   \"booking\": {     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"test\",         \"last_name\": \"test\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       },       {         \"title\": \"Mstr\",         \"first_name\": \"testtt\",         \"last_name\": \"testttt\",         \"type\": \"CHD\",         \"date_of_birth\": \"2013-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripfortesting@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     },     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\": 521     }   } }";
		//String poststringBook1="<price-check></price-check>";
	//	System.out.println(postStringBook);
		Document docBook = null;
		Document docBook1 = null;
		
		 clientSearch =  new DefaultHttpClient();
			//System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id"));
			HttpPost bookCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id")));
			//System.out.println(postStringBook);
			StringEntity paramsBook = new StringEntity(postStringBook);
			bookCall.setEntity(paramsBook);
			if(source.equalsIgnoreCase("b2c")){
				bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
				bookCall.setHeader("X-CT-SOURCETYPE","B2C");
			}
			else{
				bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			}
			
			//bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			HttpResponse bookResponse = clientSearch.execute(bookCall);
			//System.out.println("book response="+bookResponse.getEntity());
			HttpEntity entityBook = bookResponse.getEntity();
			/*String responseString = EntityUtils.toString(entityBook, "UTF-8");
			System.out.println("response string=="+responseString);*/
		
			StringBuffer sb11 = new StringBuffer();
				String str11="";
				BufferedReader br11 = new BufferedReader(new InputStreamReader(bookResponse.getEntity().getContent()));
				while((str11=br11.readLine())!=null){
					   sb11.append(str11);
				}
				//System.out.println(sb11);
				JSONObject jsonObject1 = new JSONObject(sb11.toString());
				
				
				
				return jsonObject1;
				
	}
		public String getModifiedDate(String date1){
		String date="";
		Calendar c = Calendar.getInstance();
		//System.out.println("input date="+date1);
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );  
		c.add(Calendar.DATE,Integer.parseInt(date1));
		
		String convertedDate=dateFormat.format(c.getTime());    
		//System.out.println("Date increased="+convertedDate);
		
		
		return convertedDate;
	}
	
	public String putDate(String date1){
		String date="";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		cal.add(Calendar.DATE,Integer.parseInt(date1));
		String convertedDate=format.format(cal.getTime());
		
		
		return convertedDate;
	}
	public void checkDetails(RemoteWebDriver driver,String tripid,String carrier,boolean gstpartialdetails) throws Exception{
		boolean gstnum=false;
		boolean gststate=false;
		boolean gststatecode=false;
		HashMap<String,String> hp=new HashMap<String,String>();
		hp=getGstDataFromTripXML(driver,tripid,gstpartialdetails);
		if(!gstpartialdetails){
		String gstnumber=hp.get("gstNumber").trim();
		 String gstholderstatecode=hp.get("gstHolderStateCode").trim();
		 String gstholderaddress=hp.get("gstHolderAddress").trim();
		 String gstholdername=hp.get("gstHolderName").trim();
		 String gstholderstatename=hp.get("gstHolderStateName").trim();
		//if(carrier.equalsIgnoreCase("spicejet")){
		 if(gstnumber.matches("[A-Za-z0-9]+")){
			 gstnum=true;
		 }
		 if(gstholderstatename.equalsIgnoreCase("Chhattisgarh")||(gstholderstatename.equalsIgnoreCase("Maharashtra"))){
			 gststate=true;
		 }
		 if(gstholderstatecode.equalsIgnoreCase("27")||(gstholderstatecode.equalsIgnoreCase("22"))){
			 gststatecode=true;
		 }
			Assert.assertEquals(true,gstnum,"Mismatch in GST number");
			Assert.assertEquals(gstholdername,"test","Mismatch in gst holder name");
			Assert.assertEquals(gstholderaddress,"cleartrip","gst address mismatch");
			
			Assert.assertEquals(true,gststate,"gstholderstatename  mismatch");
			Assert.assertEquals(true,gststatecode,"gstholderstatecode mismatched");
			
		//}
		}	
		else{
			 String gstholderstatename=hp.get("gstHolderStateName").trim();
			Assert.assertEquals(gstholderstatename,"Chhattisgarh","gstholderstatename  mismatch");
		}
	}
	
	
	
	public List<HashMap> getINTLRoundtripFlightDetailsNew(HashMap<String, String> hp,HashMap<String, String> hp1,DefaultHttpClient clientSearch,HashMap<String, String> fareKeys ,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,String source) throws Exception{
		clientSearch = new DefaultHttpClient();
		String term="";
		
			List a1 = new ArrayList<HashMap>();
			HttpGet get;
			System.out.println(common.value("host"));
			if(common.value("host").equalsIgnoreCase("www")) {
							System.out.println(common.value("protocol")+":api.cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		     get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
			}
			else {
				System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
			    get = new HttpGet(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&return-date="+returndate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
				
			}
		    
		    
		    //	//System.out.println(get);
			//getdepositaccountid(common.value("AccountID"),"Book.txt");
			//getdepositaccountid(common.value("AccountID"),"Payloadroundtrip.txt");
			//String farekey=getFarekey("Farekey").toString().split(":")[1];
		    if(source.equalsIgnoreCase("b2c")){
		    	if(common.value("host").equalsIgnoreCase("www")) {
				get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		    	}
		    	else {
		    		System.out.println(common.value("APIKey"));
		    		get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		    	}
				get.setHeader("X-CT-SOURCETYPE","B2C");
			}
			else{
				if(common.value("host").equalsIgnoreCase("www")) {
					get.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
			    	}
			    	else {
			    		get.setHeader("X-CT-API-KEY",common.value("APIKey"));
			    	}
			}
		    //get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		  //  get.setHeader("X-CT-API-KEY",common.value("APIKey"));
		   // get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
			HttpResponse response = clientSearch.execute(get);
			StringBuffer sb = new StringBuffer();
			String str="";
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while((str=br.readLine())!=null){
				   sb.append(str.trim());
				  
			}
			
	     //  System.out.println("------------"+sb.toString());
	       JSONObject jsonObject = new JSONObject(sb.toString());
	//Reporter.log(jsonObject.toString(),true);
	       JSONObject airSearchResult1 = jsonObject.getJSONObject("fare");
	      // System.out.println("size="+airSearchResult1.length());
	    
	    	  // JSONObject airSearchResult11 = jsonObject.getJSONObject("fare");
	    	   for(int i=1;i<airSearchResult1.length()+1;i++){
	    		   String key="";
	    		//   System.out.println();
	    		   if(airSearchResult1.getJSONObject(String.valueOf(i)).has("R")){
	    		     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
	    		  // System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr"));
	    		   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getInt("pr")));
	    		  
	    		   }
	    		   else if(airSearchResult1.getJSONObject(String.valueOf(i)).has("N")){
		     		   hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getString("fk"));
		 //  System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr"));
		   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr")));
		   
		   }
	    		   else if(airSearchResult1.getJSONObject(String.valueOf(i)).has("SPLRT")){
	    			   Iterator<String> keys = airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("SPLRT").keys();
	    			    while(keys.hasNext()){
	    			     key = keys.next();
	    			     //  System.out.println(key);
	    			       break;
	    			    }
		     		 hp1.put(String.valueOf(i),airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("SPLRT").getJSONObject(key).getJSONObject("R").getString("fk"));
		 //  System.out.println(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("N").getInt("pr"));
		   hp1.put("amount"+String.valueOf(i),String.valueOf(airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("SPLRT").getJSONObject(key).getJSONObject("R").getInt("pr")));
		   
		  
	    		   }
	    		   
	    		//  System.out.println( "i value="+i+"   and "+airSearchResult1.getJSONObject(String.valueOf(i)).getJSONObject("R").getString("fk"));
	    		  
	    	   }
	    	   
	    	 
	       
	       JSONObject airSearchResult = jsonObject.getJSONObject("mapping");
	       
	       JSONArray onward = jsonObject.getJSONObject("mapping").getJSONArray("onward");
	       for(int j=0;j<onward.length();j++){
	    	//   System.out.println("---"+onward.getJSONObject(j).get("f"));
	    	   fareKeys.put(String.valueOf(j),onward.getJSONObject(j).get("f").toString());
	    	  // System.out.println(fareKeys.get(String.valueOf(j)));
	    	   
	    	  // System.out.println(hp1.get(fareKeys.get("amount"+String.valueOf(j).toString().replace("[","").replace("]",""))));
	    	  // System.out.println(hp1.get(fareKeys.get(String.valueOf(j)).toString().replace("[","").replace("]","")));
	       }
	      /* JSONArray return1 = jsonObject.getJSONObject("mapping").getJSONArray("return");
	       for(int i=0;i<return1.length();i++){
	    	   //System.out.println("---"+return1.getJSONObject(i).get("f"));
	    	  
	    	   hp.put(String.valueOf(i),return1.getJSONObject(i).get("f").toString());
	    	  // System.out.println(hp.get(String.valueOf(i)));

	   }*/
	   //a1.add(hp);
	   a1.add(hp1);
	   a1.add(fareKeys);
	   return a1;
		}
	
public JSONObject getRoundTripItineraryIDINTL(DefaultHttpClient	clientSearch,HashMap<String, String> hp,HashMap<String, String> hp1,HashMap<String, String> fareKey,String from,String to,String carrier,String aCount,String cCount,String onwarddate,String returndate,int i,String source) throws Exception, IOException{
		
		boolean flightUnavailable=false;
		
		clientSearch=new DefaultHttpClient();
	//	System.out.println(hp);
		//System.out.println(hp1);
		String key=hp1.get(fareKey.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		//System.out.println(key);
		String amount=hp1.get("amount"+fareKey.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
	//System.out.println(hp.get(String.valueOf(i).toString()));
		//String key1=hp1.get(hp.get(String.valueOf(i).toString()));
		//System.out.println(key1);
		//String amount1=hp1.get("amount"+hp.get(String.valueOf(i)).toString().replace("[","").replace("]",""));
		//System.out.println(amount1);
		String flightno =key.split(carrier.trim())[1].split("_")[1].trim();
		 // System.out.println("-----------"+key.split("retail".trim())[2].split("_")[1].trim());
		 // System.out.println("----"+key.split("retail".trim())[2].split("_")[2].trim());
		  String carrier1=key.split("retail".trim())[2].split("_")[1].trim();
		  String flightno1 =key.split("retail".trim())[2].split("_")[2].trim();
		  //System.out.println("----------------"+flightno1);
		//String flightno1 =key.split(carrier.trim())[2].split("_")[1].trim();
		//System.out.println("----"+amount+"++++++++++"+amount1+"    flightno="+flightno+"    flightno1="+flightno1+"         farekey="+key+"         farekey1="+key1);


		flightUnavailable= false;
		//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
		// String key = fareKeys.get(String.valueOf(i)).toString();
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		
		String postString="{   \"itinerary\": {     \"cabin_type\": \"E\",     \"fare_details\": [       {         \"amount\":"+amount+",         \"fare_key\": \"" +key+"\"      },       {         \"amount\":"+amount+"     }     ],     \"flights\": [       {         \"segments\": {           \"1\": {             \"departure_airport\": \""+from+"\",             \"arrival_airport\": \""+to+"\",             \"flight_number\": \""+flightno+"\",             \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",              \"departure_date\": \""+onwarddate+"\"           }         }       },       {         \"segments\": {           \"2\": {             \"departure_airport\":  \""+to+"\",            \"arrival_airport\":  \""+from+"\",             \"flight_number\": \""+flightno1+"\",             \"airline\": \""+carrier1+"\",                        \"operating_airline\":  \""+carrier+"\",            \"departure_date\": \""+returndate+"\"             }         }       }     ],     \"pax_info_list\": [       {         \"title\": \"Mr\",         \"first_name\": \"test\",         \"last_name\": \"test\",         \"type\": \"ADT\",         \"date_of_birth\": \"1988-11-15\",         \"pax_nationality\": \"IN\",         \"poi_details\": {           \"id_card_number\": \"423\",           \"id_card_type\": \"GOVT\",           \"visa_type\": \"Business\"         },         \"passport_detail\": {           \"passport_number\": \"345678\",           \"passport_exp_date\": \"2018-11-15\",           \"passport_issuing_country\": \"IN\",           \"passport_issue_date\": \"2013-11-15\"         },         \"frequent_flyer_numbers\": [           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           },           {             \"freq_flier_number\": \"56789\",             \"applicable_airline\": \"SG\",             \"airline\": \"SG\"           }         ]       }     ],     \"contact_detail\": {       \"title\": \"Mr\",       \"first_name\": \"Frank\",       \"last_name\": \"Dsouza\",       \"email\": \"cleartripfortesting@gmail.com\",       \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",       \"mobile\": \"919844000000\",       \"landline\": \"02240554000\",       \"city_name\": \"Mumbai\",       \"state_name\": \"Maharashtra\",       \"country_name\": \"India\",       \"pin_code\": \"400011\"     },     \"payment_detail\": {       \"payment_type\": \"DA\",       \"deposit_account_id\":521     }   } }";
		//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\":521       }     } }";
		//	}
		//System.out.println("---"+postString);
		HttpPost itinenaryCall;
		if(common.value("host").equalsIgnoreCase("www")) {
		
		Reporter.log("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/json/create",true);
		 itinenaryCall = new HttpPost(new URI("https://"+common.value("ProdEnvironment")+".cleartrip.com/air/1.0/itinerary/json/create"));
		}
		else {
			System.out.println("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create");
			 itinenaryCall = new HttpPost(new URI("https://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/json/create"));	
		}
	StringEntity params = new StringEntity(postString);
	itinenaryCall.setEntity(params);
	if(source.equalsIgnoreCase("b2c")){
		
		if(common.value("host").equalsIgnoreCase("www")) {
		itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		}
		else {
			itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
		}
		itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	}
	else{
		if(common.value("host").equalsIgnoreCase("www")) {
			itinenaryCall.setHeader("X-CT-API-KEY",common.value("ProdAPIKey"));
			}
			else {
				itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
			}
	}
	//itinenaryCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
	//itinenaryCall.setHeader("X-CT-SOURCETYPE","B2C");
	//itinenaryCall.setHeader("ct-auth",value);
	//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
	HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
	int hitStatus = itinenaryResponse.getStatusLine().getStatusCode();
	Reporter.log("Http response Staus for itinenaryResponse="+hitStatus,true);
	//Assert.assertEquals(hitStatus,200,"Response code is="+hitStatus);
	HttpEntity entityIti = itinenaryResponse.getEntity();
	/*String responseString = EntityUtils.toString(entityIti, "UTF-8");
	System.out.println("response string=="+responseString);*/
	Document docIti =null;
	BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
	String str12 ="";
	StringBuffer sb12 =new StringBuffer();
	while((str12=br12.readLine())!=null){
	sb12.append(str12);
	}

	JSONObject itinenaryId = new JSONObject(sb12.toString());

//System.out.println(itinenaryId);
		return itinenaryId;
	}
public String getConnectorModifiedDate(String date1){
	 String date="";
	 Calendar c = Calendar.getInstance();
	 System.out.println("input date="+date1);
	 SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy" );  
	 c.add(Calendar.DATE,Integer.parseInt(date1));
	 
	 String convertedDate=dateFormat.format(c.getTime());    
	 System.out.println("Date increased="+convertedDate);
	 
	 
	 return convertedDate;
	}	


	
}
