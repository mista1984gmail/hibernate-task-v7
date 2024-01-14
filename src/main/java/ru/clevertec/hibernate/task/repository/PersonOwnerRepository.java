package ru.clevertec.hibernate.task.repository;

import ru.clevertec.hibernate.task.entity.dto.HouseDto;

import java.util.List;
import java.util.UUID;

public interface PersonOwnerRepository {

    List<HouseDto> findAllHousesOwn(UUID personUUID);

}
