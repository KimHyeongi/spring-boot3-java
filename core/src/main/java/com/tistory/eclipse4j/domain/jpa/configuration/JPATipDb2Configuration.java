package com.tistory.eclipse4j.domain.jpa.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.tistory.eclipse4j.domain.jpa.db2"},
        transactionManagerRef = "jpadb2TransactionManager",
        entityManagerFactoryRef = "jpadb2EntityManagerFactory")
public class JPATipDb2Configuration {

    @Bean(name = "jpadb2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.jpadb2")
    public DataSource jpadb2DataSource() {
        return new HikariDataSource();
    }

    @PersistenceContext(unitName = "jpadb2")
    @Bean(name = "jpadb2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean jpadb2EntityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("jpadb2DataSource") DataSource jpadb2DataSource) {
        return entityManagerFactoryBuilder
                .dataSource(jpadb2DataSource)
                .packages("com.tistory.eclipse4j.domain.jpa.db2")
                .persistenceUnit("jpadb2").build();
    }

    @Bean(name = "jpadb2TransactionManager")
    public PlatformTransactionManager jpadb2TransactionManager(
            @Qualifier("jpadb2EntityManagerFactory") EntityManagerFactory jpadb2EntityManagerFactory) {
        return new JpaTransactionManager(jpadb2EntityManagerFactory);
    }
}
