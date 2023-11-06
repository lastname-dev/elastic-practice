package com.practice.elasticsearch.store;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String address;

	@Embedded
	private Location location;

	public static Store from(StoreDto storeDto) {
		return Store.builder()
			.name(storeDto.getName())
			.location(storeDto.getLocation())
			.address(storeDto.getAddress())
			.build();
	}
}
