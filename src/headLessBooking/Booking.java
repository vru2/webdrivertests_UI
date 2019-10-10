package headLessBooking;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.json.JSONException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Booking {
	
	@DataProvider (parallel = true)
	public static Object[][] debug() throws Exception {
		return new Object[][] {
				{"OneWay","BOM","DEL","12/05/2016","","1","0","0","G8"},	
		};
	}

	@Test (dataProvider="debug")
	public void SearchTest(String Triptype, String From, String To, String depart_date,String return_date, String Adults, String Child, String Infant, String Airline) throws ClientProtocolException, IOException, JSONException, InterruptedException, URISyntaxException {
		
		SearchAPI api = new SearchAPI();
		String dynamicSearchData=api.airSearch(Triptype, From, To, depart_date, return_date, Adults, Child, Infant, Airline);
		//System.out.println(dynamicSearchData);
		
		
		
		HashMap<String, String> initiateBooking =api.getOWPostData(dynamicSearchData,0);
		
		if(initiateBooking==null){
			//System.out.println("No Flight fond for search criteria");
			SearchTest(Triptype, From, To, depart_date, return_date, Adults, Child, Infant, Airline);
		}
		//System.out.println(initiateBooking);
		String itineraryID=api.createItinerary(Adults, Child, Infant, Airline, initiateBooking);	
		int solutionCount= Integer.parseInt(initiateBooking.get("Solution"));
		//System.out.println("Total  OneWay Solution : "+solutionCount);
		if(itineraryID.isEmpty()&&solutionCount>0){
			 initiateBooking=null;
			for(int sol =1;sol<=solutionCount-1;sol++){
				//System.out.println(sol);
				 initiateBooking =api.getOWPostData(dynamicSearchData,sol);
				//System.out.println(initiateBooking);
			    itineraryID=api.createItinerary(Adults, Child, Infant, Airline, initiateBooking);	
				if(itineraryID.length()>2){
					break;		
				}	
			}	
		}else{
			//System.out.println(Airline+ " No Flight Found");
		}
		
		
		String userid = null;
		if(itineraryID.length()>10){
		 userid = api.login(Adults, Child, Infant, itineraryID);
		}else{
			//System.out.println("Create Itinerary Failed");
			System.exit(0);
		}
		
		boolean Seatsell = false;
		if(userid.contains("7892488")){
			String Balance =api.wallet(itineraryID).replaceAll("\\.0", "");
			int wBal=Integer.parseInt(Balance);
			//System.out.println("Current Wallet Balance : "+wBal);
			if(wBal==0){
				//System.out.println("Wallet Not working");
				System.exit(0);
			}else if(wBal<100000){
				//System.out.println("Low Balance");
				CookieStore cookieStoreHQ =api.hqLogin();
				for(int wal =1;wal<=10;wal++){
				String Added =api.addWalletMoney(cookieStoreHQ);
						if(!Added.contains("S")){
							//System.out.println("Failed to add Money into wallet Account");
							break;
						}}
			}
			 Seatsell =api.updateTravellers(itineraryID);
			
		}else{
			//System.out.println("Login Failed");
			System.exit(0);
		}
		
		String prepayment = null;
		if(Seatsell){
			 prepayment= api.payment(itineraryID);
		}else{
			//System.out.println("Seat-Sell2 Failed");
			System.exit(0);
		}
		
		String tripId = null;
		if(prepayment.contains("true")){
			 tripId = api.confirmation(itineraryID);
		}else{
			//System.out.println("advanced-prepayment Failed");
			System.exit(0);
		}
		//System.out.println(tripId);
		
		
		
		if(!tripId.startsWith("Q")){
			api.paymentRetry(itineraryID);
			api.confirmation(itineraryID);
		}
		
	

		
		
		
		
		
	
	}


	

	
	


}
