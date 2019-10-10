package com.cleartrip.hotel.extapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class Gst extends IndiaHotels{

	String City = "New+Delhi";
    String adults_per_room = "1";
   
    boolean Api_payment_flag = MakePaymentOnlyInQA2;
    String Book = "";
    StringBuilder bookbuilder = new StringBuilder();
    StringBuilder cancelbuilder = new StringBuilder();
    String children_per_room = "0";
    int chkinDate = 42;
    int chkoutDate = 43;
    String debug = common.value("debug_mode");
    boolean debug_mode = Boolean.parseBoolean(debug);
    StringBuilder itinerarybuilder = new StringBuilder();

    String number_of_rooms = "1";
    String provisional_book = "";
    StringBuilder searchResultbuilder = new StringBuilder();
    int solution = 1;

    @Test(alwaysRun = true, groups = { "api" })
    public void GST_Ext_Api() throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        // ################################# Get - Search Request  ############################## //
        try {
            HttpGet getRequest = new HttpGet(api_url() + "/hotels/1.0/search?check-in=" + putDateApi(chkinDate) + "&check-out=" + putDateApi(chkoutDate) + "&no-of-rooms="
                    + number_of_rooms + "&adults-per-room=" + adults_per_room + "&children-per-room=" + children_per_room + "&city="+City+"&country=IN");

            getRequest.addHeader("X-CT-API-KEY", "g9s45bsammqggtczpz3kj3qk"); //8a1731a74e8016392fb9938492cf8407
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
          //  System.out.println(hotelDetails);
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
            //System.out.println(hotel5.get(solution));
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
                    + "</room-type-code><booking-amount></booking-amount></provisional-book-request>";
            if ( debug_mode ) {
           //System.out.println("Provisional_book XML :"+provisional_book);
            } else {
                Reporter.log("Provisional_book XML :" + provisional_book);
            }
            HttpPost provisional_bookRequest = new HttpPost(api_url() + "/hotels/1.0/provisionalbook");
            provisional_bookRequest.addHeader("X-CT-API-KEY", "g9s45bsammqggtczpz3kj3qk");
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
            if ( debug_mode ) {
              //System.out.println("provisional_bookxmlDetails : "+provisional_bookxmlDetails);
            } else {
                Reporter.log("provisional_bookxmlDetails : " + provisional_bookxmlDetails);
            }
            String [ ] getprovisional = provisional_bookxmlDetails.split("<provisional-book-id>");
            String [ ] provisional = getprovisional[1].split("</provisional-book-id>");
            String Book = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?><book-request xmlns=\"http://www.cleartrip.com/hotel/book-request\"><affiliate-txn-id>123456</affiliate-txn-id><nri>false</nri><hotel-id>"
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
                    + "</room-type-code><booking-amount>"
                    + Cost_Price
                    + "</booking-amount>"
                    + "<customer><title>"
                    + "Mr"
                    + "</title><first-name>"
                    + "Automation"
                    + "</first-name>"
                    + "<last-name>"
                    + "Cleartrip"
                    + "</last-name><landline>"
                    + "0800000000"
                    + "</landline>"
                    + "<mobile>"
                    + "911212121212"
                    + "</mobile>"
                    + "<email>"
                    + "automation@cleartrip.com"
                    + "</email></customer>"
                    + "<payment><payment-type>"
                    + "DA"
                    + "</payment-type><deposit-account-detail>"
                    + "<id>"
                    + "521"
                    + "</id></deposit-account-detail></payment><provisional-book-id>" + provisional[0] + "</provisional-book-id>"
                    + "<tax-entity-information><tax-identifier>32ABCDE3851M1Z5</tax-identifier>"
                    + "<tax-entity-name>Test entity name</tax-entity-name>" 
                    + "<tax-entity-address>test, test building, test road, test phase, test nagar, Bangalore 560078</tax-entity-address>"
                    + "<tax-state-code>32</tax-state-code><tax-type>GST</tax-type>" 
                    + "<tax-country-code>IN</tax-country-code></tax-entity-information></book-request>";
            //System.out.println("<provisional-id>          = " +provisional[0]);
            Reporter.log("==================provisional-id==================");
            Reporter.log("<provisional-id>          = " + provisional[0]);
            if ( api_payment_flag() ) {
            if ( debug_mode ) {
                	//System.out.println("bookXml :"+Book);
                } else {
                    //System.out.println("bookXml :"+Book);
                    Reporter.log("bookXml :" + Book);
                }
                HttpPost bookpostRequest = new HttpPost(api_url() + "/hotels/1.0/book");
                bookpostRequest.addHeader("X-CT-API-KEY", "g9s45bsammqggtczpz3kj3qk");
                bookpostRequest.addHeader("X-CT-SOURCETYPE", "API");
                StringEntity input1 = new StringEntity(Book);
                input1.setContentType("application/xml");
                bookpostRequest.setEntity(input1);
                HttpResponse bookresponse = httpclient.execute(bookpostRequest);
                BufferedReader bookbr = new BufferedReader(new InputStreamReader((bookresponse.getEntity().getContent())));
                String bookxml;
                while ((bookxml = bookbr.readLine()) != null) {
                    bookbuilder.append(bookxml);
                }
                // ################################# Booking Confirmation############################## //
                String bookDetails = bookbuilder.toString();
                if ( debug_mode ) {
                  //System.out.println("bookDetails :"+bookDetails);
                } 

                else {
                  //System.out.println("------------------------------------------------------------------");
                  //System.out.println("bookDetails :"+bookDetails);
                    Reporter.log("bookDetails :" + bookDetails);
                }
                //System.out.println("------------------------------------------------------------------");
                String [ ] getTripId = bookDetails.split("<booking-id>");
                String [ ] Tripid = getTripId[1].split("</booking-id>");
                //System.out.println("<trip-id>               = " + Tripid[0]);
                String [ ] getconfirmation_number = bookDetails.split("<confirmation-number>");
                String [ ] confirmation_number = getconfirmation_number[1].split("</confirmation-number>");
                //System.out.println("<confirmation_number>               = " + confirmation_number[0]);
                Reporter.log("===============================confirmation_number================================");
                Reporter.log("<confirmation_number>               = " + confirmation_number[0]);
                Reporter.log("<trip-id>               = " + Tripid[0]);

                // ################################# Cancel Request ############################# //
            
                
                //System.out.println("Cancel Request  Execution.....");
                HttpDelete deleteRequest = new HttpDelete(api_url() + "/trips/hotel/" + Tripid[0]);
                deleteRequest.addHeader("X-CT-API-KEY", "g9s45bsammqggtczpz3kj3qk");
                deleteRequest.addHeader("X-CT-SOURCETYPE", "API");
                HttpResponse cancelRequest = httpclient.execute(deleteRequest);
                BufferedReader cancelbr = new BufferedReader(new InputStreamReader((cancelRequest.getEntity().getContent())));
                String cancelxml;
                while ((cancelxml = cancelbr.readLine()) != null) {
                    cancelbuilder.append(cancelxml);
                }
                // ################################# Cancel Details ############################## //
                String cancelDetails = cancelbuilder.toString();
                if ( debug_mode ) {
                    ////System.out.println("cancelDetails :"+cancelDetails);
                } else {
                    Reporter.log("cancelDetails :" + cancelDetails);
                }
                String [ ] getrefund_amount = cancelDetails.split("<refund-amount>");
                String [ ] refund_amount = getrefund_amount[1].split("</refund-amount>");
                String [ ] getTxnId = cancelDetails.split("<txn-id>");
                String [ ] TxnId = getTxnId[1].split("</txn-id>");
                String [ ] getStatus = cancelDetails.split("<supplier-cancellation>");
                String [ ] Status = getStatus[1].split("</supplier-cancellation>");
                float Ref_amount = Float.valueOf(refund_amount[0].trim());
                float Deduction = Cost_Price - Ref_amount;
                float Total = Ref_amount + Deduction;
                Assert.assertEquals(Total, Cost_Price);
                Assert.assertTrue(Status[0].contains("S"));
            /*    assertEquals(Total, Cost_Price);
                assertTrue(Status[0].contains("S"));*/
                if ( debug_mode ) {
                  System.out.println("Api Hotels GST TripID = " + Tripid[0]);
                   Reporter.log("Api Hotels GST TripID = " + Tripid[0]);
                   Reporter.log("<confirmation-number>   = " + confirmation_number[0]);
                   Reporter.log("<Cost Price>            = " + Cost_Price);
                   Reporter.log("<Deduction Amount>      = " + Deduction);
                   Reporter.log("<Refund Amount>         = " + refund_amount[0]);
                   Reporter.log("<txn-id>                = " + TxnId[0]);
                   Reporter.log("<supplier-cancellation> = " + Status[0]);
                    /*System.out.println("<Cost Price>            = " +Cost_Price);
                    System.out.println("<Deduction Amount>      = " +Deduction);
                    System.out.println("<Refund Amount>         = " +refund_amount[0]);
                     System.out.println("<txn-id>                = " +TxnId[0]);
                     System.out.println("<supplier-cancellation> = " +Status[0]);*/
                } else {
                	System.out.println("Api Hotels GST TripID : " + Tripid[0]);     	
                    Reporter.log("Api Hotels GST TripID : " + Tripid[0]);
                    Reporter.log("<confirmation-number>   = " + confirmation_number[0]);
                    Reporter.log("<Cost Price>            = " + Cost_Price);
                    Reporter.log("<Deduction Amount>      = " + Deduction);
                    Reporter.log("<Refund Amount>         = " + refund_amount[0]);
                    Reporter.log("<txn-id>                = " + TxnId[0]);
                    Reporter.log("<supplier-cancellation> = " + Status[0]);
                }
                logger.info("Api Hotels GST TripID = " + Tripid[0]);
            }
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
