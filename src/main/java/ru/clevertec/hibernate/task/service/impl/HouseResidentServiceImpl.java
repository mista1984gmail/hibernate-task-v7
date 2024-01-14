package ru.clevertec.hibernate.task.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.hibernate.task.entity.dto.PersonDto;
import ru.clevertec.hibernate.task.repository.HouseResidentRepository;
import ru.clevertec.hibernate.task.service.HouseResidentService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HouseResidentServiceImpl implements HouseResidentService {

    private final HouseResidentRepository houseResidentRepository;

    /**
     * Производит поиск всех жилетей в данном доме
     *
     * @param houseUUID - UUID House
     * @return лист с информацией о PersonDto
     */
    @Override
    public List<PersonDto> findAllResidents(UUID houseUUID) {
        return houseResidentRepository.findAllResidents(houseUUID);
    }

}
