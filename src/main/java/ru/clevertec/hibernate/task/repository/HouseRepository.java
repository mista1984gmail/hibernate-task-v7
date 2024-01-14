package ru.clevertec.hibernate.task.repository;

import ru.clevertec.hibernate.task.entity.dto.HouseDto;
import ru.clevertec.hibernate.task.entity.model.House;
import ru.clevertec.hibernate.task.web.request.HouseRequest;

import java.util.List;
import java.util.UUID;

public interface HouseRepository {

    HouseDto save(House house);

    House getByUUID(UUID uuid);

    void deleteByUUID(UUID uuid);

    HouseDto update(UUID uuid, HouseRequest houseRequest);

    List<HouseDto> findAll();

}
