package bht.config;

import bht.models.Order;
import bht.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan("bht.controllers")
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
                .addResourceLocations("/resources/");
    }


    @Bean(name = "person", initMethod = "initPerson", destroyMethod = "destroyPerson")
    @Scope("prototype")
    public Person person() {
        return new Person("Steven", 19);
    }


    @Bean(name = "person2", initMethod = "initPerson", destroyMethod = "destroyPerson")
    @Scope("prototype")
    public Person person2() {
        return new Person("Richard", 20);
    }


    @Bean("order")
    @Autowired
    public Order order(Person person) {
        return new Order(person);
    }


    @Bean("order2")
    @Autowired
    @Qualifier("person2")
    public Order order2(Person person) {
        return new Order(person);
    }
}