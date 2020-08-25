/* AUTHOR: PRAKHAR CHATTERJEE
 * EMAIL: prakhar.chatterjee@cleartrip.com
 * Documentation: This Class is focussed on automating the APIs related to new Payments UI.
 */

package paymentsUI;

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

import domains.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentUI_Validation extends PaymentNodeJS{

	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;


	@BeforeClass 
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","",getNewDate_TripID());
		qaUrl = qaurl;
		Url = qaUrl+ fetchPaymentURL(resp);
	}

	@Test(priority=1)
	public void firePaymentURLandValidatePaymentModes() throws Exception{

			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);
			elementVisible(driver, By.xpath("//ul[@id='paymentModeTab']"), 10);
			validatePaymentURLLoad(driver,Url);
			Cookie cookieName = new Cookie("ct-auth", "l6Z4I%2B04vrKt5Gx5nozaFyIseNC%2F5P86kzHHuN%2BPzKN4kr%2Bi%2B8B1m%2FB3fm8feq89U1wSr%2BJEjIe7OwdBdJFDtD0X9xTlhHu6stvjt4kgx7V%2BU5jhnqDtCyzJKu4r3kS7Dzw77SFNX94pdJKH9ncdBCJZfiYsA%2BxsMZOCWYm%2F5RhvQr3zj8pfk%2FTLaX5oxC%2FmMyFYg0rsrdIWJGsJLs3nG583asuyXjEFE9HqevjGqPQwtTWw3v1Ps%2FeXcTnweK96BAslgpJfYKqT8TQ9YUyOz9Hd48BLn8TPZ64oXHdiw9WArciwjup573ATWfGIR6KzSPC%2F0YxQBY8N9T2dntLTLwF3mcBECRuqgHzn%2Bi1153OwIoMo%2BwfT%2FGlbG1ftVLochM6pqdxHxCtCG4wWZpi%2FblzMcH4QxwyXRYtvbXf8Kr0uDeTP%2Fbrb9ybJ0uaGfelr%2BlKW75DV2cw0UTjQ7wxTBXUbkYGBSGMD8WnnVlChJW04snx7V%2BNTla2AfO3y6t56vdtyuN44Cw9fCyJpDCyE1vzru9iFV7Ni%2Bz4qUj1wJ3pelHswRA5V2l1g79fciujmH%2FlCz0LimGX5mv0W%2FEn0EjvVC2OxVAXWGRLidTk2kEY%3D");
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
			//validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeADCB,PaymentUI_CommonUtilities.paymentModexpath);
		

	}

	@Test(priority=2)
	public void emptyCreditCardErrorValidation() throws Exception{
			waitForElementVisibility(driver,By.xpath("//p[text()='Credit Card']"), 15);
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

	@Test(priority=3)
	public void emptyDebitCardErrorValidation() throws Exception{

	
			waitForElementVisibility(driver,By.xpath("//p[text()='Debit Card']"), 15);
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

	@Test(priority=4, enabled=false)
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





	@Test(priority=11)
	public void secondRetryMakePayment() throws Exception {
		

			resp = payUIget("BookApp/GetPay","",getNewDate_TripID());
			qaUrl = qaurl;
			Url = qaUrl+ fetchPaymentURL(resp);
			driver.get(Url);
			elementVisible(driver, By.xpath("(//label[@class='checkbox-round'])[1]"), 10);
			List<WebElement> walletCheckBoxList = driver.findElements(By.xpath("(//label[@class='checkbox-round'])[1]"));
			if(walletCheckBoxList.size()!=0) {
				click(driver,PaymentUI_CommonUtilities.walletCheckBox);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			}
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
				//validateNoErrorForValidCCDetails(driver,PaymentUI_CommonUtilities.errorTextInvalidCCDetailsxpath);
			}

		

		
	}


	@Test(priority=6)
	public void authenticateVariousDetailsLoggedin() throws Exception{

			//Commenting Out as most of these fields are removed
			//validateSectionDetails(driver,PaymentUI_CommonUtilities.sectionTripDetails,PaymentUI_CommonUtilities.sectionxpath);
			//validateSectionDetails(driver,PaymentUI_CommonUtilities.sectionTravellers,PaymentUI_CommonUtilities.sectionxpath);
			//validateSectionDetails(driver,PaymentUI_CommonUtilities.sectionItinerary,PaymentUI_CommonUtilities.sectionxpath);
			//validateSectionDetails(driver,PaymentUI_CommonUtilities.sectionPricingDetails,PaymentUI_CommonUtilities.sectionxpath);
			waitForElementVisibility(driver,By.xpath("//p[contains(text(),'Travellers')]"),15);
			validateIfPresent(driver,PaymentUI_CommonUtilities.sectionTravellersXpath);
			validateIfPresent(driver,PaymentUI_CommonUtilities.sectionItineraryXpath);
			
			//validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldTripId,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			//validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldAdult,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			//validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldTrain,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			validateIfPresent(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldTrainXpath);
			//validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldDeparture,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			//validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldArrival,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			//validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldClass,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			//validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldQuota,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			//validatetriptravellersanditineraryDetails(driver,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldUpdatedAvailability,PaymentUI_CommonUtilities.triptravelleranditinerarysectionfieldxpath);
			/*validateIfPresent(driver,PaymentUI_CommonUtilities.showFareBreakUp);
			click(driver,PaymentUI_CommonUtilities.showFareBreakUp);
			validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldOtherRailwayCharges,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
			validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldAgentServiceCharges,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
			//validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldGST,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
			validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldConvenienceFee,PaymentUI_CommonUtilities.pricingsectionfieldxpath);*/
			//validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldInuranceCharge,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
			//validatepricingSectionDetails(driver,PaymentUI_CommonUtilities.pricingsectionfieldRefundType,PaymentUI_CommonUtilities.pricingsectionfieldxpath);
	
	}

	@Test(priority=7)
	public void licenseAgreementPolicy() throws Exception{

			click(driver,PaymentUI_CommonUtilities.licenseAgreementxpath);
			validateErrorWhenLicenseCheckboxIsUnchecked(driver,PaymentUI_CommonUtilities.licenseAgreementxpath);
			Thread.sleep(5000);
			click(driver,PaymentUI_CommonUtilities.licenseAgreementxpath);
			validateLicenseAgreementPolicyLinks(driver,PaymentUI_CommonUtilities.licenseAgreementFieldBookingPolicy,PaymentUI_CommonUtilities.licenseAgreementFieldxpath);
			validateLicenseAgreementPolicyLinks(driver,PaymentUI_CommonUtilities.licenseAgreementFieldPrivacyPolicy,PaymentUI_CommonUtilities.licenseAgreementFieldxpath);
			validateLicenseAgreementPolicyLinks(driver,PaymentUI_CommonUtilities.licenseAgreementFieldTermsAndConditions,PaymentUI_CommonUtilities.licenseAgreementFieldxpath);
		
	}

	@Test(priority=8)
	public void imagesAndText() throws InterruptedException{
		
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

	@Test(priority=9)
	public void validateInvalidExpiry() throws Exception {
			waitForElementVisibility(driver,By.xpath("//p[text()='Credit Card']"), 15);
			fillValidAmexCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath(PaymentUI_CommonUtilities.expiryYearxpath)).sendKeys(PaymentUI_CommonUtilities.invalidAmexExpiryYear);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			waitForElementVisibility(driver,By.xpath("//input[@type='submit']"), 15);
			click(driver,PaymentUI_CommonUtilities.amexGatewayAuthenticationSubmitxpath);
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			waitForElementVisibility(driver,By.xpath("//div[text()='Oops! Your payment failed. If you were charged, any amount deducted will be reversed automatically.']"), 20);
			validateIfPresent(driver, PaymentUI_CommonUtilities.invalid3DFailureXpath);
			validateIfPresent(driver,PaymentUI_CommonUtilities.makePaymentbutton);
		
	}
	
	
	@Test(priority=10)
	public void validateInvalidCvv() throws Exception {
			elementVisible(driver, By.xpath("(//label[@class='checkbox-round'])[1]"), 10);
			List<WebElement> walletCheckBoxList = driver.findElements(By.xpath("(//label[@class='checkbox-round'])[1]"));
			if(walletCheckBoxList.size()!=0) {
				click(driver,PaymentUI_CommonUtilities.walletCheckBox);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			}
			click(driver,PaymentUI_CommonUtilities.creditCardPaymentxpath);
			fillValidAmexCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath(PaymentUI_CommonUtilities.cvvNumberxpath)).sendKeys(PaymentUI_CommonUtilities.invalidAmexCvv);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			waitForElementVisibility(driver,By.xpath("//input[@type='submit']"), 15);
			click(driver,PaymentUI_CommonUtilities.amexGatewayAuthenticationSubmitxpath);
			driver.get(Url);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			validateIfPresent(driver,PaymentUI_CommonUtilities.paymentSuccessHeaderTextXpath);
		
		
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


