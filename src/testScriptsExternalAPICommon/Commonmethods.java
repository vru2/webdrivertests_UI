package testScriptsExternalAPICommon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
/*
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.Namespace;
*/
import domainServices.AirCommonMethod;
public class Commonmethods extends AirCommonMethod{

/*	public void getPayLoad1111(String farekey,String from,String to,String departdate,String flightno,String carrier,String filename) throws Exception, IOException{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("src\\testScriptsExternalAPICommon\\"+filename);
		System.out.println(xmlFile.getAbsolutePath());
	    Document doc = (Document) builder.build(xmlFile);
	  
		Element rootNode = doc.getRootElement();
		System.out.println(rootNode.getName());
		Element x=rootNode.getChild("flights");
		Element flightspec=x.getChild("flight-spec");
		Element segments=flightspec.getChild("segments");
		Element segmentspec=segments.getChild("segment-spec");
		
		Element farekey1=flightspec.getChild("fare-key").setText(farekey);
		Element departureairport=segmentspec.getChild("departure-airport").setText(from);
		Element arrivalairport=segmentspec.getChild("arrival-airport").setText(to);
		Element flightnumber=segmentspec.getChild("flight-number").setText(flightno);
		Element airline=segmentspec.getChild("airline").setText(carrier);
		Element departuredate=segmentspec.getChild("departure-date").setText(departdate);
		//System.out.println(g.getText());
		System.out.println("farekey=="+farekey1.getText());
		System.out.println("departureairport="+departureairport.getText());
		System.out.println("arrivalairport="+arrivalairport.getText());
		System.out.println("flightnumber="+flightnumber.getText());
		System.out.println("airline="+airline.getText());
		System.out.println("arrivalairport="+arrivalairport.getText());
		
		System.out.println("departuredate="+departuredate.getText());
		
		XMLOutputter xmlOutput = new XMLOutputter();
//		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(doc, new FileWriter("src\\testScriptsExternalAPICommon\\"+filename));
	}
	
	public void getOWMultiPayLoad1(String farekey,String from,String to,String departdate,String flightno,String carrier,String filename) throws Exception, IOException{

		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("src\\testScriptsExternalAPICommon\\"+filename);
		System.out.println(xmlFile.getAbsolutePath());
	    Document doc = (Document) builder.build(xmlFile);
	  
		Element rootNode = doc.getRootElement();
		System.out.println("root node==="+rootNode.getName());
		Element x=rootNode.getChild("flights");
		Element flightspec=x.getChild("flight-spec");
		Element segments=flightspec.getChild("segments");
		Element segmentspec=segments.getChild("segment-spec");
		
		Element farekey1=flightspec.getChild("fare-key").setText(farekey);
		Element departureairport=segmentspec.getChild("departure-airport").setText(from);
		Element arrivalairport=segmentspec.getChild("arrival-airport").setText(to);
		Element flightnumber=segmentspec.getChild("flight-number").setText(flightno);
		Element airline=segmentspec.getChild("airline").setText(carrier);
		Element departuredate=segmentspec.getChild("departure-date").setText(departdate);
		//System.out.println(g.getText());
		System.out.println("farekey=="+farekey1.getText());
		System.out.println("departureairport="+departureairport.getText());
		System.out.println("arrivalairport="+arrivalairport.getText());
		System.out.println("flightnumber="+flightnumber.getText());
		System.out.println("airline="+airline.getText());
		System.out.println("arrivalairport="+arrivalairport.getText());
		
		System.out.println("departuredate="+departuredate.getText());
		
		XMLOutputter xmlOutput = new XMLOutputter();
//		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(doc, new FileWriter("src\\testScriptsExternalAPICommon\\"+filename));
	
	}
*/	
	public String getModifiedDate(String date1){
		String date="";
		Calendar c = Calendar.getInstance();
		System.out.println("input date="+date1);
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );  
		c.add(Calendar.DATE,Integer.parseInt(date1));
		
		String convertedDate=dateFormat.format(c.getTime());    
		System.out.println("Date increased="+convertedDate);
		
		
		return convertedDate;
	}

	
	/*public void getbookdepositaccountid(String accountid,String filename) throws Exception{
		SAXBuilder builder = new SAXBuilder();
		final Namespace ns = Namespace.getNamespace("http://www.cleartrip.com/air/");
			File xmlFile = new File("src\\testScriptsExternalAPICommon\\"+filename);
			System.out.println(xmlFile.getAbsolutePath());
			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			System.out.println(rootNode.getName());
			Element child = rootNode.getChild("payment-detail",ns);
			child.getChild("payment-type",ns).setText(accountid);
			System.out.println("------------"+child.getChild("payment-type",ns).setText("234"));
			System.out.println("------------"+child.getChild("deposit-account-id",ns).setText("234"));
			XMLOutputter xmlOutput = new XMLOutputter();
			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("src\\testScriptsExternalAPICommon\\"+filename));

			// xmlOutput.output(doc, System.out);

			System.out.println("File updated!");

	}
	
	public void getViaPayLoad1(String farekey,String from,String to,String departdate,String flightno,String flightname,String from1,String to1,String departdate1,String flightno1,String flightname1,String carrier,String filename) throws Exception, IOException{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("src\\testScriptsExternalAPICommon\\"+filename);
		System.out.println(xmlFile.getAbsolutePath());
	    Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();
		System.out.println(rootNode.getName());
		Element x=rootNode.getChild("flights");
		Element flightspec=x.getChild("flight-spec");
		Element segments=flightspec.getChild("segments");
		//Element segmentspec=
		List<Element> we=segments.getChildren();
		System.out.println(we.size());
		System.out.println(we.get(1).getName());
		System.out.println(we.get(0).getName());
		//Element segmentspec=we.get(0).getChild("segment-spec");
		Element farekey1=flightspec.getChild("fare-key").setText(farekey);
		Element departureairport=we.get(0).getChild("departure-airport").setText(from);
		Element arrivalairport=we.get(0).getChild("arrival-airport").setText(to);
		Element flightnumber=we.get(0).getChild("flight-number").setText(flightno);
		Element airline=we.get(0).getChild("airline").setText(carrier);
		Element departuredate=we.get(0).getChild("departure-date").setText(departdate);
		//Element segmentspec1=we.get(1).getChild("segment-spec");
		//Element farekey11=we.get(1).getChild("fare-key").setText(farekey);
		Element departureairport1=we.get(1).getChild("departure-airport").setText(from1);
		Element arrivalairport1=we.get(1).getChild("arrival-airport").setText(to1);
		Element flightnumber1=we.get(1).getChild("flight-number").setText(flightno1);
		Element airline1=we.get(1).getChild("airline").setText(carrier);
		Element departuredate1=we.get(1).getChild("departure-date").setText(departdate1);
		//System.out.println(g.getText());
		System.out.println("farekey=="+farekey1.getText());
		System.out.println("departureairport="+departureairport.getText());
		System.out.println("arrivalairport="+arrivalairport.getText());
		System.out.println("flightnumber="+flightnumber.getText());
		System.out.println("airline="+airline.getText());
		System.out.println("departureairport1="+departureairport1.getText());
		System.out.println("arrivalairport1="+arrivalairport1.getText());
		System.out.println("flightnumbe1r="+flightnumber1.getText());
		System.out.println("airline1="+airline1.getText());
		System.out.println("arrivalairport1="+arrivalairport1.getText());
		
		
		System.out.println("departuredate="+departuredate.getText());
		
		XMLOutputter xmlOutput = new XMLOutputter();
//		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(doc, new FileWriter("src\\testScriptsExternalAPICommon\\"+filename));
	}
	public void getRountripPayLoad1(String onwdfarekey,String rtfareKey,String from,String to,String departdate,String returndate,String onwdflightno,String rtflightno,String carrier,String filename) throws Exception, IOException{
		System.out.println(departdate+"             "+returndate);
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("src\\testScriptsExternalAPICommon\\"+filename);
		System.out.println(xmlFile.getAbsolutePath());
	    Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();
		System.out.println(rootNode.getName());
		Element x=rootNode.getChild("flights");
		List<Element> we=x.getChildren();
		System.out.println(we.size());
		System.out.println(we.get(1).getName());
		System.out.println(we.get(0).getName());
	Element segments=we.get(0).getChild("segments");
	Element segmentspec=segments.getChild("segment-spec");
	Element farekey=segmentspec.getChild("fare-key").setText(onwdfarekey);
	Element departureairport=segmentspec.getChild("departure-airport").setText(from);
	Element arrivalairport=segmentspec.getChild("arrival-airport").setText(to);
	Element flightnumber=segmentspec.getChild("flight-number").setText(onwdflightno);
	Element airline=segmentspec.getChild("airline").setText(carrier);
	Element departuredate=segmentspec.getChild("departure-date").setText(departdate);
	Element segments1=we.get(1).getChild("segments");
	Element segmentspec1=segments1.getChild("segment-spec");
	Element farekey1=segmentspec1.getChild("fare-key").setText(rtfareKey);
	Element departureairport1=segmentspec1.getChild("departure-airport").setText(to);
	Element arrivalairport1=segmentspec1.getChild("arrival-airport").setText(from);
	Element flightnumber1=segmentspec1.getChild("flight-number").setText(rtflightno);
	Element airline1=segmentspec1.getChild("airline").setText(carrier);
	
	Element departuredate1=segmentspec1.getChild("departure-date").setText(returndate);
	
	System.out.println("farekey=="+farekey.getText());
	System.out.println("farekey=="+farekey1.getText());
	System.out.println("departureairport="+departureairport.getText());
	System.out.println("arrivalairport="+arrivalairport.getText());
	System.out.println("flightnumber="+flightnumber.getText());
	System.out.println("airline="+airline.getText());
	System.out.println("arrivalairport1="+arrivalairport1.getText());
	System.out.println("departureairport1="+departureairport1.getText());
	System.out.println("arrivalairport1="+arrivalairport1.getText());
	System.out.println("flightnumbe1r="+flightnumber1.getText());
	System.out.println("airline1="+airline1.getText());
	System.out.println("departuredate="+departuredate.getText());
	System.out.println("returndate="+departuredate1.getText());
	
	XMLOutputter xmlOutput = new XMLOutputter();
//	// display nice nice
	xmlOutput.setFormat(Format.getPrettyFormat());
	xmlOutput.output(doc, new FileWriter("src\\testScriptsExternalAPICommon\\"+filename));
		//Element 
		
		
		
		
	}
	
*/	
	
}
