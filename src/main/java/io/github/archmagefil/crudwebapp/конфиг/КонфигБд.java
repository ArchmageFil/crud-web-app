package io.github.archmagefil.crudwebapp.конфиг;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@SuppressWarnings("SpellCheckingInspection")
@Configuration
@PropertySource(encoding = "UTF-8", value = "classpath:/конфиг.properties")
@ComponentScan("io.github.archmagefil.crudwebapp")
public class КонфигБд {
    private Environment кфг;

    @Bean
    DataSource настройкиMySql() {
        DriverManagerDataSource настройки = new DriverManagerDataSource();
        настройки.setDriverClassName(Objects.requireNonNull(
                кфг.getProperty("spring.datasource.driver-class-name")));
        настройки.setUrl(кфг.getProperty("spring.datasource.url"));
        настройки.setPassword(кфг.getProperty("spring.datasource.username"));
        настройки.setUsername(кфг.getProperty("spring.datasource.password"));
        return настройки;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean фабрикаСущностей() {
        LocalContainerEntityManagerFactoryBean фс = new LocalContainerEntityManagerFactoryBean();
        фс.setDataSource(настройкиMySql());
        фс.setPackagesToScan("io.github.archmagefil.crudwebapp.модель");
        JpaVendorAdapter адаптер = new HibernateJpaVendorAdapter();
        фс.setJpaVendorAdapter(адаптер);
        фс.setJpaProperties(настройкиАдаптера());
        return фс;
    }

    @Bean
    Properties настройкиАдаптера() {
        Properties переменные = new Properties();
        переменные.setProperty("hibernate.show_sql",
                кфг.getProperty("hibernate.show_sql"));
        переменные.setProperty("hibernate.hbm2ddl.auto",
                кфг.getProperty("hibernate.hbm2ddl.auto"));
        переменные.setProperty("hibernate.dialect",
                кфг.getProperty("hibernate.dialect"));
        return переменные;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Autowired
    public void setКфг(Environment кфг) {
        this.кфг = кфг;
    }
}