package com.mohyehia.jndi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.mohyehia.jndi.booking.dao",
        entityManagerFactoryRef = "bookingEntityManagerFactoryBean",
        transactionManagerRef = "bookingTransactionManager"
)
public class BookingDataSourceConfig {
    @Value("${booking.datasource}")
    private String bookingJndiDatasourceName;
    private HibernateProperties hibernateProperties;

    @Autowired
    public BookingDataSourceConfig(HibernateProperties hibernateProperties) {
        this.hibernateProperties = hibernateProperties;
    }

    @Bean
    public DataSource bookingDataSource(){
        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        return jndiDataSourceLookup.getDataSource(bookingJndiDatasourceName);
    }

    @Bean
    public PlatformTransactionManager bookingTransactionManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(bookingDataSource());
        return jpaTransactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean bookingEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(bookingDataSource());
        factoryBean.setPackagesToScan("com.mohyehia.jndi.booking.entity");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        factoryBean.setJpaProperties(hibernateProperties.getHibernateProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }
}
