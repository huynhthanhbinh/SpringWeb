package bht.model;

import org.apache.log4j.Logger;

public class Person {
    private Logger logger = Logger.getRootLogger();

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void initPerson() {
        logger.info("On init bean person !");
    }

    public void destroyPerson() {
        logger.info("On destroy person !");
    }
}
