package com.herokuapp.restfulbroker;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingIdsTest {
	@Test
	public void getBookIdsWithoutFilter() {
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
		response.print();
		
		Assert.assertEquals(response.getStatusCode(), 200, "Got error response");
		
		List<Integer> list = response.jsonPath().getList("bookkingIds");
		Assert.assertFalse(list.isEmpty(), "List is empty");
	}
}
