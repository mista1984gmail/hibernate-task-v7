package ru.clevertec.hibernate.task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.hibernate.task.entity.dto.PersonDto;
import ru.clevertec.hibernate.task.entity.model.Person;
import ru.clevertec.hibernate.task.web.request.PersonRequest;
import ru.clevertec.hibernate.task.web.response.PersonResponse;

import java.util.List;

@Mapper
public interface PersonMapper {

	/**
	 * Маппит Person в PersonDto без id
	 *
	 * @param person - Person для маппинга
	 * @return новый PersonDto
	 */
	PersonDto personToPersonDto(Person person);

	/**
	 * Маппит список List<Person> в список List<PersonDto> без id
	 *
	 * @param people - List<Person> для маппинга
	 * @return новый List<PersonDto>
	 */
	List<PersonDto> peopleToPeopleDto(List<Person> people);

	/**
	 * Маппит PersonRequest в Person без id, uuidPerson, createDate, updateDate
	 *
	 * @param personRequest - PersonRequest для маппинга
	 * @return новый Person
	 */
	@Mapping(ignore = true, target = "id")
	@Mapping(ignore = true, target = "uuidPerson")
	@Mapping(ignore = true, target = "createDate")
	@Mapping(ignore = true, target = "updateDate")
	Person requestToEntity(PersonRequest personRequest);

	/**
	 * Маппит PersonDto в PersonResponse
	 *
	 * @param personDto - PersonDto для маппинга
	 * @return новый PersonResponse
	 */
	PersonResponse dtoToResponse(PersonDto personDto);

	/**
	 * Маппит List<PersonDto> в List<PersonResponse>
	 *
	 * @param people - List<PersonDto> для маппинга
	 * @return новый List<PersonResponse>
	 */
	List<PersonResponse> toListResponse(List<PersonDto> people);

}
