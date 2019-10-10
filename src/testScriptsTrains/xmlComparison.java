/*package testScriptsTrains;

import java.io.BufferedReader; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.custommonkey.xmlunit.DetailedDiff; 
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;



public class xmlComparison {
	
public static void main(String args[]) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {


FileInputStream fis1 = new FileInputStream("D:\\xml1.xml");
FileInputStream fis2 = new FileInputStream("D:\\xml2.xml");

// using BufferedReader for improved performance 
BufferedReader source = new BufferedReader(new InputStreamReader(fis1));
BufferedReader target = new BufferedReader(new InputStreamReader(fis2)); 

//configuring XMLUnit to ignore white spaces 
XMLUnit.setIgnoreWhitespace(true); 

//comparing two XML using XMLUnit in Java
List differences = compareXML(source, target); 
printDifferences(differences);

//showing differences found in two xml files
System.out.println(differences); 
} 

public static List compareXML(Reader source, Reader target) throws SAXException, IOException, ParserConfigurationException{ //creating Diff instance to compare two XML files

	Diff xmlDiff = new Diff(source, target); 
//for getting detailed differences between two xml files 
DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff); 
return detailXmlDiff.getAllDifferences();
}

public static void printDifferences(List differences){ 

int totalDifferences = differences.size();
System.out.println("==============================="); 
System.out.println("Total differences : " + totalDifferences); 
System.out.println("================================");
Iterator differencesIterator = differences.iterator();
while (differencesIterator.hasNext()) {
	System.out.println("--------------"+differencesIterator.next());
	}
	}

}
	
	
*/