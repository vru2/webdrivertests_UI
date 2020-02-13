package tripServicesProd;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;



public class TS_LogBooking_Trains extends TripserviceCommon{
	@Test(groups={"Regression"})
	
	public void Tripservicepostcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
	  Response resp;
	 String url = "http://trip-service.cltp.com:9001/trips";
	 resp=TripservicePostcall(params6,headersForTripservicepostcall(),url);
	 validation(resp);
	 System.out.println(resp.asString());	
	}
	}