package ru.clevertec.hibernate.task.exception;

public class ClientApplicationException extends RuntimeException{
    public ClientApplicationException(String message) {
        super(message);
    }
}
