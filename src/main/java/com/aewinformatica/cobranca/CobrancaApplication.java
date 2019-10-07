package com.aewinformatica.cobranca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.aewinformatica.cobranca.config.property.AewCobrancaProperty;

@SpringBootApplication
@EnableConfigurationProperties(AewCobrancaProperty.class)//para usar a configuração externamente pelo Application.properties
public class CobrancaApplication {

	private static ApplicationContext APPLICATION_CONTEXT;
	
	public static void main(String[] args) {
		APPLICATION_CONTEXT = SpringApplication.run(CobrancaApplication.class, args);	
	}
	
	public static <T> T getBean(Class<T> requiredType) {
		return APPLICATION_CONTEXT.getBean(requiredType);
	}
	
}
