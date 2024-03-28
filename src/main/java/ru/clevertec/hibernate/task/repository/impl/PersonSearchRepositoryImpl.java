package ru.clevertec.hibernate.task.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.clevertec.hibernate.task.entity.dto.PersonDto;
import ru.clevertec.hibernate.task.entity.model.Person;
import ru.clevertec.hibernate.task.mapper.PersonMapper;
import ru.clevertec.hibernate.task.repository.PersonSearchRepository;
import ru.clevertec.hibernate.task.util.Constants;

import java.util.List;

@Repository
@AllArgsConstructor
public class PersonSearchRepositoryImpl implements PersonSearchRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PersonMapper personMapper;
    private final RowMapper<Person> rowMapperPerson;


    @Override
    public List<PersonDto> findAllWithPagination(int pagesize, int page) {
        if (pagesize == 0) {
            pagesize = Constants.DEFAULT_PAGE_SIZE;
        }
        int offset = ((page - 1) * pagesize);
        String getAllPeopleWithPagination = "SELECT * FROM person limit " + pagesize + " offset " + offset;
        return personMapper.peopleToPeopleDto(jdbcTemplate.query(getAllPeopleWithPagination, rowMapperPerson));
    }

}
