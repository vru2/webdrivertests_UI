package Locals_APIs;




import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
//import org.seleniumhq.jetty7.util.ajax.JSON;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Fitness_OracleDB_To_ESPush {



    @Test
    public void DbPushActivity(){
    
    try
    {

    HttpUriRequest request = new HttpPost("http://qa2.cleartrip.com/activities/api/index/fit/activity");
    
    
    //PASS below content with request
    
    /*{"id":5628,"desc":"Add a touch of exotic to your workout with a Bellydance class. "
    		+ "Bellydance is a meditteranean dance style gaining popularity globally. "
    		+ "This class creates a full body workout and incorporates isolation techniques to target core muscles."
    		+ "","act_name":"Belly Dance","sup_act_id":"783:7","req":null,"act_tp_id":"7","dif_lvl":null,"act_cat_id":"65",
    		"act_loc_id":2961,"sup_id":1,"sup_name":"FITICKET","fitness_category":{"id":65,"name":"DANCE","supcatid":"4","desc":null,
    	"dur":null,"tng_2_cry":null,"rec_fr":null,"preq":null,"act_tp":null},"activity_image_infos":[{"id":14860596,
    		"img_pth":null,"f_nm":"3604403f-53ab-4c5c-956c-03aa76c41e13.png"},{"id":14860598,"img_pth":null,"f_nm":"dc36bdb3-b501-4004-8b34-b91e5cac873d.jpg"}],
    		"fitness_schedules":[{"id":26244,"fit_act_id":5628,"st_tm":"19:30","ed_tm":"20:30","st_dt":"31/12/2015","ed_dt":"31/12/2016","bk_id":8212,
    			"f_d":false,"capc":5}],"subscription_venue":{"id":2961,"name":"Universal Power Martial Arts","logo":"0","ct_city_id":null,
    		"ct_city_name":null,"location":{"lat":18.467348,"lon":73.905799},"abt":"NIVERSAL POWER MARTIAL ARTS is an academy for overall martial arts and "
    				+ "fitness training. This training helps in bringing Body, Mind \u0026 Spirit together. "
    				+ "This leads towards a unique combination of Strong \u0026 beautiful Body, Sharp \u0026 calm Mind, "
    				+ "and an Indomitable Spirit that never gives up in any unfavorable situation in life.? "
    				+ "We can actually discover a better and stronger self within us. People suffering from various kinds of "
    				+ "diseases also get long term benefits.?Varieties of trainings (Martial arts, Physical fitness and yoga) "
    				+ "are provided based on suitability and individual?s interest.? Trainings are available for the age group",
    				"sup_pl_id":"783","pl_tp_id":"1","web":"http://universalpowermartialarts.com/Home.html","act_cnt":["0","2","3","2","3","2","2"],
    				"loc":"MOHAMADWADI","activity_location_info":{"city_id":null,"id":753504,"add":"Dorabjee's Royale Heritage Mall Shop "
    						+ "No. 25, Ground floor, Survey No. 25/5, NIBM Ext. Next to Vibgyor School, Dorabjee Paradise, Pune 411060, Mohammed Wadi, Pune, "
    						+ "Maharashtra 411060","lat":"18.467348","lon":"73.905799","ct_nm":"Pune"},"place_type":{"id":1,"name":"Gym"}}}
    */
    request.addHeader("Content-Type","application/json");
    request.addHeader("Accept","application/json");

    /*
    request.addHeader("nonce","PYTFWX");
    request.addHeader("APIkey","He_6_5lBkhA58wkDW_b_1rjM0nu0s5dIiF_DVcvbUAY");		
    request.addHeader("content-SHA256","3401694c20cee74b7186effd519ab41f476b4590b2595b1ac0ae1544ee1120a2");
    request.addHeader("Hmac","a4d5c5fab72ef0ff2d1ca4292d2ecd0c2adcb0aaf803110982847c47fda18f0a");
    */    
    
    //System.out.println(request);
    HttpResponse response = HttpClientBuilder.create().build().execute( request );
    
    //System.out.println("my response code" + response);
    Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "Response code");

    BufferedReader rd = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));
    String line = "";
    StringBuffer result = new StringBuffer();
    while ((line = rd.readLine()) != null)
    {
        result.append(line);
    }
     //System.out.println("Response Nandu:"+result);
    }catch (Exception e)
    {
        //System.out.println(e);
    }
}
 /*   {
        JsonPath jsonPath = new JsonPath(given().header("nonce","wkRjBy")
                .header("APIkey","He_6_5lBkhA58wkDW_b_1rjM0nu0s5dIiF_DVcvbUAY")
                .header("content-SHA256","33a9b864607d547aff836e869bc38b56c5df1227ef80d730f3a5edd8d6c4133b")
                .header("Hmac","0f2c387eecbb5366f6d9f74e1fc4c90def4fcdaf24e3f518b6bc99ccde759315").contentType(ContentType.JSON)
                .post(Fitness_ActivitiesPath.BASEURL2.getUri() + Fitness_ActivitiesPath.DBPUSH.getUri()).asString());

        //System.out.println("Response:"+jsonPath.get());

    }
*/}
