package ru.clevertec.hibernate.task.service;

import ru.clevertec.hibernate.task.entity.dto.HouseDto;

import java.util.List;

public interface HouseSearchService {

    List<HouseDto> findByCountryOrCityOrStreet(String name);

    List<HouseDto> findAllWithPagination(int pagesize, int page);

}
