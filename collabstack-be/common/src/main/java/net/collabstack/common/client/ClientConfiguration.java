package net.collabstack.common.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfiguration {

    @Bean
    RestTemplate restTemplate() {
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(3000); // 타임아웃 설정 3초
        factory.setReadTimeout(3000); // 타임아웃 설정 3초
        return new RestTemplate(factory);
    }
}
