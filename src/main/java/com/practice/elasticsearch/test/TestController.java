package com.practice.elasticsearch.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.elasticsearch.feign.CoordinateDto;
import com.practice.elasticsearch.feign.FeignService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {
	private final FeignService feignService;

	@GetMapping("/")
	public ResponseEntity<CoordinateDto> getCoordinate() {
		CoordinateDto coordinate = feignService.getCoordinate(198761.2293, 451859.4837, "TM", "WGS84");
		return ResponseEntity.ok(coordinate);
	}
}
