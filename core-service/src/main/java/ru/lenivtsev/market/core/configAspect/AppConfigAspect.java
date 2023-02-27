package ru.lenivtsev.market.core.configAspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("ru.lenivtsev")
public class AppConfigAspect {
}
