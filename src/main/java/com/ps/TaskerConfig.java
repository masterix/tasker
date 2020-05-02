package com.ps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TaskerConfig {

    @Bean
    public Clock clock() {
        log.info("Register system clock as Spring bean");
        return new SystemClock();
    }
}
