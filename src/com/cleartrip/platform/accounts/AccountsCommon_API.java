// Framework - Cleartrip Automation
// Author - Kiran Kumar


package com.cleartrip.platform.accounts;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Reporter;

import domainServices.PlatformCommonUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class AccountsCommon_API extends PlatformCommonUtil
{	
	String url_EndPoint_Trip  = "/trips/v2?trip=Q1901080092";
	String url_EndPoint_Mobile = "/trips/v2?mobile=7022651232";
	String url_EndPoint_Date = "/trips/v2?mobile=7411873374&t_date=12/03/2019";
	String url_PersonJson = "http://accounts.cltp.com:9001/r3/people/refresh_json?caller=true&from_accounts=true&username=ns.likhitha@cleartrip.com";
	String url_PersonJsonID = "http://accounts.cltp.com:9001/r3/people/refresh_json?caller=true&from_accounts=true&id=41699808";

	String url_IVR = "https://ivrqa.cleartrip.com";

	String url_Acct = "http://accounts.cltp.com:9001";

	String url_qa2 = "https://qa2.cleartrip.com:9001";
	
	String url_identity="https://qa2.cleartrip.com";

	String url_Acct_Service="http://accounts-service.cltp.com:9001";
	
	String url_flyin="http://user-accounts.cltp.com:9001";

	String url_CreateWallet = "/people/13941099/wallet?caller=postman";
	String url_acctSanity = "/accountsR3/sanity_test";
	String url_healthCheck = "/accountsR3/heart_beat";
	String url_GSTDetails = "/r3/partial/account/user/gst?email_id=ns.likhitha@cleartrip.com";
	String url_get_cfwoptinstatus="/account/cfw/opt_in?emailId=ns.likhitha@cleartrip.com";
	String url_peopleautocomplete="/account/people/auto_complete?name=vivek";
	String url_get_cfwgstdocstatus="/account/cfw/gst_doc_status?emailId=ns.likhitha@cleartrip.com";
	String url_CmpProfile = "/v1/company/search?id=5264644&caller=postman";
	String url_UserJson = "/people/13952926?caller=postman&source=B2C";
	String url_CmpConfig = "/r1/v1/company/company_configs?caller=air&id=137536";
	String url_Cmpjsonfetch="/r3/companies/121?caller=air";
	String url_b2bcmpuserdetails="/account/companies/5266142/b2b/users_info?user_profile=true";
	String url_PeopleSearch = "/r3/people/search?username=sphurti.d@cleartrip.com&caller=postman";
	String url_FetchProfile = "/r3/people/search?username=test@test1.com&caller=postman&source=B2C";
	String url_CmpRefresh = "/r3/companies/refresh_json?caller=air&domain=test.cleartripforbusiness.com&skip_login_check=true";

	String url_FetchBank = "/companies/200748/bank_details?caller=postman";
	String url_FetchPeople = "/r3/account/people/id/fetch";
	String  url_Feedbackemail="people/feedback_email";
	String url_signinpostcall="/r3/externalapi/signin";
	String url_userprofile_externalAPI_Mobileapp="/externalapi/people?caller=postman";

	String url_b2bAddtraveler="/people?caller=CORP_USER_CREATE&mode=update";

	String url_b2csignin="/externalapi/signin";
	String url_flyinsignin="/partners/signin";
	String url_flyinsigninV2="/partners/v2/signin";
	String url_Manage_trips_OTP_API="/account/auth/otp?trip_ref=Q190827475876&email_id=ns.likhitha@cleartrip.com";
	String url_cfw_active_status="/activate/LvRhD4oRkT8KhuEG";
	String url_flyinsignup="/partners/signup";
	String url_flyinsignupV2="/partners/v2/signup";
	String url_flyinresetpassword="/partners/account/reset_password";
	String url_flyinresetpasswordV2="/partners/account/resetPassword";
	String url_flyinforgetpassword="/partners/account/forgot_password";
	String url_flyinforgetpasswordV2="/partners/account/forgotPassword";
	String url_flyinusersearch="/partners/user/search";
	String url_flyinusersearchV2="/partners/v2/user/search";
	String url_flyinsocialsignup="/partners/social/signup";
	String url_flyinsocialsignupV2="/partners/v2/social/signup";
	String url_UserInfo = "/account/api/user_info?user_id=13939044&api_product_id=1040";
	String url_FetchCompany = "/account/companies/200748/company_vendor_mapping";
	String url_CompanyUsers= "/account/company/users?domain_name=eqc.agentbox.com&filter=user_ids";
	String url_DepositAcctSearch = "/account/deposit_account/search?linkable_type=Company&linked=true&domain_name=vinay.agentbox.com";
	String url_RoleInfo = "/account/roles/info";
	String url_FetchUserV2 = "/account/v2/people/search";
	String url_CompanyProfileRefresh = "/r3/companies/refresh_json?skip_login_check=true&id=206299&caller=air";
	String url_GenerateAPIKEY = "/r3/partners/profile/generate_api_key/anit.jkkl@gmail.com";
	String url_AcctRegister="r1/register?caller=true";
	String url_B2B_user_authentication="/users/authenticate";
	String url_Apiconfig_postcall="/account/api_config";
	String url_ForgotPasswordEmail="r3/reset?redirect=no";
	String url_fetchWallet="/account/wallet_balance?country=IN";
	String url_AccntHealthtest="/accountsR3/health_test";
	String url_apiConfig_APIkey="/account/api_config?api_key=d4beada793e93d93fcd432141709400f";
	String  url_b2bgetTravelerURL="/companies/b2b/101/travellers?sourceType=corp&titleType=adult";


	String url_createUpdateGST="/partial/account/user/gst?email_id=ns.likhitha@cleartrip.com";
	String url_cleartripForWorkOptin="/account/cfw/opt_in";
	String url_cfwgstdocstatusupdate="/account/cfw/gst_doc_status";
	String url_identityserviceaddclient="/ctauth/client";
	String url_registerUser="/expressway-register";
	String url_externalApi_Refreshtoken="/r3/account/external_api/refresh_token";
	String url_createUpdateTraveller="/people/externalapi/41623878/create_update_travellers?caller=Android%20App%2017.11.2";
	String url_createTraveller="/people/externalapi/41623878/create_update_travellers?caller=Android&user_profile=true";
	String url_createCompanyTags="/r3/companies/121/tags";
	String url_createUpdateTraveller_bookstep2="/people/externalapi/41623878/create_update_travellers?caller=bookstep2";
	String url_AccountsInfoAPI_BasedonUserId="/v1/people/filters?caller=ct-suite";


	String params_CreateWallet = "{\"currency\":\"INR\", \"caller\":\"pay-refund\", \"id\":\"5682929\"}";
	String params_FetchPeople = "{\"email_ids\":[\"vinay.raj@cleartrip.com\",\"sathianarayanan.s@cleartrip.com\",\"kiran.kumar@cleartrip.com\",\"xyz1\"]}";
	String params_Feedbackemail="{\"email_id\":\"ns.likhitha@cleartrip.com\",\"msg\":\"test\",\"subject\":\"working\",\"email_type\":\"default\"}";
	String params_Signinpostcall="{\"email\":\"ns.likhitha@cleartrip.com\",\"password\":\"cleartrip123\",\"persistent_login\":\"t\",\"service\":\"\",\"caller\":\"homepage\",\"source\":\"ui\",\"action_type\":\"\",\"trip_ref\":\"\",\"_\": \"\"}";
	String params_b2csignin="{\"email\":\"ns.likhitha@cleartrip.com\",\"password\":\"cleartrip123\",\"persistent_login\":\"t\",\"service\":\"\",\"caller\":\"homepage\",\"source\":\"ui\",\"action_type\":\"\",\"trip_ref\":\"\",\"_\": \"\"}";
	String params_flyinsignin="{\"username\" : \"ok@cltp.com\",\"partner\":1,\"password\":\"cleartrip1\"}";
	String params_Manage_trips_OTP_API="{\"trip_ref\":\"Q19050680460\",\"email_id\":\"ns.likhitha@cleartrip.com\"}";
	String params_cfw_active_status="{ \"cfw\":\"false\", \"user\": { \"password\": \"cleartrip1\" }, \"retype_password\": \"cleartrip1\", \"personal_data\": { \"title\": \"Mr\", \"first_name\": \"vinay\", \"last_name\": \"raj\" }, \"phone_number\": { \"phone_number_value\": \"+919743049515\", \"category\": \"mobile\" }, \"email_id\":\"ns.likhitha@cleartrip.com\" }";
	String params_flyinsignup="{ \"username\" : \"ok@cltp.com\", \"password\" : \"cleartrip1\", \"partner\" : \"1\", \"source\" : \"homepage\", \"title\" : \"Mr>\", \"first_name\":\"test\", \"last_name\":\"testte\" }";
	String params_flyinresetpassword="{ \"username\" : \"ok@cltp.com\", \"old_password\" : \"cleartrip1\", \"new_password\" : \"cleartrip1\", \"partner\" : 1, \"source\":\"homepage\" }";
	String params_flyinresetpasswordV2="{ \"username\" : \"ok@cltp.com\", \"old_password\" : \"cleartrip1\", \"new_password\" : \"cleartrip1\", \"partner\" : 1, \"source\":\"homepage\" }";
	
	String params_flyinforgetpassword="{ \"username\" : \"samsung@gmail.com\", \"key\" : \"bb54068cfd0f08459f85ce394a53caeef2feb56efa779ea9f512c3ed33d31e31\", \"new_password\" : \"cleartrip1\", \"partner\" : 1 }";
	String params_flyinforgetpasswordV2="{ \"username\" : \"samsung@gmail.com\", \"key\" : \"bb54068cfd0f08459f85ce394a53caeef2feb56efa779ea9f512c3ed33d31e31\", \"new_password\" : \"cleartrip1\", \"partner\" : 1 }";
	
	String params_flyinusersearch="{\"partner\":1,\"usernames\":[\"ok@cltp.com\",\"samsung@gmail.com\"],\"user_ids\":[65173245]}";
	String params_flyinusersearchV2="{\"partner\":1,\"user_ids\":[\"65178369\", \"65178433\", \"65071986\", \"65175660\", \"65176954\", \"65178404\", \"65178384\", \"65178378\", \"65178394\", \"9077500094746\", \"1090200101187\", \"65175544\", \"65178364\", \"1003697\", \"1007777\", \"65178476\", \"65178402\", \"65072085\", \"65072566\", \"65178449\", \"1004939\", \"1007898\", \"65178352\", \"65178403\", \"65178452\", \"65178473\", \"65178405\", \"65178355\", \"65178370\"]}";
	String params_flyinsocialsignup="{ \"username\" : \"ok@gmail.com\", \"partner\" : 1, \"title\" : \"Mr\", \"first_name\":\"test\", \"last_name\":\"Raj\", \"social_media_id\" : \"12345678\", \"social_media_type\":\"FaceBook\", \"key\":\"669947b54a191a94a50446db72924398f122d6ac58b349d0b163b1363b59d477\" }";
	String params_flyinsocialsignupV2="{ \"username\" : \"samsung10@gmail.com\", \"partner\" : 1, \"title\" : \"Mr\", \"first_name\":\"test\", \"last_name\":\"Raj\", \"social_media_id\" : \"123456\", \"social_media_type\":\"FaceBook\", \"key\":\"b5fa7bdc919c5ccb3eec706c9c770bf3f1205e7e4ff60445369f79dd816d062e\" }";
	String params_userprofile_externalAPI_Mobileapp="";
	String params_b2bAddtraveler="{ \"person\": { \"contact_data\": { \"emails\": [ { \"seq_no\": 0, \"email_id\": \"ns.likhitha@cleartrip.com\", \"category\": \"work\" } ] }, \"id\": 0, \"company_details\": [ { \"company_id\": 186462, \"company_people_roles\": [ { \"role_id\": 1073 } ], \"status\": \"0\" } ], \"personal_data\": { \"title\": \"Miss\", \"first_name\": \"sai\", \"last_name\": \"test\" }, \"recentlyBooked\": false, \"travel_profile\": { } } }";
	String params_RoleInfo= "{\"role_ids\":[1069]}";
	String params_FetchUserV2 = "{\"person_ids\":[13941098,13941098],\"usernames\":[\"vinay.raj@cleartrip.com\"],\"query\":[\"personal_data\"]}";
	String params_Register="{\"user\": { \"id\": 0,\"contact_data\": { },\"personal_data\": { },\"company_details\": [{\"company_id\": \"110340\",\"company_people_roles\": [{\"role_id\": 1064}]}],\"source_of_registration\": \"API\",\"travel_profile\": { },\"recentlyBooked\": false,\"username\": \"testcltp6@gmail.com\"} }";
	String params_B2B_user_authentication="{\"company_id\":101,\"username\":\"cleartripdemo@gmail.com\",\"password\":\"cleartrip\"}";
	String params_Apiconfig_postcall="{ \"active_date\": \"2016-01-28T12:56:35+05:30\", \"api_key\": \"d4beada793e93d93fcd432141709400f\", \"api_product_id\": 1042, \"book\": 1, \"create_date\": \"2016-01-28T12:56:35+05:30\", \"inactive_date\": \"2018-01-28T12:56:35+05:30\", \"look\": 2000, \"update_date\": \"2016-01-28T12:56:35+05:30\" }";
	String params_ForgotPasswordEmail="{\"email\":\"cleartriptester@gmail.com\"}";
	String params_createUpdateGST = "{\"gst_details\":[{\"gst_number\":\"28SAILI1234H1SL\",\"gst_holder_name\":\"Test holder name\",\"gst_holder_address\":\"test , test apartment, test road, test phase, test nagar, Bangalore 560078\",\"gst_holder_state_name\":\"Karnataka\",\"gst_holder_state_code\":\"22\"}]}";
	String params_cleartripForWorkOptin="{\"emailId\":\"ns.likhitha@cleartrip.com\",\"status\":\"enabled\"}";
	String params_cfwgstdocstatusupdate="{\"emailId\":\"ns.likhitha@cleartrip.com\",\"status\":\"accepted\"}";

	String s=RandomStringUtils.randomAlphabetic(3);
	String params_identityserviceaddclient1= "{ \"clientId\":\"likhithaa" +s;
	String params_identityserviceaddclient2="\", \"clientSecret\":\"secret\", \"authenticated\":\"true\", \"scope\":\"all\", \"authorizedGrantTypes\":\"read,write,trust\", \"redirectUri\":[\"http://localhost:8080/ctauth/authorize\"], \"authorities\":\"authorities\" }";
	String params_identityserviceaddclient=	params_identityserviceaddclient1+params_identityserviceaddclient2;
	String i = generateRandomWord(2);
	//expressway-register API POST body
	String params_registerUser="{\"username\":\"htedtalksatct"+i+"@gmail.com\", \"password\":\"Hello@123\",\"title\":\"Mr\",\"first_name\":\"Sujeett\", \"last_name\":\"Giger\", \"country_code\":\"91\", \"phone_number_value\":\"8765432161\", \"mkt_sbpt\":\"1\",\"service\":\"\", \"caller\":\"REG_FORM\", \"mail_token\":\"27A5E09E7260EAFB9815A27AEC554AF6\", \"_\":\"\"}";

	String params_externalApi_Refreshtoken="{\"refresh_token\":\"a0646154-7d20-4808-b73a-f1e100be1387\",\"company_id\":\"5363218\",\"expiry_date\":\"Tue Jun 02 14:57:37 IST 2020\",\"geolocation\":\"https://lik.api.concursolutios.com\"}";
	//create update traveller

	String params_createUpdateTraveller="{\"update_travellers\":{\"adultId1\":{\"user\":{\"personal_data\":{\"first_name\":\"new\",\"last_name\":\"userz\",\"date_of_birth\":\"\",\"title\":\"Mr\"},\"id\":\"41699808\",\"company_id\":110340,\"travel_profile\":{\"passport_no\":\"\",\"passport_date_of_expiry\":\"\",\"passport_issuing_country_id\":\"\",\"meal_preference\":\"0\",\"frequent_flyer_numbers\":[{\"frequent_flyer_number_value\":\"this is 145"+i+"4dfd10\",\"airline_code\":\"AI\",\"airline\":\"Air India [AI]\"}]}}}}}";
	String params_createTraveller="{\"create_travellers\":{\"adultId1\":{\"person\":{\"personal_data\":{\"first_name\":\"hahaha\",\"last_name\":\"ThisIsWorking\",\"date_of_birth\":\"\",\"title\":\"Mr\"},\"id\":0,\"company_id\":110340,\"travel_profile\":{\"passport_no\":\"\",\"passport_date_of_expiry\":\"\",\"passport_issuing_country_id\":\"\",\"meal_preference\":\"0\",\"frequent_flyer_numbers\":[]}}}}}";
	String params_createCompanyTags="{\"company_tags\":[ { \"coupon_expiry_date\": \"2018-11-04T23:59:59+05:30\", \"source_type\": \"SYNC\", \"tag_description\": \"Reward program coupon for trip Q1809051040\", \"tag_name\": \"Coupon:CTRP5828df132\", \"user_id\": 36348 }]}";
	String params_createUpdateTraveller_bookstep2="{\"update_travellers\":{\"adultId1\":{\"user\":{\"personal_data\":{\"first_name\":\"new\",\"last_name\":\"userz\",\"date_of_birth\":\"\",\"title\":\"Mr\"},\"id\":\"41699808\",\"company_id\":110340,\"travel_profile\":{\"passport_no\":\"\",\"passport_date_of_expiry\":\"\",\"passport_issuing_country_id\":\"\",\"meal_preference\":\"0\",\"frequent_flyer_numbers\":[{\"frequent_flyer_number_value\":\"this is 145"+i+"4dfd10\",\"airline_code\":\"AI\",\"airline\":\"Air India [AI]\"}]}}}}}";
	String params_AccountsInfoAPI_BasedonUserId="{ \"people_ids\": [ 123, 14029546 ], \"filters\": { \"status\": [ \"A\",null ], \"exists\": [ \"api_key\" ] } , \"projections\" : [\"id\",\"status\",\"api_key\",\"username\"] }";


	String cookie_value="MKdWExw7U5XHoqe5gvgNopvxR5VsdX%2BpbbtylbnbKovhQpnu62WWKv%2BtD0jlCQDW8KL31XDlHVQmZoLGGy3WuRq1V5%2Ff3cyOpY2e6VG%2B%2BEOmq1SZSPf0WwaBL9xsdFkeY8GZx6NCwQFZbnJNIFnBulgIq9NgXMFGIPcWjGvmYQ%2B7UvTyVzquOz%2F1UiyzcmLYG3ErhISsFBA7BHEzpGo4DEDWyymP5FpIOUZNJCY9VHPeH9%2BfEB%2FiE0WBt%2Fw8puA9IuQyIf89aiXKZtgoUuvt2S5NH52%2FqXy5YwY4naN9Fcti8vagSTjEILeaVGDZ9b0juahVCMz9Vt4TUmw5bD1fEhp8aGI0bkbHwvph%2F%2Ft678xfrYbifDim8FL7wHL9GLFX%2FgE5ffoswLeSHRaogy%2BY3VIwd%2BA0HfHap1%2FZKKJCwCp1FSlOYF7EqPdAiqIcu6uoDuIhpL2LD8jpaIgkNQT9G803IMJwTODu%2BN%2BIs%2BffvAOq8MsEeEuvJSbH7PowAwVswaTwsas4StK%2BPgsSD4qhUzenIycPyPqUUz5dsqejPub%2FM95pcbY4SRSdUkjIIF4wvM%2FxxitipKMbF8a57BVyUsQXhrQ8KUs7LfEQJw1bgBTXfF%2BmXV4GdF3cGT4Ijps1%2FBYKFLzqdoorE5DNKwtjZBgaRXctMghyBrbIkk1oVlwcO2XdIRIMLR3MvvwrB7R%2BLpWZgHfDMhEbt%2Bu7TdG8I425Ob4Au8ejc%2BuIvEQXek%2FWb25YYgoaZ3u0i1r%2Fb%2BL8vJHXcEOEUlE82lIxQfU3VpRaGR0GlHLFiGZZTVNEQsc%3D";
	public HashMap<String, Object> headersForms(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("ct-gn-auth", "dc7cfd533f661f92ebf6dceaf9930c3dba977bb8");
		return headers;
	}

	public HashMap<String, Object> headersForms1(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Accept", "text/json");
		return headers;
	}

	public HashMap<String, Object> headersFormsgst(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Accept", "text/json");
		headers.put("Cookie","ct-auth=EEEP41EBzbb%2FspcrZ0DsZan7S9VgbRY05xrj0tiyr6JaRedz0yuHHpvmRzXY09I2L1YjeWNmu3xY%2FpzUexWQN9BpkbZ1WOWTcucPHKwGD9XTn%2BkkNg210lHY20IlbLNNKQ25Oq4y%2BYb6rnClxu66WKLZiTdEKkjaPw9yA3jLrcBFPadDhADcu2tIIJKys%2BN5GaqX5VlxYlpbj60QRwfgD9%2BbotR8VhQRNZsocGlIYBOsREV%2Bgk7i%2BsLyHHvOoLlWigT%2FRuA2sldEPaXYwS%2FLuccxTK4Js5b3%2BYQs%2FxoSrzpmS16kH2fz%2BTmE5j4miA3%2FxtULLPwFin4hOTCqCaJMPJNErX3m9tZvweddN1V9ujQJtP%2FHYa6z7Wqo19qt2HEWRkL3KJqy4XWVsFOOftQEhMMZbYvc3lOsgjYjBXwhYOuoXqqvC6b%2BhXU1yveDjCcEzGo9i%2BGpmY138HIU9Jk5aQzWRx9AeBr0QjIRk8WfciZUmDPQERTUo9FrkxEnLpNOUa493i%2FXnYg0UumIXZtVcETv83553ZX8Vc%2Fo8neI3V439H2KuRGFqrBt00bS3ff2kTWA%2FHY8stDnH9Xyfcg%2BBrzTPgZyHAGJtFQ71UNZr3xrb2b8pEuhNy94qMBvVYZSr8NMKaInBbNNsMgTJHTIYXOv4bpNsbqZgBeOnGby%2FQstMGKPLsfTdp4%2FyWvlAwXNcPQ6QAwkIxVQAZ58PvrDkelGTDbmxvVdpf6HPA3P%2BB8%2Ba6%2FY9ZhxHxsnabhnx7gvkbef7%2BklQdxuu%2BWtbYhOaPyUQUlv5UoFz70%2B0Gh1P5UoFonwTB5CRQlF8Tq%2B4jtQhHlObStgXaVzlY5HZNFtynugbAqyG75DWZRzTGmRooE1mXBbVBT1gyk%2FBMG2BhXBMkPMWMeCmOqJvLB5fIkpg5EMmoDaTb789PjWfXaeowNp5NRyk0qrDlMzyf%2BIApdiflrboDrshzAVpm1SJY5O9Xs2DRjRNj8ARa8ajbOgAt5Xy6HWZPlT2qDJLGPe6BXsZoy%2BSIKQTQYOveue7G4EFrtgwTPl0VPWkmdhBDFvMihZqtQ2aalngA9XlDUlkrVUupbsw0zE6l2eQMBoovtm1HQQ0idQggehoOKesKDk73qxMY2EDzgz38t3vnAVX9L%2FTOOCendJZGID%2FUbdKz%2BvVOP8AQaq0LAJkKDea8zNINXEkZZlpPaEl4oHC5pf98YJRKGprLfTMLKTCUTmsWJFerslFcSwMhPO%2BhAD6fI0FwU2SIXJKoeifbwfzbsAT5XpMkWWop8rWhpvZrlMP77AjJ2sDSNUyYcp4F0JoK26%2BtEV6gkOeAPLX52Cx%2FKazqfDPc7NyU%2Bo%2BvBUyYMlhlCd76v8UzesFuBKFIHE3yE1nx%2F%2F04HrXYH4C8JPmFBi1dd4p9tWmJH7mrDgV1%2FmZEXZDXqr7cNOEprGJZuuMcPgRzWtLjdzbOswYQy8g%2BWwKfyXeEHRZW9wnKjTVrTCVFMtqulpugXxvsp2O8Q5e3%2FCLRxpWSKVOBEk7%2BQUlqZFw0tUJQP3mvTJJrADx9xmC1znVl3Z6kdBmCoyqj4EYZRzvNKXzmzxEsLr7tORj44uM2Hx%2FmKJqOnbm1bFxV6qpjULEMmLkNvYFyDKdCVWO2xGJUscdDnZgc%2BJE2nROtWLmNOhT5eJPstXvYSXn8Hg3HwL32ZeR7xxjY7%2Be9n5LyMPjxRoCIDw%2FdzGD73N4VDK5C%2B%2F9FH9OnLv2zs22aWfNz11al4ORA4RuptrckXavcPPIsAup%2FEUb%2FvIVX%2FTN1B73AvPw%2FORl2HeC1ykV07mp2N7DLR1AHZql3LN%2FQ78lcBrW8bBIdc%2F%2BgSb9FwAhiMQzVrzZVMiKeN4bV3%2FXIc9E3B8ZI7sZ60SmMbvUv1p3rVn3CBP3y1haO0pH7%2FqFJhsLsqAFkGo3WhLI%2BIhVOPzTUUR0Dtm09m9iloBDehFzKFes%2BG%2BShYrvXxWDL8d%2FO6DGkHSBHDORxCmQylc6m%2BpUZHfEMxqPYvhqZmNd%2FByFPSZOWkb9e%2BgojN3EeD6HCz4TZgFgkK%2FrICx4Hc5t6V0%2F%2BEiYwXtwx2LRO4XMA0aMbH8ZEFHpdJU20bQcpLBDdJlGUNj6DZJ07eHvNQtSCxBO7QVyF%2BKtm6HlqO9b4THaPdYDlIlFR%2BwmrDk6YXX44L9DrHepWib80zi6h7HHSmwEYtQviGFW%2BJQJAjs1XkBbjPPfULhJ2bj1vB%2FZzaCRZR7Y0mt16IDp8NrDH6X01ID%2FoTkxrz09Z9tSoMKsuqKT%2Fifm2iz3YiusNTuwp28FHrtJMfKD41HScIvPr%2BkvbDVqSmnrrdWgzk0rlr67osSeT2oE2NQUTYGglxSGyJfZL6hUC%2F08FCfz9PvUV1vR4OJAMOwb4LUa8W9Pl94V6G1lsSfG%2B%2BbUUKxi6SDa85LBv0a%2BuTnjG1yJIIursWJWWYL4wcfbdTxv7ngKn21pzhgtIVOMva3KrivJofipyqlrlOmpIFBwhnOLHW8dYOKQ7Yble%2FI6LTJuO6WJW4UMWIja%2ByoGz15v3KoEAgS%2BuBIVDOIJSDROr3a2WLwmNXZv0Qn3ar7XzCZYkcL0SLJH3kkvOqkxY5Gl0rUNwhG%2BJxULoC9eddsTKt%2F70pPhsvDcBVO6RE9clh02h%2BAT7RRleuJZ2bHZTKEcn%2BpCCW9XwLeBgH%2FK0PSxXp6gsxEGXPz20gyKP1mtA%3D%3D");
		return headers;
	}

	public HashMap<String, Object> headersFormsapi_config(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("AUTH_KEY", "weurimdjfoewpremkcuwpermapi");
		return headers;
	}


	public HashMap<String, Object> headersFormIdentity(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("clientGenAuthKey", "HBhen9VEL7zoTM884F1CycCWCb3o6s3FCynh9lTkU");

		return headers;
	}


	public HashMap<String, Object> headersFormscfwoptin(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("AUTH_KEY", "7GHT#@D65yhgder4R");

		return headers;
	}

	public HashMap<String, Object> headersFormscfwoptinstatus(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("AUTH_KEY", "7GHT#@D65yhgder4R");

		return headers;
	}



	public HashMap<String, Object> headersForms2(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return headers;
	}

	public HashMap<String, Object> headersForms3(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Accept", "text/json");
		headers.put("Content-Type", "application/json");
		return headers;
	}

	public HashMap<String, Object> headersForms4(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("AUTH_KEY", "7GHT#@D65yhgder4R");
		return headers;
	}



	public Response ivr(String Type){
		RestAssured.baseURI =url_IVR;
		String urlTrip = url_IVR+url_EndPoint_Trip;
		String urlMobile = url_IVR+url_EndPoint_Mobile;
		String urlDate= url_IVR+url_EndPoint_Date;
		String urlPersonJson = url_PersonJson;
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms();
		Response request = null;							
		if(Type.equals("TripID")) {
			Reporter.log("url  "+urlTrip);
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(urlTrip);
		}
		else if(Type.equals("Mobile")) {
			Reporter.log("url  "+urlMobile);
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(urlMobile);
		}
		else if(Type.equals("Date")) {
			Reporter.log("url  "+urlDate);
			request = RestAssured.given().
					when().
					log().all().
					headers(headers).
					get(urlDate);
		}

		return request;
	}


	public Response postCall(String Type, String Type1){
		RestAssured.baseURI =url_Acct;
		String url = null;
		String params =null;
		Response request = null;	

		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms1();

		if(Type.equals("CreateWallet")) {
			RestAssured.baseURI =url_Acct;
			url = url_CreateWallet;
			params = params_CreateWallet;					 
		}

		if(Type.equals("AcctRegister")){
			RestAssured.baseURI=url_Acct;
			headers=headersForms1();
			url=url_AcctRegister;
			params=params_Register;
		}

		if(Type.equals("B2B_user_authentication")){
			RestAssured.baseURI=url_Acct;
			headers=headersForms1();
			url=url_B2B_user_authentication;
			params=params_B2B_user_authentication;
		}

		if(Type.equals("Apiconfig_postcall")){
			RestAssured.baseURI=url_Acct;
			headers=headersFormsapi_config();
			url=url_Apiconfig_postcall;
			params=params_Apiconfig_postcall;
		}



		if(Type.equals("ForgotPasswordEmail")){
			RestAssured.baseURI=url_Acct;
			headers=headersForms3();
			url=url_ForgotPasswordEmail;
			params=params_ForgotPasswordEmail;
		}
		if(Type.equals("FetchPeople")) {
			headers = headersForms2();

			RestAssured.baseURI =url_Acct;
			url = url_FetchPeople;					
			params = params_FetchPeople;
		}

		if(Type.equals("Feedbackemail")) {
			headers = headersForms2();

			RestAssured.baseURI =url_Acct;
			url = url_Feedbackemail;					
			params = params_Feedbackemail;
		}

		if(Type.equals("signinpostcall")) {
			headers = headersForms3();

			RestAssured.baseURI =url_Acct;
			url = url_signinpostcall;					
			params =params_Signinpostcall ;
		}

		if(Type.equals("userprofile_externalAPI_Mobileapp")) {
			headers = headersForms3();

			RestAssured.baseURI =url_Acct;
			url = url_userprofile_externalAPI_Mobileapp;					
			params =params_userprofile_externalAPI_Mobileapp ;
		}


		if(Type.equals("b2bAddtraveler")) {
			headers = headersForms3();

			RestAssured.baseURI =url_Acct;
			url = url_b2bAddtraveler;					
			params =params_b2bAddtraveler ;
		}



		if(Type.equals("b2csignin")) {
			headers = headersForms3();

			RestAssured.baseURI =url_Acct;
			url = url_b2csignin;					
			params =params_b2csignin ;
		}


		if(Type.equals("API_Config_Getcall_APIKey")) {
			headers = headersFormsapi_config();


		}


		if(Type.equals("flyinsignin")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct;
			url = url_flyinsignin;					
			params =params_flyinsignin ;
		}
		
		if(Type.equals("flyinsigninV1")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct_Service;
			url = url_flyinsignin;					
			params =params_flyinsignin ;
		}


		if(Type.equals("flyinsigninV2")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct_Service;
			url = url_flyinsigninV2;					
			params =params_flyinsignin;
		}


		if(Type.equals("Manage_trips_OTP_API")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct;
			url = url_Manage_trips_OTP_API;					
			params =params_Manage_trips_OTP_API;
		}




		if(Type.equals("cfw_active_status")) {
			headers = headersForms2();

			RestAssured.baseURI =url_Acct;
			url = url_cfw_active_status;					
			params =params_cfw_active_status;
		}


		if(Type.equals("flyinsignup")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct;
			url = url_flyinsignup;					
			params =params_flyinsignup ;
		}
		
		if(Type.equals("flyinsignupV1")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct_Service;
			url = url_flyinsignup;					
			params =params_flyinsignup ;
		}


		if(Type.equals("flyinsignupV2")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct_Service;
			url = url_flyinsignupV2;					
			params =params_flyinsignup ;
		}

		if(Type.equals("flyinresetpasswordV2")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct_Service;
			url = url_flyinresetpasswordV2;					
			params =params_flyinresetpasswordV2 ;
		}
		
		if(Type.equals("flyinresetpassword")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct;
			url = url_flyinresetpassword;					
			params =params_flyinresetpassword ;
		}


		if(Type.equals("flyinforgetpassword")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct;
			url = url_flyinforgetpassword;					
			params =params_flyinforgetpassword ;
		}
		
		if(Type.equals("flyinforgetpasswordV2")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct_Service;
			url = url_flyinforgetpasswordV2;					
			params =params_flyinforgetpasswordV2 ;
		}


		if(Type.equals("flyinusersearch")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct;
			url = url_flyinusersearch;					
			params =params_flyinusersearch ;
		}
		
		if(Type.equals("flyinusersearchV1")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct_Service;
			url = url_flyinusersearch;					
			params =params_flyinusersearch ;
		}
		
		if(Type.equals("flyinusersearchV2")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct_Service;
			url = url_flyinusersearchV2;					
			params =params_flyinusersearchV2 ;
		}



		if(Type.equals("flyinsocialsignup")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct;
			url = url_flyinsocialsignup;					
			params =params_flyinsocialsignup;
		}
		
		if(Type.equals("flyinsocialsignupV1")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct_Service;
			url = url_flyinsocialsignup;					
			params =params_flyinsocialsignup;
		}

		
		if(Type.equals("flyinsocialsignupV2")) {
			headers = headersForms4();

			RestAssured.baseURI =url_Acct_Service;
			url = url_flyinsocialsignupV2;					
			params =params_flyinsocialsignupV2 ;
		}





		if(Type.equals("RoleInfo")) {
			headers = headersForms2();
			RestAssured.baseURI =url_Acct;
			url = url_RoleInfo;					
			params = params_RoleInfo;
		}
		if(Type.equals("FetchUserV2")) {
			headers = headersForms2();
			RestAssured.baseURI =url_Acct;
			url = url_FetchUserV2;					
			params = params_FetchUserV2;
		}

		if(Type.equals("createUpdateGST")) {
			headers = headersFormsgst();
			RestAssured.baseURI =url_Acct;
			url = url_createUpdateGST;					
			params = params_createUpdateGST;
		} 

		if(Type.equals("cleartripForWorkOptin")) {
			headers = headersFormscfwoptin();
			RestAssured.baseURI =url_Acct;
			url = url_cleartripForWorkOptin;					
			params = params_cleartripForWorkOptin;
		} 

		if(Type.equals("cfwgstdocstatusupdate")) {
			headers = headersFormscfwoptin();
			RestAssured.baseURI =url_Acct;
			url = url_cfwgstdocstatusupdate;					
			params = params_cfwgstdocstatusupdate;
		} 


		if(Type.equals("identityserviceaddclient")) {
			headers = headersFormIdentity();
			RestAssured.baseURI =url_identity;
			url = url_identityserviceaddclient;					
			params = params_identityserviceaddclient;
		} 


		if(Type.equals("registerUser")) {
			RestAssured.baseURI =url_Acct;
			url = url_registerUser;
			headers=headersForms3();
			params = params_registerUser;	
		}
		if(Type.equals("externalApi_Refreshtoken")) {
			RestAssured.baseURI =url_Acct;
			url = url_externalApi_Refreshtoken;
			headers=headersForms2();
			params = params_externalApi_Refreshtoken;	
		}
		if(Type.equals("createUpdateTraveller")) {
			RestAssured.baseURI = url_Acct;
			url = url_createUpdateTraveller;
			headers=headersForms2();
			params = params_createUpdateTraveller;
		}

		if(Type.equals("createTraveller")) {
			RestAssured.baseURI = url_Acct;
			url = url_createTraveller;
			headers=headersForms2();
			params = params_createTraveller;
		}

		if(Type.equals("createCompanyTags")) {
			RestAssured.baseURI = url_Acct;
			url = url_createCompanyTags;
			headers=headersForms2();
			params = params_createCompanyTags;
		}

		if(Type.equals("createUpdateTraveller_bookstep2")) {
			RestAssured.baseURI = url_Acct;
			url = url_createUpdateTraveller_bookstep2;
			headers=headersForms2();
			params = params_createUpdateTraveller_bookstep2;
		}

		if(Type.equals("AccountsInfoAPI_BasedonUserId")) {
			RestAssured.baseURI =url_Acct;
			url = url_AccountsInfoAPI_BasedonUserId;
			headers=headersForms2();
			params = params_AccountsInfoAPI_BasedonUserId;
		}

		Reporter.log("url  "+url);
		request = RestAssured.given().
				when().
				log().all().
				body(params).
				headers(headers).
				post(url);

		return request;
	}

	public Response getCall(String Type, String Type1){

		RestAssured.baseURI =url_Acct;
		String url = null;
		String params =null;
		Response request = null;	
		HashMap<String, Object> headers = new HashMap<>();
		headers = headersForms1();
		if(Type.equals("AcctSanity")) {
			url = url_acctSanity;
		}
		else if(Type.equals("healthCheck")) {				
			url = url_healthCheck;
		}
		else if(Type.equals("GstDetails")) {
			url = url_GSTDetails;
		}	

		else if(Type.equals("get_cfwoptinstatus")) {
			url = url_get_cfwoptinstatus;			
			headers = headersFormscfwoptinstatus();	

		}

		else if(Type.equals("peopleautocomplete")) {
			url = url_peopleautocomplete;			
			headers = headersFormscfwoptinstatus();	

		}


		else if(Type.equals("get_cfwgstdocstatus")) {
			url = url_get_cfwgstdocstatus;			
			headers = headersFormscfwoptinstatus();	

		}

		else if(Type.equals("apiConfig_APIkey")) {
			url =url_apiConfig_APIkey ;			
			headers = headersFormsapi_config();	

		}


		else if(Type.equals("CmpProfile")) {
			url = url_CmpProfile;
		}
		else if(Type.equals("CmpConfig")) {
			url = url_CmpConfig;
		}

		else if(Type.equals("Cmpjsonfetch")) {
			url = url_Cmpjsonfetch;
		}
		else if(Type.equals("b2bcmpuserdetails")) {
			url = url_b2bcmpuserdetails;
		}


		else if(Type.equals("UserJson")) {
			url = url_UserJson;
		}
		else if(Type.equals("FetchProfile")) {
			url = url_FetchProfile;
		}
		else if(Type.equals("CmpRefresh")) {
			url = url_CmpRefresh;
		}
		else if(Type.equals("FetchBank")) {
			url = url_FetchBank;
		}
		else if(Type.equals("UserInfo")) {
			url = url_UserInfo;
		}
		else if(Type.equals("FetchCompany")) {
			url = url_FetchCompany;
		}
		else if(Type.equals("CompanyUsers")) {
			url = url_CompanyUsers;
		}
		else if(Type.equals("DepositAcctSearch")) {
			headers.put("AUTH_KEY", "weurimdjfoewpremkcuwpermauthkey");
			url = url_DepositAcctSearch;
		}
		else if(Type.equals("CompanyProfileRefresh")) {
			url = url_CompanyProfileRefresh;
		}
		else if(Type.equals("GenerateAPIKEY")) {
			url = url_GenerateAPIKEY;
		}
		else if(Type.equals("PersonJson")) {
			url = url_PersonJson; 
		}
		else if(Type.equals("PersonJsonID")) {
			url = url_PersonJsonID; 
		}
		else if (Type.equals("fetchWallet")){
			RestAssured.baseURI =url_Acct;
			url = url_fetchWallet;
		}
		else if (Type.equals("AcctHealthtestURL")){
			url = url_AccntHealthtest;
		}

		else if (Type.equals("apiConfig_APIkey")){
			url = url_apiConfig_APIkey;
		}


		else if (Type.equals("b2bgetTravelerURL")){
			url = url_b2bgetTravelerURL;
		}


		Reporter.log("url  "+url);
		request = RestAssured.given().						
				when().
				log().all().
				headers(headers).
				get(url);
		return request;
	}

	public Response validation(Response resp, String Type, String Type2){
		Reporter.log("Response body "+Type +" : "+ resp.body().asString());
		//System.out.println("Response body "+Type +" : "+ resp.body().asString());
		int statusCode = resp.getStatusCode();
		//int statusCode1 = resp.getStatusCode();
		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();
		if(statusCode!=200) {
			Assert.assertTrue(false);
		}

		if(Type.equalsIgnoreCase("TripID")) {
			String trips = jsonPathEvaluator.getString("trips");
			if(!trips.contains("trip_id")) {
				Assert.assertTrue(false);						
			}
		}else if(Type.equalsIgnoreCase("Mobile")) {
			if(!resp.body().asString().contains("trip_id")) {
				Assert.assertTrue(false);						
			}
		}else if(Type.equalsIgnoreCase("Date")) {
			if(!resp.body().asString().contains("trip_id")) {
				Assert.assertTrue(false);						
			}
		}

		else if(Type.equalsIgnoreCase("CreateWallet")) {
			String status = jsonPathEvaluator.getString("status");
			String message = jsonPathEvaluator.getString("message");
			if(!status.contains("Successful")) {
				Assert.assertTrue(false);						
			}
			if(!message.contains("Wallet created successfully")) {
				Assert.assertTrue(false);						
			}
		}else if(Type.equalsIgnoreCase("AcctSanity")) {
			String create_user = jsonPathEvaluator.getString("create_user");
			String signinPassword = jsonPathEvaluator.getString("signin_with_right_password");
			String fetch_user_profile = jsonPathEvaluator.getString("fetch_user_profile");
			//String delete_user = jsonPathEvaluator.getString("delete_user");
			if(!create_user.contains("true")) {
				Assert.assertTrue(false);						
			}
			if(!signinPassword.contains("true")) {
				Assert.assertTrue(false);						
			}
			if(!fetch_user_profile.contains("true")) {
				Assert.assertTrue(false);						
			}
			//			if(!delete_user.contains("true")) {
			//				Assert.assertTrue(false);						
			//			}
		}

		else if(Type.equalsIgnoreCase("AcctHealthtestURL")) {
			String app = jsonPathEvaluator.getString("app");
			String db = jsonPathEvaluator.getString("db");
			String redis = jsonPathEvaluator.getString("redis");

			if(!app.contains("200")) {
				Assert.assertTrue(false);						
			}
			if(!db.contains("200")) {
				Assert.assertTrue(false);						
			}
			if(!redis.contains("200")) {
				Assert.assertTrue(false);						
			}

		}

		else if(Type.equalsIgnoreCase("apiConfig_APIkey")) {
			String id = jsonPathEvaluator.getString("api_product_id");
			String activatedate = jsonPathEvaluator.getString("active_date");
			String apikey = jsonPathEvaluator.getString("api_key");

			if(!id.contains("1042")) {
				Assert.assertTrue(false);						
			}
			if(!activatedate.contains("2016-01-28T12:56:35+05:30")) {
				Assert.assertTrue(false);						
			}
			if(!apikey.contains("d4beada793e93d93fcd432141709400f")) {
				Assert.assertTrue(false);						
			}

		}


		else if(Type.equalsIgnoreCase("GstDetails")) {
			String ReponseStr = resp.body().asString();
			if(common.value("host").equals("QA2")) {
				if(!ReponseStr.contains("Karnataka")) {
					Assert.assertTrue(false);						
				}

				if(!ReponseStr.contains("ENABLED")) {
					Assert.assertTrue(false);						
				}
			}

			else {
				if(!ReponseStr.contains("Karnataka")) {
					Assert.assertTrue(false);						
				}

				if(!ReponseStr.contains("28SAILI1234H1Z1")) {
					Assert.assertTrue(false);						
				}
			}
		}


		else if(Type.equalsIgnoreCase("get_cfwoptinstatus")) {
			String ReponseStr = resp.body().asString();
			if(common.value("host").equals("QA2")) {
				if(!ReponseStr.contains("ENABLED")) {
					Assert.assertTrue(false);						
				}

				if(!ReponseStr.contains("optInStatus")) {
					Assert.assertTrue(false);						
				}
			}
		}


		else if(Type.equalsIgnoreCase("peopleautocomplete")) {
			String ReponseStr = resp.body().asString();
			if(common.value("host").equals("QA2")) {
				if(!ReponseStr.contains("vivek.pareek@cleartrip.com")) {
					Assert.assertTrue(false);						
				}

				if(!ReponseStr.contains("vivek.ojha@kleward.com")) {
					Assert.assertTrue(false);						
				}
			}
		}




		else if(Type.equalsIgnoreCase("get_cfwgstdocstatus")) {
			String ReponseStr = resp.body().asString();
			if(common.value("host").equals("QA2")) {
				if(!ReponseStr.contains("gstDocumentStatus")) {
					Assert.assertTrue(false);						
				}

				if(!ReponseStr.contains("ACCEPTED")) {
					Assert.assertTrue(false);						
				}
			}
		}



		else if(Type.equalsIgnoreCase("b2bgetTravelerURL")) {
			String ReponseStr = resp.body().asString();
			if(common.value("host").equals("QA2")) {
				if(!ReponseStr.contains("company_id")) {
					Assert.assertTrue(false);						
				}

				if(!ReponseStr.contains("travellers")) {
					Assert.assertTrue(false);						
				}
			}

			else {
				if(!ReponseStr.contains("people_id")) {
					Assert.assertTrue(false);						
				}

				if(!ReponseStr.contains("first_name")) {
					Assert.assertTrue(false);						
				}
			}
		}



		else if(Type.equalsIgnoreCase("CmpProfile")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("18342556")) {
				Assert.assertTrue(false);						
			}	
			if(!ReponseStr.contains("CORP_PTA_ENABLED")) {
				Assert.assertTrue(false);						
			}
			if(!ReponseStr.contains("COMPANY_SALES_REPRESENTATIVE")) {
				Assert.assertTrue(false);						
			}
			if(!ReponseStr.contains("testcompany.cleartripforbusiness.com")) {
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("UserJson")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("6438960")) {
				Assert.assertTrue(false);						
			}	
			if(!ReponseStr.contains("last_login_date_time")) {
				Assert.assertTrue(false);						
			}
			if(!ReponseStr.contains("activation_date_time")) {
				Assert.assertTrue(false);						
			}
			if(!ReponseStr.contains("bonvoyage_subscription")) {
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("FetchProfile")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("bonvoyage_subscription")) {
				Assert.assertTrue(false);						
			}
			if(!ReponseStr.contains("contact_data_id")) {
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("CmpRefresh")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("company_configs")) {
				Assert.assertTrue(false);						
			}
			if(!ReponseStr.contains("channel_type_name")) {
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("FetchBank")) {
			String account_number = jsonPathEvaluator.getString("account_number");
			String ifsc_code = jsonPathEvaluator.getString("ifsc_code");
			String vendor_id = jsonPathEvaluator.getString("vendor_id");
			if(!account_number.contains("219101500771")) {
				Reporter.log("account_number :"+account_number);
				Assert.assertTrue(false);						
			}
			if(!ifsc_code.contains("ICIC0002191")) {
				Reporter.log("account_number :"+account_number);
				Assert.assertTrue(false);						
			}
			if(!vendor_id.contains("123456")) {
				Reporter.log("account_number :"+account_number);
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("FetchPeople")) {


			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("13957750")) {
				Assert.assertTrue(false);						
			}	if(!ReponseStr.contains("13950360")) {
				Assert.assertTrue(false);						
			}	if(!ReponseStr.contains("13941099")) {
				Assert.assertTrue(false);						
			}

		}

		else if(Type.equalsIgnoreCase("Feedbackemail")) {

			String status = jsonPathEvaluator.getString("status");
			if(!status.contains("Success")) {
				Assert.assertTrue(false);						
			}


		}

		else if(Type.equalsIgnoreCase("signinpostcall")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("redirect")){
				Assert.assertTrue(false);
			}
		}

		else if(Type.equalsIgnoreCase("userprofile_externalAPI_Mobileapp")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("fault")){
				Assert.assertTrue(false);
			}
		}

		else if(Type.equalsIgnoreCase("b2bAddtraveler")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("saitest")){
				Assert.assertTrue(false);
			}
		}


		else if(Type.equalsIgnoreCase("b2csignin")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("redirect")){
				Assert.assertTrue(false);
			}
		}

		else if(Type.equalsIgnoreCase("flyinsignin")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("ok@cltp.com")){
				Assert.assertTrue(false);
			}
		}

		else if(Type.equalsIgnoreCase("Manage_trips_OTP_API")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("We've sent a 6 digit OTP to ns.likhitha@cleartrip.com and 1234xxxxxx90 to verify your email address or mobile number for security reason. Please enter the OTP below to continue with the changes to your trip.")){
				Assert.assertTrue(false);
			}
		}



		else if(Type.equalsIgnoreCase("flyinsignup")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("partner_id")){
				Assert.assertTrue(false);
			}

		}


		else if(Type.equalsIgnoreCase("flyinsignupV2")) {


			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("email")){
				Assert.assertTrue(false);


				String email = jsonPathEvaluator.getString("email");
			String new_user = jsonPathEvaluator.getString("new_user");
			if(!email.contains("ok@cltp.com")) {
				Reporter.log("email :"+email);
				Assert.assertTrue(false);						
			}

			if(!new_user.contains("false")) {
				Reporter.log("newuser status :"+new_user);
				Assert.assertTrue(false);						
			}

			}
		}




		else if(Type.equalsIgnoreCase("flyinresetpassword")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("success")){
				Assert.assertTrue(false);
			}
		}
		
		else if(Type.equalsIgnoreCase("flyinresetpasswordV2")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("success")){
				Assert.assertTrue(false);
			}
		}


		else if(Type.equalsIgnoreCase("flyinforgetpassword")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("success")){
				Assert.assertTrue(false);
			}
		}
		else if(Type.equalsIgnoreCase("flyinforgetpasswordV2")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("success")){
				Assert.assertTrue(false);
			}
		}

		else if(Type.equalsIgnoreCase("flyinusersearch")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("samsung@gmail.com")){
				Assert.assertTrue(false);
			}
		}

		
		else if(Type.equalsIgnoreCase("flyinusersearchV2")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("sesidhar.nandanampati@cleartrip.com")){
				Assert.assertTrue(false);
			}
		}
 
		else if(Type.equalsIgnoreCase("flyinsocialsignup")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("ok@gmail.com")){
				Assert.assertTrue(false);
			}
		}
		
		
		else if(Type.equalsIgnoreCase("flyinsocialsignupV1")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("ok@gmail.com")){
				Assert.assertTrue(false);
			}
		}
		
		else if(Type.equalsIgnoreCase("flyinsocialsignupV2")) {

			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("samsung10@gmail.com")){
				Assert.assertTrue(false);
			}
		}



		else if(Type.equalsIgnoreCase("AcctRegister")){
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("username")){
				Assert.assertTrue(false);
			}
		}

		else if(Type.equalsIgnoreCase("B2B_user_authentication")){
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("activation_date_time")){
				Assert.assertTrue(false);
			}
		}

		else if(Type.equalsIgnoreCase("Apiconfig_postcall")){
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("d4beada793e93d93fcd432141709400f")){
				Assert.assertTrue(false);

			}
			if(!ReponseStr.contains("api_product_id")){
				Assert.assertTrue(false);

			}
		}
		else if(Type.equalsIgnoreCase("CompanyUsers")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("10440503")) {
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("DepositAcctSearch")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("7898943")) {
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("RoleInfo")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("1069")) {
				Assert.assertTrue(false);						
			}
			if(!ReponseStr.contains("The company employee who signs up for the corporate account and has all the privileges")) {
				Assert.assertTrue(false);						
			}


		}
		else if(Type.equalsIgnoreCase("FetchUserV2")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("13941098")) {
				Assert.assertTrue(false);						
			}
			if(!ReponseStr.contains("vinay.raj@cleartrip.com")) {
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("FetchCompany")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("200748")) {
				Assert.assertTrue(false);						
			}
			if(!ReponseStr.contains("123456")) {
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("UserInfo")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("13939044")) {
				Assert.assertTrue(false);						
			}
			if(!ReponseStr.contains("alex@zpond.co")) {
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("PersonJson")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("14029546")) {
				Assert.assertTrue(false);						
			}
		}
		else if(Type.equalsIgnoreCase("PersonJsonID")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("41699808")) {
				Assert.assertTrue(false);						
			}
		}

		else if(Type.equalsIgnoreCase("ForgotPasswordEmail")) {
			String ReponseStr = resp.body().asString();
			Reporter.log(ReponseStr);
			if(!(resp.getStatusCode()==200)) {
				Assert.assertTrue(false);						
			}
		}
		if(Type.equalsIgnoreCase("createUpdateGST")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("createdOrUpdated")) {
				Assert.assertTrue(false);						
			}	

		}

		if(Type.equalsIgnoreCase("cleartripForWorkOptin")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("updated status successfully")) {
				Assert.assertTrue(false);						
			}	

		}

		if(Type.equalsIgnoreCase("cfwgstdocstatusupdate")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("updated status successfully")) {
				Assert.assertTrue(false);						
			}	

		}


		if(Type.equalsIgnoreCase("identityserviceaddclient")) {
			String ReponseStr = resp.body().asString();
			if(!ReponseStr.contains("clientSecret")) {
				Assert.assertNotNull("id");
				Assert.assertNotNull("clientSecret");
				Assert.assertTrue(false);						
			}	

		}



		else if(Type.equalsIgnoreCase("registerUser")) {
			String ReponseStr = resp.body().asString();
			String message = jsonPathEvaluator.getString("message");
			if(!message.contains("REG_FORM registration done successfully")) {
				Assert.assertTrue(false);						
			}	

		}
		else if(Type.equalsIgnoreCase("externalApi_Refreshtoken")) {
			String ReponseStr = resp.body().asString();
			String message = jsonPathEvaluator.getString("message");
			if(!message.contains("recordUpdated")) {
				Assert.assertTrue(false);						
			}	

		}

		else if(Type.equalsIgnoreCase("createUpdateTraveller")) {

			if(statusCode!=200) {
				Assert.assertTrue(false);						
			}	
			if(Type.equalsIgnoreCase("fetchWallet")) {
				String ResponseStr = resp.body().asString();
				String userId = jsonPathEvaluator.getString("userId");
				String wallet_balance = jsonPathEvaluator.getString("ct_user_wallet_balance");
				Reporter.log("Wallet Balance: " +wallet_balance);
				if(!ResponseStr.contains(userId)) {
					Assert.assertTrue(false);						
				}	
				Assert.assertNotNull(wallet_balance);

			}

		}


		else if(Type.equalsIgnoreCase("createTraveller")) {

			if(statusCode!=200) {
				Assert.assertTrue(false);						
			}	
			if(Type.equalsIgnoreCase("fetchWallet")) {
				String ResponseStr = resp.body().asString();
				String userId = jsonPathEvaluator.getString("userId");
				String wallet_balance = jsonPathEvaluator.getString("ct_user_wallet_balance");
				Reporter.log("Wallet Balance: " +wallet_balance);
				if(!ResponseStr.contains(userId)) {
					Assert.assertTrue(false);						
				}	
				Assert.assertNotNull(wallet_balance);

			}

		}


		else if(Type.equalsIgnoreCase("createCompanyTags")) {

			if(statusCode!=200) {
				Assert.assertTrue(false);						
			}	

		}



		else if(Type.equalsIgnoreCase("createUpdateTraveller_bookstep2")) {

			if(statusCode!=200) {
				Assert.assertTrue(false);						
			}	
			if(Type.equalsIgnoreCase("fetchWallet")) {
				String ResponseStr = resp.body().asString();
				String userId = jsonPathEvaluator.getString("userId");
				String wallet_balance = jsonPathEvaluator.getString("ct_user_wallet_balance");
				Reporter.log("Wallet Balance: " +wallet_balance);
				if(!ResponseStr.contains(userId)) {
					Assert.assertTrue(false);						
				}	
				Assert.assertNotNull(wallet_balance);

			}
		}




		else if(Type.equalsIgnoreCase("AccountsInfoAPI_BasedonUserId")) {

			if(statusCode!=200) {
				Assert.assertTrue(false);						
			}

			else if(Type.equalsIgnoreCase("AccountsInfoAPI_BasedonUserId")) {
				String ReponseStr = resp.body().asString();
				if(!ReponseStr.contains("api_key")) {
					Assert.assertTrue(false);						
				}
				if(!ReponseStr.contains("username")) {
					Assert.assertTrue(false);						
				}
			}

		}

		return resp;
	}

}