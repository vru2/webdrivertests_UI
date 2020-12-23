	/* AUTHOR: PRAKHAR CHATTERJEE
 * EMAIL: prakhar.chatterjee@cleartrip.com
 * Documentation: This Class is focussed on automating the APIs related to promoservice.
 */


package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_PromoService extends API_PaymentCommon1 {

	@Test(priority=1)
	public void get_PromotionFromTripRefAndPromoId() throws Exception{
		Response resp = promoGet("PromoTripRefAndId","");			
		validation_PromoService("PromoTripRefAndId", resp);

	}


	@Test(priority=2)
	public void get_PromoGroupFromPromoRef() throws Exception{
		Response resp = promoGet("PromoGroupFromPromoRef", "");
		validation_PromoService("PromoGroupFromPromoRef", resp);
	}

	@Test(priority=3)
	public void get_PromotionsFromTripRef() throws Exception{
		Response resp = promoGet("PromoFromTripRef", "");
		validation_PromoService("PromoFromTripRef", resp);	
	}

	@Test(priority=4)
	public void get_PromotionsFromOnlyPromoId() throws Exception{
		Response resp = promoGet("PromoFromPromoId", "");
		validation_PromoService("PromoFromPromoId", resp);
	}
	
	@Test(priority=5)
	// This test method is written on a high level. We would need to parse the JSON to its max depth in order to ensure that we do not miss out on potential bugs.
	public void get_PromotionGroupsFromCreatedDate() throws Exception{
		Response resp = promoGet("PromoGroupsFromCreatedDate", "");
		validation_PromoService("PromoGroupsFromCreatedDate", resp);
	}
	
	@Test(priority=6)
	// This test method is written on a high level. We would need to parse the JSON to its max depth in order to ensure that we do not miss out on potential bugs.
	public void get_PromotionsGroupForAUpdatedDate() throws Exception{
		Response resp = promoGet("PromoGroupsForAUpdatedDate", "");
		validation_PromoService("PromoGroupsForAUpdatedDate", resp);
	}
	
	@Test(priority=7)
	public void get_PromotionsGroupForACreatedAndUpdatedDate() throws Exception{
		Response resp = promoGet("PromoGroupsForACreatedAndUpdatedDate", "");
		validation_PromoService("PromoGroupsForACreatedAndUpdatedDate", resp);
	}

	@Test(priority=9,dependsOnMethods = { "createPromo" })
	public void activatePromo() throws Exception{
		Response resp = promoPost("ActivatePromo","");
		validation_PromoService("ActivatePromo", resp);
	}
	
	
	@Test(priority=8)
	public void createPromo() throws Exception{
		Response resp = promoPost("CreatePromo","");
		//validation_PromoService("CreatePromo", resp);
	}
	
/*	@Test(priority=10)
	public void createPromoGroup() throws Exception{
		Response resp = promoPost("CreatePromoGroup", "");
		validation_PromoService("CreatePromoGroup", resp);
	}
	
	@Test(priority=11)
	public void updatePromo() throws Exception{
		Response resp = promoPost("UpdatePromo", "");
		validation_PromoService("UpdatePromo", resp);
	}*/

}
