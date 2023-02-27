package ru.lenivtsev.market.core.configAspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.lenivtsev.market.core.service.StatisticService;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AppLoggingAspect {
    private final StatisticService statisticService;

    @Around("execution(public * ru.lenivtsev.controller.*.*(..))")
    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        statisticService.addStatistic(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName(), duration);
        return out;
    }
}
