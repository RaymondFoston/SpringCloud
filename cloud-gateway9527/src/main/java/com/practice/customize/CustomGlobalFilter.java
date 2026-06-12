package com.practice.customize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    public static final String BEGIN_VISIT_TIME = "begin_visit_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long beginVisitTime = exchange.getAttribute(BEGIN_VISIT_TIME);
            if(beginVisitTime != null) {
                log.info("Access interface host is: " + exchange.getRequest().getURI().getHost());
                log.info("Access interface port is: " + exchange.getRequest().getURI().getPort());
                log.info("Access interface URL is: " + exchange.getRequest().getURI().getPath());
                log.info("Access interface method is: " + exchange.getRequest().getMethod());
                log.info("Access interface URL's RawQuery is: " + exchange.getRequest().getURI().getRawQuery());
                log.info("Access interface duration is: " + (System.currentTimeMillis() - beginVisitTime)  + "ms");
                log.info("======================================================================");
            }
        }));
    }

    //数字越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }

    @Bean
    public GlobalFilter customFilter() {
        return new CustomGlobalFilter();
    }
}
