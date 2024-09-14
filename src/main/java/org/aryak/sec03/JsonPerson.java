package org.aryak.sec03;

public record JsonPerson(

        String name,
        float height,
        int balance,
        int age,
        boolean employed,
        long accountNumber

) {
}
