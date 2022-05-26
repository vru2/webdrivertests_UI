package test.java.tripServices;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_PartnerTrips extends TripserviceCommon {

	@Test(groups={"Regression"})
	public void Tripserviceairputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url =  "http://172.17.51.86:9031/api/trips/partner?partnerUserId=563777277&bookedUserId=41683574";
		Reporter.log(url);
		resp=RestAssured.get(url);
		if(resp.statusCode()==200)
		{
			System.out.println(resp.asString());
			Reporter.log(resp.asString());
            Reporter.log("Status code " + resp.statusCode());
            ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertNotNull(resp);
			Assert.assertEquals(bodyAsString.contains("tripRef"), true ,"Response boday contains tripRef");
			Assert.assertEquals(bodyAsString.contains("airBookings"), true ,"Response boday contains airBookings");
			Assert.assertEquals(bodyAsString.contains("journeyType"), true ,"Response boday contains journeyType");
			Assert.assertEquals(bodyAsString.contains("airBookingType"), true ,"Response boday contains airBookingType");
			Assert.assertEquals(bodyAsString.contains("flights"), true ,"Response boday contains flights");
			Assert.assertEquals(bodyAsString.contains("segments"), true ,"Response boday contains segments");
			Assert.assertEquals(bodyAsString.contains("duration"), true ,"Response boday contains duration");
			Assert.assertEquals(bodyAsString.contains("arrivalTerminal"), true ,"Response boday contains arrivalTerminal");
			Assert.assertEquals(bodyAsString.contains("departureTerminal"), true ,"Response boday contains departureTerminal");
			Assert.assertEquals(bodyAsString.contains("seqNo"), true ,"Response boday contains seqNo");
			Assert.assertEquals(bodyAsString.contains("departureDateTime"), true ,"Response boday contains departureDateTime");
			Assert.assertEquals(bodyAsString.contains("arrivalDateTime"), true ,"Response boday contains arrivalDateTime");
			Assert.assertEquals(bodyAsString.contains("departureAirport"), true ,"Response boday contains departureAirport");
			Assert.assertEquals(bodyAsString.contains("arrivalAirport"), true ,"Response boday contains arrivalAirport");
			Assert.assertEquals(bodyAsString.contains("flightNumber"), true ,"Response boday contains flightNumber");
			Assert.assertEquals(bodyAsString.contains("marketingAirline"), true ,"Response boday contains marketingAirline");
			Assert.assertEquals(bodyAsString.contains("operatingAirline"), true ,"Response boday contains operatingAirline");
			Assert.assertEquals(bodyAsString.contains("paxInfos"), true ,"Response boday contains paxInfos");
			Assert.assertEquals(bodyAsString.contains("seqNo"), true ,"Response boday contains seqNo");
			Assert.assertEquals(bodyAsString.contains("firstName"), true ,"Response boday contains firstName");
			Assert.assertEquals(bodyAsString.contains("lastName"), true ,"Response boday contains lastName");
			Assert.assertEquals(bodyAsString.contains("title"), true ,"Response boday contains title");
			Assert.assertEquals(bodyAsString.contains("paxTypeCode"), true ,"Response boday contains paxTypeCode");
			Assert.assertEquals(bodyAsString.contains("bookingStatus"), true ,"Response boday contains bookingStatus");
			Assert.assertEquals(bodyAsString.contains("createdAt"), true ,"Response boday contains createdAt");
		}
		else{
			Reporter.log("Partner id not found");
			assertTrue(false);
		}
	}

}
