package test.java.testrailpoc;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.Test;

import test.java.testrail.APIException;


public class TestRail {
	
	/*@Test
	public void getTest() throws MalformedURLException, IOException, APIException{
		testrail_utils api = new testrail_utils();
		//api.gettest();
		
		
		
	}*/

	/*LOCAL id: 11, TRAVEL id:12, MOBILE :13 
	 * Travel Test Suites id : 14
	 * Local test Suite id :22
	 * */
	
	/*Pre-condition - Make sure no runs are active in test rail*/
	
	@Test
	public void updatetest() throws MalformedURLException, IOException, APIException{
		testrail_utils api = new testrail_utils();
		//api.get_projects();
		//api.get_runs("12");
		//api.add_milestone("6"); // This will create milestone to project
		//api.add_run("12", "RUN #2", 14, "11.6");	// will add the test run to milestone param projectid, RunName, suiteID, ReleaseID
		//api.get_cases("12",14);  // will return the id of test case.requires project id & suit IDS.
		api.get_tests("11","RUN #1","11.41" ); // params passed projectid, run id, releaseid.
		//api.updateTest();
		//api.get_statuses();
		//api.get_runid("12");
		//api.get_suites("12"); //project id
		//api.get_sections();
		
		
	}

	

}
