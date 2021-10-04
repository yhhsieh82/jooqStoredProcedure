package com.example.jooqSp.configuration;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@Configuration
public class DataSourceConfiguration {

    @Primary
    @Bean("MySqlDataSourceProperties")
    @ConfigurationProperties("spring.datasource.testdb")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("MySqlDataSource")
    public DataSource mysqlDataSource(@Qualifier(value = "MySqlDataSourceProperties") DataSourceProperties properties){
        return DataSourceBuilder.create(properties.getClassLoader())
                .type(HikariDataSource.class)
                .url(properties.getUrl())
                .driverClassName(properties.getDriverClassName())
                .username(properties.getUsername())
                .password(properties.getPassword()).build();
    }
    
    @Primary
    @Bean(value = "TransactionAwareDataSource")
    public DataSource swaplogWriteTransactionAwareDataSource(@Qualifier("MySqlDataSource") DataSource dataSource) {
        return new TransactionAwareDataSourceProxy(dataSource);
    }


    // second database for testing
    @Bean("MySqlDataSourceProperties2")
    @ConfigurationProperties("spring.datasource.testdb2")
    public DataSourceProperties MySqlDataSourceProperties2() {
        return new DataSourceProperties();
    }

    @Bean("MySqlDataSource2")
    public DataSource MySqlDataSource2(@Qualifier(value = "MySqlDataSourceProperties2") DataSourceProperties properties){
        return DataSourceBuilder.create(properties.getClassLoader())
                .type(HikariDataSource.class)
                .url(properties.getUrl())
                .driverClassName(properties.getDriverClassName())
                .username(properties.getUsername())
                .password(properties.getPassword()).build();
    }

    @Bean(value = "TransactionAwareDataSource2")
    public DataSource swaplogWriteTransactionAwareDataSource2(@Qualifier("MySqlDataSource2") DataSource dataSource) {
        return new TransactionAwareDataSourceProxy(dataSource);
    }
}
