package com.practice.elasticsearch.store;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticStoreRepository extends ElasticsearchRepository<Store,Long> {
}
