package testScriptsIndiaHotels;

import java.io.File;
import java.io.FileWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVWriter;
import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

public class HotelCom_Payment_Roomer_Refund extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	private String TripID=null;


	@Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComBangalore")
	public void Roomer(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
	 			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
 	       //driver.manage().deleteAllCookies(); 
			File file = new File(".");
			String filepath =file.getCanonicalPath()+"//exe//chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			driver = new ChromeDriver(createChromeConfig());
 		   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_Roomer"), 30,"");
		   hotelCom_ItineraryPage(driver, "ROOMER");
		   hotelCom_LoginPage(driver, "SignIN", "");		   
		   hotelCom_TravelerPage(driver);
		   TripID=hotelCom_PaymentPage(driver, Payment_Type, "Roomer Booking Refunded : ", Booking_Confirmation_Message); 
		   
		   if(!textPresent(driver,"Includes Cancellation Protection via Life Happen", 30)){
	  			Reporter.log("Text : Includes Cancellation Protection via Life Happens™ text is not displayed");
	  			Assert.assertTrue(false);
	  		}
		   
		   
  	  if (MakePaymentOnlyInQA2){
  		driver.get( baseUrl+"/account/trips/"+TripID);
  		if(!textPresent(driver,"Check your inbox for details on your claim.", 60)){
  			refreshPage(driver);
  		}
  		if(!textPresent(driver,"Want to cancel? Claim your Life Happens™ protection", 30)){
  			Reporter.log("Text : 'Want to cancel? Claim your Life Happens™ protection");
  			Assert.assertTrue(false);
  		}
  		
  		/*String Status = getText(driver, By.xpath(""));
  		if(Status.contains("")){
  			Reporter.log("Status : ");
  			Assert.assertTrue(false);
  		}*/
  		
    
  	  driver.get( baseUrl+"/hq/trips/"+TripID);
  		if(!textPresent(driver,"Tips, tools & extras", 5)){
  			refreshPage(driver);}
  		if(!textPresent(driver,"Roomer Protection", 30)){
  			Reporter.log("Text : 'Roomer Protection' text is not displayed");
  			Assert.assertTrue(false);
  		}
  		/*String Status = getText(driver, By.xpath(""));
  		if(Status.contains("")){
  			Reporter.log("Status : ");
  			Assert.assertTrue(false);
  		}*/
  		
  		
  		CSVWriter csvWriter = null;
  		try
  		{
  			//Create CSVWriter for writing to Employee.csv 
  			csvWriter = new CSVWriter(new FileWriter("sample.csv"));
  			//row1
  			String[] row = new String[]{"action","trip_ref","percentage"};
  			csvWriter.writeNext(row);
  			//row2
  			row = new String[]{"process-roomer-refund",TripID,"70"};
  			csvWriter.writeNext(row);
  		}
  		catch(Exception ee)
  		{
  			ee.printStackTrace();
  		}
  		finally
  		{
  			try
  			{
  				//closing the writer
  				csvWriter.close();
  			}
  			catch(Exception ee)
  			{
  				ee.printStackTrace();
  			}
  		}
  		
  
    	 driver.get( baseUrl+"/hq");
    	 safeClick(driver,getObject("Hotelcom_HQ_Others"));
    	 safeClick(driver,getObject("HotelCom_HQ_BatchUpload"));
    	 elementVisible(driver, getObject("HotelCom_HQ_BatchUpload_TextPresent"), 10);
    	 Reporter.log("In HQ - Batch UploadPage");
 		File f=null;
 		f=new File("sample.csv");
 		String path="";
		boolean fileExist=f.exists();
		if(fileExist==true)
		{
			path=f.getAbsolutePath();
			Reporter.log("File Path "+path);
		}else {
		 Reporter.log("File Not found");}
		 driver.findElement(By.name("file")).sendKeys(path);
    	 safeClick(driver,getObject("HotelCom_HQ_batchProcess"));
    	 Reporter.log("File Upload for processing");
    	 Thread.sleep(5000);
    	//*[@id='ContentFrame']/div[1]/div[1]/form/input
    	 elementVisible(driver,getObject("HotelCom_HQ_UploadedText"),10);
    	 elementVisible(driver,getObject("HotelCom_HQ_thisPage"),10);
    	 safeClick(driver,getObject("HotelCom_HQ_thisPage"));
       	 Thread.sleep(2000);   	 
  
    	for(int i=1; i<=10;i++){
       	 String DoneText = getText(driver, getObject("HotelCom_HQ_StatusCheck"));
       	 if(DoneText.contains("Done")){
       		 Reporter.log("UploadedFile Processed -CancellationProcess Started");
       		 driver.get( baseUrl+"/hq/trips/"+TripID); 
       		 Thread.sleep(5000);
       		 String tripStatus = getText(driver,getObject("HotelCom_HQ_TripStatus"));
       		 if( tripStatus =="Roomer activated" | tripStatus =="Roomer refunded")
       		 {
          			 Thread.sleep(2000);
          			 Reporter.log("Roomer Refund is successfull");
          			//System.out.println("Roomer Refund is successfull");
          			 for(int i1=1;i1<=10;i1++)
          			 {
   	       			 String totalText=driver.findElement(By.xpath("//*[@id='ContentFrame']/div[8]/div/dl[1]/dt["+i1+"]")).getText(); 
   	       			 if(totalText.contains("Total"))
   	       			 {
   	       				// System.out.println("Refund computation");
   	       				 Reporter.log("Refund computation");
   	       				 String totalAmountinString=driver.findElement(By.xpath("//*[@id='ContentFrame']/div[8]/div/dl[1]/dd["+i1+"]")).getText();
   	       				 int totalAmt=amtVal(totalAmountinString); //total booking amount
   	       				 String roomerAmountinString=driver.findElement(By.xpath("//*[@id='ContentFrame']/div[8]/div/dl[1]/dd["+(i1-1)+"]")).getText();
   	       				 int roomerAmt1=amtVal(roomerAmountinString);
   	       				 String roomerAmt2=Integer.toString(roomerAmt1);
   	       				 int roomerAmount=Integer.parseInt(roomerAmt2.substring(0,3)); //Roomer amount
   	       				 safeClick(driver,getObject("HotelCom_HQ_RefundTab"));
   	       				 String roomerRefundAmountinString=driver.findElement(By.xpath("//*[@id='txn-details']")).getText();
   	       				 int roomerRefundAmount=amtVal(roomerRefundAmountinString); //Refunded amount
   	       				 int amoutToRefund=calRoomerRefund(totalAmt,roomerAmount);
   	       				 if(roomerRefundAmount == amoutToRefund)
   	       				 {
   	       				//	System.out.println("Amount refunded to customer:"+roomerRefundAmount);
   	       					Reporter.log("Amount refunded to customer:"+roomerRefundAmount);
   	       					//System.out.println("Refund Amount is correct");
   	       					Reporter.log("Refund Amount is correct");
   	       					Thread.sleep(2000);
   	       			    	Assert.assertTrue(true);
   	       				 }
   	       				 else{Assert.assertTrue(false);}
   	       			 }
   	       			 else{
   	       				Reporter.log("Pricing Element missing");
   	       			Assert.assertTrue(false);
   	       			 }
          			 }
          			 //System.out.println("Roomer Refund calculation failed");
          			 Reporter.log("Roomer Refund calculation failed");
          			Assert.assertTrue(false);
          		 }
       		 else if (tripStatus.contains("Confirmed"))
       		 {
       			 Reporter.log("Roomer Refund Upload is not Successfull/Not Done");
       			 Assert.assertTrue(false);
          		 }
       		 break; 		    		
       	 }
       	 else if(DoneText.contains("Error")){
       		 	Reporter.log("Batch File processing error");
       	 	 	Assert.assertTrue(false);
       	 }
       	 else if(DoneText.contains("Processing")){
       		 refreshPage(driver);
       	 }
       	 else
          	 {
       		 Assert.assertTrue(false);
       		 }
       	driver.get( baseUrl+"/hq/trips/"+TripID); 
        String tripStatus = getText(driver,getObject("HotelCom_HQ_TripStatus"));
   		
       	/*String tripStatus = getText(driver, By.xpath(""));
   		if(tripStatus.contains("")){
   			Reporter.log("Status : ");
   			Assert.assertTrue(false);
   		}*/
   		
        driver.get( baseUrl+"/account/trips/"+TripID);
      	/*String tripStatus = getText(driver, By.xpath(""));
   		if(tripStatus.contains("")){
   			Reporter.log("Status : ");
   			Assert.assertTrue(false);
   		}*/
          	 }
  	  }
    }
    public static int amtVal(String a)
	{
		String a1=a.replaceAll("\\P{Alnum}", "");
		String result2=a1.replace("[a-zA-Z]","");
		int result3=Integer.parseInt(result2);
		return result3;
	}
	public static int calRoomerRefund(int total,int roomerAmount)
	{
		int totalBookingAmt=total,totalRoomerAmount=roomerAmount;
		int actualBookingAmount=(totalBookingAmt-totalRoomerAmount);
		int refundAmount=(int)(actualBookingAmount/100)*70;
		Reporter.log("Amount to be refunded"+refundAmount);
		return refundAmount;		
	} 
    
  @BeforeClass
  public void setUp() throws Exception {	 
	  	//driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
  }

  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }
}