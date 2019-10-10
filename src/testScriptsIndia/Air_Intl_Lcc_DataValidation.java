package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class Air_Intl_Lcc_DataValidation extends AirCommonMethod{

	 
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void Dom_LCC_Airline(String[] origin, String[] destin, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {
		

		boolean warningFound = false;
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		String Firstname="Test";
		String Lastname="Test";
		String origin1=origin[attempt];
		String DOJ="20161210";
		String destin1=destin[attempt];
		Reporter.log(flightPreference + ":" + this.getClass() + " started");
		//System.out.println(flightPreference + ":" + this.getClass() + " started");
		
		
		//do {
			driver.get(baseUrl);
			
			
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, origin[attempt], destin[attempt], "10", adults, children, infants,flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				//continue;
			}
			warningFound = filterFlightsByLCCOrGDSOW(driver, flight_type);
			if (warningFound) {
				attempt++;
				//continue;
			}
			String totaldisplayed=driver.findElement(By.xpath("//form[@id='flightForm']/section[2]/div[4]/div/nav/ul/li/table/tbody[2]/tr/th[6]/span")).getAttribute("data-pr");
		System.out.println("total in SRP="+totaldisplayed);
		
		Thread.sleep(1000);
			airCom_SRP_Oneway(driver);
			
			WebElement we = pickFirstFlight(driver);
			if (we != null) {
				bookButtonDom(we);
			} else {
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.");
				attempt++;
				//continue;
			}
		
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				//continue;
			}
			ItineraryData1(driver,insuranceRequired);
			srpcheck(driver,totaldisplayed);
		//	System.out.println(ItineraryData2(driver,insuranceRequired));
			//Assert.assertEquals(totaldisplayed,ItineraryData2(driver,insuranceRequired),"-----");
			insuranceBlock(driver, insuranceRequired);
			
				
				travellerDetails(driver, adults, children, infants,true, false, false);
				
				Boolean reachedPaymentStep = airconditionWatcher(driver);
				if (reachedPaymentStep) {
					convinienceData(driver);
					if ((common.value("makePayment").equals("true"))) {
						paymentDone = b2cPayment(driver, paymentMethod, 1);
						boolean error = false;
						if (paymentDone == true)
							error = recheckAirlinePrice(driver, "testFlag");//workaround
						else if (paymentDone == false) {
							attempt++;
							Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
							//continue;
						}
						if (error) {
							attempt++;
							//continue;
						}
					} else {
						bookingPassed = true;
						//break;
					}
				} else {
					
					attempt++;
					Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
					//continue;
				}
				attempt++;
				bookingPassed = checkBookingStatus(driver);	
				System.out.println("confirmation total="+driver.findElement(By.xpath("//ul[@class='clearFix']/li[2]")).getText().split(" ")[2].replace(",",""));
				String confirmationtotal=driver.findElement(By.xpath("//ul[@class='clearFix']/li[2]")).getText().split(" ")[2].replace(",","");
				//driver.close();
			driver.get("http://10.10.21.107:9080/trips/"+getText(driver, getObject("AirCom_Confirmation_TripID")));
			System.out.println("completed");
				
				 printInfo(getText(driver, getObject("AirCom_Confirmation_TripID")));
				    System.out.println("TripId booked: " + getText(driver, getObject("AirCom_Confirmation_TripID")));
				//driver.get("http://10.10.21.107:9080/trips/tripid");
				System.out.println("http://10.10.21.107:9080/trips/"+getText(driver, getObject("AirCom_Confirmation_TripID")));
				DefaultHttpClient client = new DefaultHttpClient();
				//HttpGet get = new HttpGet(new URI("http://10.10.21.107:9080/trips/Q1611080192"));
				HttpGet get = new HttpGet(new URI("http://10.10.21.107:9080/trips/"+getText(driver, getObject("AirCom_Confirmation_TripID"))));
				HttpResponse response = client.execute(get);
				BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer sb = new StringBuffer();
				String str="";
				while((str=br.readLine())!=null){
					//System.out.println("----"+str);
					sb.append(str);
				}
				String bookDetails=sb.toString();
				 String [ ] getbookingStatus = bookDetails.split("<booking-status>");
	                String [ ] bookingStatus = getbookingStatus[1].split("</booking-status>");
	                System.out.println("bookingStatus= " + bookingStatus[0]);
	                Reporter.log("bookingStatus= " + bookingStatus[0]);
	            
	                if(bookingStatus[0].equalsIgnoreCase("p")){
	               String [ ] getairlinePnr = bookDetails.split("<airline-pnr>");
	                String [ ] airlinePnr = getairlinePnr[1].split("</airline-pnr>");
	                System.out.println("airline-pnr = " + airlinePnr[0]);
	                Reporter.log("airline-pnr = " + airlinePnr[0]);
	                
	                
	               /* String [ ] getgdsPnr = bookDetails.split("<gds-pnr>");
	                String [ ] gdsPnr = getgdsPnr[1].split("</gds-pnr>");
	                System.out.println("gds-pnr= " + gdsPnr[0]);
	                Reporter.log("gds-pnr= " + gdsPnr[0]);*/
	                
	                String [ ] getticketNumber = bookDetails.split("<ticket-number>");
	                String [ ] ticketNumber = getticketNumber[1].split("</ticket-number>");
	                System.out.println("ticket-number= " + ticketNumber[0]);
	                Reporter.log("ticket-number= " + ticketNumber[0]);
		 }
        else{
        	Assert.assertEquals("P",bookingStatus[0],"Booking status failed");
        }
	                String [ ] getTotalFare = bookDetails.split("<total-fare>");
	                String [ ] TotalFare = getTotalFare[1].split("</total-fare>");
	                Reporter.log("total-fare= " + TotalFare[0]);
	                System.out.println("total-fare= " + TotalFare[0]);
	                String [ ] TripFirstname = bookDetails.split("<first-name>");
	                String [ ] TFirstname = TripFirstname[1].split("</first-name>");
	                
	                Reporter.log("TFirstname= " + TFirstname[0]);
	                System.out.println("TFirstname= " + TFirstname[0]);
	                
	                String [ ] TripLastname = bookDetails.split("<last-name>");
	                String [ ] TLastname = TripLastname[1].split("</last-name>");
	                Reporter.log("TLastname=" + TLastname[0]);
	                System.out.println("TLastname= " + TLastname[0]);
	                String [ ] Tripfare = bookDetails.split("<total-fare>");
	                String [ ] Tfare = Tripfare[1].split("</total-fare>");
	                Reporter.log("TFare= " + Tfare[0]);
	                String Totalfare=Tfare[0].split("/.")[0];
	                System.out.println("TFare= " + Tfare[0].split("/.")[0]);
	                String [ ] Triporder = bookDetails.split("<order-info1>");
	                String [ ] Torder = Triporder[1].split("</order-info1>");
	                String[] tripD=Torder[0].split("/");
	                String Flightname=tripD[0];
	                String Flightnumber=tripD[1];
	                String Departure=tripD[2];
	                String Arrival=tripD[3];
	                String date=tripD[4].substring(0,8);
	                Reporter.log("Airways="+Flightname+"  flightnumber="+Flightnumber+" departure airport="+Departure+" arrival airport="+Arrival+"  date="+date);
	                System.out.println("flightname="+Flightname+"  flightnumber="+Flightnumber+" departure airport="+Departure+" arrival airport="+Arrival+"  date="+date);
	                Assert.assertEquals(Firstname,TFirstname[0],"mismatch in firstname");
	                Assert.assertEquals(Lastname,TLastname[0],"mismatch in last name");
	                Assert.assertEquals(origin1.toUpperCase(),Departure,"mismatch in departure");
	                Assert.assertEquals(destin1.toUpperCase(),Arrival,"micmatch in arrival");
	                Assert.assertEquals(DOJ,date,"mismatch in date of journey");
	                System.out.println("confirmation total="+confirmationtotal+"total fare="+Totalfare);
	                Assert.assertEquals(confirmationtotal,Totalfare,"fare mismatch");
	                
	                
	                
	                
                //}
				Thread.sleep(25000);
				
				
			//} while (!bookingPassed && attempt < 3);
			//assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
		
			
		}
	
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "del","del","maa"};
		String[] destination = {"dXb","sin","cmb"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "Direct", "1", "0", "0",
				"credit card", false, "Full Refund" } };
	}		

	
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		//System.out.println("Test Case:" + _result.getMethod().getMethodName());
	}






}
