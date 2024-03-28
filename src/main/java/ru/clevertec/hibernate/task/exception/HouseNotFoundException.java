package ru.clevertec.hibernate.task.exception;

import java.util.UUID;

public class HouseNotFoundException extends ClientApplicationException{
    public HouseNotFoundException(UUID uuid) {
        super(String.format("House with id: %s not found", uuid));
    }
}
