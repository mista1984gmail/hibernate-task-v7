package ru.clevertec.hibernate.task.repository;

import ru.clevertec.hibernate.task.entity.dto.HouseDto;

import java.util.List;

public interface HouseSearchRepository {

    List<HouseDto> findByCountryOrCityOrStreet(String name);

    List<HouseDto> findAllWithPagination(int pagesize, int page);

}
