package ru.clevertec.hibernate.task.repository;

import ru.clevertec.hibernate.task.entity.dto.PersonDto;
import ru.clevertec.hibernate.task.entity.model.Person;
import ru.clevertec.hibernate.task.web.request.PersonRequest;

import java.util.List;
import java.util.UUID;

public interface PersonRepository {

    PersonDto save(Person person);

    Person getByUUID(UUID uuid);

    void deleteByUUID(UUID uuid);

    PersonDto update(UUID uuid, PersonRequest personRequest);

    List<PersonDto> findAll();

}
