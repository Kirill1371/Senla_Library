//package com.lib;
//
///**
// * Hello world!
// *
// */
//public class App
//{
//    public static void main( String[] args )
//    {
//        System.out.println( "Hello World!" );
//    }
//}


package com.lib.library.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.lib.library.api",
        "com.lib.library.impl.Controller",
        "com.lib.library.impl.Security",
        "com.lib.library.impl.Config",
        "com.lib.library.impl.Service",
        "com.lib.library.impl.Repository",
        "com.lib.library.impl.mapper" // ← ДОБАВИТЬ
})
@EnableJpaRepositories(basePackages = "com.lib.library.impl.repository")
@EntityScan(basePackages = "com.lib.library.db.entity")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
