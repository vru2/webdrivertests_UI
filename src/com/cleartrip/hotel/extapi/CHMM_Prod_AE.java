
package com.cleartrip.hotel.extapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class CHMM_Prod_AE extends IndiaHotels {

    String adults_per_room = "1";
   
   String Book = "";
    StringBuilder bookbuilder = new StringBuilder();
    StringBuilder cancelbuilder = new StringBuilder();
    String children_per_room = "0";
    int chkinDate = 50;
    int chkoutDate = 51;
    String debug = common.value("debug_mode");
    boolean debug_mode = Boolean.parseBoolean(debug);
    StringBuilder itinerarybuilder = new StringBuilder();

    String number_of_rooms = "1";
    String provisional_book = "";
    StringBuilder searchResultbuilder = new StringBuilder();
    int solution = 1;

    @Test
    public void API_AE() throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        // ################################# Get - Search Request ############################## //
        String api_url = api_url();
        String host = common.value("host");
        Reporter.log("Host : "+host);
        Reporter.log("URL :"+api_url);        
        
        try {
          //System.out.println("Search Request Execution.....");
            HttpGet getRequest = new HttpGet(api_url() + "/hotels/1.0/search?check-in=" + putDateApi(chkinDate) + "&check-out=" + putDateApi(chkoutDate) + "&no-of-rooms="
                    + number_of_rooms + "&adults-per-room=" + adults_per_room + "&children-per-room=" + children_per_room + "&city=New+Delhi&country=IN&sct=AE&scr=AED");

            getRequest.addHeader("X-CT-API-KEY", "b56151ea36bc0e75d010c245014155c5");
            getRequest.addHeader("X-CT-SOURCETYPE", "API");
            //System.out.println(getRequest);
            HttpResponse httpResponse = httpclient.execute(getRequest);
            BufferedReader searchbr = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
            String searchResultxml;
            while ((searchResultxml = searchbr.readLine()) != null) {
                searchResultbuilder.append(searchResultxml);
            }
            // ################################# Post - Create itinerary Request ############################## //
            String hotelDetails = searchResultbuilder.toString();
           
            //System.out.println(hotelDetails);
            //System.out.println("====================================================");
            ArrayList < String > hotel2 = new ArrayList < String >();
            ArrayList < String > hotel5 = new ArrayList < String >();
            ArrayList < String > hotel6 = new ArrayList < String >();
            ArrayList < String > hotel7 = new ArrayList < String >();
            ArrayList < String > hotel8 = new ArrayList < String >();
            ArrayList < String > hotel9 = new ArrayList < String >();
            ArrayList < String > hotel10 = new ArrayList < String >();
            String [ ] getHotel = hotelDetails.split("</hotel>");
            for ( int i = 0; i <= (getHotel.length - 2); i++) {
                String [ ] Supp = getHotel[i].split("<booking-code>");
                if ( Supp[1].startsWith("2:") ) {
                    hotel2.add(getHotel[i]);
                } else
                    if ( Supp[1].startsWith("5:") ) {
                        hotel5.add(getHotel[i]);
                    } else
                        if ( Supp[1].startsWith("6:") ) {
                            hotel6.add(getHotel[i]);
                        } else
                            if ( Supp[1].startsWith("7:") ) {
                                hotel7.add(getHotel[i]);
                            } else
                                if ( Supp[1].startsWith("8:") ) {
                                    hotel8.add(getHotel[i]);
                                } else
                                    if ( Supp[1].startsWith("9:") ) {
                                        hotel9.add(getHotel[i]);
                                    } else
                                        if ( Supp[1].startsWith("10:") ) {
                                            hotel10.add(getHotel[i]);
                                        } else {
                                        //System.out.println("Junk    : "+Supp[1]);
                                        }
            }
    //        System.out.println(hotel5.get(solution));
            String [ ] gethotelid = hotel5.get(solution).split("<hotel-id>");
            String [ ] hotelid = gethotelid[1].split("</hotel-id>");
            String [ ] getdate = hotel5.get(solution).split("<common:date>");
            String [ ] dateraw = getdate[1].split("</common:date>");
            String [ ] date = dateraw[0].split("\\+");
            String [ ] getbooking_code = hotel5.get(solution).split("<booking-code>");
            String [ ] booking_code = getbooking_code[1].split("</booking-code>");
            String [ ] getroom_type_code = hotel5.get(solution).split("<room-type-code>");
            String [ ] room_type_code = getroom_type_code[1].split("</room-type-code>");
            String [ ] getrate_breakdown = hotel5.get(solution).split("</rate-breakdown>");
            String [ ] rate_breakdownraw = getrate_breakdown[0].split("<common:amount>");
            float sum = 0;
            for ( int i = 1; i <= (rate_breakdownraw.length - 1); i++) {
                String [ ] rate_breakdown = rate_breakdownraw[i].split("</common:amount>");
                String rate = rate_breakdown[0];
                float amount = Float.valueOf(rate.trim());
                sum = sum + amount;
            };
            float Cost_Price = Float.valueOf(sum);
            String provisional_book = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><provisional-book-request xmlns=\"http://www.cleartrip.com/hotel/provisional-book-request\"><nri>false</nri><hotel-id>"
                    + hotelid[0]
                    + "</hotel-id><check-in-date>"
                    + putDateApi(chkinDate)
                    + "+"
                    + date[1]
                    + "</check-in-date><check-out-date>"
                    + putDateApi(chkoutDate)
                    + "+"
                    + date[1]
                    + "</check-out-date><number-of-rooms>"
                    + number_of_rooms
                    + "</number-of-rooms><adults-per-room>"
                    + adults_per_room
                    + "</adults-per-room><children-per-room>"
                    + children_per_room
                    + "</children-per-room><booking-code>"
                    + booking_code[0]
                    + "</booking-code><room-type-code>"
                    + room_type_code[0]
                    		  +"</room-type-code><booking-country>AE</booking-country>"
                              +"<booking-currency>AED</booking-currency>"
                              + "<booking-amount></booking-amount></provisional-book-request>";
            Reporter.log("Provisional_book XML :" + provisional_book);
           // System.out.println("Provisional_book XML :"+provisional_book);
            HttpPost provisional_bookRequest = new HttpPost(api_url() + "/hotels/1.0/provisionalbook");
            provisional_bookRequest.addHeader("X-CT-API-KEY", "b56151ea36bc0e75d010c245014155c5");
            provisional_bookRequest.addHeader("X-CT-SOURCETYPE", "API");
            StringEntity input = new StringEntity(provisional_book);
            input.setContentType("application/xml");
            provisional_bookRequest.setEntity(input);
            HttpResponse response = httpclient.execute(provisional_bookRequest);
            BufferedReader itinerarybr = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            String provisional_bookxml;
            while ((provisional_bookxml = itinerarybr.readLine()) != null) {
                itinerarybuilder.append(provisional_bookxml);
            }
            
         // ################################# Post - Book Request ############################## //
            String provisional_bookxmlDetails = itinerarybuilder.toString();
            //System.out.println("provisional_bookxmlDetails : "+provisional_bookxmlDetails);
            if ( debug_mode ) {
                ////System.out.println("provisional_bookxmlDetails : "+provisional_bookxmlDetails);
            } else {
                Reporter.log("provisional_bookxmlDetails : " + provisional_bookxmlDetails);
            }
            String [ ] getprovisional = provisional_bookxmlDetails.split("<provisional-book-id>");
            String [ ] provisional = getprovisional[1].split("</provisional-book-id>");           
            Reporter.log("Provisional-id CHMM AE         = " + provisional[0]);
           httpclient.getConnectionManager().shutdown();
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}