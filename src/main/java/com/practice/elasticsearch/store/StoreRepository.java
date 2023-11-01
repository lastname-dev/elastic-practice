package com.practice.elasticsearch.store;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StoreRepository extends ElasticsearchRepository<Store,Long> {
}
