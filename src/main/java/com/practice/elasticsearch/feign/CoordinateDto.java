package com.practice.elasticsearch.feign;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CoordinateDto {

	private Meta meta;
	private Document[] documents;

	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	public static class Meta {
		int total_count;
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	public static class Document {
		double x;
		double y;
	}
}
