package ru.clevertec.hibernate.task.service;

import ru.clevertec.hibernate.task.entity.dto.HouseDto;

import java.util.List;
import java.util.UUID;

public interface PersonOwnerService {

    List<HouseDto> findAllHousesOwn(UUID personUUID);

}
