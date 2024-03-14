package com.herokuapp.restfulbroker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingTest {
	@Test
	public void getBooking() {
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/1");
		response.print();
		
		Assert.assertEquals(response.getStatusCode(), 200, "Error Resp");
		
		SoftAssert sAssert = new SoftAssert();
		sAssert.assertEquals(response.jsonPath().getString("firstname"), "Jim", "Incorrectname");
		sAssert.assertEquals(response.jsonPath().getInt("totalprice"), 397, "Incorrectprince");
		sAssert.assertEquals(response.jsonPath().getBoolean("depositpaid"), false, "Incorrectpaid");
		sAssert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), "2020-07-26", "Incorrectdate");
		
		sAssert.assertAll();
	}
}
