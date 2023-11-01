package com.practice.elasticsearch.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
	private long id;
	private String name;
	private String address;
	private long lat;
	private long lon;
}
