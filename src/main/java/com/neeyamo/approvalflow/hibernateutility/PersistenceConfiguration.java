// 
// Decompiled by Procyon v0.5.36
// 

package com.neeyamo.approvalflow.hibernateutility;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.neeyamo.approvalflow",
		"com.neeyamo.approvalflow.pojo" }, entityManagerFactoryRef = "multiEntityManager", transactionManagerRef = "multiTransactionManager")
public class PersistenceConfiguration {

	private static final String POJO="com.neeyamo.approvalflow.pojo";
	
	@Bean(name = { "mainDataSource" })
	@ConfigurationProperties("spring.datasource.write")
	public DataSource mainDataSource() {
		return DataSourceBuilder.create().type((Class) HikariDataSource.class).build();
	}

	@Bean(name = { "clientADataSource" })
	@ConfigurationProperties("spring.datasource.read1")
	public DataSource clientADataSource() {
		return DataSourceBuilder.create().type((Class) HikariDataSource.class).build();
	}

	@Bean(name = "multiRoutingDataSource")
	public DataSource multiRoutingDataSource() {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DBTypeEnum.MAIN, mainDataSource());
		targetDataSources.put(DBTypeEnum.READ1, clientADataSource());
		MultiRoutingDataSource multiRoutingDataSource = new MultiRoutingDataSource();
		multiRoutingDataSource.setDefaultTargetDataSource(mainDataSource());
		multiRoutingDataSource.setTargetDataSources(targetDataSources);
		return multiRoutingDataSource;
	}

	@Bean(name = "multiEntityManager")
	public LocalContainerEntityManagerFactoryBean multiEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(multiRoutingDataSource());
		em.setPackagesToScan( POJO );
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(this.hibernateProperties());
		return em;
	}

	@Bean(name = "multiTransactionManager")
	public PlatformTransactionManager multiTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(multiEntityManager().getObject());
		return transactionManager;
	}

	@Primary
	@Bean(name = "dbSessionFactory")
	public LocalSessionFactoryBean dbSessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(multiRoutingDataSource());
		sessionFactoryBean.setPackagesToScan( POJO );
		sessionFactoryBean.setHibernateProperties(this.hibernateProperties());
		return sessionFactoryBean;
	}

	@Bean(name = { "entityManager" })
	public EntityManager entityManager() {
		return this.entityManagerFactory().createEntityManager();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setDatabase(Database.HSQL);
		jpaVendorAdapter.setDatabasePlatform(MySQL5Dialect.class.getName());
		jpaVendorAdapter.setGenerateDdl(false);
		return  jpaVendorAdapter;
	}

	@Bean(name = { "entityManagerFactory" })
	public EntityManagerFactory entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(this.multiRoutingDataSource());
		emf.setJpaVendorAdapter(this.jpaVendorAdapter());
		emf.setPackagesToScan( POJO );
		emf.setJpaProperties(this.hibernateProperties());
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("show_sql", true);
		properties.put("format_sql", false);
		properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.current_session_context_class",
				"org.springframework.orm.hibernate5.SpringSessionContext");
		properties.put("hibernate.multiTenancy", "SCHEMA");
		properties.put("hibernate.tenant_identifier_resolver",
				"com.neeyamo.approvalflow.hibernateutility.TenantIdentifierResolver");
		properties.put("hibernate.multi_tenant_connection_provider",
				"com.neeyamo.approvalflow.hibernateutility.MultiTenantConnectionProviderImpl");
		properties.put("hibernate.naming.implicit-strategy",
				"org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
		properties.put("hibernate.naming.physical-strategy",
				"org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
		return properties;
	}
}
