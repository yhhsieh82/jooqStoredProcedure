package com.example.jooqSp.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionalManagerConfiguration {
	@Primary
	@Bean("TransactionManager")
	public DataSourceTransactionManager createTransactionManager(
			@Qualifier("TransactionAwareDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("TransactionManager2")
	public DataSourceTransactionManager createTransactionManager2(
			@Qualifier("TransactionAwareDataSource2") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("ChainedTransactionalManager")
	public ChainedTransactionManager chainedTransactionManager(@Qualifier("TransactionManager") DataSourceTransactionManager a,
															   @Qualifier("TransactionManager2")  DataSourceTransactionManager b) {
		return new ChainedTransactionManager(a, b);
	}
}
