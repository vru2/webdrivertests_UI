package com.cleartrip.payment.nodejs;

import java.util.Random;

public class PaymentUI_CommonUtilities {

	protected static String creditOrDebitCardPageTextxpath= "//p[text()='#']";
	public static String creditCardPageText= "Enter your credit card details.";
	public static String debitCardPageText= "Enter your debit card details.";
	protected static String convenienceTextxpath= "//p[contains(text(),'#')]";
	public static String convenienceFeeText= "Includes a convenience fee of";
	protected static String cardNumberxpath= "//input[contains(@id,'cardNumber')]";
	protected static String expiryMonthxpath = "//select[contains(@id,'expiryMonth')]";
	protected static String expiryYearxpath = "//select[contains(@id,'expiryYear')]";
	protected static String cardHolderNamexpath = "//input[contains(@id,'name')]";
	protected static String cvvNumberxpath = "//input[contains(@id,'cvv')]";
	protected static String cardNumbererrorxpath = "//input[contains(@id,'cardNumber')]/../../../following-sibling::p[text()='errortext']";
	protected static String cardHoldererrorxpath= "//input[contains(@id,'name')]/../../following-sibling::p[text()='errortext']";
	protected static String cardCvverrorxpath= "//input[contains(@id,'cvv')]/../../../../following-sibling::p[text()='errortext']";
	protected static String expiryerrorxpath= "//select[contains(@id,'expiryMonth')]/../../../../../following-sibling::div/p[text()='errortext']";
	protected static String errortextCardNumber= "Please enter a valid card number";
	protected static String errortextExpiryMonth= "Please enter a valid expiry month";
	protected static String errortextExpiryYear= "Please enter a valid expiry year";
	protected static String errortextCardHolderName= "Please enter a valid name";
	protected static String errortextCvv= "Please enter a valid cvv";
	public static String errortextCvvXpath= "//p[text()='Please enter a valid cvv']";
	public static String makePaymentbutton= "//button[text()='Make Payment']";
	protected static String debitCardPaymentxpath= "//p[text()='Debit Card']";
	protected static String creditCardPaymentxpath= "//p[text()='Credit Card']";
	protected static String footerxpath= "//a[text()='footername']";
	protected static String footerOne= "About Us";
	protected static String footerTwo= "Careers";
	protected static String footerThree= "FAQs";
	protected static String footerFour= "Support";
	protected static String footerFive= "Blog";
	protected static String footerSix= "Mobile";
	protected static String footerSeven= "Cleartrip for Business";
	protected static String footerEight= "Gift Cards";
	protected static String footerNine= "Referral Program";
	protected static String footerTen= "Holiday Planner";
	protected static String sectionTripDetails= "Trip Details";
	protected static String sectionTravellers= "Travellers";
	protected static String sectionItinerary=  "Itinerary";
	protected static String sectionPricingDetails= "Pricing Details";
	protected static String sectionxpath= "//p[text()='sectionname']";
	protected static String triptravelleranditinerarysectionfieldxpath= "//span[text()='sectionfieldname']";
	protected static String pricingsectionfieldxpath= "//p[text()='pricingsectionfieldname']";
	protected static String recaptchaxpath= "//label[contains(@id,'recaptcha')]";
	protected static String triptravelleranditinerarysectionfieldTripId= "TripId";
	protected static String triptravelleranditinerarysectionfieldAdult= "Adult";
	protected static String triptravelleranditinerarysectionfieldTrain= "Train";
	protected static String triptravelleranditinerarysectionfieldDeparture= "Departs";
	protected static String triptravelleranditinerarysectionfieldArrival= "Arrives";
	protected static String triptravelleranditinerarysectionfieldClass= "Class";
	protected static String triptravelleranditinerarysectionfieldQuota= "Quota";
	protected static String triptravelleranditinerarysectionfieldUpdatedAvailability= "Updated Availability";
	protected static String pricingsectionfieldOtherRailwayCharges= "Other Railway Charges";
	protected static String pricingsectionfieldAgentServiceCharges= "Agent Service Charge";
	protected static String pricingsectionfieldGST= "(Inclusive of GST)";
	protected static String pricingsectionfieldConvenienceFee= "Convinence Fee";
	protected static String pricingsectionfieldInuranceCharge= "Insurance Charges";
	protected static String pricingsectionfieldRefundType= "Refund Type";
	public static String validCardNumber= "5123456789012346";
	public static String validExpiryMonth= "May (05)";
	public static String validExpiryYear= "2020";
	public static String validCvv= "123";
	public static String validCardName= "Tester Test";
	public static String invalidCardNumber= "5123456789012345";
	public static String validAmexCardNumber= "345678000000007";
	public static String validAmexExpiryYear= "2021";
	public static String validAmexCvv= "1234";
	protected static String paymentModexpath= "//p[text()='paymentmode']";
	protected static String paymentModeCC= "Credit Card";
	protected static String paymentModeDC= "Debit Card";
	protected static String paymentModeNB= "Net Banking";
	protected static String paymentModeUPI= "UPI";
	protected static String paymentModeADCB= "ADCB TouchPoints";
	protected static String paymentModeAP= "Apple Pay";
	protected static String paymentModeStoredCard= "Stored Cards";
	protected static String errorTextInvalidCCDetailsxpath= "//div[contains(@class,'retry')]";
	protected static String captchaErrorXpath= "//p[text()='captchaerrormessage']";
	public static String captchaErrorName= "Please validate captcha";
	public static String errorTextInvalidCC="Error in credentials entered. Verify your details and try again.";
	protected static String licenseAgreementxpath= "//span[contains(text(),'I understand and agree to the rules and restrictions of this fare, ')]/../label[contains(@class,'checkbox')]";
	public static String licenseAgreementErrorText= "Please accept the terms and conditions to proceed with this booking.";
	public static String licenseAgreementErrorTextXpath= "//p[text()='Please accept the terms and conditions to proceed with this booking.']";
	protected static String licenseAgreementFieldxpath= "//a[text()='#']";
	protected static String licenseAgreementFieldBookingPolicy= "Booking policy";
	protected static String licenseAgreementFieldPrivacyPolicy= "Privacy Policy" + " ";
	protected static String licenseAgreementFieldTermsAndConditions= " "+ "Terms & Conditions" + " ";
	protected static String imagesXpath= "//a[contains(@title,'imagename')]";
	protected static String imageNameVisa= "Verified by Visa";
	protected static String imageNameMasterCard= "MasterCard SecureCode";
	protected static String imageNameAmex= "SafeKey";
	protected static String irctcLogoxpath= "//img[@class='irctcLogo']";
	protected static String totalAmountWithTaxXpath= "//span[text()='Total inclusive all taxes']";
	protected static String storedCardTextXpath= "//p[text()='Save this card and make single-click payments']";
	public static String storedCardCheckbox= "//p[text()='Save this card and make single-click payments']/../label[contains(@class,'checkbox')]";
	public static String paymentSuccessHeaderTextXpath= "//h1[text()='Your payment was successful!']";
	public static String paymentSuccessMessageTextXpath= "//span[text()=' to view your booking details and Trip ID.']";
	protected static String amexGatewayAuthenticationSubmitxpath= "//input[@type='submit']";
	public static String retry3DFailureHeaderXpath= "//h1[text()='Uh-oh! Something went wrong']";
	public static String retry3DFailureMessageXpath= "//span[text()='We weren't able to complete the payment. Please try again in 15-20 minutes.']";
    public static String amexGatewayAuthenticationFailureValue= "(N) Authentication Failed";
    public static String amexGatewayAuthenticationFailureXpath= "//select[contains(@id,'AuthResult')]";
    public static String paymentInProgressHeaderXpath="//h1[text()='Payment is in progress']";
    public static String paymentInProgressMessageXpath= "//span[text()='The payment for this itinerary is already in progress. Please wait 15-20 minutes and then visit your profile to verify the booking status.']";
    public static String upiErrorTextXpath= "//p[text()='Please select any UPI payment method.']";
    public static String upiModeXpath= "//p[text()='UPI']";
    public static String nbModeXpath= "//p[text()='Net Banking']";
    public static String storedCardXpath= "//p[text()='Stored Cards']";
    public static String nbErrorTextXpath="//p[text()='Please select your bank']";
    public static String amexCardIsPresentEndingWithSeven= "//p[contains(text(),'345678')]";
    public static String storedCardCvvBoxXpath= "//p[contains(text(),'345678')]/../following-sibling::div/div/input[@name='cvv']";
    public static String manageTripsXpath= "//a[text()='Manage trips']";
    public static String expresswayXpath= "//a[text()='Expressway']";
    public static String removeStoredCardXpath= "//a[@title='Remove this card']";
    public static String walletCheckBox= "(//label[@class='checkbox-round'])[1]";
    
	public static int generateFiveDigitRandomNumber(){
		Random r = new Random( System.currentTimeMillis() );
		return 10000 + r.nextInt(20000);

	}
	
	
}
