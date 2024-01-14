package ru.clevertec.hibernate.task.service;


import ru.clevertec.hibernate.task.entity.dto.PersonDto;

import java.util.List;
import java.util.UUID;

public interface HouseResidentService {

    List<PersonDto> findAllResidents(UUID houseUUID);

}
