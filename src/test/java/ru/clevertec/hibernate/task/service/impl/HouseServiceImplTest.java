package ru.clevertec.hibernate.task.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.hibernate.task.entity.dto.HouseDto;
import ru.clevertec.hibernate.task.entity.model.House;
import ru.clevertec.hibernate.task.exception.HouseNotFoundException;
import ru.clevertec.hibernate.task.mapper.HouseMapper;
import ru.clevertec.hibernate.task.repository.HouseRepository;
import ru.clevertec.hibernate.task.util.ConstantsForTest;
import ru.clevertec.hibernate.task.util.HouseTestData;
import ru.clevertec.hibernate.task.web.request.HouseRequest;

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
class HouseServiceImplTest {

	@Mock
	private HouseRepository houseRepository;

	@Mock
	private HouseMapper houseMapper;

	@InjectMocks
	private HouseServiceImpl houseService;

	@Test
	void shouldDeleteHouse() throws Exception {
		// given
		UUID uuid = ConstantsForTest.UUID_HOUSE;
		House house = HouseTestData.builder()
								   .build()
								   .buildHouse();

		when(houseRepository.getByUUID(uuid))
				.thenReturn(house);
		doNothing()
				.when(houseRepository)
				.deleteByUUID(uuid);

		//when
		houseService.deleteByUUID(uuid);

		//then
		verify(houseRepository).deleteByUUID(uuid);
	}

	@Test
	void shouldNotDeleteHouseAndThrowsHouseNotFoundException() throws Exception {
		// given
		UUID uuid = ConstantsForTest.UUID_HOUSE;
		String errorMessage = "House with id: " + uuid + " not found";
		House house = HouseTestData.builder()
								   .withId(null)
								   .withUuidHouse(null)
								   .build()
								   .buildHouse();

		when(houseRepository.getByUUID(uuid))
				.thenReturn(house);

		//when
		HouseNotFoundException thrown = assertThrows(HouseNotFoundException.class, () -> {
			houseService.deleteByUUID(uuid);
		});

		//then
		Assertions.assertEquals(errorMessage, thrown.getMessage());
		verify(houseRepository, never()).deleteByUUID(uuid);
	}

	@Test
	void shouldNotGetHouseByUUIDAndThrowsHouseNotFoundException() throws Exception {
		// given
		UUID uuid = ConstantsForTest.UUID_HOUSE;
		String errorMessage = "House with id: " + uuid + " not found";
		House house = HouseTestData.builder()
								   .withId(null)
								   .withUuidHouse(null)
								   .build()
								   .buildHouse();

		when(houseRepository.getByUUID(uuid))
				.thenReturn(house);

		//when
		HouseNotFoundException thrown = assertThrows(HouseNotFoundException.class, () -> {
			houseService.getByUUID(uuid);
		});

		//then
		Assertions.assertEquals(errorMessage, thrown.getMessage());
	}

	@Test
	void shouldGetHouseByUUID() throws Exception {
		// given
		UUID uuid = ConstantsForTest.UUID_HOUSE;
		House house = HouseTestData.builder()
								   .build()
								   .buildHouse();
		HouseDto expected = HouseTestData.builder()
										 .build()
										 .buildHouseDto();

		when(houseRepository.getByUUID(uuid))
				.thenReturn(house);
		when(houseMapper.houseToHouseDto(house))
				.thenReturn(expected);

		//when
		HouseDto actual = houseService.getByUUID(uuid);

		//then
		assertEquals(expected, actual);
		verify(houseRepository).getByUUID(uuid);
		verify(houseMapper).houseToHouseDto(house);
	}

	@Test
	void shouldCreateHouse() throws Exception {
		// given
		HouseRequest houseRequestForSave = HouseTestData.builder()
														.build()
														.buildHouseRequest();
		House houseForSave = HouseTestData.builder()
										  .build()
										  .buildHouse();
		HouseDto expected = HouseTestData.builder()
										 .build()
										 .buildHouseDto();

		when(houseMapper.requestToEntity(houseRequestForSave))
				.thenReturn(houseForSave);
		when(houseRepository.save(houseForSave))
				.thenReturn(expected);

		//when
		HouseDto actual = houseService.save(houseRequestForSave);

		//then
		verify(houseRepository).save(houseForSave);

		assertEquals(expected, actual);
	}

	@Test
	void shouldUpdateHouse() throws Exception {
		// given
		HouseDto houseDtoForUpdate = HouseTestData.builder()
												  .withCity("Grodno")
												  .withStreet("Pushkina")
												  .withNumber(123)
												  .build()
												  .buildHouseDto();

		HouseDto houseDtoSaved = HouseTestData.builder()
											  .withCity("Grodno")
											  .withStreet("Pushkina")
											  .withNumber(123)
											  .build()
											  .buildHouseDto();

		HouseRequest houseRequestForUpdate = HouseTestData.builder()
														  .withCity("Grodno")
														  .withStreet("Pushkina")
														  .withNumber(123)
														  .build()
														  .buildHouseRequest();
		House houseFromDB = HouseTestData.builder()
										 .build()
										 .buildHouse();
		UUID uuid = ConstantsForTest.UUID_HOUSE;

		when(houseRepository.getByUUID(uuid)).thenReturn(houseFromDB);
		when(houseRepository.update(uuid, houseRequestForUpdate)).thenReturn(houseDtoSaved);

		//when
		HouseDto expected = houseService.update(uuid, houseRequestForUpdate);

		//then
		verify(houseRepository).update(uuid, houseRequestForUpdate);

		assertThat(houseDtoForUpdate)
				.hasFieldOrPropertyWithValue(House.Fields.uuidHouse, expected.getUuidHouse())
				.hasFieldOrPropertyWithValue(House.Fields.area, expected.getArea())
				.hasFieldOrPropertyWithValue(House.Fields.country, expected.getCountry())
				.hasFieldOrPropertyWithValue(House.Fields.city, expected.getCity())
				.hasFieldOrPropertyWithValue(House.Fields.street, expected.getStreet())
				.hasFieldOrPropertyWithValue(House.Fields.number, expected.getNumber())
				.hasFieldOrPropertyWithValue(House.Fields.createDate, expected.getCreateDate());
	}

	@Test
	void shouldNotUpdateHouseAndThrowsHouseNotFoundException() throws Exception {
		// given
		UUID uuid = ConstantsForTest.UUID_HOUSE;
		HouseRequest houseRequestForUpdate = HouseTestData.builder()
														  .withCity("Grodno")
														  .withStreet("Pushkina")
														  .withNumber(123)
														  .build()
														  .buildHouseRequest();
		String errorMessage = "House with id: " + uuid + " not found";
		House house = HouseTestData.builder()
								   .withId(null)
								   .withUuidHouse(null)
								   .build()
								   .buildHouse();

		when(houseRepository.getByUUID(uuid))
				.thenReturn(house);

		//when
		HouseNotFoundException thrown = assertThrows(HouseNotFoundException.class, () -> {
			houseService.update(uuid, houseRequestForUpdate);
		});

		//then
		Assertions.assertEquals(errorMessage, thrown.getMessage());
	}

	@Test
	void shouldGetAllHouses() throws Exception {
		// given
		UUID uuid = UUID.randomUUID();
		HouseDto firstHouse = HouseTestData.builder()
										   .build()
										   .buildHouseDto();
		HouseDto secondHouse = HouseTestData.builder()
											.withUuidHouse(uuid)
											.build()
											.buildHouseDto();
		List<HouseDto> houses = Arrays.asList(firstHouse, secondHouse);

		when(houseRepository.findAll())
				.thenReturn(houses);

		//when
		List<HouseDto> actual = houseService.findAll();

		//then
		assertArrayEquals(houses.toArray(), actual.toArray());
		assertEquals(2, actual.size());
		verify(houseRepository).findAll();
	}
}