package ru.clevertec.hibernate.task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.hibernate.task.entity.dto.HouseDto;
import ru.clevertec.hibernate.task.entity.model.House;
import ru.clevertec.hibernate.task.web.request.HouseRequest;
import ru.clevertec.hibernate.task.web.response.HouseResponse;

import java.util.List;

@Mapper
public interface HouseMapper {

	/**
	 * Маппит House в HouseDto без id
	 *
	 * @param house - House для маппинга
	 * @return новый DTO
	 */
	HouseDto houseToHouseDto(House house);

	/**
	 * Маппит список House в список HouseDto без id
	 *
	 * @param houses - List<House> для маппинга
	 * @return новый List<HouseDto>
	 */
	List<HouseDto> housesToHousesDto(List<House> houses);

	/**
	 * Маппит HouseRequest в House без id, uuidHouse, createDate
	 *
	 * @param houseRequest - HouseRequest для маппинга
	 * @return новый House
	 */
	@Mapping(ignore = true, target = "id")
	@Mapping(ignore = true, target = "uuidHouse")
	@Mapping(ignore = true, target = "createDate")
	House requestToEntity(HouseRequest houseRequest);

	/**
	 * Маппит HouseDto в HouseResponse
	 *
	 * @param houseDto - HouseDto для маппинга
	 * @return новый HouseResponse
	 */
	HouseResponse dtoToResponse(HouseDto houseDto);

	/**
	 * Маппит List<HouseDto> в List<HouseResponse>
	 *
	 * @param houses - List<HouseDto> для маппинга
	 * @return новый List<HouseResponse>
	 */
	List<HouseResponse> toListResponse(List<HouseDto> houses);

}
