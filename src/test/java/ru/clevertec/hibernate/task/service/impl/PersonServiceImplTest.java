package ru.clevertec.hibernate.task.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.hibernate.task.entity.dto.PersonDto;
import ru.clevertec.hibernate.task.entity.model.Person;
import ru.clevertec.hibernate.task.exception.PersonNotFoundException;
import ru.clevertec.hibernate.task.mapper.PersonMapper;
import ru.clevertec.hibernate.task.repository.PersonRepository;
import ru.clevertec.hibernate.task.util.ConstantsForTest;
import ru.clevertec.hibernate.task.util.PersonTestData;
import ru.clevertec.hibernate.task.web.request.PersonRequest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

	@Mock
	private PersonRepository personRepository;

	@Mock
	private PersonMapper personMapper;

	@InjectMocks
	private PersonServiceImpl personService;

	@Test
	void shouldDeletePerson() throws Exception {
		// given
		UUID uuid = ConstantsForTest.UUID_PERSON;
		Person person = PersonTestData.builder()
									  .build()
									  .buildPerson();

		when(personRepository.getByUUID(uuid))
				.thenReturn(person);
		doNothing()
				.when(personRepository)
				.deleteByUUID(uuid);

		//when
		personService.deleteByUUID(uuid);

		//then
		verify(personRepository).deleteByUUID(uuid);
	}

	@Test
	void shouldNotDeletePersonAndThrowsPersonNotFoundException() throws Exception {
		// given
		UUID uuid = ConstantsForTest.UUID_PERSON;
		String errorMessage = "Person with id: " + uuid + " not found";
		Person person = PersonTestData.builder()
									  .withId(null)
									  .withUuidPerson(null)
									  .build()
									  .buildPerson();

		when(personRepository.getByUUID(uuid))
				.thenReturn(person);

		//when
		PersonNotFoundException thrown = assertThrows(PersonNotFoundException.class, () -> {
			personService.deleteByUUID(uuid);
		});

		//then
		Assertions.assertEquals(errorMessage, thrown.getMessage());
		verify(personRepository, never()).deleteByUUID(uuid);
	}

	@Test
	void shouldNotGetPersonByUUIDAndThrowsHouseNotFoundException() throws Exception {
		// given
		UUID uuid = ConstantsForTest.UUID_PERSON;
		String errorMessage = "Person with id: " + uuid + " not found";
		Person person = PersonTestData.builder()
									  .withId(null)
									  .withUuidPerson(null)
									  .build()
									  .buildPerson();

		when(personRepository.getByUUID(uuid))
				.thenReturn(person);

		//when
		PersonNotFoundException thrown = assertThrows(PersonNotFoundException.class, () -> {
			personService.getByUUID(uuid);
		});

		//then
		Assertions.assertEquals(errorMessage, thrown.getMessage());
	}

	@Test
	void shouldGetPersonByUUID() throws Exception {
		// given
		UUID uuid = ConstantsForTest.UUID_PERSON;
		Person person = PersonTestData.builder()
									  .build()
									  .buildPerson();
		PersonDto expected = PersonTestData.builder()
										   .build()
										   .buildPersonDto();

		when(personRepository.getByUUID(uuid))
				.thenReturn(person);
		when(personMapper.personToPersonDto(person))
				.thenReturn(expected);

		//when
		PersonDto actual = personService.getByUUID(uuid);

		//then
		assertEquals(expected, actual);
		verify(personRepository).getByUUID(uuid);
		verify(personMapper).personToPersonDto(person);
	}

	@Test
	void shouldCreatePerson() throws Exception {
		// given
		PersonRequest personRequestForSave = PersonTestData.builder()
														   .build()
														   .buildPersonRequest();
		Person personForSave = PersonTestData.builder()
											 .build()
											 .buildPerson();
		PersonDto expected = PersonTestData.builder()
										   .build()
										   .buildPersonDto();

		when(personMapper.requestToEntity(personRequestForSave))
				.thenReturn(personForSave);
		when(personRepository.save(personForSave))
				.thenReturn(expected);

		//when
		PersonDto actual = personService.save(personRequestForSave);

		//then
		verify(personRepository).save(personForSave);

		assertEquals(expected, actual);
	}

	@Test
	void shouldUpdatePerson() throws Exception {
		// given
		PersonDto personDtoForUpdate = PersonTestData.builder()
													 .withSurname("Serveev")
													 .build()
													 .buildPersonDto();

		PersonDto personDtoSaved = PersonTestData.builder()
												 .withSurname("Serveev")
												 .build()
												 .buildPersonDto();

		PersonRequest personRequestForUpdate = PersonTestData.builder()
															 .withSurname("Serveev")
															 .build()
															 .buildPersonRequest();
		Person personFromDB = PersonTestData.builder()
											.build()
											.buildPerson();
		UUID uuid = ConstantsForTest.UUID_PERSON;

		when(personRepository.getByUUID(uuid)).thenReturn(personFromDB);
		when(personRepository.update(uuid, personRequestForUpdate)).thenReturn(personDtoSaved);

		//when
		PersonDto expected = personService.update(uuid, personRequestForUpdate);

		//then
		verify(personRepository).update(uuid, personRequestForUpdate);

		assertThat(personDtoForUpdate)
				.hasFieldOrPropertyWithValue(Person.Fields.uuidPerson, expected.getUuidPerson())
				.hasFieldOrPropertyWithValue(Person.Fields.name, expected.getName())
				.hasFieldOrPropertyWithValue(Person.Fields.surname, expected.getSurname())
				.hasFieldOrPropertyWithValue(Person.Fields.sex, expected.getSex())
				.hasFieldOrPropertyWithValue(Person.Fields.passportSeries, expected.getPassportSeries())
				.hasFieldOrPropertyWithValue(Person.Fields.passportNumber, expected.getPassportNumber())
				.hasFieldOrPropertyWithValue(Person.Fields.createDate, expected.getCreateDate())
				.hasFieldOrPropertyWithValue(Person.Fields.updateDate, expected.getUpdateDate())
				.hasFieldOrPropertyWithValue(Person.Fields.liveHouseId, expected.getLiveHouseId());
	}

	@Test
	void shouldNotUpdatePersonAndThrowsPersonNotFoundException() throws Exception {
		// given
		UUID uuid = ConstantsForTest.UUID_PERSON;
		PersonRequest personRequestForUpdate = PersonTestData.builder()
															 .withSurname("Serveev")
															 .build()
															 .buildPersonRequest();
		String errorMessage = "Person with id: " + uuid + " not found";
		Person person = PersonTestData.builder()
									  .withId(null)
									  .withUuidPerson(null)
									  .build()
									  .buildPerson();

		when(personRepository.getByUUID(uuid))
				.thenReturn(person);

		//when
		PersonNotFoundException thrown = assertThrows(PersonNotFoundException.class, () -> {
			personService.update(uuid, personRequestForUpdate);
		});

		//then
		Assertions.assertEquals(errorMessage, thrown.getMessage());
	}

	@Test
	void shouldGetAllPersons() throws Exception {
		// given
		UUID uuid = UUID.randomUUID();
		PersonDto firstPerson = PersonTestData.builder()
											  .build()
											  .buildPersonDto();
		PersonDto secondPerson = PersonTestData.builder()
											   .withUuidPerson(uuid)
											   .build()
											   .buildPersonDto();
		List<PersonDto> persons = Arrays.asList(firstPerson, secondPerson);

		when(personRepository.findAll())
				.thenReturn(persons);

		//when
		List<PersonDto> actual = personService.findAll();

		//then
		assertArrayEquals(persons.toArray(), actual.toArray());
		assertEquals(2, actual.size());
		verify(personRepository).findAll();
	}

}