package com.herokuapp.restfulbroker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.restfulbroker.common.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetBookingTest extends BaseTest {
	@Test(enabled = false)
	public void getBooking() {
		
		Response response = createBooking();
		response.print();
		
		int bookingId = response.jsonPath().getInt("bookingid");
		
		spec.pathParam("bookingId", bookingId);
		Response response1 = RestAssured.given(spec).get("/booking/{bookingId}");
		response1.print();
		
		Assert.assertEquals(response.getStatusCode(), 200, "Error Resp");
		
		SoftAssert sAssert = new SoftAssert();
		sAssert.assertEquals(response1.jsonPath().getString("firstname"), "Dmitry", "Incorrectname");
		sAssert.assertEquals(response1.jsonPath().getInt("totalprice"), 150, "Incorrectprince");
		sAssert.assertEquals(response1.jsonPath().getBoolean("depositpaid"), false, "Incorrectpaid");
		sAssert.assertEquals(response1.jsonPath().getString("bookingdates.checkin"), "2020-03-25", "Incorrectdate");
		
		sAssert.assertAll();
	}
	
	@Test
	public void getBookingXMLTest() {
		// Create booking
		Response responseCreate = createBooking();
		responseCreate.print();

		// Set path parameter
		spec.pathParam("bookingId", responseCreate.jsonPath().getInt("bookingid"));

		// Get response with booking
		Header xml = new Header("Accept", "application/xml");
		spec.header(xml);
		Response response = RestAssured.given(spec).get("/booking/{bookingId}");
		response.print();

		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		String actualFirstName = response.xmlPath().getString("booking.firstname");
		softAssert.assertEquals(actualFirstName, "Dmitry", "firstname in response is not expected");

		String actualLastName = response.xmlPath().getString("booking.lastname");
		softAssert.assertEquals(actualLastName, "Shyshkin", "lastname in response is not expected");

		int price = response.xmlPath().getInt("booking.totalprice");
		softAssert.assertEquals(price, 150, "totalprice in response is not expected");

		boolean depositpaid = response.xmlPath().getBoolean("booking.depositpaid");
		softAssert.assertFalse(depositpaid, "depositpaid should be false, but it's not");

		String actualCheckin = response.xmlPath().getString("booking.bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2020-03-25", "checkin in response is not expected");

		String actualCheckout = response.xmlPath().getString("booking.bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2020-03-27", "checkout in response is not expected");

		String actualAdditionalneeds = response.xmlPath().getString("booking.additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Baby crib", "additionalneeds in response is not expected");

		softAssert.assertAll();
	}
}
