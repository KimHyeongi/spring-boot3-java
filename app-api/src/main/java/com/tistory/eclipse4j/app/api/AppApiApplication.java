package com.tistory.eclipse4j.app.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = "com.tistory.eclipse4j",
        exclude = {
        DataSourceAutoConfiguration.class})
public class AppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApiApplication.class, args);
    }
}
