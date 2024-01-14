package ru.clevertec.hibernate.task.web.request;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.hibernate.task.entity.model.Sex;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {

	private String name;
	private String surname;
	private Sex sex;

	@Max(value = 2)
	@Max (value = 2)
	private String passportSeries;

	@Max(value = 2)
	@Max (value = 2)
	private String passportNumber;
	private Long liveHouseId;

}
