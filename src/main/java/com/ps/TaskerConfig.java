package com.ps;

import com.ps.tasks.boundary.FileSystemStorageService;
import com.ps.tasks.boundary.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Slf4j
@Configuration
public class TaskerConfig {

    @Bean
    public Clock clock() {
        log.info("Register system clock as Spring bean");
        return new SystemClock();
    }

    @Bean
    public StorageService storageService() {
        return new FileSystemStorageService(Paths.get("/home/masterix/upload"));
    }
}
