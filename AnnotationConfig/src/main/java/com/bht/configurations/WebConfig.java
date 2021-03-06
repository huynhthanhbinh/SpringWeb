package com.bht.configurations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableWebMvc
@ComponentScan("com.bht")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class WebConfig implements WebMvcConfigurer {


    // In Spring, we can use annotation @PropertySource
    // to externalize our configurations to
    // a properties file under src/main/resources folder
    // Therefore, we can get these values through
    // @Value annotation or Environment object(@Autowired)
    // For eg. @Value("${mssql.url}") private String url
    // Remind: using # for comment in properties file !
    @Autowired
    Environment environment;


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
    // Implement methods of << WebMvcConfigurer >>
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


    // DataSource / Database Configuration
    // Using properties file in src/main/resources
    // @PropertySource("classpath:db.properties")
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("mssql.DriverClassName"));
        dataSource.setUrl(environment.getProperty("mssql.Url"));
        dataSource.setUsername(environment.getProperty("mssql.Username"));
        dataSource.setPassword(environment.getProperty("mssql.Password"));

        return dataSource;
    }


    // Template for processing with JDBC Spring
    /*@Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }*/


    // As Environment obj is in the same class
    // Configurer method need to be STATIC one
    // As environment object can access directly
    @Bean
    public static PropertySourcesPlaceholderConfigurer
    placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    // Config Session Factory / Hibernate
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {

        // Session Factory Configure
        LocalSessionFactoryBean bean =
                new LocalSessionFactoryBean();


        // DataSource as learnt before:
        // Eg. MSSQL Config, DB: BHT, localhost
        bean.setDataSource(dataSource());


        // Package contains class mapping record to model
        // eg. com.bht.entities
        bean.setPackagesToScan("com.bht.entities");

        // Hibernate Properties for hibernate extra config
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect",
                environment.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.show_sql",
                environment.getProperty("hibernate.show_sql"));
        hibernateProperties.put("hibernate.connection.pool_size",
                environment.getProperty("hibernate.connection.pool_size"));
        hibernateProperties.put("hibernate.connection.autocommit",
                environment.getProperty("hibernate.connection.autocommit"));


        // Assign hibernateProperties to SessionFactory Config
        bean.setHibernateProperties(hibernateProperties);

        return bean;
    }


    // For handling transactions / connections
    // Spring support @EnableTransactionManagement
    // Transaction use in Service Class, DAO Class
    // not using for Controller !!! meaningless !!
    // 2 ways of using TX:
    // + Transaction for the whole class service/DAO
    // + Transaction for each unit method inside service/DAO
    // We can have multi different bean transaction manager
    // Therefore, we need to declare exactly the bean name
    /*@Bean("transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }*/
    @Bean("transactionManager")
    public HibernateTransactionManager hibernateTransactionManager(
            @Autowired SessionFactory sessionFactory) {

        HibernateTransactionManager hibernateTransactionManager =
                new HibernateTransactionManager();

        // Set session factory
        hibernateTransactionManager.setSessionFactory(sessionFactory);

        return hibernateTransactionManager;
    }
}