package test.java.commonUI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import test.java.commonUI.PaymentUI_Common_Bento;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class PaymentsBento_Itn_Common extends PaymentUI_Common_Bento {
	public Cookie ctauthOLD = new Cookie("ct-auth", "kQqdrcVR8t4znRp8uzBQJgaacI%2B5mUEhQsXqP%2BGvCv9Sca3PAxik9%2FDoNKFAEq5S6nDr3dyz0gFHshmzL9GNaG4e8msn1sCvUt92FE1Hxz%2B449dUBXvxJapPKHtcbOExsOm%2BE43PNH%2FbzMr%2Bgv0v9PZIafGsbWEbtoycPG3UjA%2BzcqiD2kXHlH7Tnnt7Xdd%2B");
	public Cookie ctauth = new Cookie("ct-auth", "sJUONuyZDDPfTH%2BmG7oAtQeGQcHRjdLz9zF5EgBlchm5V%2F9sTn8LOdxqj35OBpme6nDr3dyz0gFHshmzL9GNaHOIvSzMqIKQCtJCbK1tSKgV%2BL7U6ooYH8i4J5EcOuBONoXhtAHa7NHmAmxdXipy2Q9D1Cb%2FImNyrUOegGbah%2FyoBMPzz%2FQiEiCw5q%2B2kAvsfPZ1MMq6EJr6sVI7VaVzbIGGV6A3Nv62ofdJLMeX59IdZGvtFXP18OHjIOoFpWFGkS1sn3WNAlm38%2FZOcdd3IjHEO18EgEydpVtNDzf6yr6faKrVCts6PecZbivI%2Bte60tjp1DhSV%2B2jxoL0zxvbMTYesQSARdekXP6oq0AMWLH%2BjvbXcUOrzBt3ykAdcnMIUTtjggX6YfQpO6VAcAmr8QLAwWMAoVsihoCMPIyMqJnxutVqwqAKEr63AAOZlv9K");
	public RemoteWebDriver driver;
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


	String GV_number = "3000331036544999";
	String GV_pin = "104573";

	JavascriptExecutor jse = (JavascriptExecutor) driver;
	String contactnumber = "12345678";


	String searchurl2 = "/flights/results?adults=1&childs=0&infants=0&depart_date=29/12/2022&return_date=&intl=n&from=BLR&to=MAA&airline=&carrier=&sd=1643253708293&page=&sellingCountry=IN&ssfi=&flexi_search=&ssfc=&origin=BLR - Bangalore, IN&destination=MAA - Chennai, IN&class=Economy";
	String searchurl1 = "/flights/results?adults=1&childs=0&infants=0&depart_date=29/12/2022&return_date=&intl=n&from=BLR&to=CCU&airline=&carrier=&sd=1643253708293&page=&sellingCountry=IN&ssfi=&flexi_search=&ssfc=&origin=BLR - Bangalore, IN&destination=DEL - New Delhi, IN&class=Economy";
	//String searchurl = "/flights/results?adults=1&childs=0&infants=0&class=Economy&depart_date=20/06/2022&from=BLR&to=MAA&intl=n&origin=BLR%20-%20Bangalore,%20IN&destination=MAA%20-%20Chennai,%20IN&sd=1643265410611&rnd_one=O&sourceCountry=Bangalore&destinationCountry=Chennai";
	//String searchurl1 ="/flights/results?adults=1&childs=0&infants=0&depart_date=29/12/2022&return_date=&intl=n&from=BLR&to=CCU&airline=&carrier=&sd=1642563217292&page=&sellingCountry=IN&ssfi=&flexi_search=&ssfc=&origin=BLR - Bangalore, IN&destination=CCU - Kolkata, IN&class=Economy";
	String qa2url = "https://qa2.cleartrip.com";
	String aeurl = "https://qa2.cleartrip.ae";
	String bhurl = "https://qa2bh.cleartrip.com";
	String qaurl = "https://qa2qa.cleartrip.com";
	String kwurl = "https://qa2kw.cleartrip.com";
	String saurl = "https://qa2.cleartrip.sa";
	String omurl = "https://qa2om.cleartrip.com";
	String meurl = "https://qa2me.cleartrip.com";


	protected String searchurl(String Domain) throws Exception {
		String Domain_URL = null;
		String Air_URL = "/flights/results?adults=1&childs=0&infants=0&depart_date=" + getDateTime(30, "dd/MM/yyyy") + "&return_date=&intl=n&from=BLR&to=MAA&airline=&carrier=&sd=1643253708293&page=&sellingCountry=IN&ssfi=&flexi_search=&ssfc=&origin=BLR - Bangalore, IN&destination=MAA - Chennai, IN&class=Economy";
		switch (Domain) {
			case "IN":
				Domain_URL = qa2url;
				break;
			case "AE":
				Domain_URL = aeurl;
				break;
			case "BH":
				Domain_URL = bhurl;
				break;
			case "QA":
				Domain_URL = qaurl;
				break;
			case "KW":
				Domain_URL = kwurl;
				break;
			case "SA":
				Domain_URL = saurl;
				break;
			case "OM":
				Domain_URL = omurl;
				break;
			case "ME":
				Domain_URL = meurl;
				break;
			default:
				Domain_URL = qa2url;
		}
		return Domain_URL + Air_URL;
	}


	protected String searchurl_PWA(String Domain) throws Exception {

		String URL = "https://qa2new.cleartrip.com/flights/results?adults=1&childs=0&class=Economy&depart_date=04%2F09%2F2022&from=BLR&from_header=Bangalore%2C+IN+-+Kempegowda+International+Airport&infants=0&to=BOM&to_header=Mumbai%2C+IN+-+Chatrapati+Shivaji+Airport";
		return URL;
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

	public void Searchpagebook(RemoteWebDriver driver, String wallettype, String domain, String cardtype) throws Exception {
		driver.manage().addCookie(bentoitn);
		if (wallettype == "Partial") {
			driver.manage().addCookie(ctauth_partial_wallet);

		} else {
			driver.manage().addCookie(ctauth);
		}
		driver.navigate().refresh();
		textPresent(driver, "Departure time", 10);
		int i = 0;
		for (i = 0; i <= 5; i++) {
			if (elementVisible(driver, By.xpath("//div[4]/button"), 4)) {
				safeClick(driver, By.xpath("//div[4]/button"));
				break;
			} else if (elementVisible(driver, By.xpath("//div[4]/div[2]/button"), 1)) {
				safeClick(driver, By.xpath("//div[4]/div[2]/button"));
				break;
			}

		}
		if (i == 6) {
			Reporter.log("Book Button not clicked in SRP");
			assertTrue(false);
		}

		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();
		String Child_URL = "";
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
			}
		}
		if (!elementVisible(driver, By.cssSelector("h2.fs-7.px-4.c-neutral-900.fw-600"), 20)) {
			textPresent_Log(driver, "Review your itinerary", 10);
		}
		Child_URL = driver.getCurrentUrl();
		driver.close(); // Closing Child window
		driver.switchTo().window(parent);
		Thread.sleep(5000);
		driver.get(Child_URL);
		if (!elementVisible(driver, By.cssSelector("h2.fs-7.px-4.c-neutral-900.fw-600"), 20)) {
			textPresent_Log(driver, "Review your itinerary", 10);
		}
		System.out.println("Itinerary :" + Child_URL);
		Reporter.log("Itinerary :" + Child_URL);
		if (!textPresent(driver, "Review your itinerary", 1)) {
			if (textPresent(driver, "Sorry, our servers are stumped with your request", 1) || textPresent(driver, "Flight not available", 1)) {
				System.out.println("Booking failed due to itn page issue");
				Reporter.log("Booking failed due to itn page issue");
				assertTrue(false);
			}
		/*

		String parent = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);
				driver.navigate().to(driver.getCurrentUrl());
				if(!elementVisible(driver,By.cssSelector("h2.fs-7.px-4.c-neutral-900.fw-600"), 20)) {
					textPresent_Log(driver, "Review your itinerary", 10);
				}
				System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
				Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
		if(!textPresent(driver, "Review your itinerary", 1)) {
	     if (textPresent(driver, "Sorry, our servers are stumped with your request", 1)|| textPresent(driver, "Flight not available", 1))
	     {
					System.out.println("Booking failed due to itn page issue");
					Reporter.log("Booking failed due to itn page issue");
					assertTrue(false);
	     }
		 }
	     } */
		}
	}


	// Booking via search page
	public void Searchpagebook1(RemoteWebDriver driver, String wallettype, String domain, String cardtype) throws Exception {
		driver.manage().addCookie(bentoitn);
		if (wallettype == "Partial") {
			driver.manage().addCookie(ctauth_partial_wallet);

		} else {
			driver.manage().addCookie(ctauth);
		}
		driver.navigate().refresh();
		elementPresent_log(driver, getObjectPayment("Bento_Book_Button"), "Book", 10);
		if (domain == "com") {
			elementVisible(driver, getObjectPayment("Bento_Indigo_Logo"), 1);
			if (elementVisible(driver, By.xpath("//img[@alt='Air India']"), 1)) {
				Actions actions = new Actions(driver);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				smartClick(driver, By.xpath("//div[5]/div[2]/div/label[2]/div/span"));
			}
			if (elementVisible(driver, getObjectPayment("Bento_Indigo_Logo"), 5)) {
				if (elementVisible(driver, getObjectPayment("Bento_Indigo_Logo"), 5)) {
					Thread.sleep(2000);
					safeClick(driver, By.xpath("//div[4]/div/button"));
					Thread.sleep(5000);
					if (elementVisible(driver, getObjectPayment("Bento_Book_Button1"), 5)) {
						Thread.sleep(2000);
						driver.findElement(getObjectPayment("Bento_Book_Button1")).sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(3000);
						safeClick(driver, getObjectPayment("Bento_Book_Button1"));
					} else {
						Thread.sleep(2000);
						driver.findElement(getObjectPayment("Bento_Book_Button2")).sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(3000);
						safeClick(driver, getObjectPayment("Bento_Book_Button2"));
					}
				} else {
					elementVisible(driver, getObjectPayment("Bento_Book_Button"), 5);
					Thread.sleep(2000);
					driver.findElement(getObjectPayment("Bento_Book_Button")).sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(3000);
					safeClick(driver, getObjectPayment("Bento_Book_Button"));
				}

			} else if (elementVisible(driver, getObjectPayment("Bento_Spicejet_Logo"), 3)) {
				if (elementVisible(driver, By.xpath("//div[4]/div/button"), 5)) {
					safeClick(driver, By.xpath("//div[4]/div/button"));
					Thread.sleep(1000);
					if (elementVisible(driver, getObjectPayment("Bento_Book_Button1"), 5)) {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button1")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button1"));
					} else {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button2")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button2"));
					}
				} else {
					smartClick(driver, getObjectPayment("Bento_Book_Button"));
				}
			} else if (elementVisible(driver, getObjectPayment("Bento_Vistara_Logo"), 3)) {
				if (elementVisible(driver, By.xpath("//div[4]/div/button"), 5)) {
					safeClick(driver, By.xpath("//div[4]/div/button"));
					Thread.sleep(1000);
					if (elementVisible(driver, getObjectPayment("Bento_Book_Button1"), 5)) {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button1")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button1"));
					} else {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button2")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button2"));
					}
				} else {
					smartClick(driver, getObjectPayment("Bento_Book_Button"));
				}
			} else if (elementVisible(driver, getObjectPayment("Bento_AirAsia_Logo"), 3)) {
				if (elementVisible(driver, By.xpath("//div[4]/div/button"), 5)) {
					safeClick(driver, By.xpath("//div[4]/div/button"));
					Thread.sleep(1000);
					if (elementVisible(driver, getObjectPayment("Bento_Book_Button1"), 5)) {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button1")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button1"));
					} else {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button2")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button2"));
					}
				} else {
					smartClick(driver, getObjectPayment("Bento_Book_Button"));
				}
			}
		} else {
			if (elementVisible(driver, getObjectPayment("Bento_Indigo_Logo"), 5)) {
				if (elementVisible(driver, By.xpath("//div[4]/div/button"), 5)) {
					safeClick(driver, By.xpath("//div[4]/div/button"));
					Thread.sleep(1000);
					if (elementVisible(driver, getObjectPayment("Bento_Book_Button1"), 5)) {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button1")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button1"));
					} else {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button2")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button2"));
					}
				} else {
					Thread.sleep(1000);
					smartClick(driver, By.xpath("//div[4]/button"));
				}
			} else if (elementVisible(driver, getObjectPayment("Bento_Spicejet_Logo"), 3)) {
				if (elementVisible(driver, By.xpath("//div[4]/div/button"), 5)) {
					safeClick(driver, By.xpath("//div[4]/div/button"));
					Thread.sleep(1000);
					if (elementVisible(driver, getObjectPayment("Bento_Book_Button1"), 5)) {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button1")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button1"));
					} else {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button2")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button2"));
					}
				} else {
					smartClick(driver, getObjectPayment("Bento_Book_Button"));
				}
			} else if (elementVisible(driver, getObjectPayment("Bento_Vistara_Logo"), 3)) {
				if (elementVisible(driver, By.xpath("//div[4]/div/button"), 5)) {
					safeClick(driver, By.xpath("//div[4]/div/button"));
					Thread.sleep(1000);
					if (elementVisible(driver, getObjectPayment("Bento_Book_Button1"), 5)) {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button1")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button1"));
					} else {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button2")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button2"));
					}
				} else {
					smartClick(driver, getObjectPayment("Bento_Book_Button"));
				}
			} else if (elementVisible(driver, getObjectPayment("Bento_AirAsia_Logo"), 3)) {
				if (elementVisible(driver, By.xpath("//div[4]/div/button"), 5)) {
					safeClick(driver, By.xpath("//div[4]/div/button"));
					Thread.sleep(1000);
					if (elementVisible(driver, getObjectPayment("Bento_Book_Button1"), 5)) {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button1")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button1"));
					} else {
						Thread.sleep(5000);
						driver.findElement(getObjectPayment("Bento_Book_Button2")).sendKeys(Keys.ARROW_DOWN);
						safeClick(driver, getObjectPayment("Bento_Book_Button2"));
					}
				} else {
					smartClick(driver, getObjectPayment("Bento_Book_Button"));
				}
			} else {
				System.out.println("LDAP was displayed");
				Reporter.log("LDAP was displayed");
			}
		}
		Reporter.log("Clicked on Book");
		String parent = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				Thread.sleep(5000);
				driver.navigate().to(driver.getCurrentUrl());
				if (!elementVisible(driver, By.cssSelector("h2.fs-7.px-4.c-neutral-900.fw-600"), 5)) {
					textPresent_Log(driver, "Review your itinerary", 30);
				}
				System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
				Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
				if (!textPresent(driver, "Review your itinerary", 1)) {
					if (textPresent(driver, "Sorry, our servers are stumped with your request", 5) || textPresent(driver, "Flight not available", 1)) {
						System.out.println("Booking failed due to itn page issue");
						Reporter.log("Booking failed due to itn page issue");
						assertTrue(false);
					}
				}
			}
		}
	}

	public void book_itnnew(RemoteWebDriver driver, String gv_coupon) throws Exception {
		itinerary_Block(driver, gv_coupon);
		addOns_Block(driver);
		contact_Block(driver);
		traveller_Block(driver);
	}

	public void itinerary_Block(RemoteWebDriver driver, String gv_coupon) throws Exception {
		//Actions act=new Actions(driver);
		if (!textPresent(driver, "Review your itinerary", 1)) {
			if (elementVisible(driver, By.xpath("//div[5]/button"), 5)) {
				safeClick(driver, By.xpath("//div[5]/button"));
				Thread.sleep(1000);
			}
		}
		Reporter.log("Itinerary page loaded");
		Thread.sleep(2000);
		if (elementVisible(driver, By.cssSelector(".nmx-1"), 5)) {
			safeClick(driver, By.cssSelector(".nmx-1"));
		}


		if (elementVisible(driver, getObjectPayment("Bento_Itn_Standard_Fee1"), 2)) {
			safeClick(driver, getObjectPayment("Bento_Itn_Standard_Fee1"));
			Reporter.log("Selected itn fee");
		}
		book_Apply_Coupon_GV(driver, gv_coupon);
		Thread.sleep(2000);
		if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare1_Continue"), 1)) {
			WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_Fare1_Continue"));
			ele.sendKeys(Keys.END);
			Thread.sleep(4000);
			elementVisible(driver, getObjectPayment("Bento_Itn_Fare1_Continue"), 1);
			safeClick(driver, getObjectPayment("Bento_Itn_Fare1_Continue"));
			Reporter.log("Clicked on continue");
		} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue2"), 1)) {
			WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue2"));
			ele.sendKeys(Keys.END);
			Thread.sleep(4000);
			elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue2"), 1);
			safeClick(driver, getObjectPayment("Bento_Itn_Fare_Continue2"));
			Reporter.log("Clicked on continue");
		} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue3"), 1)) {
			WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue3"));
			ele.sendKeys(Keys.END);
			Thread.sleep(4000);
			elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue3"), 1);
			safeClick(driver, getObjectPayment("Bento_Itn_Fare_Continue3"));
			Reporter.log("Clicked on continue");
		} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue"), 1)) {
			WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue"));
			ele.sendKeys(Keys.END);
			Thread.sleep(2000);
			elementPresent_log(driver, getObjectPayment("Bento_Itn_Fare_Continue"), "Itinerary contiue btn", 5);
			safeClick(driver, getObjectPayment("Bento_Itn_Fare_Continue"));
			Reporter.log("Clicked on continue");

		} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue4"), 1)) {
			WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue4"));
			ele.sendKeys(Keys.END);
			Thread.sleep(2000);
			elementPresent_log(driver, getObjectPayment("Bento_Itn_Fare_Continue4"), "Itinerary contiue btn", 5);
			safeClick(driver, getObjectPayment("Bento_Itn_Fare_Continue4"));
			Reporter.log("Clicked on continue");
		} else {
			Thread.sleep(5000);
			safeClick(driver, getObjectPayment("Bento_Itn_Fare_Continue"));
		}
	}


	public void itinerary_Block1(RemoteWebDriver driver, String gv_coupon) throws Exception {
		if (!textPresent(driver, "Review your itinerary", 1)) {
			if (elementVisible(driver, By.xpath("//div[5]/button"), 5)) {
				safeClick(driver, By.xpath("//div[5]/button"));
				Thread.sleep(1000);
			}
		}
		Reporter.log("Itinerary page loaded");

		if (elementVisible(driver, getObjectPayment("Bento_Itn_Standard_Fee1"), 2)) {
			safeClick(driver, getObjectPayment("Bento_Itn_Standard_Fee1"));
			Reporter.log("Selected itn fee");
			book_Apply_Coupon_GV(driver, gv_coupon);
			if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue"), 5)) {

				safeClick(driver, getObjectPayment("Bento_Itn_Fare_Continue"));
				Reporter.log("Clicked on continue");
			} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare1_Continue"), 1)) {
				safeClick_JS(driver, getObjectPayment("Bento_Itn_Fare1_Continue"));
				Reporter.log("Clicked on fare continue");
				Thread.sleep(3000);
			}
			if (elementVisible(driver, getObjectPayment("Bento_Itn_Meal_Continue"), 1)) {
			 /*  WebElement ele4=driver.findElement(getObjectPayment("Bento_Itn_Meal_Continue"));
			   Thread.sleep(2000);
			   ele4.sendKeys(Keys.ARROW_DOWN);
			   Thread.sleep(2000);
			   driver.executeScript("return arguments[0].scrollIntoView();", ele4);
			   Thread.sleep(2000);
			   ele4.click();
			   */
				safeClick_JS(driver, getObjectPayment("Bento_Itn_Meal_Continue"));
				Reporter.log("Clicked on meal continue");
				Thread.sleep(2000);
			}


		} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Standard_Fee"), 1)) {
			scrollToElement(driver, getObjectPayment("Bento_Itn_Standard_Fee"));
			Thread.sleep(1000);
			safeClick(driver, getObjectPayment("Bento_Itn_Standard_Fee"));
			Reporter.log("Selected itn fee");
			book_Apply_Coupon_GV(driver, gv_coupon);
			if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue"), 1)) {
				scrollToElement(driver, getObjectPayment("Bento_Itn_Fare_Continue"));
				Thread.sleep(1000);
				safeClick(driver, getObjectPayment("Bento_Itn_Fare_Continue"));
				Reporter.log("Clicked on fare continue");
			} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue"), 1)) {
				safeClick_JS(driver, getObjectPayment("Bento_Itn_Fare_Continue"));

				/*  WebElement ele2=driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue"));
				  Thread.sleep(2000);
				  ele2.sendKeys(Keys.ARROW_DOWN);
				  Thread.sleep(2000);
				  driver.executeScript("return arguments[0].scrollIntoView();", ele2);
				  Thread.sleep(2000);
				  ele2.click(); */
				Reporter.log("Clicked on fare continue");
				Thread.sleep(2000);
			} else {
				scrollToElement(driver, getObjectPayment("Bento_Itn_Fare_Continue"));
				Thread.sleep(1000);
				smartClick(driver, getObjectPayment("Bento_Itn_Fare_Continue"));
				Reporter.log("Clicked on fare continue");
			}

		} else {
			book_Apply_Coupon_GV(driver, gv_coupon);
			if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare3_Continue"), 1)) {
				safeClick_JS(driver, getObjectPayment("Bento_Itn_Fare3_Continue"));
				Reporter.log("Clicked on fare continue");
				Thread.sleep(2000);
			} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare2_Continue"), 1)) {
				safeClick_JS(driver, getObjectPayment("Bento_Itn_Fare2_Continue"));
				Reporter.log("Clicked on fare continue");
			} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue"), 1)) {
				safeClick_JS(driver, getObjectPayment("Bento_Itn_Fare_Continue"));

			/*  WebElement ele2=driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue"));
			  Thread.sleep(2000);
			  ele2.sendKeys(Keys.ARROW_DOWN);
			  Thread.sleep(2000);
			  driver.executeScript("return arguments[0].scrollIntoView();", ele2);
			  Thread.sleep(2000);
			  ele2.click(); */
				Reporter.log("Clicked on fare continue");
				Thread.sleep(2000);
			}


			if (elementVisible(driver, getObjectPayment("Bento_Itn_Meal_Continue"), 1)) {
				elementVisible(driver, getObjectPayment("Bento_Itn_Meal_Continue"), 2);
				WebElement ele4 = driver.findElement(getObjectPayment("Bento_Itn_Meal_Continue"));
				Thread.sleep(2000);
				ele4.sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(2000);
				driver.executeScript("return arguments[0].scrollIntoView();", ele4);
				Thread.sleep(2000);
				ele4.click();
				Reporter.log("Clicked on meal continue");
			}
		}
	}


	public void addOns_Block(RemoteWebDriver driver) throws Exception {
		textPresent(driver, "Choose add-ons", 1);
		if (elementVisible(driver, getObjectPayment("Bento_Itn_Add_On_Skip"), 5)) {
			WebElement ele4 = driver.findElement(getObjectPayment("Bento_Itn_Add_On_Skip"));
			Thread.sleep(2000);
			ele4.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
			driver.executeScript("return arguments[0].scrollIntoView();", ele4);
			Thread.sleep(2000);
			ele4.click();
			Reporter.log("Clicked on skip addons");
		}
	}

	public void contact_Block(RemoteWebDriver driver) throws Exception {
		textPresent(driver, "Add contact details", 1);
		for (int i = 0; i < 5; i++) {
			if (elementVisible(driver, getObjectPayment("Bento_Itn_Contact_Number1"), 1)) {
				safeClick(driver, getObjectPayment("Bento_Itn_Contact_Number1"));
				safeType(driver, getObjectPayment("Bento_Itn_Contact_Number1"), "1234567890");
				Reporter.log("Entered mobile number");
				break;
			} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Contact"), 1)) {
				safeClick(driver, getObjectPayment("Bento_Itn_Contact"));
				safeType(driver, getObjectPayment("Bento_Itn_Contact"), "1234567890");
				Reporter.log("Entered mobile number");
				break;
			} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Contact_Number"), 1)) {
				elementVisible(driver, getObjectPayment("Bento_Itn_Contact_Number"), 1);
				safeClick(driver, getObjectPayment("Bento_Itn_Contact_Number"));
				safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "1234567890");
				Reporter.log("Entered mobile number");
				break;
			} else if (elementVisible(driver, getObjectPayment("Bento_Itn_Contact_Number2"), 1)) {
				elementVisible(driver, getObjectPayment("Bento_Itn_Contact_Number2"), 1);
				safeClick(driver, getObjectPayment("Bento_Itn_Contact_Number2"));
				safeType(driver, getObjectPayment("Bento_Itn_Contact_Number2"), "1234567890");
				Reporter.log("Entered mobile number");
				break;
			}
		}
		safeClick(driver, getObjectPayment("Bento_Itn_Username"));
		safeType(driver, getObjectPayment("Bento_Itn_Username"), username);

		Reporter.log("Entered user name");
		if (elementVisible(driver, getObjectPayment("Bento_Itn_Contactinfo_Continue"), 1)) {
			smartClick(driver, getObjectPayment("Bento_Itn_Contactinfo_Continue"));
		} else smartClick(driver, By.xpath("//div[6]/button"));
		Reporter.log("Clicked on continue in Add Contact details");
	}

	public void traveller_Block(RemoteWebDriver driver) throws Exception {
		textPresent(driver, "Add traveller details", 1);
		elementVisible(driver, getObjectPayment("Bento_Itn_User_Firstname"), 10);
		safeClick(driver, getObjectPayment("Bento_Itn_User_Firstname"));
		safeType(driver, getObjectPayment("Bento_Itn_User_Firstname"), "Tester");
		Reporter.log("Entered first name");
		if (elementVisible(driver, getObjectPayment("Bento_Itn_User_Lastname"), 1)) {
			safeClick(driver, getObjectPayment("Bento_Itn_User_Lastname"));
			safeType(driver, getObjectPayment("Bento_Itn_User_Lastname"), "Test");
			Reporter.log("Entered last name");
		} else {
			safeClick(driver, getObjectPayment("Bento_Itn_User_lastname"));
			safeType(driver, getObjectPayment("Bento_Itn_User_lastname"), "Test");
			Reporter.log("Entered last name");
		}

		if (elementVisible(driver, getObjectPayment("Bento_Itn_select_gender"), 1)) {
			safeClick(driver, getObjectPayment("Bento_Itn_select_gender"));
		} else {
			safeClick(driver, getObjectPayment("Bento_Itn_Select_Gender"));
		}
		safeClick(driver, getObjectPayment("Bento_Itn_Select_Female"));
		Reporter.log("Selected gender");
		if (textPresent(driver, "Nationality", 2) || elementVisible(driver, getObjectPayment("Bento_Itn_Nationality"), 1)) {
			safeClick(driver, getObjectPayment("Bento_Itn_Nationality"));
			Thread.sleep(2000);/*
					safeType(driver, getObjectPayment("Bento_Itn_Nationality"), "india");
					safeClick(driver, getObjectPayment("Bento_Itin_Select_India"));*/
			mouseHover(driver, getObjectPayment("Bento_Itin_Select_India"));
			safeClick(driver, getObjectPayment("Bento_Itin_Select_India"));
			Reporter.log("Selected nationality");
			if (elementVisible(driver, getObjectPayment("Bento_Itn_Nationality_DOB_Date"), 1)) {
				smartSelect(driver, getObjectPayment("Bento_Itn_Nationality_DOB_Date"), "01");
				smartSelect(driver, getObjectPayment("Bento_Itn_Nationality_DOB_Month"), "Jan");
				smartSelect(driver, getObjectPayment("Bento_Itn_Nationality_DOB_Year"), "2000");
				if (elementVisible(driver, getObjectPayment("Bento_Itn_Select_Day"), 2)) {
					safeClick(driver, getObjectPayment("Bento_Itn_Select_Day"));
					safeSelectByValue(driver, getObjectPayment("Bento_Itn_Select_Day"), "08");
					Thread.sleep(1000);
					safeClick(driver, getObjectPayment("Bento_Itn_Select_Month"));
					safeSelectByIndex(driver, getObjectPayment("Bento_Itn_Select_Month"), 10);
					safeClick(driver, getObjectPayment("Bento_Itn_Select_Year"));
					safeSelectByValue(driver, getObjectPayment("Bento_Itn_Select_Year"), "1994");
					safeClick(driver, getObjectPayment("Bento_Itn_Select_Year"));
					Thread.sleep(5000);
				}
			}
		}
		itn_fares(driver);
		safeClick(driver, getObjectPayment("Bento_Itn_Continue_Booking"));
		Thread.sleep(2000);
		Reporter.log("Clicked on travel details continue button");
	}

	public void book_Apply_Coupon_GV(RemoteWebDriver driver, String gv_coupon) throws Exception {
		if (elementVisible(driver, getObjectPayment("Bento_Itn_GV_Number_New"), 5)) {
			if (gv_coupon == "GV") {

				elementVisible(driver, getObjectPayment("Bento_Itn_GV_Number_New"), 5);
				WebElement ele3 = driver.findElement(getObjectPayment("Bento_Itn_GV_Number_New"));
				ele3.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(2000);
				ele3.sendKeys(GV_number);
				Reporter.log("Entered GV number");
				Thread.sleep(1000);
				elementVisible(driver, getObjectPayment("Bento_Itn_GV_Pin_New"), 2);
				safeType(driver, getObjectPayment("Bento_Itn_GV_Pin_New"), GV_pin);
				Reporter.log("Entered GV pin");
				safeClick(driver, getObjectPayment("Bento_Itn_GV_Apply_New"));
				//textPresent_Log(driver,"has been redeemed for this booking",3);
				Reporter.log("GV applied Successfully");
			} else if (gv_coupon == "GV_Partial") {
				String[] GV = getGV(10);
				elementVisible(driver, getObjectPayment("Bento_Itn_GV_Number_New"), 2);
				WebElement ele3 = driver.findElement(getObjectPayment("Bento_Itn_GV_Number_New"));
				ele3.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(2000);
				ele3.sendKeys(GV[0]);
				Reporter.log("Entered GV number");
				Thread.sleep(1000);
				elementVisible(driver, getObjectPayment("Bento_Itn_GV_Pin_New"), 2);
				safeType(driver, getObjectPayment("Bento_Itn_GV_Pin_new"), GV[1]);
				Reporter.log("Entered GV pin");
				safeClick(driver, getObjectPayment("Bento_Itn_GV_Apply_New"));
				//  textPresent_Log(driver,"has been redeemed for this booking",3);
				Reporter.log("GV applied Successfully");
				Thread.sleep(20000);
			} else if (gv_coupon == "Coupon") {
				WebElement ele4 = driver.findElement(getObjectPayment("Bento_Itn_GV_Number_New"));
				ele4.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(1000);
				ele4.sendKeys("DOMCC");
				Thread.sleep(1000);
				Reporter.log("Entered Coupon details");
				smartClick(driver, getObjectPayment("Bento_Itn_Coupon_Apply_New"));
				Thread.sleep(2000);
				if (!textPresent_Log(driver, "You just saved", 3)) {
					Reporter.log("Coupon not working");
					Assert.assertTrue(false);
				}
			} else if (gv_coupon == "DOMCC") {
				WebElement ele4 = driver.findElement(getObjectPayment("Bento_Itn_GV_Number_New"));
				ele4.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(1000);
				ele4.sendKeys("DOMCC");
				Thread.sleep(1000);
				Reporter.log("Entered Coupon details");
				smartClick(driver, getObjectPayment("Bento_Itn_Coupon_Apply_New"));
				Thread.sleep(2000);
				if (!textPresent_Log(driver, "Great! You just saved", 3)) {
					Reporter.log("Coupon not working");
					Assert.assertTrue(false);
				}
			} else if (elementVisible(driver, getObjectPayment("Bento_Itn_GV_Number"), 5)) {
				System.out.println("Coupon Call 123");
				if (gv_coupon == "GV") {
					elementVisible(driver, getObjectPayment("Bento_Itn_GV_Number"), 5);
					WebElement ele3 = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
					ele3.sendKeys(Keys.PAGE_DOWN);
					Thread.sleep(2000);
					ele3.sendKeys(GV_number);
					Reporter.log("Entered GV number");
					Thread.sleep(1000);
					elementVisible(driver, getObjectPayment("Bento_Itn_GV_Pin"), 2);
					safeType(driver, getObjectPayment("Bento_Itn_GV_Pin"), GV_pin);
					Reporter.log("Entered GV pin");
					safeClick(driver, getObjectPayment("Bento_Itn_GV_Apply"));
					//textPresent_Log(driver,"has been redeemed for this booking",3);
					Reporter.log("GV applied Successfully");
				} else if (gv_coupon == "GV_Partial") {
					String[] GV = getGV(10);
					elementVisible(driver, getObjectPayment("Bento_Itn_GV_Number"), 2);
					WebElement ele3 = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
					ele3.sendKeys(Keys.PAGE_DOWN);
					Thread.sleep(2000);
					ele3.sendKeys(GV[0]);
					Reporter.log("Entered GV number");
					Thread.sleep(1000);
					elementVisible(driver, getObjectPayment("Bento_Itn_GV_Pin"), 2);
					safeType(driver, getObjectPayment("Bento_Itn_GV_Pin"), GV[1]);
					Reporter.log("Entered GV pin");
					safeClick(driver, getObjectPayment("Bento_Itn_GV_Apply"));
					//  textPresent_Log(driver,"has been redeemed for this booking",3);
					Reporter.log("GV applied Successfully");
					Thread.sleep(20000);
				} else if (gv_coupon == "Coupon") {
					WebElement ele4 = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
					ele4.sendKeys(Keys.PAGE_DOWN);
					Thread.sleep(1000);
					ele4.sendKeys("DOMCC");
					Thread.sleep(1000);
					Reporter.log("Entered Coupon details");
					smartClick(driver, getObjectPayment("Bento_Itn_Coupon_Apply"));
					Thread.sleep(2000);
					if (!textPresent_Log(driver, "Great! You just saved", 3)) {
						Reporter.log("Coupon not working");
						Assert.assertTrue(false);
					}
				} else if (gv_coupon == "DOMCC") {
					WebElement ele4 = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
					ele4.sendKeys(Keys.PAGE_DOWN);
					Thread.sleep(1000);
					ele4.sendKeys("DOMCC");
					Thread.sleep(1000);
					Reporter.log("Entered Coupon details");
					smartClick(driver, getObjectPayment("Bento_Itn_Coupon_Apply"));
					Thread.sleep(2000);
					if (!textPresent_Log(driver, "Great! You just saved", 3)) {
						Reporter.log("Coupon not working");
						Assert.assertTrue(false);
					}
				}
			}

		}
	}

	public void noncom_itnpage(RemoteWebDriver driver, String gv_coupon, String domain) throws Exception {
		if (elementVisible(driver, By.cssSelector("h2.fs-7.px-4.c-neutral-900.fw-600"), 5)) {
		} else {
			if (elementVisible(driver, By.xpath("//div[5]/button"), 1)) {
				smartClick(driver, By.xpath("//div[5]/button"));
			}
		}
		Actions actions = new Actions(driver);
		if (domain == "ae" || domain == "sa") {
		/*	if (elementVisible(driver, getObjectPayment("Bento_aeitn_continue1"), 5)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_continue1"));
			} else*/
			smartClick(driver, getObjectPayment("Bento_aeitn_continue1"));

			if (elementVisible(driver, getObjectPayment("Bento_aeitn_removeinsurance"), 1)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_removeinsurance"));
				Thread.sleep(2000);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				Thread.sleep(1000);
				safeClick(driver, getObjectPayment("Bento_aeitn_continue"));
				Thread.sleep(3000);
			}
		} else {

			if (elementVisible(driver, getObjectPayment("Bento_aeitn_continue1"), 2)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_continue1"));
			} else {
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				Thread.sleep(1000);
				safeClick(driver, getObjectPayment("Bento_aeitn_continue"));
			}
		}

		if (elementVisible(driver, getObjectPayment("Bento_aeitn_skip"), 5)) {
			Thread.sleep(1000);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			Thread.sleep(5000);
			smartClick(driver, getObjectPayment("Bento_aeitn_skip"));
		}
		elementVisible(driver, getObjectPayment("Bento_Itn_Contact_Number"), 10);
		safeClick(driver, getObjectPayment("Bento_Itn_Contact_Number"));
		safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "1");
		if (domain == "ae" || domain == "sa") {
			if (domain == "ae") {
				safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "12345678");
			} else if (domain == "sa") {
				safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "123456789");
			}
			Reporter.log("Entered mobile number");
			safeClick(driver, getObjectPayment("Bento_Itn_Username"));
			safeType(driver, getObjectPayment("Bento_Itn_Username"), username);
			Reporter.log("Entered user name");
			safeClick(driver, getObjectPayment("Bento_Itn_Contactinfo_Continue"));
			Reporter.log("Clicked on continue");
			safeClick(driver, getObjectPayment("Bento_aeitn_firstname"));
			safeType(driver, getObjectPayment("Bento_aeitn_firstname"), "Tester");
			if (elementVisible(driver, getObjectPayment("Bento_aeitn_Lastname"), 2)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_Lastname"));
				safeType(driver, getObjectPayment("Bento_aeitn_Lastname"), "Test");
				Reporter.log("Entered last name");
			} else {
				safeClick(driver, getObjectPayment("Bento_aeitn_lastname"));
				safeType(driver, getObjectPayment("Bento_aeitn_lastname"), "Test");
				Reporter.log("Entered last name");
			}

			if (elementVisible(driver, getObjectPayment("Bento_aeitn_select_Gender"), 1)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_select_Gender"));
				safeClick(driver, getObjectPayment("Bento_ae_itn_select_Female"));
				Reporter.log("Selected gender");
			} else {
				safeClick(driver, getObjectPayment("Bento_aeitn_select_gender"));
				safeClick(driver, getObjectPayment("Bento_ae_itn_select_female"));
				Reporter.log("Selected gender");
			}
			if (elementVisible(driver, getObjectPayment("Bento_ae_nationality"), 2)) {
				safeClick(driver, getObjectPayment("Bento_ae_nationality"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_ae_type_nationality"), "India");
				safeClick(driver, getObjectPayment("Bento_ae_select_india"));
			}
			if (elementVisible(driver, getObjectPayment("Bento_ae_Continuetopayments1"), 2)) {
				safeClick(driver, getObjectPayment("Bento_ae_Continuetopayments1"));
			} else if (elementVisible(driver, getObjectPayment("Bento_ae_Continuetopayments"), 2)) {
				safeClick(driver, getObjectPayment("Bento_ae_Continuetopayments"));
			}
		} else {

			safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "1234567890");
			Reporter.log("Entered mobile number");
			safeClick(driver, getObjectPayment("Bento_Itn_Username"));
			safeType(driver, getObjectPayment("Bento_Itn_Username"), username);
			Reporter.log("Entered user name");
			safeClick(driver, getObjectPayment("Bento_Itn_Contactinfo_Continue"));
			Reporter.log("Clicked on continue");
			safeClick(driver, getObjectPayment("Bento_aeitn_Firstname"));
			safeType(driver, getObjectPayment("Bento_aeitn_Firstname"), "Tester");
			if (elementVisible(driver, getObjectPayment("Bento_aeitn_lastname"), 2)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_lastname"));
				safeType(driver, getObjectPayment("Bento_aeitn_lastname"), "Test");
				Reporter.log("Entered last name");
			} else {
				safeClick(driver, getObjectPayment("Bento_aeitn_Lastname"));
				safeType(driver, getObjectPayment("Bento_aeitn_Lastname"), "Test");
				Reporter.log("Entered last name");
			}
			if (elementVisible(driver, By.xpath("//div[3]/div/input"), 1)) {
				safeClick(driver, By.xpath("//div[3]/div/input"));
				safeType(driver, By.xpath("//div[3]/div/input"), "Test");
				Reporter.log("Entered last name");
			}
			if (elementVisible(driver, getObjectPayment("Bento_aeitn_select_Gender"), 2)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_select_Gender"));
				safeClick(driver, getObjectPayment("Bento_ae_itn_select_Female"));
				Reporter.log("Selected gender");
			} else {
				safeClick(driver, getObjectPayment("Bento_aeitn_select_gender"));
				safeClick(driver, getObjectPayment("Bento_ae_itn_select_female"));
				Reporter.log("Selected gender");
			}


			if (elementVisible(driver, getObjectPayment("Bento_ae_nationality"), 1)) {
				safeClick(driver, getObjectPayment("Bento_ae_nationality"));
				safeType(driver, getObjectPayment("Bento_ae_type_nationality"), "India");
				safeClick(driver, getObjectPayment("Bento_ae_select_india"));
			}
			if (elementVisible(driver, getObjectPayment("Bento_ae_Continuetopayments1"), 1)) {
				safeClick(driver, getObjectPayment("Bento_ae_Continuetopayments1"));
			} else if (elementVisible(driver, getObjectPayment("Bento_ae_continuetopayment"), 1)) {
				safeClick(driver, getObjectPayment("Bento_ae_continuetopayment"));
			}
		}
		Reporter.log("Clicked on continue button to navigate to payments page");
	}

	public void paymentPage(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName) throws Exception {
		if (elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 30)) {
			bento_paymentpage(driver, PaymentType, CardNumber, domain, PayType, BankName);
			if (!(CardNumber == "ADCB" || PaymentType == "Phonepe" || PaymentType == "UPIScan" || PayType == "Googlecaptcha")) {
				confirmation_page_air(driver, PaymentType, CardNumber);
			}
		} else if (textPresent(driver, "Sorry, our servers are stumped with your request", 1) || textPresent(driver, "Flight not available", 1)) {
			Reporter.log("Booking failed due to itn page issue");
			assertTrue(false);
		} else {
			Reporter.log("Payment page hasn't loaded");
			assertTrue(false);
		}
	}

	public void paymentPageHotels(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName, String TestDetails) throws Exception {
		elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 30);
		refreshPage(driver);
		if (elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 30)) {
			bento_paymentpage(driver, PaymentType, CardNumber, domain, PayType, BankName);
			if (!(CardNumber == "ADCB" || PaymentType == "Phonepe" || PaymentType == "UPIScan" || PayType == "Googlecaptcha"|| PayType == "Failure")) {
				confirmation_page_hotel(driver, PaymentType, CardNumber, TestDetails);
			}
		} else if (textPresent(driver, "Sorry, our servers are stumped with your request", 1) || textPresent(driver, "Flight not available", 1)) {
			Reporter.log("Booking failed due to itn page issue");
			assertTrue(false);
		} else {
			Reporter.log("Booking failed due to itn page issue");
			assertTrue(false);
		}
	}

	public void confirmation_page(RemoteWebDriver driver, String PaymentType, String CardNumber, String TestDetails) throws Exception {
		Thread.sleep(5000);
		if (getURL(driver).contains("flights")) {
			confirmation_page_air(driver, PaymentType, CardNumber);
		} else if (getURL(driver).contains("hotels")) {
			confirmation_page_hotel(driver, PaymentType, CardNumber, TestDetails);
		}
	}

	public void confirmation_page_air(RemoteWebDriver driver, String PaymentType, String CardNumber) throws Exception {
		elementPresent_log(driver, By.linkText("Get your ticket"), "Get your ticket", 30);
		textPresent_Log(driver, "Your booking is done", 2);
		textPresent_Log(driver, "Travelers in this trip", 2);
		textPresent_Log(driver, "Itinerary sent", 2);
/*		textPresent_Log(driver, "PAYMENT RECEIPT", 2);
		textPresent_Log(driver, "TOTAL CHARGE", 2);
		textPresent_Log(driver, "RATE BREAK UP", 2);*/
		/* textPresent_Log(driver,"Convenience Fee",2); */
		textPresent_Log(driver, "Total", 2);
		//	textPresent_Log(driver, "Travel plans change often.", 2);
		String tripid = driver.findElement(getObjectPayment("Bento_Confirmation_Page_Gettrip")).getText();
		System.out.println(PaymentType + " " + CardNumber + " : " + tripid);
		Reporter.log(PaymentType + " " + CardNumber + " : " + tripid);
		Reporter.log("Confirmation Page " + logURL(driver));
	}

	public void confirmation_page_hotel(RemoteWebDriver driver, String PaymentType, String CardNumber, String TestDetails) throws Exception {
		elementPresent_log(driver, By.xpath("//div[3]/p"), "TripID", 30);
		textPresent_Log(driver, "Booking successful", 30);
		String tripid = getText(driver, By.xpath("//div[3]/p"));
		logURL(driver);
		Reporter.log(TestDetails + tripid);
		System.out.println(TestDetails + tripid);
	}

	public void bento_paymentpage(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName) throws Exception {
		textPresent_Log(driver, "Pay to complete your booking", 20);
		Reporter.log(driver.getCurrentUrl());
		System.out.println(driver.getCurrentUrl());
		if (PaymentType.equalsIgnoreCase("WALLET")) {
			safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
			Reporter.log("Selected wallet");
			Thread.sleep(2000);
		}
		if (PayType.equalsIgnoreCase("WALLET")) {
			textPresent_Log(driver, "Cleartrip wallet", 2);
			safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
			Reporter.log("Selected wallet");
			Thread.sleep(2000);
		}
		pay_fares(driver);
		switch (PaymentType) {
			case "storedcard":
				bento_pay_StoredCard(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "GVPriceChange":
				bento_pay_GVPriceChange(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "storedcardUPI":
				bento_pay_StoredCard_UPI(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "CC":
				bento_pay_CC(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "EMI":
				bento_pay_EMI(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "BFL":
				bento_pay_BFL(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "NB":
				bento_pay_NB(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "TW":
				bento_pay_TW(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "wallet":
				bento_pay_Wallet(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "Phonepe":
				bento_pay_PhonePe(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "UPIScan":
				bento_pay_UPIScan(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "UPI":
				bento_pay_UPI(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "GV":
				bento_pay_GV(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "GV_Partial":
				bento_pay_GV_Partial(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "KNET":
				bento_pay_KNET(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "partial_wallet":
				bento_pay_PartialWallet(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "Coupon":
				bento_pay_Coupon(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "AE-SC":
				bento_pay_AE_SC(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "sc":
				bento_pay_SC(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "OTH":
				bento_pay_Others(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
		}
	}

	public void bento_pay_GVPriceChange(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName) throws Exception {
		GVPriceChange_Validation(driver, PayType, BankName);
		String parentWindowHandler = switchToPopup(driver);
		mouseHover(driver,By.xpath("//button[2]") );
		safeClick(driver, By.xpath("//button[2]"));  //change payment mode
		driver.switchTo().window(parentWindowHandler);

		GVPriceChange_Validation(driver, PayType, BankName);
		parentWindowHandler = switchToPopup(driver);
		mouseHover(driver, By.cssSelector("svg.c-neutral-900.c-pointer"));
		safeClick(driver, By.cssSelector("svg.c-neutral-900.c-pointer"));  //close button
		driver.switchTo().window(parentWindowHandler);

		GVPriceChange_Validation(driver, PayType, BankName);
		parentWindowHandler = switchToPopup(driver);
		mouseHover(driver, By.xpath("//div[4]/div/button"));
		safeClick(driver, By.xpath("//div[4]/div/button")); //Book anyway
		driver.switchTo().window(parentWindowHandler);

		Thread.sleep(5000);
		bento_pay_GWPage(driver, "RAZORPAYNB","","Success");
	}

	private void GVReduceAmount(String PayType, String BankName) throws ClassNotFoundException, SQLException {
		rearchGV_UI("VALIDATESCLPGV_UI", PayType, BankName);
		rearchGV_UI("CAPTURESCLPGV_UI", PayType, BankName);
	}

	private String switchToPopup(RemoteWebDriver driver) {
		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
			subWindowHandler = iterator.next();
		}
		// Now you are in the popup window, perform necessary actions here
		driver.switchTo().window(subWindowHandler); // switch to popup window
		return parentWindowHandler;
	}

	private void GVPriceChange_Validation(RemoteWebDriver driver, String GVNumber, String GVPin) throws Exception {
		bento_Select_PaymentType(driver, "NB");
		GVReduceAmount(GVNumber, GVPin);  //GV number / Pin
		safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		elementPresent_log(driver, By.xpath("//div[4]/div/button"),"GV price change button", 5); // book anyway btn
		elementPresent_log(driver, By.cssSelector("svg.c-neutral-900.c-pointer"),"Close button", 5); // close button
		elementPresent_log(driver, By.xpath("//button[2]"),"Close button", 5); // change payment button
		textPresent_Log(driver, "We noticed your Gift card balance has changed from ", 1);
		textPresent_Log(driver, "Gift card balance changed", 1);
		textPresent_Log(driver, "after using", 1);
		textPresent_Log(driver, "Gift card balance.", 1);
	}

	public void bento_pay_StoredCard(RemoteWebDriver driver, String PaymentType, String CardNumber, String domain, String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType(driver, "SC");
		textPresent_Log(driver, "Select a saved payment mode", 5);
		if (CardNumber == "7777") {
			if (domain.equals("HOTELS")) {
				safeClick(driver, getObjectPayment("Bento_Payment_SC_Tokenized_CVV_7777"));
				safeType(driver, getObjectPayment("Bento_Payment_SC_Tokenized_CVV_7777"), "123");
			}
		}
		Reporter.log("Entered CVV");
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		Reporter.log("Clicked on paynow");
		if (elementVisible(driver, getObjectPayment("Bento_Payment_Skip_Securecard"), 1)) {
			safeClick(driver, getObjectPayment("Bento_Payment_Skip_Securecard"));
			Thread.sleep(1000);
		}
		bento_pay_GWPage(driver, "RAZORPAYNB", "", "Success");

}

	public void bento_pay_StoredCard_UPI(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType(driver, "SC");
		textPresent_Log(driver, "Select a saved payment mode", 5);
		textPresentInElementAssert(driver,By.xpath("//div[@class='hover:bg-neutral-0'][1]"), "3212467@okhdfcbank",5);
		safeClick(driver,By.xpath("//div[@class='hover:bg-neutral-0'][1]"));
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
	}

		public void bento_pay_CC(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType(driver, "CC");
		if(CardNumber=="4111") {
			payUI_Enter_PaymentDetails(driver, "CC", "RAZORPAYDC","");
			Thread.sleep(1000);
			if(textPresent(driver, "Your wallet balance is sufficient", 2)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(2000);
			}
			smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
			//Thread.sleep(1000);
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),1))
			{
				safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
			}
			bento_pay_GWPage(driver,"PAYTM","","");
		}
		else if(CardNumber=="INVALID") {

			payUI_Enter_PaymentDetails(driver, "CC", "INVALID","");
			smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
			for (int i=0; i<=5; ++i) {
				Thread.sleep(2000);
				safeClick(driver, getObjectPayment("Bento_paynow"));
				textPresent(driver, "Invalid card number. Please re-enter the correct card details", 2);
			}
			safeClick(driver, getObjectPayment("Bento_paynow"));
			safeClick(driver, By.cssSelector("div.br-100.flex.flex-center.flex-middle.c-pointer > svg"));
			safeClick(driver, getObjectPayment("Bento_paynow"));
			textPresent_Log(driver, "Please validate captcha", 10);
			//elementPresent_log(driver, getObjectPayment("PaymentPage_GoogleCaptcha_Widget"), "Google Captcha", 5);
		}
		else if(CardNumber=="5123") {

			payUI_Enter_PaymentDetails(driver, "CC", "MASTER","");
			smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
			safeClick(driver, getObjectPayment("Bento_paynow"));
			//Save Card RBI popup
			if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
			{
				smartClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
			}
			bento_pay_GWPage(driver,"PAYU","","");

		}
		else if(CardNumber=="5241")
		{

			payUI_Enter_PaymentDetails(driver, "CC", "RAZORPAY","");
			smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");

			if (textPresent(driver, "Cleartrip wallet", 5)) { safeClick(driver,
					getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet"); Thread.sleep(2000); }

			if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
			{
				smartClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
			}
			bento_pay_GWPage(driver, "RAZORPAYCC","","");
		}
		else if(CardNumber=="3456")
		{
			payUI_Enter_PaymentDetails(driver, "CC", "AMEX","");
			smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("window.scrollBy(0,600)");
			Thread.sleep(1000);
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			if (textPresent(driver, "Cleartrip wallet", 5))
			{
				//smartClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(2000);
			}
			bento_pay_GWPage(driver, "AMEX","","");

		}

		else if(CardNumber=="7777")
		{
			payUI_Enter_PaymentDetails(driver, "CC", "PAYTM","");
			smartClick(driver, getObjectPayment("PayUI_Expressway_CheckBox_New"));
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("window.scrollBy(0,600)");
			Thread.sleep(1000);
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			if (textPresent(driver, "Cleartrip wallet", 5))
			{
				//smartClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(2000);
			}
			bento_pay_GWPage(driver,"PAYTM","","");
		}
		else
		{
			safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
			safeClick(driver, getObjectPayment("Bento_Payment_SC_CVV"));
			safeType(driver, getObjectPayment("Bento_Payment_SC_CVV"), "1234");
			Reporter.log("Entered CVV");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			if (textPresent(driver, "Cleartrip wallet", 5))
			{
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(2000);
			}
		/*	if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
			{
				safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
			}
			textPresent_Log(driver, "Please wait...", 2);
			textPresent_Log(driver, "American", 2);
			textPresent_Log(driver, "ACS Emulator", 2);
			safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
			textPresent_Log(driver, "Booking successful", 5);
			Reporter.log("Payment done successfully");*/
		}
			//bento_pay_GWPage(driver, "AMEX","","");
	}


	public void bento_pay_GWPage(RemoteWebDriver driver, String GWName, String PaymentType, String SuccessFail ) throws Exception {
		if(GWName.equalsIgnoreCase("PAYU")){
			elementVisible(driver, getObjectPayment("Bento_card_password"), 20);
			safeClick(driver, getObjectPayment("Bento_card_password"));
			Thread.sleep(1000);
			safeType(driver, getObjectPayment("Bento_card_password"),"123456");
			safeClick(driver, getObjectPayment("Bento_submit"));
		}
		else if(GWName.equalsIgnoreCase("RAZORPAYCC")){
			elementVisible(driver, getObjectPayment("Bento_Payment_Razropay_Pin"),20);
			textPresent(driver, "One Time Password (OTP) successfully sent to the phone number linked to your card ending with 0000.", 1);
			safeClick(driver, getObjectPayment("Bento_Payment_Razropay_Pin"));
			safeType(driver,getObjectPayment("Bento_Payment_Razropay_Pin"),"0000");
			safeClick(driver,getObjectPayment("Bento_Payment_Razropay_Submit"));
			textPresent_Log(driver, "Booking successful", 10);
			Reporter.log("Payment done successfully");
		}
		else if(GWName.equalsIgnoreCase("RAZORPAYNB")){
				if(SuccessFail.equalsIgnoreCase("Success")){
					Thread.sleep(2000);
					textPresent(driver, "Welcome to Razorpay Software Private Ltd Bank", 5);


					elementPresent_log(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"), "",	10);
					textPresent(driver, "Welcome to Razorpay Software Private Ltd Bank", 10);
					elementVisible(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"), 10);
					safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"));
					Reporter.log("Clicked on Success NB");
				}
				else {
					elementPresent_log(driver, getObjectPayment("Bento_Payment_NB_Payment_Failure"), "",	10);
					safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Failure"));
					Reporter.log("Clicked on failure NB");
				}
		}
		else if(GWName.equalsIgnoreCase("PAYTM")){
			if(textPresent(driver, "Bank Demo", 20)) {
					if(elementVisible(driver, By.xpath("//button[@onclick='submitSuccess()']"), 5))
					{
						safeClick(driver, By.xpath("//button[@onclick='submitSuccess()']"));
					}
				}
				else {
					textPresent(driver, "Welcome to Razorpay Software Private Ltd Bank", 2);
					if(elementVisible(driver, getObjectPayment("Bento_Payment_DC_Payment_Success"), 5))
					{
						safeClick(driver, getObjectPayment("Bento_Payment_DC_Payment_Success"));
					}
					else if(elementVisible(driver, By.xpath("//input[@type='tel']"), 10))
					{
						driver.findElement(By.xpath("//input[@type='tel']")).click();
						driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("1111");
						driver.findElement(By.xpath("//button[@id='submit-action']/span")).click();
					}
				}
				textPresent_Log(driver, "Booking successful",10);
				Reporter.log("Payment done successfully");
		}
		else if(GWName.equalsIgnoreCase("AMEX")){
			if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
			{
				smartClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
			}
			textPresent(driver, "ACS Emulator", 20);
			Thread.sleep(5000);
			smartClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
			textPresent_Log(driver, "Booking successful", 30);
			Reporter.log("Payment done successfully");
		}
	}

	public void bento_pay_BFL(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 10);
		payUI_Select_PaymentType(driver, "EMI");
		safeClick(driver, By.xpath("//div[6]/div/p"));
		Thread.sleep(2000);
		safeClick(driver, By.xpath("//div[3]/div/div[4]/div"));
		Thread.sleep(2000);
		safeClick(driver, By.xpath("//div[5]/div/div[3]/label/div/span"));
		Thread.sleep(2000);
		safeClick(driver, By.xpath("//div[2]/button"));
		Thread.sleep(2000);
		textPresent_Log(driver, "Cleartrip does not levy any charges for availing EMI.", 5);
		textPresent_Log(driver, "BAJAJ Finserv", 5);
		textPresent_Log(driver, "Cleartrip does not levy any charges for availing EMI.", 5);
		safeType(driver, By.id("cardNumber"),"2030400200341834");
		safeType(driver, By.id("name"),"Kiran");
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		safeClick(driver, By.xpath("//div[3]/button"));
		bento_pay_GWPage(driver,"RAZORPAYNB","","Success");
	}

		public void bento_pay_EMI(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 10);
		payUI_Select_PaymentType(driver, "EMI");
		//safeClick(driver, getObjectPayment("PaymentPage_EMI_ICICIBank_Radio_Btn"));
		elementVisible(driver, By.xpath("//div[10]/div/div[3]/label/div"), 5);
		if(PayType.equalsIgnoreCase("NoCostEMI")) {
			safeClick(driver, By.cssSelector("label.switch-label"));
			String NoCostEMI_Text = getText(driver, By.xpath("//div[10]/div/div[3]/label/div"));
			if (!NoCostEMI_Text.contains("No Cost")) {
				Reporter.log("No Cost text not displayed");
				//Assert.assertTrue(false);
			}

			safeClick(driver, By.xpath("//div[10]/div/div[3]/label/div"));
		}
		else if(PayType.equalsIgnoreCase("EMI")){
			String NoCostEMI_Text = getText(driver, By.xpath("//div[7]/label/div/span"));
			safeClick(driver, By.xpath("//div[7]/label/div/span"));
			if (NoCostEMI_Text.contains("No Cost")) {
				Reporter.log("No Cost text not displayed");
				Assert.assertTrue(false);
			}
		}
		if(PayType.equalsIgnoreCase("EMI")) {
				textPresent_Log(driver, "Interest charged by bank is non-refundable", 1);
		}
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);

		mouseHover(driver, By.xpath("//div[3]/button"));
		safeClick(driver, By.xpath("//div[3]/button"));
		elementVisible(driver, getObjectPayment("PaymentPage_EMI_EnterCard_Details_btn"), 10);
		scrollToElement(driver, getObjectPayment("PaymentPage_EMI_EnterCard_Details_btn"));
	//	safeClick(driver, getObjectPayment("PaymentPage_EMI_EnterCard_Details_btn"));
		//textPresent_Log(driver, "Selected EMI option", 5);

		textPresent_Log(driver, "Interest (Charged by Bank)", 5);
		if(PayType.equalsIgnoreCase("NoCostEMI")) {
			textPresent_Log(driver, "No Cost EMI discount", 1);
			textPresent_Log(driver, "is given upfront as No Cost EMI discount", 1);
		}
		else if(PayType.equalsIgnoreCase("NoCostEMI")) {
			textPresent_Log(driver, "total cost includes interest of", 1);
		}
		elementVisible(driver, getObjectPayment("PaymentPage_EMI_Change_Plan_Button"), 5);
		textPresent_Log(driver, "Enter credit card details", 5);
		Enter_CC_Details(driver, platform.value("RazorPay_Number"), platform.value("RazorPay_Month_UI"), platform.value("RazorPay_Year"), platform.value("RazorPay_CVV"));
		//safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		js.executeScript("window.scrollBy(0,500)");

		safeClick(driver, By.xpath("//div/div/div/div[3]/button"));


		Reporter.log("Clicked on paynow");
		if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),1))
		{
			safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
			Thread.sleep(1000);
		}
		bento_pay_GWPage(driver,"RAZORPAYCC","","");
	}

	public void bento_pay_NB(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		textPresent(driver, "Pay to complete your booking", 5);
		if(textPresent(driver, "Your wallet balance is sufficient", 2)) {
			safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
			Reporter.log("Deselected wallet");
			Thread.sleep(2000);
		}
		if(PayType.contains("Coupon")) {
			// Invalid Coupon Validation
			String Coupon_Value = "No Coupon";
			for(int i=3; i<=14; i++) {
				i = i+1;
				String PriceBreakup_Xpath = "//div["+i+"]/p";
				String CouponText = getText(driver, By.xpath(PriceBreakup_Xpath));
				if(CouponText.contains("Coupon code")) {
					String CouponPrice_Xpath = "//div["+i+"]/p[2]";
					Coupon_Value = getText(driver, By.xpath(CouponPrice_Xpath));
					break;
				}
			}
			bento_Select_PaymentType(driver, "NB");
			Reporter.log("Clicked on NB");
			safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));

			elementVisible(driver, By.xpath("//div[3]/div/p"), 10);

			if(!domain.equalsIgnoreCase("Hotels")) {
				String InvalidCoupon_Msg = getText(driver, By.xpath("//div[3]/div/p"));
				String Total_Price = getText(driver, By.xpath("//p[2]/span"));
				String ButtonAnyway_Text = getText(driver, By.xpath("//form/button"));
				String Coupon_Value_Popup = "";
				if (InvalidCoupon_Msg.length() > 4)
				{
					Coupon_Value_Popup = InvalidCoupon_Msg.substring(InvalidCoupon_Msg.length() - 4);
				}
				else
				{
					Coupon_Value_Popup = InvalidCoupon_Msg;
				}
				Coupon_Value_Popup= Coupon_Value_Popup.replace("₹", "").replace(".", "");

				ButtonAnyway_Text= ButtonAnyway_Text.replace("Book anyway at ", "").replace(",", "").replace("₹", "");
				InvalidCoupon_Msg= InvalidCoupon_Msg.replace(".", "");
				Total_Price= Total_Price.replace("₹", "").replace(",", "");
				Coupon_Value=Coupon_Value.replace("₹", "").replace("- ", "");

				int Coupon_Value_Breakup = Integer.parseInt(Coupon_Value);
				int Price_Without_Coupon = Integer.parseInt(ButtonAnyway_Text);
				int Coupon_Value_Popup_Int = Integer.parseInt(Coupon_Value_Popup);
				int Total_Price_Int = Integer.parseInt(Total_Price);

				Reporter.log( "Total_Price_Int "+Total_Price_Int);
				Reporter.log( "Coupon_Value_Breakup "+Coupon_Value_Breakup);
				Reporter.log( "Price_Without_Coupon "+Price_Without_Coupon);
				Reporter.log("Coupon_Value_Popup "+Coupon_Value_Popup_Int);

				if(Coupon_Value_Breakup!=Coupon_Value_Popup_Int) {
					Reporter.log("Coupon in Price Brekup "+Coupon_Value_Breakup+" & Coupon "+Coupon_Value_Popup_Int+" in Invalid Coupon Popup are not matching");
					Assert.assertTrue(false);
				}

				if(Price_Without_Coupon!=(Coupon_Value_Breakup+Total_Price_Int)) {
					Reporter.log("Price Without Coupon  "+Price_Without_Coupon+"is not equal to Sum of Coupon Value in Breakup "+Coupon_Value_Breakup+" Total amount after coupon Discount "+Total_Price_Int);
					Assert.assertTrue(false);
				}
			}
			textPresent_Log(driver, "Coupon not applicable", 5);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_Coupon_Popup_Close_Btn"), "invaid coupon Pop Up not displayed",	1);
			safeClick(driver, By.xpath("//button[2]")); // Clicking on Change paymentMode
			Thread.sleep(2000);
			textNotPresent_Log(driver, "Coupon not applicable", 1);
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));

			Thread.sleep(2000);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_Coupon_Popup_Close_Btn"), "",	1);
			safeClick(driver, By.xpath("//div[4]/div/button")); // Clicking on Continue without coupon paymentMode
			Reporter.log("Clicked on paynow in Coupon Popup");
		}
		else if(PayType.contains("Retry")) {
			bento_Select_PaymentType(driver, "NB");
			Reporter.log("Clicked on NB");
			safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			bento_pay_GWPage(driver, "RAZORPAYNB","","Failure");
			//safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Failure"));
			elementPresent_log(driver, getObjectPayment("Bento_Payment_Paynow"),"Pay button in retry page", 30);
			textPresent_Log(driver, "Oops! Your payment failed. If you were charged, any amount deducted will be reversed", 30);
			payUI_Select_PaymentType(driver, "NB");
			Reporter.log("Clicked on NB");
			Thread.sleep(1000);
			safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
			Reporter.log("Selected ICICI Bank");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			textPresent(driver, "Please wait...", 2);
			textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 20);
		}
		else {
			payUI_Select_PaymentType(driver, "NB");
			Reporter.log("Clicked on NB");
			Thread.sleep(1000);
			safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
			Reporter.log("Selected ICICI Bank");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		}
		Thread.sleep(5000);
		bento_pay_GWPage(driver, "RAZORPAYNB","","Success");
	}

	public void bento_pay_NBRetry(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		textPresent(driver, "Pay to complete your booking", 5);
		if(textPresent(driver, "Your wallet balance is sufficient", 2)) {
			safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
			Reporter.log("Deselected wallet");
			Thread.sleep(2000);
		}
		payUI_Select_PaymentType(driver, "NB");
		Reporter.log("Clicked on NB");
		Thread.sleep(1000);
		safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
		Reporter.log("Selected ICIC Bank");
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		textPresent_Log(driver, "Please wait...", 2);
		textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 20);
		safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Failure"));
		elementPresent_log(driver, getObjectPayment("Bento_Payment_Paynow"),"Pay button in retry page", 30);
		textPresent_Log(driver, "Oops! Your payment failed. If you were charged, any amount deducted will be reversed", 1);
		safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
		Reporter.log("Selected ICICI Bank");
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		textPresent_Log(driver, "Please wait...", 2);
		textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 20);
		safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"));
		Reporter.log("Payment done successfully");
	}

	public void bento_pay_TW(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		bento_Select_PaymentType(driver, "TW");
		Reporter.log("Clicked on TW");
		if(CardNumber.equals("AmazonPay")) {
			safeClick(driver, getObjectPayment("PaymentPage_Wallet_AmazonPay"));
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
			textPresent(driver, "Login with your Amazon account", 50);
			safeType(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Email"), "kiran.kumar@cleartrip.com");
			safeClick(driver, By.xpath("//span/input"));
			elementVisible(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Password"),5);
			safeType(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Password"), "Cleartrip@123");
			safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Login"));
			textPresent_Log(driver, "Select payment method", 20);
			safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_SelectCard"));
			elementVisible(driver, getObjectPayment("MakePayment_Amazon_Page_SelectCard_CVV"), 2);
			Thread.sleep(5000);
			safeType(driver, getObjectPayment("MakePayment_Amazon_Page_SelectCard_CVV"), "123");
			safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_Pay_Button"));
			elementPresent_Time(driver, getObjectPayment("MakePayment_Amazon_Page_Mock_Continue_Button"), 10);
			safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_Mock_Continue_Button"));//input
		}
	}

	public void bento_pay_Wallet(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		//safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
		Thread.sleep(1000);
		if (textPresent(driver, "Cleartrip wallet", 2)) {
			elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
			textPresent_Log(driver, "Your wallet balance is sufficient to pay for this booking. Please uncheck wallet to use other payment mode", 2);
			mouseHover(driver, getObjectPayment("Bento_Payment_Paynow"));
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			//safeClick(driver, By.xpath("//div/div/div/div[3]/button"));
			Reporter.log("Clicked on paynow");
			if(textPresent(driver,"Cleartrip wallet",2))
			{
				smartClick(driver, getObjectPayment("Bento_Payment_Paynow"));
				Reporter.log("Clicked on paynow");
			}
			textPresent(driver, "Please wait...", 10);
			Reporter.log("Payment done successfully");
			Thread.sleep(3000);
		}
	}

	public void bento_pay_PhonePe(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

		payUI_Select_PaymentType(driver, "TW");
		safeClick(driver, getObjectPayment("Bento_Payment_Wallet"));
		Reporter.log("Clicked on Wallet");
		safeClick(driver, getObjectPayment("Bento_Payment_Wallet_Phonepe"));
		Reporter.log("Selected Phonepe");
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		Reporter.log("Clicked on paynow");
		textPresent_Log(driver, "Please wait...", 5);
		textPresent_Log(driver, "PhonePe", 5);
		//textPresent_Log(driver, "SEND OTP TO LOGIN", 5);
		//textPresent(driver, "Scan&Pay via PhonePe App", 2);
		textPresent(driver, "SHOW QR CODE", 2);
		Reporter.log("PhonePe Page Validated");
		System.out.println("PhonePe Page Validated");

	}

	public void bento_pay_UPIScan(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {


		payUI_Select_PaymentType(driver, "UPI");
		Reporter.log("Clicked on UPI");
		Thread.sleep(2000);
		String Price = getText(driver, getObjectPayment("Bento_Pay_Total_Value"));
		textPresent_Log(driver, "SCAN QR CODE", 5);
		elementPresent_log(driver, getObjectPayment("Bento_Payment_Paynow"), "Show QR Code", 5);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_QRCode"), "QR Image in Payment Page", 5);
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));  //Click Show QR Code Button
		textPresent_Log(driver, "Powered by", 10);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_PoweredBy_Text"), "Powered by", 1);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_RazorPay_Image"), "Razorpay Image", 1);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Price"), "Price", 1);
		String QR_Price = getText(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Price"));
		if(!QR_Price.equals(Price)) {
			Reporter.log("Price in Payment page "+Price+" Price in QR page "+QR_Price);
			System.out.println("Price in Payment page "+Price+" Price in QR page "+QR_Price);
			//assertTrue(false);
		}
		String QRPage_URL = getURL(driver);
		Reporter.log(QRPage_URL);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Do_Not_Refresh_Text"), "Do not refresh text", 1);
		textPresent_Log(driver, "Scan here to pay with any UPI app", 1);
		textPresent_Log(driver, "Do not refresh or click 'Go Back'", 1);
		textPresent_Log(driver, "while we check your payment status", 1);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_QRCODE_Image"), "QR Code image", 1);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Gpay_Image"), "GPay Image", 1);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_PhonePe_Image"), "GPay Image", 1);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_PayTM_Image"), "PayTM Image", 1);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_CleartripLogo"), "Cleartrip logo", 1);
		safeClick(driver, getObjectPayment("Bento_Pay_UPIScan_Page_CancelPay_Link"));
		Thread.sleep(2000);
		if(!elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 30)) {
			Reporter.log("Payment page is not displayed");
			Assert.assertTrue(false);
		}
		//textPresent_Log(driver, "Payment cancelled", 10);
		//textPresent_Log(driver, "If you have already paid, please wait for a few minutes before trying again", 1);

	}

	public void bento_pay_UPI(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

		if(PayType=="SavedUPI"){
			textPresent_Log(driver,"Add new UPI",5);
			textPresent_Log(driver,"Saved UPI ID",1);
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			textPresent_Log(driver,"Enter UPI ID",5);
			textPresent_Log(driver,"Payment request will be sent to the phone",1);
			safeClick(driver, By.xpath("//div/div/div/div[3]/label/div/span"));

		}
		else if(PayType=="SavedCardTab"){

		}

		else {
			payUI_Select_PaymentType(driver, "CC");
			payUI_Select_PaymentType(driver, "UPI");
			Reporter.log("Clicked on UPI");
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("Bento_Payment_UPI_ID"));
			if(PayType.equalsIgnoreCase("Failure")) {
				safeType(driver, getObjectPayment("Bento_Payment_UPI_ID"), "failure@razorpay");
				Reporter.log("Entered UPI Details");
			}
			else {
				safeType(driver, getObjectPayment("Bento_Payment_UPI_ID"), "9986696785@ybl");
				Reporter.log("Entered UPI Details");
			}
		}
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow_UPI"));
		Reporter.log("Clicked on paynow");
		textPresent(driver, "Please wait...", 2);
		textPresent(driver, "Please accept the collect request sent to your UPI app", 5);
		Reporter.log("Payment done successfully");
		Thread.sleep(5000);
		if(PayType.equalsIgnoreCase("Failure")) {
			textPresent_Log(driver, "Oops! Your payment failed. If you were charged, any amount deducted will be reversed", 20);
		}
	}

	public void bento_pay_GV(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

		elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
		textPresent_Log(driver, "Gift card", 2);


		textPresent_Log(driver, "Pay to complete your booking", 5);
		textNotPresent_Log(driver, "Enter card details", 1);

		String GVText=getText(driver, By.xpath("//div[10]/p"));
		if(!GVText.contains("Gift card")&&GVText.contains(GV_number)) {
			Reporter.log("GV is not displayed"+GVText);
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Pay to complete your booking", 1);

		if(elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 1)) {
			Reporter.log("CC tab is displayed");
			Assert.assertTrue(false);
		}
		Reporter.log("payment tab is not displayed for full GV");
		textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare", 2);
		textPresent_Log(driver, "booking policy", 2);
		textPresent_Log(driver, "privacy policy", 1);
		textPresent_Log(driver, "terms", 1);
		textPresent_Log(driver, "Convenience fee", 1);
		Reporter.log("Includes a convenience fee of text is displayed");
		String YouPay = getText(driver, By.xpath("//p[2]/span"));
		if (!YouPay.contains("0")) {
			Reporter.log("Youpay doesn't contain 0 rs");
			Assert.assertTrue(false);
		}

		Reporter.log("Youpay  contain 0 rs");
		//String ConvFee = getText(driver, By.xpath("//div[2]/div/div[1]/div[8]/p"));
		/*
		 * if (!ConvFee.contains("100")) {
		 * Reporter.log("ConvFee doesn't contain 100 rs"); Assert.assertTrue(false); }
		 * else Reporter.log("ConvFee contain 150 rs");
		 */
		String Total = getText(driver, By.xpath("//div/div/span"));
		System.out.println("total "+Total);
		if (!Total.contains("0")) {
			Reporter.log("Total doesn't contain 0 rs");
			Assert.assertTrue(false);
		}else Reporter.log("Total contain 0 rs");

		Assert.assertEquals("Complete booking", getText(driver, getObjectPayment("Bento_Pay_Button")));
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		Reporter.log("Clicked on paynow");
		if(!PaymentType.contains("GV")) {
			textPresent_Log(driver, "Please wait...", 2);
		}
		Reporter.log("Payment done successfully");

	}

	public void bento_pay_GV_Partial(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		textPresent_Log(driver, "Pay to complete your booking", 10);
		//textPresent_Log(driver, "Gift card", 1);
		/*bento_Select_PaymentType(driver, "NB");
		Reporter.log("Clicked on NB");
		Thread.sleep(1000);
		safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
		Reporter.log("Selected ICIC Bank");
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 5);
		safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"));*/
		bento_pay_NB(driver, PaymentType, CardNumber, domain, PayType, BankName);
	}


	public void bento_pay_KNET(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		bento_Select_PaymentType(driver, "KNET");
		elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		Reporter.log("Clicked on paynow");
		elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_DropDown"), 30);
		Reporter.log("KNet Bank Page displayed");
		textPresent_Log(driver, "Cleartrip Mea Fz Llc", 2);
		safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_DropDown"), "Knet Test Card [KNET1]");
		Thread.sleep(5000);
		elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CardNumber"), 10);
		safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CardNumber"));
		safeType(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CardNumber"), "0000000001");
		safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Month"));
		safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Month"), "09");

		safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Year"));
		safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Year"), "2025");
		safeType(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CVV"), "1234");
		elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Proceed"), 10);
		safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Proceed"));
		elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Confirm"), 10);
		safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Confirm"));
		elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_RedirectionPage"), 10);
		safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_RedirectionPage"));


	}

	public void bento_pay_PartialWallet(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
		elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
		safeClick(driver,getObjectPayment("Bento_select_cardsec"));
		payUI_Enter_PaymentDetails(driver, "CC", "AMEX","");
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		Reporter.log("Clicked on paynow");
		if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
		{
			safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
		}
		textPresent_Log(driver, "Please wait...", 5);
		textPresent_Log(driver, "American", 5);
		textPresent_Log(driver, "ACS Emulator", 10);
		safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
		textPresent_Log(driver, "Booking successful", 5);
		Reporter.log("Payment done successfully");

	}

	public void bento_pay_Coupon(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		if(CardNumber.equalsIgnoreCase("Air")) {
			textPresent_Log(driver, "Coupon code (DOMCC)", 2);

		}
		if(CardNumber.equalsIgnoreCase("Hotel")) {
			textPresent_Log(driver, "Coupon code (CCHOTEL)", 2);
		}
		elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
		smartClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
		Reporter.log("Clicked on SC");
		payUI_Select_PaymentType(driver, "CC");
		payUI_Enter_PaymentDetails(driver, "CC", "RAZORPAY","");
		safeClick(driver, getObjectPayment("Bento_paynow"));
		//Save Card RBI popup
		if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
		{
			safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
		}
		/*elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
		safeClick(driver, getObjectPayment("Bento_card_password"));
		Thread.sleep(1000);
		safeType(driver, getObjectPayment("Bento_card_password"),"123456");
		safeClick(driver, getObjectPayment("Bento_submit"));*/

		textPresent(driver, "Please wait...", 5);
		textPresent(driver, "One Time Password (OTP) successfully sent to the phone number linked to your card ending with 0000.", 5);
		safeClick(driver, getObjectPayment("Bento_Payment_Razropay_Pin"));
		safeType(driver,getObjectPayment("Bento_Payment_Razropay_Pin"),"0000");
		safeClick(driver,getObjectPayment("Bento_Payment_Razropay_Submit"));
		textPresent_Log(driver, "Booking successful", 10);
		Reporter.log("Payment done successfully");

	// Razorpay NB
		/*textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 20);
		safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"));
		Reporter.log("Payment done successfully");*/
	}

	public void bento_pay_Coupon_hotel(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

		elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
		textPresent_Log(driver, "Coupon code (WALLET3)", 2);
		smartClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
		Reporter.log("Clicked on SC");
		payUI_Select_PaymentType(driver, "CC");
		payUI_Enter_PaymentDetails(driver, "CC", "MASTER","");
		safeClick(driver, getObjectPayment("Bento_paynow"));
		//Save Card RBI popup
		if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
		{
			safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
		}
		elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
		safeClick(driver, getObjectPayment("Bento_card_password"));
		Thread.sleep(1000);
		safeType(driver, getObjectPayment("Bento_card_password"),"123456");
		safeClick(driver, getObjectPayment("Bento_submit"));
	}

	public void bento_pay_AE_SC(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

		if (elementVisible(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"), 2)) {
			//safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
			Reporter.log("Deselected wallet");
			Thread.sleep(2000);
			if(CardNumber=="4000")
			{
				safeClick(driver, getObjectPayment("Bento_sc_noon"));
				safeClick(driver, getObjectPayment("Bento_sc_noon_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_noon_cvv"), "123");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_sc_noon_password"), 10);
				driver.switchTo().frame(0);
				Thread.sleep(1000);
				smartClick(driver, getObjectPayment("Bento_sc_noon_password"));
				Thread.sleep(1000);
				smartType(driver, getObjectPayment("Bento_sc_noon_password"), "1234");
				Thread.sleep(2000);
				safeClick(driver, getObjectPayment("Bento_sc_noon_password_submit"));
				textPresent_Log(driver, "Booking successful", 5);
				Reporter.log("Payment done successfully");
			}
			else if(CardNumber=="4557")
			{
				safeClick(driver, getObjectPayment("Bento_sc_noon"));
				safeClick(driver, getObjectPayment("Bento_sc_payfort_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_payfort_cvv"), "123");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_sc_payfort_password"), 5);
				safeClick(driver, getObjectPayment("Bento_sc_payfort_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_sc_payfort_password"), "12345");
				safeClick(driver, getObjectPayment("Bento_sc_payfort_submit"));
				textPresent_Log(driver, "Booking successful", 10);
				Reporter.log("Payment done successfully");
			}
			else if(CardNumber=="ADCB")
			{
				safeClick(driver,getObjectPayment("Bento_ae_adcb_select"));
				Reporter.log("Clicked on ADCB");
				System.out.println("Clicked on ADCB");
				Thread.sleep(1000);
				safeClick(driver,getObjectPayment("Bento_ae_adcb_cardnumber"));
				safeType(driver,getObjectPayment("Bento_ae_adcb_cardnumber"),"5264083966400083");
				Reporter.log("Entered on ADCB card");
				System.out.println("Entered on ADCB card");
				safeClick(driver,getObjectPayment("Bento_ae_adcb_expirymonth"));
				Thread.sleep(1000);
				safeSelectByIndex(driver,getObjectPayment("Bento_ae_adcb_expirymonth"),6);
				Reporter.log("Selected on ADCB card month");
				System.out.println("Selected on ADCB card month");
				safeClick(driver,getObjectPayment("Bento_ae_adcb_expiryyear"));
				Thread.sleep(1000);
				safeSelectByIndex(driver,getObjectPayment("Bento_ae_adcb_expiryyear"),4);
				Reporter.log("Selected on ADCB card year");
				safeClick(driver,getObjectPayment("Bento_ae_adcb_name"));
				safeType(driver,getObjectPayment("Bento_ae_adcb_name"),"test");
				Reporter.log("Entered on ADCB card name");
				safeClick(driver,getObjectPayment("Bento_ae_adcb_cvv"));
				safeType(driver,getObjectPayment("Bento_ae_adcb_cvv"),"123");
				Reporter.log("Entered on ADCB card cvv");
				safeClick(driver,getObjectPayment("Bento_ae_adcb_checkbalance"));
				textPresent_Log(driver,"Redeem ADCB TouchPoints",1);
				textPresent_Log(driver,"Available balance",1);
				textPresent_Log(driver,"A minimum amount of AED  50 must be redeemed",1);
				textPresent_Log(driver,"You will redeem AED",1);
				textPresent_Log(driver,"Your ADCB card details",1);
				textPresent_Log(driver,"You will redeem AED",1);
				elementPresent_log(driver, By.xpath("//a[contains(@href, 'https://adcbtouchpoints.com/tnc')]"), "ADCB View T&C", 1);
				String ADCB_Card = getText(driver, By.cssSelector("p.fs-3.c-neutral-900"));
				if(!ADCB_Card.contains("5264 XXXX XXXX 0083")) {
					Reporter.log("5264 XXXX XXXX 0083 card details not shown after Check Balance");
					Assert.assertTrue(false);
				}



				Reporter.log("Verified ADCB balance");
				safeClick(driver,getObjectPayment("Bento_ae_adcb_pay"));
				Thread.sleep(1000);
				elementVisible(driver,getObjectPayment("Bento_ae_adcb_otp"),5);
				textPresent_Log(driver,"Enter one time password",2);
				textPresent_Log(driver,"You pay AED",2);
				Reporter.log("Verified ADCB flow");
			}
			else
			{
				safeClick(driver, getObjectPayment("Bento_select_sc"));
				safeClick(driver, getObjectPayment("Bento_sc_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
				safeClick(driver, getObjectPayment("Bento_card_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_card_password"), "123456");
				safeClick(driver, getObjectPayment("Bento_submit"));
				textPresent_Log(driver, "Booking successful", 5);
				Reporter.log("Payment done successfully");
			}

		}
		else
		{
			if(CardNumber=="4000")
			{
				safeClick(driver, getObjectPayment("Bento_sc_noon"));
				safeClick(driver, getObjectPayment("Bento_sc_noon_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_noon_cvv"), "123");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_sc_noon_password"), 5);
				driver.switchTo().frame(0);
				Thread.sleep(1000);
				safeClick(driver, getObjectPayment("Bento_sc_noon_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_sc_noon_password"), "1234");
				safeClick(driver, getObjectPayment("Bento_sc_noon_password_submit"));
				textPresent_Log(driver, "Booking successful", 5);
				Reporter.log("Payment done successfully");

			}
			else if(CardNumber=="4557")
			{
				safeClick(driver, getObjectPayment("Bento_sc_payfort"));
				safeClick(driver, getObjectPayment("Bento_sc_payfort_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_payfort_cvv"), "123");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_sc_payfort_password"), 5);
				safeClick(driver, getObjectPayment("Bento_sc_payfort_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_sc_payfort_password"), "12345");
				safeClick(driver, getObjectPayment("Bento_sc_payfort_submit"));
				textPresent_Log(driver, "Booking successful", 5);
				Reporter.log("Payment done successfully");

			}
			else
			{
				safeClick(driver, getObjectPayment("Bento_sc_payfort"));
				safeClick(driver, getObjectPayment("Bento_sc_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
				safeClick(driver, getObjectPayment("Bento_card_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_card_password"), "123456");
				safeClick(driver, getObjectPayment("Bento_submit"));
				textPresent_Log(driver, "Booking successful", 5);
				Reporter.log("Payment done successfully");
			}
		}


	}

	public void bento_pay_Others(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

		if (elementVisible(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"), 2)) {
			//safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
			Reporter.log("Deselected wallet");
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("Bento_Select_sc"));

		} else {
			safeClick(driver, getObjectPayment("Bento_Select_sc"));
		}
		if (domain == "sa") {
			if(CardNumber=="4557")
			{
				safeClick(driver, getObjectPayment("Bento_sc_payfort"));
				safeClick(driver, getObjectPayment("Bento_sc_payfort_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_payfort_cvv"), "123");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_sc_payfort_password"), 5);
				safeClick(driver, getObjectPayment("Bento_sc_payfort_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_sc_payfort_password"), "12345");
				safeClick(driver, getObjectPayment("Bento_sc_payfort_submit"));
				textPresent_Log(driver, "Booking successful", 5);
				Reporter.log("Payment done successfully");
			}
			else if(CardNumber=="4242")
			{
				safeClick(driver, getObjectPayment("Bento_sa_sc_checkout"));
				safeClick(driver, getObjectPayment("Bento_sa_sc_checkout_cvv"));
				safeType(driver, getObjectPayment("Bento_sa_sc_checkout_cvv"), "100");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver,getObjectPayment("Bento_sa_sc_checkout_password_submit"),5);

				Thread.sleep(10000);
				driver.switchTo().frame(0);
				safeClick(driver,getObjectPayment("Bento_sa_sc_checkout_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_sa_sc_checkout_password"), "Checkout1!");
				safeClick(driver, getObjectPayment("Bento_sa_sc_checkout_password_submit"));
				textPresent_Log(driver, "Booking successful", 5);
				Reporter.log("Payment done successfully");
			}
			else if(CardNumber=="4543")
			{
				safeClick(driver, getObjectPayment("Bento_sa_sc_checkout2d"));
				safeClick(driver, getObjectPayment("Bento_sa_sc_checkout2d_cvv"));
				safeType(driver, getObjectPayment("Bento_sa_sc_checkout2d_cvv"), "956");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver,getObjectPayment("Bento_sa_sc_checkout_password_submit"),5);
				driver.switchTo().frame(0);
				Thread.sleep(2000);
				safeClick(driver, getObjectPayment("Bento_sa_sc_checkout_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_sa_sc_checkout_password"), "Checkout1!");
				safeClick(driver, getObjectPayment("Bento_sa_sc_checkout_password_submit"));
				textPresent_Log(driver, "Booking successful", 5);
				Reporter.log("Payment done successfully");
			}
			else
			{
				safeClick(driver, getObjectPayment("Bento_sc_payu"));
				safeClick(driver, getObjectPayment("Bento_sc_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
				safeClick(driver, getObjectPayment("Bento_paynow"));
			}
		} else {
			safeClick(driver, getObjectPayment("Bento_select_sc"));
			safeClick(driver, getObjectPayment("Bento_sc_cvv"));
			safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
			safeClick(driver, getObjectPayment("Bento_paynow"));
			elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
			safeClick(driver, getObjectPayment("Bento_card_password"));
			Thread.sleep(1000);
			safeType(driver, getObjectPayment("Bento_card_password"),"123456");
			safeClick(driver, getObjectPayment("Bento_submit"));

		}
		textPresent_Log(driver, "Booking successful", 10);
		Reporter.log("Payment done successfully");

	}

	public void bento_pay_SC(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {

		/*	safeClick(driver,getObjectPayment("Bento_select_cardsec"));
		payUI_Enter_PaymentDetails(driver,PayType,BankName,"");
		safeClick(driver, getObjectPayment("Bento_paynow"));
		if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
		{
		safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
		}*/
		if(CardNumber=="5123")
		{
			elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
			safeClick(driver, getObjectPayment("Bento_card_password"));
			Thread.sleep(1000);
			safeType(driver, getObjectPayment("Bento_card_password"),"123456");
			safeClick(driver, getObjectPayment("Bento_submit"));

		}
		else if(CardNumber=="5241")
		{
			if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
			{
				safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
			}

			if(textPresent(driver, "Bank Demo", 20)) {
				if(elementVisible(driver, By.xpath("//button[@onclick='submitSuccess()']"), 5))
				{
					safeClick(driver, By.xpath("//button[@onclick='submitSuccess()']"));
				}
			}
			else {
				elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
				safeClick(driver, getObjectPayment("Bento_card_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_card_password"),"123456");
				safeClick(driver, getObjectPayment("Bento_submit"));
			}

		}

		textPresent_Log(driver, "Booking successful", 10);
		Reporter.log("Payment done successfully");

	}

	public void itn_fares(RemoteWebDriver driver) throws Exception
	{
		itn_totalprice=driver.findElement(getObjectPayment("Bento_itn_totalprice")).getText();
		System.out.println("Itinerary page price:"+ itn_totalprice);
		Reporter.log("Itinerary page price:"+ itn_totalprice);
	}

	public void pay_fares(RemoteWebDriver driver) throws Exception
	{
		elementVisible(driver, getObjectPayment("Bento_pay_totalprice"), 10);
		WebElement pay_fare=driver.findElement(getObjectPayment("Bento_pay_totalprice"));
		pay_totalprice=pay_fare.getText();
		pay_totalprice=pay_totalprice.replaceAll(" ","");
		System.out.println("Payment page price:"+ pay_totalprice);
		Reporter.log("Payment page price:"+ pay_totalprice);
	}

	public void compare_fares(RemoteWebDriver driver)
	{
		System.out.println("itn_totalprice : "+itn_totalprice+" - pay_totalprice "+pay_totalprice);
		Reporter.log("itn_totalprice : "+itn_totalprice+" - pay_totalprice "+pay_totalprice);
		if(itn_totalprice.equals(pay_totalprice))
		{
			Assert.assertEquals(itn_totalprice, pay_totalprice);
			Reporter.log("Itn page fare match with pay fare");
			System.out.println("Itn page fare match with pay fare");
		}
		else
		{
			Reporter.log("Itn page fare doesn't match with pay fare");
			System.out.println("Itn page fare doesn't match with pay fare");
			//Assert.assertTrue(false);
		}
	}

}