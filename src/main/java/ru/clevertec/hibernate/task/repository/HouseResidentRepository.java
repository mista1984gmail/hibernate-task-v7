package ru.clevertec.hibernate.task.repository;

import ru.clevertec.hibernate.task.entity.dto.PersonDto;

import java.util.List;
import java.util.UUID;

public interface HouseResidentRepository {

    List<PersonDto> findAllResidents(UUID houseUUID);

}
