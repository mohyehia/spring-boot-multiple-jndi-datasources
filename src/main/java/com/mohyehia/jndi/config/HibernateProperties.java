package com.mohyehia.jndi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Properties;

@Component
public class HibernateProperties {
    private Environment environment;

    @Autowired
    public HibernateProperties(Environment environment) {
        this.environment = environment;
    }

    public Properties getHibernateProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", Objects.requireNonNull(environment.getProperty("spring.jpa.hibernate.ddl-auto")));
        jpaProperties.put("hibernate.show-sql", Objects.requireNonNull(environment.getProperty("spring.jpa.show-sql")));
        jpaProperties.put("hibernate.dialect", Objects.requireNonNull(environment.getProperty("spring.jpa.properties.hibernate.dialect")));
        return jpaProperties;
    }
}
