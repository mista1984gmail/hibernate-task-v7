package ru.clevertec.hibernate.task.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.hibernate.task.entity.dto.PersonDto;
import ru.clevertec.hibernate.task.entity.model.Person;
import ru.clevertec.hibernate.task.exception.PersonNotFoundException;
import ru.clevertec.hibernate.task.mapper.PersonMapper;
import ru.clevertec.hibernate.task.repository.PersonRepository;
import ru.clevertec.hibernate.task.service.PersonService;
import ru.clevertec.hibernate.task.web.request.PersonRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    /**
     * Создаёт новоый Person из PersonRequest
     * задает рандомный UUID и время создания
     *
     * @param personRequest PersonRequest с информацией о создании
     */
    @Override
    public PersonDto save(PersonRequest personRequest) {
        Person person = personMapper.requestToEntity(personRequest);
        person.setUuidPerson(UUID.randomUUID());
        person.setCreateDate(LocalDateTime.now());
        person.setUpdateDate(person.getCreateDate());
        return personRepository.save(person);
    }

    /**
     * Ищет Person по идентификатору
     *
     * @param uuid идентификатор Person
     * @return найденный PersonDto
     * @throws PersonNotFoundException если Person не найден
     */
    @Override
    public PersonDto getByUUID(UUID uuid) throws Exception {
        Person person = getPersonByUUID(uuid);
        return personMapper.personToPersonDto(person);
    }

    /**
     * Удаляет существующий Person
     *
     * @param uuid идентификатор Person для удаления
     * @throws PersonNotFoundException если Person не найден
     */
    @Override
    public void deleteByUUID(UUID uuid) throws Exception {
        Person person = getPersonByUUID(uuid);
        personRepository.deleteByUUID(person.getUuidPerson());
    }

    /**
     * Обновляет уже существующий Person из информации полученной в DTO
     *
     * @param uuid     идентификатор Person для обновления
     * @param personRequest PersonRequest с информацией об обновлении
     * @throws PersonNotFoundException если Person не найден
     */
    @Override
    public PersonDto update(UUID uuid, PersonRequest personRequest) throws Exception {
        Person person = getPersonByUUID(uuid);
        return personRepository.update(uuid, personRequest);
    }

    /**
     * Возвращает все существующие PersonDto
     *
     * @return лист с информацией о HouseDto
     */
    @Override
    public List<PersonDto> findAll() {
        return personRepository.findAll();
    }

    /**
     * Ищет Person по идентификатору
     *
     * @param uuid идентификатор Person
     * @return найденный Person
     * @throws PersonNotFoundException если Person не найден
     */
    private Person getPersonByUUID(UUID uuid) throws Exception {
        Person person = personRepository.getByUUID(uuid);
        if (person.getId() == null) {
            throw new PersonNotFoundException(uuid);
        } else {
            return person;
        }
    }

}
