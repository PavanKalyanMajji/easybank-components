package com.easybank.fbp.loans.dto;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "loan.service")
public class AppProperties {
    private String version;
    private String name;
    private String env;
    private String configLoadedFrom;
}
