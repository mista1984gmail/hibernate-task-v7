package ru.clevertec.hibernate.task.service;

import ru.clevertec.hibernate.task.entity.dto.PersonDto;

import java.util.List;

public interface PersonSearchService {

    List<PersonDto> findAllWithPagination(int pagesize, int page);

}
