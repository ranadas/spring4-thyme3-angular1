package com.rdas.profiled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by rdas on 04/10/2016.
 */
@Profile("prod")
@Configuration
public class PConfiguration {
    @PostConstruct
    public void init() {
        System.out.println(" \n\n ---- > In Prod Construct \n\n");
    }


    @Bean
    public DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        builder.setName("PROD-Database");
        return builder.build();
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        //databasePopulator.addScript(new ClassPathResource("schema.sql"));
        return databasePopulator;
    }

    private SimpleDriverDataSource createDataSource() {
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(org.h2.Driver.class);
//        simpleDriverDataSource.setUrl("jdbc:h2:target/database/example;AUTO_RECONNECT=TRUE");
        simpleDriverDataSource.setUsername("sa");
        simpleDriverDataSource.setPassword("sa");
        return simpleDriverDataSource;
    }
}
