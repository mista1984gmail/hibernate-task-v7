package ru.clevertec.hibernate.task.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.clevertec.hibernate.task.entity.dto.PersonDto;
import ru.clevertec.hibernate.task.entity.model.House;
import ru.clevertec.hibernate.task.entity.model.Person;
import ru.clevertec.hibernate.task.mapper.PersonMapper;
import ru.clevertec.hibernate.task.repository.HouseResidentRepository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class HouseResidentRepositoryImpl implements HouseResidentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PersonMapper personMapper;
    private final RowMapper<Person> rowMapperPerson;
    private final RowMapper<House> rowMapperHouse;


    @Override
    public List<PersonDto> findAllResidents(UUID houseUUID) {
        String findHouse = "SELECT * FROM houses as h where h.uuid_house=?";
        House house = jdbcTemplate.queryForObject(findHouse, rowMapperHouse, houseUUID);
        String query = "SELECT * FROM person where live_house_id = " + house.getId();
        return personMapper.peopleToPeopleDto(jdbcTemplate.query(query, rowMapperPerson));
    }
}
