package com.practice.elasticsearch.es;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.practice.elasticsearch.store.ElasticStoreRepository;

@EnableElasticsearchRepositories(basePackageClasses = ElasticStoreRepository.class)
@EnableFeignClients
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

	@Value(value = "${elasticsearch.host}")
	private String ELASTIC_URL;
	@Value(value = "${elasticsearch.port}")
	private String ELASTIC_PORT;
	@Override
	public RestHighLevelClient elasticsearchClient() {
		ClientConfiguration clientConfiguration = ClientConfiguration.builder()
			.connectedTo(ELASTIC_URL+":"+ELASTIC_PORT)
			.build();
		return RestClients.create(clientConfiguration).rest();
	}
}
