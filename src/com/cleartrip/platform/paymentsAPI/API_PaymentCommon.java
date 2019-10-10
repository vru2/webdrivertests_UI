// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.testng.Reporter;

import domainServices.PlatformCommonUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class API_PaymentCommon extends PlatformCommonUtil
{	
	String paramsCC ="[{\"payment\":{\"seq_no\":1,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":276,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.73.123\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\",\"firstName\":\"test\"},\"app_ref1\":\"Q18110926800\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"683a3a6bec-4e58-422a-a2c9-90707b1e5a12\",\"payment_type\":\"CC\",\"amount\":100.99,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"9W/362/DEL/BOM/201811XXXXXX00\",\"order_info2\":\"Kiran Kumar\",\"source_type\":\"ACCOUNT\",\"user_id\":85721640,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://www.cleartrip.com/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"5123456789012346\",\"card_type_id\":1,\"expiry_month\":\"05\",\"expiry_year\":\"2020\",\"cvv\":\"123\",\"name\":\"CleartripCard\",\"billto_detail\":{\"firstname\":\"test\",\"lastname\":\"test\",\"address1\":\"Cleartrip JP Nagar\",\"city_name\":\"Bangalore\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"postal_code\":\"560076\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\"}}]";
	
	String ParamsGV_Refund = "{\"paymentId\":43178534,\"amount\":400,\"refundId\":9283288,\"giftVoucherRecipientEmail\":\"cleartriptest@gmail.com\",\"originalGiftVoucherNumber\":\"3000331134027267\",\"originalInvoiceNumber\":\"43178534_Q190808460482\",\"originalGvTxnId\":\"1908081707\",\"originalBatchNumber\":\"10573009\",\"originalApprovalCode\":\"7015313\",\"currency\":\"INR\",\"tripRef\":\"Q190808460482\"}";
	String ParamsGV_CAPTURE = "{\"cardNumber\":\"3000331034250519\",\"cardPin\":\"613401\",\"cardCategory\":\"\",\"amount\":1.0,\"currency\":\"INR\",\"paymentId\":43231978,\"tripRef\":\"Q191004523300\",\"productType\":\"DOMESTIC-AIR\"}";
	
	String paramsGV  = "[{\"payment\":{\"seq_no\":2,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":1618,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"Q18110930000\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"684fe048c7-cde3-4c20-9b73-a70e3c43bc9d\",\"payment_type\":\"GV\",\"amount\":10.0,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/8481/PAT/DEL/201812XXXXXX00\",\"order_info2\":\"Cleartrip tester\",\"source_type\":\"WL\",\"user_id\":51351954,\"company_id\":5291262,\"app_return_info\":{\"url\":\"dummy\",\"method\":\"POST\"},\"gift_voucher_detail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\",\"amount\":1,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/8481/PAT/DEL/201812XXXXXX00\",\"order_info2\":\"Cleartrip Tester\",\"source_type\":\"WL\",\"user_id\":51351954,\"company_id\":5291262,\"app_return_info\":{\"url\":\"dummy\",\"method\":\"POST\"},\"gift_voucher_detail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\",\"card_category\":\"\"}}}}]";
	String paramsWallet ="[{\"payment\":{\"seq_no\":1,\"trip_id\":54808092,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":1618,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"Q1809140000\",\"app_ref2\":\"74049672\",\"itinerary_id\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"payment_type\":\"WT\",\"amount\":10,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E\\/233\\/BLR\\/MAA\\/20181125062500\",\"order_info2\":\"Test Booking\",\"source_type\":\"ACCOUNT\",\"user_id\":41654864,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/681f6b756d-67de-4efc-b663-5a7ac1bd9fa1/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/681f6b756d-67de-4efc-b663-5a7ac1bd9fa1/book-internal?ll=INFO\"},\"user_agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrom/69.0.3497.100 Safari/537.36\"}}]";
	String paramsCCGVWL ="[{\"payment\":{\"seq_no\":1,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":276,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.73.123\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\",\"firstName\":\"test\"},\"app_ref1\":\"Q18110926800\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"683a3a6bec-4e58-422a-a2c9-90707b1e5a12\",\"payment_type\":\"CC\",\"amount\":100.99,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"9W/362/DEL/BOM/201811XXXXXX00\",\"order_info2\":\"Kiran Kumar\",\"source_type\":\"ACCOUNT\",\"user_id\":85721640,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://www.cleartrip.com/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"5123456789012346\",\"card_type_id\":1,\"expiry_month\":\"05\",\"expiry_year\":\"2020\",\"cvv\":\"123\",\"name\":\"CleartripCard\",\"billto_detail\":{\"firstname\":\"test\",\"lastname\":\"test\",\"address1\":\"Cleartrip JP Nagar\",\"city_name\":\"Bangalore\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"postal_code\":\"560076\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\"}},{\"payment\":{\"seq_no\":2,\"trip_id\":54808092,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":1618,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"Q1809140000\",\"app_ref2\":\"74049672\",\"itinerary_id\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"payment_type\":\"WT\",\"amount\":10,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E\\/233\\/BLR\\/MAA\\/20181125062500\",\"order_info2\":\"Test Booking\",\"source_type\":\"ACCOUNT\",\"user_id\":41654864,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/681f6b756d-67de-4efc-b663-5a7ac1bd9fa1/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/681f6b756d-67de-4efc-b663-5a7ac1bd9fa1/book-internal?ll=INFO\"},\"user_agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrom/69.0.3497.100 Safari/537.36\"}},{\"payment\":{\"seq_no\":2,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":1618,\"payment_category\":\"B\",\"fraud_system_invocation\":\"N\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"Q18110930000\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"684fe048c7-cde3-4c20-9b73-a70e3c43bc9d\",\"payment_type\":\"GV\",\"amount\":10.0,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/8481/PAT/DEL/201812XXXXXX00\",\"order_info2\":\"Cleartrip tester\",\"source_type\":\"WL\",\"user_id\":51351954,\"company_id\":5291262,\"app_return_info\":{\"url\":\"dummy\",\"method\":\"POST\"},\"gift_voucher_detail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\",\"amount\":1,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/8481/PAT/DEL/201812XXXXXX00\",\"order_info2\":\"Cleartrip Tester\",\"source_type\":\"WL\",\"user_id\":51351954,\"company_id\":5291262,\"app_return_info\":{\"url\":\"dummy\",\"method\":\"POST\"},\"gift_voucher_detail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\",\"card_category\":\"\"}}}}]";
	String paramsInit  = "[{\"payment\":{\"seq_no\":1,\"trip_id\":106562332,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":276,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.73.123\",\"mobile\":\"9986696785\",\"email\":\"cltppayment@gmail.com\",\"firstName\":\"test\"},\"app_ref1\":\"Q18110920000\",\"app_ref2\":\"167823462\",\"itinerary_id\":\"683a3a6bec-4e58-422a-a2c9-90707b1e5a12\",\"payment_type\":\"CC\",\"amount\":100.99,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"\",\"order_info2\":\"\",\"source_type\":\"ACCOUNT\",\"user_id\":85721640,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://www.cleartrip.com/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/683a3a6bec-4e58-422a-a2c9-90707b1e5a12/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"5497774415170603\",\"card_type_id\":1,\"expiry_month\":\"02\",\"expiry_year\":\"2021\",\"cvv\":\"412\",\"name\":\"test\",\"billto_detail\":{\"firstname\":\"Cleartrip\",\"lastname\":\"Tester\",\"address1\":\"JP Nagar\",\"city_name\":\"Bangalore\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"postal_code\":\"560076\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\"}}]";
	String paramsctPay_UpdateClient ="{\"client_id\":1125,\"active\":true}";
	String paramsctPay_GetURL = "{\"order_id\":\"T2333454445\",\"client_id\":1111,\"amount\":700.10,\"currency\":\"INR\",\"country\":\"IN\",\"udf\":{\"udf1\":\"1\",\"udf2\":\"2\",\"udf3\":\"3\",\"udf4\":\"4\",\"udf5\":\"5\"},\"customer_detail\":{\"ip_address\":\"217.164.159.242\",\"address1\":\"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",\"address2\":\"null\",\"address3\":null,\"city_name\":\"Mumbai\",\"postal_code\":\"400011\",\"state_name\":\"Maharashtra\",\"country_name\":\"India\",\"mobile\":\"121222112\",\"landline\":\"1212121212\",\"other_phone\":null,\"email\":\"cltppayment@gmail.com\"}}";
	String paramsctPay_CreateURL = "{\"order_id\":\"T2021069300\",\"client_id\":1125,\"amount\":1234.52,\"currency\":\"INR\",\"country\":\"IN\",\"return_url\":\"http://qa2.cleartrip.com\",\"udf\":{\"udf1\":\"Air ()\",\"udf2\":\"Air - ()\",\"udf3\":\"Hotel ()\",\"udf4\":\"Local & ()\",\"udf5\":\"Trains ()\"},\"customer_detail\":{\"ip_address\":\"217.164.159.242\",\"address1\":\"Unit No 001, Ground Floor, DTC Bldg, Sitaram mills compound, N.M.joshi Marg, Lower parel (E)\",\"address2\":\"Cleartrip JP Nagar\",\"address3\":null,\"city_name\":\"Bangalore\",\"postal_code\":\"560085\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"1212121112\",\"landline\":\"121212121221\",\"other_phone\":null,\"email\":\"cltppayment@gmail.com\"}}";
	String paramsFlyIN = "{\"amount\":10.10,\"currency\":\"SAR\",\"country\":\"SA\",\"txnid\":\"110119042057CTK3ZG2\",\"payment_type\":\"CC\",\"product_info\":\"Flight_Flyin\",\"source_type\":\"ACCOUNT\",\"host_name\":\"preproduction.flyin.com\",\"udf\":{\"udf1\":\"vkY25EH\",\"udf2\":\"F booking txn amount 10.10\"},\"company_id\":205,\"customer_detail\":{\"ip_address\":\"119.82.106.204\",\"mobile\":\"12121121212\",\"email\":\"123@flyin.com\"},\"card_detail\":{\"card_number\":\"4242424242424242\",\"card_type_id\":1,\"expiry_month\":\"05\",\"expiry_year\":\"2020\",\"cvv\":\"100\",\"name\":\"test test\"},\"return_url\":\"http://payments.fly.in/payment/finalresponse/ct?pid=vkY25EH\"}";
	String paramWalletCreate = "{\"currency\":\"AED\",\"createdBy\":\"13957750\",\"amount\":\"100\",\"paymentId\":\"43188350\",\"tripRef\":\"Q190822469836\",\"eventType\":\"CREATION\",\"expiryDate\":\"2020-12-21\"}";
	String paramsGV_Create_10 = "{\"currency\":\"INR\",\"amount\":\"10\",\"userEmail\":\"kiran.kumar@cleartrip.com\",\"paymentId\":\"43222232\"}";
	String paramsGV_Create_5000 = "{\"currency\":\"INR\",\"amount\":\"5000\",\"userEmail\":\"kiran.kumar@cleartrip.com\",\"paymentId\":\"43222232\"}";
	String paramsGV_Create_100000 = "{\"currency\":\"INR\",\"amount\":\"100000\",\"userEmail\":\"kiran.kumar@cleartrip.com\",\"paymentId\":\"43222232\"}";
	
	String paramsEW_Pay = "{\"payment\":[{\"seq_no\":1,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1903221094\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"EP\",\"amount\":5,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Mitali Biswas\",\"source_type\":\"ACCOUNT\",\"user_id\":41649008,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"}]}";
	String paramsEW_PayV3 = "[{\"payment\":{\"seq_no\":1,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1903221094\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"EP\",\"amount\":5,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Mitali Biswas\",\"source_type\":\"ACCOUNT\",\"user_id\":41649008,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"}}]";
	String paramsEW_PayMultiV3 = "[{\"payment\":{\"seq_no\":4,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1903221094\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"EP\",\"amount\":5,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Mitali Biswas\",\"source_type\":\"ACCOUNT\",\"user_id\":41649008,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"}},{\"payment\":{\"seq_no\":1,\"trip_id\":116912714,\"appUserid\":10001,\"productType\":\"DOMESTIC-AIR\",\"highRisk\":false,\"d_plus_x_in_hours\":1618,\"fraudSystemInvocation\":\"N\",\"uiVersion\":\"v2\",\"customerDetail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"anas.ansari@cleartrip.com\"},\"appRef1\":\"Q6876405349\",\"appRef2\":\"74049672\",\"itineraryId\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"paymentType\":\"GV\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"orderInfo1\":\"6E/233/BLR/MAA/20181125062500\",\"orderInfo2\":\"Test Booking\",\"sourceType\":\"ACCOUNT\",\"userId\":13957750,\"companyId\":110340,\"appReturnInfo\":{\"url\":\"http://172.17.15.176:9080/return\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://172.17.15.176:9080/bookInternalURL\"},\"giftVoucherDetail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\"},\"userAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\",\"paymentCategory\":\"B\",\"isPWA\":false,\"dplusXInHours\":1618,\"pwa\":false}},{\"payment\":{\"seq_no\":2,\"trip_id\":116912714,\"appUserid\":10001,\"productType\":\"DOMESTIC-AIR\",\"highRisk\":false,\"d_plus_x_in_hours\":1618,\"fraudSystemInvocation\":\"N\",\"uiVersion\":\"v2\",\"customerDetail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"anas.ansari@cleartrip.com\"},\"appRef1\":\"Q6876405349\",\"appRef2\":\"74049672\",\"itineraryId\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"paymentType\":\"GV\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"orderInfo1\":\"6E/233/BLR/MAA/20181125062500\",\"orderInfo2\":\"Test Booking\",\"sourceType\":\"ACCOUNT\",\"userId\":13957750,\"companyId\":110340,\"appReturnInfo\":{\"url\":\"http://172.17.15.176:9080/return\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://172.17.15.176:9080/bookInternalURL\"},\"giftVoucherDetail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\"},\"userAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\",\"paymentCategory\":\"B\",\"isPWA\":false,\"dplusXInHours\":1618,\"pwa\":false}},{\"payment\":{\"seq_no\":1,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"customer_detail\":{\"address1\":\"Flat 403 Tower 1, Mallika Malancha HIG Complex Action area 2B, Newtown\",\"city_name\":\"kolkata\",\"postal_code\":\"560066\",\"state_name\":\"West Bengal\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1904190001\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"WT\",\"amount\":50,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Test test\",\"source_type\":\"ACCOUNT\",\"user_id\":41654864,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"}}]";
	String paramsEW_PayMulti= "{\"payment\":[{\"seq_no\":4,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1904190001\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"EP\",\"amount\":100.5,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"test test\",\"source_type\":\"ACCOUNT\",\"user_id\":41649008,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"},{\"seq_no\":1,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"anas.ansari@cleartrip.com\"},\"app_ref1\":\"Q1904190001\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"WT\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Test test\",\"source_type\":\"ACCOUNT\",\"user_id\":41649008,\"company_id\":41649008,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"},{\"seq_no\":2,\"trip_id\":116912714,\"appUserid\":10001,\"productType\":\"DOMESTIC-AIR\",\"highRisk\":false,\"d_plus_x_in_hours\":1618,\"fraudSystemInvocation\":\"N\",\"uiVersion\":\"v2\",\"customerDetail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"anas.ansari@cleartrip.com\"},\"appRef1\":\"Q6876405349\",\"appRef2\":\"74049672\",\"itineraryId\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"paymentType\":\"GV\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"orderInfo1\":\"6E/233/BLR/MAA/20181125062500\",\"orderInfo2\":\"Test Booking\",\"sourceType\":\"ACCOUNT\",\"userId\":13957750,\"companyId\":110340,\"appReturnInfo\":{\"url\":\"http://172.17.15.176:9080/return\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://172.17.15.176:9080/bookInternalURL\"},\"giftVoucherDetail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\"},\"userAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\",\"paymentCategory\":\"B\",\"dplusXInHours\":1618,\"pwa\":false},{\"seq_no\":3,\"trip_id\":116912714,\"appUserid\":10001,\"productType\":\"DOMESTIC-AIR\",\"highRisk\":false,\"d_plus_x_in_hours\":1618,\"fraudSystemInvocation\":\"N\",\"uiVersion\":\"v2\",\"customerDetail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":\"12121221212\",\"email\":\"anas.ansari@cleartrip.com\"},\"appRef1\":\"Q6876405349\",\"appRef2\":\"74049672\",\"itineraryId\":\"681f6b756d-67de-4efc-b663-5a7ac1bd9fa1\",\"paymentType\":\"GV\",\"amount\":1.0,\"currency\":\"INR\",\"country\":\"IN\",\"orderInfo1\":\"6E/233/BLR/MAA/20181125062500\",\"orderInfo2\":\"Test Booking\",\"sourceType\":\"ACCOUNT\",\"userId\":13957750,\"companyId\":110340,\"appReturnInfo\":{\"url\":\"http://172.17.15.176:9080/return\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://172.17.15.176:9080/bookInternalURL\"},\"giftVoucherDetail\":{\"card_number\":\"3000331031424400\",\"card_pin\":\"198024\"},\"userAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\",\"paymentCategory\":\"B\",\"isPWA\":false,\"dplusXInHours\":1618,\"pwa\":false}]}";
	
	String paramsEW_Validate = "{\"payment\":[{\"seq_no\":1,\"trip_id\":116912714,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":217,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"customer_detail\":{\"address1\":\"Cleartrip JP Nagar Bangalore\",\"city_name\":\"Bangalore\",\"postal_code\":\"560076\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"mobile\":\"91 1212121212\",\"email\":\"cltppayment@gmail.com\"},\"app_ref1\":\"Q1903221094\",\"app_ref2\":\"185110142\",\"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",\"payment_type\":\"EP\",\"amount\":5,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/676/BLR/CCU/201904XXXXXX00\",\"order_info2\":\"Mitali Biswas\",\"source_type\":\"ACCOUNT\",\"user_id\":41654864,\"company_id\":41654864,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/68b08214fd-e940-42d5-9f0d-190322223806/book\",\"method\":\"POST\"},\"host_name\":\"qa2.cleartrip.com\",\"user_agent\":\"Apache-HttpClient/4.4 (Java 1.5 minimum; Java/1.8.0_51)\"}]}";
	String paramsEW_AddAmt = "";
	
	String ParamsValidate = "[{\"payment\":{\"seq_no\":1,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":755,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"119.82.106.204\",\"mobile\":\" 919986696785\",\"email\":\"cltppayment@gmail.com\"},\"itinerary_id\":\"68730b1763-f9d8-4059-88ff-62ce5f4a1ef8\",\"payment_type\":\"CC\",\"amount\":4118.0,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/812/BLR/CCU/20181113055500\",\"order_info2\":\"Test Pay\",\"source_type\":\"ACCOUNT\",\"user_id\":41644016,\"company_id\":110340,\"app_return_info\":{\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/68730b1763-f9d8-4059-88ff-62ce5f4a1ef8/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"5123456789012346\",\"card_type_id\":2,\"expiry_month\":\"5\",\"expiry_year\":\"2020\",\"cvv\":\"123\",\"name\":\"Test\",\"billto_detail\":{\"firstname\":\"Test\",\"address1\":\" \",\"city_name\":\"\",\"state_name\":\"\",\"country_name\":\"\",\"postal_code\":\"\"}}}}]";
	String ParamsOLAValidate1 = "{\"promoId\":\"PS";
	String ParamsOLAValidate2 = "\",\"amount\":200,\"tripId\":\"Q898989879\",\"recipientId\":\"9986696785\",\"currency\":\"INR\",\"comments\":\"test comment\",\"returnUrl\":\"NA\",\"notificationUrl\":\"NA\",\"balanceType\":\"cash\",\"balanceName\":\"cash\",\"agentId\":\"NA\"}";
	String ParamsOLAPromoGroup1 = "{\"user_id\":41651522,\"trip_ref\":\"Q";
	String ParamsOLAPromoGroup2 = "\",\"type\":\"HOTEL_BOOKING\",\"promotions\":[{\"amount\":500.55,\"currency\":\"INR\",\"expiry_date\":\"11-11-2019\",\"trigger_date\":\"12-11-2019\",\"wallet\":\"OLA\",\"mobile\":\"9986696785\",\"email\":\"test@test.com\"}]}";
	
	String paramsOLAPay="\",\"amount\":50.55,\"tripId\":\"Q898989879\",\"recipientId\":\"9986696785\",\"currency\":\"INR\",\"comments\":\"test\",\"returnUrl\":\"NA\",\"notificationUrl\":\"NA\",\"balanceType\":\"cash\",\"balanceName\":\"cash\",\"agentId\":\"NA\"}"; 

	String Paramsadcb_checkbalance1 = "{  \"rewardsType\": \"ADCB\",  \"rewardsRequestType\": \"BALANCE_CHECK\",  \"trackId\": \"";
	String Paramsadcb_checkbalance2 = "\",  \"cardNumber\": \"5326655508579056\",  \"amount\": 0.01,  \"currency\": \"AED\",   \"params\": {     \"customerName\": \"test\",     \"expiryDate\": \"2212\"  }}";
	String Paramsadcb_sendOTP1 = "{  \"rewardsType\": \"ADCB\",  \"rewardsRequestType\": \"OTP\",  \"trackId\": \"";
	String Paramsadcb_sendOTP2 = "\",  \"cardNumber\": \"5326655508579056\",  \"amount\": 0.01,  \"currency\": \"AED\",  \"params\": {  \t \"customerName\": \"test\",     \"expiryDate\": \"2212\"  }}";
	String Paramsadcb_validate1="[   {      \"payment\":{         \"seq_no\":1,         \"payment_subtype\":\"ADCB\",         \"trip_id\":45025226,         \"app_userid\":10001,         \"product_type\":\"INTL-AIR\",         \"high_risk\":false,         \"d_plus_x_in_hours\":0,         \"payment_category\":\"B\",         \"fraud_system_invocation\":\"Y\",         \"ui_version\":\"v2\",         \"customer_detail\":{            \"ip_address\":\"192.168.50.230\",            \"mobile\":\"9999999999\",            \"email\":\"123@cleartrip.com\"         },         \"itinerary_id\":\"68730b1763-f9d8-4059-88ff-62ce5f4a1ef8\",         \"payment_type\":\"RP\",         \"amount\":10,         \"currency\":\"AED\",         \"country\":\"AE\",         \"order_info1\":\"SG/812/BLR/CCU/20181113055500\",         \"order_info2\":\"Test\",         \"source_type\":\"ACCOUNT\",         \"user_id\":41644016,         \"company_id\":110340,         \"app_return_info\":{            \"method\":\"POST\",            \"book_internal\":true,            \"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/68730b1763-f9d8-4059-88ff-62ce5f4a1ef8/book-internal?ll=INFO\"         },         \"host_name\":\"qa2.cleartrip.com\",         \"reward_points_detail\":{            \"trackId\":\"";
	String Paramsadcb_validate2=  "\",            \"otp\":\"11111111\",            \"bankName\": \"ADCB\",            \"card_detail\":{               \"card_number\":\"5326655508579056\",               \"card_type_id\":2,               \"expiry_month\":\"12\",               \"expiry_year\":\"2022\",               \"cvv\":\"123\",               \"name\":\"Test\",               \"billto_detail\":{                  \"firstname\":\"Test\",                  \"address1\":\" \",                  \"city_name\":\"\",                  \"state_name\":\"\",                  \"country_name\":\"\",                  \"postal_code\":\"\"               }            }         }      }   }]";
	String Paramsadcb_pay1 = "[ {\n  \"payment\" : { \"seq_no\" : 1, \"payment_subtype\":\"ADCB\", \"trip_id\" : 45025226, \"app_userid\" : 10001, \"product_type\" : \"INTL-AIR\", \"high_risk\" : false, \"d_plus_x_in_hours\" : 0, \"payment_category\" : \"B\", \"fraud_system_invocation\" : \"Y\", \"ui_version\" : \"v2\", \"customer_detail\" : {   \"ip_address\" : \"192.168.50.230\",   \"mobile\" : \"9999999999\",   \"email\" : \"123@cleartrip.com\" }, \"app_ref1\" : \"Q1810120439\", \"app_ref2\" : \"74093112\", \"itinerary_id\" : \"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\", \"payment_type\" : \"RP\", \"amount\" : 0.01, \"currency\" : \"AED\", \"country\" : \"AE\", \"order_info1\" : \"6E/21/DEL/DXB/201810XXXXXX00\", \"order_info2\" : \"test testing\", \"source_type\" : \"ACCOUNT\", \"user_id\" : 41629512, \"company_id\" : 110340, \"app_return_info\" : {   \"url\" : \"https://qa2.cleartrip.ae/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book\",   \"method\" : \"POST\",   \"book_internal\" : true,   \"book_internal_url\" : \"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book-internal?ll=INFO\" }, \"host_name\" : \"qa2.cleartrip.ae\", \"reward_points_detail\":{         \"trackId\":\"";
	String Paramsadcb_pay2 = "\",         \"otp\":\"11111111\",          \"bankName\": \"ADCB\",         \"card_detail\":{            \"card_number\":\"5326655508579056\",            \"card_type_id\":2,            \"expiry_month\":\"12\",            \"expiry_year\":\"2022\",            \"cvv\":\"123\",            \"name\":\"Test\",            \"billto_detail\":{               \"firstname\":\"Test\",               \"address1\":\" \",               \"city_name\":\"\",               \"state_name\":\"\",               \"country_name\":\"\",               \"postal_code\":\"\"            }         } }, \"user_agent\" : \"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\"\n  }\n} ]\n";
	String ParamsPayBackCheckBalance = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"BALANCE_CHECK\",\"trackId\":\"SAL10001141\",\"currency\":\"INR\",\"params\":{\"customerName\":\"cleartrip\",\"mobile\":\"7000322041\",\"pin\":\"2827\"}}";
	String ParamsPayBackCheckBalance_card = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"BALANCE_CHECK\",\"trackId\":\"CLRP1000018\",\"cardNumber\":\"9401120000201303\",\"params\":{\"customerName\":\"test\",\"pin\":\"6486\"}}";
	String ParamsPayBack_Vaidate1 = "[{\"payment\":{\"seq_no\":1,\"payment_subtype\":\"PAYBACK\",\"trip_id\":45025226,\"app_userid\":10001,\"product_type\":\"INTL-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":0,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"192.168.50.230\",\"mobile\":\"9620351338\",\"email\":\"123@cleartrip.com\"},\"itinerary_id\":\"68730b1763-f9d8-4059-88ff-62ce5f4a1ef8\",\"payment_type\":\"RP\",\"amount\":1,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"SG/812/BLR/CCU/20181113055500\",\"order_info2\":\"Test\",\"source_type\":\"ACCOUNT\",\"user_id\":41644016,\"company_id\":110340,\"app_return_info\":{\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/68730b1763-f9d8-4059-88ff-62ce5f4a1ef8/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"reward_points_detail\":{\"trackId\":\"CLRP1000014\",\"params\":{\"mobile\":\"9620351338\",\"pin\":\"6486\"}}}}]";
	String ParamsPayBack_CheckEarnPoints = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"EARN_CHECK\",\"trackId\":\"CLRI000009925\",\"amount\":7050,\"currency\":\"INR\",\"params\":{\"source\":\"MOBILE\",\"isPwa\":true}}";

	String ParamsPayBack_ForgotPassword = "{\"mobileNumber\":\"9620351337\",\"cardNumber\":null}";
	String ParamsPayBack_Pay = "[{\"payment\":{\"seq_no\":1,\"payment_subtype\":\"PAYBACK\",\"trip_id\":45025226,\"app_userid\":10001,\"product_type\":\"INTL-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":0,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"192.168.50.230\",\"mobile\":\"9999999999\",\"email\":\"123@cleartrip.com\"},\"app_ref1\":\"Q18101204391\",\"app_ref2\":\"74093112\",\"itinerary_id\":\"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\",\"payment_type\":\"RP\",\"amount\":10,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/21/DEL/DXB/201810XXXXXX00\",\"order_info2\":\"test testing\",\"source_type\":\"ACCOUNT\",\"user_id\":41629512,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.ae/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"reward_points_detail\":{\"trackId\":\"";
	String ParamsPayBack_Pay1 = "\",\"params\":{\"customerName\":\"test\",\"mobile\":\"9620351338\",\"pin\":\"6486\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\"}}]";
    String ParamsPayBack_Earn = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"EARN\",\"paymentId\":1123346,\"trackId\":\"";
    String ParamsPayBack_Earn1 = "\",\"cardNumber\":null,\"amount\":1000,\"currency\":\"INR\",\"params\":{\"mobile\":\"9620351338\",\"tripId\":\"test123456\"}}";
	
    String ParamsPayBack_reverseearn = "{\"rewardsType\":\"PAYBACK\",\"rewardsRequestType\":\"REVERSE_EARN\",\"paymentId\":1123345,\"trackId\":\"";
    String ParamsPayBack_reverseearn1 = "\",\"amount\":100,\"cardNumber\":null,\"params\":{\"mobile\":\"9620351338\",\"tripId\":\"test123456\"}}";
	
    String ParamsPayBack_PayWT = "\",   \"params\": {\"customerName\": \"test\", \"mobile\": \"9620351338\", \"pin\":\"6486\"   }     },     \"user_agent\" : \"Mozilla/5.0(WindowsNT6.1;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/69.0.3497.100Safari/537.36\"   }  },  {    \"payment\":{ \"seq_no\":2, \"trip_id\":45025226, \"app_userid\":10001, \"product_type\":\"DOMESTIC-AIR\", \"high_risk\":false, \"d_plus_x_in_hours\":0, \"payment_category\":\"B\", \"customer_detail\":{    \"address1\":\"Flat403Tower1,MallikaMalanchaHIGComplexActionarea2B,Newtown\",    \"city_name\":\"kolkata\",    \"postal_code\":\"560066\",    \"state_name\":\"WestBengal\",    \"country_name\":\"India\",    \"mobile\":\"9620351338\",    \"email\":\"cltppayment@gmail.com\" }, \"app_ref1\":\"Q18101204300\", \"app_ref2\":\"74093112\", \"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\", \"payment_type\":\"WT\", \"amount\":5, \"currency\":\"INR\", \"country\":\"IN\", \"order_info1\":\"6E/21/DEL/BOM/201810XXXXXX00\", \"order_info2\":\"Testtesting\", \"source_type\":\"ACCOUNT\", \"user_id\":41649008, \"company_id\":41649008, \"host_name\":\"qa2.cleartrip.com\", \"user_agent\":\"Apache-HttpClient/4.4(Java1.5minimum;Java/1.8.0_51)\"       }  } ] ";
    String ParamsPayBack_PayWTMulti = "\",\"params\": { \"customerName\": \"test\", \"mobile\": \"9620351338\", \"pin\":\"6486\"}     },     \"user_agent\" : \"Mozilla/5.0(WindowsNT6.1;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/69.0.3497.100Safari/537.36\"   }  },  {    \"payment\":{          \"seq_no\":2,          \"trip_id\":45025226,          \"app_userid\":10001,          \"product_type\":\"DOMESTIC-AIR\",          \"high_risk\":false,          \"d_plus_x_in_hours\":0,          \"payment_category\":\"B\",          \"customer_detail\":{ \"address1\":\"Flat403Tower1,MallikaMalanchaHIGComplexActionarea2B,Newtown\", \"city_name\":\"kolkata\", \"postal_code\":\"560066\", \"state_name\":\"WestBengal\", \"country_name\":\"India\", \"mobile\":\"9620351338\", \"email\":\"cltppayment@gmail.com\"          },          \"app_ref1\":\"Q18101204300\",          \"app_ref2\":\"74093112\",          \"itinerary_id\":\"68b08214fd-e940-42d5-9f0d-190322223806\",          \"payment_type\":\"WT\",          \"amount\":5.25,          \"currency\":\"INR\",          \"country\":\"IN\",          \"order_info1\":\"6E/21/DEL/BOM/201810XXXXXX00\",          \"order_info2\":\"Testtesting\",          \"source_type\":\"ACCOUNT\",          \"user_id\":41654864,          \"company_id\":110340,          \"host_name\":\"qa2.cleartrip.com\",          \"user_agent\":\"Apache-HttpClient/4.4(Java1.5minimum;Java/1.8.0_51)\"       }  },  {    \"payment\":{          \"seq_no\":3,          \"trip_id\":45025226,       \"appUserid\" : 10001,     \"productType\" : \"DOMESTIC-AIR\",     \"highRisk\" : false,     \"d_plus_x_in_hours\" : 0,     \"fraudSystemInvocation\" : \"N\",     \"uiVersion\" : \"v2\",    \"customer_detail\" : {       \"ip_address\" : \"192.168.50.230\",       \"mobile\" : \"9620351338\",       \"email\" : \"123@cleartrip.com\"     },     \"appRef1\" : \"Q18101204300\",     \"appRef2\" : \"74093112\",     \"itineraryId\" : \"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\",     \"paymentType\" : \"GV\",     \"amount\" : 10.0,     \"currency\" : \"INR\",     \"country\" : \"IN\",     \"orderInfo1\" : \"6E/21/DEL/BOM/201810XXXXXX00\",     \"orderInfo2\" : \"TestTesting\",     \"sourceType\" : \"ACCOUNT\",     \"userId\" : 41701828,     \"companyId\" : 110340,     \"appReturnInfo\" : {       \"url\" : \"http://172.17.15.176:9080/return\",       \"method\" : \"POST\",       \"book_internal\" : true,       \"book_internal_url\" : \"http://172.17.15.176:9080/bookInternalURL\"     },     \"giftVoucherDetail\" : {       \"card_number\" : \"3000331033112563\",       \"card_pin\" : \"714375\"     },     \"userAgent\" : \"Mozilla/5.0(WindowsNT6.1;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/69.0.3497.100Safari/537.36\",     \"paymentCategory\" : \"B\",     \"isPWA\" : false,     \"dplusXInHours\" : 1618,     \"pwa\" : false       }     },  {    \"payment\":{          \"seq_no\":4,          \"trip_id\":45025226,       \"appUserid\" : 10001,     \"productType\" : \"DOMESTIC-AIR\",     \"highRisk\" : false,     \"d_plus_x_in_hours\" : 0,     \"fraudSystemInvocation\" : \"N\",     \"uiVersion\" : \"v2\",    \"customer_detail\" : {       \"ip_address\" : \"192.168.50.230\",       \"mobile\" : \"9620351338\",       \"email\" : \"123@cleartrip.com\"     },     \"appRef1\" : \"Q18101204300\",     \"appRef2\" : \"74093112\",     \"itineraryId\" : \"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\",     \"paymentType\" : \"GV\",     \"amount\" : 10.5,     \"currency\" : \"INR\",     \"country\" : \"IN\",     \"orderInfo1\" : \"6E/21/DEL/BOM/201810XXXXXX00\",     \"orderInfo2\" : \"TestTesting\",     \"sourceType\" : \"ACCOUNT\",     \"userId\" : 41701828,     \"companyId\" : 110340,     \"appReturnInfo\" : {       \"url\" : \"http://172.17.15.176:9080/return\",       \"method\" : \"POST\",       \"book_internal\" : true,       \"book_internal_url\" : \"http://172.17.15.176:9080/bookInternalURL\"     },     \"giftVoucherDetail\" : {       \"card_number\" : \"3000331033112563\",       \"card_pin\" : \"714375\"     },     \"userAgent\" : \"Mozilla/5.0(WindowsNT6.1;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/69.0.3497.100Safari/537.36\",     \"paymentCategory\" : \"B\",     \"isPWA\" : false,     \"dplusXInHours\" : 1618,     \"pwa\" : false       }     }      ";
    String ParamsPayBack_PayWTMulti1 = 	"] ";
    String ParamsPayBack_PayWTMultiCC = ",{\"payment\":{\"seq_no\":5,\"trip_id\":45025226,\"app_userid\":10001,\"product_type\":\"DOMESTIC-AIR\",\"high_risk\":false,\"d_plus_x_in_hours\":0,\"payment_category\":\"B\",\"fraud_system_invocation\":\"Y\",\"ui_version\":\"v2\",\"customer_detail\":{\"ip_address\":\"192.168.50.230\",\"mobile\":\"9620351338\",\"email\":\"123@cleartrip.com\"},\"app_ref1\":\"Q18101204300\",\"app_ref2\":\"74093112\",\"itinerary_id\":\"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\",\"payment_type\":\"CC\",\"amount\":100.99,\"currency\":\"INR\",\"country\":\"IN\",\"order_info1\":\"6E/21/DEL/BOM/201810XXXXXX00\",\"order_info2\":\"test testing\",\"source_type\":\"ACCOUNT\",\"user_id\":41701828,\"company_id\":110340,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book-internal?ll=INFO\"},\"host_name\":\"qa2.cleartrip.com\",\"card_detail\":{\"card_number\":\"5123456789012346\",\"card_type_id\":1,\"expiry_month\":\"05\",\"expiry_year\":\"2020\",\"cvv\":\"123\",\"name\":\"CleartripCard\",\"billto_detail\":{\"firstname\":\"test\",\"lastname\":\"test\",\"address1\":\"Cleartrip JP Nagar\",\"city_name\":\"Bangalore\",\"state_name\":\"Karnataka\",\"country_name\":\"India\",\"postal_code\":\"560076\"}},\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\",\"isPWA\":false,\"pwa\":false}}";
    
    String ParamsPayBack_PayCC ="\",   \"params\": {  \"customerName\": \"test\",  \"mobile\": \"9620351338\",  \"pin\": \"6486\"   }   },   \"user_agent\": \"Mozilla/5.0(WindowsNT6.1;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/69.0.3497.100Safari/537.36\"    }},{    \"payment\": {   \"seq_no\": 2,   \"trip_id\": 45025226,   \"app_userid\": 10001,   \"product_type\": \"DOMESTIC-AIR\",   \"high_risk\": false,   \"d_plus_x_in_hours\": 0,   \"payment_category\": \"B\",   \"fraud_system_invocation\": \"Y\",   \"ui_version\": \"v2\",   \"customer_detail\": {   \"ip_address\": \"192.168.50.230\",   \"mobile\": \"9620351338\",   \"email\": \"123@cleartrip.com\"   },   \"app_ref1\": \"Q18101204300\",   \"app_ref2\": \"74093112\",   \"itinerary_id\": \"7325a60909-c8c7-4383-a584-4dc94dd8b5f5\",   \"payment_type\": \"CC\",   \"amount\": 100.99,   \"currency\": \"INR\",   \"country\": \"IN\",   \"order_info1\": \"6E/21/DEL/BOM/201810XXXXXX00\",   \"order_info2\": \"testtesting\",   \"source_type\": \"ACCOUNT\",   \"user_id\": 41701828,   \"company_id\": 110340,   \"app_return_info\": {   \"url\": \"https://qa2.cleartrip.com/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book\",   \"method\": \"POST\",   \"book_internal\": true,   \"book_internal_url\": \"http://book-flights.cltp.com:9001/r3/book/flights/itinerary/7325a60909-c8c7-4383-a584-4dc94dd8b5f5/book-internal?ll=INFO\"   },   \"host_name\": \"qa2.cleartrip.com\",   \"card_detail\": {   \"card_number\": \"5123456789012346\",   \"card_type_id\": 1,   \"expiry_month\": \"05\",   \"expiry_year\": \"2020\",   \"cvv\": \"123\",   \"name\": \"CleartripCard\",   \"billto_detail\": {  \"firstname\": \"test\",  \"lastname\": \"test\",  \"address1\": \"CleartripJPNagar\",  \"city_name\": \"Bangalore\",  \"state_name\": \"Karnataka\",  \"country_name\": \"India\",  \"postal_code\": \"560076\"   }   },   \"user_agent\": \"Mozilla/5.0(WindowsNT10.0;WOW64;Trident/7.0;rv:11.0)likeGecko\",   \"isPWA\": false,   \"pwa\": false    }} ]";
    
    String Params_Createrefund = "{\"isFullWalletRefund\":%s,\"tripRef\":\"%s\",\"status\":%s,\"description\":\"some test description\",\"amount\":%s,\"txnid\": %s }";
    String Params_Createrefund1 = "{\"isFullWalletRefund\":false,\"tripRef\":\"%s\",\"status\":null,\"description\":\"some test description\",\"amount\":%s,\"txnid\": %s }";

    String Params_Updatestatus ="{\"refundId\": %s ,\"currentStatus\":\"S\",\"finalStatus\":\"P\"}";
    String Params_Updatepgtxnid = "{\"refundId\": %s ,\"pgGenerationTxnId\": %s }";
    
    String urlPay = "http://172.17.8.218:9001";
    //String urlFlyin = "http://172.17.15.129:9080";
	String urlEW = "http://172.17.8.218:9001";
	String urlRefundNew = "http://172.17.8.218:9001";
	String urlOLApromo = "http://172.17.26.11:7999";
	String urladcb = "http://172.17.26.11:8251";
	String urlRewards = "http://172.17.26.11:8251";
	String urlFetchPayDetails = "http://172.17.26.11:9031";
    String urlWallet = "http://wallet-service.cltp.com:9001";

	String urladcb_validat = "http://172.17.26.11:8070";

	String urlrewards_validate = "http://172.17.26.11:8070";
	String urlrewards_payURI ="http://paymentservice.cltp.com:9001";
	String urlrewards_URI = "http://rewardsservice.cltp.com:9001/";
	String urlPromo_Used = "http://wallet-service.cltp.com:9001";
	String urlReporting ="http://172.17.26.11:8272";
	
	String urlInit = "/paymentservice/service/otp/init";
	String urlProcess = "/paymentservice/service/otp/process";
	String urlResend = "/paymentservice/service/otp/resend";
	String urlPayValidateV3 = "/paymentservice/service/validate/v3";
	String urlPayV3 = "/paymentservice/service/pay/v3";
	String url1_FetchPayDetails = "/trips?tripID=Q1904233450";
	String urlpromoActivate = "/promoservice/v1/promogroups/Q190624286218";
	String urlpromoUsed = "/payments/wallet/promo/used?tripRef=Q190702311622";
	String urlPayFlyin = "/paymentservice/gw/v1/pay";

	String urlEndPoint_Wallet_PromoUsed="/payments/wallet/promo/used?tripRef=Q19050680568"; 	
	
	String urlEndPoint_GV_Get="/payments/gv/get?cardnumber=3000331033588753&cardpin=247648&currency=INR&productType=DOMESTIC-AIR&itineraryId=681f6b756d-67de-4efc-b663-5a7ac1bd9fa1&cardCategory=DF";
	String urlEndPoint_GV_Refund="/payments/gv/refund";
	String urlEndPoint_GV_CAPTURE = "/payments/gv/capture";
	
	
	
	String urlEndPoint_Wallet_RevertPromo ="/payments/wallet/promo/revert?tripRef=Q19050680568";
	String urlEndPoint_Wallet_GetDeduction = "/payments/wallet/promo/deductions?tripRef=Q190702311622";
	String urlEndPoint_Wallet_GetWallet_Trnx = "/payments/wallet/5153602/transactions?tripRef=Q1810310049&start=1&count=1000";
	String urlEndPoint_Wallet_CASHBACK_DETAILS = "/payments/wallet/promo/13957750/promotions/5732312";
	String urlEndPoint_Wallet_GETWALLET_ALL = "/payments/wallet/13957750/getWallet";
	String urlEndPoint_Wallet_GETWALLET_INR = "/payments/wallet/fetch?userId=13957750&currency=INR";
	String urlEndPoint_Wallet_CASHBACK_DETAILS14 = "";
	String urlEndpoint_GVCreate= "/payments/gv/create";
	String urlEndPoint_Wallet_CASHBACK_Wallet = "/payments/wallet/cashback?emailId=test@test123.com&currency=INR&amount=1&tripRef=Q190812462222&expiryDate =20-09-19";
	String urlEndPoint_Wallet_Create = "/payments/wallet/65176051/createWallet";
	
	String urlCTPay = "https://qa2.cleartrip.com";
	String urlCTPay_Get ="/paymentservice/ct/v2/getCtPaymentUrl";
	String urlCTPay_Status ="/paymentservice/ct/v2/T2021069300/status";
	String urlCTPay_UpdateClient= "/paymentservice/ct/v2/updateClient";
	String urlCTPay_CreateURL= "paymentservice/ct/v2/getCtPaymentUrl";
	
	
	String urlEW_UserDetails= "/paymentservice/expresswayplus/details/41649008/INR";	
	String urlEW_OptIn= "/paymentservice/expresswayplus/user/opt/in/41697596/INR";	
	String urlEW_GetOutstandingAmt= "/paymentservice/expresswayplus/outstandingbalance?userId=41697596&currency=INR";
	String urlEW_Validate= "/paymentservice/service/validate";
	String urlEW_Pay= "/paymentservice/service/pay";
	String urlEW_AutoDebit= "/paymentservice/expresswayplus/autodebit?expIdList=1001";
	String urlEW_AddAmt = "/paymentservice/expresswayplus/41697596/INR/addcredit?creditAmount=10";

	String urlEW_PayV3= "/paymentservice/service/pay/v3";
	String urlEW_Refund= "/paymentservice/expressway/v2/refund?refundIds=";
	
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
	
	String urlreward_payback_CheckMobile_Linked  = "/payments/rewards/payback/checkAccountLinked?mobileNumber=9620351338";
	String urlreward_payback_CheckCard_Linked  = "/payments/rewards/payback/checkAccountLinked?cardNumber=9401120000201303";
	
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

	String url_walletGetCards ="/paymentservice/card/get/41654864"; //  41654864 41701828
	String url_walletValidateCards ="/paymentservice/card/validate?cardNumber=5123456789012346&name=test&cardTypeId=2&expiryMonth=5&expiryYear=2020&userId=41654864";
	String url_walletDeleteCards ="/paymentservice/card/delete/%s";
	String url_walletStoreCards ="/paymentservice/card/store?cardNumber=5123456789012346&name=test&cardTypeId=2&expiryMonth=05&expiryYear=2020&userId=41654864";

	String url_Binmanager = "https://qa2.cleartrip.com/binmanager/v1/payment/cards?bin=534977";
	
	String url_Reportingendpoint ="/paymentservice/service/air/mis/detail?tripRef=Q190530193406&paymentType=CC&reqFor=refund";
	
		public HashMap<String, Object> headersForms(){		
			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
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
			headers.put("checksum", "e0d02be92115265d90a901648fdd7341b4f6883c7fac1fb415c97ec426640c56");
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
		
		
		public Response payGet(String payType, String payType1) {
			RestAssured.baseURI =urlOLApromo;
			String url = null;
			HashMap<String, Object> headers = new HashMap<>();
			headers = headersForms();
			Response request;	
			if(payType.equalsIgnoreCase("PromoActive")) {
					url= urlpromoActivate;
			}
			
			else if(payType.equalsIgnoreCase("PromoUsed")) {
				RestAssured.baseURI =urlPromo_Used;
				url= urlpromoUsed;
			}
			else if(payType.equalsIgnoreCase("FetchPayDetails")) {
				RestAssured.baseURI =urlFetchPayDetails;
				url= url1_FetchPayDetails;
			}
			
			
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(url);
			return request;			
		}
		
		public Response payPost(String payType, String payType1) {
			RestAssured.baseURI =urlPay;
			String url = null;
			String params = null;
			HashMap<String, Object> headers = new HashMap<>();
			headers = headersForms();
			Response request;	
			if(payType.equalsIgnoreCase("")) {
				params = "";
				url= "";
			}
			request = RestAssured.given().
					when().
					log().all().
					body(params).
					headers(headers).
					post(url);
			return request;			
		}
		
		public Response rearchPayment(String payType, String payType1){
			RestAssured.baseURI =urlPay;
			Reporter.log(urlPay);
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
				}else if(payType.equalsIgnoreCase("GV")) {
				params = paramsGV;
			}else if(payType.equalsIgnoreCase("WALLET")) {
				params = paramsWallet;
			}else if(payType.equalsIgnoreCase("CCGVWL")) {
				params = paramsCCGVWL;
			}else if(payType.equalsIgnoreCase("FLYIN")) {
				//RestAssured.baseURI =urlFlyin;
				url = urlPayFlyin;
				params = paramsFlyIN;
				}

			Reporter.log(url);
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
			Reporter.log(urlWallet);
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
			Reporter.log(url);
				request = RestAssured.given().
						when().
						log().all().
						body(params).
						headers(headers).
						post(url);	
			return request;
			
		}
		
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

			Reporter.log(urlWallet);
			Reporter.log(url);
			
			return request;
		}

		public Response rearchGV(String payType, String payType1){
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
				params = ParamsGV_CAPTURE;
				request = RestAssured.given().
						when().
						log().all().
						headers(headers).
						body(params).
						post(url);
			}
			Reporter.log(urlWallet);
			Reporter.log(url);
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
			Reporter.log(urlPay);
			Reporter.log(Url);
			return response;
		}
		
		public Response ola(String payType, String payType1, String promoID) throws Exception {
			RestAssured.baseURI = urlRefundNew;
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
				Url = urlOLApromo+urlOLA_PromoGroup;
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
			Reporter.log(urlRefundNew);
			Reporter.log(Url);
			////System.out.println(response.body().asString());
			return response;
			
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
				
				}else if(payType.equalsIgnoreCase("GV")) {
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
				Reporter.log("Message is : "+description);
				
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
				if(!resp.body().asString().contains("expiry_date")) {
					Reporter.log("expiry_date is not displayed");
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
				}*/if(!resp.body().asString().contains("exp_month")) {
					Reporter.log("exp year is not displayed ");
					Assert.assertTrue(false);
				}if(!resp.body().asString().contains("number")) {
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
				if(!resp.body().asString().contains("3000331040000001")) {
					Reporter.log("3000331040000001 is not displayed ");
					Assert.assertTrue(false);
				}
				if(!resp.body().asString().contains("3000331020000001")) {
					Reporter.log("3000331020000001 is not displayed ");
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
			
			
			
			return resp;	
		}
		
		public Response rearchCtPay(String payType, String payType1){

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
			Reporter.log(url);
			return request;
		}	
		
		public Response refund(String payType, String RefundID) {
			RestAssured.baseURI = urlRefundNew;
			String endPoint = null;
			HashMap<String, Object> headers	= new HashMap<>();
			headers = headersForms();
			Response response = null;
			if(payType.equalsIgnoreCase("refund")) {
				endPoint = urlRefundNew+url_RefundNEW_EndPoint+RefundID;
				if(common.value("PaymentServer").equalsIgnoreCase("old")) {
					endPoint = "http://paymentservice.cltp.com:9001/paymentservice/service/refund?refundids="+RefundID;
				}
				
				Reporter.log(endPoint);
				response = RestAssured.given().
				when().log().all().headers(headers).get(endPoint);
			}
			Reporter.log(urlRefundNew);
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
			Reporter.log(endPoint);
			return response;
		}
			
		public Response expressWay(String payType, String PayType1) {
			RestAssured.baseURI = urlEW;
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

			Reporter.log(urlEW);
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
				RestAssured.baseURI = urladcb;
				endPoint = urladcb_checkbalance;	
				params = Paramsadcb_checkbalance1+trackid+Paramsadcb_checkbalance2;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}
			else if(payType.equalsIgnoreCase("sendOTP")) {
				RestAssured.baseURI = urladcb;
				endPoint = urladcb_sendOTP;	
				params = Paramsadcb_sendOTP1+trackid+Paramsadcb_sendOTP2;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}			
			else if(payType.equalsIgnoreCase("validate")) {
				RestAssured.baseURI = urladcb_validat;
				endPoint = urladcb_validate;	
				params = Paramsadcb_validate1+trackid+Paramsadcb_validate2;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}
			else if(payType.equalsIgnoreCase("pay")) {
				RestAssured.baseURI = urladcb_validat;
				endPoint = urladcb_pay;	
				params = Paramsadcb_pay1+trackid+Paramsadcb_pay2;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}
			Reporter.log(urladcb);
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
			if(payType.equalsIgnoreCase("ADCB_CheckBalance")) {
				RestAssured.baseURI = urladcb;
				endPoint = urladcb_checkbalance;	
				params = Paramsadcb_checkbalance1+trackid+Paramsadcb_checkbalance2;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}
			else if(payType.equalsIgnoreCase("ADCB_sendOTP")) {
				RestAssured.baseURI = urladcb;
				endPoint = urladcb_sendOTP;	
				params = Paramsadcb_sendOTP1+trackid+Paramsadcb_sendOTP2;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}			
			else if(payType.equalsIgnoreCase("ADCB_validate")) {
				RestAssured.baseURI = urladcb_validat;
				endPoint = urladcb_validate;	
				params = Paramsadcb_validate1+trackid+Paramsadcb_validate2;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}
			else if(payType.equalsIgnoreCase("ADCB_pay")) {
				RestAssured.baseURI = urladcb_validat;
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
			
			else if(payType.equalsIgnoreCase("PAYBACK_Forgotpassword")) {
				RestAssured.baseURI = urlRewards;
				endPoint = urlreward_payback_ForgotPassword;
				params = ParamsPayBack_ForgotPassword;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}
			else if(payType.equalsIgnoreCase("PAYBACK_Pay")) {
				RestAssured.baseURI = urlrewards_validate;
				endPoint = urlreward_payback_pay;
				params = ParamsPayBack_Pay+trackid+ParamsPayBack_Pay1;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}
//			else if(payType.equalsIgnoreCase("PAYBACK_Pay_WT")) {
//				Random rand = new Random();
//				int n = rand.nextInt(9999999);
//				String trackid1 = Integer.toString(n);
//				String track = "CLRTRP"+ trackid1;
//				
//				RestAssured.baseURI = urlrewards_payURI;
//				endPoint = urlreward_payback_pay;
//				params = ParamsPayBack_Pay+track+ParamsPayBack_PayWT;
//				response = RestAssured.given().
//						when().log().all().body(params).headers(headers).post(endPoint);
//			}
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
				params = ParamsPayBack_Earn+trackid+ParamsPayBack_Earn1;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}		
			else if(payType.equalsIgnoreCase("PAYBACK_Reverseearn")) {
				RestAssured.baseURI = urlRewards;
				endPoint = urlreward_payback_reverseearn;
				params = ParamsPayBack_reverseearn+trackid+ParamsPayBack_reverseearn1;
				response = RestAssured.given().
						when().log().all().body(params).headers(headers).post(endPoint);
			}
			Reporter.log(urladcb);
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
				params = String.format(Params_Createrefund,isFullWalletRefund,tripid,status,amount,txnid);			
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
			Reporter.log(urlReporting);						
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
		
		
		public ArrayList<String> MySQL_DB(String RefundType) throws SQLException, ClassNotFoundException {
			ArrayList<String> data = new ArrayList<String>();
			ArrayList<String> Name = new ArrayList<String>();
			{
				
				//jdbc:mysql://localhost:3306/orion_db
					String url = "jdbc:mysql://172.17.14.174:3306/payment";
				//String url = "jdbc:oracle:thin:@172.17.14.174:3306/payment";
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

		
}