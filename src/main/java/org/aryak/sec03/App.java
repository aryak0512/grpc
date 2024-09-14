package org.aryak.sec03;

import org.aryak.model.sec03.Address;
import org.aryak.model.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    static Path path = Path.of("person.out");

    public static void main(String[] args) {

        var address = Address.newBuilder()
                .setCity("Mumbai")
                .setStreet("St. Regis St")
                .setZipCode(435673)
                .build();

        var person = Person.newBuilder().setAge(23)
                .setName("aryak")
                .setAccountNumber(532745237465L)
                .setEmployed(true)
                .setHeight(169.453f)
                .setAddress(address)
                .build();

        serialize(person);

        log.info("Deserialized : {}", deserialize());
    }

    private static void serialize(Person person) {
        try {
            person.writeTo(Files.newOutputStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Person deserialize() {
        try {

            var inputStream = Files.newInputStream(path);
            return Person.parseFrom(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
