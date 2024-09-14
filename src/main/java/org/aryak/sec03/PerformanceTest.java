package org.aryak.sec03;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aryak.model.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PerformanceTest {

    private static final Logger log = LoggerFactory.getLogger(PerformanceTest.class);
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {

        // proto object
        Person person = Person.newBuilder()
                .setAge(23)
                .setName("aryak")
                .setAccountNumber(532745237465L)
                .setEmployed(true)
                .setHeight(169.453f)
                .setBalance(-74537)
                .build();

        // pojo
        JsonPerson jsonPerson = new JsonPerson("aryak", 168.453f, -74537, 23, true, 532745237465L);

        jsonSerde(jsonPerson);
        protoSerde(person);

        // Java clod start, ignore first reading, JVM need to warm up and optimise code
        for ( int i = 0; i < 5; i++ ) {
            runTest("proto", () -> protoSerde(person));
            runTest("json", () -> jsonSerde(jsonPerson));
        }

    }

    private static void protoSerde(Person person) {
        try {
            byte[] bytes = person.toByteArray();
            Person.parseFrom(bytes);
            //log.info("Proto bytes length : {}", bytes.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void jsonSerde(JsonPerson jsonPerson) {

        try {
            byte[] bytes = objectMapper.writeValueAsBytes(jsonPerson);
            objectMapper.readValue(bytes, JsonPerson.class);
            //log.info("Json bytes length : {}", bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runTest(String testName, Runnable r) {

        long t1 = System.currentTimeMillis();
        for ( int i = 0; i < 50_00_000; i++ ) {
            r.run();
        }
        long t2 = System.currentTimeMillis();

        log.info("Time taken by {} is : {} ms", testName, t2 - t1);
    }
}
