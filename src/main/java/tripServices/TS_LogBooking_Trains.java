package tripServices;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;



public class TS_LogBooking_Trains extends TripserviceCommon{
	@Test(groups={"Regression"})
	
	public void Tripservicepostcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
	  Response resp;
	 String url =  Service_Url("TRIPSERVICE_POST_CALL");
	 Reporter.log(url);
	 resp=TripservicePostcall(params6,headersForTripservicepostcall(),url);
	 validation(resp);
	 System.out.println(resp.asString());
	 Thread.sleep(4000);
	 DBValidation_TxnTrains(resp, "O");		
	}
	}
