package org.aryak;


import org.aryak.model.sec01.PersonOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        var person = PersonOuterClass.Person.newBuilder().setAge(26).setName("Aryak");
        log.info("Person is {}", person);

    }
}