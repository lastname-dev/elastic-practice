package com.practice.elasticsearch.store;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
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
	public void search(double topLat, double topLong, double bottomLat, double bottomLong) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
			.query(new GeoBoundingBoxQueryBuilder("location").setCorners(new GeoPoint(topLat,topLong),new GeoPoint(bottomLat,bottomLong)));

		SearchRequest searchRequest = new SearchRequest("stores").source(searchSourceBuilder);

		SearchResponse searchResponse =client.search(searchRequest,ResultOpt)

	}
}
