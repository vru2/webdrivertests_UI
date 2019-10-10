package testScriptsMicroServices;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class Verify_Connector_Response_in_SMS_Off_And_On_Case extends AirCommonMethod {
	
	
	@Parameters({ "paxConfig","tripType","testCaseCount","testCaseName" })
	@Test
	public void readTextFileAndCompare(String paxConfig,@Optional("")String tripType,String testCaseCount,String testCaseName) throws FileNotFoundException {
		
		
		addLog("Test Case Name: "+testCaseName,true);
//		  verification - As it stores data in single line we can read
//		  scanner.nextLine() or else we can use loop
		 
		String filePath_On="./On/"+testCaseCount+"_Response_"+paxConfig+"_"+tripType+".txt";
		addLog("Scanning the "+filePath_On+" file.",true);
		Scanner scanON = new Scanner(new File(filePath_On));
		String onResponse = new String();
		while (scanON.hasNextLine()) {
			onResponse += scanON.nextLine();
		}

		
//		  Instead of while we can directly read value onResponse = scanON.nextLine()
		 

		// Similarly read for OFF
		String filePath_Off="./Off/"+testCaseCount+"_Response_"+paxConfig+"_"+tripType+".txt";
		addLog("Scanning the "+filePath_Off+" file.",true);
		String offResponse = new String();
		Scanner scanOFF = new Scanner(new File(filePath_Off));
		while (scanOFF.hasNextLine()) {
			offResponse += scanOFF.nextLine();
		}

		// Process both value

		// System.out.println(onResponse);
		// System.out.println(onResponse.substring(1, offResponse.length()-2));
		addLog("Splitting onResponse based on \"},\".",true);
		String onResponseArray[] = {};
		if(onResponse.equals("{}")) {
			addLog("We did not get any data from connector. Hence file is empty",true);
		}else {
			onResponseArray = onResponse.substring(1, onResponse.length() - 2).split("},");
			for (String string : onResponseArray) {
				addLog("OnResponse: "+string,true);
			}
		}
		
		addLog("Splitting offResponse based on \"},\".",true);
		
		String offResponseArray[] = {};
		if(offResponse.equals("{}")) {
			addLog("We did not get any data from connector. Hence file is empty",true);
		}else {
			offResponseArray = offResponse.substring(1, offResponse.length() - 2).split("},");
			for (String string : offResponseArray) {
				addLog("OffResponse: "+string,true);
			}
		}

		// Convert both response into list
		addLog("Converting onResponseArray into List.",true);
		List<String> onList = Arrays.asList(onResponseArray);
		System.out.println(onList);

		addLog("Converting offResponseArray into List.",true);
		List<String> offList = Arrays.asList(offResponseArray);
		System.out.println(offList);

		boolean flag = false;
		addLog("Searching and comparing offResponse data in onResponse data.",true);
		for (String off : offList) {
			if (!onList.contains(off)) {
				addLog("offResponse data - " + off + " is not present in onResponse.",true);
				flag = true;
			}
		}

		Assert.assertFalse(flag, "--------- both ON and OFF case has different response------------");
		if (!flag) {
			addLog("--------- both ON and OFF case has same response------------",true);
		}
		
	}

}
