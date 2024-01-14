package ru.clevertec.hibernate.task.repository;

import ru.clevertec.hibernate.task.entity.dto.PersonDto;

import java.util.List;

public interface PersonSearchRepository {

    List<PersonDto> findAllWithPagination(int pagesize, int page);

}
