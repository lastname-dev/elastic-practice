package com.practice.elasticsearch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "${feign.kakao.name}", url = "${feign.kakao.url}", configuration = FeignConfig.class)
public interface KakaoFeignClient {
	@GetMapping("/geo/transcoord.json")
	CoordinateDto getCoordinate(@RequestParam double x, @RequestParam double y, @RequestParam String input_coord,
		@RequestParam String output_coord);
}
