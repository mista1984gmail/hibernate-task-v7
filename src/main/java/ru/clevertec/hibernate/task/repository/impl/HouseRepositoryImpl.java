package ru.clevertec.hibernate.task.repository.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.clevertec.hibernate.task.entity.dto.HouseDto;
import ru.clevertec.hibernate.task.entity.model.House;
import ru.clevertec.hibernate.task.mapper.HouseMapper;
import ru.clevertec.hibernate.task.repository.HouseRepository;
import ru.clevertec.hibernate.task.web.request.HouseRequest;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class HouseRepositoryImpl implements HouseRepository {

    private final HouseMapper houseMapper;
    private final SessionFactory sessionFactory;

    @Override
    public HouseDto save(House house) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(house);
            session.flush();
            House savedHouse = session.get(House.class, house.getId());
            transaction.commit();
            return houseMapper.houseToHouseDto(savedHouse);
        }
    }

    @Override
    public House getByUUID(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            House houseFromDB = getHouseFromDB(uuid);
            transaction.commit();
            return houseFromDB;
        }
    }


    @Override
    public void deleteByUUID(UUID uuid) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            House houseFromDB = getHouseFromDB(uuid);
            session.remove(houseFromDB);
            transaction.commit();
        }
    }

    @Override
    public HouseDto update(UUID uuid, HouseRequest houseRequest) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            House houseFromDB = getHouseFromDB(uuid);
            mergeHouse(houseRequest, houseFromDB);
            session.persist(houseFromDB);
            session.flush();
            House savedHouse = session.get(House.class, houseFromDB.getId());
            transaction.commit();
            return houseMapper.houseToHouseDto(savedHouse);
        }
    }

    @Override
    public List<HouseDto> findAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<House> houses = session.createQuery("SELECT h FROM House h", House.class).getResultList();
            transaction.commit();
            return houseMapper.housesToHousesDto(houses);
        }
    }

    private House getHouseFromDB(UUID uuid) {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT h FROM House h WHERE h.uuidHouse = :uuid_id", House.class)
                          .setParameter("uuid_id", uuid)
                          .uniqueResult();
        }
    }

    private void mergeHouse(HouseRequest houseRequest, House houseFromDB) {
        if(houseRequest.getArea() != null){
            houseFromDB.setArea(houseRequest.getArea());
        }
        if(houseRequest.getCountry() != null) {
            houseFromDB.setCountry(houseRequest.getCountry());
        }
        if(houseRequest.getCity() != null) {
            houseFromDB.setCity(houseRequest.getCity());
        }
        if(houseRequest.getStreet() != null) {
            houseFromDB.setStreet(houseRequest.getStreet());
        }
        if(houseRequest.getNumber() != null) {
            houseFromDB.setNumber(houseRequest.getNumber());
        }
    }
}
