package ru.clevertec.hibernate.task.config.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import ru.clevertec.hibernate.task.entity.model.House;
import ru.clevertec.hibernate.task.entity.model.Person;
import ru.clevertec.hibernate.task.entity.model.Sex;
import ru.clevertec.hibernate.task.mapper.HouseMapper;
import ru.clevertec.hibernate.task.mapper.HouseMapperImpl;
import ru.clevertec.hibernate.task.mapper.PersonMapper;
import ru.clevertec.hibernate.task.mapper.PersonMapperImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Configuration
public class MapperConfig {

	private final static DateTimeFormatter FORMATTER_FOR_LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Bean
	public HouseMapper houseMapper(){
		return new HouseMapperImpl();
	}

	@Bean
	public PersonMapper personMapper (){
		return new PersonMapperImpl();
	}

	@Bean
	public RowMapper<Person> rowMapperPerson(){
		RowMapper<Person> rowMapper = (rs, rowNum)->{
			Person person = new Person();
			person.setId(rs.getLong("id"));
			person.setUuidPerson(UUID.fromString(rs.getString("uuid_person")));
			person.setName(rs.getString("name"));
			person.setSurname((rs.getString("surname")));
			person.setSex(Sex.valueOf(rs.getString("sex")));
			person.setPassportSeries((rs.getString("passport_series")));
			person.setPassportNumber((rs.getString("passport_number")));
			person.setCreateDate(LocalDateTime.parse((rs.getString("create_date").substring(0, 19)), FORMATTER_FOR_LOCAL_DATE_TIME));
			person.setUpdateDate(LocalDateTime.parse((rs.getString("update_date").substring(0, 19)), FORMATTER_FOR_LOCAL_DATE_TIME));
			person.setLiveHouseId(Long.valueOf(rs.getString("live_house_id")));
			return person;
		};
		return rowMapper;
	}

	@Bean
	public RowMapper<House> rowMapperHouse(){
		RowMapper<House> rowMapper = (rs, rowNum)->{
			House house = new House();
			house.setId(rs.getLong("id"));
			house.setUuidHouse(UUID.fromString(rs.getString("uuid_house")));
			house.setArea(Double.valueOf(rs.getString("area")));
			house.setCountry(rs.getString("country"));
			house.setCity((rs.getString("city")));
			house.setStreet(rs.getString("street"));
			house.setNumber(Integer.valueOf(rs.getString("number")));
			house.setCreateDate(LocalDateTime.parse((rs.getString("create_date").substring(0, 19)), FORMATTER_FOR_LOCAL_DATE_TIME));
			return house;
		};
		return rowMapper;
	}

}
