package testScriptsMicroServices;

/*
 * Modified details 
 * 
 * 29/11/2018 - Gobind - Added writeConnectorResponseInTextFile(...)
 * 30/11/2018 - Gobind - 
 * 
 */


import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class GetConnectorResponseAndWriteInTextFile extends AirCommonMethod{
	
	
	
	/*
	 * @Author : Gobind
	 * @Date   : 03/12/2018
	 * @Purpose : Storing the connector response to verify sms flow
	 * @Parameter :
	 * @Return :
	 */
	
	@Parameters({ "origin", "destination","destination1", "airline", "paxConfig", "onwardDate",
		"returnDate", "suppliers", "module", "IsFliteTypeInternational", "tripType","testCaseCount" })
	@Test
	public void writeConnectorResponseInTextFile(String origin, String destination,@Optional("") String destination1, @Optional("") String airline,
			String paxConfig, String onwarddate,@Optional("") String returndate,
			@Optional("") String suppliers, @Optional("") String module, boolean IsFliteTypeInternational,String tripType,String testCaseCount) throws Exception {
		
		
		String connectorSearchUrl=getConnectorsSearchUrl(IsFliteTypeInternational, origin, destination,destination1, onwarddate, returndate, paxConfig,
				airline, suppliers,tripType);
		addLog("Getting the connector search response",true);
		String connectorSearchResponse =getConnectorResponse(connectorSearchUrl).toString();
		
//		  1. Get response in string with format
//		  "{a-b-c-d={z=1,y=2,z=3},e-f-g={k=4,l=2}}"
		
		if(module.trim().equalsIgnoreCase("sms")) 
		{
			addLog("creating text file to store response in On foler.",true);
			// 2.store string value into the file
			String filePath="./On/"+testCaseCount+"_Response_"+paxConfig+"_"+tripType+".txt";
			File file = new File(filePath);
			file.createNewFile();
			byte valbyteON[] = connectorSearchResponse.getBytes();
			FileOutputStream fosON = new FileOutputStream(file);

			fosON.write(valbyteON);
			fosON.flush();
		}
		else 
		{
			addLog("creating text file to store response in Off foler.",true);
			// 2.store string value into the file
			String filePath="./Off/"+testCaseCount+"_Response_"+paxConfig+"_"+tripType+".txt";
			File fileOFF = new File(filePath);
			fileOFF.createNewFile();
			byte valbyteOFF[] = connectorSearchResponse.getBytes();
			FileOutputStream fosOFF = new FileOutputStream(fileOFF);

			fosOFF.write(valbyteOFF);
			fosOFF.flush();
		}
	}

}
