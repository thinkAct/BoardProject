package com.mmventures.study.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.ConnectionReleaseMode;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

@Configuration
@EnableTransactionManagement
public class ApplicationContextConfig {
	  private final String DB_URL = "jdbc:mysql://localhost:3306/study";
	    private final String DB_USER_NAME = "root";
	    private final String DB_USER_PASSWORD = "";
	    
	    private final String[] PACKAGE_TARGET = {"com.mmventures.study.dto"};

	    @Bean(name = "dataSource")
	    public DataSource getDataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USER_NAME);
		dataSource.setPassword(DB_USER_PASSWORD);

		return dataSource;
	    }

	    private Properties getHibernateProperties() {
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
	    public SessionFactory sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(
			dataSource);

		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.scanPackages(PACKAGE_TARGET);

		return sessionBuilder.buildSessionFactory();
	    }

	    @Bean(name = "transactionManager")
	    public HibernateTransactionManager getTransactionManager(
		    SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = 
			new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	    }
}
