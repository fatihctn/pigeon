package com.fatihctn.pigeon.data.config;

import com.fatihctn.pigeon.data.constant.CoreDB;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class CorePersistenceContext {

    @Value("${datasource.core.driver-class-name}")
    private String driverClassName;

    @Value("${datasource.core.jdbc-url}")
    private String jdbcUrl;

    @Value("${datasource.core.username}")
    private String username;

    @Value("${datasource.core.password}")
    private String password;

    @Value("${datasource.core.max-pool-size}")
    private int maxPoolSize;

    @Value("${datasource.core.name:CoreDBPool}")
    private String poolName;

    @Value("${datasource.core.show-sql}")
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
}
