package com.herokuapp.restfulbroker;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.restfulbroker.common.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingIdsTest extends BaseTest {
	@Test(enabled = false)
	public void getBookIdsWithoutFilter() {
		Response response = RestAssured.given(spec).get("/booking");
		response.print();
		
		Assert.assertEquals(response.getStatusCode(), 200, "Got error response");
		
		List<Integer> list = response.jsonPath().getList("bookkingIds");
		Assert.assertFalse(list.isEmpty(), "List is empty");
	}
	
	@Test
	public void getBookIdsWithFilter() {
		spec.queryParam("firstName", "Dmitry");
		Response response = RestAssured.given(spec).get("/booking");
		response.print();
		
		Assert.assertEquals(response.getStatusCode(), 200, "Got error response");
		
		List<Integer> list = response.jsonPath().getList("bookkingIds");
		Assert.assertFalse(list.isEmpty(), "List is empty");
	}
	
	
}
