package com.cleartrip.local.camp.others;

import java.util.ArrayList;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AddingCollectionsSA extends CampActivities {

	@Parameters({ "user" })
	@Test
	public void manageDataMasterAddTTDCollectionsSA_36439(String user) throws Exception {
		ArrayList<String> inputTypes = new ArrayList<>();
		String categoriesType = "Collections", productType = "Things to do";
		String categoryName = "Auto_qa2_collectionName", categoryDesc = "Auto_qa2_description";
		inputTypes.add(categoriesType);
		inputTypes.add(productType);
		inputTypes.add(categoryName);
		inputTypes.add(categoryDesc);
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/master_list");
		addMasterDataCategoryCollection(inputTypes);
	}

}
