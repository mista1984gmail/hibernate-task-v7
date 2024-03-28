package ru.clevertec.hibernate.task.repository.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.clevertec.hibernate.task.entity.dto.PersonDto;
import ru.clevertec.hibernate.task.entity.model.Person;
import ru.clevertec.hibernate.task.mapper.PersonMapper;
import ru.clevertec.hibernate.task.repository.PersonRepository;
import ru.clevertec.hibernate.task.web.request.PersonRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonMapper personMapper;
    private final SessionFactory sessionFactory;

    @Override
    public PersonDto save(Person person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(person);
            session.flush();
            Person savedPerson = session.get(Person.class, person.getId());
            transaction.commit();
            return personMapper.personToPersonDto(savedPerson);
        }

    }

    @Override
    public Person getByUUID(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Person personFromDB = getPersonFromDB(uuid);
            transaction.commit();
            return personFromDB;
        }
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Person personFromDB = getPersonFromDB(uuid);
            session.remove(personFromDB);
            transaction.commit();
        }
    }

    @Override
    public PersonDto update(UUID uuid, PersonRequest personRequest) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Person personFromDB = getPersonFromDB(uuid);
            mergePerson(personRequest, personFromDB);
            session.persist(personFromDB);
            session.flush();
            Person savedPerson = session.get(Person.class, personFromDB.getId());
            transaction.commit();
            return personMapper.personToPersonDto(savedPerson);
        }
    }

    @Override
    public List<PersonDto> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Person> people = session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
            transaction.commit();
            return personMapper.peopleToPeopleDto(people);
        }
    }

    private Person getPersonFromDB(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT p FROM Person p WHERE p.uuidPerson = :uuid_id", Person.class)
                          .setParameter("uuid_id", uuid)
                          .uniqueResult();
        }
    }

    private void mergePerson(PersonRequest personRequest, Person personFromDB) {
        if(personRequest.getName() != null){
            personFromDB.setName(personRequest.getName());
        }
        if(personRequest.getSurname() != null){
            personFromDB.setSurname(personRequest.getSurname());
        }
        if(personRequest.getSex() != null){
            personFromDB.setSex(personRequest.getSex());
        }
        if(personRequest.getPassportSeries() != null){
            personFromDB.setPassportSeries(personRequest.getPassportSeries());
        }
        if(personRequest.getPassportNumber() != null){
            personFromDB.setPassportNumber(personRequest.getPassportNumber());
        }
        personFromDB.setUpdateDate(LocalDateTime.now());
    }

}
