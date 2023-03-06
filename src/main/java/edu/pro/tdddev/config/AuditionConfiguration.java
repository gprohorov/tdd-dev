package edu.pro.tdddev.config;
/*
  @author   george
  @project   tdd-dev
  @class  AuditionConfiguration
  @version  1.0.0 
  @since 06.03.23 - 22.03
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class AuditionConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
