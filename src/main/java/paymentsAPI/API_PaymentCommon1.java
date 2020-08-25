// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import domains.PlatformCommonUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import paymentsUI.PaymentUI_CommonUtilities;

public class API_PaymentCommon1 extends domains.PlatformCommonUtil

{	
	
	//  https://qa2.cleartrip.com/paymentservice/api/wallet?product=DOMESTIC-AIR&currency=INR 
	
	/*
	
	String urlPay = "http://172.17.28.21:8358";// Mysql
	String urlWallet = "http://172.17.26.11:8359";
	String promoURL = "http://172.17.26.11:8360";
	String urlDA = "http://172.17.28.21:8403";
	String urlRewards = "http://172.17.12.83:9080";
	String urlCardInfo_Service="http://172.17.26.11:8331";
	String urlFlyin = "http://172.17.26.11:8406";
	String urlrewards_validate = "http://172.17.28.21:8358";
	String urlrewards_validate1 = "http://172.17.28.21:8358";
	String urlrewards_payURI1 ="http://172.17.28.21:8358";
	String urlrewards_URI1 = "http://172.17.12.83:9080/";
	String urlPromo_Used = "http://172.17.26.11:8359";
	String urlReporting ="http://172.17.28.21:8272";
	String urlReportingTS ="http://172.17.26.11:9031";
	public String url_TestApp = "172.17.28.21:8358/paymentservice";
	String urlFetchRefunds="http://172.17.28.21:8358";
	String url_Binmanager = "https://qa2.cleartrip.com/binmanager/v1/payment/cards?bin=534977";

	*/
	public RemoteWebDriver driver;
	

		String urlFlyin = "http://172.17.26.11:8406"; // ORACLE
		String urlPay = "http://172.17.26.11:8070";
		String urlDA = "http://172.17.28.21:8403";
		String urlRefundNew = "http://172.17.26.11:8070";
		String promoURL = "http://172.17.26.11:7999";
		String urlRewards = "http://172.17.12.82:9080";
		String urlWallet = "http://172.17.26.11:8071";
		String urladcb_validat = "http://172.17.26.11:8070";
		String urlCardInfo_Service="http://172.17.26.11:8331";

		String urlrewards_validate = "http://172.17.26.11:8070";
		String urlrewards_payURI ="http://172.17.26.11:8070";
		String urlrewards_URI = "http://172.17.12.83:9080";
		String urlPromo_Used = "http://172.17.26.11:8071";
		String urlReporting ="http://172.17.28.21:8272";
		String urlReportingTS ="http://172.17.26.11:9031";		
		String url_Binmanager = "https://qa2.cleartrip.com/binmanager/v1/payment/cards?bin=534977";
		public String url_TestApp = "";
		String urlFetchRefunds="http://172.17.26.11:8070";
		//String urlFetchRefunds="http://172.17.26.11:8070";
		//String urlCS="http://172.17.26.11:8070";
	/*
	
	String urlFlyin = "http://flyin-paymentservice.gcp-cltp.com"; 
	String urlPay = "http://payment-service.gcp-cltp.com";
	String urlRefundNew = "http://payment-service.gcp-cltp.com";
	String promoURL = "http://promoservice.gcp-cltp.com";
	String urlWallet = "http://wallet-service.gcp-cltp.com";
	String urladcb_validat = "http://payment-service.gcp-cltp.com";
	String urlCardInfo_Service="http://card-info-service.gcp-cltp.com";
	String urlrewards_validate = "http://payment-service.gcp-cltp.com";
	String urlrewards_payURI ="http://payment-service.gcp-cltp.com";
	String urlPromo_Used = "http://wallet-service.gcp-cltp.com";
	String urlReporting ="http://paymentservicereporting.gcp-cltp.com";
	String urlReportingTS ="http://trip-service-api.gcp-cltp.com";		
	public String url_TestApp = "";
	String urlFetchRefunds="http://paymentservicereporting.gcp-cltp.com";
	String urlDA = "http://pay-deposit-account-r3.gcp-cltp.com";
	String urlrewards_URI = "http://10.163.15.236:9080";
	String urlRewards = "http://rewardsservice.gcp-cltp.com:9080";
	String url_Binmanager = "https://www.cleartrip.com/binmanager/v1/payment/cards?bin=534977";

*/

/*//OLD mysql apps
	String urlPay = "http://172.17.26.11:8358";
	String urlReporting ="http://172.17.26.11:8272";*/
	
	
	String paramsCC ="[{\"payment\":{\"seq_no\":1,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":276,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.73.123\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\",\"firstName\":\"test\"},\"app_ref1\":\"Q18110926800\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"683a3a6bec-4e58-422a-a2c9-90707b1e5a12\",\"payment_type\":\"CC\",\"amount\":100.99,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"9W/362/DEL/BOM/201811XXXXXX00\",\"order_info2\":\"Kiran Kumar\",\"source_type\":\"ACCOUNT\",\"user_id\":85721640,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://www.cleartrip.com/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"340000000000009\",\"card_type_id\":1,\"expiry_month\":\"12\",\"expiry_year\":\"2021\",\"cvv\":\"123\",\"name\":\"CleartripCard\",\"billto_detail\":{\"firstname\":\"test\",\"lastname\":\"test\",\"address1\":\"Cleartrip JP Nagar\",\"city_name\":\"Bangalore\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"postal_code\":\"560076\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\"}}]";
	String paramsCCVISA ="[{\"payment\":{\"seq_no\":1,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"VISA\",\"high_risk\":false,\"d_plus_x_in_hours\":276,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.73.123\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\",\"firstName\":\"test\"},\"app_ref1\":\"Q18110926800\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"683a3a6bec-4e58-422a-a2c9-90707b1e5a12\",\"payment_type\":\"CC\",\"amount\":100.99,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"9W/362/DEL/BOM/201811XXXXXX00\",\"order_info2\":\"Kiran Kumar\",\"source_type\":\"ACCOUNT\",\"user_id\":85721640,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://www.cleartrip.com/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"5123456789012346\",\"card_type_id\":1,\"expiry_month\":\"05\",\"expiry_year\":\"2020\",\"cvv\":\"123\",\"name\":\"CleartripCard\",\"billto_detail\":{\"firstname\":\"test\",\"lastname\":\"test\",\"address1\":\"Cleartrip JP Nagar\",\"city_name\":\"Bangalore\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"postal_code\":\"560076\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\"}}]";

	String ParamsGV_Refund = "{\"paymentId\":43178534,\"amount\":400,\"refundId\":9283288,\"giftVoucherRecipientEmail\":\"cleartriptest@gmail.com\",\"originalGiftVoucherNumber\":\"3000331134027267\",\"originalInvoiceNumber\":\"43178534_Q190808460482\",\"originalGvTxnId\":\"1908081707\",\"originalBatchNumber\":\"10573009\",\"originalApprovalCode\":\"7015313\",\"currency\":\"INR\",\"tripRef\":\"Q190808460482\"}";
	String ParamsGV_CAPTURE = "{\"cardNumber\":\"3000331032058605\",\"cardPin\":\"299574\",\"cardCategory\":\"\",\"amount\":10.0,\"currency\":\"INR\",\"paymentId\":";

	String ParamsGV_CAPTURE1	= ",\"tripRef\":\"T5017864736\",\"productType\":\"DOMESTIC-AIR\"}";
	String paramsDA = "{\"payment\":[{\"tripId\":46393238,\"seqNo\":1,\"highRisk\":false,\"d_plus_x_in_hours\":1138,\"fraudSystemInvocation\":\"N\",\"uiVersion\":\"v1\",\"customerDetail\":{\"address1\":\"One way\",\"city_name\":\"jp nagar\",\"postal_code\":\"111111\",\"state_name\":\"KA\",\"country_name\":\"India\",\"landline\":\"08012121212\",\"email\":\"123@123.com\"},\"appRef1\":\"Q1221212112\",\"appRef2\":\"75604242\",\"paymentType\":\"DA\",\"amount\":1.1,\"currency\":\"INR\",\"orderInfo1\":\"SG/8905/DEL/BLR/202004XXXXXX00\",\"orderInfo2\":\"Kiran Kumar\",\"sourceType\":\"API\",\"userId\":41651546,\"companyId\":110340,\"appReturnInfo\":{\"method\":\"POST\"},\"paymentCategory\":\"B\",\"deposit_account_detail\":{\"id\":44980178,\"transaction_password\":\"test123\"},\"isPWA\":false,\"pwa\":false,\"dplusXInHours\":1138}]}";
	String paramsDAValidateV3 = "[{\"payment\":{\"seq_no\":1,\"high_risk\":false,\"d_plus_x_in_hours\":633,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v1\",\"customer_detail\":{\"address1\":\"test\",\"city_name\":\"test\",\"postal_code\":\"121212\",\"state_name\":\"test\",\"country_name\":\"india\",\"mobile\":\"121221211\",\"landline\":\"\",\"email\":\"testcthotel@gmail.com\"},\"payment_type\":\"DA\",\"amount\":10.0,\"currency\":\"INR\",\"order_info1\":\"SG/8161/DEL/BOM/20200426155000\",\"order_info2\":\"test booking\",\"source_type\":\"CORP\",\"user_id\":13939735,\"company_id\":101,\"app_return_info\":{\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://corporate.cltp.com:9001/r3/corp/air-book-internal?itineraryId\\u003d689a209bce-a21e-49ac-a0f9-200330145310\\u0026corpPageId\\u003d\"},\"deposit_account_detail\":{\"id\":46207144,\"transaction_password\":\"test123\"}}}]";
	String paramsDAPayV3 = "[{\"payment\":{\"seq_no\":1,\"trip_id\":43453224,\"high_risk\":false,\"d_plus_x_in_hours\":633,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v1\",\"customer_detail\":{\"address1\":\"test\",\"city_name\":\"test\",\"postal_code\":\"121212\",\"state_name\":\"test\",\"country_name\":\"india\",\"mobile\":\"121221211\",\"landline\":\"\",\"email\":\"testcthotel@gmail.com\"},\"app_ref1\":\"Q200330788702\",\"app_ref2\":\"75629492\",\"payment_type\":\"DA\",\"amount\":1.0,\"currency\":\"INR\",\"order_info1\":\"SG/8161/DEL/BOM/20200426155000\",\"order_info2\":\"test booking\",\"source_type\":\"CORP\",\"user_id\":790982,\"company_id\":101,\"app_return_info\":{\"url\":\"https://demo.cleartripforbusiness.com/airConfirmNetBanking?itineraryId\\u003d689a209bce-a21e-49ac-a0f9-200330145310\\u0026corpPageId\\u003db8d196f8-af63-45cd-9516-d4c91f7821f2\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://corporate.cltp.com:9001/r3/corp/air-book-internal?itineraryId\\u003d689a209bce-a21e-49ac-a0f9-200330145310\\u0026corpPageId\\u003db8d196f8-af63-45cd-9516-d4c91f7821f2\"},\"deposit_account_detail\":{\"id\":46207144,\"transaction_password\":\"test123\"}}}]";

	String paramsGV  = "[{\"payment\":{\"seq_no\":2,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":1618,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"Q18110930000\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"684fe048c7-cde3-4c20-9b73-a70e3c43bc9d\",\"payment_type\":\"GV\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/8481/PAT/DEL/201812XXXXXX00\",\"order_info2\":\"Cleartrip tester\",\"source_type\":\"WL\",\"user_id\":51351954,\"company_id\":5291262,\"app_return_info\":{\"url\":\"dummy\",\"method\":\"POST\"},\"gift_voucher_detail\":{\"card_number\":\"3000331032330649\",\"card_pin\":\"968322\",\"amount\":1,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/8481/PAT/DEL/201812XXXXXX00\",\"order_info2\":\"Cleartrip Tester\",\"source_type\":\"WL\",\"user_id\":51351954,\"company_id\":5291262,\"app_return_info\":{\"url\":\"dummy\",\"method\":\"POST\"},\"gift_voucher_detail\":{\"card_number\":\"3000331035883413\",\"card_pin\":\"273064\",\"card_category\":\"\"}}}}]";
	String paramsWallet ="[{\"payment\":{\"seq_no\":1,\"trip_id\":54808092,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":1618,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"Q1809140000\",\"app_ref2\":\"74049672\",\"itinerary_id\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"payment_type\":\"WT\",\"amount\":0.1,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E\\/233\\/BLR\\/MAA\\/20181125062500\",\"order_info2\":\"Test Booking\",\"source_type\":\"ACCOUNT\",\"user_id\":41654864,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/681f6b756d-67de-4efc-b663-5a7ac1bd9fa1/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/681f6b756d-67de-4efc-b663-5a7ac1bd9fa1/book-internal?ll=INFO\"},\"user_agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrom/69.0.3497.100 Safari/537.36\"}}]";
	String paramsCCGVWL ="[{\"payment\":{\"seq_no\":1,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":276,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.73.123\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\",\"firstName\":\"test\"},\"app_ref1\":\"Q18110926800\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"683a3a6bec-4e58-422a-a2c9-90707b1e5a12\",\"payment_type\":\"CC\",\"amount\":10.99,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"9W/362/DEL/BOM/201811XXXXXX00\",\"order_info2\":\"Kiran Kumar\",\"source_type\":\"ACCOUNT\",\"user_id\":85721640,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://www.cleartrip.com/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"5123456789012346\",\"card_type_id\":1,\"expiry_month\":\"05\",\"expiry_year\":\"2020\",\"cvv\":\"123\",\"name\":\"CleartripCard\",\"billto_detail\":{\"firstname\":\"test\",\"lastname\":\"test\",\"address1\":\"Cleartrip JP Nagar\",\"city_name\":\"Bangalore\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"postal_code\":\"560076\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\"}},{\"payment\":{\"seq_no\":2,\"trip_id\":54808092,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":1618,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"Q1809140000\",\"app_ref2\":\"74049672\",\"itinerary_id\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"payment_type\":\"WT\",\"amount\":10,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E\\/233\\/BLR\\/MAA\\/20181125062500\",\"order_info2\":\"Test Booking\",\"source_type\":\"ACCOUNT\",\"user_id\":41654864,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/681f6b756d-67de-4efc-b663-5a7ac1bd9fa1/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/681f6b756d-67de-4efc-b663-5a7ac1bd9fa1/book-internal?ll=INFO\"},\"user_agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrom/69.0.3497.100 Safari/537.36\"}},{\"payment\":{\"seq_no\":2,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":1618,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"Q18110930000\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"684fe048c7-cde3-4c20-9b73-a70e3c43bc9d\",\"payment_type\":\"GV\",\"amount\":10.0,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/8481/PAT/DEL/201812XXXXXX00\",\"order_info2\":\"Cleartrip tester\",\"source_type\":\"WL\",\"user_id\":51351954,\"company_id\":5291262,\"app_return_info\":{\"url\":\"dummy\",\"method\":\"POST\"},\"gift_voucher_detail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\",\"amount\":1,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/8481/PAT/DEL/201812XXXXXX00\",\"order_info2\":\"Cleartrip Tester\",\"source_type\":\"WL\",\"user_id\":51351954,\"company_id\":5291262,\"app_return_info\":{\"url\":\"dummy\",\"method\":\"POST\"},\"gift_voucher_detail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\",\"card_category\":\"\"}}}}]";
	String paramsInit  = "[{\"payment\":{\"seq_no\":1,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":276,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.73.123\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\",\"firstName\":\"test\"},\"app_ref1\":\"Q18110920000\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"683a3a6bec-4e58-422a-a2c9-90707b1e5a12\",\"payment_type\":\"CC\",\"amount\":10.99,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"\",\"order_info2\":\"\",\"source_type\":\"ACCOUNT\",\"user_id\":85721640,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://www.cleartrip.com/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"5497774415170603\",\"card_type_id\":1,\"expiry_month\":\"02\",\"expiry_year\":\"2021\",\"cvv\":\"412\",\"name\":\"test\",\"billto_detail\":{\"firstname\":\"Cleartrip\",\"lastname\":\"Tester\",\"address1\":\"JP Nagar\",\"city_name\":\"Bangalore\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"postal_code\":\"560076\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\"}}]";
	String paramsctPay_UpdateClient ="{\"client_id\":1125,\"active\":true}";
	String paramsctPay_GetURL = "{\"order_id\":\"T2333454445\",\"client_id\":1111,\"amount\":700.10,\"currency\":\"INR\",\"country\":\"IN\",\"udf\":{\"udf1\":\"1\",\"udf2\":\"2\",\"udf3\":\"3\",\"udf4\":\"4\",\"udf5\":\"5\"},\"customer_detail\":{\"ip_address\":\"217.164.159.242\",\"address1\":\"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",\"address2\":\"null\",\"address3\":null,\"city_name\":\"Mumbai\",\"postal_code\":\"400011\",\"state_name\":\"Maharashtra\",\"country_name\":\"India\",\"mobile\":\"121222112\",\"landline\":\"1212121212\",\"other_phone\":null,\"email\":\"cltppayment@gmail.com\"}}";
	String paramsctPay_ADD = "{\"client_id\":";
	String paramsctPay_ADD1 = ",\"s2s_url\":\"wwww.123.com\",\"payment_types\":\"CC,DC,TW\"}";


	String paramsctPay_CreateURL = "{\"order_id\":\"T2021069300\",\"client_id\":1125,\"amount\":1234.52,\"currency\":\"INR\",\"country\":\"IN\",\"return_url\":\"http://qa2.cleartrip.com\",\"udf\":{\"udf1\":\"Air ()\",\"udf2\":\"Air - ()\",\"udf3\":\"Hotel ()\",\"udf4\":\"Local & ()\",\"udf5\":\"Trains ()\"},\"customer_detail\":{\"ip_address\":\"217.164.159.242\",\"address1\":\"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",\"address2\":\"Cleartrip JP Nagar\",\"address3\":null,\"city_name\":\"Bangalore\",\"postal_code\":\"560085\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"1212121112\",\"landline\":\"121212121221\",\"other_phone\":null,\"email\":\"cltppayment@gmail.com\"}}";
	String paramsFlyIN = "{\"amount\":10.10,\"currency\":\"SAR\",\"country\":\"SA\",\"txnid\":\"110119042057CTK3ZG2\",\"payment_type\":\"CC\",\"product_info\":\"Flight_Flyin\",\"source_type\":\"ACCOUNT\",\"host_name\":\"preproduction.flyin.com\",\"udf\":{\"udf1\":\"vkY25EH\",\"udf2\":\"F booking txn amount 10.10\"},\"company_id\":205,\"customer_detail\":{\"ip_address\":\"119.82.106.204\",\"mobile\":\"12121121212\",\"email\":\"123@flyin.com\"},\"card_detail\":{\"card_number\":\"4242424242424242\",\"card_type_id\":1,\"expiry_month\":\"05\",\"expiry_year\":\"2021\",\"cvv\":\"100\",\"name\":\"test test\"},\"return_url\":\"http://payments.fly.in/payment/finalresponse/ct?pid=vkY25EH\"}";
	String paramWalletCreate = "{\"currency\":\"AED\",\"createdBy\":\"13957750\",\"amount\":\"100\",\"paymentId\":\"43188350\",\"tripRef\":\"Q190822469836\",\"eventType\":\"CREATION\",\"expiryDate\":\"2020-12-21\"}";
	String paramsGV_Create_10 = "{\"currency\":\"INR\",\"amount\":\"10\",\"userEmail\":\"kiran.kumar@cleartrip.com\",\"paymentId\":\"43222146\"}";
	String paramsGV_Create_5000 = "{\"currency\":\"INR\",\"amount\":\"5000\",\"userEmail\":\"kiran.kumar@cleartrip.com\",\"paymentId\":\"43222232\"}";
	String paramsGV_Create_100000 = "{\"currency\":\"INR\",\"amount\":\"100000\",\"userEmail\":\"kiran.kumar@cleartrip.com\",\"paymentId\":\"43222232\"}";

	
	String paramsEW_Pay = "{\"payment\":[{\"seq_no\":1,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1903221094\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"EP\",\"amount\":5,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Mitali Biswas\",\"source_type\":\"ACCOUNT\",\"user_id\":41649008,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"}]}";
	String paramsEW_PayV3 = "[{\"payment\":{\"seq_no\":1,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"varalakshmi.venkateshaiah@cleartrip.com\"},\"app_ref1\":\"Q19051212126\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"EP\",\"amount\":10,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Kiran Kumar\",\"source_type\":\"ACCOUNT\",\"user_id\":41683432,\"company_id\":41654864,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"}}]";
	String paramsEW_PayMultiV3 = "[{\"payment\":{\"seq_no\":4,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1903221094\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"EP\",\"amount\":5,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Mitali Biswas\",\"source_type\":\"ACCOUNT\",\"user_id\":41649008,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"}},{\"payment\":{\"seq_no\":1,\"trip_id\":116912714,\"appUserid\":10001,\"productType\":\"DOMESTIC-AIR\",\"highRisk\":false,\"d_plus_x_in_hours\":1618,\"fraudSystemInvocation\":\"N\",\"uiVersion\":\"v2\",\"customerDetail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"anas.ansari@cleartrip.com\"},\"appRef1\":\"Q6876405349\",\"appRef2\":\"74049672\",\"itineraryId\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"paymentType\":\"GV\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"orderInfo1\":\"6E/233/BLR/MAA/20181125062500\",\"orderInfo2\":\"Test Booking\",\"sourceType\":\"ACCOUNT\",\"userId\":13957750,\"companyId\":110340,\"appReturnInfo\":{\"url\":\"http://172.17.15.176:9080/return\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://172.17.15.176:9080/bookInternalURL\"},\"giftVoucherDetail\":{\"card_number\":\"3000331035614653\",\"card_pin\":\"105525\"},\"userAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\",\"paymentCategory\":\"B\",\"isPWA\":false,\"dplusXInHours\":1618,\"pwa\":false}},{\"payment\":{\"seq_no\":2,\"trip_id\":116912714,\"appUserid\":10001,\"productType\":\"DOMESTIC-AIR\",\"highRisk\":false,\"d_plus_x_in_hours\":1618,\"fraudSystemInvocation\":\"N\",\"uiVersion\":\"v2\",\"customerDetail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"anas.ansari@cleartrip.com\"},\"appRef1\":\"Q6876405349\",\"appRef2\":\"74049672\",\"itineraryId\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"paymentType\":\"GV\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"orderInfo1\":\"6E/233/BLR/MAA/20181125062500\",\"orderInfo2\":\"Test Booking\",\"sourceType\":\"ACCOUNT\",\"userId\":13957750,\"companyId\":110340,\"appReturnInfo\":{\"url\":\"http://172.17.15.176:9080/return\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://172.17.15.176:9080/bookInternalURL\"},\"giftVoucherDetail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\"},\"userAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\",\"paymentCategory\":\"B\",\"isPWA\":false,\"dplusXInHours\":1618,\"pwa\":false}},{\"payment\":{\"seq_no\":1,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"customer_detail\":{\"address1\":\"Flat 403 Tower 1, Mallika Malancha HIG Complex Action area 2B, Newtown\",\"city_name\":\"kolkata\",\"postal_code\":\"560066\",\"state_name\":\"West Bengal\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1904190001\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"WT\",\"amount\":50,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Test test\",\"source_type\":\"ACCOUNT\",\"user_id\":41654864,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"}}]";
	String paramsEW_PayMulti= "{\"payment\":[{\"seq_no\":4,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1904190001\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"EP\",\"amount\":100.5,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"test test\",\"source_type\":\"ACCOUNT\",\"user_id\":41649008,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"},{\"seq_no\":1,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1904190001\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"WT\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Test test\",\"source_type\":\"ACCOUNT\",\"user_id\":41649008,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"},{\"seq_no\":2,\"trip_id\":116912714,\"appUserid\":10001,\"productType\":\"DOMESTIC-AIR\",\"highRisk\":false,\"d_plus_x_in_hours\":1618,\"fraudSystemInvocation\":\"N\",\"uiVersion\":\"v2\",\"customerDetail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"anas.ansari@cleartrip.com\"},\"appRef1\":\"Q6876405349\",\"appRef2\":\"74049672\",\"itineraryId\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"paymentType\":\"GV\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"orderInfo1\":\"6E/233/BLR/MAA/20181125062500\",\"orderInfo2\":\"Test Booking\",\"sourceType\":\"ACCOUNT\",\"userId\":13957750,\"companyId\":110340,\"appReturnInfo\":{\"url\":\"http://172.17.15.176:9080/return\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://172.17.15.176:9080/bookInternalURL\"},\"giftVoucherDetail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\"},\"userAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\",\"paymentCategory\":\"B\",\"dplusXInHours\":1618,\"pwa\":false},{\"seq_no\":3,\"trip_id\":116912714,\"appUserid\":10001,\"productType\":\"DOMESTIC-AIR\",\"highRisk\":false,\"d_plus_x_in_hours\":1618,\"fraudSystemInvocation\":\"N\",\"uiVersion\":\"v2\",\"customerDetail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"anas.ansari@cleartrip.com\"},\"appRef1\":\"Q6876405349\",\"appRef2\":\"74049672\",\"itineraryId\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"paymentType\":\"GV\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"orderInfo1\":\"6E/233/BLR/MAA/20181125062500\",\"orderInfo2\":\"Test Booking\",\"sourceType\":\"ACCOUNT\",\"userId\":13957750,\"companyId\":110340,\"appReturnInfo\":{\"url\":\"http://172.17.15.176:9080/return\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://172.17.15.176:9080/bookInternalURL\"},\"giftVoucherDetail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\"},\"userAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\",\"paymentCategory\":\"B\",\"isPWA\":false,\"dplusXInHours\":1618,\"pwa\":false}]}";
	String paramsGVWL = "[{\"payment\":{\"seq_no\":2,\"trip_id\":54808092,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":1618,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"kiran.kumar@cleartrip.com\"},\"app_ref1\":\"T8162456096\",\"app_ref2\":\"74049672\",\"itinerary_id\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"payment_type\":\"WT\",\"amount\":10,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E\\/233\\/BLR\\/MAA\\/20181125062500\",\"order_info2\":\"Test Booking\",\"source_type\":\"ACCOUNT\",\"user_id\":41654864,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/681f6b756d-67de-4efc-b663-5a7ac1bd9fa1/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/681f6b756d-67de-4efc-b663-5a7ac1bd9fa1/book-internal?ll=INFO\"},\"user_agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrom/69.0.3497.100 Safari/537.36\"}},{\"payment\":{\"seq_no\":2,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":1618,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"T8162456096\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"684fe048c7-cde3-4c20-9b73-a70e3c43bc9d\",\"payment_type\":\"GV\",\"amount\":10.0,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/8481/PAT/DEL/201812XXXXXX00\",\"order_info2\":\"Cleartrip tester\",\"source_type\":\"WL\",\"user_id\":51351954,\"company_id\":5291262,\"app_return_info\":{\"url\":\"dummy\",\"method\":\"POST\"},\"gift_voucher_detail\":{\"card_number\":\"3000331038355707\",\"card_pin\":\"802965\",\"amount\":10,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/8481/PAT/DEL/201812XXXXXX00\",\"order_info2\":\"Cleartrip Tester\",\"source_type\":\"WL\",\"user_id\":51351954,\"company_id\":5291262,\"app_return_info\":{\"url\":\"dummy\",\"method\":\"POST\"},\"gift_voucher_detail\":{\"card_number\":\"3000331039130955\",\"card_pin\":\"192217\",\"card_category\":\"\"}}}}]";
	
	String paramsEW_Validate = "{\"payment\":[{\"seq_no\":1,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"Q1903221094\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"EP\",\"amount\":5,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Mitali Biswas\",\"source_type\":\"ACCOUNT\",\"user_id\":41654864,\"company_id\":41654864,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"}]}";
	String paramsEW_AddAmt = "";
	String paramsDA_Refund = "{\"txn_id\":75413816,\"payment_id\":43422440,\"refund_txn_id\":75413816,\"amount\":1352.0}";

	String ParamsValidate = "[{\"payment\":{\"seq_no\":1,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":755,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.204\",\"mobile\":\" 919986696785\",\"email\":\"cltppayment@gmail.com\"},\"itinerary_id\":\"68730b1763-f9d8-4059-88ff-62ce5f4a1ef8\",\"payment_type\":\"CC\",\"amount\":4118.0,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/812/BLR/CCU/20181113055500\",\"order_info2\":\"Test Pay\",\"source_type\":\"ACCOUNT\",\"user_id\":41644016,\"company_id\":110340,\"app_return_info\":{\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/68730b1763-f9d8-4059-88ff-62ce5f4a1ef8/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"5123456789012346\",\"card_type_id\":2,\"expiry_month\":\"5\",\"expiry_year\":\"2020\",\"cvv\":\"123\",\"name\":\"Test\",\"billto_detail\":{\"firstname\":\"Test\",\"address1\":\" \",\"city_name\":\"\",\"state_name\":\"\",\"country_name\":\"\",\"postal_code\":\"\"}}}}]";
	String ParamsOLAValidate1 = "{\"promoId\":\"PS";
	String ParamsOLAValidate2 = "\",\"amount\":200,\"tripId\":\"Q898989879\",\"recipientId\":\"9986696785\",\"currency\":\"INR\",\"comments\":\"test comment\",\"returnUrl\":\"NA\",\"notificationUrl\":\"NA\",\"balanceType\":\"cash\",\"balanceName\":\"cash\",\"agentId\":\"NA\"}";
	String ParamsOLAPromoGroup1 = "{\"user_id\":41651522,\"trip_ref\":\"Q";
	String ParamsOLAPromoGroup2 = "\",\"type\":\"HOTEL_BOOKING\",\"promotions\":[{\"amount\":500.55,\"currency\":\"INR\",\"expiry_date\":\"11-11-2021\",\"trigger_date\":\"12-11-2019\",\"wallet\":\"OLA\",\"mobile\":\"9986696785\",\"email\":\"test@test.com\"}]}";

	String paramsOLAPay="\",\"amount\":50.55,\"tripId\":\"Q898989879\",\"recipientId\":\"9986696785\",\"currency\":\"INR\",\"comments\":\"test\",\"returnUrl\":\"NA\",\"notificationUrl\":\"NA\",\"balanceType\":\"cash\",\"balanceName\":\"cash\",\"agentId\":\"NA\"}"; 

	String Paramsadcb_checkbalance1 = "{  \"rewardsType\": \"ADCB\",  \"rewardsRequestType\": \"BALANCE_CHECK\",  \"trackId\": \"";
	String Paramsadcb_checkbalance2 = "\",  \"cardNumber\": \"5326655505894755\",  \"amount\": 0.01,  \"currency\": \"AED\",   \"params\": {     \"customerName\": \"test\",     \"expiryDate\": \"2111\"  }}";
	String Paramsadcb_sendOTP1 = "{  \"rewardsType\": \"ADCB\",  \"rewardsRequestType\": \"OTP\",  \"trackId\": \"";
	String Paramsadcb_sendOTP2 = "\",  \"cardNumber\": \"5326655505894755\",  \"amount\": 0.01,  \"currency\": \"AED\",  \"params\": {  \t \"customerName\": \"test\",     \"expiryDate\": \"2111\"  }}";
	
	String Paramsadcb_validate1="[   {      \"payment\":{         \"seq_no\":1,         \"payment_subtype\":\"ADCB\",         \"trip_id\":45025226,         \"app_userid\":10001,         \"product_type\":\"INTL-AIR\",         \"high_risk\":false,         \"d_plus_x_in_hours\":0,         \"payment_category\":\"B\",         \"fraud_system_invocation\":\"Y\",         \"ui_version\":\"v2\",         \"customer_detail\":{            \"ip_address\":\"192.168.50.230\",            \"mobile\":\"9999999999\",            \"email\":\"123@cleartrip.com\"         },         \"itinerary_id\":\"68730b1763-f9d8-4059-88ff-62ce5f4a1ef8\",         \"payment_type\":\"RP\",         \"amount\":10,         \"currency\":\"AED\",         \"country\":\"AE\",         \"order_info1\":\"SG/812/BLR/CCU/20181113055500\",         \"order_info2\":\"Test\",         \"source_type\":\"ACCOUNT\",         \"user_id\":41644016,         \"company_id\":110340,         \"app_return_info\":{            \"method\":\"POST\",            \"book_internal\":true,            \"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/68730b1763-f9d8-4059-88ff-62ce5f4a1ef8/book-internal?ll=INFO\"         },         \"host_name\":\"qa2.cleartrip.com\",         \"reward_points_detail\":{            \"trackId\":\"";
	
	String Paramsadcb_validate2=  "\",            \"otp\":\"11111111\",            \"bankName\": \"ADCB\",            \"card_detail\":{               \"card_number\":\"5326655505894755\",               \"card_type_id\":2,               \"expiry_month\":\"11\",               \"expiry_year\":\"2021\",               \"cvv\":\"242\",               \"name\":\"Test\",               \"billto_detail\":{                  \"firstname\":\"Test\",                  \"address1\":\" \",                  \"city_name\":\"\",                  \"state_name\":\"\",                  \"country_name\":\"\",                  \"postal_code\":\"\"               }            }         }      }   }]";
	String Paramsadcb_pay1 = "[ {\n  \"payment\" : { \"seq_no\" : 1, \"payment_subtype\":\"ADCB\", \"trip_id\" : 45025226, \"app_userid\" : 10001, \"product_type\" : \"INTL-AIR\", \"high_risk\" : false, \"d_plus_x_in_hours\" : 0, \"payment_category\" : \"B\", \"fraud_system_invocation\" : \"Y\", \"ui_version\" : \"v2\", \"customer_detail\" : {   \"ip_address\" : \"192.168.50.230\",   \"mobile\" : \"9999999999\",   \"email\" : \"123@cleartrip.com\" }, \"app_ref1\" : \"Q1810120439\", \"app_ref2\" : \"74093112\", \"itinerary_id\" : \"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\", \"payment_type\" : \"RP\", \"amount\" : 0.01, \"currency\" : \"AED\", \"country\" : \"AE\", \"order_info1\" : \"6E/21/DEL/DXB/201810XXXXXX00\", \"order_info2\" : \"test testing\", \"source_type\" : \"ACCOUNT\", \"user_id\" : 41629512, \"company_id\" : 110340, \"app_return_info\" : {   \"url\" : \"https://qa2.cleartrip.ae/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book\",   \"method\" : \"POST\",   \"book_internal\" : true,   \"book_internal_url\" : \"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book-internal?ll=INFO\" }, \"host_name\" : \"qa2.cleartrip.ae\", \"reward_points_detail\":{         \"trackId\":\"";
	String Paramsadcb_pay2 = "\",         \"otp\":\"11111111\",          \"bankName\": \"ADCB\",         \"card_detail\":{            \"card_number\":\"5326655505894755\",            \"card_type_id\":2,            \"expiry_month\":\"11\",            \"expiry_year\":\"2021\",            \"cvv\":\"242\",            \"name\":\"Test\",            \"billto_detail\":{               \"firstname\":\"Test\",               \"address1\":\" \",               \"city_name\":\"\",               \"state_name\":\"\",               \"country_name\":\"\",               \"postal_code\":\"\"            }         } }, \"user_agent\" : \"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\"\n  }\n} ]\n";
	
	String ParamsPayBackCheckBalance = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"BALANCE_CHECK\",\"trackId\":\"SAL1011122\",\"currency\":\"INR\",\"params\":{\"customerName\":\"cleartrip\",\"mobile\":\"9632699584\",\"pin\":\"3642\"}}";
	String ParamsPayBackCheckBalance_card = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"BALANCE_CHECK\",\"trackId\":\"CLRP1000018\",\"cardNumber\":\"9401120000319303\",\"params\":{\"customerName\":\"test\",\"pin\":\"3642\"}}";
	String ParamsPayBack_Vaidate1 = "[{\"payment\":{\"seq_no\":1,\"payment_subtype\":\"PAYBACK\",\"trip_id\":45025226,\"app_userid\":10001,\"product_type\":\"INTL-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":0,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"192.168.50.230\",\"mobile\":\"9620351338\",\"email\":\"123@cleartrip.com\"},\"itinerary_id\":\"68730b1763-f9d8-4059-88ff-62ce5f4a1ef8\",\"payment_type\":\"RP\",\"amount\":1,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/812/BLR/CCU/20181113055500\",\"order_info2\":\"Test\",\"source_type\":\"ACCOUNT\",\"user_id\":41644016,\"company_id\":110340,\"app_return_info\":{\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/68730b1763-f9d8-4059-88ff-62ce5f4a1ef8/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"reward_points_detail\":{\"trackId\":\"CLRP1000014\",\"params\":{\"mobile\":\"9620351338\",\"pin\":\"6486\"}}}}]";
	String ParamsPayBack_CheckEarnPoints = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"EARN_CHECK\",\"trackId\":\"SAL1011122ghfjkf\",\"amount\":7050,\"currency\":\"INR\",\"params\":{\"source\":\"MOBILE\",\"isPwa\":true}}";
	String ParamsPayBack_ForgotPassword = "{\"mobileNumber\":\"9632699584\",\"cardNumber\":null}";
	String ParamsPayBack_Pay = "[{\"payment\":{\"seq_no\":1,\"payment_subtype\":\"PAYBACK\",\"trip_id\":45025226,\"app_userid\":10001,\"product_type\":\"INTL-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":0,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"192.168.50.230\",\"mobile\":\"9999999999\",\"email\":\"123@cleartrip.com\"},\"app_ref1\":\"Q18101204391\",\"app_ref2\":\"74093112\",\"itinerary_id\":\"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\",\"payment_type\":\"RP\",\"amount\":10,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/21/DEL/DXB/201810XXXXXX00\",\"order_info2\":\"test testing\",\"source_type\":\"ACCOUNT\",\"user_id\":41629512,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.ae/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"reward_points_detail\":{\"trackId\":\"";
	String ParamsPayBack_Pay1 = "\",\"params\":{\"customerName\":\"test\",\"mobile\":\"9620351338\",\"pin\":\"6486\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\"}}]";
	String ParamsPayBack_Earn = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"EARN\",\"trackId\":\"";
	String ParamsPayBack_Earn1 = "\",\"cardNumber\":null,\"amount\":1,\"currency\":\"INR\",\"params\":{\"mobile\":\"9986696785\",\"tripRef\":\"Q191014530822\"}}";

	String ParamsPayBack_reverseearn = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"REVERSE_EARN\",\"trackId\":\"";
	String ParamsPayBack_reverseearn1 = "\",\"amount\":1,\"cardNumber\":null,\"currency\":\"INR\",\"params\":{\"mobile\":\"9986696785\",\"tripRef\":\"Q191014530822\"}}";
	String ParamsPaymentUIGetPay = "{\"train_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Ms\",\"first_name\":\"Test\",\"last_name\":\"Test\"}],\"itinerary_details\":[{\"from_station_code\":\"SBC\",\"to_station_code\":\"MYS\",\"from_station_name\":\"KSR BENGALURU\",\"to_station_name\":\"MYSURU JN\",\"train_name\":\"BSB MYS EXP\",\"train_number\":\"16230\",\"departure_time\":\"2019-11-12T00:15:00\",\"arrival_time\":\"2019-11-12T02:45:00\",\"booking_class\":\"AC 2 Tier(2A)\",\"updated_availability\":\"AVAILABLE-0119\",\"quota\":\"General\",\"seatStatus\":true}],\"pricing_details\":[{\"other_railway_charges\":119.4,\"agent_service_charge\":40,\"total\":775.4,\"insuranceCharge\":0,\"currency\":\"INR\",\"pax_pay_info\":[{\"base_fare\":616,\"pax_count\":1,\"pax_type\":\"ADULT\"}]}],\"transaction_fee_details\":{\"CC\":{\"DEFAULT\":1.8},\"DC\":{\"DEFAULT\":1},\"NB\":{\"1\":1.1,\"2\":1.2,\"3\":1.1,\"23\":1.1,\"DEFAULT\":1.35},\"KC\":{\"DEFAULT\":0},\"DA\":{\"DEFAULT\":0},\"TW\":{\"DEFAULT\":1},\"UP\":{\"DEFAULT\":1}}},\"itinerary_id\":\"f25db800de1e0137664316217d236675\",\"ttl\":3600,\"trip_id\":45134538,\"app_ref1\":\"Q1902210388\",\"app_ref2\":74282510,\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":1212121212,\"landline\":1212121212,\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"payment\",\"username\":\"cltppayment@gmail.com\"},\"product_type\":\"TRAIN\",\"currency\":\"INR\",\"order_info1\":\"16230/SBC/MYS/2019111200:15:00\",\"order_info2\":\"Test Test\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"64891349\",\"email_id\":\"cltppayment@gmail.com\",\"d_plus_x_in_hours\":273,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/trains/itinerary/f25db800de1e0137664316217d236675/process_payment\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://trains-book-nget.cltp.com:9001/r3/trains/itinerary/f25db800de1e0137664316217d236675/book_internal\",\"params\":null},\"payment_category\":\"B\"}";
	String ParamsPayBack_PayWT = "\",   \"params\": {\"customerName\": \"test\", \"mobile\": \"9620351338\", \"pin\":\"6486\"   }     },     \"user_agent\" : \"Mozilla/5.0(WindowsNT6.1;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/69.0.3497.100Safari/537.36\"   }  },  {    \"payment\":{ \"seq_no\":2, \"trip_id\":45025226, \"app_userid\":10001, \"product_type\":\"DOMESTIC-AIR\", \"high_risk\":false, \"d_plus_x_in_hours\":0, \"payment_category\":\"B\", \"customer_detail\":{    \"address1\":\"Flat403Tower1,MallikaMalanchaHIGComplexActionarea2B,Newtown\",    \"city_name\":\"kolkata\",    \"postal_code\":\"560066\",    \"state_name\":\"WestBengal\",    \"country_name\":\"India\",    \"mobile\":\"9620351338\",    \"email\":\"cltppayment@gmail.com\" }, \"app_ref1\":\"Q18101204300\", \"app_ref2\":\"74093112\", \"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\", \"payment_type\":\"WT\", \"amount\":5, \"currency\":\"INR\", \"country\":\"IN\", \"order_info1\":\"6E/21/DEL/BOM/201810XXXXXX00\", \"order_info2\":\"Testtesting\", \"source_type\":\"ACCOUNT\", \"user_id\":41649008, \"company_id\":41649008, \"host_name\":\"qa2.cleartrip.com\", \"user_agent\":\"Apache-HttpClient/4.4(Java1.5minimum;Java/1.8.0_51)\"       }  } ] ";
	String ParamsPayBack_PayWTMulti = "\",\"params\": { \"customerName\": \"test\", \"mobile\": \"9620351338\", \"pin\":\"6486\"}     },     \"user_agent\" : \"Mozilla/5.0(WindowsNT6.1;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/69.0.3497.100Safari/537.36\"   }  },  {    \"payment\":{          \"seq_no\":2,          \"trip_id\":45025226,          \"app_userid\":10001,          \"product_type\":\"DOMESTIC-AIR\",          \"high_risk\":false,          \"d_plus_x_in_hours\":0,          \"payment_category\":\"B\",          \"customer_detail\":{ \"address1\":\"Flat403Tower1,MallikaMalanchaHIGComplexActionarea2B,Newtown\", \"city_name\":\"kolkata\", \"postal_code\":\"560066\", \"state_name\":\"WestBengal\", \"country_name\":\"India\", \"mobile\":\"9620351338\", \"email\":\"cltppayment@gmail.com\"          },          \"app_ref1\":\"Q18101204300\",          \"app_ref2\":\"74093112\",          \"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",          \"payment_type\":\"WT\",          \"amount\":5.25,          \"currency\":\"INR\",          \"country\":\"IN\",          \"order_info1\":\"6E/21/DEL/BOM/201810XXXXXX00\",          \"order_info2\":\"Testtesting\",          \"source_type\":\"ACCOUNT\",          \"user_id\":41654864,          \"company_id\":110340,          \"host_name\":\"qa2.cleartrip.com\",          \"user_agent\":\"Apache-HttpClient/4.4(Java1.5minimum;Java/1.8.0_51)\"       }  },  {    \"payment\":{          \"seq_no\":3,          \"trip_id\":45025226,       \"appUserid\" : 10001,     \"productType\" : \"DOMESTIC-AIR\",     \"highRisk\" : false,     \"d_plus_x_in_hours\" : 0,     \"fraudSystemInvocation\" : \"N\",     \"uiVersion\" : \"v2\",    \"customer_detail\" : {       \"ip_address\" : \"192.168.50.230\",       \"mobile\" : \"9620351338\",       \"email\" : \"123@cleartrip.com\"     },     \"appRef1\" : \"Q18101204300\",     \"appRef2\" : \"74093112\",     \"itineraryId\" : \"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\",     \"paymentType\" : \"GV\",     \"amount\" : 10.0,     \"currency\" : \"INR\",     \"country\" : \"IN\",     \"orderInfo1\" : \"6E/21/DEL/BOM/201810XXXXXX00\",     \"orderInfo2\" : \"TestTesting\",     \"sourceType\" : \"ACCOUNT\",     \"userId\" : 41701828,     \"companyId\" : 110340,     \"appReturnInfo\" : {       \"url\" : \"http://172.17.15.176:9080/return\",       \"method\" : \"POST\",       \"book_internal\" : true,       \"book_internal_url\" : \"http://172.17.15.176:9080/bookInternalURL\"     },     \"giftVoucherDetail\" : {       \"card_number\" : \"3000331033112563\",       \"card_pin\" : \"714375\"     },     \"userAgent\" : \"Mozilla/5.0(WindowsNT6.1;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/69.0.3497.100Safari/537.36\",     \"paymentCategory\" : \"B\",     \"isPWA\" : false,     \"dplusXInHours\" : 1618,     \"pwa\" : false       }     },  {    \"payment\":{          \"seq_no\":4,          \"trip_id\":45025226,       \"appUserid\" : 10001,     \"productType\" : \"DOMESTIC-AIR\",     \"highRisk\" : false,     \"d_plus_x_in_hours\" : 0,     \"fraudSystemInvocation\" : \"N\",     \"uiVersion\" : \"v2\",    \"customer_detail\" : {       \"ip_address\" : \"192.168.50.230\",       \"mobile\" : \"9620351338\",       \"email\" : \"123@cleartrip.com\"     },     \"appRef1\" : \"Q18101204300\",     \"appRef2\" : \"74093112\",     \"itineraryId\" : \"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\",     \"paymentType\" : \"GV\",     \"amount\" : 10.5,     \"currency\" : \"INR\",     \"country\" : \"IN\",     \"orderInfo1\" : \"6E/21/DEL/BOM/201810XXXXXX00\",     \"orderInfo2\" : \"TestTesting\",     \"sourceType\" : \"ACCOUNT\",     \"userId\" : 41701828,     \"companyId\" : 110340,     \"appReturnInfo\" : {       \"url\" : \"http://172.17.15.176:9080/return\",       \"method\" : \"POST\",       \"book_internal\" : true,       \"book_internal_url\" : \"http://172.17.15.176:9080/bookInternalURL\"     },     \"giftVoucherDetail\" : {       \"card_number\" : \"3000331033112563\",       \"card_pin\" : \"714375\"     },     \"userAgent\" : \"Mozilla/5.0(WindowsNT6.1;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/69.0.3497.100Safari/537.36\",     \"paymentCategory\" : \"B\",     \"isPWA\" : false,     \"dplusXInHours\" : 1618,     \"pwa\" : false       }     }      ";
	String ParamsPayBack_PayWTMulti1 = 	"] ";
	String ParamsPayBack_PayWTMultiCC = ",{\"payment\":{\"seq_no\":5,\"trip_id\":45025226,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":0,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"192.168.50.230\",\"mobile\":\"9620351338\",\"email\":\"123@cleartrip.com\"},\"app_ref1\":\"Q18101204300\",\"app_ref2\":\"74093112\",\"itinerary_id\":\"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\",\"payment_type\":\"CC\",\"amount\":100.99,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/21/DEL/BOM/201810XXXXXX00\",\"order_info2\":\"test testing\",\"source_type\":\"ACCOUNT\",\"user_id\":41701828,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"5123456789012346\",\"card_type_id\":1,\"expiry_month\":\"05\",\"expiry_year\":\"2020\",\"cvv\":\"123\",\"name\":\"CleartripCard\",\"billto_detail\":{\"firstname\":\"test\",\"lastname\":\"test\",\"address1\":\"Cleartrip JP Nagar\",\"city_name\":\"Bangalore\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"postal_code\":\"560076\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\",\"isPWA\":false,\"pwa\":false}}";

	String ParamsPayBack_PayCC ="\",   \"params\": {  \"customerName\": \"test\",  \"mobile\": \"9620351338\",  \"pin\": \"6486\"   }   },   \"user_agent\": \"Mozilla/5.0(WindowsNT6.1;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/69.0.3497.100Safari/537.36\"    }},{    \"payment\": {   \"seq_no\": 2,   \"trip_id\": 45025226,   \"app_userid\": 10001,   \"product_type\": \"DOMESTIC-AIR\",   \"high_risk\": false,   \"d_plus_x_in_hours\": 0,   \"payment_category\": \"B\",   \"fraud_system_invocation\": \"Y\",   \"ui_version\": \"v2\",   \"customer_detail\": {   \"ip_address\": \"192.168.50.230\",   \"mobile\": \"9620351338\",   \"email\": \"123@cleartrip.com\"   },   \"app_ref1\": \"Q18101204300\",   \"app_ref2\": \"74093112\",   \"itinerary_id\": \"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\",   \"payment_type\": \"CC\",   \"amount\": 100.99,   \"currency\": \"INR\",   \"country\": \"IN\",   \"order_info1\": \"6E/21/DEL/BOM/201810XXXXXX00\",   \"order_info2\": \"testtesting\",   \"source_type\": \"ACCOUNT\",   \"user_id\": 41701828,   \"company_id\": 110340,   \"app_return_info\": {   \"url\": \"https://qa2.cleartrip.com/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book\",   \"method\": \"POST\",   \"book_internal\": true,   \"book_internal_url\": \"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book-internal?ll=INFO\"   },   \"host_name\": \"qa2.cleartrip.com\",   \"card_detail\": {   \"card_number\": \"5123456789012346\",   \"card_type_id\": 1,   \"expiry_month\": \"05\",   \"expiry_year\": \"2020\",   \"cvv\": \"123\",   \"name\": \"CleartripCard\",   \"billto_detail\": {  \"firstname\": \"test\",  \"lastname\": \"test\",  \"address1\": \"CleartripJPNagar\",  \"city_name\": \"Bangalore\",  \"state_name\": \"Karnataka\",  \"country_name\": \"India\",  \"postal_code\": \"560076\"   }   },   \"user_agent\": \"Mozilla/5.0(WindowsNT10.0;WOW64;Trident/7.0;rv:11.0)likeGecko\",   \"isPWA\": false,   \"pwa\": false    }} ]";

	String Params_Createrefund = "{\"isFullWalletRefund\":%s,\"tripRef\":\"%s\",\"description\":\"some test description\",\"amount\":%s,\"txnid\": %s }";
	String Params_Createrefund1 = "{\"isFullWalletRefund\":false,\"tripRef\":\"%s\",\"status\":null,\"description\":\"some test description\",\"amount\":%s,\"txnid\": %s }";

	String Params_Updatestatus ="{\"refundId\": %s ,\"currentStatus\":\"S\",\"finalStatus\":\"P\"}";
	String Params_Updatepgtxnid = "{\"refundId\": %s ,\"pgGenerationTxnId\": %s }";

	String Params_ROR_UpdateProfile_List= "{\"id\":5071366,\"card_number_prefix\":\"340000\",\"ip_address\":\"12.34.56.80\",\"phone_number\":\"";
	String Params_ROR_UpdateProfile_List1 = "\",\"booked_by_email_id\":\"\",\"card_holder_email_id\":\"\",\"effective_upto\":\"2019-12-26 05:30:00\",\"status\":\"T\",\"list_type\":0,\"temporary\":1,\"remarks\":\"Api tests\",\"user_id\":\"123456543\"}";


	String Params_ROR_UpdateRefund_List = "[{\"id\":9371271,\"paymentId\":43350738,\"refundAmount\":3221,\"refundFileId\":null,\"status\":\"D\",\"description\":\"test\",\"seqNo\":123,\"bosFileId\":null,\"pgGenerationTxnId\":75298206,\"paymentReconciliationFileId\":null,\"txnId\":75298206,\"taken\":null,\"pbosFileId\":1223,\"rollbacked\":null,\"rollbackSupported\":\"Y\",\"arnNo\":\"";

	String Params_ROR_UpdateRefund_List1 = "\",\"attempted\":\"N\",\"refundType\":\"DC\"}]";

	String Params_ROR_Create_Profile_List = "{\"card_number_prefix\":\"12345679\",\"ip_address\":\"12.34.56.81\",\"phone_number\":\"121212121221\",\"booked_by_email_id\":\"123@1223.com\",\"card_holder_email_id\":\"123@1223.com\",\"status\":\"T\",\"list_type\":0,\"temporary\":1,\"user_id\":\"123456543\"}";
	String Params_ROR_Update_Payments = "{\"id\":43327334,\"trip_id\":106562333,\"txn_id\":167823463,\"payment_type\":\"DC\",\"amount\":3.955111,\"created_at\":1571291019000,\"updated_at\":1578889867935,\"seq_no\":1,\"status\":\"S\",\"description\":\"Initializing the payment\",\"currency\":\"INR\",\"order_info1\":null,\"order_info2\":null,\"app_ref1\":\"167823462\",\"app_ref2\":167823462,\"neglist_id\":null,\"pos_list_id\":null,\"linkable_id\":null,\"linkable_type\":null,\"user_message\":null,\"pan_number\":null,\"payment_category\":\"B\",\"merchant_txn_ref\":\"43327334\",\"payment_subtype\":null,\"express_checkout\":null,\"emi_count\":null,\"emi_fee\":null,\"ref_payment_id\":null,\"payment_cards_gateway_accesses\":[{\"id\":389967720,\"payment_id\":43327334,\"trip_ref\":\"TPOtpK34\",\"txn_id\":167823462,\"payment_mode\":\"D\",\"gateway_id\":52,\"card_number\":\"458546XXXXXX5964\",\"name_on_card\":\"test\",\"amount\":1.0,\"status\":\"S\",\"description\":\"Initializing\",\"tran_type\":\"INIT\",\"seq_no\":1,\"created_at\":1571291061000,\"updated_at\":1571291061000,\"credential_name\":\"IN_TECH_PROCESS_OTP_QA\",\"retry_count\":0,\"card_number_hash\":\"d1e0bef8e70b9968ac037aa1d0b0b0242d4a3203836f0822d75a0c3267c37bf1\"},{\"id\":389967722,\"payment_id\":43327334,\"trip_ref\":\"TPOtpK34\",\"txn_id\":167823462,\"payment_mode\":\"D\",\"gateway_id\":52,\"card_number\":\"458546XXXXXX5964\",\"name_on_card\":\"test\",\"amount\":1.0,\"status\":\"F\",\"description\":\"OTP Flag is not present in TP response : Error Code :ERROR082\",\"gateway_response1\":\"0399\",\"gateway_response2\":\"failed\",\"gateway_response4\":\"1180\",\"gateway_response5\":\"43327334\",\"gateway_response8\":\"ERROR082\",\"gateway_response9\":\"NA\",\"gateway_response11\":\"b1029d24a0b8a9fe2ae7b00a815bb453d5c4d55f\",\"tran_type\":\"PAUT\",\"seq_no\":1,\"created_at\":1571291065000,\"updated_at\":1571291303000,\"credential_name\":\"IN_TECH_PROCESS_OTP_QA\",\"retry_count\":0,\"card_number_hash\":\"d1e0bef8e70b9968ac037aa1d0b0b0242d4a3203836f0822d75a0c3267c37bf1\"},{\"id\":389967724,\"payment_id\":43327334,\"trip_ref\":\"TPOtpK34\",\"txn_id\":167823462,\"payment_mode\":\"D\",\"gateway_id\":14,\"card_number\":\"458546XXXXXX5964\",\"name_on_card\":\"test\",\"amount\":1.0,\"status\":\"S\",\"description\":\"Initializing\",\"tran_type\":\"INIT\",\"seq_no\":1,\"created_at\":1571291303000,\"updated_at\":1571291303000,\"credential_name\":\"IN_TECH_PROCESS\",\"retry_count\":0,\"card_number_hash\":\"d1e0bef8e70b9968ac037aa1d0b0b0242d4a3203836f0822d75a0c3267c37bf1\"}],\"payment_cash_details\":null,\"payment_gift_voucher_txns\":[],\"payment_tp_wallet_txns\":[],\"payment_upi_txns\":[],\"payment_redirect_timings\":[{\"payment_id\":43327334,\"redirection_out\":1571291305948,\"created_at\":1571291305951,\"updated_at\":1571291305951,\"retry_count\":0,\"id\":34678514}],\"payment_card_details\":[{\"id\":30780182,\"payment_id\":43327334,\"card_number\":\"458546XXXX445964\",\"card_type_id\":1,\"payment_mode\":\"D\",\"bank_id\":6,\"created_at\":1571291061000,\"updated_at\":1571291061000,\"bin_id\":0,\"country\":\"INDIA\"}],\"payment_cash_card_detail\":null,\"payment_net_banking_details\":null,\"payment_ivr_detail\":null,\"payment_techpro_detail\":null,\"payment_third_party_da_details\":[],\"payment_third_party_details\":[],\"payment_ap_txns\":[],\"payment_da_transactions\":[],\"payment_da_details\":[],\"payment_wallet_transactions\":[],\"reward_points_txns\":null}";

	String Params_RORUpdate_GW_Status = "{\"currentStatus\":\"P\",\"status\":\"I\",\"gatewayId\":14}";
	String Params_RORCreate_Payment = "[{\"payment\":{\"id\":null,\"trip_id\":46198930,\"txn_id\":75300328,\"payment_type\":\"IV\",\"amount\":\"1000.0\",\"created_at\":\"2019-11-20T18:40:29+05:30\",\"updated_at\":\"2019-11-20T18:40:29+05:30\",\"seq_no\":3,\"status\":\"S\",\"description\":\"created by API\",\"currency\":\"INR\",\"order_info1\":123,\"order_info2\":345,\"app_ref1\":\"Q191109570525\",\"app_ref2\":75300328,\"neglist_id\":\"y\",\"poslist_id\":109,\"linkable_id\":null,\"linkable_type\":null,\"user_message\":null,\"pan_number\":null,\"payment_category\":\"B\",\"merchant_txn_ref\":\"12312\",\"payment_subtype\":\"ADCB\",\"express_checkout\":null,\"emi_count\":null,\"emi_fee\":null,\"ref_payment_id\":null,\"ivr_detail\":{\"id\":null,\"description\":null,\"created_at\":null,\"updated_at\":null,\"seq_no\":null,\"payment_id\":null,\"transaction_ref_no\":\"Q234334\",\"card_number\":\"1234 2344 3434\",\"response_message\":\"testmsg\",\"gateway_txn_id\":12345,\"gateway\":\"ivr_gateway\",\"status\":null,\"credential_name\":\"test\"}}}]";
	String Params_RORCreate_Profile_List = "{\"card_number_prefix\":\"12345679\",\"ip_address\":\"12.34.56.81\",\"phone_number\":\"121212121221\",\"booked_by_email_id\":\"123@1223.com\",\"card_holder_email_id\":\"123@1223.com\",\"status\":\"T\",\"list_type\":0,\"temporary\":1,\"user_id\":\"123456543\"}";
	String Params_RORSearch_Profile_List = "{\"list_type\":0,\"page_number\":2911}";
	String Params_RORCreate_Refund = "{\"isFullWalletRefund\":false,\"tripRef\":\"Q191204588938\",\"amount\":10,\"description\":\"Autaomtion REFUND\",\"txnid\":";
	String ParamsROR_Recon = "{\"tripRef\":\"Q191203587976\",\"txnId\":";

	String ParamsFetchRefund="{\"refundIds\":[9387165,9387150,9387149,9387190,9387357"
			+ ",9387405],\"txnIds\": null,"+"\"status\": [\"D\",\"P\",\"S\",\"T\",\"F\"]}";
	
	String ParamsFetchRefundByTripRef="{\"tripRef\":[\"Q200422824400\",\"Q200421824282\",\"Q200421824270\",\"Q200414822686\",\"Q200414822642\", \"Q200413822486\",\"Q200413822480\"],\"status\": \"D\"}";
	
	String s=RandomStringUtils.randomAlphabetic(5);
	String r=RandomStringUtils.randomNumeric(2);
	String t=RandomStringUtils.randomNumeric(6);
	String Param1="{\"bank\":\" "+s;
	String Param2=" \",\"bank_id\": "+r;
	String Param3=", \"bin\":\""+t;
	String Param4="\", \"card_type\": \"CREDIT\", \"country\": \"INDIA\",\"is_otp_eligible\": true,\"issuer_type\": \"MASTERCARD\",\"sub_type\": \"NA\"}";

	String Param5="\", \"card_type\": \"DEBIT\", \"country\": \"INDIA\",\"is_otp_eligible\": true,\"issuer_type\": \"MASTERCARD\",\"sub_type\": \"NA\"}";

	String Params_Singlebincard_Credit=Param1+Param2+Param3+Param4;
	String Params_Singlebincard_Debit=Param1+Param2+Param3+Param5;
	String Param6="\", \"card_type\": \"CREDIT\", \"country\": \"INDIA\",\"is_otp_eligible\": true,\"issuer_type\": \"VISA\",\"sub_type\": \"NA\"}";
	String Param7="\", \"card_type\": \"DEBIT\", \"country\": \"INDIA\",\"is_otp_eligible\": true,\"issuer_type\": \"VISA\",\"sub_type\": \"NA\"}";
	String Params_Singlebincard_Visa_Credit=Param1+Param2+Param3+Param6;
	String Params_Singlebincard_Visa_Debit=Param1+Param2+Param3+Param7;

	String Params_Reporting_UpdloadQ_FetchBy_RefundID="{\"refundIds\":[9387291,9387289],\"txnIds\":null,\"status\":null}";
	String Params_Reporting_UpdloadQ_FetchBy_TripID="{\"tripRef\":[\"Q190722430506\",\"Q200417823668\"],\"status\":\"D\"}";

	
	String Params_Reporting_Refund_Status_Post="{\"refundStatus\":\"D\",\"paymentType\":[\"GV\",\"CC\"],\"tripRef\":[\"Q190722430506\",\"Q200417823774\"]}";
	
	String paramsCSPayvalidate="[{\"payment\":{\"seq_no\":1,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":276,\r\n" + 
			"\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.73.123\",\r\n" + 
			"\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\",\"firstName\" : \"test\"},\"app_ref1\":\"Q18110926806\",\"creditshell_account_detail\":{\"id\":1},\r\n" + 
			"\"app_ref2\":\"167823462\",\"itinerary_id\":\"683a3a6bec-4e58-422a-a2c9-90707b1e5a12\",\"payment_type\":\"CS\",\"amount\":1,\"currency\":\"USD\",\"country\":\"IN\",\r\n" + 
			"\"order_info1\":\"9W/362/DEL/BOM/201811XXXXXX00\",\"order_info2\":\"Kiran Kumar\",\"source_type\":\"ACCOUNT\",\"user_id\":85721640,\"company_id\":110340,\r\n" + 
			"\"app_return_info\":{\"url\":\"https://www.cleartrip.com/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book\",\"method\":\"POST\",\"book_internal\":true,\r\n" + 
			"\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book-internal?ll=INFO\"\r\n" + 
			"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\"}}]";
	
	String paramsCSPay="";
	


	String ParamsGetPayURL_Air = "{\"itinerary_id\":\"6899c725d1-a698-4978-8fa7-2001081509423\",\"ttl\":0,\"trip_id\":46314335,\"trip_ref\":\"Q10041538791\",\"txn_id\":\"75509223\",\"currency\":\"INR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"A\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"John\",\"last_name\":\"Miller\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Ashish\",\"last_name\":\"Jain\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Rohit\",\"last_name\":\"Kumar\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Mohit\",\"last_name\":\"Verma\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Sachin\",\"last_name\":\"Reddy\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000.1,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000.02,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"cute_fee\":65,\"airline_gst\":20.5,\"paid_before\":\"100.0\",\"amend_charges\":200.5,\"ct_charges\":10,\"currency\":\"INR\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":500,\"nb\":50,\"tw\":25,\"up\":10}}}";

	String ParamsGetPayURL_Air_Coupon= "";
	

	String ParamsGetPayURL_Air_GV= "";

	String ParamsGetPayURL_Air_RP= "";

	
	
	
	String urlInit = "/paymentservice/service/otp/init";
	String urlProcess = "/paymentservice/service/otp/process";
	String urlResend = "/paymentservice/service/otp/resend";
	String urlPayValidateV3 = "/paymentservice/service/validate/v3";
	String urlPayV3 = "/paymentservice/service/pay/v3";
	String urlPayV2 = "/paymentservice/service/pay";
	String url1_FetchPayDetails = "/trips?tripID=Q1904233450";
	String urlpromoActivate = "/promoservice/v1/promogroups/Q190624286218";
	String urlpromoUsed = "/payments/wallet/promo/used?tripRef=Q190702311622";
	String urlPayFlyin = "/paymentservice/gw/v1/pay";
	String urlpayReporting= "/paymentservice/service/air/mis/detail?tripRef=Q190530193406&paymentType=CC&reqFor=refund";

	String urlEndPoint_Wallet_PromoUsed="/payments/wallet/promo/used?tripRef=Q19050680568"; 	

	String urlEndPoint_GV_Get="/payments/gv/get?cardnumber=3000331033588753&cardpin=247648&currency=INR&productType=DOMESTIC-AIR&itineraryId=681f6b756d-67de-4efc-b663-5a7ac1bd9fa1&cardCategory=DF";
	String urlEndPoint_GV_Refund="/payments/gv/refund";
	String urlEndPoint_GV_CAPTURE = "/payments/gv/capture";
	String urlPay_CreateRecord = "/paymentservice/service/createrecord?company-name=Fwd+Tourism+Pvt+Ltd&payment-type=cre&deposit-acct-id=124652522&amount=4819.5";
	String urlROR_Fetch_PayByID= "/paymentservice/payments/fetchById?id=43363994&field=paymentId";
	String urlROR_Fetch_RefundByID ="/paymentservice/service/refund/info/9373548";
	String urlROR_Fetch_ProfileList= "/paymentservice/service/profileList/info/5071366";
	String urlROR_MultiSearch_Pay= "/paymentservice/search/payments/v1?query=id:43621536,paymentType:CC";

	String endPointCardMulti = "/v1/payment/common/json?country=Palau&issuerType=MASTER";
	String endPointPromotriprefandid = "/promoservice/v1/promogroups/Q0806201815/promotions/22899";
	String endPointPromoGroupPromoRef = "/promoservice/v1/promogroups/Q1901160033/";
	String endPointPromoGroupTripRef = "/promoservice/v1/promogroups/Q191115575604/promotions";
	String endPointPromoFromPromoId = "/promoservice/v1/promogroups/Q191115575604/";
	String endPointPromoGroupsFromCreatedDate= "/promoservice/v1/promogroups?createdDate=15-11-2019";
	String endPointPromoGroupsForAUpdatedDate= "/promoservice/v1/promogroups?updatedDate=15-11-2019";
	String endPointPromoGroupsForACreatedAndUpdatedDate= "/promoservice/v1/promogroups?createdDate=15-11-2019&updatedDate=15-11-2019";
	


	String url_NavisonCC = "/paymentservice/service/mis/detail?tripRef=Q200117692102&paymentType=CC";
	String url_NavisonAir = "/paymentservice/service/air/mis/detail?tripRef=Q191226667766&paymentType=UP";

	String urlROR_Update_ProfileList= "/paymentservice/service/profileList";

	String urlROR_Update_RefundList= "/paymentservice/service/refund/update";
	String urlROR_Create_ProfileList= "/paymentservice/service/profileList";
	String urlROR_Update_Payment= "/paymentservice/payments/update";

	String urlRORUpdate_GW_Status = "/paymentservice/service/refund/updateStatusForGateway";


	String urlRORCreate_Payments ="/paymentservice/service/insert/paymentdetails";
	String urlRORCreate_Profile_List ="/paymentservice/service/profileList";
	String urlRORSearch_Profile_List ="/paymentservice/service/profileList/search";
	String urlRORCreate_Refunds="/paymentservice/service/refund";

	String urlRORRecon="/paymentservice/service/refund/recon";

	String urlROR_WalletFetch_Reads="/payments/wallet/fetch/v2?userId=13957750&currency=AED";
	String urlROR_MultiSearch_Reads ="/paymentservice/search/payments/v1?query=id:43622220,paymentType:WT";
	String urlROR_MultiSearch_TripRef_Reads= "/paymentservice/search/payments/v1?query=appRef1:Q190809461122";
	
	String urlROR_Wallet_Trnx_Reads = "/paymentservice/search/walletTransactions/v1?query=walletDetailId:5154746";
	
	String urlROR_Wallet_GetWallet_Reads = "/payments/wallet/5789285/v2/getWallet";
	String urlROR_GV_Details_Reads = "/paymentservice/search/gvDetails/v1?query=refundId:9367489";
	
	String urlROR_Refund_Reads = "/paymentservice/search/refunds/v1?query=paymentId:43410410,status:D";
	
	
	String endPointgetPay = "/paymentservice/api/getPaymentURL";

	String urlEndPoint_Wallet_RevertPromo ="/payments/wallet/promo/revert?tripRef=Q19050680568";
	String urlEndPoint_Wallet_GetDeduction = "/payments/wallet/promo/deductions?tripRef=Q190702311622";
	String urlEndPoint_Wallet_GetWallet_Trnx = "/payments/wallet/5153602/transactions?tripRef=Q1810310049&start=1&count=1000";
	String urlEndPoint_Wallet_CASHBACK_DETAILS = "/payments/wallet/promo/13957750/promotions/5732312";
	String urlEndPoint_Wallet_GETWALLET_ALL = "/payments/wallet/65179937/getWallet";
	String urlEndPoint_Wallet_GETWALLET_INR = "/payments/wallet/fetch?userId=13957750&currency=INR";
	String urlEndPoint_Wallet_CASHBACK_DETAILS14 = "";
	String urlEndpoint_GVCreate= "/payments/gv/create";
	String urlEndPoint_Wallet_CASHBACK_Wallet = "/payments/wallet/cashback?emailId=test@test123.com&currency=INR&amount=1&tripRef=Q190812462222&expiryDate =20-09-19";
	String urlEndPoint_Wallet_Create = "/payments/wallet/65176051/createWallet";

	String urlDA_Balance = "/account/61/balance";
	String urlDA_Refund= "/account/46207144/trips/Q191210603834/refund";
	String urlDA_Status = "/account/387212870";
	
	
	String urlCTPay = "https://qa2.cleartrip.com";
	
	
	String urlCTPay_Get ="/paymentservice/ct/v2/getCtPaymentUrl";
	String urlCTPay_ADD ="/paymentservice/ct/v2/addClient";
	String urlCTPay_Status ="/paymentservice/ct/v2/T2021069300/status";
	String urlCTPay_UpdateClient= "/paymentservice/ct/v2/updateClient";
	String urlCTPay_CreateURL= "paymentservice/ct/v2/getCtPaymentUrl";


	String urlEW_UserDetails= "/paymentservice/expresswayplus/details/41649008/INR";	
	String urlEW_OptIn= "/paymentservice/expresswayplus/user/opt/in/41697596/INR";	
	String urlEW_GetOutstandingAmt= "/paymentservice/expresswayplus/outstandingbalance?userId=41697596&currency=INR";
	String urlEW_Validate= "/paymentservice/service/validate";
	String urlEW_Pay= "/paymentservice/service/pay";
	String urlEW_AutoDebit= "/paymentservice/expresswayplus/autodebit?expIdList=1029";
	String urlEW_AddAmt = "/paymentservice/expresswayplus/41697596/INR/addcredit?creditAmount=10";

	String urlEW_PayV3= "/paymentservice/service/pay/v3";
	String urlEW_Refund= "/paymentservice/expressway/v2/refund?refundIds=";

	String 	urlEW_Summary = "/paymentservice/expresswayplus/paymentSummary?trip_ref=Q190521160824";
	String url_RefundNEW_EndPoint= "/paymentservice/service/refund?refundids=";

	String urlOLA_Validate = "/paymentservice/service/cashback/ola/validate";
	String urlOLA_Pay = "/paymentservice/service/cashback/ola/process";
	String urlOLA_CheckStat = "/paymentservice/service/cashback/ola/stat/";
	String urlOLA_PromoGroup = "/promoservice/v1/promogroups";

	String urlOLA_PromoStatus = "/promoservice/v1/promogroups/Q12345690012";

	String urladcb_checkbalance = "/payments/rewards/checkBalance";

	String urlreward_checkbalance = "/payments/rewards/checkBalance";

	String urlreward_Validate = "/paymentservice/service/validate/v3";

	String urlreward_PayCheckEarnPoints = "/payments/rewards/checkEarnPoints";

	String urlreward_RewardRedeem = "/payments/rewards/redeem";

	String url_reward_RewardRefund= "/payments/rewards/refund";

	String urlreward_payback_CheckMobile_Linked  = "/payments/rewards/payback/checkAccountLinked?mobileNumber=9620351338";
	String urlreward_payback_CheckCard_Linked  = "/payments/rewards/payback/checkAccountLinked?cardNumber=9401120000319303";

	String endPointCreatePromo = "/promoservice/v1/promogroups";
	String endPointUpdatePromo = "/promoservice/v1/promogroups";

	String urlreward_payback_ForgotPassword = "/payments/rewards/payback/forgotPassword";
	String urlreward_payback_pay ="/paymentservice/service/pay/v3";
	String urlreward_payback_earn ="/payments/rewards/earn";
	String urlreward_payback_reverseearn ="/payments/rewards/reverseEarn";
	String urladcb_sendOTP = "/payments/rewards/sendOtp";
	String urladcb_validate = "/paymentservice/service/validate/v3";
	String urladcb_pay = "/paymentservice/service/pay/v3";
	String url_createrefundentry = "/paymentservice/service/refund";
	String url_refundupdatestatus = "/paymentservice/service/refund/update/status";
	String url_refundpgtxnid = "/paymentservice/service/refund/update/pgTxnId";
	String url_refundgetrecord = "/paymentservice/service/refund/info/";
	String urlEndpoint_GV_GET = "http://172.17.26.11:8071/payments/gv/create";

	String url_walletGetCards ="/paymentservice/card/get/41654864"; //  41654864 41701828
	String url_walletValidateCards ="/paymentservice/card/validate?cardNumber=5123456789012346&name=test&cardTypeId=2&expiryMonth=5&expiryYear=2020&userId=41654864";
	String url_walletDeleteCards ="/paymentservice/card/delete/%s";
	String url_walletStoreCards ="/paymentservice/card/store?cardNumber=340000000000009&name=test&cardTypeId=2&expiryMonth=09&expiryYear=2020&userId=41654864";

	//String url_Binmanager = "https://qa2.cleartrip.com/binmanager/v1/payment/cards?bin=534977";

	String url_Reportingendpoint ="/paymentservice/service/air/mis/detail?tripRef=Q200109687244&paymentType=CC&reqFor=refund";
	String url_ReportingPaymentID ="/paymentservice/payments/43911126";

	String url_ReportingTS_V3 ="/trips?tripID=Q191014530470&refundRequired=true&historyRequired=true&paymentsRequired=true&apiVersion=V3";
	String url_ReportingTS_Archived_V3_False ="/paymentservice/payments/42752096?isArchived=false";
	String url_ReportingTS_Archived_V3_True ="/paymentservice/payments/42752096?isArchived=true";

	String urlRefundsTrip="/paymentservice/refund/data/fetch";
	String urlRefundsTripRef="/paymentservice/refund/data/fetchByTripRef";
	
	String url_ReportingPaginate = "/paymentservice/paginate/refunds?pageNo=1385&status=P&sort=desc";
	String url_ReportingPaginate_PayType = "/paymentservice/paginate/refunds?pageNo=1&status=D&sort=desc&paymentType=NB";
	
	String url_ReportingRefundQ_Count_D = "/paymentservice/service/refund/count/refunds?refundStatus=D";
	String url_ReportingRefundQ_Count_D_DA = "/paymentservice/service/refund/count/refunds?refundStatus=D&daStatus=S";
	String url_ReportingRefundQ_Count_S_Txns = "/paymentservice/service/refund/count/refunds?refundStatus=S&txnType=CAPT,QURY";
	String url_ReportingRefundQ_Count_S_Txns_Gw = "/paymentservice/service/refund/count/refunds?refundStatus=S&txnType=CAPT,QURY&gatewayId=10";
	String url_ReportingRefundQ_Count_S_PayType = "/paymentservice/service/refund/count/refunds?refundStatus=s&paymentType=CC,DC";
	String url_ReportingRefundQ_Count_S_PayType_GW = "/paymentservice/service/refund/count/refunds?refundStatus=D&paymentType=CC,DC&gatewayId=10";
	String url_ReportingRefundQ_Count_D_CashCard = "/paymentservice/service/refund/count/refunds?refundStatus=d&cashCardStatus=s&cashCardId=1&paymentType=KC";
	String url_ReportingRefundQ_Count_P_PayType = "/paymentservice/service/refund/count/refunds?refundStatus=p&paymentType=NB,DC,CC&paymentStatus=s";	
	String url_ReportingRefundUploadQ_fetch_RefundID = "/paymentservice/refund/data/fetch";	
	String url_ReportingRefundUploadQ_fetch_TripID = "/paymentservice/refund/data/fetchByTripRef";	
	String url_ReportingRefund_Count_ID = "/paymentservice/service/refund/count/refunds";	
	
	

	String url_Singlebincard="/v1/payment/card";



	/*String Prod_Url_PaymentService="http://172.21.65.21:8070";
	//String Prod_Url_Rewards1="http://rewardsservice.cltp.com:9001";
	String Prod_Url_Rewards="http://172.21.3.122:9080";
	String Prod_Url_Wallet="http://172.21.65.21:8071";
	String Prod_Url_Promo="http://172.21.48.21:7999";*/
	
	String Prod_Url_PaymentService="http://paymentservice.cltp.com:9001";
	//String Prod_Url_Rewards1="http://rewardsservice.cltp.com:9001";
	//String Prod_Url_Rewards="http://10.163.15.235:9001";
	String Prod_Url_Rewards="http://rewardsservice.cltp.com:9001";
	String Prod_Url_Wallet="http://wallet-service.cltp.com:9001";
	String Prod_Url_Promo="http://promoservice.cltp.com:9001";
	

	String Prod_Url_EndPoint_Get_TP_Wallets="/paymentservice/service/thirdpartywallets";

	String Prod_Url_EndPoint_Rewards_PayBack_Mobile="/payments/rewards/payback/checkAccountLinked?mobileNumber=9620351338";
	String Prod_Url_ActiveNB= "/paymentservice/service/netbankingbanks?status=active";
	String Prod_Url_PaymentStatus= "/paymentservice/service/status?paymentIds=140145386";
	String Prod_Url_ActiveCardType= "/paymentservice/service/cardtypes?status=active";
	String Prod_Url_EndPoint_Rewards_ADCB_Balance="/payments/rewards/checkBalance";
	String Prod_Url_EndPoint_Wallet_getWallet="/payments/wallet/74728676/getWallet";
	String Prod_Url_EndPoint_Promo_Group = "/promoservice/v1/promogroups/190313426926";

	String Prod_Params_Rewards_ADCB_Balance="{\"rewardsType\":\"ADCB\",\"rewardsRequestType\":\"BALANCE_CHECK\",\"trackId\":\"CL1212S00323\",\"cardNumber\":\"5420187505091559\",\"amount\":1,\"currency\":\"AED\",\"params\":{\"customerName\":\"test\",\"expiryDate\":\"0224\"}}";



	String URL_SmilesDev= "https://test-developerhub.etisalat.ae:9443";
	String URL_SmilesDev_Parm = "/test-organization/smiles/confidential/oauth2/token";
	String Param_Smiles_Auth = "grant_type=client_credentials&scope=apioauth";

	String urlCS_Pay="/paymentservice/service/pay/v3";
	String urlCS_Validate="/paymentservice/service/validate/v3";


	String MySQL_URL = "jdbc:mysql://172.17.4.15:3306/payment";
	String MySQL_User = "payment";
	String MySQL_Password = "P@yment@123";
	
	//-------------------------------------------------------------------PayUI payload-------------------------------------------------------------------------//

	String PaymentUI_Trains = "{\"train_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Ms\",\"first_name\":\"Test\",\"last_name\":\"Test\"}],\"itinerary_details\":[{\"from_station_code\":\"SBC\",\"to_station_code\":\"MYS\",\"from_station_name\":\"KSR BENGALURU\",\"to_station_name\":\"MYSURU JN\",\"train_name\":\"BSB MYS EXP\",\"train_number\":\"16230\",\"departure_time\":\"2019-11-12T00:15:00\",\"arrival_time\":\"2019-11-12T02:45:00\",\"booking_class\":\"AC 2 Tier(2A)\",\"updated_availability\":\"AVAILABLE-0119\",\"quota\":\"General\",\"seatStatus\":true}],\"pricing_details\":[{\"other_railway_charges\":119.4,\"agent_service_charge\":40,\"total\":7775.4,\"insuranceCharge\":0,\"currency\":\"INR\",\"pax_pay_info\":[{\"base_fare\":6616.0,\"pax_count\":1,\"pax_type\":\"ADULT\"}]}],\"transaction_fee_details\":{\"CC\":{\"DEFAULT\":1.8},\"DC\":{\"DEFAULT\":1.0},\"NB\":{\"DEFAULT\":1.35,\"1\":1.1,\"2\":1.2,\"23\":1.1,\"3\":1.1},\"KC\":{\"DEFAULT\":0.0},\"DA\":{\"DEFAULT\":0.0},\"TW\":{\"DEFAULT\":1.0},\"UP\":{\"DEFAULT\":1.0}}},\"itinerary_id\":\"f25db800de1e0137664316217d236675\",\"ttl\":3600,\"trip_id\":45134538,\"app_ref1\":\"";

	String PaymentUI_Trains1 = "\",\"app_ref2\":74282510,\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":1212121212,\"landline\":1212121212,\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"payment\",\"username\":\"cltppayment@gmail.com\"},\"product_type\":\"TRAIN\",\"currency\":\"INR\",\"order_info1\":\"16230/SBC/MYS/2019111200:15:00\",\"order_info2\":\"Test Test\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"64891349\",\"email_id\":\"cltppayment@gmail.com\",\"d_plus_x_in_hours\":273,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/trains/itinerary/f25db800de1e0137664316217d236675/process_payment\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://trains-book-nget.cltp.com:9001/r3/trains/itinerary/f25db800de1e0137664316217d236675/book_internal\",\"params\":null},\"payment_category\":\"B\"}";

	String params_PayUI_Air1="{\"itinerary_id\":\"NI685a33347e-464c-4a27-86c9-200710101330\",\"ttl\":0,\"trip_id\":46314335,\"trip_ref\":\"";
	String params_PayUI_Air2="\",\"txn_id\":\"75509223\",\"currency\":\"INR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"John\",\"last_name\":\"Miller\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Ashish\",\"last_name\":\"Jain\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Rohit\",\"last_name\":\"Kumar\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Mohit\",\"last_name\":\"Verma\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Sachin\",\"last_name\":\"Reddy\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000.1,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000.02,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"cute_fee\":65,\"airline_gst\":20.5,\"paid_before\":\"100.0\",\"amend_charges\":200.5,\"ct_charges\":10,\"currency\":\"INR\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":500,\"nb\":50,\"tw\":25,\"up\":10,\"wt\":100}}}";
	String params_PayUI_Air_AE="\",\"txn_id\":\"75509223\",\"currency\":\"AED\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"AE\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"John\",\"last_name\":\"Miller\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Ashish\",\"last_name\":\"Jain\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Rohit\",\"last_name\":\"Kumar\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Mohit\",\"last_name\":\"Verma\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Sachin\",\"last_name\":\"Reddy\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000.1,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000.02,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"cute_fee\":65,\"airline_gst\":20.5,\"paid_before\":\"100.0\",\"amend_charges\":200.5,\"ct_charges\":10,\"currency\":\"AED\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":500,\"nb\":50,\"tw\":25,\"up\":10,\"wt\":100}}}";
	String params_PayUI_Air_AE_AR="\",\"txn_id\":\"75509223\",\"currency\":\"AED\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"AE\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"AR\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"John\",\"last_name\":\"Miller\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000.1,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000.02,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"currency\":\"AED\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":500,\"nb\":50,\"tw\":25,\"up\":10,\"wt\":100}}}";
	String params_PayUI_Air_BH="\",\"txn_id\":\"75509223\",\"currency\":\"BHD\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"BH\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"John\",\"last_name\":\"Miller\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Ashish\",\"last_name\":\"Jain\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Rohit\",\"last_name\":\"Kumar\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Mohit\",\"last_name\":\"Verma\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Sachin\",\"last_name\":\"Reddy\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000.1,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000.02,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"cute_fee\":65,\"airline_gst\":20.5,\"paid_before\":\"100.0\",\"amend_charges\":200.5,\"ct_charges\":10,\"currency\":\"BHD\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":500,\"nb\":50,\"tw\":25,\"up\":10,\"wt\":100}}}";
	String params_PayUI_Air_KW="\",\"txn_id\":\"75509223\",\"currency\":\"KWD\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"KW\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"John\",\"last_name\":\"Miller\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Ashish\",\"last_name\":\"Jain\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Rohit\",\"last_name\":\"Kumar\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Mohit\",\"last_name\":\"Verma\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Sachin\",\"last_name\":\"Reddy\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000.1,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000.02,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"cute_fee\":65,\"airline_gst\":20.5,\"paid_before\":\"100.0\",\"amend_charges\":200.5,\"ct_charges\":10,\"currency\":\"KWD\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":500,\"nb\":50,\"tw\":25,\"up\":10,\"wt\":100}}}";
	String params_PayUI_Air_OM="\",\"txn_id\":\"75509223\",\"currency\":\"OMR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"OM\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"John\",\"last_name\":\"Miller\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Ashish\",\"last_name\":\"Jain\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Rohit\",\"last_name\":\"Kumar\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Mohit\",\"last_name\":\"Verma\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Sachin\",\"last_name\":\"Reddy\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000.1,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000.02,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"cute_fee\":65,\"airline_gst\":20.5,\"paid_before\":\"100.0\",\"amend_charges\":200.5,\"ct_charges\":10,\"currency\":\"OMR\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":500,\"nb\":50,\"tw\":25,\"up\":10,\"wt\":100}}}";
	String params_PayUI_Air_QA="\",\"txn_id\":\"75509223\",\"currency\":\"QAR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"QA\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"John\",\"last_name\":\"Miller\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Ashish\",\"last_name\":\"Jain\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Rohit\",\"last_name\":\"Kumar\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Mohit\",\"last_name\":\"Verma\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Sachin\",\"last_name\":\"Reddy\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000.1,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000.02,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"cute_fee\":65,\"airline_gst\":20.5,\"paid_before\":\"100.0\",\"amend_charges\":200.5,\"ct_charges\":10,\"currency\":\"QAR\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":500,\"nb\":50,\"tw\":25,\"up\":10,\"wt\":100}}}";
	String params_PayUI_Air_SA="\",\"txn_id\":\"75509223\",\"currency\":\"SAR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"SA\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"John\",\"last_name\":\"Miller\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Ashish\",\"last_name\":\"Jain\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Rohit\",\"last_name\":\"Kumar\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Mohit\",\"last_name\":\"Verma\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Sachin\",\"last_name\":\"Reddy\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000.1,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000.02,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"cute_fee\":65,\"airline_gst\":20.5,\"paid_before\":\"100.0\",\"amend_charges\":200.5,\"ct_charges\":10,\"currency\":\"SAR\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":500,\"nb\":50,\"tw\":25,\"up\":10,\"wt\":100}}}";
	String params_PayUI_Air_US="\",\"txn_id\":\"75509223\",\"currency\":\"USD\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"US\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"John\",\"last_name\":\"Miller\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Ashish\",\"last_name\":\"Jain\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Rohit\",\"last_name\":\"Kumar\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Mohit\",\"last_name\":\"Verma\"},{\"seq_no\":2,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Sachin\",\"last_name\":\"Reddy\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000.1,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000.02,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"cute_fee\":65,\"airline_gst\":20.5,\"paid_before\":\"100.0\",\"amend_charges\":200.5,\"ct_charges\":10,\"currency\":\"USD\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":500,\"nb\":50,\"tw\":25,\"up\":10,\"wt\":100}}}";
	
	
	String params_PayUI_Air_Coupon1 ="{\"itinerary_id\":\"NI685a33347e-464c-4a27-86c9-200710101330\",\"ttl\":0,\"trip_id\":46314335,\"trip_ref\":\"";
	String params_PayUI_Air_Coupon2 ="\",\"txn_id\":\"75509223\",\"currency\":\"INR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"121212212121\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"cltp\",\"last_name\":\"payment\"},\"coupon_details\":{\"coupon_code\":\"DOMOW\",\"coupon_type\":\"Instant\",\"amount\":150,\"message\":\"Something\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"cltp\",\"last_name\":\"pay\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":200,\"dc\":100,\"nb\":50,\"tw\":25,\"up\":10}}}";
	String params_PayUI_Air_GV1 ="{\"itinerary_id\":\"NI685a33347e-464c-4a27-86c9-200710101330\",\"ttl\":3600,\"trip_id\":46471954,\"ui_language\":\"EN\",\"client\":\"Cleartrip\",\"trip_ref\":\"";
	String params_PayUI_Air_GV2 ="\",\"txn_id\":\"75706222\",\"currency\":\"INR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"company_id\":101,\"payment_category\":\"B\",\"gift_voucher_details\":[{\"applicableAmount\":1.0,\"card_number\":\"3000331039428010\",\"card_pin\":\"639789\",\"card_category\":\"\"}],\"currency_conversion_details\":{\"displayCurrency\":\"Rs.\",\"conversionFactor\":1.0},\"customer_detail\":{\"user_id\":41701076,\"mobile\":\"121212112112\",\"landline\":\"\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Kiran\",\"last_name\":\"Kumar\",\"title\":\"Mr\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI684e3904ef-85c0-44b4-b0a6-200708134324/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI684e3904ef-85c0-44b4-b0a6-200708134324/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Kiran\",\"last_name\":\"Kumar\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":1,\"airline_name\":\"IndiGo\",\"airline_code\":\"6E\",\"flight_number\":6212,\"departure_date\":\"2020-07-29T11:00:00\",\"arrival_date\":\"2020-07-29T12:05:00\",\"departure_time\":\"11:00\",\"arrival_time\":\"12:05\",\"duration\":\"3900\",\"stops\":0,\"departure_code\":\"BLR\",\"arrival_code\":\"MAA\",\"departure_airport_name\":\"Bengaluru International Airport\",\"arrival_airport_name\":\"Chennai Airport\",\"total_time\":\"3900\",\"departure_terminal\":\"1\",\"arrival_terminal\":\"1\",\"departure_name\":\"Bangalore\",\"arrival_name\":\"Chennai\"}]}],\"flights_pricing_details\":{\"air_dev_fee\":0.0,\"airline_misc\":0.0,\"cute_fee\":0.0,\"airline_gst\":536.0,\"amend_charges\":0.0,\"ct_charges\":0.0,\"other_charges\":0.0,\"service_fee\":0.0,\"pax_pay_info\":[{\"base_fare\":1020.0,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":0.0,\"psgr_service_fee\":0.0,\"currency\":\"INR\",\"total\":500},\"convenience_fee_details\":{\"cc\":150.0,\"dc\":150.0,\"nb\":150.0,\"kc\":0.0,\"up\":150.0,\"da\":0.0,\"tw\":150.0,\"rp\":0.0,\"ap\":0.0,\"wt\":150.0,\"flat_fee\":true}},\"domain\":\"IN\"}";
	String params_PayUI_Air_GV2_Full ="\",\"txn_id\":\"75706222\",\"currency\":\"INR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"company_id\":101,\"payment_category\":\"B\",\"gift_voucher_details\":[{\"applicableAmount\":200.0,\"card_number\":\"3000331032473351\",\"card_pin\":\"230850\",\"card_category\":\"\",\"total_balance\":\"50000\"}],\"currency_conversion_details\":{\"displayCurrency\":\"Rs.\",\"conversionFactor\":1.0},\"customer_detail\":{\"user_id\":41701076,\"mobile\":\"9036117775\",\"landline\":\"\",\"email\":\"Kiran.suresh@cleartrip.com\",\"first_name\":\"Kiran\",\"last_name\":\"Kumar\",\"title\":\"Mr\"},\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI684e3904ef-85c0-44b4-b0a6-200708134324/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI684e3904ef-85c0-44b4-b0a6-200708134324/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Kiran\",\"last_name\":\"Kumar\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":1,\"airline_name\":\"IndiGo\",\"airline_code\":\"6E\",\"flight_number\":6212,\"departure_date\":\"2020-07-29T11:00:00\",\"arrival_date\":\"2020-07-29T12:05:00\",\"departure_time\":\"11:00\",\"arrival_time\":\"12:05\",\"duration\":\"3900\",\"stops\":0,\"departure_code\":\"BLR\",\"arrival_code\":\"MAA\",\"departure_airport_name\":\"Bengaluru International Airport\",\"arrival_airport_name\":\"Chennai Airport\",\"total_time\":\"3900\",\"departure_terminal\":\"1\",\"arrival_terminal\":\"1\",\"departure_name\":\"Bangalore\",\"arrival_name\":\"Chennai\"}]}],\"flights_pricing_details\":{\"air_dev_fee\":0.0,\"airline_misc\":0.0,\"cute_fee\":0.0,\"airline_gst\":500.0,\"amend_charges\":0.0,\"ct_charges\":0.0,\"other_charges\":0.0,\"service_fee\":0.0,\"pax_pay_info\":[{\"base_fare\":1000.0,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":0.0,\"psgr_service_fee\":0.0,\"currency\":\"INR\",\"total\":0.0},\"convenience_fee_details\":{\"cc\":150.0,\"dc\":150.0,\"nb\":150.0,\"kc\":0.0,\"up\":150.0,\"da\":0.0,\"tw\":150.0,\"rp\":100.0,\"ap\":0.0,\"wt\":150.0,\"gv\":100.0,\"flat_fee\":true}},\"domain\":\"IN\"}";
	String params_PayUI_Air_RP1 ="{\"itinerary_id\":\"NI685a33347e-464c-4a27-86c9-200710101330\",\"ttl\":0,\"trip_id\":46314335,\"trip_ref\":\"";
	String params_PayUI_Air_RP2 ="\",\"txn_id\":\"75509223\",\"currency\":\"INR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"1876970\",\"company_id\":110340,\"payment_category\":\"B\",\"ui_language\":\"EN\",\"trip_title\":\"AMD -> BLR\",\"back_button_url\":\"/accounts\",\"customer_detail\":{\"user_id\":1876970,\"mobile\":\"911212121212\",\"email\":\"cltppayment@gmail.com\",\"first_name\":\"rajesh\",\"last_name\":\"vyshnava\"},\"reward_point_details\":[{\"reward_points_detail\":{\"uid\":\"CLRY2KWZ7DEHER2JCMET\",\"params\":{\"mobile\":\"9986696785\",\"pin\":\"2126\"}},\"rewards_type\":\"PAYBACK\",\"amount\":1}],\"app_return_info\":{\"url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\",\"method\":\"POST\",\"params\":null,\"book_internal\":true,\"book_internal_url\":\"http://onetin.cltp.com:9001/itin/internal/itinerary/NI6826d4897a-b5af-47a5-a72e-200511165846/book/internal\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"cltp\",\"last_name\":\"pay\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-15T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"DEL\",\"arrival_code\":\"BOM\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"},{\"serial_number\":0,\"airline_name\":\"SG\",\"airline_code\":\"SG\",\"flight_number\":8169,\"departure_date\":\"2020-02-15T19:45:00\",\"arrival_date\":\"2020-02-16T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":1,\"departure_code\":\"BOM\",\"arrival_code\":\"AMD\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]},{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"AI\",\"airline_code\":\"AI\",\"flight_number\":8169,\"departure_date\":\"2020-02-20T19:45:00\",\"arrival_date\":\"2020-02-20T22:05:00\",\"departure_time\":\"07:45\",\"arrival_time\":\"10:05\",\"duration\":\"8400\",\"stops\":0,\"departure_code\":\"AMD\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Indira Gandhi Airport\",\"arrival_airport_name\":\"Chatrapati Shivaji Airport\",\"departure_name\":\"New Delhi\",\"arrival_name\":\"Mumbai\",\"date\":\"2020-02-15T19:45:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":3000,\"pax_count\":1,\"pax_type\":\"ADULT\"},{\"base_fare\":3000,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":100,\"psgr_service_fee\":91,\"air_dev_fee\":0,\"airline_misc\":177,\"cute_fee\":65,\"airline_gst\":20.5,\"paid_before\":\"100.0\",\"amend_charges\":200.5,\"ct_charges\":10,\"currency\":\"INR\",\"other_charges\":1016,\"service_fee\":1012,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":10},\"dc\":{\"DEFAULT\":0},\"nb\":{\"DEFAULT\":0},\"kc\":{\"DEFAULT\":0},\"da\":{\"DEFAULT\":0},\"tw\":{\"DEFAULT\":0},\"up\":{\"DEFAULT\":0},\"wt\":{\"DEFAULT\":0}},\"convenience_fee_details\":{\"cc\":5,\"dc\":5,\"nb\":10,\"tw\":5,\"up\":10}}}";

	// //Dont use// String param_Air_Amendment_API  = "http://bqapi.cleartripcorp.com/bqAPI/file?name=PAYMENT_STATUS-Amend-10.56.54.69-1592076267056-36-req.gz&iId=680e1794b8-87a7-40b9-a8d6-200614005232&date=2020-06-14&tripId=Q200531806440";
	
	String param_Air_Amendment_API1  = "{\"itinerary_id\":\"NI685a33347e-464c-4a27-86c9-200710101330\",\"ttl\":0,\"trip_id\":140471948,\"ui_language\":\"EN\",\"client\":\"Cleartrip\",\"trip_ref\":\"";
	String param_Air_Amendment_API2 = "\",\"txn_id\":\"227577296\",\"currency\":\"INR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"MOBILE\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"77925412\",\"company_id\":110340,\"back_button_url\":\"/amendments/itinerary\",\"trip_title\":\"Bangalore ? New Delhi\",\"payment_category\":\"A\",\"customer_detail\":{\"user_id\":7792512,\"mobile\":\"9986696785\",\"email\":\"shubham.bansal@cleartrip.com\",\"first_name\":\"CLTP\",\"last_name\":\"Payment\"},\"app_return_info\":{\"url\":\"http://amendtool.gcp-cltp.com/amend/book/68edc4657c-90e5-44bc-8eaf-200612123123\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://amendtool.gcp-cltp.com/amend/book/68edc4657c-90e5-44bc-8eaf-200612123123\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Cltp\",\"last_name\":\"Payment\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"UK\",\"airline_code\":\"UK\",\"flight_number\":816,\"departure_date\":\"2020-06-22T12:15:00\",\"arrival_date\":\"2020-06-22T15:05:00\",\"departure_time\":\"12:15\",\"arrival_time\":\"15:05\",\"duration\":\"10200\",\"stops\":0,\"departure_code\":\"BLR\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Kempegowda International Airport\",\"arrival_airport_name\":\"Indira Gandhi Airport\",\"departure_name\":\"Bangalore\",\"arrival_name\":\"New Delhi\",\"date\":\"2020-06-22T12:15:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":4970.0,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":0.0,\"psgr_service_fee\":0.0,\"air_dev_fee\":0.0,\"airline_misc\":177.0,\"cute_fee\":0.0,\"airline_gst\":255.0,\"paid_before\":\"5223.0\",\"amend_charges\":1350.0,\"ct_charges\":0.0,\"currency\":\"INR\",\"other_charges\":348.0,\"service_fee\":0.0,\"total\":500},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":0.0},\"dc\":{\"DEFAULT\":0.0},\"nb\":{\"DEFAULT\":0.0},\"kc\":{\"DEFAULT\":0.0},\"da\":{\"DEFAULT\":0.0},\"tw\":{\"DEFAULT\":0.0},\"up\":{\"DEFAULT\":0.0}}}}";
	
	
	//"140471948,\"ui_language\":\"EN\",\"client\":\"Cleartrip\",\"trip_ref\":\"200531806440\",\"txn_id\":\"227577296\",\"currency\":\"INR\",\"product_type\":\"DOMESTIC-AIR\",\"source_type\":\"MOBILE\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"77925412\",\"company_id\":110340,\"back_button_url\":\"/amendments/itinerary\",\"trip_title\":\"Bangalore ? New Delhi\",\"payment_category\":\"A\",\"customer_detail\":{\"user_id\":77925412,\"mobile\":\"9971867476\",\"email\":\"shubham.bansal@cleartrip.com\",\"first_name\":\"Shubham\",\"last_name\":\"Bansal\"},\"app_return_info\":{\"url\":\"http://amendtool.gcp-cltp.com/amend/book/680e1794b8-87a7-40b9-a8d6-200614005232\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://amendtool.gcp-cltp.com/amend/book/680e1794b8-87a7-40b9-a8d6-200614005232\"},\"air_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Mr\",\"first_name\":\"Shubham\",\"last_name\":\"Bansal\"}],\"itinerary_details\":[{\"segment_details\":[{\"serial_number\":0,\"airline_name\":\"UK\",\"airline_code\":\"UK\",\"flight_number\":816,\"departure_date\":\"2020-06-22T12:15:00\",\"arrival_date\":\"2020-06-22T15:05:00\",\"departure_time\":\"12:15\",\"arrival_time\":\"15:05\",\"duration\":\"10200\",\"stops\":0,\"departure_code\":\"BLR\",\"arrival_code\":\"DEL\",\"departure_airport_name\":\"Kempegowda International Airport\",\"arrival_airport_name\":\"Indira Gandhi Airport\",\"departure_name\":\"Bangalore\",\"arrival_name\":\"New Delhi\",\"date\":\"2020-06-22T12:15:00\"}]}],\"flights_pricing_details\":{\"pax_pay_info\":[{\"base_fare\":4970.0,\"pax_count\":1,\"pax_type\":\"ADULT\"}],\"discount\":0.0,\"psgr_service_fee\":0.0,\"air_dev_fee\":0.0,\"airline_misc\":177.0,\"cute_fee\":0.0,\"airline_gst\":255.0,\"paid_before\":\"5223.0\",\"amend_charges\":1350.0,\"ct_charges\":0.0,\"currency\":\"INR\",\"other_charges\":348.0,\"service_fee\":0.0,\"total\":1877},\"transaction_fee_details\":{\"cc\":{\"DEFAULT\":0.0},\"dc\":{\"DEFAULT\":0.0},\"nb\":{\"DEFAULT\":0.0},\"kc\":{\"DEFAULT\":0.0},\"da\":{\"DEFAULT\":0.0},\"tw\":{\"DEFAULT\":0.0},\"up\":{\"DEFAULT\":0.0}}}}"
	
	

	public String tripRef = null;

	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;


	protected String qaurl = "https://qa2.cleartrip.com";
	protected String qaurlae = "https://www.cleartrip.ae";
	protected String qaurlbh = "https://bh.cleartrip.com";
	protected String qaurlkw = "https://kw.cleartrip.com";
	protected String qaurlom = "https://om.cleartrip.com";
	protected String qaurlsa = "https://qa2.cleartrip.sa";
	protected String qaurlus = "https://me.cleartrip.com";
	protected String qaurlqa = "https://qa.cleartrip.com";
	
	List<Integer> payment_id = new ArrayList<Integer>();
	List<String> trk_id = new ArrayList<String>();
	List<String> trip_refPromo = new ArrayList<String>();

	public HashMap<String, Object> headersForms(){		
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		
		return headers;
	}
	
	public HashMap<String, Object> headersFormspay() throws Exception{		
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		
		headers.put("pay-auth", paygetAuth());
		return headers;
	}
	
	public HashMap<String, Object> headersFormspay_FullGV() throws Exception{		
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		
		headers.put("pay-auth", paygetAuth_FullGV());
		return headers;
	}


	public HashMap<String, Object> headersPayAuth(){		
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return headers;
	}
	public HashMap<String,Object> headerFormsPromoService(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("x-action", "ACTIVATE");
		return headers;
	}

	public HashMap<String, Object> headersFormsSmiles(){		
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Authorization", "Basic OGI3YTJmN2NkNDQwOTg5NTk0Yzk1Y2JkZjczOThiZWM6MDNjYzJjNzYwNTg5OTM5NDkwN2FkMWUyODJmMmFhNzU=");

		headers.put("Content-Type", "application/x-www-form-urlencoded");
		return headers;
	}


	public HashMap<String, Object> headersForms_wallet(){		
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Cookie", "ct-auth = JHuf%2BCHAT9dJA6iy3yljOA%2BJEhxSeSS6bMSVjXXjBRMEKMkFZO7FDVmoCxuK2ro9JWNRt682KuqiFuXV2whf5HObWzaif65XOuM37YyrPLX%2BaiBNwnkmSmMem2WbpgBvzIOK8AA0ICwzYBAszCVlK7Wt4vbavb4Rc9plZba2GjgZcQBlPkvWs0YEAP%2B2OXYcwxeut2x6p5X1i%2BvPjnOjN5c7bmkG62x2TeRUPL%2BcTfQ7ZtPZvYpaAQ3oRcyhXrPhCUmcbRdKxTvjY08FAtXwySBwZnpRB%2Fr6Tdc4tErNeglqJTknezoRpPhKBzjfu1gtd8ro1XIKetU3yLt3kXt9RMitRVpAKIqLA%2Bkwfued9ARpSFWPHNzcb5k%2BZjusDdQuULECGHAP00B8LK7MltV20wodXXSeczhpDpmjAwJJBWF2kulqJ%2FaQ5Oi%2BUMmQ92BEqwQ0%2FZ1GGS%2FCsh4%2Flet6bIQmTJelK7OdeSLJlOhcpan1uHwoj5PmK6CrwQl4iGe6N0IBzS8MCjon9SGgFW8uc%2B97NUe06yWRwDtxLHRrqe%2B8UfmNCT%2B9HIFFr7urccGIf09n7B1MBN2D%2F3uBsb4bR8YYXRDmXYUVm%2FXms5YZHzl1u0HRpkoj3SJCZNksleaf4%2FRMFvDNJjcW0zkxFMlzew2BiwCGms1A%2Bpuib7AbTmi3KrivJofipyqlrlOmpIFB86BH3WBORcuhJKlOSbZ88Sjk7Axf%2Bj5hS4tsbVfQlBNW%2Fn1gi1O6phXNn%2BoD%2B1RUQ2HxBmGgursB0XalVJfK1g%3D%3D");

		return headers;
	}	

	public HashMap<String, Object> headersForms_GVCreate_10(){		
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("checksum", "5251ffd74806413354865c7b718d3d5cfed9b2d41ef3035ae0da51f052f4d1b2");
		return headers;
	}

	public HashMap<String, Object> headersForms_GVCreate_5000(){		
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("checksum", "68b14cc68ab1b618f219c13c63618f685fa50f31cc8072dc294bdc02acf94c36");
		return headers;
	}

	public HashMap<String, Object> headersForms_GVCreate_100000(){		
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("checksum", "53a8a87f3707c1da57f5c1bf9d5e3ceb25ae5f5866fea66703173e530fcb9bce");
		return headers;
	}

	public HashMap<String, Object> addCookies(){		
		HashMap<String, Object> setCookies = new HashMap<>();
		setCookies.put("userid", "prakhar.chatterjee%40cleartrip.com%7C%7C%7C%7C65176819");
		setCookies.put("usermisc", "SIGNED_IN%7C");
		return setCookies;
	}
	public String returnParamForBin_CC(){
		String t=RandomStringUtils.randomNumeric(6);
		String Param1="{\"bank\":\" "+s;
		String Param2=" \",\"bank_id\": "+r;
		String Param3=", \"bin\":\""+t;
		String Param4="\", \"card_type\": \"CREDIT\", \"country\": \"INDIA\",\"is_otp_eligible\": true,\"issuer_type\": \"MASTERCARD\",\"sub_type\": \"NA\"}";

		String Param5="\", \"card_type\": \"DEBIT\", \"country\": \"INDIA\",\"is_otp_eligible\": true,\"issuer_type\": \"MASTERCARD\",\"sub_type\": \"NA\"}";

		String Params_Singlebincard_Credit=Param1+Param2+Param3+Param4;

		return Params_Singlebincard_Credit;
	}
	
	
	public Response payGet(String payType, String payType1) {
		RestAssured.baseURI =promoURL;
		String url = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request;	
		if(payType.equalsIgnoreCase("PromoActive")) {
			url= urlpromoActivate;
		}

		else if(payType.equalsIgnoreCase("PromoUsed")) {

			Reporter.log(urlReportingTS);	
			RestAssured.baseURI =urlPromo_Used;
			url= urlpromoUsed;
		}
		else if(payType.equalsIgnoreCase("FetchPayDetails")) {
			RestAssured.baseURI =urlReportingTS;
			url= url1_FetchPayDetails;
		}
		else if(payType.equalsIgnoreCase("CreateRecord")) {
			RestAssured.baseURI =urlPay;
			Reporter.log(urlPay);			
			url = urlPay_CreateRecord;
		}

		Reporter.log(url);	

		request = RestAssured.given().
				when().
				log().all().
				headers(headers).
				get(url);
		return request;			
	}


	public Response payGet1(String payType, String payType1) {
		RestAssured.baseURI =urlPay;
		String url = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request;	
		if(payType.equalsIgnoreCase("RORFetch_Pay_BY_ID")) {
			url= urlROR_Fetch_PayByID;
		}
		else if(payType.equalsIgnoreCase("RORFetch_Refund_BY_ID")) {
			url= urlROR_Fetch_RefundByID;
		}
		else if(payType.equalsIgnoreCase("RORFetch_Profile_List")) {
			url= urlROR_Fetch_ProfileList;
		}
		else if(payType.equalsIgnoreCase("NavisonCC")) {
			RestAssured.baseURI =urlReporting;
			url= url_NavisonCC;
			Reporter.log(urlReporting+url);
		}
		else if(payType.equalsIgnoreCase("NavisonAir")) {
			RestAssured.baseURI =urlReporting;
			url= url_NavisonAir;
			Reporter.log(urlReporting+url);
		}
		else if(payType.equalsIgnoreCase("ROR_MultiSearch")) {
			RestAssured.baseURI =urlReporting;
			url= urlROR_MultiSearch_Pay;
			Reporter.log(urlReporting+url);
		}	
		
		

		else if(payType.equalsIgnoreCase("ROR_WalletFetch")) {
			RestAssured.baseURI =urlWallet;
			url= urlROR_MultiSearch_TripRef_Reads;
			Reporter.log(urlReporting+url);
		}


		else if(payType.equalsIgnoreCase("ROR_MultiSearch_TripRef_Reads")) {
			RestAssured.baseURI =urlReporting;
			url= urlROR_MultiSearch_TripRef_Reads;
			Reporter.log(urlReporting+url);
		}
		
		else if(payType.equalsIgnoreCase("ROR_WalletGetV2_Reads")) {
			RestAssured.baseURI =urlPay;
			Reporter.log(urlWallet);
			url= urlROR_WalletFetch_Reads;
			Reporter.log(urlReporting+url);
		}
		else if(payType.equalsIgnoreCase("ROR_MultiSearch_Reads")) {
			RestAssured.baseURI =urlReporting;
			url= urlROR_MultiSearch_Reads;
			Reporter.log(urlReporting+url);
		}
		else if(payType.equalsIgnoreCase("ROR_WalletTrnx_Reads")) {
			RestAssured.baseURI =urlReporting;
			url= urlROR_Wallet_Trnx_Reads;
			Reporter.log(urlReporting+url);
		}
		else if(payType.equalsIgnoreCase("ROR_WalletGet_Reads")) {
			RestAssured.baseURI =urlWallet;
			url= urlROR_Wallet_GetWallet_Reads;
			Reporter.log(urlReporting+url);
		}
		else if(payType.equalsIgnoreCase("ROR_GV_Details")) {
			RestAssured.baseURI =urlPay;
			url= urlROR_GV_Details_Reads;
			Reporter.log(urlPay+url);
			
		}

		else if(payType.equalsIgnoreCase("ROR_Refund_Details")) {
			RestAssured.baseURI =urlReporting;			
			url= urlROR_Refund_Reads;
			Reporter.log(urlPay=url);
		}
		Reporter.log(url);
		//System.out.println(url);
		request = RestAssured.get(url);
		return request;			
	}

	public Response payPut(String payType, String payType1) {
		RestAssured.baseURI =urlPay;
		String url = null;
		String params = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request;	
		if(payType.equalsIgnoreCase("RORUpdate_Profile_List")) {
			Random rand = new Random(); 
			int rand_int = rand.nextInt(999999999)+1000000000;

			url= urlROR_Update_ProfileList;
			params= Params_ROR_UpdateProfile_List+rand_int+Params_ROR_UpdateProfile_List1;

		}

		else if(payType.equalsIgnoreCase("RORUpdate_Refund_List")) {
			Random rand = new Random(); 
			int rand_int = rand.nextInt(99999);

			url= urlROR_Update_RefundList;
			params= Params_ROR_UpdateRefund_List+rand_int+Params_ROR_UpdateRefund_List1;

		} 

		else if(payType.equalsIgnoreCase("RORFetch_Profile_List")) {
			Random rand = new Random(); 
			int rand_int = rand.nextInt(99999);

			url= urlROR_Update_RefundList;
			params= Params_ROR_UpdateRefund_List+rand_int+Params_ROR_UpdateRefund_List1;

		} 	

		else if(payType.equalsIgnoreCase("RORUpdate_Payment")) {
			Random rand = new Random(); 
			int rand_int = rand.nextInt(99999);

			url= urlROR_Update_Payment;
			params= Params_ROR_Update_Payments;

		} 		

		Reporter.log(urlPay+url);
		Reporter.log("Params :" +params);

		request = RestAssured.given().
				when().
				log().all().
				headers(headers).
				body(params).
				put(url);
		return request;			
	}





	public Response payPost(String payType, String payType1) throws Exception {
		RestAssured.baseURI =urlPay;
		String url = null;
		String params = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request;	
		if(payType.equalsIgnoreCase("ROR_Create_ProfileList")) {
			params = Params_ROR_Create_Profile_List;
			url= urlROR_Create_ProfileList;
		}
		else if(payType.equalsIgnoreCase("RORUpdate_GW_Status")) {
			params = Params_RORUpdate_GW_Status;
			url= urlRORUpdate_GW_Status;
		}
		else if(payType.equalsIgnoreCase("RORCreate_Payment")) {
			params = Params_RORCreate_Payment;
			url= urlRORCreate_Payments;
		}		/*
		else if(payType.equalsIgnoreCase("RORCreate_ProfileList")) {
			params = Params_RORCreate_Profile_List;
			url= urlRORCreate_Profile_List;
		}*/
		else if(payType.equalsIgnoreCase("ROR_Search_ProfileList")) {
			params = Params_RORSearch_Profile_List;
			url= urlRORSearch_Profile_List;
		}
		else if(payType.equalsIgnoreCase("ROR_Create_Refunds")) {
			String ranno = getDateTime(1, "mmddss");
			params = Params_RORCreate_Refund+"5"+ranno+"}";
			url= urlRORCreate_Refunds;
		}
	
		else if(payType.equalsIgnoreCase("ROR_Recon")) {
			String ranno = getDateTime(1, "mmddss");
			params = ParamsROR_Recon+"6"+ranno+"}";	
			url= urlRORRecon;
		}


		Reporter.log(urlPay+url);
		Reporter.log("Params :" +params);


		request = RestAssured.given().
				when().
				log().all().
				body(params).
				headers(headers).
				post(url);
		return request;			
	}











	public Response promoPost(String payType, String payType1) {
		RestAssured.baseURI =promoURL;
		String url = null;
		String params = null;
		HashMap<String, Object> headers = new HashMap<>();
		Response resp = null;
		trip_refPromo.add(API_PaymentCommon1.tripRefForPromo());
		if(payType.equalsIgnoreCase("")) {
			params = "";
			url= "";
		}

		else if(payType.equalsIgnoreCase("CreatePromo")){
			url = promoURL + endPointCreatePromo;
			String trip_ref =trip_refPromo.get(0);
			String paramCreatePromo = "{\"user_id\":41649008,\"trip_ref\": \"" +trip_ref+ "\",\"type\":\"AIR_BOOKING\",\"promotions\":[{\"amount\":336,\"currency\":\"INR\",\"expiry_date\":\"22-1-2021\",\"trigger_date\":\"22-10-2019\"},{\"amount\":500,\"currency\":\"INR\",\"expiry_date\":\"26-03-2021\",\"trigger_date\":\"25-12-2019\"},{\"amount\":120,\"currency\":\"INR\",\"expiry_date\":\"14-09-2020\",\"trigger_date\":\"04-04-2020\",\"wallet\":\"OLA\",\"mobile\":\"9986696785\",\"email\":\"test@test.com\"}]}";
			params = paramCreatePromo;
			headers = headersForms();
			resp=RestAssured.given().when().log().all().body(params).headers(headers).post(url);
		}

		else if(payType.equalsIgnoreCase("CreatePromoGroup")){
			url = promoURL + endPointCreatePromo;
			String trip_ref = trip_refPromo.get(1);
			String paramCreatePromoGroup = "{\"user_id\":65176819,\"trip_ref\":\""+trip_ref+"\",\"type\":\"AIR_BOOKING\",\"promotions\":[{\"amount\":10,\"currency\":\"INR\",\"expiry_date\":\"14-02-2021\",\"trigger_date\":\"16-12-2020\"},{\"amount\":150,\"currency\":\"INR\",\"expiry_date\":\"15-12-2020\",\"trigger_date\":\"17-12-2019\",\"promo_type\":\"OLA\"},{\"amount\":20,\"currency\":\"INR\",\"expiry_date\":\"16-12-2020\",\"trigger_date\":\"16-01-2020\",\"promo_type\":\"OLA\"}]}";
			params = paramCreatePromoGroup;
			headers = headersForms();
			resp = RestAssured.given().when().log().all().body(params).headers(headers).post(url);
		}

		else if(payType.equalsIgnoreCase("ActivatePromo")){
			String trip_ref = trip_refPromo.get(0);
			String endPointActivatePromo = "/promoservice/v1/promogroups/" + trip_ref;
			url = promoURL + endPointActivatePromo;
			headers = headerFormsPromoService();
			resp=RestAssured.given().when().log().all().headers(headers).post(url);
		}

		else if(payType.equalsIgnoreCase("UpdatePromo")){
			int listSize = trip_refPromo.size(); 
			String trip_ref = trip_refPromo.get(listSize-1);
			url = promoURL + endPointUpdatePromo;
			headers = headersForms();
			String paramUpdatePromo = "{\"user_id\":41649008,\"trip_ref\": \"" +trip_ref+ "\",\"type\":\"AIR_BOOKING\",\"promotions\":[{\"amount\":336,\"currency\":\"INR\",\"expiry_date\":\"02-11-2020\",\"trigger_date\":\"01-10-2019\"},{\"amount\":500,\"currency\":\"INR\",\"expiry_date\":\"11-11-2020\",\"trigger_date\":\"01-20-2019\"}]}";
			params = paramUpdatePromo;
			resp = RestAssured.given().when().log().all().body(params).headers(headers).post(url);
		}


		Reporter.log(promoURL+url);
		Reporter.log("Params :" +params);
		return resp;			
	}



	public Response DAGet(String Type, String Type1){
		RestAssured.baseURI = urlDA;
		String url = null;
		HashMap<String, Object> headers = new HashMap<String,Object>();
		headers = headersForms();
		Response request;
		if(Type.equalsIgnoreCase("DABalance")){
			url = urlDA_Balance;
		}else if(Type.equalsIgnoreCase("DAStatus")){
			url = urlDA_Status;
		}

		request = RestAssured.given().
				when().
				log().all().
				headers(headers).
				get(url);

		Reporter.log(urlDA+url);
		return request;
	
	}
	
	public Response fetchrefunds(String refundType, String payType1) {
		RestAssured.baseURI = urlFetchRefunds;
		String url = null;
		String params = null;
		HashMap<String, Object> headers = new HashMap<String,Object>();
		headers = headersForms();
		Response request;
		if(refundType.equalsIgnoreCase("RefundsTrip")){
			url = urlRefundsTrip;
			params=ParamsFetchRefund;
		}
		else if(refundType.equalsIgnoreCase("RefundsTripRef")){
			url = urlRefundsTripRef;
			params=ParamsFetchRefundByTripRef;
		}
		
		Reporter.log(url);
		Reporter.log("Params :" +params);
		request = RestAssured.given().
				log().all().
				body(params).
				headers(headers).
				post(url);
		return request;
	}
	
	
	public Response promoGet(String payType, String payType1){
		RestAssured.baseURI = promoURL;
		String url = null;
		HashMap<String, Object> headers = new HashMap<String,Object>();
		headers = headersForms();
		Response request;
		if(payType.equalsIgnoreCase("")){
				url = "";
		}

		else if(payType.equalsIgnoreCase("PromoTripRefAndId")){
			url = promoURL + endPointPromotriprefandid;
		}

		else if(payType.equalsIgnoreCase("PromoGroupFromPromoRef")){
			url = promoURL + endPointPromoGroupPromoRef;
		}

		else if(payType.equalsIgnoreCase("PromoFromTripRef")){
			url = promoURL + endPointPromoGroupTripRef;
		}

		else if(payType.equalsIgnoreCase("PromoFromPromoId")){
			url = promoURL + endPointPromoFromPromoId;
		}

		else if(payType.equalsIgnoreCase("PromoGroupsFromCreatedDate")){
			url = promoURL + endPointPromoGroupsFromCreatedDate;
		}

		else if(payType.equalsIgnoreCase("PromoGroupsForAUpdatedDate")){
			url = promoURL + endPointPromoGroupsForAUpdatedDate;
		}

		else if(payType.equalsIgnoreCase("PromoGroupsForACreatedAndUpdatedDate")){
			url = promoURL + endPointPromoGroupsForACreatedAndUpdatedDate;
		}

		Reporter.log(promoURL+url);
		request = RestAssured.given().
				when().
				log().all().
				headers(headers).
				get(url);

		return request;
	}


	public Response rearchPayment(String payType, String payType1){
		RestAssured.baseURI =urlPay;
		String url = urlPayV3;
		String params = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request;	 
		if(payType.equalsIgnoreCase("VALIDATE")) {
			params = paramsCC;
			url= urlPayValidateV3;
		}
		else if(payType.equalsIgnoreCase("CC")) {
			params = paramsCC;
		}
		else if(payType.equalsIgnoreCase("CCVISA")) {
			params = paramsCCVISA;
		}
		//V2 pay DA
		else if(payType.equalsIgnoreCase("DA")) {
			url = urlPayV2;
			params = paramsDA;
		}
		else if(payType.equalsIgnoreCase("DAPay")) {
			url = urlPayV3;
			params = paramsDAPayV3;
		}
		

		else if(payType.equalsIgnoreCase("DAVAlidate")) {
			url = urlPayValidateV3;
			params = paramsDAValidateV3;
		}
		else if(payType.equalsIgnoreCase("DARefund")) {
			Reporter.log(urlDA+url);
			RestAssured.baseURI =urlDA;
			url = urlDA_Refund;
			params = paramsDA_Refund;
		}
		
		else if(payType.equalsIgnoreCase("CSPay")) {
			//RestAssured.baseURI =urlCS;
			url = urlCS_Pay;
			params = paramsCSPayvalidate;
		}
		else if(payType.equalsIgnoreCase("CSValidate")) {
			//RestAssured.baseURI =urlCS;
			url = urlCS_Validate;
			params = paramsCSPayvalidate;
		}

		else if(payType.equalsIgnoreCase("GV")) {
			params = paramsGV;
		}else if(payType.equalsIgnoreCase("WALLET")) {
			params = paramsWallet;
		}else if(payType.equalsIgnoreCase("CCGVWL")) {
			params = paramsCCGVWL;
		}else if(payType.equalsIgnoreCase("GVWL")) {
			params = paramsGVWL;
		}
		else if(payType.equalsIgnoreCase("FLYIN")) {
			RestAssured.baseURI =urlFlyin;
			url = urlPayFlyin;
			params = paramsFlyIN;
			Reporter.log(urlFlyin+url);
		}

		Reporter.log(urlPay+url);
		Reporter.log("Params : "+params);
		request = RestAssured.given().
				when().
				log().all().
				body(params).
				headers(headers).
				post(url);
		return request;
	}

	public Response createGV(int Amount) {
		RestAssured.baseURI =urlWallet;
		String params = paramsGV_Create_10;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms_GVCreate_10();
		if (Amount==5000) {
			params = paramsGV_Create_5000;
			headers = new HashMap<>();
			headers = headersForms_GVCreate_5000();
		}
		else if (Amount==100000) {
			params = paramsGV_Create_100000;
			headers = new HashMap<>();
			headers = headersForms_GVCreate_100000();
		}
		Response request = null;	 

		String url= urlEndpoint_GVCreate;
		Reporter.log(urlWallet+url);
		Reporter.log("Params : "+params);
		request = RestAssured.given().
				when().
				log().all().
				body(params).
				headers(headers).
				post(url);	
		return request;

	}


	@SuppressWarnings("deprecation")
	public Response rearch_SmilesAuth(String payType, String payType1){
		RestAssured.baseURI =URL_SmilesDev;
		Reporter.log(URL_SmilesDev);
		String url = URL_SmilesDev_Parm;
		String params = Param_Smiles_Auth;
		//httpRequest.header("Authorization", "8b7a2f7cd440989594c95cbdf7398bec" + "03cc2c7605899394907ad1e282f2aa75")
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersFormsSmiles();

		Response request = null;	 

		request = RestAssured.given().
				when().
				log().all().
				headers(headers).
				body("data-raw grant_type=client_credentials&scope=apioauth").

				post(url);

		return request;
	}

/*	public Response rearch_Smiles(String payType, String payType1){
		RestAssured.baseURI =urlPay;
		Reporter.log(urlPay);
		String url = urlPayV3;
		String params = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request = null;	 
		if(payType.equalsIgnoreCase("GenrateAuth")) {
			params = paramsCC;
			url= urlPayValidateV3;
		}

		return request;
	}*/

	@SuppressWarnings("null")
	public String[] getGV(int Amount) {

		String GVdetails[] = {"0","0","0"} ;
		Response resp = null;
		resp = createGV(Amount);
		JsonPath jsonPathEvaluator = resp.jsonPath();

		//System.out.println("Response body : "+ resp.body().asString());
		GVdetails[0] = jsonPathEvaluator.getString("gvNumber");
		GVdetails[1] = jsonPathEvaluator.getString("gvPin");
		GVdetails[2] = jsonPathEvaluator.getString("balance");
		return GVdetails;

	}

	public Response rearchWallet(String payType, String payType1){
		RestAssured.baseURI =urlWallet;
		Reporter.log(urlWallet);
		String url = null;
		String params = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request = null;	 
		if(payType.equalsIgnoreCase("PROMOUSED")) {
			url= urlEndPoint_Wallet_PromoUsed;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		else if(payType.equalsIgnoreCase("REVERTPROMO")) {
			url= urlEndPoint_Wallet_RevertPromo;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					post(url);
		}
		else if(payType.equalsIgnoreCase("GETDEDUCTION")) {
			url= urlEndPoint_Wallet_GetDeduction;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		else if(payType.equalsIgnoreCase("GETWALLET_Trnx")) {
			url= urlEndPoint_Wallet_GetWallet_Trnx;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		else if(payType.equalsIgnoreCase("CASHBACK_DETAILS")) {
			url= urlEndPoint_Wallet_CASHBACK_DETAILS;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		else if(payType.equalsIgnoreCase("GETWALLET_ALL")) {
			url= urlEndPoint_Wallet_GETWALLET_ALL;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		else if(payType.equalsIgnoreCase("GETWALLET_INR")) {
			url= urlEndPoint_Wallet_GETWALLET_INR;
			Reporter.log(url);
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}	
		else if(payType.equalsIgnoreCase("CASHBACK_DETAILS_WALLET")) {
			url= urlEndPoint_Wallet_CASHBACK_Wallet;
			Reporter.log(url);
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}	
		else if(payType.equalsIgnoreCase("WALLET_CREATE")) {
			url= urlEndPoint_Wallet_Create;
			params =paramWalletCreate;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					body(params).
					post(url);
		}

		Reporter.log(urlWallet+url);
		Reporter.log("Params : "+params);

		return request;
	}

	public Response prodAPIs(String payType, String payType1){
		String url = null;
		String endpointurl = null;
		String params = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request = null;	  

		if(payType.equalsIgnoreCase("Rwd_PayBack_Mobile")) {
			endpointurl = Prod_Url_Rewards;
			RestAssured.baseURI =endpointurl;
			url= Prod_Url_EndPoint_Rewards_PayBack_Mobile;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		if(payType.equalsIgnoreCase("Rwd_ADCB_Balance")) {
			endpointurl = Prod_Url_Rewards;
			RestAssured.baseURI =endpointurl;
			url= Prod_Url_EndPoint_Rewards_ADCB_Balance;
			params=Prod_Params_Rewards_ADCB_Balance;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					body(params).
					post(url);
		}
		else if(payType.equalsIgnoreCase("Wallet_Get_Currency")) {
			endpointurl = Prod_Url_Wallet;
			RestAssured.baseURI =endpointurl;
			url= Prod_Url_EndPoint_Wallet_getWallet;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		else if(payType.equalsIgnoreCase("Pay_Get_TW_Wallets")) {
			endpointurl = Prod_Url_PaymentService;
			RestAssured.baseURI =endpointurl;
			url= Prod_Url_EndPoint_Get_TP_Wallets;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		else if(payType.equalsIgnoreCase("Promo_Get_Group")) {
			endpointurl = Prod_Url_Promo;
			RestAssured.baseURI =endpointurl;
			url= Prod_Url_EndPoint_Promo_Group;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		else if(payType.equalsIgnoreCase("Bin_Get_CardInfo")) {
			endpointurl = "https://www.cleartrip.com";;
			RestAssured.baseURI =endpointurl;
			url= "/binmanager/v1/payment/cards?bin=552260";
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}

		else if(payType.equalsIgnoreCase("ActiveNB")) {
			endpointurl = Prod_Url_PaymentService;
			RestAssured.baseURI =endpointurl;
			url = Prod_Url_ActiveNB;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}

		else if(payType.equalsIgnoreCase("GetPaymentStatus")) {
			endpointurl = Prod_Url_PaymentService;
			RestAssured.baseURI =endpointurl;
			
			url = Prod_Url_PaymentStatus;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}

		else if(payType.equalsIgnoreCase("ActiveCardTypes")) {
			endpointurl = Prod_Url_PaymentService;
			RestAssured.baseURI =endpointurl;
			
            url = Prod_Url_ActiveCardType;
            request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}

		Reporter.log(endpointurl+url);
		Reporter.log("Params : "+params);
        return request;
	}

	public Response rearchGV(String payType, String payType1) throws ClassNotFoundException, SQLException{
		ArrayList<String> db_GV = db_GV();
		String GVID = db_GV.get(1);
		RestAssured.baseURI =urlWallet;
		Reporter.log(urlWallet);
		String url = null;
		String params = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request = null;	 
		if(payType.equalsIgnoreCase("GET")) {
			url= urlEndPoint_GV_Get;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		else if(payType.equalsIgnoreCase("REFUND")) {
			url= urlEndPoint_GV_Refund;
			params = ParamsGV_Refund;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					body(params).
					post(url);
		}
		else if(payType.equalsIgnoreCase("CAPTURE")) {
			url= urlEndPoint_GV_CAPTURE;
			params = ParamsGV_CAPTURE+"43852510"+ParamsGV_CAPTURE1;
			//System.out.println(params);
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					body(params).
					post(url);
		}

		else if(payType.equalsIgnoreCase("CREATE")){
			url= urlEndpoint_GV_GET;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);

		}

		Reporter.log(urlWallet+url);
		Reporter.log("Params : "+params);
		return request;


	}

	public Response rearchPaymentOTP(String payType, String payType1, String paymentID){
		RestAssured.baseURI =urlPay;
		String params = null;
		String Url = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response response;

		if(payType.equalsIgnoreCase("INIT")) {
			params = paramsInit;
			Url = urlInit ;

		}else if(payType.equalsIgnoreCase("PROCESS")) {
			params  = "{\"payment_id\":"+paymentID+",\"otp_gateway_id\":51,\"otp\":\"123456\"}";
			Url = urlProcess;
		}else if(payType.equalsIgnoreCase("RESEND")) {
			params = "{\"payment_id\":"+paymentID+",\"otp_gateway_id\":51}";

			Url = urlResend;
		}
		Reporter.log(urlPay);
		Reporter.log(Url);
		response = RestAssured.given().
				when().
				log().all().
				body(params).
				headers(headers).
				post(Url);
		Reporter.log(urlPay+Url);
		Reporter.log("Params : "+params);
		return response;
	}

	public Response ola(String payType, String payType1, String promoID) throws Exception {
		RestAssured.baseURI = urlPay;
		String params = null;
		String Url = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response response = null;
		if(payType.equalsIgnoreCase("Validate")){
			params = ParamsOLAValidate1+promoID+ParamsOLAValidate2;
			Url = urlOLA_Validate;
		}
		else if(payType.equalsIgnoreCase("pay")){
			params = ParamsOLAValidate1+promoID+paramsOLAPay;
			Url = urlOLA_Pay;
		}
		else if(payType.equalsIgnoreCase("Stat")){
			promoID ="PS"+promoID;
			Url = urlOLA_CheckStat+promoID;
			response = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(Url);
		}
		else if(payType.equalsIgnoreCase("PromoStatus")) {				
			Url = promoURL+urlOLA_PromoGroup;
			//String trip = getRandomNo(99999999);
			Random rand = new Random();
			int n = rand.nextInt(9999999);
			String trip = Integer.toString(n);

			params = ParamsOLAPromoGroup1+trip+ParamsOLAPromoGroup2;
			Reporter.log(Url);
			Reporter.log(params);
		}			
		if(!payType.equalsIgnoreCase("Stat")){
			response = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(Url);
		}
		Reporter.log(urlPay);
		Reporter.log(Url);
		Reporter.log("Params : "+params);
		////System.out.println(response.body().asString());
		return response;

	}

	public Response BinDetail(String url, String params){
		String baseurl=urlCardInfo_Service;
		RestAssured.baseURI =baseurl;
		Reporter.log(baseurl);
		String url1 = null;
		String params1 = null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request = null;	 
		if(url.equalsIgnoreCase("ADD_MC_Credit")&&params.equalsIgnoreCase(Params_Singlebincard_Credit)) { 
			url1= baseurl+url_Singlebincard;
			params1=returnParamForBin_CC();
			request = RestAssured.given().
					when().
					log().all(). 
					headers(headers).
					body(params1).
					post(url1);
		}
		else if(url.equalsIgnoreCase(baseurl+url_Singlebincard)&&params.equalsIgnoreCase("Params_Singlebincard_Debit")) {
			url1= baseurl+url_Singlebincard;
			params1=params;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					body(params1).
					post(url1);
		}
		else if(url.equalsIgnoreCase("CAPTURE")) {
			url= urlEndPoint_GV_CAPTURE;
			params = ParamsGV_CAPTURE;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					body(params).
					post(url);
		}
		else if(url.equalsIgnoreCase("GetCard")) {
			url= "v1/payment/cards?bin=512345";
			params = ParamsGV_CAPTURE;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}

		else if(url.equalsIgnoreCase("GetCard_Multi")) {
			url= baseurl + endPointCardMulti;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		Reporter.log(urlCardInfo_Service+url);
		Reporter.log(url);
		return request;
	}

	public Response validationforbindetail(String url,Response resp){
		Reporter.log(resp.asString());
		//System.out.println(resp.asString());
		int statusCode = resp.getStatusCode();	
		Reporter.log("statusCode: " + statusCode);
		if(statusCode!=200) {
			Reporter.log("statusCode =" +statusCode);
			Assert.assertFalse(true);
		}
		String bodyAsString=resp.asString(); 
		JsonPath jsonPath = new JsonPath(bodyAsString);
		if(url.equalsIgnoreCase("ADD_Card"))
		{
			String description = jsonPath.getString("description");
			String status=jsonPath.getString("status");
			Assert.assertNotNull(description);
			//			Assert.assertEquals("true", "false");;
			Assert.assertNotNull(bodyAsString.contains("cardInfoRequest"));
			Assert.assertNotNull(bodyAsString.contains("cardInfoFromDb"));
			/*Assert.assertEquals("Response boday contains Successfully added", true ,bodyAsString.contains("Successfully added"));
			Assert.assertEquals("Response boday contains card_type", true ,bodyAsString.contains("card_type"));
			Assert.assertEquals("Response boday contains bin", true ,bodyAsString.contains("bin"));
			Assert.assertEquals("Response boday contains bank", true ,bodyAsString.contains("bank"));
			Assert.assertEquals("Response boday contains country", true ,bodyAsString.contains("country"));
			Assert.assertEquals("Response boday contains issuer_type", true ,bodyAsString.contains("issuer_type"));
			Assert.assertEquals("Response boday contains bank_id", true ,bodyAsString.contains("bank_id"));
			Assert.assertEquals("Response boday contains status", true ,bodyAsString.contains("status"));*/
			if(status.equals("S") && description.equals("Successfully added")){
				Assert.assertTrue(true);
			}
		}
		else if(url.equalsIgnoreCase("Get_Card")) {
			String bin = jsonPath.getString("bin");
			String country = jsonPath.getString("country");
			if(!bin.equalsIgnoreCase("512345")) {
				Reporter.log("bin =" +bin);
				Assert.assertFalse(true);		

			}
			if(!country.equalsIgnoreCase("INDIA")) {
				Reporter.log("country =" +country);
				Assert.assertFalse(true);		

			}
		} 

		else if(url.equalsIgnoreCase("Get_Card_Multi")){
			if(!bodyAsString.isEmpty())
				Assert.assertTrue(true);
		}
		return resp; 

	}

	public Response validation(String payType, Response resp){
		Reporter.log("Response body "+payType +" : "+ resp.body().asString());
		System.out.println("Response body "+payType +" : "+ resp.body().asString());
		int statusCode = resp.getStatusCode();	
		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();
		if(!payType.equals("WALLET_CREATE")) {
			if(statusCode!=200) {
				Assert.assertTrue(false);
			}
		}
		if(payType.equalsIgnoreCase("VALIDATE")) {
			String redirection = jsonPathEvaluator.getString("redirection_required");
			String Response = jsonPathEvaluator.getString("response_message");
			Reporter.log("redirection required " +redirection);
			Reporter.log("Response " +Response);
			if(!redirection.equals("[Y]")) {
				Assert.assertTrue(false);
			}  
			if(!Response.equals("[Payment can be processed-Redirection required]")) {
				Assert.assertTrue(false);
			}
		}

		else if(payType.equalsIgnoreCase("CC")) {
			String paymentID = jsonPathEvaluator.getString("id");
			String statusCC = jsonPathEvaluator.getString("status");
			String description = jsonPathEvaluator.getString("description");
			Reporter.log("Status is Sucess " +statusCC);
			Reporter.log("description Process " +description);
			Reporter.log(" Payment ID: " + paymentID);
			if(!statusCC.equals("[I]")) {
				if(!description.equals("[Payment successful]")) {
					Assert.assertTrue(false);
				}
				Assert.assertTrue(false);
			}
		}
		
		else if(payType.equalsIgnoreCase("DA")) {
			if(!resp.body().asString().contains("Payment successful")){
				Assert.assertTrue(false);
			}
		}	
		else if(payType.equalsIgnoreCase("DAPay")) {
			if(!resp.body().asString().contains("Payment successful")){
				Assert.assertTrue(false);
			}
		}
		else if(payType.equalsIgnoreCase("DABalance")) {
			String Balance = jsonPathEvaluator.getString("balance");
			String CreditLimit = jsonPathEvaluator.getString("credit_limit");
			String balance_type = jsonPathEvaluator.getString("balance_type");
			Reporter.log("Balance " +Balance);
			Reporter.log("CreditLimit " +CreditLimit);
			if(!balance_type.equals("DR")) {
				Assert.assertTrue(false);
			}  
		}
		
		else if(payType.equalsIgnoreCase("DAStatus")) {
			String Balance = jsonPathEvaluator.getString("balance");
			String CreditLimit = jsonPathEvaluator.getString("credit_limit");
			String balance_type = jsonPathEvaluator.getString("balance_type");
			Reporter.log("Balance " +Balance);
			Reporter.log("CreditLimit " +CreditLimit);
			if(!balance_type.equals("DR")) {
				Assert.assertTrue(false);
		
			}  
			
		}
		else if(payType.equalsIgnoreCase("DARefund")) {
			if(!resp.body().asString().contains("Payment successful")){
				Assert.assertTrue(false);
			
			}
			
		}
		
		
		
		else if(payType.equalsIgnoreCase("DAValidate")) {
			if(!resp.body().asString().contains("Payment can be processed")){
				Assert.assertTrue(false);
			
			}
			
		}		
		
		else if(payType.equalsIgnoreCase("CSValidate")) {
			String paymenttype = jsonPathEvaluator.getString("payment_type");
			String responsemsg = jsonPathEvaluator.getString("response_message");
			Reporter.log("paymentType is " +paymenttype);
			Reporter.log("Response Message " +responsemsg);
		
			/*if(!paymenttype.equals("[CS]"))
			{
				Assert.assertTrue(false);
			}
			if(!responsemsg.equals("[Validation successful]")) {
				Assert.assertTrue(false);
			}*/
		}
		else if(payType.equalsIgnoreCase("CSPay")) {
			String paymenttype = jsonPathEvaluator.getString("payment_type");
			String description = jsonPathEvaluator.getString("description");
			String status=jsonPathEvaluator.getString("status");
			Reporter.log("paymentType is " +paymenttype);
			Reporter.log("Response Message " +description);
			Reporter.log("status Message " +status);
			if(!paymenttype.equals("[CS]")){
				Assert.assertTrue(false);
			}
			if(!status.equals("[S]")) {
				Assert.assertTrue(false);
			}	
			if(!description.equals("[Pay Success]")) {
				Assert.assertTrue(false);
			}
		}
		
		else if(payType.equalsIgnoreCase("GV")) {
			String paymentID = jsonPathEvaluator.getString("id");
			String statusCC = jsonPathEvaluator.getString("status");					
			String description = jsonPathEvaluator.getString("description");
			Reporter.log("Status is failing " +statusCC);
			Reporter.log("Description GV " +description);
			Reporter.log(" Payment ID: " + paymentID);			
			if(!statusCC.equals("[S]")) {
				if(!description.equals("[Gift voucher redemption success]")) {
					Assert.assertTrue(false);
				}
				Assert.assertTrue(false);
			}		
			
		}else if(payType.equalsIgnoreCase("WALLET")) {
			String paymentID = jsonPathEvaluator.getString("id");
			String statusCC = jsonPathEvaluator.getString("status");				
			String description = jsonPathEvaluator.getString("description");
			Reporter.log("Status is failing " +statusCC);
			Reporter.log("Description Wallet " +description);
			Reporter.log(" Payment ID: " + paymentID);		
			if(!statusCC.equals("[S]")) {
				if(!description.equals("[Wallet payment success]")) {
					Assert.assertTrue(false);
				}
				Assert.assertTrue(false);
			}
		}
		else if(payType.equalsIgnoreCase("GVWL")) {
			if(!resp.body().asString().contains("Wallet payment success")){
				Assert.assertTrue(false);
			
			}
			if(!resp.body().asString().contains("Gift voucher redemption success")){
				Assert.assertTrue(false);
			
			}
		}else if(payType.equalsIgnoreCase("CCGVWL")) {
			String paymentID = jsonPathEvaluator.getString("id");
			String status = jsonPathEvaluator.getString("status");
			Reporter.log("ID : "+paymentID);
			Reporter.log("status : "+status);
			if(!status.equals("[I, P, P]")) {
				Reporter.log("Init Status is failing " +status);	 
				Assert.assertTrue(false);
			}				
		}else if(payType.equalsIgnoreCase("INIT")) {
			String paymentID = jsonPathEvaluator.getString("id");
			String statusInit = jsonPathEvaluator.getString("status");
			String collectOtp = jsonPathEvaluator.getString("collect_otp");				
			String redirection_required = jsonPathEvaluator.getString("redirection_required");
			Reporter.log("Init Status is Passed " +statusInit);
			Reporter.log("redirection_required " +redirection_required);
			Reporter.log(" Payment ID: " + paymentID);
			Reporter.log(" collectOtp: " + collectOtp);
			if(!statusInit.equals("[I]")) {
				Reporter.log("Init Status is failed " +statusInit);
				Assert.assertTrue(false);
			}
			if(!redirection_required.equals("[N]")) {
				Reporter.log("redirection_required " +redirection_required);
				Assert.assertTrue(false);
			}
			if(!collectOtp.equals("[true]")) {
				Reporter.log("collectOtp " +collectOtp);
				Assert.assertTrue(false);
			}
		}else if(payType.equalsIgnoreCase("PROCESS")) {
			String statusProcess = jsonPathEvaluator.getString("status");
			String descriptionOTP = jsonPathEvaluator.getString("description");
			Reporter.log("description Process " +descriptionOTP);
			Reporter.log("Status is failing " +statusProcess);
			if(!statusProcess.equals("[S]")) {
				if(!descriptionOTP.equals("[Payment successful]")) {
					Assert.assertTrue(false);
				}
				Assert.assertTrue(false);
			}	
		}else if(payType.equalsIgnoreCase("RESEND")) {
			String status = jsonPathEvaluator.getString("status");
			String descriptionOTP = jsonPathEvaluator.getString("description");
			Reporter.log("Status is failing " +status);
			Reporter.log("description " +descriptionOTP);
			if(!status.equals("[S]")) {
				Assert.assertTrue(false);
			}
			if(!descriptionOTP.equals("[otp resend successfully]")) {
				Assert.assertTrue(false);
			}
		}
		else if(payType.equalsIgnoreCase("UPDATE")) {
			String status = jsonPathEvaluator.getString("status");
			Reporter.log(" status : " + status);
			if(!status.equals("SUCCESS")) {
				Assert.assertTrue(false);
			}
		}	
		else if(payType.equals("RefundsTrip")) {
			if(statusCode!=200) {
				Assert.assertTrue(false);
			}
		}
		
		else if(payType.equals("RefundsTripRef")) {
			String status = jsonPathEvaluator.getString("status");
			if(statusCode!=200) {
				Assert.assertTrue(false);
			}
		
		}
		else if(payType.equalsIgnoreCase("GETURL")) {
			String CTPay_URL = jsonPathEvaluator.getString("redirection_url");
			Reporter.log("CTpay  URL "+CTPay_URL);
			if(!CTPay_URL.contains("initiatePayment")) {
				Assert.assertTrue(false);
			}
		}		
		else if(payType.equalsIgnoreCase("STATUS")) {
			String Order_Status = jsonPathEvaluator.getString("order_status");
			String Description = jsonPathEvaluator.getString("description");
			Reporter.log("Order_Status "+Order_Status);
			Reporter.log("Description : "+Description);
			if(!Order_Status.contains("SUCCESS")) {
				Assert.assertTrue(false);
			}
			if(!Description.contains("Payment successful")) {
				Assert.assertTrue(false);
			}
		}
		else if(payType.equalsIgnoreCase("CreateURL")) {
			String Order_ID = jsonPathEvaluator.getString("order_id");
			String status = jsonPathEvaluator.getString("status");
			String redirection_url = jsonPathEvaluator.getString("redirection_url");
			Reporter.log("Order_ID "+Order_ID);
			Reporter.log("redirection_url : "+redirection_url);
			Reporter.log("status : "+status);
			if(!status.contains("SUCCESS")) {
				Assert.assertTrue(false);
			}
			if(!redirection_url.contains("initiatePayment")) {
				Assert.assertTrue(false);
			}
		}

		else if(payType.equalsIgnoreCase("EW_USERDETAILS")) {
			String Avl_bal = jsonPathEvaluator.getString("available_balance");
			String Crdt_lmt = jsonPathEvaluator.getString("credit_limit");
			String Opt_In = jsonPathEvaluator.getString("opted-in");
			Reporter.log("available_balance "+Avl_bal);
			Reporter.log("credit_limit : "+Crdt_lmt);
			if(!Opt_In.contains("true")) {
				Reporter.log("Opt_In : "+Opt_In);
				Assert.assertTrue(false);
			}
			if(Avl_bal.isEmpty()) {
				Reporter.log("Avl_bal : "+Avl_bal);
				Assert.assertTrue(false);
			}
			if(Crdt_lmt.isEmpty()) {
				Reporter.log("Crdt_lmt : "+Crdt_lmt);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("EW_OUTSTANDING")) {
			String dueAmount = jsonPathEvaluator.getString("dueAmount");
			String userId = jsonPathEvaluator.getString("userId");
			if (dueAmount.isEmpty()) {
				Reporter.log("dueAmount : "+dueAmount);
				Assert.assertTrue(false);
			}
			if (!userId.equals("41697596")) {
				Reporter.log("userId : "+userId);
				Assert.assertTrue(false);
			}			
		}
		else if (payType.equalsIgnoreCase("EW_OPTIN")) {
			String description = jsonPathEvaluator.getString("description");
			String status = jsonPathEvaluator.getString("status");
			if (!description.contains("Account is already activated")) {
				Reporter.log("description : "+description);
				Assert.assertTrue(false);
			}
			if (!status.equals("false")) {
				Reporter.log("status : "+status);
				Assert.assertTrue(false);
			}	
		}	


		else if (payType.equalsIgnoreCase("EW_OPTIN123")) {
			String status = jsonPathEvaluator.getString("status");
			if(!status.contains("true")) {
				Reporter.log("status : "+status);
				Assert.assertTrue(false);
			}

		}
		else if (payType.equalsIgnoreCase("EW_PAY")) {				
			if(!resp.body().asString().contains("EP")) {
				Reporter.log("paymentType is not EP 	");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("Payment will be done later")) {
				Reporter.log("Payment will be done later message not displayed ");
				Assert.assertTrue(false);
			}
			if(resp.body().asString().contains("Not attempted")) {
				Reporter.log("Not attempted ");
				Assert.assertTrue(false);
			}				

		}else if (payType.equalsIgnoreCase("EW_Autodebit")) {	
			if(!resp.body().asString().contains("CAPTURED")) {
				Reporter.log("status text is not displayed");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("debit_amount")) {
				Reporter.log("debit_amount text is not displayed");
				Assert.assertTrue(false);
			}


		}
		else if (payType.equalsIgnoreCase("EW_PAY_Multi")) {				
			if(!resp.body().asString().contains("EP")) {
				Reporter.log("paymentType is not EP 	");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("GV")) {
				Reporter.log("paymentType is not GV 	");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("WT")) {
				Reporter.log("paymentType is not WT 	");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("Gift voucher redemption success")) {
				Reporter.log("Gift voucher redemption success not displayed");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("Wallet payment success")) {
				Reporter.log("Wallet payment success not displayed");
				Assert.assertTrue(false);
			}




			if(!resp.body().asString().contains("Payment will be done later")) {
				Reporter.log("Payment will be done later message not displayed ");
				Assert.assertTrue(false);
			}
			if(resp.body().asString().contains("Not attempted")) {
				Reporter.log("Not attempted ");
				Assert.assertTrue(false);
			}	
		}
		
		else if (payType.equalsIgnoreCase("EW_Summary")) {
			if(!resp.body().asString().contains("amount")) {
				Reporter.log("amount is not displayed : ");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("S")) {
				Reporter.log("Status S is not displayed : ");
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("EW_VALIDATE")) {
			if(!resp.body().asString().contains("Validation successful")) {
				Reporter.log("Validation successful is not displayed : ");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("EP")) {
				Reporter.log("EP  is not displayed ");
				Assert.assertTrue(false);
			}
			/*	if(!resp.body().asString().contains("WT")) {
					Reporter.log("WT is not displayed ");
					Assert.assertTrue(false);
				}*/

			if(resp.body().asString().contains("Internal server error")) {
				Reporter.log("Internal server error  is displayed ");
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("EW_Refund")) {

		}
		else if (payType.equalsIgnoreCase("OLAVALIDATE")) {
			String status = jsonPathEvaluator.getString("status");
			String id = jsonPathEvaluator.getString("id"); 
			if(id.isEmpty()) {
				Reporter.log("ID is empty: "+id);
				Assert.assertTrue(false);
			}
			if(!status.contains("success")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("OLAPAY")) {
			String status = jsonPathEvaluator.getString("status");
			String id = jsonPathEvaluator.getString("id"); 
			String transactionId = jsonPathEvaluator.getString("transactionId"); 
			if(id.isEmpty()) {
				Reporter.log("ID is empty: "+id); //transactionId
				Assert.assertTrue(false);
			}
			if(!status.contains("success")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			else if(transactionId.isEmpty()) {
				Reporter.log("transactionId is : "+transactionId);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("OLAStat")) {
			String status = jsonPathEvaluator.getString("status");
			String uniqueBillId = jsonPathEvaluator.getString("uniqueBillId"); 
			if(!status.contains("completed")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			else if(uniqueBillId.isEmpty()) {
				Reporter.log("uniqueBillId is : "+uniqueBillId);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("OLAPromoStatus")) {
			String status = jsonPathEvaluator.getString("status");
			String uniqueBillId = jsonPathEvaluator.getString("uniqueBillId"); 
			if(!resp.body().asString().contains("DEACTIVE")) {{
				Reporter.log("DEACTIVE not displayed");
				Assert.assertTrue(false);
			}
			if(!status.contains("completed")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			else if(uniqueBillId.isEmpty()) {
				Reporter.log("uniqueBillId is : "+uniqueBillId);
				Assert.assertTrue(false);
			}
			}

		}
		else if (payType.equalsIgnoreCase("FLYIN")) {
			String status = jsonPathEvaluator.getString("status");
			String reference_id = jsonPathEvaluator.getString("reference_id"); 
			String processing_gateway = jsonPathEvaluator.getString("processing_gateway"); 

			if(!status.contains("INITIALIZING")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}

			if(reference_id.isEmpty()) {
				Reporter.log("reference_id is : "+reference_id);
				Assert.assertTrue(false);
			}
			if(!processing_gateway.equals("CHECKOUT")) {
				Reporter.log("processing_gateway is : "+processing_gateway);
				Assert.assertTrue(false);
			}
		}

		else if (payType.equalsIgnoreCase("adcb_checkBalance")) {				
			if(!resp.body().asString().contains("Successfully")) {
				Reporter.log("checkBalance Failed");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("Successfully fetched pay by points amount")) {
				Reporter.log("Successfully fetched pay by points amount - message is not displayed ");
				Assert.assertTrue(false);
			}
			if(resp.body().asString().contains("Not attempted")) {
				Reporter.log("Not attempted ");
				Assert.assertTrue(false);
			}	

			Reporter.log("Successfully fetched pay by points amount - message is displayed ");

		}
		else if (payType.equalsIgnoreCase("adcb_sendOTP")) {				
			if(!resp.body().asString().contains("successfully")) {
				Reporter.log("OTP Failed");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("OTP sent successfully")) {
				Reporter.log("OTP sent successfully - message is not displayed ");
				Assert.assertTrue(false);
			}
			if(resp.body().asString().contains("Not attempted")) {
				Reporter.log("Not attempted ");
				Assert.assertTrue(false);
			}	

			Reporter.log("OTP sent successfully - message is displayed ");

		}
		else if (payType.equalsIgnoreCase("adcb_validate")) {				
			if(!resp.body().asString().contains("Reward Points")) {
				Reporter.log("Validation Failed");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("Reward Points validation successful")) {
				Reporter.log("Reward Points validation successful - message is not displayed ");
				Assert.assertTrue(false);
			}
			if(resp.body().asString().contains("Not attempted")) {
				Reporter.log("Not attempted ");
				Assert.assertTrue(false);
			}	

			Reporter.log("Reward Points validation successful - message is displayed ");

		}
		else if (payType.equalsIgnoreCase("adcb_pay")) {				

			String status = jsonPathEvaluator.getString("status");
			String description = jsonPathEvaluator.getString("description"); 

			Reporter.log("Status is : "+status);
			Reporter.log("---------------------------- Message is -----------"+description);

			if(!status.equalsIgnoreCase("[S]")) {
				Reporter.log("Status is : "+status);
				Assert.assertTrue(false);
			}

			if(!description.contains("[Successfully redeemed points]")) {
				Reporter.log("Message is : "+description);
				Assert.assertTrue(false);
			}


			Reporter.log("Successfully redeemed points - message is displayed ");

		}
		else if (payType.equalsIgnoreCase("adcb_pay2")) {				

			String status = jsonPathEvaluator.getString("status");
			String description = jsonPathEvaluator.getString("description"); 

			Reporter.log("Status is : "+status);
			Reporter.log("Message is : "+description);

			if(!status.equalsIgnoreCase("[F]")) {
				Reporter.log("Status is : "+status);
				Assert.assertTrue(false);
			}

			if(!description.contains("[External ref not found for given trackId")) {
				Reporter.log("Message is : "+description);
				Assert.assertTrue(false);
			}

			if(resp.body().asString().contains("Successfully redeemed points")) {
				Reporter.log("Validation Failed");
				Assert.assertTrue(false);
			}

			Reporter.log( description + "- message is displayed ");

		}

		else if (payType.equalsIgnoreCase("Binmanager")) {				

			String cardType = jsonPathEvaluator.getString("cardType");
			String isOtpEligible = jsonPathEvaluator.getString("isOtpEligible"); 

			Reporter.log("CardType is : "+cardType);

			if(!cardType.equalsIgnoreCase("DEBIT")) {
				Reporter.log("CardType is : "+cardType);
				Assert.assertTrue(false);
			}

			if(!isOtpEligible.equalsIgnoreCase("false")) {
				Reporter.log("isOtpEligible is : "+isOtpEligible);
				Assert.assertTrue(false);
			}

		}
		else if (payType.equalsIgnoreCase("PAYBACK_CheckBalance")) {				

			String status = jsonPathEvaluator.getString("status");
			String description = jsonPathEvaluator.getString("description"); 

			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}		
			if(!description.equalsIgnoreCase("Successfully fetched payback points amount")) {
				Reporter.log("description is : "+description);
				Assert.assertTrue(false);
			}
			Reporter.log("Successfully fetched payback points amount - message is displayed ");

		}

		else if (payType.equalsIgnoreCase("PAYBACK_Validate")) {				

			String response_message = jsonPathEvaluator.getString("response_message");
			String payment_type = jsonPathEvaluator.getString("payment_type"); 
			if(!response_message.equalsIgnoreCase("[Reward Points validation successful]")) {
				Reporter.log("response_message is : "+response_message);
				Assert.assertTrue(false);
			}		
			if(!payment_type.equalsIgnoreCase("[RP]")) {
				Reporter.log("payment_type is : "+payment_type);
				Assert.assertTrue(false);
			}
			Reporter.log("Successfully fetched pay by points amount - message is displayed ");

		}
		else if (payType.equalsIgnoreCase("PAYBACK_Pay")) {				

			String status = jsonPathEvaluator.getString("status");
			String description = jsonPathEvaluator.getString("description"); 
			if(!status.equalsIgnoreCase("[S]")) {
				Reporter.log("response_message is : "+status);
				Assert.assertTrue(false);
			}		
			if(!description.equalsIgnoreCase("[Successfully redeemed points]")) {
				Reporter.log("Message is : "+description);
				Assert.assertTrue(false);
			}
			Reporter.log("Successfully redeemed points - message is displayed ");

		}
		else if (payType.equalsIgnoreCase("PAYBACK_Pay_WT_MultiGV_CC")) {				

			if(!resp.body().asString().contains("redirection_info")) {
				Reporter.log("redirection_info is not displayed for CC ");
				Assert.assertTrue(false);
			}if(!resp.body().asString().contains("Not attempted")) {
				Reporter.log("Not attempted message is not displayed");
				Assert.assertTrue(false);
			}

			Reporter.log("Not attempted - message is displayed ");

		}
		else if (payType.equalsIgnoreCase("PAYBACK_Pay_WT_MultiGV")) {				

			System.out.println(resp.body().asString());
			if(!resp.body().asString().contains("Wallet payment success")) {
				Reporter.log("Wallet payment success message is not displayed ");
				Assert.assertTrue(false);
			}if(!resp.body().asString().contains("Successfully redeemed points")) {
				Reporter.log("Successfully redeemed points message is not displayed");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("Gift voucher redemption success")) {
				Reporter.log("Gift voucher redemption success message is not displayed");
				Assert.assertTrue(false);
			}

			Reporter.log("Successfully executed PAYBACK_Pay_WT_MultiGV ");

		}
		else if (payType.equalsIgnoreCase("PAYBACK_Earn")) {				

			String status = jsonPathEvaluator.getString("status");
			String description = jsonPathEvaluator.getString("description"); 
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("response_message is : "+status);
				Assert.assertTrue(false);
			}		
			if(!description.equalsIgnoreCase("Successful Payback earn transaction")) {
				Reporter.log("Message is : "+description);
				Assert.assertTrue(false);
			}
			Reporter.log("Successfully fetched pay by points amount - message is displayed ");

		}
		else if (payType.equalsIgnoreCase("PAYBACK_Reverseearn")) {				

			String status = jsonPathEvaluator.getString("status");
			String description = jsonPathEvaluator.getString("description"); 
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("response_message is : "+status);
				Assert.assertTrue(false);
			}		
			if(!description.equalsIgnoreCase("Successfully reversed Payback Earn transaction")) {
				Reporter.log("Message is : "+description);
				Assert.assertTrue(false);
			}
			Reporter.log("Successfully fetched pay by points amount - message is displayed ");

		}
		else if (payType.equalsIgnoreCase("PromoActive")) {		

			if(!resp.body().asString().contains("trip_ref")) {
				Reporter.log("trip_ref is not displayed ");
				Assert.assertTrue(false);
			}if(!resp.body().asString().contains("status")) {
				Reporter.log("status is not displayed");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("ACTIVE")) {
				Reporter.log("ACTIVE is not displayed");
				Assert.assertTrue(false);
			}
		}



		else if (payType.equalsIgnoreCase("PAYBACK_CheckEarnPoints")) {				

			String description = jsonPathEvaluator.getString("description");
			String status = jsonPathEvaluator.getString("status"); 
			if(!description.equalsIgnoreCase("Successfully calculated earn points")) {
				Reporter.log("Description is : "+description);
				Assert.assertTrue(false);
			}		
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			Reporter.log("Successfully checked Earn points ");
		}

		else if (payType.equalsIgnoreCase("PAYBACK_CheckMobileLinked")) {				

			String isAccountLinked = jsonPathEvaluator.getString("isAccountLinked");
			String status = jsonPathEvaluator.getString("status"); 
			if(!isAccountLinked.equalsIgnoreCase("true")) {
				Reporter.log("isAccountLinked is : "+isAccountLinked);
				Assert.assertTrue(false);
			}		
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			Reporter.log("Successfully checked if Mobile is linked ");
		}

		else if (payType.equalsIgnoreCase("PAYBACK_CheckCardLinked")) {	

			String isAccountLinked = jsonPathEvaluator.getString("isAccountLinked");
			String status = jsonPathEvaluator.getString("status"); 
			if(!isAccountLinked.equalsIgnoreCase("true")) {
				Reporter.log("isAccountLinked is : "+isAccountLinked);
				Assert.assertTrue(false);
			}		
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			Reporter.log("Successfully checked if Card is linked ");
		}
		else if (payType.equalsIgnoreCase("PAYBACK_Forgotpassword")) {				

			String description = jsonPathEvaluator.getString("isAccountLinked");
			String status = jsonPathEvaluator.getString("status"); 
			if(!description.equalsIgnoreCase("false")) {
				Reporter.log("isAccountLinked is : "+description);
				Assert.assertTrue(false);
			}		
			if(!status.equalsIgnoreCase("F")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			Reporter.log("Successfully fetched PIN for Mobile Number ");
		}

		else if (payType.equalsIgnoreCase("PromoUsed")) {				
			String usedPromo = jsonPathEvaluator.getString("usedPromo");
			if(!usedPromo.equalsIgnoreCase("10.0")) {
				Reporter.log("usedPromo is : "+usedPromo);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("FetchPayDetails")) {
			if(!resp.body().asString().contains("user_id")) {
				Reporter.log("user_id is not displayed ");
				Assert.assertTrue(false);
			}if(!resp.body().asString().contains("air_bookings")) {
				Reporter.log("air_bookings text is not displayed");
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("Reporting")) {
			if(!resp.body().asString().contains("trip_ref")) {
				Reporter.log("trip_ref is not displayed ");
				Assert.assertTrue(false);
			}if(!resp.body().asString().contains("payment_id")) {
				Reporter.log("payment_id is not displayed");
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("ReportingPAYID")) {
			if(!resp.body().asString().contains("description")) {
				Reporter.log("description is not displayed ");
				Assert.assertTrue(false);
			}/*if(!resp.body().asString().contains("Successful Transaction")) {
				Reporter.log("Successful Transaction is not displayed");
				Assert.assertTrue(false);
			}*/if(!resp.body().asString().contains("payment_type")) {
				Reporter.log("payment_type is not displayed");
				Assert.assertTrue(false);
			}
		}


		else if (payType.equalsIgnoreCase("createrefund")) {
			String message = jsonPathEvaluator.getString("message");
			String status = jsonPathEvaluator.getString("status"); 
			if(!message.equalsIgnoreCase("Refund entry successful with status S")) {
				Reporter.log("isAccountLinked is : "+message);
				Assert.assertTrue(false);
			}		
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("updatestatus")) {
			String message = jsonPathEvaluator.getString("message");
			String status = jsonPathEvaluator.getString("status"); 
			if(!message.equalsIgnoreCase("Update Successful")) {
				Reporter.log("Message is : "+message);
				Assert.assertTrue(false);
			}		
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("updatepgtxnid")) {
			String message = jsonPathEvaluator.getString("message");
			String status = jsonPathEvaluator.getString("status"); 
			if(!message.equalsIgnoreCase("Update Successful")) {
				Reporter.log("Message is : "+message);
				Assert.assertTrue(false);
			}		
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("getrecord")) {
			String message = jsonPathEvaluator.getString("message");
			String status = jsonPathEvaluator.getString("status"); 
			if(!message.equalsIgnoreCase("Query successful")) {
				Reporter.log("Message is : "+message);
				Assert.assertTrue(false);
			}		
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("wallet_getcards")) {
			/*if(!resp.body().asString().contains("number")) {
					Reporter.log("card number is not displayed ");
					Assert.assertTrue(false);
				}*/
				if(resp.body().asString().contains("Card already exist")) {
					Reporter.log("Card already exist is not displayed ");
					//Assert.assertTrue(false);					
				}
				else if(!resp.body().asString().contains("exp_month")) {
					Reporter.log("exp_month is not displayed ");
					Assert.assertTrue(false);
				}else if(!resp.body().asString().contains("number")) {
					Reporter.log("Card Number is not displayed");
					Assert.assertTrue(false);
				}
		}
		else if (payType.equalsIgnoreCase("wallet_deleteCards")) {
			if(!resp.body().asString().contains("[")) {
				Reporter.log("Card deletion failed");
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("wallet_PROMOUSED")) {
			String usedPromo = jsonPathEvaluator.getString("usedPromo");
			if(!usedPromo.equalsIgnoreCase("10.0")) {
				Reporter.log("usedPromo is : "+usedPromo);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("wallet_REVERTPROMO")) {

		}else if (payType.equalsIgnoreCase("wallet_GETDEDUCTION")) {
			String usedPromo = jsonPathEvaluator.getString("promoused");
			if(!usedPromo.equalsIgnoreCase("10.0")) {
				Reporter.log("usedPromo is : "+usedPromo);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("wallet_GETWALLET_Trnx")) {

			if(!resp.body().asString().contains("Q1810310049")) {
				Reporter.log("Q1810310049 is not displayed ");
				Assert.assertTrue(false);
			}
		}

		else if (payType.equalsIgnoreCase("wallet_GETWALLET_Trnx")) {
			String transactionType = jsonPathEvaluator.getString("transactionType");
			String description = jsonPathEvaluator.getString("description");
			if(!transactionType.equalsIgnoreCase("CAPTURE")) {
				Reporter.log("transactionType is : "+transactionType);
				Assert.assertTrue(false);
			}
			if(!description.equalsIgnoreCase("Transaction successful")) {
				Reporter.log("description is : "+description);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("wallet_GETWALLET_ALL")) {
			if(!resp.body().asString().contains("3000331040000246")) {
				Reporter.log("3000331040000001 is not displayed ");
				Assert.assertTrue(false);
			}
			/*if(!resp.body().asString().contains("3000331020000001")) {
				Reporter.log("3000331020000001 is not displayed ");
				Assert.assertTrue(false);
			}*/


		}
		else if (payType.equalsIgnoreCase("wallet_GETWALLET_INR")) {
			String walletNumber = jsonPathEvaluator.getString("walletNumber");
			String id = jsonPathEvaluator.getString("id");
			if(!walletNumber.equalsIgnoreCase("3000331040000001")) {
				Reporter.log("transactionType is : "+walletNumber);
				Assert.assertTrue(false);
			}
			if(!id.equalsIgnoreCase("5153572")) {
				Reporter.log("description is : "+id);
				Assert.assertTrue(false);
			}
		}	
		else if (payType.equalsIgnoreCase("wallet_GETWALLET_INR")) {
			String walletNumber = jsonPathEvaluator.getString("walletNumber");
			String id = jsonPathEvaluator.getString("id");
			if(!walletNumber.equalsIgnoreCase("3000331040000001")) {
				Reporter.log("transactionType is : "+walletNumber);
				Assert.assertTrue(false);
			}
			if(!id.equalsIgnoreCase("5153572")) {
				Reporter.log("description is : "+id);
				Assert.assertTrue(false);
			}
		}	
		else if (payType.equalsIgnoreCase("wallet_CASHBACK_DETAILS_WALLETS")) {
			String status = jsonPathEvaluator.getString("status");
			String description = jsonPathEvaluator.getString("description");
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			if(!description.equalsIgnoreCase("Wallet cashback success")) {
				Reporter.log("description is : "+description);
				Assert.assertTrue(false);
			}
		}	
		else if (payType.equalsIgnoreCase("WALLET_CREATE")) {
			String error = jsonPathEvaluator.getString("error");
			String message = jsonPathEvaluator.getString("message");
			if(!error.equalsIgnoreCase("Internal Server Error")) {
				Reporter.log("error is : "+error);
				Assert.assertTrue(false);
			}
			if(!message.equalsIgnoreCase("Wallet already exists for User-Id: 65176051 Currency: AED ")) {
				Reporter.log("message is : "+message);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("GV_GET")) {
			String status = jsonPathEvaluator.getString("status");
			String isExpired = jsonPathEvaluator.getString("isExpired");
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			if(!isExpired.equalsIgnoreCase("false")) {
				Reporter.log("isExpired is : "+isExpired);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("GV_REFUND")) {
			String status = jsonPathEvaluator.getString("status");
			String isExpired = jsonPathEvaluator.getString("isExpired");
			if(!status.equalsIgnoreCase("F")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("GV_CAPTURE")) {
			String description = jsonPathEvaluator.getString("description");
			String status = jsonPathEvaluator.getString("status");
			if(!description.equalsIgnoreCase("Gift voucher redemption success")) {
				Reporter.log("description is : "+description);
				Assert.assertTrue(false);
			}if(!status.equalsIgnoreCase("SUCCESS")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("GV_REFUND2")) {
			String status = jsonPathEvaluator.getString("status");
			String isExpired = jsonPathEvaluator.getString("isExpired");
			if(!status.equalsIgnoreCase("F")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("CreateRecord")) {

			String PayID = resp.asString();
			if(PayID.isEmpty()) {
				Reporter.log("Payment ID is not created");
				Assert.assertTrue(false);
			}
		}else if (payType.equalsIgnoreCase("RORFetch_Pay_BY_ID")) {

			String description = jsonPathEvaluator.getString("description");
			String app_ref1 = jsonPathEvaluator.getString("app_ref1");
			if(!description.equalsIgnoreCase("Payment successful")) {
				Reporter.log("description is : "+description);
				Assert.assertTrue(false);
			}if(!app_ref1.equalsIgnoreCase("Q191204588534")) {
				Reporter.log("app_ref1 is : "+app_ref1);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("RORFetch_Refund_BY_ID")||payType.equalsIgnoreCase("RORFetch_Profile_List")) {

			String message = jsonPathEvaluator.getString("message");
			String status = jsonPathEvaluator.getString("status");
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}if(!message.equalsIgnoreCase("Query successful")) {
				Reporter.log("message is : "+message);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("RORUpdate_Profile_List")) {

			String message = jsonPathEvaluator.getString("message");
			String status = jsonPathEvaluator.getString("status");
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}if(!message.equalsIgnoreCase("Successfully updated")) {
				Reporter.log("message is : "+message);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("RORUpdate_Profile_List")) {

			String id = jsonPathEvaluator.getString("id");
			String status = jsonPathEvaluator.getString("status");
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}if(!id.equalsIgnoreCase("9371271")) {
				Reporter.log("id is : "+id);
				Assert.assertTrue(false);
			}
		}
		else if (payType.equalsIgnoreCase("RORUpdate_Refund_List")) {

			if(!(resp.asString().contains("true"))&&(resp.asString().contains("9371271"))) {
				Reporter.log("Staus is not true");
				Assert.assertTrue(false);
			}/*

			String message = jsonPathEvaluator.getString("message");
			String status = jsonPathEvaluator.getString("status");
			if(!status.equalsIgnoreCase("true")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}if(!message.equalsIgnoreCase("Successfully updated")) {
				Reporter.log("message is : "+message);
				Assert.assertTrue(false);
			}*/
		}
		else if (payType.equalsIgnoreCase("ReportingTS_V3")) {
			if(!(resp.asString().contains("Unit No 001, Ground Floor, DTC Bldg"))) {
				Reporter.log("Unit No 001, Ground Floor, DTC Bldg msg not displayed");
				Assert.assertTrue(false);
			}

		}

		else if (payType.equalsIgnoreCase("CTPAYADD")) {			
			String s2s_url = jsonPathEvaluator.getString("s2s_url");
			String status = jsonPathEvaluator.getString("status");
			if(!status.equalsIgnoreCase("SUCCESS")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}if(!s2s_url.equalsIgnoreCase("wwww.123.com")) {
				Reporter.log("message is : "+s2s_url);
				Assert.assertTrue(false);
			}
		}
		else if(payType.equalsIgnoreCase("ROR_Create_ProfileLists")){
			String message = jsonPathEvaluator.getString("message");
			String status = jsonPathEvaluator.getString("status");
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}if(!message.contains("Profile created with id")) {
				Reporter.log("message is : "+status);
				Assert.assertTrue(false);
			}

		}

		else if(payType.equalsIgnoreCase("RORUpdate_GW_Status")){
			String currentStatus = jsonPathEvaluator.getString("currentStatus");
			String status = jsonPathEvaluator.getString("status");
			if(!status.equalsIgnoreCase("I")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			if(!currentStatus.contains("P")) {
				Reporter.log("currentStatus is : "+currentStatus);
				Assert.assertTrue(false);
			}

		}

		else if(payType.equalsIgnoreCase("RORCreate_Payment")){
			String TripID = jsonPathEvaluator.getString("Q191109570525");

			if(TripID.isEmpty()) {
				Reporter.log("TripID is : "+TripID);
				Assert.assertTrue(false);
			}

		}

		else if(payType.equalsIgnoreCase("RORCreate_ProfileList")){
			String message = jsonPathEvaluator.getString("message");
			String status = jsonPathEvaluator.getString("status");
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			if(!message.contains("Profile created with id")) {
				Reporter.log("message is : "+message);
				Assert.assertTrue(false);
			}

		}

		else if(payType.equalsIgnoreCase("ROR_Search_ProfileList")){
			if(!resp.body().asString().contains("user_id")) {
				Reporter.log("Card deletion failed");
				Assert.assertTrue(false);
			}



		}

		else if(payType.equalsIgnoreCase("ROR_Create_Refunds")){
			String message = jsonPathEvaluator.getString("message");
			String status = jsonPathEvaluator.getString("status");
			if(!status.equalsIgnoreCase("S")) {
				Reporter.log("status is : "+status);
				Assert.assertTrue(false);
			}
			if(!message.contains("Refund Initiated")) {
				Reporter.log("message is : "+message);
				Assert.assertTrue(false);
			}			
		}

		else if(payType.equalsIgnoreCase("ROR_Recon")){
			if(!resp.body().asString().contains("payment_id")) {
				Reporter.log("refundAmount");
				Assert.assertTrue(false);
			}
			if(!resp.body().asString().contains("status")) {
				Reporter.log("status D");
				Assert.assertTrue(false);
			}			
		}
		else if(payType.equalsIgnoreCase("NavisonCC")){
			String trip_ref = jsonPathEvaluator.getString("trip_ref");
			String credential_name = jsonPathEvaluator.getString("credential_name");
			if(!credential_name.equalsIgnoreCase("IN_PAYU_STAGING")) {
				Reporter.log("credential_name is : "+credential_name);
				Assert.assertTrue(false);
			}
			if(!trip_ref.contains("Q200117692102")) {
				Reporter.log("trip_ref is : "+trip_ref);
				Assert.assertTrue(false);
			}	
		}
		else if(payType.equalsIgnoreCase("NavisonAir")){
			String trip_ref = jsonPathEvaluator.getString("trip_ref");
			String credential_name = jsonPathEvaluator.getString("credential_name");
			if(!credential_name.equalsIgnoreCase("RAZORPAY")) {
				Reporter.log("credential_name is : "+credential_name);
				Assert.assertTrue(false);
			}
			if(!trip_ref.contains("Q191226667766")) {
				Reporter.log("trip_ref is : "+trip_ref);
				Assert.assertTrue(false);
			}	
		}
		else if(payType.equalsIgnoreCase("ROR_MultiSearch")){

			if(!resp.body().asString().contains("Q200123741386")) {
				Reporter.log("TripID");
				Assert.assertTrue(false);
			}	
		}
		else if(payType.equalsIgnoreCase("ROR_WalletFetch")){

			if(!resp.body().asString().contains("5153574")) {
				Reporter.log("5153574 is not displayed");
				Assert.assertTrue(false);
			}	
		}
		else if(payType.equalsIgnoreCase("ROR_MultiSearch_Reads")){

			if(!resp.body().asString().contains("43622220")) {
				Reporter.log("43622220 is not displayed");
				Assert.assertTrue(false);
			}	
		}

		else if(payType.equalsIgnoreCase("ROR_MultiSearch_TripRef_Reads")){

			if(!resp.body().asString().contains("43179370")) {
				Reporter.log("43179370 is not displayed");
				Assert.assertTrue(false);
			}	
		}

		else if(payType.equalsIgnoreCase("ROR_WalletTrnx_Reads")){

			if(!resp.body().asString().contains("5154746")) {
				Reporter.log("5154746 is not displayed");
				Assert.assertTrue(false);
			}	
		}

		else if(payType.equalsIgnoreCase("ROR_WalletGet_Reads")){

			if(!resp.body().asString().contains("5789285")) {
				Reporter.log("5789285 is not displayed");
				Assert.assertTrue(false);
			}	
		}

		else if(payType.equalsIgnoreCase("ROR_GV_Details")){

			if(!resp.body().asString().contains("3000331039771395")) {
				Reporter.log("3000331039771395 is not displayed");
				Assert.assertTrue(false);
			}	
		}
		
		else if(payType.equalsIgnoreCase("ROR_Refund_Details")){

			if(!resp.body().asString().contains("Recon Req")) {
				Reporter.log("Recon Re is not displayed");
				Assert.assertTrue(false);
			}	
		}
		else if(payType.equalsIgnoreCase("ReportingTS_Archived_V3_False")){
					if(!resp.body().asString().contains("40294932")) {
						Reporter.log("40294932 is not displayed");
						Assert.assertTrue(false);
					}
		}
		else if(payType.equalsIgnoreCase("ReportingTS_Archived_V3_True")){
			if(!resp.body().asString().contains("40294932")) {
				Reporter.log("40294932 is not displayed");
				//Assert.assertTrue(false);
			}
}
		
		
		return resp;	
	}

	public Response validation_Prod(String payType, Response resp) {
		Reporter.log("Response body "+payType +" : "+ resp.body().asString());
		//System.out.println("Response body "+payType +" : "+ resp.body().asString());
		int statusCode = resp.getStatusCode();	
		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();

		if(statusCode!=200) {
			Assert.assertTrue(false);
			Reporter.log("Response Status code is not matching");
		}

		else {
			Reporter.log("Response status code is matching");
		}
		if(payType.equalsIgnoreCase("Rwd_PayBack_Mobile")) {
			String status = jsonPathEvaluator.getString("status");
			String isAccountLinked = jsonPathEvaluator.getString("isAccountLinked");
			Reporter.log("status " +status);
			Reporter.log("isAccountLinked " +isAccountLinked);
			if(!status.equals("S")) {
				Assert.assertTrue(false);
			}  
			if(!isAccountLinked.equals("true")) {
				Assert.assertTrue(false);
			}
		}
		else if(payType.equalsIgnoreCase("Rwd_ADCB_Balance")) {
			String status = jsonPathEvaluator.getString("status");
			String description = jsonPathEvaluator.getString("description");
			Reporter.log("status " +status);
			Reporter.log("description " +description);
			if(!status.equals("F")) {
				Assert.assertTrue(false);
			}  
			if(!description.contains("You have provided incorrect card details")) {
				Assert.assertTrue(false);
			}
		}
		else if(payType.equalsIgnoreCase("GetPaymentStatus")) {
			boolean isMatching = false;
			List<HashMap<String,Object>> payStatusList = new ArrayList<>();
			payStatusList = jsonPathEvaluator.get("bookPaymentResponse");
			for(HashMap<String,Object> m : payStatusList) {
				if(m.get("status").equals("S") && m.get("description").equals("Payment successful")) {
					isMatching = true;
					Reporter.log("Payment Status Is Successful");
				}

				else {
					Reporter.log("Payment Status Is Not Successful");
				}
			}

			Assert.assertTrue(isMatching);
		}

		else if(payType.equals("ThirdPartyWallets")) {
			boolean isMatching = false;
			List<HashMap<String,Object>> tpStatusList = new ArrayList<>();
			tpStatusList = (List<HashMap<String, Object>>) resp;
			for(HashMap<String,Object> m : tpStatusList) {
				if(m.get("status").equals("D") || m.get("status").equals("A")) {
					isMatching = true;
					Reporter.log("Third Party Wllet Status Is Successful");
				}

				else {
					Reporter.log("Payment Status Is Not Successful");
				}
			}

			Assert.assertTrue(isMatching);

		}
		else if(payType.equalsIgnoreCase("Wallet_Get_Currency")) {
			String str_resp = resp.body().asString();
			if(!str_resp.contains("INR")) {
				Assert.assertTrue(false);
			}  
			if(!str_resp.contains("1001370018436908")) {
				Assert.assertTrue(false);
			} 
		}
		else if(payType.equalsIgnoreCase("Pay_Get_TW_Wallets")) {
			String str_resp = resp.body().asString();
			if(!str_resp.contains("AmazonPay")) {
				Assert.assertTrue(false);
			}  	

		}
		else if(payType.equalsIgnoreCase("Promo_Get_Group")) {
			String str_resp = resp.body().asString();
			if(!str_resp.contains("AIR_BOOKING")) {
				Assert.assertTrue(false);
			}			 
		}
		else if(payType.equalsIgnoreCase("Bin_Get_CardInfo")) {
			String cardType = jsonPathEvaluator.getString("cardType");
			String bank = jsonPathEvaluator.getString("bank");
			Reporter.log("bank " +bank);
			Reporter.log("cardType " +cardType);
			if(!cardType.equals("CREDIT")) {
				Assert.assertTrue(false);
			}  
			if(!bank.equals("HDFC BANK, LTD.")) {
				Assert.assertTrue(false);
			}
		}



		return resp;
	}


	public String generateTripRef() {
		int randomNumber= PaymentUI_CommonUtilities.generateFiveDigitRandomNumber();
		String randNumber= Integer.toString(randomNumber);
		tripRef= "Q201" + randNumber;
		return tripRef;
	}
	
	public String getPayUI(String PayType, String Domain) throws Exception {			
		tripRef = getNewDate_TripID();
		resp = payUIget(PayType, Domain, tripRef);
		if(Domain.equalsIgnoreCase("AE")) {
			qaurl=qaurlae;
		}else if(Domain.equalsIgnoreCase("BH")) {
			qaurl=qaurlbh;
		}else if(Domain.equalsIgnoreCase("KW")) { 
			qaurl=qaurlkw;
		}else if(Domain.equalsIgnoreCase("OM")) {
			qaurl=qaurlom;
		}else if(Domain.equalsIgnoreCase("QA")) {
			qaurl=qaurlqa;
		}else if(Domain.equalsIgnoreCase("SA")) {
			qaurl=qaurlsa;
		}else if(Domain.equalsIgnoreCase("US")) {
			qaurl=qaurlus;
		}
		Url = qaurl+ fetchPaymentURL(resp);
		Reporter.log("Payment URL : " +Url);
		String TripID = fetchPaymentTripID(resp);
		Reporter.log("TripID : "+TripID);
			
		if(!Url.contains("pay")) {
			Reporter.log("Pay URL is not created - Failing the script");
			Assert.assertTrue(false);			
		}
		if(textPresent(driver, "Oops, Something went wrong", 5)) {
			Reporter.log("Oops, Something went wrong");
			Assert.assertTrue(false);
		}
		
		return Url;
	}
	
	public String fetchPaymentURL(Response resp){
		String payurl="";
		JsonPath jsonPathEvaluator = resp.jsonPath();
		payurl = jsonPathEvaluator.getString("payment_url");
		if(payurl.equals(null)) {
			Reporter.log("Pay URL is not created - Failing the script");
			Assert.assertTrue(false);			
		}
		return payurl;
	}
	
	public String fetchPaymentTripID(Response resp){
		String tripID="";
		JsonPath jsonPathEvaluator = resp.jsonPath();
		tripID = jsonPathEvaluator.getString("trip_ref");
		return tripID;
	}

	public Response validation_ROR(String payType, Response resp){
		Reporter.log("Response body "+payType +" : "+ resp.body().asString());
		System.out.println("Response body "+payType +" : "+ resp.body().asString());
		int statusCode = resp.getStatusCode();	
		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();		
		if(statusCode!=200) {
				Assert.assertTrue(false);
		}
		
		
		if(payType.equalsIgnoreCase("ReportingPaginate")) {
			String str_resp = resp.body().asString();			
			if(!str_resp.contains("page_size")) {
				Assert.assertTrue(false);
			}			
		}
		
		else if(payType.equalsIgnoreCase("BasicValidaton")) {
			String status = jsonPathEvaluator.getString("status");
			String description = jsonPathEvaluator.getString("description");
			if(!status.equals("S")) {
				Assert.assertTrue(false);
			}  
			if(!description.equals("Successfully fetched")) {
				Assert.assertTrue(false);
			}
		}
		
		
		
		else if(payType.equalsIgnoreCase("ReportingRefundUploadFetch")) {
			String str_resp = resp.body().asString();			
			if(!str_resp.contains("refund_amount")) {
				Assert.assertTrue(false);
			}		
		}
			
		else if(payType.equalsIgnoreCase("ReportingPaginate")) {
			String redirection = jsonPathEvaluator.getString("redirection_required");
			String Response = jsonPathEvaluator.getString("response_message");
			Reporter.log("redirection required " +redirection);
			Reporter.log("Response " +Response);
			if(!redirection.equals("[Y]")) {
				Assert.assertTrue(false);
			}  
			if(!Response.equals("")) {
				Assert.assertTrue(false);
			}
		}
		return resp;
	}
	
	public String paygetAuth() throws Exception {
		/*int randomNumber= PaymentUI_CommonUtilities.generateFiveDigitRandomNumber();
		String randNumber= Integer.toString(randomNumber);
		String tripRef= "Q201" + randNumber;
		*/
		String URL = "/paymentservice/test/getHash?tripRef="+tripRef+"&itineraryId=NI685a33347e-464c-4a27-86c9-200710101330&amount=500";
		RestAssured.baseURI = urlPay;
		String endPoint = URL;		
		Response response = null;
		response = RestAssured.given().when().log().all().get(endPoint);
		String payAuth = response.asString();
		return payAuth;
	}
	
	public String paygetAuth_FullGV() throws Exception {
		/*int randomNumber= PaymentUI_CommonUtilities.generateFiveDigitRandomNumber();
		String randNumber= Integer.toString(randomNumber);
		String tripRef= "Q201" + randNumber;
		*/
		String URL = "/paymentservice/test/getHash?tripRef="+tripRef+"&itineraryId=NI685a33347e-464c-4a27-86c9-200710101330&amount=0";
		RestAssured.baseURI = urlPay;
		String endPoint = URL;		
		Response response = null;
		response = RestAssured.given().when().log().all().get(endPoint);
		String payAuth = response.asString();
		return payAuth;
	}
		
	
	
	public Response payUIget(String payType, String PayType1, String tripRef) throws Exception {
		String endPoint = null;
		String params = null; 
		HashMap<String, Object> headers	= new HashMap<>();
		HashMap<String, Object> cookies	= new HashMap<>();
		if(common.value("payAuth").contains("false")||payType.contains("AirAmend")) {
			headers = headersForms();
			Reporter.log("Pay Auth header not added");
		} else headers = headersFormspay();			
		cookies = addCookies();
		Response response = null;
		if(payType.equalsIgnoreCase("BookApp/GetPay")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			int randomNumber= PaymentUI_CommonUtilities.generateFiveDigitRandomNumber();
			String randNumber= Integer.toString(randomNumber);
			//String appRef= "Q201" + randNumber;
			//System.out.println("Appref1 "+tripRef);
			params = "{\"train_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Ms\",\"first_name\":\"Test\",\"last_name\":\"Test\"}],\"itinerary_details\":[{\"from_station_code\":\"SBC\",\"to_station_code\":\"MYS\",\"from_station_name\":\"KSR BENGALURU\",\"to_station_name\":\"MYSURU JN\",\"train_name\":\"BSB MYS EXP\",\"train_number\":\"16230\",\"departure_time\":\"2019-11-12T00:15:00\",\"arrival_time\":\"2019-11-12T02:45:00\",\"booking_class\":\"AC 2 Tier(2A)\",\"updated_availability\":\"AVAILABLE-0119\",\"quota\":\"General\",\"seatStatus\":true}],\"pricing_details\":[{\"other_railway_charges\":119.4,\"agent_service_charge\":40,\"total\":775.4,\"insuranceCharge\":0,\"currency\":\"INR\",\"pax_pay_info\":[{\"base_fare\":616,\"pax_count\":1,\"pax_type\":\"ADULT\"}]}],\"transaction_fee_details\":{\"CC\":{\"DEFAULT\":1.8},\"DC\":{\"DEFAULT\":1},\"NB\":{\"1\":1.1,\"2\":1.2,\"3\":1.1,\"23\":1.1,\"DEFAULT\":1.35},\"KC\":{\"DEFAULT\":0},\"DA\":{\"DEFAULT\":0},\"TW\":{\"DEFAULT\":1},\"UP\":{\"DEFAULT\":1}}},\"itinerary_id\":\"f25db800de1e0137664316217d236675\",\"ttl\":3600,\"trip_id\":45134538,\"app_ref1\":\"" + tripRef +"\",\"app_ref2\":74282510,\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":1212121212,\"landline\":1212121212,\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"payment\",\"username\":\"cltppayment@gmail.com\"},\"product_type\":\"TRAIN\",\"currency\":\"INR\",\"order_info1\":\"16230/SBC/MYS/2019111200:15:00\",\"order_info2\":\"Test Test\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"64891349\",\"email_id\":\"cltppayment@gmail.com\",\"d_plus_x_in_hours\":273,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/trains/itinerary/f25db800de1e0137664316217d236675/process_payment\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://trains-book-nget.cltp.com:9001/r3/trains/itinerary/f25db800de1e0137664316217d236675/book_internal\",\"params\":null},\"payment_category\":\"B\"}";;
			
		}
		else if(payType.equalsIgnoreCase("BookApp/GetPayWithCookie")){

			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = "{\"train_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Ms\",\"first_name\":\"Test\",\"last_name\":\"Test\"}],\"itinerary_details\":[{\"from_station_code\":\"SBC\",\"to_station_code\":\"MYS\",\"from_station_name\":\"KSR BENGALURU\",\"to_station_name\":\"MYSURU JN\",\"train_name\":\"BSB MYS EXP\",\"train_number\":\"16230\",\"departure_time\":\"2019-11-12T00:15:00\",\"arrival_time\":\"2019-11-12T02:45:00\",\"booking_class\":\"AC 2 Tier(2A)\",\"updated_availability\":\"AVAILABLE-0119\",\"quota\":\"General\",\"seatStatus\":true}],\"pricing_details\":[{\"other_railway_charges\":119.4,\"agent_service_charge\":40,\"total\":775.4,\"insuranceCharge\":0,\"currency\":\"INR\",\"pax_pay_info\":[{\"base_fare\":616,\"pax_count\":1,\"pax_type\":\"ADULT\"}]}],\"transaction_fee_details\":{\"CC\":{\"DEFAULT\":1.8},\"DC\":{\"DEFAULT\":1},\"NB\":{\"1\":1.1,\"2\":1.2,\"3\":1.1,\"23\":1.1,\"DEFAULT\":1.35},\"KC\":{\"DEFAULT\":0},\"DA\":{\"DEFAULT\":0},\"TW\":{\"DEFAULT\":1},\"UP\":{\"DEFAULT\":1}}},\"itinerary_id\":\"f25db800de1e0137664316217d236675\",\"ttl\":3600,\"trip_id\":45134538,\"app_ref1\":\"Q19020128945\",\"app_ref2\":74282510,\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":1212121212,\"landline\":1212121212,\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"payment\",\"username\":\"cltppayment@gmail.com\"},\"product_type\":\"TRAIN\",\"currency\":\"INR\",\"order_info1\":\"16230/SBC/MYS/2019111200:15:00\",\"order_info2\":\"Test Test\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"64891349\",\"email_id\":\"cltppayment@gmail.com\",\"d_plus_x_in_hours\":273,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/trains/itinerary/f25db800de1e0137664316217d236675/process_payment\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://trains-book-nget.cltp.com:9001/r3/trains/itinerary/f25db800de1e0137664316217d236675/book_internal\",\"params\":null},\"payment_category\":\"B\"}";;
			
		}
		
		else if(payType.equalsIgnoreCase("Air")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air1+tripRef+params_PayUI_Air2;
		}
		else if(payType.equalsIgnoreCase("AirCoupon")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air_Coupon1+tripRef+params_PayUI_Air_Coupon2;
		}
		else if(payType.equalsIgnoreCase("AirGV")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air_GV1+tripRef+params_PayUI_Air_GV2;
		}
		else if(payType.equalsIgnoreCase("AirGVFull")){
			headers = headersFormspay_FullGV();
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air_GV1+tripRef+params_PayUI_Air_GV2_Full;
		}
		else if(payType.equalsIgnoreCase("AirRP")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air_RP1+tripRef+params_PayUI_Air_RP2;
		}
		else if(payType.equalsIgnoreCase("AirAmend")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = param_Air_Amendment_API1+tripRef+param_Air_Amendment_API2;
		}
		else if(payType.equalsIgnoreCase("AirAE")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air1+tripRef+params_PayUI_Air_AE;
		}else if(payType.equalsIgnoreCase("AirAEAR")){ // Arabic
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air1+tripRef+params_PayUI_Air_AE_AR;
		}else if(payType.equalsIgnoreCase("AirBH")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air1+tripRef+params_PayUI_Air_BH;
		}else if(payType.equalsIgnoreCase("AirKW")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air1+tripRef+params_PayUI_Air_KW;
		}else if(payType.equalsIgnoreCase("AirSA")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air1+tripRef+params_PayUI_Air_SA;
		}else if(payType.equalsIgnoreCase("AirOM")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air1+tripRef+params_PayUI_Air_OM;
		}else if(payType.equalsIgnoreCase("AirQA")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air1+tripRef+params_PayUI_Air_QA;
		}else if(payType.equalsIgnoreCase("AirUS")){
			RestAssured.baseURI = urlPay;
			endPoint = endPointgetPay;	
			params = params_PayUI_Air1+tripRef+params_PayUI_Air_US;
		}
			response = RestAssured.given().
				when().log().all().body(params).headers(headers).post(endPoint);
			//Reporter.log("URL : "+urlPay+endPoint);
			//Reporter.log("Params : "+params);
			return response;
	}
	
	public Response rearchCtPay(String payType, String payType1) throws Exception{
		RestAssured.baseURI =urlCTPay;
		Response request = null;		
		String params = null;
		String url=null;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();

		if(payType.equals("UPDATE")) {
			url = urlCTPay_UpdateClient;
			params = paramsctPay_UpdateClient;		
			request = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(url);
		}
		else if(payType.equals("ADD")) {
			url =urlCTPay_ADD ; 
			String ranno = getRandomNos(4);

			String Date = getDateTime(1, "ssmm");

			params = paramsctPay_ADD+Date+paramsctPay_ADD1;		
			request = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(url);
		}
		else if(payType.equals("GETURL")) {
			url =urlCTPay_Get ; 
			params = paramsctPay_GetURL;		
			request = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(url);
		}
		else if(payType.equals("STATUS")) {
			url =urlCTPay_Status;
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
		}
		else if(payType.equals("CreateURL")) {
			url =urlCTPay_CreateURL ; 
			params = paramsctPay_CreateURL;		
			request = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(url);
		}
		Reporter.log(urlCTPay);
		Reporter.log("Params : "+params);
		Reporter.log(url);
		return request;
	}	

	public Response refund(String payType, String RefundID) {
		RestAssured.baseURI = urlPay;
		String endPoint = null;
		HashMap<String, Object> headers	= new HashMap<>();
		headers = headersForms();
		Response response = null;
		if(payType.equalsIgnoreCase("refund")) {
			endPoint = urlPay+url_RefundNEW_EndPoint+RefundID;
			if(common.value("PaymentServer").equalsIgnoreCase("old")) {
				endPoint = "http://paymentservice.cltp.com:9001/paymentservice/service/refund?refundids="+RefundID;
			}

			Reporter.log(endPoint);
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}
		Reporter.log(urlPay);
		Reporter.log(endPoint);
		return response;
	}

	public Response walletEndPoints(String payType, String cardId) {
		RestAssured.baseURI = urlrewards_validate;
		String endPoint = null;
		HashMap<String, Object> headers	= new HashMap<>();
		headers = headersForms_wallet();
		Response response = null;
		String params = null;
		if(payType.equalsIgnoreCase("wallet_getCards")) {
			endPoint =url_walletGetCards;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}
		if(payType.equalsIgnoreCase("wallet_validateCards")) {
			endPoint =url_walletValidateCards;
			response = RestAssured.given().
					when().log().all().headers(headers).post(endPoint);
		}
		if(payType.equalsIgnoreCase("wallet_storeCards")) {
			endPoint =url_walletStoreCards;
			response = RestAssured.given().
					when().log().all().headers(headers).post(endPoint);
		}
		if(payType.equalsIgnoreCase("wallet_deleteCards")) {
			endPoint =String.format(url_walletDeleteCards, cardId);
			response = RestAssured.given().
					when().log().all().headers(headers).post(endPoint);
		}
		Reporter.log(urlrewards_validate);
		Reporter.log("Params : "+params);
		Reporter.log(endPoint);
		return response;
	}

	public Response expressWay(String payType, String PayType1) {
		RestAssured.baseURI = urlPay;
		String endPoint = null;
		String params = null;
		HashMap<String, Object> headers	= new HashMap<>();
		headers = headersForms();
		Response response = null;
		if(payType.equalsIgnoreCase("userDetails")) {
			endPoint = urlEW_UserDetails;	
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}
		else if(payType.equalsIgnoreCase("getOutstanding")) {
			endPoint = urlEW_GetOutstandingAmt;	
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}			
		else if(payType.equalsIgnoreCase("otpIN")) {
			endPoint = urlEW_OptIn;	
			response = RestAssured.given().
					when().log().all().headers(headers).put(endPoint);
		}
		else if(payType.equalsIgnoreCase("payV3")) {
			endPoint = urlEW_PayV3;	
			params = paramsEW_PayV3;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		else if(payType.equalsIgnoreCase("payMultiV3")) {
			endPoint = urlEW_PayV3;	
			params = paramsEW_PayMultiV3;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		else if(payType.equalsIgnoreCase("pay")) {
			endPoint = urlEW_Pay;	
			params = paramsEW_Pay;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		else if(payType.equalsIgnoreCase("Autodebit")) {
			endPoint = urlEW_AutoDebit;	
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}
		else if(payType.equalsIgnoreCase("payMulti")) {
			endPoint = urlEW_Pay;	
			params = paramsEW_PayMulti;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}

		else if(payType.equalsIgnoreCase("validate")) {
			endPoint = urlEW_Validate;	
			params = paramsEW_Validate;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}	

		else if(payType.equalsIgnoreCase("EW_AddAmt")) {
			endPoint = urlEW_AddAmt;	
			params = paramsEW_AddAmt;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).put(endPoint);
		}
		else if(payType.equalsIgnoreCase("refund")) {
			endPoint = urlEW_Refund;	
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}
		else if(payType.equalsIgnoreCase("EW_Summary")) {
			endPoint = urlEW_Summary;	
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}

		Reporter.log(urlPay);
		Reporter.log("Params : "+params);
		Reporter.log(endPoint);
		return response;

	}

	public Response adcb (String payType, String PayType1) throws Exception {
		//RestAssured.baseURI = urladcb_validat;
		String endPoint = null;
		String params = null; 
		HashMap<String, Object> headers	= new HashMap<>();
		headers = headersForms();
		Response response = null;
		String trackid = PayType1;
		//String track = "CLR"+ getRandomNo(8);

		if(payType.equalsIgnoreCase("checkBalance")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urladcb_checkbalance;	
			params = Paramsadcb_checkbalance1+trackid+Paramsadcb_checkbalance2;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		else if(payType.equalsIgnoreCase("sendOTP")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urladcb_sendOTP;	
			params = Paramsadcb_sendOTP1+trackid+Paramsadcb_sendOTP2;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}			
		else if(payType.equalsIgnoreCase("validate")) {
			RestAssured.baseURI = urlrewards_validate;
			endPoint = urladcb_validate;	
			params = Paramsadcb_validate1+trackid+Paramsadcb_validate2;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		else if(payType.equalsIgnoreCase("pay")) {
			RestAssured.baseURI = urlrewards_validate;
			endPoint = urladcb_pay;	
			params = Paramsadcb_pay1+trackid+Paramsadcb_pay2;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		Reporter.log(urlRewards);
		Reporter.log("Params : "+params);
		Reporter.log(endPoint);
		return response;

	}

	public Response reward(String payType, String PayType1) throws Exception {
		String endPoint = null;
		String params = null; 
		HashMap<String, Object> headers	= new HashMap<>();
		headers = headersForms();
		Response response = null;
		String trackid = PayType1;
		payment_id.add(PlatformCommonUtil.generatePaymentID());
		trk_id.add("pay12341608"+PlatformCommonUtil.generateTrackID());
		if(payType.equalsIgnoreCase("ADCB_CheckBalance")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urladcb_checkbalance;	
			params = Paramsadcb_checkbalance1+trackid+Paramsadcb_checkbalance2;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		else if(payType.equalsIgnoreCase("ADCB_sendOTP")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urladcb_sendOTP;	
			params = Paramsadcb_sendOTP1+trackid+Paramsadcb_sendOTP2;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}			
		else if(payType.equalsIgnoreCase("ADCB_validate")) {
			RestAssured.baseURI = urlrewards_validate;
			endPoint = urladcb_validate;	
			params = Paramsadcb_validate1+trackid+Paramsadcb_validate2;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		else if(payType.equalsIgnoreCase("ADCB_pay")) {
			RestAssured.baseURI = urlrewards_validate;
			endPoint = urladcb_pay;	
			params = Paramsadcb_pay1+trackid+Paramsadcb_pay2;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}

		else if(payType.equalsIgnoreCase("PAYBACK_CheckBalance")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urlreward_checkbalance;
			params = ParamsPayBackCheckBalance;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		else if(payType.equalsIgnoreCase("PAYBACK_CheckBalance_card")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urlreward_checkbalance;
			params = ParamsPayBackCheckBalance_card;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}

		else if(payType.equalsIgnoreCase("PAYBACK_Validate")) {
			RestAssured.baseURI = urlrewards_validate;
			endPoint = urlreward_Validate;
			params = ParamsPayBack_Vaidate1;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}

		else if(payType.equalsIgnoreCase("PAYBACK_CheckEarnPoints")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urlreward_PayCheckEarnPoints; 
			params = ParamsPayBack_CheckEarnPoints;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}

		else if(payType.equalsIgnoreCase("PAYBACK_CheckMobileLinked")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urlreward_payback_CheckMobile_Linked;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}

		else if(payType.equalsIgnoreCase("PAYBACK_CheckCardLinked")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urlreward_payback_CheckCard_Linked;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}


		else if(payType.equalsIgnoreCase("PAYBACK_Pay")) {
			RestAssured.baseURI = urlrewards_validate;
			endPoint = urlreward_payback_pay;
			params = ParamsPayBack_Pay+trackid+ParamsPayBack_Pay1;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
				/*	else if(payType.equalsIgnoreCase("PAYBACK_Pay_WT")) {
						Random rand = new Random();
						int n = rand.nextInt(9999999);
						String trackid1 = Integer.toString(n);
						String track = "CLRTRP"+ trackid1;
						
						RestAssured.baseURI = urlrewards_payURI;
						endPoint = urlreward_payback_pay;
						params = ParamsPayBack_Pay+track+ParamsPayBack_PayWT;
						response = RestAssured.given().
								when().log().all().body(params).headers(headers).post(endPoint);
					}*/
		
		
		else if(payType.equalsIgnoreCase("PAYBACK_Pay_WT_MultiGV")) {
			Random rand = new Random();
			int n = rand.nextInt(9999999);
			String trackid1 = Integer.toString(n);
			String track = "CLRTRPG"+ trackid1;

			RestAssured.baseURI = urlrewards_validate;
			endPoint = urlreward_payback_pay;
			params = ParamsPayBack_Pay+track+ParamsPayBack_PayWTMulti+ParamsPayBack_PayWTMulti1;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}

		else if(payType.equalsIgnoreCase("PAYBACK_Pay_WT_MultiGV_CC")) {
			Random rand = new Random();
			int n = rand.nextInt(9999999);
			String trackid1 = Integer.toString(n);
			String track = "CLRTRPM"+ trackid1;

			RestAssured.baseURI = urlPay;
			endPoint = urlreward_payback_pay;
			params = ParamsPayBack_Pay+track+ParamsPayBack_PayCC;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}

		else if(payType.equalsIgnoreCase("PAYBACK_Earn")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urlreward_payback_earn;
			String track_id = trk_id.get(0);
			params = ParamsPayBack_Earn+track_id+ParamsPayBack_Earn1;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}	

		else if(payType.equalsIgnoreCase("PAYBACK_RedeemPoints")){
			RestAssured.baseURI = urlRewards;
			endPoint = urlreward_RewardRedeem;
			int rand_num= PlatformCommonUtil.getRandomNumber();
			String random_num = Integer.toString(rand_num);
			String track_id = "pay123419823" + random_num;
			int payment_id_redeem = payment_id.get(0);
			params = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"REDEEM\",\"paymentId\": " +payment_id_redeem+ ",\"trackId\": \"" + track_id + "\","+ "\"amount\":1,\"currency\":\"INR\",\"params\":{\"mobile\":\"9986696785\",\"tripRef\":\"Q191014530822\",\"pin\":\"2458\"}}";

			System.out.println("params : "+params);

			Reporter.log("params : "+params);
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}

		else if(payType.equalsIgnoreCase("PAYBACK_RefundPoints")){
			RestAssured.baseURI = urlRewards;
			endPoint = url_reward_RewardRefund;
			int rand_num= PlatformCommonUtil.getRandomNumber();
			String random_num = Integer.toString(rand_num);
			int uid_refund = 9331486;
			String str_uid = Integer.toString(uid_refund);
			String str_uid_new = str_uid + random_num;
			long uid = Long.parseLong(str_uid_new);
			int payment_id_refund = payment_id.get(0);
			params = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"REFUND\",\"paymentId\": " +payment_id_refund+ ",\"uid\": " + uid + ","+ "\"amount\":1,\"currency\":\"INR\",\"params\":{\"customerName\":\"test\",\"tripRef\":\"Q191014530822\"}}";
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);

		}

		else if(payType.equalsIgnoreCase("PAYBACK_Forgotpassword")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urlreward_payback_ForgotPassword;
			params = ParamsPayBack_ForgotPassword;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		else if(payType.equalsIgnoreCase("PAYBACK_Reverseearn")) {
			RestAssured.baseURI = urlRewards;
			endPoint = urlreward_payback_reverseearn;
			String track_id = trk_id.get(0);
			params = ParamsPayBack_reverseearn+track_id+ParamsPayBack_reverseearn1;
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		Reporter.log(urlRewards);
		Reporter.log("Params : "+params);
		Reporter.log(endPoint);
		return response;			
	}

	public Response refunds (String payType, String PayType1, String tripid, String amount, Boolean isFullWalletRefund, String status) {
		RestAssured.baseURI = urlPay;
		String endPoint = null;
		String params = null; 
		HashMap<String, Object> headers	= new HashMap<>();
		headers = headersForms();
		Response response = null;

		if(payType.equalsIgnoreCase("refunds_createrefund")) {
			String txnid = PayType1;
			endPoint = url_createrefundentry;
			params = String.format(Params_Createrefund,isFullWalletRefund,tripid,amount,txnid);			
			Reporter.log(endPoint);
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		if(payType.equalsIgnoreCase("refunds_updatestatus")) {
			String refundid = PayType1;
			endPoint = url_refundupdatestatus;
			params = String.format(Params_Updatestatus, refundid);
			Reporter.log(endPoint);
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		if(payType.equalsIgnoreCase("refunds_updatepgtxnid")) {
			Random rand = new Random();
			int n = rand.nextInt(9999999);
			String pgtxnid = Integer.toString(n);
			String refundid = PayType1;

			endPoint = url_refundpgtxnid;
			params = String.format(Params_Updatepgtxnid, refundid,pgtxnid );
			Reporter.log(endPoint);
			response = RestAssured.given().
					when().log().all().body(params).headers(headers).post(endPoint);
		}
		if(payType.equalsIgnoreCase("refunds_getrecord")) {
			String refundid = PayType1;
			endPoint = url_refundgetrecord+refundid;
			Reporter.log(endPoint);
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}

		Reporter.log(urlPay);						
		Reporter.log(endPoint);			
		return response;
	}

	public Response bin_manager (String payType, String PayType1) {
		RestAssured.baseURI = url_Binmanager;
		String endPoint = null;
		HashMap<String, Object> headers	= new HashMap<>();
		headers = headersForms();
		Response response = null;
		if(payType.equalsIgnoreCase("Binmanager")) {
			endPoint = url_Binmanager;

			Reporter.log(endPoint);
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}
		Reporter.log(url_Binmanager);						
		Reporter.log(endPoint);		
		return response;
	}

	public Response Reporting (String payType, String PayType1) {
		RestAssured.baseURI = urlReporting;

		Reporter.log(urlReporting);	
		String endPoint = null;
		HashMap<String, Object> headers	= new HashMap<>();
		headers = headersForms();
		Response response = null;
		if(payType.equalsIgnoreCase("Reporting")) {
			endPoint = url_Reportingendpoint;
			Reporter.log(endPoint);
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}
		else if(payType.equalsIgnoreCase("ReportingPAYID")) {
			endPoint = url_ReportingPaymentID;

			Reporter.log(endPoint);
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}
		else if(payType.equalsIgnoreCase("ReportingTS_V3")) {
			RestAssured.baseURI =urlReportingTS;

			Reporter.log(urlReportingTS);	
			endPoint = url_ReportingTS_V3;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	

		else if(payType.equalsIgnoreCase("ReportingTS_Archived_V3_False")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReportingTS);	
			endPoint = url_ReportingTS_Archived_V3_False;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		else if(payType.equalsIgnoreCase("ReportingTS_Archived_V3_True")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReportingTS);	
			endPoint = url_ReportingTS_Archived_V3_True;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
				
		
		else if(payType.equalsIgnoreCase("ReportingPaginate")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReporting);	
			endPoint = url_ReportingPaginate;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		
		else if(payType.equalsIgnoreCase("ReportingPaginatePayType")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReporting);	
	
			endPoint = url_ReportingPaginate_PayType;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		
		else if(payType.equalsIgnoreCase("ReportingRefundQCountD")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefundQ_Count_D;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		
		
		else if(payType.equalsIgnoreCase("ReportingRefundQCountDDA")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefundQ_Count_D_DA;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		
		
		else if(payType.equalsIgnoreCase("ReportingRefundQCountSTnx")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefundQ_Count_S_Txns;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		
		
		else if(payType.equalsIgnoreCase("ReportingRefundQCountSTnxGw")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefundQ_Count_S_Txns_Gw;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		
		
		else if(payType.equalsIgnoreCase("ReportingRefundQCountSPayType")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefundQ_Count_S_PayType;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		
		else if(payType.equalsIgnoreCase("ReportingRefundQCountSPayTypeGw")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefundQ_Count_S_PayType_GW;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		
		else if(payType.equalsIgnoreCase("ReportingRefundQCountDCashCard")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefundQ_Count_D_CashCard;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		
		else if(payType.equalsIgnoreCase("ReportingRefundQCountPpayType")) {
			RestAssured.baseURI =urlReporting;

			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefundQ_Count_P_PayType;
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
		}	
		
		
		
		else if(payType.equalsIgnoreCase("ReportingRefundUploadFetchRefundID")) {
			RestAssured.baseURI =urlReporting;
			String Param = Params_Reporting_UpdloadQ_FetchBy_RefundID;
			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefundUploadQ_fetch_RefundID;
			response = RestAssured.given().
					when().log().all().headers(headers).body(Param).post(endPoint);
		}	
		
		else if(payType.equalsIgnoreCase("ReportingRefundUploadFetchTripID")) {
			RestAssured.baseURI =urlReporting;

			String Param = Params_Reporting_UpdloadQ_FetchBy_TripID;
			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefundUploadQ_fetch_TripID;
			response = RestAssured.given().
					when().log().all().headers(headers).body(Param).post(endPoint);
		}	
		
		else if(payType.equalsIgnoreCase("ReportingStatusPost")) {
			RestAssured.baseURI =urlReporting;

			String Param = Params_Reporting_Refund_Status_Post;
			Reporter.log(urlReporting);	
			endPoint = url_ReportingRefund_Count_ID;
			response = RestAssured.given().
					when().log().all().headers(headers).body(Param).post(endPoint);
		}	
		
		
		
		
		
		Reporter.log(endPoint);		
		return response;
	}

	public ArrayList<String> db_Refund_Common(String RefundType) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";
			String query =  "select ID from TM.REFUNDS where STATUS='P' AND REFUND_AMOUNT!=0 AND REFUND_TYPE ='"+RefundType+"' ORDER BY CREATED_AT desc";
			Connection myCon = DriverManager.getConnection(url, user, password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					for (int x = 1; x <= 1; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}
	
	
	public ArrayList<String> db_Refund_Common_MySQL(String RefundType) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String MySQL_URL = "jdbc:mysql://172.17.4.15:3306/payment";
			String MySQL_User = "payment";
			String MySQL_Password = "P@yment@123";
			String query =  "select ID from payment.refunds where STATUS='P' AND REFUND_AMOUNT!=0 AND REFUND_TYPE ='"+RefundType+"' ORDER BY CREATED_AT desc";
			Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					for (int x = 1; x <= 1; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}



	public ArrayList<String> MySQL_DB(String RefundType) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{

			String url = "jdbc:mysql://172.17.14.174:3306/payment";
			String user = "cleartrip";
			String password = "1nterl3av3";
			String query =  "select ctpay_id from ctpay_details where ORDER_ID ='T121212123344'";
			Connection myCon = DriverManager.getConnection(url, user, password);

			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					for (int x = 1; x <= 1; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}

	public ArrayList<String> db_get_Value() throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";
			String query =  "select * from TM.REFUNDS where ID = 9187220";
			Connection myCon = DriverManager.getConnection(url, user, password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					int colCnt = result.getColumnCount();
					for (int x = 1; x <= colCnt; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				} 
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}

	public ArrayList<String> db_Refund_Delete(String TripID) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";
			String query =  "Delete from tm.refunds where ID = "+TripID;
			Connection myCon = DriverManager.getConnection(url, user, password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);

				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}
	
	public ArrayList<String> db_Refund_Delete_MySQL(String TripID) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		{

			String MySQL_URL = "jdbc:mysql://172.17.14.174:3306/payment";
			String MySQL_User = "cleartrip";
			String MySQL_Password = "1nterl3av3";
			String query =  "Delete from payments.refunds where ID = "+TripID;
			

		    try (Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
		        java.sql.PreparedStatement stmt = myCon.prepareStatement(query)) {
		      
		      stmt.setInt(1, 2);
		      stmt.executeUpdate();
		      
		      System.out.println("Record deleted successfully");
			
			
			//Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
		/*	if (myCon != null) {
				ResultSet myRes = myCon.createStatement().execute

				myCon.close();
			} else
				Reporter.log("Connection not established");*/
		}catch (SQLException e) {
		      e.printStackTrace();
		}
		}
		return data;
	}
	
	


	public ArrayList<String> db_Refund_Delete_ID(String TripID) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		if (common.value("PaymentDB").equalsIgnoreCase("ORACLE")) {
			data = db_Refund_Delete(TripID);
			
		} else {
			data = db_Refund_Delete_MySQL(TripID);
			}
			
		return data;
		
	}

	public ArrayList<String> db_Refund_Amazon(String RefundType) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";
			String query =  "select R.ID from tm.REFUNDS R inner join tm.Payments P on R.STATUS='P' AND R.REFUND_TYPE ='TW' AND R.PAYMENT_ID= P.ID AND P.PAYMENT_SUBTYPE='AMAZON_WALLET' ORDER BY R.CREATED_AT desc";
			Connection myCon = DriverManager.getConnection(url, user, password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					for (int x = 1; x <= 1; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}

	public ArrayList<String> db_Refund_StatusCC(String RefundID) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";
			String query =  "select STATUS from TM.REFUNDS where ID ="+RefundID;
			Connection myCon = DriverManager.getConnection(url, user, password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					for (int x = 1; x <= 1; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}
	
	public ArrayList<String> db_Refund_StatusCC_MySQL(String RefundID) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String MySQL_URL = "jdbc:mysql://172.17.4.15:3306/payment";
			String MySQL_User = "payment";
			String MySQL_Password = "P@yment@123";
			Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);
			String query =  "select STATUS from payment.REFUNDS where ID ="+RefundID;
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					for (int x = 1; x <= 1; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}
	
	

	public ArrayList<String> db_Refund_Status(String RefundID) throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		if (common.value("PaymentDB").equalsIgnoreCase("ORACLE")) {
			data = db_Refund_StatusCC(RefundID);
			
		} else {
			data = db_Refund_StatusCC_MySQL(RefundID);
			}
			
		return data;
		
	}
	

	public ArrayList<String> db_GV() throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		if (common.value("PaymentDB").equalsIgnoreCase("ORACLE")) {
			data = db_GV_Oracle();
			
		} else {
			data = db_GV_MySQL();
			}
			
		return data;
		
	}
	
	
	public ArrayList<String> db_CC() throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		if (common.value("PaymentDB").equalsIgnoreCase("ORACLE")) {
			data = db_Refund_Common("CC");
			
		} else {
			data = db_Refund_Common_MySQL("CC");
			}
			
		return data;
		
	}
	
	
	
	
	
	public ArrayList<String> db_GV_Oracle() throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{
			String url = "jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			String user = "tm";
			String password = "tm123clear";
			String query =  "select ID from tm.payments where Payment_Type='GV' AND status ='S' order by created_at desc";
			Connection myCon = DriverManager.getConnection(url, user, password);
			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					for (int x = 1; x <= 1; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("Connection not established");
		}
		return data;
	}
	
	

	public ArrayList<String> db_GV_MySQL() throws SQLException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> Name = new ArrayList<String>();
		{

			String MySQL_URL = "jdbc:mysql://172.17.4.15:3306/payment";
			String MySQL_User = "payment";
			String MySQL_Password = "P@yment@123";
			String query =  "select ID from payment.payments where Payment_Type='GV' AND status ='S' order by created_at desc";
			Connection myCon = DriverManager.getConnection(MySQL_URL, MySQL_User, MySQL_Password);

			if (myCon != null) {
				ResultSet myRes = myCon.createStatement().executeQuery(query);
				while (myRes.next() == true) {
					ResultSetMetaData result = myRes.getMetaData();
					for (int x = 1; x <= 1; x++) {
						String colName = result.getColumnName(x);
						String colValue = myRes.getString(x);
						Name.add(colName);
						data.add(colValue);
					}
				}
				myCon.close();
			} else
				Reporter.log("MYSQL Connection not established");
		}
		return data;
	}
	
	
	public Response validation_RewardPayback(String payType, Response resp) throws Exception{
		boolean isMatching = false;
		String status="";
		String description="";
		Reporter.log("Response body "+payType +" : "+ resp.body().asString());
		System.out.println("Response body "+payType +" : "+ resp.body().asString());
		int statusCode = resp.getStatusCode();	
		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();

		if (payType.equalsIgnoreCase("PAYBACK_CheckBalance")) {				

			status = jsonPathEvaluator.getString("status");
			description = jsonPathEvaluator.getString("description"); 

			if(status.equalsIgnoreCase("S") && description.equalsIgnoreCase("Successfully fetched payback points amount")) {
				Reporter.log("status is : "+status);
				Reporter.log("Successfully fetched payback points amount - message is displayed ");
				isMatching = true;		
			}

			else{
				isMatching = false;
				Assert.assertTrue(isMatching);
			}

		}

		if(payType.equalsIgnoreCase("PAYBACK_CheckBalance_card")){
			status = jsonPathEvaluator.getString("status");
			description = jsonPathEvaluator.getString("description"); 

			if(status.equalsIgnoreCase("S") && description.equalsIgnoreCase("Successfully fetched payback points amount")) {
				Reporter.log("status is : "+status);
				Reporter.log("Successfully fetched payback points amount - message is displayed ");
				isMatching = true;
			}

			else{
				isMatching = false;
				Assert.assertTrue(isMatching);
			}
		}

		if(payType.equalsIgnoreCase("PAYBACK_CheckEarnPoints")){
			status =  jsonPathEvaluator.getString("status");
			description = jsonPathEvaluator.getString("description");

			if(status.equalsIgnoreCase("S") && description.equalsIgnoreCase("Successfully calculated earn points")){
				Reporter.log("status is : "+status);
				Reporter.log("Successfully calculated earn points");
				isMatching = true;
			}
			else{
				isMatching = false;
				Assert.assertTrue(isMatching);
			}

		}
		if(payType.equalsIgnoreCase("PAYBACK_CheckMobileLinked")){
			status = jsonPathEvaluator.getString("status");
			String isAccountLinked = jsonPathEvaluator.getString("isAccountLinked");

			if(status.equalsIgnoreCase("S") && isAccountLinked.equalsIgnoreCase("true")){
				Reporter.log("status is : "+status);
				Reporter.log("Payback Account is successfully linked to the mobile number");
				isMatching = true;
			}
			else{
				isMatching = false;
			}
		}

		if(payType.equalsIgnoreCase("PAYBACK_CheckCardLinked")){
			status = jsonPathEvaluator.getString("status");
			String isAccountLinked = jsonPathEvaluator.getString("isAccountLinked");

			if(status.equalsIgnoreCase("S") && isAccountLinked.equalsIgnoreCase("true")){
				Reporter.log("status is : "+status);
				Reporter.log("Payback Account is successfully linked to the card number");
				isMatching = true;
			}

			else{
				isMatching = false;	
			}
		}

		if(payType.equalsIgnoreCase("PAYBACK_Forgotpassword")){
			status= jsonPathEvaluator.getString("status");
			description= jsonPathEvaluator.getString("description");

			if(status.equalsIgnoreCase("S") && description.equalsIgnoreCase("PIN has been sent to your registered PAYBACK Mobile no:XXXXXX6785 and Email ID:kXXaXXkXXaX@cleartrip.com")){
				Reporter.log("status is : "+status);
				Reporter.log("Password pin successfully sent to the mobile number");
				isMatching=true;
			}
			else{
				isMatching=false;
			}
		}

		if(payType.equalsIgnoreCase("PAYBACK_RedeemPoints")){
			status= jsonPathEvaluator.getString("status");
			description= jsonPathEvaluator.getString("description");

			if(status.equalsIgnoreCase("S") && description.equalsIgnoreCase("Successfully redeemed points")){
				Reporter.log("status is : "+status);
				Reporter.log("Successfully redeemed points");
				isMatching=true;
			}

			else{
				isMatching=false;
			}
		}

		if(payType.equalsIgnoreCase("PAYBACK_RefundPoints")){
			status= jsonPathEvaluator.getString("status");
			description= jsonPathEvaluator.getString("description");

			if(status.equalsIgnoreCase("S") && description.equalsIgnoreCase("Successfully refunded points")){
				Reporter.log("status is : "+status);
				Reporter.log("Successfully refunded points");
				isMatching=true;
			}
			else{
				isMatching=false;
			}

		}

		if(payType.equalsIgnoreCase("PAYBACK_Earn")){
			status= jsonPathEvaluator.getString("status");
			description= jsonPathEvaluator.getString("description");

			if(status.equalsIgnoreCase("S") && description.equalsIgnoreCase("Successful Payback earn transaction")){
				Reporter.log("status is : "+status);
				Reporter.log("Successful Payback earn transaction");
				isMatching=true;
			}

			else{
				isMatching=false;
			}
		}

		if(payType.equalsIgnoreCase("PAYBACK_Reverseearn")){
			status= jsonPathEvaluator.getString("status");
			description= jsonPathEvaluator.getString("description");


			if(status.equalsIgnoreCase("S") && description.equalsIgnoreCase("Successfully reversed Payback Earn transaction")){
				Reporter.log("status is : "+status);
				Reporter.log("Successful Payback reverse earn transaction");
				isMatching=true;
			}

			else{
				isMatching=false;
			}
		}

		if(payType.equalsIgnoreCase("PAYBACK_Validate")){
			String redirectionRequired = jsonPathEvaluator.getString("redirection_required");
			String responseMessage = jsonPathEvaluator.getString("response_message");
			redirectionRequired= redirectionRequired.replace("[", "");
			redirectionRequired= redirectionRequired.replace("]", "");
			
			responseMessage= responseMessage.replace("[", "");
			responseMessage= responseMessage.replace("]", "");
			
			if(redirectionRequired.equalsIgnoreCase("N") && responseMessage.equalsIgnoreCase("Reward Points validation successful")){
				Reporter.log("There is no redirection required as the card details are valid");
				Reporter.log("Reward Points validation successful");
				isMatching=true;
			}

			else{
				isMatching=false;
			}
		}
		Assert.assertTrue(isMatching);
		return resp;
	}


	public Response validation_PromoService(String payType, Response resp) throws Exception{
		boolean isMatching = false;
		String status="";
		Reporter.log("Response body "+payType +" : "+ resp.body().asString());
		//System.out.println("Response body "+payType +" : "+ resp.body().asString());
		JsonPath jsonPathEvaluator = resp.jsonPath();
		if(payType.equalsIgnoreCase("PromoTripRefAndId")){
			status = jsonPathEvaluator.getString("status");
			String currency = jsonPathEvaluator.getString("currency");
			if(status.equalsIgnoreCase("Active") && currency.equalsIgnoreCase("INR")){
				isMatching = true;
				Reporter.log("Status is showing as:" + " "+status);
				Reporter.log("Currency is showing as:" + " " +currency);
			}
		}

		if(payType.equalsIgnoreCase("PromoGroupFromPromoRef")){
			status = jsonPathEvaluator.getString("status");
			if(status.equals("ACTIVE")){
				List<HashMap<String,String>> promoListOfList = jsonPathEvaluator.get("promotions");
				for(HashMap<String,String> m : promoListOfList){
					if(m.get("status").equals("ACTIVE")){
						isMatching = true;
					}
					else if(String.valueOf(m.get("status")).equals("COMPLETE")){
						if(m.get("transactions")!=""){
							String transactions = String.valueOf(m.get("transactions"));
							if(transactions.contains("status=SUCCESS"))
								isMatching = true;
						}
					}

				}
			}

		}

		if(payType.equalsIgnoreCase("PromoFromTripRef")){
			List<String> status_list = jsonPathEvaluator.get("status");
			for(int i=0;i<status_list.size();i++){
				if(status_list.get(i).equalsIgnoreCase("COMPLETE")){
					List<List<HashMap<String,String>>> trans_Map = jsonPathEvaluator.get("transactions");
					for(List<HashMap<String,String>> m : trans_Map){
						if(m==null){
							Reporter.log("No transaction statuses are available");
						}
						else if(m.get(0).get("status").equals("SUCCESS")){
							isMatching= true;
						}
						else{
							Reporter.log("Status is a Failure");
						}
					}

				}

				else if(status_list.get(i).equals("ACTIVE")){
					isMatching= true;
				}

			}
		}

		if(payType.equalsIgnoreCase("PromoFromPromoId")){
			status = jsonPathEvaluator.get("status");
			if(status.equals("ACTIVE")){
				List<HashMap<String,String>> promoListOfList = jsonPathEvaluator.get("promotions");
				for(HashMap<String,String> m : promoListOfList){
					if(m.get("status").equals("ACTIVE")){
						isMatching = true;
					}
					else if(String.valueOf(m.get("status")).equals("COMPLETE")){
						if(m.get("transactions")!=""){
							String transactions = String.valueOf(m.get("transactions"));
							if(transactions.contains("status=SUCCESS"))
								isMatching = true;
						}
					}

				}
			}

		}

		if(payType.equalsIgnoreCase("PromoGroupsFromCreatedDate")){
			List<String> status_list = jsonPathEvaluator.get("status");
			if(status_list.get(0).equalsIgnoreCase("ACTIVE")){
				isMatching = true;
				/*List<List<HashMap<String,String>>> promo_status=  jsonPathEvaluator.get("promotions");
				isMatching = true;*/
			}
		}

		if(payType.equalsIgnoreCase("PromoGroupsForAUpdatedDate")){
			List<String> status_list = jsonPathEvaluator.get("status");
			if(status_list.get(0).equalsIgnoreCase("ACTIVE")){
				isMatching = true;
			}
		}

		if(payType.equalsIgnoreCase("PromoGroupsForACreatedAndUpdatedDate")){
			List<String> status_list = jsonPathEvaluator.get("status");
			for(int i=0;i<status_list.size();i++){
				if(status_list.get(i).equalsIgnoreCase("ACTIVE")){
					List<List<HashMap<String,Object>>> statuspromo = jsonPathEvaluator.get("promotions");
					for(List<HashMap<String,Object>> m : statuspromo){
						HashMap<String,Object> promoNewMap = m.get(0);
						if(promoNewMap.containsKey("transactions")){
							if(promoNewMap.get("transactions")!=null){
								List<HashMap<String,Object>> transStatusList = (List<HashMap<String, Object>>) promoNewMap.get("transactions");
								for(HashMap<String,Object> map : transStatusList){
									String transactionStatus="";
									if(map.containsKey("status"))
										transactionStatus = (String) map.get("status");
									if(transactionStatus.equals("SUCCESS"))
										isMatching = true;
								}
							}

						}

					}
				}
			}	
		}

		if(payType.equals("CreatePromo")){
			status = jsonPathEvaluator.getString("status");
			if(status.equalsIgnoreCase("DEACTIVE")){
				List<HashMap<String,Object>> status_promo = jsonPathEvaluator.get("promotions");
				for(HashMap<String,Object> m : status_promo){
					if(m.get("status").equals("DEACTIVE"))
						isMatching = true;
					else
						isMatching=false;
				}
			}
		}

		if(payType.equals("CreatePromoGroup")){
			status = jsonPathEvaluator.getString("status");
			if(status.equalsIgnoreCase("DEACTIVE")){
				List<HashMap<String,Object>> status_promo = jsonPathEvaluator.get("promotions");
				for(HashMap<String,Object> m : status_promo){
					if(m.get("status").equals("DEACTIVE"))
						isMatching = true;
					else
						isMatching=false;
				}
			}

		}

		if(payType.equals("ActivatePromo")){
			status = jsonPathEvaluator.getString("status");
			if(status.equalsIgnoreCase("ACTIVE")){
				List<HashMap<String,Object>> status_promo = jsonPathEvaluator.get("promotions");
				for(HashMap<String,Object> m : status_promo){
					if(m.get("status").equals("ACTIVE"))
						isMatching = true;
					else
						isMatching=false;
				}
			}
		}

		if(payType.equalsIgnoreCase("UpdatePromo")){
			status = jsonPathEvaluator.getString("status");
			if(status.equalsIgnoreCase("DEACTIVE")){
				List<HashMap<String,Object>> status_promo = jsonPathEvaluator.get("promotions");
				for(HashMap<String,Object> m : status_promo){
					if(m.get("status").equals("DEACTIVE"))
						isMatching = true;
					else
						isMatching=false;
				}
			}
		}


		Assert.assertTrue(isMatching);
		return resp;
	}


	public static String tripRefForPromo(){
		int rand_num= PlatformCommonUtil.getRandomNumber();
		String random_num = Integer.toString(rand_num);
		String trip_ref = "Q180611" + random_num;
		return trip_ref;
	}


	public Response addWalletAmt(Response resp, String EmailID, int Amount, String Currency){
		RestAssured.baseURI =urlWallet;
		String url = "/payments/wallet/cashback?emailId="+EmailID+"&currency="+Currency+"&amount="+Amount+"&tripRef=Q190812462288";
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request;	

		System.out.println(url);
		request = 		
		RestAssured.given().
		when().
		log().all().
		headers(headers).
		get(url);
		System.out.println(request);
		return request;
	}
}



