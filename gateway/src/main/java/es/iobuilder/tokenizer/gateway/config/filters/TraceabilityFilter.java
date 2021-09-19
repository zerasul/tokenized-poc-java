package es.iobuilder.tokenizer.gateway.config.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TraceabilityFilter implements GlobalFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(TraceabilityFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        //Traceability: This logs are only for demostration Pruposes. We can use ELK stack for more accuracy.

        logger.info("Traceability Start------------------");
        logger.info("Path {}",request.getPath().pathWithinApplication().value());
        logger.info("HttpMethod {}; URL {}", request. getMethod(),request.getURI().getRawPath());
        logger.info("Headers----------------: ");
        HttpHeaders headers = request.getHeaders();
        headers.forEach((key, value) -> logger.info("{}: {}", key, value));
        logger.info("headers End------------");

        return chain.filter(exchange).then(Mono.fromRunnable(() ->{
            logger.info("Response Code: {}", exchange.getResponse().getRawStatusCode());
        }));
    }





    @Override
    public int getOrder() {
        return -1;
    }
}
