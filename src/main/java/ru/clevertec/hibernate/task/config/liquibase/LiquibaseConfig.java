package ru.clevertec.hibernate.task.config.liquibase;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.hibernate.task.util.Constants;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

	@Bean
	public SpringLiquibase liquibase(DataSource dataSource) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog(Constants.LIQUIBASE_CHANGE_LOG);
		return liquibase;
	}

}
