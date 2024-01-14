package ru.clevertec.hibernate.task.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.hibernate.task.entity.dto.HouseDto;
import ru.clevertec.hibernate.task.entity.model.House;
import ru.clevertec.hibernate.task.exception.HouseNotFoundException;
import ru.clevertec.hibernate.task.mapper.HouseMapper;
import ru.clevertec.hibernate.task.repository.HouseRepository;
import ru.clevertec.hibernate.task.service.HouseService;
import ru.clevertec.hibernate.task.web.request.HouseRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;

    /**
     * Создаёт новоый House из HouseRequest
     * задает рандомный UUID и время создания
     *
     * @param houseRequest HouseRequest с информацией о создании
     */
    @Override
    public HouseDto save(HouseRequest houseRequest) {
        House house = houseMapper.requestToEntity(houseRequest);
        house.setUuidHouse(UUID.randomUUID());
        house.setCreateDate(LocalDateTime.now());
        return houseRepository.save(house);
    }

    /**
     * Ищет дом по идентификатору
     *
     * @param uuid идентификатор House
     * @return найденный HouseDto
     * @throws HouseNotFoundException если House не найден
     */
    @Override
    public HouseDto getByUUID(UUID uuid) throws Exception {
        House house = getHouseByUUID(uuid);
        return houseMapper.houseToHouseDto(house);
    }

    /**
     * Удаляет существующий дом
     *
     * @param uuid идентификатор House для удаления
     * @throws HouseNotFoundException если House не найден
     */
    @Override
    public void deleteByUUID(UUID uuid) throws Exception {
        House house = getHouseByUUID(uuid);
        houseRepository.deleteByUUID(house.getUuidHouse());
    }

    /**
     * Обновляет уже существующий дом из информации полученной в DTO
     *
     * @param uuid     идентификатор House для обновления
     * @param houseRequest HouseRequest с информацией об обновлении
     * @throws HouseNotFoundException если House не найден
     */
    @Override
    public HouseDto update(UUID uuid, HouseRequest houseRequest) throws Exception {
        House house = getHouseByUUID(uuid);
        return houseRepository.update(uuid, houseRequest);
    }

    /**
     * Возвращает все существующие дома
     *
     * @return лист с информацией о HouseDto
     */
    @Override
    public List<HouseDto> findAll() {
        return houseRepository.findAll();
    }

    /**
     * Ищет дом по идентификатору
     *
     * @param uuid идентификатор House
     * @return найденный House
     * @throws HouseNotFoundException если House не найден
     */
    private House getHouseByUUID(UUID uuid) throws Exception {
        House house = houseRepository.getByUUID(uuid);
        if (house.getId() == null) {
            throw new HouseNotFoundException(uuid);
        } else {
            return house;
        }
    }

}
