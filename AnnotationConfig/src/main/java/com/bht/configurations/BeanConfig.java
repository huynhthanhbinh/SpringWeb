package com.bht.configurations;

import com.bht.models.Order;
import com.bht.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class BeanConfig {

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
