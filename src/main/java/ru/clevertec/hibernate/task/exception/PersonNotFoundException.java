package ru.clevertec.hibernate.task.exception;

import java.util.UUID;

public class PersonNotFoundException extends ClientApplicationException{
    public PersonNotFoundException(UUID uuid) {
        super(String.format("Person with id: %s not found", uuid));
    }
}
