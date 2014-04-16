package com.springdatademo;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource () {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/pg_sbox");
        dataSource.setUsername("shinya");
        dataSource.setPassword("");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(getClass().getPackage().getName());
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProperties());

        return factory;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");

        //properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        //properties.put("hibernate.connection.url", "jdbc:h2:db/app;CIPHER=AES");
        //properties.put("hibernate.connection.username", "root");
        //properties.put("hibernate.connection.password", "root root");
        //properties.put("hibernate.connection.pool_size", "1");
        //properties.put("hibernate.use_sql_comments", "true");
        //properties.put("hibernate.c3p0.min_size", "5");
        //properties.put("hibernate.c3p0.max_size", "20");
        //properties.put("hibernate.c3p0.timeout", "300");
        //properties.put("hibernate.c3p0.max_statements", "50");
        //properties.put("hibernate.c3p0.idle_test_period", "3000");
        //properties.put("hibernate.cache.use_second_level_cache", "true");
        //properties.put("hibernate.cache.region.factory_class",
        //"org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
        //properties.put("hibernate.cache.use_query_cache", "true");
        //properties.put("hibernate.cache.use_minimal_puts", "true");
        //properties.put("hibernate.max_fetch_depth", "10"); // for one-to-one and many-to-one, Not For one-to-many

        // see also -> http://docs.jboss.org/hibernate/core/3.3/reference/en/html/session-configuration.html

        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }
}
