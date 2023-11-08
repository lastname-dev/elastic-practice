package com.practice.elasticsearch.store;

import java.util.List;

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

	@Transactional
	public void save() {
		List<Store> all = storeRepository.findAll();
		for(Store store : all) {
			elasticStoreRepository.save(store);
		}
	}
}
