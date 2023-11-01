package com.practice.elasticsearch.feign;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeignService {
	private final KakaoFeignClient kakaoFeignClient;

	public CoordinateDto getCoordinate( double x,  double y,  String input_coord,
		 String output_coord) {
		return kakaoFeignClient.getCoordinate(x, y, input_coord, output_coord);
	}
}
