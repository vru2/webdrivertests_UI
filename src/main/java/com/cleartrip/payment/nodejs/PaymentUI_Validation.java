/* AUTHOR: PRAKHAR CHATTERJEE
 * EMAIL: prakhar.chatterjee@cleartrip.com
 * Documentation: This Class is focussed on automating the APIs related to new Payments UI.
 */

package com.cleartrip.payment.nodejs;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentUI_Validation extends PaymentNodeJS{

	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;


	@BeforeClass
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","");
		qaUrl = qaurl;
		Url = qaUrl+ fetchPaymentURL(resp);
	}

	@Test(priority=1)
	public void getPayURL() throws Exception{
		validation_PaymentUI("BookApp/GetPay", resp);

	}

	@Test(priority=2, dependsOnMethods={"getPayURL"})
	public void firePaymentURLandValidatePaymentModes() throws Exception{
		try {
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);
			elementVisible(driver, By.xpath("//ul[@id='paymentModeTab']"), 10);
			validatePaymentURLLoad(driver,Url);
			Cookie cookieName = new Cookie("ct-auth", "rMSd%2B7hfyuZ4uZjjNKlV42pN3SDMXLXVZk1TVNkNLVrKtFI4anQeWMFUWdffHNMIJWNRt682KuqiFuXV2whf5HObWzaif65XOuM37YyrPLX%2BaiBNwnkmSmMem2WbpgBvzIOK8AA0ICwzYBAszCVlK7Wt4vbavb4Rc9plZba2GjgZcQBlPkvWs0YEAP%2B2OXYcwxeut2x6p5X1i%2BvPjnOjN5c7bmkG62x2TeRUPL%2BcTfQ7ZtPZvYpaAQ3oRcyhXrPhCUmcbRdKxTvjY08FAtXwySBwZnpRB%2Fr6Tdc4tErNeglqJTknezoRpPhKBzjfu1gtd8ro1XIKetU3yLt3kXt9RMitRVpAKIqLA%2Bkwfued9ARpSFWPHNzcb5k%2BZjusDdQuULECGHAP00B8LK7MltV20wodXXSeczhpDpmjAwJJBWF2kulqJ%2FaQ5Oi%2BUMmQ92BEqwQ0%2FZ1GGS%2FCsh4%2Flet6bIQmTJelK7OdeSLJlOhcpan1uHwoj5PmK6CrwQl4iGe6N0IBzS8MCjon9SGgFW8uc%2B97NUe06yWRwDtxLHRrqe%2B8UfmNCT%2B9HIFFr7urccGIf09n7B1MBN2D%2F3uBsb4bR8YYXRDmXYUVm%2FXms5YZHzl1u0HRpkoj3SJCZNksleaf4%2FRMFvDNJjcW0zkxFMlzew2BiwCGms1A%2Bpuib7AbTmi3KrivJofipyqlrlOmpIFB1G8rwPKUm0CWFPPLzylXMjky%2BAwgs2JmZ98juSAlxpgfxu0MBkt3zdybT2Q%2FMN0xIQa%2FoD%2FBy5WV6e%2FBKNrYyg%3D%3D");
			driver.manage().addCookie(cookieName);
			refreshPage(driver);
			elementVisible(driver, By.xpath("(//label[@class='checkbox-round'])[1]"), 10);
			List<WebElement> walletCheckBoxList = driver.findElements(By.xpath("(//label[@class='checkbox-round'])[1]"));
			if(walletCheckBoxList.size()!=0) {
				click(driver,PaymentUI_CommonUtilities.walletCheckBox);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			}
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeCC,PaymentUI_CommonUtilities.paymentModexpath);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeDC,PaymentUI_CommonUtilities.paymentModexpath);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeNB,PaymentUI_CommonUtilities.paymentModexpath);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeUPI,PaymentUI_CommonUtilities.paymentModexpath);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeADCB,PaymentUI_CommonUtilities.paymentModexpath);
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false);
		}

	}

	@Test(priority=3)
	public void emptyCreditCardErrorValidation() throws Exception{
		try {
			click(driver, PaymentUI_CommonUtilities.creditCardPaymentxpath);
			storedCardCheckbox(driver,PaymentUI_CommonUtilities.storedCardCheckbox);
			convenienceText(driver,PaymentUI_CommonUtilities.convenienceTextxpath);
			validateCCPageText(driver,PaymentUI_CommonUtilities.creditOrDebitCardPageTextxpath);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextCardNumber,PaymentUI_CommonUtilities.cardNumbererrorxpath);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextCardHolderName,PaymentUI_CommonUtilities.cardHoldererrorxpath);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextCvv,PaymentUI_CommonUtilities.cardCvverrorxpath);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextExpiryMonth,PaymentUI_CommonUtilities.expiryerrorxpath);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextExpiryYear,PaymentUI_CommonUtilities.expiryerrorxpath);
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false);
		}

	}

	@Test(priority=4)
	public void emptyDebitCardErrorValidation() throws Exception{

		try {
			click(driver,PaymentUI_CommonUtilities.debitCardPaymentxpath);
			convenienceText(driver,PaymentUI_CommonUtilities.convenienceTextxpath);
			validateDCPageText(driver,PaymentUI_CommonUtilities.creditOrDebitCardPageTextxpath);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextCardNumber,PaymentUI_CommonUtilities.cardNumbererrorxpath);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextCardHolderName,PaymentUI_CommonUtilities.cardHoldererrorxpath);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextCvv,PaymentUI_CommonUtilities.cardCvverrorxpath);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextExpiryMonth,PaymentUI_CommonUtilities.expiryerrorxpath);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextExpiryYear,PaymentUI_CommonUtilities.expiryerrorxpath);	
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false);
		}

	}

	@Test(priority=5, enabled=false)
	public void footerLinks() throws Exception{

		/*		validateFooterLink(driver,PaymentUICommonUtilities.footerOne,PaymentUICommonUtilities.footerxpath);
		validateFooterLink(driver,PaymentUICommonUtilities.footerTwo,PaymentUICommonUtilities.footerxpath);
		validateFooterLink(driver,PaymentUICommonUtilities.footerThree,PaymentUICommonUtilities.footerxpath);
		validateFooterLink(driver,PaymentUICommonUtilities.footerFour,PaymentUICommonUtilities.footerxpath);
		validateFooterLink(driver,PaymentUICommonUtilities.footerFive,PaymentUICommonUtilities.footerxpath);
		validateFooterLink(driver,PaymentUICommonUtilities.footerSix,PaymentUICommonUtilities.footerxpath);
		validateFooterLink(driver,PaymentUICommonUtilities.footerSeven,PaymentUICommonUtilities.footerxpath);
		validateFooterLink(driver,PaymentUICommonUtilities.footerEight,PaymentUICommonUtilities.footerxpath);
		validateFooterLink(driver,PaymentUICommonUtilities.footerNine,PaymentUICommonUtilities.footerxpath);
		validateFooterLink(driver,PaymentUICommonUtilities.footerTen,PaymentUICommonUtilities.footerxpath);*/
	}





	@Test(priority=9)
	public void secondRetryMakePayment() throws InterruptedException {
		try {
			int flag=1;
			click(driver,PaymentUI_CommonUtilities.creditCardPaymentxpath);
			while (flag<3){
				fillInInvalidCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
				click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
				waitOnAParticularElement(driver,PaymentUI_CommonUtilities.errorTextInvalidCCDetailsxpath);
				validateErrorForInvalidCCDetails(driver,PaymentUI_CommonUtilities.errorTextInvalidCCDetailsxpath);
				flag++;
			}

			fillValidCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath);
			if(driver.findElement(By.xpath(PaymentUI_CommonUtilities.makePaymentbutton)).isEnabled()){
				click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
				validateNoErrorForValidCCDetails(driver,PaymentUI_CommonUtilities.errorTextInvalidCCDetailsxpath);
			}

		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false);
		}
	}


	@Test(priority=6)
	public void authenticateVariousDetails(){

		try {
			validateSectionDetails(driver,PaymentUI_CommonUtilities.sectionTripDetails,PaymentUI_CommonUtilities.sectionxpath);
			validateSectionDetails(driver,PaymentUI_CommonUtilities.sectionTravellers,PaymentUI_CommonUtilities.sectionxpath);
			validateSectionDetails(driver,PaymentUI_CommonUtilities.sectionItinerary,PaymentUI_CommonUtilities.sectionxpath);
			validateSectionDetails(driver,PaymentUI_CommonUtilities.sectionPricingDetails,PaymentUI_CommonUtilities.sectionxpath);
			validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldTripId,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldAdult,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldTrain,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldDeparture,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldArrival,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldClass,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldQuota,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldUpdatedAvailability,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldOtherRailwayCharges,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
			validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldAgentServiceCharges,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
			validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldGST,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
			validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldConvenienceFee,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
			validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldInuranceCharge,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
			validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldRefundType,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false);
		}
	}

	@Test(priority=7)
	public void licenseAgreementPolicy() throws Exception{
		
		try {
			click(driver,PaymentUI_CommonUtilities.licenseAgreementxpath);
			validateErrorWhenLicenseCheckboxIsUnchecked(driver,PaymentUI_CommonUtilities.licenseAgreementxpath);
			Thread.sleep(5000);
			click(driver,PaymentUI_CommonUtilities.licenseAgreementxpath);
			validateLicenseAgreementPolicyLinks(driver,PaymentUI_CommonUtilities.licenseAgreementFieldBookingPolicy,PaymentUI_CommonUtilities.licenseAgreementFieldxpath);
			validateLicenseAgreementPolicyLinks(driver,PaymentUI_CommonUtilities.licenseAgreementFieldPrivacyPolicy,PaymentUI_CommonUtilities.licenseAgreementFieldxpath);
			validateLicenseAgreementPolicyLinks(driver,PaymentUI_CommonUtilities.licenseAgreementFieldTermsAndConditions,PaymentUI_CommonUtilities.licenseAgreementFieldxpath);
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false);
		}
	}

	@Test(priority=8)
	public void imagesAndText() throws InterruptedException{
		try {
			validateIfImagesArePresent(driver,PaymentUI_CommonUtilities.imagesXpath,PaymentUI_CommonUtilities.imageNameVisa);
			validateIfImagesArePresent(driver,PaymentUI_CommonUtilities.imagesXpath,PaymentUI_CommonUtilities.imageNameMasterCard);
			click(driver,PaymentUI_CommonUtilities.creditCardPaymentxpath);
			validateIfImagesArePresent(driver,PaymentUI_CommonUtilities.imagesXpath,PaymentUI_CommonUtilities.imageNameVisa);
			validateIfImagesArePresent(driver,PaymentUI_CommonUtilities.imagesXpath,PaymentUI_CommonUtilities.imageNameMasterCard);
			validateIfImagesArePresent(driver,PaymentUI_CommonUtilities.imagesXpath,PaymentUI_CommonUtilities.imageNameAmex);
			validateIfPresent(driver,PaymentUI_CommonUtilities.irctcLogoxpath);
			validateIfPresent(driver,PaymentUI_CommonUtilities.totalAmountWithTaxXpath);
			validateIfPresent(driver,PaymentUI_CommonUtilities.storedCardTextXpath);
		}
		
		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false);
		}
		
	}


	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass
	public void tearDown(){
		browserClose(driver);
	}

}


