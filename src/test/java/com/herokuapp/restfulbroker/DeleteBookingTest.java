package com.herokuapp.restfulbroker;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.restfulbroker.common.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBookingTest extends BaseTest {
	@Test
	public void deleteBookingTest() {
		// Create booking
		Response responseCreate = createBooking();
		responseCreate.print();

		// Get bookingId of new booking
		int bookingid = responseCreate.jsonPath().getInt("bookingid");

		// Delete booking
		Response responseDelete = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.delete("https://restful-booker.herokuapp.com/booking/" + bookingid);
		responseDelete.print();
		
		// Verifications
		// Verify response 201
		Assert.assertEquals(responseDelete.getStatusCode(), 201, "Status code should be 201, but it's not.");
	
		Response responseGet = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingid);
		responseGet.print();
		
		Assert.assertEquals(responseGet.getBody().asString(), "Not Found", "Body should be 'Not Found', but it's not.");
		
	}
}
