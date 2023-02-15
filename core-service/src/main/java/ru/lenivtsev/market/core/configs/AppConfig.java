package ru.lenivtsev.market.core.configs;

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
import ru.lenivtsev.market.core.properties.BasketServiceIntegrationProperties;
import ru.lenivtsev.market.core.properties.OrderServiceIntegrationProperties;


@Configuration
@EnableConfigurationProperties(
        {BasketServiceIntegrationProperties.class,
        OrderServiceIntegrationProperties.class}
)
@RequiredArgsConstructor
public class AppConfig {
    private final BasketServiceIntegrationProperties basketProperties;
    private final OrderServiceIntegrationProperties orderProperties;
    
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

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

    @Bean
    public WebClient orderServiceWebClient(){
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, orderProperties.getConnectionTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(orderProperties.getReadTimeout()));
                    connection.addHandlerLast(new WriteTimeoutHandler(orderProperties.getWriteTimeout()));
                });
        return WebClient
                .builder()
                .baseUrl(orderProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }

}
