package test.java.tripServicesProd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_UpdateBooking_Trains_Trainfare  extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Tripservicetrainputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url ="http://trip-service.cltp.com:9001/trips";
		resp=TripservicePostcall(params6,headersForTripservicepostcall(),url);
		validation(resp);
		ResponseBody body= resp.getBody();
		String bodyAsString = body.asString();
		JsonPath jsonPath = new JsonPath(bodyAsString);
		tripref= jsonPath.getJsonObject("tripRef");
		System.out.println(resp.asString());
		Thread.sleep(2000);
		Response resp1;
		String Host = common.value("host");
		String TripID=tripref;
		System.out.println(TripID);
		ArrayList<String> traindetails=db_trainDetails(TripID);
		System.out.println(traindetails);
		String trainfareid=traindetails.get(0);
		String trainbookingid=traindetails.get(1);
		String trainbookinginfoid=traindetails.get(2);
		String trainpaxinfoid=traindetails.get(3);
		String trainid=traindetails.get(4);
		String tripid=traindetails.get(5);
		ArrayList<String> trainfaredetails=db_trainfareDetails(TripID);
		String pricingelementid=trainfaredetails.get(0);
		String linkableid=trainfaredetails.get(1);
		String param1="{\"train_fares\":[{\"id\":"+trainfareid;
		String param2=",\"created_at\":\"2019-10-21T10:13:22.000+0530\",\"seq_no\":1,\"total_base_fare\":60,\"total_cashback\":0,\"total_dis_agency_commission\":0,\"total_dis_irctc_conc\":0,\"total_dis_unknown\":0,\"total_discount\":1,\"total_fare\":98.37,\"total_fee\":36.09,\"total_fee_ct_svf\":18.95,\"total_fee_gw\":2.14,\"total_fee_irctc_cc\":1,\"total_fee_irctc_conc\":1,\"total_fee_irctc_oc\":1,\"total_fee_irctc_rf\":16,\"total_fee_irctc_sf\":1,\"total_fee_irctc_svf\":1,\"total_fee_irctc_tc\":1,\"total_fee_irctc_tic\":1,\"total_tax_irctc_tist\":1,\"total_fee_unknown\":1,\"total_markup\":1,\"total_nc\":1,\"total_nc_fee\":1,\"total_tax\":4.28,\"total_tax_agency_comm_tax\":1,\"total_tax_ct_cgst\":2.53,\"total_tax_ct_igst\":1,\"total_tax_ct_sgst\":2.53,\"total_tax_ct_st\":1,\"total_tax_gw_cgst\":1.11,\"total_tax_gw_igst\":1,\"total_tax_gw_sgst\":1.11,\"total_tax_gw_st\":1,\"total_tax_irctc_st\":0,\"total_tax_unknown\":1,\"txn_id\":null,\"updated_at\":\"2019-10-21T10:13:22.000+0530\",\"pricing_elements\":[{\"id\":"+pricingelementid;
		String param3=",\"amount\":64,\"category\":\"BF\",\"chnl_charge\":null,\"chnl_display\":null,\"chnl_refund\":null,\"code\":\"IRCTC-BF\",\"created_at\":\"2019-10-21T10:13:22.000+0530\",\"label\":null,\"linkable_id\":"+linkableid;
		String param4=",\"linkable_type\":\"TrainFare\",\"updated_at\":\"2019-10-21T10:13:22.000+0530\",\"usr_charge\":null,\"usr_display\":null,\"usr_refund\":null,\"wp_check\":null}],\"train_booking_id\":"+trainbookingid;
		String param5="}],\"train_booking_infos\":[{\"id\":"+trainbookinginfoid;
		String param6=",\"berth_type\":null,\"booking_class\":\"2S\",\"booking_status\":\"P\",\"coach_number\":null,\"confirmation_status\":null,\"created_at\":\"2019-10-21T10:13:22.000+0530\",\"initial_confirmation_status\":null,\"pax_info_seq_no\":1,\"pnr_number\":null,\"seat_number\":null,\"seq_no\":1,\"slip_route_msg\":null,\"berth_preference\":null,\"status_reason\":null,\"ticket_number\":null,\"train_fare_seq_no\":1,\"train_seq_no\":1,\"updated_at\":\"2019-10-21T10:13:23.000+0530\",\"wait_list_number\":0,\"pax_info_id\":"+trainpaxinfoid;
		String param7=",\"train_booking_id\":"+trainbookingid;
		String param8=",\"train_fare_id\":"+trainfareid;
		String param9=",\"train_id\":"+trainid;
		String param10=",\"pax_info\":{\"created_at\":\"2019-10-21T10:13:22.000+0530\",\"date_of_birth\":null,\"external_refs\":null,\"first_name\":\"test\",\"freq_flier_numbers\":null,\"gender\":\"M\",\"age\":27,\"last_name\":\"test\",\"meal_request_code\":null,\"middle_name\":null,\"pax_nationality\":null,\"pax_type_code\":\"ADT\",\"redress_number\":null,\"seq_no\":1,\"title\":\"Mr\",\"total_fare\":96.37,\"trip_id\":"+tripid;
		String param11=",\"updated_at\":\"2019-10-21T10:13:23.000+0530\",\"external_references\":null,\"resident_id_card_detail\":null,\"approver_detail\":null,\"poi_detail\":null,\"passport_detail\":null,\"frequent_flier_numbers\":[]},\"external_references\":null,\"refund_records\":[]}]}";
		String updatetrainfare=param1+param2+param3+param4+param5+param6+param7+param8+param9+param10+param11;
        System.out.println(updatetrainfare);
			String url1 = ("http://trip-service.cltp.com:9001/trips/"+tripref+"/train-bookings/update-booking");
			resp1=TripserviceHotelsPutcall(params7,headersForTripserviceputcall(),url1);
			validationforputtrains(resp1);
		 Response resp2;
			 String url2=("http://trip-service.cltp.com:9001/trips?tripID="+tripref);
			 resp2=RestAssured.get(url2);
			 System.out.println(resp2.asString());
			 validationforgettrains(resp2);
			 }
		 
		 
 }
