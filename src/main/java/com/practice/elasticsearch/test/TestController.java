package com.practice.elasticsearch.test;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import com.practice.elasticsearch.csv.CSVReader;
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
		// csvReader.readCSV(name);
		return ResponseEntity.ok().build();
	}
	@PostMapping
	public ResponseEntity<Void> saveStores() {
		StoreDto storeDto = new StoreDto(2,"안녕하세요 저는 홍길동 입니다.","서울시",new Location(33,111));
		storeService.save(storeDto);
		return ResponseEntity.ok().build();
	}
	@GetMapping
	public ResponseEntity<Void> searchStores(@RequestParam double topLat, @RequestParam double topLong, @RequestParam double bottomLat, @RequestParam double bottomLong) throws
		IOException {
		storeService.searchAll(topLat,topLong,bottomLat,bottomLong);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/search")
	public ResponseEntity<Void> searchStoreByName(@RequestParam double topLat, @RequestParam double topLong, @RequestParam double bottomLat, @RequestParam double bottomLong ,@RequestParam String name) throws
		IOException {
		storeService.searchByName(topLat,topLong,bottomLat,bottomLong,name);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/popluar")
	public ResponseEntity<?> popularSearch(@RequestParam String name) {
		return ResponseEntity.ok(storeService.popularSearch(name));
	}
}
