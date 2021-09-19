package es.iobuilder.tokenizer.gateway.config;

import es.iobuilder.tokenizer.gateway.config.filters.TraceabilityFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public GlobalFilter getTraceabilityFilter(){
        return new TraceabilityFilter();
    }
}
