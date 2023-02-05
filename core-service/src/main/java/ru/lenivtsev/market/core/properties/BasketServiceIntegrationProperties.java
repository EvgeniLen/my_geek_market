package ru.lenivtsev.market.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.basket-service")
@Data
public class BasketServiceIntegrationProperties {
    private String url;
    private Integer connectionTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
