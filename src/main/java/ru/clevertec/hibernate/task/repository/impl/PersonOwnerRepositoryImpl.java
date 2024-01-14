package ru.clevertec.hibernate.task.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.clevertec.hibernate.task.entity.dto.HouseDto;
import ru.clevertec.hibernate.task.entity.model.House;
import ru.clevertec.hibernate.task.entity.model.Person;
import ru.clevertec.hibernate.task.mapper.HouseMapper;
import ru.clevertec.hibernate.task.repository.PersonOwnerRepository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PersonOwnerRepositoryImpl implements PersonOwnerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final HouseMapper houseMapper;
    private final RowMapper<Person> rowMapperPerson;
    private final RowMapper<House>rowMapperHouse;

    @Override
    public List<HouseDto> findAllHousesOwn(UUID personUUID) {
        String findPerson = "SELECT * FROM person as p where p.uuid_person=?";
        Person person = jdbcTemplate.queryForObject(findPerson, rowMapperPerson, personUUID);
        String query = "SELECT h.id, h.uuid_house, h.area, h.country, h.city, h.street, h.number, h.create_date from houses as h, person_own_house as poh " +
                "where poh.house_id = h.id and poh.person_id = " + person.getId();
        return houseMapper.housesToHousesDto(jdbcTemplate.query(query, rowMapperHouse));
    }
}
