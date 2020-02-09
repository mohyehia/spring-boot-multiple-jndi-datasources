package com.mohyehia.jndi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.mohyehia.jndi.user.dao",
        entityManagerFactoryRef = "userEntityManagerFactoryBean",
        transactionManagerRef = "userTransactionManager"
)
public class UserDataSourceConfig {
    @Value("${user.datasource}")
    private String userJndiDatasourceName;
    private HibernateProperties hibernateProperties;

    @Autowired
    public UserDataSourceConfig(HibernateProperties hibernateProperties) {
        this.hibernateProperties = hibernateProperties;
    }

    @Bean
    @Primary
    public DataSource userDataSource(){
        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        return jndiDataSourceLookup.getDataSource(userJndiDatasourceName);
    }

    @Bean
    @Primary
    public PlatformTransactionManager userTransactionManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(userEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(userDataSource());
        factoryBean.setPackagesToScan("com.mohyehia.jndi.user.entity");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        factoryBean.setJpaProperties(hibernateProperties.getHibernateProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }
}
