package ru.clevertec.hibernate.task.service;

import ru.clevertec.hibernate.task.entity.dto.PersonDto;
import ru.clevertec.hibernate.task.web.request.PersonRequest;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonDto save(PersonRequest personRequest);


    PersonDto getByUUID(UUID uuid) throws Exception;


    void deleteByUUID(UUID uuid) throws Exception;


    PersonDto update(UUID uuid, PersonRequest personRequest) throws Exception;


    List<PersonDto> findAll();

}
