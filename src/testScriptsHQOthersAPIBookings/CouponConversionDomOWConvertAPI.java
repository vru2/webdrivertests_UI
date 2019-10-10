package testScriptsHQOthersAPIBookings;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import domainServices.HQ;

public class CouponConversionDomOWConvertAPI extends HQ {
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

	@BeforeMethod
	public void beforeMethod() {
		timer();
	}
	
	@Test(groups="Regression")
	public void couponConversionDomOWConvertAPI_105() throws Exception {
		boolean failure = false;
		
		Reporter.log("Test case " + this.getClass() + " started");
		System.out.println("Test case " + this.getClass() + " started");

		tripId = getAPITripId(domain, "CouponConversionDomOWConvertAPI", "email_id", "user_id");
		driver.get(getBaseUrl(domain) + "/hq");
		signInHQ(driver);
		
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(new URI("http://172.17.12.231:9080/trips/" + tripId));
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String xml = EntityUtils.toString(entity, "UTF-8");
		//System.out.println(xml);
		String statusXml = xml.split("<booking-status>")[1].split("</booking-status>")[0];
		if (!statusXml.equals("C")) {
			Reporter.log("Booking status in trip xml should have been C but was : " + statusXml);
			failure = true;
		}
		
		/*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		StringBuilder xmlStringBuilder = new StringBuilder();
		//xmlStringBuilder.append("<?xml version="1.0"?> <class> </class>");
		ByteArrayInputStream input =  new ByteArrayInputStream(
		   xmlStringBuilder.toString().getBytes("UTF-8"));
		//Document doc = builder.parse(input);
		
		XPath xPath =  XPathFactory.newInstance().newXPath();
		
		String expression = "/class/student";	        
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			   Node nNode = nodeList.item(i);
			   ...
			}*/
		
		/*DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		dBuilder = dbFactory.newDocumentBuilder();

		org.w3c.dom.Document doc = dBuilder.parse(new InputSource( new StringReader(xml)));
		doc.getDocumentElement().normalize();
		XPath xPath =  XPathFactory.newInstance().newXPath();
		
		String expression = "booking-status";
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println(nNode.toString());
            System.out.println(nNode.getTextContent());
		}*/
		
		confirmTripLoad(driver, tripId, domain);
		Boolean converted = false;
		String status;
		status = getBookingStatus(driver);
		Reporter.log("Booking status is " + status);
		// System.out.println("Booking status is " + status);

		if (status.equals("Hold (Airline Coupon)")) {
			processCouponConversion(driver, tripId, "convert");
			confirmTripLoad(driver, tripId, domain);
			converted = true;
			status = getBookingStatus(driver);
		} else {
			Reporter.log("Booking status should have been Hold (Airline Coupon). Check if RateRUle is properly defined!");
			System.out.println("Booking status should have been Hold (Airline Coupon). Check if RateRUle is properly defined!");
			assertTrue("Failure!", false);
		}
		if (converted && checkIfStatusConfirmedForAllSegment(driver)) {
			assertTrue("Failure!", !failure);
			Reporter.log("Test case " + this.getClass() + " passed successfully");
			System.out.println("Test case " + this.getClass() + " passed successfully");
		} else if (converted && !checkIfStatusConfirmedForAllSegment(driver)) {
			for (int i = 0; i < 5; i++) {
				confirmTripLoad(driver, tripId, domain);
				status = getBookingStatus(driver);
				if (checkIfStatusConfirmedForAllSegment(driver)) {
					Reporter.log("Test case " + this.getClass() + " passed successfully");
					System.out.println("Test case " + this.getClass() + " passed successfully");
					return;
				}
			}
			Reporter.log("Booking status after conversion is " + status + ". Coupon conversion Failed!");
			// System.out.println("Booking status after conversion is " + status + ". Coupon conversion Failed!");
			assertTrue("Failure!", false);
		}
	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		// writeTripToFile(tripID);
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}
