package com.practice.elasticsearch.es;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

import com.practice.elasticsearch.store.ElasticStoreRepository;

@EnableElasticsearchRepositories(basePackageClasses = ElasticStoreRepository.class)
@EnableFeignClients
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

	@Value(value = "${elasticsearch.host}")
	private String ELASTIC_URL;
	@Value(value = "${elasticsearch.port}")
	private String ELASTIC_PORT;
	@Value(value = "${elasticsearch.user_name}")
	private String ELASTIC_USER_NAME;
	@Value(value = "${elasticsearch.user_password}")
	private String ELASTIC_USER_PASSWORD;
	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		System.out.println(ELASTIC_USER_NAME + " "+ ELASTIC_USER_PASSWORD);

		ClientConfiguration clientConfiguration = ClientConfiguration.builder()
			.connectedTo(ELASTIC_URL+":"+ELASTIC_PORT)
			.withBasicAuth(ELASTIC_USER_NAME,ELASTIC_USER_PASSWORD)
			.build();
		return RestClients.create(clientConfiguration).rest();

	}
	@Bean
	public ElasticsearchOperations elasticsearchOperations(){
		return new ElasticsearchRestTemplate(elasticsearchClient());
	}

}
