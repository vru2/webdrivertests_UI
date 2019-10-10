// Framework - Cleartrip Automation
// Version -Web Driver 2.0

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

public class Cancellation_Free extends IndiaHotels {

    String adults_per_room = "2";
   
    boolean Api_payment_flag = MakePaymentOnlyInQA2;
    String Book = "";
    StringBuilder bookbuilder = new StringBuilder();
    StringBuilder cancelbuilder = new StringBuilder();
    String children_per_room = "1";
    int chkinDate = 45;
    int chkoutDate = 46;
    String debug = common.value("debug_mode");
    boolean debug_mode = Boolean.parseBoolean(debug);
    StringBuilder itinerarybuilder = new StringBuilder();
    String APIKey_QA = "g9s45bsammqggtczpz3kj3qk";
    String HotelID= "720121"; //720121
    String City = "Mumbai";
    String number_of_rooms = "1";
    String provisional_book = "";
    StringBuilder searchResultbuilder = new StringBuilder();

    @Test(alwaysRun = true, groups = { "api" })
    public void CancellationPolicy_Free() throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        // ################################# Get - Search Request  ############################## //
        try {
            HttpGet getRequest = new HttpGet(api_url() + "/hotels/1.0/search?check-in=" + putDateApi(chkinDate) + "&check-out=" + putDateApi(chkoutDate) + "&no-of-rooms="
                    + number_of_rooms + "&adults-per-room=" + adults_per_room + "&children-per-room=" + children_per_room + "&city="+City+"&country=IN&hotel-info=no&ct_hotelid="+HotelID);

            getRequest.addHeader("X-CT-API-KEY", APIKey_QA); //8a1731a74e8016392fb9938492cf8407  
            getRequest.addHeader("X-CT-SOURCETYPE", "API");
      
            HttpResponse httpResponse = httpclient.execute(getRequest);    
            BufferedReader searchbr = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
            String searchResultxml;
            while ((searchResultxml = searchbr.readLine()) != null) {
                searchResultbuilder.append(searchResultxml);
            }
            // ################################# Post - Create itinerary Request ############################## //
            String hotelDetails = searchResultbuilder.toString();
            String [ ] getHotel = hotelDetails.split("</hotel>");
            String [ ] getBookingCodes = hotelDetails.split("<booking-code>");
            String [] getBookingCode = getBookingCodes[1].split("</booking-code>");
            String [ ] getRoomTypeCodes = hotelDetails.split("<room-type-code>");
            String [] getRoomTypeCode= getRoomTypeCodes[1].split("</room-type-code>");
            String [ ] getAmount = hotelDetails.split("<common:amount>");
            String [] getAmt = getAmount[1].split("</common:amount>");
            String [ ] getCheckIN1= hotelDetails.split("<check-in-date>");
            String [] getCheckIN = getCheckIN1[1].split("</check-in-date>");
            String [ ] getCheckOut1= hotelDetails.split("<check-out-date>");
            String [] getCheckOut = getCheckOut1[1].split("</check-out-date>");
            float sum = 0;            
            String rate = getAmt[0];
            float amount = Float.valueOf(rate.trim());
            sum = sum + amount;
            float Cost_Price = Float.valueOf(sum);
            
            String provisional_book = "<rate-rules-request xmlns=\"http://www.cleartrip.com/hotel/rate-rules-request\"><script/><script/><nri>false</nri><hotel-id>"
            		+ HotelID
                    + "</hotel-id><check-in-date>"
                    + getCheckIN[0]
                    + "</check-in-date><check-out-date>"
                    + getCheckOut[0]
                    + "</check-out-date><number-of-rooms>"
                    + number_of_rooms
                    + "</number-of-rooms><adults-per-room>"
                    + adults_per_room
                    + "</adults-per-room><children-per-room>"
                    + children_per_room
                    + "</children-per-room><booking-code>"
                    + getBookingCode[0]
                    + "</booking-code><room-type-code>"
                    + getRoomTypeCode[0]
                    + "</room-type-code></rate-rules-request>";
            HttpPost cancel_Request = new HttpPost(api_url() + "/hotels/1.0/policy");
            cancel_Request.addHeader("X-CT-API-KEY", APIKey_QA);
            cancel_Request.addHeader("X-CT-SOURCETYPE", "API");
            StringEntity input = new StringEntity(provisional_book);
            input.setContentType("application/xml");
            cancel_Request.setEntity(input);
            HttpResponse response = httpclient.execute(cancel_Request);
            BufferedReader itinerarybr = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            String provisional_bookxml;
            while ((provisional_bookxml = itinerarybr.readLine()) != null) {
                itinerarybuilder.append(provisional_bookxml);
            }
            // ################################# Cancellation Request ############################## //
            String cancellationPolicyXML = itinerarybuilder.toString();
            Reporter.log("Cancellation Policy : " + cancellationPolicyXML);
            String [ ] Cancel_Policy = cancellationPolicyXML.split("<cancel-policy>");
            String [ ] Cancel_Policy1 = Cancel_Policy[1].split("</cancel-policy>");            
            String [ ] refund = cancellationPolicyXML.split("<refundable>");
            String [ ] refundDetails = refund[1].split("</refundable>");            
            Reporter.log("Cancellation Policy  is "+Cancel_Policy1[0]);   
        	Reporter.log("refundDetails is empty :  "+refundDetails[0]);
            if(!Cancel_Policy1[0].contains("If you cancel within 24 hours before checkin,  you will incur 1 room night charges per room")) {
            	Reporter.log("Cancellation policy is displayed as :  "+Cancel_Policy1[0]);
            	Assert.assertFalse(true);
            }
            Reporter.log("Refundable value is "+refundDetails[0]);
            if(Cancel_Policy1[0].isEmpty()) {
            	Reporter.log("Cancellation policy is empty :  "+Cancel_Policy1[0]);
            	Assert.assertFalse(true);
            }
            if(refundDetails[0].isEmpty()) {
            	Reporter.log("refundDetails is empty :  "+refundDetails[0]);
            	Assert.assertFalse(true);
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
