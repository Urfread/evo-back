//package com.urfread.breaknews.core.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableJpaRepositories(basePackages = "com.urfread.breaknews.core")  // 设置包路径，扫描 JPA 仓库
//@EnableTransactionManagement
//public class JpaConfig {
//
//    // 数据源配置
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://192.168.171.130:3306/break");
//        dataSource.setUsername("new_user");
//        dataSource.setPassword("your_password");
//        return dataSource;
//    }
//
//    // 配置 EntityManagerFactory，连接 JPA 和 Hibernate
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setPackagesToScan("com.urfread.breaknews.core");  // 实体类包路径
//        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // 设置 Hibernate 适配器
//        factoryBean.setJpaProperties(hibernateProperties());
//        return factoryBean;
//    }
//
//    // 配置 Hibernate 的属性
//    private java.util.Properties hibernateProperties() {
//        java.util.Properties properties = new java.util.Properties();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//        properties.put("hibernate.hbm2ddl.auto", "update");  // 控制表结构更新策略，关闭时不会删除表
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
//        return properties;
//    }
//
//    // 配置事务管理器
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
//}
