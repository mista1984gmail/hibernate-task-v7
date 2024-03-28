package ru.clevertec.hibernate.task.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id",
			updatable = false)
	private Long id;

	@Column(name = "uuid_person", updatable = false)
	private UUID uuidPerson;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name="sex",
			nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Sex sex;

	@Column(name = "passport_series", nullable = false)
	private String passportSeries;

	@Column(name = "passport_number", nullable = false)
	private String passportNumber;

	@Column(name = "create_date", updatable = false)
	private LocalDateTime createDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@Column(name = "live_house_id", nullable = false)
	private Long liveHouseId;

}
