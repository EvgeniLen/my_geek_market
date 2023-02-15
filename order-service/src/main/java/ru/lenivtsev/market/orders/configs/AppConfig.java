package ru.lenivtsev.market.orders.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import ru.lenivtsev.market.orders.properties.BasketServiceIntegrationProperties;

@Configuration
@EnableConfigurationProperties(
        {BasketServiceIntegrationProperties.class}
)
@RequiredArgsConstructor
public class AppConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    private final BasketServiceIntegrationProperties basketProperties;

    @Bean
    public WebClient basketServiceWebClient(){
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, basketProperties.getConnectionTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(basketProperties.getReadTimeout()));
                    connection.addHandlerLast(new WriteTimeoutHandler(basketProperties.getWriteTimeout()));
                });
        return WebClient
                .builder()
                .baseUrl(basketProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
