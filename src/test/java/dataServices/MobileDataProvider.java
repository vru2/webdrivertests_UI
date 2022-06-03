// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - June, 2016
// Author - Kiran Kumar
// Copyright � 2016 cleartrip Travel. All rights reserved.
package test.java.  dataServices;

import org.testng.annotations.DataProvider;

import test.java.commonServices.WrapperMethod;

public class MobileDataProvider  extends WrapperMethod {
	  @DataProvider
	  public static Object[][] MobileWl_Dom_Oneway (){
		 return new Object[][]{{"Delhi", "Mumbai", "23", "24", "1", "0", "0","Mobile.Com Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};  
	  }
	  @DataProvider
	  public static Object[][] MobileWl_Dom_OnewayIndiGo(){
		 return new Object[][]{{"Delhi", "Mumbai", "23", "24", "1", "0", "0","Mobile.Com Air Dom OW Booking", "IndiGo", "IndiGo", "IndiGo"}};  
	  }
	  @DataProvider
	  public static Object[][] MobileWl_Dom_OnewaySpiceJet (){
		 return new Object[][]{{"Bangalore", "Mumbai", "23", "24", "1", "0", "0","Mobile.Com Air Dom OW Booking", "SpiceJet", "SpiceJet", "SpiceJet"}};  
	  }
	  @DataProvider
	  public static Object[][] MobileWl_Dom_OnewayGoAir(){
		 return new Object[][]{{"Mumbai", "Delhi", "23", "24", "1", "0", "0","Mobile.Com Air Dom OW Booking", "GoAir", "GoAir", "GoAir"}};  
	  }
	  @DataProvider
	  public static Object[][] MobileWl_Dom_OnewayAirasia(){
		 return new Object[][]{{"Delhi", "Mumbai", "23", "24", "1", "0", "0","Mobile.Com Air Dom OW Booking", "Airasia_india", "Airasia_india", "Airasia_india"}};  
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Dom_OW() {
	      return new Object [ ] [ ] { { "Bangalore", "Mumbai", "24", "25", "1", "0", "0","Mobile.Com Air Dom OW Booking", "GoAir", "IndiGo", "SpiceJet"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Dom_OW_CP() {
	      return new Object [ ] [ ] { { "Bangalore", "Mumbai", "24", "25", "1", "0", "0","Mobile.Com Air Dom OW Booking", "GoAir", "IndiGo", "SpiceJet","DOMOW"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Dom_RT_CP() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "24", "25", "1", "0", "0","Mobile.Com Air Dom OW Booking", "GoAir", "IndiGo", "SpiceJet","MOBILE321"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Int_RT() {
	      return new Object [ ] [ ] { { "DEL", "DXB", "24", "25", "1", "0", "0","Mobile.Com Air Dom OW Booking", "GoAir", "IndiGo", "SpiceJet"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Dom_OW_Filter() {
	      return new Object [ ] [ ] { { "Bangalore", "Mumbai", "24", "25", "1", "0", "0","Mobile.Com Air Dom OW Booking", "GoAir", "IndiGo", "SpiceJet"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Dom_OW_2() {
	      return new Object [ ] [ ] { { "Bangalore", "Mumbai", "24", "25", "1", "0", "0","Mobile.Com Air Dom OW Booking", "IndiGo", "IndiGo", "IndiGo"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Dom_OW_3() {
	      return new Object [ ] [ ] { { "Chennai", "Mumbai", "24", "25", "1", "0", "0","Mobile.Com Air Dom OW Booking", "SpiceJet", "SpiceJet", "SpiceJet"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Dom_OW_4() {
	      return new Object [ ] [ ] { { "Delhi", "Mumbai", "24", "25", "1", "0", "0","Mobile.Com Air Dom OW Booking", "GoAir", "GoAir", "GoAir"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Dom_OW_GDS() {
	      return new Object [ ] [ ] { { "Bangalore", "Delhi", "24", "25", "1", "0", "0","Mobile.Com Air Dom OW Booking", "Air India", "Jet Airways", "Vistara"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Intl_OW_Multi_PAX() {
	      return new Object [ ] [ ] { { "Delhi", "Dubai", "24", "25", "1", "1", "1","Mobile.Com Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] BetaMobileCom_Dom_OW() {
		  String[] source= {"Delhi","Bombay"};
		  String[] destination= {"Bombay","Delhi",};
	      return new Object [ ] [ ] { { source,destination,"24", "25", "1", "0", "0","Mobile.Com Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileAE_Dom_OW() {
	      return new Object [ ] [ ] { { "Delhi", "Mumbai", "19", "20", "1", "0", "0","Mobile.AE Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] MobileAE_INTL_OW() {
	      return new Object [ ] [ ] { { "Delhi", "DXB", "19", "20", "1", "0", "0","Mobile.AE Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] MobileKW_INTL_OW() {
	      return new Object [ ] [ ] { { "Delhi", "DXB", "19", "20", "1", "0", "0","Mobile.AE Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] MobileKW_INTL_RT_CP() {
	      return new Object [ ] [ ] { { "KWI", "BAH", "19", "20", "1", "0", "0","Mobile.AE Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir","INTLOW"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] MobileSA_Dom_OW() {
	      return new Object [ ] [ ] { { "Delhi", "Mumbai", "19", "20", "1", "0", "0","Mobile.SA Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] MobileBH_Dom_OW() {
	      return new Object [ ] [ ] { { "Delhi", "Mumbai", "19", "20", "1", "0", "0","Mobile.BH Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};
	  }	  
	  
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Dom_RT() {
	      return new Object [ ] [ ] { { "BLR", "MAA", "19", "20", "1", "0", "0","Mobile.Com Air Dom RT Booking", "SpiceJet", "IndiGo", "GoAir"}};									
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Dom_RT_withGST() {
	      return new Object [ ] [ ] { { "Delhi", "Mumbai", "19", "20", "1", "0", "0","Mobile.Com Air Dom RT Booking","IndiGo", "SpiceJet", "GoAir","Airasia_india"}};									
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileAE_Dom_RT() {
	      return new Object [ ] [ ] { { "Delhi", "Mumbai", "19", "20", "1", "0", "0","Mobile.AE Air RT Dom Booking","Indigo", "IndiGo", "GoAir",}};									
	  }
	  
	  @DataProvider
	  public static Object [][] MobileCom_Intl_Ow(){
		return new Object[] [] {{"delhi","DXB","23","25","1","0","0","Mobile Air Intl OW Booking"}};
	  }
	  @DataProvider
	  public static Object [][] MobileCom_Intl_OW_GST(){
		return new Object[] [] {{"delhi","singapore","23","25","1","0","0","Mobile Air Intl OW Booking"}};
	  }
	  //
	  @DataProvider
	  public static Object [][] MobileCom_Intl_RT(){
		return new Object[] [] {{"BOM","DEL","23","25","1","0","0","Mobile Air Intl RT Booking"}};
	  }	  

	  @DataProvider
	  public static Object [ ][ ] MobileCom_Hotel_SinglePax() {
	      return new Object [ ] [ ] { { "Bangalore", "20", "21", "2", "0", "U G Deluxe", "Mobile Hotel Booking"}};
	  }
	  

	  @DataProvider
	  public static Object [ ][ ] Hotel_PWA_Dom_Blr() {
	      return new Object [ ] [ ] { { "Bangalore", "2", "0", "", "Mobile Hotel Booking"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] Hotel_PWA_Dom_Del() {
	      return new Object [ ] [ ] { { "New Delhi", "2", "0", "", "Mobile Hotel Booking"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] Hotel_PWA_Dom_Pune() {
	      return new Object [ ] [ ] { { "Pune", "2", "0", "", "Mobile Hotel Booking"}};
	  }
	  

	  @DataProvider
	  public static Object [ ][ ] Hotel_PWA_Intl_Dubai() {
	      return new Object [ ] [ ] { { "Dubai", "2", "0", "", "Mobile Hotel Booking"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] Hotel_PWA_Intl_Singapore() {
	      return new Object [ ] [ ] { { "Singapore", "2", "0", "", "Mobile Hotel Booking"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] MobileCom_Hotel_SinglePax_INTL() {
	      return new Object [ ] [ ] { { "dubai", "20", "21", "1", "0", "Davanam sarovar", "Mobile Hotel Booking"}};
	  }
	  @DataProvider
	  public static Object [ ][ ] MobileSA_Hotel_SinglePax() {
	      return new Object [ ] [ ] { { "Mumbai", "19", "20", "1", "0", "Davanam sarovar", "Mobile.sa Hotel Booking"}};
	  }
	  
	  @DataProvider
	  public static Object [][] MobileCom_Hotel_MultiPax_MultiRoom(){
		  return new Object [][]{{"Mumbai","22","23","2","2","2","1","1","5","8","","Mobile Hotel PAX Booking"}};
	  }
	  
	  @DataProvider
	  public static Object [][] MobileBH_Hotel_MultiPax_MultiRoom(){
		  return new Object [][]{{"Chennai","14","15","2","2","2","1","1","5","8","","Mobile.BH Hotel PAX Booking"}};
	  }	  

	  @DataProvider
	  public static Object [][] MobileQA_Hotel_MultiPax_MultiRoom(){
		  return new Object [][]{{"Bangalore","14","15","2","2","2","1","1","5","8","","Mobile.QA Hotel PAX Booking"}};
	  }
	  

	  @DataProvider
	  public static Object [][] MobileME_Hotel_MultiPax_MultiRoom(){
		  return new Object [][]{{"Mumbai","14","15","2","2","2","1","1","5","8","","Mobile.ME Hotel PAX Booking"}};
	  }
	  
	  @DataProvider
	  public static Object [][] MobileOM_Hotel_MultiPax_MultiRoom(){
		  return new Object [][]{{"Bangalore","14","15","2","2","2","1","1","5","8","","Mobile.OM Hotel PAX Booking"}};
	  }
	  
	  @DataProvider
	  public static Object [][] MobileCom_Trains(){
		  return new Object [][]{{"NDLS","Chennai Central","AC 2 TIER (2A)","14","1","0","0","0","Mobile Train Booking"}};
	  }
	  
	  @DataProvider
	  public static Object [][] MobileCom_WeekendGetaways(){
		  return new Object [][]{{"Mumbai","19","21","1room,2adults","Mobile weekend getaway booking"}};
	  }
	  @DataProvider
	  public static Object[][] MobileKW_Dom_OW (){
		 return new Object[][]{{"Delhi", "Mumbai", "23", "24", "1", "0", "0","Mobile.Com Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};  
	  }
	 
	  @DataProvider
	  public static Object[][] MobileQA_Dom_OW (){
		 return new Object[][]{{"Delhi", "BLR", "23", "24", "1", "0", "0","Mobile.Com Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};  
	  }
	  
	  @DataProvider
	  public static Object[][] MobileOM_Dom_OW (){
		 return new Object[][]{{"Delhi", "BLR", "23", "24", "1", "0", "0","Mobile.Com Air Dom OW Booking", "SpiceJet", "IndiGo", "GoAir"}};  
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] MobileSA_Dom_RT() {
	      return new Object [ ] [ ] { { "Delhi", "Mumbai", "19", "20", "1", "0", "0","Mobile.AE Air RT Dom Booking","SpiceJet", "IndiGo", "GoAir",}};									
	  }
}