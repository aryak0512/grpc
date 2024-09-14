package org.aryak;


import org.aryak.model.sec01.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        var person1 = Person.newBuilder().setAge(26).setName("Aryak").build();
        log.info("Person 1 is {}", person1);

        var person2 = Person.newBuilder().setAge(26).setName("Aryak").build();
        log.info("Person 2 is {}", person2);

        log.info("equals : {}", person1.equals(person2));
        log.info("== : {}", person1 == person2);

        var person3 = Person.newBuilder().clearName().setAge(35).build();
        log.info("Person 3 is {}", person3);

        var person4 = person1.toBuilder().setAge(524).build();
        log.info("Person 4 : {}", person4);


    }
}