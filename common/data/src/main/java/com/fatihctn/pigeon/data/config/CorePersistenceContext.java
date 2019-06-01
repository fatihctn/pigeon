package com.fatihctn.pigeon.data.config;

import com.fatihctn.pigeon.data.constant.CoreDB;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class CorePersistenceContext {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.maximum-pool-size}")
    private int maxPoolSize;

    @Value("${spring.datasource.name:CoreDBPool}")
    private String poolName;

    @Value("${spring.jpa.show-sql}")
    private boolean showSql;

    @Bean(name = CoreDB.DATASOURCE)
    public DataSource coreDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setPoolName(poolName);

        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = CoreDB.ENTITY_MANAGER)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier(CoreDB.DATASOURCE) DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(CoreDB.BASE_PACKAGES)
                .persistenceUnit(CoreDB.PERSISTENCE_UNIT)
                .build();
    }

    @Bean(name = CoreDB.TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(@Qualifier(CoreDB.ENTITY_MANAGER) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = CoreDB.JDBC_TEMPLATE)
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier(CoreDB.DATASOURCE) DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
