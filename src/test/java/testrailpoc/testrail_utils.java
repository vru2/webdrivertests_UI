package testrailpoc;

import static common.CachedProperties.cacheUrlInstance;
import static common.CachedProperties.dataInstance;
import static common.CachedProperties.dbInstance;
import static common.CachedProperties.instance;
import static common.CachedProperties.objectReportInstance;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestResult;

import common.CachedProperties;
import testrail.APIClient;
import testrail.APIException;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class testrail_utils {
	DefaultHttpClient httpClient=new DefaultHttpClient();
	public CachedProperties common = instance();
	public CachedProperties dataFile = dataInstance();
	public CachedProperties db = dbInstance();
	public CachedProperties caheUrl = cacheUrlInstance();
	public CachedProperties objectRepos = objectReportInstance();
	

	
	public void gettest() throws MalformedURLException, IOException,
	APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		JSONObject c = (JSONObject) client.sendGet("get_case/1");
		System.out.println(c);
		System.out.println(c.get("title"));
	}

	public int get_projects() throws MalformedURLException, IOException,
	APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		JSONArray c = (JSONArray) client.sendGet("get_projects");
		System.out.println(c);
		return 0;
	}


	public int get_runs(String projectid) throws MalformedURLException, IOException,
	APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		JSONArray c = (JSONArray) client.sendGet("get_runs/"+projectid+"&is_completed=0");
		System.out.println(c);
		int avbRuns=c.size();
		System.out.println(avbRuns);
		return avbRuns;
	}

	public int get_runid(String projectid) throws MalformedURLException, IOException,
	APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		JSONArray c = (JSONArray) client.sendGet("get_runs/"+projectid+"&is_completed=0");
		JSONObject obj = (JSONObject) c.get(0);
		String runid =  obj.get("id").toString();
		//System.out.println(runid);
		return Integer.parseInt(runid);
	}




	//POST index.php?/api/v2/add_milestone/:project_id 
	public int add_milestone(String ReleaseID, String projectid) throws MalformedURLException, IOException,
	APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		Map data = new HashMap();
		data.put("name", "Release "+ReleaseID);
		//data.put("comment", "This test worked fine!");
		System.out.println(data);
		JSONObject r = (JSONObject) client.sendPost("add_milestone/"+projectid, data); //add_result_for_case/run_id/case_id
		Object id =r.get("id");
		return Integer.valueOf(id.toString());

	}






	//POST index.php?/api/v2/add_run/:project_id

	public int add_run(String projectid, String RunName,int suiteID,String ReleaseID) throws MalformedURLException, IOException,
	APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		Map data = new HashMap();
		data.put("suite_id", suiteID); //suite id of b2cair - section or testsuite
		data.put("name", RunName); //
		data.put("milestone_id", add_milestone(ReleaseID,projectid));
		//data.put("assignedto_id", 1);
		System.out.println(data);
		JSONObject r = (JSONObject) client.sendPost("add_run/"+projectid, data); //add_result_for_case/run_id/case_id
		Object id =r.get("id");
		return Integer.valueOf(id.toString());
	}


	public void get_tests(String projectid, String RunName,String ReleaseID) throws MalformedURLException, IOException, APIException {
		if(get_runs(projectid)==0){
			int suiteID = get_suites(projectid);
			System.out.println("suiteID : "+suiteID );
			APIClient client = new APIClient(common.value("testrailurl"));
			client.setUser(common.value("trusrname"));
			client.setPassword(common.value("trpwd"));
			JSONArray c = (JSONArray) client.sendGet("get_tests/"+add_run(projectid,RunName,suiteID,ReleaseID));
			System.out.println(c);
		}else{
			System.out.println("Closing Existing Run before creating new run in your project ");
		System.exit(0);
		}
	}


	public void get_statuses() throws MalformedURLException, IOException, APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		JSONArray c = (JSONArray) client.sendGet("get_statuses");
		System.out.println(c);
	}


	public int get_suites(String projectid) throws MalformedURLException, IOException, APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		JSONArray c = (JSONArray) client.sendGet("get_suites/"+projectid);
		System.out.println(c);
		JSONObject obj = (JSONObject) c.get(0);
		String suiteid =  obj.get("id").toString();
		return Integer.parseInt(suiteid);
		
	}
	
	public void get_cases(String projectid, int suiteID) throws MalformedURLException, IOException, APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		JSONArray c = (JSONArray) client.sendGet("get_cases/"+projectid+"&suite_id="+suiteID);
		System.out.println(c);
	}
	
	public void get_sections() throws MalformedURLException, IOException, APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		JSONArray c = (JSONArray) client.sendGet("get_sections/6&suite_id=6");
		System.out.println(c);
	}
	
	


	public void updateTest(int runId ,int caseId, int status) throws MalformedURLException, IOException,
	APIException {
		APIClient client = new APIClient(common.value("testrailurl"));
		client.setUser(common.value("trusrname"));
		client.setPassword(common.value("trpwd"));
		Map data = new HashMap();
		data.put("status_id", new Integer(status));
		//data.put("comment", "This test worked fine!");
		/*System.out.println(data);
		System.out.println("add_result_for_case/"+runId+"/"+caseId);*/
		JSONObject r = (JSONObject) client.sendPost("add_result_for_case/"+runId+"/"+caseId, data); //add_result_for_case/run_id/case_id

	}


	public void UpdateTestRail(ITestResult result, String projectid) throws MalformedURLException, IOException, APIException {
	
		testrail_utils api = new testrail_utils();
		int runID =api.get_runid(projectid);
		String testName = result.getName();
		String caseid = testName.substring(testName.lastIndexOf('_')+1);
		if(result.getStatus()==1){
			api.updateTest(runID, Integer.parseInt(caseid), result.getStatus());
		}else if(result.getStatus()==2){
			api.updateTest(runID, Integer.parseInt(caseid), 5);
		}else{
			api.updateTest(runID, Integer.parseInt(caseid), 4);	
		}
	}


}
