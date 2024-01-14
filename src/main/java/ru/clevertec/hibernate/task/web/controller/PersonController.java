package ru.clevertec.hibernate.task.web.controller;

import jakarta.validation.Valid;
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
import ru.clevertec.hibernate.task.service.PersonOwnerService;
import ru.clevertec.hibernate.task.service.PersonSearchService;
import ru.clevertec.hibernate.task.service.PersonService;
import ru.clevertec.hibernate.task.web.request.PersonRequest;
import ru.clevertec.hibernate.task.web.response.HouseResponse;
import ru.clevertec.hibernate.task.web.response.PersonResponse;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonSearchService personSearchService;
    private final PersonOwnerService personOwnerService;
    private final PersonMapper personMapper;
    private final HouseMapper houseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PersonResponse save(
            @Valid @RequestBody PersonRequest personRequest) {
        return personMapper.dtoToResponse(personService.save(personRequest));
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponse findByUUID(@PathVariable UUID uuid) throws Exception {
       return personMapper.dtoToResponse(personService.getByUUID(uuid));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByUUID(@PathVariable UUID uuid) throws Exception {
        personService.deleteByUUID(uuid);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponse update(
            @Valid @RequestBody PersonRequest personRequest,
            @PathVariable UUID uuid) throws Exception {
        return personMapper.dtoToResponse(personService.update(uuid, personRequest));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> findAll(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "pagesize") Integer pagesize){
        return personMapper.toListResponse(personSearchService.findAllWithPagination(page, pagesize));
    }

    @GetMapping("/owner/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<HouseResponse> findAllHousesOwnByPerson(@PathVariable UUID uuid){
        return houseMapper.toListResponse(personOwnerService.findAllHousesOwn(uuid));
    }

}
