package ru.clevertec.hibernate.task.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.hibernate.task.mapper.HouseMapper;
import ru.clevertec.hibernate.task.mapper.PersonMapper;
import ru.clevertec.hibernate.task.service.HouseResidentService;
import ru.clevertec.hibernate.task.service.HouseSearchService;
import ru.clevertec.hibernate.task.service.HouseService;
import ru.clevertec.hibernate.task.service.PersonOwnerService;
import ru.clevertec.hibernate.task.web.request.HouseRequest;
import ru.clevertec.hibernate.task.web.response.HouseResponse;
import ru.clevertec.hibernate.task.web.response.PersonResponse;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/houses")
public class HouseController {

    private final HouseService houseService;
    private final PersonOwnerService personOwnerService;
    private final HouseSearchService houseSearchService;
    private final HouseMapper houseMapper;
    private final PersonMapper personMapper;
    private final HouseResidentService houseResidentService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public HouseResponse save(
            @RequestBody HouseRequest houseRequest) {
        return houseMapper.dtoToResponse(houseService.save(houseRequest));
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public HouseResponse findByUUID(
            @PathVariable UUID uuid) throws Exception {
       return houseMapper.dtoToResponse(houseService.getByUUID(uuid));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByUUID(
            @PathVariable UUID uuid) throws Exception {
        houseService.deleteByUUID(uuid);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public HouseResponse update(
            @RequestBody HouseRequest houseRequest,
            @PathVariable UUID uuid) throws Exception {
        return houseMapper.dtoToResponse(houseService.update(uuid, houseRequest));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HouseResponse> findAll(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "pagesize") Integer pagesize){
        return houseMapper.toListResponse(houseSearchService.findAllWithPagination(page, pagesize));
    }

    @GetMapping("/findBy")
    @ResponseStatus(HttpStatus.OK)
    public List<HouseResponse> findByCountryOrCityOrStreet(
            @RequestParam(value = "name") String name){
        return houseMapper.toListResponse(houseSearchService.findByCountryOrCityOrStreet(name));
    }

    @GetMapping("/owner/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<HouseResponse> findAllHousesOwnByPerson( @PathVariable UUID uuid){
        return houseMapper.toListResponse(personOwnerService.findAllHousesOwn(uuid));
    }

    @GetMapping("/residents/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> findAllResidents
            (@PathVariable UUID uuid){
        return personMapper.toListResponse(houseResidentService.findAllResidents(uuid));
    }

}