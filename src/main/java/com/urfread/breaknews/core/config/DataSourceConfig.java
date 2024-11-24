package com.urfread.breaknews.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://47.96.40.238:3306/break_news");
//        dataSource.setUsername("remote_user");
        dataSource.setUrl("jdbc:mysql://192.168.171.130:3306/break_news");
        dataSource.setUsername("new_user");
        dataSource.setPassword("your_password");
        return dataSource;
    }
}


