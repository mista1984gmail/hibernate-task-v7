package ru.clevertec.hibernate.task.config.spring;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Objects;
import java.util.Properties;

@Configuration
@ComponentScan("ru.clevertec.hibernate.task")
@PropertySource("classpath:application.yml")
@EnableTransactionManagement
@EnableWebMvc
public class SpringConfig {

	@Bean
	public static BeanFactoryPostProcessor beanFactoryPostProcessor(){
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource("application.yml"));
		Properties yamlObject = Objects.requireNonNull(yaml.getObject(), "Yaml not found");
		configurer.setProperties(yamlObject);
		return configurer;
	}

}
