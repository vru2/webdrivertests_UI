package test.java.commonServices;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CachedPropertiesTemp {

	private static CachedPropertiesTemp cachedProperties;
	private static CachedPropertiesTemp dataProperties;
	private static CachedPropertiesTemp dbProperties;
	private static CachedPropertiesTemp cacheUrlProperties;
	private static CachedPropertiesTemp ORProperties;
	private static CachedPropertiesTemp ORPropertiesAir;
	private static CachedPropertiesTemp ORPropertiesHotels;
	private static CachedPropertiesTemp ORPropertiesHotelsPWA;
	private static CachedPropertiesTemp ORPropertiesLocals;
	private static CachedPropertiesTemp ORPropertiesTrains;
	private static CachedPropertiesTemp ORPropertiesPayment;	
	private static CachedPropertiesTemp ORPropertiesPlatform;	
	private static CachedPropertiesTemp rubyAPIProperties;
	private static CachedPropertiesTemp apiPayloadProperties;
	private static CachedPropertiesTemp campLocalProperties;
	private static CachedPropertiesTemp platformInstance;
	private static CachedPropertiesTemp ctSuiteProperties;
	private static CachedPropertiesTemp orCtSuiteProperties;

	// currently not capable of handling multithreaded scenarios.
	public static CachedPropertiesTemp instance() {
		if (cachedProperties == null) {
			try {
				cachedProperties = new CachedPropertiesTemp();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cachedProperties;
	}

	public static CachedPropertiesTemp dataInstance() {
		if (dataProperties == null) {
			try {
				dataProperties = new CachedPropertiesTemp("dataFile.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dataProperties;
	}

	public static CachedPropertiesTemp dbInstance() {
		if (dbProperties == null) {
			try {
				dbProperties = new CachedPropertiesTemp("db.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dbProperties;
	}

	public static CachedPropertiesTemp cacheUrlInstance() {
		if (cacheUrlProperties == null) {
			try {
				cacheUrlProperties = new CachedPropertiesTemp("cacheUrl.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cacheUrlProperties;
	}

	public static CachedPropertiesTemp objectReportInstance() {
		if (ORProperties == null) {
			try {
				ORProperties = new CachedPropertiesTemp("objectRepository.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ORProperties;
	}

	public static CachedPropertiesTemp objectReportInstanceAir() {
		if (ORPropertiesAir == null) {
			try {
				ORPropertiesAir = new CachedPropertiesTemp("objectRepositoryAir.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ORPropertiesAir;
	}

	public static CachedPropertiesTemp objectReportInstanceHotels() {
		if (ORPropertiesHotels == null) {
			try {
				ORPropertiesHotels = new CachedPropertiesTemp("objectRepositoryHotels.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ORPropertiesHotels;
	}

	public static CachedPropertiesTemp objectReportInstanceHotelsPWA() {
		if (ORPropertiesHotelsPWA == null) {
			try {
				ORPropertiesHotelsPWA = new CachedPropertiesTemp("objectRepositoryHotelsPWA.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ORPropertiesHotelsPWA;
	}

	public static CachedPropertiesTemp objectReportInstanceLocals() {
		if (ORPropertiesLocals == null) {
			try {
				ORPropertiesLocals = new CachedPropertiesTemp("objectRepositoryLocals.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ORPropertiesLocals;
	}

	public static CachedPropertiesTemp objectReportInstanceTrains() {
		if (ORPropertiesTrains == null) {
			try {
				ORPropertiesTrains = new CachedPropertiesTemp("objectRepositoryTrains.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ORPropertiesTrains;
	}
	
	public static CachedPropertiesTemp objectReportInstancePayment() {
		if (ORPropertiesPayment== null) {
			try {
				ORPropertiesPayment = new CachedPropertiesTemp("objectRepositoryPayment.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ORPropertiesPayment;
	}
	

	public static CachedPropertiesTemp objectReportInstancePlatform() {
		if (ORPropertiesPlatform== null) {
			try {
				ORPropertiesPlatform = new CachedPropertiesTemp("objectRepositoryPlatform.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ORPropertiesPlatform;
	}
	
	
	

	public static CachedPropertiesTemp getTrip() {
		if (rubyAPIProperties == null) {
			try {
				rubyAPIProperties = new CachedPropertiesTemp("rubyAPITripId.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rubyAPIProperties;
	}

	public static CachedPropertiesTemp getapiPayload() {
		if (apiPayloadProperties == null) {
			try {
				apiPayloadProperties = new CachedPropertiesTemp("apiPayload.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return apiPayloadProperties;
	}

	// currently not capable of handling multithreaded scenarios.
	public static CachedPropertiesTemp instance(InputStream stream) {
		if (cachedProperties == null) {
			try {
				cachedProperties = new CachedPropertiesTemp(stream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cachedProperties;
	}

	public static CachedPropertiesTemp campLocalInstance() {
		if (campLocalProperties == null) {
			try {
				campLocalProperties = new CachedPropertiesTemp("campLocal.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return campLocalProperties;
	}
	
	public static CachedPropertiesTemp platformInstance() {
		if (platformInstance == null) {
			try {
				platformInstance = new CachedPropertiesTemp("platform.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return platformInstance;
	}
	
	
	public static CachedPropertiesTemp ctsuite() {
		if (ctSuiteProperties == null) {
			try {
				ctSuiteProperties = new CachedPropertiesTemp("ctSuite.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ctSuiteProperties;
	}
	
	public static CachedPropertiesTemp objectReposCtSuite() {
		if (orCtSuiteProperties == null) {
			try {
				orCtSuiteProperties = new CachedPropertiesTemp("objectRepositoryCtSuite.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return orCtSuiteProperties;
	}
	
	private Properties properties;

	private CachedPropertiesTemp() throws IOException {
		// Bad way of doing things in constructor but since we do not use any
		// injection
		// and we do not care about testing it should be fine. <Nilesh>
		properties = new Properties();
		FileReader iStream = new FileReader("/Webdrivertests/resources/common.properties");
	//InputStream iStream = this.getClass().getClassLoader().getResourceAsStream("common.properties");
		properties.load(iStream);
		overrideFromEnvironment();
	}

	private CachedPropertiesTemp(String resource) throws IOException {
		// Bad way of doing things in constructor but since we do not use any
		// injection
		// and we do not care about testing it should be fine. <Nilesh>
		properties = new Properties();
		FileReader iStream = new FileReader("/Webdrivertests/resources/"+resource);
	//	InputStream iStream = this.getClass().getClassLoader().getResourceAsStream("/Webdrivertests/resources/"+resource);
		properties.load(iStream);
	}

	private CachedPropertiesTemp(InputStream stream) throws IOException {
		properties = new Properties();
		properties.load(stream);
		overrideFromEnvironment();
	}

	// go setting
	private void overrideFromEnvironment() {

		String makePayment = System.getenv("makePayment");
		if ((makePayment != null) && (!makePayment.isEmpty())) {
			properties.setProperty("makePayment", makePayment);
		}

		String host = System.getenv("host");
		if ((host != null) && (!host.isEmpty())) {
			properties.setProperty("host", host);
		}
		String browser_mob_flag = System.getenv("browser_mob_flag");
		if ((browser_mob_flag != null) && (!browser_mob_flag.isEmpty())) {
			properties.setProperty("browser_mob_flag", browser_mob_flag);
		}
		String url = System.getenv("url");
		if ((url != null) && (!url.isEmpty())) {
			properties.setProperty("url", url);
		}
		String murl = System.getenv("murl");
		if ((murl != null) && (!murl.isEmpty())) {
			properties.setProperty("murl", murl);
		}
		String mode = System.getenv("mode");
		if ((mode != null) && (!mode.isEmpty())) {
			properties.setProperty("mode", mode);
		}
		String browser = System.getenv("browser");
		if ((browser != null) && (!browser.isEmpty())) {
			properties.setProperty("browser", browser);
		}
		String testrailupdate = System.getenv("testrailupdate");
		if ((testrailupdate != null) && (!testrailupdate.isEmpty())) {
			properties.setProperty("testrailupdate", testrailupdate);
		}
		String murlLocals = System.getenv("murlLocals");
		if ((murlLocals != null) && (!murlLocals.isEmpty())) {
			properties.setProperty("murlLocals", murlLocals);
		}
		String murlLocalsAE = System.getenv("murlLocalsAE");
		if ((murlLocalsAE != null) && (!murlLocalsAE.isEmpty())) {
			properties.setProperty("murlLocalsAE", murlLocalsAE);
		}
		String Environment = System.getenv("Environment");
		if ((Environment != null) && (!Environment.isEmpty())) {
			properties.setProperty("Environment", Environment);
		}
		String APIGST = System.getenv("APIGST");
		if ((APIGST != null) && (!APIGST.isEmpty())) {
			properties.setProperty("APIGST",APIGST);
		}
		String MasterCardNo = System.getenv("MasterCardNo");
		if ((MasterCardNo != null) && (!MasterCardNo.isEmpty())) {
			properties.setProperty("MasterCardNo",MasterCardNo);
		}
		String testcardtype = System.getenv("testcardtype");
		if ((testcardtype != null) && (!testcardtype.isEmpty())) {
			properties.setProperty("testcardtype",testcardtype);
		}
	}

	public String value(String key) {
		return properties.getProperty(key);
	}
}