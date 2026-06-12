package com.practice.customize;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class CustomRoutePredicateFactory extends AbstractRoutePredicateFactory<CustomRoutePredicateFactory.Config> {
    public CustomRoutePredicateFactory() {
        super(CustomRoutePredicateFactory.Config.class);
    }

    @Validated
    public static class Config {
         @Setter
         @Getter
         @NotEmpty
        private String userType;
    }

    @Override
    public Predicate<ServerWebExchange> apply(CustomRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            public boolean test(ServerWebExchange serverWebExchange) {
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                if(userType == null){return false;}
                if(userType.equalsIgnoreCase(config.userType)) {return true;}
                return false;

            }
        };
    }

    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }
}
