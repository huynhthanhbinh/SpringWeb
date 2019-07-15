package com.bht.configurations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;


@Configuration
@EnableWebMvc
@ComponentScan("com.bht")
public class WebConfig implements WebMvcConfigurer {

    // Configure viewResolver for matching view name return by Controller
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    // Configure static resource folder for access easily -> /static/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/WEB-INF/resources/");
    }


    // Use to read messages properties for messages, logging ...
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource bundleMessageSource =
                new ReloadableResourceBundleMessageSource();

        // current class path is: src/main/java/ -> go to messages.properties
        // since all properties config files are in classpath
        // need to define the path with prefix "classpath(*):"
        // otherwise, it'll look into the web directory: webapp
        bundleMessageSource.setBasename("classpath:messages");
        bundleMessageSource.setDefaultEncoding("UTF-8");

        return bundleMessageSource;
    }


    // MultipartResolver : Spring Upload file
    @Bean
    public MultipartResolver multipartResolver() {

        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver();

        // Max-Upload-Size by Byte : ~1MB for eg.
        multipartResolver.setMaxUploadSize(1000000000);
        return multipartResolver;
    }


    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://localhost:1433;database=BHT");
        dataSource.setUsername("sa");
        dataSource.setPassword("P@ssw0rd");

        return dataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}