package ru.clevertec.hibernate.task.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseRequest {

    private Double area;
    private String country;
    private String city;
    private String street;
    private Integer number;

}
