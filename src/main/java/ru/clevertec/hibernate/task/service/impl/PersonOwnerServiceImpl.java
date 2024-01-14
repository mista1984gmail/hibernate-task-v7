package ru.clevertec.hibernate.task.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.hibernate.task.entity.dto.HouseDto;
import ru.clevertec.hibernate.task.repository.PersonOwnerRepository;
import ru.clevertec.hibernate.task.service.PersonOwnerService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonOwnerServiceImpl implements PersonOwnerService {

    private final PersonOwnerRepository personOwnerRepository;

    /**
     * Производит поиск всех домов, которыми владеет Person
     *
     * @param personUUID - UUID Person
     * @return лист с информацией о HouseDto
     */
    @Override
    public List<HouseDto> findAllHousesOwn(UUID personUUID) {
        return personOwnerRepository.findAllHousesOwn(personUUID);
    }

}
