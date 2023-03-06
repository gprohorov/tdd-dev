package edu.pro.tdddev.config;
/*
  @author   george
  @project   tdd-dev
  @class  AuditorAwareImpl
  @version  1.0.0 
  @since 06.03.23 - 21.54
*/

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}
