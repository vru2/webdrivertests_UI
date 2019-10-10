package headLessBooking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SuppressWarnings("deprecation")
public class Ratingandreviews {
    
    DefaultHttpClient httpClient=new DefaultHttpClient();
    JDBCPreparedStatement jdbc = new JDBCPreparedStatement();
    protected  List<Cookie> cookiesHQ = null;
    CookieStore cookHQ;
    String host = "qa2.cleartrip.com";
    
    static Random rand = new Random();
    List<String> reviewId;
    String activityId = "799802";
    
    public void setCookies(CookieStore cookHQ) {
        this.cookHQ=cookHQ;
    }

    public CookieStore getCookies() {
        return this.cookHQ;
    }
    
    //it should not show thumps up and down  if rating <3
    //min no of ratings:5 to calculate the 
    @DataProvider
    public static  Object [ ][ ] rating1() throws Exception {
        return new Object [ ] [ ] {
                {"Q1701110193", 5,"I am in !!",0,0,0,32},
                {"Q1701100215", 4,"I am in !!",0,0,0,32},
                {"Q1701050156", 4,"I am in !!",0,0,0,32},
                {"Q1701050142", 4,"I am in !!",0,0,0,32},
                {"Q1701050141", 4,"I am in !!",0,0,0,32},
                {"Q1701041439", 2,"",0,0,0,32},
                {"Q1701041397", 2,"",0,0,0,32},
                {"Q1701041394", 2,"",0,0,0,32},
                {"Q1701040551", 2,"",0,0,0,32}, 
                {"Q1701040545", 2,"",0,0,0,32}, 
                {"Q1701110218", 2,"",0,0,0,32}, 
                {"Q1701110222", 2,"",0,0,0,32}, 
                
                
        };
    }
    
    //it should not show thumps up if the ratings is min 70% calcutale how many gud and bad
    @DataProvider
    public static  Object [ ][ ] rating2() throws Exception {
        return new Object [ ] [ ] {
                {"Q1701110193", 5,"I am in !!",0,0,32,0},
                {"Q1701100215", 5,"I am in !!",0,0,32,0},
                {"Q1701050156", 5,"I am in !!",0,0,32,0},
                {"Q1701050142", 5,"I am in !!",0,0,32,0},
                {"Q1701050141", 5,"I am in !!",0,0,32,0},
                {"Q1701041439", 5,"I am in !!",0,0,32,0},
                {"Q1701041397", 3,"I am in !!",0,0,32,0},
                {"Q1701041394", 3,"I am in !!",0,0,32,0},
                {"Q1701040551", 3,"",0,0,32,0}, 
                {"Q1701040545", 3,"",0,0,32,0}, 
        };
    }
    
    
    @DataProvider
    public static  Object [ ][ ] rating3() throws Exception {
        return new Object [ ] [ ] {
                {"Q1701110193", 5,"I am in !!",0,0,34,0},
               {"Q1701100215", 4,"I am in !!",0,0,34,0},
                {"Q1701050156", 5,"I am in !!",0,0,34,0},
                {"Q1701050142", 5,"I am in !!",0,0,34,0},
                {"Q1701050141", 5,"I am in !!",0,0,34,0},
                {"Q1701041439", 5,"I am in !!",0,0,34,0},
                {"Q1701041397", 3,"I am in !!",0,0,34,32},
               {"Q1701041394", 3,"I am in !!",0,0,34,32},
               {"Q1701040551", 3,"I am in !!",0,0,34,32}, 
               {"Q1701040545", 3,"",0,0,34,32}, 
        };
    }
    
    
    @DataProvider
    public static  Object [ ][ ] goodAndBad() throws Exception {
        return new Object [ ] [ ] {
                {"Q1701110193", 4,"I am in !!",0,0,32,0},
                {"Q1701100215", 4,"I am in !!",0,0,32,0},
                {"Q1701050156", 4,"I am in !!",0,0,32,0},
                {"Q1701050142", 4,"I am in !!",0,0,32,0},
                {"Q1701050141", 4,"I am in !!",0,0,36,0},
                {"Q1701041439", 3,"I am in !!",0,0,34,0},
                {"Q1701041397", 3,"I am in !!",0,0,34,0},
                {"Q1701041394", 3,"I am in !!",0,0,34,0},
                {"Q1701040551", 3,"I am in !!",0,0,34,0}, 
                {"Q1701040545", 3,"Testing Testing",0,0,38,0}, 
        };
    }
    
    
    //
    @DataProvider
    public static  Object [ ][ ] ratings4() throws Exception {
        return new Object [ ] [ ] {
                {"Q1701110193", 5,"I am in !!",0,0,0,32},
                {"Q1701100215", 4,"I am in !!",0,0,0,32},
                {"Q1701050156", 5,"I am in !!",0,0,32,34},
                {"Q1701050142", 5,"I am in !!",0,0,32,34},
                {"Q1701050141", 5,"I am in !!",0,36,34,32},
                {"Q1701041439", 4,"I am in !!",38,36,34,32},
                {"Q1701041397", 4,"I am in !!",38,36,34,32},
                {"Q1701041394", 3,"I am in !!",38,36,34,32},
                {"Q1701040551", 3,"I am in !!",38,36,34,32}, 
                {"Q1701040545", 3,"Testing Testing",38,36,34,32}, 
        };
    }

    
    //http://es_user:es_user@10.10.12.111:9200/review-ratings/ratings/_search
    
    @BeforeClass
    private void seedData() throws SQLException, ClientProtocolException, URISyntaxException, IOException {
        jdbc.delete_USER_REVIEW_ATTRIBUTE_MAP(activityId);
        reviewId = jdbc.getAllReviewsId(activityId);
        int totalReview= reviewId.size();
        if(totalReview>0){
            jdbc.delete_USER_REVIEWS(activityId);
            //jdbc.delete_PRODUCT_REVIEWS(activityId);
        }
        //getCall("https://es_admin:es_admin@qa2.cleartrip.com/product-review/v1/index/reviews");
       // getCall("https://es_admin:es_admin@qa2.cleartrip.com/product-review/v1/index/ratings");
        System.out.println(reviewId);
    }

    @Test(dataProvider="ratings4")
    public void ratings(String tripid, int rating,  String comments, int att1, int att2, int att3, int att4) throws ClientProtocolException, IOException, URISyntaxException, JSONException, SQLException {
        HttpPost reviews = new HttpPost("https://qa2.cleartrip.com/product-review/v1/reviews");
        JSONObject finalJson = new JSONObject();
        finalJson.put("trip_ref", tripid);
        finalJson.put("rating", rating);
        finalJson.put("comment", comments);
        JSONArray attributes= new JSONArray();
        if(!(att1==0)){attributes.put(att1);}
        if(!(att2==0)){attributes.put(att2);}
        if(!(att3==0)){attributes.put(att3);}
        if(!(att4==0)){attributes.put(att4);}
        finalJson.put("attribute_ids", attributes);
        System.out.println(finalJson);
        StringEntity input1 = new StringEntity(finalJson.toString());
        reviews.setEntity(input1);
        
        //String cookie = "mob=0; _ga=GA1.2.650880570.1486800153; ct_ct=K4qldN68IyHLagPy0yt8JI2DuWWjpxt50WSmH9sug8XsNsavXke9l5XwocZNFly73QoqXNgmNE400srH81MoypvjJTw1rcYy0pJhc9q%2BRZ6r8PxJDF7On2Ett58CBe6JrT0rS%2BTpWmskzMbldck9kg%3D%3D; __utma=93256093.1561579430.1486800230.1488203178.1489143841.6; __utmz=93256093.1486800230.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=116119001.650880570.1486800153.1488538007.1488538007.1; __utmz=116119001.1488538007.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); NaN_hash=add5ac32BYFGXKXY1486979425194; _ga=GA1.3.650880570.1486800153; WZRK_G=7c074317d74e4a64b02e97d0eb2d6b23; Apache=dacf98af.54a59bfb0a474; r_referer=https%3A%2F%2Fqa2.cleartrip.com%2Fsignin%3Fpopup%3Dyes%26service%3D%2F; WZRK_P=https%3A%2F%2Fqa2.cleartrip.com%2F; WZRK_S_TEST-Z8R-KK8-W74Z=%7B%22p%22%3A1%2C%22s%22%3A1489143838%2C%22t%22%3A1489143839%7D; __utmb=93256093.4.9.1489143877615; __utmc=93256093; noncleartrip=false; affiliate_expdate=Fri Mar 10 2017;; usermisc=SIGNED_IN%7C; userid=automationcltp%40cleartrip.com%7C%7CM%7C%7C13941113; ct-auth-preferences=%7C%7C; currency-pref=; _session_id=BAh7BkkiD3Nlc3Npb25faWQGOgZFRiIlZTNkMzhjZWRlMmZiNDRiOTYzNTUxNmQ3NjczMmYyZGI%3D--5fde4a04c7e4ca903f37b962840556f4357b1924; booking=true";
        //reviews.setHeader("Cookie", cookie);
        
        CookieStore cookies = hqLogin();
        httpClient.setCookieStore(cookies);
        HttpResponse reviewsRes=  httpClient.execute(reviews);
        System.out.println(reviewsRes.getStatusLine().getStatusCode());
        
        EntityUtils.consumeQuietly(reviewsRes.getEntity());
        reviews.releaseConnection();
        // this is the code to insert using api
        
    }


    /*@Test(dependsOnMethods="ratings")
    private void approve() throws SQLException, ClientProtocolException, IOException, URISyntaxException, JSONException {
        System.out.println("ONE");
        HttpPost approve = new HttpPost("https://qa2.cleartrip.com/product-review/v1/reviews/approve");
        StringEntity input2 = new StringEntity(jdbc.getAllReviewsId(activityId).toString());
        approve.setEntity(input2);
        httpClient.setCookieStore(campSALogin());
        httpClient.execute(approve);
    }

    @Test(dependsOnMethods="approve")
    public void AssetRatings() throws ClientProtocolException, IOException, URISyntaxException, JSONException, SQLException {
        System.out.println("TWO");
        String avgRatingDB = jdbc.getAvgRating(activityId).get(0);
        System.out.println(avgRatingDB);
        String ratings_countDB = String.valueOf(reviewId.size());
        String reviews_counDB  = jdbc.getReviewCount(activityId).get(0);
        JSONObject detailsCall = new JSONObject(getDataFromElasticSearch());
        JSONObject details = detailsCall.getJSONObject("details");
        JSONObject ratings = details.getJSONObject("ratings");
        String avgRatingES = ratings.getString("avg_rating").toString();
        String ratings_countES = ratings.getString("ratings_count").toString();
        String reviews_countES = ratings.getString("reviews_count").toString();
        Assert.assertEquals(avgRatingDB, avgRatingES,"avg_rating mismatch");
        Assert.assertEquals(ratings_countDB, ratings_countES,"ratings_count mismatch");
        Assert.assertEquals(reviews_counDB, reviews_countES,"reviews_count mismatch");
    }*/





    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String  getDataFromElasticSearch() throws IOException, URISyntaxException, SQLException {
        DefaultHttpClient httpClient1=new DefaultHttpClient();
        List<String> reviewId = jdbc.getAllReviewsVariantId(activityId);   
        HttpGet ElasticSearchGet = new HttpGet();
        URI searchF5URL = new URI("https://es_admin:es_admin@qa2.cleartrip.com/local/api/v1/ttd/city/37103/variant/"+reviewId.get(0));
        ElasticSearchGet.setURI(searchF5URL);
        httpClient1.setRedirectStrategy(new LaxRedirectStrategy());
        HttpResponse ElasticSearchSearchResponse = httpClient1.execute(ElasticSearchGet);
        BufferedReader detail = new BufferedReader(new InputStreamReader((ElasticSearchSearchResponse.getEntity().getContent())));
        String response;
        StringBuilder DataResultbuilder = new StringBuilder();
        while ((response = detail.readLine()) != null) {
            DataResultbuilder.append(response);
        }
        String responseData = DataResultbuilder.toString();
        System.out.println(responseData);
        return responseData;
    }

    public  CookieStore hqLogin() throws URISyntaxException, IOException, JSONException {
        // DefaultHttpClient client = new DefaultHttpClient();
        URI url = new URI("https://"+host+"/externalapi/signin");
        HttpPost loginPostCall = new HttpPost(url); 
        String activityData="email=automationcltp%40cleartrip.com&password=dontask&persistent_login=t&service=%2F&caller=homepage&source=ui&action_type=&trip_ref=";
        StringEntity input1 = new StringEntity(activityData);
        loginPostCall.setEntity(input1);    
        loginPostCall.setHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        loginPostCall.setHeader("X-Requested-With", "XMLHttpRequest");
        loginPostCall.setHeader("Accept", "text/json");
        loginPostCall.setHeader("X-Prototype-Version", "1.6.0_rc0");
        loginPostCall.setHeader("user-agent", "User-Agent Mozilla/5.0 (Windows NT 6.1; rv:32.0) Gecko/20100101 Firefox/32.0");
        httpClient.setRedirectStrategy(new LaxRedirectStrategy());
        HttpResponse itineraryCreate = httpClient.execute(loginPostCall);   
        cookHQ = new BasicCookieStore();
      /*  
        for(Header h : itineraryCreate.getHeaders("Cookies")) 
            System.out.println(h.getValue());*/
        
        cookiesHQ = httpClient.getCookieStore().getCookies();
        int cookieSSize=cookiesHQ.size();
        
        for(int i=0;i<=cookieSSize-1;i++){
//            System.out.println("Cookie --- " + cookiesHQ.get(i).getName());
           /* if(cookiesHQ.get(i).getName().equalsIgnoreCase("ct-auth")) {
                Cookie cookie = new BasicClientCookie("ct-auth", cookiesHQ.get(i).getValue());
                cookHQ.addCookie(cookie);
            } else*/
                cookHQ.addCookie(cookiesHQ.get(i));
        }
        
        
        
        EntityUtils.consumeQuietly(itineraryCreate.getEntity());
        loginPostCall.releaseConnection();
        return cookHQ;

    }
    
    
    
    public  void getCall(String URL)
            throws URISyntaxException, IOException, ClientProtocolException {
    
        HttpGet Get = new HttpGet();
        URI searchF5URL = new URI(URL);
        Get.setURI(searchF5URL);
        httpClient.setRedirectStrategy(new LaxRedirectStrategy());
        HttpResponse searchResponse = httpClient.execute(Get);
        EntityUtils.consumeQuietly(searchResponse.getEntity());
        Get.releaseConnection();

        
    }
    
    public CookieStore campSALogin() throws ClientProtocolException, IOException, JSONException {

        HttpPost postRequest = new HttpPost("https://qa2.cleartrip.com/camp/accounts/sign_in");
        postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("X-CSRF-Token", "QPgQIJThGmaaMA45BRkoauhy0PxW3zYscoeDj1bzI/I=");
        postRequest.setHeader("Host", "qa2.cleartrip.com");
        StringEntity input = new StringEntity("{\"email\":\"sa1@cleartrip.com\",\"password\":\"cleartrip\"}");
        postRequest.setEntity(input);
        httpClient.setRedirectStrategy(new LaxRedirectStrategy());
        HttpResponse response = httpClient.execute(postRequest);

        CookieStore saCookieStore = new BasicCookieStore();
        List<Cookie> saCookies = httpClient.getCookieStore().getCookies();
        int CTcookieSSize=saCookies.size();
        for(int i=0;i<=CTcookieSSize-1;i++){
            ////System.out.println(saCookies.get(i));
            saCookieStore.addCookie(saCookies.get(i));
        }
        BasicClientCookie cookie;
        cookie = new BasicClientCookie("mob", "0");
        cookie.setDomain(".cleartrip.com");
        cookie.setPath("/");
        saCookieStore.addCookie(cookie);

        cookie = new BasicClientCookie("+path", "%2F");
        cookie.setDomain(".cleartrip.com");
        cookie.setPath("/");
        saCookieStore.addCookie(cookie);

        cookie = new BasicClientCookie("+expires", "Mon%2C+06-Feb-2017+10%3A55%3A55+GMT");
        cookie.setDomain(".cleartrip.com");
        cookie.setPath("/");
        saCookieStore.addCookie(cookie);

        cookie = new BasicClientCookie("+domain", "accounts.cltp.com");
        cookie.setDomain(".cleartrip.com");
        cookie.setPath("/");
        saCookieStore.addCookie(cookie);

        cookie = new BasicClientCookie("camp-ct-auth", "naZA3R%2FVAAQhh0qTY52GRKbW624mcTmJEXZLgCYmUZ9nVNi8uv4b1DVoOrzVc%2B1vu6QWzjRN9zH3dyQk2BpkpKFfieSNhPv4qwoWw49HKc43nWzkSsAOPIeWkLbFxkPpEKw6S9089oLiJyQE%2Bjqo3MuQAQu8O61oPaL2d4f5fTBt0O3Uy8H9Nm7EGfWNjtzg"
                + "");
        cookie.setDomain(".cleartrip.com");
        cookie.setPath("/");
        saCookieStore.addCookie(cookie);

        cookie = new BasicClientCookie("camp-usermisc", "SIGNED_IN%7C");
        cookie.setDomain(".cleartrip.com");
        cookie.setPath("/");
        saCookieStore.addCookie(cookie);

        cookie = new BasicClientCookie("camp-userid", "hilaludeen.m%40cleartrip.com%7C%7CM%7C%7C14035166");
        cookie.setDomain(".cleartrip.com");
        cookie.setPath("/");
        saCookieStore.addCookie(cookie);

        cookie = new BasicClientCookie("camp-ct-auth-preferences", "%7C%7C");
        cookie.setDomain(".cleartrip.com");
        cookie.setPath("/");
        saCookieStore.addCookie(cookie);   

        cookie = new BasicClientCookie("_session_id", "BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiJThiYThhZDJmNGI1NDkwOWI3N2IwY2UyOTEwOTVjMTdkBjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMWpyNmgrdlZsMTVkM0dJS0NJbkdwTFp5TmIzL1o5N1ZXTFpuN05YUlVlTlU9BjsARg%3D%3D--3062f80d45c9dfadbca3aa6f0e2ae6072fc64afd");
        cookie.setDomain("qa2.cleartrip.com");
        cookie.setPath("/");
        saCookieStore.addCookie(cookie);

        /*if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String output;
        StringBuffer totalOutput = new StringBuffer();
        //System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            ////System.out.println(output);
            totalOutput.append(output);
        }
        //System.out.println(totalOutput);*/
        EntityUtils.consumeQuietly(response.getEntity());
        postRequest.releaseConnection();
        return saCookieStore;
    }}
