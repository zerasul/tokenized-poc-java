package es.iobuilder.tokenizer.gateway.config.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TraceabilityFilter implements GlobalFilter, Ordered {

    private Logger log = LoggerFactory.getLogger(TraceabilityFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        //Trazability: This logs are only for demostration Pruposes. We can use ELK stack for more accuracy.

        log.info("Trazability Start------------------");
        log.info("Path {}",request.getPath().pathWithinApplication().value());
        log.info("HttpMethod {}; URL {}", request. getMethod(),request.getURI().getRawPath());
        log.info("Headers----------------: ");
        HttpHeaders headers = request.getHeaders();
        headers.forEach((key, value) -> log.info("{}: {}", key, value));
        log.info("headers End------------");

        return chain.filter(exchange);
    }



    @Bean
    public GlobalFilter customFilter() {
        return new TraceabilityFilter();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
