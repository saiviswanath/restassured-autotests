package com.herokuapp.restfulbroker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookingdates {
	private String checkin;
	private String checkout;
}
