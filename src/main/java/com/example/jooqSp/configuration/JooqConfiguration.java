package com.example.jooqSp.configuration;

import javax.sql.DataSource;

import org.jooq.ConnectionProvider;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JooqConfiguration {

    @Bean("ConnectionProvider")
    public ConnectionProvider connectionProvider(@Qualifier("TransactionAwareDataSource") DataSource dataSource) {
        return new DataSourceConnectionProvider(dataSource);
    }

    @Primary
    @Bean(name = "JooqConfiguration")
    public org.jooq.Configuration jooqConfiguration(@Qualifier("ConnectionProvider") ConnectionProvider connectionProvider) {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.set(connectionProvider);
        configuration.set(SQLDialect.MYSQL_8_0);
        return configuration;
    }


    @Bean("ConnectionProvider2")
    public ConnectionProvider connectionProvider2(@Qualifier("TransactionAwareDataSource2") DataSource dataSource) {
        return new DataSourceConnectionProvider(dataSource);
    }

    @Bean(name = "JooqConfiguration2")
    public org.jooq.Configuration jooqConfiguration2(@Qualifier("ConnectionProvider2") ConnectionProvider connectionProvider) {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.set(connectionProvider);
        configuration.set(SQLDialect.MYSQL_8_0);
        return configuration;
    }
}
