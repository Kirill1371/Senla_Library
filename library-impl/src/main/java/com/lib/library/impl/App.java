package com.lib.library.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.lib.library.api",
        "com.lib.library.impl.controller",
        "com.lib.library.impl.security",
        "com.lib.library.impl.config",
        "com.lib.library.impl.service",
        "com.lib.library.impl.repository",
        "com.lib.library.impl.mapper", // ← ДОБАВИТЬ
        "com.lib.library.impl.exception"
})
@EnableJpaRepositories(basePackages = "com.lib.library.impl.repository")
@EntityScan(basePackages = "com.lib.library.db.entity")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
