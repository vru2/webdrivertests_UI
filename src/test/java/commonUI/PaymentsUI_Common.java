package test.java.commonUI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import test.java.commonAPI.API_PaymentCommon;

import java.util.Random;

public class PaymentsUI_Common extends API_PaymentCommon {

    public RemoteWebDriver driver;
    protected String Url;
    protected String paymentUrl;
    protected String qaUrl;
    public Response resp;
    String Hotel_URL = "";
    public String  hotelName_DetailsPage = "2052760";
    String inurl = "https://qa2new.cleartrip.com";
    public String hotelsUrl= "http://qa2new.cleartrip.com/hotels";
    public int CheckIN = 25;
    public int CheckOut = 26;
    public String HotelName = "Holiday inn bengaluru";
    String GV_number = "3000331034062731";
    String GV_pin = "169139";
    public String hotelPrice_Itinerary = null;
    public String hotelPrice_PaymentPage = null;
    public Cookie ctauthOLD = new Cookie("ct-auth", "kQqdrcVR8t4znRp8uzBQJgaacI%2B5mUEhQsXqP%2BGvCv9Sca3PAxik9%2FDoNKFAEq5S6nDr3dyz0gFHshmzL9GNaG4e8msn1sCvUt92FE1Hxz%2B449dUBXvxJapPKHtcbOExsOm%2BE43PNH%2FbzMr%2Bgv0v9PZIafGsbWEbtoycPG3UjA%2BzcqiD2kXHlH7Tnnt7Xdd%2B");
    public Cookie ctauth = new Cookie("ct-auth", "sJUONuyZDDPfTH%2BmG7oAtQeGQcHRjdLz9zF5EgBlchm5V%2F9sTn8LOdxqj35OBpme6nDr3dyz0gFHshmzL9GNaHOIvSzMqIKQCtJCbK1tSKgV%2BL7U6ooYH8i4J5EcOuBONoXhtAHa7NHmAmxdXipy2Q9D1Cb%2FImNyrUOegGbah%2FyoBMPzz%2FQiEiCw5q%2B2kAvsfPZ1MMq6EJr6sVI7VaVzbIGGV6A3Nv62ofdJLMeX59IdZGvtFXP18OHjIOoFpWFGkS1sn3WNAlm38%2FZOcdd3IjHEO18EgEydpVtNDzf6yr6faKrVCts6PecZbivI%2Bte60tjp1DhSV%2B2jxoL0zxvbMTYesQSARdekXP6oq0AMWLH%2BjvbXcUOrzBt3ykAdcnMIUTtjggX6YfQpO6VAcAmr8QLAwWMAoVsihoCMPIyMqJnxutVqwqAKEr63AAOZlv9K");
    public Cookie cookie_Bento_Payment = new Cookie("isBentoPayment", "true");
    public Cookie ctauth_amex = new Cookie("ct-auth", "2%2BtU1cPb8lJr0jvLEAtykB9OU0fk%2F%2BykRqo7fqGZ%2FgNdUi7dMNUxWo%2BLayLyBmIQH8YOfEGj8AeevvMX%2F4QnQkvAnTTp9N%2FfevCUsB0kyv34RHOgDXRfdndn%2FWd0KOXhimPy3b9gj7V56t4fbK1hHoIzQYBzwMa%2Fi72%2FqTSKtPUlKo9yE91%2BeAEj2Bi%2FZIx%2FcqFKCJETXpAxsR3%2FhUWMrg%3D%3D");
    protected String username = "testcltp29@gmail.com";
    //public Cookie ctauth_partial_wallet = new Cookie("ct-auth","FPFASUH3u8BIfPi6n5SA9LXbDIclCp0%2BkRN%2Fw9pKarLL3y3qdEGiZQIuqxIl9f3EH8YOfEGj8AeevvMX%2F4QnQu5pne5K5EHLAFvUZ60PN8K8qX%2FBnweQFNfqHv2MpXaBWdVRYJKk4obdFibGNlCsKQmjgQzzYO8qxSqmDKTEZk5nFNSZ6oZBVnN8BGz7Phhf");
    public Cookie ctauth_partial_wallet = new Cookie("ct-auth", "GZj199N%2FGbMVLbJmPWBgoaby%2BMCazMpV1i9dChHMZOf5g4XIcmYYYosgaQ0VGvp7vdb22kmKGrhj2VAI20Altj%2BnYqVAYRNgDmQE40E8Yzj4r0PCpRdMCg9e5Ry40QoNtmqkcQw0MDAlaO6MaiGWQSfRPc2%2BxtmdnrZa56VAd4A%3D"); // 65243938  5252525252 PH
    public Cookie ctauth_partial_wallet1 = new Cookie("ct-auth", "Bk7N%2FtlW6UIM9%2Fv06RR0lzYwI2Wr5NoY6shicJ7wSEglXjP2rTXj7vKCCjzDFS1EH8YOfEGj8AeevvMX%2F4QnQkvAnTTp9N%2FfevCUsB0kyv34RHOgDXRfdndn%2FWd0KOXh%2F2AX0kdZPIqgx5R%2FHygKQrR425YROepvP0SdSctCUtkcciwXF7FvVYKJizsM6Az33Pdp0Z8op1wWr79u2xWoxw%3D%3D");
    public Cookie hotelLogin = new Cookie("ct-auth", "wXRMsuJtL9WgArSZlNMx0zMrAAXuo%2Fx75FAjg9yx7%2BaP3TmjQOZ3nIiLDZWVzahbuQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2mdq5YlcDpr1NhocHCzFUnhnFzQr4qtRb2xhSWwELLVrIm931R0DjQqCU3guA6McTHvxrx7uoG8MaIjFbgrbUFuLCIQVMwmZPuPWYE%2BZcIe2iQcBlNEUA6TPhFqzPj5kdtXzYYtxjGgBPls1FJB9t5ULG6UU2B4lpfRPn7nlPGDL%2Bh8wYo5RkzDxKQKqfs%2BRQNZv8wYRhTyYEQWZZEKWQzfg");
    public Cookie ctauth_Saved_Cards = new Cookie("ct-auth", "xQF1scG2KAjUKjb0nhbj7W5gh1ze39fzSEc%2F18%2BoP6PkPulxqJhDFt6Li6igz%2BaLRgUWITcUCW%2FPw%2Bea%2FYC76r1klNYcgXrCEAwPKA%2BIUFocRr9A4ypxdh%2BPZCq2fC%2BI26hEYAocTQWJaHIALF%2BbQSemi1L2QY4GGJ7EXBuvSvGmVYWMhCcUDL%2Bi44N5mAea2u4J%2BEE0fGu5VNbg3TPA9Q%3D%3D");
    public Cookie ctauth_PayLater = new Cookie("ct-auth", "J%2BdMd0LGgMI8a39GNJ8xHqzqXvFGWmTpxhpOSPgnVnR5rXIoRNAXiPWEaKB9yveSNxBOo2r5JZ%2FVmD3Z2PjPQXjBrq444%2F1uPr9TDoR7r0Fe4mCETJt4BFkyvt%2FwdjA%2F8xWIih%2BGLbZz3y8MqrXJA5iZUrITk7nqu1Igqg3F1qbgENsO1xgbhrKOyO1na3ElmWDw%2Feg43BsE%2Bojvv%2FgwqHnmaZS3pTnoqp6N0Ka3Y8A%3D");
    public Cookie fullwallet = new Cookie("ct-auth", "abohNkVTBrywcKccg24Aw9dJPtR30Z3dElXVUz9mBnzshjhM3ya2l7Lh72af1Vw1j9O3UYZZi4zJRwF%2Bio21NJjJfVdGhDt6EBXP56tMKTFBGHOsWoEpCRXEAPtdwG%2FQIaFneIx1HBPLs0RKghSuuS%2BfddzyZlIzJ29c3Vp4Ews%3D"); //65237343 1234123456
    public Cookie bentoitn = new Cookie("forcedBentoItn", "true");
    public Cookie bento = new Cookie("isBento", "true");
    public String itn_totalprice;
    public String pay_totalprice;

    JavascriptExecutor jse = (JavascriptExecutor) driver;
    String contactnumber = "12345678";

    Cookie cookie_Parl_Wallet = new Cookie("ct-auth", "EVefRmmOWPSC8c9sPGbZGwZMgfl%2FLjP6yfQQAwhPONaOOIjRmfrMO5ubb5%2FGLWzguQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2me%2FiLCzDjUE8Mm7nMigogz0z84lf%2Bili9Xzawt1KbN%2FMNpQDroZvb3Q7ub%2BLj1YfofQs%2BDG9mD5DXvLFNSWqYz93GfvGpnfyFmIRy226HjYgQ%3D%3D");
    Cookie cookie_Full_Wallet = new Cookie("ct-auth", "kPSO4DekYXjX1NAa%2BV6x%2BPAuD85oXXc2x6ocNR2SCho5FiJNPfE0mcjCvYIvtvEzD3habrO078UoXdzWM34lXZaLbE1jIpkEaANLn%2BHJadeW7kll2UfWWUfOoZLsVWTE5e4M7AJPpksQjN%2BnOAVReuzUb6b257o%2Bo1tkm1ssHdnsn63Uy2JyxP3spA3W9e%2Fw");
    Cookie cookie_Stored_CardNew = new Cookie("ct-auth", "EVefRmmOWPSC8c9sPGbZGwZMgfl%2FLjP6yfQQAwhPONaOOIjRmfrMO5ubb5%2FGLWzguQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2me%2FiLCzDjUE8Mm7nMigogz0z84lf%2Bili9Xzawt1KbN%2FMNpQDroZvb3Q7ub%2BLj1YfofQs%2BDG9mD5DXvLFNSWqYz93GfvGpnfyFmIRy226HjYgQ%3D%3D");
    Cookie cookie_Stored_Card = new Cookie("ct-auth", "3cZX3Pk7YZLQGkv5lH%2BqMisg41mHr4%2BV5LnkFlBYXSW7TbjXLYl7j8XVySMQUxQsuv18jxT4Krq%2BnZKZgt%2FgtsPPZuvu7kgJgSXq9dBmctulsdFnuefY%2Fk4K%2FkHUuDj%2BnitdvoouxVugJ172IcDxp41NeKUSgTMU9EpGlYfZJ60e5yZIWxI28YU6CxlbH7FH");

    public String origin = "BLR";
    public String destination = "MAA";
    public String phoneNo="1212121212";
    public String emailID="kiran.kumar@cleartrip.com";
    public int date = getRandomNumberInt(100);
    public int dateBus = 5;

    public String origin(){
        final String[] OriginValue = {"BLR", "MAA", "BOM"};
        Random random = new Random();
        int index = random.nextInt(OriginValue.length);
        Reporter.log("Origin : "+OriginValue[index]);
        return OriginValue[index];
    }

    public String destination(){
        final String[] DestinationValue = {"DEL", "HYD", "GOI"};
        Random random = new Random();
        int index = random.nextInt(DestinationValue.length);
        Reporter.log("Destination : "+DestinationValue[index]);
        return DestinationValue[index];
    }

    public String air_SRPUrl(String Domain, String origin, String destination, int date) throws Exception {
        String Air_URL = "/flights/results?adults=1&childs=0&infants=0&class=Economy&depart_date=" + getDateTime(date, "dd/MM/yyyy") + "&from=" + origin() + "&to=" + destination() + "&intl=n";
        String SearchUrl = "";
        if (Domain == "IN") {
            SearchUrl = inurl + Air_URL;
        }
        Reporter.log(SearchUrl);
        System.out.println(SearchUrl);
        return SearchUrl;
    }

    public String Bus_SRPUrl(String Domain, String origin, String destination, int date) throws Exception {
        String Air_URL = "/bus/results?fromCity=4292&toCity=4562&journeyDate=" + getDateTime(date, "yyyy/mm/dd") + "&fromCityName=Bengaluru&toCityName=Chennai";
        String SearchUrl = "";
        if (Domain == "IN") {
            SearchUrl = inurl + Air_URL;
        }
        Reporter.log(SearchUrl);
        System.out.println(SearchUrl);
        return SearchUrl;
    }

    public String air_SRPUrl1(String Domain, String origin, String destination, int date) throws Exception {
        String Air_URL = "/flights/results?adults=1&childs=0&infants=0&class=Economy&depart_date=" + getDateTime(date, "dd/MM/yyyy") + "&from=BLR&to=CCU&intl=n";
        String SearchUrl = "";
        if (Domain == "IN") {
            SearchUrl = inurl + Air_URL;
        }
        Reporter.log(SearchUrl);
        System.out.println(SearchUrl);
        return SearchUrl;
    }


    public String bus_SRPUrl(String Domain, String origin, String destination, int date) throws Exception {
        String Bus_URL = "/bus/results?fromCity=4292&toCity=4562&journeyDate=" + getDateTime(date, "yyyy-MM-dd") + "&fromCityName=Bengaluru&toCityName=Chennai";
        String SearchUrl = "";
        if (Domain == "IN") {
            SearchUrl = inurl + Bus_URL;
        }
        Reporter.log(SearchUrl);
        System.out.println(SearchUrl);
        return SearchUrl;
    }

    public void addwalletamount(int amount, String emailID) throws Exception {
        Response resp;
        String url = "http://172.29.20.92:9001/payments/wallet/cashback?emailId=" + emailID + "&currency=INR&amount=" + amount + "&expiryDate%20=30/04/24";
        System.out.println("url : " + url);
        resp = RestAssured.get(url);
        Reporter.log(resp.asString());
    }

    public void addwalletamount_UserID(int amount, String userID) throws Exception {
        Response resp;
        String url = "http://172.29.20.92:9001/payments/wallet/addcash?amount=" + amount + "&userId=" + userID + "&currency=INR";
        System.out.println("url : " + url);
        resp = RestAssured.get(url);
        Reporter.log(resp.asString());
    }

    public String hotelDetailsUrl(String Domain, String HotelID)  throws Exception {
        Hotel_URL= "/hotels/details/"+HotelID+"?c="+getDateTime(70, "ddMMyy")+"|"+getDateTime(71, "ddMMyy")+"&r=2,0&ur=1";
        String DetailsUrl = "";
        if(Domain=="IN") {
            DetailsUrl=inurl+Hotel_URL;
        }
        Reporter.log(DetailsUrl);
        System.out.println(DetailsUrl);
        return DetailsUrl;
    }

    public String hotelDetailsPWAUrl(String Domain, String HotelID)  throws Exception {
        Hotel_URL= "/hotels/details/hotel-rr-grand-2626712?c=04032023%7C05032023&city=Bangalore&country=IN&destCode=32550&r=2%2C0&state=Karnataka";
        String DetailsUrl = "";
        if(Domain=="IN") {
            DetailsUrl=inurl+Hotel_URL;
        }
        Reporter.log(DetailsUrl);
        System.out.println(DetailsUrl);
        return DetailsUrl;
    }
}