package ru.clevertec.hibernate.task.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.hibernate.task.entity.dto.HouseDto;
import ru.clevertec.hibernate.task.repository.HouseSearchRepository;
import ru.clevertec.hibernate.task.service.HouseSearchService;

import java.util.List;

@Service
@AllArgsConstructor
public class HouseSearchServiceImpl implements HouseSearchService {

    private final HouseSearchRepository houseSearchRepository;

    /**
     * Производит поиск всех домов по названию
     * страны (country), города (city) или улицы (street)
     *
     * @param name - название страны, города или улицы
     * @return лист с информацией о HouseDto
     */
    @Override
    public List<HouseDto> findByCountryOrCityOrStreet(String name) {
        return houseSearchRepository.findByCountryOrCityOrStreet(name);
    }

    /**
     * Возвращает все существующие дома с пагинацией
     *
     * @param pagesize - сколько отобразить HouseDto на одном листе
     * @param page - номер страницы
     * @return лист с информацией о HouseDto
     */
    @Override
    public List<HouseDto> findAllWithPagination(int pagesize, int page) {
        return houseSearchRepository.findAllWithPagination(pagesize, page);
    }

}
