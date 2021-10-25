package tripServices;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_Save_InternationFareRules extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void postPahcommqueue() throws IOException, InterruptedException{
		String url="http://172.17.51.86:9031/trips/75a63f6ba2-9102-49fc-bbbc-191014140645/save-international-fare-rule";
		String params="'<html xmlns=\\\"http://www.w3.org/1999/xhtml\\\">   <head>       <meta http-equiv=\\\"content-type\\\" content=\\\"text/html;charset=utf-8\\\" />       <title>Cleartrip | Fare rules for your flights booking</title>       <link href=\\\"http://www.cleartrip.com/styles/styles1.v54240.css\\\" rel=\\\"stylesheet\\\" type=\\\"text/css\\\" media=\\\"all\\\" />       <style>\\t\\t\\t\\t\\tbody.farerules div#Wrapper {\\t\\t\\t\\t\\tmin-width: 100%;\\t\\t\\t\\t\\t}\\t\\t\\t\\t\\tbody.farerules div#Wrapper div.Container {\\t\\t\\t\\t\\tpadding: 0 10px;\\t\\t\\t\\t\\t}\\t\\t\\t\\t\\tbody.farerules table {\\t\\t\\t\\t\\twidth: 100%;\\t\\t\\t\\t\\tmargin: 0.5em 0;\\t\\t\\t\\t\\t}\\t\\t\\t\\t\\tbody.farerules table th, body.farerules table td {\\t\\t\\t\\t\\tpadding: 5px;\\t\\t\\t\\t\\tvertical-align: top;\\t\\t\\t\\t\\t}\\t\\t\\t\\t\\tbody.farerules table th {\\t\\t\\t\\t\\tbackground-color: #F4F5FA;\\t\\t\\t\\t\\tfont-weight: bold;\\t\\t\\t\\t\\ttext-transform: none;\\t\\t\\t\\t\\tvertical-align: bottom;\\t\\t\\t\\t\\tborder-bottom: 1px solid #cccccc;\\t\\t\\t\\t\\t}\\t\\t\\t\\t\\tbody.farerules table th a {\\t\\t\\t\\t\\tfloat: right;\\t\\t\\t\\t\\tfont-size: 11px;\\t\\t\\t\\t\\tfont-weight: normal\\t\\t\\t\\t\\t}\\t\\t\\t\\t\\tbody.farerules table tr.h1 th {\\t\\t\\t\\t\\tbackground-color: #E8ECF5;\\t\\t\\t\\t\\tfont-size: 13px;\\t\\t\\t\\t\\tpadding: 10px 5px;\\t\\t\\t\\t\\tborder-bottom: 1px solid #dadada;\\t\\t\\t\\t\\t}\\t\\t\\t\\t\\tbody.farerules table td {\\t\\t\\t\\t\\ttext-transform: capitalize;\\t\\t\\t\\t\\tfont-size: 11px;\\t\\t\\t\\t\\t}\\t\\t\\t\\t\\tbody.farerules table tr.flights td {\\t\\t\\t\\t\\tborder-bottom: 1px dotted #cccccc;\\t\\t\\t\\t\\ttext-transform: none;\\t\\t\\t\\t\\t\\t\\t\\t\\tcolor: #777777;\\t\\t\\t\\t\\t}\\t\\t\\t\\t</style>   </head>   <body class=\\\"FullWidth farerules\\\">       <div id=\\\"Wrapper\\\">           <div class=\\\"Container\\\">               <div id=\\\"ContentFrame\\\">                   <div class=\\\"Left\\\">                       <div class=\\\"col\\\">                           <div class=\\\"logo\\\" style=\\\"border-bottom: 1px solid #cccccc; margin-bottom: 10px\\\">                               <img src=\\\"http://www.cleartrip.com/images/global/cleartrip_logo_medium.gif\\\" alt=\\\"Cleartrip: India'\\\\''s Favourite Travel Agency\\\" />                           </div>                           <!-- NO FARE RULES AVAILABLE -->                           <xsl:apply-templates select=\\\"NoFareRule\\\" />                           <a name=\\\"top\\\" href=\\\"#\\\"></a>                           <div>                               <a name=\\\"top\\\" href=\\\"#\\\" />                               <a name=\\\"Adult\\\"></a>                           </br>                           <!-- FARE RULES -->                           <h5 align=\\\"left\\\">F3 JED - DMM ( ADT,CHD,INF )                                                                                                               </h5>                           <p>Ticket is Non-Refundable in case of cancellation of the ticket before or after departure by the Passenger, and hence, no refund will be processed from Cleartrip.                           </br>Few Airlines offer Credit voucher against the cancellation of the ticket, however, passengers are requested to check with the Airline directly to avail the same (if applicable).                       </br>In case of flight cancellation by the Airline, refund (if any) will take 15-20 working days post an intimation from the customer.                   </br>               </br>           </p>           <br />           <p>We have tried our best to collate this data for you in attempt to make it easier to understand. In case you still have doubts please don'\\\\''t hesitate to call our flight experts at 920004841 (Local Call from Saudi Arabia)   between 8 am and 8 pm Saudi Arabia time</p>       </div>   </div></div></div></div></div></body></html>'";
		System.out.println(url);
		Reporter.log(url);
		Response resp;
		resp = RestAssured.given().
				when().
				log().all().
				body(params).
				headers("Content-Type","application/json").
				post(url);
		System.out.println(resp.asString());
		if(resp.statusCode()==200){
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertNotNull(resp);
			 Assert.assertEquals("Response consists of id field",bodyAsString.contentEquals("id"), bodyAsString.contentEquals("id"));	
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
		
	}

}
