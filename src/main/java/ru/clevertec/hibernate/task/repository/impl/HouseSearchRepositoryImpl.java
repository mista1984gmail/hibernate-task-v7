package ru.clevertec.hibernate.task.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.clevertec.hibernate.task.entity.dto.HouseDto;
import ru.clevertec.hibernate.task.entity.model.House;
import ru.clevertec.hibernate.task.mapper.HouseMapper;
import ru.clevertec.hibernate.task.repository.HouseSearchRepository;
import ru.clevertec.hibernate.task.util.Constants;

import java.util.List;

@Repository
@AllArgsConstructor
public class HouseSearchRepositoryImpl implements HouseSearchRepository {

    private final JdbcTemplate jdbcTemplate;
    private final HouseMapper houseMapper;
    private final RowMapper<House>rowMapperHouse;

    @Override
    public List<HouseDto> findByCountryOrCityOrStreet(String name) {
        String findHouseSQL = "SELECT * FROM houses WHERE to_tsvector(country) || to_tsvector(city) || to_tsvector(street) @@ plainto_tsquery('" + name + "')";
        return houseMapper.housesToHousesDto(jdbcTemplate.query(findHouseSQL, rowMapperHouse));
    }

    @Override
    public List<HouseDto> findAllWithPagination(int pagesize, int page) {
        if (pagesize == 0) {
            pagesize = Constants.DEFAULT_PAGE_SIZE;
        }
        int offset = ((page - 1) * pagesize);
        String getAllHousesWithPagination = "SELECT * FROM houses limit " + pagesize + " offset " + offset;
        return houseMapper.housesToHousesDto(jdbcTemplate.query(getAllHousesWithPagination, rowMapperHouse));
    }

}
