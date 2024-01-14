package ru.clevertec.hibernate.task.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.hibernate.task.entity.dto.PersonDto;
import ru.clevertec.hibernate.task.repository.PersonSearchRepository;
import ru.clevertec.hibernate.task.service.PersonSearchService;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonSearchServiceImpl implements PersonSearchService {

    private final PersonSearchRepository personSearchRepository;

    /**
     * Возвращает все существующих Person с пагинацией
     *
     * @param pagesize - сколько отобразить PersonDto на одном листе
     * @param page - номер страницы
     * @return лист с информацией о PersonDto
     */
    @Override
    public List<PersonDto> findAllWithPagination(int pagesize, int page) {
        return personSearchRepository.findAllWithPagination(pagesize, page);
    }
}
