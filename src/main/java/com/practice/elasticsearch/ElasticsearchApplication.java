package com.practice.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.practice.elasticsearch.store.ElasticStoreRepository;

@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
	type = FilterType.ASSIGNABLE_TYPE,
	classes = ElasticStoreRepository.class))
@SpringBootApplication
@EnableFeignClients
public class ElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}


}
