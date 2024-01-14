package ru.clevertec.hibernate.task.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.hibernate.task.entity.model.Sex;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

	private UUID uuidPerson;
	private String name;
	private String surname;
	private Sex sex;
	private String passportSeries;
	private String passportNumber;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private Long liveHouseId;

}
