package com.practice.elasticsearch.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.elasticsearch.store.Location;
import com.practice.elasticsearch.store.StoreDto;
import com.practice.elasticsearch.store.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

	// private final CSVReader csvReader;
	private final StoreService storeService;
	@GetMapping("/{name}")
	public ResponseEntity<Void> getCoordinate(@PathVariable String name) {
		storeService.save();
		return ResponseEntity.ok().build();
	}
	@PostMapping
	public ResponseEntity<Void> saveStores() {
		StoreDto storeDto = new StoreDto(2,"안녕하세요 저는 홍길동 입니다.","서울시",new Location(33,111));
		storeService.save();
		return ResponseEntity.ok().build();
	}
}
