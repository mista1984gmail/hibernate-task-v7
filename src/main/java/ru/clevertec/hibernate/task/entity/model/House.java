package ru.clevertec.hibernate.task.entity.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table (name = "houses")
public class House {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uuid_house", updatable = false)
	private UUID uuidHouse;

	@Column(name = "area", nullable = false)
	private Double area;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "number", nullable = false)
	private Integer number;

	@Column(name = "create_date", updatable = false)
	private LocalDateTime createDate;


}
