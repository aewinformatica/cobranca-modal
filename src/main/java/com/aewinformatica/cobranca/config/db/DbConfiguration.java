package com.aewinformatica.cobranca.config.db;


import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration

//@PropertySource({ "classpath:application.properties" })
//@ConfigurationProperties("spring.datasource")
public class DbConfiguration {
	
    @Value("${spring.jpa.database}")
    String database;

//	@Bean
//    @Primary
//    @Profile("dev")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
	
//	@Bean
//	@Profile("dev")
//    public HikariDataSource dataSource(DataSourceProperties properties) {
//        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
//                .build();
//    }
	
	@Bean
	@Profile("prod")
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:"+database+"://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;

	}
}
