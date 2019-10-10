package testScriptsJsonExternalAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import testScriptsExternalAPICommon.CommonUtils;

import com.google.common.collect.ArrayListMultimap;

public class JSON_API_Oneway_SinglePAX_SG extends CommonUtils{



	DefaultHttpClient clientSearch = null;
	ArrayListMultimap<String, String> fareKeys=ArrayListMultimap.create();
	HashMap<String,String> hp=new HashMap<String,String>();
	String payLoadFileName = "PayloadSinglePAX.txt";
	boolean bookingSuccess = false;
	String tripId= null;
	String airlinepnr=null;
	String ticketnumber=null;
	String bookingstatus=null;
	String totalfare=null;
	String basefare=null;
	String discount=null;
	String taxes=null;
	String cashback=null;
	String responseString;
	@Test(dataProvider="dp")
	public void retailOneAdult(String from,String to,String carrier,String aCount,String cCount) throws Exception{
		//String common.value("Environment") = getcommon.value("Environment")("common.value("Environment")");
		String onwarddate=getModifiedDate(common.value("Days_to_add_for_CurrentDate"));
		String returndate1=getModifiedDate(common.value("Days_to_add_for_CurrentDate_to_return"));
		System.out.println("onward date="+onwarddate+"   returndate="+returndate1);
		clientSearch = new DefaultHttpClient();
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
		System.out.println("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0");
		HttpGet get = new HttpGet(new URI("http://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy&jsonVersion=1.0"));
		//HttpGet get = new HttpGet(new URI("http://api.cleartrip.com/air/1.0/search?from=DEL&to=BOM&depart-date=2017-08-03&adults=1&children=0&infants=0&jsonVersion=1.0"));
		////System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/search?from="+from+"&to="+to+"&depart-date="+onwarddate+"&adults="+aCount+"&children="+cCount+"&infants=0&carrier="+carrier+"&cabin-type=Economy");
		// getdepositaccountid(common.value("AccountID"),"Book.txt");
		//getdepositaccountid(common.value("AccountID"),"PayloadIntlSinglePAX.txt");
////System.out.println(common.value("APIKey"));
		get.setHeader("X-CT-API-KEY","g9s45bsammqggtczpz3kj3qk");
		//get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		//get.setHeader("X-CT-API-KEY","707ef05933ce418c028a65419dadaf8d");
		HttpResponse response = clientSearch.execute(get);
		//System.out.println(response);
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

		//System.out.println(jsonObject);
		JSONObject airSearchResult = jsonObject.getJSONObject("fare");
		System.out.println("-----"+airSearchResult.length());
		for(int i=1;i<airSearchResult.length();i++){
			try{
			fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject("N").get("fk").toString());
			hp.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject("N").get("pr").toString());
			//System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject("N").get("fk"));
			}
			catch(Exception e){
				fareKeys.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject("R").get("fk").toString());
				hp.put(String.valueOf(i),airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject("R").get("pr").toString());
				System.out.println(airSearchResult.getJSONObject(String.valueOf(i)).getJSONObject("R").get("fk"));
			}
			
		}
		
		int i=1;
		boolean flightUnavailable;
		Test:do{

			flightUnavailable= false;
			//if(fareKeys.get(String.valueOf(i)).toArray().length==2){
			// String key = fareKeys.get(String.valueOf(i)).toString();
			String key1=fareKeys.get(String.valueOf(i)).toString().replace("[","").replace("]","");
		System.out.println(key1);
			String flightno = fareKeys.get(String.valueOf(i)).toString().split(carrier.trim())[1].split("_")[1].trim();
			String amount=hp.get(String.valueOf(i)).split("\\.")[0];
			System.out.println("amount="+amount);
			System.out.println("flight no="+flightno);
			
			// String flightno1=flightno.replace("[","").replace("]","");
			////System.out.println("flight no 1=================="+flightno1);
			// String key = fareKeys.get("fare-key").get(i);
			    	  
			//String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\":"+amount+",                 \"fare_key\":" +key1+"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\":"+from+",                         \"arrival_airport\":"+to+",                         \"flight_number\": "+flightno+",                         \"airline\":"+carrier+",                         \"operating_airline\": "+carrier+",                         \"departure_date\": \"2015-11-15\"                     }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frank\",                 \"last_name\": \"Dsouza\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": "+flightno+",                        \"applicable_airline\": "+carrier+",                         \"airline\": "+carrier+",                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\":"+carrier+",                         \"airline\": "+carrier+"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\": 521         }     } }";
			String postString="{     \"itinerary\": {         \"cabin_type\": \"E\",         \"fare_details\": [             {                 \"amount\": \""+amount+"\",                 \"fare_key\": \"" +key1+"\"             }         ],         \"flights\": [             {                 \"segments\": {                     \"1\": {                         \"departure_airport\": \""+from+"\",                          \"arrival_airport\": \""+to+"\",                         \"flight_number\": \""+flightno+"\",                         \"airline\": \""+carrier+"\",                        \"operating_airline\":  \""+carrier+"\",                        \"departure_date\":\""+onwarddate+"\"                    }                 }             }         ],         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\": 521         }     } }";
			//	}
			System.out.println("---"+postString);
			HttpPost itinenaryCall = new HttpPost(new URI("https://apiqa.cleartrip.com/air/1.0/itinerary/json/create"));
StringEntity params = new StringEntity(postString);
itinenaryCall.setEntity(params);

itinenaryCall.setHeader("X-CT-API-KEY","g9s45bsammqggtczpz3kj3qk");
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

//System.out.println("itinerary id="+itinenaryId);
			System.out.println(itinenaryId.getString("itinerary_id"));
			
			String postStringBook="{     \"booking\": {         \"pax_info_list\": [             {                 \"title\": \"Mr\",                 \"first_name\": \"Frankkk\",                 \"last_name\": \"Dsouzaaa\",                 \"type\": \"ADT\",                 \"date_of_birth\": \"1988-11-15\",                 \"pax_nationality\": \"IN\",                 \"poi_details\": {                     \"id_card_number\": \"423\",                     \"id_card_type\": \"GOVT\",                     \"visa_type\": \"Business\"                 },                 \"passport_detail\": {                     \"passport_number\": \"345678\",                     \"passport_exp_date\": \"2018-11-15\",                     \"passport_issuing_country\": \"IN\",                     \"passport_issue_date\": \"2013-11-15\"                 },                 \"frequent_flyer_numbers\": [                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     },                     {                         \"freq_flier_number\": \"56789\",                         \"applicable_airline\": \"SG\",                         \"airline\": \"SG\"                     }                 ]             }         ],         \"contact_detail\": {             \"title\": \"Mr\",             \"first_name\": \"Frank\",             \"last_name\": \"Dsouza\",             \"email\": \"cleartripfortesting@gmail.com\",             \"address\": \"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",             \"mobile\": \"919844000000\",             \"landline\": \"02240554000\",             \"city_name\": \"Mumbai\",             \"state_name\": \"Maharashtra\",             \"country_name\": \"India\",             \"pin_code\": \"400011\"         },         \"payment_detail\": {             \"payment_type\": \"DA\",             \"deposit_account_id\": 521         }     } }";
			Document docBook = null;
			Document docBook1 = null;
			try{
				  	    	//System.out.println(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id"));
			       /* HttpPost bookCall1 = new HttpPost(new URI(common.value("protocol")+"://"+common.value("Environment")+".cleartrip.com/air/1.0/itinerary/priceCheck/"+itinenaryId.getJSONObject("itinerary").get("itinerary-id")));
			        StringEntity paramsBook1 = new StringEntity(postStringBook1);
			        bookCall1.setEntity(paramsBook1);
			        //System.out.println(common.value("APIKey"));
			        bookCall1.setHeader("X-CT-API-KEY",common.value("APIKey"));
			        HttpResponse bookResponse1 = clientSearch.execute(bookCall1);
			        //System.out.println("book response="+bookResponse1.getEntity());
			       HttpEntity entityBook1 = bookResponse1.getEntity(); 
			  	     if(itinenaryResponse!=null){
			  	    	 DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
			               DocumentBuilder db1 = dbf1.newDocumentBuilder();
			                docBook1 = db1.parse(entityBook1.getContent());
			    	  }
			  	//   String responseString = EntityUtils.toString(entityBook1, "UTF-8");
			       //System.out.println("response string=="+responseString);
			       Reporter.log("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent());
			  	     //System.out.println("sucess message=="+docBook1.getElementsByTagName("succes-message").item(0).getTextContent());
			  	   int hitStatus = bookResponse1.getStatusLine().getStatusCode();
			  	   Reporter.log("status="+hitStatus);*/
	                //System.out.println("status="+hitStatus);
				System.out.println("https://apiqa.cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id"));
				HttpPost bookCall = new HttpPost(new URI("https://apiqa.cleartrip.com/air/1.0/itinerary/json/book/"+itinenaryId.getString("itinerary_id")));
				System.out.println(postStringBook);
				StringEntity paramsBook = new StringEntity(postStringBook);
				bookCall.setEntity(paramsBook);
				bookCall.setHeader("X-CT-API-KEY",common.value("APIKey"));
				HttpResponse bookResponse = clientSearch.execute(bookCall);
				System.out.println("book response="+bookResponse.getEntity());
				HttpEntity entityBook = bookResponse.getEntity();
			/*responseString = EntityUtils.toString(entityBook, "UTF-8");
System.out.println(responseString);*/
				StringBuffer sb11 = new StringBuffer();
					String str11="";
					BufferedReader br11 = new BufferedReader(new InputStreamReader(bookResponse.getEntity().getContent()));
					while((str11=br11.readLine())!=null){
						   sb11.append(str11);
					}
					System.out.println(sb11);
					JSONObject jsonObject1 = new JSONObject(sb11.toString());
					
					System.out.println("----"+jsonObject1.getString("trip_id"));
				if(itinenaryResponse!=null){
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					//  docBook = db.parse(responseString);
					docBook = db.parse(entityBook.getContent());
				}
				
				Reporter.log(responseString);
				Reporter.log("Tickrt number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
				//System.out.println("Tickrt number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
				Reporter.log("gds-pnr="+docBook.getElementsByTagName("gds-pnr").item(0).getTextContent());
				//System.out.println("gds-pnr="+docBook.getElementsByTagName("gds-pnr").item(0).getTextContent());
				Reporter.log("base-fare="+docBook.getElementsByTagName("base-fare").item(0).getTextContent());
				//System.out.println("base-fare="+docBook.getElementsByTagName("base-fare").item(0).getTextContent());
				basefare = docBook.getElementsByTagName("base-fare").item(0).getTextContent();
				Reporter.log("discount="+docBook.getElementsByTagName("discount").item(0).getTextContent());
				//System.out.println("discount="+docBook.getElementsByTagName("discount").item(0).getTextContent());
				discount = docBook.getElementsByTagName("discount").item(0).getTextContent();
				//System.out.println("taxes="+docBook.getElementsByTagName("taxes").item(0).getTextContent());
				Reporter.log("taxes="+docBook.getElementsByTagName("taxes").item(0).getTextContent());
				taxes = docBook.getElementsByTagName("taxes").item(0).getTextContent();
				Reporter.log("cashback="+docBook.getElementsByTagName("cashback").item(0).getTextContent());
				//System.out.println("cashback="+docBook.getElementsByTagName("cashback").item(0).getTextContent());
				cashback = docBook.getElementsByTagName("cashback").item(0).getTextContent();
				//System.out.println("trip-id="+docBook.getElementsByTagName("trip-id").item(0).getTextContent());
				Reporter.log("trip-id="+docBook.getElementsByTagName("trip-id").item(0).getTextContent());
				tripId = docBook.getElementsByTagName("trip-id").item(0).getTextContent();
				Reporter.log("airline-pnr="+docBook.getElementsByTagName("airline-pnr").item(0).getTextContent());
				//System.out.println("airline-pnr="+docBook.getElementsByTagName("airline-pnr").item(0).getTextContent());
				airlinepnr=docBook.getElementsByTagName("airline-pnr").item(0).getTextContent();
				Reporter.log("ticket-number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
				//System.out.println("ticket-number="+docBook.getElementsByTagName("ticket-number").item(0).getTextContent());
				ticketnumber=docBook.getElementsByTagName("ticket-number").item(0).getTextContent();
				Reporter.log("booking-status="+docBook.getElementsByTagName("booking-status").item(0).getTextContent());
				//System.out.println("booking-status="+docBook.getElementsByTagName("booking-status").item(0).getTextContent());
				bookingstatus=docBook.getElementsByTagName("booking-status").item(0).getTextContent();
				bookingSuccess=true;
				flightUnavailable=false;
			}
			catch(Exception e){
				////System.out.println("response string=="+responseString);
				//Reporter.log("response string=="+responseString);
				flightUnavailable=true;
			}

			i++;
break Test;
		}while(i<1 && flightUnavailable);
		Assert.assertTrue(bookingSuccess,"Booking Failed");
	}







	@DataProvider(name="dp")
	public static Object[][] oneAdultDp(){
		return new Object[][]{
				{"DEL","BOM","SG","1","0"}


		};
	}
	//PayloadIntlSinglePAX.txt



}
