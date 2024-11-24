package com.urfread.breaknews.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Class Description: A brief description of the functionality and purpose of this class.
 *
 * @author urfread
 * @date 2024-10-08 10:38
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//@SpringBootApplication
public class BreakNewsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BreakNewsApplication.class, args);
    }
}
