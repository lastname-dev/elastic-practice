package com.practice.elasticsearch.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.practice.elasticsearch.csv.CSVReader;
import com.practice.elasticsearch.feign.CoordinateDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

	private final CSVReader csvReader;
	@GetMapping("/{name}")
	public ResponseEntity<CoordinateDto> getCoordinate(@PathVariable String name) {
		csvReader.readCSV(name);
		return ResponseEntity.ok(null);
	}
}
