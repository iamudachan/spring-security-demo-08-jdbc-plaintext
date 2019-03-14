package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

//	set up the veriable to hold the jdbc
	@Autowired
	private Environment env;
	
	// settup a loger files
	private Logger logger = Logger.getLogger(getClass().getName()); 
	
	// define a bean for ViewResolver

	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	
//	define a bean for our security data source
	@Bean
	public DataSource securityDataSource() {
		// create a connection pool
		ComboPooledDataSource comboDataSource = new ComboPooledDataSource();
		// set the drive class
		try {
			comboDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException();
		}
		logger.info(">>>> url " + env.getProperty("jdbc.url"));
		logger.info(">>>> user name " + env.getProperty("jdbc.user"));
		comboDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		comboDataSource.setUser(env.getProperty("jdbc.user"));
		comboDataSource.setPassword(env.getProperty("jdbc.password"));
		comboDataSource.setInitialPoolSize(getIntProperty(env.getProperty("connection.pool.initialPoolSize")));
		comboDataSource.setMinPoolSize(getIntProperty(env.getProperty("connection.pool.minPoolSize")));
		comboDataSource.setMaxPoolSize(getIntProperty(env.getProperty("connection.pool.maxPoolSize")));
		comboDataSource.setMaxIdleTime(getIntProperty(env.getProperty("connection.pool.maxIdleTime")));
		return comboDataSource;
	}
	
	private int getIntProperty(String property) {
//		String propValue = env.getProperty(property);
		return Integer.parseInt(property);
	}
	
}









