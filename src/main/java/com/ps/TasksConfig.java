package com.ps;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.tasks")
@Getter
@Setter
public class TasksConfig {

    private String endpointMessage;

}
