package com.practice.elasticsearch.store;

import java.io.IOException;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class StoreService {

	private final ElasticStoreRepository elasticStoreRepository;
	private final StoreRepository storeRepository;
	private final RestHighLevelClient client;

	@Transactional
	public void save(StoreDto storeDto) {
		Store savedStore = storeRepository.save(Store.from(storeDto));
		elasticStoreRepository.save(savedStore);
	}

	@Transactional
	public void searchAll(double topLat, double topLong, double bottomLat, double bottomLong) throws IOException {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
			.query(new GeoBoundingBoxQueryBuilder("location").setCorners(new GeoPoint(topLat,bottomLong),new GeoPoint(bottomLat,topLong)));

		SearchRequest searchRequest = new SearchRequest("stores").source(searchSourceBuilder);

		SearchResponse searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);

		log.info("결과 : {}",searchResponse.toString());
	}
	@Transactional
	public void searchByName(double topLat, double topLong, double bottomLat, double bottomLong,String name) throws
		IOException {

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		// 이름에 대한 검색 쿼리 추가
		boolQueryBuilder.must(QueryBuilders.matchQuery("name", name));

		// 위치에 대한 GeoBoundingBoxQuery 추가
		boolQueryBuilder.must(new GeoBoundingBoxQueryBuilder("location")
			.setCorners(new GeoPoint(topLat, bottomLong), new GeoPoint(bottomLat, topLong)));

		searchSourceBuilder.query(boolQueryBuilder);

		SearchRequest searchRequest = new SearchRequest("stores").source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
	}
}
