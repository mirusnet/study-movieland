package com.mirus.movieland.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;

@Configuration
@EnableScheduling
@ComponentScan(
        basePackages = {"com.mirus.movieland"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.mirus\\.movieland\\.controller\\..*"))
public class RootConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }


    @Bean
    public BasicDataSource dataSource(
            @Value("${db.driver}") String dbDriver,
            @Value("${db.username}") String userName,
            @Value("${db.password}") String password,
            @Value("${db.url}") String url,
            @Value("${db.maxTotal:12}") int maxTotal,
            @Value("${db.maxIdle:8}") int maxIdle,
            @Value("${db.minIdle:4}") int minIdle,
            @Value("${db.initialSize:3}") int initialSize
    ) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(dbDriver);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setMaxTotal(maxTotal);
        ds.setMinIdle(maxIdle);
        ds.setMinIdle(minIdle);
        ds.setInitialSize(initialSize);
        return ds;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExecutorService executorService() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor.getThreadPoolExecutor();
    }

    @Bean
    public DataSourceTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
