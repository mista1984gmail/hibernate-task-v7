package ru.clevertec.hibernate.task.service;

import ru.clevertec.hibernate.task.entity.dto.HouseDto;
import ru.clevertec.hibernate.task.web.request.HouseRequest;

import java.util.List;
import java.util.UUID;

public interface HouseService {

    HouseDto save(HouseRequest houseRequest);

    HouseDto getByUUID(UUID uuid) throws Exception;

    void deleteByUUID(UUID uuid) throws Exception;

    HouseDto update(UUID uuid, HouseRequest houseRequest) throws Exception;

    List<HouseDto> findAll();

}
