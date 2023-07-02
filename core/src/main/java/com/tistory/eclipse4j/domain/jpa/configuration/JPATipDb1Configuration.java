package com.tistory.eclipse4j.domain.jpa.configuration;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.tistory.eclipse4j.domain.jpa.db1"},
        transactionManagerRef = "jpadb1TransactionManager",
        entityManagerFactoryRef = "jpadb1EntityManagerFactory")
public class JPATipDb1Configuration {

    @Primary
    @Bean(name = "jpadb1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.jpadb1")
    public DataSource jpadb1DataSource() {
        return new HikariDataSource();
    }

    @Primary
    @PersistenceContext(unitName = "jpadb1")
    @Bean(name = "jpadb1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean jpadb1EntityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("jpadb1DataSource") DataSource jpadb1DataSource) {
        return entityManagerFactoryBuilder
                .dataSource(jpadb1DataSource)
                .packages("com.tistory.eclipse4j.domain.jpa.db1")
                .persistenceUnit("jpadb1").
                build();
    }

    @Primary
    @Bean(name = "jpadb1TransactionManager")
    public PlatformTransactionManager jpadb1TransactionManager(
            @Qualifier("jpadb1EntityManagerFactory") EntityManagerFactory jpadb1EntityManagerFactory) {
        return new JpaTransactionManager(jpadb1EntityManagerFactory);
    }
}

