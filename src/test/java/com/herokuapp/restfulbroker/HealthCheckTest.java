package com.herokuapp.restfulbroker;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.herokuapp.restfulbroker.common.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HealthCheckTest extends BaseTest {
	
	@Test(enabled = false)
	public void healthCheckTest() {
		given().spec(spec).
		when().
			get("/ping").
				then().
					assertThat().statusCode(201);
	}
	
	@Test
	public void headerAndCookieTest() {
		
		spec.header(new Header("TestHeader1", "ValueHeader1"));
		
		spec.cookie(new Cookie.Builder("TestCookie1", "valuecookie1").build());
		
		Response response = RestAssured.given(spec).
				cookie("TestCookie", "valuecookie").
				header("TestHeader", "ValueHeader").
				log().all().
				get("/ping");
		
		Headers headers = response.getHeaders();
		System.out.println("Headers: " + headers);
		
		Header header = headers.get("Server");
		System.out.println("ServerHeader: " + header.getName());
		
		String svrHeader = response.getHeader("Server");
		System.out.println("ServerHeader1: " + svrHeader);
		
		Cookies cookies = response.getDetailedCookies();
		
		System.out.println("Cookies: " + cookies);
	}

}
