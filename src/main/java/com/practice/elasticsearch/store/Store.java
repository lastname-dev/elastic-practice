package com.practice.elasticsearch.store;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(indexName = "stores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Store {
	@Id
	private Long id;
	private String name;
	private String address;
	private double lat;
	private double lon;

	public static Store from(StoreDto storeDto) {
		return Store.builder()
			.id(storeDto.getId())
			.name(storeDto.getName())
			.lat(storeDto.getLat())
			.lon(storeDto.getLon())
			.address(storeDto.getAddress())
			.build();
	}
}
