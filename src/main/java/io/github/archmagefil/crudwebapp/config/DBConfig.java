package io.github.archmagefil.crudwebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@SuppressWarnings("SpellCheckingInspection")
@Configuration
@PropertySource(encoding = "UTF-8", value = "classpath:/mysql8.properties")
@EnableTransactionManagement
public class DBConfig {
    private Environment env;

    @Bean
    DataSource dataMySql() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(
                env.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setPassword(env.getProperty("spring.datasource.username"));
        dataSource.setUsername(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    public EntityManager entityManager(LocalContainerEntityManagerFactoryBean e) {
        return Objects.requireNonNull(e.getObject()).createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFB().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFB() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emfb.setDataSource(dataMySql());
        emfb.setJpaVendorAdapter(vendorAdapter);
        emfb.setJpaProperties(adapterConfig());
        emfb.setPackagesToScan("io.github.archmagefil.crudwebapp.model");
        return emfb;
    }

    @Bean
    Properties adapterConfig() {
        Properties p = new Properties();
        p.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        p.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        p.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        return p;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }
}