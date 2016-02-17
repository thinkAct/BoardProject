package com.mmventures.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.ConnectionReleaseMode;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

@Configuration
@PropertySource("classpath:com/mmventures/config/db.properties")
public class DBConfig {
    
    @Value("${db.driverClass}") 
    private Class<? extends java.sql.Driver> DB_DRIVER_CLASS;
    @Value("${db.url}") 
    private String DB_URL;
    @Value("${db.username}") 
    private String DB_USER_NAME;
    @Value("${db.password}") 
    private String DB_USER_PASSWORD;
    @Value("${target.package}") 
    private String[] PACKAGE_TARGET;
    
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(DB_DRIVER_CLASS);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER_NAME);
        dataSource.setPassword(DB_USER_PASSWORD);

        return dataSource;
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put(Environment.SHOW_SQL, "true");
        // SQL 포멧 정렬
        properties.put(Environment.FORMAT_SQL, "true");

        properties.put(Environment.DIALECT,
                "org.hibernate.dialect.MySQLDialect");

        // create | spawn | spawn-drop | update | validate
        // 개발시에만 update, 완료 후 none 으로 면경
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.RELEASE_CONNECTIONS,
                ConnectionReleaseMode.ON_CLOSE);

        // 자동커밋 - 일단 true
        properties.put(Environment.AUTOCOMMIT, "true");

        return properties;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(
                dataSource());

        sessionBuilder.addProperties(hibernateProperties());
        sessionBuilder.scanPackages(PACKAGE_TARGET);

        return sessionBuilder.buildSessionFactory();
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory());

        return transactionManager;
    }
}
