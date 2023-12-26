package com.example.SalarySurvey.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.example.SalarySurvey.Util.LoggerUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;


@Configuration
public class ElasticSearchConfiguration {

    LoggerUtil loggerUtil = new LoggerUtil(ElasticSearchConfiguration.class);

    @Value("${elasticsearch.fingerprint}")
    private String fingerprint;

    @Value("${elasticsearch.username}")
    private String username;
    @Value("${elasticsearch.password}")
    private String password;

    @Value("${elasticsearch.hostname}")
    private String hostname;
    @Value("${elasticsearch.port}")
    private String port;
    private static final String scheme = "https";
    @Bean
    public RestClient getRestClient() {

        try {

            SSLContext sslContext = TransportUtils.sslContextFromCaFingerprint(fingerprint);

            BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
            credsProv.setCredentials(
                    AuthScope.ANY, new UsernamePasswordCredentials(username, password)
            );

            loggerUtil.info("Establishing SSL Verified Connection");

            RestClient restClient = RestClient
                    .builder(new HttpHost(hostname, Integer.parseInt(port), scheme))
                    .setHttpClientConfigCallback(hc -> hc
                            .setSSLContext(sslContext)
                            .setDefaultCredentialsProvider(credsProv)
                    )
                    .build();

            loggerUtil.info("Connection Established!");
            return restClient;
        }
        catch (Exception e){
            loggerUtil.error("Caught Exception while trying to establish connection. : " ,e);
            e.printStackTrace();
        }

        return null;

    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport() {
        return new RestClientTransport(
                getRestClient(), new JacksonJsonpMapper());
    }


    @Bean
    public ElasticsearchClient getElasticsearchClient() {
        ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
        return client;
    }
}
