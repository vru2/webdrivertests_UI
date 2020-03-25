package common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CachedProperties {
	private static CachedProperties cachedProperties;
	private static CachedProperties dataProperties;
	private static CachedProperties dbProperties;
	private static CachedProperties cacheUrlProperties;
	private static CachedProperties ORProperties;
	private static CachedProperties ORPropertiesPlatform;	

	private static CachedProperties ORPropertiesPayment;	
	private static CachedProperties platformInstance;
	private static CachedProperties rubyAPIProperties;
	
	public static CachedProperties instance() {
		if (cachedProperties == null) {
			try {
				cachedProperties = new CachedProperties();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cachedProperties;
	}
	
	/*public static CachedProperties getTrip() {
		if (rubyAPIProperties == null) {
			try {
				rubyAPIProperties = new CachedProperties("rubyAPITripId.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rubyAPIProperties;
	}
*/

	public static CachedProperties dataInstance() {
		if (dataProperties == null) {
			try {
				dataProperties = new CachedProperties("dataFile.properties");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataProperties;
	}

	public static CachedProperties dbInstance() {
		if (dbProperties == null) {
			try {
				dbProperties = new CachedProperties("db.properties");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dbProperties;
	}

	public static CachedProperties cacheUrlInstance() {
		if (cacheUrlProperties == null) {
			try {
				cacheUrlProperties = new CachedProperties("cacheUrl.properties");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cacheUrlProperties;
	}
	
	public static CachedProperties objectReportInstancePlatform() {
		if (ORPropertiesPlatform== null) {
			try {
				ORPropertiesPlatform = new CachedProperties("objectRepositoryPlatform.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ORPropertiesPlatform;
	}

	public static CachedProperties objectReportInstance() {
		if (ORProperties == null) {
			try {
				ORProperties = new CachedProperties("objectRepository.properties");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ORProperties;
	}
	
	public static CachedProperties platformInstance() {
		if (platformInstance == null) {
			try {
				platformInstance = new CachedProperties("platform.properties");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return platformInstance;
	}
	
	private Properties properties;

	private CachedProperties() throws IOException {
		// Bad way of doing things in constructor but since we do not use any
		// injection
		// and we do not care about testing it should be fine. <Nilesh>
		properties = new Properties();
		InputStream iStream = this.getClass().getClassLoader().getResourceAsStream("common.properties");
		properties.load(iStream);
		overrideFromEnvironment();
	}

	private CachedProperties(String resource) throws IOException {
		// Bad way of doing things in constructor but since we do not use any
		// injection
		// and we do not care about testing it should be fine. <Nilesh>
		properties = new Properties();
		InputStream iStream = this.getClass().getClassLoader().getResourceAsStream(resource);
		properties.load(iStream);
	}

	private CachedProperties(InputStream stream) throws IOException {
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
	

	public static CachedProperties objectReportInstancePayment() {
		if (ORPropertiesPayment== null) {
			try {
				ORPropertiesPayment = new CachedProperties("objectRepositoryPayment.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ORPropertiesPayment;
	}
	
	
	public String value(String key) {
		return properties.getProperty(key);
	}
		
}	
