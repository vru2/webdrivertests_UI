package tripServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_Cancel_Hotel extends TripserviceCommon{
	@Test(groups={"Regression"})
	public void Tripservicehotelputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url =  Service_Url("TRIPSERVICE_POST_CALL");
		resp=TripservicePostcall(params1,headersForTripservicepostcall(),url);
		validationforputcall(resp); 
		Response resp1;
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
		String url1=("http://172.17.26.11:9031/trips/"+tripref+"/hotel-bookings/update-booking");
		Thread.sleep(2000);
		resp1=TripserviceHotelsPutcall(params4,headersForTripserviceputcall(),url1);
		validationforput(resp1);
			
		} else if(Host.equalsIgnoreCase("dev")) {
			String url1 = ("http://172.17.32.12:9031/trips/"+tripref+"/hotel-bookings/update-booking");
			resp1=TripserviceHotelsPutcall(params4,headersForTripserviceputcall(),url1);
			validationforput(resp1);	
		}
		/*
		 * else if(Host.equalsIgnoreCase("www")) { String url1 =
		 * ("http://172.21.48.21:9031/trips/"+tripref+"/hotel-bookings/update-booking");
		 * resp1=TripserviceHotelsPutcall(params4,headersForTripserviceputcall(),url1);
		 * validationforput(resp1); }
		 */
		Thread.sleep(4000);
		DBValidation_Txn(resp, "C");
       // Cancel
		String TripID="tripref";
		ArrayList<String> hotelbooking=db_HotelBookingInfo(TripID);
		String hotelbookingid=hotelbooking.get(0);
		String hotelbookinginfoid=hotelbooking.get(1);
		String tripid=hotelbooking.get(2);
		String userid=hotelbooking.get(3);
		String roomid=hotelbooking.get(4);
		String roomrateid=hotelbooking.get(5);
		String param1="{\"txn\":{\"activity_cancellations\": null,\"activity_refund_records\": null,\"air_auto_refunds\": [],\"air_cancellations\": null,\"air_refund_records\": null,\"created_at\": \"2019-06-19T14:41:59.000+0530\",\"device_id\": null,\"external_references\": [],\"external_refs\": null,\"hotel_cancellations\": [{\"hotel_booking_id\": ";
	    String param2=",\"hotel_booking_info_id\":";
	    String param3=",\"supplier_canc_date_time\": null,\"cancellation_status\": \"I\",\"cancellation_reason\": \"U\",\"hotel_refund_record_id\": null,\"description\": null,\"created_at\": \"2019-06-18T10:27:29.000+0530\",\"updated_at\": \"2019-06-18T10:27:29.000+0530\",\"auto_refund\": \"N\",\"free_cancel_policy\": null,\"hotel_penalty_record_id\": null,\"non_refundable\": null}],\"hotel_penalty_record\": null,\"hotel_refund_records\": [{\"refund_amount\": 0,\"status\": \"P\",\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"total_sup_charge\": 0,\"total_ct_charge\": 0,\"total_oth_charge\": -13567.19,\"total_rev\": 13567.19,\"total_cb\": 0,\"total_dis\": 0,\"unshelve_at\": null,\"shelve_count\": null,\"total_stx_charge\": 0,\"total_misc_charge\": 0,\"total_gw_charge\": 0,\"total_ct_stx_charge\": 0,\"total_wt_cb_charge\": 0,\"total_sbctx\": 0,\"total_kkctx\": 0,\"refund_adjustment\": 0,\"refund_id\": null,\"refund_components\": [{\"linkable_type\": null,\"category\": \"REV\",\"code\": null,\"amount\": 13567.19,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"OTH\",\"code\": null,\"amount\": -13567.19,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"DIS\",\"code\": null,\"amount\": 0,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"CB\",\"code\": null,\"amount\": 0,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"WTCB\",\"code\": null,\"amount\": 0,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"RA\",\"code\": null,\"amount\": 0,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"SUP\",\"code\": null,\"amount\": 0,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"CTSTX\",\"code\": null,\"amount\": 0,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"SBCTX\",\"code\": null,\"amount\": 0,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"KKCTX\",\"code\": null,\"amount\": 0,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"CT\",\"code\": null,\"amount\": 0,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null},{\"linkable_type\": null,\"category\": \"GW\",\"code\": null,\"amount\": 0,\"created_at\": \"2019-06-18T10:22:09.000+0530\",\"updated_at\": \"2019-06-18T10:22:09.000+0530\",\"linkable_id\": null}]}],\"insurance_refund_records\": [],\"ip_number\": \"3232248483\",\"misc\": \"--- \\\"{\\\\\\\"trip_service\\\\\\\":\\\\\\\"true\\\\\\\",\\\\\\\"payment_dropoff\\\\\\\":\\\\\\\"Initiated\\\\\\\",\\\\\\\"trip_version\\\\\\\":\\\\\\\"V2\\\\\\\"}\\\"\",\"previous_txn_id\": null,\"r_param\": null,\"r_referer\": null,\"referer_domain\": null,\"refund_records\": [],\"refunds\": [],\"roomer_refunds\": null,\"source_id\": \"192.168.50.163\",\"source_type\": \"ACCOUNT\",\"status\": \"C\",\"train_cancellations\": null,\"train_refund_records\": null,\"trip_id\":";
        String param4=",\"txn_type\": 20,\"updated_at\": \"2019-07-10T12:31:59.000+0530\",\"user_agent\": null,\"user_id\":";
        String param5=",\"utm_campaign\": null,\"utm_content\": null,\"utm_medium\": null,\"utm_source\": null,\"utm_term\": null},\"hotel_booking_infos\": [{\"id\":";
        String param6=",\"hotel_booking_id\":";
        String param7=",\"room_id\":";
        String param8=",\"room_rate_id\":";
        String param9=",\"stay_date\": \"2019-08-03T14:00:00.000+0530\",\"booking_status\": \"Q\",\"created_at\": \"2019-07-10T12:31:59.000+0530\",\"updated_at\": \"2019-07-10T12:31:59.000+0530\",\"seq_no\": 1,\"room_seq_no\": 1,\"room_rate_seq_no\": 1,\"status_reason\": null,\"external_refs\": null,\"voucher_number\": \"340047694#1234;MOD\",\"status_history\": 0,\"room_type\": \"Standard Room\",\"nri\": \"false\",\"upd_res_url\": null,\"inventory_remaining\": null,\"auto_refill_inventory_count\": null,\"base_rate_id\": null,\"is_deal_of_the_day\": \"N\",\"orig_cost_rate\": 0,\"external_references\": [],\"refund_records\": []}]}";
        String cancelparams=param1+hotelbookingid+param2+hotelbookinginfoid+param3+tripid+param4+userid+param5+hotelbookinginfoid+param6+hotelbookingid+param7+roomid+param8+roomrateid+param9;
	    System.out.println(cancelparams); 
        String url2=("http://172.17.26.11:9031/trips/"+tripref+"/cancel");
        Response resp3=TripservicePostcancelcall(cancelparams,headersForTripservicepostcancelcall(),url2);
        Reporter.log(resp3.asString());
        cancelValidation(resp3);   
      /*  ArrayList<String> payments=db_Payments(TripID);
        String payemntid=payments.get(0);
        ArrayList<String> hotelcancellation=db_HotelCancellation(TripID);
        String hotelcancellationid=hotelcancellation.get(0);
        String txnid=hotelcancellation.get(1);
        String hotelrefundrecord=hotelcancellation.get(2);
        ArrayList<String> refundcomponents=db_RefundComponents(TripID);*/
        
        
	}
	
	
	
	

}
